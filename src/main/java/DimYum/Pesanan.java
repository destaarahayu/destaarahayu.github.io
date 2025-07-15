package DimYum;

import java.util.List;

public class Pesanan {
    private String id;
    private String tanggal;
    private String metodePembayaran;
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

    // Inner Class (non-static)
    public class ItemPesanan {
        private String namaProduk;
        private int jumlah;
        private int harga;

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