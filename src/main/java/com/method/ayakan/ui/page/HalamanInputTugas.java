/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.method.ayakan.ui.page;

import java.time.LocalDate;
import java.time.format.DateTimeParseException;
import java.util.ArrayList;

import com.method.ayakan.exception.DataNotFoundException;
import com.method.ayakan.model.MataKuliah;
import com.method.ayakan.model.TIAkademik;
import com.method.ayakan.model.TIOrganisasi;
import com.method.ayakan.model.TKAkademik;
import com.method.ayakan.model.TKOrganisasi;
import com.method.ayakan.model.Tugas;
import com.method.ayakan.model.TugasKelompok;
import com.method.ayakan.service.MataKuliahManager;
import com.method.ayakan.service.TaskManager;
import com.method.ayakan.ui.MissionUtil;

public class HalamanInputTugas {

    private final TaskManager taskManager;
    private final MataKuliahManager mkManager;
    private final HalamanTugas halamanTugas;

    public HalamanInputTugas(TaskManager taskManager, MataKuliahManager mkManager, HalamanTugas halamanTugas) {
        this.taskManager = taskManager;
        this.mkManager = mkManager;
        this.halamanTugas = halamanTugas;
    }

    public void inputTambahTugas() {
        System.out.println("\n=== TAMBAH TUGAS BARU ===");
        System.out.print("Judul Tugas: ");
        String judul = MissionUtil.getUserInput();
        System.out.print("Deskripsi: ");
        String desc = MissionUtil.getUserInput();
        String priority = inputPriority();
        LocalDate deadline = inputDeadline();

        Tugas t = pilihJenisTugas(judul, desc, priority, deadline, false);
        if (t != null) {
            taskManager.tambahTugas(t);
            System.out.println("Berhasil ditambah!");
        }
        System.out.print("\nTekan Enter untuk kembali...");
        MissionUtil.getUserInput();
    }
    
    public void inputEditTugas() {
        halamanTugas.tampilkanDaftar();
        ArrayList<Tugas> list = taskManager.tampilkanTugas();

        if (list.isEmpty()) {
            return;
        }

        try {
            System.out.print("\nPilih nomor tugas yang ingin di-edit (0 untuk batal): ");
            int nomor = Integer.parseInt(MissionUtil.getUserInput());

            if (nomor == 0) {
                System.out.println("Edit tugas dibatalkan.");
                return;
            }

            int index = nomor - 1;

//            manggil semua isi tugas berdasarkan index/nomor tugas yg dipilih 
            Tugas tugasLama = list.get(index);

            String judulBaru = tugasLama.getJudul();
            String descBaru = tugasLama.getDeskripsi();
            String priorityBaru = tugasLama.getPriority();
            LocalDate deadlineBaru = tugasLama.getDeadline();

//            nemuin(?) apakah tugasnya akademik/org/kelmpok
            boolean isAkademik = isAkademik(tugasLama);
            boolean isOrganisasi = isOrganisasi(tugasLama);
            boolean isKelompok = tugasLama.isKelompok();

            System.out.println("\n=== EDIT TUGAS ===");

            System.out.print(
                    "1. Judul Tugas\n"
                    + "2. Deskripsi Tugas\n"
                    + "3. Priority Tugas\n"
                    + "4. Deadline Tugas\n");

//            nentuin tampilan di menu berdasarkan boolean di atas
            if (isAkademik) {
                System.out.println("5. Mata Kuliah");
            }
            if (isOrganisasi) {
                System.out.println("5. Nama Organisasi");
            }
            if (isKelompok) {
                System.out.println("6. Nama Kelompok");
                System.out.println("7. Daftar Anggota Kelompok");
            }

            System.out.println("0. Batal");

            System.out.println("Pilih atribut yang mau diganti:");
            int pilih = Integer.parseInt(MissionUtil.getUserInput());

            switch (pilih) {
                case 0:
                    System.out.println("Edit tugas dibatalkan.");
                    break;
                case 1:
                    System.out.print("Masukkan judul baru:");
                    judulBaru = MissionUtil.getUserInput();
                    break;
                case 2:
                    System.out.print("Masukkan deskripsi baru:");
                    descBaru = MissionUtil.getUserInput();
                    break;
                case 3:
                    priorityBaru = inputPriority();
                    break;
                case 4:
                    deadlineBaru = inputDeadline();
                    break;
                case 5:

                    if (isAkademik) {
                        MataKuliah matkulBaru = pilihMatkulTugas();
                        setMatkul(tugasLama, matkulBaru);
                    } else if (isOrganisasi) {
                        System.out.print("Masukkan Nama Organisasi baru: ");
                        String orgBaru = MissionUtil.getUserInput();
                        setOrganisasi(tugasLama, orgBaru);
                    } else {
                        System.out.println("Pilihan tidak valid!");
                        return;
                    }
                    break;
                case 6:
                    if (isKelompok) {
                        System.out.print("Masukkan nama kelompok baru:");
                        String kelBaru = MissionUtil.getUserInput();
                        ((TugasKelompok) tugasLama).setNamaKel(kelBaru);
                    } else {
                        System.out.println("Pilihan tidak valid!");
                        return;
                    }
                    break;
                case 7:

                    if (isKelompok) {

                        ArrayList<String> anggotaBaru
                                = inputAnggotaKelompok();

                        ((TugasKelompok) tugasLama)
                                .setAnggota(anggotaBaru);

                    } else {

                        System.out.println("Pilihan tidak valid!");
                        return;

                    }

                    break;
                default:
                    throw new AssertionError();
            }

            taskManager.editTugas(index, judulBaru, descBaru, priorityBaru, deadlineBaru);
            System.out.println("\nBerhasil! Tugas telah diperbarui.");

        } catch (NumberFormatException e) {
            System.out.println("Input harus berupa angka!");
        } catch (DataNotFoundException e) {
            System.out.println(e.getMessage());
        }

    }

