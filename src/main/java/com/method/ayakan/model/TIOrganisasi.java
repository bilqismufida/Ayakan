/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.method.ayakan.model;

/**
 *
 * @author RayaRahma
 */
import java.time.LocalDate;
public class TIOrganisasi extends TugasIndividu {
    private String namaOrg;

    public TIOrganisasi(String namaOrg, String judul, String deskripsi, boolean status, String priority, LocalDate deadline) {
        super(judul, deskripsi, status, priority, deadline);
        this.namaOrg = namaOrg;
    }

    @Override
    public String getInfo() {
        return "[Tugas Individu Organisasi]\nJudul: " + getJudul() + "\nOrganisasi: " + namaOrg;
    }

    @Override
    public String getInformasiTambahan() {
        if (namaOrg != null) {
            return namaOrg;
        } else {
            return "-";
        }
    }
}