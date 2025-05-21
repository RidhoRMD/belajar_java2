package com.belajar.Ridho;

import java.util.Scanner;

public class Minimarket {
    public static void main(String[] args) {
        Scanner input = new Scanner(System.in);

        // Daftar harga barang (contoh)
        String[] barang = {"Beras", "Gula", "Minyak Goreng", "Mie Instan", "Susu"};
        int[] harga = {50000, 15000, 25000, 3000, 20000};

        System.out.println("=== Selamat Datang di Minimarket ===");
        System.out.println("Daftar Barang dan Harga:");
        for (int i = 0; i < barang.length; i++) {
            System.out.println((i + 1) + ". " + barang[i] + " - Rp " + harga[i]);
        }

        // Input jumlah item yang dibeli
        System.out.print("\nBerapa jenis barang yang ingin dibeli? ");
        int jumlahJenis = input.nextInt();

        int totalHarga = 0;
        for (int i = 0; i < jumlahJenis; i++) {
            System.out.print("Pilih barang (1-5): ");
            int pilihan = input.nextInt();
            System.out.print("Jumlah beli: ");
            int jumlahBeli = input.nextInt();

            // Hitung harga barang yang dipilih
            int subtotal = harga[pilihan - 1] * jumlahBeli;
            totalHarga += subtotal;
        }

        // Diskon jika total belanja lebih dari Rp 100.000
        int diskon = 0;
        if (totalHarga > 100000) {
            diskon = (int) (totalHarga * 0.1); // 10% diskon
            System.out.println("Anda mendapatkan diskon 10%: Rp " + diskon);
        }

        int totalBayar = totalHarga - diskon;
        System.out.println("\nTotal yang harus dibayar: Rp " + totalBayar);

        // Pembayaran
        System.out.print("Masukkan uang pembayaran: Rp ");
        int bayar = input.nextInt();

        // Hitung kembalian
        if (bayar >= totalBayar) {
            int kembalian = bayar - totalBayar;
            System.out.println("Kembalian Anda: Rp " + kembalian);
        } else {
            System.out.println("Uang Anda kurang! Transaksi dibatalkan.");
        }

        input.close();
        System.out.println("Terima kasih telah berbelanja di minimarket kami!");
    }
}