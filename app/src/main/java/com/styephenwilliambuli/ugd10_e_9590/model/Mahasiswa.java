package com.styephenwilliambuli.ugd10_e_9590.model;

import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.PrimaryKey;

import java.io.Serializable;

@Entity
public class Mahasiswa implements Serializable {
    @PrimaryKey(autoGenerate = true)
    private int id;

    @ColumnInfo(name = "npm")
    private String npm;

    @ColumnInfo(name = "nama")
    private String nama;

    @ColumnInfo(name = "fakultas")
    private String fakultas;

    @ColumnInfo(name = "jurusan")
    private String jurusan;

    @ColumnInfo(name = "ipk")
    private double ipk;

    @ColumnInfo(name = "urlPhotos")
    private String urlPhotos;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getNpm() {
        return npm;
    }

    public void setNpm(String npm) {
        this.npm = npm;
    }

    public String getNama() {
        return nama;
    }

    public void setNama(String nama) {
        this.nama = nama;
    }

    public String getFakultas() {
        return fakultas;
    }

    public void setFakultas(String fakultas) {
        this.fakultas = fakultas;
    }

    public String getJurusan() {
        return jurusan;
    }

    public void setJurusan(String jurusan) {
        this.jurusan = jurusan;
    }

    public String getStringIpk() {
        return String.valueOf(ipk);
    }

    public double getIpk() {
        return ipk;
    }

    public void setIpk(double ipk) {
        this.ipk = ipk;
    }

    public String getUrlPhotos() {
        return urlPhotos;
    }

    public void setUrlPhotos(String urlPhotos) {
        this.urlPhotos = urlPhotos;
    }
}
