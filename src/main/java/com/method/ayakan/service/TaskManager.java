/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.method.ayakan.service;

import java.util.ArrayList;
import com.method.ayakan.model.Tugas;
import com.method.ayakan.exception.DataNotFoundException;
import com.method.ayakan.model.Notif;
/**
 *
 * @author UserID
 */
public class TaskManager {
    private ArrayList<Tugas> daftarTugas = new ArrayList<>();
    
    public void tambahTugas(Tugas t) { 
        daftarTugas.add(t); 
    }

    public void editTugas(int index, String JudulBaru, String DeskripsiBaru)throws DataNotFoundException {
        if (index < 0 || index >= daftarTugas.size()) {
            throw new DataNotFoundException("Gagal Edit: Tugas nomor " + (index + 1) + " tidak ditemukan!");
        }
        
        Tugas t = daftarTugas.get(index);
        
        
        t.setJudul(JudulBaru);
        t.setDeskripsi(DeskripsiBaru);
    }

    public void hapusTugas(int index) throws DataNotFoundException {
        if (index >= 0 && index < daftarTugas.size()) {
            daftarTugas.remove(index);
        } else {
            throw new DataNotFoundException("Gagal Hapus: Tugas nomor " + (index + 1) + " tidak ditemukan!");
        }
    }

    public ArrayList<Tugas> tampilkanTugas() { 
        return daftarTugas; 
    }
    
    public ArrayList<String> getNotifikasi() {
        ArrayList<String> daftarNotif = new ArrayList<>();

        for (Tugas tugas : daftarTugas) {

            Notif notif = new Notif(tugas);

            if (notif.perluDitampilkan()) {
                daftarNotif.add(notif.getPesan());
            }
        }

        return daftarNotif;
    }
    
}
