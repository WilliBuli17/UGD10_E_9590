package com.styephenwilliambuli.ugd10_e_9590.model;

public class MhsPerFak {
    private int jumlah;
    private String fakultas;

    public MhsPerFak(int jumlah, String fakultas) {
        this.jumlah = jumlah;
        this.fakultas = fakultas;
    }

    public int getJumlah() {
        return jumlah;
    }

    public void setJumlah(int jumlah) {
        this.jumlah = jumlah;
    }

    public String getFakultas() {
        return fakultas;
    }

    public void setFakultas(String fakultas) {
        this.fakultas = fakultas;
    }
}
