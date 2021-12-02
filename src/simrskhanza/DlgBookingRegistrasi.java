package simrskhanza;

import bridging.BPJSApi;
import bridging.BPJSCekNoKartu;
import bridging.BPJSCekReferensiDokterDPJP;
import bridging.BPJSCekReferensiFaskes;
import bridging.BPJSCekReferensiKabupaten;
import bridging.BPJSCekReferensiKecamatan;
import bridging.BPJSCekReferensiPenyakit;
import bridging.BPJSCekReferensiPoli;
import bridging.BPJSCekReferensiPropinsi;
import bridging.BPJSDataSEP;
import bridging.BPJSRujukanKeluar;
import bridging.DlgSKDPBPJS;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import fungsi.WarnaTable;
import fungsi.batasInput;
import fungsi.koneksiDB;
import fungsi.sekuel;
import fungsi.validasi;
import fungsi.var;
import informasi.InformasiJadwal;
import java.awt.Cursor;
import java.awt.Dimension;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.event.WindowEvent;
import java.awt.event.WindowListener;
import java.io.FileInputStream;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.temporal.ChronoUnit;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import java.util.Properties;
import javax.swing.JOptionPane;
import javax.swing.JTable;
import javax.swing.event.DocumentEvent;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableColumn;
import org.bouncycastle.util.Strings;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.MediaType;
import simrskhanza.DlgCariDokter;
import simrskhanza.DlgCariPoli;
import simrskhanza.DlgPasien;
import simrskhanza.DlgRujukMasuk;

/**
 *
 * @author dosen
 */
public class DlgBookingRegistrasi extends javax.swing.JDialog {

    private final DefaultTableModel tabMode, tabMode1, tabMode2, tabMode3, tabMode4;
    private Connection koneksi = koneksiDB.condb();
    private sekuel Sequel = new sekuel();
    private validasi Valid = new validasi();
    private Properties prop = new Properties();
    private PreparedStatement ps, pskelengkapanBPJS;
    private ResultSet rs, rskelengkapanBPJS;
    private int i = 0, pilihan = 1;
    private BPJSApi api = new BPJSApi();
    public DlgCariDokter2 dokter = new DlgCariDokter2(null, false);
    private DlgCariPoli poli = new DlgCariPoli(null, false);
    private BPJSCekReferensiPoli poliBPJS = new BPJSCekReferensiPoli(null, false);
    private DlgPasien pasien = new DlgPasien(null, false);
    public DlgRujukMasuk rujukmasuk = new DlgRujukMasuk(null, false);
    private BPJSCekReferensiPenyakit penyakit = new BPJSCekReferensiPenyakit(null, false);
    private DlgSKDPBPJS skdp = new DlgSKDPBPJS(null, false);
    private BPJSCekReferensiDokterDPJP dpjp = new BPJSCekReferensiDokterDPJP(null, false);
    private BPJSCekNoKartu cekViaBPJSKartu = new BPJSCekNoKartu();
    private BPJSCekReferensiFaskes faskes = new BPJSCekReferensiFaskes(null, false);
    private BPJSCekReferensiPropinsi provinsi = new BPJSCekReferensiPropinsi(null, false);
    private BPJSCekReferensiKabupaten kabupaten = new BPJSCekReferensiKabupaten(null, false);
    private BPJSCekReferensiKecamatan kecamatan = new BPJSCekReferensiKecamatan(null, false);
    public DlgBahasa bahasa = new DlgBahasa(null, false);
    public DlgSuku suku = new DlgSuku(null, false);
    private String URUTNOREG = "", status = "", no_rawat = "", umur = "", sttsumur = "", no_peserta = "", user = "", penjamin = "",
            jasaraharja = "", BPJS = "", Taspen = "", Asabri = "", tglkkl = "0000-00-00";
    private LocalDate date1, date2;
//    private Date date = new Date();
//    private Date date2 = new Date(), timeIn, timeOut, dateIn, dateOut;

