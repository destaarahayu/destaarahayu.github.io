package DimYum.View;

import javax.swing.*;
import java.awt.*;

public class Chocolate extends JFrame {

    private MenuGUI parent;

    public Chocolate(MenuGUI parent) {
        this.parent = parent;

        setTitle("Detail Chocolate");
        setSize(400, 650);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);

        // Panel utama
        JPanel panel = new JPanel(new BorderLayout());

        // Gambar
        JLabel imageLabel = new JLabel();
        ImageIcon originalIcon = new ImageIcon("Chocolate.jpg");
        Image scaledImage = originalIcon.getImage().getScaledInstance(400, -1, Image.SCALE_SMOOTH);
        imageLabel.setIcon(new ImageIcon(scaledImage));
        imageLabel.setHorizontalAlignment(SwingConstants.CENTER);
        imageLabel.setPreferredSize(new Dimension(400, 300));

        // Tombol kembali
        JButton backButton = new JButton("← Kembali");
        backButton.setForeground(new Color(165, 42, 42));
        backButton.setFont(new Font("Poppins", Font.BOLD, 16));
        backButton.setFocusPainted(false);
        backButton.setBorderPainted(false);
        backButton.setContentAreaFilled(false);
        backButton.setHorizontalAlignment(SwingConstants.LEFT);

        JPanel topPanel = new JPanel(new BorderLayout());
        topPanel.add(backButton, BorderLayout.WEST);
        topPanel.setOpaque(false);
        imageLabel.setLayout(new BorderLayout());
        imageLabel.add(topPanel, BorderLayout.NORTH);

        // Panel bawah
        JPanel bottomPanel = new JPanel();
        bottomPanel.setBackground(new Color(139, 0, 0));
        bottomPanel.setLayout(new BoxLayout(bottomPanel, BoxLayout.Y_AXIS));
        bottomPanel.setBorder(BorderFactory.createEmptyBorder(20, 20, 20, 20));

        // Nama dan harga
        JPanel namePricePanel = new JPanel(new BorderLayout());
        namePricePanel.setOpaque(false);
        JLabel nameLabel = new JLabel("<html><b>Minuman<br>Choco Bliss</b></html>");
        nameLabel.setForeground(Color.WHITE);
        nameLabel.setFont(new Font("Poppins", Font.BOLD, 18));
        JLabel priceLabel = new JLabel("<html><b>Rp. 22.000,-</b></html>");
        priceLabel.setForeground(Color.WHITE);
        priceLabel.setFont(new Font("Poppins", Font.BOLD, 18));
        namePricePanel.add(nameLabel, BorderLayout.WEST);
        namePricePanel.add(priceLabel, BorderLayout.EAST);

        // Deskripsi
        JLabel descLabel = new JLabel("<html>Chocolate<br>Nikmatnya cokelat pekat yang creamy dan manis pas, bikin hari kamu makin manis! Minuman cokelat favorit buat penghilang penat dan mood booster kapan aja.</html>");
        descLabel.setForeground(Color.WHITE);
        descLabel.setFont(new Font("Poppins", Font.PLAIN, 16));
        descLabel.setAlignmentX(Component.CENTER_ALIGNMENT);
        descLabel.setBorder(BorderFactory.createEmptyBorder(10, 0, 10, 0));

        // Tombol kembali - event
        backButton.addActionListener(e -> {
            this.dispose(); // Tutup detail
            parent.setVisible(true); // Kembali ke menu
        });

        // Tambah ke panel bawah
        bottomPanel.add(namePricePanel);
        bottomPanel.add(descLabel);

        // Gabungkan semua panel
        panel.add(imageLabel, BorderLayout.NORTH);
        panel.add(bottomPanel, BorderLayout.CENTER);

        setContentPane(panel);
    }
}
