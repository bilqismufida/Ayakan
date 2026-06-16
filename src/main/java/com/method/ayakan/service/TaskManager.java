/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.method.ayakan.service;

import java.util.ArrayList;
import com.method.ayakan.model.Tugas;

/**
 *
 * @author UserID
 */
public class TaskManager {
    private ArrayList<Tugas> daftarTugas = new ArrayList<>();
    
    public void tambahTugas(Tugas t) { 
        daftarTugas.add(t); 
    }

    public void editTugas(int index, Tugas t) {
        if (index >= 0 && index < daftarTugas.size()) daftarTugas.set(index, t);
    }

    public void hapusTugas(int index) {
        if (index >= 0 && index < daftarTugas.size()) {
            daftarTugas.remove(index);
        }
    }

    public ArrayList<Tugas> tampilkanTugas() { 
        return daftarTugas; 
    }
}
