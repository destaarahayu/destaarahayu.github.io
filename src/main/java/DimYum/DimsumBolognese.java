package DimYum.View;

import javax.swing.*;
import java.awt.*;

public class DimsumBolognese extends JFrame {

    private MenuGUI menuGUI;

    public DimsumBolognese(MenuGUI menuGUI) {
        this.menuGUI = menuGUI;

        setTitle("Detail Dimsum Bolognese");
        setSize(400, 650);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);

        JPanel panel = new JPanel(new BorderLayout());

        // Gambar
        JLabel imageLabel = new JLabel();
        ImageIcon originalIcon = new ImageIcon("savory_balo.jpg");
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
        JLabel nameLabel = new JLabel("<html><b>Makanan<br>Savory Balo</b></html>");
        nameLabel.setForeground(Color.WHITE);
        nameLabel.setFont(new Font("Poppins", Font.BOLD, 18));
        JLabel priceLabel = new JLabel("<html><b>Rp. 30.000,-</b></html>");
        priceLabel.setForeground(Color.WHITE);
        priceLabel.setFont(new Font("Poppins", Font.BOLD, 18));
        namePricePanel.add(nameLabel, BorderLayout.WEST);
        namePricePanel.add(priceLabel, BorderLayout.EAST);

        // Deskripsi
        JLabel descLabel = new JLabel("<html>Dimsum Bolognese<br>Perpaduan dimsum lembut dengan saus bolognaise gurih dan berempah yang bikin nagih! Cemilan unik buat kamu yang suka rasa berbeda dan lezat. Pas banget buat nemenin santai atau acara spesial!</html>");
        descLabel.setForeground(Color.WHITE);
        descLabel.setFont(new Font("Poppins", Font.PLAIN, 16));
        descLabel.setAlignmentX(Component.CENTER_ALIGNMENT);
        descLabel.setBorder(BorderFactory.createEmptyBorder(5, 0, 5, 0));

        // Event tombol kembali
        backButton.addActionListener(e -> {
            this.dispose();
            menuGUI.setVisible(true);
        });

        // Tambahkan ke panel bawah
        bottomPanel.add(namePricePanel);
        bottomPanel.add(descLabel);

        // Gabungkan semua ke panel utama
        panel.add(imageLabel, BorderLayout.NORTH);
        panel.add(bottomPanel, BorderLayout.CENTER);

        setContentPane(panel);
    }
}