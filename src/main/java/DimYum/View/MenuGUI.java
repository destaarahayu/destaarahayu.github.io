package DimYum.View;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.util.ArrayList;
import DimYum.Model.Makanan;
import DimYum.Model.Minuman;
import DimYum.Product;
import DimYum.ShoppingCartUI;
import DimYum.MainProfile;
import DimYum.MainGUI;

public class MenuGUI extends JFrame {
    private JPanel makananPanel;
    private JPanel minumanPanel;
    private CardLayout cardLayout;
    private JPanel contentPanel;
    private ArrayList<Product> cartProducts = new ArrayList<>();

    public MenuGUI() {
        setTitle("DimYum Menu");
        setSize(800, 600);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);
        setLayout(new BorderLayout());

        JPanel headerPanel = createHeaderPanel();
        add(headerPanel, BorderLayout.NORTH);

        JPanel switchPanel = createSwitchPanel();
        add(switchPanel, BorderLayout.BEFORE_FIRST_LINE);

        contentPanel = new JPanel();
        cardLayout = new CardLayout();
        contentPanel.setLayout(cardLayout);

        makananPanel = new JPanel(new GridBagLayout());
        makananPanel.setBackground(Color.WHITE);
        minumanPanel = new JPanel(new GridBagLayout());
        minumanPanel.setBackground(Color.WHITE);

        contentPanel.add(new JScrollPane(makananPanel), "Makanan");
        contentPanel.add(new JScrollPane(minumanPanel), "Minuman");

        add(contentPanel, BorderLayout.CENTER);
        JPanel navPanel = createNavPanel();
        add(navPanel, BorderLayout.PAGE_END);

