package com.method.ayakan.service;

import com.method.ayakan.exception.DataNotFoundException;
import com.method.ayakan.model.Link;
import com.method.ayakan.model.MataKuliah;
import com.method.ayakan.repository.LinkRepository;
import com.method.ayakan.ui.UITerminal;

public class LinkManager {
    
    private LinkRepository repo;
    private static UITerminal cover;

    public LinkManager(LinkRepository repo) {
        this.repo = repo;
    }
    
    public void tambah(MataKuliah matkul, int idLink, String judul, String url) {
        Link linkBaru = new Link(idLink, judul, url);
        
        matkul.getDaftarLink().put(idLink, linkBaru);
        
        repo.save(linkBaru);
        
        System.out.println("[ [Success] Link '" + judul + "' berhasil ditambahkan ke " + matkul.getNamaMatkul());
    }

    public void tampilkanSemua(MataKuliah matkul) {
        System.out.println("\n--- DAFTAR LINK: " + matkul.getNamaMatkul().toUpperCase() + " ---");
        
        if (matkul.getDaftarLink().isEmpty()) {
            System.out.println("Data Link masih kosong.");
        } else {
            for (Link c : matkul.getDaftarLink().values()) {
                c.getInfo();
            }
        }
        cover.tableT();
    }

    public void update(MataKuliah matkul, int idLink, String judulBaru, String urlBaru) {
        try {
            if (!matkul.getDaftarLink().containsKey(idLink)) {
                throw new DataNotFoundException("[ [Warning] Link ID " + idLink + " tidak ditemukan di mata kuliah ini.");
            }

            Link c = matkul.getDaftarLink().get(idLink);
            c.setJudulLink(judulBaru);
            c.setUrl(urlBaru);
            
            
            System.out.println("[ [Success] Link berhasil diupdate");

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
            
            System.out.println("Link berhasil dihapus!");

        } catch (DataNotFoundException e) {
            System.out.println("[Error] " + e.getMessage());
        }
    }
}