package com.method.ayakan.ui;

import com.method.ayakan.dummy.DummyDataInitializer;
import com.method.ayakan.repository.CatatanRepository;
import com.method.ayakan.repository.LinkRepository;
import com.method.ayakan.repository.MataKuliahRepository;
import com.method.ayakan.service.CatatanManager;
import com.method.ayakan.service.LinkManager;
import com.method.ayakan.service.MataKuliahManager;
import com.method.ayakan.service.TaskManager;
import com.method.ayakan.ui.page.HalamanMataKuliah;
import com.method.ayakan.ui.page.HalamanNotif;
import com.method.ayakan.ui.page.HalamanTugas;

public class MainTerminal {

    private static MataKuliahManager mkManager;
    private static CatatanManager catatanManager;
    private static LinkManager linkManager;
    private static TaskManager taskManager;
    private static HalamanTugas haltugas;
    private static HalamanNotif halNotif;

    public static void main(String[] args) {
        MataKuliahRepository repoMk = new MataKuliahRepository();
        LinkRepository repoL = new LinkRepository();
        CatatanRepository repoCatatan = new CatatanRepository();

        // Manager
        mkManager = new MataKuliahManager(repoMk);
        linkManager = new LinkManager(repoL);
        catatanManager = new CatatanManager(repoCatatan);
        taskManager = new TaskManager(mkManager);

        DummyDataInitializer.init(
                mkManager,
                taskManager,
                catatanManager,
                linkManager
        );
// UI
        haltugas = new HalamanTugas(taskManager, mkManager);
        halNotif = new HalamanNotif(taskManager);

        jalankanAplikasi();
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
                     HalamanMataKuliah.tampilkanMenu(
            mkManager,
            linkManager,
            catatanManager
    );
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