    /**
     * Creates new form DlgPemberianInfus
     *
     * @param parent
     * @param modal
     */
    public DlgBookingRegistrasi(java.awt.Frame parent, boolean modal) {
        super(parent, modal);
        initComponents();
        WindowCariNoRujuk.setSize(874, 250);

        tabMode = new DefaultTableModel(null, new Object[]{
            "Kode Booking",
            "Tgl. Booking",
            "No. RM",
            "Nama Pasien",
            "Tgl. Periksa",
            "Jenis Pasien",
            "Poliklinik",
            "Nama Dokter",
            "No.Reg",
            "Alamatnya",
            "Status Booking",
            "Verifikasi Data",
            "kd_poli",
            "kd_dokter",
            "kd_penjab",
            "No. Telp. Pemesan",
            "No. Rawat",
            "Status Cetak SEP"
        }) {
            @Override
            public boolean isCellEditable(int rowIndex, int colIndex) {
                boolean a = false;
                if (colIndex == 0) {
                    a = true;
                }
                return a;
            }
            Class[] types = new Class[]{
                java.lang.Object.class, java.lang.Object.class, java.lang.Object.class,
                java.lang.Object.class, java.lang.Object.class, java.lang.Object.class,
                java.lang.Object.class, java.lang.Object.class, java.lang.Object.class,
                java.lang.Object.class, java.lang.Object.class, java.lang.Object.class,
                java.lang.Object.class, java.lang.Object.class, java.lang.Object.class,
                java.lang.Object.class, java.lang.Object.class, java.lang.Object.class
            };

            @Override
            public Class getColumnClass(int columnIndex) {
                return types[columnIndex];
            }
        };
        tbObat.setModel(tabMode);

        //tbObat.setDefaultRenderer(Object.class, new WarnaTable(panelJudul.getBackground(),tbObat.getBackground()));
        tbObat.setPreferredScrollableViewportSize(new Dimension(500, 500));
        tbObat.setAutoResizeMode(JTable.AUTO_RESIZE_OFF);

        for (i = 0; i < 18; i++) {
            TableColumn column = tbObat.getColumnModel().getColumn(i);
            if (i == 0) {
                column.setPreferredWidth(100);
            } else if (i == 1) {
                column.setPreferredWidth(75);
            } else if (i == 2) {
                column.setPreferredWidth(58);
            } else if (i == 3) {
                column.setPreferredWidth(200);
            } else if (i == 4) {
                column.setPreferredWidth(75);
            } else if (i == 5) {
                column.setPreferredWidth(120);
            } else if (i == 6) {
                column.setPreferredWidth(170);
            } else if (i == 7) {
                column.setPreferredWidth(210);
            } else if (i == 8) {
                column.setPreferredWidth(50);
            } else if (i == 9) {
                column.setPreferredWidth(360);
            } else if (i == 10) {
                column.setPreferredWidth(80);
            } else if (i == 11) {
                column.setPreferredWidth(120);
            } else if (i == 12) {
                column.setMinWidth(0);
                column.setMaxWidth(0);
            } else if (i == 13) {
                column.setMinWidth(0);
                column.setMaxWidth(0);
            } else if (i == 14) {
                column.setMinWidth(0);
                column.setMaxWidth(0);
            } else if (i == 15) {
                column.setPreferredWidth(100);
            } else if (i == 16) {
                column.setPreferredWidth(110);
            } else if (i == 17) {
                column.setPreferredWidth(100);
            }
        }
        tbObat.setDefaultRenderer(Object.class, new WarnaTable());

        //tabel riwayat rujukan faskes 1
        tabMode1 = new DefaultTableModel(null, new String[]{"No.", "No. Rujukan", "Tgl. Rujukan",
            "kode_ppk", "Nama PPK Rujukan", "Poli Rujukan", "kode_icd", "Diagnosa", "Keluhan", "kode_poli",
            "kode_ply", "Jns. Pelayn."}) {
            @Override
            public boolean isCellEditable(int rowIndex, int colIndex) {
                return false;
            }
        };

        tbFaskes1.setModel(tabMode1);
        tbFaskes1.setPreferredScrollableViewportSize(new Dimension(500, 500));
        tbFaskes1.setAutoResizeMode(JTable.AUTO_RESIZE_OFF);

        for (int i = 0; i < 12; i++) {
            TableColumn column = tbFaskes1.getColumnModel().getColumn(i);
            if (i == 0) {
                column.setPreferredWidth(35);
            } else if (i == 1) {
                column.setPreferredWidth(125);
            } else if (i == 2) {
                column.setPreferredWidth(90);
            } else if (i == 3) {//sembunyi
                //column.setPreferredWidth(200);
                column.setMinWidth(0);
                column.setMaxWidth(0);
            } else if (i == 4) {
                column.setPreferredWidth(200);
            } else if (i == 5) {
                column.setPreferredWidth(200);
            } else if (i == 6) {//sembunyi
                //column.setPreferredWidth(200);
                column.setMinWidth(0);
                column.setMaxWidth(0);
            } else if (i == 7) {
                column.setPreferredWidth(200);
            } else if (i == 8) {
                column.setPreferredWidth(200);
            } else if (i == 9) {//sembunyi
                //column.setPreferredWidth(200);
                column.setMinWidth(0);
                column.setMaxWidth(0);
            } else if (i == 10) {//sembunyi
                //column.setPreferredWidth(200);
                column.setMinWidth(0);
                column.setMaxWidth(0);
            } else if (i == 11) {
                column.setPreferredWidth(150);
            }
        }
        tbFaskes1.setDefaultRenderer(Object.class, new WarnaTable());

        //tabel riwayat rujukan faskes 2
        tabMode2 = new DefaultTableModel(null, new String[]{"No.", "No. Rujukan", "Tgl. Rujukan",
            "kode_ppk", "Nama PPK Rujukan", "Poli Rujukan", "kode_icd", "Diagnosa", "Keluhan", "kode_poli",
            "kode_ply", "Jns. Pelayn."}) {
            @Override
            public boolean isCellEditable(int rowIndex, int colIndex) {
                return false;
            }
        };

        tbFaskes2.setModel(tabMode2);
        tbFaskes2.setPreferredScrollableViewportSize(new Dimension(500, 500));
        tbFaskes2.setAutoResizeMode(JTable.AUTO_RESIZE_OFF);

        for (int i = 0; i < 12; i++) {
            TableColumn column = tbFaskes2.getColumnModel().getColumn(i);
            if (i == 0) {
                column.setPreferredWidth(35);
            } else if (i == 1) {
                column.setPreferredWidth(125);
            } else if (i == 2) {
                column.setPreferredWidth(90);
            } else if (i == 3) {//sembunyi
                //column.setPreferredWidth(200);
                column.setMinWidth(0);
                column.setMaxWidth(0);
            } else if (i == 4) {
                column.setPreferredWidth(200);
            } else if (i == 5) {
                column.setPreferredWidth(200);
            } else if (i == 6) {//sembunyi
                //column.setPreferredWidth(200);
                column.setMinWidth(0);
                column.setMaxWidth(0);
            } else if (i == 7) {
                column.setPreferredWidth(200);
            } else if (i == 8) {
                column.setPreferredWidth(200);
            } else if (i == 9) {//sembunyi
                //column.setPreferredWidth(200);
                column.setMinWidth(0);
                column.setMaxWidth(0);
            } else if (i == 10) {//sembunyi
                //column.setPreferredWidth(200);
                column.setMinWidth(0);
                column.setMaxWidth(0);
            } else if (i == 11) {
                column.setPreferredWidth(150);
            }
        }
        tbFaskes2.setDefaultRenderer(Object.class, new WarnaTable());

        //tabel riwayat rujukan faskes 1 banyak
        tabMode3 = new DefaultTableModel(null, new String[]{"No.", "No. Rujukan", "Tgl. Rujukan",
            "kode_ppk", "Nama PPK Rujukan", "Poli Rujukan", "kode_icd", "Diagnosa", "Keluhan", "kode_poli",
            "kode_ply", "Jns. Pelayn."}) {
            @Override
            public boolean isCellEditable(int rowIndex, int colIndex) {
                return false;
            }
        };

        tbFaskes3.setModel(tabMode3);
        tbFaskes3.setPreferredScrollableViewportSize(new Dimension(500, 500));
        tbFaskes3.setAutoResizeMode(JTable.AUTO_RESIZE_OFF);

        for (int i = 0; i < 12; i++) {
            TableColumn column = tbFaskes3.getColumnModel().getColumn(i);
            if (i == 0) {
                column.setPreferredWidth(35);
            } else if (i == 1) {
                column.setPreferredWidth(125);
            } else if (i == 2) {
                column.setPreferredWidth(90);
            } else if (i == 3) {//sembunyi
                //column.setPreferredWidth(200);
                column.setMinWidth(0);
                column.setMaxWidth(0);
            } else if (i == 4) {
                column.setPreferredWidth(200);
            } else if (i == 5) {
                column.setPreferredWidth(200);
            } else if (i == 6) {//sembunyi
                //column.setPreferredWidth(200);
                column.setMinWidth(0);
                column.setMaxWidth(0);
            } else if (i == 7) {
                column.setPreferredWidth(200);
            } else if (i == 8) {
                column.setPreferredWidth(200);
            } else if (i == 9) {//sembunyi
                //column.setPreferredWidth(200);
                column.setMinWidth(0);
                column.setMaxWidth(0);
            } else if (i == 10) {//sembunyi
                //column.setPreferredWidth(200);
                column.setMinWidth(0);
                column.setMaxWidth(0);
            } else if (i == 11) {
                column.setPreferredWidth(150);
            }
        }
        tbFaskes3.setDefaultRenderer(Object.class, new WarnaTable());

        //tabel riwayat rujukan faskes 2 banyak
        tabMode4 = new DefaultTableModel(null, new String[]{"No.", "No. Rujukan", "Tgl. Rujukan",
            "kode_ppk", "Nama PPK Rujukan", "Poli Rujukan", "kode_icd", "Diagnosa", "Keluhan", "kode_poli",
            "kode_ply", "Jns. Pelayn."}) {
            @Override
            public boolean isCellEditable(int rowIndex, int colIndex) {
                return false;
            }
        };

        tbFaskes4.setModel(tabMode4);
        tbFaskes4.setPreferredScrollableViewportSize(new Dimension(500, 500));
        tbFaskes4.setAutoResizeMode(JTable.AUTO_RESIZE_OFF);

        for (int i = 0; i < 12; i++) {
            TableColumn column = tbFaskes4.getColumnModel().getColumn(i);
            if (i == 0) {
                column.setPreferredWidth(35);
            } else if (i == 1) {
                column.setPreferredWidth(125);
            } else if (i == 2) {
                column.setPreferredWidth(90);
            } else if (i == 3) {//sembunyi
                //column.setPreferredWidth(200);
                column.setMinWidth(0);
                column.setMaxWidth(0);
            } else if (i == 4) {
                column.setPreferredWidth(200);
            } else if (i == 5) {
                column.setPreferredWidth(200);
            } else if (i == 6) {//sembunyi
                //column.setPreferredWidth(200);
                column.setMinWidth(0);
                column.setMaxWidth(0);
            } else if (i == 7) {
                column.setPreferredWidth(200);
            } else if (i == 8) {
                column.setPreferredWidth(200);
            } else if (i == 9) {//sembunyi
                //column.setPreferredWidth(200);
                column.setMinWidth(0);
                column.setMaxWidth(0);
            } else if (i == 10) {//sembunyi
                //column.setPreferredWidth(200);
                column.setMinWidth(0);
                column.setMaxWidth(0);
            } else if (i == 11) {
                column.setPreferredWidth(150);
            }
        }
        tbFaskes4.setDefaultRenderer(Object.class, new WarnaTable());

        TNoRM.setDocument(new batasInput((byte) 6).getOnlyAngka(TNoRM));
        TCari.setDocument(new batasInput((byte) 100).getKata(TCari));
        KdDokter.setDocument(new batasInput((byte) 3).getKata(KdDokter));
        no_telp.setDocument(new batasInput((byte) 13).getOnlyAngka(no_telp));
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

        ChkInput.setSelected(false);
        isForm();

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
                    isNomer();
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

        suku.addWindowListener(new WindowListener() {
            @Override
            public void windowOpened(WindowEvent e) {
            }

            @Override
            public void windowClosing(WindowEvent e) {
            }

            @Override
            public void windowClosed(WindowEvent e) {
                if (var.getform().equals("DlgBookingRegistrasi")) {
                    if (suku.getTable().getSelectedRow() != -1) {
                        kdsuku.setText(suku.getTable().getValueAt(suku.getTable().getSelectedRow(), 0).toString());
                        nmsuku.setText(suku.getTable().getValueAt(suku.getTable().getSelectedRow(), 1).toString());
                        BtnBahasa.requestFocus();
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

        suku.getTable().addKeyListener(new KeyListener() {
            @Override
            public void keyTyped(KeyEvent e) {
            }

            @Override
            public void keyPressed(KeyEvent e) {
                if (var.getform().equals("DlgBookingRegistrasi")) {
                    if (e.getKeyCode() == KeyEvent.VK_SPACE) {
                        suku.dispose();
                    }
                }
            }

            @Override
            public void keyReleased(KeyEvent e) {
            }
        });

        bahasa.addWindowListener(new WindowListener() {
            @Override
            public void windowOpened(WindowEvent e) {
            }

            @Override
            public void windowClosing(WindowEvent e) {
            }

            @Override
            public void windowClosed(WindowEvent e) {
                if (var.getform().equals("DlgBookingRegistrasi")) {
                    if (bahasa.getTable().getSelectedRow() != -1) {
                        kdbahasa.setText(bahasa.getTable().getValueAt(bahasa.getTable().getSelectedRow(), 0).toString());
                        nmbahasa.setText(bahasa.getTable().getValueAt(bahasa.getTable().getSelectedRow(), 1).toString());
                        BtnSimpan.requestFocus();
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

        bahasa.getTable().addKeyListener(new KeyListener() {
            @Override
            public void keyTyped(KeyEvent e) {
            }

            @Override
            public void keyPressed(KeyEvent e) {
                if (var.getform().equals("DlgBookingRegistrasi")) {
                    if (e.getKeyCode() == KeyEvent.VK_SPACE) {
                        bahasa.dispose();
                    }
                }
            }

            @Override
            public void keyReleased(KeyEvent e) {
            }
        });

        provinsi.addWindowListener(new WindowListener() {
            @Override
            public void windowOpened(WindowEvent e) {
            }

            @Override
            public void windowClosing(WindowEvent e) {
            }

            @Override
            public void windowClosed(WindowEvent e) {
                if (provinsi.getTable().getSelectedRow() != -1) {
                    KdProv.setText(provinsi.getTable().getValueAt(provinsi.getTable().getSelectedRow(), 1).toString());
                    NmProv.setText(provinsi.getTable().getValueAt(provinsi.getTable().getSelectedRow(), 2).toString());
                    btnProv.requestFocus();
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

        provinsi.getTable().addKeyListener(new KeyListener() {
            @Override
            public void keyTyped(KeyEvent e) {
            }

            @Override
            public void keyPressed(KeyEvent e) {
                if (e.getKeyCode() == KeyEvent.VK_SPACE) {
                    provinsi.dispose();
                }
            }

            @Override
            public void keyReleased(KeyEvent e) {
            }
        });

        kabupaten.addWindowListener(new WindowListener() {
            @Override
            public void windowOpened(WindowEvent e) {
            }

            @Override
            public void windowClosing(WindowEvent e) {
            }

            @Override
            public void windowClosed(WindowEvent e) {
                if (kabupaten.getTable().getSelectedRow() != -1) {
                    KdKab.setText(kabupaten.getTable().getValueAt(kabupaten.getTable().getSelectedRow(), 1).toString());
                    NmKab.setText(kabupaten.getTable().getValueAt(kabupaten.getTable().getSelectedRow(), 2).toString());
                    btnKab.requestFocus();
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

        kabupaten.getTable().addKeyListener(new KeyListener() {
            @Override
            public void keyTyped(KeyEvent e) {
            }

            @Override
            public void keyPressed(KeyEvent e) {
                if (e.getKeyCode() == KeyEvent.VK_SPACE) {
                    kabupaten.dispose();
                }
            }

            @Override
            public void keyReleased(KeyEvent e) {
            }
        });

        kecamatan.addWindowListener(new WindowListener() {
            @Override
            public void windowOpened(WindowEvent e) {
            }

            @Override
            public void windowClosing(WindowEvent e) {
            }

            @Override
            public void windowClosed(WindowEvent e) {
                if (kecamatan.getTable().getSelectedRow() != -1) {
                    KdKec.setText(kecamatan.getTable().getValueAt(kecamatan.getTable().getSelectedRow(), 1).toString());
                    NmKec.setText(kecamatan.getTable().getValueAt(kecamatan.getTable().getSelectedRow(), 2).toString());
                    btnKec.requestFocus();
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

        kecamatan.getTable().addKeyListener(new KeyListener() {
            @Override
            public void keyTyped(KeyEvent e) {
            }

            @Override
            public void keyPressed(KeyEvent e) {
                if (e.getKeyCode() == KeyEvent.VK_SPACE) {
                    kecamatan.dispose();
                }
            }

            @Override
            public void keyReleased(KeyEvent e) {
            }
        });

        dpjp.addWindowListener(new WindowListener() {
            @Override
            public void windowOpened(WindowEvent e) {
            }

            @Override
            public void windowClosing(WindowEvent e) {
            }

            @Override
            public void windowClosed(WindowEvent e) {
                if (dpjp.getTable().getSelectedRow() != -1) {
                    Kddpjp.setText(dpjp.getTable().getValueAt(dpjp.getTable().getSelectedRow(), 1).toString());
                    NmDPJP.setText(dpjp.getTable().getValueAt(dpjp.getTable().getSelectedRow(), 2).toString());
                    btnDPJP.requestFocus();
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

        dpjp.getTable().addKeyListener(new KeyListener() {
            @Override
            public void keyTyped(KeyEvent e) {
            }

            @Override
            public void keyPressed(KeyEvent e) {
                if (e.getKeyCode() == KeyEvent.VK_SPACE) {
                    dpjp.dispose();
                }
            }

            @Override
            public void keyReleased(KeyEvent e) {
            }
        });

        skdp.addWindowListener(new WindowListener() {
            @Override
            public void windowOpened(WindowEvent e) {
            }

            @Override
            public void windowClosing(WindowEvent e) {
            }

            @Override
            public void windowClosed(WindowEvent e) {
                if (skdp.getTable().getSelectedRow() != -1) {
                    noSKDP.setText(skdp.getTable().getValueAt(skdp.getTable().getSelectedRow(), 11).toString());
                    btnDPJP.requestFocus();
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

        skdp.getTable().addKeyListener(new KeyListener() {
            @Override
            public void keyTyped(KeyEvent e) {
            }

            @Override
            public void keyPressed(KeyEvent e) {
                if (e.getKeyCode() == KeyEvent.VK_SPACE) {
                    skdp.dispose();
                }
            }

            @Override
            public void keyReleased(KeyEvent e) {
            }
        });

        poliBPJS.addWindowListener(new WindowListener() {
            @Override
            public void windowOpened(WindowEvent e) {
            }

            @Override
            public void windowClosing(WindowEvent e) {
            }

            @Override
            public void windowClosed(WindowEvent e) {
                if (poliBPJS.getTable().getSelectedRow() != -1) {
                    if (pilihan == 1) {
//                        KdPoli.setText(poli.getTable().getValueAt(poli.getTable().getSelectedRow(),1).toString());
//                        NmPoli.setText(poli.getTable().getValueAt(poli.getTable().getSelectedRow(),2).toString());
//                        KdPoli.requestFocus();

                        KdPoli1.setText(poliBPJS.getTable().getValueAt(poliBPJS.getTable().getSelectedRow(), 1).toString());
                        NmPoli1.setText(poliBPJS.getTable().getValueAt(poliBPJS.getTable().getSelectedRow(), 2).toString());
                        KdPoli1.requestFocus();
                    } else if (pilihan == 2) {
                        KdPoli1.setText(poliBPJS.getTable().getValueAt(poliBPJS.getTable().getSelectedRow(), 1).toString());
                        NmPoli1.setText(poliBPJS.getTable().getValueAt(poliBPJS.getTable().getSelectedRow(), 2).toString());
                        KdPoli1.requestFocus();
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

        poliBPJS.getTable().addKeyListener(new KeyListener() {
            @Override
            public void keyTyped(KeyEvent e) {
            }

            @Override
            public void keyPressed(KeyEvent e) {
                if (e.getKeyCode() == KeyEvent.VK_SPACE) {
                    poliBPJS.dispose();
                }
            }

            @Override
            public void keyReleased(KeyEvent e) {
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
                if (var.getform().equals("DlgBookingRegistrasi")) {
                    if (rujukmasuk.tbPerujuk.getSelectedRow() != -1) {
                        KdPpkRujukan.setText(rujukmasuk.tbPerujuk.getValueAt(rujukmasuk.tbPerujuk.getSelectedRow(), 1).toString());
                        NmPpkRujukan.setText(rujukmasuk.tbPerujuk.getValueAt(rujukmasuk.tbPerujuk.getSelectedRow(), 2).toString());
                        kode_rujukanya.setText(rujukmasuk.tbPerujuk.getValueAt(rujukmasuk.tbPerujuk.getSelectedRow(), 4).toString());
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

        penyakit.addWindowListener(new WindowListener() {
            @Override
            public void windowOpened(WindowEvent e) {
            }

            @Override
            public void windowClosing(WindowEvent e) {
            }

            @Override
            public void windowClosed(WindowEvent e) {
                if (penyakit.getTable().getSelectedRow() != -1) {
                    if (pilihan == 1) {
                        KdPenyakit.setText(penyakit.getTable().getValueAt(penyakit.getTable().getSelectedRow(), 1).toString());
                        NmPenyakit.setText(penyakit.getTable().getValueAt(penyakit.getTable().getSelectedRow(), 2).toString());
                        KdPenyakit.requestFocus();
                    } else if (pilihan == 2) {
                        KdPenyakit.setText(penyakit.getTable().getValueAt(penyakit.getTable().getSelectedRow(), 1).toString());
                        NmPenyakit.setText(penyakit.getTable().getValueAt(penyakit.getTable().getSelectedRow(), 2).toString());
                        KdPenyakit.requestFocus();

//                        KdPenyakit1.setText(penyakit.getTable().getValueAt(penyakit.getTable().getSelectedRow(),1).toString());
//                        NmPenyakit1.setText(penyakit.getTable().getValueAt(penyakit.getTable().getSelectedRow(),2).toString());
//                        KdPenyakit1.requestFocus();
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

        penyakit.getTable().addKeyListener(new KeyListener() {
            @Override
            public void keyTyped(KeyEvent e) {
            }

            @Override
            public void keyPressed(KeyEvent e) {
                if (e.getKeyCode() == KeyEvent.VK_SPACE) {
                    penyakit.dispose();
                }
            }

            @Override
            public void keyReleased(KeyEvent e) {
            }
        });

        faskes.addWindowListener(new WindowListener() {
            @Override
            public void windowOpened(WindowEvent e) {
            }

            @Override
            public void windowClosing(WindowEvent e) {
            }

            @Override
            public void windowClosed(WindowEvent e) {
                if (faskes.getTable().getSelectedRow() != -1) {
                    if (pilihan == 1) {
                        KdPpkRujukan.setText(faskes.getTable().getValueAt(faskes.getTable().getSelectedRow(), 1).toString());
                        NmPpkRujukan.setText(faskes.getTable().getValueAt(faskes.getTable().getSelectedRow(), 2).toString());
                        KdPpkRujukan.requestFocus();
                    } else if (pilihan == 2) {
                        KdPpkRujukan.setText(faskes.getTable().getValueAt(faskes.getTable().getSelectedRow(), 1).toString());
                        NmPpkRujukan.setText(faskes.getTable().getValueAt(faskes.getTable().getSelectedRow(), 2).toString());
                        KdPpkRujukan.requestFocus();

//                        KdPpkRujukan1.setText(faskes.getTable().getValueAt(faskes.getTable().getSelectedRow(),1).toString());
//                        NmPpkRujukan1.setText(faskes.getTable().getValueAt(faskes.getTable().getSelectedRow(),2).toString());
//                        KdPpkRujukan1.requestFocus();
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

        faskes.getTable().addKeyListener(new KeyListener() {
            @Override
            public void keyTyped(KeyEvent e) {
            }

            @Override
            public void keyPressed(KeyEvent e) {
                if (e.getKeyCode() == KeyEvent.VK_SPACE) {
                    faskes.dispose();
                }
            }

            @Override
            public void keyReleased(KeyEvent e) {
            }
        });

        poli.addWindowListener(new WindowListener() {
            @Override
            public void windowOpened(WindowEvent e) {
            }

            @Override
            public void windowClosing(WindowEvent e) {
            }

            @Override
            public void windowClosed(WindowEvent e) {
                if (poli.getTable().getSelectedRow() != -1) {
                    KdPoli.setText(poli.getTable().getValueAt(poli.getTable().getSelectedRow(), 0).toString());
                    NmPoli.setText(poli.getTable().getValueAt(poli.getTable().getSelectedRow(), 1).toString());

                    KdPoli1.setText(poli.getTable().getValueAt(poli.getTable().getSelectedRow(), 0).toString());
                    NmPoli1.setText(poli.getTable().getValueAt(poli.getTable().getSelectedRow(), 1).toString());
                    isNomer();
                    KdDokter.setText("");
                    NmDokter.setText("");
                    TanggalPeriksa.requestFocus();
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

        pasien.addWindowListener(new WindowListener() {
            @Override
            public void windowOpened(WindowEvent e) {
            }

            @Override
            public void windowClosing(WindowEvent e) {
            }

            @Override
            public void windowClosed(WindowEvent e) {
                if (pasien.getTable().getSelectedRow() != -1) {
                    TNoRM.setText(pasien.getTable().getValueAt(pasien.getTable().getSelectedRow(), 1).toString());
                    TPasien.setText(pasien.getTable().getValueAt(pasien.getTable().getSelectedRow(), 2).toString());
                    Sequel.cariIsi("select jk from pasien where no_rkm_medis=? ", jk, TNoRM.getText());
                    Sequel.cariIsi("select no_rkm_medis from pasien_mati where no_rkm_medis=? ", rmMati, TNoRM.getText());
                    kdsuku.setText(pasien.getTable().getValueAt(pasien.getTable().getSelectedRow(), 26).toString());
                    kdbahasa.setText(pasien.getTable().getValueAt(pasien.getTable().getSelectedRow(), 27).toString());
                    Sequel.cariIsi("select nama_suku_bangsa from suku_bangsa where id=?", nmsuku, kdsuku.getText());
                    Sequel.cariIsi("select nama_bahasa from bahasa_pasien where id=?", nmbahasa, kdbahasa.getText());
                }
                BtnPasien.requestFocus();
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

        pasien.getTable().addKeyListener(new KeyListener() {
            @Override
            public void keyTyped(KeyEvent e) {
            }

            @Override
            public void keyPressed(KeyEvent e) {
                if (e.getKeyCode() == KeyEvent.VK_SPACE) {
                    pasien.dispose();
                }
            }

            @Override
            public void keyReleased(KeyEvent e) {
            }
        });

        pasien.penjab.addWindowListener(new WindowListener() {
            @Override
            public void windowOpened(WindowEvent e) {
            }

            @Override
            public void windowClosing(WindowEvent e) {
            }

            @Override
            public void windowClosed(WindowEvent e) {
                if (var.getform().equals("DlgBookingRegistrasi")) {
                    if (pasien.penjab.getTable().getSelectedRow() != -1) {
                        kdpnj.setText(pasien.penjab.getTable().getValueAt(pasien.penjab.getTable().getSelectedRow(), 1).toString());
                        nmpnj.setText(pasien.penjab.getTable().getValueAt(pasien.penjab.getTable().getSelectedRow(), 2).toString());
                        no_telp.requestFocus();

                        if (var.getkode().equals("Admin Utama") || (kdpnj.getText().equals("B01") || (kdpnj.getText().equals("A03")))) {
                            FormInput.setEnabledAt(1, true);
                            FormInput.setEnabledAt(2, true);
                        } else if ((!kdpnj.getText().equals("B01") || (!kdpnj.getText().equals("A03")))) {
                            FormInput.setEnabledAt(1, false);
                            FormInput.setEnabledAt(2, false);
                            FormInput.setSelectedIndex(0);
                            emptKelengkapanSEP();
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

        pasien.penjab.getTable().addKeyListener(new KeyListener() {
            @Override
            public void keyTyped(KeyEvent e) {
            }

            @Override
            public void keyPressed(KeyEvent e) {
                if (var.getform().equals("DlgBookingRegistrasi")) {
                    if (e.getKeyCode() == KeyEvent.VK_SPACE) {
                        pasien.penjab.dispose();
                    }
                }
            }

            @Override
            public void keyReleased(KeyEvent e) {
            }
        });

        try {
            prop.loadFromXML(new FileInputStream("setting/database.xml"));
            URUTNOREG = prop.getProperty("URUTNOREG");
        } catch (Exception e) {
            URUTNOREG = "";
            System.out.println("SEP XML : " + e);
        }

        try {
            user = var.getkode().replace(" ", "").substring(0, 9);
        } catch (Exception e) {
            user = var.getkode();
        }

        cekLAYAN();
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
        Popup = new javax.swing.JPopupMenu();
        MnCetakKodeBarkode = new javax.swing.JMenuItem();
        MnCetakKodeQR = new javax.swing.JMenuItem();
        MnTidakjadibatal = new javax.swing.JMenuItem();
        MnSEP = new javax.swing.JMenuItem();
        MnSttsCetakSEP = new javax.swing.JMenu();
        Mnsudah = new javax.swing.JMenuItem();
        Mnbelum = new javax.swing.JMenuItem();
        Mngagal = new javax.swing.JMenuItem();
        TanggalBooking = new widget.Tanggal();
        WindowCariNoRujuk = new javax.swing.JDialog();
        internalFrame8 = new widget.InternalFrame();
        TabRujukan = new javax.swing.JTabbedPane();
        internalFrame4 = new widget.InternalFrame();
        scrollPane1 = new widget.ScrollPane();
        tbFaskes1 = new widget.Table();
        internalFrame5 = new widget.InternalFrame();
        scrollPane2 = new widget.ScrollPane();
        tbFaskes2 = new widget.Table();
        internalFrame6 = new widget.InternalFrame();
        scrollPane3 = new widget.ScrollPane();
        tbFaskes3 = new widget.Table();
        internalFrame9 = new widget.InternalFrame();
        scrollPane4 = new widget.ScrollPane();
        tbFaskes4 = new widget.Table();
        panelisi3 = new widget.panelisi();
        BtnKeluar1 = new widget.Button();
        kode_rujukanya = new widget.TextBox();
        nmfaskes_keluar = new widget.TextBox();
        LokasiLaka = new widget.TextBox();
        jk = new widget.TextBox();
        rmMati = new widget.TextBox();
        jamMati = new widget.TextBox();
        tglMati = new widget.TextBox();
        cekKDboking = new widget.TextBox();
        statusboking = new widget.TextBox();
        kdsuku = new widget.TextBox();
        kdbahasa = new widget.TextBox();
        cekKelengkapanSEP = new widget.TextBox();
        internalFrame1 = new widget.InternalFrame();
        Scroll = new widget.ScrollPane();
        tbObat = new widget.Table();
        jPanel3 = new javax.swing.JPanel();
        panelGlass8 = new widget.panelisi();
        BtnSimpan = new widget.Button();
        BtnBatal = new widget.Button();
        BtnGanti = new widget.Button();
        BtnHapus = new widget.Button();
        BtnPrint = new widget.Button();
        BtnAll = new widget.Button();
        BtnKeluar = new widget.Button();
        BtnJadwal = new widget.Button();
        panelGlass10 = new widget.panelisi();
        jLabel6 = new widget.Label();
        TCari = new widget.TextBox();
        BtnCari = new widget.Button();
        jLabel7 = new widget.Label();
        LCount = new widget.Label();
        panelCari = new widget.panelisi();
        R2 = new widget.RadioButton();
        DTPCari1 = new widget.Tanggal();
        jLabel22 = new widget.Label();
        DTPCari2 = new widget.Tanggal();
        R3 = new widget.RadioButton();
        DTPCari3 = new widget.Tanggal();
        jLabel25 = new widget.Label();
        DTPCari4 = new widget.Tanggal();
        PanelInput = new javax.swing.JPanel();
        ChkInput = new widget.CekBox();
        FormInput = new widget.TabPane();
        internalFrame2 = new widget.InternalFrame();
        panelisi1 = new widget.panelisi();
        jLabel4 = new widget.Label();
        TNoRM = new widget.TextBox();
        jLabel9 = new widget.Label();
        NmDokter = new widget.TextBox();
        TPasien = new widget.TextBox();
        KdDokter = new widget.TextBox();
        BtnDokter = new widget.Button();
        jLabel11 = new widget.Label();
        KdPoli = new widget.TextBox();
        NmPoli = new widget.TextBox();
        BtnPoli = new widget.Button();
        jLabel14 = new widget.Label();
        TanggalPeriksa = new widget.Tanggal();
        NoReg = new widget.TextBox();
        jLabel18 = new widget.Label();
        BtnPasien = new widget.Button();
        jLabel19 = new widget.Label();
        kdpnj = new widget.TextBox();
        nmpnj = new widget.TextBox();
        btnPenjab = new widget.Button();
        kdboking = new widget.TextBox();
        jLabel21 = new widget.Label();
        jLabel23 = new widget.Label();
        no_telp = new widget.TextBox();
        jLabel29 = new widget.Label();
        norawat = new widget.TextBox();
        jLabel32 = new widget.Label();
        nmsuku = new widget.TextBox();
        BtnSuku = new widget.Button();
        jLabel40 = new widget.Label();
        nmbahasa = new widget.TextBox();
        BtnBahasa = new widget.Button();
        jLabel33 = new widget.Label();
        verif_data = new widget.ComboBox();
        internalFrame3 = new widget.InternalFrame();
        panelisi2 = new widget.panelisi();
        jLabel8 = new widget.Label();
        TglLahir = new widget.TextBox();
        jLabel24 = new widget.Label();
        JenisPeserta = new widget.TextBox();
        jLabel20 = new widget.Label();
        JK = new widget.TextBox();
        jLabel5 = new widget.Label();
        NoKartu = new widget.TextBox();
        jLabel26 = new widget.Label();
        Status = new widget.TextBox();
        jLabel27 = new widget.Label();
        NoRujukan = new widget.TextBox();
        btnNoRujukan = new widget.Button();
        jLabel28 = new widget.Label();
        TanggalRujuk = new widget.Tanggal();
        jLabel10 = new widget.Label();
        noSKDP = new widget.TextBox();
        btnNoSKDP = new widget.Button();
        jLabel30 = new widget.Label();
        AsalRujukan = new widget.ComboBox();
        rujukanSEP = new widget.Label();
        jLabel42 = new widget.Label();
        Kddpjp = new widget.TextBox();
        NmDPJP = new widget.TextBox();
        btnDPJP = new widget.Button();
        jLabel12 = new widget.Label();
        KdPpkRujukan = new widget.TextBox();
        NmPpkRujukan = new widget.TextBox();
        btnPPKRujukan = new widget.Button();
        jLabel13 = new widget.Label();
        KdPenyakit = new widget.TextBox();
        NmPenyakit = new widget.TextBox();
        btnDiagnosa = new widget.Button();
        LabelPoli = new widget.Label();
        KdPoli1 = new widget.TextBox();
        NmPoli1 = new widget.TextBox();
        btnPoli = new widget.Button();
        LabelKatarak = new widget.Label();
        KasusKatarak = new widget.ComboBox();
        jLabel15 = new widget.Label();
        jLabel16 = new widget.Label();
        JenisPelayanan = new widget.ComboBox();
        Eksekutif = new widget.ComboBox();
        LabelKelas = new widget.Label();
        LabelKelas1 = new widget.Label();
        Kelas = new widget.ComboBox();
        COB = new widget.ComboBox();
        Scroll3 = new widget.ScrollPane();
        Catatan = new widget.TextArea();
        jLabel17 = new widget.Label();
        jLabel43 = new widget.Label();
        KdPPK = new widget.TextBox();
        NmPPK = new widget.TextBox();
        jLabel31 = new widget.Label();
        NoTelp = new widget.TextBox();
        label_cetak = new widget.Label();
        statusSEP = new widget.TextBox();
        internalFrame7 = new widget.InternalFrame();
        panelisi4 = new widget.panelisi();
        jLabel36 = new widget.Label();
        LakaLantas = new widget.ComboBox();
        LabTglkll = new widget.Label();
        TanggalKejadian = new widget.Tanggal();
        LabjaminKll = new widget.Label();
        ChkJasaRaharja = new widget.CekBox();
        ChkTaspen = new widget.CekBox();
        ChkBPJSTenaga = new widget.CekBox();
        ChkAsa = new widget.CekBox();
        LabKetkll = new widget.Label();
        Ket = new widget.TextBox();
        LabProv = new widget.Label();
        KdProv = new widget.TextBox();
        NmProv = new widget.TextBox();
        btnProv = new widget.Button();
        LabKab = new widget.Label();
        KdKab = new widget.TextBox();
        NmKab = new widget.TextBox();
        btnKab = new widget.Button();
        LabKec = new widget.Label();
        KdKec = new widget.TextBox();
        NmKec = new widget.TextBox();
        btnKec = new widget.Button();
        LabSup = new widget.Label();
        suplesi = new widget.ComboBox();
        LabNoSup = new widget.Label();
        NoSEPSuplesi = new widget.TextBox();

        Popup.setName("Popup"); // NOI18N
        Popup.setPreferredSize(new java.awt.Dimension(200, 145));

        MnCetakKodeBarkode.setBackground(new java.awt.Color(255, 255, 255));
        MnCetakKodeBarkode.setFont(new java.awt.Font("Tahoma", 0, 11)); // NOI18N
        MnCetakKodeBarkode.setIcon(new javax.swing.ImageIcon(getClass().getResource("/picture/category.png"))); // NOI18N
        MnCetakKodeBarkode.setText("Cetak Kode Booking (Barcode)");
        MnCetakKodeBarkode.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        MnCetakKodeBarkode.setHorizontalTextPosition(javax.swing.SwingConstants.RIGHT);
        MnCetakKodeBarkode.setIconTextGap(5);
        MnCetakKodeBarkode.setName("MnCetakKodeBarkode"); // NOI18N
        MnCetakKodeBarkode.setPreferredSize(new java.awt.Dimension(200, 28));
        MnCetakKodeBarkode.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                MnCetakKodeBarkodeActionPerformed(evt);
            }
        });
        Popup.add(MnCetakKodeBarkode);

        MnCetakKodeQR.setBackground(new java.awt.Color(255, 255, 255));
        MnCetakKodeQR.setFont(new java.awt.Font("Tahoma", 0, 11)); // NOI18N
        MnCetakKodeQR.setIcon(new javax.swing.ImageIcon(getClass().getResource("/picture/category.png"))); // NOI18N
        MnCetakKodeQR.setText("Cetak Kode Booking (QR Code)");
        MnCetakKodeQR.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        MnCetakKodeQR.setHorizontalTextPosition(javax.swing.SwingConstants.RIGHT);
        MnCetakKodeQR.setIconTextGap(5);
        MnCetakKodeQR.setName("MnCetakKodeQR"); // NOI18N
        MnCetakKodeQR.setPreferredSize(new java.awt.Dimension(200, 28));
        MnCetakKodeQR.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                MnCetakKodeQRActionPerformed(evt);
            }
        });
        Popup.add(MnCetakKodeQR);

        MnTidakjadibatal.setBackground(new java.awt.Color(255, 255, 255));
        MnTidakjadibatal.setFont(new java.awt.Font("Tahoma", 0, 11)); // NOI18N
        MnTidakjadibatal.setIcon(new javax.swing.ImageIcon(getClass().getResource("/picture/category.png"))); // NOI18N
        MnTidakjadibatal.setText("Tidak Jadi Batal");
        MnTidakjadibatal.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        MnTidakjadibatal.setHorizontalTextPosition(javax.swing.SwingConstants.RIGHT);
        MnTidakjadibatal.setIconTextGap(5);
        MnTidakjadibatal.setName("MnTidakjadibatal"); // NOI18N
        MnTidakjadibatal.setPreferredSize(new java.awt.Dimension(200, 28));
        MnTidakjadibatal.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                MnTidakjadibatalActionPerformed(evt);
            }
        });
        Popup.add(MnTidakjadibatal);

        MnSEP.setBackground(new java.awt.Color(255, 255, 255));
        MnSEP.setFont(new java.awt.Font("Tahoma", 0, 11)); // NOI18N
        MnSEP.setIcon(new javax.swing.ImageIcon(getClass().getResource("/picture/category.png"))); // NOI18N
        MnSEP.setText("Bridging SEP BPJS");
        MnSEP.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        MnSEP.setHorizontalTextPosition(javax.swing.SwingConstants.RIGHT);
        MnSEP.setIconTextGap(5);
        MnSEP.setName("MnSEP"); // NOI18N
        MnSEP.setPreferredSize(new java.awt.Dimension(200, 28));
        MnSEP.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                MnSEPActionPerformed(evt);
            }
        });
        Popup.add(MnSEP);

        MnSttsCetakSEP.setBackground(new java.awt.Color(248, 253, 243));
        MnSttsCetakSEP.setIcon(new javax.swing.ImageIcon(getClass().getResource("/picture/category.png"))); // NOI18N
        MnSttsCetakSEP.setText("Update Status Cetak SEP");
        MnSttsCetakSEP.setFont(new java.awt.Font("Tahoma", 0, 11)); // NOI18N
        MnSttsCetakSEP.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        MnSttsCetakSEP.setHorizontalTextPosition(javax.swing.SwingConstants.RIGHT);
        MnSttsCetakSEP.setIconTextGap(5);
        MnSttsCetakSEP.setName("MnSttsCetakSEP"); // NOI18N
        MnSttsCetakSEP.setOpaque(true);
        MnSttsCetakSEP.setPreferredSize(new java.awt.Dimension(200, 28));

        Mnsudah.setBackground(new java.awt.Color(255, 255, 255));
        Mnsudah.setFont(new java.awt.Font("Tahoma", 0, 11)); // NOI18N
        Mnsudah.setIcon(new javax.swing.ImageIcon(getClass().getResource("/picture/category.png"))); // NOI18N
        Mnsudah.setText("SUDAH");
        Mnsudah.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        Mnsudah.setHorizontalTextPosition(javax.swing.SwingConstants.RIGHT);
        Mnsudah.setIconTextGap(5);
        Mnsudah.setName("Mnsudah"); // NOI18N
        Mnsudah.setPreferredSize(new java.awt.Dimension(80, 28));
        Mnsudah.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                MnsudahActionPerformed(evt);
            }
        });
        MnSttsCetakSEP.add(Mnsudah);

        Mnbelum.setBackground(new java.awt.Color(255, 255, 255));
        Mnbelum.setFont(new java.awt.Font("Tahoma", 0, 11)); // NOI18N
        Mnbelum.setIcon(new javax.swing.ImageIcon(getClass().getResource("/picture/category.png"))); // NOI18N
        Mnbelum.setText("BELUM");
        Mnbelum.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        Mnbelum.setHorizontalTextPosition(javax.swing.SwingConstants.RIGHT);
        Mnbelum.setIconTextGap(5);
        Mnbelum.setName("Mnbelum"); // NOI18N
        Mnbelum.setPreferredSize(new java.awt.Dimension(80, 28));
        Mnbelum.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                MnbelumActionPerformed(evt);
            }
        });
        MnSttsCetakSEP.add(Mnbelum);

        Mngagal.setBackground(new java.awt.Color(255, 255, 255));
        Mngagal.setFont(new java.awt.Font("Tahoma", 0, 11)); // NOI18N
        Mngagal.setIcon(new javax.swing.ImageIcon(getClass().getResource("/picture/category.png"))); // NOI18N
        Mngagal.setText("GAGAL");
        Mngagal.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        Mngagal.setHorizontalTextPosition(javax.swing.SwingConstants.RIGHT);
        Mngagal.setIconTextGap(5);
        Mngagal.setName("Mngagal"); // NOI18N
        Mngagal.setPreferredSize(new java.awt.Dimension(80, 28));
        Mngagal.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                MngagalActionPerformed(evt);
            }
        });
        MnSttsCetakSEP.add(Mngagal);

        Popup.add(MnSttsCetakSEP);

        TanggalBooking.setEditable(false);
        TanggalBooking.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "09-04-2020" }));
        TanggalBooking.setDisplayFormat("dd-MM-yyyy");
        TanggalBooking.setName("TanggalBooking"); // NOI18N
        TanggalBooking.setOpaque(false);
        TanggalBooking.addItemListener(new java.awt.event.ItemListener() {
            public void itemStateChanged(java.awt.event.ItemEvent evt) {
                TanggalBookingItemStateChanged(evt);
            }
        });
        TanggalBooking.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                TanggalBookingKeyPressed(evt);
            }
        });

        WindowCariNoRujuk.setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);
        WindowCariNoRujuk.setName("WindowCariNoRujuk"); // NOI18N
        WindowCariNoRujuk.setUndecorated(true);
        WindowCariNoRujuk.setResizable(false);
        WindowCariNoRujuk.getContentPane().setLayout(new java.awt.BorderLayout(1, 1));

        internalFrame8.setBorder(javax.swing.BorderFactory.createTitledBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(240, 245, 235)), "::[ Cek Riwayat Rujukan Faskes ]::", javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Tahoma", 1, 12))); // NOI18N
        internalFrame8.setName("internalFrame8"); // NOI18N
        internalFrame8.setWarnaBawah(new java.awt.Color(245, 250, 240));
        internalFrame8.setLayout(new java.awt.BorderLayout(1, 1));

        TabRujukan.setToolTipText("");
        TabRujukan.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        TabRujukan.setName("TabRujukan"); // NOI18N
        TabRujukan.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                TabRujukanMouseClicked(evt);
            }
        });

        internalFrame4.setName("internalFrame4"); // NOI18N
        internalFrame4.setLayout(new java.awt.BorderLayout(1, 1));

        scrollPane1.setName("scrollPane1"); // NOI18N
        scrollPane1.setOpaque(true);

        tbFaskes1.setToolTipText("Klik data di tabel");
        tbFaskes1.setName("tbFaskes1"); // NOI18N
        tbFaskes1.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                tbFaskes1MouseClicked(evt);
            }
        });
        tbFaskes1.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                tbFaskes1KeyPressed(evt);
            }
        });
        scrollPane1.setViewportView(tbFaskes1);

        internalFrame4.add(scrollPane1, java.awt.BorderLayout.CENTER);

        TabRujukan.addTab(".: Faskes 1", internalFrame4);

        internalFrame5.setName("internalFrame5"); // NOI18N
        internalFrame5.setLayout(new java.awt.BorderLayout(1, 1));

        scrollPane2.setName("scrollPane2"); // NOI18N
        scrollPane2.setOpaque(true);

        tbFaskes2.setToolTipText("Klik data di tabel");
        tbFaskes2.setName("tbFaskes2"); // NOI18N
        tbFaskes2.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                tbFaskes2MouseClicked(evt);
            }
        });
        tbFaskes2.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                tbFaskes2KeyPressed(evt);
            }
        });
        scrollPane2.setViewportView(tbFaskes2);

        internalFrame5.add(scrollPane2, java.awt.BorderLayout.CENTER);

        TabRujukan.addTab(".: Faskes 2 (RS)", internalFrame5);

        internalFrame6.setName("internalFrame6"); // NOI18N
        internalFrame6.setLayout(new java.awt.BorderLayout(1, 1));

        scrollPane3.setName("scrollPane3"); // NOI18N
        scrollPane3.setOpaque(true);

        tbFaskes3.setToolTipText("Klik data di tabel");
        tbFaskes3.setName("tbFaskes3"); // NOI18N
        tbFaskes3.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                tbFaskes3MouseClicked(evt);
            }
        });
        tbFaskes3.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                tbFaskes3KeyPressed(evt);
            }
        });
        scrollPane3.setViewportView(tbFaskes3);

        internalFrame6.add(scrollPane3, java.awt.BorderLayout.CENTER);

        TabRujukan.addTab(".: Faskes 1 (banyak)", internalFrame6);

        internalFrame9.setName("internalFrame9"); // NOI18N
        internalFrame9.setLayout(new java.awt.BorderLayout(1, 1));

        scrollPane4.setName("scrollPane4"); // NOI18N
        scrollPane4.setOpaque(true);

        tbFaskes4.setToolTipText("Klik data di tabel");
        tbFaskes4.setName("tbFaskes4"); // NOI18N
        tbFaskes4.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                tbFaskes4MouseClicked(evt);
            }
        });
        tbFaskes4.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                tbFaskes4KeyPressed(evt);
            }
        });
        scrollPane4.setViewportView(tbFaskes4);

        internalFrame9.add(scrollPane4, java.awt.BorderLayout.CENTER);

        TabRujukan.addTab(".: Faskes 2 (RS) banyak", internalFrame9);

        internalFrame8.add(TabRujukan, java.awt.BorderLayout.CENTER);

        panelisi3.setName("panelisi3"); // NOI18N
        panelisi3.setPreferredSize(new java.awt.Dimension(44, 54));
        panelisi3.setLayout(new java.awt.FlowLayout(java.awt.FlowLayout.LEFT, 5, 9));

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
        panelisi3.add(BtnKeluar1);

        internalFrame8.add(panelisi3, java.awt.BorderLayout.PAGE_END);

        WindowCariNoRujuk.getContentPane().add(internalFrame8, java.awt.BorderLayout.CENTER);

        kode_rujukanya.setForeground(new java.awt.Color(0, 0, 0));
        kode_rujukanya.setHighlighter(null);
        kode_rujukanya.setName("kode_rujukanya"); // NOI18N
        kode_rujukanya.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                kode_rujukanyaKeyPressed(evt);
            }
        });

        nmfaskes_keluar.setForeground(new java.awt.Color(0, 0, 0));
        nmfaskes_keluar.setHighlighter(null);
        nmfaskes_keluar.setName("nmfaskes_keluar"); // NOI18N
        nmfaskes_keluar.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                nmfaskes_keluarKeyPressed(evt);
            }
        });

        LokasiLaka.setForeground(new java.awt.Color(0, 0, 0));
        LokasiLaka.setHighlighter(null);
        LokasiLaka.setName("LokasiLaka"); // NOI18N
        LokasiLaka.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                LokasiLakaKeyPressed(evt);
            }
        });

