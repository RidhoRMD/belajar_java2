package com.belajar2;
// File: Main.java

// Kelas induk
class Kendaraan {
    void bunyiKlakson() {
        System.out.println("Kendaraan mengeluarkan bunyi klakson umum.");
    }
}

// Kelas turunan 1
class Mobil extends Kendaraan {
    @Override
    void bunyiKlakson() {
        System.out.println("Mobil: Tiiin Tiiin!");
    }
}

// Kelas turunan 2
class Motor extends Kendaraan {
    @Override
    void bunyiKlakson() {
        System.out.println("Motor: Piip Piip!");
    }
}

// Kelas utama
public class Walid {
    public static void main(String[] args) {
        Kendaraan k1 = new Kendaraan();
        Kendaraan k2 = new Mobil();
        Kendaraan k3 = new Motor();

        k1.bunyiKlakson(); // Output: Kendaraan mengeluarkan bunyi klakson umum.
        k2.bunyiKlakson(); // Output: Mobil: Tiiin Tiiin!
        k3.bunyiKlakson(); // Output: Motor: Piip Piip!
    }
}