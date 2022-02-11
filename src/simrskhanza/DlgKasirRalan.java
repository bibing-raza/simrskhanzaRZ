package simrskhanza;

import bridging.BPJSDataSEP;
import bridging.BPJSProgramPRB;
import bridging.BPJSSuratKontrol;
import bridging.CoronaPasien;
import bridging.DlgDataTB;
import bridging.DlgSKDPBPJS;
import bridging.INACBGDaftarKlaim;
import bridging.INACBGPerawatanCorona;
import inventory.DlgResepObat;
import inventory.DlgPemberianObat;
import laporan.DlgDiagnosaPenyakit;
import keuangan.DlgBilingRalan;
import fungsi.WarnaTable;
import fungsi.batasInput;
import fungsi.koneksiDB;
import fungsi.sekuel;
import fungsi.validasi;
import fungsi.var;
import inventory.DlgPenjualan;
import inventory.DlgPeresepanDokter;
import inventory.DlgReturJual;
import java.awt.Cursor;
import java.awt.Desktop;
import java.awt.Dimension;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.event.WindowEvent;
import java.awt.event.WindowListener;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileWriter;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.Properties;
import java.util.HashMap;
import java.util.Map;
import javax.swing.JButton;
import javax.swing.JOptionPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.Timer;
import javax.swing.event.DocumentEvent;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableColumn;
import javax.swing.text.Document;
import javax.swing.text.html.HTMLEditorKit;
import javax.swing.text.html.StyleSheet;
import keuangan.DlgLhtPiutang;
import keuangan.DlgRBObatPoli;
import keuangan.DlgRBJmDokter;
import keuangan.DlgRBJmParamedis;
import keuangan.DlgRBTindakanPoli;
import keuangan.DlgRHJmDokter;
import keuangan.DlgRHJmParamedis;
import laporan.DlgDataHAIs;
import permintaan.DlgCariPermintaanLab;
import permintaan.DlgCariPermintaanRadiologi;
import permintaan.DlgPermintaanLaboratorium;
import permintaan.DlgPermintaanRadiologi;
import rekammedis.RMPenilaianAwalKeperawatanRalan;
import rekammedis.RMTriaseIGD;
import rekammedis.RMPenilaianAwalMedisRalanKandungan;

/**
 *
 * @author dosen
 */
public final class DlgKasirRalan extends javax.swing.JDialog {
    private final DefaultTableModel tabModekasir, tabModeMati, tabModeKunjungan;
    private sekuel Sequel = new sekuel();
    private validasi Valid = new validasi();
    private Connection koneksi = koneksiDB.condb();
    private PreparedStatement psotomatis, psotomatis2, pskasir, pscaripiutang, psumurpasien,
            pspasienboking, pspasien, psdiagnosa, psprosedur, psdokter, psMati, psRiwKunj;
    private ResultSet rskasir, rsumurpasien, rspasienboking, rspasien, rsdiagnosa, rsprosedur, 
            rsdokter, rsMati, rsRiwKunj;
    private final Properties prop = new Properties();
    public DlgKamarInap statusgizi = new DlgKamarInap(null, false);
    public DlgPemberianDiet dietRalan = new DlgPemberianDiet(null, false);
    public RMPenilaianAwalMedisRalanKandungan bid = new RMPenilaianAwalMedisRalanKandungan(null, false);
    private Date cal = new Date();
    private DlgRujukanPoliInternal dlgrjk = new DlgRujukanPoliInternal(null, false);
    private String umur = "0", sttsumur = "Th", cekSEPboking = "", tglklaim = "", drdpjp = "", poli = "", crBayar = "", diagnosa_ok = "",
            sql = " pasien_mati.no_rkm_medis=pasien.no_rkm_medis ", cekPOLI = "", namadokter = "", nik = "",
            namapoli = "", norw_dipilih = "", kddokter_dipilih = "";
    private String bangsal = Sequel.cariIsi("select kd_bangsal from set_lokasi limit 1"), nonota = "", URUTNOREG = "",
            sqlpsotomatis2 = "insert into rawat_jl_dr values (?,?,?,?,?,?,?,?,?,?,?)",
            sqlpsotomatis2petugas = "insert into rawat_jl_pr values (?,?,?,?,?,?,?,?,?,?,?)",
            sqlpsotomatis2dokterpetugas = "insert into rawat_jl_drpr values (?,?,?,?,?,?,?,?,?,?,?,?,?)",
            sqlpsotomatisdokterpetugas
            = "select jns_perawatan.kd_jenis_prw,jns_perawatan.material,jns_perawatan.bhp,"
            + "jns_perawatan.tarif_tindakandr,jns_perawatan.tarif_tindakanpr,jns_perawatan.total_byrdrpr,"
            + "jns_perawatan.kso,jns_perawatan.menejemen from set_otomatis_tindakan_ralan_dokterpetugas "
            + "inner join jns_perawatan on set_otomatis_tindakan_ralan_dokterpetugas.kd_jenis_prw=jns_perawatan.kd_jenis_prw "
            + "where set_otomatis_tindakan_ralan_dokterpetugas.kd_dokter=? and set_otomatis_tindakan_ralan_dokterpetugas.kd_pj=?",
            sqlpsotomatispetugas
            = "select jns_perawatan.kd_jenis_prw,jns_perawatan.material,jns_perawatan.bhp,"
            + "jns_perawatan.tarif_tindakanpr,jns_perawatan.total_byrpr,jns_perawatan.kso,jns_perawatan.menejemen from set_otomatis_tindakan_ralan_petugas "
            + "inner join jns_perawatan on set_otomatis_tindakan_ralan_petugas.kd_jenis_prw=jns_perawatan.kd_jenis_prw "
            + "where set_otomatis_tindakan_ralan_petugas.kd_pj=?",
            sqlpsotomatis
            = "select jns_perawatan.kd_jenis_prw,jns_perawatan.material,jns_perawatan.bhp,"
            + "jns_perawatan.tarif_tindakandr,jns_perawatan.total_byrdr,jns_perawatan.kso,jns_perawatan.menejemen from set_otomatis_tindakan_ralan "
            + "inner join jns_perawatan on set_otomatis_tindakan_ralan.kd_jenis_prw=jns_perawatan.kd_jenis_prw "
            + "where set_otomatis_tindakan_ralan.kd_dokter=? and set_otomatis_tindakan_ralan.kd_pj=?";
    public DlgBilingRalan billing = new DlgBilingRalan(null, false);
    private int i = 0, pilihan = 0, sudah = 0, x = 0, n = 0, z = 0, cekSuratTindakan = 0, diagnosa_cek = 0, r = 0, cekPilihanRehab = 0,
            cekResep = 0, cekTotKun = 0, cekDiag = 0, cekPem = 0, cekPemPet = 0, cekRujukInternal = 0, cekSEP = 0;
    private SimpleDateFormat dateformat = new SimpleDateFormat("yyyy/MM/dd");
    public DlgKamarInap kamarinap = new DlgKamarInap(null, false);
    public DlgCariDokter2 dokter = new DlgCariDokter2(null, false);

    /**
     * Creates new form DlgReg
     *
     * @param parent
     * @param modal
     */
    public DlgKasirRalan(java.awt.Frame parent, boolean modal) {
        super(parent, modal);
        initComponents();

        this.setLocation(8, 1);
        setSize(885, 674);

        tabModekasir = new DefaultTableModel(null, new String[]{
            "No.Rawat", "Kd.Dokter", "Dokter Dituju", "Nomer RM", "Nama Pasien", "Status", "Poliklinik/Inst.", "Jenis Bayar", "Jns. Kunjungan",
            "Reg. Online", "Tanggal", "Jam", "No. Reg.", "Status Klaim", "No. Telpon/HP", "Alamat Pasien"
        }) {
            @Override
            public boolean isCellEditable(int rowIndex, int colIndex) {
                return false;
            }
        };
        
        tbKasirRalan.setModel(tabModekasir);
        tbKasirRalan.setPreferredScrollableViewportSize(new Dimension(800, 800));
        tbKasirRalan.setAutoResizeMode(JTable.AUTO_RESIZE_OFF);
        
        for (i = 0; i < 16; i++) {
            TableColumn column = tbKasirRalan.getColumnModel().getColumn(i);
            if (i == 0) {
                column.setMinWidth(0);
                column.setMaxWidth(0);
            } else if (i == 1) {
                column.setMinWidth(0);
                column.setMaxWidth(0);
            } else if (i == 2) {
                column.setPreferredWidth(220);                           
            } else if (i == 3) {
                column.setPreferredWidth(65);
            } else if (i == 4) {
                column.setPreferredWidth(250);
            } else if (i == 5) {
                column.setPreferredWidth(130);
            } else if (i == 6) {
                column.setPreferredWidth(130);  
            } else if (i == 7) {
                column.setPreferredWidth(130);
            } else if (i == 8) {
                column.setPreferredWidth(90);
            } else if (i == 9) {
                column.setPreferredWidth(90);
            } else if (i == 10) {
                column.setPreferredWidth(75);
            } else if (i == 11) {
                column.setPreferredWidth(65);
            } else if (i == 12) {
                column.setPreferredWidth(60);
            } else if (i == 13) {
                column.setPreferredWidth(85);
            } else if (i == 14) {
                column.setPreferredWidth(90);
            } else if (i == 15) {
                column.setPreferredWidth(350);
            }
        }
        tbKasirRalan.setDefaultRenderer(Object.class, new WarnaTable());

        tabModeMati = new DefaultTableModel(null, new Object[]{"Tanggal", "Jam", "No.RM.", "Nama Pasien", "J.K.", "Tmp.Lahir",
            "Tgl.Lahir", "G.D.", "Stts.Nikah", "Agama", "Keterangan", "Tempat Meninggal", "ICD-10", "Unit Asal", "tgl_lahir", "tgl_mati"}) {
            Class[] types = new Class[]{
                java.lang.Object.class, java.lang.Object.class, java.lang.Object.class, java.lang.Object.class, java.lang.Object.class,
                java.lang.Object.class, java.lang.Object.class, java.lang.Object.class, java.lang.Object.class, java.lang.Object.class,
                java.lang.Object.class, java.lang.Object.class, java.lang.Object.class, java.lang.Object.class, java.lang.Object.class,
                java.lang.Object.class
            };

            @Override
            public boolean isCellEditable(int rowIndex, int colIndex) {
                return false;
            }
        };
        tbPasienMati.setModel(tabModeMati);
        tbPasienMati.setPreferredScrollableViewportSize(new Dimension(800, 800));
        tbPasienMati.setAutoResizeMode(JTable.AUTO_RESIZE_OFF);

        for (int i = 0; i < 16; i++) {
            TableColumn column = tbPasienMati.getColumnModel().getColumn(i);
            if (i == 0) {
                column.setPreferredWidth(75);
            } else if (i == 1) {
                column.setPreferredWidth(60);
            } else if (i == 2) {
                column.setPreferredWidth(80);
            } else if (i == 3) {
                column.setPreferredWidth(150);
            } else if (i == 4) {
                column.setPreferredWidth(30);
            } else if (i == 5) {
                column.setPreferredWidth(120);
            } else if (i == 6) {
                column.setPreferredWidth(75);
            } else if (i == 7) {
                column.setPreferredWidth(30);
            } else if (i == 8) {
                column.setPreferredWidth(90);
            } else if (i == 9) {
                column.setPreferredWidth(90);
            } else if (i == 10) {
                column.setPreferredWidth(170);
            } else if (i == 11) {
                column.setPreferredWidth(120);
            } else if (i == 12) {
                column.setPreferredWidth(65);
            } else if (i == 13) {
                column.setPreferredWidth(90);
            } else if (i == 14) {
                column.setMinWidth(0);
                column.setMaxWidth(0);
            } else if (i == 15) {
                column.setMinWidth(0);
                column.setMaxWidth(0);
            }
        }
        tbPasienMati.setDefaultRenderer(Object.class, new WarnaTable());

        tabModeKunjungan = new DefaultTableModel(null, new Object[]{"No.", "No. Rawat", "No. RM.", "Tgl. Kunjungan",
            "Nama Pasien", "Dokter Pemeriksa", "Jenis Bayar", "kddokter"}) {
            Class[] types = new Class[]{
                java.lang.Object.class, java.lang.Object.class, java.lang.Object.class,
                java.lang.Object.class, java.lang.Object.class, java.lang.Object.class,
                java.lang.Object.class, java.lang.Object.class
            };

            @Override
            public boolean isCellEditable(int rowIndex, int colIndex) {
                return false;
            }
        };
        tbRiwayatKunj.setModel(tabModeKunjungan);
        tbRiwayatKunj.setPreferredScrollableViewportSize(new Dimension(800, 800));
        tbRiwayatKunj.setAutoResizeMode(JTable.AUTO_RESIZE_OFF);

        for (int i = 0; i < 8; i++) {
            TableColumn column = tbRiwayatKunj.getColumnModel().getColumn(i);
            if (i == 0) {
                column.setPreferredWidth(30);
            } else if (i == 1) {
                column.setPreferredWidth(105);
            } else if (i == 2) {
                column.setPreferredWidth(55);
            } else if (i == 3) {
                column.setPreferredWidth(80);
            } else if (i == 4) {
                column.setPreferredWidth(190);
            } else if (i == 5) {
                column.setPreferredWidth(250);
            } else if (i == 6) {
                column.setPreferredWidth(110);
            } else if (i == 7) {
                column.setMinWidth(0);
                column.setMaxWidth(0);
            }
        }
        tbRiwayatKunj.setDefaultRenderer(Object.class, new WarnaTable());

        TCari.setDocument(new batasInput((byte) 100).getKata(TCari));
        CrPoli.setDocument(new batasInput((byte) 100).getKata(CrPoli));
        CrPtg.setDocument(new batasInput((byte) 100).getKata(CrPtg));
        kdboking.setDocument(new batasInput((byte) 15).getKata(kdboking));
        if (koneksiDB.cariCepat().equals("aktif")) {
            TCari.getDocument().addDocumentListener(new javax.swing.event.DocumentListener() {
                @Override
                public void insertUpdate(DocumentEvent e) {
                    tampilkasir();
                }

                @Override
                public void removeUpdate(DocumentEvent e) {
                    tampilkasir();
                }

                @Override
                public void changedUpdate(DocumentEvent e) {
                    tampilkasir();
                }
            });
        }

        jam();

        billing.dokter.addWindowListener(new WindowListener() {
            @Override
            public void windowOpened(WindowEvent e) {
            }

            @Override
            public void windowClosing(WindowEvent e) {
            }

            @Override
            public void windowClosed(WindowEvent e) {
                if (var.getform().equals("DlgKasirRalan")) {
                    if (billing.dokter.getTable().getSelectedRow() != -1) {
                        if (pilihan == 1) {
                            kddokter.setText(billing.dokter.getTable().getValueAt(billing.dokter.getTable().getSelectedRow(), 0).toString());
                            TDokter.setText(billing.dokter.getTable().getValueAt(billing.dokter.getTable().getSelectedRow(), 1).toString());
                            kddokter.requestFocus();
                            tampilkasir();
                        } else if (pilihan == 2) {
                            CrPtg.setText(billing.dokter.getTable().getValueAt(billing.dokter.getTable().getSelectedRow(), 1).toString());
                            tampilkasir();
                            CrPtg.requestFocus();
                        }
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

        billing.poli.addWindowListener(new WindowListener() {
            @Override
            public void windowOpened(WindowEvent e) {
            }

            @Override
            public void windowClosing(WindowEvent e) {
            }

            @Override
            public void windowClosed(WindowEvent e) {
                if (var.getform().equals("DlgKasirRalan")) {
                    if (billing.poli.getTable().getSelectedRow() != -1) {
                        if (pilihan == 1) {
                            kdpoli.setText(billing.poli.getTable().getValueAt(billing.poli.getTable().getSelectedRow(), 0).toString());
                            nmpoli.setText(billing.poli.getTable().getValueAt(billing.poli.getTable().getSelectedRow(), 1).toString());
                            tampilkasir();
                        } else if (pilihan == 2) {
                            kdpoli.setText(billing.poli.getTable().getValueAt(billing.poli.getTable().getSelectedRow(), 0).toString());
                            CrPoli.setText(billing.poli.getTable().getValueAt(billing.poli.getTable().getSelectedRow(), 1).toString());
                            CrPoli.requestFocus();
                            tampilkasir();
                        }
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

        billing.addWindowListener(new WindowListener() {
            @Override
            public void windowOpened(WindowEvent e) {
            }

            @Override
            public void windowClosing(WindowEvent e) {
            }

            @Override
            public void windowClosed(WindowEvent e) {
                tampilkasir();
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

        billing.penjab.addWindowListener(new WindowListener() {
            @Override
            public void windowOpened(WindowEvent e) {
            }

            @Override
            public void windowClosing(WindowEvent e) {
            }

            @Override
            public void windowClosed(WindowEvent e) {
                if (var.getform().equals("DlgKasirRalan")) {
                    if (billing.penjab.getTable().getSelectedRow() != -1) {
                        kdpenjab.setText(billing.penjab.getTable().getValueAt(billing.penjab.getTable().getSelectedRow(), 1).toString());
                        nmpenjab.setText(billing.penjab.getTable().getValueAt(billing.penjab.getTable().getSelectedRow(), 2).toString());
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

        billing.penjab.getTable().addKeyListener(new KeyListener() {
            @Override
            public void keyTyped(KeyEvent e) {
            }

            @Override
            public void keyPressed(KeyEvent e) {
                if (var.getform().equals("DlgKasirRalan")) {
                    if (e.getKeyCode() == KeyEvent.VK_SPACE) {
                        billing.penjab.dispose();
                    }
                }
            }

            @Override
            public void keyReleased(KeyEvent e) {
            }
        });

        dokter.addWindowListener(new WindowListener() {
            @Override
            public void windowOpened(WindowEvent e) {;
            }

            @Override
            public void windowClosing(WindowEvent e) {
            }

            @Override
            public void windowClosed(WindowEvent e) {
                if (dokter.getTable().getSelectedRow() != -1) {
                    KdDokter.setText(dokter.getTable().getValueAt(dokter.getTable().getSelectedRow(), 0).toString());
                    NmDokter.setText(dokter.getTable().getValueAt(dokter.getTable().getSelectedRow(), 1).toString());
                    nomorAuto();
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

        try {
            prop.loadFromXML(new FileInputStream("setting/database.xml"));
            namadokter = prop.getProperty("DOKTERAKTIFKASIRRALAN");
            namapoli = prop.getProperty("POLIAKTIFKASIRRALAN");
        } catch (Exception ex) {
            namadokter = "";
            namapoli = "";
        }
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        buttonGroup1 = new javax.swing.ButtonGroup();
        jPopupMenu1 = new javax.swing.JPopupMenu();
        MnKamarInap = new javax.swing.JMenuItem();
        MnTindakanRalan = new javax.swing.JMenu();
        MnDataRalan = new javax.swing.JMenuItem();
        MnPeriksaLab = new javax.swing.JMenuItem();
        MnPeriksaRadiologi = new javax.swing.JMenuItem();
        MnOperasi = new javax.swing.JMenuItem();
        MnObatRalan = new javax.swing.JMenu();
        MnCetakResepDokter = new javax.swing.JMenuItem();
        MnReturJual = new javax.swing.JMenuItem();
        MnNoResep = new javax.swing.JMenuItem();
        MnResepDOkter = new javax.swing.JMenuItem();
        MnDataPemberianObat = new javax.swing.JMenuItem();
        MnGanti = new javax.swing.JMenu();
        MnRehabMedik = new javax.swing.JMenuItem();
        MnPoli = new javax.swing.JMenuItem();
        MnDokter = new javax.swing.JMenuItem();
        MnPenjab = new javax.swing.JMenuItem();
        MnPermintaan = new javax.swing.JMenu();
        MnEklaimINACBG = new javax.swing.JMenu();
        MnKlaimJKN = new javax.swing.JMenuItem();
        MnKlaimCOVID = new javax.swing.JMenuItem();
        MnKlaimKIPI = new javax.swing.JMenuItem();
        MnRencanaKontrolManual = new javax.swing.JMenuItem();
        MnSEPBPJS = new javax.swing.JMenuItem();
        MnSKDPBPJS = new javax.swing.JMenuItem();
        MnPermintaanLab = new javax.swing.JMenuItem();
        MnPermintaanRadiologi = new javax.swing.JMenuItem();
        MnCariPermintaanLab = new javax.swing.JMenuItem();
        MnCariPermintaanRad = new javax.swing.JMenuItem();
        MnRujukan = new javax.swing.JMenu();
        MnPoliInternal = new javax.swing.JMenuItem();
        MnRujuk = new javax.swing.JMenuItem();
        MnRekap = new javax.swing.JMenu();
        MnDietMakanan = new javax.swing.JMenuItem();
        MnRekapHarianDokter = new javax.swing.JMenuItem();
        MnRekapHarianParamedis = new javax.swing.JMenuItem();
        MnRekapBulananDokter = new javax.swing.JMenuItem();
        MnRekapBulananParamedis = new javax.swing.JMenuItem();
        MnRekapHarianPoli = new javax.swing.JMenuItem();
        MnRekapHarianObat = new javax.swing.JMenuItem();
        MnRekapTindakanPerbup = new javax.swing.JMenuItem();
        MnStatus = new javax.swing.JMenu();
        ppBerkas = new javax.swing.JMenuItem();
        MnSudah = new javax.swing.JMenuItem();
        MnBelum = new javax.swing.JMenuItem();
        MnBatal = new javax.swing.JMenuItem();
        MnHapusData = new javax.swing.JMenuItem();
        MnInputData = new javax.swing.JMenu();
        MnTeridentifikasiTB = new javax.swing.JMenuItem();
        ppProgramPRB = new javax.swing.JMenuItem();
        ppPasienCorona = new javax.swing.JMenuItem();
        ppSuratKontrol = new javax.swing.JMenuItem();
        MnDiet = new javax.swing.JMenuItem();
        MnDataHAIs = new javax.swing.JMenuItem();
        MnSensusParu = new javax.swing.JMenuItem();
        MnRekamMedis = new javax.swing.JMenu();
        MnSuratTindakanDokter = new javax.swing.JMenuItem();
        MnDataTriaseIGD = new javax.swing.JMenuItem();
        MnPeniliaianAwalKeperawatanRalan = new javax.swing.JMenuItem();
        MnPeniliaianAwalMedisKebidananRalan = new javax.swing.JMenuItem();
        ppRiwayat = new javax.swing.JMenuItem();
        ppCekPaseinMati = new javax.swing.JMenuItem();
        ppPerawatanCorona = new javax.swing.JMenuItem();
        MnBilling = new javax.swing.JMenuItem();
        MnDiagnosa = new javax.swing.JMenuItem();
        MnLihatSEP = new javax.swing.JMenuItem();
        MnFormulirKlaim = new javax.swing.JMenuItem();
        MnLembarStatusPasien = new javax.swing.JMenuItem();
        MnStatusPasienAllKunjungan = new javax.swing.JMenuItem();
        ppCatatanPasien = new javax.swing.JMenuItem();
        WindowGantiDokter = new javax.swing.JDialog();
        internalFrame3 = new widget.InternalFrame();
        BtnCloseIn1 = new widget.Button();
        BtnSimpan1 = new widget.Button();
        jLabel13 = new widget.Label();
        kddokter = new widget.TextBox();
        TDokter = new widget.TextBox();
        btnCariDokter = new widget.Button();
        WindowGantiPoli = new javax.swing.JDialog();
        internalFrame5 = new widget.InternalFrame();
        BtnCloseIn4 = new widget.Button();
        BtnSimpan4 = new widget.Button();
        jLabel18 = new widget.Label();
        kdpoli = new widget.TextBox();
        nmpoli = new widget.TextBox();
        btnCariPoli = new widget.Button();
        WindowCaraBayar = new javax.swing.JDialog();
        internalFrame6 = new widget.InternalFrame();
        BtnCloseIn5 = new widget.Button();
        BtnSimpan5 = new widget.Button();
        jLabel19 = new widget.Label();
        kdpenjab = new widget.TextBox();
        nmpenjab = new widget.TextBox();
        btnBayar = new widget.Button();
        WindowPasienBooking = new javax.swing.JDialog();
        internalFrame7 = new widget.InternalFrame();
        BtnCloseIn6 = new widget.Button();
        BtnSimpan6 = new widget.Button();
        jLabel20 = new widget.Label();
        kdpoli1 = new widget.TextBox();
        nmpoli1 = new widget.TextBox();
        jLabel21 = new widget.Label();
        kdboking = new widget.TextBox();
        jLabel22 = new widget.Label();
        jLabel23 = new widget.Label();
        jLabel24 = new widget.Label();
        norm = new widget.TextBox();
        nmpasien = new widget.TextBox();
        kdpnj = new widget.TextBox();
        nmpnj = new widget.TextBox();
        jLabel25 = new widget.Label();
        KdDokter = new widget.TextBox();
        NmDokter = new widget.TextBox();
        BtnDokter = new widget.Button();
        jLabel26 = new widget.Label();
        norwBoking = new widget.TextBox();
        tglPeriksa = new widget.Tanggal();
        WindowPasienMati = new javax.swing.JDialog();
        internalFrame4 = new widget.InternalFrame();
        jPanel3 = new javax.swing.JPanel();
        panelisi1 = new widget.panelisi();
        jLabel7 = new widget.Label();
        TCari1 = new widget.TextBox();
        BtnCari1 = new widget.Button();
        BtnKeluar1 = new widget.Button();
        BtnAll1 = new widget.Button();
        Scroll2 = new widget.ScrollPane();
        tbPasienMati = new widget.Table();
        panelisi2 = new widget.panelisi();
        jLabel8 = new widget.Label();
        jLabel9 = new widget.Label();
        rmMati = new widget.TextBox();
        nmpxMati = new widget.TextBox();
        tglMati = new widget.TextBox();
        jLabel11 = new widget.Label();
        jamMati = new widget.TextBox();
        jLabel27 = new widget.Label();
        ketMati = new widget.TextBox();
        jLabel28 = new widget.Label();
        icdMati = new widget.TextBox();
        jLabel29 = new widget.Label();
        Scroll3 = new widget.ScrollPane();
        desMati = new widget.TextArea();
        jLabel30 = new widget.Label();
        tglLahrMati = new widget.TextBox();
        jLabel31 = new widget.Label();
        tmptMati = new widget.TextBox();
        WindowRiwayatKunjungan = new javax.swing.JDialog();
        internalFrame8 = new widget.InternalFrame();
        BtnCloseIn7 = new widget.Button();
        BtnLewati = new widget.Button();
        Scroll5 = new widget.ScrollPane();
        tbRiwayatKunj = new widget.Table();
        jLabel32 = new widget.Label();
        jLabel33 = new widget.Label();
        pasiendipilih = new widget.TextBox();
        BtnRM = new widget.Button();
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
        WindowRehabMedik = new javax.swing.JDialog();
        internalFrame10 = new widget.InternalFrame();
        BtnCloseIn8 = new widget.Button();
        BtnSimpan7 = new widget.Button();
        jLabel34 = new widget.Label();
        cmbRM = new widget.ComboBox();
        Kd2 = new widget.TextBox();
        TKdPny = new widget.TextBox();
        Tanggal = new widget.TextBox();
        Jam = new widget.TextBox();
        sepJkd = new widget.TextBox();
        sepJkdigd = new widget.TextBox();
        nmPasien = new widget.TextBox();
        dataGZ = new widget.TextBox();
        TNoReg = new widget.TextBox();
        cekKodeBoking = new widget.TextBox();
        CmbJam = new widget.ComboBox();
        CmbMenit = new widget.ComboBox();
        CmbDetik = new widget.ComboBox();
        ChkWaktu = new widget.CekBox();
        cekTerdaftar = new widget.TextBox();
        cekPasien = new widget.TextBox();
        TglKunRwt = new widget.Tanggal();
        internalFrame1 = new widget.InternalFrame();
        jPanel2 = new javax.swing.JPanel();
        panelGlass6 = new widget.panelisi();
        jLabel6 = new widget.Label();
        TCari = new widget.TextBox();
        BtnCari = new widget.Button();
        BtnAll = new widget.Button();
        jLabel10 = new widget.Label();
        LCount = new widget.Label();
        BtnKeluar = new widget.Button();
        panelGlass7 = new widget.panelisi();
        jLabel14 = new widget.Label();
        CrPtg = new widget.TextBox();
        BtnSeek3 = new widget.Button();
        jLabel16 = new widget.Label();
        CrPoli = new widget.TextBox();
        BtnSeek4 = new widget.Button();
        jLabel94 = new widget.Label();
        TNoRw = new widget.TextBox();
        jLabel95 = new widget.Label();
        NoRM = new widget.TextBox();
        panelGlass8 = new widget.panelisi();
        jLabel15 = new widget.Label();
        DTPCari1 = new widget.Tanggal();
        jLabel17 = new widget.Label();
        DTPCari2 = new widget.Tanggal();
        jLabel12 = new widget.Label();
        cmbStatus = new widget.ComboBox();
        BtnPxBooking = new widget.Button();
        ChkAutoRefres = new widget.CekBox();
        TabRawat = new javax.swing.JTabbedPane();
        Scroll1 = new widget.ScrollPane();
        tbKasirRalan = new widget.Table();

        jPopupMenu1.setForeground(new java.awt.Color(60, 80, 50));
        jPopupMenu1.setName("jPopupMenu1"); // NOI18N

        MnKamarInap.setBackground(new java.awt.Color(255, 255, 255));
        MnKamarInap.setFont(new java.awt.Font("Tahoma", 0, 11)); // NOI18N
        MnKamarInap.setIcon(new javax.swing.ImageIcon(getClass().getResource("/picture/category.png"))); // NOI18N
        MnKamarInap.setText("Kamar Inap");
        MnKamarInap.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        MnKamarInap.setHorizontalTextPosition(javax.swing.SwingConstants.RIGHT);
        MnKamarInap.setIconTextGap(5);
        MnKamarInap.setName("MnKamarInap"); // NOI18N
        MnKamarInap.setPreferredSize(new java.awt.Dimension(220, 26));
        MnKamarInap.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                MnKamarInapActionPerformed(evt);
            }
        });
        jPopupMenu1.add(MnKamarInap);

        MnTindakanRalan.setBackground(new java.awt.Color(248, 253, 243));
        MnTindakanRalan.setIcon(new javax.swing.ImageIcon(getClass().getResource("/picture/category.png"))); // NOI18N
        MnTindakanRalan.setText("Tindakan & Pemeriksaan");
        MnTindakanRalan.setFont(new java.awt.Font("Tahoma", 0, 11)); // NOI18N
        MnTindakanRalan.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        MnTindakanRalan.setHorizontalTextPosition(javax.swing.SwingConstants.RIGHT);
        MnTindakanRalan.setIconTextGap(5);
        MnTindakanRalan.setName("MnTindakanRalan"); // NOI18N
        MnTindakanRalan.setOpaque(true);
        MnTindakanRalan.setPreferredSize(new java.awt.Dimension(220, 26));

        MnDataRalan.setBackground(new java.awt.Color(255, 255, 255));
        MnDataRalan.setFont(new java.awt.Font("Tahoma", 0, 11)); // NOI18N
        MnDataRalan.setIcon(new javax.swing.ImageIcon(getClass().getResource("/picture/category.png"))); // NOI18N
        MnDataRalan.setText("Data Tindakan Rawat Jalan");
        MnDataRalan.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        MnDataRalan.setHorizontalTextPosition(javax.swing.SwingConstants.RIGHT);
        MnDataRalan.setIconTextGap(5);
        MnDataRalan.setName("MnDataRalan"); // NOI18N
        MnDataRalan.setPreferredSize(new java.awt.Dimension(180, 26));
        MnDataRalan.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                MnDataRalanActionPerformed(evt);
            }
        });
        MnTindakanRalan.add(MnDataRalan);

        MnPeriksaLab.setBackground(new java.awt.Color(255, 255, 255));
        MnPeriksaLab.setFont(new java.awt.Font("Tahoma", 0, 11)); // NOI18N
        MnPeriksaLab.setIcon(new javax.swing.ImageIcon(getClass().getResource("/picture/category.png"))); // NOI18N
        MnPeriksaLab.setText("Periksa Laboratorium");
        MnPeriksaLab.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        MnPeriksaLab.setHorizontalTextPosition(javax.swing.SwingConstants.RIGHT);
        MnPeriksaLab.setIconTextGap(5);
        MnPeriksaLab.setName("MnPeriksaLab"); // NOI18N
        MnPeriksaLab.setPreferredSize(new java.awt.Dimension(180, 26));
        MnPeriksaLab.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                MnPeriksaLabActionPerformed(evt);
            }
        });
        MnTindakanRalan.add(MnPeriksaLab);

        MnPeriksaRadiologi.setBackground(new java.awt.Color(255, 255, 255));
        MnPeriksaRadiologi.setFont(new java.awt.Font("Tahoma", 0, 11)); // NOI18N
        MnPeriksaRadiologi.setIcon(new javax.swing.ImageIcon(getClass().getResource("/picture/category.png"))); // NOI18N
        MnPeriksaRadiologi.setText("Periksa Radiologi");
        MnPeriksaRadiologi.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        MnPeriksaRadiologi.setHorizontalTextPosition(javax.swing.SwingConstants.RIGHT);
        MnPeriksaRadiologi.setIconTextGap(5);
        MnPeriksaRadiologi.setName("MnPeriksaRadiologi"); // NOI18N
        MnPeriksaRadiologi.setPreferredSize(new java.awt.Dimension(180, 26));
        MnPeriksaRadiologi.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                MnPeriksaRadiologiActionPerformed(evt);
            }
        });
        MnTindakanRalan.add(MnPeriksaRadiologi);

        MnOperasi.setBackground(new java.awt.Color(255, 255, 255));
        MnOperasi.setFont(new java.awt.Font("Tahoma", 0, 11)); // NOI18N
        MnOperasi.setIcon(new javax.swing.ImageIcon(getClass().getResource("/picture/category.png"))); // NOI18N
        MnOperasi.setText("Tagihan Operasi/VK");
        MnOperasi.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        MnOperasi.setHorizontalTextPosition(javax.swing.SwingConstants.RIGHT);
        MnOperasi.setIconTextGap(5);
        MnOperasi.setName("MnOperasi"); // NOI18N
        MnOperasi.setPreferredSize(new java.awt.Dimension(180, 26));
        MnOperasi.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                MnOperasiActionPerformed(evt);
            }
        });
        MnTindakanRalan.add(MnOperasi);

        jPopupMenu1.add(MnTindakanRalan);

        MnObatRalan.setBackground(new java.awt.Color(248, 253, 243));
        MnObatRalan.setIcon(new javax.swing.ImageIcon(getClass().getResource("/picture/category.png"))); // NOI18N
        MnObatRalan.setText("Obat");
        MnObatRalan.setFont(new java.awt.Font("Tahoma", 0, 11)); // NOI18N
        MnObatRalan.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        MnObatRalan.setHorizontalTextPosition(javax.swing.SwingConstants.RIGHT);
        MnObatRalan.setIconTextGap(5);
        MnObatRalan.setName("MnObatRalan"); // NOI18N
        MnObatRalan.setOpaque(true);
        MnObatRalan.setPreferredSize(new java.awt.Dimension(220, 26));

        MnCetakResepDokter.setBackground(new java.awt.Color(255, 255, 255));
        MnCetakResepDokter.setFont(new java.awt.Font("Tahoma", 0, 11)); // NOI18N
        MnCetakResepDokter.setIcon(new javax.swing.ImageIcon(getClass().getResource("/picture/category.png"))); // NOI18N
        MnCetakResepDokter.setText("Cetak Resep Dokter");
        MnCetakResepDokter.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        MnCetakResepDokter.setHorizontalTextPosition(javax.swing.SwingConstants.RIGHT);
        MnCetakResepDokter.setIconTextGap(5);
        MnCetakResepDokter.setName("MnCetakResepDokter"); // NOI18N
        MnCetakResepDokter.setPreferredSize(new java.awt.Dimension(160, 26));
        MnCetakResepDokter.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                MnCetakResepDokterActionPerformed(evt);
            }
        });
        MnObatRalan.add(MnCetakResepDokter);

        MnReturJual.setBackground(new java.awt.Color(255, 255, 255));
        MnReturJual.setFont(new java.awt.Font("Tahoma", 0, 11)); // NOI18N
        MnReturJual.setIcon(new javax.swing.ImageIcon(getClass().getResource("/picture/category.png"))); // NOI18N
        MnReturJual.setText("Retur Obat/Alkes");
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
        MnObatRalan.add(MnReturJual);

        MnNoResep.setBackground(new java.awt.Color(255, 255, 255));
        MnNoResep.setFont(new java.awt.Font("Tahoma", 0, 11)); // NOI18N
        MnNoResep.setIcon(new javax.swing.ImageIcon(getClass().getResource("/picture/category.png"))); // NOI18N
        MnNoResep.setText("Input No.Resep");
        MnNoResep.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        MnNoResep.setHorizontalTextPosition(javax.swing.SwingConstants.RIGHT);
        MnNoResep.setIconTextGap(5);
        MnNoResep.setName("MnNoResep"); // NOI18N
        MnNoResep.setPreferredSize(new java.awt.Dimension(160, 26));
        MnNoResep.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                MnNoResepActionPerformed(evt);
            }
        });
        MnObatRalan.add(MnNoResep);

        MnResepDOkter.setBackground(new java.awt.Color(255, 255, 255));
        MnResepDOkter.setFont(new java.awt.Font("Tahoma", 0, 11)); // NOI18N
        MnResepDOkter.setIcon(new javax.swing.ImageIcon(getClass().getResource("/picture/category.png"))); // NOI18N
        MnResepDOkter.setText("Input Resep Dokter");
        MnResepDOkter.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        MnResepDOkter.setHorizontalTextPosition(javax.swing.SwingConstants.RIGHT);
        MnResepDOkter.setIconTextGap(5);
        MnResepDOkter.setName("MnResepDOkter"); // NOI18N
        MnResepDOkter.setPreferredSize(new java.awt.Dimension(160, 26));
        MnResepDOkter.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                MnResepDOkterActionPerformed(evt);
            }
        });
        MnObatRalan.add(MnResepDOkter);

        MnDataPemberianObat.setBackground(new java.awt.Color(255, 255, 255));
        MnDataPemberianObat.setFont(new java.awt.Font("Tahoma", 0, 11)); // NOI18N
        MnDataPemberianObat.setIcon(new javax.swing.ImageIcon(getClass().getResource("/picture/category.png"))); // NOI18N
        MnDataPemberianObat.setText("Data Pemberian Obat");
        MnDataPemberianObat.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        MnDataPemberianObat.setHorizontalTextPosition(javax.swing.SwingConstants.RIGHT);
        MnDataPemberianObat.setIconTextGap(5);
        MnDataPemberianObat.setName("MnDataPemberianObat"); // NOI18N
        MnDataPemberianObat.setPreferredSize(new java.awt.Dimension(160, 26));
        MnDataPemberianObat.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                MnDataPemberianObatActionPerformed(evt);
            }
        });
        MnObatRalan.add(MnDataPemberianObat);

        jPopupMenu1.add(MnObatRalan);

        MnGanti.setBackground(new java.awt.Color(248, 253, 243));
        MnGanti.setIcon(new javax.swing.ImageIcon(getClass().getResource("/picture/category.png"))); // NOI18N
        MnGanti.setText("Ganti");
        MnGanti.setFont(new java.awt.Font("Tahoma", 0, 11)); // NOI18N
        MnGanti.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        MnGanti.setHorizontalTextPosition(javax.swing.SwingConstants.RIGHT);
        MnGanti.setIconTextGap(5);
        MnGanti.setName("MnGanti"); // NOI18N
        MnGanti.setOpaque(true);
        MnGanti.setPreferredSize(new java.awt.Dimension(220, 26));

        MnRehabMedik.setBackground(new java.awt.Color(255, 255, 255));
        MnRehabMedik.setFont(new java.awt.Font("Tahoma", 0, 11)); // NOI18N
        MnRehabMedik.setIcon(new javax.swing.ImageIcon(getClass().getResource("/picture/category.png"))); // NOI18N
        MnRehabMedik.setText("Pilihan Rehabilitasi Medik");
        MnRehabMedik.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        MnRehabMedik.setHorizontalTextPosition(javax.swing.SwingConstants.RIGHT);
        MnRehabMedik.setIconTextGap(5);
        MnRehabMedik.setName("MnRehabMedik"); // NOI18N
        MnRehabMedik.setPreferredSize(new java.awt.Dimension(160, 26));
        MnRehabMedik.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                MnRehabMedikActionPerformed(evt);
            }
        });
        MnGanti.add(MnRehabMedik);

        MnPoli.setBackground(new java.awt.Color(255, 255, 255));
        MnPoli.setFont(new java.awt.Font("Tahoma", 0, 11)); // NOI18N
        MnPoli.setIcon(new javax.swing.ImageIcon(getClass().getResource("/picture/category.png"))); // NOI18N
        MnPoli.setText("Poliklinik");
        MnPoli.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        MnPoli.setHorizontalTextPosition(javax.swing.SwingConstants.RIGHT);
        MnPoli.setIconTextGap(5);
        MnPoli.setName("MnPoli"); // NOI18N
        MnPoli.setPreferredSize(new java.awt.Dimension(160, 26));
        MnPoli.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                MnPoliActionPerformed(evt);
            }
        });
        MnGanti.add(MnPoli);

        MnDokter.setBackground(new java.awt.Color(255, 255, 255));
        MnDokter.setFont(new java.awt.Font("Tahoma", 0, 11)); // NOI18N
        MnDokter.setIcon(new javax.swing.ImageIcon(getClass().getResource("/picture/category.png"))); // NOI18N
        MnDokter.setText("Dokter Poli");
        MnDokter.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        MnDokter.setHorizontalTextPosition(javax.swing.SwingConstants.RIGHT);
        MnDokter.setIconTextGap(5);
        MnDokter.setName("MnDokter"); // NOI18N
        MnDokter.setPreferredSize(new java.awt.Dimension(160, 26));
        MnDokter.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                MnDokterActionPerformed(evt);
            }
        });
        MnGanti.add(MnDokter);

        MnPenjab.setBackground(new java.awt.Color(255, 255, 255));
        MnPenjab.setFont(new java.awt.Font("Tahoma", 0, 11)); // NOI18N
        MnPenjab.setIcon(new javax.swing.ImageIcon(getClass().getResource("/picture/category.png"))); // NOI18N
        MnPenjab.setText("Jenis Bayar");
        MnPenjab.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        MnPenjab.setHorizontalTextPosition(javax.swing.SwingConstants.RIGHT);
        MnPenjab.setIconTextGap(5);
        MnPenjab.setName("MnPenjab"); // NOI18N
        MnPenjab.setPreferredSize(new java.awt.Dimension(160, 26));
        MnPenjab.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                MnPenjabActionPerformed(evt);
            }
        });
        MnGanti.add(MnPenjab);

        jPopupMenu1.add(MnGanti);

        MnPermintaan.setBackground(new java.awt.Color(252, 255, 250));
        MnPermintaan.setIcon(new javax.swing.ImageIcon(getClass().getResource("/picture/category.png"))); // NOI18N
        MnPermintaan.setText("Permintaan");
        MnPermintaan.setFont(new java.awt.Font("Tahoma", 0, 11)); // NOI18N
        MnPermintaan.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        MnPermintaan.setHorizontalTextPosition(javax.swing.SwingConstants.RIGHT);
        MnPermintaan.setIconTextGap(5);
        MnPermintaan.setName("MnPermintaan"); // NOI18N
        MnPermintaan.setOpaque(true);
        MnPermintaan.setPreferredSize(new java.awt.Dimension(200, 26));

        MnEklaimINACBG.setBackground(new java.awt.Color(248, 253, 243));
        MnEklaimINACBG.setIcon(new javax.swing.ImageIcon(getClass().getResource("/picture/category.png"))); // NOI18N
        MnEklaimINACBG.setText("Bridging Eklaim INACBG");
        MnEklaimINACBG.setFont(new java.awt.Font("Tahoma", 0, 11)); // NOI18N
        MnEklaimINACBG.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        MnEklaimINACBG.setHorizontalTextPosition(javax.swing.SwingConstants.RIGHT);
        MnEklaimINACBG.setIconTextGap(5);
        MnEklaimINACBG.setName("MnEklaimINACBG"); // NOI18N
        MnEklaimINACBG.setOpaque(true);
        MnEklaimINACBG.setPreferredSize(new java.awt.Dimension(220, 26));

        MnKlaimJKN.setBackground(new java.awt.Color(255, 255, 255));
        MnKlaimJKN.setFont(new java.awt.Font("Tahoma", 0, 11)); // NOI18N
        MnKlaimJKN.setIcon(new javax.swing.ImageIcon(getClass().getResource("/picture/category.png"))); // NOI18N
        MnKlaimJKN.setText("Klaim JKN");
        MnKlaimJKN.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        MnKlaimJKN.setHorizontalTextPosition(javax.swing.SwingConstants.RIGHT);
        MnKlaimJKN.setIconTextGap(5);
        MnKlaimJKN.setName("MnKlaimJKN"); // NOI18N
        MnKlaimJKN.setPreferredSize(new java.awt.Dimension(130, 26));
        MnKlaimJKN.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                MnKlaimJKNActionPerformed(evt);
            }
        });
        MnEklaimINACBG.add(MnKlaimJKN);

        MnKlaimCOVID.setBackground(new java.awt.Color(255, 255, 255));
        MnKlaimCOVID.setFont(new java.awt.Font("Tahoma", 0, 11)); // NOI18N
        MnKlaimCOVID.setIcon(new javax.swing.ImageIcon(getClass().getResource("/picture/category.png"))); // NOI18N
        MnKlaimCOVID.setText("Klaim COVID-19");
        MnKlaimCOVID.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        MnKlaimCOVID.setHorizontalTextPosition(javax.swing.SwingConstants.RIGHT);
        MnKlaimCOVID.setIconTextGap(5);
        MnKlaimCOVID.setName("MnKlaimCOVID"); // NOI18N
        MnKlaimCOVID.setPreferredSize(new java.awt.Dimension(130, 26));
        MnKlaimCOVID.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                MnKlaimCOVIDActionPerformed(evt);
            }
        });
        MnEklaimINACBG.add(MnKlaimCOVID);

        MnKlaimKIPI.setBackground(new java.awt.Color(255, 255, 255));
        MnKlaimKIPI.setFont(new java.awt.Font("Tahoma", 0, 11)); // NOI18N
        MnKlaimKIPI.setIcon(new javax.swing.ImageIcon(getClass().getResource("/picture/category.png"))); // NOI18N
        MnKlaimKIPI.setText("Klaim KIPI");
        MnKlaimKIPI.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        MnKlaimKIPI.setHorizontalTextPosition(javax.swing.SwingConstants.RIGHT);
        MnKlaimKIPI.setIconTextGap(5);
        MnKlaimKIPI.setName("MnKlaimKIPI"); // NOI18N
        MnKlaimKIPI.setPreferredSize(new java.awt.Dimension(130, 26));
        MnKlaimKIPI.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                MnKlaimKIPIActionPerformed(evt);
            }
        });
        MnEklaimINACBG.add(MnKlaimKIPI);

        MnPermintaan.add(MnEklaimINACBG);

        MnRencanaKontrolManual.setBackground(new java.awt.Color(255, 255, 255));
        MnRencanaKontrolManual.setFont(new java.awt.Font("Tahoma", 0, 11)); // NOI18N
        MnRencanaKontrolManual.setIcon(new javax.swing.ImageIcon(getClass().getResource("/picture/category.png"))); // NOI18N
        MnRencanaKontrolManual.setText("Rencana Kontrol BPJS (MANUAL)");
        MnRencanaKontrolManual.setEnabled(false);
        MnRencanaKontrolManual.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        MnRencanaKontrolManual.setHorizontalTextPosition(javax.swing.SwingConstants.RIGHT);
        MnRencanaKontrolManual.setIconTextGap(5);
        MnRencanaKontrolManual.setName("MnRencanaKontrolManual"); // NOI18N
        MnRencanaKontrolManual.setPreferredSize(new java.awt.Dimension(200, 26));
        MnRencanaKontrolManual.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                MnRencanaKontrolManualActionPerformed(evt);
            }
        });
        MnPermintaan.add(MnRencanaKontrolManual);

        MnSEPBPJS.setBackground(new java.awt.Color(255, 255, 255));
        MnSEPBPJS.setFont(new java.awt.Font("Tahoma", 0, 11)); // NOI18N
        MnSEPBPJS.setIcon(new javax.swing.ImageIcon(getClass().getResource("/picture/category.png"))); // NOI18N
        MnSEPBPJS.setText("Bridging SEP BPJS");
        MnSEPBPJS.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        MnSEPBPJS.setHorizontalTextPosition(javax.swing.SwingConstants.RIGHT);
        MnSEPBPJS.setIconTextGap(5);
        MnSEPBPJS.setName("MnSEPBPJS"); // NOI18N
        MnSEPBPJS.setPreferredSize(new java.awt.Dimension(200, 26));
        MnSEPBPJS.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                MnSEPBPJSActionPerformed(evt);
            }
        });
        MnPermintaan.add(MnSEPBPJS);

        MnSKDPBPJS.setBackground(new java.awt.Color(255, 255, 255));
        MnSKDPBPJS.setFont(new java.awt.Font("Tahoma", 0, 11)); // NOI18N
        MnSKDPBPJS.setIcon(new javax.swing.ImageIcon(getClass().getResource("/picture/category.png"))); // NOI18N
        MnSKDPBPJS.setText("SKDP BPJS");
        MnSKDPBPJS.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        MnSKDPBPJS.setHorizontalTextPosition(javax.swing.SwingConstants.RIGHT);
        MnSKDPBPJS.setIconTextGap(5);
        MnSKDPBPJS.setName("MnSKDPBPJS"); // NOI18N
        MnSKDPBPJS.setPreferredSize(new java.awt.Dimension(200, 26));
        MnSKDPBPJS.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                MnSKDPBPJSActionPerformed(evt);
            }
        });
        MnPermintaan.add(MnSKDPBPJS);

        MnPermintaanLab.setBackground(new java.awt.Color(255, 255, 255));
        MnPermintaanLab.setFont(new java.awt.Font("Tahoma", 0, 11)); // NOI18N
        MnPermintaanLab.setIcon(new javax.swing.ImageIcon(getClass().getResource("/picture/category.png"))); // NOI18N
        MnPermintaanLab.setText("Pemeriksaan Laboratorium");
        MnPermintaanLab.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        MnPermintaanLab.setHorizontalTextPosition(javax.swing.SwingConstants.RIGHT);
        MnPermintaanLab.setIconTextGap(5);
        MnPermintaanLab.setName("MnPermintaanLab"); // NOI18N
        MnPermintaanLab.setPreferredSize(new java.awt.Dimension(200, 26));
        MnPermintaanLab.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                MnPermintaanLabActionPerformed(evt);
            }
        });
        MnPermintaan.add(MnPermintaanLab);

        MnPermintaanRadiologi.setBackground(new java.awt.Color(255, 255, 255));
        MnPermintaanRadiologi.setFont(new java.awt.Font("Tahoma", 0, 11)); // NOI18N
        MnPermintaanRadiologi.setIcon(new javax.swing.ImageIcon(getClass().getResource("/picture/category.png"))); // NOI18N
        MnPermintaanRadiologi.setText("Pemeriksaan Radiologi");
        MnPermintaanRadiologi.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        MnPermintaanRadiologi.setHorizontalTextPosition(javax.swing.SwingConstants.RIGHT);
        MnPermintaanRadiologi.setIconTextGap(5);
        MnPermintaanRadiologi.setName("MnPermintaanRadiologi"); // NOI18N
        MnPermintaanRadiologi.setPreferredSize(new java.awt.Dimension(200, 26));
        MnPermintaanRadiologi.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                MnPermintaanRadiologiActionPerformed(evt);
            }
        });
        MnPermintaan.add(MnPermintaanRadiologi);

        MnCariPermintaanLab.setBackground(new java.awt.Color(255, 255, 255));
        MnCariPermintaanLab.setFont(new java.awt.Font("Tahoma", 0, 11)); // NOI18N
        MnCariPermintaanLab.setIcon(new javax.swing.ImageIcon(getClass().getResource("/picture/category.png"))); // NOI18N
        MnCariPermintaanLab.setText("Cari Permintaan Laboratorium");
        MnCariPermintaanLab.setEnabled(false);
        MnCariPermintaanLab.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        MnCariPermintaanLab.setHorizontalTextPosition(javax.swing.SwingConstants.RIGHT);
        MnCariPermintaanLab.setIconTextGap(5);
        MnCariPermintaanLab.setName("MnCariPermintaanLab"); // NOI18N
        MnCariPermintaanLab.setPreferredSize(new java.awt.Dimension(200, 26));
        MnCariPermintaanLab.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                MnCariPermintaanLabActionPerformed(evt);
            }
        });
        MnPermintaan.add(MnCariPermintaanLab);

        MnCariPermintaanRad.setBackground(new java.awt.Color(255, 255, 255));
        MnCariPermintaanRad.setFont(new java.awt.Font("Tahoma", 0, 11)); // NOI18N
        MnCariPermintaanRad.setIcon(new javax.swing.ImageIcon(getClass().getResource("/picture/category.png"))); // NOI18N
        MnCariPermintaanRad.setText("Cari Permintaan Radiologi");
        MnCariPermintaanRad.setEnabled(false);
        MnCariPermintaanRad.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        MnCariPermintaanRad.setHorizontalTextPosition(javax.swing.SwingConstants.RIGHT);
        MnCariPermintaanRad.setIconTextGap(5);
        MnCariPermintaanRad.setName("MnCariPermintaanRad"); // NOI18N
        MnCariPermintaanRad.setPreferredSize(new java.awt.Dimension(200, 26));
        MnCariPermintaanRad.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                MnCariPermintaanRadActionPerformed(evt);
            }
        });
        MnPermintaan.add(MnCariPermintaanRad);

        jPopupMenu1.add(MnPermintaan);

        MnRujukan.setBackground(new java.awt.Color(248, 253, 243));
        MnRujukan.setIcon(new javax.swing.ImageIcon(getClass().getResource("/picture/category.png"))); // NOI18N
        MnRujukan.setText("Rujukan");
        MnRujukan.setFont(new java.awt.Font("Tahoma", 0, 11)); // NOI18N
        MnRujukan.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        MnRujukan.setHorizontalTextPosition(javax.swing.SwingConstants.RIGHT);
        MnRujukan.setIconTextGap(5);
        MnRujukan.setName("MnRujukan"); // NOI18N
        MnRujukan.setOpaque(true);
        MnRujukan.setPreferredSize(new java.awt.Dimension(220, 26));

        MnPoliInternal.setBackground(new java.awt.Color(255, 255, 255));
        MnPoliInternal.setFont(new java.awt.Font("Tahoma", 0, 11)); // NOI18N
        MnPoliInternal.setIcon(new javax.swing.ImageIcon(getClass().getResource("/picture/category.png"))); // NOI18N
        MnPoliInternal.setText("Poliklinik Internal");
        MnPoliInternal.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        MnPoliInternal.setHorizontalTextPosition(javax.swing.SwingConstants.RIGHT);
        MnPoliInternal.setIconTextGap(5);
        MnPoliInternal.setName("MnPoliInternal"); // NOI18N
        MnPoliInternal.setPreferredSize(new java.awt.Dimension(130, 26));
        MnPoliInternal.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                MnPoliInternalActionPerformed(evt);
            }
        });
        MnRujukan.add(MnPoliInternal);

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

        jPopupMenu1.add(MnRujukan);

        MnRekap.setBackground(new java.awt.Color(248, 253, 243));
        MnRekap.setIcon(new javax.swing.ImageIcon(getClass().getResource("/picture/category.png"))); // NOI18N
        MnRekap.setText("Rekap Data");
        MnRekap.setFont(new java.awt.Font("Tahoma", 0, 11)); // NOI18N
        MnRekap.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        MnRekap.setHorizontalTextPosition(javax.swing.SwingConstants.RIGHT);
        MnRekap.setIconTextGap(5);
        MnRekap.setName("MnRekap"); // NOI18N
        MnRekap.setOpaque(true);
        MnRekap.setPreferredSize(new java.awt.Dimension(220, 26));

        MnDietMakanan.setBackground(new java.awt.Color(255, 255, 255));
        MnDietMakanan.setFont(new java.awt.Font("Tahoma", 0, 11)); // NOI18N
        MnDietMakanan.setIcon(new javax.swing.ImageIcon(getClass().getResource("/picture/category.png"))); // NOI18N
        MnDietMakanan.setText("Diet Makanan");
        MnDietMakanan.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        MnDietMakanan.setHorizontalTextPosition(javax.swing.SwingConstants.RIGHT);
        MnDietMakanan.setIconTextGap(5);
        MnDietMakanan.setName("MnDietMakanan"); // NOI18N
        MnDietMakanan.setPreferredSize(new java.awt.Dimension(240, 26));
        MnDietMakanan.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                MnDietMakananActionPerformed(evt);
            }
        });
        MnRekap.add(MnDietMakanan);

        MnRekapHarianDokter.setBackground(new java.awt.Color(255, 255, 255));
        MnRekapHarianDokter.setFont(new java.awt.Font("Tahoma", 0, 11)); // NOI18N
        MnRekapHarianDokter.setIcon(new javax.swing.ImageIcon(getClass().getResource("/picture/category.png"))); // NOI18N
        MnRekapHarianDokter.setText("Rekap Harian Dokter ");
        MnRekapHarianDokter.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        MnRekapHarianDokter.setHorizontalTextPosition(javax.swing.SwingConstants.RIGHT);
        MnRekapHarianDokter.setIconTextGap(5);
        MnRekapHarianDokter.setName("MnRekapHarianDokter"); // NOI18N
        MnRekapHarianDokter.setPreferredSize(new java.awt.Dimension(195, 26));
        MnRekapHarianDokter.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                MnRekapHarianDokterActionPerformed(evt);
            }
        });
        MnRekap.add(MnRekapHarianDokter);

        MnRekapHarianParamedis.setBackground(new java.awt.Color(255, 255, 255));
        MnRekapHarianParamedis.setFont(new java.awt.Font("Tahoma", 0, 11)); // NOI18N
        MnRekapHarianParamedis.setIcon(new javax.swing.ImageIcon(getClass().getResource("/picture/category.png"))); // NOI18N
        MnRekapHarianParamedis.setText("Rekap Harian Paramedis");
        MnRekapHarianParamedis.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        MnRekapHarianParamedis.setHorizontalTextPosition(javax.swing.SwingConstants.RIGHT);
        MnRekapHarianParamedis.setIconTextGap(5);
        MnRekapHarianParamedis.setName("MnRekapHarianParamedis"); // NOI18N
        MnRekapHarianParamedis.setPreferredSize(new java.awt.Dimension(195, 26));
        MnRekapHarianParamedis.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                MnRekapHarianParamedisActionPerformed(evt);
            }
        });
        MnRekap.add(MnRekapHarianParamedis);

        MnRekapBulananDokter.setBackground(new java.awt.Color(255, 255, 255));
        MnRekapBulananDokter.setFont(new java.awt.Font("Tahoma", 0, 11)); // NOI18N
        MnRekapBulananDokter.setIcon(new javax.swing.ImageIcon(getClass().getResource("/picture/category.png"))); // NOI18N
        MnRekapBulananDokter.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        MnRekapBulananDokter.setHorizontalTextPosition(javax.swing.SwingConstants.RIGHT);
        MnRekapBulananDokter.setIconTextGap(5);
        MnRekapBulananDokter.setLabel("Rekap Bulanan Dokter ");
        MnRekapBulananDokter.setName("MnRekapBulananDokter"); // NOI18N
        MnRekapBulananDokter.setPreferredSize(new java.awt.Dimension(195, 26));
        MnRekapBulananDokter.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                MnRekapBulananDokterActionPerformed(evt);
            }
        });
        MnRekap.add(MnRekapBulananDokter);

        MnRekapBulananParamedis.setBackground(new java.awt.Color(255, 255, 255));
        MnRekapBulananParamedis.setFont(new java.awt.Font("Tahoma", 0, 11)); // NOI18N
        MnRekapBulananParamedis.setIcon(new javax.swing.ImageIcon(getClass().getResource("/picture/category.png"))); // NOI18N
        MnRekapBulananParamedis.setText("Rekap Bulanan Paramedis");
        MnRekapBulananParamedis.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        MnRekapBulananParamedis.setHorizontalTextPosition(javax.swing.SwingConstants.RIGHT);
        MnRekapBulananParamedis.setIconTextGap(5);
        MnRekapBulananParamedis.setName("MnRekapBulananParamedis"); // NOI18N
        MnRekapBulananParamedis.setPreferredSize(new java.awt.Dimension(195, 26));
        MnRekapBulananParamedis.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                MnRekapBulananParamedisActionPerformed(evt);
            }
        });
        MnRekap.add(MnRekapBulananParamedis);

        MnRekapHarianPoli.setBackground(new java.awt.Color(255, 255, 255));
        MnRekapHarianPoli.setFont(new java.awt.Font("Tahoma", 0, 11)); // NOI18N
        MnRekapHarianPoli.setIcon(new javax.swing.ImageIcon(getClass().getResource("/picture/category.png"))); // NOI18N
        MnRekapHarianPoli.setText("Rekap Harian Poli");
        MnRekapHarianPoli.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        MnRekapHarianPoli.setHorizontalTextPosition(javax.swing.SwingConstants.RIGHT);
        MnRekapHarianPoli.setIconTextGap(5);
        MnRekapHarianPoli.setName("MnRekapHarianPoli"); // NOI18N
        MnRekapHarianPoli.setPreferredSize(new java.awt.Dimension(195, 26));
        MnRekapHarianPoli.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                MnRekapHarianPoliActionPerformed(evt);
            }
        });
        MnRekap.add(MnRekapHarianPoli);

        MnRekapHarianObat.setBackground(new java.awt.Color(255, 255, 255));
        MnRekapHarianObat.setFont(new java.awt.Font("Tahoma", 0, 11)); // NOI18N
        MnRekapHarianObat.setIcon(new javax.swing.ImageIcon(getClass().getResource("/picture/category.png"))); // NOI18N
        MnRekapHarianObat.setText("Rekap Harian Obat");
        MnRekapHarianObat.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        MnRekapHarianObat.setHorizontalTextPosition(javax.swing.SwingConstants.RIGHT);
        MnRekapHarianObat.setIconTextGap(5);
        MnRekapHarianObat.setName("MnRekapHarianObat"); // NOI18N
        MnRekapHarianObat.setPreferredSize(new java.awt.Dimension(195, 26));
        MnRekapHarianObat.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                MnRekapHarianObatActionPerformed(evt);
            }
        });
        MnRekap.add(MnRekapHarianObat);

        MnRekapTindakanPerbup.setBackground(new java.awt.Color(255, 255, 255));
        MnRekapTindakanPerbup.setFont(new java.awt.Font("Tahoma", 0, 11)); // NOI18N
        MnRekapTindakanPerbup.setIcon(new javax.swing.ImageIcon(getClass().getResource("/picture/category.png"))); // NOI18N
        MnRekapTindakanPerbup.setText("Rekap Tindakan PERBUP Ralan");
        MnRekapTindakanPerbup.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        MnRekapTindakanPerbup.setHorizontalTextPosition(javax.swing.SwingConstants.RIGHT);
        MnRekapTindakanPerbup.setIconTextGap(5);
        MnRekapTindakanPerbup.setName("MnRekapTindakanPerbup"); // NOI18N
        MnRekapTindakanPerbup.setPreferredSize(new java.awt.Dimension(195, 26));
        MnRekapTindakanPerbup.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                MnRekapTindakanPerbupActionPerformed(evt);
            }
        });
        MnRekap.add(MnRekapTindakanPerbup);

        jPopupMenu1.add(MnRekap);

        MnStatus.setBackground(new java.awt.Color(248, 253, 243));
        MnStatus.setIcon(new javax.swing.ImageIcon(getClass().getResource("/picture/category.png"))); // NOI18N
        MnStatus.setText("Set Status");
        MnStatus.setFont(new java.awt.Font("Tahoma", 0, 11)); // NOI18N
        MnStatus.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        MnStatus.setHorizontalTextPosition(javax.swing.SwingConstants.RIGHT);
        MnStatus.setIconTextGap(5);
        MnStatus.setName("MnStatus"); // NOI18N
        MnStatus.setOpaque(true);
        MnStatus.setPreferredSize(new java.awt.Dimension(220, 26));

        ppBerkas.setBackground(new java.awt.Color(255, 255, 255));
        ppBerkas.setFont(new java.awt.Font("Tahoma", 0, 11)); // NOI18N
        ppBerkas.setIcon(new javax.swing.ImageIcon(getClass().getResource("/picture/category.png"))); // NOI18N
        ppBerkas.setText("Berkas R.M. Diterima");
        ppBerkas.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        ppBerkas.setHorizontalTextPosition(javax.swing.SwingConstants.RIGHT);
        ppBerkas.setIconTextGap(5);
        ppBerkas.setName("ppBerkas"); // NOI18N
        ppBerkas.setPreferredSize(new java.awt.Dimension(150, 26));
        ppBerkas.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                ppBerkasBtnPrintActionPerformed(evt);
            }
        });
        MnStatus.add(ppBerkas);

        MnSudah.setBackground(new java.awt.Color(255, 255, 255));
        MnSudah.setFont(new java.awt.Font("Tahoma", 0, 11)); // NOI18N
        MnSudah.setIcon(new javax.swing.ImageIcon(getClass().getResource("/picture/category.png"))); // NOI18N
        MnSudah.setText("Sudah Periksa");
        MnSudah.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        MnSudah.setHorizontalTextPosition(javax.swing.SwingConstants.RIGHT);
        MnSudah.setIconTextGap(5);
        MnSudah.setName("MnSudah"); // NOI18N
        MnSudah.setPreferredSize(new java.awt.Dimension(150, 26));
        MnSudah.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                MnSudahActionPerformed(evt);
            }
        });
        MnStatus.add(MnSudah);

        MnBelum.setBackground(new java.awt.Color(255, 255, 255));
        MnBelum.setFont(new java.awt.Font("Tahoma", 0, 11)); // NOI18N
        MnBelum.setIcon(new javax.swing.ImageIcon(getClass().getResource("/picture/category.png"))); // NOI18N
        MnBelum.setText("Belum Periksa");
        MnBelum.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        MnBelum.setHorizontalTextPosition(javax.swing.SwingConstants.RIGHT);
        MnBelum.setIconTextGap(5);
        MnBelum.setName("MnBelum"); // NOI18N
        MnBelum.setPreferredSize(new java.awt.Dimension(150, 26));
        MnBelum.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                MnBelumActionPerformed(evt);
            }
        });
        MnStatus.add(MnBelum);

        MnBatal.setBackground(new java.awt.Color(255, 255, 255));
        MnBatal.setFont(new java.awt.Font("Tahoma", 0, 11)); // NOI18N
        MnBatal.setIcon(new javax.swing.ImageIcon(getClass().getResource("/picture/category.png"))); // NOI18N
        MnBatal.setText("Batal Periksa");
        MnBatal.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        MnBatal.setHorizontalTextPosition(javax.swing.SwingConstants.RIGHT);
        MnBatal.setIconTextGap(5);
        MnBatal.setName("MnBatal"); // NOI18N
        MnBatal.setPreferredSize(new java.awt.Dimension(150, 26));
        MnBatal.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                MnBatalActionPerformed(evt);
            }
        });
        MnStatus.add(MnBatal);

        jPopupMenu1.add(MnStatus);

        MnHapusData.setBackground(new java.awt.Color(255, 255, 255));
        MnHapusData.setFont(new java.awt.Font("Tahoma", 0, 11)); // NOI18N
        MnHapusData.setIcon(new javax.swing.ImageIcon(getClass().getResource("/picture/category.png"))); // NOI18N
        MnHapusData.setText("Hapus Semua Transaksi");
        MnHapusData.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        MnHapusData.setHorizontalTextPosition(javax.swing.SwingConstants.RIGHT);
        MnHapusData.setIconTextGap(5);
        MnHapusData.setName("MnHapusData"); // NOI18N
        MnHapusData.setPreferredSize(new java.awt.Dimension(220, 26));
        MnHapusData.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                MnHapusDataActionPerformed(evt);
            }
        });
        jPopupMenu1.add(MnHapusData);

        MnInputData.setBackground(new java.awt.Color(248, 253, 243));
        MnInputData.setIcon(new javax.swing.ImageIcon(getClass().getResource("/picture/category.png"))); // NOI18N
        MnInputData.setText("Input Data Tambahan");
        MnInputData.setFont(new java.awt.Font("Tahoma", 0, 11)); // NOI18N
        MnInputData.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        MnInputData.setHorizontalTextPosition(javax.swing.SwingConstants.RIGHT);
        MnInputData.setIconTextGap(5);
        MnInputData.setName("MnInputData"); // NOI18N
        MnInputData.setOpaque(true);
        MnInputData.setPreferredSize(new java.awt.Dimension(220, 26));

        MnTeridentifikasiTB.setBackground(new java.awt.Color(255, 255, 255));
        MnTeridentifikasiTB.setFont(new java.awt.Font("Tahoma", 0, 11)); // NOI18N
        MnTeridentifikasiTB.setIcon(new javax.swing.ImageIcon(getClass().getResource("/picture/category.png"))); // NOI18N
        MnTeridentifikasiTB.setText("Teridentifikasi TB (SITB)");
        MnTeridentifikasiTB.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        MnTeridentifikasiTB.setHorizontalTextPosition(javax.swing.SwingConstants.RIGHT);
        MnTeridentifikasiTB.setIconTextGap(5);
        MnTeridentifikasiTB.setName("MnTeridentifikasiTB"); // NOI18N
        MnTeridentifikasiTB.setPreferredSize(new java.awt.Dimension(190, 26));
        MnTeridentifikasiTB.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                MnTeridentifikasiTBBtnPrintActionPerformed(evt);
            }
        });
        MnInputData.add(MnTeridentifikasiTB);

        ppProgramPRB.setBackground(new java.awt.Color(255, 255, 255));
        ppProgramPRB.setFont(new java.awt.Font("Tahoma", 0, 11)); // NOI18N
        ppProgramPRB.setIcon(new javax.swing.ImageIcon(getClass().getResource("/picture/category.png"))); // NOI18N
        ppProgramPRB.setText("Program PRB BPJS");
        ppProgramPRB.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        ppProgramPRB.setHorizontalTextPosition(javax.swing.SwingConstants.RIGHT);
        ppProgramPRB.setIconTextGap(5);
        ppProgramPRB.setName("ppProgramPRB"); // NOI18N
        ppProgramPRB.setPreferredSize(new java.awt.Dimension(190, 26));
        ppProgramPRB.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                ppProgramPRBBtnPrintActionPerformed(evt);
            }
        });
        MnInputData.add(ppProgramPRB);

        ppPasienCorona.setBackground(new java.awt.Color(255, 255, 254));
        ppPasienCorona.setFont(new java.awt.Font("Tahoma", 0, 11)); // NOI18N
        ppPasienCorona.setForeground(new java.awt.Color(50, 50, 50));
        ppPasienCorona.setIcon(new javax.swing.ImageIcon(getClass().getResource("/picture/category.png"))); // NOI18N
        ppPasienCorona.setText("Pasien Corona Kemenkes");
        ppPasienCorona.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        ppPasienCorona.setHorizontalTextPosition(javax.swing.SwingConstants.RIGHT);
        ppPasienCorona.setIconTextGap(5);
        ppPasienCorona.setName("ppPasienCorona"); // NOI18N
        ppPasienCorona.setPreferredSize(new java.awt.Dimension(190, 26));
        ppPasienCorona.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                ppPasienCoronaBtnPrintActionPerformed(evt);
            }
        });
        MnInputData.add(ppPasienCorona);

        ppSuratKontrol.setBackground(new java.awt.Color(255, 255, 254));
        ppSuratKontrol.setFont(new java.awt.Font("Tahoma", 0, 11)); // NOI18N
        ppSuratKontrol.setForeground(new java.awt.Color(0, 0, 0));
        ppSuratKontrol.setIcon(new javax.swing.ImageIcon(getClass().getResource("/picture/category.png"))); // NOI18N
        ppSuratKontrol.setText("Rencana Kontrol BPJS");
        ppSuratKontrol.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        ppSuratKontrol.setHorizontalTextPosition(javax.swing.SwingConstants.RIGHT);
        ppSuratKontrol.setIconTextGap(5);
        ppSuratKontrol.setName("ppSuratKontrol"); // NOI18N
        ppSuratKontrol.setPreferredSize(new java.awt.Dimension(190, 26));
        ppSuratKontrol.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                ppSuratKontrolBtnPrintActionPerformed(evt);
            }
        });
        MnInputData.add(ppSuratKontrol);

        MnDiet.setBackground(new java.awt.Color(255, 255, 255));
        MnDiet.setFont(new java.awt.Font("Tahoma", 0, 11)); // NOI18N
        MnDiet.setIcon(new javax.swing.ImageIcon(getClass().getResource("/picture/category.png"))); // NOI18N
        MnDiet.setText("Diet Pasien");
        MnDiet.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        MnDiet.setHorizontalTextPosition(javax.swing.SwingConstants.RIGHT);
        MnDiet.setIconTextGap(5);
        MnDiet.setName("MnDiet"); // NOI18N
        MnDiet.setPreferredSize(new java.awt.Dimension(190, 26));
        MnDiet.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                MnDietActionPerformed(evt);
            }
        });
        MnInputData.add(MnDiet);

        MnDataHAIs.setBackground(new java.awt.Color(255, 255, 255));
        MnDataHAIs.setFont(new java.awt.Font("Tahoma", 0, 11)); // NOI18N
        MnDataHAIs.setIcon(new javax.swing.ImageIcon(getClass().getResource("/picture/category.png"))); // NOI18N
        MnDataHAIs.setText("Data HAIs");
        MnDataHAIs.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        MnDataHAIs.setHorizontalTextPosition(javax.swing.SwingConstants.RIGHT);
        MnDataHAIs.setIconTextGap(5);
        MnDataHAIs.setName("MnDataHAIs"); // NOI18N
        MnDataHAIs.setPreferredSize(new java.awt.Dimension(190, 26));
        MnDataHAIs.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                MnDataHAIsBtnPrintActionPerformed(evt);
            }
        });
        MnInputData.add(MnDataHAIs);

        MnSensusParu.setBackground(new java.awt.Color(255, 255, 255));
        MnSensusParu.setFont(new java.awt.Font("Tahoma", 0, 11)); // NOI18N
        MnSensusParu.setIcon(new javax.swing.ImageIcon(getClass().getResource("/picture/category.png"))); // NOI18N
        MnSensusParu.setText("Sensus Pasien Paru");
        MnSensusParu.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        MnSensusParu.setHorizontalTextPosition(javax.swing.SwingConstants.RIGHT);
        MnSensusParu.setIconTextGap(5);
        MnSensusParu.setName("MnSensusParu"); // NOI18N
        MnSensusParu.setPreferredSize(new java.awt.Dimension(190, 26));
        MnSensusParu.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                MnSensusParuBtnPrintActionPerformed(evt);
            }
        });
        MnInputData.add(MnSensusParu);

        jPopupMenu1.add(MnInputData);

        MnRekamMedis.setBackground(new java.awt.Color(248, 253, 243));
        MnRekamMedis.setIcon(new javax.swing.ImageIcon(getClass().getResource("/picture/category.png"))); // NOI18N
        MnRekamMedis.setText("Data Rekam Medis");
        MnRekamMedis.setFont(new java.awt.Font("Tahoma", 0, 11)); // NOI18N
        MnRekamMedis.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        MnRekamMedis.setHorizontalTextPosition(javax.swing.SwingConstants.RIGHT);
        MnRekamMedis.setIconTextGap(5);
        MnRekamMedis.setName("MnRekamMedis"); // NOI18N
        MnRekamMedis.setOpaque(true);
        MnRekamMedis.setPreferredSize(new java.awt.Dimension(220, 26));

        MnSuratTindakanDokter.setBackground(new java.awt.Color(255, 255, 255));
        MnSuratTindakanDokter.setFont(new java.awt.Font("Tahoma", 0, 11)); // NOI18N
        MnSuratTindakanDokter.setIcon(new javax.swing.ImageIcon(getClass().getResource("/picture/category.png"))); // NOI18N
        MnSuratTindakanDokter.setText("Surat Tindakan Kedokteran");
        MnSuratTindakanDokter.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        MnSuratTindakanDokter.setHorizontalTextPosition(javax.swing.SwingConstants.RIGHT);
        MnSuratTindakanDokter.setIconTextGap(5);
        MnSuratTindakanDokter.setName("MnSuratTindakanDokter"); // NOI18N
        MnSuratTindakanDokter.setPreferredSize(new java.awt.Dimension(300, 26));
        MnSuratTindakanDokter.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                MnSuratTindakanDokterActionPerformed(evt);
            }
        });
        MnRekamMedis.add(MnSuratTindakanDokter);

        MnDataTriaseIGD.setBackground(new java.awt.Color(255, 255, 254));
        MnDataTriaseIGD.setFont(new java.awt.Font("Tahoma", 0, 11)); // NOI18N
        MnDataTriaseIGD.setForeground(new java.awt.Color(50, 50, 50));
        MnDataTriaseIGD.setIcon(new javax.swing.ImageIcon(getClass().getResource("/picture/category.png"))); // NOI18N
        MnDataTriaseIGD.setText("Triase Gawat Darurat");
        MnDataTriaseIGD.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        MnDataTriaseIGD.setHorizontalTextPosition(javax.swing.SwingConstants.RIGHT);
        MnDataTriaseIGD.setIconTextGap(5);
        MnDataTriaseIGD.setName("MnDataTriaseIGD"); // NOI18N
        MnDataTriaseIGD.setPreferredSize(new java.awt.Dimension(300, 26));
        MnDataTriaseIGD.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                MnDataTriaseIGDActionPerformed(evt);
            }
        });
        MnRekamMedis.add(MnDataTriaseIGD);

        MnPeniliaianAwalKeperawatanRalan.setBackground(new java.awt.Color(255, 255, 254));
        MnPeniliaianAwalKeperawatanRalan.setFont(new java.awt.Font("Tahoma", 0, 11)); // NOI18N
        MnPeniliaianAwalKeperawatanRalan.setForeground(new java.awt.Color(50, 50, 50));
        MnPeniliaianAwalKeperawatanRalan.setIcon(new javax.swing.ImageIcon(getClass().getResource("/picture/category.png"))); // NOI18N
        MnPeniliaianAwalKeperawatanRalan.setText("Peniliaian Awal Keperawatan (Assesmen)");
        MnPeniliaianAwalKeperawatanRalan.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        MnPeniliaianAwalKeperawatanRalan.setHorizontalTextPosition(javax.swing.SwingConstants.RIGHT);
        MnPeniliaianAwalKeperawatanRalan.setIconTextGap(5);
        MnPeniliaianAwalKeperawatanRalan.setName("MnPeniliaianAwalKeperawatanRalan"); // NOI18N
        MnPeniliaianAwalKeperawatanRalan.setPreferredSize(new java.awt.Dimension(300, 26));
        MnPeniliaianAwalKeperawatanRalan.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                MnPeniliaianAwalKeperawatanRalanActionPerformed(evt);
            }
        });
        MnRekamMedis.add(MnPeniliaianAwalKeperawatanRalan);

        MnPeniliaianAwalMedisKebidananRalan.setBackground(new java.awt.Color(255, 255, 254));
        MnPeniliaianAwalMedisKebidananRalan.setFont(new java.awt.Font("Tahoma", 0, 11)); // NOI18N
        MnPeniliaianAwalMedisKebidananRalan.setForeground(new java.awt.Color(50, 50, 50));
        MnPeniliaianAwalMedisKebidananRalan.setIcon(new javax.swing.ImageIcon(getClass().getResource("/picture/category.png"))); // NOI18N
        MnPeniliaianAwalMedisKebidananRalan.setText("Penilaian Awal Medis Kebidanan & Kandungan");
        MnPeniliaianAwalMedisKebidananRalan.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        MnPeniliaianAwalMedisKebidananRalan.setHorizontalTextPosition(javax.swing.SwingConstants.RIGHT);
        MnPeniliaianAwalMedisKebidananRalan.setIconTextGap(5);
        MnPeniliaianAwalMedisKebidananRalan.setName("MnPeniliaianAwalMedisKebidananRalan"); // NOI18N
        MnPeniliaianAwalMedisKebidananRalan.setPreferredSize(new java.awt.Dimension(300, 26));
        MnPeniliaianAwalMedisKebidananRalan.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                MnPeniliaianAwalMedisKebidananRalanActionPerformed(evt);
            }
        });
        MnRekamMedis.add(MnPeniliaianAwalMedisKebidananRalan);

        ppRiwayat.setBackground(new java.awt.Color(255, 255, 255));
        ppRiwayat.setFont(new java.awt.Font("Tahoma", 0, 11)); // NOI18N
        ppRiwayat.setIcon(new javax.swing.ImageIcon(getClass().getResource("/picture/category.png"))); // NOI18N
        ppRiwayat.setText("Riwayat Perawatan");
        ppRiwayat.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        ppRiwayat.setHorizontalTextPosition(javax.swing.SwingConstants.RIGHT);
        ppRiwayat.setIconTextGap(5);
        ppRiwayat.setName("ppRiwayat"); // NOI18N
        ppRiwayat.setPreferredSize(new java.awt.Dimension(300, 26));
        ppRiwayat.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                ppRiwayatBtnPrintActionPerformed(evt);
            }
        });
        MnRekamMedis.add(ppRiwayat);

        ppCekPaseinMati.setBackground(new java.awt.Color(255, 255, 255));
        ppCekPaseinMati.setFont(new java.awt.Font("Tahoma", 0, 11)); // NOI18N
        ppCekPaseinMati.setIcon(new javax.swing.ImageIcon(getClass().getResource("/picture/category.png"))); // NOI18N
        ppCekPaseinMati.setText("Cek Pasien Meninggal");
        ppCekPaseinMati.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        ppCekPaseinMati.setHorizontalTextPosition(javax.swing.SwingConstants.RIGHT);
        ppCekPaseinMati.setIconTextGap(5);
        ppCekPaseinMati.setName("ppCekPaseinMati"); // NOI18N
        ppCekPaseinMati.setPreferredSize(new java.awt.Dimension(300, 26));
        ppCekPaseinMati.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                ppCekPaseinMatiBtnPrintActionPerformed(evt);
            }
        });
        MnRekamMedis.add(ppCekPaseinMati);

        ppPerawatanCorona.setBackground(new java.awt.Color(255, 255, 254));
        ppPerawatanCorona.setFont(new java.awt.Font("Tahoma", 0, 11)); // NOI18N
        ppPerawatanCorona.setIcon(new javax.swing.ImageIcon(getClass().getResource("/picture/category.png"))); // NOI18N
        ppPerawatanCorona.setText("Perawatan Pasien Corona INACBG");
        ppPerawatanCorona.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        ppPerawatanCorona.setHorizontalTextPosition(javax.swing.SwingConstants.RIGHT);
        ppPerawatanCorona.setIconTextGap(5);
        ppPerawatanCorona.setName("ppPerawatanCorona"); // NOI18N
        ppPerawatanCorona.setPreferredSize(new java.awt.Dimension(300, 26));
        ppPerawatanCorona.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                ppPerawatanCoronaBtnPrintActionPerformed(evt);
            }
        });
        MnRekamMedis.add(ppPerawatanCorona);

        jPopupMenu1.add(MnRekamMedis);

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

        MnDiagnosa.setBackground(new java.awt.Color(255, 255, 255));
        MnDiagnosa.setFont(new java.awt.Font("Tahoma", 0, 11)); // NOI18N
        MnDiagnosa.setIcon(new javax.swing.ImageIcon(getClass().getResource("/picture/category.png"))); // NOI18N
        MnDiagnosa.setText("Diagnosa Pasien");
        MnDiagnosa.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        MnDiagnosa.setHorizontalTextPosition(javax.swing.SwingConstants.RIGHT);
        MnDiagnosa.setIconTextGap(5);
        MnDiagnosa.setName("MnDiagnosa"); // NOI18N
        MnDiagnosa.setPreferredSize(new java.awt.Dimension(220, 26));
        MnDiagnosa.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                MnDiagnosaActionPerformed(evt);
            }
        });
        jPopupMenu1.add(MnDiagnosa);

        MnLihatSEP.setBackground(new java.awt.Color(255, 255, 255));
        MnLihatSEP.setFont(new java.awt.Font("Tahoma", 0, 11)); // NOI18N
        MnLihatSEP.setIcon(new javax.swing.ImageIcon(getClass().getResource("/picture/category.png"))); // NOI18N
        MnLihatSEP.setText("Lihat No. SEP");
        MnLihatSEP.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        MnLihatSEP.setHorizontalTextPosition(javax.swing.SwingConstants.RIGHT);
        MnLihatSEP.setIconTextGap(5);
        MnLihatSEP.setName("MnLihatSEP"); // NOI18N
        MnLihatSEP.setPreferredSize(new java.awt.Dimension(220, 26));
        MnLihatSEP.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                MnLihatSEPBtnPrintActionPerformed(evt);
            }
        });
        jPopupMenu1.add(MnLihatSEP);

        MnFormulirKlaim.setBackground(new java.awt.Color(255, 255, 255));
        MnFormulirKlaim.setFont(new java.awt.Font("Tahoma", 0, 11)); // NOI18N
        MnFormulirKlaim.setIcon(new javax.swing.ImageIcon(getClass().getResource("/picture/category.png"))); // NOI18N
        MnFormulirKlaim.setText("Formulir Klaim Pasien");
        MnFormulirKlaim.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        MnFormulirKlaim.setHorizontalTextPosition(javax.swing.SwingConstants.RIGHT);
        MnFormulirKlaim.setIconTextGap(5);
        MnFormulirKlaim.setName("MnFormulirKlaim"); // NOI18N
        MnFormulirKlaim.setPreferredSize(new java.awt.Dimension(220, 26));
        MnFormulirKlaim.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                MnFormulirKlaimBtnPrintActionPerformed(evt);
            }
        });
        jPopupMenu1.add(MnFormulirKlaim);

        MnLembarStatusPasien.setBackground(new java.awt.Color(255, 255, 255));
        MnLembarStatusPasien.setFont(new java.awt.Font("Tahoma", 0, 11)); // NOI18N
        MnLembarStatusPasien.setIcon(new javax.swing.ImageIcon(getClass().getResource("/picture/category.png"))); // NOI18N
        MnLembarStatusPasien.setText("Lembar Status Pasien Baru");
        MnLembarStatusPasien.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        MnLembarStatusPasien.setHorizontalTextPosition(javax.swing.SwingConstants.RIGHT);
        MnLembarStatusPasien.setIconTextGap(5);
        MnLembarStatusPasien.setName("MnLembarStatusPasien"); // NOI18N
        MnLembarStatusPasien.setPreferredSize(new java.awt.Dimension(220, 26));
        MnLembarStatusPasien.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                MnLembarStatusPasienBtnPrintActionPerformed(evt);
            }
        });
        jPopupMenu1.add(MnLembarStatusPasien);

        MnStatusPasienAllKunjungan.setBackground(new java.awt.Color(255, 255, 255));
        MnStatusPasienAllKunjungan.setFont(new java.awt.Font("Tahoma", 0, 11)); // NOI18N
        MnStatusPasienAllKunjungan.setIcon(new javax.swing.ImageIcon(getClass().getResource("/picture/category.png"))); // NOI18N
        MnStatusPasienAllKunjungan.setText("Status Pasien Semua Kunjungan");
        MnStatusPasienAllKunjungan.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        MnStatusPasienAllKunjungan.setHorizontalTextPosition(javax.swing.SwingConstants.RIGHT);
        MnStatusPasienAllKunjungan.setIconTextGap(5);
        MnStatusPasienAllKunjungan.setName("MnStatusPasienAllKunjungan"); // NOI18N
        MnStatusPasienAllKunjungan.setPreferredSize(new java.awt.Dimension(220, 26));
        MnStatusPasienAllKunjungan.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                MnStatusPasienAllKunjunganBtnPrintActionPerformed(evt);
            }
        });
        jPopupMenu1.add(MnStatusPasienAllKunjungan);

        ppCatatanPasien.setBackground(new java.awt.Color(255, 255, 255));
        ppCatatanPasien.setFont(new java.awt.Font("Tahoma", 0, 11)); // NOI18N
        ppCatatanPasien.setIcon(new javax.swing.ImageIcon(getClass().getResource("/picture/category.png"))); // NOI18N
        ppCatatanPasien.setText("Catatan Untuk Pasien");
        ppCatatanPasien.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        ppCatatanPasien.setHorizontalTextPosition(javax.swing.SwingConstants.RIGHT);
        ppCatatanPasien.setIconTextGap(5);
        ppCatatanPasien.setName("ppCatatanPasien"); // NOI18N
        ppCatatanPasien.setPreferredSize(new java.awt.Dimension(220, 26));
        ppCatatanPasien.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                ppCatatanPasienBtnPrintActionPerformed(evt);
            }
        });
        jPopupMenu1.add(ppCatatanPasien);

        WindowGantiDokter.setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);
        WindowGantiDokter.setName("WindowGantiDokter"); // NOI18N
        WindowGantiDokter.setUndecorated(true);
        WindowGantiDokter.setResizable(false);

        internalFrame3.setBorder(javax.swing.BorderFactory.createTitledBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 153, 255), 3), "::[ Ganti Dokter Poli ]::", javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Tahoma", 1, 12))); // NOI18N
        internalFrame3.setName("internalFrame3"); // NOI18N
        internalFrame3.setWarnaBawah(new java.awt.Color(245, 250, 240));
        internalFrame3.setLayout(null);

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
        internalFrame3.add(BtnCloseIn1);
        BtnCloseIn1.setBounds(510, 30, 100, 30);

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
        internalFrame3.add(BtnSimpan1);
        BtnSimpan1.setBounds(405, 30, 100, 30);

        jLabel13.setForeground(new java.awt.Color(0, 0, 0));
        jLabel13.setText("Dr Dituju :");
        jLabel13.setName("jLabel13"); // NOI18N
        internalFrame3.add(jLabel13);
        jLabel13.setBounds(0, 32, 77, 23);

        kddokter.setEditable(false);
        kddokter.setForeground(new java.awt.Color(0, 0, 0));
        kddokter.setHighlighter(null);
        kddokter.setName("kddokter"); // NOI18N
        kddokter.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                kddokterKeyPressed(evt);
            }
        });
        internalFrame3.add(kddokter);
        kddokter.setBounds(81, 32, 90, 23);

        TDokter.setEditable(false);
        TDokter.setForeground(new java.awt.Color(0, 0, 0));
        TDokter.setName("TDokter"); // NOI18N
        internalFrame3.add(TDokter);
        TDokter.setBounds(174, 32, 190, 23);

        btnCariDokter.setForeground(new java.awt.Color(0, 0, 0));
        btnCariDokter.setIcon(new javax.swing.ImageIcon(getClass().getResource("/picture/download24.png"))); // NOI18N
        btnCariDokter.setMnemonic('7');
        btnCariDokter.setToolTipText("ALt+7");
        btnCariDokter.setName("btnCariDokter"); // NOI18N
        btnCariDokter.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnCariDokterActionPerformed(evt);
            }
        });
        internalFrame3.add(btnCariDokter);
        btnCariDokter.setBounds(366, 32, 28, 23);

        WindowGantiDokter.getContentPane().add(internalFrame3, java.awt.BorderLayout.CENTER);

        WindowGantiPoli.setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);
        WindowGantiPoli.setName("WindowGantiPoli"); // NOI18N
        WindowGantiPoli.setUndecorated(true);
        WindowGantiPoli.setResizable(false);

        internalFrame5.setBorder(javax.swing.BorderFactory.createTitledBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 153, 255), 3), "::[ Ganti Poliklinik ]::", javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Tahoma", 1, 12))); // NOI18N
        internalFrame5.setName("internalFrame5"); // NOI18N
        internalFrame5.setWarnaBawah(new java.awt.Color(240, 245, 235));
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

        jLabel18.setForeground(new java.awt.Color(0, 0, 0));
        jLabel18.setText("Poli Dituju :");
        jLabel18.setName("jLabel18"); // NOI18N
        internalFrame5.add(jLabel18);
        jLabel18.setBounds(0, 32, 77, 23);

        kdpoli.setEditable(false);
        kdpoli.setForeground(new java.awt.Color(0, 0, 0));
        kdpoli.setHighlighter(null);
        kdpoli.setName("kdpoli"); // NOI18N
        kdpoli.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                kdpoliKeyPressed(evt);
            }
        });
        internalFrame5.add(kdpoli);
        kdpoli.setBounds(81, 32, 60, 23);

        nmpoli.setEditable(false);
        nmpoli.setForeground(new java.awt.Color(0, 0, 0));
        nmpoli.setName("nmpoli"); // NOI18N
        internalFrame5.add(nmpoli);
        nmpoli.setBounds(144, 32, 220, 23);

        btnCariPoli.setForeground(new java.awt.Color(0, 0, 0));
        btnCariPoli.setIcon(new javax.swing.ImageIcon(getClass().getResource("/picture/download24.png"))); // NOI18N
        btnCariPoli.setMnemonic('7');
        btnCariPoli.setToolTipText("ALt+7");
        btnCariPoli.setName("btnCariPoli"); // NOI18N
        btnCariPoli.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnCariPoliActionPerformed(evt);
            }
        });
        internalFrame5.add(btnCariPoli);
        btnCariPoli.setBounds(366, 32, 28, 23);

        WindowGantiPoli.getContentPane().add(internalFrame5, java.awt.BorderLayout.CENTER);

        WindowCaraBayar.setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);
        WindowCaraBayar.setName("WindowCaraBayar"); // NOI18N
        WindowCaraBayar.setUndecorated(true);
        WindowCaraBayar.setResizable(false);

        internalFrame6.setBorder(javax.swing.BorderFactory.createTitledBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 153, 255), 3), "::[ Ganti Jenis Bayar ]::", javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Tahoma", 1, 12))); // NOI18N
        internalFrame6.setName("internalFrame6"); // NOI18N
        internalFrame6.setWarnaBawah(new java.awt.Color(245, 250, 240));
        internalFrame6.setLayout(null);

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
        internalFrame6.add(BtnCloseIn5);
        BtnCloseIn5.setBounds(510, 30, 100, 30);

        BtnSimpan5.setForeground(new java.awt.Color(0, 0, 0));
        BtnSimpan5.setIcon(new javax.swing.ImageIcon(getClass().getResource("/picture/save-16x16.png"))); // NOI18N
        BtnSimpan5.setMnemonic('S');
        BtnSimpan5.setText("Simpan");
        BtnSimpan5.setToolTipText("Alt+S");
        BtnSimpan5.setName("BtnSimpan5"); // NOI18N
        BtnSimpan5.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                BtnSimpan5ActionPerformed(evt);
            }
        });
        internalFrame6.add(BtnSimpan5);
        BtnSimpan5.setBounds(405, 30, 100, 30);

        jLabel19.setForeground(new java.awt.Color(0, 0, 0));
        jLabel19.setText("Jenis Bayar :");
        jLabel19.setName("jLabel19"); // NOI18N
        internalFrame6.add(jLabel19);
        jLabel19.setBounds(0, 32, 77, 23);

        kdpenjab.setEditable(false);
        kdpenjab.setForeground(new java.awt.Color(0, 0, 0));
        kdpenjab.setHighlighter(null);
        kdpenjab.setName("kdpenjab"); // NOI18N
        kdpenjab.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                kdpenjabKeyPressed(evt);
            }
        });
        internalFrame6.add(kdpenjab);
        kdpenjab.setBounds(81, 32, 80, 23);

        nmpenjab.setEditable(false);
        nmpenjab.setForeground(new java.awt.Color(0, 0, 0));
        nmpenjab.setName("nmpenjab"); // NOI18N
        internalFrame6.add(nmpenjab);
        nmpenjab.setBounds(164, 32, 200, 23);

        btnBayar.setForeground(new java.awt.Color(0, 0, 0));
        btnBayar.setIcon(new javax.swing.ImageIcon(getClass().getResource("/picture/download24.png"))); // NOI18N
        btnBayar.setMnemonic('7');
        btnBayar.setToolTipText("ALt+7");
        btnBayar.setName("btnBayar"); // NOI18N
        btnBayar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnBayarActionPerformed(evt);
            }
        });
        internalFrame6.add(btnBayar);
        btnBayar.setBounds(366, 32, 28, 23);

        WindowCaraBayar.getContentPane().add(internalFrame6, java.awt.BorderLayout.CENTER);

        WindowPasienBooking.setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);
        WindowPasienBooking.setName("WindowPasienBooking"); // NOI18N
        WindowPasienBooking.setUndecorated(true);
        WindowPasienBooking.setResizable(false);

        internalFrame7.setBorder(javax.swing.BorderFactory.createTitledBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 153, 255), 3), "::[ Pasien Booking ]::", javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Tahoma", 1, 12))); // NOI18N
        internalFrame7.setName("internalFrame7"); // NOI18N
        internalFrame7.setWarnaBawah(new java.awt.Color(240, 245, 235));
        internalFrame7.setLayout(null);

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
        internalFrame7.add(BtnCloseIn6);
        BtnCloseIn6.setBounds(460, 200, 100, 30);

        BtnSimpan6.setForeground(new java.awt.Color(0, 0, 0));
        BtnSimpan6.setIcon(new javax.swing.ImageIcon(getClass().getResource("/picture/file-edit-16x16.png"))); // NOI18N
        BtnSimpan6.setMnemonic('R');
        BtnSimpan6.setText("Registrasikan Pasien");
        BtnSimpan6.setToolTipText("Alt+R");
        BtnSimpan6.setName("BtnSimpan6"); // NOI18N
        BtnSimpan6.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                BtnSimpan6ActionPerformed(evt);
            }
        });
        internalFrame7.add(BtnSimpan6);
        BtnSimpan6.setBounds(298, 200, 158, 30);

        jLabel20.setForeground(new java.awt.Color(0, 0, 0));
        jLabel20.setText("Cara Bayar Pasien : ");
        jLabel20.setName("jLabel20"); // NOI18N
        internalFrame7.add(jLabel20);
        jLabel20.setBounds(0, 80, 130, 23);

        kdpoli1.setEditable(false);
        kdpoli1.setForeground(new java.awt.Color(0, 0, 0));
        kdpoli1.setHighlighter(null);
        kdpoli1.setName("kdpoli1"); // NOI18N
        kdpoli1.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                kdpoli1KeyPressed(evt);
            }
        });
        internalFrame7.add(kdpoli1);
        kdpoli1.setBounds(132, 140, 70, 23);

        nmpoli1.setEditable(false);
        nmpoli1.setForeground(new java.awt.Color(0, 0, 0));
        nmpoli1.setName("nmpoli1"); // NOI18N
        internalFrame7.add(nmpoli1);
        nmpoli1.setBounds(205, 140, 270, 23);

        jLabel21.setForeground(new java.awt.Color(0, 0, 0));
        jLabel21.setText("No. Rawat : ");
        jLabel21.setName("jLabel21"); // NOI18N
        internalFrame7.add(jLabel21);
        jLabel21.setBounds(225, 110, 70, 23);

        kdboking.setForeground(new java.awt.Color(0, 0, 204));
        kdboking.setFont(new java.awt.Font("Tahoma", 1, 11)); // NOI18N
        kdboking.setHighlighter(null);
        kdboking.setName("kdboking"); // NOI18N
        kdboking.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyTyped(java.awt.event.KeyEvent evt) {
                kdbokingKeyTyped(evt);
            }
            public void keyPressed(java.awt.event.KeyEvent evt) {
                kdbokingKeyPressed(evt);
            }
        });
        internalFrame7.add(kdboking);
        kdboking.setBounds(132, 20, 145, 23);

        jLabel22.setForeground(new java.awt.Color(0, 0, 0));
        jLabel22.setText("Data Pasien : ");
        jLabel22.setName("jLabel22"); // NOI18N
        internalFrame7.add(jLabel22);
        jLabel22.setBounds(0, 50, 130, 23);

        jLabel23.setForeground(new java.awt.Color(0, 0, 0));
        jLabel23.setText("Poliklinik Tujuan : ");
        jLabel23.setName("jLabel23"); // NOI18N
        internalFrame7.add(jLabel23);
        jLabel23.setBounds(0, 140, 130, 23);

        jLabel24.setForeground(new java.awt.Color(0, 0, 0));
        jLabel24.setText("Rencana Tgl. Periksa : ");
        jLabel24.setName("jLabel24"); // NOI18N
        internalFrame7.add(jLabel24);
        jLabel24.setBounds(0, 110, 130, 23);

        norm.setEditable(false);
        norm.setForeground(new java.awt.Color(0, 0, 0));
        norm.setHighlighter(null);
        norm.setName("norm"); // NOI18N
        norm.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                normKeyPressed(evt);
            }
        });
        internalFrame7.add(norm);
        norm.setBounds(132, 50, 80, 23);

        nmpasien.setEditable(false);
        nmpasien.setForeground(new java.awt.Color(0, 0, 0));
        nmpasien.setHighlighter(null);
        nmpasien.setName("nmpasien"); // NOI18N
        nmpasien.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                nmpasienKeyPressed(evt);
            }
        });
        internalFrame7.add(nmpasien);
        nmpasien.setBounds(215, 50, 330, 23);

        kdpnj.setEditable(false);
        kdpnj.setForeground(new java.awt.Color(0, 0, 0));
        kdpnj.setHighlighter(null);
        kdpnj.setName("kdpnj"); // NOI18N
        kdpnj.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                kdpnjKeyPressed(evt);
            }
        });
        internalFrame7.add(kdpnj);
        kdpnj.setBounds(132, 80, 70, 23);

        nmpnj.setEditable(false);
        nmpnj.setForeground(new java.awt.Color(0, 0, 0));
        nmpnj.setName("nmpnj"); // NOI18N
        internalFrame7.add(nmpnj);
        nmpnj.setBounds(205, 80, 270, 23);

        jLabel25.setForeground(new java.awt.Color(0, 0, 0));
        jLabel25.setText("Dokter Terjadwal : ");
        jLabel25.setName("jLabel25"); // NOI18N
        internalFrame7.add(jLabel25);
        jLabel25.setBounds(0, 170, 130, 23);

        KdDokter.setEditable(false);
        KdDokter.setForeground(new java.awt.Color(0, 0, 0));
        KdDokter.setHighlighter(null);
        KdDokter.setName("KdDokter"); // NOI18N
        internalFrame7.add(KdDokter);
        KdDokter.setBounds(132, 170, 90, 23);

        NmDokter.setEditable(false);
        NmDokter.setForeground(new java.awt.Color(0, 0, 0));
        NmDokter.setHighlighter(null);
        NmDokter.setName("NmDokter"); // NOI18N
        internalFrame7.add(NmDokter);
        NmDokter.setBounds(225, 170, 300, 23);

        BtnDokter.setForeground(new java.awt.Color(0, 0, 0));
        BtnDokter.setIcon(new javax.swing.ImageIcon(getClass().getResource("/picture/190.png"))); // NOI18N
        BtnDokter.setMnemonic('X');
        BtnDokter.setToolTipText("Alt+X");
        BtnDokter.setName("BtnDokter"); // NOI18N
        BtnDokter.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                BtnDokterActionPerformed(evt);
            }
        });
        BtnDokter.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                BtnDokterKeyPressed(evt);
            }
        });
        internalFrame7.add(BtnDokter);
        BtnDokter.setBounds(530, 170, 28, 23);

        jLabel26.setForeground(new java.awt.Color(0, 0, 0));
        jLabel26.setText("Kode Booking : ");
        jLabel26.setName("jLabel26"); // NOI18N
        internalFrame7.add(jLabel26);
        jLabel26.setBounds(0, 20, 130, 23);

        norwBoking.setEditable(false);
        norwBoking.setForeground(new java.awt.Color(0, 0, 0));
        norwBoking.setHighlighter(null);
        norwBoking.setName("norwBoking"); // NOI18N
        norwBoking.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                norwBokingKeyPressed(evt);
            }
        });
        internalFrame7.add(norwBoking);
        norwBoking.setBounds(298, 110, 177, 23);

        tglPeriksa.setEditable(false);
        tglPeriksa.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "11-02-2022" }));
        tglPeriksa.setDisplayFormat("dd-MM-yyyy");
        tglPeriksa.setName("tglPeriksa"); // NOI18N
        tglPeriksa.setOpaque(false);
        tglPeriksa.addItemListener(new java.awt.event.ItemListener() {
            public void itemStateChanged(java.awt.event.ItemEvent evt) {
                tglPeriksaItemStateChanged(evt);
            }
        });
        tglPeriksa.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                tglPeriksaMouseClicked(evt);
            }
        });
        tglPeriksa.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                tglPeriksaActionPerformed(evt);
            }
        });
        tglPeriksa.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                tglPeriksaKeyPressed(evt);
            }
        });
        internalFrame7.add(tglPeriksa);
        tglPeriksa.setBounds(132, 110, 90, 23);

        WindowPasienBooking.getContentPane().add(internalFrame7, java.awt.BorderLayout.CENTER);

        WindowPasienMati.setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);
        WindowPasienMati.setName("WindowPasienMati"); // NOI18N
        WindowPasienMati.setUndecorated(true);
        WindowPasienMati.setResizable(false);

        internalFrame4.setBorder(javax.swing.BorderFactory.createTitledBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 153, 255), 3), "::[ Daftar Pasien Meninggal  ]::", javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Tahoma", 1, 12))); // NOI18N
        internalFrame4.setName("internalFrame4"); // NOI18N
        internalFrame4.setWarnaBawah(new java.awt.Color(245, 250, 240));
        internalFrame4.setLayout(null);

        jPanel3.setName("jPanel3"); // NOI18N
        jPanel3.setOpaque(false);
        jPanel3.setLayout(new java.awt.BorderLayout(1, 1));

        panelisi1.setName("panelisi1"); // NOI18N
        panelisi1.setPreferredSize(new java.awt.Dimension(12, 42));
        panelisi1.setLayout(null);

        jLabel7.setForeground(new java.awt.Color(0, 0, 0));
        jLabel7.setText("Key Word :");
        jLabel7.setName("jLabel7"); // NOI18N
        jLabel7.setPreferredSize(new java.awt.Dimension(70, 23));
        panelisi1.add(jLabel7);
        jLabel7.setBounds(0, 10, 80, 23);

        TCari1.setForeground(new java.awt.Color(0, 0, 0));
        TCari1.setName("TCari1"); // NOI18N
        TCari1.setPreferredSize(new java.awt.Dimension(350, 23));
        TCari1.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                TCari1KeyPressed(evt);
            }
        });
        panelisi1.add(TCari1);
        TCari1.setBounds(85, 10, 320, 23);

        BtnCari1.setForeground(new java.awt.Color(0, 0, 0));
        BtnCari1.setIcon(new javax.swing.ImageIcon(getClass().getResource("/picture/accept.png"))); // NOI18N
        BtnCari1.setMnemonic('2');
        BtnCari1.setText("Tampilkan Data");
        BtnCari1.setToolTipText("Alt+2");
        BtnCari1.setName("BtnCari1"); // NOI18N
        BtnCari1.setPreferredSize(new java.awt.Dimension(150, 30));
        BtnCari1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                BtnCari1ActionPerformed(evt);
            }
        });
        BtnCari1.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                BtnCari1KeyPressed(evt);
            }
        });
        panelisi1.add(BtnCari1);
        BtnCari1.setBounds(410, 10, 130, 23);

        BtnKeluar1.setForeground(new java.awt.Color(0, 0, 0));
        BtnKeluar1.setIcon(new javax.swing.ImageIcon(getClass().getResource("/picture/exit.png"))); // NOI18N
        BtnKeluar1.setMnemonic('K');
        BtnKeluar1.setText("Keluar");
        BtnKeluar1.setToolTipText("Alt+K");
        BtnKeluar1.setName("BtnKeluar1"); // NOI18N
        BtnKeluar1.setPreferredSize(new java.awt.Dimension(100, 30));
        BtnKeluar1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                BtnKeluar1ActionPerformed(evt);
            }
        });
        BtnKeluar1.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                BtnKeluar1KeyPressed(evt);
            }
        });
        panelisi1.add(BtnKeluar1);
        BtnKeluar1.setBounds(665, 10, 80, 23);

        BtnAll1.setForeground(new java.awt.Color(0, 0, 0));
        BtnAll1.setIcon(new javax.swing.ImageIcon(getClass().getResource("/picture/Search-16x16.png"))); // NOI18N
        BtnAll1.setMnemonic('M');
        BtnAll1.setText("Semua Data");
        BtnAll1.setToolTipText("Alt+M");
        BtnAll1.setName("BtnAll1"); // NOI18N
        BtnAll1.setPreferredSize(new java.awt.Dimension(120, 30));
        BtnAll1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                BtnAll1ActionPerformed(evt);
            }
        });
        BtnAll1.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                BtnAll1KeyPressed(evt);
            }
        });
        panelisi1.add(BtnAll1);
        BtnAll1.setBounds(545, 10, 110, 23);

        jPanel3.add(panelisi1, java.awt.BorderLayout.PAGE_END);

        Scroll2.setToolTipText("");
        Scroll2.setComponentPopupMenu(jPopupMenu1);
        Scroll2.setName("Scroll2"); // NOI18N
        Scroll2.setOpaque(true);

        tbPasienMati.setAutoCreateRowSorter(true);
        tbPasienMati.setToolTipText("Klik pada nama pasien untuk melihat datanya lebih detail");
        tbPasienMati.setComponentPopupMenu(jPopupMenu1);
        tbPasienMati.setName("tbPasienMati"); // NOI18N
        tbPasienMati.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                tbPasienMatiMouseClicked(evt);
            }
        });
        tbPasienMati.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                tbPasienMatiKeyPressed(evt);
            }
        });
        Scroll2.setViewportView(tbPasienMati);

        jPanel3.add(Scroll2, java.awt.BorderLayout.CENTER);

        panelisi2.setName("panelisi2"); // NOI18N
        panelisi2.setPreferredSize(new java.awt.Dimension(12, 190));
        panelisi2.setLayout(null);

        jLabel8.setForeground(new java.awt.Color(0, 0, 0));
        jLabel8.setText("Pasien :");
        jLabel8.setName("jLabel8"); // NOI18N
        jLabel8.setPreferredSize(new java.awt.Dimension(70, 23));
        panelisi2.add(jLabel8);
        jLabel8.setBounds(0, 8, 100, 23);

        jLabel9.setForeground(new java.awt.Color(0, 0, 0));
        jLabel9.setText("Tgl. Meninggal :");
        jLabel9.setName("jLabel9"); // NOI18N
        jLabel9.setPreferredSize(new java.awt.Dimension(70, 23));
        panelisi2.add(jLabel9);
        jLabel9.setBounds(0, 37, 100, 23);

        rmMati.setEditable(false);
        rmMati.setForeground(new java.awt.Color(0, 0, 0));
        rmMati.setName("rmMati"); // NOI18N
        rmMati.setPreferredSize(new java.awt.Dimension(350, 23));
        rmMati.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                rmMatiKeyPressed(evt);
            }
        });
        panelisi2.add(rmMati);
        rmMati.setBounds(105, 8, 80, 23);

        nmpxMati.setEditable(false);
        nmpxMati.setForeground(new java.awt.Color(0, 0, 0));
        nmpxMati.setName("nmpxMati"); // NOI18N
        nmpxMati.setPreferredSize(new java.awt.Dimension(350, 23));
        nmpxMati.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                nmpxMatiKeyPressed(evt);
            }
        });
        panelisi2.add(nmpxMati);
        nmpxMati.setBounds(190, 8, 380, 23);

        tglMati.setEditable(false);
        tglMati.setForeground(new java.awt.Color(0, 0, 0));
        tglMati.setName("tglMati"); // NOI18N
        tglMati.setPreferredSize(new java.awt.Dimension(350, 23));
        tglMati.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                tglMatiKeyPressed(evt);
            }
        });
        panelisi2.add(tglMati);
        tglMati.setBounds(105, 37, 240, 23);

        jLabel11.setForeground(new java.awt.Color(0, 0, 0));
        jLabel11.setText("Jam :");
        jLabel11.setName("jLabel11"); // NOI18N
        jLabel11.setPreferredSize(new java.awt.Dimension(70, 23));
        panelisi2.add(jLabel11);
        jLabel11.setBounds(350, 37, 30, 23);

        jamMati.setEditable(false);
        jamMati.setForeground(new java.awt.Color(0, 0, 0));
        jamMati.setName("jamMati"); // NOI18N
        jamMati.setPreferredSize(new java.awt.Dimension(350, 23));
        jamMati.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                jamMatiKeyPressed(evt);
            }
        });
        panelisi2.add(jamMati);
        jamMati.setBounds(385, 37, 110, 23);

        jLabel27.setForeground(new java.awt.Color(0, 0, 0));
        jLabel27.setText("Keterangan :");
        jLabel27.setName("jLabel27"); // NOI18N
        jLabel27.setPreferredSize(new java.awt.Dimension(70, 23));
        panelisi2.add(jLabel27);
        jLabel27.setBounds(0, 66, 100, 23);

        ketMati.setEditable(false);
        ketMati.setForeground(new java.awt.Color(0, 0, 0));
        ketMati.setName("ketMati"); // NOI18N
        ketMati.setPreferredSize(new java.awt.Dimension(350, 23));
        ketMati.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                ketMatiKeyPressed(evt);
            }
        });
        panelisi2.add(ketMati);
        ketMati.setBounds(110, 66, 800, 23);

        jLabel28.setForeground(new java.awt.Color(0, 0, 0));
        jLabel28.setText("Deskripsi (ICD-10) :");
        jLabel28.setName("jLabel28"); // NOI18N
        jLabel28.setPreferredSize(new java.awt.Dimension(70, 23));
        panelisi2.add(jLabel28);
        jLabel28.setBounds(0, 125, 210, 23);

        icdMati.setEditable(false);
        icdMati.setForeground(new java.awt.Color(0, 0, 0));
        icdMati.setName("icdMati"); // NOI18N
        icdMati.setPreferredSize(new java.awt.Dimension(350, 23));
        icdMati.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                icdMatiKeyPressed(evt);
            }
        });
        panelisi2.add(icdMati);
        icdMati.setBounds(215, 96, 70, 23);

        jLabel29.setForeground(new java.awt.Color(0, 0, 0));
        jLabel29.setText("Diagnosa Penyebab Kematian (ICD-10) :");
        jLabel29.setName("jLabel29"); // NOI18N
        jLabel29.setPreferredSize(new java.awt.Dimension(70, 23));
        panelisi2.add(jLabel29);
        jLabel29.setBounds(0, 96, 210, 23);

        Scroll3.setName("Scroll3"); // NOI18N
        Scroll3.setOpaque(true);

        desMati.setEditable(false);
        desMati.setColumns(20);
        desMati.setRows(5);
        desMati.setName("desMati"); // NOI18N
        desMati.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                desMatiKeyPressed(evt);
            }
        });
        Scroll3.setViewportView(desMati);

        panelisi2.add(Scroll3);
        Scroll3.setBounds(215, 125, 700, 60);

        jLabel30.setForeground(new java.awt.Color(0, 0, 0));
        jLabel30.setText("Tgl. Lahir :");
        jLabel30.setName("jLabel30"); // NOI18N
        jLabel30.setPreferredSize(new java.awt.Dimension(70, 23));
        panelisi2.add(jLabel30);
        jLabel30.setBounds(575, 8, 60, 23);

        tglLahrMati.setEditable(false);
        tglLahrMati.setForeground(new java.awt.Color(0, 0, 0));
        tglLahrMati.setName("tglLahrMati"); // NOI18N
        tglLahrMati.setPreferredSize(new java.awt.Dimension(350, 23));
        tglLahrMati.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                tglLahrMatiKeyPressed(evt);
            }
        });
        panelisi2.add(tglLahrMati);
        tglLahrMati.setBounds(640, 8, 270, 23);

        jLabel31.setForeground(new java.awt.Color(0, 0, 0));
        jLabel31.setText("Tempat Meninggal :");
        jLabel31.setName("jLabel31"); // NOI18N
        jLabel31.setPreferredSize(new java.awt.Dimension(70, 23));
        panelisi2.add(jLabel31);
        jLabel31.setBounds(497, 37, 100, 23);

        tmptMati.setEditable(false);
        tmptMati.setForeground(new java.awt.Color(0, 0, 0));
        tmptMati.setName("tmptMati"); // NOI18N
        tmptMati.setPreferredSize(new java.awt.Dimension(350, 23));
        tmptMati.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                tmptMatiKeyPressed(evt);
            }
        });
        panelisi2.add(tmptMati);
        tmptMati.setBounds(600, 37, 310, 23);

        jPanel3.add(panelisi2, java.awt.BorderLayout.PAGE_START);

        internalFrame4.add(jPanel3);
        jPanel3.setBounds(10, 20, 970, 430);

        WindowPasienMati.getContentPane().add(internalFrame4, java.awt.BorderLayout.CENTER);

        WindowRiwayatKunjungan.setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);
        WindowRiwayatKunjungan.setName("WindowRiwayatKunjungan"); // NOI18N
        WindowRiwayatKunjungan.setUndecorated(true);
        WindowRiwayatKunjungan.setResizable(false);

        internalFrame8.setBorder(javax.swing.BorderFactory.createTitledBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 153, 255), 3), "::[ Riwayat Kunjungan 7 Hari Yang Lalu ]::", javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Tahoma", 1, 12))); // NOI18N
        internalFrame8.setName("internalFrame8"); // NOI18N
        internalFrame8.setWarnaBawah(new java.awt.Color(245, 250, 240));
        internalFrame8.setLayout(null);

        BtnCloseIn7.setForeground(new java.awt.Color(0, 0, 0));
        BtnCloseIn7.setIcon(new javax.swing.ImageIcon(getClass().getResource("/picture/cross.png"))); // NOI18N
        BtnCloseIn7.setMnemonic('U');
        BtnCloseIn7.setText("Tutup");
        BtnCloseIn7.setToolTipText("Alt+U");
        BtnCloseIn7.setName("BtnCloseIn7"); // NOI18N
        BtnCloseIn7.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                BtnCloseIn7ActionPerformed(evt);
            }
        });
        internalFrame8.add(BtnCloseIn7);
        BtnCloseIn7.setBounds(780, 315, 80, 30);

        BtnLewati.setForeground(new java.awt.Color(0, 0, 0));
        BtnLewati.setIcon(new javax.swing.ImageIcon(getClass().getResource("/picture/kanan.png"))); // NOI18N
        BtnLewati.setMnemonic('L');
        BtnLewati.setText("Lewati");
        BtnLewati.setToolTipText("Alt+L");
        BtnLewati.setHorizontalTextPosition(javax.swing.SwingConstants.LEFT);
        BtnLewati.setName("BtnLewati"); // NOI18N
        BtnLewati.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                BtnLewatiActionPerformed(evt);
            }
        });
        internalFrame8.add(BtnLewati);
        BtnLewati.setBounds(695, 315, 80, 30);

        Scroll5.setBorder(javax.swing.BorderFactory.createTitledBorder(null, "[ Data Kunjungan Yang Tercatat ]", javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Tahoma", 0, 13))); // NOI18N
        Scroll5.setName("Scroll5"); // NOI18N
        Scroll5.setOpaque(true);

        tbRiwayatKunj.setToolTipText("");
        tbRiwayatKunj.setName("tbRiwayatKunj"); // NOI18N
        tbRiwayatKunj.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                tbRiwayatKunjMouseClicked(evt);
            }
        });
        tbRiwayatKunj.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                tbRiwayatKunjKeyPressed(evt);
            }
        });
        Scroll5.setViewportView(tbRiwayatKunj);

        internalFrame8.add(Scroll5);
        Scroll5.setBounds(15, 45, 850, 260);

        jLabel32.setForeground(new java.awt.Color(0, 51, 255));
        jLabel32.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        jLabel32.setText("Mohon dilengkapi kekurangan data rekam medis rawat jalan pasien yang terdaftar pada tabel dibawah ini..!!");
        jLabel32.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        jLabel32.setName("jLabel32"); // NOI18N
        internalFrame8.add(jLabel32);
        jLabel32.setBounds(15, 20, 790, 23);

        jLabel33.setForeground(new java.awt.Color(0, 0, 0));
        jLabel33.setText("Pasien Dipilih : ");
        jLabel33.setName("jLabel33"); // NOI18N
        internalFrame8.add(jLabel33);
        jLabel33.setBounds(20, 315, 80, 23);

        pasiendipilih.setEditable(false);
        pasiendipilih.setForeground(new java.awt.Color(0, 0, 0));
        pasiendipilih.setHighlighter(null);
        pasiendipilih.setName("pasiendipilih"); // NOI18N
        pasiendipilih.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                pasiendipilihKeyPressed(evt);
            }
        });
        internalFrame8.add(pasiendipilih);
        pasiendipilih.setBounds(100, 315, 440, 23);

        BtnRM.setForeground(new java.awt.Color(0, 0, 0));
        BtnRM.setIcon(new javax.swing.ImageIcon(getClass().getResource("/picture/addressbook-edit24.png"))); // NOI18N
        BtnRM.setMnemonic('R');
        BtnRM.setText("Isi Rekam Medis");
        BtnRM.setToolTipText("Alt+R");
        BtnRM.setName("BtnRM"); // NOI18N
        BtnRM.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                BtnRMActionPerformed(evt);
            }
        });
        internalFrame8.add(BtnRM);
        BtnRM.setBounds(547, 315, 142, 30);

        WindowRiwayatKunjungan.getContentPane().add(internalFrame8, java.awt.BorderLayout.CENTER);

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

        WindowRehabMedik.setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);
        WindowRehabMedik.setName("WindowRehabMedik"); // NOI18N
        WindowRehabMedik.setUndecorated(true);
        WindowRehabMedik.setResizable(false);

        internalFrame10.setBorder(javax.swing.BorderFactory.createTitledBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 153, 255), 3), "::[ Pilihan Rehabilitasi Medik ]::", javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Tahoma", 1, 12))); // NOI18N
        internalFrame10.setName("internalFrame10"); // NOI18N
        internalFrame10.setWarnaBawah(new java.awt.Color(240, 245, 235));
        internalFrame10.setLayout(null);

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
        internalFrame10.add(BtnCloseIn8);
        BtnCloseIn8.setBounds(410, 30, 100, 30);

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
        internalFrame10.add(BtnSimpan7);
        BtnSimpan7.setBounds(300, 30, 100, 30);

        jLabel34.setForeground(new java.awt.Color(0, 0, 0));
        jLabel34.setText("Jenis Rehabilitasi Medik : ");
        jLabel34.setName("jLabel34"); // NOI18N
        internalFrame10.add(jLabel34);
        jLabel34.setBounds(0, 32, 150, 23);

        cmbRM.setForeground(new java.awt.Color(0, 0, 0));
        cmbRM.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "-", "FISIOTERAPI", "OKUPASI TERAPI", "TERAPI WICARA" }));
        cmbRM.setName("cmbRM"); // NOI18N
        internalFrame10.add(cmbRM);
        cmbRM.setBounds(155, 32, 140, 23);

        WindowRehabMedik.getContentPane().add(internalFrame10, java.awt.BorderLayout.CENTER);

        Kd2.setHighlighter(null);
        Kd2.setName("Kd2"); // NOI18N
        Kd2.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                Kd2KeyPressed(evt);
            }
        });

        TKdPny.setName("TKdPny"); // NOI18N

        Tanggal.setHighlighter(null);
        Tanggal.setName("Tanggal"); // NOI18N

        Jam.setHighlighter(null);
        Jam.setName("Jam"); // NOI18N

        sepJkd.setHighlighter(null);
        sepJkd.setName("sepJkd"); // NOI18N
        sepJkd.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                sepJkdKeyPressed(evt);
            }
        });

        sepJkdigd.setHighlighter(null);
        sepJkdigd.setName("sepJkdigd"); // NOI18N
        sepJkdigd.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                sepJkdigdKeyPressed(evt);
            }
        });

        nmPasien.setHighlighter(null);
        nmPasien.setName("nmPasien"); // NOI18N
        nmPasien.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                nmPasienKeyPressed(evt);
            }
        });

        dataGZ.setHighlighter(null);
        dataGZ.setName("dataGZ"); // NOI18N
        dataGZ.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                dataGZKeyPressed(evt);
            }
        });

        TNoReg.setHighlighter(null);
        TNoReg.setName("TNoReg"); // NOI18N
        TNoReg.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                TNoRegKeyPressed(evt);
            }
        });

        cekKodeBoking.setHighlighter(null);
        cekKodeBoking.setName("cekKodeBoking"); // NOI18N
        cekKodeBoking.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                cekKodeBokingKeyPressed(evt);
            }
        });

        CmbJam.setForeground(new java.awt.Color(0, 0, 0));
        CmbJam.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "00", "01", "02", "03", "04", "05", "06", "07", "08", "09", "10", "11", "12", "13", "14", "15", "16", "17", "18", "19", "20", "21", "22", "23" }));
        CmbJam.setName("CmbJam"); // NOI18N
        CmbJam.setOpaque(false);
        CmbJam.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                CmbJamKeyPressed(evt);
            }
        });

        CmbMenit.setForeground(new java.awt.Color(0, 0, 0));
        CmbMenit.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "00", "01", "02", "03", "04", "05", "06", "07", "08", "09", "10", "11", "12", "13", "14", "15", "16", "17", "18", "19", "20", "21", "22", "23", "24", "25", "26", "27", "28", "29", "30", "31", "32", "33", "34", "35", "36", "37", "38", "39", "40", "41", "42", "43", "44", "45", "46", "47", "48", "49", "50", "51", "52", "53", "54", "55", "56", "57", "58", "59" }));
        CmbMenit.setName("CmbMenit"); // NOI18N
        CmbMenit.setOpaque(false);
        CmbMenit.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                CmbMenitKeyPressed(evt);
            }
        });

        CmbDetik.setForeground(new java.awt.Color(0, 0, 0));
        CmbDetik.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "00", "01", "02", "03", "04", "05", "06", "07", "08", "09", "10", "11", "12", "13", "14", "15", "16", "17", "18", "19", "20", "21", "22", "23", "24", "25", "26", "27", "28", "29", "30", "31", "32", "33", "34", "35", "36", "37", "38", "39", "40", "41", "42", "43", "44", "45", "46", "47", "48", "49", "50", "51", "52", "53", "54", "55", "56", "57", "58", "59" }));
        CmbDetik.setName("CmbDetik"); // NOI18N
        CmbDetik.setOpaque(false);
        CmbDetik.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                CmbDetikKeyPressed(evt);
            }
        });

        ChkWaktu.setBackground(new java.awt.Color(235, 255, 235));
        ChkWaktu.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(195, 215, 195)));
        ChkWaktu.setForeground(new java.awt.Color(0, 0, 0));
        ChkWaktu.setSelected(true);
        ChkWaktu.setBorderPainted(true);
        ChkWaktu.setBorderPaintedFlat(true);
        ChkWaktu.setFont(new java.awt.Font("Tahoma", 1, 11)); // NOI18N
        ChkWaktu.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        ChkWaktu.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        ChkWaktu.setName("ChkWaktu"); // NOI18N

        cekTerdaftar.setHighlighter(null);
        cekTerdaftar.setName("cekTerdaftar"); // NOI18N
        cekTerdaftar.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                cekTerdaftarKeyPressed(evt);
            }
        });

        cekPasien.setHighlighter(null);
        cekPasien.setName("cekPasien"); // NOI18N
        cekPasien.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                cekPasienKeyPressed(evt);
            }
        });

        TglKunRwt.setEditable(false);
        TglKunRwt.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "11-02-2022" }));
        TglKunRwt.setDisplayFormat("dd-MM-yyyy");
        TglKunRwt.setName("TglKunRwt"); // NOI18N
        TglKunRwt.setOpaque(false);
        TglKunRwt.addItemListener(new java.awt.event.ItemListener() {
            public void itemStateChanged(java.awt.event.ItemEvent evt) {
                TglKunRwtItemStateChanged(evt);
            }
        });
        TglKunRwt.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                TglKunRwtKeyPressed(evt);
            }
        });

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);
        setUndecorated(true);
        setResizable(false);

        internalFrame1.setBorder(javax.swing.BorderFactory.createTitledBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(240, 245, 235)), "::[ Rawat Jalan ]::", javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Tahoma", 1, 12))); // NOI18N
        internalFrame1.setName("internalFrame1"); // NOI18N
        internalFrame1.setLayout(new java.awt.BorderLayout(1, 1));

        jPanel2.setName("jPanel2"); // NOI18N
        jPanel2.setOpaque(false);
        jPanel2.setLayout(new java.awt.BorderLayout(1, 1));

        panelGlass6.setName("panelGlass6"); // NOI18N
        panelGlass6.setPreferredSize(new java.awt.Dimension(55, 55));
        panelGlass6.setLayout(new java.awt.FlowLayout(java.awt.FlowLayout.LEFT, 5, 9));

        jLabel6.setForeground(new java.awt.Color(0, 0, 0));
        jLabel6.setText("Key Word :");
        jLabel6.setName("jLabel6"); // NOI18N
        jLabel6.setPreferredSize(new java.awt.Dimension(70, 23));
        panelGlass6.add(jLabel6);

        TCari.setForeground(new java.awt.Color(0, 0, 0));
        TCari.setName("TCari"); // NOI18N
        TCari.setPreferredSize(new java.awt.Dimension(405, 23));
        TCari.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                TCariKeyPressed(evt);
            }
        });
        panelGlass6.add(TCari);

        BtnCari.setForeground(new java.awt.Color(0, 0, 0));
        BtnCari.setIcon(new javax.swing.ImageIcon(getClass().getResource("/picture/accept.png"))); // NOI18N
        BtnCari.setMnemonic('6');
        BtnCari.setText("Tampilkan Data");
        BtnCari.setToolTipText("Alt+6");
        BtnCari.setName("BtnCari"); // NOI18N
        BtnCari.setPreferredSize(new java.awt.Dimension(150, 23));
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
        panelGlass6.add(BtnCari);

        BtnAll.setForeground(new java.awt.Color(0, 0, 0));
        BtnAll.setIcon(new javax.swing.ImageIcon(getClass().getResource("/picture/Search-16x16.png"))); // NOI18N
        BtnAll.setMnemonic('M');
        BtnAll.setText("Semua Data");
        BtnAll.setToolTipText("Alt+M");
        BtnAll.setName("BtnAll"); // NOI18N
        BtnAll.setPreferredSize(new java.awt.Dimension(120, 23));
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
        panelGlass6.add(BtnAll);

        jLabel10.setForeground(new java.awt.Color(0, 0, 0));
        jLabel10.setText("Record :");
        jLabel10.setName("jLabel10"); // NOI18N
        jLabel10.setPreferredSize(new java.awt.Dimension(60, 30));
        panelGlass6.add(jLabel10);

        LCount.setForeground(new java.awt.Color(0, 0, 0));
        LCount.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        LCount.setText("0");
        LCount.setName("LCount"); // NOI18N
        LCount.setPreferredSize(new java.awt.Dimension(100, 30));
        panelGlass6.add(LCount);

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
        panelGlass6.add(BtnKeluar);

        jPanel2.add(panelGlass6, java.awt.BorderLayout.PAGE_END);

        panelGlass7.setName("panelGlass7"); // NOI18N
        panelGlass7.setPreferredSize(new java.awt.Dimension(44, 44));
        panelGlass7.setLayout(new java.awt.FlowLayout(java.awt.FlowLayout.LEFT, 5, 9));

        jLabel14.setForeground(new java.awt.Color(0, 0, 0));
        jLabel14.setText("Dokter :");
        jLabel14.setName("jLabel14"); // NOI18N
        jLabel14.setPreferredSize(new java.awt.Dimension(70, 23));
        panelGlass7.add(jLabel14);

        CrPtg.setEditable(false);
        CrPtg.setForeground(new java.awt.Color(0, 0, 0));
        CrPtg.setName("CrPtg"); // NOI18N
        CrPtg.setPreferredSize(new java.awt.Dimension(280, 23));
        panelGlass7.add(CrPtg);

        BtnSeek3.setForeground(new java.awt.Color(0, 0, 0));
        BtnSeek3.setIcon(new javax.swing.ImageIcon(getClass().getResource("/picture/download24.png"))); // NOI18N
        BtnSeek3.setMnemonic('4');
        BtnSeek3.setToolTipText("ALt+4");
        BtnSeek3.setName("BtnSeek3"); // NOI18N
        BtnSeek3.setPreferredSize(new java.awt.Dimension(28, 23));
        BtnSeek3.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                BtnSeek3ActionPerformed(evt);
            }
        });
        panelGlass7.add(BtnSeek3);

        jLabel16.setForeground(new java.awt.Color(0, 0, 0));
        jLabel16.setText("Poliklinik :");
        jLabel16.setName("jLabel16"); // NOI18N
        jLabel16.setPreferredSize(new java.awt.Dimension(60, 23));
        panelGlass7.add(jLabel16);

        CrPoli.setEditable(false);
        CrPoli.setForeground(new java.awt.Color(0, 0, 0));
        CrPoli.setName("CrPoli"); // NOI18N
        CrPoli.setPreferredSize(new java.awt.Dimension(280, 23));
        CrPoli.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                CrPoliKeyPressed(evt);
            }
        });
        panelGlass7.add(CrPoli);

        BtnSeek4.setForeground(new java.awt.Color(0, 0, 0));
        BtnSeek4.setIcon(new javax.swing.ImageIcon(getClass().getResource("/picture/download24.png"))); // NOI18N
        BtnSeek4.setMnemonic('5');
        BtnSeek4.setToolTipText("ALt+5");
        BtnSeek4.setName("BtnSeek4"); // NOI18N
        BtnSeek4.setPreferredSize(new java.awt.Dimension(28, 23));
        BtnSeek4.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                BtnSeek4ActionPerformed(evt);
            }
        });
        panelGlass7.add(BtnSeek4);

        jLabel94.setForeground(new java.awt.Color(0, 0, 0));
        jLabel94.setText("No. Rawat : ");
        jLabel94.setName("jLabel94"); // NOI18N
        jLabel94.setPreferredSize(new java.awt.Dimension(70, 23));
        panelGlass7.add(jLabel94);

        TNoRw.setEditable(false);
        TNoRw.setForeground(new java.awt.Color(0, 0, 0));
        TNoRw.setName("TNoRw"); // NOI18N
        TNoRw.setPreferredSize(new java.awt.Dimension(135, 23));
        TNoRw.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                TNoRwKeyPressed(evt);
            }
        });
        panelGlass7.add(TNoRw);

        jLabel95.setForeground(new java.awt.Color(0, 0, 0));
        jLabel95.setText("No. RM : ");
        jLabel95.setName("jLabel95"); // NOI18N
        jLabel95.setPreferredSize(new java.awt.Dimension(50, 23));
        panelGlass7.add(jLabel95);

        NoRM.setEditable(false);
        NoRM.setForeground(new java.awt.Color(0, 0, 0));
        NoRM.setName("NoRM"); // NOI18N
        NoRM.setPreferredSize(new java.awt.Dimension(65, 23));
        NoRM.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                NoRMKeyPressed(evt);
            }
        });
        panelGlass7.add(NoRM);

        jPanel2.add(panelGlass7, java.awt.BorderLayout.CENTER);

        panelGlass8.setName("panelGlass8"); // NOI18N
        panelGlass8.setPreferredSize(new java.awt.Dimension(44, 44));
        panelGlass8.setLayout(new java.awt.FlowLayout(java.awt.FlowLayout.LEFT, 5, 9));

        jLabel15.setForeground(new java.awt.Color(0, 0, 0));
        jLabel15.setText("Periode :");
        jLabel15.setName("jLabel15"); // NOI18N
        jLabel15.setPreferredSize(new java.awt.Dimension(70, 23));
        panelGlass8.add(jLabel15);

        DTPCari1.setEditable(false);
        DTPCari1.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "11-02-2022" }));
        DTPCari1.setDisplayFormat("dd-MM-yyyy");
        DTPCari1.setName("DTPCari1"); // NOI18N
        DTPCari1.setOpaque(false);
        DTPCari1.setPreferredSize(new java.awt.Dimension(95, 23));
        DTPCari1.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                DTPCari1KeyPressed(evt);
            }
        });
        panelGlass8.add(DTPCari1);

        jLabel17.setForeground(new java.awt.Color(0, 0, 0));
        jLabel17.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel17.setText("s.d");
        jLabel17.setName("jLabel17"); // NOI18N
        jLabel17.setPreferredSize(new java.awt.Dimension(23, 23));
        panelGlass8.add(jLabel17);

        DTPCari2.setEditable(false);
        DTPCari2.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "11-02-2022" }));
        DTPCari2.setDisplayFormat("dd-MM-yyyy");
        DTPCari2.setName("DTPCari2"); // NOI18N
        DTPCari2.setOpaque(false);
        DTPCari2.setPreferredSize(new java.awt.Dimension(95, 23));
        DTPCari2.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                DTPCari2KeyPressed(evt);
            }
        });
        panelGlass8.add(DTPCari2);

        jLabel12.setForeground(new java.awt.Color(0, 0, 0));
        jLabel12.setText("Status Periksa :");
        jLabel12.setName("jLabel12"); // NOI18N
        jLabel12.setPreferredSize(new java.awt.Dimension(110, 23));
        panelGlass8.add(jLabel12);

        cmbStatus.setForeground(new java.awt.Color(0, 0, 0));
        cmbStatus.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "Semua", "Sudah Diperiksa Dokter", "Berkas Diterima", "Sudah", "Belum", "Bayar", "Batal" }));
        cmbStatus.setName("cmbStatus"); // NOI18N
        cmbStatus.setOpaque(false);
        cmbStatus.setPreferredSize(new java.awt.Dimension(145, 23));
        cmbStatus.addItemListener(new java.awt.event.ItemListener() {
            public void itemStateChanged(java.awt.event.ItemEvent evt) {
                cmbStatusItemStateChanged(evt);
            }
        });
        panelGlass8.add(cmbStatus);

        BtnPxBooking.setForeground(new java.awt.Color(0, 0, 0));
        BtnPxBooking.setIcon(new javax.swing.ImageIcon(getClass().getResource("/picture/barralan.png"))); // NOI18N
        BtnPxBooking.setMnemonic('B');
        BtnPxBooking.setText("Pasien Booking");
        BtnPxBooking.setToolTipText("Alt+B");
        BtnPxBooking.setName("BtnPxBooking"); // NOI18N
        BtnPxBooking.setPreferredSize(new java.awt.Dimension(140, 23));
        BtnPxBooking.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                BtnPxBookingActionPerformed(evt);
            }
        });
        BtnPxBooking.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                BtnPxBookingKeyPressed(evt);
            }
        });
        panelGlass8.add(BtnPxBooking);

        ChkAutoRefres.setBackground(new java.awt.Color(255, 255, 250));
        ChkAutoRefres.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(255, 255, 250)));
        ChkAutoRefres.setForeground(new java.awt.Color(0, 0, 102));
        ChkAutoRefres.setText("Aktifkan Auto Refresh Data");
        ChkAutoRefres.setBorderPainted(true);
        ChkAutoRefres.setBorderPaintedFlat(true);
        ChkAutoRefres.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        ChkAutoRefres.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        ChkAutoRefres.setHorizontalTextPosition(javax.swing.SwingConstants.RIGHT);
        ChkAutoRefres.setName("ChkAutoRefres"); // NOI18N
        ChkAutoRefres.setPreferredSize(new java.awt.Dimension(175, 23));
        ChkAutoRefres.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                ChkAutoRefresActionPerformed(evt);
            }
        });
        panelGlass8.add(ChkAutoRefres);

        jPanel2.add(panelGlass8, java.awt.BorderLayout.PAGE_START);

        internalFrame1.add(jPanel2, java.awt.BorderLayout.PAGE_END);

        TabRawat.setBackground(new java.awt.Color(250, 255, 245));
        TabRawat.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(230, 235, 225)));
        TabRawat.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        TabRawat.setName("TabRawat"); // NOI18N
        TabRawat.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                TabRawatMouseClicked(evt);
            }
        });

        Scroll1.setToolTipText("Klik data di table, kemudian klik kanan untuk memilih menu yang diinginkan");
        Scroll1.setComponentPopupMenu(jPopupMenu1);
        Scroll1.setName("Scroll1"); // NOI18N
        Scroll1.setOpaque(true);

        tbKasirRalan.setAutoCreateRowSorter(true);
        tbKasirRalan.setToolTipText("");
        tbKasirRalan.setComponentPopupMenu(jPopupMenu1);
        tbKasirRalan.setName("tbKasirRalan"); // NOI18N
        tbKasirRalan.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                tbKasirRalanMouseClicked(evt);
            }
        });
        tbKasirRalan.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                tbKasirRalanKeyPressed(evt);
            }
        });
        Scroll1.setViewportView(tbKasirRalan);

        TabRawat.addTab(".: Registrasi Rawat Jalan", Scroll1);

        internalFrame1.add(TabRawat, java.awt.BorderLayout.CENTER);

        getContentPane().add(internalFrame1, java.awt.BorderLayout.CENTER);

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void BtnKeluarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_BtnKeluarActionPerformed
        dispose();
}//GEN-LAST:event_BtnKeluarActionPerformed

    private void BtnKeluarKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_BtnKeluarKeyPressed
        if (evt.getKeyCode() == KeyEvent.VK_SPACE) {
            dispose();
        } else {
            Valid.pindah(evt, cmbStatus, TCari);
        }
}//GEN-LAST:event_BtnKeluarKeyPressed

    private void BtnAllActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_BtnAllActionPerformed
        if (namadokter.equals("")) {
            CrPtg.setText("");
        }

        if (ChkAutoRefres.isSelected() == false && namapoli.equals("")) {
            CrPoli.setText("");
        }
        
//        if (namapoli.equals("")) {
//            CrPoli.setText("");
//        }
        
        TCari.setText("");
        tampilkasir();
        empttext();
}//GEN-LAST:event_BtnAllActionPerformed

    private void BtnAllKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_BtnAllKeyPressed
        if (evt.getKeyCode() == KeyEvent.VK_SPACE) {
            tampilkasir();
            TCari.setText("");
        } else {
            Valid.pindah(evt, TCari, BtnKeluar);
        }
}//GEN-LAST:event_BtnAllKeyPressed

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
        tampilkasir();
        empttext();
}//GEN-LAST:event_BtnCariActionPerformed

    private void BtnCariKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_BtnCariKeyPressed
        if (evt.getKeyCode() == KeyEvent.VK_SPACE) {
            BtnCariActionPerformed(null);
        } else {
            Valid.pindah(evt, TCari, BtnAll);
        }
}//GEN-LAST:event_BtnCariKeyPressed

    private void DTPCari1KeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_DTPCari1KeyPressed
        // TODO add your handling code here:
}//GEN-LAST:event_DTPCari1KeyPressed

    private void DTPCari2KeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_DTPCari2KeyPressed
        // TODO add your handling code here:
}//GEN-LAST:event_DTPCari2KeyPressed

    private void tbKasirRalanMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tbKasirRalanMouseClicked
        if (tabModekasir.getRowCount() != 0) {
            try {
                getDatakasir();
            } catch (java.lang.NullPointerException e) {
            }

            if (evt.getClickCount() == 2) {
                MnDataRalanActionPerformed(null);
//                i = tbKasirRalan.getSelectedColumn();
//                if (i == 0) {
//                    if (var.gettindakan_ralan() == true) {
//                        MnDataRalanActionPerformed(null);
//                    }
//                } else if (i == 1) {
//                    //if(var.getbilling_ralan()==true){
//                    MnBillingActionPerformed(null);
//                    //}                    
//                } else if (i == 2) {
//                    if (var.getkamar_inap() == true) {
//                        MnKamarInapActionPerformed(null);
//                    }
//                } else if (i == 3) {
//                    if (var.getkasir_ralan() == true) {
//                        MnSudahActionPerformed(null);
//                        tampilkasir();
//                    }
//                }
            }
        }
}//GEN-LAST:event_tbKasirRalanMouseClicked

    private void tbKasirRalanKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_tbKasirRalanKeyPressed
        if (tabModekasir.getRowCount() != 0) {
            if ((evt.getKeyCode() == KeyEvent.VK_ENTER) || (evt.getKeyCode() == KeyEvent.VK_UP) || (evt.getKeyCode() == KeyEvent.VK_DOWN)) {
                try {
                    getDatakasir();
                } catch (java.lang.NullPointerException e) {
                }
            }

//            if (evt.getKeyCode() == KeyEvent.VK_SPACE) {
//                i = tbKasirRalan.getSelectedColumn();
//                if (i == 0) {
//                    if (var.gettindakan_ralan() == true) {
//                        MnDataRalanActionPerformed(null);
//                    }
//                } else if (i == 1) {
//                    //if(var.getbilling_ralan()==true){
//                    MnBillingActionPerformed(null);
//                    //}                    
//                } else if (i == 2) {
//                    if (var.getkamar_inap() == true) {
//                        MnKamarInapActionPerformed(null);
//                    }
//                } else if (i == 3) {
//                    if (var.getkasir_ralan() == true) {
//                        MnSudahActionPerformed(null);
//                        tampilkasir();
//                    }
//                }
//            }
        }
}//GEN-LAST:event_tbKasirRalanKeyPressed

private void BtnSeek3ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_BtnSeek3ActionPerformed
    var.setform("DlgKasirRalan");
    pilihan = 2;
    billing.dokter.isCek();
    billing.dokter.setSize(internalFrame1.getWidth() - 40, internalFrame1.getHeight() - 40);
    billing.dokter.setLocationRelativeTo(internalFrame1);
    billing.dokter.setVisible(true);
}//GEN-LAST:event_BtnSeek3ActionPerformed

private void BtnSeek4ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_BtnSeek4ActionPerformed
    var.setform("DlgKasirRalan");
    pilihan = 2;
    billing.poli.isCek();
    billing.poli.setSize(internalFrame1.getWidth() - 40, internalFrame1.getHeight() - 40);
    billing.poli.setLocationRelativeTo(internalFrame1);
    billing.poli.setVisible(true);
}//GEN-LAST:event_BtnSeek4ActionPerformed

private void MnBillingActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_MnBillingActionPerformed
    if (tabModekasir.getRowCount() == 0) {
        JOptionPane.showMessageDialog(null, "Maaf, table masih kosong...!!!!");
        //TNoReg.requestFocus();
    } else if (TNoRw.getText().trim().equals("")) {
        JOptionPane.showMessageDialog(null, "Maaf, Silahkan anda pilih dulu dengan menklik data pada table...!!!");
        tbKasirRalan.requestFocus();
    } else {
        if (Sequel.cariInteger("select count(no_rawat) from kamar_inap where no_rawat=?", TNoRw.getText()) > 0) {
            JOptionPane.showMessageDialog(null, "Maaf, Pasien sudah masuk Kamar Inap. Gunakan billing Ranap..!!!");
        } else {
            try {
                sudah = Sequel.cariInteger("select count(billing.no_rawat) from billing where billing.no_rawat=?", TNoRw.getText());
                pscaripiutang = koneksi.prepareStatement("select tgl_piutang from piutang_pasien where no_rkm_medis=? and status='Belum Lunas' order by tgl_piutang asc limit 1");
                try {
                    pscaripiutang.setString(1, tbKasirRalan.getValueAt(tbKasirRalan.getSelectedRow(), 3).toString());
                    rskasir = pscaripiutang.executeQuery();
                    if (rskasir.next()) {
                        i = JOptionPane.showConfirmDialog(null, "Masih ada tunggakan pembayaran, apa mau bayar sekarang ?", "Konfirmasi", JOptionPane.YES_NO_OPTION);
                        if (i == JOptionPane.YES_OPTION) {
                            DlgLhtPiutang piutang = new DlgLhtPiutang(null, false);
                            piutang.setNoRm(tbKasirRalan.getValueAt(tbKasirRalan.getSelectedRow(), 3).toString(), rskasir.getDate("tgl_registrasi"));
                            piutang.tampil();
                            piutang.setSize(internalFrame1.getWidth() - 40, internalFrame1.getHeight() - 40);
                            piutang.setLocationRelativeTo(internalFrame1);
                            piutang.setVisible(true);
                        } else {
                            if (var.getbilling_ralan() == true) {
                                otomatisRalan();
                            }

                            billing.TNoRw.setText(TNoRw.getText());
                            billing.isCek();
                            billing.isRawat();
                            if (sudah > 0) {
                                billing.setPiutang();
                            }
                            billing.setSize(internalFrame1.getWidth() - 40, internalFrame1.getHeight() - 40);
                            billing.setLocationRelativeTo(internalFrame1);
                            tampilkasir();
                            billing.setVisible(true);
                        }
                    } else {
                        if (var.getbilling_ralan() == true) {
                            otomatisRalan();
                        }
                        billing.TNoRw.setText(TNoRw.getText());
                        billing.isCek();
                        billing.isRawat();
                        billing.setSize(internalFrame1.getWidth() - 40, internalFrame1.getHeight() - 40);
                        billing.setLocationRelativeTo(internalFrame1);
                        tampilkasir();
                        billing.setVisible(true);
                    }
                } catch (Exception ex) {
                    System.out.println("Notifikasi : " + ex);
                } finally {
                    if (rskasir != null) {
                        rskasir.close();
                    }
                    if (pscaripiutang != null) {
                        pscaripiutang.close();
                    }
                }
            } catch (Exception e) {
                System.out.println(e);
            }
        }
    }
}//GEN-LAST:event_MnBillingActionPerformed

private void TNoRwKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_TNoRwKeyPressed
    //Valid.pindah(evt,TNoReg,DTPReg);
}//GEN-LAST:event_TNoRwKeyPressed

private void MnSudahActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_MnSudahActionPerformed
    if (tabModekasir.getRowCount() == 0) {
        JOptionPane.showMessageDialog(null, "Maaf, tabel masih kosong...!!!!");
        TCari.requestFocus();
    } else if (TNoRw.getText().trim().equals("")) {
        Valid.textKosong(TNoRw, "No.Rawat");
    } else {
        if (Sequel.cariInteger("select count(no_rawat) from kamar_inap where no_rawat=?", TNoRw.getText()) > 0) {
            JOptionPane.showMessageDialog(null, "Maaf, Pasien sudah masuk Kamar Inap. Gunakan billing Ranap..!!!");
        } else {
            Valid.editTable(tabModekasir, "reg_periksa", "no_rawat", TNoRw, "stts='Sudah Diperiksa Dokter'");
            if (tabModekasir.getRowCount() != 0) {
                tampilkasir();
            }
        }
    }
}//GEN-LAST:event_MnSudahActionPerformed

private void MnBelumActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_MnBelumActionPerformed
    if (tabModekasir.getRowCount() == 0) {
        JOptionPane.showMessageDialog(null, "Maaf, tabel masih kosong...!!!!");
        TCari.requestFocus();
    } else if (TNoRw.getText().trim().equals("")) {
        Valid.textKosong(TNoRw, "No.Rawat");
    } else {
        if (Sequel.cariInteger("select count(no_rawat) from kamar_inap where no_rawat=?", TNoRw.getText()) > 0) {
            JOptionPane.showMessageDialog(null, "Maaf, Pasien sudah masuk Kamar Inap. Gunakan billing Ranap..!!!");
        } else {
            Valid.editTable(tabModekasir, "reg_periksa", "no_rawat", TNoRw, "stts='Belum'");
            if (tabModekasir.getRowCount() != 0) {
                tampilkasir();
            }
        }
    }
}//GEN-LAST:event_MnBelumActionPerformed

private void MnDataRalanActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_MnDataRalanActionPerformed
    if (tabModekasir.getRowCount() == 0) {
        JOptionPane.showMessageDialog(rootPane, "Maaf, tabel masih kosong...!!!!");
        TCari.requestFocus();
    } else if (TNoRw.getText().equals("")) {
        JOptionPane.showMessageDialog(rootPane, "Klik dulu salah satu nama pasiennya pada tabel...!!!!");
        tbKasirRalan.requestFocus();
    } else {
        if (var.getkode().equals("Admin Utama") || (!kdpoli.getText().equals("IGDK") || (var.getkode().equals("PPRM")))) {
            
            if (Sequel.cariInteger("select count(no_rawat) from kamar_inap where no_rawat=?", TNoRw.getText()) > 0) {
                JOptionPane.showMessageDialog(rootPane, "Maaf, Pasien sudah masuk Kamar Inap. Gunakan billing Ranap..!!!");
            } else {
                cekReg();
            }

        } else if (kdpoli.getText().equals("IGDK")) {
            JOptionPane.showMessageDialog(rootPane, "Untuk pasien IGD silakan masukkan tindakannya melalui registrasi IGD nya...!!!!");
            TCari.requestFocus();
        }
    }
}//GEN-LAST:event_MnDataRalanActionPerformed

private void BtnCloseIn1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_BtnCloseIn1ActionPerformed
    WindowGantiDokter.dispose();
}//GEN-LAST:event_BtnCloseIn1ActionPerformed

private void BtnCloseIn1KeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_BtnCloseIn1KeyPressed
// TODO add your handling code here:
}//GEN-LAST:event_BtnCloseIn1KeyPressed

private void BtnSimpan1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_BtnSimpan1ActionPerformed
    if (TNoRw.getText().trim().equals("")) {
        Valid.textKosong(TNoRw, "No.Rawat");
    }
    if (kddokter.getText().trim().equals("") || TDokter.getText().trim().equals("")) {
        Valid.textKosong(kddokter, "Dokter");
    } else {
        Valid.editTable(tabModekasir, "reg_periksa", "no_rawat", TNoRw, " kd_dokter='" + kddokter.getText() + "'");
        Valid.editTable(tabModekasir, "rawat_jl_dr", "no_rawat", TNoRw, " kd_dokter='" + kddokter.getText() + "'");
        Valid.editTable(tabModekasir, "rawat_jl_drpr", "no_rawat", TNoRw, " kd_dokter='" + kddokter.getText() + "'");
        tampilkasir();
        WindowGantiDokter.dispose();
    }
}//GEN-LAST:event_BtnSimpan1ActionPerformed

private void BtnSimpan1KeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_BtnSimpan1KeyPressed
// TODO add your handling code here:
}//GEN-LAST:event_BtnSimpan1KeyPressed

private void kddokterKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_kddokterKeyPressed
    if (evt.getKeyCode() == KeyEvent.VK_PAGE_DOWN) {
        Sequel.cariIsi("select nm_dokter from dokter where kd_dokter=?", TDokter, kddokter.getText());
    } else if (evt.getKeyCode() == KeyEvent.VK_UP) {
        btnCariDokterActionPerformed(null);
    }
}//GEN-LAST:event_kddokterKeyPressed

private void btnCariDokterActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnCariDokterActionPerformed
    var.setform("DlgKasirRalan");
    pilihan = 1;
    billing.dokter.emptTeks();
    billing.dokter.isCek();
    billing.dokter.setSize(internalFrame1.getWidth() - 40, internalFrame1.getHeight() - 40);
    billing.dokter.setLocationRelativeTo(internalFrame1);
    billing.dokter.setVisible(true);
}//GEN-LAST:event_btnCariDokterActionPerformed

private void MnDokterActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_MnDokterActionPerformed
    if (tabModekasir.getRowCount() == 0) {
        JOptionPane.showMessageDialog(null, "Maaf, tabel masih kosong...!!!!");
        TCari.requestFocus();
    } else if (TNoRw.getText().equals("")) {
        JOptionPane.showMessageDialog(null, "Klik dulu salah satu nama pasiennya pada tabel...!!!!");
        tbKasirRalan.requestFocus();
    } else if (Sequel.cariInteger("select count(no_rawat) from kamar_inap where no_rawat=?", TNoRw.getText()) > 0) {
        JOptionPane.showMessageDialog(null, "Maaf, Pasien sudah masuk Kamar Inap. Gunakan billing Ranap..!!!");
    } else {
        if (var.getkode().equals("Admin Utama")) {
            WindowGantiDokter.setSize(630, 80);
            WindowGantiDokter.setLocationRelativeTo(internalFrame1);
            WindowGantiDokter.setVisible(true);
            btnCariDokter.requestFocus();
        } else {
            if (Sequel.cariRegistrasi(TNoRw.getText()) > 0) {
                JOptionPane.showMessageDialog(rootPane, "Data billing sudah terverifikasi..!!");
            } else {
                WindowGantiDokter.setSize(630, 80);
                WindowGantiDokter.setLocationRelativeTo(internalFrame1);
                WindowGantiDokter.setVisible(true);
                btnCariDokter.requestFocus();
            }
        }
    }
}//GEN-LAST:event_MnDokterActionPerformed

private void MnPeriksaLabActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_MnPeriksaLabActionPerformed
    diagnosa_ok = "";
    diagnosa_cek = 0;
    diagnosa_cek = Sequel.cariInteger("select count(1) cek from pemeriksaan_ralan where no_rawat='" + TNoRw.getText() + "'");

    if (diagnosa_cek == 0) {
        diagnosa_ok = "-";
    } else {
        diagnosa_ok = Sequel.cariIsi("select diagnosa from pemeriksaan_ralan where no_rawat='" + TNoRw.getText() + "'");
    }

    if (tabModekasir.getRowCount() == 0) {
        JOptionPane.showMessageDialog(null, "Maaf, table masih kosong...!!!!");
        TCari.requestFocus();
    } else if (TNoRw.getText().trim().equals("")) {
        JOptionPane.showMessageDialog(null, "Maaf, Silahkan anda pilih dulu dengan menklik data pada table...!!!");
        tbKasirRalan.requestFocus();
    } else {
        if (Sequel.cariInteger("select count(no_rawat) from kamar_inap where no_rawat=?", TNoRw.getText()) > 0) {
            JOptionPane.showMessageDialog(null, "Maaf, Pasien sudah masuk Kamar Inap. Gunakan billing Ranap..!!!");
        } else {
            kamarinap.billing.periksalab.setSize(internalFrame1.getWidth() - 40, internalFrame1.getHeight() - 40);
            kamarinap.billing.periksalab.setLocationRelativeTo(internalFrame1);
            kamarinap.billing.periksalab.emptTeks();
            kamarinap.billing.periksalab.KodePerujuk.setText(kddokter.getText());
            kamarinap.billing.periksalab.setNoRm(TNoRw.getText(), "Ralan", diagnosa_ok, "-", Sequel.cariIsi("select nm_poli from poliklinik where kd_poli='" + kdpoli.getText() + "'"));
            kamarinap.billing.periksalab.tampiltarif();
            kamarinap.billing.periksalab.tampilMintaPeriksa();
            kamarinap.billing.periksalab.tampil();
            kamarinap.billing.periksalab.isCek();
            kamarinap.billing.periksalab.setVisible(true);
            kamarinap.billing.periksalab.fokus();
        }
    }
}//GEN-LAST:event_MnPeriksaLabActionPerformed

private void MnKamarInapActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_MnKamarInapActionPerformed
    if (tabModekasir.getRowCount() == 0) {
        JOptionPane.showMessageDialog(null, "Maaf, tabel masih kosong...!!!!");
        TCari.requestFocus();
    } else if (TNoRw.getText().trim().equals("")) {
        JOptionPane.showMessageDialog(null, "Maaf, Silahkan anda pilih dulu dengan mengklik data pada tabel...!!!");
        tbKasirRalan.requestFocus();
    } else {
        if (var.getkode().equals("Admin Utama")) {
            var.setstatus(true);
            kamarinap.setSize(internalFrame1.getWidth() - 40, internalFrame1.getHeight() - 40);
            kamarinap.setLocationRelativeTo(internalFrame1);
            kamarinap.emptTeks();
            kamarinap.isCek();
            kamarinap.setNoRm(TNoRw.getText());
            kamarinap.tampil();
            kamarinap.setVisible(true);
            kamarinap.cekKetMati();
            kamarinap.UserValid();
        } else {
            if (kdpoli.getText().equals("-") || kdpoli.getText().equals("IGDK")) {
                if (Sequel.cariRegistrasi(TNoRw.getText()) > 0) {
                    JOptionPane.showMessageDialog(rootPane, "Data billing sudah terverifikasi..!!");
                } else {
                    var.setstatus(true);
                    kamarinap.setSize(internalFrame1.getWidth() - 40, internalFrame1.getHeight() - 40);
                    kamarinap.setLocationRelativeTo(internalFrame1);
                    kamarinap.emptTeks();
                    kamarinap.isCek();
                    kamarinap.setNoRm(TNoRw.getText());
                    kamarinap.tampil();
                    kamarinap.setVisible(true);
                    kamarinap.cekKetMati();
                    kamarinap.UserValid();
                }
            } else {
                JOptionPane.showMessageDialog(rootPane, "Kunjungan rawat jalan poliklinik, tdk. bisa langsung rawat inap. Sepakati dulu alurnya..!!");
                tbKasirRalan.requestFocus();
            }
        }
    }
}//GEN-LAST:event_MnKamarInapActionPerformed

private void cmbStatusItemStateChanged(java.awt.event.ItemEvent evt) {//GEN-FIRST:event_cmbStatusItemStateChanged
    tampilkasir();
}//GEN-LAST:event_cmbStatusItemStateChanged

private void Kd2KeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_Kd2KeyPressed
    // TODO add your handling code here:
}//GEN-LAST:event_Kd2KeyPressed

private void MnRekapHarianDokterActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_MnRekapHarianDokterActionPerformed
    int cekForm = Sequel.cekForm("DlgRHJmDokter_Buka"), cekDokter;
    cekDokter = Sequel.cariInteger("select count(-1) from dokter where kd_dokter = '" + var.getkode() + "'");
    if (cekForm == 1) {
        if (var.getnamauser().equals("Admin Utama") || cekDokter == 1) {
            this.setCursor(Cursor.getPredefinedCursor(Cursor.WAIT_CURSOR));
            DlgRHJmDokter rhtindakandokter = new DlgRHJmDokter(null, false);
            rhtindakandokter.isCek(var.getkode());
            rhtindakandokter.setSize(internalFrame1.getWidth() - 40, internalFrame1.getHeight() - 40);
            rhtindakandokter.setLocationRelativeTo(internalFrame1);
            rhtindakandokter.setVisible(true);
            this.setCursor(Cursor.getDefaultCursor());
        } else {
            JOptionPane.showMessageDialog(rootPane, "AKSES DITOLAK !!!");
        }
    } else {
        this.setCursor(Cursor.getPredefinedCursor(Cursor.WAIT_CURSOR));
        DlgRHJmDokter rhtindakandokter = new DlgRHJmDokter(null, false);
        rhtindakandokter.isCek("");
        rhtindakandokter.setSize(internalFrame1.getWidth() - 40, internalFrame1.getHeight() - 40);
        rhtindakandokter.setLocationRelativeTo(internalFrame1);
        rhtindakandokter.setVisible(true);
        this.setCursor(Cursor.getDefaultCursor());
    }
}//GEN-LAST:event_MnRekapHarianDokterActionPerformed

private void MnRekapHarianParamedisActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_MnRekapHarianParamedisActionPerformed
    this.setCursor(Cursor.getPredefinedCursor(Cursor.WAIT_CURSOR));
    DlgRHJmParamedis rhtindakanparamedis = new DlgRHJmParamedis(null, false);
    rhtindakanparamedis.isCek();
    rhtindakanparamedis.setSize(internalFrame1.getWidth() - 40, internalFrame1.getHeight() - 40);
    rhtindakanparamedis.setLocationRelativeTo(internalFrame1);
    rhtindakanparamedis.setVisible(true);
    this.setCursor(Cursor.getDefaultCursor());
}//GEN-LAST:event_MnRekapHarianParamedisActionPerformed

private void MnRekapBulananDokterActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_MnRekapBulananDokterActionPerformed
    int cekForm = Sequel.cekForm("DlgRBJmDokter_Buka"), cekDokter;
    cekDokter = Sequel.cariInteger("select count(-1) from dokter where kd_dokter = '" + var.getkode() + "'");
    if (cekForm == 1) {
        if (var.getnamauser().equals("Admin Utama") || cekDokter == 1) {
            this.setCursor(Cursor.getPredefinedCursor(Cursor.WAIT_CURSOR));
            DlgRBJmDokter rhtindakandokter = new DlgRBJmDokter(null, false);
            rhtindakandokter.isCek(var.getkode());
            rhtindakandokter.setSize(internalFrame1.getWidth() - 40, internalFrame1.getHeight() - 40);
            rhtindakandokter.setLocationRelativeTo(internalFrame1);
            rhtindakandokter.setVisible(true);
            this.setCursor(Cursor.getDefaultCursor());
        } else {
            JOptionPane.showMessageDialog(rootPane, "AKSES DITOLAK !!!");
        }
    } else {
        this.setCursor(Cursor.getPredefinedCursor(Cursor.WAIT_CURSOR));
        DlgRBJmDokter rhtindakandokter = new DlgRBJmDokter(null, false);
        rhtindakandokter.isCek("");
        rhtindakandokter.setSize(internalFrame1.getWidth() - 40, internalFrame1.getHeight() - 40);
        rhtindakandokter.setLocationRelativeTo(internalFrame1);
        rhtindakandokter.setVisible(true);
        this.setCursor(Cursor.getDefaultCursor());
    }
}//GEN-LAST:event_MnRekapBulananDokterActionPerformed

private void MnRekapBulananParamedisActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_MnRekapBulananParamedisActionPerformed
    this.setCursor(Cursor.getPredefinedCursor(Cursor.WAIT_CURSOR));
    DlgRBJmParamedis rhtindakanparamedis = new DlgRBJmParamedis(null, false);
    rhtindakanparamedis.isCek();
    rhtindakanparamedis.setSize(internalFrame1.getWidth() - 40, internalFrame1.getHeight() - 40);
    rhtindakanparamedis.setLocationRelativeTo(internalFrame1);
    rhtindakanparamedis.setVisible(true);
    this.setCursor(Cursor.getDefaultCursor());
}//GEN-LAST:event_MnRekapBulananParamedisActionPerformed

private void MnRekapHarianPoliActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_MnRekapHarianPoliActionPerformed
    this.setCursor(Cursor.getPredefinedCursor(Cursor.WAIT_CURSOR));
    DlgRBTindakanPoli rhtindakandokter = new DlgRBTindakanPoli(null, false);
    rhtindakandokter.setSize(internalFrame1.getWidth() - 40, internalFrame1.getHeight() - 40);
    rhtindakandokter.setLocationRelativeTo(internalFrame1);
    rhtindakandokter.setVisible(true);
    this.setCursor(Cursor.getDefaultCursor());
}//GEN-LAST:event_MnRekapHarianPoliActionPerformed

private void MnDataPemberianObatActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_MnDataPemberianObatActionPerformed
    if (tabModekasir.getRowCount() == 0) {
        JOptionPane.showMessageDialog(null, "Maaf, tabel masih kosong...!!!!");
        TCari.requestFocus();
    } else if (TNoRw.getText().trim().equals("")) {
        JOptionPane.showMessageDialog(null, "Maaf, Pasien belum dipilih...!!!");
        TNoRw.requestFocus();
    } else {
        if (Sequel.cariInteger("select count(no_rawat) from kamar_inap where no_rawat=?", TNoRw.getText()) > 0) {
            JOptionPane.showMessageDialog(null, "Maaf, Pasien sudah masuk Kamar Inap. Gunakan billing Ranap..!!!");
        } else {
            DlgPemberianObat dlgrwinap = new DlgPemberianObat(null, false);
            dlgrwinap.setSize(internalFrame1.getWidth() - 40, internalFrame1.getHeight() - 40);
            dlgrwinap.setLocationRelativeTo(internalFrame1);
            dlgrwinap.isCek();
            dlgrwinap.setNoRm(TNoRw.getText(), DTPCari1.getDate(), DTPCari2.getDate(), "ralan");
            dlgrwinap.tampilPO();
            dlgrwinap.setVisible(true);
        }
    }
}//GEN-LAST:event_MnDataPemberianObatActionPerformed

    private void MnRekapHarianObatActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_MnRekapHarianObatActionPerformed
        this.setCursor(Cursor.getPredefinedCursor(Cursor.WAIT_CURSOR));
        DlgRBObatPoli rhtindakandokter = new DlgRBObatPoli(null, false);
        rhtindakandokter.setSize(internalFrame1.getWidth() - 40, internalFrame1.getHeight() - 40);
        rhtindakandokter.setLocationRelativeTo(internalFrame1);
        rhtindakandokter.setVisible(true);
        this.setCursor(Cursor.getDefaultCursor());
    }//GEN-LAST:event_MnRekapHarianObatActionPerformed

    private void MnNoResepActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_MnNoResepActionPerformed
        if (tabModekasir.getRowCount() == 0) {
            JOptionPane.showMessageDialog(null, "Maaf, tabel masih kosong...!!!!");
            TCari.requestFocus();
        } else if (TNoRw.getText().trim().equals("")) {
            JOptionPane.showMessageDialog(null, "Maaf, Silahkan anda pilih dulu pasien...!!!");
            tbKasirRalan.requestFocus();
        } else {
            if (Sequel.cariInteger("select count(no_rawat) from kamar_inap where no_rawat=?", TNoRw.getText()) > 0) {
                JOptionPane.showMessageDialog(null, "Maaf, Pasien sudah masuk Kamar Inap. Gunakan billing Ranap..!!!");
            } else {
                DlgResepObat resep = new DlgResepObat(null, false);
                resep.setSize(internalFrame1.getWidth() - 40, internalFrame1.getHeight() - 40);
                resep.setLocationRelativeTo(internalFrame1);
                resep.emptTeks();
                resep.isCek();
                resep.setNoRm(TNoRw.getText(), DTPCari1.getDate(), DTPCari2.getDate(), Jam.getText().substring(0, 2), Jam.getText().substring(3, 5), Jam.getText().substring(6, 8));
                resep.setDokterRalan();
                resep.tampil();
                resep.setVisible(true);
            }
        }
    }//GEN-LAST:event_MnNoResepActionPerformed

    private void MnPeriksaRadiologiActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_MnPeriksaRadiologiActionPerformed
        if (tabModekasir.getRowCount() == 0) {
            JOptionPane.showMessageDialog(null, "Maaf, table masih kosong...!!!!");
            TCari.requestFocus();
        } else if (TNoRw.getText().trim().equals("")) {
            JOptionPane.showMessageDialog(null, "Maaf, Silahkan anda pilih dulu dengan menklik data pada table...!!!");
            tbKasirRalan.requestFocus();
        } else {
            if (Sequel.cariInteger("select count(no_rawat) from kamar_inap where no_rawat=?", TNoRw.getText()) > 0) {
                JOptionPane.showMessageDialog(null, "Maaf, Pasien sudah masuk Kamar Inap. Gunakan billing Ranap..!!!");
            } else {
                kamarinap.billing.periksarad.setSize(internalFrame1.getWidth() - 40, internalFrame1.getHeight() - 40);
                kamarinap.billing.periksarad.setLocationRelativeTo(internalFrame1);
                kamarinap.billing.periksarad.emptTeks();
                kamarinap.billing.periksarad.setNoRm(TNoRw.getText(), "Ralan");
                kamarinap.billing.periksarad.tampil();
                kamarinap.billing.periksarad.isCek();
                kamarinap.billing.periksarad.setVisible(true);
                kamarinap.billing.periksarad.fokus_kursor();
            }
        }
    }//GEN-LAST:event_MnPeriksaRadiologiActionPerformed

    private void BtnCloseIn4ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_BtnCloseIn4ActionPerformed
        WindowGantiPoli.dispose();
    }//GEN-LAST:event_BtnCloseIn4ActionPerformed

    private void BtnSimpan4ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_BtnSimpan4ActionPerformed
        if (TNoRw.getText().trim().equals("")) {
            Valid.textKosong(TNoRw, "No.Rawat");
        }
        if (kdpoli.getText().trim().equals("") || nmpoli.getText().trim().equals("")) {
            Valid.textKosong(kdpoli, "Poli");
        } else {
            Valid.editTable(tabModekasir, "reg_periksa", "no_rawat", TNoRw, " kd_poli='" + kdpoli.getText() + "'");
            tampilkasir();
            WindowGantiPoli.dispose();
        }
    }//GEN-LAST:event_BtnSimpan4ActionPerformed

    private void kdpoliKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_kdpoliKeyPressed
        if (evt.getKeyCode() == KeyEvent.VK_PAGE_DOWN) {
            Sequel.cariIsi("select nm_poli from poliklinik where kd_poli=?", nmpoli, kdpoli.getText());
        } else if (evt.getKeyCode() == KeyEvent.VK_UP) {
            btnCariPoliActionPerformed(null);
        } else {
            Valid.pindah(evt, BtnCloseIn4, BtnSimpan4);
        }
    }//GEN-LAST:event_kdpoliKeyPressed

    private void btnCariPoliActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnCariPoliActionPerformed
        var.setform("DlgKasirRalan");
        pilihan = 1;
        billing.poli.isCek();
        billing.poli.setSize(internalFrame1.getWidth() - 40, internalFrame1.getHeight() - 40);
        billing.poli.setLocationRelativeTo(internalFrame1);
        billing.poli.setAlwaysOnTop(false);
        billing.poli.setVisible(true);
    }//GEN-LAST:event_btnCariPoliActionPerformed

    private void MnPoliActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_MnPoliActionPerformed
        if (tabModekasir.getRowCount() == 0) {
            JOptionPane.showMessageDialog(null, "Maaf, tabel masih kosong...!!!!");
            TCari.requestFocus();
        } else if (TNoRw.getText().equals("")) {
            JOptionPane.showMessageDialog(null, "Klik dulu salah satu nama pasiennya pada tabel...!!!!");
            tbKasirRalan.requestFocus();
        } else {
            Sequel.cariIsi("select kd_poli from reg_periksa where no_rawat=?", kdpoli, TNoRw.getText());
            Sequel.cariIsi("select nm_poli from poliklinik where kd_poli=?", nmpoli, kdpoli.getText());

            WindowGantiPoli.setSize(630, 80);
            WindowGantiPoli.setLocationRelativeTo(internalFrame1);
            WindowGantiPoli.setAlwaysOnTop(false);
            WindowGantiPoli.setVisible(true);
            btnCariPoli.requestFocus();
        }
    }//GEN-LAST:event_MnPoliActionPerformed

    private void MnDiagnosaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_MnDiagnosaActionPerformed
        if (tabModekasir.getRowCount() == 0) {
            JOptionPane.showMessageDialog(null, "Maaf, tabel masih kosong...!!!!");
            TCari.requestFocus();
        } else if (TNoRw.getText().trim().equals("")) {
            JOptionPane.showMessageDialog(null, "Maaf, Silahkan anda pilih dulu pasien...!!!");
            TCari.requestFocus();
        } else {
            if (Sequel.cariInteger("select count(no_rawat) from kamar_inap where no_rawat=?", TNoRw.getText()) > 0) {
                JOptionPane.showMessageDialog(null, "Maaf, Pasien sudah masuk Kamar Inap. Masukkan diagnosa lewat kamar inap..!!!");
            } else {
                DlgDiagnosaPenyakit resep = new DlgDiagnosaPenyakit(null, false);
                resep.setSize(internalFrame1.getWidth() - 40, internalFrame1.getHeight() - 40);
                resep.setLocationRelativeTo(internalFrame1);
                resep.isCek();
                resep.setNoRm(TNoRw.getText(), DTPCari1.getDate(), DTPCari2.getDate(), "Ralan");
                resep.tampil();
                resep.setVisible(true);
            }
        }
    }//GEN-LAST:event_MnDiagnosaActionPerformed

    private void MnBatalActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_MnBatalActionPerformed
        if (tabModekasir.getRowCount() == 0) {
            JOptionPane.showMessageDialog(null, "Maaf, tabel masih kosong...!!!!");
            TCari.requestFocus();
        } else if (TNoRw.getText().trim().equals("")) {
            Valid.textKosong(TNoRw, "No.Rawat");
        } else {
            if (Sequel.cariInteger("select count(no_rawat) from kamar_inap where no_rawat=?", TNoRw.getText()) > 0) {
                JOptionPane.showMessageDialog(null, "Maaf, Pasien sudah masuk Kamar Inap. Gunakan billing Ranap..!!!");
            } else {
                Valid.editTable(tabModekasir, "reg_periksa", "no_rawat", TNoRw, "stts='Batal'");
                if (tabModekasir.getRowCount() != 0) {
                    tampilkasir();
                }
            }
        }
    }//GEN-LAST:event_MnBatalActionPerformed

    private void MnOperasiActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_MnOperasiActionPerformed
        if (tabModekasir.getRowCount() == 0) {
            JOptionPane.showMessageDialog(null, "Maaf, tabel masih kosong...!!!!");
            TCari.requestFocus();
        } else if (TNoRw.getText().trim().equals("")) {
            JOptionPane.showMessageDialog(null, "Maaf, Silahkan anda pilih dulu pasien...!!!");
            TCari.requestFocus();
        } else {
            if (Sequel.cariInteger("select count(no_rawat) from kamar_inap where no_rawat=?", TNoRw.getText()) > 0) {
                JOptionPane.showMessageDialog(null, "Maaf, Pasien sudah masuk Kamar Inap. Gunakan billing Ranap..!!!");
            } else {
                DlgTagihanOperasi dlgro = new DlgTagihanOperasi(null, false);
                dlgro.setSize(internalFrame1.getWidth() - 40, internalFrame1.getHeight() - 40);
                dlgro.setLocationRelativeTo(internalFrame1);
                dlgro.setNoRm(TNoRw.getText(), tbKasirRalan.getValueAt(tbKasirRalan.getSelectedRow(), 3).toString() + ", " + tbKasirRalan.getValueAt(tbKasirRalan.getSelectedRow(), 4).toString(), "Ralan");
                dlgro.setVisible(true);
                dlgro.fokus();
            }
        }
    }//GEN-LAST:event_MnOperasiActionPerformed

    private void MnPenjabActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_MnPenjabActionPerformed
        if (tabModekasir.getRowCount() == 0) {
            JOptionPane.showMessageDialog(null, "Maaf, tabel masih kosong...!!!!");
            TCari.requestFocus();
        } else if (TNoRw.getText().trim().equals("")) {
            JOptionPane.showMessageDialog(null, "Maaf, Silahkan anda pilih dulu pasien...!!!");
            tbKasirRalan.requestFocus();
        } else {
            Sequel.cariIsi("select kd_pj from reg_periksa where no_rawat=?", kdpenjab, TNoRw.getText());
            Sequel.cariIsi("select png_jawab from penjab where kd_pj=?", nmpenjab, kdpenjab.getText());

            WindowCaraBayar.setSize(630, 80);
            WindowCaraBayar.setLocationRelativeTo(internalFrame1);
            WindowCaraBayar.setVisible(true);
            btnBayar.requestFocus();
        }
    }//GEN-LAST:event_MnPenjabActionPerformed

    private void BtnCloseIn5ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_BtnCloseIn5ActionPerformed
        WindowCaraBayar.dispose();
    }//GEN-LAST:event_BtnCloseIn5ActionPerformed

    private void BtnSimpan5ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_BtnSimpan5ActionPerformed
        if (TNoRw.getText().trim().equals("")) {
            Valid.textKosong(TCari, "No.Rawat");
        }
        if (kdpenjab.getText().trim().equals("") || nmpenjab.getText().trim().equals("")) {
            Valid.textKosong(kdpenjab, "Jenis Bayar");
        } else {
            //String kdpj=Sequel.cariIsi("select kd_pj from reg_periksa where no_rawat=?",norawat.getText());
            Sequel.AutoComitFalse();
            //Sequel.meghapus("ubah_penjab","no_rawat",norawat.getText());
            Sequel.mengedit("reg_periksa", "no_rawat=?", " kd_pj=?", 2, new String[]{kdpenjab.getText(), TNoRw.getText()});
            //Sequel.menyimpan("ubah_penjab","?,?,?,?","Ubah Jenis Bayar",4,new String[]{norawat.getText(),now,kdpj,kdpenjab.getText()});
            Sequel.AutoComitTrue();
            tampilkasir();
            WindowCaraBayar.dispose();
        }
    }//GEN-LAST:event_BtnSimpan5ActionPerformed

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
        var.setform("DlgKasirRalan");
        billing.penjab.emptTeks();
        billing.penjab.isCek();
        billing.penjab.setSize(internalFrame1.getWidth() - 40, internalFrame1.getHeight() - 40);
        billing.penjab.setLocationRelativeTo(internalFrame1);
        billing.penjab.setVisible(true);
    }//GEN-LAST:event_btnBayarActionPerformed

    private void MnHapusDataActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_MnHapusDataActionPerformed
        cekSEPboking = "";

        if (tabModekasir.getRowCount() == 0) {
            JOptionPane.showMessageDialog(null, "Maaf, tabel masih kosong...!!!!");
            TCari.requestFocus();
        } else if (TNoRw.getText().trim().equals("")) {
            JOptionPane.showMessageDialog(null, "Maaf, Silahkan anda pilih dulu pasien...!!!");
            TCari.requestFocus();
        } else {
            cekSEPboking = Sequel.cariIsi("select kd_booking from booking_registrasi where no_rawat='" + TNoRw.getText() + "'");

            Sequel.AutoComitFalse();
            Sequel.queryu("delete from operasi where no_rawat='" + TNoRw.getText() + "'");
            Sequel.queryu("delete from beri_obat_operasi where no_rawat='" + TNoRw.getText() + "'");
            Sequel.queryu("delete from billing where no_rawat='" + TNoRw.getText() + "'");
            Sequel.queryu("delete from nota_inap where no_rawat='" + TNoRw.getText() + "'");
            Sequel.queryu("delete from nota_jalan where no_rawat='" + TNoRw.getText() + "'");
            Sequel.queryu("delete from deposit where no_rawat='" + TNoRw.getText() + "'");
            Sequel.queryu("delete from detail_beri_diet where no_rawat='" + TNoRw.getText() + "'");
            Sequel.queryu("delete from diagnosa_pasien where no_rawat='" + TNoRw.getText() + "'");
            Sequel.queryu("delete from dpjp_ranap where no_rawat='" + TNoRw.getText() + "'");
            Sequel.queryu("delete from hemodialisa where no_rawat='" + TNoRw.getText() + "'");
            Sequel.queryu("delete from kamar_inap where no_rawat='" + TNoRw.getText() + "'");
            Sequel.queryu("delete from pengurangan_biaya where no_rawat='" + TNoRw.getText() + "'");
            Sequel.queryu("delete from piutang_pasien where no_rawat='" + TNoRw.getText() + "'");
            Sequel.queryu("delete from prosedur_pasien where no_rawat='" + TNoRw.getText() + "'");
            Sequel.queryu("delete from ranap_gabung where no_rawat='" + TNoRw.getText() + "'");
            Sequel.queryu("delete from rujuk where no_rawat='" + TNoRw.getText() + "'");
            Sequel.queryu("delete from rujuk_masuk where no_rawat='" + TNoRw.getText() + "'");
            Sequel.queryu("delete from tambahan_biaya where no_rawat='" + TNoRw.getText() + "'");
            Sequel.queryu("delete from rawat_inap_dr where no_rawat='" + TNoRw.getText() + "'");
            Sequel.queryu("delete from rawat_inap_drpr where no_rawat='" + TNoRw.getText() + "'");
            Sequel.queryu("delete from rawat_inap_pr where no_rawat='" + TNoRw.getText() + "'");
            Sequel.queryu("delete from rawat_jl_dr where no_rawat='" + TNoRw.getText() + "'");
            Sequel.queryu("delete from rawat_jl_drpr where no_rawat='" + TNoRw.getText() + "'");
            Sequel.queryu("delete from rawat_jl_pr where no_rawat='" + TNoRw.getText() + "'");
            Sequel.queryu("delete from pemeriksaan_ralan where no_rawat='" + TNoRw.getText() + "'");
            Sequel.queryu("delete from pemeriksaan_ranap where no_rawat='" + TNoRw.getText() + "'");
            Sequel.queryu("delete from detail_periksa_lab where no_rawat='" + TNoRw.getText() + "'");
            Sequel.queryu("delete from periksa_lab where no_rawat='" + TNoRw.getText() + "'");
            Sequel.queryu("delete from beri_bhp_radiologi where no_rawat='" + TNoRw.getText() + "'");
            Sequel.queryu("delete from periksa_radiologi where no_rawat='" + TNoRw.getText() + "'");
            Sequel.queryu("delete from aturan_pakai where no_rawat='" + TNoRw.getText() + "'");
            Sequel.queryu("delete from detail_pemberian_obat where no_rawat='" + TNoRw.getText() + "'");
            Sequel.queryu("delete from tagihan_obat_langsung where no_rawat='" + TNoRw.getText() + "'");
            Sequel.queryu("delete from resep_obat where no_rawat='" + TNoRw.getText() + "'");
            Sequel.queryu("delete from resep_pulang where no_rawat='" + TNoRw.getText() + "'");
            Sequel.queryu("delete from returpasien where no_rawat='" + TNoRw.getText() + "'");
            Sequel.queryu("delete from stok_obat_pasien where no_rawat='" + TNoRw.getText() + "'");
            Sequel.queryu("delete from detail_nota_jalan where no_rawat='" + TNoRw.getText() + "'");
            Sequel.queryu("delete from detail_nota_inap where no_rawat='" + TNoRw.getText() + "'");
            Sequel.queryu("delete from detail_piutang_pasien where no_rawat='" + TNoRw.getText() + "'");
            Sequel.queryu("delete from mutasi_berkas where no_rawat='" + TNoRw.getText() + "'");
            Sequel.queryu("delete from ralan_aps where no_rawat='" + TNoRw.getText() + "'");
            Sequel.meghapus("reg_rujukan_intern", "no_rawat_ke", TNoRw.getText());

            Sequel.queryu("delete from catatan_resep where no_rawat='" + TNoRw.getText() + "'");
            Sequel.queryu("delete from data_igd where no_rawat='" + TNoRw.getText() + "'");
            Sequel.meghapus("bridging_jamkesda", "no_sep", sepJkd.getText());
            Sequel.meghapus("bridging_jamkesda", "no_sep", sepJkdigd.getText());
            Sequel.meghapus("pasien_mati", "no_rkm_medis", NoRM.getText());
            Sequel.queryu("delete from reg_periksa where no_rawat='" + TNoRw.getText() + "'");
            Sequel.queryu("delete from lis_reg where no_rawat='" + TNoRw.getText() + "'");
            Sequel.queryu("delete from permintaan_lab_raza where no_rawat='" + TNoRw.getText() + "'");

            Sequel.mengedit("kelengkapan_booking_sep_bpjs", "kd_booking='" + cekSEPboking + "'", "status_cetak_sep='BELUM' ");
            Sequel.mengedit("booking_registrasi", "kd_booking='" + cekSEPboking + "'", "status_booking='Batal' ");

            Sequel.AutoComitTrue();
            tampilkasir();
        }
    }//GEN-LAST:event_MnHapusDataActionPerformed

    private void ppRiwayatBtnPrintActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_ppRiwayatBtnPrintActionPerformed
        if (tabModekasir.getRowCount() == 0) {
            JOptionPane.showMessageDialog(null, "Maaf, table masih kosong...!!!!");
            //TNoReg.requestFocus();
        } else if (TNoRw.getText().trim().equals("")) {
            JOptionPane.showMessageDialog(null, "Maaf, Silahkan anda pilih dulu dengan menklik data pada table...!!!");
            tbKasirRalan.requestFocus();
        } else {
            this.setCursor(Cursor.getPredefinedCursor(Cursor.WAIT_CURSOR));
            DlgResumePerawatan resume = new DlgResumePerawatan(null, true);
            resume.setNoRm(tbKasirRalan.getValueAt(tbKasirRalan.getSelectedRow(), 3).toString(), tbKasirRalan.getValueAt(tbKasirRalan.getSelectedRow(), 4).toString());
            resume.tampil();
            resume.setSize(internalFrame1.getWidth() - 40, internalFrame1.getHeight() - 40);
            resume.setLocationRelativeTo(internalFrame1);
            resume.setVisible(true);
            this.setCursor(Cursor.getDefaultCursor());
        }
    }//GEN-LAST:event_ppRiwayatBtnPrintActionPerformed

    private void ppBerkasBtnPrintActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_ppBerkasBtnPrintActionPerformed
        if (tabModekasir.getRowCount() == 0) {
            JOptionPane.showMessageDialog(null, "Maaf, tabel masih kosong...!!!!");
            //TNoReg.requestFocus();
        } else if (TNoRw.getText().trim().equals("")) {
            JOptionPane.showMessageDialog(null, "Maaf, Silahkan anda pilih dulu dengan menklik data pada table...!!!");
            tbKasirRalan.requestFocus();
        } else {
            Sequel.menyimpan("mutasi_berkas", "'" + TNoRw.getText() + "','Sudah Diterima',now(),now(),'0000-00-00 00:00:00','0000-00-00 00:00:00'", "status='Sudah Diterima',diterima=now()", "no_rawat='" + TNoRw.getText() + "'");
            Valid.editTable(tabModekasir, "reg_periksa", "no_rawat", TNoRw, "stts='Berkas Diterima'");
            if (tabModekasir.getRowCount() != 0) {
                tampilkasir();
            }
        }
    }//GEN-LAST:event_ppBerkasBtnPrintActionPerformed

    private void MnRujukActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_MnRujukActionPerformed
        if (tabModekasir.getRowCount() == 0) {
            JOptionPane.showMessageDialog(null, "Maaf, table masih kosong...!!!!");
            TCari.requestFocus();
        } else if (TNoRw.getText().trim().equals("")) {
            JOptionPane.showMessageDialog(null, "Maaf, Silahkan anda pilih dulu dengan menklik data pada table...!!!");
            tbKasirRalan.requestFocus();
        } else {
            this.setCursor(Cursor.getPredefinedCursor(Cursor.WAIT_CURSOR));
            DlgRujuk dlgrjk = new DlgRujuk(null, false);
            dlgrjk.setSize(internalFrame1.getWidth(), internalFrame1.getHeight());
            dlgrjk.setLocationRelativeTo(internalFrame1);
            dlgrjk.emptTeks();
            dlgrjk.isCek();
            dlgrjk.setNoRm(TNoRw.getText(), DTPCari1.getDate(), DTPCari2.getDate());
            dlgrjk.tampil();
            dlgrjk.setVisible(true);
            this.setCursor(Cursor.getDefaultCursor());
        }
    }//GEN-LAST:event_MnRujukActionPerformed

    private void ppCatatanPasienBtnPrintActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_ppCatatanPasienBtnPrintActionPerformed
        if (tabModekasir.getRowCount() == 0) {
            JOptionPane.showMessageDialog(null, "Maaf, data pasien sudah habis...!!!!");
            TCari.requestFocus();
        } else if (TNoRw.getText().trim().equals("")) {
            JOptionPane.showMessageDialog(null, "Maaf, Silahkan anda pilih dulu data registrasi pada table...!!!");
            TCari.requestFocus();
        } else {
            this.setCursor(Cursor.getPredefinedCursor(Cursor.WAIT_CURSOR));
            DlgCatatan catatan = new DlgCatatan(null, true);
            catatan.setNoRm(tbKasirRalan.getValueAt(tbKasirRalan.getSelectedRow(), 3).toString());
            catatan.setSize(720, 330);
            catatan.setLocationRelativeTo(internalFrame1);
            catatan.setVisible(true);
            this.setCursor(Cursor.getDefaultCursor());
        }
    }//GEN-LAST:event_ppCatatanPasienBtnPrintActionPerformed

    private void MnResepDOkterActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_MnResepDOkterActionPerformed
        if (tabModekasir.getRowCount() == 0) {
            JOptionPane.showMessageDialog(null, "Maaf, tabel masih kosong...!!!!");
            TCari.requestFocus();
        } else if (TNoRw.getText().trim().equals("")) {
            JOptionPane.showMessageDialog(null, "Maaf, Silahkan anda pilih dulu pasien...!!!");
            tbKasirRalan.requestFocus();
        } else {
            if (Sequel.cariInteger("select count(no_rawat) from kamar_inap where no_rawat=?", TNoRw.getText()) > 0) {
                JOptionPane.showMessageDialog(null, "Maaf, Pasien sudah masuk Kamar Inap. Gunakan billing Ranap..!!!");
            } else {
                DlgPeresepanDokter resep = new DlgPeresepanDokter(null, false);
                resep.setSize(internalFrame1.getWidth() - 40, internalFrame1.getHeight() - 40);
                resep.setLocationRelativeTo(internalFrame1);
                resep.isCek();
                resep.setNoRm(TNoRw.getText(), DTPCari1.getDate(), Jam.getText().substring(0, 2), Jam.getText().substring(3, 5), Jam.getText().substring(6, 8),
                        tbKasirRalan.getValueAt(tbKasirRalan.getSelectedRow(), 1).toString(), tbKasirRalan.getValueAt(tbKasirRalan.getSelectedRow(), 2).toString());
                resep.tampilobat();
                resep.setVisible(true);
            }
        }
    }//GEN-LAST:event_MnResepDOkterActionPerformed

    private void TabRawatMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_TabRawatMouseClicked
        tampilkasir();
    }//GEN-LAST:event_TabRawatMouseClicked

    private void MnPoliInternalActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_MnPoliInternalActionPerformed
        cekRujukInternal = 0;
        cekRujukInternal = Sequel.cariInteger("select count(-1) from rujukan_internal_poli where no_rawat='" + TNoRw.getText() + "'");
                
        if (tabModekasir.getRowCount() == 0) {
            JOptionPane.showMessageDialog(null, "Maaf, tabel masih kosong...!!!!");
            TCari.requestFocus();
        } else if (TNoRw.getText().trim().equals("")) {
            JOptionPane.showMessageDialog(null, "Maaf, Silahkan anda pilih dulu dengan mengklik data pada tabel...!!!");
            tbKasirRalan.requestFocus();
        } else {
            if (cekRujukInternal == 0 || cekRujukInternal > 1) {
                dlgrjk.setSize(787, 588);
                dlgrjk.setLocationRelativeTo(internalFrame1);
                dlgrjk.isCek();
                dlgrjk.setNoRm(TNoRw.getText(), tbKasirRalan.getValueAt(tbKasirRalan.getSelectedRow(), 3).toString(),
                        tbKasirRalan.getValueAt(tbKasirRalan.getSelectedRow(), 4).toString());
                dlgrjk.tampil();
                dlgrjk.inputbaru();                
                dlgrjk.setVisible(true);
            } else if (cekRujukInternal == 1) {
                dlgrjk.setSize(787, 588);
                dlgrjk.setLocationRelativeTo(internalFrame1);
                dlgrjk.isCek();
                dlgrjk.setNoRm(TNoRw.getText(), tbKasirRalan.getValueAt(tbKasirRalan.getSelectedRow(), 3).toString(),
                        tbKasirRalan.getValueAt(tbKasirRalan.getSelectedRow(), 4).toString());
                dlgrjk.SatuTujuanPoli();
                dlgrjk.tampil();                
                dlgrjk.setVisible(true);
                dlgrjk.BtnUnit.requestFocus();
            }
        }
    }//GEN-LAST:event_MnPoliInternalActionPerformed

    private void MnFormulirKlaimBtnPrintActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_MnFormulirKlaimBtnPrintActionPerformed
        if (tbKasirRalan.getRowCount() == 0) {
            JOptionPane.showMessageDialog(null, "Maaf, data sudah habis. Tidak ada data yang bisa anda print...!!!!");
            TCari.requestFocus();
        } else if (TNoRw.getText().equals("")) {
            JOptionPane.showMessageDialog(null, "Klik dulu salah satu nama pasiennya pada tabel...!!!!");
            tbKasirRalan.requestFocus();
        } else if (tbKasirRalan.getRowCount() != 0) {
            formulirKlaim();
        }
    }//GEN-LAST:event_MnFormulirKlaimBtnPrintActionPerformed

    private void MnLembarStatusPasienBtnPrintActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_MnLembarStatusPasienBtnPrintActionPerformed
        if (tbKasirRalan.getRowCount() == 0) {
            JOptionPane.showMessageDialog(null, "Maaf, data pasien sudah habis...!!!!");
            BtnCari.requestFocus();
        } else if (TNoRw.getText().equals("")) {
            JOptionPane.showMessageDialog(null, "Klik dulu salah satu nama pasiennya pada tabel...!!!!");
            tbKasirRalan.requestFocus();
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
            Valid.MyReport("rptRMRaza.jrxml", "report", "::[ Lembar Status Pasien Baru Rawat Jalan ]::",
                    " SELECT reg_periksa.no_rawat, pasien.no_rkm_medis,	pasien.nm_pasien, pasien.no_ktp, IF(pasien.jk='L','Laki-laki','Perempuan') as jk, "
                    + " pasien.tmp_lahir, DATE_FORMAT(pasien.tgl_lahir, '%d-%m-%Y') as tgl_lahir, concat(pasien.alamat,', ',kelurahan.nm_kel,', ',kecamatan.nm_kec,', ',kabupaten.nm_kab) AS alamat, "
                    + " pasien.gol_darah, pasien.pekerjaan, pasien.stts_nikah, pasien.agama, reg_periksa.tgl_registrasi, pasien.no_tlp,	pasien.umur, "
                    + " pasien.pnd, penjab.png_jawab FROM pasien INNER JOIN kelurahan INNER JOIN kecamatan "
                    + " INNER JOIN kabupaten INNER JOIN penjab ON pasien.kd_kel = kelurahan.kd_kel "
                    + " AND pasien.kd_kec = kecamatan.kd_kec AND pasien.kd_kab = kabupaten.kd_kab "
                    + " INNER JOIN reg_periksa ON reg_periksa.kd_pj = penjab.kd_pj AND reg_periksa.no_rkm_medis = pasien.no_rkm_medis "
                    + " WHERE reg_periksa.no_rawat='" + TNoRw.getText() + "' ", param);
            this.setCursor(Cursor.getDefaultCursor());
        }
    }//GEN-LAST:event_MnLembarStatusPasienBtnPrintActionPerformed

    private void CrPoliKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_CrPoliKeyPressed
        if (evt.getKeyCode() == KeyEvent.VK_ENTER) {
            BtnCariActionPerformed(null);
        } else if (evt.getKeyCode() == KeyEvent.VK_PAGE_DOWN) {
            BtnCari.requestFocus();
        } else if (evt.getKeyCode() == KeyEvent.VK_PAGE_UP) {
            BtnKeluar.requestFocus();
        }
    }//GEN-LAST:event_CrPoliKeyPressed

    private void MnCetakResepDokterActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_MnCetakResepDokterActionPerformed
        cekResep = 0;
        cekResep = Sequel.cariInteger("select COUNT(1) cek from catatan_resep where no_rawat ='" + TNoRw.getText() + "'");

        if (tabModekasir.getRowCount() == 0) {
            JOptionPane.showMessageDialog(null, "Maaf, tabel masih kosong...!!!!");
            TCari.requestFocus();
        } else if (TNoRw.getText().equals("")) {
            JOptionPane.showMessageDialog(null, "Klik dulu salah satu nama pasiennya pada tabel...!!!!");
            tbKasirRalan.requestFocus();
        } else if (cekResep == 0) {
            JOptionPane.showMessageDialog(null, "Pasien ini tidak/belum diberi e-Resep oleh dokter pemeriksanya...!!!!");
            tbKasirRalan.requestFocus();
            tampilkasir();
            empttext();
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
            Valid.MyReport("rptResepRalan.jrxml", "report", "::[ Resep Dokter Poliklinik/Unit Rawat Jalan ]::",
                    " select c.no_rawat, pl.nm_poli, d.nm_dokter, CONCAT('Martapura, ',DATE_FORMAT(c.tgl_perawatan,'%d/%m/%Y')) tgl_resep, c.nama_obat, "
                    + "r.no_rkm_medis, p.nm_pasien, CONCAT(r.umurdaftar,' ',r.sttsumur) umur, "
                    + "CONCAT(p.alamat,', ',kl.nm_kel,', ',kc.nm_kec,', ',kb.nm_kab) alamat from catatan_resep c "
                    + "inner join reg_periksa r on r.no_rawat = c.no_rawat inner join dokter d on d.kd_dokter = c.kd_dokter "
                    + "INNER JOIN poliklinik pl on pl.kd_poli=r.kd_poli INNER JOIN pasien p on p.no_rkm_medis=r.no_rkm_medis "
                    + "INNER JOIN kelurahan kl on kl.kd_kel=p.kd_kel INNER JOIN kecamatan kc on kc.kd_kec=p.kd_kec "
                    + "INNER JOIN kabupaten kb on kb.kd_kab=p.kd_kab where c.no_rawat ='" + TNoRw.getText() + "' order by c.noId", param);
            this.setCursor(Cursor.getDefaultCursor());
        }
    }//GEN-LAST:event_MnCetakResepDokterActionPerformed

    private void sepJkdKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_sepJkdKeyPressed
        // TODO add your handling code here:
    }//GEN-LAST:event_sepJkdKeyPressed

    private void sepJkdigdKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_sepJkdigdKeyPressed
        // TODO add your handling code here:
    }//GEN-LAST:event_sepJkdigdKeyPressed

    private void NoRMKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_NoRMKeyPressed
        // TODO add your handling code here:
    }//GEN-LAST:event_NoRMKeyPressed

    private void nmPasienKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_nmPasienKeyPressed
        // TODO add your handling code here:
    }//GEN-LAST:event_nmPasienKeyPressed

    private void MnSKDPBPJSActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_MnSKDPBPJSActionPerformed
        if (tabModekasir.getRowCount() == 0) {
            JOptionPane.showMessageDialog(null, "Maaf, tabel masih kosong...!!!!");
            TCari.requestFocus();
        } else if (TNoRw.getText().equals("")) {
            JOptionPane.showMessageDialog(null, "Klik dulu salah satu nama pasiennya pada tabel...!!!!");
            tbKasirRalan.requestFocus();
        } else {
            if (Sequel.cariInteger("select count(no_rawat) from kamar_inap where no_rawat=?", TNoRw.getText()) > 0) {
                JOptionPane.showMessageDialog(null, "Maaf, Pasien sudah masuk Kamar Inap. Gunakan billing Ranap..!!!");
            } else {
                DlgSKDPBPJS form = new DlgSKDPBPJS(null, false);
                form.isCek();
                form.setSize(internalFrame1.getWidth() - 40, internalFrame1.getHeight() - 40);
                form.setLocationRelativeTo(internalFrame1);
                form.emptTeks();
                form.setNoRm(NoRM.getText(), Sequel.cariIsi("select nm_pasien from pasien where no_rkm_medis='" + NoRM.getText() + "'"));
                form.DokterPoli(kddokter.getText(), kdpoli.getText());
                form.setVisible(true);
                form.fokus();
            }
        }
    }//GEN-LAST:event_MnSKDPBPJSActionPerformed

    private void MnPermintaanLabActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_MnPermintaanLabActionPerformed
        if (tabModekasir.getRowCount() == 0) {
            JOptionPane.showMessageDialog(null, "Maaf, tabel masih kosong...!!!!");
            TCari.requestFocus();
        } else if (TNoRw.getText().trim().equals("")) {
            JOptionPane.showMessageDialog(null, "Maaf, Silahkan anda pilih dulu dengan mengklik data pada tabel...!!!");
            tbKasirRalan.requestFocus();
        } else {
            if (Sequel.cariInteger("select count(no_rawat) from kamar_inap where no_rawat=?", TNoRw.getText()) > 0) {
                JOptionPane.showMessageDialog(null, "Maaf, Pasien sudah masuk Kamar Inap. Gunakan billing Ranap..!!!");
            } else {
//                this.setCursor(Cursor.getPredefinedCursor(Cursor.WAIT_CURSOR));
//                DlgPermintaanLaboratorium dlgro = new DlgPermintaanLaboratorium(null, false);
//                dlgro.setSize(internalFrame1.getWidth() - 40, internalFrame1.getHeight() - 40);
//                dlgro.setLocationRelativeTo(internalFrame1);
//                dlgro.emptTeks();
//                dlgro.isCek();
//                dlgro.setNoRm(TNoRw.getText(), "Ralan");
//                dlgro.setVisible(true);
//                this.setCursor(Cursor.getDefaultCursor());
                
                this.setCursor(Cursor.getPredefinedCursor(Cursor.WAIT_CURSOR));
                DlgPermintaanLabRAZA lab = new DlgPermintaanLabRAZA(null, false);
                lab.setSize(978, 631);
                lab.setLocationRelativeTo(internalFrame1);
                lab.isPasien(TNoRw.getText());
                lab.AutoNomerMinta();
                lab.nmPemeriksaan.setText("");
                lab.nmPemeriksaan.requestFocus();
                lab.tampil();
                lab.tampilItemLab();
                lab.setVisible(true);
                this.setCursor(Cursor.getDefaultCursor());
            }
        }
    }//GEN-LAST:event_MnPermintaanLabActionPerformed

    private void MnPermintaanRadiologiActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_MnPermintaanRadiologiActionPerformed
        if (tabModekasir.getRowCount() == 0) {
            JOptionPane.showMessageDialog(null, "Maaf, tabel masih kosong...!!!!");
            TCari.requestFocus();
        } else if (TNoRw.getText().trim().equals("")) {
            JOptionPane.showMessageDialog(null, "Maaf, Silahkan anda pilih dulu dengan mengklik data pada tabel...!!!");
            tbKasirRalan.requestFocus();
        } else {
            if (Sequel.cariInteger("select count(no_rawat) from kamar_inap where no_rawat=?", TNoRw.getText()) > 0) {
                JOptionPane.showMessageDialog(null, "Maaf, Pasien sudah masuk Kamar Inap. Gunakan billing Ranap..!!!");
            } else {
                this.setCursor(Cursor.getPredefinedCursor(Cursor.WAIT_CURSOR));
                DlgPermintaanRadiologi dlgro = new DlgPermintaanRadiologi(null, false);
                dlgro.setSize(internalFrame1.getWidth() - 40, internalFrame1.getHeight() - 40);
                dlgro.setLocationRelativeTo(internalFrame1);
                dlgro.emptTeks();
                dlgro.isCek();
                dlgro.setNoRm(TNoRw.getText(), "Ralan");
                dlgro.setVisible(true);
                this.setCursor(Cursor.getDefaultCursor());
            }
        }
    }//GEN-LAST:event_MnPermintaanRadiologiActionPerformed

    private void MnDietActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_MnDietActionPerformed
        if (tabModekasir.getRowCount() == 0) {
            JOptionPane.showMessageDialog(null, "Maaf, tabel masih kosong...!!!!");
            TCari.requestFocus();
        } else if (TNoRw.getText().equals("")) {
            JOptionPane.showMessageDialog(null, "Klik dulu salah satu nama pasiennya pada tabel...!!!!");
            tbKasirRalan.requestFocus();
        } else {
            if (kdpoli.getText().equals("HDL") || kdpoli.getText().equals("IGDK")) {
                DlgPemberianDiet dietralan = new DlgPemberianDiet(null, false);
                dietralan.setSize(internalFrame1.getWidth(), internalFrame1.getHeight());
                dietralan.setLocationRelativeTo(internalFrame1);
                dietralan.emptTeks();
                dietralan.setNoRm(TNoRw.getText(), DTPCari1.getDate(), DTPCari2.getDate());
                dietralan.isCek();
                dietralan.tabDataKlik();
                dietralan.setVisible(true);                
            } else {
                JOptionPane.showMessageDialog(rootPane, "Hanya untuk pasien Hemodialisa & IGD saja...!!!!");
            }
        }
    }//GEN-LAST:event_MnDietActionPerformed

    private void dataGZKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_dataGZKeyPressed
        // TODO add your handling code here:
    }//GEN-LAST:event_dataGZKeyPressed

    private void MnRekapTindakanPerbupActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_MnRekapTindakanPerbupActionPerformed
        if (tabModekasir.getRowCount() == 0) {
            JOptionPane.showMessageDialog(null, "Maaf, tabel masih kosong...!!!!");
            TCari.requestFocus();
        } else if (CrPoli.getText().trim().equals("")) {
            JOptionPane.showMessageDialog(null, "Silahkan pilih salah satu poli dari data yang sudah tersimpan...!!!");
            BtnSeek4.requestFocus();
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
            param.put("periode", "PERIODE TGL. " + DTPCari1.getSelectedItem() + " S.D " + DTPCari2.getSelectedItem());
            param.put("poli", "REKAP TINDAKAN PERBUP KAB. BANJAR PASIEN DI POLI/UNIT " + CrPoli.getText());
            Valid.MyReport("rptrekaptindakanperbupralan.jrxml", "report", "::[ Laporan Rekap Tindakan PERBUP Kab. Banjar Pasien Rawat Jalan ]::",
                    " SELECT tind.nm_perawatan, COUNT(case when r.kd_pj like 'U%' then 1 end) as Umum, COUNT(case when r.kd_pj like 'B%' then 1 end) as BPJS, "
                    + "COUNT(case when r.kd_pj like 'D%' then 1 end) as Jamkesda, COUNT(case when r.kd_pj like 'A%' then 1 end) as Asuransi, "
                    + "COUNT(case when (r.kd_pj not like 'U%' AND r.kd_pj not like 'B%' AND r.kd_pj not like 'D%' AND r.kd_pj not like 'A%')then 1 end) as Lainnya, "
                    + "COUNT(drpr.tgl_perawatan) AS Jumlah FROM rawat_jl_drpr drpr INNER JOIN jns_perawatan tind ON drpr.kd_jenis_prw = tind.kd_jenis_prw "
                    + "INNER JOIN reg_periksa r ON r.no_rawat = drpr.no_rawat INNER JOIN poliklinik po on po.kd_poli=r.kd_poli "
                    + "WHERE po.nm_poli like '%" + CrPoli.getText() + "%' AND drpr.tgl_perawatan between '" + Valid.SetTgl(DTPCari1.getSelectedItem() + "") + "' AND '" + Valid.SetTgl(DTPCari2.getSelectedItem() + "") + "' "
                    + "GROUP BY tind.nm_perawatan order by jumlah desc", param);
            this.setCursor(Cursor.getDefaultCursor());
        }
    }//GEN-LAST:event_MnRekapTindakanPerbupActionPerformed

    private void BtnPxBookingActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_BtnPxBookingActionPerformed
        WindowPasienBooking.setSize(575, 242);
        WindowPasienBooking.setLocationRelativeTo(internalFrame1);
        WindowPasienBooking.setAlwaysOnTop(false);
        WindowPasienBooking.setVisible(true);
        emptBooking();
    }//GEN-LAST:event_BtnPxBookingActionPerformed

    private void BtnPxBookingKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_BtnPxBookingKeyPressed
        // TODO add your handling code here:
    }//GEN-LAST:event_BtnPxBookingKeyPressed

    private void BtnCloseIn6ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_BtnCloseIn6ActionPerformed
        WindowPasienBooking.dispose();
    }//GEN-LAST:event_BtnCloseIn6ActionPerformed

    private void BtnSimpan6ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_BtnSimpan6ActionPerformed
        if (kdboking.getText().trim().equals("")) {
            Valid.textKosong(kdboking, "kode booking pasien");
        } else if (norm.getText().equals("")) {
            Valid.textKosong(norm, "Pasien");
        } else if (KdDokter.getText().equals("")) {
            Valid.textKosong(KdDokter, "Dokter Spesialis");
        } else if (kdpnj.getText().equals("B01") || (kdpnj.getText().equals("A03"))) {
            JOptionPane.showMessageDialog(null, "Untuk Pasien BPJS silakan lsg. ke counter Kios-K yang sdh. tersedia...");
            emptBooking();
        } else {
            if (!cekTerdaftar.getText().equals("")) {
                n = JOptionPane.showConfirmDialog(null, "Pasien ini sdh. terdaftar hari ini, Apakah akan didaftarkan lagi dg. poli berbeda ?", "Konfirmasi", JOptionPane.YES_NO_OPTION);
                if (n == JOptionPane.YES_OPTION) {
                    Sequel.cariIsi("select no_rkm_medis from reg_periksa where tgl_registrasi='" + Valid.SetTgl(tglPeriksa.getSelectedItem() + "") + "' and "
                            + "kd_poli='" + kdpoli1.getText() + "' and no_rkm_medis=?", cekPasien, norm.getText());

                    if (!cekPasien.getText().equals("")) {
                        JOptionPane.showMessageDialog(null, "Pasien ini sdh. terdaftar dipoli & tanggal yang sama...");
                        emptBooking();
                    } else if (cekPasien.getText().equals("")) {
                        Sequel.menyimpantf2("reg_periksa", "?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?", "No.Rawat", 17,
                                new String[]{TNoReg.getText(), norwBoking.getText(), Valid.SetTgl(tglPeriksa.getSelectedItem() + ""), CmbJam.getSelectedItem() + ":" + CmbMenit.getSelectedItem() + ":" + CmbDetik.getSelectedItem(),
                                    KdDokter.getText(), norm.getText(), kdpoli1.getText(), "-", "-", "-", 0 + "", "Belum",
                                    "Lama", "Ralan", kdpnj.getText(), umur, sttsumur});

                        Sequel.mengedit("booking_registrasi", "kd_booking='" + kdboking.getText() + "'", "status_booking='Terdaftar',no_rawat='" + norwBoking.getText() + "'");
                        emptBooking();
                        WindowPasienBooking.dispose();
                        tampilkasir();
                    }
                } else {
                    emptBooking();
                    WindowPasienBooking.dispose();
                    tampilkasir();
                }
            } else if (cekTerdaftar.getText().equals("")) {
                Sequel.menyimpantf2("reg_periksa", "?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?", "No.Rawat", 17,
                        new String[]{TNoReg.getText(), norwBoking.getText(), Valid.SetTgl(tglPeriksa.getSelectedItem() + ""), CmbJam.getSelectedItem() + ":" + CmbMenit.getSelectedItem() + ":" + CmbDetik.getSelectedItem(),
                            KdDokter.getText(), norm.getText(), kdpoli1.getText(), "-", "-", "-", 0 + "", "Belum",
                            "Lama", "Ralan", kdpnj.getText(), umur, sttsumur});

                Sequel.mengedit("booking_registrasi", "kd_booking='" + kdboking.getText() + "'", "status_booking='Terdaftar',no_rawat='" + norwBoking.getText() + "'");
                emptBooking();
                WindowPasienBooking.dispose();
                tampilkasir();
            }
        }
    }//GEN-LAST:event_BtnSimpan6ActionPerformed

    private void kdpoli1KeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_kdpoli1KeyPressed
        // TODO add your handling code here:
    }//GEN-LAST:event_kdpoli1KeyPressed

    private void kdbokingKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_kdbokingKeyPressed
        if (evt.getKeyCode() == KeyEvent.VK_ENTER) {
            Sequel.cariIsi("select kd_booking from booking_registrasi where kd_booking='" + kdboking.getText() + "' ", cekKodeBoking);

            if (kdboking.getText().trim().equals("")) {
                Valid.textKosong(kdboking, "kode booking pasien");
                emptBooking();
            } else if (cekKodeBoking.getText().trim().equals("")) {
                JOptionPane.showMessageDialog(null, "Kode booking pasien tidak ditemukan..!!");
                emptBooking();
            } else if (!kdboking.getText().trim().equals("") && (!cekKodeBoking.getText().equals(""))) {
                cekPasienBoking();
                nomorAuto();
                umurPasien();

                Sequel.cariIsi("select no_rkm_medis from reg_periksa where no_rkm_medis='" + norm.getText() + "' "
                        + "and tgl_registrasi='" + Valid.SetTgl(tglPeriksa.getSelectedItem() + "") + "'", cekTerdaftar);

                TCari.setText(norm.getText());
                BtnDokter.requestFocus();
            }
        }
    }//GEN-LAST:event_kdbokingKeyPressed

    private void normKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_normKeyPressed
        // TODO add your handling code here:
    }//GEN-LAST:event_normKeyPressed

    private void nmpasienKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_nmpasienKeyPressed
        // TODO add your handling code here:
    }//GEN-LAST:event_nmpasienKeyPressed

    private void kdpnjKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_kdpnjKeyPressed
        // TODO add your handling code here:
    }//GEN-LAST:event_kdpnjKeyPressed

    private void BtnDokterActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_BtnDokterActionPerformed
        dokter.setPoli(nmpoli1.getText());
        dokter.isCek();
        dokter.setHari(Valid.SetTgl(tglPeriksa.getSelectedItem() + ""));
        dokter.tampil();
        dokter.TCari.requestFocus();
        dokter.setSize(internalFrame1.getWidth() - 40, internalFrame1.getHeight() - 40);
        dokter.setLocationRelativeTo(internalFrame1);
        dokter.setVisible(true);
        dokter.emptTeks();
    }//GEN-LAST:event_BtnDokterActionPerformed

    private void BtnDokterKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_BtnDokterKeyPressed
        if (evt.getKeyCode() == KeyEvent.VK_SPACE) {
            BtnDokterActionPerformed(null);
        } else {
            Valid.pindah(evt, kdboking, BtnSimpan6);
        }
    }//GEN-LAST:event_BtnDokterKeyPressed

    private void TNoRegKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_TNoRegKeyPressed
        // TODO add your handling code here:
    }//GEN-LAST:event_TNoRegKeyPressed

    private void cekKodeBokingKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_cekKodeBokingKeyPressed
        // TODO add your handling code here:
    }//GEN-LAST:event_cekKodeBokingKeyPressed

    private void norwBokingKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_norwBokingKeyPressed
        // TODO add your handling code here:
    }//GEN-LAST:event_norwBokingKeyPressed

    private void tglPeriksaItemStateChanged(java.awt.event.ItemEvent evt) {//GEN-FIRST:event_tglPeriksaItemStateChanged

    }//GEN-LAST:event_tglPeriksaItemStateChanged

    private void tglPeriksaMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tglPeriksaMouseClicked

    }//GEN-LAST:event_tglPeriksaMouseClicked

    private void tglPeriksaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_tglPeriksaActionPerformed

    }//GEN-LAST:event_tglPeriksaActionPerformed

    private void tglPeriksaKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_tglPeriksaKeyPressed

    }//GEN-LAST:event_tglPeriksaKeyPressed

    private void kdbokingKeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_kdbokingKeyTyped
        evt.setKeyChar(Character.toUpperCase(evt.getKeyChar()));
    }//GEN-LAST:event_kdbokingKeyTyped

    private void CmbJamKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_CmbJamKeyPressed
//        Valid.pindah(evt, DTPReg, CmbMenit);
    }//GEN-LAST:event_CmbJamKeyPressed

    private void CmbMenitKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_CmbMenitKeyPressed
        Valid.pindah(evt, CmbJam, CmbDetik);
    }//GEN-LAST:event_CmbMenitKeyPressed

    private void CmbDetikKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_CmbDetikKeyPressed
        Valid.pindah(evt, CmbMenit, kddokter);
    }//GEN-LAST:event_CmbDetikKeyPressed

    private void cekTerdaftarKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_cekTerdaftarKeyPressed
        // TODO add your handling code here:
    }//GEN-LAST:event_cekTerdaftarKeyPressed

    private void cekPasienKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_cekPasienKeyPressed
        // TODO add your handling code here:
    }//GEN-LAST:event_cekPasienKeyPressed

    private void MnDataHAIsBtnPrintActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_MnDataHAIsBtnPrintActionPerformed
        if (tabModekasir.getRowCount() == 0) {
            JOptionPane.showMessageDialog(null, "Maaf, tabel masih kosong...!!!!");
            TCari.requestFocus();
        } else if (TNoRw.getText().equals("")) {
            JOptionPane.showMessageDialog(null, "Klik dulu salah satu nama pasiennya pada tabel...!!!!");
            tbKasirRalan.requestFocus();
        } else {
            DlgDataHAIs hais = new DlgDataHAIs(null, false);
            hais.setSize(internalFrame1.getWidth(), internalFrame1.getHeight());
            hais.setLocationRelativeTo(internalFrame1);
            hais.emptTeks();
            hais.isCek();
            hais.setNoRm(TNoRw.getText(), DTPCari1.getDate(), DTPCari2.getDate());
            hais.tampil();
            hais.setVisible(true);
        }
    }//GEN-LAST:event_MnDataHAIsBtnPrintActionPerformed

    private void MnPeniliaianAwalKeperawatanRalanActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_MnPeniliaianAwalKeperawatanRalanActionPerformed
        if (tabModekasir.getRowCount() == 0) {
            JOptionPane.showMessageDialog(null, "Maaf, tabel masih kosong...!!!!");
        } else if (TNoRw.getText().trim().equals("")) {
            JOptionPane.showMessageDialog(null, "Maaf, Silahkan anda pilih dulu dengan menklik data pada tabel...!!!");
            tbKasirRalan.requestFocus();
        } else {
            if (tbKasirRalan.getSelectedRow() != -1) {
                this.setCursor(Cursor.getPredefinedCursor(Cursor.WAIT_CURSOR));
                RMPenilaianAwalKeperawatanRalan form = new RMPenilaianAwalKeperawatanRalan(null, false);
                form.isCek();
                form.emptTeks();
                form.setNoRm(TNoRw.getText(), DTPCari2.getDate());
                form.setSize(internalFrame1.getWidth(), internalFrame1.getHeight());
                form.setLocationRelativeTo(internalFrame1);
                form.setVisible(true);
                this.setCursor(Cursor.getDefaultCursor());
            }
        }
    }//GEN-LAST:event_MnPeniliaianAwalKeperawatanRalanActionPerformed

    private void MnDataTriaseIGDActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_MnDataTriaseIGDActionPerformed
        if (tabModekasir.getRowCount() == 0) {
            JOptionPane.showMessageDialog(null, "Maaf, tabel masih kosong...!!!!");
            TCari.requestFocus();
        } else if (TNoRw.getText().trim().equals("")) {
            JOptionPane.showMessageDialog(null, "Maaf, Silahkan anda pilih dulu dengan mengklik data pada tabel...!!!");
            tbKasirRalan.requestFocus();
        } else if (!kdpoli.getText().equals("IGDK")) {
            JOptionPane.showMessageDialog(null, "Hanya untuk pasien yang dirawat di IGD saja...!!!");
            tbKasirRalan.requestFocus();
        } else {
            if (tbKasirRalan.getSelectedRow() != -1) {
                this.setCursor(Cursor.getPredefinedCursor(Cursor.WAIT_CURSOR));
                RMTriaseIGD form = new RMTriaseIGD(null, false);
                form.isCek();
                form.setNoRm(TNoRw.getText(), NoRM.getText(), Sequel.cariIsi("select nm_pasien from pasien where no_rkm_medis='" + NoRM.getText() + "'"));
                form.setSize(internalFrame1.getWidth() - 40, internalFrame1.getHeight() - 40);
                form.setLocationRelativeTo(internalFrame1);
                form.setVisible(true);
                this.setCursor(Cursor.getDefaultCursor());
            }
        }
    }//GEN-LAST:event_MnDataTriaseIGDActionPerformed

    private void MnStatusPasienAllKunjunganBtnPrintActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_MnStatusPasienAllKunjunganBtnPrintActionPerformed
        x = 0;
        x = Sequel.cariInteger("SELECT count(1) cek FROM pemeriksaan_ralan_petugas prp INNER JOIN reg_periksa rp on rp.no_rawat=prp.no_rawat "
                + "INNER JOIN penjab pj on pj.kd_pj=rp.kd_pj INNER JOIN pasien p on p.no_rkm_medis=rp.no_rkm_medis "
                + "INNER JOIN kelurahan kl on kl.kd_kel=p.kd_kel INNER JOIN kecamatan kc on kc.kd_kec=p.kd_kec "
                + "INNER JOIN kabupaten kb on kb.kd_kab=p.kd_kab INNER JOIN dokter d on d.kd_dokter=rp.kd_dokter "
                + "LEFT JOIN diagnosa_pasien dp on dp.no_rawat=prp.no_rawat and dp.prioritas=1 "
                + "WHERE rp.no_rkm_medis = '" + NoRM.getText() + "' ");

        if (TNoRw.getText().equals("")) {
            JOptionPane.showMessageDialog(null, "Klik dulu salah satu nama pasiennya pada tabel...!!!!");
            tbKasirRalan.requestFocus();
        } else if (x == 0) {
            JOptionPane.showMessageDialog(null, "Data status pasien rawat jalan utk. semua kunjungan tdk. ditemukan..!!!!");
            tbKasirRalan.requestFocus();
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
            Valid.MyReport("rptRMRaza1.jrxml", "report", "::[ Lembar Status Pasien Rawat Jalan Yang Sudah Terisi Semua Kunjungan ]::",
                    " SELECT rp.no_rawat, p.no_rkm_medis, p.nm_pasien, ifnull(p.no_ktp,'-') no_ktp, IF (p.jk = 'L','Laki-laki','Perempuan') jk, "
                    + "p.tmp_lahir, DATE_FORMAT(p.tgl_lahir,'%d-%m-%Y') tgl_lahir, concat(p.alamat,', ',kl.nm_kel,', ',kc.nm_kec,', ',kb.nm_kab) alamat, "
                    + "p.gol_darah, p.pekerjaan, p.stts_nikah, p.agama, concat(date_format(rp.tgl_registrasi,'%d/%m/%y'),' ',pj.png_jawab) tgl_registrasi, p.no_tlp, p.umur, p.pnd, "
                    + "LOWER(prp.pemeriksaan) pemeriksaan, LOWER(prp.diagnosa) diagnosa, IFNULL(dp.kd_penyakit,'-') kd_icd_10, LOWER(IFNULL(prp.terapi,'-')) terapi, d.nm_dokter "
                    + "FROM pemeriksaan_ralan_petugas prp INNER JOIN reg_periksa rp on rp.no_rawat=prp.no_rawat "
                    + "INNER JOIN penjab pj on pj.kd_pj=rp.kd_pj INNER JOIN pasien p on p.no_rkm_medis=rp.no_rkm_medis "
                    + "INNER JOIN kelurahan kl on kl.kd_kel=p.kd_kel INNER JOIN kecamatan kc on kc.kd_kec=p.kd_kec "
                    + "INNER JOIN kabupaten kb on kb.kd_kab=p.kd_kab INNER JOIN dokter d on d.kd_dokter=rp.kd_dokter "
                    + "LEFT JOIN diagnosa_pasien dp on dp.no_rawat=prp.no_rawat and dp.prioritas=1 "
                    + "WHERE rp.no_rkm_medis = '" + NoRM.getText() + "' ORDER BY rp.tgl_registrasi DESC", param);
            this.setCursor(Cursor.getDefaultCursor());
        }
    }//GEN-LAST:event_MnStatusPasienAllKunjunganBtnPrintActionPerformed

    private void MnCariPermintaanLabActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_MnCariPermintaanLabActionPerformed
        if (tabModekasir.getRowCount() == 0) {
            JOptionPane.showMessageDialog(null, "Maaf, tabel masih kosong...!!!!");
            TCari.requestFocus();
        } else if (TNoRw.getText().trim().equals("")) {
            JOptionPane.showMessageDialog(null, "Maaf, Silahkan anda pilih dulu dengan mengklik data pada tabel...!!!");
            tbKasirRalan.requestFocus();
        } else {
            this.setCursor(Cursor.getPredefinedCursor(Cursor.WAIT_CURSOR));
            DlgCariPermintaanLab cariLab = new DlgCariPermintaanLab(null, false);
            cariLab.setSize(internalFrame1.getWidth() - 40, internalFrame1.getHeight() - 40);
            cariLab.setLocationRelativeTo(internalFrame1);
            cariLab.isCek("Ralan", TNoRw.getText());
            cariLab.TCari.setText(TNoRw.getText());
            cariLab.setVisible(true);
            this.setCursor(Cursor.getDefaultCursor());
        }
    }//GEN-LAST:event_MnCariPermintaanLabActionPerformed

    private void MnCariPermintaanRadActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_MnCariPermintaanRadActionPerformed
        if (tabModekasir.getRowCount() == 0) {
            JOptionPane.showMessageDialog(null, "Maaf, tabel masih kosong...!!!!");
            TCari.requestFocus();
        } else if (TNoRw.getText().trim().equals("")) {
            JOptionPane.showMessageDialog(null, "Maaf, Silahkan anda pilih dulu dengan mengklik data pada tabel...!!!");
            tbKasirRalan.requestFocus();
        } else {
            this.setCursor(Cursor.getPredefinedCursor(Cursor.WAIT_CURSOR));
            DlgCariPermintaanRadiologi cariRad = new DlgCariPermintaanRadiologi(null, false);
            cariRad.setSize(internalFrame1.getWidth() - 40, internalFrame1.getHeight() - 40);
            cariRad.setLocationRelativeTo(internalFrame1);
            cariRad.isCek();
            cariRad.TCari.setText(TNoRw.getText());
            cariRad.setVisible(true);
            this.setCursor(Cursor.getDefaultCursor());
        }
    }//GEN-LAST:event_MnCariPermintaanRadActionPerformed

    private void MnSuratTindakanDokterActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_MnSuratTindakanDokterActionPerformed
        cekSuratTindakan = 0;
        cekSuratTindakan = Sequel.cariInteger("select count(1) cek from surat_tindakan_kedokteran where no_rawat='" + TNoRw.getText() + "' and kasus_tindakan='Ralan'");

        if (tabModekasir.getRowCount() == 0) {
            JOptionPane.showMessageDialog(null, "Maaf, tabel masih kosong...!!!!");
            TCari.requestFocus();
        } else if (NoRM.getText().trim().equals("")) {
            JOptionPane.showMessageDialog(null, "Maaf, Silahkan anda pilih dulu dengan mengklik data pada tabel...!!!");
            tbKasirRalan.requestFocus();
        } else {
            DlgIGD surat = new DlgIGD(null, false);
            surat.DlgSuratTindakanDokter.setSize(662, 366);
            surat.DlgSuratTindakanDokter.setLocationRelativeTo(internalFrame1);
            surat.norwSurat.setText(TNoRw.getText());
            surat.TNoRM.setText(NoRM.getText());
            surat.DlgSuratTindakanDokter.setVisible(true);

            if (cekSuratTindakan == 0) {
                surat.TglSuratTindakan.setDate(new Date());
            } else {
                surat.cekSuratTindakan(TNoRw.getText());
            }

            surat.nmPJ.setText(Sequel.cariIsi("select namakeluarga from pasien where no_rkm_medis='" + NoRM.getText() + "'"));
            surat.nmPJ.requestFocus();

            if (surat.jnsSurat.getSelectedItem().toString().equals("PERSETUJUAN")) {
                surat.alasanTolak.setText("-");
                surat.alasanTolak.setEnabled(false);
            } else {
                surat.alasanTolak.setText("");
                surat.alasanTolak.setEnabled(true);
            }
        }
    }//GEN-LAST:event_MnSuratTindakanDokterActionPerformed

    private void ppPasienCoronaBtnPrintActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_ppPasienCoronaBtnPrintActionPerformed
        if (tabModekasir.getRowCount() == 0) {
            JOptionPane.showMessageDialog(null, "Maaf, data pasien sudah habis...!!!!");
            NoRM.requestFocus();
        } else if (nmPasien.getText().trim().equals("")) {
            JOptionPane.showMessageDialog(null, "Maaf, Silahkan anda pilih dulu data pasien dengan mengklik data pada tabel...!!!");
            tbKasirRalan.requestFocus();
        } else {
            cekPOLI = "";
            if (kdpoli.getText().equals("IGDK")) {
                cekPOLI = "IGD";
            } else {
                cekPOLI = "Poliklinik";
            }

            this.setCursor(Cursor.getPredefinedCursor(Cursor.WAIT_CURSOR));
            CoronaPasien form = new CoronaPasien(null, false);
            form.setPasien(NoRM.getText(), Tanggal.getText(), cekPOLI);
            form.isCek();
            form.setSize(internalFrame1.getWidth() - 40, internalFrame1.getHeight() - 40);
            form.setLocationRelativeTo(internalFrame1);
            form.setVisible(true);
            form.Inisial.requestFocus();
            this.setCursor(Cursor.getDefaultCursor());
        }
    }//GEN-LAST:event_ppPasienCoronaBtnPrintActionPerformed

    private void tbPasienMatiMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tbPasienMatiMouseClicked
        if (tabModeMati.getRowCount() != 0) {
            try {
                getDataMati();
            } catch (java.lang.NullPointerException e) {
            }
        }
    }//GEN-LAST:event_tbPasienMatiMouseClicked

    private void tbPasienMatiKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_tbPasienMatiKeyPressed
        if (tabModeMati.getRowCount() != 0) {
            if ((evt.getKeyCode() == KeyEvent.VK_ENTER) || (evt.getKeyCode() == KeyEvent.VK_UP) || (evt.getKeyCode() == KeyEvent.VK_DOWN)) {
                try {
                    getDataMati();
                } catch (java.lang.NullPointerException e) {
                }
            }
        }
    }//GEN-LAST:event_tbPasienMatiKeyPressed

    private void TCari1KeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_TCari1KeyPressed
        if (evt.getKeyCode() == KeyEvent.VK_ENTER) {
            BtnCari1ActionPerformed(null);
        } else if (evt.getKeyCode() == KeyEvent.VK_PAGE_DOWN) {
            BtnCari1.requestFocus();
        }
    }//GEN-LAST:event_TCari1KeyPressed

    private void BtnCari1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_BtnCari1ActionPerformed
        tampilMati();
    }//GEN-LAST:event_BtnCari1ActionPerformed

    private void BtnCari1KeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_BtnCari1KeyPressed
        if (evt.getKeyCode() == KeyEvent.VK_SPACE) {
            BtnCariActionPerformed(null);
        } else {
            Valid.pindah(evt, TCari, BtnAll);
        }
    }//GEN-LAST:event_BtnCari1KeyPressed

    private void BtnKeluar1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_BtnKeluar1ActionPerformed
        WindowPasienMati.dispose();
        emptMati();
        TCari1.setText("");
        tampilkasir();
    }//GEN-LAST:event_BtnKeluar1ActionPerformed

    private void BtnKeluar1KeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_BtnKeluar1KeyPressed
        if (evt.getKeyCode() == KeyEvent.VK_SPACE) {
            dispose();
        } else {
            Valid.pindah(evt, BtnCari1, TCari1);
        }
    }//GEN-LAST:event_BtnKeluar1KeyPressed

    private void rmMatiKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_rmMatiKeyPressed
        // TODO add your handling code here:
    }//GEN-LAST:event_rmMatiKeyPressed

    private void nmpxMatiKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_nmpxMatiKeyPressed
        // TODO add your handling code here:
    }//GEN-LAST:event_nmpxMatiKeyPressed

    private void tglMatiKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_tglMatiKeyPressed
        // TODO add your handling code here:
    }//GEN-LAST:event_tglMatiKeyPressed

    private void jamMatiKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_jamMatiKeyPressed
        // TODO add your handling code here:
    }//GEN-LAST:event_jamMatiKeyPressed

    private void ketMatiKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_ketMatiKeyPressed
        // TODO add your handling code here:
    }//GEN-LAST:event_ketMatiKeyPressed

    private void icdMatiKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_icdMatiKeyPressed
        // TODO add your handling code here:
    }//GEN-LAST:event_icdMatiKeyPressed

    private void desMatiKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_desMatiKeyPressed

    }//GEN-LAST:event_desMatiKeyPressed

    private void tglLahrMatiKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_tglLahrMatiKeyPressed
        // TODO add your handling code here:
    }//GEN-LAST:event_tglLahrMatiKeyPressed

    private void tmptMatiKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_tmptMatiKeyPressed
        // TODO add your handling code here:
    }//GEN-LAST:event_tmptMatiKeyPressed

    private void ppCekPaseinMatiBtnPrintActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_ppCekPaseinMatiBtnPrintActionPerformed
        if (tabModekasir.getRowCount() == 0) {
            JOptionPane.showMessageDialog(null, "Maaf, tabel masih kosong...!!!!");
        } else {
            this.setCursor(Cursor.getPredefinedCursor(Cursor.WAIT_CURSOR));
            WindowPasienMati.setSize(992, 460);
            WindowPasienMati.setLocationRelativeTo(internalFrame1);
            WindowPasienMati.setAlwaysOnTop(false);
            WindowPasienMati.setVisible(true);
            emptMati();
            TCari1.requestFocus();
            tampilMati();
            this.setCursor(Cursor.getDefaultCursor());
        }
    }//GEN-LAST:event_ppCekPaseinMatiBtnPrintActionPerformed

    private void BtnAll1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_BtnAll1ActionPerformed
        emptMati();
        tampilMati();
    }//GEN-LAST:event_BtnAll1ActionPerformed

    private void BtnAll1KeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_BtnAll1KeyPressed
        if (evt.getKeyCode() == KeyEvent.VK_SPACE) {
            tampilMati();
            TCari1.setText("");
        } else {
            Valid.pindah(evt, BtnCari1, BtnKeluar);
        }
    }//GEN-LAST:event_BtnAll1KeyPressed

    private void ppPerawatanCoronaBtnPrintActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_ppPerawatanCoronaBtnPrintActionPerformed
        if (tabModekasir.getRowCount() == 0) {
            JOptionPane.showMessageDialog(null, "Maaf, data pasien sudah habis...!!!!");
            tbKasirRalan.requestFocus();
        } else if (nmPasien.getText().trim().equals("")) {
            JOptionPane.showMessageDialog(null, "Maaf, Silahkan anda pilih dulu data pasien dengan mengklik data pada tabel...!!!");
            tbKasirRalan.requestFocus();
        } else {
            this.setCursor(Cursor.getPredefinedCursor(Cursor.WAIT_CURSOR));
            INACBGPerawatanCorona form = new INACBGPerawatanCorona(null, false);
            form.emptTeks();
            form.setPasien(TNoRw.getText(), NoRM.getText(), Sequel.cariIsi("select nm_pasien from pasien where no_rkm_medis='" + NoRM.getText() + "'"));
            form.isCek();
            form.setSize(internalFrame1.getWidth() - 40, internalFrame1.getHeight() - 40);
            form.setLocationRelativeTo(internalFrame1);
            form.setVisible(true);
            this.setCursor(Cursor.getDefaultCursor());
        }
    }//GEN-LAST:event_ppPerawatanCoronaBtnPrintActionPerformed

    private void MnSensusParuBtnPrintActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_MnSensusParuBtnPrintActionPerformed
        cekPOLI = "";
        cekPOLI = Sequel.cariIsi("SELECT ifnull(kode_unit,'-') kode_unit FROM hak_akses_unit WHERE nip='" + var.getkode() + "'");

        if (tabModekasir.getRowCount() == 0) {
            JOptionPane.showMessageDialog(null, "Maaf, tabel masih kosong...!!!!");
            TCari.requestFocus();
        } else if (TNoRw.getText().equals("")) {
            JOptionPane.showMessageDialog(null, "Klik dulu salah satu nama pasiennya pada tabel...!!!!");
            tbKasirRalan.requestFocus();
        } else if (var.getkode().equals("Admin Utama")) {
            DlgRawatJalan dlgrwjl2 = new DlgRawatJalan(null, false);
            dlgrwjl2.WindowDataParu.setSize(571, 302);
            dlgrwjl2.WindowDataParu.setLocationRelativeTo(internalFrame1);
            dlgrwjl2.WindowDataParu.setVisible(true);
            dlgrwjl2.PasienParu(TNoRw.getText());
            dlgrwjl2.cekDataParu(TNoRw.getText());
        } else {
            if (cekPOLI.equals("PAR") && kdpoli.getText().equals("PAR")) {
                DlgRawatJalan dlgrwjl2 = new DlgRawatJalan(null, false);
                dlgrwjl2.WindowDataParu.setSize(571, 302);
                dlgrwjl2.WindowDataParu.setLocationRelativeTo(internalFrame1);
                dlgrwjl2.WindowDataParu.setVisible(true);
                dlgrwjl2.PasienParu(TNoRw.getText());
                dlgrwjl2.cekDataParu(TNoRw.getText());
            } else {
                JOptionPane.showMessageDialog(null, "Pasien itu tidak terdaftar di poliklinik paru atau anda tidak ada hak aksesnya...!!!!");
                tbKasirRalan.requestFocus();
            }
        }
    }//GEN-LAST:event_MnSensusParuBtnPrintActionPerformed

    private void BtnCloseIn7ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_BtnCloseIn7ActionPerformed
        tampilkasir();
        empttext();
        WindowRiwayatKunjungan.dispose();
    }//GEN-LAST:event_BtnCloseIn7ActionPerformed

    private void BtnLewatiActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_BtnLewatiActionPerformed
        WindowRiwayatKunjungan.dispose();
        DlgRawatJalan dlgrwjl2 = new DlgRawatJalan(null, false);
        dlgrwjl2.setSize(internalFrame1.getWidth() - 40, internalFrame1.getHeight() - 40);
        dlgrwjl2.setLocationRelativeTo(internalFrame1);
        dlgrwjl2.setNoRm(TNoRw.getText(), DTPCari1.getDate(), DTPCari2.getDate());
        dlgrwjl2.tampilDrPr();
        dlgrwjl2.TotalNominal();
        dlgrwjl2.setVisible(true);
        dlgrwjl2.fokus();
        dlgrwjl2.petugas(kddokter.getText(), var.getkode());
        dlgrwjl2.isCek();
    }//GEN-LAST:event_BtnLewatiActionPerformed

    private void tbRiwayatKunjMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tbRiwayatKunjMouseClicked
        if (tabModeKunjungan.getRowCount() != 0) {
            try {
                getdataRiwKunj();
            } catch (java.lang.NullPointerException e) {
            }
        }
    }//GEN-LAST:event_tbRiwayatKunjMouseClicked

    private void tbRiwayatKunjKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_tbRiwayatKunjKeyPressed
        if (tabModeKunjungan.getRowCount() != 0) {
            if ((evt.getKeyCode() == KeyEvent.VK_ENTER) || (evt.getKeyCode() == KeyEvent.VK_UP) || (evt.getKeyCode() == KeyEvent.VK_DOWN)) {
                try {
                    getdataRiwKunj();
                } catch (java.lang.NullPointerException e) {
                }
            } else if (evt.getKeyCode() == KeyEvent.VK_V) {
                if (tbRiwayatKunj.getSelectedColumn() > 4) {
                    if (tbRiwayatKunj.getSelectedRow() != -1) {
                        if (tbRiwayatKunj.getValueAt(tbRiwayatKunj.getSelectedRow(), tbRiwayatKunj.getSelectedColumn()).toString().equals("false")) {
                            tbRiwayatKunj.setValueAt(true, tbRiwayatKunj.getSelectedRow(), tbRiwayatKunj.getSelectedColumn());
                        } else {
                            tbRiwayatKunj.setValueAt(false, tbRiwayatKunj.getSelectedRow(), tbRiwayatKunj.getSelectedColumn());
                        }
                    }
                }
            }
        }
    }//GEN-LAST:event_tbRiwayatKunjKeyPressed

    private void pasiendipilihKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_pasiendipilihKeyPressed

    }//GEN-LAST:event_pasiendipilihKeyPressed

    private void BtnRMActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_BtnRMActionPerformed
        if (pasiendipilih.getText().trim().equals("")) {
            JOptionPane.showMessageDialog(null, "Pilih dulu salah satu data pasiennya pada tabel...!!!!");
            tbRiwayatKunj.requestFocus();
        } else if (norw_dipilih.equals("")) {
            JOptionPane.showMessageDialog(null, "Pilih dulu salah satu data pasiennya pada tabel...!!!!");
            tbRiwayatKunj.requestFocus();
        } else {
            WindowRiwayatKunjungan.dispose();
            DlgRawatJalan dlgrwjl2 = new DlgRawatJalan(null, false);
            dlgrwjl2.setSize(internalFrame1.getWidth() - 40, internalFrame1.getHeight() - 40);
            dlgrwjl2.setLocationRelativeTo(internalFrame1);
            dlgrwjl2.setNoRm(norw_dipilih, TglKunRwt.getDate(), TglKunRwt.getDate());
            dlgrwjl2.tampilDrPr();
            dlgrwjl2.TotalNominal();
            dlgrwjl2.setVisible(true);
            dlgrwjl2.fokus();
            dlgrwjl2.petugas(kddokter_dipilih, var.getkode());
            dlgrwjl2.isCek();
        }
    }//GEN-LAST:event_BtnRMActionPerformed

    private void TglKunRwtItemStateChanged(java.awt.event.ItemEvent evt) {//GEN-FIRST:event_TglKunRwtItemStateChanged
        // TODO add your handling code here:
    }//GEN-LAST:event_TglKunRwtItemStateChanged

    private void TglKunRwtKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_TglKunRwtKeyPressed
        // TODO add your handling code here:
    }//GEN-LAST:event_TglKunRwtKeyPressed

    private void MnLihatSEPBtnPrintActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_MnLihatSEPBtnPrintActionPerformed
        cekSEP = 0;
        kdpenjab.setText(Sequel.cariIsi("select kd_pj from reg_periksa where no_rawat='" + TNoRw.getText() + "'"));        
        
        if (tabModekasir.getRowCount() == 0) {
            JOptionPane.showMessageDialog(null, "Maaf, tabel masih kosong...!!!!");
            BtnCari.requestFocus();
        } else if (TNoRw.getText().equals("")) {
            JOptionPane.showMessageDialog(null, "Silakan klik dulu nama pasiennya pada tabel..!!");
            tbKasirRalan.requestFocus();
        } else if (kdpenjab.getText().equals("")) {
            JOptionPane.showMessageDialog(null, "Silakan klik dulu nama pasiennya pada tabel..!!");
            tbKasirRalan.requestFocus();
        } else if (kdpenjab.getText().equals("B01")) {
            cekSEP = Sequel.cariInteger("select count(-1) from bridging_sep where no_rawat='" + TNoRw.getText() + "' and jnspelayanan='2'");
            if (cekSEP == 0) {
                JOptionPane.showMessageDialog(null, "SEP BPJS untuk pasien ini masih belum ada..!!");
                tbKasirRalan.requestFocus();
            } else if (cekSEP >= 1) {
                DlgNoSEP.setSize(451, 154);
                DlgNoSEP.setLocationRelativeTo(internalFrame1);
                DlgNoSEP.setVisible(true);

                nosep.setText(Sequel.cariIsi("select no_sep from bridging_sep where no_rawat='" + TNoRw.getText() + "' and jnspelayanan='2'"));
                tglsep.setText(Sequel.cariIsi("select day(tglsep) from bridging_sep where no_sep='" + nosep.getText() + "' and jnspelayanan='2'") + " "
                        + Sequel.bulanINDONESIA("select month(tglsep) from bridging_sep where no_sep='" + nosep.getText() + "' and jnspelayanan='2'") + " "
                        + Sequel.cariIsi("select year(tglsep) from bridging_sep where no_sep='" + nosep.getText() + "' and jnspelayanan='2'"));
                jnsBayar.setText(Sequel.cariIsi("select png_jawab from penjab where kd_pj='" + kdpenjab.getText() + "'"));
            }
        } else if (!kdpenjab.getText().equals("B01")) {
            JOptionPane.showMessageDialog(null, "Pasien ini tidak menggunakan cara bayar BPJS Kesehatan..!!");
            tbKasirRalan.requestFocus();
        }
    }//GEN-LAST:event_MnLihatSEPBtnPrintActionPerformed

    private void BtnCloseIn3ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_BtnCloseIn3ActionPerformed
        DlgNoSEP.dispose();
        empttext();
        tampilkasir();
    }//GEN-LAST:event_BtnCloseIn3ActionPerformed

    private void BtnCloseIn3KeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_BtnCloseIn3KeyPressed
        // TODO add your handling code here:
    }//GEN-LAST:event_BtnCloseIn3KeyPressed

    private void tglsepKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_tglsepKeyPressed

    }//GEN-LAST:event_tglsepKeyPressed

    private void nosepKeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_nosepKeyTyped

    }//GEN-LAST:event_nosepKeyTyped

    private void nosepKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_nosepKeyPressed
        // TODO add your handling code here:
    }//GEN-LAST:event_nosepKeyPressed

    private void jnsBayarKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_jnsBayarKeyPressed

    }//GEN-LAST:event_jnsBayarKeyPressed

    private void BtnPrint2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_BtnPrint2ActionPerformed
        if (nosep.getText().equals("")) {
            JOptionPane.showMessageDialog(null, "Data SEP tidak ditemukan...!!!!");
        } else {
            this.setCursor(Cursor.getPredefinedCursor(Cursor.WAIT_CURSOR));
            Map<String, Object> param = new HashMap<>();
            param.put("namars", var.getnamars());
            param.put("logo", Sequel.cariGambar("select bpjs from gambar"));

            if (Sequel.cariIsi("select urutan_sep from bridging_sep where no_rawat='" + TNoRw.getText() + "' and jnspelayanan='2'").equals("1")) {
                param.put("kunjunganInternal", "-");
            } else {
                param.put("kunjunganInternal", "- Kunjungan rujukan internal");
            }

            if (Sequel.cariIsi("select kd_poli from reg_periksa where no_rawat='" + TNoRw.getText() + "'").equals("HIV")) {
                param.put("subSpesialis", Sequel.cariIsi("select nm_poli from poliklinik where kd_poli='HIV'"));
                param.put("dokter", Sequel.cariIsi("select d.nm_dokter from reg_periksa rp inner join dokter d on d.kd_dokter=rp.kd_dokter "
                        + "where rp.no_rawat='" + TNoRw.getText() + "'"));

                Valid.MyReport("rptBridgingSEPvct.jrxml", "report", "::[ Cetak SEP Rawat Jalan Poliklinik VCT ]::",
                        " SELECT no_sep, tglsep, CONCAT(no_kartu,' (MR. ',nomr,')') nomor, nama_pasien, "
                        + "CONCAT(tanggal_lahir,' Kelamin : ',if(jkel='L','Laki-laki','Perempuan')) tgl_lhr, IFNULL(notelep,'') notelp, "
                        + "nmpolitujuan sub_spesialis, nmdpjpLayan doktr, nmppkrujukan faskes_perujuk, nmdiagnosaawal diag_awal, catatan, "
                        + "peserta, if(jnspelayanan='2','R. Jalan','R.Inap') jns_rawat, SUBSTRING(tujuanKunjungan,4,14) jns_kun, '-' poli_perujuk, "
                        + "if(klsrawat='1','Kelas 1',if(klsrawat='2','Kelas 2','Kelas 3')) klsHak, IFNULL(nmKelasNaiknya,'') nmklsnaik, "
                        + "if(penjamin='null','',penjamin) penjamin FROM bridging_sep WHERE no_rawat='" + TNoRw.getText() + "' "
                        + "and jnspelayanan='2'", param);
            } else {
                Valid.MyReport("rptBridgingSEP2.jrxml", "report", "::[ Cetak SEP Rawat Jalan ]::",
                        " SELECT no_sep, tglsep, CONCAT(no_kartu,' (MR. ',nomr,')') nomor, nama_pasien, "
                        + "CONCAT(tanggal_lahir,' Kelamin : ',if(jkel='L','Laki-laki','Perempuan')) tgl_lhr, IFNULL(notelep,'') notelp, "
                        + "nmpolitujuan sub_spesialis, nmdpjpLayan doktr, nmppkrujukan faskes_perujuk, nmdiagnosaawal diag_awal, catatan, "
                        + "peserta, if(jnspelayanan='2','R. Jalan','R.Inap') jns_rawat, SUBSTRING(tujuanKunjungan,4,14) jns_kun, '-' poli_perujuk, "
                        + "if(klsrawat='1','Kelas 1',if(klsrawat='2','Kelas 2','Kelas 3')) klsHak, IFNULL(nmKelasNaiknya,'') nmklsnaik, "
                        + "if(penjamin='null','',penjamin) penjamin FROM bridging_sep WHERE no_rawat='" + TNoRw.getText() + "' "
                        + "and jnspelayanan='2'", param);
            }

            Sequel.mengedit("kelengkapan_booking_sep_bpjs", "no_rawat='" + TNoRw.getText() + "'", "status_cetak_sep='SUDAH' ");

            DlgNoSEP.dispose();
            empttext();
            tampilkasir();
            this.setCursor(Cursor.getDefaultCursor());
        }
    }//GEN-LAST:event_BtnPrint2ActionPerformed

    private void BtnPrint2KeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_BtnPrint2KeyPressed

    }//GEN-LAST:event_BtnPrint2KeyPressed

    private void ppSuratKontrolBtnPrintActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_ppSuratKontrolBtnPrintActionPerformed
        if (tabModekasir.getRowCount() == 0) {
            JOptionPane.showMessageDialog(null, "Maaf, tabel masih kosong...!!!!");
            TCari.requestFocus();
        } else if (TNoRw.getText().trim().equals("")) {
            JOptionPane.showMessageDialog(null, "Maaf, Silahkan anda pilih dulu dengan mengklik data pada tabel...!!!");
            tbKasirRalan.requestFocus();
        } else if (Sequel.cariInteger("select count(-1) from pasien_mati where no_rkm_medis='" + NoRM.getText() + "'") > 0) {
            JOptionPane.showMessageDialog(null, "Pasien ini sudah berpulang ke rahmatullah, semoga Husnul Khotimah...!!!");
            tbKasirRalan.requestFocus();
        } else {
            cekSEP = 0;
            cekSEP = Sequel.cariInteger("select count(-1) from bridging_sep where no_rawat='" + TNoRw.getText() + "'");
            if (cekSEP == 0) {
                JOptionPane.showMessageDialog(null, "Pasien ini belum mempunyai/dibikinkan SEP BPJS nya...!!!");
            } else if (cekSEP > 0) {
                BPJSSuratKontrol form = new BPJSSuratKontrol(null, false);
                form.setNoRm(TNoRw.getText(),
                        Sequel.cariIsi("select no_sep from bridging_sep where no_rawat='" + TNoRw.getText() + "'"),
                        Sequel.cariIsi("select no_kartu from bridging_sep where no_rawat='" + TNoRw.getText() + "'"),
                        NoRM.getText(),
                        nmPasien.getText(),
                        Sequel.cariIsi("select tgl_lahir from pasien where no_rkm_medis='" + NoRM.getText() + "'"),
                        Sequel.cariIsi("select IF(jk='L','Laki-laki','Perempuan') from pasien where no_rkm_medis='" + NoRM.getText() + "'"),
                        Sequel.cariIsi("select nmdiagnosaawal from bridging_sep where no_rawat='" + TNoRw.getText() + "'"));
                form.setSize(internalFrame1.getWidth() - 40, internalFrame1.getHeight() - 40);
                form.setLocationRelativeTo(internalFrame1);                
                form.ChkInput.setSelected(true);
                form.TCari.setText(NoRM.getText());
                form.tampil();                
                form.isForm();
                form.isCek();
                form.setVisible(true);
            }
        }
    }//GEN-LAST:event_ppSuratKontrolBtnPrintActionPerformed

    private void ChkAutoRefresActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_ChkAutoRefresActionPerformed
        if (ChkAutoRefres.isSelected() == true) {
            if (!DTPCari1.getSelectedItem().equals(DTPCari2.getSelectedItem())) {
                JOptionPane.showMessageDialog(null, "Tgl. periode harus sama, jika berbeda fitur ini tdk. bisa digunakan..!!");
                ChkAutoRefres.setSelected(false);
            } else if (CrPoli.getText().equals("")) {
                JOptionPane.showMessageDialog(null, "Poliklinik dimaksud harus dipilih dulu..!!");
                ChkAutoRefres.setSelected(false);
                BtnSeek4.requestFocus();
            } else {
                OtomatisRefresStart();
            }
        } else {
            OtomatisRefresStop();
            tampilkasir();
            empttext();
        }
    }//GEN-LAST:event_ChkAutoRefresActionPerformed

    private void MnKlaimJKNActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_MnKlaimJKNActionPerformed
        crBayar = "";

        if (tabModekasir.getRowCount() == 0) {
            JOptionPane.showMessageDialog(null, "Maaf, tabel masih kosong...!!!!");
            TCari.requestFocus();
        } else if (TNoRw.getText().equals("")) {
            JOptionPane.showMessageDialog(null, "Klik dulu salah satu nama pasiennya pada tabel...!!!!");
            tbKasirRalan.requestFocus();
        } else {
            crBayar = Sequel.cariIsi("select kd_pj from reg_periksa where no_rawat='" + TNoRw.getText() + "'");
            if (crBayar.equals("B01") || crBayar.equals("A03")) {
                INACBGDaftarKlaim diklaim = new INACBGDaftarKlaim(null, false);
                diklaim.isCek();
                diklaim.setSize(internalFrame1.getWidth() - 40, internalFrame1.getHeight() - 40);
                diklaim.setLocationRelativeTo(internalFrame1);
                diklaim.verifData();
                diklaim.KlaimRAZA(TNoRw.getText(), Sequel.cariIsi("select no_sep from bridging_sep where no_rawat='" + TNoRw.getText() + "'"), "JKN", "3");               
                diklaim.setVisible(true);
            } else {
                JOptionPane.showMessageDialog(null, "Hanya untuk klaim pasien JKN/BPJS Kesehatan saja...!!!!");
            }
        }
    }//GEN-LAST:event_MnKlaimJKNActionPerformed

    private void MnKlaimCOVIDActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_MnKlaimCOVIDActionPerformed
        if (tabModekasir.getRowCount() == 0) {
            JOptionPane.showMessageDialog(null, "Maaf, tabel masih kosong...!!!!");
            TCari.requestFocus();
        } else if (TNoRw.getText().equals("")) {
            JOptionPane.showMessageDialog(null, "Klik dulu salah satu nama pasiennya pada tabel...!!!!");
            tbKasirRalan.requestFocus();
        } else {
            INACBGDaftarKlaim diklaim = new INACBGDaftarKlaim(null, false);
            diklaim.isCek();
            diklaim.setSize(internalFrame1.getWidth() - 40, internalFrame1.getHeight() - 40);
            diklaim.setLocationRelativeTo(internalFrame1);
            diklaim.verifData();
            diklaim.KlaimRAZA(TNoRw.getText(), "", "JAMINAN COVID-19", "71");
            diklaim.setVisible(true);
        }
    }//GEN-LAST:event_MnKlaimCOVIDActionPerformed

    private void MnKlaimKIPIActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_MnKlaimKIPIActionPerformed
        if (tabModekasir.getRowCount() == 0) {
            JOptionPane.showMessageDialog(null, "Maaf, tabel masih kosong...!!!!");
            TCari.requestFocus();
        } else if (TNoRw.getText().equals("")) {
            JOptionPane.showMessageDialog(null, "Klik dulu salah satu nama pasiennya pada tabel...!!!!");
            tbKasirRalan.requestFocus();
        } else {
            INACBGDaftarKlaim diklaim = new INACBGDaftarKlaim(null, false);
            diklaim.isCek();
            diklaim.setSize(internalFrame1.getWidth() - 40, internalFrame1.getHeight() - 40);
            diklaim.setLocationRelativeTo(internalFrame1);
            diklaim.verifData();
            diklaim.KlaimRAZA(TNoRw.getText(), "", "JAMINAN KIPI","72");
            diklaim.setVisible(true);
        }
    }//GEN-LAST:event_MnKlaimKIPIActionPerformed

    private void MnSEPBPJSActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_MnSEPBPJSActionPerformed
        if (tabModekasir.getRowCount() == 0) {
            JOptionPane.showMessageDialog(null, "Maaf, tabel masih kosong...!!!!");
            DTPCari1.requestFocus();
        } else if (TNoRw.getText().trim().equals("")) {
            JOptionPane.showMessageDialog(null, "Maaf, Silahkan anda pilih dulu dengan mengklik data pada tabel...!!!");
            tbKasirRalan.requestFocus();
        } else if (!Sequel.cariIsi("select kd_pj from reg_periksa where no_rawat='" + TNoRw.getText() + "'").equals("B01")) {
            JOptionPane.showMessageDialog(null, "Hanya untuk pasien BPJS Kesehatan saja...!!!");
            tbKasirRalan.requestFocus();
        } else {
            this.setCursor(Cursor.getPredefinedCursor(Cursor.WAIT_CURSOR));
            var.setform("DlgKasirRalan");
            BPJSDataSEP dlgki = new BPJSDataSEP(null, false);
            dlgki.setSize(internalFrame1.getWidth(), internalFrame1.getHeight());
            dlgki.setLocationRelativeTo(internalFrame1);
            dlgki.isCek();
            
            if (kdpoli.getText().equals("IGDK")) {
                dlgki.setNoRm(TNoRw.getText(), "2. Ralan", "IGD", "INSTALASI GAWAT DARURAT");
            } else {
                dlgki.setNoRm(TNoRw.getText(), "2. Ralan", kdpoli.getText(), Sequel.cariIsi("select nm_poli from poliklinik where kd_poli='" + kdpoli.getText() + "'"));
            }
            
            dlgki.tampil();
            dlgki.setVisible(true);
            dlgki.cekLAYAN();
//            dlgki.tampilNoRujukan(Sequel.cariIsi("select no_peserta from pasien where no_rkm_medis='" + NoRM.getText() + "'"));
            this.setCursor(Cursor.getDefaultCursor());
        }
    }//GEN-LAST:event_MnSEPBPJSActionPerformed

    private void MnDietMakananActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_MnDietMakananActionPerformed
        if (tabModekasir.getRowCount() == 0) {
            JOptionPane.showMessageDialog(null, "Maaf, tabel masih kosong...!!!!");
            TCari.requestFocus();
        } else {
            dietRalan.WindowLabelGiziRALAN.setSize(596, 370);
            dietRalan.WindowLabelGiziRALAN.setLocationRelativeTo(internalFrame1);
            dietRalan.WindowLabelGiziRALAN.setVisible(true);
            dietRalan.emptLabelGZ();
            dietRalan.tampilPoliGZ();
        }
    }//GEN-LAST:event_MnDietMakananActionPerformed

    private void BtnCloseIn8ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_BtnCloseIn8ActionPerformed
        WindowRehabMedik.dispose();
    }//GEN-LAST:event_BtnCloseIn8ActionPerformed

    private void BtnSimpan7ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_BtnSimpan7ActionPerformed
        cekPilihanRehab = 0;
        if (TNoRw.getText().trim().equals("")) {
            Valid.textKosong(TCari, "No. Rawat");
        }
        
        if (cmbRM.getSelectedIndex() == 0) {
            JOptionPane.showMessageDialog(rootPane, "Silahkan pilih salah satu pilihan jenis rehabilitasi medik dg. benar...!!!!");
            cmbRM.requestFocus();
        } else {
            cekPilihanRehab = Sequel.cariInteger("select count(-1) from data_rehab_medik where no_rawat='" + TNoRw.getText() + "'");
            
            if (cekPilihanRehab == 0) {
                Sequel.menyimpan("data_rehab_medik","?,?","Jenis Rehabilitasi Medik",2,new String[]{TNoRw.getText(),cmbRM.getSelectedItem().toString()});
            } else if (cekPilihanRehab > 0) {
                Sequel.mengedit("data_rehab_medik", "no_rawat=?", " jns_rehabmedik=?", 2, new String[]{cmbRM.getSelectedItem().toString(), TNoRw.getText()});
            }

            tampilkasir();
            WindowRehabMedik.dispose();
        }
    }//GEN-LAST:event_BtnSimpan7ActionPerformed

    private void MnRehabMedikActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_MnRehabMedikActionPerformed
        cekPilihanRehab = 0;
        
        if (tabModekasir.getRowCount() == 0) {
            JOptionPane.showMessageDialog(rootPane, "Maaf, tabel masih kosong...!!!!");
            TCari.requestFocus();
        } else if (TNoRw.getText().equals("")) {
            JOptionPane.showMessageDialog(rootPane, "Klik dulu salah satu nama pasiennya pada tabel...!!!!");
            tbKasirRalan.requestFocus();
        } else if (!kdpoli.getText().equals("IRM")) {
            JOptionPane.showMessageDialog(rootPane, "Hanya utk. pasien yg. berkunjung ke poliklinik rehabilitasi medik...!!!!");
            tbKasirRalan.requestFocus();
        } else {
            cekPilihanRehab = Sequel.cariInteger("select count(-1) from data_rehab_medik where no_rawat='" + TNoRw.getText() + "'");
            
            if (cekPilihanRehab == 0) {
                cmbRM.setSelectedIndex(0);
                cmbRM.requestFocus();
            } else if (cekPilihanRehab > 0) {
                cmbRM.setSelectedItem(Sequel.cariIsi("select jns_rehabmedik from data_rehab_medik where no_rawat='" + TNoRw.getText() + "'"));                
            }

            WindowRehabMedik.setSize(535, 84);
            WindowRehabMedik.setLocationRelativeTo(internalFrame1);
            WindowRehabMedik.setAlwaysOnTop(false);
            WindowRehabMedik.setVisible(true);
        }
    }//GEN-LAST:event_MnRehabMedikActionPerformed

    private void MnReturJualActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_MnReturJualActionPerformed
        this.setCursor(Cursor.getPredefinedCursor(Cursor.WAIT_CURSOR));
        if (tabModekasir.getRowCount() == 0) {
            JOptionPane.showMessageDialog(rootPane, "Maaf, tabel masih kosong...!!!!");
            TCari.requestFocus();
        } else if (TNoRw.getText().trim().equals("")) {
            JOptionPane.showMessageDialog(rootPane, "Klik dulu salah satu nama pasiennya pada tabel...!!!!");
            TCari.requestFocus();
        } else {
            if (Sequel.cariRegistrasi(TNoRw.getText()) > 0) {
                JOptionPane.showMessageDialog(rootPane, "Data billing sudah terverifikasi, data tidak boleh dihapus/diubah. Silahkan hubungi bagian kasir/keuangan ..!!");
            } else {
                var.setform("DlgKasirRalan");
                DlgReturJual returjual = new DlgReturJual(null, false);
                returjual.emptTeks();
                returjual.isCek();
                returjual.setPasien(NoRM.getText(), TNoRw.getText());
                returjual.setSize(internalFrame1.getWidth(), internalFrame1.getHeight());
                returjual.setLocationRelativeTo(internalFrame1);
                returjual.setVisible(true);
            }
        }
        this.setCursor(Cursor.getDefaultCursor());
    }//GEN-LAST:event_MnReturJualActionPerformed

    private void MnTeridentifikasiTBBtnPrintActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_MnTeridentifikasiTBBtnPrintActionPerformed
        if (TNoRw.getText().trim().equals("")) {
            JOptionPane.showMessageDialog(null, "Maaf, Silahkan anda pilih dulu pasien...!!!");
            tbKasirRalan.requestFocus();
        } else {
            if (Sequel.cariInteger("select count(no_rawat) from kamar_inap where no_rawat=?", TNoRw.getText()) > 0) {
                JOptionPane.showMessageDialog(null, "Maaf, Pasien sudah masuk Kamar Inap. Masukkan data teridentifikasi TB Kemenkes dikamar inap..!!!");
            } else if (Sequel.cariInteger("SELECT count(-1) FROM data_tb WHERE no_ktp='" + nik + "' or no_rkm_medis='" + NoRM.getText() + "'") < 1) {                
                this.setCursor(Cursor.getPredefinedCursor(Cursor.WAIT_CURSOR));
                DlgDataTB tb = new DlgDataTB(null, false);
                tb.setSize(internalFrame1.getWidth() - 40, internalFrame1.getHeight() - 40);
                tb.setLocationRelativeTo(internalFrame1);
                tb.isCek();
                tb.emptTeks();
                tb.setNoRM(TNoRw.getText());
                tb.setVisible(true);
                this.setCursor(Cursor.getDefaultCursor());
            } else {
                JOptionPane.showMessageDialog(null, "Data SITB pasien ini sudah ada, lakukan update pada halaman pasien SITB");
                this.setCursor(Cursor.getPredefinedCursor(Cursor.WAIT_CURSOR));
                DlgDataTB tb = new DlgDataTB(null, false);
                tb.setSize(internalFrame1.getWidth() - 40, internalFrame1.getHeight() - 40);
                tb.setLocationRelativeTo(internalFrame1);
                tb.isCek();
                tb.emptTeks();
                tb.TabRawat.setSelectedIndex(1);
                tb.tampil();
                tb.setVisible(true);
                this.setCursor(Cursor.getDefaultCursor());
            }
        }
    }//GEN-LAST:event_MnTeridentifikasiTBBtnPrintActionPerformed

    private void ppProgramPRBBtnPrintActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_ppProgramPRBBtnPrintActionPerformed
        if (TNoRw.getText().trim().equals("")) {
            JOptionPane.showMessageDialog(null, "Maaf, Silahkan anda pilih dulu pasien...!!!");
            TCari.requestFocus();
        } else if (!Sequel.cariIsi("select kd_pj from reg_periksa where no_rawat='" + TNoRw.getText() + "'").equals("B01")) {
            JOptionPane.showMessageDialog(null, "Fitur ini hanya untuk pasien BPJS saja...!!!");
        } else {
            this.setCursor(Cursor.getPredefinedCursor(Cursor.WAIT_CURSOR));
            BPJSProgramPRB form = new BPJSProgramPRB(null, false);
            form.setNoRm(TNoRw.getText(),
                    Sequel.cariIsi("select no_sep from bridging_sep where no_rawat='" + TNoRw.getText() + "'"),
                    Sequel.cariIsi("select no_peserta from pasien where no_rkm_medis='" + NoRM.getText() + "'"),
                    NoRM.getText(),
                    nmPasien.getText(),
                    Sequel.cariIsi("select alamat from pasien where no_rkm_medis='" + NoRM.getText() + "'"),
                    Sequel.cariIsi("select ifnull(dpjpLayan,'') from bridging_sep where no_rawat='" + TNoRw.getText() + "'"),
                    Sequel.cariIsi("select ifnull(nmdpjpLayan,'') from bridging_sep where no_rawat='" + TNoRw.getText() + "'"));
            form.setSize(internalFrame1.getWidth() - 40, internalFrame1.getHeight() - 40);
            form.setLocationRelativeTo(internalFrame1);
            form.setVisible(true);
            this.setCursor(Cursor.getDefaultCursor());
        }
    }//GEN-LAST:event_ppProgramPRBBtnPrintActionPerformed

    private void MnRencanaKontrolManualActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_MnRencanaKontrolManualActionPerformed
        if (tabModekasir.getRowCount() == 0) {
            JOptionPane.showMessageDialog(null, "Maaf, tabel masih kosong...!!!!");
            DTPCari1.requestFocus();
        } else if (TNoRw.getText().trim().equals("")) {
            JOptionPane.showMessageDialog(null, "Maaf, Silahkan anda pilih dulu dengan mengklik data pada tabel...!!!");
            tbKasirRalan.requestFocus();
        } else if (Sequel.cariInteger("select count(-1) from reg_periksa where no_rawat='" + TNoRw.getText() + "' and kd_pj='B01'") == 0) {
            JOptionPane.showMessageDialog(null, "Fitur ini hanya berlaku untuk pasien BPJS Kesehatan saja...!!!");
            tbKasirRalan.requestFocus();
        } else if (Sequel.cariInteger("select count(-1) from pasien_mati where no_rkm_medis='" + NoRM.getText() + "'") > 0) {
            JOptionPane.showMessageDialog(null, "Pasien ini sudah berpulang ke rahmatullah, semoga Husnul Khotimah...!!!");
            tbKasirRalan.requestFocus();
        } else {
            DlgRencanaKontrolManual form = new DlgRencanaKontrolManual(null, false);
            form.setSize(634, 221);
            form.setLocationRelativeTo(internalFrame1);
            form.setData("Ralan", kdpoli.getText(), NoRM.getText(), TDokter.getText());
            form.setVisible(true);
        }
    }//GEN-LAST:event_MnRencanaKontrolManualActionPerformed

    private void MnPeniliaianAwalMedisKebidananRalanActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_MnPeniliaianAwalMedisKebidananRalanActionPerformed
        if (tabModekasir.getRowCount() == 0) {
            JOptionPane.showMessageDialog(null, "Maaf, data registrasi sudah habis...!!!!");
            tbKasirRalan.requestFocus();
        } else if (TNoRw.getText().trim().equals("")) {
            JOptionPane.showMessageDialog(null, "Maaf, Silahkan anda pilih dulu data pasien dengan menklik data pada table...!!!");
            tbKasirRalan.requestFocus();
        } else if (!kdpoli.getText().equals("OBG")) {
            JOptionPane.showMessageDialog(null, "Fitur ini hanya untuk pasien yg. terdaftar dipoliklinik KEBIDANAN & KANDUNGAN...!!!");
            tbKasirRalan.requestFocus();
        } else {
            if (tbKasirRalan.getSelectedRow() != -1) {
                this.setCursor(Cursor.getPredefinedCursor(Cursor.WAIT_CURSOR));
                bid.isCek();
                bid.emptTeks();
                bid.setNoRm(TNoRw.getText(), DTPCari2.getDate(), kddokter.getText(), TDokter.getText());
                bid.setSize(internalFrame1.getWidth() - 40, internalFrame1.getHeight() - 40);
                bid.setLocationRelativeTo(internalFrame1);
                bid.setVisible(true);
                this.setCursor(Cursor.getDefaultCursor());
            }
        }
    }//GEN-LAST:event_MnPeniliaianAwalMedisKebidananRalanActionPerformed

    /**
     * @param args the command line arguments
     */
    public static void main(String args[]) {
        java.awt.EventQueue.invokeLater(() -> {
            DlgKasirRalan dialog = new DlgKasirRalan(new javax.swing.JFrame(), true);
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
    private widget.Button BtnAll;
    private widget.Button BtnAll1;
    private widget.Button BtnCari;
    private widget.Button BtnCari1;
    private widget.Button BtnCloseIn1;
    private widget.Button BtnCloseIn3;
    private widget.Button BtnCloseIn4;
    private widget.Button BtnCloseIn5;
    private widget.Button BtnCloseIn6;
    private widget.Button BtnCloseIn7;
    private widget.Button BtnCloseIn8;
    private widget.Button BtnDokter;
    private widget.Button BtnKeluar;
    private widget.Button BtnKeluar1;
    private widget.Button BtnLewati;
    private widget.Button BtnPrint2;
    private widget.Button BtnPxBooking;
    private widget.Button BtnRM;
    private widget.Button BtnSeek3;
    private widget.Button BtnSeek4;
    private widget.Button BtnSimpan1;
    private widget.Button BtnSimpan4;
    private widget.Button BtnSimpan5;
    private widget.Button BtnSimpan6;
    private widget.Button BtnSimpan7;
    public widget.CekBox ChkAutoRefres;
    private widget.CekBox ChkWaktu;
    private widget.ComboBox CmbDetik;
    private widget.ComboBox CmbJam;
    private widget.ComboBox CmbMenit;
    private widget.TextBox CrPoli;
    private widget.TextBox CrPtg;
    private widget.Tanggal DTPCari1;
    private widget.Tanggal DTPCari2;
    private javax.swing.JDialog DlgNoSEP;
    private widget.TextBox Jam;
    private widget.TextBox Kd2;
    private widget.TextBox KdDokter;
    private widget.Label LCount;
    private javax.swing.JMenuItem MnBatal;
    private javax.swing.JMenuItem MnBelum;
    private javax.swing.JMenuItem MnBilling;
    private javax.swing.JMenuItem MnCariPermintaanLab;
    private javax.swing.JMenuItem MnCariPermintaanRad;
    private javax.swing.JMenuItem MnCetakResepDokter;
    private javax.swing.JMenuItem MnDataHAIs;
    private javax.swing.JMenuItem MnDataPemberianObat;
    private javax.swing.JMenuItem MnDataRalan;
    private javax.swing.JMenuItem MnDataTriaseIGD;
    private javax.swing.JMenuItem MnDiagnosa;
    private javax.swing.JMenuItem MnDiet;
    private javax.swing.JMenuItem MnDietMakanan;
    private javax.swing.JMenuItem MnDokter;
    private javax.swing.JMenu MnEklaimINACBG;
    private javax.swing.JMenuItem MnFormulirKlaim;
    private javax.swing.JMenu MnGanti;
    private javax.swing.JMenuItem MnHapusData;
    private javax.swing.JMenu MnInputData;
    private javax.swing.JMenuItem MnKamarInap;
    private javax.swing.JMenuItem MnKlaimCOVID;
    private javax.swing.JMenuItem MnKlaimJKN;
    private javax.swing.JMenuItem MnKlaimKIPI;
    private javax.swing.JMenuItem MnLembarStatusPasien;
    private javax.swing.JMenuItem MnLihatSEP;
    private javax.swing.JMenuItem MnNoResep;
    private javax.swing.JMenu MnObatRalan;
    private javax.swing.JMenuItem MnOperasi;
    private javax.swing.JMenuItem MnPeniliaianAwalKeperawatanRalan;
    private javax.swing.JMenuItem MnPeniliaianAwalMedisKebidananRalan;
    private javax.swing.JMenuItem MnPenjab;
    private javax.swing.JMenuItem MnPeriksaLab;
    private javax.swing.JMenuItem MnPeriksaRadiologi;
    private javax.swing.JMenu MnPermintaan;
    private javax.swing.JMenuItem MnPermintaanLab;
    private javax.swing.JMenuItem MnPermintaanRadiologi;
    private javax.swing.JMenuItem MnPoli;
    private javax.swing.JMenuItem MnPoliInternal;
    private javax.swing.JMenuItem MnRehabMedik;
    private javax.swing.JMenu MnRekamMedis;
    private javax.swing.JMenu MnRekap;
    private javax.swing.JMenuItem MnRekapBulananDokter;
    private javax.swing.JMenuItem MnRekapBulananParamedis;
    private javax.swing.JMenuItem MnRekapHarianDokter;
    private javax.swing.JMenuItem MnRekapHarianObat;
    private javax.swing.JMenuItem MnRekapHarianParamedis;
    private javax.swing.JMenuItem MnRekapHarianPoli;
    private javax.swing.JMenuItem MnRekapTindakanPerbup;
    private javax.swing.JMenuItem MnRencanaKontrolManual;
    private javax.swing.JMenuItem MnResepDOkter;
    private javax.swing.JMenuItem MnReturJual;
    private javax.swing.JMenuItem MnRujuk;
    private javax.swing.JMenu MnRujukan;
    private javax.swing.JMenuItem MnSEPBPJS;
    private javax.swing.JMenuItem MnSKDPBPJS;
    private javax.swing.JMenuItem MnSensusParu;
    private javax.swing.JMenu MnStatus;
    private javax.swing.JMenuItem MnStatusPasienAllKunjungan;
    private javax.swing.JMenuItem MnSudah;
    private javax.swing.JMenuItem MnSuratTindakanDokter;
    private javax.swing.JMenuItem MnTeridentifikasiTB;
    private javax.swing.JMenu MnTindakanRalan;
    private widget.TextBox NmDokter;
    private widget.TextBox NoRM;
    private widget.ScrollPane Scroll1;
    private widget.ScrollPane Scroll2;
    private widget.ScrollPane Scroll3;
    private widget.ScrollPane Scroll5;
    public widget.TextBox TCari;
    private widget.TextBox TCari1;
    private widget.TextBox TDokter;
    private widget.TextBox TKdPny;
    private widget.TextBox TNoReg;
    private widget.TextBox TNoRw;
    private javax.swing.JTabbedPane TabRawat;
    private widget.TextBox Tanggal;
    private widget.Tanggal TglKunRwt;
    private javax.swing.JDialog WindowCaraBayar;
    private javax.swing.JDialog WindowGantiDokter;
    private javax.swing.JDialog WindowGantiPoli;
    private javax.swing.JDialog WindowPasienBooking;
    private javax.swing.JDialog WindowPasienMati;
    private javax.swing.JDialog WindowRehabMedik;
    public javax.swing.JDialog WindowRiwayatKunjungan;
    private widget.Button btnBayar;
    private widget.Button btnCariDokter;
    private widget.Button btnCariPoli;
    private javax.swing.ButtonGroup buttonGroup1;
    private widget.TextBox cekKodeBoking;
    private widget.TextBox cekPasien;
    private widget.TextBox cekTerdaftar;
    private widget.ComboBox cmbRM;
    private widget.ComboBox cmbStatus;
    private widget.TextBox dataGZ;
    private widget.TextArea desMati;
    private widget.TextBox icdMati;
    private widget.InternalFrame internalFrame1;
    private widget.InternalFrame internalFrame10;
    private widget.InternalFrame internalFrame3;
    private widget.InternalFrame internalFrame4;
    private widget.InternalFrame internalFrame5;
    private widget.InternalFrame internalFrame6;
    private widget.InternalFrame internalFrame7;
    private widget.InternalFrame internalFrame8;
    private widget.InternalFrame internalFrame9;
    private widget.Label jLabel10;
    private widget.Label jLabel11;
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
    private widget.Label jLabel30;
    private widget.Label jLabel31;
    private widget.Label jLabel32;
    private widget.Label jLabel33;
    private widget.Label jLabel34;
    private widget.Label jLabel44;
    private widget.Label jLabel45;
    private widget.Label jLabel46;
    private widget.Label jLabel6;
    private widget.Label jLabel7;
    private widget.Label jLabel8;
    private widget.Label jLabel9;
    private widget.Label jLabel94;
    private widget.Label jLabel95;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JPanel jPanel3;
    private javax.swing.JPopupMenu jPopupMenu1;
    private widget.TextBox jamMati;
    private widget.TextBox jnsBayar;
    private widget.TextBox kdboking;
    private widget.TextBox kddokter;
    private widget.TextBox kdpenjab;
    private widget.TextBox kdpnj;
    private widget.TextBox kdpoli;
    private widget.TextBox kdpoli1;
    private widget.TextBox ketMati;
    private widget.TextBox nmPasien;
    private widget.TextBox nmpasien;
    private widget.TextBox nmpenjab;
    private widget.TextBox nmpnj;
    private widget.TextBox nmpoli;
    private widget.TextBox nmpoli1;
    private widget.TextBox nmpxMati;
    private widget.TextBox norm;
    private widget.TextBox norwBoking;
    private widget.TextBox nosep;
    private widget.panelisi panelGlass6;
    private widget.panelisi panelGlass7;
    private widget.panelisi panelGlass8;
    private widget.panelisi panelisi1;
    private widget.panelisi panelisi2;
    private widget.TextBox pasiendipilih;
    private javax.swing.JMenuItem ppBerkas;
    private javax.swing.JMenuItem ppCatatanPasien;
    private javax.swing.JMenuItem ppCekPaseinMati;
    private javax.swing.JMenuItem ppPasienCorona;
    private javax.swing.JMenuItem ppPerawatanCorona;
    private javax.swing.JMenuItem ppProgramPRB;
    private javax.swing.JMenuItem ppRiwayat;
    private javax.swing.JMenuItem ppSuratKontrol;
    private widget.TextBox rmMati;
    private widget.TextBox sepJkd;
    private widget.TextBox sepJkdigd;
    private widget.Table tbKasirRalan;
    private widget.Table tbPasienMati;
    private widget.Table tbRiwayatKunj;
    private widget.TextBox tglLahrMati;
    private widget.TextBox tglMati;
    private widget.Tanggal tglPeriksa;
    private widget.TextBox tglsep;
    private widget.TextBox tmptMati;
    // End of variables declaration//GEN-END:variables

    public void tampilkasir() {
        Valid.tabelKosong(tabModekasir);
        try {
            pskasir = koneksi.prepareStatement("SELECT rp.no_rawat, rp.kd_dokter, d.nm_dokter, rp.no_rkm_medis, concat(p.nm_pasien,' (Usia : ',CONCAT(rp.umurdaftar,' ',rp.sttsumur),')') nm_pasien, rp.stts, "
                    + "pl.nm_poli, pj.png_jawab, rp.stts_daftar, IF(br.no_rawat = rp.no_rawat,'Online','-') reg_onlen, rp.tgl_registrasi, rp.jam_reg, rp.no_reg, IFNULL(enc.klaim_final, '-') stts_klaim, "
                    + "p.no_tlp, CONCAT(p.alamat,', ',kl.nm_kel,', ',kc.nm_kec,', ',kb.nm_kab) almt_pasien "
                    + "FROM reg_periksa rp INNER JOIN dokter d ON rp.kd_dokter = d.kd_dokter INNER JOIN pasien p ON rp.no_rkm_medis =p.no_rkm_medis "
                    + "INNER JOIN poliklinik pl ON rp.kd_poli = pl.kd_poli INNER JOIN penjab pj ON rp.kd_pj = pj.kd_pj "
                    + "INNER JOIN kelurahan kl ON kl.kd_kel=p.kd_kel INNER JOIN kecamatan kc ON kc.kd_kec=p.kd_kec "
                    + "INNER JOIN kabupaten kb ON kb.kd_kab=p.kd_kab LEFT JOIN booking_registrasi br ON br.no_rawat = rp.no_rawat LEFT JOIN eklaim_new_claim enc ON enc.no_rawat = rp.no_rawat WHERE "
                    + "rp.status_lanjut='Ralan' and pl.nm_poli like ? and d.nm_dokter like ? and rp.stts like ? and rp.tgl_registrasi between ? and ? and rp.no_reg like ? or "
                    + "rp.status_lanjut='Ralan' and pl.nm_poli like ? and d.nm_dokter like ? and rp.stts like ? and rp.tgl_registrasi between ? and ? and rp.no_rawat like ? or "
                    + "rp.status_lanjut='Ralan' and pl.nm_poli like ? and d.nm_dokter like ? and rp.stts like ? and rp.tgl_registrasi between ? and ? and rp.tgl_registrasi like ? or "
                    + "rp.status_lanjut='Ralan' and pl.nm_poli like ? and d.nm_dokter like ? and rp.stts like ? and rp.tgl_registrasi between ? and ? and rp.kd_dokter like ? or "
                    + "rp.status_lanjut='Ralan' and pl.nm_poli like ? and d.nm_dokter like ? and rp.stts like ? and rp.tgl_registrasi between ? and ? and d.nm_dokter like ? or "
                    + "rp.status_lanjut='Ralan' and pl.nm_poli like ? and d.nm_dokter like ? and rp.stts like ? and rp.tgl_registrasi between ? and ? and rp.no_rkm_medis like ? or "
                    + "rp.status_lanjut='Ralan' and pl.nm_poli like ? and d.nm_dokter like ? and rp.stts like ? and rp.tgl_registrasi between ? and ? and p.nm_pasien like ? or "
                    + "rp.status_lanjut='Ralan' and pl.nm_poli like ? and d.nm_dokter like ? and rp.stts like ? and rp.tgl_registrasi between ? and ? and pl.nm_poli like ? or "                    
                    + "rp.status_lanjut='Ralan' and pl.nm_poli like ? and d.nm_dokter like ? and rp.stts like ? and rp.tgl_registrasi between ? and ? and pj.png_jawab like ? or "
                    + "rp.status_lanjut='Ralan' and pl.nm_poli like ? and d.nm_dokter like ? and rp.stts like ? and rp.tgl_registrasi between ? and ? and CONCAT(p.alamat,', ',kl.nm_kel,', ',kc.nm_kec,', ',kb.nm_kab) like ? or "
                    + "rp.status_lanjut='Ralan' and pl.nm_poli like ? and d.nm_dokter like ? and rp.stts like ? and rp.tgl_registrasi between ? and ? and IF(br.no_rawat=rp.no_rawat,'Online','-') like ? or "
                    + "rp.status_lanjut='Ralan' and pl.nm_poli like ? and d.nm_dokter like ? and rp.stts like ? and rp.tgl_registrasi between ? and ? and IFNULL(enc.klaim_final,'-') like ? "
                    + "order by rp.tgl_registrasi desc, rp.jam_reg desc");
            try {
                pskasir.setString(1, "%" + CrPoli.getText() + "%");
                pskasir.setString(2, "%" + CrPtg.getText() + "%");
                pskasir.setString(3, "%" + cmbStatus.getSelectedItem().toString().replaceAll("Semua", "") + "%");
                pskasir.setString(4, Valid.SetTgl(DTPCari1.getSelectedItem() + ""));
                pskasir.setString(5, Valid.SetTgl(DTPCari2.getSelectedItem() + ""));
                pskasir.setString(6, "%" + TCari.getText().trim() + "%");
                pskasir.setString(7, "%" + CrPoli.getText() + "%");
                pskasir.setString(8, "%" + CrPtg.getText() + "%");
                pskasir.setString(9, "%" + cmbStatus.getSelectedItem().toString().replaceAll("Semua", "") + "%");
                pskasir.setString(10, Valid.SetTgl(DTPCari1.getSelectedItem() + ""));
                pskasir.setString(11, Valid.SetTgl(DTPCari2.getSelectedItem() + ""));
                pskasir.setString(12, "%" + TCari.getText().trim() + "%");
                pskasir.setString(13, "%" + CrPoli.getText() + "%");
                pskasir.setString(14, "%" + CrPtg.getText() + "%");
                pskasir.setString(15, "%" + cmbStatus.getSelectedItem().toString().replaceAll("Semua", "") + "%");
                pskasir.setString(16, Valid.SetTgl(DTPCari1.getSelectedItem() + ""));
                pskasir.setString(17, Valid.SetTgl(DTPCari2.getSelectedItem() + ""));
                pskasir.setString(18, "%" + TCari.getText().trim() + "%");
                pskasir.setString(19, "%" + CrPoli.getText() + "%");
                pskasir.setString(20, "%" + CrPtg.getText() + "%");
                pskasir.setString(21, "%" + cmbStatus.getSelectedItem().toString().replaceAll("Semua", "") + "%");
                pskasir.setString(22, Valid.SetTgl(DTPCari1.getSelectedItem() + ""));
                pskasir.setString(23, Valid.SetTgl(DTPCari2.getSelectedItem() + ""));
                pskasir.setString(24, "%" + TCari.getText().trim() + "%");
                pskasir.setString(25, "%" + CrPoli.getText() + "%");
                pskasir.setString(26, "%" + CrPtg.getText() + "%");
                pskasir.setString(27, "%" + cmbStatus.getSelectedItem().toString().replaceAll("Semua", "") + "%");
                pskasir.setString(28, Valid.SetTgl(DTPCari1.getSelectedItem() + ""));
                pskasir.setString(29, Valid.SetTgl(DTPCari2.getSelectedItem() + ""));
                pskasir.setString(30, "%" + TCari.getText().trim() + "%");
                pskasir.setString(31, "%" + CrPoli.getText() + "%");
                pskasir.setString(32, "%" + CrPtg.getText() + "%");
                pskasir.setString(33, "%" + cmbStatus.getSelectedItem().toString().replaceAll("Semua", "") + "%");
                pskasir.setString(34, Valid.SetTgl(DTPCari1.getSelectedItem() + ""));
                pskasir.setString(35, Valid.SetTgl(DTPCari2.getSelectedItem() + ""));
                pskasir.setString(36, "%" + TCari.getText().trim() + "%");
                pskasir.setString(37, "%" + CrPoli.getText() + "%");
                pskasir.setString(38, "%" + CrPtg.getText() + "%");
                pskasir.setString(39, "%" + cmbStatus.getSelectedItem().toString().replaceAll("Semua", "") + "%");
                pskasir.setString(40, Valid.SetTgl(DTPCari1.getSelectedItem() + ""));
                pskasir.setString(41, Valid.SetTgl(DTPCari2.getSelectedItem() + ""));
                pskasir.setString(42, "%" + TCari.getText().trim() + "%");
                pskasir.setString(43, "%" + CrPoli.getText() + "%");
                pskasir.setString(44, "%" + CrPtg.getText() + "%");
                pskasir.setString(45, "%" + cmbStatus.getSelectedItem().toString().replaceAll("Semua", "") + "%");
                pskasir.setString(46, Valid.SetTgl(DTPCari1.getSelectedItem() + ""));
                pskasir.setString(47, Valid.SetTgl(DTPCari2.getSelectedItem() + ""));
                pskasir.setString(48, "%" + TCari.getText().trim() + "%");
                pskasir.setString(49, "%" + CrPoli.getText() + "%");
                pskasir.setString(50, "%" + CrPtg.getText() + "%");
                pskasir.setString(51, "%" + cmbStatus.getSelectedItem().toString().replaceAll("Semua", "") + "%");
                pskasir.setString(52, Valid.SetTgl(DTPCari1.getSelectedItem() + ""));
                pskasir.setString(53, Valid.SetTgl(DTPCari2.getSelectedItem() + ""));
                pskasir.setString(54, "%" + TCari.getText().trim() + "%");
                pskasir.setString(55, "%" + CrPoli.getText() + "%");
                pskasir.setString(56, "%" + CrPtg.getText() + "%");
                pskasir.setString(57, "%" + cmbStatus.getSelectedItem().toString().replaceAll("Semua", "") + "%");
                pskasir.setString(58, Valid.SetTgl(DTPCari1.getSelectedItem() + ""));
                pskasir.setString(59, Valid.SetTgl(DTPCari2.getSelectedItem() + ""));
                pskasir.setString(60, "%" + TCari.getText().trim() + "%");
                pskasir.setString(61, "%" + CrPoli.getText() + "%");
                pskasir.setString(62, "%" + CrPtg.getText() + "%");
                pskasir.setString(63, "%" + cmbStatus.getSelectedItem().toString().replaceAll("Semua", "") + "%");
                pskasir.setString(64, Valid.SetTgl(DTPCari1.getSelectedItem() + ""));
                pskasir.setString(65, Valid.SetTgl(DTPCari2.getSelectedItem() + ""));
                pskasir.setString(66, "%" + TCari.getText().trim() + "%");
                pskasir.setString(67, "%" + CrPoli.getText() + "%");
                pskasir.setString(68, "%" + CrPtg.getText() + "%");
                pskasir.setString(69, "%" + cmbStatus.getSelectedItem().toString().replaceAll("Semua", "") + "%");
                pskasir.setString(70, Valid.SetTgl(DTPCari1.getSelectedItem() + ""));
                pskasir.setString(71, Valid.SetTgl(DTPCari2.getSelectedItem() + ""));
                pskasir.setString(72, "%" + TCari.getText().trim() + "%");
                rskasir = pskasir.executeQuery();
                while (rskasir.next()) {
                    tabModekasir.addRow(new String[]{
                        rskasir.getString("no_rawat"),
                        rskasir.getString("kd_dokter"),
                        rskasir.getString("nm_dokter"),
                        rskasir.getString("no_rkm_medis"),
                        rskasir.getString("nm_pasien"),
                        rskasir.getString("stts"),
                        rskasir.getString("nm_poli"),
                        rskasir.getString("png_jawab"),
                        rskasir.getString("stts_daftar"),
                        rskasir.getString("reg_onlen"),
                        rskasir.getString("tgl_registrasi"),
                        rskasir.getString("jam_reg"),
                        rskasir.getString("no_reg"),
                        rskasir.getString("stts_klaim"),
                        rskasir.getString("no_tlp"),
                        rskasir.getString("almt_pasien")
                    });
                }
            } catch (Exception e) {
                System.out.println("Notifikasi : " + e);
            } finally {
                if (rskasir != null) {
                    rskasir.close();
                }
                if (pskasir != null) {
                    pskasir.close();
                }
            }
        } catch (Exception e) {
            System.out.println("Notifikasi : " + e);
        }
        LCount.setText("" + tabModekasir.getRowCount());
    }

    private void getDatakasir() {
        nik = "";
        if (tbKasirRalan.getSelectedRow() != -1) {
            TNoRw.setText(tbKasirRalan.getValueAt(tbKasirRalan.getSelectedRow(), 0).toString());
            kdpoli.setText(Sequel.cariIsi("select kd_poli from reg_periksa where no_rawat='" + TNoRw.getText() + "'"));
            Tanggal.setText(tbKasirRalan.getValueAt(tbKasirRalan.getSelectedRow(), 10).toString());
            Jam.setText(tbKasirRalan.getValueAt(tbKasirRalan.getSelectedRow(), 11).toString());
            NoRM.setText(tbKasirRalan.getValueAt(tbKasirRalan.getSelectedRow(), 3).toString());
            nmPasien.setText(tbKasirRalan.getValueAt(tbKasirRalan.getSelectedRow(), 4).toString());
            kddokter.setText(tbKasirRalan.getValueAt(tbKasirRalan.getSelectedRow(), 1).toString());
            TDokter.setText(tbKasirRalan.getValueAt(tbKasirRalan.getSelectedRow(), 2).toString());
            nik = Sequel.cariIsi("select no_ktp from pasien where no_rkm_medis='" + NoRM.getText() + "'");

            Sequel.cariIsi("select kd_booking from booking_registrasi where "
                    + "tanggal_periksa='" + Valid.SetTgl(tglPeriksa.getSelectedItem() + "") + "' and "
                    + "no_rkm_medis=?", kdboking, NoRM.getText());

            sepJkd.setText(Sequel.cariIsi("SELECT bridging_jamkesda.no_sep FROM reg_periksa "
                    + "INNER JOIN bridging_jamkesda ON reg_periksa.no_rawat = bridging_jamkesda.no_rawat "
                    + "INNER JOIN penjab ON reg_periksa.kd_pj = penjab.kd_pj "
                    + "WHERE bridging_jamkesda.no_rawat='" + TNoRw.getText() + "' AND bridging_jamkesda.jns_rawat='Jalan' AND penjab.png_jawab like '%jamkesda%'"));
            sepJkdigd.setText(Sequel.cariIsi("SELECT bridging_jamkesda.no_sep FROM reg_periksa "
                    + "INNER JOIN bridging_jamkesda ON reg_periksa.no_rawat = bridging_jamkesda.no_rawat "
                    + "INNER JOIN penjab ON reg_periksa.kd_pj = penjab.kd_pj "
                    + "WHERE bridging_jamkesda.no_rawat='" + TNoRw.getText() + "' AND bridging_jamkesda.jns_rawat='Jalan IGD' AND penjab.png_jawab like '%jamkesda%'"));
        }
    }

    public JTextField getTextField() {
        return TNoRw;
    }

    public JButton getButton() {
        return BtnKeluar;
    }

    public void isCek() {
        MnKamarInap.setEnabled(var.getkamar_inap());
        MnPeriksaLab.setEnabled(var.getperiksa_lab());
        MnResepDOkter.setEnabled(var.getresep_dokter());
        MnPeriksaRadiologi.setEnabled(var.getperiksa_radiologi());
        MnOperasi.setEnabled(var.getoperasi());
        MnNoResep.setEnabled(var.getresep_obat());
        MnReturJual.setEnabled(var.getresep_obat());
        //MnBilling.setEnabled(var.getbilling_ralan());
        MnSudah.setEnabled(var.getkasir_ralan());
        MnBelum.setEnabled(var.getkasir_ralan());
        MnDataRalan.setEnabled(var.gettindakan_ralan());
        MnDataPemberianObat.setEnabled(var.getberi_obat());
        MnDokter.setEnabled(var.getkasir_ralan());
        MnPenjab.setEnabled(var.getkasir_ralan());
        MnRekapHarianDokter.setEnabled(var.getharian_dokter());
        MnRekapHarianParamedis.setEnabled(var.getharian_paramedis());
        MnRekapBulananDokter.setEnabled(var.getbulanan_dokter());
        MnRekapBulananParamedis.setEnabled(var.getbulanan_paramedis());
        MnRekapHarianPoli.setEnabled(var.getharian_tindakan_poli());
        MnRekapHarianObat.setEnabled(var.getobat_per_poli());
        MnDiagnosa.setEnabled(var.getdiagnosa_pasien());
        ppRiwayat.setEnabled(var.getresume_pasien());
        MnRujuk.setEnabled(var.getrujukan_keluar());
        MnPoliInternal.setEnabled(var.getrujukan_poli_internal());
        MnDiet.setEnabled(var.getdiet_pasien());
        BtnPxBooking.setEnabled(var.getreg_boking_kasir());
        MnDataHAIs.setEnabled(var.getdata_HAIs());
        MnPeniliaianAwalKeperawatanRalan.setEnabled(var.getpenilaian_awal_keperawatan_ralan());
        MnPeniliaianAwalMedisKebidananRalan.setEnabled(var.getpenilaian_awal_medis_ralan_kebidanan());
        MnStatusPasienAllKunjungan.setEnabled(var.getpenilaian_awal_keperawatan_ralan());
        MnSuratTindakanDokter.setEnabled(var.getdiagnosa_pasien());
        MnDataTriaseIGD.setEnabled(var.getdata_triase_igd());
        MnPermintaanLab.setEnabled(var.getpermintaan_lab());
//        MnCariPermintaanLab.setEnabled(var.getperiksa_lab());
        MnPermintaanRadiologi.setEnabled(var.getpermintaan_radiologi());
//        MnCariPermintaanRad.setEnabled(var.getperiksa_radiologi());
        ppPasienCorona.setEnabled(var.getpasien_corona());
        ppPerawatanCorona.setEnabled(var.getpasien_corona());
        ppSuratKontrol.setEnabled(var.getRencanaKontrolJKN()); 
        MnEklaimINACBG.setEnabled(var.getinacbg_klaim_raza()); 
        MnSEPBPJS.setEnabled(var.getinacbg_klaim_raza());
        ChkAutoRefres.setEnabled(var.getrujukan_poli_internal());
        ChkAutoRefres.setSelected(false);
        MnTeridentifikasiTB.setEnabled(var.getkemenkes_sitt());
        MnRencanaKontrolManual.setEnabled(var.getRencanaKontrolJKN());
        
        if (var.getbpjs_sep() == true || var.getberi_obat() == true || var.getkode().equals("Admin Utama")) {
            ppProgramPRB.setEnabled(true);
        } else {
            ppProgramPRB.setEnabled(false);
        }

        if (var.getkode().equals("Admin Utama")) {
            MnHapusData.setEnabled(true);
            MnStatus.setEnabled(true);
        } else {
            MnHapusData.setEnabled(false);
            MnStatus.setEnabled(false);
        }

        if (!namadokter.equals("")) {
            if (var.getkode().equals("Admin Utama")) {
                CrPtg.setText("");
                BtnSeek3.setEnabled(true);
                CrPtg.setEditable(true);
            } else {
                CrPtg.setText(namadokter);
                BtnSeek3.setEnabled(false);
                CrPtg.setEditable(false);
            }
        } else {
            BtnSeek3.setEnabled(true);
            CrPtg.setEditable(true);
        }

        if (!namapoli.equals("")) {
            if (var.getkode().equals("Admin Utama")) {
                CrPoli.setText("");
                BtnSeek4.setEnabled(true);
                CrPoli.setEditable(true);
            } else {
                CrPoli.setText(namapoli);
                BtnSeek4.setEnabled(false);
                CrPoli.setEditable(false);
            }
        } else {
            BtnSeek4.setEnabled(true);
            CrPoli.setEditable(true);
        }
    }

    private void otomatisRalan() {
        if (Sequel.cariRegistrasi(TNoRw.getText()) == 0) {
            try {
                psotomatis = koneksi.prepareStatement(sqlpsotomatis);
                try {
                    psotomatis.setString(1, tbKasirRalan.getValueAt(tbKasirRalan.getSelectedRow(), 1).toString());
                    psotomatis.setString(2, Sequel.cariIsi("select kd_pj from reg_periksa where no_rawat=?", TNoRw.getText()));
                    rskasir = psotomatis.executeQuery();
                    while (rskasir.next()) {
                        if (Sequel.cariIsiAngka("select count(no_rawat) from rawat_jl_dr where "
                                + "no_rawat='" + TNoRw.getText() + "' and kd_jenis_prw='" + rskasir.getString(1) + "' "
                                + "and kd_dokter='" + tbKasirRalan.getValueAt(tbKasirRalan.getSelectedRow(), 1).toString() + "'") == 0) {
                            psotomatis2 = koneksi.prepareStatement(sqlpsotomatis2);
                            try {
                                psotomatis2.setString(1, TNoRw.getText());
                                psotomatis2.setString(2, rskasir.getString(1));
                                psotomatis2.setString(3, tbKasirRalan.getValueAt(tbKasirRalan.getSelectedRow(), 1).toString());
                                psotomatis2.setString(4, Sequel.cariIsi("select current_date()"));
                                psotomatis2.setString(5, Sequel.cariIsi("select current_time()"));
                                psotomatis2.setDouble(6, rskasir.getDouble("material"));
                                psotomatis2.setDouble(7, rskasir.getDouble("bhp"));
                                psotomatis2.setDouble(8, rskasir.getDouble("tarif_tindakandr"));
                                psotomatis2.setDouble(9, rskasir.getDouble("kso"));
                                psotomatis2.setDouble(10, rskasir.getDouble("menejemen"));
                                psotomatis2.setDouble(11, rskasir.getDouble("total_byrdr"));
                                psotomatis2.executeUpdate();
                            } catch (Exception e) {
                                System.out.println("proses input data " + e);
                            } finally {
                                if (psotomatis2 != null) {
                                    psotomatis2.close();
                                }
                            }
                        }
                    }
                } catch (Exception e) {
                    System.out.println("Notifikasi : " + e);
                } finally {
                    if (rskasir != null) {
                        rskasir.close();
                    }
                    if (psotomatis != null) {
                        psotomatis.close();
                    }
                }

                if (!var.getkode().equals("Admin Utama")) {
                    psotomatis = koneksi.prepareStatement(sqlpsotomatispetugas);
                    try {
                        psotomatis.setString(1, Sequel.cariIsi("select kd_pj from reg_periksa where no_rawat=?", TNoRw.getText()));
                        rskasir = psotomatis.executeQuery();
                        while (rskasir.next()) {
                            if (Sequel.cariIsiAngka("select count(no_rawat) from rawat_jl_pr where "
                                    + "no_rawat='" + TNoRw.getText() + "' and kd_jenis_prw='" + rskasir.getString(1) + "' "
                                    + "and nip='" + var.getkode() + "'") == 0) {
                                psotomatis2 = koneksi.prepareStatement(sqlpsotomatis2petugas);
                                try {
                                    psotomatis2.setString(1, TNoRw.getText());
                                    psotomatis2.setString(2, rskasir.getString(1));
                                    psotomatis2.setString(3, var.getkode());
                                    psotomatis2.setString(4, Sequel.cariIsi("select current_date()"));
                                    psotomatis2.setString(5, Sequel.cariIsi("select current_time()"));
                                    psotomatis2.setDouble(6, rskasir.getDouble("material"));
                                    psotomatis2.setDouble(7, rskasir.getDouble("bhp"));
                                    psotomatis2.setDouble(8, rskasir.getDouble("tarif_tindakanpr"));
                                    psotomatis2.setDouble(9, rskasir.getDouble("kso"));
                                    psotomatis2.setDouble(10, rskasir.getDouble("menejemen"));
                                    psotomatis2.setDouble(11, rskasir.getDouble("total_byrpr"));
                                    psotomatis2.executeUpdate();
                                } catch (Exception e) {
                                    System.out.println("proses input data " + e);
                                } finally {
                                    if (psotomatis2 != null) {
                                        psotomatis2.close();
                                    }
                                }
                            }
                        }
                    } catch (Exception e) {
                        System.out.println("Notifikasi : " + e);
                    } finally {
                        if (rskasir != null) {
                            rskasir.close();
                        }
                        if (psotomatis != null) {
                            psotomatis.close();
                        }
                    }
                }

                if (!var.getkode().equals("Admin Utama")) {
                    psotomatis = koneksi.prepareStatement(sqlpsotomatisdokterpetugas);
                    try {
                        psotomatis.setString(1, tbKasirRalan.getValueAt(tbKasirRalan.getSelectedRow(), 1).toString());
                        psotomatis.setString(2, Sequel.cariIsi("select kd_pj from reg_periksa where no_rawat=?", TNoRw.getText()));
                        rskasir = psotomatis.executeQuery();
                        while (rskasir.next()) {
                            if (Sequel.cariIsiAngka("select count(no_rawat) from rawat_jl_drpr where "
                                    + "no_rawat='" + TNoRw.getText() + "' and kd_jenis_prw='" + rskasir.getString(1) + "' "
                                    + "and kd_dokter='" + tbKasirRalan.getValueAt(tbKasirRalan.getSelectedRow(), 1).toString() + "'") == 0) {
                                psotomatis2 = koneksi.prepareStatement(sqlpsotomatis2dokterpetugas);
                                try {
                                    psotomatis2.setString(1, TNoRw.getText());
                                    psotomatis2.setString(2, rskasir.getString(1));
                                    psotomatis2.setString(3, tbKasirRalan.getValueAt(tbKasirRalan.getSelectedRow(), 1).toString());
                                    psotomatis2.setString(4, var.getkode());
                                    psotomatis2.setString(5, Sequel.cariIsi("select current_date()"));
                                    psotomatis2.setString(6, Sequel.cariIsi("select current_time()"));
                                    psotomatis2.setDouble(7, rskasir.getDouble("material"));
                                    psotomatis2.setDouble(8, rskasir.getDouble("bhp"));
                                    psotomatis2.setDouble(9, rskasir.getDouble("tarif_tindakandr"));
                                    psotomatis2.setDouble(10, rskasir.getDouble("tarif_tindakanpr"));
                                    psotomatis2.setDouble(11, rskasir.getDouble("kso"));
                                    psotomatis2.setDouble(12, rskasir.getDouble("menejemen"));
                                    psotomatis2.setDouble(13, rskasir.getDouble("total_byrdrpr"));
                                    psotomatis2.executeUpdate();
                                } catch (Exception e) {
                                    System.out.println("proses input data " + e);
                                } finally {
                                    if (psotomatis2 != null) {
                                        psotomatis2.close();
                                    }
                                }
                            }
                        }
                    } catch (Exception e) {
                        System.out.println("Notifikasi : " + e);
                    } finally {
                        if (rskasir != null) {
                            rskasir.close();
                        }
                        if (psotomatis != null) {
                            psotomatis.close();
                        }
                    }
                }
            } catch (Exception e) {
                System.out.println("Notifikasi : " + e);
            }
        }
    }

    private void emptBooking() {
        kdboking.setText("");
        kdboking.requestFocus();
        norm.setText("");
        nmpasien.setText("");
        kdpoli1.setText("");
        nmpoli1.setText("");
        tglPeriksa.setDate(new Date());
        tglPeriksa.setEnabled(false);
        kdpnj.setText("");
        nmpnj.setText("");
        KdDokter.setText("");
        NmDokter.setText("");
        norwBoking.setText("");
        TNoReg.setText("");
        cekKodeBoking.setText("");
        cekTerdaftar.setText("");
        cekPasien.setText("");
    }

    private void nomorAuto() {
        switch (URUTNOREG) {
            case "poli":
                Valid.autoNomer3("select ifnull(MAX(CONVERT(no_reg,signed)),0) from reg_periksa where kd_poli='" + kdpoli1.getText() + "' and tgl_registrasi='" + Valid.SetTgl(tglPeriksa.getSelectedItem() + "") + "'", "", 3, TNoReg);
                break;
            case "dokter":
                Valid.autoNomer3("select ifnull(MAX(CONVERT(no_reg,signed)),0) from reg_periksa where kd_dokter='" + KdDokter.getText() + "' and tgl_registrasi='" + Valid.SetTgl(tglPeriksa.getSelectedItem() + "") + "'", "", 3, TNoReg);
                break;
            case "dokter & poli":
                Valid.autoNomer3("select ifnull(MAX(CONVERT(no_reg,signed)),0) from reg_periksa where kd_dokter='" + KdDokter.getText() + "' and kd_poli='" + kdpoli1.getText() + "' and tgl_registrasi='" + Valid.SetTgl(tglPeriksa.getSelectedItem() + "") + "'", "", 3, TNoReg);
                break;
            default:
                Valid.autoNomer3("select ifnull(MAX(CONVERT(no_reg,signed)),0) from reg_periksa where kd_dokter='" + KdDokter.getText() + "' and tgl_registrasi='" + Valid.SetTgl(tglPeriksa.getSelectedItem() + "") + "'", "", 3, TNoReg);
                break;
        }

        Valid.autoNomer3("select ifnull(MAX(CONVERT(RIGHT(no_rawat,6),signed)),0) from reg_periksa where tgl_registrasi='" + Valid.SetTgl(tglPeriksa.getSelectedItem() + "") + "' ", dateformat.format(tglPeriksa.getDate()) + "/", 6, norwBoking);
    }

    private void cekPasienBoking() {
        try {
            pspasienboking = koneksi.prepareStatement("select br.no_rkm_medis, p.nm_pasien, br.kd_poli, pl.nm_poli, br.tanggal_periksa, "
                    + "br.kd_pj, pj.png_jawab from booking_registrasi br inner join pasien p on p.no_rkm_medis=br.no_rkm_medis "
                    + "inner join poliklinik pl on pl.kd_poli=br.kd_poli inner join dokter d on d.kd_dokter=br.kd_dokter "
                    + "inner join penjab pj on pj.kd_pj=br.kd_pj where br.kd_booking=?");
            try {
                pspasienboking.setString(1, kdboking.getText());
                rspasienboking = pspasienboking.executeQuery();
                while (rspasienboking.next()) {
                    norm.setText(rspasienboking.getString("no_rkm_medis"));
                    nmpasien.setText(rspasienboking.getString("nm_pasien"));
                    kdpoli1.setText(rspasienboking.getString("kd_poli"));
                    nmpoli1.setText(rspasienboking.getString("nm_poli"));
                    Valid.SetTgl(tglPeriksa, rspasienboking.getString("tanggal_periksa"));
                    kdpnj.setText(rspasienboking.getString("kd_pj"));
                    nmpnj.setText(rspasienboking.getString("png_jawab"));
                }
            } catch (Exception ex) {
                System.out.println(ex);
            } finally {
                if (rspasienboking != null) {
                    rspasienboking.close();
                }

                if (pspasienboking != null) {
                    pspasienboking.close();
                }
            }
        } catch (Exception e) {
            System.out.println(e);
        }
    }

    private void jam() {
        ActionListener taskPerformer = new ActionListener() {
            private int nilai_jam;
            private int nilai_menit;
            private int nilai_detik;

            public void actionPerformed(ActionEvent e) {
                String nol_jam = "";
                String nol_menit = "";
                String nol_detik = "";

                Date now = Calendar.getInstance().getTime();

                // Mengambil nilaj JAM, MENIT, dan DETIK Sekarang
                if (ChkWaktu.isSelected() == true) {
                    nilai_jam = now.getHours();
                    nilai_menit = now.getMinutes();
                    nilai_detik = now.getSeconds();
                } else if (ChkWaktu.isSelected() == false) {
                    nilai_jam = CmbJam.getSelectedIndex();
                    nilai_menit = CmbMenit.getSelectedIndex();
                    nilai_detik = CmbDetik.getSelectedIndex();
                }

                // Jika nilai JAM lebih kecil dari 10 (hanya 1 digit)
                if (nilai_jam <= 9) {
                    // Tambahkan "0" didepannya
                    nol_jam = "0";
                }
                // Jika nilai MENIT lebih kecil dari 10 (hanya 1 digit)
                if (nilai_menit <= 9) {
                    // Tambahkan "0" didepannya
                    nol_menit = "0";
                }
                // Jika nilai DETIK lebih kecil dari 10 (hanya 1 digit)
                if (nilai_detik <= 9) {
                    // Tambahkan "0" didepannya
                    nol_detik = "0";
                }
                // Membuat String JAM, MENIT, DETIK
                String jam = nol_jam + Integer.toString(nilai_jam);
                String menit = nol_menit + Integer.toString(nilai_menit);
                String detik = nol_detik + Integer.toString(nilai_detik);
                // Menampilkan pada Layar
                //tampil_jam.setText("  " + jam + " : " + menit + " : " + detik + "  ");
                CmbJam.setSelectedItem(jam);
                CmbMenit.setSelectedItem(menit);
                CmbDetik.setSelectedItem(detik);
            }
        };
        // Timer
        new Timer(1000, taskPerformer).start();
    }

    private void umurPasien() {
        try {
            psumurpasien = koneksi.prepareStatement("select TIMESTAMPDIFF(YEAR, tgl_lahir, CURDATE()) as tahun, "
                    + "(TIMESTAMPDIFF(MONTH, tgl_lahir, CURDATE()) - ((TIMESTAMPDIFF(MONTH, tgl_lahir, CURDATE()) div 12) * 12)) as bulan, "
                    + "TIMESTAMPDIFF(DAY, DATE_ADD(DATE_ADD(tgl_lahir,INTERVAL TIMESTAMPDIFF(YEAR, tgl_lahir, CURDATE()) YEAR), INTERVAL TIMESTAMPDIFF(MONTH, tgl_lahir, CURDATE()) - ((TIMESTAMPDIFF(MONTH, tgl_lahir, CURDATE()) div 12) * 12) MONTH), CURDATE()) as hari from pasien "
                    + "where pasien.no_rkm_medis=?");
            try {
                psumurpasien.setString(1, norm.getText());
                rsumurpasien = psumurpasien.executeQuery();
                while (rsumurpasien.next()) {
                    umur = "0";
                    sttsumur = "Th";
                    if (rsumurpasien.getInt("tahun") > 0) {
                        umur = rsumurpasien.getString("tahun");
                        sttsumur = "Th";
                    } else if (rsumurpasien.getInt("tahun") == 0) {
                        if (rsumurpasien.getInt("bulan") > 0) {
                            umur = rsumurpasien.getString("bulan");
                            sttsumur = "Bl";
                        } else if (rsumurpasien.getInt("bulan") == 0) {
                            umur = rsumurpasien.getString("hari");
                            sttsumur = "Hr";
                        }
                    }
                }
            } catch (Exception ex) {
                System.out.println(ex);
            } finally {
                if (rsumurpasien != null) {
                    rsumurpasien.close();
                }

                if (psumurpasien != null) {
                    psumurpasien.close();
                }
            }
        } catch (Exception e) {
            System.out.println(e);
        }
    }

    public void empttext() {
        TNoRw.setText("");
        NoRM.setText("");
        Kd2.setText("");
        TKdPny.setText("");
        Tanggal.setText("");
        Jam.setText("");
        sepJkd.setText("");
        sepJkdigd.setText("");
        nmPasien.setText("");
        dataGZ.setText("");
        TNoReg.setText("");
        kdpenjab.setText("");
        nmpenjab.setText("");
        kdpoli.setText("");
        nmpoli.setText("");
        kddokter.setText("");
        TDokter.setText("");
        pasiendipilih.setText("");
        norw_dipilih = "";
        kddokter_dipilih = "";        
    }

    private void formulirKlaim() {
        tglklaim = "";
        drdpjp = "";
        poli = "";
        crBayar = "";

        try {
            Sequel.queryu("delete from temporary_formulir_klaim");
            pspasien = koneksi.prepareStatement("SELECT rp.no_rawat, rp.no_rkm_medis, p.nm_pasien, IF (p.jk = 'L','LAKI-LAKI','PEREMPUAN') jk, "
                    + "IF (rp.status_lanjut = 'Ralan','RAWAT JALAN','-') status_kunjungan, DATE_FORMAT(p.tgl_lahir,'%d') tgl_lhr, "
                    + "CONCAT(rp.umurdaftar,' ',rp.sttsumur,'.') umur, DATE_FORMAT(rp.tgl_registrasi,'%d') tgl_kunj, d.nm_dokter, pl.nm_poli, "
                    + "pj.png_jawab, IFNULL(pr.diagnosa,'-') diag_resum, IFNULL(pr.keluhan,'-') keluhan, IFNULL(pr.pemeriksaan,'-') pemeriksaan, "
                    + "IFNULL(pr.alergi,'-') alergi, IFNULL(pr.terapi,'-') terapi, IFNULL(pr.rincian_tindakan,'-') tindakan FROM reg_periksa rp INNER JOIN pasien p ON rp.no_rkm_medis = p.no_rkm_medis "
                    + "INNER JOIN dokter d ON rp.kd_dokter = d.kd_dokter INNER JOIN poliklinik pl ON rp.kd_poli = pl.kd_poli "
                    + "INNER JOIN penjab pj ON rp.kd_pj = pj.kd_pj LEFT JOIN pemeriksaan_ralan pr ON rp.no_rawat = pr.no_rawat "
                    + "WHERE rp.status_lanjut = 'Ralan' AND rp.no_rawat ='" + TNoRw.getText().trim() + "'");

            try {
                rspasien = pspasien.executeQuery();
                while (rspasien.next()) {
                    tglklaim = rspasien.getString("tgl_kunj") + " "+ Sequel.bulanINDONESIA("select month(tgl_registrasi) from reg_periksa where no_rawat='" + rspasien.getString("no_rawat") + "'") + " "
                            + Sequel.cariIsi("select year(tgl_registrasi) from reg_periksa where no_rawat='" + rspasien.getString("no_rawat") + "'");
                    drdpjp = rspasien.getString("nm_dokter");
                    poli = rspasien.getString("nm_poli");
                    crBayar = rspasien.getString("png_jawab");

                    Sequel.menyimpan("temporary_formulir_klaim", "'Kode RS',': 6303015','Nama RS',': RSUD Ratu Zalecha Martapura',"
                            + "'1. No. RM',': " + rspasien.getString("no_rkm_medis") + "',"
                            + "'2. Nama Pasien',': " + rspasien.getString("nm_pasien") + "',"
                            + "'3. Jenis Kelamin',': " + rspasien.getString("jk") + "',"
                            + "'4. Jenis Perawatan',': " + rspasien.getString("status_kunjungan") + "',"
                            + "'5. Tgl. Lahir',': " + rspasien.getString("tgl_lhr") + " " 
                            + Sequel.bulanINDONESIA("select month(tgl_lahir) from pasien where no_rkm_medis='" + rspasien.getString("no_rkm_medis") + "'") + " "
                            + Sequel.cariIsi("select year(tgl_lahir) from pasien where no_rkm_medis='" + rspasien.getString("no_rkm_medis") + "'") + "',"
                            + "'6. Umur',': " + rspasien.getString("umur") + "',"
                            + "'7. Tgl. Kunjungan',': " + rspasien.getString("tgl_kunj") + " "
                            + Sequel.bulanINDONESIA("select month(tgl_registrasi) from reg_periksa where no_rawat='" + rspasien.getString("no_rawat") + "'") + " "
                            + Sequel.cariIsi("select year(tgl_registrasi) from reg_periksa where no_rawat='" + rspasien.getString("no_rawat") + "'") + "',"
                            + "'8. Resume Medis',':','','','','','','','','','','','','','','','','',''", "Formulir Klaim Rawat Jalan");

                    Sequel.menyimpan("temporary_formulir_klaim", "'','','','','','','','','','','','','','','','','','',"
                            + "'      Keluhan',':','" + rspasien.getString("keluhan") + "',"
                            + "'','','','','','','','','','','','','','','',''", "Formulir Klaim Rawat Jalan");
                    
                    Sequel.menyimpan("temporary_formulir_klaim", "'','','','','','','','','','','','','','','','','','',"
                            + "'      Pemeriksaan',':','" + rspasien.getString("pemeriksaan") + "',"
                            + "'','','','','','','','','','','','','','','',''", "Formulir Klaim Rawat Jalan");

                    Sequel.menyimpan("temporary_formulir_klaim", "'','','','','','','','','','','','','','','','','','',"
                            + "'      Alergi',':','" + rspasien.getString("alergi") + "',"
                            + "'','','','','','','','','','','','','','','',''", "Formulir Klaim Rawat Jalan");
                    
                    Sequel.menyimpan("temporary_formulir_klaim", "'','','','','','','','','','','','','','','','','','',"
                            + "'      Terapi',':','" + rspasien.getString("terapi") + "',"
                            + "'','','','','','','','','','','','','','','',''", "Formulir Klaim Rawat Jalan");
                    
                    Sequel.menyimpan("temporary_formulir_klaim", "'','','','','','','','','','','','','','','','','','',"
                            + "'      Rincian Tindakan',':','" + rspasien.getString("tindakan") + "',"
                            + "'','','','','','','','','','','','','','','',''", "Formulir Klaim Rawat Jalan");
                    
                    Sequel.menyimpan("temporary_formulir_klaim", "'','','','','','','','','','','','','','','','','','',"
                            + "'      Diagnosa Resume',':','" + rspasien.getString("diag_resum") + "',"
                            + "'','','','','','','','','','','','','','','',''", "Formulir Klaim Rawat Jalan");
                    
                    //ngambil data diagnosa icd 10     
                    Sequel.menyimpan("temporary_formulir_klaim", "'','','','','','','','','','','','','','','','','','',"
                            + "'9. Diagnosa ICD-10',':','','','','','','','','','','','','','','','','',''", "Formulir Klaim Rawat Jalan");
                    psdiagnosa = koneksi.prepareStatement("SELECT IF (d.prioritas = '1','Primer','Sekunder') stts_diagnosa, IFNULL(d.kd_penyakit,'-') AS ICD_10, "
                            + "IFNULL(p.ciri_ciri, '-') deskripsi_diagnosa FROM diagnosa_pasien d "
                            + "INNER JOIN reg_periksa r ON r.no_rawat=d.no_rawat INNER JOIN penyakit p ON p.kd_penyakit=d.kd_penyakit "
                            + "WHERE d.status='ralan' and r.no_rawat='" + TNoRw.getText().trim() + "' order by d.prioritas");
                    try {
                        rsdiagnosa = psdiagnosa.executeQuery();
                        while (rsdiagnosa.next()) {
                            Sequel.menyimpan("temporary_formulir_klaim", "'','','','','','','','','','','','','','','','','','',"
                                    + "'      " + rsdiagnosa.getString("stts_diagnosa") + "',':','" + rsdiagnosa.getString("deskripsi_diagnosa") + "','Kode : " + rsdiagnosa.getString("ICD_10") + "',"
                                    + "'','','','','','','','','','','','','','',''", "Diagnosa Klaim Rawat Jalan");
                        }
                    } catch (Exception e) {
                        System.out.println("Notifikasi Formulir Klaim : " + e);
                    } finally {
                        if (rsdiagnosa != null) {
                            rsdiagnosa.close();
                        }
                        if (psdiagnosa != null) {
                            psdiagnosa.close();
                        }
                    }
                    
                    Sequel.menyimpan("temporary_formulir_klaim", "'','','','','','','','','','','','','','','','','','','','','','','','','','','','','','','','','','','','',''", "Formulir Klaim Rawat Jalan");
                    Sequel.menyimpan("temporary_formulir_klaim", "'','','','','','','','','','','','','','','','','','','','','','','','','','','','','','','','','','','','',''", "Formulir Klaim Rawat Jalan");
                    
                    //ngambil data tindakan icd 9
                    Sequel.menyimpan("temporary_formulir_klaim", "'','','','','','','','','','','','','','','','','','',"
                            + "'10. Tindakan ICD-9-CM',':','','','','','','','','','','','','','','','','',''", "Formulir Klaim Rawat Jalan");
                    psprosedur = koneksi.prepareStatement(
                            "SELECT IFNULL(pp.kode,'-') ICD_9, IFNULL(i.deskripsi_panjang,'-') des_prosedur "
                            + "FROM reg_periksa rp INNER JOIN prosedur_pasien pp on pp.no_rawat=rp.no_rawat INNER JOIN icd9 i on i.kode=pp.kode "
                            + "WHERE rp.status_lanjut = 'Ralan' AND rp.no_rawat ='" + TNoRw.getText().trim() + "'");
                    try {
                        rsprosedur = psprosedur.executeQuery();
                        while (rsprosedur.next()) {
                            Sequel.menyimpan("temporary_formulir_klaim", "'','','','','','','','','','','','','','','','','','',"
                                    + "'      Deskripsi',':','" + rsprosedur.getString("des_prosedur") + "','Kode : " + rsprosedur.getString("ICD_9") + "',"
                                    + "'','','','','','','','','','','','','','',''", "Tindakan Klaim Rawat Jalan");
                        }
                    } catch (Exception e) {
                        System.out.println("Notif Registrasi : " + e);
                    } finally {
                        if (rsprosedur != null) {
                            rsprosedur.close();
                        }
                        if (psprosedur != null) {
                            psprosedur.close();
                        }
                    }
                    
                    Sequel.menyimpan("temporary_formulir_klaim", "'','','','','','','','','','','','','','','','','','','','','','','','','','','','','','','','','','','','',''", "Formulir Klaim Rawat Jalan");
                    Sequel.menyimpan("temporary_formulir_klaim", "'','','','','','','','','','','','','','','','','','','','','','','','','','','','','','','','','','','','',''", "Formulir Klaim Rawat Jalan");
                }

            } catch (Exception e) {
                System.out.println("Notifikasi Cari Pasien : " + e);
            } finally {
                if (rspasien != null) {
                    rspasien.close();
                }
                if (pspasien != null) {
                    pspasien.close();
                }
            }
        } catch (Exception e) {
            System.out.println("Notifikasi : " + e);
        }

        this.setCursor(Cursor.getPredefinedCursor(Cursor.WAIT_CURSOR));
        Map<String, Object> param = new HashMap<>();
        param.put("namars", var.getnamars());
        param.put("alamatrs", var.getalamatrs());
        param.put("kotars", var.getkabupatenrs());
        param.put("propinsirs", var.getpropinsirs());
        param.put("kontakrs", var.getkontakrs());
        param.put("emailrs", var.getemailrs());
        param.put("logo", Sequel.cariGambar("select logo from setting"));
        param.put("tglKlaim", "Martapura, " + tglklaim);
        param.put("drDPJP", "( " + drdpjp + " )");
        param.put("poli", poli);
        param.put("caraBayar", crBayar);
        Valid.MyReport("rptFormulirKlaim.jrxml", "report", "::[ Lembar Formulir Klaim Pasien Rawat Jalan ]::",
                "select * from temporary_formulir_klaim", param);
        this.setCursor(Cursor.getDefaultCursor());
    }

    private void tampilMati() {
        Valid.tabelKosong(tabModeMati);
        try {
            psMati = koneksi.prepareStatement("select tanggal,jam,pasien_mati.no_rkm_medis,nm_pasien,jk,tmp_lahir,tgl_lahir,gol_darah,stts_nikah,"
                    + "agama,keterangan,temp_meninggal,icd5,unit_asal, date_format(tgl_lahir,'%d %M %Y') tgl_lhr, "
                    + "date_format(tanggal,'%d %M %Y') tgl_mati from pasien_mati,pasien where "
                    + sql + "and tanggal like '%" + TCari1.getText().trim() + "%' or "
                    + sql + "and pasien_mati.no_rkm_medis like '%" + TCari1.getText().trim() + "%' or "
                    + sql + "and nm_pasien like '%" + TCari1.getText().trim() + "%' or "
                    + sql + "and jk like '%" + TCari1.getText().trim() + "%' or "
                    + sql + "and tmp_lahir like '%" + TCari1.getText().trim() + "%' or "
                    + sql + "and gol_darah like '%" + TCari1.getText().trim() + "%' or "
                    + sql + "and stts_nikah like '%" + TCari1.getText().trim() + "%' or "
                    + sql + "and agama like '%" + TCari1.getText().trim() + "%' or "
                    + sql + "and unit_asal like '%" + TCari1.getText().trim() + "%' or "
                    + sql + "and keterangan like '%" + TCari1.getText().trim() + "%' "
                    + " order by tanggal desc limit 50");
            try {
                rsMati = psMati.executeQuery();
                while (rsMati.next()) {
                    tabModeMati.addRow(new Object[]{
                        rsMati.getString("tanggal"),
                        rsMati.getString("jam"),
                        rsMati.getString("no_rkm_medis"),
                        rsMati.getString("nm_pasien"),
                        rsMati.getString("jk"),
                        rsMati.getString("tmp_lahir"),
                        rsMati.getString("tgl_lahir"),
                        rsMati.getString("gol_darah"),
                        rsMati.getString("stts_nikah"),
                        rsMati.getString("agama"),
                        rsMati.getString("keterangan"),
                        rsMati.getString("temp_meninggal"),
                        rsMati.getString("icd5"),
                        rsMati.getString("unit_asal"),
                        rsMati.getString("tgl_lhr"),
                        rsMati.getString("tgl_mati")
                    });
                }
            } catch (Exception e) {
                System.out.println("simrskhanza.DlgKasirRalan.tampil() : " + e);
            } finally {
                if (rsMati != null) {
                    rsMati.close();
                }
                if (psMati != null) {
                    psMati.close();
                }
            }
        } catch (SQLException e) {
            System.out.println("Notifikasi : " + e);
        }
    }

    private void emptMati() {
        rmMati.setText("");
        nmpxMati.setText("");
        tglLahrMati.setText("");
        tglMati.setText("");
        jamMati.setText("");
        tmptMati.setText("");
        ketMati.setText("");
        icdMati.setText("");
        desMati.setText("");
        TCari1.setText("");
    }

    private void getDataMati() {
        if (tbPasienMati.getSelectedRow() != -1) {
            rmMati.setText(tbPasienMati.getValueAt(tbPasienMati.getSelectedRow(), 2).toString());
            nmpxMati.setText(tbPasienMati.getValueAt(tbPasienMati.getSelectedRow(), 3).toString());
            tglLahrMati.setText(tbPasienMati.getValueAt(tbPasienMati.getSelectedRow(), 14).toString());
            tglMati.setText(tbPasienMati.getValueAt(tbPasienMati.getSelectedRow(), 15).toString());
            jamMati.setText(tbPasienMati.getValueAt(tbPasienMati.getSelectedRow(), 1).toString());
            tmptMati.setText(tbPasienMati.getValueAt(tbPasienMati.getSelectedRow(), 11).toString());
            ketMati.setText(tbPasienMati.getValueAt(tbPasienMati.getSelectedRow(), 10).toString());
            icdMati.setText(tbPasienMati.getValueAt(tbPasienMati.getSelectedRow(), 12).toString());
            desMati.setText(Sequel.cariIsi("select ciri_ciri from penyakit where kd_penyakit='" + icdMati.getText() + "'"));
        }
    }

    public void tampilRiwayatKun(String code) {
        Valid.tabelKosong(tabModeKunjungan);
        try {
            psRiwKunj = koneksi.prepareStatement("SELECT rp.no_rawat, "
                    + "	p.no_rkm_medis, "
                    + "	date_format( rp.tgl_registrasi, '%d-%m-%Y' ) tgl_kunj, "
                    + "	p.nm_pasien, "
                    + "	d.nm_dokter, "
                    + "	pj.png_jawab, "
                    + "	rp.kd_dokter "
                    + "FROM reg_periksa rp "
                    + "	INNER JOIN dokter d ON d.kd_dokter = rp.kd_dokter "
                    + "	INNER JOIN pasien p ON p.no_rkm_medis = rp.no_rkm_medis "
                    + "	INNER JOIN poliklinik pl ON pl.kd_poli = rp.kd_poli "
                    + "	INNER JOIN penjab pj ON pj.kd_pj = rp.kd_pj "
                    + "left join pemeriksaan_ralan_petugas s on s.no_rawat = rp.no_rawat "
                    + "left join pemeriksaan_ralan n on n.no_rawat = rp.no_rawat "
                    + "left join diagnosa_pasien dp on dp.no_rawat = rp.no_rawat "
                    + "where (rp.tgl_registrasi BETWEEN (select DATE_sub(DATE_FORMAT(now(),'%Y-%m-%d'),INTERVAL 7 DAY)) and (select DATE_sub(DATE_FORMAT(now(),'%Y-%m-%d'),INTERVAL 1 DAY))) and rp.kd_poli ='" + code + "' "
                    + "and (IFNULL(s.no_rawat,'-') = '-' and IFNULL(n.no_rawat,'-') = '-' and IFNULL(dp.no_rawat,'-') = '-')");

            try {
                rsRiwKunj = psRiwKunj.executeQuery();
                z = 1;
                while (rsRiwKunj.next()) {
                    tabModeKunjungan.addRow(new Object[]{
                        z + ".",
                        rsRiwKunj.getString("no_rawat"),
                        rsRiwKunj.getString("no_rkm_medis"),
                        rsRiwKunj.getString("tgl_kunj"),
                        rsRiwKunj.getString("nm_pasien"),
                        rsRiwKunj.getString("nm_dokter"),
                        rsRiwKunj.getString("png_jawab"),
                        rsRiwKunj.getString("kd_dokter")
                    });
                    z++;
                }
                this.setCursor(Cursor.getDefaultCursor());
            } catch (Exception e) {
                System.out.println("simrskhanza.DlgKasirRalan.tampilRiwayatKun() : " + e);
            } finally {
                if (rsRiwKunj != null) {
                    rsRiwKunj.close();
                }
                if (psRiwKunj != null) {
                    psRiwKunj.close();
                }
            }
        } catch (SQLException e) {
            System.out.println("Notifikasi : " + e);
        }
    }

    private void cekReg() {
        if ((Sequel.cariInteger("select status_erm from poliklinik where kd_poli = '" + kdpoli.getText() + "'") == 1)) {
            cekTotKun = Sequel.cariInteger("select count(1) total from reg_periksa where "
                    + "(tgl_registrasi BETWEEN (select DATE_sub(DATE_FORMAT(now(),'%Y-%m-%d'),INTERVAL 7 DAY)) and (select DATE_sub(DATE_FORMAT(now(),'%Y-%m-%d'),INTERVAL 1 DAY))) "
                    + "and kd_poli ='" + kdpoli.getText() + "'");

            if (cekTotKun > 0) {
                cekDiag = Sequel.cariInteger("select count(1) total from reg_periksa r "
                        + "left join pemeriksaan_ralan_petugas d on d.no_rawat = r.no_rawat "
                        + "left join pemeriksaan_ralan p on p.no_rawat = r.no_rawat "
                        + "left join diagnosa_pasien s on s.no_rawat = r.no_rawat "
                        + "where (r.tgl_registrasi BETWEEN (select DATE_sub(DATE_FORMAT(now(),'%Y-%m-%d'),INTERVAL 7 DAY)) and (select DATE_sub(DATE_FORMAT(now(),'%Y-%m-%d'),INTERVAL 1 DAY))) and kd_poli ='" + kdpoli.getText() + "' "
                        + "and (IFNULL(d.no_rawat,'-') = '-' and IFNULL(s.no_rawat,'-') = '-' and IFNULL(p.no_rawat,'-') = '-')");

                if (cekDiag == 0) {
                    DlgRawatJalan dlgrwjl2 = new DlgRawatJalan(null, false);
                    dlgrwjl2.setSize(internalFrame1.getWidth() - 40, internalFrame1.getHeight() - 40);
                    dlgrwjl2.setLocationRelativeTo(internalFrame1);
                    dlgrwjl2.setNoRm(TNoRw.getText(), DTPCari1.getDate(), DTPCari2.getDate());
                    dlgrwjl2.tampilDrPr();
                    dlgrwjl2.TotalNominal();
                    dlgrwjl2.setVisible(true);
                    dlgrwjl2.fokus();
                    dlgrwjl2.petugas(kddokter.getText(), var.getkode());
                    dlgrwjl2.isCek();
                } else {
                    WindowRiwayatKunjungan.setSize(874, 361);
                    WindowRiwayatKunjungan.setLocationRelativeTo(internalFrame1);
                    pasiendipilih.setText("");
                    norw_dipilih = "";
                    kddokter_dipilih = "";
                    tampilRiwayatKun(kdpoli.getText());
                    WindowRiwayatKunjungan.setVisible(true);
                    tbRiwayatKunj.requestFocus();
                }
            } else {
                DlgRawatJalan dlgrwjl2 = new DlgRawatJalan(null, false);
                dlgrwjl2.setSize(internalFrame1.getWidth() - 40, internalFrame1.getHeight() - 40);
                dlgrwjl2.setLocationRelativeTo(internalFrame1);
                dlgrwjl2.setNoRm(TNoRw.getText(), DTPCari1.getDate(), DTPCari2.getDate());
                dlgrwjl2.tampilDrPr();
                dlgrwjl2.TotalNominal();
                dlgrwjl2.setVisible(true);
                dlgrwjl2.fokus();
                dlgrwjl2.petugas(kddokter.getText(), var.getkode());
                dlgrwjl2.isCek();
            }
        } else {
            DlgRawatJalan dlgrwjl2 = new DlgRawatJalan(null, false);
            dlgrwjl2.setSize(internalFrame1.getWidth() - 40, internalFrame1.getHeight() - 40);
            dlgrwjl2.setLocationRelativeTo(internalFrame1);
            dlgrwjl2.setNoRm(TNoRw.getText(), DTPCari1.getDate(), DTPCari2.getDate());
            dlgrwjl2.tampilDrPr();
            dlgrwjl2.TotalNominal();
            dlgrwjl2.setVisible(true);
            dlgrwjl2.fokus();
            dlgrwjl2.petugas(kddokter.getText(), var.getkode());
            dlgrwjl2.isCek();
        }
    }

    private void getdataRiwKunj() {
        norw_dipilih = "";
        kddokter_dipilih = "";

        if (tbRiwayatKunj.getSelectedRow() != -1) {
            norw_dipilih = tbRiwayatKunj.getValueAt(tbRiwayatKunj.getSelectedRow(), 1).toString();
            pasiendipilih.setText(tbRiwayatKunj.getValueAt(tbRiwayatKunj.getSelectedRow(), 2).toString() + " - "
                    + tbRiwayatKunj.getValueAt(tbRiwayatKunj.getSelectedRow(), 4).toString() + " (Tgl. Kunj. : "
                    + tbRiwayatKunj.getValueAt(tbRiwayatKunj.getSelectedRow(), 3).toString() + ")");
            kddokter_dipilih = tbRiwayatKunj.getValueAt(tbRiwayatKunj.getSelectedRow(), 7).toString();
            Valid.SetTgl(TglKunRwt, Sequel.cariIsi("select tgl_registrasi from reg_periksa where no_rawat='" + norw_dipilih + "'"));
        }
    }
    
    private void OtomatisRefresStart() {
        ActionListener taskPerformer = new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if (ChkAutoRefres.isSelected() == true) {
                    if (!DTPCari1.getSelectedItem().equals(DTPCari2.getSelectedItem())) {
                        JOptionPane.showMessageDialog(null, "Tgl. periode harus sama, jika berbeda fitur auto refresh data tdk. bisa digunakan..!!");
                        ChkAutoRefres.setSelected(false);
                    } else if (CrPoli.getText().equals("")) {
                        JOptionPane.showMessageDialog(null, "Poliklinik dimaksud harus dipilih dulu..!!");
                        ChkAutoRefres.setSelected(false);
                        BtnSeek4.requestFocus();
                    } else {
                        tampilkasir();
                    }                    
                }
            }
        };
        // Timer
        //interval 1000 ms = 1 detik
        //interval 30000 ms = 30 detik atau setngah menit
        new Timer(30000, taskPerformer).start();
    }
    
    private void OtomatisRefresStop() {
        ActionListener taskPerformer = new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if (ChkAutoRefres.isSelected() == true) {
                    if (!DTPCari1.getSelectedItem().equals(DTPCari2.getSelectedItem())) {
                        JOptionPane.showMessageDialog(null, "Tgl. periode harus sama, jika berbeda fitur auto refresh data tdk. bisa digunakan..!!");
                        ChkAutoRefres.setSelected(false);
                    } else if (CrPoli.getText().equals("")) {
                        JOptionPane.showMessageDialog(null, "Poliklinik dimaksud harus dipilih dulu..!!");
                        ChkAutoRefres.setSelected(false);
                        BtnSeek4.requestFocus();
                    } else {
                        tampilkasir();
                    }                    
                }
            }
        };
        // Timer
        //interval 1000 ms = 1 detik
        //interval 30000 ms = 30 detik atau setngah menit
        new Timer(30000, taskPerformer).stop();
    }
}
