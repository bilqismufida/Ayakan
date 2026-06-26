/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.method.ayakan.service;

import java.time.LocalDate;
import java.util.ArrayList;

import com.method.ayakan.exception.DataNotFoundException;
import com.method.ayakan.model.Tugas;

/**
 *
 * @author UserID
 */
public class TaskManager {

    private ArrayList<Tugas> daftarTugas = new ArrayList<>();
    private MataKuliahManager mkManager;

    public TaskManager(MataKuliahManager mkManager) {
        this.mkManager = mkManager;
        this.daftarTugas = new ArrayList<>();

        // inisialisasiTugas();
    }

    public void tambahTugas(Tugas t) {
        daftarTugas.add(t);
    }

    // private void inisialisasiTugas() {

    //     MataKuliah dpbo = mkManager.cariMatkulById(1);
    //     MataKuliah stat = mkManager.cariMatkulById(2);
    //     MataKuliah matdis = mkManager.cariMatkulById(3);

    //     ArrayList<String> anggotaDPBO = new ArrayList<>();
    //     anggotaDPBO.add("Raya");
    //     anggotaDPBO.add("Trye");
    //     anggotaDPBO.add("Aulia");

    //     daftarTugas.add(new TKAkademik(dpbo, "Kelompok A", anggotaDPBO, "Tugas Besar DPBO", "Membuat aplikasi", false, "High", LocalDate.now().plusDays(5)));
    //     daftarTugas.add(new TIAkademik(stat, "Laporan Uji Hipotesis", "Mengerjakan laporan", false, "Medium", LocalDate.now().plusDays(4)));
    //     daftarTugas.add(new TIOrganisasi("Staff Muda HMRPL", "Studi Banding", "Bertatap muka", false, "Medium", LocalDate.now().plusDays(3)));
    //     daftarTugas.add(new TIAkademik(matdis, "Kuis Minggu-13", "Mengerjakan soal", false, "Low", LocalDate.now().plusDays(2)));

    // }

    public void editTugas(int index, String judulBaru, String descBaru, String priorityBaru, LocalDate deadlineBaru) throws DataNotFoundException {
        if (index < 0 || index >= daftarTugas.size()) {
            throw new DataNotFoundException("Tugas tidak ditemukan!");
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

    public ArrayList<Tugas> getTugasUrutBerdasarkanDeadline() {
        ArrayList<Tugas> urut = new ArrayList<>(daftarTugas);

        urut.sort((t1, t2) -> t1.getDeadline().compareTo(t2.getDeadline()));
        return urut;
    }

    public ArrayList<Tugas> getDaftarTugasKelompok() {
        ArrayList<Tugas> hasil = new ArrayList<>();
        for (Tugas t : daftarTugas) {
            if (t.isKelompok()) {
                hasil.add(t);
            }
        }
        return hasil;
    }
}
