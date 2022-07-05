package fungsi;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;


/**
 *
 * @author Owner
 */

/**
 *
 * @author Owner
 */
public final class var {

    private static final Connection koneksi = koneksiDB.condb();
    private static PreparedStatement ps, ps2;
    private static ResultSet rs, rs2;

    private static String kode = "", kdbangsal = "", namars = "", alamatrs = "", kabupatenrs = "", propinsirs = "", kontakrs = "", emailrs = "",
            form = "", namauser = "", jenisLoket = "", nomorLoket = "";
    private static int jml1 = 0, jml2 = 0, lebar = 0, tinggi = 0;
    private static boolean aktif=false,admin = false, user = false, vakum = false, aplikasi = false, penyakit = false, obat_penyakit = false, dokter = false, jadwal_praktek = false, petugas = false, pasien = false, registrasi = false,
            tindakan_ralan = false, kamar_inap = false, tindakan_ranap = false, operasi = false, rujukan_keluar = false, rujukan_masuk = false, beri_obat = false,
            resep_pulang = false, pasien_meninggal = false, diet_pasien = false, kelahiran_bayi = false, periksa_lab = false, periksa_radiologi = false,
            kasir_ralan = false, deposit_pasien = false, piutang_pasien = false, peminjaman_berkas = false, barcode = false, presensi_harian = false,
            presensi_bulanan = false, pegawai_admin = false, pegawai_user = false, suplier = false, satuan_barang = false, konversi_satuan = false, jenis_barang = false,
            obat = false, stok_opname_obat = false, stok_obat_pasien = false, pengadaan_obat = false, pemesanan_obat = false, penjualan_obat = false, piutang_obat = false,
            retur_ke_suplier = false, retur_dari_pembeli = false, retur_obat_ranap = false, retur_piutang_pasien = false, keuntungan_penjualan = false, keuntungan_beri_obat = false,
            sirkulasi_obat = false, ipsrs_barang = false, ipsrs_jenis_barang = false, ipsrs_pengadaan_barang = false, ipsrs_stok_keluar = false, ipsrs_rekap_pengadaan = false, ipsrs_rekap_stok_keluar = false,
            ipsrs_pengeluaran_harian = false, inventaris_jenis = false, inventaris_kategori = false, inventaris_merk = false, inventaris_ruang = false, inventaris_produsen = false,
            inventaris_koleksi = false, inventaris_inventaris = false, inventaris_sirkulasi = false, parkir_jenis = false, parkir_in = false, parkir_out = false,
            parkir_rekap_harian = false, parkir_rekap_bulanan = false, informasi_kamar = false, harian_tindakan_poli = false, obat_per_poli = false, obat_per_kamar = false,
            obat_per_dokter_ralan = false, obat_per_dokter_ranap = false, harian_dokter = false, bulanan_dokter = false, harian_paramedis = false, bulanan_paramedis = false,
            pembayaran_ralan = false, pembayaran_ranap = false, rekap_pembayaran_ralan = false, rekap_pembayaran_ranap = false, tagihan_masuk = false, tambahan_biaya = false,
            potongan_biaya = false, resep_obat = false, resume_pasien = false, penyakit_ralan = false, penyakit_ranap = false, kamar = false, tarif_ralan = false, tarif_ranap = false,
            tarif_lab = false, tarif_radiologi = false, tarif_operasi = false, akun_rekening = false, rekening_tahun = false, posting_jurnal = false, buku_besar = false,
            cashflow = false, keuangan = false, pengeluaran = false, setup_pjlab = false, setup_otolokasi = false, setup_jam_kamin = false, setup_embalase = false, tracer_login = false,
            display = false, set_harga_obat = false, set_penggunaan_tarif = false, set_oto_ralan = false, biaya_harian = false, biaya_masuk_sekali = false, set_no_rm = false,
            billing_ralan = false, billing_ranap = false, status = false, jm_ranap_dokter = false, igd = false, barcoderalan = false, barcoderanap = false, set_harga_obat_ralan = false,
            set_harga_obat_ranap = false, penyakit_pd3i = false, surveilans_pd3i = false, surveilans_ralan = false, diagnosa_pasien = false, surveilans_ranap = false,
            pny_takmenular_ranap = false, pny_takmenular_ralan = false, kunjungan_ralan = false, rl32 = false, rl33 = false, rl37 = false, rl38 = false, harian_tindakan_dokter = false,
            sms = false, sidikjari = false, jam_masuk = false, jadwal_pegawai = false, parkir_barcode = false, set_nota = false, dpjp_ranap = false, mutasi_barang = false, rl34 = false, rl36 = false,
            fee_visit_dokter = false, fee_bacaan_ekg = false, fee_rujukan_rontgen = false, fee_rujukan_ranap = false, fee_ralan = false, akun_bayar = false, bayar_pemesanan_obat = false,
            obat_per_dokter_peresep = false, pemasukan_lain = false, pengaturan_rekening = false, closing_kasir = false, keterlambatan_presensi = false, set_harga_kamar = false,
            rekap_per_shift = false, bpjs_cek_nik = false, bpjs_cek_kartu = false, obat_per_cara_bayar = false, kunjungan_ranap = false, bayar_piutang = false,
            payment_point = false, bpjs_cek_nomor_rujukan = false, icd9 = false, darurat_stok = false, retensi_rm = false, temporary_presensi = false, jurnal_harian = false,
            sirkulasi_obat2 = false, edit_registrasi = false, bpjs_referensi_diagnosa = false, bpjs_referensi_poli = false, industrifarmasi = false, harian_js = false, bulanan_js = false,
            harian_paket_bhp = false, bulanan_paket_bhp = false, piutang_pasien2 = false, bpjs_referensi_faskes = false, bpjs_sep = false, pengambilan_utd = false, tarif_utd = false,
            pengambilan_utd2 = false, utd_medis_rusak = false, pengambilan_penunjang_utd = false, pengambilan_penunjang_utd2 = false, utd_penunjang_rusak = false,
            suplier_penunjang = false, utd_donor = false, bpjs_monitoring_klaim = false, utd_cekal_darah = false, utd_komponen_darah = false, utd_stok_darah = false,
            utd_pemisahan_darah = false, harian_kamar = false, rincian_piutang_pasien = false, keuntungan_beri_obat_nonpiutang = false, reklasifikasi_ralan = false,
            reklasifikasi_ranap = false, utd_penyerahan_darah = false, hutang_obat = false, riwayat_obat_alkes_bhp = false, sensus_harian_poli = false, rl4a = false,
            aplicare_referensi_kamar = false, aplicare_ketersediaan_kamar = false, inacbg_klaim_baru_otomatis = false, inacbg_klaim_baru_manual = false, inacbg_coder_nik = false,
            mutasi_berkas = false, akun_piutang = false, harian_kso = false, bulanan_kso = false, harian_menejemen = false, bulanan_menejemen = false, inhealth_cek_eligibilitas = false,
            inhealth_referensi_jenpel_ruang_rawat = false, inhealth_referensi_poli = false, inhealth_referensi_faskes = false, inhealth_sjp = false, piutang_ralan = false,
            piutang_ranap = false, detail_piutang_penjab = false, lama_pelayanan_ralan = false, catatan_pasien = false, rl4b = false, rl4asebab = false, rl4bsebab = false,
            data_HAIs = false, harian_HAIs = false, bulanan_HAIs = false, hitung_bor = false, perusahaan_pasien = false, resep_dokter = false, lama_pelayanan_apotek = false,
            hitung_alos = false, detail_tindakan = false, rujukan_poli_internal = false, rekap_poli_anak = false, grafik_kunjungan_poli = false, grafik_kunjungan_perdokter = false,
            grafik_kunjungan_perpekerjaan = false, grafik_kunjungan_perpendidikan = false, grafik_kunjungan_pertahun = false, berkas_digital_perawatan = false,
            penyakit_menular_ranap = false, penyakit_menular_ralan = false, grafik_kunjungan_perbulan = false, grafik_kunjungan_pertanggal = false, grafik_kunjungan_demografi = false,
            grafik_kunjungan_statusdaftartahun = false, grafik_kunjungan_statusdaftartahun2 = false, grafik_kunjungan_statusdaftarbulan = false, grafik_kunjungan_statusdaftarbulan2 = false,
            grafik_kunjungan_statusdaftartanggal = false, grafik_kunjungan_statusdaftartanggal2 = false, grafik_kunjungan_statusbataltahun = false, grafik_kunjungan_statusbatalbulan = false,
            pcare_cek_penyakit = false, grafik_kunjungan_statusbataltanggal = false, kategori_barang = false, golongan_barang = false, pemberian_obat_pertanggal = false, penjualan_obat_pertanggal = false,
	    bpjs_rujukan_vclaim=false,skdp_bpjs=false,booking_registrasi=false,bpjs_cek_riwayat_rujukan_pcare=false,bpjs_cek_riwayat_rujukan_rs=false,bpjs_cek_rujukan_kartu_rs=false,
            bpjs_cek_tgl_rujukan=false,bpjs_cek_no_rujukan_rs=false,bpjs_cek_rujukan_kartu_pcare=false,bpjs_cek_referensi_kelas_rawat=false,bpjs_cek_referensi_prosedur=false,
            bpjs_cek_referensi_dpjp=false,bpjs_cek_referensi_dokter=false,bpjs_cek_referensi_spesialistik=false,bpjs_cek_referensi_ruang_rawat=false,bpjs_cek_referensi_cara_keluar=false,
            bpjs_cek_referensi_pasca_pulang=false,bpjs_cek_referensi_propinsi=false,bpjs_cek_referensi_kabupaten=false,bpjs_cek_referensi_kecamatan=false,permintaan_lab=false,
            permintaan_radiologi=false,selisih_tarif_bpjs=false,edit_data_kematian=false,bridging_jamkesda=false,masuk_pindah_pulang_inap=false,masuk_pindah_inap=false,
            jumlah_macam_diet=false,jumlah_porsi_diet=false,status_gizi=false,gizi_buruk=false,master_faskes,setstatusralan=false,telusurpasien=false,sisrute_rujukan_keluar=false,sisrute_rujukan_masuk=false,
            sisrute_referensi_diagnosa=false,sisrute_referensi_alasanrujuk=false,sisrute_referensi_faskes=false,barang_cssd=false,status_pulang_inap=false,data_persalinan=false,data_ponek=false,
            reg_boking_kasir=false,bahasa_pasien=false,suku_bangsa=false,harian_hais_inap=false,harian_hais_jalan=false,bulanan_hais_inap=false,bulanan_hais_jalan=false,ringkasan_pulang_ranap=false,
            laporan_farmasi=false,master_masalah_keperawatan=false,penilaian_awal_keperawatan_ralan=false,master_triase_skala1=false,master_triase_skala2=false,
            master_triase_skala3=false,master_triase_skala4=false,master_triase_skala5=false, data_triase_igd = false, master_triase_pemeriksaan = false, master_triase_macamkasus = false, master_cara_bayar = false,
            status_kerja_dokter = false, pasien_corona = false, diagnosa_pasien_corona = false, perawatan_pasien_corona = false, inacbg_klaim_baru_manual2 = false, assesmen_gizi_harian = false, assesmen_gizi_ulang = false,
            tombol_nota_billing = false, tombol_simpan_hasil_rad = false, monev_asuhan_gizi = false, inacbg_klaim_raza = false, pengajuan_klaim_inacbg_raza = false,
            copy_pemeriksaan_dokter_kepetugas_ralan = false, jkn_belum_diproses_klaim = false, input_kode_icd = false, kendali_Mutu_kendali_Biaya_INACBG = false, dashboard_eResep = false, bpjs_sep_internal = false,
            kemenkes_sitt = false, rencana_kontrol_jkn = false, spri_jkn = false, hapus_sep = false, penilaian_awal_medis_ralan_kebidanan = false, penilaian_awal_keperawatan_kebidanan = false,
            ikhtisar_perawatan_hiv = false, survey_kepuasan = false, kemenkes_kanker=false, aktivasi_bridging=false, operator_antrian=false;

