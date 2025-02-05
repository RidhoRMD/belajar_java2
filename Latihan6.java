package com.belajar;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
public class Latihan6 {
    public static void main(String[] args) throws IOException {
        String nama;
        int umur;
        //Membuat objek inputstream
        InputStreamReader isr = new InputStreamReader(System.in);
        //Membuat objek bufferedReader
        BufferedReader br = new BufferedReader(isr);
        //Mengisi variabel nama dengan bufferedReader
        System.out.print("Inputkan nama Anda : ");
        nama = br.readLine();
        System.out.print("Inputkan umur Anda : ");
        umur = Integer.parseInt(br.readLine());
        //Tampilkan nama dan umur
        System.out.println("Nama anda adalah " + nama);
        System.out.println("Umur " + umur);

    }
}
