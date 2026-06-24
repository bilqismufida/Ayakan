/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.method.ayakan.service;

import java.util.ArrayList;
import java.time.LocalDate;
import com.method.ayakan.model.*;
import com.method.ayakan.exception.DataNotFoundException;
import com.method.ayakan.model.Notif;

/**
 *
 * @author UserID
 */
public class TaskManager {

    private ArrayList<Tugas> daftarTugas = new ArrayList<>();
    
    public TaskManager(){
        daftarTugas = new ArrayList<>();
        
        inisialisasiTugas();
    }

    public void tambahTugas(Tugas t) {
        daftarTugas.add(t);
    }
    
    private void inisialisasiTugas() {
        
        ArrayList<String> anggotaDPBO = new ArrayList<>();

        anggotaDPBO.add("Raya");
        anggotaDPBO.add("Trye");
        anggotaDPBO.add("Aulia");

        daftarTugas.add(
            new TKAkademik(
                "DPBO",
                "Kelompok A",
                anggotaDPBO,
                "Tugas Besar DPBO",
                "Membuat aplikasi Prioritas Tugas",
                false,
                "High",
                LocalDate.now().plusDays(5)));

        daftarTugas.add(
            new TIAkademik(
                "Statistika",
                "Laporan Uji Hipotesis",
                "Mengerjakan laporan sesuai panduan",
                false,
                "Medium",
                LocalDate.now().plusDays(4)));

        daftarTugas.add(
            new TIOrganisasi(
                "Staff Muda HMRPL",
                "Studi Banding",
                "Bertatap hadapan dengan kating tanpa armband",
                false,
                "Medium",
                LocalDate.now().plusDays(3)));

        daftarTugas.add(
            new TIAkademik(
                "Matematika Diskrit",
                "Kuis Minggu-13",
                "Mengerjakan soal kuis",
                false,
                "Low",
                LocalDate.now().plusDays(2)));

        daftarTugas.add(
            new TIAkademik(
                "DPBO",
                "Kuis Minggu-10",
                "Mengerjakan soal kuis",
                false,
                "Low",
                LocalDate.now().plusDays(1)));
    }

    public void editTugas(int index, String judulBaru, String descBaru, String priorityBaru, LocalDate deadlineBaru) throws DataNotFoundException {
        if (index < 0 || index >= daftarTugas.size()) {
            throw new DataNotFoundException("Gagal Edit: Tugas nomor " + (index + 1) + " tidak ditemukan!");
        }

        Tugas t = daftarTugas.get(index);
        
        t.setJudul(judulBaru);
        t.setDeskripsi(descBaru);
        t.setPriority(priorityBaru);
        t.setDeadline(deadlineBaru);
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

    public void ubahStatusTugas(int index, boolean statusBaru) throws DataNotFoundException {
        if (index < 0 || index >= daftarTugas.size()) {
            throw new DataNotFoundException(
                    "Gagal Mengubah Status: Tugas nomor "
                    + (index + 1)
                    + " tidak ditemukan!");
        }

        Tugas tugas = daftarTugas.get(index);

        if (statusBaru) {
            tugas.markCompleted();
        } else {
            tugas.markIncompleted();
        }
    }
}
