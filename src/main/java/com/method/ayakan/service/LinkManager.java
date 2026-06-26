package com.method.ayakan.service;

import com.method.ayakan.exception.DataNotFoundException;
import com.method.ayakan.model.Link;
import com.method.ayakan.model.MataKuliah;
import com.method.ayakan.repository.LinkRepository;

public class LinkManager {
    
    private LinkRepository repo;
    private int idCounter = 1;

    public LinkManager(LinkRepository repo) {
        this.repo = repo;
    }

    public void tambah(MataKuliah matkul, String judul, String url) {
        int newId = idCounter++;
        Link linkBaru = new Link(newId, judul, url);
        
        matkul.getDaftarLink().put(newId, linkBaru);
        repo.save(linkBaru);
        
        System.out.println("[ [Sukses] Link '" + judul + "' berhasil ditambahkan ke " + matkul.getNamaMatkul() + " ]");
    }

    public void tampilkanSemua(MataKuliah matkul) {
        System.out.println("\n+-----------------------------------------------------------------+");
        System.out.printf("| %-63s |%n", "DAFTAR LINK: " + matkul.getNamaMatkul().toUpperCase());
        System.out.println("+-----------------------------------------------------------------+");
        System.out.println("| ID | Judul Link           | URL                                 |");
        System.out.println("+----+----------------------+-------------------------------------+");
        
        if (matkul.getDaftarLink().isEmpty()) {
            System.out.println("| Belum ada link                                                  |");
        } else {
            for (Link c : matkul.getDaftarLink().values()) {
                System.out.printf("| %-2d | %-20s | %-35s |%n", c.getId(), c.getJudulLink(), c.getUrl());
            }
        }
        System.out.println("+----+----------------------+-------------------------------------+");
    }

    public void update(MataKuliah matkul, int idLink, String judulBaru, String urlBaru) {
        try {
            if (!matkul.getDaftarLink().containsKey(idLink)) {
                throw new DataNotFoundException("[ [Warning] Link ID " + idLink + " tidak ditemukan di mata kuliah ini. ]");
            }

            Link c = matkul.getDaftarLink().get(idLink);
            c.setJudulLink(judulBaru);
            c.setUrl(urlBaru);
            
            System.out.println("[ [Success] Link berhasil diupdate ]");

        } catch (DataNotFoundException e) {
            System.out.println("[Error] " + e.getMessage());
        }
    }

    public void hapus(MataKuliah matkul, int idLink) {
        try {
            if (!matkul.getDaftarLink().containsKey(idLink)) {
                throw new DataNotFoundException("Link ID " + idLink + " tidak ditemukan.");
            }
            matkul.getDaftarLink().remove(idLink);
            repo.delete(idLink);
            
            System.out.println("[ [Success] Link berhasil dihapus! ]");

        } catch (DataNotFoundException e) {
            System.out.println("[Error] " + e.getMessage());
        }
    }

    public boolean isLinkKosong(MataKuliah matkul) {
        return matkul.getDaftarLink().isEmpty();
    }
}