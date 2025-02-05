package com.belajar;
import javax.swing.JOptionPane;
public class Latihan5 {
    public static void main(String[] args) {
        String nama = "";
        nama = JOptionPane.showInputDialog("Silahkan masukan nama Anda");
        String msg = "Hello " + nama + "!";
        JOptionPane.showConfirmDialog(null, msg);

    }
}