    public static void setData(String user, String pass) {
        try {
            ps = koneksi.prepareStatement("select * from admin where usere=AES_ENCRYPT(?,'nur') and passworde=AES_ENCRYPT(?,'windi')");
            ps2 = koneksi.prepareStatement("select * from user u left join petugas p on p.nip = AES_DECRYPT(u.id_user,'nur') left join dokter d on d.kd_dokter = AES_DECRYPT(u.id_user, 'nur') where u.id_user=AES_ENCRYPT(?,'nur') and u.password=AES_ENCRYPT(?,'windi')");
            try {
                ps.setString(1, user);
                ps.setString(2, pass);
                rs = ps.executeQuery();
                rs.last();

                ps2.setString(1, user);
                ps2.setString(2, pass);
                rs2 = ps2.executeQuery();
                rs2.last();

                var.jml1 = rs.getRow();
                var.jml2 = rs2.getRow();

                if (user.equals("admin") && pass.equals("satu")) {
                    var.kode = "Admin Utama";
                    var.penyakit = true;
                    var.obat_penyakit = true;
                    var.dokter = true;
                    var.jadwal_praktek = true;
                    var.petugas = true;
                    var.pasien = true;
                    var.registrasi = true;
                    var.tindakan_ralan = true;
                    var.kamar_inap = true;
                    var.tindakan_ranap = true;
                    var.operasi = true;
                    var.rujukan_keluar = true;
                    var.rujukan_masuk = true;
                    var.beri_obat = true;
                    var.resep_pulang = true;
                    var.pasien_meninggal = true;
                    var.diet_pasien = true;
                    var.kelahiran_bayi = true;
                    var.periksa_lab = true;
                    var.periksa_radiologi = true;
                    var.kasir_ralan = true;
                    var.deposit_pasien = true;
                    var.piutang_pasien = true;
                    var.peminjaman_berkas = true;
                    var.barcode = true;
                    var.presensi_harian = true;
                    var.presensi_bulanan = true;
                    var.pegawai_admin = true;
                    var.pegawai_user = true;
                    var.suplier = true;
                    var.satuan_barang = true;
                    var.konversi_satuan = true;
                    var.jenis_barang = true;
                    var.obat = true;
                    var.stok_opname_obat = true;
                    var.stok_obat_pasien = true;
                    var.pengadaan_obat = true;
                    var.pemesanan_obat = true;
                    var.penjualan_obat = true;
                    var.piutang_obat = true;
                    var.retur_ke_suplier = true;
                    var.retur_dari_pembeli = true;
                    var.retur_obat_ranap = true;
                    var.retur_piutang_pasien = true;
                    var.keuntungan_penjualan = true;
                    var.keuntungan_beri_obat = true;
                    var.sirkulasi_obat = true;
                    var.ipsrs_barang = true;
                    var.ipsrs_jenis_barang = true;
                    var.ipsrs_pengadaan_barang = true;
                    var.ipsrs_stok_keluar = true;
                    var.ipsrs_rekap_pengadaan = true;
                    var.ipsrs_rekap_stok_keluar = true;
                    var.ipsrs_pengeluaran_harian = true;
                    var.inventaris_jenis = true;
                    var.inventaris_kategori = true;
                    var.inventaris_merk = true;
                    var.inventaris_ruang = true;
                    var.inventaris_produsen = true;
                    var.inventaris_koleksi = true;
                    var.inventaris_inventaris = true;
                    var.inventaris_sirkulasi = true;
                    var.parkir_jenis = true;
                    var.parkir_in = true;
                    var.parkir_out = true;
                    var.parkir_rekap_harian = true;
                    var.parkir_rekap_bulanan = true;
                    var.informasi_kamar = true;
                    var.harian_tindakan_poli = true;
                    var.obat_per_poli = true;
                    var.obat_per_kamar = true;
                    var.obat_per_dokter_ralan = true;
                    var.obat_per_dokter_ranap = true;
                    var.harian_dokter = true;
                    var.bulanan_dokter = true;
                    var.harian_paramedis = true;
                    var.bulanan_paramedis = true;
                    var.pembayaran_ralan = true;
                    var.pembayaran_ranap = true;
                    var.rekap_pembayaran_ralan = true;
                    var.rekap_pembayaran_ranap = true;
                    var.tagihan_masuk = true;
                    var.tambahan_biaya = true;
                    var.potongan_biaya = true;
                    var.resep_obat = true;
                    var.resume_pasien = true;
                    var.penyakit_ralan = true;
                    var.penyakit_ranap = true;
                    var.kamar = true;
                    var.tarif_ralan = true;
                    var.tarif_ranap = true;
                    var.tarif_lab = true;
                    var.tarif_radiologi = true;
                    var.tarif_operasi = true;
                    var.akun_rekening = true;
                    var.rekening_tahun = true;
                    var.posting_jurnal = true;
                    var.buku_besar = true;
                    var.cashflow = true;
                    var.keuangan = true;
                    var.pengeluaran = true;
                    var.setup_pjlab = true;
                    var.setup_otolokasi = true;
                    var.setup_jam_kamin = true;
                    var.setup_embalase = true;
                    var.tracer_login = true;
                    var.display = true;
                    var.set_harga_obat = true;
                    var.set_penggunaan_tarif = true;
                    var.set_oto_ralan = true;
                    var.biaya_harian = true;
                    var.biaya_masuk_sekali = true;
                    var.set_no_rm = true;
                    var.billing_ralan = true;
                    var.billing_ranap = true;
                    var.jm_ranap_dokter = true;
                    var.igd = true;
                    var.barcoderalan = true;
                    var.barcoderanap = true;
                    var.set_harga_obat_ralan = true;
                    var.set_harga_obat_ranap = true;
                    var.penyakit_pd3i = true;
                    var.surveilans_pd3i = true;
                    var.surveilans_ralan = true;
                    var.diagnosa_pasien = true;
                    var.admin = true;
                    var.user = true;
                    var.vakum = true;
                    var.aplikasi = true;
                    var.surveilans_ranap = true;
                    var.pny_takmenular_ranap = true;
                    var.pny_takmenular_ralan = true;
                    var.kunjungan_ralan = true;
                    var.rl32 = true;
                    var.rl33 = true;
                    var.rl37 = true;
                    var.rl38 = true;
                    var.harian_tindakan_dokter = true;
                    var.sms = true;
                    var.sidikjari = true;
                    var.jam_masuk = true;
                    var.jadwal_pegawai = true;
                    var.parkir_barcode = true;
                    var.set_nota = true;
                    var.dpjp_ranap = true;
                    var.mutasi_barang = true;
                    var.rl34 = true;
                    var.rl36 = true;
                    var.fee_visit_dokter = true;
                    var.fee_bacaan_ekg = true;
                    var.fee_rujukan_rontgen = true;
                    var.fee_rujukan_ranap = true;
                    var.fee_ralan = true;
                    var.akun_bayar = true;
                    var.bayar_pemesanan_obat = true;
                    var.obat_per_dokter_peresep = true;
                    var.pemasukan_lain = true;
                    var.pengaturan_rekening = true;
                    var.closing_kasir = true;
                    var.keterlambatan_presensi = true;
                    var.set_harga_kamar = true;
                    var.rekap_per_shift = true;
                    var.bpjs_cek_nik = true;
                    var.bpjs_cek_kartu = true;
                    var.obat_per_cara_bayar = true;
                    var.kunjungan_ranap = true;
                    var.bayar_piutang = true;
                    var.payment_point = true;
                    var.bpjs_cek_nomor_rujukan = true;
                    var.icd9 = true;
                    var.darurat_stok = true;
                    var.retensi_rm = true;
                    var.temporary_presensi = true;
                    var.jurnal_harian = true;
                    var.sirkulasi_obat2 = true;
                    var.edit_registrasi = true;
                    var.bpjs_referensi_diagnosa = true;
                    var.bpjs_referensi_poli = true;
                    var.industrifarmasi = true;
                    var.harian_js = true;
                    var.bulanan_js = true;
                    var.harian_paket_bhp = true;
                    var.bulanan_paket_bhp = true;
                    var.piutang_pasien2 = true;
                    var.bpjs_referensi_faskes = true;
                    var.bpjs_sep = true;
                    var.pengambilan_utd = true;
                    var.tarif_utd = true;
                    var.pengambilan_utd2 = true;
                    var.utd_medis_rusak = true;
                    var.pengambilan_penunjang_utd = true;
                    var.pengambilan_penunjang_utd2 = true;
                    var.utd_penunjang_rusak = true;
                    var.suplier_penunjang = true;
                    var.utd_donor = true;
                    var.bpjs_monitoring_klaim = true;
                    var.utd_cekal_darah = true;
                    var.utd_komponen_darah = true;
                    var.utd_stok_darah = true;
                    var.utd_pemisahan_darah = true;
                    var.harian_kamar = true;
                    var.rincian_piutang_pasien = true;
                    var.keuntungan_beri_obat_nonpiutang = true;
                    var.reklasifikasi_ralan = true;
                    var.reklasifikasi_ranap = true;
                    var.utd_penyerahan_darah = true;
                    var.hutang_obat = true;
                    var.riwayat_obat_alkes_bhp = true;
                    var.sensus_harian_poli = true;
                    var.rl4a = true;
                    var.aplicare_referensi_kamar = true;
                    var.aplicare_ketersediaan_kamar = true;
                    var.inacbg_klaim_baru_otomatis = true;
                    var.inacbg_klaim_baru_manual = true;
                    var.inacbg_coder_nik = true;
                    var.mutasi_berkas = true;
                    var.akun_piutang = true;
                    var.harian_kso = true;
                    var.bulanan_kso = true;
                    var.harian_menejemen = true;
                    var.bulanan_menejemen = true;
                    var.inhealth_cek_eligibilitas = true;
                    var.inhealth_referensi_jenpel_ruang_rawat = true;
                    var.inhealth_referensi_poli = true;
                    var.inhealth_referensi_faskes = true;
                    var.inhealth_sjp = true;
                    var.piutang_ralan = true;
                    var.piutang_ranap = true;
                    var.detail_piutang_penjab = true;
                    var.lama_pelayanan_ralan = true;
                    var.catatan_pasien = true;
                    var.rl4b = true;
                    var.rl4asebab = true;
                    var.rl4bsebab = true;
                    var.namauser = "Admin Utama";
                    var.data_HAIs = true;
                    var.harian_HAIs = true;
                    var.bulanan_HAIs = true;
                    var.hitung_bor = true;
                    var.perusahaan_pasien = true;
                    var.resep_dokter = true;
                    var.lama_pelayanan_apotek = true;
                    var.hitung_alos = true;
                    var.detail_tindakan = true;
                    var.rujukan_poli_internal = true;
                    var.rekap_poli_anak = true;
                    var.grafik_kunjungan_poli = true;
                    var.grafik_kunjungan_perdokter = true;
                    var.grafik_kunjungan_perpekerjaan = true;
                    var.grafik_kunjungan_perpendidikan = true;
                    var.grafik_kunjungan_pertahun = true;
                    var.berkas_digital_perawatan = true;
                    var.penyakit_menular_ranap = true;
                    var.penyakit_menular_ralan = true;
                    var.grafik_kunjungan_perbulan = true;
                    var.grafik_kunjungan_pertanggal = true;
                    var.grafik_kunjungan_demografi = true;
                    var.grafik_kunjungan_statusdaftartahun = true;
                    var.grafik_kunjungan_statusdaftartahun2 = true;
                    var.grafik_kunjungan_statusdaftarbulan = true;
                    var.grafik_kunjungan_statusdaftarbulan2 = true;
                    var.grafik_kunjungan_statusdaftartanggal = true;
                    var.grafik_kunjungan_statusdaftartanggal2 = true;
                    var.grafik_kunjungan_statusbataltahun = true;
                    var.grafik_kunjungan_statusbatalbulan = true;
                    var.pcare_cek_penyakit = true;
                    var.grafik_kunjungan_statusbataltanggal = true;
                    var.kategori_barang = true;
                    var.golongan_barang = true;
                    var.pemberian_obat_pertanggal = true;
                    var.penjualan_obat_pertanggal = true;
		    var.bpjs_rujukan_vclaim=true;
                    var.skdp_bpjs=true;
                    var.booking_registrasi=true;
                    var.bpjs_cek_riwayat_rujukan_pcare=true;
                    var.bpjs_cek_riwayat_rujukan_rs=true;
                    var.bpjs_cek_rujukan_kartu_rs=true;
                    var.bpjs_cek_tgl_rujukan=true;
                    var.bpjs_cek_no_rujukan_rs=true;
                    var.bpjs_cek_rujukan_kartu_pcare=true;
                    var.bpjs_cek_referensi_kelas_rawat=true;
                    var.bpjs_cek_referensi_prosedur=true;
                    var.bpjs_cek_referensi_dpjp=true;
                    var.bpjs_cek_referensi_dokter=true;
                    var.bpjs_cek_referensi_spesialistik=true;
                    var.bpjs_cek_referensi_ruang_rawat=true;
                    var.bpjs_cek_referensi_cara_keluar=true;
                    var.bpjs_cek_referensi_pasca_pulang=true;
                    var.bpjs_cek_referensi_propinsi=true;
                    var.bpjs_cek_referensi_kabupaten=true;
                    var.bpjs_cek_referensi_kecamatan=true;
                    var.permintaan_lab=true;
                    var.permintaan_radiologi=true;
                    var.selisih_tarif_bpjs=true;
                    var.edit_data_kematian=true;
                    var.bridging_jamkesda=true;
                    var.masuk_pindah_pulang_inap=true;
                    var.masuk_pindah_inap=true;
                    var.jumlah_macam_diet=true;
                    var.jumlah_porsi_diet=true;
                    var.status_gizi=true;
                    var.gizi_buruk=true;
                    var.master_faskes=true;
                    var.setstatusralan=true;
                    var.telusurpasien=true;
                    var.sisrute_rujukan_keluar=true;
                    var.sisrute_rujukan_masuk=true;
                    var.sisrute_referensi_diagnosa=true;
                    var.sisrute_referensi_alasanrujuk=true;
                    var.sisrute_referensi_faskes=true;
                    var.barang_cssd=true;
                    var.status_pulang_inap=true;
                    var.data_persalinan=true;
                    var.data_ponek=true;
                    var.reg_boking_kasir=true;
                    var.bahasa_pasien=true;
                    var.suku_bangsa=true;
                    var.harian_hais_inap=true;
                    var.harian_hais_jalan=true;
                    var.bulanan_hais_inap=true;
                    var.bulanan_hais_jalan=true;
                    var.ringkasan_pulang_ranap=true;
                    var.laporan_farmasi=true;
                    var.master_masalah_keperawatan=true;
                    var.penilaian_awal_keperawatan_ralan=true;
                    var.master_triase_skala1=true;
                    var.master_triase_skala2=true;                    
                    var.master_triase_skala3=true;
                    var.master_triase_skala4=true;
                    var.master_triase_skala5=true;
                    var.data_triase_igd=true;
                    var.master_triase_pemeriksaan=true;
                    var.master_triase_macamkasus=true;
                    var.master_cara_bayar=true;
                    var.status_kerja_dokter=true;
                    var.pasien_corona=true;
                    var.diagnosa_pasien_corona=true;
                    var.perawatan_pasien_corona=true;
                    var.inacbg_klaim_baru_manual2=true;
                    var.assesmen_gizi_harian=true;
                    var.assesmen_gizi_ulang=true;
                    var.tombol_nota_billing=true;
                    var.tombol_simpan_hasil_rad=true;
                    var.monev_asuhan_gizi=true;
                    var.inacbg_klaim_raza=true;
                    var.pengajuan_klaim_inacbg_raza=true;
                    var.copy_pemeriksaan_dokter_kepetugas_ralan=true;
                    var.jkn_belum_diproses_klaim=true;
                    var.input_kode_icd=true;
                    var.kendali_Mutu_kendali_Biaya_INACBG=true;
                    var.dashboard_eResep=true;
                    var.bpjs_sep_internal=true;
                    var.kemenkes_sitt=true;
                    var.rencana_kontrol_jkn=true;
                    var.spri_jkn=true;
                    var.hapus_sep=true;
                    var.penilaian_awal_medis_ralan_kebidanan=true;
                    var.penilaian_awal_keperawatan_kebidanan=true;
                    var.ikhtisar_perawatan_hiv=true;
                    var.survey_kepuasan=true;
                    var.kemenkes_kanker=true;
                    var.aktivasi_bridging=true;
                    var.operator_antrian=true;
                } else if (rs.getRow() >= 1) {
                    var.kode = "Admin Utama";
                    var.penyakit = true;
                    var.obat_penyakit = true;
                    var.dokter = true;
                    var.jadwal_praktek = true;
                    var.petugas = true;
                    var.pasien = true;
                    var.registrasi = true;
                    var.tindakan_ralan = true;
                    var.kamar_inap = true;
                    var.tindakan_ranap = true;
                    var.operasi = true;
                    var.rujukan_keluar = true;
                    var.rujukan_masuk = true;
                    var.beri_obat = true;
                    var.resep_pulang = true;
                    var.pasien_meninggal = true;
                    var.diet_pasien = true;
                    var.kelahiran_bayi = true;
                    var.periksa_lab = true;
                    var.periksa_radiologi = true;
                    var.kasir_ralan = true;
                    var.deposit_pasien = true;
                    var.piutang_pasien = true;
                    var.peminjaman_berkas = true;
                    var.barcode = true;
                    var.presensi_harian = true;
                    var.presensi_bulanan = true;
                    var.pegawai_admin = true;
                    var.pegawai_user = true;
                    var.suplier = true;
                    var.satuan_barang = true;
                    var.konversi_satuan = true;
                    var.jenis_barang = true;
                    var.obat = true;
                    var.stok_opname_obat = true;
                    var.stok_obat_pasien = true;
                    var.pengadaan_obat = true;
                    var.pemesanan_obat = true;
                    var.penjualan_obat = true;
                    var.piutang_obat = true;
                    var.retur_ke_suplier = true;
                    var.retur_dari_pembeli = true;
                    var.retur_obat_ranap = true;
                    var.retur_piutang_pasien = true;
                    var.keuntungan_penjualan = true;
                    var.keuntungan_beri_obat = true;
                    var.sirkulasi_obat = true;
                    var.ipsrs_barang = true;
                    var.ipsrs_pengadaan_barang = true;
                    var.ipsrs_stok_keluar = true;
                    var.ipsrs_rekap_pengadaan = true;
                    var.ipsrs_rekap_stok_keluar = true;
                    var.ipsrs_pengeluaran_harian = true;
                    var.ipsrs_jenis_barang = true;
                    var.inventaris_jenis = true;
                    var.inventaris_kategori = true;
                    var.inventaris_merk = true;
                    var.inventaris_ruang = true;
                    var.inventaris_produsen = true;
                    var.inventaris_koleksi = true;
                    var.inventaris_inventaris = true;
                    var.inventaris_sirkulasi = true;
                    var.parkir_jenis = true;
                    var.parkir_in = true;
                    var.parkir_out = true;
                    var.parkir_rekap_harian = true;
                    var.parkir_rekap_bulanan = true;
                    var.informasi_kamar = true;
                    var.harian_tindakan_poli = true;
                    var.obat_per_poli = true;
                    var.obat_per_kamar = true;
                    var.obat_per_dokter_ralan = true;
                    var.obat_per_dokter_ranap = true;
                    var.harian_dokter = true;
                    var.bulanan_dokter = true;
                    var.harian_paramedis = true;
                    var.bulanan_paramedis = true;
                    var.pembayaran_ralan = true;
                    var.pembayaran_ranap = true;
                    var.rekap_pembayaran_ralan = true;
                    var.rekap_pembayaran_ranap = true;
                    var.tagihan_masuk = true;
                    var.tambahan_biaya = true;
                    var.potongan_biaya = true;
                    var.resep_obat = true;
                    var.resume_pasien = true;
                    var.penyakit_ralan = true;
                    var.penyakit_ranap = true;
                    var.kamar = true;
                    var.tarif_ralan = true;
                    var.tarif_ranap = true;
                    var.tarif_lab = true;
                    var.tarif_radiologi = true;
                    var.tarif_operasi = true;
                    var.akun_rekening = true;
                    var.rekening_tahun = true;
                    var.posting_jurnal = true;
                    var.buku_besar = true;
                    var.cashflow = true;
                    var.keuangan = true;
                    var.pengeluaran = true;
                    var.setup_pjlab = true;
                    var.setup_otolokasi = true;
                    var.setup_jam_kamin = true;
                    var.setup_embalase = true;
                    var.tracer_login = true;
                    var.display = true;
                    var.set_harga_obat = true;
                    var.set_penggunaan_tarif = true;
                    var.set_oto_ralan = true;
                    var.biaya_harian = true;
                    var.biaya_masuk_sekali = true;
                    var.set_no_rm = true;
                    var.billing_ralan = true;
                    var.billing_ranap = true;
                    var.jm_ranap_dokter = true;
                    var.igd = true;
                    var.barcoderalan = true;
                    var.barcoderanap = true;
                    var.set_harga_obat_ralan = true;
                    var.set_harga_obat_ranap = true;
                    var.penyakit_pd3i = true;
                    var.surveilans_pd3i = true;
                    var.surveilans_ralan = true;
                    var.diagnosa_pasien = true;
                    var.admin = true;
                    var.user = true;
                    var.vakum = true;
                    var.aplikasi = true;
                    var.surveilans_ranap = true;
                    var.pny_takmenular_ranap = true;
                    var.pny_takmenular_ralan = true;
                    var.kunjungan_ralan = true;
                    var.rl32 = true;
                    var.rl33 = true;
                    var.rl37 = true;
                    var.rl38 = true;
                    var.harian_tindakan_dokter = true;
                    var.sms = true;
                    var.sidikjari = true;
                    var.jam_masuk = true;
                    var.jadwal_pegawai = true;
                    var.parkir_barcode = true;
                    var.set_nota = true;
                    var.dpjp_ranap = true;
                    var.mutasi_barang = true;
                    var.rl34 = true;
                    var.rl36 = true;
                    var.fee_visit_dokter = true;
                    var.fee_bacaan_ekg = true;
                    var.fee_rujukan_rontgen = true;
                    var.fee_rujukan_ranap = true;
                    var.fee_ralan = true;
                    var.akun_bayar = true;
                    var.bayar_pemesanan_obat = true;
                    var.obat_per_dokter_peresep = true;
                    var.pemasukan_lain = true;
                    var.pengaturan_rekening = true;
                    var.closing_kasir = true;
                    var.keterlambatan_presensi = true;
                    var.set_harga_kamar = true;
                    var.rekap_per_shift = true;
                    var.bpjs_cek_nik = true;
                    var.bpjs_cek_kartu = true;
                    var.obat_per_cara_bayar = true;
                    var.kunjungan_ranap = true;
                    var.bayar_piutang = true;
                    var.payment_point = true;
                    var.bpjs_cek_nomor_rujukan = true;
                    var.icd9 = true;
                    var.darurat_stok = true;
                    var.retensi_rm = true;
                    var.temporary_presensi = true;
                    var.jurnal_harian = true;
                    var.sirkulasi_obat2 = true;
                    var.edit_registrasi = true;
                    var.bpjs_referensi_diagnosa = true;
                    var.bpjs_referensi_poli = true;
                    var.industrifarmasi = true;
                    var.harian_js = true;
                    var.bulanan_js = true;
                    var.harian_paket_bhp = true;
                    var.bulanan_paket_bhp = true;
                    var.piutang_pasien2 = true;
                    var.bpjs_referensi_faskes = true;
                    var.bpjs_sep = true;
                    var.pengambilan_utd = true;
                    var.tarif_utd = true;
                    var.pengambilan_utd2 = true;
                    var.utd_medis_rusak = true;
                    var.pengambilan_penunjang_utd = true;
                    var.pengambilan_penunjang_utd2 = true;
                    var.utd_penunjang_rusak = true;
                    var.suplier_penunjang = true;
                    var.utd_donor = true;
                    var.bpjs_monitoring_klaim = true;
                    var.utd_cekal_darah = true;
                    var.utd_komponen_darah = true;
                    var.utd_stok_darah = true;
                    var.utd_pemisahan_darah = true;
                    var.harian_kamar = true;
                    var.rincian_piutang_pasien = true;
                    var.keuntungan_beri_obat_nonpiutang = true;
                    var.reklasifikasi_ralan = true;
                    var.reklasifikasi_ranap = true;
                    var.utd_penyerahan_darah = true;
                    var.hutang_obat = true;
                    var.riwayat_obat_alkes_bhp = true;
                    var.sensus_harian_poli = true;
                    var.rl4a = true;
                    var.aplicare_referensi_kamar = true;
                    var.aplicare_ketersediaan_kamar = true;
                    var.inacbg_klaim_baru_otomatis = true;
                    var.inacbg_klaim_baru_manual = true;
                    var.inacbg_coder_nik = true;
                    var.mutasi_berkas = true;
                    var.akun_piutang = true;
                    var.harian_kso = true;
                    var.bulanan_kso = true;
                    var.harian_menejemen = true;
                    var.bulanan_menejemen = true;
                    var.inhealth_cek_eligibilitas = true;
                    var.inhealth_referensi_jenpel_ruang_rawat = true;
                    var.inhealth_referensi_poli = true;
                    var.inhealth_referensi_faskes = true;
                    var.inhealth_sjp = true;
                    var.piutang_ralan = true;
                    var.piutang_ranap = true;
                    var.detail_piutang_penjab = true;
                    var.lama_pelayanan_ralan = true;
                    var.catatan_pasien = true;
                    var.rl4b = true;
                    var.rl4asebab = true;
                    var.rl4bsebab = true;
                    var.data_HAIs = true;
                    var.harian_HAIs = true;
                    var.bulanan_HAIs = true;
                    var.hitung_bor = true;
                    var.perusahaan_pasien = true;
                    var.resep_dokter = true;
                    var.lama_pelayanan_apotek = true;
                    var.hitung_alos = true;
                    var.detail_tindakan = true;
                    var.rujukan_poli_internal = true;
                    var.rekap_poli_anak = true;
                    var.grafik_kunjungan_poli = true;
                    var.grafik_kunjungan_perdokter = true;
                    var.grafik_kunjungan_perpekerjaan = true;
                    var.grafik_kunjungan_perpendidikan = true;
                    var.grafik_kunjungan_pertahun = true;
                    var.berkas_digital_perawatan = true;
                    var.penyakit_menular_ranap = true;
                    var.penyakit_menular_ralan = true;
                    var.grafik_kunjungan_perbulan = true;
                    var.grafik_kunjungan_pertanggal = true;
                    var.grafik_kunjungan_demografi = true;
                    var.grafik_kunjungan_statusdaftartahun = true;
                    var.grafik_kunjungan_statusdaftartahun2 = true;
                    var.grafik_kunjungan_statusdaftarbulan = true;
                    var.grafik_kunjungan_statusdaftarbulan2 = true;
                    var.grafik_kunjungan_statusdaftartanggal = true;
                    var.grafik_kunjungan_statusdaftartanggal2 = true;
                    var.grafik_kunjungan_statusbataltahun = true;
                    var.grafik_kunjungan_statusbatalbulan = true;
                    var.pcare_cek_penyakit = true;
                    var.grafik_kunjungan_statusbataltanggal = true;
                    var.kategori_barang = true;
                    var.golongan_barang = true;
                    var.pemberian_obat_pertanggal = true;
                    var.penjualan_obat_pertanggal = true;
                    var.bpjs_rujukan_vclaim = true;
                    var.skdp_bpjs = true;
                    var.booking_registrasi = true;
                    var.bpjs_cek_riwayat_rujukan_pcare = true;
                    var.bpjs_cek_riwayat_rujukan_rs = true;
                    var.bpjs_cek_rujukan_kartu_rs = true;
                    var.bpjs_cek_tgl_rujukan = true;
                    var.bpjs_cek_no_rujukan_rs = true;
                    var.bpjs_cek_rujukan_kartu_pcare = true;
                    var.bpjs_cek_referensi_kelas_rawat = true;
                    var.bpjs_cek_referensi_prosedur = true;
                    var.bpjs_cek_referensi_dpjp = true;
                    var.bpjs_cek_referensi_dokter = true;
                    var.bpjs_cek_referensi_spesialistik = true;
                    var.bpjs_cek_referensi_ruang_rawat = true;
                    var.bpjs_cek_referensi_cara_keluar = true;
                    var.bpjs_cek_referensi_pasca_pulang = true;
                    var.bpjs_cek_referensi_propinsi = true;
                    var.bpjs_cek_referensi_kabupaten = true;
                    var.bpjs_cek_referensi_kecamatan = true;
                    var.permintaan_lab = true;
                    var.permintaan_radiologi = true;
                    var.selisih_tarif_bpjs = true;
                    var.edit_data_kematian = true;
                    var.bridging_jamkesda = true;
                    var.masuk_pindah_pulang_inap = true;
                    var.masuk_pindah_inap = true;
                    var.jumlah_macam_diet = true;
                    var.jumlah_porsi_diet = true;
                    var.status_gizi = true;
                    var.gizi_buruk = true;
                    var.master_faskes = true;
                    var.setstatusralan = true;
                    var.telusurpasien = true;
                    var.sisrute_rujukan_keluar = true;
                    var.sisrute_rujukan_masuk = true;
                    var.sisrute_referensi_diagnosa = true;
                    var.sisrute_referensi_alasanrujuk = true;
                    var.sisrute_referensi_faskes = true;
                    var.barang_cssd = true;
                    var.status_pulang_inap = true;
                    var.data_persalinan = true;
                    var.data_ponek = true;
                    var.reg_boking_kasir = true;
                    var.bahasa_pasien = true;
                    var.suku_bangsa = true;
                    var.harian_hais_inap = true;
                    var.harian_hais_jalan = true;
                    var.bulanan_hais_inap = true;
                    var.bulanan_hais_jalan = true;
                    var.ringkasan_pulang_ranap = true;
                    var.laporan_farmasi = true;
                    var.master_masalah_keperawatan = true;
                    var.penilaian_awal_keperawatan_ralan = true;
                    var.master_triase_skala1 = true;
                    var.master_triase_skala2 = true;
                    var.master_triase_skala3 = true;
                    var.master_triase_skala4 = true;
                    var.master_triase_skala5 = true;
                    var.data_triase_igd = true;
                    var.master_triase_pemeriksaan = true;
                    var.master_triase_macamkasus = true;
                    var.master_cara_bayar = true;
                    var.status_kerja_dokter = true;
                    var.pasien_corona = true;
                    var.diagnosa_pasien_corona = true;
                    var.perawatan_pasien_corona = true;
                    var.inacbg_klaim_baru_manual2 = true;
                    var.assesmen_gizi_harian = true;
                    var.assesmen_gizi_ulang = true;
                    var.tombol_nota_billing = true;
                    var.tombol_simpan_hasil_rad = true;
                    var.monev_asuhan_gizi = true;
                    var.inacbg_klaim_raza = true;
                    var.pengajuan_klaim_inacbg_raza = true;
                    var.copy_pemeriksaan_dokter_kepetugas_ralan = true;
                    var.jkn_belum_diproses_klaim = true;
                    var.input_kode_icd = true;
                    var.kendali_Mutu_kendali_Biaya_INACBG = true;
                    var.dashboard_eResep = true;
                    var.bpjs_sep_internal = true;
                    var.kemenkes_sitt = true;
                    var.rencana_kontrol_jkn = true;
                    var.spri_jkn = true;
                    var.hapus_sep = true;
                    var.penilaian_awal_medis_ralan_kebidanan = true;
                    var.penilaian_awal_keperawatan_kebidanan = true;
                    var.ikhtisar_perawatan_hiv = true;
                    var.survey_kepuasan = true;
                    var.kemenkes_kanker = true;
                    var.aktivasi_bridging = true;
                    var.operator_antrian = true;
                } else if (rs2.getRow() >= 1) {
                    rs2.beforeFirst();
                    rs2.next();
                    var.kode = user;
                    var.penyakit = rs2.getBoolean("penyakit");
                    var.obat_penyakit = rs2.getBoolean("obat_penyakit");
                    var.dokter = rs2.getBoolean("dokter");
                    var.jadwal_praktek = rs2.getBoolean("jadwal_praktek");
                    var.petugas = rs2.getBoolean("petugas");
                    var.pasien = rs2.getBoolean("pasien");
                    var.registrasi = rs2.getBoolean("registrasi");
                    var.tindakan_ralan = rs2.getBoolean("tindakan_ralan");
                    var.kamar_inap = rs2.getBoolean("kamar_inap");
                    var.tindakan_ranap = rs2.getBoolean("tindakan_ranap");
                    var.operasi = rs2.getBoolean("operasi");
                    var.rujukan_keluar = rs2.getBoolean("rujukan_keluar");
                    var.rujukan_masuk = rs2.getBoolean("rujukan_masuk");
                    var.beri_obat = rs2.getBoolean("beri_obat");
                    var.resep_pulang = rs2.getBoolean("resep_pulang");
                    var.pasien_meninggal = rs2.getBoolean("pasien_meninggal");
                    var.diet_pasien = rs2.getBoolean("diet_pasien");
                    var.kelahiran_bayi = rs2.getBoolean("kelahiran_bayi");
                    var.periksa_lab = rs2.getBoolean("periksa_lab");
                    var.periksa_radiologi = rs2.getBoolean("periksa_radiologi");
                    var.kasir_ralan = rs2.getBoolean("kasir_ralan");
                    var.deposit_pasien = rs2.getBoolean("deposit_pasien");
                    var.piutang_pasien = rs2.getBoolean("piutang_pasien");
                    var.peminjaman_berkas = rs2.getBoolean("peminjaman_berkas");
                    var.barcode = rs2.getBoolean("barcode");
                    var.presensi_harian = rs2.getBoolean("presensi_harian");
                    var.presensi_bulanan = rs2.getBoolean("presensi_bulanan");
                    var.pegawai_admin = rs2.getBoolean("pegawai_admin");
                    var.pegawai_user = rs2.getBoolean("pegawai_user");
                    var.suplier = rs2.getBoolean("suplier");
                    var.satuan_barang = rs2.getBoolean("satuan_barang");
                    var.konversi_satuan = rs2.getBoolean("konversi_satuan");
                    var.jenis_barang = rs2.getBoolean("jenis_barang");
                    var.obat = rs2.getBoolean("obat");
                    var.stok_opname_obat = rs2.getBoolean("stok_opname_obat");
                    var.stok_obat_pasien = rs2.getBoolean("stok_obat_pasien");
                    var.pengadaan_obat = rs2.getBoolean("pengadaan_obat");
                    var.pemesanan_obat = rs2.getBoolean("pemesanan_obat");
                    var.penjualan_obat = rs2.getBoolean("penjualan_obat");
                    var.piutang_obat = rs2.getBoolean("piutang_obat");
                    var.retur_ke_suplier = rs2.getBoolean("retur_ke_suplier");
                    var.retur_dari_pembeli = rs2.getBoolean("retur_dari_pembeli");
                    var.retur_obat_ranap = rs2.getBoolean("retur_obat_ranap");
                    var.retur_piutang_pasien = rs2.getBoolean("retur_piutang_pasien");
                    var.keuntungan_penjualan = rs2.getBoolean("keuntungan_penjualan");
                    var.keuntungan_beri_obat = rs2.getBoolean("keuntungan_beri_obat");
                    var.sirkulasi_obat = rs2.getBoolean("sirkulasi_obat");
                    var.ipsrs_barang = rs2.getBoolean("ipsrs_barang");
                    var.ipsrs_pengadaan_barang = rs2.getBoolean("ipsrs_pengadaan_barang");
                    var.ipsrs_stok_keluar = rs2.getBoolean("ipsrs_stok_keluar");
                    var.ipsrs_jenis_barang = rs2.getBoolean("ipsrs_jenis_barang");
                    var.ipsrs_rekap_pengadaan = rs2.getBoolean("ipsrs_rekap_pengadaan");
                    var.ipsrs_rekap_stok_keluar = rs2.getBoolean("ipsrs_rekap_stok_keluar");
                    var.ipsrs_pengeluaran_harian = rs2.getBoolean("ipsrs_pengeluaran_harian");
                    var.inventaris_jenis = rs2.getBoolean("inventaris_jenis");
                    var.inventaris_kategori = rs2.getBoolean("inventaris_kategori");
                    var.inventaris_merk = rs2.getBoolean("inventaris_merk");
                    var.inventaris_ruang = rs2.getBoolean("inventaris_ruang");
                    var.inventaris_produsen = rs2.getBoolean("inventaris_produsen");
                    var.inventaris_koleksi = rs2.getBoolean("inventaris_koleksi");
                    var.inventaris_inventaris = rs2.getBoolean("inventaris_inventaris");
                    var.inventaris_sirkulasi = rs2.getBoolean("inventaris_sirkulasi");
                    var.parkir_jenis = rs2.getBoolean("parkir_jenis");
                    var.parkir_in = rs2.getBoolean("parkir_in");
                    var.parkir_out = rs2.getBoolean("parkir_out");
                    var.parkir_rekap_harian = rs2.getBoolean("parkir_rekap_harian");
                    var.parkir_rekap_bulanan = rs2.getBoolean("parkir_rekap_bulanan");
                    var.informasi_kamar = rs2.getBoolean("informasi_kamar");
                    var.harian_tindakan_poli = rs2.getBoolean("harian_tindakan_poli");
                    var.obat_per_poli = rs2.getBoolean("obat_per_poli");
                    var.obat_per_kamar = rs2.getBoolean("obat_per_kamar");
                    var.obat_per_dokter_ralan = rs2.getBoolean("obat_per_dokter_ralan");
                    var.obat_per_dokter_ranap = rs2.getBoolean("obat_per_dokter_ranap");
                    var.harian_dokter = rs2.getBoolean("harian_dokter");
                    var.bulanan_dokter = rs2.getBoolean("bulanan_dokter");
                    var.harian_paramedis = rs2.getBoolean("harian_paramedis");
                    var.bulanan_paramedis = rs2.getBoolean("bulanan_paramedis");
                    var.pembayaran_ralan = rs2.getBoolean("pembayaran_ralan");
                    var.pembayaran_ranap = rs2.getBoolean("pembayaran_ranap");
                    var.rekap_pembayaran_ralan = rs2.getBoolean("rekap_pembayaran_ralan");
                    var.rekap_pembayaran_ranap = rs2.getBoolean("rekap_pembayaran_ranap");
                    var.tagihan_masuk = rs2.getBoolean("tagihan_masuk");
                    var.tambahan_biaya = rs2.getBoolean("tambahan_biaya");
                    var.potongan_biaya = rs2.getBoolean("potongan_biaya");
                    var.resep_obat = rs2.getBoolean("resep_obat");
                    var.resume_pasien = rs2.getBoolean("resume_pasien");
                    var.penyakit_ralan = rs2.getBoolean("penyakit_ralan");
                    var.penyakit_ranap = rs2.getBoolean("penyakit_ranap");
                    var.kamar = rs2.getBoolean("kamar");
                    var.tarif_ralan = rs2.getBoolean("tarif_ralan");
                    var.tarif_ranap = rs2.getBoolean("tarif_ranap");
                    var.tarif_lab = rs2.getBoolean("tarif_lab");
                    var.tarif_radiologi = rs2.getBoolean("tarif_radiologi");
                    var.tarif_operasi = rs2.getBoolean("tarif_operasi");
                    var.akun_rekening = rs2.getBoolean("akun_rekening");
                    var.rekening_tahun = rs2.getBoolean("rekening_tahun");
                    var.posting_jurnal = rs2.getBoolean("posting_jurnal");
                    var.buku_besar = rs2.getBoolean("buku_besar");
                    var.cashflow = rs2.getBoolean("cashflow");
                    var.keuangan = rs2.getBoolean("keuangan");
                    var.pengeluaran = rs2.getBoolean("pengeluaran");
                    var.setup_pjlab = rs2.getBoolean("setup_pjlab");
                    var.setup_otolokasi = rs2.getBoolean("setup_otolokasi");
                    var.setup_jam_kamin = rs2.getBoolean("setup_jam_kamin");
                    var.setup_embalase = rs2.getBoolean("setup_embalase");
                    var.tracer_login = rs2.getBoolean("tracer_login");
                    var.display = rs2.getBoolean("display");
                    var.set_harga_obat = rs2.getBoolean("set_harga_obat");
                    var.set_penggunaan_tarif = rs2.getBoolean("set_penggunaan_tarif");
                    var.set_oto_ralan = rs2.getBoolean("set_oto_ralan");
                    var.biaya_harian = rs2.getBoolean("biaya_harian");
                    var.biaya_masuk_sekali = rs2.getBoolean("biaya_masuk_sekali");
                    var.set_no_rm = rs2.getBoolean("set_no_rm");
                    var.billing_ralan = rs2.getBoolean("billing_ralan");
                    var.billing_ranap = rs2.getBoolean("billing_ranap");
                    var.jm_ranap_dokter = rs2.getBoolean("jm_ranap_dokter");
                    var.igd = rs2.getBoolean("igd");
                    var.barcoderalan = rs2.getBoolean("barcoderalan");
                    var.barcoderanap = rs2.getBoolean("barcoderanap");
                    var.set_harga_obat_ralan = rs2.getBoolean("set_harga_obat_ralan");
                    var.set_harga_obat_ranap = rs2.getBoolean("set_harga_obat_ranap");
                    var.penyakit_pd3i = rs2.getBoolean("penyakit_pd3i");
                    var.surveilans_pd3i = rs2.getBoolean("surveilans_pd3i");
                    var.surveilans_ralan = rs2.getBoolean("surveilans_ralan");
                    var.diagnosa_pasien = rs2.getBoolean("diagnosa_pasien");
                    var.surveilans_ranap = rs2.getBoolean("surveilans_ranap");
                    var.admin = false;
                    var.user = false;
                    var.vakum = false;
                    var.aplikasi = false;
                    var.pny_takmenular_ranap = rs2.getBoolean("pny_takmenular_ranap");
                    var.pny_takmenular_ralan = rs2.getBoolean("pny_takmenular_ralan");
                    var.kunjungan_ralan = rs2.getBoolean("kunjungan_ralan");
                    var.rl32 = rs2.getBoolean("rl32");
                    var.rl33 = rs2.getBoolean("rl33");
                    var.rl37 = rs2.getBoolean("rl37");
                    var.rl38 = rs2.getBoolean("rl38");
                    var.harian_tindakan_dokter = rs2.getBoolean("harian_tindakan_dokter");
                    var.sms = rs2.getBoolean("sms");
                    var.sidikjari = rs2.getBoolean("sidikjari");
                    var.jam_masuk = rs2.getBoolean("jam_masuk");
                    var.jadwal_pegawai = rs2.getBoolean("jadwal_pegawai");
                    var.parkir_barcode = rs2.getBoolean("parkir_barcode");
                    var.set_nota = rs2.getBoolean("set_nota");
                    var.dpjp_ranap = rs2.getBoolean("dpjp_ranap");
                    var.mutasi_barang = rs2.getBoolean("mutasi_barang");
                    var.rl34 = rs2.getBoolean("rl34");
                    var.rl36 = rs2.getBoolean("rl36");
                    var.fee_visit_dokter = rs2.getBoolean("fee_visit_dokter");
                    var.fee_bacaan_ekg = rs2.getBoolean("fee_bacaan_ekg");
                    var.fee_rujukan_rontgen = rs2.getBoolean("fee_rujukan_rontgen");
                    var.fee_rujukan_ranap = rs2.getBoolean("fee_rujukan_ranap");
                    var.fee_ralan = rs2.getBoolean("fee_ralan");
                    var.akun_bayar = rs2.getBoolean("akun_bayar");
                    var.bayar_pemesanan_obat = rs2.getBoolean("bayar_pemesanan_obat");
                    var.obat_per_dokter_peresep = rs2.getBoolean("obat_per_dokter_peresep");
                    var.pemasukan_lain = rs2.getBoolean("pemasukan_lain");
                    var.pengaturan_rekening = rs2.getBoolean("pengaturan_rekening");
                    var.closing_kasir = rs2.getBoolean("closing_kasir");
                    var.keterlambatan_presensi = rs2.getBoolean("keterlambatan_presensi");
                    var.set_harga_kamar = rs2.getBoolean("set_harga_kamar");
                    var.rekap_per_shift = rs2.getBoolean("rekap_per_shift");
                    var.bpjs_cek_nik = rs2.getBoolean("bpjs_cek_nik");
                    var.bpjs_cek_kartu = rs2.getBoolean("bpjs_cek_kartu");
                    var.obat_per_cara_bayar = rs2.getBoolean("obat_per_cara_bayar");
                    var.kunjungan_ranap = rs2.getBoolean("kunjungan_ranap");
                    var.bayar_piutang = rs2.getBoolean("bayar_piutang");
                    var.payment_point = rs2.getBoolean("payment_point");
                    var.bpjs_cek_nomor_rujukan = rs2.getBoolean("bpjs_cek_nomor_rujukan");
                    var.icd9 = rs2.getBoolean("icd9");
                    var.darurat_stok = rs2.getBoolean("darurat_stok");
                    var.retensi_rm = rs2.getBoolean("retensi_rm");
                    var.temporary_presensi = rs2.getBoolean("temporary_presensi");
                    var.jurnal_harian = rs2.getBoolean("jurnal_harian");
                    var.sirkulasi_obat2 = rs2.getBoolean("sirkulasi_obat2");
                    var.edit_registrasi = rs2.getBoolean("edit_registrasi");
                    var.bpjs_referensi_diagnosa = rs2.getBoolean("bpjs_referensi_diagnosa");
                    var.bpjs_referensi_poli = rs2.getBoolean("bpjs_referensi_poli");
                    var.industrifarmasi = rs2.getBoolean("industrifarmasi");
                    var.harian_js = rs2.getBoolean("harian_js");
                    var.bulanan_js = rs2.getBoolean("bulanan_js");
                    var.harian_paket_bhp = rs2.getBoolean("harian_paket_bhp");
                    var.bulanan_paket_bhp = rs2.getBoolean("bulanan_paket_bhp");
                    var.piutang_pasien2 = rs2.getBoolean("piutang_pasien2");
                    var.bpjs_referensi_faskes = rs2.getBoolean("bpjs_referensi_faskes");
                    var.bpjs_sep = rs2.getBoolean("bpjs_sep");
                    var.pengambilan_utd = rs2.getBoolean("pengambilan_utd");
                    var.tarif_utd = rs2.getBoolean("tarif_utd");
                    var.pengambilan_utd2 = rs2.getBoolean("pengambilan_utd2");
                    var.utd_medis_rusak = rs2.getBoolean("utd_medis_rusak");
                    var.pengambilan_penunjang_utd = rs2.getBoolean("pengambilan_penunjang_utd");
                    var.pengambilan_penunjang_utd2 = rs2.getBoolean("pengambilan_penunjang_utd2");
                    var.utd_penunjang_rusak = rs2.getBoolean("utd_penunjang_rusak");
                    var.suplier_penunjang = rs2.getBoolean("suplier_penunjang");
                    var.utd_donor = rs2.getBoolean("utd_donor");
                    var.bpjs_monitoring_klaim = rs2.getBoolean("bpjs_monitoring_klaim");
                    var.utd_cekal_darah = rs2.getBoolean("utd_cekal_darah");
                    var.utd_komponen_darah = rs2.getBoolean("utd_komponen_darah");
                    var.utd_stok_darah = rs2.getBoolean("utd_stok_darah");
                    var.utd_pemisahan_darah = rs2.getBoolean("utd_pemisahan_darah");
                    var.harian_kamar = rs2.getBoolean("harian_kamar");
                    var.rincian_piutang_pasien = rs2.getBoolean("rincian_piutang_pasien");
                    var.keuntungan_beri_obat_nonpiutang = rs2.getBoolean("keuntungan_beri_obat_nonpiutang");
                    var.reklasifikasi_ralan = rs2.getBoolean("reklasifikasi_ralan");
                    var.reklasifikasi_ranap = rs2.getBoolean("reklasifikasi_ranap");
                    var.utd_penyerahan_darah = rs2.getBoolean("utd_penyerahan_darah");
                    var.hutang_obat = rs2.getBoolean("hutang_obat");
                    var.riwayat_obat_alkes_bhp = rs2.getBoolean("riwayat_obat_alkes_bhp");
                    var.sensus_harian_poli = rs2.getBoolean("sensus_harian_poli");
                    var.rl4a = rs2.getBoolean("rl4a");
                    var.aplicare_referensi_kamar = rs2.getBoolean("aplicare_referensi_kamar");
                    var.aplicare_ketersediaan_kamar = rs2.getBoolean("aplicare_ketersediaan_kamar");
                    var.inacbg_klaim_baru_otomatis = rs2.getBoolean("inacbg_klaim_baru_otomatis");
                    var.inacbg_klaim_baru_manual = rs2.getBoolean("inacbg_klaim_baru_manual");
                    var.inacbg_coder_nik = rs2.getBoolean("inacbg_coder_nik");
                    var.mutasi_berkas = rs2.getBoolean("mutasi_berkas");
                    var.akun_piutang = rs2.getBoolean("akun_piutang");
                    var.harian_kso = rs2.getBoolean("harian_kso");
                    var.bulanan_kso = rs2.getBoolean("bulanan_kso");
                    var.harian_menejemen = rs2.getBoolean("harian_menejemen");
                    var.bulanan_menejemen = rs2.getBoolean("bulanan_menejemen");
                    var.inhealth_cek_eligibilitas = rs2.getBoolean("inhealth_cek_eligibilitas");
                    var.inhealth_referensi_jenpel_ruang_rawat = rs2.getBoolean("inhealth_referensi_jenpel_ruang_rawat");
                    var.inhealth_referensi_poli = rs2.getBoolean("inhealth_referensi_poli");
                    var.inhealth_referensi_faskes = rs2.getBoolean("inhealth_referensi_faskes");
                    var.inhealth_sjp = rs2.getBoolean("inhealth_sjp");
                    var.piutang_ralan = rs2.getBoolean("piutang_ralan");
                    var.piutang_ranap = rs2.getBoolean("piutang_ranap");
                    var.detail_piutang_penjab = rs2.getBoolean("detail_piutang_penjab");
                    var.lama_pelayanan_ralan = rs2.getBoolean("lama_pelayanan_ralan");
                    var.catatan_pasien = rs2.getBoolean("catatan_pasien");
                    var.rl4b = rs2.getBoolean("rl4b");
                    var.rl4asebab = rs2.getBoolean("rl4asebab");
                    var.rl4bsebab = rs2.getBoolean("rl4bsebab");
                    var.data_HAIs = rs2.getBoolean("data_HAIs");
                    var.harian_HAIs = rs2.getBoolean("harian_HAIs");
                    var.bulanan_HAIs = rs2.getBoolean("bulanan_HAIs");
                    var.hitung_bor = rs2.getBoolean("hitung_bor");
                    var.perusahaan_pasien = rs2.getBoolean("perusahaan_pasien");
                    var.namauser = rs2.getString("nama");
                    var.resep_dokter = rs2.getBoolean("resep_dokter");
                    var.lama_pelayanan_apotek = rs2.getBoolean("lama_pelayanan_apotek");
                    var.hitung_alos = rs2.getBoolean("hitung_alos");
                    var.detail_tindakan = rs2.getBoolean("detail_tindakan");
                    var.rujukan_poli_internal = rs2.getBoolean("rujukan_poli_internal");
                    var.rekap_poli_anak = rs2.getBoolean("rekap_poli_anak");
                    var.grafik_kunjungan_poli = rs2.getBoolean("grafik_kunjungan_poli");
                    var.grafik_kunjungan_perdokter = rs2.getBoolean("grafik_kunjungan_perdokter");
                    var.grafik_kunjungan_perpekerjaan = rs2.getBoolean("grafik_kunjungan_perpekerjaan");
                    var.grafik_kunjungan_perpendidikan = rs2.getBoolean("grafik_kunjungan_perpendidikan");
                    var.grafik_kunjungan_pertahun = rs2.getBoolean("grafik_kunjungan_pertahun");
                    var.berkas_digital_perawatan = rs2.getBoolean("berkas_digital_perawatan");
                    var.penyakit_menular_ranap = rs2.getBoolean("penyakit_menular_ranap");
                    var.penyakit_menular_ralan = rs2.getBoolean("penyakit_menular_ralan");
                    var.grafik_kunjungan_perbulan = rs2.getBoolean("grafik_kunjungan_perbulan");
                    var.grafik_kunjungan_pertanggal = rs2.getBoolean("grafik_kunjungan_pertanggal");
                    var.grafik_kunjungan_demografi = rs2.getBoolean("grafik_kunjungan_demografi");
                    var.grafik_kunjungan_statusdaftartahun = rs2.getBoolean("grafik_kunjungan_statusdaftartahun");
                    var.grafik_kunjungan_statusdaftartahun2 = rs2.getBoolean("grafik_kunjungan_statusdaftartahun2");
                    var.grafik_kunjungan_statusdaftarbulan = rs2.getBoolean("grafik_kunjungan_statusdaftarbulan");
                    var.grafik_kunjungan_statusdaftarbulan2 = rs2.getBoolean("grafik_kunjungan_statusdaftarbulan2");
                    var.grafik_kunjungan_statusdaftartanggal = rs2.getBoolean("grafik_kunjungan_statusdaftartanggal");
                    var.grafik_kunjungan_statusdaftartanggal2 = rs2.getBoolean("grafik_kunjungan_statusdaftartanggal2");
                    var.grafik_kunjungan_statusbataltahun = rs2.getBoolean("grafik_kunjungan_statusbataltahun");
                    var.grafik_kunjungan_statusbatalbulan = rs2.getBoolean("grafik_kunjungan_statusbatalbulan");
                    var.pcare_cek_penyakit = rs2.getBoolean("pcare_cek_penyakit");
                    var.grafik_kunjungan_statusbataltanggal = rs2.getBoolean("grafik_kunjungan_statusbataltanggal");
                    var.kategori_barang = rs2.getBoolean("kategori_barang");
                    var.golongan_barang = rs2.getBoolean("golongan_barang");
                    var.pemberian_obat_pertanggal = rs2.getBoolean("pemberian_obat_pertanggal");
                    var.penjualan_obat_pertanggal = rs2.getBoolean("penjualan_obat_pertanggal");
                    var.bpjs_rujukan_vclaim = rs2.getBoolean("rujukan_keluar_vclaim_bpjs");
                    var.skdp_bpjs = rs2.getBoolean("skdp_bpjs");
                    var.booking_registrasi = rs2.getBoolean("booking_registrasi");
                    var.bpjs_cek_riwayat_rujukan_pcare = rs2.getBoolean("bpjs_cek_riwayat_rujukan_pcare");
                    var.bpjs_cek_riwayat_rujukan_rs = rs2.getBoolean("bpjs_cek_riwayat_rujukan_rs");
                    var.bpjs_cek_rujukan_kartu_rs = rs2.getBoolean("bpjs_cek_rujukan_kartu_rs");
                    var.bpjs_cek_tgl_rujukan = rs2.getBoolean("bpjs_cek_tanggal_rujukan");
                    var.bpjs_cek_no_rujukan_rs = rs2.getBoolean("bpjs_cek_no_rujukan_rs");
                    var.bpjs_cek_rujukan_kartu_pcare = rs2.getBoolean("bpjs_cek_rujukan_kartu_pcare");
                    var.bpjs_cek_referensi_kelas_rawat = rs2.getBoolean("bpjs_cek_referensi_kelas_rawat");
                    var.bpjs_cek_referensi_prosedur = rs2.getBoolean("bpjs_cek_referensi_prosedur");
                    var.bpjs_cek_referensi_dpjp = rs2.getBoolean("bpjs_cek_referensi_dpjp");
                    var.bpjs_cek_referensi_dokter = rs2.getBoolean("bpjs_cek_referensi_dokter");
                    var.bpjs_cek_referensi_spesialistik = rs2.getBoolean("bpjs_cek_referensi_spesialistik");
                    var.bpjs_cek_referensi_ruang_rawat = rs2.getBoolean("bpjs_cek_referensi_ruang_rawat");
                    var.bpjs_cek_referensi_cara_keluar = rs2.getBoolean("bpjs_cek_referensi_cara_keluar");
                    var.bpjs_cek_referensi_pasca_pulang = rs2.getBoolean("bpjs_cek_referensi_pasca_pulang");
                    var.bpjs_cek_referensi_propinsi = rs2.getBoolean("bpjs_cek_referensi_propinsi");
                    var.bpjs_cek_referensi_kabupaten = rs2.getBoolean("bpjs_cek_referensi_kabupaten");
                    var.bpjs_cek_referensi_kecamatan = rs2.getBoolean("bpjs_cek_referensi_kecamatan");
                    var.permintaan_lab = rs2.getBoolean("permintaan_lab");
                    var.permintaan_radiologi = rs2.getBoolean("permintaan_radiologi");
                    var.selisih_tarif_bpjs = rs2.getBoolean("selisih_tarif_bpjs");
                    var.edit_data_kematian = rs2.getBoolean("edit_data_kematian");
                    var.bridging_jamkesda = rs2.getBoolean("bridging_jamkesda");
                    var.masuk_pindah_pulang_inap = rs2.getBoolean("masuk_pindah_pulang_inap");
                    var.masuk_pindah_inap = rs2.getBoolean("masuk_pindah_inap");
                    var.jumlah_macam_diet = rs2.getBoolean("jumlah_macam_diet");
                    var.jumlah_porsi_diet = rs2.getBoolean("jumlah_porsi_diet");
                    var.status_gizi = rs2.getBoolean("status_gizi");
                    var.gizi_buruk = rs2.getBoolean("gizi_buruk");
                    var.master_faskes = rs2.getBoolean("master_faskes");
                    var.setstatusralan = rs2.getBoolean("set_status_registrasi_ralan");
                    var.telusurpasien = rs2.getBoolean("telusur_kunjungan_pasien");
                    var.sisrute_rujukan_keluar = rs2.getBoolean("sisrute_rujukan_keluar");
                    var.sisrute_rujukan_masuk = rs2.getBoolean("sisrute_rujukan_masuk");
                    var.sisrute_referensi_diagnosa = rs2.getBoolean("sisrute_referensi_diagnosa");
                    var.sisrute_referensi_alasanrujuk = rs2.getBoolean("sisrute_referensi_alasanrujuk");
                    var.sisrute_referensi_faskes = rs2.getBoolean("sisrute_referensi_faskes");
                    var.barang_cssd = rs2.getBoolean("barang_cssd");
                    var.status_pulang_inap = rs2.getBoolean("status_pulang_inap");                    
                    var.data_persalinan = rs2.getBoolean("data_persalinan");
                    var.data_ponek = rs2.getBoolean("data_ponek");
                    var.reg_boking_kasir = rs2.getBoolean("registrasi_booking_dikasir");
                    var.bahasa_pasien = rs2.getBoolean("bahasa_pasien");
                    var.suku_bangsa = rs2.getBoolean("suku_pasien");
                    var.harian_hais_inap = rs2.getBoolean("harian_hais_ranap");
                    var.harian_hais_jalan = rs2.getBoolean("harian_hais_ralan");
                    var.bulanan_hais_inap = rs2.getBoolean("bulanan_hais_ranap");
                    var.bulanan_hais_jalan = rs2.getBoolean("bulanan_hais_ralan");
                    var.ringkasan_pulang_ranap = rs2.getBoolean("ringkasan_pulang_ranap");
                    var.laporan_farmasi = rs2.getBoolean("laporan_farmasi");
                    var.master_masalah_keperawatan = rs2.getBoolean("master_masalah_keperawatan");
                    var.penilaian_awal_keperawatan_ralan = rs2.getBoolean("penilaian_awal_keperawatan_ralan");
                    var.master_triase_skala1 = rs2.getBoolean("master_triase_skala1"); 
                    var.master_triase_skala2 = rs2.getBoolean("master_triase_skala2"); 
                    var.master_triase_skala3 = rs2.getBoolean("master_triase_skala3");                    
                    var.master_triase_skala4 = rs2.getBoolean("master_triase_skala4"); 
                    var.master_triase_skala5 = rs2.getBoolean("master_triase_skala5");                    
                    var.data_triase_igd = rs2.getBoolean("data_triase_igd");  
                    var.master_triase_pemeriksaan = rs2.getBoolean("master_triase_pemeriksaan"); 
                    var.master_triase_macamkasus = rs2.getBoolean("master_triase_macamkasus"); 
                    var.master_cara_bayar = rs2.getBoolean("master_cara_bayar");
                    var.status_kerja_dokter = rs2.getBoolean("status_kerja_dokter");
                    var.pasien_corona = rs2.getBoolean("pasien_corona");
                    var.diagnosa_pasien_corona = rs2.getBoolean("diagnosa_pasien_corona");
                    var.perawatan_pasien_corona = rs2.getBoolean("perawatan_pasien_corona");
                    var.inacbg_klaim_baru_manual2 = rs2.getBoolean("inacbg_klaim_baru_manual2");
                    var.assesmen_gizi_harian = rs2.getBoolean("assesmen_gizi_harian");
                    var.assesmen_gizi_ulang = rs2.getBoolean("assesmen_gizi_ulang");
                    var.tombol_nota_billing = rs2.getBoolean("tombol_nota_billing");
                    var.tombol_simpan_hasil_rad = rs2.getBoolean("tombol_simpan_hasil_radiologi");
                    var.monev_asuhan_gizi = rs2.getBoolean("monev_asuhan_gizi");
                    var.inacbg_klaim_raza = rs2.getBoolean("inacbg_klaim_raza");
                    var.pengajuan_klaim_inacbg_raza = rs2.getBoolean("pengajuan_klaim_inacbg_raza");
                    var.copy_pemeriksaan_dokter_kepetugas_ralan = rs2.getBoolean("copy_pemeriksaan_dokter_kepetugas_ralan");
                    var.jkn_belum_diproses_klaim = rs2.getBoolean("jkn_belum_diproses_klaim");
                    var.input_kode_icd = rs2.getBoolean("input_kode_icd");
                    var.kendali_Mutu_kendali_Biaya_INACBG = rs2.getBoolean("kendali_Mutu_kendali_Biaya_INACBG");
                    var.dashboard_eResep = rs2.getBoolean("dashboard_eResep");
                    var.bpjs_sep_internal = rs2.getBoolean("bpjs_sep_internal");
                    var.kemenkes_sitt = rs2.getBoolean("kemenkes_sitt");
                    var.rencana_kontrol_jkn = rs2.getBoolean("rencana_kontrol_jkn");
                    var.spri_jkn = rs2.getBoolean("spri_jkn");
                    var.hapus_sep = rs2.getBoolean("hapus_sep");
                    var.penilaian_awal_medis_ralan_kebidanan = rs2.getBoolean("penilaian_awal_medis_ralan_kebidanan");
                    var.penilaian_awal_keperawatan_kebidanan = rs2.getBoolean("penilaian_awal_keperawatan_kebidanan");
                    var.ikhtisar_perawatan_hiv = rs2.getBoolean("ikhtisar_perawatan_hiv");
                    var.survey_kepuasan = rs2.getBoolean("survey_kepuasan");
                    var.kemenkes_kanker = rs2.getBoolean("kemenkes_kanker");
                    var.aktivasi_bridging = rs2.getBoolean("aktivasi_bridging");
                    var.operator_antrian = rs2.getBoolean("operator_antrian");
                } else if ((rs.getRow() == 0) && (rs2.getRow() == 0)) {
                    var.kode = "";
                    var.penyakit = false;
                    var.obat_penyakit = false;
                    var.dokter = false;
                    var.jadwal_praktek = false;
                    var.petugas = false;
                    var.pasien = false;
                    var.registrasi = false;
                    var.tindakan_ralan = false;
                    var.kamar_inap = false;
                    var.tindakan_ranap = false;
                    var.operasi = false;
                    var.rujukan_keluar = false;
                    var.rujukan_masuk = false;
                    var.beri_obat = false;
                    var.resep_pulang = false;
                    var.pasien_meninggal = false;
                    var.diet_pasien = false;
                    var.kelahiran_bayi = false;
                    var.periksa_lab = false;
                    var.periksa_radiologi = false;
                    var.kasir_ralan = false;
                    var.deposit_pasien = false;
                    var.piutang_pasien = false;
                    var.peminjaman_berkas = false;
                    var.barcode = false;
                    var.presensi_harian = false;
                    var.presensi_bulanan = false;
                    var.pegawai_admin = false;
                    var.pegawai_user = false;
                    var.suplier = false;
                    var.satuan_barang = false;
                    var.konversi_satuan = false;
                    var.jenis_barang = false;
                    var.obat = false;
                    var.stok_opname_obat = false;
                    var.stok_obat_pasien = false;
                    var.pengadaan_obat = false;
                    var.pemesanan_obat = false;
                    var.penjualan_obat = false;
                    var.piutang_obat = false;
                    var.retur_ke_suplier = false;
                    var.retur_dari_pembeli = false;
                    var.retur_obat_ranap = false;
                    var.retur_piutang_pasien = false;
                    var.keuntungan_penjualan = false;
                    var.keuntungan_beri_obat = false;
                    var.sirkulasi_obat = false;
                    var.ipsrs_barang = false;
                    var.ipsrs_pengadaan_barang = false;
                    var.ipsrs_stok_keluar = false;
                    var.ipsrs_rekap_pengadaan = false;
                    var.ipsrs_rekap_stok_keluar = false;
                    var.ipsrs_pengeluaran_harian = false;
                    var.ipsrs_jenis_barang = false;
                    var.inventaris_jenis = false;
                    var.inventaris_kategori = false;
                    var.inventaris_merk = false;
                    var.inventaris_ruang = false;
                    var.inventaris_produsen = false;
                    var.inventaris_koleksi = false;
                    var.inventaris_inventaris = false;
                    var.inventaris_sirkulasi = false;
                    var.parkir_jenis = false;
                    var.parkir_in = false;
                    var.parkir_out = false;
                    var.parkir_rekap_harian = false;
                    var.parkir_rekap_bulanan = false;
                    var.informasi_kamar = false;
                    var.harian_tindakan_poli = false;
                    var.obat_per_poli = false;
                    var.obat_per_kamar = false;
                    var.obat_per_dokter_ralan = false;
                    var.obat_per_dokter_ranap = false;
                    var.harian_dokter = false;
                    var.bulanan_dokter = false;
                    var.harian_paramedis = false;
                    var.bulanan_paramedis = false;
                    var.pembayaran_ralan = false;
                    var.pembayaran_ranap = false;
                    var.rekap_pembayaran_ralan = false;
                    var.rekap_pembayaran_ranap = false;
                    var.tagihan_masuk = false;
                    var.tambahan_biaya = false;
                    var.potongan_biaya = false;
                    var.resep_obat = false;
                    var.resume_pasien = false;
                    var.penyakit_ralan = false;
                    var.penyakit_ranap = false;
                    var.kamar = false;
                    var.tarif_ralan = false;
                    var.tarif_ranap = false;
                    var.tarif_lab = false;
                    var.tarif_radiologi = false;
                    var.tarif_operasi = false;
                    var.akun_rekening = false;
                    var.rekening_tahun = false;
                    var.posting_jurnal = false;
                    var.buku_besar = false;
                    var.cashflow = false;
                    var.keuangan = false;
                    var.pengeluaran = false;
                    var.setup_pjlab = false;
                    var.setup_otolokasi = false;
                    var.setup_jam_kamin = false;
                    var.setup_embalase = false;
                    var.tracer_login = false;
                    var.display = false;
                    var.set_harga_obat = false;
                    var.set_penggunaan_tarif = false;
                    var.set_oto_ralan = false;
                    var.biaya_harian = false;
                    var.biaya_masuk_sekali = false;
                    var.set_no_rm = false;
                    var.billing_ralan = false;
                    var.billing_ranap = false;
                    var.jm_ranap_dokter = false;
                    var.igd = false;
                    var.barcoderalan = false;
                    var.barcoderanap = false;
                    var.set_harga_obat_ralan = false;
                    var.set_harga_obat_ranap = false;
                    var.admin = false;
                    var.user = false;
                    var.vakum = false;
                    var.aplikasi = false;
                    var.penyakit_pd3i = false;
                    var.surveilans_pd3i = false;
                    var.surveilans_ralan = false;
                    var.diagnosa_pasien = false;
                    var.surveilans_ranap = false;
                    var.pny_takmenular_ranap = false;
                    var.pny_takmenular_ralan = false;
                    var.kunjungan_ralan = false;
                    var.rl32 = false;
                    var.rl33 = false;
                    var.rl37 = false;
                    var.rl38 = false;
                    var.harian_tindakan_dokter = false;
                    var.sms = false;
                    var.sidikjari = false;
                    var.jam_masuk = false;
                    var.jadwal_pegawai = false;
                    var.parkir_barcode = false;
                    var.set_nota = false;
                    var.dpjp_ranap = false;
                    var.mutasi_barang = false;
                    var.rl34 = false;
                    var.rl36 = false;
                    var.fee_visit_dokter = false;
                    var.fee_bacaan_ekg = false;
                    var.fee_rujukan_rontgen = false;
                    var.fee_rujukan_ranap = false;
                    var.fee_ralan = false;
                    var.akun_bayar = false;
                    var.bayar_pemesanan_obat = false;
                    var.obat_per_dokter_peresep = false;
                    var.pemasukan_lain = false;
                    var.pengaturan_rekening = false;
                    var.closing_kasir = false;
                    var.keterlambatan_presensi = false;
                    var.set_harga_kamar = false;
                    var.rekap_per_shift = false;
                    var.bpjs_cek_nik = false;
                    var.bpjs_cek_kartu = false;
                    var.obat_per_cara_bayar = false;
                    var.kunjungan_ranap = false;
                    var.bayar_piutang = false;
                    var.payment_point = false;
                    var.bpjs_cek_nomor_rujukan = false;
                    var.icd9 = false;
                    var.darurat_stok = false;
                    var.retensi_rm = false;
                    var.temporary_presensi = false;
                    var.jurnal_harian = false;
                    var.sirkulasi_obat2 = false;
                    var.edit_registrasi = false;
                    var.bpjs_referensi_diagnosa = false;
                    var.bpjs_referensi_poli = false;
                    var.industrifarmasi = false;
                    var.harian_js = false;
                    var.bulanan_js = false;
                    var.harian_paket_bhp = false;
                    var.bulanan_paket_bhp = false;
                    var.piutang_pasien2 = false;
                    var.bpjs_referensi_faskes = false;
                    var.bpjs_sep = false;
                    var.pengambilan_utd = false;
                    var.tarif_utd = false;
                    var.pengambilan_utd2 = false;
                    var.utd_medis_rusak = false;
                    var.pengambilan_penunjang_utd = false;
                    var.pengambilan_penunjang_utd2 = false;
                    var.utd_penunjang_rusak = false;
                    var.suplier_penunjang = false;
                    var.utd_donor = false;
                    var.bpjs_monitoring_klaim = false;
                    var.utd_cekal_darah = false;
                    var.utd_komponen_darah = false;
                    var.utd_stok_darah = false;
                    var.utd_pemisahan_darah = false;
                    var.harian_kamar = false;
                    var.rincian_piutang_pasien = false;
                    var.keuntungan_beri_obat_nonpiutang = false;
                    var.reklasifikasi_ralan = false;
                    var.reklasifikasi_ranap = false;
                    var.utd_penyerahan_darah = false;
                    var.hutang_obat = false;
                    var.riwayat_obat_alkes_bhp = false;
                    var.sensus_harian_poli = false;
                    var.rl4a = false;
                    var.aplicare_referensi_kamar = false;
                    var.aplicare_ketersediaan_kamar = false;
                    var.inacbg_klaim_baru_otomatis = false;
                    var.inacbg_klaim_baru_manual = false;
                    var.inacbg_coder_nik = false;
                    var.mutasi_berkas = false;
                    var.akun_piutang = false;
                    var.harian_kso = false;
                    var.bulanan_kso = false;
                    var.harian_menejemen = false;
                    var.bulanan_menejemen = false;
                    var.inhealth_cek_eligibilitas = false;
                    var.inhealth_referensi_jenpel_ruang_rawat = false;
                    var.inhealth_referensi_poli = false;
                    var.inhealth_referensi_faskes = false;
                    var.inhealth_sjp = false;
                    var.piutang_ralan = false;
                    var.piutang_ranap = false;
                    var.detail_piutang_penjab = false;
                    var.lama_pelayanan_ralan = false;
                    var.catatan_pasien = false;
                    var.rl4b = false;
                    var.rl4asebab = false;
                    var.rl4bsebab = false;
                    var.data_HAIs = false;
                    var.harian_HAIs = false;
                    var.bulanan_HAIs = false;
                    var.hitung_bor = false;
                    var.namauser = rs2.getString("nama");
                    var.perusahaan_pasien = false;
                    var.resep_dokter = false;
                    var.lama_pelayanan_apotek = false;
                    var.hitung_alos = false;
                    var.detail_tindakan = false;
                    var.rujukan_poli_internal = false;
                    var.rekap_poli_anak = false;
                    var.grafik_kunjungan_poli = false;
                    var.grafik_kunjungan_perdokter = false;
                    var.grafik_kunjungan_perpekerjaan = false;
                    var.grafik_kunjungan_perpendidikan = false;
                    var.grafik_kunjungan_pertahun = false;
                    var.berkas_digital_perawatan = false;
                    var.penyakit_menular_ranap = false;
                    var.penyakit_menular_ralan = false;
                    var.grafik_kunjungan_perbulan = false;
                    var.grafik_kunjungan_pertanggal = false;
                    var.grafik_kunjungan_demografi = false;
                    var.grafik_kunjungan_statusdaftartahun = false;
                    var.grafik_kunjungan_statusdaftartahun2 = false;
                    var.grafik_kunjungan_statusdaftarbulan = false;
                    var.grafik_kunjungan_statusdaftarbulan2 = false;
                    var.grafik_kunjungan_statusdaftartanggal = false;
                    var.grafik_kunjungan_statusdaftartanggal2 = false;
                    var.grafik_kunjungan_statusbataltahun = false;
                    var.grafik_kunjungan_statusbatalbulan = false;
                    var.pcare_cek_penyakit = false;
                    var.grafik_kunjungan_statusbataltanggal = false;
                    var.kategori_barang = false;
                    var.golongan_barang = false;
                    var.pemberian_obat_pertanggal = false;
                    var.penjualan_obat_pertanggal = false;
                    var.bpjs_rujukan_vclaim = false;
                    var.skdp_bpjs = false;
                    var.booking_registrasi = false;
                    var.bpjs_cek_riwayat_rujukan_pcare = false;
                    var.bpjs_cek_riwayat_rujukan_rs = false;
                    var.bpjs_cek_rujukan_kartu_rs = false;
                    var.bpjs_cek_tgl_rujukan = false;
                    var.bpjs_cek_no_rujukan_rs = false;
                    var.bpjs_cek_rujukan_kartu_pcare = false;
                    var.bpjs_cek_referensi_kelas_rawat = false;
                    var.bpjs_cek_referensi_prosedur = false;
                    var.bpjs_cek_referensi_dpjp = false;
                    var.bpjs_cek_referensi_dokter = false;
                    var.bpjs_cek_referensi_spesialistik = false;
                    var.bpjs_cek_referensi_ruang_rawat = false;
                    var.bpjs_cek_referensi_cara_keluar = false;
                    var.bpjs_cek_referensi_pasca_pulang = false;
                    var.bpjs_cek_referensi_propinsi = false;
                    var.bpjs_cek_referensi_kabupaten = false;
                    var.bpjs_cek_referensi_kecamatan = false;
                    var.permintaan_lab = false;
                    var.permintaan_radiologi = false;
                    var.selisih_tarif_bpjs = false;
                    var.edit_data_kematian = false;
                    var.bridging_jamkesda = false;
                    var.masuk_pindah_pulang_inap = false;
                    var.masuk_pindah_inap = false;
                    var.jumlah_macam_diet = false;
                    var.jumlah_porsi_diet = false;
                    var.status_gizi = false;
                    var.gizi_buruk = false;
                    var.master_faskes = false;
                    var.setstatusralan = false;
                    var.telusurpasien = false;
                    var.sisrute_rujukan_keluar = false;
                    var.sisrute_rujukan_masuk = false;
                    var.sisrute_referensi_diagnosa = false;
                    var.sisrute_referensi_alasanrujuk = false;
                    var.sisrute_referensi_faskes = false;
                    var.barang_cssd = false;
                    var.status_pulang_inap = false;
                    var.data_persalinan = false;
                    var.data_ponek = false;
                    var.reg_boking_kasir = false;
                    var.bahasa_pasien = false;
                    var.suku_bangsa = false;
                    var.harian_hais_inap = false;
                    var.harian_hais_jalan = false;
                    var.bulanan_hais_inap = false;
                    var.bulanan_hais_jalan = false;
                    var.ringkasan_pulang_ranap = false;
                    var.laporan_farmasi = false;
                    var.master_masalah_keperawatan = false;
                    var.penilaian_awal_keperawatan_ralan = false;
                    var.master_triase_skala1 = false;
                    var.master_triase_skala2 = false;
                    var.master_triase_skala3 = false;
                    var.master_triase_skala4 = false;
                    var.master_triase_skala5 = false;
                    var.data_triase_igd = false;
                    var.master_triase_pemeriksaan = false;
                    var.master_triase_macamkasus = false;
                    var.master_cara_bayar = false;
                    var.status_kerja_dokter = false;
                    var.pasien_corona = false;
                    var.diagnosa_pasien_corona = false;
                    var.perawatan_pasien_corona = false;
                    var.inacbg_klaim_baru_manual2 = false;
                    var.assesmen_gizi_harian = false;
                    var.assesmen_gizi_ulang = false;
                    var.tombol_nota_billing = false;
                    var.tombol_simpan_hasil_rad = false;
                    var.monev_asuhan_gizi = false;
                    var.inacbg_klaim_raza = false;
                    var.pengajuan_klaim_inacbg_raza = false;
                    var.copy_pemeriksaan_dokter_kepetugas_ralan = false;
                    var.jkn_belum_diproses_klaim = false;
                    var.input_kode_icd = false;
                    var.kendali_Mutu_kendali_Biaya_INACBG = false;
                    var.dashboard_eResep = false;
                    var.bpjs_sep_internal = false;
                    var.kemenkes_sitt = false;
                    var.rencana_kontrol_jkn = false;
                    var.spri_jkn = false;
                    var.hapus_sep = false;
                    var.penilaian_awal_medis_ralan_kebidanan = false;
                    var.penilaian_awal_keperawatan_kebidanan = false;
                    var.ikhtisar_perawatan_hiv = false;
                    var.survey_kepuasan = false;
                    var.kemenkes_kanker = false;
                    var.aktivasi_bridging = false;
                    var.operator_antrian = false;
                }
            } catch (Exception e) {
                System.out.println("Notifikasi : " + e);
            } finally {
                if (rs != null) {
                    rs.close();
                }
                if (rs2 != null) {
                    rs2.close();
                }
                if (ps != null) {
                    ps.close();
                }
                if (ps2 != null) {
                    ps2.close();
                }
            }
        } catch (Exception e) {
            System.out.println("Notifikasi : " + e);
        }
    }
    