        jk.setForeground(new java.awt.Color(0, 0, 0));
        jk.setHighlighter(null);
        jk.setName("jk"); // NOI18N
        jk.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                jkKeyPressed(evt);
            }
        });

        rmMati.setForeground(new java.awt.Color(0, 0, 0));
        rmMati.setHighlighter(null);
        rmMati.setName("rmMati"); // NOI18N
        rmMati.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                rmMatiKeyPressed(evt);
            }
        });

        jamMati.setForeground(new java.awt.Color(0, 0, 0));
        jamMati.setHighlighter(null);
        jamMati.setName("jamMati"); // NOI18N
        jamMati.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                jamMatiKeyPressed(evt);
            }
        });

        tglMati.setForeground(new java.awt.Color(0, 0, 0));
        tglMati.setHighlighter(null);
        tglMati.setName("tglMati"); // NOI18N
        tglMati.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                tglMatiKeyPressed(evt);
            }
        });

        cekKDboking.setForeground(new java.awt.Color(0, 0, 0));
        cekKDboking.setHighlighter(null);
        cekKDboking.setName("cekKDboking"); // NOI18N
        cekKDboking.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                cekKDbokingKeyPressed(evt);
            }
        });

        statusboking.setForeground(new java.awt.Color(0, 0, 0));
        statusboking.setToolTipText("");
        statusboking.setHighlighter(null);
        statusboking.setName("statusboking"); // NOI18N
        statusboking.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                statusbokingKeyPressed(evt);
            }
        });

        kdsuku.setEditable(false);
        kdsuku.setForeground(new java.awt.Color(0, 0, 0));
        kdsuku.setHighlighter(null);
        kdsuku.setName("kdsuku"); // NOI18N
        kdsuku.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                kdsukuKeyPressed(evt);
            }
        });

        kdbahasa.setEditable(false);
        kdbahasa.setForeground(new java.awt.Color(0, 0, 0));
        kdbahasa.setHighlighter(null);
        kdbahasa.setName("kdbahasa"); // NOI18N
        kdbahasa.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                kdbahasaKeyPressed(evt);
            }
        });

        cekKelengkapanSEP.setForeground(new java.awt.Color(0, 0, 0));
        cekKelengkapanSEP.setHighlighter(null);
        cekKelengkapanSEP.setName("cekKelengkapanSEP"); // NOI18N
        cekKelengkapanSEP.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                cekKelengkapanSEPKeyPressed(evt);
            }
        });

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);
        setUndecorated(true);
        setResizable(false);
        addWindowListener(new java.awt.event.WindowAdapter() {
            public void windowOpened(java.awt.event.WindowEvent evt) {
                formWindowOpened(evt);
            }
        });

        internalFrame1.setBorder(javax.swing.BorderFactory.createTitledBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(240, 245, 235)), "::[ Booking Registrasi ]::", javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Tahoma", 1, 12))); // NOI18N
        internalFrame1.setName("internalFrame1"); // NOI18N
        internalFrame1.setLayout(new java.awt.BorderLayout(1, 1));

        Scroll.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(204, 204, 255)));
        Scroll.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        Scroll.setName("Scroll"); // NOI18N
        Scroll.setOpaque(true);

        tbObat.setAutoCreateRowSorter(true);
        tbObat.setToolTipText("Silahkan klik untuk memilih data yang ataupun dihapus");
        tbObat.setComponentPopupMenu(Popup);
        tbObat.setName("tbObat"); // NOI18N
        tbObat.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                tbObatMouseClicked(evt);
            }
        });
        tbObat.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                tbObatKeyPressed(evt);
            }
        });
        Scroll.setViewportView(tbObat);

        internalFrame1.add(Scroll, java.awt.BorderLayout.CENTER);

        jPanel3.setName("jPanel3"); // NOI18N
        jPanel3.setOpaque(false);
        jPanel3.setPreferredSize(new java.awt.Dimension(44, 144));
        jPanel3.setLayout(new java.awt.BorderLayout(1, 1));

        panelGlass8.setName("panelGlass8"); // NOI18N
        panelGlass8.setPreferredSize(new java.awt.Dimension(55, 55));
        panelGlass8.setLayout(new java.awt.FlowLayout(java.awt.FlowLayout.LEFT, 5, 9));

        BtnSimpan.setForeground(new java.awt.Color(0, 0, 0));
        BtnSimpan.setIcon(new javax.swing.ImageIcon(getClass().getResource("/picture/save-16x16.png"))); // NOI18N
        BtnSimpan.setMnemonic('S');
        BtnSimpan.setText("Simpan");
        BtnSimpan.setToolTipText("Alt+S");
        BtnSimpan.setName("BtnSimpan"); // NOI18N
        BtnSimpan.setPreferredSize(new java.awt.Dimension(100, 30));
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
        panelGlass8.add(BtnSimpan);

        BtnBatal.setForeground(new java.awt.Color(0, 0, 0));
        BtnBatal.setIcon(new javax.swing.ImageIcon(getClass().getResource("/picture/Cancel-2-16x16.png"))); // NOI18N
        BtnBatal.setMnemonic('B');
        BtnBatal.setText("Baru");
        BtnBatal.setToolTipText("Alt+B");
        BtnBatal.setName("BtnBatal"); // NOI18N
        BtnBatal.setPreferredSize(new java.awt.Dimension(100, 30));
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
        panelGlass8.add(BtnBatal);

        BtnGanti.setForeground(new java.awt.Color(0, 0, 0));
        BtnGanti.setIcon(new javax.swing.ImageIcon(getClass().getResource("/picture/inventaris.png"))); // NOI18N
        BtnGanti.setMnemonic('G');
        BtnGanti.setText("Ganti");
        BtnGanti.setToolTipText("Alt+G");
        BtnGanti.setName("BtnGanti"); // NOI18N
        BtnGanti.setPreferredSize(new java.awt.Dimension(100, 30));
        BtnGanti.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                BtnGantiActionPerformed(evt);
            }
        });
        BtnGanti.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                BtnGantiKeyPressed(evt);
            }
        });
        panelGlass8.add(BtnGanti);

        BtnHapus.setForeground(new java.awt.Color(0, 0, 0));
        BtnHapus.setIcon(new javax.swing.ImageIcon(getClass().getResource("/picture/stop_f2.png"))); // NOI18N
        BtnHapus.setMnemonic('H');
        BtnHapus.setText("Batal Booking");
        BtnHapus.setToolTipText("Alt+H");
        BtnHapus.setName("BtnHapus"); // NOI18N
        BtnHapus.setPreferredSize(new java.awt.Dimension(120, 30));
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
        panelGlass8.add(BtnHapus);

        BtnPrint.setForeground(new java.awt.Color(0, 0, 0));
        BtnPrint.setIcon(new javax.swing.ImageIcon(getClass().getResource("/picture/b_print.png"))); // NOI18N
        BtnPrint.setMnemonic('T');
        BtnPrint.setText("Cetak");
        BtnPrint.setToolTipText("Alt+T");
        BtnPrint.setName("BtnPrint"); // NOI18N
        BtnPrint.setPreferredSize(new java.awt.Dimension(100, 30));
        BtnPrint.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                BtnPrintActionPerformed(evt);
            }
        });
        BtnPrint.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                BtnPrintKeyPressed(evt);
            }
        });
        panelGlass8.add(BtnPrint);

        BtnAll.setForeground(new java.awt.Color(0, 0, 0));
        BtnAll.setIcon(new javax.swing.ImageIcon(getClass().getResource("/picture/Search-16x16.png"))); // NOI18N
        BtnAll.setMnemonic('M');
        BtnAll.setText("Semua");
        BtnAll.setToolTipText("Alt+M");
        BtnAll.setName("BtnAll"); // NOI18N
        BtnAll.setPreferredSize(new java.awt.Dimension(100, 30));
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
        panelGlass8.add(BtnAll);

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
        panelGlass8.add(BtnKeluar);

        BtnJadwal.setForeground(new java.awt.Color(0, 0, 0));
        BtnJadwal.setIcon(new javax.swing.ImageIcon(getClass().getResource("/picture/accept_page.png"))); // NOI18N
        BtnJadwal.setMnemonic('J');
        BtnJadwal.setText("Jadwal Dokter");
        BtnJadwal.setToolTipText("Alt+J");
        BtnJadwal.setName("BtnJadwal"); // NOI18N
        BtnJadwal.setPreferredSize(new java.awt.Dimension(130, 30));
        BtnJadwal.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                BtnJadwalActionPerformed(evt);
            }
        });
        BtnJadwal.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                BtnJadwalKeyPressed(evt);
            }
        });
        panelGlass8.add(BtnJadwal);

        jPanel3.add(panelGlass8, java.awt.BorderLayout.PAGE_END);

        panelGlass10.setName("panelGlass10"); // NOI18N
        panelGlass10.setPreferredSize(new java.awt.Dimension(44, 44));
        panelGlass10.setLayout(new java.awt.FlowLayout(java.awt.FlowLayout.LEFT, 5, 9));

        jLabel6.setForeground(new java.awt.Color(0, 0, 0));
        jLabel6.setText("Key Word :");
        jLabel6.setName("jLabel6"); // NOI18N
        jLabel6.setPreferredSize(new java.awt.Dimension(70, 23));
        panelGlass10.add(jLabel6);

        TCari.setForeground(new java.awt.Color(0, 0, 0));
        TCari.setName("TCari"); // NOI18N
        TCari.setPreferredSize(new java.awt.Dimension(450, 23));
        TCari.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                TCariKeyPressed(evt);
            }
        });
        panelGlass10.add(TCari);

        BtnCari.setForeground(new java.awt.Color(0, 0, 0));
        BtnCari.setIcon(new javax.swing.ImageIcon(getClass().getResource("/picture/accept.png"))); // NOI18N
        BtnCari.setMnemonic('2');
        BtnCari.setText("Tampilkan Data");
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
        panelGlass10.add(BtnCari);

        jLabel7.setForeground(new java.awt.Color(0, 0, 0));
        jLabel7.setText("Record :");
        jLabel7.setName("jLabel7"); // NOI18N
        jLabel7.setPreferredSize(new java.awt.Dimension(65, 23));
        panelGlass10.add(jLabel7);

        LCount.setForeground(new java.awt.Color(0, 0, 0));
        LCount.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        LCount.setText("0");
        LCount.setName("LCount"); // NOI18N
        LCount.setPreferredSize(new java.awt.Dimension(50, 23));
        panelGlass10.add(LCount);

        jPanel3.add(panelGlass10, java.awt.BorderLayout.CENTER);

        panelCari.setName("panelCari"); // NOI18N
        panelCari.setPreferredSize(new java.awt.Dimension(44, 43));
        panelCari.setLayout(new java.awt.FlowLayout(java.awt.FlowLayout.LEFT, 2, 9));

        R2.setBackground(new java.awt.Color(240, 250, 230));
        R2.setBorder(javax.swing.BorderFactory.createLineBorder(java.awt.Color.pink));
        buttonGroup1.add(R2);
        R2.setSelected(true);
        R2.setText("Tanggal Booking :");
        R2.setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);
        R2.setHorizontalTextPosition(javax.swing.SwingConstants.RIGHT);
        R2.setName("R2"); // NOI18N
        R2.setPreferredSize(new java.awt.Dimension(125, 23));
        panelCari.add(R2);

        DTPCari1.setEditable(false);
        DTPCari1.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "09-04-2020" }));
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
        DTPCari2.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "09-04-2020" }));
        DTPCari2.setDisplayFormat("dd-MM-yyyy");
        DTPCari2.setName("DTPCari2"); // NOI18N
        DTPCari2.setOpaque(false);
        DTPCari2.setPreferredSize(new java.awt.Dimension(90, 23));
        DTPCari2.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                DTPCari2KeyPressed(evt);
            }
        });
        panelCari.add(DTPCari2);

        R3.setBackground(new java.awt.Color(240, 250, 230));
        R3.setBorder(javax.swing.BorderFactory.createLineBorder(java.awt.Color.pink));
        buttonGroup1.add(R3);
        R3.setText("Tanggal Periksa :");
        R3.setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);
        R3.setHorizontalTextPosition(javax.swing.SwingConstants.RIGHT);
        R3.setName("R3"); // NOI18N
        R3.setPreferredSize(new java.awt.Dimension(135, 23));
        panelCari.add(R3);

        DTPCari3.setEditable(false);
        DTPCari3.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "09-04-2020" }));
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
        DTPCari4.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "09-04-2020" }));
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

        jPanel3.add(panelCari, java.awt.BorderLayout.PAGE_START);

        internalFrame1.add(jPanel3, java.awt.BorderLayout.PAGE_END);

        PanelInput.setName("PanelInput"); // NOI18N
        PanelInput.setOpaque(false);
        PanelInput.setPreferredSize(new java.awt.Dimension(192, 315));
        PanelInput.setLayout(new java.awt.BorderLayout(1, 1));

        ChkInput.setForeground(new java.awt.Color(0, 0, 0));
        ChkInput.setIcon(new javax.swing.ImageIcon(getClass().getResource("/picture/143.png"))); // NOI18N
        ChkInput.setMnemonic('M');
        ChkInput.setText(".: Input Data");
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
        PanelInput.add(ChkInput, java.awt.BorderLayout.PAGE_END);

        FormInput.setForeground(new java.awt.Color(0, 0, 0));
        FormInput.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        FormInput.setName("FormInput"); // NOI18N
        FormInput.setPreferredSize(new java.awt.Dimension(400, 210));
        FormInput.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                FormInputMouseClicked(evt);
            }
        });

        internalFrame2.setName("internalFrame2"); // NOI18N
        internalFrame2.setLayout(new java.awt.BorderLayout());

        panelisi1.setName("panelisi1"); // NOI18N
        panelisi1.setLayout(null);

        jLabel4.setForeground(new java.awt.Color(0, 0, 0));
        jLabel4.setText("No. RM : ");
        jLabel4.setName("jLabel4"); // NOI18N
        panelisi1.add(jLabel4);
        jLabel4.setBounds(0, 10, 73, 23);

        TNoRM.setForeground(new java.awt.Color(0, 0, 0));
        TNoRM.setToolTipText("Harus diisi dengan angka saja..!!!");
        TNoRM.setName("TNoRM"); // NOI18N
        TNoRM.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                TNoRMKeyPressed(evt);
            }
        });
        panelisi1.add(TNoRM);
        TNoRM.setBounds(75, 10, 70, 23);

        jLabel9.setForeground(new java.awt.Color(0, 0, 0));
        jLabel9.setText("Dokter : ");
        jLabel9.setName("jLabel9"); // NOI18N
        panelisi1.add(jLabel9);
        jLabel9.setBounds(0, 94, 73, 23);

        NmDokter.setEditable(false);
        NmDokter.setForeground(new java.awt.Color(0, 0, 0));
        NmDokter.setHighlighter(null);
        NmDokter.setName("NmDokter"); // NOI18N
        panelisi1.add(NmDokter);
        NmDokter.setBounds(168, 94, 300, 23);

        TPasien.setEditable(false);
        TPasien.setForeground(new java.awt.Color(0, 0, 0));
        TPasien.setHighlighter(null);
        TPasien.setName("TPasien"); // NOI18N
        panelisi1.add(TPasien);
        TPasien.setBounds(148, 10, 320, 23);

        KdDokter.setEditable(false);
        KdDokter.setForeground(new java.awt.Color(0, 0, 0));
        KdDokter.setHighlighter(null);
        KdDokter.setName("KdDokter"); // NOI18N
        panelisi1.add(KdDokter);
        KdDokter.setBounds(75, 94, 90, 23);

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
        panelisi1.add(BtnDokter);
        BtnDokter.setBounds(473, 94, 28, 23);

        jLabel11.setForeground(new java.awt.Color(0, 0, 0));
        jLabel11.setText("Poliklinik : ");
        jLabel11.setName("jLabel11"); // NOI18N
        panelisi1.add(jLabel11);
        jLabel11.setBounds(0, 38, 73, 23);

        KdPoli.setEditable(false);
        KdPoli.setForeground(new java.awt.Color(0, 0, 0));
        KdPoli.setHighlighter(null);
        KdPoli.setName("KdPoli"); // NOI18N
        panelisi1.add(KdPoli);
        KdPoli.setBounds(75, 38, 70, 23);

        NmPoli.setEditable(false);
        NmPoli.setForeground(new java.awt.Color(0, 0, 0));
        NmPoli.setHighlighter(null);
        NmPoli.setName("NmPoli"); // NOI18N
        panelisi1.add(NmPoli);
        NmPoli.setBounds(148, 38, 320, 23);

        BtnPoli.setForeground(new java.awt.Color(0, 0, 0));
        BtnPoli.setIcon(new javax.swing.ImageIcon(getClass().getResource("/picture/190.png"))); // NOI18N
        BtnPoli.setMnemonic('X');
        BtnPoli.setToolTipText("Alt+X");
        BtnPoli.setName("BtnPoli"); // NOI18N
        BtnPoli.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                BtnPoliActionPerformed(evt);
            }
        });
        BtnPoli.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                BtnPoliKeyPressed(evt);
            }
        });
        panelisi1.add(BtnPoli);
        BtnPoli.setBounds(473, 38, 28, 23);

        jLabel14.setForeground(new java.awt.Color(0, 0, 0));
        jLabel14.setText("Rencana Tgl. Periksa :");
        jLabel14.setName("jLabel14"); // NOI18N
        panelisi1.add(jLabel14);
        jLabel14.setBounds(0, 66, 115, 23);

        TanggalPeriksa.setEditable(false);
        TanggalPeriksa.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "09-04-2020" }));
        TanggalPeriksa.setDisplayFormat("dd-MM-yyyy");
        TanggalPeriksa.setName("TanggalPeriksa"); // NOI18N
        TanggalPeriksa.setOpaque(false);
        TanggalPeriksa.addItemListener(new java.awt.event.ItemListener() {
            public void itemStateChanged(java.awt.event.ItemEvent evt) {
                TanggalPeriksaItemStateChanged(evt);
            }
        });
        TanggalPeriksa.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                TanggalPeriksaMouseClicked(evt);
            }
        });
        TanggalPeriksa.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                TanggalPeriksaActionPerformed(evt);
            }
        });
        TanggalPeriksa.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                TanggalPeriksaKeyPressed(evt);
            }
        });
        panelisi1.add(TanggalPeriksa);
        TanggalPeriksa.setBounds(120, 66, 90, 23);

        NoReg.setEditable(false);
        NoReg.setForeground(new java.awt.Color(0, 0, 0));
        NoReg.setHighlighter(null);
        NoReg.setName("NoReg"); // NOI18N
        NoReg.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                NoRegKeyPressed(evt);
            }
        });
        panelisi1.add(NoReg);
        NoReg.setBounds(270, 66, 60, 23);

        jLabel18.setForeground(new java.awt.Color(0, 0, 0));
        jLabel18.setText("No.Reg :");
        jLabel18.setName("jLabel18"); // NOI18N
        panelisi1.add(jLabel18);
        jLabel18.setBounds(215, 66, 50, 23);

        BtnPasien.setForeground(new java.awt.Color(0, 0, 0));
        BtnPasien.setIcon(new javax.swing.ImageIcon(getClass().getResource("/picture/190.png"))); // NOI18N
        BtnPasien.setMnemonic('X');
        BtnPasien.setToolTipText("Alt+X");
        BtnPasien.setName("BtnPasien"); // NOI18N
        BtnPasien.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                BtnPasienActionPerformed(evt);
            }
        });
        BtnPasien.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                BtnPasienKeyPressed(evt);
            }
        });
        panelisi1.add(BtnPasien);
        BtnPasien.setBounds(473, 10, 28, 23);

        jLabel19.setForeground(new java.awt.Color(0, 0, 0));
        jLabel19.setText("Jns. Pasien : ");
        jLabel19.setName("jLabel19"); // NOI18N
        panelisi1.add(jLabel19);
        jLabel19.setBounds(0, 123, 73, 23);

        kdpnj.setEditable(false);
        kdpnj.setForeground(new java.awt.Color(0, 0, 0));
        kdpnj.setHighlighter(null);
        kdpnj.setName("kdpnj"); // NOI18N
        kdpnj.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                kdpnjKeyPressed(evt);
            }
        });
        panelisi1.add(kdpnj);
        kdpnj.setBounds(75, 123, 70, 23);

        nmpnj.setEditable(false);
        nmpnj.setForeground(new java.awt.Color(0, 0, 0));
        nmpnj.setName("nmpnj"); // NOI18N
        panelisi1.add(nmpnj);
        nmpnj.setBounds(148, 123, 320, 23);

        btnPenjab.setForeground(new java.awt.Color(0, 0, 0));
        btnPenjab.setIcon(new javax.swing.ImageIcon(getClass().getResource("/picture/190.png"))); // NOI18N
        btnPenjab.setMnemonic('2');
        btnPenjab.setToolTipText("ALt+2");
        btnPenjab.setName("btnPenjab"); // NOI18N
        btnPenjab.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnPenjabActionPerformed(evt);
            }
        });
        btnPenjab.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                btnPenjabKeyPressed(evt);
            }
        });
        panelisi1.add(btnPenjab);
        btnPenjab.setBounds(473, 123, 28, 23);

        kdboking.setEditable(false);
        kdboking.setForeground(new java.awt.Color(0, 0, 0));
        kdboking.setName("kdboking"); // NOI18N
        kdboking.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                kdbokingKeyPressed(evt);
            }
        });
        panelisi1.add(kdboking);
        kdboking.setBounds(610, 10, 130, 23);

        jLabel21.setForeground(new java.awt.Color(0, 0, 0));
        jLabel21.setText("No. Telpn. Pemesan : ");
        jLabel21.setName("jLabel21"); // NOI18N
        panelisi1.add(jLabel21);
        jLabel21.setBounds(0, 181, 115, 23);

        jLabel23.setForeground(new java.awt.Color(0, 0, 0));
        jLabel23.setText("Kode Booking : ");
        jLabel23.setName("jLabel23"); // NOI18N
        panelisi1.add(jLabel23);
        jLabel23.setBounds(520, 10, 85, 23);

        no_telp.setForeground(new java.awt.Color(0, 0, 0));
        no_telp.setToolTipText("Harus diisi dengan angka saja..!!!");
        no_telp.setName("no_telp"); // NOI18N
        no_telp.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                no_telpKeyPressed(evt);
            }
        });
        panelisi1.add(no_telp);
        no_telp.setBounds(118, 181, 128, 23);

        jLabel29.setForeground(new java.awt.Color(0, 0, 0));
        jLabel29.setText("No. Rawat : ");
        jLabel29.setName("jLabel29"); // NOI18N
        panelisi1.add(jLabel29);
        jLabel29.setBounds(520, 38, 85, 23);

        norawat.setEditable(false);
        norawat.setForeground(new java.awt.Color(0, 0, 0));
        norawat.setName("norawat"); // NOI18N
        norawat.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                norawatKeyPressed(evt);
            }
        });
        panelisi1.add(norawat);
        norawat.setBounds(610, 38, 130, 23);

        jLabel32.setForeground(new java.awt.Color(0, 0, 0));
        jLabel32.setText("Suku/Bangsa : ");
        jLabel32.setName("jLabel32"); // NOI18N
        panelisi1.add(jLabel32);
        jLabel32.setBounds(260, 152, 80, 23);

        nmsuku.setEditable(false);
        nmsuku.setForeground(new java.awt.Color(0, 0, 0));
        nmsuku.setName("nmsuku"); // NOI18N
        panelisi1.add(nmsuku);
        nmsuku.setBounds(340, 152, 128, 23);

        BtnSuku.setIcon(new javax.swing.ImageIcon(getClass().getResource("/picture/190.png"))); // NOI18N
        BtnSuku.setMnemonic('1');
        BtnSuku.setToolTipText("ALt+1");
        BtnSuku.setName("BtnSuku"); // NOI18N
        BtnSuku.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                BtnSukuActionPerformed(evt);
            }
        });
        BtnSuku.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                BtnSukuKeyPressed(evt);
            }
        });
        panelisi1.add(BtnSuku);
        BtnSuku.setBounds(473, 152, 28, 23);

        jLabel40.setForeground(new java.awt.Color(0, 0, 0));
        jLabel40.setText("Bahasa Dipakai : ");
        jLabel40.setName("jLabel40"); // NOI18N
        panelisi1.add(jLabel40);
        jLabel40.setBounds(250, 181, 90, 23);

        nmbahasa.setEditable(false);
        nmbahasa.setForeground(new java.awt.Color(0, 0, 0));
        nmbahasa.setName("nmbahasa"); // NOI18N
        panelisi1.add(nmbahasa);
        nmbahasa.setBounds(340, 181, 128, 23);

        BtnBahasa.setIcon(new javax.swing.ImageIcon(getClass().getResource("/picture/190.png"))); // NOI18N
        BtnBahasa.setMnemonic('1');
        BtnBahasa.setToolTipText("ALt+1");
        BtnBahasa.setName("BtnBahasa"); // NOI18N
        BtnBahasa.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                BtnBahasaActionPerformed(evt);
            }
        });
        BtnBahasa.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                BtnBahasaKeyPressed(evt);
            }
        });
        panelisi1.add(BtnBahasa);
        BtnBahasa.setBounds(473, 181, 28, 23);

        jLabel33.setForeground(new java.awt.Color(0, 0, 0));
        jLabel33.setText("Verifikasi Data : ");
        jLabel33.setName("jLabel33"); // NOI18N
        panelisi1.add(jLabel33);
        jLabel33.setBounds(0, 152, 115, 23);

        verif_data.setForeground(new java.awt.Color(0, 0, 0));
        verif_data.setModel(new javax.swing.DefaultComboBoxModel(new String[] { " ", "WhatsApp Messenger", "Telegram" }));
        verif_data.setName("verif_data"); // NOI18N
        verif_data.setOpaque(false);
        verif_data.addItemListener(new java.awt.event.ItemListener() {
            public void itemStateChanged(java.awt.event.ItemEvent evt) {
                verif_dataItemStateChanged(evt);
            }
        });
        verif_data.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                verif_dataMouseClicked(evt);
            }
        });
        verif_data.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                verif_dataKeyPressed(evt);
            }
        });
        panelisi1.add(verif_data);
        verif_data.setBounds(120, 152, 140, 23);

        internalFrame2.add(panelisi1, java.awt.BorderLayout.CENTER);

        FormInput.addTab(".: Data Booking", internalFrame2);

        internalFrame3.setName("internalFrame3"); // NOI18N
        internalFrame3.setLayout(new java.awt.BorderLayout());

        panelisi2.setName("panelisi2"); // NOI18N
        panelisi2.setLayout(null);

        jLabel8.setForeground(new java.awt.Color(0, 0, 0));
        jLabel8.setText("Tgl.Lahir :");
        jLabel8.setName("jLabel8"); // NOI18N
        panelisi2.add(jLabel8);
        jLabel8.setBounds(0, 8, 70, 23);

        TglLahir.setEditable(false);
        TglLahir.setBackground(new java.awt.Color(245, 250, 240));
        TglLahir.setForeground(new java.awt.Color(0, 0, 0));
        TglLahir.setHighlighter(null);
        TglLahir.setName("TglLahir"); // NOI18N
        panelisi2.add(TglLahir);
        TglLahir.setBounds(75, 8, 100, 23);

        jLabel24.setForeground(new java.awt.Color(0, 0, 0));
        jLabel24.setText("Peserta :");
        jLabel24.setName("jLabel24"); // NOI18N
        jLabel24.setPreferredSize(new java.awt.Dimension(55, 23));
        panelisi2.add(jLabel24);
        jLabel24.setBounds(180, 8, 50, 23);

        JenisPeserta.setEditable(false);
        JenisPeserta.setBackground(new java.awt.Color(245, 250, 240));
        JenisPeserta.setForeground(new java.awt.Color(0, 0, 0));
        JenisPeserta.setName("JenisPeserta"); // NOI18N
        panelisi2.add(JenisPeserta);
        JenisPeserta.setBounds(235, 8, 170, 23);

        jLabel20.setForeground(new java.awt.Color(0, 0, 0));
        jLabel20.setText("J.K.: ");
        jLabel20.setName("jLabel20"); // NOI18N
        panelisi2.add(jLabel20);
        jLabel20.setBounds(410, 8, 30, 23);

        JK.setEditable(false);
        JK.setBackground(new java.awt.Color(245, 250, 240));
        JK.setForeground(new java.awt.Color(0, 0, 0));
        JK.setHighlighter(null);
        JK.setName("JK"); // NOI18N
        panelisi2.add(JK);
        JK.setBounds(440, 8, 40, 23);

        jLabel5.setForeground(new java.awt.Color(0, 0, 0));
        jLabel5.setText("No.Kartu :");
        jLabel5.setName("jLabel5"); // NOI18N
        panelisi2.add(jLabel5);
        jLabel5.setBounds(0, 37, 70, 23);

        NoKartu.setEditable(false);
        NoKartu.setBackground(new java.awt.Color(245, 250, 240));
        NoKartu.setForeground(new java.awt.Color(0, 0, 0));
        NoKartu.setName("NoKartu"); // NOI18N
        panelisi2.add(NoKartu);
        NoKartu.setBounds(75, 37, 152, 23);

        jLabel26.setForeground(new java.awt.Color(0, 0, 0));
        jLabel26.setText("Status :");
        jLabel26.setName("jLabel26"); // NOI18N
        jLabel26.setPreferredSize(new java.awt.Dimension(55, 23));
        panelisi2.add(jLabel26);
        jLabel26.setBounds(230, 37, 45, 23);

        Status.setEditable(false);
        Status.setBackground(new java.awt.Color(245, 250, 240));
        Status.setForeground(new java.awt.Color(0, 0, 0));
        Status.setHighlighter(null);
        Status.setName("Status"); // NOI18N
        panelisi2.add(Status);
        Status.setBounds(280, 37, 110, 23);

        jLabel27.setForeground(new java.awt.Color(0, 0, 0));
        jLabel27.setText("No.Rujukan :");
        jLabel27.setName("jLabel27"); // NOI18N
        jLabel27.setPreferredSize(new java.awt.Dimension(55, 23));
        panelisi2.add(jLabel27);
        jLabel27.setBounds(395, 37, 70, 23);

        NoRujukan.setForeground(new java.awt.Color(0, 0, 0));
        NoRujukan.setHighlighter(null);
        NoRujukan.setName("NoRujukan"); // NOI18N
        NoRujukan.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                NoRujukanKeyPressed(evt);
            }
        });
        panelisi2.add(NoRujukan);
        NoRujukan.setBounds(470, 37, 195, 23);

        btnNoRujukan.setForeground(new java.awt.Color(0, 0, 0));
        btnNoRujukan.setIcon(new javax.swing.ImageIcon(getClass().getResource("/picture/190.png"))); // NOI18N
        btnNoRujukan.setMnemonic('X');
        btnNoRujukan.setToolTipText("Alt+X");
        btnNoRujukan.setName("btnNoRujukan"); // NOI18N
        btnNoRujukan.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnNoRujukanActionPerformed(evt);
            }
        });
        btnNoRujukan.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                btnNoRujukanKeyPressed(evt);
            }
        });
        panelisi2.add(btnNoRujukan);
        btnNoRujukan.setBounds(670, 37, 28, 23);

        jLabel28.setForeground(new java.awt.Color(0, 0, 0));
        jLabel28.setText("Tgl.Rujuk :");
        jLabel28.setName("jLabel28"); // NOI18N
        jLabel28.setPreferredSize(new java.awt.Dimension(55, 23));
        panelisi2.add(jLabel28);
        jLabel28.setBounds(0, 67, 70, 23);

        TanggalRujuk.setEditable(false);
        TanggalRujuk.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "09-04-2020" }));
        TanggalRujuk.setDisplayFormat("dd-MM-yyyy");
        TanggalRujuk.setName("TanggalRujuk"); // NOI18N
        TanggalRujuk.setOpaque(false);
        TanggalRujuk.setPreferredSize(new java.awt.Dimension(95, 23));
        TanggalRujuk.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                TanggalRujukMouseClicked(evt);
            }
        });
        TanggalRujuk.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                TanggalRujukKeyPressed(evt);
            }
        });
        panelisi2.add(TanggalRujuk);
        TanggalRujuk.setBounds(75, 67, 90, 23);

        jLabel10.setForeground(new java.awt.Color(0, 0, 0));
        jLabel10.setText("No. Surat SKDP :");
        jLabel10.setName("jLabel10"); // NOI18N
        panelisi2.add(jLabel10);
        jLabel10.setBounds(168, 67, 87, 23);

        noSKDP.setForeground(new java.awt.Color(0, 0, 0));
        noSKDP.setName("noSKDP"); // NOI18N
        noSKDP.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                noSKDPKeyPressed(evt);
            }
        });
        panelisi2.add(noSKDP);
        noSKDP.setBounds(260, 67, 228, 23);

        btnNoSKDP.setForeground(new java.awt.Color(0, 0, 0));
        btnNoSKDP.setIcon(new javax.swing.ImageIcon(getClass().getResource("/picture/190.png"))); // NOI18N
        btnNoSKDP.setMnemonic('X');
        btnNoSKDP.setToolTipText("Alt+X");
        btnNoSKDP.setName("btnNoSKDP"); // NOI18N
        btnNoSKDP.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnNoSKDPActionPerformed(evt);
            }
        });
        btnNoSKDP.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                btnNoSKDPKeyPressed(evt);
            }
        });
        panelisi2.add(btnNoSKDP);
        btnNoSKDP.setBounds(490, 67, 28, 23);

        jLabel30.setForeground(new java.awt.Color(0, 0, 0));
        jLabel30.setText("Asal Rujukan :");
        jLabel30.setName("jLabel30"); // NOI18N
        panelisi2.add(jLabel30);
        jLabel30.setBounds(0, 96, 80, 23);

        AsalRujukan.setForeground(new java.awt.Color(0, 0, 0));
        AsalRujukan.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "1. Faskes 1", "2. Faskes 2(RS)" }));
        AsalRujukan.setName("AsalRujukan"); // NOI18N
        AsalRujukan.setOpaque(false);
        AsalRujukan.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                AsalRujukanMouseClicked(evt);
            }
        });
        AsalRujukan.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                AsalRujukanActionPerformed(evt);
            }
        });
        AsalRujukan.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                AsalRujukanKeyPressed(evt);
            }
        });
        panelisi2.add(AsalRujukan);
        AsalRujukan.setBounds(85, 96, 120, 23);

        rujukanSEP.setForeground(new java.awt.Color(0, 0, 0));
        rujukanSEP.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        rujukanSEP.setText("- belum terisi -");
        rujukanSEP.setName("rujukanSEP"); // NOI18N
        panelisi2.add(rujukanSEP);
        rujukanSEP.setBounds(210, 96, 170, 23);

        jLabel42.setForeground(new java.awt.Color(0, 0, 0));
        jLabel42.setText("DPJP :");
        jLabel42.setName("jLabel42"); // NOI18N
        panelisi2.add(jLabel42);
        jLabel42.setBounds(380, 96, 40, 23);

        Kddpjp.setEditable(false);
        Kddpjp.setBackground(new java.awt.Color(245, 250, 240));
        Kddpjp.setForeground(new java.awt.Color(0, 0, 0));
        Kddpjp.setHighlighter(null);
        Kddpjp.setName("Kddpjp"); // NOI18N
        panelisi2.add(Kddpjp);
        Kddpjp.setBounds(425, 96, 70, 23);

        NmDPJP.setEditable(false);
        NmDPJP.setBackground(new java.awt.Color(245, 250, 240));
        NmDPJP.setForeground(new java.awt.Color(0, 0, 0));
        NmDPJP.setHighlighter(null);
        NmDPJP.setName("NmDPJP"); // NOI18N
        panelisi2.add(NmDPJP);
        NmDPJP.setBounds(498, 96, 190, 23);

        btnDPJP.setForeground(new java.awt.Color(0, 0, 0));
        btnDPJP.setIcon(new javax.swing.ImageIcon(getClass().getResource("/picture/190.png"))); // NOI18N
        btnDPJP.setMnemonic('X');
        btnDPJP.setToolTipText("Alt+X");
        btnDPJP.setName("btnDPJP"); // NOI18N
        btnDPJP.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnDPJPActionPerformed(evt);
            }
        });
        btnDPJP.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                btnDPJPKeyPressed(evt);
            }
        });
        panelisi2.add(btnDPJP);
        btnDPJP.setBounds(690, 96, 28, 23);

        jLabel12.setForeground(new java.awt.Color(0, 0, 0));
        jLabel12.setText("PPK Rujukan :");
        jLabel12.setName("jLabel12"); // NOI18N
        panelisi2.add(jLabel12);
        jLabel12.setBounds(0, 125, 80, 23);

        KdPpkRujukan.setEditable(false);
        KdPpkRujukan.setBackground(new java.awt.Color(245, 250, 240));
        KdPpkRujukan.setForeground(new java.awt.Color(0, 0, 0));
        KdPpkRujukan.setHighlighter(null);
        KdPpkRujukan.setName("KdPpkRujukan"); // NOI18N
        panelisi2.add(KdPpkRujukan);
        KdPpkRujukan.setBounds(85, 125, 75, 23);

        NmPpkRujukan.setEditable(false);
        NmPpkRujukan.setBackground(new java.awt.Color(245, 250, 240));
        NmPpkRujukan.setForeground(new java.awt.Color(0, 0, 0));
        NmPpkRujukan.setHighlighter(null);
        NmPpkRujukan.setName("NmPpkRujukan"); // NOI18N
        panelisi2.add(NmPpkRujukan);
        NmPpkRujukan.setBounds(163, 125, 180, 23);

        btnPPKRujukan.setForeground(new java.awt.Color(0, 0, 0));
        btnPPKRujukan.setIcon(new javax.swing.ImageIcon(getClass().getResource("/picture/190.png"))); // NOI18N
        btnPPKRujukan.setMnemonic('X');
        btnPPKRujukan.setToolTipText("Alt+X");
        btnPPKRujukan.setName("btnPPKRujukan"); // NOI18N
        btnPPKRujukan.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnPPKRujukanActionPerformed(evt);
            }
        });
        btnPPKRujukan.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                btnPPKRujukanKeyPressed(evt);
            }
        });
        panelisi2.add(btnPPKRujukan);
        btnPPKRujukan.setBounds(347, 125, 28, 23);

        jLabel13.setForeground(new java.awt.Color(0, 0, 0));
        jLabel13.setText("Diagnosa Awal :");
        jLabel13.setName("jLabel13"); // NOI18N
        panelisi2.add(jLabel13);
        jLabel13.setBounds(0, 153, 80, 23);

        KdPenyakit.setEditable(false);
        KdPenyakit.setBackground(new java.awt.Color(245, 250, 240));
        KdPenyakit.setForeground(new java.awt.Color(0, 0, 0));
        KdPenyakit.setHighlighter(null);
        KdPenyakit.setName("KdPenyakit"); // NOI18N
        panelisi2.add(KdPenyakit);
        KdPenyakit.setBounds(85, 153, 75, 23);

        NmPenyakit.setEditable(false);
        NmPenyakit.setBackground(new java.awt.Color(245, 250, 240));
        NmPenyakit.setForeground(new java.awt.Color(0, 0, 0));
        NmPenyakit.setHighlighter(null);
        NmPenyakit.setName("NmPenyakit"); // NOI18N
        panelisi2.add(NmPenyakit);
        NmPenyakit.setBounds(163, 153, 180, 23);

        btnDiagnosa.setForeground(new java.awt.Color(0, 0, 0));
        btnDiagnosa.setIcon(new javax.swing.ImageIcon(getClass().getResource("/picture/190.png"))); // NOI18N
        btnDiagnosa.setMnemonic('X');
        btnDiagnosa.setToolTipText("Alt+X");
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
        panelisi2.add(btnDiagnosa);
        btnDiagnosa.setBounds(347, 153, 28, 23);

        LabelPoli.setForeground(new java.awt.Color(0, 0, 0));
        LabelPoli.setText("Poli Tujuan :");
        LabelPoli.setName("LabelPoli"); // NOI18N
        panelisi2.add(LabelPoli);
        LabelPoli.setBounds(0, 180, 80, 23);

        KdPoli1.setEditable(false);
        KdPoli1.setBackground(new java.awt.Color(245, 250, 240));
        KdPoli1.setForeground(new java.awt.Color(0, 0, 0));
        KdPoli1.setHighlighter(null);
        KdPoli1.setName("KdPoli1"); // NOI18N
        panelisi2.add(KdPoli1);
        KdPoli1.setBounds(85, 180, 75, 23);

        NmPoli1.setEditable(false);
        NmPoli1.setBackground(new java.awt.Color(245, 250, 240));
        NmPoli1.setForeground(new java.awt.Color(0, 0, 0));
        NmPoli1.setHighlighter(null);
        NmPoli1.setName("NmPoli1"); // NOI18N
        panelisi2.add(NmPoli1);
        NmPoli1.setBounds(163, 180, 180, 23);

        btnPoli.setForeground(new java.awt.Color(0, 0, 0));
        btnPoli.setIcon(new javax.swing.ImageIcon(getClass().getResource("/picture/190.png"))); // NOI18N
        btnPoli.setMnemonic('X');
        btnPoli.setToolTipText("Alt+X");
        btnPoli.setName("btnPoli"); // NOI18N
        btnPoli.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnPoliActionPerformed(evt);
            }
        });
        btnPoli.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                btnPoliKeyPressed(evt);
            }
        });
        panelisi2.add(btnPoli);
        btnPoli.setBounds(347, 180, 28, 23);

        LabelKatarak.setForeground(new java.awt.Color(0, 0, 0));
        LabelKatarak.setText("Kasus Katarak :");
        LabelKatarak.setName("LabelKatarak"); // NOI18N
        panelisi2.add(LabelKatarak);
        LabelKatarak.setBounds(525, 67, 85, 23);

        KasusKatarak.setForeground(new java.awt.Color(0, 0, 0));
        KasusKatarak.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "0. Tidak", "1. Ya" }));
        KasusKatarak.setName("KasusKatarak"); // NOI18N
        KasusKatarak.setOpaque(false);
        KasusKatarak.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                KasusKatarakMouseClicked(evt);
            }
        });
        KasusKatarak.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                KasusKatarakKeyPressed(evt);
            }
        });
        panelisi2.add(KasusKatarak);
        KasusKatarak.setBounds(615, 67, 70, 23);

        jLabel15.setForeground(new java.awt.Color(0, 0, 0));
        jLabel15.setText("Jns.Pelayanan :");
        jLabel15.setName("jLabel15"); // NOI18N
        panelisi2.add(jLabel15);
        jLabel15.setBounds(380, 153, 85, 23);

        jLabel16.setForeground(new java.awt.Color(0, 0, 0));
        jLabel16.setText("Eksekutif :");
        jLabel16.setName("jLabel16"); // NOI18N
        panelisi2.add(jLabel16);
        jLabel16.setBounds(380, 180, 85, 23);

        JenisPelayanan.setForeground(new java.awt.Color(0, 0, 0));
        JenisPelayanan.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "1. Ranap", "2. Ralan" }));
        JenisPelayanan.setSelectedIndex(1);
        JenisPelayanan.setEnabled(false);
        JenisPelayanan.setName("JenisPelayanan"); // NOI18N
        JenisPelayanan.setOpaque(false);
        JenisPelayanan.addItemListener(new java.awt.event.ItemListener() {
            public void itemStateChanged(java.awt.event.ItemEvent evt) {
                JenisPelayananItemStateChanged(evt);
            }
        });
        JenisPelayanan.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                JenisPelayananMouseClicked(evt);
            }
        });
        JenisPelayanan.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                JenisPelayananKeyPressed(evt);
            }
        });
        panelisi2.add(JenisPelayanan);
        JenisPelayanan.setBounds(470, 153, 110, 23);

        Eksekutif.setForeground(new java.awt.Color(0, 0, 0));
        Eksekutif.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "0. Tidak", "1.Ya" }));
        Eksekutif.setName("Eksekutif"); // NOI18N
        Eksekutif.setOpaque(false);
        Eksekutif.addItemListener(new java.awt.event.ItemListener() {
            public void itemStateChanged(java.awt.event.ItemEvent evt) {
                EksekutifItemStateChanged(evt);
            }
        });
        Eksekutif.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                EksekutifMouseClicked(evt);
            }
        });
        Eksekutif.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                EksekutifKeyPressed(evt);
            }
        });
        panelisi2.add(Eksekutif);
        Eksekutif.setBounds(470, 180, 110, 23);

        LabelKelas.setForeground(new java.awt.Color(0, 0, 0));
        LabelKelas.setText("Kelas :");
        LabelKelas.setName("LabelKelas"); // NOI18N
        panelisi2.add(LabelKelas);
        LabelKelas.setBounds(585, 153, 40, 23);

        LabelKelas1.setForeground(new java.awt.Color(0, 0, 0));
        LabelKelas1.setText("COB :");
        LabelKelas1.setName("LabelKelas1"); // NOI18N
        panelisi2.add(LabelKelas1);
        LabelKelas1.setBounds(585, 180, 40, 23);

        Kelas.setForeground(new java.awt.Color(0, 0, 0));
        Kelas.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "1. Kelas 1", "2. Kelas 2", "3. Kelas 3" }));
        Kelas.setName("Kelas"); // NOI18N
        Kelas.setOpaque(false);
        Kelas.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                KelasMouseClicked(evt);
            }
        });
        Kelas.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                KelasKeyPressed(evt);
            }
        });
        panelisi2.add(Kelas);
        Kelas.setBounds(630, 153, 100, 23);

        COB.setForeground(new java.awt.Color(0, 0, 0));
        COB.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "0. Tidak ", "1.Ya" }));
        COB.setName("COB"); // NOI18N
        COB.setOpaque(false);
        COB.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                COBMouseClicked(evt);
            }
        });
        COB.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                COBKeyPressed(evt);
            }
        });
        panelisi2.add(COB);
        COB.setBounds(630, 180, 100, 23);

        Scroll3.setName("Scroll3"); // NOI18N
        Scroll3.setOpaque(true);

        Catatan.setColumns(20);
        Catatan.setRows(5);
        Catatan.setName("Catatan"); // NOI18N
        Catatan.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                CatatanKeyPressed(evt);
            }
        });
        Scroll3.setViewportView(Catatan);

        panelisi2.add(Scroll3);
        Scroll3.setBounds(85, 210, 645, 50);

        jLabel17.setForeground(new java.awt.Color(0, 0, 0));
        jLabel17.setText("Catatan :");
        jLabel17.setName("jLabel17"); // NOI18N
        panelisi2.add(jLabel17);
        jLabel17.setBounds(0, 210, 80, 23);

        jLabel43.setForeground(new java.awt.Color(0, 0, 0));
        jLabel43.setText("PPK Pelayanan :");
        jLabel43.setName("jLabel43"); // NOI18N
        panelisi2.add(jLabel43);
        jLabel43.setBounds(487, 8, 80, 23);

        KdPPK.setEditable(false);
        KdPPK.setBackground(new java.awt.Color(245, 250, 240));
        KdPPK.setForeground(new java.awt.Color(0, 0, 0));
        KdPPK.setHighlighter(null);
        KdPPK.setName("KdPPK"); // NOI18N
        panelisi2.add(KdPPK);
        KdPPK.setBounds(570, 8, 75, 23);

        NmPPK.setEditable(false);
        NmPPK.setBackground(new java.awt.Color(245, 250, 240));
        NmPPK.setForeground(new java.awt.Color(0, 0, 0));
        NmPPK.setHighlighter(null);
        NmPPK.setName("NmPPK"); // NOI18N
        panelisi2.add(NmPPK);
        NmPPK.setBounds(648, 8, 180, 23);

        jLabel31.setForeground(new java.awt.Color(0, 0, 0));
        jLabel31.setText("No.Telp. : ");
        jLabel31.setName("jLabel31"); // NOI18N
        jLabel31.setPreferredSize(new java.awt.Dimension(55, 23));
        panelisi2.add(jLabel31);
        jLabel31.setBounds(380, 125, 85, 23);

        NoTelp.setEditable(false);
        NoTelp.setForeground(new java.awt.Color(0, 0, 0));
        NoTelp.setMaxLenth(13);
        NoTelp.setName("NoTelp"); // NOI18N
        NoTelp.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                NoTelpKeyPressed(evt);
            }
        });
        panelisi2.add(NoTelp);
        NoTelp.setBounds(470, 125, 150, 23);

        label_cetak.setForeground(new java.awt.Color(0, 0, 0));
        label_cetak.setText("Status Cetak SEP : ");
        label_cetak.setName("label_cetak"); // NOI18N
        panelisi2.add(label_cetak);
        label_cetak.setBounds(730, 235, 100, 23);

        statusSEP.setEditable(false);
        statusSEP.setBackground(new java.awt.Color(245, 250, 240));
        statusSEP.setForeground(new java.awt.Color(255, 0, 0));
        statusSEP.setFont(new java.awt.Font("Tahoma", 1, 11)); // NOI18N
        statusSEP.setHighlighter(null);
        statusSEP.setName("statusSEP"); // NOI18N
        panelisi2.add(statusSEP);
        statusSEP.setBounds(833, 235, 80, 23);

        internalFrame3.add(panelisi2, java.awt.BorderLayout.CENTER);

        FormInput.addTab(".: Kelengkapan SEP BPJS", internalFrame3);

        internalFrame7.setName("internalFrame7"); // NOI18N
        internalFrame7.setLayout(new java.awt.BorderLayout());

        panelisi4.setName("panelisi4"); // NOI18N
        panelisi4.setLayout(null);

        jLabel36.setForeground(new java.awt.Color(0, 0, 0));
        jLabel36.setText("Laka Lantas :");
        jLabel36.setName("jLabel36"); // NOI18N
        panelisi4.add(jLabel36);
        jLabel36.setBounds(0, 8, 90, 23);

        LakaLantas.setForeground(new java.awt.Color(0, 0, 0));
        LakaLantas.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "0. Tidak", "1. Ya" }));
        LakaLantas.setName("LakaLantas"); // NOI18N
        LakaLantas.setOpaque(false);
        LakaLantas.addItemListener(new java.awt.event.ItemListener() {
            public void itemStateChanged(java.awt.event.ItemEvent evt) {
                LakaLantasItemStateChanged(evt);
            }
        });
        LakaLantas.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                LakaLantasMouseClicked(evt);
            }
        });
        LakaLantas.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                LakaLantasKeyPressed(evt);
            }
        });
        panelisi4.add(LakaLantas);
        LakaLantas.setBounds(95, 8, 70, 23);

        LabTglkll.setForeground(new java.awt.Color(0, 0, 0));
        LabTglkll.setText("Tgl. Kejadian :");
        LabTglkll.setName("LabTglkll"); // NOI18N
        panelisi4.add(LabTglkll);
        LabTglkll.setBounds(170, 8, 70, 23);

        TanggalKejadian.setEditable(false);
        TanggalKejadian.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "09-04-2020" }));
        TanggalKejadian.setDisplayFormat("dd-MM-yyyy");
        TanggalKejadian.setName("TanggalKejadian"); // NOI18N
        TanggalKejadian.setOpaque(false);
        TanggalKejadian.setPreferredSize(new java.awt.Dimension(95, 23));
        TanggalKejadian.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                TanggalKejadianMouseClicked(evt);
            }
        });
        TanggalKejadian.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                TanggalKejadianKeyPressed(evt);
            }
        });
        panelisi4.add(TanggalKejadian);
        TanggalKejadian.setBounds(245, 8, 90, 23);

        LabjaminKll.setForeground(new java.awt.Color(0, 0, 0));
        LabjaminKll.setText("Penjamin Laka :");
        LabjaminKll.setName("LabjaminKll"); // NOI18N
        panelisi4.add(LabjaminKll);
        LabjaminKll.setBounds(0, 35, 90, 23);

        ChkJasaRaharja.setForeground(new java.awt.Color(0, 0, 0));
        ChkJasaRaharja.setText("Jasa raharja PT");
        ChkJasaRaharja.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        ChkJasaRaharja.setName("ChkJasaRaharja"); // NOI18N
        ChkJasaRaharja.setOpaque(false);
        ChkJasaRaharja.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                ChkJasaRaharjaActionPerformed(evt);
            }
        });
        panelisi4.add(ChkJasaRaharja);
        ChkJasaRaharja.setBounds(95, 35, 100, 23);

        ChkTaspen.setForeground(new java.awt.Color(0, 0, 0));
        ChkTaspen.setText("TASPEN PT");
        ChkTaspen.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        ChkTaspen.setName("ChkTaspen"); // NOI18N
        ChkTaspen.setOpaque(false);
        ChkTaspen.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                ChkTaspenActionPerformed(evt);
            }
        });
        panelisi4.add(ChkTaspen);
        ChkTaspen.setBounds(95, 62, 80, 23);

        ChkBPJSTenaga.setForeground(new java.awt.Color(0, 0, 0));
        ChkBPJSTenaga.setText("BPJS Ketenagakerjaan");
        ChkBPJSTenaga.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        ChkBPJSTenaga.setName("ChkBPJSTenaga"); // NOI18N
        ChkBPJSTenaga.setOpaque(false);
        ChkBPJSTenaga.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                ChkBPJSTenagaActionPerformed(evt);
            }
        });
        panelisi4.add(ChkBPJSTenaga);
        ChkBPJSTenaga.setBounds(200, 35, 140, 23);

        ChkAsa.setForeground(new java.awt.Color(0, 0, 0));
        ChkAsa.setText("ASABRI PT");
        ChkAsa.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        ChkAsa.setName("ChkAsa"); // NOI18N
        ChkAsa.setOpaque(false);
        ChkAsa.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                ChkAsaActionPerformed(evt);
            }
        });
        panelisi4.add(ChkAsa);
        ChkAsa.setBounds(200, 62, 80, 23);

        LabKetkll.setForeground(new java.awt.Color(0, 0, 0));
        LabKetkll.setText("Keterangan :");
        LabKetkll.setName("LabKetkll"); // NOI18N
        panelisi4.add(LabKetkll);
        LabKetkll.setBounds(0, 90, 90, 23);

        Ket.setForeground(new java.awt.Color(0, 0, 0));
        Ket.setHighlighter(null);
        Ket.setName("Ket"); // NOI18N
        Ket.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                KetKeyPressed(evt);
            }
        });
        panelisi4.add(Ket);
        Ket.setBounds(95, 90, 270, 23);

        LabProv.setForeground(new java.awt.Color(0, 0, 0));
        LabProv.setText("Provinsi :");
        LabProv.setName("LabProv"); // NOI18N
        panelisi4.add(LabProv);
        LabProv.setBounds(0, 118, 90, 23);

        KdProv.setEditable(false);
        KdProv.setBackground(new java.awt.Color(245, 250, 240));
        KdProv.setForeground(new java.awt.Color(0, 0, 0));
        KdProv.setHighlighter(null);
        KdProv.setName("KdProv"); // NOI18N
        panelisi4.add(KdProv);
        KdProv.setBounds(95, 118, 60, 23);

        NmProv.setEditable(false);
        NmProv.setBackground(new java.awt.Color(245, 250, 240));
        NmProv.setForeground(new java.awt.Color(0, 0, 0));
        NmProv.setHighlighter(null);
        NmProv.setName("NmProv"); // NOI18N
        panelisi4.add(NmProv);
        NmProv.setBounds(158, 118, 170, 23);

        btnProv.setForeground(new java.awt.Color(0, 0, 0));
        btnProv.setIcon(new javax.swing.ImageIcon(getClass().getResource("/picture/190.png"))); // NOI18N
        btnProv.setMnemonic('X');
        btnProv.setToolTipText("Alt+X");
        btnProv.setName("btnProv"); // NOI18N
        btnProv.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnProvActionPerformed(evt);
            }
        });
        btnProv.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                btnProvKeyPressed(evt);
            }
        });
        panelisi4.add(btnProv);
        btnProv.setBounds(330, 118, 28, 23);

        LabKab.setForeground(new java.awt.Color(0, 0, 0));
        LabKab.setText("Kabupaten :");
        LabKab.setName("LabKab"); // NOI18N
        panelisi4.add(LabKab);
        LabKab.setBounds(0, 147, 90, 23);

        KdKab.setEditable(false);
        KdKab.setBackground(new java.awt.Color(245, 250, 240));
        KdKab.setForeground(new java.awt.Color(0, 0, 0));
        KdKab.setHighlighter(null);
        KdKab.setName("KdKab"); // NOI18N
        panelisi4.add(KdKab);
        KdKab.setBounds(95, 147, 60, 23);

        NmKab.setEditable(false);
        NmKab.setBackground(new java.awt.Color(245, 250, 240));
        NmKab.setForeground(new java.awt.Color(0, 0, 0));
        NmKab.setHighlighter(null);
        NmKab.setName("NmKab"); // NOI18N
        panelisi4.add(NmKab);
        NmKab.setBounds(158, 147, 170, 23);

        btnKab.setForeground(new java.awt.Color(0, 0, 0));
        btnKab.setIcon(new javax.swing.ImageIcon(getClass().getResource("/picture/190.png"))); // NOI18N
        btnKab.setMnemonic('X');
        btnKab.setToolTipText("Alt+X");
        btnKab.setName("btnKab"); // NOI18N
        btnKab.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnKabActionPerformed(evt);
            }
        });
        btnKab.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                btnKabKeyPressed(evt);
            }
        });
        panelisi4.add(btnKab);
        btnKab.setBounds(330, 147, 28, 23);

        LabKec.setForeground(new java.awt.Color(0, 0, 0));
        LabKec.setText("Kecamatan :");
        LabKec.setName("LabKec"); // NOI18N
        panelisi4.add(LabKec);
        LabKec.setBounds(0, 175, 90, 23);

        KdKec.setEditable(false);
        KdKec.setBackground(new java.awt.Color(245, 250, 240));
        KdKec.setForeground(new java.awt.Color(0, 0, 0));
        KdKec.setHighlighter(null);
        KdKec.setName("KdKec"); // NOI18N
        panelisi4.add(KdKec);
        KdKec.setBounds(95, 175, 60, 23);

        NmKec.setEditable(false);
        NmKec.setBackground(new java.awt.Color(245, 250, 240));
        NmKec.setForeground(new java.awt.Color(0, 0, 0));
        NmKec.setHighlighter(null);
        NmKec.setName("NmKec"); // NOI18N
        panelisi4.add(NmKec);
        NmKec.setBounds(158, 175, 170, 23);

        btnKec.setForeground(new java.awt.Color(0, 0, 0));
        btnKec.setIcon(new javax.swing.ImageIcon(getClass().getResource("/picture/190.png"))); // NOI18N
        btnKec.setMnemonic('X');
        btnKec.setToolTipText("Alt+X");
        btnKec.setName("btnKec"); // NOI18N
        btnKec.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnKecActionPerformed(evt);
            }
        });
        btnKec.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                btnKecKeyPressed(evt);
            }
        });
        panelisi4.add(btnKec);
        btnKec.setBounds(330, 175, 28, 23);

        LabSup.setForeground(new java.awt.Color(0, 0, 0));
        LabSup.setText("Suplesi :");
        LabSup.setName("LabSup"); // NOI18N
        panelisi4.add(LabSup);
        LabSup.setBounds(0, 205, 90, 23);

        suplesi.setForeground(new java.awt.Color(0, 0, 0));
        suplesi.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "0. Tidak", "1. Ya" }));
        suplesi.setName("suplesi"); // NOI18N
        suplesi.setOpaque(false);
        suplesi.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                suplesiMouseClicked(evt);
            }
        });
        suplesi.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                suplesiKeyPressed(evt);
            }
        });
        panelisi4.add(suplesi);
        suplesi.setBounds(95, 205, 70, 23);

        LabNoSup.setForeground(new java.awt.Color(0, 0, 0));
        LabNoSup.setText("No.SEP Suplesi :");
        LabNoSup.setName("LabNoSup"); // NOI18N
        panelisi4.add(LabNoSup);
        LabNoSup.setBounds(0, 235, 90, 23);

        NoSEPSuplesi.setForeground(new java.awt.Color(0, 0, 0));
        NoSEPSuplesi.setHighlighter(null);
        NoSEPSuplesi.setName("NoSEPSuplesi"); // NOI18N
        NoSEPSuplesi.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                NoSEPSuplesiKeyPressed(evt);
            }
        });
        panelisi4.add(NoSEPSuplesi);
        NoSEPSuplesi.setBounds(95, 235, 270, 23);

        internalFrame7.add(panelisi4, java.awt.BorderLayout.CENTER);

        FormInput.addTab(".: Laka Lantas BPJS", internalFrame7);

        PanelInput.add(FormInput, java.awt.BorderLayout.CENTER);

        internalFrame1.add(PanelInput, java.awt.BorderLayout.PAGE_START);

        getContentPane().add(internalFrame1, java.awt.BorderLayout.CENTER);

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void TNoRMKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_TNoRMKeyPressed
        if (evt.getKeyCode() == KeyEvent.VK_ENTER) {
            cekPasien();
        }
}//GEN-LAST:event_TNoRMKeyPressed

    private void BtnSimpanActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_BtnSimpanActionPerformed
        Sequel.cariIsi("select kd_booking from booking_registrasi where kd_poli='" + KdPoli.getText() + "' and "
                + "tanggal_periksa='" + Valid.SetTgl(TanggalPeriksa.getSelectedItem() + "") + "' and "
                + "no_rkm_medis=?", cekKDboking, TNoRM.getText());

        if (TNoRM.getText().trim().equals("") || TPasien.getText().trim().equals("")) {
            Valid.textKosong(TNoRM, "pasien");
        } else if (NmDokter.getText().trim().equals("") || KdDokter.getText().trim().equals("")) {
            Valid.textKosong(KdDokter, "Dokter Spesialis");
        } else if (NmPoli.getText().trim().equals("") || NmPoli.getText().trim().equals("")) {
            Valid.textKosong(KdPoli, "Poliklinik");
        } else if (NoReg.getText().trim().equals("")) {
            Valid.textKosong(NoReg, "No.Antri");
        } else if (kdpnj.getText().trim().equals("") || (nmpnj.getText().trim().equals(""))) {
            Valid.textKosong(kdpnj, "Jenis Pasien");
        } else if (kdboking.getText().trim().equals("")) {
            Valid.textKosong(kdboking, "Kode Booking");
        } else if (no_telp.getText().trim().equals("")) {
            Valid.textKosong(no_telp, "No. Telpon/HP Pemesan");
        } else if (verif_data.getSelectedItem().toString().equals(" ")) {
            Valid.textKosong(verif_data, "verifikasi data");
        } else if (kdsuku.getText().trim().equals("19") || kdsuku.getText().trim().equals("") || kdsuku.getText().trim().equals("-")) {
            Valid.textKosong(kdsuku, "Suku/Bangsa Pasien");
        } else if (kdbahasa.getText().trim().equals("12") || kdbahasa.getText().trim().equals("") || kdbahasa.getText().trim().equals("-")) {
            Valid.textKosong(kdbahasa, "Bahasa Pasien");
        } else if (!cekKDboking.getText().equals("")) {
            JOptionPane.showMessageDialog(null, "Pasien ini tidak boleh terdaftar dipoli & tgl. yang sama,..!!!");
            BtnPoli.requestFocus();
        } else if (Sequel.cariInteger("select count(pasien.no_rkm_medis) from pasien inner join reg_periksa inner join kamar_inap "
                + "on reg_periksa.no_rkm_medis=pasien.no_rkm_medis and reg_periksa.no_rawat=kamar_inap.no_rawat "
                + "where kamar_inap.stts_pulang='-' and pasien.no_rkm_medis=?", TNoRM.getText()) > 0) {
            JOptionPane.showMessageDialog(null, "Pasien sedang dalam masa perawatan di kamar inap..!!");
            ChkInput.setSelected(true);
            isForm();
            TNoRM.setText("");
            TPasien.setText("");
            jk.setText("");
            rmMati.setText("");
            TNoRM.requestFocus();

        } else if (jk.getText().equals("L") && (KdPoli.getText().equals("OBG"))) {
            JOptionPane.showMessageDialog(null, "Pasien dengan jns. kelamin laki-laki tidak boleh mendaftar ke poli " + NmPoli.getText() + ".");
            BtnPoli.requestFocus();
        } else if (!rmMati.getText().equals("")) {
            Sequel.cariIsi("select jam from pasien_mati where no_rkm_medis=? ", jamMati, TNoRM.getText());
            Sequel.cariIsi("select DATE_FORMAT(tanggal,'%d-%m-%Y') tgl from pasien_mati where no_rkm_medis=? ", tglMati, TNoRM.getText());

            JOptionPane.showMessageDialog(null, "Pasien tersebut sudah dinyatakan meninggal pada tanggal " + tglMati.getText() + " jam " + jamMati.getText() + ", data tidak dapat disimpan.");
            ChkInput.setSelected(true);
            isForm();
            emptTeks();
        } else {
            if (kdpnj.getText().equals("B01") || (kdpnj.getText().equals("A03"))) {

                date1 = LocalDate.parse(Valid.SetTgl(TanggalPeriksa.getSelectedItem() + ""));
                date2 = LocalDate.parse(Valid.SetTgl(TanggalRujuk.getSelectedItem() + ""));
                long days = ChronoUnit.DAYS.between(date2, date1);

                if (TglLahir.getText().trim().equals("")) {
                    Valid.textKosong(TglLahir, "data kelengkapan SEP BPJS");
                } else if (JenisPeserta.getText().trim().equals("")) {
                    Valid.textKosong(JenisPeserta, "Jenis Peserta");
                } else if (JK.getText().trim().equals("")) {
                    Valid.textKosong(JK, "Jenis Kelamin");
                } else if (KdPPK.getText().trim().equals("") || (NmPPK.getText().trim().equals(""))) {
                    Valid.textKosong(KdPPK, "Kode PPK");
                } else if (NoKartu.getText().trim().equals("")) {
                    Valid.textKosong(NoKartu, "No. Kartu");
                } else if (Status.getText().trim().equals("")) {
                    Valid.textKosong(Status, "Status Kepesertaan");
                } else if (NoRujukan.getText().trim().equals("")) {
                    Valid.textKosong(NoRujukan, "No. Rujukan");
                } else if (noSKDP.getText().trim().equals("")) {
                    Valid.textKosong(noSKDP, "No. SKDP");
                } else if (Kddpjp.getText().trim().equals("") || (NmDPJP.getText().trim().equals(""))) {
                    Valid.textKosong(Kddpjp, "DPJP");
                } else if (KdPpkRujukan.getText().trim().equals("") || (NmPpkRujukan.getText().trim().equals(""))) {
                    Valid.textKosong(KdPpkRujukan, "PPK Rujukan");
                } else if (KdPenyakit.getText().trim().equals("") || (NmPenyakit.getText().trim().equals(""))) {
                    Valid.textKosong(KdPenyakit, "Diagnosa Pasien");
                } else if (KdPoli1.getText().trim().equals("") || (NmPoli1.getText().trim().equals(""))) {
                    Valid.textKosong(KdPoli1, "Poliklinik");
                } else if (days > 90){
                    JOptionPane.showMessageDialog(null, "Surat Rujukan ini Masa Berlaku Habis,Maksimal 3(tiga) bulan dari tanggal rujukan.Silahkan ke Faskes Perujuk Untuk Perbarui Rujukan");                    
                } else {
                    autoNomorBooking();
                    simpanBooking();
                    simpanKelengkapanSEP();
                    Sequel.mengedit("pasien", "no_rkm_medis='" + TNoRM.getText() + "'", "suku_bangsa='" + kdsuku.getText() + "', bahasa_pasien='" + kdbahasa.getText() + "' ");
                    emptTeks();
                    tampil();
                }
            } else if (kdpnj.getText().equals("U01")) {
                autoNomorBooking();
                simpanBooking();
                Sequel.mengedit("pasien", "no_rkm_medis='" + TNoRM.getText() + "'", "suku_bangsa='" + kdsuku.getText() + "', bahasa_pasien='" + kdbahasa.getText() + "' ");
                emptTeks();
                tampil();
            } else {
                JOptionPane.showMessageDialog(null, "Selain pasien Umum atau BPJS silakan lsg. ke loket pendaftaran, utk. saat ini belum ada kebijakan dari menejemen...");
                btnPenjab.requestFocus();
            }
        }
}//GEN-LAST:event_BtnSimpanActionPerformed

    private void BtnSimpanKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_BtnSimpanKeyPressed
        if (evt.getKeyCode() == KeyEvent.VK_SPACE) {
            BtnSimpanActionPerformed(null);
        } else {
            Valid.pindah(evt, NoReg, BtnBatal);
        }
}//GEN-LAST:event_BtnSimpanKeyPressed

    private void BtnBatalActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_BtnBatalActionPerformed
        emptTeks();
        ChkInput.setSelected(true);
        isForm();
}//GEN-LAST:event_BtnBatalActionPerformed

    private void BtnBatalKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_BtnBatalKeyPressed
        if (evt.getKeyCode() == KeyEvent.VK_SPACE) {
            emptTeks();
        } else {
            Valid.pindah(evt, BtnSimpan, BtnHapus);
        }
}//GEN-LAST:event_BtnBatalKeyPressed

    private void BtnHapusActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_BtnHapusActionPerformed
        if (TNoRM.getText().trim().equals("") || TPasien.getText().trim().equals("")) {
            JOptionPane.showMessageDialog(null, "Silakan pilih salah satu pasiennya dulu...");
            tbObat.requestFocus();
        } else if (kdboking.getText().trim().equals("")) {
            JOptionPane.showMessageDialog(null, "Silakan pilih salah satu pasiennya dulu...");
            tbObat.requestFocus();
        } else {
            Sequel.mengedit("booking_registrasi", "kd_booking='" + kdboking.getText() + "'", "status_booking='Batal'");
        }

        tampil();
        emptTeks();
}//GEN-LAST:event_BtnHapusActionPerformed

    private void BtnHapusKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_BtnHapusKeyPressed
        if (evt.getKeyCode() == KeyEvent.VK_SPACE) {
            BtnHapusActionPerformed(null);
        } else {
            Valid.pindah(evt, BtnBatal, BtnPrint);
        }
}//GEN-LAST:event_BtnHapusKeyPressed

    private void BtnKeluarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_BtnKeluarActionPerformed
        dispose();
}//GEN-LAST:event_BtnKeluarActionPerformed

    private void BtnKeluarKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_BtnKeluarKeyPressed
        if (evt.getKeyCode() == KeyEvent.VK_SPACE) {
            dispose();
        } else {
            Valid.pindah(evt, BtnPrint, TCari);
        }
}//GEN-LAST:event_BtnKeluarKeyPressed

    private void BtnPrintActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_BtnPrintActionPerformed
        this.setCursor(Cursor.getPredefinedCursor(Cursor.WAIT_CURSOR));
        if (tabMode.getRowCount() == 0) {
            JOptionPane.showMessageDialog(null, "Maaf, data sudah habis. Tidak ada data yang bisa anda print...!!!!");
            BtnBatal.requestFocus();
        } else if (tabMode.getRowCount() != 0) {
            Map<String, Object> param = new HashMap<>();
            param.put("namars", var.getnamars());
            param.put("alamatrs", var.getalamatrs());
            param.put("kotars", var.getkabupatenrs());
            param.put("propinsirs", var.getpropinsirs());
            param.put("kontakrs", var.getkontakrs());
            param.put("emailrs", var.getemailrs());
            param.put("logo", Sequel.cariGambar("select logo from setting"));
            if (R2.isSelected() == true) {
                status = " booking_registrasi.tanggal_booking between '" + Valid.SetTgl(DTPCari1.getSelectedItem() + "") + "' and '" + Valid.SetTgl(DTPCari2.getSelectedItem() + "") + "' ";
            } else if (R3.isSelected() == true) {
                status = " booking_registrasi.tanggal_periksa between '" + Valid.SetTgl(DTPCari3.getSelectedItem() + "") + "' and '" + Valid.SetTgl(DTPCari4.getSelectedItem() + "") + "' ";
            }
            Valid.MyReport("rptBookingRegistrasi.jrxml", "report", "::[ Laporan Daftar Booking Registrasi ]::",
                    "select booking_registrasi.tanggal_booking,booking_registrasi.no_rkm_medis, "
                    + "pasien.nm_pasien,booking_registrasi.tanggal_periksa,booking_registrasi.kd_dokter,"
                    + "dokter.nm_dokter,booking_registrasi.kd_poli,poliklinik.nm_poli,booking_registrasi.no_reg "
                    + "from booking_registrasi inner join pasien inner join dokter inner join poliklinik on "
                    + "booking_registrasi.no_rkm_medis=pasien.no_rkm_medis and "
                    + "booking_registrasi.kd_dokter=dokter.kd_dokter and booking_registrasi.kd_poli=poliklinik.kd_poli "
                    + "where " + status + " and booking_registrasi.no_rkm_medis like '%" + TCari.getText().trim() + "%' or "
                    + status + " and pasien.nm_pasien like '%" + TCari.getText().trim() + "%' or "
                    + status + " and poliklinik.nm_poli like '%" + TCari.getText().trim() + "%' or "
                    + status + " and dokter.nm_dokter like '%" + TCari.getText().trim() + "%' order by booking_registrasi.tanggal_booking,dokter.nm_dokter", param);
        }
        this.setCursor(Cursor.getDefaultCursor());
}//GEN-LAST:event_BtnPrintActionPerformed

    private void BtnPrintKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_BtnPrintKeyPressed
        if (evt.getKeyCode() == KeyEvent.VK_SPACE) {
            BtnPrintActionPerformed(null);
        } else {
            Valid.pindah(evt, BtnHapus, BtnKeluar);
        }
}//GEN-LAST:event_BtnPrintKeyPressed

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
        emptKelengkapanSEP();

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
        tampil();
        emptTeks();
        emptKelengkapanSEP();
}//GEN-LAST:event_BtnAllActionPerformed

    private void BtnAllKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_BtnAllKeyPressed
        if (evt.getKeyCode() == KeyEvent.VK_SPACE) {
            tampil();
            TCari.setText("");
        } else {
            Valid.pindah(evt, BtnCari, TPasien);
        }
}//GEN-LAST:event_BtnAllKeyPressed

    private void tbObatMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tbObatMouseClicked
        if (tabMode.getRowCount() != 0) {
            try {
                getData();
            } catch (java.lang.NullPointerException e) {
            }
        }
}//GEN-LAST:event_tbObatMouseClicked

    private void tbObatKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_tbObatKeyPressed
        if (tabMode.getRowCount() != 0) {
            if ((evt.getKeyCode() == KeyEvent.VK_ENTER) || (evt.getKeyCode() == KeyEvent.VK_UP) || (evt.getKeyCode() == KeyEvent.VK_DOWN)) {
                try {
                    getData();
                } catch (java.lang.NullPointerException e) {
                }
            }
        }
}//GEN-LAST:event_tbObatKeyPressed

