const batas_usia = 18;
const batas_waktu_lari = 12;

    function apakahmemenuhisyaratwaktu(waktulari) {
        return waktulari < batas_waktu_lari;
    }

    function apakahditerima(usia, pernahlomba, memenuhisyaratwaktu) {
        return usia >= batas_usia && (pernahlomba || memenuhisyaratwaktu);
    }
    function cekkelayakan() {
        // Mengambil input dari pengguna
        let nama = document.getElementById("nama").value.trim();
        let usia = parseInt(document.getElementById("usia").value);
        let waktulari = parseFloat(document.getElementById("waktulari").value);
        let pernahlomba = document.getElementById("pernahlomba").checked;
        
        // Menampilkan data input pengguna
        document.writeln("Nama: " + nama);
        document.writeln("<br>");
        document.writeln("Usia: " + usia);
        document.writeln("<br>");
        document.writeln("Waktu Lari: " + waktulari);
        document.writeln("<br>");
        document.writeln("Pernah Lomba: " + pernahlomba);
        document.writeln("<br> <hr>");
        
        // Menentukan kelayakan berdasarkan syarat lomba
        let memenuhisyaratwaktu = apakahmemenuhisyaratwaktu(waktulari);
        let diterima = apakahditerima(usia, pernahlomba, memenuhisyaratwaktu);
        
        let hasilText = diterima ?
            `Selamat, ${nama}! anda diterima sebagai peserta lomba!` :
            `Maaf, ${nama} anda tidak memenuhi syarat untuk lomba.`;
        
        // Menampilkan hasil keputusan
        document.writeln("Hasil: " + hasilText);
        document.writeln("<br>");
        document.writeln("Memenuhi syarat waktu: " + memenuhisyaratwaktu);
        document.writeln("<br>");
        document.writeln("Diterima: " + diterima);
    }