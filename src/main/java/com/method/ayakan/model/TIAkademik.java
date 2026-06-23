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
public class TIAkademik extends TugasIndividu {
    private String namaMataKuliah;

    public TIAkademik(String namaMataKuliah, String judul, String deskripsi, boolean status, String priority, LocalDate deadline) {
        super(judul, deskripsi, status, priority, deadline);
        this.namaMataKuliah = namaMataKuliah; 
    }

    public String getNamaMataKuliah() {
        return namaMataKuliah;
    }

    public void setNamaMataKuliah(String namaMataKuliah) {
        this.namaMataKuliah = namaMataKuliah;
    }
    
    @Override
    public void markCompleted() {
        super.markCompleted(); 
    }

    @Override
    public void markIncompleted() {
        super.markIncompleted(); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/OverriddenMethodBody
    }
    
    @Override
    public String getInfo() {
        return super.getInfo() + "\nMata Kuliah: " + getNamaMataKuliah();
    }
}
