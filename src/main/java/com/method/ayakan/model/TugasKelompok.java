/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.method.ayakan.model;
import java.time.LocalDate;
import java.util.ArrayList;

public abstract class TugasKelompok extends Tugas {
    private String namaKel;
    private ArrayList<String> anggota;

    public TugasKelompok(String namaKel, ArrayList<String> anggota, String judul, String deskripsi, boolean status, String priority, LocalDate deadline) {
        super(judul, deskripsi, status, priority, deadline);
        this.namaKel = namaKel;
        this.anggota = anggota;
    }
    @Override
    public boolean isKelompok() { 
        return true; 
    }
    public String getNamaKel() { 
        return namaKel; 
    }
}