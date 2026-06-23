/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.method.ayakan.ui.page;

import java.time.LocalDate;
import java.time.format.DateTimeParseException;
import java.util.ArrayList;
import com.method.ayakan.service.TaskManager;
import com.method.ayakan.model.*;
import com.method.ayakan.exception.*;
import com.method.ayakan.service.MataKuliahManager;
import java.time.temporal.ChronoUnit;
import com.method.ayakan.ui.MissionUtil;

public class halamanTugas {

    private final TaskManager taskManager;

    private final MataKuliahManager mkManager;

    // JANGAN DI HAPUS
    public halamanTugas(TaskManager taskManager, MataKuliahManager mkManager) {
        this.taskManager = taskManager;
        this.mkManager = mkManager;
    }

    public void tampilkanMenuTugas() {
        boolean isRunning = true;
        while (isRunning) {
            System.out.println("\n+==============================================+");
            System.out.println("|                HALAMAN TUGAS                 |");
            System.out.println("+==============================================+");
            System.out.println("|  [1] Tampilkan Semua Tugas                   |");
            System.out.println("|  [2] Deadline Terdekat                       |");
            System.out.println("|  [3] Tambah Tugas Baru                       |");
            System.out.println("|  [4] Edit Tugas                              |");
            System.out.println("|  [5] Ubah Status Tugas                       |");
            System.out.println("|  [6] Hapus Tugas                             |");
            System.out.println("+----------------------------------------------+");
            System.out.println("|  [0] Kembali ke Main Menu                    |");
            System.out.println("+==============================================+");
            System.out.print("Pilih Nomor(0-6) : ");

            String pilihan = MissionUtil.getUserInput();

            switch (pilihan) {
                case "1":
                    tampilkanDaftar();
                    break;
                case "2":
                    tampilkanDeadlineTerdekat();
                    break;
                case "3":
                    inputTambahTugas();
                    break;
                case "4":
                    inputEditTugas();
                    break;
                case "5":
                    inputUbahStatusTugas();
                    break;
                case "6":
                    inputHapusTugas();
                    break;
                case "0":
                    System.out.println("Keluar dari Halaman Tugas...");
                    isRunning = false;
                    break;
                default:
                    System.out.println("Pilihan tidak valid!");
            }
        }
    }

    private void tampilkanDaftar() {
        System.out.println("\n+===================================================================================================================+");
        System.out.println("|                                               DAFTAR TUGAS                                                        |");
        System.out.println("+----+-------------------------+-----------------+----------+--------------+--------------+-------------------+");
        System.out.println("| No | Judul                   | Matkul          | Prioritas| Deadline     | Status       | Sisa Hari         |");
        System.out.println("+----+-------------------------+-----------------+----------+--------------+--------------+-------------------+");

        ArrayList<Tugas> list = taskManager.tampilkanTugas();

        if (list.isEmpty()) {
            System.out.println("|                                         BELUM ADA TUGAS SAAT INI                                                  |");
        } else {
            LocalDate hariIni = LocalDate.now();

            for (int i = 0; i < list.size(); i++) {
                Tugas t = list.get(i);
                long sisaHari = ChronoUnit.DAYS.between(hariIni, t.getDeadline());
                String status = t.getStatus() ? "Selesai" : "Belum";

                // --- LOGIKA MENGAMBIL NAMA MATKUL DENGAN INSTANCEOF ---
                String namaMatkul = "-";
                if (t instanceof TIAkademik) {
                    namaMatkul = ((TIAkademik) t).getNamaMataKuliah();
                } else if (t instanceof TKAkademik) {
                    namaMatkul = ((TKAkademik) t).getNamaMataKuliah();
                }
                // Jika terlalu panjang, kita potong biar tabel ga jebol
                if (namaMatkul.length() > 15) {
                    namaMatkul = namaMatkul.substring(0, 12) + "...";
                }

                System.out.printf("| %-2d | %-23s | %-15s | %-8s | %-12s | %-12s | H-%-15d |%n",
                        i + 1, t.getJudul(), namaMatkul, t.getPriority(), t.getDeadline(), status, sisaHari);
            }
        }
        System.out.println("+----+-------------------------+-----------------+----------+--------------+--------------+-------------------+");
    }

    private void lihatDaftarTugas() {
        tampilkanDaftar();
        System.out.println("\nTekan ENTER untuk kembali...");
        MissionUtil.getUserInput();
    }

