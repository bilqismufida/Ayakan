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
        haltugas = new HalamanTugas(taskManager, mkManager);
        
        halNotif = new HalamanNotif(taskManager);
 
        taskManager = new TaskManager();

        inisialisasiDataAwal();

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
    // Inisialisasi mata kuliah, catatan, serta link
    private static void inisialisasiDataAwal() {
        //Inisialisasi MatKul
        mkManager.tambah("DPBO");
        mkManager.tambah("Statistika");
        mkManager.tambah("Matematika Diskrit");

        MataKuliah dpbo =
            mkManager.cariMatkulById(1);

        MataKuliah matdis =
            mkManager.cariMatkulById(3);

        // Inisialisasi Catatan

        catatanmanager.tambah(
            matdis,
            "Sifat-sifat Graf",
            "Sirkuit Euler: \n" +
            "a. Graf terhubung\n" +
            "b. Semua simpul berderajat genap\n" +
            "c. Melewati setiap sisi tepat sekali dan kembali ke simpul awal.\n" +
            "\n" +
            "Lintasan Euler:\n" +
            "a. Graf terhubung\n" +
            "b. 2 simpul berderajat ganjil\n" +
            "c. Melewati setiap sisi tepat sekali dan tidak kembali ke simpul awal\n" +
            "\n" +
            "Sirkuit Hamilton:\n" +
            "a. Graf terhubung\n" +
            "b. Melewati setiap simpul tepat sekali dan kembali ke simpul awal\n" +
            "\n" +
            "Lintasan Hamilton:\n" +
            "a. Graf terhubung\n" +
            "b. Melewati setiap simpul tepat sekali dan tidak Kembali ke simpul awal.");

        // Inisialisasi link

            linkmanager.tambah(
                dpbo,
                "Materi Encapsulation",
                "https://www.youtube.com/watch?v=eboNNUADeIc");
            
        // Inisialisasi anggota kelompok    
            ArrayList<String> anggotaA = new ArrayList<>();
            anggotaA.add("Raya");
            anggotaA.add("Trye");
            anggotaA.add("Intan");
            anggotaA.add("Bilqis");
            anggotaA.add("Nayaka");
        
        // Inisialisasi Tugas
            
            taskManager.tambahTugas(new TIAkademik(
            "Statistika", "Laporan Uji Hipotesis", "Mengerjakan laporan", false, "Medium",
            LocalDate.of(2026, 07,01)));
            
            taskManager.tambahTugas(new TKAkademik(
            "DPBO", "Kelompok A", anggotaA,"Tugas Besar DPBO", "Membuat aplikasi task management",
            false, "High", LocalDate.of(2026, 07, 03)));
            
            taskManager.tambahTugas(new TIOrganisasi(
            "Staff Muda HMRPL", "Studi Banding", "Bertatap muka dengan kating",
            false, "Low", LocalDate.of(2026, 06, 29)));
    } 
}