    public static void setNMLoket(String nmrawat) {
        if (nmrawat.equals("ralan")) {
            var.jenisLoket = "Rawat Jalan";
        } else if (nmrawat.equals("ranap")) {
            var.jenisLoket = "Rawat Inap";
        } else {
            var.jenisLoket = "";
        }
    }

    public static void setNomorLoket(String noloket) {
        if (noloket.equals("1")) {
            var.nomorLoket = "1";
        } else if (noloket.equals("2")) {
            var.nomorLoket = "2";
        } else if (noloket.equals("3")) {
            var.nomorLoket = "3";
        } else if (noloket.equals("4")) {
            var.nomorLoket = "4";
        } else if (noloket.equals("5")) {
            var.nomorLoket = "5";
        } else if (noloket.equals("6")) {
            var.nomorLoket = "6";
        } else if (noloket.equals("")) {
            var.nomorLoket = "";
        }
    }

    public static int getjml1() {
        return var.jml1;
    }

    public static int getjml2() {
        return var.jml2;
    }

    public static boolean getadmin() {
        return var.admin;
    }    
    
    public static boolean getuser() {
        return var.user;
    }

    public static boolean getvakum() {
        return var.vakum;
    }

    public static boolean getaplikasi() {
        return var.aplikasi;
    }

