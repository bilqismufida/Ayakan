package com.method.ayakan.ui;

public class UITerminal {
    public String headH2 = "\n----- * --- * --- * -----"; 
    public String tailH2 = ""; 
    
    
    public static void title() {
        System.out.println("\n===== * === * === * =====");
        System.out.println("   SISTEM MANAJEMEN TUGAS  ");
        System.out.println("===== * ========= * =====");
    }
    
    public static void h1(String title) {
        System.out.println("\n----- * --- * --- * -----");
        System.out.print("\tMenu "+title);
        System.out.println("\n----- * --------- * -----");
    }
    
    public static void tableH(String title) {
        System.out.println("\n===== Daftar " + title + "=====");
    }
    public static void tableT() {
        System.out.println("\n==============================");
    }
    
    
}
