/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.method.ayakan.model;
import java.time.LocalDate;

public abstract class TugasIndividu extends Tugas {
    public TugasIndividu(String judul, String deskripsi, boolean status, String priority, LocalDate deadline) {
        super(judul, deskripsi, status, priority, deadline);
    }
    
    @Override
    public boolean isKelompok() { return false; }
}