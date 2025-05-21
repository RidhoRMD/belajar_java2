package com.belajar;
//Latihan method non void
public class Mobilku {
    String warna1;
    int tahun_produksi;
    int nomorMesin;
    int nomorRagka;
    //Method mengisi vaiabel instance
    void isi (String warna1, int tahun_produksi, int nomorMesin, int nomorRangka){
        this.warna1 = warna1;
        this.tahun_produksi = tahun_produksi;
        this.nomorMesin = nomorMesin;
        this.nomorRagka = nomorRangka;
    }
    //Method menampilkan isi variabel
    String ambil_warna (){
        return warna1;
    }
    int ambil_tahun_produksi (){
        return tahun_produksi;
    }
    int ambil_nomorMesin (){
        return nomorMesin;
    }
    int ambil_nomorRangka (){
        return nomorRagka;
    }
    public static void main(String[] args) {
        Mobilku mobilNiaga = new Mobilku();
        //Proses pemanggilan method void
        mobilNiaga.isi("Putih", 2018, 12345, 8967); 
        System.out.println("Warna : " + mobilNiaga.ambil_warna());
        System.out.println("Tahun Produksi : " + mobilNiaga.ambil_tahun_produksi());
        System.out.println("Nomor Mesin : " + mobilNiaga.ambil_nomorMesin());
        System.out.println("Nomor Rangka : " + mobilNiaga.ambil_nomorRangka());
        
    }
}

