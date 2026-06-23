/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.method.ayakan.model;
import java.time.LocalDate;

public abstract class Tugas implements ITugas{
    private String judul;
    private String deskripsi;
    private boolean status;
    private String priority;
    private LocalDate deadline;

    public Tugas(String judul, String deskripsi, boolean status, String priority, LocalDate deadline) {
        this.judul = judul;
        this.deskripsi = deskripsi;
        this.status = false;
        this.priority = priority;
        this.deadline = deadline;
    } 
    
    public String getJudul(){
        return this.judul;
    }
    
     public String getPriority(){
        return priority;
    }

    public String getDeskripsi() {
        return deskripsi;
    }

    public boolean getStatus() {
        return status;
    }
    
   public String getStatusString() {
        if (status) {
            return "Selesai";
        } else {
            return "Belum Selesai";
        }
    }

    public LocalDate getDeadline() {
        return deadline;
    }

//    setter

    public void setJudul(String judul) {
        this.judul = judul;
    }

    public void setDeskripsi(String deskripsi) {
        this.deskripsi = deskripsi;
    }
  
    

    public void setPriority(String priority) {
        this.priority = priority;
    }

    public void setDeadline(LocalDate deadline) {
        this.deadline = deadline;
    }

    @Override
    public void markIncompleted(){
        this.status = false;
        System.out.println("Tugas '" + judul + "' diubah menjadi belum selesai!");

    }

     
    @Override
    public void markCompleted(){
       this.status = true;
        System.out.println("Tugas '" + judul + "' telah selesai!");
    }
    
    @Override
    public abstract String getInfo(); 
}
