package DimYum.Model;

public class Minuman {
    // Enkapsulasi - atribut disembunyikan (private)
    private String nama;
    private String deskripsi;
    private int harga;
    private String imagePath;

    // Konstruktor - digunakan untuk inisialisasi objek
    public Minuman(String nama, String deskripsi, int harga, String imagePath) {
        this.nama = nama;
        this.deskripsi = deskripsi;
        this.harga = harga;
        this.imagePath = imagePath;
    }

    // Enkapsulasi - akses melalui getter
    public String getNama() { return nama; }
    public String getDeskripsi() { return deskripsi; }
    public int getHarga() { return harga; }
    public String getImagePath() { return imagePath; }
}