/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.method.ayakan.model;

import java.time.LocalDate;

/**
 *
 * @author Nayaka
 */
public class Notif {
    public LocalDate deadline;
    public String pesanNotif;

    public Notif(LocalDate deadline) {
        this.deadline = deadline;
    }
    
    public long hariLagi(){
        return 0; //Belum final
    }

    public LocalDate getDeadline() {
        return deadline;
    }

    public String getPesan() {
        return pesanNotif;
    }
    
    
}
