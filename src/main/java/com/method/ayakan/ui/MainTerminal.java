package com.method.ayakan.ui;

import com.method.ayakan.repository.LinkRepository;
import com.method.ayakan.repository.MataKuliahRepository;
import com.method.ayakan.service.CatatanManager;
import com.method.ayakan.service.FlashcardManager;
import com.method.ayakan.service.MataKuliahManager;
import com.method.ayakan.service.LinkManager;
import com.method.ayakan.ui.page.HalamanMataKuliah;
import java.util.Scanner;

public class MainTerminal {
    
    private static Scanner scanner = new Scanner(System.in);
    private static MataKuliahManager mkManager;
    private static CatatanManager catatanmanager;
    private static FlashcardManager flascardmanager;
    private static LinkManager linkmanager;
    
    //panggil menu terminal
    private static MenuTerminal menu;
    private static HalamanMataKuliah mk;
    private static UITerminal cover;
    
    public static void main(String[] args) {
        
        
        MataKuliahRepository repoMk = new MataKuliahRepository();
        mkManager = new MataKuliahManager(repoMk);
        
        LinkRepository repoL = new LinkRepository();
        
        CatatanManager catatanmanager = new CatatanManager();
        FlashcardManager flascardmanager = new FlashcardManager();
        LinkManager linkmanager = new LinkManager(repoL);
        
        jalankanAplikasi();
    }
        
    private static void jalankanAplikasi(){     
        boolean isRunning = true;
        
  
        cover.title();       
        
        while(isRunning){
            System.out.println("1. Mata Kuliah");
            System.out.println("2. Tugas");
            System.out.println("0. Keluar Aplikasi");
            System.out.print("Pilih menu: ");
            
            tampilkanDashboardTugas(); 
            tampilkanMenuUtama();
        
            String pilihan = MissionUtil.getUserInput(); 
            
            switch (pilihan){
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
    private static void tampilkanMenuUtama() {
          
    }
    private static void tampilkanDashboardTugas() {
        
    }
    private static void halamanTugas() {
        
    }
    
}
