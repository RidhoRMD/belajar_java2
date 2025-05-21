package com.belajar.Ridho;

import java.util.Scanner;

class TagihanPDAM {
    private String noPelanggan;
    private double pemakaian;
    private double tarifPerM3;
    private double totalTagihan;

    // Konstruktor
    public TagihanPDAM(String noPelanggan, double pemakaian) {
        this.noPelanggan = noPelanggan;
        this.pemakaian = pemakaian;
        this.tarifPerM3 = hitungTarifPerM3();
        this.totalTagihan = hitungTotalTagihan();
    }

    // Menentukan tarif per m³ berdasarkan pemakaian
    private double hitungTarifPerM3() {
        if (pemakaian <= 10) {
            return 3000; // Tarif untuk pemakaian <= 10 m³
        } else if (pemakaian <= 20) {
            return 5000; // Tarif untuk pemakaian 11-20 m³
        } else if (pemakaian <= 30) {
            return 7000; // Tarif untuk pemakaian 21-30 m³
        } else {
            return 10000; // Tarif untuk pemakaian > 30 m³
        }
    }

    // Menghitung total tagihan
    private double hitungTotalTagihan() {
        return pemakaian * tarifPerM3;
    }

    // Getter untuk total tagihan
    public double getTotalTagihan() {
        return totalTagihan;
    }

    // Menampilkan rincian tagihan
    public void tampilkanTagihan() {
        System.out.println("\n==== Rincian Tagihan PDAM ====");
        System.out.println("Nomor Pelanggan: " + noPelanggan);
        System.out.println("Pemakaian Air  : " + pemakaian + " m³");
        System.out.println("Tarif per m³   : Rp " + tarifPerM3);
        System.out.println("Total Tagihan  : Rp " + totalTagihan);
    }
}

// Kelas utama untuk menjalankan program
public class Main {
    public static void main(String[] args) {
        Scanner input = new Scanner(System.in);

        // Meminta pengguna memasukkan nomor pelanggan
        System.out.print("Masukkan Nomor Pelanggan: ");
        String noPelanggan = input.nextLine();

        // Meminta pengguna memasukkan pemakaian air
        System.out.print("Masukkan jumlah pemakaian air (m³): ");
        double pemakaian = input.nextDouble();

        // Membuat objek dan menampilkan tagihan
        TagihanPDAM tagihan = new TagihanPDAM(noPelanggan, pemakaian);
        tagihan.tampilkanTagihan();

        // Memproses pembayaran dengan pengulangan jika uang kurang
        double totalTagihan = tagihan.getTotalTagihan();
        double pembayaran;
        
        do {
            System.out.print("\nMasukkan jumlah pembayaran: Rp ");
            pembayaran = input.nextDouble();

            if (pembayaran < totalTagihan) {
                System.out.println("Maaf, pembayaran kurang. Silakan bayar Rp " + (totalTagihan - pembayaran) + " lagi.");
            }
        } while (pembayaran < totalTagihan);

        // Menghitung kembalian jika pembayaran cukup
        double kembalian = pembayaran - totalTagihan;
        System.out.println("Pembayaran berhasil!");
        if (kembalian > 0) {
            System.out.println("Kembalian Anda: Rp " + kembalian);
        }

        input.close();
    }
}