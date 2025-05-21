package com.belajar2;

import java.awt.*;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import javax.swing.*;
import javax.swing.table.DefaultTableModel;

class Buku {
    String id, judul, pengarang, kategori;
    int    tahunTerbit;

    Buku(String id, String judul, String pengarang, int tahunTerbit, String kategori) {
        this.id          = id;
        this.judul       = judul;
        this.pengarang   = pengarang;
        this.tahunTerbit = tahunTerbit;
        this.kategori    = kategori;
    }
}

class Peminjaman {
    String namaPeminjam, idBuku, tanggalPinjam, tanggalKembali;

    Peminjaman(String nama, String pinjam, String kembali, String idBuku) {
        this.namaPeminjam = nama;
        this.tanggalPinjam = pinjam;
        this.tanggalKembali = kembali;
        this.idBuku = idBuku;
    }
}

public class LibraryApp extends JFrame {

    private final ArrayList<Buku>        daftarBuku   = new ArrayList<>();
    private final ArrayList<Peminjaman>  daftarPinjam = new ArrayList<>();

    private final DefaultTableModel bukuModel =
            new DefaultTableModel(new String[]{"ID","Judul","Pengarang","Tahun","Kategori"},0);
    private final JTable bukuTable = new JTable(bukuModel);
    private final JTextField idField = new JTextField();
    private final JTextField judulField = new JTextField();
    private final JTextField pengarangField = new JTextField();
    private final JTextField tahunField = new JTextField();
    private final JTextField kategoriField = new JTextField();

    private final DefaultTableModel pinjamModel =
            new DefaultTableModel(new String[]{"Nama","Pinjam","Kembali","ID Buku"},0);
    private final JTable pinjamTable = new JTable(pinjamModel);
    private final JTextField namaField = new JTextField();
    private final JTextField pinjamField = new JTextField();
    private final JTextField kembaliField = new JTextField();
    private final JTextField idBukuPinjamField = new JTextField();

    public LibraryApp() {
        setTitle("Aplikasi Perpustakaan");
        setSize(900,500);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(EXIT_ON_CLOSE);

        add(makeTabs());
        setVisible(true);
    }

    private JPanel makeTabBuku() {
        JPanel form = new JPanel(new GridLayout(7,2,5,5));
        form.add(new JLabel("ID Buku:")); form.add(idField);
        form.add(new JLabel("Judul:")); form.add(judulField);
        form.add(new JLabel("Pengarang:")); form.add(pengarangField);
        form.add(new JLabel("Tahun Terbit:")); form.add(tahunField);
        form.add(new JLabel("Kategori:")); form.add(kategoriField);

        JButton tambahBtn = new JButton("Tambah");
        JButton editBtn = new JButton("Edit");
        JButton hapusBtn = new JButton("Hapus");
        JButton simpanBtn = new JButton("Simpan");

        form.add(tambahBtn); form.add(editBtn);
        form.add(hapusBtn); form.add(simpanBtn);

        tambahBtn.addActionListener(e -> tambahBuku());
        editBtn.addActionListener(e -> tampilkanKeFormBuku());
        hapusBtn.addActionListener(e -> hapusBuku());
        simpanBtn.addActionListener(e -> simpanPerubahanBuku());

        JPanel panel = new JPanel(new BorderLayout());
        panel.add(form, BorderLayout.NORTH);
        panel.add(new JScrollPane(bukuTable), BorderLayout.CENTER);
        return panel;
    }

    private void tambahBuku() {
        try {
            Buku b = new Buku(
                    idField.getText(),
                    judulField.getText(),
                    pengarangField.getText(),
                    Integer.parseInt(tahunField.getText()),
                    kategoriField.getText());
            daftarBuku.add(b);
            bukuModel.addRow(new Object[]{b.id,b.judul,b.pengarang,b.tahunTerbit,b.kategori});
            clearBukuForm();
        } catch (NumberFormatException ex) {
            JOptionPane.showMessageDialog(this,"Tahun terbit harus angka!");
        }
    }