    public static boolean getpenyakit() {
        return var.penyakit;
    }

    public static boolean getobat_penyakit() {
        return var.obat_penyakit;
    }

    public static boolean getdokter() {
        return var.dokter;
    }

    public static boolean getjadwal_praktek() {
        return var.jadwal_praktek;
    }

    public static boolean getpetugas() {
        return var.petugas;
    }

    public static boolean getpasien() {
        return var.pasien;
    }

    public static boolean getregistrasi() {
        return var.registrasi;
    }

    public static boolean gettindakan_ralan() {
        return var.tindakan_ralan;
    }

    public static boolean getkamar_inap() {
        return var.kamar_inap;
    }

    public static boolean gettindakan_ranap() {
        return var.tindakan_ranap;
    }

    public static boolean getoperasi() {
        return var.operasi;
    }

    public static boolean getrujukan_keluar() {
        return var.rujukan_keluar;
    }

    public static boolean getrujukan_masuk() {
        return var.rujukan_masuk;
    }

    public static boolean getberi_obat() {
        return var.beri_obat;
    }

    public static boolean getresep_pulang() {
        return var.resep_pulang;
    }

    public static boolean getpasien_meninggal() {
        return var.pasien_meninggal;
    }

    public static boolean getdiet_pasien() {
        return var.diet_pasien;
    }

