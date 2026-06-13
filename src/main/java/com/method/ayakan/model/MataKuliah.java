package com.method.ayakan.model;

public class MataKuliah {
    private final int id;
    private String namaMatkul;

    public MataKuliah(int id, String namaMatkul) {
        this.id = id;
        this.namaMatkul = namaMatkul;
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

    @Override
    public String toString() {
        return id + " - " + namaMatkul;
    }
}
