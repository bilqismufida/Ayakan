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
    private String namaOrganisasi;
    
    public TIOrganisasi(String namaOrganisasi, String judul, String deskripsi, boolean status, String priority, LocalDate deadline) {
        super(judul, deskripsi, status, priority, deadline);
        this.namaOrganisasi = namaOrganisasi;
    }

    public String getNamaOrganisasi() {
        return namaOrganisasi;
    }

    @Override
    public void markIncompleted() {
        super.markIncompleted(); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/OverriddenMethodBody
    }

    @Override
    public void markCompleted() {
        super.markCompleted(); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/OverriddenMethodBody
    }
    
    

    @Override
    public String getInfo() {
        return super.getInfo() + "\nOrganisasi: " + namaOrganisasi;
    }
}
