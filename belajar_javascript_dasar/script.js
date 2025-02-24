const batas_usia = 18;
const batas_waktu_lari = 12;

function apakahMemenuhiSyaratWaktu(waktuLari) {
    return waktuLari < batas_waktu_lari;
}

function apakahDiterima(usia, pernahLomba, memenuhiSyaratWaktu) {
    return usia >= batas_usia && (pernahLomba || memenuhiSyaratWaktu);
}

function cekKelayakan() {

    // Mengambil input dari pengguna dengan prompt dan confirm
    let nama = prompt("Masukkan nama anda:").trim();
    if (!nama) return;
    
    let usia = parseInt(prompt("Masukkan usia anda:"));
    if (isNaN(usia)) return;
    
    let waktuLari = parseFloat(prompt("Masukkan waktu lari anda dalam detik:"));
    if (isNaN(waktuLari)) return;
    
    let pernahLomba = confirm("Apakah anda pernah mengikuti lomba sebelumnya? (OK = Ya, Cancel = Tidak)");
    
    // Konfirmasi input
    let konfirmasi = confirm(
        `Konfirmasi Data Anda:\nNama: ${nama}\nUsia: ${usia}\nWaktu Lari: ${waktuLari} detik\nPernah Lomba: ${pernahLomba}\n\nApakah data sudah sesuai?`
    );
    
    if (!konfirmasi) return;
    
    // Menentukan kelayakan berdasarkan syarat lomba
    let memenuhiSyaratWaktu = apakahMemenuhiSyaratWaktu(waktuLari);
    let diterima = apakahDiterima(usia, pernahLomba, memenuhiSyaratWaktu);
    
    // Menampilkan hasil keputusan dengan alert
    if (diterima) {
        alert(`Selamat, ${nama}! anda diterima sebagai peserta lomba!`);
    } else {
        alert(`Maaf, ${nama}, anda tidak memenuhi syarat untuk lomba.`);
    }
} 

// Jalankan fungsi saat halaman dimuat
cekKelayakan();
