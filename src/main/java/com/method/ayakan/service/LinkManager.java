/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.method.ayakan.service;

import com.method.ayakan.exception.DataNotFoundException;
import com.method.ayakan.model.MataKuliah;
import com.method.ayakan.model.Link;

class LinkManager {
    private int idCounter = 1; //otomatis nambah buat id

    public void tambahLink(MataKuliah matkul, String judul, String url) {
        int newId = idCounter++;
        Link fcBaru = new Link(newId, judul, url);
        matkul.getDaftarLink().put(newId, fcBaru);
        System.out.println("Link '" + judul + "' ditambahkan ke " + matkul.getNamaMatkul());
    }

    public void tampilSemuaLink(MataKuliah matkul) {
        System.out.println("\n----- LINK: " + matkul.getNamaMatkul() + " -----");
        if (matkul.getDaftarLink().isEmpty()) {
            System.out.println("Link kosong.");
        }
        for (Link c : matkul.getDaftarLink().values()) {
            c.getInfo();
        }
    }

    public void updateLink(MataKuliah matkul, int idLink, String judulBaru, String urlBaru) {
        try {
            if (!matkul.getDaftarLink().containsKey(idLink)) {
                throw new DataNotFoundException("Link ID " + idLink + " tidak ditemukan.");
            }
            Link c = matkul.getDaftarLink().get(idLink);
            c.setJudulLink(judulBaru);
            c.setUrl(urlBaru);
            System.out.println("Link berhasil diupdate");

        } catch (DataNotFoundException e) {
            System.out.println("[Error] " + e.getMessage());
        }
    }

    public void hapusLink(MataKuliah matkul, int idLink) {
        try {
            if (!matkul.getDaftarLink().containsKey(idLink)) {
                throw new DataNotFoundException("Link ID " + idLink + " tidak ditemukan.");
            }
            matkul.getDaftarLink().remove(idLink);
            System.out.println("Link berhasil dihapus");

        } catch (DataNotFoundException e) {
            System.out.println("[Error] " + e.getMessage());
        }
    }
}