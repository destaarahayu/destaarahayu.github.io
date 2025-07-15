package DimYum;

import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;
import java.util.List;

public class ShoppingCartUI extends JFrame {
    //encapsulation
    private Product[] products;
    private List<Product> selectedProducts;
    private JLabel totalLabel;

    //contructor
    public ShoppingCartUI(Product[] products) {
        this.products = products;
        this.selectedProducts = new ArrayList<>();
        initializeUI();
    }

    // method untuk membangun UI halaman ini
    private void initializeUI() {
        setTitle("Keranjang Belanja");
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setSize(450, 700);
        getContentPane().setBackground(new Color(153, 0, 0));
        setLayout(new BorderLayout());

        // Panel judul + tombol Back
        JPanel titlePanel = new JPanel(new BorderLayout());
        titlePanel.setBackground(new Color(153, 0, 0));

        JButton backButton = new JButton("←");
        backButton.setFont(new Font("Arial", Font.BOLD, 20));
        backButton.setBackground(new Color(153, 0, 0));
        backButton.setForeground(Color.ORANGE);
        backButton.setBorderPainted(false);
        backButton.setFocusPainted(false);
        backButton.setContentAreaFilled(false);
        backButton.setCursor(new Cursor(Cursor.HAND_CURSOR));
        backButton.addActionListener(e -> {
            this.dispose(); // kembali ke MenuGUI
        });

        titlePanel.add(backButton, BorderLayout.WEST);

        JLabel titleLabel = new JLabel("Keranjang", SwingConstants.CENTER);
        titleLabel.setFont(new Font("Arial", Font.BOLD, 24));
        titleLabel.setForeground(Color.ORANGE);
        titlePanel.add(titleLabel, BorderLayout.CENTER);

        add(titlePanel, BorderLayout.NORTH);

        // Panel isi produk
        JPanel itemsPanel = new JPanel();
        itemsPanel.setLayout(new BoxLayout(itemsPanel, BoxLayout.Y_AXIS));
        itemsPanel.setBorder(BorderFactory.createEmptyBorder(10, 20, 10, 20));
        itemsPanel.setBackground(new Color(153, 0, 0));

        if (products.length == 0) {
            JLabel kosongLabel = new JLabel("Keranjang kosong.");
            kosongLabel.setForeground(Color.WHITE);
            kosongLabel.setFont(new Font("Arial", Font.PLAIN, 16));
            itemsPanel.add(kosongLabel);
        } else {
            for (Product product : products) {
                itemsPanel.add(createItemPanel(product));
                itemsPanel.add(Box.createRigidArea(new Dimension(0, 10)));
            }
        }

        // Panel total dan tombol
        JPanel totalPanel = new JPanel(new FlowLayout(FlowLayout.RIGHT));
        totalPanel.setBorder(BorderFactory.createEmptyBorder(10, 20, 10, 20));
        totalPanel.setBackground(new Color(153, 0, 0));
        totalLabel = new JLabel("Total    Rp. " + formatPrice(calculateTotal()));
        totalLabel.setFont(new Font("Arial", Font.BOLD, 16));
        totalLabel.setForeground(Color.WHITE);
        totalPanel.add(totalLabel);

        JPanel buttonPanel = new JPanel();
        buttonPanel.setBorder(BorderFactory.createEmptyBorder(10, 20, 20, 20));
        buttonPanel.setBackground(new Color(153, 0, 0));
        JButton bayarButton = new JButton("Pesan");
        styleButton(bayarButton, Color.ORANGE);
        bayarButton.setForeground(new Color(153, 0, 0));
        bayarButton.addActionListener(e -> showOrderSummary());
        buttonPanel.add(bayarButton);

        JPanel mainPanel = new JPanel();
        mainPanel.setLayout(new BoxLayout(mainPanel, BoxLayout.Y_AXIS));
        mainPanel.setBackground(new Color(153, 0, 0));
        mainPanel.add(itemsPanel);
        mainPanel.add(totalPanel);
        mainPanel.add(buttonPanel);

        JScrollPane scrollPane = new JScrollPane(mainPanel);
        scrollPane.getViewport().setBackground(new Color(153, 0, 0));
        add(scrollPane, BorderLayout.CENTER);
    }

