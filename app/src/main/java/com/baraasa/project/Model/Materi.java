package com.baraasa.project.Model;

public class Materi {

    String penulis;
    String judul;
    String date;
    int logo;

    public Materi(String penulis, String judul, String date, int logo) {
        this.penulis = penulis;
        this.judul = judul;
        this.date = date;
        this.logo = logo;
    }

    public String getPenulis() {
        return penulis;
    }

    public void setPenulis(String penulis) {
        this.penulis = penulis;
    }

    public String getJudul() {
        return judul;
    }

    public void setJudul(String judul) {
        this.judul = judul;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public int getLogo() {
        return logo;
    }

    public void setLogo(int logo) {
        this.logo = logo;
    }


}