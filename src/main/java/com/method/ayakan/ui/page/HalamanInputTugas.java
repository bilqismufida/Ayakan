/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.method.ayakan.ui.page;

import com.method.ayakan.model.*;
import com.method.ayakan.service.*;
import com.method.ayakan.ui.MissionUtil;
import com.method.ayakan.exception.DataNotFoundException;
import java.time.LocalDate;
import java.time.format.DateTimeParseException;
import java.util.ArrayList;

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
        System.out.println("\n=== EDIT TUGAS ===");
        halamanTugas.tampilkanDaftar();
        System.out.print("Pilih nomor tugas untuk diedit: ");
        try {
            int index = Integer.parseInt(MissionUtil.getUserInput()) - 1;
            System.out.print("Judul Baru: ");
            String j = MissionUtil.getUserInput();
            System.out.print("Deskripsi Baru: ");
            String d = MissionUtil.getUserInput();
            
            taskManager.editTugas(index, j, d, inputPriority(), inputDeadline());
            System.out.println("Tugas berhasil diedit!");
        } catch (Exception e) {
            System.out.println("Gagal edit: " + e.getMessage());
        }
        System.out.print("\nTekan Enter untuk kembali...");
        MissionUtil.getUserInput();
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
        System.out.println("1. Akademik, 2. Organisasi");
        String sub = MissionUtil.getUserInput();
        if (sub.equals("1")) return new TIAkademik(pilihMatkulTugas(), judul, deskripsi, status, priority, deadline);
        else {
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
        if (sub.equals("1")) return new TKAkademik(pilihMatkulTugas(), namaKel, anggota, judul, deskripsi, status, priority, deadline);
        else {
            System.out.print("Nama Organisasi: ");
            return new TKOrganisasi(MissionUtil.getUserInput(), namaKel, anggota, judul, deskripsi, status, priority, deadline);
        }
    }

    private MataKuliah pilihMatkulTugas() {
        if (mkManager.isEmpty()) return null;
        System.out.println("\n--- Pilih Mata Kuliah ---");
        mkManager.tampilkanSemua();
        System.out.print("Pilih ID: ");
        try {
            int id = Integer.parseInt(MissionUtil.getUserInput());
            return (id == 0) ? null : mkManager.cariMatkulById(id); 
        } catch (Exception e) { return null; }
    }

    private ArrayList<String> inputAnggotaKelompok() {
        ArrayList<String> list = new ArrayList<>();
        System.out.print("Jumlah anggota: ");
        try {
            int jml = Integer.parseInt(MissionUtil.getUserInput());
            for (int i = 0; i < jml; i++) {
                System.out.print("Nama " + (i+1) + ": ");
                list.add(MissionUtil.getUserInput());
            }
        } catch (Exception e) {}
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
            } catch (DateTimeParseException e) { System.out.println("Format salah!"); }
        }
    }
}