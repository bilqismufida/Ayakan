package com.method.ayakan.model;

import java.util.HashMap;

public class MataKuliah {

    private final int id;
    private String namaMatkul;

    private HashMap<Integer, Catatan> daftarCatatan;
    private HashMap<Integer, Link> daftarLink;
    private HashMap<Integer, Flashcard> daftarFlashcard;

    public MataKuliah(int id, String namaMatkul) {
        this.id = id;
        this.namaMatkul = namaMatkul;
        this.daftarCatatan = new HashMap<>();
        this.daftarLink = new HashMap<>();
        this.daftarFlashcard = new HashMap<>();
    }

    public int getId() {
        return id;
    }

    public String getNamaMatkul() {
        return namaMatkul;
    }

    public void setNamaMatkul(String namaMatkul) {
        this.namaMatkul = namaMatkul;
    }

    public HashMap<Integer, Catatan> getDaftarCatatan() {
        return daftarCatatan;
    }

    public HashMap<Integer, Link> getDaftarLink() {
        return daftarLink;
    }

    public HashMap<Integer, Flashcard> getDaftarFlashcard() {
        return daftarFlashcard;
    }

    @Override
    public String toString() {
        return id + " - " + namaMatkul;
    }
}
