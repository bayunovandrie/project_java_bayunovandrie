package com.example.starlink.model;

import java.time.LocalDateTime;

public class DaftarPeminjam {
    private int id;
    private String namaPeminjam;
    private String judulBuku;
    private LocalDateTime tanggalPinjam;
    private LocalDateTime tanggalKembali;

    // Getters and Setters

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getNamaPeminjam() {
        return namaPeminjam;
    }

    public void setNamaPeminjam(String namaPeminjam) {
        this.namaPeminjam = namaPeminjam;
    }

    public String getJudulBuku() {
        return judulBuku;
    }

    public void setJudulBuku(String judulBuku) {
        this.judulBuku = judulBuku;
    }

    public LocalDateTime getTanggalPinjam() {
        return tanggalPinjam;
    }

    public void setTanggalPinjam(LocalDateTime tanggalPinjam) {
        this.tanggalPinjam = tanggalPinjam;
    }

    public LocalDateTime getTanggalKembali() {
        return tanggalKembali;
    }

    public void setTanggalKembali(LocalDateTime tanggalKembali) {
        this.tanggalKembali = tanggalKembali;
    }
}
