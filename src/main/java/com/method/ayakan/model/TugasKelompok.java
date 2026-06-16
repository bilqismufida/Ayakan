/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.method.ayakan.model;

import java.time.LocalDate;
import java.util.ArrayList;

/**
 *
 * @author UserID
 */
public class TugasKelompok extends Tugas {

    private String matakuliah;

    private ArrayList<String> anggota;

    public TugasKelompok(String namaKel, ArrayList<String> anggota, String judul, String deskripsi, boolean status, String priority, LocalDate deadline) {
        super(judul, deskripsi, status, priority, deadline);
//        this.namaKel = namaKel;
        this.anggota = new ArrayList<>(anggota);
    }

    @Override
    public String getInfo() {
        return "====informasi kelompok==="
                + "\nJudul: " + getJudul()
                + "\nDeskripsi: " + getDeskripsi()
                + "\nStatus: " + getStatusString()
                + "\nPriority: " + getPriority()
                + "\nDeadline: " + getDeadline()
                + "\nMata Kuliah: "
                + "\nNama Kelompok: "
                + "\nAnggota: " + getAnggota();
    }

    @Override
    public void markCompleted() {
        super.markCompleted();
    }

//    public String getNamaKel() {
//        return namaKel;
//    }

    public ArrayList<String> getAnggota() {
        return anggota;
    }
}
