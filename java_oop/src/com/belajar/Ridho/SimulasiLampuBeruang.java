package com.belajar.Ridho;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import javax.swing.Timer;

public class SimulasiLampuBeruang extends JFrame {
    private boolean lampuNyala = false;
    private boolean beruangKeluar = false;
    private int xBeruang = 420;
    private int yBeruang = 300;
    private boolean tanganNaik = false;
    private Timer animasiTimer;

    public SimulasiLampuBeruang() {
        setTitle("Simulasi Lampu & Beruang");
        setSize(600, 500);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setLocationRelativeTo(null);

        DrawingPanel panel = new DrawingPanel();
        add(panel);
        setVisible(true);
    }

    class DrawingPanel extends JPanel implements MouseListener {
        Rectangle tombolTali = new Rectangle(140, 180, 20, 20);

        public DrawingPanel() {
            addMouseListener(this);
        }

        protected void paintComponent(Graphics g) {
            super.paintComponent(g);
            setBackground(lampuNyala ? Color.YELLOW : Color.DARK_GRAY);

            // Gambar lampu
            g.setColor(lampuNyala ? Color.WHITE : Color.BLACK);
            g.fillOval(145, 120, 30, 30);

            // Gambar tali
            g.setColor(Color.BLACK);
            g.drawLine(160, 150, 160, 180);
            g.fillOval(tombolTali.x, tombolTali.y, tombolTali.width, tombolTali.height);

            // Gambar pintu
            g.setColor(new Color(153, 102, 0));
            g.fillRect(450, 250, 60, 150);

            // Gambar beruang
            if (beruangKeluar) {
                g.setColor(new Color(204, 102, 0));

                // Kaki
                g.fillOval(xBeruang - 15, yBeruang + 60, 20, 30);
                g.fillOval(xBeruang + 15, yBeruang + 60, 20, 30);

                // Badan
                g.fillOval(xBeruang - 20, yBeruang + 20, 60, 60);

                // Tangan
                if (tanganNaik) {
                    g.fillOval(xBeruang - 40, yBeruang + 5, 20, 20); // kiri naik
                    g.fillOval(xBeruang + 40, yBeruang + 5, 20, 20); // kanan naik
                } else {
                    g.fillOval(xBeruang - 35, yBeruang + 40, 20, 20); // kiri bawah
                    g.fillOval(xBeruang + 35, yBeruang + 40, 20, 20); // kanan bawah
                }

                // Kepala
                g.fillOval(xBeruang, yBeruang, 40, 40);

                // Telinga
                g.fillOval(xBeruang, yBeruang - 10, 10, 10);
                g.fillOval(xBeruang + 30, yBeruang - 10, 10, 10);

                // Mata
                g.setColor(Color.BLACK);
                g.fillOval(xBeruang + 10, yBeruang + 10, 5, 5);
                g.fillOval(xBeruang + 25, yBeruang + 10, 5, 5);
            }
        }

        public void mousePressed(MouseEvent e) {
            if (tombolTali.contains(e.getPoint()) && !lampuNyala) {
                lampuNyala = true;
                repaint();

                // Setelah lampu dinyalakan, animasi beruang keluar
                Timer t = new Timer(1000, evt -> mulaiAnimasiBeruang());
                t.setRepeats(false);
                t.start();
            }
        }

        private void mulaiAnimasiBeruang() {
            beruangKeluar = true;
            xBeruang = 420;
            tanganNaik = false;

            animasiTimer = new Timer(30, e -> {
                if (xBeruang > 140) {
                    xBeruang -= 2;
                    if (xBeruang <= 160 && !tanganNaik) {
                        tanganNaik = true;
                        repaint();
                        Timer tMatikan = new Timer(1000, evt -> {
                            lampuNyala = false;
                            tanganNaik = false;
                            repaint();
                        });
                        tMatikan.setRepeats(false);
                        tMatikan.start();
                    }
                } else {
                    // Kembali ke pintu
                    animasiTimer.stop();
                    Timer tKembali = new Timer(30, new ActionListener() {
                        public void actionPerformed(ActionEvent e2) {
                            if (xBeruang < 420) {
                                xBeruang += 2;
                                repaint();
                            } else {
                                ((Timer) e2.getSource()).stop();
                                beruangKeluar = false;
                                repaint();
                            }
                        }
                    });
                    tKembali.start();
                }
                repaint();
            });
            animasiTimer.start();
        }

        public void mouseClicked(MouseEvent e) {}
        public void mouseReleased(MouseEvent e) {}
        public void mouseEntered(MouseEvent e) {}
        public void mouseExited(MouseEvent e) {}
    }

    public static void main(String[] args) {
        new SimulasiLampuBeruang();
    }
}