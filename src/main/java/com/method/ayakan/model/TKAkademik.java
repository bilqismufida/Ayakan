/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.method.ayakan.model;

import java.time.LocalDate;
import java.util.ArrayList;


public class TKAkademik extends TugasKelompok{
    private String namaMataKuliah;

    public TKAkademik(String namaMataKuliah, String namaKel, ArrayList<String> anggota, String judul, String deskripsi, boolean status, String priority, LocalDate deadline) {
        super(namaKel, anggota, judul, deskripsi, status, priority, deadline);
        this.namaMataKuliah = namaMataKuliah;
    }

    public String getNamaMataKuliah() {
        return namaMataKuliah;
    }

    @Override
    public ArrayList<String> getAnggota() {
        return super.getAnggota(); 
    }

//    @Override
//    public String getNamaKel() {
//        return super.getNamaKel(); 
//    }

    @Override
    public void markCompleted() {
        super.markCompleted(); 
    }

    @Override
    public String getInfo() {
        return super.getInfo()+ "\nMata Kuliah: " + namaMataKuliah;
    }
    
    
}
