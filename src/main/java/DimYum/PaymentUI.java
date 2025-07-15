package DimYum;

import javax.swing.*;
import java.awt.*;
import java.util.*;
import java.util.List;

public class PaymentUI extends JFrame {

    //constructor overloading
    public PaymentUI(Product[] products, int total, ShoppingCartUI parentCart) {
        initializeUI(products, total, parentCart);
        setVisible(true);
    }
    //constructor
    public PaymentUI(Product[] products, int total) {
        initializeUI(products, total, null);
        setVisible(true);
    }

    //method
    private void initializeUI(Product[] products, int total, ShoppingCartUI parentCart) {
        setTitle("Pembayaran");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setSize(400, 500);
        getContentPane().setBackground(new Color(153, 0, 0));
        setLayout(new BorderLayout());

        JPanel titlePanel = new JPanel();
        titlePanel.setBackground(new Color(153, 0, 0));
        JLabel titleLabel = new JLabel("Pembayaran");
        titleLabel.setFont(new Font("Arial", Font.BOLD, 24));
        titleLabel.setForeground(Color.ORANGE);
        titlePanel.add(titleLabel);
        add(titlePanel, BorderLayout.NORTH);

        JPanel orderPanel = new JPanel();
        orderPanel.setLayout(new BoxLayout(orderPanel, BoxLayout.Y_AXIS));
        orderPanel.setBorder(BorderFactory.createEmptyBorder(20, 20, 20, 20));
        orderPanel.setBackground(new Color(153, 0, 0));

        for (Product product : products) {
            orderPanel.add(createOrderItem(product));
            orderPanel.add(Box.createRigidArea(new Dimension(0, 10)));
        }

        JPanel totalPanel = new JPanel(new FlowLayout(FlowLayout.RIGHT));
        totalPanel.setBackground(new Color(153, 0, 0));
        JLabel totalLabel = new JLabel("Total Pembayaran: Rp. " + formatPrice(total));
        totalLabel.setFont(new Font("Arial", Font.BOLD, 16));
        totalLabel.setForeground(Color.ORANGE);
        totalPanel.add(totalLabel);
        orderPanel.add(totalPanel);

        JPanel paymentMethodPanel = new JPanel();
        paymentMethodPanel.setLayout(new BoxLayout(paymentMethodPanel, BoxLayout.Y_AXIS));
        paymentMethodPanel.setBorder(BorderFactory.createTitledBorder("Metode Pembayaran"));
        paymentMethodPanel.setBackground(new Color(153, 0, 0));
        ((javax.swing.border.TitledBorder) paymentMethodPanel.getBorder()).setTitleColor(Color.WHITE);

        ButtonGroup paymentGroup = new ButtonGroup();
        JRadioButton bankTransfer = createRadioButton("Transfer Bank");
        JRadioButton cash = createRadioButton("Tunai");

        paymentGroup.add(bankTransfer);
        paymentGroup.add(cash);

        paymentMethodPanel.add(bankTransfer);
        paymentMethodPanel.add(cash);

        JButton confirmButton = new JButton("Konfirmasi Pembayaran");
        confirmButton.setFont(new Font("Arial", Font.BOLD, 16));
        confirmButton.setBackground(Color.ORANGE);
        confirmButton.setForeground(new Color(153, 0, 0));
        confirmButton.setOpaque(true);
        confirmButton.setBorderPainted(false);
        confirmButton.addActionListener(e -> {
            String metode = bankTransfer.isSelected() ? "Transfer Bank" : "Tunai";

            List<MainGUI.Pesanan.ItemPesanan> itemList = new ArrayList<>();
            MainGUI.Pesanan pesananBaru = new MainGUI.Pesanan(
                    UUID.randomUUID().toString().substring(0, 5),
                    java.time.LocalDate.now().toString(),
                    metode,
                    itemList
            );

            for (Product product : products) {
                itemList.add(pesananBaru.new ItemPesanan(
                        product.getName(),
                        product.getQuantity(),
                        product.getPrice()
                ));
            }

            MainGUI.tambahRiwayatPesanan(pesananBaru);

            JOptionPane.showMessageDialog(this, "Pembayaran berhasil!", "Sukses", JOptionPane.INFORMATION_MESSAGE);
            new MainGUI().setVisible(true);
            this.dispose();
        });

        JPanel mainPanel = new JPanel();
        mainPanel.setLayout(new BoxLayout(mainPanel, BoxLayout.Y_AXIS));
        mainPanel.setBackground(new Color(153, 0, 0));
        mainPanel.add(orderPanel);

        JPanel centerPaymentPanel = new JPanel(new FlowLayout(FlowLayout.CENTER));
        centerPaymentPanel.setBackground(new Color(153, 0, 0));
        centerPaymentPanel.add(paymentMethodPanel);
        mainPanel.add(centerPaymentPanel);

        mainPanel.add(Box.createRigidArea(new Dimension(0, 20)));

        JPanel buttonPanel = new JPanel(new FlowLayout(FlowLayout.CENTER));
        buttonPanel.setBackground(new Color(153, 0, 0));
        buttonPanel.add(confirmButton);
        mainPanel.add(buttonPanel);

        add(mainPanel, BorderLayout.CENTER);
    }