    private void inputTambahTugas() {
        System.out.println("\n=== TAMBAH TUGAS BARU ===");
        System.out.print("Judul Tugas: ");
        String judul = MissionUtil.getUserInput();
        System.out.print("Deskripsi Tugas: ");
        String deskripsi = MissionUtil.getUserInput();

        String priority = inputPriority();
        LocalDate deadline = inputDeadline();
        boolean statusAwal = false;

        Tugas tugasBaru = pilihJenisTugas(judul, deskripsi, priority, deadline, statusAwal);

        if (tugasBaru == null) {
            System.out.println("Gagal membuat tugas. Pilihan menu kategori tidak valid!");
            return;
        }

        taskManager.tambahTugas(tugasBaru);
        System.out.println("Berhasil! Tugas baru telah ditambahkan ke sistem.");
    }

    private Tugas pilihJenisTugas(String judul, String deskripsi, String priority, LocalDate deadline, boolean statusAwal) {

        System.out.println("\nPilih Jenis Tugas:");
        System.out.println("1. Tugas Individu");
        System.out.println("2. Tugas Kelompok");
        System.out.print("Pilihan (1-2): ");
        String pilihanJenis = MissionUtil.getUserInput();

        if (pilihanJenis.equals("1")) {
            return buatTugasIndividu(judul, deskripsi, priority, deadline, statusAwal);
        } else if (pilihanJenis.equals("2")) {
            return buatTugasKelompok(judul, deskripsi, priority, deadline, statusAwal);
        }
        return null;
    }

    private Tugas buatTugasIndividu(String judul, String deskripsi, String priority, LocalDate deadline, boolean statusAwal) {

        System.out.println("\nPilih Kategori Tugas Individu:");
        System.out.println("1. Akademik (TIAkademik)");
        System.out.println("2. Organisasi (TIOrganisasi)");
        System.out.print("Pilihan (1-2): ");
        String subPilihan = MissionUtil.getUserInput();

        if (subPilihan.equals("1")) {
            String matkul = pilihMatkulTugas();
            return new TIAkademik(matkul, judul, deskripsi, statusAwal, priority, deadline);
        } else if (subPilihan.equals("2")) {
            System.out.print("Nama Organisasi: ");
            String organisasi = MissionUtil.getUserInput();
            return new TIOrganisasi(organisasi, judul, deskripsi, statusAwal, priority, deadline);
        }
        return null;
    }

    private Tugas buatTugasKelompok(String judul, String deskripsi, String priority, LocalDate deadline, boolean statusAwal) {

        System.out.print("Nama Kelompok: ");
        String namaKel = MissionUtil.getUserInput();
        ArrayList<String> anggota = inputAnggotaKelompok();

        System.out.println("\nPilih Kategori Tugas Kelompok:");
        System.out.println("1. Akademik (TKAkademik)");
        System.out.println("2. Organisasi (TKOrganisasi)");
        System.out.print("Pilihan (1-2): ");
        String subPilihan = MissionUtil.getUserInput();

        if (subPilihan.equals("1")) {
            String matkul = pilihMatkulTugas();
            return new TKAkademik(matkul, namaKel, anggota, judul, deskripsi, statusAwal, priority, deadline);
        } else if (subPilihan.equals("2")) {
            System.out.print("Nama Organisasi: ");
            String organisasi = MissionUtil.getUserInput();
            return new TKOrganisasi(organisasi, namaKel, anggota, judul, deskripsi, statusAwal, priority, deadline);
        }
        return null;
    }

    private String pilihMatkulTugas() {
        if (mkManager.isEmpty()) {
            System.out.println("[Info] Belum ada Mata Kuliah yang terdaftar. Matkul akan dikosongkan (-).");
            return "-";
        }

        System.out.println("\n--- Pilih Mata Kuliah untuk Tugas Ini ---");
        mkManager.tampilkanSemua();
        System.out.println("Ketik [0] jika tidak ingin menautkan mata kuliah apapun.");

        while (true) {
            System.out.print("Pilih ID Matkul (atau 0): ");
            try {
                int idDipilih = Integer.parseInt(MissionUtil.getUserInput());

                if (idDipilih == 0) {
                    return "-";
                }

                MataKuliah mk = mkManager.cariMatkulById(idDipilih);
                if (mk != null) {
                    return mk.getNamaMatkul();
                } else {
                    System.out.println("[Error] ID Mata Kuliah tidak ditemukan. Silakan coba lagi.");
                }
            } catch (NumberFormatException e) {
                System.out.println("[Error] Input harus berupa angka!");
            }
        }
    }

    private ArrayList<String> inputAnggotaKelompok() {
        ArrayList<String> anggota = new ArrayList<>();
        System.out.print("Masukkan jumlah anggota kelompok: ");

        try {
            int jmlAnggota = Integer.parseInt(MissionUtil.getUserInput());
            for (int i = 0; i < jmlAnggota; i++) {
                System.out.print("Nama Anggota ke-" + (i + 1) + ": ");
                anggota.add(MissionUtil.getUserInput());
            }
        } catch (NumberFormatException e) {
            System.out.println("Input jumlah harus berupa angka! Anggota dikosongkan dahulu.");
        }
        return anggota;
    }