    //method untuk produk
    private JPanel createItemPanel(Product product) {
        JPanel panel = new JPanel(new BorderLayout(10, 0));
        panel.setBackground(new Color(153, 0, 0));
        panel.setBorder(BorderFactory.createEmptyBorder(5, 5, 5, 5));
        panel.setPreferredSize(new Dimension(400, 100));

        JPanel leftPanel = new JPanel();
        leftPanel.setLayout(new BoxLayout(leftPanel, BoxLayout.X_AXIS));
        leftPanel.setBackground(new Color(153, 0, 0));

        JCheckBox checkBox = new JCheckBox();
        checkBox.setBackground(new Color(153, 0, 0));
        checkBox.setSelected(false); // jadi tidak otomatis
        checkBox.setForeground(Color.ORANGE);
        checkBox.addActionListener(e -> {
            product.setSelected(checkBox.isSelected());
            updateTotal();
        });

        JLabel imageLabel;
        try {
            ImageIcon icon = new ImageIcon(product.getImagePath());
            Image scaledImage = icon.getImage().getScaledInstance(60, 60, Image.SCALE_SMOOTH);
            imageLabel = new JLabel(new ImageIcon(scaledImage));
        } catch (Exception e) {
            imageLabel = new JLabel();
        }

        JPanel textPanel = new JPanel(new GridLayout(2, 1));
        textPanel.setBackground(new Color(153, 0, 0));
        JLabel nameLabel = new JLabel(product.getName());
        nameLabel.setFont(new Font("Arial", Font.BOLD, 14));
        nameLabel.setForeground(Color.ORANGE);
        JLabel priceLabel = new JLabel("Rp. " + formatPrice(product.getPrice()));
        priceLabel.setFont(new Font("Arial", Font.PLAIN, 12));
        priceLabel.setForeground(Color.ORANGE);
        textPanel.add(nameLabel);
        textPanel.add(priceLabel);

        leftPanel.add(imageLabel);
        leftPanel.add(Box.createRigidArea(new Dimension(10, 0)));
        leftPanel.add(checkBox);
        leftPanel.add(Box.createRigidArea(new Dimension(10, 0)));
        leftPanel.add(textPanel);

        JPanel quantityPanel = new JPanel(new FlowLayout(FlowLayout.RIGHT));
        quantityPanel.setBackground(new Color(153, 0, 0));
        JButton minusButton = new JButton("-");
        styleQuantityButton(minusButton);
        JLabel quantityLabel = new JLabel(String.valueOf(product.getQuantity()));
        quantityLabel.setFont(new Font("Arial", Font.BOLD, 14));
        quantityLabel.setForeground(Color.ORANGE);
        JButton plusButton = new JButton("+");
        styleQuantityButton(plusButton);

        minusButton.addActionListener(e -> {
            if (product.getQuantity() > 1) {
                product.setQuantity(product.getQuantity() - 1);
                quantityLabel.setText(String.valueOf(product.getQuantity()));
                updateTotal();
            }
        });
        plusButton.addActionListener(e -> {
            product.setQuantity(product.getQuantity() + 1);
            quantityLabel.setText(String.valueOf(product.getQuantity()));
            updateTotal();
        });

        quantityPanel.add(minusButton);
        quantityPanel.add(quantityLabel);
        quantityPanel.add(plusButton);

        panel.add(leftPanel, BorderLayout.CENTER);
        panel.add(quantityPanel, BorderLayout.EAST);
        return panel;
    }

    private void styleQuantityButton(JButton button) {
        button.setFont(new Font("Arial", Font.BOLD, 14));
        button.setBackground(new Color(255, 140, 0));
        button.setForeground(Color.WHITE);
        button.setFocusPainted(false);
        button.setBorderPainted(false);
        button.setContentAreaFilled(true);
        button.setOpaque(true);
        button.setPreferredSize(new Dimension(50, 40));
        button.setCursor(new Cursor(Cursor.HAND_CURSOR));
    }

    private void updateTotal() {
        //polymorpisme override
        totalLabel.setText("Total    Rp. " + formatPrice(calculateTotal()));
    }

    private void showOrderSummary() {
        calculateTotal();
        if (selectedProducts.isEmpty()) {
            JOptionPane.showMessageDialog(this, "Pilih minimal 1 produk terlebih dahulu", "Peringatan", JOptionPane.WARNING_MESSAGE);
            return;
        }
        OrderSummaryUI orderSummary = new OrderSummaryUI(selectedProducts.toArray(new Product[0]), calculateTotal(), this);
        orderSummary.setVisible(true);
        this.setVisible(false);
    }

    private int calculateTotal() {
        int total = 0;
        selectedProducts.clear();
        for (Product product : products) {
            if (product.isSelected()) {
                total += product.getTotalPrice();
                selectedProducts.add(product);
            }
        }
        return total;
    }

    private void styleButton(JButton button, Color bgColor) {
        button.setFont(new Font("Arial", Font.BOLD, 16));
        button.setBackground(bgColor);
        button.setOpaque(true);
        button.setBorderPainted(false);
        button.setPreferredSize(new Dimension(100, 40));
    }

    private String formatPrice(int price) {
        return String.format("%,d", price).replace(',', '.') + ",-";
    }
}