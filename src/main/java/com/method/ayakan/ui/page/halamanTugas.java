/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.method.ayakan.ui.page;
import java.util.Scanner;
import java.time.LocalDate;                      
import java.time.format.DateTimeParseException;    
import java.util.ArrayList;
import com.method.ayakan.service.TaskManager;
import com.method.ayakan.model.*;
import com.method.ayakan.exception.DataNotFoundException;
import java.time.temporal.ChronoUnit;

public class halamanTugas {
    private TaskManager taskManager = new TaskManager();
    private Scanner scanner = new Scanner(System.in);
    
    // JANGAN DI HAPUS
    public halamanTugas(TaskManager taskManager) {
    this.taskManager = taskManager; 
    }
    public void tampilkanMenuTugas() {
        while (true) {
            System.out.println("\n--- HALAMAN TUGAS ---");
            System.out.println("1. Tampilkan Semua Tugas");
            System.out.println("2. Tampilkan Deadline Terdekat");
            System.out.println("3. Tambah Tugas Baru");
            System.out.println("4. Edit Tugas");
            System.out.println("5. Hapus Tugas");
            System.out.println("0. Kembali ke Main Menu");
            System.out.print("Pilih aksi (0-5): ");
            
            String pilihan = scanner.nextLine();
            
            if (pilihan.equals("1")) {
                tampilkanDaftar();
            } else if (pilihan.equals("2")){
                tampilkanDeadlineTerdekat();
            }else if (pilihan.equals("3")) {
                inputTambahTugas();
            } else if (pilihan.equals("4")) {
                inputEditTugas();
            } else if (pilihan.equals("5")) {
                inputHapusTugas();
            } else if (pilihan.equals("0")) {
                System.out.println("Keluar dari Halaman Tugas...");
                break; 
            } else {
                System.out.println("Pilihan tidak valid!");
            }
        }
    }
    
    private void tampilkanDaftar() {
        System.out.println("\n=== DAFTAR TUGAS ===");
        ArrayList<Tugas> list = taskManager.tampilkanTugas();
        
        if (list.isEmpty()) {
            System.out.println("[Kosong] Belum ada tugas saat ini.");
            return;
        }

        for (int i = 0; i < list.size(); i++) {
            Tugas t = list.get(i);
            System.out.println("\nNo. " + (i + 1));
            System.out.println(t.getInfo());
            System.out.println("-----------------------------------");
        }
    }

    private void inputTambahTugas() {
       System.out.println("\n=== TAMBAH TUGAS NEW ===");
        System.out.print("Judul Tugas: ");
        String judul = scanner.nextLine();
        System.out.print("Deskripsi Tugas: ");
        String deskripsi = scanner.nextLine();
        System.out.print("Prioritas (Low/Medium/High): ");
        String priority = scanner.nextLine();
        
        LocalDate deadline = null;
        while (deadline == null) {
            System.out.print("Deadline (YYYY-MM-DD), contoh 2026-08-17: ");
            try {
                deadline = LocalDate.parse(scanner.nextLine());
            } catch (DateTimeParseException e) {
                System.out.println("Format tanggal salah! Gunakan format YYYY-MM-DD.");
            }
        }

        boolean statusAwal = false; 

        System.out.println("\nPilih Jenis Tugas:");
        System.out.println("1. Tugas Individu");
        System.out.println("2. Tugas Kelompok");
        System.out.print("Pilihan (1-2): ");
        String pilihanJenis = scanner.nextLine();

        Tugas tugasBaru = null;

        if (pilihanJenis.equals("1")) {
            System.out.println("\nPilih Kategori Tugas Individu:");
            System.out.println("1. Akademik (TIAkademik)");
            System.out.println("2. Organisasi (TIOrganisasi)");
            System.out.print("Pilihan (1-2): ");
            String subPilihan = scanner.nextLine();

            if (subPilihan.equals("1")) {
                System.out.print("Nama Mata Kuliah: ");
                String matkul = scanner.nextLine();
                tugasBaru = new TIAkademik(matkul, judul, deskripsi, statusAwal, priority, deadline);
            } else if (subPilihan.equals("2")) {
                System.out.print("Nama Organisasi: ");
                String organisasi = scanner.nextLine();
                tugasBaru = new TIOrganisasi(organisasi, judul, deskripsi, statusAwal, priority, deadline);
            }

        } else if (pilihanJenis.equals("2")) {
            System.out.print("Nama Kelompok: ");
            String namaKel = scanner.nextLine();
            
            ArrayList<String> anggota = new ArrayList<>();
            System.out.print("Masukkan jumlah anggota kelompok: ");
            try {
                int jmlAnggota = Integer.parseInt(scanner.nextLine());
                for (int i = 0; i < jmlAnggota; i++) {
                    System.out.print("Nama Anggota ke-" + (i + 1) + ": ");
                    anggota.add(scanner.nextLine());
                }
            } catch (NumberFormatException e) {
                System.out.println("Input jumlah harus berupa angka! Anggota dikosongkan dahulu.");
            }

            System.out.println("\nPilih Kategori Tugas Kelompok:");
            System.out.println("1. Akademik (TKAkademik)");
            System.out.println("2. Organisasi (TKOrganisasi)");
            System.out.print("Pilihan (1-2): ");
            String subPilihan = scanner.nextLine();

            if (subPilihan.equals("1")) {
                System.out.print("Nama Mata Kuliah: ");
                String matkul = scanner.nextLine();
                tugasBaru = new TKAkademik(matkul, namaKel, anggota, judul, deskripsi, statusAwal, priority, deadline);
            } else if (subPilihan.equals("2")) {
                System.out.print("Nama Organisasi: ");
                String organisasi = scanner.nextLine();
                tugasBaru = new TKOrganisasi(organisasi, namaKel, anggota, judul, deskripsi, statusAwal, priority, deadline);
            }
        }

        if (tugasBaru == null) {
            System.out.println("Gagal membuat tugas. Pilihan menu kategori tidak valid!");
            return;
        }

        taskManager.tambahTugas(tugasBaru);
        System.out.println("Berhasil! Tugas baru telah ditambahkan ke sistem.");
    }
    
