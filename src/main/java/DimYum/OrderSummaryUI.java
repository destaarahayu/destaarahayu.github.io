package DimYum;

import javax.swing.*;
import java.awt.*;

public class OrderSummaryUI extends JFrame {
    //encapsulation
    private Product[] products;
    private int total;
    private ShoppingCartUI parentCart;

    //constructor
    public OrderSummaryUI(Product[] products, int total, ShoppingCartUI parentCart) {
        this.products = products;
        this.total = total;
        this.parentCart = parentCart;
        initializeUI();
    }
    //method
    private void initializeUI() {
        setTitle("Pesananmu");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setSize(400, 600);
        getContentPane().setBackground(new Color(153, 0, 0));
        setLayout(new BorderLayout());

        // Panel atas dengan tombol back dan judul
        JPanel headerPanel = new JPanel(new BorderLayout());
        headerPanel.setBackground(new Color(153, 0, 0));
        headerPanel.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));

        JButton backButton = new JButton("←");
        backButton.setFont(new Font("Arial", Font.BOLD, 20));
        backButton.setBackground(new Color(153, 0, 0));
        backButton.setPreferredSize(new Dimension(60, 40));
        backButton.setForeground(Color.WHITE);
        backButton.setBorderPainted(false);
        backButton.setFocusPainted(false);
        backButton.setContentAreaFilled(false);

        backButton.addActionListener(e -> {
            parentCart.setVisible(true);
            this.dispose();
        });
        headerPanel.add(backButton, BorderLayout.WEST);

        JLabel titleLabel = new JLabel("Pesananmu", SwingConstants.CENTER); // DITAMBAHKAN JUDUL DI SINI
        titleLabel.setFont(new Font("Arial", Font.BOLD, 20));
        titleLabel.setForeground(Color.ORANGE);
        headerPanel.add(titleLabel, BorderLayout.CENTER);

        add(headerPanel, BorderLayout.NORTH);

        // Panel utama isi ringkasan
        JPanel summaryPanel = new JPanel();
        summaryPanel.setLayout(new BoxLayout(summaryPanel, BoxLayout.Y_AXIS));
        summaryPanel.setBackground(new Color(153, 0, 0));
        summaryPanel.setBorder(BorderFactory.createEmptyBorder(10, 20, 10, 20));

        for (Product product : products) {
            JPanel itemPanel = new JPanel(new BorderLayout(10, 0));
            itemPanel.setBackground(new Color(153, 0, 0));

            JLabel imageLabel;
            try {
                ImageIcon icon = new ImageIcon(product.getImagePath());
                Image scaledImage = icon.getImage().getScaledInstance(50, 50, Image.SCALE_SMOOTH);
                imageLabel = new JLabel(new ImageIcon(scaledImage));
            } catch (Exception ex) {
                imageLabel = new JLabel();
            }
            itemPanel.add(imageLabel, BorderLayout.WEST);

            JPanel textPanel = new JPanel(new BorderLayout());
            textPanel.setBackground(new Color(153, 0, 0));

            JLabel quantityLabel = new JLabel(product.getName() + " ");
            quantityLabel.setForeground(Color.WHITE);
            quantityLabel.setFont(new Font("Arial", Font.PLAIN, 14));
            textPanel.add(quantityLabel, BorderLayout.WEST);

            JLabel nameLabel = new JLabel(product.getQuantity() + "x", SwingConstants.CENTER);
            nameLabel.setForeground(Color.WHITE);
            nameLabel.setFont(new Font("Arial", Font.PLAIN, 14));
            textPanel.add(nameLabel, BorderLayout.CENTER);

            JLabel priceLabel = new JLabel("Rp. " + formatPrice(product.getTotalPrice()));
            priceLabel.setForeground(Color.WHITE);
            priceLabel.setFont(new Font("Arial", Font.PLAIN, 14));
            textPanel.add(priceLabel, BorderLayout.EAST);

            itemPanel.add(textPanel, BorderLayout.CENTER);

            summaryPanel.add(itemPanel);
            summaryPanel.add(Box.createRigidArea(new Dimension(0, 10)));
        }

        JLabel totalLabel = new JLabel("Total: Rp. " + formatPrice(total));
        totalLabel.setFont(new Font("Arial", Font.BOLD, 16));
        totalLabel.setForeground(Color.ORANGE);
        totalLabel.setAlignmentX(Component.LEFT_ALIGNMENT);
        summaryPanel.add(Box.createRigidArea(new Dimension(0, 10)));
        summaryPanel.add(totalLabel);

        JScrollPane scrollPane = new JScrollPane(summaryPanel);
        scrollPane.setBorder(null);
        scrollPane.getViewport().setBackground(new Color(153, 0, 0));
        add(scrollPane, BorderLayout.CENTER);

        // Tombol Pesan di bawah
        JButton pesanButton = new JButton("Pesan Sekarang");
        pesanButton.setFont(new Font("Arial", Font.BOLD, 16));
        pesanButton.setBackground(Color.ORANGE);
        pesanButton.setForeground(new Color(153, 0, 0));
        pesanButton.setPreferredSize(new Dimension(200, 40));
        JPanel buttonPanel = new JPanel();
        buttonPanel.setBackground(new Color(153, 0, 0));
        buttonPanel.setBorder(BorderFactory.createEmptyBorder(10, 20, 20, 20));
        buttonPanel.add(pesanButton);

        pesanButton.addActionListener(e -> {
            //constructor overloading
            new PaymentUI(products, total, parentCart).setVisible(true);
            this.dispose();
        });

        add(buttonPanel, BorderLayout.SOUTH);
    }

    private String formatPrice(int price) {
        return String.format("%,d", price).replace(',', '.') + ",-";
    }
}