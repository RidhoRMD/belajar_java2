package com.Perpustakaan;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.sql.*;

public class BukuForm extends JFrame {
    private JTextField tfId, tfJudul, tfPengarang, tfTahun, tfKategori;
    private JButton btnTambah, btnEdit, btnHapus, btnSimpan;

    public BukuForm() {
        setTitle("Form Input Buku");
        setSize(400, 350);
        setLayout(new GridLayout(7, 2));

        // Input fields
        add(new JLabel("ID Buku:"));
        tfId = new JTextField();
        add(tfId);

        add(new JLabel("Judul:"));
        tfJudul = new JTextField();
        add(tfJudul);

        add(new JLabel("Pengarang:"));
        tfPengarang = new JTextField();
        add(tfPengarang);

        add(new JLabel("Tahun Terbit:"));
        tfTahun = new JTextField();
        add(tfTahun);

        add(new JLabel("Kategori:"));
        tfKategori = new JTextField();
        add(tfKategori);

        // Tombol
        btnTambah = new JButton("Tambah");
        btnEdit = new JButton("Edit");
        btnHapus = new JButton("Hapus");
        btnSimpan = new JButton("Simpan");

        add(btnTambah);
        add(btnEdit);
        add(btnHapus);
        add(btnSimpan);

        // Aksi tombol
        btnTambah.addActionListener(e -> tambahData());
        btnEdit.addActionListener(e -> editData());
        btnHapus.addActionListener(e -> hapusData());
        btnSimpan.addActionListener(e -> bersihkanForm());

        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setVisible(true);
    }

    private void tambahData() {
        try {
            Connection conn = DatabaseConnection.getConnection();
            String sql = "INSERT INTO buku (id_buku, judul, pengarang, tahun_terbit, kategori) VALUES (?, ?, ?, ?, ?)";
            PreparedStatement stmt = conn.prepareStatement(sql);
            stmt.setString(1, tfId.getText());
            stmt.setString(2, tfJudul.getText());
            stmt.setString(3, tfPengarang.getText());
            stmt.setInt(4, Integer.parseInt(tfTahun.getText()));
            stmt.setString(5, tfKategori.getText());
            stmt.executeUpdate();
            JOptionPane.showMessageDialog(this, "Data berhasil ditambahkan!");
        } catch (Exception ex) {
            JOptionPane.showMessageDialog(this, "Gagal tambah data: " + ex.getMessage());
        }
    }

    private void editData() {
        try {
            Connection conn = DatabaseConnection.getConnection();
            String sql = "UPDATE buku SET judul=?, pengarang=?, tahun_terbit=?, kategori=? WHERE id_buku=?";
            PreparedStatement stmt = conn.prepareStatement(sql);
            stmt.setString(1, tfJudul.getText());
            stmt.setString(2, tfPengarang.getText());
            stmt.setInt(3, Integer.parseInt(tfTahun.getText()));
            stmt.setString(4, tfKategori.getText());
            stmt.setString(5, tfId.getText());
            stmt.executeUpdate();
            JOptionPane.showMessageDialog(this, "Data berhasil diedit!");
        } catch (Exception ex) {
            JOptionPane.showMessageDialog(this, "Gagal edit data: " + ex.getMessage());
        }
    }

    private void hapusData() {
        try {
            Connection conn = DatabaseConnection.getConnection();
            String sql = "DELETE FROM buku WHERE id_buku=?";
            PreparedStatement stmt = conn.prepareStatement(sql);
            stmt.setString(1, tfId.getText());
            stmt.executeUpdate();
            JOptionPane.showMessageDialog(this, "Data berhasil dihapus!");
        } catch (Exception ex) {
            JOptionPane.showMessageDialog(this, "Gagal hapus data: " + ex.getMessage());
        }
    }

    private void bersihkanForm() {
        tfId.setText("");
        tfJudul.setText("");
        tfPengarang.setText("");
        tfTahun.setText("");
        tfKategori.setText("");
    }

    public static void main(String[] args) {
        new BukuForm();
    }
}