private void BtnDokterActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_BtnDokterActionPerformed
    dokter.setPoli(NmPoli.getText());
    dokter.isCek();
    dokter.setHari(Valid.SetTgl(TanggalPeriksa.getSelectedItem() + ""));
    dokter.tampil();
    dokter.TCari.requestFocus();
    dokter.setSize(1046, 341);
    dokter.setLocationRelativeTo(internalFrame1);
    dokter.setVisible(true);
    dokter.emptTeks();
}//GEN-LAST:event_BtnDokterActionPerformed

private void BtnDokterKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_BtnDokterKeyPressed
    if (evt.getKeyCode() == KeyEvent.VK_SPACE) {
        BtnDokterActionPerformed(null);
    } else {
        Valid.pindah(evt, TanggalPeriksa, btnPenjab);
    }
}//GEN-LAST:event_BtnDokterKeyPressed

private void ChkInputActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_ChkInputActionPerformed
    isForm();
}//GEN-LAST:event_ChkInputActionPerformed

    private void DTPCari1KeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_DTPCari1KeyPressed

    }//GEN-LAST:event_DTPCari1KeyPressed

    private void DTPCari2KeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_DTPCari2KeyPressed
        R2.setSelected(true);
    }//GEN-LAST:event_DTPCari2KeyPressed

    private void DTPCari3KeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_DTPCari3KeyPressed
        // TODO add your handling code here:
    }//GEN-LAST:event_DTPCari3KeyPressed

    private void DTPCari4KeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_DTPCari4KeyPressed
        // TODO add your handling code here:
    }//GEN-LAST:event_DTPCari4KeyPressed

    private void formWindowOpened(java.awt.event.WindowEvent evt) {//GEN-FIRST:event_formWindowOpened
        tampil();
        autoNomorBooking();
        FormInput.setEnabledAt(1, false);
        FormInput.setEnabledAt(2, false);
    }//GEN-LAST:event_formWindowOpened

    private void DTPCari1ItemStateChanged(java.awt.event.ItemEvent evt) {//GEN-FIRST:event_DTPCari1ItemStateChanged
        R2.setSelected(true);
    }//GEN-LAST:event_DTPCari1ItemStateChanged

    private void DTPCari3ItemStateChanged(java.awt.event.ItemEvent evt) {//GEN-FIRST:event_DTPCari3ItemStateChanged
        R3.setSelected(true);
    }//GEN-LAST:event_DTPCari3ItemStateChanged

    private void DTPCari4ItemStateChanged(java.awt.event.ItemEvent evt) {//GEN-FIRST:event_DTPCari4ItemStateChanged
        R3.setSelected(true);
    }//GEN-LAST:event_DTPCari4ItemStateChanged

    private void TanggalPeriksaKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_TanggalPeriksaKeyPressed

    }//GEN-LAST:event_TanggalPeriksaKeyPressed

    private void TanggalPeriksaItemStateChanged(java.awt.event.ItemEvent evt) {//GEN-FIRST:event_TanggalPeriksaItemStateChanged
        try {
            isNomer();
        } catch (Exception e) {
        }
    }//GEN-LAST:event_TanggalPeriksaItemStateChanged

    private void BtnPoliKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_BtnPoliKeyPressed
        if (evt.getKeyCode() == KeyEvent.VK_SPACE) {
            BtnPoliActionPerformed(null);
        } else {
            Valid.pindah(evt, BtnPasien, BtnDokter);
        }
    }//GEN-LAST:event_BtnPoliKeyPressed

    private void BtnPoliActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_BtnPoliActionPerformed
        poli.isCek();
        poli.setSize(1074, 662);
        poli.setLocationRelativeTo(internalFrame1);
        poli.setVisible(true);
        poli.emptTeks();
    }//GEN-LAST:event_BtnPoliActionPerformed

    private void NoRegKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_NoRegKeyPressed
        Valid.pindah(evt, TanggalPeriksa, BtnSimpan);
    }//GEN-LAST:event_NoRegKeyPressed

    private void BtnPasienActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_BtnPasienActionPerformed
        pasien.emptTeks();
        pasien.isCek();
        pasien.setSize(internalFrame1.getWidth() - 40, internalFrame1.getHeight() - 40);
        pasien.setLocationRelativeTo(internalFrame1);
        pasien.setVisible(true);
        pasien.fokus();
    }//GEN-LAST:event_BtnPasienActionPerformed

    private void BtnPasienKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_BtnPasienKeyPressed
        if (evt.getKeyCode() == KeyEvent.VK_SPACE) {
            BtnPasienActionPerformed(null);
        } else {
            Valid.pindah(evt, TCari, BtnPoli);
        }
    }//GEN-LAST:event_BtnPasienKeyPressed

    private void FormInputMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_FormInputMouseClicked
        if (FormInput.getSelectedIndex() == 0) {
            BtnPasien.requestFocus();
        } else if (FormInput.getSelectedIndex() == 1) {
            cekBPJS();
            tampil();
        }
    }//GEN-LAST:event_FormInputMouseClicked

    private void kdpnjKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_kdpnjKeyPressed

    }//GEN-LAST:event_kdpnjKeyPressed

    private void btnPenjabActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnPenjabActionPerformed
        var.setform("DlgBookingRegistrasi");
        pasien.penjab.onCari();
        pasien.penjab.isCek();
        pasien.penjab.setSize(906, 729);
        pasien.penjab.setLocationRelativeTo(internalFrame1);
        pasien.penjab.setVisible(true);
        pasien.penjab.onCari();
    }//GEN-LAST:event_btnPenjabActionPerformed

    private void kdbokingKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_kdbokingKeyPressed
        // TODO add your handling code here:
    }//GEN-LAST:event_kdbokingKeyPressed

    private void TanggalBookingItemStateChanged(java.awt.event.ItemEvent evt) {//GEN-FIRST:event_TanggalBookingItemStateChanged
        // TODO add your handling code here:
    }//GEN-LAST:event_TanggalBookingItemStateChanged

    private void TanggalBookingKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_TanggalBookingKeyPressed
        // TODO add your handling code here:
    }//GEN-LAST:event_TanggalBookingKeyPressed

    private void TanggalPeriksaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_TanggalPeriksaActionPerformed

    }//GEN-LAST:event_TanggalPeriksaActionPerformed

    private void TanggalPeriksaMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_TanggalPeriksaMouseClicked
        KdDokter.setText("");
        NmDokter.setText("");
    }//GEN-LAST:event_TanggalPeriksaMouseClicked

    private void BtnGantiActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_BtnGantiActionPerformed
        if (TNoRM.getText().trim().equals("") || TPasien.getText().trim().equals("")) {
            Valid.textKosong(TNoRM, "Pasien");
        } else if (NmDokter.getText().trim().equals("") || KdDokter.getText().trim().equals("")) {
            Valid.textKosong(KdDokter, "Dokter Spesialis");
        } else if (NmPoli.getText().trim().equals("") || KdPoli.getText().trim().equals("")) {
            Valid.textKosong(KdPoli, "Poliklinik");
        } else if (kdpnj.getText().trim().equals("") || nmpnj.getText().trim().equals("")) {
            Valid.textKosong(kdpnj, "Jenis Pasien");
        } else if (no_telp.getText().trim().equals("")) {
            Valid.textKosong(no_telp, "No. Telpon/HP Pemesan");
        } else if (verif_data.getSelectedItem().toString().equals(" ")) {
            Valid.textKosong(verif_data, "verifikasi data");
        } else if (jk.getText().equals("L") && (KdPoli.getText().equals("OBG"))) {
            JOptionPane.showMessageDialog(null, "Pasien dengan jns. kelamin laki-laki tidak boleh mendaftar ke poli " + NmPoli.getText() + ".");
            BtnPoli.requestFocus();
        } else if (kdsuku.getText().trim().equals("19") || kdsuku.getText().trim().equals("") || kdsuku.getText().trim().equals("-")) {
            Valid.textKosong(kdsuku, "Suku/Bangsa Pasien");
        } else if (kdbahasa.getText().trim().equals("12") || kdbahasa.getText().trim().equals("") || kdbahasa.getText().trim().equals("-")) {
            Valid.textKosong(kdbahasa, "Bahasa Pasien");
        } else if (!rmMati.getText().equals("")) {
            Sequel.cariIsi("select jam from pasien_mati where no_rkm_medis=? ", jamMati, TNoRM.getText());
            Sequel.cariIsi("select DATE_FORMAT(tanggal,'%d-%m-%Y') tgl from pasien_mati where no_rkm_medis=? ", tglMati, TNoRM.getText());

            JOptionPane.showMessageDialog(null, "Pasien tersebut sudah dinyatakan meninggal pada tanggal " + tglMati.getText() + " jam " + jamMati.getText() + ", data tidak dapat disimpan.");
            ChkInput.setSelected(true);
            isForm();
            BtnBatal.requestFocus();
        } else {
            if (kdpnj.getText().equals("B01") || (kdpnj.getText().equals("A03"))) {
                if (TglLahir.getText().trim().equals("")) {
                    Valid.textKosong(TglLahir, "data kelengkapan SEP BPJS");
                } else if (JenisPeserta.getText().trim().equals("")) {
                    Valid.textKosong(JenisPeserta, "Jenis Peserta");
                } else if (JK.getText().trim().equals("")) {
                    Valid.textKosong(JK, "Jenis Kelamin");
                } else if (KdPPK.getText().trim().equals("") || (NmPPK.getText().trim().equals(""))) {
                    Valid.textKosong(KdPPK, "Kode PPK");
                } else if (NoKartu.getText().trim().equals("")) {
                    Valid.textKosong(NoKartu, "No. Kartu");
                } else if (Status.getText().trim().equals("")) {
                    Valid.textKosong(Status, "Status Kepesertaan");
                } else if (NoRujukan.getText().trim().equals("")) {
                    Valid.textKosong(NoRujukan, "No. Rujukan");
                } else if (noSKDP.getText().trim().equals("")) {
                    Valid.textKosong(noSKDP, "No. SKDP");
                } else if (Kddpjp.getText().trim().equals("") || (NmDPJP.getText().trim().equals(""))) {
                    Valid.textKosong(Kddpjp, "DPJP");
                } else if (KdPpkRujukan.getText().trim().equals("") || (NmPpkRujukan.getText().trim().equals(""))) {
                    Valid.textKosong(KdPpkRujukan, "PPK Rujukan");
                } else if (KdPenyakit.getText().trim().equals("") || (NmPenyakit.getText().trim().equals(""))) {
                    Valid.textKosong(KdPenyakit, "Diagnosa Pasien");
                } else if (KdPoli1.getText().trim().equals("") || (NmPoli1.getText().trim().equals(""))) {
                    Valid.textKosong(KdPoli1, "Poliklinik");
                } else {
                    gantiDataBooking();
                    Sequel.mengedit("pasien", "no_rkm_medis='" + TNoRM.getText() + "'", "suku_bangsa='" + kdsuku.getText() + "', bahasa_pasien='" + kdbahasa.getText() + "' ");

                    Sequel.cariIsi("select kd_booking from kelengkapan_booking_sep_bpjs where kd_booking='" + kdboking.getText() + "'", cekKelengkapanSEP);
                    if (cekKelengkapanSEP.getText().equals("")) {
                        simpanKelengkapanSEP();
                    } else if (!cekKelengkapanSEP.getText().equals("")) {
                        gantiDataSEP();
                    }
                    tampil();
                    emptTeks();
                }
            } else if (kdpnj.getText().equals("U01")) {
                gantiDataBooking();
                Sequel.mengedit("pasien", "no_rkm_medis='" + TNoRM.getText() + "'", "suku_bangsa='" + kdsuku.getText() + "', bahasa_pasien='" + kdbahasa.getText() + "' ");
                Sequel.meghapus("kelengkapan_booking_sep_bpjs", "kd_booking", kdboking.getText());
                tampil();
                emptTeks();
            } else {
                JOptionPane.showMessageDialog(null, "Selain pasien Umum atau BPJS silakan lsg. ke loket pendaftaran, utk. saat ini belum ada kebijakan dari menejemen...");
                btnPenjab.requestFocus();
            }
        }
    }//GEN-LAST:event_BtnGantiActionPerformed

    private void BtnGantiKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_BtnGantiKeyPressed
        // TODO add your handling code here:
    }//GEN-LAST:event_BtnGantiKeyPressed

    private void no_telpKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_no_telpKeyPressed
        // TODO add your handling code here:
    }//GEN-LAST:event_no_telpKeyPressed

    private void BtnJadwalActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_BtnJadwalActionPerformed
        this.setCursor(Cursor.getPredefinedCursor(Cursor.WAIT_CURSOR));
        InformasiJadwal jadwal = new InformasiJadwal(null, false);
        jadwal.setSize(internalFrame1.getWidth(), internalFrame1.getHeight());
        jadwal.setLocationRelativeTo(internalFrame1);
        jadwal.setVisible(true);
        jadwal.TCari.requestFocus();
        this.setCursor(Cursor.getDefaultCursor());
    }//GEN-LAST:event_BtnJadwalActionPerformed

    private void BtnJadwalKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_BtnJadwalKeyPressed
        // TODO add your handling code here:
    }//GEN-LAST:event_BtnJadwalKeyPressed

    private void btnPenjabKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_btnPenjabKeyPressed
        if (evt.getKeyCode() == KeyEvent.VK_SPACE) {
            btnPenjabActionPerformed(null);
        } else {
            Valid.pindah(evt, BtnDokter, verif_data);
        }
    }//GEN-LAST:event_btnPenjabKeyPressed

    private void NoRujukanKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_NoRujukanKeyPressed
        Valid.pindah(evt, TCari, TanggalRujuk);
    }//GEN-LAST:event_NoRujukanKeyPressed

    private void btnNoRujukanActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnNoRujukanActionPerformed
        if (TNoRM.getText().trim().equals("") || TPasien.getText().trim().equals("")) {
            Valid.textKosong(TNoRM, "Pasien");
        } else if (NoKartu.getText().trim().equals("")) {
            Valid.textKosong(NoKartu, "Nomor Kartu");
        } else {
            WindowCariNoRujuk.setLocationRelativeTo(internalFrame1);
            WindowCariNoRujuk.setVisible(true);
            tampilFaskes1();
        }
    }//GEN-LAST:event_btnNoRujukanActionPerformed

    private void btnNoRujukanKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_btnNoRujukanKeyPressed
        // TODO add your handling code here:
    }//GEN-LAST:event_btnNoRujukanKeyPressed

    private void TanggalRujukMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_TanggalRujukMouseClicked

    }//GEN-LAST:event_TanggalRujukMouseClicked

    private void TanggalRujukKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_TanggalRujukKeyPressed
        Valid.pindah(evt, NoRujukan, btnNoSKDP);
    }//GEN-LAST:event_TanggalRujukKeyPressed

    private void noSKDPKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_noSKDPKeyPressed
        // TODO add your handling code here:
    }//GEN-LAST:event_noSKDPKeyPressed

    private void btnNoSKDPActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnNoSKDPActionPerformed
        skdp.setNoRm(TNoRM.getText(), TPasien.getText());
        skdp.setSize(internalFrame1.getWidth() - 40, internalFrame1.getHeight() - 40);
        skdp.setLocationRelativeTo(internalFrame1);
        skdp.setVisible(true);
        skdp.fokusdata();
    }//GEN-LAST:event_btnNoSKDPActionPerformed

    private void btnNoSKDPKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_btnNoSKDPKeyPressed
        // TODO add your handling code here:
    }//GEN-LAST:event_btnNoSKDPKeyPressed

    private void AsalRujukanMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_AsalRujukanMouseClicked
        AsalRujukan.setEditable(false);
    }//GEN-LAST:event_AsalRujukanMouseClicked

    private void AsalRujukanActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_AsalRujukanActionPerformed
        if (AsalRujukan.getSelectedIndex() == 1) {
            KdPpkRujukan.setText(KdPPK.getText());
            rujukanSEP.setText(NmPPK.getText());
            Sequel.cariIsi("select nama_rujukan from master_nama_rujukan where kode_faskes_bpjs=? ", NmPpkRujukan, KdPpkRujukan.getText());
//            NmPpkRujukan.setText(NmPPK.getText());
//            NmPpkRujukan.setText("");
        } else if (AsalRujukan.getSelectedIndex() == 0) {
            KdPpkRujukan.setText("");
            rujukanSEP.setText("- belum terisi -");
            NmPpkRujukan.setText("");
        }
    }//GEN-LAST:event_AsalRujukanActionPerformed

    private void AsalRujukanKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_AsalRujukanKeyPressed
        Valid.pindah(evt, btnNoSKDP, btnPPKRujukan);
    }//GEN-LAST:event_AsalRujukanKeyPressed

    private void btnDPJPActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnDPJPActionPerformed
        pilihan = 1;
        dpjp.setSize(internalFrame1.getWidth() - 40, internalFrame1.getHeight() - 40);
        dpjp.setLocationRelativeTo(internalFrame1);
        dpjp.setVisible(true);
        dpjp.fokus();
        dpjp.poliklinik(KdPoli.getText(), NmPoli.getText());
    }//GEN-LAST:event_btnDPJPActionPerformed

    private void btnDPJPKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_btnDPJPKeyPressed
        // TODO add your handling code here:
    }//GEN-LAST:event_btnDPJPKeyPressed

    private void btnPPKRujukanActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnPPKRujukanActionPerformed
        var.setform("DlgBookingRegistrasi");
        rujukmasuk.tampil2();
        rujukmasuk.WindowPerujuk.setSize(900, 573);
        rujukmasuk.WindowPerujuk.setLocationRelativeTo(internalFrame1);
        rujukmasuk.WindowPerujuk.setVisible(true);
        rujukmasuk.onCariPerujuk();

        //        pilihan=1;
        //        faskes.setSize(internalFrame1.getWidth()-40,internalFrame1.getHeight()-40);
        //        faskes.setLocationRelativeTo(internalFrame1);
        //        faskes.setVisible(true);
        //        faskes.fokus();
    }//GEN-LAST:event_btnPPKRujukanActionPerformed

    private void btnPPKRujukanKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_btnPPKRujukanKeyPressed
        Valid.pindah(evt, AsalRujukan, btnDiagnosa);
    }//GEN-LAST:event_btnPPKRujukanKeyPressed

    private void btnDiagnosaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnDiagnosaActionPerformed
        pilihan = 1;
        penyakit.setSize(841, 417);
        penyakit.setLocationRelativeTo(internalFrame1);
        penyakit.setVisible(true);
        penyakit.fokus();
    }//GEN-LAST:event_btnDiagnosaActionPerformed

    private void btnDiagnosaKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_btnDiagnosaKeyPressed
        Valid.pindah(evt, btnPPKRujukan, btnPoli);
    }//GEN-LAST:event_btnDiagnosaKeyPressed

    private void btnPoliActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnPoliActionPerformed
        pilihan = 1;
        poliBPJS.setSize(842, 390);
        poliBPJS.setLocationRelativeTo(internalFrame1);
        poliBPJS.setVisible(true);
        poliBPJS.fokus();
    }//GEN-LAST:event_btnPoliActionPerformed

    private void btnPoliKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_btnPoliKeyPressed
