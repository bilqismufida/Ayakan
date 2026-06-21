package com.method.ayakan.ui;

import com.method.ayakan.repository.LinkRepository;

import com.method.ayakan.model.*;
import com.method.ayakan.service.*;
import com.method.ayakan.repository.*;
import com.method.ayakan.ui.page.HalamanMataKuliah;
import java.util.Scanner;
import java.time.LocalDate;
import java.time.temporal.ChronoUnit;
import java.util.ArrayList;
import java.util.Comparator;

public class MainTerminal {

    private static Scanner scanner = new Scanner(System.in);
    private static MataKuliahManager mkManager;
    private static CatatanManager catatanmanager;
    private static LinkManager linkmanager;
    private static TaskManager taskManager;

    //panggil menu terminal
    private static HalamanMataKuliah mk;
    private static UITerminal cover;
    private static Iterable<Tugas> daftarTugas;

    public static void main(String[] args) {

//        manggil matkul
        MataKuliahRepository repoMk = new MataKuliahRepository();
        mkManager = new MataKuliahManager(repoMk);

        // manggil link
        LinkRepository repoL = new LinkRepository();
        linkmanager = new LinkManager(repoL);

//        manggil cttn
        CatatanRepository repoCatatan = new CatatanRepository();
        catatanmanager = new CatatanManager(repoCatatan);

        taskManager = new TaskManager();

        jalankanAplikasi();
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

    private static void jalankanAplikasi() {
        boolean isRunning = true;
        cover.title();
        tampilkanDashboardTugas();
        tampilkanMenuUtama();

        while (isRunning) {
            System.out.println("1. Mata Kuliah");
            System.out.println("2. Tugas");
            System.out.println("0. Keluar Aplikasi");
            System.out.print("Pilih menu: ");

            String pilihan = MissionUtil.getUserInput();

            switch (pilihan) {
                case "1":
                    HalamanMataKuliah.halMatkul(mkManager);
                    break;
                case "2":
                    halamanTugas();
                    break;
                case "0":
                    System.out.println("Menyimpan data... Sampai jumpa!");
                    isRunning = false;
                    break;
                default:
                    System.out.println("❌ Pilihan tidak valid. Tekan Enter untuk mencoba lagi.");
                    MissionUtil.getUserInput();
            }
        }
    }

    private static void tampilkanDashboardTugas() {

        ArrayList<Tugas> daftarTugas = taskManager.tampilkanTugas();

        urutkanBerdasarkanDeadline(daftarTugas);

        LocalDate hariIni = LocalDate.now();

        int nomor = 1;

        for (Tugas t : daftarTugas) {

            long sisaHari = ChronoUnit.DAYS.between(
                    hariIni,
                    t.getDeadline()
            );

            System.out.println(
                    nomor + ". "
                    + t.getJudul()
                    + " (H-" + sisaHari + ")"
            );

            nomor++;
        }

        System.out.println("------------------------------------------------------");
    }

    private static void tampilkanMenuUtama() {

    }

    private static void halamanTugas() {

    }

}
