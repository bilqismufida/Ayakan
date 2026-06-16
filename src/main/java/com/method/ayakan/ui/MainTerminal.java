package com.method.ayakan.ui;

import com.method.ayakan.repository.MataKuliahRepository;
import com.method.ayakan.service.CatatanManager;
import com.method.ayakan.service.FlashcardManager;
import com.method.ayakan.service.MataKuliahManager;
import com.method.ayakan.service.LinkManager;
import java.util.Scanner;

public class MainTerminal {
    
    private static Scanner scanner = new Scanner(System.in);
    private static MataKuliahManager mkManager;
    private static CatatanManager catatanmanager;
    private static FlashcardManager flascardmanager;
    private static LinkManager linkmanager;
    
    public static void main(String[] args) {
        
        MataKuliahRepository repo = new MataKuliahRepository();
        MataKuliahManager mkManager = new MataKuliahManager(repo);
        CatatanManager catatanmanager = new CatatanManager();
        FlashcardManager flascardmanager = new FlashcardManager();
        LinkManager linkmanager = new LinkManager();
        
        jalankanAplikasi();
    }
        
    private static void jalankanAplikasi(){     
        boolean isRunning = true;
     
        
        System.out.println("===== * ===== *** ===== * =====");
        System.out.println("   SISTEM MANAJEMEN TUGAS      ");
        System.out.println("===== * ===== *** ===== * =====");
        
        while(isRunning){
            System.out.println("\n----- MENU MATA KULIAH -----");
            System.out.println("1. Tambah Mata Kuliah");
            System.out.println("2. Tampilkan Semua Mata Kuliah");
            System.out.println("3. Update Mata Kuliah");
            System.out.println("4. Hapus Mata Kuliah");
            System.out.println("0. Keluar Aplikasi");
            System.out.print("Pilih menu: ");
            
            tampilkanDashboardTugas(); 
            tampilkanMenuUtama();
        
            String pilihan = MissionUtil.getUserInput(); 
            
            
            switch (pilihan){
                case "1":
                    halamanMataKuliah(); 
                    break;
                case "2":
                    halamanTugas(); 
                    break;
                case "3":
                    halamanCatatan();
                    break;
                case "4":
                    halamanFlashcard();
                    break;
                case "5":
                    halamanLink();
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
        
    }
    private static void tampilkanMenuUtama() {
          
    }
    private static void halamanMataKuliah() {
        
    }
    private static void halamanTugas() {
        
    }
    private static void halamanCatatan() {
        
    }
    private static void halamanFlashcard() {
        
    }
    private static void halamanLink() {
        
    }
    
}
