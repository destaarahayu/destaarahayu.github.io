package DimYum;

import DimYum.View.MenuGUI;
import javax.swing.*;
import javax.swing.border.*;
import java.awt.*;
import java.util.*;
import java.util.List;

import static java.util.Arrays.asList;

public class MainGUI extends JFrame {

    private static List<Pesanan> semuaPesananDinamis = new ArrayList<>();

    public static void tambahRiwayatPesanan(Pesanan p) {
        semuaPesananDinamis.add(p);
    }

    public MainGUI() {
        setTitle("Riwayat Pemesanan Dimyum");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setSize(500, 700);
        setLocationRelativeTo(null);

        JPanel backgroundPanel = new JPanel();
        backgroundPanel.setBackground(new Color(0x7E, 0x08, 0x08));
        backgroundPanel.setLayout(new BoxLayout(backgroundPanel, BoxLayout.Y_AXIS));
        backgroundPanel.setBorder(new EmptyBorder(20, 20, 20, 20));

        JPanel headerPanel = new JPanel(new BorderLayout());
        headerPanel.setBackground(new Color(0x7E, 0x08, 0x08));
        headerPanel.setBorder(new EmptyBorder(0, 0, 20, 0));

        JLabel backLabel = new JLabel("←");
        backLabel.setFont(new Font("SansSerif", Font.BOLD, 35));
        backLabel.setForeground(Color.WHITE);
        backLabel.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
        backLabel.setBorder(new EmptyBorder(0, 10, 10, 10));
        backLabel.addMouseListener(new java.awt.event.MouseAdapter() {
            @Override
            public void mouseClicked(java.awt.event.MouseEvent e) {
                dispose();
                new MenuGUI().setVisible(true);
            }
        });
        headerPanel.add(backLabel, BorderLayout.WEST);

        JPanel centerHeader = new JPanel();
        centerHeader.setBackground(new Color(0x7E, 0x08, 0x08));
        centerHeader.setLayout(new BoxLayout(centerHeader, BoxLayout.Y_AXIS));

        JLabel titleLabel = new JLabel("Riwayat Pemesanan");
        titleLabel.setForeground(Color.WHITE);
        titleLabel.setFont(new Font("Arial", Font.BOLD, 24));
        titleLabel.setAlignmentX(Component.CENTER_ALIGNMENT);
        titleLabel.setBorder(new EmptyBorder(0, 0, 10, 0));

        try {
            ImageIcon originalIcon = new ImageIcon("logoDimYum.jpg");
            Image scaledImage = originalIcon.getImage().getScaledInstance(100, 100, Image.SCALE_SMOOTH);
            JLabel logoLabel = new JLabel(new ImageIcon(scaledImage));
            logoLabel.setAlignmentX(Component.CENTER_ALIGNMENT);

            centerHeader.add(titleLabel);
            centerHeader.add(logoLabel);
        } catch (Exception ex) {
            System.err.println("Gagal memuat logo: " + ex.getMessage());
            centerHeader.add(titleLabel);
        }

        headerPanel.add(centerHeader, BorderLayout.CENTER);
        backgroundPanel.add(Box.createVerticalStrut(10));
        backgroundPanel.add(headerPanel);

        JScrollPane scrollPane = new JScrollPane(backgroundPanel);
        scrollPane.setBorder(null);
        scrollPane.getVerticalScrollBar().setUnitIncrement(16);
        add(scrollPane);

        // Dummy data (optional - boleh dihapus jika hanya ingin tampilkan data dinamis)
        if (semuaPesananDinamis.isEmpty()) {
            Pesanan pesanan1 = new Pesanan("001", "2025-05-19", "Tunai", new ArrayList<>());
            pesanan1.getDaftarItem().add(pesanan1.new ItemPesanan("Classic Charm", 2, 20000));
            semuaPesananDinamis.add(pesanan1);
        }

        for (Pesanan p : semuaPesananDinamis) {
            backgroundPanel.add(Box.createVerticalStrut(15));
            backgroundPanel.add(new PesananCard(p));
        }
    }

    public static class Pesanan {
        private String id, tanggal, metodePembayaran;
        private List<ItemPesanan> daftarItem;

        public Pesanan(String id, String tanggal, String metodePembayaran, List<ItemPesanan> daftarItem) {
            this.id = id;
            this.tanggal = tanggal;
            this.metodePembayaran = metodePembayaran;
            this.daftarItem = daftarItem;
        }

