/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.method.ayakan.model;

import java.time.LocalDate;
import java.util.ArrayList;


public class TKAkademik extends TugasKelompok {
    private MataKuliah namaMataKuliah;

    public TKAkademik(MataKuliah namaMataKuliah, String namaKel, ArrayList<String> anggota, String judul, String deskripsi, boolean status, String priority, LocalDate deadline) {
        super(namaKel, anggota, judul, deskripsi, status, priority, deadline);
        this.namaMataKuliah = namaMataKuliah;
    }

    @Override
    public String getInfo() {
        String nama;
        if (namaMataKuliah != null) {
            nama = namaMataKuliah.getNamaMatkul();
        } else {
            nama = "-";
        }
        return "[Tugas Kelompok Akademik]\nJudul: " + getJudul() + "\nMatkul: " + nama + "\nKelompok: " + getNamaKel();
    }

    @Override
    public String getInformasiTambahan() {
        if (namaMataKuliah != null) {
            return namaMataKuliah.getNamaMatkul();
        } else {
            return "-";
        }
    }

     public MataKuliah getNamaMataKuliah() {
        return namaMataKuliah;
    }

    public void setMataKuliah(MataKuliah namaMataKuliah) {
        this.namaMataKuliah = namaMataKuliah;
    }
}