    private JRadioButton createRadioButton(String text) {
        JRadioButton radioButton = new JRadioButton(text);
        radioButton.setBackground(new Color(153, 0, 0));
        radioButton.setForeground(Color.ORANGE);
        radioButton.setFont(new Font("Arial", Font.PLAIN, 14));
        return radioButton;
    }

    private JPanel createOrderItem(Product product) {
        JPanel panel = new JPanel(new BorderLayout());
        panel.setBackground(new Color(153, 0, 0));

        JPanel leftPanel = new JPanel(new BorderLayout(10, 0));
        leftPanel.setBackground(new Color(153, 0, 0));

        JLabel imageLabel;
        try {
            ImageIcon originalIcon = new ImageIcon(getClass().getResource("/" + product.getImagePath()));
            Image scaledImage = originalIcon.getImage().getScaledInstance(40, 40, Image.SCALE_SMOOTH);
            imageLabel = new JLabel(new ImageIcon(scaledImage));
        } catch (Exception ex) {
            imageLabel = new JLabel();
        }

        JLabel nameLabel = new JLabel(product.getName());
        nameLabel.setFont(new Font("Arial", Font.PLAIN, 14));
        nameLabel.setForeground(Color.ORANGE);

        leftPanel.add(imageLabel, BorderLayout.WEST);
        leftPanel.add(nameLabel, BorderLayout.CENTER);

        JPanel rightPanel = new JPanel(new FlowLayout(FlowLayout.RIGHT));
        rightPanel.setBackground(new Color(153, 0, 0));

        JLabel quantityLabel = new JLabel(product.getQuantity() + " x ");
        quantityLabel.setFont(new Font("Arial", Font.PLAIN, 12));
        quantityLabel.setForeground(Color.ORANGE);

        JLabel priceLabel = new JLabel("Rp. " + formatPrice(product.getPrice()));
        priceLabel.setFont(new Font("Arial", Font.PLAIN, 12));
        priceLabel.setForeground(Color.ORANGE);

        JLabel totalLabel = new JLabel("= Rp. " + formatPrice(product.getTotalPrice()));
        totalLabel.setFont(new Font("Arial", Font.PLAIN, 12));
        totalLabel.setForeground(Color.ORANGE);

        rightPanel.add(quantityLabel);
        rightPanel.add(priceLabel);
        rightPanel.add(totalLabel);

        panel.add(leftPanel, BorderLayout.WEST);
        panel.add(rightPanel, BorderLayout.EAST);

        return panel;
    }

    private String formatPrice(int price) {
        return String.format("%,d", price).replace(',', '.') + ",-";
    }
}