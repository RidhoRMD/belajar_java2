package com.Perpustakaan;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.sql.*;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

public class PeminjamanForm extends JFrame {
    private JTextField tfNama, tfTanggalPinjam, tfTanggalKembali, tfIdBuku;
    private JButton btnPinjam, btnReset;

    public PeminjamanForm() {
        setTitle("Form Peminjaman Buku");
        setSize(400, 250);
        setLayout(new GridLayout(6, 2));

        // Input fields
        add(new JLabel("Nama Peminjam:"));
        tfNama = new JTextField();
        add(tfNama);

        add(new JLabel("ID Buku:"));
        tfIdBuku = new JTextField();
        add(tfIdBuku);

        add(new JLabel("Tanggal Pinjam (yyyy-mm-dd):"));
        tfTanggalPinjam = new JTextField();
        add(tfTanggalPinjam);

        add(new JLabel("Tanggal Kembali (yyyy-mm-dd):"));
        tfTanggalKembali = new JTextField();
        add(tfTanggalKembali);

        // Tombol
        btnPinjam = new JButton("Pinjam");
        btnReset = new JButton("Reset");

        add(btnPinjam);
        add(btnReset);

        btnPinjam.addActionListener(e -> prosesPeminjaman());
        btnReset.addActionListener(e -> resetForm());

        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setVisible(true);
    }

    private void prosesPeminjaman() {
        String nama = tfNama.getText();
        String idBuku = tfIdBuku.getText();
        String tglPinjamStr = tfTanggalPinjam.getText();
        String tglKembaliStr = tfTanggalKembali.getText();

        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");

        try {
            LocalDate tglPinjam = LocalDate.parse(tglPinjamStr, formatter);
            LocalDate tglKembali = LocalDate.parse(tglKembaliStr, formatter);

            if (tglKembali.isBefore(tglPinjam)) {
                JOptionPane.showMessageDialog(this, "Tanggal kembali tidak boleh sebelum tanggal pinjam!");
                return;
            }

            Connection conn = DatabaseConnection.getConnection();
            String sql = "INSERT INTO peminjaman (nama_peminjam, id_buku, tanggal_pinjam, tanggal_kembali) VALUES (?, ?, ?, ?)";
            PreparedStatement stmt = conn.prepareStatement(sql);
            stmt.setString(1, nama);
            stmt.setString(2, idBuku);
            stmt.setDate(3, java.sql.Date.valueOf(tglPinjam));
            stmt.setDate(4, java.sql.Date.valueOf(tglKembali));
            stmt.executeUpdate();

            JOptionPane.showMessageDialog(this, "Peminjaman berhasil disimpan!");

        } catch (Exception ex) {
            JOptionPane.showMessageDialog(this, "Gagal meminjam: " + ex.getMessage());
        }
    }

    private void resetForm() {
        tfNama.setText("");
        tfIdBuku.setText("");
        tfTanggalPinjam.setText("");
        tfTanggalKembali.setText("");
    }

    public static void main(String[] args) {
        new PeminjamanForm();
    }
}