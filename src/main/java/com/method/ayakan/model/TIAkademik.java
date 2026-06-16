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
    private String mataKuliah;

    public TIAkademik(String mataKuliah, String judul, String deskripsi, boolean status, String priority, LocalDate deadline) {
        super(judul, deskripsi, status, priority, deadline);
        this.mataKuliah = mataKuliah;
    }

    public String getMataKuliah() {
        return mataKuliah;
    }

    @Override
    public String getInfo() {
        return super.getInfo() + "\nMata Kuliah: " + mataKuliah;
    }
}
