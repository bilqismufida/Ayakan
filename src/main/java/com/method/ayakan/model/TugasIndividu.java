/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.method.ayakan.model;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

/**
 *
 * @author TRYE TINTIAN RUNGA
 */
import java.time.LocalDate;
public class TugasIndividu extends Tugas {
    private static final DateTimeFormatter fmt = DateTimeFormatter.ofPattern("dd-MM-yyy");
    
    public TugasIndividu(String judul, String deskripsi, boolean status, String priority, LocalDate deadline){
      super(judul, deskripsi, status, priority, deadline);  
    }
    
    @Override
    public String getInfo(){
        return "[Tugas Individu]" +
                "\nJudul     : " + getJudul() +
                "\nDeskripsi: " + getDeskripsi() +
                "\nPrioritas : " + getPriority() +
                "\nDeadline  : " + getDeadline() +
                "\nStatus    : " + getStatusString();
    }
}
