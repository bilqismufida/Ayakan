package com.method.ayakan.service;

import com.method.ayakan.exception.DataNotFoundException;
import com.method.ayakan.model.MataKuliah;
import com.method.ayakan.repository.MataKuliahRepository;
import java.util.List;

public class MataKuliahManager {

    private MataKuliahRepository repo;

    public MataKuliahManager(MataKuliahRepository repo) {
        this.repo = repo;
    }

    public void tambah(int id, String namaMatkul) {
        MataKuliah mk = new MataKuliah(id, namaMatkul);
        repo.save(mk);
        System.out.println("Mata Kuliah " + namaMatkul + " berhasil ditambahkan.");
    }

    public void tampilkanSemua() {
        System.out.println("\n===== DAFTAR MATA KULIAH =====");
        if (repo.findAll().isEmpty()) {
            System.out.println("Belum ada mata kuliah");
            return;
        }
        for (MataKuliah mk : repo.findAll().values()) {
            System.out.println(mk.toString());
        }
        System.out.println("\n==============================");

    }

    public void update(int idTarget, String namaMatkulBaru) {
        try {
            if (!repo.check(idTarget)) {
                throw new DataNotFoundException("Mata Kuliah dengan ID " + idTarget + " tidak ditemukan");
            }
            MataKuliah mk = repo.findById(idTarget);
            mk.setNamaMatkul(namaMatkulBaru);
            System.out.println("Mata Kuliah ID " + idTarget + " berhasil diupdate");
        } catch (DataNotFoundException e) {
            System.out.println("[Error] " + e.getMessage());
        }
    }

    public void hapus(int idTarget) {
        try {
            if (!repo.check(idTarget)) {
                throw new DataNotFoundException("Mata Kuliah dengan ID " + idTarget + " tidak ditemukan");
            }
            repo.delete(idTarget);
            System.out.println("Mata Kuliah ID " + idTarget + " berhasil dihapus");
        } catch (DataNotFoundException e) {
            System.out.println("[Error] " + e.getMessage());
        }
    }

    public MataKuliah cariMatkulById(int idTarget) {
        return repo.findById(idTarget);
    }
}