        updateMakananList();
        updateMinumanList();
        cardLayout.show(contentPanel, "Makanan");
    }

    private JPanel createHeaderPanel() {
        JPanel panel = new JPanel(new BorderLayout());
        JLabel promoLabel = new JLabel("🎉 Promo Spesial! Beli 2 Gratis 1 semua dimsum & minuman! 🎉");
        promoLabel.setFont(new Font("Arial", Font.BOLD, 16));
        promoLabel.setForeground(Color.WHITE);
        promoLabel.setHorizontalAlignment(SwingConstants.CENTER);
        promoLabel.setBorder(BorderFactory.createEmptyBorder(15, 0, 15, 0));
        panel.setBackground(new Color(153, 0, 0));
        panel.add(promoLabel, BorderLayout.CENTER);
        return panel;
    }

    private JPanel createSwitchPanel() {
        JPanel panel = new JPanel();
        JButton btnMakanan = new JButton("Makanan");
        JButton btnMinuman = new JButton("Minuman");

        btnMakanan.setForeground(Color.WHITE);
        btnMinuman.setForeground(Color.WHITE);
        btnMakanan.setBackground(new Color(153, 0, 0));
        btnMinuman.setBackground(new Color(153, 0, 0));

        panel.setBackground(new Color(153, 0, 0));
        panel.add(btnMakanan);
        panel.add(btnMinuman);

        btnMakanan.addActionListener(e -> cardLayout.show(contentPanel, "Makanan"));
        btnMinuman.addActionListener(e -> cardLayout.show(contentPanel, "Minuman"));

        return panel;
    }

    private JPanel createNavPanel() {
        JPanel panel = new JPanel(new GridLayout(1, 4, 10, 0));
        panel.setBackground(new Color(153, 0, 0));
        panel.setPreferredSize(new Dimension(getWidth(), 80));

        JPanel userPanel = createNavItem("user.png", "User");
        userPanel.addMouseListener(new MouseAdapter() {
            public void mouseClicked(MouseEvent e) {
                openUserProfile();
            }
        });
        panel.add(userPanel);

        JPanel historyPanel = createNavItem("history.png", "History");
        historyPanel.addMouseListener(new MouseAdapter() {
            public void mouseClicked(MouseEvent e) {
                openHistoryPage();
            }
        });
        panel.add(historyPanel);

        JPanel cartPanel = createNavItem("keranjang.png", "Keranjang");
        cartPanel.addMouseListener(new MouseAdapter() {
            public void mouseClicked(MouseEvent e) {
                openCartPage();
            }
        });
        panel.add(cartPanel);

        return panel;
    }

    private void updateMakananList() {
        makananPanel.removeAll();
        ArrayList<Makanan> daftarMakanan = new ArrayList<>();
        daftarMakanan.add(new Makanan("Classic Charm", "Dimsum Original...", 20000, "classic_charm.jpg"));
        daftarMakanan.add(new Makanan("Fiery Mentai", "Dimsum Pedas Mentai", 30000, "feiry_mentai.jpg"));
        daftarMakanan.add(new Makanan("Savory Balo", "Dimsum Bolognese", 30000, "savory_balo.jpg"));
        daftarMakanan.add(new Makanan("Cheesey Melt", "Dimsum Lelehan Keju", 30000, "cheesy_melt.jpg"));
        daftarMakanan.add(new Makanan("Grilled Glow", "Dimsum Panggang Gurih", 25000, "grilled_glow.jpg"));

        GridBagConstraints gbc = new GridBagConstraints();
        gbc.insets = new Insets(10, 10, 10, 10);

        for (int i = 0; i < daftarMakanan.size(); i++) {
            Makanan m = daftarMakanan.get(i);
            JPanel itemPanel = createItemPanel(m);
            gbc.gridx = i % 2;
            gbc.gridy = i / 2;
            makananPanel.add(itemPanel, gbc);
        }

        makananPanel.revalidate();
        makananPanel.repaint();
    }

    private void updateMinumanList() {
        minumanPanel.removeAll();
        ArrayList<Minuman> daftarMinuman = new ArrayList<>();
        daftarMinuman.add(new Minuman("Brewed Calm", "Teh menenangkan", 15000, "EsTeh.jpg"));
        daftarMinuman.add(new Minuman("Matcha Muse", "Matcha creamy", 25000, "Matcha.jpg"));
        daftarMinuman.add(new Minuman("Choco Bliss", "Cokelat manis", 22000, "Chocolate.jpg"));
        daftarMinuman.add(new Minuman("Coffee Drift", "Kopi susu nikmat", 20000, "Kopi.jpg"));
        daftarMinuman.add(new Minuman("Citrus Spark", "Jeruk soda segar", 18000, "Jus.jpg"));

        GridBagConstraints gbc = new GridBagConstraints();
        gbc.insets = new Insets(10, 10, 10, 10);

        for (int i = 0; i < daftarMinuman.size(); i++) {
            Minuman m = daftarMinuman.get(i);
            JPanel itemPanel = createItemPanel(m);
            gbc.gridx = i % 2;
            gbc.gridy = i / 2;
            minumanPanel.add(itemPanel, gbc);
        }

        minumanPanel.revalidate();
        minumanPanel.repaint();
    }

    private JPanel createItemPanel(Object item) {
        String nama, deskripsi, imagePath;
        int harga;

        if (item instanceof Makanan m) {
            nama = m.getNama();
            deskripsi = m.getDeskripsi();
            harga = m.getHarga();
            imagePath = m.getImagePath();
        } else {
            Minuman m = (Minuman) item;
            nama = m.getNama();
            deskripsi = m.getDeskripsi();
            harga = m.getHarga();
            imagePath = m.getImagePath();
        }

        JPanel panel = new JPanel();
        panel.setPreferredSize(new Dimension(180, 200));
        panel.setLayout(new BorderLayout(5, 5));
        panel.setBackground(new Color(153, 0, 0));
        panel.setBorder(BorderFactory.createLineBorder(Color.ORANGE, 2, true));

        JLabel imgLabel = new JLabel();
        imgLabel.setHorizontalAlignment(JLabel.CENTER);
        try {
            ImageIcon icon = new ImageIcon(imagePath);
            Image img = icon.getImage().getScaledInstance(160, 100, Image.SCALE_SMOOTH);
            imgLabel.setIcon(new ImageIcon(img));
        } catch (Exception e) {
            imgLabel.setText("[img]");
        }
        imgLabel.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
        imgLabel.addMouseListener(new MouseAdapter() {
            public void mouseClicked(MouseEvent e) {
                openDetailPage(item);
            }
        });
        panel.add(imgLabel, BorderLayout.NORTH);

        JPanel infoPanel = new JPanel();
        infoPanel.setLayout(new BoxLayout(infoPanel, BoxLayout.Y_AXIS));
        infoPanel.setBackground(new Color(153, 0, 0));

        JLabel lblNama = new JLabel(nama);
        lblNama.setFont(new Font("Arial", Font.BOLD, 14));
        lblNama.setForeground(Color.WHITE);
        JLabel lblDesc = new JLabel("<html><small>" + deskripsi + "</small></html>");
        lblDesc.setForeground(Color.WHITE);
        JLabel lblHarga = new JLabel("Rp. " + harga);
        lblHarga.setForeground(Color.WHITE);

        infoPanel.add(lblNama);
        infoPanel.add(lblDesc);
        infoPanel.add(lblHarga);

        panel.add(infoPanel, BorderLayout.CENTER);

        JButton plusButton = new JButton("+");
        plusButton.setMargin(new Insets(2, 5, 2, 5));
        plusButton.addActionListener(e -> addToCart(item));
        JPanel plusPanel = new JPanel(new FlowLayout(FlowLayout.RIGHT));
        plusPanel.setBackground(new Color(153, 0, 0));
        plusPanel.add(plusButton);
        panel.add(plusPanel, BorderLayout.SOUTH);

        return panel;
    }

    private JPanel createNavItem(String iconPath, String labelText) {
        JPanel panel = new JPanel();
        panel.setLayout(new BoxLayout(panel, BoxLayout.Y_AXIS));
        panel.setOpaque(false);
        panel.setBorder(BorderFactory.createEmptyBorder(5, 5, 5, 5));

        JLabel iconLabel = new JLabel();
        try {
            ImageIcon icon = new ImageIcon(iconPath);
            Image img = icon.getImage().getScaledInstance(30, 30, Image.SCALE_SMOOTH);
            iconLabel.setIcon(new ImageIcon(img));
        } catch (Exception e) {
            iconLabel.setText("[icon]");
        }
        iconLabel.setAlignmentX(Component.CENTER_ALIGNMENT);

        JLabel textLabel = new JLabel(labelText);
        textLabel.setForeground(Color.WHITE);
        textLabel.setFont(new Font("Arial", Font.BOLD, 12));
        textLabel.setAlignmentX(Component.CENTER_ALIGNMENT);

        panel.add(iconLabel);
        panel.add(Box.createVerticalStrut(5));
        panel.add(textLabel);

        return panel;
    }

    private void openDetailPage(Object item) {
        if (item instanceof Makanan m) {
            switch (m.getNama()) {
                case "Classic Charm" -> new DimYum.View.DimsumOriginal(this).setVisible(true);
                case "Fiery Mentai" -> new DimYum.View.DimsumMentai(this).setVisible(true);
                case "Savory Balo" -> new DimYum.View.DimsumBolognese(this).setVisible(true);
                case "Cheesey Melt" -> new DimYum.View.DimsumCheese(this).setVisible(true);
                case "Grilled Glow" -> new DimYum.View.DimsumBakar(this).setVisible(true);
            }
        } else if (item instanceof Minuman m) {
            switch (m.getNama()) {
                case "Brewed Calm" -> new DimYum.View.Esteh(this).setVisible(true);
                case "Matcha Muse" -> new DimYum.View.Matcha(this).setVisible(true);
                case "Choco Bliss" -> new DimYum.View.Chocolate(this).setVisible(true);
                case "Coffee Drift" -> new DimYum.View.Kopi(this).setVisible(true);
                case "Citrus Spark" -> new DimYum.View.Jus(this).setVisible(true);
            }
        }
    }

    private void addToCart(Object item) {
        String nama = "";
        int harga = 0;
        String imagePath = "";

        if (item instanceof Makanan m) {
            nama = m.getNama();
            harga = m.getHarga();
            imagePath = m.getImagePath();
        } else if (item instanceof Minuman m) {
            nama = m.getNama();
            harga = m.getHarga();
            imagePath = m.getImagePath();
        }

        for (Product p : cartProducts) {
            if (p.getName().equals(nama)) {
                p.setQuantity(p.getQuantity() + 1);
                JOptionPane.showMessageDialog(this, nama + " ditambahkan lagi ke keranjang!");
                return;
            }
        }

        cartProducts.add(new Product(nama, harga, 1, imagePath));
        JOptionPane.showMessageDialog(this, nama + " telah ditambahkan ke keranjang!");
    }

    private void openCartPage() {
        this.setVisible(false);
        Product[] productArray = cartProducts.toArray(new Product[0]);
        ShoppingCartUI cartUI = new ShoppingCartUI(productArray);
        cartUI.addWindowListener(new WindowAdapter() {
            @Override
            public void windowClosed(WindowEvent e) {
                setVisible(true);
            }
        });
        cartUI.setVisible(true);
    }

    private void openUserProfile() {
        this.setVisible(false);
        MainProfile profile = new MainProfile(this);
        profile.setVisible(true);
    }

    private void openHistoryPage() {
        this.setVisible(false);
        MainGUI historyPage = new MainGUI();
        historyPage.setVisible(true);
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> new MenuGUI().setVisible(true));
    }
}