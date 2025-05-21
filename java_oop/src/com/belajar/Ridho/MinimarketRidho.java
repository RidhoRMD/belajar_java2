package com.belajar.Ridho;
import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;

public class MinimarketRidho {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        // Daftar barang dan harga
        String[] barang = {"Susu", "Mie", "Roti", "Eskrim", "Sabun", "Shampo"};
        int[] harga = {3000, 2500, 2000, 5000, 8000, 9000};

        // Menyimpan barang yang dibeli
        Map<String, Integer> keranjang = new HashMap<>();
        Map<String, Integer> totalHargaPerBarang = new HashMap<>();

        System.out.println("Selamat Datang Di Market Ridho");
        System.out.println("Daftar barang dan harga:");
        for (int i = 0; i < barang.length; i++) {
            System.out.println((i + 1) + ". " + barang[i] + " - Rp " + harga[i]);
        }

        int totalHarga = 0;
        boolean lanjutBelanja = true;

        while (lanjutBelanja) {
            System.out.print("\nSilakan pilih (1-6): ");
            int pilihan = scanner.nextInt();

            if (pilihan < 1 || pilihan > 6) {
                System.out.println("Pilihan tidak valid!");
                continue;
            }

            System.out.print("Jumlah beli: ");
            int jumlah = scanner.nextInt();
            
            String namaBarang = barang[pilihan - 1];
            int hargaBarang = harga[pilihan - 1];
            int subtotal = hargaBarang * jumlah;
            
            // Menambahkan ke keranjang
            keranjang.put(namaBarang, keranjang.getOrDefault(namaBarang, 0) + jumlah);
            totalHargaPerBarang.put(namaBarang, totalHargaPerBarang.getOrDefault(namaBarang, 0) + subtotal);
            
            totalHarga += subtotal;

            System.out.println(namaBarang + " sebanyak " + jumlah + " dimasukkan ke keranjang.");

            System.out.print("Barang yang mau dibeli lagi? (ya/tidak): ");
            String lanjut = scanner.next();
            lanjutBelanja = lanjut.equalsIgnoreCase("ya");
        }

        // Menampilkan daftar belanja
        System.out.println("\nRincian pembelian:");
        for (String namaBarang : keranjang.keySet()) {
            int jumlah = keranjang.get(namaBarang);
            int subtotal = totalHargaPerBarang.get(namaBarang);
            System.out.println(namaBarang + " " + jumlah + " x " + harga[findIndex(barang, namaBarang)] + " = Rp " + subtotal);
        }

        System.out.println("Total yang harus dibayar: Rp " + totalHarga);
        
        System.out.print("Masukkan uang pembayaran: ");
        int uangDibayar = scanner.nextInt();

        while (uangDibayar < totalHarga) {
            System.out.println("Uang tidak cukup, masukkan jumlah yang benar.");
            System.out.print("Masukkan uang pembayaran: ");
            uangDibayar = scanner.nextInt();
        }

        // Hitung kembalian
        int kembalian = uangDibayar - totalHarga;
        System.out.println("Kembalian uang Anda: Rp " + kembalian);
        System.out.println("Terima kasih telah belanja di Market Ridho, hati-hati di jalan :)");

        scanner.close();
    }

    // Fungsi untuk mencari indeks barang dalam array
    private static int findIndex(String[] array, String item) {
        for (int i = 0; i < array.length; i++) {
            if (array[i].equals(item)) {
                return i;
            }
        }
        return -1;
    }
}        