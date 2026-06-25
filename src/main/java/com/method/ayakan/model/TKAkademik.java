/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.method.ayakan.model;

import java.time.LocalDate;
import java.util.ArrayList;


public class TKAkademik extends TugasKelompok {
    private MataKuliah matkul;

    public TKAkademik(MataKuliah matkul, String namaKel, ArrayList<String> anggota, String judul, String deskripsi, boolean status, String priority, LocalDate deadline) {
        super(namaKel, anggota, judul, deskripsi, status, priority, deadline);
        this.matkul = matkul;
    }

    @Override
    public String getInfo() {
        String nama;
        if (matkul != null) {
            nama = matkul.getNamaMatkul();
        } else {
            nama = "-";
        }
        return "[Tugas Kelompok Akademik]\nJudul: " + getJudul() + "\nMatkul: " + nama + "\nKelompok: " + getNamaKel();
    }

    @Override
    public String getInformasiTambahan() {
        if (matkul != null) {
            return matkul.getNamaMatkul();
        } else {
            return "-";
        }
    }
}