    public void inputHapusTugas() {
        System.out.println("\n=== HAPUS TUGAS ===");
        halamanTugas.tampilkanDaftar();
        System.out.print("Pilih nomor tugas untuk dihapus: ");
        try {
            int index = Integer.parseInt(MissionUtil.getUserInput()) - 1;
            taskManager.hapusTugas(index);
            System.out.println("Tugas berhasil dihapus!");
        } catch (DataNotFoundException e) {
            System.out.println(e.getMessage());
        }
        System.out.print("\nTekan Enter untuk kembali...");
        MissionUtil.getUserInput();
    }

    public void inputUbahStatusTugas() {
        System.out.println("\n=== UBAH STATUS TUGAS ===");
        halamanTugas.tampilkanDaftar();
        System.out.print("Pilih nomor tugas: ");
        try {
            int index = Integer.parseInt(MissionUtil.getUserInput()) - 1;
            System.out.print("Tugas sudah selesai? (y/n): ");
            boolean status = MissionUtil.getUserInput().equalsIgnoreCase("y");
            taskManager.ubahStatusTugas(index, status);
            System.out.println("Status berhasil diubah!");
        } catch (Exception e) {
            System.out.println("Gagal: " + e.getMessage());
        }
        System.out.print("\nTekan Enter untuk kembali...");
        MissionUtil.getUserInput();
    }

    private Tugas pilihJenisTugas(String judul, String deskripsi, String priority, LocalDate deadline, boolean status) {
        System.out.println("\n--- Pilih Jenis Tugas ---");
        System.out.println("1. Individu");
        System.out.println("2. Kelompok");
        System.out.print("Pilihan (1-2): ");
        String pilihan = MissionUtil.getUserInput();

        if (pilihan.equals("1")) {
            return buatTugasIndividu(judul, deskripsi, priority, deadline, status);
        } else if (pilihan.equals("2")) {
            return buatTugasKelompok(judul, deskripsi, priority, deadline, status);
        }
        return null;
    }