//        Valid.pindah(evt,btnDiagnosa,Catatan);
    }//GEN-LAST:event_btnPoliKeyPressed

    private void KasusKatarakMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_KasusKatarakMouseClicked
        KasusKatarak.setEditable(false);
    }//GEN-LAST:event_KasusKatarakMouseClicked

    private void KasusKatarakKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_KasusKatarakKeyPressed
        // TODO add your handling code here:
    }//GEN-LAST:event_KasusKatarakKeyPressed

    private void JenisPelayananItemStateChanged(java.awt.event.ItemEvent evt) {//GEN-FIRST:event_JenisPelayananItemStateChanged
        cekLAYAN();
        cekPPKRUJUKAN();
    }//GEN-LAST:event_JenisPelayananItemStateChanged

    private void JenisPelayananMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_JenisPelayananMouseClicked
        JenisPelayanan.setEditable(false);
    }//GEN-LAST:event_JenisPelayananMouseClicked

    private void JenisPelayananKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_JenisPelayananKeyPressed
//        Valid.pindah(evt,LakaLantas,Kelas);
    }//GEN-LAST:event_JenisPelayananKeyPressed

    private void EksekutifItemStateChanged(java.awt.event.ItemEvent evt) {//GEN-FIRST:event_EksekutifItemStateChanged
        // TODO add your handling code here:
    }//GEN-LAST:event_EksekutifItemStateChanged

    private void EksekutifMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_EksekutifMouseClicked
        Eksekutif.setEditable(false);
    }//GEN-LAST:event_EksekutifMouseClicked

    private void EksekutifKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_EksekutifKeyPressed
        Valid.pindah(evt, Kelas, COB);
    }//GEN-LAST:event_EksekutifKeyPressed

    private void KelasMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_KelasMouseClicked
        Kelas.setEditable(false);
    }//GEN-LAST:event_KelasMouseClicked

    private void KelasKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_KelasKeyPressed
        Valid.pindah(evt, JenisPelayanan, Eksekutif);
    }//GEN-LAST:event_KelasKeyPressed

    private void COBMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_COBMouseClicked
        COB.setEditable(false);
    }//GEN-LAST:event_COBMouseClicked

    private void COBKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_COBKeyPressed
        Valid.pindah(evt, Eksekutif, Catatan);
    }//GEN-LAST:event_COBKeyPressed

    private void CatatanKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_CatatanKeyPressed