    public static boolean getkelahiran_bayi() {
        return var.kelahiran_bayi;
    }

    public static boolean getperiksa_lab() {
        return var.periksa_lab;
    }

    public static boolean getperiksa_radiologi() {
        return var.periksa_radiologi;
    }

    public static boolean getkasir_ralan() {
        return var.kasir_ralan;
    }

    public static boolean getdeposit_pasien() {
        return var.deposit_pasien;
    }

    public static boolean getpiutang_pasien() {
        return var.piutang_pasien;
    }

    public static boolean getpeminjaman_berkas() {
        return var.peminjaman_berkas;
    }

    public static boolean getbarcode() {
        return var.barcode;
    }

    public static boolean getpresensi_harian() {
        return var.presensi_harian;
    }

    public static boolean getpresensi_bulanan() {
        return var.presensi_bulanan;
    }

    public static boolean getpegawai_admin() {
        return var.pegawai_admin;
    }

    public static boolean getpegawai_user() {
        return var.pegawai_user;
    }

    public static boolean getsuplier() {
        return var.suplier;
    }

    public static boolean getsatuan_barang() {
        return var.satuan_barang;
    }

    public static boolean getkonversi_satuan() {
        return var.konversi_satuan;
    }

    public static boolean getjenis_barang() {
        return var.jenis_barang;
    }

