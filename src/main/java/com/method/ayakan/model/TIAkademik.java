/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.method.ayakan.model;
import java.time.LocalDate;

public class TIAkademik extends TugasIndividu {
    private MataKuliah namaMataKuliah;

    public TIAkademik(MataKuliah namaMataKuliah, String judul, String deskripsi, boolean status, String priority, LocalDate deadline) {
        super(judul, deskripsi, status, priority, deadline);
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
        return "[Tugas Individu Akademik]\nJudul: " + getJudul() + "\nMatkul: " + nama;
    }

    @Override
    public String getInformasiTambahan() {
        if (namaMataKuliah != null) {
            return namaMataKuliah.getNamaMatkul();
        } else {
            return "-";
        }
    }

    public void setMataKuliah(MataKuliah namaMataKuliah) {
        this.namaMataKuliah = namaMataKuliah;
    }
}