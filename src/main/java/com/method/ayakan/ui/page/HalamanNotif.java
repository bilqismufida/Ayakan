/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.method.ayakan.ui.page;

import com.method.ayakan.model.Notif;
import com.method.ayakan.model.Tugas;
import com.method.ayakan.service.TaskManager;
import com.method.ayakan.ui.MissionUtil;

import java.util.ArrayList;
import java.util.Comparator;

public class HalamanNotif {

    private TaskManager taskManager;

    public HalamanNotif(TaskManager taskManager) {
        this.taskManager = taskManager;
    }

    public void tampilkanMenuNotif() {

        boolean running = true;

        while (running) {

            tampilkanByDeadline();

            System.out.println();
            System.out.println("--------------------------------");
            System.out.println("1. Urutkan Berdasarkan Prioritas");
            System.out.println("0. Kembali");
            System.out.println("--------------------------------");
            System.out.print("Pilihan : ");

            String pilih = MissionUtil.getUserInput();

            switch (pilih) {

                case "1":
                    tampilkanMenuPrioritas();
                    break;

                case "0":
                    running = false;
                    break;

                default:
                    System.out.println("Pilihan tidak valid!");
            }
        }
    }

    private ArrayList<Notif> getDaftarNotif() {

        ArrayList<Notif> daftarNotif = new ArrayList<>();

        for (Tugas tugas : taskManager.tampilkanTugas()) {

            Notif notif = new Notif(tugas);

            if (notif.perluDitampilkan()) {
                daftarNotif.add(notif);
            }
        }

        return daftarNotif;
    }

    private void tampilkanByDeadline() {

        ArrayList<Notif> daftar = getDaftarNotif();

        daftar.sort(
                Comparator.comparing(
                        n -> n.getTugas().getDeadline()
                )
        );

        System.out.println();
        System.out.println("====================");
        System.out.println("    NOTIFIKASI");
        System.out.println("====================");

        if (daftar.isEmpty()) {
            System.out.println("\nTidak ada notifikasi.");
            return;
        }

        int no = 1;

        for (Notif notif : daftar) {

            Tugas tugas = notif.getTugas();

            System.out.println();
            System.out.println(no++ + ") " + tugas.getJudul());
            System.out.println("   [" + tugas.getDeskripsi() + "]");
            System.out.println("   Priority  : " + tugas.getPriority());
            System.out.println("   Deadline  : " + tugas.getDeadline());
            System.out.println("   Sisa Hari : " + notif.getSisaHari() + " hari");
        }
    }

    private void tampilkanMenuPrioritas() {

        boolean running = true;

        while (running) {

            tampilkanByPrioritas();

            System.out.println();
            System.out.println("--------------------------------");
            System.out.println("1. Kembali ke Urutan Deadline");
            System.out.println("0. Kembali");
            System.out.println("--------------------------------");
            System.out.print("Pilihan : ");

            String pilih = MissionUtil.getUserInput();

            switch (pilih) {

                case "1":
                    return;

                case "0":
                    return;

                default:
                    System.out.println("Pilihan tidak valid!");
            }
        }
    }

    private void tampilkanByPrioritas() {

        ArrayList<Notif> daftar = getDaftarNotif();

        System.out.println();
        System.out.println("====================");
        System.out.println("    NOTIFIKASI");
        System.out.println("====================");

        tampilkanKategori("High", daftar);
        tampilkanKategori("Medium", daftar);
        tampilkanKategori("Low", daftar);
    }

    private void tampilkanKategori(
            String priority,
            ArrayList<Notif> daftar) {

        System.out.println();
        System.out.println("===== "
                + priority.toUpperCase()
                + " =====");

        int no = 1;
        boolean ada = false;

        for (Notif notif : daftar) {

            Tugas tugas = notif.getTugas();

            if (tugas.getPriority()
                    .equalsIgnoreCase(priority)) {

                ada = true;

                System.out.println();
                System.out.println(no++ + ") "
                        + tugas.getJudul());

                System.out.println("   ["
                        + tugas.getDeskripsi()
                        + "]");

                System.out.println("   Deadline  : "
                        + tugas.getDeadline());

                System.out.println("   Sisa Hari : "
                        + notif.getSisaHari()
                        + " hari");
            }
        }

        if (!ada) {
            System.out.println("Tidak ada tugas.");
        }
    }
}