package com.method.ayakan.ui;

import com.method.ayakan.repository.MataKuliahRepository;
import com.method.ayakan.service.MataKuliahManager;
import java.util.Scanner;

public class MainTerminal {
    public static void main(String[] args) {
        MataKuliahRepository repo = new MataKuliahRepository();
        MataKuliahManager mkManager = new MataKuliahManager(repo);
        
        Scanner sc = new Scanner(System.in);
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
            
            int pilih = sc.nextInt();
            sc.nextLine();
            
            switch (pilih){
                case 1:
                    System.out.print("Masukkan ID Matkul: ");
                    int idBaru = sc.nextInt();
                    sc.nextLine();
                    
                    System.out.print("Masukkan Nama Matkul: ");
                    String mkBaru = sc.nextLine();
                    
                    mkManager.tambah(idBaru, mkBaru);
                    break;
                case 2:
                    mkManager.tampilkanSemua();
                    break;
            }
        }
        
        sc.close();
    }
}