        public String getId() { return id; }
        public String getTanggal() { return tanggal; }
        public String getMetodePembayaran() { return metodePembayaran; }
        public List<ItemPesanan> getDaftarItem() { return daftarItem; }

        public class ItemPesanan {
            private String namaProduk;
            private int jumlah, harga;

            public ItemPesanan(String namaProduk, int jumlah, int harga) {
                this.namaProduk = namaProduk;
                this.jumlah = jumlah;
                this.harga = harga;
            }

            public String getNamaProduk() { return namaProduk; }
            public int getJumlah() { return jumlah; }
            public int getHarga() { return harga; }
        }
    }

    public class PesananCard extends JPanel {
        public PesananCard(Pesanan pesanan) {
            setLayout(new BorderLayout());
            setBackground(Color.WHITE);
            setBorder(new CompoundBorder(
                    new EmptyBorder(10, 10, 10, 10),
                    new LineBorder(new Color(220, 220, 220), 1, true)
            ));

            JLabel lblHeader = new JLabel("Pesanan Selesai");
            lblHeader.setFont(new Font("SansSerif", Font.BOLD, 16));
            lblHeader.setForeground(Color.WHITE);
            lblHeader.setHorizontalAlignment(SwingConstants.CENTER);

            JPanel header = new JPanel();
            header.setBackground(new Color(255, 172, 9));
            header.setLayout(new BorderLayout());
            header.setPreferredSize(new Dimension(100, 40));
            header.setBorder(new CompoundBorder(
                    new EmptyBorder(5, 10, 5, 10),
                    new LineBorder(new Color(255, 172, 9), 1, true)
            ));
            header.add(lblHeader, BorderLayout.CENTER);
            add(header, BorderLayout.NORTH);

            JPanel content = new JPanel();
            content.setBackground(Color.WHITE);
            content.setLayout(new BoxLayout(content, BoxLayout.Y_AXIS));
            content.setBorder(new EmptyBorder(10, 5, 10, 5));

            JLabel lblId = new JLabel("ID Pesanan: " + pesanan.getId());
            JLabel lblTanggal = new JLabel("Tanggal: " + pesanan.getTanggal());
            JLabel lblPembayaran = new JLabel("Metode: " + pesanan.getMetodePembayaran());

            for (JLabel label : List.of(lblId, lblTanggal, lblPembayaran)) {
                label.setFont(new Font("SansSerif", Font.PLAIN, 12));
                label.setForeground(new Color(60, 60, 60));
                content.add(label);
            }

            content.add(Box.createVerticalStrut(10));
            content.add(new JSeparator());

            int total = 0;
            for (Pesanan.ItemPesanan item : pesanan.getDaftarItem()) {
                int subtotal = item.getJumlah() * item.getHarga();
                total += subtotal;

                JPanel itemPanel = new JPanel(new BorderLayout());
                itemPanel.setBackground(Color.WHITE);
                itemPanel.setBorder(new EmptyBorder(5, 0, 5, 0));

                JLabel namaProduk = new JLabel(item.getNamaProduk() + " x" + item.getJumlah());
                namaProduk.setFont(new Font("SansSerif", Font.PLAIN, 13));

                JLabel harga = new JLabel("Rp" + subtotal);
                harga.setFont(new Font("SansSerif", Font.PLAIN, 13));
                harga.setHorizontalAlignment(SwingConstants.RIGHT);

                itemPanel.add(namaProduk, BorderLayout.WEST);
                itemPanel.add(harga, BorderLayout.EAST);

                content.add(itemPanel);
            }

            content.add(Box.createVerticalStrut(10));
            content.add(new JSeparator());

            JPanel totalPanel = new JPanel(new BorderLayout());
            totalPanel.setBackground(Color.WHITE);
            totalPanel.setBorder(new EmptyBorder(5, 0, 0, 0));

            JLabel lblTotal = new JLabel("Total");
            lblTotal.setFont(new Font("SansSerif", Font.BOLD, 14));

            JLabel lblHargaTotal = new JLabel("Rp" + total);
            lblHargaTotal.setFont(new Font("SansSerif", Font.BOLD, 14));
            lblHargaTotal.setHorizontalAlignment(SwingConstants.RIGHT);

            totalPanel.add(lblTotal, BorderLayout.WEST);
            totalPanel.add(lblHargaTotal, BorderLayout.EAST);

            content.add(totalPanel);
            add(content, BorderLayout.CENTER);
        }
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> new MainGUI().setVisible(true));
    }
}