    private String inputPriority() {
        while (true) {
            System.out.println("\nPilih Prioritas:");
            System.out.println("1. Low");
            System.out.println("2. Medium");
            System.out.println("3. High");
            System.out.print("Pilihan (1-3): ");
            String pilihan = MissionUtil.getUserInput();

            switch (pilihan) {
                case "1":
                    return "Low";
                case "2":
                    return "Medium";
                case "3":
                    return "High";
                default:
                    System.out.println("Pilihan tidak valid! Masukkan angka 1, 2, atau 3.");
            }
        }
    }

    private LocalDate inputDeadline() {

        while (true) {
            System.out.print("Deadline (YYYY-MM-DD): ");

            try {

                LocalDate deadline = LocalDate.parse(MissionUtil.getUserInput());
                validasiDeadline(deadline);

                return deadline;

            } catch (DateTimeParseException e) {

                System.out.println("Format tanggal salah! Gunakan YYYY-MM-DD.");

            } catch (InvalidDeadlineException e) {

                System.out.println(e.getMessage());
            }
        }
    }

    private void validasiDeadline(LocalDate deadline) throws InvalidDeadlineException {
        LocalDate sekarang = LocalDate.now();

        if (deadline.getYear() < sekarang.getYear()) {
            throw new InvalidDeadlineException("Tahun deadline tidak boleh kurang dari tahun sekarang!");
        }

        if (deadline.isBefore(sekarang)) {

            throw new InvalidDeadlineException("Deadline tidak boleh berada di masa lalu!");
        }
    }

