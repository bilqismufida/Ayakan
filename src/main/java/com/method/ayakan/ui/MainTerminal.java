package com.method.ayakan.ui;

import com.method.ayakan.model.*;
import com.method.ayakan.repository.*;
import com.method.ayakan.service.*;
import com.method.ayakan.ui.page.*;
import java.time.LocalDate;
import java.util.ArrayList;

public class MainTerminal {
    private static MataKuliahManager mkManager;
    private static CatatanManager catatanmanager;
    private static LinkManager linkmanager;
    private static TaskManager taskManager;
    private static HalamanTugas haltugas;
    private static HalamanNotif halNotif;
    
    public static void main(String[] args) {
        // 1. Inisialisasi Repository
        MataKuliahRepository repoMk = new MataKuliahRepository();
        LinkRepository repoL = new LinkRepository();
        CatatanRepository repoCatatan = new CatatanRepository();

        // 2. Inisialisasi Manager
        mkManager = new MataKuliahManager(repoMk);
        linkmanager = new LinkManager(repoL);
        catatanmanager = new CatatanManager(repoCatatan);

        // 3. Tambahkan data Mata Kuliah DULU (Agar bisa dipakai TaskManager)
        mkManager.tambah("DPBO");
        mkManager.tambah("Statistika");
        mkManager.tambah("Matematika Diskrit");

        // 4. Baru inisialisasi TaskManager (Mengirim mkManager agar bisa akses objek Matkul)
        taskManager = new TaskManager(mkManager); 

        // 5. Inisialisasi Halaman UI
        haltugas = new HalamanTugas(taskManager, mkManager);
        halNotif = new HalamanNotif(taskManager);

        jalankanAplikasi();
    }

    private static void jalankanAplikasi() {
        boolean isRunning = true;
        while (isRunning) {
            System.out.println("\n+==============================================+");
            System.out.println("|         DIGITAL TASK MANAGEMENT SYSTEM       |");
            System.out.println("+==============================================+");
            System.out.println("|  [1]  Mata Kuliah                            |");
            System.out.println("|  [2]  Tugas                                  |");
            System.out.println("|  [3]  Notifikasi                             |");
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
                case "3":
                    halNotif.tampilkanMenuNotif();
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