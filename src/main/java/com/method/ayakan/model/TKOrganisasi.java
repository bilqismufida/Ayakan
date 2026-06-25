/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.method.ayakan.model;

import java.time.LocalDate;
import java.util.ArrayList;


public class TKOrganisasi extends TugasKelompok {
    private String namaOrg;

    public TKOrganisasi(String namaOrg, String namaKel, ArrayList<String> anggota, String judul, String deskripsi, boolean status, String priority, LocalDate deadline) {
        super(namaKel, anggota, judul, deskripsi, status, priority, deadline);
        this.namaOrg = namaOrg;
    }

    @Override
    public String getInfo() {
        return "[Tugas Kelompok Organisasi]\nJudul: " + getJudul() + "\nOrganisasi: " + namaOrg + "\nKelompok: " + getNamaKel();
    }

    @Override
    public String getInformasiTambahan() {
        if (namaOrg != null) {
            return namaOrg;
        } else {
            return "-";
        }
    }

    public void setNamaOrganisasi(String namaOrg) {
        this.namaOrg = namaOrg;
    }
}