//        Valid.pindah(evt,COB,LokasiLaka);
    }//GEN-LAST:event_CatatanKeyPressed

    private void tbFaskes1MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tbFaskes1MouseClicked
        if (tabMode1.getRowCount() != 0) {
            try {
                getDataFK1();
            } catch (java.lang.NullPointerException e) {
            }
        }
    }//GEN-LAST:event_tbFaskes1MouseClicked

    private void tbFaskes1KeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_tbFaskes1KeyPressed
        if (tabMode1.getRowCount() != 0) {
            if ((evt.getKeyCode() == KeyEvent.VK_ENTER) || (evt.getKeyCode() == KeyEvent.VK_UP) || (evt.getKeyCode() == KeyEvent.VK_DOWN)) {
                try {
                    getDataFK1();
                } catch (java.lang.NullPointerException e) {
                }
            }
            WindowCariNoRujuk.dispose();
        }
    }//GEN-LAST:event_tbFaskes1KeyPressed

    private void tbFaskes2MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tbFaskes2MouseClicked
        if (tabMode2.getRowCount() != 0) {
            try {
                getDataFK2();
            } catch (java.lang.NullPointerException e) {
            }
        }
    }//GEN-LAST:event_tbFaskes2MouseClicked

    private void tbFaskes2KeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_tbFaskes2KeyPressed
        if (tabMode2.getRowCount() != 0) {
            if ((evt.getKeyCode() == KeyEvent.VK_ENTER) || (evt.getKeyCode() == KeyEvent.VK_UP) || (evt.getKeyCode() == KeyEvent.VK_DOWN)) {
                try {
                    getDataFK2();
                } catch (java.lang.NullPointerException e) {
                }
            }
            WindowCariNoRujuk.dispose();
        }
    }//GEN-LAST:event_tbFaskes2KeyPressed

    private void tbFaskes3MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tbFaskes3MouseClicked
        if (tabMode3.getRowCount() != 0) {
            try {
                getDataFK1byk();
            } catch (java.lang.NullPointerException e) {
            }
        }
    }//GEN-LAST:event_tbFaskes3MouseClicked

    private void tbFaskes3KeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_tbFaskes3KeyPressed
        if (tabMode3.getRowCount() != 0) {
            if ((evt.getKeyCode() == KeyEvent.VK_ENTER) || (evt.getKeyCode() == KeyEvent.VK_UP) || (evt.getKeyCode() == KeyEvent.VK_DOWN)) {
                try {
                    getDataFK1byk();
                } catch (java.lang.NullPointerException e) {
                }
            }
            WindowCariNoRujuk.dispose();
        }
    }//GEN-LAST:event_tbFaskes3KeyPressed

    private void tbFaskes4MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tbFaskes4MouseClicked
        if (tabMode4.getRowCount() != 0) {
            try {
                getDataFK2byk();
            } catch (java.lang.NullPointerException e) {
            }
        }
    }//GEN-LAST:event_tbFaskes4MouseClicked

    private void tbFaskes4KeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_tbFaskes4KeyPressed
        if (tabMode4.getRowCount() != 0) {
            if ((evt.getKeyCode() == KeyEvent.VK_ENTER) || (evt.getKeyCode() == KeyEvent.VK_UP) || (evt.getKeyCode() == KeyEvent.VK_DOWN)) {
                try {
                    getDataFK2byk();
                } catch (java.lang.NullPointerException e) {
                }
            }
            WindowCariNoRujuk.dispose();
        }
    }//GEN-LAST:event_tbFaskes4KeyPressed

    private void TabRujukanMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_TabRujukanMouseClicked
        if (TabRujukan.getSelectedIndex() == 0) {
            tampilFaskes1();
        } else if (TabRujukan.getSelectedIndex() == 1) {
            tampilFaskes2();
        } else if (TabRujukan.getSelectedIndex() == 2) {
            tampilFaskes1BYK();
        } else if (TabRujukan.getSelectedIndex() == 3) {
            tampilFaskes2BYK();
        }
    }//GEN-LAST:event_TabRujukanMouseClicked

    private void BtnKeluar1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_BtnKeluar1ActionPerformed
        WindowCariNoRujuk.dispose();
    }//GEN-LAST:event_BtnKeluar1ActionPerformed

    private void BtnKeluar1KeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_BtnKeluar1KeyPressed
        if (evt.getKeyCode() == KeyEvent.VK_SPACE) {
            dispose();
        } else {
            Valid.pindah(evt, BtnPrint, BtnKeluar);
        }
    }//GEN-LAST:event_BtnKeluar1KeyPressed

    private void kode_rujukanyaKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_kode_rujukanyaKeyPressed
        // TODO add your handling code here:
    }//GEN-LAST:event_kode_rujukanyaKeyPressed

    private void nmfaskes_keluarKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_nmfaskes_keluarKeyPressed
        // TODO add your handling code here:
    }//GEN-LAST:event_nmfaskes_keluarKeyPressed

    private void NoTelpKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_NoTelpKeyPressed
        // TODO add your handling code here:
    }//GEN-LAST:event_NoTelpKeyPressed

    private void LakaLantasItemStateChanged(java.awt.event.ItemEvent evt) {//GEN-FIRST:event_LakaLantasItemStateChanged

    }//GEN-LAST:event_LakaLantasItemStateChanged

    private void LakaLantasMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_LakaLantasMouseClicked
        LakaLantas.setEditable(false);
    }//GEN-LAST:event_LakaLantasMouseClicked

    private void LakaLantasKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_LakaLantasKeyPressed
        Valid.pindah(evt, btnPoli, JenisPelayanan);
    }//GEN-LAST:event_LakaLantasKeyPressed

    private void TanggalKejadianMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_TanggalKejadianMouseClicked
        TanggalKejadian.setEditable(false);
    }//GEN-LAST:event_TanggalKejadianMouseClicked

    private void TanggalKejadianKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_TanggalKejadianKeyPressed
        // TODO add your handling code here:
    }//GEN-LAST:event_TanggalKejadianKeyPressed

    private void ChkJasaRaharjaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_ChkJasaRaharjaActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_ChkJasaRaharjaActionPerformed

    private void ChkTaspenActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_ChkTaspenActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_ChkTaspenActionPerformed

    private void ChkBPJSTenagaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_ChkBPJSTenagaActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_ChkBPJSTenagaActionPerformed

    private void ChkAsaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_ChkAsaActionPerformed

    }//GEN-LAST:event_ChkAsaActionPerformed

    private void KetKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_KetKeyPressed
        // TODO add your handling code here:
    }//GEN-LAST:event_KetKeyPressed

    private void btnProvActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnProvActionPerformed
        pilihan = 1;
        provinsi.setSize(618, 338);
        provinsi.setLocationRelativeTo(internalFrame1);
        provinsi.setVisible(true);
        provinsi.fokus();
    }//GEN-LAST:event_btnProvActionPerformed

    private void btnProvKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_btnProvKeyPressed
        // TODO add your handling code here:
    }//GEN-LAST:event_btnProvKeyPressed

    private void btnKabActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnKabActionPerformed
        pilihan = 1;
        kabupaten.setSize(746, 310);
        kabupaten.setLocationRelativeTo(internalFrame1);
        kabupaten.setVisible(true);
        kabupaten.fokus(KdProv.getText(), NmProv.getText());
    }//GEN-LAST:event_btnKabActionPerformed

    private void btnKabKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_btnKabKeyPressed
        // TODO add your handling code here:
    }//GEN-LAST:event_btnKabKeyPressed

    private void btnKecActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnKecActionPerformed
        pilihan = 1;
        kecamatan.setSize(764, 376);
        kecamatan.setLocationRelativeTo(internalFrame1);
        kecamatan.setVisible(true);
        kecamatan.fokus(KdKab.getText(), NmKab.getText());
    }//GEN-LAST:event_btnKecActionPerformed

    private void btnKecKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_btnKecKeyPressed
        // TODO add your handling code here:
    }//GEN-LAST:event_btnKecKeyPressed

    private void suplesiMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_suplesiMouseClicked
        suplesi.setEditable(false);
    }//GEN-LAST:event_suplesiMouseClicked

    private void suplesiKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_suplesiKeyPressed
        // TODO add your handling code here:
    }//GEN-LAST:event_suplesiKeyPressed

    private void NoSEPSuplesiKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_NoSEPSuplesiKeyPressed
        // TODO add your handling code here:
    }//GEN-LAST:event_NoSEPSuplesiKeyPressed

    private void LokasiLakaKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_LokasiLakaKeyPressed
        Valid.pindah(evt, Catatan, BtnSimpan);
    }//GEN-LAST:event_LokasiLakaKeyPressed

    private void MnCetakKodeBarkodeActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_MnCetakKodeBarkodeActionPerformed
        Sequel.cariIsi("select kd_booking from booking_registrasi where kd_booking=?", cekKDboking, kdboking.getText());

        if (tabMode.getRowCount() == 0) {
            JOptionPane.showMessageDialog(null, "Maaf, data pasien sudah habis...!!!!");
            R2.requestFocus();
        } else if (TPasien.getText().trim().equals("") && (TNoRM.getText().trim().equals(""))) {
            JOptionPane.showMessageDialog(null, "Maaf, Silahkan anda pilih dulu data pasien dengan mengklik data pada tabel...!!!");
            tbObat.requestFocus();
        } else if (cekKDboking.getText().equals("")) {
            JOptionPane.showMessageDialog(null, "Kode booking tidak ditemukan didatabase...!!!");
            tbObat.requestFocus();
        } else {
            this.setCursor(Cursor.getPredefinedCursor(Cursor.WAIT_CURSOR));
            Map<String, Object> param = new HashMap<>();
            param.put("namars", var.getnamars());
            param.put("logo", Sequel.cariGambar("select logo from setting"));
            Valid.AutoPrintToImage("rptKodeBooking.jrxml", "report", "::[ Kode Booking (Barcode) Pasien ]::",
                    "SELECT br.kd_booking, date_format(br.tanggal_periksa,'%d %M %Y') tgl_periksa, pl.nm_poli FROM booking_registrasi br "
                    + "inner join poliklinik pl on pl.kd_poli=br.kd_poli "
                    + "WHERE br.kd_booking='" + cekKDboking.getText() + "'", param, Sequel.cariFolder(), "Barcode Booking");
            this.setCursor(Cursor.getDefaultCursor());

            emptTeks();
            tampil();
        }
    }//GEN-LAST:event_MnCetakKodeBarkodeActionPerformed

    private void jkKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_jkKeyPressed
        // TODO add your handling code here:
    }//GEN-LAST:event_jkKeyPressed

    private void rmMatiKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_rmMatiKeyPressed
        // TODO add your handling code here:
    }//GEN-LAST:event_rmMatiKeyPressed

    private void jamMatiKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_jamMatiKeyPressed
        // TODO add your handling code here:
    }//GEN-LAST:event_jamMatiKeyPressed

    private void tglMatiKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_tglMatiKeyPressed
        // TODO add your handling code here:
    }//GEN-LAST:event_tglMatiKeyPressed

    private void cekKDbokingKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_cekKDbokingKeyPressed
        // TODO add your handling code here:
    }//GEN-LAST:event_cekKDbokingKeyPressed

    private void MnCetakKodeQRActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_MnCetakKodeQRActionPerformed
        Sequel.cariIsi("select kd_booking from booking_registrasi where kd_booking=?", cekKDboking, kdboking.getText());

        if (tabMode.getRowCount() == 0) {
            JOptionPane.showMessageDialog(null, "Maaf, data pasien sudah habis...!!!!");
            R2.requestFocus();
        } else if (TPasien.getText().trim().equals("") && (TNoRM.getText().trim().equals(""))) {
            JOptionPane.showMessageDialog(null, "Maaf, Silahkan anda pilih dulu data pasien dengan mengklik data pada tabel...!!!");
            tbObat.requestFocus();
        } else if (cekKDboking.getText().equals("")) {
            JOptionPane.showMessageDialog(null, "Kode booking tidak ditemukan didatabase...!!!");
            tbObat.requestFocus();
        } else {
            Valid.cetakQr(kdboking.getText(), Sequel.cariFolder(), "QR.jpg");
            Sequel.queryu("delete from setting_qr where judul = 'QR'");
            Sequel.menyimpanQr("setting_qr", "'QR'", "file QRCode Booking", Sequel.cariFolderPrint());

            this.setCursor(Cursor.getPredefinedCursor(Cursor.WAIT_CURSOR));
            Map<String, Object> param = new HashMap<>();
            param.put("namars", var.getnamars());
            param.put("lokasi", Sequel.cariGambar("select gambar from setting_qr where judul = 'QR'"));
            param.put("logo", Sequel.cariGambar("select logo from setting"));
            Valid.AutoPrintToImage("rptKodeBookingQR.jrxml", "report", "::[ Kode Booking (QR Code) Pasien ]::",
                    "SELECT br.kd_booking, date_format(br.tanggal_periksa,'%d %M %Y') tgl_periksa, pl.nm_poli FROM booking_registrasi br "
                    + "inner join poliklinik pl on pl.kd_poli=br.kd_poli "
                    + "WHERE br.kd_booking='" + cekKDboking.getText() + "'", param, Sequel.cariFolder(), "QRCode Booking");
            this.setCursor(Cursor.getDefaultCursor());

            emptTeks();
            tampil();
        }
    }//GEN-LAST:event_MnCetakKodeQRActionPerformed

    private void norawatKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_norawatKeyPressed
        // TODO add your handling code here:
    }//GEN-LAST:event_norawatKeyPressed

    private void MnTidakjadibatalActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_MnTidakjadibatalActionPerformed
        if (statusboking.getText().equals("Terdaftar")) {
            JOptionPane.showMessageDialog(null, "Pasien sudah terdaftar dipoliklinik, status booking tidak bisa diganti...!!!");
        } else {
            Sequel.mengedit("booking_registrasi", "kd_booking='" + kdboking.getText() + "'", "status_booking='Menunggu' ");
            emptTeks();
            tampil();
        }
    }//GEN-LAST:event_MnTidakjadibatalActionPerformed

    private void statusbokingKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_statusbokingKeyPressed
        // TODO add your handling code here:
    }//GEN-LAST:event_statusbokingKeyPressed

    private void BtnSukuActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_BtnSukuActionPerformed
        var.setform("DlgBookingRegistrasi");
        suku.isCek();
        suku.setSize(1105, 512);
        suku.setLocationRelativeTo(internalFrame1);
        suku.setVisible(true);
        suku.TCari.requestFocus();
    }//GEN-LAST:event_BtnSukuActionPerformed

    private void BtnSukuKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_BtnSukuKeyPressed
        if (evt.getKeyCode() == KeyEvent.VK_SPACE) {
            BtnSukuActionPerformed(null);
        } else if (evt.getKeyCode() == KeyEvent.VK_PAGE_UP) {
            BtnBahasa.requestFocus();
        } else if (evt.getKeyCode() == KeyEvent.VK_ENTER) {
            BtnBahasa.requestFocus();
        }
    }//GEN-LAST:event_BtnSukuKeyPressed

    private void BtnBahasaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_BtnBahasaActionPerformed
        var.setform("DlgBookingRegistrasi");
        bahasa.isCek();
        bahasa.setSize(1105, 512);
        bahasa.setLocationRelativeTo(internalFrame1);
        bahasa.setVisible(true);
        bahasa.TCari.requestFocus();
    }//GEN-LAST:event_BtnBahasaActionPerformed

    private void BtnBahasaKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_BtnBahasaKeyPressed
        if (evt.getKeyCode() == KeyEvent.VK_SPACE) {
            BtnBahasaActionPerformed(null);
        } else if (evt.getKeyCode() == KeyEvent.VK_PAGE_UP) {
            BtnSuku.requestFocus();
        } else if (evt.getKeyCode() == KeyEvent.VK_ENTER) {
            BtnSimpan.requestFocus();
        }
    }//GEN-LAST:event_BtnBahasaKeyPressed

    private void kdsukuKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_kdsukuKeyPressed
        // TODO add your handling code here:
    }//GEN-LAST:event_kdsukuKeyPressed

    private void kdbahasaKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_kdbahasaKeyPressed
        // TODO add your handling code here:
    }//GEN-LAST:event_kdbahasaKeyPressed

    private void MnSEPActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_MnSEPActionPerformed
        if (tabMode.getRowCount() == 0) {
            JOptionPane.showMessageDialog(null, "Maaf, tabel masih kosong...!!!!");
            NoReg.requestFocus();
        } else if (TNoRM.getText().trim().equals("") || (TPasien.getText().trim().equals(""))) {
            JOptionPane.showMessageDialog(null, "Maaf, Silahkan anda pilih dulu dengan mengklik data pada tabel...!!!");
            tbObat.requestFocus();
        } else if (kdpnj.getText().equals("U01")) {
            JOptionPane.showMessageDialog(null, "Pasien umum tidak ada membuat SEP...!!!");
        } else {
            if (kdpnj.getText().equals("B01") || (kdpnj.getText().equals("A03"))) {
                if (statusSEP.getText().equals("SUDAH")) {
                    JOptionPane.showMessageDialog(null, "Pasien ini SEP nya sudah tercetak...!!!");
                    emptTeks();
                    tampil();
                    ChkInput.setSelected(true);
                    isForm();
                } else if (statusSEP.getText().equals("BELUM")) {
                    JOptionPane.showMessageDialog(null, "Pasien ini belum datang, biar aja SEP nya dicetak sendiri...!!!");
                    emptTeks();
                    tampil();
                    ChkInput.setSelected(true);
                    isForm();
                } else if (statusSEP.getText().equals("GAGAL")) {
                    this.setCursor(Cursor.getPredefinedCursor(Cursor.WAIT_CURSOR));
                    var.setform("DlgBookingRegistrasi");
                    BPJSDataSEP dlgki = new BPJSDataSEP(null, false);
                    dlgki.setSize(internalFrame1.getWidth(), internalFrame1.getHeight());
                    dlgki.setLocationRelativeTo(internalFrame1);
                    dlgki.isCek();
                    dlgki.setNoRm(norawat.getText(), Valid.SetTgl(TanggalPeriksa.getSelectedItem() + ""), "2. Ralan", KdPoli1.getText(), NmPoli1.getText());
                    dlgki.Catatan.setText(Catatan.getText());
                    dlgki.tampil();
                    dlgki.setVisible(true);
                    dlgki.cekLAKA();
                    dlgki.cekLAYAN();
                    dlgki.tampilNoRujukan(NoKartu.getText());
                    this.setCursor(Cursor.getDefaultCursor());
                }
            }
        }
    }//GEN-LAST:event_MnSEPActionPerformed

    private void verif_dataItemStateChanged(java.awt.event.ItemEvent evt) {//GEN-FIRST:event_verif_dataItemStateChanged
        // TODO add your handling code here:
    }//GEN-LAST:event_verif_dataItemStateChanged

    private void verif_dataMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_verif_dataMouseClicked
        verif_data.setEditable(false);
    }//GEN-LAST:event_verif_dataMouseClicked

    private void verif_dataKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_verif_dataKeyPressed
        Valid.pindah(evt, btnPenjab, no_telp);
    }//GEN-LAST:event_verif_dataKeyPressed

    private void MnsudahActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_MnsudahActionPerformed
        Sequel.cariIsi("select kd_booking from kelengkapan_booking_sep_bpjs where nomr=?", cekKDboking, TNoRM.getText());

        if (var.getkode().equals("Admin Utama")) {
            if (cekKDboking.getText().equals("")) {
                JOptionPane.showMessageDialog(null, "Pilih dulu salah satu nama pasiennya dg. mengklik data pada tabel & hanya utk. pasien BPJS saja...!!!");
                tbObat.requestFocus();
            } else if (kdpnj.getText().equals("B01") || (kdpnj.getText().equals("A03"))) {
                Sequel.mengedit("kelengkapan_booking_sep_bpjs", "kd_booking='" + kdboking.getText() + "'", "status_cetak_sep='SUDAH' ");
                emptTeks();
                tampil();
            }
        } else {
            JOptionPane.showMessageDialog(null, "Silahkan koordinasi dulu dg. UNIT SIMRS utk. memastikan datanya...!!!");
            emptTeks();
            tampil();
        }
    }//GEN-LAST:event_MnsudahActionPerformed

    private void MnbelumActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_MnbelumActionPerformed
        Sequel.cariIsi("select kd_booking from kelengkapan_booking_sep_bpjs where nomr=?", cekKDboking, TNoRM.getText());

        if (var.getkode().equals("Admin Utama") || var.getbooking_registrasi()) {
            if (cekKDboking.getText().equals("")) {
                JOptionPane.showMessageDialog(null, "Pilih dulu salah satu nama pasiennya dg. mengklik data pada tabel & hanya utk. pasien BPJS saja...!!!");
                tbObat.requestFocus();
            } else if (kdpnj.getText().equals("B01") || (kdpnj.getText().equals("A03"))) {
                Sequel.mengedit("kelengkapan_booking_sep_bpjs", "kd_booking='" + kdboking.getText() + "'", "status_cetak_sep='BELUM' ");
                emptTeks();
                tampil();
            }
        } else {
            JOptionPane.showMessageDialog(null, "Silahkan koordinasi dulu dg. UNIT SIMRS utk. memastikan datanya...!!!");
            emptTeks();
            tampil();
        }
    }//GEN-LAST:event_MnbelumActionPerformed

    private void MngagalActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_MngagalActionPerformed
        Sequel.cariIsi("select kd_booking from kelengkapan_booking_sep_bpjs where nomr=?", cekKDboking, TNoRM.getText());

        if (var.getkode().equals("Admin Utama") || var.getbooking_registrasi()) {
            if (cekKDboking.getText().equals("")) {
                JOptionPane.showMessageDialog(null, "Pilih dulu salah satu nama pasiennya dg. mengklik data pada tabel & hanya utk. pasien BPJS saja...!!!");
                tbObat.requestFocus();
            } else if (kdpnj.getText().equals("B01") || (kdpnj.getText().equals("A03"))) {
                Sequel.mengedit("kelengkapan_booking_sep_bpjs", "kd_booking='" + kdboking.getText() + "'", "status_cetak_sep='GAGAL' ");
                emptTeks();
                tampil();
            }
        } else {
            JOptionPane.showMessageDialog(null, "Silahkan koordinasi dulu dg. UNIT SIMRS utk. memastikan datanya...!!!");
            emptTeks();
            tampil();
        }
    }//GEN-LAST:event_MngagalActionPerformed

    private void cekKelengkapanSEPKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_cekKelengkapanSEPKeyPressed
        // TODO add your handling code here:
    }//GEN-LAST:event_cekKelengkapanSEPKeyPressed

    /**
     * @param args the command line arguments
     */
    public static void main(String args[]) {
        java.awt.EventQueue.invokeLater(() -> {
            DlgBookingRegistrasi dialog = new DlgBookingRegistrasi(new javax.swing.JFrame(), true);
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
    private widget.ComboBox AsalRujukan;
    private widget.Button BtnAll;
    private widget.Button BtnBahasa;
    private widget.Button BtnBatal;
    private widget.Button BtnCari;
    private widget.Button BtnDokter;
    private widget.Button BtnGanti;
    private widget.Button BtnHapus;
    private widget.Button BtnJadwal;
    private widget.Button BtnKeluar;
    private widget.Button BtnKeluar1;
    private widget.Button BtnPasien;
    private widget.Button BtnPoli;
    private widget.Button BtnPrint;
    private widget.Button BtnSimpan;
    private widget.Button BtnSuku;
    private widget.ComboBox COB;
    private widget.TextArea Catatan;
    private widget.CekBox ChkAsa;
    private widget.CekBox ChkBPJSTenaga;
    private widget.CekBox ChkInput;
    private widget.CekBox ChkJasaRaharja;
    private widget.CekBox ChkTaspen;
    private widget.Tanggal DTPCari1;
    private widget.Tanggal DTPCari2;
    private widget.Tanggal DTPCari3;
    private widget.Tanggal DTPCari4;
    private widget.ComboBox Eksekutif;
    public widget.TabPane FormInput;
    private widget.TextBox JK;
    private widget.ComboBox JenisPelayanan;
    private widget.TextBox JenisPeserta;
    private widget.ComboBox KasusKatarak;
    private widget.TextBox KdDokter;
    private widget.TextBox KdKab;
    private widget.TextBox KdKec;
    private widget.TextBox KdPPK;
    private widget.TextBox KdPenyakit;
    private widget.TextBox KdPoli;
    private widget.TextBox KdPoli1;
    private widget.TextBox KdPpkRujukan;
    private widget.TextBox KdProv;
    private widget.TextBox Kddpjp;
    private widget.ComboBox Kelas;
    private widget.TextBox Ket;
    private widget.Label LCount;
    private widget.Label LabKab;
    private widget.Label LabKec;
    private widget.Label LabKetkll;
    private widget.Label LabNoSup;
    private widget.Label LabProv;
    private widget.Label LabSup;
    private widget.Label LabTglkll;
    private widget.Label LabelKatarak;
    private widget.Label LabelKelas;
    private widget.Label LabelKelas1;
    private widget.Label LabelPoli;
    private widget.Label LabjaminKll;
    private widget.ComboBox LakaLantas;
    private widget.TextBox LokasiLaka;
    private javax.swing.JMenuItem MnCetakKodeBarkode;
    private javax.swing.JMenuItem MnCetakKodeQR;
    private javax.swing.JMenuItem MnSEP;
    private javax.swing.JMenu MnSttsCetakSEP;
    private javax.swing.JMenuItem MnTidakjadibatal;
    private javax.swing.JMenuItem Mnbelum;
    private javax.swing.JMenuItem Mngagal;
    private javax.swing.JMenuItem Mnsudah;
    private widget.TextBox NmDPJP;
    private widget.TextBox NmDokter;
    private widget.TextBox NmKab;
    private widget.TextBox NmKec;
    private widget.TextBox NmPPK;
    private widget.TextBox NmPenyakit;
    private widget.TextBox NmPoli;
    private widget.TextBox NmPoli1;
    private widget.TextBox NmPpkRujukan;
    private widget.TextBox NmProv;
    private widget.TextBox NoKartu;
    private widget.TextBox NoReg;
    private widget.TextBox NoRujukan;
    private widget.TextBox NoSEPSuplesi;
    private widget.TextBox NoTelp;
    private javax.swing.JPanel PanelInput;
    private javax.swing.JPopupMenu Popup;
    private widget.RadioButton R2;
    private widget.RadioButton R3;
    private widget.ScrollPane Scroll;
    private widget.ScrollPane Scroll3;
    private widget.TextBox Status;
    public widget.TextBox TCari;
    private widget.TextBox TNoRM;
    private widget.TextBox TPasien;
    private javax.swing.JTabbedPane TabRujukan;
    private widget.Tanggal TanggalBooking;
    private widget.Tanggal TanggalKejadian;
    private widget.Tanggal TanggalPeriksa;
    private widget.Tanggal TanggalRujuk;
    private widget.TextBox TglLahir;
    private javax.swing.JDialog WindowCariNoRujuk;
    private widget.Button btnDPJP;
    private widget.Button btnDiagnosa;
    private widget.Button btnKab;
    private widget.Button btnKec;
    private widget.Button btnNoRujukan;
    private widget.Button btnNoSKDP;
    private widget.Button btnPPKRujukan;
    private widget.Button btnPenjab;
    private widget.Button btnPoli;
    private widget.Button btnProv;
    private javax.swing.ButtonGroup buttonGroup1;
    private widget.TextBox cekKDboking;
    private widget.TextBox cekKelengkapanSEP;
    private widget.InternalFrame internalFrame1;
    private widget.InternalFrame internalFrame2;
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
    private widget.Label jLabel36;
    private widget.Label jLabel4;
    private widget.Label jLabel40;
    private widget.Label jLabel42;
    private widget.Label jLabel43;
    private widget.Label jLabel5;
    private widget.Label jLabel6;
    private widget.Label jLabel7;
    private widget.Label jLabel8;
    private widget.Label jLabel9;
    private javax.swing.JPanel jPanel3;
    private widget.TextBox jamMati;
    private widget.TextBox jk;
    private widget.TextBox kdbahasa;
    private widget.TextBox kdboking;
    private widget.TextBox kdpnj;
    private widget.TextBox kdsuku;
    private widget.TextBox kode_rujukanya;
    private widget.Label label_cetak;
    private widget.TextBox nmbahasa;
    private widget.TextBox nmfaskes_keluar;
    private widget.TextBox nmpnj;
    private widget.TextBox nmsuku;
    private widget.TextBox noSKDP;
    private widget.TextBox no_telp;
    private widget.TextBox norawat;
    private widget.panelisi panelCari;
    private widget.panelisi panelGlass10;
    private widget.panelisi panelGlass8;
    private widget.panelisi panelisi1;
    private widget.panelisi panelisi2;
    private widget.panelisi panelisi3;
    private widget.panelisi panelisi4;
    private widget.TextBox rmMati;
    private widget.Label rujukanSEP;
    private widget.ScrollPane scrollPane1;
    private widget.ScrollPane scrollPane2;
    private widget.ScrollPane scrollPane3;
    private widget.ScrollPane scrollPane4;
    private widget.TextBox statusSEP;
    private widget.TextBox statusboking;
    private widget.ComboBox suplesi;
    private widget.Table tbFaskes1;
    private widget.Table tbFaskes2;
    private widget.Table tbFaskes3;
    private widget.Table tbFaskes4;
    private widget.Table tbObat;
    private widget.TextBox tglMati;
    private widget.ComboBox verif_data;
    // End of variables declaration//GEN-END:variables

    public void tampil() {
        if (R2.isSelected() == true) {
            status = " br.tanggal_booking between '" + Valid.SetTgl(DTPCari1.getSelectedItem() + "") + " 00:00:00' and '" + Valid.SetTgl(DTPCari2.getSelectedItem() + "") + " 23:59:59' ";
        } else if (R3.isSelected() == true) {
            status = " br.tanggal_periksa between '" + Valid.SetTgl(DTPCari3.getSelectedItem() + "") + "' and '" + Valid.SetTgl(DTPCari4.getSelectedItem() + "") + "' ";
        }
        Valid.tabelKosong(tabMode);
        try {
            ps = koneksi.prepareStatement(
                    "select br.kd_booking,DATE_FORMAT(br.tanggal_booking,'%d-%m-%Y') tgl_boking,br.no_rkm_medis, p.nm_pasien,br.tanggal_periksa,pj.png_jawab,pl.nm_poli, "
                    + "d.nm_dokter,br.no_reg,concat(p.alamat,', ',kl.nm_kel,', ',kc.nm_kec,',',kb.nm_kab) alamat, "
                    + "br.status_booking, br.data_dari, br.kd_poli, br.kd_dokter, br.kd_pj, br.no_telp_pemesan, br.no_rawat, "
                    + "if(br.kd_pj='U01','Tidak ada SEP',ks.status_cetak_sep) sep_bpjs from booking_registrasi br inner join pasien p on p.no_rkm_medis=br.no_rkm_medis "
                    + "inner join dokter d on d.kd_dokter=br.kd_dokter inner join poliklinik pl on pl.kd_poli=br.kd_poli "
                    + "INNER JOIN kelurahan kl on kl.kd_kel=p.kd_kel INNER JOIN kecamatan kc on kc.kd_kec=p.kd_kec "
                    + "INNER JOIN kabupaten kb on kb.kd_kab=p.kd_kab INNER JOIN penjab pj on pj.kd_pj=br.kd_pj "
                    + "left join kelengkapan_booking_sep_bpjs ks on ks.kd_booking=br.kd_booking where "
                    + status + " and br.no_rkm_medis like ? or "
                    + status + " and p.nm_pasien like ? or "
                    + status + " and pl.nm_poli like ? or "
                    + status + " and br.kd_booking like ? or "
                    + status + " and br.no_rkm_medis like ? or "
                    + status + " and pj.png_jawab like ? or "
                    + status + " and concat(p.alamat,', ',kl.nm_kel,', ',kc.nm_kec,',',kb.nm_kab) like ? or "
                    + status + " and br.status_booking like ? or "
                    + status + " and br.data_dari like ? or "
                    + status + " and br.no_telp_pemesan like ? or "
                    + status + " and br.no_rawat like ? or "
                    + status + " and if(br.kd_pj='U01','Tidak ada SEP',ks.status_cetak_sep) like ? or "
                    + status + " and d.nm_dokter like ? order by br.tanggal_booking,d.nm_dokter");
            try {
                ps.setString(1, "%" + TCari.getText().trim() + "%");
                ps.setString(2, "%" + TCari.getText().trim() + "%");
                ps.setString(3, "%" + TCari.getText().trim() + "%");
                ps.setString(4, "%" + TCari.getText().trim() + "%");
                ps.setString(5, "%" + TCari.getText().trim() + "%");
                ps.setString(6, "%" + TCari.getText().trim() + "%");
                ps.setString(7, "%" + TCari.getText().trim() + "%");
                ps.setString(8, "%" + TCari.getText().trim() + "%");
                ps.setString(9, "%" + TCari.getText().trim() + "%");
                ps.setString(10, "%" + TCari.getText().trim() + "%");
                ps.setString(11, "%" + TCari.getText().trim() + "%");
                ps.setString(12, "%" + TCari.getText().trim() + "%");
                ps.setString(13, "%" + TCari.getText().trim() + "%");
                rs = ps.executeQuery();
                while (rs.next()) {
                    tabMode.addRow(new Object[]{
                        rs.getString("kd_booking"),
                        rs.getString("tgl_boking"),
                        rs.getString("no_rkm_medis"),
                        rs.getString("nm_pasien"),
                        rs.getString("tanggal_periksa"),
                        rs.getString("png_jawab"),
                        rs.getString("nm_poli"),
                        rs.getString("nm_dokter"),
                        rs.getString("no_reg"),
                        rs.getString("alamat"),
                        rs.getString("status_booking"),
                        rs.getString("data_dari"),
                        rs.getString("kd_poli"),
                        rs.getString("kd_dokter"),
                        rs.getString("kd_pj"),
                        rs.getString("no_telp_pemesan"),
                        rs.getString("no_rawat"),
                        rs.getString("sep_bpjs")
                    });
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
        LCount.setText("" + tabMode.getRowCount());
    }

    public void emptTeks() {
        autoNomorBooking();
        FormInput.setSelectedIndex(0);
        FormInput.setEnabledAt(1, false);
        FormInput.setEnabledAt(2, false);
        TNoRM.requestFocus();
        KdDokter.setText("");
        NmDokter.setText("");
        TNoRM.setText("");
        TPasien.setText("");
        KdPoli.setText("");
        NmPoli.setText("");
        kdpnj.setText("");
        nmpnj.setText("");
        no_telp.setText("");
        statusSEP.setText("");
        jk.setText("");
        rmMati.setText("");
        jamMati.setText("");
        tglMati.setText("");
        cekKDboking.setText("");
        norawat.setText("");
        statusboking.setText("");
        kdsuku.setText("");
        nmsuku.setText("");
        kdbahasa.setText("");
        nmbahasa.setText("");
        cekKelengkapanSEP.setText("");
        verif_data.setSelectedIndex(0);
        TanggalBooking.setDate(new Date());
        TanggalPeriksa.setDate(new Date());
        isNomer();
        emptKelengkapanSEP();
    }

    private void isNomer() {
        switch (URUTNOREG) {
            case "poli":
                Valid.autoNomer3("select ifnull(MAX(CONVERT(no_reg,signed)),0) from booking_registrasi where kd_poli='" + KdPoli.getText() + "' and tanggal_periksa='" + Valid.SetTgl(TanggalPeriksa.getSelectedItem() + "") + "'", "", 3, NoReg);
                break;
            case "dokter":
                Valid.autoNomer3("select ifnull(MAX(CONVERT(no_reg,signed)),0) from booking_registrasi where kd_dokter='" + KdDokter.getText() + "' and tanggal_periksa='" + Valid.SetTgl(TanggalPeriksa.getSelectedItem() + "") + "'", "", 3, NoReg);
                break;
            case "dokter & poli":
                Valid.autoNomer3("select ifnull(MAX(CONVERT(no_reg,signed)),0) from booking_registrasi where kd_dokter='" + KdDokter.getText() + "' and kd_poli='" + KdPoli.getText() + "' and tanggal_periksa='" + Valid.SetTgl(TanggalPeriksa.getSelectedItem() + "") + "'", "", 3, NoReg);
                break;
            default:
                Valid.autoNomer3("select ifnull(MAX(CONVERT(no_reg,signed)),0) from booking_registrasi where kd_dokter='" + KdDokter.getText() + "' and tanggal_periksa='" + Valid.SetTgl(TanggalPeriksa.getSelectedItem() + "") + "'", "", 3, NoReg);
                break;
        }
    }

    private void getData() {
        if (tbObat.getSelectedRow() != -1) {
            kdboking.setText(tbObat.getValueAt(tbObat.getSelectedRow(), 0).toString());
            TNoRM.setText(tbObat.getValueAt(tbObat.getSelectedRow(), 2).toString());
            TPasien.setText(tbObat.getValueAt(tbObat.getSelectedRow(), 3).toString());
            KdPoli.setText(tbObat.getValueAt(tbObat.getSelectedRow(), 12).toString());
            Sequel.cariIsi("select nm_poli from poliklinik where kd_poli=? ", NmPoli, KdPoli.getText());
            KdDokter.setText(tbObat.getValueAt(tbObat.getSelectedRow(), 13).toString());
            Sequel.cariIsi("select nm_dokter from dokter where kd_dokter=? ", NmDokter, KdDokter.getText());
            kdpnj.setText(tbObat.getValueAt(tbObat.getSelectedRow(), 14).toString());
            Sequel.cariIsi("select png_jawab from penjab where kd_pj=? ", nmpnj, kdpnj.getText());
            NoReg.setText(tbObat.getValueAt(tbObat.getSelectedRow(), 8).toString());
            statusboking.setText(tbObat.getValueAt(tbObat.getSelectedRow(), 10).toString());
            verif_data.setSelectedItem(tbObat.getValueAt(tbObat.getSelectedRow(), 11).toString());
            Valid.SetTgl(TanggalPeriksa, tbObat.getValueAt(tbObat.getSelectedRow(), 4).toString());
            Sequel.cariIsi("select no_telp_pemesan from booking_registrasi where kd_booking=? ", no_telp, kdboking.getText());
            Sequel.cariIsi("select no_rkm_medis from pasien_mati where no_rkm_medis=? ", rmMati, TNoRM.getText());
            Sequel.cariIsi("select jk from pasien where no_rkm_medis=? ", jk, TNoRM.getText());
            norawat.setText(tbObat.getValueAt(tbObat.getSelectedRow(), 16).toString());
            Sequel.cariIsi("select suku_bangsa from pasien where no_rkm_medis=?", kdsuku, TNoRM.getText());
            Sequel.cariIsi("select bahasa_pasien from pasien where no_rkm_medis=?", kdbahasa, TNoRM.getText());
            Sequel.cariIsi("select nama_suku_bangsa from suku_bangsa where id=?", nmsuku, kdsuku.getText());
            Sequel.cariIsi("select nama_bahasa from bahasa_pasien where id=?", nmbahasa, kdbahasa.getText());

            if (var.getkode().equals("Admin Utama") || (kdpnj.getText().equals("B01") || (kdpnj.getText().equals("A03")))) {
                FormInput.setEnabledAt(1, true);
                FormInput.setEnabledAt(2, true);
            } else if ((!kdpnj.getText().equals("B01") || (!kdpnj.getText().equals("A03")))) {
                FormInput.setEnabledAt(1, false);
                FormInput.setEnabledAt(2, false);
                FormInput.setSelectedIndex(0);
                emptKelengkapanSEP();
            }

            cekDataKelengkapan();
            Sequel.cariIsi("select nama_rujukan from master_nama_rujukan where kode_faskes_bpjs=? ", NmPpkRujukan, KdPpkRujukan.getText());
            JenisPelayanan.setSelectedIndex(1);
            //untuk penjamin laka masih belum bisa ditampilkan (bingung caranya)

//            ChkInput.setSelected(true);
//            isForm();
            label_cetak.setVisible(true);
            statusSEP.setVisible(true);
        }
    }

    public void setNoRm(String norm, String nama) {
        TNoRM.setText(norm);
        TPasien.setText(nama);
        TCari.setText(norm);
        ChkInput.setSelected(true);
        isForm();
        tampil();
    }

    public void setNoRm(String norm, String nama, String kodepoli, String namapoli, String kodedokter, String namadokter) {
        TNoRM.setText(norm);
        TPasien.setText(nama);
        KdPoli.setText(kodepoli);
        NmPoli.setText(namapoli);
        KdDokter.setText(kodedokter);
        NmDokter.setText(namadokter);
        TCari.setText(norm);
        ChkInput.setSelected(true);
        isForm();
        tampil();
    }

    private void isForm() {
        if (ChkInput.isSelected() == true) {
            ChkInput.setVisible(false);
            PanelInput.setPreferredSize(new Dimension(WIDTH, 315));
            FormInput.setVisible(true);
            ChkInput.setVisible(true);
        } else if (ChkInput.isSelected() == false) {
            ChkInput.setVisible(false);
            PanelInput.setPreferredSize(new Dimension(WIDTH, 20));
            FormInput.setVisible(false);
            ChkInput.setVisible(true);
        }
    }

    public void isCek() {
        BtnSimpan.setEnabled(var.getbooking_registrasi());
        BtnHapus.setEnabled(var.getbooking_registrasi());
//        BtnPrint.setEnabled(var.getbooking_registrasi());
        BtnPrint.setEnabled(false);
    }

    public void autoNomorBooking() {
        TanggalBooking.setDate(new Date());

        Valid.autoNomer7("select ifnull(MAX(CONVERT(LEFT(kd_booking,4),signed)),0) from booking_registrasi where "
                + "tanggal_booking like '%" + Valid.SetTgl(TanggalBooking.getSelectedItem() + "").substring(0, 7) + "%' ", "/BK/" + Valid.SetTgl(TanggalBooking.getSelectedItem() + "").substring(5, 7)
                + "/" + Valid.SetTgl(TanggalBooking.getSelectedItem() + "").substring(0, 4), 4, kdboking);
    }

    public void tampilFaskes1() {
        try {
            prop.loadFromXML(new FileInputStream("setting/database.xml"));
            String URL = prop.getProperty("URLAPIBPJS") + "/Rujukan/Peserta/" + NoKartu.getText();

            HttpHeaders headers = new HttpHeaders();
            headers.setContentType(MediaType.APPLICATION_JSON);
            headers.add("X-Cons-ID", prop.getProperty("CONSIDAPIBPJS"));
            headers.add("X-Timestamp", String.valueOf(api.GetUTCdatetimeAsString()));
            headers.add("X-Signature", api.getHmac());

            HttpEntity requestEntity = new HttpEntity(headers);
            ObjectMapper mapper = new ObjectMapper();
            JsonNode root = mapper.readTree(api.getRest().exchange(URL, HttpMethod.GET, requestEntity, String.class).getBody());
            JsonNode nameNode = root.path("metaData");
            if (nameNode.path("code").asText().equals("200")) {
                Valid.tabelKosong(tabMode1);
                JsonNode response = root.path("response").path("rujukan");
                tabMode1.addRow(new Object[]{
                    "1", response.path("noKunjungan").asText(),
                    response.path("tglKunjungan").asText(),
                    response.path("provPerujuk").path("kode").asText(),
                    response.path("provPerujuk").path("nama").asText(),
                    response.path("poliRujukan").path("nama").asText(),
                    response.path("diagnosa").path("kode").asText(),
                    response.path("diagnosa").path("nama").asText(),
                    response.path("keluhan").asText(),
                    response.path("poliRujukan").path("kode").asText(),
                    response.path("pelayanan").path("kode").asText(),
                    response.path("pelayanan").path("nama").asText()
                });
            } else {
                JOptionPane.showMessageDialog(null, nameNode.path("message").asText() + " di Faskes 1.");
            }
        } catch (Exception ex) {
            System.out.println("Notifikasi : " + ex);
            if (ex.toString().contains("UnknownHostException")) {
                JOptionPane.showMessageDialog(rootPane, "Koneksi ke server BPJS terputus...!");
            }
        }
    }

    public void tampilFaskes2() {
        try {
            prop.loadFromXML(new FileInputStream("setting/database.xml"));
            String URL = prop.getProperty("URLAPIBPJS") + "/Rujukan/RS/Peserta/" + NoKartu.getText();

            HttpHeaders headers = new HttpHeaders();
            headers.setContentType(MediaType.APPLICATION_JSON);
            headers.add("X-Cons-ID", prop.getProperty("CONSIDAPIBPJS"));
            headers.add("X-Timestamp", String.valueOf(api.GetUTCdatetimeAsString()));
            headers.add("X-Signature", api.getHmac());

            HttpEntity requestEntity = new HttpEntity(headers);
            ObjectMapper mapper = new ObjectMapper();
            JsonNode root = mapper.readTree(api.getRest().exchange(URL, HttpMethod.GET, requestEntity, String.class).getBody());
            JsonNode nameNode = root.path("metaData");
            if (nameNode.path("code").asText().equals("200")) {
                Valid.tabelKosong(tabMode2);
                JsonNode response = root.path("response").path("rujukan");
                tabMode2.addRow(new Object[]{
                    "1", response.path("noKunjungan").asText(),
                    response.path("tglKunjungan").asText(),
                    response.path("provPerujuk").path("kode").asText(),
                    response.path("provPerujuk").path("nama").asText(),
                    response.path("poliRujukan").path("nama").asText(),
                    response.path("diagnosa").path("kode").asText(),
                    response.path("diagnosa").path("nama").asText(),
                    response.path("keluhan").asText(),
                    response.path("poliRujukan").path("kode").asText(),
                    response.path("pelayanan").path("kode").asText(),
                    response.path("pelayanan").path("nama").asText()
                });
            } else {
                JOptionPane.showMessageDialog(null, nameNode.path("message").asText() + " di Faskes 2.");
            }
        } catch (Exception ex) {
            System.out.println("Notifikasi : " + ex);
            if (ex.toString().contains("UnknownHostException")) {
                JOptionPane.showMessageDialog(rootPane, "Koneksi ke server BPJS terputus...!");
            }
        }
    }

    public void tampilFaskes1BYK() {
        try {
            prop.loadFromXML(new FileInputStream("setting/database.xml"));
            String URL = prop.getProperty("URLAPIBPJS") + "/Rujukan/List/Peserta/" + NoKartu.getText();

            HttpHeaders headers = new HttpHeaders();
            headers.setContentType(MediaType.APPLICATION_JSON);
            headers.add("X-Cons-ID", prop.getProperty("CONSIDAPIBPJS"));
            headers.add("X-Timestamp", String.valueOf(api.GetUTCdatetimeAsString()));
            headers.add("X-Signature", api.getHmac());

            HttpEntity requestEntity = new HttpEntity(headers);
            ObjectMapper mapper = new ObjectMapper();
            JsonNode root = mapper.readTree(api.getRest().exchange(URL, HttpMethod.GET, requestEntity, String.class).getBody());
            JsonNode nameNode = root.path("metaData");
            if (nameNode.path("code").asText().equals("200")) {
                Valid.tabelKosong(tabMode3);
                JsonNode response = root.path("response");
                if (response.path("rujukan").isArray()) {
                    i = 1;
                    for (JsonNode list : response.path("rujukan")) {
                        tabMode3.addRow(new Object[]{
                            i + ".",
                            list.path("noKunjungan").asText(),
                            list.path("tglKunjungan").asText(),
                            list.path("provPerujuk").path("kode").asText(),
                            list.path("provPerujuk").path("nama").asText(),
                            list.path("poliRujukan").asText(),
                            list.path("diagnosa").path("kode").asText(),
                            list.path("diagnosa").path("nama").asText(),
                            list.path("keluhan").asText(),
                            list.path("poliRujukan").path("kode").asText(),
                            list.path("pelayanan").path("kode").asText(),
                            list.path("pelayanan").path("nama").asText()
                        });
                        i++;
                    }
                }
            } else {
                JOptionPane.showMessageDialog(null, nameNode.path("message").asText() + " di Faskes 1 data rujukan banyak.");
            }
        } catch (Exception ex) {
            System.out.println("Notifikasi : " + ex);
            if (ex.toString().contains("UnknownHostException")) {
                JOptionPane.showMessageDialog(rootPane, "Koneksi ke server BPJS terputus...!");
            }
        }
    }

    public void tampilFaskes2BYK() {
        try {
            prop.loadFromXML(new FileInputStream("setting/database.xml"));
            String URL = prop.getProperty("URLAPIBPJS") + "/Rujukan/RS/List/Peserta/" + NoKartu.getText();

            HttpHeaders headers = new HttpHeaders();
            headers.setContentType(MediaType.APPLICATION_JSON);
            headers.add("X-Cons-ID", prop.getProperty("CONSIDAPIBPJS"));
            headers.add("X-Timestamp", String.valueOf(api.GetUTCdatetimeAsString()));
            headers.add("X-Signature", api.getHmac());

            HttpEntity requestEntity = new HttpEntity(headers);
            ObjectMapper mapper = new ObjectMapper();
            JsonNode root = mapper.readTree(api.getRest().exchange(URL, HttpMethod.GET, requestEntity, String.class).getBody());
            JsonNode nameNode = root.path("metaData");
            if (nameNode.path("code").asText().equals("200")) {
                Valid.tabelKosong(tabMode4);
                JsonNode response = root.path("response");
                if (response.path("rujukan").isArray()) {
                    i = 1;
                    for (JsonNode list : response.path("rujukan")) {
                        tabMode4.addRow(new Object[]{
                            i + ".",
                            list.path("noKunjungan").asText(),
                            list.path("tglKunjungan").asText(),
                            list.path("provPerujuk").path("kode").asText(),
                            list.path("provPerujuk").path("nama").asText(),
                            list.path("poliRujukan").asText(),
                            list.path("diagnosa").path("kode").asText(),
                            list.path("diagnosa").path("nama").asText(),
                            list.path("keluhan").asText(),
                            list.path("poliRujukan").path("kode").asText(),
                            list.path("pelayanan").path("kode").asText(),
                            list.path("pelayanan").path("nama").asText()
                        });
                        i++;
                    }
                }
            } else {
                JOptionPane.showMessageDialog(null, nameNode.path("message").asText() + " di Faskes 2 data rujukan banyak.");
            }
        } catch (Exception ex) {
            System.out.println("Notifikasi : " + ex);
            if (ex.toString().contains("UnknownHostException")) {
                JOptionPane.showMessageDialog(rootPane, "Koneksi ke server BPJS terputus...!");
            }
        }
    }

    private void getDataFK1() {
        if (tbFaskes1.getSelectedRow() != -1) {
            AsalRujukan.setSelectedIndex(0);
            NoRujukan.setText(tbFaskes1.getValueAt(tbFaskes1.getSelectedRow(), 1).toString());
            Valid.SetTgl(TanggalRujuk, tbFaskes1.getValueAt(tbFaskes1.getSelectedRow(), 2).toString());
            KdPpkRujukan.setText(tbFaskes1.getValueAt(tbFaskes1.getSelectedRow(), 3).toString());
            rujukanSEP.setText(tbFaskes1.getValueAt(tbFaskes1.getSelectedRow(), 4).toString());
            Sequel.cariIsi("select nama_rujukan from master_nama_rujukan where kode_faskes_bpjs=? ", NmPpkRujukan, KdPpkRujukan.getText());
//NmPpkRujukan.setText(tbFaskes1.getValueAt(tbFaskes1.getSelectedRow(), 4).toString());
            KdPenyakit.setText(tbFaskes1.getValueAt(tbFaskes1.getSelectedRow(), 6).toString());
            NmPenyakit.setText(tbFaskes1.getValueAt(tbFaskes1.getSelectedRow(), 7).toString());
            KdPoli1.setText(tbFaskes1.getValueAt(tbFaskes1.getSelectedRow(), 9).toString());
            NmPoli1.setText(tbFaskes1.getValueAt(tbFaskes1.getSelectedRow(), 5).toString());

            if (tbFaskes1.getValueAt(tbFaskes1.getSelectedRow(), 10).toString().contains("2")) {
                JenisPelayanan.setSelectedIndex(1);
            } else {
                JenisPelayanan.setSelectedIndex(0);
            }
        }
    }

    private void getDataFK2() {
        if (tbFaskes2.getSelectedRow() != -1) {
            AsalRujukan.setSelectedIndex(1);
            NoRujukan.setText(tbFaskes2.getValueAt(tbFaskes2.getSelectedRow(), 1).toString());
            Valid.SetTgl(TanggalRujuk, tbFaskes2.getValueAt(tbFaskes2.getSelectedRow(), 2).toString());
            KdPpkRujukan.setText(tbFaskes2.getValueAt(tbFaskes2.getSelectedRow(), 3).toString());
            rujukanSEP.setText(tbFaskes2.getValueAt(tbFaskes2.getSelectedRow(), 4).toString());
            Sequel.cariIsi("select nama_rujukan from master_nama_rujukan where kode_faskes_bpjs=? ", NmPpkRujukan, KdPpkRujukan.getText());
            //NmPpkRujukan.setText(tbFaskes2.getValueAt(tbFaskes2.getSelectedRow(), 4).toString());
            KdPenyakit.setText(tbFaskes2.getValueAt(tbFaskes2.getSelectedRow(), 6).toString());
            NmPenyakit.setText(tbFaskes2.getValueAt(tbFaskes2.getSelectedRow(), 7).toString());
            KdPoli1.setText(tbFaskes2.getValueAt(tbFaskes2.getSelectedRow(), 9).toString());
            NmPoli1.setText(tbFaskes2.getValueAt(tbFaskes2.getSelectedRow(), 5).toString());

            if (tbFaskes2.getValueAt(tbFaskes2.getSelectedRow(), 10).toString().contains("2")) {
                JenisPelayanan.setSelectedIndex(1);
            } else {
                JenisPelayanan.setSelectedIndex(0);
            }
        }
    }

    private void getDataFK1byk() {
        if (tbFaskes3.getSelectedRow() != -1) {
            AsalRujukan.setSelectedIndex(0);
            NoRujukan.setText(tbFaskes3.getValueAt(tbFaskes3.getSelectedRow(), 1).toString());
            Valid.SetTgl(TanggalRujuk, tbFaskes3.getValueAt(tbFaskes3.getSelectedRow(), 2).toString());
            KdPpkRujukan.setText(tbFaskes3.getValueAt(tbFaskes3.getSelectedRow(), 3).toString());
            rujukanSEP.setText(tbFaskes3.getValueAt(tbFaskes3.getSelectedRow(), 4).toString());
            Sequel.cariIsi("select nama_rujukan from master_nama_rujukan where kode_faskes_bpjs=? ", NmPpkRujukan, KdPpkRujukan.getText());
            //NmPpkRujukan.setText(tbFaskes3.getValueAt(tbFaskes3.getSelectedRow(), 4).toString());
            KdPenyakit.setText(tbFaskes3.getValueAt(tbFaskes3.getSelectedRow(), 6).toString());
            NmPenyakit.setText(tbFaskes3.getValueAt(tbFaskes3.getSelectedRow(), 7).toString());
            KdPoli1.setText(tbFaskes3.getValueAt(tbFaskes3.getSelectedRow(), 9).toString());
            NmPoli1.setText(tbFaskes3.getValueAt(tbFaskes3.getSelectedRow(), 5).toString());

            if (tbFaskes3.getValueAt(tbFaskes3.getSelectedRow(), 10).toString().contains("2")) {
                JenisPelayanan.setSelectedIndex(1);
            } else {
                JenisPelayanan.setSelectedIndex(0);
            }
        }
    }

    private void getDataFK2byk() {
        if (tbFaskes4.getSelectedRow() != -1) {
            AsalRujukan.setSelectedIndex(1);
            NoRujukan.setText(tbFaskes4.getValueAt(tbFaskes4.getSelectedRow(), 1).toString());
            Valid.SetTgl(TanggalRujuk, tbFaskes4.getValueAt(tbFaskes4.getSelectedRow(), 2).toString());
            KdPpkRujukan.setText(tbFaskes4.getValueAt(tbFaskes4.getSelectedRow(), 3).toString());
            rujukanSEP.setText(tbFaskes4.getValueAt(tbFaskes4.getSelectedRow(), 4).toString());
            Sequel.cariIsi("select nama_rujukan from master_nama_rujukan where kode_faskes_bpjs=? ", NmPpkRujukan, KdPpkRujukan.getText());
            //NmPpkRujukan.setText(tbFaskes4.getValueAt(tbFaskes4.getSelectedRow(), 4).toString());
            KdPenyakit.setText(tbFaskes4.getValueAt(tbFaskes4.getSelectedRow(), 6).toString());
            NmPenyakit.setText(tbFaskes4.getValueAt(tbFaskes4.getSelectedRow(), 7).toString());
            KdPoli1.setText(tbFaskes4.getValueAt(tbFaskes4.getSelectedRow(), 9).toString());
            NmPoli1.setText(tbFaskes4.getValueAt(tbFaskes4.getSelectedRow(), 5).toString());

            if (tbFaskes4.getValueAt(tbFaskes4.getSelectedRow(), 10).toString().contains("2")) {
                JenisPelayanan.setSelectedIndex(1);
            } else {
                JenisPelayanan.setSelectedIndex(0);
            }
        }
    }

    public void cekPPKRUJUKAN() {
        if (!NoKartu.getText().equals("")) {
            if (AsalRujukan.getSelectedIndex() == 0) {
                KdPpkRujukan.setText(cekViaBPJSKartu.provUmumkdProvider);
//                NmPpkRujukan.setText(cekViaBPJSKartu.provUmumnmProvider);
                Sequel.cariIsi("select nama_rujukan from master_nama_rujukan where kode_faskes_bpjs=? ", NmPpkRujukan, KdPpkRujukan.getText());
            } else if (AsalRujukan.getSelectedIndex() == 1) {
                KdPpkRujukan.setText(KdPPK.getText());
//                NmPpkRujukan.setText(NmPPK.getText());
                Sequel.cariIsi("select nama_rujukan from master_nama_rujukan where kode_faskes_bpjs=? ", NmPpkRujukan, KdPpkRujukan.getText());
            }
        } else if (NoKartu.getText().equals("")) {
            cekLAYAN();
        } else {
            JOptionPane.showMessageDialog(null, "Status kepesertaan tidak aktif..!!");
            btnDiagnosa.requestFocus();
        }
    }

    public void cekLAYAN() {
        if (JenisPelayanan.getSelectedItem().equals("1. Ranap")) {
            KdPoli.setText("");
            NmPoli.setText("");
            LabelPoli.setVisible(false);
            KdPoli.setVisible(false);
            NmPoli.setVisible(false);
            btnPoli.setVisible(false);

            jLabel10.setText("No. SPRI :");
            NoRujukan.setText("");
            noSKDP.setText("");
//            NoRujukan.setText(TNoRw.getText());
//            noSKDP.setText(TNoRw.getText());
            AsalRujukan.setSelectedIndex(1);
        } else {
            LabelPoli.setVisible(true);
            KdPoli.setVisible(true);
            NmPoli.setVisible(true);
            btnPoli.setVisible(true);
            jLabel10.setText("No. Surat SKDP :");
            AsalRujukan.setSelectedIndex(0);
        }
    }

    public void cekBPJS() {
        no_peserta = Sequel.cariIsi("select no_peserta from pasien where no_rkm_medis=?", TNoRM.getText());

        if (no_peserta.trim().equals("")) {
            JOptionPane.showMessageDialog(null, "Pasien tidak mempunyai kepesertaan BPJS");
            BtnBatal.requestFocus();
        } else {
            cekViaBPJSKartu.tampil(no_peserta);
            if (cekViaBPJSKartu.informasi.equals("OK")) {
                if (cekViaBPJSKartu.statusPesertaketerangan.equals("AKTIF")) {
                    KdPPK.setText(Sequel.cariIsi("select kode_ppk from setting"));
                    NmPPK.setText(Sequel.cariIsi("select nama_instansi from setting"));
                    TPasien.setText(Strings.toUpperCase(cekViaBPJSKartu.nama));
                    TglLahir.setText(cekViaBPJSKartu.tglLahir);
                    JK.setText(cekViaBPJSKartu.sex);
                    NoKartu.setText(no_peserta);
                    JenisPeserta.setText(cekViaBPJSKartu.jenisPesertaketerangan);
                    Status.setText(cekViaBPJSKartu.statusPesertaketerangan);

                    if (AsalRujukan.getSelectedIndex() == 0) {
                        KdPpkRujukan.setText(cekViaBPJSKartu.provUmumkdProvider);
                        rujukanSEP.setText(cekViaBPJSKartu.provUmumnmProvider);
                        Sequel.cariIsi("select nama_rujukan from master_nama_rujukan where kode_faskes_bpjs=? ", NmPpkRujukan, KdPpkRujukan.getText());
                        Sequel.cariIsi("select kd_rujukan from master_nama_rujukan where status='1' and kode_faskes_bpjs=? ", kode_rujukanya, KdPpkRujukan.getText());
//                            NmPpkRujukan.setText("");
                    } else if (AsalRujukan.getSelectedIndex() == 1) {
                        KdPpkRujukan.setText(KdPPK.getText());
//                            NmPpkRujukan.setText(NmPPK.getText());
                        rujukanSEP.setText(NmPPK.getText());
                        Sequel.cariIsi("select kd_rujukan from master_nama_rujukan where status='1' and kode_faskes_bpjs=? ", kode_rujukanya, KdPpkRujukan.getText());
                    }

                    if (cekViaBPJSKartu.hakKelaskode.equals("1")) {
                        Kelas.setSelectedIndex(0);
                    } else if (cekViaBPJSKartu.hakKelaskode.equals("2")) {
                        Kelas.setSelectedIndex(1);
                    } else if (cekViaBPJSKartu.hakKelaskode.equals("3")) {
                        Kelas.setSelectedIndex(2);
                    }

                    tampilNoRujukan(no_peserta);
                    NoTelp.setText(no_telp.getText());

//                    NoTelp.setText(cekViaBPJSKartu.mrnoTelepon);
//                    NoTelp.requestFocus();
                } else {
                    JOptionPane.showMessageDialog(null, "Status kepesertaan tidak aktif..!!");
                    BtnBatal.requestFocus();
                }
            } else {
                BtnBatal.requestFocus();
            }
        }
    }

    public void tampilNoRujukan(String nomorrujukan) {
        try {
            prop.loadFromXML(new FileInputStream("setting/database.xml"));
            String URL = prop.getProperty("URLAPIBPJS") + "/Rujukan/Peserta/" + nomorrujukan;

            HttpHeaders headers = new HttpHeaders();
            headers.setContentType(MediaType.APPLICATION_JSON);
            headers.add("X-Cons-ID", prop.getProperty("CONSIDAPIBPJS"));
            headers.add("X-Timestamp", String.valueOf(api.GetUTCdatetimeAsString()));
            headers.add("X-Signature", api.getHmac());

            HttpEntity requestEntity = new HttpEntity(headers);
            ObjectMapper mapper = new ObjectMapper();
            JsonNode root = mapper.readTree(api.getRest().exchange(URL, HttpMethod.GET, requestEntity, String.class).getBody());
            JsonNode nameNode = root.path("metaData");
            if (nameNode.path("code").asText().equals("200")) {
                Valid.tabelKosong(tabMode);
                JsonNode response = root.path("response").path("rujukan");
                KdPenyakit.setText(response.path("diagnosa").path("kode").asText());
                NmPenyakit.setText(response.path("diagnosa").path("nama").asText());
                NoRujukan.setText(response.path("noKunjungan").asText());
                KdPpkRujukan.setText(response.path("provPerujuk").path("kode").asText());
//                NmPpkRujukan.setText(response.path("provPerujuk").path("nama").asText());
                Valid.SetTgl(TanggalRujuk, response.path("tglKunjungan").asText());
            } else {
                JOptionPane.showMessageDialog(null, nameNode.path("message").asText() + " di Faskes 1.");
            }
        } catch (Exception ex) {
            System.out.println("Notifikasi Peserta : " + ex);
            if (ex.toString().contains("UnknownHostException")) {
                JOptionPane.showMessageDialog(rootPane, "Koneksi ke server BPJS terputus...!");
            }
        }
    }

    private void simpanBooking() {
        Sequel.menyimpan("booking_registrasi",
                "Now(),"
                + "'" + TNoRM.getText() + "',"
                + "'" + Valid.SetTgl(TanggalPeriksa.getSelectedItem() + "") + "',"
                + "'" + KdDokter.getText() + "',"
                + "'" + KdPoli.getText() + "',"
                + "'" + NoReg.getText() + "',"
                + "'" + kdboking.getText() + "',"
                + "'Menunggu',"
                + "'" + kdpnj.getText() + "',"
                + "'" + verif_data.getSelectedItem().toString() + "',"
                + "'" + no_telp.getText() + "',"
                + "'-'");
    }

    private void simpanKelengkapanSEP() {
        jasaraharja = "";
        BPJS = "";
        Taspen = "";
        Asabri = "";
        penjamin = "";

        if (ChkJasaRaharja.isSelected() == true) {
            jasaraharja = "1,";
        }
        if (ChkBPJSTenaga.isSelected() == true) {
            BPJS = "2,";
        }
        if (ChkTaspen.isSelected() == true) {
            Taspen = "3,";
        }
        if (ChkAsa.isSelected() == true) {
            Asabri = "4,";
        }

        if ((ChkJasaRaharja.isSelected() == true) || (ChkBPJSTenaga.isSelected() == true) || (ChkTaspen.isSelected() == true) || (ChkAsa.isSelected() == true)) {
            penjamin = jasaraharja + BPJS + Taspen + Asabri + penjamin;
        } else {
            penjamin = "";
        }

        if (penjamin.endsWith(",")) {
            penjamin = penjamin.substring(0, penjamin.length() - 1);
        }

        tglkkl = "0000-00-00";
        if (LakaLantas.getSelectedIndex() == 1) {
            tglkkl = Valid.SetTgl(TanggalKejadian.getSelectedItem() + "");
        }

        Sequel.menyimpan("kelengkapan_booking_sep_bpjs", ""
                + "'" + Valid.SetTgl(TanggalRujuk.getSelectedItem() + "") + "',"
                + "'" + NoRujukan.getText() + "',"
                + "'" + KdPpkRujukan.getText() + "',"
                + "'" + rujukanSEP.getText() + "',"
                + "'" + KdPPK.getText() + "',"
                + "'" + NmPPK.getText() + "',"
                + "'" + JenisPelayanan.getSelectedItem().toString().substring(0, 1) + "',"
                + "'" + Catatan.getText() + "',"
                + "'" + KdPenyakit.getText() + "',"
                + "'" + NmPenyakit.getText().replace("'", "''") + "',"
                + "'" + KdPoli1.getText() + "',"
                + "'" + NmPoli1.getText() + "',"
                + "'" + Kelas.getSelectedItem().toString().substring(0, 1) + "',"
                + "'" + LakaLantas.getSelectedItem().toString().substring(0, 1) + "',"
                + "'" + LokasiLaka.getText() + "',"
                + "'" + user + "',"
                + "'" + TNoRM.getText() + "',"
                + "'" + TPasien.getText().replace("'", "''") + "',"
                + "'" + TglLahir.getText() + "',"
                + "'" + JenisPeserta.getText() + "',"
                + "'" + JK.getText() + "',"
                + "'" + NoKartu.getText() + "',"
                + "'0000-00-00 00:00:00',"
                + "'" + AsalRujukan.getSelectedItem().toString() + "',"
                + "'" + Eksekutif.getSelectedItem().toString() + "',"
                + "'" + COB.getSelectedItem().toString() + "',"
                + "'" + penjamin + "',"
                + "'" + no_telp.getText() + "',"
                + "'" + KasusKatarak.getSelectedItem().toString() + "',"
                + "'" + tglkkl + "',"
                + "'" + Ket.getText() + "',"
                + "'" + suplesi.getSelectedItem().toString() + "',"
                + "'" + NoSEPSuplesi.getText() + "',"
                + "'" + KdProv.getText() + "',"
                + "'" + NmProv.getText() + "',"
                + "'" + KdKab.getText() + "',"
                + "'" + NmKab.getText() + "',"
                + "'" + KdKec.getText() + "',"
                + "'" + NmKec.getText() + "',"
                + "'" + noSKDP.getText() + "',"
                + "'" + Kddpjp.getText() + "',"
                + "'" + NmDPJP.getText() + "',"
                + "'',"
                + "'" + kdboking.getText() + "',"
                + "'BELUM',"
                + "'" + NmPpkRujukan.getText() + "',"
                + "'-'");

        Sequel.mengedit("pasien", "no_rkm_medis='" + TNoRM.getText() + "'", "no_tlp='" + no_telp.getText() + "' ");
    }

    private void cekPasien() {
        if (TNoRM.getText().trim().equals("")) {
            Valid.textKosong(TNoRM, "pasien");
            TNoRM.requestFocus();
        } else if (!TNoRM.getText().equals("")) {
            Sequel.cariIsi("select nm_pasien from pasien where no_rkm_medis=? ", TPasien, TNoRM.getText());

            if (TPasien.getText().equals("")) {
                JOptionPane.showMessageDialog(null, "Data pasien tidak ditemukan...");
                TPasien.setText("");
                TNoRM.requestFocus();
            } else if (!TPasien.getText().equals("")) {
                Sequel.cariIsi("select nm_pasien from pasien where no_rkm_medis=? ", TPasien, TNoRM.getText());
                Sequel.cariIsi("select jk from pasien where no_rkm_medis=? ", jk, TNoRM.getText());
                Sequel.cariIsi("select no_rkm_medis from pasien_mati where no_rkm_medis=? ", rmMati, TNoRM.getText());

                Sequel.cariIsi("select suku_bangsa from pasien where no_rkm_medis=? ", kdsuku, TNoRM.getText());
                Sequel.cariIsi("select bahasa_pasien from pasien where no_rkm_medis=? ", kdbahasa, TNoRM.getText());
                Sequel.cariIsi("select nama_suku_bangsa from suku_bangsa where id=?", nmsuku, kdsuku.getText());
                Sequel.cariIsi("select nama_bahasa from bahasa_pasien where id=?", nmbahasa, kdbahasa.getText());
                BtnPoli.requestFocus();
            }
        }
    }

    public void emptKelengkapanSEP() {
        kode_rujukanya.setText("");
        nmfaskes_keluar.setText("");
        LokasiLaka.setText("");

        TglLahir.setText("");
        JenisPeserta.setText("");
        JK.setText("");
        KdPPK.setText("");
        NmPPK.setText("");
        NoKartu.setText("");
        Status.setText("");
        NoRujukan.setText("");
        noSKDP.setText("");
        rujukanSEP.setText("- belum terisi -");
        Kddpjp.setText("");
        NmDPJP.setText("");
        KdPpkRujukan.setText("");
        NmPpkRujukan.setText("");
        KasusKatarak.setSelectedIndex(0);
        NoTelp.setText("");
        KdPenyakit.setText("");
        NmPenyakit.setText("");
        JenisPelayanan.setSelectedIndex(1);
        Kelas.setSelectedIndex(0);
        KdPoli1.setText("");
        NmPoli1.setText("");
        TanggalRujuk.setDate(new Date());
        Eksekutif.setSelectedIndex(0);
        COB.setSelectedIndex(0);
        Catatan.setText("Registrasi pasien dengan mendaftar online");
        label_cetak.setVisible(false);
        statusSEP.setVisible(false);

        LakaLantas.setSelectedIndex(0);
        TanggalKejadian.setDate(new Date());
        ChkJasaRaharja.setSelected(false);
        ChkBPJSTenaga.setSelected(false);
        ChkTaspen.setSelected(false);
        ChkAsa.setSelected(false);
        Ket.setText("");
        KdProv.setText("");
        NmProv.setText("");
        KdKab.setText("");
        NmKab.setText("");
        KdKec.setText("");
        NmKec.setText("");
        suplesi.setSelectedIndex(0);
        NoSEPSuplesi.setText("");
    }

    private void gantiDataBooking() {
        Sequel.mengedit("booking_registrasi", "kd_booking='" + kdboking.getText() + "'",
                "tanggal_periksa='" + Valid.SetTgl(TanggalPeriksa.getSelectedItem() + "") + "', "
                + "kd_dokter='" + KdDokter.getText() + "', "
                + "kd_poli='" + KdPoli.getText() + "', "
                + "kd_pj='" + kdpnj.getText() + "', "
                + "data_dari='" + verif_data.getSelectedItem().toString() + "', "
                + "no_telp_pemesan='" + no_telp.getText() + "' ");
    }

    private void gantiDataSEP() {
        jasaraharja = "";
        BPJS = "";
        Taspen = "";
        Asabri = "";
        penjamin = "";

        if (ChkJasaRaharja.isSelected() == true) {
            jasaraharja = "1,";
        }
        if (ChkBPJSTenaga.isSelected() == true) {
            BPJS = "2,";
        }
        if (ChkTaspen.isSelected() == true) {
            Taspen = "3,";
        }
        if (ChkAsa.isSelected() == true) {
            Asabri = "4,";
        }

        if ((ChkJasaRaharja.isSelected() == true) || (ChkBPJSTenaga.isSelected() == true) || (ChkTaspen.isSelected() == true) || (ChkAsa.isSelected() == true)) {
            penjamin = jasaraharja + BPJS + Taspen + Asabri + penjamin;
        } else {
            penjamin = "";
        }

        if (penjamin.endsWith(",")) {
            penjamin = penjamin.substring(0, penjamin.length() - 1);
        }

        tglkkl = "0000-00-00";
        if (LakaLantas.getSelectedIndex() == 1) {
            tglkkl = Valid.SetTgl(TanggalKejadian.getSelectedItem() + "");
        }

        Sequel.mengedit("kelengkapan_booking_sep_bpjs", "kd_booking='" + kdboking.getText() + "'",
                "tglrujukan='" + Valid.SetTgl(TanggalRujuk.getSelectedItem() + "") + "', "
                + "no_rujukan='" + NoRujukan.getText() + "',"
                + "kdppkrujukan='" + KdPpkRujukan.getText() + "', "
                + "nmppkrujukan='" + rujukanSEP.getText() + "',"
                + "kdppkpelayanan='" + KdPPK.getText() + "',"
                + "nmppkpelayanan='" + NmPPK.getText() + "',"
                + "jnspelayanan='" + JenisPelayanan.getSelectedItem().toString().substring(0, 1) + "',"
                + "catatan='" + Catatan.getText() + "',"
                + "diagawal='" + KdPenyakit.getText() + "',"
                + "nmdiagnosaawal='" + NmPenyakit.getText().replace("'", "''") + "',"
                + "kdpolitujuan='" + KdPoli1.getText() + "',"
                + "nmpolitujuan='" + NmPoli1.getText() + "',"
                + "klsrawat='" + Kelas.getSelectedItem().toString().substring(0, 1) + "',"
                + "lakalantas='" + LakaLantas.getSelectedItem().toString().substring(0, 1) + "',"
                + "lokasilaka='" + LokasiLaka.getText() + "',"
                + "user='" + user + "',"
                + "nomr='" + TNoRM.getText() + "',"
                + "nama_pasien='" + TPasien.getText().replace("'", "''") + "',"
                + "tanggal_lahir='" + TglLahir.getText() + "',"
                + "peserta='" + JenisPeserta.getText() + "',"
                + "jkel='" + JK.getText() + "',"
                + "no_kartu='" + NoKartu.getText() + "',"
                + "asal_rujukan='" + AsalRujukan.getSelectedItem().toString() + "',"
                + "eksekutif='" + Eksekutif.getSelectedItem().toString() + "',"
                + "cob='" + COB.getSelectedItem().toString() + "',"
                + "penjamin='" + penjamin + "',"
                + "notelep='" + no_telp.getText() + "',"
                + "katarak='" + KasusKatarak.getSelectedItem().toString() + "',"
                + "tglkkl='" + tglkkl + "',"
                + "keterangankkl='" + Ket.getText() + "',"
                + "suplesi='" + suplesi.getSelectedItem().toString() + "',"
                + "no_sep_suplesi='" + NoSEPSuplesi.getText() + "',"
                + "kdprop='" + KdProv.getText() + "',"
                + "nmprop='" + NmProv.getText() + "',"
                + "kdkab='" + KdKab.getText() + "',"
                + "nmkab='" + NmKab.getText() + "',"
                + "kdkec='" + KdKec.getText() + "',"
                + "nmkec='" + NmKec.getText() + "',"
                + "noskdp='" + noSKDP.getText() + "',"
                + "kddpjp='" + Kddpjp.getText() + "',"
                + "nmdpdjp='" + NmDPJP.getText() + "',"
                + "rujukan_masuknya='" + NmPpkRujukan.getText() + "' ");

        Sequel.mengedit("pasien", "no_rkm_medis='" + TNoRM.getText() + "'", "no_tlp='" + no_telp.getText() + "'");
    }

    private void cekDataKelengkapan() {
        try {
            pskelengkapanBPJS = koneksi.prepareStatement("select tanggal_lahir, peserta, jkel, kdppkpelayanan, nmppkpelayanan, no_kartu, 'AKTIF' status, "
                    + "no_rujukan, tglrujukan, noskdp, katarak, asal_rujukan, nmppkrujukan, kddpjp, nmdpdjp, kdppkrujukan, notelep, diagawal, "
                    + "nmdiagnosaawal, IF (klsrawat = '1','1. Kelas 1',IF (klsrawat = '2','2. Kelas 2','3. Kelas 3')) kls_rwt, kdpolitujuan, nmpolitujuan, eksekutif, "
                    + "cob, catatan, IF (lakalantas = '0','0. Tidak','1. Ya') kasus_laka, keterangankkl, kdprop, nmprop, kdkab, nmkab, kdkec, nmkec, suplesi, "
                    + "no_sep_suplesi, status_cetak_sep from kelengkapan_booking_sep_bpjs where kd_booking=?");
            try {
                pskelengkapanBPJS.setString(1, kdboking.getText());
                rskelengkapanBPJS = pskelengkapanBPJS.executeQuery();
                while (rskelengkapanBPJS.next()) {
                    TglLahir.setText(rskelengkapanBPJS.getString("tanggal_lahir"));
                    JenisPeserta.setText(rskelengkapanBPJS.getString("peserta"));
                    JK.setText(rskelengkapanBPJS.getString("jkel"));
                    KdPPK.setText(rskelengkapanBPJS.getString("kdppkpelayanan"));
                    NmPPK.setText(rskelengkapanBPJS.getString("nmppkpelayanan"));
                    NoKartu.setText(rskelengkapanBPJS.getString("no_kartu"));
                    Status.setText(rskelengkapanBPJS.getString("status"));
                    NoRujukan.setText(rskelengkapanBPJS.getString("no_rujukan"));
                    Valid.SetTgl(TanggalRujuk, rskelengkapanBPJS.getString("tglrujukan"));
                    noSKDP.setText(rskelengkapanBPJS.getString("noskdp"));
                    KasusKatarak.setSelectedItem(rskelengkapanBPJS.getString("katarak"));
                    AsalRujukan.setSelectedItem(rskelengkapanBPJS.getString("asal_rujukan"));
                    rujukanSEP.setText(rskelengkapanBPJS.getString("nmppkrujukan"));
                    Kddpjp.setText(rskelengkapanBPJS.getString("kddpjp"));
                    NmDPJP.setText(rskelengkapanBPJS.getString("nmdpdjp"));
                    KdPpkRujukan.setText(rskelengkapanBPJS.getString("kdppkrujukan"));
                    NoTelp.setText(rskelengkapanBPJS.getString("notelep"));
                    KdPenyakit.setText(rskelengkapanBPJS.getString("diagawal"));
                    NmPenyakit.setText(rskelengkapanBPJS.getString("nmdiagnosaawal"));
                    Kelas.setSelectedItem(rskelengkapanBPJS.getString("kls_rwt"));
                    KdPoli1.setText(rskelengkapanBPJS.getString("kdpolitujuan"));
                    NmPoli1.setText(rskelengkapanBPJS.getString("nmpolitujuan"));
                    Eksekutif.setSelectedItem(rskelengkapanBPJS.getString("eksekutif"));
                    COB.setSelectedItem(rskelengkapanBPJS.getString("cob"));
                    Catatan.setText(rskelengkapanBPJS.getString("catatan"));
                    LakaLantas.setSelectedItem(rskelengkapanBPJS.getString("kasus_laka"));
                    Ket.setText(rskelengkapanBPJS.getString("keterangankkl"));
                    KdProv.setText(rskelengkapanBPJS.getString("kdprop"));
                    NmProv.setText(rskelengkapanBPJS.getString("nmprop"));
                    KdKab.setText(rskelengkapanBPJS.getString("kdkab"));
                    NmKab.setText(rskelengkapanBPJS.getString("nmkab"));
                    KdKec.setText(rskelengkapanBPJS.getString("kdkec"));
                    NmKec.setText(rskelengkapanBPJS.getString("nmkec"));
                    suplesi.setSelectedItem(rskelengkapanBPJS.getString("suplesi"));
                    NoSEPSuplesi.setText(rskelengkapanBPJS.getString("no_sep_suplesi"));
                    statusSEP.setText(rskelengkapanBPJS.getString("status_cetak_sep"));
                }
            } catch (Exception ex) {
                System.out.println(ex);
            } finally {
                if (rskelengkapanBPJS != null) {
                    rskelengkapanBPJS.close();
                }

                if (pskelengkapanBPJS != null) {
                    pskelengkapanBPJS.close();
                }
            }
        } catch (Exception e) {
            System.out.println(e);
        }
    }

}