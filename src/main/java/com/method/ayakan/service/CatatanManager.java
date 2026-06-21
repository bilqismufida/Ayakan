package com.method.ayakan.service;

import com.method.ayakan.exception.DataNotFoundException;
import com.method.ayakan.model.Catatan;
import com.method.ayakan.model.MataKuliah;
import com.method.ayakan.repository.CatatanRepository;

public class CatatanManager {

    private CatatanRepository repo;
    private int idCounter = 1; 
    
    public CatatanManager(CatatanRepository repo) {
        this.repo = repo;
    }

    public void tambah(MataKuliah matkul, String judulCatatan, String isiCatatan) {
        int newId = idCounter++;
        Catatan ctnBaru = new Catatan(newId, judulCatatan, isiCatatan);
        
        matkul.getDaftarCatatan().put(newId, ctnBaru);
        repo.save(ctnBaru);
        System.out.println("[ [Sukses] Catatan '" + judulCatatan + "' berhasil ditambahkan ke " + matkul.getNamaMatkul() + " ]");
    }

    public void tampilkanSemua(MataKuliah matkul) {
        System.out.println("\n+-----------------------------------------------------------------+");
        System.out.printf("| %-63s |%n", "DAFTAR CATATAN: " + matkul.getNamaMatkul().toUpperCase());
        System.out.println("+-----------------------------------------------------------------+");
        System.out.println("| ID | Judul Catatan        | Isi Catatan                         |");
        System.out.println("+----+----------------------+-------------------------------------+");
        
        if (matkul.getDaftarCatatan().isEmpty()) {
            System.out.println("| Belum ada catatan                                               |");
        } else {
            for (Catatan c : matkul.getDaftarCatatan().values()) {
                System.out.printf("| %-2d | %-20s | %-35s |%n", c.getId(), c.getJudulCatatan(), c.getIsiCatatan());
            }
        }
        System.out.println("+----+----------------------+-------------------------------------+");
    }

    public void update(MataKuliah matkul, int idCatatan, String judulBaru, String isiBaru) {
        try {
            if (!matkul.getDaftarCatatan().containsKey(idCatatan)) {
                throw new DataNotFoundException("Catatan ID " + idCatatan + " tidak ditemukan di mata kuliah ini.");
            }
            Catatan c = matkul.getDaftarCatatan().get(idCatatan);
            c.setJudulCatatan(judulBaru);
            c.setIsiCatatan(isiBaru);
            System.out.println("[Sukses] Catatan berhasil diupdate!");

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
            repo.delete(idCatatan);
            System.out.println("[Sukses] Catatan berhasil dihapus!");

        } catch (DataNotFoundException e) {
            System.out.println("[Error] " + e.getMessage());
        }
    }

    public boolean isCatatanKosong(MataKuliah matkul) {
        return matkul.getDaftarCatatan().isEmpty();
    }
}