    private void inputEditTugas() {
        tampilkanDaftar();
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
            String priorityBaru = tugasLama.getDeskripsi();
            LocalDate deadlineBaru = tugasLama.getDeadline();

//            nemuin(?) apakah tugasnya akademik/org/kelmpok
            boolean isAkademik = (tugasLama instanceof TIAkademik || tugasLama instanceof TKAkademik);
            boolean isOrganisasi = (tugasLama instanceof TIOrganisasi || tugasLama instanceof TKOrganisasi);
            boolean isKelompok = (tugasLama instanceof TKAkademik || tugasLama instanceof TKOrganisasi);

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
                        String matkulBaru = pilihMatkulTugas();
                        if (tugasLama instanceof TIAkademik) {
//                            proses casting; tergantung dari jenis tugas di tugasnyaa
                            ((TIAkademik) tugasLama).setNamaMataKuliah(matkulBaru);
                        } else if (tugasLama instanceof TKAkademik) {
                            ((TKAkademik) tugasLama).setNamaMataKuliah(matkulBaru);
                        }
                    } else if (isOrganisasi) {
                        System.out.print("Masukkan Nama Organisasi baru: ");
                        String orgBaru = MissionUtil.getUserInput();
                        if (tugasLama instanceof TIOrganisasi) {
                            ((TIOrganisasi) tugasLama).setNamaOrganisasi(orgBaru);
                        } else if (tugasLama instanceof TKOrganisasi) {
                            ((TKOrganisasi) tugasLama).setNamaOrganisasi(orgBaru);
                        }
                    } else {
                        System.out.println("Pilihan tidak valid!");
                        return;
                    }
                    break;
                case 6:
                    if (isKelompok) {
                        System.out.print("Masukkan nama kelompok baru:");
                        String kelBaru = MissionUtil.getUserInput();
                        if (tugasLama instanceof TKAkademik) {
//                            setter class tugaskelompok
                            ((TKAkademik) tugasLama).setNamaKel(kelBaru);
                        } else if (tugasLama instanceof TKOrganisasi) {
                            ((TKOrganisasi) tugasLama).setNamaKel(kelBaru);
                        }
                    } else {
                        System.out.println("Pilihan tidak valid!");
                        return;
                    }
                    break;
                case 7:
                    if (isKelompok) {
                        ArrayList<String> anggotaBaru = inputAnggotaKelompok();
                        if (tugasLama instanceof TKAkademik) {
//                            pake setter dari class tugaskelompok
                            ((TKAkademik) tugasLama).setAnggota(anggotaBaru);
                        } else if (tugasLama instanceof TKOrganisasi) {
                            ((TKOrganisasi) tugasLama).setAnggota(anggotaBaru);
                        }
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

    private void inputUbahStatusTugas() {

        tampilkanDaftar();

        ArrayList<Tugas> list = taskManager.tampilkanTugas();

        if (list.isEmpty()) {
            return;
        }

        try {

            System.out.print("\nPilih nomor tugas yang ingin diubah statusnya (0 untuk batal): ");

            int nomor = Integer.parseInt(MissionUtil.getUserInput());

            if (nomor == 0) {
                System.out.println("Perubahan status dibatalkan.");
                return;
            }

            int index = nomor - 1;

            System.out.println("\n=== UBAH STATUS TUGAS ===");
            System.out.println("1. Belum Selesai");
            System.out.println("2. Selesai");
            System.out.print("Pilih status: ");

            String pilihan = MissionUtil.getUserInput();

            switch (pilihan) {

                case "1":
                    taskManager.ubahStatusTugas(index, false);
                    break;

                case "2":
                    taskManager.ubahStatusTugas(index, true);
                    break;

                default:
                    System.out.println("Pilihan status tidak valid!");
                    return;
            }

            System.out.println("\nStatus tugas berhasil diperbarui.");

        } catch (NumberFormatException e) {

            System.out.println("Input harus berupa angka!");

        } catch (DataNotFoundException e) {

            System.out.println(e.getMessage());
        }

        System.out.println("\nTekan ENTER untuk kembali...");
        MissionUtil.getUserInput();
    }

    private void inputHapusTugas() {

        tampilkanDaftar();
        ArrayList<Tugas> list = taskManager.tampilkanTugas();

        if (list.isEmpty()) {
            return;
        }

        try {
            System.out.print("\nPilih nomor tugas yang ingin dihapus (0 untuk batal): ");
            int nomor = Integer.parseInt(MissionUtil.getUserInput());

            if (nomor == 0) {
                System.out.println("Penghapusan dibatalkan.");
                return;
            }

            int index = nomor - 1;

            System.out.print("Yakin ingin menghapus tugas ini? (Y/N): ");
            String konfirmasi = MissionUtil.getUserInput();

            if (!konfirmasi.equalsIgnoreCase("Y")) {
                System.out.println("Penghapusan dibatalkan.");
                return;
            }

            taskManager.hapusTugas(index);

            System.out.println("\nBerhasil! Tugas telah dihapus.");

        } catch (NumberFormatException e) {
            System.out.println("Input harus berupa angka!");
        } catch (DataNotFoundException e) {
            System.out.println(e.getMessage());
        }

    }

    private static void urutkanBerdasarkanDeadline(ArrayList<Tugas> daftarTugas) {

        for (int i = 0; i < daftarTugas.size() - 1; i++) {

            for (int j = 0; j < daftarTugas.size() - i - 1; j++) {

                if (daftarTugas.get(j).getDeadline()
                        .isAfter(daftarTugas.get(j + 1).getDeadline())) {

                    Tugas temp = daftarTugas.get(j);
                    daftarTugas.set(j, daftarTugas.get(j + 1));
                    daftarTugas.set(j + 1, temp);
                }
            }
        }
    }

    public void tampilkanDeadlineTerdekat() {
        System.out.println("+----------------------------------------------------------------------------+");
        System.out.println("|                       DEADLINE TERDEKAT (MENDESAK)                         |");
        System.out.println("+----+---------------------------------+-----------------+-----------------+");
        System.out.println("| No | Judul Tugas                     | Matkul          | Sisa Hari       |");
        System.out.println("+----+---------------------------------+-----------------+-----------------+");

        ArrayList<Tugas> daftarTugas = taskManager.tampilkanTugas();

        if (daftarTugas.isEmpty()) {
            System.out.println("|                      Tidak ada tugas saat ini.                             |");
        } else {
            urutkanBerdasarkanDeadline(daftarTugas);
            LocalDate hariIni = LocalDate.now();
            int nomor = 1;
            for (Tugas t : daftarTugas) {
                long sisaHari = ChronoUnit.DAYS.between(hariIni, t.getDeadline());

                // --- LOGIKA MENGAMBIL NAMA MATKUL ---
                String namaMatkul = "-";
                if (t instanceof TIAkademik) {
                    namaMatkul = ((TIAkademik) t).getNamaMataKuliah();
                } else if (t instanceof TKAkademik) {
                    namaMatkul = ((TKAkademik) t).getNamaMataKuliah();
                }
                if (namaMatkul.length() > 15) {
                    namaMatkul = namaMatkul.substring(0, 12) + "...";
                }

                System.out.printf("| %-2d | %-31s | %-15s | H-%-13d |%n",
                        nomor, t.getJudul(), namaMatkul, sisaHari);
                nomor++;
            }
        }
        System.out.println("+----+---------------------------------+-----------------+-----------------+");
        System.out.println("\nTekan ENTER untuk kembali...");
        MissionUtil.getUserInput();
    }
}