    private void tampilkanKeFormBuku() {
        int row = bukuTable.getSelectedRow();
        if (row < 0) return;
        idField.setText(bukuModel.getValueAt(row,0).toString());
        judulField.setText(bukuModel.getValueAt(row,1).toString());
        pengarangField.setText(bukuModel.getValueAt(row,2).toString());
        tahunField.setText(bukuModel.getValueAt(row,3).toString());
        kategoriField.setText(bukuModel.getValueAt(row,4).toString());
    }

    private void hapusBuku() {
        int row = bukuTable.getSelectedRow();
        if (row < 0) return;
        daftarBuku.remove(row);
        bukuModel.removeRow(row);
        clearBukuForm();
    }

    private void simpanPerubahanBuku() {
        int row = bukuTable.getSelectedRow();
        if (row < 0) {
            JOptionPane.showMessageDialog(this,"Pilih baris yang ingin diperbarui (klik di tabel).");
            return;
        }
        try {
            Buku b = new Buku(
                    idField.getText(),
                    judulField.getText(),
                    pengarangField.getText(),
                    Integer.parseInt(tahunField.getText()),
                    kategoriField.getText());
            daftarBuku.set(row,b);
            bukuModel.setValueAt(b.id, row,0);
            bukuModel.setValueAt(b.judul, row,1);
            bukuModel.setValueAt(b.pengarang, row,2);
            bukuModel.setValueAt(b.tahunTerbit, row,3);
            bukuModel.setValueAt(b.kategori, row,4);
            clearBukuForm();
        } catch (NumberFormatException ex) {
            JOptionPane.showMessageDialog(this,"Tahun terbit harus angka!");
        }
    }

    private void clearBukuForm() {
        idField.setText(""); judulField.setText("");
        pengarangField.setText(""); tahunField.setText("");
        kategoriField.setText("");
    }

    private JPanel makeTabPinjam() {
        JPanel form = new JPanel(new GridLayout(5,2,5,5));
        form.add(new JLabel("Nama Peminjam:")); form.add(namaField);
        form.add(new JLabel("Tanggal Pinjam (tanggal-bulan-tahun):")); form.add(pinjamField);
        form.add(new JLabel("Tanggal Kembali (tanggal-bulan-tahun):")); form.add(kembaliField);
        form.add(new JLabel("ID Buku:")); form.add(idBukuPinjamField);

        JButton pinjamBtn = new JButton("Pinjam");
        form.add(pinjamBtn);

        pinjamBtn.addActionListener(e -> prosesPinjam());

        JPanel panel = new JPanel(new BorderLayout());
        panel.add(form, BorderLayout.NORTH);
        panel.add(new JScrollPane(pinjamTable), BorderLayout.CENTER);
        return panel;
    }

    private void prosesPinjam() {
        DateTimeFormatter fmt = DateTimeFormatter.ofPattern("dd-MM-yyyy");

        try {
            LocalDate tPinjam  = LocalDate.parse(pinjamField.getText(), fmt);
            LocalDate tKembali = LocalDate.parse(kembaliField.getText(), fmt);

            if (tKembali.isBefore(tPinjam)) {
                JOptionPane.showMessageDialog(this,"Tanggal kembali tidak boleh sebelum tanggal pinjam.");
                return;
            }

            Peminjaman p = new Peminjaman(
                    namaField.getText(),
                    tPinjam.format(fmt),
                    tKembali.format(fmt),
                    idBukuPinjamField.getText());

            daftarPinjam.add(p);
            pinjamModel.addRow(new Object[]{p.namaPeminjam,p.tanggalPinjam,p.tanggalKembali,p.idBuku});
            clearPinjamForm();

        } catch (Exception ex) {
            JOptionPane.showMessageDialog(this,"Format tanggal harus tanggal-bulan-tahun!ex:01-01-2025");
        }
    }

    private void clearPinjamForm() {
        namaField.setText("");
        pinjamField.setText("");
        kembaliField.setText("");
        idBukuPinjamField.setText("");
    }

    private JTabbedPane makeTabs() {
        JTabbedPane tabs = new JTabbedPane();
        tabs.add("Data Buku", makeTabBuku());
        tabs.add("Peminjaman", makeTabPinjam());
        return tabs;
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(LibraryApp::new);
    }
}