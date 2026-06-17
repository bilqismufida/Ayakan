package com.method.ayakan.service;

import com.method.ayakan.exception.DataNotFoundException;
import com.method.ayakan.model.Catatan;
import com.method.ayakan.model.MataKuliah;
import com.method.ayakan.repository.CatatanRepository;
import com.method.ayakan.ui.UITerminal;


public class CatatanManager {

    private CatatanRepository repo;
    private static UITerminal cover;

    public CatatanManager(CatatanRepository repo) {
        this.repo = repo;
    }

    public void tambah(MataKuliah matkul, int newId, String judulCatatan, String isiCatatan) {
        Catatan ctnBaru = new Catatan(newId, judulCatatan, isiCatatan);
        
        matkul.getDaftarCatatan().put(newId, ctnBaru);
        repo.save(ctnBaru);
        System.out.println("Catatan '" + judulCatatan + "' ditambahkan ke " + matkul.getNamaMatkul());
    }

    public void tampilkanSemua(MataKuliah matkul) {
        System.out.println("\n----- CATATAN: " + matkul.getNamaMatkul() + " -----");
        if (matkul.getDaftarCatatan().isEmpty()) {
            System.out.println("Catatan kosong.");
        } else {
            for (Catatan c : matkul.getDaftarCatatan().values()) {
            c.getInfo();
            }
        }
        cover.tableT();
    }

    public void update(MataKuliah matkul, int idCatatan, String judulBaru, String isiBaru) {
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

    public void hapus(MataKuliah matkul, int idCatatan) {
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
