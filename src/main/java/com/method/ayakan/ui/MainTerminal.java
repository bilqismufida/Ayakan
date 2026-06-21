package com.method.ayakan.ui;

import java.util.Scanner;

import com.method.ayakan.model.Tugas;
import com.method.ayakan.repository.*;
import com.method.ayakan.service.*;
import com.method.ayakan.ui.page.HalamanMataKuliah;
import com.method.ayakan.ui.page.halamanTugas;

public class MainTerminal {
    private static Scanner scanner = new Scanner(System.in);
    private static MataKuliahManager mkManager;
    private static CatatanManager catatanmanager;
    private static LinkManager linkmanager;
    private static TaskManager taskManager;
    private static halamanTugas haltugas;

    //panggil menu terminal
    private static UITerminal cover;
    private static HalamanMataKuliah mk;
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
        haltugas = new halamanTugas(taskManager, mkManager);

        jalankanAplikasi();
    }

    private static void jalankanAplikasi() {
        boolean isRunning = true;
        while (isRunning) {
            System.out.println("\n+==============================================+");
            System.out.println("|       DIGITAL TASK MANAGEMENT SYSTEM         |");
            System.out.println("+==============================================+");
            System.out.println("|  [1]  Mata Kuliah                            |");
            System.out.println("|  [2]  Tugas                                  |");
            System.out.println("+----------------------------------------------+");
            System.out.println("|  [0]  Keluar Aplikasi                        |");
            System.out.println("+==============================================+");
            System.out.print("  Pilih menu: ");

            String pilihan = MissionUtil.getUserInput();

            switch (pilihan) {
                case "1":
                    HalamanMataKuliah.tampilkanMenu(mkManager);
                    break;
                case "2":
                    haltugas.tampilkanMenuTugas();
                    break;
                case "0":
                    System.out.println("Menyimpan data... Sampai jumpa!");
                    isRunning = false;
                    break;
                default:
                    System.out.println("Pilihan tidak valid. Tekan Enter untuk mencoba lagi.");
                    MissionUtil.getUserInput();
            }
        }
    }
}
