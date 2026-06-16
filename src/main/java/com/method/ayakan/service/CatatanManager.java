package com.method.ayakan.service;

import com.method.ayakan.exception.DataNotFoundException;
import com.method.ayakan.model.Catatan;
import com.method.ayakan.model.MataKuliah;

public class CatatanManager {

    private int idCounter = 1; //otomatis nambah buat id

    public void tambahCatatan(MataKuliah matkul, String judulCatatan, String isiCatatan) {
        int newId = idCounter++;
        Catatan ctnBaru = new Catatan(newId, judulCatatan, isiCatatan);
        matkul.getDaftarCatatan().put(newId, ctnBaru);
        System.out.println("Catatan '" + judulCatatan + "' ditambahkan ke " + matkul.getNamaMatkul());
    }

    public void tampilSemuaCatatan(MataKuliah matkul) {
        System.out.println("\n----- CATATAN: " + matkul.getNamaMatkul() + " -----");
        if (matkul.getDaftarCatatan().isEmpty()) {
            System.out.println("Catatan kosong.");
        }
        for (Catatan c : matkul.getDaftarCatatan().values()) {
            c.getInfo();
        }
    }

    public void updateCatatan(MataKuliah matkul, int idCatatan, String judulBaru, String isiBaru) {
        try {
            if (!matkul.getDaftarCatatan().containsKey(idCatatan)) {
                throw new DataNotFoundException("Catatan ID " + idCatatan + " tidak ditemukan.");
            }
            Catatan c = matkul.getDaftarCatatan().get(idCatatan);
            c.setJudulCatatan(judulBaru);
            c.setIsiCatatan(isiBaru);
            System.out.println("Catatan berhasil diupdate");

        } catch (DataNotFoundException e) {
            System.out.println("[Error] " + e.getMessage());
        }
    }

    public void hapusCatatan(MataKuliah matkul, int idCatatan) {
        try {
            if (!matkul.getDaftarCatatan().containsKey(idCatatan)) {
                throw new DataNotFoundException("Catatan ID " + idCatatan + " tidak ditemukan.");
            }
            matkul.getDaftarCatatan().remove(idCatatan);
            System.out.println("Catatan berhasil dihapus");

        } catch (DataNotFoundException e) {
            System.out.println("[Error] " + e.getMessage());
        }
    }
}
