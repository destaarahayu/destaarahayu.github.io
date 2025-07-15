package DimYum;

public class AkunUser implements ProfilUser {
    // enkapsulasi
    private String nama;
    private String email;
    private String noTelepon;
    private String jenisKelamin;
    private String tanggalLahir;
    private String kewarganegaraan;
    private String pekerjaan;

    //konstruktor
    public AkunUser(String nama, String email, String noTelepon, String jenisKelamin,
                    String tanggalLahir, String kewarganegaraan, String pekerjaan) {
        this.nama = nama;
        this.email = email;
        this.noTelepon = noTelepon;
        this.jenisKelamin = jenisKelamin;
        this.tanggalLahir = tanggalLahir;
        this.kewarganegaraan = kewarganegaraan;
        this.pekerjaan = pekerjaan;
    }

    // buat ngambil enkapsulasi Getter dan Setter
    public String getNama() { return nama; }
    public void setNama(String nama) { this.nama = nama; }

    public String getEmail() { return email; }
    public void setEmail(String email) { this.email = email; }

    public String getNoTelepon() { return noTelepon; }
    public void setNoTelepon(String noTelepon) { this.noTelepon = noTelepon; }

    public String getJenisKelamin() { return jenisKelamin; }
    public void setJenisKelamin(String jenisKelamin) { this.jenisKelamin = jenisKelamin; }

    public String getTanggalLahir() { return tanggalLahir; }
    public void setTanggalLahir(String tanggalLahir) { this.tanggalLahir = tanggalLahir; }

    public String getKewarganegaraan() { return kewarganegaraan; }
    public void setKewarganegaraan(String kewarganegaraan) { this.kewarganegaraan = kewarganegaraan; }

    public String getPekerjaan() { return pekerjaan; }
    public void setPekerjaan(String pekerjaan) { this.pekerjaan = pekerjaan; }
}