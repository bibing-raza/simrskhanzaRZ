/*
  Dilarang keras menggandakan/mengcopy/menyebarkan/membajak/mendecompile 
  Software ini dalam bentuk apapun tanpa seijin pembuat software
  (Khanza.Soft Media). Bagi yang sengaja membajak softaware ini ta
  npa ijin, kami sumpahi sial 1000 turunan, miskin sampai 500 turu
  nan. Selalu mendapat kecelakaan sampai 400 turunan. Anak pertama
  nya cacat tidak punya kaki sampai 300 turunan. Susah cari jodoh
  sampai umur 50 tahun sampai 200 turunan. Ya Alloh maafkan kami 
  karena telah berdoa buruk, semua ini kami lakukan karena kami ti
  dak pernah rela karya kami dibajak tanpa ijin.
 */
package simrskhanza;

import inventory.DlgResepObat;
import laporan.DlgDataHAIs;
import bridging.BPJSDataSEP;
import bridging.BPJSSuratKontrol;
import bridging.CoronaPasien;
import bridging.DlgSKDPBPJS;
import bridging.INACBGPerawatanCorona;
import bridging.InhealthDataSJP;
import bridging.SisruteRujukanKeluar;
import laporan.DlgDiagnosaPenyakit;
import informasi.InformasiAnalisaKamin;
import keuangan.DlgKamar;
import fungsi.WarnaTable;
import fungsi.batasInput;
import fungsi.koneksiDB;
import fungsi.sekuel;
import fungsi.validasi;
import fungsi.var;
import inventory.DlgInputStokPasien;
import inventory.DlgReturJual;
import java.awt.Cursor;
import java.awt.Dimension;
import java.awt.event.ActionEvent;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.event.WindowEvent;
import java.awt.event.WindowListener;
import static java.awt.image.ImageObserver.WIDTH;
import java.io.FileInputStream;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.DateFormat;
import java.text.DecimalFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import java.util.Properties;
import javax.swing.JOptionPane;
import javax.swing.JTable;
import javax.swing.event.DocumentEvent;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableColumn;
import keuangan.DlgBilingRanap;
import keuangan.DlgLhtPiutang;
import laporan.DlgDataDietRanap;
import laporan.DlgKaruRanap;
import rekammedis.DlgAssesmenGiziHarian;
import rekammedis.DlgAssesmenGiziUlang;
import rekammedis.DlgMonevAsuhanGizi;
import simrskhanza.DlgInputPonek;
import simrskhanza.DlgRingkasanPulangRanap;

/**
 *
 * @author perpustakaan
 */
public class DlgKamarInap extends javax.swing.JDialog {

    private final DefaultTableModel tabMode, tabMode3, tabMode5, tabMode6;
    private Connection koneksi = koneksiDB.condb();
    private sekuel Sequel = new sekuel();
    private validasi Valid = new validasi();
    private final Properties prop = new Properties();
    public DlgIKBBayi ikb = new DlgIKBBayi(null, false);
    public DlgKamar kamar = new DlgKamar(null, false);
    public DlgReg reg = new DlgReg(null, false);
    public DlgBilingRanap billing = new DlgBilingRanap(null, false);
    public DlgRujukMasuk rujukmasuk = new DlgRujukMasuk(null, false);
    public DlgRingkasanPulangRanap ringkasan = new DlgRingkasanPulangRanap(null, false);
    public DlgPasienPersalinan persalinan = new DlgPasienPersalinan(null, false);
    public DlgDiagnosaPenyakit diagnosa = new DlgDiagnosaPenyakit(null, false);
    public DlgKaruRanap karu = new DlgKaruRanap(null, false);
    private SimpleDateFormat dateformat = new SimpleDateFormat("yyyy/MM/dd");
    private DateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
    private DateFormat dateFormat2 = new SimpleDateFormat("yyyy-MM-dd");
    private Date date = new Date();
    private Date date2 = new Date(), timeIn, timeOut, dateIn, dateOut;
    private String now = dateFormat.format(date), kmr = "", key = "", key2 = "", tglmasuk, jammasuk, kd_pj,
            hariawal = Sequel.cariIsi("select hariawal from set_jam_minimal"), pilihancetak = "",
            nonota = "", kdDiag = "", tgm = "", key3 = "", host = "";
    private PreparedStatement ps, pscaripiutang, psdiagnosa, psibu, psanak, pstarif, psdpjp, psDok, psPet, psDP,
            pscariumur, pspersalinan, pspersalinan1, psGZburuk, pssep, psAPS, psFar, psRad, psLab, psKos;
    private ResultSet rs, rs2, rs3, rs4, rs6, rssep, rsAPS, rsFar, rsRad, rsLab, rsKos, rsDok, rsPet, rsDP;
    private int i, x, sudah = 0, cekAda = 0, row = 0, cekDb = 0, cekKamar = 0, cekOperasi = 0, cekUsia = 0, cekRuang = 0,
            cekPr = 0, cekDr = 0, cekDrPr = 0, cekTinPers = 0, cekKamar2 = 0, cekOperasi2 = 0, diagnosa_cek = 0,
            g = 0, gb = 0, cekBonGZ = 0, cekjampersal = 0, cekjamkesda = 0, cekPXbpjs = 0;
    private double lama = Sequel.cariIsiAngka("select lamajam from set_jam_minimal"), persenbayi = Sequel.cariInteger("select bayi from set_jam_minimal");
    private String dokterranap = "", bangsal = "", diagnosa_akhir = Sequel.cariIsi("select diagnosaakhir from set_jam_minimal"), cekKelamin = "",
            namakamar = "", umur = "0", sttsumur = "Th", cekAPS = "", norawatAPS = "", cekdokter = "", kdAPS = "", diagnosa_ok = "",
            cekDataPersalinan = "", cekRMbayi = "", kamarCovid = "", cekHR = "", nmgedung = "";

    /**
     * Creates new form DlgKamarInap
     *
     * @param parent
     * @param modal
     */
    public DlgKamarInap(java.awt.Frame parent, boolean modal) {
        super(parent, modal);
        initComponents();
        this.setLocation(8, 1);
        setSize(628, 674);

        Object[] row = {"No. Rawat", "Nomer RM", "Nama Pasien", "Tgl. Lahir", "Alamat Pasien", "Jenis Bayar",
            "Cetak SEP", "Kode Kamar", "Ruang Rawat Inap", "Tarif Kamar",
            "Diagnosa Awal", "Diagnosa Akhir", "Tgl. Masuk", "Jam Masuk", "Tgl. Keluar", "Jam Keluar",
            "Ttl. Biaya", "Stts. Pulang", "Lama", "Dokter P.J.", "No. Telpon/HP", "dokterDPJP"};
        tabMode = new DefaultTableModel(null, row) {
            @Override
            public boolean isCellEditable(int rowIndex, int colIndex) {
                return false;
            }
        };
        tbKamIn.setModel(tabMode);

        //tbObat.setDefaultRenderer(Object.class, new WarnaTable(panelJudul.getBackground(),tbObat.getBackground()));
        tbKamIn.setPreferredScrollableViewportSize(new Dimension(500, 500));
        tbKamIn.setAutoResizeMode(JTable.AUTO_RESIZE_OFF);

        for (i = 0; i < 22; i++) {
            TableColumn column = tbKamIn.getColumnModel().getColumn(i);
            if (i == 0) {
                column.setPreferredWidth(110);
            } else if (i == 1) {
                column.setPreferredWidth(70);
            } else if (i == 2) {
                column.setPreferredWidth(200);
            } else if (i == 3) {
                column.setPreferredWidth(75);
            } else if (i == 4) {
                column.setPreferredWidth(250);
            } else if (i == 5) {
                column.setPreferredWidth(110);
            } else if (i == 6) {
                column.setPreferredWidth(60);
            } else if (i == 7) {
                column.setPreferredWidth(100);
            } else if (i == 8) {
                column.setPreferredWidth(200);
            } else if (i == 9) {
                column.setPreferredWidth(90);
            } else if (i == 10) {
                column.setPreferredWidth(200);
            } else if (i == 11) {
                column.setPreferredWidth(100);
            } else if (i == 12) {
                column.setPreferredWidth(75);
            } else if (i == 13) {
                column.setPreferredWidth(74);
            } else if (i == 14) {
                column.setPreferredWidth(75);
            } else if (i == 15) {
                column.setPreferredWidth(75);
            } else if (i == 16) {
                column.setPreferredWidth(90);
            } else if (i == 17) {
                column.setPreferredWidth(130);
            } else if (i == 18) {
                column.setPreferredWidth(40);
            } else if (i == 19) {
                column.setPreferredWidth(200);
            } else if (i == 20) {
                column.setPreferredWidth(100);
            } else if (i == 21) {
                column.setMinWidth(0);
                column.setMaxWidth(0);
            }
        }
        tbKamIn.setDefaultRenderer(Object.class, new WarnaTable());

        tabMode3 = new DefaultTableModel(null, new Object[]{"No.", "No. Rawat", "No. RM", "Nama Pasien", "Alamat",
            "Umur", "Tgl. Input", "Diagnosa Awal", "BB-Awal", "BB-Akhir",
            "PB/TB", "BB/U", "BB/PB", "PB/U", "Perhitng. Keb. Zat Gizi",
            "Diagn. dr. Sp.GZ/Ahli GZ", "Pemberian Nutrisi", "Lab. Albumin", "Lab. Hb", "Lab. Leukosit",
            "Lab. PLT", "Puskesmas Asal"}) {
            @Override
            public boolean isCellEditable(int rowIndex, int colIndex) {
                return false;
            }
        };
        tbGiziBuruk.setModel(tabMode3);

        tbGiziBuruk.setPreferredScrollableViewportSize(new Dimension(500, 500));
        tbGiziBuruk.setAutoResizeMode(JTable.AUTO_RESIZE_OFF);

        for (int i = 0; i < 22; i++) {
            TableColumn column = tbGiziBuruk.getColumnModel().getColumn(i);
            if (i == 0) {
                column.setPreferredWidth(30);
            } else if (i == 1) {
                column.setPreferredWidth(110);
            } else if (i == 2) {
                column.setPreferredWidth(50);
            } else if (i == 3) {
                column.setPreferredWidth(170);
            } else if (i == 4) {
                column.setPreferredWidth(250);
            } else if (i == 5) {
                column.setPreferredWidth(45);
            } else if (i == 6) {
                column.setPreferredWidth(70);
            } else if (i == 7) {
                column.setPreferredWidth(200);
            } else if (i == 8) {
                column.setPreferredWidth(50);
            } else if (i == 9) {
                column.setPreferredWidth(50);
            } else if (i == 10) {
                column.setPreferredWidth(50);
            } else if (i == 11) {
                column.setPreferredWidth(50);
            } else if (i == 12) {
                column.setPreferredWidth(50);
            } else if (i == 13) {
                column.setPreferredWidth(50);
            } else if (i == 14) {
                column.setPreferredWidth(250);
            } else if (i == 15) {
                column.setPreferredWidth(250);
            } else if (i == 16) {
                column.setPreferredWidth(250);
            } else if (i == 17) {
                column.setPreferredWidth(130);
            } else if (i == 18) {
                column.setPreferredWidth(130);
            } else if (i == 19) {
                column.setPreferredWidth(130);
            } else if (i == 20) {
                column.setPreferredWidth(130);
            } else if (i == 21) {
                column.setPreferredWidth(200);
            }
        }
        tbGiziBuruk.setDefaultRenderer(Object.class, new WarnaTable());
        
        tabMode5 = new DefaultTableModel(null, new Object[]{"Kode Alasan", "Jenis Alasan APS"}) {
            @Override
            public boolean isCellEditable(int rowIndex, int colIndex) {
                return false;
            }
        };
        tbAPS.setModel(tabMode5);
        tbAPS.setPreferredScrollableViewportSize(new Dimension(500, 500));
        tbAPS.setAutoResizeMode(JTable.AUTO_RESIZE_OFF);

        for (int i = 0; i < 2; i++) {
            TableColumn column = tbAPS.getColumnModel().getColumn(i);
            if (i == 0) {
                column.setPreferredWidth(75);
            } else if (i == 1) {
                column.setPreferredWidth(320);
            }
        }
        tbAPS.setDefaultRenderer(Object.class, new WarnaTable());

        tabMode6 = new DefaultTableModel(null, new Object[]{"No. Rawat", "Tgl. Transaksi", "Jam Transaksi", "Uraian Transaksi"}) {
            @Override
            public boolean isCellEditable(int rowIndex, int colIndex) {
                return false;
            }
        };
        
        tbDataTran.setModel(tabMode6);
        tbDataTran.setPreferredScrollableViewportSize(new Dimension(500, 500));
        tbDataTran.setAutoResizeMode(JTable.AUTO_RESIZE_OFF);

        for (int i = 0; i < 4; i++) {
            TableColumn column = tbDataTran.getColumnModel().getColumn(i);
            if (i == 0) {
                column.setPreferredWidth(110);
            } else if (i == 1) {
                column.setPreferredWidth(75);
            } else if (i == 2) {
                column.setPreferredWidth(75);
            } else if (i == 3) {
                column.setPreferredWidth(250);
            }
        }
        tbDataTran.setDefaultRenderer(Object.class, new WarnaTable());

        TNoRwTujuan.setDocument(new batasInput((byte) 17).getKata(TNoRwTujuan));
        tglDari.setDocument(new batasInput((byte) 10).getKata(tglDari));
        pukulDari.setDocument(new batasInput((byte) 8).getKata(pukulDari));
        norawat.setDocument(new batasInput((byte) 17).getKata(norawat));
        kdkamar.setDocument(new batasInput((byte) 15).getKata(kdkamar));
        kdkamarpindah.setDocument(new batasInput((byte) 15).getKata(kdkamarpindah));
        ttlbiaya.setDocument(new batasInput((byte) 25).getKata(ttlbiaya));
        diagnosaawal.setDocument(new batasInput((byte) 100).getKata(diagnosaawal));
        TDiagnosaAwal.setDocument(new batasInput((byte) 100).getKata(TDiagnosaAwal));
        diagnosaakhir.setDocument(new batasInput((byte) 100).getKata(diagnosaakhir));
        TCari.setDocument(new batasInput((byte) 100).getKata(TCari));

        if (koneksiDB.cariCepat().equals("aktif")) {
            TCari.getDocument().addDocumentListener(new javax.swing.event.DocumentListener() {
                @Override
                public void insertUpdate(DocumentEvent e) {
                    tampil();
                }

                @Override
                public void removeUpdate(DocumentEvent e) {
                    tampil();
                }

                @Override
                public void changedUpdate(DocumentEvent e) {
                    tampil();
                }
            });
        }

        TJmlHari.getDocument().addDocumentListener(new javax.swing.event.DocumentListener() {
            @Override
            public void insertUpdate(DocumentEvent e) {
                isjml();
            }

            @Override
            public void removeUpdate(DocumentEvent e) {
                isjml();
            }

            @Override
            public void changedUpdate(DocumentEvent e) {
                isjml();
            }
        });

        rujukmasuk.WindowPerujuk.addWindowListener(new WindowListener() {
            @Override
            public void windowOpened(WindowEvent e) {
            }

            @Override
            public void windowClosing(WindowEvent e) {
            }

            @Override
            public void windowClosed(WindowEvent e) {
                if (var.getform().equals("DlgKamarInap")) {
                    if (rujukmasuk.tbPerujuk.getSelectedRow() != -1) {
                        asalFaskes.setText(rujukmasuk.tbPerujuk.getValueAt(rujukmasuk.tbPerujuk.getSelectedRow(), 2).toString());
                    }
                }
            }

            @Override
            public void windowIconified(WindowEvent e) {
            }

            @Override
            public void windowDeiconified(WindowEvent e) {
            }

            @Override
            public void windowActivated(WindowEvent e) {
            }

            @Override
            public void windowDeactivated(WindowEvent e) {
            }
        });

        TTarif.getDocument().addDocumentListener(new javax.swing.event.DocumentListener() {
            @Override
            public void insertUpdate(DocumentEvent e) {
                isjml();
            }

            @Override
            public void removeUpdate(DocumentEvent e) {
                isjml();
            }

            @Override
            public void changedUpdate(DocumentEvent e) {
                isjml();
            }
        });

        TTarifpindah.getDocument().addDocumentListener(new javax.swing.event.DocumentListener() {
            @Override
            public void insertUpdate(DocumentEvent e) {
                isjml();
            }

            @Override
            public void removeUpdate(DocumentEvent e) {
                isjml();
            }

            @Override
            public void changedUpdate(DocumentEvent e) {
                isjml();
            }
        });

        Valid.LoadTahun(CmbTahun);
        Valid.LoadTahun(CmbTahunpindah);

        WindowInputKamar.setSize(675, 320);
        WindowInputKamar.setLocationRelativeTo(null);
        WindowPindahKamar.setSize(675, 285);
        WindowCaraBayar.setSize(630, 80);
        WindowRanapGabung.setSize(630, 120);
        WindowStatusPulang.setSize(474, 81);
        WindowDiagnosaAwal.setSize(772, 82);        

        CmbTahun.setSelectedItem(now.substring(0, 4));
        CmbBln.setSelectedItem(now.substring(5, 7));
        CmbTgl.setSelectedItem(now.substring(8, 10));
        cmbJam.setSelectedItem(now.substring(11, 13));
        cmbMnt.setSelectedItem(now.substring(14, 16));
        cmbDtk.setSelectedItem(now.substring(17, 19));

        reg.pasien.addWindowListener(new WindowListener() {
            @Override
            public void windowOpened(WindowEvent e) {
            }

            @Override
            public void windowClosing(WindowEvent e) {
            }

            @Override
            public void windowClosed(WindowEvent e) {
                if (var.getform().equals("DlgKamarInap")) {
                    if (reg.pasien.getTable().getSelectedRow() != -1) {
                        NoRmBayi.setText(reg.pasien.getTable().getValueAt(reg.pasien.getTable().getSelectedRow(), 1).toString());
                        NmBayi.setText(reg.pasien.getTable().getValueAt(reg.pasien.getTable().getSelectedRow(), 2).toString());
                    }
                    NoRmBayi.requestFocus();
                }
            }

            @Override
            public void windowIconified(WindowEvent e) {
            }

            @Override
            public void windowDeiconified(WindowEvent e) {
            }

            @Override
            public void windowActivated(WindowEvent e) {
            }

            @Override
            public void windowDeactivated(WindowEvent e) {
            }
        });

        reg.pasien.getTable().addKeyListener(new KeyListener() {
            @Override
            public void keyTyped(KeyEvent e) {
            }

            @Override
            public void keyPressed(KeyEvent e) {
                if (var.getform().equals("DlgKamarInap")) {
                    if (e.getKeyCode() == KeyEvent.VK_SPACE) {
                        reg.pasien.dispose();
                    }
                }
            }

            @Override
            public void keyReleased(KeyEvent e) {
            }
        });

        ikb.addWindowListener(new WindowListener() {
            @Override
            public void windowOpened(WindowEvent e) {
            }

            @Override
            public void windowClosing(WindowEvent e) {
            }

            @Override
            public void windowClosed(WindowEvent e) {
                if (var.getform().equals("DlgKamarInap")) {
                    if (ikb.getTable().getSelectedRow() != -1) {
                        NoRmBayi.setText(ikb.getTable().getValueAt(ikb.getTable().getSelectedRow(), 0).toString());
                        NmBayi.setText(ikb.getTable().getValueAt(ikb.getTable().getSelectedRow(), 1).toString());
                    }
                    NoRmBayi.requestFocus();
                }
            }

            @Override
            public void windowIconified(WindowEvent e) {
            }

            @Override
            public void windowDeiconified(WindowEvent e) {
            }

            @Override
            public void windowActivated(WindowEvent e) {
            }

            @Override
            public void windowDeactivated(WindowEvent e) {
            }
        });

        ikb.getTable().addKeyListener(new KeyListener() {
            @Override
            public void keyTyped(KeyEvent e) {
            }

            @Override
            public void keyPressed(KeyEvent e) {
                if (var.getform().equals("DlgKamarInap")) {
                    if (e.getKeyCode() == KeyEvent.VK_SPACE) {
                        ikb.dispose();
                    }
                }
            }

            @Override
            public void keyReleased(KeyEvent e) {
            }
        });

        kamar.addWindowListener(new WindowListener() {
            @Override
            public void windowOpened(WindowEvent e) {
            }

            @Override
            public void windowClosing(WindowEvent e) {
            }

            @Override
            public void windowClosed(WindowEvent e) {
                if (var.getform().equals("DlgKamarInap")) {
                    if (kamar.getTable().getSelectedRow() != -1) {
                        kdkamar.setText(kamar.getTable().getValueAt(kamar.getTable().getSelectedRow(), 1).toString());
                        kdkamarpindah.setText(kamar.getTable().getValueAt(kamar.getTable().getSelectedRow(), 1).toString());
                        isKmr();
                        if ((WindowInputKamar.isVisible() == true) && (!TBangsal.getText().equals("")) && (!norawat.getText().equals(""))) {
                            if (TIn.getText().equals("")) {
                                tglmasuk = CmbTahun.getSelectedItem() + "-" + CmbBln.getSelectedItem() + "-" + CmbTgl.getSelectedItem();
                                jammasuk = cmbJam.getSelectedItem() + ":" + cmbMnt.getSelectedItem() + ":" + cmbDtk.getSelectedItem();
                            } else {
                                tglmasuk = TIn.getText();
                                jammasuk = JamMasuk.getText();
                            }
                            if (hariawal.equals("Yes")) {
                                Sequel.cariIsi("select (if(to_days('" + CmbTahun.getSelectedItem() + "-" + CmbBln.getSelectedItem() + "-" + CmbTgl.getSelectedItem() + " " + cmbJam.getSelectedItem() + ":" + cmbMnt.getSelectedItem() + ":" + cmbDtk.getSelectedItem() + "')-to_days('" + tglmasuk + " " + jammasuk + "')=0,if(time_to_sec('" + CmbTahun.getSelectedItem() + "-" + CmbBln.getSelectedItem() + "-" + CmbTgl.getSelectedItem() + " " + cmbJam.getSelectedItem() + ":" + cmbMnt.getSelectedItem() + ":" + cmbDtk.getSelectedItem() + "')-time_to_sec('" + tglmasuk + " " + jammasuk + "')>(3600*" + lama + "),1,0),to_days('" + CmbTahun.getSelectedItem() + "-" + CmbBln.getSelectedItem() + "-" + CmbTgl.getSelectedItem() + " " + cmbJam.getSelectedItem() + ":" + cmbMnt.getSelectedItem() + ":" + cmbDtk.getSelectedItem() + "')-to_days('" + tglmasuk + " " + jammasuk + "'))+1) as lama", TJmlHari);
                            } else {
                                Sequel.cariIsi("select if(to_days('" + CmbTahun.getSelectedItem() + "-" + CmbBln.getSelectedItem() + "-" + CmbTgl.getSelectedItem() + " " + cmbJam.getSelectedItem() + ":" + cmbMnt.getSelectedItem() + ":" + cmbDtk.getSelectedItem() + "')-to_days('" + tglmasuk + " " + jammasuk + "')=0,if(time_to_sec('" + CmbTahun.getSelectedItem() + "-" + CmbBln.getSelectedItem() + "-" + CmbTgl.getSelectedItem() + " " + cmbJam.getSelectedItem() + ":" + cmbMnt.getSelectedItem() + ":" + cmbDtk.getSelectedItem() + "')-time_to_sec('" + tglmasuk + " " + jammasuk + "')>(3600*" + lama + "),1,0),to_days('" + CmbTahun.getSelectedItem() + "-" + CmbBln.getSelectedItem() + "-" + CmbTgl.getSelectedItem() + " " + cmbJam.getSelectedItem() + ":" + cmbMnt.getSelectedItem() + ":" + cmbDtk.getSelectedItem() + "')-to_days('" + tglmasuk + " " + jammasuk + "')) as lama", TJmlHari);
                            }
                        }
                    }
                    kdkamar.requestFocus();
                }
            }

            @Override
            public void windowIconified(WindowEvent e) {
            }

            @Override
            public void windowDeiconified(WindowEvent e) {
            }

            @Override
            public void windowActivated(WindowEvent e) {
            }

            @Override
            public void windowDeactivated(WindowEvent e) {
            }
        });

        diagnosa.addWindowListener(new WindowListener() {
            @Override
            public void windowOpened(WindowEvent e) {
            }

            @Override
            public void windowClosing(WindowEvent e) {
            }

            @Override
            public void windowClosed(WindowEvent e) {
                if (var.getform().equals("DlgKamarInap")) {
                    try {
                        key = "";
                        psdiagnosa = koneksi.prepareStatement("select kd_penyakit from diagnosa_pasien where no_rawat=? order by prioritas asc");
                        try {
                            psdiagnosa.setString(1, norawat.getText());
                            rs = psdiagnosa.executeQuery();
                            while (rs.next()) {
                                key = rs.getString(1) + ", " + key;
                            }
                        } catch (Exception ex) {
                            System.out.println("Notifikasi : " + ex);
                        } finally {
                            if (rs != null) {
                                rs.close();
                            }
                            if (psdiagnosa != null) {
                                psdiagnosa.close();
                            }
                        }
                    } catch (Exception ex) {
                        System.out.println(ex);
                    }

                    if (WindowInputKamar.isVisible() == true) {
                        diagnosaakhir.setText(key);
                        diagnosaakhir.requestFocus();
                    } else if (WindowInputKamar.isVisible() == false) {
                        Sequel.mengedit("kamar_inap", "no_rawat='" + norawat.getText() + "' and kd_kamar='" + kdkamar.getText() + "' and tgl_masuk='" + TIn.getText() + "' and jam_masuk='" + JamMasuk.getText() + "'", "diagnosa_akhir='" + key + "'");
                        tampil();
                    }
                }
            }

            @Override
            public void windowIconified(WindowEvent e) {
            }

            @Override
            public void windowDeiconified(WindowEvent e) {
            }

            @Override
            public void windowActivated(WindowEvent e) {
            }

            @Override
            public void windowDeactivated(WindowEvent e) {
            }
        });

        kamar.getTable().addKeyListener(new KeyListener() {
            @Override
            public void keyTyped(KeyEvent e) {
            }

            @Override
            public void keyPressed(KeyEvent e) {
                if (var.getform().equals("DlgKamarInap")) {
                    if (e.getKeyCode() == KeyEvent.VK_SPACE) {
                        kamar.dispose();
                    }
                }
            }

            @Override
            public void keyReleased(KeyEvent e) {
            }
        });

        kamar.bangsal.addWindowListener(new WindowListener() {
            @Override
            public void windowOpened(WindowEvent e) {
            }

            @Override
            public void windowClosing(WindowEvent e) {
            }

            @Override
            public void windowClosed(WindowEvent e) {
                if (var.getform().equals("DlgKamarInap")) {
                    if (kamar.bangsal.getTable().getSelectedRow() != -1) {
                        BangsalCari.setText(kamar.bangsal.getTable().getValueAt(kamar.bangsal.getTable().getSelectedRow(), 1).toString());
                    }
                    BangsalCari.requestFocus();
                }
            }

            @Override
            public void windowIconified(WindowEvent e) {
            }

            @Override
            public void windowDeiconified(WindowEvent e) {
            }

            @Override
            public void windowActivated(WindowEvent e) {
            }

            @Override
            public void windowDeactivated(WindowEvent e) {
            }
        });

        reg.pasien.penjab.addWindowListener(new WindowListener() {
            @Override
            public void windowOpened(WindowEvent e) {
            }

            @Override
            public void windowClosing(WindowEvent e) {
            }

            @Override
            public void windowClosed(WindowEvent e) {
                if (var.getform().equals("DlgKamarInap")) {
                    if (reg.pasien.penjab.getTable().getSelectedRow() != -1) {
                        kdpenjab.setText(reg.pasien.penjab.getTable().getValueAt(reg.pasien.penjab.getTable().getSelectedRow(), 1).toString());
                        nmpenjab.setText(reg.pasien.penjab.getTable().getValueAt(reg.pasien.penjab.getTable().getSelectedRow(), 2).toString());
                    }
                    kdpenjab.requestFocus();
                }
            }

            @Override
            public void windowIconified(WindowEvent e) {
            }

            @Override
            public void windowDeiconified(WindowEvent e) {
            }

            @Override
            public void windowActivated(WindowEvent e) {
            }

            @Override
            public void windowDeactivated(WindowEvent e) {
            }
        });

        reg.pasien.penjab.getTable().addKeyListener(new KeyListener() {
            @Override
            public void keyTyped(KeyEvent e) {
            }

            @Override
            public void keyPressed(KeyEvent e) {
                if (var.getform().equals("DlgKamarInap")) {
                    if (e.getKeyCode() == KeyEvent.VK_SPACE) {
                        reg.pasien.penjab.dispose();
                    }
                }
            }

            @Override
            public void keyReleased(KeyEvent e) {
            }
        });

        billing.rawatinap.addWindowListener(new WindowListener() {
            @Override
            public void windowOpened(WindowEvent e) {
            }

            @Override
            public void windowClosing(WindowEvent e) {
            }

            @Override
            public void windowClosed(WindowEvent e) {
                if (var.getform().equals("DlgKamarInap")) {
                    tbKamIn.requestFocus();
                }
            }

            @Override
            public void windowIconified(WindowEvent e) {
            }

            @Override
            public void windowDeiconified(WindowEvent e) {
            }

            @Override
            public void windowActivated(WindowEvent e) {
            }

            @Override
            public void windowDeactivated(WindowEvent e) {
            }
        });

        reg.getButton().addActionListener((ActionEvent e) -> {
            if (var.getform().equals("DlgKamarInap")) {
                norawat.setText(reg.getTextField().getText());
                Sequel.cariIsi("select no_rkm_medis from reg_periksa where no_rawat=? ", TNoRM, norawat.getText());
                Sequel.cariIsi("select nm_pasien from pasien where no_rkm_medis=? ", TPasien, TNoRM.getText());
            }
        });
        try {
            if (diagnosa_akhir.equals("Yes")) {
                diagnosaakhir.setEditable(true);
            } else {
                diagnosaakhir.setEditable(false);
            }
        } catch (Exception e) {
            diagnosaakhir.setEditable(false);
        }
        userLap();
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        WindowInputKamar = new javax.swing.JDialog();
        internalFrame2 = new widget.InternalFrame();
        norawat = new widget.TextBox();
        TPasien = new widget.TextBox();
        kdkamar = new widget.TextBox();
        jLabel10 = new widget.Label();
        ttlbiaya = new widget.TextBox();
        jLabel3 = new widget.Label();
        jLabel12 = new widget.Label();
        btnReg = new widget.Button();
        TNoRM = new widget.TextBox();
        btnKamar = new widget.Button();
        TKdBngsal = new widget.TextBox();
        jLabel13 = new widget.Label();
        cmbDtk = new widget.ComboBox();
        cmbMnt = new widget.ComboBox();
        cmbJam = new widget.ComboBox();
        TBangsal = new widget.TextBox();
        jLabel11 = new widget.Label();
        TJmlHari = new widget.TextBox();
        jLabel15 = new widget.Label();
        TSttsKamar = new widget.TextBox();
        BtnCloseIn = new widget.Button();
        jLabel19 = new widget.Label();
        BtnSimpan = new widget.Button();
        BtnBatal = new widget.Button();
        jLabel14 = new widget.Label();
        jLabel16 = new widget.Label();
        TTarif = new widget.TextBox();
        LblStts = new widget.Label();
        jLabel18 = new widget.Label();
        diagnosaawal = new widget.TextBox();
        diagnosaakhir = new widget.TextBox();
        jLabel23 = new widget.Label();
        CmbTahun = new widget.ComboBox();
        CmbBln = new widget.ComboBox();
        CmbTgl = new widget.ComboBox();
        jLabel24 = new widget.Label();
        cmbStatus = new widget.ComboBox();
        jLabel26 = new widget.Label();
        btnDiagnosa = new widget.Button();
        jLabel41 = new widget.Label();
        ket = new widget.TextBox();
        jLabel40 = new widget.Label();
        TglMati = new widget.Tanggal();
        cmbJam1 = new widget.ComboBox();
        jLabel38 = new widget.Label();
        cmbMnt1 = new widget.ComboBox();
        cmbDtk1 = new widget.ComboBox();
        TOut = new widget.TextBox();
        TIn = new widget.TextBox();
        jPopupMenu1 = new javax.swing.JPopupMenu();
        MnTindakan = new javax.swing.JMenu();
        MnRawatInap = new javax.swing.JMenuItem();
        MnRawatJalan = new javax.swing.JMenuItem();
        MnPeriksaLab = new javax.swing.JMenuItem();
        MnPeriksaRadiologi = new javax.swing.JMenuItem();
        MnOperasi = new javax.swing.JMenuItem();
        MnObat = new javax.swing.JMenu();
        MnPemberianObat = new javax.swing.JMenuItem();
        MnInputResep = new javax.swing.JMenuItem();
        MnNoResep = new javax.swing.JMenuItem();
        MnStokObatPasien = new javax.swing.JMenuItem();
        MnReturJual = new javax.swing.JMenuItem();
        MnResepPulang = new javax.swing.JMenuItem();
        MnRujukan = new javax.swing.JMenu();
        MnRujuk = new javax.swing.JMenuItem();
        MnRujukMasuk = new javax.swing.JMenuItem();
        MnPermintaan = new javax.swing.JMenu();
        MnOrderLab = new javax.swing.JMenuItem();
        MnOrderRad = new javax.swing.JMenuItem();
        Mndpjp = new javax.swing.JMenu();
        MnDPJP = new javax.swing.JMenuItem();
        MnDPJPRanap = new javax.swing.JMenuItem();
        MnGantiData = new javax.swing.JMenu();
        MnDataMati = new javax.swing.JMenuItem();
        MnPenjab = new javax.swing.JMenuItem();
        MnBatalPulang = new javax.swing.JMenuItem();
        MnDiagnosaAwal = new javax.swing.JMenuItem();
        MnStatusPulang = new javax.swing.JMenuItem();
        MnWaktuRegRalan = new javax.swing.JMenuItem();
        MnPindahTransaksi = new javax.swing.JMenuItem();
        MnHapusData = new javax.swing.JMenu();
        MnHapusTagihanOperasi = new javax.swing.JMenuItem();
        MnHapusObatOperasi = new javax.swing.JMenuItem();
        MnHapusDataSalah = new javax.swing.JMenuItem();
        MnLaporan = new javax.swing.JMenu();
        MnRegisterPasien = new javax.swing.JMenuItem();
        MnRegisterPersalinan = new javax.swing.JMenuItem();
        MnSuratKeteranganRawat = new javax.swing.JMenu();
        MnCovid = new javax.swing.JMenuItem();
        MnNonCovid = new javax.swing.JMenuItem();
        MnRincianObat = new javax.swing.JMenuItem();
        MnRM2D = new javax.swing.JMenuItem();
        MnSensusRanap = new javax.swing.JMenuItem();
        MnDietMakanan = new javax.swing.JMenuItem();
        MnStatusGZ = new javax.swing.JMenuItem();
        MnGiziBurukPx = new javax.swing.JMenuItem();
        MnPencapaianAsuhanGZ = new javax.swing.JMenuItem();
        MnTilikBedah = new javax.swing.JMenuItem();
        MnAsuhanGizi = new javax.swing.JMenuItem();
        MnSrtAPS = new javax.swing.JMenuItem();
        MnSrtMati = new javax.swing.JMenuItem();
        MnSrtMatiICU = new javax.swing.JMenuItem();
        MnFormulirKematian = new javax.swing.JMenuItem();
        MnPengantarPulang = new javax.swing.JMenuItem();
        MnManualSEPBPJS = new javax.swing.JMenuItem();
        MnCetakKelengkapanInap = new javax.swing.JMenu();
        MnKartu = new javax.swing.JMenu();
        MnKartuUmum = new javax.swing.JMenuItem();
        MnKartuNonUmum = new javax.swing.JMenuItem();
        MnGelangDewasaAnak = new javax.swing.JMenu();
        MnPrinterBaru = new javax.swing.JMenuItem();
        MnPrinterLama = new javax.swing.JMenuItem();
        MnGelangBayi = new javax.swing.JMenu();
        MnPrinterBaru1 = new javax.swing.JMenuItem();
        MnPrinterLama1 = new javax.swing.JMenuItem();
        MnBarcodeRM = new javax.swing.JMenu();
        MnBarcodeRM1 = new javax.swing.JMenuItem();
        MnBarcodeRM2 = new javax.swing.JMenuItem();
        MnLabelRM = new javax.swing.JMenu();
        MnLabelRM1 = new javax.swing.JMenuItem();
        MnLabelRM2 = new javax.swing.JMenuItem();
        MnTracerInap = new javax.swing.JMenuItem();
        jSeparator1 = new javax.swing.JPopupMenu.Separator();
        MnLabelPxRanap3 = new javax.swing.JMenu();
        MrkChampion = new javax.swing.JMenuItem();
        MrkAjp = new javax.swing.JMenuItem();
        MrkCox = new javax.swing.JMenuItem();
        MrkAlfa = new javax.swing.JMenuItem();
        MrkOlean = new javax.swing.JMenuItem();
        MrkKojico = new javax.swing.JMenuItem();
        MnLabelPxRanap1 = new javax.swing.JMenuItem();
        MnLabelPxRanap2 = new javax.swing.JMenuItem();
        MnBridging = new javax.swing.JMenu();
        ppPasienCorona = new javax.swing.JMenuItem();
        MnSEP = new javax.swing.JMenuItem();
        ppSuratKontrol = new javax.swing.JMenuItem();
        MnSJP = new javax.swing.JMenuItem();
        MnSEPJamkesda = new javax.swing.JMenuItem();
        MnSEPJampersal = new javax.swing.JMenuItem();
        MnRujukSisrute = new javax.swing.JMenuItem();
        MnDataBPJS = new javax.swing.JMenu();
        MnLihatSEP = new javax.swing.JMenuItem();
        MnSKDPBPJS = new javax.swing.JMenuItem();
        MnSelisihTarif = new javax.swing.JMenuItem();
        MnInputData = new javax.swing.JMenu();
        MnAssesmenAsuhanGizi = new javax.swing.JMenuItem();
        MnMonevAsuhanGizi = new javax.swing.JMenuItem();
        MnDiet = new javax.swing.JMenuItem();
        MnStatusGizi = new javax.swing.JMenuItem();
        MnGiziBuruk = new javax.swing.JMenuItem();
        MnDiagnosa = new javax.swing.JMenuItem();
        MnRingkasanPulang = new javax.swing.JMenuItem();
        ppDataPersalinan = new javax.swing.JMenuItem();
        ppDataPonek = new javax.swing.JMenuItem();
        ppDataHAIs = new javax.swing.JMenuItem();
        ppCatatanPasien = new javax.swing.JMenuItem();
        ppPerawatanCorona = new javax.swing.JMenuItem();
        MnBilling = new javax.swing.JMenuItem();
        MnDeposit = new javax.swing.JMenuItem();
        MnUpdateHari = new javax.swing.JMenuItem();
        ppRiwayat = new javax.swing.JMenuItem();
        jPopupMenu2 = new javax.swing.JPopupMenu();
        MnIndividuPx = new javax.swing.JMenuItem();
        buttonGroup1 = new javax.swing.ButtonGroup();
        JamMasuk = new widget.TextBox();
        WindowPindahKamar = new javax.swing.JDialog();
        internalFrame3 = new widget.InternalFrame();
        norawatpindah = new widget.TextBox();
        TPasienpindah = new widget.TextBox();
        kdkamarpindah = new widget.TextBox();
        ttlbiayapindah = new widget.TextBox();
        jLabel4 = new widget.Label();
        jLabel20 = new widget.Label();
        TNoRMpindah = new widget.TextBox();
        btnKamar2 = new widget.Button();
        TKdBngsalpindah = new widget.TextBox();
        jLabel27 = new widget.Label();
        cmbDtkpindah = new widget.ComboBox();
        cmbMntpindah = new widget.ComboBox();
        cmbJampindah = new widget.ComboBox();
        TBangsalpindah = new widget.TextBox();
        jLabel28 = new widget.Label();
        TJmlHaripindah = new widget.TextBox();
        jLabel29 = new widget.Label();
        TSttsKamarpindah = new widget.TextBox();
        BtnCloseInpindah = new widget.Button();
        jLabel30 = new widget.Label();
        BtnSimpanpindah = new widget.Button();
        jLabel31 = new widget.Label();
        jLabel32 = new widget.Label();
        TTarifpindah = new widget.TextBox();
        CmbTahunpindah = new widget.ComboBox();
        CmbBlnpindah = new widget.ComboBox();
        CmbTglpindah = new widget.ComboBox();
        jLabel35 = new widget.Label();
        Rganti3 = new widget.RadioButton();
        jLabel33 = new widget.Label();
        Rganti2 = new widget.RadioButton();
        Rganti1 = new widget.RadioButton();
        Rganti4 = new widget.RadioButton();
        buttonGroup2 = new javax.swing.ButtonGroup();
        WindowCaraBayar = new javax.swing.JDialog();
        internalFrame5 = new widget.InternalFrame();
        BtnCloseIn4 = new widget.Button();
        BtnSimpan4 = new widget.Button();
        jLabel17 = new widget.Label();
        kdpenjab = new widget.TextBox();
        nmpenjab = new widget.TextBox();
        btnBayar = new widget.Button();
        WindowRanapGabung = new javax.swing.JDialog();
        internalFrame6 = new widget.InternalFrame();
        BtnCloseGabung = new widget.Button();
        BtnSimpanGabung = new widget.Button();
        jLabel34 = new widget.Label();
        NoRmBayi = new widget.TextBox();
        NmBayi = new widget.TextBox();
        btnPasienRanapGabung = new widget.Button();
        BtnHapusGabung = new widget.Button();
        NoRawatGabung = new widget.TextBox();
        btnPasienRanapGabung1 = new widget.Button();
        DlgJamkesda = new javax.swing.JDialog();
        internalFrame7 = new widget.InternalFrame();
        BtnCloseIn1 = new widget.Button();
        BtnSimpan1 = new widget.Button();
        jLabel36 = new widget.Label();
        noSrt = new widget.TextBox();
        jLabel37 = new widget.Label();
        Tglsurat = new widget.Tanggal();
        BtnGantijkd = new widget.Button();
        BtnCtkJkd = new widget.Button();
        DlgJampersal = new javax.swing.JDialog();
        internalFrame18 = new widget.InternalFrame();
        BtnCloseIn13 = new widget.Button();
        BtnSimpan3 = new widget.Button();
        jLabel104 = new widget.Label();
        noSrt1 = new widget.TextBox();
        jLabel105 = new widget.Label();
        Tglsurat1 = new widget.Tanggal();
        BtnGantijmp = new widget.Button();
        BtnCtkJmp = new widget.Button();
        DlgMati = new javax.swing.JDialog();
        internalFrame8 = new widget.InternalFrame();
        BtnCloseIn2 = new widget.Button();
        ket1 = new widget.TextBox();
        jLabel43 = new widget.Label();
        TglMati1 = new widget.Tanggal();
        BtnGantiMati = new widget.Button();
        jLabel39 = new widget.Label();
        cmbJam2 = new widget.ComboBox();
        cmbMnt2 = new widget.ComboBox();
        cmbDtk2 = new widget.ComboBox();
        BtnHapusMati = new widget.Button();
        jLabel48 = new widget.Label();
        DlgNoSEP = new javax.swing.JDialog();
        internalFrame9 = new widget.InternalFrame();
        BtnCloseIn3 = new widget.Button();
        tglsep = new widget.TextBox();
        jLabel44 = new widget.Label();
        jLabel45 = new widget.Label();
        nosep = new widget.TextBox();
        jLabel46 = new widget.Label();
        jnsBayar = new widget.TextBox();
        BtnPrint2 = new widget.Button();
        WindowSelisihTarif = new javax.swing.JDialog();
        internalFrame10 = new widget.InternalFrame();
        BtnCloseIn6 = new widget.Button();
        BtnSimpan6 = new widget.Button();
        jLabel42 = new widget.Label();
        NoSEP = new widget.TextBox();
        jLabel47 = new widget.Label();
        norm = new widget.TextBox();
        nmpasien = new widget.TextBox();
        nokartu = new widget.TextBox();
        jLabel49 = new widget.Label();
        norawatSEP = new widget.TextBox();
        jLabel50 = new widget.Label();
        jLabel52 = new widget.Label();
        rginap = new widget.TextBox();
        jLabel53 = new widget.Label();
        hakkelas = new widget.TextBox();
        jLabel54 = new widget.Label();
        kdINACBG = new widget.TextBox();
        deskripsiKD = new widget.TextBox();
        jLabel55 = new widget.Label();
        jLabel56 = new widget.Label();
        tarifkls1 = new widget.TextBox();
        jLabel57 = new widget.Label();
        tarifkls2 = new widget.TextBox();
        jLabel58 = new widget.Label();
        tarifkls3 = new widget.TextBox();
        jLabel59 = new widget.Label();
        jLabel60 = new widget.Label();
        lmrawat = new widget.TextBox();
        dibayar = new widget.TextBox();
        jLabel61 = new widget.Label();
        persenSELISIH = new widget.TextBox();
        jLabel62 = new widget.Label();
        labelbyr = new widget.Label();
        naikKLS = new widget.TextBox();
        BtnGantikode = new widget.Button();
        WindowGiziBuruk = new javax.swing.JDialog();
        internalFrame13 = new widget.InternalFrame();
        Scroll3 = new widget.ScrollPane();
        tbGiziBuruk = new widget.Table();
        jPanel5 = new javax.swing.JPanel();
        panelGlass9 = new widget.panelisi();
        jLabel91 = new widget.Label();
        DTPCari9 = new widget.Tanggal();
        jLabel92 = new widget.Label();
        DTPCari10 = new widget.Tanggal();
        jLabel90 = new widget.Label();
        TCari3 = new widget.TextBox();
        BtnCari3 = new widget.Button();
        panelGlass12 = new widget.panelisi();
        BtnSimpan7 = new widget.Button();
        BtnHapus = new widget.Button();
        BtnEdit2 = new widget.Button();
        BtnCetakGB = new widget.Button();
        BtnAll3 = new widget.Button();
        BtnCloseIn8 = new widget.Button();
        PanelInput1 = new javax.swing.JPanel();
        FormInput = new widget.PanelBiasa();
        jLabel71 = new widget.Label();
        norwGB = new widget.TextBox();
        normGB = new widget.TextBox();
        nmpxGB = new widget.TextBox();
        jLabel72 = new widget.Label();
        umurGB = new widget.TextBox();
        jLabel73 = new widget.Label();
        diagawalGB = new widget.TextBox();
        jLabel74 = new widget.Label();
        bbAwal = new widget.TextBox();
        jLabel77 = new widget.Label();
        jLabel78 = new widget.Label();
        bbu = new widget.TextBox();
        jLabel75 = new widget.Label();
        bbAkhir = new widget.TextBox();
        jLabel79 = new widget.Label();
        bbpb = new widget.TextBox();
        jLabel76 = new widget.Label();
        pbtb = new widget.TextBox();
        jLabel80 = new widget.Label();
        pbu = new widget.TextBox();
        jLabel81 = new widget.Label();
        jLabel82 = new widget.Label();
        jLabel83 = new widget.Label();
        pemberianNutrisi = new widget.TextBox();
        diagDokterGZ = new widget.TextBox();
        perhitunganZatGZ = new widget.TextBox();
        jLabel84 = new widget.Label();
        jLabel85 = new widget.Label();
        albumin = new widget.TextBox();
        jLabel87 = new widget.Label();
        leukosit = new widget.TextBox();
        plt = new widget.TextBox();
        jLabel88 = new widget.Label();
        hb = new widget.TextBox();
        jLabel86 = new widget.Label();
        jLabel89 = new widget.Label();
        asalFaskes = new widget.TextBox();
        btnFaskes = new widget.Button();
        ChkInput = new widget.CekBox();
        WindowStatusPulang = new javax.swing.JDialog();
        internalFrame14 = new widget.InternalFrame();
        BtnCloseIn9 = new widget.Button();
        BtnSimpan8 = new widget.Button();
        jLabel93 = new widget.Label();
        cmbSttsPlg = new widget.ComboBox();
        WindowPulangAPS = new javax.swing.JDialog();
        internalFrame16 = new widget.InternalFrame();
        BtnCloseIn11 = new widget.Button();
        BtnSimpan9 = new widget.Button();
        jLabel96 = new widget.Label();
        Scroll5 = new widget.ScrollPane();
        tbAPS = new widget.Table();
        jLabel98 = new widget.Label();
        TCari4 = new widget.TextBox();
        BtnCari5 = new widget.Button();
        alasanAPS = new widget.TextBox();
        BtnBatal2 = new widget.Button();
        BtnEdit3 = new widget.Button();
        BtnPrint1 = new widget.Button();
        jLabel100 = new widget.Label();
        ketAPS = new widget.TextBox();
        WindowDiagnosaAwal = new javax.swing.JDialog();
        internalFrame17 = new widget.InternalFrame();
        BtnCloseIn12 = new widget.Button();
        BtnSimpan10 = new widget.Button();
        jLabel101 = new widget.Label();
        TDiagnosaAwal = new widget.TextBox();
        WindowWaktuRegRalan = new javax.swing.JDialog();
        internalFrame11 = new widget.InternalFrame();
        BtnCloseIn5 = new widget.Button();
        jLabel51 = new widget.Label();
        TglRegRalan = new widget.Tanggal();
        BtnGantiTgl = new widget.Button();
        jLabel111 = new widget.Label();
        cmbJamReg = new widget.CekBox();
        jLabel115 = new widget.Label();
        Jreg = new widget.ComboBox();
        Mreg = new widget.ComboBox();
        Dreg = new widget.ComboBox();
        WindowPindahkanTransaksi = new javax.swing.JDialog();
        internalFrame19 = new widget.InternalFrame();
        BtnCloseIn14 = new widget.Button();
        jLabel63 = new widget.Label();
        BtnProsesTran = new widget.Button();
        jLabel64 = new widget.Label();
        jLabel106 = new widget.Label();
        TNoRwTerpilih = new widget.TextBox();
        TNoRwTujuan = new widget.TextBox();
        ChkTglTran = new widget.CekBox();
        jLabel108 = new widget.Label();
        cmbJnsTran = new widget.ComboBox();
        pasienTerpilih = new widget.TextBox();
        tglDari = new widget.TextBox();
        jLabel110 = new widget.Label();
        pukulDari = new widget.TextBox();
        Scroll6 = new widget.ScrollPane();
        tbDataTran = new widget.Table();
        BtnCekTran = new widget.Button();
        jLabel107 = new widget.Label();
        jLabel109 = new widget.Label();
        jLabel112 = new widget.Label();
        LCount1 = new widget.Label();
        sepJkd = new widget.TextBox();
        sepJmp = new widget.TextBox();
        tglInap = new widget.TextBox();
        catatanIGD = new widget.TextBox();
        NoRMmati = new widget.TextBox();
        StatusDaftar = new widget.TextBox();
        JamPulang = new widget.TextBox();
        JenisMati = new widget.TextBox();
        ruangrawat = new widget.TextBox();
        userBerizin = new widget.TextBox();
        ruangDicetak = new widget.TextBox();
        kdAkses = new widget.TextBox();
        statusSEP = new widget.TextBox();
        tglMasukInap = new widget.Tanggal();
        nominal1 = new widget.TextBox();
        hasilLM = new widget.TextBox();
        nominal2 = new widget.TextBox();
        statusSELISIH = new widget.TextBox();
        dataGZ = new widget.TextBox();
        dataGB = new widget.TextBox();
        cekDataAnak = new widget.TextBox();
        status_pulang = new widget.TextBox();
        internalFrame1 = new widget.InternalFrame();
        PanelCariUtama = new javax.swing.JPanel();
        panelGlass10 = new widget.panelisi();
        BtnIn = new widget.Button();
        BtnOut = new widget.Button();
        btnPindah = new widget.Button();
        BtnAll = new widget.Button();
        BtnKeluar = new widget.Button();
        jLabel94 = new widget.Label();
        norawatCopy = new widget.TextBox();
        jLabel95 = new widget.Label();
        noRMCopy = new widget.TextBox();
        panelGlass11 = new widget.panelisi();
        jLabel21 = new widget.Label();
        BangsalCari = new widget.TextBox();
        btnBangsalCari = new widget.Button();
        jLabel6 = new widget.Label();
        TCari = new widget.TextBox();
        BtnCari = new widget.Button();
        jLabel7 = new widget.Label();
        LCount = new widget.Label();
        panelCari = new widget.panelisi();
        R1 = new widget.RadioButton();
        R2 = new widget.RadioButton();
        DTPCari1 = new widget.Tanggal();
        jLabel22 = new widget.Label();
        DTPCari2 = new widget.Tanggal();
        R3 = new widget.RadioButton();
        DTPCari3 = new widget.Tanggal();
        jLabel25 = new widget.Label();
        DTPCari4 = new widget.Tanggal();
        jLabel8 = new widget.Label();
        cmbRuangan = new widget.ComboBox();
        cmbRuangKhusus1 = new widget.ComboBox();
        cmbRuangKhusus2 = new widget.ComboBox();
        cmbRuangKhusus4 = new widget.ComboBox();
        cmbRuangKhusus3 = new widget.ComboBox();
        cmbRuangKhusus5 = new widget.ComboBox();
        Scroll = new widget.ScrollPane();
        tbKamIn = new widget.Table();

        WindowInputKamar.setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);
        WindowInputKamar.setName("WindowInputKamar"); // NOI18N
        WindowInputKamar.setUndecorated(true);
        WindowInputKamar.setResizable(false);
        WindowInputKamar.addWindowListener(new java.awt.event.WindowAdapter() {
            public void windowActivated(java.awt.event.WindowEvent evt) {
                WindowInputKamarWindowActivated(evt);
            }
        });

        internalFrame2.setBorder(javax.swing.BorderFactory.createTitledBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 153, 255), 3), "::[ Input Kamar Inap Pasien ]::", javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Tahoma", 1, 12))); // NOI18N
        internalFrame2.setName("internalFrame2"); // NOI18N
        internalFrame2.setWarnaBawah(new java.awt.Color(245, 250, 240));
        internalFrame2.setLayout(null);

        norawat.setForeground(new java.awt.Color(0, 0, 0));
        norawat.setHighlighter(null);
        norawat.setName("norawat"); // NOI18N
        norawat.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                norawatKeyPressed(evt);
            }
        });
        internalFrame2.add(norawat);
        norawat.setBounds(75, 25, 150, 23);

        TPasien.setEditable(false);
        TPasien.setForeground(new java.awt.Color(0, 0, 0));
        TPasien.setHighlighter(null);
        TPasien.setName("TPasien"); // NOI18N
        internalFrame2.add(TPasien);
        TPasien.setBounds(359, 25, 269, 23);

        kdkamar.setForeground(new java.awt.Color(0, 0, 0));
        kdkamar.setHighlighter(null);
        kdkamar.setName("kdkamar"); // NOI18N
        kdkamar.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                kdkamarKeyPressed(evt);
            }
        });
        internalFrame2.add(kdkamar);
        kdkamar.setBounds(75, 55, 95, 23);

        jLabel10.setForeground(new java.awt.Color(0, 0, 0));
        jLabel10.setText("Proses :");
        jLabel10.setName("jLabel10"); // NOI18N
        internalFrame2.add(jLabel10);
        jLabel10.setBounds(0, 175, 72, 23);

        ttlbiaya.setEditable(false);
        ttlbiaya.setForeground(new java.awt.Color(0, 0, 0));
        ttlbiaya.setText("0");
        ttlbiaya.setDisabledTextColor(new java.awt.Color(0, 0, 0));
        ttlbiaya.setHighlighter(null);
        ttlbiaya.setName("ttlbiaya"); // NOI18N
        ttlbiaya.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                ttlbiayaKeyPressed(evt);
            }
        });
        internalFrame2.add(ttlbiaya);
        ttlbiaya.setBounds(368, 145, 290, 23);

        jLabel3.setForeground(new java.awt.Color(0, 0, 0));
        jLabel3.setText("No.Rawat :");
        jLabel3.setName("jLabel3"); // NOI18N
        internalFrame2.add(jLabel3);
        jLabel3.setBounds(0, 25, 72, 23);

        jLabel12.setForeground(new java.awt.Color(0, 0, 0));
        jLabel12.setText("Kamar :");
        jLabel12.setName("jLabel12"); // NOI18N
        internalFrame2.add(jLabel12);
        jLabel12.setBounds(0, 55, 72, 23);

        btnReg.setForeground(new java.awt.Color(0, 0, 0));
        btnReg.setIcon(new javax.swing.ImageIcon(getClass().getResource("/picture/190.png"))); // NOI18N
        btnReg.setMnemonic('1');
        btnReg.setToolTipText("Alt+1");
        btnReg.setName("btnReg"); // NOI18N
        btnReg.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnRegActionPerformed(evt);
            }
        });
        btnReg.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                btnRegKeyPressed(evt);
            }
        });
        internalFrame2.add(btnReg);
        btnReg.setBounds(630, 25, 28, 23);

        TNoRM.setEditable(false);
        TNoRM.setForeground(new java.awt.Color(0, 0, 0));
        TNoRM.setHighlighter(null);
        TNoRM.setName("TNoRM"); // NOI18N
        internalFrame2.add(TNoRM);
        TNoRM.setBounds(227, 25, 130, 23);

        btnKamar.setForeground(new java.awt.Color(0, 0, 0));
        btnKamar.setIcon(new javax.swing.ImageIcon(getClass().getResource("/picture/190.png"))); // NOI18N
        btnKamar.setMnemonic('2');
        btnKamar.setToolTipText("Alt+2");
        btnKamar.setName("btnKamar"); // NOI18N
        btnKamar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnKamarActionPerformed(evt);
            }
        });
        btnKamar.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                btnKamarKeyPressed(evt);
            }
        });
        internalFrame2.add(btnKamar);
        btnKamar.setBounds(428, 55, 28, 23);

        TKdBngsal.setEditable(false);
        TKdBngsal.setForeground(new java.awt.Color(0, 0, 0));
        TKdBngsal.setName("TKdBngsal"); // NOI18N
        internalFrame2.add(TKdBngsal);
        TKdBngsal.setBounds(172, 55, 82, 23);

        jLabel13.setForeground(new java.awt.Color(0, 0, 0));
        jLabel13.setText("Tanggal :");
        jLabel13.setName("jLabel13"); // NOI18N
        internalFrame2.add(jLabel13);
        jLabel13.setBounds(0, 85, 72, 23);

        cmbDtk.setForeground(new java.awt.Color(0, 0, 0));
        cmbDtk.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "00", "01", "02", "03", "04", "05", "06", "07", "08", "09", "10", "11", "12", "13", "14", "15", "16", "17", "18", "19", "20", "21", "22", "23", "24", "25", "26", "27", "28", "29", "30", "31", "32", "33", "34", "35", "36", "37", "38", "39", "40", "41", "42", "43", "44", "45", "46", "47", "48", "49", "50", "51", "52", "53", "54", "55", "56", "57", "58", "59" }));
        cmbDtk.setName("cmbDtk"); // NOI18N
        cmbDtk.addItemListener(new java.awt.event.ItemListener() {
            public void itemStateChanged(java.awt.event.ItemEvent evt) {
                CmbTahunItemStateChanged(evt);
            }
        });
        cmbDtk.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                cmbDtkKeyPressed(evt);
            }
        });
        internalFrame2.add(cmbDtk);
        cmbDtk.setBounds(177, 115, 48, 23);

        cmbMnt.setForeground(new java.awt.Color(0, 0, 0));
        cmbMnt.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "00", "01", "02", "03", "04", "05", "06", "07", "08", "09", "10", "11", "12", "13", "14", "15", "16", "17", "18", "19", "20", "21", "22", "23", "24", "25", "26", "27", "28", "29", "30", "31", "32", "33", "34", "35", "36", "37", "38", "39", "40", "41", "42", "43", "44", "45", "46", "47", "48", "49", "50", "51", "52", "53", "54", "55", "56", "57", "58", "59" }));
        cmbMnt.setName("cmbMnt"); // NOI18N
        cmbMnt.addItemListener(new java.awt.event.ItemListener() {
            public void itemStateChanged(java.awt.event.ItemEvent evt) {
                CmbTahunItemStateChanged(evt);
            }
        });
        cmbMnt.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                cmbMntKeyPressed(evt);
            }
        });
        internalFrame2.add(cmbMnt);
        cmbMnt.setBounds(126, 115, 48, 23);

        cmbJam.setForeground(new java.awt.Color(0, 0, 0));
        cmbJam.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "00", "01", "02", "03", "04", "05", "06", "07", "08", "09", "10", "11", "12", "13", "14", "15", "16", "17", "18", "19", "20", "21", "22", "23" }));
        cmbJam.setName("cmbJam"); // NOI18N
        cmbJam.addItemListener(new java.awt.event.ItemListener() {
            public void itemStateChanged(java.awt.event.ItemEvent evt) {
                CmbTahunItemStateChanged(evt);
            }
        });
        cmbJam.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                cmbJamKeyPressed(evt);
            }
        });
        internalFrame2.add(cmbJam);
        cmbJam.setBounds(75, 115, 48, 23);

        TBangsal.setEditable(false);
        TBangsal.setForeground(new java.awt.Color(0, 0, 0));
        TBangsal.setHighlighter(null);
        TBangsal.setName("TBangsal"); // NOI18N
        TBangsal.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                TBangsalKeyPressed(evt);
            }
        });
        internalFrame2.add(TBangsal);
        TBangsal.setBounds(256, 55, 170, 23);

        jLabel11.setForeground(new java.awt.Color(0, 0, 0));
        jLabel11.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel11.setText("X");
        jLabel11.setName("jLabel11"); // NOI18N
        internalFrame2.add(jLabel11);
        jLabel11.setBounds(173, 145, 15, 23);

        TJmlHari.setEditable(false);
        TJmlHari.setForeground(new java.awt.Color(0, 0, 0));
        TJmlHari.setText("0");
        TJmlHari.setDisabledTextColor(new java.awt.Color(0, 0, 0));
        TJmlHari.setHighlighter(null);
        TJmlHari.setName("TJmlHari"); // NOI18N
        internalFrame2.add(TJmlHari);
        TJmlHari.setBounds(75, 145, 96, 23);

        jLabel15.setForeground(new java.awt.Color(0, 0, 0));
        jLabel15.setText("=");
        jLabel15.setName("jLabel15"); // NOI18N
        internalFrame2.add(jLabel15);
        jLabel15.setBounds(342, 145, 20, 23);

        TSttsKamar.setEditable(false);
        TSttsKamar.setForeground(new java.awt.Color(0, 0, 0));
        TSttsKamar.setName("TSttsKamar"); // NOI18N
        internalFrame2.add(TSttsKamar);
        TSttsKamar.setBounds(548, 55, 110, 23);

        BtnCloseIn.setForeground(new java.awt.Color(0, 0, 0));
        BtnCloseIn.setIcon(new javax.swing.ImageIcon(getClass().getResource("/picture/cross.png"))); // NOI18N
        BtnCloseIn.setMnemonic('U');
        BtnCloseIn.setText("Tutup");
        BtnCloseIn.setToolTipText("Alt+U");
        BtnCloseIn.setName("BtnCloseIn"); // NOI18N
        BtnCloseIn.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                BtnCloseInActionPerformed(evt);
            }
        });
        BtnCloseIn.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                BtnCloseInKeyPressed(evt);
            }
        });
        internalFrame2.add(BtnCloseIn);
        BtnCloseIn.setBounds(560, 280, 100, 30);

        jLabel19.setForeground(new java.awt.Color(0, 0, 0));
        jLabel19.setText("-------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------");
        jLabel19.setName("jLabel19"); // NOI18N
        internalFrame2.add(jLabel19);
        jLabel19.setBounds(0, 260, 850, 14);

        BtnSimpan.setForeground(new java.awt.Color(0, 0, 0));
        BtnSimpan.setIcon(new javax.swing.ImageIcon(getClass().getResource("/picture/save-16x16.png"))); // NOI18N
        BtnSimpan.setMnemonic('S');
        BtnSimpan.setText("Simpan");
        BtnSimpan.setToolTipText("Alt+S");
        BtnSimpan.setName("BtnSimpan"); // NOI18N
        BtnSimpan.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                BtnSimpanActionPerformed(evt);
            }
        });
        BtnSimpan.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                BtnSimpanKeyPressed(evt);
            }
        });
        internalFrame2.add(BtnSimpan);
        BtnSimpan.setBounds(10, 280, 100, 30);

        BtnBatal.setForeground(new java.awt.Color(0, 0, 0));
        BtnBatal.setIcon(new javax.swing.ImageIcon(getClass().getResource("/picture/Cancel-2-16x16.png"))); // NOI18N
        BtnBatal.setMnemonic('B');
        BtnBatal.setText("Batal");
        BtnBatal.setToolTipText("Alt+B");
        BtnBatal.setName("BtnBatal"); // NOI18N
        BtnBatal.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                BtnBatalActionPerformed(evt);
            }
        });
        BtnBatal.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                BtnBatalKeyPressed(evt);
            }
        });
        internalFrame2.add(BtnBatal);
        BtnBatal.setBounds(120, 280, 100, 30);

        jLabel14.setForeground(new java.awt.Color(0, 0, 0));
        jLabel14.setText("Stts.Kamar :");
        jLabel14.setName("jLabel14"); // NOI18N
        internalFrame2.add(jLabel14);
        jLabel14.setBounds(444, 55, 100, 23);

        jLabel16.setForeground(new java.awt.Color(0, 0, 0));
        jLabel16.setText("Biaya :");
        jLabel16.setName("jLabel16"); // NOI18N
        internalFrame2.add(jLabel16);
        jLabel16.setBounds(0, 145, 72, 23);

        TTarif.setEditable(false);
        TTarif.setForeground(new java.awt.Color(0, 0, 0));
        TTarif.setText("0");
        TTarif.setDisabledTextColor(new java.awt.Color(0, 0, 0));
        TTarif.setHighlighter(null);
        TTarif.setName("TTarif"); // NOI18N
        internalFrame2.add(TTarif);
        TTarif.setBounds(188, 145, 160, 23);

        LblStts.setForeground(new java.awt.Color(0, 0, 0));
        LblStts.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        LblStts.setText("Check In");
        LblStts.setName("LblStts"); // NOI18N
        internalFrame2.add(LblStts);
        LblStts.setBounds(75, 175, 180, 23);

        jLabel18.setForeground(new java.awt.Color(0, 0, 0));
        jLabel18.setText("Diagnosa Awal Masuk :");
        jLabel18.setName("jLabel18"); // NOI18N
        internalFrame2.add(jLabel18);
        jLabel18.setBounds(275, 85, 140, 23);

        diagnosaawal.setForeground(new java.awt.Color(0, 0, 0));
        diagnosaawal.setHighlighter(null);
        diagnosaawal.setName("diagnosaawal"); // NOI18N
        diagnosaawal.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                diagnosaawalKeyPressed(evt);
            }
        });
        internalFrame2.add(diagnosaawal);
        diagnosaawal.setBounds(418, 85, 240, 23);

        diagnosaakhir.setForeground(new java.awt.Color(0, 0, 0));
        diagnosaakhir.setHighlighter(null);
        diagnosaakhir.setName("diagnosaakhir"); // NOI18N
        diagnosaakhir.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                diagnosaakhirKeyPressed(evt);
            }
        });
        internalFrame2.add(diagnosaakhir);
        diagnosaakhir.setBounds(418, 115, 210, 23);

        jLabel23.setForeground(new java.awt.Color(0, 0, 0));
        jLabel23.setText("Diagnosa Akhir Keluar :");
        jLabel23.setName("jLabel23"); // NOI18N
        internalFrame2.add(jLabel23);
        jLabel23.setBounds(275, 115, 140, 23);

        CmbTahun.setForeground(new java.awt.Color(0, 0, 0));
        CmbTahun.setName("CmbTahun"); // NOI18N
        CmbTahun.addItemListener(new java.awt.event.ItemListener() {
            public void itemStateChanged(java.awt.event.ItemEvent evt) {
                CmbTahunItemStateChanged(evt);
            }
        });
        CmbTahun.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                CmbTahunKeyPressed(evt);
            }
        });
        internalFrame2.add(CmbTahun);
        CmbTahun.setBounds(177, 85, 70, 23);

        CmbBln.setForeground(new java.awt.Color(0, 0, 0));
        CmbBln.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "01", "02", "03", "04", "05", "06", "07", "08", "09", "10", "11", "12" }));
        CmbBln.setName("CmbBln"); // NOI18N
        CmbBln.addItemListener(new java.awt.event.ItemListener() {
            public void itemStateChanged(java.awt.event.ItemEvent evt) {
                CmbTahunItemStateChanged(evt);
            }
        });
        CmbBln.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                CmbBlnKeyPressed(evt);
            }
        });
        internalFrame2.add(CmbBln);
        CmbBln.setBounds(126, 85, 48, 23);

        CmbTgl.setForeground(new java.awt.Color(0, 0, 0));
        CmbTgl.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "01", "02", "03", "04", "05", "06", "07", "08", "09", "10", "11", "12", "13", "14", "15", "16", "17", "18", "19", "20", "21", "22", "23", "24", "25", "26", "27", "28", "29", "30", "31" }));
        CmbTgl.setName("CmbTgl"); // NOI18N
        CmbTgl.addItemListener(new java.awt.event.ItemListener() {
            public void itemStateChanged(java.awt.event.ItemEvent evt) {
                CmbTahunItemStateChanged(evt);
            }
        });
        CmbTgl.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                CmbTglKeyPressed(evt);
            }
        });
        internalFrame2.add(CmbTgl);
        CmbTgl.setBounds(75, 85, 48, 23);

        jLabel24.setForeground(new java.awt.Color(0, 0, 0));
        jLabel24.setText("Jam :");
        jLabel24.setName("jLabel24"); // NOI18N
        internalFrame2.add(jLabel24);
        jLabel24.setBounds(0, 115, 72, 23);

        cmbStatus.setForeground(new java.awt.Color(0, 0, 0));
        cmbStatus.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "Dirujuk", "APS", "Meninggal >= 48 Jam", "Meninggal < 48 Jam", "Sembuh/BLPL", "Kabur", "-" }));
        cmbStatus.setName("cmbStatus"); // NOI18N
        cmbStatus.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                cmbStatusActionPerformed(evt);
            }
        });
        cmbStatus.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                cmbStatusKeyPressed(evt);
            }
        });
        internalFrame2.add(cmbStatus);
        cmbStatus.setBounds(488, 175, 170, 23);

        jLabel26.setForeground(new java.awt.Color(0, 0, 0));
        jLabel26.setText("Status Pulang/Keluar :");
        jLabel26.setName("jLabel26"); // NOI18N
        internalFrame2.add(jLabel26);
        jLabel26.setBounds(350, 175, 130, 23);

        btnDiagnosa.setForeground(new java.awt.Color(0, 0, 0));
        btnDiagnosa.setIcon(new javax.swing.ImageIcon(getClass().getResource("/picture/190.png"))); // NOI18N
        btnDiagnosa.setMnemonic('3');
        btnDiagnosa.setToolTipText("Alt+3");
        btnDiagnosa.setName("btnDiagnosa"); // NOI18N
        btnDiagnosa.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnDiagnosaActionPerformed(evt);
            }
        });
        btnDiagnosa.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                btnDiagnosaKeyPressed(evt);
            }
        });
        internalFrame2.add(btnDiagnosa);
        btnDiagnosa.setBounds(630, 115, 28, 23);

        jLabel41.setForeground(new java.awt.Color(0, 51, 204));
        jLabel41.setText("Keterangan Meninggal :");
        jLabel41.setName("jLabel41"); // NOI18N
        internalFrame2.add(jLabel41);
        jLabel41.setBounds(30, 205, 120, 23);

        ket.setForeground(new java.awt.Color(0, 0, 153));
        ket.setHighlighter(null);
        ket.setName("ket"); // NOI18N
        ket.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                ketKeyPressed(evt);
            }
        });
        internalFrame2.add(ket);
        ket.setBounds(160, 205, 500, 23);

        jLabel40.setForeground(new java.awt.Color(0, 51, 204));
        jLabel40.setText("Tgl. Meninggal :");
        jLabel40.setName("jLabel40"); // NOI18N
        internalFrame2.add(jLabel40);
        jLabel40.setBounds(150, 235, 90, 23);

        TglMati.setEditable(false);
        TglMati.setForeground(new java.awt.Color(0, 51, 204));
        TglMati.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "25-12-2021" }));
        TglMati.setDisplayFormat("dd-MM-yyyy");
        TglMati.setName("TglMati"); // NOI18N
        TglMati.setOpaque(false);
        TglMati.addItemListener(new java.awt.event.ItemListener() {
            public void itemStateChanged(java.awt.event.ItemEvent evt) {
                TglMatiItemStateChanged(evt);
            }
        });
        TglMati.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                TglMatiKeyPressed(evt);
            }
        });
        internalFrame2.add(TglMati);
        TglMati.setBounds(245, 235, 110, 23);

        cmbJam1.setForeground(new java.awt.Color(0, 51, 204));
        cmbJam1.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "00", "01", "02", "03", "04", "05", "06", "07", "08", "09", "10", "11", "12", "13", "14", "15", "16", "17", "18", "19", "20", "21", "22", "23" }));
        cmbJam1.setName("cmbJam1"); // NOI18N
        cmbJam1.setOpaque(false);
        cmbJam1.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                cmbJam1KeyPressed(evt);
            }
        });
        internalFrame2.add(cmbJam1);
        cmbJam1.setBounds(445, 235, 45, 23);

        jLabel38.setForeground(new java.awt.Color(0, 51, 204));
        jLabel38.setText("Jam Meninggal :");
        jLabel38.setName("jLabel38"); // NOI18N
        internalFrame2.add(jLabel38);
        jLabel38.setBounds(360, 235, 80, 23);

        cmbMnt1.setForeground(new java.awt.Color(0, 51, 204));
        cmbMnt1.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "00", "01", "02", "03", "04", "05", "06", "07", "08", "09", "10", "11", "12", "13", "14", "15", "16", "17", "18", "19", "20", "21", "22", "23", "24", "25", "26", "27", "28", "29", "30", "31", "32", "33", "34", "35", "36", "37", "38", "39", "40", "41", "42", "43", "44", "45", "46", "47", "48", "49", "50", "51", "52", "53", "54", "55", "56", "57", "58", "59" }));
        cmbMnt1.setName("cmbMnt1"); // NOI18N
        cmbMnt1.setOpaque(false);
        cmbMnt1.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                cmbMnt1KeyPressed(evt);
            }
        });
        internalFrame2.add(cmbMnt1);
        cmbMnt1.setBounds(495, 235, 45, 23);

        cmbDtk1.setForeground(new java.awt.Color(0, 51, 204));
        cmbDtk1.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "00", "01", "02", "03", "04", "05", "06", "07", "08", "09", "10", "11", "12", "13", "14", "15", "16", "17", "18", "19", "20", "21", "22", "23", "24", "25", "26", "27", "28", "29", "30", "31", "32", "33", "34", "35", "36", "37", "38", "39", "40", "41", "42", "43", "44", "45", "46", "47", "48", "49", "50", "51", "52", "53", "54", "55", "56", "57", "58", "59" }));
        cmbDtk1.setName("cmbDtk1"); // NOI18N
        cmbDtk1.setOpaque(false);
        cmbDtk1.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                cmbDtk1KeyPressed(evt);
            }
        });
        internalFrame2.add(cmbDtk1);
        cmbDtk1.setBounds(545, 235, 45, 23);

        WindowInputKamar.getContentPane().add(internalFrame2, java.awt.BorderLayout.CENTER);

        TOut.setEditable(false);
        TOut.setForeground(new java.awt.Color(255, 255, 255));
        TOut.setHighlighter(null);
        TOut.setName("TOut"); // NOI18N
        TOut.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                TOutKeyPressed(evt);
            }
        });

        TIn.setEditable(false);
        TIn.setForeground(new java.awt.Color(255, 255, 255));
        TIn.setHighlighter(null);
        TIn.setName("TIn"); // NOI18N
        TIn.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                TInKeyPressed(evt);
            }
        });

        jPopupMenu1.setFont(new java.awt.Font("Tahoma", 0, 11)); // NOI18N
        jPopupMenu1.setAutoscrolls(true);
        jPopupMenu1.setCursor(new java.awt.Cursor(java.awt.Cursor.DEFAULT_CURSOR));
        jPopupMenu1.setFocusTraversalPolicyProvider(true);
        jPopupMenu1.setName("jPopupMenu1"); // NOI18N

        MnTindakan.setBackground(new java.awt.Color(255, 255, 255));
        MnTindakan.setIcon(new javax.swing.ImageIcon(getClass().getResource("/picture/category.png"))); // NOI18N
        MnTindakan.setText("Tindakan");
        MnTindakan.setFont(new java.awt.Font("Tahoma", 0, 11)); // NOI18N
        MnTindakan.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        MnTindakan.setHorizontalTextPosition(javax.swing.SwingConstants.RIGHT);
        MnTindakan.setIconTextGap(5);
        MnTindakan.setName("MnTindakan"); // NOI18N
        MnTindakan.setOpaque(true);
        MnTindakan.setPreferredSize(new java.awt.Dimension(220, 26));

        MnRawatInap.setBackground(new java.awt.Color(255, 255, 255));
        MnRawatInap.setFont(new java.awt.Font("Tahoma", 0, 11)); // NOI18N
        MnRawatInap.setIcon(new javax.swing.ImageIcon(getClass().getResource("/picture/category.png"))); // NOI18N
        MnRawatInap.setText("Data Tagihan/Tindakan RAWAT INAP");
        MnRawatInap.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        MnRawatInap.setHorizontalTextPosition(javax.swing.SwingConstants.RIGHT);
        MnRawatInap.setIconTextGap(5);
        MnRawatInap.setName("MnRawatInap"); // NOI18N
        MnRawatInap.setPreferredSize(new java.awt.Dimension(230, 26));
        MnRawatInap.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                MnRawatInapActionPerformed(evt);
            }
        });
        MnTindakan.add(MnRawatInap);

        MnRawatJalan.setBackground(new java.awt.Color(255, 255, 255));
        MnRawatJalan.setFont(new java.awt.Font("Tahoma", 0, 11)); // NOI18N
        MnRawatJalan.setIcon(new javax.swing.ImageIcon(getClass().getResource("/picture/category.png"))); // NOI18N
        MnRawatJalan.setText("Data Tagihan/Tindakan RAWAT JALAN");
        MnRawatJalan.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        MnRawatJalan.setHorizontalTextPosition(javax.swing.SwingConstants.RIGHT);
        MnRawatJalan.setIconTextGap(5);
        MnRawatJalan.setName("MnRawatJalan"); // NOI18N
        MnRawatJalan.setPreferredSize(new java.awt.Dimension(230, 26));
        MnRawatJalan.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                MnRawatJalanActionPerformed(evt);
            }
        });
        MnTindakan.add(MnRawatJalan);

        MnPeriksaLab.setBackground(new java.awt.Color(255, 255, 255));
        MnPeriksaLab.setFont(new java.awt.Font("Tahoma", 0, 11)); // NOI18N
        MnPeriksaLab.setIcon(new javax.swing.ImageIcon(getClass().getResource("/picture/category.png"))); // NOI18N
        MnPeriksaLab.setText("Periksa Laboratorium");
        MnPeriksaLab.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        MnPeriksaLab.setHorizontalTextPosition(javax.swing.SwingConstants.RIGHT);
        MnPeriksaLab.setIconTextGap(5);
        MnPeriksaLab.setName("MnPeriksaLab"); // NOI18N
        MnPeriksaLab.setPreferredSize(new java.awt.Dimension(230, 26));
        MnPeriksaLab.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                MnPeriksaLabActionPerformed(evt);
            }
        });
        MnTindakan.add(MnPeriksaLab);

        MnPeriksaRadiologi.setBackground(new java.awt.Color(255, 255, 255));
        MnPeriksaRadiologi.setFont(new java.awt.Font("Tahoma", 0, 11)); // NOI18N
        MnPeriksaRadiologi.setIcon(new javax.swing.ImageIcon(getClass().getResource("/picture/category.png"))); // NOI18N
        MnPeriksaRadiologi.setText("Periksa Radiologi");
        MnPeriksaRadiologi.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        MnPeriksaRadiologi.setHorizontalTextPosition(javax.swing.SwingConstants.RIGHT);
        MnPeriksaRadiologi.setIconTextGap(5);
        MnPeriksaRadiologi.setName("MnPeriksaRadiologi"); // NOI18N
        MnPeriksaRadiologi.setPreferredSize(new java.awt.Dimension(230, 26));
        MnPeriksaRadiologi.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                MnPeriksaRadiologiActionPerformed(evt);
            }
        });
        MnTindakan.add(MnPeriksaRadiologi);

        MnOperasi.setBackground(new java.awt.Color(255, 255, 255));
        MnOperasi.setFont(new java.awt.Font("Tahoma", 0, 11)); // NOI18N
        MnOperasi.setIcon(new javax.swing.ImageIcon(getClass().getResource("/picture/category.png"))); // NOI18N
        MnOperasi.setText("Tagihan Operasi/VK");
        MnOperasi.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        MnOperasi.setHorizontalTextPosition(javax.swing.SwingConstants.RIGHT);
        MnOperasi.setIconTextGap(5);
        MnOperasi.setName("MnOperasi"); // NOI18N
        MnOperasi.setPreferredSize(new java.awt.Dimension(230, 26));
        MnOperasi.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                MnOperasiActionPerformed(evt);
            }
        });
        MnTindakan.add(MnOperasi);

        jPopupMenu1.add(MnTindakan);

        MnObat.setBackground(new java.awt.Color(255, 255, 255));
        MnObat.setIcon(new javax.swing.ImageIcon(getClass().getResource("/picture/category.png"))); // NOI18N
        MnObat.setText("Obat");
        MnObat.setFont(new java.awt.Font("Tahoma", 0, 11)); // NOI18N
        MnObat.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        MnObat.setHorizontalTextPosition(javax.swing.SwingConstants.RIGHT);
        MnObat.setIconTextGap(5);
        MnObat.setName("MnObat"); // NOI18N
        MnObat.setOpaque(true);
        MnObat.setPreferredSize(new java.awt.Dimension(220, 26));

        MnPemberianObat.setBackground(new java.awt.Color(255, 255, 255));
        MnPemberianObat.setFont(new java.awt.Font("Tahoma", 0, 11)); // NOI18N
        MnPemberianObat.setIcon(new javax.swing.ImageIcon(getClass().getResource("/picture/category.png"))); // NOI18N
        MnPemberianObat.setText("Data Pemberian Obat/BHP");
        MnPemberianObat.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        MnPemberianObat.setHorizontalTextPosition(javax.swing.SwingConstants.RIGHT);
        MnPemberianObat.setIconTextGap(5);
        MnPemberianObat.setName("MnPemberianObat"); // NOI18N
        MnPemberianObat.setPreferredSize(new java.awt.Dimension(180, 26));
        MnPemberianObat.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                MnPemberianObatActionPerformed(evt);
            }
        });
        MnObat.add(MnPemberianObat);

        MnInputResep.setBackground(new java.awt.Color(255, 255, 255));
        MnInputResep.setFont(new java.awt.Font("Tahoma", 0, 11)); // NOI18N
        MnInputResep.setIcon(new javax.swing.ImageIcon(getClass().getResource("/picture/category.png"))); // NOI18N
        MnInputResep.setText("Input Resep Pulang");
        MnInputResep.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        MnInputResep.setHorizontalTextPosition(javax.swing.SwingConstants.RIGHT);
        MnInputResep.setIconTextGap(5);
        MnInputResep.setName("MnInputResep"); // NOI18N
        MnInputResep.setPreferredSize(new java.awt.Dimension(180, 26));
        MnInputResep.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                MnInputResepActionPerformed(evt);
            }
        });
        MnObat.add(MnInputResep);

        MnNoResep.setBackground(new java.awt.Color(255, 255, 255));
        MnNoResep.setFont(new java.awt.Font("Tahoma", 0, 11)); // NOI18N
        MnNoResep.setIcon(new javax.swing.ImageIcon(getClass().getResource("/picture/category.png"))); // NOI18N
        MnNoResep.setText("Input No.Resep");
        MnNoResep.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        MnNoResep.setHorizontalTextPosition(javax.swing.SwingConstants.RIGHT);
        MnNoResep.setIconTextGap(5);
        MnNoResep.setName("MnNoResep"); // NOI18N
        MnNoResep.setPreferredSize(new java.awt.Dimension(180, 26));
        MnNoResep.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                MnNoResepActionPerformed(evt);
            }
        });
        MnObat.add(MnNoResep);

        MnStokObatPasien.setBackground(new java.awt.Color(255, 255, 255));
        MnStokObatPasien.setFont(new java.awt.Font("Tahoma", 0, 11)); // NOI18N
        MnStokObatPasien.setIcon(new javax.swing.ImageIcon(getClass().getResource("/picture/category.png"))); // NOI18N
        MnStokObatPasien.setText("Stok Obat Pasien Ranap");
        MnStokObatPasien.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        MnStokObatPasien.setHorizontalTextPosition(javax.swing.SwingConstants.RIGHT);
        MnStokObatPasien.setIconTextGap(5);
        MnStokObatPasien.setName("MnStokObatPasien"); // NOI18N
        MnStokObatPasien.setPreferredSize(new java.awt.Dimension(180, 26));
        MnStokObatPasien.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                MnStokObatPasienActionPerformed(evt);
            }
        });
        MnObat.add(MnStokObatPasien);

        MnReturJual.setBackground(new java.awt.Color(255, 255, 255));
        MnReturJual.setFont(new java.awt.Font("Tahoma", 0, 11)); // NOI18N
        MnReturJual.setIcon(new javax.swing.ImageIcon(getClass().getResource("/picture/category.png"))); // NOI18N
        MnReturJual.setText("Retur Obat/Barang/Alkes");
        MnReturJual.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        MnReturJual.setHorizontalTextPosition(javax.swing.SwingConstants.RIGHT);
        MnReturJual.setIconTextGap(5);
        MnReturJual.setName("MnReturJual"); // NOI18N
        MnReturJual.setPreferredSize(new java.awt.Dimension(180, 26));
        MnReturJual.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                MnReturJualActionPerformed(evt);
            }
        });
        MnObat.add(MnReturJual);

        MnResepPulang.setBackground(new java.awt.Color(255, 255, 255));
        MnResepPulang.setFont(new java.awt.Font("Tahoma", 0, 11)); // NOI18N
        MnResepPulang.setIcon(new javax.swing.ImageIcon(getClass().getResource("/picture/category.png"))); // NOI18N
        MnResepPulang.setText("Data Resep Pulang");
        MnResepPulang.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        MnResepPulang.setHorizontalTextPosition(javax.swing.SwingConstants.RIGHT);
        MnResepPulang.setIconTextGap(5);
        MnResepPulang.setName("MnResepPulang"); // NOI18N
        MnResepPulang.setPreferredSize(new java.awt.Dimension(180, 26));
        MnResepPulang.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                MnResepPulangActionPerformed(evt);
            }
        });
        MnObat.add(MnResepPulang);

        jPopupMenu1.add(MnObat);

        MnRujukan.setBackground(new java.awt.Color(255, 255, 255));
        MnRujukan.setIcon(new javax.swing.ImageIcon(getClass().getResource("/picture/category.png"))); // NOI18N
        MnRujukan.setText("Rujukan Pasien");
        MnRujukan.setFont(new java.awt.Font("Tahoma", 0, 11)); // NOI18N
        MnRujukan.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        MnRujukan.setHorizontalTextPosition(javax.swing.SwingConstants.RIGHT);
        MnRujukan.setIconTextGap(5);
        MnRujukan.setName("MnRujukan"); // NOI18N
        MnRujukan.setOpaque(true);
        MnRujukan.setPreferredSize(new java.awt.Dimension(220, 26));

        MnRujuk.setBackground(new java.awt.Color(255, 255, 255));
        MnRujuk.setFont(new java.awt.Font("Tahoma", 0, 11)); // NOI18N
        MnRujuk.setIcon(new javax.swing.ImageIcon(getClass().getResource("/picture/category.png"))); // NOI18N
        MnRujuk.setText("Rujukan Keluar");
        MnRujuk.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        MnRujuk.setHorizontalTextPosition(javax.swing.SwingConstants.RIGHT);
        MnRujuk.setIconTextGap(5);
        MnRujuk.setName("MnRujuk"); // NOI18N
        MnRujuk.setPreferredSize(new java.awt.Dimension(130, 26));
        MnRujuk.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                MnRujukActionPerformed(evt);
            }
        });
        MnRujukan.add(MnRujuk);

        MnRujukMasuk.setBackground(new java.awt.Color(255, 255, 255));
        MnRujukMasuk.setFont(new java.awt.Font("Tahoma", 0, 11)); // NOI18N
        MnRujukMasuk.setIcon(new javax.swing.ImageIcon(getClass().getResource("/picture/category.png"))); // NOI18N
        MnRujukMasuk.setText("Rujukan Masuk");
        MnRujukMasuk.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        MnRujukMasuk.setHorizontalTextPosition(javax.swing.SwingConstants.RIGHT);
        MnRujukMasuk.setIconTextGap(5);
        MnRujukMasuk.setName("MnRujukMasuk"); // NOI18N
        MnRujukMasuk.setPreferredSize(new java.awt.Dimension(130, 26));
        MnRujukMasuk.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                MnRujukMasukActionPerformed(evt);
            }
        });
        MnRujukan.add(MnRujukMasuk);

        jPopupMenu1.add(MnRujukan);

        MnPermintaan.setBackground(new java.awt.Color(255, 255, 255));
        MnPermintaan.setIcon(new javax.swing.ImageIcon(getClass().getResource("/picture/category.png"))); // NOI18N
        MnPermintaan.setText("Permintaan");
        MnPermintaan.setFont(new java.awt.Font("Tahoma", 0, 11)); // NOI18N
        MnPermintaan.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        MnPermintaan.setHorizontalTextPosition(javax.swing.SwingConstants.RIGHT);
        MnPermintaan.setIconTextGap(5);
        MnPermintaan.setName("MnPermintaan"); // NOI18N
        MnPermintaan.setOpaque(true);
        MnPermintaan.setPreferredSize(new java.awt.Dimension(220, 26));

        MnOrderLab.setBackground(new java.awt.Color(255, 255, 255));
        MnOrderLab.setFont(new java.awt.Font("Tahoma", 0, 11)); // NOI18N
        MnOrderLab.setIcon(new javax.swing.ImageIcon(getClass().getResource("/picture/category.png"))); // NOI18N
        MnOrderLab.setText("Pemeriksaan Laboratorium");
        MnOrderLab.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        MnOrderLab.setHorizontalTextPosition(javax.swing.SwingConstants.RIGHT);
        MnOrderLab.setIconTextGap(5);
        MnOrderLab.setName("MnOrderLab"); // NOI18N
        MnOrderLab.setPreferredSize(new java.awt.Dimension(190, 26));
        MnOrderLab.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                MnOrderLabActionPerformed(evt);
            }
        });
        MnPermintaan.add(MnOrderLab);

        MnOrderRad.setBackground(new java.awt.Color(255, 255, 255));
        MnOrderRad.setFont(new java.awt.Font("Tahoma", 0, 11)); // NOI18N
        MnOrderRad.setIcon(new javax.swing.ImageIcon(getClass().getResource("/picture/category.png"))); // NOI18N
        MnOrderRad.setText("Pemeriksaan Radiologi");
        MnOrderRad.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        MnOrderRad.setHorizontalTextPosition(javax.swing.SwingConstants.RIGHT);
        MnOrderRad.setIconTextGap(5);
        MnOrderRad.setName("MnOrderRad"); // NOI18N
        MnOrderRad.setPreferredSize(new java.awt.Dimension(190, 26));
        MnOrderRad.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                MnOrderRadActionPerformed(evt);
            }
        });
        MnPermintaan.add(MnOrderRad);

        jPopupMenu1.add(MnPermintaan);

        Mndpjp.setBackground(new java.awt.Color(255, 255, 255));
        Mndpjp.setIcon(new javax.swing.ImageIcon(getClass().getResource("/picture/category.png"))); // NOI18N
        Mndpjp.setText("DPJP Pasien");
        Mndpjp.setFont(new java.awt.Font("Tahoma", 0, 11)); // NOI18N
        Mndpjp.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        Mndpjp.setHorizontalTextPosition(javax.swing.SwingConstants.RIGHT);
        Mndpjp.setIconTextGap(5);
        Mndpjp.setName("Mndpjp"); // NOI18N
        Mndpjp.setOpaque(true);
        Mndpjp.setPreferredSize(new java.awt.Dimension(220, 26));

        MnDPJP.setBackground(new java.awt.Color(255, 255, 255));
        MnDPJP.setFont(new java.awt.Font("Tahoma", 0, 11)); // NOI18N
        MnDPJP.setIcon(new javax.swing.ImageIcon(getClass().getResource("/picture/category.png"))); // NOI18N
        MnDPJP.setText("Input DPJP Pasien Ranap");
        MnDPJP.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        MnDPJP.setHorizontalTextPosition(javax.swing.SwingConstants.RIGHT);
        MnDPJP.setIconTextGap(5);
        MnDPJP.setName("MnDPJP"); // NOI18N
        MnDPJP.setPreferredSize(new java.awt.Dimension(190, 26));
        MnDPJP.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                MnDPJPActionPerformed(evt);
            }
        });
        Mndpjp.add(MnDPJP);

        MnDPJPRanap.setBackground(new java.awt.Color(255, 255, 255));
        MnDPJPRanap.setFont(new java.awt.Font("Tahoma", 0, 11)); // NOI18N
        MnDPJPRanap.setIcon(new javax.swing.ImageIcon(getClass().getResource("/picture/category.png"))); // NOI18N
        MnDPJPRanap.setText("Tampilkan DPJP Pasien Ranap");
        MnDPJPRanap.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        MnDPJPRanap.setHorizontalTextPosition(javax.swing.SwingConstants.RIGHT);
        MnDPJPRanap.setIconTextGap(5);
        MnDPJPRanap.setName("MnDPJPRanap"); // NOI18N
        MnDPJPRanap.setPreferredSize(new java.awt.Dimension(190, 26));
        MnDPJPRanap.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                MnDPJPRanapActionPerformed(evt);
            }
        });
        Mndpjp.add(MnDPJPRanap);

        jPopupMenu1.add(Mndpjp);

        MnGantiData.setBackground(new java.awt.Color(255, 255, 255));
        MnGantiData.setIcon(new javax.swing.ImageIcon(getClass().getResource("/picture/category.png"))); // NOI18N
        MnGantiData.setText("Ganti Data");
        MnGantiData.setFont(new java.awt.Font("Tahoma", 0, 11)); // NOI18N
        MnGantiData.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        MnGantiData.setHorizontalTextPosition(javax.swing.SwingConstants.RIGHT);
        MnGantiData.setIconTextGap(5);
        MnGantiData.setName("MnGantiData"); // NOI18N
        MnGantiData.setOpaque(true);
        MnGantiData.setPreferredSize(new java.awt.Dimension(220, 26));

        MnDataMati.setBackground(new java.awt.Color(255, 255, 255));
        MnDataMati.setFont(new java.awt.Font("Tahoma", 0, 11)); // NOI18N
        MnDataMati.setIcon(new javax.swing.ImageIcon(getClass().getResource("/picture/category.png"))); // NOI18N
        MnDataMati.setText("Kematian");
        MnDataMati.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        MnDataMati.setHorizontalTextPosition(javax.swing.SwingConstants.RIGHT);
        MnDataMati.setIconTextGap(5);
        MnDataMati.setName("MnDataMati"); // NOI18N
        MnDataMati.setPreferredSize(new java.awt.Dimension(155, 26));
        MnDataMati.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                MnDataMatiActionPerformed(evt);
            }
        });
        MnGantiData.add(MnDataMati);

        MnPenjab.setBackground(new java.awt.Color(255, 255, 255));
        MnPenjab.setFont(new java.awt.Font("Tahoma", 0, 11)); // NOI18N
        MnPenjab.setIcon(new javax.swing.ImageIcon(getClass().getResource("/picture/category.png"))); // NOI18N
        MnPenjab.setText("Jenis Bayar");
        MnPenjab.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        MnPenjab.setHorizontalTextPosition(javax.swing.SwingConstants.RIGHT);
        MnPenjab.setIconTextGap(5);
        MnPenjab.setName("MnPenjab"); // NOI18N
        MnPenjab.setPreferredSize(new java.awt.Dimension(155, 26));
        MnPenjab.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                MnPenjabActionPerformed(evt);
            }
        });
        MnGantiData.add(MnPenjab);

        MnBatalPulang.setBackground(new java.awt.Color(255, 255, 255));
        MnBatalPulang.setFont(new java.awt.Font("Tahoma", 0, 11)); // NOI18N
        MnBatalPulang.setIcon(new javax.swing.ImageIcon(getClass().getResource("/picture/category.png"))); // NOI18N
        MnBatalPulang.setText("Batal Pulang");
        MnBatalPulang.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        MnBatalPulang.setHorizontalTextPosition(javax.swing.SwingConstants.RIGHT);
        MnBatalPulang.setIconTextGap(5);
        MnBatalPulang.setName("MnBatalPulang"); // NOI18N
        MnBatalPulang.setPreferredSize(new java.awt.Dimension(155, 26));
        MnBatalPulang.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                MnBatalPulangActionPerformed(evt);
            }
        });
        MnGantiData.add(MnBatalPulang);

        MnDiagnosaAwal.setBackground(new java.awt.Color(255, 255, 255));
        MnDiagnosaAwal.setFont(new java.awt.Font("Tahoma", 0, 11)); // NOI18N
        MnDiagnosaAwal.setIcon(new javax.swing.ImageIcon(getClass().getResource("/picture/category.png"))); // NOI18N
        MnDiagnosaAwal.setText("Diagnosa Awal");
        MnDiagnosaAwal.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        MnDiagnosaAwal.setHorizontalTextPosition(javax.swing.SwingConstants.RIGHT);
        MnDiagnosaAwal.setIconTextGap(5);
        MnDiagnosaAwal.setName("MnDiagnosaAwal"); // NOI18N
        MnDiagnosaAwal.setPreferredSize(new java.awt.Dimension(155, 26));
        MnDiagnosaAwal.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                MnDiagnosaAwalActionPerformed(evt);
            }
        });
        MnGantiData.add(MnDiagnosaAwal);

        MnStatusPulang.setBackground(new java.awt.Color(255, 255, 255));
        MnStatusPulang.setFont(new java.awt.Font("Tahoma", 0, 11)); // NOI18N
        MnStatusPulang.setIcon(new javax.swing.ImageIcon(getClass().getResource("/picture/category.png"))); // NOI18N
        MnStatusPulang.setText("Status Pulang");
        MnStatusPulang.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        MnStatusPulang.setHorizontalTextPosition(javax.swing.SwingConstants.RIGHT);
        MnStatusPulang.setIconTextGap(5);
        MnStatusPulang.setName("MnStatusPulang"); // NOI18N
        MnStatusPulang.setPreferredSize(new java.awt.Dimension(155, 26));
        MnStatusPulang.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                MnStatusPulangActionPerformed(evt);
            }
        });
        MnGantiData.add(MnStatusPulang);

        MnWaktuRegRalan.setBackground(new java.awt.Color(255, 255, 255));
        MnWaktuRegRalan.setFont(new java.awt.Font("Tahoma", 0, 11)); // NOI18N
        MnWaktuRegRalan.setIcon(new javax.swing.ImageIcon(getClass().getResource("/picture/category.png"))); // NOI18N
        MnWaktuRegRalan.setText("Waktu Reg. Ralan/IGD");
        MnWaktuRegRalan.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        MnWaktuRegRalan.setHorizontalTextPosition(javax.swing.SwingConstants.RIGHT);
        MnWaktuRegRalan.setIconTextGap(5);
        MnWaktuRegRalan.setName("MnWaktuRegRalan"); // NOI18N
        MnWaktuRegRalan.setPreferredSize(new java.awt.Dimension(155, 26));
        MnWaktuRegRalan.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                MnWaktuRegRalanActionPerformed(evt);
            }
        });
        MnGantiData.add(MnWaktuRegRalan);

        MnPindahTransaksi.setBackground(new java.awt.Color(255, 255, 255));
        MnPindahTransaksi.setFont(new java.awt.Font("Tahoma", 0, 11)); // NOI18N
        MnPindahTransaksi.setIcon(new javax.swing.ImageIcon(getClass().getResource("/picture/category.png"))); // NOI18N
        MnPindahTransaksi.setText("Pindahkan Transaksi");
        MnPindahTransaksi.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        MnPindahTransaksi.setHorizontalTextPosition(javax.swing.SwingConstants.RIGHT);
        MnPindahTransaksi.setIconTextGap(5);
        MnPindahTransaksi.setName("MnPindahTransaksi"); // NOI18N
        MnPindahTransaksi.setPreferredSize(new java.awt.Dimension(155, 26));
        MnPindahTransaksi.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                MnPindahTransaksiActionPerformed(evt);
            }
        });
        MnGantiData.add(MnPindahTransaksi);

        jPopupMenu1.add(MnGantiData);

        MnHapusData.setBackground(new java.awt.Color(255, 255, 255));
        MnHapusData.setIcon(new javax.swing.ImageIcon(getClass().getResource("/picture/category.png"))); // NOI18N
        MnHapusData.setText("Hapus Data");
        MnHapusData.setFont(new java.awt.Font("Tahoma", 0, 11)); // NOI18N
        MnHapusData.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        MnHapusData.setHorizontalTextPosition(javax.swing.SwingConstants.RIGHT);
        MnHapusData.setIconTextGap(5);
        MnHapusData.setName("MnHapusData"); // NOI18N
        MnHapusData.setOpaque(true);
        MnHapusData.setPreferredSize(new java.awt.Dimension(220, 26));

        MnHapusTagihanOperasi.setBackground(new java.awt.Color(255, 255, 255));
        MnHapusTagihanOperasi.setFont(new java.awt.Font("Tahoma", 0, 11)); // NOI18N
        MnHapusTagihanOperasi.setIcon(new javax.swing.ImageIcon(getClass().getResource("/picture/category.png"))); // NOI18N
        MnHapusTagihanOperasi.setText("Hapus Tagihan Operasi");
        MnHapusTagihanOperasi.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        MnHapusTagihanOperasi.setHorizontalTextPosition(javax.swing.SwingConstants.RIGHT);
        MnHapusTagihanOperasi.setName("MnHapusTagihanOperasi"); // NOI18N
        MnHapusTagihanOperasi.setPreferredSize(new java.awt.Dimension(170, 26));
        MnHapusTagihanOperasi.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                MnHapusTagihanOperasiActionPerformed(evt);
            }
        });
        MnHapusData.add(MnHapusTagihanOperasi);

        MnHapusObatOperasi.setBackground(new java.awt.Color(255, 255, 255));
        MnHapusObatOperasi.setFont(new java.awt.Font("Tahoma", 0, 11)); // NOI18N
        MnHapusObatOperasi.setIcon(new javax.swing.ImageIcon(getClass().getResource("/picture/category.png"))); // NOI18N
        MnHapusObatOperasi.setText("Hapus Obat Operasi");
        MnHapusObatOperasi.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        MnHapusObatOperasi.setHorizontalTextPosition(javax.swing.SwingConstants.RIGHT);
        MnHapusObatOperasi.setName("MnHapusObatOperasi"); // NOI18N
        MnHapusObatOperasi.setPreferredSize(new java.awt.Dimension(170, 26));
        MnHapusObatOperasi.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                MnHapusObatOperasiActionPerformed(evt);
            }
        });
        MnHapusData.add(MnHapusObatOperasi);

        MnHapusDataSalah.setBackground(new java.awt.Color(255, 255, 255));
        MnHapusDataSalah.setFont(new java.awt.Font("Tahoma", 0, 11)); // NOI18N
        MnHapusDataSalah.setIcon(new javax.swing.ImageIcon(getClass().getResource("/picture/category.png"))); // NOI18N
        MnHapusDataSalah.setText("Hapus Data Salah");
        MnHapusDataSalah.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        MnHapusDataSalah.setHorizontalTextPosition(javax.swing.SwingConstants.RIGHT);
        MnHapusDataSalah.setName("MnHapusDataSalah"); // NOI18N
        MnHapusDataSalah.setPreferredSize(new java.awt.Dimension(170, 26));
        MnHapusDataSalah.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                MnHapusDataSalahActionPerformed(evt);
            }
        });
        MnHapusData.add(MnHapusDataSalah);

        jPopupMenu1.add(MnHapusData);

        MnLaporan.setBackground(new java.awt.Color(255, 255, 255));
        MnLaporan.setIcon(new javax.swing.ImageIcon(getClass().getResource("/picture/category.png"))); // NOI18N
        MnLaporan.setText("Laporan & Surat");
        MnLaporan.setFont(new java.awt.Font("Tahoma", 0, 11)); // NOI18N
        MnLaporan.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        MnLaporan.setHorizontalTextPosition(javax.swing.SwingConstants.RIGHT);
        MnLaporan.setIconTextGap(5);
        MnLaporan.setName("MnLaporan"); // NOI18N
        MnLaporan.setOpaque(true);
        MnLaporan.setPreferredSize(new java.awt.Dimension(220, 26));

        MnRegisterPasien.setBackground(new java.awt.Color(255, 255, 255));
        MnRegisterPasien.setFont(new java.awt.Font("Tahoma", 0, 11)); // NOI18N
        MnRegisterPasien.setIcon(new javax.swing.ImageIcon(getClass().getResource("/picture/category.png"))); // NOI18N
        MnRegisterPasien.setText("Register Pasien");
        MnRegisterPasien.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        MnRegisterPasien.setHorizontalTextPosition(javax.swing.SwingConstants.RIGHT);
        MnRegisterPasien.setName("MnRegisterPasien"); // NOI18N
        MnRegisterPasien.setPreferredSize(new java.awt.Dimension(240, 26));
        MnRegisterPasien.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                MnRegisterPasienActionPerformed(evt);
            }
        });
        MnLaporan.add(MnRegisterPasien);

        MnRegisterPersalinan.setBackground(new java.awt.Color(255, 255, 255));
        MnRegisterPersalinan.setFont(new java.awt.Font("Tahoma", 0, 11)); // NOI18N
        MnRegisterPersalinan.setIcon(new javax.swing.ImageIcon(getClass().getResource("/picture/category.png"))); // NOI18N
        MnRegisterPersalinan.setText("Register Persalinan");
        MnRegisterPersalinan.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        MnRegisterPersalinan.setHorizontalTextPosition(javax.swing.SwingConstants.RIGHT);
        MnRegisterPersalinan.setName("MnRegisterPersalinan"); // NOI18N
        MnRegisterPersalinan.setPreferredSize(new java.awt.Dimension(240, 26));
        MnRegisterPersalinan.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                MnRegisterPersalinanActionPerformed(evt);
            }
        });
        MnLaporan.add(MnRegisterPersalinan);

        MnSuratKeteranganRawat.setBackground(new java.awt.Color(255, 255, 255));
        MnSuratKeteranganRawat.setIcon(new javax.swing.ImageIcon(getClass().getResource("/picture/category.png"))); // NOI18N
        MnSuratKeteranganRawat.setText("Surat Keterangan Perawatan");
        MnSuratKeteranganRawat.setFont(new java.awt.Font("Tahoma", 0, 11)); // NOI18N
        MnSuratKeteranganRawat.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        MnSuratKeteranganRawat.setHorizontalTextPosition(javax.swing.SwingConstants.RIGHT);
        MnSuratKeteranganRawat.setIconTextGap(5);
        MnSuratKeteranganRawat.setName("MnSuratKeteranganRawat"); // NOI18N
        MnSuratKeteranganRawat.setOpaque(true);
        MnSuratKeteranganRawat.setPreferredSize(new java.awt.Dimension(240, 26));

        MnCovid.setBackground(new java.awt.Color(255, 255, 255));
        MnCovid.setFont(new java.awt.Font("Tahoma", 0, 11)); // NOI18N
        MnCovid.setIcon(new javax.swing.ImageIcon(getClass().getResource("/picture/category.png"))); // NOI18N
        MnCovid.setText("Pasien COVID-19");
        MnCovid.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        MnCovid.setHorizontalTextPosition(javax.swing.SwingConstants.RIGHT);
        MnCovid.setIconTextGap(5);
        MnCovid.setName("MnCovid"); // NOI18N
        MnCovid.setPreferredSize(new java.awt.Dimension(140, 26));
        MnCovid.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                MnCovidActionPerformed(evt);
            }
        });
        MnSuratKeteranganRawat.add(MnCovid);

        MnNonCovid.setBackground(new java.awt.Color(255, 255, 255));
        MnNonCovid.setFont(new java.awt.Font("Tahoma", 0, 11)); // NOI18N
        MnNonCovid.setIcon(new javax.swing.ImageIcon(getClass().getResource("/picture/category.png"))); // NOI18N
        MnNonCovid.setText("Pasien Non COVID-19");
        MnNonCovid.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        MnNonCovid.setHorizontalTextPosition(javax.swing.SwingConstants.RIGHT);
        MnNonCovid.setName("MnNonCovid"); // NOI18N
        MnNonCovid.setPreferredSize(new java.awt.Dimension(140, 26));
        MnNonCovid.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                MnNonCovidActionPerformed(evt);
            }
        });
        MnSuratKeteranganRawat.add(MnNonCovid);

        MnLaporan.add(MnSuratKeteranganRawat);

        MnRincianObat.setBackground(new java.awt.Color(255, 255, 255));
        MnRincianObat.setFont(new java.awt.Font("Tahoma", 0, 11)); // NOI18N
        MnRincianObat.setIcon(new javax.swing.ImageIcon(getClass().getResource("/picture/category.png"))); // NOI18N
        MnRincianObat.setText("Rincian Penggunaan Obat");
        MnRincianObat.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        MnRincianObat.setHorizontalTextPosition(javax.swing.SwingConstants.RIGHT);
        MnRincianObat.setName("MnRincianObat"); // NOI18N
        MnRincianObat.setPreferredSize(new java.awt.Dimension(240, 26));
        MnRincianObat.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                MnRincianObatActionPerformed(evt);
            }
        });
        MnLaporan.add(MnRincianObat);

        MnRM2D.setBackground(new java.awt.Color(255, 255, 255));
        MnRM2D.setFont(new java.awt.Font("Tahoma", 0, 11)); // NOI18N
        MnRM2D.setIcon(new javax.swing.ImageIcon(getClass().getResource("/picture/category.png"))); // NOI18N
        MnRM2D.setText("Asesment Pasien IGD");
        MnRM2D.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        MnRM2D.setHorizontalTextPosition(javax.swing.SwingConstants.RIGHT);
        MnRM2D.setName("MnRM2D"); // NOI18N
        MnRM2D.setPreferredSize(new java.awt.Dimension(240, 26));
        MnRM2D.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                MnRM2DActionPerformed(evt);
            }
        });
        MnLaporan.add(MnRM2D);

        MnSensusRanap.setBackground(new java.awt.Color(255, 255, 255));
        MnSensusRanap.setFont(new java.awt.Font("Tahoma", 0, 11)); // NOI18N
        MnSensusRanap.setIcon(new javax.swing.ImageIcon(getClass().getResource("/picture/category.png"))); // NOI18N
        MnSensusRanap.setText("Sensus Harian Ranap");
        MnSensusRanap.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        MnSensusRanap.setHorizontalTextPosition(javax.swing.SwingConstants.RIGHT);
        MnSensusRanap.setName("MnSensusRanap"); // NOI18N
        MnSensusRanap.setPreferredSize(new java.awt.Dimension(240, 26));
        MnSensusRanap.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                MnSensusRanapActionPerformed(evt);
            }
        });
        MnLaporan.add(MnSensusRanap);

        MnDietMakanan.setBackground(new java.awt.Color(255, 255, 255));
        MnDietMakanan.setFont(new java.awt.Font("Tahoma", 0, 11)); // NOI18N
        MnDietMakanan.setIcon(new javax.swing.ImageIcon(getClass().getResource("/picture/category.png"))); // NOI18N
        MnDietMakanan.setText("Diet Makanan");
        MnDietMakanan.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        MnDietMakanan.setHorizontalTextPosition(javax.swing.SwingConstants.RIGHT);
        MnDietMakanan.setName("MnDietMakanan"); // NOI18N
        MnDietMakanan.setPreferredSize(new java.awt.Dimension(240, 26));
        MnDietMakanan.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                MnDietMakananActionPerformed(evt);
            }
        });
        MnLaporan.add(MnDietMakanan);

        MnStatusGZ.setBackground(new java.awt.Color(255, 255, 255));
        MnStatusGZ.setFont(new java.awt.Font("Tahoma", 0, 11)); // NOI18N
        MnStatusGZ.setIcon(new javax.swing.ImageIcon(getClass().getResource("/picture/category.png"))); // NOI18N
        MnStatusGZ.setText("Status Gizi Pasien");
        MnStatusGZ.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        MnStatusGZ.setHorizontalTextPosition(javax.swing.SwingConstants.RIGHT);
        MnStatusGZ.setName("MnStatusGZ"); // NOI18N
        MnStatusGZ.setPreferredSize(new java.awt.Dimension(240, 26));
        MnStatusGZ.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                MnStatusGZActionPerformed(evt);
            }
        });
        MnLaporan.add(MnStatusGZ);

        MnGiziBurukPx.setBackground(new java.awt.Color(255, 255, 255));
        MnGiziBurukPx.setFont(new java.awt.Font("Tahoma", 0, 11)); // NOI18N
        MnGiziBurukPx.setIcon(new javax.swing.ImageIcon(getClass().getResource("/picture/category.png"))); // NOI18N
        MnGiziBurukPx.setText("Gizi Buruk Pasien");
        MnGiziBurukPx.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        MnGiziBurukPx.setHorizontalTextPosition(javax.swing.SwingConstants.RIGHT);
        MnGiziBurukPx.setName("MnGiziBurukPx"); // NOI18N
        MnGiziBurukPx.setPreferredSize(new java.awt.Dimension(240, 26));
        MnGiziBurukPx.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                MnGiziBurukPxActionPerformed(evt);
            }
        });
        MnLaporan.add(MnGiziBurukPx);

        MnPencapaianAsuhanGZ.setBackground(new java.awt.Color(255, 255, 255));
        MnPencapaianAsuhanGZ.setFont(new java.awt.Font("Tahoma", 0, 11)); // NOI18N
        MnPencapaianAsuhanGZ.setIcon(new javax.swing.ImageIcon(getClass().getResource("/picture/category.png"))); // NOI18N
        MnPencapaianAsuhanGZ.setText("Pencapaian Asuhan Gizi");
        MnPencapaianAsuhanGZ.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        MnPencapaianAsuhanGZ.setHorizontalTextPosition(javax.swing.SwingConstants.RIGHT);
        MnPencapaianAsuhanGZ.setName("MnPencapaianAsuhanGZ"); // NOI18N
        MnPencapaianAsuhanGZ.setPreferredSize(new java.awt.Dimension(240, 26));
        MnPencapaianAsuhanGZ.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                MnPencapaianAsuhanGZActionPerformed(evt);
            }
        });
        MnLaporan.add(MnPencapaianAsuhanGZ);

        MnTilikBedah.setBackground(new java.awt.Color(255, 255, 255));
        MnTilikBedah.setFont(new java.awt.Font("Tahoma", 0, 11)); // NOI18N
        MnTilikBedah.setIcon(new javax.swing.ImageIcon(getClass().getResource("/picture/category.png"))); // NOI18N
        MnTilikBedah.setText("Daftar Tilik Keselamatan Bedah");
        MnTilikBedah.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        MnTilikBedah.setHorizontalTextPosition(javax.swing.SwingConstants.RIGHT);
        MnTilikBedah.setName("MnTilikBedah"); // NOI18N
        MnTilikBedah.setPreferredSize(new java.awt.Dimension(240, 26));
        MnTilikBedah.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                MnTilikBedahActionPerformed(evt);
            }
        });
        MnLaporan.add(MnTilikBedah);

        MnAsuhanGizi.setBackground(new java.awt.Color(255, 255, 255));
        MnAsuhanGizi.setFont(new java.awt.Font("Tahoma", 0, 11)); // NOI18N
        MnAsuhanGizi.setIcon(new javax.swing.ImageIcon(getClass().getResource("/picture/category.png"))); // NOI18N
        MnAsuhanGizi.setText("Assesment Gizi");
        MnAsuhanGizi.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        MnAsuhanGizi.setHorizontalTextPosition(javax.swing.SwingConstants.RIGHT);
        MnAsuhanGizi.setName("MnAsuhanGizi"); // NOI18N
        MnAsuhanGizi.setPreferredSize(new java.awt.Dimension(240, 26));
        MnAsuhanGizi.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                MnAsuhanGiziActionPerformed(evt);
            }
        });
        MnLaporan.add(MnAsuhanGizi);

        MnSrtAPS.setBackground(new java.awt.Color(255, 255, 255));
        MnSrtAPS.setFont(new java.awt.Font("Tahoma", 0, 11)); // NOI18N
        MnSrtAPS.setIcon(new javax.swing.ImageIcon(getClass().getResource("/picture/category.png"))); // NOI18N
        MnSrtAPS.setText("Surat Pernyataan APS");
        MnSrtAPS.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        MnSrtAPS.setHorizontalTextPosition(javax.swing.SwingConstants.RIGHT);
        MnSrtAPS.setName("MnSrtAPS"); // NOI18N
        MnSrtAPS.setPreferredSize(new java.awt.Dimension(240, 26));
        MnSrtAPS.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                MnSrtAPSActionPerformed(evt);
            }
        });
        MnLaporan.add(MnSrtAPS);

        MnSrtMati.setBackground(new java.awt.Color(255, 255, 255));
        MnSrtMati.setFont(new java.awt.Font("Tahoma", 0, 11)); // NOI18N
        MnSrtMati.setIcon(new javax.swing.ImageIcon(getClass().getResource("/picture/category.png"))); // NOI18N
        MnSrtMati.setText("Surat Keterangan Kematian");
        MnSrtMati.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        MnSrtMati.setHorizontalTextPosition(javax.swing.SwingConstants.RIGHT);
        MnSrtMati.setName("MnSrtMati"); // NOI18N
        MnSrtMati.setPreferredSize(new java.awt.Dimension(240, 26));
        MnSrtMati.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                MnSrtMatiActionPerformed(evt);
            }
        });
        MnLaporan.add(MnSrtMati);

        MnSrtMatiICU.setBackground(new java.awt.Color(255, 255, 255));
        MnSrtMatiICU.setFont(new java.awt.Font("Tahoma", 0, 11)); // NOI18N
        MnSrtMatiICU.setIcon(new javax.swing.ImageIcon(getClass().getResource("/picture/category.png"))); // NOI18N
        MnSrtMatiICU.setText("Surat Keterangan Kematian (ICU)");
        MnSrtMatiICU.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        MnSrtMatiICU.setHorizontalTextPosition(javax.swing.SwingConstants.RIGHT);
        MnSrtMatiICU.setName("MnSrtMatiICU"); // NOI18N
        MnSrtMatiICU.setPreferredSize(new java.awt.Dimension(240, 26));
        MnSrtMatiICU.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                MnSrtMatiICUActionPerformed(evt);
            }
        });
        MnLaporan.add(MnSrtMatiICU);

        MnFormulirKematian.setBackground(new java.awt.Color(255, 255, 255));
        MnFormulirKematian.setFont(new java.awt.Font("Tahoma", 0, 11)); // NOI18N
        MnFormulirKematian.setIcon(new javax.swing.ImageIcon(getClass().getResource("/picture/category.png"))); // NOI18N
        MnFormulirKematian.setText("Formulir Keterangan Penyebab Kematian");
        MnFormulirKematian.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        MnFormulirKematian.setHorizontalTextPosition(javax.swing.SwingConstants.RIGHT);
        MnFormulirKematian.setName("MnFormulirKematian"); // NOI18N
        MnFormulirKematian.setPreferredSize(new java.awt.Dimension(240, 26));
        MnFormulirKematian.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                MnFormulirKematianActionPerformed(evt);
            }
        });
        MnLaporan.add(MnFormulirKematian);

        MnPengantarPulang.setBackground(new java.awt.Color(255, 255, 255));
        MnPengantarPulang.setFont(new java.awt.Font("Tahoma", 0, 11)); // NOI18N
        MnPengantarPulang.setIcon(new javax.swing.ImageIcon(getClass().getResource("/picture/category.png"))); // NOI18N
        MnPengantarPulang.setText("Surat Pengantar Pulang");
        MnPengantarPulang.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        MnPengantarPulang.setHorizontalTextPosition(javax.swing.SwingConstants.RIGHT);
        MnPengantarPulang.setName("MnPengantarPulang"); // NOI18N
        MnPengantarPulang.setPreferredSize(new java.awt.Dimension(240, 26));
        MnPengantarPulang.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                MnPengantarPulangActionPerformed(evt);
            }
        });
        MnLaporan.add(MnPengantarPulang);

        MnManualSEPBPJS.setBackground(new java.awt.Color(255, 255, 255));
        MnManualSEPBPJS.setFont(new java.awt.Font("Tahoma", 0, 11)); // NOI18N
        MnManualSEPBPJS.setIcon(new javax.swing.ImageIcon(getClass().getResource("/picture/category.png"))); // NOI18N
        MnManualSEPBPJS.setText("SEP Manual Pasien BPJS Rawat Inap");
        MnManualSEPBPJS.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        MnManualSEPBPJS.setHorizontalTextPosition(javax.swing.SwingConstants.RIGHT);
        MnManualSEPBPJS.setName("MnManualSEPBPJS"); // NOI18N
        MnManualSEPBPJS.setPreferredSize(new java.awt.Dimension(240, 26));
        MnManualSEPBPJS.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                MnManualSEPBPJSActionPerformed(evt);
            }
        });
        MnLaporan.add(MnManualSEPBPJS);

        jPopupMenu1.add(MnLaporan);

        MnCetakKelengkapanInap.setBackground(new java.awt.Color(255, 255, 255));
        MnCetakKelengkapanInap.setIcon(new javax.swing.ImageIcon(getClass().getResource("/picture/b_print.png"))); // NOI18N
        MnCetakKelengkapanInap.setText("Cetak Kelengkapan Opname");
        MnCetakKelengkapanInap.setFont(new java.awt.Font("Tahoma", 0, 11)); // NOI18N
        MnCetakKelengkapanInap.setName("MnCetakKelengkapanInap"); // NOI18N
        MnCetakKelengkapanInap.setOpaque(true);
        MnCetakKelengkapanInap.setPreferredSize(new java.awt.Dimension(220, 26));

        MnKartu.setBackground(new java.awt.Color(255, 255, 255));
        MnKartu.setIcon(new javax.swing.ImageIcon(getClass().getResource("/picture/category.png"))); // NOI18N
        MnKartu.setText("Kartu Berobat Pasien (KIB)");
        MnKartu.setFont(new java.awt.Font("Tahoma", 0, 11)); // NOI18N
        MnKartu.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        MnKartu.setHorizontalTextPosition(javax.swing.SwingConstants.RIGHT);
        MnKartu.setIconTextGap(5);
        MnKartu.setName("MnKartu"); // NOI18N
        MnKartu.setOpaque(true);
        MnKartu.setPreferredSize(new java.awt.Dimension(210, 26));

        MnKartuUmum.setBackground(new java.awt.Color(255, 255, 255));
        MnKartuUmum.setFont(new java.awt.Font("Tahoma", 0, 11)); // NOI18N
        MnKartuUmum.setIcon(new javax.swing.ImageIcon(getClass().getResource("/picture/category.png"))); // NOI18N
        MnKartuUmum.setText("Pasien UMUM");
        MnKartuUmum.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        MnKartuUmum.setHorizontalTextPosition(javax.swing.SwingConstants.RIGHT);
        MnKartuUmum.setIconTextGap(5);
        MnKartuUmum.setName("MnKartuUmum"); // NOI18N
        MnKartuUmum.setPreferredSize(new java.awt.Dimension(140, 26));
        MnKartuUmum.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                MnKartuUmumActionPerformed(evt);
            }
        });
        MnKartu.add(MnKartuUmum);

        MnKartuNonUmum.setBackground(new java.awt.Color(255, 255, 255));
        MnKartuNonUmum.setFont(new java.awt.Font("Tahoma", 0, 11)); // NOI18N
        MnKartuNonUmum.setIcon(new javax.swing.ImageIcon(getClass().getResource("/picture/category.png"))); // NOI18N
        MnKartuNonUmum.setText("Pasien NON UMUM");
        MnKartuNonUmum.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        MnKartuNonUmum.setHorizontalTextPosition(javax.swing.SwingConstants.RIGHT);
        MnKartuNonUmum.setIconTextGap(5);
        MnKartuNonUmum.setName("MnKartuNonUmum"); // NOI18N
        MnKartuNonUmum.setPreferredSize(new java.awt.Dimension(140, 26));
        MnKartuNonUmum.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                MnKartuNonUmumActionPerformed(evt);
            }
        });
        MnKartu.add(MnKartuNonUmum);

        MnCetakKelengkapanInap.add(MnKartu);

        MnGelangDewasaAnak.setBackground(new java.awt.Color(255, 255, 255));
        MnGelangDewasaAnak.setIcon(new javax.swing.ImageIcon(getClass().getResource("/picture/category.png"))); // NOI18N
        MnGelangDewasaAnak.setText("Gelang DEWASA & ANAK-ANAK");
        MnGelangDewasaAnak.setFont(new java.awt.Font("Tahoma", 0, 11)); // NOI18N
        MnGelangDewasaAnak.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        MnGelangDewasaAnak.setHorizontalTextPosition(javax.swing.SwingConstants.RIGHT);
        MnGelangDewasaAnak.setIconTextGap(5);
        MnGelangDewasaAnak.setName("MnGelangDewasaAnak"); // NOI18N
        MnGelangDewasaAnak.setOpaque(true);
        MnGelangDewasaAnak.setPreferredSize(new java.awt.Dimension(210, 26));

        MnPrinterBaru.setBackground(new java.awt.Color(255, 255, 255));
        MnPrinterBaru.setFont(new java.awt.Font("Tahoma", 0, 11)); // NOI18N
        MnPrinterBaru.setIcon(new javax.swing.ImageIcon(getClass().getResource("/picture/b_print.png"))); // NOI18N
        MnPrinterBaru.setText("Printer BARU");
        MnPrinterBaru.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        MnPrinterBaru.setHorizontalTextPosition(javax.swing.SwingConstants.RIGHT);
        MnPrinterBaru.setIconTextGap(5);
        MnPrinterBaru.setName("MnPrinterBaru"); // NOI18N
        MnPrinterBaru.setPreferredSize(new java.awt.Dimension(125, 26));
        MnPrinterBaru.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                MnPrinterBaruActionPerformed(evt);
            }
        });
        MnGelangDewasaAnak.add(MnPrinterBaru);

        MnPrinterLama.setBackground(new java.awt.Color(255, 255, 255));
        MnPrinterLama.setFont(new java.awt.Font("Tahoma", 0, 11)); // NOI18N
        MnPrinterLama.setIcon(new javax.swing.ImageIcon(getClass().getResource("/picture/b_print.png"))); // NOI18N
        MnPrinterLama.setText("Printer LAMA");
        MnPrinterLama.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        MnPrinterLama.setHorizontalTextPosition(javax.swing.SwingConstants.RIGHT);
        MnPrinterLama.setIconTextGap(5);
        MnPrinterLama.setName("MnPrinterLama"); // NOI18N
        MnPrinterLama.setPreferredSize(new java.awt.Dimension(125, 26));
        MnPrinterLama.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                MnPrinterLamaActionPerformed(evt);
            }
        });
        MnGelangDewasaAnak.add(MnPrinterLama);

        MnCetakKelengkapanInap.add(MnGelangDewasaAnak);

        MnGelangBayi.setBackground(new java.awt.Color(255, 255, 255));
        MnGelangBayi.setIcon(new javax.swing.ImageIcon(getClass().getResource("/picture/category.png"))); // NOI18N
        MnGelangBayi.setText("Gelang BAYI");
        MnGelangBayi.setFont(new java.awt.Font("Tahoma", 0, 11)); // NOI18N
        MnGelangBayi.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        MnGelangBayi.setHorizontalTextPosition(javax.swing.SwingConstants.RIGHT);
        MnGelangBayi.setIconTextGap(5);
        MnGelangBayi.setName("MnGelangBayi"); // NOI18N
        MnGelangBayi.setOpaque(true);
        MnGelangBayi.setPreferredSize(new java.awt.Dimension(210, 26));

        MnPrinterBaru1.setBackground(new java.awt.Color(255, 255, 255));
        MnPrinterBaru1.setFont(new java.awt.Font("Tahoma", 0, 11)); // NOI18N
        MnPrinterBaru1.setIcon(new javax.swing.ImageIcon(getClass().getResource("/picture/b_print.png"))); // NOI18N
        MnPrinterBaru1.setText("Printer BARU");
        MnPrinterBaru1.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        MnPrinterBaru1.setHorizontalTextPosition(javax.swing.SwingConstants.RIGHT);
        MnPrinterBaru1.setIconTextGap(5);
        MnPrinterBaru1.setName("MnPrinterBaru1"); // NOI18N
        MnPrinterBaru1.setPreferredSize(new java.awt.Dimension(125, 26));
        MnPrinterBaru1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                MnPrinterBaru1ActionPerformed(evt);
            }
        });
        MnGelangBayi.add(MnPrinterBaru1);

        MnPrinterLama1.setBackground(new java.awt.Color(255, 255, 255));
        MnPrinterLama1.setFont(new java.awt.Font("Tahoma", 0, 11)); // NOI18N
        MnPrinterLama1.setIcon(new javax.swing.ImageIcon(getClass().getResource("/picture/b_print.png"))); // NOI18N
        MnPrinterLama1.setText("Printer LAMA");
        MnPrinterLama1.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        MnPrinterLama1.setHorizontalTextPosition(javax.swing.SwingConstants.RIGHT);
        MnPrinterLama1.setIconTextGap(5);
        MnPrinterLama1.setName("MnPrinterLama1"); // NOI18N
        MnPrinterLama1.setPreferredSize(new java.awt.Dimension(125, 26));
        MnPrinterLama1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                MnPrinterLama1ActionPerformed(evt);
            }
        });
        MnGelangBayi.add(MnPrinterLama1);

        MnCetakKelengkapanInap.add(MnGelangBayi);

        MnBarcodeRM.setBackground(new java.awt.Color(255, 255, 255));
        MnBarcodeRM.setIcon(new javax.swing.ImageIcon(getClass().getResource("/picture/category.png"))); // NOI18N
        MnBarcodeRM.setText("Barcode");
        MnBarcodeRM.setFont(new java.awt.Font("Tahoma", 0, 11)); // NOI18N
        MnBarcodeRM.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        MnBarcodeRM.setHorizontalTextPosition(javax.swing.SwingConstants.RIGHT);
        MnBarcodeRM.setIconTextGap(5);
        MnBarcodeRM.setName("MnBarcodeRM"); // NOI18N
        MnBarcodeRM.setOpaque(true);
        MnBarcodeRM.setPreferredSize(new java.awt.Dimension(210, 26));

        MnBarcodeRM1.setBackground(new java.awt.Color(255, 255, 255));
        MnBarcodeRM1.setFont(new java.awt.Font("Tahoma", 0, 11)); // NOI18N
        MnBarcodeRM1.setIcon(new javax.swing.ImageIcon(getClass().getResource("/picture/b_print.png"))); // NOI18N
        MnBarcodeRM1.setText("KERTAS BESAR");
        MnBarcodeRM1.setName("MnBarcodeRM1"); // NOI18N
        MnBarcodeRM1.setPreferredSize(new java.awt.Dimension(125, 26));
        MnBarcodeRM1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                MnBarcodeRM1ActionPerformed(evt);
            }
        });
        MnBarcodeRM.add(MnBarcodeRM1);

        MnBarcodeRM2.setBackground(new java.awt.Color(255, 255, 255));
        MnBarcodeRM2.setFont(new java.awt.Font("Tahoma", 0, 11)); // NOI18N
        MnBarcodeRM2.setIcon(new javax.swing.ImageIcon(getClass().getResource("/picture/b_print.png"))); // NOI18N
        MnBarcodeRM2.setText("KERTAS KECIL");
        MnBarcodeRM2.setName("MnBarcodeRM2"); // NOI18N
        MnBarcodeRM2.setPreferredSize(new java.awt.Dimension(125, 26));
        MnBarcodeRM2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                MnBarcodeRM2ActionPerformed(evt);
            }
        });
        MnBarcodeRM.add(MnBarcodeRM2);

        MnCetakKelengkapanInap.add(MnBarcodeRM);

        MnLabelRM.setBackground(new java.awt.Color(255, 255, 255));
        MnLabelRM.setIcon(new javax.swing.ImageIcon(getClass().getResource("/picture/category.png"))); // NOI18N
        MnLabelRM.setText("Label Identitas");
        MnLabelRM.setFont(new java.awt.Font("Tahoma", 0, 11)); // NOI18N
        MnLabelRM.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        MnLabelRM.setHorizontalTextPosition(javax.swing.SwingConstants.RIGHT);
        MnLabelRM.setIconTextGap(5);
        MnLabelRM.setName("MnLabelRM"); // NOI18N
        MnLabelRM.setOpaque(true);
        MnLabelRM.setPreferredSize(new java.awt.Dimension(210, 26));

        MnLabelRM1.setBackground(new java.awt.Color(255, 255, 255));
        MnLabelRM1.setFont(new java.awt.Font("Tahoma", 0, 11)); // NOI18N
        MnLabelRM1.setIcon(new javax.swing.ImageIcon(getClass().getResource("/picture/b_print.png"))); // NOI18N
        MnLabelRM1.setText("KERTAS BESAR");
        MnLabelRM1.setName("MnLabelRM1"); // NOI18N
        MnLabelRM1.setPreferredSize(new java.awt.Dimension(125, 26));
        MnLabelRM1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                MnLabelRM1ActionPerformed(evt);
            }
        });
        MnLabelRM.add(MnLabelRM1);

        MnLabelRM2.setBackground(new java.awt.Color(255, 255, 255));
        MnLabelRM2.setFont(new java.awt.Font("Tahoma", 0, 11)); // NOI18N
        MnLabelRM2.setIcon(new javax.swing.ImageIcon(getClass().getResource("/picture/b_print.png"))); // NOI18N
        MnLabelRM2.setText("KERTAS KECIL");
        MnLabelRM2.setName("MnLabelRM2"); // NOI18N
        MnLabelRM2.setPreferredSize(new java.awt.Dimension(125, 26));
        MnLabelRM2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                MnLabelRM2ActionPerformed(evt);
            }
        });
        MnLabelRM.add(MnLabelRM2);

        MnCetakKelengkapanInap.add(MnLabelRM);

        MnTracerInap.setBackground(new java.awt.Color(255, 255, 255));
        MnTracerInap.setFont(new java.awt.Font("Tahoma", 0, 11)); // NOI18N
        MnTracerInap.setIcon(new javax.swing.ImageIcon(getClass().getResource("/picture/b_print.png"))); // NOI18N
        MnTracerInap.setText("Tracer Rawat Inap");
        MnTracerInap.setName("MnTracerInap"); // NOI18N
        MnTracerInap.setPreferredSize(new java.awt.Dimension(210, 26));
        MnTracerInap.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                MnTracerInapActionPerformed(evt);
            }
        });
        MnCetakKelengkapanInap.add(MnTracerInap);

        jSeparator1.setName("jSeparator1"); // NOI18N
        MnCetakKelengkapanInap.add(jSeparator1);

        MnLabelPxRanap3.setBackground(new java.awt.Color(255, 255, 255));
        MnLabelPxRanap3.setIcon(new javax.swing.ImageIcon(getClass().getResource("/picture/category.png"))); // NOI18N
        MnLabelPxRanap3.setText("Label Pasien (4,9 x 1,9 Cm)");
        MnLabelPxRanap3.setFont(new java.awt.Font("Tahoma", 0, 11)); // NOI18N
        MnLabelPxRanap3.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        MnLabelPxRanap3.setHorizontalTextPosition(javax.swing.SwingConstants.RIGHT);
        MnLabelPxRanap3.setIconTextGap(5);
        MnLabelPxRanap3.setName("MnLabelPxRanap3"); // NOI18N
        MnLabelPxRanap3.setOpaque(true);
        MnLabelPxRanap3.setPreferredSize(new java.awt.Dimension(210, 26));

        MrkChampion.setBackground(new java.awt.Color(255, 255, 255));
        MrkChampion.setFont(new java.awt.Font("Tahoma", 0, 11)); // NOI18N
        MrkChampion.setIcon(new javax.swing.ImageIcon(getClass().getResource("/picture/category.png"))); // NOI18N
        MrkChampion.setText("Merek CHAMPION");
        MrkChampion.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        MrkChampion.setHorizontalTextPosition(javax.swing.SwingConstants.RIGHT);
        MrkChampion.setIconTextGap(5);
        MrkChampion.setName("MrkChampion"); // NOI18N
        MrkChampion.setPreferredSize(new java.awt.Dimension(175, 26));
        MrkChampion.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                MrkChampionActionPerformed(evt);
            }
        });
        MnLabelPxRanap3.add(MrkChampion);

        MrkAjp.setBackground(new java.awt.Color(255, 255, 255));
        MrkAjp.setFont(new java.awt.Font("Tahoma", 0, 11)); // NOI18N
        MrkAjp.setIcon(new javax.swing.ImageIcon(getClass().getResource("/picture/category.png"))); // NOI18N
        MrkAjp.setText("Merek AJP BRAND");
        MrkAjp.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        MrkAjp.setHorizontalTextPosition(javax.swing.SwingConstants.RIGHT);
        MrkAjp.setIconTextGap(5);
        MrkAjp.setName("MrkAjp"); // NOI18N
        MrkAjp.setPreferredSize(new java.awt.Dimension(175, 26));
        MrkAjp.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                MrkAjpActionPerformed(evt);
            }
        });
        MnLabelPxRanap3.add(MrkAjp);

        MrkCox.setBackground(new java.awt.Color(255, 255, 255));
        MrkCox.setFont(new java.awt.Font("Tahoma", 0, 11)); // NOI18N
        MrkCox.setIcon(new javax.swing.ImageIcon(getClass().getResource("/picture/category.png"))); // NOI18N
        MrkCox.setText("Merek COX");
        MrkCox.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        MrkCox.setHorizontalTextPosition(javax.swing.SwingConstants.RIGHT);
        MrkCox.setIconTextGap(5);
        MrkCox.setName("MrkCox"); // NOI18N
        MrkCox.setPreferredSize(new java.awt.Dimension(175, 26));
        MrkCox.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                MrkCoxActionPerformed(evt);
            }
        });
        MnLabelPxRanap3.add(MrkCox);

        MrkAlfa.setBackground(new java.awt.Color(255, 255, 255));
        MrkAlfa.setFont(new java.awt.Font("Tahoma", 0, 11)); // NOI18N
        MrkAlfa.setIcon(new javax.swing.ImageIcon(getClass().getResource("/picture/category.png"))); // NOI18N
        MrkAlfa.setText("Merek ALFA PREMIUM");
        MrkAlfa.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        MrkAlfa.setHorizontalTextPosition(javax.swing.SwingConstants.RIGHT);
        MrkAlfa.setIconTextGap(5);
        MrkAlfa.setName("MrkAlfa"); // NOI18N
        MrkAlfa.setPreferredSize(new java.awt.Dimension(175, 26));
        MrkAlfa.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                MrkAlfaActionPerformed(evt);
            }
        });
        MnLabelPxRanap3.add(MrkAlfa);

        MrkOlean.setBackground(new java.awt.Color(255, 255, 255));
        MrkOlean.setFont(new java.awt.Font("Tahoma", 0, 11)); // NOI18N
        MrkOlean.setIcon(new javax.swing.ImageIcon(getClass().getResource("/picture/category.png"))); // NOI18N
        MrkOlean.setText("Merek OLEAN CITY BRAND");
        MrkOlean.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        MrkOlean.setHorizontalTextPosition(javax.swing.SwingConstants.RIGHT);
        MrkOlean.setIconTextGap(5);
        MrkOlean.setName("MrkOlean"); // NOI18N
        MrkOlean.setPreferredSize(new java.awt.Dimension(175, 26));
        MrkOlean.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                MrkOleanActionPerformed(evt);
            }
        });
        MnLabelPxRanap3.add(MrkOlean);

        MrkKojico.setBackground(new java.awt.Color(255, 255, 255));
        MrkKojico.setFont(new java.awt.Font("Tahoma", 0, 11)); // NOI18N
        MrkKojico.setIcon(new javax.swing.ImageIcon(getClass().getResource("/picture/category.png"))); // NOI18N
        MrkKojico.setText("Merek KOJICO BRAND");
        MrkKojico.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        MrkKojico.setHorizontalTextPosition(javax.swing.SwingConstants.RIGHT);
        MrkKojico.setIconTextGap(5);
        MrkKojico.setName("MrkKojico"); // NOI18N
        MrkKojico.setPreferredSize(new java.awt.Dimension(175, 26));
        MrkKojico.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                MrkKojicoActionPerformed(evt);
            }
        });
        MnLabelPxRanap3.add(MrkKojico);

        MnCetakKelengkapanInap.add(MnLabelPxRanap3);

        MnLabelPxRanap1.setBackground(new java.awt.Color(255, 255, 255));
        MnLabelPxRanap1.setFont(new java.awt.Font("Tahoma", 0, 11)); // NOI18N
        MnLabelPxRanap1.setIcon(new javax.swing.ImageIcon(getClass().getResource("/picture/b_print.png"))); // NOI18N
        MnLabelPxRanap1.setText("Label Pasien (3,9 x 1,9 Cm)");
        MnLabelPxRanap1.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        MnLabelPxRanap1.setHorizontalTextPosition(javax.swing.SwingConstants.RIGHT);
        MnLabelPxRanap1.setName("MnLabelPxRanap1"); // NOI18N
        MnLabelPxRanap1.setPreferredSize(new java.awt.Dimension(210, 26));
        MnLabelPxRanap1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                MnLabelPxRanap1ActionPerformed(evt);
            }
        });
        MnCetakKelengkapanInap.add(MnLabelPxRanap1);

        MnLabelPxRanap2.setBackground(new java.awt.Color(255, 255, 255));
        MnLabelPxRanap2.setFont(new java.awt.Font("Tahoma", 0, 11)); // NOI18N
        MnLabelPxRanap2.setIcon(new javax.swing.ImageIcon(getClass().getResource("/picture/b_print.png"))); // NOI18N
        MnLabelPxRanap2.setText("Label Pasien (6,4 x 3,2 Cm)");
        MnLabelPxRanap2.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        MnLabelPxRanap2.setHorizontalTextPosition(javax.swing.SwingConstants.RIGHT);
        MnLabelPxRanap2.setName("MnLabelPxRanap2"); // NOI18N
        MnLabelPxRanap2.setPreferredSize(new java.awt.Dimension(210, 26));
        MnLabelPxRanap2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                MnLabelPxRanap2ActionPerformed(evt);
            }
        });
        MnCetakKelengkapanInap.add(MnLabelPxRanap2);

        jPopupMenu1.add(MnCetakKelengkapanInap);

        MnBridging.setBackground(new java.awt.Color(255, 255, 255));
        MnBridging.setIcon(new javax.swing.ImageIcon(getClass().getResource("/picture/category.png"))); // NOI18N
        MnBridging.setText("Bridging");
        MnBridging.setFont(new java.awt.Font("Tahoma", 0, 11)); // NOI18N
        MnBridging.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        MnBridging.setHorizontalTextPosition(javax.swing.SwingConstants.RIGHT);
        MnBridging.setIconTextGap(5);
        MnBridging.setName("MnBridging"); // NOI18N
        MnBridging.setOpaque(true);
        MnBridging.setPreferredSize(new java.awt.Dimension(220, 26));
        MnBridging.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                MnBridgingActionPerformed(evt);
            }
        });

        ppPasienCorona.setBackground(new java.awt.Color(255, 255, 255));
        ppPasienCorona.setFont(new java.awt.Font("Tahoma", 0, 11)); // NOI18N
        ppPasienCorona.setForeground(new java.awt.Color(50, 50, 50));
        ppPasienCorona.setIcon(new javax.swing.ImageIcon(getClass().getResource("/picture/category.png"))); // NOI18N
        ppPasienCorona.setText("Pasien Corona Kemenkes");
        ppPasienCorona.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        ppPasienCorona.setHorizontalTextPosition(javax.swing.SwingConstants.RIGHT);
        ppPasienCorona.setName("ppPasienCorona"); // NOI18N
        ppPasienCorona.setPreferredSize(new java.awt.Dimension(190, 26));
        ppPasienCorona.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                ppPasienCoronaBtnPrintActionPerformed(evt);
            }
        });
        MnBridging.add(ppPasienCorona);

        MnSEP.setBackground(new java.awt.Color(255, 255, 255));
        MnSEP.setFont(new java.awt.Font("Tahoma", 0, 11)); // NOI18N
        MnSEP.setIcon(new javax.swing.ImageIcon(getClass().getResource("/picture/category.png"))); // NOI18N
        MnSEP.setText("Bridging SEP BPJS");
        MnSEP.setFocusPainted(true);
        MnSEP.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        MnSEP.setHorizontalTextPosition(javax.swing.SwingConstants.RIGHT);
        MnSEP.setIconTextGap(5);
        MnSEP.setName("MnSEP"); // NOI18N
        MnSEP.setPreferredSize(new java.awt.Dimension(190, 26));
        MnSEP.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                MnSEPActionPerformed(evt);
            }
        });
        MnBridging.add(MnSEP);

        ppSuratKontrol.setBackground(new java.awt.Color(255, 255, 255));
        ppSuratKontrol.setFont(new java.awt.Font("Tahoma", 0, 11)); // NOI18N
        ppSuratKontrol.setIcon(new javax.swing.ImageIcon(getClass().getResource("/picture/category.png"))); // NOI18N
        ppSuratKontrol.setText("Rencana Kontrol/SPRI BPJS");
        ppSuratKontrol.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        ppSuratKontrol.setHorizontalTextPosition(javax.swing.SwingConstants.RIGHT);
        ppSuratKontrol.setName("ppSuratKontrol"); // NOI18N
        ppSuratKontrol.setPreferredSize(new java.awt.Dimension(190, 26));
        ppSuratKontrol.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                ppSuratKontrolBtnPrintActionPerformed(evt);
            }
        });
        MnBridging.add(ppSuratKontrol);

        MnSJP.setBackground(new java.awt.Color(255, 255, 255));
        MnSJP.setFont(new java.awt.Font("Tahoma", 0, 11)); // NOI18N
        MnSJP.setIcon(new javax.swing.ImageIcon(getClass().getResource("/picture/category.png"))); // NOI18N
        MnSJP.setText("Bridging SJP Inhealth");
        MnSJP.setFocusPainted(true);
        MnSJP.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        MnSJP.setHorizontalTextPosition(javax.swing.SwingConstants.RIGHT);
        MnSJP.setIconTextGap(5);
        MnSJP.setName("MnSJP"); // NOI18N
        MnSJP.setPreferredSize(new java.awt.Dimension(190, 26));
        MnSJP.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                MnSJPActionPerformed(evt);
            }
        });
        MnBridging.add(MnSJP);

        MnSEPJamkesda.setBackground(new java.awt.Color(255, 255, 255));
        MnSEPJamkesda.setFont(new java.awt.Font("Tahoma", 0, 11)); // NOI18N
        MnSEPJamkesda.setIcon(new javax.swing.ImageIcon(getClass().getResource("/picture/category.png"))); // NOI18N
        MnSEPJamkesda.setText("Bridging SEP Jamkesda");
        MnSEPJamkesda.setFocusPainted(true);
        MnSEPJamkesda.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        MnSEPJamkesda.setHorizontalTextPosition(javax.swing.SwingConstants.RIGHT);
        MnSEPJamkesda.setIconTextGap(5);
        MnSEPJamkesda.setName("MnSEPJamkesda"); // NOI18N
        MnSEPJamkesda.setPreferredSize(new java.awt.Dimension(190, 26));
        MnSEPJamkesda.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                MnSEPJamkesdaActionPerformed(evt);
            }
        });
        MnBridging.add(MnSEPJamkesda);

        MnSEPJampersal.setBackground(new java.awt.Color(255, 255, 255));
        MnSEPJampersal.setFont(new java.awt.Font("Tahoma", 0, 11)); // NOI18N
        MnSEPJampersal.setIcon(new javax.swing.ImageIcon(getClass().getResource("/picture/category.png"))); // NOI18N
        MnSEPJampersal.setText("Bridging SEP Jampersal");
        MnSEPJampersal.setFocusPainted(true);
        MnSEPJampersal.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        MnSEPJampersal.setHorizontalTextPosition(javax.swing.SwingConstants.RIGHT);
        MnSEPJampersal.setIconTextGap(5);
        MnSEPJampersal.setName("MnSEPJampersal"); // NOI18N
        MnSEPJampersal.setPreferredSize(new java.awt.Dimension(190, 26));
        MnSEPJampersal.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                MnSEPJampersalActionPerformed(evt);
            }
        });
        MnBridging.add(MnSEPJampersal);

        MnRujukSisrute.setBackground(new java.awt.Color(255, 255, 255));
        MnRujukSisrute.setFont(new java.awt.Font("Tahoma", 0, 11)); // NOI18N
        MnRujukSisrute.setIcon(new javax.swing.ImageIcon(getClass().getResource("/picture/category.png"))); // NOI18N
        MnRujukSisrute.setText("Rujuk Keluar Via Sisrute");
        MnRujukSisrute.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        MnRujukSisrute.setHorizontalTextPosition(javax.swing.SwingConstants.RIGHT);
        MnRujukSisrute.setIconTextGap(5);
        MnRujukSisrute.setName("MnRujukSisrute"); // NOI18N
        MnRujukSisrute.setPreferredSize(new java.awt.Dimension(190, 26));
        MnRujukSisrute.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                MnRujukSisruteActionPerformed(evt);
            }
        });
        MnBridging.add(MnRujukSisrute);

        jPopupMenu1.add(MnBridging);

        MnDataBPJS.setBackground(new java.awt.Color(255, 255, 255));
        MnDataBPJS.setIcon(new javax.swing.ImageIcon(getClass().getResource("/picture/category.png"))); // NOI18N
        MnDataBPJS.setText("Data BPJS");
        MnDataBPJS.setFont(new java.awt.Font("Tahoma", 0, 11)); // NOI18N
        MnDataBPJS.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        MnDataBPJS.setHorizontalTextPosition(javax.swing.SwingConstants.RIGHT);
        MnDataBPJS.setIconTextGap(5);
        MnDataBPJS.setName("MnDataBPJS"); // NOI18N
        MnDataBPJS.setOpaque(true);
        MnDataBPJS.setPreferredSize(new java.awt.Dimension(220, 26));

        MnLihatSEP.setBackground(new java.awt.Color(255, 255, 255));
        MnLihatSEP.setFont(new java.awt.Font("Tahoma", 0, 11)); // NOI18N
        MnLihatSEP.setIcon(new javax.swing.ImageIcon(getClass().getResource("/picture/category.png"))); // NOI18N
        MnLihatSEP.setText("Lihat No. SEP");
        MnLihatSEP.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        MnLihatSEP.setHorizontalTextPosition(javax.swing.SwingConstants.RIGHT);
        MnLihatSEP.setIconTextGap(5);
        MnLihatSEP.setName("MnLihatSEP"); // NOI18N
        MnLihatSEP.setPreferredSize(new java.awt.Dimension(150, 26));
        MnLihatSEP.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                MnLihatSEPActionPerformed(evt);
            }
        });
        MnDataBPJS.add(MnLihatSEP);

        MnSKDPBPJS.setBackground(new java.awt.Color(255, 255, 255));
        MnSKDPBPJS.setFont(new java.awt.Font("Tahoma", 0, 11)); // NOI18N
        MnSKDPBPJS.setIcon(new javax.swing.ImageIcon(getClass().getResource("/picture/category.png"))); // NOI18N
        MnSKDPBPJS.setText("SKDP BPJS");
        MnSKDPBPJS.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        MnSKDPBPJS.setHorizontalTextPosition(javax.swing.SwingConstants.RIGHT);
        MnSKDPBPJS.setIconTextGap(5);
        MnSKDPBPJS.setName("MnSKDPBPJS"); // NOI18N
        MnSKDPBPJS.setPreferredSize(new java.awt.Dimension(150, 26));
        MnSKDPBPJS.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                MnSKDPBPJSActionPerformed(evt);
            }
        });
        MnDataBPJS.add(MnSKDPBPJS);

        MnSelisihTarif.setBackground(new java.awt.Color(255, 255, 255));
        MnSelisihTarif.setFont(new java.awt.Font("Tahoma", 0, 11)); // NOI18N
        MnSelisihTarif.setIcon(new javax.swing.ImageIcon(getClass().getResource("/picture/category.png"))); // NOI18N
        MnSelisihTarif.setText("Selisih Tarif INACBG");
        MnSelisihTarif.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        MnSelisihTarif.setHorizontalTextPosition(javax.swing.SwingConstants.RIGHT);
        MnSelisihTarif.setIconTextGap(5);
        MnSelisihTarif.setName("MnSelisihTarif"); // NOI18N
        MnSelisihTarif.setPreferredSize(new java.awt.Dimension(150, 26));
        MnSelisihTarif.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                MnSelisihTarifActionPerformed(evt);
            }
        });
        MnDataBPJS.add(MnSelisihTarif);

        jPopupMenu1.add(MnDataBPJS);

        MnInputData.setBackground(new java.awt.Color(255, 255, 255));
        MnInputData.setIcon(new javax.swing.ImageIcon(getClass().getResource("/picture/category.png"))); // NOI18N
        MnInputData.setText("Input Data Tambahan");
        MnInputData.setFont(new java.awt.Font("Tahoma", 0, 11)); // NOI18N
        MnInputData.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        MnInputData.setHorizontalTextPosition(javax.swing.SwingConstants.RIGHT);
        MnInputData.setIconTextGap(5);
        MnInputData.setName("MnInputData"); // NOI18N
        MnInputData.setOpaque(true);
        MnInputData.setPreferredSize(new java.awt.Dimension(220, 26));

        MnAssesmenAsuhanGizi.setBackground(new java.awt.Color(255, 255, 255));
        MnAssesmenAsuhanGizi.setFont(new java.awt.Font("Tahoma", 0, 11)); // NOI18N
        MnAssesmenAsuhanGizi.setIcon(new javax.swing.ImageIcon(getClass().getResource("/picture/category.png"))); // NOI18N
        MnAssesmenAsuhanGizi.setText("Assesment (Asuhan Gizi)");
        MnAssesmenAsuhanGizi.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        MnAssesmenAsuhanGizi.setHorizontalTextPosition(javax.swing.SwingConstants.RIGHT);
        MnAssesmenAsuhanGizi.setIconTextGap(5);
        MnAssesmenAsuhanGizi.setName("MnAssesmenAsuhanGizi"); // NOI18N
        MnAssesmenAsuhanGizi.setPreferredSize(new java.awt.Dimension(210, 26));
        MnAssesmenAsuhanGizi.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                MnAssesmenAsuhanGiziActionPerformed(evt);
            }
        });
        MnInputData.add(MnAssesmenAsuhanGizi);

        MnMonevAsuhanGizi.setBackground(new java.awt.Color(255, 255, 255));
        MnMonevAsuhanGizi.setFont(new java.awt.Font("Tahoma", 0, 11)); // NOI18N
        MnMonevAsuhanGizi.setIcon(new javax.swing.ImageIcon(getClass().getResource("/picture/category.png"))); // NOI18N
        MnMonevAsuhanGizi.setText("Monitoring Evaluasi Asuhan Gizi");
        MnMonevAsuhanGizi.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        MnMonevAsuhanGizi.setHorizontalTextPosition(javax.swing.SwingConstants.RIGHT);
        MnMonevAsuhanGizi.setIconTextGap(5);
        MnMonevAsuhanGizi.setName("MnMonevAsuhanGizi"); // NOI18N
        MnMonevAsuhanGizi.setPreferredSize(new java.awt.Dimension(210, 26));
        MnMonevAsuhanGizi.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                MnMonevAsuhanGiziActionPerformed(evt);
            }
        });
        MnInputData.add(MnMonevAsuhanGizi);

        MnDiet.setBackground(new java.awt.Color(255, 255, 255));
        MnDiet.setFont(new java.awt.Font("Tahoma", 0, 11)); // NOI18N
        MnDiet.setIcon(new javax.swing.ImageIcon(getClass().getResource("/picture/category.png"))); // NOI18N
        MnDiet.setText("Diet Pasien");
        MnDiet.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        MnDiet.setHorizontalTextPosition(javax.swing.SwingConstants.RIGHT);
        MnDiet.setIconTextGap(5);
        MnDiet.setName("MnDiet"); // NOI18N
        MnDiet.setPreferredSize(new java.awt.Dimension(210, 26));
        MnDiet.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                MnDietActionPerformed(evt);
            }
        });
        MnInputData.add(MnDiet);

        MnStatusGizi.setBackground(new java.awt.Color(255, 255, 255));
        MnStatusGizi.setFont(new java.awt.Font("Tahoma", 0, 11)); // NOI18N
        MnStatusGizi.setIcon(new javax.swing.ImageIcon(getClass().getResource("/picture/category.png"))); // NOI18N
        MnStatusGizi.setText("Status Gizi Pasien");
        MnStatusGizi.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        MnStatusGizi.setHorizontalTextPosition(javax.swing.SwingConstants.RIGHT);
        MnStatusGizi.setIconTextGap(5);
        MnStatusGizi.setName("MnStatusGizi"); // NOI18N
        MnStatusGizi.setPreferredSize(new java.awt.Dimension(210, 26));
        MnStatusGizi.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                MnStatusGiziActionPerformed(evt);
            }
        });
        MnInputData.add(MnStatusGizi);

        MnGiziBuruk.setBackground(new java.awt.Color(255, 255, 255));
        MnGiziBuruk.setFont(new java.awt.Font("Tahoma", 0, 11)); // NOI18N
        MnGiziBuruk.setIcon(new javax.swing.ImageIcon(getClass().getResource("/picture/category.png"))); // NOI18N
        MnGiziBuruk.setText("Gizi Buruk/Kurang Pasien");
        MnGiziBuruk.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        MnGiziBuruk.setHorizontalTextPosition(javax.swing.SwingConstants.RIGHT);
        MnGiziBuruk.setIconTextGap(5);
        MnGiziBuruk.setName("MnGiziBuruk"); // NOI18N
        MnGiziBuruk.setPreferredSize(new java.awt.Dimension(210, 26));
        MnGiziBuruk.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                MnGiziBurukActionPerformed(evt);
            }
        });
        MnInputData.add(MnGiziBuruk);

        MnDiagnosa.setBackground(new java.awt.Color(255, 255, 255));
        MnDiagnosa.setFont(new java.awt.Font("Tahoma", 0, 11)); // NOI18N
        MnDiagnosa.setIcon(new javax.swing.ImageIcon(getClass().getResource("/picture/category.png"))); // NOI18N
        MnDiagnosa.setText("Diagnosa Pasien");
        MnDiagnosa.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        MnDiagnosa.setHorizontalTextPosition(javax.swing.SwingConstants.RIGHT);
        MnDiagnosa.setIconTextGap(5);
        MnDiagnosa.setName("MnDiagnosa"); // NOI18N
        MnDiagnosa.setPreferredSize(new java.awt.Dimension(210, 26));
        MnDiagnosa.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                MnDiagnosaActionPerformed(evt);
            }
        });
        MnInputData.add(MnDiagnosa);

        MnRingkasanPulang.setBackground(new java.awt.Color(255, 255, 255));
        MnRingkasanPulang.setFont(new java.awt.Font("Tahoma", 0, 11)); // NOI18N
        MnRingkasanPulang.setIcon(new javax.swing.ImageIcon(getClass().getResource("/picture/category.png"))); // NOI18N
        MnRingkasanPulang.setText("Ringkasan Pulang");
        MnRingkasanPulang.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        MnRingkasanPulang.setHorizontalTextPosition(javax.swing.SwingConstants.RIGHT);
        MnRingkasanPulang.setIconTextGap(5);
        MnRingkasanPulang.setName("MnRingkasanPulang"); // NOI18N
        MnRingkasanPulang.setPreferredSize(new java.awt.Dimension(210, 26));
        MnRingkasanPulang.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                MnRingkasanPulangActionPerformed(evt);
            }
        });
        MnInputData.add(MnRingkasanPulang);

        ppDataPersalinan.setBackground(new java.awt.Color(255, 255, 255));
        ppDataPersalinan.setFont(new java.awt.Font("Tahoma", 0, 11)); // NOI18N
        ppDataPersalinan.setIcon(new javax.swing.ImageIcon(getClass().getResource("/picture/category.png"))); // NOI18N
        ppDataPersalinan.setText("Data Persalinan");
        ppDataPersalinan.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        ppDataPersalinan.setHorizontalTextPosition(javax.swing.SwingConstants.RIGHT);
        ppDataPersalinan.setIconTextGap(5);
        ppDataPersalinan.setName("ppDataPersalinan"); // NOI18N
        ppDataPersalinan.setPreferredSize(new java.awt.Dimension(210, 26));
        ppDataPersalinan.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                ppDataPersalinanBtnPrintActionPerformed(evt);
            }
        });
        MnInputData.add(ppDataPersalinan);

        ppDataPonek.setBackground(new java.awt.Color(255, 255, 255));
        ppDataPonek.setFont(new java.awt.Font("Tahoma", 0, 11)); // NOI18N
        ppDataPonek.setIcon(new javax.swing.ImageIcon(getClass().getResource("/picture/category.png"))); // NOI18N
        ppDataPonek.setText("Data Ponek");
        ppDataPonek.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        ppDataPonek.setHorizontalTextPosition(javax.swing.SwingConstants.RIGHT);
        ppDataPonek.setIconTextGap(5);
        ppDataPonek.setName("ppDataPonek"); // NOI18N
        ppDataPonek.setPreferredSize(new java.awt.Dimension(210, 26));
        ppDataPonek.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                ppDataPonekBtnPrintActionPerformed(evt);
            }
        });
        MnInputData.add(ppDataPonek);

        ppDataHAIs.setBackground(new java.awt.Color(255, 255, 255));
        ppDataHAIs.setFont(new java.awt.Font("Tahoma", 0, 11)); // NOI18N
        ppDataHAIs.setIcon(new javax.swing.ImageIcon(getClass().getResource("/picture/category.png"))); // NOI18N
        ppDataHAIs.setText("Data HAIs");
        ppDataHAIs.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        ppDataHAIs.setHorizontalTextPosition(javax.swing.SwingConstants.RIGHT);
        ppDataHAIs.setIconTextGap(5);
        ppDataHAIs.setName("ppDataHAIs"); // NOI18N
        ppDataHAIs.setPreferredSize(new java.awt.Dimension(210, 26));
        ppDataHAIs.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                ppDataHAIsBtnPrintActionPerformed(evt);
            }
        });
        MnInputData.add(ppDataHAIs);

        ppCatatanPasien.setBackground(new java.awt.Color(255, 255, 255));
        ppCatatanPasien.setFont(new java.awt.Font("Tahoma", 0, 11)); // NOI18N
        ppCatatanPasien.setIcon(new javax.swing.ImageIcon(getClass().getResource("/picture/category.png"))); // NOI18N
        ppCatatanPasien.setText("Catatan Untuk Pasien");
        ppCatatanPasien.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        ppCatatanPasien.setHorizontalTextPosition(javax.swing.SwingConstants.RIGHT);
        ppCatatanPasien.setIconTextGap(5);
        ppCatatanPasien.setName("ppCatatanPasien"); // NOI18N
        ppCatatanPasien.setPreferredSize(new java.awt.Dimension(210, 26));
        ppCatatanPasien.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                ppCatatanPasienBtnPrintActionPerformed(evt);
            }
        });
        MnInputData.add(ppCatatanPasien);

        ppPerawatanCorona.setBackground(new java.awt.Color(255, 255, 255));
        ppPerawatanCorona.setFont(new java.awt.Font("Tahoma", 0, 11)); // NOI18N
        ppPerawatanCorona.setIcon(new javax.swing.ImageIcon(getClass().getResource("/picture/category.png"))); // NOI18N
        ppPerawatanCorona.setText("Perawatan Pasien Corona INACBG");
        ppPerawatanCorona.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        ppPerawatanCorona.setHorizontalTextPosition(javax.swing.SwingConstants.RIGHT);
        ppPerawatanCorona.setName("ppPerawatanCorona"); // NOI18N
        ppPerawatanCorona.setPreferredSize(new java.awt.Dimension(210, 26));
        ppPerawatanCorona.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                ppPerawatanCoronaBtnPrintActionPerformed(evt);
            }
        });
        MnInputData.add(ppPerawatanCorona);

        jPopupMenu1.add(MnInputData);

        MnBilling.setBackground(new java.awt.Color(255, 255, 255));
        MnBilling.setFont(new java.awt.Font("Tahoma", 0, 11)); // NOI18N
        MnBilling.setIcon(new javax.swing.ImageIcon(getClass().getResource("/picture/category.png"))); // NOI18N
        MnBilling.setText("Billing/Pembayaran Pasien");
        MnBilling.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        MnBilling.setHorizontalTextPosition(javax.swing.SwingConstants.RIGHT);
        MnBilling.setIconTextGap(5);
        MnBilling.setName("MnBilling"); // NOI18N
        MnBilling.setPreferredSize(new java.awt.Dimension(220, 26));
        MnBilling.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                MnBillingActionPerformed(evt);
            }
        });
        jPopupMenu1.add(MnBilling);

        MnDeposit.setBackground(new java.awt.Color(255, 255, 255));
        MnDeposit.setFont(new java.awt.Font("Tahoma", 0, 11)); // NOI18N
        MnDeposit.setIcon(new javax.swing.ImageIcon(getClass().getResource("/picture/category.png"))); // NOI18N
        MnDeposit.setText("Deposit/Titipan Pasien");
        MnDeposit.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        MnDeposit.setHorizontalTextPosition(javax.swing.SwingConstants.RIGHT);
        MnDeposit.setIconTextGap(5);
        MnDeposit.setName("MnDeposit"); // NOI18N
        MnDeposit.setPreferredSize(new java.awt.Dimension(220, 26));
        MnDeposit.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                MnDepositActionPerformed(evt);
            }
        });
        jPopupMenu1.add(MnDeposit);

        MnUpdateHari.setBackground(new java.awt.Color(255, 255, 255));
        MnUpdateHari.setFont(new java.awt.Font("Tahoma", 0, 11)); // NOI18N
        MnUpdateHari.setIcon(new javax.swing.ImageIcon(getClass().getResource("/picture/category.png"))); // NOI18N
        MnUpdateHari.setText("Update Hari Perawatan");
        MnUpdateHari.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        MnUpdateHari.setHorizontalTextPosition(javax.swing.SwingConstants.RIGHT);
        MnUpdateHari.setIconTextGap(5);
        MnUpdateHari.setName("MnUpdateHari"); // NOI18N
        MnUpdateHari.setPreferredSize(new java.awt.Dimension(220, 26));
        MnUpdateHari.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                MnUpdateHariActionPerformed(evt);
            }
        });
        jPopupMenu1.add(MnUpdateHari);

        ppRiwayat.setBackground(new java.awt.Color(255, 255, 255));
        ppRiwayat.setFont(new java.awt.Font("Tahoma", 0, 11)); // NOI18N
        ppRiwayat.setIcon(new javax.swing.ImageIcon(getClass().getResource("/picture/category.png"))); // NOI18N
        ppRiwayat.setText("Riwayat Perawatan");
        ppRiwayat.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        ppRiwayat.setHorizontalTextPosition(javax.swing.SwingConstants.RIGHT);
        ppRiwayat.setIconTextGap(5);
        ppRiwayat.setName("ppRiwayat"); // NOI18N
        ppRiwayat.setPreferredSize(new java.awt.Dimension(220, 26));
        ppRiwayat.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                ppRiwayatBtnPrintActionPerformed(evt);
            }
        });
        jPopupMenu1.add(ppRiwayat);

        jPopupMenu2.setFont(new java.awt.Font("Tahoma", 0, 11)); // NOI18N
        jPopupMenu2.setName("jPopupMenu2"); // NOI18N
        jPopupMenu2.setPreferredSize(new java.awt.Dimension(220, 26));

        MnIndividuPx.setBackground(new java.awt.Color(255, 255, 255));
        MnIndividuPx.setFont(new java.awt.Font("Tahoma", 0, 11)); // NOI18N
        MnIndividuPx.setIcon(new javax.swing.ImageIcon(getClass().getResource("/picture/category.png"))); // NOI18N
        MnIndividuPx.setText("Cetak Data Individu Pasien");
        MnIndividuPx.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        MnIndividuPx.setHorizontalTextPosition(javax.swing.SwingConstants.RIGHT);
        MnIndividuPx.setIconTextGap(5);
        MnIndividuPx.setName("MnIndividuPx"); // NOI18N
        MnIndividuPx.setPreferredSize(new java.awt.Dimension(220, 26));
        MnIndividuPx.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                MnIndividuPxActionPerformed(evt);
            }
        });
        jPopupMenu2.add(MnIndividuPx);

        JamMasuk.setEditable(false);
        JamMasuk.setForeground(new java.awt.Color(255, 255, 255));
        JamMasuk.setHighlighter(null);
        JamMasuk.setName("JamMasuk"); // NOI18N
        JamMasuk.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                JamMasukKeyPressed(evt);
            }
        });

        WindowPindahKamar.setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);
        WindowPindahKamar.setName("WindowPindahKamar"); // NOI18N
        WindowPindahKamar.setUndecorated(true);
        WindowPindahKamar.setResizable(false);
        WindowPindahKamar.addWindowListener(new java.awt.event.WindowAdapter() {
            public void windowActivated(java.awt.event.WindowEvent evt) {
                WindowPindahKamarWindowActivated(evt);
            }
        });

        internalFrame3.setBorder(javax.swing.BorderFactory.createTitledBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 153, 255), 3), "::[ Pindah Kamar Inap Pasien ]::", javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Tahoma", 1, 12))); // NOI18N
        internalFrame3.setName("internalFrame3"); // NOI18N
        internalFrame3.setWarnaBawah(new java.awt.Color(245, 250, 240));
        internalFrame3.setLayout(null);

        norawatpindah.setEditable(false);
        norawatpindah.setForeground(new java.awt.Color(0, 0, 0));
        norawatpindah.setHighlighter(null);
        norawatpindah.setName("norawatpindah"); // NOI18N
        internalFrame3.add(norawatpindah);
        norawatpindah.setBounds(75, 25, 150, 23);

        TPasienpindah.setEditable(false);
        TPasienpindah.setForeground(new java.awt.Color(0, 0, 0));
        TPasienpindah.setHighlighter(null);
        TPasienpindah.setName("TPasienpindah"); // NOI18N
        internalFrame3.add(TPasienpindah);
        TPasienpindah.setBounds(359, 25, 299, 23);

        kdkamarpindah.setForeground(new java.awt.Color(0, 0, 0));
        kdkamarpindah.setHighlighter(null);
        kdkamarpindah.setName("kdkamarpindah"); // NOI18N
        kdkamarpindah.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                kdkamarpindahKeyPressed(evt);
            }
        });
        internalFrame3.add(kdkamarpindah);
        kdkamarpindah.setBounds(75, 55, 95, 23);

        ttlbiayapindah.setEditable(false);
        ttlbiayapindah.setForeground(new java.awt.Color(0, 0, 0));
        ttlbiayapindah.setText("0");
        ttlbiayapindah.setDisabledTextColor(new java.awt.Color(0, 0, 0));
        ttlbiayapindah.setEnabled(false);
        ttlbiayapindah.setHighlighter(null);
        ttlbiayapindah.setName("ttlbiayapindah"); // NOI18N
        internalFrame3.add(ttlbiayapindah);
        ttlbiayapindah.setBounds(368, 115, 290, 23);

        jLabel4.setForeground(new java.awt.Color(0, 0, 0));
        jLabel4.setText("No.Rawat :");
        jLabel4.setName("jLabel4"); // NOI18N
        internalFrame3.add(jLabel4);
        jLabel4.setBounds(0, 25, 72, 23);

        jLabel20.setForeground(new java.awt.Color(0, 0, 0));
        jLabel20.setText("Kamar :");
        jLabel20.setName("jLabel20"); // NOI18N
        internalFrame3.add(jLabel20);
        jLabel20.setBounds(0, 55, 72, 23);

        TNoRMpindah.setEditable(false);
        TNoRMpindah.setForeground(new java.awt.Color(0, 0, 0));
        TNoRMpindah.setHighlighter(null);
        TNoRMpindah.setName("TNoRMpindah"); // NOI18N
        internalFrame3.add(TNoRMpindah);
        TNoRMpindah.setBounds(227, 25, 130, 23);

        btnKamar2.setForeground(new java.awt.Color(0, 0, 0));
        btnKamar2.setIcon(new javax.swing.ImageIcon(getClass().getResource("/picture/190.png"))); // NOI18N
        btnKamar2.setMnemonic('2');
        btnKamar2.setToolTipText("Alt+2");
        btnKamar2.setName("btnKamar2"); // NOI18N
        btnKamar2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnKamar2ActionPerformed(evt);
            }
        });
        internalFrame3.add(btnKamar2);
        btnKamar2.setBounds(428, 55, 28, 23);

        TKdBngsalpindah.setEditable(false);
        TKdBngsalpindah.setForeground(new java.awt.Color(0, 0, 0));
        TKdBngsalpindah.setName("TKdBngsalpindah"); // NOI18N
        internalFrame3.add(TKdBngsalpindah);
        TKdBngsalpindah.setBounds(172, 55, 82, 23);

        jLabel27.setForeground(new java.awt.Color(0, 0, 0));
        jLabel27.setText("Tanggal :");
        jLabel27.setName("jLabel27"); // NOI18N
        internalFrame3.add(jLabel27);
        jLabel27.setBounds(0, 85, 72, 23);

        cmbDtkpindah.setForeground(new java.awt.Color(0, 0, 0));
        cmbDtkpindah.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "00", "01", "02", "03", "04", "05", "06", "07", "08", "09", "10", "11", "12", "13", "14", "15", "16", "17", "18", "19", "20", "21", "22", "23", "24", "25", "26", "27", "28", "29", "30", "31", "32", "33", "34", "35", "36", "37", "38", "39", "40", "41", "42", "43", "44", "45", "46", "47", "48", "49", "50", "51", "52", "53", "54", "55", "56", "57", "58", "59" }));
        cmbDtkpindah.setName("cmbDtkpindah"); // NOI18N
        internalFrame3.add(cmbDtkpindah);
        cmbDtkpindah.setBounds(466, 85, 55, 23);

        cmbMntpindah.setForeground(new java.awt.Color(0, 0, 0));
        cmbMntpindah.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "00", "01", "02", "03", "04", "05", "06", "07", "08", "09", "10", "11", "12", "13", "14", "15", "16", "17", "18", "19", "20", "21", "22", "23", "24", "25", "26", "27", "28", "29", "30", "31", "32", "33", "34", "35", "36", "37", "38", "39", "40", "41", "42", "43", "44", "45", "46", "47", "48", "49", "50", "51", "52", "53", "54", "55", "56", "57", "58", "59" }));
        cmbMntpindah.setName("cmbMntpindah"); // NOI18N
        internalFrame3.add(cmbMntpindah);
        cmbMntpindah.setBounds(408, 85, 55, 23);

        cmbJampindah.setForeground(new java.awt.Color(0, 0, 0));
        cmbJampindah.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "00", "01", "02", "03", "04", "05", "06", "07", "08", "09", "10", "11", "12", "13", "14", "15", "16", "17", "18", "19", "20", "21", "22", "23" }));
        cmbJampindah.setName("cmbJampindah"); // NOI18N
        internalFrame3.add(cmbJampindah);
        cmbJampindah.setBounds(350, 85, 55, 23);

        TBangsalpindah.setEditable(false);
        TBangsalpindah.setForeground(new java.awt.Color(0, 0, 0));
        TBangsalpindah.setHighlighter(null);
        TBangsalpindah.setName("TBangsalpindah"); // NOI18N
        TBangsalpindah.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                TBangsalpindahKeyPressed(evt);
            }
        });
        internalFrame3.add(TBangsalpindah);
        TBangsalpindah.setBounds(256, 55, 170, 23);

        jLabel28.setForeground(new java.awt.Color(0, 0, 0));
        jLabel28.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel28.setText("X");
        jLabel28.setName("jLabel28"); // NOI18N
        internalFrame3.add(jLabel28);
        jLabel28.setBounds(173, 115, 15, 23);

        TJmlHaripindah.setForeground(new java.awt.Color(0, 0, 0));
        TJmlHaripindah.setText("1");
        TJmlHaripindah.setDisabledTextColor(new java.awt.Color(0, 0, 0));
        TJmlHaripindah.setEnabled(false);
        TJmlHaripindah.setHighlighter(null);
        TJmlHaripindah.setName("TJmlHaripindah"); // NOI18N
        internalFrame3.add(TJmlHaripindah);
        TJmlHaripindah.setBounds(75, 115, 96, 23);

        jLabel29.setForeground(new java.awt.Color(0, 0, 0));
        jLabel29.setText("=");
        jLabel29.setName("jLabel29"); // NOI18N
        internalFrame3.add(jLabel29);
        jLabel29.setBounds(342, 115, 20, 23);

        TSttsKamarpindah.setEditable(false);
        TSttsKamarpindah.setForeground(new java.awt.Color(0, 0, 0));
        TSttsKamarpindah.setName("TSttsKamarpindah"); // NOI18N
        internalFrame3.add(TSttsKamarpindah);
        TSttsKamarpindah.setBounds(548, 55, 110, 23);

        BtnCloseInpindah.setForeground(new java.awt.Color(0, 0, 0));
        BtnCloseInpindah.setIcon(new javax.swing.ImageIcon(getClass().getResource("/picture/cross.png"))); // NOI18N
        BtnCloseInpindah.setMnemonic('U');
        BtnCloseInpindah.setText("Tutup");
        BtnCloseInpindah.setToolTipText("Alt+U");
        BtnCloseInpindah.setName("BtnCloseInpindah"); // NOI18N
        BtnCloseInpindah.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                BtnCloseInpindahActionPerformed(evt);
            }
        });
        BtnCloseInpindah.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                BtnCloseInpindahKeyPressed(evt);
            }
        });
        internalFrame3.add(BtnCloseInpindah);
        BtnCloseInpindah.setBounds(560, 235, 100, 30);

        jLabel30.setForeground(new java.awt.Color(0, 0, 0));
        jLabel30.setText("-------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------");
        jLabel30.setName("jLabel30"); // NOI18N
        internalFrame3.add(jLabel30);
        jLabel30.setBounds(-10, 215, 850, 14);

        BtnSimpanpindah.setForeground(new java.awt.Color(0, 0, 0));
        BtnSimpanpindah.setIcon(new javax.swing.ImageIcon(getClass().getResource("/picture/save-16x16.png"))); // NOI18N
        BtnSimpanpindah.setMnemonic('S');
        BtnSimpanpindah.setText("Simpan");
        BtnSimpanpindah.setToolTipText("Alt+S");
        BtnSimpanpindah.setName("BtnSimpanpindah"); // NOI18N
        BtnSimpanpindah.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                BtnSimpanpindahActionPerformed(evt);
            }
        });
        BtnSimpanpindah.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                BtnSimpanpindahKeyPressed(evt);
            }
        });
        internalFrame3.add(BtnSimpanpindah);
        BtnSimpanpindah.setBounds(14, 235, 100, 30);

        jLabel31.setForeground(new java.awt.Color(0, 0, 0));
        jLabel31.setText("Stts.Kamar :");
        jLabel31.setName("jLabel31"); // NOI18N
        internalFrame3.add(jLabel31);
        jLabel31.setBounds(444, 55, 100, 23);

        jLabel32.setForeground(new java.awt.Color(0, 0, 0));
        jLabel32.setText("Pilihan :");
        jLabel32.setName("jLabel32"); // NOI18N
        internalFrame3.add(jLabel32);
        jLabel32.setBounds(0, 145, 72, 23);

        TTarifpindah.setEditable(false);
        TTarifpindah.setForeground(new java.awt.Color(0, 0, 0));
        TTarifpindah.setText("0");
        TTarifpindah.setDisabledTextColor(new java.awt.Color(0, 0, 0));
        TTarifpindah.setEnabled(false);
        TTarifpindah.setHighlighter(null);
        TTarifpindah.setName("TTarifpindah"); // NOI18N
        internalFrame3.add(TTarifpindah);
        TTarifpindah.setBounds(188, 115, 160, 23);

        CmbTahunpindah.setForeground(new java.awt.Color(0, 0, 0));
        CmbTahunpindah.setName("CmbTahunpindah"); // NOI18N
        internalFrame3.add(CmbTahunpindah);
        CmbTahunpindah.setBounds(191, 85, 80, 23);

        CmbBlnpindah.setForeground(new java.awt.Color(0, 0, 0));
        CmbBlnpindah.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "01", "02", "03", "04", "05", "06", "07", "08", "09", "10", "11", "12" }));
        CmbBlnpindah.setName("CmbBlnpindah"); // NOI18N
        internalFrame3.add(CmbBlnpindah);
        CmbBlnpindah.setBounds(133, 85, 55, 23);

        CmbTglpindah.setForeground(new java.awt.Color(0, 0, 0));
        CmbTglpindah.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "01", "02", "03", "04", "05", "06", "07", "08", "09", "10", "11", "12", "13", "14", "15", "16", "17", "18", "19", "20", "21", "22", "23", "24", "25", "26", "27", "28", "29", "30", "31" }));
        CmbTglpindah.setName("CmbTglpindah"); // NOI18N
        internalFrame3.add(CmbTglpindah);
        CmbTglpindah.setBounds(75, 85, 55, 23);

        jLabel35.setForeground(new java.awt.Color(0, 0, 0));
        jLabel35.setText("Jam :");
        jLabel35.setName("jLabel35"); // NOI18N
        internalFrame3.add(jLabel35);
        jLabel35.setBounds(280, 85, 67, 23);

        buttonGroup2.add(Rganti3);
        Rganti3.setSelected(true);
        Rganti3.setText("3. Kamar Inap sebelumnya distatuskan pindah, lama inap dihitung dan pasien dimasukkan Ke Kamar inap yang baru");
        Rganti3.setName("Rganti3"); // NOI18N
        internalFrame3.add(Rganti3);
        Rganti3.setBounds(75, 176, 620, 20);

        jLabel33.setForeground(new java.awt.Color(0, 0, 0));
        jLabel33.setText("Biaya :");
        jLabel33.setName("jLabel33"); // NOI18N
        internalFrame3.add(jLabel33);
        jLabel33.setBounds(0, 115, 72, 23);

        buttonGroup2.add(Rganti2);
        Rganti2.setText("2. Kamar Inap sebelumnya diganti kamarnya dengan Kamar Inap terbaru dan harga kamar menyesuaikan harga baru");
        Rganti2.setName("Rganti2"); // NOI18N
        internalFrame3.add(Rganti2);
        Rganti2.setBounds(75, 159, 620, 20);

        buttonGroup2.add(Rganti1);
        Rganti1.setText("1. Kamar Inap sebelumnya dihapus dan pasien dihitung menginap mulai saat ini (Kamar Inap lama dihapus dari billing)");
        Rganti1.setEnabled(false);
        Rganti1.setName("Rganti1"); // NOI18N
        internalFrame3.add(Rganti1);
        Rganti1.setBounds(75, 142, 620, 20);

        buttonGroup2.add(Rganti4);
        Rganti4.setText("4. Seperti nomer 3, Kamar Inap sebelumnya mengikuti harga tertinggi");
        Rganti4.setEnabled(false);
        Rganti4.setName("Rganti4"); // NOI18N
        internalFrame3.add(Rganti4);
        Rganti4.setBounds(75, 193, 620, 20);

        WindowPindahKamar.getContentPane().add(internalFrame3, java.awt.BorderLayout.CENTER);

        WindowCaraBayar.setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);
        WindowCaraBayar.setName("WindowCaraBayar"); // NOI18N
        WindowCaraBayar.setUndecorated(true);
        WindowCaraBayar.setResizable(false);

        internalFrame5.setBorder(javax.swing.BorderFactory.createTitledBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 153, 255), 3), "::[ Ganti Jenis Bayar ]::", javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Tahoma", 1, 12))); // NOI18N
        internalFrame5.setName("internalFrame5"); // NOI18N
        internalFrame5.setWarnaBawah(new java.awt.Color(245, 250, 240));
        internalFrame5.setLayout(null);

        BtnCloseIn4.setForeground(new java.awt.Color(0, 0, 0));
        BtnCloseIn4.setIcon(new javax.swing.ImageIcon(getClass().getResource("/picture/cross.png"))); // NOI18N
        BtnCloseIn4.setMnemonic('U');
        BtnCloseIn4.setText("Tutup");
        BtnCloseIn4.setToolTipText("Alt+U");
        BtnCloseIn4.setName("BtnCloseIn4"); // NOI18N
        BtnCloseIn4.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                BtnCloseIn4ActionPerformed(evt);
            }
        });
        internalFrame5.add(BtnCloseIn4);
        BtnCloseIn4.setBounds(510, 30, 100, 30);

        BtnSimpan4.setForeground(new java.awt.Color(0, 0, 0));
        BtnSimpan4.setIcon(new javax.swing.ImageIcon(getClass().getResource("/picture/save-16x16.png"))); // NOI18N
        BtnSimpan4.setMnemonic('S');
        BtnSimpan4.setText("Simpan");
        BtnSimpan4.setToolTipText("Alt+S");
        BtnSimpan4.setName("BtnSimpan4"); // NOI18N
        BtnSimpan4.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                BtnSimpan4ActionPerformed(evt);
            }
        });
        internalFrame5.add(BtnSimpan4);
        BtnSimpan4.setBounds(405, 30, 100, 30);

        jLabel17.setForeground(new java.awt.Color(0, 0, 0));
        jLabel17.setText("Jenis Bayar :");
        jLabel17.setName("jLabel17"); // NOI18N
        internalFrame5.add(jLabel17);
        jLabel17.setBounds(0, 32, 77, 23);

        kdpenjab.setEditable(false);
        kdpenjab.setForeground(new java.awt.Color(0, 0, 0));
        kdpenjab.setHighlighter(null);
        kdpenjab.setName("kdpenjab"); // NOI18N
        kdpenjab.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                kdpenjabKeyPressed(evt);
            }
        });
        internalFrame5.add(kdpenjab);
        kdpenjab.setBounds(81, 32, 100, 23);

        nmpenjab.setEditable(false);
        nmpenjab.setForeground(new java.awt.Color(0, 0, 0));
        nmpenjab.setName("nmpenjab"); // NOI18N
        internalFrame5.add(nmpenjab);
        nmpenjab.setBounds(183, 32, 181, 23);

        btnBayar.setForeground(new java.awt.Color(0, 0, 0));
        btnBayar.setIcon(new javax.swing.ImageIcon(getClass().getResource("/picture/190.png"))); // NOI18N
        btnBayar.setMnemonic('7');
        btnBayar.setToolTipText("ALt+7");
        btnBayar.setName("btnBayar"); // NOI18N
        btnBayar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnBayarActionPerformed(evt);
            }
        });
        internalFrame5.add(btnBayar);
        btnBayar.setBounds(366, 32, 28, 23);

        WindowCaraBayar.getContentPane().add(internalFrame5, java.awt.BorderLayout.CENTER);

        WindowRanapGabung.setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);
        WindowRanapGabung.setName("WindowRanapGabung"); // NOI18N
        WindowRanapGabung.setUndecorated(true);
        WindowRanapGabung.setResizable(false);

        internalFrame6.setBorder(javax.swing.BorderFactory.createTitledBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 153, 255), 3), "::[ Ranap Gabung Ibu & Bayi ]::"));
        internalFrame6.setName("internalFrame6"); // NOI18N
        internalFrame6.setWarnaBawah(new java.awt.Color(245, 250, 240));
        internalFrame6.setLayout(null);

        BtnCloseGabung.setForeground(new java.awt.Color(0, 0, 0));
        BtnCloseGabung.setIcon(new javax.swing.ImageIcon(getClass().getResource("/picture/cross.png"))); // NOI18N
        BtnCloseGabung.setMnemonic('U');
        BtnCloseGabung.setText("Tutup");
        BtnCloseGabung.setToolTipText("Alt+U");
        BtnCloseGabung.setName("BtnCloseGabung"); // NOI18N
        BtnCloseGabung.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                BtnCloseGabungActionPerformed(evt);
            }
        });
        internalFrame6.add(BtnCloseGabung);
        BtnCloseGabung.setBounds(510, 70, 100, 30);

        BtnSimpanGabung.setForeground(new java.awt.Color(0, 0, 0));
        BtnSimpanGabung.setIcon(new javax.swing.ImageIcon(getClass().getResource("/picture/save-16x16.png"))); // NOI18N
        BtnSimpanGabung.setMnemonic('S');
        BtnSimpanGabung.setText("Simpan");
        BtnSimpanGabung.setToolTipText("Alt+S");
        BtnSimpanGabung.setName("BtnSimpanGabung"); // NOI18N
        BtnSimpanGabung.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                BtnSimpanGabungActionPerformed(evt);
            }
        });
        internalFrame6.add(BtnSimpanGabung);
        BtnSimpanGabung.setBounds(17, 70, 100, 30);

        jLabel34.setForeground(new java.awt.Color(0, 0, 0));
        jLabel34.setText("No.R.M.Bayi :");
        jLabel34.setName("jLabel34"); // NOI18N
        internalFrame6.add(jLabel34);
        jLabel34.setBounds(2, 30, 87, 23);

        NoRmBayi.setForeground(new java.awt.Color(0, 0, 0));
        NoRmBayi.setHighlighter(null);
        NoRmBayi.setName("NoRmBayi"); // NOI18N
        NoRmBayi.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                NoRmBayiKeyPressed(evt);
            }
        });
        internalFrame6.add(NoRmBayi);
        NoRmBayi.setBounds(92, 30, 100, 23);

        NmBayi.setEditable(false);
        NmBayi.setForeground(new java.awt.Color(0, 0, 0));
        NmBayi.setName("NmBayi"); // NOI18N
        internalFrame6.add(NmBayi);
        NmBayi.setBounds(193, 30, 350, 23);

        btnPasienRanapGabung.setForeground(new java.awt.Color(0, 0, 0));
        btnPasienRanapGabung.setIcon(new javax.swing.ImageIcon(getClass().getResource("/picture/190.png"))); // NOI18N
        btnPasienRanapGabung.setMnemonic('7');
        btnPasienRanapGabung.setToolTipText("ALt+7");
        btnPasienRanapGabung.setName("btnPasienRanapGabung"); // NOI18N
        btnPasienRanapGabung.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnPasienRanapGabungActionPerformed(evt);
            }
        });
        internalFrame6.add(btnPasienRanapGabung);
        btnPasienRanapGabung.setBounds(546, 30, 28, 23);

        BtnHapusGabung.setForeground(new java.awt.Color(0, 0, 0));
        BtnHapusGabung.setIcon(new javax.swing.ImageIcon(getClass().getResource("/picture/stop_f2.png"))); // NOI18N
        BtnHapusGabung.setMnemonic('H');
        BtnHapusGabung.setText("Hapus");
        BtnHapusGabung.setToolTipText("Alt+H");
        BtnHapusGabung.setName("BtnHapusGabung"); // NOI18N
        BtnHapusGabung.setPreferredSize(new java.awt.Dimension(100, 30));
        BtnHapusGabung.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                BtnHapusGabungActionPerformed(evt);
            }
        });
        internalFrame6.add(BtnHapusGabung);
        BtnHapusGabung.setBounds(123, 70, 100, 30);

        NoRawatGabung.setForeground(new java.awt.Color(0, 0, 0));
        NoRawatGabung.setHighlighter(null);
        NoRawatGabung.setName("NoRawatGabung"); // NOI18N
        internalFrame6.add(NoRawatGabung);
        NoRawatGabung.setBounds(230, 220, 190, 23);

        btnPasienRanapGabung1.setForeground(new java.awt.Color(0, 0, 0));
        btnPasienRanapGabung1.setIcon(new javax.swing.ImageIcon(getClass().getResource("/picture/190.png"))); // NOI18N
        btnPasienRanapGabung1.setMnemonic('7');
        btnPasienRanapGabung1.setToolTipText("ALt+7");
        btnPasienRanapGabung1.setName("btnPasienRanapGabung1"); // NOI18N
        btnPasienRanapGabung1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnPasienRanapGabung1ActionPerformed(evt);
            }
        });
        internalFrame6.add(btnPasienRanapGabung1);
        btnPasienRanapGabung1.setBounds(576, 30, 28, 23);

        WindowRanapGabung.getContentPane().add(internalFrame6, java.awt.BorderLayout.CENTER);

        DlgJamkesda.setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);
        DlgJamkesda.setName("DlgJamkesda"); // NOI18N
        DlgJamkesda.setUndecorated(true);
        DlgJamkesda.setResizable(false);

        internalFrame7.setBorder(javax.swing.BorderFactory.createTitledBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 153, 255), 3), "::[ Bridging JAMKESDA Rawat Inap ]::", javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Tahoma", 1, 12))); // NOI18N
        internalFrame7.setName("internalFrame7"); // NOI18N
        internalFrame7.setWarnaBawah(new java.awt.Color(245, 250, 240));
        internalFrame7.setLayout(null);

        BtnCloseIn1.setForeground(new java.awt.Color(0, 0, 0));
        BtnCloseIn1.setIcon(new javax.swing.ImageIcon(getClass().getResource("/picture/cross.png"))); // NOI18N
        BtnCloseIn1.setMnemonic('U');
        BtnCloseIn1.setText("Tutup");
        BtnCloseIn1.setToolTipText("Alt+U");
        BtnCloseIn1.setName("BtnCloseIn1"); // NOI18N
        BtnCloseIn1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                BtnCloseIn1ActionPerformed(evt);
            }
        });
        BtnCloseIn1.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                BtnCloseIn1KeyPressed(evt);
            }
        });
        internalFrame7.add(BtnCloseIn1);
        BtnCloseIn1.setBounds(340, 80, 100, 30);

        BtnSimpan1.setForeground(new java.awt.Color(0, 0, 0));
        BtnSimpan1.setIcon(new javax.swing.ImageIcon(getClass().getResource("/picture/save-16x16.png"))); // NOI18N
        BtnSimpan1.setMnemonic('S');
        BtnSimpan1.setText("Simpan");
        BtnSimpan1.setToolTipText("Alt+S");
        BtnSimpan1.setName("BtnSimpan1"); // NOI18N
        BtnSimpan1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                BtnSimpan1ActionPerformed(evt);
            }
        });
        BtnSimpan1.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                BtnSimpan1KeyPressed(evt);
            }
        });
        internalFrame7.add(BtnSimpan1);
        BtnSimpan1.setBounds(10, 80, 100, 30);

        jLabel36.setForeground(new java.awt.Color(0, 0, 0));
        jLabel36.setText("Tgl. Surat Ketrgn. :");
        jLabel36.setName("jLabel36"); // NOI18N
        internalFrame7.add(jLabel36);
        jLabel36.setBounds(0, 50, 110, 23);

        noSrt.setForeground(new java.awt.Color(0, 0, 0));
        noSrt.setHighlighter(null);
        noSrt.setName("noSrt"); // NOI18N
        noSrt.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                noSrtKeyPressed(evt);
            }
        });
        internalFrame7.add(noSrt);
        noSrt.setBounds(120, 20, 350, 23);

        jLabel37.setForeground(new java.awt.Color(0, 0, 0));
        jLabel37.setText("No. Surat Ketrgn. :");
        jLabel37.setName("jLabel37"); // NOI18N
        internalFrame7.add(jLabel37);
        jLabel37.setBounds(0, 20, 110, 23);

        Tglsurat.setEditable(false);
        Tglsurat.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "25-12-2021" }));
        Tglsurat.setDisplayFormat("dd-MM-yyyy");
        Tglsurat.setName("Tglsurat"); // NOI18N
        Tglsurat.setOpaque(false);
        Tglsurat.addItemListener(new java.awt.event.ItemListener() {
            public void itemStateChanged(java.awt.event.ItemEvent evt) {
                TglsuratItemStateChanged(evt);
            }
        });
        Tglsurat.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                TglsuratKeyPressed(evt);
            }
        });
        internalFrame7.add(Tglsurat);
        Tglsurat.setBounds(120, 50, 100, 23);

        BtnGantijkd.setForeground(new java.awt.Color(0, 0, 0));
        BtnGantijkd.setIcon(new javax.swing.ImageIcon(getClass().getResource("/picture/inventaris.png"))); // NOI18N
        BtnGantijkd.setMnemonic('G');
        BtnGantijkd.setText("Ganti");
        BtnGantijkd.setToolTipText("Alt+G");
        BtnGantijkd.setName("BtnGantijkd"); // NOI18N
        BtnGantijkd.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                BtnGantijkdActionPerformed(evt);
            }
        });
        BtnGantijkd.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                BtnGantijkdKeyPressed(evt);
            }
        });
        internalFrame7.add(BtnGantijkd);
        BtnGantijkd.setBounds(120, 80, 100, 30);

        BtnCtkJkd.setForeground(new java.awt.Color(0, 0, 0));
        BtnCtkJkd.setIcon(new javax.swing.ImageIcon(getClass().getResource("/picture/b_print.png"))); // NOI18N
        BtnCtkJkd.setMnemonic('C');
        BtnCtkJkd.setText("Cetak SEP");
        BtnCtkJkd.setToolTipText("Alt+C");
        BtnCtkJkd.setName("BtnCtkJkd"); // NOI18N
        BtnCtkJkd.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                BtnCtkJkdActionPerformed(evt);
            }
        });
        BtnCtkJkd.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                BtnCtkJkdKeyPressed(evt);
            }
        });
        internalFrame7.add(BtnCtkJkd);
        BtnCtkJkd.setBounds(230, 80, 100, 30);

        DlgJamkesda.getContentPane().add(internalFrame7, java.awt.BorderLayout.CENTER);

        DlgJampersal.setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);
        DlgJampersal.setName("DlgJampersal"); // NOI18N
        DlgJampersal.setUndecorated(true);
        DlgJampersal.setResizable(false);

        internalFrame18.setBorder(javax.swing.BorderFactory.createTitledBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 153, 255), 3), "::[ Bridging JAMPERSAL Rawat Inap ]::", javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Tahoma", 1, 12))); // NOI18N
        internalFrame18.setName("internalFrame18"); // NOI18N
        internalFrame18.setWarnaBawah(new java.awt.Color(245, 250, 240));
        internalFrame18.setLayout(null);

        BtnCloseIn13.setForeground(new java.awt.Color(0, 0, 0));
        BtnCloseIn13.setIcon(new javax.swing.ImageIcon(getClass().getResource("/picture/cross.png"))); // NOI18N
        BtnCloseIn13.setMnemonic('U');
        BtnCloseIn13.setText("Tutup");
        BtnCloseIn13.setToolTipText("Alt+U");
        BtnCloseIn13.setName("BtnCloseIn13"); // NOI18N
        BtnCloseIn13.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                BtnCloseIn13ActionPerformed(evt);
            }
        });
        BtnCloseIn13.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                BtnCloseIn13KeyPressed(evt);
            }
        });
        internalFrame18.add(BtnCloseIn13);
        BtnCloseIn13.setBounds(340, 80, 100, 30);

        BtnSimpan3.setForeground(new java.awt.Color(0, 0, 0));
        BtnSimpan3.setIcon(new javax.swing.ImageIcon(getClass().getResource("/picture/save-16x16.png"))); // NOI18N
        BtnSimpan3.setMnemonic('S');
        BtnSimpan3.setText("Simpan");
        BtnSimpan3.setToolTipText("Alt+S");
        BtnSimpan3.setName("BtnSimpan3"); // NOI18N
        BtnSimpan3.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                BtnSimpan3ActionPerformed(evt);
            }
        });
        BtnSimpan3.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                BtnSimpan3KeyPressed(evt);
            }
        });
        internalFrame18.add(BtnSimpan3);
        BtnSimpan3.setBounds(10, 80, 100, 30);

        jLabel104.setForeground(new java.awt.Color(0, 0, 0));
        jLabel104.setText("Tgl. Surat Ketrgn. :");
        jLabel104.setName("jLabel104"); // NOI18N
        internalFrame18.add(jLabel104);
        jLabel104.setBounds(0, 50, 110, 23);

        noSrt1.setForeground(new java.awt.Color(0, 0, 0));
        noSrt1.setHighlighter(null);
        noSrt1.setName("noSrt1"); // NOI18N
        noSrt1.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                noSrt1KeyPressed(evt);
            }
        });
        internalFrame18.add(noSrt1);
        noSrt1.setBounds(120, 20, 350, 23);

        jLabel105.setForeground(new java.awt.Color(0, 0, 0));
        jLabel105.setText("No. Surat Ketrgn. :");
        jLabel105.setName("jLabel105"); // NOI18N
        internalFrame18.add(jLabel105);
        jLabel105.setBounds(0, 20, 110, 23);

        Tglsurat1.setEditable(false);
        Tglsurat1.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "25-12-2021" }));
        Tglsurat1.setDisplayFormat("dd-MM-yyyy");
        Tglsurat1.setName("Tglsurat1"); // NOI18N
        Tglsurat1.setOpaque(false);
        Tglsurat1.addItemListener(new java.awt.event.ItemListener() {
            public void itemStateChanged(java.awt.event.ItemEvent evt) {
                Tglsurat1ItemStateChanged(evt);
            }
        });
        Tglsurat1.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                Tglsurat1KeyPressed(evt);
            }
        });
        internalFrame18.add(Tglsurat1);
        Tglsurat1.setBounds(120, 50, 100, 23);

        BtnGantijmp.setForeground(new java.awt.Color(0, 0, 0));
        BtnGantijmp.setIcon(new javax.swing.ImageIcon(getClass().getResource("/picture/inventaris.png"))); // NOI18N
        BtnGantijmp.setMnemonic('G');
        BtnGantijmp.setText("Ganti");
        BtnGantijmp.setToolTipText("Alt+G");
        BtnGantijmp.setName("BtnGantijmp"); // NOI18N
        BtnGantijmp.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                BtnGantijmpActionPerformed(evt);
            }
        });
        BtnGantijmp.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                BtnGantijmpKeyPressed(evt);
            }
        });
        internalFrame18.add(BtnGantijmp);
        BtnGantijmp.setBounds(120, 80, 100, 30);

        BtnCtkJmp.setForeground(new java.awt.Color(0, 0, 0));
        BtnCtkJmp.setIcon(new javax.swing.ImageIcon(getClass().getResource("/picture/b_print.png"))); // NOI18N
        BtnCtkJmp.setMnemonic('C');
        BtnCtkJmp.setText("Cetak SEP");
        BtnCtkJmp.setToolTipText("Alt+C");
        BtnCtkJmp.setName("BtnCtkJmp"); // NOI18N
        BtnCtkJmp.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                BtnCtkJmpActionPerformed(evt);
            }
        });
        BtnCtkJmp.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                BtnCtkJmpKeyPressed(evt);
            }
        });
        internalFrame18.add(BtnCtkJmp);
        BtnCtkJmp.setBounds(230, 80, 100, 30);

        DlgJampersal.getContentPane().add(internalFrame18, java.awt.BorderLayout.CENTER);

        DlgMati.setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);
        DlgMati.setName("DlgMati"); // NOI18N
        DlgMati.setUndecorated(true);
        DlgMati.setResizable(false);

        internalFrame8.setBorder(javax.swing.BorderFactory.createTitledBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 153, 255), 3), "::[ Ganti Data Pasien Meninggal ]::", javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Tahoma", 1, 12))); // NOI18N
        internalFrame8.setName("internalFrame8"); // NOI18N
        internalFrame8.setWarnaBawah(new java.awt.Color(245, 250, 240));
        internalFrame8.setLayout(null);

        BtnCloseIn2.setForeground(new java.awt.Color(0, 0, 0));
        BtnCloseIn2.setIcon(new javax.swing.ImageIcon(getClass().getResource("/picture/cross.png"))); // NOI18N
        BtnCloseIn2.setMnemonic('U');
        BtnCloseIn2.setText("Tutup");
        BtnCloseIn2.setToolTipText("Alt+U");
        BtnCloseIn2.setName("BtnCloseIn2"); // NOI18N
        BtnCloseIn2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                BtnCloseIn2ActionPerformed(evt);
            }
        });
        BtnCloseIn2.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                BtnCloseIn2KeyPressed(evt);
            }
        });
        internalFrame8.add(BtnCloseIn2);
        BtnCloseIn2.setBounds(310, 80, 80, 30);

        ket1.setForeground(new java.awt.Color(0, 0, 0));
        ket1.setHighlighter(null);
        ket1.setName("ket1"); // NOI18N
        ket1.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                ket1KeyPressed(evt);
            }
        });
        internalFrame8.add(ket1);
        ket1.setBounds(135, 20, 500, 23);

        jLabel43.setForeground(new java.awt.Color(0, 0, 0));
        jLabel43.setText("Keterangan Meninggal :");
        jLabel43.setName("jLabel43"); // NOI18N
        internalFrame8.add(jLabel43);
        jLabel43.setBounds(10, 20, 120, 23);

        TglMati1.setEditable(false);
        TglMati1.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "25-12-2021" }));
        TglMati1.setDisplayFormat("dd-MM-yyyy");
        TglMati1.setName("TglMati1"); // NOI18N
        TglMati1.setOpaque(false);
        TglMati1.addItemListener(new java.awt.event.ItemListener() {
            public void itemStateChanged(java.awt.event.ItemEvent evt) {
                TglMati1ItemStateChanged(evt);
            }
        });
        TglMati1.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                TglMati1KeyPressed(evt);
            }
        });
        internalFrame8.add(TglMati1);
        TglMati1.setBounds(135, 50, 100, 23);

        BtnGantiMati.setForeground(new java.awt.Color(0, 0, 0));
        BtnGantiMati.setIcon(new javax.swing.ImageIcon(getClass().getResource("/picture/inventaris.png"))); // NOI18N
        BtnGantiMati.setMnemonic('G');
        BtnGantiMati.setText("Ganti");
        BtnGantiMati.setToolTipText("Alt+G");
        BtnGantiMati.setName("BtnGantiMati"); // NOI18N
        BtnGantiMati.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                BtnGantiMatiActionPerformed(evt);
            }
        });
        BtnGantiMati.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                BtnGantiMatiKeyPressed(evt);
            }
        });
        internalFrame8.add(BtnGantiMati);
        BtnGantiMati.setBounds(220, 80, 80, 30);

        jLabel39.setForeground(new java.awt.Color(0, 0, 0));
        jLabel39.setText("Jam :");
        jLabel39.setName("jLabel39"); // NOI18N
        internalFrame8.add(jLabel39);
        jLabel39.setBounds(240, 50, 35, 23);

        cmbJam2.setForeground(new java.awt.Color(0, 0, 0));
        cmbJam2.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "00", "01", "02", "03", "04", "05", "06", "07", "08", "09", "10", "11", "12", "13", "14", "15", "16", "17", "18", "19", "20", "21", "22", "23" }));
        cmbJam2.setName("cmbJam2"); // NOI18N
        cmbJam2.setOpaque(false);
        cmbJam2.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                cmbJam2KeyPressed(evt);
            }
        });
        internalFrame8.add(cmbJam2);
        cmbJam2.setBounds(280, 50, 45, 23);

        cmbMnt2.setForeground(new java.awt.Color(0, 0, 0));
        cmbMnt2.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "00", "01", "02", "03", "04", "05", "06", "07", "08", "09", "10", "11", "12", "13", "14", "15", "16", "17", "18", "19", "20", "21", "22", "23", "24", "25", "26", "27", "28", "29", "30", "31", "32", "33", "34", "35", "36", "37", "38", "39", "40", "41", "42", "43", "44", "45", "46", "47", "48", "49", "50", "51", "52", "53", "54", "55", "56", "57", "58", "59" }));
        cmbMnt2.setName("cmbMnt2"); // NOI18N
        cmbMnt2.setOpaque(false);
        cmbMnt2.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                cmbMnt2KeyPressed(evt);
            }
        });
        internalFrame8.add(cmbMnt2);
        cmbMnt2.setBounds(330, 50, 45, 23);

        cmbDtk2.setForeground(new java.awt.Color(0, 0, 0));
        cmbDtk2.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "00", "01", "02", "03", "04", "05", "06", "07", "08", "09", "10", "11", "12", "13", "14", "15", "16", "17", "18", "19", "20", "21", "22", "23", "24", "25", "26", "27", "28", "29", "30", "31", "32", "33", "34", "35", "36", "37", "38", "39", "40", "41", "42", "43", "44", "45", "46", "47", "48", "49", "50", "51", "52", "53", "54", "55", "56", "57", "58", "59" }));
        cmbDtk2.setName("cmbDtk2"); // NOI18N
        cmbDtk2.setOpaque(false);
        cmbDtk2.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                cmbDtk2KeyPressed(evt);
            }
        });
        internalFrame8.add(cmbDtk2);
        cmbDtk2.setBounds(380, 50, 45, 23);

        BtnHapusMati.setForeground(new java.awt.Color(0, 0, 0));
        BtnHapusMati.setIcon(new javax.swing.ImageIcon(getClass().getResource("/picture/stop_f2.png"))); // NOI18N
        BtnHapusMati.setMnemonic('H');
        BtnHapusMati.setText("Hapus");
        BtnHapusMati.setToolTipText("Alt+H");
        BtnHapusMati.setName("BtnHapusMati"); // NOI18N
        BtnHapusMati.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                BtnHapusMatiActionPerformed(evt);
            }
        });
        BtnHapusMati.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                BtnHapusMatiKeyPressed(evt);
            }
        });
        internalFrame8.add(BtnHapusMati);
        BtnHapusMati.setBounds(130, 80, 80, 30);

        jLabel48.setForeground(new java.awt.Color(0, 0, 0));
        jLabel48.setText("Tgl. Meninggal :");
        jLabel48.setName("jLabel48"); // NOI18N
        internalFrame8.add(jLabel48);
        jLabel48.setBounds(10, 50, 120, 23);

        DlgMati.getContentPane().add(internalFrame8, java.awt.BorderLayout.CENTER);

        DlgNoSEP.setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);
        DlgNoSEP.setName("DlgNoSEP"); // NOI18N
        DlgNoSEP.setUndecorated(true);
        DlgNoSEP.setResizable(false);

        internalFrame9.setBorder(javax.swing.BorderFactory.createTitledBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 153, 255), 3), "::[ Nomor SEP Pasien ]::", javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Tahoma", 1, 12))); // NOI18N
        internalFrame9.setName("internalFrame9"); // NOI18N
        internalFrame9.setWarnaBawah(new java.awt.Color(245, 250, 240));
        internalFrame9.setLayout(null);

        BtnCloseIn3.setForeground(new java.awt.Color(0, 0, 0));
        BtnCloseIn3.setIcon(new javax.swing.ImageIcon(getClass().getResource("/picture/cross.png"))); // NOI18N
        BtnCloseIn3.setMnemonic('U');
        BtnCloseIn3.setText("Tutup");
        BtnCloseIn3.setToolTipText("Alt+U");
        BtnCloseIn3.setName("BtnCloseIn3"); // NOI18N
        BtnCloseIn3.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                BtnCloseIn3ActionPerformed(evt);
            }
        });
        BtnCloseIn3.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                BtnCloseIn3KeyPressed(evt);
            }
        });
        internalFrame9.add(BtnCloseIn3);
        BtnCloseIn3.setBounds(330, 103, 80, 30);

        tglsep.setEditable(false);
        tglsep.setForeground(new java.awt.Color(0, 0, 0));
        tglsep.setHighlighter(null);
        tglsep.setName("tglsep"); // NOI18N
        tglsep.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                tglsepKeyPressed(evt);
            }
        });
        internalFrame9.add(tglsep);
        tglsep.setBounds(135, 47, 280, 23);

        jLabel44.setForeground(new java.awt.Color(0, 0, 0));
        jLabel44.setText("Tgl. SEP :");
        jLabel44.setName("jLabel44"); // NOI18N
        internalFrame9.add(jLabel44);
        jLabel44.setBounds(10, 47, 120, 23);

        jLabel45.setForeground(new java.awt.Color(0, 0, 0));
        jLabel45.setText("No. SEP Tercetak :");
        jLabel45.setName("jLabel45"); // NOI18N
        internalFrame9.add(jLabel45);
        jLabel45.setBounds(10, 20, 120, 23);

        nosep.setEditable(false);
        nosep.setForeground(new java.awt.Color(0, 0, 0));
        nosep.setHighlighter(null);
        nosep.setName("nosep"); // NOI18N
        nosep.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyTyped(java.awt.event.KeyEvent evt) {
                nosepKeyTyped(evt);
            }
            public void keyPressed(java.awt.event.KeyEvent evt) {
                nosepKeyPressed(evt);
            }
        });
        internalFrame9.add(nosep);
        nosep.setBounds(135, 20, 280, 23);

        jLabel46.setForeground(new java.awt.Color(0, 0, 0));
        jLabel46.setText("Jenis Bayar :");
        jLabel46.setName("jLabel46"); // NOI18N
        internalFrame9.add(jLabel46);
        jLabel46.setBounds(10, 74, 120, 23);

        jnsBayar.setEditable(false);
        jnsBayar.setForeground(new java.awt.Color(0, 0, 0));
        jnsBayar.setHighlighter(null);
        jnsBayar.setName("jnsBayar"); // NOI18N
        jnsBayar.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                jnsBayarKeyPressed(evt);
            }
        });
        internalFrame9.add(jnsBayar);
        jnsBayar.setBounds(135, 74, 280, 23);

        BtnPrint2.setForeground(new java.awt.Color(0, 0, 0));
        BtnPrint2.setIcon(new javax.swing.ImageIcon(getClass().getResource("/picture/b_print.png"))); // NOI18N
        BtnPrint2.setMnemonic('T');
        BtnPrint2.setText("Print SEP");
        BtnPrint2.setToolTipText("Alt+T");
        BtnPrint2.setName("BtnPrint2"); // NOI18N
        BtnPrint2.setPreferredSize(new java.awt.Dimension(100, 30));
        BtnPrint2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                BtnPrint2ActionPerformed(evt);
            }
        });
        BtnPrint2.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                BtnPrint2KeyPressed(evt);
            }
        });
        internalFrame9.add(BtnPrint2);
        BtnPrint2.setBounds(230, 103, 90, 30);

        DlgNoSEP.getContentPane().add(internalFrame9, java.awt.BorderLayout.CENTER);

        WindowSelisihTarif.setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);
        WindowSelisihTarif.setName("WindowSelisihTarif"); // NOI18N
        WindowSelisihTarif.setUndecorated(true);
        WindowSelisihTarif.setResizable(false);

        internalFrame10.setBorder(javax.swing.BorderFactory.createTitledBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 153, 255), 3), "::[ Selisih Tarif Pasien R. Inap BPJS ]::", javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Tahoma", 1, 12))); // NOI18N
        internalFrame10.setName("internalFrame10"); // NOI18N
        internalFrame10.setWarnaBawah(new java.awt.Color(245, 250, 240));
        internalFrame10.setLayout(null);

        BtnCloseIn6.setForeground(new java.awt.Color(0, 0, 0));
        BtnCloseIn6.setIcon(new javax.swing.ImageIcon(getClass().getResource("/picture/cross.png"))); // NOI18N
        BtnCloseIn6.setMnemonic('U');
        BtnCloseIn6.setText("Tutup");
        BtnCloseIn6.setToolTipText("Alt+U");
        BtnCloseIn6.setName("BtnCloseIn6"); // NOI18N
        BtnCloseIn6.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                BtnCloseIn6ActionPerformed(evt);
            }
        });
        internalFrame10.add(BtnCloseIn6);
        BtnCloseIn6.setBounds(200, 235, 90, 30);

        BtnSimpan6.setForeground(new java.awt.Color(0, 0, 0));
        BtnSimpan6.setIcon(new javax.swing.ImageIcon(getClass().getResource("/picture/save-16x16.png"))); // NOI18N
        BtnSimpan6.setMnemonic('S');
        BtnSimpan6.setText("Simpan");
        BtnSimpan6.setToolTipText("Alt+S");
        BtnSimpan6.setName("BtnSimpan6"); // NOI18N
        BtnSimpan6.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                BtnSimpan6ActionPerformed(evt);
            }
        });
        internalFrame10.add(BtnSimpan6);
        BtnSimpan6.setBounds(10, 235, 90, 30);

        jLabel42.setForeground(new java.awt.Color(0, 0, 0));
        jLabel42.setText("No.SEP : ");
        jLabel42.setName("jLabel42"); // NOI18N
        internalFrame10.add(jLabel42);
        jLabel42.setBounds(0, 25, 102, 23);

        NoSEP.setEditable(false);
        NoSEP.setForeground(new java.awt.Color(0, 0, 0));
        NoSEP.setHighlighter(null);
        NoSEP.setName("NoSEP"); // NOI18N
        NoSEP.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyTyped(java.awt.event.KeyEvent evt) {
                NoSEPKeyTyped(evt);
            }
            public void keyPressed(java.awt.event.KeyEvent evt) {
                NoSEPKeyPressed(evt);
            }
        });
        internalFrame10.add(NoSEP);
        NoSEP.setBounds(106, 25, 150, 23);

        jLabel47.setForeground(new java.awt.Color(0, 0, 0));
        jLabel47.setText("Pasien : ");
        jLabel47.setName("jLabel47"); // NOI18N
        internalFrame10.add(jLabel47);
        jLabel47.setBounds(260, 25, 50, 23);

        norm.setEditable(false);
        norm.setForeground(new java.awt.Color(0, 0, 0));
        norm.setHighlighter(null);
        norm.setName("norm"); // NOI18N
        norm.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                normKeyPressed(evt);
            }
        });
        internalFrame10.add(norm);
        norm.setBounds(310, 25, 80, 23);

        nmpasien.setEditable(false);
        nmpasien.setForeground(new java.awt.Color(0, 0, 0));
        nmpasien.setHighlighter(null);
        nmpasien.setName("nmpasien"); // NOI18N
        nmpasien.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                nmpasienKeyPressed(evt);
            }
        });
        internalFrame10.add(nmpasien);
        nmpasien.setBounds(394, 25, 312, 23);

        nokartu.setEditable(false);
        nokartu.setForeground(new java.awt.Color(0, 0, 0));
        nokartu.setHighlighter(null);
        nokartu.setName("nokartu"); // NOI18N
        nokartu.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                nokartuKeyPressed(evt);
            }
        });
        internalFrame10.add(nokartu);
        nokartu.setBounds(106, 52, 120, 23);

        jLabel49.setForeground(new java.awt.Color(0, 0, 0));
        jLabel49.setText("No. Rawat : ");
        jLabel49.setName("jLabel49"); // NOI18N
        internalFrame10.add(jLabel49);
        jLabel49.setBounds(230, 52, 70, 23);

        norawatSEP.setEditable(false);
        norawatSEP.setForeground(new java.awt.Color(0, 0, 0));
        norawatSEP.setHighlighter(null);
        norawatSEP.setName("norawatSEP"); // NOI18N
        norawatSEP.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                norawatSEPKeyPressed(evt);
            }
        });
        internalFrame10.add(norawatSEP);
        norawatSEP.setBounds(302, 52, 140, 23);

        jLabel50.setForeground(new java.awt.Color(0, 0, 0));
        jLabel50.setText("No.Kartu : ");
        jLabel50.setName("jLabel50"); // NOI18N
        internalFrame10.add(jLabel50);
        jLabel50.setBounds(0, 52, 102, 23);

        jLabel52.setForeground(new java.awt.Color(0, 0, 0));
        jLabel52.setText("Rg. Rwt. Inap naik kls. : ");
        jLabel52.setName("jLabel52"); // NOI18N
        internalFrame10.add(jLabel52);
        jLabel52.setBounds(0, 79, 130, 23);

        rginap.setEditable(false);
        rginap.setForeground(new java.awt.Color(0, 0, 0));
        rginap.setHighlighter(null);
        rginap.setName("rginap"); // NOI18N
        rginap.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                rginapKeyPressed(evt);
            }
        });
        internalFrame10.add(rginap);
        rginap.setBounds(131, 79, 575, 23);

        jLabel53.setForeground(new java.awt.Color(0, 0, 0));
        jLabel53.setText("Hak Kelas BPJS : ");
        jLabel53.setName("jLabel53"); // NOI18N
        internalFrame10.add(jLabel53);
        jLabel53.setBounds(0, 178, 102, 23);

        hakkelas.setEditable(false);
        hakkelas.setForeground(new java.awt.Color(0, 0, 0));
        hakkelas.setHorizontalAlignment(javax.swing.JTextField.CENTER);
        hakkelas.setHighlighter(null);
        hakkelas.setName("hakkelas"); // NOI18N
        hakkelas.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                hakkelasKeyPressed(evt);
            }
        });
        internalFrame10.add(hakkelas);
        hakkelas.setBounds(106, 178, 40, 23);

        jLabel54.setForeground(new java.awt.Color(0, 0, 0));
        jLabel54.setText("Tarif : Kelas 1 Rp.");
        jLabel54.setName("jLabel54"); // NOI18N
        internalFrame10.add(jLabel54);
        jLabel54.setBounds(0, 133, 102, 23);

        kdINACBG.setForeground(new java.awt.Color(0, 0, 0));
        kdINACBG.setHighlighter(null);
        kdINACBG.setMaxLenth(15);
        kdINACBG.setName("kdINACBG"); // NOI18N
        kdINACBG.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyTyped(java.awt.event.KeyEvent evt) {
                kdINACBGKeyTyped(evt);
            }
            public void keyPressed(java.awt.event.KeyEvent evt) {
                kdINACBGKeyPressed(evt);
            }
        });
        internalFrame10.add(kdINACBG);
        kdINACBG.setBounds(106, 106, 90, 23);

        deskripsiKD.setEditable(false);
        deskripsiKD.setForeground(new java.awt.Color(0, 0, 0));
        deskripsiKD.setHighlighter(null);
        deskripsiKD.setName("deskripsiKD"); // NOI18N
        deskripsiKD.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                deskripsiKDKeyPressed(evt);
            }
        });
        internalFrame10.add(deskripsiKD);
        deskripsiKD.setBounds(198, 106, 508, 23);

        jLabel55.setForeground(new java.awt.Color(0, 0, 0));
        jLabel55.setText("--- Penghitungan selisih tarif (Permenkes RI No. 51 Tahun 2018) -------------------------------------------------------------------------------------------------------------------------------------------------------------");
        jLabel55.setFont(new java.awt.Font("Tahoma", 1, 11)); // NOI18N
        jLabel55.setName("jLabel55"); // NOI18N
        internalFrame10.add(jLabel55);
        jLabel55.setBounds(0, 160, 850, 14);

        jLabel56.setForeground(new java.awt.Color(0, 0, 250));
        jLabel56.setText("Kode INACBG : ");
        jLabel56.setFont(new java.awt.Font("Tahoma", 1, 11)); // NOI18N
        jLabel56.setName("jLabel56"); // NOI18N
        internalFrame10.add(jLabel56);
        jLabel56.setBounds(0, 106, 102, 23);

        tarifkls1.setEditable(false);
        tarifkls1.setForeground(new java.awt.Color(0, 0, 0));
        tarifkls1.setText("0");
        tarifkls1.setHighlighter(null);
        tarifkls1.setName("tarifkls1"); // NOI18N
        tarifkls1.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyTyped(java.awt.event.KeyEvent evt) {
                tarifkls1KeyTyped(evt);
            }
            public void keyPressed(java.awt.event.KeyEvent evt) {
                tarifkls1KeyPressed(evt);
            }
        });
        internalFrame10.add(tarifkls1);
        tarifkls1.setBounds(106, 133, 120, 23);

        jLabel57.setForeground(new java.awt.Color(0, 0, 0));
        jLabel57.setText("Kelas 2 Rp.");
        jLabel57.setName("jLabel57"); // NOI18N
        internalFrame10.add(jLabel57);
        jLabel57.setBounds(230, 133, 60, 23);

        tarifkls2.setEditable(false);
        tarifkls2.setForeground(new java.awt.Color(0, 0, 0));
        tarifkls2.setText("0");
        tarifkls2.setHighlighter(null);
        tarifkls2.setName("tarifkls2"); // NOI18N
        tarifkls2.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyTyped(java.awt.event.KeyEvent evt) {
                tarifkls2KeyTyped(evt);
            }
            public void keyPressed(java.awt.event.KeyEvent evt) {
                tarifkls2KeyPressed(evt);
            }
        });
        internalFrame10.add(tarifkls2);
        tarifkls2.setBounds(293, 133, 120, 23);

        jLabel58.setForeground(new java.awt.Color(0, 0, 0));
        jLabel58.setText("Kelas 3 Rp.");
        jLabel58.setName("jLabel58"); // NOI18N
        internalFrame10.add(jLabel58);
        jLabel58.setBounds(420, 133, 60, 23);

        tarifkls3.setEditable(false);
        tarifkls3.setForeground(new java.awt.Color(0, 0, 0));
        tarifkls3.setText("0");
        tarifkls3.setHighlighter(null);
        tarifkls3.setName("tarifkls3"); // NOI18N
        tarifkls3.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyTyped(java.awt.event.KeyEvent evt) {
                tarifkls3KeyTyped(evt);
            }
            public void keyPressed(java.awt.event.KeyEvent evt) {
                tarifkls3KeyPressed(evt);
            }
        });
        internalFrame10.add(tarifkls3);
        tarifkls3.setBounds(484, 133, 120, 23);

        jLabel59.setForeground(new java.awt.Color(0, 0, 0));
        jLabel59.setText("Naik ke : ");
        jLabel59.setName("jLabel59"); // NOI18N
        internalFrame10.add(jLabel59);
        jLabel59.setBounds(152, 178, 46, 23);

        jLabel60.setForeground(new java.awt.Color(0, 0, 0));
        jLabel60.setText("Lama rawat Rg. VIP : ");
        jLabel60.setName("jLabel60"); // NOI18N
        internalFrame10.add(jLabel60);
        jLabel60.setBounds(290, 178, 110, 23);

        lmrawat.setEditable(false);
        lmrawat.setForeground(new java.awt.Color(0, 0, 0));
        lmrawat.setHorizontalAlignment(javax.swing.JTextField.CENTER);
        lmrawat.setText("0");
        lmrawat.setHighlighter(null);
        lmrawat.setName("lmrawat"); // NOI18N
        lmrawat.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                lmrawatKeyPressed(evt);
            }
        });
        internalFrame10.add(lmrawat);
        lmrawat.setBounds(400, 178, 40, 23);

        dibayar.setEditable(false);
        dibayar.setForeground(new java.awt.Color(0, 0, 0));
        dibayar.setText("0");
        dibayar.setFont(new java.awt.Font("Tahoma", 1, 11)); // NOI18N
        dibayar.setHighlighter(null);
        dibayar.setName("dibayar"); // NOI18N
        dibayar.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyTyped(java.awt.event.KeyEvent evt) {
                dibayarKeyTyped(evt);
            }
            public void keyPressed(java.awt.event.KeyEvent evt) {
                dibayarKeyPressed(evt);
            }
        });
        internalFrame10.add(dibayar);
        dibayar.setBounds(610, 206, 95, 23);

        jLabel61.setForeground(new java.awt.Color(0, 0, 0));
        jLabel61.setText("hari,   Persentase tambahan kelas VIP : ");
        jLabel61.setName("jLabel61"); // NOI18N
        internalFrame10.add(jLabel61);
        jLabel61.setBounds(442, 178, 195, 23);

        persenSELISIH.setEditable(false);
        persenSELISIH.setForeground(new java.awt.Color(0, 0, 0));
        persenSELISIH.setHorizontalAlignment(javax.swing.JTextField.CENTER);
        persenSELISIH.setText("0");
        persenSELISIH.setHighlighter(null);
        persenSELISIH.setName("persenSELISIH"); // NOI18N
        persenSELISIH.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                persenSELISIHKeyPressed(evt);
            }
        });
        internalFrame10.add(persenSELISIH);
        persenSELISIH.setBounds(640, 178, 45, 23);

        jLabel62.setForeground(new java.awt.Color(0, 0, 0));
        jLabel62.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        jLabel62.setText("%");
        jLabel62.setName("jLabel62"); // NOI18N
        internalFrame10.add(jLabel62);
        jLabel62.setBounds(690, 178, 20, 23);

        labelbyr.setForeground(new java.awt.Color(0, 0, 0));
        labelbyr.setText("Total bayar : Rp. ");
        labelbyr.setName("labelbyr"); // NOI18N
        internalFrame10.add(labelbyr);
        labelbyr.setBounds(0, 206, 610, 23);

        naikKLS.setEditable(false);
        naikKLS.setForeground(new java.awt.Color(0, 0, 0));
        naikKLS.setHighlighter(null);
        naikKLS.setName("naikKLS"); // NOI18N
        naikKLS.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyTyped(java.awt.event.KeyEvent evt) {
                naikKLSKeyTyped(evt);
            }
            public void keyPressed(java.awt.event.KeyEvent evt) {
                naikKLSKeyPressed(evt);
            }
        });
        internalFrame10.add(naikKLS);
        naikKLS.setBounds(199, 178, 90, 24);

        BtnGantikode.setForeground(new java.awt.Color(0, 0, 0));
        BtnGantikode.setIcon(new javax.swing.ImageIcon(getClass().getResource("/picture/inventaris.png"))); // NOI18N
        BtnGantikode.setMnemonic('S');
        BtnGantikode.setText("Ganti");
        BtnGantikode.setToolTipText("Alt+S");
        BtnGantikode.setName("BtnGantikode"); // NOI18N
        BtnGantikode.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                BtnGantikodeActionPerformed(evt);
            }
        });
        internalFrame10.add(BtnGantikode);
        BtnGantikode.setBounds(105, 235, 90, 30);

        WindowSelisihTarif.getContentPane().add(internalFrame10, java.awt.BorderLayout.CENTER);

        WindowGiziBuruk.setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);
        WindowGiziBuruk.setName("WindowGiziBuruk"); // NOI18N
        WindowGiziBuruk.setUndecorated(true);
        WindowGiziBuruk.setResizable(false);

        internalFrame13.setBorder(javax.swing.BorderFactory.createTitledBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 153, 255), 3), "::[ Data Gizi Buruk Pasien ]::", javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Tahoma", 1, 12))); // NOI18N
        internalFrame13.setName("internalFrame13"); // NOI18N
        internalFrame13.setWarnaBawah(new java.awt.Color(245, 250, 240));
        internalFrame13.setLayout(new java.awt.BorderLayout());

        Scroll3.setName("Scroll3"); // NOI18N
        Scroll3.setOpaque(true);

        tbGiziBuruk.setAutoCreateRowSorter(true);
        tbGiziBuruk.setToolTipText("Silahkan klik untuk memilih data yang mau diedit ataupun dihapus");
        tbGiziBuruk.setComponentPopupMenu(jPopupMenu2);
        tbGiziBuruk.setName("tbGiziBuruk"); // NOI18N
        tbGiziBuruk.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                tbGiziBurukMouseClicked(evt);
            }
        });
        tbGiziBuruk.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                tbGiziBurukKeyPressed(evt);
            }
        });
        Scroll3.setViewportView(tbGiziBuruk);

        internalFrame13.add(Scroll3, java.awt.BorderLayout.CENTER);

        jPanel5.setName("jPanel5"); // NOI18N
        jPanel5.setOpaque(false);
        jPanel5.setPreferredSize(new java.awt.Dimension(44, 100));
        jPanel5.setLayout(new java.awt.BorderLayout(1, 1));

        panelGlass9.setName("panelGlass9"); // NOI18N
        panelGlass9.setPreferredSize(new java.awt.Dimension(44, 44));
        panelGlass9.setLayout(new java.awt.FlowLayout(java.awt.FlowLayout.LEFT, 5, 9));

        jLabel91.setForeground(new java.awt.Color(0, 0, 0));
        jLabel91.setText("Tgl. Laporan : ");
        jLabel91.setName("jLabel91"); // NOI18N
        panelGlass9.add(jLabel91);

        DTPCari9.setEditable(false);
        DTPCari9.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "25-12-2021" }));
        DTPCari9.setDisplayFormat("dd-MM-yyyy");
        DTPCari9.setName("DTPCari9"); // NOI18N
        DTPCari9.setOpaque(false);
        DTPCari9.setPreferredSize(new java.awt.Dimension(95, 23));
        DTPCari9.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                DTPCari9MouseClicked(evt);
            }
        });
        panelGlass9.add(DTPCari9);

        jLabel92.setForeground(new java.awt.Color(0, 0, 0));
        jLabel92.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel92.setText("s.d");
        jLabel92.setName("jLabel92"); // NOI18N
        panelGlass9.add(jLabel92);

        DTPCari10.setEditable(false);
        DTPCari10.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "25-12-2021" }));
        DTPCari10.setDisplayFormat("dd-MM-yyyy");
        DTPCari10.setName("DTPCari10"); // NOI18N
        DTPCari10.setOpaque(false);
        DTPCari10.setPreferredSize(new java.awt.Dimension(95, 23));
        DTPCari10.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                DTPCari10MouseClicked(evt);
            }
        });
        panelGlass9.add(DTPCari10);

        jLabel90.setForeground(new java.awt.Color(0, 0, 0));
        jLabel90.setText("Key Word :");
        jLabel90.setName("jLabel90"); // NOI18N
        jLabel90.setPreferredSize(new java.awt.Dimension(70, 23));
        jLabel90.setRequestFocusEnabled(false);
        panelGlass9.add(jLabel90);

        TCari3.setForeground(new java.awt.Color(0, 0, 0));
        TCari3.setName("TCari3"); // NOI18N
        TCari3.setPreferredSize(new java.awt.Dimension(350, 23));
        TCari3.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                TCari3KeyPressed(evt);
            }
        });
        panelGlass9.add(TCari3);

        BtnCari3.setForeground(new java.awt.Color(0, 0, 0));
        BtnCari3.setIcon(new javax.swing.ImageIcon(getClass().getResource("/picture/accept.png"))); // NOI18N
        BtnCari3.setMnemonic('1');
        BtnCari3.setText("Tampilkan Data");
        BtnCari3.setToolTipText("Alt+1");
        BtnCari3.setName("BtnCari3"); // NOI18N
        BtnCari3.setPreferredSize(new java.awt.Dimension(130, 23));
        BtnCari3.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                BtnCari3ActionPerformed(evt);
            }
        });
        BtnCari3.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                BtnCari3KeyPressed(evt);
            }
            public void keyReleased(java.awt.event.KeyEvent evt) {
                BtnCari3KeyReleased(evt);
            }
        });
        panelGlass9.add(BtnCari3);

        jPanel5.add(panelGlass9, java.awt.BorderLayout.PAGE_START);

        panelGlass12.setName("panelGlass12"); // NOI18N
        panelGlass12.setPreferredSize(new java.awt.Dimension(44, 44));
        panelGlass12.setLayout(new java.awt.FlowLayout(java.awt.FlowLayout.LEFT, 5, 9));

        BtnSimpan7.setForeground(new java.awt.Color(0, 0, 0));
        BtnSimpan7.setIcon(new javax.swing.ImageIcon(getClass().getResource("/picture/save-16x16.png"))); // NOI18N
        BtnSimpan7.setMnemonic('S');
        BtnSimpan7.setText("Simpan");
        BtnSimpan7.setToolTipText("Alt+S");
        BtnSimpan7.setName("BtnSimpan7"); // NOI18N
        BtnSimpan7.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                BtnSimpan7ActionPerformed(evt);
            }
        });
        panelGlass12.add(BtnSimpan7);

        BtnHapus.setForeground(new java.awt.Color(0, 0, 0));
        BtnHapus.setIcon(new javax.swing.ImageIcon(getClass().getResource("/picture/stop_f2.png"))); // NOI18N
        BtnHapus.setMnemonic('H');
        BtnHapus.setText("Hapus");
        BtnHapus.setToolTipText("Alt+H");
        BtnHapus.setName("BtnHapus"); // NOI18N
        BtnHapus.setPreferredSize(new java.awt.Dimension(100, 30));
        BtnHapus.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                BtnHapusActionPerformed(evt);
            }
        });
        BtnHapus.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                BtnHapusKeyPressed(evt);
            }
        });
        panelGlass12.add(BtnHapus);

        BtnEdit2.setForeground(new java.awt.Color(0, 0, 0));
        BtnEdit2.setIcon(new javax.swing.ImageIcon(getClass().getResource("/picture/inventaris.png"))); // NOI18N
        BtnEdit2.setMnemonic('G');
        BtnEdit2.setText("Ganti");
        BtnEdit2.setToolTipText("Alt+G");
        BtnEdit2.setName("BtnEdit2"); // NOI18N
        BtnEdit2.setPreferredSize(new java.awt.Dimension(100, 30));
        BtnEdit2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                BtnEdit2ActionPerformed(evt);
            }
        });
        BtnEdit2.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                BtnEdit2KeyPressed(evt);
            }
        });
        panelGlass12.add(BtnEdit2);

        BtnCetakGB.setForeground(new java.awt.Color(0, 0, 0));
        BtnCetakGB.setIcon(new javax.swing.ImageIcon(getClass().getResource("/picture/b_print.png"))); // NOI18N
        BtnCetakGB.setMnemonic('L');
        BtnCetakGB.setText("Cetak Lap. Rekap");
        BtnCetakGB.setToolTipText("Alt+L");
        BtnCetakGB.setName("BtnCetakGB"); // NOI18N
        BtnCetakGB.setPreferredSize(new java.awt.Dimension(140, 30));
        BtnCetakGB.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                BtnCetakGBActionPerformed(evt);
            }
        });
        BtnCetakGB.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                BtnCetakGBKeyPressed(evt);
            }
        });
        panelGlass12.add(BtnCetakGB);

        BtnAll3.setForeground(new java.awt.Color(0, 0, 0));
        BtnAll3.setIcon(new javax.swing.ImageIcon(getClass().getResource("/picture/Search-16x16.png"))); // NOI18N
        BtnAll3.setMnemonic('2');
        BtnAll3.setText("Semua Data");
        BtnAll3.setToolTipText("Alt+2");
        BtnAll3.setName("BtnAll3"); // NOI18N
        BtnAll3.setPreferredSize(new java.awt.Dimension(120, 26));
        BtnAll3.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                BtnAll3ActionPerformed(evt);
            }
        });
        BtnAll3.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                BtnAll3KeyPressed(evt);
            }
        });
        panelGlass12.add(BtnAll3);

        BtnCloseIn8.setForeground(new java.awt.Color(0, 0, 0));
        BtnCloseIn8.setIcon(new javax.swing.ImageIcon(getClass().getResource("/picture/cross.png"))); // NOI18N
        BtnCloseIn8.setMnemonic('U');
        BtnCloseIn8.setText("Tutup");
        BtnCloseIn8.setToolTipText("Alt+U");
        BtnCloseIn8.setName("BtnCloseIn8"); // NOI18N
        BtnCloseIn8.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                BtnCloseIn8ActionPerformed(evt);
            }
        });
        panelGlass12.add(BtnCloseIn8);

        jPanel5.add(panelGlass12, java.awt.BorderLayout.CENTER);

        internalFrame13.add(jPanel5, java.awt.BorderLayout.PAGE_END);

        PanelInput1.setName("PanelInput1"); // NOI18N
        PanelInput1.setOpaque(false);
        PanelInput1.setPreferredSize(new java.awt.Dimension(440, 280));
        PanelInput1.setLayout(new java.awt.BorderLayout(1, 1));

        FormInput.setName("FormInput"); // NOI18N
        FormInput.setPreferredSize(new java.awt.Dimension(440, 77));
        FormInput.setLayout(null);

        jLabel71.setForeground(new java.awt.Color(0, 0, 0));
        jLabel71.setText("Pasien : ");
        jLabel71.setName("jLabel71"); // NOI18N
        FormInput.add(jLabel71);
        jLabel71.setBounds(0, 10, 77, 23);

        norwGB.setEditable(false);
        norwGB.setForeground(new java.awt.Color(0, 0, 0));
        norwGB.setHighlighter(null);
        norwGB.setName("norwGB"); // NOI18N
        norwGB.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                norwGBKeyPressed(evt);
            }
        });
        FormInput.add(norwGB);
        norwGB.setBounds(80, 10, 140, 23);

        normGB.setEditable(false);
        normGB.setForeground(new java.awt.Color(0, 0, 0));
        normGB.setName("normGB"); // NOI18N
        FormInput.add(normGB);
        normGB.setBounds(224, 10, 70, 23);

        nmpxGB.setEditable(false);
        nmpxGB.setForeground(new java.awt.Color(0, 0, 0));
        nmpxGB.setName("nmpxGB"); // NOI18N
        FormInput.add(nmpxGB);
        nmpxGB.setBounds(298, 10, 423, 23);

        jLabel72.setForeground(new java.awt.Color(0, 0, 0));
        jLabel72.setText("Umur : ");
        jLabel72.setName("jLabel72"); // NOI18N
        FormInput.add(jLabel72);
        jLabel72.setBounds(0, 37, 77, 23);

        umurGB.setEditable(false);
        umurGB.setForeground(new java.awt.Color(0, 0, 0));
        umurGB.setName("umurGB"); // NOI18N
        FormInput.add(umurGB);
        umurGB.setBounds(80, 37, 60, 23);

        jLabel73.setForeground(new java.awt.Color(0, 0, 0));
        jLabel73.setText("Diagnosa Awal : ");
        jLabel73.setName("jLabel73"); // NOI18N
        FormInput.add(jLabel73);
        jLabel73.setBounds(144, 37, 90, 23);

        diagawalGB.setForeground(new java.awt.Color(0, 0, 0));
        diagawalGB.setName("diagawalGB"); // NOI18N
        diagawalGB.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                diagawalGBKeyPressed(evt);
            }
        });
        FormInput.add(diagawalGB);
        diagawalGB.setBounds(235, 37, 485, 23);

        jLabel74.setForeground(new java.awt.Color(0, 0, 0));
        jLabel74.setText("BB - Awal : ");
        jLabel74.setName("jLabel74"); // NOI18N
        FormInput.add(jLabel74);
        jLabel74.setBounds(0, 64, 77, 23);

        bbAwal.setForeground(new java.awt.Color(0, 0, 0));
        bbAwal.setMaxLenth(10);
        bbAwal.setName("bbAwal"); // NOI18N
        bbAwal.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                bbAwalKeyPressed(evt);
            }
        });
        FormInput.add(bbAwal);
        bbAwal.setBounds(80, 64, 60, 23);

        jLabel77.setForeground(new java.awt.Color(0, 0, 0));
        jLabel77.setText("Perhitungan status gizi WHONCHS : ");
        jLabel77.setName("jLabel77"); // NOI18N
        FormInput.add(jLabel77);
        jLabel77.setBounds(144, 64, 180, 23);

        jLabel78.setForeground(new java.awt.Color(0, 0, 0));
        jLabel78.setText("BB / U : ");
        jLabel78.setName("jLabel78"); // NOI18N
        FormInput.add(jLabel78);
        jLabel78.setBounds(327, 64, 50, 23);

        bbu.setForeground(new java.awt.Color(0, 0, 0));
        bbu.setName("bbu"); // NOI18N
        bbu.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                bbuKeyPressed(evt);
            }
        });
        FormInput.add(bbu);
        bbu.setBounds(380, 64, 340, 23);

        jLabel75.setForeground(new java.awt.Color(0, 0, 0));
        jLabel75.setText("BB - Akhir : ");
        jLabel75.setName("jLabel75"); // NOI18N
        FormInput.add(jLabel75);
        jLabel75.setBounds(0, 92, 77, 23);

        bbAkhir.setForeground(new java.awt.Color(0, 0, 0));
        bbAkhir.setMaxLenth(10);
        bbAkhir.setName("bbAkhir"); // NOI18N
        bbAkhir.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                bbAkhirKeyPressed(evt);
            }
        });
        FormInput.add(bbAkhir);
        bbAkhir.setBounds(80, 92, 60, 23);

        jLabel79.setForeground(new java.awt.Color(0, 0, 0));
        jLabel79.setText("BB / PB : ");
        jLabel79.setName("jLabel79"); // NOI18N
        FormInput.add(jLabel79);
        jLabel79.setBounds(327, 92, 50, 23);

        bbpb.setForeground(new java.awt.Color(0, 0, 0));
        bbpb.setName("bbpb"); // NOI18N
        bbpb.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                bbpbKeyPressed(evt);
            }
        });
        FormInput.add(bbpb);
        bbpb.setBounds(380, 92, 340, 23);

        jLabel76.setForeground(new java.awt.Color(0, 0, 0));
        jLabel76.setText("PB / TB : ");
        jLabel76.setName("jLabel76"); // NOI18N
        FormInput.add(jLabel76);
        jLabel76.setBounds(0, 119, 77, 23);

        pbtb.setForeground(new java.awt.Color(0, 0, 0));
        pbtb.setMaxLenth(10);
        pbtb.setName("pbtb"); // NOI18N
        pbtb.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                pbtbKeyPressed(evt);
            }
        });
        FormInput.add(pbtb);
        pbtb.setBounds(80, 119, 60, 23);

        jLabel80.setForeground(new java.awt.Color(0, 0, 0));
        jLabel80.setText("PB / U : ");
        jLabel80.setName("jLabel80"); // NOI18N
        FormInput.add(jLabel80);
        jLabel80.setBounds(327, 119, 50, 23);

        pbu.setForeground(new java.awt.Color(0, 0, 0));
        pbu.setName("pbu"); // NOI18N
        pbu.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                pbuKeyPressed(evt);
            }
        });
        FormInput.add(pbu);
        pbu.setBounds(380, 119, 340, 23);

        jLabel81.setForeground(new java.awt.Color(0, 0, 0));
        jLabel81.setText("Perhitungan Keb. Zat Gizi : ");
        jLabel81.setName("jLabel81"); // NOI18N
        FormInput.add(jLabel81);
        jLabel81.setBounds(0, 147, 140, 23);

        jLabel82.setForeground(new java.awt.Color(0, 0, 0));
        jLabel82.setText("Diagnosa dr. Sp. Gz/Ahli Gizi : ");
        jLabel82.setName("jLabel82"); // NOI18N
        FormInput.add(jLabel82);
        jLabel82.setBounds(0, 175, 160, 23);

        jLabel83.setForeground(new java.awt.Color(0, 0, 0));
        jLabel83.setText("Pemberian Nutrisi : ");
        jLabel83.setName("jLabel83"); // NOI18N
        FormInput.add(jLabel83);
        jLabel83.setBounds(0, 203, 140, 23);

        pemberianNutrisi.setForeground(new java.awt.Color(0, 0, 0));
        pemberianNutrisi.setName("pemberianNutrisi"); // NOI18N
        pemberianNutrisi.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                pemberianNutrisiKeyPressed(evt);
            }
        });
        FormInput.add(pemberianNutrisi);
        pemberianNutrisi.setBounds(142, 203, 578, 23);

        diagDokterGZ.setForeground(new java.awt.Color(0, 0, 0));
        diagDokterGZ.setName("diagDokterGZ"); // NOI18N
        diagDokterGZ.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                diagDokterGZKeyPressed(evt);
            }
        });
        FormInput.add(diagDokterGZ);
        diagDokterGZ.setBounds(162, 175, 558, 23);

        perhitunganZatGZ.setForeground(new java.awt.Color(0, 0, 0));
        perhitunganZatGZ.setName("perhitunganZatGZ"); // NOI18N
        perhitunganZatGZ.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                perhitunganZatGZKeyPressed(evt);
            }
        });
        FormInput.add(perhitunganZatGZ);
        perhitunganZatGZ.setBounds(142, 147, 578, 23);

        jLabel84.setForeground(new java.awt.Color(0, 0, 0));
        jLabel84.setText("Data Pendukung (Hasil Lab.) : ");
        jLabel84.setName("jLabel84"); // NOI18N
        FormInput.add(jLabel84);
        jLabel84.setBounds(730, 10, 160, 23);

        jLabel85.setForeground(new java.awt.Color(0, 0, 0));
        jLabel85.setText("Albumin : ");
        jLabel85.setName("jLabel85"); // NOI18N
        FormInput.add(jLabel85);
        jLabel85.setBounds(730, 37, 60, 23);

        albumin.setForeground(new java.awt.Color(0, 0, 0));
        albumin.setName("albumin"); // NOI18N
        albumin.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                albuminKeyPressed(evt);
            }
        });
        FormInput.add(albumin);
        albumin.setBounds(790, 37, 160, 23);

        jLabel87.setForeground(new java.awt.Color(0, 0, 0));
        jLabel87.setText("Leukosit : ");
        jLabel87.setName("jLabel87"); // NOI18N
        FormInput.add(jLabel87);
        jLabel87.setBounds(730, 92, 60, 23);

        leukosit.setForeground(new java.awt.Color(0, 0, 0));
        leukosit.setName("leukosit"); // NOI18N
        leukosit.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                leukositKeyPressed(evt);
            }
        });
        FormInput.add(leukosit);
        leukosit.setBounds(790, 92, 160, 23);

        plt.setForeground(new java.awt.Color(0, 0, 0));
        plt.setName("plt"); // NOI18N
        plt.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                pltKeyPressed(evt);
            }
        });
        FormInput.add(plt);
        plt.setBounds(790, 119, 160, 23);

        jLabel88.setForeground(new java.awt.Color(0, 0, 0));
        jLabel88.setText("PLT : ");
        jLabel88.setName("jLabel88"); // NOI18N
        FormInput.add(jLabel88);
        jLabel88.setBounds(730, 119, 60, 23);

        hb.setForeground(new java.awt.Color(0, 0, 0));
        hb.setName("hb"); // NOI18N
        hb.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                hbKeyPressed(evt);
            }
        });
        FormInput.add(hb);
        hb.setBounds(790, 64, 160, 23);

        jLabel86.setForeground(new java.awt.Color(0, 0, 0));
        jLabel86.setText("Hb : ");
        jLabel86.setName("jLabel86"); // NOI18N
        FormInput.add(jLabel86);
        jLabel86.setBounds(730, 64, 60, 23);

        jLabel89.setForeground(new java.awt.Color(0, 0, 0));
        jLabel89.setText("Puskesmas Daerah Asal : ");
        jLabel89.setName("jLabel89"); // NOI18N
        FormInput.add(jLabel89);
        jLabel89.setBounds(0, 230, 140, 23);

        asalFaskes.setEditable(false);
        asalFaskes.setForeground(new java.awt.Color(0, 0, 0));
        asalFaskes.setName("asalFaskes"); // NOI18N
        FormInput.add(asalFaskes);
        asalFaskes.setBounds(142, 230, 352, 23);

        btnFaskes.setForeground(new java.awt.Color(0, 0, 0));
        btnFaskes.setIcon(new javax.swing.ImageIcon(getClass().getResource("/picture/190.png"))); // NOI18N
        btnFaskes.setMnemonic('3');
        btnFaskes.setToolTipText("Alt+3");
        btnFaskes.setName("btnFaskes"); // NOI18N
        btnFaskes.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnFaskesActionPerformed(evt);
            }
        });
        btnFaskes.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                btnFaskesKeyPressed(evt);
            }
        });
        FormInput.add(btnFaskes);
        btnFaskes.setBounds(500, 230, 28, 23);

        PanelInput1.add(FormInput, java.awt.BorderLayout.CENTER);

        ChkInput.setForeground(new java.awt.Color(0, 0, 0));
        ChkInput.setIcon(new javax.swing.ImageIcon(getClass().getResource("/picture/143.png"))); // NOI18N
        ChkInput.setMnemonic('I');
        ChkInput.setText(".: Input Data");
        ChkInput.setToolTipText("Alt+I");
        ChkInput.setBorderPainted(true);
        ChkInput.setBorderPaintedFlat(true);
        ChkInput.setFocusable(false);
        ChkInput.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        ChkInput.setHorizontalTextPosition(javax.swing.SwingConstants.RIGHT);
        ChkInput.setName("ChkInput"); // NOI18N
        ChkInput.setPreferredSize(new java.awt.Dimension(192, 20));
        ChkInput.setRolloverIcon(new javax.swing.ImageIcon(getClass().getResource("/picture/143.png"))); // NOI18N
        ChkInput.setRolloverSelectedIcon(new javax.swing.ImageIcon(getClass().getResource("/picture/145.png"))); // NOI18N
        ChkInput.setSelectedIcon(new javax.swing.ImageIcon(getClass().getResource("/picture/145.png"))); // NOI18N
        ChkInput.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                ChkInputActionPerformed(evt);
            }
        });
        PanelInput1.add(ChkInput, java.awt.BorderLayout.PAGE_END);

        internalFrame13.add(PanelInput1, java.awt.BorderLayout.PAGE_START);

        WindowGiziBuruk.getContentPane().add(internalFrame13, java.awt.BorderLayout.CENTER);

        WindowStatusPulang.setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);
        WindowStatusPulang.setName("WindowStatusPulang"); // NOI18N
        WindowStatusPulang.setUndecorated(true);
        WindowStatusPulang.setResizable(false);

        internalFrame14.setBorder(javax.swing.BorderFactory.createTitledBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 153, 255), 3), "::[ Ganti Status Pulang ]::", javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Tahoma", 1, 12))); // NOI18N
        internalFrame14.setName("internalFrame14"); // NOI18N
        internalFrame14.setWarnaBawah(new java.awt.Color(245, 250, 240));
        internalFrame14.setLayout(null);

        BtnCloseIn9.setForeground(new java.awt.Color(0, 0, 0));
        BtnCloseIn9.setIcon(new javax.swing.ImageIcon(getClass().getResource("/picture/cross.png"))); // NOI18N
        BtnCloseIn9.setMnemonic('U');
        BtnCloseIn9.setText("Tutup");
        BtnCloseIn9.setToolTipText("Alt+U");
        BtnCloseIn9.setName("BtnCloseIn9"); // NOI18N
        BtnCloseIn9.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                BtnCloseIn9ActionPerformed(evt);
            }
        });
        internalFrame14.add(BtnCloseIn9);
        BtnCloseIn9.setBounds(380, 30, 80, 30);

        BtnSimpan8.setForeground(new java.awt.Color(0, 0, 0));
        BtnSimpan8.setIcon(new javax.swing.ImageIcon(getClass().getResource("/picture/save-16x16.png"))); // NOI18N
        BtnSimpan8.setMnemonic('S');
        BtnSimpan8.setText("Simpan");
        BtnSimpan8.setToolTipText("Alt+S");
        BtnSimpan8.setName("BtnSimpan8"); // NOI18N
        BtnSimpan8.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                BtnSimpan8ActionPerformed(evt);
            }
        });
        internalFrame14.add(BtnSimpan8);
        BtnSimpan8.setBounds(285, 30, 90, 30);

        jLabel93.setForeground(new java.awt.Color(0, 0, 0));
        jLabel93.setText("Pilih Status Pulang : ");
        jLabel93.setName("jLabel93"); // NOI18N
        internalFrame14.add(jLabel93);
        jLabel93.setBounds(0, 32, 120, 23);

        cmbSttsPlg.setForeground(new java.awt.Color(0, 0, 0));
        cmbSttsPlg.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "- pilih salah satu -", "Dirujuk", "APS", "Meninggal >= 48 Jam", "Meninggal < 48 Jam", "Sembuh/BLPL", "Kabur" }));
        cmbSttsPlg.setLightWeightPopupEnabled(false);
        cmbSttsPlg.setName("cmbSttsPlg"); // NOI18N
        cmbSttsPlg.setOpaque(false);
        cmbSttsPlg.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                cmbSttsPlgMouseClicked(evt);
            }
        });
        cmbSttsPlg.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                cmbSttsPlgKeyPressed(evt);
            }
        });
        internalFrame14.add(cmbSttsPlg);
        cmbSttsPlg.setBounds(125, 32, 150, 23);

        WindowStatusPulang.getContentPane().add(internalFrame14, java.awt.BorderLayout.CENTER);

        WindowPulangAPS.setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);
        WindowPulangAPS.setName("WindowPulangAPS"); // NOI18N
        WindowPulangAPS.setUndecorated(true);
        WindowPulangAPS.setResizable(false);

        internalFrame16.setBorder(javax.swing.BorderFactory.createTitledBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 153, 255), 3), "::[ Pernyataan Alasan Status Pulang APS ]::", javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Tahoma", 1, 12))); // NOI18N
        internalFrame16.setName("internalFrame16"); // NOI18N
        internalFrame16.setWarnaBawah(new java.awt.Color(245, 250, 240));
        internalFrame16.setLayout(null);

        BtnCloseIn11.setForeground(new java.awt.Color(0, 0, 0));
        BtnCloseIn11.setIcon(new javax.swing.ImageIcon(getClass().getResource("/picture/cross.png"))); // NOI18N
        BtnCloseIn11.setMnemonic('U');
        BtnCloseIn11.setText("Tutup");
        BtnCloseIn11.setToolTipText("Alt+U");
        BtnCloseIn11.setName("BtnCloseIn11"); // NOI18N
        BtnCloseIn11.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                BtnCloseIn11ActionPerformed(evt);
            }
        });
        internalFrame16.add(BtnCloseIn11);
        BtnCloseIn11.setBounds(360, 242, 80, 30);

        BtnSimpan9.setForeground(new java.awt.Color(0, 0, 0));
        BtnSimpan9.setIcon(new javax.swing.ImageIcon(getClass().getResource("/picture/save-16x16.png"))); // NOI18N
        BtnSimpan9.setMnemonic('S');
        BtnSimpan9.setText("Simpan");
        BtnSimpan9.setToolTipText("Alt+S");
        BtnSimpan9.setName("BtnSimpan9"); // NOI18N
        BtnSimpan9.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                BtnSimpan9ActionPerformed(evt);
            }
        });
        internalFrame16.add(BtnSimpan9);
        BtnSimpan9.setBounds(95, 242, 90, 30);

        jLabel96.setForeground(new java.awt.Color(0, 0, 0));
        jLabel96.setText("Alasan APS :");
        jLabel96.setName("jLabel96"); // NOI18N
        internalFrame16.add(jLabel96);
        jLabel96.setBounds(0, 157, 81, 23);

        Scroll5.setName("Scroll5"); // NOI18N
        Scroll5.setOpaque(true);

        tbAPS.setAutoCreateRowSorter(true);
        tbAPS.setToolTipText("Silahkan klik untuk memilih data yang mau diedit ataupun dihapus");
        tbAPS.setName("tbAPS"); // NOI18N
        tbAPS.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                tbAPSMouseClicked(evt);
            }
        });
        tbAPS.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                tbAPSKeyPressed(evt);
            }
        });
        Scroll5.setViewportView(tbAPS);

        internalFrame16.add(Scroll5);
        Scroll5.setBounds(10, 20, 430, 130);

        jLabel98.setForeground(new java.awt.Color(0, 0, 0));
        jLabel98.setText("Key Word :");
        jLabel98.setName("jLabel98"); // NOI18N
        jLabel98.setPreferredSize(new java.awt.Dimension(158, 23));
        internalFrame16.add(jLabel98);
        jLabel98.setBounds(0, 213, 81, 23);

        TCari4.setForeground(new java.awt.Color(0, 0, 0));
        TCari4.setName("TCari4"); // NOI18N
        TCari4.setPreferredSize(new java.awt.Dimension(300, 23));
        TCari4.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                TCari4ActionPerformed(evt);
            }
        });
        TCari4.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                TCari4KeyPressed(evt);
            }
        });
        internalFrame16.add(TCari4);
        TCari4.setBounds(85, 213, 220, 23);

        BtnCari5.setForeground(new java.awt.Color(0, 0, 0));
        BtnCari5.setIcon(new javax.swing.ImageIcon(getClass().getResource("/picture/accept.png"))); // NOI18N
        BtnCari5.setMnemonic('7');
        BtnCari5.setText("Tampilkan Data");
        BtnCari5.setToolTipText("Alt+7");
        BtnCari5.setName("BtnCari5"); // NOI18N
        BtnCari5.setPreferredSize(new java.awt.Dimension(150, 23));
        BtnCari5.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                BtnCari5ActionPerformed(evt);
            }
        });
        BtnCari5.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                BtnCari5KeyPressed(evt);
            }
        });
        internalFrame16.add(BtnCari5);
        BtnCari5.setBounds(310, 213, 130, 23);

        alasanAPS.setEditable(false);
        alasanAPS.setForeground(new java.awt.Color(0, 0, 0));
        alasanAPS.setName("alasanAPS"); // NOI18N
        alasanAPS.setPreferredSize(new java.awt.Dimension(300, 23));
        alasanAPS.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                alasanAPSKeyPressed(evt);
            }
        });
        internalFrame16.add(alasanAPS);
        alasanAPS.setBounds(85, 157, 350, 23);

        BtnBatal2.setForeground(new java.awt.Color(0, 0, 0));
        BtnBatal2.setIcon(new javax.swing.ImageIcon(getClass().getResource("/picture/Cancel-2-16x16.png"))); // NOI18N
        BtnBatal2.setMnemonic('B');
        BtnBatal2.setText("Baru");
        BtnBatal2.setToolTipText("Alt+B");
        BtnBatal2.setName("BtnBatal2"); // NOI18N
        BtnBatal2.setPreferredSize(new java.awt.Dimension(100, 30));
        BtnBatal2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                BtnBatal2ActionPerformed(evt);
            }
        });
        BtnBatal2.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                BtnBatal2KeyPressed(evt);
            }
        });
        internalFrame16.add(BtnBatal2);
        BtnBatal2.setBounds(10, 242, 80, 30);

        BtnEdit3.setForeground(new java.awt.Color(0, 0, 0));
        BtnEdit3.setIcon(new javax.swing.ImageIcon(getClass().getResource("/picture/inventaris.png"))); // NOI18N
        BtnEdit3.setMnemonic('G');
        BtnEdit3.setText("Ganti");
        BtnEdit3.setToolTipText("Alt+G");
        BtnEdit3.setName("BtnEdit3"); // NOI18N
        BtnEdit3.setPreferredSize(new java.awt.Dimension(100, 30));
        BtnEdit3.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                BtnEdit3ActionPerformed(evt);
            }
        });
        BtnEdit3.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                BtnEdit3KeyPressed(evt);
            }
        });
        internalFrame16.add(BtnEdit3);
        BtnEdit3.setBounds(190, 242, 80, 30);

        BtnPrint1.setForeground(new java.awt.Color(0, 0, 0));
        BtnPrint1.setIcon(new javax.swing.ImageIcon(getClass().getResource("/picture/b_print.png"))); // NOI18N
        BtnPrint1.setMnemonic('T');
        BtnPrint1.setText("Cetak");
        BtnPrint1.setToolTipText("Alt+T");
        BtnPrint1.setName("BtnPrint1"); // NOI18N
        BtnPrint1.setPreferredSize(new java.awt.Dimension(100, 30));
        BtnPrint1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                BtnPrint1ActionPerformed(evt);
            }
        });
        BtnPrint1.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                BtnPrint1KeyPressed(evt);
            }
        });
        internalFrame16.add(BtnPrint1);
        BtnPrint1.setBounds(275, 242, 80, 30);

        jLabel100.setForeground(new java.awt.Color(0, 0, 0));
        jLabel100.setText("Keterangan :");
        jLabel100.setName("jLabel100"); // NOI18N
        internalFrame16.add(jLabel100);
        jLabel100.setBounds(0, 185, 81, 23);

        ketAPS.setForeground(new java.awt.Color(0, 0, 0));
        ketAPS.setName("ketAPS"); // NOI18N
        ketAPS.setPreferredSize(new java.awt.Dimension(300, 23));
        ketAPS.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                ketAPSKeyPressed(evt);
            }
        });
        internalFrame16.add(ketAPS);
        ketAPS.setBounds(85, 185, 350, 23);

        WindowPulangAPS.getContentPane().add(internalFrame16, java.awt.BorderLayout.CENTER);

        WindowDiagnosaAwal.setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);
        WindowDiagnosaAwal.setName("WindowDiagnosaAwal"); // NOI18N
        WindowDiagnosaAwal.setUndecorated(true);
        WindowDiagnosaAwal.setResizable(false);

        internalFrame17.setBorder(javax.swing.BorderFactory.createTitledBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 153, 255), 3), "::[ Perbaikan Diagnosa Awal ]::", javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Tahoma", 1, 12))); // NOI18N
        internalFrame17.setName("internalFrame17"); // NOI18N
        internalFrame17.setWarnaBawah(new java.awt.Color(245, 250, 240));
        internalFrame17.setLayout(null);

        BtnCloseIn12.setForeground(new java.awt.Color(0, 0, 0));
        BtnCloseIn12.setIcon(new javax.swing.ImageIcon(getClass().getResource("/picture/cross.png"))); // NOI18N
        BtnCloseIn12.setMnemonic('U');
        BtnCloseIn12.setText("Tutup");
        BtnCloseIn12.setToolTipText("Alt+U");
        BtnCloseIn12.setName("BtnCloseIn12"); // NOI18N
        BtnCloseIn12.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                BtnCloseIn12ActionPerformed(evt);
            }
        });
        internalFrame17.add(BtnCloseIn12);
        BtnCloseIn12.setBounds(680, 30, 80, 30);

        BtnSimpan10.setForeground(new java.awt.Color(0, 0, 0));
        BtnSimpan10.setIcon(new javax.swing.ImageIcon(getClass().getResource("/picture/save-16x16.png"))); // NOI18N
        BtnSimpan10.setMnemonic('S');
        BtnSimpan10.setText("Simpan");
        BtnSimpan10.setToolTipText("Alt+S");
        BtnSimpan10.setName("BtnSimpan10"); // NOI18N
        BtnSimpan10.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                BtnSimpan10ActionPerformed(evt);
            }
        });
        internalFrame17.add(BtnSimpan10);
        BtnSimpan10.setBounds(575, 30, 90, 30);

        jLabel101.setForeground(new java.awt.Color(0, 0, 0));
        jLabel101.setText("Diagnsoa Awal :");
        jLabel101.setName("jLabel101"); // NOI18N
        internalFrame17.add(jLabel101);
        jLabel101.setBounds(0, 32, 90, 23);

        TDiagnosaAwal.setForeground(new java.awt.Color(0, 0, 0));
        TDiagnosaAwal.setHighlighter(null);
        TDiagnosaAwal.setName("TDiagnosaAwal"); // NOI18N
        TDiagnosaAwal.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyTyped(java.awt.event.KeyEvent evt) {
                TDiagnosaAwalKeyTyped(evt);
            }
            public void keyPressed(java.awt.event.KeyEvent evt) {
                TDiagnosaAwalKeyPressed(evt);
            }
        });
        internalFrame17.add(TDiagnosaAwal);
        TDiagnosaAwal.setBounds(95, 32, 470, 23);

        WindowDiagnosaAwal.getContentPane().add(internalFrame17, java.awt.BorderLayout.CENTER);

        WindowWaktuRegRalan.setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);
        WindowWaktuRegRalan.setName("WindowWaktuRegRalan"); // NOI18N
        WindowWaktuRegRalan.setUndecorated(true);
        WindowWaktuRegRalan.setResizable(false);

        internalFrame11.setBorder(javax.swing.BorderFactory.createTitledBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 153, 255), 3), "::[ Ganti Tgl. & Jam Registrasi Rawat Jalan/IGD ]::", javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Tahoma", 1, 12))); // NOI18N
        internalFrame11.setName("internalFrame11"); // NOI18N
        internalFrame11.setWarnaBawah(new java.awt.Color(245, 250, 240));
        internalFrame11.setLayout(null);

        BtnCloseIn5.setForeground(new java.awt.Color(0, 0, 0));
        BtnCloseIn5.setIcon(new javax.swing.ImageIcon(getClass().getResource("/picture/cross.png"))); // NOI18N
        BtnCloseIn5.setMnemonic('U');
        BtnCloseIn5.setText("Tutup");
        BtnCloseIn5.setToolTipText("Alt+U");
        BtnCloseIn5.setName("BtnCloseIn5"); // NOI18N
        BtnCloseIn5.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                BtnCloseIn5ActionPerformed(evt);
            }
        });
        BtnCloseIn5.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                BtnCloseIn5KeyPressed(evt);
            }
        });
        internalFrame11.add(BtnCloseIn5);
        BtnCloseIn5.setBounds(250, 120, 90, 30);

        jLabel51.setForeground(new java.awt.Color(0, 0, 0));
        jLabel51.setText("Tgl. Registrasi :");
        jLabel51.setName("jLabel51"); // NOI18N
        internalFrame11.add(jLabel51);
        jLabel51.setBounds(0, 25, 130, 23);

        TglRegRalan.setEditable(false);
        TglRegRalan.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "25-12-2021" }));
        TglRegRalan.setDisplayFormat("dd-MM-yyyy");
        TglRegRalan.setName("TglRegRalan"); // NOI18N
        TglRegRalan.setOpaque(false);
        TglRegRalan.addItemListener(new java.awt.event.ItemListener() {
            public void itemStateChanged(java.awt.event.ItemEvent evt) {
                TglRegRalanItemStateChanged(evt);
            }
        });
        TglRegRalan.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                TglRegRalanKeyPressed(evt);
            }
        });
        internalFrame11.add(TglRegRalan);
        TglRegRalan.setBounds(135, 25, 100, 23);

        BtnGantiTgl.setForeground(new java.awt.Color(0, 0, 0));
        BtnGantiTgl.setIcon(new javax.swing.ImageIcon(getClass().getResource("/picture/inventaris.png"))); // NOI18N
        BtnGantiTgl.setMnemonic('G');
        BtnGantiTgl.setText("Ganti");
        BtnGantiTgl.setToolTipText("Alt+G");
        BtnGantiTgl.setName("BtnGantiTgl"); // NOI18N
        BtnGantiTgl.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                BtnGantiTglActionPerformed(evt);
            }
        });
        BtnGantiTgl.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                BtnGantiTglKeyPressed(evt);
            }
        });
        internalFrame11.add(BtnGantiTgl);
        BtnGantiTgl.setBounds(140, 120, 100, 30);

        jLabel111.setForeground(new java.awt.Color(0, 0, 0));
        jLabel111.setText("Jam Reg. Manual :");
        jLabel111.setName("jLabel111"); // NOI18N
        internalFrame11.add(jLabel111);
        jLabel111.setBounds(0, 55, 130, 23);

        cmbJamReg.setForeground(new java.awt.Color(0, 0, 0));
        cmbJamReg.setText("Tgl. Masuk & Jam Masuk Rawat Inap");
        cmbJamReg.setName("cmbJamReg"); // NOI18N
        cmbJamReg.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                cmbJamRegActionPerformed(evt);
            }
        });
        internalFrame11.add(cmbJamReg);
        cmbJamReg.setBounds(135, 85, 210, 23);

        jLabel115.setForeground(new java.awt.Color(0, 0, 0));
        jLabel115.setText("Disamakan dg. : ");
        jLabel115.setName("jLabel115"); // NOI18N
        internalFrame11.add(jLabel115);
        jLabel115.setBounds(0, 85, 130, 23);

        Jreg.setForeground(new java.awt.Color(0, 0, 0));
        Jreg.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "00", "01", "02", "03", "04", "05", "06", "07", "08", "09", "10", "11", "12", "13", "14", "15", "16", "17", "18", "19", "20", "21", "22", "23" }));
        Jreg.setName("Jreg"); // NOI18N
        Jreg.addItemListener(new java.awt.event.ItemListener() {
            public void itemStateChanged(java.awt.event.ItemEvent evt) {
                JregCmbTahunItemStateChanged(evt);
            }
        });
        Jreg.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                JregKeyPressed(evt);
            }
        });
        internalFrame11.add(Jreg);
        Jreg.setBounds(135, 55, 48, 23);

        Mreg.setForeground(new java.awt.Color(0, 0, 0));
        Mreg.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "00", "01", "02", "03", "04", "05", "06", "07", "08", "09", "10", "11", "12", "13", "14", "15", "16", "17", "18", "19", "20", "21", "22", "23", "24", "25", "26", "27", "28", "29", "30", "31", "32", "33", "34", "35", "36", "37", "38", "39", "40", "41", "42", "43", "44", "45", "46", "47", "48", "49", "50", "51", "52", "53", "54", "55", "56", "57", "58", "59" }));
        Mreg.setName("Mreg"); // NOI18N
        Mreg.addItemListener(new java.awt.event.ItemListener() {
            public void itemStateChanged(java.awt.event.ItemEvent evt) {
                MregCmbTahunItemStateChanged(evt);
            }
        });
        Mreg.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                MregKeyPressed(evt);
            }
        });
        internalFrame11.add(Mreg);
        Mreg.setBounds(186, 55, 48, 23);

        Dreg.setForeground(new java.awt.Color(0, 0, 0));
        Dreg.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "00", "01", "02", "03", "04", "05", "06", "07", "08", "09", "10", "11", "12", "13", "14", "15", "16", "17", "18", "19", "20", "21", "22", "23", "24", "25", "26", "27", "28", "29", "30", "31", "32", "33", "34", "35", "36", "37", "38", "39", "40", "41", "42", "43", "44", "45", "46", "47", "48", "49", "50", "51", "52", "53", "54", "55", "56", "57", "58", "59" }));
        Dreg.setName("Dreg"); // NOI18N
        Dreg.addItemListener(new java.awt.event.ItemListener() {
            public void itemStateChanged(java.awt.event.ItemEvent evt) {
                DregCmbTahunItemStateChanged(evt);
            }
        });
        Dreg.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                DregKeyPressed(evt);
            }
        });
        internalFrame11.add(Dreg);
        Dreg.setBounds(238, 55, 48, 23);

        WindowWaktuRegRalan.getContentPane().add(internalFrame11, java.awt.BorderLayout.CENTER);

        WindowPindahkanTransaksi.setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);
        WindowPindahkanTransaksi.setName("WindowPindahkanTransaksi"); // NOI18N
        WindowPindahkanTransaksi.setUndecorated(true);
        WindowPindahkanTransaksi.setResizable(false);

        internalFrame19.setBorder(javax.swing.BorderFactory.createTitledBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 153, 255), 3), "::[ Memindahkan Data Transaksi ]::", javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Tahoma", 1, 12))); // NOI18N
        internalFrame19.setName("internalFrame19"); // NOI18N
        internalFrame19.setWarnaBawah(new java.awt.Color(245, 250, 240));
        internalFrame19.setLayout(null);

        BtnCloseIn14.setForeground(new java.awt.Color(0, 0, 0));
        BtnCloseIn14.setIcon(new javax.swing.ImageIcon(getClass().getResource("/picture/cross.png"))); // NOI18N
        BtnCloseIn14.setMnemonic('U');
        BtnCloseIn14.setText("Tutup");
        BtnCloseIn14.setToolTipText("Alt+U");
        BtnCloseIn14.setName("BtnCloseIn14"); // NOI18N
        BtnCloseIn14.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                BtnCloseIn14ActionPerformed(evt);
            }
        });
        BtnCloseIn14.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                BtnCloseIn14KeyPressed(evt);
            }
        });
        internalFrame19.add(BtnCloseIn14);
        BtnCloseIn14.setBounds(460, 360, 80, 30);

        jLabel63.setForeground(new java.awt.Color(0, 0, 0));
        jLabel63.setText("No. Rawat Terpilih : ");
        jLabel63.setName("jLabel63"); // NOI18N
        internalFrame19.add(jLabel63);
        jLabel63.setBounds(5, 20, 115, 23);

        BtnProsesTran.setForeground(new java.awt.Color(0, 0, 0));
        BtnProsesTran.setIcon(new javax.swing.ImageIcon(getClass().getResource("/picture/42a.png"))); // NOI18N
        BtnProsesTran.setMnemonic('D');
        BtnProsesTran.setText("Diproses");
        BtnProsesTran.setToolTipText("Alt+D");
        BtnProsesTran.setName("BtnProsesTran"); // NOI18N
        BtnProsesTran.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                BtnProsesTranActionPerformed(evt);
            }
        });
        internalFrame19.add(BtnProsesTran);
        BtnProsesTran.setBounds(355, 360, 100, 30);

        jLabel64.setForeground(new java.awt.Color(0, 0, 0));
        jLabel64.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel64.setText("yyyy-mm-dd");
        jLabel64.setFont(new java.awt.Font("Tahoma", 2, 11)); // NOI18N
        jLabel64.setName("jLabel64"); // NOI18N
        internalFrame19.add(jLabel64);
        jLabel64.setBounds(123, 121, 80, 23);

        jLabel106.setForeground(new java.awt.Color(0, 0, 0));
        jLabel106.setText("No. Rawat Tujuan : ");
        jLabel106.setName("jLabel106"); // NOI18N
        internalFrame19.add(jLabel106);
        jLabel106.setBounds(5, 47, 115, 23);

        TNoRwTerpilih.setEditable(false);
        TNoRwTerpilih.setForeground(new java.awt.Color(0, 0, 0));
        TNoRwTerpilih.setName("TNoRwTerpilih"); // NOI18N
        TNoRwTerpilih.setPreferredSize(new java.awt.Dimension(135, 23));
        internalFrame19.add(TNoRwTerpilih);
        TNoRwTerpilih.setBounds(123, 20, 125, 23);

        TNoRwTujuan.setForeground(new java.awt.Color(0, 0, 0));
        TNoRwTujuan.setFont(new java.awt.Font("Tahoma", 1, 11)); // NOI18N
        TNoRwTujuan.setName("TNoRwTujuan"); // NOI18N
        TNoRwTujuan.setPreferredSize(new java.awt.Dimension(135, 23));
        internalFrame19.add(TNoRwTujuan);
        TNoRwTujuan.setBounds(123, 47, 143, 23);

        ChkTglTran.setBackground(new java.awt.Color(255, 255, 250));
        ChkTglTran.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(255, 255, 250)));
        ChkTglTran.setForeground(new java.awt.Color(0, 0, 0));
        ChkTglTran.setText("Dipilih");
        ChkTglTran.setBorderPainted(true);
        ChkTglTran.setBorderPaintedFlat(true);
        ChkTglTran.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        ChkTglTran.setHorizontalTextPosition(javax.swing.SwingConstants.RIGHT);
        ChkTglTran.setName("ChkTglTran"); // NOI18N
        ChkTglTran.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                ChkTglTranActionPerformed(evt);
            }
        });
        internalFrame19.add(ChkTglTran);
        ChkTglTran.setBounds(330, 102, 200, 23);

        jLabel108.setForeground(new java.awt.Color(0, 0, 0));
        jLabel108.setText("Jenis Transaksi : ");
        jLabel108.setName("jLabel108"); // NOI18N
        internalFrame19.add(jLabel108);
        jLabel108.setBounds(5, 75, 115, 23);

        cmbJnsTran.setForeground(new java.awt.Color(0, 0, 0));
        cmbJnsTran.setModel(new javax.swing.DefaultComboBoxModel(new String[] { " ", "FARMASI", "RADIOLOGI", "LABORATORIUM", "PENANGANAN DOKTER", "PENANGANAN PETUGAS", "PENANGANAN DOKTER & PETUGAS" }));
        cmbJnsTran.setName("cmbJnsTran"); // NOI18N
        cmbJnsTran.setOpaque(false);
        cmbJnsTran.setPreferredSize(new java.awt.Dimension(115, 23));
        cmbJnsTran.addItemListener(new java.awt.event.ItemListener() {
            public void itemStateChanged(java.awt.event.ItemEvent evt) {
                cmbJnsTranItemStateChanged(evt);
            }
        });
        internalFrame19.add(cmbJnsTran);
        cmbJnsTran.setBounds(123, 75, 207, 23);

        pasienTerpilih.setEditable(false);
        pasienTerpilih.setForeground(new java.awt.Color(0, 0, 0));
        pasienTerpilih.setHighlighter(null);
        pasienTerpilih.setName("pasienTerpilih"); // NOI18N
        pasienTerpilih.setPreferredSize(new java.awt.Dimension(135, 23));
        internalFrame19.add(pasienTerpilih);
        pasienTerpilih.setBounds(250, 20, 295, 23);

        tglDari.setForeground(new java.awt.Color(0, 0, 0));
        tglDari.setName("tglDari"); // NOI18N
        tglDari.setPreferredSize(new java.awt.Dimension(135, 23));
        internalFrame19.add(tglDari);
        tglDari.setBounds(123, 102, 80, 23);

        jLabel110.setForeground(new java.awt.Color(0, 0, 0));
        jLabel110.setText("Pukul : ");
        jLabel110.setName("jLabel110"); // NOI18N
        internalFrame19.add(jLabel110);
        jLabel110.setBounds(205, 102, 40, 23);

        pukulDari.setForeground(new java.awt.Color(0, 0, 0));
        pukulDari.setName("pukulDari"); // NOI18N
        pukulDari.setPreferredSize(new java.awt.Dimension(135, 23));
        internalFrame19.add(pukulDari);
        pukulDari.setBounds(245, 102, 80, 23);

        Scroll6.setBorder(javax.swing.BorderFactory.createTitledBorder(null, "[ Data Waktu Transaksi ]", javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Tahoma", 0, 13))); // NOI18N
        Scroll6.setName("Scroll6"); // NOI18N
        Scroll6.setOpaque(true);

        tbDataTran.setToolTipText("");
        tbDataTran.setName("tbDataTran"); // NOI18N
        tbDataTran.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                tbDataTranMouseClicked(evt);
            }
        });
        tbDataTran.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                tbDataTranKeyPressed(evt);
            }
        });
        Scroll6.setViewportView(tbDataTran);

        internalFrame19.add(Scroll6);
        Scroll6.setBounds(15, 142, 530, 210);

        BtnCekTran.setForeground(new java.awt.Color(0, 0, 0));
        BtnCekTran.setIcon(new javax.swing.ImageIcon(getClass().getResource("/picture/Search.png"))); // NOI18N
        BtnCekTran.setMnemonic('T');
        BtnCekTran.setText("Cek Transaksi");
        BtnCekTran.setToolTipText("Alt+T");
        BtnCekTran.setName("BtnCekTran"); // NOI18N
        BtnCekTran.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                BtnCekTranActionPerformed(evt);
            }
        });
        internalFrame19.add(BtnCekTran);
        BtnCekTran.setBounds(220, 360, 130, 30);

        jLabel107.setForeground(new java.awt.Color(0, 0, 0));
        jLabel107.setText("Dari Tgl. Trans. : ");
        jLabel107.setName("jLabel107"); // NOI18N
        internalFrame19.add(jLabel107);
        jLabel107.setBounds(5, 103, 115, 23);

        jLabel109.setForeground(new java.awt.Color(0, 0, 0));
        jLabel109.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel109.setText("hh:mm:ss");
        jLabel109.setFont(new java.awt.Font("Tahoma", 2, 11)); // NOI18N
        jLabel109.setName("jLabel109"); // NOI18N
        internalFrame19.add(jLabel109);
        jLabel109.setBounds(245, 121, 80, 23);

        jLabel112.setForeground(new java.awt.Color(0, 0, 0));
        jLabel112.setText("Record : ");
        jLabel112.setName("jLabel112"); // NOI18N
        jLabel112.setPreferredSize(new java.awt.Dimension(70, 30));
        internalFrame19.add(jLabel112);
        jLabel112.setBounds(20, 360, 60, 30);

        LCount1.setForeground(new java.awt.Color(0, 0, 0));
        LCount1.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        LCount1.setText("0");
        LCount1.setName("LCount1"); // NOI18N
        LCount1.setPreferredSize(new java.awt.Dimension(72, 30));
        internalFrame19.add(LCount1);
        LCount1.setBounds(80, 360, 100, 30);

        WindowPindahkanTransaksi.getContentPane().add(internalFrame19, java.awt.BorderLayout.CENTER);

        sepJkd.setName("sepJkd"); // NOI18N
        sepJkd.setPreferredSize(new java.awt.Dimension(207, 23));

        sepJmp.setName("sepJmp"); // NOI18N
        sepJmp.setPreferredSize(new java.awt.Dimension(207, 23));

        tglInap.setName("tglInap"); // NOI18N
        tglInap.setPreferredSize(new java.awt.Dimension(207, 23));

        catatanIGD.setName("catatanIGD"); // NOI18N
        catatanIGD.setPreferredSize(new java.awt.Dimension(207, 23));

        NoRMmati.setName("NoRMmati"); // NOI18N
        NoRMmati.setPreferredSize(new java.awt.Dimension(207, 23));

        StatusDaftar.setName("StatusDaftar"); // NOI18N
        StatusDaftar.setPreferredSize(new java.awt.Dimension(207, 23));

        JamPulang.setName("JamPulang"); // NOI18N
        JamPulang.setPreferredSize(new java.awt.Dimension(207, 23));

        JenisMati.setName("JenisMati"); // NOI18N
        JenisMati.setPreferredSize(new java.awt.Dimension(207, 23));

        ruangrawat.setName("ruangrawat"); // NOI18N
        ruangrawat.setPreferredSize(new java.awt.Dimension(207, 23));

        userBerizin.setName("userBerizin"); // NOI18N
        userBerizin.setPreferredSize(new java.awt.Dimension(207, 23));

        ruangDicetak.setName("ruangDicetak"); // NOI18N
        ruangDicetak.setPreferredSize(new java.awt.Dimension(207, 23));

        kdAkses.setName("kdAkses"); // NOI18N
        kdAkses.setPreferredSize(new java.awt.Dimension(207, 23));

        statusSEP.setName("statusSEP"); // NOI18N
        statusSEP.setPreferredSize(new java.awt.Dimension(207, 23));

        tglMasukInap.setEditable(false);
        tglMasukInap.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "25-12-2021" }));
        tglMasukInap.setDisplayFormat("dd-MM-yyyy");
        tglMasukInap.setName("tglMasukInap"); // NOI18N
        tglMasukInap.setOpaque(false);
        tglMasukInap.setPreferredSize(new java.awt.Dimension(100, 23));
        tglMasukInap.addItemListener(new java.awt.event.ItemListener() {
            public void itemStateChanged(java.awt.event.ItemEvent evt) {
                tglMasukInapItemStateChanged(evt);
            }
        });
        tglMasukInap.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                tglMasukInapKeyPressed(evt);
            }
        });

        nominal1.setForeground(new java.awt.Color(0, 0, 0));
        nominal1.setHighlighter(null);
        nominal1.setName("nominal1"); // NOI18N
        nominal1.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyTyped(java.awt.event.KeyEvent evt) {
                nominal1KeyTyped(evt);
            }
            public void keyPressed(java.awt.event.KeyEvent evt) {
                nominal1KeyPressed(evt);
            }
        });

        hasilLM.setForeground(new java.awt.Color(0, 0, 0));
        hasilLM.setHighlighter(null);
        hasilLM.setName("hasilLM"); // NOI18N
        hasilLM.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyTyped(java.awt.event.KeyEvent evt) {
                hasilLMKeyTyped(evt);
            }
            public void keyPressed(java.awt.event.KeyEvent evt) {
                hasilLMKeyPressed(evt);
            }
        });

        nominal2.setForeground(new java.awt.Color(0, 0, 0));
        nominal2.setHighlighter(null);
        nominal2.setName("nominal2"); // NOI18N
        nominal2.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyTyped(java.awt.event.KeyEvent evt) {
                nominal2KeyTyped(evt);
            }
            public void keyPressed(java.awt.event.KeyEvent evt) {
                nominal2KeyPressed(evt);
            }
        });

        statusSELISIH.setForeground(new java.awt.Color(0, 0, 0));
        statusSELISIH.setHighlighter(null);
        statusSELISIH.setName("statusSELISIH"); // NOI18N
        statusSELISIH.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyTyped(java.awt.event.KeyEvent evt) {
                statusSELISIHKeyTyped(evt);
            }
            public void keyPressed(java.awt.event.KeyEvent evt) {
                statusSELISIHKeyPressed(evt);
            }
        });

        dataGZ.setName("dataGZ"); // NOI18N
        dataGZ.setPreferredSize(new java.awt.Dimension(207, 23));

        dataGB.setName("dataGB"); // NOI18N
        dataGB.setPreferredSize(new java.awt.Dimension(207, 23));

        cekDataAnak.setName("cekDataAnak"); // NOI18N
        cekDataAnak.setPreferredSize(new java.awt.Dimension(207, 23));

        status_pulang.setName("status_pulang"); // NOI18N
        status_pulang.setPreferredSize(new java.awt.Dimension(207, 23));

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);
        setCursor(new java.awt.Cursor(java.awt.Cursor.DEFAULT_CURSOR));
        setUndecorated(true);
        setResizable(false);
        addWindowListener(new java.awt.event.WindowAdapter() {
            public void windowOpened(java.awt.event.WindowEvent evt) {
                formWindowOpened(evt);
            }
            public void windowActivated(java.awt.event.WindowEvent evt) {
                formWindowActivated(evt);
            }
        });

        internalFrame1.setBorder(javax.swing.BorderFactory.createTitledBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(240, 245, 235)), "::[ Kamar Inap Pasien ]::", javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Tahoma", 1, 12))); // NOI18N
        internalFrame1.setName("internalFrame1"); // NOI18N
        internalFrame1.setLayout(new java.awt.BorderLayout(1, 1));

        PanelCariUtama.setName("PanelCariUtama"); // NOI18N
        PanelCariUtama.setOpaque(false);
        PanelCariUtama.setPreferredSize(new java.awt.Dimension(100, 143));
        PanelCariUtama.setLayout(new java.awt.BorderLayout(1, 1));

        panelGlass10.setName("panelGlass10"); // NOI18N
        panelGlass10.setPreferredSize(new java.awt.Dimension(55, 55));
        panelGlass10.setLayout(new java.awt.FlowLayout(java.awt.FlowLayout.LEFT, 5, 9));

        BtnIn.setForeground(new java.awt.Color(0, 0, 0));
        BtnIn.setIcon(new javax.swing.ImageIcon(getClass().getResource("/picture/Agenda-1-16x16.png"))); // NOI18N
        BtnIn.setMnemonic('M');
        BtnIn.setText("Masuk");
        BtnIn.setToolTipText("Alt+M");
        BtnIn.setName("BtnIn"); // NOI18N
        BtnIn.setPreferredSize(new java.awt.Dimension(100, 30));
        BtnIn.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                BtnInActionPerformed(evt);
            }
        });
        BtnIn.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                BtnInKeyPressed(evt);
            }
        });
        panelGlass10.add(BtnIn);

        BtnOut.setForeground(new java.awt.Color(0, 0, 0));
        BtnOut.setIcon(new javax.swing.ImageIcon(getClass().getResource("/picture/add-file-16x16.png"))); // NOI18N
        BtnOut.setMnemonic('U');
        BtnOut.setText("Pulang");
        BtnOut.setToolTipText("Alt+U");
        BtnOut.setName("BtnOut"); // NOI18N
        BtnOut.setPreferredSize(new java.awt.Dimension(100, 30));
        BtnOut.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                BtnOutActionPerformed(evt);
            }
        });
        BtnOut.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                BtnOutKeyPressed(evt);
            }
        });
        panelGlass10.add(BtnOut);

        btnPindah.setForeground(new java.awt.Color(0, 0, 0));
        btnPindah.setIcon(new javax.swing.ImageIcon(getClass().getResource("/picture/inventaris.png"))); // NOI18N
        btnPindah.setMnemonic('P');
        btnPindah.setText("Pindah");
        btnPindah.setToolTipText("Alt+P");
        btnPindah.setName("btnPindah"); // NOI18N
        btnPindah.setPreferredSize(new java.awt.Dimension(100, 30));
        btnPindah.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnPindahActionPerformed(evt);
            }
        });
        btnPindah.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                btnPindahKeyPressed(evt);
            }
        });
        panelGlass10.add(btnPindah);

        BtnAll.setForeground(new java.awt.Color(0, 0, 0));
        BtnAll.setIcon(new javax.swing.ImageIcon(getClass().getResource("/picture/Search-16x16.png"))); // NOI18N
        BtnAll.setMnemonic('M');
        BtnAll.setText("Semua Data");
        BtnAll.setToolTipText("Alt+M");
        BtnAll.setName("BtnAll"); // NOI18N
        BtnAll.setPreferredSize(new java.awt.Dimension(120, 30));
        BtnAll.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                BtnAllActionPerformed(evt);
            }
        });
        BtnAll.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                BtnAllKeyPressed(evt);
            }
        });
        panelGlass10.add(BtnAll);

        BtnKeluar.setForeground(new java.awt.Color(0, 0, 0));
        BtnKeluar.setIcon(new javax.swing.ImageIcon(getClass().getResource("/picture/exit.png"))); // NOI18N
        BtnKeluar.setMnemonic('K');
        BtnKeluar.setText("Keluar");
        BtnKeluar.setToolTipText("Alt+K");
        BtnKeluar.setName("BtnKeluar"); // NOI18N
        BtnKeluar.setPreferredSize(new java.awt.Dimension(100, 30));
        BtnKeluar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                BtnKeluarActionPerformed(evt);
            }
        });
        BtnKeluar.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                BtnKeluarKeyPressed(evt);
            }
        });
        panelGlass10.add(BtnKeluar);

        jLabel94.setForeground(new java.awt.Color(0, 0, 0));
        jLabel94.setText("No. Rawat : ");
        jLabel94.setName("jLabel94"); // NOI18N
        jLabel94.setPreferredSize(new java.awt.Dimension(70, 23));
        panelGlass10.add(jLabel94);

        norawatCopy.setEditable(false);
        norawatCopy.setForeground(new java.awt.Color(0, 0, 0));
        norawatCopy.setName("norawatCopy"); // NOI18N
        norawatCopy.setPreferredSize(new java.awt.Dimension(135, 23));
        norawatCopy.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyTyped(java.awt.event.KeyEvent evt) {
                norawatCopyKeyTyped(evt);
            }
            public void keyPressed(java.awt.event.KeyEvent evt) {
                norawatCopyKeyPressed(evt);
            }
        });
        panelGlass10.add(norawatCopy);

        jLabel95.setForeground(new java.awt.Color(0, 0, 0));
        jLabel95.setText("No. RM : ");
        jLabel95.setName("jLabel95"); // NOI18N
        jLabel95.setPreferredSize(new java.awt.Dimension(45, 23));
        panelGlass10.add(jLabel95);

        noRMCopy.setEditable(false);
        noRMCopy.setForeground(new java.awt.Color(0, 0, 0));
        noRMCopy.setName("noRMCopy"); // NOI18N
        noRMCopy.setPreferredSize(new java.awt.Dimension(65, 23));
        noRMCopy.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyTyped(java.awt.event.KeyEvent evt) {
                noRMCopyKeyTyped(evt);
            }
            public void keyPressed(java.awt.event.KeyEvent evt) {
                noRMCopyKeyPressed(evt);
            }
        });
        panelGlass10.add(noRMCopy);

        PanelCariUtama.add(panelGlass10, java.awt.BorderLayout.PAGE_END);

        panelGlass11.setName("panelGlass11"); // NOI18N
        panelGlass11.setPreferredSize(new java.awt.Dimension(44, 44));
        panelGlass11.setLayout(new java.awt.FlowLayout(java.awt.FlowLayout.LEFT, 2, 9));

        jLabel21.setForeground(new java.awt.Color(0, 0, 0));
        jLabel21.setText("Kamar :");
        jLabel21.setName("jLabel21"); // NOI18N
        jLabel21.setPreferredSize(new java.awt.Dimension(50, 23));
        panelGlass11.add(jLabel21);

        BangsalCari.setForeground(new java.awt.Color(0, 0, 0));
        BangsalCari.setName("BangsalCari"); // NOI18N
        BangsalCari.setPreferredSize(new java.awt.Dimension(200, 23));
        BangsalCari.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                BangsalCariKeyPressed(evt);
            }
        });
        panelGlass11.add(BangsalCari);

        btnBangsalCari.setForeground(new java.awt.Color(0, 0, 0));
        btnBangsalCari.setIcon(new javax.swing.ImageIcon(getClass().getResource("/picture/190.png"))); // NOI18N
        btnBangsalCari.setMnemonic('3');
        btnBangsalCari.setToolTipText("Alt+3");
        btnBangsalCari.setName("btnBangsalCari"); // NOI18N
        btnBangsalCari.setPreferredSize(new java.awt.Dimension(28, 23));
        btnBangsalCari.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnBangsalCariActionPerformed(evt);
            }
        });
        btnBangsalCari.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                btnBangsalCariKeyPressed(evt);
            }
        });
        panelGlass11.add(btnBangsalCari);

        jLabel6.setForeground(new java.awt.Color(0, 0, 0));
        jLabel6.setText("Key Word :");
        jLabel6.setName("jLabel6"); // NOI18N
        jLabel6.setPreferredSize(new java.awt.Dimension(70, 23));
        panelGlass11.add(jLabel6);

        TCari.setForeground(new java.awt.Color(0, 0, 0));
        TCari.setName("TCari"); // NOI18N
        TCari.setPreferredSize(new java.awt.Dimension(230, 23));
        TCari.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyTyped(java.awt.event.KeyEvent evt) {
                TCariKeyTyped(evt);
            }
            public void keyPressed(java.awt.event.KeyEvent evt) {
                TCariKeyPressed(evt);
            }
        });
        panelGlass11.add(TCari);

        BtnCari.setForeground(new java.awt.Color(0, 0, 0));
        BtnCari.setIcon(new javax.swing.ImageIcon(getClass().getResource("/picture/accept.png"))); // NOI18N
        BtnCari.setMnemonic('4');
        BtnCari.setText("Tampilkan Data");
        BtnCari.setToolTipText("Alt+4");
        BtnCari.setName("BtnCari"); // NOI18N
        BtnCari.setPreferredSize(new java.awt.Dimension(130, 23));
        BtnCari.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                BtnCariActionPerformed(evt);
            }
        });
        BtnCari.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                BtnCariKeyPressed(evt);
            }
        });
        panelGlass11.add(BtnCari);

        jLabel7.setForeground(new java.awt.Color(0, 0, 0));
        jLabel7.setText("Record :");
        jLabel7.setName("jLabel7"); // NOI18N
        jLabel7.setPreferredSize(new java.awt.Dimension(55, 30));
        panelGlass11.add(jLabel7);

        LCount.setForeground(new java.awt.Color(0, 0, 0));
        LCount.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        LCount.setText("0");
        LCount.setName("LCount"); // NOI18N
        LCount.setPreferredSize(new java.awt.Dimension(75, 30));
        panelGlass11.add(LCount);

        PanelCariUtama.add(panelGlass11, java.awt.BorderLayout.CENTER);

        panelCari.setName("panelCari"); // NOI18N
        panelCari.setPreferredSize(new java.awt.Dimension(44, 43));
        panelCari.setLayout(new java.awt.FlowLayout(java.awt.FlowLayout.LEFT, 2, 9));

        R1.setBackground(new java.awt.Color(240, 250, 230));
        R1.setBorder(javax.swing.BorderFactory.createLineBorder(java.awt.Color.pink));
        buttonGroup1.add(R1);
        R1.setSelected(true);
        R1.setText("Belum Pulang");
        R1.setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);
        R1.setHorizontalTextPosition(javax.swing.SwingConstants.RIGHT);
        R1.setName("R1"); // NOI18N
        R1.setPreferredSize(new java.awt.Dimension(95, 23));
        R1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                R1ActionPerformed(evt);
            }
        });
        panelCari.add(R1);

        R2.setBackground(new java.awt.Color(240, 250, 230));
        R2.setBorder(javax.swing.BorderFactory.createLineBorder(java.awt.Color.pink));
        buttonGroup1.add(R2);
        R2.setText("Tgl.Masuk :");
        R2.setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);
        R2.setHorizontalTextPosition(javax.swing.SwingConstants.RIGHT);
        R2.setName("R2"); // NOI18N
        R2.setPreferredSize(new java.awt.Dimension(90, 23));
        panelCari.add(R2);

        DTPCari1.setEditable(false);
        DTPCari1.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "25-12-2021" }));
        DTPCari1.setDisplayFormat("dd-MM-yyyy");
        DTPCari1.setName("DTPCari1"); // NOI18N
        DTPCari1.setOpaque(false);
        DTPCari1.setPreferredSize(new java.awt.Dimension(90, 23));
        DTPCari1.addItemListener(new java.awt.event.ItemListener() {
            public void itemStateChanged(java.awt.event.ItemEvent evt) {
                DTPCari1ItemStateChanged(evt);
            }
        });
        DTPCari1.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                DTPCari1KeyPressed(evt);
            }
        });
        panelCari.add(DTPCari1);

        jLabel22.setForeground(new java.awt.Color(0, 0, 0));
        jLabel22.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel22.setText("s.d");
        jLabel22.setName("jLabel22"); // NOI18N
        jLabel22.setPreferredSize(new java.awt.Dimension(25, 23));
        panelCari.add(jLabel22);

        DTPCari2.setEditable(false);
        DTPCari2.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "25-12-2021" }));
        DTPCari2.setDisplayFormat("dd-MM-yyyy");
        DTPCari2.setName("DTPCari2"); // NOI18N
        DTPCari2.setOpaque(false);
        DTPCari2.setPreferredSize(new java.awt.Dimension(90, 23));
        DTPCari2.addItemListener(new java.awt.event.ItemListener() {
            public void itemStateChanged(java.awt.event.ItemEvent evt) {
                DTPCari1ItemStateChanged(evt);
            }
        });
        DTPCari2.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                DTPCari2KeyPressed(evt);
            }
        });
        panelCari.add(DTPCari2);

        R3.setBackground(new java.awt.Color(240, 250, 230));
        R3.setBorder(javax.swing.BorderFactory.createLineBorder(java.awt.Color.pink));
        buttonGroup1.add(R3);
        R3.setText("Pulang :");
        R3.setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);
        R3.setHorizontalTextPosition(javax.swing.SwingConstants.RIGHT);
        R3.setName("R3"); // NOI18N
        R3.setPreferredSize(new java.awt.Dimension(75, 23));
        panelCari.add(R3);

        DTPCari3.setEditable(false);
        DTPCari3.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "25-12-2021" }));
        DTPCari3.setDisplayFormat("dd-MM-yyyy");
        DTPCari3.setName("DTPCari3"); // NOI18N
        DTPCari3.setOpaque(false);
        DTPCari3.setPreferredSize(new java.awt.Dimension(90, 23));
        DTPCari3.addItemListener(new java.awt.event.ItemListener() {
            public void itemStateChanged(java.awt.event.ItemEvent evt) {
                DTPCari3ItemStateChanged(evt);
            }
        });
        DTPCari3.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                DTPCari3KeyPressed(evt);
            }
        });
        panelCari.add(DTPCari3);

        jLabel25.setForeground(new java.awt.Color(0, 0, 0));
        jLabel25.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel25.setText("s.d");
        jLabel25.setName("jLabel25"); // NOI18N
        jLabel25.setPreferredSize(new java.awt.Dimension(25, 23));
        panelCari.add(jLabel25);

        DTPCari4.setEditable(false);
        DTPCari4.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "25-12-2021" }));
        DTPCari4.setDisplayFormat("dd-MM-yyyy");
        DTPCari4.setName("DTPCari4"); // NOI18N
        DTPCari4.setOpaque(false);
        DTPCari4.setPreferredSize(new java.awt.Dimension(90, 23));
        DTPCari4.addItemListener(new java.awt.event.ItemListener() {
            public void itemStateChanged(java.awt.event.ItemEvent evt) {
                DTPCari4ItemStateChanged(evt);
            }
        });
        DTPCari4.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                DTPCari4KeyPressed(evt);
            }
        });
        panelCari.add(DTPCari4);

        jLabel8.setForeground(new java.awt.Color(0, 0, 0));
        jLabel8.setText("Ruangan Inap :");
        jLabel8.setName("jLabel8"); // NOI18N
        jLabel8.setPreferredSize(new java.awt.Dimension(90, 30));
        panelCari.add(jLabel8);

        cmbRuangan.setForeground(new java.awt.Color(0, 0, 0));
        cmbRuangan.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "- pilih salah satu -", "ZAAL", "RKPD", "PARU", "JANTUNG", "AS-SAMI", "AS-SAMI/UMUM", "ANAK", "BEDAH", "INTERNIST", "ICU/ICCU", "NICU", "OBGYN", "VIP", "SVIP", "BAYI-SEHAT", "AR-RAUDAH", "SYARAF", "MATA-THT-KK", "ISOLASI COVID19", "ISOLASI BAYI COVID19", "COVID19", "SEMUA RUANG" }));
        cmbRuangan.setMinimumSize(new java.awt.Dimension(150, 23));
        cmbRuangan.setName("cmbRuangan"); // NOI18N
        cmbRuangan.setPreferredSize(new java.awt.Dimension(150, 23));
        cmbRuangan.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                cmbRuanganMouseClicked(evt);
            }
        });
        cmbRuangan.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                cmbRuanganActionPerformed(evt);
            }
        });
        cmbRuangan.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                cmbRuanganKeyPressed(evt);
            }
        });
        panelCari.add(cmbRuangan);

        cmbRuangKhusus1.setForeground(new java.awt.Color(0, 0, 0));
        cmbRuangKhusus1.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "- pilih salah satu -", "JANTUNG", "AS-SAMI", "AS-SAMI/UMUM" }));
        cmbRuangKhusus1.setName("cmbRuangKhusus1"); // NOI18N
        cmbRuangKhusus1.setPreferredSize(new java.awt.Dimension(120, 23));
        cmbRuangKhusus1.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                cmbRuangKhusus1MouseClicked(evt);
            }
        });
        cmbRuangKhusus1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                cmbRuangKhusus1ActionPerformed(evt);
            }
        });
        cmbRuangKhusus1.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                cmbRuangKhusus1KeyPressed(evt);
            }
        });
        panelCari.add(cmbRuangKhusus1);

        cmbRuangKhusus2.setForeground(new java.awt.Color(0, 0, 0));
        cmbRuangKhusus2.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "- pilih salah satu -", "ZAAL", "RKPD", "INTERNIST" }));
        cmbRuangKhusus2.setName("cmbRuangKhusus2"); // NOI18N
        cmbRuangKhusus2.setPreferredSize(new java.awt.Dimension(120, 23));
        cmbRuangKhusus2.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                cmbRuangKhusus2MouseClicked(evt);
            }
        });
        cmbRuangKhusus2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                cmbRuangKhusus2ActionPerformed(evt);
            }
        });
        cmbRuangKhusus2.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                cmbRuangKhusus2KeyPressed(evt);
            }
        });
        panelCari.add(cmbRuangKhusus2);

        cmbRuangKhusus4.setForeground(new java.awt.Color(0, 0, 0));
        cmbRuangKhusus4.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "- pilih salah satu -", "AR-RAUDAH", "SYARAF", "MATA-THT-KK" }));
        cmbRuangKhusus4.setName("cmbRuangKhusus4"); // NOI18N
        cmbRuangKhusus4.setPreferredSize(new java.awt.Dimension(120, 23));
        cmbRuangKhusus4.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                cmbRuangKhusus4MouseClicked(evt);
            }
        });
        cmbRuangKhusus4.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                cmbRuangKhusus4ActionPerformed(evt);
            }
        });
        cmbRuangKhusus4.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                cmbRuangKhusus4KeyPressed(evt);
            }
        });
        panelCari.add(cmbRuangKhusus4);

        cmbRuangKhusus3.setForeground(new java.awt.Color(0, 0, 0));
        cmbRuangKhusus3.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "- pilih salah satu -", "NICU", "BAYI-SEHAT" }));
        cmbRuangKhusus3.setName("cmbRuangKhusus3"); // NOI18N
        cmbRuangKhusus3.setPreferredSize(new java.awt.Dimension(120, 23));
        cmbRuangKhusus3.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                cmbRuangKhusus3MouseClicked(evt);
            }
        });
        cmbRuangKhusus3.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                cmbRuangKhusus3ActionPerformed(evt);
            }
        });
        cmbRuangKhusus3.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                cmbRuangKhusus3KeyPressed(evt);
            }
        });
        panelCari.add(cmbRuangKhusus3);

        cmbRuangKhusus5.setForeground(new java.awt.Color(0, 0, 0));
        cmbRuangKhusus5.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "- pilih salah satu -", "VIP", "SVIP", "ISOLASI COVID19", "ISOLASI BAYI COVID19", "COVID19" }));
        cmbRuangKhusus5.setName("cmbRuangKhusus5"); // NOI18N
        cmbRuangKhusus5.setPreferredSize(new java.awt.Dimension(150, 23));
        cmbRuangKhusus5.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                cmbRuangKhusus5MouseClicked(evt);
            }
        });
        cmbRuangKhusus5.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                cmbRuangKhusus5ActionPerformed(evt);
            }
        });
        cmbRuangKhusus5.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                cmbRuangKhusus5KeyPressed(evt);
            }
        });
        panelCari.add(cmbRuangKhusus5);

        PanelCariUtama.add(panelCari, java.awt.BorderLayout.PAGE_START);

        internalFrame1.add(PanelCariUtama, java.awt.BorderLayout.PAGE_END);

        Scroll.setToolTipText("Klik data di table, kemudian klik kanan untuk memilih menu yang diinginkan");
        Scroll.setComponentPopupMenu(jPopupMenu1);
        Scroll.setName("Scroll"); // NOI18N
        Scroll.setOpaque(true);
        Scroll.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                ScrollKeyPressed(evt);
            }
        });

        tbKamIn.setAutoCreateRowSorter(true);
        tbKamIn.setToolTipText("Klik data di table, kemudian klik kanan untuk memilih menu yang diinginkan");
        tbKamIn.setComponentPopupMenu(jPopupMenu1);
        tbKamIn.setName("tbKamIn"); // NOI18N
        tbKamIn.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                tbKamInMouseClicked(evt);
            }
        });
        tbKamIn.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                tbKamInKeyPressed(evt);
            }
        });
        Scroll.setViewportView(tbKamIn);

        internalFrame1.add(Scroll, java.awt.BorderLayout.CENTER);

        getContentPane().add(internalFrame1, java.awt.BorderLayout.CENTER);

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void norawatKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_norawatKeyPressed
        if (evt.getKeyCode() == KeyEvent.VK_PAGE_DOWN) {
            Sequel.cariIsi("select no_rkm_medis from reg_periksa where no_rawat=? ", TNoRM, norawat.getText());
            Sequel.cariIsi("select nm_pasien from pasien where no_rkm_medis=? ", TPasien, TNoRM.getText());
        } else if (evt.getKeyCode() == KeyEvent.VK_UP) {
            btnRegActionPerformed(null);
        } else {
            Valid.pindah(evt, BtnCloseIn, kdkamar);
        }
}//GEN-LAST:event_norawatKeyPressed

    private void kdkamarKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_kdkamarKeyPressed
        if (evt.getKeyCode() == KeyEvent.VK_PAGE_DOWN) {
            i = 1;
            isKmr();
        } else if (evt.getKeyCode() == KeyEvent.VK_ENTER) {
            CmbTahun.requestFocus();
            isKmr();
        } else if (evt.getKeyCode() == KeyEvent.VK_PAGE_UP) {
            norawat.requestFocus();
            isKmr();
        } else if (evt.getKeyCode() == KeyEvent.VK_UP) {
            btnKamarActionPerformed(null);
        }
}//GEN-LAST:event_kdkamarKeyPressed

    private void ttlbiayaKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_ttlbiayaKeyPressed
        // Valid.pindah(evt,TKdOb,BtnSimpan);
}//GEN-LAST:event_ttlbiayaKeyPressed

    private void btnRegActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnRegActionPerformed
        var.setform("DlgKamarInap");
        reg.emptTeks();
        reg.isCek();
        reg.setSize(internalFrame1.getWidth() - 40, internalFrame1.getHeight() - 40);
        reg.setLocationRelativeTo(internalFrame1);
        reg.setVisible(true);
}//GEN-LAST:event_btnRegActionPerformed

    private void btnRegKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_btnRegKeyPressed
        Valid.pindah(evt, norawat, kdkamar);
}//GEN-LAST:event_btnRegKeyPressed

    private void btnKamarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnKamarActionPerformed
        var.setform("DlgKamarInap");
        i = 1;
        kamar.load();
        kamar.isCek();
        kamar.emptTeks();
        kamar.tampil();
        kamar.setSize(internalFrame1.getWidth() - 40, internalFrame1.getHeight() - 40);
        kamar.setLocationRelativeTo(internalFrame1);
        kamar.setVisible(true);
        kamar.TCari.requestFocus();
}//GEN-LAST:event_btnKamarActionPerformed

    private void btnKamarKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_btnKamarKeyPressed
        Valid.pindah(evt, kdkamar, CmbTahun);
}//GEN-LAST:event_btnKamarKeyPressed

    private void BtnInActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_BtnInActionPerformed
        norawat.setEditable(true);
        kdkamar.setEditable(true);
        diagnosaawal.setEditable(true);
        diagnosaakhir.setVisible(false);
        btnDiagnosa.setVisible(false);
        cmbStatus.setVisible(false);
        jLabel26.setVisible(false);
        jLabel23.setVisible(false);
        LblStts.setText("Masuk/Check In");
        btnReg.setEnabled(true);
        btnKamar.setEnabled(true);
        emptTeks();
        lama = Sequel.cariIsiAngka("select lamajam from set_jam_minimal");
        hariawal = Sequel.cariIsi("select hariawal from set_jam_minimal");

        WindowInputKamar.setLocationRelativeTo(internalFrame1);
        WindowInputKamar.setVisible(true);
        cekKetMati();
}//GEN-LAST:event_BtnInActionPerformed

    private void BtnInKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_BtnInKeyPressed
        if (evt.getKeyCode() == KeyEvent.VK_SPACE) {
            BtnInActionPerformed(null);
        } else {
            Valid.pindah(evt, TCari, BtnOut);
        }
}//GEN-LAST:event_BtnInKeyPressed

    private void BtnOutActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_BtnOutActionPerformed
        cekUsia = 0;
        sttsumur = "";
        cekRMbayi = "";
        cekUsia = Sequel.cariInteger("select umurdaftar from reg_periksa where no_rawat='" + norawat.getText() + "'");
        sttsumur = Sequel.cariIsi("select sttsumur from reg_periksa where no_rawat='" + norawat.getText() + "'");
        cekRMbayi = Sequel.cariIsi("SELECT no_rkm_medis FROM pasien_bayi WHERE no_rkm_medis='" + TNoRM.getText() + "' ");

        if (TOut.getText().trim().length() > 0) {
            JOptionPane.showMessageDialog(null, "Maaf, pasien ini sudah pulang pada tanggal " + TOut.getText() + " ...!!!");
            emptTeks();
            tbKamIn.requestFocus();
        } else if ((TOut.getText().length() == 0) && (TPasien.getText().length() > 0)) {

            //cek data pasien bayi dulu---------------------
            if (cekRMbayi.equals("")) {
                if (cekUsia <= 28 && sttsumur.equals("Hr") && (kdkamar.getText().substring(0, 2).equals("BY") || kdkamar.getText().equals("ISO/BYVIP/101"))) {
                    x = JOptionPane.showConfirmDialog(null, "Pasien bayi ini belum bisa dipulangkan, silakan isi & "
                            + "lengkapi dulu data bayinya...!!", "Konfirmasi", JOptionPane.YES_NO_OPTION);
                    if (x == JOptionPane.YES_OPTION) {
                        DlgIKBBayi lahir = new DlgIKBBayi(null, false);
                        lahir.isCek();
                        lahir.emptTeks();
                        lahir.setSize(internalFrame1.getWidth(), internalFrame1.getHeight());
                        lahir.setLocationRelativeTo(internalFrame1);
                        lahir.setVisible(true);
                        lahir.bukaDataBayi(TNoRM.getText());
                        lahir.autoSKL();

                        emptTeks();
                        tampil();
                    } else {
                        emptTeks();
                        tampil();
                    }
                }
            }

            //jika data pasien bayi sudah terpenuhi atau data bukan termasuk pasien bayi, next---------->>>>>
            if (tabMode.getRowCount() == 0) {
                JOptionPane.showMessageDialog(null, "Maaf, data kamar inap pasien sudah habis...!!!!");
                BtnIn.requestFocus();
            } else if (TPasien.getText().trim().equals("")) {
//                tbKamIn.requestFocus();
            } else {
                PasiennyaPulang();
            }
        }
}//GEN-LAST:event_BtnOutActionPerformed

    private void BtnOutKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_BtnOutKeyPressed
        if (evt.getKeyCode() == KeyEvent.VK_SPACE) {
            BtnOutActionPerformed(null);
        } else {
            Valid.pindah(evt, BtnIn, btnPindah);
        }
}//GEN-LAST:event_BtnOutKeyPressed

    private void BtnKeluarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_BtnKeluarActionPerformed
        WindowInputKamar.dispose();
        dispose();
}//GEN-LAST:event_BtnKeluarActionPerformed

    private void BtnKeluarKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_BtnKeluarKeyPressed
        if (evt.getKeyCode() == KeyEvent.VK_SPACE) {
            WindowInputKamar.dispose();
            dispose();
        }
}//GEN-LAST:event_BtnKeluarKeyPressed

    private void TCariKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_TCariKeyPressed
        if (evt.getKeyCode() == KeyEvent.VK_ENTER) {
            BtnCariActionPerformed(null);
        } else if (evt.getKeyCode() == KeyEvent.VK_PAGE_DOWN) {
            BtnCari.requestFocus();
        } else if (evt.getKeyCode() == KeyEvent.VK_PAGE_UP) {
            BtnKeluar.requestFocus();
        }
}//GEN-LAST:event_TCariKeyPressed

    private void BtnCariActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_BtnCariActionPerformed
        tampil();
        emptTeks();
}//GEN-LAST:event_BtnCariActionPerformed

    private void BtnCariKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_BtnCariKeyPressed
        if (evt.getKeyCode() == KeyEvent.VK_SPACE) {
            BtnCariActionPerformed(null);
        } else {
            Valid.pindah(evt, TCari, BtnAll);
        }
}//GEN-LAST:event_BtnCariKeyPressed

    private void BtnAllActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_BtnAllActionPerformed
        TCari.setText("");
        BangsalCari.setText("");
        tampil();
        emptTeks();
}//GEN-LAST:event_BtnAllActionPerformed

    private void BtnAllKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_BtnAllKeyPressed
        if (evt.getKeyCode() == KeyEvent.VK_SPACE) {
            BtnAllActionPerformed(null);
        } else {
            Valid.pindah(evt, BtnCari, BtnIn);
        }
}//GEN-LAST:event_BtnAllKeyPressed

    private void cmbDtkKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_cmbDtkKeyPressed
        Valid.pindah(evt, cmbMnt, diagnosaawal);
    }//GEN-LAST:event_cmbDtkKeyPressed

    private void cmbMntKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_cmbMntKeyPressed
        Valid.pindah(evt, cmbJam, cmbDtk);
    }//GEN-LAST:event_cmbMntKeyPressed

    private void cmbJamKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_cmbJamKeyPressed
        Valid.pindah(evt, CmbTgl, cmbMnt);
    }//GEN-LAST:event_cmbJamKeyPressed

    private void TBangsalKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_TBangsalKeyPressed
        // TODO add your handling code here:
    }//GEN-LAST:event_TBangsalKeyPressed

    private void BtnCloseInActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_BtnCloseInActionPerformed
        TOut.setText("");
        var.setstatus(false);
        WindowInputKamar.dispose();
    }//GEN-LAST:event_BtnCloseInActionPerformed

    private void BtnCloseInKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_BtnCloseInKeyPressed
        if (evt.getKeyCode() == KeyEvent.VK_SPACE) {
            WindowInputKamar.dispose();
        } else {
            Valid.pindah(evt, BtnBatal, norawat);
        }
    }//GEN-LAST:event_BtnCloseInKeyPressed

    private void BtnSimpanActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_BtnSimpanActionPerformed
        kdDiag = "";
        tgm = CmbTahun.getSelectedItem() + "-" + CmbBln.getSelectedItem() + "-" + CmbTgl.getSelectedItem();
        date = new Date();
        date2 = new Date();
        timeIn = new Date();
        timeOut = new Date();
        dateIn = new Date();
        dateOut = new Date();
        now = dateFormat2.format(date);

        try {
            date = new SimpleDateFormat("yyyy-MM-dd").parse(tgm);
            date2 = new SimpleDateFormat("yyyy-MM-dd").parse(now);
            if (!JamMasuk.getText().equals("")) {
                timeIn = new SimpleDateFormat("HH:mm:ss").parse(JamMasuk.getText());
            }
            if (!TIn.getText().equals("")) {
                dateIn = new SimpleDateFormat("yyyy-MM-dd").parse(TIn.getText());
            }
            timeOut = new SimpleDateFormat("HH:mm:ss").parse(cmbJam.getSelectedItem() + ":" + cmbMnt.getSelectedItem() + ":" + cmbDtk.getSelectedItem());
            dateOut = new SimpleDateFormat("yyyy-MM-dd").parse(CmbTahun.getSelectedItem() + "-" + CmbBln.getSelectedItem() + "-" + CmbTgl.getSelectedItem());
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, "Data tidak bisa disimpan, Tanggal Pulang Tidak Valid !!!");
        }

        if (TPasien.getText().trim().equals("")) {
            Valid.textKosong(norawat, "pasien");
        } else if (TKdBngsal.getText().trim().equals("")) {
            Valid.textKosong(kdkamar, "kamar");
        } else if (Double.parseDouble(TJmlHari.getText().trim()) < 0) {
            JOptionPane.showMessageDialog(null, "Data Tidak Bisa disimpan, Cek Tanggal yang Anda Input !!!");
            BtnOutActionPerformed(null);
        } else if (date.after(date2)) {
            JOptionPane.showMessageDialog(null, "Data Tidak Bisa disimpan, Tanggal Pulang Tidak Valid !!!");
            BtnOutActionPerformed(null);
        } else {
            if (norawat.isEditable() == true) {
                switch (TSttsKamar.getText().trim()) {
                    case "ISI":
                        JOptionPane.showMessageDialog(null, "Maaf, Status kamar sudah terisi silahkan pilih kamar kosong..!!!!");
                        kdkamar.requestFocus();
                        break;
                    case "KOSONG":

                        Sequel.menyimpan("kamar_inap", "'" + norawat.getText() + "','"
                                + kdkamar.getText() + "','" + TTarif.getText() + "','"
                                + diagnosaawal.getText() + "','"
                                + diagnosaakhir.getText() + "','"
                                + CmbTahun.getSelectedItem() + "-" + CmbBln.getSelectedItem() + "-" + CmbTgl.getSelectedItem() + "','"
                                + cmbJam.getSelectedItem() + ":" + cmbMnt.getSelectedItem() + ":" + cmbDtk.getSelectedItem() + "','0000-00-00','00:00:00','" + TJmlHari.getText() + "','"
                                + ttlbiaya.getText() + "','-'", "No.Rawat");
                        Sequel.mengedit("reg_periksa", "no_rawat='" + norawat.getText() + "'", "status_lanjut='Ranap'");
                        Sequel.mengedit("kamar", "kd_kamar='" + kdkamar.getText() + "'", "status='ISI'");
                        Sequel.menyimpan("history_user", "Now(),'" + norawat.getText() + "','" + var.getkode() + "','Masuk Kamar Inap','Simpan'");

                        emptTeks();
                        WindowInputKamar.dispose();
                        break;
                }
                norawat.requestFocus();

            } else if (norawat.isEditable() == false) {
                if (cmbStatus.getSelectedItem().equals("-")) {
                    Valid.textKosong(cmbStatus, "Status Pulang");
                } else if (diagnosaakhir.getText().trim().equals("")) {
                    Valid.textKosong(diagnosaakhir, "Diagnosa Akhir");
                    cmbStatus.setSelectedIndex(0);
                    BtnOutActionPerformed(null);
                } else {
                    for (int i = 0; i < diagnosaakhir.getText().trim().length(); i++) {
                        if (i == diagnosaakhir.getText().length() - 1) {
                            kdDiag = kdDiag + diagnosaakhir.getText().substring(i, i + 1);
                            cekDb = Sequel.cariInteger("select count(-1) from penyakit where kd_penyakit =?", kdDiag.trim());
                            if (cekDb == 0) {
                                cekAda = cekAda + 1;
                            }
                            kdDiag = "";
                        } else if (diagnosaakhir.getText().substring(i, i + 1).equals(",")) {
                            cekDb = Sequel.cariInteger("select count(-1) from penyakit where kd_penyakit =?", kdDiag.trim());
                            if (cekDb == 0) {
                                cekAda = cekAda + 1;
                            }
                            kdDiag = "";
                        } else {
                            kdDiag = kdDiag + diagnosaakhir.getText().substring(i, i + 1);
                        }
                    }
                    if (cekAda > 0) {
                        JOptionPane.showMessageDialog(null, "Maaf, Kode Diagnosa Akhir Salah, Silakan Input Ulang......");
                        diagnosaakhir.setText("");
                        diagnosaakhir.requestFocus();
                        cekAda = 0;
                    } else {
                        if (dateIn.equals(dateOut)) {
                            if (timeIn.after(timeOut)) {
                                JOptionPane.showMessageDialog(null, "Waktu Pulang Salah, Jam Pulang Kurang dari Jam Masuk");
                            } else {
                                Sequel.mengedit("kamar_inap", "no_rawat='" + norawat.getText() + "' and kd_kamar='" + kdkamar.getText() + "' and tgl_masuk='" + TIn.getText() + "' and jam_masuk='" + JamMasuk.getText() + "'",
                                        "tgl_keluar='" + CmbTahun.getSelectedItem() + "-" + CmbBln.getSelectedItem() + "-" + CmbTgl.getSelectedItem()
                                        + "',trf_kamar='" + TTarif.getText() + "',jam_keluar='" + cmbJam.getSelectedItem() + ":" + cmbMnt.getSelectedItem() + ":" + cmbDtk.getSelectedItem()
                                        + "',ttl_biaya='" + ttlbiaya.getText() + "',stts_pulang='" + cmbStatus.getSelectedItem() + "',diagnosa_akhir='" + diagnosaakhir.getText() + "',lama='" + TJmlHari.getText() + "'");

                                //jika pulang dengan meninggal
                                if (cmbStatus.getSelectedItem().equals("Meninggal >= 48 Jam") || (cmbStatus.getSelectedItem().equals("Meninggal < 48 Jam"))) {
                                    Sequel.menyimpan("pasien_mati", "'" + Valid.SetTgl(TglMati.getSelectedItem() + "") + "','"
                                            + cmbJam1.getSelectedItem() + ":" + cmbMnt1.getSelectedItem() + ":" + cmbDtk1.getSelectedItem() + "','"
                                            + TNoRM.getText() + "','" + cmbStatus.getSelectedItem() + " diruang " + TBangsal.getText() + " - "
                                            + ket.getText() + "','Rumah Sakit','-','-','-','-','Ruangan Inap','-'", "pasien");

                                    x = JOptionPane.showConfirmDialog(null, "Apakah anda akan mencetak surat keterangan kematian ?", "Konfirmasi", JOptionPane.YES_NO_OPTION);
                                    if (x == JOptionPane.YES_OPTION) {
                                        ctkSuratMati();
                                    } else {
                                        BtnCari.requestFocus();
                                    }

                                    //jika pulang dengan dirujuk
                                } else if (cmbStatus.getSelectedItem().equals("Dirujuk")) {
                                    DlgRujuk dlgrjk = new DlgRujuk(null, false);
                                    dlgrjk.setSize(863, 494);
                                    dlgrjk.setLocationRelativeTo(internalFrame1);
                                    dlgrjk.emptTeks();
                                    dlgrjk.isCek();
                                    dlgrjk.setNoRm(norawat.getText(), DTPCari1.getDate(), DTPCari2.getDate());
                                    dlgrjk.tampil();
                                    dlgrjk.setVisible(true);
                                    dlgrjk.btnFaskes.requestFocus();

                                    //jika pulang dengan APS
                                } else if (cmbStatus.getSelectedItem().equals("APS")) {
                                    Sequel.menyimpan("ranap_aps", "'" + norawat.getText() + "','" + kdAPS + "','" + ketAPS.getText() + "'");
                                    JOptionPane.showMessageDialog(null, "Data berhasil tersimpan, utk. mencetak surat pernyataan APS klik tombol cetak...!!!!");
                                }

                                //update tanggal pulang didata bridging sep
                                if (cekPXbpjs >= 1) {
                                    Sequel.mengedit("bridging_sep", "no_rawat='" + norawat.getText() + "' and jnspelayanan='1'",
                                            "tglpulang='" + CmbTahun.getSelectedItem() + "-" + CmbBln.getSelectedItem() + "-" + CmbTgl.getSelectedItem() + " "
                                            + cmbJam.getSelectedItem() + ":" + cmbMnt.getSelectedItem() + ":" + cmbDtk.getSelectedItem() + "'");
                                }

                                Sequel.mengedit("kamar", "kd_kamar='" + kdkamar.getText() + "'", "status='KOSONG'");
                                Sequel.menyimpan("history_user", "Now(),'" + norawat.getText() + "','" + var.getkode() + "','Pasien Pulang','Simpan'");

                                WindowInputKamar.dispose();
                                emptTeks();

                            }
                        } else {
                            Sequel.mengedit("kamar_inap", "no_rawat='" + norawat.getText() + "' and kd_kamar='" + kdkamar.getText() + "' and tgl_masuk='" + TIn.getText() + "' and jam_masuk='" + JamMasuk.getText() + "'",
                                    "tgl_keluar='" + CmbTahun.getSelectedItem() + "-" + CmbBln.getSelectedItem() + "-" + CmbTgl.getSelectedItem()
                                    + "',trf_kamar='" + TTarif.getText() + "',jam_keluar='" + cmbJam.getSelectedItem() + ":" + cmbMnt.getSelectedItem() + ":" + cmbDtk.getSelectedItem()
                                    + "',ttl_biaya='" + ttlbiaya.getText() + "',stts_pulang='" + cmbStatus.getSelectedItem() + "',diagnosa_akhir='" + diagnosaakhir.getText() + "',lama='" + TJmlHari.getText() + "'");

                            //jika pulang dengan meninggal
                            if (cmbStatus.getSelectedItem().equals("Meninggal >= 48 Jam") || (cmbStatus.getSelectedItem().equals("Meninggal < 48 Jam"))) {
                                Sequel.menyimpan("pasien_mati", "'" + Valid.SetTgl(TglMati.getSelectedItem() + "") + "','"
                                        + cmbJam1.getSelectedItem() + ":" + cmbMnt1.getSelectedItem() + ":" + cmbDtk1.getSelectedItem() + "','"
                                        + TNoRM.getText() + "','" + cmbStatus.getSelectedItem() + " diruang " + TBangsal.getText() + " - "
                                        + ket.getText() + "','Rumah Sakit','-','-','-','-','Ruangan Inap','-'", "pasien");

                                x = JOptionPane.showConfirmDialog(null, "Apakah anda akan mencetak surat keterangan kematian ?", "Konfirmasi", JOptionPane.YES_NO_OPTION);
                                if (x == JOptionPane.YES_OPTION) {
                                    ctkSuratMati();
                                } else {
                                    BtnCari.requestFocus();
                                }

                                //jika pulang dengan dirujuk
                            } else if (cmbStatus.getSelectedItem().equals("Dirujuk")) {
                                DlgRujuk dlgrjk = new DlgRujuk(null, false);
                                dlgrjk.setSize(863, 494);
                                dlgrjk.setLocationRelativeTo(internalFrame1);
                                dlgrjk.emptTeks();
                                dlgrjk.isCek();
                                dlgrjk.setNoRm(norawat.getText(), DTPCari1.getDate(), DTPCari2.getDate());
                                dlgrjk.tampil();
                                dlgrjk.setVisible(true);
                                dlgrjk.btnFaskes.requestFocus();

                                //jika pulang dengan APS
                            } else if (cmbStatus.getSelectedItem().equals("APS")) {
                                Sequel.menyimpan("ranap_aps", "'" + norawat.getText() + "','" + kdAPS + "','" + ketAPS.getText() + "'");
                                JOptionPane.showMessageDialog(null, "Data berhasil tersimpan, utk. mencetak surat pernyataan APS klik tombol cetak...!!!!");
                            }

                            //update tanggal pulang didata bridging sep
                            if (cekPXbpjs >= 1) {
                                Sequel.mengedit("bridging_sep", "no_rawat='" + norawat.getText() + "' and jnspelayanan='1'",
                                        "tglpulang='" + CmbTahun.getSelectedItem() + "-" + CmbBln.getSelectedItem() + "-" + CmbTgl.getSelectedItem() + " "
                                        + cmbJam.getSelectedItem() + ":" + cmbMnt.getSelectedItem() + ":" + cmbDtk.getSelectedItem() + "'");
                            }

                            Sequel.mengedit("kamar", "kd_kamar='" + kdkamar.getText() + "'", "status='KOSONG'");
                            Sequel.menyimpan("history_user", "Now(),'" + norawat.getText() + "','" + var.getkode() + "','Pasien Pulang','Simpan'");

                            WindowInputKamar.dispose();
                            emptTeks();
                        }
//                        Sequel.mengedit("kamar_inap", "no_rawat='" + norawat.getText() + "' and kd_kamar='" + kdkamar.getText() + "' and tgl_masuk='" + TIn.getText() + "' and jam_masuk='" + JamMasuk.getText() + "'",
//                                "tgl_keluar='" + CmbTahun.getSelectedItem() + "-" + CmbBln.getSelectedItem() + "-" + CmbTgl.getSelectedItem()
//                                + "',trf_kamar='" + TTarif.getText() + "',jam_keluar='" + cmbJam.getSelectedItem() + ":" + cmbMnt.getSelectedItem() + ":" + cmbDtk.getSelectedItem()
//                                + "',ttl_biaya='" + ttlbiaya.getText() + "',stts_pulang='" + cmbStatus.getSelectedItem() + "',diagnosa_akhir='" + diagnosaakhir.getText() + "',lama='" + TJmlHari.getText() + "'");
//
//                        if (cmbStatus.getSelectedItem().equals("Meninggal >= 48 Jam") || (cmbStatus.getSelectedItem().equals("Meninggal < 48 Jam"))) {
//                            Sequel.menyimpan("pasien_mati", "'" + Valid.SetTgl(TglMati.getSelectedItem() + "") + "','"
//                                    + cmbJam1.getSelectedItem() + ":" + cmbMnt1.getSelectedItem() + ":" + cmbDtk1.getSelectedItem() + "','"
//                                    + TNoRM.getText() + "','" + cmbStatus.getSelectedItem() + " diruang " + TBangsal.getText() + " - "
//                                    + ket.getText() + "','Rumah Sakit','-','-','-','-','Ruangan Inap','-'", "pasien");
//
//                            x = JOptionPane.showConfirmDialog(null, "Apakah anda akan mencetak surat keterangan kematian ?", "Konfirmasi", JOptionPane.YES_NO_OPTION);
//                            if (x == JOptionPane.YES_OPTION) {
//                                ctkSuratMati();
//                            } else {
//                                BtnCari.requestFocus();
//                            }
//
//                        } else if (cmbStatus.getSelectedItem().equals("Dirujuk")) {
//                            DlgRujuk dlgrjk = new DlgRujuk(null, false);
//                            dlgrjk.setSize(863, 494);
//                            dlgrjk.setLocationRelativeTo(internalFrame1);
//                            dlgrjk.emptTeks();
//                            dlgrjk.isCek();
//                            dlgrjk.setNoRm(norawat.getText(), DTPCari1.getDate(), DTPCari2.getDate());
//                            dlgrjk.tampil();
//                            dlgrjk.setVisible(true);
//                            dlgrjk.btnFaskes.requestFocus();
//                        }
//
//                        Sequel.mengedit("kamar", "kd_kamar='" + kdkamar.getText() + "'", "status='KOSONG'");
//                        Sequel.menyimpan("history_user", "Now(),'" + norawat.getText() + "','" + var.getkode() + "','Pasien Pulang','Simpan'");
//
//                        WindowInputKamar.dispose();
//                        emptTeks();
                    }
                }
            }
            tampil();
        }
    }//GEN-LAST:event_BtnSimpanActionPerformed

    private void BtnSimpanKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_BtnSimpanKeyPressed
        Valid.pindah(evt, cmbDtk, BtnBatal);
    }//GEN-LAST:event_BtnSimpanKeyPressed

    private void BtnBatalActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_BtnBatalActionPerformed
        if (norawat.isEditable() == true) {
            emptTeks();
        } else if (norawat.isEditable() == false) {
            emptTeks();
            WindowInputKamar.dispose();
        }
    }//GEN-LAST:event_BtnBatalActionPerformed

    private void BtnBatalKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_BtnBatalKeyPressed
        if (evt.getKeyCode() == KeyEvent.VK_SPACE) {
            emptTeks();
        } else {
            Valid.pindah(evt, BtnSimpan, BtnCloseIn);
        }
    }//GEN-LAST:event_BtnBatalKeyPressed

    private void TInKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_TInKeyPressed
        // TODO add your handling code here:
    }//GEN-LAST:event_TInKeyPressed

    private void DTPTglItemStateChanged(java.awt.event.ItemEvent evt) {//GEN-FIRST:event_DTPTglItemStateChanged

    }//GEN-LAST:event_DTPTglItemStateChanged

    private void DTPCari1ItemStateChanged(java.awt.event.ItemEvent evt) {//GEN-FIRST:event_DTPCari1ItemStateChanged
        R2.setSelected(true);

}//GEN-LAST:event_DTPCari1ItemStateChanged

    private void DTPCari1KeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_DTPCari1KeyPressed
        Valid.pindah(evt, BangsalCari, DTPCari2);
}//GEN-LAST:event_DTPCari1KeyPressed

    private void DTPCari2KeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_DTPCari2KeyPressed
        Valid.pindah(evt, DTPCari1, BangsalCari);
}//GEN-LAST:event_DTPCari2KeyPressed

    private void diagnosaawalKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_diagnosaawalKeyPressed
        Valid.pindah(evt, cmbDtk, diagnosaakhir);
    }//GEN-LAST:event_diagnosaawalKeyPressed

    private void diagnosaakhirKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_diagnosaakhirKeyPressed
        Valid.pindah(evt, diagnosaawal, BtnSimpan);
    }//GEN-LAST:event_diagnosaakhirKeyPressed

    private void CmbTahunItemStateChanged(java.awt.event.ItemEvent evt) {//GEN-FIRST:event_CmbTahunItemStateChanged
        if ((WindowInputKamar.isVisible() == true) && (!TBangsal.getText().equals("")) && (!norawat.getText().equals(""))) {
            if (TIn.getText().equals("")) {
                tglmasuk = CmbTahun.getSelectedItem() + "-" + CmbBln.getSelectedItem() + "-" + CmbTgl.getSelectedItem();
                jammasuk = cmbJam.getSelectedItem() + ":" + cmbMnt.getSelectedItem() + ":" + cmbDtk.getSelectedItem();
            } else {
                tglmasuk = TIn.getText();
                jammasuk = JamMasuk.getText();
            }
            if (hariawal.equals("Yes")) {
                Sequel.cariIsi("select (if(to_days('" + CmbTahun.getSelectedItem() + "-" + CmbBln.getSelectedItem() + "-" + CmbTgl.getSelectedItem() + " " + cmbJam.getSelectedItem() + ":" + cmbMnt.getSelectedItem() + ":" + cmbDtk.getSelectedItem() + "')-to_days('" + tglmasuk + " " + jammasuk + "')=0,if(time_to_sec('" + CmbTahun.getSelectedItem() + "-" + CmbBln.getSelectedItem() + "-" + CmbTgl.getSelectedItem() + " " + cmbJam.getSelectedItem() + ":" + cmbMnt.getSelectedItem() + ":" + cmbDtk.getSelectedItem() + "')-time_to_sec('" + tglmasuk + " " + jammasuk + "')>(3600*" + lama + "),1,0),to_days('" + CmbTahun.getSelectedItem() + "-" + CmbBln.getSelectedItem() + "-" + CmbTgl.getSelectedItem() + " " + cmbJam.getSelectedItem() + ":" + cmbMnt.getSelectedItem() + ":" + cmbDtk.getSelectedItem() + "')-to_days('" + tglmasuk + " " + jammasuk + "'))+1) as lama", TJmlHari);
                if (Double.parseDouble(TJmlHari.getText()) == 0) {
                    TJmlHari.setText("1");
                }
            } else {
                Sequel.cariIsi("select (if(to_days('" + CmbTahun.getSelectedItem() + "-" + CmbBln.getSelectedItem() + "-" + CmbTgl.getSelectedItem() + " " + cmbJam.getSelectedItem() + ":" + cmbMnt.getSelectedItem() + ":" + cmbDtk.getSelectedItem() + "')-to_days('" + tglmasuk + " " + jammasuk + "')=0,if(time_to_sec('" + CmbTahun.getSelectedItem() + "-" + CmbBln.getSelectedItem() + "-" + CmbTgl.getSelectedItem() + " " + cmbJam.getSelectedItem() + ":" + cmbMnt.getSelectedItem() + ":" + cmbDtk.getSelectedItem() + "')-time_to_sec('" + tglmasuk + " " + jammasuk + "')>(3600*" + lama + "),1,0),to_days('" + CmbTahun.getSelectedItem() + "-" + CmbBln.getSelectedItem() + "-" + CmbTgl.getSelectedItem() + " " + cmbJam.getSelectedItem() + ":" + cmbMnt.getSelectedItem() + ":" + cmbDtk.getSelectedItem() + "')-to_days('" + tglmasuk + " " + jammasuk + "'))) as lama", TJmlHari);
                if (Double.parseDouble(TJmlHari.getText()) == 0) {
                    TJmlHari.setText("1");
                }
            }
            //Sequel.cariIsi("select (if(to_days('"+CmbTahun.getSelectedItem()+"-"+CmbBln.getSelectedItem()+"-"+CmbTgl.getSelectedItem()+" "+cmbJam.getSelectedItem()+":"+cmbMnt.getSelectedItem()+":"+cmbDtk.getSelectedItem()+"')-to_days('"+tglmasuk+" "+jammasuk+"')=0,if(time_to_sec('"+CmbTahun.getSelectedItem()+"-"+CmbBln.getSelectedItem()+"-"+CmbTgl.getSelectedItem()+" "+cmbJam.getSelectedItem()+":"+cmbMnt.getSelectedItem()+":"+cmbDtk.getSelectedItem()+"')-time_to_sec('"+tglmasuk+" "+jammasuk+"')>(3600*"+lama+"),1,0),to_days('"+CmbTahun.getSelectedItem()+"-"+CmbBln.getSelectedItem()+"-"+CmbTgl.getSelectedItem()+" "+cmbJam.getSelectedItem()+":"+cmbMnt.getSelectedItem()+":"+cmbDtk.getSelectedItem()+"')-to_days('"+tglmasuk+" "+jammasuk+"'))+1) as lama",TJmlHari);             
        }
    }//GEN-LAST:event_CmbTahunItemStateChanged

    private void CmbTahunKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_CmbTahunKeyPressed
        Valid.pindah(evt, kdkamar, CmbBln);
    }//GEN-LAST:event_CmbTahunKeyPressed

    private void CmbBlnKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_CmbBlnKeyPressed
        Valid.pindah(evt, CmbTahun, CmbTgl);
    }//GEN-LAST:event_CmbBlnKeyPressed

    private void CmbTglKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_CmbTglKeyPressed
        Valid.pindah(evt, CmbBln, cmbJam);
    }//GEN-LAST:event_CmbTglKeyPressed

    private void TOutKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_TOutKeyPressed
        // TODO add your handling code here:
}//GEN-LAST:event_TOutKeyPressed

    private void tbKamInMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tbKamInMouseClicked
        if (tabMode.getRowCount() != 0) {
            try {
                getData();
            } catch (java.lang.NullPointerException e) {
            }

            if (evt.getClickCount() == 2) {
                i = tbKamIn.getSelectedColumn();
                if (i == 0) {
                    if (var.gettindakan_ranap() == true) {
                        MnRawatInapActionPerformed(null);
                    }
                } else if (i == 1) {
                    if (var.getberi_obat() == true) {
                        //MnPemberianObatActionPerformed(null);
                        if (tabMode.getRowCount() == 0) {
                            JOptionPane.showMessageDialog(null, "Maaf, tabel masih kosong...!!!!");
                            TCari.requestFocus();
                        } else if (TPasien.getText().trim().equals("")) {
                            try {
                                psanak = koneksi.prepareStatement(
                                        "select pasien.no_rkm_medis,pasien.nm_pasien,ranap_gabung.no_rawat2,concat(reg_periksa.umurdaftar,' ',reg_periksa.sttsumur)as umur,pasien.no_peserta, "
                                        + "concat(pasien.alamatpj,', ',pasien.kelurahanpj,', ',pasien.kecamatanpj,', ',pasien.kabupatenpj) as alamat "
                                        + "from reg_periksa inner join pasien inner join ranap_gabung on "
                                        + "pasien.no_rkm_medis=reg_periksa.no_rkm_medis and ranap_gabung.no_rawat2=reg_periksa.no_rawat where ranap_gabung.no_rawat=?");
                                try {
                                    psanak.setString(1, tbKamIn.getValueAt(tbKamIn.getSelectedRow(), 0).toString());
                                    rs2 = psanak.executeQuery();
                                    if (rs2.next()) {
                                        var.setform("DlgKamarInap");
                                        bangsal = Sequel.cariIsi("select kd_depo from set_depo_ranap where kd_bangsal=?", Sequel.cariIsi("select kd_bangsal from kamar where kd_kamar=?", kdkamar.getText()));
                                        if (bangsal.equals("")) {
                                            if (Sequel.cariIsi("select asal_stok from set_lokasi").equals("Gunakan Stok Bangsal")) {
                                                var.setkdbangsal(Sequel.cariIsi("select kd_bangsal from kamar where kd_kamar=?", kdkamar.getText()));
                                            } else {
                                                var.setkdbangsal(Sequel.cariIsi("select kd_bangsal from set_lokasi"));
                                            }
                                        } else {
                                            var.setkdbangsal(bangsal);
                                        }

                                        billing.beriobat.dlgobt.setSize(internalFrame1.getWidth(), internalFrame1.getHeight());
                                        billing.beriobat.dlgobt.setLocationRelativeTo(internalFrame1);
                                        billing.beriobat.dlgobt.setNoRm(rs2.getString("no_rawat2"), DTPCari1.getDate(), cmbJam.getSelectedItem().toString(), cmbMnt.getSelectedItem().toString(), cmbDtk.getSelectedItem().toString(), false);
                                        billing.beriobat.dlgobt.isCek();
                                        billing.beriobat.dlgobt.tampil();
                                        billing.beriobat.dlgobt.setVisible(true);
                                    } else {
                                        JOptionPane.showMessageDialog(null, "Maaf, Silahkan anda pilih dulu pasien...!!!");
                                        tbKamIn.requestFocus();
                                    }
                                } catch (Exception ex) {
                                    System.out.println("Notifikasi : " + ex);
                                } finally {
                                    if (rs2 != null) {
                                        rs2.close();
                                    }
                                    if (psanak != null) {
                                        psanak.close();
                                    }
                                }
                            } catch (Exception e) {
                                System.out.println(e);
                            }
                        } else {
                            var.setform("DlgKamarInap");
                            bangsal = Sequel.cariIsi("select kd_depo from set_depo_ranap where kd_bangsal=?", Sequel.cariIsi("select kd_bangsal from kamar where kd_kamar=?", kdkamar.getText()));
                            if (bangsal.equals("")) {
                                if (Sequel.cariIsi("select asal_stok from set_lokasi").equals("Gunakan Stok Bangsal")) {
                                    var.setkdbangsal(Sequel.cariIsi("select kd_bangsal from kamar where kd_kamar=?", kdkamar.getText()));
                                } else {
                                    var.setkdbangsal(Sequel.cariIsi("select kd_bangsal from set_lokasi"));
                                }
                            } else {
                                var.setkdbangsal(bangsal);
                            }
                            billing.beriobat.dlgobt.setSize(internalFrame1.getWidth(), internalFrame1.getHeight());
                            billing.beriobat.dlgobt.setLocationRelativeTo(internalFrame1);
                            billing.beriobat.dlgobt.setNoRm(norawat.getText(), DTPCari1.getDate(), cmbJam.getSelectedItem().toString(), cmbMnt.getSelectedItem().toString(), cmbDtk.getSelectedItem().toString(), false);
                            billing.beriobat.dlgobt.isCek();
                            billing.beriobat.dlgobt.tampil();
                            billing.beriobat.dlgobt.setVisible(true);
                            //this.dispose();
                        }
                    }
                } else if (i == 2) {
                    //if(var.getbilling_ranap()==true){
                    MnBillingActionPerformed(null);
                    //}                    
                } else if (i == 3) {
                    if (var.getresep_pulang() == true) {
                        MnInputResepActionPerformed(null);
                    }
                } else if (i == 18) {
                    if (var.getdpjp_ranap() == true) {
                        MnDPJPActionPerformed(null);
                    }
                }
            }
        }
}//GEN-LAST:event_tbKamInMouseClicked

    private void tbKamInKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_tbKamInKeyPressed
        if (tabMode.getRowCount() != 0) {
            if ((evt.getKeyCode() == KeyEvent.VK_ENTER) || (evt.getKeyCode() == KeyEvent.VK_UP) || (evt.getKeyCode() == KeyEvent.VK_DOWN)) {
                try {
                    getData();
                } catch (java.lang.NullPointerException e) {
                }
            }

            if (evt.getKeyCode() == KeyEvent.VK_SPACE) {
                i = tbKamIn.getSelectedColumn();
                if (i == 0) {
                    if (var.gettindakan_ranap() == true) {
                        MnRawatInapActionPerformed(null);
                    }
                } else if (i == 1) {
                    if (var.getberi_obat() == true) {
                        //MnPemberianObatActionPerformed(null);
                        if (tabMode.getRowCount() == 0) {
                            JOptionPane.showMessageDialog(null, "Maaf, tabel masih kosong...!!!!");
                            TCari.requestFocus();
                        } else if (TPasien.getText().trim().equals("")) {
                            try {
                                psanak = koneksi.prepareStatement(
                                        "select pasien.no_rkm_medis,pasien.nm_pasien,ranap_gabung.no_rawat2,concat(reg_periksa.umurdaftar,' ',reg_periksa.sttsumur)as umur,pasien.no_peserta, "
                                        + "concat(pasien.alamatpj,', ',pasien.kelurahanpj,', ',pasien.kecamatanpj,', ',pasien.kabupatenpj) as alamat "
                                        + "from reg_periksa inner join pasien inner join ranap_gabung on "
                                        + "pasien.no_rkm_medis=reg_periksa.no_rkm_medis and ranap_gabung.no_rawat2=reg_periksa.no_rawat where ranap_gabung.no_rawat=?");
                                try {
                                    psanak.setString(1, tbKamIn.getValueAt(tbKamIn.getSelectedRow(), 0).toString());
                                    rs2 = psanak.executeQuery();
                                    if (rs2.next()) {
                                        var.setform("DlgKamarInap");
                                        bangsal = Sequel.cariIsi("select kd_depo from set_depo_ranap where kd_bangsal=?", Sequel.cariIsi("select kd_bangsal from kamar where kd_kamar=?", kdkamar.getText()));
                                        if (bangsal.equals("")) {
                                            if (Sequel.cariIsi("select asal_stok from set_lokasi").equals("Gunakan Stok Bangsal")) {
                                                var.setkdbangsal(Sequel.cariIsi("select kd_bangsal from kamar where kd_kamar=?", kdkamar.getText()));
                                            } else {
                                                var.setkdbangsal(Sequel.cariIsi("select kd_bangsal from set_lokasi"));
                                            }
                                        } else {
                                            var.setkdbangsal(bangsal);
                                        }

                                        billing.beriobat.dlgobt.setSize(internalFrame1.getWidth(), internalFrame1.getHeight());
                                        billing.beriobat.dlgobt.setLocationRelativeTo(internalFrame1);
                                        billing.beriobat.dlgobt.setNoRm(rs2.getString("no_rawat2"), DTPCari1.getDate(), cmbJam.getSelectedItem().toString(), cmbMnt.getSelectedItem().toString(), cmbDtk.getSelectedItem().toString(), false);
                                        billing.beriobat.dlgobt.isCek();
                                        billing.beriobat.dlgobt.tampil();
                                        billing.beriobat.dlgobt.setVisible(true);
                                    } else {
                                        JOptionPane.showMessageDialog(null, "Maaf, Silahkan anda pilih dulu pasien...!!!");
                                        tbKamIn.requestFocus();
                                    }
                                } catch (Exception ex) {
                                    System.out.println("Notifikasi : " + ex);
                                } finally {
                                    if (rs2 != null) {
                                        rs2.close();
                                    }
                                    if (psanak != null) {
                                        psanak.close();
                                    }
                                }
                            } catch (Exception e) {
                                System.out.println(e);
                            }
                        } else {
                            var.setform("DlgKamarInap");
                            bangsal = Sequel.cariIsi("select kd_depo from set_depo_ranap where kd_bangsal=?", Sequel.cariIsi("select kd_bangsal from kamar where kd_kamar=?", kdkamar.getText()));
                            if (bangsal.equals("")) {
                                if (Sequel.cariIsi("select asal_stok from set_lokasi").equals("Gunakan Stok Bangsal")) {
                                    var.setkdbangsal(Sequel.cariIsi("select kd_bangsal from kamar where kd_kamar=?", kdkamar.getText()));
                                } else {
                                    var.setkdbangsal(Sequel.cariIsi("select kd_bangsal from set_lokasi"));
                                }
                            } else {
                                var.setkdbangsal(bangsal);
                            }
                            billing.beriobat.dlgobt.setSize(internalFrame1.getWidth(), internalFrame1.getHeight());
                            billing.beriobat.dlgobt.setLocationRelativeTo(internalFrame1);
                            billing.beriobat.dlgobt.setNoRm(norawat.getText(), DTPCari1.getDate(), cmbJam.getSelectedItem().toString(), cmbMnt.getSelectedItem().toString(), cmbDtk.getSelectedItem().toString(), false);
                            billing.beriobat.dlgobt.isCek();
                            billing.beriobat.dlgobt.tampil();
                            billing.beriobat.dlgobt.setVisible(true);
                            //this.dispose();
                        }
                    }
                } else if (i == 2) {
                    //if(var.getbilling_ranap()==true){
                    MnBillingActionPerformed(null);
                    //}                    
                } else if (i == 3) {
                    if (var.getresep_pulang() == true) {
                        MnInputResepActionPerformed(null);
                    }
                } else if (i == 18) {
                    if (var.getdpjp_ranap() == true) {
                        MnDPJPActionPerformed(null);
                    }
                }
            }
        }
}//GEN-LAST:event_tbKamInKeyPressed

private void btnBangsalCariActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnBangsalCariActionPerformed
    var.setform("DlgKamarInap");
    kamar.bangsal.isCek();
    kamar.bangsal.emptTeks();
    kamar.bangsal.setSize(internalFrame1.getWidth() - 40, internalFrame1.getHeight() - 40);
    kamar.bangsal.setLocationRelativeTo(internalFrame1);
    kamar.bangsal.setVisible(true);
}//GEN-LAST:event_btnBangsalCariActionPerformed

private void btnBangsalCariKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_btnBangsalCariKeyPressed
    Valid.pindah(evt, DTPCari2, TCari);
}//GEN-LAST:event_btnBangsalCariKeyPressed

private void BangsalCariKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_BangsalCariKeyPressed
    if (evt.getKeyCode() == KeyEvent.VK_ENTER) {
        tampil();
    } else if (evt.getKeyCode() == KeyEvent.VK_UP) {
        btnBangsalCariActionPerformed(null);
    } else if (evt.getKeyCode() == KeyEvent.VK_PAGE_UP) {
        DTPCari3.requestFocus();
    } else if (evt.getKeyCode() == KeyEvent.VK_PAGE_DOWN) {
        TCari.requestFocus();
    }
}//GEN-LAST:event_BangsalCariKeyPressed

private void MnRawatInapActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_MnRawatInapActionPerformed
    cekUsia = 0;
    sttsumur = "";
    cekRMbayi = "";
    cekUsia = Sequel.cariInteger("select umurdaftar from reg_periksa where no_rawat='" + norawat.getText() + "'");
    sttsumur = Sequel.cariIsi("select sttsumur from reg_periksa where no_rawat='" + norawat.getText() + "'");
    cekRMbayi = Sequel.cariIsi("SELECT no_rkm_medis FROM pasien_bayi WHERE no_rkm_medis='" + TNoRM.getText() + "' ");

    //cek data pasien bayi dulu---------------------
    if (cekRMbayi.equals("")) {
        if (cekUsia <= 28 && sttsumur.equals("Hr") && (kdkamar.getText().substring(0, 2).equals("BY") || kdkamar.getText().equals("ISO/BYVIP/101"))) {
            x = JOptionPane.showConfirmDialog(null, "Tagihan/tindakan rawat inap tidak bisa dimasukkan, silakan isi & "
                    + "lengkapi dulu data bayinya...!!", "Konfirmasi", JOptionPane.YES_NO_OPTION);
            if (x == JOptionPane.YES_OPTION) {
                DlgIKBBayi lahir = new DlgIKBBayi(null, false);
                lahir.tampil();
                lahir.isCek();
                lahir.emptTeks();
                lahir.setSize(internalFrame1.getWidth(), internalFrame1.getHeight());
                lahir.setLocationRelativeTo(internalFrame1);
                lahir.setVisible(true);
                lahir.bukaDataBayi(TNoRM.getText());
                lahir.autoSKL();

                emptTeks();
                tampil();
            } else {
                emptTeks();
                tampil();
            }
        }
    }

    //jika data pasien bayi sudah terpenuhi atau data bukan termasuk pasien bayi, next---------->>>>>
    if (tabMode.getRowCount() == 0) {
        JOptionPane.showMessageDialog(null, "Maaf, tabel masih kosong...!!!!");
        TCari.requestFocus();
    } else if (TPasien.getText().trim().equals("")) {
        try {
            psanak = koneksi.prepareStatement(
                    "select pasien.no_rkm_medis,pasien.nm_pasien,ranap_gabung.no_rawat2,concat(reg_periksa.umurdaftar,' ',reg_periksa.sttsumur)as umur,pasien.no_peserta, "
                    + "concat(pasien.alamatpj,', ',pasien.kelurahanpj,', ',pasien.kecamatanpj,', ',pasien.kabupatenpj) as alamat "
                    + "from reg_periksa inner join pasien inner join ranap_gabung on "
                    + "pasien.no_rkm_medis=reg_periksa.no_rkm_medis and ranap_gabung.no_rawat2=reg_periksa.no_rawat where ranap_gabung.no_rawat=?");
            try {
                psanak.setString(1, tbKamIn.getValueAt(tbKamIn.getSelectedRow(), 0).toString());
                rs2 = psanak.executeQuery();
                if (rs2.next()) {
                    if (Sequel.cariRegistrasi(rs2.getString("no_rawat2")) > 0) {
                        JOptionPane.showMessageDialog(rootPane, "Data billing sudah terverifikasi, data tidak boleh dihapus/diubah. Silahkan hubungi bagian kasir/keuangan ..!!");
                    } else {
                        var.setform("DlgKamarInap");
                        billing.rawatinap.setSize(internalFrame1.getWidth(), internalFrame1.getHeight());
                        billing.rawatinap.setLocationRelativeTo(internalFrame1);
                        billing.rawatinap.isCek();
                        billing.rawatinap.setNoRm(rs2.getString("no_rawat2"), DTPCari1.getDate(), DTPCari2.getDate());
                        billing.rawatinap.setHidup((String) cmbStatus.getSelectedItem());
                        billing.rawatinap.dataRawat(ruangrawat.getText(), Valid.SetTgl3(tglInap.getText()), JamMasuk.getText());
                        billing.rawatinap.setpetugas();
                        billing.rawatinap.tampilDr();
                        billing.rawatinap.setVisible(true);
                    }
                } else {
                    JOptionPane.showMessageDialog(null, "Maaf, Silahkan anda pilih dulu pasien...!!!");
                    tbKamIn.requestFocus();
                }
            } catch (Exception ex) {
                System.out.println("Notifikasi : " + ex);
            } finally {
                if (rs2 != null) {
                    rs2.close();
                }
                if (psanak != null) {
                    psanak.close();
                }
            }
        } catch (Exception e) {
            System.out.println(e);
        }
    } else {
        if (Sequel.cariRegistrasi(tbKamIn.getValueAt(tbKamIn.getSelectedRow(), 0).toString()) > 0) {
            JOptionPane.showMessageDialog(rootPane, "Data billing sudah terverifikasi, data tidak boleh dihapus/diubah. Silahkan hubungi bagian kasir/keuangan ..!!");
        } else {
            var.setform("DlgKamarInap");
            billing.rawatinap.setSize(internalFrame1.getWidth(), internalFrame1.getHeight());
            billing.rawatinap.setLocationRelativeTo(internalFrame1);
            billing.rawatinap.isCek();
            billing.rawatinap.setNoRm(norawat.getText(), DTPCari1.getDate(), DTPCari2.getDate());
            billing.rawatinap.setHidup((String) cmbStatus.getSelectedItem());
            billing.rawatinap.dataRawat(ruangrawat.getText(), Valid.SetTgl3(tglInap.getText()), JamMasuk.getText());
            billing.rawatinap.setpetugas();
            billing.rawatinap.tampilDr();
            billing.rawatinap.setVisible(true);
        }
    }
}//GEN-LAST:event_MnRawatInapActionPerformed

private void MnResepPulangActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_MnResepPulangActionPerformed
    if (tabMode.getRowCount() == 0) {
        JOptionPane.showMessageDialog(null, "Maaf, tabel masih kosong...!!!!");
        TCari.requestFocus();
    } else if (TPasien.getText().trim().equals("")) {
        try {
            psanak = koneksi.prepareStatement(
                    "select pasien.no_rkm_medis,pasien.nm_pasien,ranap_gabung.no_rawat2,concat(reg_periksa.umurdaftar,' ',reg_periksa.sttsumur)as umur,pasien.no_peserta, "
                    + "concat(pasien.alamatpj,', ',pasien.kelurahanpj,', ',pasien.kecamatanpj,', ',pasien.kabupatenpj) as alamat "
                    + "from reg_periksa inner join pasien inner join ranap_gabung on "
                    + "pasien.no_rkm_medis=reg_periksa.no_rkm_medis and ranap_gabung.no_rawat2=reg_periksa.no_rawat where ranap_gabung.no_rawat=?");
            try {
                psanak.setString(1, tbKamIn.getValueAt(tbKamIn.getSelectedRow(), 0).toString());
                rs2 = psanak.executeQuery();
                if (rs2.next()) {
                    if (Sequel.cariRegistrasi(rs2.getString("no_rawat2")) > 0) {
                        JOptionPane.showMessageDialog(rootPane, "Data billing sudah terverifikasi, data tidak boleh dihapus/diubah. Silahkan hubungi bagian kasir/keuangan ..!!");
                    } else {
                        var.setform("DlgKamarInap");
                        billing.reseppulang.isCek();
                        billing.reseppulang.setNoRm(rs2.getString("no_rawat2"), DTPCari1.getDate(), DTPCari2.getDate());
                        billing.reseppulang.tampil();
                        billing.reseppulang.setSize(internalFrame1.getWidth(), internalFrame1.getHeight());
                        billing.reseppulang.setLocationRelativeTo(internalFrame1);
                        billing.reseppulang.setVisible(true);
                    }

                } else {
                    JOptionPane.showMessageDialog(null, "Maaf, Silahkan anda pilih dulu pasien...!!!");
                    tbKamIn.requestFocus();
                }
            } catch (Exception ex) {
                System.out.println("Notifikasi : " + ex);
            } finally {
                if (rs2 != null) {
                    rs2.close();
                }
                if (psanak != null) {
                    psanak.close();
                }
            }
        } catch (Exception e) {
            System.out.println(e);
        }
    } else {
        if (Sequel.cariRegistrasi(tbKamIn.getValueAt(tbKamIn.getSelectedRow(), 0).toString()) > 0) {
            JOptionPane.showMessageDialog(rootPane, "Data billing sudah terverifikasi, data tidak boleh dihapus/diubah. Silahkan hubungi bagian kasir/keuangan ..!!");
        } else {
            var.setform("DlgKamarInap");
            billing.reseppulang.isCek();
            billing.reseppulang.setNoRm(norawat.getText(), DTPCari1.getDate(), DTPCari2.getDate());
            billing.reseppulang.tampil();
            billing.reseppulang.setSize(internalFrame1.getWidth(), internalFrame1.getHeight());
            billing.reseppulang.setLocationRelativeTo(internalFrame1);
            billing.reseppulang.setVisible(true);
        }

    }
}//GEN-LAST:event_MnResepPulangActionPerformed

private void MnRujukActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_MnRujukActionPerformed
    if (tabMode.getRowCount() == 0) {
        JOptionPane.showMessageDialog(null, "Maaf, tabel masih kosong...!!!!");
        TCari.requestFocus();
    } else if (TPasien.getText().trim().equals("")) {
        try {
            psanak = koneksi.prepareStatement(
                    "select pasien.no_rkm_medis,pasien.nm_pasien,ranap_gabung.no_rawat2,concat(reg_periksa.umurdaftar,' ',reg_periksa.sttsumur)as umur,pasien.no_peserta, "
                    + "concat(pasien.alamatpj,', ',pasien.kelurahanpj,', ',pasien.kecamatanpj,', ',pasien.kabupatenpj) as alamat "
                    + "from reg_periksa inner join pasien inner join ranap_gabung on "
                    + "pasien.no_rkm_medis=reg_periksa.no_rkm_medis and ranap_gabung.no_rawat2=reg_periksa.no_rawat where ranap_gabung.no_rawat=?");
            try {
                psanak.setString(1, tbKamIn.getValueAt(tbKamIn.getSelectedRow(), 0).toString());
                rs2 = psanak.executeQuery();
                if (rs2.next()) {
                    DlgRujuk dlgrjk = new DlgRujuk(null, false);
                    dlgrjk.setSize(internalFrame1.getWidth(), internalFrame1.getHeight());
                    dlgrjk.setLocationRelativeTo(internalFrame1);
                    dlgrjk.emptTeks();
                    dlgrjk.isCek();
                    dlgrjk.setNoRm(rs2.getString("no_rawat2"), DTPCari1.getDate(), DTPCari2.getDate());
                    dlgrjk.tampil();
                    dlgrjk.setVisible(true);
                    dlgrjk.btnFaskes.requestFocus();
                } else {
                    JOptionPane.showMessageDialog(null, "Maaf, Silahkan anda pilih dulu pasien...!!!");
                    tbKamIn.requestFocus();
                }
            } catch (Exception ex) {
                System.out.println("Notifikasi : " + ex);
            } finally {
                if (rs2 != null) {
                    rs2.close();
                }
                if (psanak != null) {
                    psanak.close();
                }
            }
        } catch (Exception e) {
            System.out.println(e);
        }
    } else {
        DlgRujuk dlgrjk = new DlgRujuk(null, false);
        dlgrjk.setSize(internalFrame1.getWidth(), internalFrame1.getHeight());
        dlgrjk.setLocationRelativeTo(internalFrame1);
        dlgrjk.emptTeks();
        dlgrjk.isCek();
        dlgrjk.setNoRm(norawat.getText(), DTPCari1.getDate(), DTPCari2.getDate());
        dlgrjk.tampil();
        dlgrjk.setVisible(true);
        dlgrjk.btnFaskes.requestFocus();
    }
}//GEN-LAST:event_MnRujukActionPerformed

private void MnPemberianObatActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_MnPemberianObatActionPerformed
    if (tabMode.getRowCount() == 0) {
        JOptionPane.showMessageDialog(null, "Maaf, tabel masih kosong...!!!!");
        TCari.requestFocus();
    } else if (TPasien.getText().trim().equals("")) {
        try {
            psanak = koneksi.prepareStatement(
                    "select pasien.no_rkm_medis,pasien.nm_pasien,ranap_gabung.no_rawat2,concat(reg_periksa.umurdaftar,' ',reg_periksa.sttsumur)as umur,pasien.no_peserta, "
                    + "concat(pasien.alamatpj,', ',pasien.kelurahanpj,', ',pasien.kecamatanpj,', ',pasien.kabupatenpj) as alamat "
                    + "from reg_periksa inner join pasien inner join ranap_gabung on "
                    + "pasien.no_rkm_medis=reg_periksa.no_rkm_medis and ranap_gabung.no_rawat2=reg_periksa.no_rawat where ranap_gabung.no_rawat=?");
            try {
                psanak.setString(1, tbKamIn.getValueAt(tbKamIn.getSelectedRow(), 0).toString());
                rs2 = psanak.executeQuery();
                if (rs2.next()) {
                    var.setform("DlgKamarInap");
                    bangsal = Sequel.cariIsi("select kd_depo from set_depo_ranap where kd_bangsal=?", Sequel.cariIsi("select kd_bangsal from kamar where kd_kamar=?", kdkamar.getText()));
                    if (bangsal.equals("")) {
                        if (Sequel.cariIsi("select asal_stok from set_lokasi").equals("Gunakan Stok Bangsal")) {
                            var.setkdbangsal(Sequel.cariIsi("select kd_bangsal from kamar where kd_kamar=?", kdkamar.getText()));
                        } else {
                            var.setkdbangsal(Sequel.cariIsi("select kd_bangsal from set_lokasi"));
                        }
                    } else {
                        var.setkdbangsal(bangsal);
                    }
                    if (Sequel.cariRegistrasi(rs2.getString("no_rawat2")) > 0) {
                        if (var.getnamauser().equals("Admin Utama") || var.getkode().equals("0215")) {
                            billing.beriobat.setSize(internalFrame1.getWidth(), internalFrame1.getHeight());
                            billing.beriobat.setLocationRelativeTo(internalFrame1);
                            billing.beriobat.setNoRm(rs2.getString("no_rawat2"), DTPCari1.getDate(), DTPCari2.getDate(), "ranap");
                            billing.beriobat.isCek();
                            billing.beriobat.tampilPO();
                            billing.beriobat.setVisible(true);
                        } else {
                            JOptionPane.showMessageDialog(rootPane, "Data billing sudah terverifikasi, data tidak boleh dihapus/diubah. Silahkan hubungi bagian kasir/keuangan ..!!");
                        }
                    } else {
                        billing.beriobat.setSize(internalFrame1.getWidth(), internalFrame1.getHeight());
                        billing.beriobat.setLocationRelativeTo(internalFrame1);
                        billing.beriobat.setNoRm(rs2.getString("no_rawat2"), DTPCari1.getDate(), DTPCari2.getDate(), "ranap");
                        billing.beriobat.isCek();
                        billing.beriobat.tampilPO();
                        billing.beriobat.setVisible(true);
                    }
                } else {
                    JOptionPane.showMessageDialog(null, "Maaf, Silahkan anda pilih dulu pasien...!!!");
                    tbKamIn.requestFocus();
                }
            } catch (Exception ex) {
                System.out.println("Notifikasi : " + ex);
            } finally {
                if (rs2 != null) {
                    rs2.close();
                }
                if (psanak != null) {
                    psanak.close();
                }
            }
        } catch (Exception e) {
            System.out.println(e);
        }
    } else {
        var.setform("DlgKamarInap");
        bangsal = Sequel.cariIsi("select kd_depo from set_depo_ranap where kd_bangsal=?", Sequel.cariIsi("select kd_bangsal from kamar where kd_kamar=?", kdkamar.getText()));
        if (bangsal.equals("")) {
            if (Sequel.cariIsi("select asal_stok from set_lokasi").equals("Gunakan Stok Bangsal")) {
                var.setkdbangsal(Sequel.cariIsi("select kd_bangsal from kamar where kd_kamar=?", kdkamar.getText()));
            } else {
                var.setkdbangsal(Sequel.cariIsi("select kd_bangsal from set_lokasi"));
            }
        } else {
            var.setkdbangsal(bangsal);
        }
        if (Sequel.cariRegistrasi(tbKamIn.getValueAt(tbKamIn.getSelectedRow(), 0).toString()) > 0) {
            if (var.getnamauser().equals("Admin Utama") || var.getkode().equals("0215")) {
                billing.beriobat.setSize(internalFrame1.getWidth(), internalFrame1.getHeight());
                billing.beriobat.setLocationRelativeTo(internalFrame1);
                billing.beriobat.setNoRm(norawat.getText(), DTPCari1.getDate(), DTPCari2.getDate(), "ranap");
                billing.beriobat.isCek();
                billing.beriobat.tampilPO();
                billing.beriobat.setVisible(true);
            } else {
                JOptionPane.showMessageDialog(rootPane, "Data billing sudah terverifikasi, data tidak boleh dihapus/diubah. Silahkan hubungi bagian kasir/keuangan ..!!");
            }
        } else {
            billing.beriobat.setSize(internalFrame1.getWidth(), internalFrame1.getHeight());
            billing.beriobat.setLocationRelativeTo(internalFrame1);
            billing.beriobat.setNoRm(norawat.getText(), DTPCari1.getDate(), DTPCari2.getDate(), "ranap");
            billing.beriobat.isCek();
            billing.beriobat.tampilPO();
            billing.beriobat.setVisible(true);
        }

    }
}//GEN-LAST:event_MnPemberianObatActionPerformed

private void MnBillingActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_MnBillingActionPerformed
    if (tabMode.getRowCount() == 0) {
        JOptionPane.showMessageDialog(null, "Maaf, tabel masih kosong...!!!!");
        TCari.requestFocus();
    } else if (TPasien.getText().trim().equals("")) {
        JOptionPane.showMessageDialog(null, "Maaf, Silahkan anda pilih dulu pasien...!!!");
        tbKamIn.requestFocus();
    } else {
        try {
            pscaripiutang = koneksi.prepareStatement("select tgl_piutang from piutang_pasien where no_rkm_medis=? and status='Belum Lunas' order by tgl_piutang asc limit 1");
            try {
                pscaripiutang.setString(1, TNoRM.getText());
                rs = pscaripiutang.executeQuery();
                if (rs.next()) {
                    i = JOptionPane.showConfirmDialog(null, "Masih ada tunggakan pembayaran, apa mau bayar sekarang ?", "Konfirmasi", JOptionPane.YES_NO_OPTION);
                    if (i == JOptionPane.YES_OPTION) {
                        DlgLhtPiutang piutang = new DlgLhtPiutang(null, false);
                        piutang.setNoRm(TNoRM.getText(), rs.getDate(1));
                        piutang.tampil();
                        piutang.setSize(internalFrame1.getWidth() - 40, internalFrame1.getHeight() - 40);
                        piutang.setLocationRelativeTo(internalFrame1);
                        piutang.setVisible(true);
                    } else {
                        bangsal = Sequel.cariIsi("select kd_depo from set_depo_ranap where kd_bangsal=?", Sequel.cariIsi("select kd_bangsal from kamar "
                                + "where kd_kamar=?", kdkamar.getText()));
                        if (bangsal.equals("")) {
                            if (Sequel.cariIsi("select asal_stok from set_lokasi").equals("Gunakan Stok Bangsal")) {
                                var.setkdbangsal(Sequel.cariIsi("select kd_bangsal from kamar where kd_kamar=?", kdkamar.getText()));
                            } else {
                                var.setkdbangsal(Sequel.cariIsi("select kd_bangsal from set_lokasi"));
                            }
                        } else {
                            var.setkdbangsal(bangsal);
                        }

                        billing.TNoRw.setText(norawat.getText());
                        billing.isCek(kdkamar.getText());
                        billing.cekLR.setSelected(true);
                        billing.isRawat();
                        billing.setSize(internalFrame1.getWidth(), internalFrame1.getHeight());
                        billing.setLocationRelativeTo(internalFrame1);
                        billing.setVisible(true);
                    }
                } else {
                    bangsal = Sequel.cariIsi("select kd_depo from set_depo_ranap where kd_bangsal=?", Sequel.cariIsi("select kd_bangsal from kamar where kd_kamar=?", kdkamar.getText()));
                    if (bangsal.equals("")) {
                        if (Sequel.cariIsi("select asal_stok from set_lokasi").equals("Gunakan Stok Bangsal")) {
                            var.setkdbangsal(Sequel.cariIsi("select kd_bangsal from kamar where kd_kamar=?", kdkamar.getText()));
                        } else {
                            var.setkdbangsal(Sequel.cariIsi("select kd_bangsal from set_lokasi"));
                        }
                    } else {
                        var.setkdbangsal(bangsal);
                    }

                    billing.TNoRw.setText(norawat.getText());
                    billing.isCek(kdkamar.getText());
                    billing.cekLR.setSelected(true);
                    billing.isRawat();
                    billing.setSize(internalFrame1.getWidth(), internalFrame1.getHeight());
                    billing.setLocationRelativeTo(internalFrame1);
                    billing.setVisible(true);
                }
            } catch (Exception e) {
                System.out.println("Notifikasi : " + e);
            } finally {
                if (rs != null) {
                    rs.close();
                }
                if (pscaripiutang != null) {
                    pscaripiutang.close();
                }
            }
        } catch (Exception e) {
            System.out.println(e);
        }
    }
}//GEN-LAST:event_MnBillingActionPerformed

private void MnDietActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_MnDietActionPerformed
    if (tabMode.getRowCount() == 0) {
        JOptionPane.showMessageDialog(null, "Maaf, tabel masih kosong...!!!!");
        TCari.requestFocus();
    } else if (norawatCopy.getText().equals("")) {
        JOptionPane.showMessageDialog(null, "Pilih salah satu pasiennya dulu pada tabel..!!!!");
    } else {
        DlgPemberianDiet rawatinap = new DlgPemberianDiet(null, false);
        rawatinap.setSize(internalFrame1.getWidth(), internalFrame1.getHeight());
        rawatinap.setLocationRelativeTo(internalFrame1);
        rawatinap.emptTeks();
        rawatinap.cekWaktu.setSelected(true);
        rawatinap.TCari.setText("");        
        rawatinap.setNoRm(norawat.getText(), DTPCari1.getDate(), DTPCari2.getDate());
        rawatinap.isCek();
        rawatinap.tabDataKlik();
        rawatinap.setVisible(true);
    }
}//GEN-LAST:event_MnDietActionPerformed

private void DTPCari3ItemStateChanged(java.awt.event.ItemEvent evt) {//GEN-FIRST:event_DTPCari3ItemStateChanged
    R3.setSelected(true);
}//GEN-LAST:event_DTPCari3ItemStateChanged

private void DTPCari3KeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_DTPCari3KeyPressed
// TODO add your handling code here:
}//GEN-LAST:event_DTPCari3KeyPressed

private void cmbStatusKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_cmbStatusKeyPressed

}//GEN-LAST:event_cmbStatusKeyPressed

private void MnPeriksaLabActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_MnPeriksaLabActionPerformed
    cekdokter = "";
    diagnosa_ok = "";
    diagnosa_cek = 0;
    diagnosa_cek = Sequel.cariInteger("select count(1) cek from kamar_inap where no_rawat='" + norawat.getText() + "'");
    cekdokter = Sequel.cariIsi("select kd_dokter from dpjp_ranap where no_rawat='" + norawat.getText() + "'");

    if (diagnosa_cek == 0) {
        diagnosa_ok = "-";
    } else {
        diagnosa_ok = Sequel.cariIsi("select diagnosa_awal from kamar_inap where no_rawat='" + norawat.getText() + "'");
    }

    if (tabMode.getRowCount() == 0) {
        JOptionPane.showMessageDialog(null, "Maaf, tabel masih kosong...!!!!");
        TCari.requestFocus();
    } else if (TPasien.getText().trim().equals("")) {
        try {
            psanak = koneksi.prepareStatement(
                    "select pasien.no_rkm_medis,pasien.nm_pasien,ranap_gabung.no_rawat2,concat(reg_periksa.umurdaftar,' ',reg_periksa.sttsumur)as umur,pasien.no_peserta, "
                    + "concat(pasien.alamatpj,', ',pasien.kelurahanpj,', ',pasien.kecamatanpj,', ',pasien.kabupatenpj) as alamat "
                    + "from reg_periksa inner join pasien inner join ranap_gabung on "
                    + "pasien.no_rkm_medis=reg_periksa.no_rkm_medis and ranap_gabung.no_rawat2=reg_periksa.no_rawat where ranap_gabung.no_rawat=?");
            try {
                psanak.setString(1, tbKamIn.getValueAt(tbKamIn.getSelectedRow(), 0).toString());
                rs2 = psanak.executeQuery();
                if (rs2.next()) {
                    if (Sequel.cariRegistrasi(rs2.getString("no_rawat2")) > 0) {
                        JOptionPane.showMessageDialog(rootPane, "Data billing sudah terverifikasi, data tidak boleh dihapus/diubah. Silahkan hubungi bagian kasir/keuangan ..!!");
                    } else {
                        var.setform("DlgKamarInap");
                        billing.periksalab.setSize(internalFrame1.getWidth(), internalFrame1.getHeight());
                        billing.periksalab.setLocationRelativeTo(internalFrame1);
                        billing.periksalab.emptTeks();
                        billing.periksalab.KodePerujuk.setText(cekdokter);
                        billing.periksalab.setNoRm(rs2.getString("no_rawat2"), "Ranap", "-", diagnosa_ok, ruangrawat.getText());
                        billing.periksalab.tampiltarif();
                        billing.periksalab.tampil();
                        billing.periksalab.tampilMintaPeriksa();
                        billing.periksalab.isCek();
                        billing.periksalab.setVisible(true);
                        billing.periksalab.fokus();
                    }

                } else {
                    JOptionPane.showMessageDialog(null, "Maaf, Silahkan anda pilih dulu pasien...!!!");
                    tbKamIn.requestFocus();
                }
            } catch (Exception ex) {
                System.out.println("Notifikasi : " + ex);
            } finally {
                if (rs2 != null) {
                    rs2.close();
                }
                if (psanak != null) {
                    psanak.close();
                }
            }
        } catch (Exception e) {
            System.out.println(e);
        }
    } else {
        if (Sequel.cariRegistrasi(tbKamIn.getValueAt(tbKamIn.getSelectedRow(), 0).toString()) > 0) {
            JOptionPane.showMessageDialog(rootPane, "Data billing sudah terverifikasi, data tidak boleh dihapus/diubah. Silahkan hubungi bagian kasir/keuangan ..!!");
        } else {
            var.setform("DlgKamarInap");
            billing.periksalab.setSize(internalFrame1.getWidth(), internalFrame1.getHeight());
            billing.periksalab.setLocationRelativeTo(internalFrame1);
            billing.periksalab.emptTeks();
            billing.periksalab.KodePerujuk.setText(cekdokter);
            billing.periksalab.setNoRm(norawat.getText(), "Ranap", "-", diagnosa_ok, ruangrawat.getText());
            billing.periksalab.tampiltarif();
            billing.periksalab.tampil();
            billing.periksalab.tampilMintaPeriksa();
            billing.periksalab.isCek();
            billing.periksalab.setVisible(true);
            billing.periksalab.fokus();
        }
    }
}//GEN-LAST:event_MnPeriksaLabActionPerformed

private void JamMasukKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_JamMasukKeyPressed
// TODO add your handling code here:
}//GEN-LAST:event_JamMasukKeyPressed

private void MnOperasiActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_MnOperasiActionPerformed
    if (tabMode.getRowCount() == 0) {
        JOptionPane.showMessageDialog(null, "Maaf, tabel masih kosong...!!!!");
        TCari.requestFocus();
    } else if (TPasien.getText().trim().equals("")) {
        try {
            psanak = koneksi.prepareStatement(
                    "select pasien.no_rkm_medis,pasien.nm_pasien,ranap_gabung.no_rawat2,concat(reg_periksa.umurdaftar,' ',reg_periksa.sttsumur)as umur,pasien.no_peserta, "
                    + "concat(pasien.alamatpj,', ',pasien.kelurahanpj,', ',pasien.kecamatanpj,', ',pasien.kabupatenpj) as alamat "
                    + "from reg_periksa inner join pasien inner join ranap_gabung on "
                    + "pasien.no_rkm_medis=reg_periksa.no_rkm_medis and ranap_gabung.no_rawat2=reg_periksa.no_rawat where ranap_gabung.no_rawat=?");
            try {
                psanak.setString(1, tbKamIn.getValueAt(tbKamIn.getSelectedRow(), 0).toString());
                rs2 = psanak.executeQuery();
                if (rs2.next()) {
                    if (Sequel.cariRegistrasi(rs2.getString("no_rawat2")) > 0) {
                        JOptionPane.showMessageDialog(rootPane, "Data billing sudah terverifikasi, data tidak boleh dihapus/diubah. Silahkan hubungi bagian kasir/keuangan ..!!");
                    } else {
                        DlgTagihanOperasi dlgro = new DlgTagihanOperasi(null, false);
                        dlgro.setSize(internalFrame1.getWidth(), internalFrame1.getHeight());
                        dlgro.setLocationRelativeTo(internalFrame1);
                        dlgro.setNoRm(rs2.getString("no_rawat2"), rs2.getString("no_rkm_medis") + ", " + rs2.getString("nm_pasien"), "Ranap");
                        dlgro.setVisible(true);
                        dlgro.fokus();
                    }

                } else {
                    JOptionPane.showMessageDialog(null, "Maaf, Silahkan anda pilih dulu pasien...!!!");
                    tbKamIn.requestFocus();
                }
            } catch (Exception ex) {
                System.out.println("Notifikasi : " + ex);
            } finally {
                if (rs2 != null) {
                    rs2.close();
                }
                if (psanak != null) {
                    psanak.close();
                }
            }
        } catch (Exception e) {
            System.out.println(e);
        }
    } else {
        if (Sequel.cariRegistrasi(tbKamIn.getValueAt(tbKamIn.getSelectedRow(), 0).toString()) > 0) {
            JOptionPane.showMessageDialog(rootPane, "Data billing sudah terverifikasi, data tidak boleh dihapus/diubah. Silahkan hubungi bagian kasir/keuangan ..!!");
        } else {
            DlgTagihanOperasi dlgro = new DlgTagihanOperasi(null, false);
            dlgro.setSize(internalFrame1.getWidth(), internalFrame1.getHeight());
            dlgro.setLocationRelativeTo(internalFrame1);
            dlgro.setNoRm(norawat.getText(), TNoRM.getText() + ", " + TPasien.getText(), "Ranap");
            dlgro.setVisible(true);
            dlgro.fokus();
        }
    }
}//GEN-LAST:event_MnOperasiActionPerformed

private void MnHapusTagihanOperasiActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_MnHapusTagihanOperasiActionPerformed
    if (tabMode.getRowCount() == 0) {
        JOptionPane.showMessageDialog(null, "Maaf, tabel masih kosong...!!!!");
        TCari.requestFocus();
    } else if (TPasien.getText().trim().equals("")) {
        try {
            psanak = koneksi.prepareStatement(
                    "select pasien.no_rkm_medis,pasien.nm_pasien,ranap_gabung.no_rawat2,concat(reg_periksa.umurdaftar,' ',reg_periksa.sttsumur)as umur,pasien.no_peserta, "
                    + "concat(pasien.alamatpj,', ',pasien.kelurahanpj,', ',pasien.kecamatanpj,', ',pasien.kabupatenpj) as alamat "
                    + "from reg_periksa inner join pasien inner join ranap_gabung on "
                    + "pasien.no_rkm_medis=reg_periksa.no_rkm_medis and ranap_gabung.no_rawat2=reg_periksa.no_rawat where ranap_gabung.no_rawat=?");
            try {
                psanak.setString(1, tbKamIn.getValueAt(tbKamIn.getSelectedRow(), 0).toString());
                rs2 = psanak.executeQuery();
                if (rs2.next()) {
                    Sequel.queryu("delete from operasi where no_rawat='" + rs2.getString("no_rawat2") + "'");
                    Sequel.queryu("delete from beri_obat_operasi where no_rawat='" + rs2.getString("no_rawat2") + "'");
                } else {
                    JOptionPane.showMessageDialog(null, "Maaf, Silahkan anda pilih dulu pasien...!!!");
                    tbKamIn.requestFocus();
                }
            } catch (Exception ex) {
                System.out.println("Notifikasi : " + ex);
            } finally {
                if (rs2 != null) {
                    rs2.close();
                }
                if (psanak != null) {
                    psanak.close();
                }
            }
        } catch (Exception e) {
            System.out.println(e);
        }
    } else {
        Sequel.queryu("delete from operasi where no_rawat='" + tbKamIn.getValueAt(tbKamIn.getSelectedRow(), 0) + "'");
        Sequel.queryu("delete from beri_obat_operasi where no_rawat='" + tbKamIn.getValueAt(tbKamIn.getSelectedRow(), 0) + "'");
    }
}//GEN-LAST:event_MnHapusTagihanOperasiActionPerformed

private void MnHapusObatOperasiActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_MnHapusObatOperasiActionPerformed
    if (tabMode.getRowCount() == 0) {
        JOptionPane.showMessageDialog(null, "Maaf, tabel masih kosong...!!!!");
        TCari.requestFocus();
    } else if (TPasien.getText().trim().equals("")) {
        try {
            psanak = koneksi.prepareStatement(
                    "select pasien.no_rkm_medis,pasien.nm_pasien,ranap_gabung.no_rawat2,concat(reg_periksa.umurdaftar,' ',reg_periksa.sttsumur)as umur,pasien.no_peserta, "
                    + "concat(pasien.alamatpj,', ',pasien.kelurahanpj,', ',pasien.kecamatanpj,', ',pasien.kabupatenpj) as alamat "
                    + "from reg_periksa inner join pasien inner join ranap_gabung on "
                    + "pasien.no_rkm_medis=reg_periksa.no_rkm_medis and ranap_gabung.no_rawat2=reg_periksa.no_rawat where ranap_gabung.no_rawat=?");
            try {
                psanak.setString(1, tbKamIn.getValueAt(tbKamIn.getSelectedRow(), 0).toString());
                rs2 = psanak.executeQuery();
                if (rs2.next()) {
                    Sequel.queryu("delete from beri_obat_operasi where no_rawat='" + rs2.getString("no_rawat2") + "'");
                } else {
                    JOptionPane.showMessageDialog(null, "Maaf, Silahkan anda pilih dulu pasien...!!!");
                    tbKamIn.requestFocus();
                }
            } catch (Exception ex) {
                System.out.println("Notifikasi : " + ex);
            } finally {
                if (rs2 != null) {
                    rs2.close();
                }
                if (psanak != null) {
                    psanak.close();
                }
            }
        } catch (Exception e) {
            System.out.println(e);
        }
    } else {
        Sequel.queryu("delete from beri_obat_operasi where no_rawat='" + tbKamIn.getValueAt(tbKamIn.getSelectedRow(), 0) + "'");
    }
}//GEN-LAST:event_MnHapusObatOperasiActionPerformed

private void MnRujukMasukActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_MnRujukMasukActionPerformed
    if (tabMode.getRowCount() == 0) {
        JOptionPane.showMessageDialog(null, "Maaf, tabel masih kosong...!!!!");
        TCari.requestFocus();
    } else if (TPasien.getText().trim().equals("")) {
        try {
            psanak = koneksi.prepareStatement(
                    "select pasien.no_rkm_medis,pasien.nm_pasien,ranap_gabung.no_rawat2,concat(reg_periksa.umurdaftar,' ',reg_periksa.sttsumur)as umur,pasien.no_peserta, "
                    + "concat(pasien.alamatpj,', ',pasien.kelurahanpj,', ',pasien.kecamatanpj,', ',pasien.kabupatenpj) as alamat "
                    + "from reg_periksa inner join pasien inner join ranap_gabung on "
                    + "pasien.no_rkm_medis=reg_periksa.no_rkm_medis and ranap_gabung.no_rawat2=reg_periksa.no_rawat where ranap_gabung.no_rawat=?");

            try {
                psanak.setString(1, tbKamIn.getValueAt(tbKamIn.getSelectedRow(), 0).toString());
                rs2 = psanak.executeQuery();
                if (rs2.next()) {
                    var.setform("DlgKamarInap");
                    rujukmasuk.setSize(internalFrame1.getWidth(), internalFrame1.getHeight());
                    rujukmasuk.setLocationRelativeTo(internalFrame1);
                    rujukmasuk.emptTeks();
                    rujukmasuk.isCek();
                    rujukmasuk.setNoRm(rs2.getString("no_rawat2"), DTPCari1.getDate(), DTPCari2.getDate());
                    rujukmasuk.tampil();
                    rujukmasuk.setVisible(true);
                } else {
                    JOptionPane.showMessageDialog(null, "Maaf, Silahkan anda pilih dulu pasien...!!!");
                    tbKamIn.requestFocus();
                }
            } catch (Exception ex) {
                System.out.println("Notifikasi : " + ex);
            } finally {
                if (rs2 != null) {
                    rs2.close();
                }
                if (psanak != null) {
                    psanak.close();
                }
            }
        } catch (Exception e) {
            System.out.println(e);
        }
    } else {
        var.setform("DlgKamarInap");
        rujukmasuk.setSize(internalFrame1.getWidth(), internalFrame1.getHeight());
        rujukmasuk.setLocationRelativeTo(internalFrame1);
        rujukmasuk.emptTeks();
        rujukmasuk.isCek();
        rujukmasuk.setNoRm(norawat.getText(), DTPCari1.getDate(), DTPCari2.getDate());
        rujukmasuk.tampil();
        rujukmasuk.setVisible(true);
    }
}//GEN-LAST:event_MnRujukMasukActionPerformed

    private void btnPindahActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnPindahActionPerformed
        cekUsia = 0;
        sttsumur = "";
        cekRMbayi = "";
        cekUsia = Sequel.cariInteger("select umurdaftar from reg_periksa where no_rawat='" + norawat.getText() + "'");
        sttsumur = Sequel.cariIsi("select sttsumur from reg_periksa where no_rawat='" + norawat.getText() + "'");
        cekRMbayi = Sequel.cariIsi("SELECT no_rkm_medis FROM pasien_bayi WHERE no_rkm_medis='" + TNoRM.getText() + "' ");

        if (TOut.getText().trim().length() > 0) {
            JOptionPane.showMessageDialog(null, "Maaf, pasien ini sudah pulang pada tanggal " + TOut.getText() + " ...!!!");
            emptTeks();
            tbKamIn.requestFocus();
        } else if ((TOut.getText().length() == 0) && (TPasien.getText().length() > 0)) {

            //cek data pasien bayi dulu---------------------
            if (cekRMbayi.equals("")) {
                if (cekUsia <= 28 && sttsumur.equals("Hr") && (kdkamar.getText().substring(0, 2).equals("BY") || kdkamar.getText().equals("ISO/BYVIP/101"))) {
                    x = JOptionPane.showConfirmDialog(null, "Pasien bayi ini belum bisa dipindahkan ruang rawatnya, silakan isi & "
                            + "lengkapi dulu data bayinya...!!", "Konfirmasi", JOptionPane.YES_NO_OPTION);
                    if (x == JOptionPane.YES_OPTION) {
                        DlgIKBBayi lahir = new DlgIKBBayi(null, false);
                        lahir.tampil();
                        lahir.isCek();
                        lahir.emptTeks();
                        lahir.setSize(internalFrame1.getWidth(), internalFrame1.getHeight());
                        lahir.setLocationRelativeTo(internalFrame1);
                        lahir.setVisible(true);
                        lahir.bukaDataBayi(TNoRM.getText());
                        lahir.autoSKL();

                        emptTeks();
                        tampil();
                    } else {
                        emptTeks();
                        tampil();
                    }
                }
            }

            //jika data pasien bayi sudah terpenuhi atau data bukan termasuk pasien bayi, next---------->>>>>
            if (tabMode.getRowCount() == 0) {
                JOptionPane.showMessageDialog(null, "Maaf, data kamar inap pasien sudah habis...!!!!");
                BtnIn.requestFocus();
            } else if (TPasien.getText().trim().equals("")) {
                tbKamIn.requestFocus();
            } else {
                kdkamarpindah.setText("");
                TKdBngsalpindah.setText("");
                TBangsalpindah.setText("");
                TJmlHaripindah.setText("1");
                TTarifpindah.setText("0");
                ttlbiayapindah.setText("0");
                date = new Date();
                now = dateFormat.format(date);
                CmbTahunpindah.setSelectedItem(now.substring(0, 4));
                CmbBlnpindah.setSelectedItem(now.substring(5, 7));
                CmbTglpindah.setSelectedItem(now.substring(8, 10));
                cmbJampindah.setSelectedItem(now.substring(11, 13));
                cmbMntpindah.setSelectedItem(now.substring(14, 16));
                cmbDtkpindah.setSelectedItem(now.substring(17, 19));
                norawat.requestFocus();
                WindowPindahKamar.setLocationRelativeTo(internalFrame1);
                WindowPindahKamar.setVisible(true);
                lama = Sequel.cariIsiAngka("select lamajam from set_jam_minimal");
                i = 2;
                isKmr();
                isjml();
            }
        }
    }//GEN-LAST:event_btnPindahActionPerformed

    private void btnPindahKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_btnPindahKeyPressed
        if (evt.getKeyCode() == KeyEvent.VK_SPACE) {
            btnPindahActionPerformed(null);
        } else {
            Valid.pindah(evt, BtnOut, BtnCari);
        }
    }//GEN-LAST:event_btnPindahKeyPressed

    private void kdkamarpindahKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_kdkamarpindahKeyPressed
        if (evt.getKeyCode() == KeyEvent.VK_PAGE_DOWN) {
            i = 2;
            isKmr();
        } else if (evt.getKeyCode() == KeyEvent.VK_ENTER) {
            CmbTahun.requestFocus();
            i = 2;
            isKmr();
        } else if (evt.getKeyCode() == KeyEvent.VK_PAGE_UP) {
            BtnSimpanpindah.requestFocus();
            i = 2;
            isKmr();
        } else if (evt.getKeyCode() == KeyEvent.VK_UP) {
            btnKamar2ActionPerformed(null);
        }
    }//GEN-LAST:event_kdkamarpindahKeyPressed

    private void btnKamar2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnKamar2ActionPerformed
        var.setform("DlgKamarInap");
        i = 2;
        kamar.load();
        kamar.isCek();
        kamar.emptTeks();
        kamar.tampil();
        kamar.setSize(internalFrame1.getWidth() - 40, internalFrame1.getHeight() - 40);
        kamar.setLocationRelativeTo(internalFrame1);
        kamar.setVisible(true);
    }//GEN-LAST:event_btnKamar2ActionPerformed

    private void TBangsalpindahKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_TBangsalpindahKeyPressed
        // TODO add your handling code here:
    }//GEN-LAST:event_TBangsalpindahKeyPressed

    private void BtnCloseInpindahActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_BtnCloseInpindahActionPerformed
        TOut.setText("");
        WindowPindahKamar.dispose();
    }//GEN-LAST:event_BtnCloseInpindahActionPerformed

    private void BtnCloseInpindahKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_BtnCloseInpindahKeyPressed
        // TODO add your handling code here:
    }//GEN-LAST:event_BtnCloseInpindahKeyPressed

    private void BtnSimpanpindahActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_BtnSimpanpindahActionPerformed
        tgm = CmbTahunpindah.getSelectedItem() + "-" + CmbBlnpindah.getSelectedItem() + "-" + CmbTglpindah.getSelectedItem();
        date = new Date();
        date2 = new Date();
        now = dateFormat2.format(date2);

        try {
            date = new SimpleDateFormat("yyyy-MM-dd").parse(tgm);
            date2 = new SimpleDateFormat("yyyy-MM-dd").parse(now);
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, "Data Tidak Bisa disimpan, Tanggal Pindah Tidak Valid !!!");
        }

        if (TPasienpindah.getText().trim().equals("")) {
            Valid.textKosong(norawatpindah, "pasien");
        } else if (TKdBngsalpindah.getText().trim().equals("")) {
            Valid.textKosong(kdkamarpindah, "kamar");
        } else if (date.after(date2)) {
            JOptionPane.showMessageDialog(null, "Data Tidak Bisa disimpan, Tanggal Pindah Tidak Valid !!!");
        } else {
            switch (TSttsKamarpindah.getText().trim()) {
                case "ISI":
                    JOptionPane.showMessageDialog(null, "Maaf, Status kamar sudah terisi silahkan pilih kamar kosong..!!!!");
                    kdkamar.requestFocus();
                    break;
                case "KOSONG":
                    if (Rganti1.isSelected() == true) {
//                        if (Double.parseDouble(TJmlHari.getText().trim()) < 0) {
//                            JOptionPane.showMessageDialog(null, "Data Tidak Bisa disimpan, Cek Tanggal yang Anda Input !!!");
//                        } else {
//                            Sequel.menyimpan("kamar_inap", "'" + norawatpindah.getText() + "','"
//                                    + kdkamarpindah.getText() + "','" + TTarifpindah.getText() + "','"
//                                    + diagnosaawal.getText() + "','"
//                                    + diagnosaakhir.getText() + "','"
//                                    + CmbTahunpindah.getSelectedItem() + "-" + CmbBlnpindah.getSelectedItem() + "-" + CmbTglpindah.getSelectedItem() + "','"
//                                    + cmbJampindah.getSelectedItem() + ":" + cmbMntpindah.getSelectedItem() + ":" + cmbDtkpindah.getSelectedItem() + "','0000-00-00','00:00:00','"
//                                    + TJmlHaripindah.getText() + "','" + ttlbiayapindah.getText() + "','-'", "No.Rawat");
//                            Sequel.mengedit("kamar", "kd_kamar='" + kdkamarpindah.getText() + "'", "status='ISI'");
//                            Sequel.queryu("delete from kamar_inap where no_rawat='" + tbKamIn.getValueAt(tbKamIn.getSelectedRow(), 0).toString()
//                                    + "' and kd_kamar='" + tbKamIn.getValueAt(tbKamIn.getSelectedRow(), 6).toString()
//                                    + "' and tgl_masuk='" + tbKamIn.getValueAt(tbKamIn.getSelectedRow(), 11).toString()
//                                    + "' and jam_masuk='" + tbKamIn.getValueAt(tbKamIn.getSelectedRow(), 12).toString() + "'");
//                            Sequel.mengedit("kamar", "kd_kamar='" + tbKamIn.getValueAt(tbKamIn.getSelectedRow(), 6).toString() + "'", "status='KOSONG'");
//                            WindowPindahKamar.dispose();
//                            tampil();
//                            break;
//                        }

                    } else if (Rganti2.isSelected() == true) {
//                        kamarCovid = "";
//                        kamarCovid = Sequel.cariIsi("SELECT b.kd_bangsal FROM kamar k INNER JOIN bangsal b on b.kd_bangsal=k.kd_bangsal "
//                                + "WHERE k.kd_kamar='" + tbKamIn.getValueAt(tbKamIn.getSelectedRow(), 7).toString() + "'");

                        //ini hanya untuk admin utama & admin vip/isolasi covid aja
//                        if (var.getkode().equals("Admin Utama") || var.gettombolnota_billing()) {
                            if (Double.parseDouble(TJmlHari.getText().trim()) < 0) {
                                JOptionPane.showMessageDialog(null, "Data Tidak Bisa disimpan, Cek Tanggal yang Anda Input !!!");
                            } else {
                                Sequel.queryu("update kamar_inap set kd_kamar='" + kdkamarpindah.getText() + "',trf_kamar='" + TTarifpindah.getText() + "',"
                                        + "lama='" + TJmlHaripindah.getText() + "',ttl_biaya='" + ttlbiayapindah.getText()
                                        + "' where no_rawat='" + tbKamIn.getValueAt(tbKamIn.getSelectedRow(), 0).toString()
                                        + "' and kd_kamar='" + tbKamIn.getValueAt(tbKamIn.getSelectedRow(), 7).toString()
                                        + "' and tgl_masuk='" + tbKamIn.getValueAt(tbKamIn.getSelectedRow(), 12).toString()
                                        + "' and jam_masuk='" + tbKamIn.getValueAt(tbKamIn.getSelectedRow(), 13).toString() + "'");
                                Sequel.mengedit("kamar", "kd_kamar='" + kdkamarpindah.getText() + "'", "status='ISI'");
                                Sequel.mengedit("kamar", "kd_kamar='" + tbKamIn.getValueAt(tbKamIn.getSelectedRow(), 7).toString() + "'", "status='KOSONG'");
                                WindowPindahKamar.dispose();
                                tampil();
                                break;
                            }
//                        } else {
//                            if (kamarCovid.equals("ISOV") || kamarCovid.equals("ISOVB")) {
//                                JOptionPane.showMessageDialog(null, "Khusus utk. pasien Covid-19, silahkan gunakan pilihan No. 3, kamar sebelumnya dihitung sebagai data kunjungan...!!!");
//                                tampil();
//                                break;
//                            } else {
//                                if (Double.parseDouble(TJmlHari.getText().trim()) < 0) {
//                                    JOptionPane.showMessageDialog(null, "Data Tidak Bisa disimpan, Cek Tanggal yang Anda Input !!!");
//                                } else {
//                                    Sequel.queryu("update kamar_inap set kd_kamar='" + kdkamarpindah.getText() + "',trf_kamar='" + TTarifpindah.getText() + "',"
//                                            + "lama='" + TJmlHaripindah.getText() + "',ttl_biaya='" + ttlbiayapindah.getText()
//                                            + "' where no_rawat='" + tbKamIn.getValueAt(tbKamIn.getSelectedRow(), 0).toString()
//                                            + "' and kd_kamar='" + tbKamIn.getValueAt(tbKamIn.getSelectedRow(), 7).toString()
//                                            + "' and tgl_masuk='" + tbKamIn.getValueAt(tbKamIn.getSelectedRow(), 12).toString()
//                                            + "' and jam_masuk='" + tbKamIn.getValueAt(tbKamIn.getSelectedRow(), 13).toString() + "'");
//                                    Sequel.mengedit("kamar", "kd_kamar='" + kdkamarpindah.getText() + "'", "status='ISI'");
//                                    Sequel.mengedit("kamar", "kd_kamar='" + tbKamIn.getValueAt(tbKamIn.getSelectedRow(), 7).toString() + "'", "status='KOSONG'");
//                                    WindowPindahKamar.dispose();
//                                    tampil();
//                                    break;
//                                }
//                            }
//                        }

                    } else if (Rganti3.isSelected() == true) {
                        i = 1;
                        kdkamar.setText(tbKamIn.getValueAt(tbKamIn.getSelectedRow(), 7).toString());
                        isKmr();
                        if (hariawal.equals("Yes")) {
                            Sequel.cariIsi("select (if(to_days('" + CmbTahunpindah.getSelectedItem()
                                    + "-" + CmbBlnpindah.getSelectedItem() + "-" + CmbTglpindah.getSelectedItem()
                                    + " " + cmbJampindah.getSelectedItem() + ":" + cmbMntpindah.getSelectedItem()
                                    + ":" + cmbDtkpindah.getSelectedItem() + "')-to_days('" + tbKamIn.getValueAt(tbKamIn.getSelectedRow(), 12).toString()
                                    + " " + tbKamIn.getValueAt(tbKamIn.getSelectedRow(), 13).toString()
                                    + "')=0,if(time_to_sec('" + CmbTahunpindah.getSelectedItem()
                                    + "-" + CmbBlnpindah.getSelectedItem() + "-" + CmbTglpindah.getSelectedItem()
                                    + " " + cmbJampindah.getSelectedItem() + ":" + cmbMntpindah.getSelectedItem()
                                    + ":" + cmbDtkpindah.getSelectedItem() + "')-time_to_sec('" + tbKamIn.getValueAt(tbKamIn.getSelectedRow(), 12).toString()
                                    + " " + tbKamIn.getValueAt(tbKamIn.getSelectedRow(), 13).toString() + "')>(3600*"
                                    + lama + "),1,0),to_days('" + CmbTahunpindah.getSelectedItem()
                                    + "-" + CmbBlnpindah.getSelectedItem() + "-" + CmbTglpindah.getSelectedItem()
                                    + " " + cmbJampindah.getSelectedItem() + ":" + cmbMntpindah.getSelectedItem() + ":" + cmbDtkpindah.getSelectedItem()
                                    + "')-to_days('" + tbKamIn.getValueAt(tbKamIn.getSelectedRow(), 12).toString()
                                    + " " + tbKamIn.getValueAt(tbKamIn.getSelectedRow(), 13).toString() + "'))+1) as lama", TJmlHari);
                        } else {
                            Sequel.cariIsi("select if(to_days('" + CmbTahunpindah.getSelectedItem()
                                    + "-" + CmbBlnpindah.getSelectedItem() + "-" + CmbTglpindah.getSelectedItem()
                                    + " " + cmbJampindah.getSelectedItem() + ":" + cmbMntpindah.getSelectedItem()
                                    + ":" + cmbDtkpindah.getSelectedItem() + "')-to_days('" + tbKamIn.getValueAt(tbKamIn.getSelectedRow(), 12).toString()
                                    + " " + tbKamIn.getValueAt(tbKamIn.getSelectedRow(), 13).toString()
                                    + "')=0,if(time_to_sec('" + CmbTahunpindah.getSelectedItem()
                                    + "-" + CmbBlnpindah.getSelectedItem() + "-" + CmbTglpindah.getSelectedItem()
                                    + " " + cmbJampindah.getSelectedItem() + ":" + cmbMntpindah.getSelectedItem()
                                    + ":" + cmbDtkpindah.getSelectedItem() + "')-time_to_sec('" + tbKamIn.getValueAt(tbKamIn.getSelectedRow(), 12).toString()
                                    + " " + tbKamIn.getValueAt(tbKamIn.getSelectedRow(), 13).toString() + "')>(3600*"
                                    + lama + "),1,0),to_days('" + CmbTahunpindah.getSelectedItem()
                                    + "-" + CmbBlnpindah.getSelectedItem() + "-" + CmbTglpindah.getSelectedItem()
                                    + " " + cmbJampindah.getSelectedItem() + ":" + cmbMntpindah.getSelectedItem() + ":" + cmbDtkpindah.getSelectedItem()
                                    + "')-to_days('" + tbKamIn.getValueAt(tbKamIn.getSelectedRow(), 12).toString()
                                    + " " + tbKamIn.getValueAt(tbKamIn.getSelectedRow(), 13).toString() + "')) as lama", TJmlHari);
                        }

                        isjml();
                        if (Double.parseDouble(TJmlHari.getText().trim()) < 0) {
                            JOptionPane.showMessageDialog(null, "Data Tidak Bisa disimpan, Cek Tanggal yang Anda Input !!!");
                            break;
                        } else {
                            Sequel.mengedit("kamar_inap", "no_rawat='" + tbKamIn.getValueAt(tbKamIn.getSelectedRow(), 0).toString()
                                    + "' and kd_kamar='" + tbKamIn.getValueAt(tbKamIn.getSelectedRow(), 7).toString()
                                    + "' and tgl_masuk='" + tbKamIn.getValueAt(tbKamIn.getSelectedRow(), 12).toString()
                                    + "' and jam_masuk='" + tbKamIn.getValueAt(tbKamIn.getSelectedRow(), 13).toString() + "'",
                                    "trf_kamar='" + TTarif.getText() + "',tgl_keluar='" + CmbTahunpindah.getSelectedItem() + "-" + CmbBlnpindah.getSelectedItem() + "-" + CmbTglpindah.getSelectedItem()
                                    + "',jam_keluar='" + cmbJampindah.getSelectedItem() + ":" + cmbMntpindah.getSelectedItem() + ":" + cmbDtkpindah.getSelectedItem()
                                    + "',lama='" + TJmlHari.getText() + "',ttl_biaya='" + ttlbiaya.getText() + "',stts_pulang='Pindah Kamar'");
                            Sequel.mengedit("kamar", "kd_kamar='" + tbKamIn.getValueAt(tbKamIn.getSelectedRow(), 7).toString() + "'", "status='KOSONG'");
                            Sequel.menyimpan("kamar_inap", "'" + norawatpindah.getText() + "','"
                                    + kdkamarpindah.getText() + "','" + TTarifpindah.getText() + "','"
                                    + diagnosaawal.getText() + "','" + diagnosaakhir.getText() + "','"
                                    + CmbTahunpindah.getSelectedItem() + "-" + CmbBlnpindah.getSelectedItem() + "-" + CmbTglpindah.getSelectedItem() + "','"
                                    + cmbJampindah.getSelectedItem() + ":" + cmbMntpindah.getSelectedItem() + ":" + cmbDtkpindah.getSelectedItem() + "','0000-00-00','00:00:00','"
                                    + TJmlHaripindah.getText() + "','" + ttlbiayapindah.getText() + "','-'", "No.Rawat");
                            Sequel.mengedit("kamar", "kd_kamar='" + kdkamarpindah.getText() + "'", "status='ISI'");
                            WindowPindahKamar.dispose();
                            tampil();
                            break;
                        }

                    } else if (Rganti4.isSelected() == true) {
//                        i = 1;
//                        kdkamar.setText(tbKamIn.getValueAt(tbKamIn.getSelectedRow(), 19).toString());
//                        isKmr();
//                        if (hariawal.equals("Yes")) {
//                            Sequel.cariIsi("select (if(to_days('" + CmbTahunpindah.getSelectedItem()
//                                    + "-" + CmbBlnpindah.getSelectedItem() + "-" + CmbTglpindah.getSelectedItem()
//                                    + " " + cmbJampindah.getSelectedItem() + ":" + cmbMntpindah.getSelectedItem()
//                                    + ":" + cmbDtkpindah.getSelectedItem() + "')-to_days('" + tbKamIn.getValueAt(tbKamIn.getSelectedRow(), 11).toString()
//                                    + " " + tbKamIn.getValueAt(tbKamIn.getSelectedRow(), 12).toString()
//                                    + "')=0,if(time_to_sec('" + CmbTahunpindah.getSelectedItem()
//                                    + "-" + CmbBlnpindah.getSelectedItem() + "-" + CmbTglpindah.getSelectedItem()
//                                    + " " + cmbJampindah.getSelectedItem() + ":" + cmbMntpindah.getSelectedItem()
//                                    + ":" + cmbDtkpindah.getSelectedItem() + "')-time_to_sec('" + tbKamIn.getValueAt(tbKamIn.getSelectedRow(), 11).toString()
//                                    + " " + tbKamIn.getValueAt(tbKamIn.getSelectedRow(), 12).toString() + "')>(3600*"
//                                    + lama + "),1,0),to_days('" + CmbTahunpindah.getSelectedItem()
//                                    + "-" + CmbBlnpindah.getSelectedItem() + "-" + CmbTglpindah.getSelectedItem()
//                                    + " " + cmbJampindah.getSelectedItem() + ":" + cmbMntpindah.getSelectedItem() + ":" + cmbDtkpindah.getSelectedItem()
//                                    + "')-to_days('" + tbKamIn.getValueAt(tbKamIn.getSelectedRow(), 11).toString()
//                                    + " " + tbKamIn.getValueAt(tbKamIn.getSelectedRow(), 12).toString() + "'))+1) as lama", TJmlHari);
//                        } else {
//                            Sequel.cariIsi("select if(to_days('" + CmbTahunpindah.getSelectedItem()
//                                    + "-" + CmbBlnpindah.getSelectedItem() + "-" + CmbTglpindah.getSelectedItem()
//                                    + " " + cmbJampindah.getSelectedItem() + ":" + cmbMntpindah.getSelectedItem()
//                                    + ":" + cmbDtkpindah.getSelectedItem() + "')-to_days('" + tbKamIn.getValueAt(tbKamIn.getSelectedRow(), 11).toString()
//                                    + " " + tbKamIn.getValueAt(tbKamIn.getSelectedRow(), 12).toString()
//                                    + "')=0,if(time_to_sec('" + CmbTahunpindah.getSelectedItem()
//                                    + "-" + CmbBlnpindah.getSelectedItem() + "-" + CmbTglpindah.getSelectedItem()
//                                    + " " + cmbJampindah.getSelectedItem() + ":" + cmbMntpindah.getSelectedItem()
//                                    + ":" + cmbDtkpindah.getSelectedItem() + "')-time_to_sec('" + tbKamIn.getValueAt(tbKamIn.getSelectedRow(), 11).toString()
//                                    + " " + tbKamIn.getValueAt(tbKamIn.getSelectedRow(), 12).toString() + "')>(3600*"
//                                    + lama + "),1,0),to_days('" + CmbTahunpindah.getSelectedItem()
//                                    + "-" + CmbBlnpindah.getSelectedItem() + "-" + CmbTglpindah.getSelectedItem()
//                                    + " " + cmbJampindah.getSelectedItem() + ":" + cmbMntpindah.getSelectedItem() + ":" + cmbDtkpindah.getSelectedItem()
//                                    + "')-to_days('" + tbKamIn.getValueAt(tbKamIn.getSelectedRow(), 11).toString()
//                                    + " " + tbKamIn.getValueAt(tbKamIn.getSelectedRow(), 12).toString() + "')) as lama", TJmlHari);
//                        }
//
//                        DecimalFormat df2 = new DecimalFormat("####");
//                        if ((!TJmlHari.getText().equals("")) && (!TTarif.getText().equals(""))) {
//                            double x = Double.parseDouble(TJmlHari.getText().trim());
//                            double y = 0;
//                            if (Double.parseDouble(TTarif.getText().trim()) > Double.parseDouble(TTarifpindah.getText().trim())) {
//                                y = Double.parseDouble(TTarif.getText().trim());
//                            } else if (Double.parseDouble(TTarif.getText().trim()) < Double.parseDouble(TTarifpindah.getText().trim())) {
//                                y = Double.parseDouble(TTarifpindah.getText().trim());
//                            }
//                            ttlbiaya.setText(df2.format(x * y));
//                        }
//                        if (Double.parseDouble(TJmlHari.getText().trim()) < 0) {
//                            JOptionPane.showMessageDialog(null, "Data Tidak Bisa disimpan, Cek Tanggal yang Anda Input !!!");
//                            break;
//                        } else {
//                            Sequel.mengedit("kamar_inap", "no_rawat='" + tbKamIn.getValueAt(tbKamIn.getSelectedRow(), 0).toString()
//                                    + "' and kd_kamar='" + tbKamIn.getValueAt(tbKamIn.getSelectedRow(), 19).toString()
//                                    + "' and tgl_masuk='" + tbKamIn.getValueAt(tbKamIn.getSelectedRow(), 11).toString()
//                                    + "' and jam_masuk='" + tbKamIn.getValueAt(tbKamIn.getSelectedRow(), 12).toString() + "'",
//                                    "trf_kamar='" + TTarifpindah.getText() + "',tgl_keluar='" + CmbTahunpindah.getSelectedItem() + "-" + CmbBlnpindah.getSelectedItem() + "-" + CmbTglpindah.getSelectedItem()
//                                    + "',jam_keluar='" + cmbJampindah.getSelectedItem() + ":" + cmbMntpindah.getSelectedItem() + ":" + cmbDtkpindah.getSelectedItem()
//                                    + "',ttl_biaya='" + ttlbiaya.getText() + "',lama='" + TJmlHari.getText() + "',stts_pulang='Pindah Kamar'");
//                            Sequel.mengedit("kamar", "kd_kamar='" + tbKamIn.getValueAt(tbKamIn.getSelectedRow(), 19).toString() + "'", "status='KOSONG'");
//                            Sequel.menyimpan("kamar_inap", "'" + norawatpindah.getText() + "','"
//                                    + kdkamarpindah.getText() + "','" + TTarifpindah.getText() + "','"
//                                    + diagnosaawal.getText() + "','"
//                                    + diagnosaakhir.getText() + "','"
//                                    + CmbTahunpindah.getSelectedItem() + "-" + CmbBlnpindah.getSelectedItem() + "-" + CmbTglpindah.getSelectedItem() + "','"
//                                    + cmbJampindah.getSelectedItem() + ":" + cmbMntpindah.getSelectedItem() + ":" + cmbDtkpindah.getSelectedItem() + "','0000-00-00','00:00:00','" + TJmlHaripindah.getText() + "','"
//                                    + ttlbiayapindah.getText() + "','-'", "No.Rawat");
//                            Sequel.mengedit("kamar", "kd_kamar='" + kdkamarpindah.getText() + "'", "status='ISI'");
//                            WindowPindahKamar.dispose();
//                            tampil();
//                            break;
//                        }

                    }
                    tampil();
//                    WindowPindahKamar.dispose();
//                    break;
            }
        }
    }//GEN-LAST:event_BtnSimpanpindahActionPerformed

    private void BtnSimpanpindahKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_BtnSimpanpindahKeyPressed
        // TODO add your handling code here:
    }//GEN-LAST:event_BtnSimpanpindahKeyPressed

    private void MnHapusDataSalahActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_MnHapusDataSalahActionPerformed
        if (tabMode.getRowCount() == 0) {
            JOptionPane.showMessageDialog(null, "Maaf, data sudah habis...!!!!");
            norawat.requestFocus();
        } else if (TPasien.getText().trim().equals("")) {
            JOptionPane.showMessageDialog(null, "Maaf, Gagal menghapus. Pilih dulu data yang mau dihapus. Klik data pada tabel untuk memilih. Untuk menghapus pasien bayi lewat ranap gabung!!!!");
        } else if (!(TPasien.getText().trim().equals(""))) {
            if (var.getkode().equals("Admin Utama")) {
                i = JOptionPane.showConfirmDialog(rootPane, "Yakin data mau dihapus..??", "Konfirmasi", JOptionPane.YES_NO_OPTION);
                if (i == JOptionPane.YES_OPTION) {
                    sepJkd.setText(Sequel.cariIsi("SELECT bridging_jamkesda.no_sep FROM reg_periksa "
                            + "INNER JOIN bridging_jamkesda ON reg_periksa.no_rawat = bridging_jamkesda.no_rawat "
                            + "INNER JOIN penjab ON reg_periksa.kd_pj = penjab.kd_pj "
                            + "WHERE bridging_jamkesda.no_rawat='" + norawat.getText() + "' AND bridging_jamkesda.jns_rawat='Inap' AND penjab.png_jawab like '%jamkesda%'"));

                    catatanIGD.setText(Sequel.cariIsi("SELECT ket_igd FROM data_igd WHERE data_igd.no_rawat='" + norawat.getText() + "' AND ket_igd='DIRAWAT' "));

                    Sequel.queryu("delete from kamar_inap where no_rawat='" + norawat.getText() + "' and kd_kamar='" + kdkamar.getText() + "' "
                            + "and tgl_masuk='" + TIn.getText() + "' and jam_masuk='" + JamMasuk.getText() + "'");
                    Sequel.meghapus("bridging_jamkesda", "no_sep", sepJkd.getText());
                    Sequel.meghapus("pasien_mati", "no_rkm_medis", TNoRM.getText());
                    Sequel.meghapus("dpjp_ranap", "no_rawat", norawat.getText());
                    Sequel.mengedit("kamar", "kd_kamar='" + kdkamar.getText() + "'", "status='KOSONG'");
                    Sequel.mengedit("data_igd", "no_rawat='" + norawat.getText() + "'", "tindakan_lanjut='RAWAT JALAN', ket_igd='PULANG'");

                    if (Sequel.cariInteger("select count(no_rawat) from kamar_inap where no_rawat=?", norawat.getText()) == 0) {
                        Sequel.mengedit("reg_periksa", "no_rawat='" + norawat.getText() + "'", "status_lanjut='Ralan'");
                    }
                    tampil();
                }
            } else {
                JOptionPane.showMessageDialog(null, "Data pasien tersebut tidak bisa dihapus...!!!");
            }
        }
        emptTeks();
    }//GEN-LAST:event_MnHapusDataSalahActionPerformed

    private void MnStokObatPasienActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_MnStokObatPasienActionPerformed
        if (tabMode.getRowCount() == 0) {
            JOptionPane.showMessageDialog(null, "Maaf, tabel masih kosong...!!!!");
            TCari.requestFocus();
        } else if (TPasien.getText().trim().equals("")) {
            try {
                psanak = koneksi.prepareStatement(
                        "select pasien.no_rkm_medis,pasien.nm_pasien,ranap_gabung.no_rawat2,concat(reg_periksa.umurdaftar,' ',reg_periksa.sttsumur)as umur,pasien.no_peserta, "
                        + "concat(pasien.alamatpj,', ',pasien.kelurahanpj,', ',pasien.kecamatanpj,', ',pasien.kabupatenpj) as alamat "
                        + "from reg_periksa inner join pasien inner join ranap_gabung on "
                        + "pasien.no_rkm_medis=reg_periksa.no_rkm_medis and ranap_gabung.no_rawat2=reg_periksa.no_rawat where ranap_gabung.no_rawat=?");
                try {
                    psanak.setString(1, tbKamIn.getValueAt(tbKamIn.getSelectedRow(), 0).toString());
                    rs2 = psanak.executeQuery();
                    if (rs2.next()) {
                        if (Sequel.cariRegistrasi(rs2.getString("no_rawat2")) > 0) {
                            JOptionPane.showMessageDialog(rootPane, "Data billing sudah terverifikasi, data tidak boleh dihapus/diubah. Silahkan hubungi bagian kasir/keuangan ..!!");
                        } else {
                            DlgInputStokPasien dlgrjk = new DlgInputStokPasien(null, false);
                            dlgrjk.setSize(internalFrame1.getWidth(), internalFrame1.getHeight());
                            dlgrjk.setLocationRelativeTo(internalFrame1);
                            dlgrjk.isCek();
                            dlgrjk.setNoRm(rs2.getString("no_rawat2"), TNoRM.getText() + " " + TPasien.getText());
                            dlgrjk.tampil();
                            dlgrjk.setVisible(true);
                        }
                    } else {
                        JOptionPane.showMessageDialog(null, "Maaf, Silahkan anda pilih dulu pasien...!!!");
                        tbKamIn.requestFocus();
                    }
                } catch (Exception ex) {
                    System.out.println("Notifikasi : " + ex);
                } finally {
                    if (rs2 != null) {
                        rs2.close();
                    }
                    if (psanak != null) {
                        psanak.close();
                    }
                }
            } catch (Exception e) {
                System.out.println(e);
            }
        } else {
            if (Sequel.cariRegistrasi(tbKamIn.getValueAt(tbKamIn.getSelectedRow(), 0).toString()) > 0) {
                JOptionPane.showMessageDialog(rootPane, "Data billing sudah terverifikasi, data tidak boleh dihapus/diubah. Silahkan hubungi bagian kasir/keuangan ..!!");
            } else {
                DlgInputStokPasien dlgrjk = new DlgInputStokPasien(null, false);
                dlgrjk.setSize(internalFrame1.getWidth(), internalFrame1.getHeight());
                dlgrjk.setLocationRelativeTo(internalFrame1);
                dlgrjk.isCek();
                dlgrjk.setNoRm(norawat.getText(), TNoRM.getText() + " " + TPasien.getText());
                dlgrjk.tampil();
                dlgrjk.setVisible(true);
            }

        }
    }//GEN-LAST:event_MnStokObatPasienActionPerformed

    private void BtnCloseIn4ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_BtnCloseIn4ActionPerformed
        WindowCaraBayar.dispose();
    }//GEN-LAST:event_BtnCloseIn4ActionPerformed

    private void BtnSimpan4ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_BtnSimpan4ActionPerformed
        if (norawat.getText().trim().equals("")) {
            Valid.textKosong(norawat, "No.Rawat");
        }
        if (kdpenjab.getText().trim().equals("") || nmpenjab.getText().trim().equals("")) {
            Valid.textKosong(kdpenjab, "Jenis Bayar");
        } else {
            Sequel.AutoComitFalse();
            Sequel.mengedit("reg_periksa", "no_rawat=?", " kd_pj=?", 2, new String[]{kdpenjab.getText(), norawat.getText()});
            Sequel.AutoComitTrue();
            tampil();
            WindowCaraBayar.dispose();
        }
    }//GEN-LAST:event_BtnSimpan4ActionPerformed

    private void kdpenjabKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_kdpenjabKeyPressed
        if (evt.getKeyCode() == KeyEvent.VK_PAGE_DOWN) {
            Sequel.cariIsi("select nm_poli from poliklinik where kd_poli=?", nmpenjab, kdpenjab.getText());
        } else if (evt.getKeyCode() == KeyEvent.VK_UP) {
            btnBayarActionPerformed(null);
        } else {
            Valid.pindah(evt, BtnCloseIn4, BtnSimpan4);
        }
    }//GEN-LAST:event_kdpenjabKeyPressed

    private void btnBayarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnBayarActionPerformed
        var.setform("DlgKamarInap");
        reg.pasien.penjab.emptTeks();
        reg.pasien.penjab.isCek();
        reg.pasien.penjab.setSize(internalFrame1.getWidth() - 40, internalFrame1.getHeight() - 40);
        reg.pasien.penjab.setLocationRelativeTo(internalFrame1);
        reg.pasien.penjab.setVisible(true);
    }//GEN-LAST:event_btnBayarActionPerformed

    private void MnPenjabActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_MnPenjabActionPerformed
        if (tabMode.getRowCount() == 0) {
            JOptionPane.showMessageDialog(null, "Maaf, tabel masih kosong...!!!!");
            TCari.requestFocus();
        } else if (TPasien.getText().trim().equals("")) {
            JOptionPane.showMessageDialog(null, "Maaf, Silahkan anda pilih dulu pasien...!!!");
            tbKamIn.requestFocus();
        } else {
            WindowCaraBayar.setLocationRelativeTo(internalFrame1);
            WindowCaraBayar.setVisible(true);
            btnBayar.requestFocus();
        }
    }//GEN-LAST:event_MnPenjabActionPerformed

    private void MnSensusRanapActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_MnSensusRanapActionPerformed
        this.setCursor(Cursor.getPredefinedCursor(Cursor.WAIT_CURSOR));
        try {
            host = Sequel.decXML(prop.getProperty("HOSTraza"), prop.getProperty("KEY"));
            if (var.getpegawai_admin() == true) {
                Valid.panggilUrlRAZA("/rzid/");
            } else if (var.getpegawai_user() == true) {
                Valid.panggilUrlRAZA("/rzid/");
            }
        } catch (Exception ex) {
            System.out.println("Notifikasi : " + ex);
        }

        this.setCursor(Cursor.getDefaultCursor());
    }//GEN-LAST:event_MnSensusRanapActionPerformed

    private void MnDepositActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_MnDepositActionPerformed
        if (tabMode.getRowCount() == 0) {
            JOptionPane.showMessageDialog(null, "Maaf, tabel masih kosong...!!!!");
            TCari.requestFocus();
        } else if (TPasien.getText().trim().equals("")) {
            try {
                psanak = koneksi.prepareStatement(
                        "select pasien.no_rkm_medis,pasien.nm_pasien,ranap_gabung.no_rawat2,concat(reg_periksa.umurdaftar,' ',reg_periksa.sttsumur)as umur,pasien.no_peserta, "
                        + "concat(pasien.alamatpj,', ',pasien.kelurahanpj,', ',pasien.kecamatanpj,', ',pasien.kabupatenpj) as alamat "
                        + "from reg_periksa inner join pasien inner join ranap_gabung on "
                        + "pasien.no_rkm_medis=reg_periksa.no_rkm_medis and ranap_gabung.no_rawat2=reg_periksa.no_rawat where ranap_gabung.no_rawat=?");

                try {
                    psanak.setString(1, tbKamIn.getValueAt(tbKamIn.getSelectedRow(), 0).toString());
                    rs2 = psanak.executeQuery();
                    if (rs2.next()) {
                        billing.deposit.setSize(internalFrame1.getWidth(), internalFrame1.getHeight());
                        billing.deposit.setLocationRelativeTo(internalFrame1);
                        billing.deposit.setNoRm(rs2.getString("no_rawat2"), DTPCari1.getDate(), DTPCari2.getDate());
                        billing.deposit.tampil();
                        billing.deposit.setVisible(true);
                    } else {
                        JOptionPane.showMessageDialog(null, "Maaf, Silahkan anda pilih dulu pasien...!!!");
                        tbKamIn.requestFocus();
                    }
                } catch (Exception ex) {
                    System.out.println("Notifikasi : " + ex);
                } finally {
                    if (rs2 != null) {
                        rs2.close();
                    }
                    if (psanak != null) {
                        psanak.close();
                    }
                }
            } catch (Exception e) {
                System.out.println(e);
            }
        } else {
            billing.deposit.setSize(internalFrame1.getWidth(), internalFrame1.getHeight());
            billing.deposit.setLocationRelativeTo(internalFrame1);
            billing.deposit.setNoRm(norawat.getText(), DTPCari1.getDate(), DTPCari2.getDate());
            billing.deposit.tampil();
            billing.deposit.setVisible(true);
        }
    }//GEN-LAST:event_MnDepositActionPerformed

    private void MnNoResepActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_MnNoResepActionPerformed
        if (tabMode.getRowCount() == 0) {
            JOptionPane.showMessageDialog(null, "Maaf, tabel masih kosong...!!!!");
            TCari.requestFocus();
        } else if (TPasien.getText().trim().equals("")) {
            try {
                psanak = koneksi.prepareStatement(
                        "select pasien.no_rkm_medis,pasien.nm_pasien,ranap_gabung.no_rawat2,concat(reg_periksa.umurdaftar,' ',reg_periksa.sttsumur) as umur,pasien.no_peserta, "
                        + "concat(pasien.alamatpj,', ',pasien.kelurahanpj,', ',pasien.kecamatanpj,', ',pasien.kabupatenpj) as alamat "
                        + "from reg_periksa inner join pasien inner join ranap_gabung on "
                        + "pasien.no_rkm_medis=reg_periksa.no_rkm_medis and ranap_gabung.no_rawat2=reg_periksa.no_rawat where ranap_gabung.no_rawat=?");
                try {
                    psanak.setString(1, tbKamIn.getValueAt(tbKamIn.getSelectedRow(), 0).toString());
                    rs2 = psanak.executeQuery();
                    if (rs2.next()) {
                        if (Sequel.cariRegistrasi(rs2.getString("no_rawat2")) > 0) {
                            if (var.getnamauser().equals("Admin Utama") || var.gettombolnota_billing()) {
                                DlgResepObat resep = new DlgResepObat(null, false);
                                resep.setSize(internalFrame1.getWidth(), internalFrame1.getHeight());
                                resep.setLocationRelativeTo(internalFrame1);
                                resep.emptTeks();
                                resep.isCek();
                                resep.setNoRm(rs2.getString("no_rawat2"), DTPCari1.getDate(), DTPCari2.getDate(), now.substring(11, 13), now.substring(14, 16), now.substring(17, 19));
                                resep.tampil();
                                resep.setVisible(true);
                            } else {
                                JOptionPane.showMessageDialog(rootPane, "Data billing sudah terverifikasi, data tidak boleh dihapus/diubah. Silahkan hubungi bagian kasir/keuangan ..!!");
                            }
                        } else {
                            DlgResepObat resep = new DlgResepObat(null, false);
                            resep.setSize(internalFrame1.getWidth(), internalFrame1.getHeight());
                            resep.setLocationRelativeTo(internalFrame1);
                            resep.emptTeks();
                            resep.isCek();
                            resep.setNoRm(rs2.getString("no_rawat2"), DTPCari1.getDate(), DTPCari2.getDate(), now.substring(11, 13), now.substring(14, 16), now.substring(17, 19));
                            resep.tampil();
                            resep.setVisible(true);
                        }
                    } else {
                        JOptionPane.showMessageDialog(null, "Maaf, Silahkan anda pilih dulu pasien...!!!");
                        tbKamIn.requestFocus();
                    }
                } catch (Exception ex) {
                    System.out.println("Notifikasi : " + ex);
                } finally {
                    if (rs2 != null) {
                        rs2.close();
                    }
                    if (psanak != null) {
                        psanak.close();
                    }
                }
            } catch (Exception e) {
                System.out.println(e);
            }
        } else {
            if (Sequel.cariRegistrasi(tbKamIn.getValueAt(tbKamIn.getSelectedRow(), 0).toString()) > 0) {
                if (var.getnamauser().equals("Admin Utama") || var.gettombolnota_billing()) {
                    DlgResepObat resep = new DlgResepObat(null, false);
                    resep.setSize(internalFrame1.getWidth(), internalFrame1.getHeight());
                    resep.setLocationRelativeTo(internalFrame1);
                    resep.emptTeks();
                    resep.isCek();
                    resep.setNoRm(norawat.getText(), DTPCari1.getDate(), DTPCari2.getDate(), now.substring(11, 13), now.substring(14, 16), now.substring(17, 19));
                    resep.tampil();
                    resep.setVisible(true);
                } else {
                    JOptionPane.showMessageDialog(rootPane, "Data billing sudah terverifikasi, data tidak boleh dihapus/diubah. Silahkan hubungi bagian kasir/keuangan ..!!");
                }
            } else {
                DlgResepObat resep = new DlgResepObat(null, false);
                resep.setSize(internalFrame1.getWidth(), internalFrame1.getHeight());
                resep.setLocationRelativeTo(internalFrame1);
                resep.emptTeks();
                resep.isCek();
                resep.setNoRm(norawat.getText(), DTPCari1.getDate(), DTPCari2.getDate(), now.substring(11, 13), now.substring(14, 16), now.substring(17, 19));
                resep.tampil();
                resep.setVisible(true);
            }

        }
    }//GEN-LAST:event_MnNoResepActionPerformed

    private void MnRM2DActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_MnRM2DActionPerformed
        if (tabMode.getRowCount() == 0) {
            JOptionPane.showMessageDialog(null, "Maaf, tabel masih kosong...!!!!");
            TCari.requestFocus();
        } else if (TPasien.getText().trim().equals("")) {
            try {
                psanak = koneksi.prepareStatement(
                        "select pasien.no_rkm_medis,pasien.nm_pasien,ranap_gabung.no_rawat2,concat(reg_periksa.umurdaftar,' ',reg_periksa.sttsumur)as umur,pasien.no_peserta, "
                        + "concat(pasien.alamatpj,', ',pasien.kelurahanpj,', ',pasien.kecamatanpj,', ',pasien.kabupatenpj) as alamat "
                        + "from reg_periksa inner join pasien inner join ranap_gabung on "
                        + "pasien.no_rkm_medis=reg_periksa.no_rkm_medis and ranap_gabung.no_rawat2=reg_periksa.no_rawat where ranap_gabung.no_rawat=?");

                try {
                    psanak.setString(1, tbKamIn.getValueAt(tbKamIn.getSelectedRow(), 0).toString());
                    rs2 = psanak.executeQuery();
                    if (rs2.next()) {
                        Map<String, Object> param = new HashMap<>();
                        param.put("nama", rs2.getString("nm_pasien"));
                        param.put("jkel", Sequel.cariIsi("select if(pasien.jk='L','Laki-Laki','Perempuan') from pasien where pasien.no_rkm_medis=?", rs2.getString("no_rkm_medis")));
                        param.put("lahir", Sequel.cariIsi("select DATE_FORMAT(pasien.tgl_lahir,'%d-%m-%Y') from pasien where pasien.no_rkm_medis=?", rs2.getString("no_rkm_medis")));
                        param.put("norm", rs2.getString("no_rkm_medis"));
                        param.put("ruang", tbKamIn.getValueAt(tbKamIn.getSelectedRow(), 8).toString());
                        param.put("kelas", Sequel.cariIsi("select kamar.kelas from kamar inner join bangsal on kamar.kd_bangsal=bangsal.kd_bangsal "
                                + "where kamar.kd_kamar=? ", tbKamIn.getValueAt(tbKamIn.getSelectedRow(), 7).toString()));
                        param.put("tanggaldaftar", Sequel.cariIsi("select DATE_FORMAT(reg_periksa.tgl_registrasi,'%d-%m-%Y') from reg_periksa "
                                + "where reg_periksa.no_rawat=?", rs2.getString("no_rawat2")));
                        param.put("jamdaftar", Sequel.cariIsi("select reg_periksa.jam_reg from reg_periksa where reg_periksa.no_rawat=?", rs2.getString("no_rawat2")));
                        param.put("noreg", Sequel.cariIsi("select reg_periksa.no_reg from reg_periksa where reg_periksa.no_rawat=?", rs2.getString("no_rawat2")));
                        param.put("pendidikan", "-");
                        param.put("agama", Sequel.cariIsi("select pasien.agama from pasien where pasien.no_rkm_medis=?", rs2.getString("no_rkm_medis")));
                        param.put("bangsa", "Jawa/Indonesia");
                        param.put("pekerjaan", Sequel.cariIsi("select pasien.agama from pasien where pasien.no_rkm_medis=?", rs2.getString("no_rkm_medis")));
                        param.put("status", "Single");
                        param.put("alamat", tbKamIn.getValueAt(tbKamIn.getSelectedRow(), 3).toString());
                        param.put("keluarga", Sequel.cariIsi("select pasien.namakeluarga from pasien where pasien.no_rkm_medis=?", rs2.getString("no_rkm_medis")));
                        param.put("telp", Sequel.cariIsi("select pasien.no_tlp from pasien where pasien.no_rkm_medis=?", rs2.getString("no_rkm_medis")));
                        param.put("rujukandari", Sequel.cariIsi("select rujuk_masuk.perujuk from rujuk_masuk where rujuk_masuk.no_rawat=?", rs2.getString("no_rawat2")));
                        param.put("chkri", Sequel.cariIsi("select if(count(kamar_inap.no_rawat)>1,'V','') from reg_periksa inner join pasien inner join kamar_inap "
                                + "on pasien.no_rkm_medis=reg_periksa.no_rkm_medis and reg_periksa.no_rawat=kamar_inap.no_rawat "
                                + "where reg_periksa.no_rkm_medis=? ", rs2.getString("no_rkm_medis")));
                        param.put("chkrj", Sequel.cariIsi("select if(count(reg_periksa.no_rawat)>1,'V','') from reg_periksa inner join pasien "
                                + "on pasien.no_rkm_medis=reg_periksa.no_rkm_medis where reg_periksa.no_rkm_medis=? ", rs2.getString("no_rkm_medis")));
                        param.put("riterakhir", Sequel.cariIsi("select if(count(kamar_inap.no_rawat)>1,DATE_FORMAT(kamar_inap.tgl_keluar,'%d-%m-%Y'),'') from reg_periksa inner join pasien inner join kamar_inap "
                                + "on pasien.no_rkm_medis=reg_periksa.no_rkm_medis and reg_periksa.no_rawat=kamar_inap.no_rawat where "
                                + "reg_periksa.no_rkm_medis=? order by kamar_inap.tgl_masuk desc limit 1", rs2.getString("no_rkm_medis")));
                        param.put("rjterakhir", Sequel.cariIsi("select if(count(reg_periksa.no_rawat)>1,DATE_FORMAT(reg_periksa.tgl_registrasi,'%d-%m-%Y'),'') from reg_periksa inner join pasien "
                                + "on pasien.no_rkm_medis=reg_periksa.no_rkm_medis where reg_periksa.no_rkm_medis=? "
                                + "order by reg_periksa.tgl_registrasi desc ", rs2.getString("no_rkm_medis")));
                        param.put("rike", Sequel.cariIsi("select if(count(kamar_inap.no_rawat)>1,(count(kamar_inap.no_rawat)-1),'') from reg_periksa inner join pasien inner join kamar_inap "
                                + "on pasien.no_rkm_medis=reg_periksa.no_rkm_medis and reg_periksa.no_rawat=kamar_inap.no_rawat where "
                                + "reg_periksa.no_rkm_medis=? ", rs2.getString("no_rkm_medis")));
                        param.put("rjke", Sequel.cariIsi("select if(count(reg_periksa.no_rawat)>1,(count(reg_periksa.no_rawat)-1),'') from reg_periksa inner join pasien "
                                + "on pasien.no_rkm_medis=reg_periksa.no_rkm_medis where reg_periksa.no_rkm_medis=? ", rs2.getString("no_rkm_medis")));
                        param.put("riruang", Sequel.cariIsi("select if(count(kamar_inap.no_rawat)>1,kamar_inap.kd_kamar,'') from reg_periksa inner join pasien inner join kamar_inap "
                                + "on pasien.no_rkm_medis=reg_periksa.no_rkm_medis and reg_periksa.no_rawat=kamar_inap.no_rawat where "
                                + "reg_periksa.no_rkm_medis=? order by kamar_inap.tgl_masuk desc limit 1", rs2.getString("no_rkm_medis")));
                        param.put("rjruang", Sequel.cariIsi("select if(count(reg_periksa.no_rawat)>1,kd_poli,'') from reg_periksa inner join pasien "
                                + "on pasien.no_rkm_medis=reg_periksa.no_rkm_medis where reg_periksa.no_rkm_medis=? "
                                + "order by reg_periksa.tgl_registrasi desc ", rs2.getString("no_rkm_medis")));
                        param.put("chkruang", Sequel.cariIsi("select if(count(kamar_inap.no_rawat)>1,'V','') from reg_periksa inner join pasien inner join kamar_inap "
                                + "on pasien.no_rkm_medis=reg_periksa.no_rkm_medis and reg_periksa.no_rawat=kamar_inap.no_rawat where "
                                + "reg_periksa.no_rkm_medis=? order by kamar_inap.tgl_masuk desc limit 1", rs2.getString("no_rkm_medis")));
                        param.put("chkbangsal", Sequel.cariIsi("select if(count(reg_periksa.no_rawat)>1,'V','') from reg_periksa inner join pasien "
                                + "on pasien.no_rkm_medis=reg_periksa.no_rkm_medis where reg_periksa.no_rkm_medis=? "
                                + "order by reg_periksa.tgl_registrasi desc ", rs2.getString("no_rkm_medis")));
                        param.put("chkkelri", Sequel.cariIsi("select if(count(kamar_inap.no_rawat)>1,'V','') from reg_periksa inner join pasien inner join kamar_inap "
                                + "on pasien.no_rkm_medis=reg_periksa.no_rkm_medis and reg_periksa.no_rawat=kamar_inap.no_rawat where "
                                + "reg_periksa.no_rkm_medis=? order by kamar_inap.tgl_masuk desc limit 1", rs2.getString("no_rkm_medis")));
                        param.put("chkkelrj", Sequel.cariIsi("select if(count(reg_periksa.no_rawat)>1,'V','') from reg_periksa inner join pasien "
                                + "on pasien.no_rkm_medis=reg_periksa.no_rkm_medis where reg_periksa.no_rkm_medis=? "
                                + "order by reg_periksa.tgl_registrasi desc ", rs2.getString("no_rkm_medis")));
                        param.put("petugas", Sequel.cariIsi("select nama from petugas where nip=?", var.getkode()));

                        param.put("namars", var.getnamars());
                        param.put("alamatrs", var.getalamatrs());
                        param.put("kotars", var.getkabupatenrs());
                        param.put("propinsirs", var.getpropinsirs());
                        param.put("kontakrs", var.getkontakrs());
                        param.put("emailrs", var.getemailrs());
                        param.put("logo", Sequel.cariGambar("select logo from setting"));
                        Valid.MyReport("rptRM2D.jrxml", "report", "::[ Lembar Assasmen ]::",
                                "select current_date() as tanggal, current_time() as jam", param);
                    } else {
                        JOptionPane.showMessageDialog(null, "Maaf, Silahkan anda pilih dulu pasien...!!!");
                        tbKamIn.requestFocus();
                    }
                } catch (Exception ex) {
                    System.out.println("Notifikasi : " + ex);
                } finally {
                    if (rs2 != null) {
                        rs2.close();
                    }
                    if (psanak != null) {
                        psanak.close();
                    }
                }
            } catch (Exception e) {
                System.out.println(e);
            }
        } else {
            Map<String, Object> param = new HashMap<>();
            param.put("nama", TPasien.getText());
            param.put("jkel", Sequel.cariIsi("select if(pasien.jk='L','Laki-Laki','Perempuan') from pasien where pasien.no_rkm_medis=?", TNoRM.getText()));
            param.put("lahir", Sequel.cariIsi("select DATE_FORMAT(pasien.tgl_lahir,'%d-%m-%Y') from pasien where pasien.no_rkm_medis=?", TNoRM.getText()));
            param.put("norm", TNoRM.getText());
            param.put("ruang", tbKamIn.getValueAt(tbKamIn.getSelectedRow(), 8).toString());
            param.put("kelas", Sequel.cariIsi("select kamar.kelas from kamar inner join bangsal on kamar.kd_bangsal=bangsal.kd_bangsal "
                    + "where kamar.kd_kamar=? ", tbKamIn.getValueAt(tbKamIn.getSelectedRow(), 7).toString()));
            param.put("tanggaldaftar", Sequel.cariIsi("select DATE_FORMAT(reg_periksa.tgl_registrasi,'%d-%m-%Y') from reg_periksa where reg_periksa.no_rawat=?", norawat.getText()));
            param.put("jamdaftar", Sequel.cariIsi("select reg_periksa.jam_reg from reg_periksa where reg_periksa.no_rawat=?", norawat.getText()));
            param.put("noreg", Sequel.cariIsi("select reg_periksa.no_reg from reg_periksa where reg_periksa.no_rawat=?", norawat.getText()));
            param.put("pendidikan", Sequel.cariIsi("select pasien.pnd from pasien where pasien.no_rkm_medis=?", TNoRM.getText()));
            param.put("agama", Sequel.cariIsi("select pasien.agama from pasien where pasien.no_rkm_medis=?", TNoRM.getText()));
            param.put("bangsa", "Jawa/Indonesia");
            param.put("pekerjaan", Sequel.cariIsi("select pasien.agama from pasien where pasien.no_rkm_medis=?", TNoRM.getText()));
            param.put("status", Sequel.cariIsi("select pasien.stts_nikah from pasien where pasien.no_rkm_medis=?", TNoRM.getText()));
            param.put("alamat", tbKamIn.getValueAt(tbKamIn.getSelectedRow(), 3).toString());
            param.put("keluarga", Sequel.cariIsi("select pasien.namakeluarga from pasien where pasien.no_rkm_medis=?", TNoRM.getText()));
            param.put("telp", Sequel.cariIsi("select pasien.no_tlp from pasien where pasien.no_rkm_medis=?", TNoRM.getText()));
            param.put("rujukandari", Sequel.cariIsi("select rujuk_masuk.perujuk from rujuk_masuk where rujuk_masuk.no_rawat=?", norawat.getText()));
            param.put("chkri", Sequel.cariIsi("select if(count(kamar_inap.no_rawat)>1,'V','') from reg_periksa inner join pasien inner join kamar_inap "
                    + "on pasien.no_rkm_medis=reg_periksa.no_rkm_medis and reg_periksa.no_rawat=kamar_inap.no_rawat where reg_periksa.no_rkm_medis=? ", TNoRM.getText()));
            param.put("chkrj", Sequel.cariIsi("select if(count(reg_periksa.no_rawat)>1,'V','') from reg_periksa inner join pasien "
                    + "on pasien.no_rkm_medis=reg_periksa.no_rkm_medis where reg_periksa.no_rkm_medis=? ", TNoRM.getText()));
            param.put("riterakhir", Sequel.cariIsi("select if(count(kamar_inap.no_rawat)>1,DATE_FORMAT(kamar_inap.tgl_keluar,'%d-%m-%Y'),'') from reg_periksa inner join pasien inner join kamar_inap "
                    + "on pasien.no_rkm_medis=reg_periksa.no_rkm_medis and reg_periksa.no_rawat=kamar_inap.no_rawat where "
                    + "reg_periksa.no_rkm_medis=? order by kamar_inap.tgl_masuk desc limit 1", TNoRM.getText()));
            param.put("rjterakhir", Sequel.cariIsi("select if(count(reg_periksa.no_rawat)>1,DATE_FORMAT(reg_periksa.tgl_registrasi,'%d-%m-%Y'),'') from reg_periksa inner join pasien "
                    + "on pasien.no_rkm_medis=reg_periksa.no_rkm_medis where reg_periksa.no_rkm_medis=? order by reg_periksa.tgl_registrasi desc ", TNoRM.getText()));
            param.put("rike", Sequel.cariIsi("select if(count(kamar_inap.no_rawat)>1,(count(kamar_inap.no_rawat)-1),'') from reg_periksa inner join pasien inner join kamar_inap "
                    + "on pasien.no_rkm_medis=reg_periksa.no_rkm_medis and reg_periksa.no_rawat=kamar_inap.no_rawat where reg_periksa.no_rkm_medis=? ", TNoRM.getText()));
            param.put("rjke", Sequel.cariIsi("select if(count(reg_periksa.no_rawat)>1,(count(reg_periksa.no_rawat)-1),'') from reg_periksa inner join pasien "
                    + "on pasien.no_rkm_medis=reg_periksa.no_rkm_medis where reg_periksa.no_rkm_medis=? ", TNoRM.getText()));
            param.put("riruang", Sequel.cariIsi("select if(count(kamar_inap.no_rawat)>1,kamar_inap.kd_kamar,'') from reg_periksa inner join pasien inner join kamar_inap "
                    + "on pasien.no_rkm_medis=reg_periksa.no_rkm_medis and reg_periksa.no_rawat=kamar_inap.no_rawat where "
                    + "reg_periksa.no_rkm_medis=? order by kamar_inap.tgl_masuk desc limit 1", TNoRM.getText()));
            param.put("rjruang", Sequel.cariIsi("select if(count(reg_periksa.no_rawat)>1,kd_poli,'') from reg_periksa inner join pasien "
                    + "on pasien.no_rkm_medis=reg_periksa.no_rkm_medis where reg_periksa.no_rkm_medis=? order by reg_periksa.tgl_registrasi desc ", TNoRM.getText()));
            param.put("chkruang", Sequel.cariIsi("select if(count(kamar_inap.no_rawat)>1,'V','') from reg_periksa inner join pasien inner join kamar_inap "
                    + "on pasien.no_rkm_medis=reg_periksa.no_rkm_medis and reg_periksa.no_rawat=kamar_inap.no_rawat where "
                    + "reg_periksa.no_rkm_medis=? order by kamar_inap.tgl_masuk desc limit 1", TNoRM.getText()));
            param.put("chkbangsal", Sequel.cariIsi("select if(count(reg_periksa.no_rawat)>1,'V','') from reg_periksa inner join pasien "
                    + "on pasien.no_rkm_medis=reg_periksa.no_rkm_medis where reg_periksa.no_rkm_medis=? order by reg_periksa.tgl_registrasi desc ", TNoRM.getText()));
            param.put("chkkelri", Sequel.cariIsi("select if(count(kamar_inap.no_rawat)>1,'V','') from reg_periksa inner join pasien inner join kamar_inap "
                    + "on pasien.no_rkm_medis=reg_periksa.no_rkm_medis and reg_periksa.no_rawat=kamar_inap.no_rawat where "
                    + "reg_periksa.no_rkm_medis=? order by kamar_inap.tgl_masuk desc limit 1", TNoRM.getText()));
            param.put("chkkelrj", Sequel.cariIsi("select if(count(reg_periksa.no_rawat)>1,'V','') from reg_periksa inner join pasien "
                    + "on pasien.no_rkm_medis=reg_periksa.no_rkm_medis where reg_periksa.no_rkm_medis=? order by reg_periksa.tgl_registrasi desc ", TNoRM.getText()));
            param.put("petugas", Sequel.cariIsi("select nama from petugas where nip=?", var.getkode()));

            param.put("namars", var.getnamars());
            param.put("alamatrs", var.getalamatrs());
            param.put("kotars", var.getkabupatenrs());
            param.put("propinsirs", var.getpropinsirs());
            param.put("kontakrs", var.getkontakrs());
            param.put("emailrs", var.getemailrs());
            param.put("logo", Sequel.cariGambar("select logo from setting"));
            Valid.MyReport("rptRM2D.jrxml", "report", "::[ Lembar Assasmen ]::",
                    "select current_date() as tanggal, current_time() as jam", param);
        }
    }//GEN-LAST:event_MnRM2DActionPerformed

    private void formWindowActivated(java.awt.event.WindowEvent evt) {//GEN-FIRST:event_formWindowActivated
        if ((!(TOut.getText().trim().length() > 0)) && (var.getstatus() == true)) {
            WindowInputKamar.setVisible(true);
        }
    }//GEN-LAST:event_formWindowActivated

    private void formWindowOpened(java.awt.event.WindowEvent evt) {//GEN-FIRST:event_formWindowOpened
        tampil();
    }//GEN-LAST:event_formWindowOpened

    private void MnInputResepActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_MnInputResepActionPerformed
        if (tabMode.getRowCount() == 0) {
            JOptionPane.showMessageDialog(null, "Maaf, tabel masih kosong...!!!!");
            TCari.requestFocus();
        } else if (TPasien.getText().trim().equals("")) {
            try {
                psanak = koneksi.prepareStatement(
                        "select pasien.no_rkm_medis,pasien.nm_pasien,ranap_gabung.no_rawat2,concat(reg_periksa.umurdaftar,' ',reg_periksa.sttsumur)as umur,pasien.no_peserta, "
                        + "concat(pasien.alamatpj,', ',pasien.kelurahanpj,', ',pasien.kecamatanpj,', ',pasien.kabupatenpj) as alamat "
                        + "from reg_periksa inner join pasien inner join ranap_gabung on "
                        + "pasien.no_rkm_medis=reg_periksa.no_rkm_medis and ranap_gabung.no_rawat2=reg_periksa.no_rawat where ranap_gabung.no_rawat=?");

                try {
                    psanak.setString(1, tbKamIn.getValueAt(tbKamIn.getSelectedRow(), 0).toString());
                    rs2 = psanak.executeQuery();
                    if (rs2.next()) {
                        if (Sequel.cariRegistrasi(rs2.getString("no_rawat2")) > 0) {
                            if (var.getnamauser().equals("Admin Utama")) {
                                billing.reseppulang.inputresep.isCek();
                                billing.reseppulang.inputresep.setNoRm(rs2.getString("no_rawat2"), "-", Sequel.cariIsi("select tgl_registrasi from reg_periksa where no_rawat=?", norawat.getText()),
                                        Sequel.cariIsi("select jam_reg from reg_periksa where no_rawat=?", norawat.getText()));
                                billing.reseppulang.inputresep.tampil();
                                billing.reseppulang.inputresep.setSize(internalFrame1.getWidth() - 40, internalFrame1.getHeight() - 40);
                                billing.reseppulang.inputresep.setLocationRelativeTo(internalFrame1);
                                billing.reseppulang.inputresep.setVisible(true);
                            } else {
                                JOptionPane.showMessageDialog(rootPane, "Data billing sudah terverifikasi, data tidak boleh dihapus/diubah. Silahkan hubungi bagian kasir/keuangan ..!!");
                            }
                        } else {
                            billing.reseppulang.inputresep.isCek();
                            billing.reseppulang.inputresep.setNoRm(rs2.getString("no_rawat2"), "-", Sequel.cariIsi("select tgl_registrasi from reg_periksa where no_rawat=?", norawat.getText()),
                                    Sequel.cariIsi("select jam_reg from reg_periksa where no_rawat=?", norawat.getText()));
                            billing.reseppulang.inputresep.tampil();
                            billing.reseppulang.inputresep.setSize(internalFrame1.getWidth() - 40, internalFrame1.getHeight() - 40);
                            billing.reseppulang.inputresep.setLocationRelativeTo(internalFrame1);
                            billing.reseppulang.inputresep.setVisible(true);
                        }
                    } else {
                        JOptionPane.showMessageDialog(null, "Maaf, Silahkan anda pilih dulu pasien...!!!");
                        tbKamIn.requestFocus();
                    }
                } catch (Exception ex) {
                    System.out.println("Notifikasi : " + ex);
                } finally {
                    if (rs2 != null) {
                        rs2.close();
                    }
                    if (psanak != null) {
                        psanak.close();
                    }
                }
            } catch (Exception e) {
                System.out.println(e);
            }
        } else {
            if (Sequel.cariRegistrasi(tbKamIn.getValueAt(tbKamIn.getSelectedRow(), 0).toString()) > 0) {
                if (var.getnamauser().equals("Admin Utama")) {
                    billing.reseppulang.inputresep.isCek();
                    billing.reseppulang.inputresep.setNoRm(norawat.getText(), "-", Sequel.cariIsi("select tgl_registrasi from reg_periksa where no_rawat=?", norawat.getText()),
                            Sequel.cariIsi("select jam_reg from reg_periksa where no_rawat=?", norawat.getText()));
                    billing.reseppulang.inputresep.tampil();
                    billing.reseppulang.inputresep.setSize(internalFrame1.getWidth() - 40, internalFrame1.getHeight() - 40);
                    billing.reseppulang.inputresep.setLocationRelativeTo(internalFrame1);
                    billing.reseppulang.inputresep.setVisible(true);
                } else {
                    JOptionPane.showMessageDialog(rootPane, "Data billing sudah terverifikasi, data tidak boleh dihapus/diubah. Silahkan hubungi bagian kasir/keuangan ..!!");
                }
            } else {
                billing.reseppulang.inputresep.isCek();
                billing.reseppulang.inputresep.setNoRm(norawat.getText(), "-", Sequel.cariIsi("select tgl_registrasi from reg_periksa where no_rawat=?", norawat.getText()),
                        Sequel.cariIsi("select jam_reg from reg_periksa where no_rawat=?", norawat.getText()));
                billing.reseppulang.inputresep.tampil();
                billing.reseppulang.inputresep.setSize(internalFrame1.getWidth() - 40, internalFrame1.getHeight() - 40);
                billing.reseppulang.inputresep.setLocationRelativeTo(internalFrame1);
                billing.reseppulang.inputresep.setVisible(true);
            }
        }
    }//GEN-LAST:event_MnInputResepActionPerformed

    private void MnPeriksaRadiologiActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_MnPeriksaRadiologiActionPerformed
        if (tabMode.getRowCount() == 0) {
            JOptionPane.showMessageDialog(null, "Maaf, tabel masih kosong...!!!!");
            TCari.requestFocus();
        } else if (TPasien.getText().trim().equals("")) {
            try {
                psanak = koneksi.prepareStatement(
                        "select pasien.no_rkm_medis,pasien.nm_pasien,ranap_gabung.no_rawat2,concat(reg_periksa.umurdaftar,' ',reg_periksa.sttsumur)as umur,pasien.no_peserta, "
                        + "concat(pasien.alamatpj,', ',pasien.kelurahanpj,', ',pasien.kecamatanpj,', ',pasien.kabupatenpj) as alamat "
                        + "from reg_periksa inner join pasien inner join ranap_gabung on "
                        + "pasien.no_rkm_medis=reg_periksa.no_rkm_medis and ranap_gabung.no_rawat2=reg_periksa.no_rawat where ranap_gabung.no_rawat=?");
                try {
                    psanak.setString(1, tbKamIn.getValueAt(tbKamIn.getSelectedRow(), 0).toString());
                    rs2 = psanak.executeQuery();
                    if (rs2.next()) {
                        if (Sequel.cariRegistrasi(rs2.getString("no_rawat2")) > 0) {
                            if (var.getnamauser().equals("Admin Utama")) {
                                billing.periksarad.setSize(internalFrame1.getWidth() - 40, internalFrame1.getHeight() - 40);
                                billing.periksarad.setLocationRelativeTo(internalFrame1);
                                billing.periksarad.emptTeks();
                                billing.periksarad.setNoRm(rs2.getString("no_rawat2"), "Ranap");
                                billing.periksarad.tampil();
                                billing.periksarad.isCek();
                                billing.periksarad.setVisible(true);
                                billing.periksarad.fokus_kursor();
                            } else {
                                JOptionPane.showMessageDialog(rootPane, "Data billing sudah terverifikasi, data tidak boleh dihapus/diubah. Silahkan hubungi bagian kasir/keuangan ..!!");
                            }
                            JOptionPane.showMessageDialog(rootPane, "Data billing sudah terverifikasi, data tidak boleh dihapus/diubah. Silahkan hubungi bagian kasir/keuangan ..!!");
                        } else {
                            billing.periksarad.setSize(internalFrame1.getWidth() - 40, internalFrame1.getHeight() - 40);
                            billing.periksarad.setLocationRelativeTo(internalFrame1);
                            billing.periksarad.emptTeks();
                            billing.periksarad.setNoRm(rs2.getString("no_rawat2"), "Ranap");
                            billing.periksarad.tampil();
                            billing.periksarad.isCek();
                            billing.periksarad.setVisible(true);
                            billing.periksarad.fokus_kursor();
                        }
                    } else {
                        JOptionPane.showMessageDialog(null, "Maaf, Silahkan anda pilih dulu pasien...!!!");
                        tbKamIn.requestFocus();
                    }
                } catch (Exception ex) {
                    System.out.println("Notifikasi : " + ex);
                } finally {
                    if (rs2 != null) {
                        rs2.close();
                    }
                    if (psanak != null) {
                        psanak.close();
                    }
                }
            } catch (Exception e) {
                System.out.println(e);
            }
        } else {
            if (Sequel.cariRegistrasi(tbKamIn.getValueAt(tbKamIn.getSelectedRow(), 0).toString()) > 0) {
                if (var.getnamauser().equals("Admin Utama")) {
                    billing.periksarad.setSize(internalFrame1.getWidth(), internalFrame1.getHeight());
                    billing.periksarad.setLocationRelativeTo(internalFrame1);
                    billing.periksarad.emptTeks();
                    billing.periksarad.setNoRm(norawat.getText(), "Ranap");
                    billing.periksarad.tampil();
                    billing.periksarad.isCek();
                    billing.periksarad.setVisible(true);
                    billing.periksarad.fokus_kursor();
                } else {
                    JOptionPane.showMessageDialog(rootPane, "Data billing sudah terverifikasi, data tidak boleh dihapus/diubah. Silahkan hubungi bagian kasir/keuangan ..!!");
                }
            } else {
                billing.periksarad.setSize(internalFrame1.getWidth(), internalFrame1.getHeight());
                billing.periksarad.setLocationRelativeTo(internalFrame1);
                billing.periksarad.emptTeks();
                billing.periksarad.setNoRm(norawat.getText(), "Ranap");
                billing.periksarad.tampil();
                billing.periksarad.isCek();
                billing.periksarad.setVisible(true);
                billing.periksarad.fokus_kursor();
            }
        }
    }//GEN-LAST:event_MnPeriksaRadiologiActionPerformed

    private void WindowInputKamarWindowActivated(java.awt.event.WindowEvent evt) {//GEN-FIRST:event_WindowInputKamarWindowActivated
        var.setstatus(false);
    }//GEN-LAST:event_WindowInputKamarWindowActivated

    private void WindowPindahKamarWindowActivated(java.awt.event.WindowEvent evt) {//GEN-FIRST:event_WindowPindahKamarWindowActivated
        var.setstatus(false);
    }//GEN-LAST:event_WindowPindahKamarWindowActivated

    private void MnTilikBedahActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_MnTilikBedahActionPerformed
        if (tabMode.getRowCount() == 0) {
            JOptionPane.showMessageDialog(null, "Maaf, tabel masih kosong...!!!!");
            TCari.requestFocus();
        } else if (TPasien.getText().trim().equals("")) {
            try {
                psanak = koneksi.prepareStatement(
                        "select pasien.no_rkm_medis,pasien.nm_pasien,ranap_gabung.no_rawat2,concat(reg_periksa.umurdaftar,' ',reg_periksa.sttsumur)as umur,pasien.no_peserta, "
                        + "concat(pasien.alamatpj,', ',pasien.kelurahanpj,', ',pasien.kecamatanpj,', ',pasien.kabupatenpj) as alamat "
                        + "from reg_periksa inner join pasien inner join ranap_gabung on "
                        + "pasien.no_rkm_medis=reg_periksa.no_rkm_medis and ranap_gabung.no_rawat2=reg_periksa.no_rawat where ranap_gabung.no_rawat=?");
                try {
                    psanak.setString(1, tbKamIn.getValueAt(tbKamIn.getSelectedRow(), 0).toString());
                    rs2 = psanak.executeQuery();
                    if (rs2.next()) {
                        Map<String, Object> param = new HashMap<>();
                        param.put("namars", var.getnamars());
                        param.put("alamatrs", var.getalamatrs());
                        param.put("kotars", var.getkabupatenrs());
                        param.put("propinsirs", var.getpropinsirs());
                        param.put("kontakrs", var.getkontakrs());
                        param.put("emailrs", var.getemailrs());
                        param.put("logo", Sequel.cariGambar("select logo from setting"));
                        param.put("nama", rs2.getString("nm_pasien"));
                        param.put("jkel", Sequel.cariIsi("select if(pasien.jk='L','Laki-Laki','Perempuan') from pasien where pasien.no_rkm_medis=?", rs2.getString("no_rkm_medis")));
                        param.put("lahir", Sequel.cariIsi("select DATE_FORMAT(pasien.tgl_lahir,'%d-%m-%Y') from pasien where pasien.no_rkm_medis=?", rs2.getString("no_rkm_medis")));
                        param.put("norm", rs2.getString("no_rkm_medis"));
                        param.put("ruang", tbKamIn.getValueAt(tbKamIn.getSelectedRow(), 7).toString());
                        param.put("kelas", Sequel.cariIsi("select kamar.kelas from kamar inner join bangsal on kamar.kd_bangsal=bangsal.kd_bangsal "
                                + "where kamar.kd_kamar=? ", tbKamIn.getValueAt(tbKamIn.getSelectedRow(), 7).toString()));

                        Valid.MyReport("rptTilikBedah.jrxml", "report", "::[ Lembar Tilik Bedah ]::",
                                "select current_date() as tanggal, current_time() as jam", param);
                    } else {
                        JOptionPane.showMessageDialog(null, "Maaf, Silahkan anda pilih dulu pasien...!!!");
                        tbKamIn.requestFocus();
                    }
                } catch (Exception ex) {
                    System.out.println("Notifikasi : " + ex);
                } finally {
                    if (rs2 != null) {
                        rs2.close();
                    }
                    if (psanak != null) {
                        psanak.close();
                    }
                }
            } catch (Exception e) {
                System.out.println(e);
            }
        } else {
            Map<String, Object> param = new HashMap<>();
            param.put("namars", var.getnamars());
            param.put("alamatrs", var.getalamatrs());
            param.put("kotars", var.getkabupatenrs());
            param.put("propinsirs", var.getpropinsirs());
            param.put("kontakrs", var.getkontakrs());
            param.put("emailrs", var.getemailrs());
            param.put("logo", Sequel.cariGambar("select logo from setting"));
            param.put("nama", TPasien.getText());
            param.put("jkel", Sequel.cariIsi("select if(pasien.jk='L','Laki-Laki','Perempuan') from pasien where pasien.no_rkm_medis=?", TNoRM.getText()));
            param.put("lahir", Sequel.cariIsi("select DATE_FORMAT(pasien.tgl_lahir,'%d-%m-%Y') from pasien where pasien.no_rkm_medis=?", TNoRM.getText()));
            param.put("norm", TNoRM.getText());
            param.put("ruang", tbKamIn.getValueAt(tbKamIn.getSelectedRow(), 7).toString());
            param.put("kelas", Sequel.cariIsi("select kamar.kelas from kamar inner join bangsal on kamar.kd_bangsal=bangsal.kd_bangsal "
                    + "where kamar.kd_kamar=? ", tbKamIn.getValueAt(tbKamIn.getSelectedRow(), 7).toString()));

            Valid.MyReport("rptTilikBedah.jrxml", "report", "::[ Lembar Tilik Bedah ]::",
                    "select current_date() as tanggal, current_time() as jam", param);
        }
    }//GEN-LAST:event_MnTilikBedahActionPerformed

    private void MnUpdateHariActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_MnUpdateHariActionPerformed
        if (tabMode.getRowCount() == 0) {
            JOptionPane.showMessageDialog(null, "Maaf, tabel masih kosong...!!!!");
            TCari.requestFocus();
        } else if (R1.isSelected() == false) {
            JOptionPane.showMessageDialog(rootPane, "Tampilkan data yang belum pulang terlebih dahulu");
        } else {
            updateHari();
            tampil();
        }
    }//GEN-LAST:event_MnUpdateHariActionPerformed

    private void MnAsuhanGiziActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_MnAsuhanGiziActionPerformed
        if (tabMode.getRowCount() == 0) {
            JOptionPane.showMessageDialog(null, "Maaf, tabel masih kosong...!!!!");
            TCari.requestFocus();
        } else if (TPasien.getText().trim().equals("")) {
            try {
                psanak = koneksi.prepareStatement(
                        "select pasien.no_rkm_medis,pasien.nm_pasien,ranap_gabung.no_rawat2,concat(reg_periksa.umurdaftar,' ',reg_periksa.sttsumur)as umur,pasien.no_peserta, "
                        + "concat(pasien.alamatpj,', ',pasien.kelurahanpj,', ',pasien.kecamatanpj,', ',pasien.kabupatenpj) as alamat "
                        + "from reg_periksa inner join pasien inner join ranap_gabung on "
                        + "pasien.no_rkm_medis=reg_periksa.no_rkm_medis and ranap_gabung.no_rawat2=reg_periksa.no_rawat where ranap_gabung.no_rawat=?");

                try {
                    psanak.setString(1, tbKamIn.getValueAt(tbKamIn.getSelectedRow(), 0).toString());
                    rs2 = psanak.executeQuery();
                    if (rs2.next()) {
                        this.setCursor(Cursor.getPredefinedCursor(Cursor.WAIT_CURSOR));
                        Map<String, Object> param = new HashMap<>();
                        param.put("nama", rs2.getString("nm_pasien"));
                        param.put("jkel", Sequel.cariIsi("select if(pasien.jk='L','Laki-Laki','Perempuan') from pasien where pasien.no_rkm_medis=?", rs2.getString("no_rkm_medis")));
                        param.put("lahir", Sequel.cariIsi("select DATE_FORMAT(pasien.tgl_lahir,'%d-%m-%Y') from pasien where pasien.no_rkm_medis=?", rs2.getString("no_rkm_medis")));
                        param.put("norm", rs2.getString("no_rkm_medis"));
                        param.put("ruang", tbKamIn.getValueAt(tbKamIn.getSelectedRow(), 7).toString());
                        param.put("namars", var.getnamars());
                        param.put("alamatrs", var.getalamatrs());
                        param.put("kotars", var.getkabupatenrs());
                        param.put("propinsirs", var.getpropinsirs());
                        param.put("kontakrs", var.getkontakrs());
                        param.put("emailrs", var.getemailrs());
                        param.put("logo", Sequel.cariGambar("select logo from setting"));
                        param.put("kelas", Sequel.cariIsi("select kamar.kelas from kamar inner join bangsal on kamar.kd_bangsal=bangsal.kd_bangsal "
                                + "where kamar.kd_kamar=? ", tbKamIn.getValueAt(tbKamIn.getSelectedRow(), 7).toString()));
                        Valid.MyReport("rptAssesmentGizi.jrxml", "report", "::[ Lembar Asuhan Gizi ]::",
                                "select current_date() as tanggal, current_time() as jam", param);
                        this.setCursor(Cursor.getDefaultCursor());
                    } else {
                        JOptionPane.showMessageDialog(null, "Maaf, Silahkan anda pilih dulu pasien...!!!");
                        tbKamIn.requestFocus();
                    }
                } catch (Exception ex) {
                    System.out.println("Notifikasi : " + ex);
                } finally {
                    if (rs2 != null) {
                        rs2.close();
                    }
                    if (psanak != null) {
                        psanak.close();
                    }
                }
            } catch (Exception e) {
                System.out.println(e);
            }
        } else {
            this.setCursor(Cursor.getPredefinedCursor(Cursor.WAIT_CURSOR));
            Map<String, Object> param = new HashMap<>();
            param.put("nama", TPasien.getText());
            param.put("jkel", Sequel.cariIsi("select if(pasien.jk='L','Laki-Laki','Perempuan') from pasien where pasien.no_rkm_medis=?", TNoRM.getText()));
            param.put("lahir", Sequel.cariIsi("select DATE_FORMAT(pasien.tgl_lahir,'%d-%m-%Y') from pasien where pasien.no_rkm_medis=?", TNoRM.getText()));
            param.put("norm", TNoRM.getText());
            param.put("ruang", tbKamIn.getValueAt(tbKamIn.getSelectedRow(), 7).toString());
            param.put("namars", var.getnamars());
            param.put("alamatrs", var.getalamatrs());
            param.put("kotars", var.getkabupatenrs());
            param.put("propinsirs", var.getpropinsirs());
            param.put("kontakrs", var.getkontakrs());
            param.put("emailrs", var.getemailrs());
            param.put("logo", Sequel.cariGambar("select logo from setting"));
            param.put("kelas", Sequel.cariIsi("select kamar.kelas from kamar inner join bangsal on kamar.kd_bangsal=bangsal.kd_bangsal "
                    + "where kamar.kd_kamar=? ", tbKamIn.getValueAt(tbKamIn.getSelectedRow(), 7).toString()));
            Valid.MyReport("rptAssesmentGizi.jrxml", "report", "::[ Lembar Asuhan Gizi ]::",
                    "select current_date() as tanggal, current_time() as jam", param);
            this.setCursor(Cursor.getDefaultCursor());
        }
    }//GEN-LAST:event_MnAsuhanGiziActionPerformed

    private void btnDiagnosaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnDiagnosaActionPerformed
        if (norawat.getText().trim().equals("")) {
            JOptionPane.showMessageDialog(null, "Maaf, Silahkan anda pilih dulu pasien...!!!");
            tbKamIn.requestFocus();
        } else {
            var.setform("DlgKamarInap");
            diagnosa.setSize(internalFrame1.getWidth(), internalFrame1.getHeight());
            diagnosa.setLocationRelativeTo(internalFrame1);
            diagnosa.isCek();
            try {
                date = new SimpleDateFormat("yyyy-MM-dd").parse(TIn.getText());
            } catch (Exception e) {
                date = DTPCari2.getDate();
            }
            diagnosa.setNoRm(norawat.getText(), date, DTPCari2.getDate(), "Ranap");
//            diagnosa.setNoRm(norawat.getText(), DTPCari1.getDate(), DTPCari2.getDate(), "Ranap");
            diagnosa.tampil();
            diagnosa.setVisible(true);
        }
    }//GEN-LAST:event_btnDiagnosaActionPerformed

    private void btnDiagnosaKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_btnDiagnosaKeyPressed
        // TODO add your handling code here:
    }//GEN-LAST:event_btnDiagnosaKeyPressed

    private void BtnCloseGabungActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_BtnCloseGabungActionPerformed
        NoRawatGabung.setText("");
        NoRmBayi.setText("");
        NmBayi.setText("");
        WindowRanapGabung.dispose();
    }//GEN-LAST:event_BtnCloseGabungActionPerformed

    private void BtnSimpanGabungActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_BtnSimpanGabungActionPerformed
        if (norawat.getText().trim().equals("")) {
            Valid.textKosong(NoRmBayi, "Pasien");
        } else {
            try {
                psibu = koneksi.prepareStatement("select no_reg,tgl_registrasi,jam_reg,kd_dokter,no_rkm_medis,kd_poli,p_jawab,"
                        + "almt_pj,hubunganpj,biaya_reg,stts,stts_daftar,status_lanjut,kd_pj from reg_periksa where no_rawat=?");
                try {
                    psibu.setString(1, norawat.getText());
                    rs = psibu.executeQuery();
                    if (rs.next()) {
                        pscariumur = koneksi.prepareStatement(
                                "select TIMESTAMPDIFF(YEAR, tgl_lahir, CURDATE()) as tahun, "
                                + "(TIMESTAMPDIFF(MONTH, tgl_lahir, CURDATE()) - ((TIMESTAMPDIFF(MONTH, tgl_lahir, CURDATE()) div 12) * 12)) as bulan, "
                                + "TIMESTAMPDIFF(DAY, DATE_ADD(DATE_ADD(tgl_lahir,INTERVAL TIMESTAMPDIFF(YEAR, tgl_lahir, CURDATE()) YEAR), INTERVAL TIMESTAMPDIFF(MONTH, tgl_lahir, CURDATE()) - ((TIMESTAMPDIFF(MONTH, tgl_lahir, CURDATE()) div 12) * 12) MONTH), CURDATE()) as hari "
                                + "from pasien where no_rkm_medis=?");
                        try {
                            pscariumur.setString(1, NoRmBayi.getText());
                            rs2 = pscariumur.executeQuery();
                            if (rs2.next()) {
                                umur = "0";
                                sttsumur = "Th";
                                if (rs2.getInt("tahun") > 0) {
                                    umur = rs2.getString("tahun");
                                    sttsumur = "Th";
                                } else if (rs2.getInt("tahun") == 0) {
                                    if (rs2.getInt("bulan") > 0) {
                                        umur = rs2.getString("bulan");
                                        sttsumur = "Bl";
                                    } else if (rs2.getInt("bulan") == 0) {
                                        umur = rs2.getString("hari");
                                        sttsumur = "Hr";
                                    }
                                }
                            }
                        } catch (Exception e) {
                            System.out.println("Notifikasi Umur : " + e);
                        } finally {
                            if (rs2 != null) {
                                rs2.close();
                            }
                            if (pscariumur != null) {
                                pscariumur.close();
                            }
                        }
                        Valid.autoNomer3("select (ifnull(MAX(CONVERT(RIGHT(no_rawat,6),signed)),0)+1) from reg_periksa where tgl_registrasi='" + rs.getString("tgl_registrasi") + "' ", dateformat.format(rs.getDate("tgl_registrasi")) + "/", 6, NoRawatGabung);
                        if (Sequel.menyimpantf2("reg_periksa", "?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?", "Reg Periksa", 17,
                                new String[]{rs.getString("no_reg"), NoRawatGabung.getText(), rs.getString("tgl_registrasi"), rs.getString("jam_reg"),
                                    rs.getString("kd_dokter"), NoRmBayi.getText(), rs.getString("kd_poli"), rs.getString("p_jawab"),
                                    rs.getString("almt_pj"), rs.getString("hubunganpj"), rs.getString("biaya_reg"), "Belum", "Baru", "Ranap", rs.getString("kd_pj"), umur, sttsumur}) == true) {
                            Sequel.menyimpan("ranap_gabung", "?,?", "Data Ranap Gabung", 2, new String[]{
                                norawat.getText(), NoRawatGabung.getText()
                            });
                        }
                    }
                } catch (Exception ex) {
                    System.out.println("Notifikasi : " + ex);
                } finally {
                    if (rs != null) {
                        rs.close();
                    }
                    if (psibu != null) {
                        psibu.close();
                    }
                }

                tampil();
            } catch (Exception e) {
                System.out.println(e);
            }
            NoRawatGabung.setText("");
            NoRmBayi.setText("");
            NmBayi.setText("");
            WindowRanapGabung.dispose();
        }
    }//GEN-LAST:event_BtnSimpanGabungActionPerformed

    private void NoRmBayiKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_NoRmBayiKeyPressed
        if (evt.getKeyCode() == KeyEvent.VK_ENTER) {
            Sequel.cariIsi("select nm_pasien from pasien where no_rkm_medis=?", NmBayi, NoRmBayi.getText());
        }
    }//GEN-LAST:event_NoRmBayiKeyPressed

    private void btnPasienRanapGabungActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnPasienRanapGabungActionPerformed
        var.setform("DlgKamarInap");
        reg.pasien.emptTeks();
        reg.pasien.isCek();
        reg.pasien.setSize(internalFrame1.getWidth() - 40, internalFrame1.getHeight() - 40);
        reg.pasien.setLocationRelativeTo(internalFrame1);
        reg.pasien.setVisible(true);
    }//GEN-LAST:event_btnPasienRanapGabungActionPerformed

    private void BtnHapusGabungActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_BtnHapusGabungActionPerformed
        Sequel.meghapus("ranap_gabung", "no_rawat", norawat.getText());
        NoRawatGabung.setText("");
        NoRmBayi.setText("");
        NmBayi.setText("");
        tampil();
    }//GEN-LAST:event_BtnHapusGabungActionPerformed

    private void MnDiagnosaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_MnDiagnosaActionPerformed
        if (tabMode.getRowCount() == 0) {
            JOptionPane.showMessageDialog(null, "Maaf, tabel masih kosong...!!!!");
            TCari.requestFocus();
        } else if (TPasien.getText().trim().equals("")) {
            try {
                psanak = koneksi.prepareStatement(
                        "select pasien.no_rkm_medis,pasien.nm_pasien,ranap_gabung.no_rawat2,concat(reg_periksa.umurdaftar,' ',reg_periksa.sttsumur)as umur,pasien.no_peserta, "
                        + "concat(pasien.alamatpj,', ',pasien.kelurahanpj,', ',pasien.kecamatanpj,', ',pasien.kabupatenpj) as alamat "
                        + "from reg_periksa inner join pasien inner join ranap_gabung on "
                        + "pasien.no_rkm_medis=reg_periksa.no_rkm_medis and ranap_gabung.no_rawat2=reg_periksa.no_rawat where ranap_gabung.no_rawat=?");

                try {
                    psanak.setString(1, tbKamIn.getValueAt(tbKamIn.getSelectedRow(), 0).toString());
                    rs2 = psanak.executeQuery();
                    if (rs2.next()) {
                        var.setform("DlgKamarInap");
                        diagnosa.setSize(internalFrame1.getWidth(), internalFrame1.getHeight());
                        diagnosa.setLocationRelativeTo(internalFrame1);
                        diagnosa.isCek();
                        try {
                            date = new SimpleDateFormat("yyyy-MM-dd").parse(TIn.getText());
                        } catch (Exception e) {
                            date = DTPCari2.getDate();
                        }
                        diagnosa.setNoRm(rs2.getString("no_rawat2"), date, DTPCari2.getDate(), "Ranap");
                        diagnosa.tampil();
                        diagnosa.setVisible(true);
                    } else {
                        JOptionPane.showMessageDialog(null, "Maaf, Silahkan anda pilih dulu pasien...!!!");
                        tbKamIn.requestFocus();
                    }
                } catch (Exception ex) {
                    System.out.println("Notifikasi : " + ex);
                } finally {
                    if (rs2 != null) {
                        rs2.close();
                    }
                    if (psanak != null) {
                        psanak.close();
                    }
                }
            } catch (Exception e) {
                System.out.println(e);
            }
        } else {
            var.setform("DlgKamarInap");
            diagnosa.setSize(internalFrame1.getWidth(), internalFrame1.getHeight());
            diagnosa.setLocationRelativeTo(internalFrame1);
            diagnosa.isCek();
            try {
                date = new SimpleDateFormat("yyyy-MM-dd").parse(TIn.getText());
            } catch (Exception e) {
                date = DTPCari2.getDate();
            }
            diagnosa.setNoRm(norawat.getText(), date, DTPCari2.getDate(), "Ranap");
            diagnosa.tampil();
            diagnosa.setVisible(true);
        }
    }//GEN-LAST:event_MnDiagnosaActionPerformed

    private void DTPCari4ItemStateChanged(java.awt.event.ItemEvent evt) {//GEN-FIRST:event_DTPCari4ItemStateChanged
        R3.setSelected(true);
    }//GEN-LAST:event_DTPCari4ItemStateChanged

    private void DTPCari4KeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_DTPCari4KeyPressed
        // TODO add your handling code here:
    }//GEN-LAST:event_DTPCari4KeyPressed

    private void MnDPJPActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_MnDPJPActionPerformed
        if (tabMode.getRowCount() == 0) {
            JOptionPane.showMessageDialog(null, "Maaf, tabel masih kosong...!!!!");
            TCari.requestFocus();
        } else if (TPasien.getText().trim().equals("")) {
            try {
                psanak = koneksi.prepareStatement(
                        "select pasien.no_rkm_medis,pasien.nm_pasien,ranap_gabung.no_rawat2,concat(reg_periksa.umurdaftar,' ',reg_periksa.sttsumur)as umur,pasien.no_peserta, "
                        + "concat(pasien.alamatpj,', ',pasien.kelurahanpj,', ',pasien.kecamatanpj,', ',pasien.kabupatenpj) as alamat "
                        + "from reg_periksa inner join pasien inner join ranap_gabung on "
                        + "pasien.no_rkm_medis=reg_periksa.no_rkm_medis and ranap_gabung.no_rawat2=reg_periksa.no_rawat where ranap_gabung.no_rawat=?");

                try {
                    psanak.setString(1, tbKamIn.getValueAt(tbKamIn.getSelectedRow(), 0).toString());
                    rs2 = psanak.executeQuery();
                    if (rs2.next()) {
                        DlgDpjp dpjp = new DlgDpjp(null, false);
                        dpjp.setSize(internalFrame1.getWidth(), internalFrame1.getHeight());
                        dpjp.setLocationRelativeTo(internalFrame1);
                        dpjp.isCek();
//                        try {
//                            date = new SimpleDateFormat("yyyy-MM-dd").parse(TIn.getText());
//                        } catch (Exception e) {
//                            date = DTPCari2.getDate();
//                        }

                        dpjp.setNoRm(rs2.getString("no_rawat2"), TIn.getText());
                        dpjp.tampil();
                        dpjp.setVisible(true);
                    } else {
                        JOptionPane.showMessageDialog(null, "Maaf, Silahkan anda pilih dulu pasien...!!!");
                        tbKamIn.requestFocus();
                    }
                } catch (Exception ex) {
                    System.out.println("Notifikasi : " + ex);
                } finally {
                    if (rs2 != null) {
                        rs2.close();
                    }
                    if (psanak != null) {
                        psanak.close();
                    }
                }
            } catch (Exception e) {
                System.out.println(e);
            }
        } else {
            DlgDpjp dpjp = new DlgDpjp(null, false);
            dpjp.setSize(internalFrame1.getWidth(), internalFrame1.getHeight());
            dpjp.setLocationRelativeTo(internalFrame1);
            dpjp.isCek();
//            try {
//                date = new SimpleDateFormat("yyyy-MM-dd").parse(TIn.getText());
//            } catch (Exception e) {
//                date = DTPCari2.getDate();
//            }

            dpjp.setNoRm(norawat.getText(), TIn.getText());
            dpjp.tampil();
            dpjp.setVisible(true);
        }
    }//GEN-LAST:event_MnDPJPActionPerformed

    private void MnLabelPxRanap1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_MnLabelPxRanap1ActionPerformed
        if (tabMode.getRowCount() == 0) {
            JOptionPane.showMessageDialog(null, "Maaf, tabel masih kosong...!!!!");
            TCari.requestFocus();
        } else if (noRMCopy.getText().equals("")) {
            JOptionPane.showMessageDialog(null, "Silahkan klik dulu salah satu data pasiennya pada tabel...!!!!");
            tbKamIn.requestFocus();
        } else if (R1.isSelected() == false) {
            JOptionPane.showMessageDialog(null, "Label pasien hanya bisa dicetak utk. yang masih dirawat inap saja...!!!!");
        } else {
            Valid.MyReport("rptLabelPxRanap.jrxml", "report", "::[ Label Pasien (3,9 x 1,9 Cm) ]::",
                    "select no_rkm_medis, concat(nm_pasien,' (',if(jk='L','LK','PR'),')') nm_pasien, "
                    + "date_format(tgl_lahir,'%d-%m-%Y') tgl_lhr from pasien where no_rkm_medis='" + noRMCopy.getText() + "'");
        }
    }//GEN-LAST:event_MnLabelPxRanap1ActionPerformed

    private void MnReturJualActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_MnReturJualActionPerformed
        this.setCursor(Cursor.getPredefinedCursor(Cursor.WAIT_CURSOR));
        if (tabMode.getRowCount() == 0) {
            JOptionPane.showMessageDialog(null, "Maaf, tabel masih kosong...!!!!");
            TCari.requestFocus();
        } else if (TPasien.getText().trim().equals("")) {
            try {
                psanak = koneksi.prepareStatement(
                        "select pasien.no_rkm_medis,pasien.nm_pasien,ranap_gabung.no_rawat2,concat(reg_periksa.umurdaftar,' ',reg_periksa.sttsumur)as umur,pasien.no_peserta, "
                        + "concat(pasien.alamatpj,', ',pasien.kelurahanpj,', ',pasien.kecamatanpj,', ',pasien.kabupatenpj) as alamat "
                        + "from reg_periksa inner join pasien inner join ranap_gabung on "
                        + "pasien.no_rkm_medis=reg_periksa.no_rkm_medis and ranap_gabung.no_rawat2=reg_periksa.no_rawat where ranap_gabung.no_rawat=?");

                try {
                    psanak.setString(1, tbKamIn.getValueAt(tbKamIn.getSelectedRow(), 0).toString());
                    rs2 = psanak.executeQuery();
                    if (rs2.next()) {
                        if (Sequel.cariRegistrasi(rs2.getString("no_rawat2")) > 0) {
                            JOptionPane.showMessageDialog(rootPane, "Data billing sudah terverifikasi, data tidak boleh dihapus/diubah. Silahkan hubungi bagian kasir/keuangan ..!!");
                        } else {
                            var.setform("DlgKamarInap");
                            DlgReturJual returjual = new DlgReturJual(null, false);
                            returjual.emptTeks();
                            returjual.isCek();
                            returjual.setPasien(tbKamIn.getValueAt(tbKamIn.getSelectedRow(), 1).toString(), rs2.getString("no_rawat2"));
                            returjual.setSize(internalFrame1.getWidth(), internalFrame1.getHeight());
                            returjual.setLocationRelativeTo(internalFrame1);
                            returjual.setVisible(true);
                        }
                    } else {
                        JOptionPane.showMessageDialog(null, "Maaf, Silahkan anda pilih dulu pasien...!!!");
                        tbKamIn.requestFocus();
                    }
                } catch (Exception ex) {
                    System.out.println("Notifikasi : " + ex);
                } finally {
                    if (rs2 != null) {
                        rs2.close();
                    }
                    if (psanak != null) {
                        psanak.close();
                    }
                }
            } catch (Exception e) {
                System.out.println(e);
            }
        } else {
            if (Sequel.cariRegistrasi(tbKamIn.getValueAt(tbKamIn.getSelectedRow(), 0).toString()) > 0) {
                JOptionPane.showMessageDialog(rootPane, "Data billing sudah terverifikasi, data tidak boleh dihapus/diubah. Silahkan hubungi bagian kasir/keuangan ..!!");
            } else {
                var.setform("DlgKamarInap");
                DlgReturJual returjual = new DlgReturJual(null, false);
                returjual.emptTeks();
                returjual.isCek();
                returjual.setPasien(tbKamIn.getValueAt(tbKamIn.getSelectedRow(), 1).toString(), norawat.getText());
                returjual.setSize(internalFrame1.getWidth(), internalFrame1.getHeight());
                returjual.setLocationRelativeTo(internalFrame1);
                returjual.setVisible(true);
            }

        }
        this.setCursor(Cursor.getDefaultCursor());
    }//GEN-LAST:event_MnReturJualActionPerformed

    private void MnRincianObatActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_MnRincianObatActionPerformed
        if (tabMode.getRowCount() == 0) {
            JOptionPane.showMessageDialog(null, "Maaf, tabel masih kosong...!!!!");
            TCari.requestFocus();
        } else if (TPasien.getText().trim().equals("")) {
            try {
                psanak = koneksi.prepareStatement(
                        "select pasien.no_rkm_medis,pasien.nm_pasien,ranap_gabung.no_rawat2,concat(reg_periksa.umurdaftar,' ',reg_periksa.sttsumur)as umur,pasien.no_peserta, "
                        + "concat(pasien.alamatpj,', ',pasien.kelurahanpj,', ',pasien.kecamatanpj,', ',pasien.kabupatenpj) as alamat "
                        + "from reg_periksa inner join pasien inner join ranap_gabung on "
                        + "pasien.no_rkm_medis=reg_periksa.no_rkm_medis and ranap_gabung.no_rawat2=reg_periksa.no_rawat where ranap_gabung.no_rawat=?");

                try {
                    psanak.setString(1, tbKamIn.getValueAt(tbKamIn.getSelectedRow(), 0).toString());
                    rs2 = psanak.executeQuery();
                    if (rs2.next()) {
                        this.setCursor(Cursor.getPredefinedCursor(Cursor.WAIT_CURSOR));
                        Map<String, Object> param = new HashMap<>();
                        param.put("namapasien", rs2.getString("nm_pasien"));
                        param.put("norm", rs2.getString("no_rkm_medis"));
                        param.put("nokartu", rs2.getString("no_peserta"));
                        param.put("umur", rs2.getString("umur"));
                        param.put("alamatpasien", rs2.getString("alamat"));
                        param.put("ruang", tbKamIn.getValueAt(tbKamIn.getSelectedRow(), 8).toString().replaceAll("  ", " "));
                        param.put("namars", var.getnamars());
                        param.put("alamatrs", var.getalamatrs());
                        param.put("kotars", var.getkabupatenrs());
                        param.put("propinsirs", var.getpropinsirs());
                        param.put("kontakrs", var.getkontakrs());
                        param.put("emailrs", var.getemailrs());
                        param.put("logo", Sequel.cariGambar("select logo from setting"));
                        Valid.MyReport("rptRincianRiwayatObat.jrxml", "report", "::[ Rincian Penggunaan Obat ]::",
                                "select * from temporary", param);
                        this.setCursor(Cursor.getDefaultCursor());
                    } else {
                        JOptionPane.showMessageDialog(null, "Maaf, Silahkan anda pilih dulu pasien...!!!");
                        tbKamIn.requestFocus();
                    }
                } catch (Exception ex) {
                    System.out.println("Notifikasi : " + ex);
                } finally {
                    if (rs2 != null) {
                        rs2.close();
                    }
                    if (psanak != null) {
                        psanak.close();
                    }
                }
            } catch (Exception e) {
                System.out.println(e);
            }
        } else {
            this.setCursor(Cursor.getPredefinedCursor(Cursor.WAIT_CURSOR));
            Map<String, Object> param = new HashMap<>();
            param.put("namapasien", TPasien.getText());
            param.put("norm", TNoRM.getText());
            param.put("nokartu", Sequel.cariIsi("select no_peserta from pasien where no_rkm_medis=?", TNoRM.getText()));
            param.put("umur", Sequel.cariIsi("select umur from pasien where no_rkm_medis=?", TNoRM.getText()));
            param.put("alamatpasien", Sequel.cariIsi("select concat(pasien.alamatpj,', ',pasien.kelurahanpj,', ',pasien.kecamatanpj,', ',pasien.kabupatenpj) from pasien "
                    + "where no_rkm_medis=?", TNoRM.getText()));
            param.put("ruang", tbKamIn.getValueAt(tbKamIn.getSelectedRow(), 8).toString().replaceAll("  ", " "));
            param.put("namars", var.getnamars());
            param.put("alamatrs", var.getalamatrs());
            param.put("kotars", var.getkabupatenrs());
            param.put("propinsirs", var.getpropinsirs());
            param.put("kontakrs", var.getkontakrs());
            param.put("emailrs", var.getemailrs());
            param.put("logo", Sequel.cariGambar("select logo from setting"));
            Valid.MyReport("rptRincianRiwayatObat.jrxml", "report", "::[ Rincian Penggunaan Obat ]::",
                    "select * from temporary", param);
            this.setCursor(Cursor.getDefaultCursor());
        }
    }//GEN-LAST:event_MnRincianObatActionPerformed

    private void MnDPJPRanapActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_MnDPJPRanapActionPerformed
        tampilDPJP();
    }//GEN-LAST:event_MnDPJPRanapActionPerformed

    private void MnSEPActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_MnSEPActionPerformed
        if (tabMode.getRowCount() == 0) {
            JOptionPane.showMessageDialog(null, "Maaf, tabel masih kosong...!!!!");
            TCari.requestFocus();
        } else if (TPasien.getText().trim().equals("")) {
            try {
                psanak = koneksi.prepareStatement(
                        "select pasien.no_rkm_medis,pasien.nm_pasien,ranap_gabung.no_rawat2,concat(reg_periksa.umurdaftar,' ',reg_periksa.sttsumur)as umur,pasien.no_peserta, "
                        + "concat(pasien.alamatpj,', ',pasien.kelurahanpj,', ',pasien.kecamatanpj,', ',pasien.kabupatenpj) as alamat "
                        + "from reg_periksa inner join pasien inner join ranap_gabung on "
                        + "pasien.no_rkm_medis=reg_periksa.no_rkm_medis and ranap_gabung.no_rawat2=reg_periksa.no_rawat where ranap_gabung.no_rawat=?");
                try {
                    psanak.setString(1, tbKamIn.getValueAt(tbKamIn.getSelectedRow(), 0).toString());
                    rs2 = psanak.executeQuery();
                    if (rs2.next()) {
                        this.setCursor(Cursor.getPredefinedCursor(Cursor.WAIT_CURSOR));
                        var.setform("DlgKamarInap");
                        BPJSDataSEP dlgki = new BPJSDataSEP(null, false);
                        dlgki.setSize(internalFrame1.getWidth(), internalFrame1.getHeight());
                        dlgki.setLocationRelativeTo(internalFrame1);
                        dlgki.isCek();
                        dlgki.setNoRm(rs2.getString("no_rawat2"), Valid.SetTgl(tbKamIn.getValueAt(tbKamIn.getSelectedRow(), 12).toString()), "1. Ranap", "", "");
                        dlgki.tampil();
                        dlgki.setVisible(true);
                        dlgki.cekLAKA();
                        dlgki.cekLAYAN();
                        this.setCursor(Cursor.getDefaultCursor());
                    } else {
                        JOptionPane.showMessageDialog(null, "Maaf, Silahkan anda pilih dulu pasien...!!!");
                        tbKamIn.requestFocus();
                    }
                } catch (Exception ex) {
                    System.out.println("Notifikasi : " + ex);
                } finally {
                    if (rs2 != null) {
                        rs2.close();
                    }
                    if (psanak != null) {
                        psanak.close();
                    }
                }
            } catch (Exception e) {
                System.out.println(e);
            }
        } else {
            this.setCursor(Cursor.getPredefinedCursor(Cursor.WAIT_CURSOR));
            var.setform("DlgKamarInap");
            BPJSDataSEP dlgki = new BPJSDataSEP(null, false);
            dlgki.setSize(internalFrame1.getWidth(), internalFrame1.getHeight());
            dlgki.setLocationRelativeTo(internalFrame1);
            dlgki.isCek();
            dlgki.setNoRm(norawat.getText(), Valid.SetTgl(tbKamIn.getValueAt(tbKamIn.getSelectedRow(), 12).toString()), "1. Rawat Inap", "", "");
            dlgki.tampil();
            dlgki.setVisible(true);
            dlgki.cekLAKA();
            dlgki.cekLAYAN();
            this.setCursor(Cursor.getDefaultCursor());
        }
    }//GEN-LAST:event_MnSEPActionPerformed

    private void ppRiwayatBtnPrintActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_ppRiwayatBtnPrintActionPerformed
        if (tabMode.getRowCount() == 0) {
            JOptionPane.showMessageDialog(null, "Maaf, data pasien sudah habis...!!!!");
            TCari.requestFocus();
        } else if (tbKamIn.getSelectedRow() == -1) {
            JOptionPane.showMessageDialog(null, "Maaf, Silahkan anda pilih dulu data kamar inap pada tabel...!!!");
            TCari.requestFocus();
        } else {
            this.setCursor(Cursor.getPredefinedCursor(Cursor.WAIT_CURSOR));
            DlgResumePerawatan resume = new DlgResumePerawatan(null, true);
            resume.setNoRm(tbKamIn.getValueAt(tbKamIn.getSelectedRow(), 1).toString(), tbKamIn.getValueAt(tbKamIn.getSelectedRow(), 2).toString());
            resume.tampil();
            resume.setSize(internalFrame1.getWidth() - 40, internalFrame1.getHeight() - 40);
            resume.setLocationRelativeTo(internalFrame1);
            resume.setVisible(true);
            this.setCursor(Cursor.getDefaultCursor());
        }
    }//GEN-LAST:event_ppRiwayatBtnPrintActionPerformed

    private void ppCatatanPasienBtnPrintActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_ppCatatanPasienBtnPrintActionPerformed
        if (tabMode.getRowCount() == 0) {
            JOptionPane.showMessageDialog(null, "Maaf, data pasien sudah habis...!!!!");
            TCari.requestFocus();
        } else if (tbKamIn.getSelectedRow() == -1) {
            JOptionPane.showMessageDialog(null, "Maaf, Silahkan anda pilih dulu data kamar inap pada tabel...!!!");
            TCari.requestFocus();
        } else {
            this.setCursor(Cursor.getPredefinedCursor(Cursor.WAIT_CURSOR));
            DlgCatatan catatan = new DlgCatatan(null, true);
            catatan.setNoRm(tbKamIn.getValueAt(tbKamIn.getSelectedRow(), 1).toString());
            catatan.setSize(720, 330);
            catatan.setLocationRelativeTo(internalFrame1);
            catatan.setVisible(true);
            this.setCursor(Cursor.getDefaultCursor());
        }
    }//GEN-LAST:event_ppCatatanPasienBtnPrintActionPerformed

    private void ppDataHAIsBtnPrintActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_ppDataHAIsBtnPrintActionPerformed
        if (tabMode.getRowCount() == 0) {
            JOptionPane.showMessageDialog(null, "Maaf, tabel masih kosong...!!!!");
            TCari.requestFocus();
        } else if (TPasien.getText().trim().equals("")) {
            try {
                psanak = koneksi.prepareStatement(
                        "select pasien.no_rkm_medis,pasien.nm_pasien,ranap_gabung.no_rawat2,concat(reg_periksa.umurdaftar,' ',reg_periksa.sttsumur)as umur,pasien.no_peserta, "
                        + "concat(pasien.alamatpj,', ',pasien.kelurahanpj,', ',pasien.kecamatanpj,', ',pasien.kabupatenpj) as alamat "
                        + "from reg_periksa inner join pasien inner join ranap_gabung on "
                        + "pasien.no_rkm_medis=reg_periksa.no_rkm_medis and ranap_gabung.no_rawat2=reg_periksa.no_rawat where ranap_gabung.no_rawat=?");
                try {
                    psanak.setString(1, tbKamIn.getValueAt(tbKamIn.getSelectedRow(), 0).toString());
                    rs2 = psanak.executeQuery();
                    if (rs2.next()) {
                        DlgDataHAIs hais = new DlgDataHAIs(null, false);
                        hais.setSize(internalFrame1.getWidth(), internalFrame1.getHeight());
                        hais.setLocationRelativeTo(internalFrame1);
                        hais.emptTeks();
                        hais.isCek();
                        hais.setNoRm(rs2.getString("no_rawat2"), DTPCari1.getDate(), DTPCari2.getDate());
                        hais.tampil();
                        hais.setVisible(true);
                    } else {
                        JOptionPane.showMessageDialog(null, "Maaf, Silahkan anda pilih dulu pasien...!!!");
                        tbKamIn.requestFocus();
                    }
                } catch (Exception ex) {
                    System.out.println("Notifikasi : " + ex);
                } finally {
                    if (rs2 != null) {
                        rs2.close();
                    }
                    if (psanak != null) {
                        psanak.close();
                    }
                }
            } catch (Exception e) {
                System.out.println(e);
            }
        } else {
            DlgDataHAIs hais = new DlgDataHAIs(null, false);
            hais.setSize(internalFrame1.getWidth(), internalFrame1.getHeight());
            hais.setLocationRelativeTo(internalFrame1);
            hais.emptTeks();
            hais.isCek();
            hais.setNoRm(norawat.getText(), DTPCari1.getDate(), DTPCari2.getDate());
            hais.tampil();
            hais.setVisible(true);
        }
    }//GEN-LAST:event_ppDataHAIsBtnPrintActionPerformed

    private void btnPasienRanapGabung1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnPasienRanapGabung1ActionPerformed
        var.setform("DlgKamarInap");
        ikb.emptTeks();
        ikb.isCek();
        ikb.setSize(internalFrame1.getWidth() - 40, internalFrame1.getHeight() - 40);
        ikb.setLocationRelativeTo(internalFrame1);
        ikb.setVisible(true);
    }//GEN-LAST:event_btnPasienRanapGabung1ActionPerformed

    private void MnPengantarPulangActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_MnPengantarPulangActionPerformed
        if (tabMode.getRowCount() == 0) {
            JOptionPane.showMessageDialog(null, "Maaf, tabel masih kosong...!!!!");
            TCari.requestFocus();
        } else if (TPasien.getText().trim().equals("")) {
            try {
                psanak = koneksi.prepareStatement(
                        "select pasien.no_rkm_medis,pasien.nm_pasien,ranap_gabung.no_rawat2,concat(reg_periksa.umurdaftar,' ',reg_periksa.sttsumur)as umur,pasien.no_peserta, "
                        + "concat(pasien.alamatpj,', ',pasien.kelurahanpj,', ',pasien.kecamatanpj,', ',pasien.kabupatenpj) as alamat "
                        + "from reg_periksa inner join pasien inner join ranap_gabung on "
                        + "pasien.no_rkm_medis=reg_periksa.no_rkm_medis and ranap_gabung.no_rawat2=reg_periksa.no_rawat where ranap_gabung.no_rawat=?");

                try {
                    psanak.setString(1, tbKamIn.getValueAt(tbKamIn.getSelectedRow(), 0).toString());
                    rs2 = psanak.executeQuery();
                    if (rs2.next()) {
                        this.setCursor(Cursor.getPredefinedCursor(Cursor.WAIT_CURSOR));
                        Map<String, Object> param = new HashMap<>();
                        param.put("nama", rs2.getString("nm_pasien"));
                        param.put("jkel", Sequel.cariIsi("select if(pasien.jk='L','Laki-Laki','Perempuan') from pasien where pasien.no_rkm_medis=?", rs2.getString("no_rkm_medis")));
                        param.put("lahir", Sequel.cariIsi("select DATE_FORMAT(pasien.tgl_lahir,'%d-%m-%Y') from pasien where pasien.no_rkm_medis=?", rs2.getString("no_rkm_medis")));
                        param.put("norm", rs2.getString("no_rkm_medis"));
                        param.put("namars", var.getnamars());
                        param.put("alamatrs", var.getalamatrs());
                        param.put("kotars", var.getkabupatenrs());
                        param.put("propinsirs", var.getpropinsirs());
                        param.put("kontakrs", var.getkontakrs());
                        param.put("emailrs", var.getemailrs());
                        param.put("logo", Sequel.cariGambar("select logo from setting"));
                        Valid.MyReport("rptSuratPengantarPulang.jrxml", param, "::[ Surat Pengantar Pulang ]::");
                        this.setCursor(Cursor.getDefaultCursor());
                    } else {
                        JOptionPane.showMessageDialog(null, "Maaf, Silahkan anda pilih dulu pasien...!!!");
                        tbKamIn.requestFocus();
                    }
                } catch (Exception ex) {
                    System.out.println("Notifikasi : " + ex);
                } finally {
                    if (rs2 != null) {
                        rs2.close();
                    }
                    if (psanak != null) {
                        psanak.close();
                    }
                }
            } catch (Exception e) {
                System.out.println(e);
            }
        } else {
            this.setCursor(Cursor.getPredefinedCursor(Cursor.WAIT_CURSOR));
            Map<String, Object> param = new HashMap<>();
            param.put("nama", TPasien.getText());
            param.put("jkel", Sequel.cariIsi("select if(pasien.jk='L','Laki-Laki','Perempuan') from pasien where pasien.no_rkm_medis=?", TNoRM.getText()));
            param.put("lahir", Sequel.cariIsi("select DATE_FORMAT(pasien.tgl_lahir,'%d-%m-%Y') from pasien where pasien.no_rkm_medis=?", TNoRM.getText()));
            param.put("norm", TNoRM.getText());
            param.put("namars", var.getnamars());
            param.put("alamatrs", var.getalamatrs());
            param.put("kotars", var.getkabupatenrs());
            param.put("propinsirs", var.getpropinsirs());
            param.put("kontakrs", var.getkontakrs());
            param.put("emailrs", var.getemailrs());
            param.put("logo", Sequel.cariGambar("select logo from setting"));
            Valid.MyReport("rptSuratPengantarPulang.jrxml", param, "::[ Surat Pengantar Pulang ]::");
            this.setCursor(Cursor.getDefaultCursor());
        }
    }//GEN-LAST:event_MnPengantarPulangActionPerformed

    private void MnBarcodeRM1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_MnBarcodeRM1ActionPerformed
        if (tabMode.getRowCount() == 0) {
            JOptionPane.showMessageDialog(null, "Maaf, data pasien sudah habis...!!!!");
            TNoRM.requestFocus();
        } else if (TPasien.getText().trim().equals("")) {
            JOptionPane.showMessageDialog(null, "Maaf, Silahkan anda pilih dulu data pasien dengan mengklik data pada tabel...!!!");
            tbKamIn.requestFocus();
        } else {
            this.setCursor(Cursor.getPredefinedCursor(Cursor.WAIT_CURSOR));
            Map<String, Object> param = new HashMap<>();
            param.put("namars", var.getnamars());
            param.put("alamatrs", var.getalamatrs());
            param.put("kotars", var.getkabupatenrs());
            param.put("propinsirs", var.getpropinsirs());
            param.put("kontakrs", var.getkontakrs());
            param.put("emailrs", var.getemailrs());
            param.put("logo", Sequel.cariGambar("select logo from setting"));
            Valid.MyReport("rptBarcodeRM11.jrxml", "report", "::[ BARCODE (Label BESAR Rekam Medis) ]::", "select pasien.no_rkm_medis, pasien.nm_pasien, pasien.no_ktp, pasien.jk, "
                    + "pasien.tmp_lahir, DATE_FORMAT(pasien.tgl_lahir,'%d/%m/%Y') as tgl_lahir, pasien.nm_ibu, "
                    + "concat(pasien.alamat,', ',kelurahan.nm_kel,', ',kecamatan.nm_kec,', ',kabupaten.nm_kab) as alamat, pasien.gol_darah, pasien.pekerjaan,"
                    + "pasien.stts_nikah,pasien.agama,pasien.tgl_daftar,pasien.no_tlp,pasien.umur,"
                    + "pasien.pnd, pasien.keluarga, pasien.namakeluarga,penjab.png_jawab,pasien.pekerjaanpj,"
                    + "concat(pasien.alamatpj,', ',pasien.kelurahanpj,', ',pasien.kecamatanpj,', ',pasien.kabupatenpj) as alamatpj from pasien "
                    + "inner join kelurahan inner join kecamatan inner join kabupaten "
                    + "inner join penjab on pasien.kd_pj=penjab.kd_pj and pasien.kd_kel=kelurahan.kd_kel "
                    + "and pasien.kd_kec=kecamatan.kd_kec and pasien.kd_kab=kabupaten.kd_kab  where pasien.no_rkm_medis='" + TNoRM.getText() + "' ", param);
            this.setCursor(Cursor.getDefaultCursor());
        }
    }//GEN-LAST:event_MnBarcodeRM1ActionPerformed

    private void MnLabelRM1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_MnLabelRM1ActionPerformed
        if (tabMode.getRowCount() == 0) {
            JOptionPane.showMessageDialog(null, "Maaf, data pasien sudah habis...!!!!");
            TNoRM.requestFocus();
        } else if (TPasien.getText().trim().equals("")) {
            JOptionPane.showMessageDialog(null, "Maaf, Silahkan anda pilih dulu data pasien dengan mengklik data pada tabel...!!!");
            tbKamIn.requestFocus();
        } else {
            this.setCursor(Cursor.getPredefinedCursor(Cursor.WAIT_CURSOR));
            Map<String, Object> param = new HashMap<>();
            param.put("tgl_lahir", Sequel.cariIsi("select date_format(tgl_lahir,'%d') from pasien where no_rkm_medis='" + TNoRM.getText() + "'") + " "
                    + Sequel.bulanINDONESIA("select month(tgl_lahir) from pasien where no_rkm_medis='" + TNoRM.getText() + "'") + " "
                    + Sequel.cariIsi("select year(tgl_lahir) from pasien where no_rkm_medis='" + TNoRM.getText() + "'"));
            Valid.MyReport("rptLabelRM.jrxml", "report", "::[ LABEL BESAR IDENTITAS (Rekam Medis) ]::",
                    "select no_rkm_medis, nm_pasien from pasien where no_rkm_medis='" + TNoRM.getText() + "' ", param);
            this.setCursor(Cursor.getDefaultCursor());
        }
    }//GEN-LAST:event_MnLabelRM1ActionPerformed

    private void TCariKeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_TCariKeyTyped
        evt.setKeyChar(Character.toUpperCase(evt.getKeyChar()));
    }//GEN-LAST:event_TCariKeyTyped

    private void MnSEPJamkesdaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_MnSEPJamkesdaActionPerformed
        if (nmpenjab.getText().equals("")) {
            //emptTeks();
            JOptionPane.showMessageDialog(null, "Silakan klik dulu nama pasiennya pada tabel..!!");
            tbKamIn.requestFocus();
        } else if (nmpenjab.getText().equals("JAMKESDA (PBI)")) {
            DlgJamkesda.setSize(490, 125);
            DlgJamkesda.setLocationRelativeTo(internalFrame1);
            DlgJamkesda.setVisible(true);
        } else {
            //emptTeks();
            JOptionPane.showMessageDialog(null, "Jenis cara bayar pasien tersebut tidak sesuai..!!");
            tbKamIn.requestFocus();
        }
    }//GEN-LAST:event_MnSEPJamkesdaActionPerformed

    private void BtnCloseIn1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_BtnCloseIn1ActionPerformed
        DlgJamkesda.dispose();
        emptTeks();
        tampil();
    }//GEN-LAST:event_BtnCloseIn1ActionPerformed

    private void BtnCloseIn1KeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_BtnCloseIn1KeyPressed
        // TODO add your handling code here:
    }//GEN-LAST:event_BtnCloseIn1KeyPressed

    private void BtnSimpan1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_BtnSimpan1ActionPerformed
        cekjamkesda = 0;
        cekjamkesda = Sequel.cariInteger("SELECT Count(1) cek FROM bridging_jamkesda WHERE no_rawat='" + norawat.getText() + "' AND jns_rawat='Inap'");

        if (noSrt.getText().trim().equals("")) {
            Valid.textKosong(noSrt, "No. Surat Keterangan Peserta Jamkesda");
        } else if (cekjamkesda >= 1) {
            JOptionPane.showMessageDialog(null, "Data tidak tersimpan, SEP sudah pernah dibikinkan dengan no. rawat yang sama..!!");
            noSrt.requestFocus();
        } else {
            Sequel.menyimpan("bridging_jamkesda", "'" + norawat.getText() + "','" + noSrt.getText() + "','Inap',"
                    + "'" + tglInap.getText() + "',"
                    + "'" + Valid.SetTgl(Tglsurat.getSelectedItem() + "") + "',"
                    + "'" + norawat.getText() + "RI' ", "Data SEP Jamkesda");
            JOptionPane.showMessageDialog(null, "Data berhasil disimpan, SEP siap dicetak......");
            BtnCtkJkd.requestFocus();
        }
    }//GEN-LAST:event_BtnSimpan1ActionPerformed

    private void BtnSimpan1KeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_BtnSimpan1KeyPressed
        // TODO add your handling code here:
    }//GEN-LAST:event_BtnSimpan1KeyPressed

    private void noSrtKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_noSrtKeyPressed

    }//GEN-LAST:event_noSrtKeyPressed

    private void TglsuratItemStateChanged(java.awt.event.ItemEvent evt) {//GEN-FIRST:event_TglsuratItemStateChanged
        // TODO add your handling code here:
    }//GEN-LAST:event_TglsuratItemStateChanged

    private void TglsuratKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_TglsuratKeyPressed
        // TODO add your handling code here:
    }//GEN-LAST:event_TglsuratKeyPressed

    private void BtnGantijkdActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_BtnGantijkdActionPerformed
        if (noSrt.getText().trim().equals("")) {
            Valid.textKosong(noSrt, "No. Surat Keterangan Peserta Jamkesda");
        } else {
            Sequel.mengedit("bridging_jamkesda", "no_sep='" + sepJkd.getText() + "'", "no_surat='" + noSrt.getText() + "', tgl_surat='" + Valid.SetTgl(Tglsurat.getSelectedItem() + "") + "' ");
            JOptionPane.showMessageDialog(null, "Data berhasil diubah, SEP siap dicetak......");
            BtnCtkJkd.requestFocus();
        }
    }//GEN-LAST:event_BtnGantijkdActionPerformed

    private void BtnGantijkdKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_BtnGantijkdKeyPressed
        // TODO add your handling code here:
    }//GEN-LAST:event_BtnGantijkdKeyPressed

    private void BtnCtkJkdActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_BtnCtkJkdActionPerformed
        sepJkd.setText(Sequel.cariIsi("SELECT no_sep FROM bridging_jamkesda WHERE no_rawat='" + norawat.getText() + "' AND jns_rawat='Inap'"));

        if (noSrt.getText().trim().equals("")) {
            Valid.textKosong(noSrt, "No. Surat Keterangan Peserta Jamkesda");
        } else if (sepJkd.getText().equals("")) {
            Valid.textKosong(sepJkd, "No. SEP Jamkesda");
        } else {
            this.setCursor(Cursor.getPredefinedCursor(Cursor.WAIT_CURSOR));
            Map<String, Object> param = new HashMap<>();
            param.put("namars", var.getnamars());
            param.put("alamatrs", var.getalamatrs());
            param.put("kotars", var.getkabupatenrs());
            param.put("propinsirs", var.getpropinsirs());
            param.put("kontakrs", var.getkontakrs());
            param.put("emailrs", var.getemailrs());
            param.put("logo", Sequel.cariGambar("select logo from setting"));
            Valid.MyReport("rptSEPInap.jrxml", "report", "::[ Cetak SEP Pasien JAMKESDA Rawat Inap ]::",
                    " SELECT pasien.no_rkm_medis, bridging_jamkesda.no_sep, reg_periksa.tgl_registrasi, pasien.no_ktp, "
                    + " pasien.nm_pasien, pasien.tgl_lahir, IF(pasien.jk='L','Laki-laki','Perempuan') AS jk, "
                    + " reg_periksa.no_rawat, bridging_jamkesda.no_surat, bridging_jamkesda.jns_rawat, bridging_jamkesda.tgl_surat, "
                    + " bridging_jamkesda.tgl_rawat, penjab.png_jawab, bangsal.nm_bangsal, kamar.kelas "
                    + " FROM reg_periksa INNER JOIN pasien ON reg_periksa.no_rkm_medis = pasien.no_rkm_medis "
                    + " INNER JOIN bridging_jamkesda ON bridging_jamkesda.no_rawat = reg_periksa.no_rawat "
                    + " INNER JOIN penjab ON reg_periksa.kd_pj = penjab.kd_pj "
                    + " INNER JOIN kamar_inap ON kamar_inap.no_rawat = reg_periksa.no_rawat "
                    + " INNER JOIN kamar ON kamar_inap.kd_kamar = kamar.kd_kamar "
                    + " INNER JOIN bangsal ON kamar.kd_bangsal = bangsal.kd_bangsal "
                    + " WHERE bridging_jamkesda.no_sep='" + sepJkd.getText() + "' AND bridging_jamkesda.jns_rawat='Inap' "
                    + " AND penjab.png_jawab LIKE '%jamkesda%'", param);
            this.setCursor(Cursor.getDefaultCursor());
            DlgJamkesda.dispose();
            emptTeks();
            tampil();
        }
    }//GEN-LAST:event_BtnCtkJkdActionPerformed

    private void BtnCtkJkdKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_BtnCtkJkdKeyPressed
        // TODO add your handling code here:
    }//GEN-LAST:event_BtnCtkJkdKeyPressed

    private void cmbRuanganKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_cmbRuanganKeyPressed
        // TODO add your handling code here:
    }//GEN-LAST:event_cmbRuanganKeyPressed

    private void MnManualSEPBPJSActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_MnManualSEPBPJSActionPerformed
        if (tbKamIn.getRowCount() == 0) {
            JOptionPane.showMessageDialog(null, "Maaf, data sudah habis. Tidak ada data yang bisa anda print...!!!!");
            TCari.requestFocus();
        } else if (tbKamIn.getRowCount() != 0) {
            if (kdpenjab.getText().equals("B01")) {
                this.setCursor(Cursor.getPredefinedCursor(Cursor.WAIT_CURSOR));
                Map<String, Object> param = new HashMap<>();
                param.put("namars", var.getnamars());
                param.put("alamatrs", var.getalamatrs());
                param.put("kotars", var.getkabupatenrs());
                param.put("propinsirs", var.getpropinsirs());
                param.put("kontakrs", var.getkontakrs());
                param.put("emailrs", var.getemailrs());
                param.put("logo", Sequel.cariGambar("select bpjs from gambar"));
                Valid.MyReport("rptManualSEPInap.jrxml", "report", "::[ Cetak Manual SEP Pasien BPJS Rawat Inap ]::",
                        " SELECT DATE_FORMAT(reg_periksa.tgl_registrasi,'%Y-%m-%d') tgl_sep, CONCAT(pasien.no_peserta,' ( MR. ',reg_periksa.no_rkm_medis,' )') no_kartu, "
                        + " pasien.nm_pasien, CONCAT(DATE_FORMAT(pasien.tgl_lahir,'%Y-%m-%d'),' Kelamin : ',IF(pasien.jk='L','Laki-laki','Perempuan')) tgl_lahir, "
                        + " IFNULL(pasien.no_tlp,'-') no_telpon, poliklinik.nm_poli, penjab.png_jawab, IF(reg_periksa.status_lanjut='Ralan','R. Jalan','R. Inap') jns_rawat "
                        + " FROM reg_periksa INNER JOIN pasien ON reg_periksa.no_rkm_medis = pasien.no_rkm_medis "
                        + " INNER JOIN poliklinik ON reg_periksa.kd_poli = poliklinik.kd_poli INNER JOIN penjab ON reg_periksa.kd_pj = penjab.kd_pj "
                        + " WHERE reg_periksa.status_lanjut='Ranap' AND penjab.png_jawab LIKE '%bpjs%' AND reg_periksa.no_rawat='" + norawat.getText() + "'", param);
                this.setCursor(Cursor.getDefaultCursor());
            } else if (!kdpenjab.getText().equals("B01")) {
                JOptionPane.showMessageDialog(null, "Hanya pasien BPJS yang bisa dicetak SEP manualnya...!!!!");
            }
        }
    }//GEN-LAST:event_MnManualSEPBPJSActionPerformed

    private void ketKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_ketKeyPressed
        // TODO add your handling code here:
    }//GEN-LAST:event_ketKeyPressed

    private void TglMatiItemStateChanged(java.awt.event.ItemEvent evt) {//GEN-FIRST:event_TglMatiItemStateChanged
        // TODO add your handling code here:
    }//GEN-LAST:event_TglMatiItemStateChanged

    private void TglMatiKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_TglMatiKeyPressed
        // TODO add your handling code here:
    }//GEN-LAST:event_TglMatiKeyPressed

    private void cmbJam1KeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_cmbJam1KeyPressed
        Valid.pindah(evt, cmbJam1, cmbMnt1);
    }//GEN-LAST:event_cmbJam1KeyPressed

    private void cmbMnt1KeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_cmbMnt1KeyPressed
        Valid.pindah(evt, cmbMnt1, cmbDtk1);
    }//GEN-LAST:event_cmbMnt1KeyPressed

    private void cmbDtk1KeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_cmbDtk1KeyPressed
        Valid.pindah(evt, cmbDtk1, BtnSimpan);
    }//GEN-LAST:event_cmbDtk1KeyPressed

    private void cmbStatusActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_cmbStatusActionPerformed
        if (cmbStatus.getSelectedItem().equals("Meninggal >= 48 Jam") || (cmbStatus.getSelectedItem().equals("Meninggal < 48 Jam"))) {

            x = JOptionPane.showConfirmDialog(null, "Apakah ada tindakan perawatan jenazah ?", "Konfirmasi", JOptionPane.YES_NO_OPTION);
            if (x == JOptionPane.YES_OPTION) {
                var.setform("DlgKamarInap");
                billing.rawatinap.setSize(internalFrame1.getWidth(), internalFrame1.getHeight());
                billing.rawatinap.setLocationRelativeTo(internalFrame1);
                billing.rawatinap.isCek();
                billing.rawatinap.setNoRm(norawat.getText(), DTPCari1.getDate(), DTPCari2.getDate());
                billing.rawatinap.setStatus((String) cmbStatus.getSelectedItem());
                billing.rawatinap.setpetugas();
                billing.rawatinap.tampilDr();
                billing.rawatinap.setVisible(true);

                WindowInputKamar.dispose();
                emptTeks();
                tampil();

            } else {
                jLabel38.setVisible(true);
                jLabel41.setVisible(true);
                jLabel40.setVisible(true);
                ket.setVisible(true);
                ket.requestFocus();
                TglMati.setVisible(true);
                cmbJam1.setVisible(true);
                cmbMnt1.setVisible(true);
                cmbDtk1.setVisible(true);

                cmbJam1.setSelectedItem(now.substring(11, 13));
                cmbMnt1.setSelectedItem(now.substring(14, 16));
                cmbDtk1.setSelectedItem(now.substring(17, 19));
            }
        } else {
            cekKetMati();
        }

        if (cmbStatus.getSelectedItem().toString().equals("APS")) {
            x = JOptionPane.showConfirmDialog(null, "Apakah pasien ini pulang dengan cara APS ?", "Konfirmasi", JOptionPane.YES_NO_OPTION);
            if (x == JOptionPane.YES_OPTION) {
                var.setform("DlgKamarInap");
                WindowInputKamar.dispose();
                WindowPulangAPS.setSize(455, 289);
                WindowPulangAPS.setLocationRelativeTo(internalFrame1);
                WindowPulangAPS.setVisible(true);

                TCari4.setText("");
                alasanAPS.setText("");
                ketAPS.setText("");
                kdAPS = "";
                BtnEdit3.setEnabled(false);
                BtnSimpan9.setEnabled(true);
                BtnCloseIn11.setEnabled(true);
                tampilAPS();

            } else {
                WindowPulangAPS.dispose();
                cmbStatus.setSelectedIndex(0);
            }
        }
    }//GEN-LAST:event_cmbStatusActionPerformed

    private void MnSrtMatiActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_MnSrtMatiActionPerformed
        if ((R2.isSelected() == true) || (R3.isSelected() == true)) {
            if (!NoRMmati.getText().equals("")) {
                ctkSuratMati();
                R1.requestFocus();
            } else {
                JOptionPane.showMessageDialog(null, "Pasien tersebut pulang dari rumah sakit BUKAN dengan status Meninggal atau pasien masih dirawat inap...!!!!");
            }
        } else {
            JOptionPane.showMessageDialog(null, "Surat keterangan kematian TIDAK bisa dilihat dipilihan pasien BELUM PULANG ...!!!!");
            R3.requestFocus();
        }
    }//GEN-LAST:event_MnSrtMatiActionPerformed

    private void R1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_R1ActionPerformed

    }//GEN-LAST:event_R1ActionPerformed

    private void MnFormulirKematianActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_MnFormulirKematianActionPerformed
        if ((R2.isSelected() == true) || (R3.isSelected() == true)) {
            if (!NoRMmati.getText().equals("")) {
                ctkFormulirMati();
                R1.requestFocus();
            } else {
                JOptionPane.showMessageDialog(null, "Pasien tersebut pulang dari rumah sakit BUKAN dengan status Meninggal atau pasien masih dirawat inap...!!!!");
            }
        } else {
            JOptionPane.showMessageDialog(null, "Formulir penyebab kematian TIDAK bisa dilihat dipilihan pasien BELUM PULANG ...!!!!");
            R3.requestFocus();
        }
    }//GEN-LAST:event_MnFormulirKematianActionPerformed

    private void MnTracerInapActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_MnTracerInapActionPerformed
        StatusDaftar.setText(Sequel.cariIsi("SELECT stts_daftar FROM reg_periksa WHERE no_rawat='" + norawat.getText() + "' "));

        if (tabMode.getRowCount() == 0) {
            JOptionPane.showMessageDialog(null, "Maaf, data registrasi sudah habis...!!!!");
            TCari.requestFocus();
        } else if (TPasien.getText().trim().equals("")) {
            JOptionPane.showMessageDialog(null, "Maaf, Silahkan anda pilih dulu data registrasi pada tabel...!!!");
            TCari.requestFocus();
        } else if (R1.isSelected() == false) {
            JOptionPane.showMessageDialog(null, "Tracer pasien bisa dicetak untuk yg. masih dirawat inap saja...!!!");
            R1.requestFocus();
        } else {
            if (StatusDaftar.getText().equals("Baru")) {
                JOptionPane.showMessageDialog(null, "Tracer rawat inap tidak bisa dicetak, pasien tersebut merupakan kunjungan BARU...!!!");
                emptTeks();
                tampil();

            } else {
                this.setCursor(Cursor.getPredefinedCursor(Cursor.WAIT_CURSOR));
                Map<String, Object> param = new HashMap<>();
                param.put("namars", var.getnamars());
                param.put("alamatrs", var.getalamatrs());
                param.put("kotars", var.getkabupatenrs());
                param.put("propinsirs", var.getpropinsirs());
                param.put("kontakrs", var.getkontakrs());
                param.put("emailrs", var.getemailrs());
                param.put("logo", Sequel.cariGambar("select logo from setting"));
                Valid.MyReport("rptTracerInapRZ.jrxml", "report", "::[ Tracer Pasien Rawat Inap ]::",
                        " SELECT r.no_rkm_medis, p.nm_pasien, b.nm_bangsal, concat(DATE_FORMAT(ki.tgl_masuk,'%d-%m-%Y'),', Jam : ',ki.jam_masuk) wkt_msk FROM kamar_inap ki "
                        + "INNER JOIN reg_periksa r on r.no_rawat = ki.no_rawat "
                        + "INNER JOIN pasien p on p.no_rkm_medis = r.no_rkm_medis "
                        + "INNER JOIN kamar k on k.kd_kamar = ki.kd_kamar "
                        + "INNER JOIN bangsal b on b.kd_bangsal = k.kd_bangsal "
                        + " WHERE ki.no_rawat='" + norawat.getText() + "' AND r.status_lanjut='Ranap' AND r.stts_daftar='Lama' AND ki.tgl_masuk='" + tglInap.getText() + "' "
                        + "ORDER BY ki.tgl_masuk, ki.jam_masuk LIMIT 1", param);
                this.setCursor(Cursor.getDefaultCursor());
            }
        }
    }//GEN-LAST:event_MnTracerInapActionPerformed

    private void MnDataMatiActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_MnDataMatiActionPerformed
        if ((R2.isSelected() == true) || (R3.isSelected() == true)) {
            if (!NoRMmati.getText().equals("")) {
                bukaMenuMati();
                R1.requestFocus();
            } else {
                JOptionPane.showMessageDialog(null, "Maaf, tidak ditemukan data meninggal untuk pasien tersebut...!!!!");
            }
        } else {
            JOptionPane.showMessageDialog(null, "Untuk proses perubahan data kematian TIDAK bisa dilakukan dipilihan pasien BELUM PULANG ...!!!!");
            R3.requestFocus();
        }
    }//GEN-LAST:event_MnDataMatiActionPerformed

    private void BtnCloseIn2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_BtnCloseIn2ActionPerformed
        DlgMati.dispose();
        emptTeks();
        tampil();
    }//GEN-LAST:event_BtnCloseIn2ActionPerformed

    private void BtnCloseIn2KeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_BtnCloseIn2KeyPressed
        // TODO add your handling code here:
    }//GEN-LAST:event_BtnCloseIn2KeyPressed

    private void ket1KeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_ket1KeyPressed
        // TODO add your handling code here:
    }//GEN-LAST:event_ket1KeyPressed

    private void TglMati1ItemStateChanged(java.awt.event.ItemEvent evt) {//GEN-FIRST:event_TglMati1ItemStateChanged
        // TODO add your handling code here:
    }//GEN-LAST:event_TglMati1ItemStateChanged

    private void TglMati1KeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_TglMati1KeyPressed
        // TODO add your handling code here:
    }//GEN-LAST:event_TglMati1KeyPressed

    private void BtnGantiMatiActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_BtnGantiMatiActionPerformed
        if (ket1.getText().trim().equals("")) {
            Valid.textKosong(ket, "keterangan pasien meninggal");
        } else {
            Sequel.mengedit("pasien_mati", "no_rkm_medis='" + TNoRM.getText() + "'", "tanggal='" + Valid.SetTgl(TglMati1.getSelectedItem() + "") + "', "
                    + "jam='" + cmbJam2.getSelectedItem() + ":" + cmbMnt2.getSelectedItem() + ":" + cmbDtk2.getSelectedItem() + "',"
                    + "keterangan='" + ket1.getText() + "', unit_asal='Ruangan Inap' ");

            DlgMati.dispose();
            emptTeks();
        }
    }//GEN-LAST:event_BtnGantiMatiActionPerformed

    private void BtnGantiMatiKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_BtnGantiMatiKeyPressed
        // TODO add your handling code here:
    }//GEN-LAST:event_BtnGantiMatiKeyPressed

    private void cmbJam2KeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_cmbJam2KeyPressed
        //      Valid.pindah(evt,TglMati,cmbMnt);
    }//GEN-LAST:event_cmbJam2KeyPressed

    private void cmbMnt2KeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_cmbMnt2KeyPressed
        //      Valid.pindah(evt,cmbJam,cmbDtk);
    }//GEN-LAST:event_cmbMnt2KeyPressed

    private void cmbDtk2KeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_cmbDtk2KeyPressed
        //     Valid.pindah(evt,cmbMnt,"Rumah Sakit");
    }//GEN-LAST:event_cmbDtk2KeyPressed

    private void BtnHapusMatiActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_BtnHapusMatiActionPerformed
        if (var.getkode().equals("Admin Utama")) {
            if (!NoRMmati.getText().equals("")) {
                Sequel.meghapus("pasien_mati", "no_rkm_medis", TNoRM.getText());
                DlgMati.dispose();
                emptTeks();
            } else {
                JOptionPane.showMessageDialog(null, "Tidak ada data yang terhapus untuk pasien tersebut di laporan rekap pasien meninggal...!!!");
                ket1.requestFocus();
            }
        } else {
            JOptionPane.showMessageDialog(null, "Maaf data pasien tersebut tidak bisa dihapus...!!!");
            BtnCloseIn2.requestFocus();
        }
    }//GEN-LAST:event_BtnHapusMatiActionPerformed

    private void BtnHapusMatiKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_BtnHapusMatiKeyPressed
        // TODO add your handling code here:
    }//GEN-LAST:event_BtnHapusMatiKeyPressed

    private void ScrollKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_ScrollKeyPressed
        // TODO add your handling code here:
    }//GEN-LAST:event_ScrollKeyPressed

    private void cmbRuangKhusus1MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_cmbRuangKhusus1MouseClicked
        cmbRuangKhusus1.setEditable(false);

        if (cmbRuangKhusus1.getSelectedItem().equals("JANTUNG") || (cmbRuangKhusus1.getSelectedItem().equals("AS-SAMI"))) {
            ruangDicetak.setText(cmbRuangKhusus1.getSelectedItem().toString());
        } else if (cmbRuangKhusus1.getSelectedItem().equals("AS-SAMI/UMUM")) {
            ruangDicetak.setText("AS-SAMI/GENERAL");
        }
    }//GEN-LAST:event_cmbRuangKhusus1MouseClicked

    private void cmbRuangKhusus1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_cmbRuangKhusus1ActionPerformed
        if (cmbRuangKhusus1.getSelectedItem().equals("JANTUNG") || (cmbRuangKhusus1.getSelectedItem().equals("AS-SAMI"))) {
            ruangDicetak.setText(cmbRuangKhusus1.getSelectedItem().toString());
        } else if (cmbRuangKhusus1.getSelectedItem().equals("AS-SAMI/UMUM")) {
            ruangDicetak.setText("AS-SAMI/GENERAL");
        }
    }//GEN-LAST:event_cmbRuangKhusus1ActionPerformed

    private void cmbRuangKhusus1KeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_cmbRuangKhusus1KeyPressed
        // TODO add your handling code here:
    }//GEN-LAST:event_cmbRuangKhusus1KeyPressed

    private void cmbRuangKhusus2MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_cmbRuangKhusus2MouseClicked
        cmbRuangKhusus2.setEditable(false);

        if (!cmbRuangKhusus2.getSelectedItem().equals("")) {
            ruangDicetak.setText(cmbRuangKhusus2.getSelectedItem().toString());
        }

        if (cmbRuangKhusus2.getSelectedItem().equals("RKPD")) {
            ruangDicetak.setText("AR-RAZAQ");
        } else if (cmbRuangKhusus2.getSelectedItem().equals("ZAAL")) {
            ruangDicetak.setText("ZAAL");
        }
    }//GEN-LAST:event_cmbRuangKhusus2MouseClicked

    private void cmbRuangKhusus2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_cmbRuangKhusus2ActionPerformed
        if (!cmbRuangKhusus2.getSelectedItem().equals("")) {
            ruangDicetak.setText(cmbRuangKhusus2.getSelectedItem().toString());
        }

        if (cmbRuangKhusus2.getSelectedItem().equals("RKPD")) {
            ruangDicetak.setText("AR-RAZAQ");
        } else if (cmbRuangKhusus2.getSelectedItem().equals("ZAAL")) {
            ruangDicetak.setText("ZAAL");
        }
    }//GEN-LAST:event_cmbRuangKhusus2ActionPerformed

    private void cmbRuangKhusus2KeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_cmbRuangKhusus2KeyPressed
        // TODO add your handling code here:
    }//GEN-LAST:event_cmbRuangKhusus2KeyPressed

    private void cmbRuangKhusus3MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_cmbRuangKhusus3MouseClicked
        if (!cmbRuangKhusus3.getSelectedItem().equals("")) {
            ruangDicetak.setText(cmbRuangKhusus3.getSelectedItem().toString());
        }
    }//GEN-LAST:event_cmbRuangKhusus3MouseClicked

    private void cmbRuangKhusus3ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_cmbRuangKhusus3ActionPerformed
        if (!cmbRuangKhusus3.getSelectedItem().equals("")) {
            ruangDicetak.setText(cmbRuangKhusus3.getSelectedItem().toString());
        }
    }//GEN-LAST:event_cmbRuangKhusus3ActionPerformed

    private void cmbRuangKhusus3KeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_cmbRuangKhusus3KeyPressed
        // TODO add your handling code here:
    }//GEN-LAST:event_cmbRuangKhusus3KeyPressed

    private void cmbRuangKhusus4MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_cmbRuangKhusus4MouseClicked
        if (!cmbRuangKhusus4.getSelectedItem().equals("")) {
            ruangDicetak.setText(cmbRuangKhusus4.getSelectedItem().toString());
        }
    }//GEN-LAST:event_cmbRuangKhusus4MouseClicked

    private void cmbRuangKhusus4ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_cmbRuangKhusus4ActionPerformed
        if (!cmbRuangKhusus4.getSelectedItem().equals("")) {
            ruangDicetak.setText(cmbRuangKhusus4.getSelectedItem().toString());
        }
    }//GEN-LAST:event_cmbRuangKhusus4ActionPerformed

    private void cmbRuangKhusus4KeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_cmbRuangKhusus4KeyPressed
        // TODO add your handling code here:
    }//GEN-LAST:event_cmbRuangKhusus4KeyPressed

    private void cmbRuanganActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_cmbRuanganActionPerformed
        if (cmbRuangan.getSelectedItem().equals("RKPD")) {
            ruangDicetak.setText("AR-RAZAQ");
        } else if (cmbRuangan.getSelectedItem().equals("ZAAL")) {
            ruangDicetak.setText("ZAAL");
        } else if (cmbRuangan.getSelectedItem().equals("AS-SAMI/UMUM")) {
            ruangDicetak.setText("AS-SAMI/GENERAL");
        } else {
            ruangDicetak.setText(cmbRuangan.getSelectedItem().toString());
        }
    }//GEN-LAST:event_cmbRuanganActionPerformed

    private void cmbRuanganMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_cmbRuanganMouseClicked
        if (cmbRuangan.getSelectedItem().equals("RKPD")) {
            ruangDicetak.setText("AR-RAZAQ");
        } else if (cmbRuangan.getSelectedItem().equals("ZAAL")) {
            ruangDicetak.setText("ZAAL");
        } else if (cmbRuangan.getSelectedItem().equals("AS-SAMI/UMUM")) {
            ruangDicetak.setText("AS-SAMI/GENERAL");
        } else {
            ruangDicetak.setText(cmbRuangan.getSelectedItem().toString());
        }
    }//GEN-LAST:event_cmbRuanganMouseClicked

    private void MnLihatSEPActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_MnLihatSEPActionPerformed
        if (tabMode.getRowCount() == 0) {
            JOptionPane.showMessageDialog(null, "Maaf, tabel masih kosong...!!!!");
            BtnCari.requestFocus();
        } else if (statusSEP.getText().equals("") && kdpenjab.getText().equals("")) {
            JOptionPane.showMessageDialog(null, "Silakan klik dulu nama pasiennya pada tabel..!!");
            tbKamIn.requestFocus();
        } else if (statusSEP.getText().equals("SUDAH") && kdpenjab.getText().equals("B01")) {
            DlgNoSEP.setSize(451, 154);
            DlgNoSEP.setLocationRelativeTo(internalFrame1);
            DlgNoSEP.setVisible(true);

            nosep.setText(Sequel.cariIsi("select no_sep from bridging_sep where no_rawat='" + norawat.getText() + "' and jnspelayanan='1'"));
            tglsep.setText(Sequel.cariIsi("select day(tglsep) from bridging_sep where no_sep='" + nosep.getText() + "' and jnspelayanan='1'") + " "
                    + Sequel.bulanINDONESIA("select month(tglsep) from bridging_sep where no_sep='" + nosep.getText() + "' and jnspelayanan='1'") + " "
                    + Sequel.cariIsi("select year(tglsep) from bridging_sep where no_sep='" + nosep.getText() + "' and jnspelayanan='1'"));
            jnsBayar.setText(nmpenjab.getText());
        } else if (statusSEP.getText().equals("BELUM") && kdpenjab.getText().equals("B01")) {
            DlgNoSEP.setSize(451, 154);
            DlgNoSEP.setLocationRelativeTo(internalFrame1);
            DlgNoSEP.setVisible(true);

            nosep.setText("SEP belum dicetak, silakan cek lagi..!!!");
            tglsep.setText("-");
            jnsBayar.setText(nmpenjab.getText());
        } else if (statusSEP.getText().equals("NON SEP") || !kdpenjab.getText().equals("B01")) {
            JOptionPane.showMessageDialog(null, "Tidak ada SEP yang dicetak/pasien tersebut tidak termasuk pasien BPJS..!!");
            tbKamIn.requestFocus();
        }
    }//GEN-LAST:event_MnLihatSEPActionPerformed

    private void BtnCloseIn3ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_BtnCloseIn3ActionPerformed
        DlgNoSEP.dispose();
        emptTeks();
        tampil();
    }//GEN-LAST:event_BtnCloseIn3ActionPerformed

    private void BtnCloseIn3KeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_BtnCloseIn3KeyPressed
        // TODO add your handling code here:
    }//GEN-LAST:event_BtnCloseIn3KeyPressed

    private void tglsepKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_tglsepKeyPressed

    }//GEN-LAST:event_tglsepKeyPressed

    private void nosepKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_nosepKeyPressed
        // TODO add your handling code here:
    }//GEN-LAST:event_nosepKeyPressed

    private void jnsBayarKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_jnsBayarKeyPressed

    }//GEN-LAST:event_jnsBayarKeyPressed

    private void nosepKeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_nosepKeyTyped

    }//GEN-LAST:event_nosepKeyTyped

    private void MnSKDPBPJSActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_MnSKDPBPJSActionPerformed
        if (tabMode.getRowCount() == 0) {
            JOptionPane.showMessageDialog(null, "Maaf, tabel masih kosong...!!!!");
            TCari.requestFocus();
        } else if (tbKamIn.getValueAt(tbKamIn.getSelectedRow(), 0).toString().equals("")) {
            try {
                psanak = koneksi.prepareStatement(
                        "select pasien.no_rkm_medis,pasien.nm_pasien,ranap_gabung.no_rawat2,concat(reg_periksa.umurdaftar,' ',reg_periksa.sttsumur)as umur,pasien.no_peserta, "
                        + "concat(pasien.alamatpj,', ',pasien.kelurahanpj,', ',pasien.kecamatanpj,', ',pasien.kabupatenpj) as alamat "
                        + "from reg_periksa inner join pasien inner join ranap_gabung on "
                        + "pasien.no_rkm_medis=reg_periksa.no_rkm_medis and ranap_gabung.no_rawat2=reg_periksa.no_rawat where ranap_gabung.no_rawat=?");
                try {
                    psanak.setString(1, tbKamIn.getValueAt(tbKamIn.getSelectedRow() - 1, 0).toString());
                    rs2 = psanak.executeQuery();
                    if (rs2.next()) {
                        DlgSKDPBPJS form = new DlgSKDPBPJS(null, false);
                        form.isCek();
                        form.setSize(internalFrame1.getWidth() - 40, internalFrame1.getHeight() - 40);
                        form.setLocationRelativeTo(internalFrame1);
                        form.emptTeks();
                        form.setNoRm(rs2.getString("no_rkm_medis"), rs2.getString("nm_pasien"));
                        form.setVisible(true);
                        form.fokus();
                    } else {
                        JOptionPane.showMessageDialog(null, "Maaf, Silahkan anda pilih dulu pasien...!!!");
                        tbKamIn.requestFocus();
                    }
                } catch (Exception ex) {
                    System.out.println("Notifikasi : " + ex);
                } finally {
                    if (rs2 != null) {
                        rs2.close();
                    }
                    if (psanak != null) {
                        psanak.close();
                    }
                }
            } catch (Exception e) {
                System.out.println(e);
            }
        } else {
            DlgSKDPBPJS form = new DlgSKDPBPJS(null, false);
            form.isCek();
            form.setSize(internalFrame1.getWidth() - 40, internalFrame1.getHeight() - 40);
            form.setLocationRelativeTo(internalFrame1);
            form.emptTeks();
            form.setNoRm(TNoRM.getText(), TPasien.getText());
            form.setVisible(true);
            form.fokus();
        }
    }//GEN-LAST:event_MnSKDPBPJSActionPerformed

    private void MnSJPActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_MnSJPActionPerformed
        if (tabMode.getRowCount() == 0) {
            JOptionPane.showMessageDialog(null, "Maaf, tabel masih kosong...!!!!");
            R1.requestFocus();
        } else if (norawat.getText().trim().equals("")) {
            JOptionPane.showMessageDialog(null, "Maaf, Silahkan anda pilih dulu dengan mengklik data pada tabel...!!!");
            tbKamIn.requestFocus();
        } else if (!kdpenjab.getText().equals("A01")) {
            JOptionPane.showMessageDialog(null, "Pasien itu bukan pasien Inhealth...!!!");
            tbKamIn.requestFocus();
        } else if (R1.isSelected() == false) {
            JOptionPane.showMessageDialog(null, "Pembuatan SJP Inhealth hanya bisa dilakukan dipilihan pasien BELUM PULANG...!!!");
            R1.requestFocus();
        } else if ((kdpenjab.getText().equals("A01")) && (R1.isSelected() == true)) {
            this.setCursor(Cursor.getPredefinedCursor(Cursor.WAIT_CURSOR));
            var.setform("DlgKamarInap");
            InhealthDataSJP dlgki = new InhealthDataSJP(null, false);
            dlgki.setSize(internalFrame1.getWidth(), internalFrame1.getHeight());
            dlgki.setLocationRelativeTo(internalFrame1);
            dlgki.isCek();
            dlgki.setNoRm(norawat.getText(), Valid.SetTgl(tbKamIn.getValueAt(tbKamIn.getSelectedRow(), 12).toString()), "4 RITL RAWAT INAP TINGKAT LANJUT", "IGDK", "IGD");
            dlgki.tampil();
            dlgki.setVisible(true);
            this.setCursor(Cursor.getDefaultCursor());
        }
    }//GEN-LAST:event_MnSJPActionPerformed

    private void tglMasukInapItemStateChanged(java.awt.event.ItemEvent evt) {//GEN-FIRST:event_tglMasukInapItemStateChanged
        // TODO add your handling code here:
    }//GEN-LAST:event_tglMasukInapItemStateChanged

    private void tglMasukInapKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_tglMasukInapKeyPressed
        // TODO add your handling code here:
    }//GEN-LAST:event_tglMasukInapKeyPressed

    private void MnSelisihTarifActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_MnSelisihTarifActionPerformed
        if (tabMode.getRowCount() == 0) {
            JOptionPane.showMessageDialog(null, "Maaf, tabel masih kosong...!!!!");
            BtnCari.requestFocus();
        } else if (norawat.getText().equals("")) {
            JOptionPane.showMessageDialog(null, "Silakan klik dulu nama pasiennya pada tabel..!!");
            tbKamIn.requestFocus();
        } else if (R1.isSelected() == true) {
            JOptionPane.showMessageDialog(null, "Pasien harus dipulangkan terlebih dulu, klik dipilihan pasien pulang..!!");
            BtnOut.requestFocus();
        } else if (R2.isSelected() == true) {
            JOptionPane.showMessageDialog(null, "Kode INACBG utk selisih tarif hanya bisa diisi pada pilihan pasien pulang, klik dipilihan pasien pulang..!!");
            R3.requestFocus();
        } else if ((R3.isSelected() == true) && (!kdpenjab.getText().equals("B01"))) {
            JOptionPane.showMessageDialog(null, "Pasien itu bukan jenis pasien BPJS, ulangi lagi...!!");
            tbKamIn.requestFocus();
        } else if ((R3.isSelected() == true) && (kdpenjab.getText().equals("B01")) && (!statusSEP.getText().equals("SUDAH"))) {
            JOptionPane.showMessageDialog(null, "Pasien itu belum mencetak SEP, cek lagi kelengkapan berkasnya..!!");
            tbKamIn.requestFocus();
        } else if ((R3.isSelected() == true) && (kdpenjab.getText().equals("B01")) && (statusSEP.getText().equals("SUDAH"))) {
            WindowSelisihTarif.setSize(729, 277);
            WindowSelisihTarif.setLocationRelativeTo(internalFrame1);
            WindowSelisihTarif.setVisible(true);

            Sequel.cariIsi("select no_sep from bridging_sep where jnspelayanan='1' and no_rawat=? ", NoSEP, norawat.getText());
            cekSEP();
            kdINACBG.requestFocus();
            cekINACBG();
            hitungSelisih();
        }
    }//GEN-LAST:event_MnSelisihTarifActionPerformed

    private void nominal1KeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_nominal1KeyTyped
        // TODO add your handling code here:
    }//GEN-LAST:event_nominal1KeyTyped

    private void nominal1KeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_nominal1KeyPressed
        // TODO add your handling code here:
    }//GEN-LAST:event_nominal1KeyPressed

    private void hasilLMKeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_hasilLMKeyTyped
        // TODO add your handling code here:
    }//GEN-LAST:event_hasilLMKeyTyped

    private void hasilLMKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_hasilLMKeyPressed
        // TODO add your handling code here:
    }//GEN-LAST:event_hasilLMKeyPressed

    private void nominal2KeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_nominal2KeyTyped
        // TODO add your handling code here:
    }//GEN-LAST:event_nominal2KeyTyped

    private void nominal2KeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_nominal2KeyPressed
        // TODO add your handling code here:
    }//GEN-LAST:event_nominal2KeyPressed

    private void BtnCloseIn6ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_BtnCloseIn6ActionPerformed
        WindowSelisihTarif.dispose();
        selisihBaru();
        emptTeks();
        tampil();
    }//GEN-LAST:event_BtnCloseIn6ActionPerformed

    private void BtnSimpan6ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_BtnSimpan6ActionPerformed
        Sequel.cariIsi("select total_byr from pemasukan_lain where no_rawat=? ", statusSELISIH, norawatSEP.getText());

        if (statusSELISIH.getText().equals("")) {
            Sequel.mengedit("bridging_sep", "no_sep='" + NoSEP.getText() + "' and jnspelayanan='1'", "kode_inacbg='" + kdINACBG.getText() + "' ");
            Sequel.mengedit("bridging_sep_backup", "no_sep='" + NoSEP.getText() + "' and jnspelayanan='1'", "kode_inacbg='" + kdINACBG.getText() + "' ");

            WindowSelisihTarif.dispose();
            tampil();
            selisihBaru();
            emptTeks();
        } else {
            JOptionPane.showMessageDialog(null, "Kode " + kdINACBG.getText() + " sudah terverifikasi kasir utk. pembayaran. Data tidak bisa diubah...!!!");
            Sequel.cariIsi("select kode_inacbg from bridging_sep where jnspelayanan='1' and no_sep=? ", kdINACBG, NoSEP.getText());
            cekINACBG();
            if (hakkelas.getText().equals("1")) {
                hitungSelisih();
            } else if (hakkelas.getText().equals("2")) {
                hitungSelisih();
            } else if (hakkelas.getText().equals("3")) {
                hitungSelisih();
            }
            BtnCloseIn6.requestFocus();
        }
    }//GEN-LAST:event_BtnSimpan6ActionPerformed

    private void NoSEPKeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_NoSEPKeyTyped
        evt.setKeyChar(Character.toUpperCase(evt.getKeyChar()));
    }//GEN-LAST:event_NoSEPKeyTyped

    private void NoSEPKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_NoSEPKeyPressed

    }//GEN-LAST:event_NoSEPKeyPressed

    private void normKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_normKeyPressed
        // TODO add your handling code here:
    }//GEN-LAST:event_normKeyPressed

    private void nmpasienKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_nmpasienKeyPressed
        // TODO add your handling code here:
    }//GEN-LAST:event_nmpasienKeyPressed

    private void nokartuKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_nokartuKeyPressed
        // TODO add your handling code here:
    }//GEN-LAST:event_nokartuKeyPressed

    private void norawatSEPKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_norawatSEPKeyPressed
        // TODO add your handling code here:
    }//GEN-LAST:event_norawatSEPKeyPressed

    private void rginapKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_rginapKeyPressed
        // TODO add your handling code here:
    }//GEN-LAST:event_rginapKeyPressed

    private void hakkelasKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_hakkelasKeyPressed
        // TODO add your handling code here:
    }//GEN-LAST:event_hakkelasKeyPressed

    private void kdINACBGKeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_kdINACBGKeyTyped
        evt.setKeyChar(Character.toUpperCase(evt.getKeyChar()));
    }//GEN-LAST:event_kdINACBGKeyTyped

    private void kdINACBGKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_kdINACBGKeyPressed
        if (evt.getKeyCode() == KeyEvent.VK_ENTER) {

            if (kdINACBG.getText().trim().equals("")) {
                JOptionPane.showMessageDialog(null, "Kode INACBG belum diisi..!!");
                kdINACBG.requestFocus();
                selisihBaru();
            } else {
                cekINACBG();

                if (deskripsiKD.getText().equals("")) {
                    JOptionPane.showMessageDialog(null, "Kode INACBG salah, ulangi lagi..!!!");
                    kdINACBG.requestFocus();
                    kdINACBG.setText("");
                } else if (!deskripsiKD.getText().equals("")) {
                    if (hakkelas.getText().equals("1")) {
                        hitungSelisih();
                    } else if (hakkelas.getText().equals("2")) {
                        hitungSelisih();
                    } else if (hakkelas.getText().equals("3")) {
                        hitungSelisih();
                    }
                }
            }
        }
    }//GEN-LAST:event_kdINACBGKeyPressed

    private void deskripsiKDKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_deskripsiKDKeyPressed
        // TODO add your handling code here:
    }//GEN-LAST:event_deskripsiKDKeyPressed

    private void tarifkls1KeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_tarifkls1KeyTyped
        // TODO add your handling code here:
    }//GEN-LAST:event_tarifkls1KeyTyped

    private void tarifkls1KeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_tarifkls1KeyPressed
        // TODO add your handling code here:
    }//GEN-LAST:event_tarifkls1KeyPressed

    private void tarifkls2KeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_tarifkls2KeyTyped
        // TODO add your handling code here:
    }//GEN-LAST:event_tarifkls2KeyTyped

    private void tarifkls2KeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_tarifkls2KeyPressed
        // TODO add your handling code here:
    }//GEN-LAST:event_tarifkls2KeyPressed

    private void tarifkls3KeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_tarifkls3KeyTyped
        // TODO add your handling code here:
    }//GEN-LAST:event_tarifkls3KeyTyped

    private void tarifkls3KeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_tarifkls3KeyPressed
        // TODO add your handling code here:
    }//GEN-LAST:event_tarifkls3KeyPressed

    private void lmrawatKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_lmrawatKeyPressed
        // TODO add your handling code here:
    }//GEN-LAST:event_lmrawatKeyPressed

    private void dibayarKeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_dibayarKeyTyped
        // TODO add your handling code here:
    }//GEN-LAST:event_dibayarKeyTyped

    private void dibayarKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_dibayarKeyPressed
        // TODO add your handling code here:
    }//GEN-LAST:event_dibayarKeyPressed

    private void persenSELISIHKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_persenSELISIHKeyPressed
        // TODO add your handling code here:
    }//GEN-LAST:event_persenSELISIHKeyPressed

    private void naikKLSKeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_naikKLSKeyTyped
        // TODO add your handling code here:
    }//GEN-LAST:event_naikKLSKeyTyped

    private void naikKLSKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_naikKLSKeyPressed
        // TODO add your handling code here:
    }//GEN-LAST:event_naikKLSKeyPressed

    private void BtnGantikodeActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_BtnGantikodeActionPerformed
        Sequel.cariIsi("select total_byr from pemasukan_lain where no_rawat=? ", statusSELISIH, norawatSEP.getText());

        if (statusSELISIH.getText().equals("")) {
            Sequel.mengedit("bridging_sep", "no_sep='" + NoSEP.getText() + "' and jnspelayanan='1'", "kode_inacbg='" + kdINACBG.getText() + "' ");
            Sequel.mengedit("bridging_sep_backup", "no_sep='" + NoSEP.getText() + "' and jnspelayanan='1'", "kode_inacbg='" + kdINACBG.getText() + "' ");

            WindowSelisihTarif.dispose();
            tampil();
            selisihBaru();
            emptTeks();
        } else {
            JOptionPane.showMessageDialog(null, "Kode " + kdINACBG.getText() + " sudah terverifikasi kasir utk. pembayaran. Data tidak bisa diubah...!!!");
            Sequel.cariIsi("select kode_inacbg from bridging_sep where jnspelayanan='1' and no_sep=? ", kdINACBG, NoSEP.getText());
            cekINACBG();
            if (hakkelas.getText().equals("1")) {
                hitungSelisih();
            } else if (hakkelas.getText().equals("2")) {
                hitungSelisih();
            } else if (hakkelas.getText().equals("3")) {
                hitungSelisih();
            }
            BtnCloseIn6.requestFocus();
        }
    }//GEN-LAST:event_BtnGantikodeActionPerformed

    private void statusSELISIHKeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_statusSELISIHKeyTyped
        // TODO add your handling code here:
    }//GEN-LAST:event_statusSELISIHKeyTyped

    private void statusSELISIHKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_statusSELISIHKeyPressed
        // TODO add your handling code here:
    }//GEN-LAST:event_statusSELISIHKeyPressed

    private void ppDataPersalinanBtnPrintActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_ppDataPersalinanBtnPrintActionPerformed
        cekKamar = 0;
        cekDataPersalinan = "";
        cekKelamin = "";
        cekKamar = Sequel.cariInteger("select count(1) tes from kamar_inap k inner join kamar r on r.kd_kamar = k.kd_kamar "
                + "inner join bangsal b on b.kd_bangsal = r.kd_bangsal where "
                + "(b.nm_bangsal like '%OBG%' or b.nm_bangsal like '%VIP%' or b.nm_bangsal like '%ICU/ICCU/General%') and (b.nm_bangsal not like '%BAYI%') "
                + "and k.no_rawat = '" + norawat.getText() + "'");

        cekDataPersalinan = Sequel.cariIsi("select no_rawat from data_persalinan where no_rawat='" + norawat.getText() + "'");
        cekKelamin = Sequel.cariIsi("select jk from pasien where no_rkm_medis='" + TNoRM.getText() + "'");

        if (tabMode.getRowCount() == 0) {
            JOptionPane.showMessageDialog(null, "Maaf, tabel masih kosong...!!!!");
            TCari.requestFocus();
        } else if (cekKamar == 0) {
            JOptionPane.showMessageDialog(null, "Hanya untuk pasien persalinan saja...!!!!");
            tbKamIn.requestFocus();
        } else if (norawat.getText().equals("")) {
            JOptionPane.showMessageDialog(null, "Silakan klik salah satu nama pasien atau no. rekam medisnya dulu...!!!!");
            tbKamIn.requestFocus();
        } else if (!cekKelamin.equals("P")) {
            JOptionPane.showMessageDialog(null, "Hanya untuk pasien yang berjenis kelamin perempuan saja...!!!!");
            tbKamIn.requestFocus();
        } else if (!cekDataPersalinan.equals("")) {
            x = JOptionPane.showConfirmDialog(null, "Data persalinan pasien yg bernama " + TPasien.getText() + " sudah tersimpan, apakah mau diperbaiki...?", "Konfirmasi", JOptionPane.YES_NO_OPTION);
            if (x == JOptionPane.YES_OPTION) {
                persalinan.setSize(943, 595);
                persalinan.setLocationRelativeTo(internalFrame1);
                persalinan.setPasien(norawat.getText(), TNoRM.getText(), tglInap.getText(), status_pulang.getText());
                persalinan.TCari.setText(norawat.getText());
                persalinan.tampilSatuPersalinan();
                persalinan.ChkInput.setSelected(true);
                persalinan.isForm();
                persalinan.isCek();
                persalinan.BtnSimpan.setEnabled(false);
                persalinan.BtnGanti.setEnabled(true);
                persalinan.setVisible(true);

            } else {
                emptTeks();
                tampil();
            }
        } else if (!norawat.getText().equals("") && (cekKelamin.equals("P")) && (cekDataPersalinan.equals(""))) {
            persalinan.setSize(943, 595);
            persalinan.setLocationRelativeTo(internalFrame1);
            persalinan.setPasien(norawat.getText(), TNoRM.getText(), tglInap.getText(), status_pulang.getText());
            persalinan.TCari.setText(norawat.getText());
            persalinan.tampil();
            persalinan.ChkInput.setSelected(true);
            persalinan.isForm();
            persalinan.isCek();
            persalinan.BtnSimpan.setEnabled(true);
            persalinan.BtnGanti.setEnabled(false);
            persalinan.setVisible(true);
        }
    }//GEN-LAST:event_ppDataPersalinanBtnPrintActionPerformed

    private void MnBarcodeRM2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_MnBarcodeRM2ActionPerformed
        if (tabMode.getRowCount() == 0) {
            JOptionPane.showMessageDialog(null, "Maaf, data pasien sudah habis...!!!!");
            TNoRM.requestFocus();
        } else if (TPasien.getText().trim().equals("")) {
            JOptionPane.showMessageDialog(null, "Maaf, Silahkan anda pilih dulu data pasien dengan mengklik data pada tabel...!!!");
            tbKamIn.requestFocus();
        } else {
            this.setCursor(Cursor.getPredefinedCursor(Cursor.WAIT_CURSOR));
            Map<String, Object> param = new HashMap<>();
            param.put("namars", var.getnamars());
            param.put("alamatrs", var.getalamatrs());
            param.put("kotars", var.getkabupatenrs());
            param.put("propinsirs", var.getpropinsirs());
            param.put("kontakrs", var.getkontakrs());
            param.put("emailrs", var.getemailrs());
            param.put("logo", Sequel.cariGambar("select logo from setting"));
            Valid.MyReport("rptBarcodeRM111.jrxml", "report", "::[ BARCODE (Label KECIL Rekam Medis) ]::", "select pasien.no_rkm_medis, pasien.nm_pasien, pasien.no_ktp, pasien.jk, "
                    + "pasien.tmp_lahir, DATE_FORMAT(pasien.tgl_lahir,'%d/%m/%Y') as tgl_lahir, pasien.nm_ibu, concat(pasien.alamat,', ',kelurahan.nm_kel,', ',kecamatan.nm_kec,', ',kabupaten.nm_kab) as alamat, pasien.gol_darah, pasien.pekerjaan,"
                    + "pasien.stts_nikah,pasien.agama,pasien.tgl_daftar,pasien.no_tlp,pasien.umur,"
                    + "pasien.pnd, pasien.keluarga, pasien.namakeluarga,penjab.png_jawab,pasien.pekerjaanpj,"
                    + "concat(pasien.alamatpj,', ',pasien.kelurahanpj,', ',pasien.kecamatanpj,', ',pasien.kabupatenpj) as alamatpj from pasien "
                    + "inner join kelurahan inner join kecamatan inner join kabupaten "
                    + "inner join penjab on pasien.kd_pj=penjab.kd_pj and pasien.kd_kel=kelurahan.kd_kel "
                    + "and pasien.kd_kec=kecamatan.kd_kec and pasien.kd_kab=kabupaten.kd_kab  where pasien.no_rkm_medis='" + TNoRM.getText() + "' ", param);
            this.setCursor(Cursor.getDefaultCursor());
        }
    }//GEN-LAST:event_MnBarcodeRM2ActionPerformed

    private void MnLabelRM2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_MnLabelRM2ActionPerformed
        if (tabMode.getRowCount() == 0) {
            JOptionPane.showMessageDialog(null, "Maaf, data pasien sudah habis...!!!!");
            TNoRM.requestFocus();
        } else if (TPasien.getText().trim().equals("")) {
            JOptionPane.showMessageDialog(null, "Maaf, Silahkan anda pilih dulu data pasien dengan mengklik data pada tabel...!!!");
            tbKamIn.requestFocus();
        } else {
            this.setCursor(Cursor.getPredefinedCursor(Cursor.WAIT_CURSOR));
            Map<String, Object> param = new HashMap<>();
            param.put("tgl_lahir", Sequel.cariIsi("select date_format(tgl_lahir,'%d') from pasien where no_rkm_medis='" + TNoRM.getText() + "'") + " "
                    + Sequel.bulanINDONESIA("select month(tgl_lahir) from pasien where no_rkm_medis='" + TNoRM.getText() + "'") + " "
                    + Sequel.cariIsi("select year(tgl_lahir) from pasien where no_rkm_medis='" + TNoRM.getText() + "'"));
            Valid.MyReport("rptLabelRM1.jrxml", "report", "::[ LABEL KECIL IDENTITAS (Rekam Medis) ]::",
                    "select no_rkm_medis, nm_pasien from pasien where pasien.no_rkm_medis='" + TNoRM.getText() + "' ", param);
            this.setCursor(Cursor.getDefaultCursor());
        }
    }//GEN-LAST:event_MnLabelRM2ActionPerformed

    private void MnStatusGiziActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_MnStatusGiziActionPerformed
        Sequel.cariIsi("SELECT no_rawat FROM status_gizi_inap where no_rawat=? ", dataGZ, norawat.getText());

        if (tabMode.getRowCount() == 0) {
            JOptionPane.showMessageDialog(null, "Maaf, tabel masih kosong...!!!!");
            TCari.requestFocus();
        } else if (norawat.getText().equals("")) {
            JOptionPane.showMessageDialog(null, "Silakan klik salah satu nama pasien atau no. rekam medisnya dulu...!!!!");
            tbKamIn.requestFocus();
        } else if (!dataGZ.getText().equals("")) {
            x = JOptionPane.showConfirmDialog(null, "Data status gizi utk. pasien yg bernama " + TPasien.getText() + " sudah tersimpan, apakah mau diperbaiki...?", "Konfirmasi", JOptionPane.YES_NO_OPTION);
            if (x == JOptionPane.YES_OPTION) {
                DlgStatusGizi statusGZ = new DlgStatusGizi(null, false);
                statusGZ.setSize(812, 489);
                statusGZ.setLocationRelativeTo(internalFrame1);
                statusGZ.emptTeks();
                statusGZ.setPasien(norawat.getText(), TNoRM.getText(), TPasien.getText(), kdkamar.getText());
                statusGZ.tampil();
                statusGZ.BtnSimpan.setEnabled(false);
                statusGZ.BtnEdit.setEnabled(true);
                statusGZ.setVisible(true);
            } else {
                emptTeks();
            }
        } else if (dataGZ.getText().equals("")) {
            DlgStatusGizi statusGZ = new DlgStatusGizi(null, false);
            statusGZ.setSize(812, 489);
            statusGZ.setLocationRelativeTo(internalFrame1);
            statusGZ.emptTeks();
            statusGZ.setPasien(norawat.getText(), TNoRM.getText(), TPasien.getText(), kdkamar.getText());
            statusGZ.tampil();
            statusGZ.BtnSimpan.setEnabled(true);
            statusGZ.BtnEdit.setEnabled(false);
            statusGZ.cmbstatusGZ.requestFocus();
            statusGZ.setVisible(true);
        }
    }//GEN-LAST:event_MnStatusGiziActionPerformed

    private void MnGiziBurukActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_MnGiziBurukActionPerformed
        cekDataAnak.setText("");
        Sequel.cariIsi("SELECT no_rawat FROM gizi_buruk where no_rawat=? ", dataGB, norawat.getText());
        Sequel.cariIsi("SELECT b.nm_bangsal FROM kamar k INNER JOIN bangsal b ON b.kd_bangsal = k.kd_bangsal "
                + " WHERE nm_bangsal LIKE '%anak%' and k.kd_kamar=? ", cekDataAnak, kdkamar.getText());

        if (tabMode.getRowCount() == 0) {
            JOptionPane.showMessageDialog(null, "Maaf, tabel masih kosong...!!!!");
            TCari.requestFocus();
        } else if (norawat.getText().equals("")) {
            JOptionPane.showMessageDialog(null, "Silakan klik salah satu nama pasien atau no. rekam medisnya dulu...!!!!");
            tbKamIn.requestFocus();
        } else if (cekDataAnak.getText().equals("")) {
            JOptionPane.showMessageDialog(null, "Hanya pasien yang dirawat diruang anak saja yang bisa diinput datanya...!!!!");
            tbKamIn.requestFocus();
        } else if (!dataGB.getText().equals("")) {
            x = JOptionPane.showConfirmDialog(null, "Data gizi buruk utk. pasien yg bernama " + TPasien.getText() + " sudah tersimpan, apakah mau diperbaiki...?", "Konfirmasi", JOptionPane.YES_NO_OPTION);
            if (x == JOptionPane.YES_OPTION) {
                WindowGiziBuruk.setSize(973, 573);
                WindowGiziBuruk.setLocationRelativeTo(internalFrame1);
                WindowGiziBuruk.setVisible(true);

                tampilGZburuk();
                cekdataGB();
                ChkInput.setSelected(true);
                isForm();
                BtnSimpan7.setEnabled(false);
                BtnEdit2.setEnabled(true);
                BtnHapus.setEnabled(true);
            } else {
                WindowGiziBuruk.dispose();
                emptGZburuk();
                emptTeks();
            }
        } else if (dataGB.getText().equals("")) {
            WindowGiziBuruk.setSize(973, 573);
            WindowGiziBuruk.setLocationRelativeTo(internalFrame1);
            WindowGiziBuruk.setVisible(true);

            tampilGZburuk();
            bbAwal.requestFocus();
            ChkInput.setSelected(true);
            isForm();
            BtnSimpan7.setEnabled(true);
            BtnEdit2.setEnabled(false);
            BtnHapus.setEnabled(false);
        }
    }//GEN-LAST:event_MnGiziBurukActionPerformed

    private void MnStatusGZActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_MnStatusGZActionPerformed
        dataGZ.setText("");
        dataGZ.setText(Sequel.cariIsi("SELECT COUNT(no_rawat) total_px FROM status_gizi_inap WHERE tgl_input BETWEEN "
                + " '" + Valid.SetTgl(DTPCari1.getSelectedItem() + "") + "' and '" + Valid.SetTgl(DTPCari2.getSelectedItem() + "") + "' "));

        if (dataGZ.getText().equals("0")) {
            JOptionPane.showMessageDialog(null, "Data status gizi pasien utk periode tgl. " + DTPCari1.getSelectedItem() + " s.d " + DTPCari2.getSelectedItem() + " tidak ditemukan,...!!!");
            DTPCari1.requestFocus();
        } else if (R2.isSelected() == false) {
            JOptionPane.showMessageDialog(null, "Tentukan dulu datanya berdasarkan tgl. masuk pasien,...!!!");
            DTPCari1.requestFocus();
        } else if (!dataGZ.getText().equals("0")) {
            this.setCursor(Cursor.getDefaultCursor());
            Map<String, Object> param = new HashMap<>();
            param.put("namars", var.getnamars());
            param.put("alamatrs", var.getalamatrs());
            param.put("kotars", var.getkabupatenrs());
            param.put("propinsirs", var.getpropinsirs());
            param.put("kontakrs", var.getkontakrs());
            param.put("emailrs", var.getemailrs());
            param.put("logo", Sequel.cariGambar("select logo from setting"));
            param.put("periode", "PERIODE TGL. " + DTPCari1.getSelectedItem() + " S.D " + DTPCari2.getSelectedItem());
            param.put("tgl_surat", Sequel.cariIsi("select day(now())") + " " + Sequel.bulanINDONESIA("select month(now())") + " " + Sequel.cariIsi("select year(now())"));
            Valid.MyReport("rptrekapstatusgizi.jrxml", "report", "::[ Data Rekap Status Gizi Pasien ]::",
                    " select sg.ruang_rawat, COUNT(CASE WHEN sg.status_gizi = 'BURUK' THEN 1 END) as buruk, "
                    + " COUNT(CASE WHEN sg.status_gizi = 'KURANG' THEN 1 END) as kurang, "
                    + " COUNT(CASE WHEN sg.status_gizi = 'NORMAL' THEN 1 END) as normal, "
                    + " COUNT(CASE WHEN sg.status_gizi = 'LEBIH' THEN 1 END) as lebih, "
                    + " COUNT(CASE WHEN sg.status_gizi = 'OBESITAS' THEN 1 END) as obesitas, "
                    + " COUNT(sg.no_rawat) total from status_gizi_inap sg "
                    + " inner join reg_periksa r on r.no_rawat=sg.no_rawat "
                    + " where r.tgl_registrasi BETWEEN '" + Valid.SetTgl(DTPCari1.getSelectedItem() + "") + "' AND '" + Valid.SetTgl(DTPCari2.getSelectedItem() + "") + "' "
                    + " and r.status_lanjut='ranap' group by sg.ruang_rawat order by sg.ruang_rawat desc", param);
            this.setCursor(Cursor.getDefaultCursor());

            tampil();
            emptTeks();
        }
    }//GEN-LAST:event_MnStatusGZActionPerformed

    private void BtnCloseIn8ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_BtnCloseIn8ActionPerformed
        WindowGiziBuruk.dispose();
        emptGZburuk();
        emptTeks();
        tampil();
        ChkInput.setSelected(false);
    }//GEN-LAST:event_BtnCloseIn8ActionPerformed

    private void BtnSimpan7ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_BtnSimpan7ActionPerformed
        if (bbAwal.getText().equals("") || (bbAkhir.getText().equals("")) || (pbtb.getText().equals("")) || (norwGB.getText().equals(""))) {
            JOptionPane.showMessageDialog(null, "Berat Badan Awal, Berat Badan Akhir, Panjang Badan/Tinggi Badan & data pasien tidak boleh kosong....!!!!");
            bbAwal.requestFocus();
        } else {
            Sequel.menyimpan("gizi_buruk", "'" + norwGB.getText() + "','" + bbAwal.getText() + "',"
                    + "'" + bbAkhir.getText() + "','" + pbtb.getText() + "','" + bbu.getText() + "','" + bbpb.getText() + "',"
                    + "'" + pbu.getText() + "','" + perhitunganZatGZ.getText() + "','" + diagDokterGZ.getText() + "',"
                    + "'" + pemberianNutrisi.getText() + "','" + albumin.getText() + "','" + hb.getText() + "','" + leukosit.getText() + "',"
                    + "'" + plt.getText() + "','" + asalFaskes.getText() + "','" + dateFormat.format(date) + "' ", "Data gizi buruk");

            Sequel.mengedit("kamar_inap", "no_rawat='" + norwGB.getText() + "'", "diagnosa_awal='" + diagawalGB.getText() + "' ");

            tampilGZburuk();
            emptGZburuk();
            BtnSimpan7.setEnabled(false);
            BtnEdit2.setEnabled(true);
            BtnHapus.setEnabled(true);
            BtnCloseIn8.requestFocus();
        }
    }//GEN-LAST:event_BtnSimpan7ActionPerformed

    private void norwGBKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_norwGBKeyPressed
        // TODO add your handling code here:
    }//GEN-LAST:event_norwGBKeyPressed

    private void btnFaskesActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnFaskesActionPerformed
        var.setform("DlgKamarInap");
        rujukmasuk.tampil2();
        rujukmasuk.WindowPerujuk.setSize(900, 573);
        rujukmasuk.WindowPerujuk.setLocationRelativeTo(internalFrame1);
        rujukmasuk.WindowPerujuk.setVisible(true);
        rujukmasuk.onCariPerujuk();
    }//GEN-LAST:event_btnFaskesActionPerformed

    private void btnFaskesKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_btnFaskesKeyPressed
        Valid.pindah(evt, pemberianNutrisi, albumin);
    }//GEN-LAST:event_btnFaskesKeyPressed

    private void tbGiziBurukMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tbGiziBurukMouseClicked
        if (tabMode3.getRowCount() != 0) {
            try {
                getdataGB();
                ChkInput.setSelected(true);
                isForm();
                BtnSimpan7.setEnabled(false);
                BtnEdit2.setEnabled(true);
            } catch (java.lang.NullPointerException e) {
            }
        }
    }//GEN-LAST:event_tbGiziBurukMouseClicked

    private void tbGiziBurukKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_tbGiziBurukKeyPressed
        if (tabMode3.getRowCount() != 0) {
            if ((evt.getKeyCode() == KeyEvent.VK_ENTER) || (evt.getKeyCode() == KeyEvent.VK_UP) || (evt.getKeyCode() == KeyEvent.VK_DOWN)) {
                try {
                    getdataGB();
                } catch (java.lang.NullPointerException e) {
                }
            } else if (evt.getKeyCode() == KeyEvent.VK_V) {
                if (tbGiziBuruk.getSelectedColumn() > 4) {
                    if (tbGiziBuruk.getSelectedRow() != -1) {
                        if (tbGiziBuruk.getValueAt(tbGiziBuruk.getSelectedRow(), tbGiziBuruk.getSelectedColumn()).toString().equals("false")) {
                            tbGiziBuruk.setValueAt(true, tbGiziBuruk.getSelectedRow(), tbGiziBuruk.getSelectedColumn());
                        } else {
                            tbGiziBuruk.setValueAt(false, tbGiziBuruk.getSelectedRow(), tbGiziBuruk.getSelectedColumn());
                        }
                    }
                }
            }
        }
    }//GEN-LAST:event_tbGiziBurukKeyPressed

    private void TCari3KeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_TCari3KeyPressed
        if (evt.getKeyCode() == KeyEvent.VK_ENTER) {
            BtnCari3ActionPerformed(null);
        } else if (evt.getKeyCode() == KeyEvent.VK_PAGE_DOWN) {
            BtnCari3.requestFocus();
        } else if (evt.getKeyCode() == KeyEvent.VK_PAGE_UP) {
            BtnCloseIn8.requestFocus();
        } else if (evt.getKeyCode() == KeyEvent.VK_UP) {
            tbGiziBuruk.requestFocus();
        }
    }//GEN-LAST:event_TCari3KeyPressed

    private void BtnCari3ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_BtnCari3ActionPerformed
        tampilGZburuk();
    }//GEN-LAST:event_BtnCari3ActionPerformed

    private void BtnCari3KeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_BtnCari3KeyPressed
        // TODO add your handling code here:
    }//GEN-LAST:event_BtnCari3KeyPressed

    private void BtnCari3KeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_BtnCari3KeyReleased
        // TODO add your handling code here:
    }//GEN-LAST:event_BtnCari3KeyReleased

    private void BtnAll3ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_BtnAll3ActionPerformed
        TCari3.setText("");
        tampilGZburuk();
        emptGZburuk();
    }//GEN-LAST:event_BtnAll3ActionPerformed

    private void BtnAll3KeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_BtnAll3KeyPressed
        // TODO add your handling code here:
    }//GEN-LAST:event_BtnAll3KeyPressed

    private void BtnEdit2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_BtnEdit2ActionPerformed
        if (bbAwal.getText().equals("") || (bbAkhir.getText().equals("")) || (pbtb.getText().equals("")) || (norwGB.getText().equals(""))) {
            JOptionPane.showMessageDialog(null, "Berat Badan Awal, Berat Badan Akhir, Panjang Badan/Tinggi Badan & data pasien tidak boleh kosong....!!!!");
            bbAwal.requestFocus();
        } else {
            Sequel.mengedit("gizi_buruk", "no_rawat='" + norwGB.getText() + "'", "bb_awal='" + bbAwal.getText() + "', bb_akhir='" + bbAkhir.getText() + "',"
                    + "pb_tb='" + pbtb.getText() + "', bb_u='" + bbu.getText() + "', bb_pb='" + bbpb.getText() + "', pb_u='" + pbu.getText() + "', "
                    + "penghitungan_zat_gizi='" + perhitunganZatGZ.getText() + "', diagnosa_dr_gizi='" + diagDokterGZ.getText() + "', pemberian_nutrisi='" + pemberianNutrisi.getText() + "', "
                    + "data_albumin='" + albumin.getText() + "', data_hb='" + hb.getText() + "', data_leukosit='" + leukosit.getText() + "', "
                    + "data_plt='" + plt.getText() + "', asal_rujukan='" + asalFaskes.getText() + "', tgl_input='" + dateFormat.format(date) + "' ");

            Sequel.mengedit("kamar_inap", "no_rawat='" + norwGB.getText() + "'", "diagnosa_awal='" + diagawalGB.getText() + "' ");

            tampilGZburuk();
            emptGZburuk();
        }
    }//GEN-LAST:event_BtnEdit2ActionPerformed

    private void BtnEdit2KeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_BtnEdit2KeyPressed
        // TODO add your handling code here:
    }//GEN-LAST:event_BtnEdit2KeyPressed

    private void BtnHapusActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_BtnHapusActionPerformed
        if (tabMode3.getRowCount() == 0) {
            JOptionPane.showMessageDialog(null, "Maaf, data sudah habis...!!!!");
            DTPCari9.requestFocus();
        } else if (norwGB.getText().trim().equals("")) {
            JOptionPane.showMessageDialog(null, "Maaf, Gagal menghapus. Pilih dulu data yang mau dihapus. Klik data pada tabel untuk memilih...!!!!");
        } else if (!(norwGB.getText().trim().equals(""))) {
            if (tbGiziBuruk.getSelectedRow() > -1) {
                Sequel.AutoComitFalse();
                try {
                    Sequel.queryu2("delete from gizi_buruk where no_rawat=? ", 1, new String[]{
                        norwGB.getText()
                    });
                } catch (Exception e) {
                    System.out.println("Notifikasi : " + e);
                }
                Sequel.AutoComitTrue();
                tampilGZburuk();
                emptGZburuk();
            }
        }
    }//GEN-LAST:event_BtnHapusActionPerformed

    private void BtnHapusKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_BtnHapusKeyPressed
//        if (evt.getKeyCode() == KeyEvent.VK_SPACE) {
//            BtnHapusActionPerformed(null);
//        } else {
//            Valid.pindah(evt, BtnBatal, BtnAll);
//        }
    }//GEN-LAST:event_BtnHapusKeyPressed

    private void DTPCari9MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_DTPCari9MouseClicked
        // TODO add your handling code here:
    }//GEN-LAST:event_DTPCari9MouseClicked

    private void DTPCari10MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_DTPCari10MouseClicked
        // TODO add your handling code here:
    }//GEN-LAST:event_DTPCari10MouseClicked

    private void diagawalGBKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_diagawalGBKeyPressed
        Valid.pindah(evt, umurGB, bbAwal);
    }//GEN-LAST:event_diagawalGBKeyPressed

    private void bbAwalKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_bbAwalKeyPressed
        Valid.pindah(evt, bbAwal, bbAkhir);
    }//GEN-LAST:event_bbAwalKeyPressed

    private void pbtbKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_pbtbKeyPressed
        Valid.pindah(evt, bbAkhir, bbu);
    }//GEN-LAST:event_pbtbKeyPressed

    private void bbuKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_bbuKeyPressed
        Valid.pindah(evt, pbtb, bbpb);
    }//GEN-LAST:event_bbuKeyPressed

    private void bbpbKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_bbpbKeyPressed
        Valid.pindah(evt, bbu, pbu);
    }//GEN-LAST:event_bbpbKeyPressed

    private void pbuKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_pbuKeyPressed
        Valid.pindah(evt, bbpb, perhitunganZatGZ);
    }//GEN-LAST:event_pbuKeyPressed

    private void perhitunganZatGZKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_perhitunganZatGZKeyPressed
        Valid.pindah(evt, pbu, diagDokterGZ);
    }//GEN-LAST:event_perhitunganZatGZKeyPressed

    private void diagDokterGZKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_diagDokterGZKeyPressed
        Valid.pindah(evt, perhitunganZatGZ, pemberianNutrisi);
    }//GEN-LAST:event_diagDokterGZKeyPressed

    private void pemberianNutrisiKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_pemberianNutrisiKeyPressed
        Valid.pindah(evt, diagDokterGZ, btnFaskes);
    }//GEN-LAST:event_pemberianNutrisiKeyPressed

    private void albuminKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_albuminKeyPressed
        Valid.pindah(evt, btnFaskes, hb);
    }//GEN-LAST:event_albuminKeyPressed

    private void hbKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_hbKeyPressed
        Valid.pindah(evt, albumin, leukosit);
    }//GEN-LAST:event_hbKeyPressed

    private void leukositKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_leukositKeyPressed
        Valid.pindah(evt, hb, plt);
    }//GEN-LAST:event_leukositKeyPressed

    private void bbAkhirKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_bbAkhirKeyPressed
        Valid.pindah(evt, bbAwal, pbtb);
    }//GEN-LAST:event_bbAkhirKeyPressed

    private void pltKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_pltKeyPressed
        Valid.pindah(evt, leukosit, BtnSimpan7);
    }//GEN-LAST:event_pltKeyPressed

    private void BtnCetakGBActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_BtnCetakGBActionPerformed
        dataGB.setText("");
        dataGB.setText(Sequel.cariIsi("SELECT COUNT(no_rawat) total_px FROM gizi_buruk WHERE tgl_input BETWEEN "
                + " '" + Valid.SetTgl(DTPCari9.getSelectedItem() + "") + "' and '" + Valid.SetTgl(DTPCari10.getSelectedItem() + "") + "' "));

        if (dataGB.getText().equals("0")) {
            JOptionPane.showMessageDialog(null, "Data gizi buruk pasien utk periode tgl. " + DTPCari9.getSelectedItem() + " s.d " + DTPCari10.getSelectedItem() + " tidak ditemukan,...!!!");
            DTPCari9.requestFocus();
        } else if (!dataGB.getText().equals("0")) {
            this.setCursor(Cursor.getDefaultCursor());
            Map<String, Object> param = new HashMap<>();
            param.put("periode", "PERIODE TGL. " + DTPCari9.getSelectedItem() + " S.D " + DTPCari10.getSelectedItem());
            Valid.MyReport("rptRekapGiziBuruk.jrxml", "report", "::[ Data Rekapitulasi Pasien Gizi Buruk ]::",
                    " SELECT DISTINCT gb.no_rawat, p.no_rkm_medis, p.nm_pasien, IF(p.jk='L','Laki-laki','Perempuan') jk, "
                    + " CONCAT(p.alamat,', ',kl.nm_kel,', ',kc.nm_kec,', ',kb.nm_kab) alamat, "
                    + " CONCAT(rp.umurdaftar,' ',sttsumur) umur, DATE_FORMAT(gb.tgl_input,'%d/%m/%Y') tgl_input, ki.diagnosa_awal, gb.bb_awal, "
                    + " gb.bb_akhir, gb.pb_tb, gb.bb_u, gb.bb_pb, gb.pb_u, gb.penghitungan_zat_gizi, gb.diagnosa_dr_gizi, gb.pemberian_nutrisi, "
                    + " gb.data_albumin, gb.data_hb, gb.data_leukosit, gb.data_plt, gb.asal_rujukan, (SELECT COUNT(no_rawat) total FROM gizi_buruk) total_px "
                    + " FROM gizi_buruk gb INNER JOIN reg_periksa rp ON rp.no_rawat=gb.no_rawat "
                    + " INNER JOIN pasien p ON p.no_rkm_medis=rp.no_rkm_medis "
                    + " INNER JOIN kamar_inap ki ON ki.no_rawat=gb.no_rawat "
                    + " INNER JOIN kelurahan kl ON kl.kd_kel=p.kd_kel "
                    + " INNER JOIN kecamatan kc ON kc.kd_kec=p.kd_kec "
                    + " INNER JOIN kabupaten kb ON kb.kd_kab=p.kd_kab "
                    + " where gb.tgl_input BETWEEN '" + Valid.SetTgl(DTPCari9.getSelectedItem() + "") + "' and '" + Valid.SetTgl(DTPCari10.getSelectedItem() + "") + "' ", param);
            this.setCursor(Cursor.getDefaultCursor());

            tampilGZburuk();
            emptGZburuk();
            BtnCloseIn8.requestFocus();
        }
    }//GEN-LAST:event_BtnCetakGBActionPerformed

    private void BtnCetakGBKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_BtnCetakGBKeyPressed
        // TODO add your handling code here:
    }//GEN-LAST:event_BtnCetakGBKeyPressed

    private void ChkInputActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_ChkInputActionPerformed
        isForm();
    }//GEN-LAST:event_ChkInputActionPerformed

    private void MnIndividuPxActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_MnIndividuPxActionPerformed
        if (norwGB.getText().equals("")) {
            JOptionPane.showMessageDialog(null, "Silakan pilih dulu salah satu nama pasiennya pada tabel....!!!!");
            tbGiziBuruk.requestFocus();
        } else if (!norwGB.getText().equals("")) {
            this.setCursor(Cursor.getDefaultCursor());
            Map<String, Object> param = new HashMap<>();
            param.put("namars", var.getnamars());
            param.put("alamatrs", var.getalamatrs());
            param.put("kotars", var.getkabupatenrs());
            param.put("propinsirs", var.getpropinsirs());
            param.put("kontakrs", var.getkontakrs());
            param.put("emailrs", var.getemailrs());
            param.put("logo", Sequel.cariGambar("select logo from setting"));
            Valid.MyReport("rptDataGiziBurukPX.jrxml", "report", "::[ Data Individu Pasien Gizi Buruk ]::",
                    " SELECT DISTINCT gb.no_rawat, p.no_rkm_medis, p.nm_pasien, IF(p.jk='L','Laki-laki','Perempuan') jk, "
                    + " CONCAT(p.alamat,', ',kl.nm_kel,', ',kc.nm_kec,', ',kb.nm_kab) alamat, "
                    + " CONCAT(rp.umurdaftar,' ',sttsumur) umur, DATE_FORMAT(gb.tgl_input,'%d/%m/%Y') tgl_input, ki.diagnosa_awal, gb.bb_awal, "
                    + " gb.bb_akhir, gb.pb_tb, gb.bb_u, gb.bb_pb, gb.pb_u, gb.penghitungan_zat_gizi, gb.diagnosa_dr_gizi, gb.pemberian_nutrisi, "
                    + " gb.data_albumin, gb.data_hb, gb.data_leukosit, gb.data_plt, gb.asal_rujukan FROM gizi_buruk gb "
                    + " INNER JOIN reg_periksa rp ON rp.no_rawat=gb.no_rawat "
                    + " INNER JOIN pasien p ON p.no_rkm_medis=rp.no_rkm_medis "
                    + " INNER JOIN kamar_inap ki ON ki.no_rawat=gb.no_rawat "
                    + " INNER JOIN kelurahan kl ON kl.kd_kel=p.kd_kel "
                    + " INNER JOIN kecamatan kc ON kc.kd_kec=p.kd_kec "
                    + " INNER JOIN kabupaten kb ON kb.kd_kab=p.kd_kab "
                    + " where gb.no_rawat='" + norwGB.getText() + "'", param);
            this.setCursor(Cursor.getDefaultCursor());

            tampilGZburuk();
            emptGZburuk();
            BtnCloseIn8.requestFocus();
        }
    }//GEN-LAST:event_MnIndividuPxActionPerformed

    private void MnGiziBurukPxActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_MnGiziBurukPxActionPerformed
        dataGB.setText("");
        dataGB.setText(Sequel.cariIsi("SELECT COUNT(no_rawat) total_px FROM gizi_buruk WHERE tgl_input BETWEEN "
                + " '" + Valid.SetTgl(DTPCari1.getSelectedItem() + "") + "' and '" + Valid.SetTgl(DTPCari2.getSelectedItem() + "") + "' "));

        if (dataGB.getText().equals("0")) {
            JOptionPane.showMessageDialog(null, "Data rekapitulasi gizi buruk pasien utk periode tgl. " + DTPCari1.getSelectedItem() + " s.d " + DTPCari2.getSelectedItem() + " tidak ditemukan,...!!!");
            DTPCari1.requestFocus();
        } else if (R2.isSelected() == false) {
            JOptionPane.showMessageDialog(null, "Tentukan dulu datanya berdasarkan tgl. masuk pasien,...!!!");
            DTPCari1.requestFocus();
        } else if (!dataGB.getText().equals("0")) {
            this.setCursor(Cursor.getDefaultCursor());
            Map<String, Object> param = new HashMap<>();
            param.put("periode", "PERIODE TGL. " + DTPCari1.getSelectedItem() + " S.D " + DTPCari2.getSelectedItem());
            Valid.MyReport("rptRekapGiziBuruk.jrxml", "report", "::[ Data Rekapitulasi Pasien Gizi Buruk ]::",
                    " SELECT DISTINCT gb.no_rawat, p.no_rkm_medis, p.nm_pasien, IF(p.jk='L','Laki-laki','Perempuan') jk, "
                    + " CONCAT(p.alamat,', ',kl.nm_kel,', ',kc.nm_kec,', ',kb.nm_kab) alamat, "
                    + " CONCAT(rp.umurdaftar,' ',sttsumur) umur, DATE_FORMAT(gb.tgl_input,'%d/%m/%Y') tgl_input, ki.diagnosa_awal, gb.bb_awal, "
                    + " gb.bb_akhir, gb.pb_tb, gb.bb_u, gb.bb_pb, gb.pb_u, gb.penghitungan_zat_gizi, gb.diagnosa_dr_gizi, gb.pemberian_nutrisi, "
                    + " gb.data_albumin, gb.data_hb, gb.data_leukosit, gb.data_plt, gb.asal_rujukan, (SELECT COUNT(no_rawat) total FROM gizi_buruk) total_px "
                    + " FROM gizi_buruk gb INNER JOIN reg_periksa rp ON rp.no_rawat=gb.no_rawat "
                    + " INNER JOIN pasien p ON p.no_rkm_medis=rp.no_rkm_medis "
                    + " INNER JOIN kamar_inap ki ON ki.no_rawat=gb.no_rawat "
                    + " INNER JOIN kelurahan kl ON kl.kd_kel=p.kd_kel "
                    + " INNER JOIN kecamatan kc ON kc.kd_kec=p.kd_kec "
                    + " INNER JOIN kabupaten kb ON kb.kd_kab=p.kd_kab "
                    + " where gb.tgl_input BETWEEN '" + Valid.SetTgl(DTPCari1.getSelectedItem() + "") + "' and '" + Valid.SetTgl(DTPCari2.getSelectedItem() + "") + "' ", param);
            this.setCursor(Cursor.getDefaultCursor());

            tampilGZburuk();
            emptGZburuk();
        }
    }//GEN-LAST:event_MnGiziBurukPxActionPerformed

    private void MnPencapaianAsuhanGZActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_MnPencapaianAsuhanGZActionPerformed
        if (R2.isSelected() == false) {
            JOptionPane.showMessageDialog(null, "Tentukan dulu datanya berdasarkan tgl. masuk pasien,...!!!");
            DTPCari1.requestFocus();
        } else {
            this.setCursor(Cursor.getDefaultCursor());
            Map<String, Object> param = new HashMap<>();
            param.put("namars", var.getnamars());
            param.put("alamatrs", var.getalamatrs());
            param.put("kotars", var.getkabupatenrs());
            param.put("propinsirs", var.getpropinsirs());
            param.put("kontakrs", var.getkontakrs());
            param.put("emailrs", var.getemailrs());
            param.put("logo", Sequel.cariGambar("select logo from setting"));
            param.put("periode", "PERIODE TGL. " + DTPCari1.getSelectedItem() + " S.D " + DTPCari2.getSelectedItem());
            param.put("tgl_surat", Sequel.cariIsi("select day(now())") + " " + Sequel.bulanINDONESIA("select month(now())") + " " + Sequel.cariIsi("select year(now())"));
            Valid.MyReport("rptrekappencapaiangizi.jrxml", "report", "::[ Data Pencapaian Gizi Pasien Rawat Inap ]::",
                    " SELECT a.nm_kmr, a.total jml_pasien, b.total jml_asuhan_gizi, ifnull((( b.total / a.total )* 100 ),0) persentasi "
                    + "FROM ((SELECT 'VIP Intan' AS nm_kmr, count( b.nm_gedung ) total FROM kamar_inap k INNER JOIN reg_periksa r ON k.no_rawat = r.no_rawat "
                    + "INNER JOIN kamar km ON km.kd_kamar = k.kd_kamar INNER JOIN bangsal b ON b.kd_bangsal = km.kd_bangsal WHERE k.stts_pulang <> 'Pindah Kamar'  "
                    + "AND r.tgl_registrasi BETWEEN  '" + Valid.SetTgl(DTPCari1.getSelectedItem() + "") + "' and '" + Valid.SetTgl(DTPCari2.getSelectedItem() + "") + "' AND b.kd_bangsal IN ( 'GN009', 'GN041' )  "
                    + "GROUP BY b.nm_gedung UNION ALL SELECT 'ICU/ICCU' AS nm_kmr, count( b.nm_gedung ) total FROM kamar_inap k INNER JOIN reg_periksa r ON k.no_rawat = r.no_rawat "
                    + "INNER JOIN kamar km ON km.kd_kamar = k.kd_kamar INNER JOIN bangsal b ON b.kd_bangsal = km.kd_bangsal WHERE k.stts_pulang <> 'Pindah Kamar'  "
                    + "AND r.tgl_registrasi BETWEEN  '" + Valid.SetTgl(DTPCari1.getSelectedItem() + "") + "' and '" + Valid.SetTgl(DTPCari2.getSelectedItem() + "") + "' AND b.kd_bangsal IN ( 'IC001' ) GROUP BY b.nm_gedung UNION ALL "
                    + "SELECT 'Penyakit Dalam' AS nm_kmr, count( b.nm_gedung ) total FROM kamar_inap k INNER JOIN reg_periksa r ON k.no_rawat = r.no_rawat  "
                    + "INNER JOIN kamar km ON km.kd_kamar = k.kd_kamar INNER JOIN bangsal b ON b.kd_bangsal = km.kd_bangsal WHERE k.stts_pulang <> 'Pindah Kamar'  "
                    + "AND r.tgl_registrasi BETWEEN  '" + Valid.SetTgl(DTPCari1.getSelectedItem() + "") + "' and '" + Valid.SetTgl(DTPCari2.getSelectedItem() + "") + "' AND b.nm_bangsal LIKE '%Ar-Razaq%'  "
                    + "GROUP BY b.nm_gedung UNION ALL SELECT 'Zaal' AS nm_kmr, count( b.nm_gedung ) total FROM kamar_inap k INNER JOIN reg_periksa r ON k.no_rawat = r.no_rawat "
                    + "INNER JOIN kamar km ON km.kd_kamar = k.kd_kamar INNER JOIN bangsal b ON b.kd_bangsal = km.kd_bangsal WHERE k.stts_pulang <> 'Pindah Kamar'  "
                    + "AND r.tgl_registrasi BETWEEN  '" + Valid.SetTgl(DTPCari1.getSelectedItem() + "") + "' and '" + Valid.SetTgl(DTPCari2.getSelectedItem() + "") + "' AND b.nm_bangsal LIKE '%Zaal%' GROUP BY b.nm_gedung UNION ALL "
                    + "SELECT 'Bedah' AS nm_kmr, count( b.nm_gedung ) total FROM kamar_inap k INNER JOIN reg_periksa r ON k.no_rawat = r.no_rawat "
                    + "INNER JOIN kamar km ON km.kd_kamar = k.kd_kamar INNER JOIN bangsal b ON b.kd_bangsal = km.kd_bangsal WHERE k.stts_pulang <> 'Pindah Kamar'  "
                    + "AND r.tgl_registrasi BETWEEN  '" + Valid.SetTgl(DTPCari1.getSelectedItem() + "") + "' and '" + Valid.SetTgl(DTPCari2.getSelectedItem() + "") + "' AND b.nm_bangsal LIKE '%Bedah%' GROUP BY b.nm_gedung UNION ALL "
                    + "SELECT 'Anak' AS nm_kmr, count(- 1 ) total FROM kamar_inap k INNER JOIN reg_periksa r ON k.no_rawat = r.no_rawat  "
                    + "INNER JOIN kamar km ON km.kd_kamar = k.kd_kamar INNER JOIN bangsal b ON b.kd_bangsal = km.kd_bangsal WHERE k.stts_pulang <> 'Pindah Kamar'  "
                    + "AND r.tgl_registrasi BETWEEN  '" + Valid.SetTgl(DTPCari1.getSelectedItem() + "") + "' and '" + Valid.SetTgl(DTPCari2.getSelectedItem() + "") + "' AND b.nm_bangsal LIKE '%Ar-Rahman%' OR b.nm_bangsal LIKE '%Ext Bed Anak%' UNION ALL "
                    + "SELECT 'Al-Hakim' AS nm_kmr, count( b.nm_gedung ) total FROM kamar_inap k INNER JOIN reg_periksa r ON k.no_rawat = r.no_rawat "
                    + "INNER JOIN kamar km ON km.kd_kamar = k.kd_kamar INNER JOIN bangsal b ON b.kd_bangsal = km.kd_bangsal WHERE k.stts_pulang <> 'Pindah Kamar'  "
                    + "AND r.tgl_registrasi BETWEEN  '" + Valid.SetTgl(DTPCari1.getSelectedItem() + "") + "' and '" + Valid.SetTgl(DTPCari2.getSelectedItem() + "") + "' AND b.nm_bangsal LIKE '%Al-Hakim%' GROUP BY b.nm_gedung UNION ALL "
                    + "SELECT 'As-Sami' AS nm_kmr, count(- 1 ) total FROM kamar_inap k INNER JOIN reg_periksa r ON k.no_rawat = r.no_rawat "
                    + "INNER JOIN kamar km ON km.kd_kamar = k.kd_kamar INNER JOIN bangsal b ON b.kd_bangsal = km.kd_bangsal WHERE k.stts_pulang <> 'Pindah Kamar'  "
                    + "AND r.tgl_registrasi BETWEEN  '" + Valid.SetTgl(DTPCari1.getSelectedItem() + "") + "' and '" + Valid.SetTgl(DTPCari2.getSelectedItem() + "") + "' AND b.nm_bangsal LIKE '%As-Sami%' UNION ALL SELECT 'Ar-Raudah' AS nm_kmr, "
                    + "count(- 1 ) total FROM kamar_inap k INNER JOIN reg_periksa r ON k.no_rawat = r.no_rawat INNER JOIN kamar km ON km.kd_kamar = k.kd_kamar "
                    + "INNER JOIN bangsal b ON b.kd_bangsal = km.kd_bangsal WHERE k.stts_pulang <> 'Pindah Kamar' AND r.tgl_registrasi  "
                    + "BETWEEN  '" + Valid.SetTgl(DTPCari1.getSelectedItem() + "") + "' and '" + Valid.SetTgl(DTPCari2.getSelectedItem() + "") + "' AND b.nm_bangsal LIKE '%Ar-Raudah%' UNION ALL SELECT 'Bersalin' AS nm_kmr, count(- 1 ) total  "
                    + "FROM kamar_inap k INNER JOIN reg_periksa r ON k.no_rawat = r.no_rawat INNER JOIN kamar km ON km.kd_kamar = k.kd_kamar "
                    + "INNER JOIN bangsal b ON b.kd_bangsal = km.kd_bangsal WHERE k.stts_pulang <> 'Pindah Kamar' AND r.tgl_registrasi  "
                    + "BETWEEN  '" + Valid.SetTgl(DTPCari1.getSelectedItem() + "") + "' and '" + Valid.SetTgl(DTPCari2.getSelectedItem() + "") + "' AND b.nm_bangsal LIKE '%Obgyn%' UNION ALL SELECT 'Isolasi Covid-19 (Dewasa)' AS nm_kmr, "
                    + "count(- 1 ) total FROM kamar_inap k INNER JOIN reg_periksa r ON k.no_rawat = r.no_rawat INNER JOIN kamar km ON km.kd_kamar = k.kd_kamar "
                    + "INNER JOIN bangsal b ON b.kd_bangsal = km.kd_bangsal WHERE k.stts_pulang <> 'Pindah Kamar' AND r.tgl_registrasi  "
                    + "BETWEEN  '" + Valid.SetTgl(DTPCari1.getSelectedItem() + "") + "' and '" + Valid.SetTgl(DTPCari2.getSelectedItem() + "") + "' AND b.nm_bangsal LIKE '%ISOLASI COVID19%' UNION ALL SELECT 'Isolasi Covid-19 (Bayi)' AS nm_kmr, "
                    + "count(- 1 ) total FROM kamar_inap k INNER JOIN reg_periksa r ON k.no_rawat = r.no_rawat INNER JOIN kamar km ON km.kd_kamar = k.kd_kamar "
                    + "INNER JOIN bangsal b ON b.kd_bangsal = km.kd_bangsal WHERE k.stts_pulang <> 'Pindah Kamar' AND r.tgl_registrasi  "
                    + "BETWEEN  '" + Valid.SetTgl(DTPCari1.getSelectedItem() + "") + "' and '" + Valid.SetTgl(DTPCari2.getSelectedItem() + "") + "' AND b.nm_bangsal LIKE '%ISOLASI BAYI COVID19%') AS a LEFT JOIN (SELECT 'VIP Intan' AS nm_kmr, "
                    + "count(- 1 ) total FROM (SELECT DISTINCT k.no_rawat FROM kamar_inap k INNER JOIN reg_periksa r ON k.no_rawat = r.no_rawat  "
                    + "INNER JOIN kamar km ON km.kd_kamar = k.kd_kamar INNER JOIN bangsal b ON b.kd_bangsal = km.kd_bangsal "
                    + "INNER JOIN rawat_inap_pr rw ON rw.no_rawat = k.no_rawat INNER JOIN jns_perawatan_inap j ON j.kd_jenis_prw = rw.kd_jenis_prw WHERE k.stts_pulang <> 'Pindah Kamar'  "
                    + "AND r.tgl_registrasi BETWEEN  '" + Valid.SetTgl(DTPCari1.getSelectedItem() + "") + "' and '" + Valid.SetTgl(DTPCari2.getSelectedItem() + "") + "' AND b.kd_bangsal IN ( 'GN009', 'GN041' ) AND j.STATUS = '1' AND j.nm_perawatan LIKE '%screen%') AS q UNION ALL "
                    + "SELECT 'ICU/ICCU' AS nm_kmr, count(- 1 ) total FROM (SELECT DISTINCT k.no_rawat FROM kamar_inap k INNER JOIN reg_periksa r ON k.no_rawat = r.no_rawat "
                    + "INNER JOIN kamar km ON km.kd_kamar = k.kd_kamar INNER JOIN bangsal b ON b.kd_bangsal = km.kd_bangsal INNER JOIN rawat_inap_pr rw ON rw.no_rawat = k.no_rawat "
                    + "INNER JOIN jns_perawatan_inap j ON j.kd_jenis_prw = rw.kd_jenis_prw WHERE k.stts_pulang <> 'Pindah Kamar' AND r.tgl_registrasi  "
                    + "BETWEEN  '" + Valid.SetTgl(DTPCari1.getSelectedItem() + "") + "' and '" + Valid.SetTgl(DTPCari2.getSelectedItem() + "") + "' AND b.kd_bangsal IN ( 'IC001' ) AND j.STATUS = '1' AND j.nm_perawatan LIKE '%screen%') AS r UNION ALL "
                    + "SELECT 'Penyakit Dalam' AS nm_kmr, count(- 1 ) total FROM (SELECT DISTINCT k.no_rawat FROM kamar_inap k INNER JOIN reg_periksa r ON k.no_rawat = r.no_rawat "
                    + "INNER JOIN kamar km ON km.kd_kamar = k.kd_kamar INNER JOIN bangsal b ON b.kd_bangsal = km.kd_bangsal INNER JOIN rawat_inap_pr rw ON rw.no_rawat = k.no_rawat "
                    + "INNER JOIN jns_perawatan_inap j ON j.kd_jenis_prw = rw.kd_jenis_prw WHERE k.stts_pulang <> 'Pindah Kamar' AND r.tgl_registrasi  "
                    + "BETWEEN  '" + Valid.SetTgl(DTPCari1.getSelectedItem() + "") + "' and '" + Valid.SetTgl(DTPCari2.getSelectedItem() + "") + "' AND b.nm_bangsal LIKE '%Ar-Razaq%' AND j.STATUS = '1' AND j.nm_perawatan LIKE '%screen%') AS s UNION ALL "
                    + "SELECT 'Zaal' AS nm_kmr, count(- 1 ) total FROM (SELECT DISTINCT k.no_rawat FROM kamar_inap k INNER JOIN reg_periksa r ON k.no_rawat = r.no_rawat "
                    + "INNER JOIN kamar km ON km.kd_kamar = k.kd_kamar INNER JOIN bangsal b ON b.kd_bangsal = km.kd_bangsal INNER JOIN rawat_inap_pr rw ON rw.no_rawat = k.no_rawat "
                    + "INNER JOIN jns_perawatan_inap j ON j.kd_jenis_prw = rw.kd_jenis_prw WHERE k.stts_pulang <> 'Pindah Kamar' AND r.tgl_registrasi  "
                    + "BETWEEN  '" + Valid.SetTgl(DTPCari1.getSelectedItem() + "") + "' and '" + Valid.SetTgl(DTPCari2.getSelectedItem() + "") + "' AND b.nm_bangsal LIKE '%Zaal%' AND j.STATUS = '1' AND j.nm_perawatan LIKE '%screen%') AS t UNION ALL "
                    + "SELECT 'Bedah' AS nm_kmr, count(- 1 ) total FROM (SELECT DISTINCT k.no_rawat FROM kamar_inap k INNER JOIN reg_periksa r ON k.no_rawat = r.no_rawat "
                    + "INNER JOIN kamar km ON km.kd_kamar = k.kd_kamar INNER JOIN bangsal b ON b.kd_bangsal = km.kd_bangsal INNER JOIN rawat_inap_pr rw ON rw.no_rawat = k.no_rawat "
                    + "INNER JOIN jns_perawatan_inap j ON j.kd_jenis_prw = rw.kd_jenis_prw WHERE k.stts_pulang <> 'Pindah Kamar' AND r.tgl_registrasi  "
                    + "BETWEEN  '" + Valid.SetTgl(DTPCari1.getSelectedItem() + "") + "' and '" + Valid.SetTgl(DTPCari2.getSelectedItem() + "") + "' AND b.nm_bangsal LIKE '%Bedah%' AND j.STATUS = '1' AND j.nm_perawatan LIKE '%screen%') AS u UNION ALL "
                    + "SELECT 'Anak' AS nm_kmr, count(- 1 ) total FROM (SELECT DISTINCT k.no_rawat FROM kamar_inap k INNER JOIN reg_periksa r ON k.no_rawat = r.no_rawat "
                    + "INNER JOIN kamar km ON km.kd_kamar = k.kd_kamar INNER JOIN bangsal b ON b.kd_bangsal = km.kd_bangsal INNER JOIN rawat_inap_pr rw ON rw.no_rawat = k.no_rawat "
                    + "INNER JOIN jns_perawatan_inap j ON j.kd_jenis_prw = rw.kd_jenis_prw  WHERE k.stts_pulang <> 'Pindah Kamar' AND r.tgl_registrasi  "
                    + "BETWEEN  '" + Valid.SetTgl(DTPCari1.getSelectedItem() + "") + "' and '" + Valid.SetTgl(DTPCari2.getSelectedItem() + "") + "' AND ( b.nm_bangsal LIKE '%Ar-Rahman%' OR b.nm_bangsal LIKE '%Ext Bed Anak%' ) AND j.STATUS = '1'  "
                    + "AND j.nm_perawatan LIKE '%screen%') AS v UNION ALL SELECT 'Al-Hakim' AS nm_kmr, count(- 1 ) total FROM (SELECT DISTINCT k.no_rawat FROM "
                    + "kamar_inap k INNER JOIN reg_periksa r ON k.no_rawat = r.no_rawat INNER JOIN kamar km ON km.kd_kamar = k.kd_kamar "
                    + "INNER JOIN bangsal b ON b.kd_bangsal = km.kd_bangsal INNER JOIN rawat_inap_pr rw ON rw.no_rawat = k.no_rawat "
                    + "INNER JOIN jns_perawatan_inap j ON j.kd_jenis_prw = rw.kd_jenis_prw WHERE k.stts_pulang <> 'Pindah Kamar' AND r.tgl_registrasi  "
                    + "BETWEEN  '" + Valid.SetTgl(DTPCari1.getSelectedItem() + "") + "' and '" + Valid.SetTgl(DTPCari2.getSelectedItem() + "") + "' AND b.nm_bangsal LIKE '%Al-Hakim%' AND j.STATUS = '1' AND j.nm_perawatan LIKE '%screen%') AS w UNION ALL "
                    + "SELECT 'As-Sami' AS nm_kmr, count(- 1 ) total FROM (SELECT DISTINCT k.no_rawat FROM kamar_inap k INNER JOIN reg_periksa r ON k.no_rawat = r.no_rawat "
                    + "INNER JOIN kamar km ON km.kd_kamar = k.kd_kamar INNER JOIN bangsal b ON b.kd_bangsal = km.kd_bangsal INNER JOIN rawat_inap_pr rw ON rw.no_rawat = k.no_rawat "
                    + "INNER JOIN jns_perawatan_inap j ON j.kd_jenis_prw = rw.kd_jenis_prw WHERE k.stts_pulang <> 'Pindah Kamar' AND r.tgl_registrasi  "
                    + "BETWEEN  '" + Valid.SetTgl(DTPCari1.getSelectedItem() + "") + "' and '" + Valid.SetTgl(DTPCari2.getSelectedItem() + "") + "' AND b.nm_bangsal LIKE '%As-Sami%' AND j.STATUS = '1' AND j.nm_perawatan LIKE '%screen%') AS x UNION ALL "
                    + "SELECT 'Ar-Raudah' AS nm_kmr, count(- 1 ) total FROM (SELECT DISTINCT k.no_rawat FROM kamar_inap k INNER JOIN reg_periksa r ON k.no_rawat = r.no_rawat "
                    + "INNER JOIN kamar km ON km.kd_kamar = k.kd_kamar INNER JOIN bangsal b ON b.kd_bangsal = km.kd_bangsal INNER JOIN rawat_inap_pr rw ON rw.no_rawat = k.no_rawat "
                    + "INNER JOIN jns_perawatan_inap j ON j.kd_jenis_prw = rw.kd_jenis_prw WHERE k.stts_pulang <> 'Pindah Kamar' AND r.tgl_registrasi  "
                    + "BETWEEN  '" + Valid.SetTgl(DTPCari1.getSelectedItem() + "") + "' and '" + Valid.SetTgl(DTPCari2.getSelectedItem() + "") + "' AND b.nm_bangsal LIKE '%Ar-Raudah%' AND j.STATUS = '1' AND j.nm_perawatan LIKE '%screen%') AS y UNION ALL "
                    + "SELECT 'Bersalin' AS nm_kmr, count(- 1 ) total FROM (SELECT DISTINCT k.no_rawat FROM kamar_inap k INNER JOIN reg_periksa r ON k.no_rawat = r.no_rawat "
                    + "INNER JOIN kamar km ON km.kd_kamar = k.kd_kamar INNER JOIN bangsal b ON b.kd_bangsal = km.kd_bangsal INNER JOIN rawat_inap_pr rw ON rw.no_rawat = k.no_rawat "
                    + "INNER JOIN jns_perawatan_inap j ON j.kd_jenis_prw = rw.kd_jenis_prw WHERE k.stts_pulang <> 'Pindah Kamar' AND r.tgl_registrasi  "
                    + "BETWEEN  '" + Valid.SetTgl(DTPCari1.getSelectedItem() + "") + "' and '" + Valid.SetTgl(DTPCari2.getSelectedItem() + "") + "' AND b.nm_bangsal LIKE '%Obgyn%' AND j.STATUS = '1' AND j.nm_perawatan LIKE '%screen%') AS z UNION ALL "
                    + "SELECT 'Isolasi Covid-19 (Dewasa)' AS nm_kmr, count(- 1 ) total FROM (SELECT DISTINCT k.no_rawat FROM kamar_inap k INNER JOIN reg_periksa r ON k.no_rawat = r.no_rawat "
                    + "INNER JOIN kamar km ON km.kd_kamar = k.kd_kamar INNER JOIN bangsal b ON b.kd_bangsal = km.kd_bangsal INNER JOIN rawat_inap_pr rw ON rw.no_rawat = k.no_rawat "
                    + "INNER JOIN jns_perawatan_inap j ON j.kd_jenis_prw = rw.kd_jenis_prw WHERE k.stts_pulang <> 'Pindah Kamar' AND r.tgl_registrasi  "
                    + "BETWEEN  '" + Valid.SetTgl(DTPCari1.getSelectedItem() + "") + "' and '" + Valid.SetTgl(DTPCari2.getSelectedItem() + "") + "' AND b.nm_bangsal LIKE '%ISOLASI COVID19%' AND j.STATUS = '1' AND j.nm_perawatan LIKE '%screen%') AS aa UNION ALL "
                    + "SELECT 'Isolasi Covid-19 (Bayi)' AS nm_kmr, count(- 1 ) total FROM (SELECT DISTINCT k.no_rawat FROM kamar_inap k INNER JOIN reg_periksa r ON k.no_rawat = r.no_rawat "
                    + "INNER JOIN kamar km ON km.kd_kamar = k.kd_kamar INNER JOIN bangsal b ON b.kd_bangsal = km.kd_bangsal INNER JOIN rawat_inap_pr rw ON rw.no_rawat = k.no_rawat "
                    + "INNER JOIN jns_perawatan_inap j ON j.kd_jenis_prw = rw.kd_jenis_prw WHERE k.stts_pulang <> 'Pindah Kamar' AND r.tgl_registrasi  "
                    + "BETWEEN  '" + Valid.SetTgl(DTPCari1.getSelectedItem() + "") + "' and '" + Valid.SetTgl(DTPCari2.getSelectedItem() + "") + "' AND b.nm_bangsal LIKE '%ISOLASI BAYI COVID19%' AND j.STATUS = '1' AND j.nm_perawatan LIKE '%screen%') AS ab) AS b  "
                    + "ON a.nm_kmr = b.nm_kmr) ORDER BY a.nm_kmr", param);
            this.setCursor(Cursor.getDefaultCursor());

            tampil();
            emptTeks();
        }
    }//GEN-LAST:event_MnPencapaianAsuhanGZActionPerformed

    private void MnBatalPulangActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_MnBatalPulangActionPerformed
        cekPXbpjs = 0;
        cekPXbpjs = Sequel.cariInteger("select count(1) cek from bridging_sep where jnspelayanan='1' and no_rawat='" + norawat.getText() + "'");

        if (tabMode.getRowCount() == 0) {
            JOptionPane.showMessageDialog(null, "Maaf, tabel masih kosong...!!!!");
            TCari.requestFocus();
        } else if (TPasien.getText().trim().equals("")) {
            JOptionPane.showMessageDialog(null, "Maaf, Silahkan anda pilih dulu pasien...!!!");
            tbKamIn.requestFocus();
        } else {
            if (var.getkode().equals("Admin Utama")) {
                x = JOptionPane.showConfirmDialog(null, "Apakah anda yakin akan membatalkan transaksi pasien pulang ?", "Konfirmasi", JOptionPane.YES_NO_OPTION);
                if (x == JOptionPane.YES_OPTION) {
                    i = 0;
                    i = Sequel.cariInteger("select count(billing.no_rawat) from billing where billing.no_rawat='" + norawat.getText() + "'");
                    if (i > 0) {
                        JOptionPane.showMessageDialog(null, "Notanya sudah tersimpan, hapus dulu notanya dibilling pembayaran..!!!");
                        MnBillingActionPerformed(evt);
                    } else {
                        try {
                            if ((Sequel.cariInteger("select count(1) from pasien_mati where no_rkm_medis = '" + TNoRM.getText() + "'")) > 0) {
                                Sequel.meghapus("pasien_mati", "no_rkm_medis", TNoRM.getText());
                            }

                            if ((Sequel.cariInteger("select count(1) from ranap_aps where no_rawat = '" + norawat.getText() + "'")) > 0) {
                                Sequel.meghapus("ranap_aps", "no_rawat", norawat.getText());
                            }

                            Sequel.queryu("update kamar_inap set tgl_keluar = '0000-00-00', jam_keluar = '00:00:00', lama = '0', ttl_biaya = '0', stts_pulang = '-' "
                                    + "where no_rawat = '" + norawat.getText() + "' and stts_pulang not in ('-','Pindah Kamar')");

                            //update tanggal pulang didata bridging sep
                            if (cekPXbpjs >= 1) {
                                Sequel.mengedit("bridging_sep", "no_rawat='" + norawat.getText() + "' and jnspelayanan='1'", "tglpulang='0000-00-00 00:00:00'");
                            }
                            JOptionPane.showMessageDialog(rootPane, "Proses pembatalan pulang telah berhasil dilakukan...!!");
                            tampil();
                        } catch (Exception e) {
                            JOptionPane.showMessageDialog(null, "Ada kesalahan query = " + e);
                        }
                    }
                }
            } else {
                JOptionPane.showMessageDialog(null, "Batal pasien pulang hrs. dicek transaksinya dulu, kordinasikan lagi dg. petugas terkait...!!!");
                TCari.requestFocus();
            }
        }
    }//GEN-LAST:event_MnBatalPulangActionPerformed

    private void MnRujukSisruteActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_MnRujukSisruteActionPerformed
        if (tabMode.getRowCount() == 0) {
            JOptionPane.showMessageDialog(null, "Maaf, tabel masih kosong...!!!!");
            TCari.requestFocus();
        } else if (tbKamIn.getValueAt(tbKamIn.getSelectedRow(), 0).toString().equals("")) {
            try {
                psanak = koneksi.prepareStatement(
                        "select pasien.no_rkm_medis,pasien.nm_pasien,ranap_gabung.no_rawat2,concat(reg_periksa.umurdaftar,' ',reg_periksa.sttsumur)as umur,pasien.no_peserta, "
                        + "concat(pasien.alamatpj,', ',pasien.kelurahanpj,', ',pasien.kecamatanpj,', ',pasien.kabupatenpj) as alamat "
                        + "from reg_periksa inner join pasien inner join ranap_gabung on "
                        + "pasien.no_rkm_medis=reg_periksa.no_rkm_medis and ranap_gabung.no_rawat2=reg_periksa.no_rawat where ranap_gabung.no_rawat=?");
                try {
                    psanak.setString(1, tbKamIn.getValueAt(tbKamIn.getSelectedRow() - 1, 0).toString());
                    rs2 = psanak.executeQuery();
                    if (rs2.next()) {
                        if (var.getkode().equals("Admin Utama") || (var.getsisrute_rujukan_keluar())) {
                            this.setCursor(Cursor.getPredefinedCursor(Cursor.WAIT_CURSOR));
                            var.setform("DlgKamarInap");
                            SisruteRujukanKeluar dlgki = new SisruteRujukanKeluar(null, false);
                            dlgki.setSize(internalFrame1.getWidth() - 20, internalFrame1.getHeight() - 20);
                            dlgki.setLocationRelativeTo(internalFrame1);
                            dlgki.isCek();
                            dlgki.setPasien2(rs2.getString("no_rawat2"));
                            dlgki.JenisRujukan.setSelectedItem(1);
                            dlgki.setVisible(true);
                            this.setCursor(Cursor.getDefaultCursor());
                        } else {
                            JOptionPane.showMessageDialog(null, "Hanya petugas yang memiliki hak akses saja yang bisa menggunakan fitur ini...!!!");
                        }
                    } else {
                        JOptionPane.showMessageDialog(null, "Maaf, Silahkan anda pilih dulu pasien...!!!");
                        tbKamIn.requestFocus();
                    }
                } catch (Exception ex) {
                    System.out.println("Notifikasi : " + ex);
                } finally {
                    if (rs2 != null) {
                        rs2.close();
                    }
                    if (psanak != null) {
                        psanak.close();
                    }
                }
            } catch (Exception e) {
                System.out.println(e);
            }
        } else {
            if (var.getkode().equals("Admin Utama") || (var.getsisrute_rujukan_keluar())) {
                this.setCursor(Cursor.getPredefinedCursor(Cursor.WAIT_CURSOR));
                var.setform("DlgKamarInap");
                SisruteRujukanKeluar dlgki = new SisruteRujukanKeluar(null, false);
                dlgki.setSize(internalFrame1.getWidth() - 20, internalFrame1.getHeight() - 20);
                dlgki.setLocationRelativeTo(internalFrame1);
                dlgki.isCek();
                dlgki.setPasien2(norawat.getText());
                dlgki.JenisRujukan.setSelectedItem(1);
                dlgki.setVisible(true);
                this.setCursor(Cursor.getDefaultCursor());
            } else {
                JOptionPane.showMessageDialog(null, "Hanya petugas yang memiliki hak akses saja yang bisa menggunakan fitur ini...!!!");
            }
        }
    }//GEN-LAST:event_MnRujukSisruteActionPerformed

    private void BtnCloseIn9ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_BtnCloseIn9ActionPerformed
        WindowStatusPulang.dispose();
        tampil();
        emptTeks();
    }//GEN-LAST:event_BtnCloseIn9ActionPerformed

    private void BtnSimpan8ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_BtnSimpan8ActionPerformed
        if (cmbSttsPlg.getSelectedItem().equals("- pilih salah satu -")) {
            JOptionPane.showMessageDialog(null, "Silahkan pilih dulu salah satu jenis status pulang pasien yang akan diganti....");
            cmbSttsPlg.requestFocus();
        } else if (cmbSttsPlg.getSelectedItem().equals("Dirujuk")) {
            Sequel.meghapus("rujuk", "no_rawat", norawat.getText());
            Sequel.meghapus("pasien_mati", "no_rkm_medis", TNoRM.getText());
            Sequel.meghapus("ranap_aps", "no_rawat", norawat.getText());

            DlgRujuk dlgrjk = new DlgRujuk(null, false);
            dlgrjk.setSize(863, 494);
            dlgrjk.setLocationRelativeTo(internalFrame1);
            dlgrjk.emptTeks();
            dlgrjk.isCek();
            dlgrjk.setNoRm(norawat.getText(), DTPCari1.getDate(), DTPCari2.getDate());
            dlgrjk.tampil();
            dlgrjk.setVisible(true);
            dlgrjk.btnFaskes.requestFocus();

            Sequel.mengedit("kamar_inap", "no_rawat='" + norawat.getText() + "' and stts_pulang='" + status_pulang.getText() + "'", "stts_pulang='Dirujuk' ");
            tampil();
            emptTeks();
            WindowStatusPulang.dispose();

        } else if (cmbSttsPlg.getSelectedItem().equals("APS")) {

            x = JOptionPane.showConfirmDialog(null, "Apakah pasien ini pulang dengan cara APS ?", "Konfirmasi", JOptionPane.YES_NO_OPTION);
            if (x == JOptionPane.YES_OPTION) {
                var.setform("DlgKamarInap");
                WindowStatusPulang.dispose();
                WindowPulangAPS.setSize(455, 289);
                WindowPulangAPS.setLocationRelativeTo(internalFrame1);
                WindowPulangAPS.setVisible(true);

                TCari4.setText("");
                alasanAPS.setText("");
                ketAPS.setText("");
                kdAPS = "";
                BtnEdit3.setEnabled(true);
                BtnSimpan9.setEnabled(false);
                BtnCloseIn11.setEnabled(true);
                tampilAPS();

            } else {
                WindowPulangAPS.dispose();
                cmbStatus.setSelectedIndex(0);
                cmbSttsPlg.setSelectedIndex(0);
            }

        } else if (cmbSttsPlg.getSelectedItem().equals("Meninggal >= 48 Jam")) {
            Sequel.meghapus("rujuk", "no_rawat", norawat.getText());
            Sequel.meghapus("pasien_mati", "no_rkm_medis", TNoRM.getText());
            Sequel.meghapus("ranap_aps", "no_rawat", norawat.getText());

            Sequel.menyimpan("pasien_mati", "'" + TOut.getText() + "','" + JamPulang.getText() + "','"
                    + TNoRM.getText() + "','Meninggal >= 48 Jam diruang " + ruangrawat.getText() + "',"
                    + "'Rumah Sakit','-','-','-','-','Ruangan Inap','-'", "pasien");

            Sequel.mengedit("kamar_inap", "no_rawat='" + norawat.getText() + "' and stts_pulang='" + status_pulang.getText() + "'", "stts_pulang='Meninggal >= 48 Jam' ");
            tampil();

            x = JOptionPane.showConfirmDialog(null, "Apakah anda akan mencetak surat keterangan kematian ?", "Konfirmasi", JOptionPane.YES_NO_OPTION);
            if (x == JOptionPane.YES_OPTION) {
                ctkSuratMati();
            } else {
                BtnCari.requestFocus();
            }

            emptTeks();
            WindowStatusPulang.dispose();

        } else if (cmbSttsPlg.getSelectedItem().equals("Meninggal < 48 Jam")) {
            Sequel.meghapus("rujuk", "no_rawat", norawat.getText());
            Sequel.meghapus("pasien_mati", "no_rkm_medis", TNoRM.getText());
            Sequel.meghapus("ranap_aps", "no_rawat", norawat.getText());

            Sequel.menyimpan("pasien_mati", "'" + TOut.getText() + "','" + JamPulang.getText() + "','"
                    + TNoRM.getText() + "','Meninggal < 48 Jam diruang " + ruangrawat.getText() + "',"
                    + "'Rumah Sakit','-','-','-','-','Ruangan Inap','-'", "pasien");

            Sequel.mengedit("kamar_inap", "no_rawat='" + norawat.getText() + "' and stts_pulang='" + status_pulang.getText() + "'", "stts_pulang='Meninggal < 48 Jam' ");
            tampil();

            x = JOptionPane.showConfirmDialog(null, "Apakah anda akan mencetak surat keterangan kematian ?", "Konfirmasi", JOptionPane.YES_NO_OPTION);
            if (x == JOptionPane.YES_OPTION) {
                ctkSuratMati();
            } else {
                BtnCari.requestFocus();
            }

            emptTeks();
            WindowStatusPulang.dispose();

        } else if (cmbSttsPlg.getSelectedItem().equals("Sembuh/BLPL")) {
            Sequel.meghapus("rujuk", "no_rawat", norawat.getText());
            Sequel.meghapus("pasien_mati", "no_rkm_medis", TNoRM.getText());
            Sequel.meghapus("ranap_aps", "no_rawat", norawat.getText());
            Sequel.mengedit("kamar_inap", "no_rawat='" + norawat.getText() + "' and stts_pulang='" + status_pulang.getText() + "'", "stts_pulang='Sembuh/BLPL' ");
            tampil();
            emptTeks();
            WindowStatusPulang.dispose();

        } else if (cmbSttsPlg.getSelectedItem().equals("Kabur")) {
            Sequel.meghapus("rujuk", "no_rawat", norawat.getText());
            Sequel.meghapus("pasien_mati", "no_rkm_medis", TNoRM.getText());
            Sequel.meghapus("ranap_aps", "no_rawat", norawat.getText());
            Sequel.mengedit("kamar_inap", "no_rawat='" + norawat.getText() + "' and stts_pulang='" + status_pulang.getText() + "'", "stts_pulang='Kabur' ");
            tampil();
            emptTeks();
            WindowStatusPulang.dispose();
        }
    }//GEN-LAST:event_BtnSimpan8ActionPerformed

    private void cmbSttsPlgKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_cmbSttsPlgKeyPressed
        Valid.pindah(evt, BtnSimpan8, cmbSttsPlg);
    }//GEN-LAST:event_cmbSttsPlgKeyPressed

    private void cmbSttsPlgMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_cmbSttsPlgMouseClicked
        cmbSttsPlg.setEditable(false);
    }//GEN-LAST:event_cmbSttsPlgMouseClicked

    private void MnStatusPulangActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_MnStatusPulangActionPerformed
        if (status_pulang.getText().equals("-") || (status_pulang.getText().equals("Pindah Kamar"))) {
            JOptionPane.showMessageDialog(null, "Hanya status pulang pasien [SELAIN] - atau Pindah Kamar yang bisa diganti dari fitur ini....");
            tampil();
        } else {
            x = JOptionPane.showConfirmDialog(null, "Apakah anda benar akan mengganti status pulang pasien rawat inap ?", "Konfirmasi", JOptionPane.YES_NO_OPTION);
            if (x == JOptionPane.YES_OPTION) {
                if (tabMode.getRowCount() == 0) {
                    JOptionPane.showMessageDialog(null, "Maaf, tabel masih kosong...!!!!");
                    TCari.requestFocus();
                } else if (TPasien.getText().trim().equals("")) {
                    JOptionPane.showMessageDialog(null, "Maaf, Silahkan anda pilih dulu pasien...!!!");
                    tbKamIn.requestFocus();
                } else {
                    WindowStatusPulang.setLocationRelativeTo(internalFrame1);
                    WindowStatusPulang.setVisible(true);
                    cmbSttsPlg.setSelectedIndex(0);
                    cmbSttsPlg.requestFocus();
                }
            } else {
                BtnCari.requestFocus();
            }
        }
    }//GEN-LAST:event_MnStatusPulangActionPerformed

    private void norawatCopyKeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_norawatCopyKeyTyped
        // TODO add your handling code here:
    }//GEN-LAST:event_norawatCopyKeyTyped

    private void norawatCopyKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_norawatCopyKeyPressed
        // TODO add your handling code here:
    }//GEN-LAST:event_norawatCopyKeyPressed

    private void noRMCopyKeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_noRMCopyKeyTyped
        // TODO add your handling code here:
    }//GEN-LAST:event_noRMCopyKeyTyped

    private void noRMCopyKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_noRMCopyKeyPressed
        // TODO add your handling code here:
    }//GEN-LAST:event_noRMCopyKeyPressed

    private void ppDataPonekBtnPrintActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_ppDataPonekBtnPrintActionPerformed
        cekKamar = 0;
        cekKelamin = "";
        cekDataPersalinan = "";
        cekKamar = Sequel.cariInteger("SELECT count(1) tes FROM kamar_inap k INNER JOIN kamar r ON r.kd_kamar = k.kd_kamar "
                + "INNER JOIN bangsal b ON b.kd_bangsal = r.kd_bangsal "
                + "WHERE (b.nm_bangsal not LIKE '%BAYI%' and b.nm_bangsal not like '%anak%') AND k.no_rawat = '" + norawat.getText() + "'");

        cekKelamin = Sequel.cariIsi("select jk from pasien where no_rkm_medis='" + TNoRM.getText() + "'");
        cekDataPersalinan = Sequel.cariIsi("select no_rawat from data_ponek where no_rawat='" + norawat.getText() + "'");

        if (tabMode.getRowCount() == 0) {
            JOptionPane.showMessageDialog(null, "Maaf, tabel masih kosong...!!!!");
            TCari.requestFocus();
        } else if (cekKamar == 0) {
            JOptionPane.showMessageDialog(null, "Data ponek bukan untuk pasien bayi atau anak...!!!!");
            tbKamIn.requestFocus();
        } else if (norawat.getText().equals("")) {
            JOptionPane.showMessageDialog(null, "Silakan klik salah satu nama pasien atau no. rekam medisnya dulu...!!!!");
            tbKamIn.requestFocus();
        } else if (!cekKelamin.equals("P")) {
            JOptionPane.showMessageDialog(null, "Hanya untuk pasien yang berjenis kelamin perempuan saja...!!!!");
            tbKamIn.requestFocus();
        } else if (!cekDataPersalinan.equals("")) {
            x = JOptionPane.showConfirmDialog(null, "Data ponek pasien yg bernama " + TPasien.getText() + " sudah tersimpan, apakah mau diperbaiki...?", "Konfirmasi", JOptionPane.YES_NO_OPTION);
            if (x == JOptionPane.YES_OPTION) {
                this.setCursor(Cursor.getPredefinedCursor(Cursor.WAIT_CURSOR));
                var.setform("DlgKamarInap");
                DlgInputPonek ponek = new DlgInputPonek(null, false);
                ponek.setSize(817, 601);
                ponek.setLocationRelativeTo(internalFrame1);
                ponek.pasien(norawat.getText(), TNoRM.getText(), TPasien.getText(), tbKamIn.getValueAt(tbKamIn.getSelectedRow(), 4).toString());
                ponek.tampil();
                ponek.setVisible(true);
                ponek.PanelInput.setPreferredSize(new Dimension(WIDTH, 270));
                ponek.FormInput.setVisible(true);
                ponek.ChkInput.setVisible(true);
                ponek.cmbAlamat.requestFocus();
                this.setCursor(Cursor.getDefaultCursor());
            } else {
                emptTeks();
            }
        } else if (cekKelamin.equals("P") && (cekDataPersalinan.equals(""))) {
            this.setCursor(Cursor.getPredefinedCursor(Cursor.WAIT_CURSOR));
            var.setform("DlgKamarInap");
            DlgInputPonek ponek = new DlgInputPonek(null, false);
            ponek.setSize(817, 601);
            ponek.setLocationRelativeTo(internalFrame1);
            ponek.pasien(norawat.getText(), TNoRM.getText(), TPasien.getText(), tbKamIn.getValueAt(tbKamIn.getSelectedRow(), 4).toString());
            ponek.tampil();
            ponek.setVisible(true);
            ponek.PanelInput.setPreferredSize(new Dimension(WIDTH, 270));
            ponek.FormInput.setVisible(true);
            ponek.ChkInput.setVisible(true);
            ponek.cmbAlamat.requestFocus();
            this.setCursor(Cursor.getDefaultCursor());
        }
    }//GEN-LAST:event_ppDataPonekBtnPrintActionPerformed

    private void MnKartuUmumActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_MnKartuUmumActionPerformed
        if (tabMode.getRowCount() == 0) {
            JOptionPane.showMessageDialog(null, "Maaf, data pasien sudah habis...!!!!");
            TNoRM.requestFocus();
        } else if (TPasien.getText().trim().equals("")) {
            JOptionPane.showMessageDialog(null, "Maaf, Silahkan anda pilih dulu data pasien dengan mengklik data pada tabel...!!!");
            tbKamIn.requestFocus();
        } else {
            this.setCursor(Cursor.getPredefinedCursor(Cursor.WAIT_CURSOR));
            Valid.MyReport("rptKartuBerobat.jrxml", "report", "::[ Kartu Berobat Pasien (KIB) Umum ]::",
                    "select * from pasien where pasien.no_rkm_medis='" + TNoRM.getText() + "' ");
            this.setCursor(Cursor.getDefaultCursor());
        }
    }//GEN-LAST:event_MnKartuUmumActionPerformed

    private void MnKartuNonUmumActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_MnKartuNonUmumActionPerformed
        if (tabMode.getRowCount() == 0) {
            JOptionPane.showMessageDialog(null, "Maaf, data pasien sudah habis...!!!!");
            TNoRM.requestFocus();
        } else if (TPasien.getText().trim().equals("")) {
            JOptionPane.showMessageDialog(null, "Maaf, Silahkan anda pilih dulu data pasien dengan mengklik data pada tabel...!!!");
            tbKamIn.requestFocus();
        } else {
            this.setCursor(Cursor.getPredefinedCursor(Cursor.WAIT_CURSOR));
            Valid.MyReport("rptKartuBerobatAsuransi.jrxml", "report", "::[ Kartu Berobat Pasien (KIB) Non Umum ]::",
                    "select * from pasien where pasien.no_rkm_medis='" + TNoRM.getText() + "' ");
            this.setCursor(Cursor.getDefaultCursor());
        }
    }//GEN-LAST:event_MnKartuNonUmumActionPerformed

    private void MnDietMakananActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_MnDietMakananActionPerformed
        if (R1.isSelected() == false) {
            JOptionPane.showMessageDialog(null, "Data diet makanan hanya bisa dicetak utk. pasien yg. masih dirawat inap...!!");
        } else {
            DlgDataDietRanap dietRanap = new DlgDataDietRanap(null, false);
            dietRanap.setSize(657, 370);
            dietRanap.setLocationRelativeTo(internalFrame1);
            dietRanap.emptTeks();
            dietRanap.tampil();
            dietRanap.setVisible(true);
        }
    }//GEN-LAST:event_MnDietMakananActionPerformed

    private void BtnCloseIn11ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_BtnCloseIn11ActionPerformed
        if (status_pulang.getText().equals("-") || (status_pulang.getText().equals("Pindah Kamar"))) {
            WindowPulangAPS.dispose();
            cmbStatus.setSelectedIndex(0);
            norawatAPS = "";
            BtnOutActionPerformed(null);
        } else {
            WindowPulangAPS.dispose();
            cmbStatus.setSelectedIndex(0);
            norawatAPS = "";
            emptTeks();
        }
    }//GEN-LAST:event_BtnCloseIn11ActionPerformed

    private void BtnSimpan9ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_BtnSimpan9ActionPerformed
        if (kdAPS.equals("") && (alasanAPS.getText().equals(""))) {
            JOptionPane.showMessageDialog(null, "Pilih dulu salah satu jenis alasan APS nya pada tabel...!!!!");
            tbAPS.requestFocus();
        } else if (ketAPS.getText().trim().equals("")) {
            JOptionPane.showMessageDialog(null, "Keterangan APS harus diisi...!!!!");
            ketAPS.requestFocus();
        } else {
            BtnSimpanActionPerformed(null);
        }
    }//GEN-LAST:event_BtnSimpan9ActionPerformed

    private void tbAPSMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tbAPSMouseClicked
        if (tabMode5.getRowCount() != 0) {
            try {
                getdataAPS();
            } catch (java.lang.NullPointerException e) {
            }
        }
    }//GEN-LAST:event_tbAPSMouseClicked

    private void tbAPSKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_tbAPSKeyPressed
        if (tabMode5.getRowCount() != 0) {
            if ((evt.getKeyCode() == KeyEvent.VK_ENTER) || (evt.getKeyCode() == KeyEvent.VK_UP) || (evt.getKeyCode() == KeyEvent.VK_DOWN)) {
                try {
                    getdataAPS();
                } catch (java.lang.NullPointerException e) {
                }
            } else if (evt.getKeyCode() == KeyEvent.VK_V) {
                if (tbAPS.getSelectedColumn() > 4) {
                    if (tbAPS.getSelectedRow() != -1) {
                        if (tbAPS.getValueAt(tbAPS.getSelectedRow(), tbAPS.getSelectedColumn()).toString().equals("false")) {
                            tbAPS.setValueAt(true, tbAPS.getSelectedRow(), tbAPS.getSelectedColumn());
                        } else {
                            tbAPS.setValueAt(false, tbAPS.getSelectedRow(), tbAPS.getSelectedColumn());
                        }
                    }
                }
            }
        }
    }//GEN-LAST:event_tbAPSKeyPressed

    private void TCari4KeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_TCari4KeyPressed
        if (evt.getKeyCode() == KeyEvent.VK_ENTER) {
            BtnCari5ActionPerformed(null);
        } else if (evt.getKeyCode() == KeyEvent.VK_PAGE_DOWN) {
            BtnCari5.requestFocus();
        } else if (evt.getKeyCode() == KeyEvent.VK_PAGE_UP) {
            BtnCloseIn11.requestFocus();
        }
    }//GEN-LAST:event_TCari4KeyPressed

    private void BtnCari5ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_BtnCari5ActionPerformed
        tampilAPS();
    }//GEN-LAST:event_BtnCari5ActionPerformed

    private void BtnCari5KeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_BtnCari5KeyPressed
        if (evt.getKeyCode() == KeyEvent.VK_SPACE) {
            BtnCariActionPerformed(null);
        } else {
            Valid.pindah(evt, TCari, BtnAll);
        }
    }//GEN-LAST:event_BtnCari5KeyPressed

    private void alasanAPSKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_alasanAPSKeyPressed
        // TODO add your handling code here:
    }//GEN-LAST:event_alasanAPSKeyPressed

    private void BtnBatal2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_BtnBatal2ActionPerformed
        TCari4.setText("");
        alasanAPS.setText("");
        ketAPS.setText("");
        kdAPS = "";
        tampilAPS();
    }//GEN-LAST:event_BtnBatal2ActionPerformed

    private void BtnBatal2KeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_BtnBatal2KeyPressed
        if (evt.getKeyCode() == KeyEvent.VK_SPACE) {
            emptTeks();
        } else {
            Valid.pindah(evt, BtnSimpan, BtnHapus);
        }
    }//GEN-LAST:event_BtnBatal2KeyPressed

    private void TCari4ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_TCari4ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_TCari4ActionPerformed

    private void BtnEdit3ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_BtnEdit3ActionPerformed
        cekAPS = "";
        cekAPS = Sequel.cariIsi("select no_rawat from ranap_aps where no_rawat='" + norawat.getText() + "'");

        Sequel.meghapus("rujuk", "no_rawat", norawat.getText());
        Sequel.meghapus("pasien_mati", "no_rkm_medis", TNoRM.getText());
        Sequel.mengedit("kamar_inap", "no_rawat='" + norawat.getText() + "' and stts_pulang='" + status_pulang.getText() + "'", "stts_pulang='APS' ");

        if (ketAPS.getText().trim().equals("")) {
            JOptionPane.showMessageDialog(null, "Keterangan APS harus diisi...!!!!");
            ketAPS.requestFocus();
        } else {
            if (cekAPS.equals("")) {
                Sequel.menyimpan("ranap_aps", "'" + norawat.getText() + "','" + kdAPS + "','" + ketAPS.getText() + "'");
            } else {
                Sequel.mengedit("ranap_aps", "no_rawat='" + norawat.getText() + "'", "kd_aps='" + kdAPS + "',keterangan='" + ketAPS.getText() + "' ");
            }
        }

        tampil();
        JOptionPane.showMessageDialog(null, "Data berhasil tersimpan, utk. mencetak surat pernyataan APS klik tombol cetak...!!!!");
    }//GEN-LAST:event_BtnEdit3ActionPerformed

    private void BtnEdit3KeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_BtnEdit3KeyPressed

    }//GEN-LAST:event_BtnEdit3KeyPressed

    private void BtnPrint1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_BtnPrint1ActionPerformed
        cekAPS = "";
        cekAPS = Sequel.cariIsi("select no_rawat from ranap_aps where no_rawat='" + norawatAPS + "'");

        this.setCursor(Cursor.getPredefinedCursor(Cursor.WAIT_CURSOR));
        if (cekAPS.equals("")) {
            JOptionPane.showMessageDialog(null, "Pilih dulu salah satu pasien yang pulang dg. cara APS pada tabel...!!!!");
            BtnBatal.requestFocus();
        } else if (!cekAPS.equals("")) {
            Map<String, Object> param = new HashMap<>();
            param.put("namars", var.getnamars());
            param.put("alamatrs", var.getalamatrs());
            param.put("kotars", var.getkabupatenrs());
            param.put("propinsirs", var.getpropinsirs());
            param.put("kontakrs", var.getkontakrs());
            param.put("emailrs", var.getemailrs());
            param.put("logo", Sequel.cariGambar("select logo from setting"));
            param.put("tgl_pulang", Sequel.cariIsi("select day(tgl_keluar) from kamar_inap where stts_pulang not in ('-','pindah kamar') and no_rawat='" + norawat.getText() + "'") + " "
                    + Sequel.bulanINDONESIA("select month(tgl_keluar) from kamar_inap where stts_pulang not in ('-','pindah kamar') and no_rawat='" + norawat.getText() + "'") + " "
                    + Sequel.cariIsi("select year(tgl_keluar) from kamar_inap where stts_pulang not in ('-','pindah kamar') and no_rawat='" + norawat.getText() + "'"));
            Valid.MyReport("rptsuratAPS.jrxml", "report", "::[ Surat Pernyataan Pulang APS (Atas Permintaan Sendiri) RM 18.5 ]::",
                    "SELECT rp.no_rkm_medis, p.nm_pasien, IFNULL(p.tmp_lahir,'-') tmpt_lhr, DATE_FORMAT(p.tgl_lahir,'%d-%m-%Y') tgl_lahir, ma.nm_alasan, "
                    + "d.nm_dokter, ra.keterangan FROM ranap_aps ra INNER JOIN reg_periksa rp on rp.no_rawat=ra.no_rawat INNER JOIN pasien p on p.no_rkm_medis=rp.no_rkm_medis "
                    + "INNER JOIN kamar_inap ki on ki.no_rawat=ra.no_rawat INNER JOIN dpjp_ranap dr on dr.no_rawat=rp.no_rawat INNER JOIN dokter d on d.kd_dokter=dr.kd_dokter "
                    + "INNER JOIN master_aps ma on ma.kd_aps=ra.kd_aps WHERE ki.stts_pulang not in ('-','pindah kamar') and ra.no_rawat='" + norawatAPS + "'", param);
        }
        this.setCursor(Cursor.getDefaultCursor());
        WindowPulangAPS.dispose();
        norawatAPS = "";
        cmbStatus.setSelectedIndex(0);
        emptTeks();
    }//GEN-LAST:event_BtnPrint1ActionPerformed

    private void BtnPrint1KeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_BtnPrint1KeyPressed

    }//GEN-LAST:event_BtnPrint1KeyPressed

    private void MnSrtAPSActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_MnSrtAPSActionPerformed
        cekAPS = "";
        cekAPS = Sequel.cariIsi("select no_rawat from ranap_aps where no_rawat='" + norawat.getText() + "'");

        this.setCursor(Cursor.getPredefinedCursor(Cursor.WAIT_CURSOR));
        if (norawat.getText().equals("")) {
            JOptionPane.showMessageDialog(null, "Pilih dulu salah satu pasien yang pulang dg. cara APS pada tabel...!!!!");
        } else if (!norawat.getText().equals("") && ((status_pulang.getText().equals("-") || (status_pulang.getText().equals("Pindah Kamar"))))) {
            JOptionPane.showMessageDialog(null, "Pasien itu masih dirawat inap...!!!!");
        } else if (cekAPS.equals("")) {
            JOptionPane.showMessageDialog(null, "Pasien itu pulang bukan dg. cara APS atau alasan APSnya belum tersimpan...!!!!");
            BtnBatal.requestFocus();
        } else if (!cekAPS.equals("")) {
            Map<String, Object> param = new HashMap<>();
            param.put("namars", var.getnamars());
            param.put("alamatrs", var.getalamatrs());
            param.put("kotars", var.getkabupatenrs());
            param.put("propinsirs", var.getpropinsirs());
            param.put("kontakrs", var.getkontakrs());
            param.put("emailrs", var.getemailrs());
            param.put("logo", Sequel.cariGambar("select logo from setting"));
            param.put("tgl_pulang", Sequel.cariIsi("select day(tgl_keluar) from kamar_inap where stts_pulang not in ('-','pindah kamar') and no_rawat='" + norawat.getText() + "'") + " "
                    + Sequel.bulanINDONESIA("select month(tgl_keluar) from kamar_inap where stts_pulang not in ('-','pindah kamar') and no_rawat='" + norawat.getText() + "'") + " "
                    + Sequel.cariIsi("select year(tgl_keluar) from kamar_inap where stts_pulang not in ('-','pindah kamar') and no_rawat='" + norawat.getText() + "'"));
            Valid.MyReport("rptsuratAPS.jrxml", "report", "::[ Surat Pernyataan Pulang APS (Atas Permintaan Sendiri) RM 18.5 ]::",
                    "SELECT rp.no_rkm_medis, p.nm_pasien, IFNULL(p.tmp_lahir,'-') tmpt_lhr, DATE_FORMAT(p.tgl_lahir,'%d-%m-%Y') tgl_lahir, ma.nm_alasan, "
                    + "d.nm_dokter, ra.keterangan FROM ranap_aps ra INNER JOIN reg_periksa rp on rp.no_rawat=ra.no_rawat "
                    + "INNER JOIN pasien p on p.no_rkm_medis=rp.no_rkm_medis INNER JOIN kamar_inap ki on ki.no_rawat=ra.no_rawat "
                    + "INNER JOIN dpjp_ranap dr on dr.no_rawat=rp.no_rawat INNER JOIN dokter d on d.kd_dokter=dr.kd_dokter "
                    + "INNER JOIN master_aps ma on ma.kd_aps=ra.kd_aps WHERE ki.stts_pulang not in ('-','pindah kamar') and ra.no_rawat='" + norawat.getText() + "'", param);
        }
        this.setCursor(Cursor.getDefaultCursor());
        WindowPulangAPS.dispose();
        norawatAPS = "";
    }//GEN-LAST:event_MnSrtAPSActionPerformed

    private void MnLabelPxRanap2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_MnLabelPxRanap2ActionPerformed
        if (tabMode.getRowCount() == 0) {
            JOptionPane.showMessageDialog(null, "Maaf, tabel masih kosong...!!!!");
            TCari.requestFocus();
        } else if (noRMCopy.getText().equals("")) {
            JOptionPane.showMessageDialog(null, "Silahkan klik dulu salah satu data pasiennya pada tabel...!!!!");
            tbKamIn.requestFocus();
        } else if (R1.isSelected() == false) {
            JOptionPane.showMessageDialog(null, "Label pasien hanya bisa dicetak utk. yang masih dirawat inap saja...!!!!");
        } else {
            Valid.MyReport("rptLabelPxRanap1.jrxml", "report", "::[ Label Pasien (6,4 x 3,2 Cm) ]::",
                    "select p.no_rkm_medis, concat(p.nm_pasien,' (',if(p.jk='L','LK','PR'),')') nm_pasien, date_format(p.tgl_lahir,'%d %M %Y') tgl_lhr, "
                    + "CONCAT(p.alamat,', ',kl.nm_kel,', ',kc.nm_kec,', ',kb.nm_kab) alamat from pasien p "
                    + "INNER JOIN kelurahan kl on kl.kd_kel=p.kd_kel INNER JOIN kecamatan kc on kc.kd_kec=p.kd_kec "
                    + "INNER JOIN kabupaten kb on kb.kd_kab=p.kd_kab where p.no_rkm_medis='" + noRMCopy.getText() + "'");
        }
    }//GEN-LAST:event_MnLabelPxRanap2ActionPerformed

    private void MnRingkasanPulangActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_MnRingkasanPulangActionPerformed
        if (tabMode.getRowCount() == 0) {
            JOptionPane.showMessageDialog(null, "Maaf, tabel masih kosong...!!!!");
            TCari.requestFocus();
        } else if (R3.isSelected() == false) {
            JOptionPane.showMessageDialog(null, "Ringkasan pulang hanya bisa diisi pada pilihan pasien pulang..!!!!");
        } else if (status_pulang.getText().equals("Pindah Kamar")) {
            JOptionPane.showMessageDialog(null, "Pasien masih pindah ruang rawat, belum bisa diisi ringkasan pulangnya....");
        } else if (!norawat.getText().equals("")) {
            ringkasan.TNmDokter.requestFocus();
            ringkasan.setPasien(norawat.getText());
            ringkasan.setSize(1098, 550);
            ringkasan.setLocationRelativeTo(internalFrame1);
            ringkasan.setVisible(true);
            ringkasan.tampil();
            ringkasan.ChkInput.setSelected(true);
            ringkasan.isForm();
            ringkasan.FormInput.setSelectedIndex(0);
        }
    }//GEN-LAST:event_MnRingkasanPulangActionPerformed

    private void ketAPSKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_ketAPSKeyPressed
        // TODO add your handling code here:
    }//GEN-LAST:event_ketAPSKeyPressed

    private void MnBridgingActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_MnBridgingActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_MnBridgingActionPerformed

    private void MnRegisterPasienActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_MnRegisterPasienActionPerformed
        if (tbKamIn.getRowCount() == 0) {
            JOptionPane.showMessageDialog(null, "Maaf, data sudah habis. Tidak ada data yang bisa anda print...!!!!");
            BtnCari.requestFocus();
        } else if ((cmbRuangan.isVisible() == true) && ((cmbRuangan.getSelectedItem().equals("- pilih salah satu -")) || (cmbRuangan.getSelectedItem().equals("SEMUA RUANG")))
                || (cmbRuangKhusus1.isVisible() == true) && (cmbRuangKhusus1.getSelectedItem().equals("- pilih salah satu -"))
                || (cmbRuangKhusus2.isVisible() == true) && (cmbRuangKhusus2.getSelectedItem().equals("- pilih salah satu -"))
                || (cmbRuangKhusus3.isVisible() == true) && (cmbRuangKhusus3.getSelectedItem().equals("- pilih salah satu -"))
                || (cmbRuangKhusus4.isVisible() == true) && (cmbRuangKhusus4.getSelectedItem().equals("- pilih salah satu -"))
                || (cmbRuangKhusus5.isVisible() == true) && (cmbRuangKhusus5.getSelectedItem().equals("- pilih salah satu -"))) {
            JOptionPane.showMessageDialog(null, "Pilih salah satu nama ruangan inapnya dulu...!!!!");
        } else if (R1.isSelected() == true) {
            JOptionPane.showMessageDialog(null, "Silakan tentukan dulu pilihan tanggal masuknya...!!!!");
        } else if (R3.isSelected() == true) {
            JOptionPane.showMessageDialog(null, "Silakan tentukan dulu pilihan tanggal masuknya...!!!!");
        } else {
            cetakRegInap();
        }
    }//GEN-LAST:event_MnRegisterPasienActionPerformed

    private void MnDiagnosaAwalActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_MnDiagnosaAwalActionPerformed
        TDiagnosaAwal.setText("");

        if (tabMode.getRowCount() == 0) {
            JOptionPane.showMessageDialog(null, "Maaf, tabel masih kosong...!!!!");
            TCari.requestFocus();
        } else if (TPasien.getText().trim().equals("")) {
            JOptionPane.showMessageDialog(null, "Maaf, Silahkan anda pilih dulu pasien...!!!");
            tbKamIn.requestFocus();
        } else {
            TDiagnosaAwal.setText(Sequel.cariIsi("select diagnosa_awal from kamar_inap where no_rawat='" + norawat.getText() + "'"));
            WindowDiagnosaAwal.setLocationRelativeTo(internalFrame1);
            WindowDiagnosaAwal.setVisible(true);
            TDiagnosaAwal.requestFocus();
        }
    }//GEN-LAST:event_MnDiagnosaAwalActionPerformed

    private void BtnCloseIn12ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_BtnCloseIn12ActionPerformed
        WindowDiagnosaAwal.dispose();
    }//GEN-LAST:event_BtnCloseIn12ActionPerformed

    private void BtnSimpan10ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_BtnSimpan10ActionPerformed
        if (TDiagnosaAwal.getText().equals("")) {
            JOptionPane.showMessageDialog(null, "Diagnosa awal pasien sebaiknya jangan dikosongkan..!!!");
            TDiagnosaAwal.requestFocus();
        } else {
            Sequel.AutoComitFalse();
            Sequel.mengedit("kamar_inap", "no_rawat='" + norawat.getText() + "'", "diagnosa_awal='" + TDiagnosaAwal.getText() + "'");
            Sequel.AutoComitTrue();
            tampil();
            emptTeks();
            WindowDiagnosaAwal.dispose();
        }
    }//GEN-LAST:event_BtnSimpan10ActionPerformed

    private void TDiagnosaAwalKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_TDiagnosaAwalKeyPressed
        // TODO add your handling code here:
    }//GEN-LAST:event_TDiagnosaAwalKeyPressed

    private void TDiagnosaAwalKeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_TDiagnosaAwalKeyTyped
        evt.setKeyChar(Character.toUpperCase(evt.getKeyChar()));
    }//GEN-LAST:event_TDiagnosaAwalKeyTyped

    private void MnSEPJampersalActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_MnSEPJampersalActionPerformed
        if (nmpenjab.getText().equals("")) {
            //emptTeks();
            JOptionPane.showMessageDialog(null, "Silakan klik dulu nama pasiennya pada tabel..!!");
            tbKamIn.requestFocus();
        } else if (nmpenjab.getText().equals("JAMPERSAL (PBI)")) {
            DlgJampersal.setSize(490, 125);
            DlgJampersal.setLocationRelativeTo(internalFrame1);
            DlgJampersal.setVisible(true);
        } else {
            //emptTeks();
            JOptionPane.showMessageDialog(null, "Jenis cara bayar pasien tersebut tidak sesuai..!!");
            tbKamIn.requestFocus();
        }
    }//GEN-LAST:event_MnSEPJampersalActionPerformed

    private void BtnCloseIn13ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_BtnCloseIn13ActionPerformed
        DlgJampersal.dispose();
        emptTeks();
        tampil();
    }//GEN-LAST:event_BtnCloseIn13ActionPerformed

    private void BtnCloseIn13KeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_BtnCloseIn13KeyPressed
        // TODO add your handling code here:
    }//GEN-LAST:event_BtnCloseIn13KeyPressed

    private void BtnSimpan3ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_BtnSimpan3ActionPerformed
        cekjampersal = 0;
        cekjampersal = Sequel.cariInteger("SELECT Count(1) cek FROM bridging_jampersal WHERE no_rawat='" + norawat.getText() + "' AND jns_rawat='Inap'");

        if (noSrt1.getText().trim().equals("")) {
            Valid.textKosong(noSrt1, "No. Surat Keterangan Peserta Jampersal");
        } else if (cekjampersal >= 1) {
            JOptionPane.showMessageDialog(null, "Data tidak tersimpan, SEP sudah pernah dibikinkan dengan no. rawat yang sama..!!");
            noSrt1.requestFocus();
        } else {
            Sequel.menyimpan("bridging_jampersal", "'" + norawat.getText() + "',"
                    + "'" + noSrt1.getText() + "','Inap',"
                    + "'" + tglInap.getText() + "',"
                    + "'" + Valid.SetTgl(Tglsurat1.getSelectedItem() + "") + "',"
                    + "'" + norawat.getText() + "JI' ", "Data SEP Jampersal");
            JOptionPane.showMessageDialog(null, "Data berhasil disimpan, SEP siap dicetak......");
            BtnCtkJmp.requestFocus();
        }
    }//GEN-LAST:event_BtnSimpan3ActionPerformed

    private void BtnSimpan3KeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_BtnSimpan3KeyPressed
        // TODO add your handling code here:
    }//GEN-LAST:event_BtnSimpan3KeyPressed

    private void noSrt1KeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_noSrt1KeyPressed
        // TODO add your handling code here:
    }//GEN-LAST:event_noSrt1KeyPressed

    private void Tglsurat1ItemStateChanged(java.awt.event.ItemEvent evt) {//GEN-FIRST:event_Tglsurat1ItemStateChanged
        // TODO add your handling code here:
    }//GEN-LAST:event_Tglsurat1ItemStateChanged

    private void Tglsurat1KeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_Tglsurat1KeyPressed
        // TODO add your handling code here:
    }//GEN-LAST:event_Tglsurat1KeyPressed

    private void BtnGantijmpActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_BtnGantijmpActionPerformed
        if (noSrt1.getText().trim().equals("")) {
            Valid.textKosong(noSrt1, "No. Surat Keterangan Peserta Jampersal");
        } else {
            Sequel.mengedit("bridging_jampersal", "no_sep='" + sepJmp.getText() + "'",
                    "no_surat='" + noSrt1.getText() + "', "
                    + "tgl_surat='" + Valid.SetTgl(Tglsurat1.getSelectedItem() + "") + "' ");
            JOptionPane.showMessageDialog(null, "Data berhasil diubah, SEP siap dicetak......");
            BtnCtkJmp.requestFocus();
        }
    }//GEN-LAST:event_BtnGantijmpActionPerformed

    private void BtnGantijmpKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_BtnGantijmpKeyPressed
        // TODO add your handling code here:
    }//GEN-LAST:event_BtnGantijmpKeyPressed

    private void BtnCtkJmpActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_BtnCtkJmpActionPerformed
        sepJmp.setText(Sequel.cariIsi("SELECT no_sep FROM bridging_jampersal WHERE no_rawat='" + norawat.getText() + "' AND jns_rawat='Inap'"));

        if (noSrt1.getText().trim().equals("")) {
            Valid.textKosong(noSrt1, "No. Surat Keterangan Peserta Jampersal");
        } else if (sepJmp.getText().equals("")) {
            Valid.textKosong(sepJmp, "No. SEP Jampersal");
        } else {
            this.setCursor(Cursor.getPredefinedCursor(Cursor.WAIT_CURSOR));
            Map<String, Object> param = new HashMap<>();
            param.put("namars", var.getnamars());
            param.put("alamatrs", var.getalamatrs());
            param.put("kotars", var.getkabupatenrs());
            param.put("propinsirs", var.getpropinsirs());
            param.put("kontakrs", var.getkontakrs());
            param.put("emailrs", var.getemailrs());
            param.put("logo", Sequel.cariGambar("select logo from setting"));
            Valid.MyReport("rptSEPInap1.jrxml", "report", "::[ Cetak SEP Pasien JAMPERSAL Rawat Inap ]::",
                    " SELECT p.no_rkm_medis, bs.no_sep, rp.tgl_registrasi, p.no_ktp, p.nm_pasien, p.tgl_lahir, IF(p.jk='L','Laki-laki','Perempuan') AS jk, "
                    + "rp.no_rawat, bs.no_surat, bs.jns_rawat, bs.tgl_surat, bs.tgl_rawat, pj.png_jawab, b.nm_bangsal, k.kelas "
                    + "FROM reg_periksa rp INNER JOIN pasien p ON rp.no_rkm_medis = p.no_rkm_medis "
                    + "INNER JOIN bridging_jampersal bs ON bs.no_rawat = rp.no_rawat INNER JOIN penjab pj ON rp.kd_pj = pj.kd_pj "
                    + "INNER JOIN kamar_inap ki ON ki.no_rawat = rp.no_rawat INNER JOIN kamar k ON ki.kd_kamar = k.kd_kamar "
                    + "INNER JOIN bangsal b ON k.kd_bangsal = b.kd_bangsal WHERE bs.no_sep='" + sepJmp.getText() + "' AND bs.jns_rawat='Inap' "
                    + "AND pj.png_jawab LIKE '%jampersal%'", param);
            this.setCursor(Cursor.getDefaultCursor());
            DlgJampersal.dispose();
            emptTeks();
            tampil();
        }
    }//GEN-LAST:event_BtnCtkJmpActionPerformed

    private void BtnCtkJmpKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_BtnCtkJmpKeyPressed
        // TODO add your handling code here:
    }//GEN-LAST:event_BtnCtkJmpKeyPressed

    private void MnRawatJalanActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_MnRawatJalanActionPerformed
        if (norawat.getText().trim().equals("")) {
            JOptionPane.showMessageDialog(null, "Maaf, Pasien belum dipilih...!!!");
            norawat.requestFocus();
        } else {
            DlgRawatJalan dlgrwjl = new DlgRawatJalan(null, false);
            dlgrwjl.setSize(internalFrame1.getWidth() - 40, internalFrame1.getHeight() - 40);
            dlgrwjl.setLocationRelativeTo(internalFrame1);
            dlgrwjl.setNoRm(norawat.getText(), tglMasukInap.getDate(), new Date());
            dlgrwjl.tampilDrPr();
            dlgrwjl.TotalNominal();
            dlgrwjl.setVisible(true);
            dlgrwjl.fokus();
            dlgrwjl.isCek();
        }
    }//GEN-LAST:event_MnRawatJalanActionPerformed

    private void ppPasienCoronaBtnPrintActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_ppPasienCoronaBtnPrintActionPerformed
        if (tabMode.getRowCount() == 0) {
            JOptionPane.showMessageDialog(null, "Maaf, data pasien sudah habis...!!!!");
            TNoRM.requestFocus();
        } else if (TPasien.getText().trim().equals("")) {
            JOptionPane.showMessageDialog(null, "Maaf, Silahkan anda pilih dulu data pasien dengan menklik data pada table...!!!");
            tbKamIn.requestFocus();
        } else {
            this.setCursor(Cursor.getPredefinedCursor(Cursor.WAIT_CURSOR));
            CoronaPasien form = new CoronaPasien(null, false);
            form.setPasien(TNoRM.getText(), tglInap.getText(), "NGINAP");
            form.isCek();
            form.setSize(internalFrame1.getWidth() - 20, internalFrame1.getHeight() - 20);
            form.setLocationRelativeTo(internalFrame1);
            form.setVisible(true);
            form.Inisial.requestFocus();
            this.setCursor(Cursor.getDefaultCursor());
        }
    }//GEN-LAST:event_ppPasienCoronaBtnPrintActionPerformed

    private void ppPerawatanCoronaBtnPrintActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_ppPerawatanCoronaBtnPrintActionPerformed
        if (tabMode.getRowCount() == 0) {
            JOptionPane.showMessageDialog(null, "Maaf, data pasien sudah habis...!!!!");
            tbKamIn.requestFocus();
        } else if (TPasien.getText().trim().equals("")) {
            JOptionPane.showMessageDialog(null, "Maaf, Silahkan anda pilih dulu data pasien dengan mengklik data pada tabel...!!!");
            tbKamIn.requestFocus();
        } else {
            this.setCursor(Cursor.getPredefinedCursor(Cursor.WAIT_CURSOR));
            INACBGPerawatanCorona form = new INACBGPerawatanCorona(null, false);
            form.emptTeks();
            form.setPasien(norawat.getText(), TNoRM.getText(), TPasien.getText());
            form.isCek();
            form.setSize(internalFrame1.getWidth() - 20, internalFrame1.getHeight() - 20);
            form.setLocationRelativeTo(internalFrame1);
            form.setVisible(true);
            this.setCursor(Cursor.getDefaultCursor());
        }
    }//GEN-LAST:event_ppPerawatanCoronaBtnPrintActionPerformed

    private void MrkAlfaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_MrkAlfaActionPerformed
        if (tabMode.getRowCount() == 0) {
            JOptionPane.showMessageDialog(null, "Maaf, tabel masih kosong...!!!!");
            TCari.requestFocus();
        } else if (noRMCopy.getText().equals("")) {
            JOptionPane.showMessageDialog(null, "Silahkan klik dulu salah satu data pasiennya pada tabel...!!!!");
            tbKamIn.requestFocus();
        } else if (R1.isSelected() == false) {
            JOptionPane.showMessageDialog(null, "Label pasien hanya bisa dicetak utk. yang masih dirawat inap saja...!!!!");
        } else {
            Valid.MyReport("rptLabelPxRanap2.jrxml", "report", "::[ Label Pasien (4,9 x 1,9 Cm) Merek ALFA PREMIUM ]::",
                    "select no_rkm_medis, concat(nm_pasien,' (',if(jk='L','LK','PR'),')') nm_pasien, "
                    + "date_format(tgl_lahir,'%d-%m-%Y') tgl_lhr from pasien where no_rkm_medis='" + noRMCopy.getText() + "'");
        }
    }//GEN-LAST:event_MrkAlfaActionPerformed

    private void MrkOleanActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_MrkOleanActionPerformed
        if (tabMode.getRowCount() == 0) {
            JOptionPane.showMessageDialog(null, "Maaf, tabel masih kosong...!!!!");
            TCari.requestFocus();
        } else if (noRMCopy.getText().equals("")) {
            JOptionPane.showMessageDialog(null, "Silahkan klik dulu salah satu data pasiennya pada tabel...!!!!");
            tbKamIn.requestFocus();
        } else if (R1.isSelected() == false) {
            JOptionPane.showMessageDialog(null, "Label pasien hanya bisa dicetak utk. yang masih dirawat inap saja...!!!!");
        } else {
            Valid.MyReport("rptLabelPxRanap3.jrxml", "report", "::[ Label Pasien (4,9 x 1,9 Cm) Merek OLEAN CITY BRAND ]::",
                    "select no_rkm_medis, concat(nm_pasien,' (',if(jk='L','LK','PR'),')') nm_pasien, "
                    + "date_format(tgl_lahir,'%d-%m-%Y') tgl_lhr from pasien where no_rkm_medis='" + noRMCopy.getText() + "'");
        }
    }//GEN-LAST:event_MrkOleanActionPerformed

    private void cmbRuangKhusus5MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_cmbRuangKhusus5MouseClicked
        if (!cmbRuangKhusus5.getSelectedItem().equals("")) {
            ruangDicetak.setText(cmbRuangKhusus5.getSelectedItem().toString());
        }
    }//GEN-LAST:event_cmbRuangKhusus5MouseClicked

    private void cmbRuangKhusus5ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_cmbRuangKhusus5ActionPerformed
        if (!cmbRuangKhusus5.getSelectedItem().equals("")) {
            ruangDicetak.setText(cmbRuangKhusus5.getSelectedItem().toString());
        }

//        if (cmbRuangKhusus5.getSelectedItem().equals("VIP")
//                || (cmbRuangKhusus5.getSelectedItem().equals("SVIP"))
//                || (cmbRuangKhusus5.getSelectedItem().equals("ISOLASI COVID19"))
//                || (cmbRuangKhusus5.getSelectedItem().equals("ISOLASI BAYI COVID19"))
//                || (cmbRuangKhusus5.getSelectedItem().equals("COVID19"))) {
//            ruangDicetak.setText(cmbRuangKhusus5.getSelectedItem().toString());
//        } else if (cmbRuangKhusus5.getSelectedIndex() == 0) {
//            JOptionPane.showMessageDialog(null, "Pilih salah satu nama ruangan inapnya dulu...!!!!");
//        }
    }//GEN-LAST:event_cmbRuangKhusus5ActionPerformed

    private void cmbRuangKhusus5KeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_cmbRuangKhusus5KeyPressed
        // TODO add your handling code here:
    }//GEN-LAST:event_cmbRuangKhusus5KeyPressed

    private void MrkKojicoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_MrkKojicoActionPerformed
        if (tabMode.getRowCount() == 0) {
            JOptionPane.showMessageDialog(null, "Maaf, tabel masih kosong...!!!!");
            TCari.requestFocus();
        } else if (noRMCopy.getText().equals("")) {
            JOptionPane.showMessageDialog(null, "Silahkan klik dulu salah satu data pasiennya pada tabel...!!!!");
            tbKamIn.requestFocus();
        } else if (R1.isSelected() == false) {
            JOptionPane.showMessageDialog(null, "Label pasien hanya bisa dicetak utk. yang masih dirawat inap saja...!!!!");
        } else {
            Valid.MyReport("rptLabelPxRanap4.jrxml", "report", "::[ Label Pasien (4,9 x 1,9 Cm) Merek KOJICO BRAND ]::",
                    "select no_rkm_medis, concat(nm_pasien,' (',if(jk='L','LK','PR'),')') nm_pasien, "
                    + "date_format(tgl_lahir,'%d-%m-%Y') tgl_lhr from pasien where no_rkm_medis='" + noRMCopy.getText() + "'");
        }
    }//GEN-LAST:event_MrkKojicoActionPerformed

    private void MnWaktuRegRalanActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_MnWaktuRegRalanActionPerformed
        i = 0;
        i = Sequel.cariInteger("select count(billing.no_rawat) cek from billing where billing.no_rawat='" + norawat.getText() + "'");
        Valid.SetTgl(TglRegRalan, Sequel.cariIsi("select tgl_registrasi from reg_periksa where no_rawat='" + norawat.getText() + "'"));

        if (tabMode.getRowCount() == 0) {
            JOptionPane.showMessageDialog(null, "Maaf, data pasien sudah habis...!!!!");
            tbKamIn.requestFocus();
        } else if (norawat.getText().trim().equals("")) {
            JOptionPane.showMessageDialog(null, "Maaf, Silahkan anda pilih dulu data pasien dengan mengklik data pada tabel...!!!");
            tbKamIn.requestFocus();
        } else if (i > 0) {
            JOptionPane.showMessageDialog(null, "Nota/Kwitansinya sudah tersimpan, hapus dulu notanya dibilling pembayaran..!!!");
        } else {
            WindowWaktuRegRalan.setSize(368, 171);
            WindowWaktuRegRalan.setLocationRelativeTo(internalFrame1);
            WindowWaktuRegRalan.setVisible(true);
            TglRegRalan.requestFocus();
            Jreg.setSelectedIndex(0);
            Mreg.setSelectedIndex(0);
            Dreg.setSelectedIndex(0);
            cmbJamReg.setSelected(false);
        }
    }//GEN-LAST:event_MnWaktuRegRalanActionPerformed

    private void BtnCloseIn5ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_BtnCloseIn5ActionPerformed
        WindowWaktuRegRalan.dispose();
        tampil();
        emptTeks();
    }//GEN-LAST:event_BtnCloseIn5ActionPerformed

    private void BtnCloseIn5KeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_BtnCloseIn5KeyPressed
        // TODO add your handling code here:
    }//GEN-LAST:event_BtnCloseIn5KeyPressed

    private void TglRegRalanItemStateChanged(java.awt.event.ItemEvent evt) {//GEN-FIRST:event_TglRegRalanItemStateChanged
        // TODO add your handling code here:
    }//GEN-LAST:event_TglRegRalanItemStateChanged

    private void TglRegRalanKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_TglRegRalanKeyPressed
        // TODO add your handling code here:
    }//GEN-LAST:event_TglRegRalanKeyPressed

    private void BtnGantiTglActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_BtnGantiTglActionPerformed
        if (cmbJamReg.isSelected() == true) {
            Sequel.mengedit("reg_periksa", "no_rawat='" + norawat.getText() + "'",
                    "tgl_registrasi='" + TIn.getText() + "',jam_reg='" + JamMasuk.getText() + "'");
            WindowWaktuRegRalan.dispose();
            tampil();
            emptTeks();
        } else {
            Sequel.mengedit("reg_periksa", "no_rawat='" + norawat.getText() + "'",
                    "tgl_registrasi='" + Valid.SetTgl(TglRegRalan.getSelectedItem() + "") + "',"
                    + "jam_reg='" + Jreg.getSelectedItem() + ":" + Mreg.getSelectedItem() + ":" + Dreg.getSelectedItem() + "'");
            WindowWaktuRegRalan.dispose();
            tampil();
            emptTeks();
        }
    }//GEN-LAST:event_BtnGantiTglActionPerformed

    private void BtnGantiTglKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_BtnGantiTglKeyPressed
        // TODO add your handling code here:
    }//GEN-LAST:event_BtnGantiTglKeyPressed

    private void MnPindahTransaksiActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_MnPindahTransaksiActionPerformed
        i = 0;
        i = Sequel.cariInteger("select count(billing.no_rawat) cek from billing where billing.no_rawat='" + TNoRwTerpilih.getText() + "'");

        if (tabMode.getRowCount() == 0) {
            JOptionPane.showMessageDialog(null, "Maaf, data pasien sudah habis...!!!!");
            tbKamIn.requestFocus();
        } else if (norawat.getText().trim().equals("")) {
            JOptionPane.showMessageDialog(null, "Maaf, Silahkan anda pilih dulu data pasien dengan mengklik data pada tabel...!!!");
            tbKamIn.requestFocus();
        } else if (i > 0) {
            JOptionPane.showMessageDialog(null, "Untuk No. Rawat terpilih notanya sudah tersimpan, hapus dulu notanya dibilling pembayaran..!!!");
        } else {
            if (var.getkode().equals("Admin Utama")) {
                WindowPindahkanTransaksi.setSize(558, 403);
                WindowPindahkanTransaksi.setLocationRelativeTo(internalFrame1);
                WindowPindahkanTransaksi.setVisible(true);
                TNoRwTerpilih.setText(norawat.getText());
                pasienTerpilih.setText(TNoRM.getText() + " - " + TPasien.getText());
                TNoRwTujuan.requestFocus();
            } else {
                JOptionPane.showMessageDialog(null, "Maaf, hak akses anda tertutup, Silakan hub. petugas terkait...!!");
            }
        }
    }//GEN-LAST:event_MnPindahTransaksiActionPerformed

    private void BtnCloseIn14ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_BtnCloseIn14ActionPerformed
        WindowPindahkanTransaksi.dispose();
        tampil();
        emptTeks();
    }//GEN-LAST:event_BtnCloseIn14ActionPerformed

    private void BtnCloseIn14KeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_BtnCloseIn14KeyPressed
        // TODO add your handling code here:
    }//GEN-LAST:event_BtnCloseIn14KeyPressed

    private void BtnProsesTranActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_BtnProsesTranActionPerformed
        i = 0;
        i = Sequel.cariInteger("select count(billing.no_rawat) cek from billing where billing.no_rawat='" + TNoRwTujuan.getText() + "'");

        if (TNoRwTujuan.getText().trim().equals("")) {
            JOptionPane.showMessageDialog(null, "Nomor rawat tujuan harus terisi dengan benar...!!");
            TNoRwTujuan.requestFocus();
        } else if (cmbJnsTran.getSelectedItem().equals(" ")) {
            JOptionPane.showMessageDialog(null, "Pilih dulu salah satu jenis transaksi yang akan diproses...!!");
            cmbJnsTran.requestFocus();
        } else if (i > 0) {
            JOptionPane.showMessageDialog(null, "Untuk No. Rawat tujuan notanya sudah tersimpan, hapus dulu notanya dibilling pembayaran..!!!");
        } else if (tglDari.getText().equals("salah satu")) {
            JOptionPane.showMessageDialog(null, "Tgl. Transaksi dan Pukul salah...!!");
            cmbJnsTran.requestFocus();
        } else {
            if (cmbJnsTran.getSelectedItem().equals("FARMASI")) {
                tranFarmasi();
            } else if (cmbJnsTran.getSelectedItem().equals("RADIOLOGI")) {
                tranRadiologi();
            } else if (cmbJnsTran.getSelectedItem().equals("LABORATORIUM")) {
                tranLaboratorium();
            } else if (cmbJnsTran.getSelectedItem().equals("PENANGANAN DOKTER")) {
                tranDokter();
            } else if (cmbJnsTran.getSelectedItem().equals("PENANGANAN PETUGAS")) {
                tranPetugas();
            } else if (cmbJnsTran.getSelectedItem().equals("PENANGANAN DOKTER & PETUGAS")) {
                tranDokterPetugas();
            }
        }
    }//GEN-LAST:event_BtnProsesTranActionPerformed

    private void ChkTglTranActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_ChkTglTranActionPerformed
        if (ChkTglTran.isSelected() == true) {
            ChkTglTran.setText("Dipilih");
        } else if (ChkTglTran.isSelected() == false) {
            ChkTglTran.setText("Semua Tgl. Transaksi");
            tglDari.setText("");
            pukulDari.setText("");
        }
    }//GEN-LAST:event_ChkTglTranActionPerformed

    private void cmbJnsTranItemStateChanged(java.awt.event.ItemEvent evt) {//GEN-FIRST:event_cmbJnsTranItemStateChanged

    }//GEN-LAST:event_cmbJnsTranItemStateChanged

    private void tbDataTranMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tbDataTranMouseClicked
        if (tabMode6.getRowCount() != 0) {
            try {
                getdataTran();
            } catch (java.lang.NullPointerException e) {
            }
        }
    }//GEN-LAST:event_tbDataTranMouseClicked

    private void tbDataTranKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_tbDataTranKeyPressed
        if (tabMode6.getRowCount() != 0) {
            if ((evt.getKeyCode() == KeyEvent.VK_ENTER) || (evt.getKeyCode() == KeyEvent.VK_UP) || (evt.getKeyCode() == KeyEvent.VK_DOWN)) {
                try {
                    getdataTran();
                } catch (java.lang.NullPointerException e) {
                }
            } else if (evt.getKeyCode() == KeyEvent.VK_V) {
                if (tbDataTran.getSelectedColumn() > 4) {
                    if (tbDataTran.getSelectedRow() != -1) {
                        if (tbDataTran.getValueAt(tbDataTran.getSelectedRow(), tbDataTran.getSelectedColumn()).toString().equals("false")) {
                            tbDataTran.setValueAt(true, tbDataTran.getSelectedRow(), tbDataTran.getSelectedColumn());
                        } else {
                            tbDataTran.setValueAt(false, tbDataTran.getSelectedRow(), tbDataTran.getSelectedColumn());
                        }
                    }
                }
            }
        }
    }//GEN-LAST:event_tbDataTranKeyPressed

    private void BtnCekTranActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_BtnCekTranActionPerformed
        if (TNoRwTerpilih.getText().trim().equals("")) {
            JOptionPane.showMessageDialog(null, "Nomor rawat dipilih harus sudah terisi dengan benar...!!");
            BtnCloseIn14.requestFocus();
        } else {
            if (cmbJnsTran.getSelectedItem().equals(" ")) {
                ChkTglTran.setSelected(false);
                ChkTglTran.setText("Semua Tgl. Transaksi");
                tglDari.setText("");
                pukulDari.setText("");
                tampilTranKosong();
            } else if (cmbJnsTran.getSelectedItem().equals("FARMASI")) {
                ChkTglTran.setSelected(false);
                ChkTglTran.setText("Semua Tgl. Transaksi");
                tglDari.setText("");
                pukulDari.setText("");
                tampilTranFarmasi();
            } else if (cmbJnsTran.getSelectedItem().equals("RADIOLOGI")) {
                ChkTglTran.setSelected(false);
                ChkTglTran.setText("Semua Tgl. Transaksi");
                tglDari.setText("");
                pukulDari.setText("");
                tampilTranRadiologi();
            } else if (cmbJnsTran.getSelectedItem().equals("LABORATORIUM")) {
                ChkTglTran.setSelected(false);
                ChkTglTran.setText("Semua Tgl. Transaksi");
                tglDari.setText("");
                pukulDari.setText("");
                tampilTranLaboratorium();
            } else if (cmbJnsTran.getSelectedItem().equals("PENANGANAN DOKTER")) {
                ChkTglTran.setSelected(false);
                ChkTglTran.setText("Semua Tgl. Transaksi");
                tglDari.setText("");
                pukulDari.setText("");
                tampilTranDokter();
            } else if (cmbJnsTran.getSelectedItem().equals("PENANGANAN PETUGAS")) {
                ChkTglTran.setSelected(false);
                ChkTglTran.setText("Semua Tgl. Transaksi");
                tglDari.setText("");
                pukulDari.setText("");
                tampilTranPetugas();
            } else if (cmbJnsTran.getSelectedItem().equals("PENANGANAN DOKTER & PETUGAS")) {
                ChkTglTran.setSelected(false);
                ChkTglTran.setText("Semua Tgl. Transaksi");
                tglDari.setText("");
                pukulDari.setText("");
                tampilTranDokterPetugas();
            }
        }
    }//GEN-LAST:event_BtnCekTranActionPerformed

    private void MnSrtMatiICUActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_MnSrtMatiICUActionPerformed
        i = 0;
        cekRuang = 0;
        i = Sequel.cariInteger("select count(billing.no_rawat) cek from billing where billing.no_rawat='" + norawat.getText() + "'");
        cekRuang = Sequel.cariInteger("SELECT COUNT(*) cek FROM kamar_inap ki INNER JOIN kamar k ON k.kd_kamar = ki.kd_kamar "
                + "INNER JOIN bangsal b ON b.kd_bangsal = k.kd_bangsal WHERE "
                + "ki.stts_pulang in ('-','pindah kamar') AND b.nm_bangsal LIKE '%iccu%' AND ki.no_rawat = '" + norawat.getText() + "'");

        if (tabMode.getRowCount() == 0) {
            JOptionPane.showMessageDialog(null, "Maaf, data pasien sudah habis...!!!!");
            tbKamIn.requestFocus();
        } else if (norawat.getText().trim().equals("")) {
            JOptionPane.showMessageDialog(null, "Maaf, Silahkan anda pilih dulu data pasien dengan mengklik data pada tabel...!!!");
            tbKamIn.requestFocus();
        } else if (i > 0) {
            JOptionPane.showMessageDialog(null, "Notanya sudah tersimpan, surat keterangan kematian manual tidak bisa diprint..!!!");
        } else if (cekRuang == 0) {
            JOptionPane.showMessageDialog(null, "Surat keterangan kematian ini hanya untuk Rg. ICU/ICCU saja & yang blm dipulangkan..!!!");
        } else {
            if (var.getkode().equals("Admin Utama")) {
                ctkSuratMatiICU();
                R1.requestFocus();
            } else {
                if (R1.isSelected() == true) {
                    ctkSuratMatiICU();
                    R1.requestFocus();
                } else {
                    JOptionPane.showMessageDialog(null, "Surat keterangan kematian manual Rg. ICU hanya bisa diprin pada pilihan pasien belum pulang...!!");
                    R1.requestFocus();
                }
            }
        }
    }//GEN-LAST:event_MnSrtMatiICUActionPerformed

    private void MnOrderLabActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_MnOrderLabActionPerformed
        if (tabMode.getRowCount() == 0) {
            JOptionPane.showMessageDialog(null, "Maaf, tabel masih kosong...!!!!");
            TCari.requestFocus();
        } else if (norawat.getText().trim().equals("")) {
            JOptionPane.showMessageDialog(null, "Maaf, Silahkan anda pilih dulu dengan mengklik data pada tabel...!!!");
            tbKamIn.requestFocus();
        } else {
            if (Sequel.cariRegistrasi(tbKamIn.getValueAt(tbKamIn.getSelectedRow(), 0).toString()) > 0) {
                JOptionPane.showMessageDialog(null, "Pasiennya sudah pulang dari rawat inap & sudah melakukan pembayaran seluruh tagihan biaya perawatan...!!!");
                tbKamIn.requestFocus();
            } else {
                this.setCursor(Cursor.getPredefinedCursor(Cursor.WAIT_CURSOR));
                DlgPermintaanLabRAZA lab = new DlgPermintaanLabRAZA(null, false);
                lab.setSize(978, 631);
                lab.setLocationRelativeTo(internalFrame1);
                lab.isPasien(norawat.getText());
                lab.AutoNomerMinta();
                lab.nmPemeriksaan.setText("");
                lab.nmPemeriksaan.requestFocus();
                lab.tampil();
                lab.tampilItemLab();
                lab.setVisible(true);
                this.setCursor(Cursor.getDefaultCursor());
            }
        }
    }//GEN-LAST:event_MnOrderLabActionPerformed

    private void MnOrderRadActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_MnOrderRadActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_MnOrderRadActionPerformed

    private void cmbJamRegActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_cmbJamRegActionPerformed

    }//GEN-LAST:event_cmbJamRegActionPerformed

    private void JregCmbTahunItemStateChanged(java.awt.event.ItemEvent evt) {//GEN-FIRST:event_JregCmbTahunItemStateChanged
        // TODO add your handling code here:
    }//GEN-LAST:event_JregCmbTahunItemStateChanged

    private void JregKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_JregKeyPressed
        // TODO add your handling code here:
    }//GEN-LAST:event_JregKeyPressed

    private void MregCmbTahunItemStateChanged(java.awt.event.ItemEvent evt) {//GEN-FIRST:event_MregCmbTahunItemStateChanged
        // TODO add your handling code here:
    }//GEN-LAST:event_MregCmbTahunItemStateChanged

    private void MregKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_MregKeyPressed
        // TODO add your handling code here:
    }//GEN-LAST:event_MregKeyPressed

    private void DregCmbTahunItemStateChanged(java.awt.event.ItemEvent evt) {//GEN-FIRST:event_DregCmbTahunItemStateChanged
        // TODO add your handling code here:
    }//GEN-LAST:event_DregCmbTahunItemStateChanged

    private void DregKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_DregKeyPressed
        // TODO add your handling code here:
    }//GEN-LAST:event_DregKeyPressed

    private void MnRegisterPersalinanActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_MnRegisterPersalinanActionPerformed
        if (tbKamIn.getRowCount() == 0) {
            JOptionPane.showMessageDialog(null, "Maaf, data sudah habis. Tidak ada data yang bisa anda print...!!!!");
            BtnCari.requestFocus();
        } else if (R1.isSelected() == true) {
            JOptionPane.showMessageDialog(null, "Silakan tentukan dulu pilihan tanggal masuknya...!!!!");
        } else if (R3.isSelected() == true) {
            JOptionPane.showMessageDialog(null, "Silakan tentukan dulu pilihan tanggal masuknya...!!!!");
        } else {
            Map<String, Object> param = new HashMap<>();
            param.put("periode", DTPCari1.getSelectedItem() + " S.D. " + DTPCari2.getSelectedItem());
            Valid.MyReport("rptRegisterPersalinan.jrxml", "report", "::[ Register Persalinan Pasien Rawat Inap ]::", " "
                    + "SELECT DISTINCT p1.no_rkm_medis no_rm, p1.nm_pasien, CONCAT(p1.alamat,', ',kl.nm_kel,', ',kc.nm_kec) alamat, kb.nm_kab, pj.png_jawab cr_byr, p1.agama, CONCAT(rp.umurdaftar,' ',rp.sttsumur) umur, "
                    + "DATE_FORMAT(rp.tgl_registrasi,'%d/%m/%y') tgl_msk, DATE_FORMAT(rp.jam_reg,'%H:%i') jam_msk, concat(ki1.diagnosa_awal,' (Ket. : ',dp.jns_persalinan,')') diag_ibu, IF(dp.rujukan='Tanpa Rujukan','-',dp.rujukan) rujukn, DATE_FORMAT(p2.tgl_lahir,'%d/%m/%y') tgl_lhr_by, "
                    + "DATE_FORMAT(pb.jam_lahir,'%H:%i') jam_lahir, pb.cara_lahir, pb.jenis_penolong, p2.jk, format(pb.berat_badan_benar,0) bb_bayi, IF(pb.keterangan='','-',pb.keterangan) diag_by, "
                    + "DATE_FORMAT(ki1.tgl_keluar,'%d/%m/%y') tgl_plg, b.nm_bangsal FROM data_persalinan dp "
                    + "INNER JOIN kamar_inap ki1 on ki1.no_rawat=dp.no_rawat INNER JOIN kamar k on k.kd_kamar=ki1.kd_kamar "
                    + "INNER JOIN bangsal b on b.kd_bangsal=k.kd_bangsal INNER JOIN reg_periksa rp on rp.no_rawat=dp.no_rawat "
                    + "INNER JOIN penjab pj on pj.kd_pj=rp.kd_pj INNER JOIN pasien p1 on p1.no_rkm_medis=rp.no_rkm_medis "
                    + "INNER JOIN kelurahan kl on kl.kd_kel=p1.kd_kel INNER JOIN kecamatan kc on kc.kd_kec=p1.kd_kec "
                    + "INNER JOIN kabupaten kb on kb.kd_kab=p1.kd_kab INNER JOIN pasien_bayi pb on pb.no_rawat_ibu=dp.no_rawat "
                    + "INNER JOIN pasien p2 on p2.no_rkm_medis=pb.no_rkm_medis INNER JOIN kamar_inap ki2 on ki2.no_rawat=pb.no_rawat_ibu "
                    + "WHERE rp.tgl_registrasi between '" + Valid.SetTgl(DTPCari1.getSelectedItem() + "") + "' and '" + Valid.SetTgl(DTPCari2.getSelectedItem() + "") + "' AND ki1.stts_pulang not in ('-','Pindah Kamar') UNION ALL "
                    + "SELECT DISTINCT px.no_rkm_medis no_rm, px.nm_pasien, CONCAT(px.alamat,', ',klx.nm_kel,', ',kcx.nm_kec) alamat, kbx.nm_kab, pjx.png_jawab cr_byr, px.agama, CONCAT(rpx.umurdaftar,' ',rpx.sttsumur) umur, "
                    + "DATE_FORMAT(rpx.tgl_registrasi,'%d/%m/%y') tgl_msk, DATE_FORMAT(rpx.jam_reg,'%H:%i') jam_msk, concat(kix.diagnosa_awal,' (Ket. : ',dpx.jns_persalinan,')') diag_ibu, IF(dpx.rujukan='Tanpa Rujukan','-',dpx.rujukan) rujukn, '-' tgl_lhr_by, "
                    + "'-' jam_lahir, '-' cara_lahir, '-' jenis_penolong, '-' jk, '-' bb_bayi, '-' diag_by, DATE_FORMAT(kix.tgl_keluar,'%d/%m/%y') tgl_plg, bx.nm_bangsal FROM data_persalinan dpx "
                    + "INNER JOIN kamar_inap kix ON kix.no_rawat = dpx.no_rawat INNER JOIN kamar kx ON kx.kd_kamar = kix.kd_kamar "
                    + "INNER JOIN bangsal bx ON bx.kd_bangsal = kx.kd_bangsal INNER JOIN reg_periksa rpx ON rpx.no_rawat = dpx.no_rawat "
                    + "INNER JOIN penjab pjx ON pjx.kd_pj = rpx.kd_pj INNER JOIN pasien px ON px.no_rkm_medis = rpx.no_rkm_medis "
                    + "INNER JOIN kelurahan klx ON klx.kd_kel = px.kd_kel INNER JOIN kecamatan kcx ON kcx.kd_kec = px.kd_kec INNER JOIN kabupaten kbx ON kbx.kd_kab = px.kd_kab "
                    + "WHERE rpx.tgl_registrasi BETWEEN '" + Valid.SetTgl(DTPCari1.getSelectedItem() + "") + "' and '" + Valid.SetTgl(DTPCari2.getSelectedItem() + "") + "' AND kix.stts_pulang NOT IN ('-', 'Pindah Kamar') "
                    + "AND dpx.jns_persalinan IN ('Abortus','OP Laparatomi','Perawatan / Konservatif')", param);
            this.setCursor(Cursor.getDefaultCursor());
        }
    }//GEN-LAST:event_MnRegisterPersalinanActionPerformed

    private void MnNonCovidActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_MnNonCovidActionPerformed
        if (tbKamIn.getRowCount() == 0) {
            JOptionPane.showMessageDialog(null, "Maaf, data sudah habis. Tidak ada data yang bisa anda print...!!!!");
            TCari.requestFocus();
        } else if (R1.isSelected() == true) {
            JOptionPane.showMessageDialog(null, "Silakan tentukan dulu pilihan tanggal masuknya...!!!!");
        } else if (R3.isSelected() == true) {
            JOptionPane.showMessageDialog(null, "Silakan tentukan dulu pilihan tanggal masuknya...!!!!");
        } else {
            this.setCursor(Cursor.getPredefinedCursor(Cursor.WAIT_CURSOR));
            if (!ruangrawat.getText().substring(0, 7).equals("ISOLASI")) {
                Map<String, Object> param = new HashMap<>();
                Valid.MyReport("rptSuratKeteranganRawat.jrxml", "report", "::[ Cetak Surat Keterangan Perawatan Inap ]::",
                        " SELECT p.no_rkm_medis, p.nm_pasien, CONCAT(DATE_FORMAT(p.tgl_lahir,'%d-%m-%Y'),' / ',rp.umurdaftar,' ',rp.sttsumur,'.') tgl_lhr_umur, "
                        + "ifnull(bs.no_kartu,'-') no_kartu, IF(ki.diagnosa_awal,'-',ki.diagnosa_awal) diag_awal, DATE_FORMAT(rp.tgl_registrasi,'%d-%m-%Y') tgl_msk, b.nm_bangsal, "
                        + "CONCAT('Martapura, ',DATE_FORMAT(rp.tgl_registrasi,'%d-%m-%Y')) tgl_surt, b.nm_gedung FROM reg_periksa rp "
                        + "INNER JOIN pasien p ON p.no_rkm_medis=rp.no_rkm_medis INNER JOIN kamar_inap ki on ki.no_rawat=rp.no_rawat "
                        + "INNER JOIN kamar k on k.kd_kamar=ki.kd_kamar INNER JOIN bangsal b on b.kd_bangsal=k.kd_bangsal "
                        + "LEFT JOIN bridging_sep bs on bs.no_rawat=ki.no_rawat WHERE rp.no_rkm_medis='" + TNoRM.getText() + "' AND ki.kd_kamar='" + kdkamar.getText() + "'", param);
            } else {
                JOptionPane.showMessageDialog(null, "Fitur ini hanya utk. selain pasien Covid 19 saja...!!!!");
            }
            this.setCursor(Cursor.getDefaultCursor());
        }
    }//GEN-LAST:event_MnNonCovidActionPerformed

    private void MnAssesmenAsuhanGiziActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_MnAssesmenAsuhanGiziActionPerformed
        if (tabMode.getRowCount() == 0) {
            JOptionPane.showMessageDialog(null, "Maaf, tabel masih kosong...!!!!");
            TCari.requestFocus();
        } else if (norawat.getText().equals("")) {
            JOptionPane.showMessageDialog(null, "Pilih salah satu pasiennya dulu pada tabel..!!!!");
        } else if (R1.isSelected() == false) {
            JOptionPane.showMessageDialog(null, "Silakan pilih data pasien pada pilihan belum pulang...!!!!");
        } else {
            cekHR = "";
            updateHari();
            tampil();
            cekHR = Sequel.cariIsi("select lama from kamar_inap where "
                    + "no_rawat='" + norawat.getText() + "' and "
                    + "kd_kamar='" + kdkamar.getText() + "' and "
                    + "tgl_masuk='" + tglInap.getText() + "' and "
                    + "jam_masuk='" + JamMasuk.getText() + "'");

            if (nmgedung.equals("ANAK")) {
                if (Integer.parseInt(cekHR) > 3) {
                    DlgAssesmenGiziUlang asesGiziUlang = new DlgAssesmenGiziUlang(null, false);
                    asesGiziUlang.setSize(internalFrame1.getWidth(), internalFrame1.getHeight());
                    asesGiziUlang.setLocationRelativeTo(internalFrame1);
                    asesGiziUlang.isCek();
                    asesGiziUlang.setPasien(norawat.getText(), ruangrawat.getText(), kdkamar.getText(), diagnosaawal.getText());
                    asesGiziUlang.emptTeks();
                    asesGiziUlang.TCari.setText("");
                    asesGiziUlang.ChkInput.setSelected(true);
                    asesGiziUlang.isForm();
                    asesGiziUlang.tampil();
                    asesGiziUlang.setVisible(true);
                } else {
                    DlgAssesmenGiziHarian asesGizi = new DlgAssesmenGiziHarian(null, false);
                    asesGizi.setSize(internalFrame1.getWidth(), internalFrame1.getHeight());
                    asesGizi.setLocationRelativeTo(internalFrame1);
                    asesGizi.isCek();
                    asesGizi.setPasien(norawat.getText(), ruangrawat.getText(), kdkamar.getText(), diagnosaawal.getText());
                    asesGizi.emptTeks();
                    asesGizi.TCari.setText("");
                    asesGizi.ChkInput.setSelected(true);
                    asesGizi.isForm();
                    asesGizi.tampil();
                    asesGizi.setVisible(true);
                }

            } else {
                if (Integer.parseInt(cekHR) <= 7) {
                    DlgAssesmenGiziHarian asesGizi = new DlgAssesmenGiziHarian(null, false);
                    asesGizi.setSize(internalFrame1.getWidth(), internalFrame1.getHeight());
                    asesGizi.setLocationRelativeTo(internalFrame1);
                    asesGizi.isCek();
                    asesGizi.setPasien(norawat.getText(), ruangrawat.getText(), kdkamar.getText(), diagnosaawal.getText());
                    asesGizi.emptTeks();
                    asesGizi.TCari.setText("");
                    asesGizi.ChkInput.setSelected(true);
                    asesGizi.isForm();
                    asesGizi.tampil();
                    asesGizi.setVisible(true);
                } else {
                    DlgAssesmenGiziUlang asesGiziUlang = new DlgAssesmenGiziUlang(null, false);
                    asesGiziUlang.setSize(internalFrame1.getWidth(), internalFrame1.getHeight());
                    asesGiziUlang.setLocationRelativeTo(internalFrame1);
                    asesGiziUlang.isCek();
                    asesGiziUlang.setPasien(norawat.getText(), ruangrawat.getText(), kdkamar.getText(), diagnosaawal.getText());
                    asesGiziUlang.emptTeks();
                    asesGiziUlang.TCari.setText("");
                    asesGiziUlang.ChkInput.setSelected(true);
                    asesGiziUlang.isForm();
                    asesGiziUlang.tampil();
                    asesGiziUlang.setVisible(true);
                }
            }
        }
    }//GEN-LAST:event_MnAssesmenAsuhanGiziActionPerformed

    private void BtnPrint2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_BtnPrint2ActionPerformed
        if (nosep.getText().equals("SEP belum dicetak, silakan cek lagi..!!!")) {
            JOptionPane.showMessageDialog(null, "Data SEP tidak ditemukan...!!!!");
        } else {
            this.setCursor(Cursor.getPredefinedCursor(Cursor.WAIT_CURSOR));
            Map<String, Object> param = new HashMap<>();
            param.put("namars", var.getnamars());
            param.put("alamatrs", var.getalamatrs());
            param.put("kotars", var.getkabupatenrs());
            param.put("propinsirs", var.getpropinsirs());
            param.put("kontakrs", var.getkontakrs());
            param.put("emailrs", var.getemailrs());
            param.put("logo", Sequel.cariGambar("select bpjs from gambar"));
            Valid.MyReport("rptBridgingSEP.jrxml", "report", "::[ Cetak SEP Rawat Inap ]::",
                    " SELECT bridging_sep.no_sep, bridging_sep.no_rawat, bridging_sep.nomr, bridging_sep.nama_pasien, bridging_sep.tglsep, "
                    + " bridging_sep.tglrujukan, bridging_sep.no_rujukan, bridging_sep.kdppkrujukan, bridging_sep.nmppkrujukan, bridging_sep.kdppkpelayanan, "
                    + " bridging_sep.nmppkpelayanan, IF (bridging_sep.jnspelayanan = '1','R.Inap','R.Jalan') jns_rawat, bridging_sep.catatan, bridging_sep.diagawal, "
                    + " bridging_sep.nmdiagnosaawal, bridging_sep.kdpolitujuan, bridging_sep.nmpolitujuan, "
                    + " IF (bridging_sep.klsrawat = '1','Kelas 1',IF (bridging_sep.klsrawat = '2','Kelas 2','Kelas 3')) kelas, "
                    + " IF (bridging_sep.lakalantas = '0','Kasus Kecelakaan','Bukan Kasus Kecelakaan'), bridging_sep.lokasilaka, bridging_sep. USER, "
                    + " CONCAT(DATE_FORMAT(bridging_sep.tanggal_lahir,'%Y-%m-%d'),' Kelamin : ',IF(bridging_sep.jkel='L','Laki-laki','Perempuan')) tanggal_lahir, "
                    + " bridging_sep.peserta, bridging_sep.jkel, bridging_sep.no_kartu, bridging_sep.asal_rujukan, bridging_sep.eksekutif, "
                    + " IF(bridging_sep.cob='0. Tidak','-',bridging_sep.cob) cob, IF(bridging_sep.penjamin='','-',bridging_sep.penjamin) penjamin, "
                    + " IF(bridging_sep.notelep='','-',bridging_sep.notelep) notelep FROM bridging_sep where no_sep='" + nosep.getText() + "' and jnspelayanan='1'", param);

            DlgNoSEP.dispose();
            emptTeks();
            tampil();
            this.setCursor(Cursor.getDefaultCursor());
        }
    }//GEN-LAST:event_BtnPrint2ActionPerformed

    private void BtnPrint2KeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_BtnPrint2KeyPressed

    }//GEN-LAST:event_BtnPrint2KeyPressed

    private void ppSuratKontrolBtnPrintActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_ppSuratKontrolBtnPrintActionPerformed
        if (tabMode.getRowCount() == 0) {
            JOptionPane.showMessageDialog(null, "Maaf, table masih kosong...!!!!");
            TCari.requestFocus();
        } else {
            if (tbKamIn.getSelectedRow() > -1) {
                if (tbKamIn.getValueAt(tbKamIn.getSelectedRow(), 0).toString().equals("")) {
                    try {
                        psanak = koneksi.prepareStatement(
                                "select ranap_gabung.no_rawat2 from ranap_gabung where ranap_gabung.no_rawat=?");
                        try {
                            psanak.setString(1, tbKamIn.getValueAt(tbKamIn.getSelectedRow() - 1, 0).toString());
                            rs2 = psanak.executeQuery();
                            if (rs2.next()) {
                                ps = koneksi.prepareStatement("select * from bridging_sep where no_rawat=?");
                                try {
                                    ps.setString(1, rs2.getString("no_rawat2"));
                                    rs = ps.executeQuery();
                                    if (rs.next()) {
                                        this.setCursor(Cursor.getPredefinedCursor(Cursor.WAIT_CURSOR));
                                        BPJSSuratKontrol form = new BPJSSuratKontrol(null, false);
                                        form.setNoRm(rs.getString("no_rawat"),
                                                Sequel.cariIsi("select no_sep from bridging_sep where no_rawat='" + rs.getString("no_rawat") + "'"),
                                                Sequel.cariIsi("select no_kartu from bridging_sep where no_rawat='" + rs.getString("no_rawat") + "'"),
                                                Sequel.cariIsi("select nomr from bridging_sep where no_rawat='" + rs.getString("no_rawat") + "'"),
                                                Sequel.cariIsi("select nama_pasien from bridging_sep where no_rawat='" + rs.getString("no_rawat") + "'"),
                                                Sequel.cariIsi("select tanggal_lahir from bridging_sep where no_rawat='" + rs.getString("no_rawat") + "'"),
                                                Sequel.cariIsi("select jkel from bridging_sep where no_rawat='" + rs.getString("no_rawat") + "'"),
                                                Sequel.cariIsi("select nmdiagnosaawal from bridging_sep where no_rawat='" + rs.getString("no_rawat") + "'"));
                                        form.setSize(internalFrame1.getWidth() - 20, internalFrame1.getHeight() - 20);
                                        form.setLocationRelativeTo(internalFrame1);
                                        form.setVisible(true);
                                        this.setCursor(Cursor.getDefaultCursor());
                                    } else {
                                        JOptionPane.showMessageDialog(null, "Pasien tersebut belum dibikinkan SEP, silahkan hubungi petugas yg. bersangkutan..!!");
                                        TCari.requestFocus();
                                    }
                                } catch (Exception e) {
                                    System.out.println("Notif : " + e);
                                } finally {
                                    if (rs != null) {
                                        rs.close();
                                    }
                                    if (ps != null) {
                                        ps.close();
                                    }
                                }
                            } else {
                                JOptionPane.showMessageDialog(null, "Maaf, Silahkan anda pilih dulu pasien...!!!");
                                tbKamIn.requestFocus();
                            }
                        } catch (Exception ex) {
                            System.out.println("Notifikasi : " + ex);
                        } finally {
                            if (rs2 != null) {
                                rs2.close();
                            }
                            if (psanak != null) {
                                psanak.close();
                            }
                        }
                    } catch (Exception e) {
                        System.out.println(e);
                    }
                } else {
                    try {
                        ps = koneksi.prepareStatement("select * from bridging_sep where no_rawat=?");
                        try {
                            ps.setString(1, norawat.getText());
                            rs = ps.executeQuery();
                            if (rs.next()) {
                                this.setCursor(Cursor.getPredefinedCursor(Cursor.WAIT_CURSOR));
                                BPJSSuratKontrol form = new BPJSSuratKontrol(null, false);
                                form.setNoRm(rs.getString("no_rawat"),
                                        Sequel.cariIsi("select no_sep from bridging_sep where no_rawat='" + rs.getString("no_rawat") + "'"),
                                        Sequel.cariIsi("select no_kartu from bridging_sep where no_rawat='" + rs.getString("no_rawat") + "'"),
                                        Sequel.cariIsi("select nomr from bridging_sep where no_rawat='" + rs.getString("no_rawat") + "'"),
                                        Sequel.cariIsi("select nama_pasien from bridging_sep where no_rawat='" + rs.getString("no_rawat") + "'"),
                                        Sequel.cariIsi("select tanggal_lahir from bridging_sep where no_rawat='" + rs.getString("no_rawat") + "'"),
                                        Sequel.cariIsi("select jkel from bridging_sep where no_rawat='" + rs.getString("no_rawat") + "'"),
                                        Sequel.cariIsi("select nmdiagnosaawal from bridging_sep where no_rawat='" + rs.getString("no_rawat") + "'"));
                                form.setSize(internalFrame1.getWidth() - 20, internalFrame1.getHeight() - 20);
                                form.setLocationRelativeTo(internalFrame1);
                                form.setVisible(true);
                                this.setCursor(Cursor.getDefaultCursor());
                            } else {
                                JOptionPane.showMessageDialog(null, "Pasien tersebut belum dibikinkan SEP, silahkan hubungi petugas yg. bersangkutan..!!");
                                TCari.requestFocus();
                            }
                        } catch (Exception e) {
                            System.out.println("Notif : " + e);
                        } finally {
                            if (rs != null) {
                                rs.close();
                            }
                            if (ps != null) {
                                ps.close();
                            }
                        }
                    } catch (Exception e) {
                        System.out.println("Notif : " + e);
                    }
                }
            }
        }
    }//GEN-LAST:event_ppSuratKontrolBtnPrintActionPerformed

    private void MnMonevAsuhanGiziActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_MnMonevAsuhanGiziActionPerformed
        if (tabMode.getRowCount() == 0) {
            JOptionPane.showMessageDialog(null, "Maaf, tabel masih kosong...!!!!");
            TCari.requestFocus();
        } else if (norawat.getText().equals("")) {
            JOptionPane.showMessageDialog(null, "Pilih salah satu pasiennya dulu pada tabel..!!!!");
        } else if (R1.isSelected() == false) {
            JOptionPane.showMessageDialog(null, "Silakan pilih data pasien pada pilihan belum pulang...!!!!");
        } else {
            DlgMonevAsuhanGizi monevGZ = new DlgMonevAsuhanGizi(null, false);
            monevGZ.setSize(internalFrame1.getWidth(), internalFrame1.getHeight());
            monevGZ.setLocationRelativeTo(internalFrame1);
            monevGZ.isCek();
            monevGZ.setPasien(norawat.getText(), ruangrawat.getText(), kdkamar.getText());
            monevGZ.emptTeks();
            monevGZ.TCari.setText("");
            monevGZ.ChkInput.setSelected(true);
            monevGZ.isForm();
            monevGZ.tampil();
            monevGZ.setVisible(true);
        }
    }//GEN-LAST:event_MnMonevAsuhanGiziActionPerformed

    private void MrkCoxActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_MrkCoxActionPerformed
        if (tabMode.getRowCount() == 0) {
            JOptionPane.showMessageDialog(null, "Maaf, tabel masih kosong...!!!!");
            TCari.requestFocus();
        } else if (noRMCopy.getText().equals("")) {
            JOptionPane.showMessageDialog(null, "Silahkan klik dulu salah satu data pasiennya pada tabel...!!!!");
            tbKamIn.requestFocus();
        } else if (R1.isSelected() == false) {
            JOptionPane.showMessageDialog(null, "Label pasien hanya bisa dicetak utk. yang masih dirawat inap saja...!!!!");
        } else {
            Valid.MyReport("rptLabelPxRanap5.jrxml", "report", "::[ Label Pasien (4,9 x 1,8 Cm) Merek COX ]::",
                    "select no_rkm_medis, concat(nm_pasien,' (',if(jk='L','LK','PR'),')') nm_pasien, "
                    + "date_format(tgl_lahir,'%d-%m-%Y') tgl_lhr from pasien where no_rkm_medis='" + noRMCopy.getText() + "'");
        }
    }//GEN-LAST:event_MrkCoxActionPerformed

    private void MnPrinterBaruActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_MnPrinterBaruActionPerformed
        if (tabMode.getRowCount() == 0) {
            JOptionPane.showMessageDialog(null, "Maaf, data registrasi sudah habis...!!!!");
            TCari.requestFocus();
        } else if (TPasien.getText().trim().equals("")) {
            JOptionPane.showMessageDialog(null, "Maaf, Silahkan anda pilih dulu data registrasi pada tabel...!!!");
            TCari.requestFocus();
        } else {
            this.setCursor(Cursor.getPredefinedCursor(Cursor.WAIT_CURSOR));
            Map<String, Object> param = new HashMap<>();
            param.put("namars", var.getnamars());
            param.put("kotars", var.getkabupatenrs());
            param.put("logo", Sequel.cariGambar("select logo from setting"));
            param.put("tgl_lahir", Sequel.cariIsi("select date_format(tgl_lahir,'%d') from pasien where no_rkm_medis='" + TNoRM.getText() + "'") + " "
                    + Sequel.bulanINDONESIA("select month(tgl_lahir) from pasien where no_rkm_medis='" + TNoRM.getText() + "'") + " "
                    + Sequel.cariIsi("select year(tgl_lahir) from pasien where no_rkm_medis='" + TNoRM.getText() + "'"));
            Valid.MyReport("rptGelangDewasa.jrxml", "report", "::[ Gelang Pasien Dewasa/Anak (Laki-laki/Perempuan) ]::",
                    "select no_rkm_medis, nm_pasien, jk from pasien where no_rkm_medis='" + TNoRM.getText() + "' ", param);
            this.setCursor(Cursor.getDefaultCursor());
        }
    }//GEN-LAST:event_MnPrinterBaruActionPerformed

    private void MnPrinterLamaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_MnPrinterLamaActionPerformed
        if (tabMode.getRowCount() == 0) {
            JOptionPane.showMessageDialog(null, "Maaf, data registrasi sudah habis...!!!!");
            TCari.requestFocus();
        } else if (TPasien.getText().trim().equals("")) {
            JOptionPane.showMessageDialog(null, "Maaf, Silahkan anda pilih dulu data registrasi pada tabel...!!!");
            TCari.requestFocus();
        } else {
            this.setCursor(Cursor.getPredefinedCursor(Cursor.WAIT_CURSOR));
            Map<String, Object> param = new HashMap<>();
            param.put("namars", var.getnamars());
            param.put("alamatrs", var.getalamatrs());
            param.put("kotars", var.getkabupatenrs());
            param.put("propinsirs", var.getpropinsirs());
            param.put("kontakrs", var.getkontakrs());
            param.put("emailrs", var.getemailrs());
            param.put("logo", Sequel.cariGambar("select logo from setting"));
            param.put("tgl_lahir", Sequel.cariIsi("select date_format(tgl_lahir,'%d') from pasien where no_rkm_medis='" + TNoRM.getText() + "'") + " "
                    + Sequel.bulanINDONESIA("select month(tgl_lahir) from pasien where no_rkm_medis='" + TNoRM.getText() + "'") + " "
                    + Sequel.cariIsi("select year(tgl_lahir) from pasien where no_rkm_medis='" + TNoRM.getText() + "'"));
            Valid.MyReport("rptBarcodeRM7.jrxml", "report", "::[ Gelang Pasien Dewasa/Anak (Laki-laki/Perempuan) ]::",
                    "select no_rkm_medis, nm_pasien, jk from pasien where no_rkm_medis='" + TNoRM.getText() + "' ", param);
            this.setCursor(Cursor.getDefaultCursor());
        }
    }//GEN-LAST:event_MnPrinterLamaActionPerformed

    private void MnPrinterBaru1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_MnPrinterBaru1ActionPerformed
        if (tabMode.getRowCount() == 0) {
            JOptionPane.showMessageDialog(null, "Maaf, data registrasi sudah habis...!!!!");
            TCari.requestFocus();
        } else if (TPasien.getText().trim().equals("")) {
            JOptionPane.showMessageDialog(null, "Maaf, Silahkan anda pilih dulu data registrasi pada tabel...!!!");
            TCari.requestFocus();
        } else {
            this.setCursor(Cursor.getPredefinedCursor(Cursor.WAIT_CURSOR));
            Map<String, Object> param = new HashMap<>();
            param.put("namars", var.getnamars());
            param.put("kotars", var.getkabupatenrs());
            param.put("logo", Sequel.cariGambar("select logo from setting"));
            param.put("tgl_lahir", Sequel.cariIsi("select date_format(tgl_lahir,'%d') from pasien where no_rkm_medis='" + TNoRM.getText() + "'") + " "
                    + Sequel.bulanINDONESIA("select month(tgl_lahir) from pasien where no_rkm_medis='" + TNoRM.getText() + "'") + " "
                    + Sequel.cariIsi("select year(tgl_lahir) from pasien where no_rkm_medis='" + TNoRM.getText() + "'"));
            Valid.MyReport("rptGelangBayi2021.jrxml", "report", "::[ Gelang Pasien Bayi (Laki-laki/Perempuan) ]::",
                    "select no_rkm_medis, nm_pasien, jk from pasien where no_rkm_medis='" + TNoRM.getText() + "' ", param);
            this.setCursor(Cursor.getDefaultCursor());
        }
    }//GEN-LAST:event_MnPrinterBaru1ActionPerformed

    private void MnPrinterLama1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_MnPrinterLama1ActionPerformed
        if (tabMode.getRowCount() == 0) {
            JOptionPane.showMessageDialog(null, "Maaf, data registrasi sudah habis...!!!!");
            TCari.requestFocus();
        } else if (TPasien.getText().trim().equals("")) {
            JOptionPane.showMessageDialog(null, "Maaf, Silahkan anda pilih dulu data registrasi pada tabel...!!!");
            TCari.requestFocus();
        } else {
            this.setCursor(Cursor.getPredefinedCursor(Cursor.WAIT_CURSOR));
            Map<String, Object> param = new HashMap<>();
            param.put("namars", var.getnamars());
            param.put("alamatrs", var.getalamatrs());
            param.put("kotars", var.getkabupatenrs());
            param.put("propinsirs", var.getpropinsirs());
            param.put("kontakrs", var.getkontakrs());
            param.put("emailrs", var.getemailrs());
            param.put("logo", Sequel.cariGambar("select logo from setting"));
            param.put("tgl_lahir", Sequel.cariIsi("select date_format(tgl_lahir,'%d') from pasien where no_rkm_medis='" + TNoRM.getText() + "'") + " "
                    + Sequel.bulanINDONESIA("select month(tgl_lahir) from pasien where no_rkm_medis='" + TNoRM.getText() + "'") + " "
                    + Sequel.cariIsi("select year(tgl_lahir) from pasien where no_rkm_medis='" + TNoRM.getText() + "'"));
            Valid.MyReport("rptGelangBayi.jrxml", "report", "::[ Gelang Pasien Bayi (Laki-laki/Perempuan) ]::",
                    "select no_rkm_medis, nm_pasien, jk from pasien where no_rkm_medis='" + TNoRM.getText() + "' ", param);
            this.setCursor(Cursor.getDefaultCursor());
        }
    }//GEN-LAST:event_MnPrinterLama1ActionPerformed

    private void MrkAjpActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_MrkAjpActionPerformed
        if (tabMode.getRowCount() == 0) {
            JOptionPane.showMessageDialog(null, "Maaf, tabel masih kosong...!!!!");
            TCari.requestFocus();
        } else if (noRMCopy.getText().equals("")) {
            JOptionPane.showMessageDialog(null, "Silahkan klik dulu salah satu data pasiennya pada tabel...!!!!");
            tbKamIn.requestFocus();
        } else if (R1.isSelected() == false) {
            JOptionPane.showMessageDialog(null, "Label pasien hanya bisa dicetak utk. yang masih dirawat inap saja...!!!!");
        } else {
            Valid.MyReport("rptLabelPxRanap6.jrxml", "report", "::[ Label Pasien (4,9 x 1,8 Cm) Merek AJP BRAND ]::",
                    "select no_rkm_medis, concat(nm_pasien,' (',if(jk='L','LK','PR'),')') nm_pasien, "
                    + "date_format(tgl_lahir,'%d-%m-%Y') tgl_lhr from pasien where no_rkm_medis='" + noRMCopy.getText() + "'");
        }
    }//GEN-LAST:event_MrkAjpActionPerformed

    private void MrkChampionActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_MrkChampionActionPerformed
        if (tabMode.getRowCount() == 0) {
            JOptionPane.showMessageDialog(null, "Maaf, tabel masih kosong...!!!!");
            TCari.requestFocus();
        } else if (noRMCopy.getText().equals("")) {
            JOptionPane.showMessageDialog(null, "Silahkan klik dulu salah satu data pasiennya pada tabel...!!!!");
            tbKamIn.requestFocus();
        } else if (R1.isSelected() == false) {
            JOptionPane.showMessageDialog(null, "Label pasien hanya bisa dicetak utk. yang masih dirawat inap saja...!!!!");
        } else {
            Valid.MyReport("rptLabelPxRanap7.jrxml", "report", "::[ Label Pasien (4,9 x 1,8 Cm) Merek CHAMPION ]::",
                    "select no_rkm_medis, concat(nm_pasien,' (',if(jk='L','LK','PR'),')') nm_pasien, "
                    + "date_format(tgl_lahir,'%d-%m-%Y') tgl_lhr from pasien where no_rkm_medis='" + noRMCopy.getText() + "'");
        }
    }//GEN-LAST:event_MrkChampionActionPerformed

    private void MnCovidActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_MnCovidActionPerformed
        if (tbKamIn.getRowCount() == 0) {
            JOptionPane.showMessageDialog(null, "Maaf, data sudah habis. Tidak ada data yang bisa anda print...!!!!");
            TCari.requestFocus();
        } else if (R1.isSelected() == true) {
            JOptionPane.showMessageDialog(null, "Silakan tentukan dulu pilihan tanggal masuknya...!!!!");
        } else if (R3.isSelected() == true) {
            JOptionPane.showMessageDialog(null, "Silakan tentukan dulu pilihan tanggal masuknya...!!!!");
        } else {
            this.setCursor(Cursor.getPredefinedCursor(Cursor.WAIT_CURSOR));
            if (ruangrawat.getText().substring(0, 7).equals("ISOLASI")) {
                karu.setSize(487, 161);
                karu.setLocationRelativeTo(internalFrame1);
                karu.setData(TNoRM.getText(), kdkamar.getText());
                karu.setVisible(true);
            } else {
                JOptionPane.showMessageDialog(null, "Fitur ini hanya utk. pasien Covid 19 saja...!!!!");
            }
            this.setCursor(Cursor.getDefaultCursor());
        }
    }//GEN-LAST:event_MnCovidActionPerformed

    /**
     * @param args the command line arguments
     */
    public static void main(String args[]) {
        java.awt.EventQueue.invokeLater(() -> {
            DlgKamarInap dialog = new DlgKamarInap(new javax.swing.JFrame(), true);
            dialog.addWindowListener(new java.awt.event.WindowAdapter() {
                @Override
                public void windowClosing(java.awt.event.WindowEvent e) {
                    System.exit(0);
                }
            });
            dialog.setVisible(true);
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private widget.TextBox BangsalCari;
    private widget.Button BtnAll;
    private widget.Button BtnAll3;
    private widget.Button BtnBatal;
    private widget.Button BtnBatal2;
    private widget.Button BtnCari;
    private widget.Button BtnCari3;
    private widget.Button BtnCari5;
    private widget.Button BtnCekTran;
    public widget.Button BtnCetakGB;
    private widget.Button BtnCloseGabung;
    private widget.Button BtnCloseIn;
    private widget.Button BtnCloseIn1;
    private widget.Button BtnCloseIn11;
    private widget.Button BtnCloseIn12;
    private widget.Button BtnCloseIn13;
    private widget.Button BtnCloseIn14;
    private widget.Button BtnCloseIn2;
    private widget.Button BtnCloseIn3;
    private widget.Button BtnCloseIn4;
    private widget.Button BtnCloseIn5;
    private widget.Button BtnCloseIn6;
    public widget.Button BtnCloseIn8;
    private widget.Button BtnCloseIn9;
    private widget.Button BtnCloseInpindah;
    private widget.Button BtnCtkJkd;
    private widget.Button BtnCtkJmp;
    public widget.Button BtnEdit2;
    private widget.Button BtnEdit3;
    private widget.Button BtnGantiMati;
    private widget.Button BtnGantiTgl;
    private widget.Button BtnGantijkd;
    private widget.Button BtnGantijmp;
    private widget.Button BtnGantikode;
    public widget.Button BtnHapus;
    private widget.Button BtnHapusGabung;
    private widget.Button BtnHapusMati;
    private widget.Button BtnIn;
    private widget.Button BtnKeluar;
    private widget.Button BtnOut;
    private widget.Button BtnPrint1;
    private widget.Button BtnPrint2;
    private widget.Button BtnProsesTran;
    private widget.Button BtnSimpan;
    private widget.Button BtnSimpan1;
    private widget.Button BtnSimpan10;
    private widget.Button BtnSimpan3;
    private widget.Button BtnSimpan4;
    private widget.Button BtnSimpan6;
    public widget.Button BtnSimpan7;
    private widget.Button BtnSimpan8;
    private widget.Button BtnSimpan9;
    private widget.Button BtnSimpanGabung;
    private widget.Button BtnSimpanpindah;
    public widget.CekBox ChkInput;
    private widget.CekBox ChkTglTran;
    private widget.ComboBox CmbBln;
    private widget.ComboBox CmbBlnpindah;
    private widget.ComboBox CmbTahun;
    private widget.ComboBox CmbTahunpindah;
    private widget.ComboBox CmbTgl;
    private widget.ComboBox CmbTglpindah;
    private widget.Tanggal DTPCari1;
    private widget.Tanggal DTPCari10;
    private widget.Tanggal DTPCari2;
    private widget.Tanggal DTPCari3;
    private widget.Tanggal DTPCari4;
    private widget.Tanggal DTPCari9;
    private javax.swing.JDialog DlgJamkesda;
    private javax.swing.JDialog DlgJampersal;
    private javax.swing.JDialog DlgMati;
    private javax.swing.JDialog DlgNoSEP;
    private widget.ComboBox Dreg;
    private widget.PanelBiasa FormInput;
    private widget.TextBox JamMasuk;
    private widget.TextBox JamPulang;
    private widget.TextBox JenisMati;
    private widget.ComboBox Jreg;
    private widget.Label LCount;
    private widget.Label LCount1;
    private widget.Label LblStts;
    private javax.swing.JMenuItem MnAssesmenAsuhanGizi;
    private javax.swing.JMenuItem MnAsuhanGizi;
    private javax.swing.JMenu MnBarcodeRM;
    private javax.swing.JMenuItem MnBarcodeRM1;
    private javax.swing.JMenuItem MnBarcodeRM2;
    private javax.swing.JMenuItem MnBatalPulang;
    private javax.swing.JMenuItem MnBilling;
    private javax.swing.JMenu MnBridging;
    private javax.swing.JMenu MnCetakKelengkapanInap;
    private javax.swing.JMenuItem MnCovid;
    private javax.swing.JMenuItem MnDPJP;
    private javax.swing.JMenuItem MnDPJPRanap;
    private javax.swing.JMenu MnDataBPJS;
    private javax.swing.JMenuItem MnDataMati;
    private javax.swing.JMenuItem MnDeposit;
    private javax.swing.JMenuItem MnDiagnosa;
    private javax.swing.JMenuItem MnDiagnosaAwal;
    private javax.swing.JMenuItem MnDiet;
    private javax.swing.JMenuItem MnDietMakanan;
    private javax.swing.JMenuItem MnFormulirKematian;
    private javax.swing.JMenu MnGantiData;
    private javax.swing.JMenu MnGelangBayi;
    private javax.swing.JMenu MnGelangDewasaAnak;
    private javax.swing.JMenuItem MnGiziBuruk;
    private javax.swing.JMenuItem MnGiziBurukPx;
    private javax.swing.JMenu MnHapusData;
    private javax.swing.JMenuItem MnHapusDataSalah;
    private javax.swing.JMenuItem MnHapusObatOperasi;
    private javax.swing.JMenuItem MnHapusTagihanOperasi;
    private javax.swing.JMenuItem MnIndividuPx;
    private javax.swing.JMenu MnInputData;
    private javax.swing.JMenuItem MnInputResep;
    private javax.swing.JMenu MnKartu;
    private javax.swing.JMenuItem MnKartuNonUmum;
    private javax.swing.JMenuItem MnKartuUmum;
    private javax.swing.JMenuItem MnLabelPxRanap1;
    private javax.swing.JMenuItem MnLabelPxRanap2;
    private javax.swing.JMenu MnLabelPxRanap3;
    private javax.swing.JMenu MnLabelRM;
    private javax.swing.JMenuItem MnLabelRM1;
    private javax.swing.JMenuItem MnLabelRM2;
    private javax.swing.JMenu MnLaporan;
    private javax.swing.JMenuItem MnLihatSEP;
    private javax.swing.JMenuItem MnManualSEPBPJS;
    private javax.swing.JMenuItem MnMonevAsuhanGizi;
    private javax.swing.JMenuItem MnNoResep;
    private javax.swing.JMenuItem MnNonCovid;
    private javax.swing.JMenu MnObat;
    private javax.swing.JMenuItem MnOperasi;
    private javax.swing.JMenuItem MnOrderLab;
    private javax.swing.JMenuItem MnOrderRad;
    private javax.swing.JMenuItem MnPemberianObat;
    private javax.swing.JMenuItem MnPencapaianAsuhanGZ;
    private javax.swing.JMenuItem MnPengantarPulang;
    private javax.swing.JMenuItem MnPenjab;
    private javax.swing.JMenuItem MnPeriksaLab;
    private javax.swing.JMenuItem MnPeriksaRadiologi;
    private javax.swing.JMenu MnPermintaan;
    private javax.swing.JMenuItem MnPindahTransaksi;
    private javax.swing.JMenuItem MnPrinterBaru;
    private javax.swing.JMenuItem MnPrinterBaru1;
    private javax.swing.JMenuItem MnPrinterLama;
    private javax.swing.JMenuItem MnPrinterLama1;
    private javax.swing.JMenuItem MnRM2D;
    private javax.swing.JMenuItem MnRawatInap;
    private javax.swing.JMenuItem MnRawatJalan;
    private javax.swing.JMenuItem MnRegisterPasien;
    private javax.swing.JMenuItem MnRegisterPersalinan;
    private javax.swing.JMenuItem MnResepPulang;
    private javax.swing.JMenuItem MnReturJual;
    private javax.swing.JMenuItem MnRincianObat;
    private javax.swing.JMenuItem MnRingkasanPulang;
    private javax.swing.JMenuItem MnRujuk;
    private javax.swing.JMenuItem MnRujukMasuk;
    private javax.swing.JMenuItem MnRujukSisrute;
    private javax.swing.JMenu MnRujukan;
    private javax.swing.JMenuItem MnSEP;
    private javax.swing.JMenuItem MnSEPJamkesda;
    private javax.swing.JMenuItem MnSEPJampersal;
    private javax.swing.JMenuItem MnSJP;
    private javax.swing.JMenuItem MnSKDPBPJS;
    private javax.swing.JMenuItem MnSelisihTarif;
    private javax.swing.JMenuItem MnSensusRanap;
    private javax.swing.JMenuItem MnSrtAPS;
    private javax.swing.JMenuItem MnSrtMati;
    private javax.swing.JMenuItem MnSrtMatiICU;
    private javax.swing.JMenuItem MnStatusGZ;
    private javax.swing.JMenuItem MnStatusGizi;
    private javax.swing.JMenuItem MnStatusPulang;
    private javax.swing.JMenuItem MnStokObatPasien;
    private javax.swing.JMenu MnSuratKeteranganRawat;
    private javax.swing.JMenuItem MnTilikBedah;
    private javax.swing.JMenu MnTindakan;
    private javax.swing.JMenuItem MnTracerInap;
    private javax.swing.JMenuItem MnUpdateHari;
    private javax.swing.JMenuItem MnWaktuRegRalan;
    private javax.swing.JMenu Mndpjp;
    private widget.ComboBox Mreg;
    private javax.swing.JMenuItem MrkAjp;
    private javax.swing.JMenuItem MrkAlfa;
    private javax.swing.JMenuItem MrkChampion;
    private javax.swing.JMenuItem MrkCox;
    private javax.swing.JMenuItem MrkKojico;
    private javax.swing.JMenuItem MrkOlean;
    private widget.TextBox NmBayi;
    private widget.TextBox NoRMmati;
    private widget.TextBox NoRawatGabung;
    private widget.TextBox NoRmBayi;
    private widget.TextBox NoSEP;
    private javax.swing.JPanel PanelCariUtama;
    private javax.swing.JPanel PanelInput1;
    private widget.RadioButton R1;
    private widget.RadioButton R2;
    private widget.RadioButton R3;
    private widget.RadioButton Rganti1;
    private widget.RadioButton Rganti2;
    private widget.RadioButton Rganti3;
    private widget.RadioButton Rganti4;
    private widget.ScrollPane Scroll;
    private widget.ScrollPane Scroll3;
    private widget.ScrollPane Scroll5;
    private widget.ScrollPane Scroll6;
    private widget.TextBox StatusDaftar;
    private widget.TextBox TBangsal;
    private widget.TextBox TBangsalpindah;
    private widget.TextBox TCari;
    public widget.TextBox TCari3;
    private widget.TextBox TCari4;
    private widget.TextBox TDiagnosaAwal;
    private widget.TextBox TIn;
    private widget.TextBox TJmlHari;
    private widget.TextBox TJmlHaripindah;
    private widget.TextBox TKdBngsal;
    private widget.TextBox TKdBngsalpindah;
    private widget.TextBox TNoRM;
    private widget.TextBox TNoRMpindah;
    private widget.TextBox TNoRwTerpilih;
    private widget.TextBox TNoRwTujuan;
    private widget.TextBox TOut;
    private widget.TextBox TPasien;
    private widget.TextBox TPasienpindah;
    private widget.TextBox TSttsKamar;
    private widget.TextBox TSttsKamarpindah;
    private widget.TextBox TTarif;
    private widget.TextBox TTarifpindah;
    private widget.Tanggal TglMati;
    private widget.Tanggal TglMati1;
    private widget.Tanggal TglRegRalan;
    private widget.Tanggal Tglsurat;
    private widget.Tanggal Tglsurat1;
    private javax.swing.JDialog WindowCaraBayar;
    private javax.swing.JDialog WindowDiagnosaAwal;
    public javax.swing.JDialog WindowGiziBuruk;
    private javax.swing.JDialog WindowInputKamar;
    private javax.swing.JDialog WindowPindahKamar;
    private javax.swing.JDialog WindowPindahkanTransaksi;
    private javax.swing.JDialog WindowPulangAPS;
    private javax.swing.JDialog WindowRanapGabung;
    private javax.swing.JDialog WindowSelisihTarif;
    private javax.swing.JDialog WindowStatusPulang;
    private javax.swing.JDialog WindowWaktuRegRalan;
    private widget.TextBox alasanAPS;
    private widget.TextBox albumin;
    private widget.TextBox asalFaskes;
    private widget.TextBox bbAkhir;
    private widget.TextBox bbAwal;
    private widget.TextBox bbpb;
    private widget.TextBox bbu;
    private widget.Button btnBangsalCari;
    private widget.Button btnBayar;
    private widget.Button btnDiagnosa;
    private widget.Button btnFaskes;
    private widget.Button btnKamar;
    private widget.Button btnKamar2;
    private widget.Button btnPasienRanapGabung;
    private widget.Button btnPasienRanapGabung1;
    private widget.Button btnPindah;
    private widget.Button btnReg;
    private javax.swing.ButtonGroup buttonGroup1;
    private javax.swing.ButtonGroup buttonGroup2;
    private widget.TextBox catatanIGD;
    private widget.TextBox cekDataAnak;
    private widget.ComboBox cmbDtk;
    private widget.ComboBox cmbDtk1;
    private widget.ComboBox cmbDtk2;
    private widget.ComboBox cmbDtkpindah;
    private widget.ComboBox cmbJam;
    private widget.ComboBox cmbJam1;
    private widget.ComboBox cmbJam2;
    private widget.CekBox cmbJamReg;
    private widget.ComboBox cmbJampindah;
    private widget.ComboBox cmbJnsTran;
    private widget.ComboBox cmbMnt;
    private widget.ComboBox cmbMnt1;
    private widget.ComboBox cmbMnt2;
    private widget.ComboBox cmbMntpindah;
    private widget.ComboBox cmbRuangKhusus1;
    private widget.ComboBox cmbRuangKhusus2;
    private widget.ComboBox cmbRuangKhusus3;
    private widget.ComboBox cmbRuangKhusus4;
    private widget.ComboBox cmbRuangKhusus5;
    private widget.ComboBox cmbRuangan;
    private widget.ComboBox cmbStatus;
    private widget.ComboBox cmbSttsPlg;
    private widget.TextBox dataGB;
    private widget.TextBox dataGZ;
    private widget.TextBox deskripsiKD;
    private widget.TextBox diagDokterGZ;
    private widget.TextBox diagawalGB;
    private widget.TextBox diagnosaakhir;
    private widget.TextBox diagnosaawal;
    private widget.TextBox dibayar;
    private widget.TextBox hakkelas;
    private widget.TextBox hasilLM;
    private widget.TextBox hb;
    private widget.InternalFrame internalFrame1;
    private widget.InternalFrame internalFrame10;
    private widget.InternalFrame internalFrame11;
    public widget.InternalFrame internalFrame13;
    private widget.InternalFrame internalFrame14;
    private widget.InternalFrame internalFrame16;
    private widget.InternalFrame internalFrame17;
    private widget.InternalFrame internalFrame18;
    private widget.InternalFrame internalFrame19;
    private widget.InternalFrame internalFrame2;
    private widget.InternalFrame internalFrame3;
    private widget.InternalFrame internalFrame5;
    private widget.InternalFrame internalFrame6;
    private widget.InternalFrame internalFrame7;
    private widget.InternalFrame internalFrame8;
    private widget.InternalFrame internalFrame9;
    private widget.Label jLabel10;
    private widget.Label jLabel100;
    private widget.Label jLabel101;
    private widget.Label jLabel104;
    private widget.Label jLabel105;
    private widget.Label jLabel106;
    private widget.Label jLabel107;
    private widget.Label jLabel108;
    private widget.Label jLabel109;
    private widget.Label jLabel11;
    private widget.Label jLabel110;
    private widget.Label jLabel111;
    private widget.Label jLabel112;
    private widget.Label jLabel115;
    private widget.Label jLabel12;
    private widget.Label jLabel13;
    private widget.Label jLabel14;
    private widget.Label jLabel15;
    private widget.Label jLabel16;
    private widget.Label jLabel17;
    private widget.Label jLabel18;
    private widget.Label jLabel19;
    private widget.Label jLabel20;
    private widget.Label jLabel21;
    private widget.Label jLabel22;
    private widget.Label jLabel23;
    private widget.Label jLabel24;
    private widget.Label jLabel25;
    private widget.Label jLabel26;
    private widget.Label jLabel27;
    private widget.Label jLabel28;
    private widget.Label jLabel29;
    private widget.Label jLabel3;
    private widget.Label jLabel30;
    private widget.Label jLabel31;
    private widget.Label jLabel32;
    private widget.Label jLabel33;
    private widget.Label jLabel34;
    private widget.Label jLabel35;
    private widget.Label jLabel36;
    private widget.Label jLabel37;
    private widget.Label jLabel38;
    private widget.Label jLabel39;
    private widget.Label jLabel4;
    private widget.Label jLabel40;
    private widget.Label jLabel41;
    private widget.Label jLabel42;
    private widget.Label jLabel43;
    private widget.Label jLabel44;
    private widget.Label jLabel45;
    private widget.Label jLabel46;
    private widget.Label jLabel47;
    private widget.Label jLabel48;
    private widget.Label jLabel49;
    private widget.Label jLabel50;
    private widget.Label jLabel51;
    private widget.Label jLabel52;
    private widget.Label jLabel53;
    private widget.Label jLabel54;
    private widget.Label jLabel55;
    private widget.Label jLabel56;
    private widget.Label jLabel57;
    private widget.Label jLabel58;
    private widget.Label jLabel59;
    private widget.Label jLabel6;
    private widget.Label jLabel60;
    private widget.Label jLabel61;
    private widget.Label jLabel62;
    private widget.Label jLabel63;
    private widget.Label jLabel64;
    private widget.Label jLabel7;
    private widget.Label jLabel71;
    private widget.Label jLabel72;
    private widget.Label jLabel73;
    private widget.Label jLabel74;
    private widget.Label jLabel75;
    private widget.Label jLabel76;
    private widget.Label jLabel77;
    private widget.Label jLabel78;
    private widget.Label jLabel79;
    private widget.Label jLabel8;
    private widget.Label jLabel80;
    private widget.Label jLabel81;
    private widget.Label jLabel82;
    private widget.Label jLabel83;
    private widget.Label jLabel84;
    private widget.Label jLabel85;
    private widget.Label jLabel86;
    private widget.Label jLabel87;
    private widget.Label jLabel88;
    private widget.Label jLabel89;
    private widget.Label jLabel90;
    private widget.Label jLabel91;
    private widget.Label jLabel92;
    private widget.Label jLabel93;
    private widget.Label jLabel94;
    private widget.Label jLabel95;
    private widget.Label jLabel96;
    private widget.Label jLabel98;
    private javax.swing.JPanel jPanel5;
    private javax.swing.JPopupMenu jPopupMenu1;
    private javax.swing.JPopupMenu jPopupMenu2;
    private javax.swing.JPopupMenu.Separator jSeparator1;
    private widget.TextBox jnsBayar;
    private widget.TextBox kdAkses;
    private widget.TextBox kdINACBG;
    private widget.TextBox kdkamar;
    private widget.TextBox kdkamarpindah;
    private widget.TextBox kdpenjab;
    private widget.TextBox ket;
    private widget.TextBox ket1;
    private widget.TextBox ketAPS;
    private widget.Label labelbyr;
    private widget.TextBox leukosit;
    private widget.TextBox lmrawat;
    private widget.TextBox naikKLS;
    private widget.TextBox nmpasien;
    private widget.TextBox nmpenjab;
    private widget.TextBox nmpxGB;
    private widget.TextBox noRMCopy;
    private widget.TextBox noSrt;
    private widget.TextBox noSrt1;
    private widget.TextBox nokartu;
    private widget.TextBox nominal1;
    private widget.TextBox nominal2;
    private widget.TextBox norawat;
    private widget.TextBox norawatCopy;
    private widget.TextBox norawatSEP;
    private widget.TextBox norawatpindah;
    private widget.TextBox norm;
    private widget.TextBox normGB;
    private widget.TextBox norwGB;
    private widget.TextBox nosep;
    private widget.panelisi panelCari;
    private widget.panelisi panelGlass10;
    private widget.panelisi panelGlass11;
    private widget.panelisi panelGlass12;
    private widget.panelisi panelGlass9;
    private widget.TextBox pasienTerpilih;
    private widget.TextBox pbtb;
    private widget.TextBox pbu;
    private widget.TextBox pemberianNutrisi;
    private widget.TextBox perhitunganZatGZ;
    private widget.TextBox persenSELISIH;
    private widget.TextBox plt;
    private javax.swing.JMenuItem ppCatatanPasien;
    private javax.swing.JMenuItem ppDataHAIs;
    private javax.swing.JMenuItem ppDataPersalinan;
    private javax.swing.JMenuItem ppDataPonek;
    private javax.swing.JMenuItem ppPasienCorona;
    private javax.swing.JMenuItem ppPerawatanCorona;
    private javax.swing.JMenuItem ppRiwayat;
    private javax.swing.JMenuItem ppSuratKontrol;
    private widget.TextBox pukulDari;
    private widget.TextBox rginap;
    private widget.TextBox ruangDicetak;
    private widget.TextBox ruangrawat;
    private widget.TextBox sepJkd;
    private widget.TextBox sepJmp;
    private widget.TextBox statusSELISIH;
    private widget.TextBox statusSEP;
    private widget.TextBox status_pulang;
    private widget.TextBox tarifkls1;
    private widget.TextBox tarifkls2;
    private widget.TextBox tarifkls3;
    private widget.Table tbAPS;
    private widget.Table tbDataTran;
    private widget.Table tbGiziBuruk;
    private widget.Table tbKamIn;
    private widget.TextBox tglDari;
    private widget.TextBox tglInap;
    private widget.Tanggal tglMasukInap;
    private widget.TextBox tglsep;
    private widget.TextBox ttlbiaya;
    private widget.TextBox ttlbiayapindah;
    private widget.TextBox umurGB;
    private widget.TextBox userBerizin;
    // End of variables declaration//GEN-END:variables

    public void tampil() {
        if (R1.isSelected() == true) {
            kmr = " a.stts_pulang='-' ";
            if (!BangsalCari.getText().equals("")) {
                kmr = " a.stts_pulang='-' and a.nm_bangsal='" + BangsalCari.getText() + "' ";
            }
        } else if (R2.isSelected() == true) {
            kmr = " a.tgl_masuk between '" + Valid.SetTgl(DTPCari1.getSelectedItem() + "") + "' and '" + Valid.SetTgl(DTPCari2.getSelectedItem() + "") + "' ";
            if (!BangsalCari.getText().equals("")) {
                kmr = " a.tgl_masuk between '" + Valid.SetTgl(DTPCari1.getSelectedItem() + "") + "' and '" + Valid.SetTgl(DTPCari2.getSelectedItem() + "") + "' and a.nm_bangsal='" + BangsalCari.getText() + "' ";
            }
        } else if (R3.isSelected() == true) {
            kmr = " a.tgl_keluar between '" + Valid.SetTgl(DTPCari3.getSelectedItem() + "") + "' and '" + Valid.SetTgl(DTPCari4.getSelectedItem() + "") + "' ";
            if (!BangsalCari.getText().equals("")) {
                kmr = " a.tgl_keluar between '" + Valid.SetTgl(DTPCari3.getSelectedItem() + "") + "' and '" + Valid.SetTgl(DTPCari4.getSelectedItem() + "") + "' and a.nm_bangsal='" + BangsalCari.getText() + "' ";
            }
        }

        key = kmr + " ";
        if (!TCari.getText().equals("")) {
            key = kmr + "and a.no_rawat like '%" + TCari.getText().trim() + "%' or "
                    + kmr + "and a.no_rkm_medis like '%" + TCari.getText().trim() + "%' or "
                    + kmr + "and a.nm_pasien like '%" + TCari.getText().trim() + "%' or "
                    + kmr + "and concat(a.alamat,', ',a.nm_kel,', ',a.nm_kec,', ',a.nm_kab) like '%" + TCari.getText().trim() + "%' or "
                    + kmr + "and a.kd_kamar like '%" + TCari.getText().trim() + "%' or "
                    + kmr + "and a.nm_bangsal like '%" + TCari.getText().trim() + "%' or "
                    + kmr + "and a.diagnosa_awal like '%" + TCari.getText().trim() + "%' or "
                    + kmr + "and a.diagnosa_akhir like '%" + TCari.getText().trim() + "%' or "
                    + kmr + "and a.trf_kamar like '%" + TCari.getText().trim() + "%' or "
                    + kmr + "and a.tgl_masuk like '%" + TCari.getText().trim() + "%' or "
                    + kmr + "and a.nm_dokter like '%" + TCari.getText().trim() + "%' or "
                    + kmr + "and a.stts_pulang like '%" + TCari.getText().trim() + "%' or "
                    + kmr + "and a.tgl_keluar like '%" + TCari.getText().trim() + "%' or "
                    + kmr + "and a.png_jawab like '%" + TCari.getText().trim() + "%' or "
                    + kmr + "and IF (b.no_rawat = a.no_rawat OR c.no_rawat = a.no_rawat or d.no_rawat = a.no_rawat, 'SUDAH', IF (a.kd_pj <> 'b01' AND a.kd_pj not in ('d01','d04'),'NON SEP','BELUM')) like '%" + TCari.getText().trim() + "%' or "
                    + kmr + "and a.ttl_biaya like '%" + TCari.getText().trim() + "%' ";
        }

        Valid.tabelKosong(tabMode);
        try {
            try {
                rs = koneksi.prepareStatement(
                        " SELECT a.no_rawat, a.no_rkm_medis, a.nm_pasien, a.tgl_lahir, a.alamat, a.png_jawab, "
                        + "IF (b.no_rawat = a.no_rawat OR c.no_rawat = a.no_rawat or d.no_rawat = a.no_rawat, 'SUDAH', IF (a.kd_pj <> 'b01' AND a.kd_pj not in ('d01','d04'),'NON SEP','BELUM')) sep, "
                        + "a.kd_kamar, a.nm_bangsal, a.trf_kamar, a.diagnosa_awal, a.diagnosa_akhir, a.tgl_masuk, a.jam_masuk, a.tgl_keluar, a.jam_keluar, "
                        + "a.ttl_biaya, a.stts_pulang, a.lama, a.nm_dokter, a.no_tlp, a.kd_pj, a.nm_kel,a.nm_kec,a.nm_kab, a.dokter2 FROM  "
                        + "((SELECT ki.no_rawat, r.no_rkm_medis, p.nm_pasien, date_format(p.tgl_lahir,'%d-%m-%Y') tgl_lahir, "
                        + "concat(p.alamat,', ',kl.nm_kel,', ',kc.nm_kec,',',kb.nm_kab) alamat, pj.png_jawab, ki.kd_kamar, "
                        + "b.nm_bangsal, ki.trf_kamar, ki.diagnosa_awal, ki.diagnosa_akhir, ki.tgl_masuk, ki.jam_masuk, IF (ki.tgl_keluar ='0000-00-00','',ki.tgl_keluar) tgl_keluar, "
                        + "IF (ki.jam_keluar = '00:00:00','',ki.jam_keluar) jam_keluar, ki.ttl_biaya, ki.stts_pulang, ki.lama, "
                        + "d.nm_dokter, r.kd_pj,kl.nm_kel,kc.nm_kec,kb.nm_kab,k.kelas,p.no_tlp, d1.nm_dokter dokter2 "
                        + "FROM kamar_inap ki INNER JOIN reg_periksa r ON ki.no_rawat = r.no_rawat "
                        + "INNER JOIN pasien p ON p.no_rkm_medis = r.no_rkm_medis "
                        + "INNER JOIN dokter d ON d.kd_dokter = r.kd_dokter "
                        + "INNER JOIN penjab pj ON pj.kd_pj = r.kd_pj "
                        + "INNER JOIN kamar k ON k.kd_kamar = ki.kd_kamar "
                        + "INNER JOIN bangsal b ON b.kd_bangsal = k.kd_bangsal "
                        + "INNER JOIN kelurahan kl ON kl.kd_kel = p.kd_kel "
                        + "INNER JOIN kecamatan kc ON kc.kd_kec = p.kd_kec "
                        + "INNER JOIN kabupaten kb ON kb.kd_kab = p.kd_kab "
                        + "LEFT JOIN dpjp_ranap dr ON dr.no_rawat = ki.no_rawat "
                        + "LEFT JOIN dokter d1 ON d1.kd_dokter = dr.kd_dokter) AS a "
                        + "LEFT JOIN (SELECT DISTINCT bs.no_rawat FROM	bridging_sep bs WHERE bs.jnspelayanan = '1') AS b "
                        + "ON a.no_rawat = b.no_rawat "
                        + "LEFT JOIN (SELECT DISTINCT no_rawat FROM bridging_jamkesda where jns_rawat = 'inap') AS c "
                        + "ON c.no_rawat = a.no_rawat "
                        + "LEFT JOIN (SELECT DISTINCT no_rawat FROM bridging_jampersal WHERE jns_rawat = 'inap') AS d "
                        + "ON d.no_rawat = a.no_rawat) where " + key + " order by a.nm_bangsal, a.tgl_masuk, a.jam_masuk").executeQuery();

                while (rs.next()) {
                    tabMode.addRow(new String[]{
                        rs.getString("no_rawat"),
                        rs.getString("no_rkm_medis"),
                        rs.getString("nm_pasien"),
                        rs.getString("tgl_lahir"),
                        rs.getString("alamat"),
                        rs.getString("png_jawab"),
                        rs.getString("sep"),
                        rs.getString("kd_kamar"),
                        rs.getString("nm_bangsal"),
                        Valid.SetAngka(rs.getDouble("trf_kamar")),
                        rs.getString("diagnosa_awal"),
                        rs.getString("diagnosa_akhir"),
                        rs.getString("tgl_masuk"),
                        rs.getString("jam_masuk"),
                        rs.getString("tgl_keluar"),
                        rs.getString("jam_keluar"),
                        Valid.SetAngka(rs.getDouble("ttl_biaya")),
                        rs.getString("stts_pulang"),
                        rs.getString("lama"),
                        rs.getString("nm_dokter"),
                        rs.getString("no_tlp"),
                        rs.getString("dokter2")
                    });
                    psanak = koneksi.prepareStatement(
                            "select pasien.no_rkm_medis,pasien.nm_pasien,ranap_gabung.no_rawat2,concat(reg_periksa.umurdaftar,' ',reg_periksa.sttsumur)as umur,pasien.no_peserta, "
                            + "concat(pasien.alamatpj,', ',pasien.kelurahanpj,', ',pasien.kecamatanpj,', ',pasien.kabupatenpj) as alamat "
                            + "from reg_periksa inner join pasien inner join ranap_gabung on "
                            + "pasien.no_rkm_medis=reg_periksa.no_rkm_medis and ranap_gabung.no_rawat2=reg_periksa.no_rawat where ranap_gabung.no_rawat=?");
                    try {
                        psanak.setString(1, rs.getString(1));
                        rs2 = psanak.executeQuery();
                        if (rs2.next()) {
                            tabMode.addRow(new String[]{"",
                                rs2.getString("no_rkm_medis"),
                                rs2.getString("nm_pasien"),
                                rs.getString(4),
                                rs.getString(5),
                                rs.getString(6),
                                rs.getString(7),
                                rs.getString(8),
                                Valid.SetAngka(rs.getDouble(9) * (persenbayi / 100)), "-", "-",
                                rs.getString(12),
                                rs.getString(13),
                                rs.getString(14),
                                rs.getString(15),
                                Valid.SetAngka(rs.getDouble(16) * (persenbayi / 100)),
                                rs.getString(17),
                                rs.getString(18),
                                rs.getString(19)});
                        }
                    } catch (Exception ex) {
                        System.out.println("Notifikasi : " + ex);
                    } finally {
                        if (rs2 != null) {
                            rs2.close();
                        }
                        if (psanak != null) {
                            psanak.close();
                        }
                    }
                }
            } catch (Exception e) {
                System.out.println("Notifikasi : " + e);
            } finally {
                if (rs != null) {
                    rs.close();
                }
            }
        } catch (Exception e) {
            System.out.println("Notifikasi : " + e);
        }
        LCount.setText("" + tabMode.getRowCount());
    }

    public void emptTeks() {
        norawat.setText("");
        TNoRM.setText("");
        TPasien.setText("");
        kdkamar.setText("");
        TKdBngsal.setText("");
        TBangsal.setText("");
        diagnosaawal.setText("");
        diagnosaakhir.setText("");
        date = new Date();
        now = dateFormat.format(date);
        CmbTahun.setSelectedItem(now.substring(0, 4));
        CmbBln.setSelectedItem(now.substring(5, 7));
        CmbTgl.setSelectedItem(now.substring(8, 10));
        cmbJam.setSelectedItem(now.substring(11, 13));
        cmbMnt.setSelectedItem(now.substring(14, 16));
        cmbDtk.setSelectedItem(now.substring(17, 19));

        TOut.setText("");
        TIn.setText("");
        JamMasuk.setText("");
        TTarif.setText("0");
        TJmlHari.setText("0");
        ttlbiaya.setText("0");
        norawat.requestFocus();
        tglMasukInap.setDate(new Date());

        noSrt.setText("");
        noSrt1.setText("");
        Tglsurat.setDate(new Date());
        Tglsurat1.setDate(new Date());
        sepJkd.setText("");
        sepJmp.setText("");
        DlgJamkesda.setVisible(false);
        DlgJampersal.setVisible(false);
        tglInap.setText("");
        nmpenjab.setText("");
        catatanIGD.setText("");

        ket.setText("-");
        TglMati.setDate(new Date());
        cmbJam1.setSelectedIndex(0);
        cmbMnt1.setSelectedIndex(0);
        cmbDtk1.setSelectedIndex(0);
        NoRMmati.setText("");
        StatusDaftar.setText("");
        JamPulang.setText("");
        JenisMati.setText("");
        ruangrawat.setText("");
        statusSEP.setText("");

        cmbRuangan.setEditable(false);
        DTPCari1.setEditable(false);
        DTPCari2.setEditable(false);
        DTPCari3.setEditable(false);
        DTPCari4.setEditable(false);

        nominal1.setText("0");
        nominal2.setText("0");
        hasilLM.setText("");
        statusSELISIH.setText("");
        dataGZ.setText("");
        dataGB.setText("");
        cekDataAnak.setText("");
        status_pulang.setText("");
        cmbSttsPlg.setSelectedIndex(0);
        norawatCopy.setText("");
        noRMCopy.setText("");
        TDiagnosaAwal.setText("");
        ChkTglTran.setSelected(false);
        ChkTglTran.setText("Semua Tgl. Transaksi");
        TNoRwTujuan.setText("");
        cmbJnsTran.setSelectedIndex(0);
        tglDari.setText("");
        pukulDari.setText("");
        tampilTranKosong();
    }

    private void getData() {
        TOut.setText("");
        TIn.setText("");
        JamMasuk.setText("");
        norawatAPS = "";
        nmgedung = "";

        if (tbKamIn.getSelectedRow() != -1) {
            norawat.setText(tbKamIn.getValueAt(tbKamIn.getSelectedRow(), 0).toString());
            norawatCopy.setText(norawat.getText());
            TNoRwTerpilih.setText(norawat.getText());
            norawatAPS = norawat.getText();
            nmpenjab.setText(tbKamIn.getValueAt(tbKamIn.getSelectedRow(), 5).toString());
            tglInap.setText(tbKamIn.getValueAt(tbKamIn.getSelectedRow(), 12).toString());
            Sequel.cariIsi("select no_rkm_medis from reg_periksa where no_rawat=? ", TNoRM, norawat.getText());
            Sequel.cariIsi("select kd_pj from reg_periksa where no_rawat=? ", kdpenjab, norawat.getText());
            Sequel.cariIsi("select nm_pasien from pasien where no_rkm_medis=? ", TPasien, TNoRM.getText());
            noRMCopy.setText(TNoRM.getText());
            Sequel.cariIsi("select jam_keluar from kamar_inap where no_rawat=? ", JamPulang, norawat.getText());
            Sequel.cariIsi("select concat(umurdaftar,' ',sttsumur) umur from reg_periksa where no_rawat=? ", umurGB, norawat.getText());
            Sequel.cariIsi("select stts_pulang from kamar_inap where no_rawat=? and "
                    + "stts_pulang not in ('Dirujuk','APS','Sembuh/BLPL','Kabur','-','Pindah Kamar') ", JenisMati, norawat.getText());
            norawatpindah.setText(tbKamIn.getValueAt(tbKamIn.getSelectedRow(), 0).toString());
            TNoRMpindah.setText(TNoRM.getText());
            TPasienpindah.setText(TPasien.getText());
            kdkamar.setText(tbKamIn.getValueAt(tbKamIn.getSelectedRow(), 7).toString());
            Sequel.cariIsi("SELECT b.nm_bangsal FROM kamar k INNER JOIN bangsal b ON b.kd_bangsal = k.kd_bangsal WHERE k.kd_kamar=? ", ruangrawat, kdkamar.getText());
            nmgedung = Sequel.cariIsi("SELECT b.nm_gedung FROM kamar k INNER JOIN bangsal b ON b.kd_bangsal = k.kd_bangsal WHERE k.kd_kamar='" + kdkamar.getText() + "'");
            diagnosaawal.setText(tbKamIn.getValueAt(tbKamIn.getSelectedRow(), 10).toString());
            diagnosaakhir.setText(tbKamIn.getValueAt(tbKamIn.getSelectedRow(), 11).toString());
            TIn.setText(tbKamIn.getValueAt(tbKamIn.getSelectedRow(), 12).toString());
            Valid.SetTgl(tglMasukInap, tbKamIn.getValueAt(tbKamIn.getSelectedRow(), 12).toString());
            JamMasuk.setText(tbKamIn.getValueAt(tbKamIn.getSelectedRow(), 13).toString());
            TOut.setText(tbKamIn.getValueAt(tbKamIn.getSelectedRow(), 14).toString());
            ttlbiaya.setText(tbKamIn.getValueAt(tbKamIn.getSelectedRow(), 16).toString());
            status_pulang.setText(tbKamIn.getValueAt(tbKamIn.getSelectedRow(), 17).toString());
            statusSEP.setText(tbKamIn.getValueAt(tbKamIn.getSelectedRow(), 6).toString());
            norwGB.setText(norawat.getText());
            normGB.setText(TNoRM.getText());
            nmpxGB.setText(TPasien.getText());
            TCari3.setText(norawat.getText());
            diagawalGB.setText(diagnosaawal.getText());

            NoRMmati.setText(Sequel.cariIsi("SELECT no_rkm_medis FROM pasien_mati WHERE no_rkm_medis='" + TNoRM.getText() + "' "));
            Valid.SetTgl(Tglsurat, Sequel.cariIsi("SELECT tgl_surat FROM bridging_jamkesda WHERE no_rawat='" + norawat.getText() + "' AND jns_rawat='Inap' "));
            Valid.SetTgl(Tglsurat1, Sequel.cariIsi("SELECT tgl_surat FROM bridging_jampersal WHERE no_rawat='" + norawat.getText() + "' AND jns_rawat='Inap' "));
            noSrt.setText(Sequel.cariIsi("SELECT no_surat FROM bridging_jamkesda WHERE no_rawat='" + norawat.getText() + "' AND jns_rawat='Inap'"));
            noSrt1.setText(Sequel.cariIsi("SELECT no_surat FROM bridging_jampersal WHERE no_rawat='" + norawat.getText() + "' AND jns_rawat='Inap'"));
            sepJkd.setText(Sequel.cariIsi("SELECT no_sep FROM bridging_jamkesda WHERE no_rawat='" + norawat.getText() + "' AND jns_rawat='Inap'"));
            sepJmp.setText(Sequel.cariIsi("SELECT no_sep FROM bridging_jampersal WHERE no_rawat='" + norawat.getText() + "' AND jns_rawat='Inap'"));

            if ((R2.isSelected() == true) || (R3.isSelected() == true)) {
                cmbStatus.setSelectedItem("-");

                if ((NoRMmati.getText().equals("")) && (JenisMati.getText().equals("Meninggal >= 48 Jam") || (JenisMati.getText().equals("Meninggal < 48 Jam")))) {
                    Sequel.menyimpan("pasien_mati", "'" + TOut.getText() + "','" + JamPulang.getText() + "','"
                            + TNoRM.getText() + "','" + JenisMati.getText() + " diruang "
                            + ruangrawat.getText() + "','Rumah Sakit','-','-','-','-','Ruangan Inap','-'", "pasien");
                }

            } else if (!NoRMmati.getText().equals("")) {
                JOptionPane.showMessageDialog(null, "Data pasien tersebut sudah tersimpan dilaporan rekap pasien meninggal...!!!");
                tampil();
                emptTeks();
            } else {
                cmbStatus.setSelectedItem(tbKamIn.getValueAt(tbKamIn.getSelectedRow(), 16).toString());
            }

            if (noSrt.getText().equals("")) {
                tbKamIn.requestFocus();
            } else {
                noSrt.setText(Sequel.cariIsi("SELECT no_surat FROM bridging_jamkesda WHERE no_rawat='" + norawat.getText() + "' AND jns_rawat='Inap'"));
                sepJkd.setText(Sequel.cariIsi("SELECT no_sep FROM bridging_jamkesda WHERE no_rawat='" + norawat.getText() + "' AND jns_rawat='Inap'"));
            }

            if (noSrt1.getText().equals("")) {
                tbKamIn.requestFocus();
            } else {
                noSrt1.setText(Sequel.cariIsi("SELECT no_surat FROM bridging_jampersal WHERE no_rawat='" + norawat.getText() + "' AND jns_rawat='Inap'"));
                sepJmp.setText(Sequel.cariIsi("SELECT no_sep FROM bridging_jampersal WHERE no_rawat='" + norawat.getText() + "' AND jns_rawat='Inap'"));
            }

        }
    }

    private void isKmr() {
        if (i == 1) {
            kd_pj = Sequel.cariIsi("select kd_pj from reg_periksa where no_rawat=?", norawat.getText());
            Sequel.cariIsi("select kd_bangsal from kamar where kd_kamar=? ", TKdBngsal, kdkamar.getText());
            Sequel.cariIsi("select nm_bangsal from bangsal where kd_bangsal=? ", TBangsal, TKdBngsal.getText());
            Sequel.cariIsi("select status from kamar where kd_kamar=? ", TSttsKamar, kdkamar.getText());
            try {
                pstarif = koneksi.prepareStatement("select tarif from set_harga_kamar where kd_kamar=? and kd_pj=?");
                try {
                    pstarif.setString(1, kdkamar.getText());
                    pstarif.setString(2, kd_pj);
                    rs = pstarif.executeQuery();
                    if (rs.next()) {
                        TTarif.setText(rs.getString(1));
                    } else {
//                        Sequel.cariIsi("select trf_kamar from kamar where kd_kamar=? ", TTarif, kdkamar.getText());
                        if (Sequel.cariInteger("select count(-1) from reg_periksa where no_rawat = '" + norawat.getText() + "' and tgl_registrasi < '2018-05-01'") > 0) {
                            Sequel.cariIsi("select trf_kamar from riwayat_tarif_kamar where kd_kamar=? ", TTarif, kdkamar.getText());
                        } else {
                            Sequel.cariIsi("select trf_kamar from kamar where kd_kamar=? ", TTarif, kdkamar.getText());
                        }
//                        Sequel.cariIsi("select trf_kamar from riwayat_tarif_kamar where kd_kamar=? ", TTarif, kdkamar.getText());
                    }
                } catch (Exception e) {
                    System.out.println("Notifikasi : " + e);
                } finally {
                    if (rs != null) {
                        rs.close();
                    }
                    if (pstarif != null) {
                        pstarif.close();
                    }
                }
            } catch (Exception e) {
                System.out.println("Notifikasi : " + e);
            }
        } else {
            kd_pj = Sequel.cariIsi("select kd_pj from reg_periksa where no_rawat=?", norawatpindah.getText());
            Sequel.cariIsi("select kd_bangsal from kamar where kd_kamar=? ", TKdBngsalpindah, kdkamarpindah.getText());
            Sequel.cariIsi("select nm_bangsal from bangsal where kd_bangsal=? ", TBangsalpindah, TKdBngsalpindah.getText());
            Sequel.cariIsi("select status from kamar where kd_kamar=? ", TSttsKamarpindah, kdkamarpindah.getText());
            if (Sequel.cariInteger("select count(-1) from reg_periksa where no_rawat = '" + norawat.getText() + "' and tgl_registrasi < '2018-05-01'") > 0) {
                Sequel.cariIsi("select trf_kamar from riwayat_tarif_kamar where kd_kamar=? ", TTarif, kdkamar.getText());
            } else {
                Sequel.cariIsi("select trf_kamar from kamar where kd_kamar=? ", TTarifpindah, kdkamarpindah.getText());
            }

//            Sequel.cariIsi("select trf_kamar from kamar where kd_kamar=? ", TTarifpindah, kdkamarpindah.getText());
            try {
                pstarif = koneksi.prepareStatement("select tarif from set_harga_kamar where kd_kamar=? and kd_pj=?");
                try {
                    pstarif.setString(1, kdkamarpindah.getText());
                    pstarif.setString(2, kd_pj);
                    rs = pstarif.executeQuery();
                    if (rs.next()) {
                        TTarifpindah.setText(rs.getString(1));
                    } else {
                        if (Sequel.cariInteger("select count(-1) from reg_periksa where no_rawat = '" + norawat.getText() + "' and tgl_registrasi < '2018-05-01'") > 0) {
                            Sequel.cariIsi("select trf_kamar from riwayat_tarif_kamar where kd_kamar=? ", TTarif, kdkamar.getText());
                        } else {
                            Sequel.cariIsi("select trf_kamar from kamar where kd_kamar=? ", TTarifpindah, kdkamar.getText());
                        }

//                        Sequel.cariIsi("select trf_kamar from kamar where kd_kamar=? ", TTarifpindah, kdkamar.getText());
                    }
                } catch (Exception e) {
                    System.out.println("Notifikasi : " + e);
                } finally {
                    if (rs != null) {
                        rs.close();
                    }
                    if (pstarif != null) {
                        pstarif.close();
                    }
                }
            } catch (Exception e) {
                System.out.println("Notifikasi : " + e);
            }
        }
    }

    private void isjml() {
        DecimalFormat df2 = new DecimalFormat("####");
        if ((!TJmlHari.getText().equals("")) && (!TTarif.getText().equals(""))) {
            double x = Double.parseDouble(TJmlHari.getText().trim());
            double y = Double.parseDouble(TTarif.getText().trim());
            ttlbiaya.setText(df2.format(x * y));
        }

        if ((!TJmlHaripindah.getText().equals("")) && (!TTarifpindah.getText().equals(""))) {
            double x = Double.parseDouble(TJmlHaripindah.getText().trim());
            double y = Double.parseDouble(TTarifpindah.getText().trim());
            ttlbiayapindah.setText(df2.format(x * y));
        }
    }

    public void setNoRm(String norwt) {
        norawat.setText(norwt);
        Sequel.cariIsi("select no_rkm_medis from reg_periksa where no_rawat=? ", TNoRM, norawat.getText());
        Sequel.cariIsi("select nm_pasien from pasien where no_rkm_medis=? ", TPasien, TNoRM.getText());
        R1.setSelected(true);
        TCari.setText(norwt);
        try {
            ps = koneksi.prepareStatement("select no_rawat, kd_kamar, diagnosa_awal, diagnosa_akhir, tgl_masuk, jam_masuk, tgl_keluar, jam_keluar, ttl_biaya "
                    + "from kamar_inap where no_rawat=? order by tgl_masuk,jam_masuk desc limit 1 ");
            try {
                ps.setString(1, norawat.getText());
                rs = ps.executeQuery();
                if (rs.next()) {
                    norawat.setEditable(false);
                    norawat.setText(rs.getString(1));
                    kdkamar.setText(rs.getString(2));
                    diagnosaawal.setText(rs.getString(3));
                    diagnosaakhir.setText(rs.getString(4));
                    TIn.setText(rs.getString(5));
                    JamMasuk.setText(rs.getString(6));
                    TOut.setText(rs.getString(7));
                    ttlbiaya.setText(rs.getString(8));

                    kdkamar.setEditable(false);
                    diagnosaawal.setEditable(false);
                    diagnosaakhir.setVisible(true);
                    btnDiagnosa.setVisible(true);
                    jLabel23.setVisible(true);
                    cmbStatus.setVisible(true);
                    jLabel26.setVisible(true);
                    diagnosaakhir.setText("");
                    LblStts.setText("Pulang/Check Out");
                    i = 1;
                    btnReg.setEnabled(false);
                    btnKamar.setEnabled(false);
                    CmbTahun.setSelectedItem(now.substring(0, 4));
                    CmbBln.setSelectedItem(now.substring(5, 7));
                    CmbTgl.setSelectedItem(now.substring(8, 10));
                } else {
                    norawat.setEditable(true);
                    kdkamar.setEditable(true);
                    diagnosaawal.setEditable(true);
                    diagnosaakhir.setVisible(false);
                    btnDiagnosa.setVisible(false);
                    TIn.setText("");
                    JamMasuk.setText("");
                    TOut.setText("");
                    ttlbiaya.setText("0");
                    jLabel23.setVisible(false);
                    cmbStatus.setVisible(false);
                    jLabel26.setVisible(false);
                    diagnosaakhir.setText("-");
                    LblStts.setText("Masuk/Check In");
                    btnReg.setEnabled(true);
                    btnKamar.setEnabled(true);
                    CmbTahun.setSelectedItem(now.substring(0, 4));
                    CmbBln.setSelectedItem(now.substring(5, 7));
                    CmbTgl.setSelectedItem(now.substring(8, 10));
                }
            } catch (Exception e) {
                System.out.println("Notifikasi : " + e);
            } finally {
                if (rs != null) {
                    rs.close();
                }
                if (ps != null) {
                    ps.close();
                }
            }

            if (kdkamar.isEditable() == false) {
                isKmr();
                isjml();
            }
            CmbTahunItemStateChanged(null);
        } catch (SQLException e) {
            System.out.println(e);
        }
    }

    public void isCek() {
        try {
            prop.loadFromXML(new FileInputStream("setting/database.xml"));
            namakamar = prop.getProperty("KAMARAKTIFRANAP");
        } catch (Exception ex) {
            namakamar = "";
        }

        if (!namakamar.equals("")) {
            if (var.getkode().equals("Admin Utama")) {
                BangsalCari.setText("");
                btnBangsalCari.setEnabled(true);
                BangsalCari.setEditable(true);
            } else {
                BangsalCari.setText(namakamar);
                btnBangsalCari.setEnabled(false);
                BangsalCari.setEditable(false);
            }
        } else {
            btnBangsalCari.setEnabled(true);
            BangsalCari.setEditable(true);
        }

        BtnSimpan.setEnabled(var.getkamar_inap());
        BtnSimpanpindah.setEnabled(var.getkamar_inap());
        MnRawatInap.setEnabled(var.gettindakan_ranap());
        MnRawatJalan.setEnabled(var.gettindakan_ranap());
        MnPemberianObat.setEnabled(var.getberi_obat());
        MnReturJual.setEnabled(var.getretur_dari_pembeli());
        MnInputResep.setEnabled(var.getresep_pulang());
        MnNoResep.setEnabled(var.getresep_obat());
        MnNoResep.setEnabled(var.gettombolnota_billing());
        MnDiet.setEnabled(var.getdiet_pasien());
        MnPeriksaLab.setEnabled(var.getperiksa_lab());
        MnPeriksaRadiologi.setEnabled(var.getperiksa_radiologi());
        MnOperasi.setEnabled(var.getoperasi());
        MnDeposit.setEnabled(var.getdeposit_pasien());
        MnStokObatPasien.setEnabled(var.getstok_obat_pasien());
        MnResepPulang.setEnabled(var.getresep_pulang());
        MnRujuk.setEnabled(var.getrujukan_keluar());
        MnRujukMasuk.setEnabled(var.getrujukan_masuk());
        MnHapusTagihanOperasi.setEnabled(var.getoperasi());
        MnHapusObatOperasi.setEnabled(var.getoperasi());
        MnHapusDataSalah.setEnabled(var.getkamar_inap());
        MnPenjab.setEnabled(var.getkamar_inap());
        MnUpdateHari.setEnabled(var.getkamar_inap());
        MnDiagnosa.setEnabled(var.getdiagnosa_pasien());
        MnDPJP.setEnabled(var.getdpjp_ranap());
        ppRiwayat.setEnabled(var.getresume_pasien());
        ppCatatanPasien.setEnabled(var.getcatatan_pasien());
        ppDataHAIs.setEnabled(var.getdata_HAIs());
        MnSEP.setEnabled(var.getbpjs_sep());
        MnSelisihTarif.setEnabled(var.getselisih_tarif_bpjs());
        MnDataMati.setEnabled(var.getedit_data_kematian());
        MnSJP.setEnabled(var.getinhealth_sjp());
        MnSEPJamkesda.setEnabled(var.getbridging_jamkesda());
        BtnIn.setEnabled(var.getmasuk_pindah_pulang_inap());
        BtnOut.setEnabled(var.getmasuk_pindah_pulang_inap());
        btnPindah.setEnabled(var.getmasuk_pindah_pulang_inap());
        BtnIn.setEnabled(var.getmasuk_pindah_inap());
        btnPindah.setEnabled(var.getmasuk_pindah_inap());
        MnStatusGizi.setEnabled(var.getstatusgizi());
        MnStatusGZ.setEnabled(var.getstatusgizi());
        MnPencapaianAsuhanGZ.setEnabled(var.getstatusgizi());
        MnGiziBuruk.setEnabled(var.getgizi_buruk());
        MnGiziBurukPx.setEnabled(var.getgizi_buruk());
        MnStatusPulang.setEnabled(var.getstatus_pulang_inap());
        ppDataPersalinan.setEnabled(var.getdata_persalinan());
        ppDataPonek.setEnabled(var.getdata_ponek());
        MnRingkasanPulang.setEnabled(var.getringkasanpulangranap());
        ppPasienCorona.setEnabled(var.getpasien_corona());
        ppPerawatanCorona.setEnabled(var.getpasien_corona());
        MnOrderLab.setEnabled(var.getpermintaan_lab());
        MnOrderRad.setEnabled(var.getpermintaan_radiologi());
        MnAssesmenAsuhanGizi.setEnabled(var.getassesmen_gizi_harian());
        ppSuratKontrol.setEnabled(var.getbpjs_surat_kontrol());
        MnMonevAsuhanGizi.setEnabled(var.getmonev_asuhan_gizi());
    }

    private void updateHari() {
        if ((R1.isSelected() == true) && (var.getstatus() == false)) {
            for (i = 0; i < tbKamIn.getRowCount(); i++) {
                if (tbKamIn.getValueAt(i, 14).toString().equals("")) {
                    if (hariawal.equals("Yes")) {
                        Sequel.mengedit(" kamar_inap ", " no_rawat='" + tbKamIn.getValueAt(i, 0).toString() + "' and "
                                + " kd_kamar='" + tbKamIn.getValueAt(i, 7).toString() + "' "
                                + " and tgl_masuk='" + tbKamIn.getValueAt(i, 12).toString() + "' and jam_masuk='" + tbKamIn.getValueAt(i, 13).toString() + "'",
                                " lama=if(to_days(NOW())-to_days(concat(tgl_masuk,' ',jam_masuk))=0,if(time_to_sec(NOW())-time_to_sec(concat(tgl_masuk,' ',jam_masuk))>(3600*" + lama + "),1,0),to_days(NOW())-to_days(concat(tgl_masuk,' ',jam_masuk)))+1,"
                                + " ttl_biaya=(if(to_days(NOW())-to_days(concat(tgl_masuk,' ',jam_masuk))=0,if(time_to_sec(NOW())-time_to_sec(concat(tgl_masuk,' ',jam_masuk))>(3600*" + lama + "),1,0),to_days(NOW())-to_days(concat(tgl_masuk,' ',jam_masuk)))+1)*trf_kamar");

                    } else {
                        Sequel.mengedit(" kamar_inap ", " no_rawat='" + tbKamIn.getValueAt(i, 0).toString() + "' and "
                                + " kd_kamar='" + tbKamIn.getValueAt(i, 7).toString() + "' "
                                + " and tgl_masuk='" + tbKamIn.getValueAt(i, 12).toString() + "' and jam_masuk='" + tbKamIn.getValueAt(i, 13).toString() + "'",
                                " lama=if(to_days(NOW())-to_days(concat(tgl_masuk,' ',jam_masuk))=0,if(time_to_sec(NOW())-time_to_sec(concat(tgl_masuk,' ',jam_masuk))>(3600*" + lama + "),1,0),to_days(NOW())-to_days(concat(tgl_masuk,' ',jam_masuk))),"
                                + " ttl_biaya=if(to_days(NOW())-to_days(concat(tgl_masuk,' ',jam_masuk))=0,if(time_to_sec(NOW())-time_to_sec(concat(tgl_masuk,' ',jam_masuk))>(3600*" + lama + "),1,0),to_days(NOW())-to_days(concat(tgl_masuk,' ',jam_masuk)))*trf_kamar");
                    }
                }
            }
        }
    }

    public void setCariKosong() {
        TCari.setText("");
    }

    public void ctkSuratMati() {
        Map<String, Object> param = new HashMap<>();
        param.put("namars", var.getnamars());
        param.put("alamatrs", var.getalamatrs());
        param.put("kotars", var.getkabupatenrs());
        param.put("propinsirs", var.getpropinsirs());
        param.put("kontakrs", var.getkontakrs());
        param.put("emailrs", var.getemailrs());
        param.put("logo", Sequel.cariGambar("select logo from setting"));
        param.put("tgl_surat", Sequel.cariIsi("select day(now())") + " " + Sequel.bulanINDONESIA("select month(now())") + " " + Sequel.cariIsi("select year(now())"));
        param.put("tgl_lahir", Sequel.cariIsi("select tmp_lahir from pasien where no_rkm_medis='" + TNoRM.getText() + "'") + ", "
                + Sequel.cariIsi("select date_format(tgl_lahir,'%d') from pasien where no_rkm_medis='" + TNoRM.getText() + "'") + " "
                + Sequel.bulanINDONESIA("select month(tgl_lahir) from pasien where no_rkm_medis='" + TNoRM.getText() + "'") + " "
                + Sequel.cariIsi("select year(tgl_lahir) from pasien where no_rkm_medis='" + TNoRM.getText() + "'"));
        Valid.MyReport("rptSuratKematian.jrxml", "report", "::[ Surat Keterangan Kematian ]::",
                " SELECT pasien_mati.tanggal, pasien_mati.jam, pasien_mati.no_rkm_medis, "
                + " pasien.nm_pasien, TIMESTAMPDIFF(Year,pasien.tgl_lahir,CURDATE()) as umur_thn, "
                + " TIMESTAMPDIFF(MONTH,pasien.tgl_lahir,CURDATE()) % 12 as umur_bln, "
                + " FLOOR( TIMESTAMPDIFF( DAY, pasien.tgl_lahir, CURDATE() ) % 30.4375 ) as umur_hari, "
                + " pasien.alamat, pasien.no_ktp, "
                + " pasien.no_tlp, if(jk = 'L','Laki-laki','Perempuan') AS Kelamin, "
                + " pasien.gol_darah, pasien.stts_nikah, pasien.agama, pasien_mati.keterangan, "
                + " kelurahan.nm_kel, kecamatan.nm_kec, kabupaten.nm_kab "
                + " FROM pasien_mati, pasien "
                + " INNER JOIN kabupaten ON kabupaten.kd_kab = pasien.kd_kab "
                + " INNER JOIN kecamatan ON kecamatan.kd_kec = pasien.kd_kec "
                + " INNER JOIN kelurahan ON kelurahan.kd_kel = pasien.kd_kel "
                + " WHERE pasien_mati.no_rkm_medis = pasien.no_rkm_medis "
                + " and (pasien_mati.no_rkm_medis='" + TNoRM.getText() + "' or pasien_mati.no_rkm_medis='" + NoRMmati.getText() + "') ", param);
        this.setCursor(Cursor.getDefaultCursor());
    }

    public void ctkSuratMatiICU() {
        Map<String, Object> param = new HashMap<>();
        param.put("namars", var.getnamars());
        param.put("alamatrs", var.getalamatrs());
        param.put("kotars", var.getkabupatenrs());
        param.put("propinsirs", var.getpropinsirs());
        param.put("kontakrs", var.getkontakrs());
        param.put("emailrs", var.getemailrs());
        param.put("logo", Sequel.cariGambar("select logo from setting"));
        param.put("tgl_surat", Sequel.cariIsi("select day(now())") + " " + Sequel.bulanINDONESIA("select month(now())") + " " + Sequel.cariIsi("select year(now())"));
        param.put("tgl_lahir", Sequel.cariIsi("select tmp_lahir from pasien where no_rkm_medis='" + TNoRM.getText() + "'") + ", "
                + Sequel.cariIsi("select date_format(tgl_lahir,'%d') from pasien where no_rkm_medis='" + TNoRM.getText() + "'") + " "
                + Sequel.bulanINDONESIA("select month(tgl_lahir) from pasien where no_rkm_medis='" + TNoRM.getText() + "'") + " "
                + Sequel.cariIsi("select year(tgl_lahir) from pasien where no_rkm_medis='" + TNoRM.getText() + "'"));
        Valid.MyReport("rptSuratKematianManual.jrxml", "report", "::[ Surat Keterangan Kematian Ruang ICU/ICCU]::",
                " SELECT p.no_rkm_medis, p.nm_pasien, TIMESTAMPDIFF(YEAR, p.tgl_lahir, CURDATE()) AS umur_thn, "
                + "(TIMESTAMPDIFF(MONTH, p.tgl_lahir, CURDATE()) - ((TIMESTAMPDIFF(MONTH, p.tgl_lahir, CURDATE()) div 12) * 12)) AS umur_bln, "
                + "TIMESTAMPDIFF(DAY, DATE_ADD(DATE_ADD(p.tgl_lahir,INTERVAL TIMESTAMPDIFF(YEAR, p.tgl_lahir, CURDATE()) YEAR), INTERVAL TIMESTAMPDIFF(MONTH, p.tgl_lahir, CURDATE()) - ((TIMESTAMPDIFF(MONTH, p.tgl_lahir, CURDATE()) div 12) * 12) MONTH), CURDATE()) AS umur_hari, "
                + "p.alamat, p.no_ktp, p.no_tlp, IF (jk = 'L','Laki-laki','Perempuan') AS Kelamin, p.gol_darah, p.stts_nikah, p.agama, kl.nm_kel, "
                + "kc.nm_kec, kb.nm_kab FROM pasien p INNER JOIN kabupaten kb ON kb.kd_kab = p.kd_kab INNER JOIN kecamatan kc ON kc.kd_kec = p.kd_kec "
                + "INNER JOIN kelurahan kl ON kl.kd_kel = p.kd_kel WHERE p.no_rkm_medis = '" + TNoRM.getText() + "'", param);
        this.setCursor(Cursor.getDefaultCursor());
    }

    public void ctkFormulirMati() {
        Map<String, Object> param = new HashMap<>();
        param.put("namars", var.getnamars());
        param.put("alamatrs", var.getalamatrs());
        param.put("kotars", var.getkabupatenrs());
        param.put("propinsirs", var.getpropinsirs());
        param.put("kontakrs", var.getkontakrs());
        param.put("emailrs", var.getemailrs());
        param.put("logo", Sequel.cariGambar("select logo from setting"));
        param.put("tgl_surat", Sequel.cariIsi("select day(now())") + " " + Sequel.bulanINDONESIA("select month(now())") + " " + Sequel.cariIsi("select year(now())"));
        Valid.MyReport("rptFormulirKematianRM.jrxml", "report", "::[ Surat Kematian Untuk KELUARGA PASIEN ]::",
                " SELECT pasien_mati.tanggal, pasien_mati.jam, pasien_mati.no_rkm_medis, "
                + " pasien.nm_pasien, TIMESTAMPDIFF(Year,pasien.tgl_lahir,CURDATE()) as umur_thn, "
                + " TIMESTAMPDIFF(MONTH,pasien.tgl_lahir,CURDATE()) % 12 as umur_bln, "
                + " FLOOR( TIMESTAMPDIFF( DAY, pasien.tgl_lahir, CURDATE() ) % 30.4375 ) as umur_hari, "
                + " pasien.alamat, pasien.no_ktp, pasien.no_tlp, "
                + " if(jk = 'L','Laki-laki','Perempuan') AS Kelamin, pasien.tmp_lahir, "
                + " DATE_FORMAT(pasien.tgl_lahir,'%d') AS tgl_lahir, "
                + " DATE_FORMAT(pasien.tgl_lahir,'%m') AS bln_lahir, "
                + " DATE_FORMAT(pasien.tgl_lahir,'%Y') AS thn_lahir, "
                + " pasien.gol_darah, pasien.stts_nikah, pasien.agama, pasien_mati.keterangan, "
                + " kelurahan.nm_kel, kecamatan.nm_kec, kabupaten.nm_kab "
                + " FROM pasien_mati, pasien "
                + " INNER JOIN kabupaten ON kabupaten.kd_kab = pasien.kd_kab "
                + " INNER JOIN kecamatan ON kecamatan.kd_kec = pasien.kd_kec "
                + " INNER JOIN kelurahan ON kelurahan.kd_kel = pasien.kd_kel "
                + " WHERE pasien_mati.no_rkm_medis = pasien.no_rkm_medis "
                + " and (pasien_mati.no_rkm_medis='" + TNoRM.getText() + "' or pasien_mati.no_rkm_medis='" + NoRMmati.getText() + "') ", param);
        this.setCursor(Cursor.getDefaultCursor());
    }

    public void cekKetMati() {
        jLabel38.setVisible(false);
        jLabel41.setVisible(false);
        jLabel40.setVisible(false);
        ket.setVisible(false);
        TglMati.setVisible(false);
        cmbJam1.setVisible(false);
        cmbMnt1.setVisible(false);
        cmbDtk1.setVisible(false);
    }

    public void bukaMenuMati() {
        if (tabMode.getRowCount() == 0) {
            JOptionPane.showMessageDialog(null, "Maaf, tabel masih kosong...!!!!");
            TCari.requestFocus();
        } else if (TNoRM.getText().equals("") || (TPasien.getText().trim().equals(""))) {
            DlgMati.setVisible(false);
            JOptionPane.showMessageDialog(null, "Maaf, Silahkan anda pilih dulu dengan mengklik data pada tabel...!!!");
            TCari.requestFocus();
        } else if (!TNoRM.getText().equals("")) {
            DlgMati.setSize(650, 125);
            DlgMati.setLocationRelativeTo(internalFrame1);
            DlgMati.setVisible(true);

            ket1.requestFocus();
            ket1.setText(Sequel.cariIsi("select keterangan from pasien_mati where no_rkm_medis='" + TNoRM.getText() + "' "));
            Valid.SetTgl(TglMati1, Sequel.cariIsi("select tanggal from pasien_mati where no_rkm_medis='" + TNoRM.getText() + "'"));
            cmbJam2.setSelectedItem(Sequel.cariIsi("select jam from pasien_mati where no_rkm_medis='" + TNoRM.getText() + "' ").toString().substring(0, 2));
            cmbMnt2.setSelectedItem(Sequel.cariIsi("select jam from pasien_mati where no_rkm_medis='" + TNoRM.getText() + "' ").toString().substring(3, 5));
            cmbDtk2.setSelectedItem(Sequel.cariIsi("select jam from pasien_mati where no_rkm_medis='" + TNoRM.getText() + "' ").toString().substring(6, 8));
        }
    }

    public void userLap() {
        userBerizin.setText("");
        kdAkses.setText("");
        ruangDicetak.setText("");
    }

    public void UserValid() {
        userBerizin.setText(Sequel.cariIsi("SELECT nip FROM hak_akses_unit WHERE nip='" + var.getkode() + "' "));

        if (userBerizin.getText().equals("")) {
            //BtnCari.setEnabled(false);
            cmbRuangan.setSelectedIndex(0);
            cmbRuangan.setEnabled(false);

            cmbRuangan.setVisible(true);
            cmbRuangKhusus1.setVisible(false);
            cmbRuangKhusus2.setVisible(false);
            cmbRuangKhusus3.setVisible(false);
            cmbRuangKhusus4.setVisible(false);
            cmbRuangKhusus5.setVisible(false);
            kdAkses.setText("");

        } else if ((userBerizin.getText().equals("Admin Utama")) || (userBerizin.getText().equals("PPRM"))) {
            //BtnCari.setEnabled(true);            
            cmbRuangan.setSelectedIndex(0);
            cmbRuangan.setEnabled(true);
            cmbRuangan.setVisible(true);
            cmbRuangKhusus1.setVisible(false);
            cmbRuangKhusus2.setVisible(false);
            cmbRuangKhusus3.setVisible(false);
            cmbRuangKhusus4.setVisible(false);
            cmbRuangKhusus5.setVisible(false);
            tampil();

        } else if ((!userBerizin.getText().equals(""))
                || ((!userBerizin.getText().equals("Admin Utama"))
                || (!userBerizin.getText().equals("PPRM")))) {

            if (userBerizin.getText().equals("PR04")) {
                //BtnCari.setEnabled(true);
                cmbRuangKhusus1.setVisible(true);
                cmbRuangKhusus1.setSelectedIndex(0);
                cmbRuangan.setVisible(false);
                cmbRuangKhusus2.setVisible(false);
                cmbRuangKhusus3.setVisible(false);
                cmbRuangKhusus4.setVisible(false);
                cmbRuangKhusus5.setVisible(false);
                kdAkses.setText("");

            } else if (userBerizin.getText().equals("PR06")) {
                //BtnCari.setEnabled(true);
                cmbRuangKhusus2.setVisible(true);
                cmbRuangKhusus2.setSelectedIndex(0);
                cmbRuangan.setVisible(false);
                cmbRuangKhusus1.setVisible(false);
                cmbRuangKhusus3.setVisible(false);
                cmbRuangKhusus4.setVisible(false);
                cmbRuangKhusus5.setVisible(false);
                kdAkses.setText("");

            } else if (userBerizin.getText().equals("PR05")) {
                //BtnCari.setEnabled(true);
                cmbRuangKhusus5.setVisible(true);
                cmbRuangKhusus5.setSelectedIndex(0);
                cmbRuangan.setVisible(false);
                cmbRuangKhusus1.setVisible(false);
                cmbRuangKhusus2.setVisible(false);
                cmbRuangKhusus3.setVisible(false);
                cmbRuangKhusus4.setVisible(false);
                kdAkses.setText("");

            } else if (userBerizin.getText().equals("PR10")) {
                //BtnCari.setEnabled(true);
                cmbRuangKhusus3.setVisible(true);
                cmbRuangKhusus3.setSelectedIndex(0);
                cmbRuangan.setVisible(false);
                cmbRuangKhusus1.setVisible(false);
                cmbRuangKhusus2.setVisible(false);
                cmbRuangKhusus4.setVisible(false);
                cmbRuangKhusus5.setVisible(false);
                kdAkses.setText("");

            } else if (userBerizin.getText().equals("PR07")) {
                //BtnCari.setEnabled(true);
                cmbRuangKhusus4.setVisible(true);
                cmbRuangKhusus4.setSelectedIndex(0);
                cmbRuangan.setVisible(false);
                cmbRuangKhusus1.setVisible(false);
                cmbRuangKhusus2.setVisible(false);
                cmbRuangKhusus3.setVisible(false);
                cmbRuangKhusus5.setVisible(false);
                kdAkses.setText("");

            } else {
                //BtnCari.setEnabled(true);
                cmbRuangan.setEnabled(false);
                cmbRuangan.setVisible(true);
                cmbRuangKhusus1.setVisible(false);
                cmbRuangKhusus2.setVisible(false);
                cmbRuangKhusus3.setVisible(false);
                cmbRuangKhusus4.setVisible(false);
                cmbRuangKhusus5.setVisible(false);

                kdAkses.setText(Sequel.cariIsi("SELECT kode_unit FROM hak_akses_unit WHERE nip='" + var.getkode() + "' "));

                if (kdAkses.getText().equals("")) {
                    cmbRuangan.setSelectedItem("- pilih salah satu -");
                } else if (!kdAkses.getText().equals("")) {
                    cmbRuangan.setSelectedItem(Sequel.cariIsi("SELECT kode_unit FROM hak_akses_unit WHERE nip='" + var.getkode() + "' "));
                }
                tampil();
            }
        }
    }

    public void cetakRegInap() {
        if (ruangDicetak.getText().equals("AR-RAZAQ")) {
            key = "kamar_inap.tgl_masuk between '" + Valid.SetTgl(DTPCari1.getSelectedItem() + "") + "' and '" + Valid.SetTgl(DTPCari2.getSelectedItem() + "") + "' "
                    + "and bangsal.nm_bangsal like '%" + ruangDicetak.getText() + "%' ";
            key2 = "and bangsal.nm_bangsal like '%" + ruangDicetak.getText() + "%' ";
        } else if (ruangDicetak.getText().equals("ZAAL")) {
            key = "kamar_inap.tgl_masuk between '" + Valid.SetTgl(DTPCari1.getSelectedItem() + "") + "' and '" + Valid.SetTgl(DTPCari2.getSelectedItem() + "") + "' "
                    + "and bangsal.nm_bangsal like '%" + ruangDicetak.getText() + "%' ";
            key2 = "and bangsal.nm_bangsal like '%" + ruangDicetak.getText() + "%' ";
        } else {
            key = "kamar_inap.tgl_masuk between '" + Valid.SetTgl(DTPCari1.getSelectedItem() + "") + "' and '" + Valid.SetTgl(DTPCari2.getSelectedItem() + "") + "' "
                    + "and bangsal.nm_bangsal like '%" + ruangDicetak.getText() + "%' ";
            key2 = "and bangsal.nm_bangsal like '%" + ruangDicetak.getText() + "%' ";
        }

        this.setCursor(Cursor.getPredefinedCursor(Cursor.WAIT_CURSOR));
        Map<String, Object> param = new HashMap<>();
        param.put("registerBLN", Sequel.bulanINDONESIA("select month(kamar_inap.tgl_masuk) from kamar_inap inner join kamar on kamar.kd_kamar=kamar_inap.kd_kamar "
                + "inner join bangsal on bangsal.kd_bangsal=kamar.kd_bangsal where " + key + "") + "    Tahun : " + Sequel.cariIsi("select year(kamar_inap.tgl_masuk) from kamar_inap "
                        + "inner join kamar on kamar.kd_kamar=kamar_inap.kd_kamar inner join bangsal on bangsal.kd_bangsal=kamar.kd_bangsal where " + key + ""));
        if (cmbRuangKhusus1.getSelectedItem().equals("AS-SAMI/UMUM") || (cmbRuangan.getSelectedItem().equals("AS-SAMI/UMUM"))) {
            param.put("instalasi_ruang", "AS-SAMI/UMUM");
        }
        if (cmbRuangKhusus2.getSelectedItem().equals("RKPD") || (cmbRuangan.getSelectedItem().equals("RKPD"))) {
            param.put("instalasi_ruang", "RKPD");
        } else {
            param.put("instalasi_ruang", ruangDicetak.getText());
        }

        Valid.MyReport("rptRegisterPasienInap.jrxml", "report", "::[ Register Pasien Rawat Inap ]::", " "
                + " SELECT (SELECT COUNT(kd_kamar) FROM bangsal INNER JOIN kamar ON kamar.kd_bangsal = bangsal.kd_bangsal "
                + " WHERE kamar. STATUS <> 'RUSAK' AND kamar.kd_bangsal NOT LIKE '%BYSH%' " + key2 + ") AS Jumlah_bed, "
                + " PL.nm_poli AS msk_melalui, kamar_inap.no_rawat, reg_periksa.no_rkm_medis, pasien.nm_pasien, pasien.jk, "
                + " concat(reg_periksa.umurdaftar,' ',reg_periksa.sttsumur,'.') AS umur, "
                + " concat(pasien.alamat,', ',kelurahan.nm_kel,', ',kecamatan.nm_kec,', ',kabupaten.nm_kab) as alamat, "
                + " reg_periksa.p_jawab, reg_periksa.hubunganpj, penjab.png_jawab, concat(kamar_inap.kd_kamar,' ',bangsal.nm_bangsal) as ruang_inap, IFNULL(bangsal2.nm_bangsal,'-') pindahan_dari, "
                + " kamar_inap.trf_kamar, kamar_inap.diagnosa_awal, kamar_inap.diagnosa_akhir, kamar_inap.tgl_masuk,kamar_inap.jam_masuk, "
                + " IF (kamar_inap.tgl_keluar = '0000-00-00','',DATE_FORMAT(kamar_inap.tgl_keluar,'%d/%m/%Y')) as tgl_keluar, "
                + " IF (kamar_inap.jam_keluar = '00:00:00','',kamar_inap.jam_keluar) as jam_keluar, "
                + " kamar_inap.ttl_biaya,kamar_inap.stts_pulang,kamar_inap.lama,ifnull(dokter.nm_dokter,'-') as dokter_dpjp,kamar_inap.kd_kamar,reg_periksa.kd_pj "
                + " FROM kamar_inap INNER JOIN reg_periksa ON kamar_inap.no_rawat = reg_periksa.no_rawat "
                + " INNER JOIN pasien on reg_periksa.no_rkm_medis = pasien.no_rkm_medis "
                + " INNER JOIN kamar on kamar_inap.kd_kamar = kamar.kd_kamar INNER JOIN bangsal on kamar.kd_bangsal = bangsal.kd_bangsal "
                + " INNER JOIN kelurahan on pasien.kd_kel = kelurahan.kd_kel INNER JOIN kecamatan on pasien.kd_kec = kecamatan.kd_kec "
                + " INNER JOIN kabupaten on pasien.kd_kab = kabupaten.kd_kab INNER JOIN penjab on reg_periksa.kd_pj = penjab.kd_pj "
                + " LEFT JOIN poliklinik PL ON reg_periksa.kd_poli = PL.kd_poli left join dpjp_ranap r on r.no_rawat = reg_periksa.no_rawat "
                + " left join dokter on dokter.kd_dokter = r.kd_dokter "
                + " left join kamar_inap kamar_inap2 on kamar_inap.tgl_masuk = kamar_inap2.tgl_keluar and kamar_inap.jam_masuk = kamar_inap2.jam_keluar and kamar_inap.no_rawat=kamar_inap2.no_rawat "
                + " left join kamar kamar1 on kamar_inap.kd_kamar=kamar1.kd_kamar left join kamar kamar2 on kamar_inap2.kd_kamar=kamar2.kd_kamar "
                + " left join bangsal bangsal1 on kamar.kd_bangsal=bangsal1.kd_bangsal left join bangsal bangsal2 on kamar2.kd_bangsal=bangsal2.kd_bangsal "
                + " WHERE " + key + " ORDER BY kamar_inap.tgl_masuk, reg_periksa.no_rkm_medis", param);
        this.setCursor(Cursor.getDefaultCursor());
    }

    public void cekSEP() {
        try {
            pssep = koneksi.prepareStatement("select nomr, kode_inacbg, nama_pasien, no_kartu, no_rawat, "
                    + "klsrawat from bridging_sep where jnspelayanan='1' and no_sep='" + NoSEP.getText() + "'");

            try {
                rssep = pssep.executeQuery();
                while (rssep.next()) {
                    norm.setText(rssep.getString("nomr"));
                    kdINACBG.setText(rssep.getString("kode_inacbg"));
                    nmpasien.setText(rssep.getString("nama_pasien"));
                    nokartu.setText(rssep.getString("no_kartu"));
                    norawatSEP.setText(rssep.getString("no_rawat"));
                    hakkelas.setText(rssep.getString("klsrawat"));
                }
            } catch (Exception e) {
                System.out.println("Notifikasi : " + e);
            } finally {
                if (rssep != null) {
                    rssep.close();
                }
                if (pssep != null) {
                    pssep.close();
                }
            }
        } catch (Exception e) {
            System.out.println("Notifikasi : " + e);
        }

        Sequel.cariIsi("SELECT k.kelas FROM kamar_inap ki INNER JOIN kamar k on k.kd_kamar=ki.kd_kamar "
                + "INNER JOIN bangsal b on b.kd_bangsal=k.kd_bangsal WHERE stts_pulang not in ('-','Pindah Kamar') and ki.no_rawat=? ", naikKLS, norawatSEP.getText());

        Sequel.cariIsi("SELECT ki.lama FROM reg_periksa r INNER JOIN kamar_inap ki on ki.no_rawat=r.no_rawat INNER JOIN kamar k on k.kd_kamar=ki.kd_kamar "
                + "INNER JOIN bangsal b on b.kd_bangsal=k.kd_bangsal LEFT JOIN bridging_sep bs on bs.no_rawat=ki.no_rawat "
                + "WHERE ki.stts_pulang not in ('-','Pindah Kamar') and r.kd_pj='b01' and r.status_lanjut='Ranap' and r.stts_daftar <> 'batal' "
                + "and b.nm_bangsal like '%vip%' and bs.no_sep=? ", hasilLM, NoSEP.getText());

//        if (naikKLS.getText().equals("Rawat Khusus")) {
//            naikKLS.setText("");
//            hasilLM.setText("");
//            Sequel.cariIsi("SELECT k.kelas FROM kamar_inap ki INNER JOIN kamar k on k.kd_kamar=ki.kd_kamar "
//                    + "INNER JOIN bangsal b on b.kd_bangsal=k.kd_bangsal WHERE ki.kd_kamar not like '%IC%' and ki.no_rawat=? order by tgl_keluar desc limit 1 ", naikKLS, norawatSEP.getText());
//
//            Sequel.cariIsi("SELECT ki.lama FROM reg_periksa r INNER JOIN kamar_inap ki on ki.no_rawat=r.no_rawat INNER JOIN kamar k on k.kd_kamar=ki.kd_kamar "
//                    + "INNER JOIN bangsal b on b.kd_bangsal=k.kd_bangsal LEFT JOIN bridging_sep bs on bs.no_rawat=ki.no_rawat "
//                    + "WHERE r.kd_pj='b01' and r.status_lanjut='Ranap' and r.stts_daftar <> 'batal' "
//                    + "and b.nm_bangsal like '%vip%' and bs.no_sep=? ", hasilLM, NoSEP.getText());
//        }
        if (naikKLS.getText().equals("Intensif")) {
            naikKLS.setText("");
            hasilLM.setText("");
            Sequel.cariIsi("SELECT k.kelas FROM kamar_inap ki INNER JOIN kamar k on k.kd_kamar=ki.kd_kamar "
                    + "INNER JOIN bangsal b on b.kd_bangsal=k.kd_bangsal WHERE ki.kd_kamar not like '%IC%' and ki.no_rawat=? order by tgl_keluar desc limit 1 ", naikKLS, norawatSEP.getText());

            Sequel.cariIsi("SELECT ki.lama FROM reg_periksa r INNER JOIN kamar_inap ki on ki.no_rawat=r.no_rawat INNER JOIN kamar k on k.kd_kamar=ki.kd_kamar "
                    + "INNER JOIN bangsal b on b.kd_bangsal=k.kd_bangsal LEFT JOIN bridging_sep bs on bs.no_rawat=ki.no_rawat "
                    + "WHERE r.kd_pj='b01' and r.status_lanjut='Ranap' and r.stts_daftar <> 'batal' "
                    + "and b.nm_bangsal like '%vip%' and bs.no_sep=? ", hasilLM, NoSEP.getText());
        }

        Sequel.cariIsi("SELECT b.nm_bangsal FROM kamar_inap ki INNER JOIN kamar k on k.kd_kamar=ki.kd_kamar "
                + "INNER JOIN bangsal b on b.kd_bangsal=k.kd_bangsal WHERE stts_pulang not in ('-','Pindah Kamar') and no_rawat=? ", rginap, norawatSEP.getText());

        if (hasilLM.getText().equals("")) {
            lmrawat.setText("0");
        } else if (!hasilLM.getText().equals("")) {
            lmrawat.setText(hasilLM.getText());
        }

        if ((Double.parseDouble(lmrawat.getText())) <= 3) {
            persenSELISIH.setText(Sequel.cariIsi("select selisih_tarif_bpjs1 from set_tarif"));
        } else if ((Double.parseDouble(lmrawat.getText())) >= 4) {
            persenSELISIH.setText(Sequel.cariIsi("select selisih_tarif_bpjs2 from set_tarif"));
        }

        if (lmrawat.getText().equals("0")) {
            persenSELISIH.setText("0");
        }

        if ((hakkelas.getText().equals("1")) && (naikKLS.getText().equals("Kelas 1"))
                || (hakkelas.getText().equals("2")) && (naikKLS.getText().equals("Kelas 2"))
                || (hakkelas.getText().equals("3")) && (naikKLS.getText().equals("Kelas 3"))) {
            JOptionPane.showMessageDialog(null, "Pasien sudah sesuai hak kelasnya...!!!");
            selisihBaru();
            WindowSelisihTarif.dispose();
        } //        else if (naikKLS.getText().equals("Rawat Khusus")) {
        //            JOptionPane.showMessageDialog(null, "Ruang rawat ICU,ICCU atau NICU tidak dianggap naik kelas rawat..!!!");
        //            selisihBaru();
        //            WindowSelisihTarif.dispose();
        //        } 
        else if (naikKLS.getText().equals("Intensif")) {
            JOptionPane.showMessageDialog(null, "Ruang rawat ICU,ICCU atau NICU tidak dianggap naik kelas rawat..!!!");
            selisihBaru();
            WindowSelisihTarif.dispose();
        } else if (naikKLS.getText().equals("")) {
            JOptionPane.showMessageDialog(null, "Pasien belum pulang, harus dipulangkan dulu...!!!");
            selisihBaru();
            WindowSelisihTarif.dispose();
            R1.requestFocus();
        }
    }

    public void cekINACBG() {
        Sequel.cariIsi("SELECT DESKRIPSI FROM kode_inacbg WHERE INACBG=? ", deskripsiKD, kdINACBG.getText());
        Sequel.cariIsi("SELECT TARIFF FROM tarif_eklaim WHERE REGIONAL='reg4' and KODE_TARIFF='bp' and JENIS_PELAYANAN='1' and KELAS_RAWAT='1' and INACBG=? ", tarifkls1, kdINACBG.getText());
        Sequel.cariIsi("SELECT TARIFF FROM tarif_eklaim WHERE REGIONAL='reg4' and KODE_TARIFF='bp' and JENIS_PELAYANAN='1' and KELAS_RAWAT='2' and INACBG=? ", tarifkls2, kdINACBG.getText());
        Sequel.cariIsi("SELECT TARIFF FROM tarif_eklaim WHERE REGIONAL='reg4' and KODE_TARIFF='bp' and JENIS_PELAYANAN='1' and KELAS_RAWAT='3' and INACBG=? ", tarifkls3, kdINACBG.getText());
    }

    public void hitungSelisih() {
        DecimalFormat df4 = new DecimalFormat("####");
        double a = Double.parseDouble(tarifkls1.getText().trim());
        double b = Double.parseDouble(tarifkls2.getText().trim());
        double c = Double.parseDouble(tarifkls3.getText().trim());
        double d = Double.parseDouble(persenSELISIH.getText());

        if ((hakkelas.getText().equals("3")) && (naikKLS.getText().equals("Kelas 2"))) {
            dibayar.setText(Valid.SetAngka3(b - c));
            labelbyr.setText("Kalkulasi tarif INACBG : kls. 2 Rp. " + Valid.SetAngka3(b) + " - kls. 3 Rp. " + Valid.SetAngka3(c) + " = yang harus dibayar adalah Rp. ");

        } else if ((hakkelas.getText().equals("3")) && (naikKLS.getText().equals("Kelas 1"))) {
            dibayar.setText(Valid.SetAngka3(a - c));
            labelbyr.setText("Kalkulasi tarif INACBG : kls. 1 Rp. " + Valid.SetAngka3(a) + " - kls. 3 Rp. " + Valid.SetAngka3(c) + " = yang harus dibayar adalah Rp. ");

        } else if ((hakkelas.getText().equals("3")) && (naikKLS.getText().equals("Intensif"))) {
            dibayar.setText(Valid.SetAngka3(a - c));
            labelbyr.setText("Kalkulasi tarif INACBG : kls. 1 Rp. " + Valid.SetAngka3(a) + " - kls. 3 Rp. " + Valid.SetAngka3(c) + " = yang harus dibayar adalah Rp. ");

        } else if ((hakkelas.getText().equals("2")) && (naikKLS.getText().equals("Kelas 1"))) {
            dibayar.setText(Valid.SetAngka3(a - b));
            labelbyr.setText("Kalkulasi tarif INACBG : kls. 1 Rp. " + Valid.SetAngka3(a) + " - kls. 2 Rp. " + Valid.SetAngka3(b) + " = yang harus dibayar adalah Rp. ");

        } else if ((hakkelas.getText().equals("2")) && (naikKLS.getText().equals("Intensif"))) {
            dibayar.setText(Valid.SetAngka3(a - b));
            labelbyr.setText("Kalkulasi tarif INACBG : kls. 1 Rp. " + Valid.SetAngka3(a) + " - kls. 2 Rp. " + Valid.SetAngka3(b) + " = yang harus dibayar adalah Rp. ");

        } else if ((hakkelas.getText().equals("3")) && ((naikKLS.getText().equals("Kelas VIP")) || (naikKLS.getText().equals("Kelas VVIP")))) {
            nominal1.setText(df4.format(a - c));
            double e = Double.parseDouble(nominal1.getText());

            nominal2.setText(df4.format(a * d / 100));
            double f = Double.parseDouble(nominal2.getText());
            dibayar.setText(Valid.SetAngka3(e + f));
            labelbyr.setText("Kalkulasi tarif INACBG : kls. 1 Rp. " + Valid.SetAngka3(a) + " - kls. 3 Rp. " + Valid.SetAngka3(c) + " + (kls. 1 Rp. " + Valid.SetAngka3(a) + " x " + persenSELISIH.getText() + "%) = yang dibayar Rp. ");

        } else if ((hakkelas.getText().equals("2")) && ((naikKLS.getText().equals("Kelas VIP")) || (naikKLS.getText().equals("Kelas VVIP")))) {
            nominal1.setText(df4.format(a - b));
            double g = Double.parseDouble(nominal1.getText());

            nominal2.setText(df4.format(a * d / 100));
            double h = Double.parseDouble(nominal2.getText());
            dibayar.setText(Valid.SetAngka3(g + h));
            labelbyr.setText("Kalkulasi tarif INACBG : kls. 1 Rp. " + Valid.SetAngka3(a) + " - kls. 2 Rp. " + Valid.SetAngka3(b) + " + (kls. 1 Rp. " + Valid.SetAngka3(a) + " x " + persenSELISIH.getText() + "%) = yang dibayar Rp. ");

        } else if ((hakkelas.getText().equals("1")) && ((naikKLS.getText().equals("Kelas VIP")) || (naikKLS.getText().equals("Kelas VVIP")))) {
            dibayar.setText(Valid.SetAngka3(a * d / 100));
            labelbyr.setText("Kalkulasi tarif INACBG : kls. 1 Rp. " + Valid.SetAngka3(a) + " x " + persenSELISIH.getText() + "% = yang harus dibayar adalah Rp. ");

        } else if ((hakkelas.getText().equals("Intensif")) && ((naikKLS.getText().equals("Kelas VIP")) || (naikKLS.getText().equals("Kelas VVIP")))) {
            dibayar.setText(Valid.SetAngka3(a * d / 100));
            labelbyr.setText("Kalkulasi tarif INACBG : kls. 1 Rp. " + Valid.SetAngka3(a) + " x " + persenSELISIH.getText() + "% = yang harus dibayar adalah Rp. ");
        }
    }

    public void selisihBaru() {
        NoSEP.setText("");
        norm.setText("");
        nmpasien.setText("");
        nokartu.setText("");
        norawat.setText("");
        hakkelas.setText("");
        rginap.setText("");
        kdINACBG.setText("");
        deskripsiKD.setText("");

        tarifkls1.setText("0");
        tarifkls2.setText("0");
        tarifkls3.setText("0");
        dibayar.setText("0");
        persenSELISIH.setText("0");
        labelbyr.setText("Total bayar : Rp. ");
        nominal1.setText("0");
        nominal2.setText("0");
        hasilLM.setText("");
        lmrawat.setText("0");
        naikKLS.setText("");
    }

    public void emptGZburuk() {
        norwGB.setText("");
        normGB.setText("");
        nmpxGB.setText("");
        umurGB.setText("");
        diagawalGB.setText("");
        bbAwal.setText("");
        bbAkhir.setText("");
        pbtb.setText("");
        bbu.setText("");
        bbpb.setText("");
        pbu.setText("");
        perhitunganZatGZ.setText("");
        diagDokterGZ.setText("");
        pemberianNutrisi.setText("");
        albumin.setText("");
        hb.setText("");
        leukosit.setText("");
        plt.setText("");
        asalFaskes.setText("");
        TCari3.setText("");
    }

    public void tampilGZburuk() {
        Valid.tabelKosong(tabMode3);
        try {
            psGZburuk = koneksi.prepareStatement(" SELECT DISTINCT gb.no_rawat, p.no_rkm_medis, p.nm_pasien, CONCAT(p.alamat,', ',kl.nm_kel,', ',kc.nm_kec,', ',kb.nm_kab) alamat, "
                    + " CONCAT(rp.umurdaftar,' ',sttsumur) umur, DATE_FORMAT(gb.tgl_input,'%d/%m/%Y') tgl_input, ki.diagnosa_awal, gb.bb_awal, "
                    + " gb.bb_akhir, gb.pb_tb, gb.bb_u, gb.bb_pb, gb.pb_u, gb.penghitungan_zat_gizi, gb.diagnosa_dr_gizi, gb.pemberian_nutrisi, "
                    + " gb.data_albumin, gb.data_hb, gb.data_leukosit, gb.data_plt, gb.asal_rujukan FROM gizi_buruk gb "
                    + " INNER JOIN reg_periksa rp ON rp.no_rawat=gb.no_rawat "
                    + " INNER JOIN pasien p ON p.no_rkm_medis=rp.no_rkm_medis "
                    + " INNER JOIN kamar_inap ki ON ki.no_rawat=gb.no_rawat "
                    + " INNER JOIN kelurahan kl ON kl.kd_kel=p.kd_kel "
                    + " INNER JOIN kecamatan kc ON kc.kd_kec=p.kd_kec "
                    + " INNER JOIN kabupaten kb ON kb.kd_kab=p.kd_kab where "
                    + " gb.tgl_input BETWEEN ? AND ? and gb.no_rawat like ? or "
                    + " gb.tgl_input BETWEEN ? AND ? and p.no_rkm_medis like ? or "
                    + " gb.tgl_input BETWEEN ? AND ? and p.nm_pasien like ? or "
                    + " gb.tgl_input BETWEEN ? AND ? and p.alamat like ? or "
                    + " gb.tgl_input BETWEEN ? AND ? and rp.umurdaftar like ? or "
                    + " gb.tgl_input BETWEEN ? AND ? and ki.diagnosa_awal like ? or "
                    + " gb.tgl_input BETWEEN ? AND ? and gb.bb_awal like ? or "
                    + " gb.tgl_input BETWEEN ? AND ? and gb.bb_akhir like ? or "
                    + " gb.tgl_input BETWEEN ? AND ? and gb.pb_tb like ? or "
                    + " gb.tgl_input BETWEEN ? AND ? and gb.penghitungan_zat_gizi like ? or "
                    + " gb.tgl_input BETWEEN ? AND ? and gb.diagnosa_dr_gizi like ? or "
                    + " gb.tgl_input BETWEEN ? AND ? and gb.pemberian_nutrisi like ? or "
                    + " gb.tgl_input BETWEEN ? AND ? and gb.asal_rujukan like ? "
                    + " ORDER BY gb.tgl_input DESC");

            try {
                psGZburuk.setString(1, Valid.SetTgl(DTPCari9.getSelectedItem() + ""));
                psGZburuk.setString(2, Valid.SetTgl(DTPCari10.getSelectedItem() + ""));
                psGZburuk.setString(3, "%" + TCari3.getText().trim() + "%");
                psGZburuk.setString(4, Valid.SetTgl(DTPCari9.getSelectedItem() + ""));
                psGZburuk.setString(5, Valid.SetTgl(DTPCari10.getSelectedItem() + ""));
                psGZburuk.setString(6, "%" + TCari3.getText().trim() + "%");
                psGZburuk.setString(7, Valid.SetTgl(DTPCari9.getSelectedItem() + ""));
                psGZburuk.setString(8, Valid.SetTgl(DTPCari10.getSelectedItem() + ""));
                psGZburuk.setString(9, "%" + TCari3.getText().trim() + "%");
                psGZburuk.setString(10, Valid.SetTgl(DTPCari9.getSelectedItem() + ""));
                psGZburuk.setString(11, Valid.SetTgl(DTPCari10.getSelectedItem() + ""));
                psGZburuk.setString(12, "%" + TCari3.getText().trim() + "%");
                psGZburuk.setString(13, Valid.SetTgl(DTPCari9.getSelectedItem() + ""));
                psGZburuk.setString(14, Valid.SetTgl(DTPCari10.getSelectedItem() + ""));
                psGZburuk.setString(15, "%" + TCari3.getText().trim() + "%");
                psGZburuk.setString(16, Valid.SetTgl(DTPCari9.getSelectedItem() + ""));
                psGZburuk.setString(17, Valid.SetTgl(DTPCari10.getSelectedItem() + ""));
                psGZburuk.setString(18, "%" + TCari3.getText().trim() + "%");
                psGZburuk.setString(19, Valid.SetTgl(DTPCari9.getSelectedItem() + ""));
                psGZburuk.setString(20, Valid.SetTgl(DTPCari10.getSelectedItem() + ""));
                psGZburuk.setString(21, "%" + TCari3.getText().trim() + "%");
                psGZburuk.setString(22, Valid.SetTgl(DTPCari9.getSelectedItem() + ""));
                psGZburuk.setString(23, Valid.SetTgl(DTPCari10.getSelectedItem() + ""));
                psGZburuk.setString(24, "%" + TCari3.getText().trim() + "%");
                psGZburuk.setString(25, Valid.SetTgl(DTPCari9.getSelectedItem() + ""));
                psGZburuk.setString(26, Valid.SetTgl(DTPCari10.getSelectedItem() + ""));
                psGZburuk.setString(27, "%" + TCari3.getText().trim() + "%");
                psGZburuk.setString(28, Valid.SetTgl(DTPCari9.getSelectedItem() + ""));
                psGZburuk.setString(29, Valid.SetTgl(DTPCari10.getSelectedItem() + ""));
                psGZburuk.setString(30, "%" + TCari3.getText().trim() + "%");
                psGZburuk.setString(31, Valid.SetTgl(DTPCari9.getSelectedItem() + ""));
                psGZburuk.setString(32, Valid.SetTgl(DTPCari10.getSelectedItem() + ""));
                psGZburuk.setString(33, "%" + TCari3.getText().trim() + "%");
                psGZburuk.setString(34, Valid.SetTgl(DTPCari9.getSelectedItem() + ""));
                psGZburuk.setString(35, Valid.SetTgl(DTPCari10.getSelectedItem() + ""));
                psGZburuk.setString(36, "%" + TCari3.getText().trim() + "%");
                psGZburuk.setString(37, Valid.SetTgl(DTPCari9.getSelectedItem() + ""));
                psGZburuk.setString(38, Valid.SetTgl(DTPCari10.getSelectedItem() + ""));
                psGZburuk.setString(39, "%" + TCari3.getText().trim() + "%");
                rs6 = psGZburuk.executeQuery();
                gb = 1;
                while (rs6.next()) {
                    tabMode3.addRow(new Object[]{
                        gb + ".",
                        rs6.getString(1),
                        rs6.getString(2),
                        rs6.getString(3),
                        rs6.getString(4),
                        rs6.getString(5),
                        rs6.getString(6),
                        rs6.getString(7),
                        rs6.getString(8),
                        rs6.getString(9),
                        rs6.getString(10),
                        rs6.getString(11),
                        rs6.getString(12),
                        rs6.getString(13),
                        rs6.getString(14),
                        rs6.getString(15),
                        rs6.getString(16),
                        rs6.getString(17),
                        rs6.getString(18),
                        rs6.getString(19),
                        rs6.getString(20),
                        rs6.getString(21)
                    });
                    gb++;
                }
                this.setCursor(Cursor.getDefaultCursor());
            } catch (Exception e) {
                System.out.println("simrskhanza.DlgKamarInap.tampil() : " + e);
            } finally {
                if (rs6 != null) {
                    rs6.close();
                }
                if (psGZburuk != null) {
                    psGZburuk.close();
                }
            }
        } catch (SQLException e) {
            System.out.println("Notifikasi : " + e);
        }
    }

    private void getdataGB() {
        if (tbGiziBuruk.getSelectedRow() != -1) {
            norwGB.setText(tbGiziBuruk.getValueAt(tbGiziBuruk.getSelectedRow(), 1).toString());
            normGB.setText(tbGiziBuruk.getValueAt(tbGiziBuruk.getSelectedRow(), 2).toString());
            nmpxGB.setText(tbGiziBuruk.getValueAt(tbGiziBuruk.getSelectedRow(), 3).toString());
            umurGB.setText(tbGiziBuruk.getValueAt(tbGiziBuruk.getSelectedRow(), 5).toString());
            diagawalGB.setText(tbGiziBuruk.getValueAt(tbGiziBuruk.getSelectedRow(), 7).toString());
            bbAwal.setText(tbGiziBuruk.getValueAt(tbGiziBuruk.getSelectedRow(), 8).toString());
            bbAkhir.setText(tbGiziBuruk.getValueAt(tbGiziBuruk.getSelectedRow(), 9).toString());
            pbtb.setText(tbGiziBuruk.getValueAt(tbGiziBuruk.getSelectedRow(), 10).toString());
            bbu.setText(tbGiziBuruk.getValueAt(tbGiziBuruk.getSelectedRow(), 11).toString());
            bbpb.setText(tbGiziBuruk.getValueAt(tbGiziBuruk.getSelectedRow(), 12).toString());
            pbu.setText(tbGiziBuruk.getValueAt(tbGiziBuruk.getSelectedRow(), 13).toString());
            perhitunganZatGZ.setText(tbGiziBuruk.getValueAt(tbGiziBuruk.getSelectedRow(), 14).toString());
            diagDokterGZ.setText(tbGiziBuruk.getValueAt(tbGiziBuruk.getSelectedRow(), 15).toString());
            pemberianNutrisi.setText(tbGiziBuruk.getValueAt(tbGiziBuruk.getSelectedRow(), 16).toString());
            albumin.setText(tbGiziBuruk.getValueAt(tbGiziBuruk.getSelectedRow(), 17).toString());
            hb.setText(tbGiziBuruk.getValueAt(tbGiziBuruk.getSelectedRow(), 18).toString());
            leukosit.setText(tbGiziBuruk.getValueAt(tbGiziBuruk.getSelectedRow(), 19).toString());
            plt.setText(tbGiziBuruk.getValueAt(tbGiziBuruk.getSelectedRow(), 20).toString());
            asalFaskes.setText(tbGiziBuruk.getValueAt(tbGiziBuruk.getSelectedRow(), 21).toString());

            BtnEdit2.setEnabled(true);
            BtnHapus.setEnabled(true);
        }
    }

    private void cekdataGB() {
        Sequel.cariIsi("select bb_awal from gizi_buruk where no_rawat=? ", bbAwal, norawat.getText());
        Sequel.cariIsi("select bb_akhir from gizi_buruk where no_rawat=? ", bbAkhir, norawat.getText());
        Sequel.cariIsi("select pb_tb from gizi_buruk where no_rawat=? ", pbtb, norawat.getText());
        Sequel.cariIsi("select bb_u from gizi_buruk where no_rawat=? ", bbu, norawat.getText());
        Sequel.cariIsi("select bb_pb from gizi_buruk where no_rawat=? ", bbpb, norawat.getText());
        Sequel.cariIsi("select pb_u from gizi_buruk where no_rawat=? ", pbu, norawat.getText());
        Sequel.cariIsi("select penghitungan_zat_gizi from gizi_buruk where no_rawat=? ", perhitunganZatGZ, norawat.getText());
        Sequel.cariIsi("select diagnosa_dr_gizi from gizi_buruk where no_rawat=? ", diagDokterGZ, norawat.getText());
        Sequel.cariIsi("select pemberian_nutrisi from gizi_buruk where no_rawat=? ", pemberianNutrisi, norawat.getText());
        Sequel.cariIsi("select data_albumin from gizi_buruk where no_rawat=? ", albumin, norawat.getText());
        Sequel.cariIsi("select data_hb from gizi_buruk where no_rawat=? ", hb, norawat.getText());
        Sequel.cariIsi("select data_leukosit from gizi_buruk where no_rawat=? ", leukosit, norawat.getText());
        Sequel.cariIsi("select data_plt from gizi_buruk where no_rawat=? ", plt, norawat.getText());
        Sequel.cariIsi("select asal_rujukan from gizi_buruk where no_rawat=? ", asalFaskes, norawat.getText());
    }

    public void isForm() {
        if (ChkInput.isSelected() == true) {
            ChkInput.setVisible(false);
            PanelInput1.setPreferredSize(new Dimension(WIDTH, 280));
            FormInput.setVisible(true);
            ChkInput.setVisible(true);
        } else if (ChkInput.isSelected() == false) {
            ChkInput.setVisible(false);
            PanelInput1.setPreferredSize(new Dimension(WIDTH, 20));
            FormInput.setVisible(false);
            ChkInput.setVisible(true);
        }
    }

    private void tampilDPJP() {
        row = tbKamIn.getRowCount();
        for (i = 0; i < row; i++) {
            try {
                psdpjp = koneksi.prepareStatement("select dokter.nm_dokter from dpjp_ranap inner join dokter "
                        + "on dpjp_ranap.kd_dokter=dokter.kd_dokter where dpjp_ranap.no_rawat=?");
                dokterranap = "";
                try {
                    psdpjp.setString(1, tbKamIn.getValueAt(i, 0).toString());
                    rs = psdpjp.executeQuery();
                    while (rs.next()) {
                        dokterranap = rs.getString("nm_dokter") + ", " + dokterranap;
                    }
                } catch (Exception e) {
                    System.out.println("Notifikasi : " + e);
                } finally {
                    if (rs != null) {
                        rs.close();
                    }
                    if (psdpjp != null) {
                        psdpjp.close();
                    }
                }
                tbKamIn.setValueAt(dokterranap, i, 19);
            } catch (Exception e) {
                System.out.println("Notifikasi : " + e);
            }
        }
    }

    private void tampilAPS() {
        Valid.tabelKosong(tabMode5);
        try {
            psAPS = koneksi.prepareStatement("SELECT kd_aps, nm_alasan FROM master_aps where status='1' and nm_alasan like ? ORDER BY kd_aps");

            try {
                psAPS.setString(1, "%" + TCari4.getText().trim() + "%");
                rsAPS = psAPS.executeQuery();
                while (rsAPS.next()) {
                    tabMode5.addRow(new Object[]{
                        rsAPS.getString("kd_aps"),
                        rsAPS.getString("nm_alasan")
                    });
                }
            } catch (Exception e) {
                System.out.println("simrskhanza.DlgKamarInap.tampil() : " + e);
            } finally {
                if (rsAPS != null) {
                    rsAPS.close();
                }
                if (psAPS != null) {
                    psAPS.close();
                }
            }
        } catch (SQLException e) {
            System.out.println("Notifikasi : " + e);
        }
    }

    private void getdataAPS() {
        kdAPS = "";

        if (tbAPS.getSelectedRow() != -1) {
            kdAPS = tbAPS.getValueAt(tbAPS.getSelectedRow(), 0).toString();
            alasanAPS.setText(tbAPS.getValueAt(tbAPS.getSelectedRow(), 1).toString());
            ketAPS.requestFocus();
        }
    }

    private void PasiennyaPulang() {
        cekPXbpjs = 0;
        cekKamar = 0;
        cekPr = 0;
        cekDr = 0;
        cekDrPr = 0;
        cekOperasi = 0;
        cekTinPers = 0;
        cekOperasi2 = 0;
        dokterranap = "";

        cekKamar = Sequel.cariInteger("select count(1) tes from kamar_inap k inner join kamar r on r.kd_kamar = k.kd_kamar "
                + "inner join bangsal b on b.kd_bangsal = r.kd_bangsal where (b.nm_bangsal like '%OBG%' ) "
                + "and (b.nm_bangsal not like '%BAYI%') and k.no_rawat = '" + norawat.getText() + "'");
        cekKamar2 = Sequel.cariInteger("select count(1) tes from kamar_inap k inner join kamar r on r.kd_kamar = k.kd_kamar "
                + "inner join bangsal b on b.kd_bangsal = r.kd_bangsal where (b.nm_bangsal like '%VIP%' or b.nm_bangsal like '%ICU/ICCU/General%') "
                + "and (b.nm_bangsal not like '%BAYI%') and k.no_rawat = '" + norawat.getText() + "'");
        cekOperasi = Sequel.cariInteger("select count(1) test from operasi where no_rawat = '" + norawat.getText() + "'");
        cekPr = Sequel.cariInteger("select count(1) test from rawat_inap_pr r inner join jns_perawatan_inap j on j.kd_jenis_prw = r.kd_jenis_prw "
                + "where (j.nm_perawatan like '%partus%' or j.nm_perawatan like '%curettage%') and no_rawat = '" + norawat.getText() + "'");
        cekDr = Sequel.cariInteger("select count(1) test from rawat_inap_dr r inner join jns_perawatan_inap j on j.kd_jenis_prw = r.kd_jenis_prw "
                + "where (j.nm_perawatan like '%partus%' or j.nm_perawatan like '%curettage%') and no_rawat = '" + norawat.getText() + "'");
        cekDrPr = Sequel.cariInteger("select count(1) test from rawat_inap_drpr r inner join jns_perawatan_inap j on j.kd_jenis_prw = r.kd_jenis_prw "
                + "where (j.nm_perawatan like '%partus%' or j.nm_perawatan like '%curettage%') and no_rawat = '" + norawat.getText() + "'");
        cekTinPers = Sequel.cariInteger("select count(1) test from data_persalinan where no_rawat = '" + norawat.getText() + "'");
        cekOperasi2 = Sequel.cariInteger("select count(1) test from kamar_inap p inner join operasi o on o.no_rawat = p.no_rawat "
                + "where p.diagnosa_awal like 'g%p%a' and kd_kamar not like 'BD%' and p.no_rawat = '" + norawat.getText() + "'");
        dokterranap = Sequel.cariIsi("select no_rawat from dpjp_ranap where no_rawat='" + norawat.getText() + "'");
        cekPXbpjs = Sequel.cariInteger("select count(1) cek from bridging_sep where jnspelayanan='1' and no_rawat='" + norawat.getText() + "'");

        if (cekKamar > 0) {
            if (cekDr > 0 || cekPr > 0 || cekDrPr > 0 || cekOperasi > 0) {
                if (cekTinPers == 0) {
                    JOptionPane.showMessageDialog(null, "Data Persalinan Harus diisi terlebih dahulu!!!");
                } else {
                    if (dokterranap.equals("")) {
                        JOptionPane.showMessageDialog(null, "DPJP pasien ini harus diisi dulu sebelum pasien dipulangkan,.. !!!");
                    } else {
                        norawat.setEditable(false);
                        kdkamar.setEditable(false);
                        i = 1;
                        isKmr();
                        diagnosaawal.setEditable(false);
                        diagnosaakhir.setVisible(true);
                        btnDiagnosa.setVisible(true);
                        jLabel23.setVisible(true);
                        diagnosaakhir.setText("");
                        cmbStatus.setVisible(true);
                        cmbStatus.setSelectedItem(0);
                        jLabel26.setVisible(true);
                        lama = Sequel.cariIsiAngka("select lamajam from set_jam_minimal");
                        hariawal = Sequel.cariIsi("select hariawal from set_jam_minimal");
                        LblStts.setText("Pulang/Check Out");

                        btnReg.setEnabled(false);
                        btnKamar.setEnabled(false);
                        date = new Date();
                        now = dateFormat.format(date);
                        CmbTahun.setSelectedItem(now.substring(0, 4));
                        CmbBln.setSelectedItem(now.substring(5, 7));
                        CmbTgl.setSelectedItem(now.substring(8, 10));
                        cmbJam.setSelectedItem(now.substring(11, 13));
                        cmbMnt.setSelectedItem(now.substring(14, 16));
                        cmbDtk.setSelectedItem(now.substring(17, 19));
                        tglmasuk = TIn.getText();
                        jammasuk = JamMasuk.getText();
                        if (hariawal.equals("Yes")) {
                            Sequel.cariIsi("select (if(to_days('" + CmbTahun.getSelectedItem() + "-" + CmbBln.getSelectedItem() + "-" + CmbTgl.getSelectedItem() + " " + cmbJam.getSelectedItem() + ":" + cmbMnt.getSelectedItem() + ":" + cmbDtk.getSelectedItem() + "')-to_days('" + tglmasuk + " " + jammasuk + "')=0,if(time_to_sec('" + CmbTahun.getSelectedItem() + "-" + CmbBln.getSelectedItem() + "-" + CmbTgl.getSelectedItem() + " " + cmbJam.getSelectedItem() + ":" + cmbMnt.getSelectedItem() + ":" + cmbDtk.getSelectedItem() + "')-time_to_sec('" + tglmasuk + " " + jammasuk + "')>(3600*" + lama + "),1,0),to_days('" + CmbTahun.getSelectedItem() + "-" + CmbBln.getSelectedItem() + "-" + CmbTgl.getSelectedItem() + " " + cmbJam.getSelectedItem() + ":" + cmbMnt.getSelectedItem() + ":" + cmbDtk.getSelectedItem() + "')-to_days('" + tglmasuk + " " + jammasuk + "'))+1) as lama", TJmlHari);
                        } else {
                            Sequel.cariIsi("select (if(to_days('" + CmbTahun.getSelectedItem() + "-" + CmbBln.getSelectedItem() + "-" + CmbTgl.getSelectedItem() + " " + cmbJam.getSelectedItem() + ":" + cmbMnt.getSelectedItem() + ":" + cmbDtk.getSelectedItem() + "')-to_days('" + tglmasuk + " " + jammasuk + "')=0,if(time_to_sec('" + CmbTahun.getSelectedItem() + "-" + CmbBln.getSelectedItem() + "-" + CmbTgl.getSelectedItem() + " " + cmbJam.getSelectedItem() + ":" + cmbMnt.getSelectedItem() + ":" + cmbDtk.getSelectedItem() + "')-time_to_sec('" + tglmasuk + " " + jammasuk + "')>(3600*" + lama + "),1,0),to_days('" + CmbTahun.getSelectedItem() + "-" + CmbBln.getSelectedItem() + "-" + CmbTgl.getSelectedItem() + " " + cmbJam.getSelectedItem() + ":" + cmbMnt.getSelectedItem() + ":" + cmbDtk.getSelectedItem() + "')-to_days('" + tglmasuk + " " + jammasuk + "'))) as lama", TJmlHari);
                        }

                        //Sequel.cariIsi("select (if(to_days('"+CmbTahun.getSelectedItem()+"-"+CmbBln.getSelectedItem()+"-"+CmbTgl.getSelectedItem()+" "+cmbJam.getSelectedItem()+":"+cmbMnt.getSelectedItem()+":"+cmbDtk.getSelectedItem()+"')-to_days('"+tglmasuk+" "+jammasuk+"')=0,if(time_to_sec('"+CmbTahun.getSelectedItem()+"-"+CmbBln.getSelectedItem()+"-"+CmbTgl.getSelectedItem()+" "+cmbJam.getSelectedItem()+":"+cmbMnt.getSelectedItem()+":"+cmbDtk.getSelectedItem()+"')-time_to_sec('"+tglmasuk+" "+jammasuk+"')>(3600*"+lama+"),1,0),to_days('"+CmbTahun.getSelectedItem()+"-"+CmbBln.getSelectedItem()+"-"+CmbTgl.getSelectedItem()+" "+cmbJam.getSelectedItem()+":"+cmbMnt.getSelectedItem()+":"+cmbDtk.getSelectedItem()+"')-to_days('"+tglmasuk+" "+jammasuk+"'))+1) as lama",TJmlHari);             
                        norawat.requestFocus();
                        isjml();
                        WindowInputKamar.setLocationRelativeTo(internalFrame1);
                        WindowInputKamar.setVisible(true);

                        if (cmbStatus.getSelectedItem().equals("Meninggal >= 48 Jam") || (cmbStatus.getSelectedItem().equals("Meninggal < 48 Jam"))) {
                            jLabel38.setVisible(true);
                            jLabel41.setVisible(true);
                            jLabel40.setVisible(true);
                            ket.setVisible(true);
                            TglMati.setVisible(true);
                            cmbJam1.setVisible(true);
                            cmbMnt1.setVisible(true);
                            cmbDtk1.setVisible(true);
                        } else {
                            cekKetMati();
                        }
                    }
                }
            } else {
                if (dokterranap.equals("")) {
                    JOptionPane.showMessageDialog(null, "DPJP pasien ini harus diisi dulu sebelum pasien dipulangkan,.. !!!");
                } else {
                    norawat.setEditable(false);
                    kdkamar.setEditable(false);
                    i = 1;
                    isKmr();
                    diagnosaawal.setEditable(false);
                    diagnosaakhir.setVisible(true);
                    btnDiagnosa.setVisible(true);
                    jLabel23.setVisible(true);
                    diagnosaakhir.setText("");
                    cmbStatus.setVisible(true);
                    cmbStatus.setSelectedItem(0);
                    jLabel26.setVisible(true);
                    lama = Sequel.cariIsiAngka("select lamajam from set_jam_minimal");
                    hariawal = Sequel.cariIsi("select hariawal from set_jam_minimal");
                    LblStts.setText("Pulang/Check Out");

                    btnReg.setEnabled(false);
                    btnKamar.setEnabled(false);
                    date = new Date();
                    now = dateFormat.format(date);
                    CmbTahun.setSelectedItem(now.substring(0, 4));
                    CmbBln.setSelectedItem(now.substring(5, 7));
                    CmbTgl.setSelectedItem(now.substring(8, 10));
                    cmbJam.setSelectedItem(now.substring(11, 13));
                    cmbMnt.setSelectedItem(now.substring(14, 16));
                    cmbDtk.setSelectedItem(now.substring(17, 19));
                    tglmasuk = TIn.getText();
                    jammasuk = JamMasuk.getText();
                    if (hariawal.equals("Yes")) {
                        Sequel.cariIsi("select (if(to_days('" + CmbTahun.getSelectedItem() + "-" + CmbBln.getSelectedItem() + "-" + CmbTgl.getSelectedItem() + " " + cmbJam.getSelectedItem() + ":" + cmbMnt.getSelectedItem() + ":" + cmbDtk.getSelectedItem() + "')-to_days('" + tglmasuk + " " + jammasuk + "')=0,if(time_to_sec('" + CmbTahun.getSelectedItem() + "-" + CmbBln.getSelectedItem() + "-" + CmbTgl.getSelectedItem() + " " + cmbJam.getSelectedItem() + ":" + cmbMnt.getSelectedItem() + ":" + cmbDtk.getSelectedItem() + "')-time_to_sec('" + tglmasuk + " " + jammasuk + "')>(3600*" + lama + "),1,0),to_days('" + CmbTahun.getSelectedItem() + "-" + CmbBln.getSelectedItem() + "-" + CmbTgl.getSelectedItem() + " " + cmbJam.getSelectedItem() + ":" + cmbMnt.getSelectedItem() + ":" + cmbDtk.getSelectedItem() + "')-to_days('" + tglmasuk + " " + jammasuk + "'))+1) as lama", TJmlHari);
                    } else {
                        Sequel.cariIsi("select (if(to_days('" + CmbTahun.getSelectedItem() + "-" + CmbBln.getSelectedItem() + "-" + CmbTgl.getSelectedItem() + " " + cmbJam.getSelectedItem() + ":" + cmbMnt.getSelectedItem() + ":" + cmbDtk.getSelectedItem() + "')-to_days('" + tglmasuk + " " + jammasuk + "')=0,if(time_to_sec('" + CmbTahun.getSelectedItem() + "-" + CmbBln.getSelectedItem() + "-" + CmbTgl.getSelectedItem() + " " + cmbJam.getSelectedItem() + ":" + cmbMnt.getSelectedItem() + ":" + cmbDtk.getSelectedItem() + "')-time_to_sec('" + tglmasuk + " " + jammasuk + "')>(3600*" + lama + "),1,0),to_days('" + CmbTahun.getSelectedItem() + "-" + CmbBln.getSelectedItem() + "-" + CmbTgl.getSelectedItem() + " " + cmbJam.getSelectedItem() + ":" + cmbMnt.getSelectedItem() + ":" + cmbDtk.getSelectedItem() + "')-to_days('" + tglmasuk + " " + jammasuk + "'))) as lama", TJmlHari);
                    }

                    //Sequel.cariIsi("select (if(to_days('"+CmbTahun.getSelectedItem()+"-"+CmbBln.getSelectedItem()+"-"+CmbTgl.getSelectedItem()+" "+cmbJam.getSelectedItem()+":"+cmbMnt.getSelectedItem()+":"+cmbDtk.getSelectedItem()+"')-to_days('"+tglmasuk+" "+jammasuk+"')=0,if(time_to_sec('"+CmbTahun.getSelectedItem()+"-"+CmbBln.getSelectedItem()+"-"+CmbTgl.getSelectedItem()+" "+cmbJam.getSelectedItem()+":"+cmbMnt.getSelectedItem()+":"+cmbDtk.getSelectedItem()+"')-time_to_sec('"+tglmasuk+" "+jammasuk+"')>(3600*"+lama+"),1,0),to_days('"+CmbTahun.getSelectedItem()+"-"+CmbBln.getSelectedItem()+"-"+CmbTgl.getSelectedItem()+" "+cmbJam.getSelectedItem()+":"+cmbMnt.getSelectedItem()+":"+cmbDtk.getSelectedItem()+"')-to_days('"+tglmasuk+" "+jammasuk+"'))+1) as lama",TJmlHari);             
                    norawat.requestFocus();
                    isjml();
                    WindowInputKamar.setLocationRelativeTo(internalFrame1);
                    WindowInputKamar.setVisible(true);

                    if (cmbStatus.getSelectedItem().equals("Meninggal >= 48 Jam") || (cmbStatus.getSelectedItem().equals("Meninggal < 48 Jam"))) {
                        jLabel38.setVisible(true);
                        jLabel41.setVisible(true);
                        jLabel40.setVisible(true);
                        ket.setVisible(true);
                        TglMati.setVisible(true);
                        cmbJam1.setVisible(true);
                        cmbMnt1.setVisible(true);
                        cmbDtk1.setVisible(true);
                    } else {
                        cekKetMati();
                    }
                }
            }

        } else if (cekKamar2 > 0) {
            if (cekDr > 0 || cekPr > 0 || cekDrPr > 0 || cekOperasi2 > 0) {
                if (cekTinPers == 0) {
                    JOptionPane.showMessageDialog(null, "Data Persalinan Harus diisi terlebih dahulu!!!");
                } else {
                    if (dokterranap.equals("")) {
                        JOptionPane.showMessageDialog(null, "DPJP pasien ini harus diisi dulu sebelum pasien dipulangkan,.. !!!");
                    } else {
                        norawat.setEditable(false);
                        kdkamar.setEditable(false);
                        i = 1;
                        isKmr();
                        diagnosaawal.setEditable(false);
                        diagnosaakhir.setVisible(true);
                        btnDiagnosa.setVisible(true);
                        jLabel23.setVisible(true);
                        diagnosaakhir.setText("");
                        cmbStatus.setVisible(true);
                        cmbStatus.setSelectedItem(0);
                        jLabel26.setVisible(true);
                        lama = Sequel.cariIsiAngka("select lamajam from set_jam_minimal");
                        hariawal = Sequel.cariIsi("select hariawal from set_jam_minimal");
                        LblStts.setText("Pulang/Check Out");

                        btnReg.setEnabled(false);
                        btnKamar.setEnabled(false);
                        date = new Date();
                        now = dateFormat.format(date);
                        CmbTahun.setSelectedItem(now.substring(0, 4));
                        CmbBln.setSelectedItem(now.substring(5, 7));
                        CmbTgl.setSelectedItem(now.substring(8, 10));
                        cmbJam.setSelectedItem(now.substring(11, 13));
                        cmbMnt.setSelectedItem(now.substring(14, 16));
                        cmbDtk.setSelectedItem(now.substring(17, 19));
                        tglmasuk = TIn.getText();
                        jammasuk = JamMasuk.getText();
                        if (hariawal.equals("Yes")) {
                            Sequel.cariIsi("select (if(to_days('" + CmbTahun.getSelectedItem() + "-" + CmbBln.getSelectedItem() + "-" + CmbTgl.getSelectedItem() + " " + cmbJam.getSelectedItem() + ":" + cmbMnt.getSelectedItem() + ":" + cmbDtk.getSelectedItem() + "')-to_days('" + tglmasuk + " " + jammasuk + "')=0,if(time_to_sec('" + CmbTahun.getSelectedItem() + "-" + CmbBln.getSelectedItem() + "-" + CmbTgl.getSelectedItem() + " " + cmbJam.getSelectedItem() + ":" + cmbMnt.getSelectedItem() + ":" + cmbDtk.getSelectedItem() + "')-time_to_sec('" + tglmasuk + " " + jammasuk + "')>(3600*" + lama + "),1,0),to_days('" + CmbTahun.getSelectedItem() + "-" + CmbBln.getSelectedItem() + "-" + CmbTgl.getSelectedItem() + " " + cmbJam.getSelectedItem() + ":" + cmbMnt.getSelectedItem() + ":" + cmbDtk.getSelectedItem() + "')-to_days('" + tglmasuk + " " + jammasuk + "'))+1) as lama", TJmlHari);
                        } else {
                            Sequel.cariIsi("select (if(to_days('" + CmbTahun.getSelectedItem() + "-" + CmbBln.getSelectedItem() + "-" + CmbTgl.getSelectedItem() + " " + cmbJam.getSelectedItem() + ":" + cmbMnt.getSelectedItem() + ":" + cmbDtk.getSelectedItem() + "')-to_days('" + tglmasuk + " " + jammasuk + "')=0,if(time_to_sec('" + CmbTahun.getSelectedItem() + "-" + CmbBln.getSelectedItem() + "-" + CmbTgl.getSelectedItem() + " " + cmbJam.getSelectedItem() + ":" + cmbMnt.getSelectedItem() + ":" + cmbDtk.getSelectedItem() + "')-time_to_sec('" + tglmasuk + " " + jammasuk + "')>(3600*" + lama + "),1,0),to_days('" + CmbTahun.getSelectedItem() + "-" + CmbBln.getSelectedItem() + "-" + CmbTgl.getSelectedItem() + " " + cmbJam.getSelectedItem() + ":" + cmbMnt.getSelectedItem() + ":" + cmbDtk.getSelectedItem() + "')-to_days('" + tglmasuk + " " + jammasuk + "'))) as lama", TJmlHari);
                        }

                        //Sequel.cariIsi("select (if(to_days('"+CmbTahun.getSelectedItem()+"-"+CmbBln.getSelectedItem()+"-"+CmbTgl.getSelectedItem()+" "+cmbJam.getSelectedItem()+":"+cmbMnt.getSelectedItem()+":"+cmbDtk.getSelectedItem()+"')-to_days('"+tglmasuk+" "+jammasuk+"')=0,if(time_to_sec('"+CmbTahun.getSelectedItem()+"-"+CmbBln.getSelectedItem()+"-"+CmbTgl.getSelectedItem()+" "+cmbJam.getSelectedItem()+":"+cmbMnt.getSelectedItem()+":"+cmbDtk.getSelectedItem()+"')-time_to_sec('"+tglmasuk+" "+jammasuk+"')>(3600*"+lama+"),1,0),to_days('"+CmbTahun.getSelectedItem()+"-"+CmbBln.getSelectedItem()+"-"+CmbTgl.getSelectedItem()+" "+cmbJam.getSelectedItem()+":"+cmbMnt.getSelectedItem()+":"+cmbDtk.getSelectedItem()+"')-to_days('"+tglmasuk+" "+jammasuk+"'))+1) as lama",TJmlHari);             
                        norawat.requestFocus();
                        isjml();
                        WindowInputKamar.setLocationRelativeTo(internalFrame1);
                        WindowInputKamar.setVisible(true);

                        if (cmbStatus.getSelectedItem().equals("Meninggal >= 48 Jam") || (cmbStatus.getSelectedItem().equals("Meninggal < 48 Jam"))) {
                            jLabel38.setVisible(true);
                            jLabel41.setVisible(true);
                            jLabel40.setVisible(true);
                            ket.setVisible(true);
                            TglMati.setVisible(true);
                            cmbJam1.setVisible(true);
                            cmbMnt1.setVisible(true);
                            cmbDtk1.setVisible(true);
                        } else {
                            cekKetMati();
                        }
                    }
                }
            } else {
                if (dokterranap.equals("")) {
                    JOptionPane.showMessageDialog(null, "DPJP pasien ini harus diisi dulu sebelum pasien dipulangkan,.. !!!");
                } else {
                    norawat.setEditable(false);
                    kdkamar.setEditable(false);
                    i = 1;
                    isKmr();
                    diagnosaawal.setEditable(false);
                    diagnosaakhir.setVisible(true);
                    btnDiagnosa.setVisible(true);
                    jLabel23.setVisible(true);
                    diagnosaakhir.setText("");
                    cmbStatus.setVisible(true);
                    cmbStatus.setSelectedItem(0);
                    jLabel26.setVisible(true);
                    lama = Sequel.cariIsiAngka("select lamajam from set_jam_minimal");
                    hariawal = Sequel.cariIsi("select hariawal from set_jam_minimal");
                    LblStts.setText("Pulang/Check Out");

                    btnReg.setEnabled(false);
                    btnKamar.setEnabled(false);
                    date = new Date();
                    now = dateFormat.format(date);
                    CmbTahun.setSelectedItem(now.substring(0, 4));
                    CmbBln.setSelectedItem(now.substring(5, 7));
                    CmbTgl.setSelectedItem(now.substring(8, 10));
                    cmbJam.setSelectedItem(now.substring(11, 13));
                    cmbMnt.setSelectedItem(now.substring(14, 16));
                    cmbDtk.setSelectedItem(now.substring(17, 19));
                    tglmasuk = TIn.getText();
                    jammasuk = JamMasuk.getText();
                    if (hariawal.equals("Yes")) {
                        Sequel.cariIsi("select (if(to_days('" + CmbTahun.getSelectedItem() + "-" + CmbBln.getSelectedItem() + "-" + CmbTgl.getSelectedItem() + " " + cmbJam.getSelectedItem() + ":" + cmbMnt.getSelectedItem() + ":" + cmbDtk.getSelectedItem() + "')-to_days('" + tglmasuk + " " + jammasuk + "')=0,if(time_to_sec('" + CmbTahun.getSelectedItem() + "-" + CmbBln.getSelectedItem() + "-" + CmbTgl.getSelectedItem() + " " + cmbJam.getSelectedItem() + ":" + cmbMnt.getSelectedItem() + ":" + cmbDtk.getSelectedItem() + "')-time_to_sec('" + tglmasuk + " " + jammasuk + "')>(3600*" + lama + "),1,0),to_days('" + CmbTahun.getSelectedItem() + "-" + CmbBln.getSelectedItem() + "-" + CmbTgl.getSelectedItem() + " " + cmbJam.getSelectedItem() + ":" + cmbMnt.getSelectedItem() + ":" + cmbDtk.getSelectedItem() + "')-to_days('" + tglmasuk + " " + jammasuk + "'))+1) as lama", TJmlHari);
                    } else {
                        Sequel.cariIsi("select (if(to_days('" + CmbTahun.getSelectedItem() + "-" + CmbBln.getSelectedItem() + "-" + CmbTgl.getSelectedItem() + " " + cmbJam.getSelectedItem() + ":" + cmbMnt.getSelectedItem() + ":" + cmbDtk.getSelectedItem() + "')-to_days('" + tglmasuk + " " + jammasuk + "')=0,if(time_to_sec('" + CmbTahun.getSelectedItem() + "-" + CmbBln.getSelectedItem() + "-" + CmbTgl.getSelectedItem() + " " + cmbJam.getSelectedItem() + ":" + cmbMnt.getSelectedItem() + ":" + cmbDtk.getSelectedItem() + "')-time_to_sec('" + tglmasuk + " " + jammasuk + "')>(3600*" + lama + "),1,0),to_days('" + CmbTahun.getSelectedItem() + "-" + CmbBln.getSelectedItem() + "-" + CmbTgl.getSelectedItem() + " " + cmbJam.getSelectedItem() + ":" + cmbMnt.getSelectedItem() + ":" + cmbDtk.getSelectedItem() + "')-to_days('" + tglmasuk + " " + jammasuk + "'))) as lama", TJmlHari);
                    }

                    //Sequel.cariIsi("select (if(to_days('"+CmbTahun.getSelectedItem()+"-"+CmbBln.getSelectedItem()+"-"+CmbTgl.getSelectedItem()+" "+cmbJam.getSelectedItem()+":"+cmbMnt.getSelectedItem()+":"+cmbDtk.getSelectedItem()+"')-to_days('"+tglmasuk+" "+jammasuk+"')=0,if(time_to_sec('"+CmbTahun.getSelectedItem()+"-"+CmbBln.getSelectedItem()+"-"+CmbTgl.getSelectedItem()+" "+cmbJam.getSelectedItem()+":"+cmbMnt.getSelectedItem()+":"+cmbDtk.getSelectedItem()+"')-time_to_sec('"+tglmasuk+" "+jammasuk+"')>(3600*"+lama+"),1,0),to_days('"+CmbTahun.getSelectedItem()+"-"+CmbBln.getSelectedItem()+"-"+CmbTgl.getSelectedItem()+" "+cmbJam.getSelectedItem()+":"+cmbMnt.getSelectedItem()+":"+cmbDtk.getSelectedItem()+"')-to_days('"+tglmasuk+" "+jammasuk+"'))+1) as lama",TJmlHari);             
                    norawat.requestFocus();
                    isjml();
                    WindowInputKamar.setLocationRelativeTo(internalFrame1);
                    WindowInputKamar.setVisible(true);

                    if (cmbStatus.getSelectedItem().equals("Meninggal >= 48 Jam") || (cmbStatus.getSelectedItem().equals("Meninggal < 48 Jam"))) {
                        jLabel38.setVisible(true);
                        jLabel41.setVisible(true);
                        jLabel40.setVisible(true);
                        ket.setVisible(true);
                        TglMati.setVisible(true);
                        cmbJam1.setVisible(true);
                        cmbMnt1.setVisible(true);
                        cmbDtk1.setVisible(true);
                    } else {
                        cekKetMati();
                    }
                }
            }

        } else {
            if (dokterranap.equals("")) {
                JOptionPane.showMessageDialog(null, "DPJP pasien ini harus diisi dulu sebelum pasien dipulangkan,.. !!!");
            } else {
                norawat.setEditable(false);
                kdkamar.setEditable(false);
                i = 1;
                isKmr();
                diagnosaawal.setEditable(false);
                diagnosaakhir.setVisible(true);
                btnDiagnosa.setVisible(true);
                jLabel23.setVisible(true);
                diagnosaakhir.setText("");
                cmbStatus.setVisible(true);
                cmbStatus.setSelectedItem(0);
                jLabel26.setVisible(true);
                lama = Sequel.cariIsiAngka("select lamajam from set_jam_minimal");
                hariawal = Sequel.cariIsi("select hariawal from set_jam_minimal");
                LblStts.setText("Pulang/Check Out");

                btnReg.setEnabled(false);
                btnKamar.setEnabled(false);
                date = new Date();
                now = dateFormat.format(date);
                CmbTahun.setSelectedItem(now.substring(0, 4));
                CmbBln.setSelectedItem(now.substring(5, 7));
                CmbTgl.setSelectedItem(now.substring(8, 10));
                cmbJam.setSelectedItem(now.substring(11, 13));
                cmbMnt.setSelectedItem(now.substring(14, 16));
                cmbDtk.setSelectedItem(now.substring(17, 19));
                tglmasuk = TIn.getText();
                jammasuk = JamMasuk.getText();
                if (hariawal.equals("Yes")) {
                    Sequel.cariIsi("select (if(to_days('" + CmbTahun.getSelectedItem() + "-" + CmbBln.getSelectedItem() + "-" + CmbTgl.getSelectedItem() + " " + cmbJam.getSelectedItem() + ":" + cmbMnt.getSelectedItem() + ":" + cmbDtk.getSelectedItem() + "')-to_days('" + tglmasuk + " " + jammasuk + "')=0,if(time_to_sec('" + CmbTahun.getSelectedItem() + "-" + CmbBln.getSelectedItem() + "-" + CmbTgl.getSelectedItem() + " " + cmbJam.getSelectedItem() + ":" + cmbMnt.getSelectedItem() + ":" + cmbDtk.getSelectedItem() + "')-time_to_sec('" + tglmasuk + " " + jammasuk + "')>(3600*" + lama + "),1,0),to_days('" + CmbTahun.getSelectedItem() + "-" + CmbBln.getSelectedItem() + "-" + CmbTgl.getSelectedItem() + " " + cmbJam.getSelectedItem() + ":" + cmbMnt.getSelectedItem() + ":" + cmbDtk.getSelectedItem() + "')-to_days('" + tglmasuk + " " + jammasuk + "'))+1) as lama", TJmlHari);
                } else {
                    Sequel.cariIsi("select (if(to_days('" + CmbTahun.getSelectedItem() + "-" + CmbBln.getSelectedItem() + "-" + CmbTgl.getSelectedItem() + " " + cmbJam.getSelectedItem() + ":" + cmbMnt.getSelectedItem() + ":" + cmbDtk.getSelectedItem() + "')-to_days('" + tglmasuk + " " + jammasuk + "')=0,if(time_to_sec('" + CmbTahun.getSelectedItem() + "-" + CmbBln.getSelectedItem() + "-" + CmbTgl.getSelectedItem() + " " + cmbJam.getSelectedItem() + ":" + cmbMnt.getSelectedItem() + ":" + cmbDtk.getSelectedItem() + "')-time_to_sec('" + tglmasuk + " " + jammasuk + "')>(3600*" + lama + "),1,0),to_days('" + CmbTahun.getSelectedItem() + "-" + CmbBln.getSelectedItem() + "-" + CmbTgl.getSelectedItem() + " " + cmbJam.getSelectedItem() + ":" + cmbMnt.getSelectedItem() + ":" + cmbDtk.getSelectedItem() + "')-to_days('" + tglmasuk + " " + jammasuk + "'))) as lama", TJmlHari);
                }

                //Sequel.cariIsi("select (if(to_days('"+CmbTahun.getSelectedItem()+"-"+CmbBln.getSelectedItem()+"-"+CmbTgl.getSelectedItem()+" "+cmbJam.getSelectedItem()+":"+cmbMnt.getSelectedItem()+":"+cmbDtk.getSelectedItem()+"')-to_days('"+tglmasuk+" "+jammasuk+"')=0,if(time_to_sec('"+CmbTahun.getSelectedItem()+"-"+CmbBln.getSelectedItem()+"-"+CmbTgl.getSelectedItem()+" "+cmbJam.getSelectedItem()+":"+cmbMnt.getSelectedItem()+":"+cmbDtk.getSelectedItem()+"')-time_to_sec('"+tglmasuk+" "+jammasuk+"')>(3600*"+lama+"),1,0),to_days('"+CmbTahun.getSelectedItem()+"-"+CmbBln.getSelectedItem()+"-"+CmbTgl.getSelectedItem()+" "+cmbJam.getSelectedItem()+":"+cmbMnt.getSelectedItem()+":"+cmbDtk.getSelectedItem()+"')-to_days('"+tglmasuk+" "+jammasuk+"'))+1) as lama",TJmlHari);             
                norawat.requestFocus();
                isjml();
                WindowInputKamar.setLocationRelativeTo(internalFrame1);
                WindowInputKamar.setVisible(true);

                if (cmbStatus.getSelectedItem().equals("Meninggal >= 48 Jam") || (cmbStatus.getSelectedItem().equals("Meninggal < 48 Jam"))) {
                    jLabel38.setVisible(true);
                    jLabel41.setVisible(true);
                    jLabel40.setVisible(true);
                    ket.setVisible(true);
                    TglMati.setVisible(true);
                    cmbJam1.setVisible(true);
                    cmbMnt1.setVisible(true);
                    cmbDtk1.setVisible(true);
                } else {
                    cekKetMati();
                }
            }
        }
    }

    private void tampilTranFarmasi() {
        Valid.tabelKosong(tabMode6);
        try {
            psFar = koneksi.prepareStatement("SELECT d.no_rawat, d.tgl_perawatan, d.jam, db.nama_brng FROM detail_pemberian_obat d "
                    + "INNER JOIN databarang db on db.kode_brng=d.kode_brng "
                    + "WHERE d.no_rawat='" + TNoRwTerpilih.getText() + "' ORDER BY d.tgl_perawatan, d.jam");

            try {
                rsFar = psFar.executeQuery();
                while (rsFar.next()) {
                    tabMode6.addRow(new Object[]{
                        rsFar.getString("no_rawat"),
                        rsFar.getString("tgl_perawatan"),
                        rsFar.getString("jam"),
                        rsFar.getString("nama_brng")
                    });
                }
                this.setCursor(Cursor.getDefaultCursor());
            } catch (Exception e) {
                System.out.println("simrskhanza.DlgKamarInap.tampilTranFarmasi() : " + e);
            } finally {
                if (rsFar != null) {
                    rsFar.close();
                }
                if (psFar != null) {
                    psFar.close();
                }
            }
        } catch (SQLException e) {
            System.out.println("Notifikasi : " + e);
        }
        LCount1.setText("" + tabMode6.getRowCount());
    }

    private void tampilTranRadiologi() {
        Valid.tabelKosong(tabMode6);
        try {
            psRad = koneksi.prepareStatement("SELECT pr.no_rawat, pr.tgl_periksa, pr.jam, j.nm_perawatan FROM periksa_radiologi pr "
                    + "INNER JOIN jns_perawatan_radiologi j on j.kd_jenis_prw=pr.kd_jenis_prw "
                    + "WHERE pr.no_rawat='" + TNoRwTerpilih.getText() + "' ORDER BY pr.tgl_periksa, pr.jam");

            try {
                rsRad = psRad.executeQuery();
                while (rsRad.next()) {
                    tabMode6.addRow(new Object[]{
                        rsRad.getString("no_rawat"),
                        rsRad.getString("tgl_periksa"),
                        rsRad.getString("jam"),
                        rsRad.getString("nm_perawatan")
                    });
                }
                this.setCursor(Cursor.getDefaultCursor());
            } catch (Exception e) {
                System.out.println("simrskhanza.DlgKamarInap.tampilTranRadiologi() : " + e);
            } finally {
                if (rsRad != null) {
                    rsRad.close();
                }
                if (psRad != null) {
                    psRad.close();
                }
            }
        } catch (SQLException e) {
            System.out.println("Notifikasi : " + e);
        }
        LCount1.setText("" + tabMode6.getRowCount());
    }

    private void tampilTranLaboratorium() {
        Valid.tabelKosong(tabMode6);
        try {
            psLab = koneksi.prepareStatement("SELECT pl.no_rawat, pl.tgl_periksa, pl.jam, j.nm_perawatan FROM periksa_lab pl "
                    + "INNER JOIN jns_perawatan_lab j on j.kd_jenis_prw=pl.kd_jenis_prw "
                    + "WHERE pl.no_rawat='" + TNoRwTerpilih.getText() + "' ORDER BY pl.tgl_periksa, pl.jam");

            try {
                rsLab = psLab.executeQuery();
                while (rsLab.next()) {
                    tabMode6.addRow(new Object[]{
                        rsLab.getString("no_rawat"),
                        rsLab.getString("tgl_periksa"),
                        rsLab.getString("jam"),
                        rsLab.getString("nm_perawatan")
                    });
                }
                this.setCursor(Cursor.getDefaultCursor());
            } catch (Exception e) {
                System.out.println("simrskhanza.DlgKamarInap.tampilTranLaboratorium() : " + e);
            } finally {
                if (rsLab != null) {
                    rsLab.close();
                }
                if (psLab != null) {
                    psLab.close();
                }
            }
        } catch (SQLException e) {
            System.out.println("Notifikasi : " + e);
        }
        LCount1.setText("" + tabMode6.getRowCount());
    }
    
    private void tampilTranDokter() {
        Valid.tabelKosong(tabMode6);
        try {
            psDok = koneksi.prepareStatement("SELECT ri.no_rawat, ri.tgl_perawatan, ri.jam_rawat, ji.nm_perawatan FROM rawat_inap_dr ri "
                    + "INNER JOIN jns_perawatan_inap ji ON ji.kd_jenis_prw=ri.kd_jenis_prw "
                    + "WHERE ri.no_rawat='" + TNoRwTerpilih.getText() + "' ORDER BY ri.tgl_perawatan, ri.jam_rawat");

            try {
                rsDok = psDok.executeQuery();
                while (rsDok.next()) {
                    tabMode6.addRow(new Object[]{
                        rsDok.getString("no_rawat"),
                        rsDok.getString("tgl_perawatan"),
                        rsDok.getString("jam_rawat"),
                        rsDok.getString("nm_perawatan")
                    });
                }
                this.setCursor(Cursor.getDefaultCursor());
            } catch (Exception e) {
                System.out.println("simrskhanza.DlgKamarInap.tampilTranDokter() : " + e);
            } finally {
                if (rsDok != null) {
                    rsDok.close();
                }
                if (psDok != null) {
                    psDok.close();
                }
            }
        } catch (SQLException e) {
            System.out.println("Notifikasi : " + e);
        }
        LCount1.setText("" + tabMode6.getRowCount());
    }
    
    private void tampilTranPetugas() {
        Valid.tabelKosong(tabMode6);
        try {
            psPet = koneksi.prepareStatement("SELECT ri.no_rawat, ri.tgl_perawatan, ri.jam_rawat, ji.nm_perawatan FROM rawat_inap_pr ri "
                    + "INNER JOIN jns_perawatan_inap ji ON ji.kd_jenis_prw=ri.kd_jenis_prw "
                    + "WHERE ri.no_rawat='" + TNoRwTerpilih.getText() + "' ORDER BY ri.tgl_perawatan, ri.jam_rawat");

            try {
                rsPet = psPet.executeQuery();
                while (rsPet.next()) {
                    tabMode6.addRow(new Object[]{
                        rsPet.getString("no_rawat"),
                        rsPet.getString("tgl_perawatan"),
                        rsPet.getString("jam_rawat"),
                        rsPet.getString("nm_perawatan")
                    });
                }
                this.setCursor(Cursor.getDefaultCursor());
            } catch (Exception e) {
                System.out.println("simrskhanza.DlgKamarInap.tampilTranPetugas() : " + e);
            } finally {
                if (rsPet != null) {
                    rsPet.close();
                }
                if (psPet != null) {
                    psPet.close();
                }
            }
        } catch (SQLException e) {
            System.out.println("Notifikasi : " + e);
        }
        LCount1.setText("" + tabMode6.getRowCount());
    }
    
    private void tampilTranDokterPetugas() {
        Valid.tabelKosong(tabMode6);
        try {
            psDP = koneksi.prepareStatement("SELECT ri.no_rawat, ri.tgl_perawatan, ri.jam_rawat, ji.nm_perawatan FROM rawat_inap_drpr ri "
                    + "INNER JOIN jns_perawatan_inap ji ON ji.kd_jenis_prw=ri.kd_jenis_prw "
                    + "WHERE ri.no_rawat='" + TNoRwTerpilih.getText() + "' ORDER BY ri.tgl_perawatan, ri.jam_rawat");

            try {
                rsDP = psDP.executeQuery();
                while (rsDP.next()) {
                    tabMode6.addRow(new Object[]{
                        rsDP.getString("no_rawat"),
                        rsDP.getString("tgl_perawatan"),
                        rsDP.getString("jam_rawat"),
                        rsDP.getString("nm_perawatan")
                    });
                }
                this.setCursor(Cursor.getDefaultCursor());
            } catch (Exception e) {
                System.out.println("simrskhanza.DlgKamarInap.tampilTranDokterPetugas() : " + e);
            } finally {
                if (rsDP != null) {
                    rsDP.close();
                }
                if (psDP != null) {
                    psDP.close();
                }
            }
        } catch (SQLException e) {
            System.out.println("Notifikasi : " + e);
        }
        LCount1.setText("" + tabMode6.getRowCount());
    }

    private void tampilTranKosong() {
        Valid.tabelKosong(tabMode6);
        try {
            psKos = koneksi.prepareStatement("SELECT 'pilih dulu' pilih, 'salah satu' salah, 'jenis' jenis, 'transaksinya pada combobox' kombo");

            try {
                rsKos = psKos.executeQuery();
                while (rsKos.next()) {
                    tabMode6.addRow(new Object[]{
                        rsKos.getString("pilih"),
                        rsKos.getString("salah"),
                        rsKos.getString("jenis"),
                        rsKos.getString("kombo")
                    });
                }
                this.setCursor(Cursor.getDefaultCursor());
            } catch (Exception e) {
                System.out.println("simrskhanza.DlgKamarInap.tampilTranKosong() : " + e);
            } finally {
                if (rsKos != null) {
                    rsKos.close();
                }
                if (psKos != null) {
                    psKos.close();
                }
            }
        } catch (SQLException e) {
            System.out.println("Notifikasi : " + e);
        }
        LCount1.setText("" + tabMode6.getRowCount());
    }

    private void getdataTran() {
        if (tbDataTran.getSelectedRow() != -1) {
            tglDari.setText(tbDataTran.getValueAt(tbDataTran.getSelectedRow(), 1).toString());
            pukulDari.setText(tbDataTran.getValueAt(tbDataTran.getSelectedRow(), 2).toString());
            ChkTglTran.setSelected(true);
            ChkTglTran.setText("Dipilih");
        }
    }

    private void tranFarmasi() {
        if (ChkTglTran.isSelected() == true && !tglDari.getText().equals("")) {
            Sequel.mengedit("detail_pemberian_obat", "tgl_perawatan = '" + tglDari.getText() + "' and jam >='" + pukulDari.getText() + "' "
                    + "and no_rawat='" + TNoRwTerpilih.getText() + "'", "no_rawat='" + TNoRwTujuan.getText() + "'");
            Sequel.mengedit("detail_pemberian_obat", "tgl_perawatan > '" + tglDari.getText() + "' "
                    + "and no_rawat='" + TNoRwTerpilih.getText() + "'", "no_rawat='" + TNoRwTujuan.getText() + "'");

            Sequel.mengedit("resep_obat", "tgl_perawatan = '" + tglDari.getText() + "' and jam >='" + pukulDari.getText() + "' "
                    + "and no_rawat='" + TNoRwTerpilih.getText() + "'", "no_rawat='" + TNoRwTujuan.getText() + "'");
            Sequel.mengedit("resep_obat", "tgl_perawatan > '" + tglDari.getText() + "' "
                    + "and no_rawat='" + TNoRwTerpilih.getText() + "'", "no_rawat='" + TNoRwTujuan.getText() + "'");

            Sequel.mengedit("riwayat_obat_pasien", "tanggal = '" + tglDari.getText() + "' and jam >='" + pukulDari.getText() + "' "
                    + "and no_rawat='" + TNoRwTerpilih.getText() + "'", "no_rawat='" + TNoRwTujuan.getText() + "'");
            Sequel.mengedit("riwayat_obat_pasien", "tanggal > '" + tglDari.getText() + "' "
                    + "and no_rawat='" + TNoRwTerpilih.getText() + "'", "no_rawat='" + TNoRwTujuan.getText() + "'");

            WindowPindahkanTransaksi.dispose();
            JOptionPane.showMessageDialog(null, "Proses pemindahan data transaksi farmasi BERHASIL dilakukan ke no. rawat " + TNoRwTujuan.getText() + "...!!");
            tampil();
            emptTeks();
        } else if (ChkTglTran.isSelected() == false && tglDari.getText().equals("")) {
            Sequel.mengedit("detail_pemberian_obat", "no_rawat='" + TNoRwTerpilih.getText() + "'", "no_rawat='" + TNoRwTujuan.getText() + "'");
            Sequel.mengedit("resep_obat", "no_rawat='" + TNoRwTerpilih.getText() + "'", "no_rawat='" + TNoRwTujuan.getText() + "'");
            Sequel.mengedit("riwayat_obat_pasien", "no_rawat='" + TNoRwTerpilih.getText() + "'", "no_rawat='" + TNoRwTujuan.getText() + "'");
            WindowPindahkanTransaksi.dispose();
            JOptionPane.showMessageDialog(null, "Proses pemindahan data transaksi farmasi BERHASIL dilakukan ke no. rawat " + TNoRwTujuan.getText() + "...!!");
            tampil();
            emptTeks();
        } else {
            JOptionPane.showMessageDialog(null, "Menentukan tgl. transaksi & jamnya tidak Valid...!!");
            ChkTglTran.requestFocus();
        }
    }

    private void tranRadiologi() {
        if (ChkTglTran.isSelected() == true && !tglDari.getText().equals("")) {
            Sequel.mengedit("periksa_radiologi", "tgl_periksa = '" + tglDari.getText() + "' and jam >='" + pukulDari.getText() + "' "
                    + "and no_rawat='" + TNoRwTerpilih.getText() + "'", "no_rawat='" + TNoRwTujuan.getText() + "'");
            Sequel.mengedit("periksa_radiologi", "tgl_periksa > '" + tglDari.getText() + "' "
                    + "and no_rawat='" + TNoRwTerpilih.getText() + "'", "no_rawat='" + TNoRwTujuan.getText() + "'");

            Sequel.mengedit("beri_bhp_radiologi", "tgl_periksa = '" + tglDari.getText() + "' and jam >='" + pukulDari.getText() + "' "
                    + "and no_rawat='" + TNoRwTerpilih.getText() + "'", "no_rawat='" + TNoRwTujuan.getText() + "'");
            Sequel.mengedit("beri_bhp_radiologi", "tgl_periksa > '" + tglDari.getText() + "' "
                    + "and no_rawat='" + TNoRwTerpilih.getText() + "'", "no_rawat='" + TNoRwTujuan.getText() + "'");

            Sequel.mengedit("hasil_radiologi", "tgl_periksa = '" + tglDari.getText() + "' and jam >='" + pukulDari.getText() + "' "
                    + "and no_rawat='" + TNoRwTerpilih.getText() + "'", "no_rawat='" + TNoRwTujuan.getText() + "'");
            Sequel.mengedit("hasil_radiologi", "tgl_periksa > '" + tglDari.getText() + "' "
                    + "and no_rawat='" + TNoRwTerpilih.getText() + "'", "no_rawat='" + TNoRwTujuan.getText() + "'");

            WindowPindahkanTransaksi.dispose();
            JOptionPane.showMessageDialog(null, "Proses pemindahan data transaksi pemeriksaan radiologi BERHASIL dilakukan ke no. rawat " + TNoRwTujuan.getText() + "...!!");
            tampil();
            emptTeks();

        } else if (ChkTglTran.isSelected() == false && tglDari.getText().equals("")) {
            Sequel.mengedit("periksa_radiologi", "no_rawat='" + TNoRwTerpilih.getText() + "'", "no_rawat='" + TNoRwTujuan.getText() + "'");
            Sequel.mengedit("beri_bhp_radiologi", "no_rawat='" + TNoRwTerpilih.getText() + "'", "no_rawat='" + TNoRwTujuan.getText() + "'");
            Sequel.mengedit("hasil_radiologi", "no_rawat='" + TNoRwTerpilih.getText() + "'", "no_rawat='" + TNoRwTujuan.getText() + "'");
            WindowPindahkanTransaksi.dispose();
            JOptionPane.showMessageDialog(null, "Proses pemindahan data transaksi pemeriksaan radiologi BERHASIL dilakukan ke no. rawat " + TNoRwTujuan.getText() + "...!!");
            tampil();
            emptTeks();
        } else {
            JOptionPane.showMessageDialog(null, "Menentukan tgl. transaksi & jamnya tidak Valid...!!");
            ChkTglTran.requestFocus();
        }
    }

    private void tranLaboratorium() {
        if (ChkTglTran.isSelected() == true && !tglDari.getText().equals("")) {
            Sequel.mengedit("periksa_lab", "tgl_periksa = '" + tglDari.getText() + "' and jam >='" + pukulDari.getText() + "' "
                    + "and no_rawat='" + TNoRwTerpilih.getText() + "'", "no_rawat='" + TNoRwTujuan.getText() + "'");
            Sequel.mengedit("periksa_lab", "tgl_periksa > '" + tglDari.getText() + "' "
                    + "and no_rawat='" + TNoRwTerpilih.getText() + "'", "no_rawat='" + TNoRwTujuan.getText() + "'");

            Sequel.mengedit("detail_periksa_lab", "tgl_periksa = '" + tglDari.getText() + "' and jam >='" + pukulDari.getText() + "' "
                    + "and no_rawat='" + TNoRwTerpilih.getText() + "'", "no_rawat='" + TNoRwTujuan.getText() + "'");
            Sequel.mengedit("detail_periksa_lab", "tgl_periksa > '" + tglDari.getText() + "' "
                    + "and no_rawat='" + TNoRwTerpilih.getText() + "'", "no_rawat='" + TNoRwTujuan.getText() + "'");

            Sequel.mengedit("lis_reg", "tgl_periksa = '" + tglDari.getText() + "' and jam_periksa >='" + pukulDari.getText() + "' "
                    + "and no_rawat='" + TNoRwTerpilih.getText() + "'", "no_rawat='" + TNoRwTujuan.getText() + "'");
            Sequel.mengedit("lis_reg", "tgl_periksa > '" + tglDari.getText() + "' "
                    + "and no_rawat='" + TNoRwTerpilih.getText() + "'", "no_rawat='" + TNoRwTujuan.getText() + "'");

            WindowPindahkanTransaksi.dispose();
            JOptionPane.showMessageDialog(null, "Proses pemindahan data transaksi pemeriksaan laboratorium BERHASIL dilakukan ke no. rawat " + TNoRwTujuan.getText() + "...!!");
            tampil();
            emptTeks();
        } else if (ChkTglTran.isSelected() == false && tglDari.getText().equals("")) {
            Sequel.mengedit("periksa_lab", "no_rawat='" + TNoRwTerpilih.getText() + "'", "no_rawat='" + TNoRwTujuan.getText() + "'");
            Sequel.mengedit("detail_periksa_lab", "no_rawat='" + TNoRwTerpilih.getText() + "'", "no_rawat='" + TNoRwTujuan.getText() + "'");
            Sequel.mengedit("lis_reg", "no_rawat='" + TNoRwTerpilih.getText() + "'", "no_rawat='" + TNoRwTujuan.getText() + "'");
            WindowPindahkanTransaksi.dispose();
            JOptionPane.showMessageDialog(null, "Proses pemindahan data transaksi pemeriksaan laboratorium BERHASIL dilakukan ke no. rawat " + TNoRwTujuan.getText() + "...!!");
            tampil();
            emptTeks();
        } else {
            JOptionPane.showMessageDialog(null, "Menentukan tgl. transaksi & jamnya tidak Valid...!!");
            ChkTglTran.requestFocus();
        }
    }
    
    private void tranDokter() {
        if (ChkTglTran.isSelected() == true && !tglDari.getText().equals("")) {
            Sequel.mengedit("rawat_inap_dr", "tgl_perawatan = '" + tglDari.getText() + "' and jam_rawat >='" + pukulDari.getText() + "' "
                    + "and no_rawat='" + TNoRwTerpilih.getText() + "'", "no_rawat='" + TNoRwTujuan.getText() + "'");
            Sequel.mengedit("rawat_inap_dr", "tgl_perawatan > '" + tglDari.getText() + "' "
                    + "and no_rawat='" + TNoRwTerpilih.getText() + "'", "no_rawat='" + TNoRwTujuan.getText() + "'");

            WindowPindahkanTransaksi.dispose();
            JOptionPane.showMessageDialog(rootPane, "Proses pemindahan data transaksi penanganan dokter BERHASIL dilakukan ke no. rawat " + TNoRwTujuan.getText() + "...!!");
            tampil();
            emptTeks();
            
        } else if (ChkTglTran.isSelected() == false && tglDari.getText().equals("")) {
            Sequel.mengedit("rawat_inap_dr", "no_rawat='" + TNoRwTerpilih.getText() + "'", "no_rawat='" + TNoRwTujuan.getText() + "'");            
            WindowPindahkanTransaksi.dispose();
            JOptionPane.showMessageDialog(rootPane, "Proses pemindahan data transaksi penanganan dokter BERHASIL dilakukan ke no. rawat " + TNoRwTujuan.getText() + "...!!");
            tampil();
            emptTeks();
            
        } else {
            JOptionPane.showMessageDialog(rootPane, "Menentukan tgl. transaksi & jamnya tidak Valid...!!");
            ChkTglTran.requestFocus();
        }
    }
    
    private void tranPetugas() {
        if (ChkTglTran.isSelected() == true && !tglDari.getText().equals("")) {
            Sequel.mengedit("rawat_inap_pr", "tgl_perawatan = '" + tglDari.getText() + "' and jam_rawat >='" + pukulDari.getText() + "' "
                    + "and no_rawat='" + TNoRwTerpilih.getText() + "'", "no_rawat='" + TNoRwTujuan.getText() + "'");
            Sequel.mengedit("rawat_inap_pr", "tgl_perawatan > '" + tglDari.getText() + "' "
                    + "and no_rawat='" + TNoRwTerpilih.getText() + "'", "no_rawat='" + TNoRwTujuan.getText() + "'");

            WindowPindahkanTransaksi.dispose();
            JOptionPane.showMessageDialog(rootPane, "Proses pemindahan data transaksi penanganan petugas BERHASIL dilakukan ke no. rawat " + TNoRwTujuan.getText() + "...!!");
            tampil();
            emptTeks();
            
        } else if (ChkTglTran.isSelected() == false && tglDari.getText().equals("")) {
            Sequel.mengedit("rawat_inap_pr", "no_rawat='" + TNoRwTerpilih.getText() + "'", "no_rawat='" + TNoRwTujuan.getText() + "'");            
            WindowPindahkanTransaksi.dispose();
            JOptionPane.showMessageDialog(rootPane, "Proses pemindahan data transaksi penanganan petugas BERHASIL dilakukan ke no. rawat " + TNoRwTujuan.getText() + "...!!");
            tampil();
            emptTeks();
            
        } else {
            JOptionPane.showMessageDialog(rootPane, "Menentukan tgl. transaksi & jamnya tidak Valid...!!");
            ChkTglTran.requestFocus();
        }
    }
    
    private void tranDokterPetugas() {
        if (ChkTglTran.isSelected() == true && !tglDari.getText().equals("")) {
            Sequel.mengedit("rawat_inap_drpr", "tgl_perawatan = '" + tglDari.getText() + "' and jam_rawat >='" + pukulDari.getText() + "' "
                    + "and no_rawat='" + TNoRwTerpilih.getText() + "'", "no_rawat='" + TNoRwTujuan.getText() + "'");
            Sequel.mengedit("rawat_inap_drpr", "tgl_perawatan > '" + tglDari.getText() + "' "
                    + "and no_rawat='" + TNoRwTerpilih.getText() + "'", "no_rawat='" + TNoRwTujuan.getText() + "'");

            WindowPindahkanTransaksi.dispose();
            JOptionPane.showMessageDialog(rootPane, "Proses pemindahan data transaksi penanganan dokter & petugas BERHASIL dilakukan ke no. rawat " + TNoRwTujuan.getText() + "...!!");
            tampil();
            emptTeks();
            
        } else if (ChkTglTran.isSelected() == false && tglDari.getText().equals("")) {
            Sequel.mengedit("rawat_inap_drpr", "no_rawat='" + TNoRwTerpilih.getText() + "'", "no_rawat='" + TNoRwTujuan.getText() + "'");            
            WindowPindahkanTransaksi.dispose();
            JOptionPane.showMessageDialog(rootPane, "Proses pemindahan data transaksi penanganan dokter & petugas BERHASIL dilakukan ke no. rawat " + TNoRwTujuan.getText() + "...!!");
            tampil();
            emptTeks();
            
        } else {
            JOptionPane.showMessageDialog(rootPane, "Menentukan tgl. transaksi & jamnya tidak Valid...!!");
            ChkTglTran.requestFocus();
        }
    }
}
