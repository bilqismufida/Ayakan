package com.method.ayakan.model;

public class Flashcard {
    int id;
    String istilah;
    String definisi;

    public Flashcard(int id, String istilah, String definisi) {
        this.id = id;
        this.istilah = istilah;
        this.definisi = definisi;
    }

    public int getId() {
        return id;
    }
    
    public String getIstilah() {
        return istilah;
    }

    public void setIstilah(String istilah) {
        this.istilah = istilah;
    }

    public String getDefinisi() {
        return definisi;
    }

    public void setDefinisi(String definisi) {
        this.definisi = definisi;
    }
    
    public void getInfo() {
        System.out.println("[" + id +"] " + istilah +" - "+ definisi);
    }

    @Override
    public String toString() {
        return "Flashcard{" + "id=" + id + ", istilah=" + istilah + ", definisi=" + definisi + '}';
    }
    
    
}
