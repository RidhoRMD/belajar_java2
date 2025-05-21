package com.Perpustakaan;

import java.time.LocalDate;

public class Peminjaman {
    private String namaPeminjam, idBuku;
    private LocalDate tanggalPinjam, tanggalKembali;

    public Peminjaman(String namaPeminjam, LocalDate tanggalPinjam, LocalDate tanggalKembali, String idBuku) {
        this.namaPeminjam = namaPeminjam;
        this.tanggalPinjam = tanggalPinjam;
        this.tanggalKembali = tanggalKembali;
        this.idBuku = idBuku;
    }

    // Getter & Setter
    public String getNamaPeminjam() { return namaPeminjam; }
    public LocalDate getTanggalPinjam() { return tanggalPinjam; }
    public LocalDate getTanggalKembali() { return tanggalKembali; }
    public String getIdBuku() { return idBuku; }

    public void setNamaPeminjam(String namaPeminjam) { this.namaPeminjam = namaPeminjam; }
    public void setTanggalPinjam(LocalDate tanggalPinjam) { this.tanggalPinjam = tanggalPinjam; }
    public void setTanggalKembali(LocalDate tanggalKembali) { this.tanggalKembali = tanggalKembali; }
    public void setIdBuku(String idBuku) { this.idBuku = idBuku; }
}