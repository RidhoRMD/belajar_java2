package com.belajar.Ridho;

import java.util.Scanner;

public class Market2 {
    public static void main(String[] args) {
        Scanner input = new Scanner(System.in);
        
        String[] barang = {"Susu", "Mie", "Roti", "Eskrim", "Sabun", "Shampo",};
        int[] harga ={3000, 2500, 2000, 5000, 8000, 9000};

        System.out.println("Selamat Datang Di Market Ridho");
        System.out.println("Daftar Barang Dan Harga");
        for (int i = 0; i < barang.length; i++) {
            System.out.println((i + 1) +". " + barang[i] +" - Rp " + harga[i]);
        }

        System.out.print("\nAda yang mau dibeli? (iya/tidak): ");
        String jawaban = input.nextLine().trim().toLowerCase();

        if (jawaban.equals("iya")) {
            System.out.println("Silakan pilih barang yang ingin dibeli.");
        } else if (jawaban.equals("tidak")) {
            System.out.println("Baik, terimakasih telah berkunjung :).");
            return;  
        } else {
            System.out.println("Jawaban tidak valid.");
            return;
        }


        System.out.print("\nBerapa jenis barang yang ingin dibeli? ");
        int jumlahJenis = input.nextInt();

        int totalHarga = 0;
        for (int i = 0; i < jumlahJenis; i++) {
            System.out.print("Pilih barang (1-6): ");
            int pilihan = input.nextInt();
            System.out.print("Jumlah beli: ");
            int jumlahBeli = input.nextInt();

           
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

        
        System.out.print("Masukkan uang pembayaran: Rp ");
        int bayar = input.nextInt();

        
        if (bayar >= totalBayar) {
            int kembalian = bayar - totalBayar;
            System.out.println("Kembalian Anda: Rp " + kembalian);
        } else {
            System.out.println("Uang Anda kurang! Transaksi dibatalkan.");
        }

        input.close();
        System.out.println("Terima kasih telah berbelanja di market ridho,hati-hati di jalan;)!");

    }
    
}