    private void inputEditTugas() {
        tampilkanDaftar();
        ArrayList<Tugas> list = taskManager.tampilkanTugas();
        if (list.isEmpty()) return;

        System.out.print("Pilih nomor tugas yang ingin di-edit: ");
        try {
            int nomor = Integer.parseInt(scanner.nextLine());
            int index = nomor - 1; 

            System.out.print("Masukkan Judul Baru: ");
            String judulBaru = scanner.nextLine();
            System.out.print("Masukkan Deskripsi Baru: ");
            String deskripsiBaru = scanner.nextLine();

            
            taskManager.editTugas(index, judulBaru, deskripsiBaru);
            System.out.println("Berhasil! Tugas telah diperbarui.");

        } catch (NumberFormatException e) {
            System.out.println("Error: Input harus berupa angka nomor urut tugas!");
        } catch (DataNotFoundException e) {
            System.out.println(e.getMessage());
        }
    }

    
    private void inputHapusTugas() {
        tampilkanDaftar();
        ArrayList<Tugas> list = taskManager.tampilkanTugas();
        if (list.isEmpty()) return;

        System.out.print("Pilih nomor tugas yang ingin dihapus: ");
        try {
            int nomor = Integer.parseInt(scanner.nextLine());
            int index = nomor - 1;

            taskManager.hapusTugas(index);
            System.out.println("Berhasil! Tugas telah dihapus.");

        } catch (NumberFormatException e) {
            System.out.println("Error: Input harus berupa angka nomor urut tugas!");
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
        System.out.println("+------------------------------------------------------+");
        System.out.println("|          DEADLINE TERDEKAT (MENDESAK)                |");
        System.out.println("+------------------------------------------------------+");
        System.out.println("| No |  Judul Tugas                    | Sisa Hari     |");
        System.out.println("+----+---------------------------------+---------------+");

        ArrayList<Tugas> daftarTugas = taskManager.tampilkanTugas();

        if (daftarTugas.isEmpty()) {
            System.out.println("|          Tidak ada tugas saat ini.                   |");
        } else {
            urutkanBerdasarkanDeadline(daftarTugas);
            LocalDate hariIni = LocalDate.now();
            int nomor = 1;
            for (Tugas t : daftarTugas) {
                long sisaHari = ChronoUnit.DAYS.between(hariIni, t.getDeadline());
                System.out.printf("| %-2d | %-31s | H-%-12d|%n", nomor, t.getJudul(), sisaHari);
                nomor++;
            }
        }
         System.out.println("+----+---------------------------------+---------------+");
    }
}