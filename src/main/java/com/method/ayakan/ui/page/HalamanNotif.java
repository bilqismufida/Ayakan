package com.method.ayakan.ui.page;

import com.method.ayakan.model.Notif;
import com.method.ayakan.model.Tugas;
import com.method.ayakan.service.TaskManager;
import com.method.ayakan.ui.MissionUtil;

import java.util.ArrayList;

public class HalamanNotif {

    private TaskManager taskManager;

    public HalamanNotif(TaskManager taskManager) {
        this.taskManager = taskManager;
    }

    public void tampilkanMenuNotif() {

        tampilkanByPrioritas();

        System.out.println();
        System.out.println("0. Kembali");
        System.out.print("Pilihan : ");

        MissionUtil.getUserInput();
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

    private void tampilkanByPrioritas() {

        ArrayList<Notif> daftar = getDaftarNotif();

        daftar.sort((n1, n2) -> {
            int p1 = getPriorityValue(n1.getTugas().getPriority());

            int p2 = getPriorityValue(n2.getTugas().getPriority());

            return Integer.compare(p1, p2);
        });

        tampilkanTabelNotif(daftar);
    }

    private int getPriorityValue(String priority) {

        switch (priority.toLowerCase()) {

            case "high":
                return 1;

            case "medium":
                return 2;

            case "low":
                return 3;

            default:
                return 4;
        }
    }

    private void tampilkanTabelNotif(ArrayList<Notif> daftar) {

        System.out.println();
        System.out.println("+------------------------------------------------------------------+");
        System.out.println("|                           NOTIFIKASI                             |");
        System.out.println("+------------------------------------------------------------------+");

        if (daftar.isEmpty()) {

            System.out.printf("| %-64s |\n", "Tidak ada notifikasi.");

            System.out.println("+------------------------------------------------------------------+");
            return;
        }

        int no = 1;

        for (Notif notif : daftar) {

            Tugas tugas = notif.getTugas();

            String isiNotif;

            if (notif.getSisaHari() < 0) {

                isiNotif = no++ + ") " + "[" + tugas.getPriority().toUpperCase() + "] " + "[SUDAH LEWAT] " + tugas.getJudul() 
                        + " deadline telah terlewati " + Math.abs(notif.getSisaHari()) + " hari!!! " + notif.getPesan();

            } else {

                isiNotif = no++ + ") " + "[" + tugas.getPriority().toUpperCase() + "] " + tugas.getJudul() + " tinggal sisa "
                        + notif.getSisaHari() + " hari!!! " + notif.getPesan();
            }

            printDalamTabel(isiNotif);

            System.out.printf("| %-64s |\n", "");
        }

        System.out.println("+------------------------------------------------------------------+");
    }

    private void printDalamTabel(String text) {

        int lebar = 64;

        while (text.length() > lebar) {

            int batas = text.lastIndexOf(" ", lebar);

            if (batas <= 0) {
                batas = lebar;
            }
            text = text.substring(batas).trim();
            
            System.out.printf("| %-64s |\n", text.substring(0, batas));
        }

        System.out.printf("| %-64s |\n", text);
    }
}