package com.Perpustakaan;

public class Buku {
    private String idBuku, judul, pengarang, kategori;
    private int tahunTerbit;

    public Buku(String idBuku, String judul, String pengarang, int tahunTerbit, String kategori) {
        this.idBuku = idBuku;
        this.judul = judul;
        this.pengarang = pengarang;
        this.tahunTerbit = tahunTerbit;
        this.kategori = kategori;
    }

    // Getter & Setter
    public String getIdBuku() { return idBuku; }
    public String getJudul() { return judul; }
    public String getPengarang() { return pengarang; }
    public int getTahunTerbit() { return tahunTerbit; }
    public String getKategori() { return kategori; }

    public void setIdBuku(String idBuku) { this.idBuku = idBuku; }
    public void setJudul(String judul) { this.judul = judul; }
    public void setPengarang(String pengarang) { this.pengarang = pengarang; }
    public void setTahunTerbit(int tahunTerbit) { this.tahunTerbit = tahunTerbit; }
    public void setKategori(String kategori) { this.kategori = kategori; }
}