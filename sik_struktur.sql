/*
SQLyog Ultimate v12.5.1 (64 bit)
MySQL - 10.3.29-MariaDB-0+deb10u1-log : Database - sik
*********************************************************************
*/

/*!40101 SET NAMES utf8 */;

/*!40101 SET SQL_MODE=''*/;

/*!40014 SET @OLD_UNIQUE_CHECKS=@@UNIQUE_CHECKS, UNIQUE_CHECKS=0 */;
/*!40014 SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0 */;
/*!40101 SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='NO_AUTO_VALUE_ON_ZERO' */;
/*!40111 SET @OLD_SQL_NOTES=@@SQL_NOTES, SQL_NOTES=0 */;
CREATE DATABASE /*!32312 IF NOT EXISTS*/`sik` /*!40100 DEFAULT CHARACTER SET utf8mb4 */;

USE `sik`;

/*Table structure for table `admin` */

DROP TABLE IF EXISTS `admin`;

CREATE TABLE `admin` (
  `usere` text DEFAULT NULL,
  `passworde` text DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

/*Table structure for table `akun_bayar` */

DROP TABLE IF EXISTS `akun_bayar`;

CREATE TABLE `akun_bayar` (
  `nama_bayar` varchar(50) NOT NULL,
  `kd_rek` varchar(15) DEFAULT NULL,
  `ppn` double DEFAULT NULL,
  PRIMARY KEY (`nama_bayar`),
  KEY `akun_bayar_ibfk_1` (`kd_rek`),
  KEY `ppn` (`ppn`) USING BTREE,
  CONSTRAINT `akun_bayar_ibfk_1` FOREIGN KEY (`kd_rek`) REFERENCES `rekening` (`kd_rek`) ON DELETE CASCADE ON UPDATE CASCADE
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

/*Table structure for table `akun_online` */

DROP TABLE IF EXISTS `akun_online`;

CREATE TABLE `akun_online` (
  `no_rkm_medis` varchar(15) NOT NULL,
  `nm_pasien` varchar(50) DEFAULT NULL,
  `katasandi` varchar(50) DEFAULT NULL,
  `tgl_aktivasi` datetime DEFAULT current_timestamp(),
  `tgl_ganti_pass` datetime DEFAULT NULL ON UPDATE current_timestamp(),
  PRIMARY KEY (`no_rkm_medis`),
  CONSTRAINT `FK_akun_online_pasien` FOREIGN KEY (`no_rkm_medis`) REFERENCES `pasien` (`no_rkm_medis`) ON DELETE NO ACTION ON UPDATE CASCADE
) ENGINE=InnoDB DEFAULT CHARSET=latin1 ROW_FORMAT=COMPACT;

/*Table structure for table `akun_piutang` */

DROP TABLE IF EXISTS `akun_piutang`;

CREATE TABLE `akun_piutang` (
  `nama_bayar` varchar(50) NOT NULL,
  `kd_rek` varchar(15) DEFAULT NULL,
  `kd_pj` char(3) DEFAULT NULL,
  PRIMARY KEY (`nama_bayar`),
  UNIQUE KEY `kd_rek_2` (`kd_rek`,`kd_pj`) USING BTREE,
  KEY `kd_rek` (`kd_rek`) USING BTREE,
  KEY `kd_pj` (`kd_pj`) USING BTREE,
  CONSTRAINT `akun_piutang_ibfk_1` FOREIGN KEY (`kd_rek`) REFERENCES `rekening` (`kd_rek`) ON DELETE CASCADE ON UPDATE CASCADE,
  CONSTRAINT `akun_piutang_ibfk_2` FOREIGN KEY (`kd_pj`) REFERENCES `penjab` (`kd_pj`) ON UPDATE CASCADE
) ENGINE=InnoDB DEFAULT CHARSET=latin1 ROW_FORMAT=COMPACT;

/*Table structure for table `ambil_dankes` */

DROP TABLE IF EXISTS `ambil_dankes`;

CREATE TABLE `ambil_dankes` (
  `id` int(11) NOT NULL,
  `tanggal` date NOT NULL,
  `ktg` varchar(50) NOT NULL,
  `dankes` double NOT NULL,
  PRIMARY KEY (`id`,`tanggal`),
  KEY `ktg` (`ktg`),
  KEY `dankes` (`dankes`),
  CONSTRAINT `ambil_dankes_ibfk_1` FOREIGN KEY (`id`) REFERENCES `pegawai` (`id`) ON UPDATE CASCADE
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

/*Table structure for table `angsuran_koperasi` */

DROP TABLE IF EXISTS `angsuran_koperasi`;

CREATE TABLE `angsuran_koperasi` (
  `id` int(11) NOT NULL,
  `tanggal_pinjam` date NOT NULL,
  `tanggal_angsur` date NOT NULL,
  `pokok` double NOT NULL,
  `jasa` double NOT NULL,
  PRIMARY KEY (`id`,`tanggal_pinjam`,`tanggal_angsur`),
  KEY `id` (`id`),
  KEY `pokok` (`pokok`),
  KEY `jasa` (`jasa`),
  CONSTRAINT `angsuran_koperasi_ibfk_1` FOREIGN KEY (`id`) REFERENCES `pegawai` (`id`) ON UPDATE CASCADE
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

/*Table structure for table `antriloket` */

DROP TABLE IF EXISTS `antriloket`;

CREATE TABLE `antriloket` (
  `loket` int(11) NOT NULL,
  `antrian` int(11) NOT NULL,
  KEY `loket` (`loket`) USING BTREE,
  KEY `antrian` (`antrian`) USING BTREE
) ENGINE=MyISAM DEFAULT CHARSET=latin1;

/*Table structure for table `aplicare_ketersediaan_kamar` */

DROP TABLE IF EXISTS `aplicare_ketersediaan_kamar`;

CREATE TABLE `aplicare_ketersediaan_kamar` (
  `kode_kelas_aplicare` varchar(15) NOT NULL DEFAULT '',
  `kd_bangsal` char(5) NOT NULL DEFAULT '',
  `kelas` enum('Kelas 1','Kelas 2','Kelas 3','Kelas Utama','Kelas VIP','Kelas VVIP') NOT NULL DEFAULT 'Kelas 1',
  `kapasitas` int(11) DEFAULT NULL,
  `tersedia` int(11) DEFAULT NULL,
  `tersediapria` int(11) DEFAULT NULL,
  `tersediawanita` int(11) DEFAULT NULL,
  `tersediapriawanita` int(11) DEFAULT NULL,
  PRIMARY KEY (`kode_kelas_aplicare`,`kd_bangsal`,`kelas`),
  KEY `kd_bangsal` (`kd_bangsal`),
  KEY `kapasitas` (`kapasitas`) USING BTREE,
  KEY `tersedia` (`tersedia`) USING BTREE,
  KEY `tersediapria` (`tersediapria`) USING BTREE,
  KEY `tersediawanita` (`tersediawanita`) USING BTREE,
  KEY `tersediapriawanita` (`tersediapriawanita`) USING BTREE,
  CONSTRAINT `aplicare_ketersediaan_kamar_ibfk_1` FOREIGN KEY (`kd_bangsal`) REFERENCES `bangsal` (`kd_bangsal`) ON DELETE CASCADE ON UPDATE CASCADE
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

/*Table structure for table `assesmen_gizi` */

DROP TABLE IF EXISTS `assesmen_gizi`;

CREATE TABLE `assesmen_gizi` (
  `no_rawat` varchar(17) NOT NULL,
  `tgl_assesmen` date NOT NULL,
  `BB` varchar(4) DEFAULT NULL,
  `TB` varchar(4) DEFAULT NULL,
  `IMT` varchar(10) DEFAULT NULL,
  `LLA` varchar(10) DEFAULT NULL,
  `status_gizi` enum('-','BURUK','KURANG','NORMAL','LEBIH','OBESITAS') DEFAULT NULL,
  `biokimia_domain` longtext DEFAULT NULL,
  `mual_muntah` enum('true','false') DEFAULT NULL,
  `diare` enum('true','false') DEFAULT NULL,
  `odeme` enum('true','false') DEFAULT NULL,
  `anorexia` enum('true','false') DEFAULT NULL,
  `nyeri_ulu_hati` enum('true','false') DEFAULT NULL,
  `kesulitan_menelan` enum('true','false') DEFAULT NULL,
  `konstipasi` enum('true','false') DEFAULT NULL,
  `gangguan_gigi_geligi` enum('true','false') DEFAULT NULL,
  `riwayat_makan1` enum('MAKAN > 3X SEHARI','MAKAN < 3X SEHARI','-') DEFAULT NULL,
  `riwayat_makan2` enum('KEKURANGAN INTAKE','KELEBIHAN INTAKE','INTAKE NORMAL','-') DEFAULT NULL,
  `alergi_makanan` enum('true','false') DEFAULT NULL,
  `riwayat_penyakit_personal` varchar(255) DEFAULT NULL,
  `bentuk_makan` enum('BUBUR','NASI BIASA','NASI LUNAK','CAIR','SARING') DEFAULT NULL,
  `kd_diet` varchar(10) DEFAULT NULL,
  `kalori` enum('600','900','1200','1500','1700','1900','2100') DEFAULT NULL,
  `protein` enum('10 %','15 %') DEFAULT NULL,
  `lemak` enum('20 %','25 %') DEFAULT NULL,
  `karbohidrat` enum('65 %','70 %') DEFAULT NULL,
  `kd_kamar` varchar(15) DEFAULT NULL,
  `jam` time DEFAULT NULL,
  `ket_jenis_diet` varchar(255) DEFAULT NULL,
  `ket_alergi_makan` varchar(255) DEFAULT NULL,
  `diagnosa_medis` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`no_rawat`,`tgl_assesmen`),
  KEY `kd_diet` (`kd_diet`) USING BTREE,
  CONSTRAINT `assesmen_gizi_ibfk_1` FOREIGN KEY (`kd_diet`) REFERENCES `diet` (`kd_diet`) ON UPDATE CASCADE
) ENGINE=InnoDB DEFAULT CHARSET=latin1 ROW_FORMAT=COMPACT;

/*Table structure for table `assesmen_gizi_ulang` */

DROP TABLE IF EXISTS `assesmen_gizi_ulang`;

CREATE TABLE `assesmen_gizi_ulang` (
  `no_rawat` varchar(17) NOT NULL,
  `tgl_assesmen` date NOT NULL,
  `BB` varchar(4) DEFAULT NULL,
  `TB` varchar(4) DEFAULT NULL,
  `IMT` varchar(10) DEFAULT NULL,
  `LLA` varchar(10) DEFAULT NULL,
  `status_gizi` enum('-','BURUK','KURANG','NORMAL','LEBIH','OBESITAS') DEFAULT NULL,
  `biokimia_domain` varchar(255) DEFAULT NULL,
  `mual_muntah` enum('true','false') DEFAULT NULL,
  `diare` enum('true','false') DEFAULT NULL,
  `odeme` enum('true','false') DEFAULT NULL,
  `anorexia` enum('true','false') DEFAULT NULL,
  `nyeri_ulu_hati` enum('true','false') DEFAULT NULL,
  `kesulitan_menelan` enum('true','false') DEFAULT NULL,
  `konstipasi` enum('true','false') DEFAULT NULL,
  `gangguan_gigi_geligi` enum('true','false') DEFAULT NULL,
  `riwayat_makan1` enum('MAKAN > 3X SEHARI','MAKAN < 3X SEHARI','-') DEFAULT NULL,
  `riwayat_makan2` enum('KEKURANGAN INTAKE','KELEBIHAN INTAKE','INTAKE NORMAL','-') DEFAULT NULL,
  `alergi_makanan` enum('true','false') DEFAULT NULL,
  `riwayat_penyakit_personal` varchar(255) DEFAULT NULL,
  `kd_kamar` varchar(15) NOT NULL,
  `jam` time DEFAULT NULL,
  `ket_alergi_makan` varchar(255) DEFAULT NULL,
  `diagnosa_medis` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`no_rawat`,`tgl_assesmen`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1 ROW_FORMAT=COMPACT;

/*Table structure for table `asuransi` */

DROP TABLE IF EXISTS `asuransi`;

CREATE TABLE `asuransi` (
  `stts` char(5) NOT NULL,
  `biaya` double NOT NULL,
  PRIMARY KEY (`stts`),
  KEY `biaya` (`biaya`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

/*Table structure for table `aturan_pakai` */

DROP TABLE IF EXISTS `aturan_pakai`;

CREATE TABLE `aturan_pakai` (
  `tgl_perawatan` date NOT NULL DEFAULT '0000-00-00',
  `jam` time NOT NULL DEFAULT '00:00:00',
  `no_rawat` varchar(17) NOT NULL DEFAULT '',
  `kode_brng` varchar(15) NOT NULL DEFAULT '',
  `aturan` varchar(150) DEFAULT NULL,
  PRIMARY KEY (`tgl_perawatan`,`jam`,`no_rawat`,`kode_brng`),
  KEY `no_rawat` (`no_rawat`),
  KEY `kode_brng` (`kode_brng`),
  CONSTRAINT `aturan_pakai_ibfk_1` FOREIGN KEY (`no_rawat`) REFERENCES `reg_periksa` (`no_rawat`) ON DELETE CASCADE ON UPDATE CASCADE,
  CONSTRAINT `aturan_pakai_ibfk_2` FOREIGN KEY (`kode_brng`) REFERENCES `databarang` (`kode_brng`) ON DELETE CASCADE ON UPDATE CASCADE
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

/*Table structure for table `bahasa_pasien` */

DROP TABLE IF EXISTS `bahasa_pasien`;

CREATE TABLE `bahasa_pasien` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `nama_bahasa` varchar(30) DEFAULT NULL,
  PRIMARY KEY (`id`),
  UNIQUE KEY `nama_bahasa` (`nama_bahasa`)
) ENGINE=InnoDB AUTO_INCREMENT=29 DEFAULT CHARSET=latin1;

/*Table structure for table `bangsal` */

DROP TABLE IF EXISTS `bangsal`;

CREATE TABLE `bangsal` (
  `kd_bangsal` char(5) NOT NULL,
  `nm_bangsal` varchar(50) DEFAULT NULL,
  `status` enum('0','1') DEFAULT NULL,
  `inisial_label_gizi` varchar(3) NOT NULL,
  `nm_gedung` varchar(20) DEFAULT NULL,
  PRIMARY KEY (`kd_bangsal`),
  KEY `nm_bangsal` (`nm_bangsal`),
  KEY `status` (`status`) USING BTREE,
  KEY `kd_bangsal` (`kd_bangsal`) USING BTREE
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

/*Table structure for table `bank` */

DROP TABLE IF EXISTS `bank`;

CREATE TABLE `bank` (
  `namabank` varchar(50) NOT NULL,
  PRIMARY KEY (`namabank`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

/*Table structure for table `barcode` */

DROP TABLE IF EXISTS `barcode`;

CREATE TABLE `barcode` (
  `id` int(11) NOT NULL,
  `barcode` varchar(25) NOT NULL,
  PRIMARY KEY (`id`),
  UNIQUE KEY `barcode` (`barcode`),
  CONSTRAINT `barcode_ibfk_1` FOREIGN KEY (`id`) REFERENCES `pegawai` (`id`) ON UPDATE CASCADE
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

/*Table structure for table `bayar_pemesanan` */

DROP TABLE IF EXISTS `bayar_pemesanan`;

CREATE TABLE `bayar_pemesanan` (
  `tgl_bayar` date DEFAULT NULL,
  `no_faktur` varchar(20) DEFAULT NULL,
  `nip` varchar(20) DEFAULT NULL,
  `besar_bayar` double DEFAULT NULL,
  `keterangan` varchar(100) DEFAULT NULL,
  `nama_bayar` varchar(50) DEFAULT NULL,
  `no_bukti` varchar(20) DEFAULT NULL,
  UNIQUE KEY `tgl_bayar` (`tgl_bayar`,`no_faktur`),
  KEY `bayar_pemesanan_ibfk_2` (`nip`),
  KEY `bayar_pemesanan_ibfk_3` (`nama_bayar`),
  KEY `bayar_pemesanan_ibfk_1` (`no_faktur`),
  CONSTRAINT `bayar_pemesanan_ibfk_1` FOREIGN KEY (`no_faktur`) REFERENCES `pemesanan` (`no_faktur`) ON DELETE CASCADE ON UPDATE CASCADE,
  CONSTRAINT `bayar_pemesanan_ibfk_2` FOREIGN KEY (`nip`) REFERENCES `petugas` (`nip`) ON DELETE CASCADE ON UPDATE CASCADE,
  CONSTRAINT `bayar_pemesanan_ibfk_3` FOREIGN KEY (`nama_bayar`) REFERENCES `akun_bayar` (`nama_bayar`) ON DELETE CASCADE ON UPDATE CASCADE
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

/*Table structure for table `bayar_piutang` */

DROP TABLE IF EXISTS `bayar_piutang`;

CREATE TABLE `bayar_piutang` (
  `tgl_bayar` date NOT NULL,
  `no_rkm_medis` varchar(15) NOT NULL,
  `besar_cicilan` double NOT NULL,
  `catatan` varchar(100) NOT NULL,
  `no_rawat` varchar(17) NOT NULL,
  `kd_rek` varchar(15) DEFAULT NULL,
  PRIMARY KEY (`tgl_bayar`,`no_rkm_medis`,`no_rawat`),
  KEY `no_rkm_medis` (`no_rkm_medis`),
  KEY `nota_piutang` (`no_rawat`),
  KEY `besar_cicilan` (`besar_cicilan`),
  KEY `catatan` (`catatan`),
  KEY `kd_rek` (`kd_rek`),
  CONSTRAINT `bayar_piutang_ibfk_1` FOREIGN KEY (`no_rkm_medis`) REFERENCES `pasien` (`no_rkm_medis`) ON UPDATE CASCADE,
  CONSTRAINT `bayar_piutang_ibfk_2` FOREIGN KEY (`kd_rek`) REFERENCES `akun_bayar` (`kd_rek`) ON DELETE CASCADE ON UPDATE CASCADE
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

/*Table structure for table `beri_bhp_radiologi` */

DROP TABLE IF EXISTS `beri_bhp_radiologi`;

CREATE TABLE `beri_bhp_radiologi` (
  `no_rawat` varchar(18) NOT NULL,
  `tgl_periksa` date NOT NULL,
  `jam` time NOT NULL,
  `kode_brng` varchar(15) NOT NULL,
  `kode_sat` char(4) NOT NULL,
  `jumlah` double NOT NULL,
  `harga` double DEFAULT NULL,
  `total` double DEFAULT NULL,
  KEY `no_rawat` (`no_rawat`),
  KEY `kode_brng` (`kode_brng`),
  KEY `kode_sat` (`kode_sat`),
  KEY `tgl_periksa` (`tgl_periksa`),
  KEY `jam` (`jam`),
  KEY `jumlah` (`jumlah`),
  CONSTRAINT `beri_bhp_radiologi_ibfk_4` FOREIGN KEY (`no_rawat`) REFERENCES `reg_periksa` (`no_rawat`) ON UPDATE CASCADE,
  CONSTRAINT `beri_bhp_radiologi_ibfk_5` FOREIGN KEY (`kode_brng`) REFERENCES `ipsrsbarang` (`kode_brng`) ON UPDATE CASCADE,
  CONSTRAINT `beri_bhp_radiologi_ibfk_6` FOREIGN KEY (`kode_sat`) REFERENCES `kodesatuan` (`kode_sat`) ON UPDATE CASCADE
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

/*Table structure for table `beri_obat_operasi` */

DROP TABLE IF EXISTS `beri_obat_operasi`;

CREATE TABLE `beri_obat_operasi` (
  `no_rawat` varchar(18) NOT NULL,
  `tanggal` datetime NOT NULL,
  `kd_obat` varchar(15) NOT NULL,
  `hargasatuan` double NOT NULL,
  `jumlah` double NOT NULL,
  KEY `no_rawat` (`no_rawat`),
  KEY `kd_obat` (`kd_obat`),
  KEY `tanggal` (`tanggal`),
  KEY `hargasatuan` (`hargasatuan`),
  KEY `jumlah` (`jumlah`),
  CONSTRAINT `beri_obat_operasi_ibfk_2` FOREIGN KEY (`kd_obat`) REFERENCES `obatbhp_ok` (`kd_obat`) ON DELETE CASCADE ON UPDATE CASCADE,
  CONSTRAINT `beri_obat_operasi_ibfk_3` FOREIGN KEY (`no_rawat`) REFERENCES `reg_periksa` (`no_rawat`) ON DELETE CASCADE ON UPDATE CASCADE
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

/*Table structure for table `berkas_digital_perawatan` */

DROP TABLE IF EXISTS `berkas_digital_perawatan`;

CREATE TABLE `berkas_digital_perawatan` (
  `no_rawat` varchar(18) NOT NULL,
  `kode` varchar(10) NOT NULL,
  `lokasi_file` varchar(600) DEFAULT NULL,
  PRIMARY KEY (`no_rawat`,`kode`),
  KEY `kode` (`kode`) USING BTREE,
  CONSTRAINT `berkas_digital_perawatan_ibfk_1` FOREIGN KEY (`no_rawat`) REFERENCES `reg_periksa` (`no_rawat`) ON UPDATE CASCADE,
  CONSTRAINT `berkas_digital_perawatan_ibfk_2` FOREIGN KEY (`kode`) REFERENCES `master_berkas_digital` (`kode`) ON UPDATE CASCADE
) ENGINE=InnoDB DEFAULT CHARSET=latin1 ROW_FORMAT=COMPACT;

/*Table structure for table `biaya_harian` */

DROP TABLE IF EXISTS `biaya_harian`;

CREATE TABLE `biaya_harian` (
  `kd_kamar` varchar(15) NOT NULL,
  `nama_biaya` varchar(50) NOT NULL,
  `besar_biaya` double NOT NULL,
  `jml` int(11) NOT NULL,
  PRIMARY KEY (`kd_kamar`,`nama_biaya`),
  KEY `besar_biaya` (`besar_biaya`),
  KEY `jml` (`jml`),
  CONSTRAINT `biaya_harian_ibfk_1` FOREIGN KEY (`kd_kamar`) REFERENCES `kamar` (`kd_kamar`) ON DELETE CASCADE ON UPDATE CASCADE
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

/*Table structure for table `biaya_naik_kelas_bpjs` */

DROP TABLE IF EXISTS `biaya_naik_kelas_bpjs`;

CREATE TABLE `biaya_naik_kelas_bpjs` (
  `tgl_transaksi` date DEFAULT NULL,
  `jam_transaksi` time DEFAULT NULL,
  `no_rawat` varchar(17) DEFAULT NULL,
  `no_sep` varchar(40) DEFAULT NULL,
  `total_tagihan` double DEFAULT NULL,
  `sudah_dibayar` double DEFAULT NULL,
  `sisa_tagihan` double DEFAULT NULL,
  `jumlah_byr` double DEFAULT NULL,
  `sisa_setelah_byr` double DEFAULT NULL,
  `pembayaran_ke` int(11) DEFAULT NULL,
  `no_transaksi` varchar(15) NOT NULL,
  `status_transaksi` enum('lunas','dicicil') DEFAULT NULL,
  PRIMARY KEY (`no_transaksi`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1 ROW_FORMAT=COMPACT;

/*Table structure for table `biaya_sekali` */

DROP TABLE IF EXISTS `biaya_sekali`;

CREATE TABLE `biaya_sekali` (
  `kd_kamar` varchar(15) NOT NULL,
  `nama_biaya` varchar(50) NOT NULL,
  `besar_biaya` double NOT NULL,
  PRIMARY KEY (`kd_kamar`,`nama_biaya`),
  KEY `kd_kamar` (`kd_kamar`),
  KEY `besar_biaya` (`besar_biaya`),
  CONSTRAINT `biaya_sekali_ibfk_1` FOREIGN KEY (`kd_kamar`) REFERENCES `kamar` (`kd_kamar`) ON DELETE CASCADE ON UPDATE CASCADE
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

/*Table structure for table `bidang` */

DROP TABLE IF EXISTS `bidang`;

CREATE TABLE `bidang` (
  `nama` varchar(15) NOT NULL,
  PRIMARY KEY (`nama`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

/*Table structure for table `billing` */

DROP TABLE IF EXISTS `billing`;

CREATE TABLE `billing` (
  `noindex` int(100) NOT NULL AUTO_INCREMENT,
  `no_rawat` varchar(18) NOT NULL,
  `tgl_byr` date DEFAULT NULL,
  `no` varchar(50) NOT NULL,
  `nm_perawatan` varchar(200) NOT NULL,
  `pemisah` char(1) NOT NULL,
  `biaya` double NOT NULL,
  `jumlah` double NOT NULL,
  `tambahan` double NOT NULL,
  `totalbiaya` double NOT NULL,
  `status` enum('Laborat','Radiologi','Operasi','Obat','Ranap Dokter','Ranap Dokter Paramedis','Ranap Paramedis','Ralan Dokter','Ralan Dokter Paramedis','Ralan Paramedis','Tambahan','Potongan','Administrasi','Kamar','-','Registrasi','Harian','Service','TtlObat','TtlRanap Dokter','TtlRanap Paramedis','TtlRalan Dokter','TtlRalan Paramedis','TtlKamar','Dokter','Perawat','TtlTambahan','Retur Obat','TtlRetur Obat','Resep Pulang','TtlResep Pulang','TtlPotongan','TtlLaborat','TtlOperasi','TtlRadiologi','Tagihan') DEFAULT NULL,
  `no_nota` varchar(17) DEFAULT NULL,
  PRIMARY KEY (`noindex`),
  KEY `no_rawat` (`no_rawat`),
  KEY `status` (`status`),
  KEY `totalbiaya` (`totalbiaya`),
  KEY `nm_perawatan` (`nm_perawatan`),
  KEY `tgl_byr` (`tgl_byr`),
  KEY `no` (`no`),
  KEY `pemisah` (`pemisah`),
  KEY `biaya` (`biaya`),
  KEY `jumlah` (`jumlah`),
  KEY `tambahan` (`tambahan`),
  KEY `no_nota` (`no_nota`) USING BTREE,
  CONSTRAINT `billing_ibfk_1` FOREIGN KEY (`no_rawat`) REFERENCES `reg_periksa` (`no_rawat`) ON UPDATE CASCADE
) ENGINE=InnoDB AUTO_INCREMENT=7203222 DEFAULT CHARSET=latin1;

/*Table structure for table `booking_registrasi` */

DROP TABLE IF EXISTS `booking_registrasi`;

CREATE TABLE `booking_registrasi` (
  `tanggal_booking` datetime DEFAULT NULL,
  `no_rkm_medis` varchar(15) NOT NULL,
  `tanggal_periksa` date NOT NULL,
  `kd_dokter` varchar(20) DEFAULT NULL,
  `kd_poli` varchar(5) DEFAULT NULL,
  `no_reg` varchar(8) DEFAULT NULL,
  `kd_booking` varchar(15) NOT NULL,
  `status_booking` enum('Terdaftar','Batal','Menunggu') DEFAULT NULL,
  `kd_pj` char(3) NOT NULL,
  `data_dari` enum('Website RAZA','WhatsApp Messenger','Telegram') DEFAULT NULL,
  `no_telp_pemesan` varchar(16) DEFAULT '',
  `no_rawat` varchar(17) DEFAULT '',
  PRIMARY KEY (`no_rkm_medis`,`tanggal_periksa`,`kd_booking`),
  KEY `kd_dokter` (`kd_dokter`) USING BTREE,
  KEY `kd_poli` (`kd_poli`) USING BTREE,
  KEY `no_rkm_medis` (`no_rkm_medis`) USING BTREE,
  KEY `no_rawat` (`no_rawat`) USING BTREE,
  CONSTRAINT `booking_registrasi_ibfk_2` FOREIGN KEY (`kd_dokter`) REFERENCES `dokter` (`kd_dokter`) ON DELETE CASCADE ON UPDATE CASCADE,
  CONSTRAINT `booking_registrasi_ibfk_3` FOREIGN KEY (`kd_poli`) REFERENCES `poliklinik` (`kd_poli`) ON UPDATE CASCADE,
  CONSTRAINT `booking_registrasi_ibfk_4` FOREIGN KEY (`no_rkm_medis`) REFERENCES `pasien` (`no_rkm_medis`) ON UPDATE CASCADE
) ENGINE=InnoDB DEFAULT CHARSET=latin1 ROW_FORMAT=COMPACT;

/*Table structure for table `bpjs` */

DROP TABLE IF EXISTS `bpjs`;

CREATE TABLE `bpjs` (
  `stts` char(5) NOT NULL,
  `biaya` double NOT NULL,
  PRIMARY KEY (`stts`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

/*Table structure for table `bridging_inhealth` */

DROP TABLE IF EXISTS `bridging_inhealth`;

CREATE TABLE `bridging_inhealth` (
  `no_sjp` varchar(40) NOT NULL DEFAULT '',
  `no_rawat` varchar(18) DEFAULT NULL,
  `tglsep` datetime DEFAULT NULL,
  `tglrujukan` datetime DEFAULT NULL,
  `no_rujukan` varchar(30) DEFAULT NULL,
  `kdppkrujukan` varchar(12) DEFAULT NULL,
  `nmppkrujukan` varchar(200) DEFAULT NULL,
  `kdppkpelayanan` varchar(12) DEFAULT NULL,
  `nmppkpelayanan` varchar(200) DEFAULT NULL,
  `jnspelayanan` enum('1','2','3','4') DEFAULT NULL,
  `catatan` varchar(100) DEFAULT NULL,
  `diagawal` varchar(10) DEFAULT NULL,
  `nmdiagnosaawal` varchar(100) DEFAULT NULL,
  `diagawal2` varchar(10) NOT NULL,
  `nmdiagnosaawal2` varchar(100) NOT NULL,
  `kdpolitujuan` varchar(5) DEFAULT NULL,
  `nmpolitujuan` varchar(50) DEFAULT NULL,
  `klsrawat` enum('000','100','101','102','103','104','110','200','201','202','203','204','210','300','301','302','303','304','310','311','312','400','401','402','403','404','410','411','412','413','500','510','511','512','610','611','612','613','710','711','712','713','910','911','912','913') DEFAULT NULL,
  `klsdesc` varchar(50) DEFAULT NULL,
  `kdbu` varchar(12) DEFAULT NULL,
  `nmbu` varchar(200) DEFAULT NULL,
  `lakalantas` enum('0','1','2') DEFAULT NULL,
  `lokasilaka` varchar(100) DEFAULT NULL,
  `user` varchar(90) DEFAULT NULL,
  `nomr` varchar(15) DEFAULT NULL,
  `nama_pasien` varchar(100) DEFAULT NULL,
  `tanggal_lahir` date DEFAULT NULL,
  `jkel` enum('LAKI-LAKI','PEREMPUAN') DEFAULT NULL,
  `no_kartu` varchar(25) DEFAULT NULL,
  `tglpulang` datetime DEFAULT NULL,
  `plan` varchar(20) NOT NULL,
  `plandesc` varchar(100) NOT NULL,
  `idakomodasi` varchar(20) DEFAULT NULL,
  `tipesjp` varchar(20) DEFAULT NULL,
  `tipecob` varchar(50) DEFAULT NULL,
  `kelas_rawat` varchar(20) DEFAULT NULL,
  `kode_ruang` varchar(80) DEFAULT NULL,
  PRIMARY KEY (`no_sjp`),
  KEY `no_rawat` (`no_rawat`) USING BTREE,
  CONSTRAINT `bridging_inhealth_ibfk_1` FOREIGN KEY (`no_rawat`) REFERENCES `reg_periksa` (`no_rawat`) ON DELETE CASCADE ON UPDATE CASCADE
) ENGINE=InnoDB DEFAULT CHARSET=latin1 ROW_FORMAT=COMPACT;

/*Table structure for table `bridging_jamkesda` */

DROP TABLE IF EXISTS `bridging_jamkesda`;

CREATE TABLE `bridging_jamkesda` (
  `no_rawat` varchar(17) NOT NULL,
  `no_surat` varchar(160) NOT NULL,
  `jns_rawat` varchar(15) NOT NULL,
  `tgl_rawat` date NOT NULL DEFAULT '0000-00-00',
  `tgl_surat` date NOT NULL DEFAULT '0000-00-00',
  `no_sep` varchar(150) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1 ROW_FORMAT=COMPACT;

/*Table structure for table `bridging_jampersal` */

DROP TABLE IF EXISTS `bridging_jampersal`;

CREATE TABLE `bridging_jampersal` (
  `no_rawat` varchar(17) NOT NULL,
  `no_surat` varchar(160) NOT NULL,
  `jns_rawat` varchar(15) NOT NULL,
  `tgl_rawat` date NOT NULL DEFAULT '0000-00-00',
  `tgl_surat` date NOT NULL DEFAULT '0000-00-00',
  `no_sep` varchar(150) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1 ROW_FORMAT=COMPACT;

/*Table structure for table `bridging_rujukan_bpjs` */

DROP TABLE IF EXISTS `bridging_rujukan_bpjs`;

CREATE TABLE `bridging_rujukan_bpjs` (
  `no_sep` varchar(40) NOT NULL,
  `tglRujukan` date DEFAULT NULL,
  `ppkDirujuk` varchar(20) DEFAULT NULL,
  `nm_ppkDirujuk` varchar(100) DEFAULT NULL,
  `jnsPelayanan` enum('1','2') DEFAULT NULL,
  `catatan` varchar(255) DEFAULT NULL,
  `diagRujukan` varchar(10) DEFAULT NULL,
  `nama_diagRujukan` varchar(400) DEFAULT NULL,
  `tipeRujukan` enum('0. Penuh','1. Partial','2. Rujuk Balik') DEFAULT NULL,
  `poliRujukan` varchar(15) DEFAULT NULL,
  `nama_poliRujukan` varchar(50) DEFAULT NULL,
  `no_rujukan` varchar(40) DEFAULT NULL,
  `user` varchar(25) DEFAULT NULL,
  PRIMARY KEY (`no_sep`),
  CONSTRAINT `bridging_rujukan_bpjs_ibfk_1` FOREIGN KEY (`no_sep`) REFERENCES `bridging_sep` (`no_sep`) ON DELETE CASCADE ON UPDATE CASCADE
) ENGINE=InnoDB DEFAULT CHARSET=latin1 ROW_FORMAT=COMPACT;

/*Table structure for table `bridging_sep` */

DROP TABLE IF EXISTS `bridging_sep`;

CREATE TABLE `bridging_sep` (
  `no_sep` varchar(40) NOT NULL DEFAULT '',
  `no_rawat` varchar(17) DEFAULT NULL,
  `tglsep` date DEFAULT NULL,
  `tglrujukan` date DEFAULT NULL,
  `no_rujukan` varchar(40) DEFAULT NULL,
  `kdppkrujukan` varchar(12) DEFAULT NULL,
  `nmppkrujukan` varchar(200) DEFAULT NULL,
  `kdppkpelayanan` varchar(12) DEFAULT NULL,
  `nmppkpelayanan` varchar(200) DEFAULT NULL,
  `jnspelayanan` enum('1','2') DEFAULT NULL,
  `catatan` varchar(255) DEFAULT NULL,
  `diagawal` varchar(10) DEFAULT NULL,
  `nmdiagnosaawal` varchar(400) DEFAULT NULL,
  `kdpolitujuan` varchar(15) DEFAULT NULL,
  `nmpolitujuan` varchar(50) DEFAULT NULL,
  `klsrawat` enum('1','2','3') DEFAULT NULL,
  `lakalantas` enum('0','1') DEFAULT NULL,
  `lokasilaka` varchar(100) DEFAULT NULL,
  `user` varchar(25) DEFAULT NULL,
  `nomr` varchar(15) DEFAULT NULL,
  `nama_pasien` varchar(100) DEFAULT NULL,
  `tanggal_lahir` date DEFAULT NULL,
  `peserta` varchar(100) DEFAULT NULL,
  `jkel` enum('LAKI-LAKI','PEREMPUAN','L','P') DEFAULT NULL,
  `no_kartu` varchar(25) DEFAULT NULL,
  `tglpulang` datetime DEFAULT NULL,
  `asal_rujukan` enum('1. Faskes 1','2. Faskes 2(RS)') NOT NULL,
  `eksekutif` enum('0. Tidak','1.Ya','0.Tidak','1. Ya') NOT NULL,
  `cob` enum('0. Tidak','1.Ya','0.Tidak','1. Ya') NOT NULL,
  `penjamin` varchar(15) NOT NULL,
  `notelep` varchar(13) NOT NULL,
  `katarak` enum('0. Tidak','1.Ya','0.Tidak','1. Ya') NOT NULL,
  `tglkkl` date NOT NULL,
  `keterangankkl` varchar(100) NOT NULL,
  `suplesi` enum('0. Tidak','1. Ya','0.Tidak','1.Ya') NOT NULL,
  `no_sep_suplesi` varchar(40) NOT NULL,
  `kdprop` varchar(10) NOT NULL,
  `nmprop` varchar(50) NOT NULL,
  `kdkab` varchar(10) NOT NULL,
  `nmkab` varchar(50) NOT NULL,
  `kdkec` varchar(10) NOT NULL,
  `nmkec` varchar(50) NOT NULL,
  `noskdp` varchar(20) NOT NULL,
  `kddpjp` varchar(10) NOT NULL,
  `nmdpdjp` varchar(100) NOT NULL,
  `kode_inacbg` varchar(14) DEFAULT NULL,
  PRIMARY KEY (`no_sep`),
  KEY `no_rawat` (`no_rawat`),
  CONSTRAINT `bridging_sep_ibfk_1` FOREIGN KEY (`no_rawat`) REFERENCES `reg_periksa` (`no_rawat`) ON DELETE CASCADE ON UPDATE CASCADE
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

/*Table structure for table `bridging_sep_backup` */

DROP TABLE IF EXISTS `bridging_sep_backup`;

CREATE TABLE `bridging_sep_backup` (
  `no_sep` varchar(40) DEFAULT '',
  `no_rawat` varchar(18) DEFAULT NULL,
  `tglsep` datetime DEFAULT NULL,
  `tglrujukan` datetime DEFAULT NULL,
  `no_rujukan` varchar(40) DEFAULT NULL,
  `kdppkrujukan` varchar(12) DEFAULT NULL,
  `nmppkrujukan` varchar(200) DEFAULT NULL,
  `kdppkpelayanan` varchar(12) DEFAULT NULL,
  `nmppkpelayanan` varchar(200) DEFAULT NULL,
  `jnspelayanan` enum('1','2') DEFAULT NULL,
  `catatan` varchar(255) DEFAULT NULL,
  `diagawal` varchar(10) DEFAULT NULL,
  `nmdiagnosaawal` varchar(400) DEFAULT NULL,
  `kdpolitujuan` varchar(15) DEFAULT NULL,
  `nmpolitujuan` varchar(50) DEFAULT NULL,
  `klsrawat` enum('1','2','3') DEFAULT NULL,
  `lakalantas` enum('0','1') DEFAULT NULL,
  `lokasilaka` varchar(100) DEFAULT NULL,
  `user` varchar(25) DEFAULT NULL,
  `nomr` varchar(15) DEFAULT NULL,
  `nama_pasien` varchar(100) DEFAULT NULL,
  `tanggal_lahir` date DEFAULT NULL,
  `peserta` varchar(100) DEFAULT NULL,
  `jkel` enum('LAKI-LAKI','PEREMPUAN','L','P') DEFAULT NULL,
  `no_kartu` varchar(25) DEFAULT NULL,
  `tglpulang` datetime DEFAULT NULL,
  `asal_rujukan` enum('1. Faskes 1','2. Faskes 2(RS)') NOT NULL,
  `eksekutif` enum('0. Tidak','1. Ya','0.Tidak','1.Ya') NOT NULL,
  `cob` enum('0. Tidak','1. Ya','0.Tidak','1.Ya') NOT NULL,
  `penjamin` varchar(15) NOT NULL,
  `notelep` varchar(13) NOT NULL,
  `code` varchar(255) DEFAULT NULL,
  `message` varchar(255) DEFAULT NULL,
  `katarak` enum('0. Tidak','1. Ya','0.Tidak','1.Ya') NOT NULL,
  `tglkkl` date NOT NULL,
  `keterangankkl` varchar(100) NOT NULL,
  `suplesi` enum('0. Tidak','1. Ya','0.Tidak','1.Ya') NOT NULL,
  `no_sep_suplesi` varchar(40) NOT NULL,
  `kdprop` varchar(10) NOT NULL,
  `nmprop` varchar(50) NOT NULL,
  `kdkab` varchar(10) NOT NULL,
  `nmkab` varchar(50) NOT NULL,
  `kdkec` varchar(10) NOT NULL,
  `nmkec` varchar(50) NOT NULL,
  `noskdp` varchar(20) NOT NULL,
  `kddpjp` varchar(10) NOT NULL,
  `nmdpdjp` varchar(100) NOT NULL,
  `kode_inacbg` varchar(14) DEFAULT NULL,
  KEY `no_rawat` (`no_rawat`),
  CONSTRAINT `bridging_sep_backup_ibfk_1` FOREIGN KEY (`no_rawat`) REFERENCES `reg_periksa` (`no_rawat`) ON DELETE CASCADE ON UPDATE CASCADE
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

/*Table structure for table `bridging_surat_kontrol_bpjs` */

DROP TABLE IF EXISTS `bridging_surat_kontrol_bpjs`;

CREATE TABLE `bridging_surat_kontrol_bpjs` (
  `no_sep` varchar(40) DEFAULT NULL,
  `tgl_surat` date NOT NULL,
  `no_surat` varchar(40) NOT NULL,
  `tgl_rencana` date DEFAULT NULL,
  `kd_dokter_bpjs` varchar(20) DEFAULT NULL,
  `nm_dokter_bpjs` varchar(50) DEFAULT NULL,
  `kd_poli_bpjs` varchar(15) DEFAULT NULL,
  `nm_poli_bpjs` varchar(40) DEFAULT NULL,
  `jenis` enum('1: Rencana Kontrol','2: SPRI') NOT NULL,
  PRIMARY KEY (`no_surat`),
  KEY `no_sep` (`no_sep`) USING BTREE,
  CONSTRAINT `bridging_surat_kontrol_bpjs_ibfk_1` FOREIGN KEY (`no_sep`) REFERENCES `bridging_sep` (`no_sep`) ON UPDATE CASCADE
) ENGINE=InnoDB DEFAULT CHARSET=latin1 ROW_FORMAT=COMPACT;

/*Table structure for table `catatan_pasien` */

DROP TABLE IF EXISTS `catatan_pasien`;

CREATE TABLE `catatan_pasien` (
  `no_rkm_medis` varchar(15) NOT NULL,
  `catatan` text DEFAULT NULL,
  PRIMARY KEY (`no_rkm_medis`),
  KEY `no_rkm_medis` (`no_rkm_medis`) USING BTREE,
  CONSTRAINT `catatan_pasien_ibfk_1` FOREIGN KEY (`no_rkm_medis`) REFERENCES `pasien` (`no_rkm_medis`) ON DELETE CASCADE ON UPDATE CASCADE
) ENGINE=InnoDB DEFAULT CHARSET=latin1 ROW_FORMAT=COMPACT;

/*Table structure for table `catatan_resep` */

DROP TABLE IF EXISTS `catatan_resep`;

CREATE TABLE `catatan_resep` (
  `noId` varchar(10) NOT NULL,
  `no_rawat` varchar(18) NOT NULL,
  `tgl_perawatan` date NOT NULL,
  `jam_perawatan` time NOT NULL,
  `nama_obat` longtext NOT NULL,
  `status` enum('BELUM','SUDAH','DILUAR') NOT NULL DEFAULT 'BELUM',
  `kd_dokter` varchar(20) NOT NULL,
  PRIMARY KEY (`noId`),
  KEY `no_rawat` (`no_rawat`) USING BTREE,
  KEY `kd_dokter` (`kd_dokter`) USING BTREE,
  CONSTRAINT `catatan_resep_ibfk_1` FOREIGN KEY (`no_rawat`) REFERENCES `reg_periksa` (`no_rawat`) ON UPDATE CASCADE,
  CONSTRAINT `catatan_resep_ibfk_2` FOREIGN KEY (`kd_dokter`) REFERENCES `dokter` (`kd_dokter`) ON DELETE CASCADE ON UPDATE CASCADE
) ENGINE=InnoDB DEFAULT CHARSET=latin1 ROW_FORMAT=COMPACT;

/*Table structure for table `closing_kasir` */

DROP TABLE IF EXISTS `closing_kasir`;

CREATE TABLE `closing_kasir` (
  `shift` enum('Pagi','Siang','Sore','Malam') NOT NULL,
  `jam_masuk` time NOT NULL,
  `jam_pulang` time NOT NULL,
  PRIMARY KEY (`shift`),
  KEY `jam_masuk` (`jam_masuk`) USING BTREE,
  KEY `jam_pulang` (`jam_pulang`) USING BTREE
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

/*Table structure for table `cssd_barang` */

DROP TABLE IF EXISTS `cssd_barang`;

CREATE TABLE `cssd_barang` (
  `no_inventaris` varchar(30) NOT NULL,
  `jenis_barang` enum('Heacting Set','Partus Set','Set Bedah') DEFAULT NULL,
  PRIMARY KEY (`no_inventaris`),
  CONSTRAINT `cssd_barang_ibfk_1` FOREIGN KEY (`no_inventaris`) REFERENCES `inventaris` (`no_inventaris`) ON DELETE CASCADE ON UPDATE CASCADE
) ENGINE=InnoDB DEFAULT CHARSET=latin1 ROW_FORMAT=COMPACT;

/*Table structure for table `daftar_antrian_kamar` */

DROP TABLE IF EXISTS `daftar_antrian_kamar`;

CREATE TABLE `daftar_antrian_kamar` (
  `KD_ANTRIAN` int(255) NOT NULL AUTO_INCREMENT,
  `NAMA_PASIEN` varchar(255) DEFAULT NULL,
  `NO_TELPON_PENANGGUNG_JWB` varchar(255) DEFAULT NULL,
  `TGL_ANTRI` datetime(6) DEFAULT current_timestamp(6),
  `STATUS_ANTRI` varchar(255) DEFAULT NULL,
  `kd_bangsal` varchar(255) DEFAULT NULL,
  `no_rkm_medis` varchar(255) DEFAULT NULL,
  `NM_PENANGGUNG_JWB` varchar(255) DEFAULT NULL,
  `JK_PASIEN` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`KD_ANTRIAN`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

/*Table structure for table `dansos` */

DROP TABLE IF EXISTS `dansos`;

CREATE TABLE `dansos` (
  `dana` double NOT NULL,
  PRIMARY KEY (`dana`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

/*Table structure for table `data_hais` */

DROP TABLE IF EXISTS `data_hais`;

CREATE TABLE `data_hais` (
  `tanggal` date NOT NULL,
  `no_rawat` varchar(18) NOT NULL,
  `ETT` int(11) DEFAULT NULL,
  `CVL` int(11) DEFAULT NULL,
  `IVL` int(11) DEFAULT NULL,
  `UC` int(11) DEFAULT NULL,
  `VAP` int(11) DEFAULT NULL,
  `IAD` int(11) DEFAULT NULL,
  `PLEB` int(11) DEFAULT NULL,
  `ISK` int(11) DEFAULT NULL,
  `DEKU` enum('IYA','TIDAK') DEFAULT NULL,
  `SPUTUM` varchar(200) DEFAULT NULL,
  `DARAH` varchar(200) DEFAULT NULL,
  `URINE` varchar(200) DEFAULT NULL,
  `ANTIBIOTIK` varchar(200) DEFAULT NULL,
  `IDO_ILO` int(11) DEFAULT NULL,
  PRIMARY KEY (`no_rawat`),
  KEY `no_rawat` (`no_rawat`) USING BTREE,
  KEY `ETT` (`ETT`) USING BTREE,
  KEY `CVL` (`CVL`) USING BTREE,
  KEY `IVL` (`IVL`) USING BTREE,
  KEY `SPUTUM` (`SPUTUM`) USING BTREE,
  KEY `ANTIBIOTIK` (`ANTIBIOTIK`) USING BTREE,
  KEY `DARAH` (`DARAH`) USING BTREE,
  KEY `URINE` (`URINE`) USING BTREE,
  KEY `DEKU` (`DEKU`) USING BTREE,
  KEY `ISK` (`ISK`) USING BTREE,
  KEY `PLEB` (`PLEB`) USING BTREE,
  KEY `IAD` (`IAD`) USING BTREE,
  KEY `VAP` (`VAP`) USING BTREE,
  KEY `UC` (`UC`) USING BTREE,
  CONSTRAINT `data_HAIs_ibfk_1` FOREIGN KEY (`no_rawat`) REFERENCES `reg_periksa` (`no_rawat`) ON DELETE CASCADE ON UPDATE CASCADE
) ENGINE=InnoDB DEFAULT CHARSET=latin1 ROW_FORMAT=COMPACT;

/*Table structure for table `data_igd` */

DROP TABLE IF EXISTS `data_igd`;

CREATE TABLE `data_igd` (
  `no_rawat` varchar(17) NOT NULL,
  `tindakan_lanjut` varchar(50) DEFAULT NULL,
  `trauma` varchar(50) DEFAULT NULL,
  `datang_sendiri` varchar(50) DEFAULT NULL,
  `non_trauma` varchar(60) DEFAULT NULL,
  `ket_igd` varchar(50) DEFAULT NULL,
  PRIMARY KEY (`no_rawat`),
  CONSTRAINT `FK_data_igd_reg_periksa` FOREIGN KEY (`no_rawat`) REFERENCES `reg_periksa` (`no_rawat`) ON DELETE CASCADE ON UPDATE CASCADE
) ENGINE=InnoDB DEFAULT CHARSET=latin1 ROW_FORMAT=COMPACT;

/*Table structure for table `data_persalinan` */

DROP TABLE IF EXISTS `data_persalinan`;

CREATE TABLE `data_persalinan` (
  `no_rawat` varchar(17) NOT NULL,
  `no_rkm_medis` varchar(15) NOT NULL,
  `jns_persalinan` enum('Persalinan Normal','Sectio Caesarean (SC)','Perdarahan Sebelum Persalinan','Perdarahan Sesudah Persalinan','Pre Eclampsi','Eclampsi','Infeksi','Lain-lain','Abortus','Imunisasi-TT1','Imunisasi-TT2','Amniotomy','Partus Penyulit','Vacum Extraksi','SC + MOW','OP Laparatomi','Perawatan / Konservatif') DEFAULT NULL,
  `rujukan` enum('Rumah Sakit','Bidan','Puskesmas','Faskes Lainnya','Non Medis','Tanpa Rujukan') DEFAULT NULL,
  PRIMARY KEY (`no_rawat`),
  KEY `no_rkm_medis` (`no_rkm_medis`) USING BTREE,
  CONSTRAINT `FK_data_persalinan_reg_periksa` FOREIGN KEY (`no_rawat`) REFERENCES `reg_periksa` (`no_rawat`) ON UPDATE CASCADE,
  CONSTRAINT `data_persalinan_ibfk_1` FOREIGN KEY (`no_rkm_medis`) REFERENCES `pasien` (`no_rkm_medis`) ON UPDATE CASCADE
) ENGINE=InnoDB DEFAULT CHARSET=latin1 ROW_FORMAT=COMPACT;

/*Table structure for table `data_ponek` */

DROP TABLE IF EXISTS `data_ponek`;

CREATE TABLE `data_ponek` (
  `no_rawat` varchar(18) NOT NULL,
  `no_rkm_medis` varchar(15) NOT NULL,
  `jenis_alamat` enum('Dalam Wilayah','Luar Wilayah','') DEFAULT NULL,
  `hamil_ke` enum('1','2-4','>=5','') DEFAULT NULL,
  `umur_kehamilan` enum('<37','38-41','>=42','') DEFAULT NULL,
  `cara_persalinan` enum('Spt Kepala','Spt Bokong','Spt dengan Induksi','VE','SC Cito','SC Elektif','Tidak Ada','') DEFAULT NULL,
  `tindakan_lain` enum('Manual Plac','Curretase','Tidak Ada','') DEFAULT NULL,
  `tgl_input` date DEFAULT NULL,
  PRIMARY KEY (`no_rawat`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1 ROW_FORMAT=COMPACT;

/*Table structure for table `data_rehab_medik` */

DROP TABLE IF EXISTS `data_rehab_medik`;

CREATE TABLE `data_rehab_medik` (
  `no_rawat` varchar(17) CHARACTER SET latin1 NOT NULL,
  `jns_rehabmedik` enum('FISIOTERAPI','OKUPASI TERAPI','TERAPI WICARA') DEFAULT NULL,
  PRIMARY KEY (`no_rawat`),
  CONSTRAINT `FK_data_rehab_medik_reg_periksa` FOREIGN KEY (`no_rawat`) REFERENCES `reg_periksa` (`no_rawat`) ON DELETE CASCADE ON UPDATE CASCADE
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

/*Table structure for table `data_triase_igd` */

DROP TABLE IF EXISTS `data_triase_igd`;

CREATE TABLE `data_triase_igd` (
  `no_rawat` varchar(17) NOT NULL,
  `tgl_kunjungan` datetime NOT NULL,
  `cara_masuk` enum('Jalan','Brankar','Kursi Roda','Digendong') NOT NULL,
  `alat_transportasi` enum('-','AGD','Sendiri','Swasta') NOT NULL,
  `alasan_kedatangan` enum('Datang Sendiri','Polisi','Rujukan','-') NOT NULL,
  `keterangan_kedatangan` varchar(100) NOT NULL,
  `kode_kasus` varchar(3) NOT NULL,
  `tekanan_darah` varchar(7) NOT NULL,
  `nadi` varchar(3) NOT NULL,
  `pernapasan` varchar(3) NOT NULL,
  `suhu` varchar(3) NOT NULL,
  `saturasi_o2` varchar(3) NOT NULL,
  `nyeri` varchar(5) NOT NULL,
  PRIMARY KEY (`no_rawat`),
  KEY `kode_kasus` (`kode_kasus`) USING BTREE,
  CONSTRAINT `data_triase_igd_ibfk_1` FOREIGN KEY (`no_rawat`) REFERENCES `reg_periksa` (`no_rawat`) ON DELETE CASCADE ON UPDATE CASCADE,
  CONSTRAINT `data_triase_igd_ibfk_2` FOREIGN KEY (`kode_kasus`) REFERENCES `master_triase_macam_kasus` (`kode_kasus`) ON DELETE CASCADE ON UPDATE CASCADE
) ENGINE=InnoDB DEFAULT CHARSET=latin1 ROW_FORMAT=COMPACT;

/*Table structure for table `data_triase_igddetail_skala1` */

DROP TABLE IF EXISTS `data_triase_igddetail_skala1`;

CREATE TABLE `data_triase_igddetail_skala1` (
  `no_rawat` varchar(17) NOT NULL,
  `kode_skala1` varchar(3) NOT NULL,
  PRIMARY KEY (`no_rawat`,`kode_skala1`),
  KEY `data_triase_igddetail_skala1_ibfk_1` (`kode_skala1`) USING BTREE,
  CONSTRAINT `data_triase_igddetail_skala1_ibfk_1` FOREIGN KEY (`kode_skala1`) REFERENCES `master_triase_skala1` (`kode_skala1`) ON DELETE CASCADE ON UPDATE CASCADE,
  CONSTRAINT `data_triase_igddetail_skala1_ibfk_2` FOREIGN KEY (`no_rawat`) REFERENCES `reg_periksa` (`no_rawat`) ON DELETE CASCADE ON UPDATE CASCADE
) ENGINE=InnoDB DEFAULT CHARSET=latin1 ROW_FORMAT=COMPACT;

/*Table structure for table `data_triase_igddetail_skala2` */

DROP TABLE IF EXISTS `data_triase_igddetail_skala2`;

CREATE TABLE `data_triase_igddetail_skala2` (
  `no_rawat` varchar(17) NOT NULL,
  `kode_skala2` varchar(3) NOT NULL,
  PRIMARY KEY (`no_rawat`,`kode_skala2`),
  KEY `kode_skala2` (`kode_skala2`) USING BTREE,
  CONSTRAINT `data_triase_igddetail_skala2_ibfk_1` FOREIGN KEY (`no_rawat`) REFERENCES `reg_periksa` (`no_rawat`) ON DELETE CASCADE ON UPDATE CASCADE,
  CONSTRAINT `data_triase_igddetail_skala2_ibfk_2` FOREIGN KEY (`kode_skala2`) REFERENCES `master_triase_skala2` (`kode_skala2`) ON DELETE CASCADE ON UPDATE CASCADE
) ENGINE=InnoDB DEFAULT CHARSET=latin1 ROW_FORMAT=COMPACT;

/*Table structure for table `data_triase_igddetail_skala3` */

DROP TABLE IF EXISTS `data_triase_igddetail_skala3`;

CREATE TABLE `data_triase_igddetail_skala3` (
  `no_rawat` varchar(17) NOT NULL,
  `kode_skala3` varchar(3) NOT NULL,
  PRIMARY KEY (`no_rawat`,`kode_skala3`),
  KEY `kode_skala3` (`kode_skala3`) USING BTREE,
  CONSTRAINT `data_triase_igddetail_skala3_ibfk_1` FOREIGN KEY (`no_rawat`) REFERENCES `reg_periksa` (`no_rawat`) ON DELETE CASCADE ON UPDATE CASCADE,
  CONSTRAINT `data_triase_igddetail_skala3_ibfk_2` FOREIGN KEY (`kode_skala3`) REFERENCES `master_triase_skala3` (`kode_skala3`) ON DELETE CASCADE ON UPDATE CASCADE
) ENGINE=InnoDB DEFAULT CHARSET=latin1 ROW_FORMAT=COMPACT;

/*Table structure for table `data_triase_igddetail_skala4` */

DROP TABLE IF EXISTS `data_triase_igddetail_skala4`;

CREATE TABLE `data_triase_igddetail_skala4` (
  `no_rawat` varchar(17) NOT NULL,
  `kode_skala4` varchar(3) NOT NULL,
  PRIMARY KEY (`no_rawat`,`kode_skala4`),
  KEY `kode_skala4` (`kode_skala4`) USING BTREE,
  CONSTRAINT `data_triase_igddetail_skala4_ibfk_1` FOREIGN KEY (`no_rawat`) REFERENCES `reg_periksa` (`no_rawat`) ON DELETE CASCADE ON UPDATE CASCADE,
  CONSTRAINT `data_triase_igddetail_skala4_ibfk_2` FOREIGN KEY (`kode_skala4`) REFERENCES `master_triase_skala4` (`kode_skala4`) ON DELETE CASCADE ON UPDATE CASCADE
) ENGINE=InnoDB DEFAULT CHARSET=latin1 ROW_FORMAT=COMPACT;

/*Table structure for table `data_triase_igddetail_skala5` */

DROP TABLE IF EXISTS `data_triase_igddetail_skala5`;

CREATE TABLE `data_triase_igddetail_skala5` (
  `no_rawat` varchar(17) NOT NULL,
  `kode_skala5` varchar(3) NOT NULL,
  PRIMARY KEY (`no_rawat`,`kode_skala5`),
  KEY `kode_skala5` (`kode_skala5`) USING BTREE,
  CONSTRAINT `data_triase_igddetail_skala5_ibfk_1` FOREIGN KEY (`no_rawat`) REFERENCES `reg_periksa` (`no_rawat`) ON DELETE CASCADE ON UPDATE CASCADE,
  CONSTRAINT `data_triase_igddetail_skala5_ibfk_2` FOREIGN KEY (`kode_skala5`) REFERENCES `master_triase_skala5` (`kode_skala5`) ON DELETE CASCADE ON UPDATE CASCADE
) ENGINE=InnoDB DEFAULT CHARSET=latin1 ROW_FORMAT=COMPACT;

/*Table structure for table `data_triase_igdprimer` */

DROP TABLE IF EXISTS `data_triase_igdprimer`;

CREATE TABLE `data_triase_igdprimer` (
  `no_rawat` varchar(17) NOT NULL,
  `keluhan_utama` varchar(400) NOT NULL,
  `kebutuhan_khusus` enum('-','UPPA','Airborne','Dekontaminan') NOT NULL,
  `catatan` varchar(100) NOT NULL,
  `plan` enum('Ruang Resusitasi','Ruang Kritis') NOT NULL,
  `tanggaltriase` datetime NOT NULL,
  `nip` varchar(20) NOT NULL,
  PRIMARY KEY (`no_rawat`),
  KEY `nip` (`nip`) USING BTREE,
  CONSTRAINT `data_triase_igdprimer_ibfk_1` FOREIGN KEY (`no_rawat`) REFERENCES `reg_periksa` (`no_rawat`) ON DELETE CASCADE ON UPDATE CASCADE,
  CONSTRAINT `data_triase_igdprimer_ibfk_2` FOREIGN KEY (`nip`) REFERENCES `petugas` (`nip`) ON DELETE CASCADE ON UPDATE CASCADE
) ENGINE=InnoDB DEFAULT CHARSET=latin1 ROW_FORMAT=COMPACT;

/*Table structure for table `data_triase_igdsekunder` */

DROP TABLE IF EXISTS `data_triase_igdsekunder`;

CREATE TABLE `data_triase_igdsekunder` (
  `no_rawat` varchar(17) NOT NULL,
  `anamnesa_singkat` varchar(400) NOT NULL,
  `catatan` varchar(100) NOT NULL,
  `plan` enum('Zona Kuning','Zona Hijau') NOT NULL,
  `tanggaltriase` datetime NOT NULL,
  `nip` varchar(20) NOT NULL,
  PRIMARY KEY (`no_rawat`),
  KEY `nip` (`nip`) USING BTREE,
  CONSTRAINT `data_triase_igdsekunder_ibfk_1` FOREIGN KEY (`nip`) REFERENCES `petugas` (`nip`) ON DELETE CASCADE ON UPDATE CASCADE
) ENGINE=InnoDB DEFAULT CHARSET=latin1 ROW_FORMAT=COMPACT;

/*Table structure for table `databarang` */

DROP TABLE IF EXISTS `databarang`;

CREATE TABLE `databarang` (
  `kode_brng` varchar(15) NOT NULL DEFAULT '',
  `nama_brng` varchar(80) DEFAULT NULL,
  `kode_sat` char(4) DEFAULT NULL,
  `letak_barang` varchar(50) DEFAULT NULL,
  `h_beli` double DEFAULT NULL,
  `ralan` double DEFAULT NULL,
  `kelas1` double DEFAULT NULL,
  `kelas2` double DEFAULT NULL,
  `kelas3` double DEFAULT NULL,
  `utama` double DEFAULT NULL,
  `vip` double DEFAULT NULL,
  `vvip` double DEFAULT NULL,
  `beliluar` double DEFAULT NULL,
  `jualbebas` double DEFAULT NULL,
  `karyawan` double DEFAULT NULL,
  `stokminimal` double DEFAULT NULL,
  `kdjns` char(4) DEFAULT NULL,
  `kapasitas` double NOT NULL,
  `expire` date DEFAULT NULL,
  `status` enum('0','1') NOT NULL,
  `kode_industri` char(5) DEFAULT NULL,
  `kode_kategori` char(4) DEFAULT NULL,
  `kode_golongan` char(4) DEFAULT NULL,
  `tipe_brg` enum('Alkes','Generik','Non Generik','-') DEFAULT NULL,
  `high_alert` enum('Ya','Tidak') DEFAULT NULL,
  PRIMARY KEY (`kode_brng`),
  KEY `kode_sat` (`kode_sat`),
  KEY `kdjns` (`kdjns`),
  KEY `nama_brng` (`nama_brng`),
  KEY `letak_barang` (`letak_barang`),
  KEY `h_beli` (`h_beli`),
  KEY `h_distributor` (`ralan`),
  KEY `h_grosir` (`kelas1`),
  KEY `h_retail` (`kelas2`),
  KEY `stok` (`stokminimal`),
  KEY `kapasitas` (`kapasitas`),
  KEY `kode_industri` (`kode_industri`),
  KEY `kelas3` (`kelas3`) USING BTREE,
  KEY `utama` (`utama`) USING BTREE,
  KEY `vip` (`vip`) USING BTREE,
  KEY `vvip` (`vvip`) USING BTREE,
  KEY `beliluar` (`beliluar`) USING BTREE,
  KEY `jualbebas` (`jualbebas`) USING BTREE,
  KEY `karyawan` (`karyawan`) USING BTREE,
  KEY `expire` (`expire`) USING BTREE,
  KEY `status` (`status`) USING BTREE,
  KEY `kode_kategori` (`kode_kategori`) USING BTREE,
  KEY `kode_golongan` (`kode_golongan`) USING BTREE,
  KEY `kode_brng` (`kode_brng`) USING BTREE,
  CONSTRAINT `databarang_ibfk_2` FOREIGN KEY (`kdjns`) REFERENCES `jenis` (`kdjns`) ON UPDATE CASCADE,
  CONSTRAINT `databarang_ibfk_3` FOREIGN KEY (`kode_sat`) REFERENCES `kodesatuan` (`kode_sat`) ON UPDATE CASCADE,
  CONSTRAINT `databarang_ibfk_4` FOREIGN KEY (`kode_industri`) REFERENCES `industrifarmasi` (`kode_industri`) ON DELETE CASCADE ON UPDATE CASCADE,
  CONSTRAINT `databarang_ibfk_5` FOREIGN KEY (`kode_kategori`) REFERENCES `kategori_barang` (`kode`) ON DELETE NO ACTION ON UPDATE CASCADE,
  CONSTRAINT `databarang_ibfk_6` FOREIGN KEY (`kode_golongan`) REFERENCES `golongan_barang` (`kode`) ON DELETE NO ACTION ON UPDATE CASCADE
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

/*Table structure for table `databarang_temp` */

DROP TABLE IF EXISTS `databarang_temp`;

CREATE TABLE `databarang_temp` (
  `kode_brng` varchar(15) NOT NULL DEFAULT '',
  `nama_brng` varchar(80) DEFAULT NULL,
  `kode_kategori` char(4) DEFAULT NULL,
  `kode_golongan` char(4) DEFAULT NULL,
  `tipe_brg` enum('Alkes','Generik','Non Generik','-') DEFAULT NULL,
  `high_alert` enum('Ya','Tidak') DEFAULT NULL,
  PRIMARY KEY (`kode_brng`),
  KEY `kode_kategori` (`kode_kategori`) USING BTREE,
  KEY `kode_golongan` (`kode_golongan`) USING BTREE,
  CONSTRAINT `databarang_temp_ibfk_1` FOREIGN KEY (`kode_kategori`) REFERENCES `kategori_barang` (`kode`) ON DELETE CASCADE ON UPDATE CASCADE,
  CONSTRAINT `databarang_temp_ibfk_2` FOREIGN KEY (`kode_golongan`) REFERENCES `golongan_barang` (`kode`) ON DELETE CASCADE ON UPDATE CASCADE
) ENGINE=InnoDB DEFAULT CHARSET=latin1 ROW_FORMAT=COMPACT;

/*Table structure for table `datasuplier` */

DROP TABLE IF EXISTS `datasuplier`;

CREATE TABLE `datasuplier` (
  `kode_suplier` char(5) NOT NULL,
  `nama_suplier` varchar(50) DEFAULT NULL,
  `alamat` varchar(50) DEFAULT NULL,
  `kota` varchar(20) DEFAULT NULL,
  `no_telp` varchar(13) DEFAULT NULL,
  PRIMARY KEY (`kode_suplier`),
  KEY `nama_suplier` (`nama_suplier`),
  KEY `alamat` (`alamat`),
  KEY `kota` (`kota`),
  KEY `no_telp` (`no_telp`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

/*Table structure for table `departemen` */

DROP TABLE IF EXISTS `departemen`;

CREATE TABLE `departemen` (
  `dep_id` char(4) NOT NULL,
  `nama` varchar(25) NOT NULL,
  PRIMARY KEY (`dep_id`),
  KEY `nama` (`nama`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

/*Table structure for table `deposit` */

DROP TABLE IF EXISTS `deposit`;

CREATE TABLE `deposit` (
  `no_rawat` varchar(18) NOT NULL,
  `tgl_deposit` datetime NOT NULL,
  `besar_deposit` double DEFAULT NULL,
  `nip` varchar(20) NOT NULL,
  PRIMARY KEY (`no_rawat`,`tgl_deposit`),
  KEY `nip` (`nip`),
  KEY `besar_deposit` (`besar_deposit`),
  CONSTRAINT `deposit_ibfk_1` FOREIGN KEY (`no_rawat`) REFERENCES `reg_periksa` (`no_rawat`) ON DELETE CASCADE ON UPDATE CASCADE,
  CONSTRAINT `deposit_ibfk_2` FOREIGN KEY (`nip`) REFERENCES `petugas` (`nip`) ON DELETE CASCADE ON UPDATE CASCADE
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

/*Table structure for table `detail_beri_diet` */

DROP TABLE IF EXISTS `detail_beri_diet`;

CREATE TABLE `detail_beri_diet` (
  `no_rawat` varchar(18) NOT NULL,
  `kd_kamar` varchar(15) NOT NULL,
  `tanggal` date NOT NULL,
  `waktu` enum('Pagi','Siang','Sore','Malam') NOT NULL,
  `kd_diet` varchar(6) NOT NULL,
  PRIMARY KEY (`no_rawat`,`kd_kamar`,`tanggal`,`waktu`,`kd_diet`),
  KEY `kd_kamar` (`kd_kamar`),
  KEY `kd_diet` (`kd_diet`),
  KEY `tanggal` (`tanggal`),
  KEY `waktu` (`waktu`),
  CONSTRAINT `detail_beri_diet_ibfk_4` FOREIGN KEY (`no_rawat`) REFERENCES `reg_periksa` (`no_rawat`) ON UPDATE CASCADE,
  CONSTRAINT `detail_beri_diet_ibfk_5` FOREIGN KEY (`kd_kamar`) REFERENCES `kamar` (`kd_kamar`) ON UPDATE CASCADE,
  CONSTRAINT `detail_beri_diet_ibfk_6` FOREIGN KEY (`kd_diet`) REFERENCES `diet` (`kd_diet`) ON UPDATE CASCADE
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

/*Table structure for table `detail_beri_diet_ralan` */

DROP TABLE IF EXISTS `detail_beri_diet_ralan`;

CREATE TABLE `detail_beri_diet_ralan` (
  `no_rawat` varchar(18) NOT NULL,
  `kd_poli` varchar(15) NOT NULL,
  `tanggal` date NOT NULL,
  `waktu` enum('Pagi','Siang','Sore','Malam') NOT NULL,
  `kd_diet` varchar(6) NOT NULL,
  PRIMARY KEY (`no_rawat`,`kd_poli`,`tanggal`,`waktu`,`kd_diet`),
  KEY `kd_poli` (`kd_poli`) USING BTREE,
  KEY `kd_diet` (`kd_diet`) USING BTREE,
  CONSTRAINT `detail_beri_diet_ralan_ibfk_1` FOREIGN KEY (`no_rawat`) REFERENCES `reg_periksa` (`no_rawat`) ON UPDATE CASCADE,
  CONSTRAINT `detail_beri_diet_ralan_ibfk_2` FOREIGN KEY (`kd_poli`) REFERENCES `poliklinik` (`kd_poli`) ON UPDATE CASCADE,
  CONSTRAINT `detail_beri_diet_ralan_ibfk_3` FOREIGN KEY (`kd_diet`) REFERENCES `diet` (`kd_diet`) ON UPDATE CASCADE
) ENGINE=InnoDB DEFAULT CHARSET=latin1 ROW_FORMAT=COMPACT;

/*Table structure for table `detail_diagnosa_ponek` */

DROP TABLE IF EXISTS `detail_diagnosa_ponek`;

CREATE TABLE `detail_diagnosa_ponek` (
  `no_rawat` varchar(18) NOT NULL,
  `no_rkm_medis` varchar(15) NOT NULL,
  `kode_diagnosa` varchar(10) NOT NULL,
  PRIMARY KEY (`no_rawat`,`kode_diagnosa`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1 ROW_FORMAT=COMPACT;

/*Table structure for table `detail_nota_inap` */

DROP TABLE IF EXISTS `detail_nota_inap`;

CREATE TABLE `detail_nota_inap` (
  `no_rawat` varchar(17) DEFAULT NULL,
  `nama_bayar` varchar(50) DEFAULT NULL,
  `besarppn` double DEFAULT NULL,
  `besar_bayar` double DEFAULT NULL,
  KEY `no_rawat` (`no_rawat`) USING BTREE,
  KEY `nama_bayar` (`nama_bayar`) USING BTREE,
  KEY `besarppn` (`besarppn`) USING BTREE,
  KEY `besar_bayar` (`besar_bayar`) USING BTREE,
  CONSTRAINT `detail_nota_inap_ibfk_1` FOREIGN KEY (`no_rawat`) REFERENCES `reg_periksa` (`no_rawat`) ON UPDATE CASCADE,
  CONSTRAINT `detail_nota_inap_ibfk_2` FOREIGN KEY (`nama_bayar`) REFERENCES `akun_bayar` (`nama_bayar`) ON UPDATE CASCADE
) ENGINE=InnoDB DEFAULT CHARSET=latin1 ROW_FORMAT=COMPACT;

/*Table structure for table `detail_nota_jalan` */

DROP TABLE IF EXISTS `detail_nota_jalan`;

CREATE TABLE `detail_nota_jalan` (
  `no_rawat` varchar(18) DEFAULT NULL,
  `nama_bayar` varchar(50) DEFAULT NULL,
  `besarppn` double DEFAULT NULL,
  `besar_bayar` double DEFAULT NULL,
  `no_nota` varchar(17) DEFAULT NULL,
  KEY `no_rawat` (`no_rawat`) USING BTREE,
  KEY `nama_bayar` (`nama_bayar`) USING BTREE,
  KEY `besarppn` (`besarppn`) USING BTREE,
  KEY `besar_bayar` (`besar_bayar`) USING BTREE,
  CONSTRAINT `detail_nota_jalan_ibfk_1` FOREIGN KEY (`no_rawat`) REFERENCES `reg_periksa` (`no_rawat`) ON UPDATE CASCADE,
  CONSTRAINT `detail_nota_jalan_ibfk_2` FOREIGN KEY (`nama_bayar`) REFERENCES `akun_bayar` (`nama_bayar`) ON UPDATE CASCADE
) ENGINE=InnoDB DEFAULT CHARSET=latin1 ROW_FORMAT=COMPACT;

/*Table structure for table `detail_pemberian_obat` */

DROP TABLE IF EXISTS `detail_pemberian_obat`;

CREATE TABLE `detail_pemberian_obat` (
  `tgl_perawatan` date NOT NULL DEFAULT '0000-00-00',
  `jam` time NOT NULL DEFAULT '00:00:00',
  `no_rawat` varchar(18) NOT NULL DEFAULT '',
  `kode_brng` varchar(15) NOT NULL,
  `h_beli` double DEFAULT NULL,
  `biaya_obat` double DEFAULT NULL,
  `jml` double NOT NULL,
  `embalase` double DEFAULT NULL,
  `tuslah` double DEFAULT NULL,
  `total` double NOT NULL,
  `status` enum('Ralan','Ranap') DEFAULT NULL,
  `kd_bangsal` char(5) DEFAULT NULL,
  `stts_bayar` enum('BELUM','BAYAR') DEFAULT 'BELUM',
  `no_nota` varchar(17) DEFAULT NULL,
  PRIMARY KEY (`tgl_perawatan`,`jam`,`no_rawat`,`kode_brng`),
  KEY `no_rawat` (`no_rawat`),
  KEY `kd_obat` (`kode_brng`),
  KEY `tgl_perawatan` (`tgl_perawatan`),
  KEY `jam` (`jam`),
  KEY `jml` (`jml`),
  KEY `tambahan` (`embalase`),
  KEY `total` (`total`),
  KEY `biaya_obat` (`biaya_obat`),
  KEY `kd_bangsal` (`kd_bangsal`) USING BTREE,
  KEY `no_nota` (`no_nota`) USING BTREE,
  CONSTRAINT `detail_pemberian_obat_ibfk_3` FOREIGN KEY (`kode_brng`) REFERENCES `databarang` (`kode_brng`) ON DELETE CASCADE ON UPDATE CASCADE,
  CONSTRAINT `detail_pemberian_obat_ibfk_4` FOREIGN KEY (`no_rawat`) REFERENCES `reg_periksa` (`no_rawat`) ON DELETE CASCADE ON UPDATE CASCADE,
  CONSTRAINT `detail_pemberian_obat_ibfk_5` FOREIGN KEY (`kd_bangsal`) REFERENCES `bangsal` (`kd_bangsal`) ON DELETE NO ACTION ON UPDATE CASCADE
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

/*Table structure for table `detail_periksa_lab` */

DROP TABLE IF EXISTS `detail_periksa_lab`;

CREATE TABLE `detail_periksa_lab` (
  `no_rawat` varchar(18) NOT NULL,
  `kd_jenis_prw` varchar(15) NOT NULL,
  `tgl_periksa` date NOT NULL,
  `jam` time NOT NULL,
  `id_template` int(11) NOT NULL,
  `nilai` varchar(60) NOT NULL,
  `nilai_rujukan` varchar(20) NOT NULL,
  `keterangan` varchar(60) NOT NULL,
  `bagian_rs` double NOT NULL,
  `bhp` double NOT NULL,
  `bagian_perujuk` double NOT NULL,
  `bagian_dokter` double NOT NULL,
  `bagian_laborat` double NOT NULL,
  `kso` double DEFAULT NULL,
  `menejemen` double DEFAULT NULL,
  `biaya_item` double NOT NULL,
  `no_nota` varchar(17) DEFAULT NULL,
  PRIMARY KEY (`no_rawat`,`kd_jenis_prw`,`tgl_periksa`,`jam`,`id_template`),
  KEY `id_template` (`id_template`),
  KEY `kd_jenis_prw` (`kd_jenis_prw`),
  KEY `tgl_periksa` (`tgl_periksa`),
  KEY `jam` (`jam`),
  KEY `nilai` (`nilai`),
  KEY `nilai_rujukan` (`nilai_rujukan`),
  KEY `keterangan` (`keterangan`),
  KEY `biaya_item` (`biaya_item`),
  KEY `menejemen` (`menejemen`) USING BTREE,
  KEY `kso` (`kso`) USING BTREE,
  KEY `bagian_rs` (`bagian_rs`) USING BTREE,
  KEY `bhp` (`bhp`) USING BTREE,
  KEY `bagian_perujuk` (`bagian_perujuk`) USING BTREE,
  KEY `bagian_dokter` (`bagian_dokter`) USING BTREE,
  KEY `bagian_laborat` (`bagian_laborat`) USING BTREE,
  KEY `no_nota` (`no_nota`) USING BTREE,
  CONSTRAINT `detail_periksa_lab_ibfk_10` FOREIGN KEY (`no_rawat`) REFERENCES `reg_periksa` (`no_rawat`) ON UPDATE CASCADE,
  CONSTRAINT `detail_periksa_lab_ibfk_11` FOREIGN KEY (`kd_jenis_prw`) REFERENCES `jns_perawatan_lab` (`kd_jenis_prw`) ON DELETE CASCADE ON UPDATE CASCADE,
  CONSTRAINT `detail_periksa_lab_ibfk_12` FOREIGN KEY (`id_template`) REFERENCES `template_laboratorium` (`id_template`) ON DELETE CASCADE ON UPDATE CASCADE
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

/*Table structure for table `detail_periksa_lab_rujukan` */

DROP TABLE IF EXISTS `detail_periksa_lab_rujukan`;

CREATE TABLE `detail_periksa_lab_rujukan` (
  `no_lab` varchar(17) NOT NULL,
  `kd_jenis_prw` varchar(15) NOT NULL,
  `id_template` int(11) NOT NULL,
  `nilai` varchar(60) NOT NULL,
  `nilai_rujukan` varchar(20) NOT NULL,
  `keterangan` varchar(60) NOT NULL,
  `bagian_rs` double NOT NULL,
  `bhp` double NOT NULL,
  `bagian_perujuk` double NOT NULL,
  `bagian_dokter` double NOT NULL,
  `bagian_laborat` double NOT NULL,
  `kso` double DEFAULT NULL,
  `menejemen` double DEFAULT NULL,
  `biaya_item` double NOT NULL,
  KEY `kd_jenis_prw` (`kd_jenis_prw`),
  KEY `no_lab` (`no_lab`),
  KEY `id_template` (`id_template`),
  CONSTRAINT `detail_periksa_lab_rujukan_ibfk_1` FOREIGN KEY (`kd_jenis_prw`) REFERENCES `jns_perawatan_lab` (`kd_jenis_prw`) ON DELETE CASCADE ON UPDATE CASCADE,
  CONSTRAINT `detail_periksa_lab_rujukan_ibfk_2` FOREIGN KEY (`no_lab`) REFERENCES `pendaftaran_lab_rujukan` (`no_lab`) ON DELETE CASCADE ON UPDATE CASCADE,
  CONSTRAINT `detail_periksa_lab_rujukan_ibfk_3` FOREIGN KEY (`id_template`) REFERENCES `template_laboratorium` (`id_template`) ON DELETE CASCADE ON UPDATE CASCADE
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

/*Table structure for table `detail_piutang_pasien` */

DROP TABLE IF EXISTS `detail_piutang_pasien`;

CREATE TABLE `detail_piutang_pasien` (
  `no_rawat` varchar(18) DEFAULT NULL,
  `nama_bayar` varchar(50) DEFAULT NULL,
  `kd_pj` char(3) DEFAULT NULL,
  `totalpiutang` double DEFAULT NULL,
  `sisapiutang` double DEFAULT NULL,
  `tgltempo` date DEFAULT NULL,
  `no_nota` varchar(17) DEFAULT NULL,
  KEY `no_rawat` (`no_rawat`) USING BTREE,
  KEY `nama_bayar` (`nama_bayar`) USING BTREE,
  KEY `kd_pj` (`kd_pj`) USING BTREE,
  CONSTRAINT `detail_piutang_pasien_ibfk_1` FOREIGN KEY (`no_rawat`) REFERENCES `reg_periksa` (`no_rawat`) ON UPDATE CASCADE,
  CONSTRAINT `detail_piutang_pasien_ibfk_2` FOREIGN KEY (`nama_bayar`) REFERENCES `akun_piutang` (`nama_bayar`) ON UPDATE CASCADE,
  CONSTRAINT `detail_piutang_pasien_ibfk_3` FOREIGN KEY (`kd_pj`) REFERENCES `penjab` (`kd_pj`) ON UPDATE CASCADE
) ENGINE=InnoDB DEFAULT CHARSET=latin1 ROW_FORMAT=COMPACT;

/*Table structure for table `detailbeli` */

DROP TABLE IF EXISTS `detailbeli`;

CREATE TABLE `detailbeli` (
  `no_faktur` varchar(20) NOT NULL,
  `kode_brng` varchar(15) NOT NULL DEFAULT '',
  `kode_sat` char(4) DEFAULT NULL,
  `jumlah` double DEFAULT NULL,
  `h_beli` double DEFAULT NULL,
  `subtotal` double DEFAULT NULL,
  `dis` double NOT NULL,
  `besardis` double NOT NULL,
  `total` double NOT NULL,
  `no_batch` varchar(10) NOT NULL,
  `jumlah2` double DEFAULT NULL,
  PRIMARY KEY (`no_faktur`,`kode_brng`),
  KEY `no_faktur` (`no_faktur`),
  KEY `kode_brng` (`kode_brng`),
  KEY `kode_sat` (`kode_sat`),
  KEY `jumlah` (`jumlah`),
  KEY `h_beli` (`h_beli`),
  KEY `subtotal` (`subtotal`),
  KEY `dis` (`dis`),
  KEY `besardis` (`besardis`),
  KEY `total` (`total`),
  KEY `kode_sat_2` (`kode_sat`),
  CONSTRAINT `detailbeli_ibfk_5` FOREIGN KEY (`kode_brng`) REFERENCES `databarang` (`kode_brng`) ON DELETE CASCADE ON UPDATE CASCADE,
  CONSTRAINT `detailbeli_ibfk_6` FOREIGN KEY (`kode_sat`) REFERENCES `kodesatuan` (`kode_sat`) ON UPDATE CASCADE,
  CONSTRAINT `detailbeli_ibfk_7` FOREIGN KEY (`no_faktur`) REFERENCES `pembelian` (`no_faktur`) ON DELETE CASCADE ON UPDATE CASCADE
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

/*Table structure for table `detailjual` */

DROP TABLE IF EXISTS `detailjual`;

CREATE TABLE `detailjual` (
  `nota_jual` varchar(20) NOT NULL,
  `kode_brng` varchar(15) NOT NULL DEFAULT '',
  `kode_sat` char(4) DEFAULT NULL,
  `h_jual` double DEFAULT NULL,
  `h_beli` double DEFAULT NULL,
  `jumlah` double DEFAULT NULL,
  `subtotal` double DEFAULT NULL,
  `dis` double DEFAULT NULL,
  `bsr_dis` double DEFAULT NULL,
  `tambahan` double DEFAULT NULL,
  `total` double DEFAULT NULL,
  PRIMARY KEY (`nota_jual`,`kode_brng`),
  KEY `nota_jual` (`nota_jual`),
  KEY `kode_brng` (`kode_brng`),
  KEY `kode_sat` (`kode_sat`),
  KEY `h_jual` (`h_jual`),
  KEY `h_beli` (`h_beli`),
  KEY `jumlah` (`jumlah`),
  KEY `subtotal` (`subtotal`),
  KEY `dis` (`dis`),
  KEY `bsr_dis` (`bsr_dis`),
  KEY `tambahan` (`tambahan`),
  KEY `total` (`total`),
  CONSTRAINT `detailjual_ibfk_1` FOREIGN KEY (`nota_jual`) REFERENCES `penjualan` (`nota_jual`) ON DELETE CASCADE ON UPDATE CASCADE,
  CONSTRAINT `detailjual_ibfk_2` FOREIGN KEY (`kode_brng`) REFERENCES `databarang` (`kode_brng`) ON DELETE CASCADE ON UPDATE CASCADE
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

/*Table structure for table `detailjurnal` */

DROP TABLE IF EXISTS `detailjurnal`;

CREATE TABLE `detailjurnal` (
  `no_jurnal` varchar(20) DEFAULT NULL,
  `kd_rek` varchar(15) DEFAULT NULL,
  `debet` double DEFAULT NULL,
  `kredit` double DEFAULT NULL,
  KEY `no_jurnal` (`no_jurnal`),
  KEY `kd_rek` (`kd_rek`),
  KEY `debet` (`debet`),
  KEY `kredit` (`kredit`),
  CONSTRAINT `detailjurnal_ibfk_1` FOREIGN KEY (`no_jurnal`) REFERENCES `jurnal` (`no_jurnal`) ON DELETE CASCADE ON UPDATE CASCADE,
  CONSTRAINT `detailjurnal_ibfk_2` FOREIGN KEY (`kd_rek`) REFERENCES `rekening` (`kd_rek`) ON DELETE CASCADE ON UPDATE CASCADE
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

/*Table structure for table `detailpesan` */

DROP TABLE IF EXISTS `detailpesan`;

CREATE TABLE `detailpesan` (
  `no_faktur` varchar(30) NOT NULL,
  `kode_brng` varchar(15) NOT NULL DEFAULT '',
  `kode_sat` char(4) DEFAULT NULL,
  `jumlah` double DEFAULT NULL,
  `h_pesan` double DEFAULT NULL,
  `subtotal` double DEFAULT NULL,
  `dis` double NOT NULL,
  `besardis` double NOT NULL,
  `total` double NOT NULL,
  `no_batch` varchar(10) NOT NULL,
  `jumlah2` double DEFAULT NULL,
  PRIMARY KEY (`no_faktur`,`kode_brng`),
  KEY `no_faktur` (`no_faktur`),
  KEY `kode_brng` (`kode_brng`),
  KEY `kode_sat` (`kode_sat`),
  KEY `jumlah` (`jumlah`),
  KEY `h_pesan` (`h_pesan`),
  KEY `subtotal` (`subtotal`),
  KEY `dis` (`dis`),
  KEY `besardis` (`besardis`),
  KEY `total` (`total`),
  CONSTRAINT `detailpesan_ibfk_1` FOREIGN KEY (`kode_brng`) REFERENCES `databarang` (`kode_brng`) ON DELETE CASCADE ON UPDATE CASCADE,
  CONSTRAINT `detailpesan_ibfk_2` FOREIGN KEY (`kode_sat`) REFERENCES `kodesatuan` (`kode_sat`) ON UPDATE CASCADE,
  CONSTRAINT `detailpesan_ibfk_3` FOREIGN KEY (`no_faktur`) REFERENCES `pemesanan` (`no_faktur`) ON DELETE CASCADE ON UPDATE CASCADE
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

/*Table structure for table `detailpiutang` */

DROP TABLE IF EXISTS `detailpiutang`;

CREATE TABLE `detailpiutang` (
  `nota_piutang` varchar(20) NOT NULL,
  `kode_brng` varchar(15) NOT NULL DEFAULT '',
  `kode_sat` char(4) DEFAULT NULL,
  `h_jual` double DEFAULT NULL,
  `h_beli` double DEFAULT NULL,
  `jumlah` double DEFAULT NULL,
  `subtotal` double DEFAULT NULL,
  `dis` double DEFAULT NULL,
  `bsr_dis` double DEFAULT NULL,
  `total` double DEFAULT NULL,
  PRIMARY KEY (`nota_piutang`,`kode_brng`),
  KEY `nota_jual` (`nota_piutang`),
  KEY `kode_brng` (`kode_brng`),
  KEY `kode_sat` (`kode_sat`),
  KEY `h_jual` (`h_jual`),
  KEY `h_beli` (`h_beli`),
  KEY `jumlah` (`jumlah`),
  KEY `subtotal` (`subtotal`),
  KEY `dis` (`dis`),
  KEY `bsr_dis` (`bsr_dis`),
  KEY `total` (`total`),
  CONSTRAINT `detailpiutang_ibfk_1` FOREIGN KEY (`nota_piutang`) REFERENCES `piutang` (`nota_piutang`) ON DELETE CASCADE ON UPDATE CASCADE,
  CONSTRAINT `detailpiutang_ibfk_2` FOREIGN KEY (`kode_brng`) REFERENCES `databarang` (`kode_brng`) ON DELETE CASCADE ON UPDATE CASCADE
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

/*Table structure for table `detreturbeli` */

DROP TABLE IF EXISTS `detreturbeli`;

CREATE TABLE `detreturbeli` (
  `no_retur_beli` varchar(20) NOT NULL,
  `no_faktur` varchar(20) NOT NULL,
  `kode_brng` varchar(15) NOT NULL DEFAULT '',
  `kode_sat` char(4) DEFAULT NULL,
  `h_beli` double DEFAULT NULL,
  `jml_beli` double DEFAULT NULL,
  `h_retur` double DEFAULT NULL,
  `jml_retur` double DEFAULT NULL,
  `total` double DEFAULT NULL,
  `no_batch` varchar(10) NOT NULL,
  `jml_retur2` double DEFAULT NULL,
  PRIMARY KEY (`no_retur_beli`,`no_faktur`,`kode_brng`),
  KEY `no_retur_beli` (`no_retur_beli`),
  KEY `no_faktur` (`no_faktur`),
  KEY `kode_brng` (`kode_brng`),
  KEY `kode_sat` (`kode_sat`),
  KEY `h_beli` (`h_beli`),
  KEY `jml_beli` (`jml_beli`),
  KEY `h_retur` (`h_retur`),
  KEY `jml_retur` (`jml_retur`),
  KEY `total` (`total`),
  CONSTRAINT `detreturbeli_ibfk_2` FOREIGN KEY (`kode_brng`) REFERENCES `databarang` (`kode_brng`) ON DELETE CASCADE ON UPDATE CASCADE,
  CONSTRAINT `detreturbeli_ibfk_3` FOREIGN KEY (`no_retur_beli`) REFERENCES `returbeli` (`no_retur_beli`) ON DELETE CASCADE ON UPDATE CASCADE
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

/*Table structure for table `detreturjual` */

DROP TABLE IF EXISTS `detreturjual`;

CREATE TABLE `detreturjual` (
  `no_retur_jual` varchar(20) NOT NULL,
  `nota_jual` varchar(20) NOT NULL,
  `kode_brng` varchar(15) NOT NULL DEFAULT '',
  `kode_sat` char(4) DEFAULT NULL,
  `jml_jual` double DEFAULT NULL,
  `h_jual` double DEFAULT NULL,
  `jml_retur` double DEFAULT NULL,
  `h_retur` double DEFAULT NULL,
  `subtotal` double DEFAULT NULL,
  KEY `no_retur_jual` (`no_retur_jual`),
  KEY `nota_jual` (`nota_jual`),
  KEY `kode_brng` (`kode_brng`),
  KEY `kode_sat` (`kode_sat`),
  KEY `jml_jual` (`jml_jual`),
  KEY `h_jual` (`h_jual`),
  KEY `jml_retur` (`jml_retur`),
  KEY `h_retur` (`h_retur`),
  KEY `subtotal` (`subtotal`),
  CONSTRAINT `detreturjual_ibfk_1` FOREIGN KEY (`no_retur_jual`) REFERENCES `returjual` (`no_retur_jual`) ON DELETE CASCADE ON UPDATE CASCADE,
  CONSTRAINT `detreturjual_ibfk_3` FOREIGN KEY (`kode_brng`) REFERENCES `databarang` (`kode_brng`) ON DELETE CASCADE ON UPDATE CASCADE
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

/*Table structure for table `detreturpiutang` */

DROP TABLE IF EXISTS `detreturpiutang`;

CREATE TABLE `detreturpiutang` (
  `no_retur_piutang` varchar(20) NOT NULL,
  `nota_piutang` varchar(20) NOT NULL,
  `kode_brng` varchar(15) NOT NULL DEFAULT '',
  `kode_sat` char(4) DEFAULT NULL,
  `jml_piutang` double DEFAULT NULL,
  `h_piutang` double DEFAULT NULL,
  `jml_retur` double DEFAULT NULL,
  `h_retur` double DEFAULT NULL,
  `subtotal` double DEFAULT NULL,
  PRIMARY KEY (`no_retur_piutang`,`nota_piutang`,`kode_brng`),
  KEY `no_retur_piutang` (`no_retur_piutang`),
  KEY `nota_piutang` (`nota_piutang`),
  KEY `kode_brng` (`kode_brng`),
  KEY `kode_sat` (`kode_sat`),
  KEY `jml_piutang` (`jml_piutang`),
  KEY `h_piutang` (`h_piutang`),
  KEY `jml_retur` (`jml_retur`),
  KEY `h_retur` (`h_retur`),
  KEY `subtotal` (`subtotal`),
  CONSTRAINT `detreturpiutang_ibfk_4` FOREIGN KEY (`no_retur_piutang`) REFERENCES `returpiutang` (`no_retur_piutang`) ON DELETE CASCADE ON UPDATE CASCADE,
  CONSTRAINT `detreturpiutang_ibfk_5` FOREIGN KEY (`kode_brng`) REFERENCES `databarang` (`kode_brng`) ON DELETE CASCADE ON UPDATE CASCADE
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

/*Table structure for table `diagnosa_corona` */

DROP TABLE IF EXISTS `diagnosa_corona`;

CREATE TABLE `diagnosa_corona` (
  `no_rkm_medis` varchar(15) NOT NULL,
  `kode_icd` varchar(10) NOT NULL,
  `nama_penyakit` varchar(200) DEFAULT NULL,
  `status` enum('Primer','Sekunder') DEFAULT NULL,
  PRIMARY KEY (`no_rkm_medis`,`kode_icd`),
  CONSTRAINT `diagnosa_corona_ibfk_1` FOREIGN KEY (`no_rkm_medis`) REFERENCES `pasien_corona` (`no_rkm_medis`) ON DELETE CASCADE ON UPDATE CASCADE
) ENGINE=InnoDB DEFAULT CHARSET=latin1 ROW_FORMAT=COMPACT;

/*Table structure for table `diagnosa_gizi_pasien` */

DROP TABLE IF EXISTS `diagnosa_gizi_pasien`;

CREATE TABLE `diagnosa_gizi_pasien` (
  `no_rawat` varchar(17) NOT NULL,
  `kd_diagnosa_gz` varchar(10) NOT NULL,
  `tgl_input` date NOT NULL,
  `jam_input` time NOT NULL,
  PRIMARY KEY (`no_rawat`,`kd_diagnosa_gz`,`tgl_input`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1 ROW_FORMAT=COMPACT;

/*Table structure for table `diagnosa_pasien` */

DROP TABLE IF EXISTS `diagnosa_pasien`;

CREATE TABLE `diagnosa_pasien` (
  `no_rawat` varchar(18) NOT NULL,
  `kd_penyakit` varchar(10) NOT NULL,
  `status` enum('Ralan','Ranap') NOT NULL,
  `prioritas` tinyint(4) NOT NULL,
  PRIMARY KEY (`no_rawat`,`kd_penyakit`,`status`),
  KEY `kd_penyakit` (`kd_penyakit`),
  KEY `status` (`status`),
  KEY `prioritas` (`prioritas`),
  KEY `no_rawat` (`no_rawat`),
  CONSTRAINT `diagnosa_pasien_ibfk_1` FOREIGN KEY (`no_rawat`) REFERENCES `reg_periksa` (`no_rawat`) ON UPDATE CASCADE,
  CONSTRAINT `diagnosa_pasien_ibfk_2` FOREIGN KEY (`kd_penyakit`) REFERENCES `penyakit` (`kd_penyakit`) ON UPDATE CASCADE
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

/*Table structure for table `diet` */

DROP TABLE IF EXISTS `diet`;

CREATE TABLE `diet` (
  `kd_diet` varchar(6) NOT NULL,
  `nama_diet` varchar(50) NOT NULL,
  `kategori` enum('Umum','Asuhan Gizi') DEFAULT NULL,
  `flag` enum('0','1') DEFAULT NULL,
  PRIMARY KEY (`kd_diet`),
  KEY `nama_diet` (`nama_diet`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

/*Table structure for table `dokter` */

DROP TABLE IF EXISTS `dokter`;

CREATE TABLE `dokter` (
  `kd_dokter` varchar(20) NOT NULL,
  `nm_dokter` varchar(50) DEFAULT NULL,
  `jk` enum('L','P') DEFAULT NULL,
  `tmp_lahir` varchar(20) DEFAULT NULL,
  `tgl_lahir` date DEFAULT NULL,
  `gol_drh` enum('A','B','O','AB','-') DEFAULT NULL,
  `agama` varchar(12) DEFAULT NULL,
  `almt_tgl` varchar(60) DEFAULT NULL,
  `no_telp` varchar(13) DEFAULT NULL,
  `stts_nikah` enum('BELUM MENIKAH','MENIKAH','JANDA','DUDA') DEFAULT NULL,
  `kd_sps` char(5) DEFAULT NULL,
  `alumni` varchar(60) DEFAULT NULL,
  `no_ijn_praktek` varchar(40) DEFAULT NULL,
  `status` enum('0','1') NOT NULL,
  `url_photo` text DEFAULT NULL,
  PRIMARY KEY (`kd_dokter`),
  KEY `kd_sps` (`kd_sps`),
  KEY `nm_dokter` (`nm_dokter`),
  KEY `jk` (`jk`),
  KEY `tmp_lahir` (`tmp_lahir`),
  KEY `tgl_lahir` (`tgl_lahir`),
  KEY `gol_drh` (`gol_drh`),
  KEY `agama` (`agama`),
  KEY `almt_tgl` (`almt_tgl`),
  KEY `no_telp` (`no_telp`),
  KEY `stts_nikah` (`stts_nikah`),
  KEY `alumni` (`alumni`),
  KEY `no_ijn_praktek` (`no_ijn_praktek`),
  KEY `kd_dokter` (`kd_dokter`) USING BTREE,
  KEY `status` (`status`) USING BTREE,
  CONSTRAINT `dokter_ibfk_2` FOREIGN KEY (`kd_sps`) REFERENCES `spesialis` (`kd_sps`) ON UPDATE CASCADE,
  CONSTRAINT `dokter_ibfk_3` FOREIGN KEY (`kd_dokter`) REFERENCES `pegawai` (`nik`) ON UPDATE CASCADE
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

/*Table structure for table `dpjp_ranap` */

DROP TABLE IF EXISTS `dpjp_ranap`;

CREATE TABLE `dpjp_ranap` (
  `no_rawat` varchar(17) NOT NULL,
  `kd_dokter` varchar(20) NOT NULL,
  PRIMARY KEY (`no_rawat`,`kd_dokter`),
  KEY `dpjp_ranap_ibfk_2` (`kd_dokter`),
  CONSTRAINT `dpjp_ranap_ibfk_1` FOREIGN KEY (`no_rawat`) REFERENCES `reg_periksa` (`no_rawat`) ON UPDATE CASCADE,
  CONSTRAINT `dpjp_ranap_ibfk_2` FOREIGN KEY (`kd_dokter`) REFERENCES `dokter` (`kd_dokter`) ON DELETE CASCADE ON UPDATE CASCADE
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

/*Table structure for table `dtd_penyakit` */

DROP TABLE IF EXISTS `dtd_penyakit`;

CREATE TABLE `dtd_penyakit` (
  `kode_dtd` varchar(7) NOT NULL,
  `deskripsi_penyakit` varchar(255) DEFAULT NULL,
  `range` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`kode_dtd`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

/*Table structure for table `eklaim_covid19_data` */

DROP TABLE IF EXISTS `eklaim_covid19_data`;

CREATE TABLE `eklaim_covid19_data` (
  `no_sep` varchar(20) NOT NULL DEFAULT '-',
  `no_kartu_t` varchar(20) NOT NULL DEFAULT '-',
  `covid19_status_cd` enum('3','4','5','-') NOT NULL DEFAULT '-',
  `covid19_status_nm` varchar(50) NOT NULL DEFAULT '-',
  `co_insidense_ind` enum('0','1') NOT NULL DEFAULT '0',
  `rs_darurat_ind` enum('0','1') NOT NULL DEFAULT '0',
  `cc_ind` enum('0','1') NOT NULL DEFAULT '0',
  `top_up_rawat_gross` double NOT NULL DEFAULT 0,
  `top_up_rawat_factor` decimal(4,2) NOT NULL DEFAULT 0.00,
  `top_up_rawat` double NOT NULL DEFAULT 0,
  `top_up_jenazah` double NOT NULL DEFAULT 0,
  `tgl_input` datetime NOT NULL DEFAULT current_timestamp(),
  PRIMARY KEY (`no_sep`),
  CONSTRAINT `FK_eklaim_covid19_data_eklaim_grouping` FOREIGN KEY (`no_sep`) REFERENCES `eklaim_grouping` (`no_sep`) ON DELETE CASCADE ON UPDATE CASCADE
) ENGINE=InnoDB DEFAULT CHARSET=latin1 ROW_FORMAT=COMPACT;

/*Table structure for table `eklaim_covid19_episodes` */

DROP TABLE IF EXISTS `eklaim_covid19_episodes`;

CREATE TABLE `eklaim_covid19_episodes` (
  `no_sep` varchar(20) NOT NULL DEFAULT '-',
  `episodes_id` smallint(3) NOT NULL DEFAULT 99,
  `episodes_class_cd` varchar(3) NOT NULL DEFAULT '-',
  `episodes_class_nm` varchar(150) NOT NULL DEFAULT '-',
  `los` smallint(6) NOT NULL DEFAULT 0,
  `tariff` double NOT NULL DEFAULT 0,
  `order_no` smallint(6) NOT NULL DEFAULT 0,
  PRIMARY KEY (`no_sep`,`episodes_id`),
  CONSTRAINT `FK__eklaim_grouping` FOREIGN KEY (`no_sep`) REFERENCES `eklaim_covid19_data` (`no_sep`) ON DELETE CASCADE ON UPDATE CASCADE
) ENGINE=InnoDB DEFAULT CHARSET=latin1 ROW_FORMAT=COMPACT;

/*Table structure for table `eklaim_covid19_pemulasaraan_jenazah` */

DROP TABLE IF EXISTS `eklaim_covid19_pemulasaraan_jenazah`;

CREATE TABLE `eklaim_covid19_pemulasaraan_jenazah` (
  `no_sep` varchar(20) NOT NULL DEFAULT '-',
  `pemulasaraan` double NOT NULL DEFAULT 0,
  `kantong` double NOT NULL DEFAULT 0,
  `peti` double NOT NULL DEFAULT 0,
  `plastik` double NOT NULL DEFAULT 0,
  `desinfektan_jenazah` double NOT NULL DEFAULT 0,
  `mobil` double NOT NULL DEFAULT 0,
  `desinfektan_mobil` double NOT NULL DEFAULT 0,
  PRIMARY KEY (`no_sep`),
  CONSTRAINT `FK__eklaim_covid19_data` FOREIGN KEY (`no_sep`) REFERENCES `eklaim_covid19_data` (`no_sep`) ON DELETE CASCADE ON UPDATE CASCADE
) ENGINE=InnoDB DEFAULT CHARSET=latin1 ROW_FORMAT=COMPACT;

/*Table structure for table `eklaim_covid19_penunjang_pengurang` */

DROP TABLE IF EXISTS `eklaim_covid19_penunjang_pengurang`;

CREATE TABLE `eklaim_covid19_penunjang_pengurang` (
  `no_sep` varchar(20) NOT NULL DEFAULT '-',
  `lab_asam_laktat` double NOT NULL DEFAULT 0,
  `lab_procalcitonin` double NOT NULL DEFAULT 0,
  `lab_crp` double NOT NULL DEFAULT 0,
  `lab_kultur` double NOT NULL DEFAULT 0,
  `lab_d_dimer` double NOT NULL DEFAULT 0,
  `lab_pt` double NOT NULL DEFAULT 0,
  `lab_aptt` double NOT NULL DEFAULT 0,
  `lab_waktu_pendarahan` double NOT NULL DEFAULT 0,
  `lab_anti_hiv` double NOT NULL DEFAULT 0,
  `lab_analisa_gas` double NOT NULL DEFAULT 0,
  `lab_albumin` double NOT NULL DEFAULT 0,
  `rad_thorax_ap_pa` double NOT NULL DEFAULT 0,
  PRIMARY KEY (`no_sep`),
  CONSTRAINT `FK_eklaim_covid19_penunjang_pengurang_eklaim_covid19_data` FOREIGN KEY (`no_sep`) REFERENCES `eklaim_covid19_data` (`no_sep`) ON DELETE CASCADE ON UPDATE CASCADE
) ENGINE=InnoDB DEFAULT CHARSET=latin1 ROW_FORMAT=COMPACT;

/*Table structure for table `eklaim_file_upload` */

DROP TABLE IF EXISTS `eklaim_file_upload`;

CREATE TABLE `eklaim_file_upload` (
  `no_sep` varchar(20) DEFAULT NULL,
  `file_id` varchar(6) DEFAULT NULL,
  `file_class` varchar(20) DEFAULT NULL,
  `file_name` varchar(150) DEFAULT NULL,
  `file_type` varchar(75) DEFAULT NULL,
  `file_size` varchar(20) DEFAULT NULL,
  `kode` varchar(10) DEFAULT NULL,
  `upload_dc_bpjs` enum('0','1') NOT NULL DEFAULT '0',
  `message` varchar(150) DEFAULT NULL,
  `tgl_input` datetime NOT NULL DEFAULT current_timestamp(),
  KEY `FK_eklaim_file_upload_eklaim_new_claim` (`no_sep`) USING BTREE,
  CONSTRAINT `FK_eklaim_file_upload_eklaim_new_claim` FOREIGN KEY (`no_sep`) REFERENCES `eklaim_new_claim` (`no_sep`) ON DELETE CASCADE ON UPDATE CASCADE
) ENGINE=InnoDB DEFAULT CHARSET=latin1 ROW_FORMAT=COMPACT;

/*Table structure for table `eklaim_file_upload_temp` */

DROP TABLE IF EXISTS `eklaim_file_upload_temp`;

CREATE TABLE `eklaim_file_upload_temp` (
  `no_sep` varchar(20) DEFAULT NULL,
  `file_id` varchar(6) DEFAULT NULL,
  `file_class` varchar(20) DEFAULT NULL,
  `basecode64_file` longtext DEFAULT NULL,
  `tgl_input` datetime NOT NULL DEFAULT current_timestamp(),
  KEY `FK_eklaim_file_upload_eklaim_new_claim` (`no_sep`) USING BTREE,
  CONSTRAINT `eklaim_file_upload_temp_ibfk_1` FOREIGN KEY (`no_sep`) REFERENCES `eklaim_new_claim` (`no_sep`) ON DELETE CASCADE ON UPDATE CASCADE
) ENGINE=InnoDB DEFAULT CHARSET=latin1 ROW_FORMAT=COMPACT;

/*Table structure for table `eklaim_generate_claim` */

DROP TABLE IF EXISTS `eklaim_generate_claim`;

CREATE TABLE `eklaim_generate_claim` (
  `no_rawat` varchar(20) NOT NULL,
  `claim_number` varchar(20) NOT NULL DEFAULT '-',
  `payor_id` varchar(5) NOT NULL,
  `tgl_input` datetime NOT NULL DEFAULT current_timestamp(),
  PRIMARY KEY (`no_rawat`,`payor_id`),
  CONSTRAINT `FK_eklaim_generate_claim_reg_periksa` FOREIGN KEY (`no_rawat`) REFERENCES `reg_periksa` (`no_rawat`) ON UPDATE CASCADE
) ENGINE=InnoDB DEFAULT CHARSET=latin1 ROW_FORMAT=COMPACT;

/*Table structure for table `eklaim_grouping` */

DROP TABLE IF EXISTS `eklaim_grouping`;

CREATE TABLE `eklaim_grouping` (
  `no_sep` varchar(20) NOT NULL DEFAULT '-',
  `grouping_stage` enum('1','2') NOT NULL DEFAULT '1',
  `cbg_code` varchar(20) NOT NULL DEFAULT '-',
  `cbg_desc` varchar(150) NOT NULL DEFAULT '-',
  `cbg_tarif` double NOT NULL DEFAULT 0,
  `sub_acute_code` varchar(20) NOT NULL DEFAULT '-',
  `sub_acute_desc` varchar(150) NOT NULL DEFAULT '-',
  `sub_acute_tarif` double NOT NULL DEFAULT 0,
  `chronic_code` varchar(20) NOT NULL,
  `chronic_desc` varchar(150) NOT NULL,
  `chronic_tarif` double NOT NULL DEFAULT 0,
  `kelas` varchar(20) NOT NULL,
  `add_payment_amt` double NOT NULL DEFAULT 0,
  `inacbg_version` varchar(50) NOT NULL,
  `tgl_input` datetime NOT NULL DEFAULT current_timestamp(),
  PRIMARY KEY (`no_sep`),
  CONSTRAINT `FK_eklaim_grouping_eklaim_new_claim` FOREIGN KEY (`no_sep`) REFERENCES `eklaim_new_claim` (`no_sep`) ON DELETE CASCADE ON UPDATE CASCADE
) ENGINE=InnoDB DEFAULT CHARSET=latin1 ROW_FORMAT=COMPACT;

/*Table structure for table `eklaim_grouping_cmg_opt` */

DROP TABLE IF EXISTS `eklaim_grouping_cmg_opt`;

CREATE TABLE `eklaim_grouping_cmg_opt` (
  `no_sep` varchar(20) NOT NULL DEFAULT '-',
  `code` varchar(20) NOT NULL DEFAULT '-',
  `desc` varchar(150) NOT NULL DEFAULT '-',
  `type` varchar(150) NOT NULL DEFAULT '-',
  `tgl_input` datetime NOT NULL DEFAULT current_timestamp(),
  PRIMARY KEY (`no_sep`,`type`,`code`),
  CONSTRAINT `eklaim_grouping_cmg_opt_ibfk_1` FOREIGN KEY (`no_sep`) REFERENCES `eklaim_grouping` (`no_sep`) ON DELETE CASCADE ON UPDATE CASCADE
) ENGINE=InnoDB DEFAULT CHARSET=latin1 ROW_FORMAT=COMPACT;

/*Table structure for table `eklaim_grouping_spc_cmg` */

DROP TABLE IF EXISTS `eklaim_grouping_spc_cmg`;

CREATE TABLE `eklaim_grouping_spc_cmg` (
  `no_sep` varchar(20) NOT NULL DEFAULT '-',
  `code` varchar(20) NOT NULL DEFAULT '-',
  `desc` varchar(150) NOT NULL DEFAULT '-',
  `type` varchar(150) NOT NULL DEFAULT '-',
  `tarif` double NOT NULL DEFAULT 0,
  `tgl_input` datetime NOT NULL DEFAULT current_timestamp(),
  PRIMARY KEY (`no_sep`,`type`,`code`),
  CONSTRAINT `eklaim_grouping_spc_cmg_ibfk_1` FOREIGN KEY (`no_sep`) REFERENCES `eklaim_grouping` (`no_sep`) ON DELETE CASCADE ON UPDATE CASCADE
) ENGINE=InnoDB DEFAULT CHARSET=latin1 ROW_FORMAT=COMPACT;

/*Table structure for table `eklaim_grouping_tarif_alt` */

DROP TABLE IF EXISTS `eklaim_grouping_tarif_alt`;

CREATE TABLE `eklaim_grouping_tarif_alt` (
  `no_sep` varchar(20) NOT NULL DEFAULT '-',
  `kelas` varchar(20) NOT NULL DEFAULT '-',
  `tarif_inacbg` double NOT NULL DEFAULT 0,
  `tarif_sub_acute` double NOT NULL DEFAULT 0,
  `tarif_chronic` double NOT NULL DEFAULT 0,
  `tarif_sp` double NOT NULL DEFAULT 0,
  `tarif_sr` double NOT NULL DEFAULT 0,
  `tarif_si` double NOT NULL DEFAULT 0,
  `tarif_sd` double NOT NULL DEFAULT 0,
  `tgl_input` timestamp NOT NULL DEFAULT current_timestamp(),
  PRIMARY KEY (`no_sep`,`kelas`),
  CONSTRAINT `FK_eklaim_grouping_tarif_alt_eklaim_new_claim` FOREIGN KEY (`no_sep`) REFERENCES `eklaim_grouping` (`no_sep`) ON DELETE CASCADE ON UPDATE CASCADE
) ENGINE=InnoDB DEFAULT CHARSET=latin1 ROW_FORMAT=COMPACT;

/*Table structure for table `eklaim_new_claim` */

DROP TABLE IF EXISTS `eklaim_new_claim`;

CREATE TABLE `eklaim_new_claim` (
  `no_rawat` varchar(20) NOT NULL,
  `no_sep` varchar(20) NOT NULL,
  `tglsep` date DEFAULT NULL,
  `jnspelayanan` enum('1','2') DEFAULT '1',
  `no_kartu` varchar(20) DEFAULT NULL,
  `no_rm` varchar(10) DEFAULT NULL,
  `nm_pasien` varchar(75) DEFAULT NULL,
  `tgl_lahir` datetime DEFAULT NULL,
  `gender` enum('1','2') DEFAULT '1',
  `patient_id` varchar(10) NOT NULL DEFAULT '-',
  `admission_id` varchar(10) NOT NULL DEFAULT '-',
  `hospital_admission_id` varchar(15) NOT NULL DEFAULT '-',
  `tgl_input` datetime NOT NULL DEFAULT current_timestamp(),
  `klaim_final` enum('Belum','Final') NOT NULL DEFAULT 'Belum',
  `tgl_update` datetime DEFAULT NULL ON UPDATE current_timestamp(),
  PRIMARY KEY (`no_sep`,`no_rawat`),
  UNIQUE KEY `no_sep` (`no_sep`) USING BTREE,
  KEY `FK_eklaim_new_claim_reg_periksa` (`no_rawat`) USING BTREE,
  CONSTRAINT `FK_eklaim_new_claim_reg_periksa` FOREIGN KEY (`no_rawat`) REFERENCES `reg_periksa` (`no_rawat`) ON UPDATE CASCADE
) ENGINE=InnoDB DEFAULT CHARSET=latin1 ROW_FORMAT=COMPACT;

/*Table structure for table `eklaim_online_status` */

DROP TABLE IF EXISTS `eklaim_online_status`;

CREATE TABLE `eklaim_online_status` (
  `no_sep` varchar(20) NOT NULL,
  `kemkes_dc_status` varchar(20) NOT NULL DEFAULT '-',
  `bpjs_dc_status` varchar(20) NOT NULL DEFAULT '-',
  `cob_dc_status` varchar(20) NOT NULL DEFAULT '-',
  `message` varchar(150) NOT NULL DEFAULT '-',
  `error_no` varchar(20) NOT NULL DEFAULT '-',
  `curl_error_no` varchar(20) NOT NULL DEFAULT '-',
  `curl_error_message` varchar(150) NOT NULL DEFAULT '-',
  `curl_error_constant` varchar(150) NOT NULL DEFAULT '-',
  `tgl_input` datetime NOT NULL DEFAULT current_timestamp(),
  PRIMARY KEY (`no_sep`),
  CONSTRAINT `FK_eklaim_online_status_eklaim_grouping` FOREIGN KEY (`no_sep`) REFERENCES `eklaim_grouping` (`no_sep`) ON DELETE CASCADE ON UPDATE CASCADE
) ENGINE=InnoDB DEFAULT CHARSET=latin1 ROW_FORMAT=COMPACT;

/*Table structure for table `eklaim_set_claim` */

DROP TABLE IF EXISTS `eklaim_set_claim`;

CREATE TABLE `eklaim_set_claim` (
  `no_sep` varchar(20) NOT NULL DEFAULT '-',
  `no_kartu` varchar(20) DEFAULT NULL,
  `tgl_masuk` datetime DEFAULT NULL,
  `tgl_pulang` datetime DEFAULT NULL,
  `jenis_rawat` enum('1','2') NOT NULL DEFAULT '1',
  `kelas_rawat` enum('3','2','1') NOT NULL DEFAULT '3',
  `adl_sub_acute` smallint(6) DEFAULT NULL,
  `adl_chronic` smallint(6) DEFAULT NULL,
  `icu_indikator` enum('0','1') NOT NULL DEFAULT '0',
  `icu_los` tinyint(4) DEFAULT NULL,
  `ventilator_hour` smallint(6) DEFAULT NULL,
  `upgrade_class_ind` enum('0','1') NOT NULL DEFAULT '0',
  `upgrade_class_class` enum('kelas_1','kelas_2','vip','vvip','-','kelas_3','') NOT NULL DEFAULT '-',
  `upgrade_class_los` smallint(6) DEFAULT NULL,
  `add_payment_pct` smallint(6) DEFAULT NULL,
  `birth_weight` smallint(6) DEFAULT NULL,
  `discharge_status` enum('1','2','3','4','5') DEFAULT '1',
  `diagnosa` varchar(100) DEFAULT NULL,
  `procedure` varchar(100) DEFAULT NULL,
  `tarif_prosedur_non_bedah` double NOT NULL DEFAULT 0,
  `tarif_prosedur_bedah` double NOT NULL DEFAULT 0,
  `tarif_konsultasi` double NOT NULL DEFAULT 0,
  `tarif_tenaga_ahli` double NOT NULL DEFAULT 0,
  `tarif_keperawatan` double NOT NULL DEFAULT 0,
  `tarif_penunjang` double NOT NULL DEFAULT 0,
  `tarif_radiologi` double NOT NULL DEFAULT 0,
  `tarif_laboratorium` double NOT NULL DEFAULT 0,
  `tarif_pelayanan_darah` double NOT NULL DEFAULT 0,
  `tarif_rehabilitasi` double NOT NULL DEFAULT 0,
  `tarif_kamar` double NOT NULL DEFAULT 0,
  `tarif_rawat_intensif` double NOT NULL DEFAULT 0,
  `tarif_obat` double NOT NULL DEFAULT 0,
  `tarif_obat_kronis` double NOT NULL DEFAULT 0,
  `tarif_obat_kemoterapi` double NOT NULL DEFAULT 0,
  `tarif_alkes` double NOT NULL DEFAULT 0,
  `tarif_bmhp` double NOT NULL DEFAULT 0,
  `tarif_sewa_alat` double NOT NULL DEFAULT 0,
  `real_tarif` double NOT NULL DEFAULT 0,
  `pemulasaraan_jenazah` enum('0','1','') NOT NULL DEFAULT '0',
  `kantong_jenazah` enum('0','1','') NOT NULL DEFAULT '0',
  `peti_jenazah` enum('0','1','') NOT NULL DEFAULT '0',
  `plastik_erat` enum('0','1','') NOT NULL DEFAULT '0',
  `desinfektan_jenazah` enum('0','1','') NOT NULL DEFAULT '0',
  `mobil_jenazah` enum('0','1','') NOT NULL DEFAULT '0',
  `desinfektan_mobil_jenazah` enum('0','1','') NOT NULL DEFAULT '0',
  `covid19_status_cd` enum('1','2','3','4','5','-','') NOT NULL DEFAULT '-',
  `nomor_kartu_t` varchar(20) NOT NULL DEFAULT '-',
  `episodes` varchar(20) NOT NULL DEFAULT '-',
  `covid19_cc_ind` enum('0','1','') NOT NULL DEFAULT '0',
  `covid19_rs_darurat_ind` enum('0','1','') NOT NULL DEFAULT '0',
  `covid19_co_insidense_ind` enum('0','1','') NOT NULL DEFAULT '0',
  `lab_asam_laktat` enum('0','1','') NOT NULL DEFAULT '0',
  `lab_procalcitonin` enum('0','1','') NOT NULL DEFAULT '0',
  `lab_crp` enum('0','1','') NOT NULL DEFAULT '0',
  `lab_kultur` enum('0','1','') NOT NULL DEFAULT '0',
  `lab_d_dimer` enum('0','1','') NOT NULL DEFAULT '0',
  `lab_pt` enum('0','1','') NOT NULL DEFAULT '0',
  `lab_aptt` enum('0','1','') NOT NULL DEFAULT '0',
  `lab_waktu_pendarahan` enum('0','1','') NOT NULL DEFAULT '0',
  `lab_anti_hiv` enum('0','1','') NOT NULL DEFAULT '0',
  `lab_analisa_gas` enum('0','1','') NOT NULL DEFAULT '0',
  `lab_albumin` enum('0','1','') NOT NULL DEFAULT '0',
  `rad_thorax_ap_pa` enum('0','1','') NOT NULL DEFAULT '0',
  `terapi_konvalesen` double NOT NULL DEFAULT 0,
  `akses_naat` enum('A','B','C','#') NOT NULL DEFAULT '#',
  `isoman_ind` enum('0','1') NOT NULL DEFAULT '0',
  `bayi_lahir_status_cd` enum('0','1','2') NOT NULL DEFAULT '0',
  `tarif_poli_eks` double NOT NULL DEFAULT 0,
  `nama_dokter` varchar(200) NOT NULL DEFAULT '-',
  `kode_tarif` varchar(5) NOT NULL DEFAULT 'BP',
  `payor_id` varchar(5) NOT NULL DEFAULT '3',
  `payor_cd` varchar(50) NOT NULL DEFAULT 'JKN',
  `cob_cd` varchar(20) NOT NULL DEFAULT '#',
  `coder_nik` varchar(20) NOT NULL DEFAULT '-',
  `tgl_input` datetime NOT NULL DEFAULT current_timestamp(),
  PRIMARY KEY (`no_sep`),
  CONSTRAINT `FK_eklaim_set_claim_eklaim_new_claim` FOREIGN KEY (`no_sep`) REFERENCES `eklaim_new_claim` (`no_sep`) ON DELETE CASCADE ON UPDATE CASCADE
) ENGINE=InnoDB DEFAULT CHARSET=latin1 ROW_FORMAT=COMPACT;

/*Table structure for table `fix_pasien_mati` */

DROP TABLE IF EXISTS `fix_pasien_mati`;

CREATE TABLE `fix_pasien_mati` (
  `no_rkm_medis` varchar(15) NOT NULL,
  `tgl` date DEFAULT NULL,
  `jam` time DEFAULT NULL,
  PRIMARY KEY (`no_rkm_medis`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

/*Table structure for table `fix_status_kamar` */

DROP TABLE IF EXISTS `fix_status_kamar`;

CREATE TABLE `fix_status_kamar` (
  `tgl` datetime NOT NULL DEFAULT '0000-00-00 00:00:00' ON UPDATE current_timestamp(),
  `kd_kamar` varchar(15) DEFAULT NULL,
  `stts1` varchar(20) DEFAULT NULL,
  `stts2` varchar(20) DEFAULT NULL,
  `usr` varchar(50) DEFAULT NULL,
  `alasan` varchar(150) DEFAULT NULL,
  PRIMARY KEY (`tgl`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

/*Table structure for table `gambar` */

DROP TABLE IF EXISTS `gambar`;

CREATE TABLE `gambar` (
  `inde` int(11) NOT NULL,
  `bpjs` longblob NOT NULL,
  `nyeri` longblob NOT NULL,
  PRIMARY KEY (`inde`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

/*Table structure for table `gambar_radiologi` */

DROP TABLE IF EXISTS `gambar_radiologi`;

CREATE TABLE `gambar_radiologi` (
  `no_rawat` varchar(18) NOT NULL,
  `tgl_periksa` date NOT NULL,
  `jam` time NOT NULL,
  `lokasi_gambar` varchar(500) NOT NULL,
  PRIMARY KEY (`no_rawat`,`tgl_periksa`,`jam`,`lokasi_gambar`),
  CONSTRAINT `gambar_radiologi_ibfk_1` FOREIGN KEY (`no_rawat`) REFERENCES `reg_periksa` (`no_rawat`) ON UPDATE CASCADE
) ENGINE=InnoDB DEFAULT CHARSET=latin1 ROW_FORMAT=COMPACT;

/*Table structure for table `gizi_buruk` */

DROP TABLE IF EXISTS `gizi_buruk`;

CREATE TABLE `gizi_buruk` (
  `no_rawat` varchar(17) NOT NULL,
  `bb_awal` varchar(10) DEFAULT NULL,
  `bb_akhir` varchar(10) DEFAULT NULL,
  `pb_tb` varchar(10) DEFAULT NULL,
  `bb_u` varchar(255) DEFAULT NULL,
  `bb_pb` varchar(255) DEFAULT NULL,
  `pb_u` varchar(255) DEFAULT NULL,
  `penghitungan_zat_gizi` varchar(255) DEFAULT NULL,
  `diagnosa_dr_gizi` varchar(255) DEFAULT NULL,
  `pemberian_nutrisi` varchar(255) DEFAULT NULL,
  `data_albumin` varchar(255) DEFAULT NULL,
  `data_hb` varchar(255) DEFAULT NULL,
  `data_leukosit` varchar(255) DEFAULT NULL,
  `data_plt` varchar(255) DEFAULT NULL,
  `asal_rujukan` varchar(50) DEFAULT NULL,
  `tgl_input` date DEFAULT NULL,
  PRIMARY KEY (`no_rawat`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1 ROW_FORMAT=COMPACT;

/*Table structure for table `golongan_barang` */

DROP TABLE IF EXISTS `golongan_barang`;

CREATE TABLE `golongan_barang` (
  `kode` char(4) NOT NULL,
  `nama` varchar(30) DEFAULT NULL,
  PRIMARY KEY (`kode`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1 ROW_FORMAT=COMPACT;

/*Table structure for table `gudangbarang` */

DROP TABLE IF EXISTS `gudangbarang`;

CREATE TABLE `gudangbarang` (
  `kode_brng` varchar(15) NOT NULL,
  `kd_bangsal` char(5) NOT NULL DEFAULT '',
  `stok` double NOT NULL,
  PRIMARY KEY (`kd_bangsal`,`kode_brng`),
  KEY `kode_brng` (`kode_brng`),
  KEY `stok` (`stok`),
  KEY `kd_bangsal` (`kd_bangsal`) USING BTREE,
  CONSTRAINT `gudangbarang_ibfk_1` FOREIGN KEY (`kd_bangsal`) REFERENCES `bangsal` (`kd_bangsal`) ON DELETE CASCADE ON UPDATE CASCADE,
  CONSTRAINT `gudangbarang_ibfk_2` FOREIGN KEY (`kode_brng`) REFERENCES `databarang` (`kode_brng`) ON DELETE CASCADE ON UPDATE CASCADE
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

/*Table structure for table `hak_akses` */

DROP TABLE IF EXISTS `hak_akses`;

CREATE TABLE `hak_akses` (
  `id_user` varchar(255) DEFAULT NULL,
  `form` varchar(255) DEFAULT NULL,
  `action` enum('Buka','Simpan','Ganti','Hapus') DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1 ROW_FORMAT=COMPACT;

/*Table structure for table `hak_akses_unit` */

DROP TABLE IF EXISTS `hak_akses_unit`;

CREATE TABLE `hak_akses_unit` (
  `nip` varchar(20) DEFAULT NULL,
  `kode_unit` char(50) DEFAULT NULL,
  `kd_baris` int(11) NOT NULL AUTO_INCREMENT,
  PRIMARY KEY (`kd_baris`)
) ENGINE=InnoDB AUTO_INCREMENT=84 DEFAULT CHARSET=latin1;

/*Table structure for table `hari_libur` */

DROP TABLE IF EXISTS `hari_libur`;

CREATE TABLE `hari_libur` (
  `tgl_libur` date NOT NULL DEFAULT '0000-00-00',
  `keterangan` varchar(200) DEFAULT NULL,
  PRIMARY KEY (`tgl_libur`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

/*Table structure for table `harian_kurangi_bulanan` */

DROP TABLE IF EXISTS `harian_kurangi_bulanan`;

CREATE TABLE `harian_kurangi_bulanan` (
  `harian` int(11) NOT NULL,
  `bulanan` int(11) NOT NULL,
  KEY `harian` (`harian`),
  KEY `bulanan` (`bulanan`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

/*Table structure for table `hasil_radiologi` */

DROP TABLE IF EXISTS `hasil_radiologi`;

CREATE TABLE `hasil_radiologi` (
  `no_rawat` varchar(18) NOT NULL,
  `tgl_periksa` date NOT NULL,
  `jam` time NOT NULL,
  `hasil` text NOT NULL,
  `diag_klinis_radiologi` varchar(255) DEFAULT NULL,
  `kd_jenis_prw` varchar(15) NOT NULL,
  PRIMARY KEY (`no_rawat`,`tgl_periksa`,`jam`,`kd_jenis_prw`),
  KEY `no_rawat` (`no_rawat`),
  CONSTRAINT `hasil_radiologi_ibfk_1` FOREIGN KEY (`no_rawat`) REFERENCES `reg_periksa` (`no_rawat`) ON DELETE CASCADE ON UPDATE CASCADE
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

/*Table structure for table `hemodialisa` */

DROP TABLE IF EXISTS `hemodialisa`;

CREATE TABLE `hemodialisa` (
  `tanggal` datetime NOT NULL,
  `no_rawat` varchar(18) NOT NULL,
  `kd_penyakit` varchar(10) NOT NULL,
  `lama` int(11) NOT NULL,
  `dialist` varchar(30) NOT NULL,
  `penarikan` double NOT NULL,
  `akses` varchar(30) NOT NULL,
  `transfusi` double NOT NULL,
  `ureum` varchar(10) NOT NULL,
  `hb` varchar(10) NOT NULL,
  `hsbag` varchar(10) NOT NULL,
  `creatinin` varchar(10) NOT NULL,
  `gds` varchar(10) NOT NULL,
  `ctbt` varchar(10) NOT NULL,
  `lain` varchar(200) NOT NULL,
  `kd_dokter` varchar(20) NOT NULL,
  PRIMARY KEY (`tanggal`,`no_rawat`),
  KEY `kd_penyakit` (`kd_penyakit`),
  KEY `kd_dokter` (`kd_dokter`),
  KEY `no_rawat` (`no_rawat`),
  CONSTRAINT `hemodialisa_ibfk_1` FOREIGN KEY (`no_rawat`) REFERENCES `reg_periksa` (`no_rawat`) ON UPDATE CASCADE,
  CONSTRAINT `hemodialisa_ibfk_2` FOREIGN KEY (`kd_penyakit`) REFERENCES `penyakit` (`kd_penyakit`) ON UPDATE CASCADE,
  CONSTRAINT `hemodialisa_ibfk_3` FOREIGN KEY (`kd_dokter`) REFERENCES `dokter` (`kd_dokter`) ON DELETE CASCADE ON UPDATE CASCADE
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

/*Table structure for table `history_update` */

DROP TABLE IF EXISTS `history_update`;

CREATE TABLE `history_update` (
  `versi_update` varchar(100) DEFAULT NULL,
  `tgl_update` date DEFAULT NULL,
  `keterangan` longtext DEFAULT NULL,
  `kode` int(255) NOT NULL AUTO_INCREMENT,
  `jam_update` time DEFAULT NULL,
  PRIMARY KEY (`kode`)
) ENGINE=InnoDB AUTO_INCREMENT=32 DEFAULT CHARSET=latin1 ROW_FORMAT=COMPACT;

/*Table structure for table `history_user` */

DROP TABLE IF EXISTS `history_user`;

CREATE TABLE `history_user` (
  `tanggal` datetime DEFAULT NULL ON UPDATE current_timestamp(),
  `no_rawat` varchar(255) DEFAULT NULL,
  `id_user` varchar(255) DEFAULT NULL,
  `halaman` varchar(255) DEFAULT NULL,
  `keterangan` varchar(255) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1 ROW_FORMAT=COMPACT;

/*Table structure for table `historykamars` */

DROP TABLE IF EXISTS `historykamars`;

CREATE TABLE `historykamars` (
  `tgl` datetime NOT NULL,
  `kd_kamar` varchar(50) DEFAULT NULL,
  `stts1` varchar(10) DEFAULT NULL,
  `stts2` varchar(10) DEFAULT NULL,
  `usr` varchar(20) DEFAULT NULL,
  `alasan` text DEFAULT NULL,
  PRIMARY KEY (`tgl`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1 ROW_FORMAT=COMPACT;

/*Table structure for table `icd9` */

DROP TABLE IF EXISTS `icd9`;

CREATE TABLE `icd9` (
  `kode` varchar(8) NOT NULL,
  `deskripsi_panjang` varchar(250) DEFAULT NULL,
  `deskripsi_pendek` varchar(40) DEFAULT NULL,
  PRIMARY KEY (`kode`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

/*Table structure for table `inacbg_coder_nik` */

DROP TABLE IF EXISTS `inacbg_coder_nik`;

CREATE TABLE `inacbg_coder_nik` (
  `nik` varchar(20) NOT NULL,
  `no_ik` varchar(30) DEFAULT NULL,
  `status` enum('0','1') DEFAULT NULL,
  PRIMARY KEY (`nik`),
  CONSTRAINT `inacbg_coder_nik_ibfk_1` FOREIGN KEY (`nik`) REFERENCES `pegawai` (`nik`) ON DELETE CASCADE ON UPDATE CASCADE
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

/*Table structure for table `inacbg_data_terkirim` */

DROP TABLE IF EXISTS `inacbg_data_terkirim`;

CREATE TABLE `inacbg_data_terkirim` (
  `no_sep` varchar(40) NOT NULL,
  `nik` varchar(30) DEFAULT NULL,
  PRIMARY KEY (`no_sep`),
  CONSTRAINT `inacbg_data_terkirim_ibfk_1` FOREIGN KEY (`no_sep`) REFERENCES `bridging_sep` (`no_sep`) ON DELETE CASCADE ON UPDATE CASCADE
) ENGINE=InnoDB DEFAULT CHARSET=latin1 ROW_FORMAT=COMPACT;

/*Table structure for table `inacbg_data_terkirim2` */

DROP TABLE IF EXISTS `inacbg_data_terkirim2`;

CREATE TABLE `inacbg_data_terkirim2` (
  `no_sep` varchar(40) NOT NULL,
  `nik` varchar(30) DEFAULT NULL,
  PRIMARY KEY (`no_sep`),
  CONSTRAINT `inacbg_data_terkirim2_ibfk_1` FOREIGN KEY (`no_sep`) REFERENCES `inacbg_klaim_baru2` (`no_sep`) ON DELETE CASCADE ON UPDATE CASCADE
) ENGINE=InnoDB DEFAULT CHARSET=latin1 ROW_FORMAT=COMPACT;

/*Table structure for table `inacbg_grouping_stage1` */

DROP TABLE IF EXISTS `inacbg_grouping_stage1`;

CREATE TABLE `inacbg_grouping_stage1` (
  `no_sep` varchar(40) NOT NULL,
  `code_cbg` varchar(10) DEFAULT NULL,
  `deskripsi` varchar(200) DEFAULT NULL,
  `tarif` double DEFAULT NULL,
  PRIMARY KEY (`no_sep`),
  CONSTRAINT `inacbg_grouping_stage1_ibfk_1` FOREIGN KEY (`no_sep`) REFERENCES `bridging_sep` (`no_sep`) ON DELETE CASCADE ON UPDATE CASCADE
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

/*Table structure for table `inacbg_grouping_stage12` */

DROP TABLE IF EXISTS `inacbg_grouping_stage12`;

CREATE TABLE `inacbg_grouping_stage12` (
  `no_sep` varchar(40) NOT NULL,
  `code_cbg` varchar(10) DEFAULT NULL,
  `deskripsi` varchar(200) DEFAULT NULL,
  `tarif` double DEFAULT NULL,
  PRIMARY KEY (`no_sep`),
  CONSTRAINT `inacbg_grouping_stage12_ibfk_1` FOREIGN KEY (`no_sep`) REFERENCES `inacbg_klaim_baru2` (`no_sep`) ON DELETE CASCADE ON UPDATE CASCADE
) ENGINE=InnoDB DEFAULT CHARSET=latin1 ROW_FORMAT=COMPACT;

/*Table structure for table `inacbg_klaim_baru` */

DROP TABLE IF EXISTS `inacbg_klaim_baru`;

CREATE TABLE `inacbg_klaim_baru` (
  `no_sep` varchar(40) NOT NULL DEFAULT '',
  `patient_id` varchar(30) DEFAULT NULL,
  `admission_id` varchar(30) DEFAULT NULL,
  `hospital_admission_id` varchar(30) DEFAULT NULL,
  PRIMARY KEY (`no_sep`),
  CONSTRAINT `inacbg_klaim_baru_ibfk_1` FOREIGN KEY (`no_sep`) REFERENCES `bridging_sep` (`no_sep`) ON DELETE CASCADE ON UPDATE CASCADE
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

/*Table structure for table `inacbg_klaim_baru2` */

DROP TABLE IF EXISTS `inacbg_klaim_baru2`;

CREATE TABLE `inacbg_klaim_baru2` (
  `no_rawat` varchar(18) NOT NULL,
  `no_sep` varchar(40) NOT NULL DEFAULT '',
  `patient_id` varchar(30) DEFAULT NULL,
  `admission_id` varchar(30) DEFAULT NULL,
  `hospital_admission_id` varchar(30) DEFAULT NULL,
  PRIMARY KEY (`no_rawat`),
  UNIQUE KEY `no_sep` (`no_sep`) USING BTREE,
  CONSTRAINT `inacbg_klaim_baru2_ibfk_1` FOREIGN KEY (`no_rawat`) REFERENCES `reg_periksa` (`no_rawat`) ON DELETE CASCADE ON UPDATE CASCADE
) ENGINE=InnoDB DEFAULT CHARSET=latin1 ROW_FORMAT=COMPACT;

/*Table structure for table `inacbg_noklaim_corona` */

DROP TABLE IF EXISTS `inacbg_noklaim_corona`;

CREATE TABLE `inacbg_noklaim_corona` (
  `no_rawat` varchar(17) NOT NULL,
  `no_klaim` varchar(40) DEFAULT NULL,
  PRIMARY KEY (`no_rawat`),
  UNIQUE KEY `no_klaim` (`no_klaim`) USING BTREE,
  CONSTRAINT `inacbg_noklaim_corona_ibfk_1` FOREIGN KEY (`no_rawat`) REFERENCES `reg_periksa` (`no_rawat`) ON DELETE CASCADE ON UPDATE CASCADE
) ENGINE=InnoDB DEFAULT CHARSET=latin1 ROW_FORMAT=COMPACT;

/*Table structure for table `indekref` */

DROP TABLE IF EXISTS `indekref`;

CREATE TABLE `indekref` (
  `kdindex` char(4) NOT NULL,
  `n` double NOT NULL,
  `ttl` double NOT NULL,
  KEY `kdindex` (`kdindex`),
  KEY `n` (`n`),
  KEY `ttl` (`ttl`),
  CONSTRAINT `indekref_ibfk_1` FOREIGN KEY (`kdindex`) REFERENCES `departemen` (`dep_id`) ON UPDATE CASCADE
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

/*Table structure for table `indexins` */

DROP TABLE IF EXISTS `indexins`;

CREATE TABLE `indexins` (
  `dep_id` char(4) NOT NULL,
  `persen` double NOT NULL,
  PRIMARY KEY (`dep_id`),
  KEY `persen` (`persen`),
  CONSTRAINT `indexins_ibfk_1` FOREIGN KEY (`dep_id`) REFERENCES `departemen` (`dep_id`) ON UPDATE CASCADE
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

/*Table structure for table `indextotal` */

DROP TABLE IF EXISTS `indextotal`;

CREATE TABLE `indextotal` (
  `kdindex` char(4) NOT NULL,
  `ttl` double NOT NULL,
  KEY `kdindex` (`kdindex`),
  KEY `ttl` (`ttl`),
  CONSTRAINT `indextotal_ibfk_1` FOREIGN KEY (`kdindex`) REFERENCES `departemen` (`dep_id`) ON UPDATE CASCADE
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

/*Table structure for table `industrifarmasi` */

DROP TABLE IF EXISTS `industrifarmasi`;

CREATE TABLE `industrifarmasi` (
  `kode_industri` char(5) NOT NULL DEFAULT '',
  `nama_industri` varchar(50) DEFAULT NULL,
  `alamat` varchar(50) DEFAULT NULL,
  `kota` varchar(20) DEFAULT NULL,
  `no_telp` varchar(20) DEFAULT NULL,
  PRIMARY KEY (`kode_industri`),
  KEY `nama_industri` (`nama_industri`) USING BTREE,
  KEY `alamat` (`alamat`) USING BTREE,
  KEY `kota` (`kota`) USING BTREE,
  KEY `no_telp` (`no_telp`) USING BTREE
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

/*Table structure for table `inhealth_jenpel_ruang_rawat` */

DROP TABLE IF EXISTS `inhealth_jenpel_ruang_rawat`;

CREATE TABLE `inhealth_jenpel_ruang_rawat` (
  `kd_kamar` varchar(15) NOT NULL,
  `kode_jenpel_ruang_rawat` varchar(20) NOT NULL,
  `nama_jenpel_ruang_rawat` varchar(100) DEFAULT NULL,
  `tarif` double NOT NULL,
  PRIMARY KEY (`kode_jenpel_ruang_rawat`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1 ROW_FORMAT=COMPACT;

/*Table structure for table `inventaris` */

DROP TABLE IF EXISTS `inventaris`;

CREATE TABLE `inventaris` (
  `no_inventaris` varchar(30) NOT NULL,
  `kode_barang` varchar(20) DEFAULT NULL,
  `asal_barang` enum('Beli','Bantuan','Hibah','-') DEFAULT NULL,
  `tgl_pengadaan` date DEFAULT NULL,
  `harga` double DEFAULT NULL,
  `status_barang` enum('Ada','Rusak','Hilang','Perbaikan','Dipinjam','-') DEFAULT NULL,
  `id_ruang` char(5) DEFAULT NULL,
  `no_rak` char(3) DEFAULT NULL,
  `no_box` char(3) DEFAULT NULL,
  PRIMARY KEY (`no_inventaris`),
  KEY `kode_barang` (`kode_barang`),
  KEY `kd_ruang` (`id_ruang`),
  KEY `asal_barang` (`asal_barang`),
  KEY `tgl_pengadaan` (`tgl_pengadaan`),
  KEY `harga` (`harga`),
  KEY `status_barang` (`status_barang`),
  KEY `no_rak` (`no_rak`),
  KEY `no_box` (`no_box`),
  CONSTRAINT `inventaris_ibfk_1` FOREIGN KEY (`kode_barang`) REFERENCES `inventaris_barang` (`kode_barang`) ON UPDATE CASCADE,
  CONSTRAINT `inventaris_ibfk_2` FOREIGN KEY (`id_ruang`) REFERENCES `inventaris_ruang` (`id_ruang`) ON UPDATE CASCADE
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

/*Table structure for table `inventaris_barang` */

DROP TABLE IF EXISTS `inventaris_barang`;

CREATE TABLE `inventaris_barang` (
  `kode_barang` varchar(20) NOT NULL,
  `nama_barang` varchar(60) DEFAULT NULL,
  `jml_barang` int(11) DEFAULT NULL,
  `kode_produsen` varchar(10) DEFAULT NULL,
  `id_merk` varchar(10) DEFAULT NULL,
  `thn_produksi` year(4) DEFAULT NULL,
  `isbn` varchar(20) DEFAULT NULL,
  `id_kategori` char(10) DEFAULT NULL,
  `id_jenis` char(10) DEFAULT NULL,
  PRIMARY KEY (`kode_barang`),
  KEY `kode_produsen` (`kode_produsen`),
  KEY `id_merk` (`id_merk`),
  KEY `id_kategori` (`id_kategori`),
  KEY `id_jenis` (`id_jenis`),
  KEY `nama_barang` (`nama_barang`),
  KEY `jml_barang` (`jml_barang`),
  KEY `thn_produksi` (`thn_produksi`),
  KEY `isbn` (`isbn`),
  CONSTRAINT `inventaris_barang_ibfk_5` FOREIGN KEY (`kode_produsen`) REFERENCES `inventaris_produsen` (`kode_produsen`) ON UPDATE CASCADE,
  CONSTRAINT `inventaris_barang_ibfk_6` FOREIGN KEY (`id_merk`) REFERENCES `inventaris_merk` (`id_merk`) ON UPDATE CASCADE,
  CONSTRAINT `inventaris_barang_ibfk_7` FOREIGN KEY (`id_kategori`) REFERENCES `inventaris_kategori` (`id_kategori`) ON UPDATE CASCADE,
  CONSTRAINT `inventaris_barang_ibfk_8` FOREIGN KEY (`id_jenis`) REFERENCES `inventaris_jenis` (`id_jenis`) ON UPDATE CASCADE
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

/*Table structure for table `inventaris_jenis` */

DROP TABLE IF EXISTS `inventaris_jenis`;

CREATE TABLE `inventaris_jenis` (
  `id_jenis` char(10) NOT NULL,
  `nama_jenis` varchar(40) DEFAULT NULL,
  PRIMARY KEY (`id_jenis`),
  KEY `nama_jenis` (`nama_jenis`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

/*Table structure for table `inventaris_kategori` */

DROP TABLE IF EXISTS `inventaris_kategori`;

CREATE TABLE `inventaris_kategori` (
  `id_kategori` char(10) NOT NULL,
  `nama_kategori` varchar(40) DEFAULT NULL,
  PRIMARY KEY (`id_kategori`),
  KEY `nama_kategori` (`nama_kategori`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

/*Table structure for table `inventaris_merk` */

DROP TABLE IF EXISTS `inventaris_merk`;

CREATE TABLE `inventaris_merk` (
  `id_merk` varchar(10) NOT NULL,
  `nama_merk` varchar(40) NOT NULL,
  PRIMARY KEY (`id_merk`),
  KEY `nama_merk` (`nama_merk`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

/*Table structure for table `inventaris_peminjaman` */

DROP TABLE IF EXISTS `inventaris_peminjaman`;

CREATE TABLE `inventaris_peminjaman` (
  `peminjam` varchar(50) NOT NULL DEFAULT '',
  `tlp` varchar(13) NOT NULL,
  `no_inventaris` varchar(30) NOT NULL DEFAULT '',
  `tgl_pinjam` date NOT NULL DEFAULT '0000-00-00',
  `tgl_kembali` date DEFAULT NULL,
  `nip` varchar(20) NOT NULL DEFAULT '',
  `status_pinjam` enum('Masih Dipinjam','Sudah Kembali') DEFAULT NULL,
  PRIMARY KEY (`peminjam`,`no_inventaris`,`tgl_pinjam`,`nip`),
  KEY `no_inventaris` (`no_inventaris`),
  KEY `nip` (`nip`),
  KEY `tgl_kembali` (`tgl_kembali`),
  KEY `status_pinjam` (`status_pinjam`),
  CONSTRAINT `inventaris_peminjaman_ibfk_1` FOREIGN KEY (`no_inventaris`) REFERENCES `inventaris` (`no_inventaris`) ON UPDATE CASCADE,
  CONSTRAINT `inventaris_peminjaman_ibfk_2` FOREIGN KEY (`nip`) REFERENCES `petugas` (`nip`) ON DELETE CASCADE ON UPDATE CASCADE
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

/*Table structure for table `inventaris_produsen` */

DROP TABLE IF EXISTS `inventaris_produsen`;

CREATE TABLE `inventaris_produsen` (
  `kode_produsen` varchar(10) NOT NULL,
  `nama_produsen` varchar(40) DEFAULT NULL,
  `alamat_produsen` varchar(70) DEFAULT NULL,
  `no_telp` varchar(13) DEFAULT NULL,
  `email` varchar(25) DEFAULT NULL,
  `website_produsen` varchar(30) DEFAULT NULL,
  PRIMARY KEY (`kode_produsen`),
  KEY `nama_produsen` (`nama_produsen`),
  KEY `alamat_produsen` (`alamat_produsen`),
  KEY `no_telp` (`no_telp`),
  KEY `email` (`email`),
  KEY `website_produsen` (`website_produsen`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

/*Table structure for table `inventaris_ruang` */

DROP TABLE IF EXISTS `inventaris_ruang`;

CREATE TABLE `inventaris_ruang` (
  `id_ruang` varchar(5) NOT NULL,
  `nama_ruang` varchar(40) NOT NULL,
  PRIMARY KEY (`id_ruang`),
  KEY `nama_ruang` (`nama_ruang`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

/*Table structure for table `ipsrsbarang` */

DROP TABLE IF EXISTS `ipsrsbarang`;

CREATE TABLE `ipsrsbarang` (
  `kode_brng` varchar(15) NOT NULL,
  `nama_brng` varchar(80) NOT NULL,
  `kode_sat` char(4) NOT NULL,
  `jenis` char(5) DEFAULT NULL,
  `stok` double NOT NULL,
  `harga` double NOT NULL,
  PRIMARY KEY (`kode_brng`),
  KEY `kode_sat` (`kode_sat`),
  KEY `nama_brng` (`nama_brng`),
  KEY `jenis` (`jenis`(1)),
  KEY `stok` (`stok`),
  KEY `harga` (`harga`),
  KEY `jenis_2` (`jenis`),
  CONSTRAINT `ipsrsbarang_ibfk_1` FOREIGN KEY (`kode_sat`) REFERENCES `kodesatuan` (`kode_sat`) ON UPDATE CASCADE,
  CONSTRAINT `ipsrsbarang_ibfk_2` FOREIGN KEY (`jenis`) REFERENCES `ipsrsjenisbarang` (`kd_jenis`) ON UPDATE CASCADE
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

/*Table structure for table `ipsrsdetailbeli` */

DROP TABLE IF EXISTS `ipsrsdetailbeli`;

CREATE TABLE `ipsrsdetailbeli` (
  `no_faktur` varchar(15) NOT NULL,
  `kode_brng` varchar(15) NOT NULL,
  `kode_sat` char(4) NOT NULL,
  `jumlah` double NOT NULL,
  `harga` double NOT NULL,
  `subtotal` double NOT NULL,
  `dis` double NOT NULL,
  `besardis` double NOT NULL,
  `total` double NOT NULL,
  PRIMARY KEY (`no_faktur`,`kode_brng`),
  KEY `kode_sat` (`kode_sat`),
  KEY `kode_brng` (`kode_brng`),
  KEY `jumlah` (`jumlah`),
  KEY `harga` (`harga`),
  KEY `subtotal` (`subtotal`),
  KEY `dis` (`dis`),
  KEY `besardis` (`besardis`),
  KEY `total` (`total`),
  CONSTRAINT `ipsrsdetailbeli_ibfk_1` FOREIGN KEY (`no_faktur`) REFERENCES `ipsrspembelian` (`no_faktur`) ON DELETE CASCADE ON UPDATE CASCADE,
  CONSTRAINT `ipsrsdetailbeli_ibfk_4` FOREIGN KEY (`kode_brng`) REFERENCES `ipsrsbarang` (`kode_brng`) ON UPDATE CASCADE,
  CONSTRAINT `ipsrsdetailbeli_ibfk_5` FOREIGN KEY (`kode_sat`) REFERENCES `kodesatuan` (`kode_sat`) ON UPDATE CASCADE
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

/*Table structure for table `ipsrsdetailpengeluaran` */

DROP TABLE IF EXISTS `ipsrsdetailpengeluaran`;

CREATE TABLE `ipsrsdetailpengeluaran` (
  `no_keluar` varchar(15) NOT NULL,
  `kode_brng` varchar(15) NOT NULL,
  `kode_sat` char(4) NOT NULL,
  `jumlah` double NOT NULL,
  `harga` double NOT NULL,
  `total` double NOT NULL,
  PRIMARY KEY (`no_keluar`,`kode_brng`),
  KEY `kode_sat` (`kode_sat`),
  KEY `kode_brng` (`kode_brng`),
  KEY `jumlah` (`jumlah`),
  KEY `harga` (`harga`),
  KEY `total` (`total`),
  CONSTRAINT `ipsrsdetailpengeluaran_ibfk_1` FOREIGN KEY (`no_keluar`) REFERENCES `ipsrspengeluaran` (`no_keluar`) ON DELETE CASCADE ON UPDATE CASCADE,
  CONSTRAINT `ipsrsdetailpengeluaran_ibfk_3` FOREIGN KEY (`kode_sat`) REFERENCES `kodesatuan` (`kode_sat`) ON UPDATE CASCADE,
  CONSTRAINT `ipsrsdetailpengeluaran_ibfk_4` FOREIGN KEY (`kode_brng`) REFERENCES `ipsrsbarang` (`kode_brng`) ON UPDATE CASCADE
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

/*Table structure for table `ipsrsjenisbarang` */

DROP TABLE IF EXISTS `ipsrsjenisbarang`;

CREATE TABLE `ipsrsjenisbarang` (
  `kd_jenis` char(5) NOT NULL,
  `nm_jenis` varchar(50) DEFAULT NULL,
  PRIMARY KEY (`kd_jenis`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

/*Table structure for table `ipsrspembelian` */

DROP TABLE IF EXISTS `ipsrspembelian`;

CREATE TABLE `ipsrspembelian` (
  `no_faktur` varchar(15) NOT NULL,
  `kode_suplier` char(5) NOT NULL,
  `nip` varchar(20) NOT NULL,
  `tgl_beli` date NOT NULL,
  `subtotal` double NOT NULL,
  `potongan` double NOT NULL,
  `total` double NOT NULL,
  `ppn` double DEFAULT NULL,
  `tagihan` double DEFAULT NULL,
  `kd_rek` varchar(15) DEFAULT NULL,
  PRIMARY KEY (`no_faktur`),
  KEY `kode_suplier` (`kode_suplier`),
  KEY `nip` (`nip`),
  KEY `tgl_beli` (`tgl_beli`),
  KEY `subtotal` (`subtotal`),
  KEY `potongan` (`potongan`),
  KEY `total` (`total`),
  KEY `ipsrspembelian_ibfk_5` (`kd_rek`),
  CONSTRAINT `ipsrspembelian_ibfk_4` FOREIGN KEY (`nip`) REFERENCES `petugas` (`nip`) ON DELETE CASCADE ON UPDATE CASCADE,
  CONSTRAINT `ipsrspembelian_ibfk_5` FOREIGN KEY (`kd_rek`) REFERENCES `akun_bayar` (`kd_rek`) ON DELETE CASCADE ON UPDATE CASCADE,
  CONSTRAINT `ipsrspembelian_ibfk_6` FOREIGN KEY (`kode_suplier`) REFERENCES `ipsrssuplier` (`kode_suplier`) ON DELETE CASCADE ON UPDATE CASCADE
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

/*Table structure for table `ipsrspengeluaran` */

DROP TABLE IF EXISTS `ipsrspengeluaran`;

CREATE TABLE `ipsrspengeluaran` (
  `no_keluar` varchar(15) NOT NULL,
  `tanggal` date NOT NULL,
  `nip` varchar(20) NOT NULL,
  `keterangan` varchar(100) NOT NULL,
  PRIMARY KEY (`no_keluar`),
  KEY `nip` (`nip`),
  KEY `tanggal` (`tanggal`),
  KEY `keterangan` (`keterangan`),
  CONSTRAINT `ipsrspengeluaran_ibfk_1` FOREIGN KEY (`nip`) REFERENCES `petugas` (`nip`) ON DELETE CASCADE ON UPDATE CASCADE
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

/*Table structure for table `ipsrssuplier` */

DROP TABLE IF EXISTS `ipsrssuplier`;

CREATE TABLE `ipsrssuplier` (
  `kode_suplier` char(5) NOT NULL,
  `nama_suplier` varchar(50) DEFAULT NULL,
  `alamat` varchar(50) DEFAULT NULL,
  `kota` varchar(20) DEFAULT NULL,
  `no_telp` varchar(13) DEFAULT NULL,
  PRIMARY KEY (`kode_suplier`),
  KEY `nama_suplier` (`nama_suplier`),
  KEY `alamat` (`alamat`),
  KEY `kota` (`kota`),
  KEY `no_telp` (`no_telp`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

/*Table structure for table `jabatan` */

DROP TABLE IF EXISTS `jabatan`;

CREATE TABLE `jabatan` (
  `kd_jbtn` char(4) NOT NULL DEFAULT '',
  `nm_jbtn` varchar(25) DEFAULT NULL,
  PRIMARY KEY (`kd_jbtn`),
  KEY `nm_jbtn` (`nm_jbtn`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

/*Table structure for table `jadwal` */

DROP TABLE IF EXISTS `jadwal`;

CREATE TABLE `jadwal` (
  `kd_dokter` varchar(20) NOT NULL,
  `hari_kerja` enum('SENIN','SELASA','RABU','KAMIS','JUMAT','SABTU','AKHAD') NOT NULL DEFAULT 'SENIN',
  `jam_mulai` time NOT NULL DEFAULT '00:00:00',
  `jam_selesai` time NOT NULL,
  `kd_poli` char(5) NOT NULL,
  PRIMARY KEY (`kd_dokter`,`hari_kerja`,`jam_mulai`,`jam_selesai`,`kd_poli`),
  KEY `kd_dokter` (`kd_dokter`),
  KEY `kd_poli` (`kd_poli`),
  KEY `jam_mulai` (`jam_mulai`),
  KEY `jam_selesai` (`jam_selesai`),
  CONSTRAINT `jadwal_ibfk_1` FOREIGN KEY (`kd_dokter`) REFERENCES `dokter` (`kd_dokter`) ON DELETE CASCADE ON UPDATE CASCADE,
  CONSTRAINT `jadwal_ibfk_2` FOREIGN KEY (`kd_poli`) REFERENCES `poliklinik` (`kd_poli`) ON DELETE CASCADE ON UPDATE CASCADE
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

/*Table structure for table `jadwal_pegawai` */

DROP TABLE IF EXISTS `jadwal_pegawai`;

CREATE TABLE `jadwal_pegawai` (
  `id` int(11) NOT NULL,
  `tahun` year(4) NOT NULL,
  `bulan` enum('01','02','03','04','05','06','07','08','09','10','11','12') NOT NULL,
  `h1` enum('Pagi','Pagi2','Pagi3','Pagi4','Pagi5','Pagi6','Pagi7','Pagi8','Pagi9','Pagi10','Siang','Siang2','Siang3','Siang4','Siang5','Siang6','Siang7','Siang8','Siang9','Siang10','Malam','Malam2','Malam3','Malam4','Malam5','Malam6','Malam7','Malam8','Malam9','Malam10','Midle Pagi1','Midle Pagi2','Midle Pagi3','Midle Pagi4','Midle Pagi5','Midle Pagi6','Midle Pagi7','Midle Pagi8','Midle Pagi9','Midle Pagi10','Midle Siang1','Midle Siang2','Midle Siang3','Midle Siang4','Midle Siang5','Midle Siang6','Midle Siang7','Midle Siang8','Midle Siang9','Midle Siang10','Midle Malam1','Midle Malam2','Midle Malam3','Midle Malam4','Midle Malam5','Midle Malam6','Midle Malam7','Midle Malam8','Midle Malam9','Midle Malam10','') NOT NULL,
  `h2` enum('Pagi','Pagi2','Pagi3','Pagi4','Pagi5','Pagi6','Pagi7','Pagi8','Pagi9','Pagi10','Siang','Siang2','Siang3','Siang4','Siang5','Siang6','Siang7','Siang8','Siang9','Siang10','Malam','Malam2','Malam3','Malam4','Malam5','Malam6','Malam7','Malam8','Malam9','Malam10','Midle Pagi1','Midle Pagi2','Midle Pagi3','Midle Pagi4','Midle Pagi5','Midle Pagi6','Midle Pagi7','Midle Pagi8','Midle Pagi9','Midle Pagi10','Midle Siang1','Midle Siang2','Midle Siang3','Midle Siang4','Midle Siang5','Midle Siang6','Midle Siang7','Midle Siang8','Midle Siang9','Midle Siang10','Midle Malam1','Midle Malam2','Midle Malam3','Midle Malam4','Midle Malam5','Midle Malam6','Midle Malam7','Midle Malam8','Midle Malam9','Midle Malam10','') NOT NULL,
  `h3` enum('Pagi','Pagi2','Pagi3','Pagi4','Pagi5','Pagi6','Pagi7','Pagi8','Pagi9','Pagi10','Siang','Siang2','Siang3','Siang4','Siang5','Siang6','Siang7','Siang8','Siang9','Siang10','Malam','Malam2','Malam3','Malam4','Malam5','Malam6','Malam7','Malam8','Malam9','Malam10','Midle Pagi1','Midle Pagi2','Midle Pagi3','Midle Pagi4','Midle Pagi5','Midle Pagi6','Midle Pagi7','Midle Pagi8','Midle Pagi9','Midle Pagi10','Midle Siang1','Midle Siang2','Midle Siang3','Midle Siang4','Midle Siang5','Midle Siang6','Midle Siang7','Midle Siang8','Midle Siang9','Midle Siang10','Midle Malam1','Midle Malam2','Midle Malam3','Midle Malam4','Midle Malam5','Midle Malam6','Midle Malam7','Midle Malam8','Midle Malam9','Midle Malam10','') NOT NULL,
  `h4` enum('Pagi','Pagi2','Pagi3','Pagi4','Pagi5','Pagi6','Pagi7','Pagi8','Pagi9','Pagi10','Siang','Siang2','Siang3','Siang4','Siang5','Siang6','Siang7','Siang8','Siang9','Siang10','Malam','Malam2','Malam3','Malam4','Malam5','Malam6','Malam7','Malam8','Malam9','Malam10','Midle Pagi1','Midle Pagi2','Midle Pagi3','Midle Pagi4','Midle Pagi5','Midle Pagi6','Midle Pagi7','Midle Pagi8','Midle Pagi9','Midle Pagi10','Midle Siang1','Midle Siang2','Midle Siang3','Midle Siang4','Midle Siang5','Midle Siang6','Midle Siang7','Midle Siang8','Midle Siang9','Midle Siang10','Midle Malam1','Midle Malam2','Midle Malam3','Midle Malam4','Midle Malam5','Midle Malam6','Midle Malam7','Midle Malam8','Midle Malam9','Midle Malam10','') NOT NULL,
  `h5` enum('Pagi','Pagi2','Pagi3','Pagi4','Pagi5','Pagi6','Pagi7','Pagi8','Pagi9','Pagi10','Siang','Siang2','Siang3','Siang4','Siang5','Siang6','Siang7','Siang8','Siang9','Siang10','Malam','Malam2','Malam3','Malam4','Malam5','Malam6','Malam7','Malam8','Malam9','Malam10','Midle Pagi1','Midle Pagi2','Midle Pagi3','Midle Pagi4','Midle Pagi5','Midle Pagi6','Midle Pagi7','Midle Pagi8','Midle Pagi9','Midle Pagi10','Midle Siang1','Midle Siang2','Midle Siang3','Midle Siang4','Midle Siang5','Midle Siang6','Midle Siang7','Midle Siang8','Midle Siang9','Midle Siang10','Midle Malam1','Midle Malam2','Midle Malam3','Midle Malam4','Midle Malam5','Midle Malam6','Midle Malam7','Midle Malam8','Midle Malam9','Midle Malam10','') NOT NULL,
  `h6` enum('Pagi','Pagi2','Pagi3','Pagi4','Pagi5','Pagi6','Pagi7','Pagi8','Pagi9','Pagi10','Siang','Siang2','Siang3','Siang4','Siang5','Siang6','Siang7','Siang8','Siang9','Siang10','Malam','Malam2','Malam3','Malam4','Malam5','Malam6','Malam7','Malam8','Malam9','Malam10','Midle Pagi1','Midle Pagi2','Midle Pagi3','Midle Pagi4','Midle Pagi5','Midle Pagi6','Midle Pagi7','Midle Pagi8','Midle Pagi9','Midle Pagi10','Midle Siang1','Midle Siang2','Midle Siang3','Midle Siang4','Midle Siang5','Midle Siang6','Midle Siang7','Midle Siang8','Midle Siang9','Midle Siang10','Midle Malam1','Midle Malam2','Midle Malam3','Midle Malam4','Midle Malam5','Midle Malam6','Midle Malam7','Midle Malam8','Midle Malam9','Midle Malam10','') NOT NULL,
  `h7` enum('Pagi','Pagi2','Pagi3','Pagi4','Pagi5','Pagi6','Pagi7','Pagi8','Pagi9','Pagi10','Siang','Siang2','Siang3','Siang4','Siang5','Siang6','Siang7','Siang8','Siang9','Siang10','Malam','Malam2','Malam3','Malam4','Malam5','Malam6','Malam7','Malam8','Malam9','Malam10','Midle Pagi1','Midle Pagi2','Midle Pagi3','Midle Pagi4','Midle Pagi5','Midle Pagi6','Midle Pagi7','Midle Pagi8','Midle Pagi9','Midle Pagi10','Midle Siang1','Midle Siang2','Midle Siang3','Midle Siang4','Midle Siang5','Midle Siang6','Midle Siang7','Midle Siang8','Midle Siang9','Midle Siang10','Midle Malam1','Midle Malam2','Midle Malam3','Midle Malam4','Midle Malam5','Midle Malam6','Midle Malam7','Midle Malam8','Midle Malam9','Midle Malam10','') NOT NULL,
  `h8` enum('Pagi','Pagi2','Pagi3','Pagi4','Pagi5','Pagi6','Pagi7','Pagi8','Pagi9','Pagi10','Siang','Siang2','Siang3','Siang4','Siang5','Siang6','Siang7','Siang8','Siang9','Siang10','Malam','Malam2','Malam3','Malam4','Malam5','Malam6','Malam7','Malam8','Malam9','Malam10','Midle Pagi1','Midle Pagi2','Midle Pagi3','Midle Pagi4','Midle Pagi5','Midle Pagi6','Midle Pagi7','Midle Pagi8','Midle Pagi9','Midle Pagi10','Midle Siang1','Midle Siang2','Midle Siang3','Midle Siang4','Midle Siang5','Midle Siang6','Midle Siang7','Midle Siang8','Midle Siang9','Midle Siang10','Midle Malam1','Midle Malam2','Midle Malam3','Midle Malam4','Midle Malam5','Midle Malam6','Midle Malam7','Midle Malam8','Midle Malam9','Midle Malam10','') NOT NULL,
  `h9` enum('Pagi','Pagi2','Pagi3','Pagi4','Pagi5','Pagi6','Pagi7','Pagi8','Pagi9','Pagi10','Siang','Siang2','Siang3','Siang4','Siang5','Siang6','Siang7','Siang8','Siang9','Siang10','Malam','Malam2','Malam3','Malam4','Malam5','Malam6','Malam7','Malam8','Malam9','Malam10','Midle Pagi1','Midle Pagi2','Midle Pagi3','Midle Pagi4','Midle Pagi5','Midle Pagi6','Midle Pagi7','Midle Pagi8','Midle Pagi9','Midle Pagi10','Midle Siang1','Midle Siang2','Midle Siang3','Midle Siang4','Midle Siang5','Midle Siang6','Midle Siang7','Midle Siang8','Midle Siang9','Midle Siang10','Midle Malam1','Midle Malam2','Midle Malam3','Midle Malam4','Midle Malam5','Midle Malam6','Midle Malam7','Midle Malam8','Midle Malam9','Midle Malam10','') NOT NULL,
  `h10` enum('Pagi','Pagi2','Pagi3','Pagi4','Pagi5','Pagi6','Pagi7','Pagi8','Pagi9','Pagi10','Siang','Siang2','Siang3','Siang4','Siang5','Siang6','Siang7','Siang8','Siang9','Siang10','Malam','Malam2','Malam3','Malam4','Malam5','Malam6','Malam7','Malam8','Malam9','Malam10','Midle Pagi1','Midle Pagi2','Midle Pagi3','Midle Pagi4','Midle Pagi5','Midle Pagi6','Midle Pagi7','Midle Pagi8','Midle Pagi9','Midle Pagi10','Midle Siang1','Midle Siang2','Midle Siang3','Midle Siang4','Midle Siang5','Midle Siang6','Midle Siang7','Midle Siang8','Midle Siang9','Midle Siang10','Midle Malam1','Midle Malam2','Midle Malam3','Midle Malam4','Midle Malam5','Midle Malam6','Midle Malam7','Midle Malam8','Midle Malam9','Midle Malam10','') NOT NULL,
  `h11` enum('Pagi','Pagi2','Pagi3','Pagi4','Pagi5','Pagi6','Pagi7','Pagi8','Pagi9','Pagi10','Siang','Siang2','Siang3','Siang4','Siang5','Siang6','Siang7','Siang8','Siang9','Siang10','Malam','Malam2','Malam3','Malam4','Malam5','Malam6','Malam7','Malam8','Malam9','Malam10','Midle Pagi1','Midle Pagi2','Midle Pagi3','Midle Pagi4','Midle Pagi5','Midle Pagi6','Midle Pagi7','Midle Pagi8','Midle Pagi9','Midle Pagi10','Midle Siang1','Midle Siang2','Midle Siang3','Midle Siang4','Midle Siang5','Midle Siang6','Midle Siang7','Midle Siang8','Midle Siang9','Midle Siang10','Midle Malam1','Midle Malam2','Midle Malam3','Midle Malam4','Midle Malam5','Midle Malam6','Midle Malam7','Midle Malam8','Midle Malam9','Midle Malam10','') NOT NULL,
  `h12` enum('Pagi','Pagi2','Pagi3','Pagi4','Pagi5','Pagi6','Pagi7','Pagi8','Pagi9','Pagi10','Siang','Siang2','Siang3','Siang4','Siang5','Siang6','Siang7','Siang8','Siang9','Siang10','Malam','Malam2','Malam3','Malam4','Malam5','Malam6','Malam7','Malam8','Malam9','Malam10','Midle Pagi1','Midle Pagi2','Midle Pagi3','Midle Pagi4','Midle Pagi5','Midle Pagi6','Midle Pagi7','Midle Pagi8','Midle Pagi9','Midle Pagi10','Midle Siang1','Midle Siang2','Midle Siang3','Midle Siang4','Midle Siang5','Midle Siang6','Midle Siang7','Midle Siang8','Midle Siang9','Midle Siang10','Midle Malam1','Midle Malam2','Midle Malam3','Midle Malam4','Midle Malam5','Midle Malam6','Midle Malam7','Midle Malam8','Midle Malam9','Midle Malam10','') NOT NULL,
  `h13` enum('Pagi','Pagi2','Pagi3','Pagi4','Pagi5','Pagi6','Pagi7','Pagi8','Pagi9','Pagi10','Siang','Siang2','Siang3','Siang4','Siang5','Siang6','Siang7','Siang8','Siang9','Siang10','Malam','Malam2','Malam3','Malam4','Malam5','Malam6','Malam7','Malam8','Malam9','Malam10','Midle Pagi1','Midle Pagi2','Midle Pagi3','Midle Pagi4','Midle Pagi5','Midle Pagi6','Midle Pagi7','Midle Pagi8','Midle Pagi9','Midle Pagi10','Midle Siang1','Midle Siang2','Midle Siang3','Midle Siang4','Midle Siang5','Midle Siang6','Midle Siang7','Midle Siang8','Midle Siang9','Midle Siang10','Midle Malam1','Midle Malam2','Midle Malam3','Midle Malam4','Midle Malam5','Midle Malam6','Midle Malam7','Midle Malam8','Midle Malam9','Midle Malam10','') NOT NULL,
  `h14` enum('Pagi','Pagi2','Pagi3','Pagi4','Pagi5','Pagi6','Pagi7','Pagi8','Pagi9','Pagi10','Siang','Siang2','Siang3','Siang4','Siang5','Siang6','Siang7','Siang8','Siang9','Siang10','Malam','Malam2','Malam3','Malam4','Malam5','Malam6','Malam7','Malam8','Malam9','Malam10','Midle Pagi1','Midle Pagi2','Midle Pagi3','Midle Pagi4','Midle Pagi5','Midle Pagi6','Midle Pagi7','Midle Pagi8','Midle Pagi9','Midle Pagi10','Midle Siang1','Midle Siang2','Midle Siang3','Midle Siang4','Midle Siang5','Midle Siang6','Midle Siang7','Midle Siang8','Midle Siang9','Midle Siang10','Midle Malam1','Midle Malam2','Midle Malam3','Midle Malam4','Midle Malam5','Midle Malam6','Midle Malam7','Midle Malam8','Midle Malam9','Midle Malam10','') NOT NULL,
  `h15` enum('Pagi','Pagi2','Pagi3','Pagi4','Pagi5','Pagi6','Pagi7','Pagi8','Pagi9','Pagi10','Siang','Siang2','Siang3','Siang4','Siang5','Siang6','Siang7','Siang8','Siang9','Siang10','Malam','Malam2','Malam3','Malam4','Malam5','Malam6','Malam7','Malam8','Malam9','Malam10','Midle Pagi1','Midle Pagi2','Midle Pagi3','Midle Pagi4','Midle Pagi5','Midle Pagi6','Midle Pagi7','Midle Pagi8','Midle Pagi9','Midle Pagi10','Midle Siang1','Midle Siang2','Midle Siang3','Midle Siang4','Midle Siang5','Midle Siang6','Midle Siang7','Midle Siang8','Midle Siang9','Midle Siang10','Midle Malam1','Midle Malam2','Midle Malam3','Midle Malam4','Midle Malam5','Midle Malam6','Midle Malam7','Midle Malam8','Midle Malam9','Midle Malam10','') NOT NULL,
  `h16` enum('Pagi','Pagi2','Pagi3','Pagi4','Pagi5','Pagi6','Pagi7','Pagi8','Pagi9','Pagi10','Siang','Siang2','Siang3','Siang4','Siang5','Siang6','Siang7','Siang8','Siang9','Siang10','Malam','Malam2','Malam3','Malam4','Malam5','Malam6','Malam7','Malam8','Malam9','Malam10','Midle Pagi1','Midle Pagi2','Midle Pagi3','Midle Pagi4','Midle Pagi5','Midle Pagi6','Midle Pagi7','Midle Pagi8','Midle Pagi9','Midle Pagi10','Midle Siang1','Midle Siang2','Midle Siang3','Midle Siang4','Midle Siang5','Midle Siang6','Midle Siang7','Midle Siang8','Midle Siang9','Midle Siang10','Midle Malam1','Midle Malam2','Midle Malam3','Midle Malam4','Midle Malam5','Midle Malam6','Midle Malam7','Midle Malam8','Midle Malam9','Midle Malam10','') NOT NULL,
  `h17` enum('Pagi','Pagi2','Pagi3','Pagi4','Pagi5','Pagi6','Pagi7','Pagi8','Pagi9','Pagi10','Siang','Siang2','Siang3','Siang4','Siang5','Siang6','Siang7','Siang8','Siang9','Siang10','Malam','Malam2','Malam3','Malam4','Malam5','Malam6','Malam7','Malam8','Malam9','Malam10','Midle Pagi1','Midle Pagi2','Midle Pagi3','Midle Pagi4','Midle Pagi5','Midle Pagi6','Midle Pagi7','Midle Pagi8','Midle Pagi9','Midle Pagi10','Midle Siang1','Midle Siang2','Midle Siang3','Midle Siang4','Midle Siang5','Midle Siang6','Midle Siang7','Midle Siang8','Midle Siang9','Midle Siang10','Midle Malam1','Midle Malam2','Midle Malam3','Midle Malam4','Midle Malam5','Midle Malam6','Midle Malam7','Midle Malam8','Midle Malam9','Midle Malam10','') NOT NULL,
  `h18` enum('Pagi','Pagi2','Pagi3','Pagi4','Pagi5','Pagi6','Pagi7','Pagi8','Pagi9','Pagi10','Siang','Siang2','Siang3','Siang4','Siang5','Siang6','Siang7','Siang8','Siang9','Siang10','Malam','Malam2','Malam3','Malam4','Malam5','Malam6','Malam7','Malam8','Malam9','Malam10','Midle Pagi1','Midle Pagi2','Midle Pagi3','Midle Pagi4','Midle Pagi5','Midle Pagi6','Midle Pagi7','Midle Pagi8','Midle Pagi9','Midle Pagi10','Midle Siang1','Midle Siang2','Midle Siang3','Midle Siang4','Midle Siang5','Midle Siang6','Midle Siang7','Midle Siang8','Midle Siang9','Midle Siang10','Midle Malam1','Midle Malam2','Midle Malam3','Midle Malam4','Midle Malam5','Midle Malam6','Midle Malam7','Midle Malam8','Midle Malam9','Midle Malam10','') NOT NULL,
  `h19` enum('Pagi','Pagi2','Pagi3','Pagi4','Pagi5','Pagi6','Pagi7','Pagi8','Pagi9','Pagi10','Siang','Siang2','Siang3','Siang4','Siang5','Siang6','Siang7','Siang8','Siang9','Siang10','Malam','Malam2','Malam3','Malam4','Malam5','Malam6','Malam7','Malam8','Malam9','Malam10','Midle Pagi1','Midle Pagi2','Midle Pagi3','Midle Pagi4','Midle Pagi5','Midle Pagi6','Midle Pagi7','Midle Pagi8','Midle Pagi9','Midle Pagi10','Midle Siang1','Midle Siang2','Midle Siang3','Midle Siang4','Midle Siang5','Midle Siang6','Midle Siang7','Midle Siang8','Midle Siang9','Midle Siang10','Midle Malam1','Midle Malam2','Midle Malam3','Midle Malam4','Midle Malam5','Midle Malam6','Midle Malam7','Midle Malam8','Midle Malam9','Midle Malam10','') NOT NULL,
  `h20` enum('Pagi','Pagi2','Pagi3','Pagi4','Pagi5','Pagi6','Pagi7','Pagi8','Pagi9','Pagi10','Siang','Siang2','Siang3','Siang4','Siang5','Siang6','Siang7','Siang8','Siang9','Siang10','Malam','Malam2','Malam3','Malam4','Malam5','Malam6','Malam7','Malam8','Malam9','Malam10','Midle Pagi1','Midle Pagi2','Midle Pagi3','Midle Pagi4','Midle Pagi5','Midle Pagi6','Midle Pagi7','Midle Pagi8','Midle Pagi9','Midle Pagi10','Midle Siang1','Midle Siang2','Midle Siang3','Midle Siang4','Midle Siang5','Midle Siang6','Midle Siang7','Midle Siang8','Midle Siang9','Midle Siang10','Midle Malam1','Midle Malam2','Midle Malam3','Midle Malam4','Midle Malam5','Midle Malam6','Midle Malam7','Midle Malam8','Midle Malam9','Midle Malam10','') NOT NULL,
  `h21` enum('Pagi','Pagi2','Pagi3','Pagi4','Pagi5','Pagi6','Pagi7','Pagi8','Pagi9','Pagi10','Siang','Siang2','Siang3','Siang4','Siang5','Siang6','Siang7','Siang8','Siang9','Siang10','Malam','Malam2','Malam3','Malam4','Malam5','Malam6','Malam7','Malam8','Malam9','Malam10','Midle Pagi1','Midle Pagi2','Midle Pagi3','Midle Pagi4','Midle Pagi5','Midle Pagi6','Midle Pagi7','Midle Pagi8','Midle Pagi9','Midle Pagi10','Midle Siang1','Midle Siang2','Midle Siang3','Midle Siang4','Midle Siang5','Midle Siang6','Midle Siang7','Midle Siang8','Midle Siang9','Midle Siang10','Midle Malam1','Midle Malam2','Midle Malam3','Midle Malam4','Midle Malam5','Midle Malam6','Midle Malam7','Midle Malam8','Midle Malam9','Midle Malam10','') NOT NULL,
  `h22` enum('Pagi','Pagi2','Pagi3','Pagi4','Pagi5','Pagi6','Pagi7','Pagi8','Pagi9','Pagi10','Siang','Siang2','Siang3','Siang4','Siang5','Siang6','Siang7','Siang8','Siang9','Siang10','Malam','Malam2','Malam3','Malam4','Malam5','Malam6','Malam7','Malam8','Malam9','Malam10','Midle Pagi1','Midle Pagi2','Midle Pagi3','Midle Pagi4','Midle Pagi5','Midle Pagi6','Midle Pagi7','Midle Pagi8','Midle Pagi9','Midle Pagi10','Midle Siang1','Midle Siang2','Midle Siang3','Midle Siang4','Midle Siang5','Midle Siang6','Midle Siang7','Midle Siang8','Midle Siang9','Midle Siang10','Midle Malam1','Midle Malam2','Midle Malam3','Midle Malam4','Midle Malam5','Midle Malam6','Midle Malam7','Midle Malam8','Midle Malam9','Midle Malam10','') NOT NULL,
  `h23` enum('Pagi','Pagi2','Pagi3','Pagi4','Pagi5','Pagi6','Pagi7','Pagi8','Pagi9','Pagi10','Siang','Siang2','Siang3','Siang4','Siang5','Siang6','Siang7','Siang8','Siang9','Siang10','Malam','Malam2','Malam3','Malam4','Malam5','Malam6','Malam7','Malam8','Malam9','Malam10','Midle Pagi1','Midle Pagi2','Midle Pagi3','Midle Pagi4','Midle Pagi5','Midle Pagi6','Midle Pagi7','Midle Pagi8','Midle Pagi9','Midle Pagi10','Midle Siang1','Midle Siang2','Midle Siang3','Midle Siang4','Midle Siang5','Midle Siang6','Midle Siang7','Midle Siang8','Midle Siang9','Midle Siang10','Midle Malam1','Midle Malam2','Midle Malam3','Midle Malam4','Midle Malam5','Midle Malam6','Midle Malam7','Midle Malam8','Midle Malam9','Midle Malam10','') NOT NULL,
  `h24` enum('Pagi','Pagi2','Pagi3','Pagi4','Pagi5','Pagi6','Pagi7','Pagi8','Pagi9','Pagi10','Siang','Siang2','Siang3','Siang4','Siang5','Siang6','Siang7','Siang8','Siang9','Siang10','Malam','Malam2','Malam3','Malam4','Malam5','Malam6','Malam7','Malam8','Malam9','Malam10','Midle Pagi1','Midle Pagi2','Midle Pagi3','Midle Pagi4','Midle Pagi5','Midle Pagi6','Midle Pagi7','Midle Pagi8','Midle Pagi9','Midle Pagi10','Midle Siang1','Midle Siang2','Midle Siang3','Midle Siang4','Midle Siang5','Midle Siang6','Midle Siang7','Midle Siang8','Midle Siang9','Midle Siang10','Midle Malam1','Midle Malam2','Midle Malam3','Midle Malam4','Midle Malam5','Midle Malam6','Midle Malam7','Midle Malam8','Midle Malam9','Midle Malam10','') NOT NULL,
  `h25` enum('Pagi','Pagi2','Pagi3','Pagi4','Pagi5','Pagi6','Pagi7','Pagi8','Pagi9','Pagi10','Siang','Siang2','Siang3','Siang4','Siang5','Siang6','Siang7','Siang8','Siang9','Siang10','Malam','Malam2','Malam3','Malam4','Malam5','Malam6','Malam7','Malam8','Malam9','Malam10','Midle Pagi1','Midle Pagi2','Midle Pagi3','Midle Pagi4','Midle Pagi5','Midle Pagi6','Midle Pagi7','Midle Pagi8','Midle Pagi9','Midle Pagi10','Midle Siang1','Midle Siang2','Midle Siang3','Midle Siang4','Midle Siang5','Midle Siang6','Midle Siang7','Midle Siang8','Midle Siang9','Midle Siang10','Midle Malam1','Midle Malam2','Midle Malam3','Midle Malam4','Midle Malam5','Midle Malam6','Midle Malam7','Midle Malam8','Midle Malam9','Midle Malam10','') NOT NULL,
  `h26` enum('Pagi','Pagi2','Pagi3','Pagi4','Pagi5','Pagi6','Pagi7','Pagi8','Pagi9','Pagi10','Siang','Siang2','Siang3','Siang4','Siang5','Siang6','Siang7','Siang8','Siang9','Siang10','Malam','Malam2','Malam3','Malam4','Malam5','Malam6','Malam7','Malam8','Malam9','Malam10','Midle Pagi1','Midle Pagi2','Midle Pagi3','Midle Pagi4','Midle Pagi5','Midle Pagi6','Midle Pagi7','Midle Pagi8','Midle Pagi9','Midle Pagi10','Midle Siang1','Midle Siang2','Midle Siang3','Midle Siang4','Midle Siang5','Midle Siang6','Midle Siang7','Midle Siang8','Midle Siang9','Midle Siang10','Midle Malam1','Midle Malam2','Midle Malam3','Midle Malam4','Midle Malam5','Midle Malam6','Midle Malam7','Midle Malam8','Midle Malam9','Midle Malam10','') NOT NULL,
  `h27` enum('Pagi','Pagi2','Pagi3','Pagi4','Pagi5','Pagi6','Pagi7','Pagi8','Pagi9','Pagi10','Siang','Siang2','Siang3','Siang4','Siang5','Siang6','Siang7','Siang8','Siang9','Siang10','Malam','Malam2','Malam3','Malam4','Malam5','Malam6','Malam7','Malam8','Malam9','Malam10','Midle Pagi1','Midle Pagi2','Midle Pagi3','Midle Pagi4','Midle Pagi5','Midle Pagi6','Midle Pagi7','Midle Pagi8','Midle Pagi9','Midle Pagi10','Midle Siang1','Midle Siang2','Midle Siang3','Midle Siang4','Midle Siang5','Midle Siang6','Midle Siang7','Midle Siang8','Midle Siang9','Midle Siang10','Midle Malam1','Midle Malam2','Midle Malam3','Midle Malam4','Midle Malam5','Midle Malam6','Midle Malam7','Midle Malam8','Midle Malam9','Midle Malam10','') NOT NULL,
  `h28` enum('Pagi','Pagi2','Pagi3','Pagi4','Pagi5','Pagi6','Pagi7','Pagi8','Pagi9','Pagi10','Siang','Siang2','Siang3','Siang4','Siang5','Siang6','Siang7','Siang8','Siang9','Siang10','Malam','Malam2','Malam3','Malam4','Malam5','Malam6','Malam7','Malam8','Malam9','Malam10','Midle Pagi1','Midle Pagi2','Midle Pagi3','Midle Pagi4','Midle Pagi5','Midle Pagi6','Midle Pagi7','Midle Pagi8','Midle Pagi9','Midle Pagi10','Midle Siang1','Midle Siang2','Midle Siang3','Midle Siang4','Midle Siang5','Midle Siang6','Midle Siang7','Midle Siang8','Midle Siang9','Midle Siang10','Midle Malam1','Midle Malam2','Midle Malam3','Midle Malam4','Midle Malam5','Midle Malam6','Midle Malam7','Midle Malam8','Midle Malam9','Midle Malam10','') NOT NULL,
  `h29` enum('Pagi','Pagi2','Pagi3','Pagi4','Pagi5','Pagi6','Pagi7','Pagi8','Pagi9','Pagi10','Siang','Siang2','Siang3','Siang4','Siang5','Siang6','Siang7','Siang8','Siang9','Siang10','Malam','Malam2','Malam3','Malam4','Malam5','Malam6','Malam7','Malam8','Malam9','Malam10','Midle Pagi1','Midle Pagi2','Midle Pagi3','Midle Pagi4','Midle Pagi5','Midle Pagi6','Midle Pagi7','Midle Pagi8','Midle Pagi9','Midle Pagi10','Midle Siang1','Midle Siang2','Midle Siang3','Midle Siang4','Midle Siang5','Midle Siang6','Midle Siang7','Midle Siang8','Midle Siang9','Midle Siang10','Midle Malam1','Midle Malam2','Midle Malam3','Midle Malam4','Midle Malam5','Midle Malam6','Midle Malam7','Midle Malam8','Midle Malam9','Midle Malam10','') NOT NULL,
  `h30` enum('Pagi','Pagi2','Pagi3','Pagi4','Pagi5','Pagi6','Pagi7','Pagi8','Pagi9','Pagi10','Siang','Siang2','Siang3','Siang4','Siang5','Siang6','Siang7','Siang8','Siang9','Siang10','Malam','Malam2','Malam3','Malam4','Malam5','Malam6','Malam7','Malam8','Malam9','Malam10','Midle Pagi1','Midle Pagi2','Midle Pagi3','Midle Pagi4','Midle Pagi5','Midle Pagi6','Midle Pagi7','Midle Pagi8','Midle Pagi9','Midle Pagi10','Midle Siang1','Midle Siang2','Midle Siang3','Midle Siang4','Midle Siang5','Midle Siang6','Midle Siang7','Midle Siang8','Midle Siang9','Midle Siang10','Midle Malam1','Midle Malam2','Midle Malam3','Midle Malam4','Midle Malam5','Midle Malam6','Midle Malam7','Midle Malam8','Midle Malam9','Midle Malam10','') NOT NULL,
  `h31` enum('Pagi','Pagi2','Pagi3','Pagi4','Pagi5','Pagi6','Pagi7','Pagi8','Pagi9','Pagi10','Siang','Siang2','Siang3','Siang4','Siang5','Siang6','Siang7','Siang8','Siang9','Siang10','Malam','Malam2','Malam3','Malam4','Malam5','Malam6','Malam7','Malam8','Malam9','Malam10','Midle Pagi1','Midle Pagi2','Midle Pagi3','Midle Pagi4','Midle Pagi5','Midle Pagi6','Midle Pagi7','Midle Pagi8','Midle Pagi9','Midle Pagi10','Midle Siang1','Midle Siang2','Midle Siang3','Midle Siang4','Midle Siang5','Midle Siang6','Midle Siang7','Midle Siang8','Midle Siang9','Midle Siang10','Midle Malam1','Midle Malam2','Midle Malam3','Midle Malam4','Midle Malam5','Midle Malam6','Midle Malam7','Midle Malam8','Midle Malam9','Midle Malam10','') NOT NULL,
  PRIMARY KEY (`id`,`tahun`,`bulan`),
  KEY `h1` (`h1`) USING BTREE,
  KEY `h2` (`h2`) USING BTREE,
  KEY `h3` (`h3`) USING BTREE,
  KEY `h4` (`h4`) USING BTREE,
  KEY `h30` (`h30`) USING BTREE,
  KEY `h31` (`h31`) USING BTREE,
  KEY `h29` (`h29`) USING BTREE,
  KEY `h28` (`h28`) USING BTREE,
  KEY `h18` (`h18`) USING BTREE,
  KEY `h9` (`h9`) USING BTREE,
  CONSTRAINT `jadwal_pegawai_ibfk_1` FOREIGN KEY (`id`) REFERENCES `pegawai` (`id`) ON UPDATE CASCADE
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

/*Table structure for table `jadwal_tambahan` */

DROP TABLE IF EXISTS `jadwal_tambahan`;

CREATE TABLE `jadwal_tambahan` (
  `id` int(11) NOT NULL,
  `tahun` year(4) NOT NULL,
  `bulan` enum('01','02','03','04','05','06','07','08','09','10','11','12') NOT NULL,
  `h1` enum('Pagi','Pagi2','Pagi3','Pagi4','Pagi5','Pagi6','Pagi7','Pagi8','Pagi9','Pagi10','Siang','Siang2','Siang3','Siang4','Siang5','Siang6','Siang7','Siang8','Siang9','Siang10','Malam','Malam2','Malam3','Malam4','Malam5','Malam6','Malam7','Malam8','Malam9','Malam10','Midle Pagi1','Midle Pagi2','Midle Pagi3','Midle Pagi4','Midle Pagi5','Midle Pagi6','Midle Pagi7','Midle Pagi8','Midle Pagi9','Midle Pagi10','Midle Siang1','Midle Siang2','Midle Siang3','Midle Siang4','Midle Siang5','Midle Siang6','Midle Siang7','Midle Siang8','Midle Siang9','Midle Siang10','Midle Malam1','Midle Malam2','Midle Malam3','Midle Malam4','Midle Malam5','Midle Malam6','Midle Malam7','Midle Malam8','Midle Malam9','Midle Malam10','') NOT NULL,
  `h2` enum('Pagi','Pagi2','Pagi3','Pagi4','Pagi5','Pagi6','Pagi7','Pagi8','Pagi9','Pagi10','Siang','Siang2','Siang3','Siang4','Siang5','Siang6','Siang7','Siang8','Siang9','Siang10','Malam','Malam2','Malam3','Malam4','Malam5','Malam6','Malam7','Malam8','Malam9','Malam10','Midle Pagi1','Midle Pagi2','Midle Pagi3','Midle Pagi4','Midle Pagi5','Midle Pagi6','Midle Pagi7','Midle Pagi8','Midle Pagi9','Midle Pagi10','Midle Siang1','Midle Siang2','Midle Siang3','Midle Siang4','Midle Siang5','Midle Siang6','Midle Siang7','Midle Siang8','Midle Siang9','Midle Siang10','Midle Malam1','Midle Malam2','Midle Malam3','Midle Malam4','Midle Malam5','Midle Malam6','Midle Malam7','Midle Malam8','Midle Malam9','Midle Malam10','') NOT NULL,
  `h3` enum('Pagi','Pagi2','Pagi3','Pagi4','Pagi5','Pagi6','Pagi7','Pagi8','Pagi9','Pagi10','Siang','Siang2','Siang3','Siang4','Siang5','Siang6','Siang7','Siang8','Siang9','Siang10','Malam','Malam2','Malam3','Malam4','Malam5','Malam6','Malam7','Malam8','Malam9','Malam10','Midle Pagi1','Midle Pagi2','Midle Pagi3','Midle Pagi4','Midle Pagi5','Midle Pagi6','Midle Pagi7','Midle Pagi8','Midle Pagi9','Midle Pagi10','Midle Siang1','Midle Siang2','Midle Siang3','Midle Siang4','Midle Siang5','Midle Siang6','Midle Siang7','Midle Siang8','Midle Siang9','Midle Siang10','Midle Malam1','Midle Malam2','Midle Malam3','Midle Malam4','Midle Malam5','Midle Malam6','Midle Malam7','Midle Malam8','Midle Malam9','Midle Malam10','') NOT NULL,
  `h4` enum('Pagi','Pagi2','Pagi3','Pagi4','Pagi5','Pagi6','Pagi7','Pagi8','Pagi9','Pagi10','Siang','Siang2','Siang3','Siang4','Siang5','Siang6','Siang7','Siang8','Siang9','Siang10','Malam','Malam2','Malam3','Malam4','Malam5','Malam6','Malam7','Malam8','Malam9','Malam10','Midle Pagi1','Midle Pagi2','Midle Pagi3','Midle Pagi4','Midle Pagi5','Midle Pagi6','Midle Pagi7','Midle Pagi8','Midle Pagi9','Midle Pagi10','Midle Siang1','Midle Siang2','Midle Siang3','Midle Siang4','Midle Siang5','Midle Siang6','Midle Siang7','Midle Siang8','Midle Siang9','Midle Siang10','Midle Malam1','Midle Malam2','Midle Malam3','Midle Malam4','Midle Malam5','Midle Malam6','Midle Malam7','Midle Malam8','Midle Malam9','Midle Malam10','') NOT NULL,
  `h5` enum('Pagi','Pagi2','Pagi3','Pagi4','Pagi5','Pagi6','Pagi7','Pagi8','Pagi9','Pagi10','Siang','Siang2','Siang3','Siang4','Siang5','Siang6','Siang7','Siang8','Siang9','Siang10','Malam','Malam2','Malam3','Malam4','Malam5','Malam6','Malam7','Malam8','Malam9','Malam10','Midle Pagi1','Midle Pagi2','Midle Pagi3','Midle Pagi4','Midle Pagi5','Midle Pagi6','Midle Pagi7','Midle Pagi8','Midle Pagi9','Midle Pagi10','Midle Siang1','Midle Siang2','Midle Siang3','Midle Siang4','Midle Siang5','Midle Siang6','Midle Siang7','Midle Siang8','Midle Siang9','Midle Siang10','Midle Malam1','Midle Malam2','Midle Malam3','Midle Malam4','Midle Malam5','Midle Malam6','Midle Malam7','Midle Malam8','Midle Malam9','Midle Malam10','') NOT NULL,
  `h6` enum('Pagi','Pagi2','Pagi3','Pagi4','Pagi5','Pagi6','Pagi7','Pagi8','Pagi9','Pagi10','Siang','Siang2','Siang3','Siang4','Siang5','Siang6','Siang7','Siang8','Siang9','Siang10','Malam','Malam2','Malam3','Malam4','Malam5','Malam6','Malam7','Malam8','Malam9','Malam10','Midle Pagi1','Midle Pagi2','Midle Pagi3','Midle Pagi4','Midle Pagi5','Midle Pagi6','Midle Pagi7','Midle Pagi8','Midle Pagi9','Midle Pagi10','Midle Siang1','Midle Siang2','Midle Siang3','Midle Siang4','Midle Siang5','Midle Siang6','Midle Siang7','Midle Siang8','Midle Siang9','Midle Siang10','Midle Malam1','Midle Malam2','Midle Malam3','Midle Malam4','Midle Malam5','Midle Malam6','Midle Malam7','Midle Malam8','Midle Malam9','Midle Malam10','') NOT NULL,
  `h7` enum('Pagi','Pagi2','Pagi3','Pagi4','Pagi5','Pagi6','Pagi7','Pagi8','Pagi9','Pagi10','Siang','Siang2','Siang3','Siang4','Siang5','Siang6','Siang7','Siang8','Siang9','Siang10','Malam','Malam2','Malam3','Malam4','Malam5','Malam6','Malam7','Malam8','Malam9','Malam10','Midle Pagi1','Midle Pagi2','Midle Pagi3','Midle Pagi4','Midle Pagi5','Midle Pagi6','Midle Pagi7','Midle Pagi8','Midle Pagi9','Midle Pagi10','Midle Siang1','Midle Siang2','Midle Siang3','Midle Siang4','Midle Siang5','Midle Siang6','Midle Siang7','Midle Siang8','Midle Siang9','Midle Siang10','Midle Malam1','Midle Malam2','Midle Malam3','Midle Malam4','Midle Malam5','Midle Malam6','Midle Malam7','Midle Malam8','Midle Malam9','Midle Malam10','') NOT NULL,
  `h8` enum('Pagi','Pagi2','Pagi3','Pagi4','Pagi5','Pagi6','Pagi7','Pagi8','Pagi9','Pagi10','Siang','Siang2','Siang3','Siang4','Siang5','Siang6','Siang7','Siang8','Siang9','Siang10','Malam','Malam2','Malam3','Malam4','Malam5','Malam6','Malam7','Malam8','Malam9','Malam10','Midle Pagi1','Midle Pagi2','Midle Pagi3','Midle Pagi4','Midle Pagi5','Midle Pagi6','Midle Pagi7','Midle Pagi8','Midle Pagi9','Midle Pagi10','Midle Siang1','Midle Siang2','Midle Siang3','Midle Siang4','Midle Siang5','Midle Siang6','Midle Siang7','Midle Siang8','Midle Siang9','Midle Siang10','Midle Malam1','Midle Malam2','Midle Malam3','Midle Malam4','Midle Malam5','Midle Malam6','Midle Malam7','Midle Malam8','Midle Malam9','Midle Malam10','') NOT NULL,
  `h9` enum('Pagi','Pagi2','Pagi3','Pagi4','Pagi5','Pagi6','Pagi7','Pagi8','Pagi9','Pagi10','Siang','Siang2','Siang3','Siang4','Siang5','Siang6','Siang7','Siang8','Siang9','Siang10','Malam','Malam2','Malam3','Malam4','Malam5','Malam6','Malam7','Malam8','Malam9','Malam10','Midle Pagi1','Midle Pagi2','Midle Pagi3','Midle Pagi4','Midle Pagi5','Midle Pagi6','Midle Pagi7','Midle Pagi8','Midle Pagi9','Midle Pagi10','Midle Siang1','Midle Siang2','Midle Siang3','Midle Siang4','Midle Siang5','Midle Siang6','Midle Siang7','Midle Siang8','Midle Siang9','Midle Siang10','Midle Malam1','Midle Malam2','Midle Malam3','Midle Malam4','Midle Malam5','Midle Malam6','Midle Malam7','Midle Malam8','Midle Malam9','Midle Malam10','') NOT NULL,
  `h10` enum('Pagi','Pagi2','Pagi3','Pagi4','Pagi5','Pagi6','Pagi7','Pagi8','Pagi9','Pagi10','Siang','Siang2','Siang3','Siang4','Siang5','Siang6','Siang7','Siang8','Siang9','Siang10','Malam','Malam2','Malam3','Malam4','Malam5','Malam6','Malam7','Malam8','Malam9','Malam10','Midle Pagi1','Midle Pagi2','Midle Pagi3','Midle Pagi4','Midle Pagi5','Midle Pagi6','Midle Pagi7','Midle Pagi8','Midle Pagi9','Midle Pagi10','Midle Siang1','Midle Siang2','Midle Siang3','Midle Siang4','Midle Siang5','Midle Siang6','Midle Siang7','Midle Siang8','Midle Siang9','Midle Siang10','Midle Malam1','Midle Malam2','Midle Malam3','Midle Malam4','Midle Malam5','Midle Malam6','Midle Malam7','Midle Malam8','Midle Malam9','Midle Malam10','') NOT NULL,
  `h11` enum('Pagi','Pagi2','Pagi3','Pagi4','Pagi5','Pagi6','Pagi7','Pagi8','Pagi9','Pagi10','Siang','Siang2','Siang3','Siang4','Siang5','Siang6','Siang7','Siang8','Siang9','Siang10','Malam','Malam2','Malam3','Malam4','Malam5','Malam6','Malam7','Malam8','Malam9','Malam10','Midle Pagi1','Midle Pagi2','Midle Pagi3','Midle Pagi4','Midle Pagi5','Midle Pagi6','Midle Pagi7','Midle Pagi8','Midle Pagi9','Midle Pagi10','Midle Siang1','Midle Siang2','Midle Siang3','Midle Siang4','Midle Siang5','Midle Siang6','Midle Siang7','Midle Siang8','Midle Siang9','Midle Siang10','Midle Malam1','Midle Malam2','Midle Malam3','Midle Malam4','Midle Malam5','Midle Malam6','Midle Malam7','Midle Malam8','Midle Malam9','Midle Malam10','') NOT NULL,
  `h12` enum('Pagi','Pagi2','Pagi3','Pagi4','Pagi5','Pagi6','Pagi7','Pagi8','Pagi9','Pagi10','Siang','Siang2','Siang3','Siang4','Siang5','Siang6','Siang7','Siang8','Siang9','Siang10','Malam','Malam2','Malam3','Malam4','Malam5','Malam6','Malam7','Malam8','Malam9','Malam10','Midle Pagi1','Midle Pagi2','Midle Pagi3','Midle Pagi4','Midle Pagi5','Midle Pagi6','Midle Pagi7','Midle Pagi8','Midle Pagi9','Midle Pagi10','Midle Siang1','Midle Siang2','Midle Siang3','Midle Siang4','Midle Siang5','Midle Siang6','Midle Siang7','Midle Siang8','Midle Siang9','Midle Siang10','Midle Malam1','Midle Malam2','Midle Malam3','Midle Malam4','Midle Malam5','Midle Malam6','Midle Malam7','Midle Malam8','Midle Malam9','Midle Malam10','') NOT NULL,
  `h13` enum('Pagi','Pagi2','Pagi3','Pagi4','Pagi5','Pagi6','Pagi7','Pagi8','Pagi9','Pagi10','Siang','Siang2','Siang3','Siang4','Siang5','Siang6','Siang7','Siang8','Siang9','Siang10','Malam','Malam2','Malam3','Malam4','Malam5','Malam6','Malam7','Malam8','Malam9','Malam10','Midle Pagi1','Midle Pagi2','Midle Pagi3','Midle Pagi4','Midle Pagi5','Midle Pagi6','Midle Pagi7','Midle Pagi8','Midle Pagi9','Midle Pagi10','Midle Siang1','Midle Siang2','Midle Siang3','Midle Siang4','Midle Siang5','Midle Siang6','Midle Siang7','Midle Siang8','Midle Siang9','Midle Siang10','Midle Malam1','Midle Malam2','Midle Malam3','Midle Malam4','Midle Malam5','Midle Malam6','Midle Malam7','Midle Malam8','Midle Malam9','Midle Malam10','') NOT NULL,
  `h14` enum('Pagi','Pagi2','Pagi3','Pagi4','Pagi5','Pagi6','Pagi7','Pagi8','Pagi9','Pagi10','Siang','Siang2','Siang3','Siang4','Siang5','Siang6','Siang7','Siang8','Siang9','Siang10','Malam','Malam2','Malam3','Malam4','Malam5','Malam6','Malam7','Malam8','Malam9','Malam10','Midle Pagi1','Midle Pagi2','Midle Pagi3','Midle Pagi4','Midle Pagi5','Midle Pagi6','Midle Pagi7','Midle Pagi8','Midle Pagi9','Midle Pagi10','Midle Siang1','Midle Siang2','Midle Siang3','Midle Siang4','Midle Siang5','Midle Siang6','Midle Siang7','Midle Siang8','Midle Siang9','Midle Siang10','Midle Malam1','Midle Malam2','Midle Malam3','Midle Malam4','Midle Malam5','Midle Malam6','Midle Malam7','Midle Malam8','Midle Malam9','Midle Malam10','') NOT NULL,
  `h15` enum('Pagi','Pagi2','Pagi3','Pagi4','Pagi5','Pagi6','Pagi7','Pagi8','Pagi9','Pagi10','Siang','Siang2','Siang3','Siang4','Siang5','Siang6','Siang7','Siang8','Siang9','Siang10','Malam','Malam2','Malam3','Malam4','Malam5','Malam6','Malam7','Malam8','Malam9','Malam10','Midle Pagi1','Midle Pagi2','Midle Pagi3','Midle Pagi4','Midle Pagi5','Midle Pagi6','Midle Pagi7','Midle Pagi8','Midle Pagi9','Midle Pagi10','Midle Siang1','Midle Siang2','Midle Siang3','Midle Siang4','Midle Siang5','Midle Siang6','Midle Siang7','Midle Siang8','Midle Siang9','Midle Siang10','Midle Malam1','Midle Malam2','Midle Malam3','Midle Malam4','Midle Malam5','Midle Malam6','Midle Malam7','Midle Malam8','Midle Malam9','Midle Malam10','') NOT NULL,
  `h16` enum('Pagi','Pagi2','Pagi3','Pagi4','Pagi5','Pagi6','Pagi7','Pagi8','Pagi9','Pagi10','Siang','Siang2','Siang3','Siang4','Siang5','Siang6','Siang7','Siang8','Siang9','Siang10','Malam','Malam2','Malam3','Malam4','Malam5','Malam6','Malam7','Malam8','Malam9','Malam10','Midle Pagi1','Midle Pagi2','Midle Pagi3','Midle Pagi4','Midle Pagi5','Midle Pagi6','Midle Pagi7','Midle Pagi8','Midle Pagi9','Midle Pagi10','Midle Siang1','Midle Siang2','Midle Siang3','Midle Siang4','Midle Siang5','Midle Siang6','Midle Siang7','Midle Siang8','Midle Siang9','Midle Siang10','Midle Malam1','Midle Malam2','Midle Malam3','Midle Malam4','Midle Malam5','Midle Malam6','Midle Malam7','Midle Malam8','Midle Malam9','Midle Malam10','') NOT NULL,
  `h17` enum('Pagi','Pagi2','Pagi3','Pagi4','Pagi5','Pagi6','Pagi7','Pagi8','Pagi9','Pagi10','Siang','Siang2','Siang3','Siang4','Siang5','Siang6','Siang7','Siang8','Siang9','Siang10','Malam','Malam2','Malam3','Malam4','Malam5','Malam6','Malam7','Malam8','Malam9','Malam10','Midle Pagi1','Midle Pagi2','Midle Pagi3','Midle Pagi4','Midle Pagi5','Midle Pagi6','Midle Pagi7','Midle Pagi8','Midle Pagi9','Midle Pagi10','Midle Siang1','Midle Siang2','Midle Siang3','Midle Siang4','Midle Siang5','Midle Siang6','Midle Siang7','Midle Siang8','Midle Siang9','Midle Siang10','Midle Malam1','Midle Malam2','Midle Malam3','Midle Malam4','Midle Malam5','Midle Malam6','Midle Malam7','Midle Malam8','Midle Malam9','Midle Malam10','') NOT NULL,
  `h18` enum('Pagi','Pagi2','Pagi3','Pagi4','Pagi5','Pagi6','Pagi7','Pagi8','Pagi9','Pagi10','Siang','Siang2','Siang3','Siang4','Siang5','Siang6','Siang7','Siang8','Siang9','Siang10','Malam','Malam2','Malam3','Malam4','Malam5','Malam6','Malam7','Malam8','Malam9','Malam10','Midle Pagi1','Midle Pagi2','Midle Pagi3','Midle Pagi4','Midle Pagi5','Midle Pagi6','Midle Pagi7','Midle Pagi8','Midle Pagi9','Midle Pagi10','Midle Siang1','Midle Siang2','Midle Siang3','Midle Siang4','Midle Siang5','Midle Siang6','Midle Siang7','Midle Siang8','Midle Siang9','Midle Siang10','Midle Malam1','Midle Malam2','Midle Malam3','Midle Malam4','Midle Malam5','Midle Malam6','Midle Malam7','Midle Malam8','Midle Malam9','Midle Malam10','') NOT NULL,
  `h19` enum('Pagi','Pagi2','Pagi3','Pagi4','Pagi5','Pagi6','Pagi7','Pagi8','Pagi9','Pagi10','Siang','Siang2','Siang3','Siang4','Siang5','Siang6','Siang7','Siang8','Siang9','Siang10','Malam','Malam2','Malam3','Malam4','Malam5','Malam6','Malam7','Malam8','Malam9','Malam10','Midle Pagi1','Midle Pagi2','Midle Pagi3','Midle Pagi4','Midle Pagi5','Midle Pagi6','Midle Pagi7','Midle Pagi8','Midle Pagi9','Midle Pagi10','Midle Siang1','Midle Siang2','Midle Siang3','Midle Siang4','Midle Siang5','Midle Siang6','Midle Siang7','Midle Siang8','Midle Siang9','Midle Siang10','Midle Malam1','Midle Malam2','Midle Malam3','Midle Malam4','Midle Malam5','Midle Malam6','Midle Malam7','Midle Malam8','Midle Malam9','Midle Malam10','') NOT NULL,
  `h20` enum('Pagi','Pagi2','Pagi3','Pagi4','Pagi5','Pagi6','Pagi7','Pagi8','Pagi9','Pagi10','Siang','Siang2','Siang3','Siang4','Siang5','Siang6','Siang7','Siang8','Siang9','Siang10','Malam','Malam2','Malam3','Malam4','Malam5','Malam6','Malam7','Malam8','Malam9','Malam10','Midle Pagi1','Midle Pagi2','Midle Pagi3','Midle Pagi4','Midle Pagi5','Midle Pagi6','Midle Pagi7','Midle Pagi8','Midle Pagi9','Midle Pagi10','Midle Siang1','Midle Siang2','Midle Siang3','Midle Siang4','Midle Siang5','Midle Siang6','Midle Siang7','Midle Siang8','Midle Siang9','Midle Siang10','Midle Malam1','Midle Malam2','Midle Malam3','Midle Malam4','Midle Malam5','Midle Malam6','Midle Malam7','Midle Malam8','Midle Malam9','Midle Malam10','') NOT NULL,
  `h21` enum('Pagi','Pagi2','Pagi3','Pagi4','Pagi5','Pagi6','Pagi7','Pagi8','Pagi9','Pagi10','Siang','Siang2','Siang3','Siang4','Siang5','Siang6','Siang7','Siang8','Siang9','Siang10','Malam','Malam2','Malam3','Malam4','Malam5','Malam6','Malam7','Malam8','Malam9','Malam10','Midle Pagi1','Midle Pagi2','Midle Pagi3','Midle Pagi4','Midle Pagi5','Midle Pagi6','Midle Pagi7','Midle Pagi8','Midle Pagi9','Midle Pagi10','Midle Siang1','Midle Siang2','Midle Siang3','Midle Siang4','Midle Siang5','Midle Siang6','Midle Siang7','Midle Siang8','Midle Siang9','Midle Siang10','Midle Malam1','Midle Malam2','Midle Malam3','Midle Malam4','Midle Malam5','Midle Malam6','Midle Malam7','Midle Malam8','Midle Malam9','Midle Malam10','') NOT NULL,
  `h22` enum('Pagi','Pagi2','Pagi3','Pagi4','Pagi5','Pagi6','Pagi7','Pagi8','Pagi9','Pagi10','Siang','Siang2','Siang3','Siang4','Siang5','Siang6','Siang7','Siang8','Siang9','Siang10','Malam','Malam2','Malam3','Malam4','Malam5','Malam6','Malam7','Malam8','Malam9','Malam10','Midle Pagi1','Midle Pagi2','Midle Pagi3','Midle Pagi4','Midle Pagi5','Midle Pagi6','Midle Pagi7','Midle Pagi8','Midle Pagi9','Midle Pagi10','Midle Siang1','Midle Siang2','Midle Siang3','Midle Siang4','Midle Siang5','Midle Siang6','Midle Siang7','Midle Siang8','Midle Siang9','Midle Siang10','Midle Malam1','Midle Malam2','Midle Malam3','Midle Malam4','Midle Malam5','Midle Malam6','Midle Malam7','Midle Malam8','Midle Malam9','Midle Malam10','') NOT NULL,
  `h23` enum('Pagi','Pagi2','Pagi3','Pagi4','Pagi5','Pagi6','Pagi7','Pagi8','Pagi9','Pagi10','Siang','Siang2','Siang3','Siang4','Siang5','Siang6','Siang7','Siang8','Siang9','Siang10','Malam','Malam2','Malam3','Malam4','Malam5','Malam6','Malam7','Malam8','Malam9','Malam10','Midle Pagi1','Midle Pagi2','Midle Pagi3','Midle Pagi4','Midle Pagi5','Midle Pagi6','Midle Pagi7','Midle Pagi8','Midle Pagi9','Midle Pagi10','Midle Siang1','Midle Siang2','Midle Siang3','Midle Siang4','Midle Siang5','Midle Siang6','Midle Siang7','Midle Siang8','Midle Siang9','Midle Siang10','Midle Malam1','Midle Malam2','Midle Malam3','Midle Malam4','Midle Malam5','Midle Malam6','Midle Malam7','Midle Malam8','Midle Malam9','Midle Malam10','') NOT NULL,
  `h24` enum('Pagi','Pagi2','Pagi3','Pagi4','Pagi5','Pagi6','Pagi7','Pagi8','Pagi9','Pagi10','Siang','Siang2','Siang3','Siang4','Siang5','Siang6','Siang7','Siang8','Siang9','Siang10','Malam','Malam2','Malam3','Malam4','Malam5','Malam6','Malam7','Malam8','Malam9','Malam10','Midle Pagi1','Midle Pagi2','Midle Pagi3','Midle Pagi4','Midle Pagi5','Midle Pagi6','Midle Pagi7','Midle Pagi8','Midle Pagi9','Midle Pagi10','Midle Siang1','Midle Siang2','Midle Siang3','Midle Siang4','Midle Siang5','Midle Siang6','Midle Siang7','Midle Siang8','Midle Siang9','Midle Siang10','Midle Malam1','Midle Malam2','Midle Malam3','Midle Malam4','Midle Malam5','Midle Malam6','Midle Malam7','Midle Malam8','Midle Malam9','Midle Malam10','') NOT NULL,
  `h25` enum('Pagi','Pagi2','Pagi3','Pagi4','Pagi5','Pagi6','Pagi7','Pagi8','Pagi9','Pagi10','Siang','Siang2','Siang3','Siang4','Siang5','Siang6','Siang7','Siang8','Siang9','Siang10','Malam','Malam2','Malam3','Malam4','Malam5','Malam6','Malam7','Malam8','Malam9','Malam10','Midle Pagi1','Midle Pagi2','Midle Pagi3','Midle Pagi4','Midle Pagi5','Midle Pagi6','Midle Pagi7','Midle Pagi8','Midle Pagi9','Midle Pagi10','Midle Siang1','Midle Siang2','Midle Siang3','Midle Siang4','Midle Siang5','Midle Siang6','Midle Siang7','Midle Siang8','Midle Siang9','Midle Siang10','Midle Malam1','Midle Malam2','Midle Malam3','Midle Malam4','Midle Malam5','Midle Malam6','Midle Malam7','Midle Malam8','Midle Malam9','Midle Malam10','') NOT NULL,
  `h26` enum('Pagi','Pagi2','Pagi3','Pagi4','Pagi5','Pagi6','Pagi7','Pagi8','Pagi9','Pagi10','Siang','Siang2','Siang3','Siang4','Siang5','Siang6','Siang7','Siang8','Siang9','Siang10','Malam','Malam2','Malam3','Malam4','Malam5','Malam6','Malam7','Malam8','Malam9','Malam10','Midle Pagi1','Midle Pagi2','Midle Pagi3','Midle Pagi4','Midle Pagi5','Midle Pagi6','Midle Pagi7','Midle Pagi8','Midle Pagi9','Midle Pagi10','Midle Siang1','Midle Siang2','Midle Siang3','Midle Siang4','Midle Siang5','Midle Siang6','Midle Siang7','Midle Siang8','Midle Siang9','Midle Siang10','Midle Malam1','Midle Malam2','Midle Malam3','Midle Malam4','Midle Malam5','Midle Malam6','Midle Malam7','Midle Malam8','Midle Malam9','Midle Malam10','') NOT NULL,
  `h27` enum('Pagi','Pagi2','Pagi3','Pagi4','Pagi5','Pagi6','Pagi7','Pagi8','Pagi9','Pagi10','Siang','Siang2','Siang3','Siang4','Siang5','Siang6','Siang7','Siang8','Siang9','Siang10','Malam','Malam2','Malam3','Malam4','Malam5','Malam6','Malam7','Malam8','Malam9','Malam10','Midle Pagi1','Midle Pagi2','Midle Pagi3','Midle Pagi4','Midle Pagi5','Midle Pagi6','Midle Pagi7','Midle Pagi8','Midle Pagi9','Midle Pagi10','Midle Siang1','Midle Siang2','Midle Siang3','Midle Siang4','Midle Siang5','Midle Siang6','Midle Siang7','Midle Siang8','Midle Siang9','Midle Siang10','Midle Malam1','Midle Malam2','Midle Malam3','Midle Malam4','Midle Malam5','Midle Malam6','Midle Malam7','Midle Malam8','Midle Malam9','Midle Malam10','') NOT NULL,
  `h28` enum('Pagi','Pagi2','Pagi3','Pagi4','Pagi5','Pagi6','Pagi7','Pagi8','Pagi9','Pagi10','Siang','Siang2','Siang3','Siang4','Siang5','Siang6','Siang7','Siang8','Siang9','Siang10','Malam','Malam2','Malam3','Malam4','Malam5','Malam6','Malam7','Malam8','Malam9','Malam10','Midle Pagi1','Midle Pagi2','Midle Pagi3','Midle Pagi4','Midle Pagi5','Midle Pagi6','Midle Pagi7','Midle Pagi8','Midle Pagi9','Midle Pagi10','Midle Siang1','Midle Siang2','Midle Siang3','Midle Siang4','Midle Siang5','Midle Siang6','Midle Siang7','Midle Siang8','Midle Siang9','Midle Siang10','Midle Malam1','Midle Malam2','Midle Malam3','Midle Malam4','Midle Malam5','Midle Malam6','Midle Malam7','Midle Malam8','Midle Malam9','Midle Malam10','') NOT NULL,
  `h29` enum('Pagi','Pagi2','Pagi3','Pagi4','Pagi5','Pagi6','Pagi7','Pagi8','Pagi9','Pagi10','Siang','Siang2','Siang3','Siang4','Siang5','Siang6','Siang7','Siang8','Siang9','Siang10','Malam','Malam2','Malam3','Malam4','Malam5','Malam6','Malam7','Malam8','Malam9','Malam10','Midle Pagi1','Midle Pagi2','Midle Pagi3','Midle Pagi4','Midle Pagi5','Midle Pagi6','Midle Pagi7','Midle Pagi8','Midle Pagi9','Midle Pagi10','Midle Siang1','Midle Siang2','Midle Siang3','Midle Siang4','Midle Siang5','Midle Siang6','Midle Siang7','Midle Siang8','Midle Siang9','Midle Siang10','Midle Malam1','Midle Malam2','Midle Malam3','Midle Malam4','Midle Malam5','Midle Malam6','Midle Malam7','Midle Malam8','Midle Malam9','Midle Malam10','') NOT NULL,
  `h30` enum('Pagi','Pagi2','Pagi3','Pagi4','Pagi5','Pagi6','Pagi7','Pagi8','Pagi9','Pagi10','Siang','Siang2','Siang3','Siang4','Siang5','Siang6','Siang7','Siang8','Siang9','Siang10','Malam','Malam2','Malam3','Malam4','Malam5','Malam6','Malam7','Malam8','Malam9','Malam10','Midle Pagi1','Midle Pagi2','Midle Pagi3','Midle Pagi4','Midle Pagi5','Midle Pagi6','Midle Pagi7','Midle Pagi8','Midle Pagi9','Midle Pagi10','Midle Siang1','Midle Siang2','Midle Siang3','Midle Siang4','Midle Siang5','Midle Siang6','Midle Siang7','Midle Siang8','Midle Siang9','Midle Siang10','Midle Malam1','Midle Malam2','Midle Malam3','Midle Malam4','Midle Malam5','Midle Malam6','Midle Malam7','Midle Malam8','Midle Malam9','Midle Malam10','') NOT NULL,
  `h31` enum('Pagi','Pagi2','Pagi3','Pagi4','Pagi5','Pagi6','Pagi7','Pagi8','Pagi9','Pagi10','Siang','Siang2','Siang3','Siang4','Siang5','Siang6','Siang7','Siang8','Siang9','Siang10','Malam','Malam2','Malam3','Malam4','Malam5','Malam6','Malam7','Malam8','Malam9','Malam10','Midle Pagi1','Midle Pagi2','Midle Pagi3','Midle Pagi4','Midle Pagi5','Midle Pagi6','Midle Pagi7','Midle Pagi8','Midle Pagi9','Midle Pagi10','Midle Siang1','Midle Siang2','Midle Siang3','Midle Siang4','Midle Siang5','Midle Siang6','Midle Siang7','Midle Siang8','Midle Siang9','Midle Siang10','Midle Malam1','Midle Malam2','Midle Malam3','Midle Malam4','Midle Malam5','Midle Malam6','Midle Malam7','Midle Malam8','Midle Malam9','Midle Malam10','') NOT NULL,
  PRIMARY KEY (`id`,`tahun`,`bulan`),
  CONSTRAINT `jadwal_tambahan_ibfk_1` FOREIGN KEY (`id`) REFERENCES `pegawai` (`id`) ON UPDATE CASCADE
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

/*Table structure for table `jam_jaga` */

DROP TABLE IF EXISTS `jam_jaga`;

CREATE TABLE `jam_jaga` (
  `no_id` int(11) NOT NULL AUTO_INCREMENT,
  `dep_id` char(4) NOT NULL,
  `shift` enum('Pagi','Pagi2','Pagi3','Pagi4','Pagi5','Pagi6','Pagi7','Pagi8','Pagi9','Pagi10','Siang','Siang2','Siang3','Siang4','Siang5','Siang6','Siang7','Siang8','Siang9','Siang10','Malam','Malam2','Malam3','Malam4','Malam5','Malam6','Malam7','Malam8','Malam9','Malam10','Midle Pagi1','Midle Pagi2','Midle Pagi3','Midle Pagi4','Midle Pagi5','Midle Pagi6','Midle Pagi7','Midle Pagi8','Midle Pagi9','Midle Pagi10','Midle Siang1','Midle Siang2','Midle Siang3','Midle Siang4','Midle Siang5','Midle Siang6','Midle Siang7','Midle Siang8','Midle Siang9','Midle Siang10','Midle Malam1','Midle Malam2','Midle Malam3','Midle Malam4','Midle Malam5','Midle Malam6','Midle Malam7','Midle Malam8','Midle Malam9','Midle Malam10') NOT NULL,
  `jam_masuk` time NOT NULL,
  `jam_pulang` time NOT NULL,
  PRIMARY KEY (`no_id`),
  UNIQUE KEY `dep_id_2` (`dep_id`,`shift`),
  KEY `dep_id` (`dep_id`),
  KEY `shift` (`shift`),
  KEY `jam_masuk` (`jam_masuk`),
  KEY `jam_pulang` (`jam_pulang`),
  CONSTRAINT `jam_jaga_ibfk_1` FOREIGN KEY (`dep_id`) REFERENCES `departemen` (`dep_id`) ON UPDATE CASCADE
) ENGINE=InnoDB AUTO_INCREMENT=6 DEFAULT CHARSET=latin1;

/*Table structure for table `jam_masuk` */

DROP TABLE IF EXISTS `jam_masuk`;

CREATE TABLE `jam_masuk` (
  `shift` enum('Pagi','Pagi2','Pagi3','Pagi4','Pagi5','Pagi6','Pagi7','Pagi8','Pagi9','Pagi10','Siang','Siang2','Siang3','Siang4','Siang5','Siang6','Siang7','Siang8','Siang9','Siang10','Malam','Malam2','Malam3','Malam4','Malam5','Malam6','Malam7','Malam8','Malam9','Malam10','Midle Pagi1','Midle Pagi2','Midle Pagi3','Midle Pagi4','Midle Pagi5','Midle Pagi6','Midle Pagi7','Midle Pagi8','Midle Pagi9','Midle Pagi10','Midle Siang1','Midle Siang2','Midle Siang3','Midle Siang4','Midle Siang5','Midle Siang6','Midle Siang7','Midle Siang8','Midle Siang9','Midle Siang10','Midle Malam1','Midle Malam2','Midle Malam3','Midle Malam4','Midle Malam5','Midle Malam6','Midle Malam7','Midle Malam8','Midle Malam9','Midle Malam10') NOT NULL,
  `jam_masuk` time NOT NULL,
  `jam_pulang` time NOT NULL,
  PRIMARY KEY (`shift`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

/*Table structure for table `jamsostek` */

DROP TABLE IF EXISTS `jamsostek`;

CREATE TABLE `jamsostek` (
  `stts` char(5) NOT NULL,
  `biaya` double NOT NULL,
  PRIMARY KEY (`stts`),
  KEY `biaya` (`biaya`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

/*Table structure for table `jasa_lain` */

DROP TABLE IF EXISTS `jasa_lain`;

CREATE TABLE `jasa_lain` (
  `thn` year(4) NOT NULL,
  `bln` int(11) NOT NULL,
  `id` int(11) NOT NULL,
  `bsr_jasa` double NOT NULL,
  `ktg` varchar(40) NOT NULL,
  PRIMARY KEY (`thn`,`bln`,`id`,`bsr_jasa`,`ktg`),
  KEY `id` (`id`),
  CONSTRAINT `jasa_lain_ibfk_1` FOREIGN KEY (`id`) REFERENCES `pegawai` (`id`) ON UPDATE CASCADE
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

/*Table structure for table `jenis` */

DROP TABLE IF EXISTS `jenis`;

CREATE TABLE `jenis` (
  `kdjns` char(4) NOT NULL,
  `nama` varchar(30) NOT NULL,
  `keterangan` varchar(50) NOT NULL,
  PRIMARY KEY (`kdjns`),
  KEY `nama` (`nama`),
  KEY `keterangan` (`keterangan`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

/*Table structure for table `jgmlm` */

DROP TABLE IF EXISTS `jgmlm`;

CREATE TABLE `jgmlm` (
  `tgl` date NOT NULL,
  `id` int(11) NOT NULL,
  `jml` int(11) NOT NULL,
  PRIMARY KEY (`tgl`,`id`),
  KEY `id` (`id`),
  KEY `jml` (`jml`),
  CONSTRAINT `jgmlm_ibfk_1` FOREIGN KEY (`id`) REFERENCES `pegawai` (`id`) ON UPDATE CASCADE
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

/*Table structure for table `jnj_jabatan` */

DROP TABLE IF EXISTS `jnj_jabatan`;

CREATE TABLE `jnj_jabatan` (
  `kode` varchar(10) NOT NULL,
  `nama` varchar(50) NOT NULL,
  `tnj` double NOT NULL,
  PRIMARY KEY (`kode`),
  KEY `nama` (`nama`),
  KEY `tnj` (`tnj`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

/*Table structure for table `jns_perawatan` */

DROP TABLE IF EXISTS `jns_perawatan`;

CREATE TABLE `jns_perawatan` (
  `kd_jenis_prw` varchar(15) NOT NULL,
  `nm_perawatan` varchar(120) DEFAULT NULL,
  `kd_kategori` char(5) DEFAULT NULL,
  `material` double DEFAULT NULL,
  `bhp` double NOT NULL,
  `tarif_tindakandr` double DEFAULT NULL,
  `tarif_tindakanpr` double DEFAULT NULL,
  `kso` double DEFAULT NULL,
  `menejemen` double DEFAULT NULL,
  `total_byrdr` double DEFAULT NULL,
  `total_byrpr` double DEFAULT NULL,
  `total_byrdrpr` double NOT NULL,
  `kd_pj` char(3) NOT NULL,
  `kd_poli` char(5) NOT NULL,
  `status` enum('0','1') NOT NULL,
  PRIMARY KEY (`kd_jenis_prw`),
  KEY `kd_kategori` (`kd_kategori`),
  KEY `kd_pj` (`kd_pj`),
  KEY `kd_poli` (`kd_poli`),
  KEY `nm_perawatan` (`nm_perawatan`),
  KEY `material` (`material`),
  KEY `tarif_tindakandr` (`tarif_tindakandr`),
  KEY `tarif_tindakanpr` (`tarif_tindakanpr`),
  KEY `total_byrdr` (`total_byrdr`),
  KEY `total_byrpr` (`total_byrpr`),
  KEY `kso` (`kso`) USING BTREE,
  KEY `menejemen` (`menejemen`) USING BTREE,
  KEY `status` (`status`) USING BTREE,
  KEY `total_byrdrpr` (`total_byrdrpr`) USING BTREE,
  KEY `bhp` (`bhp`) USING BTREE,
  CONSTRAINT `jns_perawatan_ibfk_1` FOREIGN KEY (`kd_kategori`) REFERENCES `kategori_perawatan` (`kd_kategori`) ON UPDATE CASCADE,
  CONSTRAINT `jns_perawatan_ibfk_2` FOREIGN KEY (`kd_pj`) REFERENCES `penjab` (`kd_pj`) ON UPDATE CASCADE,
  CONSTRAINT `jns_perawatan_ibfk_3` FOREIGN KEY (`kd_poli`) REFERENCES `poliklinik` (`kd_poli`) ON UPDATE CASCADE
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

/*Table structure for table `jns_perawatan_inap` */

DROP TABLE IF EXISTS `jns_perawatan_inap`;

CREATE TABLE `jns_perawatan_inap` (
  `kd_jenis_prw` varchar(15) NOT NULL,
  `nm_perawatan` varchar(80) DEFAULT NULL,
  `kd_kategori` char(5) NOT NULL,
  `material` double DEFAULT NULL,
  `bhp` double NOT NULL,
  `tarif_tindakandr` double DEFAULT NULL,
  `tarif_tindakanpr` double DEFAULT NULL,
  `kso` double DEFAULT NULL,
  `menejemen` double DEFAULT NULL,
  `total_byrdr` double DEFAULT NULL,
  `total_byrpr` double DEFAULT NULL,
  `total_byrdrpr` double NOT NULL,
  `kd_pj` char(3) NOT NULL,
  `kd_bangsal` char(5) NOT NULL,
  `status` enum('0','1') NOT NULL,
  `kelas` enum('-','Kelas 1','Kelas 2','Kelas 3','Kelas Utama','Kelas VIP','Kelas VVIP') NOT NULL,
  PRIMARY KEY (`kd_jenis_prw`),
  KEY `kd_pj` (`kd_pj`),
  KEY `kd_bangsal` (`kd_bangsal`),
  KEY `kd_kategori` (`kd_kategori`),
  KEY `nm_perawatan` (`nm_perawatan`),
  KEY `material` (`material`),
  KEY `tarif_tindakandr` (`tarif_tindakandr`),
  KEY `tarif_tindakanpr` (`tarif_tindakanpr`),
  KEY `total_byrdr` (`total_byrdr`),
  KEY `total_byrpr` (`total_byrpr`),
  KEY `bhp` (`bhp`) USING BTREE,
  KEY `kso` (`kso`) USING BTREE,
  KEY `menejemen` (`menejemen`) USING BTREE,
  KEY `status` (`status`) USING BTREE,
  KEY `total_byrdrpr` (`total_byrdrpr`) USING BTREE,
  CONSTRAINT `jns_perawatan_inap_ibfk_7` FOREIGN KEY (`kd_kategori`) REFERENCES `kategori_perawatan` (`kd_kategori`) ON UPDATE CASCADE,
  CONSTRAINT `jns_perawatan_inap_ibfk_8` FOREIGN KEY (`kd_pj`) REFERENCES `penjab` (`kd_pj`) ON UPDATE CASCADE,
  CONSTRAINT `jns_perawatan_inap_ibfk_9` FOREIGN KEY (`kd_bangsal`) REFERENCES `bangsal` (`kd_bangsal`) ON DELETE CASCADE ON UPDATE CASCADE
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

/*Table structure for table `jns_perawatan_lab` */

DROP TABLE IF EXISTS `jns_perawatan_lab`;

CREATE TABLE `jns_perawatan_lab` (
  `kd_jenis_prw` varchar(15) NOT NULL,
  `nm_perawatan` varchar(120) DEFAULT NULL,
  `bagian_rs` double DEFAULT NULL,
  `bhp` double NOT NULL,
  `tarif_perujuk` double NOT NULL,
  `tarif_tindakan_dokter` double NOT NULL,
  `tarif_tindakan_petugas` double DEFAULT NULL,
  `kso` double DEFAULT NULL,
  `menejemen` double DEFAULT NULL,
  `total_byr` double DEFAULT NULL,
  `kd_pj` char(3) NOT NULL,
  `status` enum('0','1') NOT NULL,
  `kelas` enum('-','Rawat Jalan','Kelas 1','Kelas 2','Kelas 3','Kelas Utama','Kelas VIP','Kelas VVIP') NOT NULL,
  PRIMARY KEY (`kd_jenis_prw`),
  KEY `kd_pj` (`kd_pj`),
  KEY `nm_perawatan` (`nm_perawatan`),
  KEY `tarif_perujuk` (`tarif_perujuk`),
  KEY `tarif_tindakan_dokter` (`tarif_tindakan_dokter`),
  KEY `tarif_tindakan_petugas` (`tarif_tindakan_petugas`),
  KEY `total_byr` (`total_byr`),
  KEY `bagian_rs` (`bagian_rs`),
  KEY `bhp` (`bhp`) USING BTREE,
  KEY `kso` (`kso`) USING BTREE,
  KEY `menejemen` (`menejemen`) USING BTREE,
  KEY `status` (`status`) USING BTREE,
  CONSTRAINT `jns_perawatan_lab_ibfk_1` FOREIGN KEY (`kd_pj`) REFERENCES `penjab` (`kd_pj`) ON UPDATE CASCADE
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

/*Table structure for table `jns_perawatan_radiologi` */

DROP TABLE IF EXISTS `jns_perawatan_radiologi`;

CREATE TABLE `jns_perawatan_radiologi` (
  `kd_jenis_prw` varchar(15) NOT NULL,
  `nm_perawatan` varchar(80) DEFAULT NULL,
  `bagian_rs` double DEFAULT NULL,
  `bhp` double NOT NULL,
  `tarif_perujuk` double NOT NULL,
  `tarif_tindakan_dokter` double NOT NULL,
  `tarif_tindakan_petugas` double DEFAULT NULL,
  `kso` double DEFAULT NULL,
  `menejemen` double DEFAULT NULL,
  `total_byr` double DEFAULT NULL,
  `kd_pj` char(3) NOT NULL,
  `status` enum('0','1') NOT NULL,
  `kelas` enum('-','Rawat Jalan','Kelas 1','Kelas 2','Kelas 3','Kelas Utama','Kelas VIP','Kelas VVIP') NOT NULL,
  PRIMARY KEY (`kd_jenis_prw`),
  KEY `kd_pj` (`kd_pj`),
  KEY `nm_perawatan` (`nm_perawatan`),
  KEY `bagian_rs` (`bagian_rs`),
  KEY `tarif_perujuk` (`tarif_perujuk`),
  KEY `tarif_tindakan_dokter` (`tarif_tindakan_dokter`),
  KEY `tarif_tindakan_petugas` (`tarif_tindakan_petugas`),
  KEY `total_byr` (`total_byr`),
  KEY `bhp` (`bhp`) USING BTREE,
  KEY `kso` (`kso`) USING BTREE,
  KEY `menejemen` (`menejemen`) USING BTREE,
  KEY `status` (`status`) USING BTREE,
  CONSTRAINT `jns_perawatan_radiologi_ibfk_1` FOREIGN KEY (`kd_pj`) REFERENCES `penjab` (`kd_pj`) ON UPDATE CASCADE
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

/*Table structure for table `jns_perawatan_utd` */

DROP TABLE IF EXISTS `jns_perawatan_utd`;

CREATE TABLE `jns_perawatan_utd` (
  `kd_jenis_prw` varchar(15) NOT NULL DEFAULT '',
  `nm_perawatan` varchar(80) DEFAULT NULL,
  `bagian_rs` double DEFAULT NULL,
  `bhp` double DEFAULT NULL,
  `tarif_perujuk` double DEFAULT NULL,
  `tarif_tindakan_dokter` double DEFAULT NULL,
  `tarif_tindakan_petugas` double DEFAULT NULL,
  `kso` double DEFAULT NULL,
  `manajemen` double DEFAULT NULL,
  `total_byr` double DEFAULT NULL,
  `kd_pj` char(3) DEFAULT NULL,
  `status` enum('0','1') DEFAULT NULL,
  PRIMARY KEY (`kd_jenis_prw`),
  KEY `kd_pj` (`kd_pj`),
  CONSTRAINT `jns_perawatan_utd_ibfk_1` FOREIGN KEY (`kd_pj`) REFERENCES `penjab` (`kd_pj`) ON UPDATE CASCADE
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

/*Table structure for table `jumpasien` */

DROP TABLE IF EXISTS `jumpasien`;

CREATE TABLE `jumpasien` (
  `thn` year(4) NOT NULL,
  `bln` int(11) NOT NULL,
  `id` int(11) NOT NULL,
  `jml` int(11) NOT NULL,
  PRIMARY KEY (`thn`,`bln`,`id`),
  KEY `id` (`id`),
  KEY `jml` (`jml`),
  CONSTRAINT `jumpasien_ibfk_1` FOREIGN KEY (`id`) REFERENCES `pegawai` (`id`) ON UPDATE CASCADE
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

/*Table structure for table `jurnal` */

DROP TABLE IF EXISTS `jurnal`;

CREATE TABLE `jurnal` (
  `no_jurnal` varchar(20) NOT NULL,
  `no_bukti` varchar(20) DEFAULT NULL,
  `tgl_jurnal` date DEFAULT NULL,
  `jenis` enum('U','P') DEFAULT NULL,
  `keterangan` varchar(250) DEFAULT NULL,
  PRIMARY KEY (`no_jurnal`),
  KEY `no_bukti` (`no_bukti`),
  KEY `tgl_jurnal` (`tgl_jurnal`),
  KEY `jenis` (`jenis`),
  KEY `keterangan` (`keterangan`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

/*Table structure for table `kabupaten` */

DROP TABLE IF EXISTS `kabupaten`;

CREATE TABLE `kabupaten` (
  `kd_kab` int(11) NOT NULL AUTO_INCREMENT,
  `nm_kab` varchar(60) NOT NULL,
  PRIMARY KEY (`kd_kab`),
  UNIQUE KEY `nm_kab` (`nm_kab`)
) ENGINE=InnoDB AUTO_INCREMENT=2770 DEFAULT CHARSET=latin1;

/*Table structure for table `kamar` */

DROP TABLE IF EXISTS `kamar`;

CREATE TABLE `kamar` (
  `kd_kamar` varchar(15) NOT NULL,
  `kd_bangsal` char(5) DEFAULT NULL,
  `trf_kamar` double DEFAULT NULL,
  `status` enum('ISI','KOSONG','RUSAK','BOOKING') DEFAULT NULL,
  `kelas` enum('Kelas 1','Kelas 2','Kelas 3','Kelas Utama','Kelas VIP','Kelas VVIP','Rawat Khusus','Intensif','High Care','Isolasi') DEFAULT NULL,
  `statusdata` enum('0','1') DEFAULT NULL,
  PRIMARY KEY (`kd_kamar`),
  KEY `kd_bangsal` (`kd_bangsal`),
  KEY `trf_kamar` (`trf_kamar`),
  KEY `status` (`status`),
  KEY `kelas` (`kelas`),
  KEY `statusdata` (`statusdata`) USING BTREE,
  KEY `kd_kamar` (`kd_kamar`) USING BTREE,
  CONSTRAINT `kamar_ibfk_1` FOREIGN KEY (`kd_bangsal`) REFERENCES `bangsal` (`kd_bangsal`) ON DELETE CASCADE ON UPDATE CASCADE
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

/*Table structure for table `kamar_inap` */

DROP TABLE IF EXISTS `kamar_inap`;

CREATE TABLE `kamar_inap` (
  `no_rawat` varchar(17) NOT NULL DEFAULT '',
  `kd_kamar` varchar(15) NOT NULL,
  `trf_kamar` double DEFAULT NULL,
  `diagnosa_awal` varchar(200) DEFAULT NULL,
  `diagnosa_akhir` varchar(100) DEFAULT NULL,
  `tgl_masuk` date NOT NULL DEFAULT '0000-00-00',
  `jam_masuk` time NOT NULL DEFAULT '00:00:00',
  `tgl_keluar` date DEFAULT NULL,
  `jam_keluar` time DEFAULT NULL,
  `lama` double DEFAULT NULL,
  `ttl_biaya` double DEFAULT NULL,
  `stts_pulang` enum('Dirujuk','APS','Meninggal >= 48 Jam','Meninggal < 48 Jam','Sembuh/BLPL','Kabur','-','Pindah Kamar') NOT NULL,
  PRIMARY KEY (`no_rawat`,`tgl_masuk`,`jam_masuk`),
  KEY `no_rawat` (`no_rawat`),
  KEY `kd_kamar` (`kd_kamar`),
  KEY `diagnosa_awal` (`diagnosa_awal`),
  KEY `diagnosa_akhir` (`diagnosa_akhir`),
  KEY `tgl_keluar` (`tgl_keluar`),
  KEY `jam_keluar` (`jam_keluar`),
  KEY `lama` (`lama`),
  KEY `ttl_biaya` (`ttl_biaya`),
  KEY `stts_pulang` (`stts_pulang`),
  KEY `trf_kamar` (`trf_kamar`) USING BTREE,
  CONSTRAINT `kamar_inap_ibfk_2` FOREIGN KEY (`kd_kamar`) REFERENCES `kamar` (`kd_kamar`) ON DELETE CASCADE ON UPDATE CASCADE,
  CONSTRAINT `kamar_inap_ibfk_3` FOREIGN KEY (`no_rawat`) REFERENCES `reg_periksa` (`no_rawat`) ON DELETE CASCADE ON UPDATE CASCADE
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

/*Table structure for table `kasift` */

DROP TABLE IF EXISTS `kasift`;

CREATE TABLE `kasift` (
  `id` int(11) NOT NULL,
  `jmlks` bigint(20) NOT NULL,
  `bsr` double NOT NULL,
  PRIMARY KEY (`id`),
  KEY `jmlks` (`jmlks`),
  KEY `bsr` (`bsr`),
  CONSTRAINT `kasift_ibfk_1` FOREIGN KEY (`id`) REFERENCES `pegawai` (`id`) ON DELETE CASCADE ON UPDATE CASCADE
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

/*Table structure for table `katalog_diagnosa_gizi` */

DROP TABLE IF EXISTS `katalog_diagnosa_gizi`;

CREATE TABLE `katalog_diagnosa_gizi` (
  `kd_diagnosa_gizi` varchar(10) NOT NULL,
  `deskripsi_diagnosa` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`kd_diagnosa_gizi`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

/*Table structure for table `kategori_barang` */

DROP TABLE IF EXISTS `kategori_barang`;

CREATE TABLE `kategori_barang` (
  `kode` char(4) NOT NULL,
  `nama` varchar(30) DEFAULT NULL,
  PRIMARY KEY (`kode`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1 ROW_FORMAT=COMPACT;

/*Table structure for table `kategori_pemasukan_lain` */

DROP TABLE IF EXISTS `kategori_pemasukan_lain`;

CREATE TABLE `kategori_pemasukan_lain` (
  `kode_kategori` varchar(5) NOT NULL,
  `nama_kategori` varchar(40) DEFAULT NULL,
  `kd_rek` varchar(15) DEFAULT NULL,
  `kd_rek2` varchar(15) DEFAULT NULL,
  PRIMARY KEY (`kode_kategori`),
  KEY `kd_rek` (`kd_rek`),
  KEY `kd_rek2` (`kd_rek2`),
  CONSTRAINT `kategori_pemasukan_lain_ibfk_1` FOREIGN KEY (`kd_rek`) REFERENCES `rekening` (`kd_rek`) ON UPDATE CASCADE,
  CONSTRAINT `kategori_pemasukan_lain_ibfk_2` FOREIGN KEY (`kd_rek2`) REFERENCES `rekening` (`kd_rek`) ON UPDATE CASCADE
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

/*Table structure for table `kategori_pengeluaran_harian` */

DROP TABLE IF EXISTS `kategori_pengeluaran_harian`;

CREATE TABLE `kategori_pengeluaran_harian` (
  `kode_kategori` varchar(5) NOT NULL,
  `nama_kategori` varchar(40) DEFAULT NULL,
  `kd_rek` varchar(15) DEFAULT NULL,
  `kd_rek2` varchar(15) DEFAULT NULL,
  PRIMARY KEY (`kode_kategori`),
  KEY `kd_rek` (`kd_rek`),
  KEY `kd_rek2` (`kd_rek2`),
  CONSTRAINT `kategori_pengeluaran_harian_ibfk_1` FOREIGN KEY (`kd_rek`) REFERENCES `rekening` (`kd_rek`) ON UPDATE CASCADE,
  CONSTRAINT `kategori_pengeluaran_harian_ibfk_2` FOREIGN KEY (`kd_rek2`) REFERENCES `rekening` (`kd_rek`) ON UPDATE CASCADE
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

/*Table structure for table `kategori_penyakit` */

DROP TABLE IF EXISTS `kategori_penyakit`;

CREATE TABLE `kategori_penyakit` (
  `kd_ktg` varchar(8) NOT NULL,
  `nm_kategori` varchar(30) DEFAULT NULL,
  `ciri_umum` varchar(200) DEFAULT NULL,
  PRIMARY KEY (`kd_ktg`),
  KEY `nm_kategori` (`nm_kategori`),
  KEY `ciri_umum` (`ciri_umum`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

/*Table structure for table `kategori_perawatan` */

DROP TABLE IF EXISTS `kategori_perawatan`;

CREATE TABLE `kategori_perawatan` (
  `kd_kategori` char(5) NOT NULL,
  `nm_kategori` varchar(30) DEFAULT NULL,
  `urut` int(11) DEFAULT NULL,
  PRIMARY KEY (`kd_kategori`),
  KEY `nm_kategori` (`nm_kategori`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

/*Table structure for table `keanggotaan` */

DROP TABLE IF EXISTS `keanggotaan`;

CREATE TABLE `keanggotaan` (
  `id` int(11) NOT NULL,
  `koperasi` char(5) NOT NULL,
  `jamsostek` char(5) NOT NULL,
  `bpjs` char(5) NOT NULL,
  PRIMARY KEY (`id`),
  KEY `koperasi` (`koperasi`),
  KEY `jamsostek` (`jamsostek`),
  KEY `bpjs` (`bpjs`),
  CONSTRAINT `keanggotaan_ibfk_3` FOREIGN KEY (`id`) REFERENCES `pegawai` (`id`) ON DELETE CASCADE ON UPDATE CASCADE,
  CONSTRAINT `keanggotaan_ibfk_4` FOREIGN KEY (`koperasi`) REFERENCES `koperasi` (`stts`) ON DELETE CASCADE ON UPDATE CASCADE,
  CONSTRAINT `keanggotaan_ibfk_5` FOREIGN KEY (`jamsostek`) REFERENCES `jamsostek` (`stts`) ON DELETE CASCADE ON UPDATE CASCADE,
  CONSTRAINT `keanggotaan_ibfk_6` FOREIGN KEY (`bpjs`) REFERENCES `bpjs` (`stts`) ON DELETE CASCADE ON UPDATE CASCADE
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

/*Table structure for table `kecamatan` */

DROP TABLE IF EXISTS `kecamatan`;

CREATE TABLE `kecamatan` (
  `kd_kec` int(11) NOT NULL AUTO_INCREMENT,
  `nm_kec` varchar(60) NOT NULL,
  PRIMARY KEY (`kd_kec`),
  UNIQUE KEY `nm_kec` (`nm_kec`)
) ENGINE=InnoDB AUTO_INCREMENT=27768 DEFAULT CHARSET=latin1;

/*Table structure for table `kelengkapan_booking_sep_bpjs` */

DROP TABLE IF EXISTS `kelengkapan_booking_sep_bpjs`;

CREATE TABLE `kelengkapan_booking_sep_bpjs` (
  `tglrujukan` date DEFAULT NULL,
  `no_rujukan` varchar(40) DEFAULT NULL,
  `kdppkrujukan` varchar(12) DEFAULT NULL,
  `nmppkrujukan` varchar(200) DEFAULT NULL,
  `kdppkpelayanan` varchar(12) DEFAULT NULL,
  `nmppkpelayanan` varchar(200) DEFAULT NULL,
  `jnspelayanan` enum('1','2') DEFAULT NULL,
  `catatan` varchar(255) DEFAULT NULL,
  `diagawal` varchar(10) DEFAULT NULL,
  `nmdiagnosaawal` varchar(400) DEFAULT NULL,
  `kdpolitujuan` varchar(15) DEFAULT NULL,
  `nmpolitujuan` varchar(50) DEFAULT NULL,
  `klsrawat` enum('1','2','3') DEFAULT NULL,
  `lakalantas` enum('0','1') DEFAULT NULL,
  `lokasilaka` varchar(100) DEFAULT NULL,
  `user` varchar(25) DEFAULT NULL,
  `nomr` varchar(15) DEFAULT NULL,
  `nama_pasien` varchar(100) DEFAULT NULL,
  `tanggal_lahir` date DEFAULT NULL,
  `peserta` varchar(100) DEFAULT NULL,
  `jkel` enum('LAKI-LAKI','PEREMPUAN','L','P') DEFAULT NULL,
  `no_kartu` varchar(25) DEFAULT NULL,
  `tglpulang` datetime DEFAULT NULL,
  `asal_rujukan` enum('1. Faskes 1','2. Faskes 2(RS)') NOT NULL,
  `eksekutif` enum('0. Tidak','1.Ya','0.Tidak','1. Ya') NOT NULL,
  `cob` enum('0. Tidak','1.Ya','0.Tidak','1. Ya') NOT NULL,
  `penjamin` varchar(15) NOT NULL,
  `notelep` varchar(13) NOT NULL,
  `katarak` enum('0. Tidak','1.Ya','0.Tidak','1. Ya') NOT NULL,
  `tglkkl` date NOT NULL,
  `keterangankkl` varchar(100) NOT NULL,
  `suplesi` enum('0. Tidak','1. Ya','0.Tidak','1.Ya') NOT NULL,
  `no_sep_suplesi` varchar(40) NOT NULL,
  `kdprop` varchar(10) NOT NULL,
  `nmprop` varchar(50) NOT NULL,
  `kdkab` varchar(10) NOT NULL,
  `nmkab` varchar(50) NOT NULL,
  `kdkec` varchar(10) NOT NULL,
  `nmkec` varchar(50) NOT NULL,
  `noskdp` varchar(20) NOT NULL,
  `kddpjp` varchar(10) NOT NULL,
  `nmdpdjp` varchar(100) NOT NULL,
  `kode_inacbg` varchar(14) DEFAULT NULL,
  `kd_booking` varchar(15) NOT NULL,
  `status_cetak_sep` enum('SUDAH','BELUM','GAGAL') DEFAULT NULL,
  `rujukan_masuknya` varchar(200) DEFAULT NULL,
  `no_rawat` varchar(17) DEFAULT NULL,
  PRIMARY KEY (`kd_booking`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1 ROW_FORMAT=COMPACT;

/*Table structure for table `kelurahan` */

DROP TABLE IF EXISTS `kelurahan`;

CREATE TABLE `kelurahan` (
  `kd_kel` int(11) NOT NULL AUTO_INCREMENT,
  `nm_kel` varchar(60) NOT NULL,
  PRIMARY KEY (`kd_kel`),
  UNIQUE KEY `nm_kel` (`nm_kel`)
) ENGINE=InnoDB AUTO_INCREMENT=146732 DEFAULT CHARSET=latin1;

/*Table structure for table `ketidakhadiran` */

DROP TABLE IF EXISTS `ketidakhadiran`;

CREATE TABLE `ketidakhadiran` (
  `tgl` date NOT NULL,
  `id` int(11) NOT NULL,
  `jns` enum('A','S','C','I') NOT NULL,
  `ktg` varchar(40) NOT NULL,
  `jml` int(10) DEFAULT NULL,
  PRIMARY KEY (`tgl`,`id`,`jns`),
  KEY `id` (`id`),
  KEY `ktg` (`ktg`),
  KEY `jml` (`jml`),
  CONSTRAINT `ketidakhadiran_ibfk_1` FOREIGN KEY (`id`) REFERENCES `pegawai` (`id`) ON UPDATE CASCADE
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

/*Table structure for table `kode_inacbg` */

DROP TABLE IF EXISTS `kode_inacbg`;

CREATE TABLE `kode_inacbg` (
  `INACBG` char(10) NOT NULL DEFAULT '',
  `DESCRIPTION_ORIGINAL` char(100) NOT NULL DEFAULT '',
  `D2` char(10) NOT NULL DEFAULT '',
  `DESKRIPSI_INGGRIS` char(100) NOT NULL DEFAULT '',
  `D3` char(10) NOT NULL DEFAULT '',
  `SEVERITY` char(10) NOT NULL DEFAULT '',
  `DESKRIPSI` char(100) NOT NULL DEFAULT '',
  `DESKRIPSI_PMK_59_2014` char(110) NOT NULL DEFAULT '',
  PRIMARY KEY (`INACBG`)
) ENGINE=MyISAM DEFAULT CHARSET=utf8;

/*Table structure for table `kodesatuan` */

DROP TABLE IF EXISTS `kodesatuan`;

CREATE TABLE `kodesatuan` (
  `kode_sat` char(4) NOT NULL,
  `satuan` varchar(30) DEFAULT NULL,
  PRIMARY KEY (`kode_sat`),
  KEY `satuan` (`satuan`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

/*Table structure for table `konver_sat` */

DROP TABLE IF EXISTS `konver_sat`;

CREATE TABLE `konver_sat` (
  `nilai` double DEFAULT NULL,
  `kode_sat` char(4) NOT NULL DEFAULT '',
  `nilai_konversi` double DEFAULT NULL,
  `sat_konversi` char(4) NOT NULL DEFAULT '',
  PRIMARY KEY (`kode_sat`,`sat_konversi`),
  KEY `kode_sat` (`kode_sat`),
  KEY `nilai` (`nilai`),
  KEY `nilai_konversi` (`nilai_konversi`),
  CONSTRAINT `konver_sat_ibfk_1` FOREIGN KEY (`kode_sat`) REFERENCES `kodesatuan` (`kode_sat`) ON DELETE CASCADE ON UPDATE CASCADE
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

/*Table structure for table `koperasi` */

DROP TABLE IF EXISTS `koperasi`;

CREATE TABLE `koperasi` (
  `stts` char(5) NOT NULL,
  `wajib` double NOT NULL,
  PRIMARY KEY (`stts`),
  KEY `wajib` (`wajib`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

/*Table structure for table `lis_error_log` */

DROP TABLE IF EXISTS `lis_error_log`;

CREATE TABLE `lis_error_log` (
  `errornum` int(100) NOT NULL AUTO_INCREMENT,
  `no_rawat` varchar(17) DEFAULT NULL,
  `sumber` varchar(30) DEFAULT NULL,
  `pesan` text DEFAULT NULL,
  `keterangan` text DEFAULT NULL,
  `waktu` datetime DEFAULT current_timestamp(),
  PRIMARY KEY (`errornum`)
) ENGINE=InnoDB AUTO_INCREMENT=528 DEFAULT CHARSET=latin1 ROW_FORMAT=COMPACT;

/*Table structure for table `lis_hasil_data_pasien` */

DROP TABLE IF EXISTS `lis_hasil_data_pasien`;

CREATE TABLE `lis_hasil_data_pasien` (
  `no_lab` varchar(45) NOT NULL,
  `waktu_reg_lab` datetime DEFAULT NULL,
  `pasien_no_rm` varchar(15) DEFAULT NULL,
  `pasien_nama` varchar(45) DEFAULT NULL,
  `pasien_jns_kelamin` varchar(10) DEFAULT NULL,
  `pasien_tgl_lahir` date DEFAULT NULL,
  `pasien_umur_tahun` int(11) DEFAULT 0,
  `pasien_umur_bulan` int(11) DEFAULT 0,
  `pasien_umur_hari` int(11) DEFAULT 0,
  `pasien_alamat` text DEFAULT NULL,
  `pasien_no_telp` varchar(15) DEFAULT NULL,
  `dokter_pengirim` varchar(45) DEFAULT NULL,
  `unit_asal` varchar(45) DEFAULT NULL,
  `penjamin` varchar(50) DEFAULT NULL,
  `diagnosa_awal` text DEFAULT NULL,
  `waktu_insert` datetime DEFAULT current_timestamp() ON UPDATE current_timestamp(),
  PRIMARY KEY (`no_lab`),
  CONSTRAINT `FK_lis_hasil_lis_reg` FOREIGN KEY (`no_lab`) REFERENCES `lis_reg` (`no_lab`) ON DELETE CASCADE ON UPDATE CASCADE
) ENGINE=InnoDB DEFAULT CHARSET=latin1 ROW_FORMAT=COMPACT;

/*Table structure for table `lis_hasil_periksa_lab` */

DROP TABLE IF EXISTS `lis_hasil_periksa_lab`;

CREATE TABLE `lis_hasil_periksa_lab` (
  `no_lab` varchar(45) NOT NULL,
  `kategori_pemeriksaan_nama` varchar(45) DEFAULT NULL,
  `kategori_pemeriksaan_no_urut` int(45) DEFAULT NULL,
  `sub_kategori_pemeriksaan_nama` varchar(45) DEFAULT NULL,
  `sub_kategori_pemeriksaan_no_urut` int(11) DEFAULT NULL,
  `pemeriksaan_kode` varchar(45) NOT NULL,
  `pemeriksaan_nama` varchar(45) DEFAULT NULL,
  `pemeriksaan_no_urut` int(11) DEFAULT NULL,
  `metode` varchar(45) DEFAULT NULL,
  `satuan` varchar(45) DEFAULT NULL,
  `nilai_hasil` varchar(50) DEFAULT NULL,
  `nilai_rujukan` varchar(50) DEFAULT NULL,
  `flag_kode` varchar(45) DEFAULT NULL,
  PRIMARY KEY (`no_lab`,`pemeriksaan_kode`),
  KEY `FK_lis_hasil_lis_reg` (`no_lab`) USING BTREE,
  CONSTRAINT `lis_hasil_periksa_lab_ibfk_1` FOREIGN KEY (`no_lab`) REFERENCES `lis_hasil_data_pasien` (`no_lab`) ON DELETE CASCADE ON UPDATE CASCADE
) ENGINE=InnoDB DEFAULT CHARSET=latin1 ROW_FORMAT=COMPACT;

/*Table structure for table `lis_reg` */

DROP TABLE IF EXISTS `lis_reg`;

CREATE TABLE `lis_reg` (
  `no_lab` varchar(45) NOT NULL,
  `no_rawat` varchar(17) NOT NULL,
  `tgl_periksa` date NOT NULL,
  `jam_periksa` time NOT NULL,
  `kd_dokter` varchar(20) NOT NULL,
  `unit_asal` varchar(50) DEFAULT NULL,
  `diagnosa_awal` text DEFAULT NULL,
  `ket_klinis` text DEFAULT NULL,
  `waktu_post` datetime DEFAULT current_timestamp(),
  `json` text DEFAULT NULL,
  PRIMARY KEY (`no_lab`),
  KEY `FK__reg_periksa` (`no_rawat`) USING BTREE,
  KEY `FK__dokter` (`kd_dokter`) USING BTREE,
  KEY `tgl_periksa` (`tgl_periksa`) USING BTREE,
  KEY `jam_periksa` (`jam_periksa`) USING BTREE,
  CONSTRAINT `FK__dokter` FOREIGN KEY (`kd_dokter`) REFERENCES `dokter` (`kd_dokter`) ON UPDATE CASCADE,
  CONSTRAINT `FK__reg_periksa` FOREIGN KEY (`no_rawat`) REFERENCES `reg_periksa` (`no_rawat`) ON UPDATE CASCADE
) ENGINE=InnoDB DEFAULT CHARSET=latin1 ROW_FORMAT=COMPACT;

/*Table structure for table `master_aps` */

DROP TABLE IF EXISTS `master_aps`;

CREATE TABLE `master_aps` (
  `kd_aps` int(11) NOT NULL AUTO_INCREMENT,
  `nm_alasan` varchar(255) DEFAULT NULL,
  `status` enum('0','1') DEFAULT NULL,
  PRIMARY KEY (`kd_aps`)
) ENGINE=InnoDB AUTO_INCREMENT=6 DEFAULT CHARSET=latin1;

/*Table structure for table `master_aturan_pakai` */

DROP TABLE IF EXISTS `master_aturan_pakai`;

CREATE TABLE `master_aturan_pakai` (
  `aturan` varchar(150) NOT NULL,
  PRIMARY KEY (`aturan`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

/*Table structure for table `master_berkas_digital` */

DROP TABLE IF EXISTS `master_berkas_digital`;

CREATE TABLE `master_berkas_digital` (
  `kode` varchar(10) NOT NULL,
  `nama` varchar(100) DEFAULT NULL,
  PRIMARY KEY (`kode`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1 ROW_FORMAT=COMPACT;

/*Table structure for table `master_diagnosa_ponek` */

DROP TABLE IF EXISTS `master_diagnosa_ponek`;

CREATE TABLE `master_diagnosa_ponek` (
  `kode_diagnosa` varchar(10) NOT NULL,
  `nm_diagnosa` varchar(100) DEFAULT NULL,
  `status_diagnosa` enum('0','1') DEFAULT NULL,
  `kode_icd10` varchar(10) DEFAULT NULL,
  PRIMARY KEY (`kode_diagnosa`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1 ROW_FORMAT=COMPACT;

/*Table structure for table `master_masalah_keperawatan` */

DROP TABLE IF EXISTS `master_masalah_keperawatan`;

CREATE TABLE `master_masalah_keperawatan` (
  `kode_masalah` varchar(3) NOT NULL,
  `nama_masalah` varchar(100) DEFAULT NULL,
  PRIMARY KEY (`kode_masalah`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

/*Table structure for table `master_nama_rujukan` */

DROP TABLE IF EXISTS `master_nama_rujukan`;

CREATE TABLE `master_nama_rujukan` (
  `kd_rujukan` int(11) NOT NULL AUTO_INCREMENT,
  `nama_rujukan` varchar(80) DEFAULT NULL,
  `status` enum('0','1') DEFAULT NULL,
  `kode_faskes_bpjs` varchar(17) DEFAULT NULL,
  `tipe_faskes` enum('Rumah Sakit','RS TNI/POLRI','Puskesmas','Praktek Dokter','Praktek Dokter Gigi','Klinik Pratama','Klinik Utama','Klinik TNI','Klinik POLRI','Apotek','Optik','Lainnya','Raza') DEFAULT NULL,
  `alamat_faskes` varchar(30) DEFAULT NULL,
  PRIMARY KEY (`kd_rujukan`)
) ENGINE=InnoDB AUTO_INCREMENT=828 DEFAULT CHARSET=latin1 ROW_FORMAT=COMPACT;

/*Table structure for table `master_tindakan` */

DROP TABLE IF EXISTS `master_tindakan`;

CREATE TABLE `master_tindakan` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `nama` varchar(50) NOT NULL,
  `jm` double NOT NULL,
  `jns` enum('Karyawan','dr umum','dr spesialis') NOT NULL,
  PRIMARY KEY (`id`),
  UNIQUE KEY `nama` (`nama`),
  KEY `jm` (`jm`),
  KEY `jns` (`jns`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

/*Table structure for table `master_triase_macam_kasus` */

DROP TABLE IF EXISTS `master_triase_macam_kasus`;

CREATE TABLE `master_triase_macam_kasus` (
  `kode_kasus` varchar(3) NOT NULL,
  `macam_kasus` varchar(150) NOT NULL,
  PRIMARY KEY (`kode_kasus`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

/*Table structure for table `master_triase_pemeriksaan` */

DROP TABLE IF EXISTS `master_triase_pemeriksaan`;

CREATE TABLE `master_triase_pemeriksaan` (
  `kode_pemeriksaan` varchar(3) NOT NULL,
  `nama_pemeriksaan` varchar(150) DEFAULT NULL,
  PRIMARY KEY (`kode_pemeriksaan`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

/*Table structure for table `master_triase_skala1` */

DROP TABLE IF EXISTS `master_triase_skala1`;

CREATE TABLE `master_triase_skala1` (
  `kode_pemeriksaan` varchar(3) NOT NULL,
  `kode_skala1` varchar(3) NOT NULL,
  `pengkajian_skala1` varchar(150) NOT NULL,
  PRIMARY KEY (`kode_skala1`),
  KEY `kode_pemeriksaan` (`kode_pemeriksaan`),
  CONSTRAINT `master_triase_skala1_ibfk_1` FOREIGN KEY (`kode_pemeriksaan`) REFERENCES `master_triase_pemeriksaan` (`kode_pemeriksaan`) ON DELETE CASCADE ON UPDATE CASCADE
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

/*Table structure for table `master_triase_skala2` */

DROP TABLE IF EXISTS `master_triase_skala2`;

CREATE TABLE `master_triase_skala2` (
  `kode_pemeriksaan` varchar(3) NOT NULL,
  `kode_skala2` varchar(3) NOT NULL,
  `pengkajian_skala2` varchar(150) NOT NULL,
  PRIMARY KEY (`kode_skala2`),
  KEY `kode_pemeriksaan` (`kode_pemeriksaan`),
  CONSTRAINT `master_triase_skala2_ibfk_1` FOREIGN KEY (`kode_pemeriksaan`) REFERENCES `master_triase_pemeriksaan` (`kode_pemeriksaan`) ON DELETE CASCADE ON UPDATE CASCADE
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

/*Table structure for table `master_triase_skala3` */

DROP TABLE IF EXISTS `master_triase_skala3`;

CREATE TABLE `master_triase_skala3` (
  `kode_pemeriksaan` varchar(3) NOT NULL,
  `kode_skala3` varchar(3) NOT NULL,
  `pengkajian_skala3` varchar(150) NOT NULL,
  PRIMARY KEY (`kode_skala3`),
  KEY `kode_pemeriksaan` (`kode_pemeriksaan`),
  CONSTRAINT `master_triase_skala3_ibfk_1` FOREIGN KEY (`kode_pemeriksaan`) REFERENCES `master_triase_pemeriksaan` (`kode_pemeriksaan`) ON DELETE CASCADE ON UPDATE CASCADE
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

/*Table structure for table `master_triase_skala4` */

DROP TABLE IF EXISTS `master_triase_skala4`;

CREATE TABLE `master_triase_skala4` (
  `kode_pemeriksaan` varchar(3) NOT NULL,
  `kode_skala4` varchar(3) NOT NULL,
  `pengkajian_skala4` varchar(150) NOT NULL,
  PRIMARY KEY (`kode_skala4`),
  KEY `kode_pemeriksaan` (`kode_pemeriksaan`),
  CONSTRAINT `master_triase_skala1_ibfk_4` FOREIGN KEY (`kode_pemeriksaan`) REFERENCES `master_triase_pemeriksaan` (`kode_pemeriksaan`) ON DELETE CASCADE ON UPDATE CASCADE
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

/*Table structure for table `master_triase_skala5` */

DROP TABLE IF EXISTS `master_triase_skala5`;

CREATE TABLE `master_triase_skala5` (
  `kode_pemeriksaan` varchar(3) NOT NULL,
  `kode_skala5` varchar(3) NOT NULL,
  `pengkajian_skala5` varchar(150) NOT NULL,
  PRIMARY KEY (`kode_skala5`),
  KEY `kode_pemeriksaan` (`kode_pemeriksaan`),
  CONSTRAINT `master_triase_skala1_ibfk_5` FOREIGN KEY (`kode_pemeriksaan`) REFERENCES `master_triase_pemeriksaan` (`kode_pemeriksaan`) ON DELETE CASCADE ON UPDATE CASCADE
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

/*Table structure for table `master_tunjangan_bulanan` */

DROP TABLE IF EXISTS `master_tunjangan_bulanan`;

CREATE TABLE `master_tunjangan_bulanan` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `nama` varchar(60) NOT NULL,
  `tnj` double NOT NULL,
  PRIMARY KEY (`id`),
  KEY `nama` (`nama`),
  KEY `tnj` (`tnj`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

/*Table structure for table `master_tunjangan_harian` */

DROP TABLE IF EXISTS `master_tunjangan_harian`;

CREATE TABLE `master_tunjangan_harian` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `nama` varchar(40) NOT NULL,
  `tnj` double NOT NULL,
  PRIMARY KEY (`id`),
  KEY `nama` (`nama`),
  KEY `tnj` (`tnj`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

/*Table structure for table `monev_asuhan_gizi` */

DROP TABLE IF EXISTS `monev_asuhan_gizi`;

CREATE TABLE `monev_asuhan_gizi` (
  `no_rawat` varchar(17) NOT NULL,
  `tgl_monev` date NOT NULL,
  `jam_monev` time DEFAULT NULL,
  `perkembangan_hasil_lab` varchar(255) DEFAULT NULL,
  `perkembangan_fisik_klinik` varchar(255) DEFAULT NULL,
  `perkembangan_diet` varchar(255) DEFAULT NULL,
  `evaluasi` varchar(255) DEFAULT NULL,
  `catatan_makanan_dari_luar` varchar(255) DEFAULT NULL,
  `edukasi_keamanan_pangan` enum('Ya','Tidak') DEFAULT NULL,
  `tgl_edukasi` date DEFAULT NULL,
  `menu_pilihan` enum('Ya','Tidak') DEFAULT NULL,
  `makanan_pokok` varchar(200) DEFAULT NULL,
  `lauk` varchar(200) DEFAULT NULL,
  `sayur` varchar(200) DEFAULT NULL,
  `buah_snack` varchar(200) DEFAULT NULL,
  `kd_kamar` varchar(15) DEFAULT NULL,
  PRIMARY KEY (`no_rawat`,`tgl_monev`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1 ROW_FORMAT=COMPACT;

/*Table structure for table `mutasi_berkas` */

DROP TABLE IF EXISTS `mutasi_berkas`;

CREATE TABLE `mutasi_berkas` (
  `no_rawat` varchar(18) NOT NULL,
  `status` enum('Sudah Dikirim','Sudah Diterima','Sudah Kembali','Tidak Ada') DEFAULT NULL,
  `dikirim` datetime DEFAULT NULL,
  `diterima` datetime DEFAULT NULL,
  `kembali` datetime DEFAULT NULL,
  `tidakada` datetime DEFAULT NULL,
  PRIMARY KEY (`no_rawat`),
  CONSTRAINT `mutasi_berkas_ibfk_1` FOREIGN KEY (`no_rawat`) REFERENCES `reg_periksa` (`no_rawat`) ON DELETE CASCADE ON UPDATE CASCADE
) ENGINE=InnoDB DEFAULT CHARSET=latin1 ROW_FORMAT=COMPACT;

/*Table structure for table `mutasibarang` */

DROP TABLE IF EXISTS `mutasibarang`;

CREATE TABLE `mutasibarang` (
  `kode_brng` varchar(15) NOT NULL,
  `jml` double NOT NULL,
  `kd_bangsaldari` char(5) NOT NULL,
  `kd_bangsalke` char(5) NOT NULL,
  `tanggal` date NOT NULL,
  `keterangan` varchar(60) NOT NULL,
  PRIMARY KEY (`kode_brng`,`kd_bangsaldari`,`kd_bangsalke`,`tanggal`),
  KEY `kd_bangsaldari` (`kd_bangsaldari`),
  KEY `kd_bangsalke` (`kd_bangsalke`),
  KEY `jml` (`jml`),
  KEY `keterangan` (`keterangan`),
  CONSTRAINT `mutasibarang_ibfk_1` FOREIGN KEY (`kode_brng`) REFERENCES `databarang` (`kode_brng`) ON DELETE CASCADE ON UPDATE CASCADE,
  CONSTRAINT `mutasibarang_ibfk_2` FOREIGN KEY (`kd_bangsaldari`) REFERENCES `bangsal` (`kd_bangsal`) ON DELETE CASCADE ON UPDATE CASCADE,
  CONSTRAINT `mutasibarang_ibfk_3` FOREIGN KEY (`kd_bangsalke`) REFERENCES `bangsal` (`kd_bangsal`) ON DELETE CASCADE ON UPDATE CASCADE
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

/*Table structure for table `nota_inap` */

DROP TABLE IF EXISTS `nota_inap`;

CREATE TABLE `nota_inap` (
  `no_rawat` varchar(17) NOT NULL DEFAULT '',
  `no_nota` varchar(17) DEFAULT NULL,
  `tanggal` date DEFAULT NULL,
  `jam` time DEFAULT NULL,
  `Uang_Muka` double DEFAULT NULL,
  PRIMARY KEY (`no_rawat`),
  UNIQUE KEY `no_nota` (`no_nota`),
  KEY `tanggal` (`tanggal`) USING BTREE,
  KEY `jam` (`jam`) USING BTREE,
  KEY `Uang_Muka` (`Uang_Muka`) USING BTREE,
  CONSTRAINT `nota_inap_ibfk_1` FOREIGN KEY (`no_rawat`) REFERENCES `reg_periksa` (`no_rawat`) ON UPDATE CASCADE
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

/*Table structure for table `nota_jalan` */

DROP TABLE IF EXISTS `nota_jalan`;

CREATE TABLE `nota_jalan` (
  `no_rawat` varchar(18) NOT NULL DEFAULT '',
  `no_nota` varchar(17) NOT NULL,
  `tanggal` date DEFAULT NULL,
  `jam` time DEFAULT NULL,
  `Jasa_Medik_Dokter_Tindakan_Ralan` double DEFAULT NULL,
  `Jasa_Medik_Paramedis_Tindakan_Ralan` double DEFAULT NULL,
  `KSO_Tindakan_Ralan` double DEFAULT NULL,
  `Jasa_Medik_Dokter_Laborat_Ralan` double DEFAULT NULL,
  `Jasa_Medik_Petugas_Laborat_Ralan` double DEFAULT NULL,
  `Kso_Laborat_Ralan` double DEFAULT NULL,
  `Persediaan_Laborat_Rawat_Jalan` double DEFAULT NULL,
  `Jasa_Medik_Dokter_Radiologi_Ralan` double DEFAULT NULL,
  `Jasa_Medik_Petugas_Radiologi_Ralan` double DEFAULT NULL,
  `Kso_Radiologi_Ralan` double DEFAULT NULL,
  `Persediaan_Radiologi_Rawat_Jalan` double DEFAULT NULL,
  `Obat_Rawat_Jalan` double DEFAULT NULL,
  `Jasa_Medik_Dokter_Operasi_Ralan` double DEFAULT NULL,
  `Jasa_Medik_Paramedis_Operasi_Ralan` double DEFAULT NULL,
  `Obat_Operasi_Ralan` double DEFAULT NULL,
  PRIMARY KEY (`no_rawat`,`no_nota`),
  UNIQUE KEY `no_nota` (`no_nota`) USING BTREE,
  KEY `tanggal` (`tanggal`) USING BTREE,
  KEY `jam` (`jam`) USING BTREE,
  KEY `Jasa_Medik_Dokter_Tindakan_Ralan` (`Jasa_Medik_Dokter_Tindakan_Ralan`) USING BTREE,
  KEY `Jasa_Medik_Paramedis_Tindakan_Ralan` (`Jasa_Medik_Paramedis_Tindakan_Ralan`) USING BTREE,
  CONSTRAINT `nota_jalan_ibfk_1` FOREIGN KEY (`no_rawat`) REFERENCES `reg_periksa` (`no_rawat`) ON UPDATE CASCADE
) ENGINE=InnoDB DEFAULT CHARSET=latin1 ROW_FORMAT=COMPACT;

/*Table structure for table `obat_penyakit` */

DROP TABLE IF EXISTS `obat_penyakit`;

CREATE TABLE `obat_penyakit` (
  `kd_penyakit` varchar(10) NOT NULL DEFAULT '',
  `kode_brng` varchar(15) NOT NULL,
  `referensi` varchar(60) DEFAULT NULL,
  PRIMARY KEY (`kd_penyakit`,`kode_brng`),
  KEY `kd_penyakit` (`kd_penyakit`),
  KEY `kd_obat` (`kode_brng`),
  CONSTRAINT `obat_penyakit_ibfk_1` FOREIGN KEY (`kd_penyakit`) REFERENCES `penyakit` (`kd_penyakit`) ON UPDATE CASCADE,
  CONSTRAINT `obat_penyakit_ibfk_2` FOREIGN KEY (`kode_brng`) REFERENCES `databarang` (`kode_brng`) ON DELETE CASCADE ON UPDATE CASCADE
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

/*Table structure for table `obatbhp_ok` */

DROP TABLE IF EXISTS `obatbhp_ok`;

CREATE TABLE `obatbhp_ok` (
  `kd_obat` varchar(15) NOT NULL,
  `nm_obat` varchar(50) NOT NULL,
  `kode_sat` char(4) NOT NULL,
  `hargasatuan` double NOT NULL,
  PRIMARY KEY (`kd_obat`),
  KEY `kode_sat` (`kode_sat`),
  KEY `nm_obat` (`nm_obat`),
  KEY `hargasatuan` (`hargasatuan`),
  CONSTRAINT `obatbhp_ok_ibfk_1` FOREIGN KEY (`kode_sat`) REFERENCES `kodesatuan` (`kode_sat`) ON UPDATE CASCADE
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

/*Table structure for table `operasi` */

DROP TABLE IF EXISTS `operasi`;

CREATE TABLE `operasi` (
  `no_rawat` varchar(18) NOT NULL,
  `tgl_operasi` datetime NOT NULL,
  `jenis_anasthesi` varchar(8) NOT NULL,
  `kategori` enum('-','Kecil','Sedang','Besar','Khusus I','Khusus II') DEFAULT NULL,
  `operator1` varchar(20) NOT NULL,
  `operator2` varchar(20) NOT NULL,
  `operator3` varchar(20) NOT NULL,
  `asisten_operator1` varchar(20) NOT NULL,
  `asisten_operator2` varchar(20) NOT NULL,
  `asisten_operator3` varchar(20) DEFAULT NULL,
  `instrumen` varchar(20) DEFAULT NULL,
  `dokter_anak` varchar(20) NOT NULL,
  `perawaat_resusitas` varchar(20) NOT NULL,
  `dokter_anestesi` varchar(20) NOT NULL,
  `asisten_anestesi` varchar(20) NOT NULL,
  `asisten_anestesi2` varchar(20) DEFAULT NULL,
  `bidan` varchar(20) NOT NULL,
  `bidan2` varchar(20) DEFAULT NULL,
  `bidan3` varchar(20) DEFAULT NULL,
  `perawat_luar` varchar(20) NOT NULL,
  `omloop` varchar(20) DEFAULT NULL,
  `omloop2` varchar(20) DEFAULT NULL,
  `omloop3` varchar(20) DEFAULT NULL,
  `omloop4` varchar(20) DEFAULT NULL,
  `omloop5` varchar(20) DEFAULT NULL,
  `dokter_pjanak` varchar(20) DEFAULT NULL,
  `dokter_umum` varchar(20) DEFAULT NULL,
  `kode_paket` varchar(15) NOT NULL,
  `biayaoperator1` double NOT NULL,
  `biayaoperator2` double NOT NULL,
  `biayaoperator3` double NOT NULL,
  `biayaasisten_operator1` double NOT NULL,
  `biayaasisten_operator2` double NOT NULL,
  `biayaasisten_operator3` double DEFAULT NULL,
  `biayainstrumen` double DEFAULT NULL,
  `biayadokter_anak` double NOT NULL,
  `biayaperawaat_resusitas` double NOT NULL,
  `biayadokter_anestesi` double NOT NULL,
  `biayaasisten_anestesi` double NOT NULL,
  `biayaasisten_anestesi2` double DEFAULT NULL,
  `biayabidan` double NOT NULL,
  `biayabidan2` double DEFAULT NULL,
  `biayabidan3` double DEFAULT NULL,
  `biayaperawat_luar` double NOT NULL,
  `biayaalat` double NOT NULL,
  `biayasewaok` double NOT NULL,
  `akomodasi` double DEFAULT NULL,
  `bagian_rs` double NOT NULL,
  `biaya_omloop` double DEFAULT NULL,
  `biaya_omloop2` double DEFAULT NULL,
  `biaya_omloop3` double DEFAULT NULL,
  `biaya_omloop4` double DEFAULT NULL,
  `biaya_omloop5` double DEFAULT NULL,
  `biayasarpras` double DEFAULT NULL,
  `biaya_dokter_pjanak` double DEFAULT NULL,
  `biaya_dokter_umum` double DEFAULT NULL,
  `status` enum('Ranap','Ralan') DEFAULT NULL,
  `stts_bayar` enum('BELUM','BAYAR') DEFAULT 'BELUM',
  `no_nota` varchar(17) DEFAULT NULL,
  `spesialisasi_operasi` enum('-','Bedah','Obstetrik & Ginekologi','Bedah Saraf','THT','Mata','Kulit & Kelamin','Gigi & Mulut','Bedah Anak','Kardiovaskuler','Bedah Orthopedi','Thorak','Digestive','Urologi','Onkologi','Lain-lain','Bedah Vaskuler') DEFAULT NULL,
  PRIMARY KEY (`no_rawat`,`tgl_operasi`,`kode_paket`),
  KEY `no_rawat` (`no_rawat`),
  KEY `operator1` (`operator1`),
  KEY `operator2` (`operator2`),
  KEY `operator3` (`operator3`),
  KEY `asisten_operator1` (`asisten_operator1`),
  KEY `asisten_operator2` (`asisten_operator2`),
  KEY `asisten_operator3` (`instrumen`),
  KEY `dokter_anak` (`dokter_anak`),
  KEY `perawaat_resusitas` (`perawaat_resusitas`),
  KEY `dokter_anestesi` (`dokter_anestesi`),
  KEY `asisten_anestesi` (`asisten_anestesi`),
  KEY `bidan` (`bidan`),
  KEY `perawat_luar` (`perawat_luar`),
  KEY `kode_paket` (`kode_paket`),
  KEY `operasi_ibfk_45` (`bidan2`),
  KEY `operasi_ibfk_46` (`bidan3`),
  KEY `operasi_ibfk_47` (`omloop`),
  KEY `operasi_ibfk_48` (`omloop2`),
  KEY `operasi_ibfk_49` (`omloop3`),
  KEY `dokter_pjanak` (`dokter_pjanak`),
  KEY `dokter_umum` (`dokter_umum`),
  KEY `asisten_operator3_2` (`asisten_operator3`) USING BTREE,
  KEY `asisten_anestesi2` (`asisten_anestesi2`) USING BTREE,
  KEY `omloop4` (`omloop4`) USING BTREE,
  KEY `omloop5` (`omloop5`) USING BTREE,
  CONSTRAINT `operasi_ibfk_31` FOREIGN KEY (`no_rawat`) REFERENCES `reg_periksa` (`no_rawat`) ON UPDATE CASCADE,
  CONSTRAINT `operasi_ibfk_32` FOREIGN KEY (`operator1`) REFERENCES `dokter` (`kd_dokter`) ON DELETE CASCADE ON UPDATE CASCADE,
  CONSTRAINT `operasi_ibfk_33` FOREIGN KEY (`operator2`) REFERENCES `dokter` (`kd_dokter`) ON DELETE CASCADE ON UPDATE CASCADE,
  CONSTRAINT `operasi_ibfk_34` FOREIGN KEY (`operator3`) REFERENCES `dokter` (`kd_dokter`) ON DELETE CASCADE ON UPDATE CASCADE,
  CONSTRAINT `operasi_ibfk_35` FOREIGN KEY (`asisten_operator1`) REFERENCES `petugas` (`nip`) ON DELETE CASCADE ON UPDATE CASCADE,
  CONSTRAINT `operasi_ibfk_36` FOREIGN KEY (`asisten_operator2`) REFERENCES `petugas` (`nip`) ON DELETE CASCADE ON UPDATE CASCADE,
  CONSTRAINT `operasi_ibfk_37` FOREIGN KEY (`instrumen`) REFERENCES `petugas` (`nip`) ON DELETE CASCADE ON UPDATE CASCADE,
  CONSTRAINT `operasi_ibfk_38` FOREIGN KEY (`dokter_anak`) REFERENCES `dokter` (`kd_dokter`) ON DELETE CASCADE ON UPDATE CASCADE,
  CONSTRAINT `operasi_ibfk_39` FOREIGN KEY (`perawaat_resusitas`) REFERENCES `petugas` (`nip`) ON DELETE CASCADE ON UPDATE CASCADE,
  CONSTRAINT `operasi_ibfk_40` FOREIGN KEY (`dokter_anestesi`) REFERENCES `dokter` (`kd_dokter`) ON UPDATE CASCADE,
  CONSTRAINT `operasi_ibfk_41` FOREIGN KEY (`asisten_anestesi`) REFERENCES `petugas` (`nip`) ON DELETE CASCADE ON UPDATE CASCADE,
  CONSTRAINT `operasi_ibfk_42` FOREIGN KEY (`bidan`) REFERENCES `petugas` (`nip`) ON DELETE CASCADE ON UPDATE CASCADE,
  CONSTRAINT `operasi_ibfk_43` FOREIGN KEY (`perawat_luar`) REFERENCES `petugas` (`nip`) ON DELETE CASCADE ON UPDATE CASCADE,
  CONSTRAINT `operasi_ibfk_44` FOREIGN KEY (`kode_paket`) REFERENCES `paket_operasi` (`kode_paket`) ON DELETE CASCADE ON UPDATE CASCADE,
  CONSTRAINT `operasi_ibfk_45` FOREIGN KEY (`bidan2`) REFERENCES `petugas` (`nip`) ON DELETE CASCADE ON UPDATE CASCADE,
  CONSTRAINT `operasi_ibfk_46` FOREIGN KEY (`bidan3`) REFERENCES `petugas` (`nip`) ON DELETE CASCADE ON UPDATE CASCADE,
  CONSTRAINT `operasi_ibfk_47` FOREIGN KEY (`omloop`) REFERENCES `petugas` (`nip`) ON DELETE CASCADE ON UPDATE CASCADE,
  CONSTRAINT `operasi_ibfk_48` FOREIGN KEY (`omloop2`) REFERENCES `petugas` (`nip`) ON DELETE CASCADE ON UPDATE CASCADE,
  CONSTRAINT `operasi_ibfk_49` FOREIGN KEY (`omloop3`) REFERENCES `petugas` (`nip`) ON DELETE CASCADE ON UPDATE CASCADE,
  CONSTRAINT `operasi_ibfk_50` FOREIGN KEY (`dokter_pjanak`) REFERENCES `dokter` (`kd_dokter`) ON DELETE CASCADE ON UPDATE CASCADE,
  CONSTRAINT `operasi_ibfk_51` FOREIGN KEY (`dokter_umum`) REFERENCES `dokter` (`kd_dokter`) ON DELETE CASCADE ON UPDATE CASCADE,
  CONSTRAINT `operasi_ibfk_52` FOREIGN KEY (`asisten_operator3`) REFERENCES `petugas` (`nip`) ON DELETE CASCADE ON UPDATE CASCADE,
  CONSTRAINT `operasi_ibfk_53` FOREIGN KEY (`asisten_anestesi2`) REFERENCES `petugas` (`nip`) ON DELETE CASCADE ON UPDATE CASCADE,
  CONSTRAINT `operasi_ibfk_54` FOREIGN KEY (`omloop4`) REFERENCES `petugas` (`nip`) ON DELETE CASCADE ON UPDATE CASCADE,
  CONSTRAINT `operasi_ibfk_55` FOREIGN KEY (`omloop5`) REFERENCES `petugas` (`nip`) ON DELETE CASCADE ON UPDATE CASCADE
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

/*Table structure for table `opname` */

DROP TABLE IF EXISTS `opname`;

CREATE TABLE `opname` (
  `kode_brng` varchar(15) NOT NULL,
  `h_beli` double DEFAULT NULL,
  `tanggal` date NOT NULL,
  `stok` int(11) NOT NULL,
  `real` int(11) NOT NULL,
  `selisih` int(11) NOT NULL,
  `nomihilang` double NOT NULL,
  `keterangan` varchar(60) NOT NULL,
  `kd_bangsal` char(5) NOT NULL,
  PRIMARY KEY (`kode_brng`,`tanggal`,`kd_bangsal`),
  KEY `kd_bangsal` (`kd_bangsal`),
  KEY `stok` (`stok`),
  KEY `real` (`real`),
  KEY `selisih` (`selisih`),
  KEY `nomihilang` (`nomihilang`),
  KEY `keterangan` (`keterangan`),
  CONSTRAINT `opname_ibfk_1` FOREIGN KEY (`kode_brng`) REFERENCES `databarang` (`kode_brng`) ON DELETE CASCADE ON UPDATE CASCADE,
  CONSTRAINT `opname_ibfk_2` FOREIGN KEY (`kd_bangsal`) REFERENCES `bangsal` (`kd_bangsal`) ON DELETE CASCADE ON UPDATE CASCADE
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

/*Table structure for table `paket_operasi` */

DROP TABLE IF EXISTS `paket_operasi`;

CREATE TABLE `paket_operasi` (
  `kode_paket` varchar(15) NOT NULL,
  `nm_perawatan` varchar(80) NOT NULL,
  `kategori` enum('Kebidanan','Operasi') DEFAULT NULL,
  `operator1` double NOT NULL,
  `operator2` double NOT NULL,
  `operator3` double NOT NULL,
  `asisten_operator1` double DEFAULT NULL,
  `asisten_operator2` double NOT NULL,
  `asisten_operator3` double DEFAULT NULL,
  `instrumen` double DEFAULT NULL,
  `dokter_anak` double NOT NULL,
  `perawaat_resusitas` double NOT NULL,
  `dokter_anestesi` double NOT NULL,
  `asisten_anestesi` double NOT NULL,
  `asisten_anestesi2` double DEFAULT NULL,
  `bidan` double NOT NULL,
  `bidan2` double DEFAULT NULL,
  `bidan3` double DEFAULT NULL,
  `perawat_luar` double NOT NULL,
  `sewa_ok` double NOT NULL,
  `alat` double NOT NULL,
  `akomodasi` double DEFAULT NULL,
  `bagian_rs` double NOT NULL,
  `omloop` double NOT NULL,
  `omloop2` double DEFAULT NULL,
  `omloop3` double DEFAULT NULL,
  `omloop4` double DEFAULT NULL,
  `omloop5` double DEFAULT NULL,
  `sarpras` double DEFAULT NULL,
  `dokter_pjanak` double DEFAULT NULL,
  `dokter_umum` double DEFAULT NULL,
  `kd_pj` char(3) DEFAULT NULL,
  `status` enum('0','1') DEFAULT NULL,
  `kelas` enum('-','Rawat Jalan','Kelas 1','Kelas 2','Kelas 3','Kelas Utama','Kelas VIP','Kelas VVIP') DEFAULT NULL,
  PRIMARY KEY (`kode_paket`),
  KEY `nm_perawatan` (`nm_perawatan`),
  KEY `operator1` (`operator1`),
  KEY `operator2` (`operator2`),
  KEY `operator3` (`operator3`),
  KEY `asisten_operator1` (`asisten_operator1`),
  KEY `asisten_operator2` (`asisten_operator2`),
  KEY `asisten_operator3` (`instrumen`),
  KEY `dokter_anak` (`dokter_anak`),
  KEY `perawat_resusitas` (`perawaat_resusitas`),
  KEY `dokter_anestasi` (`dokter_anestesi`),
  KEY `asisten_anastesi` (`asisten_anestesi`),
  KEY `bidan` (`bidan`),
  KEY `perawat_luar` (`perawat_luar`),
  KEY `sewa_ok` (`sewa_ok`),
  KEY `alat` (`alat`),
  KEY `sewa_vk` (`akomodasi`),
  KEY `bagian_rs` (`bagian_rs`),
  KEY `omloop` (`omloop`),
  KEY `kd_pj` (`kd_pj`),
  KEY `asisten_anestesi2` (`asisten_anestesi2`) USING BTREE,
  KEY `omloop2` (`omloop2`) USING BTREE,
  KEY `omloop3` (`omloop3`) USING BTREE,
  KEY `omloop4` (`omloop4`) USING BTREE,
  KEY `omloop5` (`omloop5`) USING BTREE,
  KEY `status` (`status`) USING BTREE,
  KEY `kategori` (`kategori`) USING BTREE,
  KEY `bidan2` (`bidan2`) USING BTREE,
  KEY `bidan3` (`bidan3`) USING BTREE,
  KEY `asisten_operator3_2` (`asisten_operator3`) USING BTREE,
  CONSTRAINT `paket_operasi_ibfk_1` FOREIGN KEY (`kd_pj`) REFERENCES `penjab` (`kd_pj`) ON DELETE CASCADE ON UPDATE CASCADE
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

/*Table structure for table `parkir` */

DROP TABLE IF EXISTS `parkir`;

CREATE TABLE `parkir` (
  `nip` varchar(20) DEFAULT NULL,
  `nomer_kartu` varchar(5) DEFAULT NULL,
  `kd_parkir` varchar(5) DEFAULT NULL,
  `no_kendaraan` varchar(15) NOT NULL DEFAULT '',
  `tgl_masuk` date NOT NULL DEFAULT '0000-00-00',
  `jam_masuk` time NOT NULL DEFAULT '00:00:00',
  `tgl_keluar` date DEFAULT NULL,
  `jam_keluar` time DEFAULT NULL,
  `lama_parkir` int(11) DEFAULT NULL,
  `ttl_biaya` double DEFAULT NULL,
  PRIMARY KEY (`no_kendaraan`,`tgl_masuk`,`jam_masuk`),
  KEY `kd_barcode` (`nomer_kartu`),
  KEY `kd_petugas` (`nip`),
  KEY `kd_parkir` (`kd_parkir`),
  CONSTRAINT `parkir_ibfk_1` FOREIGN KEY (`nip`) REFERENCES `petugas` (`nip`) ON DELETE CASCADE ON UPDATE CASCADE,
  CONSTRAINT `parkir_ibfk_2` FOREIGN KEY (`kd_parkir`) REFERENCES `parkir_jenis` (`kd_parkir`) ON DELETE CASCADE ON UPDATE CASCADE
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

/*Table structure for table `parkir_barcode` */

DROP TABLE IF EXISTS `parkir_barcode`;

CREATE TABLE `parkir_barcode` (
  `kode_barcode` varchar(15) NOT NULL,
  `nomer_kartu` varchar(5) NOT NULL,
  PRIMARY KEY (`kode_barcode`),
  UNIQUE KEY `no_card` (`nomer_kartu`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

/*Table structure for table `parkir_jenis` */

DROP TABLE IF EXISTS `parkir_jenis`;

CREATE TABLE `parkir_jenis` (
  `kd_parkir` char(5) NOT NULL,
  `jns_parkir` varchar(50) NOT NULL,
  `biaya` double NOT NULL,
  `jenis` enum('Harian','Jam') NOT NULL,
  PRIMARY KEY (`kd_parkir`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

/*Table structure for table `pasien` */

DROP TABLE IF EXISTS `pasien`;

CREATE TABLE `pasien` (
  `no_rkm_medis` varchar(15) NOT NULL,
  `nm_pasien` varchar(40) DEFAULT NULL,
  `no_ktp` varchar(20) DEFAULT NULL,
  `jk` enum('L','P') DEFAULT NULL,
  `tmp_lahir` varchar(15) DEFAULT NULL,
  `tgl_lahir` date DEFAULT NULL,
  `nm_ibu` varchar(40) NOT NULL,
  `alamat` varchar(200) DEFAULT NULL,
  `gol_darah` enum('A','B','O','AB','-') DEFAULT NULL,
  `pekerjaan` varchar(100) DEFAULT NULL,
  `stts_nikah` enum('BELUM MENIKAH','MENIKAH','JANDA','DUDA') DEFAULT NULL,
  `agama` varchar(12) DEFAULT NULL,
  `tgl_daftar` date DEFAULT NULL,
  `no_tlp` varchar(13) DEFAULT NULL,
  `umur` varchar(20) NOT NULL,
  `pnd` enum('TS','TK','SD','SMP','SMA','D1','D2','D3','D4','S1','S2','S3','-') NOT NULL,
  `keluarga` enum('AYAH','IBU','ISTRI','SUAMI','SAUDARA','ANAK','SEPUPU','PASIEN SENDIRI','PAMAN','BIBI','KAKEK','NENEK','TEMAN','TETANGGA','IPAR','BESAN','MENANTU','MERTUA','KEPONAKAN') DEFAULT NULL,
  `namakeluarga` varchar(50) NOT NULL,
  `kd_pj` char(3) NOT NULL,
  `no_peserta` varchar(25) DEFAULT NULL,
  `kd_kel` int(11) NOT NULL,
  `kd_kec` int(11) NOT NULL,
  `kd_kab` int(11) NOT NULL,
  `pekerjaanpj` varchar(15) NOT NULL,
  `alamatpj` varchar(100) NOT NULL,
  `kelurahanpj` varchar(60) NOT NULL,
  `kecamatanpj` varchar(60) NOT NULL,
  `kabupatenpj` varchar(60) NOT NULL,
  `tinggi_badan` varchar(5) DEFAULT NULL,
  `suku_bangsa` int(11) NOT NULL,
  `bahasa_pasien` int(11) NOT NULL,
  `alamat_domisili_pasien` varchar(200) DEFAULT NULL,
  `kd_kel_domisili_pasien` int(11) NOT NULL,
  `kd_kec_domisili_pasien` int(11) NOT NULL,
  `kd_kab_domisili_pasien` int(11) NOT NULL,
  PRIMARY KEY (`no_rkm_medis`),
  KEY `kd_pj` (`kd_pj`),
  KEY `kd_kec` (`kd_kec`),
  KEY `kd_kab` (`kd_kab`),
  KEY `nm_pasien` (`nm_pasien`),
  KEY `alamat` (`alamat`),
  KEY `kd_kel_2` (`kd_kel`),
  KEY `keluarga` (`keluarga`),
  KEY `no_ktp` (`no_ktp`),
  KEY `jk` (`jk`),
  KEY `tmp_lahir` (`tmp_lahir`),
  KEY `tgl_lahir` (`tgl_lahir`),
  KEY `nm_ibu` (`nm_ibu`),
  KEY `gol_darah` (`gol_darah`),
  KEY `pekerjaan` (`pekerjaan`),
  KEY `stts_nikah` (`stts_nikah`),
  KEY `agama` (`agama`),
  KEY `tgl_daftar` (`tgl_daftar`),
  KEY `no_tlp` (`no_tlp`),
  KEY `umur` (`umur`),
  KEY `pnd` (`pnd`),
  KEY `no_peserta` (`no_peserta`) USING BTREE,
  KEY `pekerjaanpj` (`pekerjaanpj`) USING BTREE,
  KEY `alamatpj` (`alamatpj`) USING BTREE,
  KEY `namakeluarga` (`namakeluarga`) USING BTREE,
  KEY `kelurahanpj` (`kelurahanpj`) USING BTREE,
  KEY `kecamatanpj` (`kecamatanpj`) USING BTREE,
  KEY `kabupatenpj` (`kabupatenpj`) USING BTREE,
  CONSTRAINT `pasien_ibfk_1` FOREIGN KEY (`kd_pj`) REFERENCES `penjab` (`kd_pj`) ON UPDATE CASCADE,
  CONSTRAINT `pasien_ibfk_2` FOREIGN KEY (`kd_kel`) REFERENCES `kelurahan` (`kd_kel`) ON UPDATE CASCADE,
  CONSTRAINT `pasien_ibfk_3` FOREIGN KEY (`kd_kec`) REFERENCES `kecamatan` (`kd_kec`) ON UPDATE CASCADE,
  CONSTRAINT `pasien_ibfk_4` FOREIGN KEY (`kd_kab`) REFERENCES `kabupaten` (`kd_kab`) ON UPDATE CASCADE
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

/*Table structure for table `pasien_bayi` */

DROP TABLE IF EXISTS `pasien_bayi`;

CREATE TABLE `pasien_bayi` (
  `no_rkm_medis` varchar(15) NOT NULL,
  `umur_ibu` varchar(8) NOT NULL,
  `nama_ayah` varchar(50) NOT NULL,
  `umur_ayah` varchar(8) NOT NULL,
  `berat_badan` varchar(20) NOT NULL,
  `panjang_badan` varchar(10) NOT NULL,
  `lingkar_kepala` varchar(10) NOT NULL,
  `proses_lahir` varchar(60) NOT NULL,
  `anakke` char(2) NOT NULL,
  `jam_lahir` time NOT NULL,
  `keterangan` varchar(50) NOT NULL,
  `diagnosa` varchar(60) DEFAULT NULL,
  `penyulit_kehamilan` varchar(60) DEFAULT NULL,
  `ketuban` varchar(60) DEFAULT NULL,
  `lingkar_perut` varchar(10) DEFAULT NULL,
  `lingkar_dada` varchar(10) DEFAULT NULL,
  `penolong` varchar(20) DEFAULT NULL,
  `no_skl` varchar(30) DEFAULT NULL,
  `kematian_perinatal` enum('-','Kelahiran mati','Mati neonatal < 7 hari') DEFAULT NULL,
  `sebab_kematian` enum('Asphyxia','Trauma Lahir','BBLR','Tetanus Neonatum','Kelainan Congenital','ISPA','Diare','Lain-lain','-') DEFAULT NULL,
  `asal_rujukan` enum('Rumah Sakit','Bidan','Non Medis','Puskesmas','Faskes lainnya','Non Rujukan') DEFAULT NULL,
  `dirujuk` enum('Tidak','Ya') DEFAULT NULL,
  `jenis_alamat` enum('Dalam Wilayah Kab. Banjar','Luar Wilayah','') DEFAULT NULL,
  `cara_lahir` enum('Spontan','Letak Sungsang','VE/Forcep','SC','') DEFAULT NULL,
  `jenis_penolong` enum('Dokter Spesialis','Bidan','') DEFAULT NULL,
  `apgus_skor_menit1` enum('0-3','4-7','8-10','') DEFAULT NULL,
  `apgus_skor_menit5` enum('0-3','4-7','8-10','') DEFAULT NULL,
  `apgus_skor_menit10` enum('0-3','4-7','8-10','') DEFAULT NULL,
  `asal_bayi` enum('Dalam RS','Luar RS','') DEFAULT NULL,
  `umur_kehamilan` enum('<=35','36-40','>=42','') DEFAULT NULL,
  `rawat_gabung_dg_ibu` enum('Ya','Tidak') DEFAULT NULL,
  `IMD` enum('Ya','Tidak') DEFAULT NULL,
  `KMC` enum('Ya','Tidak') DEFAULT NULL,
  `berat_badan_benar` int(4) NOT NULL,
  `no_rawat_ibu` varchar(17) DEFAULT NULL,
  PRIMARY KEY (`no_rkm_medis`),
  UNIQUE KEY `no_skl` (`no_skl`) USING BTREE,
  KEY `umur_ibu` (`umur_ibu`),
  KEY `umur_ayah` (`umur_ayah`),
  KEY `berat_badan` (`berat_badan`),
  KEY `panjang_badan` (`panjang_badan`),
  KEY `lingkar_kepala` (`lingkar_kepala`),
  KEY `proses_lahir` (`proses_lahir`),
  KEY `anakke` (`anakke`),
  KEY `jam_lahir` (`jam_lahir`),
  KEY `keterangan` (`keterangan`),
  KEY `penolong` (`penolong`) USING BTREE,
  CONSTRAINT `pasien_bayi_ibfk_1` FOREIGN KEY (`no_rkm_medis`) REFERENCES `pasien` (`no_rkm_medis`) ON DELETE CASCADE ON UPDATE CASCADE,
  CONSTRAINT `pasien_bayi_ibfk_2` FOREIGN KEY (`penolong`) REFERENCES `pegawai` (`nik`) ON DELETE CASCADE ON UPDATE CASCADE
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

/*Table structure for table `pasien_corona` */

DROP TABLE IF EXISTS `pasien_corona`;

CREATE TABLE `pasien_corona` (
  `no_pengenal` varchar(20) DEFAULT NULL,
  `no_rkm_medis` varchar(15) NOT NULL,
  `inisial` varchar(15) DEFAULT NULL,
  `nama_lengkap` varchar(40) DEFAULT NULL,
  `tgl_masuk` date NOT NULL,
  `kode_jk` varchar(1) DEFAULT NULL,
  `nama_jk` varchar(10) DEFAULT NULL,
  `tgl_lahir` date DEFAULT NULL,
  `kode_kewarganegaraan` varchar(5) DEFAULT NULL,
  `nama_kewarganegaraan` varchar(25) DEFAULT NULL,
  `kode_penularan` varchar(5) DEFAULT NULL,
  `sumber_penularan` varchar(40) DEFAULT NULL,
  `kd_kelurahan` varchar(15) DEFAULT NULL,
  `nm_kelurahan` varchar(200) DEFAULT NULL,
  `kd_kecamatan` varchar(10) DEFAULT NULL,
  `nm_kecamatan` varchar(20) DEFAULT NULL,
  `kd_kabupaten` varchar(6) DEFAULT NULL,
  `nm_kabupaten` varchar(20) DEFAULT NULL,
  `kd_propinsi` varchar(3) DEFAULT NULL,
  `nm_propinsi` varchar(20) DEFAULT NULL,
  `tgl_keluar` date DEFAULT NULL,
  `kode_statuskeluar` varchar(5) DEFAULT NULL,
  `nama_statuskeluar` varchar(40) DEFAULT NULL,
  `tgl_lapor` datetime DEFAULT NULL,
  `kode_statusrawat` varchar(5) DEFAULT NULL,
  `nama_statusrawat` varchar(40) DEFAULT NULL,
  `kode_statusisolasi` varchar(5) DEFAULT NULL,
  `nama_statusisolasi` varchar(100) DEFAULT NULL,
  `email` varchar(50) DEFAULT NULL,
  `notelp` varchar(40) DEFAULT NULL,
  `sebab_kematian` varchar(60) DEFAULT NULL,
  `kode_jenis_pasien` varchar(5) NOT NULL,
  `nama_jenis_pasien` varchar(40) NOT NULL,
  PRIMARY KEY (`no_rkm_medis`,`tgl_masuk`),
  KEY `tgl_masuk` (`tgl_masuk`) USING BTREE,
  KEY `tgl_lapor` (`tgl_lapor`) USING BTREE,
  CONSTRAINT `pasien_corona_ibfk_1` FOREIGN KEY (`no_rkm_medis`) REFERENCES `pasien` (`no_rkm_medis`) ON UPDATE CASCADE
) ENGINE=InnoDB DEFAULT CHARSET=latin1 ROW_FORMAT=COMPACT;

/*Table structure for table `pasien_mati` */

DROP TABLE IF EXISTS `pasien_mati`;

CREATE TABLE `pasien_mati` (
  `tanggal` date DEFAULT NULL,
  `jam` time DEFAULT NULL,
  `no_rkm_medis` varchar(15) NOT NULL DEFAULT '',
  `keterangan` varchar(100) DEFAULT NULL,
  `temp_meninggal` enum('-','Rumah Sakit','Puskesmas','Rumah Bersalin','Rumah Tempat Tinggal','Lain-lain (Termasuk Doa)','Tidak tahu','Dari luar Rumah Sakit') DEFAULT NULL,
  `icd1` varchar(20) DEFAULT NULL,
  `icd2` varchar(20) DEFAULT NULL,
  `icd3` varchar(20) DEFAULT NULL,
  `icd4` varchar(20) DEFAULT NULL,
  `unit_asal` varchar(50) DEFAULT NULL,
  `icd5` varchar(20) DEFAULT NULL,
  PRIMARY KEY (`no_rkm_medis`),
  CONSTRAINT `pasien_mati_ibfk_1` FOREIGN KEY (`no_rkm_medis`) REFERENCES `pasien` (`no_rkm_medis`) ON UPDATE CASCADE
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

/*Table structure for table `pegawai` */

DROP TABLE IF EXISTS `pegawai`;

CREATE TABLE `pegawai` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `nik` varchar(20) NOT NULL,
  `nama` varchar(50) NOT NULL,
  `jk` enum('Pria','Wanita') NOT NULL,
  `jbtn` varchar(25) NOT NULL,
  `jnj_jabatan` varchar(5) NOT NULL,
  `departemen` char(4) NOT NULL,
  `bidang` varchar(15) NOT NULL,
  `stts_wp` char(5) NOT NULL,
  `stts_kerja` char(3) NOT NULL,
  `npwp` varchar(15) NOT NULL,
  `pendidikan` varchar(80) NOT NULL,
  `gapok` double NOT NULL,
  `tmp_lahir` varchar(20) NOT NULL,
  `tgl_lahir` date NOT NULL,
  `alamat` varchar(60) NOT NULL,
  `kota` varchar(20) NOT NULL,
  `mulai_kerja` date NOT NULL,
  `ms_kerja` enum('<1','PT','FT>1') NOT NULL,
  `indexins` char(4) NOT NULL,
  `bpd` varchar(50) NOT NULL,
  `rekening` varchar(25) NOT NULL,
  `stts_aktif` enum('AKTIF','CUTI','KELUAR','TENAGA LUAR','PENSIUN') NOT NULL,
  `wajibmasuk` tinyint(4) NOT NULL,
  `pengurang` double NOT NULL,
  `indek` tinyint(4) NOT NULL,
  `mulai_kontrak` date DEFAULT NULL,
  `cuti_diambil` int(11) NOT NULL,
  `dankes` double NOT NULL,
  `no_ktp` varchar(20) NOT NULL,
  PRIMARY KEY (`id`,`nik`),
  UNIQUE KEY `nik_2` (`nik`),
  KEY `departemen` (`departemen`),
  KEY `bidang` (`bidang`),
  KEY `stts_wp` (`stts_wp`),
  KEY `stts_kerja` (`stts_kerja`),
  KEY `pendidikan` (`pendidikan`),
  KEY `indexins` (`indexins`),
  KEY `jnj_jabatan` (`jnj_jabatan`),
  KEY `bpd` (`bpd`),
  KEY `nama` (`nama`) USING BTREE,
  KEY `jbtn` (`jbtn`) USING BTREE,
  KEY `npwp` (`npwp`) USING BTREE,
  KEY `dankes` (`dankes`) USING BTREE,
  KEY `cuti_diambil` (`cuti_diambil`) USING BTREE,
  KEY `mulai_kontrak` (`mulai_kontrak`) USING BTREE,
  KEY `stts_aktif` (`stts_aktif`) USING BTREE,
  KEY `tmp_lahir` (`tmp_lahir`) USING BTREE,
  KEY `alamat` (`alamat`) USING BTREE,
  KEY `mulai_kerja` (`mulai_kerja`) USING BTREE,
  KEY `gapok` (`gapok`) USING BTREE,
  KEY `kota` (`kota`) USING BTREE,
  KEY `pengurang` (`pengurang`) USING BTREE,
  KEY `indek` (`indek`) USING BTREE,
  KEY `jk` (`jk`) USING BTREE,
  KEY `ms_kerja` (`ms_kerja`) USING BTREE,
  KEY `tgl_lahir` (`tgl_lahir`) USING BTREE,
  KEY `rekening` (`rekening`) USING BTREE,
  KEY `wajibmasuk` (`wajibmasuk`) USING BTREE,
  CONSTRAINT `pegawai_ibfk_1` FOREIGN KEY (`jnj_jabatan`) REFERENCES `jnj_jabatan` (`kode`) ON UPDATE CASCADE,
  CONSTRAINT `pegawai_ibfk_2` FOREIGN KEY (`departemen`) REFERENCES `departemen` (`dep_id`) ON UPDATE CASCADE,
  CONSTRAINT `pegawai_ibfk_3` FOREIGN KEY (`bidang`) REFERENCES `bidang` (`nama`) ON UPDATE CASCADE,
  CONSTRAINT `pegawai_ibfk_4` FOREIGN KEY (`stts_wp`) REFERENCES `stts_wp` (`stts`) ON UPDATE CASCADE,
  CONSTRAINT `pegawai_ibfk_5` FOREIGN KEY (`stts_kerja`) REFERENCES `stts_kerja` (`stts`) ON UPDATE CASCADE,
  CONSTRAINT `pegawai_ibfk_6` FOREIGN KEY (`pendidikan`) REFERENCES `pendidikan` (`tingkat`) ON UPDATE CASCADE,
  CONSTRAINT `pegawai_ibfk_7` FOREIGN KEY (`indexins`) REFERENCES `departemen` (`dep_id`) ON UPDATE CASCADE,
  CONSTRAINT `pegawai_ibfk_8` FOREIGN KEY (`bpd`) REFERENCES `bank` (`namabank`) ON UPDATE CASCADE
) ENGINE=InnoDB AUTO_INCREMENT=1179 DEFAULT CHARSET=latin1;

/*Table structure for table `pemasukan_lain` */

DROP TABLE IF EXISTS `pemasukan_lain`;

CREATE TABLE `pemasukan_lain` (
  `tanggal` date NOT NULL DEFAULT '0000-00-00',
  `kode_kategori` varchar(5) NOT NULL DEFAULT '',
  `besar` double DEFAULT NULL,
  `nip` varchar(20) DEFAULT NULL,
  `keterangan` longtext DEFAULT NULL,
  `jam_penerimaan` time NOT NULL,
  `no_transaksi` varchar(15) NOT NULL,
  `telah_terima_dari` varchar(150) DEFAULT NULL,
  `no_sep` varchar(40) DEFAULT NULL,
  `no_rkm_medis` varchar(15) DEFAULT NULL,
  `no_kartu` varchar(25) DEFAULT NULL,
  `no_rawat` varchar(17) DEFAULT NULL,
  `tgl_masuk` varchar(15) DEFAULT NULL,
  `tgl_pulang` varchar(15) DEFAULT NULL,
  `ruang_inap` varchar(100) DEFAULT NULL,
  `kode_inacbg` varchar(10) DEFAULT NULL,
  `trf_kls1` double DEFAULT NULL,
  `trf_kls2` double DEFAULT NULL,
  `trf_kls3` double DEFAULT NULL,
  `hak_kelas` varchar(2) DEFAULT NULL,
  `naik_kelas` varchar(20) DEFAULT NULL,
  `lm_rawat` varchar(3) DEFAULT NULL,
  `persen_tambahan` double DEFAULT NULL,
  `rumus_selisih_tarif` varchar(255) DEFAULT NULL,
  `total_byr` double DEFAULT NULL,
  `nominal_pajak_sewa` double DEFAULT NULL,
  `nominal_sewa` double DEFAULT NULL,
  PRIMARY KEY (`no_transaksi`),
  KEY `pemasukan_lain_ibfk_2` (`kode_kategori`),
  KEY `pemasukan_lain_ibfk_1` (`nip`),
  CONSTRAINT `pemasukan_lain_ibfk_1` FOREIGN KEY (`nip`) REFERENCES `petugas` (`nip`) ON DELETE CASCADE ON UPDATE CASCADE,
  CONSTRAINT `pemasukan_lain_ibfk_2` FOREIGN KEY (`kode_kategori`) REFERENCES `kategori_pemasukan_lain` (`kode_kategori`) ON DELETE CASCADE ON UPDATE CASCADE
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

/*Table structure for table `pembagian_akte` */

DROP TABLE IF EXISTS `pembagian_akte`;

CREATE TABLE `pembagian_akte` (
  `id` int(11) NOT NULL,
  `persen` double NOT NULL,
  PRIMARY KEY (`id`),
  CONSTRAINT `pembagian_akte_ibfk_1` FOREIGN KEY (`id`) REFERENCES `pegawai` (`id`) ON UPDATE CASCADE
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

/*Table structure for table `pembagian_resume` */

DROP TABLE IF EXISTS `pembagian_resume`;

CREATE TABLE `pembagian_resume` (
  `id` int(11) NOT NULL,
  `persen` double NOT NULL,
  PRIMARY KEY (`id`),
  CONSTRAINT `pembagian_resume_ibfk_1` FOREIGN KEY (`id`) REFERENCES `pegawai` (`id`) ON UPDATE CASCADE
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

/*Table structure for table `pembagian_tuslah` */

DROP TABLE IF EXISTS `pembagian_tuslah`;

CREATE TABLE `pembagian_tuslah` (
  `id` int(11) NOT NULL,
  `persen` double NOT NULL,
  PRIMARY KEY (`id`),
  CONSTRAINT `pembagian_tuslah_ibfk_1` FOREIGN KEY (`id`) REFERENCES `pegawai` (`id`) ON UPDATE CASCADE
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

/*Table structure for table `pembagian_warung` */

DROP TABLE IF EXISTS `pembagian_warung`;

CREATE TABLE `pembagian_warung` (
  `id` int(11) NOT NULL,
  `persen` double NOT NULL,
  PRIMARY KEY (`id`),
  CONSTRAINT `pembagian_warung_ibfk_1` FOREIGN KEY (`id`) REFERENCES `pegawai` (`id`) ON UPDATE CASCADE
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

/*Table structure for table `pembelian` */

DROP TABLE IF EXISTS `pembelian`;

CREATE TABLE `pembelian` (
  `no_faktur` varchar(20) NOT NULL,
  `kode_suplier` char(5) DEFAULT NULL,
  `nip` varchar(20) DEFAULT NULL,
  `tgl_beli` date DEFAULT NULL,
  `total1` double NOT NULL,
  `potongan` double NOT NULL,
  `total2` double NOT NULL,
  `ppn` double NOT NULL,
  `tagihan` double NOT NULL,
  `kd_bangsal` char(5) NOT NULL,
  `kd_rek` varchar(15) DEFAULT NULL,
  PRIMARY KEY (`no_faktur`),
  KEY `kode_suplier` (`kode_suplier`),
  KEY `nip` (`nip`),
  KEY `kd_bangsal` (`kd_bangsal`),
  KEY `pembelian_ibfk_4` (`kd_rek`),
  CONSTRAINT `pembelian_ibfk_1` FOREIGN KEY (`kode_suplier`) REFERENCES `datasuplier` (`kode_suplier`) ON DELETE CASCADE ON UPDATE CASCADE,
  CONSTRAINT `pembelian_ibfk_2` FOREIGN KEY (`nip`) REFERENCES `petugas` (`nip`) ON DELETE CASCADE ON UPDATE CASCADE,
  CONSTRAINT `pembelian_ibfk_3` FOREIGN KEY (`kd_bangsal`) REFERENCES `bangsal` (`kd_bangsal`) ON DELETE CASCADE ON UPDATE CASCADE,
  CONSTRAINT `pembelian_ibfk_4` FOREIGN KEY (`kd_rek`) REFERENCES `akun_bayar` (`kd_rek`) ON DELETE CASCADE ON UPDATE CASCADE
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

/*Table structure for table `pemeriksaan_ralan` */

DROP TABLE IF EXISTS `pemeriksaan_ralan`;

CREATE TABLE `pemeriksaan_ralan` (
  `no_rawat` varchar(18) NOT NULL,
  `tgl_perawatan` date DEFAULT NULL,
  `jam_rawat` time DEFAULT NULL,
  `suhu_tubuh` char(5) DEFAULT NULL,
  `tensi` char(7) NOT NULL,
  `nadi` char(3) DEFAULT NULL,
  `respirasi` char(3) DEFAULT NULL,
  `tinggi` char(5) DEFAULT NULL,
  `berat` char(5) DEFAULT NULL,
  `gcs` varchar(10) DEFAULT NULL,
  `keluhan` longtext DEFAULT NULL,
  `pemeriksaan` longtext DEFAULT NULL,
  `alergi` longtext DEFAULT NULL,
  `imun_ke` enum('-','1','2','3','4','5','6','7','8','10','11','12','13') DEFAULT NULL,
  `diagnosa` longtext DEFAULT NULL,
  `kd_dokter` varchar(20) DEFAULT NULL,
  `rencana_follow_up` longtext DEFAULT NULL,
  `rincian_tindakan` longtext DEFAULT NULL,
  `terapi` longtext DEFAULT NULL,
  PRIMARY KEY (`no_rawat`),
  KEY `no_rawat` (`no_rawat`),
  KEY `kd_dokter` (`kd_dokter`) USING BTREE,
  CONSTRAINT `pemeriksaan_ralan_ibfk_1` FOREIGN KEY (`no_rawat`) REFERENCES `reg_periksa` (`no_rawat`) ON DELETE CASCADE ON UPDATE CASCADE,
  CONSTRAINT `pemeriksaan_ralan_ibfk_2` FOREIGN KEY (`kd_dokter`) REFERENCES `dokter` (`kd_dokter`) ON DELETE CASCADE ON UPDATE CASCADE
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

/*Table structure for table `pemeriksaan_ralan_petugas` */

DROP TABLE IF EXISTS `pemeriksaan_ralan_petugas`;

CREATE TABLE `pemeriksaan_ralan_petugas` (
  `no_rawat` varchar(18) NOT NULL,
  `tgl_perawatan` date DEFAULT NULL,
  `jam_rawat` time DEFAULT NULL,
  `suhu_tubuh` char(5) DEFAULT NULL,
  `tensi` char(7) NOT NULL,
  `nadi` char(3) DEFAULT NULL,
  `respirasi` char(3) DEFAULT NULL,
  `tinggi` char(5) DEFAULT NULL,
  `berat` char(5) DEFAULT NULL,
  `gcs` varchar(10) DEFAULT NULL,
  `keluhan` longtext DEFAULT NULL,
  `pemeriksaan` longtext DEFAULT NULL,
  `alergi` longtext DEFAULT NULL,
  `imun_ke` enum('-','1','2','3','4','5','6','7','8','10','11','12','13') DEFAULT NULL,
  `diagnosa` longtext DEFAULT NULL,
  `kd_dokter` varchar(20) DEFAULT NULL,
  `rencana_follow_up` longtext DEFAULT NULL,
  `nip` varchar(20) DEFAULT NULL,
  `rincian_tindakan` longtext DEFAULT NULL,
  `terapi` longtext DEFAULT NULL,
  PRIMARY KEY (`no_rawat`),
  KEY `no_rawat` (`no_rawat`) USING BTREE,
  KEY `kd_dokter` (`kd_dokter`) USING BTREE,
  KEY `pemeriksaan_ralan_petugas_ibfk_3` (`nip`) USING BTREE,
  CONSTRAINT `pemeriksaan_ralan_petugas_ibfk_1` FOREIGN KEY (`no_rawat`) REFERENCES `reg_periksa` (`no_rawat`) ON DELETE CASCADE ON UPDATE CASCADE,
  CONSTRAINT `pemeriksaan_ralan_petugas_ibfk_2` FOREIGN KEY (`kd_dokter`) REFERENCES `dokter` (`kd_dokter`) ON DELETE CASCADE ON UPDATE CASCADE
) ENGINE=InnoDB DEFAULT CHARSET=latin1 ROW_FORMAT=COMPACT;

/*Table structure for table `pemeriksaan_ranap` */

DROP TABLE IF EXISTS `pemeriksaan_ranap`;

CREATE TABLE `pemeriksaan_ranap` (
  `no_rawat` varchar(17) NOT NULL,
  `tgl_perawatan` date NOT NULL,
  `jam_rawat` time NOT NULL,
  `suhu_tubuh` char(5) DEFAULT NULL,
  `tensi` char(7) NOT NULL,
  `nadi` char(3) DEFAULT NULL,
  `respirasi` char(3) DEFAULT NULL,
  `tinggi` char(5) DEFAULT NULL,
  `berat` char(5) DEFAULT NULL,
  `gcs` varchar(10) DEFAULT NULL,
  `keluhan` varchar(400) DEFAULT NULL,
  `pemeriksaan` varchar(400) DEFAULT NULL,
  `alergi` varchar(50) DEFAULT NULL,
  PRIMARY KEY (`no_rawat`,`tgl_perawatan`,`jam_rawat`),
  KEY `no_rawat` (`no_rawat`),
  CONSTRAINT `pemeriksaan_ranap_ibfk_1` FOREIGN KEY (`no_rawat`) REFERENCES `reg_periksa` (`no_rawat`) ON DELETE CASCADE ON UPDATE CASCADE
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

/*Table structure for table `pemesanan` */

DROP TABLE IF EXISTS `pemesanan`;

CREATE TABLE `pemesanan` (
  `no_faktur` varchar(30) NOT NULL,
  `no_order` varchar(30) NOT NULL,
  `kode_suplier` char(5) DEFAULT NULL,
  `nip` varchar(20) DEFAULT NULL,
  `tgl_pesan` date DEFAULT NULL,
  `tgl_faktur` date DEFAULT NULL,
  `tgl_tempo` date DEFAULT NULL,
  `total1` double NOT NULL,
  `potongan` double NOT NULL,
  `total2` double NOT NULL,
  `ppn` double NOT NULL,
  `tagihan` double NOT NULL,
  `kd_bangsal` char(5) NOT NULL,
  `status` enum('Sudah Dibayar','Belum Dibayar','Belum Lunas') DEFAULT NULL,
  PRIMARY KEY (`no_faktur`),
  KEY `kode_suplier` (`kode_suplier`),
  KEY `nip` (`nip`),
  KEY `kd_bangsal` (`kd_bangsal`),
  CONSTRAINT `pemesanan_ibfk_1` FOREIGN KEY (`kode_suplier`) REFERENCES `datasuplier` (`kode_suplier`) ON DELETE CASCADE ON UPDATE CASCADE,
  CONSTRAINT `pemesanan_ibfk_2` FOREIGN KEY (`nip`) REFERENCES `petugas` (`nip`) ON DELETE CASCADE ON UPDATE CASCADE,
  CONSTRAINT `pemesanan_ibfk_3` FOREIGN KEY (`kd_bangsal`) REFERENCES `bangsal` (`kd_bangsal`) ON DELETE CASCADE ON UPDATE CASCADE
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

/*Table structure for table `peminjaman_berkas` */

DROP TABLE IF EXISTS `peminjaman_berkas`;

CREATE TABLE `peminjaman_berkas` (
  `peminjam` varchar(60) NOT NULL,
  `id_ruang` varchar(5) NOT NULL,
  `no_rkm_medis` varchar(15) NOT NULL,
  `tgl_pinjam` date NOT NULL,
  `tgl_kembali` date NOT NULL,
  `nip` varchar(20) NOT NULL,
  `status_pinjam` enum('Masih Dipinjam','Sudah Kembali') NOT NULL,
  PRIMARY KEY (`peminjam`,`id_ruang`,`no_rkm_medis`,`tgl_pinjam`,`nip`),
  KEY `no_rkm_medis` (`no_rkm_medis`),
  KEY `nip` (`nip`),
  KEY `id_ruang` (`id_ruang`),
  CONSTRAINT `peminjaman_berkas_ibfk_1` FOREIGN KEY (`no_rkm_medis`) REFERENCES `pasien` (`no_rkm_medis`) ON UPDATE CASCADE,
  CONSTRAINT `peminjaman_berkas_ibfk_2` FOREIGN KEY (`nip`) REFERENCES `petugas` (`nip`) ON DELETE CASCADE ON UPDATE CASCADE,
  CONSTRAINT `peminjaman_berkas_ibfk_3` FOREIGN KEY (`id_ruang`) REFERENCES `inventaris_ruang` (`id_ruang`) ON DELETE CASCADE ON UPDATE CASCADE
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

/*Table structure for table `peminjaman_koperasi` */

DROP TABLE IF EXISTS `peminjaman_koperasi`;

CREATE TABLE `peminjaman_koperasi` (
  `id` int(11) NOT NULL,
  `tanggal` date NOT NULL,
  `pinjaman` double NOT NULL,
  `banyak_angsur` int(11) NOT NULL,
  `pokok` double NOT NULL,
  `jasa` double NOT NULL,
  `status` enum('Lunas','Belum Lunas') NOT NULL,
  PRIMARY KEY (`id`,`tanggal`),
  CONSTRAINT `peminjaman_koperasi_ibfk_1` FOREIGN KEY (`id`) REFERENCES `pegawai` (`id`) ON UPDATE CASCADE
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

/*Table structure for table `pendaftaran_lab_rujukan` */

DROP TABLE IF EXISTS `pendaftaran_lab_rujukan`;

CREATE TABLE `pendaftaran_lab_rujukan` (
  `no_lab` varchar(17) NOT NULL,
  `tgl_periksa` date NOT NULL,
  `jam` time NOT NULL,
  `dokter_perujuk` varchar(40) NOT NULL,
  `nm_pasien` varchar(40) DEFAULT NULL,
  `no_ktp` varchar(20) DEFAULT NULL,
  `jk` enum('L','P') DEFAULT NULL,
  `tmp_lahir` varchar(15) DEFAULT NULL,
  `tgl_lahir` date DEFAULT NULL,
  `umur` varchar(20) NOT NULL,
  `alamat` varchar(200) DEFAULT NULL,
  `gol_darah` enum('A','B','O','AB','-') DEFAULT NULL,
  `pekerjaan` varchar(15) DEFAULT NULL,
  `stts_nikah` enum('SINGLE','MENIKAH','JANDA','DUDHA','JOMBLO') DEFAULT NULL,
  `agama` varchar(12) DEFAULT NULL,
  `no_tlp` varchar(13) DEFAULT NULL,
  `pnd` enum('TS','TK','SD','SMP','SMA','D1','D2','D3','D4','S1','S2','S3','-') DEFAULT NULL,
  `nip` varchar(20) NOT NULL,
  `kd_dokter` varchar(20) NOT NULL,
  `status` enum('Sudah Dibayar','Belum Dibayar') DEFAULT NULL,
  `kd_rek` varchar(15) DEFAULT NULL,
  PRIMARY KEY (`no_lab`),
  KEY `nip` (`nip`),
  KEY `kd_dokter` (`kd_dokter`),
  KEY `kd_rek` (`kd_rek`),
  CONSTRAINT `pendaftaran_lab_rujukan_ibfk_1` FOREIGN KEY (`nip`) REFERENCES `petugas` (`nip`) ON DELETE CASCADE ON UPDATE CASCADE,
  CONSTRAINT `pendaftaran_lab_rujukan_ibfk_2` FOREIGN KEY (`kd_dokter`) REFERENCES `dokter` (`kd_dokter`) ON DELETE CASCADE ON UPDATE CASCADE,
  CONSTRAINT `pendaftaran_lab_rujukan_ibfk_3` FOREIGN KEY (`kd_rek`) REFERENCES `rekening` (`kd_rek`) ON UPDATE CASCADE
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

/*Table structure for table `pendidikan` */

DROP TABLE IF EXISTS `pendidikan`;

CREATE TABLE `pendidikan` (
  `tingkat` varchar(80) NOT NULL,
  `indek` tinyint(4) NOT NULL,
  `gapok1` double NOT NULL,
  `kenaikan` double NOT NULL,
  `maksimal` int(11) NOT NULL,
  PRIMARY KEY (`tingkat`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

/*Table structure for table `pengaduan` */

DROP TABLE IF EXISTS `pengaduan`;

CREATE TABLE `pengaduan` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `date_time` datetime NOT NULL,
  `username` varchar(255) CHARACTER SET utf8 COLLATE utf8_unicode_ci NOT NULL,
  `message` varchar(255) CHARACTER SET utf8 COLLATE utf8_unicode_ci NOT NULL,
  `username_penerima` varchar(255) NOT NULL,
  `status` enum('-','read','unread') NOT NULL,
  PRIMARY KEY (`id`)
) ENGINE=MyISAM DEFAULT CHARSET=latin1 ROW_FORMAT=DYNAMIC;

/*Table structure for table `pengeluaran_harian` */

DROP TABLE IF EXISTS `pengeluaran_harian`;

CREATE TABLE `pengeluaran_harian` (
  `tanggal` datetime NOT NULL DEFAULT '0000-00-00 00:00:00',
  `kode_kategori` varchar(5) DEFAULT NULL,
  `biaya` double NOT NULL,
  `nip` varchar(20) DEFAULT NULL,
  `keterangan` varchar(50) NOT NULL DEFAULT '',
  PRIMARY KEY (`tanggal`,`keterangan`),
  KEY `nip` (`nip`),
  KEY `pengeluaran_harian_ibfk_2` (`kode_kategori`),
  CONSTRAINT `pengeluaran_harian_ibfk_1` FOREIGN KEY (`nip`) REFERENCES `petugas` (`nip`) ON DELETE CASCADE ON UPDATE CASCADE,
  CONSTRAINT `pengeluaran_harian_ibfk_2` FOREIGN KEY (`kode_kategori`) REFERENCES `kategori_pengeluaran_harian` (`kode_kategori`) ON DELETE CASCADE ON UPDATE CASCADE
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

/*Table structure for table `pengurangan_biaya` */

DROP TABLE IF EXISTS `pengurangan_biaya`;

CREATE TABLE `pengurangan_biaya` (
  `no_rawat` varchar(18) NOT NULL DEFAULT '',
  `nama_pengurangan` varchar(255) NOT NULL,
  `besar_pengurangan` double DEFAULT NULL,
  PRIMARY KEY (`no_rawat`,`nama_pengurangan`),
  CONSTRAINT `pengurangan_biaya_ibfk_1` FOREIGN KEY (`no_rawat`) REFERENCES `reg_periksa` (`no_rawat`) ON UPDATE CASCADE
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

/*Table structure for table `penilaian_awal_keperawatan_ralan` */

DROP TABLE IF EXISTS `penilaian_awal_keperawatan_ralan`;

CREATE TABLE `penilaian_awal_keperawatan_ralan` (
  `no_rawat` varchar(17) NOT NULL,
  `tanggal` datetime NOT NULL,
  `informasi` enum('Autoanamnesis','Alloanamnesis') NOT NULL,
  `td` char(5) NOT NULL DEFAULT '',
  `nadi` char(5) NOT NULL DEFAULT '',
  `rr` char(5) NOT NULL,
  `suhu` char(5) NOT NULL DEFAULT '',
  `gcs` char(5) NOT NULL,
  `bb` char(5) NOT NULL DEFAULT '',
  `tb` char(5) NOT NULL DEFAULT '',
  `bmi` char(5) NOT NULL,
  `keluhan_utama` varchar(150) NOT NULL DEFAULT '',
  `rpd` varchar(100) NOT NULL DEFAULT '',
  `rpk` varchar(100) NOT NULL,
  `rpo` varchar(100) NOT NULL,
  `alergi` varchar(25) NOT NULL DEFAULT '',
  `alat_bantu` enum('Tidak','Ya') NOT NULL,
  `ket_bantu` varchar(50) NOT NULL DEFAULT '',
  `prothesa` enum('Tidak','Ya') NOT NULL,
  `ket_pro` varchar(50) NOT NULL,
  `adl` enum('Mandiri','Dibantu') NOT NULL,
  `status_psiko` enum('Tenang','Takut','Cemas','Depresi','Lain-lain') NOT NULL,
  `ket_psiko` varchar(70) NOT NULL,
  `hub_keluarga` enum('Baik','Tidak Baik') NOT NULL,
  `tinggal_dengan` enum('Sendiri','Orang Tua','Suami / Istri','Lainnya') NOT NULL,
  `ket_tinggal` varchar(40) NOT NULL,
  `ekonomi` enum('Baik','Cukup','Kurang') NOT NULL,
  `budaya` enum('Tidak Ada','Ada') NOT NULL,
  `ket_budaya` varchar(50) NOT NULL,
  `edukasi` enum('Pasien','Keluarga') NOT NULL,
  `ket_edukasi` varchar(50) NOT NULL,
  `berjalan_a` enum('Ya','Tidak') NOT NULL,
  `berjalan_b` enum('Ya','Tidak') NOT NULL,
  `berjalan_c` enum('Ya','Tidak') NOT NULL,
  `hasil` enum('Tidak beresiko (tidak ditemukan a dan b)','Resiko rendah (ditemukan a/b)','Resiko tinggi (ditemukan a dan b)') NOT NULL,
  `lapor` enum('Ya','Tidak') NOT NULL,
  `ket_lapor` varchar(15) NOT NULL,
  `sg1` enum('Tidak','Tidak Yakin','Ya, 1-5 Kg','Ya, 6-10 Kg','Ya, 11-15 Kg','Ya, >15 Kg') NOT NULL,
  `nilai1` enum('0','1','2','3','4') NOT NULL,
  `sg2` enum('Ya','Tidak') NOT NULL,
  `nilai2` enum('0','1') NOT NULL,
  `total_hasil` tinyint(4) NOT NULL,
  `nyeri` enum('Tidak Ada Nyeri','Nyeri Akut','Nyeri Kronis') NOT NULL,
  `provokes` enum('Proses Penyakit','Benturan','Lain-lain') NOT NULL,
  `ket_provokes` varchar(40) NOT NULL,
  `quality` enum('Seperti Tertusuk','Berdenyut','Teriris','Tertindih','Tertiban','Lain-lain') NOT NULL,
  `ket_quality` varchar(50) NOT NULL,
  `lokasi` varchar(50) NOT NULL,
  `menyebar` enum('Tidak','Ya') NOT NULL,
  `skala_nyeri` enum('0','1','2','3','4','5','6','7','8','9','10') NOT NULL,
  `durasi` varchar(25) NOT NULL,
  `nyeri_hilang` enum('Istirahat','Medengar Musik','Minum Obat') NOT NULL,
  `ket_nyeri` varchar(40) NOT NULL,
  `pada_dokter` enum('Tidak','Ya') NOT NULL,
  `ket_dokter` varchar(15) NOT NULL,
  `rencana` varchar(200) NOT NULL,
  `nip` varchar(20) NOT NULL,
  `nama_cacat_fisik` varchar(150) DEFAULT NULL,
  PRIMARY KEY (`no_rawat`),
  KEY `nip` (`nip`) USING BTREE,
  CONSTRAINT `penilaian_awal_keperawatan_ralan_ibfk_1` FOREIGN KEY (`no_rawat`) REFERENCES `reg_periksa` (`no_rawat`) ON DELETE CASCADE ON UPDATE CASCADE,
  CONSTRAINT `penilaian_awal_keperawatan_ralan_ibfk_2` FOREIGN KEY (`nip`) REFERENCES `petugas` (`nip`) ON DELETE CASCADE ON UPDATE CASCADE
) ENGINE=InnoDB DEFAULT CHARSET=latin1 ROW_FORMAT=COMPACT;

/*Table structure for table `penilaian_awal_keperawatan_ralan_masalah` */

DROP TABLE IF EXISTS `penilaian_awal_keperawatan_ralan_masalah`;

CREATE TABLE `penilaian_awal_keperawatan_ralan_masalah` (
  `no_rawat` varchar(17) NOT NULL,
  `kode_masalah` varchar(3) NOT NULL,
  PRIMARY KEY (`no_rawat`,`kode_masalah`),
  KEY `kode_masalah` (`kode_masalah`) USING BTREE,
  CONSTRAINT `penilaian_awal_keperawatan_ralan_masalah_ibfk_1` FOREIGN KEY (`no_rawat`) REFERENCES `reg_periksa` (`no_rawat`) ON DELETE CASCADE ON UPDATE CASCADE,
  CONSTRAINT `penilaian_awal_keperawatan_ralan_masalah_ibfk_2` FOREIGN KEY (`kode_masalah`) REFERENCES `master_masalah_keperawatan` (`kode_masalah`) ON DELETE CASCADE ON UPDATE CASCADE
) ENGINE=InnoDB DEFAULT CHARSET=latin1 ROW_FORMAT=COMPACT;

/*Table structure for table `penjab` */

DROP TABLE IF EXISTS `penjab`;

CREATE TABLE `penjab` (
  `kd_pj` char(3) NOT NULL,
  `png_jawab` varchar(30) NOT NULL,
  `status` enum('0','1') NOT NULL,
  PRIMARY KEY (`kd_pj`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

/*Table structure for table `penjualan` */

DROP TABLE IF EXISTS `penjualan`;

CREATE TABLE `penjualan` (
  `nota_jual` varchar(20) NOT NULL,
  `tgl_jual` date DEFAULT NULL,
  `nip` char(20) DEFAULT NULL,
  `no_rkm_medis` varchar(10) DEFAULT NULL,
  `nm_pasien` varchar(50) DEFAULT NULL,
  `keterangan` varchar(150) DEFAULT NULL,
  `jns_jual` enum('Jual Bebas','Karyawan','Beli Luar','Rawat Jalan','Kelas 1','Kelas 2','Kelas 3','Utama/BPJS','VIP','VVIP') DEFAULT NULL,
  `ongkir` double DEFAULT NULL,
  `status` enum('Belum Dibayar','Sudah Dibayar') DEFAULT NULL,
  `kd_bangsal` char(5) NOT NULL,
  `kd_rek` varchar(15) DEFAULT NULL,
  `kd_dokter` varchar(20) DEFAULT NULL,
  `no_rawat` varchar(17) DEFAULT NULL,
  PRIMARY KEY (`nota_jual`),
  KEY `nip` (`nip`),
  KEY `no_rkm_medis` (`no_rkm_medis`),
  KEY `kd_bangsal` (`kd_bangsal`),
  KEY `penjualan_ibfk_12` (`kd_rek`),
  CONSTRAINT `penjualan_ibfk_10` FOREIGN KEY (`no_rkm_medis`) REFERENCES `pasien` (`no_rkm_medis`) ON UPDATE CASCADE,
  CONSTRAINT `penjualan_ibfk_11` FOREIGN KEY (`kd_bangsal`) REFERENCES `bangsal` (`kd_bangsal`) ON DELETE CASCADE ON UPDATE CASCADE,
  CONSTRAINT `penjualan_ibfk_12` FOREIGN KEY (`kd_rek`) REFERENCES `akun_bayar` (`kd_rek`) ON DELETE CASCADE ON UPDATE CASCADE,
  CONSTRAINT `penjualan_ibfk_9` FOREIGN KEY (`nip`) REFERENCES `petugas` (`nip`) ON DELETE CASCADE ON UPDATE CASCADE
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

/*Table structure for table `penyakit` */

DROP TABLE IF EXISTS `penyakit`;

CREATE TABLE `penyakit` (
  `kd_penyakit` varchar(10) NOT NULL,
  `nm_penyakit` varchar(100) DEFAULT NULL,
  `ciri_ciri` text DEFAULT NULL,
  `keterangan` varchar(60) DEFAULT NULL,
  `kd_ktg` varchar(8) DEFAULT NULL,
  `status` enum('Menular','Tidak Menular') NOT NULL,
  `kode_dtd` varchar(5) DEFAULT NULL,
  PRIMARY KEY (`kd_penyakit`),
  KEY `kd_ktg` (`kd_ktg`) USING BTREE,
  KEY `nm_penyakit` (`nm_penyakit`) USING BTREE,
  KEY `status` (`status`) USING BTREE,
  CONSTRAINT `penyakit_ibfk_1` FOREIGN KEY (`kd_ktg`) REFERENCES `kategori_penyakit` (`kd_ktg`) ON UPDATE CASCADE
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

/*Table structure for table `penyakit_pd3i` */

DROP TABLE IF EXISTS `penyakit_pd3i`;

CREATE TABLE `penyakit_pd3i` (
  `kd_penyakit` varchar(10) NOT NULL,
  PRIMARY KEY (`kd_penyakit`),
  CONSTRAINT `penyakit_pd3i_ibfk_1` FOREIGN KEY (`kd_penyakit`) REFERENCES `penyakit` (`kd_penyakit`) ON UPDATE CASCADE
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

/*Table structure for table `perawatan_corona` */

DROP TABLE IF EXISTS `perawatan_corona`;

CREATE TABLE `perawatan_corona` (
  `no_rawat` varchar(17) NOT NULL,
  `pemulasaraan_jenazah` enum('Tidak','Ya') DEFAULT NULL,
  `kantong_jenazah` enum('Tidak','Ya') DEFAULT NULL,
  `peti_jenazah` enum('Tidak','Ya') DEFAULT NULL,
  `plastik_erat` enum('Tidak','Ya') DEFAULT NULL,
  `desinfektan_jenazah` enum('Tidak','Ya') DEFAULT NULL,
  `mobil_jenazah` enum('Tidak','Ya') DEFAULT NULL,
  `desinfektan_mobil_jenazah` enum('Tidak','Ya') DEFAULT NULL,
  `covid19_status_cd` enum('ODP','PDP','Positif') DEFAULT NULL,
  `nomor_kartu_t` varchar(30) DEFAULT NULL,
  `episodes1` int(11) DEFAULT NULL,
  `episodes2` int(11) DEFAULT NULL,
  `episodes3` int(11) DEFAULT NULL,
  `episodes4` int(11) DEFAULT NULL,
  `episodes5` int(11) DEFAULT NULL,
  `episodes6` int(11) DEFAULT NULL,
  `covid19_cc_ind` enum('Tidak','Ya') DEFAULT NULL,
  PRIMARY KEY (`no_rawat`),
  CONSTRAINT `perawatan_corona_ibfk_1` FOREIGN KEY (`no_rawat`) REFERENCES `reg_periksa` (`no_rawat`) ON DELETE CASCADE ON UPDATE CASCADE
) ENGINE=InnoDB DEFAULT CHARSET=latin1 ROW_FORMAT=COMPACT;

/*Table structure for table `periksa_lab` */

DROP TABLE IF EXISTS `periksa_lab`;

CREATE TABLE `periksa_lab` (
  `no_rawat` varchar(18) NOT NULL,
  `nip` varchar(20) NOT NULL,
  `kd_jenis_prw` varchar(15) NOT NULL,
  `tgl_periksa` date NOT NULL,
  `jam` time NOT NULL,
  `dokter_perujuk` varchar(20) NOT NULL,
  `bagian_rs` double NOT NULL,
  `bhp` double NOT NULL,
  `tarif_perujuk` double NOT NULL,
  `tarif_tindakan_dokter` double NOT NULL,
  `tarif_tindakan_petugas` double NOT NULL,
  `kso` double DEFAULT NULL,
  `menejemen` double DEFAULT NULL,
  `biaya` double NOT NULL,
  `kd_dokter` varchar(20) NOT NULL,
  `status` enum('Ralan','Ranap') DEFAULT NULL,
  `stts_bayar` enum('BELUM','BAYAR') DEFAULT 'BELUM',
  `no_nota` varchar(17) DEFAULT NULL,
  PRIMARY KEY (`no_rawat`,`kd_jenis_prw`,`tgl_periksa`,`jam`),
  KEY `nip` (`nip`),
  KEY `kd_jenis_prw` (`kd_jenis_prw`),
  KEY `kd_dokter` (`kd_dokter`),
  KEY `dokter_perujuk` (`dokter_perujuk`),
  KEY `no_nota` (`no_nota`) USING BTREE,
  KEY `tgl_periksa` (`tgl_periksa`) USING BTREE,
  KEY `jam` (`jam`) USING BTREE,
  CONSTRAINT `periksa_lab_ibfk_10` FOREIGN KEY (`nip`) REFERENCES `petugas` (`nip`) ON DELETE CASCADE ON UPDATE CASCADE,
  CONSTRAINT `periksa_lab_ibfk_11` FOREIGN KEY (`kd_jenis_prw`) REFERENCES `jns_perawatan_lab` (`kd_jenis_prw`) ON DELETE CASCADE ON UPDATE CASCADE,
  CONSTRAINT `periksa_lab_ibfk_12` FOREIGN KEY (`dokter_perujuk`) REFERENCES `dokter` (`kd_dokter`) ON DELETE CASCADE ON UPDATE CASCADE,
  CONSTRAINT `periksa_lab_ibfk_13` FOREIGN KEY (`kd_dokter`) REFERENCES `dokter` (`kd_dokter`) ON DELETE CASCADE ON UPDATE CASCADE,
  CONSTRAINT `periksa_lab_ibfk_9` FOREIGN KEY (`no_rawat`) REFERENCES `reg_periksa` (`no_rawat`) ON UPDATE CASCADE
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

/*Table structure for table `periksa_lab_rujukan` */

DROP TABLE IF EXISTS `periksa_lab_rujukan`;

CREATE TABLE `periksa_lab_rujukan` (
  `no_lab` varchar(17) DEFAULT NULL,
  `kd_jenis_prw` varchar(15) NOT NULL,
  `bagian_rs` double NOT NULL,
  `bhp` double NOT NULL,
  `tarif_perujuk` double NOT NULL,
  `tarif_tindakan_dokter` double NOT NULL,
  `tarif_tindakan_petugas` double NOT NULL,
  `kso` double DEFAULT NULL,
  `menejemen` double DEFAULT NULL,
  `biaya` double NOT NULL,
  KEY `kd_jenis_prw` (`kd_jenis_prw`),
  KEY `no_lab` (`no_lab`),
  CONSTRAINT `periksa_lab_rujukan_ibfk_1` FOREIGN KEY (`kd_jenis_prw`) REFERENCES `jns_perawatan_lab` (`kd_jenis_prw`) ON DELETE CASCADE ON UPDATE CASCADE,
  CONSTRAINT `periksa_lab_rujukan_ibfk_2` FOREIGN KEY (`no_lab`) REFERENCES `pendaftaran_lab_rujukan` (`no_lab`) ON DELETE CASCADE ON UPDATE CASCADE
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

/*Table structure for table `periksa_radiologi` */

DROP TABLE IF EXISTS `periksa_radiologi`;

CREATE TABLE `periksa_radiologi` (
  `no_rawat` varchar(18) NOT NULL,
  `nip` varchar(20) NOT NULL,
  `kd_jenis_prw` varchar(15) NOT NULL,
  `tgl_periksa` date NOT NULL,
  `jam` time NOT NULL,
  `dokter_perujuk` varchar(20) NOT NULL,
  `bagian_rs` double NOT NULL,
  `bhp` double NOT NULL,
  `tarif_perujuk` double NOT NULL,
  `tarif_tindakan_dokter` double NOT NULL,
  `tarif_tindakan_petugas` double NOT NULL,
  `kso` double DEFAULT NULL,
  `menejemen` double DEFAULT NULL,
  `biaya` double NOT NULL,
  `kd_dokter` varchar(20) NOT NULL,
  `status` enum('Ranap','Ralan') DEFAULT NULL,
  `stts_bayar` enum('BELUM','BAYAR') DEFAULT 'BELUM',
  `no_nota` varchar(17) DEFAULT NULL,
  PRIMARY KEY (`no_rawat`,`kd_jenis_prw`,`tgl_periksa`,`jam`),
  KEY `nip` (`nip`),
  KEY `kd_jenis_prw` (`kd_jenis_prw`),
  KEY `kd_dokter` (`kd_dokter`),
  KEY `dokter_perujuk` (`dokter_perujuk`),
  KEY `no_nota` (`no_nota`) USING BTREE,
  CONSTRAINT `periksa_radiologi_ibfk_4` FOREIGN KEY (`no_rawat`) REFERENCES `reg_periksa` (`no_rawat`) ON UPDATE CASCADE,
  CONSTRAINT `periksa_radiologi_ibfk_5` FOREIGN KEY (`nip`) REFERENCES `petugas` (`nip`) ON DELETE CASCADE ON UPDATE CASCADE,
  CONSTRAINT `periksa_radiologi_ibfk_6` FOREIGN KEY (`kd_jenis_prw`) REFERENCES `jns_perawatan_radiologi` (`kd_jenis_prw`) ON DELETE CASCADE ON UPDATE CASCADE,
  CONSTRAINT `periksa_radiologi_ibfk_7` FOREIGN KEY (`kd_dokter`) REFERENCES `dokter` (`kd_dokter`) ON DELETE CASCADE ON UPDATE CASCADE,
  CONSTRAINT `periksa_radiologi_ibfk_8` FOREIGN KEY (`dokter_perujuk`) REFERENCES `dokter` (`kd_dokter`) ON DELETE CASCADE ON UPDATE CASCADE
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

/*Table structure for table `permintaan_detail_permintaan_lab` */

DROP TABLE IF EXISTS `permintaan_detail_permintaan_lab`;

CREATE TABLE `permintaan_detail_permintaan_lab` (
  `noorder` varchar(15) NOT NULL,
  `kd_jenis_prw` varchar(15) NOT NULL,
  `id_template` int(11) NOT NULL,
  PRIMARY KEY (`noorder`,`kd_jenis_prw`,`id_template`),
  KEY `id_template` (`id_template`),
  KEY `kd_jenis_prw` (`kd_jenis_prw`),
  CONSTRAINT `permintaan_detail_permintaan_lab_ibfk_2` FOREIGN KEY (`kd_jenis_prw`) REFERENCES `jns_perawatan_lab` (`kd_jenis_prw`) ON DELETE CASCADE ON UPDATE CASCADE,
  CONSTRAINT `permintaan_detail_permintaan_lab_ibfk_3` FOREIGN KEY (`id_template`) REFERENCES `template_laboratorium` (`id_template`) ON DELETE CASCADE ON UPDATE CASCADE,
  CONSTRAINT `permintaan_detail_permintaan_lab_ibfk_4` FOREIGN KEY (`noorder`) REFERENCES `permintaan_lab` (`noorder`) ON DELETE CASCADE ON UPDATE CASCADE
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

/*Table structure for table `permintaan_lab` */

DROP TABLE IF EXISTS `permintaan_lab`;

CREATE TABLE `permintaan_lab` (
  `noorder` varchar(15) NOT NULL,
  `no_rawat` varchar(17) NOT NULL,
  `tgl_permintaan` date NOT NULL,
  `jam_permintaan` time NOT NULL,
  `tgl_sampel` date NOT NULL,
  `jam_sampel` time NOT NULL,
  `tgl_hasil` date NOT NULL,
  `jam_hasil` time NOT NULL,
  `dokter_perujuk` varchar(20) NOT NULL,
  PRIMARY KEY (`noorder`),
  KEY `dokter_perujuk` (`dokter_perujuk`),
  KEY `no_rawat` (`no_rawat`),
  CONSTRAINT `permintaan_lab_ibfk_2` FOREIGN KEY (`dokter_perujuk`) REFERENCES `dokter` (`kd_dokter`) ON DELETE CASCADE ON UPDATE CASCADE,
  CONSTRAINT `permintaan_lab_ibfk_3` FOREIGN KEY (`no_rawat`) REFERENCES `reg_periksa` (`no_rawat`) ON DELETE CASCADE ON UPDATE CASCADE
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

/*Table structure for table `permintaan_lab_raza` */

DROP TABLE IF EXISTS `permintaan_lab_raza`;

CREATE TABLE `permintaan_lab_raza` (
  `no_rawat` varchar(17) NOT NULL,
  `tgl_permintaan` date NOT NULL,
  `jam_permintaan` time NOT NULL,
  `dokter_perujuk` varchar(20) NOT NULL,
  `nm_pemeriksaan` varchar(255) DEFAULT NULL,
  `status_rawat` enum('Ranap','Ralan') DEFAULT NULL,
  `no_minta` varchar(20) NOT NULL,
  `status_periksa` enum('BELUM','SUDAH') DEFAULT NULL,
  PRIMARY KEY (`no_minta`),
  KEY `dokter_perujuk` (`dokter_perujuk`) USING BTREE,
  KEY `no_rawat` (`no_rawat`) USING BTREE,
  CONSTRAINT `permintaan_lab_raza_ibfk_1` FOREIGN KEY (`dokter_perujuk`) REFERENCES `dokter` (`kd_dokter`) ON DELETE CASCADE ON UPDATE CASCADE,
  CONSTRAINT `permintaan_lab_raza_ibfk_2` FOREIGN KEY (`no_rawat`) REFERENCES `reg_periksa` (`no_rawat`) ON DELETE CASCADE ON UPDATE CASCADE
) ENGINE=InnoDB DEFAULT CHARSET=latin1 ROW_FORMAT=COMPACT;

/*Table structure for table `permintaan_pemeriksaan_lab` */

DROP TABLE IF EXISTS `permintaan_pemeriksaan_lab`;

CREATE TABLE `permintaan_pemeriksaan_lab` (
  `noorder` varchar(15) NOT NULL,
  `kd_jenis_prw` varchar(15) NOT NULL,
  PRIMARY KEY (`noorder`,`kd_jenis_prw`),
  KEY `kd_jenis_prw` (`kd_jenis_prw`),
  CONSTRAINT `permintaan_pemeriksaan_lab_ibfk_1` FOREIGN KEY (`noorder`) REFERENCES `permintaan_lab` (`noorder`) ON DELETE CASCADE ON UPDATE CASCADE,
  CONSTRAINT `permintaan_pemeriksaan_lab_ibfk_2` FOREIGN KEY (`kd_jenis_prw`) REFERENCES `jns_perawatan_lab` (`kd_jenis_prw`) ON DELETE CASCADE ON UPDATE CASCADE
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

/*Table structure for table `permintaan_pemeriksaan_radiologi` */

DROP TABLE IF EXISTS `permintaan_pemeriksaan_radiologi`;

CREATE TABLE `permintaan_pemeriksaan_radiologi` (
  `noorder` varchar(15) NOT NULL,
  `kd_jenis_prw` varchar(15) NOT NULL,
  PRIMARY KEY (`noorder`,`kd_jenis_prw`),
  KEY `kd_jenis_prw` (`kd_jenis_prw`),
  CONSTRAINT `permintaan_pemeriksaan_radiologi_ibfk_1` FOREIGN KEY (`noorder`) REFERENCES `permintaan_radiologi` (`noorder`) ON DELETE CASCADE ON UPDATE CASCADE,
  CONSTRAINT `permintaan_pemeriksaan_radiologi_ibfk_2` FOREIGN KEY (`kd_jenis_prw`) REFERENCES `jns_perawatan_radiologi` (`kd_jenis_prw`) ON DELETE CASCADE ON UPDATE CASCADE
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

/*Table structure for table `permintaan_radiologi` */

DROP TABLE IF EXISTS `permintaan_radiologi`;

CREATE TABLE `permintaan_radiologi` (
  `noorder` varchar(15) NOT NULL,
  `no_rawat` varchar(17) NOT NULL,
  `tgl_permintaan` date NOT NULL,
  `jam_permintaan` time NOT NULL,
  `tgl_sampel` date NOT NULL,
  `jam_sampel` time NOT NULL,
  `tgl_hasil` date NOT NULL,
  `jam_hasil` time NOT NULL,
  `dokter_perujuk` varchar(20) NOT NULL,
  PRIMARY KEY (`noorder`),
  KEY `dokter_perujuk` (`dokter_perujuk`),
  KEY `no_rawat` (`no_rawat`),
  CONSTRAINT `permintaan_radiologi_ibfk_1` FOREIGN KEY (`no_rawat`) REFERENCES `reg_periksa` (`no_rawat`) ON DELETE CASCADE ON UPDATE CASCADE,
  CONSTRAINT `permintaan_radiologi_ibfk_3` FOREIGN KEY (`dokter_perujuk`) REFERENCES `dokter` (`kd_dokter`) ON DELETE CASCADE ON UPDATE CASCADE
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

/*Table structure for table `perusahaan_pasien` */

DROP TABLE IF EXISTS `perusahaan_pasien`;

CREATE TABLE `perusahaan_pasien` (
  `kode_perusahaan` varchar(8) NOT NULL,
  `nama_perusahaan` varchar(70) DEFAULT NULL,
  `alamat` varchar(100) DEFAULT NULL,
  `kota` varchar(40) DEFAULT NULL,
  `no_telp` varchar(27) DEFAULT NULL,
  PRIMARY KEY (`kode_perusahaan`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1 ROW_FORMAT=COMPACT;

/*Table structure for table `petugas` */

DROP TABLE IF EXISTS `petugas`;

CREATE TABLE `petugas` (
  `nip` varchar(20) NOT NULL,
  `nama` varchar(50) DEFAULT NULL,
  `jk` enum('L','P') DEFAULT NULL,
  `tmp_lahir` varchar(20) DEFAULT NULL,
  `tgl_lahir` date DEFAULT NULL,
  `gol_darah` enum('A','B','O','AB','-') DEFAULT NULL,
  `agama` varchar(12) DEFAULT NULL,
  `stts_nikah` enum('BELUM MENIKAH','MENIKAH','JANDA','DUDA') DEFAULT NULL,
  `alamat` varchar(60) DEFAULT NULL,
  `kd_jbtn` char(4) DEFAULT NULL,
  `no_telp` varchar(13) DEFAULT NULL,
  `status` enum('0','1') DEFAULT NULL,
  PRIMARY KEY (`nip`),
  KEY `kd_jbtn` (`kd_jbtn`),
  KEY `nama` (`nama`),
  KEY `nip` (`nip`) USING BTREE,
  KEY `tmp_lahir` (`tmp_lahir`) USING BTREE,
  KEY `tgl_lahir` (`tgl_lahir`) USING BTREE,
  KEY `agama` (`agama`) USING BTREE,
  KEY `stts_nikah` (`stts_nikah`) USING BTREE,
  KEY `alamat` (`alamat`) USING BTREE,
  CONSTRAINT `petugas_ibfk_4` FOREIGN KEY (`nip`) REFERENCES `pegawai` (`nik`) ON UPDATE CASCADE,
  CONSTRAINT `petugas_ibfk_5` FOREIGN KEY (`kd_jbtn`) REFERENCES `jabatan` (`kd_jbtn`) ON UPDATE CASCADE
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

/*Table structure for table `piutang` */

DROP TABLE IF EXISTS `piutang`;

CREATE TABLE `piutang` (
  `nota_piutang` varchar(20) NOT NULL,
  `tgl_piutang` date DEFAULT NULL,
  `nip` char(20) DEFAULT NULL,
  `no_rkm_medis` varchar(10) DEFAULT NULL,
  `nm_pasien` varchar(50) DEFAULT NULL,
  `catatan` varchar(40) DEFAULT NULL,
  `jns_jual` enum('Jual Bebas','Karyawan','Beli Luar','Rawat Jalan','Kelas 1','Kelas 2','Kelas 3','Utama','VIP','VVIP') DEFAULT NULL,
  `ongkir` double DEFAULT NULL,
  `uangmuka` double DEFAULT NULL,
  `sisapiutang` double NOT NULL,
  `status` enum('UMUM','PAJAK') DEFAULT NULL,
  `tgltempo` date NOT NULL,
  `kd_bangsal` char(5) NOT NULL,
  PRIMARY KEY (`nota_piutang`),
  KEY `nip` (`nip`),
  KEY `no_rkm_medis` (`no_rkm_medis`),
  KEY `kd_bangsal` (`kd_bangsal`),
  CONSTRAINT `piutang_ibfk_1` FOREIGN KEY (`nip`) REFERENCES `petugas` (`nip`) ON DELETE CASCADE ON UPDATE CASCADE,
  CONSTRAINT `piutang_ibfk_2` FOREIGN KEY (`no_rkm_medis`) REFERENCES `pasien` (`no_rkm_medis`) ON UPDATE CASCADE,
  CONSTRAINT `piutang_ibfk_3` FOREIGN KEY (`kd_bangsal`) REFERENCES `bangsal` (`kd_bangsal`) ON DELETE CASCADE ON UPDATE CASCADE
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

/*Table structure for table `piutang_pasien` */

DROP TABLE IF EXISTS `piutang_pasien`;

CREATE TABLE `piutang_pasien` (
  `no_rawat` varchar(18) NOT NULL,
  `tgl_piutang` date DEFAULT NULL,
  `no_rkm_medis` varchar(15) DEFAULT NULL,
  `status` enum('Lunas','Belum Lunas') NOT NULL,
  `totalpiutang` double DEFAULT NULL,
  `uangmuka` double DEFAULT NULL,
  `sisapiutang` double NOT NULL,
  `tgltempo` date NOT NULL,
  `no_nota` varchar(17) DEFAULT NULL,
  PRIMARY KEY (`no_rawat`),
  KEY `no_rkm_medis` (`no_rkm_medis`),
  CONSTRAINT `piutang_pasien_ibfk_2` FOREIGN KEY (`no_rkm_medis`) REFERENCES `pasien` (`no_rkm_medis`) ON UPDATE CASCADE,
  CONSTRAINT `piutang_pasien_ibfk_3` FOREIGN KEY (`no_rawat`) REFERENCES `reg_periksa` (`no_rawat`) ON UPDATE CASCADE
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

/*Table structure for table `pnm_tnj_bulanan` */

DROP TABLE IF EXISTS `pnm_tnj_bulanan`;

CREATE TABLE `pnm_tnj_bulanan` (
  `id` int(11) NOT NULL,
  `id_tnj` int(11) NOT NULL,
  PRIMARY KEY (`id`,`id_tnj`),
  KEY `id_tnj` (`id_tnj`),
  CONSTRAINT `pnm_tnj_bulanan_ibfk_5` FOREIGN KEY (`id`) REFERENCES `pegawai` (`id`) ON UPDATE CASCADE,
  CONSTRAINT `pnm_tnj_bulanan_ibfk_6` FOREIGN KEY (`id_tnj`) REFERENCES `master_tunjangan_bulanan` (`id`) ON UPDATE CASCADE
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

/*Table structure for table `pnm_tnj_harian` */

DROP TABLE IF EXISTS `pnm_tnj_harian`;

CREATE TABLE `pnm_tnj_harian` (
  `id` int(11) NOT NULL,
  `id_tnj` int(11) NOT NULL,
  PRIMARY KEY (`id`,`id_tnj`),
  KEY `id_tnj` (`id_tnj`),
  CONSTRAINT `pnm_tnj_harian_ibfk_5` FOREIGN KEY (`id`) REFERENCES `pegawai` (`id`) ON UPDATE CASCADE,
  CONSTRAINT `pnm_tnj_harian_ibfk_6` FOREIGN KEY (`id_tnj`) REFERENCES `master_tunjangan_harian` (`id`) ON UPDATE CASCADE
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

/*Table structure for table `poliklinik` */

DROP TABLE IF EXISTS `poliklinik`;

CREATE TABLE `poliklinik` (
  `kd_poli` char(5) NOT NULL DEFAULT '',
  `nm_poli` varchar(50) DEFAULT NULL,
  `registrasi` double NOT NULL,
  `registrasilama` double NOT NULL,
  `status_online` enum('0','1') NOT NULL,
  `status_erm` int(11) DEFAULT NULL,
  PRIMARY KEY (`kd_poli`),
  KEY `nm_poli` (`nm_poli`),
  KEY `registrasi` (`registrasi`),
  KEY `registrasilama` (`registrasilama`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

/*Table structure for table `potongan` */

DROP TABLE IF EXISTS `potongan`;

CREATE TABLE `potongan` (
  `tahun` year(4) NOT NULL,
  `bulan` tinyint(4) NOT NULL,
  `id` int(11) NOT NULL,
  `bpjs` double NOT NULL,
  `jamsostek` double NOT NULL,
  `dansos` double NOT NULL,
  `simwajib` double NOT NULL,
  `angkop` double NOT NULL,
  `angla` double NOT NULL,
  `telpri` double NOT NULL,
  `pajak` double NOT NULL,
  `pribadi` double NOT NULL,
  `lain` double NOT NULL,
  `ktg` varchar(50) NOT NULL,
  PRIMARY KEY (`tahun`,`bulan`,`id`),
  KEY `id` (`id`),
  CONSTRAINT `potongan_ibfk_1` FOREIGN KEY (`id`) REFERENCES `pegawai` (`id`) ON UPDATE CASCADE
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

/*Table structure for table `presensi` */

DROP TABLE IF EXISTS `presensi`;

CREATE TABLE `presensi` (
  `tgl` date NOT NULL,
  `id` int(11) NOT NULL,
  `jns` enum('HR','HB') NOT NULL,
  `lembur` int(11) NOT NULL,
  PRIMARY KEY (`tgl`,`id`),
  KEY `id` (`id`),
  CONSTRAINT `presensi_ibfk_1` FOREIGN KEY (`id`) REFERENCES `pegawai` (`id`) ON UPDATE CASCADE
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

/*Table structure for table `prmrj` */

DROP TABLE IF EXISTS `prmrj`;

CREATE TABLE `prmrj` (
  `kd_prmrj` varchar(20) NOT NULL,
  `no_rkm_medis` varchar(15) DEFAULT NULL,
  `tgl_input` date DEFAULT NULL,
  `diagnosis` longtext DEFAULT NULL,
  `kd_icd10` varchar(255) DEFAULT NULL,
  `pemeriksaan_penunjang` longtext DEFAULT NULL,
  `obat` longtext DEFAULT NULL,
  `riwayat` longtext DEFAULT NULL,
  `prosedur_bedah_ops` longtext DEFAULT NULL,
  `kd_dokter` varchar(20) DEFAULT NULL,
  `no_rawat` varchar(18) NOT NULL,
  PRIMARY KEY (`kd_prmrj`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1 ROW_FORMAT=COMPACT;

/*Table structure for table `prosedur_pasien` */

DROP TABLE IF EXISTS `prosedur_pasien`;

CREATE TABLE `prosedur_pasien` (
  `no_rawat` varchar(18) NOT NULL,
  `kode` varchar(8) NOT NULL,
  `status` enum('Ralan','Ranap') NOT NULL,
  `prioritas` tinyint(4) NOT NULL,
  PRIMARY KEY (`no_rawat`,`kode`,`status`),
  KEY `kode` (`kode`),
  CONSTRAINT `prosedur_pasien_ibfk_1` FOREIGN KEY (`no_rawat`) REFERENCES `reg_periksa` (`no_rawat`) ON DELETE CASCADE ON UPDATE CASCADE,
  CONSTRAINT `prosedur_pasien_ibfk_2` FOREIGN KEY (`kode`) REFERENCES `icd9` (`kode`) ON DELETE CASCADE ON UPDATE CASCADE
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

/*Table structure for table `ralan_aps` */

DROP TABLE IF EXISTS `ralan_aps`;

CREATE TABLE `ralan_aps` (
  `no_rawat` varchar(17) NOT NULL,
  `kd_aps` int(11) NOT NULL,
  `keterangan` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`no_rawat`),
  KEY `kd_aps` (`kd_aps`) USING BTREE,
  CONSTRAINT `ralan_aps_ibfk_1` FOREIGN KEY (`kd_aps`) REFERENCES `master_aps` (`kd_aps`) ON UPDATE CASCADE
) ENGINE=InnoDB DEFAULT CHARSET=latin1 ROW_FORMAT=COMPACT;

/*Table structure for table `ranap_aps` */

DROP TABLE IF EXISTS `ranap_aps`;

CREATE TABLE `ranap_aps` (
  `no_rawat` varchar(17) NOT NULL,
  `kd_aps` int(11) NOT NULL,
  `keterangan` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`no_rawat`),
  KEY `kd_aps` (`kd_aps`) USING BTREE,
  CONSTRAINT `ranap_aps_ibfk_1` FOREIGN KEY (`kd_aps`) REFERENCES `master_aps` (`kd_aps`) ON UPDATE CASCADE
) ENGINE=InnoDB DEFAULT CHARSET=latin1 ROW_FORMAT=COMPACT;

/*Table structure for table `ranap_gabung` */

DROP TABLE IF EXISTS `ranap_gabung`;

CREATE TABLE `ranap_gabung` (
  `no_rawat` varchar(17) NOT NULL,
  `no_rawat2` varchar(17) NOT NULL,
  PRIMARY KEY (`no_rawat`,`no_rawat2`),
  KEY `no_rawat2` (`no_rawat2`),
  CONSTRAINT `ranap_gabung_ibfk_1` FOREIGN KEY (`no_rawat`) REFERENCES `reg_periksa` (`no_rawat`) ON UPDATE CASCADE,
  CONSTRAINT `ranap_gabung_ibfk_2` FOREIGN KEY (`no_rawat2`) REFERENCES `reg_periksa` (`no_rawat`) ON UPDATE CASCADE
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

/*Table structure for table `rawat_inap_dr` */

DROP TABLE IF EXISTS `rawat_inap_dr`;

CREATE TABLE `rawat_inap_dr` (
  `no_rawat` varchar(17) NOT NULL DEFAULT '',
  `kd_jenis_prw` varchar(15) NOT NULL,
  `kd_dokter` varchar(20) NOT NULL,
  `tgl_perawatan` date NOT NULL DEFAULT '0000-00-00',
  `jam_rawat` time NOT NULL DEFAULT '00:00:00',
  `material` double NOT NULL,
  `bhp` double NOT NULL,
  `tarif_tindakandr` double NOT NULL,
  `kso` double DEFAULT NULL,
  `menejemen` double DEFAULT NULL,
  `biaya_rawat` double DEFAULT NULL,
  `kd_dokter_mewakili` varchar(20) DEFAULT NULL,
  PRIMARY KEY (`no_rawat`,`kd_jenis_prw`,`kd_dokter`,`tgl_perawatan`,`jam_rawat`),
  KEY `no_rawat` (`no_rawat`),
  KEY `kd_jenis_prw` (`kd_jenis_prw`),
  KEY `kd_dokter` (`kd_dokter`),
  KEY `tgl_perawatan` (`tgl_perawatan`),
  KEY `biaya_rawat` (`biaya_rawat`),
  KEY `jam_rawat` (`jam_rawat`),
  CONSTRAINT `rawat_inap_dr_ibfk_3` FOREIGN KEY (`kd_dokter`) REFERENCES `dokter` (`kd_dokter`) ON DELETE CASCADE ON UPDATE CASCADE,
  CONSTRAINT `rawat_inap_dr_ibfk_6` FOREIGN KEY (`kd_jenis_prw`) REFERENCES `jns_perawatan_inap` (`kd_jenis_prw`) ON DELETE CASCADE ON UPDATE CASCADE,
  CONSTRAINT `rawat_inap_dr_ibfk_7` FOREIGN KEY (`no_rawat`) REFERENCES `reg_periksa` (`no_rawat`) ON UPDATE CASCADE
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

/*Table structure for table `rawat_inap_drpr` */

DROP TABLE IF EXISTS `rawat_inap_drpr`;

CREATE TABLE `rawat_inap_drpr` (
  `no_rawat` varchar(17) NOT NULL DEFAULT '',
  `kd_jenis_prw` varchar(15) NOT NULL,
  `kd_dokter` varchar(20) NOT NULL,
  `nip` varchar(20) NOT NULL DEFAULT '',
  `tgl_perawatan` date NOT NULL DEFAULT '0000-00-00',
  `jam_rawat` time NOT NULL DEFAULT '00:00:00',
  `material` double NOT NULL,
  `bhp` double NOT NULL,
  `tarif_tindakandr` double DEFAULT NULL,
  `tarif_tindakanpr` double DEFAULT NULL,
  `kso` double DEFAULT NULL,
  `menejemen` double DEFAULT NULL,
  `biaya_rawat` double DEFAULT NULL,
  PRIMARY KEY (`no_rawat`,`kd_jenis_prw`,`kd_dokter`,`nip`,`tgl_perawatan`,`jam_rawat`),
  KEY `rawat_inap_drpr_ibfk_2` (`kd_jenis_prw`),
  KEY `rawat_inap_drpr_ibfk_3` (`kd_dokter`),
  KEY `rawat_inap_drpr_ibfk_4` (`nip`),
  KEY `tgl_perawatan` (`tgl_perawatan`) USING BTREE,
  KEY `jam_rawat` (`jam_rawat`) USING BTREE,
  KEY `biaya_rawat` (`biaya_rawat`) USING BTREE,
  CONSTRAINT `rawat_inap_drpr_ibfk_1` FOREIGN KEY (`no_rawat`) REFERENCES `reg_periksa` (`no_rawat`) ON UPDATE CASCADE,
  CONSTRAINT `rawat_inap_drpr_ibfk_2` FOREIGN KEY (`kd_jenis_prw`) REFERENCES `jns_perawatan_inap` (`kd_jenis_prw`) ON DELETE CASCADE ON UPDATE CASCADE,
  CONSTRAINT `rawat_inap_drpr_ibfk_3` FOREIGN KEY (`kd_dokter`) REFERENCES `dokter` (`kd_dokter`) ON DELETE CASCADE ON UPDATE CASCADE,
  CONSTRAINT `rawat_inap_drpr_ibfk_4` FOREIGN KEY (`nip`) REFERENCES `petugas` (`nip`) ON DELETE CASCADE ON UPDATE CASCADE
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

/*Table structure for table `rawat_inap_pr` */

DROP TABLE IF EXISTS `rawat_inap_pr`;

CREATE TABLE `rawat_inap_pr` (
  `no_rawat` varchar(17) NOT NULL DEFAULT '',
  `kd_jenis_prw` varchar(15) NOT NULL,
  `nip` varchar(20) NOT NULL DEFAULT '',
  `tgl_perawatan` date NOT NULL DEFAULT '0000-00-00',
  `jam_rawat` time NOT NULL DEFAULT '00:00:00',
  `material` double NOT NULL,
  `bhp` double NOT NULL,
  `tarif_tindakanpr` double NOT NULL,
  `kso` double DEFAULT NULL,
  `menejemen` double DEFAULT NULL,
  `biaya_rawat` double DEFAULT NULL,
  PRIMARY KEY (`no_rawat`,`kd_jenis_prw`,`nip`,`tgl_perawatan`,`jam_rawat`),
  KEY `no_rawat` (`no_rawat`),
  KEY `kd_jenis_prw` (`kd_jenis_prw`),
  KEY `nip` (`nip`),
  KEY `biaya_rawat` (`biaya_rawat`),
  KEY `tgl_perawatan` (`tgl_perawatan`) USING BTREE,
  KEY `jam_rawat` (`jam_rawat`) USING BTREE,
  CONSTRAINT `rawat_inap_pr_ibfk_3` FOREIGN KEY (`nip`) REFERENCES `petugas` (`nip`) ON DELETE CASCADE ON UPDATE CASCADE,
  CONSTRAINT `rawat_inap_pr_ibfk_6` FOREIGN KEY (`kd_jenis_prw`) REFERENCES `jns_perawatan_inap` (`kd_jenis_prw`) ON DELETE CASCADE ON UPDATE CASCADE,
  CONSTRAINT `rawat_inap_pr_ibfk_7` FOREIGN KEY (`no_rawat`) REFERENCES `reg_periksa` (`no_rawat`) ON UPDATE CASCADE
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

/*Table structure for table `rawat_jl_dr` */

DROP TABLE IF EXISTS `rawat_jl_dr`;

CREATE TABLE `rawat_jl_dr` (
  `no_rawat` varchar(18) NOT NULL DEFAULT '',
  `kd_jenis_prw` varchar(15) NOT NULL,
  `kd_dokter` varchar(20) NOT NULL,
  `tgl_perawatan` date DEFAULT NULL,
  `jam_rawat` time DEFAULT NULL,
  `material` double NOT NULL,
  `bhp` double NOT NULL,
  `tarif_tindakandr` double NOT NULL,
  `kso` double DEFAULT NULL,
  `menejemen` double DEFAULT NULL,
  `biaya_rawat` double DEFAULT NULL,
  KEY `no_rawat` (`no_rawat`),
  KEY `kd_jenis_prw` (`kd_jenis_prw`),
  KEY `kd_dokter` (`kd_dokter`),
  KEY `biaya_rawat` (`biaya_rawat`),
  CONSTRAINT `rawat_jl_dr_ibfk_2` FOREIGN KEY (`kd_jenis_prw`) REFERENCES `jns_perawatan` (`kd_jenis_prw`) ON DELETE CASCADE ON UPDATE CASCADE,
  CONSTRAINT `rawat_jl_dr_ibfk_3` FOREIGN KEY (`kd_dokter`) REFERENCES `dokter` (`kd_dokter`) ON DELETE CASCADE ON UPDATE CASCADE,
  CONSTRAINT `rawat_jl_dr_ibfk_5` FOREIGN KEY (`no_rawat`) REFERENCES `reg_periksa` (`no_rawat`) ON UPDATE CASCADE
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

/*Table structure for table `rawat_jl_drpr` */

DROP TABLE IF EXISTS `rawat_jl_drpr`;

CREATE TABLE `rawat_jl_drpr` (
  `no_rawat` varchar(18) NOT NULL DEFAULT '',
  `kd_jenis_prw` varchar(15) NOT NULL,
  `kd_dokter` varchar(20) NOT NULL,
  `nip` varchar(20) NOT NULL,
  `tgl_perawatan` date NOT NULL,
  `jam_rawat` time NOT NULL,
  `material` double DEFAULT NULL,
  `bhp` double NOT NULL,
  `tarif_tindakandr` double DEFAULT NULL,
  `tarif_tindakanpr` double DEFAULT NULL,
  `kso` double DEFAULT NULL,
  `menejemen` double DEFAULT NULL,
  `biaya_rawat` double DEFAULT NULL,
  `stts_bayar` enum('BELUM','BAYAR') DEFAULT 'BELUM',
  `no_nota` varchar(17) DEFAULT NULL,
  KEY `rawat_jl_drpr_ibfk_2` (`kd_jenis_prw`),
  KEY `rawat_jl_drpr_ibfk_3` (`kd_dokter`),
  KEY `rawat_jl_drpr_ibfk_4` (`nip`),
  KEY `no_rawat` (`no_rawat`) USING BTREE,
  KEY `tgl_perawatan` (`tgl_perawatan`) USING BTREE,
  KEY `jam_rawat` (`jam_rawat`) USING BTREE,
  KEY `no_nota` (`no_nota`) USING BTREE,
  CONSTRAINT `rawat_jl_drpr_ibfk_1` FOREIGN KEY (`no_rawat`) REFERENCES `reg_periksa` (`no_rawat`) ON UPDATE CASCADE,
  CONSTRAINT `rawat_jl_drpr_ibfk_2` FOREIGN KEY (`kd_jenis_prw`) REFERENCES `jns_perawatan` (`kd_jenis_prw`) ON DELETE CASCADE ON UPDATE CASCADE,
  CONSTRAINT `rawat_jl_drpr_ibfk_3` FOREIGN KEY (`kd_dokter`) REFERENCES `dokter` (`kd_dokter`) ON DELETE CASCADE ON UPDATE CASCADE,
  CONSTRAINT `rawat_jl_drpr_ibfk_4` FOREIGN KEY (`nip`) REFERENCES `petugas` (`nip`) ON DELETE CASCADE ON UPDATE CASCADE
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

/*Table structure for table `rawat_jl_pr` */

DROP TABLE IF EXISTS `rawat_jl_pr`;

CREATE TABLE `rawat_jl_pr` (
  `no_rawat` varchar(18) NOT NULL DEFAULT '',
  `kd_jenis_prw` varchar(15) NOT NULL,
  `nip` varchar(20) NOT NULL DEFAULT '',
  `tgl_perawatan` date DEFAULT NULL,
  `jam_rawat` time DEFAULT NULL,
  `material` double NOT NULL,
  `bhp` double NOT NULL,
  `tarif_tindakanpr` double NOT NULL,
  `kso` double DEFAULT NULL,
  `menejemen` double DEFAULT NULL,
  `biaya_rawat` double DEFAULT NULL,
  KEY `no_rawat` (`no_rawat`),
  KEY `kd_jenis_prw` (`kd_jenis_prw`),
  KEY `nip` (`nip`),
  KEY `biaya_rawat` (`biaya_rawat`),
  CONSTRAINT `rawat_jl_pr_ibfk_10` FOREIGN KEY (`nip`) REFERENCES `petugas` (`nip`) ON DELETE CASCADE ON UPDATE CASCADE,
  CONSTRAINT `rawat_jl_pr_ibfk_8` FOREIGN KEY (`no_rawat`) REFERENCES `reg_periksa` (`no_rawat`) ON UPDATE CASCADE,
  CONSTRAINT `rawat_jl_pr_ibfk_9` FOREIGN KEY (`kd_jenis_prw`) REFERENCES `jns_perawatan` (`kd_jenis_prw`) ON DELETE CASCADE ON UPDATE CASCADE
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

/*Table structure for table `rawatjalan` */

DROP TABLE IF EXISTS `rawatjalan`;

CREATE TABLE `rawatjalan` (
  `tgl` datetime NOT NULL,
  `id` int(11) NOT NULL,
  `tnd` int(11) NOT NULL,
  `jm` double NOT NULL,
  `nm_pasien` varchar(30) NOT NULL,
  `kamar` varchar(20) NOT NULL,
  `diagnosa` varchar(50) NOT NULL,
  `jmlh` int(11) NOT NULL,
  PRIMARY KEY (`tgl`,`id`,`tnd`),
  KEY `id` (`id`),
  KEY `tnd` (`tnd`),
  CONSTRAINT `rawatjalan_ibfk_1` FOREIGN KEY (`id`) REFERENCES `pegawai` (`id`) ON UPDATE CASCADE
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

/*Table structure for table `reg_periksa` */

DROP TABLE IF EXISTS `reg_periksa`;

CREATE TABLE `reg_periksa` (
  `no_reg` varchar(8) DEFAULT NULL,
  `no_rawat` varchar(17) NOT NULL,
  `tgl_registrasi` date DEFAULT NULL,
  `jam_reg` time DEFAULT NULL,
  `kd_dokter` varchar(20) DEFAULT NULL,
  `no_rkm_medis` varchar(15) DEFAULT NULL,
  `kd_poli` char(5) DEFAULT NULL,
  `p_jawab` varchar(100) DEFAULT NULL,
  `almt_pj` varchar(200) DEFAULT NULL,
  `hubunganpj` varchar(20) DEFAULT NULL,
  `biaya_reg` double DEFAULT NULL,
  `stts` enum('Belum','Sudah','Bayar','Batal','Berkas Diterima','Dirujuk','Meninggal','Dirawat','Sudah Diperiksa Dokter') DEFAULT NULL,
  `stts_daftar` enum('-','Lama','Baru') NOT NULL,
  `status_lanjut` enum('Ralan','Ranap') NOT NULL,
  `kd_pj` char(3) NOT NULL,
  `umurdaftar` int(11) DEFAULT NULL,
  `sttsumur` enum('Th','Bl','Hr') DEFAULT NULL,
  PRIMARY KEY (`no_rawat`),
  KEY `nip` (`kd_dokter`),
  KEY `no_rkm_medis` (`no_rkm_medis`),
  KEY `kd_poli` (`kd_poli`),
  KEY `kd_pj` (`kd_pj`),
  KEY `no_reg` (`no_reg`),
  KEY `tgl_registrasi` (`tgl_registrasi`),
  KEY `jam_reg` (`jam_reg`),
  KEY `p_jawab` (`p_jawab`),
  KEY `almt_pj` (`almt_pj`),
  KEY `hubunganpj` (`hubunganpj`),
  KEY `biaya_reg` (`biaya_reg`),
  KEY `stts` (`stts`),
  KEY `stts_daftar` (`stts_daftar`),
  KEY `status_lanjut` (`status_lanjut`),
  KEY `sttsumur` (`sttsumur`) USING BTREE,
  KEY `umurdaftar` (`umurdaftar`) USING BTREE,
  KEY `kd_dokter` (`kd_dokter`) USING BTREE,
  CONSTRAINT `reg_periksa_ibfk_3` FOREIGN KEY (`kd_poli`) REFERENCES `poliklinik` (`kd_poli`) ON DELETE CASCADE ON UPDATE CASCADE,
  CONSTRAINT `reg_periksa_ibfk_4` FOREIGN KEY (`kd_dokter`) REFERENCES `dokter` (`kd_dokter`) ON DELETE CASCADE ON UPDATE CASCADE,
  CONSTRAINT `reg_periksa_ibfk_6` FOREIGN KEY (`kd_pj`) REFERENCES `penjab` (`kd_pj`) ON DELETE CASCADE ON UPDATE CASCADE,
  CONSTRAINT `reg_periksa_ibfk_7` FOREIGN KEY (`no_rkm_medis`) REFERENCES `pasien` (`no_rkm_medis`) ON UPDATE CASCADE
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

/*Table structure for table `reg_rujukan_intern` */

DROP TABLE IF EXISTS `reg_rujukan_intern`;

CREATE TABLE `reg_rujukan_intern` (
  `no_rawat_dari` varchar(17) NOT NULL,
  `no_rawat_ke` varchar(17) NOT NULL,
  PRIMARY KEY (`no_rawat_dari`),
  CONSTRAINT `FK_reg_rujukan_intern_reg_periksa` FOREIGN KEY (`no_rawat_dari`) REFERENCES `reg_periksa` (`no_rawat`) ON UPDATE CASCADE
) ENGINE=InnoDB DEFAULT CHARSET=latin1 ROW_FORMAT=COMPACT;

/*Table structure for table `rekap_presensi` */

DROP TABLE IF EXISTS `rekap_presensi`;

CREATE TABLE `rekap_presensi` (
  `id` int(10) NOT NULL,
  `shift` enum('Pagi','Pagi2','Pagi3','Pagi4','Pagi5','Pagi6','Pagi7','Pagi8','Pagi9','Pagi10','Siang','Siang2','Siang3','Siang4','Siang5','Siang6','Siang7','Siang8','Siang9','Siang10','Malam','Malam2','Malam3','Malam4','Malam5','Malam6','Malam7','Malam8','Malam9','Malam10','Midle Pagi1','Midle Pagi2','Midle Pagi3','Midle Pagi4','Midle Pagi5','Midle Pagi6','Midle Pagi7','Midle Pagi8','Midle Pagi9','Midle Pagi10','Midle Siang1','Midle Siang2','Midle Siang3','Midle Siang4','Midle Siang5','Midle Siang6','Midle Siang7','Midle Siang8','Midle Siang9','Midle Siang10','Midle Malam1','Midle Malam2','Midle Malam3','Midle Malam4','Midle Malam5','Midle Malam6','Midle Malam7','Midle Malam8','Midle Malam9','Midle Malam10') NOT NULL,
  `jam_datang` datetime NOT NULL DEFAULT '0000-00-00 00:00:00',
  `jam_pulang` datetime DEFAULT NULL,
  `status` enum('Tepat Waktu','Terlambat Toleransi','Terlambat I','Terlambat II','Tepat Waktu & PSW','Terlambat Toleransi & PSW','Terlambat I & PSW','Terlambat II & PSW') NOT NULL,
  `keterlambatan` varchar(20) NOT NULL,
  `durasi` varchar(20) DEFAULT NULL,
  `keterangan` varchar(100) NOT NULL,
  `photo` varchar(500) NOT NULL,
  PRIMARY KEY (`id`,`jam_datang`),
  KEY `id` (`id`),
  CONSTRAINT `rekap_presensi_ibfk_1` FOREIGN KEY (`id`) REFERENCES `pegawai` (`id`) ON UPDATE CASCADE
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

/*Table structure for table `rekening` */

DROP TABLE IF EXISTS `rekening`;

CREATE TABLE `rekening` (
  `kd_rek` varchar(15) NOT NULL DEFAULT '',
  `nm_rek` varchar(100) DEFAULT NULL,
  `tipe` enum('N','M','R') DEFAULT NULL,
  `balance` enum('D','K') DEFAULT NULL,
  `level` enum('0','1') DEFAULT NULL,
  PRIMARY KEY (`kd_rek`),
  KEY `nm_rek` (`nm_rek`) USING BTREE,
  KEY `tipe` (`tipe`) USING BTREE,
  KEY `balance` (`balance`) USING BTREE
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

/*Table structure for table `rekeningtahun` */

DROP TABLE IF EXISTS `rekeningtahun`;

CREATE TABLE `rekeningtahun` (
  `thn` year(4) NOT NULL,
  `kd_rek` varchar(15) NOT NULL DEFAULT '',
  `saldo_awal` double NOT NULL,
  PRIMARY KEY (`thn`,`kd_rek`),
  KEY `kd_rek` (`kd_rek`),
  KEY `saldo_awal` (`saldo_awal`),
  CONSTRAINT `rekeningtahun_ibfk_1` FOREIGN KEY (`kd_rek`) REFERENCES `rekening` (`kd_rek`) ON UPDATE CASCADE
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

/*Table structure for table `resep_dokter` */

DROP TABLE IF EXISTS `resep_dokter`;

CREATE TABLE `resep_dokter` (
  `no_resep` varchar(10) DEFAULT NULL,
  `kode_brng` varchar(15) DEFAULT NULL,
  `jml` double DEFAULT NULL,
  `aturan_pakai` varchar(150) DEFAULT NULL,
  KEY `no_resep` (`no_resep`) USING BTREE,
  KEY `kode_brng` (`kode_brng`) USING BTREE,
  CONSTRAINT `resep_dokter_ibfk_1` FOREIGN KEY (`no_resep`) REFERENCES `resep_obat` (`no_resep`) ON DELETE CASCADE ON UPDATE CASCADE,
  CONSTRAINT `resep_dokter_ibfk_2` FOREIGN KEY (`kode_brng`) REFERENCES `databarang` (`kode_brng`) ON DELETE CASCADE ON UPDATE CASCADE
) ENGINE=InnoDB DEFAULT CHARSET=latin1 ROW_FORMAT=COMPACT;

/*Table structure for table `resep_obat` */

DROP TABLE IF EXISTS `resep_obat`;

CREATE TABLE `resep_obat` (
  `no_resep` varchar(10) NOT NULL DEFAULT '',
  `tgl_perawatan` date DEFAULT NULL,
  `jam` time NOT NULL,
  `no_rawat` varchar(18) NOT NULL DEFAULT '',
  `kd_dokter` varchar(20) NOT NULL,
  `tgl_peresepan` date DEFAULT NULL,
  `jam_peresepan` time DEFAULT NULL,
  PRIMARY KEY (`no_resep`),
  UNIQUE KEY `tgl_perawatan` (`tgl_perawatan`,`jam`,`no_rawat`),
  KEY `no_rawat` (`no_rawat`),
  KEY `kd_dokter` (`kd_dokter`),
  CONSTRAINT `resep_obat_ibfk_3` FOREIGN KEY (`no_rawat`) REFERENCES `reg_periksa` (`no_rawat`) ON UPDATE CASCADE,
  CONSTRAINT `resep_obat_ibfk_4` FOREIGN KEY (`kd_dokter`) REFERENCES `dokter` (`kd_dokter`) ON DELETE CASCADE ON UPDATE CASCADE
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

/*Table structure for table `resep_pulang` */

DROP TABLE IF EXISTS `resep_pulang`;

CREATE TABLE `resep_pulang` (
  `no_rawat` varchar(17) NOT NULL,
  `kode_brng` varchar(15) NOT NULL,
  `jml_barang` double NOT NULL,
  `harga` double NOT NULL,
  `total` double NOT NULL,
  `dosis` varchar(20) NOT NULL,
  PRIMARY KEY (`no_rawat`,`kode_brng`),
  KEY `kode_brng` (`kode_brng`),
  CONSTRAINT `resep_pulang_ibfk_2` FOREIGN KEY (`kode_brng`) REFERENCES `databarang` (`kode_brng`) ON DELETE CASCADE ON UPDATE CASCADE,
  CONSTRAINT `resep_pulang_ibfk_3` FOREIGN KEY (`no_rawat`) REFERENCES `reg_periksa` (`no_rawat`) ON UPDATE CASCADE
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

/*Table structure for table `retensi_pasien` */

DROP TABLE IF EXISTS `retensi_pasien`;

CREATE TABLE `retensi_pasien` (
  `no_rkm_medis` varchar(15) DEFAULT NULL,
  `terakhir_daftar` date DEFAULT NULL,
  `tgl_retensi` date DEFAULT NULL,
  `lokasi_pdf` varchar(500) DEFAULT NULL,
  KEY `no_rkm_medis` (`no_rkm_medis`),
  CONSTRAINT `retensi_pasien_ibfk_1` FOREIGN KEY (`no_rkm_medis`) REFERENCES `pasien` (`no_rkm_medis`) ON DELETE CASCADE ON UPDATE CASCADE
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

/*Table structure for table `returbeli` */

DROP TABLE IF EXISTS `returbeli`;

CREATE TABLE `returbeli` (
  `no_retur_beli` varchar(20) NOT NULL,
  `tgl_retur` date DEFAULT NULL,
  `nip` char(20) DEFAULT NULL,
  `kode_suplier` char(5) NOT NULL,
  `kd_bangsal` char(5) NOT NULL,
  PRIMARY KEY (`no_retur_beli`),
  KEY `nip` (`nip`),
  KEY `kode_suplier` (`kode_suplier`),
  KEY `kd_bangsal` (`kd_bangsal`),
  CONSTRAINT `returbeli_ibfk_2` FOREIGN KEY (`kode_suplier`) REFERENCES `datasuplier` (`kode_suplier`) ON DELETE CASCADE ON UPDATE CASCADE,
  CONSTRAINT `returbeli_ibfk_3` FOREIGN KEY (`nip`) REFERENCES `petugas` (`nip`) ON DELETE CASCADE ON UPDATE CASCADE,
  CONSTRAINT `returbeli_ibfk_4` FOREIGN KEY (`kd_bangsal`) REFERENCES `bangsal` (`kd_bangsal`) ON DELETE CASCADE ON UPDATE CASCADE
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

/*Table structure for table `returjual` */

DROP TABLE IF EXISTS `returjual`;

CREATE TABLE `returjual` (
  `no_retur_jual` varchar(20) NOT NULL,
  `tgl_retur` date NOT NULL,
  `nip` char(20) DEFAULT NULL,
  `no_rkm_medis` varchar(15) NOT NULL,
  `kd_bangsal` char(5) NOT NULL,
  PRIMARY KEY (`no_retur_jual`,`tgl_retur`) USING BTREE,
  KEY `nip` (`nip`),
  KEY `no_rkm_medis` (`no_rkm_medis`),
  KEY `kd_bangsal` (`kd_bangsal`),
  KEY `tgl_retur` (`tgl_retur`),
  CONSTRAINT `returjual_ibfk_6` FOREIGN KEY (`nip`) REFERENCES `petugas` (`nip`) ON DELETE CASCADE ON UPDATE CASCADE,
  CONSTRAINT `returjual_ibfk_7` FOREIGN KEY (`no_rkm_medis`) REFERENCES `pasien` (`no_rkm_medis`) ON DELETE CASCADE ON UPDATE CASCADE,
  CONSTRAINT `returjual_ibfk_8` FOREIGN KEY (`kd_bangsal`) REFERENCES `bangsal` (`kd_bangsal`) ON DELETE CASCADE ON UPDATE CASCADE
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

/*Table structure for table `returpasien` */

DROP TABLE IF EXISTS `returpasien`;

CREATE TABLE `returpasien` (
  `tanggal` date NOT NULL,
  `no_rawat` varchar(18) NOT NULL,
  `kode_brng` varchar(15) NOT NULL,
  `jml` double NOT NULL,
  PRIMARY KEY (`tanggal`,`no_rawat`,`kode_brng`),
  KEY `kode_brng` (`kode_brng`),
  KEY `no_rawat` (`no_rawat`),
  CONSTRAINT `returpasien_ibfk_3` FOREIGN KEY (`no_rawat`) REFERENCES `reg_periksa` (`no_rawat`) ON UPDATE CASCADE,
  CONSTRAINT `returpasien_ibfk_4` FOREIGN KEY (`kode_brng`) REFERENCES `databarang` (`kode_brng`) ON DELETE CASCADE ON UPDATE CASCADE
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

/*Table structure for table `returpiutang` */

DROP TABLE IF EXISTS `returpiutang`;

CREATE TABLE `returpiutang` (
  `no_retur_piutang` varchar(20) NOT NULL,
  `tgl_retur` date DEFAULT NULL,
  `nip` char(20) DEFAULT NULL,
  `no_rkm_medis` varchar(15) NOT NULL,
  `kd_bangsal` char(5) NOT NULL,
  PRIMARY KEY (`no_retur_piutang`),
  KEY `nip` (`nip`),
  KEY `no_rkm_medis` (`no_rkm_medis`),
  KEY `kd_bangsal` (`kd_bangsal`),
  CONSTRAINT `returpiutang_ibfk_3` FOREIGN KEY (`nip`) REFERENCES `petugas` (`nip`) ON DELETE CASCADE ON UPDATE CASCADE,
  CONSTRAINT `returpiutang_ibfk_4` FOREIGN KEY (`no_rkm_medis`) REFERENCES `pasien` (`no_rkm_medis`) ON UPDATE CASCADE,
  CONSTRAINT `returpiutang_ibfk_5` FOREIGN KEY (`kd_bangsal`) REFERENCES `bangsal` (`kd_bangsal`) ON DELETE CASCADE ON UPDATE CASCADE
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

/*Table structure for table `review_rm_detail_analisa` */

DROP TABLE IF EXISTS `review_rm_detail_analisa`;

CREATE TABLE `review_rm_detail_analisa` (
  `no_rawat` varchar(17) NOT NULL,
  `id_komp_analisa` varchar(4) NOT NULL,
  `jns_analisis` enum('-','igd','h1inap','hpulang') NOT NULL DEFAULT '-',
  `review` enum('-','L','TL','TA','TD') NOT NULL DEFAULT '-',
  `nip` varchar(20) NOT NULL DEFAULT '-',
  `waktu_input` datetime NOT NULL DEFAULT current_timestamp(),
  `waktu_update` datetime DEFAULT NULL ON UPDATE current_timestamp(),
  PRIMARY KEY (`no_rawat`,`id_komp_analisa`,`jns_analisis`),
  KEY `FK_review_rm_detail_analisa_petugas` (`nip`) USING BTREE,
  KEY `FK_review_rm_detail_analisa_review_rm_komp_analisa` (`id_komp_analisa`),
  CONSTRAINT `FK_review_rm_detail_analisa_petugas` FOREIGN KEY (`nip`) REFERENCES `petugas` (`nip`) ON UPDATE CASCADE,
  CONSTRAINT `FK_review_rm_detail_analisa_reg_periksa` FOREIGN KEY (`no_rawat`) REFERENCES `reg_periksa` (`no_rawat`) ON UPDATE CASCADE,
  CONSTRAINT `FK_review_rm_detail_analisa_review_rm_komp_analisa` FOREIGN KEY (`id_komp_analisa`) REFERENCES `review_rm_komp_analisa` (`id_komp_analisa`) ON UPDATE CASCADE
) ENGINE=InnoDB DEFAULT CHARSET=latin1 ROW_FORMAT=COMPACT;

/*Table structure for table `review_rm_komp_analisa` */

DROP TABLE IF EXISTS `review_rm_komp_analisa`;

CREATE TABLE `review_rm_komp_analisa` (
  `id_komp_analisa` varchar(4) NOT NULL,
  `nm_komp_analisa` text NOT NULL,
  `id_komp_kategori` varchar(4) NOT NULL DEFAULT '',
  `komp_IGD` enum('0','1') NOT NULL DEFAULT '1',
  `komp_H_1_ruangan` enum('0','1') NOT NULL DEFAULT '1',
  `komp_H_pulang` enum('0','1') NOT NULL DEFAULT '1',
  `aktif` enum('0','1') NOT NULL DEFAULT '1',
  PRIMARY KEY (`id_komp_analisa`),
  KEY `FK_review_rm_komp_analisa_review_rm_komponen_kategori` (`id_komp_kategori`) USING BTREE,
  CONSTRAINT `FK_review_rm_komp_analisa_review_rm_komponen_kategori` FOREIGN KEY (`id_komp_kategori`) REFERENCES `review_rm_komp_kategori` (`id_komp_kategori`) ON UPDATE CASCADE
) ENGINE=InnoDB DEFAULT CHARSET=latin1 ROW_FORMAT=COMPACT;

/*Table structure for table `review_rm_komp_kategori` */

DROP TABLE IF EXISTS `review_rm_komp_kategori`;

CREATE TABLE `review_rm_komp_kategori` (
  `id_komp_kategori` varchar(4) NOT NULL,
  `nm_komp_kategori` varchar(200) NOT NULL,
  PRIMARY KEY (`id_komp_kategori`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1 ROW_FORMAT=COMPACT;

/*Table structure for table `review_rm_tidak_lengkap` */

DROP TABLE IF EXISTS `review_rm_tidak_lengkap`;

CREATE TABLE `review_rm_tidak_lengkap` (
  `no_rawat` varchar(17) NOT NULL,
  `id_komp_analisa` varchar(4) NOT NULL,
  `jns_analisis` enum('-','igd','h1inap','hpulang') NOT NULL DEFAULT '-',
  `ket_tidak_lengkap` varchar(250) NOT NULL DEFAULT '-',
  PRIMARY KEY (`no_rawat`,`id_komp_analisa`,`jns_analisis`),
  KEY `FK_review_rm_tidak_lengkap_review_rm_komp_analisa` (`id_komp_analisa`),
  CONSTRAINT `FK_review_rm_tidak_lengkap_review_rm_detail_analisa` FOREIGN KEY (`no_rawat`) REFERENCES `review_rm_detail_analisa` (`no_rawat`) ON UPDATE CASCADE,
  CONSTRAINT `FK_review_rm_tidak_lengkap_review_rm_komp_analisa` FOREIGN KEY (`id_komp_analisa`) REFERENCES `review_rm_komp_analisa` (`id_komp_analisa`) ON UPDATE CASCADE
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

/*Table structure for table `ringkasan_pulang_ranap` */

DROP TABLE IF EXISTS `ringkasan_pulang_ranap`;

CREATE TABLE `ringkasan_pulang_ranap` (
  `no_rawat` varchar(17) NOT NULL,
  `alasan_masuk_dirawat` longtext DEFAULT NULL,
  `ringkasan_riwayat_penyakit` longtext DEFAULT NULL,
  `pemeriksaan_fisik` longtext DEFAULT NULL,
  `pemeriksaan_penunjang` longtext DEFAULT NULL,
  `terapi_pengobatan` longtext DEFAULT NULL,
  `diagnosa_utama` longtext DEFAULT NULL,
  `diagnosa_sekunder` longtext DEFAULT NULL,
  `keadaan_umum` longtext DEFAULT NULL,
  `kesadaran` longtext DEFAULT NULL,
  `tekanan_darah` varchar(5) DEFAULT NULL,
  `suhu` varchar(5) DEFAULT NULL,
  `nadi` varchar(5) DEFAULT NULL,
  `frekuensi_nafas` varchar(5) DEFAULT NULL,
  `catatan_penting` longtext DEFAULT NULL,
  `terapi_pulang` longtext DEFAULT NULL,
  `pengobatan_dilanjutkan` enum('Poliklinik','RS Lain','Puskesmas','Dokter Luar','-') DEFAULT NULL,
  `tgl_kontrol_poliklinik` date DEFAULT NULL,
  `nm_dokter_pengirim` varchar(150) DEFAULT NULL,
  `GCS` varchar(255) DEFAULT NULL,
  `tindakan_prosedur` longtext DEFAULT NULL,
  `dokter_luar_lanjutan` varchar(150) DEFAULT NULL,
  PRIMARY KEY (`no_rawat`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1 ROW_FORMAT=COMPACT;

/*Table structure for table `riwayat_barang_medis` */

DROP TABLE IF EXISTS `riwayat_barang_medis`;

CREATE TABLE `riwayat_barang_medis` (
  `kode_brng` varchar(15) DEFAULT NULL,
  `stok_awal` double DEFAULT NULL,
  `masuk` double DEFAULT NULL,
  `keluar` double DEFAULT NULL,
  `stok_akhir` double NOT NULL,
  `posisi` enum('Pemberian Obat','Pengadaan','Pemesanan','Piutang','Retur Beli','Retur Jual','Retur Piutang','Mutasi','Opname','Resep Pulang','Retur Pasien','Stok Pasien Ranap','Pengambilan Medis','Penjualan','Ubah Pemesanan') DEFAULT NULL,
  `tanggal` date DEFAULT NULL,
  `jam` time DEFAULT NULL,
  `petugas` varchar(20) DEFAULT NULL,
  `kd_bangsal` char(5) DEFAULT NULL,
  `status` enum('Simpan','Hapus') DEFAULT NULL,
  KEY `riwayat_barang_medis_ibfk_1` (`kode_brng`),
  KEY `kd_bangsal` (`kd_bangsal`),
  KEY `tanggal` (`tanggal`) USING BTREE,
  KEY `posisi` (`posisi`) USING BTREE,
  KEY `status` (`status`) USING BTREE,
  CONSTRAINT `riwayat_barang_medis_ibfk_1` FOREIGN KEY (`kode_brng`) REFERENCES `databarang` (`kode_brng`) ON DELETE CASCADE ON UPDATE CASCADE,
  CONSTRAINT `riwayat_barang_medis_ibfk_2` FOREIGN KEY (`kd_bangsal`) REFERENCES `bangsal` (`kd_bangsal`) ON DELETE CASCADE ON UPDATE CASCADE
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

/*Table structure for table `riwayat_jabatan` */

DROP TABLE IF EXISTS `riwayat_jabatan`;

CREATE TABLE `riwayat_jabatan` (
  `id` int(11) NOT NULL,
  `jabatan` varchar(50) NOT NULL,
  `tmt_pangkat` date NOT NULL,
  `tmt_pangkat_yad` date NOT NULL,
  `pejabat_penetap` varchar(50) NOT NULL,
  `nomor_sk` varchar(25) NOT NULL,
  `tgl_sk` date NOT NULL,
  `dasar_peraturan` varchar(50) NOT NULL,
  `masa_kerja` int(11) NOT NULL,
  `bln_kerja` int(11) NOT NULL,
  PRIMARY KEY (`id`,`jabatan`),
  KEY `jnj_jabatan` (`jabatan`),
  CONSTRAINT `riwayat_jabatan_ibfk_1` FOREIGN KEY (`id`) REFERENCES `pegawai` (`id`) ON UPDATE CASCADE
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

/*Table structure for table `riwayat_naik_gaji` */

DROP TABLE IF EXISTS `riwayat_naik_gaji`;

CREATE TABLE `riwayat_naik_gaji` (
  `id` int(11) NOT NULL,
  `pangkatjabatan` varchar(50) NOT NULL,
  `gapok` double NOT NULL,
  `tmt_berkala` date NOT NULL,
  `tmt_berkala_yad` date NOT NULL,
  `no_sk` varchar(25) NOT NULL,
  `tgl_sk` date NOT NULL,
  `masa_kerja` int(11) NOT NULL,
  `bulan_kerja` int(11) NOT NULL,
  PRIMARY KEY (`id`,`pangkatjabatan`,`gapok`),
  CONSTRAINT `riwayat_naik_gaji_ibfk_1` FOREIGN KEY (`id`) REFERENCES `pegawai` (`id`) ON UPDATE CASCADE
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

/*Table structure for table `riwayat_obat_pasien` */

DROP TABLE IF EXISTS `riwayat_obat_pasien`;

CREATE TABLE `riwayat_obat_pasien` (
  `kode_brng` varchar(15) DEFAULT NULL,
  `stok_awal` double DEFAULT NULL,
  `masuk` double DEFAULT NULL,
  `keluar` double DEFAULT NULL,
  `stok_akhir` double NOT NULL,
  `posisi` enum('Pemberian Obat','Pengadaan','Pemesanan','Piutang','Retur Beli','Retur Jual','Retur Piutang','Mutasi','Opname','Resep Pulang','Retur Pasien','Stok Pasien Ranap','Pengambilan Medis','Penjualan') DEFAULT NULL,
  `tanggal` date DEFAULT NULL,
  `jam` time DEFAULT NULL,
  `petugas` varchar(20) DEFAULT NULL,
  `kd_bangsal` char(5) DEFAULT NULL,
  `status` enum('Simpan','Hapus','Ganti Hapus','Ganti Simpan') DEFAULT NULL,
  `no_rkm_medis` varchar(15) DEFAULT NULL,
  `no_rawat` varchar(18) DEFAULT NULL,
  KEY `riwayat_barang_medis_ibfk_1` (`kode_brng`) USING BTREE,
  KEY `kd_bangsal` (`kd_bangsal`) USING BTREE,
  KEY `riwayat_obat_pasien_ibfk_3` (`no_rkm_medis`) USING BTREE,
  KEY `riwayat_obat_pasien_ibfk_4` (`no_rawat`) USING BTREE,
  CONSTRAINT `riwayat_obat_pasien_ibfk_1` FOREIGN KEY (`kode_brng`) REFERENCES `databarang` (`kode_brng`) ON DELETE CASCADE ON UPDATE CASCADE,
  CONSTRAINT `riwayat_obat_pasien_ibfk_2` FOREIGN KEY (`kd_bangsal`) REFERENCES `bangsal` (`kd_bangsal`) ON DELETE CASCADE ON UPDATE CASCADE,
  CONSTRAINT `riwayat_obat_pasien_ibfk_3` FOREIGN KEY (`no_rkm_medis`) REFERENCES `pasien` (`no_rkm_medis`) ON DELETE CASCADE ON UPDATE CASCADE,
  CONSTRAINT `riwayat_obat_pasien_ibfk_4` FOREIGN KEY (`no_rawat`) REFERENCES `reg_periksa` (`no_rawat`) ON DELETE CASCADE ON UPDATE CASCADE
) ENGINE=InnoDB DEFAULT CHARSET=latin1 ROW_FORMAT=COMPACT;

/*Table structure for table `riwayat_pendidikan` */

DROP TABLE IF EXISTS `riwayat_pendidikan`;

CREATE TABLE `riwayat_pendidikan` (
  `id` int(11) NOT NULL,
  `pendidikan` enum('SD','SMP','SMA','SMK','D I','D II','D III','D IV','S1','S2','S3','Post Doctor') NOT NULL,
  `sekolah` varchar(50) NOT NULL,
  `jurusan` varchar(40) NOT NULL,
  `thn_lulus` year(4) NOT NULL,
  `kepala` varchar(50) NOT NULL,
  `pendanaan` enum('Biaya Sendiri','Biaya Instansi Sendiri','Lembaga Swasta Kerjasama','Lembaga Swasta Kompetisi','Lembaga Pemerintah Kerjasama','Lembaga Pemerintah Kompetisi','Lembaga Internasional') NOT NULL,
  `keterangan` varchar(50) NOT NULL,
  `status` varchar(40) NOT NULL,
  PRIMARY KEY (`id`,`pendidikan`,`sekolah`),
  CONSTRAINT `riwayat_pendidikan_ibfk_1` FOREIGN KEY (`id`) REFERENCES `pegawai` (`id`) ON UPDATE CASCADE
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

/*Table structure for table `riwayat_seminar` */

DROP TABLE IF EXISTS `riwayat_seminar`;

CREATE TABLE `riwayat_seminar` (
  `id` int(11) NOT NULL,
  `tingkat` enum('Local','Regional','Nasional','Internasional') NOT NULL,
  `jenis` enum('WORKSHOP','SIMPOSIUM','SEMINAR','FGD','PELATIHAN','LAINNYA') NOT NULL,
  `nama_seminar` varchar(50) NOT NULL,
  `peranan` varchar(40) NOT NULL,
  `mulai` date NOT NULL,
  `selesai` date NOT NULL,
  `penyelengara` varchar(50) NOT NULL,
  `tempat` varchar(50) NOT NULL,
  PRIMARY KEY (`id`,`nama_seminar`,`mulai`),
  KEY `id` (`id`),
  CONSTRAINT `riwayat_seminar_ibfk_1` FOREIGN KEY (`id`) REFERENCES `pegawai` (`id`) ON UPDATE CASCADE
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

/*Table structure for table `riwayat_tarif_kamar` */

DROP TABLE IF EXISTS `riwayat_tarif_kamar`;

CREATE TABLE `riwayat_tarif_kamar` (
  `kd_kamar` varchar(15) NOT NULL,
  `kd_bangsal` char(5) DEFAULT NULL,
  `trf_kamar` double DEFAULT NULL,
  `kelas` enum('Kelas 1','Kelas 2','Kelas 3','Kelas Utama','Kelas VIP','Kelas VVIP','Rawat Khusus') DEFAULT NULL,
  `statusdata` enum('0','1') DEFAULT NULL,
  `tgl_tarif_berlaku` date DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

/*Table structure for table `rujuk` */

DROP TABLE IF EXISTS `rujuk`;

CREATE TABLE `rujuk` (
  `no_rujuk` varchar(40) NOT NULL,
  `no_rawat` varchar(18) DEFAULT NULL,
  `rujuk_ke` varchar(100) DEFAULT NULL,
  `tgl_rujuk` date DEFAULT NULL,
  `keterangan_diagnosa` text DEFAULT NULL,
  `kd_dokter` varchar(20) DEFAULT NULL,
  `kat_rujuk` enum('-','Bedah','Non Bedah','Kebidanan','Anak') DEFAULT NULL,
  `ambulance` enum('-','AGD','SENDIRI') DEFAULT NULL,
  `keterangan` text DEFAULT NULL,
  `jam` time DEFAULT NULL,
  `kd_rujukan` int(11) DEFAULT NULL,
  KEY `no_rawat` (`no_rawat`),
  KEY `kd_dokter` (`kd_dokter`),
  KEY `rujuk_ibfk_3` (`kd_rujukan`) USING BTREE,
  CONSTRAINT `rujuk_ibfk_1` FOREIGN KEY (`kd_dokter`) REFERENCES `dokter` (`kd_dokter`) ON DELETE CASCADE ON UPDATE CASCADE,
  CONSTRAINT `rujuk_ibfk_2` FOREIGN KEY (`no_rawat`) REFERENCES `reg_periksa` (`no_rawat`) ON DELETE NO ACTION ON UPDATE CASCADE,
  CONSTRAINT `rujuk_ibfk_3` FOREIGN KEY (`kd_rujukan`) REFERENCES `master_nama_rujukan` (`kd_rujukan`) ON DELETE NO ACTION ON UPDATE CASCADE
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

/*Table structure for table `rujuk_keluar_sisrute` */

DROP TABLE IF EXISTS `rujuk_keluar_sisrute`;

CREATE TABLE `rujuk_keluar_sisrute` (
  `no_rujuk` varchar(40) NOT NULL,
  `no_rawat` varchar(18) DEFAULT NULL,
  `rujuk_ke` varchar(100) DEFAULT NULL,
  `tgl_rujuk` date DEFAULT NULL,
  `keterangan_diagnosa` text DEFAULT NULL,
  `kd_dokter` varchar(20) DEFAULT NULL,
  `kat_rujuk` enum('-','Bedah','Non Bedah','Kebidanan','Anak') DEFAULT NULL,
  `ambulance` enum('-','AGD','SENDIRI') DEFAULT NULL,
  `keterangan` text DEFAULT NULL,
  `jam` time DEFAULT NULL,
  KEY `no_rawat` (`no_rawat`) USING BTREE,
  KEY `kd_dokter` (`kd_dokter`) USING BTREE,
  CONSTRAINT `rujuk_keluar_sisrute_ibfk_1` FOREIGN KEY (`kd_dokter`) REFERENCES `dokter` (`kd_dokter`) ON DELETE CASCADE ON UPDATE CASCADE,
  CONSTRAINT `rujuk_keluar_sisrute_ibfk_2` FOREIGN KEY (`no_rawat`) REFERENCES `reg_periksa` (`no_rawat`) ON DELETE NO ACTION ON UPDATE CASCADE
) ENGINE=InnoDB DEFAULT CHARSET=latin1 ROW_FORMAT=COMPACT;

/*Table structure for table `rujuk_masuk` */

DROP TABLE IF EXISTS `rujuk_masuk`;

CREATE TABLE `rujuk_masuk` (
  `no_rawat` varchar(17) NOT NULL DEFAULT '',
  `perujuk` varchar(60) DEFAULT NULL,
  `alamat` varchar(70) NOT NULL,
  `no_rujuk` varchar(40) NOT NULL,
  `jm_perujuk` double NOT NULL,
  `dokter_perujuk` varchar(50) DEFAULT NULL,
  `kd_penyakit` varchar(10) DEFAULT NULL,
  `kategori_rujuk` enum('-','Bedah','Non-Bedah','Kebidanan','Anak') DEFAULT NULL,
  `keterangan` varchar(200) DEFAULT NULL,
  `no_balasan` varchar(20) DEFAULT NULL,
  `kd_rujukan` int(11) DEFAULT NULL,
  PRIMARY KEY (`no_rawat`),
  KEY `no_rawat` (`no_rawat`),
  KEY `kd_dokter` (`perujuk`),
  KEY `perujuk` (`perujuk`),
  KEY `alamat` (`alamat`),
  KEY `jm_perujuk` (`jm_perujuk`),
  KEY `kd_penyakit` (`kd_penyakit`) USING BTREE,
  KEY `rujuk_masuk_ibfk_2` (`kd_rujukan`) USING BTREE,
  CONSTRAINT `rujuk_masuk_ibfk_1` FOREIGN KEY (`no_rawat`) REFERENCES `reg_periksa` (`no_rawat`) ON DELETE NO ACTION ON UPDATE CASCADE,
  CONSTRAINT `rujuk_masuk_ibfk_2` FOREIGN KEY (`kd_rujukan`) REFERENCES `master_nama_rujukan` (`kd_rujukan`) ON DELETE NO ACTION ON UPDATE CASCADE
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

/*Table structure for table `rujuk_masuk_inhealth` */

DROP TABLE IF EXISTS `rujuk_masuk_inhealth`;

CREATE TABLE `rujuk_masuk_inhealth` (
  `no_rawat` varchar(17) NOT NULL DEFAULT '',
  `perujuk` varchar(60) DEFAULT NULL,
  `alamat` varchar(70) NOT NULL,
  `no_rujuk` varchar(40) NOT NULL,
  `jm_perujuk` double NOT NULL,
  `dokter_perujuk` varchar(50) DEFAULT NULL,
  `kd_penyakit` varchar(10) DEFAULT NULL,
  `kategori_rujuk` enum('-','Bedah','Non-Bedah','Kebidanan','Anak') DEFAULT NULL,
  `keterangan` varchar(200) DEFAULT NULL,
  `no_balasan` varchar(20) DEFAULT NULL,
  `kd_rujukan` int(11) DEFAULT NULL,
  PRIMARY KEY (`no_rawat`),
  KEY `no_rawat` (`no_rawat`) USING BTREE,
  KEY `kd_dokter` (`perujuk`) USING BTREE,
  KEY `perujuk` (`perujuk`) USING BTREE,
  KEY `alamat` (`alamat`) USING BTREE,
  KEY `jm_perujuk` (`jm_perujuk`) USING BTREE,
  KEY `kd_penyakit` (`kd_penyakit`) USING BTREE,
  KEY `rujuk_masuk_ibfk_2` (`kd_rujukan`) USING BTREE,
  CONSTRAINT `rujuk_masuk_inhealth_ibfk_1` FOREIGN KEY (`no_rawat`) REFERENCES `reg_periksa` (`no_rawat`) ON DELETE NO ACTION ON UPDATE CASCADE
) ENGINE=InnoDB DEFAULT CHARSET=latin1 ROW_FORMAT=COMPACT;

/*Table structure for table `rujukan_internal_poli` */

DROP TABLE IF EXISTS `rujukan_internal_poli`;

CREATE TABLE `rujukan_internal_poli` (
  `no_rawat` varchar(18) NOT NULL,
  `kd_poli` char(50) NOT NULL,
  `keterangan` longtext DEFAULT NULL,
  `tgl_rencana_dirujuk` date DEFAULT NULL,
  PRIMARY KEY (`no_rawat`,`kd_poli`),
  KEY `kd_poli` (`kd_poli`) USING BTREE,
  CONSTRAINT `FK_rujukan_internal_poli_poliklinik` FOREIGN KEY (`kd_poli`) REFERENCES `poliklinik` (`kd_poli`) ON DELETE CASCADE ON UPDATE CASCADE,
  CONSTRAINT `rujukan_internal_poli_ibfk_1` FOREIGN KEY (`no_rawat`) REFERENCES `reg_periksa` (`no_rawat`) ON DELETE CASCADE ON UPDATE CASCADE
) ENGINE=InnoDB DEFAULT CHARSET=latin1 ROW_FORMAT=COMPACT;

/*Table structure for table `rujukanranap_dokter_rs` */

DROP TABLE IF EXISTS `rujukanranap_dokter_rs`;

CREATE TABLE `rujukanranap_dokter_rs` (
  `tanggal` date NOT NULL,
  `kd_dokter` varchar(20) NOT NULL,
  `no_rkm_medis` varchar(15) NOT NULL,
  `kd_kamar` varchar(15) NOT NULL,
  `jasarujuk` double NOT NULL,
  PRIMARY KEY (`tanggal`,`kd_dokter`,`no_rkm_medis`,`kd_kamar`),
  KEY `no_rkm_medis` (`no_rkm_medis`),
  KEY `kd_kamar` (`kd_kamar`),
  KEY `rujukanranap_dokter_rs_ibfk_1` (`kd_dokter`),
  CONSTRAINT `rujukanranap_dokter_rs_ibfk_1` FOREIGN KEY (`kd_dokter`) REFERENCES `dokter` (`kd_dokter`) ON DELETE CASCADE ON UPDATE CASCADE,
  CONSTRAINT `rujukanranap_dokter_rs_ibfk_2` FOREIGN KEY (`no_rkm_medis`) REFERENCES `pasien` (`no_rkm_medis`) ON UPDATE CASCADE,
  CONSTRAINT `rujukanranap_dokter_rs_ibfk_3` FOREIGN KEY (`kd_kamar`) REFERENCES `kamar` (`kd_kamar`) ON DELETE CASCADE ON UPDATE CASCADE
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

/*Table structure for table `runtext` */

DROP TABLE IF EXISTS `runtext`;

CREATE TABLE `runtext` (
  `teks` text NOT NULL,
  `aktifkan` enum('Yes','No') NOT NULL,
  `gambar` longblob NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

/*Table structure for table `sensus_poli_paru` */

DROP TABLE IF EXISTS `sensus_poli_paru`;

CREATE TABLE `sensus_poli_paru` (
  `no_rawat` varchar(17) NOT NULL,
  `kasus_ppok` enum('SERANGAN','KONSULTASI','-') DEFAULT NULL,
  `obat_TB` enum('LAIN-LAIN','BPJS','DOT','PUSKESMAS','-') DEFAULT NULL,
  `keterangan` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`no_rawat`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1 ROW_FORMAT=COMPACT;

/*Table structure for table `set_akte` */

DROP TABLE IF EXISTS `set_akte`;

CREATE TABLE `set_akte` (
  `tahun` year(4) NOT NULL,
  `bulan` tinyint(4) NOT NULL,
  `pendapatan_akte` double NOT NULL,
  `persen_rs` double NOT NULL,
  `bagian_rs` double NOT NULL,
  `persen_kry` double NOT NULL,
  `bagian_kry` double NOT NULL,
  PRIMARY KEY (`tahun`,`bulan`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

/*Table structure for table `set_akun` */

DROP TABLE IF EXISTS `set_akun`;

CREATE TABLE `set_akun` (
  `Pengadaan_Obat` varchar(15) DEFAULT NULL,
  `Pemesanan_Obat` varchar(15) DEFAULT NULL,
  `Kontra_Pemesanan_Obat` varchar(15) DEFAULT NULL,
  `Bayar_Pemesanan_Obat` varchar(15) DEFAULT NULL,
  `Penjualan_Obat` varchar(15) DEFAULT NULL,
  `Piutang_Obat` varchar(15) DEFAULT NULL,
  `Kontra_Piutang_Obat` varchar(15) DEFAULT NULL,
  `Retur_Ke_Suplayer` varchar(15) DEFAULT NULL,
  `Kontra_Retur_Ke_Suplayer` varchar(15) DEFAULT NULL,
  `Retur_Dari_pembeli` varchar(15) DEFAULT NULL,
  `Kontra_Retur_Dari_Pembeli` varchar(15) DEFAULT NULL,
  `Retur_Piutang_Obat` varchar(15) DEFAULT NULL,
  `Kontra_Retur_Piutang_Obat` varchar(15) DEFAULT NULL,
  `Pengadaan_Ipsrs` varchar(15) DEFAULT NULL,
  `Stok_Keluar_Ipsrs` varchar(15) DEFAULT NULL,
  `Kontra_Stok_Keluar_Ipsrs` varchar(15) DEFAULT NULL,
  `Bayar_Piutang_Pasien` varchar(15) DEFAULT NULL,
  `Pengambilan_Utd` varchar(15) DEFAULT NULL,
  `Kontra_Pengambilan_Utd` varchar(15) DEFAULT NULL,
  `Pengambilan_Penunjang_Utd` varchar(15) DEFAULT NULL,
  `Kontra_Pengambilan_Penunjang_Utd` varchar(15) DEFAULT NULL,
  `Penyerahan_Darah` varchar(15) DEFAULT NULL,
  KEY `Pengadaan_Obat` (`Pengadaan_Obat`) USING BTREE,
  KEY `Pemesanan_Obat` (`Pemesanan_Obat`) USING BTREE,
  KEY `Kontra_Pemesanan_Obat` (`Kontra_Pemesanan_Obat`) USING BTREE,
  KEY `Bayar_Pemesanan_Obat` (`Bayar_Pemesanan_Obat`) USING BTREE,
  KEY `Penjualan_Obat` (`Penjualan_Obat`) USING BTREE,
  KEY `Piutang_Obat` (`Piutang_Obat`) USING BTREE,
  KEY `Kontra_Piutang_Obat` (`Kontra_Piutang_Obat`) USING BTREE,
  KEY `Retur_Ke_Suplayer` (`Retur_Ke_Suplayer`) USING BTREE,
  KEY `Kontra_Retur_Ke_Suplayer` (`Kontra_Retur_Ke_Suplayer`) USING BTREE,
  KEY `Retur_Dari_pembeli` (`Retur_Dari_pembeli`) USING BTREE,
  KEY `Kontra_Retur_Dari_Pembeli` (`Kontra_Retur_Dari_Pembeli`) USING BTREE,
  KEY `Retur_Piutang_Obat` (`Retur_Piutang_Obat`) USING BTREE,
  KEY `Kontra_Retur_Piutang_Obat` (`Kontra_Retur_Piutang_Obat`) USING BTREE,
  KEY `Pengadaan_Ipsrs` (`Pengadaan_Ipsrs`) USING BTREE,
  KEY `Stok_Keluar_Ipsrs` (`Stok_Keluar_Ipsrs`) USING BTREE,
  KEY `Kontra_Stok_Keluar_Ipsrs` (`Kontra_Stok_Keluar_Ipsrs`) USING BTREE,
  KEY `Bayar_Piutang_Pasien` (`Bayar_Piutang_Pasien`) USING BTREE,
  KEY `Pengambilan_Utd` (`Pengambilan_Utd`) USING BTREE,
  KEY `Kontra_Pengambilan_Utd` (`Kontra_Pengambilan_Utd`) USING BTREE,
  KEY `Pengambilan_Penunjang_Utd` (`Pengambilan_Penunjang_Utd`) USING BTREE,
  KEY `Kontra_Pengambilan_Penunjang_Utd` (`Kontra_Pengambilan_Penunjang_Utd`) USING BTREE,
  KEY `Penyerahan_Darah` (`Penyerahan_Darah`) USING BTREE,
  CONSTRAINT `set_akun_ibfk_1` FOREIGN KEY (`Pengadaan_Obat`) REFERENCES `rekening` (`kd_rek`) ON UPDATE CASCADE,
  CONSTRAINT `set_akun_ibfk_10` FOREIGN KEY (`Retur_Dari_pembeli`) REFERENCES `rekening` (`kd_rek`) ON DELETE NO ACTION ON UPDATE CASCADE,
  CONSTRAINT `set_akun_ibfk_11` FOREIGN KEY (`Kontra_Retur_Dari_Pembeli`) REFERENCES `rekening` (`kd_rek`) ON DELETE NO ACTION ON UPDATE CASCADE,
  CONSTRAINT `set_akun_ibfk_12` FOREIGN KEY (`Retur_Piutang_Obat`) REFERENCES `rekening` (`kd_rek`) ON DELETE NO ACTION ON UPDATE CASCADE,
  CONSTRAINT `set_akun_ibfk_13` FOREIGN KEY (`Kontra_Retur_Piutang_Obat`) REFERENCES `rekening` (`kd_rek`) ON DELETE NO ACTION ON UPDATE CASCADE,
  CONSTRAINT `set_akun_ibfk_14` FOREIGN KEY (`Pengadaan_Ipsrs`) REFERENCES `rekening` (`kd_rek`) ON DELETE NO ACTION ON UPDATE CASCADE,
  CONSTRAINT `set_akun_ibfk_15` FOREIGN KEY (`Stok_Keluar_Ipsrs`) REFERENCES `rekening` (`kd_rek`) ON DELETE NO ACTION ON UPDATE CASCADE,
  CONSTRAINT `set_akun_ibfk_16` FOREIGN KEY (`Kontra_Stok_Keluar_Ipsrs`) REFERENCES `rekening` (`kd_rek`) ON DELETE NO ACTION ON UPDATE CASCADE,
  CONSTRAINT `set_akun_ibfk_17` FOREIGN KEY (`Bayar_Piutang_Pasien`) REFERENCES `rekening` (`kd_rek`) ON DELETE NO ACTION ON UPDATE CASCADE,
  CONSTRAINT `set_akun_ibfk_18` FOREIGN KEY (`Pengambilan_Utd`) REFERENCES `rekening` (`kd_rek`) ON DELETE NO ACTION ON UPDATE CASCADE,
  CONSTRAINT `set_akun_ibfk_19` FOREIGN KEY (`Kontra_Pengambilan_Utd`) REFERENCES `rekening` (`kd_rek`) ON DELETE NO ACTION ON UPDATE CASCADE,
  CONSTRAINT `set_akun_ibfk_2` FOREIGN KEY (`Pemesanan_Obat`) REFERENCES `rekening` (`kd_rek`) ON DELETE NO ACTION ON UPDATE CASCADE,
  CONSTRAINT `set_akun_ibfk_20` FOREIGN KEY (`Pengambilan_Penunjang_Utd`) REFERENCES `rekening` (`kd_rek`) ON DELETE NO ACTION ON UPDATE CASCADE,
  CONSTRAINT `set_akun_ibfk_21` FOREIGN KEY (`Kontra_Pengambilan_Penunjang_Utd`) REFERENCES `rekening` (`kd_rek`) ON DELETE NO ACTION ON UPDATE CASCADE,
  CONSTRAINT `set_akun_ibfk_22` FOREIGN KEY (`Penyerahan_Darah`) REFERENCES `rekening` (`kd_rek`) ON DELETE NO ACTION ON UPDATE CASCADE,
  CONSTRAINT `set_akun_ibfk_3` FOREIGN KEY (`Kontra_Pemesanan_Obat`) REFERENCES `rekening` (`kd_rek`) ON DELETE NO ACTION ON UPDATE CASCADE,
  CONSTRAINT `set_akun_ibfk_4` FOREIGN KEY (`Bayar_Pemesanan_Obat`) REFERENCES `rekening` (`kd_rek`) ON DELETE NO ACTION ON UPDATE CASCADE,
  CONSTRAINT `set_akun_ibfk_5` FOREIGN KEY (`Penjualan_Obat`) REFERENCES `rekening` (`kd_rek`) ON DELETE NO ACTION ON UPDATE CASCADE,
  CONSTRAINT `set_akun_ibfk_6` FOREIGN KEY (`Piutang_Obat`) REFERENCES `rekening` (`kd_rek`) ON DELETE NO ACTION ON UPDATE CASCADE,
  CONSTRAINT `set_akun_ibfk_7` FOREIGN KEY (`Kontra_Piutang_Obat`) REFERENCES `rekening` (`kd_rek`) ON DELETE NO ACTION ON UPDATE CASCADE,
  CONSTRAINT `set_akun_ibfk_8` FOREIGN KEY (`Retur_Ke_Suplayer`) REFERENCES `rekening` (`kd_rek`) ON DELETE NO ACTION ON UPDATE CASCADE,
  CONSTRAINT `set_akun_ibfk_9` FOREIGN KEY (`Kontra_Retur_Ke_Suplayer`) REFERENCES `rekening` (`kd_rek`) ON DELETE NO ACTION ON UPDATE CASCADE
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

/*Table structure for table `set_akun_ralan` */

DROP TABLE IF EXISTS `set_akun_ralan`;

CREATE TABLE `set_akun_ralan` (
  `Tindakan_Ralan` varchar(15) DEFAULT NULL,
  `Beban_Jasa_Medik_Dokter_Tindakan_Ralan` varchar(15) DEFAULT NULL,
  `Utang_Jasa_Medik_Dokter_Tindakan_Ralan` varchar(15) DEFAULT NULL,
  `Beban_Jasa_Medik_Paramedis_Tindakan_Ralan` varchar(15) DEFAULT NULL,
  `Utang_Jasa_Medik_Paramedis_Tindakan_Ralan` varchar(15) DEFAULT NULL,
  `Beban_KSO_Tindakan_Ralan` varchar(15) NOT NULL,
  `Utang_KSO_Tindakan_Ralan` varchar(15) NOT NULL,
  `Laborat_Ralan` varchar(15) DEFAULT NULL,
  `Beban_Jasa_Medik_Dokter_Laborat_Ralan` varchar(15) DEFAULT NULL,
  `Utang_Jasa_Medik_Dokter_Laborat_Ralan` varchar(15) DEFAULT NULL,
  `Beban_Jasa_Medik_Petugas_Laborat_Ralan` varchar(15) DEFAULT NULL,
  `Utang_Jasa_Medik_Petugas_Laborat_Ralan` varchar(15) DEFAULT NULL,
  `Beban_Kso_Laborat_Ralan` varchar(15) DEFAULT NULL,
  `Utang_Kso_Laborat_Ralan` varchar(15) DEFAULT NULL,
  `HPP_Persediaan_Laborat_Rawat_Jalan` varchar(15) DEFAULT NULL,
  `Persediaan_BHP_Laborat_Rawat_Jalan` varchar(15) DEFAULT NULL,
  `Radiologi_Ralan` varchar(15) DEFAULT NULL,
  `Beban_Jasa_Medik_Dokter_Radiologi_Ralan` varchar(15) NOT NULL,
  `Utang_Jasa_Medik_Dokter_Radiologi_Ralan` varchar(15) NOT NULL,
  `Beban_Jasa_Medik_Petugas_Radiologi_Ralan` varchar(15) NOT NULL,
  `Utang_Jasa_Medik_Petugas_Radiologi_Ralan` varchar(15) NOT NULL,
  `Beban_Kso_Radiologi_Ralan` varchar(15) NOT NULL,
  `Utang_Kso_Radiologi_Ralan` varchar(15) NOT NULL,
  `HPP_Persediaan_Radiologi_Rawat_Jalan` varchar(15) NOT NULL,
  `Persediaan_BHP_Radiologi_Rawat_Jalan` varchar(15) NOT NULL,
  `Obat_Ralan` varchar(15) DEFAULT NULL,
  `HPP_Obat_Rawat_Jalan` varchar(15) NOT NULL,
  `Persediaan_Obat_Rawat_Jalan` varchar(15) NOT NULL,
  `Registrasi_Ralan` varchar(15) DEFAULT NULL,
  `Operasi_Ralan` varchar(15) DEFAULT NULL,
  `Beban_Jasa_Medik_Dokter_Operasi_Ralan` varchar(15) NOT NULL,
  `Utang_Jasa_Medik_Dokter_Operasi_Ralan` varchar(15) NOT NULL,
  `Beban_Jasa_Medik_Paramedis_Operasi_Ralan` varchar(15) NOT NULL,
  `Utang_Jasa_Medik_Paramedis_Operasi_Ralan` varchar(15) NOT NULL,
  `HPP_Obat_Operasi_Ralan` varchar(15) NOT NULL,
  `Persediaan_Obat_Kamar_Operasi_Ralan` varchar(15) NOT NULL,
  `Tambahan_Ralan` varchar(15) DEFAULT NULL,
  `Potongan_Ralan` varchar(15) DEFAULT NULL,
  KEY `Tindakan_Ralan` (`Tindakan_Ralan`) USING BTREE,
  KEY `Beban_Jasa_Medik_Dokter_Tindakan_Ralan` (`Beban_Jasa_Medik_Dokter_Tindakan_Ralan`) USING BTREE,
  KEY `Utang_Jasa_Medik_Dokter_Tindakan_Ralan` (`Utang_Jasa_Medik_Dokter_Tindakan_Ralan`) USING BTREE,
  KEY `Beban_Jasa_Medik_Paramedis_Tindakan_Ralan` (`Beban_Jasa_Medik_Paramedis_Tindakan_Ralan`) USING BTREE,
  KEY `Utang_Jasa_Medik_Paramedis_Tindakan_Ralan` (`Utang_Jasa_Medik_Paramedis_Tindakan_Ralan`) USING BTREE,
  KEY `Beban_KSO_Tindakan_Ralan` (`Beban_KSO_Tindakan_Ralan`) USING BTREE,
  KEY `Utang_KSO_Tindakan_Ralan` (`Utang_KSO_Tindakan_Ralan`) USING BTREE,
  KEY `Laborat_Ralan` (`Laborat_Ralan`) USING BTREE,
  KEY `Beban_Jasa_Medik_Dokter_Laborat_Ralan` (`Beban_Jasa_Medik_Dokter_Laborat_Ralan`) USING BTREE,
  KEY `Utang_Jasa_Medik_Dokter_Laborat_Ralan` (`Utang_Jasa_Medik_Dokter_Laborat_Ralan`) USING BTREE,
  KEY `Beban_Jasa_Medik_Petugas_Laborat_Ralan` (`Beban_Jasa_Medik_Petugas_Laborat_Ralan`) USING BTREE,
  KEY `Utang_Jasa_Medik_Petugas_Laborat_Ralan` (`Utang_Jasa_Medik_Petugas_Laborat_Ralan`) USING BTREE,
  KEY `Beban_Kso_Laborat_Ralan` (`Beban_Kso_Laborat_Ralan`) USING BTREE,
  KEY `Utang_Kso_Laborat_Ralan` (`Utang_Kso_Laborat_Ralan`) USING BTREE,
  KEY `HPP_Persediaan_Laborat_Rawat_Jalan` (`HPP_Persediaan_Laborat_Rawat_Jalan`) USING BTREE,
  KEY `Persediaan_BHP_Laborat_Rawat_Jalan` (`Persediaan_BHP_Laborat_Rawat_Jalan`) USING BTREE,
  KEY `Radiologi_Ralan` (`Radiologi_Ralan`) USING BTREE,
  KEY `Beban_Jasa_Medik_Dokter_Radiologi_Ralan` (`Beban_Jasa_Medik_Dokter_Radiologi_Ralan`) USING BTREE,
  KEY `Utang_Jasa_Medik_Dokter_Radiologi_Ralan` (`Utang_Jasa_Medik_Dokter_Radiologi_Ralan`) USING BTREE,
  KEY `Beban_Jasa_Medik_Petugas_Radiologi_Ralan` (`Beban_Jasa_Medik_Petugas_Radiologi_Ralan`) USING BTREE,
  KEY `Utang_Jasa_Medik_Petugas_Radiologi_Ralan` (`Utang_Jasa_Medik_Petugas_Radiologi_Ralan`) USING BTREE,
  KEY `Beban_Kso_Radiologi_Ralan` (`Beban_Kso_Radiologi_Ralan`) USING BTREE,
  KEY `Utang_Kso_Radiologi_Ralan` (`Utang_Kso_Radiologi_Ralan`) USING BTREE,
  KEY `HPP_Persediaan_Radiologi_Rawat_Jalan` (`HPP_Persediaan_Radiologi_Rawat_Jalan`) USING BTREE,
  KEY `Persediaan_BHP_Radiologi_Rawat_Jalan` (`Persediaan_BHP_Radiologi_Rawat_Jalan`) USING BTREE,
  KEY `Obat_Ralan` (`Obat_Ralan`) USING BTREE,
  KEY `HPP_Obat_Rawat_Jalan` (`HPP_Obat_Rawat_Jalan`) USING BTREE,
  KEY `Persediaan_Obat_Rawat_Jalan` (`Persediaan_Obat_Rawat_Jalan`) USING BTREE,
  KEY `Registrasi_Ralan` (`Registrasi_Ralan`) USING BTREE,
  KEY `Operasi_Ralan` (`Operasi_Ralan`) USING BTREE,
  KEY `Beban_Jasa_Medik_Dokter_Operasi_Ralan` (`Beban_Jasa_Medik_Dokter_Operasi_Ralan`) USING BTREE,
  KEY `Utang_Jasa_Medik_Dokter_Operasi_Ralan` (`Utang_Jasa_Medik_Dokter_Operasi_Ralan`) USING BTREE,
  KEY `Beban_Jasa_Medik_Paramedis_Operasi_Ralan` (`Beban_Jasa_Medik_Paramedis_Operasi_Ralan`) USING BTREE,
  KEY `Utang_Jasa_Medik_Paramedis_Operasi_Ralan` (`Utang_Jasa_Medik_Paramedis_Operasi_Ralan`) USING BTREE,
  KEY `HPP_Obat_Operasi_Ralan` (`HPP_Obat_Operasi_Ralan`) USING BTREE,
  KEY `Persediaan_Obat_Kamar_Operasi_Ralan` (`Persediaan_Obat_Kamar_Operasi_Ralan`) USING BTREE,
  KEY `Tambahan_Ralan` (`Tambahan_Ralan`) USING BTREE,
  KEY `Potongan_Ralan` (`Potongan_Ralan`) USING BTREE,
  CONSTRAINT `set_akun_ralan_ibfk_1` FOREIGN KEY (`Tindakan_Ralan`) REFERENCES `rekening` (`kd_rek`) ON UPDATE CASCADE,
  CONSTRAINT `set_akun_ralan_ibfk_10` FOREIGN KEY (`Utang_Jasa_Medik_Dokter_Laborat_Ralan`) REFERENCES `rekening` (`kd_rek`) ON DELETE NO ACTION ON UPDATE CASCADE,
  CONSTRAINT `set_akun_ralan_ibfk_11` FOREIGN KEY (`Beban_Jasa_Medik_Petugas_Laborat_Ralan`) REFERENCES `rekening` (`kd_rek`) ON DELETE NO ACTION ON UPDATE CASCADE,
  CONSTRAINT `set_akun_ralan_ibfk_12` FOREIGN KEY (`Utang_Jasa_Medik_Petugas_Laborat_Ralan`) REFERENCES `rekening` (`kd_rek`) ON DELETE NO ACTION ON UPDATE CASCADE,
  CONSTRAINT `set_akun_ralan_ibfk_13` FOREIGN KEY (`Beban_Kso_Laborat_Ralan`) REFERENCES `rekening` (`kd_rek`) ON DELETE NO ACTION ON UPDATE CASCADE,
  CONSTRAINT `set_akun_ralan_ibfk_14` FOREIGN KEY (`Utang_Kso_Laborat_Ralan`) REFERENCES `rekening` (`kd_rek`) ON DELETE NO ACTION ON UPDATE CASCADE,
  CONSTRAINT `set_akun_ralan_ibfk_15` FOREIGN KEY (`HPP_Persediaan_Laborat_Rawat_Jalan`) REFERENCES `rekening` (`kd_rek`) ON DELETE NO ACTION ON UPDATE CASCADE,
  CONSTRAINT `set_akun_ralan_ibfk_16` FOREIGN KEY (`Persediaan_BHP_Laborat_Rawat_Jalan`) REFERENCES `rekening` (`kd_rek`) ON DELETE NO ACTION ON UPDATE CASCADE,
  CONSTRAINT `set_akun_ralan_ibfk_17` FOREIGN KEY (`Radiologi_Ralan`) REFERENCES `rekening` (`kd_rek`) ON DELETE NO ACTION ON UPDATE CASCADE,
  CONSTRAINT `set_akun_ralan_ibfk_18` FOREIGN KEY (`Beban_Jasa_Medik_Dokter_Radiologi_Ralan`) REFERENCES `rekening` (`kd_rek`) ON DELETE NO ACTION ON UPDATE CASCADE,
  CONSTRAINT `set_akun_ralan_ibfk_19` FOREIGN KEY (`Utang_Jasa_Medik_Dokter_Radiologi_Ralan`) REFERENCES `rekening` (`kd_rek`) ON DELETE NO ACTION ON UPDATE CASCADE,
  CONSTRAINT `set_akun_ralan_ibfk_2` FOREIGN KEY (`Beban_Jasa_Medik_Dokter_Tindakan_Ralan`) REFERENCES `rekening` (`kd_rek`) ON DELETE NO ACTION ON UPDATE CASCADE,
  CONSTRAINT `set_akun_ralan_ibfk_20` FOREIGN KEY (`Beban_Jasa_Medik_Petugas_Radiologi_Ralan`) REFERENCES `rekening` (`kd_rek`) ON DELETE NO ACTION ON UPDATE CASCADE,
  CONSTRAINT `set_akun_ralan_ibfk_21` FOREIGN KEY (`Utang_Jasa_Medik_Petugas_Radiologi_Ralan`) REFERENCES `rekening` (`kd_rek`) ON DELETE NO ACTION ON UPDATE CASCADE,
  CONSTRAINT `set_akun_ralan_ibfk_22` FOREIGN KEY (`Beban_Kso_Radiologi_Ralan`) REFERENCES `rekening` (`kd_rek`) ON DELETE NO ACTION ON UPDATE CASCADE,
  CONSTRAINT `set_akun_ralan_ibfk_23` FOREIGN KEY (`Utang_Kso_Radiologi_Ralan`) REFERENCES `rekening` (`kd_rek`) ON DELETE NO ACTION ON UPDATE CASCADE,
  CONSTRAINT `set_akun_ralan_ibfk_24` FOREIGN KEY (`HPP_Persediaan_Radiologi_Rawat_Jalan`) REFERENCES `rekening` (`kd_rek`) ON DELETE NO ACTION ON UPDATE CASCADE,
  CONSTRAINT `set_akun_ralan_ibfk_25` FOREIGN KEY (`Persediaan_BHP_Radiologi_Rawat_Jalan`) REFERENCES `rekening` (`kd_rek`) ON DELETE NO ACTION ON UPDATE CASCADE,
  CONSTRAINT `set_akun_ralan_ibfk_26` FOREIGN KEY (`Obat_Ralan`) REFERENCES `rekening` (`kd_rek`) ON DELETE NO ACTION ON UPDATE CASCADE,
  CONSTRAINT `set_akun_ralan_ibfk_27` FOREIGN KEY (`HPP_Obat_Rawat_Jalan`) REFERENCES `rekening` (`kd_rek`) ON DELETE NO ACTION ON UPDATE CASCADE,
  CONSTRAINT `set_akun_ralan_ibfk_28` FOREIGN KEY (`Persediaan_Obat_Rawat_Jalan`) REFERENCES `rekening` (`kd_rek`) ON DELETE NO ACTION ON UPDATE CASCADE,
  CONSTRAINT `set_akun_ralan_ibfk_29` FOREIGN KEY (`Registrasi_Ralan`) REFERENCES `rekening` (`kd_rek`) ON DELETE NO ACTION ON UPDATE CASCADE,
  CONSTRAINT `set_akun_ralan_ibfk_3` FOREIGN KEY (`Utang_Jasa_Medik_Dokter_Tindakan_Ralan`) REFERENCES `rekening` (`kd_rek`) ON DELETE NO ACTION ON UPDATE CASCADE,
  CONSTRAINT `set_akun_ralan_ibfk_30` FOREIGN KEY (`Operasi_Ralan`) REFERENCES `rekening` (`kd_rek`) ON DELETE NO ACTION ON UPDATE CASCADE,
  CONSTRAINT `set_akun_ralan_ibfk_31` FOREIGN KEY (`Beban_Jasa_Medik_Dokter_Operasi_Ralan`) REFERENCES `rekening` (`kd_rek`) ON DELETE NO ACTION ON UPDATE CASCADE,
  CONSTRAINT `set_akun_ralan_ibfk_32` FOREIGN KEY (`Utang_Jasa_Medik_Dokter_Operasi_Ralan`) REFERENCES `rekening` (`kd_rek`) ON DELETE NO ACTION ON UPDATE CASCADE,
  CONSTRAINT `set_akun_ralan_ibfk_33` FOREIGN KEY (`Beban_Jasa_Medik_Paramedis_Operasi_Ralan`) REFERENCES `rekening` (`kd_rek`) ON DELETE NO ACTION ON UPDATE CASCADE,
  CONSTRAINT `set_akun_ralan_ibfk_34` FOREIGN KEY (`Utang_Jasa_Medik_Paramedis_Operasi_Ralan`) REFERENCES `rekening` (`kd_rek`) ON DELETE NO ACTION ON UPDATE CASCADE,
  CONSTRAINT `set_akun_ralan_ibfk_35` FOREIGN KEY (`HPP_Obat_Operasi_Ralan`) REFERENCES `rekening` (`kd_rek`) ON DELETE NO ACTION ON UPDATE CASCADE,
  CONSTRAINT `set_akun_ralan_ibfk_36` FOREIGN KEY (`Persediaan_Obat_Kamar_Operasi_Ralan`) REFERENCES `rekening` (`kd_rek`) ON DELETE NO ACTION ON UPDATE CASCADE,
  CONSTRAINT `set_akun_ralan_ibfk_37` FOREIGN KEY (`Tambahan_Ralan`) REFERENCES `rekening` (`kd_rek`) ON DELETE NO ACTION ON UPDATE CASCADE,
  CONSTRAINT `set_akun_ralan_ibfk_38` FOREIGN KEY (`Potongan_Ralan`) REFERENCES `rekening` (`kd_rek`) ON DELETE NO ACTION ON UPDATE CASCADE,
  CONSTRAINT `set_akun_ralan_ibfk_4` FOREIGN KEY (`Beban_Jasa_Medik_Paramedis_Tindakan_Ralan`) REFERENCES `rekening` (`kd_rek`) ON DELETE NO ACTION ON UPDATE CASCADE,
  CONSTRAINT `set_akun_ralan_ibfk_5` FOREIGN KEY (`Utang_Jasa_Medik_Paramedis_Tindakan_Ralan`) REFERENCES `rekening` (`kd_rek`) ON DELETE NO ACTION ON UPDATE CASCADE,
  CONSTRAINT `set_akun_ralan_ibfk_6` FOREIGN KEY (`Beban_KSO_Tindakan_Ralan`) REFERENCES `rekening` (`kd_rek`) ON DELETE NO ACTION ON UPDATE CASCADE,
  CONSTRAINT `set_akun_ralan_ibfk_7` FOREIGN KEY (`Utang_KSO_Tindakan_Ralan`) REFERENCES `rekening` (`kd_rek`) ON DELETE NO ACTION ON UPDATE CASCADE,
  CONSTRAINT `set_akun_ralan_ibfk_8` FOREIGN KEY (`Laborat_Ralan`) REFERENCES `rekening` (`kd_rek`) ON DELETE NO ACTION ON UPDATE CASCADE,
  CONSTRAINT `set_akun_ralan_ibfk_9` FOREIGN KEY (`Beban_Jasa_Medik_Dokter_Laborat_Ralan`) REFERENCES `rekening` (`kd_rek`) ON DELETE NO ACTION ON UPDATE CASCADE
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

/*Table structure for table `set_akun_ranap` */

DROP TABLE IF EXISTS `set_akun_ranap`;

CREATE TABLE `set_akun_ranap` (
  `Suspen_Piutang_Tindakan_Ranap` varchar(15) NOT NULL,
  `Tindakan_Ranap` varchar(15) DEFAULT NULL,
  `Beban_Jasa_Medik_Dokter_Tindakan_Ranap` varchar(15) NOT NULL,
  `Utang_Jasa_Medik_Dokter_Tindakan_Ranap` varchar(15) NOT NULL,
  `Beban_Jasa_Medik_Paramedis_Tindakan_Ranap` varchar(15) NOT NULL,
  `Utang_Jasa_Medik_Paramedis_Tindakan_Ranap` varchar(15) NOT NULL,
  `Beban_KSO_Tindakan_Ranap` varchar(15) NOT NULL,
  `Utang_KSO_Tindakan_Ranap` varchar(15) NOT NULL,
  `Suspen_Piutang_Laborat_Ranap` varchar(15) NOT NULL,
  `Laborat_Ranap` varchar(15) DEFAULT NULL,
  `Beban_Jasa_Medik_Dokter_Laborat_Ranap` varchar(15) NOT NULL,
  `Utang_Jasa_Medik_Dokter_Laborat_Ranap` varchar(15) NOT NULL,
  `Beban_Jasa_Medik_Petugas_Laborat_Ranap` varchar(15) NOT NULL,
  `Utang_Jasa_Medik_Petugas_Laborat_Ranap` varchar(15) NOT NULL,
  `Beban_Kso_Laborat_Ranap` varchar(15) NOT NULL,
  `Utang_Kso_Laborat_Ranap` varchar(15) NOT NULL,
  `HPP_Persediaan_Laborat_Rawat_inap` varchar(15) NOT NULL,
  `Persediaan_BHP_Laborat_Rawat_Inap` varchar(15) NOT NULL,
  `Suspen_Piutang_Radiologi_Ranap` varchar(15) NOT NULL,
  `Radiologi_Ranap` varchar(15) DEFAULT NULL,
  `Beban_Jasa_Medik_Dokter_Radiologi_Ranap` varchar(15) NOT NULL,
  `Utang_Jasa_Medik_Dokter_Radiologi_Ranap` varchar(15) NOT NULL,
  `Beban_Jasa_Medik_Petugas_Radiologi_Ranap` varchar(15) NOT NULL,
  `Utang_Jasa_Medik_Petugas_Radiologi_Ranap` varchar(15) NOT NULL,
  `Beban_Kso_Radiologi_Ranap` varchar(15) NOT NULL,
  `Utang_Kso_Radiologi_Ranap` varchar(15) NOT NULL,
  `HPP_Persediaan_Radiologi_Rawat_Inap` varchar(15) NOT NULL,
  `Persediaan_BHP_Radiologi_Rawat_Inap` varchar(15) NOT NULL,
  `Suspen_Piutang_Obat_Ranap` varchar(15) NOT NULL,
  `Obat_Ranap` varchar(15) DEFAULT NULL,
  `HPP_Obat_Rawat_Inap` varchar(15) NOT NULL,
  `Persediaan_Obat_Rawat_Inap` varchar(15) NOT NULL,
  `Registrasi_Ranap` varchar(15) DEFAULT NULL,
  `Service_Ranap` varchar(15) DEFAULT NULL,
  `Tambahan_Ranap` varchar(15) DEFAULT NULL,
  `Potongan_Ranap` varchar(15) DEFAULT NULL,
  `Retur_Obat_Ranap` varchar(15) DEFAULT NULL,
  `Resep_Pulang_Ranap` varchar(15) DEFAULT NULL,
  `Kamar_Inap` varchar(15) DEFAULT NULL,
  `Suspen_Piutang_Operasi_Ranap` varchar(15) NOT NULL,
  `Operasi_Ranap` varchar(15) DEFAULT NULL,
  `Beban_Jasa_Medik_Dokter_Operasi_Ranap` varchar(15) NOT NULL,
  `Utang_Jasa_Medik_Dokter_Operasi_Ranap` varchar(15) NOT NULL,
  `Beban_Jasa_Medik_Paramedis_Operasi_Ranap` varchar(15) NOT NULL,
  `Utang_Jasa_Medik_Paramedis_Operasi_Ranap` varchar(15) NOT NULL,
  `HPP_Obat_Operasi_Ranap` varchar(15) NOT NULL,
  `Persediaan_Obat_Kamar_Operasi_Ranap` varchar(15) NOT NULL,
  `Harian_Ranap` varchar(15) DEFAULT NULL,
  `Uang_Muka_Ranap` varchar(15) DEFAULT NULL,
  `Piutang_Pasien_Ranap` varchar(15) DEFAULT NULL,
  KEY `Suspen_Piutang_Tindakan_Ranap` (`Suspen_Piutang_Tindakan_Ranap`) USING BTREE,
  KEY `Tindakan_Ranap` (`Tindakan_Ranap`) USING BTREE,
  KEY `Beban_Jasa_Medik_Dokter_Tindakan_Ranap` (`Beban_Jasa_Medik_Dokter_Tindakan_Ranap`) USING BTREE,
  KEY `Utang_Jasa_Medik_Dokter_Tindakan_Ranap` (`Utang_Jasa_Medik_Dokter_Tindakan_Ranap`) USING BTREE,
  KEY `Beban_Jasa_Medik_Paramedis_Tindakan_Ranap` (`Beban_Jasa_Medik_Paramedis_Tindakan_Ranap`) USING BTREE,
  KEY `Utang_Jasa_Medik_Paramedis_Tindakan_Ranap` (`Utang_Jasa_Medik_Paramedis_Tindakan_Ranap`) USING BTREE,
  KEY `Beban_KSO_Tindakan_Ranap` (`Beban_KSO_Tindakan_Ranap`) USING BTREE,
  KEY `Utang_KSO_Tindakan_Ranap` (`Utang_KSO_Tindakan_Ranap`) USING BTREE,
  KEY `Suspen_Piutang_Laborat_Ranap` (`Suspen_Piutang_Laborat_Ranap`) USING BTREE,
  KEY `Laborat_Ranap` (`Laborat_Ranap`) USING BTREE,
  KEY `Beban_Jasa_Medik_Dokter_Laborat_Ranap` (`Beban_Jasa_Medik_Dokter_Laborat_Ranap`) USING BTREE,
  KEY `Utang_Jasa_Medik_Dokter_Laborat_Ranap` (`Utang_Jasa_Medik_Dokter_Laborat_Ranap`) USING BTREE,
  KEY `Beban_Jasa_Medik_Petugas_Laborat_Ranap` (`Beban_Jasa_Medik_Petugas_Laborat_Ranap`) USING BTREE,
  KEY `Utang_Jasa_Medik_Petugas_Laborat_Ranap` (`Utang_Jasa_Medik_Petugas_Laborat_Ranap`) USING BTREE,
  KEY `Beban_Kso_Laborat_Ranap` (`Beban_Kso_Laborat_Ranap`) USING BTREE,
  KEY `Utang_Kso_Laborat_Ranap` (`Utang_Kso_Laborat_Ranap`) USING BTREE,
  KEY `HPP_Persediaan_Laborat_Rawat_inap` (`HPP_Persediaan_Laborat_Rawat_inap`) USING BTREE,
  KEY `Persediaan_BHP_Laborat_Rawat_Inap` (`Persediaan_BHP_Laborat_Rawat_Inap`) USING BTREE,
  KEY `Suspen_Piutang_Radiologi_Ranap` (`Suspen_Piutang_Radiologi_Ranap`) USING BTREE,
  KEY `Radiologi_Ranap` (`Radiologi_Ranap`) USING BTREE,
  KEY `Beban_Jasa_Medik_Dokter_Radiologi_Ranap` (`Beban_Jasa_Medik_Dokter_Radiologi_Ranap`) USING BTREE,
  KEY `Utang_Jasa_Medik_Dokter_Radiologi_Ranap` (`Utang_Jasa_Medik_Dokter_Radiologi_Ranap`) USING BTREE,
  KEY `Beban_Jasa_Medik_Petugas_Radiologi_Ranap` (`Beban_Jasa_Medik_Petugas_Radiologi_Ranap`) USING BTREE,
  KEY `Utang_Jasa_Medik_Petugas_Radiologi_Ranap` (`Utang_Jasa_Medik_Petugas_Radiologi_Ranap`) USING BTREE,
  KEY `Beban_Kso_Radiologi_Ranap` (`Beban_Kso_Radiologi_Ranap`) USING BTREE,
  KEY `Utang_Kso_Radiologi_Ranap` (`Utang_Kso_Radiologi_Ranap`) USING BTREE,
  KEY `HPP_Persediaan_Radiologi_Rawat_Inap` (`HPP_Persediaan_Radiologi_Rawat_Inap`) USING BTREE,
  KEY `Persediaan_BHP_Radiologi_Rawat_Inap` (`Persediaan_BHP_Radiologi_Rawat_Inap`) USING BTREE,
  KEY `Obat_Ranap` (`Obat_Ranap`) USING BTREE,
  KEY `Registrasi_Ranap` (`Registrasi_Ranap`) USING BTREE,
  KEY `Service_Ranap` (`Service_Ranap`) USING BTREE,
  KEY `Tambahan_Ranap` (`Tambahan_Ranap`) USING BTREE,
  KEY `Potongan_Ranap` (`Potongan_Ranap`) USING BTREE,
  KEY `Retur_Obat_Ranap` (`Retur_Obat_Ranap`) USING BTREE,
  KEY `Resep_Pulang_Ranap` (`Resep_Pulang_Ranap`) USING BTREE,
  KEY `Kamar_Inap` (`Kamar_Inap`) USING BTREE,
  KEY `Operasi_Ranap` (`Operasi_Ranap`) USING BTREE,
  KEY `Harian_Ranap` (`Harian_Ranap`) USING BTREE,
  KEY `Uang_Muka_Ranap` (`Uang_Muka_Ranap`) USING BTREE,
  KEY `Piutang_Pasien_Ranap` (`Piutang_Pasien_Ranap`) USING BTREE,
  KEY `Suspen_Piutang_Obat_Ranap` (`Suspen_Piutang_Obat_Ranap`) USING BTREE,
  KEY `HPP_Obat_Rawat_Inap` (`HPP_Obat_Rawat_Inap`) USING BTREE,
  KEY `Persediaan_Obat_Rawat_Inap` (`Persediaan_Obat_Rawat_Inap`) USING BTREE,
  KEY `Suspen_Piutang_Operasi_Ranap` (`Suspen_Piutang_Operasi_Ranap`) USING BTREE,
  KEY `Beban_Jasa_Medik_Dokter_Operasi_Ranap` (`Beban_Jasa_Medik_Dokter_Operasi_Ranap`) USING BTREE,
  KEY `Utang_Jasa_Medik_Dokter_Operasi_Ranap` (`Utang_Jasa_Medik_Dokter_Operasi_Ranap`) USING BTREE,
  KEY `Beban_Jasa_Medik_Paramedis_Operasi_Ranap` (`Beban_Jasa_Medik_Paramedis_Operasi_Ranap`) USING BTREE,
  KEY `Utang_Jasa_Medik_Paramedis_Operasi_Ranap` (`Utang_Jasa_Medik_Paramedis_Operasi_Ranap`) USING BTREE,
  KEY `HPP_Obat_Operasi_Ranap` (`HPP_Obat_Operasi_Ranap`) USING BTREE,
  KEY `Persediaan_Obat_Kamar_Operasi_Ranap` (`Persediaan_Obat_Kamar_Operasi_Ranap`) USING BTREE,
  CONSTRAINT `set_akun_ranap_ibfk_1` FOREIGN KEY (`Suspen_Piutang_Tindakan_Ranap`) REFERENCES `rekening` (`kd_rek`) ON UPDATE CASCADE,
  CONSTRAINT `set_akun_ranap_ibfk_10` FOREIGN KEY (`Laborat_Ranap`) REFERENCES `rekening` (`kd_rek`) ON DELETE NO ACTION ON UPDATE CASCADE,
  CONSTRAINT `set_akun_ranap_ibfk_11` FOREIGN KEY (`Beban_Jasa_Medik_Dokter_Laborat_Ranap`) REFERENCES `rekening` (`kd_rek`) ON DELETE NO ACTION ON UPDATE CASCADE,
  CONSTRAINT `set_akun_ranap_ibfk_12` FOREIGN KEY (`Utang_Jasa_Medik_Dokter_Laborat_Ranap`) REFERENCES `rekening` (`kd_rek`) ON DELETE NO ACTION ON UPDATE CASCADE,
  CONSTRAINT `set_akun_ranap_ibfk_13` FOREIGN KEY (`Beban_Jasa_Medik_Petugas_Laborat_Ranap`) REFERENCES `rekening` (`kd_rek`) ON DELETE NO ACTION ON UPDATE CASCADE,
  CONSTRAINT `set_akun_ranap_ibfk_14` FOREIGN KEY (`Utang_Jasa_Medik_Petugas_Laborat_Ranap`) REFERENCES `rekening` (`kd_rek`) ON DELETE NO ACTION ON UPDATE CASCADE,
  CONSTRAINT `set_akun_ranap_ibfk_15` FOREIGN KEY (`Beban_Kso_Laborat_Ranap`) REFERENCES `rekening` (`kd_rek`) ON DELETE NO ACTION ON UPDATE CASCADE,
  CONSTRAINT `set_akun_ranap_ibfk_16` FOREIGN KEY (`Utang_Kso_Laborat_Ranap`) REFERENCES `rekening` (`kd_rek`) ON DELETE NO ACTION ON UPDATE CASCADE,
  CONSTRAINT `set_akun_ranap_ibfk_17` FOREIGN KEY (`HPP_Persediaan_Laborat_Rawat_inap`) REFERENCES `rekening` (`kd_rek`) ON DELETE NO ACTION ON UPDATE CASCADE,
  CONSTRAINT `set_akun_ranap_ibfk_18` FOREIGN KEY (`Persediaan_BHP_Laborat_Rawat_Inap`) REFERENCES `rekening` (`kd_rek`) ON DELETE NO ACTION ON UPDATE CASCADE,
  CONSTRAINT `set_akun_ranap_ibfk_19` FOREIGN KEY (`Suspen_Piutang_Radiologi_Ranap`) REFERENCES `rekening` (`kd_rek`) ON DELETE NO ACTION ON UPDATE CASCADE,
  CONSTRAINT `set_akun_ranap_ibfk_2` FOREIGN KEY (`Tindakan_Ranap`) REFERENCES `rekening` (`kd_rek`) ON DELETE NO ACTION ON UPDATE CASCADE,
  CONSTRAINT `set_akun_ranap_ibfk_20` FOREIGN KEY (`Radiologi_Ranap`) REFERENCES `rekening` (`kd_rek`) ON DELETE NO ACTION ON UPDATE CASCADE,
  CONSTRAINT `set_akun_ranap_ibfk_21` FOREIGN KEY (`Beban_Jasa_Medik_Dokter_Radiologi_Ranap`) REFERENCES `rekening` (`kd_rek`) ON DELETE NO ACTION ON UPDATE CASCADE,
  CONSTRAINT `set_akun_ranap_ibfk_22` FOREIGN KEY (`Utang_Jasa_Medik_Dokter_Radiologi_Ranap`) REFERENCES `rekening` (`kd_rek`) ON DELETE NO ACTION ON UPDATE CASCADE,
  CONSTRAINT `set_akun_ranap_ibfk_23` FOREIGN KEY (`Beban_Jasa_Medik_Petugas_Radiologi_Ranap`) REFERENCES `rekening` (`kd_rek`) ON DELETE NO ACTION ON UPDATE CASCADE,
  CONSTRAINT `set_akun_ranap_ibfk_24` FOREIGN KEY (`Utang_Jasa_Medik_Petugas_Radiologi_Ranap`) REFERENCES `rekening` (`kd_rek`) ON DELETE NO ACTION ON UPDATE CASCADE,
  CONSTRAINT `set_akun_ranap_ibfk_25` FOREIGN KEY (`Beban_Kso_Radiologi_Ranap`) REFERENCES `rekening` (`kd_rek`) ON DELETE NO ACTION ON UPDATE CASCADE,
  CONSTRAINT `set_akun_ranap_ibfk_26` FOREIGN KEY (`Utang_Kso_Radiologi_Ranap`) REFERENCES `rekening` (`kd_rek`) ON DELETE NO ACTION ON UPDATE CASCADE,
  CONSTRAINT `set_akun_ranap_ibfk_27` FOREIGN KEY (`HPP_Persediaan_Radiologi_Rawat_Inap`) REFERENCES `rekening` (`kd_rek`) ON DELETE NO ACTION ON UPDATE CASCADE,
  CONSTRAINT `set_akun_ranap_ibfk_28` FOREIGN KEY (`Persediaan_BHP_Radiologi_Rawat_Inap`) REFERENCES `rekening` (`kd_rek`) ON DELETE NO ACTION ON UPDATE CASCADE,
  CONSTRAINT `set_akun_ranap_ibfk_29` FOREIGN KEY (`Obat_Ranap`) REFERENCES `rekening` (`kd_rek`) ON DELETE NO ACTION ON UPDATE CASCADE,
  CONSTRAINT `set_akun_ranap_ibfk_3` FOREIGN KEY (`Beban_Jasa_Medik_Dokter_Tindakan_Ranap`) REFERENCES `rekening` (`kd_rek`) ON DELETE NO ACTION ON UPDATE CASCADE,
  CONSTRAINT `set_akun_ranap_ibfk_30` FOREIGN KEY (`Registrasi_Ranap`) REFERENCES `rekening` (`kd_rek`) ON DELETE NO ACTION ON UPDATE CASCADE,
  CONSTRAINT `set_akun_ranap_ibfk_31` FOREIGN KEY (`Service_Ranap`) REFERENCES `rekening` (`kd_rek`) ON DELETE NO ACTION ON UPDATE CASCADE,
  CONSTRAINT `set_akun_ranap_ibfk_32` FOREIGN KEY (`Tambahan_Ranap`) REFERENCES `rekening` (`kd_rek`) ON DELETE NO ACTION ON UPDATE CASCADE,
  CONSTRAINT `set_akun_ranap_ibfk_33` FOREIGN KEY (`Potongan_Ranap`) REFERENCES `rekening` (`kd_rek`) ON DELETE NO ACTION ON UPDATE CASCADE,
  CONSTRAINT `set_akun_ranap_ibfk_34` FOREIGN KEY (`Retur_Obat_Ranap`) REFERENCES `rekening` (`kd_rek`) ON DELETE NO ACTION ON UPDATE CASCADE,
  CONSTRAINT `set_akun_ranap_ibfk_35` FOREIGN KEY (`Resep_Pulang_Ranap`) REFERENCES `rekening` (`kd_rek`) ON DELETE NO ACTION ON UPDATE CASCADE,
  CONSTRAINT `set_akun_ranap_ibfk_36` FOREIGN KEY (`Kamar_Inap`) REFERENCES `rekening` (`kd_rek`) ON DELETE NO ACTION ON UPDATE CASCADE,
  CONSTRAINT `set_akun_ranap_ibfk_37` FOREIGN KEY (`Operasi_Ranap`) REFERENCES `rekening` (`kd_rek`) ON DELETE NO ACTION ON UPDATE CASCADE,
  CONSTRAINT `set_akun_ranap_ibfk_38` FOREIGN KEY (`Harian_Ranap`) REFERENCES `rekening` (`kd_rek`) ON DELETE NO ACTION ON UPDATE CASCADE,
  CONSTRAINT `set_akun_ranap_ibfk_39` FOREIGN KEY (`Uang_Muka_Ranap`) REFERENCES `rekening` (`kd_rek`) ON DELETE NO ACTION ON UPDATE CASCADE,
  CONSTRAINT `set_akun_ranap_ibfk_4` FOREIGN KEY (`Utang_Jasa_Medik_Dokter_Tindakan_Ranap`) REFERENCES `rekening` (`kd_rek`) ON DELETE NO ACTION ON UPDATE CASCADE,
  CONSTRAINT `set_akun_ranap_ibfk_40` FOREIGN KEY (`Piutang_Pasien_Ranap`) REFERENCES `rekening` (`kd_rek`) ON DELETE NO ACTION ON UPDATE CASCADE,
  CONSTRAINT `set_akun_ranap_ibfk_41` FOREIGN KEY (`Suspen_Piutang_Obat_Ranap`) REFERENCES `rekening` (`kd_rek`) ON DELETE NO ACTION ON UPDATE CASCADE,
  CONSTRAINT `set_akun_ranap_ibfk_42` FOREIGN KEY (`HPP_Obat_Rawat_Inap`) REFERENCES `rekening` (`kd_rek`) ON DELETE NO ACTION ON UPDATE CASCADE,
  CONSTRAINT `set_akun_ranap_ibfk_43` FOREIGN KEY (`Persediaan_Obat_Rawat_Inap`) REFERENCES `rekening` (`kd_rek`) ON DELETE NO ACTION ON UPDATE CASCADE,
  CONSTRAINT `set_akun_ranap_ibfk_44` FOREIGN KEY (`Suspen_Piutang_Operasi_Ranap`) REFERENCES `rekening` (`kd_rek`) ON DELETE NO ACTION ON UPDATE CASCADE,
  CONSTRAINT `set_akun_ranap_ibfk_45` FOREIGN KEY (`Beban_Jasa_Medik_Dokter_Operasi_Ranap`) REFERENCES `rekening` (`kd_rek`) ON DELETE NO ACTION ON UPDATE CASCADE,
  CONSTRAINT `set_akun_ranap_ibfk_46` FOREIGN KEY (`Utang_Jasa_Medik_Dokter_Operasi_Ranap`) REFERENCES `rekening` (`kd_rek`) ON DELETE NO ACTION ON UPDATE CASCADE,
  CONSTRAINT `set_akun_ranap_ibfk_47` FOREIGN KEY (`Beban_Jasa_Medik_Paramedis_Operasi_Ranap`) REFERENCES `rekening` (`kd_rek`) ON DELETE NO ACTION ON UPDATE CASCADE,
  CONSTRAINT `set_akun_ranap_ibfk_48` FOREIGN KEY (`Utang_Jasa_Medik_Paramedis_Operasi_Ranap`) REFERENCES `rekening` (`kd_rek`) ON DELETE NO ACTION ON UPDATE CASCADE,
  CONSTRAINT `set_akun_ranap_ibfk_49` FOREIGN KEY (`HPP_Obat_Operasi_Ranap`) REFERENCES `rekening` (`kd_rek`) ON DELETE NO ACTION ON UPDATE CASCADE,
  CONSTRAINT `set_akun_ranap_ibfk_5` FOREIGN KEY (`Beban_Jasa_Medik_Paramedis_Tindakan_Ranap`) REFERENCES `rekening` (`kd_rek`) ON DELETE NO ACTION ON UPDATE CASCADE,
  CONSTRAINT `set_akun_ranap_ibfk_50` FOREIGN KEY (`Persediaan_Obat_Kamar_Operasi_Ranap`) REFERENCES `rekening` (`kd_rek`) ON DELETE NO ACTION ON UPDATE CASCADE,
  CONSTRAINT `set_akun_ranap_ibfk_6` FOREIGN KEY (`Utang_Jasa_Medik_Paramedis_Tindakan_Ranap`) REFERENCES `rekening` (`kd_rek`) ON DELETE NO ACTION ON UPDATE CASCADE,
  CONSTRAINT `set_akun_ranap_ibfk_7` FOREIGN KEY (`Beban_KSO_Tindakan_Ranap`) REFERENCES `rekening` (`kd_rek`) ON DELETE NO ACTION ON UPDATE CASCADE,
  CONSTRAINT `set_akun_ranap_ibfk_8` FOREIGN KEY (`Utang_KSO_Tindakan_Ranap`) REFERENCES `rekening` (`kd_rek`) ON DELETE NO ACTION ON UPDATE CASCADE,
  CONSTRAINT `set_akun_ranap_ibfk_9` FOREIGN KEY (`Suspen_Piutang_Laborat_Ranap`) REFERENCES `rekening` (`kd_rek`) ON DELETE NO ACTION ON UPDATE CASCADE
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

/*Table structure for table `set_alamat_pasien` */

DROP TABLE IF EXISTS `set_alamat_pasien`;

CREATE TABLE `set_alamat_pasien` (
  `kelurahan` enum('true','false') DEFAULT NULL,
  `kecamatan` enum('true','false') DEFAULT NULL,
  `kabupaten` enum('true','false') DEFAULT NULL
) ENGINE=MyISAM DEFAULT CHARSET=latin1;

/*Table structure for table `set_batas_umur` */

DROP TABLE IF EXISTS `set_batas_umur`;

CREATE TABLE `set_batas_umur` (
  `batas_maksimal` int(11) NOT NULL,
  `batas_minimal` int(11) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

/*Table structure for table `set_depo_ralan` */

DROP TABLE IF EXISTS `set_depo_ralan`;

CREATE TABLE `set_depo_ralan` (
  `kd_poli` char(5) NOT NULL,
  `kd_bangsal` char(5) NOT NULL,
  PRIMARY KEY (`kd_poli`,`kd_bangsal`),
  KEY `kd_bangsal` (`kd_bangsal`) USING BTREE,
  CONSTRAINT `set_depo_ralan_ibfk_1` FOREIGN KEY (`kd_poli`) REFERENCES `poliklinik` (`kd_poli`) ON DELETE CASCADE ON UPDATE CASCADE,
  CONSTRAINT `set_depo_ralan_ibfk_2` FOREIGN KEY (`kd_bangsal`) REFERENCES `bangsal` (`kd_bangsal`) ON DELETE CASCADE ON UPDATE CASCADE
) ENGINE=InnoDB DEFAULT CHARSET=latin1 ROW_FORMAT=COMPACT;

/*Table structure for table `set_depo_ranap` */

DROP TABLE IF EXISTS `set_depo_ranap`;

CREATE TABLE `set_depo_ranap` (
  `kd_bangsal` char(5) NOT NULL,
  `kd_depo` char(5) NOT NULL,
  PRIMARY KEY (`kd_bangsal`,`kd_depo`),
  KEY `kd_depo` (`kd_depo`) USING BTREE,
  CONSTRAINT `set_depo_ranap_ibfk_1` FOREIGN KEY (`kd_depo`) REFERENCES `bangsal` (`kd_bangsal`) ON DELETE CASCADE ON UPDATE CASCADE,
  CONSTRAINT `set_depo_ranap_ibfk_2` FOREIGN KEY (`kd_bangsal`) REFERENCES `bangsal` (`kd_bangsal`) ON DELETE CASCADE ON UPDATE CASCADE
) ENGINE=InnoDB DEFAULT CHARSET=latin1 ROW_FORMAT=COMPACT;

/*Table structure for table `set_embalase` */

DROP TABLE IF EXISTS `set_embalase`;

CREATE TABLE `set_embalase` (
  `embalase_per_obat` double NOT NULL,
  `tuslah_per_obat` double DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

/*Table structure for table `set_hadir` */

DROP TABLE IF EXISTS `set_hadir`;

CREATE TABLE `set_hadir` (
  `tnj` double NOT NULL,
  PRIMARY KEY (`tnj`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

/*Table structure for table `set_harga_kamar` */

DROP TABLE IF EXISTS `set_harga_kamar`;

CREATE TABLE `set_harga_kamar` (
  `kd_kamar` varchar(15) NOT NULL,
  `kd_pj` char(3) NOT NULL,
  `tarif` double NOT NULL,
  PRIMARY KEY (`kd_kamar`,`kd_pj`),
  KEY `kd_pj` (`kd_pj`),
  KEY `tarif` (`tarif`) USING BTREE,
  CONSTRAINT `set_harga_kamar_ibfk_1` FOREIGN KEY (`kd_kamar`) REFERENCES `kamar` (`kd_kamar`) ON DELETE CASCADE ON UPDATE CASCADE,
  CONSTRAINT `set_harga_kamar_ibfk_2` FOREIGN KEY (`kd_pj`) REFERENCES `penjab` (`kd_pj`) ON UPDATE CASCADE
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

/*Table structure for table `set_harga_obat_ralan` */

DROP TABLE IF EXISTS `set_harga_obat_ralan`;

CREATE TABLE `set_harga_obat_ralan` (
  `kd_pj` char(3) NOT NULL,
  `hargajual` double NOT NULL,
  PRIMARY KEY (`kd_pj`),
  CONSTRAINT `set_harga_obat_ralan_ibfk_1` FOREIGN KEY (`kd_pj`) REFERENCES `penjab` (`kd_pj`) ON UPDATE CASCADE
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

/*Table structure for table `set_harga_obat_ranap` */

DROP TABLE IF EXISTS `set_harga_obat_ranap`;

CREATE TABLE `set_harga_obat_ranap` (
  `kd_pj` char(3) NOT NULL,
  `kelas` enum('Kelas 1','Kelas 2','Kelas 3','Kelas Utama','Kelas VIP','Kelas VVIP') NOT NULL DEFAULT 'Kelas 1',
  `hargajual` double NOT NULL,
  PRIMARY KEY (`kd_pj`,`kelas`),
  CONSTRAINT `set_harga_obat_ranap_ibfk_1` FOREIGN KEY (`kd_pj`) REFERENCES `penjab` (`kd_pj`) ON UPDATE CASCADE
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

/*Table structure for table `set_hari_libur` */

DROP TABLE IF EXISTS `set_hari_libur`;

CREATE TABLE `set_hari_libur` (
  `tanggal` date NOT NULL,
  `ktg` varchar(40) NOT NULL,
  PRIMARY KEY (`tanggal`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

/*Table structure for table `set_insentif` */

DROP TABLE IF EXISTS `set_insentif`;

CREATE TABLE `set_insentif` (
  `tahun` year(4) NOT NULL,
  `bulan` tinyint(4) NOT NULL,
  `pendapatan` double NOT NULL,
  `persen` double NOT NULL,
  `total_insentif` double NOT NULL,
  PRIMARY KEY (`tahun`,`bulan`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

/*Table structure for table `set_jam_minimal` */

DROP TABLE IF EXISTS `set_jam_minimal`;

CREATE TABLE `set_jam_minimal` (
  `lamajam` int(11) NOT NULL,
  `hariawal` enum('Yes','No') NOT NULL,
  `feeperujuk` double NOT NULL,
  `diagnosaakhir` enum('Yes','No') DEFAULT NULL,
  `bayi` int(11) DEFAULT NULL
) ENGINE=MyISAM DEFAULT CHARSET=latin1;

/*Table structure for table `set_jgmlm` */

DROP TABLE IF EXISTS `set_jgmlm`;

CREATE TABLE `set_jgmlm` (
  `tnj` double NOT NULL,
  PRIMARY KEY (`tnj`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

/*Table structure for table `set_jgtambah` */

DROP TABLE IF EXISTS `set_jgtambah`;

CREATE TABLE `set_jgtambah` (
  `tnj` double NOT NULL,
  `pendidikan` varchar(80) NOT NULL,
  PRIMARY KEY (`pendidikan`),
  CONSTRAINT `set_jgtambah_ibfk_1` FOREIGN KEY (`pendidikan`) REFERENCES `pendidikan` (`tingkat`) ON UPDATE CASCADE
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

/*Table structure for table `set_kelengkapan_data_pasien` */

DROP TABLE IF EXISTS `set_kelengkapan_data_pasien`;

CREATE TABLE `set_kelengkapan_data_pasien` (
  `no_ktp` enum('Yes','No') DEFAULT NULL,
  `p_no_ktp` tinyint(4) DEFAULT NULL,
  `tmp_lahir` enum('Yes','No') DEFAULT NULL,
  `p_tmp_lahir` tinyint(4) DEFAULT NULL,
  `nm_ibu` enum('Yes','No') DEFAULT NULL,
  `p_nm_ibu` tinyint(4) DEFAULT NULL,
  `alamat` enum('Yes','No') DEFAULT NULL,
  `p_alamat` tinyint(4) DEFAULT NULL,
  `pekerjaan` enum('Yes','No') DEFAULT NULL,
  `p_pekerjaan` tinyint(4) DEFAULT NULL,
  `no_tlp` enum('Yes','No') DEFAULT NULL,
  `p_no_tlp` tinyint(4) DEFAULT NULL,
  `umur` enum('Yes','No') DEFAULT NULL,
  `p_umur` tinyint(4) DEFAULT NULL,
  `namakeluarga` enum('Yes','No') DEFAULT NULL,
  `p_namakeluarga` tinyint(4) DEFAULT NULL,
  `no_peserta` enum('Yes','No') DEFAULT NULL,
  `p_no_peserta` tinyint(4) DEFAULT NULL,
  `kelurahan` enum('Yes','No') DEFAULT NULL,
  `p_kelurahan` tinyint(4) DEFAULT NULL,
  `kecamatan` enum('Yes','No') DEFAULT NULL,
  `p_kecamatan` tinyint(4) DEFAULT NULL,
  `kabupaten` enum('Yes','No') DEFAULT NULL,
  `p_kabupaten` tinyint(4) DEFAULT NULL,
  `pekerjaanpj` enum('Yes','No') DEFAULT NULL,
  `p_pekerjaanpj` tinyint(4) DEFAULT NULL,
  `alamatpj` enum('Yes','No') DEFAULT NULL,
  `p_alamatpj` tinyint(4) DEFAULT NULL,
  `kelurahanpj` enum('Yes','No') DEFAULT NULL,
  `p_kelurahanpj` tinyint(4) DEFAULT NULL,
  `kecamatanpj` enum('Yes','No') DEFAULT NULL,
  `p_kecamatanpj` tinyint(4) DEFAULT NULL,
  `kabupatenpj` enum('Yes','No') DEFAULT NULL,
  `p_kabupatenpj` tinyint(4) DEFAULT NULL,
  `alamat_dom` enum('Yes','No') DEFAULT NULL,
  `p_alamat_dom` tinyint(4) DEFAULT NULL,
  `kelurahan_dom` enum('Yes','No') DEFAULT NULL,
  `p_kelurahan_dom` tinyint(4) DEFAULT NULL,
  `kecamatan_dom` enum('Yes','No') DEFAULT NULL,
  `p_kecamatan_dom` tinyint(4) DEFAULT NULL,
  `kabupaten_dom` enum('Yes','No') DEFAULT NULL,
  `p_kabupaten_dom` tinyint(4) DEFAULT NULL
) ENGINE=MyISAM DEFAULT CHARSET=latin1;

/*Table structure for table `set_keterlambatan` */

DROP TABLE IF EXISTS `set_keterlambatan`;

CREATE TABLE `set_keterlambatan` (
  `toleransi` int(11) DEFAULT NULL,
  `terlambat1` int(11) DEFAULT NULL,
  `terlambat2` int(11) DEFAULT NULL
) ENGINE=MyISAM DEFAULT CHARSET=latin1;

/*Table structure for table `set_lemburhb` */

DROP TABLE IF EXISTS `set_lemburhb`;

CREATE TABLE `set_lemburhb` (
  `tnj` double NOT NULL,
  PRIMARY KEY (`tnj`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

/*Table structure for table `set_lemburhr` */

DROP TABLE IF EXISTS `set_lemburhr`;

CREATE TABLE `set_lemburhr` (
  `tnj` double NOT NULL,
  PRIMARY KEY (`tnj`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

/*Table structure for table `set_lokasi` */

DROP TABLE IF EXISTS `set_lokasi`;

CREATE TABLE `set_lokasi` (
  `kd_bangsal` char(5) NOT NULL,
  `asal_stok` enum('Gunakan Stok Utama Obat','Gunakan Stok Bangsal') NOT NULL,
  KEY `kd_bangsal` (`kd_bangsal`),
  CONSTRAINT `set_lokasi_ibfk_1` FOREIGN KEY (`kd_bangsal`) REFERENCES `bangsal` (`kd_bangsal`) ON DELETE CASCADE ON UPDATE CASCADE
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

/*Table structure for table `set_modal_payment` */

DROP TABLE IF EXISTS `set_modal_payment`;

CREATE TABLE `set_modal_payment` (
  `modal_awal` double DEFAULT NULL
) ENGINE=MyISAM DEFAULT CHARSET=latin1;

/*Table structure for table `set_no_rkm_medis` */

DROP TABLE IF EXISTS `set_no_rkm_medis`;

CREATE TABLE `set_no_rkm_medis` (
  `no_rkm_medis` varchar(15) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

/*Table structure for table `set_nota` */

DROP TABLE IF EXISTS `set_nota`;

CREATE TABLE `set_nota` (
  `notaralan` varchar(11) NOT NULL,
  `kwitansiralan` varchar(11) NOT NULL,
  `nota1ranap` varchar(11) NOT NULL,
  `nota2ranap` varchar(11) NOT NULL,
  `kwitansiranap` varchar(11) NOT NULL,
  `notaapotek` varchar(11) NOT NULL,
  `notalabrad` varchar(11) NOT NULL,
  `cetaknotasimpanralan` enum('Yes','No') NOT NULL,
  `cetaknotasimpanranap` enum('Yes','No') NOT NULL,
  `rinciandokterralan` enum('Yes','No') NOT NULL,
  `rinciandokterranap` enum('Yes','No') NOT NULL,
  `centangdokterralan` enum('Yes','No') NOT NULL,
  `centangdokterranap` enum('Yes','No') NOT NULL,
  `tampilkan_administrasi_di_billingranap` enum('Yes','No') NOT NULL,
  `rincianoperasi` enum('Yes','No') DEFAULT NULL,
  `tampilkan_ppnobat_ralan` enum('Yes','No') DEFAULT NULL,
  `tampilkan_ppnobat_ranap` enum('Yes','No') DEFAULT NULL,
  `tampilkan_tombol_nota_ralan` enum('Yes','No') DEFAULT NULL,
  `tampilkan_tombol_nota_ranap` enum('Yes','No') DEFAULT NULL,
  `verifikasi_penjualan_di_kasir` enum('Yes','No') DEFAULT NULL,
  `verifikasi_penyerahan_darah_di_kasir` enum('Yes','No') DEFAULT NULL,
  `cetaknotasimpanpenjualan` enum('Yes','No') DEFAULT NULL,
  `tampilkan_tombol_nota_penjualan` enum('Yes','No') DEFAULT NULL,
  `centangobatralan` enum('Yes','No') DEFAULT NULL,
  `jumlah_lembar_sep_bpjs` varchar(11) NOT NULL
) ENGINE=MyISAM DEFAULT CHARSET=latin1;

/*Table structure for table `set_otomatis_tindakan_ralan` */

DROP TABLE IF EXISTS `set_otomatis_tindakan_ralan`;

CREATE TABLE `set_otomatis_tindakan_ralan` (
  `kd_dokter` varchar(20) NOT NULL,
  `kd_jenis_prw` varchar(15) NOT NULL,
  `kd_pj` char(3) NOT NULL DEFAULT '',
  PRIMARY KEY (`kd_dokter`,`kd_jenis_prw`,`kd_pj`),
  KEY `kd_jenis_prw` (`kd_jenis_prw`),
  KEY `kd_pj` (`kd_pj`),
  CONSTRAINT `set_otomatis_tindakan_ralan_ibfk_1` FOREIGN KEY (`kd_dokter`) REFERENCES `dokter` (`kd_dokter`) ON DELETE CASCADE ON UPDATE CASCADE,
  CONSTRAINT `set_otomatis_tindakan_ralan_ibfk_2` FOREIGN KEY (`kd_jenis_prw`) REFERENCES `jns_perawatan` (`kd_jenis_prw`) ON DELETE CASCADE ON UPDATE CASCADE,
  CONSTRAINT `set_otomatis_tindakan_ralan_ibfk_3` FOREIGN KEY (`kd_pj`) REFERENCES `penjab` (`kd_pj`) ON DELETE CASCADE ON UPDATE CASCADE
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

/*Table structure for table `set_otomatis_tindakan_ralan_dokterpetugas` */

DROP TABLE IF EXISTS `set_otomatis_tindakan_ralan_dokterpetugas`;

CREATE TABLE `set_otomatis_tindakan_ralan_dokterpetugas` (
  `kd_dokter` varchar(20) NOT NULL,
  `kd_jenis_prw` varchar(15) NOT NULL,
  `kd_pj` char(3) NOT NULL DEFAULT '',
  PRIMARY KEY (`kd_dokter`,`kd_jenis_prw`,`kd_pj`),
  KEY `kd_jenis_prw` (`kd_jenis_prw`) USING BTREE,
  KEY `kd_pj` (`kd_pj`) USING BTREE,
  CONSTRAINT `set_otomatis_tindakan_ralan_dokterpetugas_ibfk_1` FOREIGN KEY (`kd_dokter`) REFERENCES `dokter` (`kd_dokter`) ON DELETE CASCADE ON UPDATE CASCADE,
  CONSTRAINT `set_otomatis_tindakan_ralan_dokterpetugas_ibfk_2` FOREIGN KEY (`kd_jenis_prw`) REFERENCES `jns_perawatan` (`kd_jenis_prw`) ON DELETE CASCADE ON UPDATE CASCADE,
  CONSTRAINT `set_otomatis_tindakan_ralan_dokterpetugas_ibfk_3` FOREIGN KEY (`kd_pj`) REFERENCES `penjab` (`kd_pj`) ON DELETE CASCADE ON UPDATE CASCADE
) ENGINE=InnoDB DEFAULT CHARSET=latin1 ROW_FORMAT=COMPACT;

/*Table structure for table `set_otomatis_tindakan_ralan_petugas` */

DROP TABLE IF EXISTS `set_otomatis_tindakan_ralan_petugas`;

CREATE TABLE `set_otomatis_tindakan_ralan_petugas` (
  `kd_jenis_prw` varchar(15) NOT NULL,
  `kd_pj` char(3) NOT NULL,
  PRIMARY KEY (`kd_jenis_prw`,`kd_pj`),
  KEY `kd_pj` (`kd_pj`) USING BTREE,
  CONSTRAINT `set_otomatis_tindakan_ralan_petugas_ibfk_1` FOREIGN KEY (`kd_jenis_prw`) REFERENCES `jns_perawatan` (`kd_jenis_prw`) ON DELETE CASCADE ON UPDATE CASCADE,
  CONSTRAINT `set_otomatis_tindakan_ralan_petugas_ibfk_2` FOREIGN KEY (`kd_pj`) REFERENCES `penjab` (`kd_pj`) ON DELETE CASCADE ON UPDATE CASCADE
) ENGINE=InnoDB DEFAULT CHARSET=latin1 ROW_FORMAT=COMPACT;

/*Table structure for table `set_pjlab` */

DROP TABLE IF EXISTS `set_pjlab`;

CREATE TABLE `set_pjlab` (
  `kd_dokterlab` varchar(20) NOT NULL,
  `kd_dokterrad` varchar(20) NOT NULL,
  `kd_dokterhemodialisa` varchar(20) NOT NULL,
  `kd_dokterutd` varchar(20) DEFAULT NULL,
  `aktivasi_LIS` enum('0','1') NOT NULL,
  PRIMARY KEY (`kd_dokterlab`,`kd_dokterrad`,`kd_dokterhemodialisa`),
  KEY `kd_dokterrad` (`kd_dokterrad`),
  KEY `kd_dokterhemodialisa` (`kd_dokterhemodialisa`),
  KEY `kd_dokterutd` (`kd_dokterutd`),
  CONSTRAINT `set_pjlab_ibfk_1` FOREIGN KEY (`kd_dokterlab`) REFERENCES `dokter` (`kd_dokter`) ON DELETE CASCADE ON UPDATE CASCADE,
  CONSTRAINT `set_pjlab_ibfk_2` FOREIGN KEY (`kd_dokterrad`) REFERENCES `dokter` (`kd_dokter`) ON DELETE CASCADE ON UPDATE CASCADE,
  CONSTRAINT `set_pjlab_ibfk_3` FOREIGN KEY (`kd_dokterhemodialisa`) REFERENCES `dokter` (`kd_dokter`) ON DELETE CASCADE ON UPDATE CASCADE,
  CONSTRAINT `set_pjlab_ibfk_4` FOREIGN KEY (`kd_dokterutd`) REFERENCES `dokter` (`kd_dokter`) ON DELETE CASCADE ON UPDATE CASCADE
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

/*Table structure for table `set_resume` */

DROP TABLE IF EXISTS `set_resume`;

CREATE TABLE `set_resume` (
  `tahun` year(4) NOT NULL,
  `bulan` tinyint(4) NOT NULL,
  `pendapatan_resume` double NOT NULL,
  `persen_rs` double NOT NULL,
  `bagian_rs` double NOT NULL,
  `persen_kry` double NOT NULL,
  `bagian_kry` double NOT NULL,
  PRIMARY KEY (`tahun`,`bulan`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

/*Table structure for table `set_service_ranap` */

DROP TABLE IF EXISTS `set_service_ranap`;

CREATE TABLE `set_service_ranap` (
  `nama_service` varchar(100) NOT NULL DEFAULT '',
  `besar` double DEFAULT NULL,
  `laborat` enum('Yes','No') DEFAULT NULL,
  `radiologi` enum('Yes','No') DEFAULT NULL,
  `operasi` enum('Yes','No') DEFAULT NULL,
  `obat` enum('Yes','No') DEFAULT NULL,
  `ranap_dokter` enum('Yes','No') DEFAULT NULL,
  `ranap_paramedis` enum('Yes','No') DEFAULT NULL,
  `ralan_dokter` enum('Yes','No') DEFAULT NULL,
  `ralan_paramedis` enum('Yes','No') DEFAULT NULL,
  `tambahan` enum('Yes','No') DEFAULT NULL,
  `potongan` enum('Yes','No') DEFAULT NULL,
  `kamar` enum('Yes','No') DEFAULT NULL,
  `registrasi` enum('Yes','No') DEFAULT NULL,
  `harian` enum('Yes','No') DEFAULT NULL,
  `retur_Obat` enum('Yes','No') DEFAULT NULL,
  `resep_Pulang` enum('Yes','No') DEFAULT NULL,
  PRIMARY KEY (`nama_service`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

/*Table structure for table `set_service_ranap_piutang` */

DROP TABLE IF EXISTS `set_service_ranap_piutang`;

CREATE TABLE `set_service_ranap_piutang` (
  `nama_service` varchar(100) NOT NULL DEFAULT '',
  `besar` double DEFAULT NULL,
  `laborat` enum('Yes','No') DEFAULT NULL,
  `radiologi` enum('Yes','No') DEFAULT NULL,
  `operasi` enum('Yes','No') DEFAULT NULL,
  `obat` enum('Yes','No') DEFAULT NULL,
  `ranap_dokter` enum('Yes','No') DEFAULT NULL,
  `ranap_paramedis` enum('Yes','No') DEFAULT NULL,
  `ralan_dokter` enum('Yes','No') DEFAULT NULL,
  `ralan_paramedis` enum('Yes','No') DEFAULT NULL,
  `tambahan` enum('Yes','No') DEFAULT NULL,
  `potongan` enum('Yes','No') DEFAULT NULL,
  `kamar` enum('Yes','No') DEFAULT NULL,
  `registrasi` enum('Yes','No') DEFAULT NULL,
  `harian` enum('Yes','No') DEFAULT NULL,
  `retur_Obat` enum('Yes','No') DEFAULT NULL,
  `resep_Pulang` enum('Yes','No') DEFAULT NULL,
  PRIMARY KEY (`nama_service`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

/*Table structure for table `set_tahun` */

DROP TABLE IF EXISTS `set_tahun`;

CREATE TABLE `set_tahun` (
  `tahun` year(4) NOT NULL,
  `bulan` tinyint(2) NOT NULL,
  `jmlhr` int(11) NOT NULL,
  `jmllbr` int(11) NOT NULL,
  `normal` int(11) NOT NULL,
  PRIMARY KEY (`tahun`,`bulan`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

/*Table structure for table `set_tarif` */

DROP TABLE IF EXISTS `set_tarif`;

CREATE TABLE `set_tarif` (
  `poli_ralan` enum('Yes','No') NOT NULL,
  `cara_bayar_ralan` enum('Yes','No') NOT NULL,
  `ruang_ranap` enum('Yes','No') NOT NULL,
  `cara_bayar_ranap` enum('Yes','No') NOT NULL,
  `cara_bayar_lab` enum('Yes','No') NOT NULL,
  `cara_bayar_radiologi` enum('Yes','No') NOT NULL,
  `cara_bayar_operasi` enum('Yes','No') DEFAULT NULL,
  `kelas_ranap` enum('Yes','No') NOT NULL,
  `kelas_lab` enum('Yes','No') NOT NULL,
  `kelas_radiologi` enum('Yes','No') NOT NULL,
  `kelas_operasi` enum('Yes','No') NOT NULL,
  `selisih_tarif_bpjs1` double DEFAULT NULL,
  `selisih_tarif_bpjs2` double DEFAULT NULL,
  `pajak_sewa_tempat` double DEFAULT NULL,
  KEY `poli_ralan` (`poli_ralan`,`cara_bayar_ralan`,`ruang_ranap`,`cara_bayar_ranap`,`cara_bayar_lab`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

/*Table structure for table `set_tnjanak` */

DROP TABLE IF EXISTS `set_tnjanak`;

CREATE TABLE `set_tnjanak` (
  `tnj` double NOT NULL,
  PRIMARY KEY (`tnj`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

/*Table structure for table `set_tnjnikah` */

DROP TABLE IF EXISTS `set_tnjnikah`;

CREATE TABLE `set_tnjnikah` (
  `tnj` double NOT NULL,
  PRIMARY KEY (`tnj`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

/*Table structure for table `set_tuslah` */

DROP TABLE IF EXISTS `set_tuslah`;

CREATE TABLE `set_tuslah` (
  `tahun` year(4) NOT NULL,
  `bulan` tinyint(4) NOT NULL,
  `pendapatan_tuslah` double NOT NULL,
  `persen_rs` double NOT NULL,
  `bagian_rs` double NOT NULL,
  `persen_kry` double NOT NULL,
  `bagian_kry` double NOT NULL,
  PRIMARY KEY (`tahun`,`bulan`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

/*Table structure for table `set_urut_no_rkm_medis` */

DROP TABLE IF EXISTS `set_urut_no_rkm_medis`;

CREATE TABLE `set_urut_no_rkm_medis` (
  `urutan` enum('Straight','Middle','Terminal') NOT NULL,
  `tahun` enum('Yes','No') NOT NULL,
  `bulan` enum('Yes','No') NOT NULL,
  `posisi_tahun_bulan` enum('Depan','Belakang') DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

/*Table structure for table `set_validasi_registrasi` */

DROP TABLE IF EXISTS `set_validasi_registrasi`;

CREATE TABLE `set_validasi_registrasi` (
  `wajib_closing_kasir` enum('Yes','No') DEFAULT NULL
) ENGINE=MyISAM DEFAULT CHARSET=latin1 ROW_FORMAT=FIXED;

/*Table structure for table `set_warung` */

DROP TABLE IF EXISTS `set_warung`;

CREATE TABLE `set_warung` (
  `tahun` year(4) NOT NULL,
  `bulan` tinyint(4) NOT NULL,
  `pendapatan_warung` double NOT NULL,
  `persen_rs` double NOT NULL,
  `bagian_rs` double NOT NULL,
  `persen_kry` double NOT NULL,
  `bagian_kry` double NOT NULL,
  PRIMARY KEY (`tahun`,`bulan`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

/*Table structure for table `setpenjualan` */

DROP TABLE IF EXISTS `setpenjualan`;

CREATE TABLE `setpenjualan` (
  `ralan` double DEFAULT NULL,
  `kelas1` double DEFAULT NULL,
  `kelas2` double DEFAULT NULL,
  `kelas3` double DEFAULT NULL,
  `utama` double DEFAULT NULL,
  `vip` double DEFAULT NULL,
  `vvip` double DEFAULT NULL,
  `beliluar` double DEFAULT NULL,
  `jualbebas` double DEFAULT NULL,
  `karyawan` double DEFAULT NULL,
  `kdjns` char(4) DEFAULT NULL,
  KEY `kdjns` (`kdjns`),
  CONSTRAINT `setpenjualan_ibfk_1` FOREIGN KEY (`kdjns`) REFERENCES `jenis` (`kdjns`) ON UPDATE CASCADE
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

/*Table structure for table `setsms` */

DROP TABLE IF EXISTS `setsms`;

CREATE TABLE `setsms` (
  `kode_sms` varchar(200) NOT NULL DEFAULT '',
  `sintax_balasan` text DEFAULT NULL,
  PRIMARY KEY (`kode_sms`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

/*Table structure for table `setting` */

DROP TABLE IF EXISTS `setting`;

CREATE TABLE `setting` (
  `nama_instansi` varchar(60) NOT NULL DEFAULT '',
  `alamat_instansi` varchar(150) DEFAULT NULL,
  `kabupaten` varchar(30) DEFAULT NULL,
  `propinsi` varchar(30) DEFAULT NULL,
  `kontak` varchar(50) NOT NULL,
  `email` varchar(50) NOT NULL,
  `aktifkan` enum('Yes','No') NOT NULL,
  `kode_ppk` varchar(15) DEFAULT NULL,
  `kode_ppkinhealth` varchar(15) DEFAULT NULL,
  `wallpaper` longblob DEFAULT NULL,
  `logo` longblob NOT NULL,
  `logo_hitam_putih` longblob NOT NULL,
  `logo_kabupaten` longblob NOT NULL,
  PRIMARY KEY (`nama_instansi`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

/*Table structure for table `setting_qr` */

DROP TABLE IF EXISTS `setting_qr`;

CREATE TABLE `setting_qr` (
  `judul` varchar(255) DEFAULT NULL,
  `gambar` longblob DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1 ROW_FORMAT=COMPACT;

/*Table structure for table `sidikjari` */

DROP TABLE IF EXISTS `sidikjari`;

CREATE TABLE `sidikjari` (
  `id` int(11) NOT NULL,
  `sidikjari` text NOT NULL,
  PRIMARY KEY (`id`),
  CONSTRAINT `sidikjari_ibfk_1` FOREIGN KEY (`id`) REFERENCES `pegawai` (`id`) ON UPDATE CASCADE
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

/*Table structure for table `sidikjaripasien` */

DROP TABLE IF EXISTS `sidikjaripasien`;

CREATE TABLE `sidikjaripasien` (
  `no_rkm_medis` varchar(15) NOT NULL,
  `sidikjari` text NOT NULL,
  PRIMARY KEY (`no_rkm_medis`),
  CONSTRAINT `sidikjaripasien_ibfk_1` FOREIGN KEY (`no_rkm_medis`) REFERENCES `pasien` (`no_rkm_medis`) ON DELETE CASCADE ON UPDATE CASCADE
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

/*Table structure for table `sisrute_rujukan_keluar` */

DROP TABLE IF EXISTS `sisrute_rujukan_keluar`;

CREATE TABLE `sisrute_rujukan_keluar` (
  `no_rawat` varchar(17) NOT NULL,
  `no_rujuk` varchar(40) NOT NULL,
  `no_rkm_medis` varchar(15) NOT NULL,
  `nm_pasien` varchar(40) NOT NULL,
  `no_ktp` varchar(20) NOT NULL,
  `no_peserta` varchar(25) NOT NULL,
  `jk` enum('L','P') NOT NULL,
  `tgl_lahir` date NOT NULL,
  `tmp_lahir` varchar(15) NOT NULL,
  `alamat` varchar(200) NOT NULL,
  `no_tlp` varchar(40) NOT NULL,
  `jns_rujukan` enum('1. Rawat Jalan','2. Rawat Darurat/Inap','3. Parsial') NOT NULL,
  `tgl_rujuk` datetime NOT NULL,
  `kd_faskes_tujuan` varchar(12) NOT NULL,
  `nm_faskes_tujuan` varchar(200) NOT NULL,
  `kd_alasan` varchar(5) NOT NULL,
  `alasan_rujuk` varchar(150) NOT NULL,
  `alasan_lainnya` varchar(50) NOT NULL,
  `kd_diagnosa` varchar(10) NOT NULL,
  `diagnosa_rujuk` varchar(400) NOT NULL,
  `nik_dokter` varchar(20) NOT NULL,
  `dokter_perujuk` varchar(50) NOT NULL,
  `nik_petugas` varchar(20) NOT NULL,
  `petugas_entry` varchar(50) NOT NULL,
  `anamnesis_pemeriksaan` varchar(700) NOT NULL,
  `kesadaran` enum('1. Sadar','2. Tidak Sadar') NOT NULL,
  `tekanan_darah` varchar(7) NOT NULL,
  `nadi` varchar(3) NOT NULL,
  `suhu` varchar(5) NOT NULL,
  `respirasi` varchar(3) NOT NULL,
  `keadaan_umum` varchar(300) NOT NULL,
  `tingkat_nyeri` enum('0. Tidak Nyeri','1. Ringan','2. Sedang','3. Berat') NOT NULL,
  `alergi` varchar(50) NOT NULL,
  `laboratorium` varchar(1000) NOT NULL,
  `radiologi` varchar(1000) NOT NULL,
  `terapitindakan` varchar(1000) NOT NULL,
  PRIMARY KEY (`no_rawat`),
  CONSTRAINT `sisrute_rujukan_keluar_ibfk_1` FOREIGN KEY (`no_rawat`) REFERENCES `reg_periksa` (`no_rawat`) ON DELETE CASCADE ON UPDATE CASCADE
) ENGINE=InnoDB DEFAULT CHARSET=latin1 ROW_FORMAT=COMPACT;

/*Table structure for table `skdp_bpjs` */

DROP TABLE IF EXISTS `skdp_bpjs`;

CREATE TABLE `skdp_bpjs` (
  `tahun` year(4) NOT NULL,
  `no_rkm_medis` varchar(15) DEFAULT NULL,
  `diagnosa` varchar(50) NOT NULL,
  `terapi` varchar(50) NOT NULL,
  `alasan1` varchar(50) DEFAULT NULL,
  `alasan2` varchar(50) DEFAULT NULL,
  `rtl1` varchar(50) DEFAULT NULL,
  `rtl2` varchar(50) DEFAULT NULL,
  `tanggal_datang` date DEFAULT NULL,
  `tanggal_rujukan` date NOT NULL,
  `no_antrian` varchar(6) NOT NULL,
  `kd_dokter` varchar(20) DEFAULT NULL,
  `status` enum('Menunggu','Sudah Periksa','Batal Periksa') NOT NULL,
  `kd_poli` char(5) NOT NULL,
  PRIMARY KEY (`tahun`,`no_antrian`),
  KEY `no_rkm_medis` (`no_rkm_medis`) USING BTREE,
  KEY `kd_dokter` (`kd_dokter`) USING BTREE,
  CONSTRAINT `skdp_bpjs_ibfk_1` FOREIGN KEY (`no_rkm_medis`) REFERENCES `pasien` (`no_rkm_medis`) ON UPDATE CASCADE,
  CONSTRAINT `skdp_bpjs_ibfk_2` FOREIGN KEY (`kd_dokter`) REFERENCES `dokter` (`kd_dokter`) ON DELETE CASCADE ON UPDATE CASCADE
) ENGINE=InnoDB DEFAULT CHARSET=latin1 ROW_FORMAT=COMPACT;

/*Table structure for table `sms` */

DROP TABLE IF EXISTS `sms`;

CREATE TABLE `sms` (
  `id_pesan` int(10) unsigned NOT NULL AUTO_INCREMENT,
  `sms_masuk` varchar(255) DEFAULT NULL,
  `no_hp` varchar(15) DEFAULT NULL,
  `pdu_pesan` varchar(255) DEFAULT NULL,
  `encoding` varchar(20) DEFAULT NULL,
  `id_gateway` varchar(20) DEFAULT NULL,
  `tgl_sms` datetime DEFAULT NULL,
  PRIMARY KEY (`id_pesan`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

/*Table structure for table `spesialis` */

DROP TABLE IF EXISTS `spesialis`;

CREATE TABLE `spesialis` (
  `kd_sps` char(5) NOT NULL DEFAULT '',
  `nm_sps` varchar(30) DEFAULT NULL,
  PRIMARY KEY (`kd_sps`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

/*Table structure for table `status_gizi_inap` */

DROP TABLE IF EXISTS `status_gizi_inap`;

CREATE TABLE `status_gizi_inap` (
  `no_rawat` varchar(17) NOT NULL,
  `tgl_input` date DEFAULT NULL,
  `status_gizi` enum('-','BURUK','KURANG','NORMAL','LEBIH','OBESITAS') DEFAULT NULL,
  `ruang_rawat` enum('-','VIP/SVIP','ICU/ICCU','RKPD','ZAAL','BEDAH','ANAK','PARU','AS-SAMI UMUM','AS-SAMI JANTUNG','AR-RAUDAH SYARAF','AR-RAUDAH MT-KK-THT','BERSALIN','ISOLASI COVID19','ISOLASI BAYI COVID19') DEFAULT NULL,
  PRIMARY KEY (`no_rawat`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1 ROW_FORMAT=COMPACT;

/*Table structure for table `stok_bulanan` */

DROP TABLE IF EXISTS `stok_bulanan`;

CREATE TABLE `stok_bulanan` (
  `kode_brng` varchar(15) NOT NULL,
  `periode` varchar(8) NOT NULL,
  `kd_bangsal` char(15) NOT NULL,
  `stok_awal` double DEFAULT NULL,
  `tgl_input_awal` date DEFAULT NULL,
  `stok_akhir` double DEFAULT NULL,
  `tgl_input_akhir` date DEFAULT NULL,
  PRIMARY KEY (`kode_brng`,`periode`,`kd_bangsal`) USING BTREE,
  KEY `kode_brng` (`kode_brng`) USING BTREE,
  KEY `periode` (`periode`) USING BTREE,
  KEY `tgl_input_awal` (`tgl_input_awal`) USING BTREE,
  KEY `tgl_input_akhir` (`tgl_input_akhir`) USING BTREE,
  KEY `kd_bangsal` (`kd_bangsal`) USING BTREE,
  CONSTRAINT `stok_bulanan_ibfk_1` FOREIGN KEY (`kode_brng`) REFERENCES `databarang` (`kode_brng`) ON DELETE CASCADE ON UPDATE CASCADE,
  CONSTRAINT `stok_bulanan_ibfk_2` FOREIGN KEY (`kd_bangsal`) REFERENCES `bangsal` (`kd_bangsal`) ON DELETE CASCADE ON UPDATE CASCADE
) ENGINE=InnoDB DEFAULT CHARSET=latin1 ROW_FORMAT=COMPACT;

/*Table structure for table `stok_obat_pasien` */

DROP TABLE IF EXISTS `stok_obat_pasien`;

CREATE TABLE `stok_obat_pasien` (
  `tanggal` date NOT NULL,
  `no_rawat` varchar(18) NOT NULL,
  `kode_brng` varchar(15) NOT NULL,
  `jumlah` double NOT NULL,
  `kd_bangsal` char(5) NOT NULL,
  PRIMARY KEY (`tanggal`,`no_rawat`,`kode_brng`),
  KEY `no_rawat` (`no_rawat`),
  KEY `kode_brng` (`kode_brng`),
  KEY `kd_bangsal` (`kd_bangsal`),
  CONSTRAINT `stok_obat_pasien_ibfk_2` FOREIGN KEY (`kode_brng`) REFERENCES `databarang` (`kode_brng`) ON DELETE CASCADE ON UPDATE CASCADE,
  CONSTRAINT `stok_obat_pasien_ibfk_3` FOREIGN KEY (`kd_bangsal`) REFERENCES `bangsal` (`kd_bangsal`) ON UPDATE CASCADE,
  CONSTRAINT `stok_obat_pasien_ibfk_4` FOREIGN KEY (`no_rawat`) REFERENCES `reg_periksa` (`no_rawat`) ON UPDATE CASCADE
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

/*Table structure for table `stts_kerja` */

DROP TABLE IF EXISTS `stts_kerja`;

CREATE TABLE `stts_kerja` (
  `stts` char(3) NOT NULL,
  `ktg` varchar(20) NOT NULL,
  `indek` tinyint(4) NOT NULL,
  PRIMARY KEY (`stts`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

/*Table structure for table `stts_wp` */

DROP TABLE IF EXISTS `stts_wp`;

CREATE TABLE `stts_wp` (
  `stts` char(5) NOT NULL,
  `ktg` varchar(50) NOT NULL,
  PRIMARY KEY (`stts`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

/*Table structure for table `subrekening` */

DROP TABLE IF EXISTS `subrekening`;

CREATE TABLE `subrekening` (
  `kd_rek` varchar(15) NOT NULL,
  `kd_rek2` varchar(15) NOT NULL,
  PRIMARY KEY (`kd_rek2`),
  KEY `kd_rek` (`kd_rek`) USING BTREE,
  CONSTRAINT `subrekening_ibfk_1` FOREIGN KEY (`kd_rek`) REFERENCES `rekening` (`kd_rek`) ON UPDATE CASCADE,
  CONSTRAINT `subrekening_ibfk_2` FOREIGN KEY (`kd_rek2`) REFERENCES `rekening` (`kd_rek`) ON DELETE CASCADE ON UPDATE CASCADE
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

/*Table structure for table `suku_bangsa` */

DROP TABLE IF EXISTS `suku_bangsa`;

CREATE TABLE `suku_bangsa` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `nama_suku_bangsa` varchar(30) DEFAULT NULL,
  PRIMARY KEY (`id`),
  UNIQUE KEY `nama_suku_bangsa` (`nama_suku_bangsa`)
) ENGINE=InnoDB AUTO_INCREMENT=34 DEFAULT CHARSET=latin1;

/*Table structure for table `surat_tindakan_kedokteran` */

DROP TABLE IF EXISTS `surat_tindakan_kedokteran`;

CREATE TABLE `surat_tindakan_kedokteran` (
  `no_rawat` varchar(17) NOT NULL,
  `tgl_surat` date DEFAULT NULL,
  `jam_surat` time DEFAULT NULL,
  `nm_penjab` varchar(150) DEFAULT NULL,
  `umur_penjab` varchar(4) DEFAULT NULL,
  `jk_penjab` enum('L','P') DEFAULT NULL,
  `alamat_penjab` varchar(255) DEFAULT NULL,
  `kd_kel` int(11) DEFAULT NULL,
  `kd_kec` int(11) DEFAULT NULL,
  `kd_kab` int(11) DEFAULT NULL,
  `hubungan_dg_pasien` enum('Pasien','Ayah','Ibu','Anak','Suami','Istri','Sepupu','Saudara','Paman','Bibi','Kakek','Nenek','Teman','Tetangga','Ipar','Besan','Menantu','Mertua','Keponakan') DEFAULT NULL,
  `jns_surat` enum('PERSETUJUAN','PENOLAKAN') DEFAULT NULL,
  `kasus_tindakan` enum('Ranap','Ralan') DEFAULT NULL,
  `nm_tindakan_kedokteran` varchar(255) DEFAULT NULL,
  `alasan_penolakan` varchar(255) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1 ROW_FORMAT=COMPACT;

/*Table structure for table `tagihan_obat_langsung` */

DROP TABLE IF EXISTS `tagihan_obat_langsung`;

CREATE TABLE `tagihan_obat_langsung` (
  `no_rawat` varchar(18) NOT NULL,
  `besar_tagihan` double NOT NULL,
  KEY `no_rawat` (`no_rawat`),
  CONSTRAINT `tagihan_obat_langsung_ibfk_1` FOREIGN KEY (`no_rawat`) REFERENCES `reg_periksa` (`no_rawat`) ON UPDATE CASCADE
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

/*Table structure for table `tagihan_sadewa` */

DROP TABLE IF EXISTS `tagihan_sadewa`;

CREATE TABLE `tagihan_sadewa` (
  `no_nota` varchar(17) NOT NULL,
  `no_rkm_medis` varchar(15) NOT NULL,
  `nama_pasien` varchar(60) NOT NULL,
  `alamat` varchar(200) NOT NULL,
  `tgl_bayar` datetime NOT NULL,
  `jenis_bayar` enum('Pelunasan','Deposit','Cicilan','Uang Muka') NOT NULL,
  `jumlah_tagihan` double NOT NULL,
  `jumlah_bayar` double NOT NULL,
  `status` enum('Sudah','Belum') NOT NULL,
  `petugas` varchar(20) DEFAULT NULL,
  `no_nota2` varchar(17) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

/*Table structure for table `tambahan_biaya` */

DROP TABLE IF EXISTS `tambahan_biaya`;

CREATE TABLE `tambahan_biaya` (
  `no_rawat` varchar(18) NOT NULL,
  `nama_biaya` varchar(60) NOT NULL,
  `besar_biaya` double NOT NULL,
  `user_penginput` varchar(150) DEFAULT NULL,
  `tgl_simpan` date DEFAULT NULL,
  `jam_simpan` time DEFAULT NULL,
  PRIMARY KEY (`no_rawat`,`nama_biaya`),
  CONSTRAINT `tambahan_biaya_ibfk_1` FOREIGN KEY (`no_rawat`) REFERENCES `reg_periksa` (`no_rawat`) ON UPDATE CASCADE
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

/*Table structure for table `tambahanpotongan` */

DROP TABLE IF EXISTS `tambahanpotongan`;

CREATE TABLE `tambahanpotongan` (
  `indexins` char(4) NOT NULL,
  `potongan` double NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

/*Table structure for table `tambahjaga` */

DROP TABLE IF EXISTS `tambahjaga`;

CREATE TABLE `tambahjaga` (
  `tgl` date NOT NULL,
  `id` int(11) NOT NULL,
  `jml` int(11) NOT NULL,
  PRIMARY KEY (`tgl`,`id`),
  KEY `id` (`id`),
  CONSTRAINT `tambahjaga_ibfk_1` FOREIGN KEY (`id`) REFERENCES `pegawai` (`id`) ON UPDATE CASCADE
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

/*Table structure for table `tampbeli1` */

DROP TABLE IF EXISTS `tampbeli1`;

CREATE TABLE `tampbeli1` (
  `kode_brng` varchar(15) NOT NULL DEFAULT '',
  `nama_brng` varchar(100) DEFAULT NULL,
  `satuan` varchar(10) DEFAULT NULL,
  `satuan_stok` varchar(10) DEFAULT NULL,
  `h_beli` double DEFAULT NULL,
  `jumlah` double DEFAULT NULL,
  `jumlah_stok` double DEFAULT NULL,
  `total` double DEFAULT NULL,
  PRIMARY KEY (`kode_brng`),
  KEY `kode_brng` (`kode_brng`),
  CONSTRAINT `tampbeli1_ibfk_1` FOREIGN KEY (`kode_brng`) REFERENCES `databarang` (`kode_brng`) ON DELETE CASCADE ON UPDATE CASCADE
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

/*Table structure for table `tampjual1` */

DROP TABLE IF EXISTS `tampjual1`;

CREATE TABLE `tampjual1` (
  `kode_brng` varchar(15) NOT NULL DEFAULT '',
  `nama_brng` varchar(100) DEFAULT NULL,
  `satuan` varchar(10) DEFAULT NULL,
  `h_jual` double DEFAULT NULL,
  `h_beli` double NOT NULL,
  `jumlah` double DEFAULT NULL,
  `subtotal` double DEFAULT NULL,
  `dis` double DEFAULT NULL,
  `bsr_dis` double DEFAULT NULL,
  `total` double DEFAULT NULL,
  PRIMARY KEY (`kode_brng`),
  KEY `kode_brng` (`kode_brng`),
  CONSTRAINT `tampjual1_ibfk_1` FOREIGN KEY (`kode_brng`) REFERENCES `databarang` (`kode_brng`) ON DELETE CASCADE ON UPDATE CASCADE
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

/*Table structure for table `tampjurnal` */

DROP TABLE IF EXISTS `tampjurnal`;

CREATE TABLE `tampjurnal` (
  `kd_rek` char(15) NOT NULL,
  `nm_rek` varchar(100) DEFAULT NULL,
  `debet` double DEFAULT NULL,
  `kredit` double DEFAULT NULL,
  PRIMARY KEY (`kd_rek`),
  KEY `nm_rek` (`nm_rek`) USING BTREE,
  KEY `debet` (`debet`) USING BTREE,
  KEY `kredit` (`kredit`) USING BTREE
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

/*Table structure for table `tamppiutang` */

DROP TABLE IF EXISTS `tamppiutang`;

CREATE TABLE `tamppiutang` (
  `kode_brng` varchar(15) NOT NULL DEFAULT '',
  `nama_brng` varchar(50) DEFAULT NULL,
  `satuan` varchar(10) DEFAULT NULL,
  `h_jual` double DEFAULT NULL,
  `h_beli` double DEFAULT NULL,
  `jumlah` double DEFAULT NULL,
  `subtotal` double DEFAULT NULL,
  `dis` double DEFAULT NULL,
  `bsr_dis` double DEFAULT NULL,
  `total` double DEFAULT NULL,
  PRIMARY KEY (`kode_brng`),
  KEY `kode_brng` (`kode_brng`),
  CONSTRAINT `tamppiutang_ibfk_1` FOREIGN KEY (`kode_brng`) REFERENCES `databarang` (`kode_brng`) ON DELETE CASCADE ON UPDATE CASCADE
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

/*Table structure for table `tampreturbeli` */

DROP TABLE IF EXISTS `tampreturbeli`;

CREATE TABLE `tampreturbeli` (
  `no_faktur` varchar(20) NOT NULL DEFAULT '',
  `kode_brng` varchar(15) NOT NULL DEFAULT '',
  `nama_brng` varchar(100) DEFAULT NULL,
  `satuan` varchar(10) DEFAULT NULL,
  `h_beli` double DEFAULT NULL,
  `jml_beli` double DEFAULT NULL,
  `h_retur` double DEFAULT NULL,
  `jml_retur` double DEFAULT NULL,
  `total` double DEFAULT NULL,
  `no_batch` varchar(10) NOT NULL,
  `jml_retur2` double DEFAULT NULL,
  PRIMARY KEY (`no_faktur`,`kode_brng`),
  KEY `no_faktur` (`no_faktur`),
  KEY `kode_brng` (`kode_brng`),
  CONSTRAINT `tampreturbeli_ibfk_2` FOREIGN KEY (`kode_brng`) REFERENCES `databarang` (`kode_brng`) ON DELETE CASCADE ON UPDATE CASCADE
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

/*Table structure for table `tampreturjual` */

DROP TABLE IF EXISTS `tampreturjual`;

CREATE TABLE `tampreturjual` (
  `nota_jual` varchar(8) NOT NULL DEFAULT '',
  `kode_brng` varchar(15) NOT NULL DEFAULT '',
  `nama_brng` varchar(100) DEFAULT NULL,
  `jml_jual` double DEFAULT NULL,
  `h_jual` double DEFAULT NULL,
  `jml_retur` double DEFAULT NULL,
  `h_retur` double DEFAULT NULL,
  `satuan` varchar(10) DEFAULT NULL,
  `subtotal` double DEFAULT NULL,
  PRIMARY KEY (`nota_jual`,`kode_brng`),
  KEY `nota_jual` (`nota_jual`),
  KEY `kode_brng` (`kode_brng`),
  CONSTRAINT `tampreturjual_ibfk_3` FOREIGN KEY (`kode_brng`) REFERENCES `databarang` (`kode_brng`) ON DELETE CASCADE ON UPDATE CASCADE
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

/*Table structure for table `tampreturpiutang` */

DROP TABLE IF EXISTS `tampreturpiutang`;

CREATE TABLE `tampreturpiutang` (
  `nota_piutang` varchar(8) NOT NULL DEFAULT '',
  `kode_brng` varchar(15) NOT NULL DEFAULT '',
  `nama_brng` varchar(100) DEFAULT NULL,
  `jml_piutang` double DEFAULT NULL,
  `h_piutang` double DEFAULT NULL,
  `jml_retur` double DEFAULT NULL,
  `h_retur` double DEFAULT NULL,
  `satuan` varchar(10) DEFAULT NULL,
  `subtotal` double DEFAULT NULL,
  PRIMARY KEY (`nota_piutang`,`kode_brng`),
  KEY `kode_brng` (`kode_brng`),
  CONSTRAINT `tampreturpiutang_ibfk_2` FOREIGN KEY (`kode_brng`) REFERENCES `databarang` (`kode_brng`) ON DELETE CASCADE ON UPDATE CASCADE
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

/*Table structure for table `tarif_eklaim` */

DROP TABLE IF EXISTS `tarif_eklaim`;

CREATE TABLE `tarif_eklaim` (
  `INACBG` char(10) NOT NULL DEFAULT '',
  `REGIONAL` char(10) NOT NULL DEFAULT '',
  `KODE_TARIFF` char(10) NOT NULL DEFAULT '',
  `KELAS_RAWAT` char(10) NOT NULL DEFAULT '',
  `JENIS_PELAYANAN` char(10) NOT NULL DEFAULT '',
  `TARIFF_ORIGINAL` char(30) NOT NULL DEFAULT '',
  `TARIFF` char(30) NOT NULL DEFAULT '',
  PRIMARY KEY (`INACBG`,`REGIONAL`,`KODE_TARIFF`,`KELAS_RAWAT`)
) ENGINE=MyISAM DEFAULT CHARSET=utf8;

/*Table structure for table `tarif_penjab_ralan` */

DROP TABLE IF EXISTS `tarif_penjab_ralan`;

CREATE TABLE `tarif_penjab_ralan` (
  `kode_tarif` varchar(15) NOT NULL,
  `deskripsi` varchar(150) NOT NULL,
  `tarif` double NOT NULL,
  `kd_pj` char(3) NOT NULL,
  PRIMARY KEY (`kode_tarif`),
  KEY `kd_pj` (`kd_pj`),
  CONSTRAINT `tarif_penjab_ralan_ibfk_1` FOREIGN KEY (`kd_pj`) REFERENCES `penjab` (`kd_pj`) ON UPDATE CASCADE
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

/*Table structure for table `tarif_penjab_ranap` */

DROP TABLE IF EXISTS `tarif_penjab_ranap`;

CREATE TABLE `tarif_penjab_ranap` (
  `kode_tarif` varchar(15) NOT NULL,
  `deskripsi` varchar(150) NOT NULL,
  `kelas_1` double NOT NULL,
  `kelas_2` double NOT NULL,
  `kelas_3` double NOT NULL,
  `kelas_utama` double NOT NULL,
  `kelas_vip` double NOT NULL,
  `kelas_vvip` int(11) NOT NULL,
  `lainnya` double NOT NULL,
  `kd_pj` char(3) NOT NULL,
  PRIMARY KEY (`kode_tarif`),
  KEY `kd_pj` (`kd_pj`),
  CONSTRAINT `tarif_penjab_ranap_ibfk_1` FOREIGN KEY (`kd_pj`) REFERENCES `penjab` (`kd_pj`) ON UPDATE CASCADE
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

/*Table structure for table `template_laboratorium` */

DROP TABLE IF EXISTS `template_laboratorium`;

CREATE TABLE `template_laboratorium` (
  `kd_jenis_prw` varchar(15) NOT NULL,
  `id_template` int(11) NOT NULL AUTO_INCREMENT,
  `Pemeriksaan` varchar(200) NOT NULL,
  `satuan` varchar(20) NOT NULL,
  `nilai_rujukan_ld` varchar(20) NOT NULL,
  `nilai_rujukan_la` varchar(20) NOT NULL,
  `nilai_rujukan_pd` varchar(20) NOT NULL,
  `nilai_rujukan_pa` varchar(20) NOT NULL,
  `bagian_rs` double NOT NULL,
  `bhp` double NOT NULL,
  `bagian_perujuk` double NOT NULL,
  `bagian_dokter` double NOT NULL,
  `bagian_laborat` double NOT NULL,
  `kso` double DEFAULT NULL,
  `menejemen` double DEFAULT NULL,
  `biaya_item` double NOT NULL,
  `urut` int(4) DEFAULT NULL,
  PRIMARY KEY (`id_template`),
  KEY `kd_jenis_prw` (`kd_jenis_prw`),
  KEY `Pemeriksaan` (`Pemeriksaan`) USING BTREE,
  KEY `satuan` (`satuan`) USING BTREE,
  KEY `nilai_rujukan_ld` (`nilai_rujukan_ld`) USING BTREE,
  KEY `nilai_rujukan_la` (`nilai_rujukan_la`) USING BTREE,
  KEY `nilai_rujukan_pd` (`nilai_rujukan_pd`) USING BTREE,
  KEY `nilai_rujukan_pa` (`nilai_rujukan_pa`) USING BTREE,
  KEY `bagian_rs` (`bagian_rs`) USING BTREE,
  KEY `bhp` (`bhp`) USING BTREE,
  KEY `bagian_perujuk` (`bagian_perujuk`) USING BTREE,
  KEY `bagian_dokter` (`bagian_dokter`) USING BTREE,
  KEY `bagian_laborat` (`bagian_laborat`) USING BTREE,
  KEY `kso` (`kso`) USING BTREE,
  KEY `menejemen` (`menejemen`) USING BTREE,
  KEY `biaya_item` (`biaya_item`) USING BTREE,
  KEY `urut` (`urut`) USING BTREE,
  CONSTRAINT `template_laboratorium_ibfk_1` FOREIGN KEY (`kd_jenis_prw`) REFERENCES `jns_perawatan_lab` (`kd_jenis_prw`) ON DELETE CASCADE ON UPDATE CASCADE
) ENGINE=InnoDB AUTO_INCREMENT=551 DEFAULT CHARSET=latin1;

/*Table structure for table `template_utd` */

DROP TABLE IF EXISTS `template_utd`;

CREATE TABLE `template_utd` (
  `kd_jenis_prw` varchar(15) DEFAULT NULL,
  `id_template` int(11) NOT NULL AUTO_INCREMENT,
  `pemeriksaan` varchar(200) DEFAULT NULL,
  `bagian_rs` double DEFAULT NULL,
  `bhp` double DEFAULT NULL,
  `bagian_perujuk` double DEFAULT NULL,
  `bagian_dokter` double DEFAULT NULL,
  `petugas_utd` double DEFAULT NULL,
  `kso` double DEFAULT NULL,
  `menejemen` double DEFAULT NULL,
  `biaya_item` double DEFAULT NULL,
  `urut` tinyint(4) DEFAULT NULL,
  PRIMARY KEY (`id_template`),
  KEY `kd_jenis_prw` (`kd_jenis_prw`) USING BTREE,
  CONSTRAINT `template_utd_ibfk_1` FOREIGN KEY (`kd_jenis_prw`) REFERENCES `jns_perawatan_utd` (`kd_jenis_prw`) ON DELETE CASCADE ON UPDATE CASCADE
) ENGINE=InnoDB DEFAULT CHARSET=latin1 ROW_FORMAT=COMPACT;

/*Table structure for table `temporary` */

DROP TABLE IF EXISTS `temporary`;

CREATE TABLE `temporary` (
  `no` int(11) NOT NULL AUTO_INCREMENT,
  `temp1` varchar(100) NOT NULL,
  `temp2` varchar(100) NOT NULL,
  `temp3` varchar(255) NOT NULL,
  `temp4` varchar(400) NOT NULL,
  `temp5` varchar(255) NOT NULL,
  `temp6` varchar(100) NOT NULL,
  `temp7` varchar(100) NOT NULL,
  `temp8` varchar(100) NOT NULL,
  `temp9` varchar(100) NOT NULL,
  `temp10` varchar(255) NOT NULL,
  `temp11` varchar(100) NOT NULL,
  `temp12` varchar(100) NOT NULL,
  `temp13` varchar(100) NOT NULL,
  `temp14` varchar(100) NOT NULL,
  `temp15` varchar(100) NOT NULL,
  `temp16` varchar(100) NOT NULL,
  `temp17` varchar(100) NOT NULL,
  `temp18` varchar(100) NOT NULL,
  `temp19` varchar(100) NOT NULL,
  `temp20` varchar(100) NOT NULL,
  `temp21` varchar(100) NOT NULL,
  `temp22` varchar(100) NOT NULL,
  `temp23` varchar(100) NOT NULL,
  `temp24` varchar(100) NOT NULL,
  `temp25` varchar(100) NOT NULL,
  `temp26` varchar(100) NOT NULL,
  `temp27` varchar(100) NOT NULL,
  `temp28` varchar(100) NOT NULL,
  `temp29` varchar(100) NOT NULL,
  `temp30` varchar(100) NOT NULL,
  `temp31` varchar(100) NOT NULL,
  `temp32` varchar(100) NOT NULL,
  `temp33` varchar(100) NOT NULL,
  `temp34` varchar(100) NOT NULL,
  `temp35` varchar(100) NOT NULL,
  `temp36` varchar(100) NOT NULL,
  `temp37` varchar(100) NOT NULL,
  PRIMARY KEY (`no`)
) ENGINE=MyISAM AUTO_INCREMENT=3672710 DEFAULT CHARSET=latin1;

/*Table structure for table `temporary2` */

DROP TABLE IF EXISTS `temporary2`;

CREATE TABLE `temporary2` (
  `no` int(11) NOT NULL AUTO_INCREMENT,
  `temp1` varchar(100) NOT NULL,
  `temp2` varchar(100) NOT NULL,
  `temp3` varchar(100) NOT NULL,
  `temp4` varchar(100) NOT NULL,
  `temp5` varchar(100) NOT NULL,
  `temp6` varchar(100) NOT NULL,
  `temp7` varchar(100) NOT NULL,
  `temp8` varchar(100) NOT NULL,
  `temp9` varchar(100) NOT NULL,
  `temp10` varchar(100) NOT NULL,
  `temp11` varchar(100) NOT NULL,
  `temp12` varchar(100) NOT NULL,
  `temp13` varchar(100) NOT NULL,
  `temp14` varchar(100) NOT NULL,
  `temp15` varchar(100) NOT NULL,
  `temp16` varchar(100) NOT NULL,
  `temp17` varchar(100) NOT NULL,
  `temp18` varchar(100) NOT NULL,
  `temp19` varchar(100) NOT NULL,
  `temp20` varchar(100) NOT NULL,
  `temp21` varchar(100) NOT NULL,
  `temp22` varchar(100) NOT NULL,
  `temp23` varchar(100) NOT NULL,
  `temp24` varchar(100) NOT NULL,
  `temp25` varchar(100) NOT NULL,
  `temp26` varchar(100) NOT NULL,
  `temp27` varchar(100) NOT NULL,
  `temp28` varchar(100) NOT NULL,
  `temp29` varchar(100) NOT NULL,
  `temp30` varchar(100) NOT NULL,
  `temp31` varchar(100) NOT NULL,
  `temp32` varchar(100) NOT NULL,
  `temp33` varchar(100) NOT NULL,
  `temp34` varchar(100) NOT NULL,
  `temp35` varchar(100) NOT NULL,
  `temp36` varchar(100) NOT NULL,
  `temp37` varchar(100) NOT NULL,
  `temp38` varchar(100) NOT NULL,
  `temp39` varchar(100) NOT NULL,
  `temp40` varchar(100) NOT NULL,
  `temp41` varchar(100) NOT NULL,
  `temp42` varchar(100) NOT NULL,
  `temp43` varchar(100) NOT NULL,
  `temp44` varchar(100) NOT NULL,
  `temp45` varchar(100) NOT NULL,
  `temp46` varchar(100) NOT NULL,
  `temp47` varchar(100) NOT NULL,
  `temp48` varchar(100) NOT NULL,
  `temp49` varchar(100) NOT NULL,
  `temp50` varchar(100) NOT NULL,
  `temp51` varchar(100) NOT NULL,
  `temp52` varchar(100) NOT NULL,
  `temp53` varchar(100) NOT NULL,
  `temp54` varchar(100) NOT NULL,
  `temp55` varchar(100) NOT NULL,
  `temp56` varchar(100) NOT NULL,
  `temp57` varchar(100) NOT NULL,
  `temp58` varchar(100) NOT NULL,
  `temp59` varchar(100) NOT NULL,
  `temp60` varchar(100) NOT NULL,
  `temp61` varchar(100) NOT NULL,
  `temp62` varchar(100) NOT NULL,
  `temp63` varchar(100) NOT NULL,
  `temp64` varchar(100) NOT NULL,
  `temp65` varchar(100) NOT NULL,
  `temp66` varchar(100) NOT NULL,
  `temp67` varchar(100) NOT NULL,
  `temp68` varchar(100) NOT NULL,
  `temp69` varchar(100) NOT NULL,
  `temp70` varchar(100) NOT NULL,
  `temp71` varchar(100) NOT NULL,
  `temp72` varchar(100) NOT NULL,
  `temp73` varchar(100) NOT NULL,
  `temp74` varchar(100) NOT NULL,
  `temp75` varchar(100) NOT NULL,
  `temp76` varchar(100) NOT NULL,
  `temp77` varchar(100) NOT NULL,
  `temp78` varchar(100) NOT NULL,
  `temp79` varchar(100) NOT NULL,
  `temp80` varchar(100) NOT NULL,
  `temp81` varchar(100) NOT NULL,
  `temp82` varchar(100) NOT NULL,
  `temp83` varchar(100) NOT NULL,
  `temp84` varchar(100) NOT NULL,
  `temp85` varchar(100) NOT NULL,
  `temp86` varchar(100) NOT NULL,
  `temp87` varchar(100) NOT NULL,
  `temp88` varchar(100) NOT NULL,
  `temp89` varchar(100) NOT NULL,
  `temp90` varchar(100) NOT NULL,
  `temp91` varchar(100) NOT NULL,
  `temp92` varchar(100) NOT NULL,
  `temp93` varchar(100) NOT NULL,
  `temp94` varchar(100) NOT NULL,
  `temp95` varchar(100) NOT NULL,
  `temp96` varchar(100) NOT NULL,
  `temp97` varchar(100) NOT NULL,
  `temp98` varchar(100) NOT NULL,
  `temp99` varchar(100) NOT NULL,
  `temp100` varchar(100) NOT NULL,
  PRIMARY KEY (`no`)
) ENGINE=MyISAM AUTO_INCREMENT=95 DEFAULT CHARSET=latin1;

/*Table structure for table `temporary_bayar_ralan` */

DROP TABLE IF EXISTS `temporary_bayar_ralan`;

CREATE TABLE `temporary_bayar_ralan` (
  `no` int(11) NOT NULL AUTO_INCREMENT,
  `temp1` varchar(100) NOT NULL,
  `temp2` varchar(200) NOT NULL,
  `temp3` varchar(100) NOT NULL,
  `temp4` varchar(100) NOT NULL,
  `temp5` varchar(100) NOT NULL,
  `temp6` varchar(100) NOT NULL,
  `temp7` varchar(100) NOT NULL,
  `temp8` varchar(100) NOT NULL,
  `temp9` varchar(100) NOT NULL,
  `temp10` varchar(100) NOT NULL,
  `temp11` varchar(100) NOT NULL,
  `temp12` varchar(100) NOT NULL,
  `temp13` varchar(100) NOT NULL,
  `temp14` varchar(100) NOT NULL,
  `temp15` varchar(100) NOT NULL,
  `temp16` varchar(100) NOT NULL,
  `temp17` varchar(100) NOT NULL,
  PRIMARY KEY (`no`)
) ENGINE=MyISAM AUTO_INCREMENT=1812724 DEFAULT CHARSET=latin1;

/*Table structure for table `temporary_bayar_ranap` */

DROP TABLE IF EXISTS `temporary_bayar_ranap`;

CREATE TABLE `temporary_bayar_ranap` (
  `no` int(11) NOT NULL AUTO_INCREMENT,
  `temp1` varchar(100) NOT NULL,
  `temp2` varchar(200) NOT NULL,
  `temp3` varchar(100) NOT NULL,
  `temp4` varchar(100) NOT NULL,
  `temp5` varchar(100) NOT NULL,
  `temp6` varchar(100) NOT NULL,
  `temp7` varchar(100) NOT NULL,
  `temp8` varchar(100) NOT NULL,
  `temp9` varchar(100) NOT NULL,
  `temp10` varchar(100) NOT NULL,
  `temp11` varchar(100) NOT NULL,
  `temp12` varchar(100) NOT NULL,
  `temp13` varchar(100) NOT NULL,
  `temp14` varchar(100) NOT NULL,
  `temp15` varchar(100) NOT NULL,
  `temp16` varchar(100) NOT NULL,
  `temp17` varchar(100) NOT NULL,
  PRIMARY KEY (`no`)
) ENGINE=MyISAM AUTO_INCREMENT=4529660 DEFAULT CHARSET=latin1;

/*Table structure for table `temporary_formulir_klaim` */

DROP TABLE IF EXISTS `temporary_formulir_klaim`;

CREATE TABLE `temporary_formulir_klaim` (
  `temp1` longtext NOT NULL,
  `temp2` longtext NOT NULL,
  `temp3` longtext NOT NULL,
  `temp4` longtext NOT NULL,
  `temp5` longtext NOT NULL,
  `temp6` longtext NOT NULL,
  `temp7` longtext NOT NULL,
  `temp8` longtext NOT NULL,
  `temp9` longtext NOT NULL,
  `temp10` longtext NOT NULL,
  `temp11` longtext NOT NULL,
  `temp12` longtext NOT NULL,
  `temp13` longtext NOT NULL,
  `temp14` longtext NOT NULL,
  `temp15` longtext NOT NULL,
  `temp16` longtext NOT NULL,
  `temp17` longtext NOT NULL,
  `temp18` longtext NOT NULL,
  `temp19` longtext NOT NULL,
  `temp20` longtext NOT NULL,
  `temp21` longtext NOT NULL,
  `temp22` longtext NOT NULL,
  `temp23` longtext NOT NULL,
  `temp24` longtext NOT NULL,
  `temp25` longtext NOT NULL,
  `temp26` longtext NOT NULL,
  `temp27` longtext NOT NULL,
  `temp28` longtext NOT NULL,
  `temp29` longtext NOT NULL,
  `temp30` longtext NOT NULL,
  `temp31` longtext NOT NULL,
  `temp32` longtext NOT NULL,
  `temp33` longtext NOT NULL,
  `temp34` longtext NOT NULL,
  `temp35` longtext NOT NULL,
  `temp36` longtext NOT NULL,
  `temp37` longtext NOT NULL
) ENGINE=MyISAM DEFAULT CHARSET=latin1 ROW_FORMAT=DYNAMIC;

/*Table structure for table `temporary_gizi` */

DROP TABLE IF EXISTS `temporary_gizi`;

CREATE TABLE `temporary_gizi` (
  `no` int(100) NOT NULL AUTO_INCREMENT,
  `temp1` varchar(1000) NOT NULL,
  `temp2` varchar(1000) NOT NULL,
  `temp3` varchar(1000) NOT NULL,
  `temp4` varchar(1000) NOT NULL,
  `temp5` varchar(1000) NOT NULL,
  `temp6` varchar(1000) NOT NULL,
  `temp7` varchar(1000) NOT NULL,
  `temp8` varchar(1000) NOT NULL,
  `temp9` varchar(1000) NOT NULL,
  `temp10` varchar(1000) NOT NULL,
  `temp11` varchar(1000) NOT NULL,
  `temp12` varchar(1000) NOT NULL,
  `temp13` varchar(1000) NOT NULL,
  `temp14` varchar(1000) NOT NULL,
  `temp15` varchar(1000) NOT NULL,
  `temp16` varchar(100) NOT NULL,
  `temp17` varchar(100) NOT NULL,
  `temp18` varchar(100) NOT NULL,
  `temp19` varchar(100) NOT NULL,
  `temp20` varchar(100) NOT NULL,
  `temp21` varchar(100) NOT NULL,
  `temp22` varchar(100) NOT NULL,
  `temp23` varchar(100) NOT NULL,
  `temp24` varchar(100) NOT NULL,
  `temp25` varchar(100) NOT NULL,
  `temp26` varchar(100) NOT NULL,
  `temp27` varchar(100) NOT NULL,
  `temp28` varchar(100) NOT NULL,
  `temp29` varchar(100) NOT NULL,
  `temp30` varchar(100) NOT NULL,
  `temp31` varchar(100) NOT NULL,
  `temp32` varchar(100) NOT NULL,
  `temp33` varchar(100) NOT NULL,
  `temp34` varchar(100) NOT NULL,
  `temp35` varchar(100) NOT NULL,
  `temp36` varchar(100) NOT NULL,
  `temp37` varchar(100) NOT NULL,
  PRIMARY KEY (`no`)
) ENGINE=MyISAM AUTO_INCREMENT=61 DEFAULT CHARSET=latin1 ROW_FORMAT=DYNAMIC;

/*Table structure for table `temporary_lis` */

DROP TABLE IF EXISTS `temporary_lis`;

CREATE TABLE `temporary_lis` (
  `temp1` varchar(100) NOT NULL,
  `temp2` varchar(100) NOT NULL,
  `temp3` varchar(255) NOT NULL,
  `temp4` varchar(400) NOT NULL,
  `temp5` varchar(255) NOT NULL,
  `temp6` varchar(100) NOT NULL,
  `temp7` varchar(100) NOT NULL,
  `temp8` varchar(100) NOT NULL,
  `temp9` varchar(100) NOT NULL,
  `temp10` varchar(255) NOT NULL,
  `temp11` varchar(100) NOT NULL,
  `temp12` varchar(100) NOT NULL,
  `temp13` varchar(100) NOT NULL,
  `temp14` varchar(100) NOT NULL,
  `temp15` varchar(100) NOT NULL,
  `temp16` varchar(100) NOT NULL,
  `temp17` varchar(100) NOT NULL,
  `temp18` varchar(100) NOT NULL,
  `temp19` varchar(100) NOT NULL,
  `temp20` varchar(100) NOT NULL,
  `temp21` varchar(100) NOT NULL,
  `temp22` varchar(100) NOT NULL,
  `temp23` varchar(100) NOT NULL,
  `temp24` varchar(100) NOT NULL,
  `temp25` varchar(100) NOT NULL,
  `temp26` varchar(100) NOT NULL,
  `temp27` varchar(100) NOT NULL,
  `temp28` varchar(100) NOT NULL,
  `temp29` varchar(100) NOT NULL,
  `temp30` varchar(100) NOT NULL,
  `temp31` varchar(100) NOT NULL,
  `temp32` varchar(100) NOT NULL,
  `temp33` varchar(100) NOT NULL,
  `temp34` varchar(100) NOT NULL,
  `temp35` varchar(100) NOT NULL,
  `temp36` varchar(100) NOT NULL,
  `temp37` varchar(100) NOT NULL
) ENGINE=MyISAM DEFAULT CHARSET=latin1 ROW_FORMAT=DYNAMIC;

/*Table structure for table `temporary_obat_injeksi` */

DROP TABLE IF EXISTS `temporary_obat_injeksi`;

CREATE TABLE `temporary_obat_injeksi` (
  `temp1` varchar(400) DEFAULT NULL,
  `temp2` varchar(400) DEFAULT NULL,
  `temp3` varchar(400) DEFAULT NULL,
  `temp4` varchar(400) DEFAULT NULL,
  `temp5` varchar(400) DEFAULT NULL,
  `temp6` varchar(400) DEFAULT NULL,
  `temp7` varchar(400) DEFAULT NULL,
  `temp8` varchar(400) DEFAULT NULL,
  `temp9` varchar(400) DEFAULT NULL,
  `temp10` varchar(400) DEFAULT NULL,
  `temp11` varchar(400) DEFAULT NULL,
  `temp12` varchar(400) DEFAULT NULL,
  `temp13` varchar(400) DEFAULT NULL,
  `temp14` varchar(400) DEFAULT NULL,
  `temp15` varchar(400) DEFAULT NULL,
  `temp16` varchar(400) DEFAULT NULL,
  `temp17` varchar(400) DEFAULT NULL,
  `temp18` varchar(400) DEFAULT NULL,
  `temp19` varchar(400) DEFAULT NULL,
  `temp20` varchar(400) DEFAULT NULL,
  `temp21` varchar(100) DEFAULT NULL,
  `temp22` varchar(100) DEFAULT NULL,
  `temp23` varchar(100) DEFAULT NULL,
  `temp24` varchar(100) DEFAULT NULL,
  `temp25` varchar(100) DEFAULT NULL,
  `temp26` varchar(100) DEFAULT NULL,
  `temp27` varchar(100) DEFAULT NULL,
  `temp28` varchar(100) DEFAULT NULL,
  `temp29` varchar(100) DEFAULT NULL,
  `temp30` varchar(100) DEFAULT NULL,
  `temp31` varchar(100) DEFAULT NULL,
  `temp32` varchar(100) DEFAULT NULL,
  `temp33` varchar(100) DEFAULT NULL,
  `temp34` varchar(100) DEFAULT NULL,
  `temp35` varchar(100) DEFAULT NULL,
  `temp36` varchar(100) DEFAULT NULL,
  `temp37` varchar(100) DEFAULT NULL
) ENGINE=MyISAM DEFAULT CHARSET=latin1 ROW_FORMAT=DYNAMIC;

/*Table structure for table `temporary_permintaan_lab` */

DROP TABLE IF EXISTS `temporary_permintaan_lab`;

CREATE TABLE `temporary_permintaan_lab` (
  `no` int(100) NOT NULL,
  `temp1` varchar(1000) NOT NULL,
  `temp2` varchar(1000) NOT NULL,
  `temp3` varchar(1000) NOT NULL,
  `temp4` varchar(1000) NOT NULL,
  `temp5` varchar(1000) NOT NULL,
  `temp6` varchar(1000) NOT NULL,
  `temp7` varchar(1000) NOT NULL,
  `temp8` varchar(1000) NOT NULL,
  `temp9` varchar(1000) NOT NULL,
  `temp10` varchar(1000) NOT NULL,
  `temp11` varchar(1000) NOT NULL,
  `temp12` varchar(1000) NOT NULL,
  `temp13` varchar(1000) NOT NULL,
  `temp14` varchar(1000) NOT NULL,
  `temp15` varchar(1000) NOT NULL,
  `temp16` varchar(100) NOT NULL,
  `temp17` varchar(100) NOT NULL,
  `temp18` varchar(100) NOT NULL,
  `temp19` varchar(100) NOT NULL,
  `temp20` varchar(100) NOT NULL,
  `temp21` varchar(100) NOT NULL,
  `temp22` varchar(100) NOT NULL,
  `temp23` varchar(100) NOT NULL,
  `temp24` varchar(100) NOT NULL,
  `temp25` varchar(100) NOT NULL,
  `temp26` varchar(100) NOT NULL,
  `temp27` varchar(100) NOT NULL,
  `temp28` varchar(100) NOT NULL,
  `temp29` varchar(100) NOT NULL,
  `temp30` varchar(100) NOT NULL,
  `temp31` varchar(100) NOT NULL,
  `temp32` varchar(100) NOT NULL,
  `temp33` varchar(100) NOT NULL,
  `temp34` varchar(100) NOT NULL,
  `temp35` varchar(100) NOT NULL,
  `temp36` varchar(100) NOT NULL,
  `temp37` varchar(100) NOT NULL
) ENGINE=MyISAM DEFAULT CHARSET=latin1;

/*Table structure for table `temporary_permintaan_radiologi` */

DROP TABLE IF EXISTS `temporary_permintaan_radiologi`;

CREATE TABLE `temporary_permintaan_radiologi` (
  `no` int(100) NOT NULL,
  `temp1` varchar(1000) NOT NULL,
  `temp2` varchar(1000) NOT NULL,
  `temp3` varchar(1000) NOT NULL,
  `temp4` varchar(1000) NOT NULL,
  `temp5` varchar(1000) NOT NULL,
  `temp6` varchar(1000) NOT NULL,
  `temp7` varchar(1000) NOT NULL,
  `temp8` varchar(1000) NOT NULL,
  `temp9` varchar(1000) NOT NULL,
  `temp10` varchar(1000) NOT NULL,
  `temp11` varchar(1000) NOT NULL,
  `temp12` varchar(1000) NOT NULL,
  `temp13` varchar(1000) NOT NULL,
  `temp14` varchar(1000) NOT NULL,
  `temp15` varchar(1000) NOT NULL,
  `temp16` varchar(100) NOT NULL,
  `temp17` varchar(100) NOT NULL,
  `temp18` varchar(100) NOT NULL,
  `temp19` varchar(100) NOT NULL,
  `temp20` varchar(100) NOT NULL,
  `temp21` varchar(100) NOT NULL,
  `temp22` varchar(100) NOT NULL,
  `temp23` varchar(100) NOT NULL,
  `temp24` varchar(100) NOT NULL,
  `temp25` varchar(100) NOT NULL,
  `temp26` varchar(100) NOT NULL,
  `temp27` varchar(100) NOT NULL,
  `temp28` varchar(100) NOT NULL,
  `temp29` varchar(100) NOT NULL,
  `temp30` varchar(100) NOT NULL,
  `temp31` varchar(100) NOT NULL,
  `temp32` varchar(100) NOT NULL,
  `temp33` varchar(100) NOT NULL,
  `temp34` varchar(100) NOT NULL,
  `temp35` varchar(100) NOT NULL,
  `temp36` varchar(100) NOT NULL,
  `temp37` varchar(100) NOT NULL
) ENGINE=MyISAM DEFAULT CHARSET=latin1;

/*Table structure for table `temporary_presensi` */

DROP TABLE IF EXISTS `temporary_presensi`;

CREATE TABLE `temporary_presensi` (
  `id` int(11) NOT NULL,
  `shift` enum('Pagi','Pagi2','Pagi3','Pagi4','Pagi5','Pagi6','Pagi7','Pagi8','Pagi9','Pagi10','Siang','Siang2','Siang3','Siang4','Siang5','Siang6','Siang7','Siang8','Siang9','Siang10','Malam','Malam2','Malam3','Malam4','Malam5','Malam6','Malam7','Malam8','Malam9','Malam10','Midle Pagi1','Midle Pagi2','Midle Pagi3','Midle Pagi4','Midle Pagi5','Midle Pagi6','Midle Pagi7','Midle Pagi8','Midle Pagi9','Midle Pagi10','Midle Siang1','Midle Siang2','Midle Siang3','Midle Siang4','Midle Siang5','Midle Siang6','Midle Siang7','Midle Siang8','Midle Siang9','Midle Siang10','Midle Malam1','Midle Malam2','Midle Malam3','Midle Malam4','Midle Malam5','Midle Malam6','Midle Malam7','Midle Malam8','Midle Malam9','Midle Malam10') NOT NULL,
  `jam_datang` datetime DEFAULT NULL,
  `jam_pulang` datetime DEFAULT NULL,
  `status` enum('Tepat Waktu','Terlambat Toleransi','Terlambat I','Terlambat II','Tepat Waktu & PSW','Terlambat Toleransi & PSW','Terlambat I & PSW','Terlambat II & PSW') NOT NULL,
  `keterlambatan` varchar(20) NOT NULL,
  `durasi` varchar(20) DEFAULT NULL,
  `photo` varchar(500) NOT NULL,
  PRIMARY KEY (`id`),
  CONSTRAINT `temporary_presensi_ibfk_1` FOREIGN KEY (`id`) REFERENCES `pegawai` (`id`) ON UPDATE CASCADE
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

/*Table structure for table `temporary_ringkasan_pulang` */

DROP TABLE IF EXISTS `temporary_ringkasan_pulang`;

CREATE TABLE `temporary_ringkasan_pulang` (
  `no` int(11) NOT NULL AUTO_INCREMENT,
  `temp1` longtext NOT NULL,
  `temp2` longtext NOT NULL,
  `temp3` longtext NOT NULL,
  `temp4` longtext NOT NULL,
  `temp5` longtext NOT NULL,
  `temp6` longtext NOT NULL,
  `temp7` longtext NOT NULL,
  `temp8` longtext NOT NULL,
  `temp9` longtext NOT NULL,
  `temp10` longtext NOT NULL,
  `temp11` longtext NOT NULL,
  `temp12` longtext NOT NULL,
  `temp13` longtext NOT NULL,
  `temp14` longtext NOT NULL,
  `temp15` longtext NOT NULL,
  `temp16` longtext NOT NULL,
  `temp17` longtext NOT NULL,
  `temp18` longtext NOT NULL,
  `temp19` longtext NOT NULL,
  `temp20` longtext NOT NULL,
  `temp21` longtext NOT NULL,
  `temp22` longtext NOT NULL,
  `temp23` longtext NOT NULL,
  `temp24` longtext NOT NULL,
  `temp25` longtext NOT NULL,
  `temp26` longtext NOT NULL,
  `temp27` longtext NOT NULL,
  `temp28` longtext NOT NULL,
  `temp29` longtext NOT NULL,
  `temp30` longtext NOT NULL,
  `temp31` longtext NOT NULL,
  `temp32` longtext NOT NULL,
  `temp33` longtext NOT NULL,
  `temp34` longtext NOT NULL,
  `temp35` longtext NOT NULL,
  `temp36` longtext NOT NULL,
  `temp37` longtext NOT NULL,
  PRIMARY KEY (`no`)
) ENGINE=MyISAM DEFAULT CHARSET=latin1 ROW_FORMAT=DYNAMIC;

/*Table structure for table `temporary_surveilens_penyakit` */

DROP TABLE IF EXISTS `temporary_surveilens_penyakit`;

CREATE TABLE `temporary_surveilens_penyakit` (
  `kd_penyakit` varchar(10) NOT NULL,
  `kd_penyakit2` varchar(10) NOT NULL,
  KEY `kd_penyakit` (`kd_penyakit`),
  KEY `kd_penyakit2` (`kd_penyakit2`),
  CONSTRAINT `temporary_surveilens_penyakit_ibfk_1` FOREIGN KEY (`kd_penyakit`) REFERENCES `penyakit` (`kd_penyakit`) ON DELETE CASCADE ON UPDATE CASCADE,
  CONSTRAINT `temporary_surveilens_penyakit_ibfk_2` FOREIGN KEY (`kd_penyakit2`) REFERENCES `penyakit` (`kd_penyakit`) ON DELETE CASCADE ON UPDATE CASCADE
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

/*Table structure for table `temporary_tambahan_potongan` */

DROP TABLE IF EXISTS `temporary_tambahan_potongan`;

CREATE TABLE `temporary_tambahan_potongan` (
  `no_rawat` varchar(17) NOT NULL,
  `nama_tambahan` varchar(100) NOT NULL,
  `biaya` double NOT NULL,
  `status` varchar(30) NOT NULL,
  PRIMARY KEY (`no_rawat`,`nama_tambahan`,`status`)
) ENGINE=MyISAM DEFAULT CHARSET=latin1;

/*Table structure for table `temppanggilnorawat` */

DROP TABLE IF EXISTS `temppanggilnorawat`;

CREATE TABLE `temppanggilnorawat` (
  `no_rawat` varchar(17) NOT NULL
) ENGINE=MyISAM DEFAULT CHARSET=latin1 ROW_FORMAT=DYNAMIC;

/*Table structure for table `temppanggilrm` */

DROP TABLE IF EXISTS `temppanggilrm`;

CREATE TABLE `temppanggilrm` (
  `no_rkm_medis` varchar(15) NOT NULL,
  PRIMARY KEY (`no_rkm_medis`)
) ENGINE=MyISAM DEFAULT CHARSET=latin1;

/*Table structure for table `tindakan` */

DROP TABLE IF EXISTS `tindakan`;

CREATE TABLE `tindakan` (
  `tgl` datetime NOT NULL,
  `id` int(11) NOT NULL,
  `tnd` int(11) NOT NULL,
  `jm` double NOT NULL,
  `nm_pasien` varchar(30) NOT NULL,
  `kamar` varchar(20) NOT NULL,
  `diagnosa` varchar(50) NOT NULL,
  `jmlh` int(11) NOT NULL,
  PRIMARY KEY (`tgl`,`id`,`tnd`,`nm_pasien`),
  KEY `id` (`id`),
  KEY `tnd` (`tnd`),
  CONSTRAINT `tindakan_ibfk_1` FOREIGN KEY (`id`) REFERENCES `pegawai` (`id`) ON UPDATE CASCADE
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

/*Table structure for table `tracker` */

DROP TABLE IF EXISTS `tracker`;

CREATE TABLE `tracker` (
  `nip` varchar(20) NOT NULL,
  `tgl_login` date NOT NULL,
  `jam_login` time NOT NULL,
  PRIMARY KEY (`nip`,`tgl_login`,`jam_login`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

/*Table structure for table `trackersql` */

DROP TABLE IF EXISTS `trackersql`;

CREATE TABLE `trackersql` (
  `tanggal` datetime NOT NULL,
  `sqle` text NOT NULL,
  `usere` varchar(20) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

/*Table structure for table `ubah_penjab` */

DROP TABLE IF EXISTS `ubah_penjab`;

CREATE TABLE `ubah_penjab` (
  `no_rawat` varchar(18) NOT NULL,
  `tgl_ubah` datetime NOT NULL,
  `kd_pj1` char(3) NOT NULL,
  `kd_pj2` char(3) NOT NULL,
  KEY `no_rawat` (`no_rawat`),
  KEY `tgl_ubah` (`tgl_ubah`),
  KEY `kd_pj1` (`kd_pj1`),
  KEY `kd_pj2` (`kd_pj2`),
  CONSTRAINT `ubah_penjab_ibfk_4` FOREIGN KEY (`no_rawat`) REFERENCES `reg_periksa` (`no_rawat`) ON UPDATE CASCADE,
  CONSTRAINT `ubah_penjab_ibfk_5` FOREIGN KEY (`kd_pj1`) REFERENCES `penjab` (`kd_pj`) ON UPDATE CASCADE,
  CONSTRAINT `ubah_penjab_ibfk_6` FOREIGN KEY (`kd_pj2`) REFERENCES `penjab` (`kd_pj`) ON UPDATE CASCADE
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

/*Table structure for table `user` */

DROP TABLE IF EXISTS `user`;

CREATE TABLE `user` (
  `id_user` varchar(700) NOT NULL,
  `password` text NOT NULL,
  `penyakit` enum('true','false') NOT NULL,
  `obat_penyakit` enum('true','false') NOT NULL,
  `dokter` enum('true','false') NOT NULL,
  `jadwal_praktek` enum('true','false') NOT NULL,
  `petugas` enum('true','false') NOT NULL,
  `pasien` enum('true','false') NOT NULL,
  `registrasi` enum('true','false') NOT NULL,
  `tindakan_ralan` enum('true','false') NOT NULL,
  `kamar_inap` enum('true','false') NOT NULL,
  `tindakan_ranap` enum('true','false') NOT NULL,
  `operasi` enum('true','false') NOT NULL,
  `rujukan_keluar` enum('true','false') NOT NULL,
  `rujukan_masuk` enum('true','false') NOT NULL,
  `beri_obat` enum('true','false') NOT NULL,
  `resep_pulang` enum('true','false') NOT NULL,
  `pasien_meninggal` enum('true','false') NOT NULL,
  `diet_pasien` enum('true','false') NOT NULL,
  `kelahiran_bayi` enum('true','false') NOT NULL,
  `periksa_lab` enum('true','false') NOT NULL,
  `periksa_radiologi` enum('true','false') NOT NULL,
  `kasir_ralan` enum('true','false') NOT NULL,
  `deposit_pasien` enum('true','false') NOT NULL,
  `piutang_pasien` enum('true','false') NOT NULL,
  `peminjaman_berkas` enum('true','false') NOT NULL,
  `barcode` enum('true','false') NOT NULL,
  `presensi_harian` enum('true','false') NOT NULL,
  `presensi_bulanan` enum('true','false') NOT NULL,
  `pegawai_admin` enum('true','false') NOT NULL,
  `pegawai_user` enum('true','false') NOT NULL,
  `suplier` enum('true','false') NOT NULL,
  `satuan_barang` enum('true','false') NOT NULL,
  `konversi_satuan` enum('true','false') NOT NULL,
  `jenis_barang` enum('true','false') NOT NULL,
  `obat` enum('true','false') NOT NULL,
  `stok_opname_obat` enum('true','false') NOT NULL,
  `stok_obat_pasien` enum('true','false') NOT NULL,
  `pengadaan_obat` enum('true','false') NOT NULL,
  `pemesanan_obat` enum('true','false') NOT NULL,
  `penjualan_obat` enum('true','false') NOT NULL,
  `piutang_obat` enum('true','false') NOT NULL,
  `retur_ke_suplier` enum('true','false') NOT NULL,
  `retur_dari_pembeli` enum('true','false') NOT NULL,
  `retur_obat_ranap` enum('true','false') NOT NULL,
  `retur_piutang_pasien` enum('true','false') NOT NULL,
  `keuntungan_penjualan` enum('true','false') NOT NULL,
  `keuntungan_beri_obat` enum('true','false') NOT NULL,
  `sirkulasi_obat` enum('true','false') NOT NULL,
  `ipsrs_barang` enum('true','false') NOT NULL,
  `ipsrs_pengadaan_barang` enum('true','false') NOT NULL,
  `ipsrs_stok_keluar` enum('true','false') NOT NULL,
  `ipsrs_rekap_pengadaan` enum('true','false') NOT NULL,
  `ipsrs_rekap_stok_keluar` enum('true','false') NOT NULL,
  `ipsrs_pengeluaran_harian` enum('true','false') NOT NULL,
  `inventaris_jenis` enum('true','false') NOT NULL,
  `inventaris_kategori` enum('true','false') NOT NULL,
  `inventaris_merk` enum('true','false') NOT NULL,
  `inventaris_ruang` enum('true','false') NOT NULL,
  `inventaris_produsen` enum('true','false') NOT NULL,
  `inventaris_koleksi` enum('true','false') NOT NULL,
  `inventaris_inventaris` enum('true','false') NOT NULL,
  `inventaris_sirkulasi` enum('true','false') NOT NULL,
  `parkir_jenis` enum('true','false') NOT NULL,
  `parkir_in` enum('true','false') NOT NULL,
  `parkir_out` enum('true','false') NOT NULL,
  `parkir_rekap_harian` enum('true','false') NOT NULL,
  `parkir_rekap_bulanan` enum('true','false') NOT NULL,
  `informasi_kamar` enum('true','false') NOT NULL,
  `harian_tindakan_poli` enum('true','false') NOT NULL,
  `obat_per_poli` enum('true','false') NOT NULL,
  `obat_per_kamar` enum('true','false') NOT NULL,
  `obat_per_dokter_ralan` enum('true','false') NOT NULL,
  `obat_per_dokter_ranap` enum('true','false') NOT NULL,
  `harian_dokter` enum('true','false') NOT NULL,
  `bulanan_dokter` enum('true','false') NOT NULL,
  `harian_paramedis` enum('true','false') NOT NULL,
  `bulanan_paramedis` enum('true','false') NOT NULL,
  `pembayaran_ralan` enum('true','false') NOT NULL,
  `pembayaran_ranap` enum('true','false') NOT NULL,
  `rekap_pembayaran_ralan` enum('true','false') NOT NULL,
  `rekap_pembayaran_ranap` enum('true','false') NOT NULL,
  `tagihan_masuk` enum('true','false') NOT NULL,
  `tambahan_biaya` enum('true','false') NOT NULL,
  `potongan_biaya` enum('true','false') NOT NULL,
  `resep_obat` enum('true','false') NOT NULL,
  `resume_pasien` enum('true','false') NOT NULL,
  `penyakit_ralan` enum('true','false') NOT NULL,
  `penyakit_ranap` enum('true','false') NOT NULL,
  `kamar` enum('true','false') NOT NULL,
  `tarif_ralan` enum('true','false') NOT NULL,
  `tarif_ranap` enum('true','false') NOT NULL,
  `tarif_lab` enum('true','false') NOT NULL,
  `tarif_radiologi` enum('true','false') NOT NULL,
  `tarif_operasi` enum('true','false') NOT NULL,
  `akun_rekening` enum('true','false') NOT NULL,
  `rekening_tahun` enum('true','false') NOT NULL,
  `posting_jurnal` enum('true','false') NOT NULL,
  `buku_besar` enum('true','false') NOT NULL,
  `cashflow` enum('true','false') NOT NULL,
  `keuangan` enum('true','false') NOT NULL,
  `pengeluaran` enum('true','false') NOT NULL,
  `setup_pjlab` enum('true','false') NOT NULL,
  `setup_otolokasi` enum('true','false') NOT NULL,
  `setup_jam_kamin` enum('true','false') NOT NULL,
  `setup_embalase` enum('true','false') NOT NULL,
  `tracer_login` enum('true','false') NOT NULL,
  `display` enum('true','false') NOT NULL,
  `set_harga_obat` enum('true','false') NOT NULL,
  `set_penggunaan_tarif` enum('true','false') NOT NULL,
  `set_oto_ralan` enum('true','false') NOT NULL,
  `biaya_harian` enum('true','false') NOT NULL,
  `biaya_masuk_sekali` enum('true','false') NOT NULL,
  `set_no_rm` enum('true','false') NOT NULL,
  `billing_ralan` enum('true','false') NOT NULL,
  `billing_ranap` enum('true','false') NOT NULL,
  `jm_ranap_dokter` enum('true','false') NOT NULL,
  `igd` enum('true','false') NOT NULL,
  `barcoderalan` enum('true','false') NOT NULL,
  `barcoderanap` enum('true','false') NOT NULL,
  `set_harga_obat_ralan` enum('true','false') NOT NULL,
  `set_harga_obat_ranap` enum('true','false') NOT NULL,
  `penyakit_pd3i` enum('true','false') NOT NULL,
  `surveilans_pd3i` enum('true','false') NOT NULL,
  `surveilans_ralan` enum('true','false') NOT NULL,
  `diagnosa_pasien` enum('true','false') NOT NULL,
  `surveilans_ranap` enum('true','false') NOT NULL,
  `pny_takmenular_ranap` enum('true','false') NOT NULL,
  `pny_takmenular_ralan` enum('true','false') NOT NULL,
  `kunjungan_ralan` enum('true','false') NOT NULL,
  `rl32` enum('true','false') NOT NULL,
  `rl33` enum('true','false') NOT NULL,
  `rl37` enum('true','false') NOT NULL,
  `rl38` enum('true','false') NOT NULL,
  `harian_tindakan_dokter` enum('true','false') NOT NULL,
  `sms` enum('true','false') NOT NULL,
  `sidikjari` enum('true','false') NOT NULL,
  `jam_masuk` enum('true','false') NOT NULL,
  `jadwal_pegawai` enum('true','false') NOT NULL,
  `parkir_barcode` enum('true','false') NOT NULL,
  `set_nota` enum('true','false') NOT NULL,
  `dpjp_ranap` enum('true','false') NOT NULL,
  `mutasi_barang` enum('true','false') NOT NULL,
  `rl34` enum('true','false') DEFAULT NULL,
  `rl36` enum('true','false') NOT NULL,
  `fee_visit_dokter` enum('true','false') DEFAULT NULL,
  `fee_bacaan_ekg` enum('true','false') DEFAULT NULL,
  `fee_rujukan_rontgen` enum('true','false') DEFAULT NULL,
  `fee_rujukan_ranap` enum('true','false') DEFAULT NULL,
  `fee_ralan` enum('true','false') DEFAULT NULL,
  `akun_bayar` enum('true','false') DEFAULT NULL,
  `bayar_pemesanan_obat` enum('true','false') DEFAULT NULL,
  `obat_per_dokter_peresep` enum('true','false') DEFAULT NULL,
  `ipsrs_jenis_barang` enum('true','false') DEFAULT NULL,
  `pemasukan_lain` enum('true','false') DEFAULT NULL,
  `pengaturan_rekening` enum('true','false') DEFAULT NULL,
  `closing_kasir` enum('true','false') DEFAULT NULL,
  `keterlambatan_presensi` enum('true','false') DEFAULT NULL,
  `set_harga_kamar` enum('true','false') DEFAULT NULL,
  `rekap_per_shift` enum('true','false') DEFAULT NULL,
  `bpjs_cek_nik` enum('true','false') DEFAULT NULL,
  `bpjs_cek_kartu` enum('true','false') DEFAULT NULL,
  `bpjs_cek_riwayat` enum('true','false') DEFAULT NULL,
  `obat_per_cara_bayar` enum('true','false') DEFAULT NULL,
  `kunjungan_ranap` enum('true','false') DEFAULT NULL,
  `bayar_piutang` enum('true','false') DEFAULT NULL,
  `payment_point` enum('true','false') DEFAULT NULL,
  `bpjs_cek_nomor_rujukan` enum('true','false') DEFAULT NULL,
  `icd9` enum('true','false') DEFAULT NULL,
  `darurat_stok` enum('true','false') DEFAULT NULL,
  `retensi_rm` enum('true','false') DEFAULT NULL,
  `temporary_presensi` enum('true','false') DEFAULT NULL,
  `jurnal_harian` enum('true','false') DEFAULT NULL,
  `sirkulasi_obat2` enum('true','false') DEFAULT NULL,
  `edit_registrasi` enum('true','false') DEFAULT NULL,
  `bpjs_referensi_diagnosa` enum('true','false') DEFAULT NULL,
  `bpjs_referensi_poli` enum('true','false') DEFAULT NULL,
  `industrifarmasi` enum('true','false') DEFAULT NULL,
  `harian_js` enum('true','false') DEFAULT NULL,
  `bulanan_js` enum('true','false') DEFAULT NULL,
  `harian_paket_bhp` enum('true','false') DEFAULT NULL,
  `bulanan_paket_bhp` enum('true','false') DEFAULT NULL,
  `piutang_pasien2` enum('true','false') DEFAULT NULL,
  `bpjs_referensi_faskes` enum('true','false') DEFAULT NULL,
  `bpjs_sep` enum('true','false') DEFAULT NULL,
  `pengambilan_utd` enum('true','false') DEFAULT NULL,
  `tarif_utd` enum('true','false') DEFAULT NULL,
  `pengambilan_utd2` enum('true','false') DEFAULT NULL,
  `utd_medis_rusak` enum('true','false') DEFAULT NULL,
  `pengambilan_penunjang_utd` enum('true','false') DEFAULT NULL,
  `pengambilan_penunjang_utd2` enum('true','false') DEFAULT NULL,
  `utd_penunjang_rusak` enum('true','false') DEFAULT NULL,
  `suplier_penunjang` enum('true','false') DEFAULT NULL,
  `utd_donor` enum('true','false') DEFAULT NULL,
  `bpjs_monitoring_klaim` enum('true','false') DEFAULT NULL,
  `utd_cekal_darah` enum('true','false') DEFAULT NULL,
  `utd_komponen_darah` enum('true','false') DEFAULT NULL,
  `utd_stok_darah` enum('true','false') DEFAULT NULL,
  `utd_pemisahan_darah` enum('true','false') DEFAULT NULL,
  `harian_kamar` enum('true','false') DEFAULT NULL,
  `rincian_piutang_pasien` enum('true','false') DEFAULT NULL,
  `keuntungan_beri_obat_nonpiutang` enum('true','false') DEFAULT NULL,
  `reklasifikasi_ralan` enum('true','false') DEFAULT NULL,
  `reklasifikasi_ranap` enum('true','false') DEFAULT NULL,
  `utd_penyerahan_darah` enum('true','false') DEFAULT NULL,
  `hutang_obat` enum('true','false') DEFAULT NULL,
  `riwayat_obat_alkes_bhp` enum('true','false') DEFAULT NULL,
  `sensus_harian_poli` enum('true','false') DEFAULT NULL,
  `rl4a` enum('true','false') DEFAULT NULL,
  `aplicare_referensi_kamar` enum('true','false') DEFAULT NULL,
  `aplicare_ketersediaan_kamar` enum('true','false') DEFAULT NULL,
  `inacbg_klaim_baru_otomatis` enum('true','false') DEFAULT NULL,
  `inacbg_klaim_baru_manual` enum('true','false') DEFAULT NULL,
  `inacbg_coder_nik` enum('true','false') DEFAULT NULL,
  `mutasi_berkas` enum('true','false') DEFAULT NULL,
  `akun_piutang` enum('true','false') DEFAULT NULL,
  `harian_kso` enum('true','false') DEFAULT NULL,
  `bulanan_kso` enum('true','false') DEFAULT NULL,
  `harian_menejemen` enum('true','false') DEFAULT NULL,
  `bulanan_menejemen` enum('true','false') DEFAULT NULL,
  `inhealth_cek_eligibilitas` enum('true','false') DEFAULT NULL,
  `inhealth_referensi_jenpel_ruang_rawat` enum('true','false') DEFAULT NULL,
  `inhealth_referensi_poli` enum('true','false') DEFAULT NULL,
  `inhealth_referensi_faskes` enum('true','false') DEFAULT NULL,
  `inhealth_sjp` enum('true','false') DEFAULT NULL,
  `piutang_ralan` enum('true','false') DEFAULT NULL,
  `piutang_ranap` enum('true','false') DEFAULT NULL,
  `detail_piutang_penjab` enum('true','false') DEFAULT NULL,
  `lama_pelayanan_ralan` enum('true','false') DEFAULT NULL,
  `catatan_pasien` enum('true','false') DEFAULT NULL,
  `rl4b` enum('true','false') DEFAULT NULL,
  `rl4asebab` enum('true','false') DEFAULT NULL,
  `rl4bsebab` enum('true','false') DEFAULT NULL,
  `data_HAIs` enum('true','false') DEFAULT NULL,
  `harian_HAIs` enum('true','false') DEFAULT NULL,
  `bulanan_HAIs` enum('true','false') DEFAULT NULL,
  `hitung_bor` enum('true','false') DEFAULT NULL,
  `perusahaan_pasien` enum('true','false') DEFAULT NULL,
  `resep_dokter` enum('true','false') DEFAULT NULL,
  `lama_pelayanan_apotek` enum('true','false') DEFAULT NULL,
  `hitung_alos` enum('true','false') DEFAULT NULL,
  `detail_tindakan` enum('true','false') DEFAULT NULL,
  `rujukan_poli_internal` enum('true','false') DEFAULT NULL,
  `rekap_poli_anak` enum('true','false') DEFAULT NULL,
  `grafik_kunjungan_poli` enum('true','false') DEFAULT NULL,
  `grafik_kunjungan_perdokter` enum('true','false') DEFAULT NULL,
  `grafik_kunjungan_perpekerjaan` enum('true','false') DEFAULT NULL,
  `grafik_kunjungan_perpendidikan` enum('true','false') DEFAULT NULL,
  `grafik_kunjungan_pertahun` enum('true','false') DEFAULT NULL,
  `berkas_digital_perawatan` enum('true','false') DEFAULT NULL,
  `penyakit_menular_ranap` enum('true','false') DEFAULT NULL,
  `penyakit_menular_ralan` enum('true','false') DEFAULT NULL,
  `grafik_kunjungan_perbulan` enum('true','false') DEFAULT NULL,
  `grafik_kunjungan_pertanggal` enum('true','false') DEFAULT NULL,
  `grafik_kunjungan_demografi` enum('true','false') DEFAULT NULL,
  `grafik_kunjungan_statusdaftartahun` enum('true','false') DEFAULT NULL,
  `grafik_kunjungan_statusdaftartahun2` enum('true','false') DEFAULT NULL,
  `grafik_kunjungan_statusdaftarbulan` enum('true','false') DEFAULT NULL,
  `grafik_kunjungan_statusdaftarbulan2` enum('true','false') DEFAULT NULL,
  `grafik_kunjungan_statusdaftartanggal` enum('true','false') DEFAULT NULL,
  `grafik_kunjungan_statusdaftartanggal2` enum('true','false') DEFAULT NULL,
  `grafik_kunjungan_statusbataltahun` enum('true','false') DEFAULT NULL,
  `grafik_kunjungan_statusbatalbulan` enum('true','false') DEFAULT NULL,
  `pcare_cek_penyakit` enum('true','false') DEFAULT NULL,
  `grafik_kunjungan_statusbataltanggal` enum('true','false') DEFAULT NULL,
  `kategori_barang` enum('true','false') DEFAULT NULL,
  `golongan_barang` enum('true','false') DEFAULT NULL,
  `pemberian_obat_pertanggal` enum('true','false') DEFAULT NULL,
  `penjualan_obat_pertanggal` enum('true','false') DEFAULT NULL,
  `skdp_bpjs` enum('true','false') DEFAULT NULL,
  `rujukan_keluar_vclaim_bpjs` enum('true','false') DEFAULT NULL,
  `booking_registrasi` enum('true','false') DEFAULT NULL,
  `bpjs_cek_riwayat_rujukan_pcare` enum('true','false') DEFAULT NULL,
  `bpjs_cek_riwayat_rujukan_rs` enum('true','false') DEFAULT NULL,
  `bpjs_cek_rujukan_kartu_rs` enum('true','false') DEFAULT NULL,
  `bpjs_cek_tanggal_rujukan` enum('true','false') DEFAULT NULL,
  `bpjs_cek_no_rujukan_rs` enum('true','false') DEFAULT NULL,
  `bpjs_cek_rujukan_kartu_pcare` enum('true','false') DEFAULT NULL,
  `bpjs_cek_referensi_kelas_rawat` enum('true','false') DEFAULT NULL,
  `bpjs_cek_referensi_prosedur` enum('true','false') DEFAULT NULL,
  `bpjs_cek_referensi_dpjp` enum('true','false') DEFAULT NULL,
  `bpjs_cek_referensi_dokter` enum('true','false') DEFAULT NULL,
  `bpjs_cek_referensi_spesialistik` enum('true','false') DEFAULT NULL,
  `bpjs_cek_referensi_ruang_rawat` enum('true','false') DEFAULT NULL,
  `bpjs_cek_referensi_cara_keluar` enum('true','false') DEFAULT NULL,
  `bpjs_cek_referensi_pasca_pulang` enum('true','false') DEFAULT NULL,
  `bpjs_cek_referensi_propinsi` enum('true','false') DEFAULT NULL,
  `bpjs_cek_referensi_kabupaten` enum('true','false') DEFAULT NULL,
  `bpjs_cek_referensi_kecamatan` enum('true','false') DEFAULT NULL,
  `permintaan_lab` enum('true','false') DEFAULT NULL,
  `permintaan_radiologi` enum('true','false') DEFAULT NULL,
  `selisih_tarif_bpjs` enum('true','false') DEFAULT NULL,
  `edit_data_kematian` enum('true','false') DEFAULT NULL,
  `bridging_jamkesda` enum('true','false') DEFAULT NULL,
  `masuk_pindah_pulang_inap` enum('true','false') DEFAULT NULL,
  `masuk_pindah_inap` enum('true','false') DEFAULT NULL,
  `jumlah_macam_diet` enum('true','false') DEFAULT NULL,
  `jumlah_porsi_diet` enum('true','false') DEFAULT NULL,
  `status_gizi` enum('true','false') DEFAULT NULL,
  `gizi_buruk` enum('true','false') DEFAULT NULL,
  `master_faskes` enum('true','false') DEFAULT NULL,
  `set_status_registrasi_ralan` enum('true','false') DEFAULT NULL,
  `telusur_kunjungan_pasien` enum('true','false') DEFAULT NULL,
  `sisrute_referensi_faskes` enum('true','false') DEFAULT NULL,
  `sisrute_referensi_alasanrujuk` enum('true','false') DEFAULT NULL,
  `sisrute_referensi_diagnosa` enum('true','false') DEFAULT NULL,
  `sisrute_rujukan_masuk` enum('true','false') DEFAULT NULL,
  `sisrute_rujukan_keluar` enum('true','false') DEFAULT NULL,
  `barang_cssd` enum('true','false') DEFAULT NULL,
  `status_pulang_inap` enum('true','false') DEFAULT NULL,
  `data_persalinan` enum('true','false') DEFAULT NULL,
  `data_ponek` enum('true','false') DEFAULT NULL,
  `registrasi_booking_dikasir` enum('true','false') DEFAULT NULL,
  `bahasa_pasien` enum('true','false') DEFAULT NULL,
  `suku_pasien` enum('true','false') DEFAULT NULL,
  `harian_hais_ranap` enum('true','false') DEFAULT NULL,
  `bulanan_hais_ranap` enum('true','false') DEFAULT NULL,
  `harian_hais_ralan` enum('true','false') DEFAULT NULL,
  `bulanan_hais_ralan` enum('true','false') DEFAULT NULL,
  `ringkasan_pulang_ranap` enum('true','false') DEFAULT NULL,
  `laporan_farmasi` enum('true','false') DEFAULT NULL,
  `master_masalah_keperawatan` enum('true','false') DEFAULT NULL,
  `penilaian_awal_keperawatan_ralan` enum('true','false') DEFAULT NULL,
  `master_triase_skala1` enum('true','false') DEFAULT NULL,
  `master_triase_skala2` enum('true','false') DEFAULT NULL,
  `master_triase_skala3` enum('true','false') DEFAULT NULL,
  `master_triase_skala4` enum('true','false') DEFAULT NULL,
  `master_triase_skala5` enum('true','false') DEFAULT NULL,
  `data_triase_igd` enum('true','false') DEFAULT NULL,
  `master_triase_pemeriksaan` enum('true','false') DEFAULT NULL,
  `master_triase_macamkasus` enum('true','false') DEFAULT NULL,
  `master_cara_bayar` enum('true','false') DEFAULT NULL,
  `status_kerja_dokter` enum('true','false') DEFAULT NULL,
  `pasien_corona` enum('true','false') DEFAULT NULL,
  `diagnosa_pasien_corona` enum('true','false') DEFAULT NULL,
  `perawatan_pasien_corona` enum('true','false') DEFAULT NULL,
  `inacbg_klaim_baru_manual2` enum('true','false') DEFAULT NULL,
  `indikator_ranap` enum('true','false') DEFAULT NULL,
  `sensus_inap` enum('true','false') DEFAULT NULL,
  `review_rm_igd` enum('true','false') DEFAULT NULL,
  `review_rm_ruangan_h1` enum('true','false') DEFAULT NULL,
  `review_rm_ruangan_pulang` enum('true','false') DEFAULT NULL,
  `review_rm_laporan` enum('true','false') DEFAULT NULL,
  `assesmen_gizi_harian` enum('true','false') DEFAULT NULL,
  `assesmen_gizi_ulang` enum('true','false') DEFAULT NULL,
  `tombol_nota_billing` enum('true','false') DEFAULT NULL,
  `tombol_simpan_hasil_radiologi` enum('true','false') DEFAULT NULL,
  `bpjs_surat_kontrol` enum('true','false') DEFAULT NULL,
  `monev_asuhan_gizi` enum('true','false') DEFAULT NULL,
  `inacbg_klaim_raza` enum('true','false') DEFAULT NULL,
  `pengajuan_klaim_inacbg_raza` enum('true','false') DEFAULT NULL,
  `copy_pemeriksaan_dokter_kepetugas_ralan` enum('true','false') DEFAULT NULL,
  `jkn_belum_diproses_klaim` enum('true','false') DEFAULT NULL,
  `input_kode_icd` enum('true','false') DEFAULT NULL,
  `indikator_mutu_unit` enum('true','false') DEFAULT NULL,
  `kendali_Mutu_kendali_Biaya_INACBG` enum('true','false') DEFAULT NULL,
  `dashboard_eResep` enum('true','false') DEFAULT NULL,
  PRIMARY KEY (`id_user`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

/*Table structure for table `utd_cekal_darah` */

DROP TABLE IF EXISTS `utd_cekal_darah`;

CREATE TABLE `utd_cekal_darah` (
  `no_donor` varchar(15) NOT NULL,
  `tanggal` date DEFAULT NULL,
  `dinas` enum('Pagi','Siang','Sore','Malam') DEFAULT NULL,
  `petugas_pemusnahan` varchar(20) DEFAULT NULL,
  `keterangan` varchar(100) DEFAULT NULL,
  PRIMARY KEY (`no_donor`),
  KEY `petugas_pemusnahan` (`petugas_pemusnahan`),
  CONSTRAINT `utd_cekal_darah_ibfk_1` FOREIGN KEY (`no_donor`) REFERENCES `utd_donor` (`no_donor`) ON DELETE CASCADE ON UPDATE CASCADE,
  CONSTRAINT `utd_cekal_darah_ibfk_2` FOREIGN KEY (`petugas_pemusnahan`) REFERENCES `petugas` (`nip`) ON DELETE CASCADE ON UPDATE CASCADE
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

/*Table structure for table `utd_detail_pemisahan_komponen` */

DROP TABLE IF EXISTS `utd_detail_pemisahan_komponen`;

CREATE TABLE `utd_detail_pemisahan_komponen` (
  `no_donor` varchar(15) DEFAULT NULL,
  `no_kantong` varchar(15) NOT NULL,
  `kode_komponen` varchar(5) DEFAULT NULL,
  `tanggal_kadaluarsa` date DEFAULT NULL,
  PRIMARY KEY (`no_kantong`),
  KEY `no_donor` (`no_donor`),
  CONSTRAINT `utd_detail_pemisahan_komponen_ibfk_1` FOREIGN KEY (`no_donor`) REFERENCES `utd_pemisahan_komponen` (`no_donor`) ON DELETE CASCADE ON UPDATE CASCADE
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

/*Table structure for table `utd_donor` */

DROP TABLE IF EXISTS `utd_donor`;

CREATE TABLE `utd_donor` (
  `no_donor` varchar(15) NOT NULL,
  `nama` varchar(60) DEFAULT NULL,
  `tanggal` date DEFAULT NULL,
  `dinas` enum('Pagi','Siang','Sore','Malam') DEFAULT NULL,
  `jk` enum('L','P') DEFAULT NULL,
  `umur` tinyint(4) DEFAULT NULL,
  `alamat` varchar(100) DEFAULT NULL,
  `golongan_darah` enum('A','AB','B','O') DEFAULT NULL,
  `resus` enum('(-)','(+)') DEFAULT NULL,
  `tensi` varchar(7) DEFAULT NULL,
  `no_bag` int(11) DEFAULT NULL,
  `no_telp` varchar(13) DEFAULT NULL,
  `jenis_bag` enum('SB','DB','TB','QB') DEFAULT NULL,
  `jenis_donor` enum('DB','DP','DS') DEFAULT NULL,
  `tempat_aftap` enum('Dalam Gedung','Luar Gedung') DEFAULT NULL,
  `petugas_aftap` varchar(20) DEFAULT NULL,
  `hbsag` enum('Negatif','Positif') DEFAULT NULL,
  `hcv` enum('Negatif','Positif') DEFAULT NULL,
  `hiv` enum('Negatif','Positif') DEFAULT NULL,
  `spilis` enum('Negatif','Positif') DEFAULT NULL,
  `malaria` enum('Negatif','Positif') DEFAULT NULL,
  `petugas_u_saring` varchar(20) DEFAULT NULL,
  `status` enum('Aman','Cekal') DEFAULT NULL,
  PRIMARY KEY (`no_donor`),
  KEY `petugas_aftap` (`petugas_aftap`),
  KEY `petugas_u_saring` (`petugas_u_saring`),
  KEY `no_selang` (`no_telp`),
  CONSTRAINT `utd_donor_ibfk_1` FOREIGN KEY (`petugas_aftap`) REFERENCES `petugas` (`nip`) ON DELETE CASCADE ON UPDATE CASCADE,
  CONSTRAINT `utd_donor_ibfk_2` FOREIGN KEY (`petugas_u_saring`) REFERENCES `petugas` (`nip`) ON DELETE CASCADE ON UPDATE CASCADE
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

/*Table structure for table `utd_komponen_darah` */

DROP TABLE IF EXISTS `utd_komponen_darah`;

CREATE TABLE `utd_komponen_darah` (
  `kode` varchar(5) NOT NULL,
  `nama` varchar(70) DEFAULT NULL,
  `lama` smallint(6) DEFAULT NULL,
  `jasa_sarana` double DEFAULT NULL,
  `paket_bhp` double DEFAULT NULL,
  `kso` double DEFAULT NULL,
  `manajemen` double DEFAULT NULL,
  `total` double DEFAULT NULL,
  `pembatalan` double DEFAULT NULL,
  PRIMARY KEY (`kode`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

/*Table structure for table `utd_medis_rusak` */

DROP TABLE IF EXISTS `utd_medis_rusak`;

CREATE TABLE `utd_medis_rusak` (
  `kode_brng` varchar(15) NOT NULL DEFAULT '',
  `jml` double DEFAULT NULL,
  `hargabeli` double DEFAULT NULL,
  `total` double DEFAULT NULL,
  `nip` varchar(20) NOT NULL DEFAULT '',
  `tanggal` datetime NOT NULL DEFAULT '0000-00-00 00:00:00',
  `keterangan` varchar(60) DEFAULT NULL,
  PRIMARY KEY (`kode_brng`,`nip`,`tanggal`),
  KEY `nip` (`nip`),
  CONSTRAINT `utd_medis_rusak_ibfk_1` FOREIGN KEY (`kode_brng`) REFERENCES `databarang` (`kode_brng`) ON DELETE CASCADE ON UPDATE CASCADE,
  CONSTRAINT `utd_medis_rusak_ibfk_2` FOREIGN KEY (`nip`) REFERENCES `petugas` (`nip`) ON DELETE CASCADE ON UPDATE CASCADE
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

/*Table structure for table `utd_pemisahan_komponen` */

DROP TABLE IF EXISTS `utd_pemisahan_komponen`;

CREATE TABLE `utd_pemisahan_komponen` (
  `no_donor` varchar(15) NOT NULL,
  `tanggal` date DEFAULT NULL,
  `dinas` enum('Pagi','Siang','Sore','Malam') DEFAULT NULL,
  `nip` varchar(20) DEFAULT NULL,
  PRIMARY KEY (`no_donor`),
  KEY `nip` (`nip`),
  CONSTRAINT `utd_pemisahan_komponen_ibfk_1` FOREIGN KEY (`no_donor`) REFERENCES `utd_donor` (`no_donor`) ON DELETE CASCADE ON UPDATE CASCADE,
  CONSTRAINT `utd_pemisahan_komponen_ibfk_2` FOREIGN KEY (`nip`) REFERENCES `petugas` (`nip`) ON DELETE CASCADE ON UPDATE CASCADE
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

/*Table structure for table `utd_pengambilan_medis` */

DROP TABLE IF EXISTS `utd_pengambilan_medis`;

CREATE TABLE `utd_pengambilan_medis` (
  `kode_brng` varchar(15) NOT NULL DEFAULT '',
  `jml` double DEFAULT NULL,
  `hargabeli` double DEFAULT NULL,
  `total` double DEFAULT NULL,
  `kd_bangsal_dr` char(5) NOT NULL DEFAULT '',
  `tanggal` datetime NOT NULL DEFAULT '0000-00-00 00:00:00',
  `keterangan` varchar(60) DEFAULT NULL,
  PRIMARY KEY (`kode_brng`,`kd_bangsal_dr`,`tanggal`),
  KEY `kd_bangsal_dr` (`kd_bangsal_dr`),
  CONSTRAINT `utd_pengambilan_medis_ibfk_1` FOREIGN KEY (`kode_brng`) REFERENCES `databarang` (`kode_brng`) ON DELETE CASCADE ON UPDATE CASCADE,
  CONSTRAINT `utd_pengambilan_medis_ibfk_2` FOREIGN KEY (`kd_bangsal_dr`) REFERENCES `bangsal` (`kd_bangsal`) ON DELETE CASCADE ON UPDATE CASCADE
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

/*Table structure for table `utd_pengambilan_penunjang` */

DROP TABLE IF EXISTS `utd_pengambilan_penunjang`;

CREATE TABLE `utd_pengambilan_penunjang` (
  `kode_brng` varchar(15) NOT NULL DEFAULT '',
  `jml` double DEFAULT NULL,
  `harga` double DEFAULT NULL,
  `total` double DEFAULT NULL,
  `nip` varchar(20) NOT NULL DEFAULT '',
  `tanggal` datetime NOT NULL DEFAULT '0000-00-00 00:00:00',
  `keterangan` varchar(60) DEFAULT NULL,
  PRIMARY KEY (`kode_brng`,`nip`,`tanggal`),
  KEY `kode_brng` (`kode_brng`),
  KEY `nip` (`nip`),
  CONSTRAINT `utd_pengambilan_penunjang_ibfk_1` FOREIGN KEY (`kode_brng`) REFERENCES `ipsrsbarang` (`kode_brng`) ON DELETE CASCADE ON UPDATE CASCADE,
  CONSTRAINT `utd_pengambilan_penunjang_ibfk_2` FOREIGN KEY (`nip`) REFERENCES `petugas` (`nip`) ON DELETE CASCADE ON UPDATE CASCADE
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

/*Table structure for table `utd_penggunaan_medis_donor` */

DROP TABLE IF EXISTS `utd_penggunaan_medis_donor`;

CREATE TABLE `utd_penggunaan_medis_donor` (
  `no_donor` varchar(15) NOT NULL DEFAULT '',
  `kode_brng` varchar(15) NOT NULL DEFAULT '',
  `jml` double DEFAULT NULL,
  `harga` double DEFAULT NULL,
  `total` double DEFAULT NULL,
  PRIMARY KEY (`no_donor`,`kode_brng`),
  KEY `kode_brng` (`kode_brng`),
  CONSTRAINT `utd_penggunaan_medis_donor_ibfk_1` FOREIGN KEY (`no_donor`) REFERENCES `utd_donor` (`no_donor`) ON DELETE CASCADE ON UPDATE CASCADE,
  CONSTRAINT `utd_penggunaan_medis_donor_ibfk_2` FOREIGN KEY (`kode_brng`) REFERENCES `databarang` (`kode_brng`) ON DELETE CASCADE ON UPDATE CASCADE
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

/*Table structure for table `utd_penggunaan_medis_pemisahan_komponen` */

DROP TABLE IF EXISTS `utd_penggunaan_medis_pemisahan_komponen`;

CREATE TABLE `utd_penggunaan_medis_pemisahan_komponen` (
  `no_donor` varchar(15) NOT NULL DEFAULT '',
  `kode_brng` varchar(15) NOT NULL DEFAULT '',
  `jml` double DEFAULT NULL,
  `harga` double DEFAULT NULL,
  `total` double DEFAULT NULL,
  PRIMARY KEY (`no_donor`,`kode_brng`),
  KEY `kode_brng` (`kode_brng`),
  CONSTRAINT `utd_penggunaan_medis_pemisahan_komponen_ibfk_2` FOREIGN KEY (`kode_brng`) REFERENCES `databarang` (`kode_brng`) ON DELETE CASCADE ON UPDATE CASCADE,
  CONSTRAINT `utd_penggunaan_medis_pemisahan_komponen_ibfk_3` FOREIGN KEY (`no_donor`) REFERENCES `utd_pemisahan_komponen` (`no_donor`) ON DELETE CASCADE ON UPDATE CASCADE
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

/*Table structure for table `utd_penggunaan_medis_penyerahan_darah` */

DROP TABLE IF EXISTS `utd_penggunaan_medis_penyerahan_darah`;

CREATE TABLE `utd_penggunaan_medis_penyerahan_darah` (
  `no_penyerahan` varchar(17) NOT NULL,
  `kode_brng` varchar(15) NOT NULL DEFAULT '',
  `jml` double DEFAULT NULL,
  `harga` double DEFAULT NULL,
  `total` double DEFAULT NULL,
  PRIMARY KEY (`no_penyerahan`,`kode_brng`),
  KEY `kode_brng` (`kode_brng`),
  CONSTRAINT `utd_penggunaan_medis_penyerahan_darah_ibfk_1` FOREIGN KEY (`kode_brng`) REFERENCES `databarang` (`kode_brng`) ON DELETE CASCADE ON UPDATE CASCADE,
  CONSTRAINT `utd_penggunaan_medis_penyerahan_darah_ibfk_2` FOREIGN KEY (`no_penyerahan`) REFERENCES `utd_penyerahan_darah` (`no_penyerahan`) ON DELETE CASCADE ON UPDATE CASCADE
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

/*Table structure for table `utd_penggunaan_penunjang_donor` */

DROP TABLE IF EXISTS `utd_penggunaan_penunjang_donor`;

CREATE TABLE `utd_penggunaan_penunjang_donor` (
  `no_donor` varchar(15) NOT NULL DEFAULT '',
  `kode_brng` varchar(15) NOT NULL DEFAULT '',
  `jml` double DEFAULT NULL,
  `harga` double DEFAULT NULL,
  `total` double DEFAULT NULL,
  PRIMARY KEY (`no_donor`,`kode_brng`),
  KEY `kode_brng` (`kode_brng`),
  CONSTRAINT `utd_penggunaan_penunjang_donor_ibfk_1` FOREIGN KEY (`no_donor`) REFERENCES `utd_donor` (`no_donor`) ON DELETE CASCADE ON UPDATE CASCADE,
  CONSTRAINT `utd_penggunaan_penunjang_donor_ibfk_2` FOREIGN KEY (`kode_brng`) REFERENCES `ipsrsbarang` (`kode_brng`) ON DELETE CASCADE ON UPDATE CASCADE
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

/*Table structure for table `utd_penggunaan_penunjang_pemisahan_komponen` */

DROP TABLE IF EXISTS `utd_penggunaan_penunjang_pemisahan_komponen`;

CREATE TABLE `utd_penggunaan_penunjang_pemisahan_komponen` (
  `no_donor` varchar(15) NOT NULL DEFAULT '',
  `kode_brng` varchar(15) NOT NULL DEFAULT '',
  `jml` double DEFAULT NULL,
  `harga` double DEFAULT NULL,
  `total` double DEFAULT NULL,
  PRIMARY KEY (`no_donor`,`kode_brng`),
  KEY `kode_brng` (`kode_brng`),
  CONSTRAINT `utd_penggunaan_penunjang_pemisahan_komponen_ibfk_2` FOREIGN KEY (`kode_brng`) REFERENCES `ipsrsbarang` (`kode_brng`) ON DELETE CASCADE ON UPDATE CASCADE,
  CONSTRAINT `utd_penggunaan_penunjang_pemisahan_komponen_ibfk_3` FOREIGN KEY (`no_donor`) REFERENCES `utd_pemisahan_komponen` (`no_donor`) ON DELETE CASCADE ON UPDATE CASCADE
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

/*Table structure for table `utd_penggunaan_penunjang_penyerahan_darah` */

DROP TABLE IF EXISTS `utd_penggunaan_penunjang_penyerahan_darah`;

CREATE TABLE `utd_penggunaan_penunjang_penyerahan_darah` (
  `no_penyerahan` varchar(17) NOT NULL DEFAULT '',
  `kode_brng` varchar(15) NOT NULL DEFAULT '',
  `jml` double DEFAULT NULL,
  `harga` double DEFAULT NULL,
  `total` double DEFAULT NULL,
  PRIMARY KEY (`no_penyerahan`,`kode_brng`),
  KEY `kode_brng` (`kode_brng`),
  CONSTRAINT `utd_penggunaan_penunjang_penyerahan_darah_ibfk_1` FOREIGN KEY (`kode_brng`) REFERENCES `ipsrsbarang` (`kode_brng`) ON DELETE CASCADE ON UPDATE CASCADE,
  CONSTRAINT `utd_penggunaan_penunjang_penyerahan_darah_ibfk_2` FOREIGN KEY (`no_penyerahan`) REFERENCES `utd_penyerahan_darah` (`no_penyerahan`) ON DELETE CASCADE ON UPDATE CASCADE
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

/*Table structure for table `utd_penunjang_rusak` */

DROP TABLE IF EXISTS `utd_penunjang_rusak`;

CREATE TABLE `utd_penunjang_rusak` (
  `kode_brng` varchar(15) NOT NULL DEFAULT '',
  `jml` double DEFAULT NULL,
  `harga` double DEFAULT NULL,
  `total` double DEFAULT NULL,
  `nip` varchar(20) NOT NULL DEFAULT '',
  `tanggal` datetime NOT NULL DEFAULT '0000-00-00 00:00:00',
  `keterangan` varchar(60) DEFAULT NULL,
  PRIMARY KEY (`kode_brng`,`nip`,`tanggal`),
  KEY `nip` (`nip`),
  CONSTRAINT `utd_penunjang_rusak_ibfk_1` FOREIGN KEY (`kode_brng`) REFERENCES `ipsrsbarang` (`kode_brng`) ON DELETE CASCADE ON UPDATE CASCADE,
  CONSTRAINT `utd_penunjang_rusak_ibfk_2` FOREIGN KEY (`nip`) REFERENCES `petugas` (`nip`) ON DELETE CASCADE ON UPDATE CASCADE
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

/*Table structure for table `utd_penyerahan_darah` */

DROP TABLE IF EXISTS `utd_penyerahan_darah`;

CREATE TABLE `utd_penyerahan_darah` (
  `no_penyerahan` varchar(17) NOT NULL DEFAULT '',
  `tanggal` date DEFAULT NULL,
  `dinas` enum('Pagi','Siang','Sore','Malam') DEFAULT NULL,
  `nip_cross` varchar(20) DEFAULT NULL,
  `keterangan` varchar(40) DEFAULT NULL,
  `status` enum('Belum Dibayar','Sudah Dibayar') DEFAULT NULL,
  `kd_rek` varchar(15) DEFAULT NULL,
  `pengambil_darah` varchar(70) DEFAULT NULL,
  `alamat_pengambil_darah` varchar(120) DEFAULT NULL,
  `nip_pj` varchar(20) DEFAULT NULL,
  `besarppn` double DEFAULT NULL,
  PRIMARY KEY (`no_penyerahan`),
  KEY `nip` (`nip_cross`),
  KEY `kd_rek` (`kd_rek`),
  CONSTRAINT `utd_penyerahan_darah_ibfk_1` FOREIGN KEY (`nip_cross`) REFERENCES `petugas` (`nip`) ON DELETE CASCADE ON UPDATE CASCADE,
  CONSTRAINT `utd_penyerahan_darah_ibfk_2` FOREIGN KEY (`kd_rek`) REFERENCES `rekening` (`kd_rek`) ON DELETE CASCADE ON UPDATE CASCADE
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

/*Table structure for table `utd_penyerahan_darah_detail` */

DROP TABLE IF EXISTS `utd_penyerahan_darah_detail`;

CREATE TABLE `utd_penyerahan_darah_detail` (
  `no_penyerahan` varchar(17) NOT NULL DEFAULT '',
  `no_kantong` varchar(20) NOT NULL DEFAULT '',
  `jasa_sarana` double DEFAULT NULL,
  `paket_bhp` double DEFAULT NULL,
  `kso` double DEFAULT NULL,
  `manajemen` double DEFAULT NULL,
  `total` double DEFAULT NULL,
  PRIMARY KEY (`no_penyerahan`,`no_kantong`),
  KEY `no_kantong` (`no_kantong`),
  CONSTRAINT `utd_penyerahan_darah_detail_ibfk_1` FOREIGN KEY (`no_penyerahan`) REFERENCES `utd_penyerahan_darah` (`no_penyerahan`) ON DELETE CASCADE ON UPDATE CASCADE,
  CONSTRAINT `utd_penyerahan_darah_detail_ibfk_2` FOREIGN KEY (`no_kantong`) REFERENCES `utd_stok_darah` (`no_kantong`) ON DELETE CASCADE ON UPDATE CASCADE
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

/*Table structure for table `utd_stok_darah` */

DROP TABLE IF EXISTS `utd_stok_darah`;

CREATE TABLE `utd_stok_darah` (
  `no_kantong` varchar(20) NOT NULL DEFAULT '',
  `kode_komponen` varchar(5) DEFAULT NULL,
  `golongan_darah` enum('A','AB','B','O') DEFAULT NULL,
  `resus` enum('(-)','(+)') DEFAULT NULL,
  `tanggal_aftap` date DEFAULT NULL,
  `tanggal_kadaluarsa` date DEFAULT NULL,
  `asal_darah` enum('Hibah','Beli','Produksi Sendiri') DEFAULT NULL,
  `status` enum('Ada','Diambil','Dimusnahkan') DEFAULT NULL,
  PRIMARY KEY (`no_kantong`),
  KEY `kode_komponen` (`kode_komponen`),
  CONSTRAINT `utd_stok_darah_ibfk_1` FOREIGN KEY (`kode_komponen`) REFERENCES `utd_komponen_darah` (`kode`) ON UPDATE CASCADE
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

/*Table structure for table `utd_stok_medis` */

DROP TABLE IF EXISTS `utd_stok_medis`;

CREATE TABLE `utd_stok_medis` (
  `kode_brng` varchar(15) NOT NULL DEFAULT '',
  `stok` double DEFAULT NULL,
  `hargaterakhir` double DEFAULT NULL,
  PRIMARY KEY (`kode_brng`),
  CONSTRAINT `utd_stok_medis_ibfk_1` FOREIGN KEY (`kode_brng`) REFERENCES `databarang` (`kode_brng`) ON DELETE CASCADE ON UPDATE CASCADE
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

/*Table structure for table `utd_stok_penunjang` */

DROP TABLE IF EXISTS `utd_stok_penunjang`;

CREATE TABLE `utd_stok_penunjang` (
  `kode_brng` varchar(15) NOT NULL DEFAULT '',
  `stok` double DEFAULT NULL,
  `hargaterakhir` double DEFAULT NULL,
  PRIMARY KEY (`kode_brng`),
  CONSTRAINT `utd_stok_penunjang_ibfk_1` FOREIGN KEY (`kode_brng`) REFERENCES `ipsrsbarang` (`kode_brng`) ON DELETE CASCADE ON UPDATE CASCADE
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;
