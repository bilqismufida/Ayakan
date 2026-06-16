package com.method.ayakan.service;

import com.method.ayakan.exception.DataNotFoundException;
import com.method.ayakan.model.Flashcard;
import com.method.ayakan.model.MataKuliah;

public class FlashcardManager {
    private int idCounter = 1; //otomatis nambah buat id

    public void tambahFlashcard(MataKuliah matkul, String istilah, String definisi) {
        int newId = idCounter++;
        Flashcard fcBaru = new Flashcard(newId, istilah, definisi);
        matkul.getDaftarFlashcard().put(newId, fcBaru);
        System.out.println("Flashcard '" + istilah + "' ditambahkan ke " + matkul.getNamaMatkul());
    }

    public void tampilSemuaFlashcard(MataKuliah matkul) {
        System.out.println("\n----- FLASHCARD: " + matkul.getNamaMatkul() + " -----");
        if (matkul.getDaftarFlashcard().isEmpty()) {
            System.out.println("Flashcard kosong.");
        }
        for (Flashcard c : matkul.getDaftarFlashcard().values()) {
            c.getInfo();
        }
    }

    public void updateFlashcard(MataKuliah matkul, int idFlashcard, String istilahBaru, String definisiBaru) {
        try {
            if (!matkul.getDaftarFlashcard().containsKey(idFlashcard)) {
                throw new DataNotFoundException("Flashcard ID " + idFlashcard + " tidak ditemukan.");
            }
            Flashcard c = matkul.getDaftarFlashcard().get(idFlashcard);
            c.setIstilah(istilahBaru);
            c.setDefinisi(definisiBaru);
            System.out.println("Flashcard berhasil diupdate");

        } catch (DataNotFoundException e) {
            System.out.println("[Error] " + e.getMessage());
        }
    }

    public void hapusFlashcard(MataKuliah matkul, int idFlashcard) {
        try {
            if (!matkul.getDaftarFlashcard().containsKey(idFlashcard)) {
                throw new DataNotFoundException("Flashcard ID " + idFlashcard + " tidak ditemukan.");
            }
            matkul.getDaftarFlashcard().remove(idFlashcard);
            System.out.println("Flashcard berhasil dihapus");

        } catch (DataNotFoundException e) {
            System.out.println("[Error] " + e.getMessage());
        }
    }
}