    public static boolean getobat() {
        return var.obat;
    }

    public static boolean getstok_opname_obat() {
        return var.stok_opname_obat;
    }

    public static boolean getstok_obat_pasien() {
        return var.stok_obat_pasien;
    }

    public static boolean getpengadaan_obat() {
        return var.pengadaan_obat;
    }

    public static boolean getpemesanan_obat() {
        return var.pemesanan_obat;
    }

    public static boolean getpenjualan_obat() {
        return var.penjualan_obat;
    }

    public static void setpenjualan_obatfalse() {
        var.penjualan_obat = false;
    }

    public static boolean getpiutang_obat() {
        return var.piutang_obat;
    }

    public static boolean getretur_ke_suplier() {
        return var.retur_ke_suplier;
    }

    public static boolean getretur_dari_pembeli() {
        return var.retur_dari_pembeli;
    }

    public static boolean getretur_obat_ranap() {
        return var.retur_obat_ranap;
    }

    public static boolean getretur_piutang_pasien() {
        return var.retur_piutang_pasien;
    }

    public static boolean getkeuntungan_penjualan() {
        return var.keuntungan_penjualan;
    }

    public static boolean getkeuntungan_beri_obat() {
        return var.keuntungan_beri_obat;
    }

    public static boolean getsirkulasi_obat() {
        return var.sirkulasi_obat;
    }

