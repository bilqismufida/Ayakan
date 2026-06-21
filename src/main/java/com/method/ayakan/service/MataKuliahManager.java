package com.method.ayakan.service;

import com.method.ayakan.exception.DataNotFoundException;
import com.method.ayakan.model.MataKuliah;
import com.method.ayakan.repository.MataKuliahRepository;

public class MataKuliahManager {

    private MataKuliahRepository repo;
    private int idCounter = 1;

    public MataKuliahManager(MataKuliahRepository repo) {
        this.repo = repo;
    }

    public void tambah(String namaMatkul) {
        int idOtomatis = idCounter++;
        MataKuliah mk = new MataKuliah(idOtomatis, namaMatkul);
        repo.save(mk);
        System.out.println("[ [Sukses] Mata Kuliah '" + namaMatkul + "' berhasil ditambahkan dengan ID " + idOtomatis + " ]");
    }

    public void tampilkanSemua() {
        // Sesuaikan garis pembatas agar cukup lebar untuk semua kolom
        System.out.println("\n+----+------------------------------+-------------+----------------+");
        System.out.printf("| %-62s |%n", "DAFTAR MATA KULIAH");
        System.out.println("+----+------------------------------+-------------+----------------+");
        System.out.println("| ID | Mata Kuliah                  | Jml Catatan | Jml Link       |");
        System.out.println("+----+------------------------------+-------------+----------------+");

        if (repo.isEmpty()) {
            System.out.println("| Belum ada mata kuliah yang terdaftar                           |");
        } else {
            for (MataKuliah mk : repo.findAll().values()) {
                int jmlCatatan = mk.getDaftarCatatan().size();
                int jmlLink = mk.getDaftarLink().size();

                System.out.printf("| %-2d | %-28s | %-11d | %-14d |%n",
                        mk.getId(), mk.getNamaMatkul(), jmlCatatan, jmlLink);
            }
        }
        System.out.println("+----+------------------------------+-------------+----------------+");
    }

    public void update(int idTarget, String namaMatkulBaru) {
        MataKuliah mk = repo.findById(idTarget);
        if (mk != null) {
            mk.setNamaMatkul(namaMatkulBaru);
            System.out.println("[ [Sukses] Mata Kuliah berhasil diupdate ]");
        }
    }

    public void hapus(int idTarget) {
        try {
            if (!repo.check(idTarget)) {
                throw new DataNotFoundException("Mata Kuliah ID " + idTarget + " tidak ditemukan");
            }
            repo.delete(idTarget);
            System.out.println("[ [Sukses] Mata Kuliah berhasil dihapus ]");
        } catch (DataNotFoundException e) {
            System.out.println("[Error] " + e.getMessage());
        }
    }

    public MataKuliah cariMatkulById(int idTarget) {
        return repo.findById(idTarget);
    }

    public boolean isEmpty() {
        return repo.isEmpty();
    }
}
