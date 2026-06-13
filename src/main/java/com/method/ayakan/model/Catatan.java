package com.method.ayakan.model;

public class Catatan {
    int id;
    String judulCatatan;

    String isiCatatan;

    public int getId() {
        return id;
    }

    public String getJudulCatatan() {
        return judulCatatan;
    }

    public void setJudulCatatan(String judulCatatan) {
        this.judulCatatan = judulCatatan;
    }

    public String getIsiCatatan() {
        return isiCatatan;
    }

    public void setIsiCatatan(String isiCatatan) {
        this.isiCatatan = isiCatatan;
    }

    public Catatan(int id, String judulCatatan, String isiCatatan) {
        this.id = id;
        this.judulCatatan = judulCatatan;
        this.isiCatatan = isiCatatan;
    }
    
    public void getInfo(){
        System.out.println("["+ id +"]" + judulCatatan + " - " + isiCatatan);
    }

    @Override
    public String toString() {
        return "Catatan{" + "id=" + id + ", judulCatatan=" + judulCatatan + ", isiCatatan=" + isiCatatan + '}';
    }
}