    private Tugas buatTugasIndividu(String judul, String deskripsi, String priority, LocalDate deadline, boolean status) {
        System.out.println("1. Akademik, "
                + "\n2. Organisasi");
        String sub = MissionUtil.getUserInput();
        if (sub.equals("1")) {
            return new TIAkademik(pilihMatkulTugas(), judul, deskripsi, status, priority, deadline);
        } else {
            System.out.print("Nama Organisasi: ");
            return new TIOrganisasi(MissionUtil.getUserInput(), judul, deskripsi, status, priority, deadline);
        }
    }

    private Tugas buatTugasKelompok(String judul, String deskripsi, String priority, LocalDate deadline, boolean status) {
        System.out.print("Nama Kelompok: ");
        String namaKel = MissionUtil.getUserInput();
        ArrayList<String> anggota = inputAnggotaKelompok();
        System.out.println("1. Akademik, 2. Organisasi");
        String sub = MissionUtil.getUserInput();
        if (sub.equals("1")) {
            return new TKAkademik(pilihMatkulTugas(), namaKel, anggota, judul, deskripsi, status, priority, deadline);
        } else {
            System.out.print("Nama Organisasi: ");
            return new TKOrganisasi(MissionUtil.getUserInput(), namaKel, anggota, judul, deskripsi, status, priority, deadline);
        }
    }

    // Method untuk memilih mata kuliah untuk tugas akademik
    private MataKuliah pilihMatkulTugas() {

        mkManager.tampilkanSemua();

        System.out.print("Pilih ID Matkul: ");

        int id = Integer.parseInt(
                MissionUtil.getUserInput()
        );

        return mkManager.cariMatkulById(id);

    }

    private ArrayList<String> inputAnggotaKelompok() {
        ArrayList<String> list = new ArrayList<>();
        System.out.print("Jumlah anggota: ");
        try {
            int jml = Integer.parseInt(MissionUtil.getUserInput());
            for (int i = 0; i < jml; i++) {
                System.out.print("Nama " + (i + 1) + ": ");
                list.add(MissionUtil.getUserInput());
            }
        } catch (Exception e) {
        }
        return list;
    }

    private String inputPriority() {
        System.out.println("\n--- Pilih Prioritas ---");
        System.out.println("1. Low");
        System.out.println("2. Medium");
        System.out.println("3. High");
        System.out.print("Pilihan (1-3): ");
        String p = MissionUtil.getUserInput();

        switch (p) {
            case "1":
                return "Low";
            case "3":
                return "High";
            default:
                return "Medium";
        }
    }

    private LocalDate inputDeadline() {
        while (true) {
            try {
                System.out.print("Deadline (YYYY-MM-DD): ");
                return LocalDate.parse(MissionUtil.getUserInput());
            } catch (DateTimeParseException e) {
                System.out.println("Format salah!");
            }
        }
    }

    // HELPER METHODS BUAT MENGECEK JENIS TUGAS (AKADEMIK/ORGANISASI/KELOMPOK) UNTUK EDIT TUGAS =============
    // Method untuk mengubah mata kuliah pada tugas akademik
    private void setMatkul(Tugas tugas, MataKuliah matkulBaru) {

        if (tugas instanceof TIAkademik) {

            ((TIAkademik) tugas)
                    .setMataKuliah(matkulBaru);

        } else if (tugas instanceof TKAkademik) {

            ((TKAkademik) tugas)
                    .setMataKuliah(matkulBaru);

        }

    }

    // Method untuk mengubah organisasi pada tugas organisasi
    private void setOrganisasi(Tugas tugas, String orgBaru) {

        if (tugas instanceof TIOrganisasi) {
            ((TIOrganisasi) tugas).setNamaOrganisasi(orgBaru);

        } else if (tugas instanceof TKOrganisasi) {
            ((TKOrganisasi) tugas).setNamaOrganisasi(orgBaru);
        }

    }

    // Method untuk memeriksa apakah tugas adalah tugas akademik
    private boolean isAkademik(Tugas tugas) {
        return tugas instanceof TIAkademik
                || tugas instanceof TKAkademik;
    }

    // Method untuk memeriksa apakah tugas adalah tugas kelompok
    private boolean isOrganisasi(Tugas tugas) {
        return tugas instanceof TIOrganisasi
                || tugas instanceof TKOrganisasi;
    }

}
