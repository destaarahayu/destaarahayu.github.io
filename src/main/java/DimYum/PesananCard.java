package DimYum;

import javax.swing.*;
import javax.swing.border.*;
import java.awt.*;

public class PesananCard extends JPanel {
    public PesananCard(Pesanan pesanan) {
        setLayout(new BorderLayout());
        setBorder(new CompoundBorder(
                new EmptyBorder(10, 10, 10, 10),
                new LineBorder(Color.GRAY, 1, true)
        ));
        setBackground(Color.WHITE);

        JLabel lblTanggal = new JLabel("Tanggal: " + pesanan.getTanggal());
        JLabel lblMetode = new JLabel("Metode Pembayaran: " + pesanan.getMetodePembayaran());
        JLabel lblId = new JLabel("ID Pesanan: " + pesanan.getId());

        JPanel header = new JPanel(new GridLayout(3, 1));
        header.setOpaque(false);
        header.add(lblTanggal);
        header.add(lblMetode);
        header.add(lblId);

        add(header, BorderLayout.NORTH);

        JPanel itemPanel = new JPanel();
        itemPanel.setLayout(new BoxLayout(itemPanel, BoxLayout.Y_AXIS));
        itemPanel.setOpaque(false);

        int totalHarga = 0;

        for (Pesanan.ItemPesanan item : pesanan.getDaftarItem()) {
            int subtotal = item.getJumlah() * item.getHarga();
            totalHarga += subtotal;

            JLabel lblItem = new JLabel(item.getNamaProduk() + " x" + item.getJumlah() + " @Rp" + item.getHarga());
            itemPanel.add(lblItem);
        }

        JLabel lblTotal = new JLabel("Total Harga: Rp" + totalHarga);
        lblTotal.setFont(lblTotal.getFont().deriveFont(Font.BOLD));

        JPanel bottom = new JPanel(new BorderLayout());
        bottom.setOpaque(false);
        bottom.add(lblTotal, BorderLayout.EAST);

        add(itemPanel, BorderLayout.CENTER);
        add(bottom, BorderLayout.SOUTH);
    }
}
