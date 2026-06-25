/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.method.ayakan.model;
import java.time.LocalDate;

public class TIAkademik extends TugasIndividu {
    private MataKuliah matkul;

    public TIAkademik(MataKuliah matkul, String judul, String deskripsi, boolean status, String priority, LocalDate deadline) {
        super(judul, deskripsi, status, priority, deadline);
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
        return "[Tugas Individu Akademik]\nJudul: " + getJudul() + "\nMatkul: " + nama;
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