/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.method.ayakan.model;

import java.time.LocalDate;
import java.time.temporal.ChronoUnit;

/**
 *
 * @author Nayaka
 */

public class Notif {
     private Tugas tugas;

    public Notif(Tugas tugas){
        this.tugas = tugas;
    }

    public long hariLagi(){
        return ChronoUnit.DAYS.between(
                LocalDate.now(),
                tugas.getDeadline());
    }

    public boolean perluDitampilkan(){

        int batas;

        switch(tugas.getPriority()){
            case "High":
                batas = 7;
                break;

            case "Mid":
                batas = 5;
                break;

            default:
                batas = 3;
        }

        return hariLagi() <= batas && hariLagi() >= 0;
    }

    public String getPesan(){
        return "[" + tugas.getPriority() + "] Deadline tugas " + tugas.getJudul() + " tinggal " + hariLagi() + " hari lagi";
    }
    
}