    public static boolean getipsrs_barang() {
        return var.ipsrs_barang;
    }

    public static boolean getipsrs_pengadaan_barang() {
        return var.ipsrs_pengadaan_barang;
    }

    public static boolean getipsrs_stok_keluar() {
        return var.ipsrs_stok_keluar;
    }

    public static boolean getipsrs_rekap_pengadaan() {
        return var.ipsrs_rekap_pengadaan;
    }

    public static boolean getipsrs_rekap_stok_keluar() {
        return var.ipsrs_rekap_stok_keluar;
    }

    public static boolean getipsrs_pengeluaran_harian() {
        return var.ipsrs_pengeluaran_harian;
    }

    public static boolean getipsrs_jenis_barang() {
        return var.ipsrs_jenis_barang;
    }

    public static boolean getinventaris_jenis() {
        return var.inventaris_jenis;
    }

    public static boolean getinventaris_kategori() {
        return var.inventaris_kategori;
    }

    public static boolean getinventaris_merk() {
        return var.inventaris_merk;
    }

    public static boolean getinventaris_ruang() {
        return var.inventaris_ruang;
    }

    public static boolean getinventaris_produsen() {
        return var.inventaris_produsen;
    }

    public static boolean getinventaris_koleksi() {
        return var.inventaris_koleksi;
    }

    public static boolean getinventaris_inventaris() {
        return var.inventaris_inventaris;
    }

    public static boolean getinventaris_sirkulasi() {
        return var.inventaris_sirkulasi;
    }

    public static boolean getparkir_jenis() {
        return var.parkir_jenis;
    }

    public static boolean getparkir_in() {
        return var.parkir_in;
    }

    public static boolean getparkir_out() {
        return var.parkir_out;
    }

    public static boolean getparkir_rekap_harian() {
        return var.parkir_rekap_harian;
    }

    public static boolean getparkir_rekap_bulanan() {
        return var.parkir_rekap_bulanan;
    }

    public static boolean getinformasi_kamar() {
        return var.informasi_kamar;
    }

    public static boolean getharian_tindakan_poli() {
        return var.harian_tindakan_poli;
    }

    public static boolean getobat_per_poli() {
        return var.obat_per_poli;
    }

    public static boolean getobat_per_kamar() {
        return var.obat_per_kamar;
    }

    public static boolean getobat_per_dokter_ralan() {
        return var.obat_per_dokter_ralan;
    }

    public static boolean getobat_per_dokter_ranap() {
        return var.obat_per_dokter_ranap;
    }

    public static boolean getharian_dokter() {
        return var.harian_dokter;
    }

    public static boolean getbulanan_dokter() {
        return var.bulanan_dokter;
    }

    public static boolean getharian_paramedis() {
        return var.harian_paramedis;
    }

    public static boolean getbulanan_paramedis() {
        return var.bulanan_paramedis;
    }

    public static boolean getpembayaran_ralan() {
        return var.pembayaran_ralan;
    }

    public static boolean getpembayaran_ranap() {
        return var.pembayaran_ranap;
    }

    public static boolean getrekap_pembayaran_ralan() {
        return var.rekap_pembayaran_ralan;
    }

    public static boolean getrekap_pembayaran_ranap() {
        return var.rekap_pembayaran_ranap;
    }

    public static boolean gettagihan_masuk() {
        return var.tagihan_masuk;
    }

    public static boolean gettambahan_biaya() {
        return var.tambahan_biaya;
    }

    public static boolean getpotongan_biaya() {
        return var.potongan_biaya;
    }

    public static boolean getresep_obat() {
        return var.resep_obat;
    }

    public static boolean getresume_pasien() {
        return var.resume_pasien;
    }

    public static boolean getpenyakit_ralan() {
        return var.penyakit_ralan;
    }

    public static boolean getpenyakit_ranap() {
        return var.penyakit_ranap;
    }

    public static boolean getkamar() {
        return var.kamar;
    }

    public static boolean gettarif_ralan() {
        return var.tarif_ralan;
    }

    public static boolean gettarif_ranap() {
        return var.tarif_ranap;
    }

    public static boolean gettarif_lab() {
        return var.tarif_lab;
    }

    public static boolean gettarif_radiologi() {
        return var.tarif_radiologi;
    }

    public static boolean gettarif_operasi() {
        return var.tarif_operasi;
    }

    public static boolean getakun_rekening() {
        return var.akun_rekening;
    }

    public static boolean getrekening_tahun() {
        return var.rekening_tahun;
    }

    public static boolean getposting_jurnal() {
        return var.posting_jurnal;
    }

    public static boolean getbuku_besar() {
        return var.buku_besar;
    }

    public static boolean getcashflow() {
        return var.cashflow;
    }

    public static boolean getkeuangan() {
        return var.keuangan;
    }

    public static boolean getpengeluaran() {
        return var.pengeluaran;
    }

    public static boolean getsetup_pjlab() {
        return var.setup_pjlab;
    }

    public static boolean getsetup_otolokasi() {
        return var.setup_otolokasi;
    }

    public static boolean getsetup_jam_kamin() {
        return var.setup_jam_kamin;
    }

    public static boolean getsetup_embalase() {
        return var.setup_embalase;
    }

    public static boolean gettracer_login() {
        return var.tracer_login;
    }

    public static boolean getdisplay() {
        return var.display;
    }

    public static boolean getset_harga_obat() {
        return var.set_harga_obat;
    }

    public static boolean getset_penggunaan_tarif() {
        return var.set_penggunaan_tarif;
    }

    public static boolean getset_oto_ralan() {
        return var.set_oto_ralan;
    }

    public static boolean getbiaya_harian() {
        return var.biaya_harian;
    }

    public static boolean getbiaya_masuk_sekali() {
        return var.biaya_masuk_sekali;
    }

    public static boolean getset_no_rm() {
        return var.set_no_rm;
    }

    public static boolean getbilling_ralan() {
        return var.billing_ralan;
    }

    public static boolean getbilling_ranap() {
        return var.billing_ranap;
    }

    public static String getkode() {
        return var.kode;
    }
    
    public static String getJenisLoket() {
        return var.jenisLoket;
    }
    
    public static String getNomorLoket() {
        return var.nomorLoket;
    }

    public static void setkdbangsal(String kdbangsal) {
        var.kdbangsal = kdbangsal;
    }

    public static String getkdbangsal() {
        return var.kdbangsal;
    }

    public static void setform(String form) {
        var.form = form;
    }

    public static String getform() {
        return var.form;
    }

    public static void setnamauser(String namauser) {
        var.namauser = namauser;
    }

    public static String getnamauser() {
        return var.namauser;
    }

    public static void setstatus(boolean status) {
        var.status = status;
    }

    public static boolean getstatus() {
        return var.status;
    }

    public static boolean getjm_ranap_dokter() {
        return var.jm_ranap_dokter;
    }

    public static boolean getigd() {
        return var.igd;
    }

    public static boolean getbarcoderalan() {
        return var.barcoderalan;
    }

    public static boolean getbarcoderanap() {
        return var.barcoderanap;
    }

    public static boolean getset_harga_obat_ralan() {
        return var.set_harga_obat_ralan;
    }

    public static boolean getset_harga_obat_ranap() {
        return var.set_harga_obat_ranap;
    }

    public static boolean getpenyakit_pd3i() {
        return var.penyakit_pd3i;
    }

    public static boolean getsurveilans_pd3i() {
        return var.surveilans_pd3i;
    }

    public static boolean getsurveilans_ralan() {
        return var.surveilans_ralan;
    }

    public static boolean getdiagnosa_pasien() {
        return var.diagnosa_pasien;
    }

    public static boolean getsurveilans_ranap() {
        return var.surveilans_ranap;
    }

    public static boolean getpny_takmenular_ranap() {
        return var.pny_takmenular_ranap;
    }

    public static boolean getpny_takmenular_ralan() {
        return var.pny_takmenular_ralan;
    }

    public static void setnamars(String namars) {
        var.namars = namars;
    }

    public static void setalamatrs(String alamatrs) {
        var.alamatrs = alamatrs;
    }

    public static void setkabupatenrs(String kabupatenrs) {
        var.kabupatenrs = kabupatenrs;
    }

    public static void setpropinsirs(String propinsirs) {
        var.propinsirs = propinsirs;
    }

    public static void setkontakrs(String kontakrs) {
        var.kontakrs = kontakrs;
    }

    public static void setemailrs(String emailrs) {
        var.emailrs = emailrs;
    }

    public static String getnamars() {
        return var.namars;
    }

    public static String getalamatrs() {
        return var.alamatrs;
    }

    public static String getkabupatenrs() {
        return var.kabupatenrs;
    }

    public static String getpropinsirs() {
        return var.propinsirs;
    }

    public static String getkontakrs() {
        return var.kontakrs;
    }

    public static String getemailrs() {
        return var.emailrs;
    }

    public static boolean getkunjungan_ralan() {
        return var.kunjungan_ralan;
    }

    public static boolean getrl32() {
        return var.rl32;
    }

    public static boolean getrl33() {
        return var.rl33;
    }

    public static boolean getrl37() {
        return var.rl37;
    }

    public static boolean getrl38() {
        return var.rl38;
    }

    public static boolean getharian_tindakan_dokter() {
        return var.harian_tindakan_dokter;
    }

    public static boolean getsms() {
        return var.sms;
    }

    public static boolean getsidikjari() {
        return var.sidikjari;
    }

    public static boolean getjam_masuk() {
        return var.jam_masuk;
    }

    public static boolean getjadwal_pegawai() {
        return var.jadwal_pegawai;
    }

    public static boolean getparkir_barcode() {
        return var.parkir_barcode;
    }

    public static boolean getset_nota() {
        return var.set_nota;
    }

    public static boolean getdpjp_ranap() {
        return var.dpjp_ranap;
    }

    public static boolean getmutasi_barang() {
        return var.mutasi_barang;
    }

    public static boolean getrl34() {
        return var.rl34;
    }

    public static boolean getrl36() {
        return var.rl36;
    }

    public static boolean getfee_visit_dokter() {
        return var.fee_visit_dokter;
    }

    public static boolean getfee_bacaan_ekg() {
        return var.fee_bacaan_ekg;
    }

    public static boolean getfee_rujukan_rontgen() {
        return var.fee_rujukan_rontgen;
    }

    public static boolean getfee_rujukan_ranap() {
        return var.fee_rujukan_ranap;
    }

    public static boolean getfee_ralan() {
        return var.fee_ralan;
    }

    public static boolean getakun_bayar() {
        return var.akun_bayar;
    }

    public static boolean getbayar_pemesanan_obat() {
        return var.bayar_pemesanan_obat;
    }

    public static boolean getobat_per_dokter_peresep() {
        return var.obat_per_dokter_peresep;
    }

    public static boolean getpemasukan_lain() {
        return var.pemasukan_lain;
    }

    public static boolean getpengaturan_rekening() {
        return var.pengaturan_rekening;
    }

    public static boolean getclosing_kasir() {
        return var.closing_kasir;
    }

    public static boolean getketerlambatan_presensi() {
        return var.keterlambatan_presensi;
    }

    public static boolean getset_harga_kamar() {
        return var.set_harga_kamar;
    }

    public static boolean getrekap_per_shift() {
        return var.rekap_per_shift;
    }

    public static boolean getbpjs_cek_nik() {
        return var.bpjs_cek_nik;
    }

    public static boolean getbpjs_cek_kartu() {
        return var.bpjs_cek_kartu;
    }

    public static boolean getobat_per_cara_bayar() {
        return var.obat_per_cara_bayar;
    }

    public static boolean getkunjungan_ranap() {
        return var.kunjungan_ranap;
    }

    public static boolean getbayar_piutang() {
        return var.bayar_piutang;
    }

    public static boolean getpayment_point() {
        return var.payment_point;
    }

    public static boolean getbpjs_cek_nomor_rujukan() {
        return var.bpjs_cek_nomor_rujukan;
    }

    public static boolean geticd9() {
        return var.icd9;
    }

    public static boolean getdarurat_stok() {
        return var.darurat_stok;
    }

    public static boolean getretensi_rm() {
        return var.retensi_rm;
    }

    public static boolean gettemporary_presensi() {
        return var.temporary_presensi;
    }

    public static boolean getjurnal_harian() {
        return var.jurnal_harian;
    }

    public static boolean getsirkulasi_obat2() {
        return var.sirkulasi_obat2;
    }

    public static boolean getedit_registrasi() {
        return var.edit_registrasi;
    }

    public static boolean getbpjs_referensi_diagnosa() {
        return var.bpjs_referensi_diagnosa;
    }

    public static boolean getbpjs_referensi_poli() {
        return var.bpjs_referensi_poli;
    }

    public static boolean getindustrifarmasi() {
        return var.industrifarmasi;
    }

    public static boolean getharian_js() {
        return var.harian_js;
    }

    public static boolean getbulanan_js() {
        return var.bulanan_js;
    }

    public static boolean getharian_paket_bhp() {
        return var.harian_paket_bhp;
    }

    public static boolean getbulanan_paket_bhp() {
        return var.bulanan_paket_bhp;
    }

    public static boolean getpiutang_pasien2() {
        return var.piutang_pasien2;
    }

    public static boolean getbpjs_referensi_faskes() {
        return var.bpjs_referensi_faskes;
    }

    public static boolean getbpjs_sep() {
        return var.bpjs_sep;
    }

    public static boolean getpengambilan_utd() {
        return var.pengambilan_utd;
    }

    public static boolean gettarif_utd() {
        return var.tarif_utd;
    }

    public static boolean getpengambilan_utd2() {
        return var.pengambilan_utd2;
    }

    public static boolean getutd_medis_rusak() {
        return var.utd_medis_rusak;
    }

    public static boolean getpengambilan_penunjang_utd() {
        return var.pengambilan_penunjang_utd;
    }

    public static boolean getpengambilan_penunjang_utd2() {
        return var.pengambilan_penunjang_utd2;
    }

    public static boolean getutd_penunjang_rusak() {
        return var.utd_penunjang_rusak;
    }

    public static boolean getsuplier_penunjang() {
        return var.suplier_penunjang;
    }

    public static boolean getutd_donor() {
        return var.utd_donor;
    }

    public static boolean getbpjs_monitoring_klaim() {
        return var.bpjs_monitoring_klaim;
    }

    public static boolean getutd_cekal_darah() {
        return var.utd_cekal_darah;
    }

    public static boolean getutd_komponen_darah() {
        return var.utd_komponen_darah;
    }

    public static boolean getutd_stok_darah() {
        return var.utd_stok_darah;
    }

    public static boolean getutd_pemisahan_darah() {
        return var.utd_pemisahan_darah;
    }

    public static boolean getharian_kamar() {
        return var.harian_kamar;
    }

    public static boolean getrincian_piutang_pasien() {
        return var.rincian_piutang_pasien;
    }

