/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.method.ayakan.ui.page;

import java.time.LocalDate;
import java.util.ArrayList;
import com.method.ayakan.service.TaskManager;
import com.method.ayakan.model.Tugas;
import com.method.ayakan.service.MataKuliahManager;
import java.time.temporal.ChronoUnit;
import com.method.ayakan.ui.MissionUtil;

public class HalamanTugas {

    private final TaskManager taskManager;
    private final MataKuliahManager mkManager;
    private final HalamanInputTugas halamanInputTugas; // Menggunakan nama kelas baru

    public HalamanTugas(TaskManager taskManager, MataKuliahManager mkManager) {
        this.taskManager = taskManager;
        this.mkManager = mkManager;
        this.halamanInputTugas = new HalamanInputTugas(taskManager, mkManager, this);
    }

    public void tampilkanMenuTugas() {
        boolean isRunning = true;
        while (isRunning) {
            System.out.println("\n+==============================================+");
            System.out.println("|                HALAMAN TUGAS                 |");
            System.out.println("+==============================================+");
            System.out.println("|  [1] Tampilkan Semua Tugas                   |");
            System.out.println("|  [2] Deadline Terdekat                       |");
            System.out.println("|  [3] Tampilkan Tugas Kelompok                |"); 
            System.out.println("|  [4] Tambah Tugas Baru                       |");
            System.out.println("|  [5] Edit Tugas                              |");
            System.out.println("|  [6] Ubah Status Tugas                       |");
            System.out.println("|  [7] Hapus Tugas                             |");
            System.out.println("+----------------------------------------------+");
            System.out.println("|  [0] Kembali ke Main Menu                    |");
            System.out.println("+==============================================+");
            System.out.print("Pilih Nomor(0-7) : ");

            String pilihan = MissionUtil.getUserInput();

            switch (pilihan) {
                case "1": tampilkanDaftar(); break;
                case "2": tampilkanDeadlineTerdekat(); break;
                case "3": tampilkanDaftarTugasKelompok(); break;
                case "4": halamanInputTugas.inputTambahTugas(); break;
                case "5": halamanInputTugas.inputEditTugas(); break;
                case "6": halamanInputTugas.inputUbahStatusTugas(); break;
                case "7": halamanInputTugas.inputHapusTugas(); break;
                case "0":
                    System.out.println("Keluar dari Halaman Tugas...");
                    isRunning = false;
                    break;
                default:
                    System.out.println("Pilihan tidak valid!");
            }
        }
    }

    public void tampilkanDaftar() {
        System.out.println("\n+===================================================================================================================+");
        System.out.println("|                                               DAFTAR TUGAS                                                        |");
        System.out.println("+----+-------------------------+-----------------+----------+--------------+--------------+-------------------+");
        System.out.println("| No | Judul                   | Matkul/Org      | Prioritas| Deadline     | Status       | Sisa Hari         |");
        System.out.println("+----+-------------------------+-----------------+----------+--------------+--------------+-------------------+");

        ArrayList<Tugas> list = taskManager.tampilkanTugas();

        if (list.isEmpty()) {
            System.out.println("|                                     BELUM ADA TUGAS SAAT INI                                              |");
        } else {
            LocalDate hariIni = LocalDate.now();

            for (int i = 0; i < list.size(); i++) {
                Tugas t = list.get(i);
                long sisaHari = ChronoUnit.DAYS.between(hariIni, t.getDeadline());
                String status = t.getStatus() ? "Selesai" : "Belum";

                String namaMatkul = t.getInformasiTambahan();
                if (namaMatkul.length() > 15) {
                    namaMatkul = namaMatkul.substring(0, 12) + "...";
                }

                System.out.printf("| %-2d | %-23s | %-15s | %-8s | %-12s | %-12s | H-%-15d |%n",
                        i + 1, t.getJudul(), namaMatkul, t.getPriority(), t.getDeadline(), status, sisaHari);
            }
        }
        System.out.println("+----+-------------------------+-----------------+----------+--------------+--------------+-------------------+");
    }

    public void tampilkanDeadlineTerdekat() {
        System.out.println("+----------------------------------------------------------------------------+");
        System.out.println("|                       DEADLINE TERDEKAT (MENDESAK)                         |");
        System.out.println("+----+---------------------------------+-----------------+-----------------+");
        System.out.println("| No | Judul Tugas                     | Matkul/Org      | Sisa Hari       |");
        System.out.println("+----+---------------------------------+-----------------+-----------------+");

        ArrayList<Tugas> daftarTugasUrut = taskManager.getTugasUrutBerdasarkanDeadline();

        if (daftarTugasUrut.isEmpty()) {
            System.out.println("|                     Tidak ada tugas saat ini.                              |");
        } else {
            LocalDate hariIni = LocalDate.now();
            int nomor = 1;
            for (Tugas t : daftarTugasUrut) {
                long sisaHari = ChronoUnit.DAYS.between(hariIni, t.getDeadline());

                String namaMatkul = t.getInformasiTambahan();
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

    private void tampilkanDaftarTugasKelompok() {
        System.out.println("\n=== DAFTAR TUGAS KELOMPOK ===");
        ArrayList<Tugas> listKelompok = taskManager.getDaftarTugasKelompok();

        if (listKelompok.isEmpty()) {
            System.out.println("Belum ada tugas kelompok saat ini.");
        } else {
            int nomor = 1;
            for (Tugas t : listKelompok) {
                System.out.println(nomor + ". " + t.getInfo());
                System.out.println();
                nomor++;
            }
        }
        System.out.println("Tekan ENTER untuk kembali...");
        MissionUtil.getUserInput();
    }
}