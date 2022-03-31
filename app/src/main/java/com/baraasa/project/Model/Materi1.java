package com.baraasa.project.Model;

public class Materi1 {

    String penulis1;
    String judul1;
    String date1;
    int logo1;

    public Materi1(String penulis1, String judul1, String date1, int logo1) {
        this.penulis1 = penulis1;
        this.judul1 = judul1;
        this.date1 = date1;
        this.logo1 = logo1;
    }

    public String getPenulis1() {
        return penulis1;
    }

    public void setPenulis1(String penulis1) {
        this.penulis1 = penulis1;
    }

    public String getJudul1() {
        return judul1;
    }

    public void setJudul1(String judul1) {
        this.judul1 = judul1;
    }

    public String getDate1() {
        return date1;
    }

    public void setDate1(String date1) {
        this.date1 = date1;
    }

    public int getLogo1() {
        return logo1;
    }

    public void setLogo1(int logo1) {
        this.logo1 = logo1;
    }
}