    public static boolean getkeuntungan_beri_obat_nonpiutang() {
        return var.keuntungan_beri_obat_nonpiutang;
    }

    public static boolean getreklasifikasi_ralan() {
        return var.reklasifikasi_ralan;
    }

    public static boolean getreklasifikasi_ranap() {
        return var.reklasifikasi_ranap;
    }

    public static boolean getutd_penyerahan_darah() {
        return var.utd_penyerahan_darah;
    }

    public static void setutd_penyerahan_darahfalse() {
        var.utd_penyerahan_darah = false;
    }

    public static boolean gethutang_obat() {
        return var.hutang_obat;
    }

    public static boolean getriwayat_obat_alkes_bhp() {
        return var.riwayat_obat_alkes_bhp;
    }

    public static boolean getsensus_harian_poli() {
        return var.sensus_harian_poli;
    }

    public static boolean getrl4a() {
        return var.rl4a;
    }

    public static boolean getaplicare_referensi_kamar() {
        return var.aplicare_referensi_kamar;
    }

    public static boolean getaplicare_ketersediaan_kamar() {
        return var.aplicare_ketersediaan_kamar;
    }

    public static boolean getinacbg_klaim_baru_otomatis() {
        return var.inacbg_klaim_baru_otomatis;
    }

    public static boolean getinacbg_klaim_baru_manual() {
        return var.inacbg_klaim_baru_manual;
    }
    
    public static boolean getinacbg_klaim_baru_manual2() {
        return var.inacbg_klaim_baru_manual2;
    }

    public static boolean getinacbg_coder_nik() {
        return var.inacbg_coder_nik;
    }

    public static boolean getmutasi_berkas() {
        return var.mutasi_berkas;
    }

    public static boolean getakun_piutang() {
        return var.akun_piutang;
    }

    public static boolean getharian_kso() {
        return var.harian_kso;
    }

    public static boolean getbulanan_kso() {
        return var.bulanan_kso;
    }

    public static boolean getharian_menejemen() {
        return var.harian_menejemen;
    }

    public static boolean getbulanan_menejemen() {
        return var.bulanan_menejemen;
    }

    public static boolean getinhealth_cek_eligibilitas() {
        return var.inhealth_cek_eligibilitas;
    }

    public static boolean getinhealth_referensi_jenpel_ruang_rawat() {
        return var.inhealth_referensi_jenpel_ruang_rawat;
    }

    public static boolean getinhealth_referensi_poli() {
        return var.inhealth_referensi_poli;
    }

    public static boolean getinhealth_referensi_faskes() {
        return var.inhealth_referensi_faskes;
    }

    public static boolean getinhealth_sjp() {
        return var.inhealth_sjp;
    }

    public static boolean getpiutang_ralan() {
        return var.piutang_ralan;
    }

    public static boolean getpiutang_ranap() {
        return var.piutang_ranap;
    }

    public static boolean getdetail_piutang_penjab() {
        return var.detail_piutang_penjab;
    }

    public static boolean getlama_pelayanan_ralan() {
        return var.lama_pelayanan_ralan;
    }

    public static boolean getcatatan_pasien() {
        return var.catatan_pasien;
    }

    public static boolean getrl4b() {
        return var.rl4b;
    }

    public static boolean getrl4asebab() {
        return var.rl4asebab;
    }

    public static boolean getrl4bsebab() {
        return var.rl4bsebab;
    }

    public static boolean getdata_HAIs() {
        return var.data_HAIs;
    }

    public static boolean getharian_HAIs() {
        return var.harian_HAIs;
    }

    public static boolean getbulanan_HAIs() {
        return var.bulanan_HAIs;
    }

    public static boolean gethitung_bor() {
        return var.hitung_bor;
    }

    public static boolean getperusahaan_pasien() {
        return var.perusahaan_pasien;
    }

    public static boolean getresep_dokter() {
        return var.resep_dokter;
    }

    public static void setresep_dokterfalse() {
        var.resep_dokter = false;
    }

    public static boolean getlama_pelayanan_apotek() {
        return var.lama_pelayanan_apotek;
    }

    public static boolean gethitung_alos() {
        return var.hitung_alos;
    }

    public static boolean getdetail_tindakan() {
        return var.detail_tindakan;
    }

    public static boolean getrujukan_poli_internal() {
        return var.rujukan_poli_internal;
    }

    public static boolean getrekap_poli_anak() {
        return var.rekap_poli_anak;
    }

    public static boolean getgrafik_kunjungan_poli() {
        return var.grafik_kunjungan_poli;
    }

    public static boolean getgrafik_kunjungan_perdokter() {
        return var.grafik_kunjungan_perdokter;
    }

    public static boolean getgrafik_kunjungan_perpekerjaan() {
        return var.grafik_kunjungan_perpekerjaan;
    }

    public static boolean getgrafik_kunjungan_perpendidikan() {
        return var.grafik_kunjungan_perpendidikan;
    }

    public static boolean getgrafik_kunjungan_pertahun() {
        return var.grafik_kunjungan_pertahun;
    }

    public static boolean getberkas_digital_perawatan() {
        return var.berkas_digital_perawatan;
    }

    public static boolean getpenyakit_menular_ranap() {
        return var.penyakit_menular_ranap;
    }

    public static boolean getpenyakit_menular_ralan() {
        return var.penyakit_menular_ralan;
    }

    public static boolean getgrafik_kunjungan_perbulan() {
        return var.grafik_kunjungan_perbulan;
    }

    public static boolean getgrafik_kunjungan_pertanggal() {
        return var.grafik_kunjungan_pertanggal;
    }

    public static boolean getgrafik_kunjungan_demografi() {
        return var.grafik_kunjungan_demografi;
    }

    public static boolean getgrafik_kunjungan_statusdaftartahun() {
        return var.grafik_kunjungan_statusdaftartahun;
    }

    public static boolean getgrafik_kunjungan_statusdaftartahun2() {
        return var.grafik_kunjungan_statusdaftartahun2;
    }

    public static boolean getgrafik_kunjungan_statusdaftarbulan() {
        return var.grafik_kunjungan_statusdaftarbulan;
    }

    public static boolean getgrafik_kunjungan_statusdaftarbulan2() {
        return var.grafik_kunjungan_statusdaftarbulan2;
    }

    public static boolean getgrafik_kunjungan_statusdaftartanggal() {
        return var.grafik_kunjungan_statusdaftartanggal;
    }

    public static boolean getgrafik_kunjungan_statusdaftartanggal2() {
        return var.grafik_kunjungan_statusdaftartanggal2;
    }

    public static boolean getgrafik_kunjungan_statusbataltahun() {
        return var.grafik_kunjungan_statusbataltahun;
    }

    public static boolean getgrafik_kunjungan_statusbatalbulan() {
        return var.grafik_kunjungan_statusbatalbulan;
    }

    public static boolean getpcare_cek_penyakit() {
        return var.pcare_cek_penyakit;
    }

    public static boolean getgrafik_kunjungan_statusbataltanggal() {
        return var.grafik_kunjungan_statusbataltanggal;
    }

    public static boolean getkategori_barang() {
        return var.kategori_barang;
    }

    public static boolean getgolongan_barang() {
        return var.golongan_barang;
    }
    
    public static boolean getpemberian_obat_pertanggal() {
        return var.pemberian_obat_pertanggal;
    }

    public static boolean getpenjualan_obat_pertanggal() {
        return var.penjualan_obat_pertanggal;
    }
	public static boolean getbpjs_rujukan_keluar(){return var.bpjs_rujukan_vclaim;}
        public static boolean getskdp_bpjs(){return var.skdp_bpjs;}
        public static boolean getbooking_registrasi(){return var.booking_registrasi;}
        public static boolean getbpjs_cek_riwayat_rujukan_pcare(){return var.bpjs_cek_riwayat_rujukan_pcare;}
        public static boolean getbpjs_cek_riwayat_rujukan_rs(){return var.bpjs_cek_riwayat_rujukan_rs;}
        public static boolean getbpjs_cek_rujukan_kartu_rs(){return var.bpjs_cek_rujukan_kartu_rs;}
        public static boolean getbpjs_cek_tgl_rujukan(){return var.bpjs_cek_tgl_rujukan;}
        public static boolean getbpjs_cek_no_rujukan_rs(){return var.bpjs_cek_no_rujukan_rs;}
        public static boolean getbpjs_cek_rujukan_kartu_pcare(){return var.bpjs_cek_rujukan_kartu_pcare;}
        public static boolean getbpjs_cek_referensi_kelas_rawat(){return var.bpjs_cek_referensi_kelas_rawat;}
        public static boolean getbpjs_cek_referensi_prosedur(){return var.bpjs_cek_referensi_prosedur;}
        public static boolean getbpjs_cek_referensi_dpjp(){return var.bpjs_cek_referensi_dpjp;}
        public static boolean getbpjs_cek_referensi_dokter(){return var.bpjs_cek_referensi_dokter;}
        public static boolean getbpjs_cek_referensi_spesialistik(){return var.bpjs_cek_referensi_spesialistik;}
        public static boolean getbpjs_cek_referensi_ruang_rawat(){return var.bpjs_cek_referensi_ruang_rawat;}
        public static boolean getbpjs_cek_referensi_cara_keluar(){return var.bpjs_cek_referensi_cara_keluar;}
        public static boolean getbpjs_cek_referensi_pasca_pulang(){return var.bpjs_cek_referensi_pasca_pulang;}
        public static boolean getbpjs_cek_referensi_propinsi(){return var.bpjs_cek_referensi_propinsi;}
        public static boolean getbpjs_cek_referensi_kabupaten(){return var.bpjs_cek_referensi_kabupaten;}
        public static boolean getbpjs_cek_referensi_kecamatan(){return var.bpjs_cek_referensi_kecamatan;}
        public static boolean getpermintaan_lab(){return var.permintaan_lab;}
        public static boolean getpermintaan_radiologi(){return var.permintaan_radiologi;}
        public static boolean getselisih_tarif_bpjs(){return var.selisih_tarif_bpjs;}
        public static boolean getedit_data_kematian(){return var.edit_data_kematian;}
        public static boolean getbridging_jamkesda(){return var.bridging_jamkesda;}
        public static boolean getmasuk_pindah_pulang_inap(){return var.masuk_pindah_pulang_inap;}
        public static boolean getmasuk_pindah_inap(){return var.masuk_pindah_inap;}
        public static boolean getjumlah_macam_diet(){return var.jumlah_macam_diet;}
        public static boolean getjumlah_porsi_diet(){return var.jumlah_porsi_diet;}
        public static boolean getstatusgizi(){return var.status_gizi;}
        public static boolean getgizi_buruk(){return var.gizi_buruk;}
        public static boolean getmaster_faskes(){return var.master_faskes;}
        public static boolean getsetstatusralan(){return var.setstatusralan;}
        public static boolean gettelusurpasien(){return var.telusurpasien;}
        public static boolean getsisrute_rujukan_keluar(){return var.sisrute_rujukan_keluar;}
        public static void setAktif(boolean status){var.aktif=status;}
        public static boolean getAktif(){return var.aktif;}
        public static boolean getsisrute_rujukan_masuk(){return var.sisrute_rujukan_masuk;}
        public static boolean getsisrute_referensi_diagnosa(){return var.sisrute_referensi_diagnosa;}
        public static boolean getsisrute_referensi_alasanrujuk(){return var.sisrute_referensi_alasanrujuk;}
        public static boolean getsisrute_referensi_faskes(){return var.sisrute_referensi_faskes;}
        public static boolean getbarang_cssd(){return var.barang_cssd;}
        public static boolean getstatus_pulang_inap(){return var.status_pulang_inap;}
        public static boolean getdata_persalinan(){return var.data_persalinan;}
        public static boolean getdata_ponek(){return var.data_ponek;}
        public static boolean getreg_boking_kasir(){return var.reg_boking_kasir;}
        public static boolean getbahasa_pasien(){return var.bahasa_pasien;}
        public static boolean getsuku_bangsa(){return var.suku_bangsa;}        
        public static boolean getharianhaisinap(){return var.harian_hais_inap;}
        public static boolean getharianhaisjalan(){return var.harian_hais_jalan;}
        public static boolean getbulananhaisinap(){return var.bulanan_hais_inap;}
        public static boolean getbulananhaisjalan(){return var.bulanan_hais_jalan;}
        public static boolean getringkasanpulangranap(){return var.ringkasan_pulang_ranap;}
        public static boolean getlaporanfarmasi(){return var.laporan_farmasi;}
        public static boolean getmaster_masalah_keperawatan(){return var.master_masalah_keperawatan;}
        public static boolean getpenilaian_awal_keperawatan_ralan(){return var.penilaian_awal_keperawatan_ralan;}
        public static boolean getmaster_triase_skala1(){return var.master_triase_skala1;}
        public static boolean getmaster_triase_skala2(){return var.master_triase_skala2;}        
        public static boolean getmaster_triase_skala3(){return var.master_triase_skala3;}
        public static boolean getmaster_triase_skala4(){return var.master_triase_skala4;}
        public static boolean getmaster_triase_skala5(){return var.master_triase_skala5;}
        public static boolean getdata_triase_igd(){return var.data_triase_igd;}
        public static boolean getmaster_triase_pemeriksaan(){return var.master_triase_pemeriksaan;}
        public static boolean getmaster_triase_macamkasus(){return var.master_triase_macamkasus;}
        public static boolean getmaster_cara_bayar(){return var.master_cara_bayar;}
        public static boolean getstatus_kerja_dokter(){return var.status_kerja_dokter;}
        public static boolean getpasien_corona(){return var.pasien_corona;}
        public static boolean getdiagnosa_pasien_corona(){return var.diagnosa_pasien_corona;}
        public static boolean getperawatan_pasien_corona(){return var.perawatan_pasien_corona;}
        public static boolean getassesmen_gizi_harian(){return var.assesmen_gizi_harian;}
        public static boolean getassesmen_gizi_ulang(){return var.assesmen_gizi_ulang;}
        public static boolean gettombolnota_billing(){return var.tombol_nota_billing;}
        public static boolean gettombolsimpan_hasil_rad(){return var.tombol_simpan_hasil_rad;}
        public static boolean getmonev_asuhan_gizi(){return var.monev_asuhan_gizi;}
        public static boolean getinacbg_klaim_raza(){return var.inacbg_klaim_raza;}
        public static boolean getpengajuan_klaim_raza(){return var.pengajuan_klaim_inacbg_raza;}
        public static boolean getcopy_pemeriksaan_dr_kepetugas(){return var.copy_pemeriksaan_dokter_kepetugas_ralan;}
        public static boolean getjkn_belum_diproses_klaim(){return var.jkn_belum_diproses_klaim;}
        public static boolean getinput_kode_icd(){return var.input_kode_icd;}
        public static boolean getkendali_mutu_kendali_biaya_inacbg(){return var.kendali_Mutu_kendali_Biaya_INACBG;}
        public static boolean getdashboard_eResep(){return var.dashboard_eResep;}
        public static boolean getbpjsSEPinternal(){return var.bpjs_sep_internal;}
        public static boolean getkemenkes_sitt(){return var.kemenkes_sitt;}
        public static boolean getRencanaKontrolJKN(){return var.rencana_kontrol_jkn;}
        public static boolean getSPRIJKN(){return var.spri_jkn;}
        public static boolean getHapusSEP(){return var.hapus_sep;}
        public static boolean getpenilaian_awal_medis_ralan_kebidanan(){return var.penilaian_awal_medis_ralan_kebidanan;}
        public static boolean getpenilaian_awal_keperawatan_kebidanan(){return var.penilaian_awal_keperawatan_kebidanan;}
        public static boolean getikhtisar_perawatan_hiv(){return var.ikhtisar_perawatan_hiv;}
        public static boolean getsurvey_kepuasan(){return var.survey_kepuasan;}
        public static boolean getkemenkes_kanker(){return var.kemenkes_kanker;}
        public static boolean getset_bridging(){return var.aktivasi_bridging;}
        public static boolean getOperator_antrian(){return var.operator_antrian;}
}
