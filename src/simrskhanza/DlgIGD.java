/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

 /*
 * DlgReg.java
 *
 * Created on Jun 8, 2010, choose Tools | Templates
 * and open the template in10:28:56 PM
 */
package simrskhanza;

import inventory.DlgResepObat;
import inventory.DlgPemberianObat;
import bridging.BPJSDataSEP;
import bridging.CoronaPasien;
import bridging.DlgSKDPBPJS;
import bridging.INACBGPerawatanCorona;
import bridging.InhealthDataSJP;
import bridging.SisruteRujukanKeluar;
import laporan.DlgFrekuensiPenyakitRalan;
import keuangan.DlgBilingRalan;
import fungsi.WarnaTable;
import fungsi.batasInput;
import grafikanalisa.grafikperiksaperagama;
import grafikanalisa.grafikperiksaperbulan;
import grafikanalisa.grafikperiksaperdokter;
import grafikanalisa.grafikperiksaperhari;
import grafikanalisa.grafikperiksaperjk;
import grafikanalisa.grafikperiksaperpekerjaan;
import grafikanalisa.grafikperiksaperpoli;
import grafikanalisa.grafikperiksapertahun;
import grafikanalisa.grafiksql;
import fungsi.koneksiDB;
import fungsi.sekuel;
import fungsi.validasi;
import fungsi.var;
import inventory.DlgPeresepanDokter;
import java.awt.Cursor;
import java.awt.Dimension;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.event.WindowEvent;
import java.awt.event.WindowListener;
import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.Writer;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import java.util.Properties;
import javax.swing.JButton;
import javax.swing.JOptionPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.Timer;
import javax.swing.event.DocumentEvent;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableColumn;
import keuangan.DlgLhtPiutang;
import laporan.DlgDataHAIs;
import laporan.DlgDiagnosaPenyakit;
import permintaan.DlgPermintaanLaboratorium;
import permintaan.DlgPermintaanRadiologi;
import rekammedis.RMPenilaianAwalKeperawatanRalan;
import rekammedis.RMTriaseIGD;

/**
 *
 * @author dosen
 */
public final class DlgIGD extends javax.swing.JDialog {

    private final DefaultTableModel tabMode, tabMode1;
    private sekuel Sequel = new sekuel();
    private validasi Valid = new validasi();
    private Connection koneksi = koneksiDB.condb();
    public DlgPasien pasien = new DlgPasien(null, false);
    public DlgCariDokter dokter = new DlgCariDokter(null, false);
    public DlgRujukMasuk rujukmasuk = new DlgRujukMasuk(null, false);
    public DlgBahasa bahasa = new DlgBahasa(null, false);
    public DlgSuku suku = new DlgSuku(null, false);
    public DlgKabupaten kab = new DlgKabupaten(null, false);
    public DlgKecamatan kec = new DlgKecamatan(null, false);
    public DlgKelurahan kel = new DlgKelurahan(null, false);
    private PreparedStatement ps, ps2, ps3, pscaripiutang, psAPS, psK;
    private ResultSet rs, rsAPS, rsK;
    private int pilihan = 0, i = 0, cekRujuk = 0, x, cekjampersal = 0, cekjamkesda = 0, cekinap = 0, 
            cekNRWSurat = 0, diagnosa_cek = 0, cekUmur = 0;
    private Date cal = new Date();
    private Properties prop = new Properties();
    private SimpleDateFormat dateformat = new SimpleDateFormat("yyyy/MM/dd");
    private double biaya = 0, cek = 0;
    private String nosisrute = "", URUTNOREG = "", alamatperujuk = "-", umur = "0", sttsumur = "Th",
            kelaminPJ = "", IPPRINTERTRACER = "", norawatAPS = "", kdAPS = "", cekSurat = "", sttsumur1 = "",
            cekAPS = "", kdKel = "", kdKec = "", kdKab = "", diagnosa_ok = "", tglDaftar = "", tglnoRW = "",
            validasiregistrasi = Sequel.cariIsi("select wajib_closing_kasir from set_validasi_registrasi");
    private char ESC = 27;
    // ganti kertas
    private char[] FORM_FEED = {12};
    // reset setting
    private char[] RESET = {ESC, '@'};
    // huruf tebal diaktifkan
    private char[] BOLD_ON = {ESC, 'E'};
    // huruf tebal dimatikan
    private char[] BOLD_OFF = {ESC, 'F'};
    // huruf miring diaktifkan
    private char[] ITALIC_ON = {ESC, '4'};
    // huruf miring dimatikan
    private char[] ITALIC_OFF = {ESC, '5'};
    // mode draft diaktifkan
    private char[] MODE_DRAFT = {ESC, 'x', 0};
    private char[] MODE_NLQ = {ESC, 'x', 1};
    // font Roman (halaman 47)
    private char[] FONT_ROMAN = {ESC, 'k', 0};
    // font Sans serif
    private char[] FONT_SANS_SERIF = {ESC, 'k', 1};
    // font size (halaman 49)
    private char[] SIZE_5_CPI = {ESC, 'W', '1', ESC, 'P'};
    private char[] SIZE_6_CPI = {ESC, 'W', '1', ESC, 'M'};
    private char[] SIZE_10_CPI = {ESC, 'P'};
    private char[] SIZE_12_CPI = {ESC, 'M'};
    //font height
    private char[] HEIGHT_NORMAL = {ESC, 'w', '0'};
    private char[] HEIGHT_DOUBLE = {ESC, 'w', '1'};
    // double strike (satu dot dicetak 2 kali)
    private char[] DOUBLE_STRIKE_ON = {ESC, 'G'};
    private char[] DOUBLE_STRIKE_OFF = {ESC, 'H'};
    // http://www.berklix.com/~jhs/standards/escapes.epson
    // condensed (huruf kurus)
    private char[] CONDENSED_ON = {15};
    private char[] CONDENSED_OFF = {18};
    // condensed (huruf gemuk)
    private char[] ENLARGED_ON = {(char) 14};
    private char[] ENLARGED_OFF = {(char) 20};
    // line spacing
    private char[] SPACING_9_72 = {ESC, '0'};
    private char[] SPACING_7_72 = {ESC, '1'};
    private char[] SPACING_12_72 = {ESC, '2'};
    // set unit for margin setting
    private char[] UNIT_1_360 = {ESC, 40, 'U', '1', '0'};
    // move vertical print position
    private char[] VERTICAL_PRINT_POSITION = {ESC, 'J', '1'};

    /**
     * Creates new form DlgReg
     *
     * @param parent
     * @param modal
     */
    public DlgIGD(java.awt.Frame parent, boolean modal) {
        super(parent, modal);
        initComponents();
        
        this.setLocation(8, 1);
        setSize(885, 674);

        Object[] row = {"P", "No. Reg", "No. Rawat", "Tanggal", "Jam", "Kd.Dokter", "Dokter Dituju", "Nomer RM", "Nama Pasien", "J.K.", 
            "Umur", "Jenis Bayar", "No. SEP BPJS","Stts Transaksi", "Jns. Pasien", "Poliklinik", "Penanggung Jawab", "Alamat P.J.", "Hubungan dg P.J.", "Biaya Regristrasi", 
            "Dtg. Sendiri","Trauma", "Non Trauma","Tindkn. Lanjut","Ket.","umur_daftar"};
        tabMode = new DefaultTableModel(null, row) {
            @Override
            public boolean isCellEditable(int rowIndex, int colIndex) {
                boolean a = false;
                if (colIndex == 0) {
                    a = true;
                }
                return a;
            }
            Class[] types = new Class[]{
                java.lang.Boolean.class, java.lang.Object.class, java.lang.Object.class, java.lang.Object.class,
                java.lang.Object.class, java.lang.Object.class, java.lang.Object.class, java.lang.Object.class,
                java.lang.Object.class, java.lang.Object.class, java.lang.Object.class, java.lang.Object.class,
                java.lang.Object.class, java.lang.Object.class, java.lang.Object.class, java.lang.Object.class,
                java.lang.Object.class, java.lang.Object.class, java.lang.Object.class, java.lang.Object.class,
                java.lang.Object.class, java.lang.Object.class, java.lang.Object.class, java.lang.Object.class,
                java.lang.Object.class, java.lang.Object.class
            };

            @Override
            public Class getColumnClass(int columnIndex) {
                return types[columnIndex];
            }
        };
        
        tbregistrasiIGD.setModel(tabMode);
        tbregistrasiIGD.setPreferredScrollableViewportSize(new Dimension(800, 800));
        tbregistrasiIGD.setAutoResizeMode(JTable.AUTO_RESIZE_OFF);

        for (i = 0; i < 26; i++) {
            TableColumn column = tbregistrasiIGD.getColumnModel().getColumn(i);
            if (i == 0) {
                column.setPreferredWidth(20);
            } else if (i == 1) {
                column.setPreferredWidth(50);
            } else if (i == 2) {
                column.setPreferredWidth(120);
            } else if (i == 3) {
                column.setPreferredWidth(70);
            } else if (i == 4) {
                column.setPreferredWidth(50);
            } else if (i == 5) {//sembunyi
                //column.setPreferredWidth(70);
                column.setMinWidth(0);
                column.setMaxWidth(0);
            } else if (i == 6) {
                column.setPreferredWidth(200);
            } else if (i == 7) {
                column.setPreferredWidth(70);
            } else if (i == 8) {
                column.setPreferredWidth(200);
            } else if (i == 9) {
                column.setPreferredWidth(30);
            } else if (i == 10) {
                column.setPreferredWidth(50);
            } else if (i == 11) {
                column.setPreferredWidth(105);
            } else if (i == 12) {
                column.setPreferredWidth(130);
            } else if (i == 13) {
                column.setPreferredWidth(80);
            } else if (i == 14) {
                column.setPreferredWidth(60);
            } else if (i == 15) {//sembunyi
                //column.setPreferredWidth(200);
                column.setMinWidth(0);
                column.setMaxWidth(0);
            } else if (i == 16) {
                column.setPreferredWidth(150);
            } else if (i == 17) {
//                column.setPreferredWidth(200);
                column.setMinWidth(0);
                column.setMaxWidth(0);
            } else if (i == 18) {
//                column.setPreferredWidth(100);
                column.setMinWidth(0);
                column.setMaxWidth(0);
            } else if (i == 19) {//sembunyi
                //column.setPreferredWidth(70);
                column.setMinWidth(0);
                column.setMaxWidth(0);
            } else if (i == 20) {
                column.setPreferredWidth(70);
            } else if (i == 21) {
                column.setPreferredWidth(80);
            } else if (i == 22) {
                column.setPreferredWidth(95);
            } else if (i == 23) {
                column.setPreferredWidth(100);
            } else if (i == 24) {
                column.setPreferredWidth(70);
            } else if (i == 25) {
                column.setMinWidth(0);
                column.setMaxWidth(0);
            }
        }
        tbregistrasiIGD.setDefaultRenderer(Object.class, new WarnaTable());
        
        tabMode1 = new DefaultTableModel(null, new Object[]{"Kode Alasan","Jenis Alasan APS"}) {
            @Override
            public boolean isCellEditable(int rowIndex, int colIndex) {
                return false;
            }
        };
        tbAPS.setModel(tabMode1);
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

        TNoReg.setDocument(new batasInput((byte) 8).getKata(TNoReg));
        TNoRw.setDocument(new batasInput((byte) 17).getKata(TNoRw));
        TNoID.setDocument(new batasInput((byte) 10).getKata(TNoID));
        kddokter.setDocument(new batasInput((byte) 20).getKata(kddokter));
        kdpnj.setDocument(new batasInput((byte) 3).getKata(kdpnj));
        TPngJwb.setDocument(new batasInput((byte) 30).getKata(TPngJwb));
        AsalRujukan.setDocument(new batasInput((byte) 60).getKata(AsalRujukan));
        THbngn.setDocument(new batasInput((byte) 20).getKata(THbngn));
        TAlmt.setDocument(new batasInput((byte) 60).getKata(TAlmt));
        TCari.setDocument(new batasInput((byte) 100).getKata(TCari));
        umurPJ.setDocument(new batasInput((byte) 3).getOnlyAngka(umurPJ));
        alamatPJ.setDocument(new batasInput((int) 200).getKata(alamatPJ));
        nmPJ.setDocument(new batasInput((int) 200).getKata(nmPJ));
        
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

                private void tampil() {
                    throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
                }
            });
        }

        ChkInput.setSelected(false);
        isForm();

        pasien.addWindowListener(new WindowListener() {
            @Override
            public void windowOpened(WindowEvent e) {
            }

            @Override
            public void windowClosing(WindowEvent e) {
            }

            @Override
            public void windowClosed(WindowEvent e) {
                if (var.getform().equals("DlgIGD")) {
                    if (pasien.getTable().getSelectedRow() != -1) {
                        if (validasiregistrasi.equals("Yes")) {
                            if (Sequel.cariInteger("select count(no_rkm_medis) from reg_periksa where no_rkm_medis=? and (stts='Sudah' or stts='Belum') ", pasien.getTable().getValueAt(pasien.getTable().getSelectedRow(), 1).toString()) > 0) {
                                JOptionPane.showMessageDialog(rootPane, "Pasien ini pada kunjungan sebelumnya memiliki tagihan yang belum diselesaikan. Silahkan konfirmasi dengan pihak kasir.. !!");
                            } else {
                                TNoID.setText(pasien.getTable().getValueAt(pasien.getTable().getSelectedRow(), 1).toString());
                                TNoRM.setText(pasien.getTable().getValueAt(pasien.getTable().getSelectedRow(), 1).toString());
                                JnsnoID.setSelectedIndex(0);
                                kdsuku.setText(pasien.getTable().getValueAt(pasien.getTable().getSelectedRow(), 26).toString());
                                kdbahasa.setText(pasien.getTable().getValueAt(pasien.getTable().getSelectedRow(), 27).toString());
                                Sequel.cariIsi("select nama_suku_bangsa from suku_bangsa where id=?", nmsuku, kdsuku.getText());
                                Sequel.cariIsi("select nama_bahasa from bahasa_pasien where id=?", nmbahasa, kdbahasa.getText());
                                isPas();
                                isNumber();
                            }
                        } else {
                            TNoID.setText(pasien.getTable().getValueAt(pasien.getTable().getSelectedRow(), 1).toString());
                            TNoRM.setText(pasien.getTable().getValueAt(pasien.getTable().getSelectedRow(), 1).toString());
                            JnsnoID.setSelectedIndex(0);
                            kdsuku.setText(pasien.getTable().getValueAt(pasien.getTable().getSelectedRow(), 26).toString());
                            kdbahasa.setText(pasien.getTable().getValueAt(pasien.getTable().getSelectedRow(), 27).toString());
                            Sequel.cariIsi("select nama_suku_bangsa from suku_bangsa where id=?", nmsuku, kdsuku.getText());
                            Sequel.cariIsi("select nama_bahasa from bahasa_pasien where id=?", nmbahasa, kdbahasa.getText());
                            isPas();
                            isNumber();
                        }
                    }
                    TNoID.requestFocus();
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
        
        kel.addWindowListener(new WindowListener() {
            @Override
            public void windowOpened(WindowEvent e) {
            }

            @Override
            public void windowClosing(WindowEvent e) {
            }

            @Override
            public void windowClosed(WindowEvent e) {
                if (var.getform().equals("DlgIGD")) {
                    kdKel = "";
                    if (kel.getTable().getSelectedRow() != -1) {
                        nmkel.setText(kel.getTable().getValueAt(kel.getTable().getSelectedRow(), 0).toString());
                        kdKel = Sequel.cariIsi("select kd_kel from kelurahan where nm_kel='" + nmkel.getText() + "'");
                        BtnKecamatan.requestFocus();
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
        
        kec.addWindowListener(new WindowListener() {
            @Override
            public void windowOpened(WindowEvent e) {
            }

            @Override
            public void windowClosing(WindowEvent e) {
            }

            @Override
            public void windowClosed(WindowEvent e) {
                if (var.getform().equals("DlgIGD")) {
                    kdKec = "";
                    if (kec.getTable().getSelectedRow() != -1) {
                        nmkec.setText(kec.getTable().getValueAt(kec.getTable().getSelectedRow(), 0).toString());
                        kdKec = Sequel.cariIsi("select kd_kec from kecamatan where nm_kec='" + nmkec.getText() + "'");
                        btnKab.requestFocus();
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
        
        kab.addWindowListener(new WindowListener() {
            @Override
            public void windowOpened(WindowEvent e) {
            }

            @Override
            public void windowClosing(WindowEvent e) {
            }

            @Override
            public void windowClosed(WindowEvent e) {
                if (var.getform().equals("DlgIGD")) {
                    kdKab = "";
                    if (kab.getTable().getSelectedRow() != -1) {
                        nmkab.setText(kab.getTable().getValueAt(kab.getTable().getSelectedRow(), 0).toString());
                        kdKab = Sequel.cariIsi("select kd_kab from kabupaten where nm_kab='" + nmkab.getText() + "'");
                        hubungan.requestFocus();
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

        pasien.getTable().addKeyListener(new KeyListener() {
            @Override
            public void keyTyped(KeyEvent e) {
            }

            @Override
            public void keyPressed(KeyEvent e) {
                if (var.getform().equals("DlgIGD")) {
                    if (e.getKeyCode() == KeyEvent.VK_SPACE) {
                        pasien.dispose();
                    }
                }
            }

            @Override
            public void keyReleased(KeyEvent e) {
            }
        });
        
        suku.addWindowListener(new WindowListener() {
            @Override
            public void windowOpened(WindowEvent e) {}
            @Override
            public void windowClosing(WindowEvent e) {}
            @Override
            public void windowClosed(WindowEvent e) {
                if(var.getform().equals("DlgIGD")){
                    if(suku.getTable().getSelectedRow()!= -1){
                        kdsuku.setText(suku.getTable().getValueAt(suku.getTable().getSelectedRow(),0).toString());
                        nmsuku.setText(suku.getTable().getValueAt(suku.getTable().getSelectedRow(),1).toString());
                        BtnBahasa.requestFocus();
                    }                      
                }
            }
            @Override
            public void windowIconified(WindowEvent e) {}
            @Override
            public void windowDeiconified(WindowEvent e) {}
            @Override
            public void windowActivated(WindowEvent e) {}
            @Override
            public void windowDeactivated(WindowEvent e) {}
        });
        
        suku.getTable().addKeyListener(new KeyListener() {
            @Override
            public void keyTyped(KeyEvent e) {}
            @Override
            public void keyPressed(KeyEvent e) {
                if(var.getform().equals("DlgIGD")){
                    if(e.getKeyCode()==KeyEvent.VK_SPACE){
                        suku.dispose();
                    }                
                }
            }
            @Override
            public void keyReleased(KeyEvent e) {}
        });
        
        bahasa.addWindowListener(new WindowListener() {
            @Override
            public void windowOpened(WindowEvent e) {}
            @Override
            public void windowClosing(WindowEvent e) {}
            @Override
            public void windowClosed(WindowEvent e) {
                if(var.getform().equals("DlgIGD")){
                    if(bahasa.getTable().getSelectedRow()!= -1){
                        kdbahasa.setText(bahasa.getTable().getValueAt(bahasa.getTable().getSelectedRow(),0).toString());
                        nmbahasa.setText(bahasa.getTable().getValueAt(bahasa.getTable().getSelectedRow(),1).toString());
                        BtnSimpan.requestFocus();
                    } 
                }
            }
            @Override
            public void windowIconified(WindowEvent e) {}
            @Override
            public void windowDeiconified(WindowEvent e) {}
            @Override
            public void windowActivated(WindowEvent e) {}
            @Override
            public void windowDeactivated(WindowEvent e) {}
        });
        
        bahasa.getTable().addKeyListener(new KeyListener() {
            @Override
            public void keyTyped(KeyEvent e) {}
            @Override
            public void keyPressed(KeyEvent e) {
                if(var.getform().equals("DlgIGD")){
                    if(e.getKeyCode()==KeyEvent.VK_SPACE){
                        bahasa.dispose();
                    }                
                }
            }
            @Override
            public void keyReleased(KeyEvent e) {}
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
                if (var.getform().equals("DlgReg")) {
                    if (rujukmasuk.tbPerujuk.getSelectedRow() != -1) {
                        AsalRujukan.setText(rujukmasuk.tbPerujuk.getValueAt(rujukmasuk.tbPerujuk.getSelectedRow(), 2).toString());
                        alamatperujuk = rujukmasuk.tbPerujuk.getValueAt(rujukmasuk.tbPerujuk.getSelectedRow(), 3).toString();
                        kode_rujukanya.setText(rujukmasuk.tbPerujuk.getValueAt(rujukmasuk.tbPerujuk.getSelectedRow(), 4).toString());
                    }
                    AsalRujukan.requestFocus();
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

        dokter.addWindowListener(new WindowListener() {
            @Override
            public void windowOpened(WindowEvent e) {;
            }

            @Override
            public void windowClosing(WindowEvent e) {
            }

            @Override
            public void windowClosed(WindowEvent e) {
                if (var.getform().equals("DlgIGD")) {
                    if (dokter.getTable().getSelectedRow() != -1) {
                        kddokter.setText(dokter.getTable().getValueAt(dokter.getTable().getSelectedRow(), 0).toString());
                        TDokter.setText(dokter.getTable().getValueAt(dokter.getTable().getSelectedRow(), 1).toString());
                        isNumber();
                        kddokter.requestFocus();
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

        pasien.kab.addWindowListener(new WindowListener() {
            @Override
            public void windowOpened(WindowEvent e) {
            }

            @Override
            public void windowClosing(WindowEvent e) {
            }

            @Override
            public void windowClosed(WindowEvent e) {
                if (var.getform().equals("DlgIGD")) {
                    if (pasien.kab.getTable().getSelectedRow() != -1) {
                        Kabupaten2.setText(pasien.kab.getTable().getValueAt(pasien.kab.getTable().getSelectedRow(), 0).toString());
                    }
                    Kabupaten2.requestFocus();
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

        pasien.kec.addWindowListener(new WindowListener() {
            @Override
            public void windowOpened(WindowEvent e) {
            }

            @Override
            public void windowClosing(WindowEvent e) {
            }

            @Override
            public void windowClosed(WindowEvent e) {
                if (var.getform().equals("DlgIGD")) {
                    if (pasien.kec.getTable().getSelectedRow() != -1) {
                        Kecamatan2.setText(pasien.kec.getTable().getValueAt(pasien.kec.getTable().getSelectedRow(), 0).toString());
                    }
                    Kecamatan2.requestFocus();
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

        pasien.kel.addWindowListener(new WindowListener() {
            @Override
            public void windowOpened(WindowEvent e) {
            }

            @Override
            public void windowClosing(WindowEvent e) {
            }

            @Override
            public void windowClosed(WindowEvent e) {
                if (var.getform().equals("DlgIGD")) {
                    if (pasien.kel.getTable().getSelectedRow() != -1) {
                        Kelurahan2.setText(pasien.kel.getTable().getValueAt(pasien.kel.getTable().getSelectedRow(), 0).toString());
                    }
                    Kelurahan2.requestFocus();
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

        pasien.penjab.addWindowListener(new WindowListener() {
            @Override
            public void windowOpened(WindowEvent e) {
            }

            @Override
            public void windowClosing(WindowEvent e) {
            }

            @Override
            public void windowClosed(WindowEvent e) {
                if (var.getform().equals("DlgIGD")) {
                    if (pasien.penjab.getTable().getSelectedRow() != -1) {
                        kdpnj.setText(pasien.penjab.getTable().getValueAt(pasien.penjab.getTable().getSelectedRow(), 1).toString());
                        nmpnj.setText(pasien.penjab.getTable().getValueAt(pasien.penjab.getTable().getSelectedRow(), 2).toString());
                    }
                    kdpnj.requestFocus();
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
                if (var.getform().equals("DlgIGD")) {
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
            ps = koneksi.prepareStatement("SELECT r.no_reg, r.no_rawat, r.tgl_registrasi, r.jam_reg, r.kd_dokter, "
                    + " d.nm_dokter, r.no_rkm_medis, p.nm_pasien, p.jk, concat(r.umurdaftar,' ',r.sttsumur) AS umur, "
                    + " pj.png_jawab, r.stts, r.stts_daftar, pl.nm_poli, r.p_jawab, r.almt_pj, r.hubunganpj, r.biaya_reg, "
                    + " IFNULL(di.datang_sendiri,'-') AS datang_sendiri, "
                    + " IFNULL(di.trauma, '-') AS trauma, IFNULL(di.non_trauma, '-') AS non_trauma, "
                    + " IFNULL(di.tindakan_lanjut,'-') AS tindakan_lanjut, IFNULL(di.ket_igd, '-') AS ket_igd, r.umurdaftar, IFNULL(bs.no_sep,'-') nosep "
                    + " FROM reg_periksa r INNER JOIN pasien p ON r.no_rkm_medis = p.no_rkm_medis "
                    + " INNER JOIN poliklinik pl ON pl.kd_poli = r.kd_poli INNER JOIN penjab pj ON r.kd_pj = pj.kd_pj "
                    + " INNER JOIN dokter d ON r.kd_dokter = d.kd_dokter LEFT JOIN data_igd di ON r.no_rawat = di.no_rawat LEFT JOIN bridging_sep bs ON bs.no_rawat = r.no_rawat WHERE "
                    + " pl.kd_poli='IGDK' and r.tgl_registrasi between ? and ? and r.no_reg like ? or "
                    + " pl.kd_poli='IGDK' and r.tgl_registrasi between ? and ? and r.no_rawat like ? or "
                    + " pl.kd_poli='IGDK' and r.tgl_registrasi between ? and ? and r.tgl_registrasi like ? or "
                    + " pl.kd_poli='IGDK' and r.tgl_registrasi between ? and ? and r.kd_dokter like ? or "
                    + " pl.kd_poli='IGDK' and r.tgl_registrasi between ? and ? and d.nm_dokter like ? or "
                    + " pl.kd_poli='IGDK' and r.tgl_registrasi between ? and ? and r.no_rkm_medis like ? or "
                    + " pl.kd_poli='IGDK' and r.tgl_registrasi between ? and ? and r.stts_daftar like ? or "
                    + " pl.kd_poli='IGDK' and r.tgl_registrasi between ? and ? and p.nm_pasien like ? or "
                    + " pl.kd_poli='IGDK' and r.tgl_registrasi between ? and ? and pl.nm_poli like ? or "
                    + " pl.kd_poli='IGDK' and r.tgl_registrasi between ? and ? and r.p_jawab like ? or "
                    + " pl.kd_poli='IGDK' and r.tgl_registrasi between ? and ? and r.almt_pj like ? or "
                    + " pl.kd_poli='IGDK' and r.tgl_registrasi between ? and ? and r.hubunganpj like ? or "
                    + " pl.kd_poli='IGDK' and r.tgl_registrasi between ? and ? and di.tindakan_lanjut like ? or "
                    + " pl.kd_poli='IGDK' and r.tgl_registrasi between ? and ? and di.non_trauma like ? or "
                    + " pl.kd_poli='IGDK' and r.tgl_registrasi between ? and ? and di.ket_igd like ? or "
                    + " pl.kd_poli='IGDK' and r.tgl_registrasi between ? and ? and pj.png_jawab like ? order by r.no_rawat ");
            
            pscaripiutang = koneksi.prepareStatement("select tgl_piutang from piutang_pasien where no_rkm_medis=? and status='Belum Lunas' order by tgl_piutang asc limit 1");
        } catch (Exception ex) {
            System.out.println(ex);
        }

        try {
            prop.loadFromXML(new FileInputStream("setting/database.xml"));
            IPPRINTERTRACER = prop.getProperty("IPPRINTERTRACER");
            URUTNOREG = prop.getProperty("URUTNOREG");
        } catch (Exception ex) {
            IPPRINTERTRACER = "";
            URUTNOREG = "";
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
        MnKartu = new javax.swing.JMenu();
        MnKartuUmum = new javax.swing.JMenuItem();
        MnKartuNonUmum = new javax.swing.JMenuItem();
        MnTindakanRawatJalan = new javax.swing.JMenuItem();
        MnRujukan = new javax.swing.JMenu();
        MnRujuk = new javax.swing.JMenuItem();
        MnRujukMasuk = new javax.swing.JMenuItem();
        MnKelengkapanInap = new javax.swing.JMenu();
        MnGelangDewasaAnak = new javax.swing.JMenu();
        MnPrinterBaru = new javax.swing.JMenuItem();
        MnPrinterLama = new javax.swing.JMenuItem();
        MnGelangBayi = new javax.swing.JMenu();
        MnPrinterBaru1 = new javax.swing.JMenuItem();
        MnPrinterLama1 = new javax.swing.JMenuItem();
        MnBarcodeRM1 = new javax.swing.JMenuItem();
        MnBarcodeRM2 = new javax.swing.JMenuItem();
        MnLabelRM1 = new javax.swing.JMenuItem();
        MnLabelRM2 = new javax.swing.JMenuItem();
        jMenu1 = new javax.swing.JMenu();
        MnLaporanKunjunganIGD = new javax.swing.JMenuItem();
        MnLaporanStatistikIGD = new javax.swing.JMenuItem();
        MnLaporanRekapKunjunganPoli = new javax.swing.JMenuItem();
        MnLaporanRekapKunjunganDokter = new javax.swing.JMenuItem();
        MnLaporanRekapJenisBayar = new javax.swing.JMenuItem();
        MnLaporanRekapRawatDarurat = new javax.swing.JMenuItem();
        MnLaporanRekapKunjunganBulanan = new javax.swing.JMenuItem();
        MnLaporanRekapKunjunganBulananPoli = new javax.swing.JMenuItem();
        MnLaporanRekapPenyakitRalan = new javax.swing.JMenuItem();
        jMenu2 = new javax.swing.JMenu();
        ppGrafikPerpoli = new javax.swing.JMenuItem();
        ppGrafikPerdokter = new javax.swing.JMenuItem();
        ppGrafikPerJK = new javax.swing.JMenuItem();
        ppGrafikPerPekerjaan = new javax.swing.JMenuItem();
        ppGrafikPerAgama = new javax.swing.JMenuItem();
        ppGrafikPerTahun = new javax.swing.JMenuItem();
        ppGrafikPerBulan = new javax.swing.JMenuItem();
        ppGrafikPerTanggal = new javax.swing.JMenuItem();
        ppGrafikDemografi = new javax.swing.JMenuItem();
        jMenu4 = new javax.swing.JMenu();
        MnIdentitasRM = new javax.swing.JMenu();
        MnlembarCover = new javax.swing.JMenuItem();
        MnlembarRM5 = new javax.swing.JMenuItem();
        MnlembarRM51 = new javax.swing.JMenuItem();
        MnlembarRM52 = new javax.swing.JMenuItem();
        MnlembarRM521 = new javax.swing.JMenuItem();
        MnlembarRM6 = new javax.swing.JMenuItem();
        MnlembarLabel = new javax.swing.JMenuItem();
        CtkSuratTindakanDokter = new javax.swing.JMenuItem();
        MnSuratAPS = new javax.swing.JMenuItem();
        MnCetakSuratSakit1 = new javax.swing.JMenuItem();
        MnCetakSuratSakit2 = new javax.swing.JMenuItem();
        MnCetakRegister = new javax.swing.JMenuItem();
        MnPersetujuanMedis = new javax.swing.JMenuItem();
        MnLembarCasemix = new javax.swing.JMenuItem();
        MnCetakSuratSakit = new javax.swing.JMenuItem();
        jMenu3 = new javax.swing.JMenu();
        MnCheckList = new javax.swing.JMenuItem();
        MnCheckList1 = new javax.swing.JMenuItem();
        MnCheckList2 = new javax.swing.JMenuItem();
        MnCheckList3 = new javax.swing.JMenuItem();
        jMenu5 = new javax.swing.JMenu();
        MnCheckList4 = new javax.swing.JMenuItem();
        MnCheckList5 = new javax.swing.JMenuItem();
        MnCheckList6 = new javax.swing.JMenuItem();
        MnCheckList7 = new javax.swing.JMenuItem();
        MnBridging = new javax.swing.JMenu();
        ppPasienCorona = new javax.swing.JMenuItem();
        MnSEP = new javax.swing.JMenuItem();
        MnSJP = new javax.swing.JMenuItem();
        MnSEPJamkesda = new javax.swing.JMenuItem();
        MnJAMPERSAL = new javax.swing.JMenuItem();
        MnRujukSisrute = new javax.swing.JMenuItem();
        MnPermintaan = new javax.swing.JMenu();
        MnSKDPBPJS = new javax.swing.JMenuItem();
        MnPermintaanLab = new javax.swing.JMenuItem();
        MnPermintaanRadiologi = new javax.swing.JMenuItem();
        MnMati = new javax.swing.JMenuItem();
        MnRekamMedis = new javax.swing.JMenu();
        MnSuratTindakanDokter = new javax.swing.JMenuItem();
        MnDiagnosa = new javax.swing.JMenuItem();
        MnDataHAIs = new javax.swing.JMenuItem();
        MnCatatanPasien = new javax.swing.JMenuItem();
        MnDataPonek = new javax.swing.JMenuItem();
        ppPerawatanCorona = new javax.swing.JMenuItem();
        MnPeniliaianAwalKeperawatanRalan = new javax.swing.JMenuItem();
        MnDataTriaseIGD = new javax.swing.JMenuItem();
        ppRiwayat = new javax.swing.JMenuItem();
        MnBilling = new javax.swing.JMenuItem();
        MnBarcode = new javax.swing.JMenuItem();
        DlgSakit = new javax.swing.JDialog();
        internalFrame3 = new widget.InternalFrame();
        panelBiasa2 = new widget.PanelBiasa();
        TglSakit1 = new widget.Tanggal();
        jLabel31 = new widget.Label();
        BtnPrint2 = new widget.Button();
        BtnKeluar2 = new widget.Button();
        jLabel32 = new widget.Label();
        TglSakit2 = new widget.Tanggal();
        jLabel33 = new widget.Label();
        lmsakit = new widget.TextBox();
        DlgDemografi = new javax.swing.JDialog();
        internalFrame4 = new widget.InternalFrame();
        panelBiasa3 = new widget.PanelBiasa();
        BtnPrint3 = new widget.Button();
        BtnKeluar3 = new widget.Button();
        Kelurahan2 = new widget.TextBox();
        btnKel = new widget.Button();
        Kecamatan2 = new widget.TextBox();
        btnKec = new widget.Button();
        Kabupaten2 = new widget.TextBox();
        btnKab = new widget.Button();
        jLabel34 = new widget.Label();
        jLabel35 = new widget.Label();
        jLabel36 = new widget.Label();
        BtnPrint4 = new widget.Button();
        DlgSakit2 = new javax.swing.JDialog();
        internalFrame5 = new widget.InternalFrame();
        panelBiasa4 = new widget.PanelBiasa();
        jLabel37 = new widget.Label();
        BtnPrint5 = new widget.Button();
        BtnKeluar4 = new widget.Button();
        NomorSurat = new widget.TextBox();
        BtnSeek5 = new widget.Button();
        CrDokter3 = new widget.TextBox();
        jLabel24 = new widget.Label();
        DlgMati = new javax.swing.JDialog();
        internalFrame7 = new widget.InternalFrame();
        BtnCloseIn2 = new widget.Button();
        ket = new widget.TextBox();
        jLabel41 = new widget.Label();
        TglMati = new widget.Tanggal();
        BtnGantiMati = new widget.Button();
        jLabel11 = new widget.Label();
        cmbJam = new widget.ComboBox();
        cmbMnt = new widget.ComboBox();
        cmbDtk = new widget.ComboBox();
        BtnHapusMati = new widget.Button();
        jam = new widget.TextBox();
        tgl = new widget.TextBox();
        BtnCetakMati = new widget.Button();
        cekTglMati = new widget.CekBox();
        DlgJamkesda = new javax.swing.JDialog();
        internalFrame6 = new widget.InternalFrame();
        BtnCloseIn1 = new widget.Button();
        BtnSimpan1 = new widget.Button();
        jLabel25 = new widget.Label();
        noSrt = new widget.TextBox();
        jLabel28 = new widget.Label();
        TglSurat = new widget.Tanggal();
        BtnGantijkd = new widget.Button();
        BtnCtkJkd = new widget.Button();
        DlgJampersal = new javax.swing.JDialog();
        internalFrame8 = new widget.InternalFrame();
        BtnCloseIn3 = new widget.Button();
        BtnSimpan2 = new widget.Button();
        jLabel44 = new widget.Label();
        noSrt1 = new widget.TextBox();
        jLabel45 = new widget.Label();
        TglSurat1 = new widget.Tanggal();
        BtnGantijmp = new widget.Button();
        BtnCtkJmp = new widget.Button();
        DlgAPS = new javax.swing.JDialog();
        internalFrame16 = new widget.InternalFrame();
        BtnCloseIn11 = new widget.Button();
        Scroll5 = new widget.ScrollPane();
        tbAPS = new widget.Table();
        jLabel98 = new widget.Label();
        TCari1 = new widget.TextBox();
        BtnCari5 = new widget.Button();
        DlgSuratTindakanDokter = new javax.swing.JDialog();
        internalFrame9 = new widget.InternalFrame();
        BtnCloseIn4 = new widget.Button();
        nmPJ = new widget.TextBox();
        jLabel46 = new widget.Label();
        TglSuratTindakan = new widget.Tanggal();
        BtnGantiSurat = new widget.Button();
        jLabel12 = new widget.Label();
        CmbJam1 = new widget.ComboBox();
        CmbMenit1 = new widget.ComboBox();
        CmbDetik1 = new widget.ComboBox();
        BtnHapusSurat = new widget.Button();
        BtnCetakSurat = new widget.Button();
        jLabel47 = new widget.Label();
        umurPJ = new widget.TextBox();
        jLabel48 = new widget.Label();
        jLabel49 = new widget.Label();
        jkPJ = new widget.ComboBox();
        jLabel50 = new widget.Label();
        alamatPJ = new widget.TextBox();
        nmkel = new widget.TextBox();
        jLabel51 = new widget.Label();
        jLabel52 = new widget.Label();
        nmkec = new widget.TextBox();
        nmkab = new widget.TextBox();
        jLabel53 = new widget.Label();
        jLabel54 = new widget.Label();
        hubungan = new widget.ComboBox();
        jLabel55 = new widget.Label();
        jnsSurat = new widget.ComboBox();
        jLabel56 = new widget.Label();
        jSeparator1 = new javax.swing.JSeparator();
        tindakanDokter = new widget.TextBox();
        jLabel57 = new widget.Label();
        alasanTolak = new widget.TextBox();
        jLabel58 = new widget.Label();
        BtnSimpan3 = new widget.Button();
        BtnKelurahan = new widget.Button();
        BtnKecamatan = new widget.Button();
        BtnKabupaten = new widget.Button();
        ChkJln1 = new widget.CekBox();
        Kd2 = new widget.TextBox();
        NoBalasan = new widget.TextBox();
        KataDatang = new widget.TextBox();
        sepJkd = new widget.TextBox();
        sepJmp = new widget.TextBox();
        noRawatDataIGD = new widget.TextBox();
        rmMati = new widget.TextBox();
        unitIGD = new widget.TextBox();
        TNoRM = new widget.TextBox();
        kode_rujukanya = new widget.TextBox();
        kdsuku = new widget.TextBox();
        kdbahasa = new widget.TextBox();
        umurDaftar = new widget.TextBox();
        kelaminPersalinan = new widget.TextBox();
        cekDataPersalinan = new widget.TextBox();
        norwSurat = new widget.TextBox();
        internalFrame1 = new widget.InternalFrame();
        Scroll = new widget.ScrollPane();
        tbregistrasiIGD = new widget.Table();
        jPanel2 = new javax.swing.JPanel();
        panelGlass6 = new widget.panelisi();
        BtnSimpan = new widget.Button();
        BtnBatal = new widget.Button();
        BtnHapus = new widget.Button();
        BtnEdit = new widget.Button();
        BtnPrint = new widget.Button();
        BtnAll = new widget.Button();
        BtnKeluar = new widget.Button();
        jLabel10 = new widget.Label();
        LCount = new widget.Label();
        panelGlass7 = new widget.panelisi();
        jLabel15 = new widget.Label();
        DTPCari1 = new widget.Tanggal();
        jLabel17 = new widget.Label();
        DTPCari2 = new widget.Tanggal();
        jLabel6 = new widget.Label();
        TCari = new widget.TextBox();
        BtnCari = new widget.Button();
        PanelInput = new javax.swing.JPanel();
        FormInput = new widget.PanelBiasa();
        jLabel3 = new widget.Label();
        jLabel4 = new widget.Label();
        TDokter = new widget.TextBox();
        TNoRw = new widget.TextBox();
        jLabel8 = new widget.Label();
        jLabel13 = new widget.Label();
        jLabel20 = new widget.Label();
        jLabel21 = new widget.Label();
        TPngJwb = new widget.TextBox();
        TNoID = new widget.TextBox();
        TNoReg = new widget.TextBox();
        jLabel7 = new widget.Label();
        TAlmt = new widget.TextBox();
        BtnPasien = new widget.Button();
        TPasien = new widget.TextBox();
        jLabel22 = new widget.Label();
        THbngn = new widget.TextBox();
        kddokter = new widget.TextBox();
        BtnDokter = new widget.Button();
        jLabel30 = new widget.Label();
        TStatus = new widget.TextBox();
        jLabel18 = new widget.Label();
        kdpnj = new widget.TextBox();
        nmpnj = new widget.TextBox();
        btnPenjab = new widget.Button();
        AsalRujukan = new widget.TextBox();
        jLabel23 = new widget.Label();
        btnPenjab1 = new widget.Button();
        ChkTracker = new widget.CekBox();
        jLabel26 = new widget.Label();
        cmbTindakan = new widget.ComboBox();
        jLabel27 = new widget.Label();
        cmbTrauma = new widget.ComboBox();
        ChkDtgSendiri = new widget.CekBox();
        jLabel38 = new widget.Label();
        cmbNonTrauma = new widget.ComboBox();
        jLabel39 = new widget.Label();
        cmbKet = new widget.ComboBox();
        JnsnoID = new widget.ComboBox();
        jLabel29 = new widget.Label();
        nmsuku = new widget.TextBox();
        jLabel40 = new widget.Label();
        nmbahasa = new widget.TextBox();
        BtnSuku = new widget.Button();
        BtnBahasa = new widget.Button();
        jLabel42 = new widget.Label();
        jLabel43 = new widget.Label();
        alasanAPS = new widget.TextBox();
        ketAPS = new widget.TextBox();
        BtnAlasanAPS = new widget.Button();
        tulisan_tanggal = new widget.Label();
        ChkInput = new widget.CekBox();

        jPopupMenu1.setName("jPopupMenu1"); // NOI18N

        MnKamarInap.setBackground(new java.awt.Color(255, 255, 255));
        MnKamarInap.setFont(new java.awt.Font("Tahoma", 0, 11)); // NOI18N
        MnKamarInap.setIcon(new javax.swing.ImageIcon(getClass().getResource("/picture/category.png"))); // NOI18N
        MnKamarInap.setText("Kamar Inap");
        MnKamarInap.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        MnKamarInap.setHorizontalTextPosition(javax.swing.SwingConstants.RIGHT);
        MnKamarInap.setIconTextGap(5);
        MnKamarInap.setName("MnKamarInap"); // NOI18N
        MnKamarInap.setPreferredSize(new java.awt.Dimension(240, 26));
        MnKamarInap.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                MnKamarInapActionPerformed(evt);
            }
        });
        jPopupMenu1.add(MnKamarInap);

        MnKartu.setBackground(new java.awt.Color(255, 255, 255));
        MnKartu.setIcon(new javax.swing.ImageIcon(getClass().getResource("/picture/category.png"))); // NOI18N
        MnKartu.setText("Kartu Berobat Pasien (KIB)");
        MnKartu.setFont(new java.awt.Font("Tahoma", 0, 11)); // NOI18N
        MnKartu.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        MnKartu.setHorizontalTextPosition(javax.swing.SwingConstants.RIGHT);
        MnKartu.setIconTextGap(5);
        MnKartu.setName("MnKartu"); // NOI18N
        MnKartu.setOpaque(true);
        MnKartu.setPreferredSize(new java.awt.Dimension(230, 28));

        MnKartuUmum.setBackground(new java.awt.Color(255, 255, 255));
        MnKartuUmum.setFont(new java.awt.Font("Tahoma", 0, 11)); // NOI18N
        MnKartuUmum.setIcon(new javax.swing.ImageIcon(getClass().getResource("/picture/category.png"))); // NOI18N
        MnKartuUmum.setText("Pasien UMUM");
        MnKartuUmum.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        MnKartuUmum.setHorizontalTextPosition(javax.swing.SwingConstants.RIGHT);
        MnKartuUmum.setIconTextGap(5);
        MnKartuUmum.setName("MnKartuUmum"); // NOI18N
        MnKartuUmum.setPreferredSize(new java.awt.Dimension(140, 28));
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
        MnKartuNonUmum.setPreferredSize(new java.awt.Dimension(140, 28));
        MnKartuNonUmum.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                MnKartuNonUmumActionPerformed(evt);
            }
        });
        MnKartu.add(MnKartuNonUmum);

        jPopupMenu1.add(MnKartu);

        MnTindakanRawatJalan.setBackground(new java.awt.Color(255, 255, 255));
        MnTindakanRawatJalan.setFont(new java.awt.Font("Tahoma", 0, 11)); // NOI18N
        MnTindakanRawatJalan.setIcon(new javax.swing.ImageIcon(getClass().getResource("/picture/category.png"))); // NOI18N
        MnTindakanRawatJalan.setText("Tagihan/Tindakan Rawat Jalan");
        MnTindakanRawatJalan.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        MnTindakanRawatJalan.setHorizontalTextPosition(javax.swing.SwingConstants.RIGHT);
        MnTindakanRawatJalan.setIconTextGap(5);
        MnTindakanRawatJalan.setName("MnTindakanRawatJalan"); // NOI18N
        MnTindakanRawatJalan.setPreferredSize(new java.awt.Dimension(240, 26));
        MnTindakanRawatJalan.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                MnTindakanRawatJalanActionPerformed(evt);
            }
        });
        jPopupMenu1.add(MnTindakanRawatJalan);

        MnRujukan.setBackground(new java.awt.Color(255, 255, 255));
        MnRujukan.setIcon(new javax.swing.ImageIcon(getClass().getResource("/picture/category.png"))); // NOI18N
        MnRujukan.setText("Rujukan Pasien");
        MnRujukan.setFont(new java.awt.Font("Tahoma", 0, 11)); // NOI18N
        MnRujukan.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        MnRujukan.setHorizontalTextPosition(javax.swing.SwingConstants.RIGHT);
        MnRujukan.setIconTextGap(5);
        MnRujukan.setName("MnRujukan"); // NOI18N
        MnRujukan.setOpaque(true);
        MnRujukan.setPreferredSize(new java.awt.Dimension(240, 26));

        MnRujuk.setBackground(new java.awt.Color(255, 255, 255));
        MnRujuk.setFont(new java.awt.Font("Tahoma", 0, 11)); // NOI18N
        MnRujuk.setIcon(new javax.swing.ImageIcon(getClass().getResource("/picture/category.png"))); // NOI18N
        MnRujuk.setText("Rujukan Keluar");
        MnRujuk.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        MnRujuk.setHorizontalTextPosition(javax.swing.SwingConstants.RIGHT);
        MnRujuk.setIconTextGap(5);
        MnRujuk.setName("MnRujuk"); // NOI18N
        MnRujuk.setPreferredSize(new java.awt.Dimension(135, 26));
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
        MnRujukMasuk.setPreferredSize(new java.awt.Dimension(135, 26));
        MnRujukMasuk.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                MnRujukMasukActionPerformed(evt);
            }
        });
        MnRujukan.add(MnRujukMasuk);

        jPopupMenu1.add(MnRujukan);

        MnKelengkapanInap.setBackground(new java.awt.Color(255, 255, 255));
        MnKelengkapanInap.setIcon(new javax.swing.ImageIcon(getClass().getResource("/picture/b_print.png"))); // NOI18N
        MnKelengkapanInap.setText("Cetak Kelengkapan Opname");
        MnKelengkapanInap.setFont(new java.awt.Font("Tahoma", 0, 11)); // NOI18N
        MnKelengkapanInap.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        MnKelengkapanInap.setHorizontalTextPosition(javax.swing.SwingConstants.RIGHT);
        MnKelengkapanInap.setIconTextGap(5);
        MnKelengkapanInap.setName("MnKelengkapanInap"); // NOI18N
        MnKelengkapanInap.setOpaque(true);
        MnKelengkapanInap.setPreferredSize(new java.awt.Dimension(250, 28));

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

        MnKelengkapanInap.add(MnGelangDewasaAnak);

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

        MnKelengkapanInap.add(MnGelangBayi);

        MnBarcodeRM1.setBackground(new java.awt.Color(255, 255, 255));
        MnBarcodeRM1.setFont(new java.awt.Font("Tahoma", 0, 11)); // NOI18N
        MnBarcodeRM1.setIcon(new javax.swing.ImageIcon(getClass().getResource("/picture/b_print.png"))); // NOI18N
        MnBarcodeRM1.setText("Barcode (KERTAS BESAR)");
        MnBarcodeRM1.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        MnBarcodeRM1.setHorizontalTextPosition(javax.swing.SwingConstants.RIGHT);
        MnBarcodeRM1.setIconTextGap(5);
        MnBarcodeRM1.setName("MnBarcodeRM1"); // NOI18N
        MnBarcodeRM1.setPreferredSize(new java.awt.Dimension(200, 28));
        MnBarcodeRM1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                MnBarcodeRM1ActionPerformed(evt);
            }
        });
        MnKelengkapanInap.add(MnBarcodeRM1);

        MnBarcodeRM2.setBackground(new java.awt.Color(255, 255, 255));
        MnBarcodeRM2.setFont(new java.awt.Font("Tahoma", 0, 11)); // NOI18N
        MnBarcodeRM2.setIcon(new javax.swing.ImageIcon(getClass().getResource("/picture/b_print.png"))); // NOI18N
        MnBarcodeRM2.setText("Barcode (KERTAS KECIL)");
        MnBarcodeRM2.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        MnBarcodeRM2.setHorizontalTextPosition(javax.swing.SwingConstants.RIGHT);
        MnBarcodeRM2.setIconTextGap(5);
        MnBarcodeRM2.setName("MnBarcodeRM2"); // NOI18N
        MnBarcodeRM2.setPreferredSize(new java.awt.Dimension(200, 28));
        MnBarcodeRM2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                MnBarcodeRM2ActionPerformed(evt);
            }
        });
        MnKelengkapanInap.add(MnBarcodeRM2);

        MnLabelRM1.setBackground(new java.awt.Color(255, 255, 255));
        MnLabelRM1.setFont(new java.awt.Font("Tahoma", 0, 11)); // NOI18N
        MnLabelRM1.setIcon(new javax.swing.ImageIcon(getClass().getResource("/picture/b_print.png"))); // NOI18N
        MnLabelRM1.setText("Label Identitas (KERTAS BESAR)");
        MnLabelRM1.setName("MnLabelRM1"); // NOI18N
        MnLabelRM1.setPreferredSize(new java.awt.Dimension(210, 28));
        MnLabelRM1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                MnLabelRM1ActionPerformed(evt);
            }
        });
        MnKelengkapanInap.add(MnLabelRM1);

        MnLabelRM2.setBackground(new java.awt.Color(255, 255, 255));
        MnLabelRM2.setFont(new java.awt.Font("Tahoma", 0, 11)); // NOI18N
        MnLabelRM2.setIcon(new javax.swing.ImageIcon(getClass().getResource("/picture/b_print.png"))); // NOI18N
        MnLabelRM2.setText("Label Identitas (KERTAS KECIL)");
        MnLabelRM2.setName("MnLabelRM2"); // NOI18N
        MnLabelRM2.setPreferredSize(new java.awt.Dimension(210, 28));
        MnLabelRM2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                MnLabelRM2ActionPerformed(evt);
            }
        });
        MnKelengkapanInap.add(MnLabelRM2);

        jPopupMenu1.add(MnKelengkapanInap);

        jMenu1.setBackground(new java.awt.Color(255, 255, 255));
        jMenu1.setIcon(new javax.swing.ImageIcon(getClass().getResource("/picture/category.png"))); // NOI18N
        jMenu1.setText("Laporan");
        jMenu1.setFont(new java.awt.Font("Tahoma", 0, 11)); // NOI18N
        jMenu1.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        jMenu1.setHorizontalTextPosition(javax.swing.SwingConstants.RIGHT);
        jMenu1.setIconTextGap(5);
        jMenu1.setName("jMenu1"); // NOI18N
        jMenu1.setOpaque(true);
        jMenu1.setPreferredSize(new java.awt.Dimension(240, 26));
        jMenu1.setRequestFocusEnabled(false);

        MnLaporanKunjunganIGD.setBackground(new java.awt.Color(255, 255, 255));
        MnLaporanKunjunganIGD.setFont(new java.awt.Font("Tahoma", 0, 11)); // NOI18N
        MnLaporanKunjunganIGD.setIcon(new javax.swing.ImageIcon(getClass().getResource("/picture/category.png"))); // NOI18N
        MnLaporanKunjunganIGD.setText("Laporan Daftar Kunjungan IGD");
        MnLaporanKunjunganIGD.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        MnLaporanKunjunganIGD.setHorizontalTextPosition(javax.swing.SwingConstants.RIGHT);
        MnLaporanKunjunganIGD.setIconTextGap(5);
        MnLaporanKunjunganIGD.setName("MnLaporanKunjunganIGD"); // NOI18N
        MnLaporanKunjunganIGD.setPreferredSize(new java.awt.Dimension(240, 26));
        MnLaporanKunjunganIGD.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                MnLaporanKunjunganIGDActionPerformed(evt);
            }
        });
        jMenu1.add(MnLaporanKunjunganIGD);

        MnLaporanStatistikIGD.setBackground(new java.awt.Color(255, 255, 255));
        MnLaporanStatistikIGD.setFont(new java.awt.Font("Tahoma", 0, 11)); // NOI18N
        MnLaporanStatistikIGD.setIcon(new javax.swing.ImageIcon(getClass().getResource("/picture/category.png"))); // NOI18N
        MnLaporanStatistikIGD.setText("Laporan Statistik Kunjungan IGD");
        MnLaporanStatistikIGD.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        MnLaporanStatistikIGD.setHorizontalTextPosition(javax.swing.SwingConstants.RIGHT);
        MnLaporanStatistikIGD.setIconTextGap(5);
        MnLaporanStatistikIGD.setName("MnLaporanStatistikIGD"); // NOI18N
        MnLaporanStatistikIGD.setPreferredSize(new java.awt.Dimension(240, 26));
        MnLaporanStatistikIGD.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                MnLaporanStatistikIGDActionPerformed(evt);
            }
        });
        jMenu1.add(MnLaporanStatistikIGD);

        MnLaporanRekapKunjunganPoli.setBackground(new java.awt.Color(255, 255, 255));
        MnLaporanRekapKunjunganPoli.setFont(new java.awt.Font("Tahoma", 0, 11)); // NOI18N
        MnLaporanRekapKunjunganPoli.setIcon(new javax.swing.ImageIcon(getClass().getResource("/picture/category.png"))); // NOI18N
        MnLaporanRekapKunjunganPoli.setText("Laporan Rekap Kunjungan Per Poli");
        MnLaporanRekapKunjunganPoli.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        MnLaporanRekapKunjunganPoli.setHorizontalTextPosition(javax.swing.SwingConstants.RIGHT);
        MnLaporanRekapKunjunganPoli.setIconTextGap(5);
        MnLaporanRekapKunjunganPoli.setName("MnLaporanRekapKunjunganPoli"); // NOI18N
        MnLaporanRekapKunjunganPoli.setPreferredSize(new java.awt.Dimension(240, 26));
        MnLaporanRekapKunjunganPoli.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                MnLaporanRekapKunjunganPoliActionPerformed(evt);
            }
        });
        jMenu1.add(MnLaporanRekapKunjunganPoli);

        MnLaporanRekapKunjunganDokter.setBackground(new java.awt.Color(255, 255, 255));
        MnLaporanRekapKunjunganDokter.setFont(new java.awt.Font("Tahoma", 0, 11)); // NOI18N
        MnLaporanRekapKunjunganDokter.setIcon(new javax.swing.ImageIcon(getClass().getResource("/picture/category.png"))); // NOI18N
        MnLaporanRekapKunjunganDokter.setText("Laporan Rekap Kunjungan Per Dokter");
        MnLaporanRekapKunjunganDokter.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        MnLaporanRekapKunjunganDokter.setHorizontalTextPosition(javax.swing.SwingConstants.RIGHT);
        MnLaporanRekapKunjunganDokter.setIconTextGap(5);
        MnLaporanRekapKunjunganDokter.setName("MnLaporanRekapKunjunganDokter"); // NOI18N
        MnLaporanRekapKunjunganDokter.setPreferredSize(new java.awt.Dimension(240, 26));
        MnLaporanRekapKunjunganDokter.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                MnLaporanRekapKunjunganDokterActionPerformed(evt);
            }
        });
        jMenu1.add(MnLaporanRekapKunjunganDokter);

        MnLaporanRekapJenisBayar.setBackground(new java.awt.Color(255, 255, 255));
        MnLaporanRekapJenisBayar.setFont(new java.awt.Font("Tahoma", 0, 11)); // NOI18N
        MnLaporanRekapJenisBayar.setIcon(new javax.swing.ImageIcon(getClass().getResource("/picture/category.png"))); // NOI18N
        MnLaporanRekapJenisBayar.setText("Laporan RL 315 Cara bayar");
        MnLaporanRekapJenisBayar.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        MnLaporanRekapJenisBayar.setHorizontalTextPosition(javax.swing.SwingConstants.RIGHT);
        MnLaporanRekapJenisBayar.setIconTextGap(5);
        MnLaporanRekapJenisBayar.setName("MnLaporanRekapJenisBayar"); // NOI18N
        MnLaporanRekapJenisBayar.setPreferredSize(new java.awt.Dimension(240, 26));
        MnLaporanRekapJenisBayar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                MnLaporanRekapJenisBayarActionPerformed(evt);
            }
        });
        jMenu1.add(MnLaporanRekapJenisBayar);

        MnLaporanRekapRawatDarurat.setBackground(new java.awt.Color(255, 255, 255));
        MnLaporanRekapRawatDarurat.setFont(new java.awt.Font("Tahoma", 0, 11)); // NOI18N
        MnLaporanRekapRawatDarurat.setIcon(new javax.swing.ImageIcon(getClass().getResource("/picture/category.png"))); // NOI18N
        MnLaporanRekapRawatDarurat.setText("Laporan RL 32 Rawat Darurat");
        MnLaporanRekapRawatDarurat.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        MnLaporanRekapRawatDarurat.setHorizontalTextPosition(javax.swing.SwingConstants.RIGHT);
        MnLaporanRekapRawatDarurat.setIconTextGap(5);
        MnLaporanRekapRawatDarurat.setName("MnLaporanRekapRawatDarurat"); // NOI18N
        MnLaporanRekapRawatDarurat.setPreferredSize(new java.awt.Dimension(240, 26));
        MnLaporanRekapRawatDarurat.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                MnLaporanRekapRawatDaruratActionPerformed(evt);
            }
        });
        jMenu1.add(MnLaporanRekapRawatDarurat);

        MnLaporanRekapKunjunganBulanan.setBackground(new java.awt.Color(255, 255, 255));
        MnLaporanRekapKunjunganBulanan.setFont(new java.awt.Font("Tahoma", 0, 11)); // NOI18N
        MnLaporanRekapKunjunganBulanan.setIcon(new javax.swing.ImageIcon(getClass().getResource("/picture/category.png"))); // NOI18N
        MnLaporanRekapKunjunganBulanan.setText("Laporan RL 51");
        MnLaporanRekapKunjunganBulanan.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        MnLaporanRekapKunjunganBulanan.setHorizontalTextPosition(javax.swing.SwingConstants.RIGHT);
        MnLaporanRekapKunjunganBulanan.setIconTextGap(5);
        MnLaporanRekapKunjunganBulanan.setName("MnLaporanRekapKunjunganBulanan"); // NOI18N
        MnLaporanRekapKunjunganBulanan.setPreferredSize(new java.awt.Dimension(240, 26));
        MnLaporanRekapKunjunganBulanan.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                MnLaporanRekapKunjunganBulananActionPerformed(evt);
            }
        });
        jMenu1.add(MnLaporanRekapKunjunganBulanan);

        MnLaporanRekapKunjunganBulananPoli.setBackground(new java.awt.Color(255, 255, 255));
        MnLaporanRekapKunjunganBulananPoli.setFont(new java.awt.Font("Tahoma", 0, 11)); // NOI18N
        MnLaporanRekapKunjunganBulananPoli.setIcon(new javax.swing.ImageIcon(getClass().getResource("/picture/category.png"))); // NOI18N
        MnLaporanRekapKunjunganBulananPoli.setText("Laporan RL 52");
        MnLaporanRekapKunjunganBulananPoli.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        MnLaporanRekapKunjunganBulananPoli.setHorizontalTextPosition(javax.swing.SwingConstants.RIGHT);
        MnLaporanRekapKunjunganBulananPoli.setIconTextGap(5);
        MnLaporanRekapKunjunganBulananPoli.setName("MnLaporanRekapKunjunganBulananPoli"); // NOI18N
        MnLaporanRekapKunjunganBulananPoli.setPreferredSize(new java.awt.Dimension(240, 26));
        MnLaporanRekapKunjunganBulananPoli.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                MnLaporanRekapKunjunganBulananPoliActionPerformed(evt);
            }
        });
        jMenu1.add(MnLaporanRekapKunjunganBulananPoli);

        MnLaporanRekapPenyakitRalan.setBackground(new java.awt.Color(255, 255, 255));
        MnLaporanRekapPenyakitRalan.setFont(new java.awt.Font("Tahoma", 0, 11)); // NOI18N
        MnLaporanRekapPenyakitRalan.setIcon(new javax.swing.ImageIcon(getClass().getResource("/picture/category.png"))); // NOI18N
        MnLaporanRekapPenyakitRalan.setText("Laporan RL 53 Penyakit Ralan");
        MnLaporanRekapPenyakitRalan.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        MnLaporanRekapPenyakitRalan.setHorizontalTextPosition(javax.swing.SwingConstants.RIGHT);
        MnLaporanRekapPenyakitRalan.setIconTextGap(5);
        MnLaporanRekapPenyakitRalan.setName("MnLaporanRekapPenyakitRalan"); // NOI18N
        MnLaporanRekapPenyakitRalan.setPreferredSize(new java.awt.Dimension(240, 26));
        MnLaporanRekapPenyakitRalan.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                MnLaporanRekapPenyakitRalanActionPerformed(evt);
            }
        });
        jMenu1.add(MnLaporanRekapPenyakitRalan);

        jPopupMenu1.add(jMenu1);

        jMenu2.setBackground(new java.awt.Color(255, 255, 255));
        jMenu2.setIcon(new javax.swing.ImageIcon(getClass().getResource("/picture/category.png"))); // NOI18N
        jMenu2.setText("Grafik Analisa");
        jMenu2.setFont(new java.awt.Font("Tahoma", 0, 11)); // NOI18N
        jMenu2.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        jMenu2.setHorizontalTextPosition(javax.swing.SwingConstants.RIGHT);
        jMenu2.setIconTextGap(5);
        jMenu2.setName("jMenu2"); // NOI18N
        jMenu2.setOpaque(true);
        jMenu2.setPreferredSize(new java.awt.Dimension(240, 26));
        jMenu2.setRequestFocusEnabled(false);

        ppGrafikPerpoli.setBackground(new java.awt.Color(255, 255, 255));
        ppGrafikPerpoli.setFont(new java.awt.Font("Tahoma", 0, 11)); // NOI18N
        ppGrafikPerpoli.setIcon(new javax.swing.ImageIcon(getClass().getResource("/picture/category.png"))); // NOI18N
        ppGrafikPerpoli.setText("Grafik Kunjungan Per Poli");
        ppGrafikPerpoli.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        ppGrafikPerpoli.setHorizontalTextPosition(javax.swing.SwingConstants.RIGHT);
        ppGrafikPerpoli.setIconTextGap(5);
        ppGrafikPerpoli.setName("ppGrafikPerpoli"); // NOI18N
        ppGrafikPerpoli.setPreferredSize(new java.awt.Dimension(225, 26));
        ppGrafikPerpoli.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                ppGrafikPerpoliActionPerformed(evt);
            }
        });
        jMenu2.add(ppGrafikPerpoli);

        ppGrafikPerdokter.setBackground(new java.awt.Color(255, 255, 255));
        ppGrafikPerdokter.setFont(new java.awt.Font("Tahoma", 0, 11)); // NOI18N
        ppGrafikPerdokter.setIcon(new javax.swing.ImageIcon(getClass().getResource("/picture/category.png"))); // NOI18N
        ppGrafikPerdokter.setText("Grafik Kunjungan Per Dokter");
        ppGrafikPerdokter.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        ppGrafikPerdokter.setHorizontalTextPosition(javax.swing.SwingConstants.RIGHT);
        ppGrafikPerdokter.setIconTextGap(5);
        ppGrafikPerdokter.setName("ppGrafikPerdokter"); // NOI18N
        ppGrafikPerdokter.setPreferredSize(new java.awt.Dimension(225, 26));
        ppGrafikPerdokter.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                ppGrafikPerdokterActionPerformed(evt);
            }
        });
        jMenu2.add(ppGrafikPerdokter);

        ppGrafikPerJK.setBackground(new java.awt.Color(255, 255, 255));
        ppGrafikPerJK.setFont(new java.awt.Font("Tahoma", 0, 11)); // NOI18N
        ppGrafikPerJK.setIcon(new javax.swing.ImageIcon(getClass().getResource("/picture/category.png"))); // NOI18N
        ppGrafikPerJK.setText("Grafik Kunjungan Per Jenis Kelamin");
        ppGrafikPerJK.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        ppGrafikPerJK.setHorizontalTextPosition(javax.swing.SwingConstants.RIGHT);
        ppGrafikPerJK.setIconTextGap(5);
        ppGrafikPerJK.setName("ppGrafikPerJK"); // NOI18N
        ppGrafikPerJK.setPreferredSize(new java.awt.Dimension(225, 26));
        ppGrafikPerJK.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                ppGrafikPerJKActionPerformed(evt);
            }
        });
        jMenu2.add(ppGrafikPerJK);

        ppGrafikPerPekerjaan.setBackground(new java.awt.Color(255, 255, 255));
        ppGrafikPerPekerjaan.setFont(new java.awt.Font("Tahoma", 0, 11)); // NOI18N
        ppGrafikPerPekerjaan.setIcon(new javax.swing.ImageIcon(getClass().getResource("/picture/category.png"))); // NOI18N
        ppGrafikPerPekerjaan.setText("Grafik Kunjungan Per Pekerjaan");
        ppGrafikPerPekerjaan.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        ppGrafikPerPekerjaan.setHorizontalTextPosition(javax.swing.SwingConstants.RIGHT);
        ppGrafikPerPekerjaan.setIconTextGap(5);
        ppGrafikPerPekerjaan.setName("ppGrafikPerPekerjaan"); // NOI18N
        ppGrafikPerPekerjaan.setPreferredSize(new java.awt.Dimension(225, 26));
        ppGrafikPerPekerjaan.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                ppGrafikPerPekerjaanActionPerformed(evt);
            }
        });
        jMenu2.add(ppGrafikPerPekerjaan);

        ppGrafikPerAgama.setBackground(new java.awt.Color(255, 255, 255));
        ppGrafikPerAgama.setFont(new java.awt.Font("Tahoma", 0, 11)); // NOI18N
        ppGrafikPerAgama.setIcon(new javax.swing.ImageIcon(getClass().getResource("/picture/category.png"))); // NOI18N
        ppGrafikPerAgama.setText("Grafik Kunjungan Per Agama");
        ppGrafikPerAgama.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        ppGrafikPerAgama.setHorizontalTextPosition(javax.swing.SwingConstants.RIGHT);
        ppGrafikPerAgama.setIconTextGap(5);
        ppGrafikPerAgama.setName("ppGrafikPerAgama"); // NOI18N
        ppGrafikPerAgama.setPreferredSize(new java.awt.Dimension(225, 26));
        ppGrafikPerAgama.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                ppGrafikPerAgamaActionPerformed(evt);
            }
        });
        jMenu2.add(ppGrafikPerAgama);

        ppGrafikPerTahun.setBackground(new java.awt.Color(255, 255, 255));
        ppGrafikPerTahun.setFont(new java.awt.Font("Tahoma", 0, 11)); // NOI18N
        ppGrafikPerTahun.setIcon(new javax.swing.ImageIcon(getClass().getResource("/picture/category.png"))); // NOI18N
        ppGrafikPerTahun.setText("Grafik Kunjungan Per Tahun");
        ppGrafikPerTahun.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        ppGrafikPerTahun.setHorizontalTextPosition(javax.swing.SwingConstants.RIGHT);
        ppGrafikPerTahun.setIconTextGap(5);
        ppGrafikPerTahun.setName("ppGrafikPerTahun"); // NOI18N
        ppGrafikPerTahun.setPreferredSize(new java.awt.Dimension(225, 26));
        ppGrafikPerTahun.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                ppGrafikPerTahunActionPerformed(evt);
            }
        });
        jMenu2.add(ppGrafikPerTahun);

        ppGrafikPerBulan.setBackground(new java.awt.Color(255, 255, 255));
        ppGrafikPerBulan.setFont(new java.awt.Font("Tahoma", 0, 11)); // NOI18N
        ppGrafikPerBulan.setIcon(new javax.swing.ImageIcon(getClass().getResource("/picture/category.png"))); // NOI18N
        ppGrafikPerBulan.setText("Grafik Kunjungan Per Bulan");
        ppGrafikPerBulan.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        ppGrafikPerBulan.setHorizontalTextPosition(javax.swing.SwingConstants.RIGHT);
        ppGrafikPerBulan.setIconTextGap(5);
        ppGrafikPerBulan.setName("ppGrafikPerBulan"); // NOI18N
        ppGrafikPerBulan.setPreferredSize(new java.awt.Dimension(225, 26));
        ppGrafikPerBulan.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                ppGrafikPerBulanActionPerformed(evt);
            }
        });
        jMenu2.add(ppGrafikPerBulan);

        ppGrafikPerTanggal.setBackground(new java.awt.Color(255, 255, 255));
        ppGrafikPerTanggal.setFont(new java.awt.Font("Tahoma", 0, 11)); // NOI18N
        ppGrafikPerTanggal.setIcon(new javax.swing.ImageIcon(getClass().getResource("/picture/category.png"))); // NOI18N
        ppGrafikPerTanggal.setText("Grafik Kunjungan Per Tanggal");
        ppGrafikPerTanggal.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        ppGrafikPerTanggal.setHorizontalTextPosition(javax.swing.SwingConstants.RIGHT);
        ppGrafikPerTanggal.setIconTextGap(5);
        ppGrafikPerTanggal.setName("ppGrafikPerTanggal"); // NOI18N
        ppGrafikPerTanggal.setPreferredSize(new java.awt.Dimension(225, 26));
        ppGrafikPerTanggal.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                ppGrafikPerTanggalActionPerformed(evt);
            }
        });
        jMenu2.add(ppGrafikPerTanggal);

        ppGrafikDemografi.setBackground(new java.awt.Color(255, 255, 255));
        ppGrafikDemografi.setFont(new java.awt.Font("Tahoma", 0, 11)); // NOI18N
        ppGrafikDemografi.setIcon(new javax.swing.ImageIcon(getClass().getResource("/picture/category.png"))); // NOI18N
        ppGrafikDemografi.setText("Demografi Pendaftar");
        ppGrafikDemografi.setActionCommand("Grafik Demografi Pasien");
        ppGrafikDemografi.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        ppGrafikDemografi.setHorizontalTextPosition(javax.swing.SwingConstants.RIGHT);
        ppGrafikDemografi.setIconTextGap(5);
        ppGrafikDemografi.setName("ppGrafikDemografi"); // NOI18N
        ppGrafikDemografi.setPreferredSize(new java.awt.Dimension(225, 26));
        ppGrafikDemografi.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                ppGrafikDemografiActionPerformed(evt);
            }
        });
        jMenu2.add(ppGrafikDemografi);

        jPopupMenu1.add(jMenu2);

        jMenu4.setBackground(new java.awt.Color(255, 255, 255));
        jMenu4.setIcon(new javax.swing.ImageIcon(getClass().getResource("/picture/category.png"))); // NOI18N
        jMenu4.setText("Surat-Surat");
        jMenu4.setFont(new java.awt.Font("Tahoma", 0, 11)); // NOI18N
        jMenu4.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        jMenu4.setHorizontalTextPosition(javax.swing.SwingConstants.RIGHT);
        jMenu4.setIconTextGap(5);
        jMenu4.setName("jMenu4"); // NOI18N
        jMenu4.setOpaque(true);
        jMenu4.setPreferredSize(new java.awt.Dimension(240, 26));

        MnIdentitasRM.setBackground(new java.awt.Color(255, 255, 255));
        MnIdentitasRM.setIcon(new javax.swing.ImageIcon(getClass().getResource("/picture/category.png"))); // NOI18N
        MnIdentitasRM.setText("Identitas RM Pasien");
        MnIdentitasRM.setFont(new java.awt.Font("Tahoma", 0, 11)); // NOI18N
        MnIdentitasRM.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        MnIdentitasRM.setHorizontalTextPosition(javax.swing.SwingConstants.RIGHT);
        MnIdentitasRM.setIconTextGap(5);
        MnIdentitasRM.setName("MnIdentitasRM"); // NOI18N
        MnIdentitasRM.setOpaque(true);
        MnIdentitasRM.setPreferredSize(new java.awt.Dimension(190, 26));

        MnlembarCover.setBackground(new java.awt.Color(255, 255, 255));
        MnlembarCover.setFont(new java.awt.Font("Tahoma", 0, 11)); // NOI18N
        MnlembarCover.setIcon(new javax.swing.ImageIcon(getClass().getResource("/picture/category.png"))); // NOI18N
        MnlembarCover.setText("Lembar Cover RM");
        MnlembarCover.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        MnlembarCover.setHorizontalTextPosition(javax.swing.SwingConstants.RIGHT);
        MnlembarCover.setIconTextGap(5);
        MnlembarCover.setName("MnlembarCover"); // NOI18N
        MnlembarCover.setPreferredSize(new java.awt.Dimension(130, 26));
        MnlembarCover.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                MnlembarCoverActionPerformed(evt);
            }
        });
        MnIdentitasRM.add(MnlembarCover);

        MnlembarRM5.setBackground(new java.awt.Color(255, 255, 255));
        MnlembarRM5.setFont(new java.awt.Font("Tahoma", 0, 11)); // NOI18N
        MnlembarRM5.setIcon(new javax.swing.ImageIcon(getClass().getResource("/picture/category.png"))); // NOI18N
        MnlembarRM5.setText("Lembar RM 5");
        MnlembarRM5.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        MnlembarRM5.setHorizontalTextPosition(javax.swing.SwingConstants.RIGHT);
        MnlembarRM5.setIconTextGap(5);
        MnlembarRM5.setName("MnlembarRM5"); // NOI18N
        MnlembarRM5.setPreferredSize(new java.awt.Dimension(130, 26));
        MnlembarRM5.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                MnlembarRM5ActionPerformed(evt);
            }
        });
        MnIdentitasRM.add(MnlembarRM5);

        MnlembarRM51.setBackground(new java.awt.Color(255, 255, 255));
        MnlembarRM51.setFont(new java.awt.Font("Tahoma", 0, 11)); // NOI18N
        MnlembarRM51.setIcon(new javax.swing.ImageIcon(getClass().getResource("/picture/category.png"))); // NOI18N
        MnlembarRM51.setText("Lembar RM 5.1");
        MnlembarRM51.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        MnlembarRM51.setHorizontalTextPosition(javax.swing.SwingConstants.RIGHT);
        MnlembarRM51.setIconTextGap(5);
        MnlembarRM51.setName("MnlembarRM51"); // NOI18N
        MnlembarRM51.setPreferredSize(new java.awt.Dimension(130, 26));
        MnlembarRM51.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                MnlembarRM51ActionPerformed(evt);
            }
        });
        MnIdentitasRM.add(MnlembarRM51);

        MnlembarRM52.setBackground(new java.awt.Color(255, 255, 255));
        MnlembarRM52.setFont(new java.awt.Font("Tahoma", 0, 11)); // NOI18N
        MnlembarRM52.setIcon(new javax.swing.ImageIcon(getClass().getResource("/picture/category.png"))); // NOI18N
        MnlembarRM52.setText("Lembar RM 5.2");
        MnlembarRM52.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        MnlembarRM52.setHorizontalTextPosition(javax.swing.SwingConstants.RIGHT);
        MnlembarRM52.setIconTextGap(5);
        MnlembarRM52.setName("MnlembarRM52"); // NOI18N
        MnlembarRM52.setPreferredSize(new java.awt.Dimension(130, 26));
        MnlembarRM52.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                MnlembarRM52ActionPerformed(evt);
            }
        });
        MnIdentitasRM.add(MnlembarRM52);

        MnlembarRM521.setBackground(new java.awt.Color(255, 255, 255));
        MnlembarRM521.setFont(new java.awt.Font("Tahoma", 0, 11)); // NOI18N
        MnlembarRM521.setIcon(new javax.swing.ImageIcon(getClass().getResource("/picture/category.png"))); // NOI18N
        MnlembarRM521.setText("Lembar RM 5.2.1");
        MnlembarRM521.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        MnlembarRM521.setHorizontalTextPosition(javax.swing.SwingConstants.RIGHT);
        MnlembarRM521.setIconTextGap(5);
        MnlembarRM521.setName("MnlembarRM521"); // NOI18N
        MnlembarRM521.setPreferredSize(new java.awt.Dimension(130, 26));
        MnlembarRM521.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                MnlembarRM521ActionPerformed(evt);
            }
        });
        MnIdentitasRM.add(MnlembarRM521);

        MnlembarRM6.setBackground(new java.awt.Color(255, 255, 255));
        MnlembarRM6.setFont(new java.awt.Font("Tahoma", 0, 11)); // NOI18N
        MnlembarRM6.setIcon(new javax.swing.ImageIcon(getClass().getResource("/picture/category.png"))); // NOI18N
        MnlembarRM6.setText("Lembar RM 6");
        MnlembarRM6.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        MnlembarRM6.setHorizontalTextPosition(javax.swing.SwingConstants.RIGHT);
        MnlembarRM6.setIconTextGap(5);
        MnlembarRM6.setName("MnlembarRM6"); // NOI18N
        MnlembarRM6.setPreferredSize(new java.awt.Dimension(130, 26));
        MnlembarRM6.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                MnlembarRM6ActionPerformed(evt);
            }
        });
        MnIdentitasRM.add(MnlembarRM6);

        MnlembarLabel.setBackground(new java.awt.Color(255, 255, 255));
        MnlembarLabel.setFont(new java.awt.Font("Tahoma", 0, 11)); // NOI18N
        MnlembarLabel.setIcon(new javax.swing.ImageIcon(getClass().getResource("/picture/category.png"))); // NOI18N
        MnlembarLabel.setText("Lembar Label");
        MnlembarLabel.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        MnlembarLabel.setHorizontalTextPosition(javax.swing.SwingConstants.RIGHT);
        MnlembarLabel.setIconTextGap(5);
        MnlembarLabel.setName("MnlembarLabel"); // NOI18N
        MnlembarLabel.setPreferredSize(new java.awt.Dimension(130, 26));
        MnlembarLabel.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                MnlembarLabelActionPerformed(evt);
            }
        });
        MnIdentitasRM.add(MnlembarLabel);

        jMenu4.add(MnIdentitasRM);

        CtkSuratTindakanDokter.setBackground(new java.awt.Color(255, 255, 255));
        CtkSuratTindakanDokter.setFont(new java.awt.Font("Tahoma", 0, 11)); // NOI18N
        CtkSuratTindakanDokter.setIcon(new javax.swing.ImageIcon(getClass().getResource("/picture/category.png"))); // NOI18N
        CtkSuratTindakanDokter.setText("Surat Tindakan Kedokteran");
        CtkSuratTindakanDokter.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        CtkSuratTindakanDokter.setHorizontalTextPosition(javax.swing.SwingConstants.RIGHT);
        CtkSuratTindakanDokter.setIconTextGap(5);
        CtkSuratTindakanDokter.setName("CtkSuratTindakanDokter"); // NOI18N
        CtkSuratTindakanDokter.setPreferredSize(new java.awt.Dimension(190, 26));
        CtkSuratTindakanDokter.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                CtkSuratTindakanDokterActionPerformed(evt);
            }
        });
        jMenu4.add(CtkSuratTindakanDokter);

        MnSuratAPS.setBackground(new java.awt.Color(255, 255, 255));
        MnSuratAPS.setFont(new java.awt.Font("Tahoma", 0, 11)); // NOI18N
        MnSuratAPS.setIcon(new javax.swing.ImageIcon(getClass().getResource("/picture/category.png"))); // NOI18N
        MnSuratAPS.setText("Surat Pernyataan APS IGD");
        MnSuratAPS.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        MnSuratAPS.setHorizontalTextPosition(javax.swing.SwingConstants.RIGHT);
        MnSuratAPS.setIconTextGap(5);
        MnSuratAPS.setName("MnSuratAPS"); // NOI18N
        MnSuratAPS.setPreferredSize(new java.awt.Dimension(190, 26));
        MnSuratAPS.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                MnSuratAPSActionPerformed(evt);
            }
        });
        jMenu4.add(MnSuratAPS);

        MnCetakSuratSakit1.setBackground(new java.awt.Color(255, 255, 255));
        MnCetakSuratSakit1.setFont(new java.awt.Font("Tahoma", 0, 11)); // NOI18N
        MnCetakSuratSakit1.setIcon(new javax.swing.ImageIcon(getClass().getResource("/picture/category.png"))); // NOI18N
        MnCetakSuratSakit1.setText("Surat Cuti Sakit");
        MnCetakSuratSakit1.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        MnCetakSuratSakit1.setHorizontalTextPosition(javax.swing.SwingConstants.RIGHT);
        MnCetakSuratSakit1.setIconTextGap(5);
        MnCetakSuratSakit1.setName("MnCetakSuratSakit1"); // NOI18N
        MnCetakSuratSakit1.setPreferredSize(new java.awt.Dimension(190, 26));
        MnCetakSuratSakit1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                MnCetakSuratSakit1ActionPerformed(evt);
            }
        });
        jMenu4.add(MnCetakSuratSakit1);

        MnCetakSuratSakit2.setBackground(new java.awt.Color(255, 255, 255));
        MnCetakSuratSakit2.setFont(new java.awt.Font("Tahoma", 0, 11)); // NOI18N
        MnCetakSuratSakit2.setIcon(new javax.swing.ImageIcon(getClass().getResource("/picture/category.png"))); // NOI18N
        MnCetakSuratSakit2.setText("Surat Keterangan Rawat Inap");
        MnCetakSuratSakit2.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        MnCetakSuratSakit2.setHorizontalTextPosition(javax.swing.SwingConstants.RIGHT);
        MnCetakSuratSakit2.setIconTextGap(5);
        MnCetakSuratSakit2.setName("MnCetakSuratSakit2"); // NOI18N
        MnCetakSuratSakit2.setPreferredSize(new java.awt.Dimension(190, 26));
        MnCetakSuratSakit2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                MnCetakSuratSakit2ActionPerformed(evt);
            }
        });
        jMenu4.add(MnCetakSuratSakit2);

        MnCetakRegister.setBackground(new java.awt.Color(255, 255, 255));
        MnCetakRegister.setFont(new java.awt.Font("Tahoma", 0, 11)); // NOI18N
        MnCetakRegister.setIcon(new javax.swing.ImageIcon(getClass().getResource("/picture/category.png"))); // NOI18N
        MnCetakRegister.setText("Bukti Register");
        MnCetakRegister.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        MnCetakRegister.setHorizontalTextPosition(javax.swing.SwingConstants.RIGHT);
        MnCetakRegister.setIconTextGap(5);
        MnCetakRegister.setName("MnCetakRegister"); // NOI18N
        MnCetakRegister.setPreferredSize(new java.awt.Dimension(190, 26));
        MnCetakRegister.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                MnCetakRegisterActionPerformed(evt);
            }
        });
        jMenu4.add(MnCetakRegister);

        MnPersetujuanMedis.setBackground(new java.awt.Color(255, 255, 255));
        MnPersetujuanMedis.setFont(new java.awt.Font("Tahoma", 0, 11)); // NOI18N
        MnPersetujuanMedis.setIcon(new javax.swing.ImageIcon(getClass().getResource("/picture/category.png"))); // NOI18N
        MnPersetujuanMedis.setText("Persetujuan Medis");
        MnPersetujuanMedis.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        MnPersetujuanMedis.setHorizontalTextPosition(javax.swing.SwingConstants.RIGHT);
        MnPersetujuanMedis.setIconTextGap(5);
        MnPersetujuanMedis.setName("MnPersetujuanMedis"); // NOI18N
        MnPersetujuanMedis.setPreferredSize(new java.awt.Dimension(190, 26));
        MnPersetujuanMedis.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                MnPersetujuanMedisActionPerformed(evt);
            }
        });
        jMenu4.add(MnPersetujuanMedis);

        MnLembarCasemix.setBackground(new java.awt.Color(255, 255, 255));
        MnLembarCasemix.setFont(new java.awt.Font("Tahoma", 0, 11)); // NOI18N
        MnLembarCasemix.setIcon(new javax.swing.ImageIcon(getClass().getResource("/picture/category.png"))); // NOI18N
        MnLembarCasemix.setText("Lembar Casemix");
        MnLembarCasemix.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        MnLembarCasemix.setHorizontalTextPosition(javax.swing.SwingConstants.RIGHT);
        MnLembarCasemix.setIconTextGap(5);
        MnLembarCasemix.setName("MnLembarCasemix"); // NOI18N
        MnLembarCasemix.setPreferredSize(new java.awt.Dimension(190, 26));
        MnLembarCasemix.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                MnLembarCasemixActionPerformed(evt);
            }
        });
        jMenu4.add(MnLembarCasemix);

        MnCetakSuratSakit.setBackground(new java.awt.Color(255, 255, 255));
        MnCetakSuratSakit.setFont(new java.awt.Font("Tahoma", 0, 11)); // NOI18N
        MnCetakSuratSakit.setIcon(new javax.swing.ImageIcon(getClass().getResource("/picture/category.png"))); // NOI18N
        MnCetakSuratSakit.setText("Surat Sakit");
        MnCetakSuratSakit.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        MnCetakSuratSakit.setHorizontalTextPosition(javax.swing.SwingConstants.RIGHT);
        MnCetakSuratSakit.setIconTextGap(5);
        MnCetakSuratSakit.setName("MnCetakSuratSakit"); // NOI18N
        MnCetakSuratSakit.setPreferredSize(new java.awt.Dimension(190, 26));
        MnCetakSuratSakit.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                MnCetakSuratSakitActionPerformed(evt);
            }
        });
        jMenu4.add(MnCetakSuratSakit);

        jPopupMenu1.add(jMenu4);

        jMenu3.setBackground(new java.awt.Color(255, 255, 255));
        jMenu3.setIcon(new javax.swing.ImageIcon(getClass().getResource("/picture/category.png"))); // NOI18N
        jMenu3.setText("Check List Kelengkapan Pendaftaran");
        jMenu3.setFont(new java.awt.Font("Tahoma", 0, 11)); // NOI18N
        jMenu3.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        jMenu3.setHorizontalTextPosition(javax.swing.SwingConstants.RIGHT);
        jMenu3.setIconTextGap(5);
        jMenu3.setName("jMenu3"); // NOI18N
        jMenu3.setOpaque(true);
        jMenu3.setPreferredSize(new java.awt.Dimension(240, 26));

        MnCheckList.setBackground(new java.awt.Color(255, 255, 255));
        MnCheckList.setFont(new java.awt.Font("Tahoma", 0, 11)); // NOI18N
        MnCheckList.setIcon(new javax.swing.ImageIcon(getClass().getResource("/picture/category.png"))); // NOI18N
        MnCheckList.setText("Check List Kelengkapan Pendaftaran Kanan");
        MnCheckList.setToolTipText("");
        MnCheckList.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        MnCheckList.setHorizontalTextPosition(javax.swing.SwingConstants.RIGHT);
        MnCheckList.setIconTextGap(5);
        MnCheckList.setName("MnCheckList"); // NOI18N
        MnCheckList.setPreferredSize(new java.awt.Dimension(300, 26));
        MnCheckList.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                MnCheckListActionPerformed(evt);
            }
        });
        jMenu3.add(MnCheckList);

        MnCheckList1.setBackground(new java.awt.Color(255, 255, 255));
        MnCheckList1.setFont(new java.awt.Font("Tahoma", 0, 11)); // NOI18N
        MnCheckList1.setIcon(new javax.swing.ImageIcon(getClass().getResource("/picture/category.png"))); // NOI18N
        MnCheckList1.setText("Check List Kelengkapan Pendaftaran Kiri");
        MnCheckList1.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        MnCheckList1.setHorizontalTextPosition(javax.swing.SwingConstants.RIGHT);
        MnCheckList1.setIconTextGap(5);
        MnCheckList1.setName("MnCheckList1"); // NOI18N
        MnCheckList1.setPreferredSize(new java.awt.Dimension(300, 26));
        MnCheckList1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                MnCheckList1ActionPerformed(evt);
            }
        });
        jMenu3.add(MnCheckList1);

        MnCheckList2.setBackground(new java.awt.Color(255, 255, 255));
        MnCheckList2.setFont(new java.awt.Font("Tahoma", 0, 11)); // NOI18N
        MnCheckList2.setIcon(new javax.swing.ImageIcon(getClass().getResource("/picture/category.png"))); // NOI18N
        MnCheckList2.setText("Check List Kelengkapan Pendaftaran Kanan+Tracker");
        MnCheckList2.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        MnCheckList2.setHorizontalTextPosition(javax.swing.SwingConstants.RIGHT);
        MnCheckList2.setIconTextGap(5);
        MnCheckList2.setName("MnCheckList2"); // NOI18N
        MnCheckList2.setPreferredSize(new java.awt.Dimension(300, 26));
        MnCheckList2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                MnCheckList2ActionPerformed(evt);
            }
        });
        jMenu3.add(MnCheckList2);

        MnCheckList3.setBackground(new java.awt.Color(255, 255, 255));
        MnCheckList3.setFont(new java.awt.Font("Tahoma", 0, 11)); // NOI18N
        MnCheckList3.setIcon(new javax.swing.ImageIcon(getClass().getResource("/picture/category.png"))); // NOI18N
        MnCheckList3.setText("Check List Kelengkapan Pendaftaran Kiri+Tracker");
        MnCheckList3.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        MnCheckList3.setHorizontalTextPosition(javax.swing.SwingConstants.RIGHT);
        MnCheckList3.setIconTextGap(5);
        MnCheckList3.setName("MnCheckList3"); // NOI18N
        MnCheckList3.setPreferredSize(new java.awt.Dimension(300, 26));
        MnCheckList3.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                MnCheckList3ActionPerformed(evt);
            }
        });
        jMenu3.add(MnCheckList3);

        jPopupMenu1.add(jMenu3);

        jMenu5.setBackground(new java.awt.Color(255, 255, 255));
        jMenu5.setIcon(new javax.swing.ImageIcon(getClass().getResource("/picture/category.png"))); // NOI18N
        jMenu5.setText("Lembar Periksa Pasien");
        jMenu5.setFont(new java.awt.Font("Tahoma", 0, 11)); // NOI18N
        jMenu5.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        jMenu5.setHorizontalTextPosition(javax.swing.SwingConstants.RIGHT);
        jMenu5.setIconTextGap(5);
        jMenu5.setName("jMenu5"); // NOI18N
        jMenu5.setOpaque(true);
        jMenu5.setPreferredSize(new java.awt.Dimension(240, 26));

        MnCheckList4.setBackground(new java.awt.Color(255, 255, 255));
        MnCheckList4.setFont(new java.awt.Font("Tahoma", 0, 11)); // NOI18N
        MnCheckList4.setIcon(new javax.swing.ImageIcon(getClass().getResource("/picture/category.png"))); // NOI18N
        MnCheckList4.setText("Lembar Periksa Pasien Kanan");
        MnCheckList4.setToolTipText("");
        MnCheckList4.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        MnCheckList4.setHorizontalTextPosition(javax.swing.SwingConstants.RIGHT);
        MnCheckList4.setIconTextGap(5);
        MnCheckList4.setName("MnCheckList4"); // NOI18N
        MnCheckList4.setPreferredSize(new java.awt.Dimension(200, 26));
        MnCheckList4.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                MnCheckList4ActionPerformed(evt);
            }
        });
        jMenu5.add(MnCheckList4);

        MnCheckList5.setBackground(new java.awt.Color(255, 255, 255));
        MnCheckList5.setFont(new java.awt.Font("Tahoma", 0, 11)); // NOI18N
        MnCheckList5.setIcon(new javax.swing.ImageIcon(getClass().getResource("/picture/category.png"))); // NOI18N
        MnCheckList5.setText("Lembar Periksa Pasien Kiri");
        MnCheckList5.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        MnCheckList5.setHorizontalTextPosition(javax.swing.SwingConstants.RIGHT);
        MnCheckList5.setIconTextGap(5);
        MnCheckList5.setName("MnCheckList5"); // NOI18N
        MnCheckList5.setPreferredSize(new java.awt.Dimension(200, 26));
        MnCheckList5.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                MnCheckList5ActionPerformed(evt);
            }
        });
        jMenu5.add(MnCheckList5);

        MnCheckList6.setBackground(new java.awt.Color(255, 255, 255));
        MnCheckList6.setFont(new java.awt.Font("Tahoma", 0, 11)); // NOI18N
        MnCheckList6.setIcon(new javax.swing.ImageIcon(getClass().getResource("/picture/category.png"))); // NOI18N
        MnCheckList6.setText("Lembar Periksa Pasien Kanan 2");
        MnCheckList6.setToolTipText("");
        MnCheckList6.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        MnCheckList6.setHorizontalTextPosition(javax.swing.SwingConstants.RIGHT);
        MnCheckList6.setIconTextGap(5);
        MnCheckList6.setName("MnCheckList6"); // NOI18N
        MnCheckList6.setPreferredSize(new java.awt.Dimension(200, 26));
        MnCheckList6.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                MnCheckList6ActionPerformed(evt);
            }
        });
        jMenu5.add(MnCheckList6);

        MnCheckList7.setBackground(new java.awt.Color(255, 255, 255));
        MnCheckList7.setFont(new java.awt.Font("Tahoma", 0, 11)); // NOI18N
        MnCheckList7.setIcon(new javax.swing.ImageIcon(getClass().getResource("/picture/category.png"))); // NOI18N
        MnCheckList7.setText("Lembar Periksa Pasien Kiri 2");
        MnCheckList7.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        MnCheckList7.setHorizontalTextPosition(javax.swing.SwingConstants.RIGHT);
        MnCheckList7.setIconTextGap(5);
        MnCheckList7.setName("MnCheckList7"); // NOI18N
        MnCheckList7.setPreferredSize(new java.awt.Dimension(200, 26));
        MnCheckList7.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                MnCheckList7ActionPerformed(evt);
            }
        });
        jMenu5.add(MnCheckList7);

        jPopupMenu1.add(jMenu5);

        MnBridging.setBackground(new java.awt.Color(255, 255, 255));
        MnBridging.setIcon(new javax.swing.ImageIcon(getClass().getResource("/picture/category.png"))); // NOI18N
        MnBridging.setText("Bridging");
        MnBridging.setFont(new java.awt.Font("Tahoma", 0, 11)); // NOI18N
        MnBridging.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        MnBridging.setHorizontalTextPosition(javax.swing.SwingConstants.RIGHT);
        MnBridging.setIconTextGap(5);
        MnBridging.setName("MnBridging"); // NOI18N
        MnBridging.setOpaque(true);
        MnBridging.setPreferredSize(new java.awt.Dimension(240, 26));

        ppPasienCorona.setBackground(new java.awt.Color(255, 255, 255));
        ppPasienCorona.setFont(new java.awt.Font("Tahoma", 0, 11)); // NOI18N
        ppPasienCorona.setForeground(new java.awt.Color(50, 50, 50));
        ppPasienCorona.setIcon(new javax.swing.ImageIcon(getClass().getResource("/picture/category.png"))); // NOI18N
        ppPasienCorona.setText("Pasien Corona Kemenkes");
        ppPasienCorona.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        ppPasienCorona.setHorizontalTextPosition(javax.swing.SwingConstants.RIGHT);
        ppPasienCorona.setName("ppPasienCorona"); // NOI18N
        ppPasienCorona.setPreferredSize(new java.awt.Dimension(180, 26));
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
        MnSEP.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        MnSEP.setHorizontalTextPosition(javax.swing.SwingConstants.RIGHT);
        MnSEP.setIconTextGap(5);
        MnSEP.setName("MnSEP"); // NOI18N
        MnSEP.setPreferredSize(new java.awt.Dimension(170, 26));
        MnSEP.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                MnSEPActionPerformed(evt);
            }
        });
        MnBridging.add(MnSEP);

        MnSJP.setBackground(new java.awt.Color(255, 255, 255));
        MnSJP.setFont(new java.awt.Font("Tahoma", 0, 11)); // NOI18N
        MnSJP.setIcon(new javax.swing.ImageIcon(getClass().getResource("/picture/category.png"))); // NOI18N
        MnSJP.setText("Bridging SJP Inhealth");
        MnSJP.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        MnSJP.setHorizontalTextPosition(javax.swing.SwingConstants.RIGHT);
        MnSJP.setIconTextGap(5);
        MnSJP.setName("MnSJP"); // NOI18N
        MnSJP.setPreferredSize(new java.awt.Dimension(170, 26));
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
        MnSEPJamkesda.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        MnSEPJamkesda.setHorizontalTextPosition(javax.swing.SwingConstants.RIGHT);
        MnSEPJamkesda.setIconTextGap(5);
        MnSEPJamkesda.setName("MnSEPJamkesda"); // NOI18N
        MnSEPJamkesda.setPreferredSize(new java.awt.Dimension(170, 26));
        MnSEPJamkesda.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                MnSEPJamkesdaActionPerformed(evt);
            }
        });
        MnBridging.add(MnSEPJamkesda);

        MnJAMPERSAL.setBackground(new java.awt.Color(255, 255, 255));
        MnJAMPERSAL.setFont(new java.awt.Font("Tahoma", 0, 11)); // NOI18N
        MnJAMPERSAL.setIcon(new javax.swing.ImageIcon(getClass().getResource("/picture/category.png"))); // NOI18N
        MnJAMPERSAL.setText("Bridging SEP Jampersal");
        MnJAMPERSAL.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        MnJAMPERSAL.setHorizontalTextPosition(javax.swing.SwingConstants.RIGHT);
        MnJAMPERSAL.setIconTextGap(5);
        MnJAMPERSAL.setName("MnJAMPERSAL"); // NOI18N
        MnJAMPERSAL.setPreferredSize(new java.awt.Dimension(170, 26));
        MnJAMPERSAL.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                MnJAMPERSALActionPerformed(evt);
            }
        });
        MnBridging.add(MnJAMPERSAL);

        MnRujukSisrute.setBackground(new java.awt.Color(255, 255, 255));
        MnRujukSisrute.setFont(new java.awt.Font("Tahoma", 0, 11)); // NOI18N
        MnRujukSisrute.setIcon(new javax.swing.ImageIcon(getClass().getResource("/picture/category.png"))); // NOI18N
        MnRujukSisrute.setText("Rujuk Keluar Via Sisrute");
        MnRujukSisrute.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        MnRujukSisrute.setHorizontalTextPosition(javax.swing.SwingConstants.RIGHT);
        MnRujukSisrute.setIconTextGap(5);
        MnRujukSisrute.setName("MnRujukSisrute"); // NOI18N
        MnRujukSisrute.setPreferredSize(new java.awt.Dimension(180, 26));
        MnRujukSisrute.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                MnRujukSisruteActionPerformed(evt);
            }
        });
        MnBridging.add(MnRujukSisrute);

        jPopupMenu1.add(MnBridging);

        MnPermintaan.setBackground(new java.awt.Color(255, 255, 255));
        MnPermintaan.setForeground(new java.awt.Color(0, 0, 0));
        MnPermintaan.setIcon(new javax.swing.ImageIcon(getClass().getResource("/picture/category.png"))); // NOI18N
        MnPermintaan.setText("Permintaan");
        MnPermintaan.setFont(new java.awt.Font("Tahoma", 0, 11)); // NOI18N
        MnPermintaan.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        MnPermintaan.setHorizontalTextPosition(javax.swing.SwingConstants.RIGHT);
        MnPermintaan.setIconTextGap(5);
        MnPermintaan.setName("MnPermintaan"); // NOI18N
        MnPermintaan.setOpaque(true);
        MnPermintaan.setPreferredSize(new java.awt.Dimension(240, 26));

        MnSKDPBPJS.setBackground(new java.awt.Color(255, 255, 255));
        MnSKDPBPJS.setFont(new java.awt.Font("Tahoma", 0, 11)); // NOI18N
        MnSKDPBPJS.setForeground(new java.awt.Color(0, 0, 0));
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
        MnPermintaan.add(MnSKDPBPJS);

        MnPermintaanLab.setBackground(new java.awt.Color(255, 255, 255));
        MnPermintaanLab.setFont(new java.awt.Font("Tahoma", 0, 11)); // NOI18N
        MnPermintaanLab.setForeground(new java.awt.Color(0, 0, 0));
        MnPermintaanLab.setIcon(new javax.swing.ImageIcon(getClass().getResource("/picture/category.png"))); // NOI18N
        MnPermintaanLab.setText("Pemeriksaan Lab");
        MnPermintaanLab.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        MnPermintaanLab.setHorizontalTextPosition(javax.swing.SwingConstants.RIGHT);
        MnPermintaanLab.setIconTextGap(5);
        MnPermintaanLab.setName("MnPermintaanLab"); // NOI18N
        MnPermintaanLab.setPreferredSize(new java.awt.Dimension(150, 26));
        MnPermintaanLab.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                MnPermintaanLabActionPerformed(evt);
            }
        });
        MnPermintaan.add(MnPermintaanLab);

        MnPermintaanRadiologi.setBackground(new java.awt.Color(255, 255, 255));
        MnPermintaanRadiologi.setFont(new java.awt.Font("Tahoma", 0, 11)); // NOI18N
        MnPermintaanRadiologi.setForeground(new java.awt.Color(0, 0, 0));
        MnPermintaanRadiologi.setIcon(new javax.swing.ImageIcon(getClass().getResource("/picture/category.png"))); // NOI18N
        MnPermintaanRadiologi.setText("Pemeriksaan Radiologi");
        MnPermintaanRadiologi.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        MnPermintaanRadiologi.setHorizontalTextPosition(javax.swing.SwingConstants.RIGHT);
        MnPermintaanRadiologi.setIconTextGap(5);
        MnPermintaanRadiologi.setName("MnPermintaanRadiologi"); // NOI18N
        MnPermintaanRadiologi.setPreferredSize(new java.awt.Dimension(150, 26));
        MnPermintaanRadiologi.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                MnPermintaanRadiologiActionPerformed(evt);
            }
        });
        MnPermintaan.add(MnPermintaanRadiologi);

        MnMati.setBackground(new java.awt.Color(255, 255, 255));
        MnMati.setFont(new java.awt.Font("Tahoma", 0, 11)); // NOI18N
        MnMati.setIcon(new javax.swing.ImageIcon(getClass().getResource("/picture/category.png"))); // NOI18N
        MnMati.setText("Edit Data Kematian");
        MnMati.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        MnMati.setHorizontalTextPosition(javax.swing.SwingConstants.RIGHT);
        MnMati.setIconTextGap(5);
        MnMati.setName("MnMati"); // NOI18N
        MnMati.setPreferredSize(new java.awt.Dimension(150, 26));
        MnMati.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                MnMatiActionPerformed(evt);
            }
        });
        MnPermintaan.add(MnMati);

        jPopupMenu1.add(MnPermintaan);

        MnRekamMedis.setBackground(new java.awt.Color(255, 255, 255));
        MnRekamMedis.setIcon(new javax.swing.ImageIcon(getClass().getResource("/picture/category.png"))); // NOI18N
        MnRekamMedis.setText("Data Rekam Medis");
        MnRekamMedis.setFont(new java.awt.Font("Tahoma", 0, 11)); // NOI18N
        MnRekamMedis.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        MnRekamMedis.setHorizontalTextPosition(javax.swing.SwingConstants.RIGHT);
        MnRekamMedis.setIconTextGap(5);
        MnRekamMedis.setName("MnRekamMedis"); // NOI18N
        MnRekamMedis.setOpaque(true);
        MnRekamMedis.setPreferredSize(new java.awt.Dimension(240, 26));

        MnSuratTindakanDokter.setBackground(new java.awt.Color(255, 255, 255));
        MnSuratTindakanDokter.setFont(new java.awt.Font("Tahoma", 0, 11)); // NOI18N
        MnSuratTindakanDokter.setIcon(new javax.swing.ImageIcon(getClass().getResource("/picture/category.png"))); // NOI18N
        MnSuratTindakanDokter.setText("Surat Tindakan Kedokteran");
        MnSuratTindakanDokter.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        MnSuratTindakanDokter.setHorizontalTextPosition(javax.swing.SwingConstants.RIGHT);
        MnSuratTindakanDokter.setIconTextGap(5);
        MnSuratTindakanDokter.setName("MnSuratTindakanDokter"); // NOI18N
        MnSuratTindakanDokter.setPreferredSize(new java.awt.Dimension(245, 26));
        MnSuratTindakanDokter.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                MnSuratTindakanDokterActionPerformed(evt);
            }
        });
        MnRekamMedis.add(MnSuratTindakanDokter);

        MnDiagnosa.setBackground(new java.awt.Color(255, 255, 255));
        MnDiagnosa.setFont(new java.awt.Font("Tahoma", 0, 11)); // NOI18N
        MnDiagnosa.setIcon(new javax.swing.ImageIcon(getClass().getResource("/picture/category.png"))); // NOI18N
        MnDiagnosa.setText("Diagnosa Pasien");
        MnDiagnosa.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        MnDiagnosa.setHorizontalTextPosition(javax.swing.SwingConstants.RIGHT);
        MnDiagnosa.setIconTextGap(5);
        MnDiagnosa.setName("MnDiagnosa"); // NOI18N
        MnDiagnosa.setPreferredSize(new java.awt.Dimension(245, 26));
        MnDiagnosa.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                MnDiagnosaActionPerformed(evt);
            }
        });
        MnRekamMedis.add(MnDiagnosa);

        MnDataHAIs.setBackground(new java.awt.Color(255, 255, 255));
        MnDataHAIs.setFont(new java.awt.Font("Tahoma", 0, 11)); // NOI18N
        MnDataHAIs.setIcon(new javax.swing.ImageIcon(getClass().getResource("/picture/category.png"))); // NOI18N
        MnDataHAIs.setText("Data HAIs");
        MnDataHAIs.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        MnDataHAIs.setHorizontalTextPosition(javax.swing.SwingConstants.RIGHT);
        MnDataHAIs.setIconTextGap(5);
        MnDataHAIs.setName("MnDataHAIs"); // NOI18N
        MnDataHAIs.setPreferredSize(new java.awt.Dimension(245, 26));
        MnDataHAIs.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                MnDataHAIsBtnPrintActionPerformed(evt);
            }
        });
        MnRekamMedis.add(MnDataHAIs);

        MnCatatanPasien.setBackground(new java.awt.Color(255, 255, 255));
        MnCatatanPasien.setFont(new java.awt.Font("Tahoma", 0, 11)); // NOI18N
        MnCatatanPasien.setIcon(new javax.swing.ImageIcon(getClass().getResource("/picture/category.png"))); // NOI18N
        MnCatatanPasien.setText("Catatan Untuk Pasien");
        MnCatatanPasien.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        MnCatatanPasien.setHorizontalTextPosition(javax.swing.SwingConstants.RIGHT);
        MnCatatanPasien.setIconTextGap(5);
        MnCatatanPasien.setName("MnCatatanPasien"); // NOI18N
        MnCatatanPasien.setPreferredSize(new java.awt.Dimension(245, 26));
        MnCatatanPasien.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                MnCatatanPasienBtnPrintActionPerformed(evt);
            }
        });
        MnRekamMedis.add(MnCatatanPasien);

        MnDataPonek.setBackground(new java.awt.Color(255, 255, 255));
        MnDataPonek.setFont(new java.awt.Font("Tahoma", 0, 11)); // NOI18N
        MnDataPonek.setIcon(new javax.swing.ImageIcon(getClass().getResource("/picture/category.png"))); // NOI18N
        MnDataPonek.setText("Data Ponek");
        MnDataPonek.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        MnDataPonek.setHorizontalTextPosition(javax.swing.SwingConstants.RIGHT);
        MnDataPonek.setIconTextGap(5);
        MnDataPonek.setName("MnDataPonek"); // NOI18N
        MnDataPonek.setPreferredSize(new java.awt.Dimension(245, 26));
        MnDataPonek.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                MnDataPonekBtnPrintActionPerformed(evt);
            }
        });
        MnRekamMedis.add(MnDataPonek);

        ppPerawatanCorona.setBackground(new java.awt.Color(255, 255, 255));
        ppPerawatanCorona.setFont(new java.awt.Font("Tahoma", 0, 11)); // NOI18N
        ppPerawatanCorona.setForeground(new java.awt.Color(0, 0, 0));
        ppPerawatanCorona.setIcon(new javax.swing.ImageIcon(getClass().getResource("/picture/category.png"))); // NOI18N
        ppPerawatanCorona.setText("Perawatan Pasien Corona INACBG");
        ppPerawatanCorona.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        ppPerawatanCorona.setHorizontalTextPosition(javax.swing.SwingConstants.RIGHT);
        ppPerawatanCorona.setName("ppPerawatanCorona"); // NOI18N
        ppPerawatanCorona.setPreferredSize(new java.awt.Dimension(245, 26));
        ppPerawatanCorona.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                ppPerawatanCoronaBtnPrintActionPerformed(evt);
            }
        });
        MnRekamMedis.add(ppPerawatanCorona);

        MnPeniliaianAwalKeperawatanRalan.setBackground(new java.awt.Color(255, 255, 255));
        MnPeniliaianAwalKeperawatanRalan.setFont(new java.awt.Font("Tahoma", 0, 11)); // NOI18N
        MnPeniliaianAwalKeperawatanRalan.setIcon(new javax.swing.ImageIcon(getClass().getResource("/picture/category.png"))); // NOI18N
        MnPeniliaianAwalKeperawatanRalan.setText("Peniliaian Awal Keperawatan (Assesmen)");
        MnPeniliaianAwalKeperawatanRalan.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        MnPeniliaianAwalKeperawatanRalan.setHorizontalTextPosition(javax.swing.SwingConstants.RIGHT);
        MnPeniliaianAwalKeperawatanRalan.setIconTextGap(5);
        MnPeniliaianAwalKeperawatanRalan.setName("MnPeniliaianAwalKeperawatanRalan"); // NOI18N
        MnPeniliaianAwalKeperawatanRalan.setPreferredSize(new java.awt.Dimension(245, 26));
        MnPeniliaianAwalKeperawatanRalan.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                MnPeniliaianAwalKeperawatanRalanBtnPrintActionPerformed(evt);
            }
        });
        MnRekamMedis.add(MnPeniliaianAwalKeperawatanRalan);

        MnDataTriaseIGD.setBackground(new java.awt.Color(255, 255, 255));
        MnDataTriaseIGD.setFont(new java.awt.Font("Tahoma", 0, 11)); // NOI18N
        MnDataTriaseIGD.setIcon(new javax.swing.ImageIcon(getClass().getResource("/picture/category.png"))); // NOI18N
        MnDataTriaseIGD.setText("Triase Gawat Darurat");
        MnDataTriaseIGD.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        MnDataTriaseIGD.setHorizontalTextPosition(javax.swing.SwingConstants.RIGHT);
        MnDataTriaseIGD.setIconTextGap(5);
        MnDataTriaseIGD.setName("MnDataTriaseIGD"); // NOI18N
        MnDataTriaseIGD.setPreferredSize(new java.awt.Dimension(245, 26));
        MnDataTriaseIGD.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                MnDataTriaseIGDBtnPrintActionPerformed(evt);
            }
        });
        MnRekamMedis.add(MnDataTriaseIGD);

        ppRiwayat.setBackground(new java.awt.Color(255, 255, 255));
        ppRiwayat.setFont(new java.awt.Font("Tahoma", 0, 11)); // NOI18N
        ppRiwayat.setIcon(new javax.swing.ImageIcon(getClass().getResource("/picture/category.png"))); // NOI18N
        ppRiwayat.setText("Riwayat Perawatan");
        ppRiwayat.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        ppRiwayat.setHorizontalTextPosition(javax.swing.SwingConstants.RIGHT);
        ppRiwayat.setIconTextGap(5);
        ppRiwayat.setName("ppRiwayat"); // NOI18N
        ppRiwayat.setPreferredSize(new java.awt.Dimension(245, 26));
        ppRiwayat.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                ppRiwayatBtnPrintActionPerformed(evt);
            }
        });
        MnRekamMedis.add(ppRiwayat);

        jPopupMenu1.add(MnRekamMedis);

        MnBilling.setBackground(new java.awt.Color(255, 255, 255));
        MnBilling.setFont(new java.awt.Font("Tahoma", 0, 11)); // NOI18N
        MnBilling.setIcon(new javax.swing.ImageIcon(getClass().getResource("/picture/category.png"))); // NOI18N
        MnBilling.setText("Billing/Pembayaran Pasien");
        MnBilling.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        MnBilling.setHorizontalTextPosition(javax.swing.SwingConstants.RIGHT);
        MnBilling.setIconTextGap(5);
        MnBilling.setName("MnBilling"); // NOI18N
        MnBilling.setPreferredSize(new java.awt.Dimension(240, 26));
        MnBilling.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                MnBillingActionPerformed(evt);
            }
        });
        jPopupMenu1.add(MnBilling);

        MnBarcode.setBackground(new java.awt.Color(255, 255, 255));
        MnBarcode.setFont(new java.awt.Font("Tahoma", 0, 11)); // NOI18N
        MnBarcode.setIcon(new javax.swing.ImageIcon(getClass().getResource("/picture/category.png"))); // NOI18N
        MnBarcode.setText("Barcode Perawatan");
        MnBarcode.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        MnBarcode.setHorizontalTextPosition(javax.swing.SwingConstants.RIGHT);
        MnBarcode.setIconTextGap(5);
        MnBarcode.setName("MnBarcode"); // NOI18N
        MnBarcode.setPreferredSize(new java.awt.Dimension(240, 26));
        MnBarcode.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                MnBarcodeActionPerformed(evt);
            }
        });
        jPopupMenu1.add(MnBarcode);

        DlgSakit.setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);
        DlgSakit.setName("DlgSakit"); // NOI18N
        DlgSakit.setUndecorated(true);
        DlgSakit.setResizable(false);

        internalFrame3.setBorder(javax.swing.BorderFactory.createTitledBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 153, 255), 3), "::[ Cetak Surat Sakit ]::", javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Tahoma", 1, 12), new java.awt.Color(0, 0, 0))); // NOI18N
        internalFrame3.setName("internalFrame3"); // NOI18N
        internalFrame3.setLayout(new java.awt.BorderLayout(1, 1));

        panelBiasa2.setName("panelBiasa2"); // NOI18N
        panelBiasa2.setLayout(null);

        TglSakit1.setEditable(false);
        TglSakit1.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "12-08-2021" }));
        TglSakit1.setDisplayFormat("dd-MM-yyyy");
        TglSakit1.setName("TglSakit1"); // NOI18N
        TglSakit1.setOpaque(false);
        panelBiasa2.add(TglSakit1);
        TglSakit1.setBounds(70, 10, 100, 23);

        jLabel31.setForeground(new java.awt.Color(0, 0, 0));
        jLabel31.setText("Lama Sakit :");
        jLabel31.setName("jLabel31"); // NOI18N
        panelBiasa2.add(jLabel31);
        jLabel31.setBounds(297, 10, 110, 23);

        BtnPrint2.setForeground(new java.awt.Color(0, 0, 0));
        BtnPrint2.setIcon(new javax.swing.ImageIcon(getClass().getResource("/picture/b_print.png"))); // NOI18N
        BtnPrint2.setMnemonic('T');
        BtnPrint2.setText("Cetak");
        BtnPrint2.setToolTipText("Alt+T");
        BtnPrint2.setName("BtnPrint2"); // NOI18N
        BtnPrint2.setPreferredSize(new java.awt.Dimension(100, 30));
        BtnPrint2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                BtnPrint2ActionPerformed(evt);
            }
        });
        panelBiasa2.add(BtnPrint2);
        BtnPrint2.setBounds(10, 50, 100, 30);

        BtnKeluar2.setForeground(new java.awt.Color(0, 0, 0));
        BtnKeluar2.setIcon(new javax.swing.ImageIcon(getClass().getResource("/picture/exit.png"))); // NOI18N
        BtnKeluar2.setMnemonic('K');
        BtnKeluar2.setText("Keluar");
        BtnKeluar2.setToolTipText("Alt+K");
        BtnKeluar2.setName("BtnKeluar2"); // NOI18N
        BtnKeluar2.setPreferredSize(new java.awt.Dimension(100, 30));
        BtnKeluar2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                BtnKeluar2ActionPerformed(evt);
            }
        });
        panelBiasa2.add(BtnKeluar2);
        BtnKeluar2.setBounds(430, 50, 100, 30);

        jLabel32.setForeground(new java.awt.Color(0, 0, 0));
        jLabel32.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel32.setText("s/d");
        jLabel32.setName("jLabel32"); // NOI18N
        panelBiasa2.add(jLabel32);
        jLabel32.setBounds(176, 10, 20, 23);

        TglSakit2.setEditable(false);
        TglSakit2.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "12-08-2021" }));
        TglSakit2.setDisplayFormat("dd-MM-yyyy");
        TglSakit2.setName("TglSakit2"); // NOI18N
        TglSakit2.setOpaque(false);
        panelBiasa2.add(TglSakit2);
        TglSakit2.setBounds(200, 10, 100, 23);

        jLabel33.setForeground(new java.awt.Color(0, 0, 0));
        jLabel33.setText("Tanggal :");
        jLabel33.setName("jLabel33"); // NOI18N
        panelBiasa2.add(jLabel33);
        jLabel33.setBounds(0, 10, 66, 23);

        lmsakit.setForeground(new java.awt.Color(0, 0, 0));
        lmsakit.setHighlighter(null);
        lmsakit.setName("lmsakit"); // NOI18N
        panelBiasa2.add(lmsakit);
        lmsakit.setBounds(410, 10, 110, 23);

        internalFrame3.add(panelBiasa2, java.awt.BorderLayout.CENTER);

        DlgSakit.getContentPane().add(internalFrame3, java.awt.BorderLayout.CENTER);

        DlgDemografi.setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);
        DlgDemografi.setName("DlgDemografi"); // NOI18N
        DlgDemografi.setUndecorated(true);
        DlgDemografi.setResizable(false);

        internalFrame4.setBorder(javax.swing.BorderFactory.createTitledBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 153, 255), 3), "::[ Demografi Pendaftar ]::"));
        internalFrame4.setName("internalFrame4"); // NOI18N
        internalFrame4.setLayout(new java.awt.BorderLayout(1, 1));

        panelBiasa3.setName("panelBiasa3"); // NOI18N
        panelBiasa3.setLayout(null);

        BtnPrint3.setForeground(new java.awt.Color(0, 0, 0));
        BtnPrint3.setIcon(new javax.swing.ImageIcon(getClass().getResource("/picture/b_print.png"))); // NOI18N
        BtnPrint3.setMnemonic('G');
        BtnPrint3.setText("Grafik");
        BtnPrint3.setToolTipText("Alt+G");
        BtnPrint3.setName("BtnPrint3"); // NOI18N
        BtnPrint3.setPreferredSize(new java.awt.Dimension(100, 30));
        BtnPrint3.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                BtnPrint3ActionPerformed(evt);
            }
        });
        BtnPrint3.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                BtnPrint3KeyPressed(evt);
            }
        });
        panelBiasa3.add(BtnPrint3);
        BtnPrint3.setBounds(110, 110, 100, 30);

        BtnKeluar3.setForeground(new java.awt.Color(0, 0, 0));
        BtnKeluar3.setIcon(new javax.swing.ImageIcon(getClass().getResource("/picture/exit.png"))); // NOI18N
        BtnKeluar3.setMnemonic('K');
        BtnKeluar3.setText("Keluar");
        BtnKeluar3.setToolTipText("Alt+K");
        BtnKeluar3.setName("BtnKeluar3"); // NOI18N
        BtnKeluar3.setPreferredSize(new java.awt.Dimension(100, 30));
        BtnKeluar3.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                BtnKeluar3ActionPerformed(evt);
            }
        });
        BtnKeluar3.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                BtnKeluar3KeyPressed(evt);
            }
        });
        panelBiasa3.add(BtnKeluar3);
        BtnKeluar3.setBounds(430, 110, 100, 30);

        Kelurahan2.setForeground(new java.awt.Color(0, 0, 0));
        Kelurahan2.setHighlighter(null);
        Kelurahan2.setName("Kelurahan2"); // NOI18N
        Kelurahan2.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                Kelurahan2KeyPressed(evt);
            }
        });
        panelBiasa3.add(Kelurahan2);
        Kelurahan2.setBounds(105, 70, 350, 23);

        btnKel.setForeground(new java.awt.Color(0, 0, 0));
        btnKel.setIcon(new javax.swing.ImageIcon(getClass().getResource("/picture/190.png"))); // NOI18N
        btnKel.setMnemonic('1');
        btnKel.setToolTipText("ALt+1");
        btnKel.setName("btnKel"); // NOI18N
        btnKel.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnKelActionPerformed(evt);
            }
        });
        panelBiasa3.add(btnKel);
        btnKel.setBounds(460, 70, 28, 23);

        Kecamatan2.setForeground(new java.awt.Color(0, 0, 0));
        Kecamatan2.setHighlighter(null);
        Kecamatan2.setName("Kecamatan2"); // NOI18N
        Kecamatan2.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                Kecamatan2KeyPressed(evt);
            }
        });
        panelBiasa3.add(Kecamatan2);
        Kecamatan2.setBounds(105, 40, 350, 23);

        btnKec.setForeground(new java.awt.Color(0, 0, 0));
        btnKec.setIcon(new javax.swing.ImageIcon(getClass().getResource("/picture/190.png"))); // NOI18N
        btnKec.setMnemonic('1');
        btnKec.setToolTipText("ALt+1");
        btnKec.setName("btnKec"); // NOI18N
        btnKec.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnKecActionPerformed(evt);
            }
        });
        panelBiasa3.add(btnKec);
        btnKec.setBounds(460, 40, 28, 23);

        Kabupaten2.setForeground(new java.awt.Color(0, 0, 0));
        Kabupaten2.setHighlighter(null);
        Kabupaten2.setName("Kabupaten2"); // NOI18N
        Kabupaten2.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                Kabupaten2KeyPressed(evt);
            }
        });
        panelBiasa3.add(Kabupaten2);
        Kabupaten2.setBounds(105, 10, 350, 23);

        btnKab.setForeground(new java.awt.Color(0, 0, 0));
        btnKab.setIcon(new javax.swing.ImageIcon(getClass().getResource("/picture/190.png"))); // NOI18N
        btnKab.setMnemonic('1');
        btnKab.setToolTipText("ALt+1");
        btnKab.setName("btnKab"); // NOI18N
        btnKab.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnKabActionPerformed(evt);
            }
        });
        panelBiasa3.add(btnKab);
        btnKab.setBounds(460, 10, 28, 23);

        jLabel34.setForeground(new java.awt.Color(0, 0, 0));
        jLabel34.setText("Kelurahan :");
        jLabel34.setName("jLabel34"); // NOI18N
        panelBiasa3.add(jLabel34);
        jLabel34.setBounds(0, 70, 100, 23);

        jLabel35.setForeground(new java.awt.Color(0, 0, 0));
        jLabel35.setText("Kabupaten :");
        jLabel35.setName("jLabel35"); // NOI18N
        panelBiasa3.add(jLabel35);
        jLabel35.setBounds(0, 10, 100, 23);

        jLabel36.setForeground(new java.awt.Color(0, 0, 0));
        jLabel36.setText("Kecamatan :");
        jLabel36.setName("jLabel36"); // NOI18N
        panelBiasa3.add(jLabel36);
        jLabel36.setBounds(0, 40, 100, 23);

        BtnPrint4.setForeground(new java.awt.Color(0, 0, 0));
        BtnPrint4.setIcon(new javax.swing.ImageIcon(getClass().getResource("/picture/b_print.png"))); // NOI18N
        BtnPrint4.setMnemonic('T');
        BtnPrint4.setText("Cetak");
        BtnPrint4.setToolTipText("Alt+T");
        BtnPrint4.setName("BtnPrint4"); // NOI18N
        BtnPrint4.setPreferredSize(new java.awt.Dimension(100, 30));
        BtnPrint4.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                BtnPrint4ActionPerformed(evt);
            }
        });
        BtnPrint4.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                BtnPrint4KeyPressed(evt);
            }
        });
        panelBiasa3.add(BtnPrint4);
        BtnPrint4.setBounds(10, 110, 100, 30);

        internalFrame4.add(panelBiasa3, java.awt.BorderLayout.CENTER);

        DlgDemografi.getContentPane().add(internalFrame4, java.awt.BorderLayout.CENTER);

        DlgSakit2.setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);
        DlgSakit2.setName("DlgSakit2"); // NOI18N
        DlgSakit2.setUndecorated(true);
        DlgSakit2.setResizable(false);

        internalFrame5.setBorder(javax.swing.BorderFactory.createTitledBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 153, 255), 3), "::[ Cetak Surat Keterangan Rawat Inap ]::"));
        internalFrame5.setName("internalFrame5"); // NOI18N
        internalFrame5.setLayout(new java.awt.BorderLayout(1, 1));

        panelBiasa4.setName("panelBiasa4"); // NOI18N
        panelBiasa4.setLayout(null);

        jLabel37.setForeground(new java.awt.Color(0, 0, 0));
        jLabel37.setText("Nomor Surat Keterangan :");
        jLabel37.setName("jLabel37"); // NOI18N
        panelBiasa4.add(jLabel37);
        jLabel37.setBounds(7, 10, 140, 23);

        BtnPrint5.setForeground(new java.awt.Color(0, 0, 0));
        BtnPrint5.setIcon(new javax.swing.ImageIcon(getClass().getResource("/picture/b_print.png"))); // NOI18N
        BtnPrint5.setMnemonic('T');
        BtnPrint5.setText("Cetak");
        BtnPrint5.setToolTipText("Alt+T");
        BtnPrint5.setName("BtnPrint5"); // NOI18N
        BtnPrint5.setPreferredSize(new java.awt.Dimension(100, 30));
        BtnPrint5.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                BtnPrint5ActionPerformed(evt);
            }
        });
        panelBiasa4.add(BtnPrint5);
        BtnPrint5.setBounds(10, 80, 100, 30);

        BtnKeluar4.setForeground(new java.awt.Color(0, 0, 0));
        BtnKeluar4.setIcon(new javax.swing.ImageIcon(getClass().getResource("/picture/exit.png"))); // NOI18N
        BtnKeluar4.setMnemonic('K');
        BtnKeluar4.setText("Keluar");
        BtnKeluar4.setToolTipText("Alt+K");
        BtnKeluar4.setName("BtnKeluar4"); // NOI18N
        BtnKeluar4.setPreferredSize(new java.awt.Dimension(100, 30));
        BtnKeluar4.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                BtnKeluar4ActionPerformed(evt);
            }
        });
        panelBiasa4.add(BtnKeluar4);
        BtnKeluar4.setBounds(430, 80, 100, 30);

        NomorSurat.setForeground(new java.awt.Color(0, 0, 0));
        NomorSurat.setHighlighter(null);
        NomorSurat.setName("NomorSurat"); // NOI18N
        panelBiasa4.add(NomorSurat);
        NomorSurat.setBounds(150, 10, 370, 23);

        BtnSeek5.setForeground(new java.awt.Color(0, 0, 0));
        BtnSeek5.setIcon(new javax.swing.ImageIcon(getClass().getResource("/picture/190.png"))); // NOI18N
        BtnSeek5.setMnemonic('6');
        BtnSeek5.setToolTipText("ALt+6");
        BtnSeek5.setName("BtnSeek5"); // NOI18N
        BtnSeek5.setPreferredSize(new java.awt.Dimension(28, 23));
        BtnSeek5.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                BtnSeek5ActionPerformed(evt);
            }
        });
        panelBiasa4.add(BtnSeek5);
        BtnSeek5.setBounds(492, 40, 28, 23);

        CrDokter3.setEditable(false);
        CrDokter3.setForeground(new java.awt.Color(0, 0, 0));
        CrDokter3.setName("CrDokter3"); // NOI18N
        CrDokter3.setPreferredSize(new java.awt.Dimension(300, 23));
        panelBiasa4.add(CrDokter3);
        CrDokter3.setBounds(150, 40, 340, 23);

        jLabel24.setForeground(new java.awt.Color(0, 0, 0));
        jLabel24.setText("Dokter Png. Jawab :");
        jLabel24.setName("jLabel24"); // NOI18N
        jLabel24.setPreferredSize(new java.awt.Dimension(60, 23));
        panelBiasa4.add(jLabel24);
        jLabel24.setBounds(7, 40, 140, 23);

        internalFrame5.add(panelBiasa4, java.awt.BorderLayout.CENTER);

        DlgSakit2.getContentPane().add(internalFrame5, java.awt.BorderLayout.CENTER);

        DlgMati.setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);
        DlgMati.setName("DlgMati"); // NOI18N
        DlgMati.setUndecorated(true);
        DlgMati.setResizable(false);

        internalFrame7.setBorder(javax.swing.BorderFactory.createTitledBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 153, 255), 3), "::[ Ganti Data Pasien Meninggal ]::", javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Tahoma", 1, 12), new java.awt.Color(0, 0, 0))); // NOI18N
        internalFrame7.setName("internalFrame7"); // NOI18N
        internalFrame7.setWarnaBawah(new java.awt.Color(245, 250, 240));
        internalFrame7.setLayout(null);

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
        internalFrame7.add(BtnCloseIn2);
        BtnCloseIn2.setBounds(410, 80, 80, 30);

        ket.setForeground(new java.awt.Color(0, 0, 0));
        ket.setHighlighter(null);
        ket.setName("ket"); // NOI18N
        ket.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                ketKeyPressed(evt);
            }
        });
        internalFrame7.add(ket);
        ket.setBounds(135, 20, 500, 23);

        jLabel41.setForeground(new java.awt.Color(0, 0, 0));
        jLabel41.setText("Keterangan Meninggal :");
        jLabel41.setName("jLabel41"); // NOI18N
        internalFrame7.add(jLabel41);
        jLabel41.setBounds(10, 20, 120, 23);

        TglMati.setEditable(false);
        TglMati.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "12-08-2021" }));
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
        internalFrame7.add(TglMati);
        TglMati.setBounds(135, 50, 100, 23);

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
        internalFrame7.add(BtnGantiMati);
        BtnGantiMati.setBounds(220, 80, 80, 30);

        jLabel11.setForeground(new java.awt.Color(0, 0, 0));
        jLabel11.setText("Jam :");
        jLabel11.setName("jLabel11"); // NOI18N
        internalFrame7.add(jLabel11);
        jLabel11.setBounds(240, 50, 35, 23);

        cmbJam.setForeground(new java.awt.Color(0, 0, 0));
        cmbJam.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "00", "01", "02", "03", "04", "05", "06", "07", "08", "09", "10", "11", "12", "13", "14", "15", "16", "17", "18", "19", "20", "21", "22", "23" }));
        cmbJam.setName("cmbJam"); // NOI18N
        cmbJam.setOpaque(false);
        cmbJam.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                cmbJamKeyPressed(evt);
            }
        });
        internalFrame7.add(cmbJam);
        cmbJam.setBounds(280, 50, 45, 23);

        cmbMnt.setForeground(new java.awt.Color(0, 0, 0));
        cmbMnt.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "00", "01", "02", "03", "04", "05", "06", "07", "08", "09", "10", "11", "12", "13", "14", "15", "16", "17", "18", "19", "20", "21", "22", "23", "24", "25", "26", "27", "28", "29", "30", "31", "32", "33", "34", "35", "36", "37", "38", "39", "40", "41", "42", "43", "44", "45", "46", "47", "48", "49", "50", "51", "52", "53", "54", "55", "56", "57", "58", "59" }));
        cmbMnt.setName("cmbMnt"); // NOI18N
        cmbMnt.setOpaque(false);
        cmbMnt.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                cmbMntKeyPressed(evt);
            }
        });
        internalFrame7.add(cmbMnt);
        cmbMnt.setBounds(330, 50, 45, 23);

        cmbDtk.setForeground(new java.awt.Color(0, 0, 0));
        cmbDtk.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "00", "01", "02", "03", "04", "05", "06", "07", "08", "09", "10", "11", "12", "13", "14", "15", "16", "17", "18", "19", "20", "21", "22", "23", "24", "25", "26", "27", "28", "29", "30", "31", "32", "33", "34", "35", "36", "37", "38", "39", "40", "41", "42", "43", "44", "45", "46", "47", "48", "49", "50", "51", "52", "53", "54", "55", "56", "57", "58", "59" }));
        cmbDtk.setName("cmbDtk"); // NOI18N
        cmbDtk.setOpaque(false);
        cmbDtk.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                cmbDtkKeyPressed(evt);
            }
        });
        internalFrame7.add(cmbDtk);
        cmbDtk.setBounds(380, 50, 45, 23);

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
        internalFrame7.add(BtnHapusMati);
        BtnHapusMati.setBounds(130, 80, 80, 30);

        jam.setEditable(false);
        jam.setForeground(new java.awt.Color(0, 0, 0));
        jam.setDisabledTextColor(new java.awt.Color(0, 0, 0));
        jam.setHighlighter(null);
        jam.setName("jam"); // NOI18N
        jam.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                jamKeyPressed(evt);
            }
        });
        internalFrame7.add(jam);
        jam.setBounds(430, 50, 98, 23);

        tgl.setEditable(false);
        tgl.setForeground(new java.awt.Color(0, 0, 0));
        tgl.setDisabledTextColor(new java.awt.Color(0, 0, 0));
        tgl.setHighlighter(null);
        tgl.setName("tgl"); // NOI18N
        tgl.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                tglKeyPressed(evt);
            }
        });
        internalFrame7.add(tgl);
        tgl.setBounds(530, 50, 105, 23);

        BtnCetakMati.setForeground(new java.awt.Color(0, 0, 0));
        BtnCetakMati.setIcon(new javax.swing.ImageIcon(getClass().getResource("/picture/b_print.png"))); // NOI18N
        BtnCetakMati.setMnemonic('C');
        BtnCetakMati.setText("Cetak");
        BtnCetakMati.setToolTipText("Alt+C");
        BtnCetakMati.setName("BtnCetakMati"); // NOI18N
        BtnCetakMati.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                BtnCetakMatiActionPerformed(evt);
            }
        });
        BtnCetakMati.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                BtnCetakMatiKeyPressed(evt);
            }
        });
        internalFrame7.add(BtnCetakMati);
        BtnCetakMati.setBounds(310, 80, 80, 30);

        cekTglMati.setForeground(new java.awt.Color(0, 0, 0));
        cekTglMati.setText("Tgl. Meninggal :");
        cekTglMati.setToolTipText("");
        cekTglMati.setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);
        cekTglMati.setName("cekTglMati"); // NOI18N
        cekTglMati.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                cekTglMatiActionPerformed(evt);
            }
        });
        internalFrame7.add(cekTglMati);
        cekTglMati.setBounds(10, 50, 120, 23);

        DlgMati.getContentPane().add(internalFrame7, java.awt.BorderLayout.CENTER);

        DlgJamkesda.setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);
        DlgJamkesda.setName("DlgJamkesda"); // NOI18N
        DlgJamkesda.setUndecorated(true);
        DlgJamkesda.setResizable(false);

        internalFrame6.setBorder(javax.swing.BorderFactory.createTitledBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 153, 255), 3), "::[ Bridging JAMKESDA Rawat Jalan ]::", javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Tahoma", 1, 12), new java.awt.Color(0, 0, 0))); // NOI18N
        internalFrame6.setName("internalFrame6"); // NOI18N
        internalFrame6.setWarnaBawah(new java.awt.Color(245, 250, 240));
        internalFrame6.setLayout(null);

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
        internalFrame6.add(BtnCloseIn1);
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
        internalFrame6.add(BtnSimpan1);
        BtnSimpan1.setBounds(10, 80, 100, 30);

        jLabel25.setForeground(new java.awt.Color(0, 0, 0));
        jLabel25.setText("Tgl. Surat Ketrgn. :");
        jLabel25.setName("jLabel25"); // NOI18N
        internalFrame6.add(jLabel25);
        jLabel25.setBounds(0, 50, 110, 23);

        noSrt.setForeground(new java.awt.Color(0, 0, 0));
        noSrt.setHighlighter(null);
        noSrt.setName("noSrt"); // NOI18N
        noSrt.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                noSrtKeyPressed(evt);
            }
        });
        internalFrame6.add(noSrt);
        noSrt.setBounds(120, 20, 350, 23);

        jLabel28.setForeground(new java.awt.Color(0, 0, 0));
        jLabel28.setText("No. Surat Ketrgn. :");
        jLabel28.setName("jLabel28"); // NOI18N
        internalFrame6.add(jLabel28);
        jLabel28.setBounds(0, 20, 110, 23);

        TglSurat.setEditable(false);
        TglSurat.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "12-08-2021" }));
        TglSurat.setDisplayFormat("dd-MM-yyyy");
        TglSurat.setName("TglSurat"); // NOI18N
        TglSurat.setOpaque(false);
        TglSurat.addItemListener(new java.awt.event.ItemListener() {
            public void itemStateChanged(java.awt.event.ItemEvent evt) {
                TglSuratItemStateChanged(evt);
            }
        });
        TglSurat.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                TglSuratKeyPressed(evt);
            }
        });
        internalFrame6.add(TglSurat);
        TglSurat.setBounds(120, 50, 100, 23);

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
        internalFrame6.add(BtnGantijkd);
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
        internalFrame6.add(BtnCtkJkd);
        BtnCtkJkd.setBounds(230, 80, 100, 30);

        DlgJamkesda.getContentPane().add(internalFrame6, java.awt.BorderLayout.CENTER);

        DlgJampersal.setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);
        DlgJampersal.setName("DlgJampersal"); // NOI18N
        DlgJampersal.setUndecorated(true);
        DlgJampersal.setResizable(false);

        internalFrame8.setBorder(javax.swing.BorderFactory.createTitledBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 153, 255), 3), "::[ Bridging JAMPERSAL Rawat Jalan ]::", javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Tahoma", 1, 12))); // NOI18N
        internalFrame8.setName("internalFrame8"); // NOI18N
        internalFrame8.setWarnaBawah(new java.awt.Color(245, 250, 240));
        internalFrame8.setLayout(null);

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
        internalFrame8.add(BtnCloseIn3);
        BtnCloseIn3.setBounds(340, 80, 100, 30);

        BtnSimpan2.setForeground(new java.awt.Color(0, 0, 0));
        BtnSimpan2.setIcon(new javax.swing.ImageIcon(getClass().getResource("/picture/save-16x16.png"))); // NOI18N
        BtnSimpan2.setMnemonic('S');
        BtnSimpan2.setText("Simpan");
        BtnSimpan2.setToolTipText("Alt+S");
        BtnSimpan2.setName("BtnSimpan2"); // NOI18N
        BtnSimpan2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                BtnSimpan2ActionPerformed(evt);
            }
        });
        BtnSimpan2.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                BtnSimpan2KeyPressed(evt);
            }
        });
        internalFrame8.add(BtnSimpan2);
        BtnSimpan2.setBounds(10, 80, 100, 30);

        jLabel44.setForeground(new java.awt.Color(0, 0, 0));
        jLabel44.setText("Tgl. Surat Ketrgn. :");
        jLabel44.setName("jLabel44"); // NOI18N
        internalFrame8.add(jLabel44);
        jLabel44.setBounds(0, 50, 110, 23);

        noSrt1.setForeground(new java.awt.Color(0, 0, 0));
        noSrt1.setHighlighter(null);
        noSrt1.setName("noSrt1"); // NOI18N
        noSrt1.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                noSrt1KeyPressed(evt);
            }
        });
        internalFrame8.add(noSrt1);
        noSrt1.setBounds(120, 20, 350, 23);

        jLabel45.setForeground(new java.awt.Color(0, 0, 0));
        jLabel45.setText("No. Surat Ketrgn. :");
        jLabel45.setName("jLabel45"); // NOI18N
        internalFrame8.add(jLabel45);
        jLabel45.setBounds(0, 20, 110, 23);

        TglSurat1.setEditable(false);
        TglSurat1.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "12-08-2021" }));
        TglSurat1.setDisplayFormat("dd-MM-yyyy");
        TglSurat1.setName("TglSurat1"); // NOI18N
        TglSurat1.setOpaque(false);
        TglSurat1.addItemListener(new java.awt.event.ItemListener() {
            public void itemStateChanged(java.awt.event.ItemEvent evt) {
                TglSurat1ItemStateChanged(evt);
            }
        });
        TglSurat1.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                TglSurat1KeyPressed(evt);
            }
        });
        internalFrame8.add(TglSurat1);
        TglSurat1.setBounds(120, 50, 100, 23);

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
        internalFrame8.add(BtnGantijmp);
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
        internalFrame8.add(BtnCtkJmp);
        BtnCtkJmp.setBounds(230, 80, 100, 30);

        DlgJampersal.getContentPane().add(internalFrame8, java.awt.BorderLayout.CENTER);

        DlgAPS.setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);
        DlgAPS.setName("DlgAPS"); // NOI18N
        DlgAPS.setUndecorated(true);
        DlgAPS.setResizable(false);

        internalFrame16.setBorder(javax.swing.BorderFactory.createTitledBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 153, 255), 3), "::[ Pernyataan Alasan Status Pulang APS ]::", javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Tahoma", 1, 12), new java.awt.Color(0, 0, 0))); // NOI18N
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
        BtnCloseIn11.setBounds(360, 190, 80, 30);

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
        jLabel98.setBounds(0, 157, 81, 23);

        TCari1.setForeground(new java.awt.Color(0, 0, 0));
        TCari1.setName("TCari1"); // NOI18N
        TCari1.setPreferredSize(new java.awt.Dimension(300, 23));
        TCari1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                TCari1ActionPerformed(evt);
            }
        });
        TCari1.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                TCari1KeyPressed(evt);
            }
        });
        internalFrame16.add(TCari1);
        TCari1.setBounds(85, 157, 220, 23);

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
        BtnCari5.setBounds(310, 157, 130, 23);

        DlgAPS.getContentPane().add(internalFrame16, java.awt.BorderLayout.CENTER);

        DlgSuratTindakanDokter.setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);
        DlgSuratTindakanDokter.setName("DlgSuratTindakanDokter"); // NOI18N
        DlgSuratTindakanDokter.setUndecorated(true);
        DlgSuratTindakanDokter.setResizable(false);

        internalFrame9.setBorder(javax.swing.BorderFactory.createTitledBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 153, 255), 3), "::[ Surat Tindakan Kedokteran ]::", javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Tahoma", 1, 12), new java.awt.Color(0, 0, 0))); // NOI18N
        internalFrame9.setName("internalFrame9"); // NOI18N
        internalFrame9.setWarnaBawah(new java.awt.Color(245, 250, 240));
        internalFrame9.setLayout(null);

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
        BtnCloseIn4.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                BtnCloseIn4KeyPressed(evt);
            }
        });
        internalFrame9.add(BtnCloseIn4);
        BtnCloseIn4.setBounds(440, 318, 80, 30);

        nmPJ.setForeground(new java.awt.Color(0, 0, 0));
        nmPJ.setHighlighter(null);
        nmPJ.setName("nmPJ"); // NOI18N
        nmPJ.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                nmPJKeyPressed(evt);
            }
        });
        internalFrame9.add(nmPJ);
        nmPJ.setBounds(155, 20, 480, 23);

        jLabel46.setForeground(new java.awt.Color(0, 0, 0));
        jLabel46.setText("Nama Penanggung Jwb. :");
        jLabel46.setName("jLabel46"); // NOI18N
        internalFrame9.add(jLabel46);
        jLabel46.setBounds(0, 20, 150, 23);

        TglSuratTindakan.setEditable(false);
        TglSuratTindakan.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "12-08-2021" }));
        TglSuratTindakan.setDisplayFormat("dd-MM-yyyy");
        TglSuratTindakan.setName("TglSuratTindakan"); // NOI18N
        TglSuratTindakan.setOpaque(false);
        TglSuratTindakan.addItemListener(new java.awt.event.ItemListener() {
            public void itemStateChanged(java.awt.event.ItemEvent evt) {
                TglSuratTindakanItemStateChanged(evt);
            }
        });
        TglSuratTindakan.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                TglSuratTindakanKeyPressed(evt);
            }
        });
        internalFrame9.add(TglSuratTindakan);
        TglSuratTindakan.setBounds(155, 275, 100, 23);

        BtnGantiSurat.setForeground(new java.awt.Color(0, 0, 0));
        BtnGantiSurat.setIcon(new javax.swing.ImageIcon(getClass().getResource("/picture/inventaris.png"))); // NOI18N
        BtnGantiSurat.setMnemonic('G');
        BtnGantiSurat.setText("Ganti");
        BtnGantiSurat.setToolTipText("Alt+G");
        BtnGantiSurat.setName("BtnGantiSurat"); // NOI18N
        BtnGantiSurat.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                BtnGantiSuratActionPerformed(evt);
            }
        });
        BtnGantiSurat.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                BtnGantiSuratKeyPressed(evt);
            }
        });
        internalFrame9.add(BtnGantiSurat);
        BtnGantiSurat.setBounds(250, 318, 80, 30);

        jLabel12.setForeground(new java.awt.Color(0, 0, 0));
        jLabel12.setText("Jam :");
        jLabel12.setName("jLabel12"); // NOI18N
        internalFrame9.add(jLabel12);
        jLabel12.setBounds(261, 276, 30, 23);

        CmbJam1.setForeground(new java.awt.Color(0, 0, 0));
        CmbJam1.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "00", "01", "02", "03", "04", "05", "06", "07", "08", "09", "10", "11", "12", "13", "14", "15", "16", "17", "18", "19", "20", "21", "22", "23" }));
        CmbJam1.setName("CmbJam1"); // NOI18N
        CmbJam1.setOpaque(false);
        CmbJam1.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                CmbJam1KeyPressed(evt);
            }
        });
        internalFrame9.add(CmbJam1);
        CmbJam1.setBounds(295, 276, 45, 23);

        CmbMenit1.setForeground(new java.awt.Color(0, 0, 0));
        CmbMenit1.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "00", "01", "02", "03", "04", "05", "06", "07", "08", "09", "10", "11", "12", "13", "14", "15", "16", "17", "18", "19", "20", "21", "22", "23", "24", "25", "26", "27", "28", "29", "30", "31", "32", "33", "34", "35", "36", "37", "38", "39", "40", "41", "42", "43", "44", "45", "46", "47", "48", "49", "50", "51", "52", "53", "54", "55", "56", "57", "58", "59" }));
        CmbMenit1.setName("CmbMenit1"); // NOI18N
        CmbMenit1.setOpaque(false);
        CmbMenit1.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                CmbMenit1KeyPressed(evt);
            }
        });
        internalFrame9.add(CmbMenit1);
        CmbMenit1.setBounds(345, 276, 45, 23);

        CmbDetik1.setForeground(new java.awt.Color(0, 0, 0));
        CmbDetik1.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "00", "01", "02", "03", "04", "05", "06", "07", "08", "09", "10", "11", "12", "13", "14", "15", "16", "17", "18", "19", "20", "21", "22", "23", "24", "25", "26", "27", "28", "29", "30", "31", "32", "33", "34", "35", "36", "37", "38", "39", "40", "41", "42", "43", "44", "45", "46", "47", "48", "49", "50", "51", "52", "53", "54", "55", "56", "57", "58", "59" }));
        CmbDetik1.setName("CmbDetik1"); // NOI18N
        CmbDetik1.setOpaque(false);
        CmbDetik1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                CmbDetik1ActionPerformed(evt);
            }
        });
        CmbDetik1.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                CmbDetik1KeyPressed(evt);
            }
        });
        internalFrame9.add(CmbDetik1);
        CmbDetik1.setBounds(395, 276, 45, 23);

        BtnHapusSurat.setForeground(new java.awt.Color(0, 0, 0));
        BtnHapusSurat.setIcon(new javax.swing.ImageIcon(getClass().getResource("/picture/stop_f2.png"))); // NOI18N
        BtnHapusSurat.setMnemonic('H');
        BtnHapusSurat.setText("Hapus");
        BtnHapusSurat.setToolTipText("Alt+H");
        BtnHapusSurat.setName("BtnHapusSurat"); // NOI18N
        BtnHapusSurat.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                BtnHapusSuratActionPerformed(evt);
            }
        });
        BtnHapusSurat.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                BtnHapusSuratKeyPressed(evt);
            }
        });
        internalFrame9.add(BtnHapusSurat);
        BtnHapusSurat.setBounds(160, 318, 80, 30);

        BtnCetakSurat.setForeground(new java.awt.Color(0, 0, 0));
        BtnCetakSurat.setIcon(new javax.swing.ImageIcon(getClass().getResource("/picture/b_print.png"))); // NOI18N
        BtnCetakSurat.setMnemonic('C');
        BtnCetakSurat.setText("Cetak");
        BtnCetakSurat.setToolTipText("Alt+C");
        BtnCetakSurat.setName("BtnCetakSurat"); // NOI18N
        BtnCetakSurat.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                BtnCetakSuratActionPerformed(evt);
            }
        });
        BtnCetakSurat.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                BtnCetakSuratKeyPressed(evt);
            }
        });
        internalFrame9.add(BtnCetakSurat);
        BtnCetakSurat.setBounds(340, 318, 80, 30);

        jLabel47.setForeground(new java.awt.Color(0, 0, 0));
        jLabel47.setText("Umur :");
        jLabel47.setName("jLabel47"); // NOI18N
        internalFrame9.add(jLabel47);
        jLabel47.setBounds(0, 48, 150, 23);

        umurPJ.setForeground(new java.awt.Color(0, 0, 0));
        umurPJ.setHighlighter(null);
        umurPJ.setName("umurPJ"); // NOI18N
        umurPJ.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                umurPJKeyPressed(evt);
            }
        });
        internalFrame9.add(umurPJ);
        umurPJ.setBounds(155, 48, 50, 23);

        jLabel48.setForeground(new java.awt.Color(0, 0, 0));
        jLabel48.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        jLabel48.setText("Tahun");
        jLabel48.setName("jLabel48"); // NOI18N
        internalFrame9.add(jLabel48);
        jLabel48.setBounds(208, 48, 40, 23);

        jLabel49.setForeground(new java.awt.Color(0, 0, 0));
        jLabel49.setText("Jenis Kelamin :");
        jLabel49.setName("jLabel49"); // NOI18N
        internalFrame9.add(jLabel49);
        jLabel49.setBounds(260, 48, 80, 23);

        jkPJ.setForeground(new java.awt.Color(0, 0, 0));
        jkPJ.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "Laki-laki", "Perempuan" }));
        jkPJ.setName("jkPJ"); // NOI18N
        jkPJ.setPreferredSize(new java.awt.Dimension(55, 28));
        jkPJ.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                jkPJKeyPressed(evt);
            }
        });
        internalFrame9.add(jkPJ);
        jkPJ.setBounds(345, 48, 100, 23);

        jLabel50.setForeground(new java.awt.Color(0, 0, 0));
        jLabel50.setText("Alamat :");
        jLabel50.setName("jLabel50"); // NOI18N
        internalFrame9.add(jLabel50);
        jLabel50.setBounds(0, 75, 150, 23);

        alamatPJ.setForeground(new java.awt.Color(0, 0, 0));
        alamatPJ.setHighlighter(null);
        alamatPJ.setName("alamatPJ"); // NOI18N
        alamatPJ.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                alamatPJKeyPressed(evt);
            }
        });
        internalFrame9.add(alamatPJ);
        alamatPJ.setBounds(155, 75, 480, 23);

        nmkel.setEditable(false);
        nmkel.setForeground(new java.awt.Color(0, 0, 0));
        nmkel.setHighlighter(null);
        nmkel.setName("nmkel"); // NOI18N
        nmkel.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                nmkelKeyPressed(evt);
            }
        });
        internalFrame9.add(nmkel);
        nmkel.setBounds(155, 103, 340, 23);

        jLabel51.setForeground(new java.awt.Color(0, 0, 0));
        jLabel51.setText("Kelurahan :");
        jLabel51.setName("jLabel51"); // NOI18N
        internalFrame9.add(jLabel51);
        jLabel51.setBounds(0, 103, 150, 23);

        jLabel52.setForeground(new java.awt.Color(0, 0, 0));
        jLabel52.setText("Kecamatan :");
        jLabel52.setName("jLabel52"); // NOI18N
        internalFrame9.add(jLabel52);
        jLabel52.setBounds(0, 131, 150, 23);

        nmkec.setEditable(false);
        nmkec.setForeground(new java.awt.Color(0, 0, 0));
        nmkec.setHighlighter(null);
        nmkec.setName("nmkec"); // NOI18N
        nmkec.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                nmkecKeyPressed(evt);
            }
        });
        internalFrame9.add(nmkec);
        nmkec.setBounds(155, 131, 340, 23);

        nmkab.setEditable(false);
        nmkab.setForeground(new java.awt.Color(0, 0, 0));
        nmkab.setHighlighter(null);
        nmkab.setName("nmkab"); // NOI18N
        nmkab.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                nmkabKeyPressed(evt);
            }
        });
        internalFrame9.add(nmkab);
        nmkab.setBounds(155, 159, 340, 23);

        jLabel53.setForeground(new java.awt.Color(0, 0, 0));
        jLabel53.setText("Kabupaten :");
        jLabel53.setName("jLabel53"); // NOI18N
        internalFrame9.add(jLabel53);
        jLabel53.setBounds(0, 159, 150, 23);

        jLabel54.setForeground(new java.awt.Color(0, 0, 0));
        jLabel54.setText("Hubungan dg. Pasien :");
        jLabel54.setName("jLabel54"); // NOI18N
        internalFrame9.add(jLabel54);
        jLabel54.setBounds(0, 189, 150, 23);

        hubungan.setForeground(new java.awt.Color(0, 0, 0));
        hubungan.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "Saudara", "Ayah", "Ibu", "Anak", "Suami", "Istri", "Sepupu", "Pasien Sendiri", "Paman", "Bibi", "Kakek", "Nenek", "Teman", "Tetangga", "Ipar", "Besan", "Menantu", "Mertua", "Keponakan" }));
        hubungan.setName("hubungan"); // NOI18N
        hubungan.setPreferredSize(new java.awt.Dimension(55, 28));
        hubungan.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                hubunganKeyPressed(evt);
            }
        });
        internalFrame9.add(hubungan);
        hubungan.setBounds(155, 189, 110, 23);

        jLabel55.setForeground(new java.awt.Color(0, 0, 0));
        jLabel55.setText("Jenis Surat :");
        jLabel55.setName("jLabel55"); // NOI18N
        internalFrame9.add(jLabel55);
        jLabel55.setBounds(270, 189, 70, 23);

        jnsSurat.setForeground(new java.awt.Color(0, 0, 0));
        jnsSurat.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "PERSETUJUAN", "PENOLAKAN" }));
        jnsSurat.setName("jnsSurat"); // NOI18N
        jnsSurat.setPreferredSize(new java.awt.Dimension(55, 28));
        jnsSurat.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jnsSuratActionPerformed(evt);
            }
        });
        jnsSurat.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                jnsSuratKeyPressed(evt);
            }
        });
        internalFrame9.add(jnsSurat);
        jnsSurat.setBounds(345, 189, 110, 23);

        jLabel56.setForeground(new java.awt.Color(0, 0, 0));
        jLabel56.setText("Tindakan Kedokteran :");
        jLabel56.setName("jLabel56"); // NOI18N
        internalFrame9.add(jLabel56);
        jLabel56.setBounds(0, 218, 150, 23);

        jSeparator1.setName("jSeparator1"); // NOI18N
        internalFrame9.add(jSeparator1);
        jSeparator1.setBounds(0, 308, 730, 10);

        tindakanDokter.setForeground(new java.awt.Color(0, 0, 0));
        tindakanDokter.setHighlighter(null);
        tindakanDokter.setName("tindakanDokter"); // NOI18N
        tindakanDokter.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                tindakanDokterKeyPressed(evt);
            }
        });
        internalFrame9.add(tindakanDokter);
        tindakanDokter.setBounds(155, 218, 480, 23);

        jLabel57.setForeground(new java.awt.Color(0, 0, 0));
        jLabel57.setText("Alasan Penolakan :");
        jLabel57.setName("jLabel57"); // NOI18N
        internalFrame9.add(jLabel57);
        jLabel57.setBounds(0, 246, 150, 23);

        alasanTolak.setForeground(new java.awt.Color(0, 0, 0));
        alasanTolak.setHighlighter(null);
        alasanTolak.setName("alasanTolak"); // NOI18N
        alasanTolak.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                alasanTolakKeyPressed(evt);
            }
        });
        internalFrame9.add(alasanTolak);
        alasanTolak.setBounds(155, 246, 480, 23);

        jLabel58.setForeground(new java.awt.Color(0, 0, 0));
        jLabel58.setText("Tgl. Surat :");
        jLabel58.setName("jLabel58"); // NOI18N
        internalFrame9.add(jLabel58);
        jLabel58.setBounds(0, 275, 150, 23);

        BtnSimpan3.setForeground(new java.awt.Color(0, 0, 0));
        BtnSimpan3.setIcon(new javax.swing.ImageIcon(getClass().getResource("/picture/save-16x16.png"))); // NOI18N
        BtnSimpan3.setMnemonic('S');
        BtnSimpan3.setText("Simpan");
        BtnSimpan3.setToolTipText("Alt+S");
        BtnSimpan3.setName("BtnSimpan3"); // NOI18N
        BtnSimpan3.setPreferredSize(new java.awt.Dimension(100, 30));
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
        internalFrame9.add(BtnSimpan3);
        BtnSimpan3.setBounds(60, 318, 90, 30);

        BtnKelurahan.setForeground(new java.awt.Color(0, 0, 0));
        BtnKelurahan.setIcon(new javax.swing.ImageIcon(getClass().getResource("/picture/download24.png"))); // NOI18N
        BtnKelurahan.setMnemonic('2');
        BtnKelurahan.setToolTipText("ALt+2");
        BtnKelurahan.setName("BtnKelurahan"); // NOI18N
        BtnKelurahan.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                BtnKelurahanActionPerformed(evt);
            }
        });
        internalFrame9.add(BtnKelurahan);
        BtnKelurahan.setBounds(500, 103, 28, 23);

        BtnKecamatan.setForeground(new java.awt.Color(0, 0, 0));
        BtnKecamatan.setIcon(new javax.swing.ImageIcon(getClass().getResource("/picture/download24.png"))); // NOI18N
        BtnKecamatan.setMnemonic('3');
        BtnKecamatan.setToolTipText("ALt+3");
        BtnKecamatan.setName("BtnKecamatan"); // NOI18N
        BtnKecamatan.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                BtnKecamatanActionPerformed(evt);
            }
        });
        internalFrame9.add(BtnKecamatan);
        BtnKecamatan.setBounds(500, 131, 28, 23);

        BtnKabupaten.setForeground(new java.awt.Color(0, 0, 0));
        BtnKabupaten.setIcon(new javax.swing.ImageIcon(getClass().getResource("/picture/download24.png"))); // NOI18N
        BtnKabupaten.setMnemonic('4');
        BtnKabupaten.setToolTipText("ALt+4");
        BtnKabupaten.setName("BtnKabupaten"); // NOI18N
        BtnKabupaten.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                BtnKabupatenActionPerformed(evt);
            }
        });
        internalFrame9.add(BtnKabupaten);
        BtnKabupaten.setBounds(500, 159, 28, 23);

        ChkJln1.setBackground(new java.awt.Color(235, 255, 235));
        ChkJln1.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(195, 215, 195)));
        ChkJln1.setForeground(new java.awt.Color(0, 0, 0));
        ChkJln1.setSelected(true);
        ChkJln1.setBorderPainted(true);
        ChkJln1.setBorderPaintedFlat(true);
        ChkJln1.setFont(new java.awt.Font("Tahoma", 1, 11)); // NOI18N
        ChkJln1.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        ChkJln1.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        ChkJln1.setName("ChkJln1"); // NOI18N
        ChkJln1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                ChkJln1ActionPerformed(evt);
            }
        });
        internalFrame9.add(ChkJln1);
        ChkJln1.setBounds(445, 276, 23, 23);

        DlgSuratTindakanDokter.getContentPane().add(internalFrame9, java.awt.BorderLayout.CENTER);

        Kd2.setName("Kd2"); // NOI18N
        Kd2.setPreferredSize(new java.awt.Dimension(207, 23));

        NoBalasan.setHighlighter(null);
        NoBalasan.setName("NoBalasan"); // NOI18N

        KataDatang.setHighlighter(null);
        KataDatang.setName("KataDatang"); // NOI18N

        sepJkd.setName("sepJkd"); // NOI18N
        sepJkd.setPreferredSize(new java.awt.Dimension(207, 23));

        sepJmp.setName("sepJmp"); // NOI18N
        sepJmp.setPreferredSize(new java.awt.Dimension(207, 23));

        noRawatDataIGD.setName("noRawatDataIGD"); // NOI18N
        noRawatDataIGD.setPreferredSize(new java.awt.Dimension(207, 23));

        rmMati.setName("rmMati"); // NOI18N
        rmMati.setPreferredSize(new java.awt.Dimension(207, 23));

        unitIGD.setName("unitIGD"); // NOI18N
        unitIGD.setPreferredSize(new java.awt.Dimension(207, 23));

        TNoRM.setForeground(new java.awt.Color(0, 0, 0));
        TNoRM.setHighlighter(null);
        TNoRM.setName("TNoRM"); // NOI18N
        TNoRM.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                TNoRMKeyPressed(evt);
            }
        });

        kode_rujukanya.setForeground(new java.awt.Color(0, 0, 0));
        kode_rujukanya.setHighlighter(null);
        kode_rujukanya.setName("kode_rujukanya"); // NOI18N
        kode_rujukanya.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                kode_rujukanyaKeyPressed(evt);
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

        umurDaftar.setEditable(false);
        umurDaftar.setForeground(new java.awt.Color(0, 0, 0));
        umurDaftar.setHighlighter(null);
        umurDaftar.setName("umurDaftar"); // NOI18N
        umurDaftar.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                umurDaftarKeyPressed(evt);
            }
        });

        kelaminPersalinan.setEditable(false);
        kelaminPersalinan.setForeground(new java.awt.Color(0, 0, 0));
        kelaminPersalinan.setHighlighter(null);
        kelaminPersalinan.setName("kelaminPersalinan"); // NOI18N
        kelaminPersalinan.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                kelaminPersalinanKeyPressed(evt);
            }
        });

        cekDataPersalinan.setEditable(false);
        cekDataPersalinan.setForeground(new java.awt.Color(0, 0, 0));
        cekDataPersalinan.setHighlighter(null);
        cekDataPersalinan.setName("cekDataPersalinan"); // NOI18N
        cekDataPersalinan.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                cekDataPersalinanKeyPressed(evt);
            }
        });

        norwSurat.setName("norwSurat"); // NOI18N
        norwSurat.setPreferredSize(new java.awt.Dimension(207, 23));

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);
        setUndecorated(true);
        setResizable(false);
        addWindowListener(new java.awt.event.WindowAdapter() {
            public void windowOpened(java.awt.event.WindowEvent evt) {
                formWindowOpened(evt);
            }
        });

        internalFrame1.setBorder(javax.swing.BorderFactory.createTitledBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(240, 245, 235)), "::[ Registrasi IGD Hari Ini ]::", javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Tahoma", 1, 12), new java.awt.Color(0, 0, 0))); // NOI18N
        internalFrame1.setName("internalFrame1"); // NOI18N
        internalFrame1.setLayout(new java.awt.BorderLayout(1, 1));

        Scroll.setToolTipText("Klik data di table, kemudian klik kanan untuk menampilkan menu yang dibutuhkan");
        Scroll.setComponentPopupMenu(jPopupMenu1);
        Scroll.setName("Scroll"); // NOI18N
        Scroll.setOpaque(true);
        Scroll.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                ScrollKeyPressed(evt);
            }
        });

        tbregistrasiIGD.setAutoCreateRowSorter(true);
        tbregistrasiIGD.setToolTipText("Klik data di table, kemudian klik kanan untuk menampilkan menu yang dibutuhkan");
        tbregistrasiIGD.setComponentPopupMenu(jPopupMenu1);
        tbregistrasiIGD.setName("tbregistrasiIGD"); // NOI18N
        tbregistrasiIGD.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                tbregistrasiIGDMouseClicked(evt);
            }
        });
        tbregistrasiIGD.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                tbregistrasiIGDKeyPressed(evt);
            }
        });
        Scroll.setViewportView(tbregistrasiIGD);

        internalFrame1.add(Scroll, java.awt.BorderLayout.CENTER);

        jPanel2.setName("jPanel2"); // NOI18N
        jPanel2.setOpaque(false);
        jPanel2.setLayout(new java.awt.BorderLayout(1, 1));

        panelGlass6.setName("panelGlass6"); // NOI18N
        panelGlass6.setPreferredSize(new java.awt.Dimension(55, 55));
        panelGlass6.setLayout(new java.awt.FlowLayout(java.awt.FlowLayout.LEFT, 5, 9));

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
        panelGlass6.add(BtnSimpan);

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
        panelGlass6.add(BtnBatal);

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
        panelGlass6.add(BtnHapus);

        BtnEdit.setForeground(new java.awt.Color(0, 0, 0));
        BtnEdit.setIcon(new javax.swing.ImageIcon(getClass().getResource("/picture/inventaris.png"))); // NOI18N
        BtnEdit.setMnemonic('G');
        BtnEdit.setText("Ganti");
        BtnEdit.setToolTipText("Alt+G");
        BtnEdit.setName("BtnEdit"); // NOI18N
        BtnEdit.setPreferredSize(new java.awt.Dimension(100, 30));
        BtnEdit.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                BtnEditActionPerformed(evt);
            }
        });
        BtnEdit.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                BtnEditKeyPressed(evt);
            }
        });
        panelGlass6.add(BtnEdit);

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
        panelGlass6.add(BtnPrint);

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
        panelGlass6.add(BtnAll);

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

        jLabel10.setForeground(new java.awt.Color(0, 0, 0));
        jLabel10.setText("Record :");
        jLabel10.setName("jLabel10"); // NOI18N
        jLabel10.setPreferredSize(new java.awt.Dimension(70, 30));
        panelGlass6.add(jLabel10);

        LCount.setForeground(new java.awt.Color(0, 0, 0));
        LCount.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        LCount.setText("0");
        LCount.setName("LCount"); // NOI18N
        LCount.setPreferredSize(new java.awt.Dimension(72, 30));
        panelGlass6.add(LCount);

        jPanel2.add(panelGlass6, java.awt.BorderLayout.PAGE_END);

        panelGlass7.setName("panelGlass7"); // NOI18N
        panelGlass7.setPreferredSize(new java.awt.Dimension(44, 44));
        panelGlass7.setLayout(new java.awt.FlowLayout(java.awt.FlowLayout.LEFT, 5, 9));

        jLabel15.setForeground(new java.awt.Color(0, 0, 0));
        jLabel15.setText("Periode :");
        jLabel15.setName("jLabel15"); // NOI18N
        jLabel15.setPreferredSize(new java.awt.Dimension(60, 23));
        panelGlass7.add(jLabel15);

        DTPCari1.setEditable(false);
        DTPCari1.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "12-08-2021" }));
        DTPCari1.setDisplayFormat("dd-MM-yyyy");
        DTPCari1.setName("DTPCari1"); // NOI18N
        DTPCari1.setOpaque(false);
        DTPCari1.setPreferredSize(new java.awt.Dimension(90, 23));
        panelGlass7.add(DTPCari1);

        jLabel17.setForeground(new java.awt.Color(0, 0, 0));
        jLabel17.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel17.setText("s.d");
        jLabel17.setName("jLabel17"); // NOI18N
        jLabel17.setPreferredSize(new java.awt.Dimension(24, 23));
        panelGlass7.add(jLabel17);

        DTPCari2.setEditable(false);
        DTPCari2.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "12-08-2021" }));
        DTPCari2.setDisplayFormat("dd-MM-yyyy");
        DTPCari2.setName("DTPCari2"); // NOI18N
        DTPCari2.setOpaque(false);
        DTPCari2.setPreferredSize(new java.awt.Dimension(90, 23));
        panelGlass7.add(DTPCari2);

        jLabel6.setForeground(new java.awt.Color(0, 0, 0));
        jLabel6.setText("Key Word :");
        jLabel6.setName("jLabel6"); // NOI18N
        jLabel6.setPreferredSize(new java.awt.Dimension(158, 23));
        panelGlass7.add(jLabel6);

        TCari.setForeground(new java.awt.Color(0, 0, 0));
        TCari.setName("TCari"); // NOI18N
        TCari.setPreferredSize(new java.awt.Dimension(300, 23));
        TCari.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                TCariKeyPressed(evt);
            }
        });
        panelGlass7.add(TCari);

        BtnCari.setForeground(new java.awt.Color(0, 0, 0));
        BtnCari.setIcon(new javax.swing.ImageIcon(getClass().getResource("/picture/accept.png"))); // NOI18N
        BtnCari.setMnemonic('7');
        BtnCari.setText("Tampilkan Data");
        BtnCari.setToolTipText("Alt+7");
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
        panelGlass7.add(BtnCari);

        jPanel2.add(panelGlass7, java.awt.BorderLayout.CENTER);

        internalFrame1.add(jPanel2, java.awt.BorderLayout.PAGE_END);

        PanelInput.setName("PanelInput"); // NOI18N
        PanelInput.setOpaque(false);
        PanelInput.setPreferredSize(new java.awt.Dimension(490, 245));
        PanelInput.setLayout(new java.awt.BorderLayout(1, 1));

        FormInput.setName("FormInput"); // NOI18N
        FormInput.setPreferredSize(new java.awt.Dimension(490, 167));
        FormInput.setLayout(null);

        jLabel3.setForeground(new java.awt.Color(0, 0, 0));
        jLabel3.setText("No. Reg. :");
        jLabel3.setName("jLabel3"); // NOI18N
        FormInput.add(jLabel3);
        jLabel3.setBounds(0, 12, 77, 23);

        jLabel4.setForeground(new java.awt.Color(0, 0, 0));
        jLabel4.setText("No. Rawat :");
        jLabel4.setName("jLabel4"); // NOI18N
        FormInput.add(jLabel4);
        jLabel4.setBounds(0, 42, 77, 23);

        TDokter.setEditable(false);
        TDokter.setForeground(new java.awt.Color(0, 0, 0));
        TDokter.setName("TDokter"); // NOI18N
        FormInput.add(TDokter);
        TDokter.setBounds(154, 102, 210, 23);

        TNoRw.setForeground(new java.awt.Color(0, 0, 0));
        TNoRw.setHighlighter(null);
        TNoRw.setName("TNoRw"); // NOI18N
        TNoRw.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                TNoRwKeyPressed(evt);
            }
        });
        FormInput.add(TNoRw);
        TNoRw.setBounds(81, 42, 190, 23);

        jLabel8.setForeground(new java.awt.Color(0, 0, 0));
        jLabel8.setText("Tgl. Reg. :");
        jLabel8.setName("jLabel8"); // NOI18N
        FormInput.add(jLabel8);
        jLabel8.setBounds(0, 72, 77, 23);

        jLabel13.setForeground(new java.awt.Color(0, 0, 0));
        jLabel13.setText("Dr Dituju :");
        jLabel13.setName("jLabel13"); // NOI18N
        FormInput.add(jLabel13);
        jLabel13.setBounds(0, 102, 77, 23);
        jLabel13.getAccessibleContext().setAccessibleDescription("");

        jLabel20.setForeground(new java.awt.Color(0, 0, 0));
        jLabel20.setText("Png. Jawab :");
        jLabel20.setName("jLabel20"); // NOI18N
        FormInput.add(jLabel20);
        jLabel20.setBounds(396, 42, 100, 23);

        jLabel21.setForeground(new java.awt.Color(0, 0, 0));
        jLabel21.setText("Almt Png. Jwb :");
        jLabel21.setName("jLabel21"); // NOI18N
        FormInput.add(jLabel21);
        jLabel21.setBounds(396, 72, 100, 23);

        TPngJwb.setForeground(new java.awt.Color(0, 0, 0));
        TPngJwb.setHighlighter(null);
        TPngJwb.setName("TPngJwb"); // NOI18N
        TPngJwb.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                TPngJwbKeyPressed(evt);
            }
        });
        FormInput.add(TPngJwb);
        TPngJwb.setBounds(500, 42, 180, 23);

        TNoID.setForeground(new java.awt.Color(0, 0, 0));
        TNoID.setHighlighter(null);
        TNoID.setName("TNoID"); // NOI18N
        TNoID.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                TNoIDKeyPressed(evt);
            }
        });
        FormInput.add(TNoID);
        TNoID.setBounds(499, 12, 120, 23);

        TNoReg.setForeground(new java.awt.Color(0, 0, 0));
        TNoReg.setHighlighter(null);
        TNoReg.setName("TNoReg"); // NOI18N
        TNoReg.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                TNoRegKeyPressed(evt);
            }
        });
        FormInput.add(TNoReg);
        TNoReg.setBounds(81, 12, 120, 23);

        jLabel7.setForeground(new java.awt.Color(0, 0, 0));
        jLabel7.setText("No. ID Sesuai dg. :");
        jLabel7.setName("jLabel7"); // NOI18N
        FormInput.add(jLabel7);
        jLabel7.setBounds(280, 12, 100, 23);

        TAlmt.setForeground(new java.awt.Color(0, 0, 0));
        TAlmt.setHighlighter(null);
        TAlmt.setName("TAlmt"); // NOI18N
        TAlmt.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                TAlmtKeyPressed(evt);
            }
        });
        FormInput.add(TAlmt);
        TAlmt.setBounds(500, 72, 240, 23);

        BtnPasien.setForeground(new java.awt.Color(0, 0, 0));
        BtnPasien.setIcon(new javax.swing.ImageIcon(getClass().getResource("/picture/190.png"))); // NOI18N
        BtnPasien.setMnemonic('1');
        BtnPasien.setToolTipText("ALt+1");
        BtnPasien.setName("BtnPasien"); // NOI18N
        BtnPasien.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                BtnPasienActionPerformed(evt);
            }
        });
        FormInput.add(BtnPasien);
        BtnPasien.setBounds(852, 12, 28, 23);

        TPasien.setEditable(false);
        TPasien.setForeground(new java.awt.Color(0, 0, 0));
        TPasien.setHighlighter(null);
        TPasien.setName("TPasien"); // NOI18N
        FormInput.add(TPasien);
        TPasien.setBounds(623, 12, 224, 23);

        jLabel22.setForeground(new java.awt.Color(0, 0, 0));
        jLabel22.setText("Hubungan :");
        jLabel22.setName("jLabel22"); // NOI18N
        FormInput.add(jLabel22);
        jLabel22.setBounds(648, 42, 98, 23);

        THbngn.setForeground(new java.awt.Color(0, 0, 0));
        THbngn.setHighlighter(null);
        THbngn.setName("THbngn"); // NOI18N
        THbngn.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                THbngnKeyPressed(evt);
            }
        });
        FormInput.add(THbngn);
        THbngn.setBounds(750, 42, 130, 23);

        kddokter.setEditable(false);
        kddokter.setForeground(new java.awt.Color(0, 0, 0));
        kddokter.setHighlighter(null);
        kddokter.setName("kddokter"); // NOI18N
        kddokter.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                kddokterKeyPressed(evt);
            }
        });
        FormInput.add(kddokter);
        kddokter.setBounds(81, 102, 70, 23);

        BtnDokter.setForeground(new java.awt.Color(0, 0, 0));
        BtnDokter.setIcon(new javax.swing.ImageIcon(getClass().getResource("/picture/190.png"))); // NOI18N
        BtnDokter.setMnemonic('3');
        BtnDokter.setToolTipText("ALt+3");
        BtnDokter.setName("BtnDokter"); // NOI18N
        BtnDokter.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                BtnDokterActionPerformed(evt);
            }
        });
        FormInput.add(BtnDokter);
        BtnDokter.setBounds(366, 102, 28, 23);

        jLabel30.setForeground(new java.awt.Color(0, 0, 0));
        jLabel30.setText("Status :");
        jLabel30.setName("jLabel30"); // NOI18N
        FormInput.add(jLabel30);
        jLabel30.setBounds(735, 72, 50, 23);

        TStatus.setEditable(false);
        TStatus.setForeground(new java.awt.Color(0, 0, 0));
        TStatus.setHighlighter(null);
        TStatus.setName("TStatus"); // NOI18N
        FormInput.add(TStatus);
        TStatus.setBounds(790, 72, 90, 23);

        jLabel18.setForeground(new java.awt.Color(0, 0, 0));
        jLabel18.setText("Jenis Bayar :");
        jLabel18.setName("jLabel18"); // NOI18N
        FormInput.add(jLabel18);
        jLabel18.setBounds(396, 102, 100, 23);

        kdpnj.setEditable(false);
        kdpnj.setForeground(new java.awt.Color(0, 0, 0));
        kdpnj.setHighlighter(null);
        kdpnj.setName("kdpnj"); // NOI18N
        kdpnj.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                kdpnjKeyPressed(evt);
            }
        });
        FormInput.add(kdpnj);
        kdpnj.setBounds(500, 102, 70, 23);

        nmpnj.setEditable(false);
        nmpnj.setForeground(new java.awt.Color(0, 0, 0));
        nmpnj.setName("nmpnj"); // NOI18N
        FormInput.add(nmpnj);
        nmpnj.setBounds(572, 102, 279, 23);

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
        FormInput.add(btnPenjab);
        btnPenjab.setBounds(852, 102, 28, 23);

        AsalRujukan.setEditable(false);
        AsalRujukan.setForeground(new java.awt.Color(0, 0, 0));
        AsalRujukan.setName("AsalRujukan"); // NOI18N
        AsalRujukan.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                AsalRujukanKeyPressed(evt);
            }
        });
        FormInput.add(AsalRujukan);
        AsalRujukan.setBounds(500, 132, 351, 23);

        jLabel23.setForeground(new java.awt.Color(0, 0, 0));
        jLabel23.setText("Asal Rujukan :");
        jLabel23.setName("jLabel23"); // NOI18N
        FormInput.add(jLabel23);
        jLabel23.setBounds(396, 132, 100, 23);

        btnPenjab1.setForeground(new java.awt.Color(0, 0, 0));
        btnPenjab1.setIcon(new javax.swing.ImageIcon(getClass().getResource("/picture/190.png"))); // NOI18N
        btnPenjab1.setMnemonic('2');
        btnPenjab1.setToolTipText("ALt+2");
        btnPenjab1.setName("btnPenjab1"); // NOI18N
        btnPenjab1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnPenjab1ActionPerformed(evt);
            }
        });
        FormInput.add(btnPenjab1);
        btnPenjab1.setBounds(852, 132, 28, 23);

        ChkTracker.setBackground(new java.awt.Color(235, 255, 235));
        ChkTracker.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(195, 215, 195)));
        ChkTracker.setForeground(new java.awt.Color(0, 0, 0));
        ChkTracker.setBorderPainted(true);
        ChkTracker.setBorderPaintedFlat(true);
        ChkTracker.setFont(new java.awt.Font("Tahoma", 1, 11)); // NOI18N
        ChkTracker.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        ChkTracker.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        ChkTracker.setName("ChkTracker"); // NOI18N
        FormInput.add(ChkTracker);
        ChkTracker.setBounds(203, 12, 23, 23);

        jLabel26.setForeground(new java.awt.Color(0, 0, 0));
        jLabel26.setText("Tindkn. Lanjut :");
        jLabel26.setName("jLabel26"); // NOI18N
        FormInput.add(jLabel26);
        jLabel26.setBounds(890, 102, 80, 23);

        cmbTindakan.setForeground(new java.awt.Color(0, 0, 0));
        cmbTindakan.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "-", "PRAKTEK DOKTER", "RAWAT JALAN", "RAWAT INAP", "DI RUJUK", "MENINGGAL DI IGD", "D.O.A", "APS" }));
        cmbTindakan.setName("cmbTindakan"); // NOI18N
        cmbTindakan.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                cmbTindakanActionPerformed(evt);
            }
        });
        cmbTindakan.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                cmbTindakanKeyPressed(evt);
            }
        });
        FormInput.add(cmbTindakan);
        cmbTindakan.setBounds(975, 102, 125, 23);

        jLabel27.setForeground(new java.awt.Color(0, 0, 0));
        jLabel27.setText("Spesialisasi : ");
        jLabel27.setName("jLabel27"); // NOI18N
        FormInput.add(jLabel27);
        jLabel27.setBounds(900, 72, 70, 23);

        cmbTrauma.setForeground(new java.awt.Color(0, 0, 0));
        cmbTrauma.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "-", "KLL", "K. KERJA", "K. RT", "LAIN - LAIN" }));
        cmbTrauma.setName("cmbTrauma"); // NOI18N
        cmbTrauma.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                cmbTraumaKeyPressed(evt);
            }
        });
        FormInput.add(cmbTrauma);
        cmbTrauma.setBounds(975, 42, 110, 23);

        ChkDtgSendiri.setBackground(new java.awt.Color(255, 255, 250));
        ChkDtgSendiri.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(255, 255, 250)));
        ChkDtgSendiri.setForeground(new java.awt.Color(0, 0, 0));
        ChkDtgSendiri.setText("Pasien TIDAK Datang Sendiri");
        ChkDtgSendiri.setBorderPainted(true);
        ChkDtgSendiri.setBorderPaintedFlat(true);
        ChkDtgSendiri.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        ChkDtgSendiri.setHorizontalTextPosition(javax.swing.SwingConstants.RIGHT);
        ChkDtgSendiri.setName("ChkDtgSendiri"); // NOI18N
        ChkDtgSendiri.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                ChkDtgSendiriActionPerformed(evt);
            }
        });
        FormInput.add(ChkDtgSendiri);
        ChkDtgSendiri.setBounds(887, 12, 310, 23);

        jLabel38.setForeground(new java.awt.Color(0, 0, 0));
        jLabel38.setText("Trauma :");
        jLabel38.setName("jLabel38"); // NOI18N
        FormInput.add(jLabel38);
        jLabel38.setBounds(900, 42, 70, 23);

        cmbNonTrauma.setForeground(new java.awt.Color(0, 0, 0));
        cmbNonTrauma.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "-", "P. DALAM", "BEDAH", "ANAK", "PARU", "SYARAF", "JANTUNG", "THT", "MATA", "KEBIDANAN", "KULIT & KELAMIN" }));
        cmbNonTrauma.setName("cmbNonTrauma"); // NOI18N
        cmbNonTrauma.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                cmbNonTraumaKeyPressed(evt);
            }
        });
        FormInput.add(cmbNonTrauma);
        cmbNonTrauma.setBounds(975, 72, 120, 23);

        jLabel39.setForeground(new java.awt.Color(0, 0, 0));
        jLabel39.setText("Ket. Tindk Lnjt. :");
        jLabel39.setName("jLabel39"); // NOI18N
        FormInput.add(jLabel39);
        jLabel39.setBounds(890, 132, 80, 23);

        cmbKet.setForeground(new java.awt.Color(0, 0, 0));
        cmbKet.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "-", "PULANG", "DIRAWAT", "OBSERVASI" }));
        cmbKet.setName("cmbKet"); // NOI18N
        cmbKet.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                cmbKetActionPerformed(evt);
            }
        });
        cmbKet.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                cmbKetKeyPressed(evt);
            }
        });
        FormInput.add(cmbKet);
        cmbKet.setBounds(975, 132, 90, 23);

        JnsnoID.setForeground(new java.awt.Color(0, 0, 0));
        JnsnoID.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "No. Rekam Medik", "No. Kartu BPJS", "NIK KTP" }));
        JnsnoID.setName("JnsnoID"); // NOI18N
        JnsnoID.setOpaque(false);
        JnsnoID.addItemListener(new java.awt.event.ItemListener() {
            public void itemStateChanged(java.awt.event.ItemEvent evt) {
                JnsnoIDItemStateChanged(evt);
            }
        });
        JnsnoID.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                JnsnoIDMouseClicked(evt);
            }
        });
        JnsnoID.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                JnsnoIDKeyPressed(evt);
            }
        });
        FormInput.add(JnsnoID);
        JnsnoID.setBounds(385, 12, 110, 23);

        jLabel29.setForeground(new java.awt.Color(0, 0, 0));
        jLabel29.setText("Suku/Bangsa : ");
        jLabel29.setName("jLabel29"); // NOI18N
        FormInput.add(jLabel29);
        jLabel29.setBounds(396, 162, 100, 23);

        nmsuku.setEditable(false);
        nmsuku.setForeground(new java.awt.Color(0, 0, 0));
        nmsuku.setName("nmsuku"); // NOI18N
        FormInput.add(nmsuku);
        nmsuku.setBounds(500, 162, 160, 23);

        jLabel40.setForeground(new java.awt.Color(0, 0, 0));
        jLabel40.setText("Bahasa Dipakai : ");
        jLabel40.setName("jLabel40"); // NOI18N
        FormInput.add(jLabel40);
        jLabel40.setBounds(396, 190, 100, 23);

        nmbahasa.setEditable(false);
        nmbahasa.setForeground(new java.awt.Color(0, 0, 0));
        nmbahasa.setName("nmbahasa"); // NOI18N
        FormInput.add(nmbahasa);
        nmbahasa.setBounds(500, 190, 160, 23);

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
        FormInput.add(BtnSuku);
        BtnSuku.setBounds(665, 162, 28, 23);

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
        FormInput.add(BtnBahasa);
        BtnBahasa.setBounds(665, 190, 28, 23);

        jLabel42.setForeground(new java.awt.Color(0, 0, 0));
        jLabel42.setText("Alasan APS : ");
        jLabel42.setName("jLabel42"); // NOI18N
        FormInput.add(jLabel42);
        jLabel42.setBounds(700, 162, 100, 23);

        jLabel43.setForeground(new java.awt.Color(0, 0, 0));
        jLabel43.setText("Keterangan APS : ");
        jLabel43.setName("jLabel43"); // NOI18N
        FormInput.add(jLabel43);
        jLabel43.setBounds(700, 190, 100, 23);

        alasanAPS.setEditable(false);
        alasanAPS.setForeground(new java.awt.Color(0, 0, 0));
        alasanAPS.setName("alasanAPS"); // NOI18N
        FormInput.add(alasanAPS);
        alasanAPS.setBounds(803, 162, 300, 23);

        ketAPS.setForeground(new java.awt.Color(0, 0, 0));
        ketAPS.setName("ketAPS"); // NOI18N
        FormInput.add(ketAPS);
        ketAPS.setBounds(803, 190, 300, 23);

        BtnAlasanAPS.setIcon(new javax.swing.ImageIcon(getClass().getResource("/picture/190.png"))); // NOI18N
        BtnAlasanAPS.setMnemonic('1');
        BtnAlasanAPS.setToolTipText("ALt+1");
        BtnAlasanAPS.setName("BtnAlasanAPS"); // NOI18N
        BtnAlasanAPS.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                BtnAlasanAPSActionPerformed(evt);
            }
        });
        BtnAlasanAPS.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                BtnAlasanAPSKeyPressed(evt);
            }
        });
        FormInput.add(BtnAlasanAPS);
        BtnAlasanAPS.setBounds(1105, 162, 28, 23);

        tulisan_tanggal.setForeground(new java.awt.Color(0, 0, 0));
        tulisan_tanggal.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        tulisan_tanggal.setText("tulisan_tanggal");
        tulisan_tanggal.setName("tulisan_tanggal"); // NOI18N
        FormInput.add(tulisan_tanggal);
        tulisan_tanggal.setBounds(81, 72, 300, 23);

        PanelInput.add(FormInput, java.awt.BorderLayout.CENTER);

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
        PanelInput.add(ChkInput, java.awt.BorderLayout.PAGE_END);

        internalFrame1.add(PanelInput, java.awt.BorderLayout.PAGE_START);

        getContentPane().add(internalFrame1, java.awt.BorderLayout.CENTER);

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void TNoRwKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_TNoRwKeyPressed
        Valid.pindah(evt, TNoReg, BtnDokter);
}//GEN-LAST:event_TNoRwKeyPressed

    private void TPngJwbKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_TPngJwbKeyPressed
        Valid.pindah(evt, TNoID, THbngn);
}//GEN-LAST:event_TPngJwbKeyPressed

    private void TNoIDKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_TNoIDKeyPressed
        if (evt.getKeyCode() == KeyEvent.VK_PAGE_DOWN) {
            isPas();
        } else if (evt.getKeyCode() == KeyEvent.VK_UP) {
            BtnPasienActionPerformed(null);
        } else if (evt.getKeyCode() == KeyEvent.VK_DOWN) {
            if (!TPasien.getText().isEmpty()) {
                Map<String, Object> data = new HashMap<>();
                data.put("poli", "IGD");
                data.put("antrian", TNoReg.getText());
                data.put("nama", TPasien.getText());
                data.put("norm", TNoID.getText());
                data.put("bayar", nmpnj.getText());
                data.put("namars", var.getnamars());
                data.put("alamatrs", var.getalamatrs());
                data.put("kotars", var.getkabupatenrs());
                data.put("propinsirs", var.getpropinsirs());
                data.put("kontakrs", var.getkontakrs());
                data.put("emailrs", var.getemailrs());
                data.put("logo", Sequel.cariGambar("select logo from setting"));
                Valid.MyReport("rptCheckList.jrxml", "report", "::[ Check List ]::",
                        "select current_date() as sekarang", data);
            }
        } else if (evt.getKeyCode() == KeyEvent.VK_ENTER) {
            isPas();
            TPngJwb.requestFocus();
        } else if (evt.getKeyCode() == KeyEvent.VK_PAGE_UP) {
            kddokter.requestFocus();
        }
}//GEN-LAST:event_TNoIDKeyPressed

    private void TNoRegKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_TNoRegKeyPressed
        Valid.pindah(evt, TCari, TNoRw);
}//GEN-LAST:event_TNoRegKeyPressed

    private void TAlmtKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_TAlmtKeyPressed
        Valid.pindah(evt, THbngn, kdpnj);
}//GEN-LAST:event_TAlmtKeyPressed

    private void BtnPasienActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_BtnPasienActionPerformed
        var.setform("DlgIGD");
        pasien.isiKeterangan("Daftar Pasien IGD");
        pasien.emptTeks();
        pasien.isCek();
        pasien.setSize(internalFrame1.getWidth() - 40, internalFrame1.getHeight() - 40);
        pasien.setLocationRelativeTo(internalFrame1);
        pasien.setVisible(true);
}//GEN-LAST:event_BtnPasienActionPerformed

    private void THbngnKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_THbngnKeyPressed
        Valid.pindah(evt, TPngJwb, TAlmt);
}//GEN-LAST:event_THbngnKeyPressed

    private void BtnSimpanActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_BtnSimpanActionPerformed
        rmMati.setText(Sequel.cariIsi("select no_rkm_medis from pasien_mati where no_rkm_medis='" + TNoRM.getText() + "' "));

        if (TNoReg.getText().trim().equals("")) {
            Valid.textKosong(TNoReg, "No.Regristrasi");
        } else if (TNoRw.getText().trim().equals("")) {
            Valid.textKosong(TNoRw, "No.Rawat");
        } else if (TDokter.getText().trim().equals("")) {
            Valid.textKosong(kddokter, "dokter");
        } else if (TNoRM.getText().trim().equals("") || TPasien.getText().trim().equals("")) {
            Valid.textKosong(TNoRM, "pasien");
        } else if (kdpnj.getText().trim().equals("") || nmpnj.getText().trim().equals("")) {
            Valid.textKosong(kdpnj, "Jenis Bayar");
        } else if (kdsuku.getText().trim().equals("19") || kdsuku.getText().trim().equals("") || kdsuku.getText().trim().equals("-")) {
            Valid.textKosong(kdsuku, "Suku/Bangsa Pasien");
        } else if (kdbahasa.getText().trim().equals("12") || kdbahasa.getText().trim().equals("") || kdbahasa.getText().trim().equals("-")) {
            Valid.textKosong(kdbahasa, "Bahasa Pasien");
        } else if (cmbTindakan.getSelectedItem().equals("APS") && (cmbKet.getSelectedItem().equals("PULANG") && (alasanAPS.getText().equals("")))) {
            JOptionPane.showMessageDialog(null, "Jika pasiennya pulang APS dari IGD, alasan & keterangan APS harus diisi..!!");
            BtnAlasanAPS.requestFocus();
        } else if (!alasanAPS.getText().equals("") && (ketAPS.getText().trim().equals(""))) {
            JOptionPane.showMessageDialog(null, "Karena pasiennya pulang APS dari IGD, keterangan APS harus diisi..!!");
            ketAPS.requestFocus();
        } else if (Sequel.cariInteger("select count(pasien.no_rkm_medis) from pasien inner join reg_periksa inner join kamar_inap "
                + "on reg_periksa.no_rkm_medis=pasien.no_rkm_medis and reg_periksa.no_rawat=kamar_inap.no_rawat "
                + "where kamar_inap.stts_pulang='-' and pasien.no_rkm_medis=?", TNoRM.getText()) > 0) {
            JOptionPane.showMessageDialog(null, "Pasien sedang dalam masa perawatan di kamar inap..!!");
            TNoID.requestFocus();
        } else if (!rmMati.getText().equals("")) {
            jam.setText("Jam : " + Sequel.cariIsi("select jam from pasien_mati where no_rkm_medis='" + TNoRM.getText() + "' "));
            tgl.setText("Tgl. : " + Sequel.cariIsi("select DATE_FORMAT(tanggal,'%d-%m-%Y') tgl from pasien_mati where no_rkm_medis='" + TNoRM.getText() + "' "));

            JOptionPane.showMessageDialog(null, "Pasien tersebut sudah dinyatakan meninggal pada " + tgl.getText() + " " + jam.getText() + ", data tidak dapat disimpan.");
            ChkInput.setSelected(true);
            isForm();
            emptTeks();
        } else if (cekUmur > Sequel.cariInteger("select batas_maksimal from set_batas_umur") || cekUmur < Sequel.cariInteger("select batas_minimal from set_batas_umur")) {
            JOptionPane.showMessageDialog(null, "Silahkan perbaiki dulu tgl. lahir pasien ini, karena umurnya " + umur + " " + sttsumur1);
        } else {
            cekDaftar();
            if (cek > 0) {
                JOptionPane.showMessageDialog(null, "Pasien sudah didaftarkan......");
            } else {
                switch (TStatus.getText()) {
                    case "Baru":
                        biaya = Sequel.cariIsiAngka("select registrasi from poliklinik where kd_poli='IGDK'");
                        break;
                    case "Lama":
                        biaya = Sequel.cariIsiAngka("select registrasilama from poliklinik where kd_poli='IGDK'");
                        break;
                    default:
                        biaya = Sequel.cariIsiAngka("select registrasi from poliklinik where kd_poli='IGDK'");
                        break;
                }
                
                Sequel.menyimpan("poliklinik", "?,?,?,?,?,?", 6, new String[]{"IGDK", "IGD", "0", "0","0","0"});
                if (Sequel.menyimpantf2("reg_periksa", "?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?", "No.Rawat", 17,
                        new String[]{TNoReg.getText(), TNoRw.getText(), tglDaftar, Sequel.cariIsi("SELECT TIME(NOW()) jam"),
                            kddokter.getText(), TNoRM.getText(), "IGDK", TPngJwb.getText(), TAlmt.getText(), THbngn.getText(), biaya + "", "Belum",
                            TStatus.getText(), "Ralan", kdpnj.getText(), umur, sttsumur}) == true) {
                    UpdateUmur();
                    Sequel.menyimpan("data_igd", "'" + TNoRw.getText() + "','" + cmbTindakan.getSelectedItem().toString() + "','" + cmbTrauma.getSelectedItem().toString() + "','" + KataDatang.getText() + "','" + cmbNonTrauma.getSelectedItem().toString() + "','" + cmbKet.getSelectedItem().toString() + "' ", "Data IGD");
                    Sequel.mengedit("pasien", "no_rkm_medis='" + TNoRM.getText() + "'", "suku_bangsa='" + kdsuku.getText() + "', bahasa_pasien='" + kdbahasa.getText() + "' ");

                    if ((cmbTindakan.getSelectedItem().equals("MENINGGAL DI IGD")) || (cmbTindakan.getSelectedItem().equals("D.O.A"))) {
                        Sequel.menyimpan("pasien_mati", "'" + tglDaftar + "','" + Sequel.cariIsi("SELECT TIME(NOW()) jam") + "','"
                                + TNoRM.getText() + "','" + cmbTindakan.getSelectedItem() + "','Rumah Sakit','-','-','-','-','IGD','-'", "pasien");
                    
                    } else if (cmbTindakan.getSelectedItem().equals("APS")) {
                        Sequel.menyimpan("ralan_aps", "'" + TNoRw.getText() + "','" + kdAPS + "','" + ketAPS.getText() + "'");
                    }

                    if (!AsalRujukan.getText().equals("")) {
                        Sequel.menyimpan("rujuk_masuk", "'" + TNoRw.getText() + "','" + AsalRujukan.getText() + "','" + alamatperujuk + "',"
                                + "'-','0','" + AsalRujukan.getText() + "','-','-','-','" + NoBalasan.getText() + "','" + kode_rujukanya.getText() + "'", "No.Rujuk");
                    }
                    if (ChkTracker.isSelected() == true) {
                        ctk();
                    }
                    tampil();
                    emptTeks();
                } else {
                    Kd2.setText("");
                    isNumber();
                    if (Sequel.menyimpantf2("reg_periksa", "?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?", "No.Rawat", 17,
                            new String[]{TNoReg.getText(), TNoRw.getText(), tglDaftar, Sequel.cariIsi("SELECT TIME(NOW()) jam"),
                                kddokter.getText(), TNoRM.getText(), "IGDK", TPngJwb.getText(), TAlmt.getText(), THbngn.getText(), biaya + "", "Belum",
                                TStatus.getText(), "Ralan", kdpnj.getText(), umur, sttsumur}) == true) {
                        UpdateUmur();
                        Sequel.menyimpan("data_igd", "'" + TNoRw.getText() + "','" + cmbTindakan.getSelectedItem().toString() + "','" + cmbTrauma.getSelectedItem().toString() + "','" + KataDatang.getText() + "','" + cmbNonTrauma.getSelectedItem().toString() + "','" + cmbKet.getSelectedItem().toString() + "' ", "Data IGD");
                        Sequel.mengedit("pasien", "no_rkm_medis='" + TNoRM.getText() + "'", "suku_bangsa='" + kdsuku.getText() + "', bahasa_pasien='" + kdbahasa.getText() + "' ");

                        if ((cmbTindakan.getSelectedItem().equals("MENINGGAL DI IGD")) || (cmbTindakan.getSelectedItem().equals("D.O.A"))) {
                            Sequel.menyimpan("pasien_mati", "'" + tglDaftar + "','" + Sequel.cariIsi("SELECT TIME(NOW()) jam") + "','"
                                    + TNoRM.getText() + "','" + cmbTindakan.getSelectedItem() + "','Rumah Sakit','-','-','-','-','IGD','-'", "pasien");
                        
                        } else if (cmbTindakan.getSelectedItem().equals("APS")) {
                            Sequel.menyimpan("ralan_aps", "'" + TNoRw.getText() + "','" + kdAPS + "','" + ketAPS.getText() + "'");
                        }

                        if (!AsalRujukan.getText().equals("")) {
                            Sequel.menyimpan("rujuk_masuk", "'" + TNoRw.getText() + "','" + AsalRujukan.getText() + "','" + alamatperujuk + "',"
                                    + "'-','0','" + AsalRujukan.getText() + "','-','-','-','" + NoBalasan.getText() + "','" + kode_rujukanya.getText() + "'", "No.Rujuk");
                        }
                        if (ChkTracker.isSelected() == true) {
                            ctk();
                        }
                        tampil();
                        emptTeks();
                    } else {
                        Kd2.setText("");
                        isNumber();
                        if (Sequel.menyimpantf2("reg_periksa", "?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?", "No.Rawat", 17,
                                new String[]{TNoReg.getText(), TNoRw.getText(), tglDaftar, Sequel.cariIsi("SELECT TIME(NOW()) jam"),
                                    kddokter.getText(), TNoRM.getText(), "IGDK", TPngJwb.getText(), TAlmt.getText(), THbngn.getText(), biaya + "", "Belum",
                                    TStatus.getText(), "Ralan", kdpnj.getText(), umur, sttsumur}) == true) {
                            UpdateUmur();
                            Sequel.menyimpan("data_igd", "'" + TNoRw.getText() + "','" + cmbTindakan.getSelectedItem().toString() + "','" + cmbTrauma.getSelectedItem().toString() + "','" + KataDatang.getText() + "','" + cmbNonTrauma.getSelectedItem().toString() + "','" + cmbKet.getSelectedItem().toString() + "' ", "Data IGD");
                            Sequel.mengedit("pasien", "no_rkm_medis='" + TNoRM.getText() + "'", "suku_bangsa='" + kdsuku.getText() + "', bahasa_pasien='" + kdbahasa.getText() + "' ");

                            if ((cmbTindakan.getSelectedItem().equals("MENINGGAL DI IGD")) || (cmbTindakan.getSelectedItem().equals("D.O.A"))) {
                                Sequel.menyimpan("pasien_mati", "'" + tglDaftar + "','" + Sequel.cariIsi("SELECT TIME(NOW()) jam") + "','"
                                        + TNoRM.getText() + "','" + cmbTindakan.getSelectedItem() + "','Rumah Sakit','-','-','-','-','IGD','-'", "pasien");
                            
                            } else if (cmbTindakan.getSelectedItem().equals("APS")) {
                                Sequel.menyimpan("ralan_aps", "'" + TNoRw.getText() + "','" + kdAPS + "','" + ketAPS.getText() + "'");
                            }

                            if (!AsalRujukan.getText().equals("")) {
                                Sequel.menyimpan("rujuk_masuk", "'" + TNoRw.getText() + "','" + AsalRujukan.getText() + "','" + alamatperujuk + "',"
                                        + "'-','0','" + AsalRujukan.getText() + "','-','-','-','" + NoBalasan.getText() + "','" + kode_rujukanya.getText() + "'", "No.Rujuk");
                            }
                            if (ChkTracker.isSelected() == true) {
                                ctk();
                            }
                            tampil();
                            emptTeks();
                        } else {
                            Kd2.setText("");
                            isNumber();
                            if (Sequel.menyimpantf2("reg_periksa", "?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?", "No.Rawat", 17,
                                    new String[]{TNoReg.getText(), TNoRw.getText(), tglDaftar, Sequel.cariIsi("SELECT TIME(NOW()) jam"),
                                        kddokter.getText(), TNoRM.getText(), "IGDK", TPngJwb.getText(), TAlmt.getText(), THbngn.getText(), biaya + "", "Belum",
                                        TStatus.getText(), "Ralan", kdpnj.getText(), umur, sttsumur}) == true) {
                                UpdateUmur();
                                Sequel.menyimpan("data_igd", "'" + TNoRw.getText() + "','" + cmbTindakan.getSelectedItem().toString() + "','" + cmbTrauma.getSelectedItem().toString() + "','" + KataDatang.getText() + "','" + cmbNonTrauma.getSelectedItem().toString() + "','" + cmbKet.getSelectedItem().toString() + "' ", "Data IGD");
                                Sequel.mengedit("pasien", "no_rkm_medis='" + TNoRM.getText() + "'", "suku_bangsa='" + kdsuku.getText() + "', bahasa_pasien='" + kdbahasa.getText() + "' ");

                                if ((cmbTindakan.getSelectedItem().equals("MENINGGAL DI IGD")) || (cmbTindakan.getSelectedItem().equals("D.O.A"))) {
                                    Sequel.menyimpan("pasien_mati", "'" + tglDaftar + "','" + Sequel.cariIsi("SELECT TIME(NOW()) jam") + "','"
                                            + TNoRM.getText() + "','" + cmbTindakan.getSelectedItem() + "','Rumah Sakit','-','-','-','-','IGD','-'", "pasien");
                                
                                } else if (cmbTindakan.getSelectedItem().equals("APS")) {
                                    Sequel.menyimpan("ralan_aps", "'" + TNoRw.getText() + "','" + kdAPS + "','" + ketAPS.getText() + "'");
                                }

                                if (!AsalRujukan.getText().equals("")) {
                                    Sequel.menyimpan("rujuk_masuk", "'" + TNoRw.getText() + "','" + AsalRujukan.getText() + "','" + alamatperujuk + "',"
                                            + "'-','0','" + AsalRujukan.getText() + "','-','-','-','" + NoBalasan.getText() + "','" + kode_rujukanya.getText() + "'", "No.Rujuk");
                                }
                                if (ChkTracker.isSelected() == true) {
                                    ctk();
                                }
                                tampil();
                                emptTeks();
                            } else {
                                Kd2.setText("");
                                isNumber();
                                if (Sequel.menyimpantf("reg_periksa", "?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?", "No.Rawat", 17,
                                        new String[]{TNoReg.getText(), TNoRw.getText(), tglDaftar, Sequel.cariIsi("SELECT TIME(NOW()) jam"),
                                            kddokter.getText(), TNoRM.getText(), "IGDK", TPngJwb.getText(), TAlmt.getText(), THbngn.getText(), biaya + "", "Belum",
                                            TStatus.getText(), "Ralan", kdpnj.getText(), umur, sttsumur}) == true) {
                                    UpdateUmur();
                                    Sequel.menyimpan("data_igd", "'" + TNoRw.getText() + "','" + cmbTindakan.getSelectedItem().toString() + "','" + cmbTrauma.getSelectedItem().toString() + "','" + KataDatang.getText() + "','" + cmbNonTrauma.getSelectedItem().toString() + "','" + cmbKet.getSelectedItem().toString() + "' ", "Data IGD");
                                    Sequel.mengedit("pasien", "no_rkm_medis='" + TNoRM.getText() + "'", "suku_bangsa='" + kdsuku.getText() + "', bahasa_pasien='" + kdbahasa.getText() + "' ");

                                    if ((cmbTindakan.getSelectedItem().equals("MENINGGAL DI IGD")) || (cmbTindakan.getSelectedItem().equals("D.O.A"))) {
                                        Sequel.menyimpan("pasien_mati", "'" + tglDaftar + "','" + Sequel.cariIsi("SELECT TIME(NOW()) jam") + "','"
                                                + TNoRM.getText() + "','" + cmbTindakan.getSelectedItem() + "','Rumah Sakit','-','-','-','-','IGD','-'", "pasien");
                                    
                                    } else if (cmbTindakan.getSelectedItem().equals("APS")) {
                                        Sequel.menyimpan("ralan_aps", "'" + TNoRw.getText() + "','" + kdAPS + "','" + ketAPS.getText() + "'");
                                    }

                                    if (!AsalRujukan.getText().equals("")) {
                                        Sequel.menyimpan("rujuk_masuk", "'" + TNoRw.getText() + "','" + AsalRujukan.getText() + "','" + alamatperujuk + "',"
                                                + "'-','0','" + AsalRujukan.getText() + "','-','-','-','" + NoBalasan.getText() + "','" + kode_rujukanya.getText() + "'", "No.Rujuk");
                                    }
                                    if (ChkTracker.isSelected() == true) {
                                        ctk();
                                    }
                                    tampil();
                                    emptTeks();
                                } else {
                                    Kd2.setText("");
                                    TNoID.requestFocus();
                                    isNumber();
                                }
                            }
                        }
                    }
                }
            }
            Sequel.menyimpan("history_user", "Now(),'" + TNoRw.getText() + "','" + var.getkode() + "','Registrasi IGD','Simpan'");
        }
}//GEN-LAST:event_BtnSimpanActionPerformed

    private void BtnSimpanKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_BtnSimpanKeyPressed
        if (evt.getKeyCode() == KeyEvent.VK_SPACE) {
            BtnSimpanActionPerformed(null);
        } else {
            Valid.pindah(evt, kdpnj, BtnBatal);
        }
}//GEN-LAST:event_BtnSimpanKeyPressed

    private void BtnBatalActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_BtnBatalActionPerformed
        ChkInput.setSelected(true);
        isForm();
        emptTeks();
}//GEN-LAST:event_BtnBatalActionPerformed

    private void BtnBatalKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_BtnBatalKeyPressed
        if (evt.getKeyCode() == KeyEvent.VK_SPACE) {
            emptTeks();
        } else {
            Valid.pindah(evt, BtnSimpan, BtnHapus);
        }
}//GEN-LAST:event_BtnBatalKeyPressed

    private void BtnHapusActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_BtnHapusActionPerformed
        for (i = 0; i < tbregistrasiIGD.getRowCount(); i++) {
            if (tbregistrasiIGD.getValueAt(i, 0).toString().equals("true")) {
                sepJkd.setText(Sequel.cariIsi("SELECT bridging_jamkesda.no_sep FROM reg_periksa "
                        + "INNER JOIN bridging_jamkesda ON reg_periksa.no_rawat = bridging_jamkesda.no_rawat "
                        + "INNER JOIN penjab ON reg_periksa.kd_pj = penjab.kd_pj "
                        + "WHERE bridging_jamkesda.no_rawat='" + TNoRw.getText() + "' AND bridging_jamkesda.jns_rawat='Jalan IGD' AND penjab.png_jawab like '%jamkesda%'"));
                
                Sequel.meghapus("data_igd", "no_rawat", tbregistrasiIGD.getValueAt(i, 2).toString());
                Sequel.meghapus("rujuk_masuk", "no_rawat", tbregistrasiIGD.getValueAt(i, 2).toString());
                Sequel.meghapus("rujuk", "no_rawat", tbregistrasiIGD.getValueAt(i, 2).toString());
                Sequel.meghapus("pasien_mati", "no_rkm_medis", tbregistrasiIGD.getValueAt(i, 7).toString());
                Sequel.meghapus("bridging_jamkesda", "no_sep", sepJkd.getText());
                Sequel.meghapus("ralan_aps", "no_rawat", tbregistrasiIGD.getValueAt(i, 2).toString());
                Sequel.meghapus("reg_periksa", "no_rawat", tbregistrasiIGD.getValueAt(i, 2).toString());
            }
        }
        Sequel.menyimpan("history_user", "Now(),'" + TNoRw.getText() + "','" + var.getkode() + "','Registrasi IGD','Hapus'");
        tampil();
        emptTeks();
}//GEN-LAST:event_BtnHapusActionPerformed

    private void BtnHapusKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_BtnHapusKeyPressed
        if (evt.getKeyCode() == KeyEvent.VK_SPACE) {
            BtnHapusActionPerformed(null);
        } else {
            Valid.pindah(evt, BtnBatal, BtnEdit);
        }
}//GEN-LAST:event_BtnHapusKeyPressed

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
            Valid.MyReport("rptReg.jrxml", "report", "::[ Data Registrasi Periksa ]::", "select reg_periksa.no_reg,reg_periksa.no_rawat,reg_periksa.tgl_registrasi,reg_periksa.jam_reg,"
                    + "reg_periksa.kd_dokter,dokter.nm_dokter,reg_periksa.no_rkm_medis,pasien.nm_pasien,pasien.jk,concat(reg_periksa.umurdaftar,' ',reg_periksa.sttsumur)as umur,poliklinik.nm_poli,ifnull((select perujuk from rujuk_masuk where rujuk_masuk.no_rawat=reg_periksa.no_rawat),'') as perujuk,"
                    + "reg_periksa.p_jawab,reg_periksa.almt_pj,reg_periksa.hubunganpj,reg_periksa.biaya_reg,reg_periksa.stts_daftar,penjab.png_jawab "
                    + "from reg_periksa inner join dokter inner join pasien inner join poliklinik inner join penjab  "
                    + "on reg_periksa.kd_dokter=dokter.kd_dokter and reg_periksa.kd_pj=penjab.kd_pj "
                    + "and reg_periksa.no_rkm_medis=pasien.no_rkm_medis and reg_periksa.kd_poli=poliklinik.kd_poli "
                    + "where poliklinik.kd_poli='IGDK' and tgl_registrasi between '" + Valid.SetTgl(DTPCari1.getSelectedItem() + "") + "' and '" + Valid.SetTgl(DTPCari2.getSelectedItem() + "") + "' and reg_periksa.no_reg like '%" + TCari.getText().trim() + "%' or "
                    + " poliklinik.kd_poli='IGDK' and tgl_registrasi between '" + Valid.SetTgl(DTPCari1.getSelectedItem() + "") + "' and '" + Valid.SetTgl(DTPCari2.getSelectedItem() + "") + "' and reg_periksa.no_rawat like '%" + TCari.getText().trim() + "%' or "
                    + " poliklinik.kd_poli='IGDK' and tgl_registrasi between '" + Valid.SetTgl(DTPCari1.getSelectedItem() + "") + "' and '" + Valid.SetTgl(DTPCari2.getSelectedItem() + "") + "' and reg_periksa.tgl_registrasi like '%" + TCari.getText().trim() + "%' or "
                    + " poliklinik.kd_poli='IGDK' and tgl_registrasi between '" + Valid.SetTgl(DTPCari1.getSelectedItem() + "") + "' and '" + Valid.SetTgl(DTPCari2.getSelectedItem() + "") + "' and reg_periksa.kd_dokter like '%" + TCari.getText().trim() + "%' or "
                    + " poliklinik.kd_poli='IGDK' and tgl_registrasi between '" + Valid.SetTgl(DTPCari1.getSelectedItem() + "") + "' and '" + Valid.SetTgl(DTPCari2.getSelectedItem() + "") + "' and dokter.nm_dokter like '%" + TCari.getText().trim() + "%' or "
                    + " poliklinik.kd_poli='IGDK' and tgl_registrasi between '" + Valid.SetTgl(DTPCari1.getSelectedItem() + "") + "' and '" + Valid.SetTgl(DTPCari2.getSelectedItem() + "") + "' and reg_periksa.no_rkm_medis like '%" + TCari.getText().trim() + "%' or "
                    + " poliklinik.kd_poli='IGDK' and tgl_registrasi between '" + Valid.SetTgl(DTPCari1.getSelectedItem() + "") + "' and '" + Valid.SetTgl(DTPCari2.getSelectedItem() + "") + "' and reg_periksa.stts_daftar like '%" + TCari.getText().trim() + "%' or "
                    + " poliklinik.kd_poli='IGDK' and tgl_registrasi between '" + Valid.SetTgl(DTPCari1.getSelectedItem() + "") + "' and '" + Valid.SetTgl(DTPCari2.getSelectedItem() + "") + "' and pasien.nm_pasien like '%" + TCari.getText().trim() + "%' or "
                    + " poliklinik.kd_poli='IGDK' and tgl_registrasi between '" + Valid.SetTgl(DTPCari1.getSelectedItem() + "") + "' and '" + Valid.SetTgl(DTPCari2.getSelectedItem() + "") + "' and poliklinik.nm_poli like '%" + TCari.getText().trim() + "%' or "
                    + " poliklinik.kd_poli='IGDK' and tgl_registrasi between '" + Valid.SetTgl(DTPCari1.getSelectedItem() + "") + "' and '" + Valid.SetTgl(DTPCari2.getSelectedItem() + "") + "' and reg_periksa.p_jawab like '%" + TCari.getText().trim() + "%' or "
                    + " poliklinik.kd_poli='IGDK' and tgl_registrasi between '" + Valid.SetTgl(DTPCari1.getSelectedItem() + "") + "' and '" + Valid.SetTgl(DTPCari2.getSelectedItem() + "") + "' and reg_periksa.almt_pj like '%" + TCari.getText().trim() + "%' or "
                    + " poliklinik.kd_poli='IGDK' and tgl_registrasi between '" + Valid.SetTgl(DTPCari1.getSelectedItem() + "") + "' and '" + Valid.SetTgl(DTPCari2.getSelectedItem() + "") + "' and penjab.png_jawab like '%" + TCari.getText().trim() + "%' or "
                    + " poliklinik.kd_poli='IGDK' and tgl_registrasi between '" + Valid.SetTgl(DTPCari1.getSelectedItem() + "") + "' and '" + Valid.SetTgl(DTPCari2.getSelectedItem() + "") + "' and reg_periksa.hubunganpj like '%" + TCari.getText().trim() + "%' order by reg_periksa.no_rawat desc", param);
        }
        this.setCursor(Cursor.getDefaultCursor());
}//GEN-LAST:event_BtnPrintActionPerformed

    private void BtnPrintKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_BtnPrintKeyPressed
        if (evt.getKeyCode() == KeyEvent.VK_SPACE) {
            BtnPrintActionPerformed(null);
        } else {
            Valid.pindah(evt, BtnHapus, BtnAll);
        }
}//GEN-LAST:event_BtnPrintKeyPressed

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

    private void BtnAllActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_BtnAllActionPerformed
        TCari.setText("");
        tampil();
        emptTeks();
}//GEN-LAST:event_BtnAllActionPerformed

    private void BtnAllKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_BtnAllKeyPressed
        if (evt.getKeyCode() == KeyEvent.VK_SPACE) {
            tampil();
            TCari.setText("");
        } else {
            Valid.pindah(evt, BtnPrint, BtnKeluar);
        }
}//GEN-LAST:event_BtnAllKeyPressed

    private void BtnEditActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_BtnEditActionPerformed
        cekinap = 0;
        cekinap = Sequel.cariInteger("select count(1) cek from kamar_inap where no_rawat='" + TNoRw.getText() + "'");
        
        if (TNoReg.getText().trim().equals("")) {
            Valid.textKosong(TNoReg, "No.Regristrasi");
        } else if (TNoRw.getText().trim().equals("")) {
            Valid.textKosong(TNoRw, "No.Rawat");
        } else if (TDokter.getText().trim().equals("")) {
            Valid.textKosong(kddokter, "dokter");
        } else if (TNoRM.getText().trim().equals("") || TPasien.getText().trim().equals("")) {
            Valid.textKosong(TNoRM, "pasien");
        } else if (kdsuku.getText().trim().equals("19") || kdsuku.getText().trim().equals("") || kdsuku.getText().trim().equals("-")) {
            Valid.textKosong(kdsuku, "Suku/Bangsa Pasien");
        } else if (kdbahasa.getText().trim().equals("12") || kdbahasa.getText().trim().equals("") || kdbahasa.getText().trim().equals("-")) {
            Valid.textKosong(kdbahasa, "Bahasa Pasien");
        } else if (cmbTindakan.getSelectedItem().equals("APS") && (cmbKet.getSelectedItem().equals("PULANG") && (alasanAPS.getText().equals("")))) {
            JOptionPane.showMessageDialog(null, "Jika pasiennya pulang APS dari IGD, alasan & keterangan APS harus diisi..!!");
            BtnAlasanAPS.requestFocus();
        } else if (!alasanAPS.getText().equals("") && (ketAPS.getText().trim().equals(""))) {
            JOptionPane.showMessageDialog(null, "Karena pasiennya pulang APS dari IGD, keterangan APS harus diisi..!!");
            ketAPS.requestFocus();
        } else if (cekinap >=1 && (cmbTindakan.getSelectedItem().toString().equals("MENINGGAL DI IGD") || (cmbTindakan.getSelectedItem().toString().equals("D.O.A")))) {
            JOptionPane.showMessageDialog(null, "Pasien ini sudah terlanjur didaftarkan ke rawat inap, batalkan dulu proses rawat inapnya..!!");
        } else {
            switch (TStatus.getText()) {
                case "Baru":
                    biaya = Sequel.cariIsiAngka("select registrasi from poliklinik where kd_poli='IGDK'");
                    break;
                case "Lama":
                    biaya = Sequel.cariIsiAngka("select registrasilama from poliklinik where kd_poli='IGDK'");
                    break;
                default:
                    biaya = Sequel.cariIsiAngka("select registrasi from poliklinik where kd_poli='IGDK'");
                    break;
            }
            
            cekRujuk = Sequel.cariInteger("select count(-1) from rujuk_masuk where no_rawat='" + TNoRw.getText() + "'");
            if (var.getedit_registrasi() == true) {
                Sequel.queryu2("update reg_periksa set no_rawat=?,no_reg=?,kd_dokter=?,no_rkm_medis=?,kd_poli=?,"
                        + "p_jawab=?,almt_pj=?,biaya_reg=?,hubunganpj=?,stts_daftar=?,kd_pj=? where no_rawat=?", 12,
                        new String[]{TNoRw.getText(), TNoReg.getText(), kddokter.getText(), TNoRM.getText(), "IGDK", TPngJwb.getText(), 
                            TAlmt.getText(), "" + biaya, THbngn.getText(), TStatus.getText(), kdpnj.getText(), 
                            tbregistrasiIGD.getValueAt(tbregistrasiIGD.getSelectedRow(), 2).toString()});

                if (!AsalRujukan.getText().equals("")) {
                    Sequel.mengedit("rujuk_masuk", "no_rawat='" + TNoRw.getText() + "'", "perujuk='" + AsalRujukan.getText() + "', alamat='" + alamatperujuk + "', "
                            + "dokter_perujuk='" + AsalRujukan.getText() + "', kd_rujukan='" + kode_rujukanya.getText() + "' ");
                }
                                
                Sequel.mengedit("pasien", "no_rkm_medis='" + TNoRM.getText() + "'", "suku_bangsa='" + kdsuku.getText() + "', bahasa_pasien='" + kdbahasa.getText() + "' ");

                if (noRawatDataIGD.getText().equals("")) {
                    Sequel.menyimpan("data_igd", "'" + TNoRw.getText() + "','" + cmbTindakan.getSelectedItem().toString() + "','" + cmbTrauma.getSelectedItem().toString() + "','"
                            + KataDatang.getText() + "','" + cmbNonTrauma.getSelectedItem().toString() + "','" + cmbKet.getSelectedItem().toString() + "' ", "Data IGD");
                } else if (!noRawatDataIGD.getText().equals("")) {
                    Sequel.mengedit("data_igd", "no_rawat='" + TNoRw.getText() + "'", "tindakan_lanjut='" + cmbTindakan.getSelectedItem().toString() + "', trauma='" + cmbTrauma.getSelectedItem().toString() + "',"
                            + " datang_sendiri='" + KataDatang.getText() + "', non_trauma='" + cmbNonTrauma.getSelectedItem().toString() + "', ket_igd='" + cmbKet.getSelectedItem().toString() + "' ");
                }

                editMati();
                editAPS();

                if (cekRujuk == 0) {
                    Valid.autoNomer3("select ifnull(MAX(CONVERT(RIGHT(rujuk_masuk.no_rawat,4),signed)),0) from reg_periksa inner join rujuk_masuk on reg_periksa.no_rawat=rujuk_masuk.no_rawat where reg_periksa.tgl_registrasi='" + tglDaftar + "' ", "BR/" + tglnoRW + "/", 4, NoBalasan);
                    if (!AsalRujukan.getText().equals("")) {
                        Sequel.menyimpan("rujuk_masuk", "'" + TNoRw.getText() + "','" + AsalRujukan.getText() + "','" + alamatperujuk + "',"
                                + "'-','0','" + AsalRujukan.getText() + "','-','-','-','" + NoBalasan.getText() + "','" + kode_rujukanya.getText() + "'", "No.Rujuk");
                    }
                } else {
                    if (!AsalRujukan.getText().equals("")) {
                        Sequel.mengedit("rujuk_masuk", "no_rawat='" + TNoRw.getText() + "'", "perujuk='" + AsalRujukan.getText() + "', dokter_perujuk='" + AsalRujukan.getText() + "', kd_rujukan='" + kode_rujukanya.getText() + "'");
                    }
                }

            } else {
                if ((Sequel.cariInteger("select count(no_rawat) from rawat_jl_dr where no_rawat=?", TNoRw.getText()) > 0)
                        || (Sequel.cariInteger("select count(no_rawat) from rawat_jl_pr where no_rawat=?", TNoRw.getText()) > 0)
                        || (Sequel.cariInteger("select count(no_rawat) from rawat_jl_drpr where no_rawat=?", TNoRw.getText()) > 0)
                        || (Sequel.cariInteger("select count(no_rawat) from periksa_lab where no_rawat=?", TNoRw.getText()) > 0)
                        || (Sequel.cariInteger("select count(no_rawat) from kamar_inap where no_rawat=?", TNoRw.getText()) > 0)) {
                    JOptionPane.showMessageDialog(rootPane, "Maaf pasien sudah ada transaksi sebelumnya & tidak bisa diedit..!!! ");
                    TCari.requestFocus();
                } else {
                    Sequel.queryu2("update reg_periksa set no_rawat=?,no_reg=?,kd_dokter=?,no_rkm_medis=?,kd_poli=?,"
                            + "p_jawab=?,almt_pj=?,biaya_reg=?,hubunganpj=?,stts_daftar=?,kd_pj=? where no_rawat=?", 12,
                            new String[]{TNoRw.getText(), TNoReg.getText(), kddokter.getText(), TNoRM.getText(), "IGDK", TPngJwb.getText(), 
                                TAlmt.getText(), "" + biaya, THbngn.getText(), TStatus.getText(), kdpnj.getText(), 
                                tbregistrasiIGD.getValueAt(tbregistrasiIGD.getSelectedRow(), 2).toString()});

                    if (!AsalRujukan.getText().equals("")) {
                        Sequel.mengedit("rujuk_masuk", "no_rawat='" + TNoRw.getText() + "'", "perujuk='" + AsalRujukan.getText() + "', alamat='" + alamatperujuk + "', "
                                + "dokter_perujuk='" + AsalRujukan.getText() + "', kd_rujukan='" + kode_rujukanya.getText() + "' ");
                    }
                    
                    Sequel.mengedit("pasien", "no_rkm_medis='" + TNoRM.getText() + "'", "suku_bangsa='" + kdsuku.getText() + "', bahasa_pasien='" + kdbahasa.getText() + "' ");

                    if (noRawatDataIGD.getText().equals("")) {
                        Sequel.menyimpan("data_igd", "'" + TNoRw.getText() + "','" + cmbTindakan.getSelectedItem().toString() + "','" + cmbTrauma.getSelectedItem().toString() + "','"
                                + KataDatang.getText() + "','" + cmbNonTrauma.getSelectedItem().toString() + "','" + cmbKet.getSelectedItem().toString() + "' ", "Data IGD");
                    } else if (!noRawatDataIGD.getText().equals("")) {
                        Sequel.mengedit("data_igd", "no_rawat='" + TNoRw.getText() + "'", "tindakan_lanjut='" + cmbTindakan.getSelectedItem().toString() + "', trauma='" + cmbTrauma.getSelectedItem().toString() + "',"
                                + " datang_sendiri='" + KataDatang.getText() + "', non_trauma='" + cmbNonTrauma.getSelectedItem().toString() + "', ket_igd='" + cmbKet.getSelectedItem().toString() + "' ");
                    }

                    editMati();
                    editAPS();

                    if (cekRujuk == 0) {
                        Valid.autoNomer3("select ifnull(MAX(CONVERT(RIGHT(rujuk_masuk.no_rawat,4),signed)),0) from reg_periksa inner join rujuk_masuk on reg_periksa.no_rawat=rujuk_masuk.no_rawat where reg_periksa.tgl_registrasi='" + tglDaftar + "' ", "BR/" + tglnoRW + "/", 4, NoBalasan);
                        if (!AsalRujukan.getText().equals("")) {
                            Sequel.menyimpan("rujuk_masuk", "'" + TNoRw.getText() + "','" + AsalRujukan.getText() + "','" + alamatperujuk + "','-','0','" + AsalRujukan.getText() + "','-','-','-','" + NoBalasan.getText() + "','" + kode_rujukanya.getText() + "'", "No.Rujuk");
                        }
                    } else {
                        if (!AsalRujukan.getText().equals("")) {
                            Sequel.mengedit("rujuk_masuk", "no_rawat='" + TNoRw.getText() + "'", "perujuk='" + AsalRujukan.getText() + "', alamat='" + alamatperujuk + "', "
                                    + "dokter_perujuk='" + AsalRujukan.getText() + "', kd_rujukan='" + kode_rujukanya.getText() + "' ");
                        }
                    }

                }
            }

            Sequel.menyimpan("history_user", "Now(),'" + TNoRw.getText() + "','" + var.getkode() + "','Registrasi IGD','Ganti'");
            tampil();
            emptTeks();
        }
}//GEN-LAST:event_BtnEditActionPerformed

    private void BtnEditKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_BtnEditKeyPressed
        if (evt.getKeyCode() == KeyEvent.VK_ENTER) {
            BtnEditActionPerformed(null);
        } else {
            Valid.pindah(evt, BtnHapus, BtnPrint);
        }
}//GEN-LAST:event_BtnEditKeyPressed

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
}//GEN-LAST:event_BtnCariActionPerformed

    private void BtnCariKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_BtnCariKeyPressed
        if (evt.getKeyCode() == KeyEvent.VK_SPACE) {
            BtnCariActionPerformed(null);
        } else {
            Valid.pindah(evt, TCari, BtnAll);
        }
}//GEN-LAST:event_BtnCariKeyPressed

    private void tbregistrasiIGDMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tbregistrasiIGDMouseClicked
        if (tabMode.getRowCount() != 0) {
            try {
                getData();
            } catch (java.lang.NullPointerException e) {
            }
            if (evt.getClickCount() == 2) {
                MnTindakanRawatJalanActionPerformed(null);
                
//                i = tbregistrasiIGD.getSelectedColumn();
//                if (i == 1) {
//                    if (var.getkamar_inap() == true) {
//                        MnKamarInapActionPerformed(null);
//                    }
//                } else if (i == 2) {
//                    if (var.getrujukan_masuk() == true) {
//                        MnRujukMasukActionPerformed(null);
//                    }
//                }
            }
        }

}//GEN-LAST:event_tbregistrasiIGDMouseClicked

    private void tbregistrasiIGDKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_tbregistrasiIGDKeyPressed
        if (tabMode.getRowCount() != 0) {
            if ((evt.getKeyCode() == KeyEvent.VK_ENTER) || (evt.getKeyCode() == KeyEvent.VK_UP) || (evt.getKeyCode() == KeyEvent.VK_DOWN)) {
                try {
                    getData();
                } catch (java.lang.NullPointerException e) {
                }
            }
            if (evt.getKeyCode() == KeyEvent.VK_SPACE) {
                MnTindakanRawatJalanActionPerformed(null);
                
//                i = tbregistrasiIGD.getSelectedColumn();
//                if (i == 1) {
//                    if (var.getkamar_inap() == true) {
//                        MnKamarInapActionPerformed(null);
//                    }
//                } else if (i == 2) {
//                    if (var.gettindakan_ralan() == true) {
//                        MnRujukMasukActionPerformed(null);
//                    }
//                }
            }
        }
}//GEN-LAST:event_tbregistrasiIGDKeyPressed

private void kddokterKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_kddokterKeyPressed
    if (evt.getKeyCode() == KeyEvent.VK_PAGE_DOWN) {
        isNumber();
        Sequel.cariIsi("select nm_dokter from dokter where kd_dokter=?", TDokter, kddokter.getText());
    } else if (evt.getKeyCode() == KeyEvent.VK_UP) {
        BtnDokterActionPerformed(null);
    } else {
        isNumber();
        Sequel.cariIsi("select nm_dokter from dokter where kd_dokter=?", TDokter, kddokter.getText());
        Valid.pindah(evt, TNoRw, TNoID);
    }
}//GEN-LAST:event_kddokterKeyPressed

private void BtnDokterActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_BtnDokterActionPerformed
    var.setform("DlgIGD");
    pilihan = 1;
    dokter.isCek();
    dokter.TCari.requestFocus();
    dokter.setSize(1041, 332);
    dokter.setLocationRelativeTo(internalFrame1);
    dokter.setVisible(true);
    dokter.emptTeks();
}//GEN-LAST:event_BtnDokterActionPerformed

private void MnKamarInapActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_MnKamarInapActionPerformed
    cekinap = 0;
    cekinap = Sequel.cariInteger("select count(1) cek from pasien_mati where no_rkm_medis='" + TNoRM.getText() + "'");
    
    if (tabMode.getRowCount() == 0) {
        JOptionPane.showMessageDialog(null, "Maaf, tabel masih kosong...!!!!");
        TNoReg.requestFocus();
    } else if (TPasien.getText().trim().equals("")) {
        JOptionPane.showMessageDialog(null, "Maaf, Silahkan anda pilih dulu dengan mengklik data pada tabel...!!!");
        tbregistrasiIGD.requestFocus();
    } else if (cekinap >=1) {
        JOptionPane.showMessageDialog(null, "Pasien ini sudah meninggal, tidak bisa didaftarkan kerawat inap...!!!");
    } else {
        if (Sequel.cariRegistrasi(TNoRw.getText()) > 0) {
            JOptionPane.showMessageDialog(rootPane, "Data billing sudah terverifikasi..!!");
        } else {
            var.setstatus(true);
            DlgKamarInap dlgki = new DlgKamarInap(null, false);
            dlgki.setSize(internalFrame1.getWidth(), internalFrame1.getHeight());
            dlgki.setLocationRelativeTo(internalFrame1);
            dlgki.emptTeks();
            dlgki.isCek();
            dlgki.setNoRm(TNoRw.getText());
            //dlgki.tampil();
            dlgki.setVisible(true);
            dlgki.cekKetMati();
            dlgki.UserValid();
        }
    }
}//GEN-LAST:event_MnKamarInapActionPerformed

private void MnTindakanRawatJalanActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_MnTindakanRawatJalanActionPerformed
    if (tabMode.getRowCount() == 0) {
        JOptionPane.showMessageDialog(null, "Maaf, tabel masih kosong...!!!!");
        TNoReg.requestFocus();
    } else if (TPasien.getText().trim().equals("")) {
        JOptionPane.showMessageDialog(null, "Maaf, Silahkan anda pilih dulu dengan mengklik data pada tabel...!!!");
        tbregistrasiIGD.requestFocus();
    } else {
//        if (Sequel.cariInteger("select count(no_rawat) from kamar_inap where no_rawat=?", TNoRw.getText()) > 0) {
        if (Sequel.cariInteger("select count(no_rawat) from nota_inap where no_rawat=?", TNoRw.getText()) > 0) {
//            JOptionPane.showMessageDialog(null, "Maaf, Pasien sudah masuk Kamar Inap. Gunakan billing Ranap..!!!");
            JOptionPane.showMessageDialog(null, "Pasien telah menyelesaikan seluruh transaksi & pembayaran biaya perawatan IGD & rawat inap....");
        } else {
            if (Sequel.cariInteger("select count(no_rawat) from kamar_inap where no_rawat=?", TNoRw.getText()) > 0) {
                DlgRawatJalan dlgrwjl = new DlgRawatJalan(null, false);
                dlgrwjl.cekInapIGD("inap");                
                dlgrwjl.setSize(internalFrame1.getWidth(), internalFrame1.getHeight());
                dlgrwjl.setLocationRelativeTo(internalFrame1);
                dlgrwjl.setNoRm(TNoRw.getText(), DTPCari1.getDate(), DTPCari2.getDate());
                dlgrwjl.unitRawatJalan(unitIGD.getText());
                dlgrwjl.tampilDrPr();
                dlgrwjl.TotalNominal();
                dlgrwjl.setVisible(true);
                dlgrwjl.fokus();
                dlgrwjl.petugas(kddokter.getText(), var.getkode());
                dlgrwjl.isCek();
            } else {
                DlgRawatJalan dlgrwjl = new DlgRawatJalan(null, false);                
                dlgrwjl.setSize(internalFrame1.getWidth(), internalFrame1.getHeight());
                dlgrwjl.setLocationRelativeTo(internalFrame1);
                dlgrwjl.setNoRm(TNoRw.getText(), DTPCari1.getDate(), DTPCari2.getDate());
                dlgrwjl.unitRawatJalan(unitIGD.getText());
                dlgrwjl.tampilDrPr();
                dlgrwjl.TotalNominal();
                dlgrwjl.setVisible(true);
                dlgrwjl.fokus();
                dlgrwjl.petugas(kddokter.getText(), var.getkode());
                dlgrwjl.isCek();
            }
        }
    }
}//GEN-LAST:event_MnTindakanRawatJalanActionPerformed

private void MnRujukActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_MnRujukActionPerformed
    if (tabMode.getRowCount() == 0) {
        JOptionPane.showMessageDialog(null, "Maaf, tabel masih kosong...!!!!");
        TNoReg.requestFocus();
    } else if (TPasien.getText().trim().equals("")) {
        JOptionPane.showMessageDialog(null, "Maaf, Silahkan anda pilih dulu dengan mengklik data pada tabel...!!!");
        tbregistrasiIGD.requestFocus();
    } else {
        DlgRujuk dlgrjk = new DlgRujuk(null, false);
        dlgrjk.setSize(internalFrame1.getWidth(), internalFrame1.getHeight());
        dlgrjk.setLocationRelativeTo(internalFrame1);
        dlgrjk.emptTeks();
        dlgrjk.isCek();
        dlgrjk.setNoRm(TNoRw.getText(), DTPCari1.getDate(), DTPCari2.getDate());
        dlgrjk.tampil();
        dlgrjk.setVisible(true);
        dlgrjk.btnFaskes.requestFocus();
    }
}//GEN-LAST:event_MnRujukActionPerformed

private void MnBillingActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_MnBillingActionPerformed
    if (tabMode.getRowCount() == 0) {
        JOptionPane.showMessageDialog(null, "Maaf, tabel masih kosong...!!!!");
        TNoReg.requestFocus();
    } else if (TPasien.getText().trim().equals("")) {
        JOptionPane.showMessageDialog(null, "Maaf, Silahkan anda pilih dulu dengan mengklik data pada tabel...!!!");
        tbregistrasiIGD.requestFocus();
    } else {
        if (Sequel.cariInteger("select count(no_rawat) from kamar_inap where no_rawat=?", TNoRw.getText()) > 0) {
            JOptionPane.showMessageDialog(null, "Maaf, Pasien sudah masuk Kamar Inap. Gunakan billing Ranap..!!!");
        } else {
            if (Sequel.cariInteger("select count(no_rawat) from kamar_inap where no_rawat=?", TNoRw.getText()) > 0) {
                JOptionPane.showMessageDialog(null, "Maaf, Pasien sudah masuk Kamar Inap. Gunakan billing Ranap..!!!");
            } else {
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
                            DlgBilingRalan dlgbil = new DlgBilingRalan(null, false);
                            dlgbil.TNoRw.setText(TNoRw.getText());
                            dlgbil.isRawat();
                            dlgbil.isKembali();
                            dlgbil.isRawat();
                            dlgbil.isCek();
                            dlgbil.setSize(internalFrame1.getWidth(), internalFrame1.getHeight());
                            dlgbil.setLocationRelativeTo(internalFrame1);
                            dlgbil.setVisible(true);
                        }
                    } else {
                        DlgBilingRalan dlgbil = new DlgBilingRalan(null, false);
                        dlgbil.TNoRw.setText(TNoRw.getText());
                        dlgbil.isRawat();
                        dlgbil.isKembali();
                        dlgbil.isRawat();
                        dlgbil.isCek();
                        dlgbil.setSize(internalFrame1.getWidth(), internalFrame1.getHeight());
                        dlgbil.setLocationRelativeTo(internalFrame1);
                        dlgbil.setVisible(true);
                    }
                } catch (Exception e) {
                    System.out.println(e);
                }
            }
        }
    }
}//GEN-LAST:event_MnBillingActionPerformed

private void ChkInputActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_ChkInputActionPerformed
    isForm();
}//GEN-LAST:event_ChkInputActionPerformed

private void ppGrafikPerpoliActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_ppGrafikPerpoliActionPerformed
    tampil();
    grafikperiksaperpoli kas = new grafikperiksaperpoli("Grafik Periksa Per Unit/Poli Tanggal " + Valid.SetTgl(DTPCari1.getSelectedItem() + "") + " S.D. " + Valid.SetTgl(DTPCari2.getSelectedItem() + ""), "where tgl_registrasi between '" + Valid.SetTgl(DTPCari1.getSelectedItem() + "") + "' and '" + Valid.SetTgl(DTPCari2.getSelectedItem() + "") + "' ");
    kas.setSize(this.getWidth(), this.getHeight());
    kas.setLocationRelativeTo(this);
    kas.setVisible(true);
}//GEN-LAST:event_ppGrafikPerpoliActionPerformed

private void ppGrafikPerdokterActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_ppGrafikPerdokterActionPerformed
    tampil();
    grafikperiksaperdokter kas = new grafikperiksaperdokter("Grafik Periksa Per Dokter " + Valid.SetTgl(DTPCari1.getSelectedItem() + "") + " S.D. " + Valid.SetTgl(DTPCari2.getSelectedItem() + ""), "where tgl_registrasi between '" + Valid.SetTgl(DTPCari1.getSelectedItem() + "") + "' and '" + Valid.SetTgl(DTPCari2.getSelectedItem() + "") + "' ");
    kas.setSize(this.getWidth(), this.getHeight());
    kas.setLocationRelativeTo(this);
    kas.setVisible(true);
}//GEN-LAST:event_ppGrafikPerdokterActionPerformed

private void ppGrafikPerJKActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_ppGrafikPerJKActionPerformed
    tampil();
    grafikperiksaperjk kas = new grafikperiksaperjk("Grafik Periksa Per Jenis Kelamin " + Valid.SetTgl(DTPCari1.getSelectedItem() + "") + " S.D. " + Valid.SetTgl(DTPCari2.getSelectedItem() + ""), "where tgl_registrasi between '" + Valid.SetTgl(DTPCari1.getSelectedItem() + "") + "' and '" + Valid.SetTgl(DTPCari2.getSelectedItem() + "") + "' ");
    kas.setSize(this.getWidth(), this.getHeight());
    kas.setLocationRelativeTo(this);
    kas.setVisible(true);
}//GEN-LAST:event_ppGrafikPerJKActionPerformed

private void ppGrafikPerPekerjaanActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_ppGrafikPerPekerjaanActionPerformed
    tampil();
    grafikperiksaperpekerjaan kas = new grafikperiksaperpekerjaan("Grafik Periksa Per Pekerjaan " + Valid.SetTgl(DTPCari1.getSelectedItem() + "") + " S.D. " + Valid.SetTgl(DTPCari2.getSelectedItem() + ""), "where tgl_registrasi between '" + Valid.SetTgl(DTPCari1.getSelectedItem() + "") + "' and '" + Valid.SetTgl(DTPCari2.getSelectedItem() + "") + "' ");
    kas.setSize(this.getWidth(), this.getHeight());
    kas.setLocationRelativeTo(this);
    kas.setVisible(true);
}//GEN-LAST:event_ppGrafikPerPekerjaanActionPerformed

private void ppGrafikPerAgamaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_ppGrafikPerAgamaActionPerformed
    tampil();
    grafikperiksaperagama kas = new grafikperiksaperagama("Grafik Periksa Per Agama " + Valid.SetTgl(DTPCari1.getSelectedItem() + "") + " S.D. " + Valid.SetTgl(DTPCari2.getSelectedItem() + ""), "where tgl_registrasi between '" + Valid.SetTgl(DTPCari1.getSelectedItem() + "") + "' and '" + Valid.SetTgl(DTPCari2.getSelectedItem() + "") + "' ");
    kas.setSize(this.getWidth(), this.getHeight());
    kas.setLocationRelativeTo(this);
    kas.setVisible(true);
}//GEN-LAST:event_ppGrafikPerAgamaActionPerformed

private void ppGrafikPerTahunActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_ppGrafikPerTahunActionPerformed
    tampil();
    grafikperiksapertahun kas = new grafikperiksapertahun("Grafik Periksa Per Tahun " + Valid.SetTgl(DTPCari1.getSelectedItem() + "") + " S.D. " + Valid.SetTgl(DTPCari2.getSelectedItem() + ""), "where tgl_registrasi between '" + Valid.SetTgl(DTPCari1.getSelectedItem() + "") + "' and '" + Valid.SetTgl(DTPCari2.getSelectedItem() + "") + "' ");
    kas.setSize(this.getWidth(), this.getHeight());
    kas.setLocationRelativeTo(this);
    kas.setVisible(true);
}//GEN-LAST:event_ppGrafikPerTahunActionPerformed

private void ppGrafikPerBulanActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_ppGrafikPerBulanActionPerformed
    tampil();
    grafikperiksaperbulan kas = new grafikperiksaperbulan("Grafik Periksa Per Bulan " + Valid.SetTgl(DTPCari1.getSelectedItem() + "") + " S.D. " + Valid.SetTgl(DTPCari2.getSelectedItem() + ""), "where tgl_registrasi between '" + Valid.SetTgl(DTPCari1.getSelectedItem() + "") + "' and '" + Valid.SetTgl(DTPCari2.getSelectedItem() + "") + "' ");
    kas.setSize(this.getWidth(), this.getHeight());
    kas.setLocationRelativeTo(this);
    kas.setVisible(true);
}//GEN-LAST:event_ppGrafikPerBulanActionPerformed

private void ppGrafikPerTanggalActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_ppGrafikPerTanggalActionPerformed
    tampil();
    grafikperiksaperhari kas = new grafikperiksaperhari("Grafik Periksa Per Hari " + Valid.SetTgl(DTPCari1.getSelectedItem() + "") + " S.D. " + Valid.SetTgl(DTPCari2.getSelectedItem() + ""), "where tgl_registrasi between '" + Valid.SetTgl(DTPCari1.getSelectedItem() + "") + "' and '" + Valid.SetTgl(DTPCari2.getSelectedItem() + "") + "' ");
    kas.setSize(this.getWidth(), this.getHeight());
    kas.setLocationRelativeTo(this);
    kas.setVisible(true);
}//GEN-LAST:event_ppGrafikPerTanggalActionPerformed

private void MnCetakSuratSakitActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_MnCetakSuratSakitActionPerformed
    DlgSakit.setSize(550, 121);
    DlgSakit.setLocationRelativeTo(internalFrame1);
    DlgSakit.setVisible(true);
}//GEN-LAST:event_MnCetakSuratSakitActionPerformed

private void BtnPrint2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_BtnPrint2ActionPerformed
    if (TPasien.getText().trim().equals("")) {
        JOptionPane.showMessageDialog(null, "Maaf, Silahkan anda pilih dulu pasien...!!!");
    } else {
        Map<String, Object> param = new HashMap<>();
        param.put("hari", lmsakit.getText());
        param.put("TanggalAwal", TglSakit1.getSelectedItem().toString());
        param.put("TanggalAkhir", TglSakit2.getSelectedItem().toString());
        param.put("namars", var.getnamars());
        param.put("alamatrs", var.getalamatrs());
        param.put("kotars", var.getkabupatenrs());
        param.put("propinsirs", var.getpropinsirs());
        param.put("kontakrs", var.getkontakrs());
        param.put("emailrs", var.getemailrs());
        param.put("logo", Sequel.cariGambar("select logo from setting"));
        Valid.MyReport("rptSuratSakit.jrxml", "report", "::[ Surat Sakit ]::",
                "select reg_periksa.no_rawat,dokter.nm_dokter,pasien.keluarga,pasien.namakeluarga,pasien.tgl_lahir,pasien.jk,"
                + " pasien.nm_pasien,pasien.jk,concat(reg_periksa.umurdaftar,' ',reg_periksa.sttsumur)as umur,pasien.pekerjaan,pasien.alamat"
                + " from reg_periksa inner join pasien inner join dokter"
                + " on reg_periksa.no_rkm_medis=pasien.no_rkm_medis and reg_periksa.kd_dokter=dokter.kd_dokter "
                + "where reg_periksa.no_rawat='" + TNoRw.getText() + "' ", param);
    }
}//GEN-LAST:event_BtnPrint2ActionPerformed

private void BtnKeluar2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_BtnKeluar2ActionPerformed
    DlgSakit.dispose();
}//GEN-LAST:event_BtnKeluar2ActionPerformed

private void BtnPrint3ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_BtnPrint3ActionPerformed

    if (!Kelurahan2.getText().equals("")) {
        DlgDemografi.dispose();
        grafiksql kas = new grafiksql("::[ Data Demografi Per Area Kelurahan " + Kelurahan2.getText() + ", Kecamatan " + Kecamatan2.getText() + ", Kabupaten " + Kabupaten2.getText() + " ]::",
                " reg_periksa inner join pasien inner join kabupaten inner join kecamatan inner join kelurahan on pasien.kd_kab=kabupaten.kd_kab and pasien.kd_kec=kecamatan.kd_kec and pasien.kd_kel=kelurahan.kd_kel "
                + " and reg_periksa.no_rkm_medis=pasien.no_rkm_medis where kabupaten.nm_kab='" + Kabupaten2.getText() + "' and kecamatan.nm_kec='" + Kecamatan2.getText() + "' and kelurahan.nm_kel='" + Kelurahan2.getText() + "'  and reg_periksa.tgl_registrasi between '" + Valid.SetTgl(DTPCari1.getSelectedItem() + "") + "' and '" + Valid.SetTgl(DTPCari2.getSelectedItem() + "") + "'   ",
                "pasien.alamat", "Area");
        kas.setSize(this.getWidth(), this.getHeight());
        kas.setLocationRelativeTo(this);
        kas.setVisible(true);
    } else if (!Kecamatan2.getText().equals("")) {
        DlgDemografi.dispose();
        grafiksql kas = new grafiksql("::[ Data Demografi Per Kelurahan Kecamatan " + Kecamatan2.getText() + " Kabupaten " + Kabupaten2.getText() + " ]::",
                " reg_periksa inner join pasien inner join kabupaten inner join kecamatan inner join kelurahan on pasien.kd_kab=kabupaten.kd_kab "
                + "and pasien.kd_kec=kecamatan.kd_kec and pasien.kd_kel=kelurahan.kd_kel "
                + "and reg_periksa.no_rkm_medis=pasien.no_rkm_medis where kabupaten.nm_kab='" + Kabupaten2.getText() + "' and kecamatan.nm_kec='" + Kecamatan2.getText() + "' and reg_periksa.tgl_registrasi between '" + Valid.SetTgl(DTPCari1.getSelectedItem() + "") + "' and '" + Valid.SetTgl(DTPCari2.getSelectedItem() + "") + "'  ",
                "kelurahan.nm_kel", "Kelurahan");
        kas.setSize(this.getWidth(), this.getHeight());
        kas.setLocationRelativeTo(this);
        kas.setVisible(true);
    } else if (!Kabupaten2.getText().equals("")) {
        DlgDemografi.dispose();
        grafiksql kas = new grafiksql("::[ Data Per Kecamatan Kabupaten " + Kabupaten2.getText() + " ]::",
                " reg_periksa inner join pasien inner join kabupaten inner join kecamatan on pasien.kd_kab=kabupaten.kd_kab "
                + "and pasien.kd_kec=kecamatan.kd_kec and reg_periksa.no_rkm_medis=pasien.no_rkm_medis where kabupaten.nm_kab='" + Kabupaten2.getText() + "'  and reg_periksa.tgl_registrasi between '" + Valid.SetTgl(DTPCari1.getSelectedItem() + "") + "' and '" + Valid.SetTgl(DTPCari2.getSelectedItem() + "") + "'  ",
                "kecamatan.nm_kec", "Kecamatan");
        kas.setSize(this.getWidth(), this.getHeight());
        kas.setLocationRelativeTo(this);
        kas.setVisible(true);
    } else if (Kabupaten2.getText().equals("")) {
        DlgDemografi.dispose();
        grafiksql kas = new grafiksql("::[ Data Demografi Per Kabupaten ]::",
                " reg_periksa inner join pasien inner join kabupaten on pasien.kd_kab=kabupaten.kd_kab and reg_periksa.no_rkm_medis=pasien.no_rkm_medis where reg_periksa.tgl_registrasi between '" + Valid.SetTgl(DTPCari1.getSelectedItem() + "") + "' and '" + Valid.SetTgl(DTPCari2.getSelectedItem() + "") + "' ",
                "kabupaten.nm_kab", "Kabupaten");
        kas.setSize(this.getWidth(), this.getHeight());
        kas.setLocationRelativeTo(this);
        kas.setVisible(true);
    }
}//GEN-LAST:event_BtnPrint3ActionPerformed

private void BtnKeluar3ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_BtnKeluar3ActionPerformed
    DlgDemografi.dispose();
}//GEN-LAST:event_BtnKeluar3ActionPerformed

private void btnKelActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnKelActionPerformed
    var.setform("DlgIGD");
    pasien.kel.emptTeks();
    pasien.kel.setSize(internalFrame1.getWidth() - 40, internalFrame1.getHeight() - 40);
    pasien.kel.setLocationRelativeTo(internalFrame1);
    pasien.kel.setVisible(true);

}//GEN-LAST:event_btnKelActionPerformed

private void btnKecActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnKecActionPerformed
    var.setform("DlgIGD");
    pasien.kec.emptTeks();
    pasien.kec.setSize(internalFrame1.getWidth() - 40, internalFrame1.getHeight() - 40);
    pasien.kec.setLocationRelativeTo(internalFrame1);
    pasien.kec.setVisible(true);
}//GEN-LAST:event_btnKecActionPerformed

private void btnKabActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnKabActionPerformed
    var.setform("DlgIGD");
    pasien.kab.emptTeks();
    pasien.kab.setSize(internalFrame1.getWidth() - 40, internalFrame1.getHeight() - 40);
    pasien.kab.setLocationRelativeTo(internalFrame1);
    pasien.kab.setVisible(true);
}//GEN-LAST:event_btnKabActionPerformed

private void BtnPrint4ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_BtnPrint4ActionPerformed
    this.setCursor(Cursor.getPredefinedCursor(Cursor.WAIT_CURSOR));
    if (tabMode.getRowCount() == 0) {
        JOptionPane.showMessageDialog(null, "Maaf, data sudah habis. Tidak ada data yang bisa anda print...!!!!");
        BtnBatal.requestFocus();
    } else if (tabMode.getRowCount() != 0) {
        if (!Kelurahan2.getText().equals("")) {
            DlgDemografi.dispose();
            Map<String, Object> data = new HashMap<>();
            data.put("judul", "Data Demografi Per Area Kelurahan " + Kelurahan2.getText() + ", Kecamatan " + Kecamatan2.getText() + ", Kabupaten " + Kabupaten2.getText());
            data.put("area", "Area");
            data.put("namars", var.getnamars());
            data.put("alamatrs", var.getalamatrs());
            data.put("kotars", var.getkabupatenrs());
            data.put("propinsirs", var.getpropinsirs());
            data.put("kontakrs", var.getkontakrs());
            data.put("emailrs", var.getemailrs());
            data.put("logo", Sequel.cariGambar("select logo from setting"));
            Valid.MyReport("rptDemografi.jrxml", "report", "::[ Data Demografi Per Area Kelurahan " + Kelurahan2.getText() + ", Kecamatan " + Kecamatan2.getText() + ", Kabupaten " + Kabupaten2.getText() + " ]::",
                    "select  pasien.alamat as area,count(pasien.alamat) as jumlah from reg_periksa inner join pasien "
                    + "inner join kabupaten inner join kecamatan inner join kelurahan on pasien.kd_kab=kabupaten.kd_kab "
                    + "and pasien.kd_kec=kecamatan.kd_kec and pasien.kd_kel=kelurahan.kd_kel and reg_periksa.no_rkm_medis=pasien.no_rkm_medis "
                    + "where kabupaten.nm_kab='" + Kabupaten2.getText() + "' and kecamatan.nm_kec='" + Kecamatan2.getText() + "' "
                    + "and kelurahan.nm_kel='" + Kelurahan2.getText() + "' and reg_periksa.tgl_registrasi between '" + Valid.SetTgl(DTPCari1.getSelectedItem() + "") + "' and '" + Valid.SetTgl(DTPCari2.getSelectedItem() + "") + "' group by pasien.alamat order by pasien.alamat", data);
        } else if (!Kecamatan2.getText().equals("")) {
            DlgDemografi.dispose();
            Map<String, Object> data = new HashMap<>();
            data.put("judul", "Data Demografi Per Kelurahan Kecamatan " + Kecamatan2.getText() + ", Kabupaten " + Kabupaten2.getText());
            data.put("area", "Kelurahan");
            data.put("namars", var.getnamars());
            data.put("alamatrs", var.getalamatrs());
            data.put("kotars", var.getkabupatenrs());
            data.put("propinsirs", var.getpropinsirs());
            data.put("kontakrs", var.getkontakrs());
            data.put("emailrs", var.getemailrs());
            data.put("logo", Sequel.cariGambar("select logo from setting"));
            Valid.MyReport("rptDemografi.jrxml", "report", "::[ Data Demografi Per Kelurahan Kecamatan " + Kecamatan2.getText() + " Kabupaten " + Kabupaten2.getText() + " ]::",
                    "select kelurahan.nm_kel as area,count(kelurahan.nm_kel) as jumlah from reg_periksa inner join pasien "
                    + "inner join kabupaten inner join kecamatan inner join kelurahan on pasien.kd_kab=kabupaten.kd_kab "
                    + "and pasien.kd_kec=kecamatan.kd_kec and pasien.kd_kel=kelurahan.kd_kel and reg_periksa.no_rkm_medis=pasien.no_rkm_medis "
                    + "where kabupaten.nm_kab='" + Kabupaten2.getText() + "' and kecamatan.nm_kec='" + Kecamatan2.getText() + "' and reg_periksa.tgl_registrasi between '" + Valid.SetTgl(DTPCari1.getSelectedItem() + "") + "' and '" + Valid.SetTgl(DTPCari2.getSelectedItem() + "") + "' group by kelurahan.nm_kel order by kelurahan.nm_kel", data);
        } else if (!Kabupaten2.getText().equals("")) {
            DlgDemografi.dispose();
            Map<String, Object> data = new HashMap<>();
            data.put("judul", "Data Demografi Per Kecamatan Kabupaten " + Kabupaten2.getText());
            data.put("area", "Kecamatan");
            data.put("namars", var.getnamars());
            data.put("alamatrs", var.getalamatrs());
            data.put("kotars", var.getkabupatenrs());
            data.put("propinsirs", var.getpropinsirs());
            data.put("kontakrs", var.getkontakrs());
            data.put("emailrs", var.getemailrs());
            data.put("logo", Sequel.cariGambar("select logo from setting"));
            Valid.MyReport("rptDemografi.jrxml", "report", "::[ Data Per Kecamatan Kabupaten " + Kabupaten2.getText() + " ]::",
                    "select kecamatan.nm_kec as area,count(kecamatan.nm_kec) as jumlah from reg_periksa inner join pasien "
                    + "inner join kabupaten inner join kecamatan on pasien.kd_kab=kabupaten.kd_kab and reg_periksa.no_rkm_medis=pasien.no_rkm_medis "
                    + "and pasien.kd_kec=kecamatan.kd_kec where kabupaten.nm_kab='" + Kabupaten2.getText() + "' and reg_periksa.tgl_registrasi between '" + Valid.SetTgl(DTPCari1.getSelectedItem() + "") + "' and '" + Valid.SetTgl(DTPCari2.getSelectedItem() + "") + "' group by kecamatan.nm_kec order by kecamatan.nm_kec", data);
        } else if (Kabupaten2.getText().equals("")) {
            DlgDemografi.dispose();
            Map<String, Object> data = new HashMap<>();
            data.put("judul", "Data Demografi Per Kabupaten");
            data.put("area", "Kabupaten");
            data.put("namars", var.getnamars());
            data.put("alamatrs", var.getalamatrs());
            data.put("kotars", var.getkabupatenrs());
            data.put("propinsirs", var.getpropinsirs());
            data.put("kontakrs", var.getkontakrs());
            data.put("emailrs", var.getemailrs());
            data.put("logo", Sequel.cariGambar("select logo from setting"));
            Valid.MyReport("rptDemografi.jrxml", "report", "::[ Data Demografi Per Kabupaten ]::", "select kabupaten.nm_kab as area,count(kabupaten.nm_kab) as jumlah from reg_periksa inner join pasien "
                    + "inner join kabupaten on pasien.kd_kab=kabupaten.kd_kab and reg_periksa.no_rkm_medis=pasien.no_rkm_medis where reg_periksa.tgl_registrasi between '" + Valid.SetTgl(DTPCari1.getSelectedItem() + "") + "' and '" + Valid.SetTgl(DTPCari2.getSelectedItem() + "") + "' group by kabupaten.nm_kab order by kabupaten.nm_kab", data);
        }
    }
    this.setCursor(Cursor.getDefaultCursor());
}//GEN-LAST:event_BtnPrint4ActionPerformed

private void ppGrafikDemografiActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_ppGrafikDemografiActionPerformed
    DlgDemografi.setSize(550, 180);
    DlgDemografi.setLocationRelativeTo(internalFrame1);
    DlgDemografi.setVisible(true);
}//GEN-LAST:event_ppGrafikDemografiActionPerformed

private void MnRujukMasukActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_MnRujukMasukActionPerformed
    if (TPasien.getText().trim().equals("")) {
        JOptionPane.showMessageDialog(null, "Maaf, Silahkan anda pilih dulu pasien...!!!");
        tbregistrasiIGD.requestFocus();
    } else {

        DlgRujukMasuk rujukmasuk = new DlgRujukMasuk(null, false);
        rujukmasuk.setSize(internalFrame1.getWidth(), internalFrame1.getHeight());
        rujukmasuk.setLocationRelativeTo(internalFrame1);
        rujukmasuk.emptTeks();
        rujukmasuk.isCek();
        rujukmasuk.setNoRm(TNoRw.getText(), DTPCari1.getDate(), DTPCari2.getDate());
        rujukmasuk.tampil();
        rujukmasuk.setVisible(true);
        //this.dispose();
    }
}//GEN-LAST:event_MnRujukMasukActionPerformed

private void kdpnjKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_kdpnjKeyPressed
    if (evt.getKeyCode() == KeyEvent.VK_PAGE_DOWN) {
        Sequel.cariIsi("select png_jawab from penjab where kd_pj=?", nmpnj, kdpnj.getText());
    } else if (evt.getKeyCode() == KeyEvent.VK_PAGE_UP) {
        Sequel.cariIsi("select png_jawab from penjab where kd_pj=?", nmpnj, kdpnj.getText());
        TAlmt.requestFocus();
    } else if (evt.getKeyCode() == KeyEvent.VK_ENTER) {
        Sequel.cariIsi("select png_jawab from penjab where kd_pj=?", nmpnj, kdpnj.getText());
        AsalRujukan.requestFocus();
    } else if (evt.getKeyCode() == KeyEvent.VK_UP) {
        btnPenjabActionPerformed(null);
    }
}//GEN-LAST:event_kdpnjKeyPressed

private void btnPenjabActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnPenjabActionPerformed
    var.setform("DlgIGD");
    pasien.penjab.onCari();
    pasien.penjab.isCek();
    pasien.penjab.setSize(868, 519);
    pasien.penjab.setLocationRelativeTo(internalFrame1);
    pasien.penjab.setVisible(true);
    pasien.penjab.onCari();
}//GEN-LAST:event_btnPenjabActionPerformed

private void MnLaporanRekapKunjunganPoliActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_MnLaporanRekapKunjunganPoliActionPerformed
    if (tabMode.getRowCount() == 0) {
        JOptionPane.showMessageDialog(null, "Maaf, tabel masih kosong...!!!!");
        TNoReg.requestFocus();
    } else {
        Valid.panggilUrl("billing/LaporanKunjunganPoli.php?tanggal1=" + Valid.SetTgl(DTPCari1.getSelectedItem() + "") + "&tanggal2=" + Valid.SetTgl(DTPCari2.getSelectedItem() + ""));
    }
}//GEN-LAST:event_MnLaporanRekapKunjunganPoliActionPerformed

private void MnLaporanRekapKunjunganDokterActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_MnLaporanRekapKunjunganDokterActionPerformed
    if (tabMode.getRowCount() == 0) {
        JOptionPane.showMessageDialog(null, "Maaf, tabel masih kosong...!!!!");
        TNoReg.requestFocus();
    } else {
        Valid.panggilUrl("billing/LaporanKunjunganDokter.php?tanggal1=" + Valid.SetTgl(DTPCari1.getSelectedItem() + "") + "&tanggal2=" + Valid.SetTgl(DTPCari2.getSelectedItem() + ""));
    }
}//GEN-LAST:event_MnLaporanRekapKunjunganDokterActionPerformed

private void MnLaporanRekapJenisBayarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_MnLaporanRekapJenisBayarActionPerformed
    if (tabMode.getRowCount() == 0) {
        JOptionPane.showMessageDialog(null, "Maaf, tabel masih kosong...!!!!");
        TNoReg.requestFocus();
    } else {
        Valid.panggilUrl("billing/LaporanKunjunganJenisBayar.php?tanggal1=" + Valid.SetTgl(DTPCari1.getSelectedItem() + "") + "&tanggal2=" + Valid.SetTgl(DTPCari2.getSelectedItem() + ""));
    }
}//GEN-LAST:event_MnLaporanRekapJenisBayarActionPerformed

private void MnLaporanRekapRawatDaruratActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_MnLaporanRekapRawatDaruratActionPerformed
    if (tabMode.getRowCount() == 0) {
        JOptionPane.showMessageDialog(null, "Maaf, tabel masih kosong...!!!!");
        TNoReg.requestFocus();
    } else {
        Valid.panggilUrl("billing/LaporanRawatDarurat.php?tanggal1=" + Valid.SetTgl(DTPCari1.getSelectedItem() + "") + "&tanggal2=" + Valid.SetTgl(DTPCari2.getSelectedItem() + ""));
    }
}//GEN-LAST:event_MnLaporanRekapRawatDaruratActionPerformed

private void MnLaporanRekapKunjunganBulananActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_MnLaporanRekapKunjunganBulananActionPerformed
    if (tabMode.getRowCount() == 0) {
        JOptionPane.showMessageDialog(null, "Maaf, tabel masih kosong...!!!!");
        TNoReg.requestFocus();
    } else {
        Valid.panggilUrl("billing/LaporanKunjunganBulanan.php?tanggal1=" + Valid.SetTgl(DTPCari1.getSelectedItem() + "") + "&tanggal2=" + Valid.SetTgl(DTPCari2.getSelectedItem() + ""));
    }
}//GEN-LAST:event_MnLaporanRekapKunjunganBulananActionPerformed

private void MnLaporanRekapKunjunganBulananPoliActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_MnLaporanRekapKunjunganBulananPoliActionPerformed
    if (tabMode.getRowCount() == 0) {
        JOptionPane.showMessageDialog(null, "Maaf, tabel masih kosong...!!!!");
        TNoReg.requestFocus();
    } else {
        Valid.panggilUrl("billing/LaporanKunjunganBulananPoli.php?tanggal1=" + Valid.SetTgl(DTPCari1.getSelectedItem() + "") + "&tanggal2=" + Valid.SetTgl(DTPCari2.getSelectedItem() + ""));
    }
}//GEN-LAST:event_MnLaporanRekapKunjunganBulananPoliActionPerformed

    private void MnLaporanRekapPenyakitRalanActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_MnLaporanRekapPenyakitRalanActionPerformed
        this.setCursor(Cursor.getPredefinedCursor(Cursor.WAIT_CURSOR));
        DlgFrekuensiPenyakitRalan ktginventaris = new DlgFrekuensiPenyakitRalan(null, false);
        ktginventaris.isCek();
        ktginventaris.setSize(this.getWidth() - 40, this.getHeight() - 40);
        ktginventaris.setLocationRelativeTo(this);
        ktginventaris.setVisible(true);
        this.setCursor(Cursor.getDefaultCursor());
    }//GEN-LAST:event_MnLaporanRekapPenyakitRalanActionPerformed

    private void Kabupaten2KeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_Kabupaten2KeyPressed
        if (evt.getKeyCode() == KeyEvent.VK_UP) {
            btnKabActionPerformed(null);
        } else {
            Valid.pindah(evt, BtnKeluar3, Kecamatan2);
        }
    }//GEN-LAST:event_Kabupaten2KeyPressed

    private void Kecamatan2KeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_Kecamatan2KeyPressed
        if (evt.getKeyCode() == KeyEvent.VK_UP) {
            btnKecActionPerformed(null);
        } else {
            Valid.pindah(evt, Kabupaten2, Kelurahan2);
        }
    }//GEN-LAST:event_Kecamatan2KeyPressed

    private void Kelurahan2KeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_Kelurahan2KeyPressed
        if (evt.getKeyCode() == KeyEvent.VK_UP) {
            btnKelActionPerformed(null);
        } else {
            Valid.pindah(evt, Kecamatan2, BtnPrint4);
        }
    }//GEN-LAST:event_Kelurahan2KeyPressed

    private void BtnPrint4KeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_BtnPrint4KeyPressed
        if (evt.getKeyCode() == KeyEvent.VK_SPACE) {
            BtnPrint4ActionPerformed(null);
        } else {
            Valid.pindah(evt, Kelurahan2, BtnPrint3);
        }
    }//GEN-LAST:event_BtnPrint4KeyPressed

    private void BtnPrint3KeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_BtnPrint3KeyPressed
        if (evt.getKeyCode() == KeyEvent.VK_SPACE) {
            BtnPrint3ActionPerformed(null);
        } else {
            Valid.pindah(evt, BtnPrint4, BtnKeluar3);
        }
    }//GEN-LAST:event_BtnPrint3KeyPressed

    private void BtnKeluar3KeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_BtnKeluar3KeyPressed
        if (evt.getKeyCode() == KeyEvent.VK_SPACE) {
            BtnKeluar3ActionPerformed(null);
        } else {
            Valid.pindah(evt, BtnPrint3, Kabupaten2);
        }
    }//GEN-LAST:event_BtnKeluar3KeyPressed

    private void formWindowOpened(java.awt.event.WindowEvent evt) {//GEN-FIRST:event_formWindowOpened
        tampil();
    }//GEN-LAST:event_formWindowOpened

    private void MnBarcodeActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_MnBarcodeActionPerformed
        if (!TPasien.getText().isEmpty()) {
            Map<String, Object> data = new HashMap<>();
            data.put("nama", TPasien.getText());
            data.put("alamat", Sequel.cariIsi("select date_format(tgl_lahir,'%d/%m/%Y') from pasien where no_rkm_medis=?", TNoRM.getText()));
            data.put("norm", TNoRM.getText());
            data.put("parameter", "%" + TCari.getText().trim() + "%");
            data.put("namars", var.getnamars());
            data.put("alamatrs", var.getalamatrs());
            data.put("kotars", var.getkabupatenrs());
            data.put("propinsirs", var.getpropinsirs());
            data.put("kontakrs", var.getkontakrs());
            data.put("emailrs", var.getemailrs());
            Valid.MyReport("rptBarcodeRawat.jrxml", "report", "::[ Barcode No.Rawat ]::",
                    "select no_rawat from reg_periksa where no_rawat='" + TNoRw.getText() + "'", data);
        }
    }//GEN-LAST:event_MnBarcodeActionPerformed

    private void MnDiagnosaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_MnDiagnosaActionPerformed
        if (TPasien.getText().trim().equals("")) {
            JOptionPane.showMessageDialog(null, "Maaf, Silahkan anda pilih dulu pasien...!!!");
            tbregistrasiIGD.requestFocus();
        } else {
            if (Sequel.cariInteger("select count(no_rawat) from kamar_inap where no_rawat=?", TNoRw.getText()) > 0) {
                JOptionPane.showMessageDialog(null, "Maaf, Pasien sudah masuk Kamar Inap. Masukkan diagnosa lewat kamar inap..!!!");
            } else {
                DlgDiagnosaPenyakit resep = new DlgDiagnosaPenyakit(null, false);
                resep.setSize(internalFrame1.getWidth(), internalFrame1.getHeight());
                resep.setLocationRelativeTo(internalFrame1);
                resep.isCek();
                resep.setNoRm(TNoRw.getText(), DTPCari1.getDate(), DTPCari2.getDate(), "Ralan");
                resep.tampil();
                resep.setVisible(true);
            }
        }
    }//GEN-LAST:event_MnDiagnosaActionPerformed

    private void AsalRujukanKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_AsalRujukanKeyPressed
        if (evt.getKeyCode() == KeyEvent.VK_PAGE_UP) {
            kdpnj.requestFocus();
        } else if (evt.getKeyCode() == KeyEvent.VK_ENTER) {
            BtnSimpan.requestFocus();
        } else if (evt.getKeyCode() == KeyEvent.VK_UP) {
            btnPenjabActionPerformed(null);
        }
    }//GEN-LAST:event_AsalRujukanKeyPressed

    private void btnPenjab1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnPenjab1ActionPerformed
        var.setform("DlgReg");
        rujukmasuk.tampil2();
        rujukmasuk.WindowPerujuk.setSize(900, 573);
        rujukmasuk.WindowPerujuk.setLocationRelativeTo(internalFrame1);
        rujukmasuk.WindowPerujuk.setVisible(true);
        rujukmasuk.onCariPerujuk();
    }//GEN-LAST:event_btnPenjab1ActionPerformed

    private void MnCetakSuratSakit1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_MnCetakSuratSakit1ActionPerformed
        DlgSakit.setSize(550, 121);
        DlgSakit.setLocationRelativeTo(internalFrame1);
        DlgSakit.setVisible(true);
    }//GEN-LAST:event_MnCetakSuratSakit1ActionPerformed

    private void MnCetakSuratSakit2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_MnCetakSuratSakit2ActionPerformed
        DlgSakit2.setSize(550, 151);
        DlgSakit2.setLocationRelativeTo(internalFrame1);
        DlgSakit2.setVisible(true);
    }//GEN-LAST:event_MnCetakSuratSakit2ActionPerformed

    private void MnCetakRegisterActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_MnCetakRegisterActionPerformed
        if (TPasien.getText().trim().equals("")) {
            JOptionPane.showMessageDialog(null, "Maaf, Silahkan anda pilih dulu pasien...!!!");
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
            Valid.MyReport("rptBuktiRegister.jrxml", "report", "::[ Bukti Register ]::",
                    "select reg_periksa.no_reg,reg_periksa.no_rawat,reg_periksa.tgl_registrasi,reg_periksa.jam_reg,pasien.no_tlp,"
                    + "reg_periksa.kd_dokter,dokter.nm_dokter,reg_periksa.no_rkm_medis,pasien.nm_pasien,pasien.jk,concat(year(from_days(datediff(now(), pasien.tgl_lahir))),' Th ',month(from_days(datediff(now(),pasien.tgl_lahir))),' Bl ',day(from_days(datediff(now(),pasien.tgl_lahir))),' Hr')as umur,poliklinik.nm_poli,"
                    + "reg_periksa.p_jawab,reg_periksa.almt_pj,reg_periksa.hubunganpj,reg_periksa.biaya_reg,reg_periksa.stts_daftar,penjab.png_jawab "
                    + "from reg_periksa inner join dokter inner join pasien inner join poliklinik inner join penjab "
                    + "on reg_periksa.kd_dokter=dokter.kd_dokter and reg_periksa.no_rkm_medis=pasien.no_rkm_medis "
                    + "and reg_periksa.kd_pj=penjab.kd_pj and reg_periksa.kd_poli=poliklinik.kd_poli where reg_periksa.no_rawat='" + TNoRw.getText() + "' ", param);
            this.setCursor(Cursor.getDefaultCursor());
        }
    }//GEN-LAST:event_MnCetakRegisterActionPerformed

    private void MnPersetujuanMedisActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_MnPersetujuanMedisActionPerformed
        if (TPasien.getText().trim().equals("")) {
            JOptionPane.showMessageDialog(null, "Maaf, Silahkan anda pilih dulu pasien...!!!");
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
            Valid.MyReport("persetujuantindakanmedis.jrxml", "report", "::[ Persetujuan Tindakan ]::", "select pasien.no_rkm_medis, pasien.nm_pasien, pasien.no_ktp, pasien.jk, "
                    + "pasien.tmp_lahir, pasien.tgl_lahir,pasien.nm_ibu, concat(pasien.alamat,', ',kelurahan.nm_kel,', ',kecamatan.nm_kec,', ',kabupaten.nm_kab) as alamat, pasien.gol_darah, pasien.pekerjaan,"
                    + "pasien.stts_nikah,pasien.agama,pasien.tgl_daftar,pasien.no_tlp,pasien.umur,"
                    + "pasien.pnd, pasien.keluarga, pasien.namakeluarga,penjab.png_jawab,pasien.pekerjaanpj,"
                    + "concat(pasien.alamatpj,', ',pasien.kelurahanpj,', ',pasien.kecamatanpj,', ',pasien.kabupatenpj) as alamatpj from pasien "
                    + "inner join kelurahan inner join kecamatan inner join kabupaten "
                    + "inner join penjab on pasien.kd_pj=penjab.kd_pj and pasien.kd_kel=kelurahan.kd_kel "
                    + "and pasien.kd_kec=kecamatan.kd_kec and pasien.kd_kab=kabupaten.kd_kab where pasien.no_rkm_medis='" + TNoRM.getText() + "' ", param);
            this.setCursor(Cursor.getDefaultCursor());
        }
    }//GEN-LAST:event_MnPersetujuanMedisActionPerformed

    private void BtnPrint5ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_BtnPrint5ActionPerformed
        if (TPasien.getText().trim().equals("")) {
            JOptionPane.showMessageDialog(null, "Maaf, Silahkan anda pilih dulu pasien...!!!");
        } else {
            this.setCursor(Cursor.getPredefinedCursor(Cursor.WAIT_CURSOR));
            Map<String, Object> param = new HashMap<>();
            param.put("hari", lmsakit.getText());
            param.put("TanggalAwal", TglSakit1.getSelectedItem().toString());
            param.put("TanggalAkhir", TglSakit2.getSelectedItem().toString());
            param.put("namars", var.getnamars());
            param.put("alamatrs", var.getalamatrs());
            param.put("kotars", var.getkabupatenrs());
            param.put("propinsirs", var.getpropinsirs());
            param.put("kontakrs", var.getkontakrs());
            param.put("nomersurat", NomorSurat.getText());
            param.put("dokterpj", CrDokter3.getText());
            param.put("emailrs", var.getemailrs());
            param.put("logo", Sequel.cariGambar("select logo from setting"));
            Valid.MyReport("rptSuratSakit2.jrxml", "report", "::[ Surat Sakit ]::",
                    "select reg_periksa.no_rkm_medis,dokter.nm_dokter,pasien.keluarga,pasien.namakeluarga,pasien.tgl_lahir,pasien.jk,"
                    + " pasien.nm_pasien,pasien.jk,concat(reg_periksa.umurdaftar,' ',reg_periksa.sttsumur)as umur,pasien.pekerjaan,pasien.alamat"
                    + " from reg_periksa inner join pasien inner join dokter"
                    + " on reg_periksa.no_rkm_medis=pasien.no_rkm_medis and reg_periksa.kd_dokter=dokter.kd_dokter  "
                    + "where reg_periksa.no_rawat='" + TNoRw.getText() + "' ", param);
            this.setCursor(Cursor.getDefaultCursor());
        }
    }//GEN-LAST:event_BtnPrint5ActionPerformed

    private void BtnKeluar4ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_BtnKeluar4ActionPerformed
        DlgSakit2.dispose();
    }//GEN-LAST:event_BtnKeluar4ActionPerformed

    private void BtnSeek5ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_BtnSeek5ActionPerformed
        var.setform("DlgReg");
        pilihan = 3;
        dokter.isCek();
        dokter.TCari.requestFocus();
        dokter.setSize(internalFrame1.getWidth() - 40, internalFrame1.getHeight() - 40);
        dokter.setLocationRelativeTo(internalFrame1);
        dokter.setVisible(true);
    }//GEN-LAST:event_BtnSeek5ActionPerformed

    private void MnCheckListActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_MnCheckListActionPerformed
        if (!TPasien.getText().equals("")) {
            this.setCursor(Cursor.getPredefinedCursor(Cursor.WAIT_CURSOR));
            Map<String, Object> param = new HashMap<>();
            param.put("poli", "UGD/IGD");
            param.put("antrian", TNoReg.getText());
            param.put("nama", TPasien.getText());
            param.put("norm", TNoRM.getText());
            param.put("bayar", nmpnj.getText());
            param.put("namars", var.getnamars());
            param.put("alamatrs", var.getalamatrs());
            param.put("kotars", var.getkabupatenrs());
            param.put("propinsirs", var.getpropinsirs());
            param.put("kontakrs", var.getkontakrs());
            param.put("emailrs", var.getemailrs());
            param.put("logo", Sequel.cariGambar("select logo from setting"));
            Valid.MyReport("rptCheckList.jrxml", "report", "::[ Check List ]::",
                    "select current_date() as sekarang", param);
            this.setCursor(Cursor.getDefaultCursor());
        }
    }//GEN-LAST:event_MnCheckListActionPerformed

    private void MnCheckList1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_MnCheckList1ActionPerformed
        if (!TPasien.getText().equals("")) {
            this.setCursor(Cursor.getPredefinedCursor(Cursor.WAIT_CURSOR));
            Map<String, Object> param = new HashMap<>();
            param.put("poli", "UGD/IGD");
            param.put("antrian", TNoReg.getText());
            param.put("nama", TPasien.getText());
            param.put("norm", TNoRM.getText());
            param.put("bayar", nmpnj.getText());
            param.put("namars", var.getnamars());
            param.put("alamatrs", var.getalamatrs());
            param.put("kotars", var.getkabupatenrs());
            param.put("propinsirs", var.getpropinsirs());
            param.put("kontakrs", var.getkontakrs());
            param.put("emailrs", var.getemailrs());
            param.put("logo", Sequel.cariGambar("select logo from setting"));
            Valid.MyReport("rptCheckList2.jrxml", "report", "::[ Check List ]::",
                    "select current_date() as sekarang", param);
            this.setCursor(Cursor.getDefaultCursor());
        }
    }//GEN-LAST:event_MnCheckList1ActionPerformed

    private void MnCheckList2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_MnCheckList2ActionPerformed
        if (!TPasien.getText().equals("")) {
            this.setCursor(Cursor.getPredefinedCursor(Cursor.WAIT_CURSOR));
            Map<String, Object> param = new HashMap<>();
            param.put("poli", "UGD/IGD");
            param.put("antrian", TNoReg.getText());
            param.put("nama", TPasien.getText());
            param.put("norm", TNoRM.getText());
            param.put("bayar", nmpnj.getText());
            param.put("dokter", TDokter.getText());
            param.put("namars", var.getnamars());
            param.put("alamatrs", var.getalamatrs());
            param.put("alamat", TAlmt.getText());
            param.put("kotars", var.getkabupatenrs());
            param.put("propinsirs", var.getpropinsirs());
            param.put("kontakrs", var.getkontakrs());
            param.put("emailrs", var.getemailrs());
            param.put("logo", Sequel.cariGambar("select logo from setting"));
            Valid.MyReport("rptCheckList4.jrxml", "report", "::[ Check List ]::",
                    "select current_date() as sekarang", param);
            this.setCursor(Cursor.getDefaultCursor());
        }
    }//GEN-LAST:event_MnCheckList2ActionPerformed

    private void MnCheckList3ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_MnCheckList3ActionPerformed
        if (!TPasien.getText().equals("")) {
            this.setCursor(Cursor.getPredefinedCursor(Cursor.WAIT_CURSOR));
            Map<String, Object> param = new HashMap<>();
            param.put("poli", "UGD/IGD");
            param.put("antrian", TNoReg.getText());
            param.put("nama", TPasien.getText());
            param.put("norm", TNoRM.getText());
            param.put("dokter", TDokter.getText());
            param.put("bayar", nmpnj.getText());
            param.put("namars", var.getnamars());
            param.put("alamatrs", var.getalamatrs());
            param.put("alamat", TAlmt.getText());
            param.put("kotars", var.getkabupatenrs());
            param.put("propinsirs", var.getpropinsirs());
            param.put("kontakrs", var.getkontakrs());
            param.put("emailrs", var.getemailrs());
            param.put("logo", Sequel.cariGambar("select logo from setting"));
            Valid.MyReport("rptCheckList3.jrxml", "report", "::[ Check List ]::",
                    "select current_date() as sekarang", param);
            this.setCursor(Cursor.getDefaultCursor());
        }
    }//GEN-LAST:event_MnCheckList3ActionPerformed

    private void MnCheckList4ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_MnCheckList4ActionPerformed
        this.setCursor(Cursor.getPredefinedCursor(Cursor.WAIT_CURSOR));
        if (!TPasien.getText().equals("")) {
            Map<String, Object> param = new HashMap<>();
            param.put("poli", "UGD/IGD");
            param.put("antrian", TNoReg.getText());
            param.put("nama", TPasien.getText());
            param.put("norm", TNoRM.getText());
            param.put("dokter", TDokter.getText());
            param.put("no_rawat", TNoRw.getText());
            param.put("bayar", nmpnj.getText());
            param.put("penjab", TPngJwb.getText());
            param.put("namars", var.getnamars());
            param.put("alamatrs", var.getalamatrs());
            param.put("kotars", var.getkabupatenrs());
            param.put("propinsirs", var.getpropinsirs());
            param.put("kontakrs", var.getkontakrs());
            param.put("emailrs", var.getemailrs());
            param.put("logo", Sequel.cariGambar("select logo from setting"));
            Valid.MyReport("rptLembarPeriksa.jrxml", "report", "::[ Lembar Periksa ]::",
                    "select date_format(current_date(),'%d/%m/%Y') as sekarang", param);
        }
        this.setCursor(Cursor.getDefaultCursor());
    }//GEN-LAST:event_MnCheckList4ActionPerformed

    private void MnCheckList5ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_MnCheckList5ActionPerformed
        this.setCursor(Cursor.getPredefinedCursor(Cursor.WAIT_CURSOR));
        if (!TPasien.getText().equals("")) {
            Map<String, Object> param = new HashMap<>();
            param.put("poli", "UGD/IGD");
            param.put("antrian", TNoReg.getText());
            param.put("nama", TPasien.getText());
            param.put("norm", TNoRM.getText());
            param.put("dokter", TDokter.getText());
            param.put("no_rawat", TNoRw.getText());
            param.put("bayar", nmpnj.getText());
            param.put("penjab", TPngJwb.getText());
            param.put("namars", var.getnamars());
            param.put("alamatrs", var.getalamatrs());
            param.put("kotars", var.getkabupatenrs());
            param.put("propinsirs", var.getpropinsirs());
            param.put("kontakrs", var.getkontakrs());
            param.put("emailrs", var.getemailrs());
            param.put("logo", Sequel.cariGambar("select logo from setting"));
            Valid.MyReport("rptLembarPeriksa2.jrxml", "report", "::[ Lembar Periksa ]::",
                    "select date_format(current_date(),'%d/%m/%Y') as sekarang", param);
        }
        this.setCursor(Cursor.getDefaultCursor());
    }//GEN-LAST:event_MnCheckList5ActionPerformed

    private void MnCheckList6ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_MnCheckList6ActionPerformed
        this.setCursor(Cursor.getPredefinedCursor(Cursor.WAIT_CURSOR));
        if (!TPasien.getText().equals("")) {
            Map<String, Object> param = new HashMap<>();
            param.put("poli", "UGD/IGD");
            param.put("antrian", TNoReg.getText());
            param.put("nama", TPasien.getText());
            param.put("norm", TNoRM.getText());
            param.put("lahir", Sequel.cariIsi("select DATE_FORMAT(tgl_lahir,'%d-%m-%Y') from pasien where no_rkm_medis=? ", TNoRM.getText()));
            param.put("dokter", TDokter.getText());
            param.put("no_rawat", TNoRw.getText());
            param.put("bayar", nmpnj.getText());
            param.put("penjab", TPngJwb.getText());
            param.put("namars", var.getnamars());
            param.put("alamatrs", var.getalamatrs());
            param.put("kotars", var.getkabupatenrs());
            param.put("propinsirs", var.getpropinsirs());
            param.put("kontakrs", var.getkontakrs());
            param.put("emailrs", var.getemailrs());
            param.put("logo", Sequel.cariGambar("select logo from setting"));
            Valid.MyReport("rptLembarPeriksa3.jrxml", "report", "::[ Lembar Periksa]::",
                    "select date_format(current_date(),'%d/%m/%Y') as sekarang", param);
        }
        this.setCursor(Cursor.getDefaultCursor());
    }//GEN-LAST:event_MnCheckList6ActionPerformed

    private void MnCheckList7ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_MnCheckList7ActionPerformed
        this.setCursor(Cursor.getPredefinedCursor(Cursor.WAIT_CURSOR));
        if (!TPasien.getText().equals("")) {
            Map<String, Object> param = new HashMap<>();
            param.put("poli", "UGD/IGD");
            param.put("antrian", TNoReg.getText());
            param.put("nama", TPasien.getText());
            param.put("norm", TNoRM.getText());
            param.put("lahir", Sequel.cariIsi("select DATE_FORMAT(tgl_lahir,'%d-%m-%Y') from pasien where no_rkm_medis=? ", TNoRM.getText()));
            param.put("dokter", TDokter.getText());
            param.put("no_rawat", TNoRw.getText());
            param.put("bayar", nmpnj.getText());
            param.put("penjab", TPngJwb.getText());
            param.put("namars", var.getnamars());
            param.put("alamatrs", var.getalamatrs());
            param.put("kotars", var.getkabupatenrs());
            param.put("propinsirs", var.getpropinsirs());
            param.put("kontakrs", var.getkontakrs());
            param.put("emailrs", var.getemailrs());
            param.put("logo", Sequel.cariGambar("select logo from setting"));
            Valid.MyReport("rptLembarPeriksa4.jrxml", "report", "::[ Lembar Periksa]::",
                    "select date_format(current_date(),'%d/%m/%Y') as sekarang", param);
        }
        this.setCursor(Cursor.getDefaultCursor());
    }//GEN-LAST:event_MnCheckList7ActionPerformed

    private void MnSEPActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_MnSEPActionPerformed
        if (tabMode.getRowCount() == 0) {
            JOptionPane.showMessageDialog(null, "Maaf, tabel masih kosong...!!!!");
            TNoReg.requestFocus();
        } else if (TPasien.getText().trim().equals("")) {
            JOptionPane.showMessageDialog(null, "Maaf, Silahkan anda pilih dulu dengan mengklik data pada tabel...!!!");
            tbregistrasiIGD.requestFocus();
        } else {
            this.setCursor(Cursor.getPredefinedCursor(Cursor.WAIT_CURSOR));
            var.setform("DlgIGD");
            BPJSDataSEP dlgki = new BPJSDataSEP(null, false);
            dlgki.setSize(internalFrame1.getWidth(), internalFrame1.getHeight());
            dlgki.setLocationRelativeTo(internalFrame1);
            dlgki.isCek();
            dlgki.setNoRm(TNoRw.getText(), tglDaftar, "2. Ralan", "IGDK", "Unit IGD/UGD");
            dlgki.tampil();
            dlgki.setVisible(true);
            dlgki.cekLAKA();
            dlgki.cekLAYAN();
            this.setCursor(Cursor.getDefaultCursor());
        }
    }//GEN-LAST:event_MnSEPActionPerformed

    private void MnBarcodeRM1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_MnBarcodeRM1ActionPerformed
        if (tabMode.getRowCount() == 0) {
            JOptionPane.showMessageDialog(null, "Maaf, data pasien sudah habis...!!!!");
            BtnBatal.requestFocus();
        } else if (TPasien.getText().trim().equals("")) {
            JOptionPane.showMessageDialog(null, "Maaf, Silahkan anda pilih dulu data pasien dengan mengklik data pada tabel...!!!");
            tbregistrasiIGD.requestFocus();
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
            Valid.MyReport("rptBarcodeRM11.jrxml", "report", "::[ Label Barcode Kertas BESAR ]::", "select pasien.no_rkm_medis, pasien.nm_pasien, pasien.no_ktp, pasien.jk, "
                    + "pasien.tmp_lahir, DATE_FORMAT(pasien.tgl_lahir,'%d/%m/%Y') as tgl_lahir, pasien.nm_ibu, concat(pasien.alamat,', ',kelurahan.nm_kel,', ',kecamatan.nm_kec,', ',kabupaten.nm_kab) as alamat, pasien.gol_darah, pasien.pekerjaan,"
                    + "pasien.stts_nikah,pasien.agama,pasien.tgl_daftar,pasien.no_tlp,pasien.umur,"
                    + "pasien.pnd, pasien.keluarga, pasien.namakeluarga,penjab.png_jawab,pasien.pekerjaanpj,"
                    + "concat(pasien.alamatpj,', ',pasien.kelurahanpj,', ',pasien.kecamatanpj,', ',pasien.kabupatenpj) as alamatpj from pasien "
                    + "inner join kelurahan inner join kecamatan inner join kabupaten "
                    + "inner join penjab on pasien.kd_pj=penjab.kd_pj and pasien.kd_kel=kelurahan.kd_kel "
                    + "and pasien.kd_kec=kecamatan.kd_kec and pasien.kd_kab=kabupaten.kd_kab where pasien.no_rkm_medis='" + TNoRM.getText() + "' ", param);
            this.setCursor(Cursor.getDefaultCursor());
        }
    }//GEN-LAST:event_MnBarcodeRM1ActionPerformed

    private void MnLembarCasemixActionPerformed(java.awt.event.ActionEvent evt) {
        if (TPasien.getText().trim().equals("")) {
            JOptionPane.showMessageDialog(null, "Maaf, Silahkan anda pilih dulu pasien...!!!");
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
            Valid.MyReport("rptLembarCasemix.jrxml", "report", "::[ Surat Jaminan & Bukti Pelayanan Rawat Jalan ]::",
                    "select reg_periksa.no_reg,reg_periksa.no_rawat,reg_periksa.tgl_registrasi,"
                    + "reg_periksa.jam_reg, reg_periksa.kd_dokter,dokter.nm_dokter,reg_periksa.no_rkm_medis,pasien.nm_pasien,pasien.jk,concat(reg_periksa.umurdaftar,' ',reg_periksa.sttsumur)as umur,poliklinik.nm_poli,"
                    + "reg_periksa.p_jawab,reg_periksa.almt_pj,reg_periksa.hubunganpj,reg_periksa.biaya_reg,"
                    + "reg_periksa.stts_daftar,penjab.png_jawab,pasien.no_peserta,pasien.tgl_lahir "
                    + "from reg_periksa inner join dokter inner join pasien inner join poliklinik inner join penjab "
                    + "on reg_periksa.kd_dokter=dokter.kd_dokter and reg_periksa.no_rkm_medis=pasien.no_rkm_medis and reg_periksa.kd_pj=penjab.kd_pj and reg_periksa.kd_poli=poliklinik.kd_poli "
                    + "where reg_periksa.no_rawat='" + TNoRw.getText() + "' ", param);
            this.setCursor(Cursor.getDefaultCursor());
        }
    }

    private void MnCatatanPasienBtnPrintActionPerformed(java.awt.event.ActionEvent evt) {
        if (tabMode.getRowCount() == 0) {
            JOptionPane.showMessageDialog(null, "Maaf, data pasien sudah habis...!!!!");
            TNoRw.requestFocus();
        } else if (TPasien.getText().trim().equals("")) {
            JOptionPane.showMessageDialog(null, "Maaf, Silahkan anda pilih dulu data registrasi pada tabel...!!!");
            TCari.requestFocus();
        } else {
            this.setCursor(Cursor.getPredefinedCursor(Cursor.WAIT_CURSOR));
            DlgCatatan catatan = new DlgCatatan(null, true);
            catatan.setNoRm(TNoRM.getText());
            catatan.setSize(720, 330);
            catatan.setLocationRelativeTo(internalFrame1);
            catatan.setVisible(true);
            this.setCursor(Cursor.getDefaultCursor());
        }
    }

    private void MnSPBKActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_MnSPBKActionPerformed
        if (TPasien.getText().trim().equals("")) {
            JOptionPane.showMessageDialog(null, "Maaf, Silahkan anda pilih dulu pasien...!!!");
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
            Valid.MyReport("rptSBPK.jrxml", "report", "::[ Surat Bukti Pelayanan Kesehatan ]::",
                    "select reg_periksa.no_reg,reg_periksa.no_rawat,reg_periksa.tgl_registrasi,"
                    + "reg_periksa.jam_reg, reg_periksa.kd_dokter,dokter.nm_dokter,reg_periksa.no_rkm_medis,pasien.nm_pasien,pasien.jk,concat(reg_periksa.umurdaftar,' ',reg_periksa.sttsumur)as umur,poliklinik.nm_poli,"
                    + "reg_periksa.p_jawab,reg_periksa.almt_pj,reg_periksa.hubunganpj,reg_periksa.biaya_reg,"
                    + "reg_periksa.stts_daftar,penjab.png_jawab,pasien.no_peserta,pasien.tgl_lahir "
                    + "from reg_periksa inner join dokter inner join pasien inner join poliklinik inner join penjab "
                    + "on reg_periksa.kd_dokter=dokter.kd_dokter and reg_periksa.no_rkm_medis=pasien.no_rkm_medis and reg_periksa.kd_pj=penjab.kd_pj and reg_periksa.kd_poli=poliklinik.kd_poli "
                    + "where reg_periksa.no_rawat='" + TNoRw.getText() + "' ", param);
            this.setCursor(Cursor.getDefaultCursor());
        }
    }//GEN-LAST:event_MnSPBKActionPerformed

    private void ppRiwayatBtnPrintActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_ppRiwayatBtnPrintActionPerformed
        if (tabMode.getRowCount() == 0) {
            JOptionPane.showMessageDialog(null, "Maaf, data pasien sudah habis...!!!!");
            TNoRw.requestFocus();
        } else if (TPasien.getText().trim().equals("")) {
            JOptionPane.showMessageDialog(null, "Maaf, Silahkan anda pilih dulu data registrasi pada tabel...!!!");
            TCari.requestFocus();
        } else {
            this.setCursor(Cursor.getPredefinedCursor(Cursor.WAIT_CURSOR));
            DlgResumePerawatan resume = new DlgResumePerawatan(null, true);
            resume.setNoRm(TNoRM.getText(), TPasien.getText());
            resume.tampil();
            resume.setSize(internalFrame1.getWidth() - 40, internalFrame1.getHeight() - 40);
            resume.setLocationRelativeTo(internalFrame1);
            resume.setVisible(true);
            this.setCursor(Cursor.getDefaultCursor());
        }
    }//GEN-LAST:event_ppRiwayatBtnPrintActionPerformed

    private void ppBerkasBtnPrintActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_ppBerkasBtnPrintActionPerformed
        if (tabMode.getRowCount() == 0) {
            JOptionPane.showMessageDialog(null, "Maaf, table masih kosong...!!!!");
            //TNoReg.requestFocus();
        } else if (TNoRw.getText().trim().equals("")) {
            JOptionPane.showMessageDialog(null, "Maaf, Silahkan anda pilih dulu dengan menklik data pada table...!!!");
            tbregistrasiIGD.requestFocus();
        } else {
            Sequel.menyimpan("mutasi_berkas", "'" + TNoRw.getText() + "','Sudah Diterima',now(),now(),'0000-00-00 00:00:00','0000-00-00 00:00:00'", "status='Sudah Diterima',diterima=now()", "no_rawat='" + TNoRw.getText() + "'");
            Valid.editTable(tabMode, "reg_periksa", "no_rawat", TNoRw, "stts='Berkas Diterima'");
            if (tabMode.getRowCount() != 0) {
                tampil();
            }
        }
    }//GEN-LAST:event_ppBerkasBtnPrintActionPerformed

    private void MnSudahActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_MnSudahActionPerformed
        if (TNoRw.getText().trim().equals("")) {
            Valid.textKosong(TNoRw, "No.Rawat");
        } else {
            if (Sequel.cariInteger("select count(no_rawat) from kamar_inap where no_rawat=?", TNoRw.getText()) > 0) {
                JOptionPane.showMessageDialog(null, "Maaf, Pasien sudah masuk Kamar Inap. Gunakan billing Ranap..!!!");
            } else {
                Valid.editTable(tabMode, "reg_periksa", "no_rawat", TNoRw, "stts='Sudah'");
                if (tabMode.getRowCount() != 0) {
                    tampil();
                }
            }

        }
    }//GEN-LAST:event_MnSudahActionPerformed

    private void MnBelumActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_MnBelumActionPerformed
        if (TNoRw.getText().trim().equals("")) {
            Valid.textKosong(TNoRw, "No.Rawat");
        } else {
            if (Sequel.cariInteger("select count(no_rawat) from kamar_inap where no_rawat=?", TNoRw.getText()) > 0) {
                JOptionPane.showMessageDialog(null, "Maaf, Pasien sudah masuk Kamar Inap. Gunakan billing Ranap..!!!");
            } else {
                Valid.editTable(tabMode, "reg_periksa", "no_rawat", TNoRw, "stts='Belum'");
                if (tabMode.getRowCount() != 0) {
                    tampil();
                }
            }

        }
    }//GEN-LAST:event_MnBelumActionPerformed

    private void MnBayarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_MnBayarActionPerformed
        if (TNoRw.getText().trim().equals("")) {
            Valid.textKosong(TNoRw, "No.Rawat");
        } else {
            if (Sequel.cariInteger("select count(no_rawat) from kamar_inap where no_rawat=?", TNoRw.getText()) > 0) {
                JOptionPane.showMessageDialog(null, "Maaf, Pasien sudah masuk Kamar Inap. Gunakan billing Ranap..!!!");
            } else {
                Valid.editTable(tabMode, "reg_periksa", "no_rawat", TNoRw, "stts='Bayar'");
                if (tabMode.getRowCount() != 0) {
                    tampil();
                }
            }

        }
    }//GEN-LAST:event_MnBayarActionPerformed

    private void MnBatalActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_MnBatalActionPerformed
        if (TNoRw.getText().trim().equals("")) {
            Valid.textKosong(TNoRw, "No.Rawat");
        } else {
            if (Sequel.cariInteger("select count(no_rawat) from kamar_inap where no_rawat=?", TNoRw.getText()) > 0) {
                JOptionPane.showMessageDialog(null, "Maaf, Pasien sudah masuk Kamar Inap. Gunakan billing Ranap..!!!");
            } else {
                Valid.editTable(tabMode, "reg_periksa", "no_rawat", TNoRw, "stts='Batal'");
                if (tabMode.getRowCount() != 0) {
                    tampil();
                }
            }
        }
    }//GEN-LAST:event_MnBatalActionPerformed

    private void MnDirujukActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_MnDirujukActionPerformed
        if (TNoRw.getText().trim().equals("")) {
            Valid.textKosong(TNoRw, "No.Rawat");
        } else {
            if (Sequel.cariInteger("select count(no_rawat) from kamar_inap where no_rawat=?", TNoRw.getText()) > 0) {
                JOptionPane.showMessageDialog(null, "Maaf, Pasien sudah masuk Kamar Inap. Gunakan billing Ranap..!!!");
            } else {
                Valid.editTable(tabMode, "reg_periksa", "no_rawat", TNoRw, "stts='Dirujuk'");
                MnRujukActionPerformed(evt);
                if (tabMode.getRowCount() != 0) {
                    tampil();
                }
            }

        }
    }//GEN-LAST:event_MnDirujukActionPerformed

    private void MnDIrawatActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_MnDIrawatActionPerformed
        if (TNoRw.getText().trim().equals("")) {
            Valid.textKosong(TNoRw, "No.Rawat");
        } else {
            if (Sequel.cariInteger("select count(no_rawat) from kamar_inap where no_rawat=?", TNoRw.getText()) > 0) {
                JOptionPane.showMessageDialog(null, "Maaf, Pasien sudah masuk Kamar Inap. Gunakan billing Ranap..!!!");
            } else {
                Valid.editTable(tabMode, "reg_periksa", "no_rawat", TNoRw, "stts='Dirawat'");
                MnKamarInapActionPerformed(evt);
                if (tabMode.getRowCount() != 0) {
                    tampil();
                }
            }

        }
    }//GEN-LAST:event_MnDIrawatActionPerformed

    private void MnMeninggalActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_MnMeninggalActionPerformed
        if (TNoRw.getText().trim().equals("")) {
            Valid.textKosong(TNoRw, "No.Rawat");
        } else {
            if (Sequel.cariInteger("select count(no_rawat) from kamar_inap where no_rawat=?", TNoRw.getText()) > 0) {
                JOptionPane.showMessageDialog(null, "Maaf, Pasien sudah masuk Kamar Inap. Gunakan billing Ranap..!!!");
            } else {
                Valid.editTable(tabMode, "reg_periksa", "no_rawat", TNoRw, "stts='Meninggal'");
                DlgPasienMati dlgPasienMati = new DlgPasienMati(null, false);
                dlgPasienMati.setSize(internalFrame1.getWidth() - 40, internalFrame1.getHeight() - 40);
                dlgPasienMati.setLocationRelativeTo(internalFrame1);
                dlgPasienMati.emptTeks();
                dlgPasienMati.setNoRm(TNoID.getText());
                dlgPasienMati.isCek();
                dlgPasienMati.setVisible(true);
                if (tabMode.getRowCount() != 0) {
                    tampil();
                }
            }

        }
    }//GEN-LAST:event_MnMeninggalActionPerformed

    private void cmbTindakanKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_cmbTindakanKeyPressed
        // TODO add your handling code here:
    }//GEN-LAST:event_cmbTindakanKeyPressed

    private void cmbTraumaKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_cmbTraumaKeyPressed
        // TODO add your handling code here:
    }//GEN-LAST:event_cmbTraumaKeyPressed

    private void ChkDtgSendiriActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_ChkDtgSendiriActionPerformed
        if (ChkDtgSendiri.isSelected() == true) {
            KataDatang.setText("Ya");
            ChkDtgSendiri.setText("Pasien DATANG SENDIRI");
        } else if (ChkDtgSendiri.isSelected() == false) {
            KataDatang.setText("Tidak");
            ChkDtgSendiri.setText("Pasien TIDAK Datang Sendiri");
        }
    }//GEN-LAST:event_ChkDtgSendiriActionPerformed

    private void MnLaporanKunjunganIGDActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_MnLaporanKunjunganIGDActionPerformed
        if (tabMode.getRowCount() == 0) {
            JOptionPane.showMessageDialog(null, "Maaf, tabel masih kosong...!!!!");
            DTPCari1.requestFocus();
        } else if (tabMode.getRowCount() > 0) {
            this.setCursor(Cursor.getPredefinedCursor(Cursor.WAIT_CURSOR));
            Map<String, Object> param = new HashMap<>();
            param.put("namars", var.getnamars());
            param.put("alamatrs", var.getalamatrs());
            param.put("kotars", var.getkabupatenrs());
            param.put("propinsirs", var.getpropinsirs());
            param.put("kontakrs", var.getkontakrs());
            param.put("emailrs", var.getemailrs());
            param.put("logo", Sequel.cariGambar("select logo from setting"));
            param.put("periode", DTPCari1.getSelectedItem() + " s.d " + DTPCari2.getSelectedItem());
            Valid.MyReport("rptLapKunjunganIGD.jrxml", "report", "::[ Laporan Daftar Kunjungan IGD ]::",
                    " SELECT rp.no_rkm_medis no_rm, p.nm_pasien, "
                    + "CONCAT(IF(p.jk='L','LK','PR'),' (',rp.umurdaftar,' ',rp.sttsumur,')') umur, "
                    + "CONCAT(p.alamat,', ',kl.nm_kel,', ',kc.nm_kec,', ',kb.nm_kab) alamat, "
                    + "p.pekerjaan, rp.stts_daftar kunjungan, pj.png_jawab cara_bayar, "
                    + "IFNULL(di.non_trauma,'-') non_trauma, IFNULL(di.trauma,'-') trauma, "
                    + "IFNULL(di.datang_sendiri,'-') datang_sendiri, IFNULL(rm.perujuk,'-') cara_masuk, "
                    + "IFNULL(di.tindakan_lanjut,'-') tindakan_lanjut, IFNULL(IFNULL(pr.diagnosa,prp.diagnosa),'-') diagnosa_igd, "
                    + "d.nm_dokter, IFNULL(di.ket_igd,'-') keterangan "
                    + "FROM reg_periksa rp INNER JOIN pasien p ON rp.no_rkm_medis = p.no_rkm_medis "
                    + "INNER JOIN kelurahan kl ON p.kd_kel = kl.kd_kel INNER JOIN kecamatan kc ON p.kd_kec = kc.kd_kec "
                    + "INNER JOIN kabupaten kb ON p.kd_kab = kb.kd_kab INNER JOIN penjab pj ON rp.kd_pj = pj.kd_pj "
                    + "INNER JOIN dokter d ON rp.kd_dokter = d.kd_dokter LEFT JOIN rujuk_masuk rm ON rm.no_rawat = rp.no_rawat "
                    + "LEFT JOIN pemeriksaan_ralan_petugas prp ON prp.no_rawat = rp.no_rawat "
                    + "LEFT JOIN pemeriksaan_ralan pr ON pr.no_rawat = rp.no_rawat "
                    + "LEFT JOIN data_igd di ON rp.no_rawat = di.no_rawat "
                    + "WHERE rp.kd_poli='igdk' AND rp.tgl_registrasi BETWEEN '" + Valid.SetTgl(DTPCari1.getSelectedItem() + "") + "' AND '" + Valid.SetTgl(DTPCari2.getSelectedItem() + "") + "' "
                    + "ORDER BY rp.tgl_registrasi", param);
            this.setCursor(Cursor.getDefaultCursor());
        }
    }//GEN-LAST:event_MnLaporanKunjunganIGDActionPerformed

    private void MnLaporanStatistikIGDActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_MnLaporanStatistikIGDActionPerformed
        if (tabMode.getRowCount() == 0) {
            JOptionPane.showMessageDialog(null, "Maaf, tabel masih kosong...!!!!");
            DTPCari1.requestFocus();
        } else if (tabMode.getRowCount() > 0) {
            this.setCursor(Cursor.getPredefinedCursor(Cursor.WAIT_CURSOR));
            Map<String, Object> param = new HashMap<>();
            param.put("namars", var.getnamars());
            param.put("alamatrs", var.getalamatrs());
            param.put("kotars", var.getkabupatenrs());
            param.put("propinsirs", var.getpropinsirs());
            param.put("kontakrs", var.getkontakrs());
            param.put("emailrs", var.getemailrs());
            param.put("logo", Sequel.cariGambar("select logo from setting"));
            param.put("periode", DTPCari1.getSelectedItem() + " s.d " + DTPCari2.getSelectedItem());
            Valid.MyReport("rptLapStatistikKunIGD.jrxml", "report", "::[ Laporan Statistik Kunjungan IGD ]::",
                    " SELECT CONCAT(rp.no_rkm_medis,' - ',p.nm_pasien) AS pasien, convert(IF(rp.stts_daftar='Baru','1','0'),int) AS Baru, "
                    + "convert(IF(rp.stts_daftar='Lama','1','0'),int) as Lama, convert(IF(pj.png_jawab LIKE '%umum%','1','0'),int) AS UMUM, "
                    + "convert(IF(pj.png_jawab in ('JAMKESMAS (PBI)','JAMPERSAL (PBI)','BPJS KESEHATAN','TNI/POLRI','Program Keluarga Harapan (PKH)'),'1','0'),int) AS BPJS, "
                    + "convert(IF(pj.png_jawab NOT in ('UMUM','JAMKESMAS (PBI)','JAMPERSAL (PBI)','BPJS KESEHATAN','TNI/POLRI','Program Keluarga Harapan (PKH)'),'1','0'),int) AS KrjSM_Lain2, "
                    + "convert(IF(di.non_trauma='P. DALAM','1','0'),int) as PD, convert(IF(di.non_trauma='BEDAH','1','0'),int) as Bedah, "
                    + "convert(IF(di.non_trauma='ANAK','1','0'),int) as Anak, convert(IF(di.non_trauma='PARU','1','0'),int) as Paru, "
                    + "convert(IF(di.non_trauma='SYARAF','1','0'),int) as Syaraf, convert(IF(di.non_trauma='JANTUNG','1','0'),int) as Jantung, "
                    + "convert(IF(di.non_trauma='THT','1','0'),int) as THT, convert(IF(di.non_trauma='MATA','1','0'),int) as Mata, "
                    + "convert(IF(di.non_trauma='KEBIDANAN','1','0'),int) as Kebidanan, convert(IF(di.non_trauma='KULIT & KELAMIN','1','0'),int) as Kulkel, "
                    + "convert(IF(di.non_trauma='-','1','0'),int) as NTinvalid, convert(IF(di.trauma like '%KLL%','1','0'),int) AS KLL, "
                    + "convert(IF(di.trauma like '%KERJA%','1','0'),int) AS K_KERJA, convert(IF(di.trauma like '%RT%','1','0'),int) AS K_RT, "
                    + "convert(IF(di.trauma like '%LAIN%','1','0'),int) AS LAIN, convert(IF(di.trauma='-','1','0'),int) AS trauma_invalid, "
                    + "convert(IF(di.datang_sendiri='Tidak','1','0'),int) AS stts_dtg_invalid, convert(IF(di.datang_sendiri='Ya','1','0'),int) AS stts_dtg_sendiri, "
                    + "convert(IF(rm.perujuk in ('','-'),'1','0'),int) AS rujukan_invalid, convert(IF(rm.perujuk not in ('','-'),'1','0'),int) AS rujukan_masuk, "
                    + "convert(IF(di.tindakan_lanjut='PRAKTEK DOKTER','1','0'),int) AS praktk_doktr, convert(IF(di.tindakan_lanjut='RAWAT JALAN','1','0'),int) AS r_jalan, "
                    + "convert(IF(di.tindakan_lanjut='RAWAT INAP','1','0'),int) AS r_inap, convert(IF(di.tindakan_lanjut='DI RUJUK','1','0'),int) AS dirujuk, "
                    + "convert(IF(di.tindakan_lanjut like '%MENINGGAL%','1','0'),int) AS meninggl, convert(IF(di.tindakan_lanjut='D.O.A','1','0'),int) AS doa, "
                    + "convert(IF(di.tindakan_lanjut like '%APS%','1','0'),int) AS aps FROM reg_periksa rp "
                    + "INNER JOIN pasien p ON rp.no_rkm_medis = p.no_rkm_medis INNER JOIN penjab pj ON rp.kd_pj = pj.kd_pj "
                    + "LEFT JOIN rujuk_masuk rm ON rm.no_rawat = rp.no_rawat LEFT JOIN data_igd di ON rp.no_rawat = di.no_rawat "
                    + "LEFT JOIN ralan_aps ra ON ra.no_rawat=rp.no_rawat WHERE rp.kd_poli='igdk' AND "
                    + "rp.tgl_registrasi BETWEEN '" + Valid.SetTgl(DTPCari1.getSelectedItem() + "") + "' AND '" + Valid.SetTgl(DTPCari2.getSelectedItem() + "") + "' "
                    + "ORDER BY rp.tgl_registrasi", param);
            this.setCursor(Cursor.getDefaultCursor());
        }
    }//GEN-LAST:event_MnLaporanStatistikIGDActionPerformed

    private void MnSEPJamkesdaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_MnSEPJamkesdaActionPerformed
        if (nmpnj.getText().equals("")) {
            emptTeks();
            JOptionPane.showMessageDialog(null, "Silakan klik dulu nama pasiennya pada tabel..!!");
            tbregistrasiIGD.requestFocus();
        } else if (nmpnj.getText().equals("JAMKESDA (PBI)")) {
            DlgJamkesda.setSize(490, 125);
            DlgJamkesda.setLocationRelativeTo(internalFrame1);
            DlgJamkesda.setVisible(true);
        } else {
            emptTeks();
            JOptionPane.showMessageDialog(null, "Jenis cara bayar pasien tersebut tidak sesuai..!!");
            tbregistrasiIGD.requestFocus();
        }
    }//GEN-LAST:event_MnSEPJamkesdaActionPerformed

    private void BtnCloseIn1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_BtnCloseIn1ActionPerformed
        DlgJamkesda.dispose();
        emptTeks();
    }//GEN-LAST:event_BtnCloseIn1ActionPerformed

    private void BtnCloseIn1KeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_BtnCloseIn1KeyPressed
        // TODO add your handling code here:
    }//GEN-LAST:event_BtnCloseIn1KeyPressed

    private void BtnSimpan1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_BtnSimpan1ActionPerformed
        cekjamkesda = 0;
        cekjamkesda = Sequel.cariInteger("SELECT Count(1) cek FROM bridging_jamkesda bj INNER JOIN reg_periksa rp ON bj.no_rawat = rp.no_rawat "
                + "INNER JOIN penjab pj ON rp.kd_pj = pj.kd_pj WHERE bj.no_rawat='" + TNoRw.getText() + "' AND bj.jns_rawat='Jalan IGD' AND pj.png_jawab LIKE '%jamkesda%'");

        if (noSrt.getText().trim().equals("")) {
            Valid.textKosong(noSrt, "No. Surat Keterangan Peserta Jamkesda");
        } else if (cekjamkesda >=1) {
            JOptionPane.showMessageDialog(null, "Data tidak tersimpan, SEP sudah pernah dibikinkan dengan no. rawat yang sama..!!");
            noSrt.requestFocus();
        } else {
            Sequel.menyimpan("bridging_jamkesda", "'" + TNoRw.getText() + "','" + noSrt.getText() + "','Jalan IGD',"
                    + "'" + tglDaftar + "',"
                    + "'" + Valid.SetTgl(TglSurat.getSelectedItem() + "") + "',"
                    + "'" + TNoRw.getText() + "RJD' ", "Data SEP Jamkesda");
            JOptionPane.showMessageDialog(null, "Data berhasil disimpan, SEP siap dicetak......");
            BtnCtkJkd.requestFocus();
        }
    }//GEN-LAST:event_BtnSimpan1ActionPerformed

    private void BtnSimpan1KeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_BtnSimpan1KeyPressed
        // TODO add your handling code here:
    }//GEN-LAST:event_BtnSimpan1KeyPressed

    private void noSrtKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_noSrtKeyPressed

    }//GEN-LAST:event_noSrtKeyPressed

    private void TglSuratItemStateChanged(java.awt.event.ItemEvent evt) {//GEN-FIRST:event_TglSuratItemStateChanged
        // TODO add your handling code here:
    }//GEN-LAST:event_TglSuratItemStateChanged

    private void TglSuratKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_TglSuratKeyPressed
        // TODO add your handling code here:
    }//GEN-LAST:event_TglSuratKeyPressed

    private void BtnGantijkdActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_BtnGantijkdActionPerformed
        if (noSrt.getText().trim().equals("")) {
            Valid.textKosong(noSrt, "No. Surat Keterangan Peserta Jamkesda");
        } else {
            Sequel.mengedit("bridging_jamkesda", "no_sep='" + sepJkd.getText() + "'", "no_surat='" + noSrt.getText() + "', tgl_surat='" + Valid.SetTgl(TglSurat.getSelectedItem() + "") + "' ");
            JOptionPane.showMessageDialog(null, "Data berhasil diubah, SEP siap dicetak......");
            BtnCtkJkd.requestFocus();
        }
    }//GEN-LAST:event_BtnGantijkdActionPerformed

    private void BtnGantijkdKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_BtnGantijkdKeyPressed
        // TODO add your handling code here:
    }//GEN-LAST:event_BtnGantijkdKeyPressed

    private void BtnCtkJkdActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_BtnCtkJkdActionPerformed
        sepJkd.setText(Sequel.cariIsi("SELECT no_sep FROM bridging_jamkesda WHERE no_rawat='" + TNoRw.getText() + "' AND jns_rawat='Jalan IGD' "));

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
            Valid.MyReport("rptSEPJalan.jrxml", "report", "::[ Cetak SEP Pasien JAMKESDA Rawat Jalan IGD ]::",
                    " SELECT pasien.no_rkm_medis, bridging_jamkesda.no_sep, reg_periksa.tgl_registrasi, pasien.no_ktp, "
                    + " pasien.nm_pasien, pasien.tgl_lahir, IF(pasien.jk='L','Laki-laki','Perempuan') as jk, "
                    + " poliklinik.nm_poli, reg_periksa.no_rawat, bridging_jamkesda.no_surat, bridging_jamkesda.jns_rawat, "
                    + " bridging_jamkesda.tgl_surat, bridging_jamkesda.tgl_rawat, penjab.png_jawab "
                    + " FROM reg_periksa INNER JOIN pasien ON reg_periksa.no_rkm_medis = pasien.no_rkm_medis "
                    + " INNER JOIN poliklinik ON reg_periksa.kd_poli = poliklinik.kd_poli "
                    + " INNER JOIN bridging_jamkesda ON bridging_jamkesda.no_rawat = reg_periksa.no_rawat "
                    + " INNER JOIN penjab ON reg_periksa.kd_pj = penjab.kd_pj "
                    + " WHERE bridging_jamkesda.no_sep='" + sepJkd.getText() + "' AND bridging_jamkesda.jns_rawat='Jalan IGD' AND penjab.png_jawab LIKE '%jamkesda%'", param);
            this.setCursor(Cursor.getDefaultCursor());
            DlgJamkesda.dispose();
            emptTeks();
        }
    }//GEN-LAST:event_BtnCtkJkdActionPerformed

    private void BtnCtkJkdKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_BtnCtkJkdKeyPressed
        // TODO add your handling code here:
    }//GEN-LAST:event_BtnCtkJkdKeyPressed

    private void cmbNonTraumaKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_cmbNonTraumaKeyPressed
        // TODO add your handling code here:
    }//GEN-LAST:event_cmbNonTraumaKeyPressed

    private void cmbKetKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_cmbKetKeyPressed
        // TODO add your handling code here:
    }//GEN-LAST:event_cmbKetKeyPressed

    private void cmbTindakanActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_cmbTindakanActionPerformed
        if (cmbTindakan.getSelectedItem().equals("RAWAT INAP")) {
            cmbKet.setSelectedItem("DIRAWAT");
            alasanAPS.setText("");
            ketAPS.setText("");
        } else if (cmbTindakan.getSelectedItem().equals("APS")) {
            cmbKet.setSelectedItem("PULANG");
        } else if (!cmbTindakan.getSelectedItem().equals("APS")) {
            alasanAPS.setText("");
            ketAPS.setText("");
        } else if (!cmbTindakan.getSelectedItem().equals("RAWAT INAP") || (!cmbTindakan.getSelectedItem().equals("APS"))) {
            cmbKet.setSelectedItem("-");
            alasanAPS.setText("");
            ketAPS.setText("");
        }
    }//GEN-LAST:event_cmbTindakanActionPerformed

    private void BtnCloseIn2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_BtnCloseIn2ActionPerformed
        DlgMati.dispose();
        emptTeks();
    }//GEN-LAST:event_BtnCloseIn2ActionPerformed

    private void BtnCloseIn2KeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_BtnCloseIn2KeyPressed
        // TODO add your handling code here:
    }//GEN-LAST:event_BtnCloseIn2KeyPressed

    private void ketKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_ketKeyPressed
        // TODO add your handling code here:
    }//GEN-LAST:event_ketKeyPressed

    private void TglMatiItemStateChanged(java.awt.event.ItemEvent evt) {//GEN-FIRST:event_TglMatiItemStateChanged
        // TODO add your handling code here:
    }//GEN-LAST:event_TglMatiItemStateChanged

    private void TglMatiKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_TglMatiKeyPressed
        // TODO add your handling code here:
    }//GEN-LAST:event_TglMatiKeyPressed

    private void BtnGantiMatiActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_BtnGantiMatiActionPerformed
        if (cekTglMati.isSelected() == true) {
            if (ket.getText().trim().equals("")) {
                Valid.textKosong(ket, "keterangan pasien meninggal");
            } else {
                Sequel.mengedit("pasien_mati", "no_rkm_medis='" + TNoRM.getText() + "'", "tanggal='" + Valid.SetTgl(TglMati.getSelectedItem() + "") + "', jam='" + cmbJam.getSelectedItem() + ":" + cmbMnt.getSelectedItem() + ":" + cmbDtk.getSelectedItem() + "',"
                        + "keterangan='" + ket.getText() + "', unit_asal='IGD' ");
                JOptionPane.showMessageDialog(null, "Data pasien meninggal berhasil diubah....");
                jam.setText("");
                tgl.setText("");
                BtnCloseIn2.requestFocus();
            }
        } else if (cekTglMati.isSelected() == false) {
            JOptionPane.showMessageDialog(null, "Silakan conteng dulu Tgl. Meninggal untuk melakukan proses perubahan data...!!!");
            cekTglMati.requestFocus();
        }
    }//GEN-LAST:event_BtnGantiMatiActionPerformed

    private void BtnGantiMatiKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_BtnGantiMatiKeyPressed
        // TODO add your handling code here:
    }//GEN-LAST:event_BtnGantiMatiKeyPressed

    private void cmbJamKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_cmbJamKeyPressed
        //      Valid.pindah(evt,TglMati,cmbMnt);
    }//GEN-LAST:event_cmbJamKeyPressed

    private void cmbMntKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_cmbMntKeyPressed
        //      Valid.pindah(evt,cmbJam,cmbDtk);
    }//GEN-LAST:event_cmbMntKeyPressed

    private void cmbDtkKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_cmbDtkKeyPressed
        //     Valid.pindah(evt,cmbMnt,"Rumah Sakit");
    }//GEN-LAST:event_cmbDtkKeyPressed

    private void MnMatiActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_MnMatiActionPerformed
        noRawatDataIGD.setText(Sequel.cariIsi("select no_rawat from data_igd where no_rawat='" + TNoRw.getText() + "' and "
                + "(tindakan_lanjut like '%meni%' OR tindakan_lanjut LIKE '%d.o.a%')"));

        if (!noRawatDataIGD.getText().equals("")) {
            TglMati.setVisible(false);
            cmbJam.setVisible(false);
            cmbMnt.setVisible(false);
            cmbDtk.setVisible(false);
            jLabel11.setVisible(false);

            jam.setVisible(true);
            tgl.setVisible(true);

            bukaMenuMati();
        } else {

            if (tabMode.getRowCount() == 0) {
                JOptionPane.showMessageDialog(null, "Maaf, tabel masih kosong...!!!!");
                TNoReg.requestFocus();
            } else if (TNoID.getText().equals("") || (TPasien.getText().trim().equals(""))) {
                DlgMati.setVisible(false);
                JOptionPane.showMessageDialog(null, "Maaf, Silahkan anda pilih dulu dengan mengklik data pada tabel...!!!");
                tbregistrasiIGD.requestFocus();
            } else {
                JOptionPane.showMessageDialog(null, "Silahkan anda ganti dulu jenis tindakan lanjut Meninggal di IGD atau D.O.A ...!!!");
                cmbTindakan.requestFocus();
            }
        }
    }//GEN-LAST:event_MnMatiActionPerformed

    private void BtnHapusMatiActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_BtnHapusMatiActionPerformed
        if (!rmMati.getText().equals("")) {
            Sequel.meghapus("pasien_mati", "no_rkm_medis", TNoRM.getText());
            DlgMati.dispose();
            emptTeks();
        } else {
            JOptionPane.showMessageDialog(null, "Tidak ada data yang terhapus untuk pasien tersebut di laporan rekap pasien meninggal...!!!");
            ket.requestFocus();
        }
    }//GEN-LAST:event_BtnHapusMatiActionPerformed

    private void BtnHapusMatiKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_BtnHapusMatiKeyPressed
        // TODO add your handling code here:
    }//GEN-LAST:event_BtnHapusMatiKeyPressed

    private void jamKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_jamKeyPressed
        // TODO add your handling code here:
    }//GEN-LAST:event_jamKeyPressed

    private void tglKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_tglKeyPressed
        // TODO add your handling code here:
    }//GEN-LAST:event_tglKeyPressed

    private void BtnCetakMatiActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_BtnCetakMatiActionPerformed
        rmMati.setText(Sequel.cariIsi("select no_rkm_medis from pasien_mati where no_rkm_medis='" + TNoRM.getText() + "' "));

        if (!rmMati.getText().equals("")) {
            Map<String, Object> param = new HashMap<>();
            param.put("namars", var.getnamars());
            param.put("alamatrs", var.getalamatrs());
            param.put("kotars", var.getkabupatenrs());
            param.put("propinsirs", var.getpropinsirs());
            param.put("kontakrs", var.getkontakrs());
            param.put("emailrs", var.getemailrs());
            param.put("logo", Sequel.cariGambar("select logo from setting"));
            Valid.MyReport("rptSuratKematian.jrxml", "report", "::[ Surat Keterangan Kematian ]::",
                    " SELECT pasien_mati.tanggal, pasien_mati.jam, pasien_mati.no_rkm_medis, "
                    + " pasien.nm_pasien, TIMESTAMPDIFF(Year,pasien.tgl_lahir,CURDATE()) as umur_thn, "
                    + " TIMESTAMPDIFF(MONTH,pasien.tgl_lahir,CURDATE()) % 12 as umur_bln, "
                    + " FLOOR( TIMESTAMPDIFF( DAY, pasien.tgl_lahir, CURDATE() ) % 30.4375 ) as umur_hari, "
                    + " pasien.alamat, pasien.no_ktp, "
                    + " pasien.no_tlp, if(jk = 'L','Laki-laki','Perempuan') AS Kelamin, "
                    + " pasien.tmp_lahir, DATE_FORMAT(pasien.tgl_lahir,'%d %M %Y') AS tgl_lahir, "
                    + " pasien.gol_darah, pasien.stts_nikah, pasien.agama, pasien_mati.keterangan, "
                    + " kelurahan.nm_kel, kecamatan.nm_kec, kabupaten.nm_kab "
                    + " FROM pasien_mati, pasien "
                    + " INNER JOIN kabupaten ON kabupaten.kd_kab = pasien.kd_kab "
                    + " INNER JOIN kecamatan ON kecamatan.kd_kec = pasien.kd_kec "
                    + " INNER JOIN kelurahan ON kelurahan.kd_kel = pasien.kd_kel "
                    + " WHERE pasien_mati.no_rkm_medis = pasien.no_rkm_medis "
                    + " and pasien_mati.no_rkm_medis='" + rmMati.getText() + "' ", param);
            this.setCursor(Cursor.getDefaultCursor());
            emptTeks();
            DlgMati.dispose();
        }
    }//GEN-LAST:event_BtnCetakMatiActionPerformed

    private void BtnCetakMatiKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_BtnCetakMatiKeyPressed
        // TODO add your handling code here:
    }//GEN-LAST:event_BtnCetakMatiKeyPressed

    private void ScrollKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_ScrollKeyPressed
        // TODO add your handling code here:
    }//GEN-LAST:event_ScrollKeyPressed

    private void cekTglMatiActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_cekTglMatiActionPerformed
        if (cekTglMati.isSelected() == true) {
            TglMati.setVisible(true);
            cmbJam.setVisible(true);
            cmbMnt.setVisible(true);
            cmbDtk.setVisible(true);
            jLabel11.setVisible(true);

            jam.setVisible(false);
            tgl.setVisible(false);
        } else if (cekTglMati.isSelected() == false) {
            TglMati.setVisible(false);
            cmbJam.setVisible(false);
            cmbMnt.setVisible(false);
            cmbDtk.setVisible(false);
            jLabel11.setVisible(false);

            jam.setVisible(true);
            tgl.setVisible(true);
        }
    }//GEN-LAST:event_cekTglMatiActionPerformed

    private void MnSJPActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_MnSJPActionPerformed
        if (tabMode.getRowCount() == 0) {
            JOptionPane.showMessageDialog(null, "Maaf, tabel masih kosong...!!!!");
            TNoReg.requestFocus();
        } else if (TPasien.getText().trim().equals("")) {
            JOptionPane.showMessageDialog(null, "Maaf, Silahkan anda pilih dulu dengan mengklik data pada tabel...!!!");
            tbregistrasiIGD.requestFocus();
        } else if (!kdpnj.getText().equals("A01")) {
            JOptionPane.showMessageDialog(null, "Pasien itu bukan pasien Inhealth...!!!");
            tbregistrasiIGD.requestFocus();
        } else if (kdpnj.getText().equals("A01")) {
            this.setCursor(Cursor.getPredefinedCursor(Cursor.WAIT_CURSOR));
            var.setform("DlgIGD");
            InhealthDataSJP dlgki = new InhealthDataSJP(null, false);
            dlgki.setSize(internalFrame1.getWidth(), internalFrame1.getHeight());
            dlgki.setLocationRelativeTo(internalFrame1);
            dlgki.isCek();
            dlgki.setNoRm(TNoRw.getText(), tglDaftar, "3 RJTL RAWAT JALAN TINGKAT LANJUT", "IGDK", "IGD");
            dlgki.tampil();
            dlgki.setVisible(true);
            this.setCursor(Cursor.getDefaultCursor());
        }
    }//GEN-LAST:event_MnSJPActionPerformed

    private void MnSKDPBPJSActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_MnSKDPBPJSActionPerformed
        if(tabMode.getRowCount()==0){
            JOptionPane.showMessageDialog(null,"Maaf, tabel masih kosong...!!!!");
            TCari.requestFocus();
        }else{
            if(Sequel.cariInteger("select count(no_rawat) from kamar_inap where no_rawat=?",TNoRw.getText())>0){
                JOptionPane.showMessageDialog(null,"Maaf, Pasien sudah masuk Kamar Inap. Gunakan billing Ranap..!!!");
            }else {
                DlgSKDPBPJS form=new DlgSKDPBPJS(null,false);
                form.isCek();
                form.setSize(internalFrame1.getWidth()-40,internalFrame1.getHeight()-40);
                form.setLocationRelativeTo(internalFrame1);
                form.emptTeks();
                form.setNoRm(TNoRM.getText(),TPasien.getText());
                form.setVisible(true);
                form.fokus();
            }
        }
    }//GEN-LAST:event_MnSKDPBPJSActionPerformed

    private void MnPermintaanLabActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_MnPermintaanLabActionPerformed
        if(tabMode.getRowCount()==0){
            JOptionPane.showMessageDialog(null,"Maaf, tabel masih kosong...!!!!");
            TNoReg.requestFocus();
        }else if(TPasien.getText().trim().equals("")){
            JOptionPane.showMessageDialog(null,"Maaf, Silahkan anda pilih dulu dengan mengklik data pada tabel...!!!");
            tbregistrasiIGD.requestFocus();
        }else{
            if(Sequel.cariInteger("select count(no_rawat) from kamar_inap where no_rawat=?",TNoRw.getText())>0){
                JOptionPane.showMessageDialog(null,"Maaf, Pasien sudah masuk Kamar Inap. Gunakan billing Ranap..!!!");
            }else {
                this.setCursor(Cursor.getPredefinedCursor(Cursor.WAIT_CURSOR));
                DlgPermintaanLaboratorium dlgro=new DlgPermintaanLaboratorium(null,false);
                dlgro.setSize(internalFrame1.getWidth()-40,internalFrame1.getHeight()-40);
                dlgro.setLocationRelativeTo(internalFrame1);
                dlgro.emptTeks();
                dlgro.isCek();
                dlgro.setNoRm(TNoRw.getText(),"Ralan");
                dlgro.setVisible(true);
                this.setCursor(Cursor.getDefaultCursor());
            }
        }
    }//GEN-LAST:event_MnPermintaanLabActionPerformed

    private void MnPermintaanRadiologiActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_MnPermintaanRadiologiActionPerformed
        if(tabMode.getRowCount()==0){
            JOptionPane.showMessageDialog(null,"Maaf, tabel masih kosong...!!!!");
            TNoReg.requestFocus();
        }else if(TPasien.getText().trim().equals("")){
            JOptionPane.showMessageDialog(null,"Maaf, Silahkan anda pilih dulu dengan mengklik data pada tabel...!!!");
            tbregistrasiIGD.requestFocus();
        }else{
            if(Sequel.cariInteger("select count(no_rawat) from kamar_inap where no_rawat=?",TNoRw.getText())>0){
                JOptionPane.showMessageDialog(null,"Maaf, Pasien sudah masuk Kamar Inap. Gunakan billing Ranap..!!!");
            }else {
                this.setCursor(Cursor.getPredefinedCursor(Cursor.WAIT_CURSOR));
                DlgPermintaanRadiologi dlgro=new DlgPermintaanRadiologi(null,false);
                dlgro.setSize(internalFrame1.getWidth()-40,internalFrame1.getHeight()-40);
                dlgro.setLocationRelativeTo(internalFrame1);
                dlgro.emptTeks();
                dlgro.isCek();
                dlgro.setNoRm(TNoRw.getText(),"Ralan");
                dlgro.setVisible(true);
                this.setCursor(Cursor.getDefaultCursor());
            }
        }
    }//GEN-LAST:event_MnPermintaanRadiologiActionPerformed

    private void JnsnoIDItemStateChanged(java.awt.event.ItemEvent evt) {//GEN-FIRST:event_JnsnoIDItemStateChanged
        isPas();
        JnsnoID.requestFocus();
    }//GEN-LAST:event_JnsnoIDItemStateChanged

    private void JnsnoIDMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_JnsnoIDMouseClicked
        JnsnoID.setEditable(false);
    }//GEN-LAST:event_JnsnoIDMouseClicked

    private void JnsnoIDKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_JnsnoIDKeyPressed

    }//GEN-LAST:event_JnsnoIDKeyPressed

    private void TNoRMKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_TNoRMKeyPressed
        // TODO add your handling code here:
    }//GEN-LAST:event_TNoRMKeyPressed

    private void MnBarcodeRM2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_MnBarcodeRM2ActionPerformed
        if (tabMode.getRowCount() == 0) {
            JOptionPane.showMessageDialog(null, "Maaf, data pasien sudah habis...!!!!");
            BtnBatal.requestFocus();
        } else if (TPasien.getText().trim().equals("")) {
            JOptionPane.showMessageDialog(null, "Maaf, Silahkan anda pilih dulu data pasien dengan mengklik data pada tabel...!!!");
            tbregistrasiIGD.requestFocus();
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
            Valid.MyReport("rptBarcodeRM111.jrxml", "report", "::[ Label Barcode Kertas KECIL ]::", "select pasien.no_rkm_medis, pasien.nm_pasien, pasien.no_ktp, pasien.jk, "
                    + "pasien.tmp_lahir, DATE_FORMAT(pasien.tgl_lahir,'%d/%m/%Y') as tgl_lahir, pasien.nm_ibu, concat(pasien.alamat,', ',kelurahan.nm_kel,', ',kecamatan.nm_kec,', ',kabupaten.nm_kab) as alamat, pasien.gol_darah, pasien.pekerjaan,"
                    + "pasien.stts_nikah,pasien.agama,pasien.tgl_daftar,pasien.no_tlp,pasien.umur,"
                    + "pasien.pnd, pasien.keluarga, pasien.namakeluarga,penjab.png_jawab,pasien.pekerjaanpj,"
                    + "concat(pasien.alamatpj,', ',pasien.kelurahanpj,', ',pasien.kecamatanpj,', ',pasien.kabupatenpj) as alamatpj from pasien "
                    + "inner join kelurahan inner join kecamatan inner join kabupaten "
                    + "inner join penjab on pasien.kd_pj=penjab.kd_pj and pasien.kd_kel=kelurahan.kd_kel "
                    + "and pasien.kd_kec=kecamatan.kd_kec and pasien.kd_kab=kabupaten.kd_kab where pasien.no_rkm_medis='" + TNoRM.getText() + "' ", param);
            this.setCursor(Cursor.getDefaultCursor());
        }
    }//GEN-LAST:event_MnBarcodeRM2ActionPerformed

    private void MnLabelRM1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_MnLabelRM1ActionPerformed
        if (tabMode.getRowCount() == 0) {
            JOptionPane.showMessageDialog(null, "Maaf, data pasien sudah habis...!!!!");
            DTPCari1.requestFocus();
        } else if (TPasien.getText().trim().equals("")) {
            JOptionPane.showMessageDialog(null, "Maaf, Silahkan anda pilih dulu data pasien dengan mengklik data pada tabel...!!!");
            tbregistrasiIGD.requestFocus();
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

    private void MnLabelRM2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_MnLabelRM2ActionPerformed
        if (tabMode.getRowCount() == 0) {
            JOptionPane.showMessageDialog(null, "Maaf, data pasien sudah habis...!!!!");
            DTPCari1.requestFocus();
        } else if (TPasien.getText().trim().equals("")) {
            JOptionPane.showMessageDialog(null, "Maaf, Silahkan anda pilih dulu data pasien dengan mengklik data pada tabel...!!!");
            tbregistrasiIGD.requestFocus();
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

    private void kode_rujukanyaKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_kode_rujukanyaKeyPressed
        // TODO add your handling code here:
    }//GEN-LAST:event_kode_rujukanyaKeyPressed

    private void MnRujukSisruteActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_MnRujukSisruteActionPerformed
        if (tabMode.getRowCount() == 0) {
            JOptionPane.showMessageDialog(null, "Maaf, tabel masih kosong...!!!!");
            TNoReg.requestFocus();
        } else if (TPasien.getText().trim().equals("")) {
            JOptionPane.showMessageDialog(null, "Maaf, Silahkan anda pilih dulu dengan menklik data pada tabel...!!!");
            tbregistrasiIGD.requestFocus();
        } else {
            if (var.getkode().equals("Admin Utama") || (var.getsisrute_rujukan_keluar())) {
                this.setCursor(Cursor.getPredefinedCursor(Cursor.WAIT_CURSOR));
                var.setform("DlgIGD");
                SisruteRujukanKeluar dlgki = new SisruteRujukanKeluar(null, false);
                dlgki.setSize(internalFrame1.getWidth() - 20, internalFrame1.getHeight() - 20);
                dlgki.setLocationRelativeTo(internalFrame1);
                dlgki.isCek();
                dlgki.setPasien(TNoRw.getText());
                dlgki.JenisRujukan.setSelectedItem(1);
                dlgki.setVisible(true);
                this.setCursor(Cursor.getDefaultCursor());
            } else {
                JOptionPane.showMessageDialog(null, "Hanya petugas yang memiliki hak akses saja yang bisa menggunakan fitur ini...!!!");
            }
        }
    }//GEN-LAST:event_MnRujukSisruteActionPerformed

    private void kdsukuKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_kdsukuKeyPressed
        // TODO add your handling code here:
    }//GEN-LAST:event_kdsukuKeyPressed

    private void kdbahasaKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_kdbahasaKeyPressed
        // TODO add your handling code here:
    }//GEN-LAST:event_kdbahasaKeyPressed

    private void BtnSukuActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_BtnSukuActionPerformed
        var.setform("DlgIGD");
        suku.isCek();
        suku.setSize(1105, 512);
        suku.setLocationRelativeTo(internalFrame1);
        suku.setVisible(true);
        suku.TCari.requestFocus();
    }//GEN-LAST:event_BtnSukuActionPerformed

    private void BtnSukuKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_BtnSukuKeyPressed
        if(evt.getKeyCode()==KeyEvent.VK_SPACE){
            BtnSukuActionPerformed(null);
        }else if(evt.getKeyCode()==KeyEvent.VK_PAGE_UP){
            BtnBahasa.requestFocus();
        }else if(evt.getKeyCode()==KeyEvent.VK_ENTER){
            BtnBahasa.requestFocus();
        }
    }//GEN-LAST:event_BtnSukuKeyPressed

    private void BtnBahasaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_BtnBahasaActionPerformed
        var.setform("DlgIGD");
        bahasa.isCek();
        bahasa.setSize(1105, 512);
        bahasa.setLocationRelativeTo(internalFrame1);
        bahasa.setVisible(true);
        bahasa.TCari.requestFocus();
    }//GEN-LAST:event_BtnBahasaActionPerformed

    private void BtnBahasaKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_BtnBahasaKeyPressed
        if(evt.getKeyCode()==KeyEvent.VK_SPACE){
            BtnBahasaActionPerformed(null);
        }else if(evt.getKeyCode()==KeyEvent.VK_PAGE_UP){
            BtnSuku.requestFocus();
        }else if(evt.getKeyCode()==KeyEvent.VK_ENTER){
            BtnSimpan.requestFocus();
        }
    }//GEN-LAST:event_BtnBahasaKeyPressed

    private void MnDataHAIsBtnPrintActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_MnDataHAIsBtnPrintActionPerformed
        if (tabMode.getRowCount() == 0) {
            JOptionPane.showMessageDialog(null, "Maaf, tabel masih kosong...!!!!");
            TCari.requestFocus();
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

    private void MnDataPonekBtnPrintActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_MnDataPonekBtnPrintActionPerformed
        Sequel.cariIsi("select jk from pasien where no_rkm_medis=? ", kelaminPersalinan, TNoRM.getText());
        Sequel.cariIsi("select no_rawat from data_ponek where no_rawat=? ", cekDataPersalinan, TNoRw.getText());
        
        if (tabMode.getRowCount() == 0) {
            JOptionPane.showMessageDialog(null, "Maaf, tabel masih kosong...!!!!");
            TCari.requestFocus();
        } else if (Integer.parseInt(umurDaftar.getText())<=10) {
            JOptionPane.showMessageDialog(null, "Data ponek bukan untuk pasien bayi atau anak...!!!!");
            tbregistrasiIGD.requestFocus();
        } else if (TNoRw.getText().equals("")) {
            JOptionPane.showMessageDialog(null, "Silakan klik salah satu nama pasien atau no. rekam medisnya dulu...!!!!");
            tbregistrasiIGD.requestFocus();
        } else if (!kelaminPersalinan.getText().equals("P")) {
            JOptionPane.showMessageDialog(null, "Hanya untuk pasien yang berjenis kelamin perempuan saja...!!!!");
            tbregistrasiIGD.requestFocus();
        } else if (!cekDataPersalinan.getText().equals("")) {
            x = JOptionPane.showConfirmDialog(null, "Data ponek pasien yg bernama " + TPasien.getText() + " sudah tersimpan, apakah mau diperbaiki...?", "Konfirmasi", JOptionPane.YES_NO_OPTION);
            if (x == JOptionPane.YES_OPTION) {
                this.setCursor(Cursor.getPredefinedCursor(Cursor.WAIT_CURSOR));
                var.setform("DlgIGD");
                DlgInputPonek ponek = new DlgInputPonek(null, false);
                //ponek.setSize(internalFrame1.getWidth(), internalFrame1.getHeight());
                ponek.setSize(817, 601);
                ponek.setLocationRelativeTo(internalFrame1);
                ponek.pasien(TNoRw.getText(), TNoRM.getText(), TPasien.getText(), tbregistrasiIGD.getValueAt(tbregistrasiIGD.getSelectedRow(), 17).toString());
                ponek.tampil();
                ponek.setVisible(true);
                ponek.PanelInput.setPreferredSize(new Dimension(WIDTH,270));
                ponek.FormInput.setVisible(true);
                ponek.ChkInput.setVisible(true);
                ponek.cmbAlamat.requestFocus();
                this.setCursor(Cursor.getDefaultCursor());
            } else {
                emptTeks();
            }
        } else if (kelaminPersalinan.getText().equals("P") && (cekDataPersalinan.getText().equals(""))) {
            this.setCursor(Cursor.getPredefinedCursor(Cursor.WAIT_CURSOR));
            var.setform("DlgIGD");
            DlgInputPonek ponek = new DlgInputPonek(null, false);
            //ponek.setSize(internalFrame1.getWidth(), internalFrame1.getHeight());
            ponek.setSize(817, 601);
            ponek.setLocationRelativeTo(internalFrame1);
            ponek.pasien(TNoRw.getText(), TNoRM.getText(), TPasien.getText(), tbregistrasiIGD.getValueAt(tbregistrasiIGD.getSelectedRow(), 17).toString());
            ponek.tampil();
            ponek.setVisible(true);
            ponek.PanelInput.setPreferredSize(new Dimension(WIDTH,270));
            ponek.FormInput.setVisible(true);
            ponek.ChkInput.setVisible(true);
            ponek.cmbAlamat.requestFocus();
            this.setCursor(Cursor.getDefaultCursor());
        }
    }//GEN-LAST:event_MnDataPonekBtnPrintActionPerformed

    private void umurDaftarKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_umurDaftarKeyPressed
        // TODO add your handling code here:
    }//GEN-LAST:event_umurDaftarKeyPressed

    private void kelaminPersalinanKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_kelaminPersalinanKeyPressed
        // TODO add your handling code here:
    }//GEN-LAST:event_kelaminPersalinanKeyPressed

    private void cekDataPersalinanKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_cekDataPersalinanKeyPressed
        // TODO add your handling code here:
    }//GEN-LAST:event_cekDataPersalinanKeyPressed

    private void MnKartuUmumActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_MnKartuUmumActionPerformed
        if (tabMode.getRowCount() == 0) {
            JOptionPane.showMessageDialog(null, "Maaf, data pasien sudah habis...!!!!");
            BtnBatal.requestFocus();
        } else if (TPasien.getText().trim().equals("")) {
            JOptionPane.showMessageDialog(null, "Maaf, Silahkan anda pilih dulu data pasien dengan mengklik data pada tabel...!!!");
            tbregistrasiIGD.requestFocus();
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
            BtnBatal.requestFocus();
        } else if (TPasien.getText().trim().equals("")) {
            JOptionPane.showMessageDialog(null, "Maaf, Silahkan anda pilih dulu data pasien dengan mengklik data pada tabel...!!!");
            tbregistrasiIGD.requestFocus();
        } else {
            this.setCursor(Cursor.getPredefinedCursor(Cursor.WAIT_CURSOR));
            Valid.MyReport("rptKartuBerobatAsuransi.jrxml", "report", "::[ Kartu Berobat Pasien (KIB) Non Umum ]::",
                "select * from pasien where pasien.no_rkm_medis='" + TNoRM.getText() + "' ");
            this.setCursor(Cursor.getDefaultCursor());
        }
    }//GEN-LAST:event_MnKartuNonUmumActionPerformed

    private void MnlembarRM5ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_MnlembarRM5ActionPerformed
        if (TNoRM.getText().equals("")) {
            JOptionPane.showMessageDialog(null, "Pilih dulu salah satu data pasiennya pada tabel...!!!!");
            tbregistrasiIGD.requestFocus();
        } else {
            this.setCursor(Cursor.getPredefinedCursor(Cursor.WAIT_CURSOR));
            Valid.MyReport("rptlembarRM5.jrxml", "report", "::[ Identitas Pasien Lembar RM 5 ]::",
                    "select * from pasien where no_rkm_medis='" + TNoRM.getText() + "' ");
            this.setCursor(Cursor.getDefaultCursor());
        }
    }//GEN-LAST:event_MnlembarRM5ActionPerformed

    private void MnlembarRM51ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_MnlembarRM51ActionPerformed
        if (TNoRM.getText().equals("")) {
            JOptionPane.showMessageDialog(null, "Pilih dulu salah satu data pasiennya pada tabel...!!!!");
            tbregistrasiIGD.requestFocus();
        } else {
            this.setCursor(Cursor.getPredefinedCursor(Cursor.WAIT_CURSOR));
            Valid.MyReport("rptlembarRM51.jrxml", "report", "::[ Identitas Pasien Lembar RM 5.1 ]::",
                    "select * from pasien where no_rkm_medis='" + TNoRM.getText() + "' ");
            this.setCursor(Cursor.getDefaultCursor());
        }
    }//GEN-LAST:event_MnlembarRM51ActionPerformed

    private void MnlembarRM52ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_MnlembarRM52ActionPerformed
        if (TNoRM.getText().equals("")) {
            JOptionPane.showMessageDialog(null, "Pilih dulu salah satu data pasiennya pada tabel...!!!!");
            tbregistrasiIGD.requestFocus();
        } else {
            this.setCursor(Cursor.getPredefinedCursor(Cursor.WAIT_CURSOR));
            Valid.MyReport("rptlembarRM52.jrxml", "report", "::[ Identitas Pasien Lembar RM 5.2 ]::",
                    "select * from pasien where no_rkm_medis='" + TNoRM.getText() + "' ");
            this.setCursor(Cursor.getDefaultCursor());
        }
    }//GEN-LAST:event_MnlembarRM52ActionPerformed

    private void MnlembarRM521ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_MnlembarRM521ActionPerformed
        if (TNoRM.getText().equals("")) {
            JOptionPane.showMessageDialog(null, "Pilih dulu salah satu data pasiennya pada tabel...!!!!");
            tbregistrasiIGD.requestFocus();
        } else {
            this.setCursor(Cursor.getPredefinedCursor(Cursor.WAIT_CURSOR));
            Valid.MyReport("rptlembarRM521.jrxml", "report", "::[ Identitas Pasien Lembar RM 5.2.1 ]::",
                    "select * from pasien where no_rkm_medis='" + TNoRM.getText() + "' ");
            this.setCursor(Cursor.getDefaultCursor());
        }
    }//GEN-LAST:event_MnlembarRM521ActionPerformed

    private void MnlembarRM6ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_MnlembarRM6ActionPerformed
        if (TNoRM.getText().equals("")) {
            JOptionPane.showMessageDialog(null, "Pilih dulu salah satu data pasiennya pada tabel...!!!!");
            tbregistrasiIGD.requestFocus();
        } else {
            this.setCursor(Cursor.getPredefinedCursor(Cursor.WAIT_CURSOR));
            Valid.MyReport("rptlembarRM6.jrxml", "report", "::[ Identitas Pasien Lembar RM 6 ]::",
                    "select * from pasien where no_rkm_medis='" + TNoRM.getText() + "' ");
            this.setCursor(Cursor.getDefaultCursor());
        }
    }//GEN-LAST:event_MnlembarRM6ActionPerformed

    private void MnlembarCoverActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_MnlembarCoverActionPerformed
        if (TNoRM.getText().equals("")) {
            JOptionPane.showMessageDialog(null, "Pilih dulu salah satu data pasiennya pada tabel...!!!!");
            tbregistrasiIGD.requestFocus();
        } else {
            this.setCursor(Cursor.getPredefinedCursor(Cursor.WAIT_CURSOR));
            Valid.MyReport("rptlembarCoverRM.jrxml", "report", "::[ Identitas Pasien Lembar Cover RM ]::",
                    "SELECT DATE_FORMAT(rp.tgl_registrasi,'%d-%m-%Y') tgl_kunjungan, p.no_rkm_medis, p.nm_pasien, "
                    + "DATE_FORMAT(p.tgl_lahir,'%d-%m-%Y') tgl_lahir, pj.png_jawab, "
                    + "CONCAT(p.alamat,', ',kl.nm_kel,', ',kc.nm_kec,', ',kb.nm_kab) alamat FROM pasien p "
                    + "INNER JOIN kelurahan kl on kl.kd_kel=p.kd_kel INNER JOIN kecamatan kc on kc.kd_kec=p.kd_kec "
                    + "INNER JOIN kabupaten kb on kb.kd_kab=p.kd_kab INNER JOIN reg_periksa rp on rp.no_rkm_medis=p.no_rkm_medis "
                    + "INNER JOIN penjab pj on pj.kd_pj=rp.kd_pj WHERE p.no_rkm_medis='" + TNoRM.getText() + "' AND "
                    + "rp.tgl_registrasi='" + tglDaftar + "' ");
            this.setCursor(Cursor.getDefaultCursor());
        }
    }//GEN-LAST:event_MnlembarCoverActionPerformed

    private void MnlembarLabelActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_MnlembarLabelActionPerformed
        if (TNoRM.getText().equals("")) {
            JOptionPane.showMessageDialog(null, "Pilih dulu salah satu data pasiennya pada tabel...!!!!");
            tbregistrasiIGD.requestFocus();
        } else {
            this.setCursor(Cursor.getPredefinedCursor(Cursor.WAIT_CURSOR));
            Valid.MyReport("rptlembarLabelIGD.jrxml", "report", "::[ Identitas Pasien Lembar Label ]::",
                    "select * from pasien where no_rkm_medis='" + TNoRM.getText() + "' ");
            this.setCursor(Cursor.getDefaultCursor());
        }
    }//GEN-LAST:event_MnlembarLabelActionPerformed

    private void BtnAlasanAPSActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_BtnAlasanAPSActionPerformed
        if (!cmbTindakan.getSelectedItem().equals("APS")) {
            cmbTindakan.setSelectedItem("APS");
            cmbKet.setSelectedItem("PULANG");
        }

        var.setform("DlgIGD");
        DlgAPS.setSize(455, 231);
        DlgAPS.setLocationRelativeTo(internalFrame1);
        DlgAPS.setVisible(true);

        TCari1.setText("");
        kdAPS = "";
        BtnCloseIn11.setEnabled(true);
        tampilAPS();
    }//GEN-LAST:event_BtnAlasanAPSActionPerformed

    private void BtnAlasanAPSKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_BtnAlasanAPSKeyPressed
        // TODO add your handling code here:
    }//GEN-LAST:event_BtnAlasanAPSKeyPressed

    private void BtnCloseIn11ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_BtnCloseIn11ActionPerformed
        DlgAPS.dispose();
        norawatAPS = "";
    }//GEN-LAST:event_BtnCloseIn11ActionPerformed

    private void tbAPSMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tbAPSMouseClicked
        if (tabMode1.getRowCount() != 0) {
            try {
                getdataAPS();
            } catch (java.lang.NullPointerException e) {
            }
        }
    }//GEN-LAST:event_tbAPSMouseClicked

    private void tbAPSKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_tbAPSKeyPressed
        if (tabMode1.getRowCount() != 0) {
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

    private void TCari1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_TCari1ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_TCari1ActionPerformed

    private void TCari1KeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_TCari1KeyPressed
        if (evt.getKeyCode() == KeyEvent.VK_ENTER) {
            BtnCari5ActionPerformed(null);
        } else if (evt.getKeyCode() == KeyEvent.VK_PAGE_DOWN) {
            BtnCari5.requestFocus();
        } else if (evt.getKeyCode() == KeyEvent.VK_PAGE_UP) {
            BtnCloseIn11.requestFocus();
        }
    }//GEN-LAST:event_TCari1KeyPressed

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

    private void cmbKetActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_cmbKetActionPerformed
        if (cmbTindakan.getSelectedItem().equals("APS")) {
            cmbKet.setSelectedItem("PULANG");
        }
    }//GEN-LAST:event_cmbKetActionPerformed

    private void MnSuratAPSActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_MnSuratAPSActionPerformed
        cekAPS = "";
        cekAPS = Sequel.cariIsi("select no_rawat from ralan_aps where no_rawat='" + norawatAPS + "'");
        
        if (TNoRM.getText().equals("")) {
            JOptionPane.showMessageDialog(null, "Pilih dulu salah satu pasien yang pulang dg. cara APS pada tabel...!!!!");
        } else if (cekAPS.equals("") && (!TNoRM.getText().equals(""))) {
            JOptionPane.showMessageDialog(null, "Pasien ini bukan termasuk jenis pasien yg. APS dari IGD...!!!!");
            BtnBatal.requestFocus();
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
            Valid.MyReport("rptsuratAPS1.jrxml", "report", "::[ Surat Pernyataan Pulang APS (Atas Permintaan Sendiri) RM 18.5 ]::",
                    "SELECT rp.no_rkm_medis, p.nm_pasien, IFNULL(p.tmp_lahir, '-') tmpt_lhr, DATE_FORMAT(p.tgl_lahir, '%d-%m-%Y') tgl_lahir,"
                    + "ma.nm_alasan, d.nm_dokter, DATE_FORMAT(rp.tgl_registrasi, '%d %M %Y') tgl_kunjungan, ra.keterangan FROM ralan_aps ra "
                    + "INNER JOIN reg_periksa rp ON rp.no_rawat = ra.no_rawat INNER JOIN pasien p ON p.no_rkm_medis = rp.no_rkm_medis "
                    + "INNER JOIN dokter d ON d.kd_dokter = rp.kd_dokter INNER JOIN master_aps ma ON ma.kd_aps = ra.kd_aps "
                    + "WHERE rp.stts_daftar<>'batal' AND ra.no_rawat = '" + norawatAPS + "'", param);

            this.setCursor(Cursor.getDefaultCursor());
            norawatAPS = "";
        }
        
    }//GEN-LAST:event_MnSuratAPSActionPerformed

    private void MnJAMPERSALActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_MnJAMPERSALActionPerformed
        if (nmpnj.getText().equals("")) {
            emptTeks();
            JOptionPane.showMessageDialog(null, "Silakan klik dulu nama pasiennya pada tabel..!!");
            tbregistrasiIGD.requestFocus();
        } else if (nmpnj.getText().equals("JAMPERSAL (PBI)")) {
            DlgJampersal.setSize(490, 125);
            DlgJampersal.setLocationRelativeTo(internalFrame1);
            DlgJampersal.setVisible(true);
        } else {
            emptTeks();
            JOptionPane.showMessageDialog(null, "Jenis cara bayar pasien tersebut tidak sesuai..!!");
            tbregistrasiIGD.requestFocus();
        }
    }//GEN-LAST:event_MnJAMPERSALActionPerformed

    private void BtnCloseIn3ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_BtnCloseIn3ActionPerformed
        DlgJampersal.dispose();
        emptTeks();
    }//GEN-LAST:event_BtnCloseIn3ActionPerformed

    private void BtnCloseIn3KeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_BtnCloseIn3KeyPressed
        // TODO add your handling code here:
    }//GEN-LAST:event_BtnCloseIn3KeyPressed

    private void BtnSimpan2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_BtnSimpan2ActionPerformed
        cekjampersal = 0;
        cekjampersal = Sequel.cariInteger("SELECT Count(1) cek FROM bridging_jampersal WHERE jns_rawat='Jalan' and no_rawat='" + TNoRw.getText() + "'");

        if (noSrt1.getText().trim().equals("")) {
            Valid.textKosong(noSrt1, "No. Surat Keterangan Peserta Jampersal");
        } else if (cekjampersal >= 1) {
            JOptionPane.showMessageDialog(null, "Data tidak tersimpan, SEP sudah pernah dibikinkan dengan no. rawat yang sama..!!");
            noSrt1.requestFocus();
        } else {
            Sequel.menyimpan("bridging_jampersal", "'" + TNoRw.getText() + "',"
                + "'" + noSrt1.getText() + "','Jalan',"
                + "'" + tglDaftar + "',"
                + "'" + Valid.SetTgl(TglSurat1.getSelectedItem() + "") + "',"
                + "'" + TNoRw.getText() + "JJD' ", "Data SEP Jampersal");
            JOptionPane.showMessageDialog(null, "Data berhasil disimpan, SEP siap dicetak......");
            BtnCtkJmp.requestFocus();
        }
    }//GEN-LAST:event_BtnSimpan2ActionPerformed

    private void BtnSimpan2KeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_BtnSimpan2KeyPressed
        // TODO add your handling code here:
    }//GEN-LAST:event_BtnSimpan2KeyPressed

    private void noSrt1KeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_noSrt1KeyPressed
        // TODO add your handling code here:
    }//GEN-LAST:event_noSrt1KeyPressed

    private void TglSurat1ItemStateChanged(java.awt.event.ItemEvent evt) {//GEN-FIRST:event_TglSurat1ItemStateChanged
        // TODO add your handling code here:
    }//GEN-LAST:event_TglSurat1ItemStateChanged

    private void TglSurat1KeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_TglSurat1KeyPressed
        // TODO add your handling code here:
    }//GEN-LAST:event_TglSurat1KeyPressed

    private void BtnGantijmpActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_BtnGantijmpActionPerformed
        if (noSrt1.getText().trim().equals("")) {
            Valid.textKosong(noSrt1, "No. Surat Keterangan Peserta Jampersal");
        } else {
            Sequel.mengedit("bridging_jampersal", "no_sep='" + sepJmp.getText() + "'", "no_surat='" + noSrt1.getText() + "',"
                + " tgl_surat='" + Valid.SetTgl(TglSurat1.getSelectedItem() + "") + "' ");
            JOptionPane.showMessageDialog(null, "Data berhasil diubah, SEP siap dicetak......");
            BtnCtkJmp.requestFocus();
        }
    }//GEN-LAST:event_BtnGantijmpActionPerformed

    private void BtnGantijmpKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_BtnGantijmpKeyPressed
        // TODO add your handling code here:
    }//GEN-LAST:event_BtnGantijmpKeyPressed

    private void BtnCtkJmpActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_BtnCtkJmpActionPerformed
        sepJmp.setText(Sequel.cariIsi("SELECT no_sep FROM bridging_jampersal WHERE no_rawat='" + TNoRw.getText() + "' AND jns_rawat='Jalan'"));

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
            Valid.MyReport("rptSEPJalan1.jrxml", "report", "::[ Cetak SEP Pasien JAMPERSAL Rawat Jalan ]::",
                    " SELECT p.no_rkm_medis, bp.no_sep, rp.tgl_registrasi, p.no_ktp, "
                    + "p.nm_pasien, p.tgl_lahir, IF(p.jk='L','Laki-laki','Perempuan') as jk, "
                    + "pl.nm_poli, rp.no_rawat, bp.no_surat, bp.jns_rawat, bp.tgl_surat, bp.tgl_rawat, pj.png_jawab "
                    + "FROM reg_periksa rp INNER JOIN pasien p ON rp.no_rkm_medis = p.no_rkm_medis "
                    + "INNER JOIN poliklinik pl ON rp.kd_poli = pl.kd_poli INNER JOIN bridging_jampersal bp ON bp.no_rawat = rp.no_rawat "
                    + "INNER JOIN penjab pj ON rp.kd_pj = pj.kd_pj WHERE bp.no_sep='" + sepJmp.getText() + "'  "
                    + "AND bp.jns_rawat='Jalan' AND pj.png_jawab LIKE '%jampersal%'", param);
            this.setCursor(Cursor.getDefaultCursor());
            DlgJampersal.dispose();
            emptTeks();
        }
    }//GEN-LAST:event_BtnCtkJmpActionPerformed

    private void BtnCtkJmpKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_BtnCtkJmpKeyPressed
        // TODO add your handling code here:
    }//GEN-LAST:event_BtnCtkJmpKeyPressed

    private void MnPeniliaianAwalKeperawatanRalanBtnPrintActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_MnPeniliaianAwalKeperawatanRalanBtnPrintActionPerformed
        if (tabMode.getRowCount() == 0) {
            JOptionPane.showMessageDialog(null, "Maaf, tabel masih kosong...!!!!");
        } else if (TNoRw.getText().trim().equals("")) {
            JOptionPane.showMessageDialog(null, "Maaf, Silahkan anda pilih dulu dengan mengklik data pada tabel...!!!");
            tbregistrasiIGD.requestFocus();
        } else {
            if (tbregistrasiIGD.getSelectedRow() != -1) {
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
    }//GEN-LAST:event_MnPeniliaianAwalKeperawatanRalanBtnPrintActionPerformed

    private void MnDataTriaseIGDBtnPrintActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_MnDataTriaseIGDBtnPrintActionPerformed
        if (tabMode.getRowCount() == 0) {
            JOptionPane.showMessageDialog(null, "Maaf, tabel masih kosong...!!!!");
            TCari.requestFocus();
        } else if (TNoRw.getText().trim().equals("")) {
            JOptionPane.showMessageDialog(null, "Maaf, Silahkan anda pilih dulu dengan mengklik data pada tabel...!!!");
            tbregistrasiIGD.requestFocus();
        } else {
            if (tbregistrasiIGD.getSelectedRow() != -1) {
                this.setCursor(Cursor.getPredefinedCursor(Cursor.WAIT_CURSOR));
                RMTriaseIGD form = new RMTriaseIGD(null, false);
                form.isCek();
                form.setNoRm(TNoRw.getText(), TNoRM.getText(), TPasien.getText());
                form.setSize(internalFrame1.getWidth() - 20, internalFrame1.getHeight() - 20);
                form.setLocationRelativeTo(internalFrame1);
                form.setVisible(true);
                this.setCursor(Cursor.getDefaultCursor());
            }
        }
    }//GEN-LAST:event_MnDataTriaseIGDBtnPrintActionPerformed

    private void BtnCloseIn4ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_BtnCloseIn4ActionPerformed
        DlgSuratTindakanDokter.dispose();
        emptSuratTindakan();
    }//GEN-LAST:event_BtnCloseIn4ActionPerformed

    private void BtnCloseIn4KeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_BtnCloseIn4KeyPressed
        // TODO add your handling code here:
    }//GEN-LAST:event_BtnCloseIn4KeyPressed

    private void nmPJKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_nmPJKeyPressed
        Valid.pindah(evt, nmPJ, umurPJ);
    }//GEN-LAST:event_nmPJKeyPressed

    private void TglSuratTindakanItemStateChanged(java.awt.event.ItemEvent evt) {//GEN-FIRST:event_TglSuratTindakanItemStateChanged
        // TODO add your handling code here:
    }//GEN-LAST:event_TglSuratTindakanItemStateChanged

    private void TglSuratTindakanKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_TglSuratTindakanKeyPressed
        // TODO add your handling code here:
    }//GEN-LAST:event_TglSuratTindakanKeyPressed

    private void BtnGantiSuratActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_BtnGantiSuratActionPerformed
        if (TNoRM.getText().trim().equals("")) {
            Valid.textKosong(TNoRM, "Nama Pasien");
        } else if (nmPJ.getText().trim().equals("")) {
            Valid.textKosong(nmPJ, "Nama Penanggung Jawab");
        } else if (umurPJ.getText().trim().equals("")) {
            Valid.textKosong(umurPJ, "Umur Penanggung Jawab");
        } else if (alamatPJ.getText().trim().equals("")) {
            Valid.textKosong(alamatPJ, "Alamat Penanggung Jawab");
        } else if (nmkel.getText().trim().equals("") || nmkec.getText().trim().equals("") || nmkab.getText().trim().equals("")) {
            Valid.textKosong(nmkel, "Nama kelurahan, kecamatan, kabupaten");
        } else if (tindakanDokter.getText().trim().equals("")) {
            Valid.textKosong(tindakanDokter, "Tindakan Kedokteran");
        } else {
            kelaminPJ = "";
            cekSurat = "";

            if (jkPJ.getSelectedItem().toString().equals("Laki-laki")) {
                kelaminPJ = "L";
            } else {
                kelaminPJ = "P";
            }
            
            Sequel.mengedit("surat_tindakan_kedokteran", "no_rawat='" + norwSurat.getText() + "' and kasus_tindakan='Ralan'",
                    "tgl_surat='" + Valid.SetTgl(TglSuratTindakan.getSelectedItem() + "") + "', jam_surat='" + CmbJam1.getSelectedItem() + ":" + CmbMenit1.getSelectedItem() + ":" + CmbDetik1.getSelectedItem() + "',"
                    + "nm_penjab='" + nmPJ.getText() + "', umur_penjab='" + umurPJ.getText() + "', jk_penjab='" + kelaminPJ + "', alamat_penjab='" + alamatPJ.getText() + "', "
                    + "kd_kel='" + kdKel + "', kd_kec='" + kdKec + "', kd_kab='" + kdKab + "', hubungan_dg_pasien='" + hubungan.getSelectedItem() + "', "
                    + "jns_surat='" + jnsSurat.getSelectedItem() + "', nm_tindakan_kedokteran='" + tindakanDokter.getText() + "', alasan_penolakan='" + alasanTolak.getText() + "'");
            
            Sequel.mengedit("pasien", "no_rkm_medis='" + TNoRM.getText() + "'", "namakeluarga='" + nmPJ.getText() + "',alamatpj='" + alamatPJ.getText() + "',"
                    + "kelurahanpj='" + kdKel + "', kecamatanpj='" + kdKec + "', kabupatenpj='" + kdKab + "', keluarga='" + hubungan.getSelectedItem() + "'");
            
            cekSurat = Sequel.cariIsi("select jns_surat from surat_tindakan_kedokteran where no_rawat='" + norwSurat.getText() + "' and kasus_tindakan='Ralan'");
            if (cekSurat.equals("PERSETUJUAN")) {
                Sequel.mengedit("surat_tindakan_kedokteran", "no_rawat='" + norwSurat.getText() + "' and kasus_tindakan='Ralan'", "alasan_penolakan='-'");
            }
            
            emptSuratTindakan();
        }
    }//GEN-LAST:event_BtnGantiSuratActionPerformed

    private void BtnGantiSuratKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_BtnGantiSuratKeyPressed
        // TODO add your handling code here:
    }//GEN-LAST:event_BtnGantiSuratKeyPressed

    private void CmbJam1KeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_CmbJam1KeyPressed
        // TODO add your handling code here:
    }//GEN-LAST:event_CmbJam1KeyPressed

    private void CmbMenit1KeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_CmbMenit1KeyPressed
        // TODO add your handling code here:
    }//GEN-LAST:event_CmbMenit1KeyPressed

    private void CmbDetik1KeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_CmbDetik1KeyPressed
        // TODO add your handling code here:
    }//GEN-LAST:event_CmbDetik1KeyPressed

    private void BtnHapusSuratActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_BtnHapusSuratActionPerformed
        if (TNoRM.getText().trim().equals("")) {
            Valid.textKosong(TNoRM, "Nama Pasien");
        } else {
            Sequel.queryu("DELETE FROM surat_tindakan_kedokteran WHERE no_rawat='" + norwSurat.getText() + "' and kasus_tindakan='Ralan'");
            DlgSuratTindakanDokter.dispose();
            emptSuratTindakan();
        }
    }//GEN-LAST:event_BtnHapusSuratActionPerformed

    private void BtnHapusSuratKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_BtnHapusSuratKeyPressed
        // TODO add your handling code here:
    }//GEN-LAST:event_BtnHapusSuratKeyPressed

    private void BtnCetakSuratActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_BtnCetakSuratActionPerformed
        cekNRWSurat = 0;
        cekNRWSurat = Sequel.cariInteger("select count(1) cek from surat_tindakan_kedokteran where no_rawat='" + norwSurat.getText() + "' and kasus_tindakan='Ralan'");
        cekSuratTindakan(norwSurat.getText());
        
        if (TNoRM.getText().trim().equals("")) {
            Valid.textKosong(TNoRM, "Nama Pasien");
        } else if (nmPJ.getText().trim().equals("")) {
            Valid.textKosong(nmPJ, "Nama Penanggung Jawab");
        } else if (cekNRWSurat == 0) {
            JOptionPane.showMessageDialog(null, "Data Persetujuan/Penolakan Tindakan Medis Kedokteran tdk. ditemukan pada kunjungan yg. dimaksud...!!!!");
            nmPJ.requestFocus();
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
            
            if (jnsSurat.getSelectedItem().toString().equals("PERSETUJUAN")) {
                Valid.MyReport("rptSetujuTindakanDokter.jrxml", "report", "::[ Lembar Formulir Persetujuan Tindakan Kedokteran ]::",
                        "SELECT s.nm_penjab, CONCAT(s.umur_penjab,' Thn. / ',IF(s.jk_penjab='L','Laki-laki','Perempuan')) umur_pj, "
                        + "CONCAT(s.alamat_penjab,', ',kl1.nm_kel,', ',kc1.nm_kec,', ',kb1.nm_kab) alamat_pj, s.hubungan_dg_pasien selaku, s.jns_surat, "
                        + "s.nm_tindakan_kedokteran, s.alasan_penolakan, p.nm_pasien, CONCAT(rp.umurdaftar,' ',rp.sttsumur,'. / ',IF(p.jk='L','Laki-laki','Perempuan')) umur_px, "
                        + "CONCAT(p.alamat,', ',kl2.nm_kel,', ',kc2.nm_kec,', ',kb2.nm_kab) alamat_px, "
                        + "CONCAT('Martapura, ',DATE_FORMAT(s.tgl_surat,'%d-%m-%Y'),' Pukul ',DATE_FORMAT(s.jam_surat,'%h:%m %p'),' WITA') tgl_surat FROM surat_tindakan_kedokteran s "
                        + "INNER JOIN reg_periksa rp on rp.no_rawat=s.no_rawat INNER JOIN pasien p on p.no_rkm_medis=rp.no_rkm_medis "
                        + "INNER JOIN kelurahan kl1 on kl1.kd_kel=s.kd_kel INNER JOIN kelurahan kl2 on kl2.kd_kel=p.kd_kel "
                        + "INNER JOIN kecamatan kc1 on kc1.kd_kec=s.kd_kec INNER JOIN kecamatan kc2 on kc2.kd_kec=p.kd_kec "
                        + "INNER JOIN kabupaten kb1 on kb1.kd_kab=s.kd_kab INNER JOIN kabupaten kb2 on kb2.kd_kab=p.kd_kab "
                        + "WHERE s.kasus_tindakan='Ralan' and s.no_rawat='" + norwSurat.getText() + "' and s.jns_surat='" + jnsSurat.getSelectedItem() + "'", param);
            } else {
                Valid.MyReport("rptTolakTindakanDokter.jrxml", "report", "::[ Lembar Formulir Penolakan Tindakan Kedokteran ]::",
                        "SELECT s.nm_penjab, CONCAT(s.umur_penjab,' Thn. / ',IF(s.jk_penjab='L','Laki-laki','Perempuan')) umur_pj, "
                        + "CONCAT(s.alamat_penjab,', ',kl1.nm_kel,', ',kc1.nm_kec,', ',kb1.nm_kab) alamat_pj, s.hubungan_dg_pasien selaku, s.jns_surat, "
                        + "s.nm_tindakan_kedokteran, s.alasan_penolakan, p.nm_pasien, CONCAT(rp.umurdaftar,' ',rp.sttsumur,'. / ',IF(p.jk='L','Laki-laki','Perempuan')) umur_px, "
                        + "CONCAT(p.alamat,', ',kl2.nm_kel,', ',kc2.nm_kec,', ',kb2.nm_kab) alamat_px, "
                        + "CONCAT('Martapura, ',DATE_FORMAT(s.tgl_surat,'%d-%m-%Y'),' Pukul ',DATE_FORMAT(s.jam_surat,'%h:%m %p'),' WITA') tgl_surat FROM surat_tindakan_kedokteran s "
                        + "INNER JOIN reg_periksa rp on rp.no_rawat=s.no_rawat INNER JOIN pasien p on p.no_rkm_medis=rp.no_rkm_medis "
                        + "INNER JOIN kelurahan kl1 on kl1.kd_kel=s.kd_kel INNER JOIN kelurahan kl2 on kl2.kd_kel=p.kd_kel "
                        + "INNER JOIN kecamatan kc1 on kc1.kd_kec=s.kd_kec INNER JOIN kecamatan kc2 on kc2.kd_kec=p.kd_kec "
                        + "INNER JOIN kabupaten kb1 on kb1.kd_kab=s.kd_kab INNER JOIN kabupaten kb2 on kb2.kd_kab=p.kd_kab "
                        + "WHERE s.kasus_tindakan='Ralan' and s.no_rawat='" + norwSurat.getText() + "' and s.jns_surat='" + jnsSurat.getSelectedItem() + "'", param);
            }
            this.setCursor(Cursor.getDefaultCursor());
            DlgSuratTindakanDokter.dispose();
            emptSuratTindakan();
            emptTeks();
        }
    }//GEN-LAST:event_BtnCetakSuratActionPerformed

    private void BtnCetakSuratKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_BtnCetakSuratKeyPressed
        // TODO add your handling code here:
    }//GEN-LAST:event_BtnCetakSuratKeyPressed

    private void umurPJKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_umurPJKeyPressed
        // TODO add your handling code here:
    }//GEN-LAST:event_umurPJKeyPressed

    private void jkPJKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_jkPJKeyPressed
//        Valid.pindah(evt,TanggalKunjungan,AlasanKedatangan);
    }//GEN-LAST:event_jkPJKeyPressed

    private void alamatPJKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_alamatPJKeyPressed
        // TODO add your handling code here:
    }//GEN-LAST:event_alamatPJKeyPressed

    private void nmkelKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_nmkelKeyPressed
        // TODO add your handling code here:
    }//GEN-LAST:event_nmkelKeyPressed

    private void nmkecKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_nmkecKeyPressed
        // TODO add your handling code here:
    }//GEN-LAST:event_nmkecKeyPressed

    private void nmkabKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_nmkabKeyPressed
        // TODO add your handling code here:
    }//GEN-LAST:event_nmkabKeyPressed

    private void hubunganKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_hubunganKeyPressed
        // TODO add your handling code here:
    }//GEN-LAST:event_hubunganKeyPressed

    private void jnsSuratKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_jnsSuratKeyPressed
        // TODO add your handling code here:
    }//GEN-LAST:event_jnsSuratKeyPressed

    private void tindakanDokterKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_tindakanDokterKeyPressed
        // TODO add your handling code here:
    }//GEN-LAST:event_tindakanDokterKeyPressed

    private void alasanTolakKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_alasanTolakKeyPressed
        // TODO add your handling code here:
    }//GEN-LAST:event_alasanTolakKeyPressed

    private void BtnSimpan3ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_BtnSimpan3ActionPerformed
        cekNRWSurat = 0;
        cekNRWSurat = Sequel.cariInteger("select count(1) cek from surat_tindakan_kedokteran where no_rawat='" + norwSurat.getText() + "' and kasus_tindakan='Ralan'");
        
        if (TNoRM.getText().trim().equals("")) {
            Valid.textKosong(TNoRM, "Nama Pasien");
        } else if (nmPJ.getText().trim().equals("")) {
            Valid.textKosong(nmPJ, "Nama Penanggung Jawab");
        } else if (umurPJ.getText().trim().equals("")) {
            Valid.textKosong(umurPJ, "Umur Penanggung Jawab");
        } else if (alamatPJ.getText().trim().equals("")) {
            Valid.textKosong(alamatPJ, "Alamat Penanggung Jawab");
        } else if (nmkel.getText().trim().equals("") || nmkec.getText().trim().equals("") || nmkab.getText().trim().equals("")) {
            Valid.textKosong(nmkel, "Nama kelurahan, kecamatan, kabupaten");
        } else if (tindakanDokter.getText().trim().equals("")) {
            Valid.textKosong(tindakanDokter, "Tindakan Kedokteran");
        } else if (cekNRWSurat >= 1) {
            JOptionPane.showMessageDialog(null, "Data sudah pernah tersimpan, jika melakukan perubahan data klik tombol ganti...!!!");
            BtnGantiSurat.requestFocus();
        } else {
            kelaminPJ = "";

            if (jkPJ.getSelectedItem().toString().equals("Laki-laki")) {
                kelaminPJ = "L";
            } else {
                kelaminPJ = "P";
            }

            Sequel.menyimpan("surat_tindakan_kedokteran", "'" + norwSurat.getText() + "','" + Valid.SetTgl(TglSuratTindakan.getSelectedItem() + "") + "',"
                    + "'" + CmbJam1.getSelectedItem() + ":" + CmbMenit1.getSelectedItem() + ":" + CmbDetik1.getSelectedItem() + "',"
                    + "'" + nmPJ.getText() + "','" + umurPJ.getText() + "','" + kelaminPJ + "','" + alamatPJ.getText() + "','" + kdKel + "','" + kdKec + "',"
                    + "'" + kdKab + "','" + hubungan.getSelectedItem() + "','" + jnsSurat.getSelectedItem() + "','Ralan','" + tindakanDokter.getText() + "',"
                    + "'" + alasanTolak.getText() + "' ", "Surat Tindakan Kedokteran");

            Sequel.mengedit("pasien", "no_rkm_medis='" + TNoRM.getText() + "'", "namakeluarga='" + nmPJ.getText() + "',alamatpj='" + alamatPJ.getText() + "',"
                    + "kelurahanpj='" + kdKel + "', kecamatanpj='" + kdKec + "', kabupatenpj='" + kdKab + "', keluarga='" + hubungan.getSelectedItem() + "'");

            emptSuratTindakan();
        }
    }//GEN-LAST:event_BtnSimpan3ActionPerformed

    private void BtnSimpan3KeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_BtnSimpan3KeyPressed
        if (evt.getKeyCode() == KeyEvent.VK_SPACE) {
            BtnSimpanActionPerformed(null);
        } else {
            Valid.pindah(evt, kdpnj, BtnBatal);
        }
    }//GEN-LAST:event_BtnSimpan3KeyPressed

    private void BtnKelurahanActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_BtnKelurahanActionPerformed
        var.setform("DlgIGD");
        kel.setSize(703, 384);
        kel.setLocationRelativeTo(internalFrame1);
        kel.setVisible(true);
        kel.TCari.requestFocus();
    }//GEN-LAST:event_BtnKelurahanActionPerformed

    private void BtnKecamatanActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_BtnKecamatanActionPerformed
        var.setform("DlgIGD");
        kec.setSize(703, 384);
        kec.setLocationRelativeTo(internalFrame1);
        kec.setVisible(true);
        kec.TCari.requestFocus();
    }//GEN-LAST:event_BtnKecamatanActionPerformed

    private void BtnKabupatenActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_BtnKabupatenActionPerformed
        var.setform("DlgIGD");
        kab.setSize(703, 384);
        kab.setLocationRelativeTo(internalFrame1);
        kab.setVisible(true);
        kab.TCari.requestFocus();
    }//GEN-LAST:event_BtnKabupatenActionPerformed

    private void ChkJln1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_ChkJln1ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_ChkJln1ActionPerformed

    private void CmbDetik1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_CmbDetik1ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_CmbDetik1ActionPerformed

    private void jnsSuratActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jnsSuratActionPerformed
        if (jnsSurat.getSelectedItem().toString().equals("PERSETUJUAN")) {
//            alasanTolak.setText("-");
            alasanTolak.setEnabled(false);
            BtnSimpan3.requestFocus();
        } else {
//            alasanTolak.setText("");
            alasanTolak.setEnabled(true);
            alasanTolak.requestFocus();
        }
    }//GEN-LAST:event_jnsSuratActionPerformed

    private void MnSuratTindakanDokterActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_MnSuratTindakanDokterActionPerformed
        cekNRWSurat = 0;
        cekNRWSurat = Sequel.cariInteger("select count(1) cek from surat_tindakan_kedokteran where no_rawat='" + norwSurat.getText() + "' and kasus_tindakan='Ralan'");
        
        if (tabMode.getRowCount() == 0) {
            JOptionPane.showMessageDialog(null, "Maaf, tabel masih kosong...!!!!");
            TCari.requestFocus();
        } else if (TNoRM.getText().trim().equals("")) {
            JOptionPane.showMessageDialog(null, "Maaf, Silahkan anda pilih dulu dengan mengklik data pada tabel...!!!");
            tbregistrasiIGD.requestFocus();
        } else {
            DlgSuratTindakanDokter.setSize(662, 366);
            DlgSuratTindakanDokter.setLocationRelativeTo(internalFrame1);
            DlgSuratTindakanDokter.setVisible(true);
            
            if (cekNRWSurat == 0) {
                TglSuratTindakan.setDate(new Date());
            } else {
                cekSuratTindakan(norwSurat.getText());
            }

            nmPJ.setText(Sequel.cariIsi("select namakeluarga from pasien where no_rkm_medis='" + TNoRM.getText() + "'"));
            nmPJ.requestFocus();

            if (jnsSurat.getSelectedItem().toString().equals("PERSETUJUAN")) {
                alasanTolak.setText("-");                
                alasanTolak.setEnabled(false);
            } else {
                alasanTolak.setText("");
                alasanTolak.setEnabled(true);
            }
        }
    }//GEN-LAST:event_MnSuratTindakanDokterActionPerformed

    private void CtkSuratTindakanDokterActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_CtkSuratTindakanDokterActionPerformed
        cekNRWSurat = 0;
        cekNRWSurat = Sequel.cariInteger("select count(1) cek from surat_tindakan_kedokteran where no_rawat='" + norwSurat.getText() + "' and kasus_tindakan='Ralan'");
        cekSuratTindakan(norwSurat.getText());
        
        if (cekNRWSurat == 0) {
            JOptionPane.showMessageDialog(null, "Data Persetujuan/Penolakan Tindakan Medis Kedokteran tdk. ditemukan pada kunjungan yg. dimaksud...!!!!");
            tbregistrasiIGD.requestFocus();
        } else if (TNoRM.getText().trim().equals("") || TNoID.getText().trim().equals("")) {
            JOptionPane.showMessageDialog(null, "Pilih dulu salah satu nama pasien pada tabel...!!!!");
            tbregistrasiIGD.requestFocus();
        } else if (nmPJ.getText().trim().equals("")) {
            JOptionPane.showMessageDialog(null, "Pilih dulu salah satu nama pasien pada tabel...!!!!");
            tbregistrasiIGD.requestFocus();
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

            if (jnsSurat.getSelectedItem().toString().equals("PERSETUJUAN")) {
                Valid.MyReport("rptSetujuTindakanDokter.jrxml", "report", "::[ Lembar Formulir Persetujuan Tindakan Kedokteran ]::",
                        "SELECT s.nm_penjab, CONCAT(s.umur_penjab,' Thn. / ',IF(s.jk_penjab='L','Laki-laki','Perempuan')) umur_pj, "
                        + "CONCAT(s.alamat_penjab,', ',kl1.nm_kel,', ',kc1.nm_kec,', ',kb1.nm_kab) alamat_pj, s.hubungan_dg_pasien selaku, s.jns_surat, "
                        + "s.nm_tindakan_kedokteran, s.alasan_penolakan, p.nm_pasien, CONCAT(rp.umurdaftar,' ',rp.sttsumur,'. / ',IF(p.jk='L','Laki-laki','Perempuan')) umur_px, "
                        + "CONCAT(p.alamat,', ',kl2.nm_kel,', ',kc2.nm_kec,', ',kb2.nm_kab) alamat_px, "
                        + "CONCAT('Martapura, ',DATE_FORMAT(s.tgl_surat,'%d-%m-%Y'),' Pukul ',DATE_FORMAT(s.jam_surat,'%h:%m %p'),' WITA') tgl_surat FROM surat_tindakan_kedokteran s "
                        + "INNER JOIN reg_periksa rp on rp.no_rawat=s.no_rawat INNER JOIN pasien p on p.no_rkm_medis=rp.no_rkm_medis "
                        + "INNER JOIN kelurahan kl1 on kl1.kd_kel=s.kd_kel INNER JOIN kelurahan kl2 on kl2.kd_kel=p.kd_kel "
                        + "INNER JOIN kecamatan kc1 on kc1.kd_kec=s.kd_kec INNER JOIN kecamatan kc2 on kc2.kd_kec=p.kd_kec "
                        + "INNER JOIN kabupaten kb1 on kb1.kd_kab=s.kd_kab INNER JOIN kabupaten kb2 on kb2.kd_kab=p.kd_kab "
                        + "WHERE s.kasus_tindakan='Ralan' and s.no_rawat='" + norwSurat.getText() + "' and s.jns_surat='" + jnsSurat.getSelectedItem() + "'", param);
            } else {
                Valid.MyReport("rptTolakTindakanDokter.jrxml", "report", "::[ Lembar Formulir Penolakan Tindakan Kedokteran ]::",
                        "SELECT s.nm_penjab, CONCAT(s.umur_penjab,' Thn. / ',IF(s.jk_penjab='L','Laki-laki','Perempuan')) umur_pj, "
                        + "CONCAT(s.alamat_penjab,', ',kl1.nm_kel,', ',kc1.nm_kec,', ',kb1.nm_kab) alamat_pj, s.hubungan_dg_pasien selaku, s.jns_surat, "
                        + "s.nm_tindakan_kedokteran, s.alasan_penolakan, p.nm_pasien, CONCAT(rp.umurdaftar,' ',rp.sttsumur,'. / ',IF(p.jk='L','Laki-laki','Perempuan')) umur_px, "
                        + "CONCAT(p.alamat,', ',kl2.nm_kel,', ',kc2.nm_kec,', ',kb2.nm_kab) alamat_px, "
                        + "CONCAT('Martapura, ',DATE_FORMAT(s.tgl_surat,'%d-%m-%Y'),' Pukul ',DATE_FORMAT(s.jam_surat,'%h:%m %p'),' WITA') tgl_surat FROM surat_tindakan_kedokteran s "
                        + "INNER JOIN reg_periksa rp on rp.no_rawat=s.no_rawat INNER JOIN pasien p on p.no_rkm_medis=rp.no_rkm_medis "
                        + "INNER JOIN kelurahan kl1 on kl1.kd_kel=s.kd_kel INNER JOIN kelurahan kl2 on kl2.kd_kel=p.kd_kel "
                        + "INNER JOIN kecamatan kc1 on kc1.kd_kec=s.kd_kec INNER JOIN kecamatan kc2 on kc2.kd_kec=p.kd_kec "
                        + "INNER JOIN kabupaten kb1 on kb1.kd_kab=s.kd_kab INNER JOIN kabupaten kb2 on kb2.kd_kab=p.kd_kab "
                        + "WHERE s.kasus_tindakan='Ralan' and s.no_rawat='" + norwSurat.getText() + "' and s.jns_surat='" + jnsSurat.getSelectedItem() + "'", param);
            }
            this.setCursor(Cursor.getDefaultCursor());
            DlgSuratTindakanDokter.dispose();
            emptSuratTindakan();
            emptTeks();
        }
    }//GEN-LAST:event_CtkSuratTindakanDokterActionPerformed

    private void ppPasienCoronaBtnPrintActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_ppPasienCoronaBtnPrintActionPerformed
        if (tabMode.getRowCount() == 0) {
            JOptionPane.showMessageDialog(null, "Maaf, data pasien sudah habis...!!!!");
            TNoRM.requestFocus();
        } else if (TPasien.getText().trim().equals("")) {
            JOptionPane.showMessageDialog(null, "Maaf, Silahkan anda pilih dulu data pasien dengan mengklik data pada tabel...!!!");
            tbregistrasiIGD.requestFocus();
        } else {
            this.setCursor(Cursor.getPredefinedCursor(Cursor.WAIT_CURSOR));
            CoronaPasien form = new CoronaPasien(null, false);
            form.setPasien(TNoRM.getText(), tglDaftar, "IGD");
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
            TNoRM.requestFocus();
        } else if (TPasien.getText().trim().equals("")) {
            JOptionPane.showMessageDialog(null, "Maaf, Silahkan anda pilih dulu data pasien dengan mengklik data pada tabel...!!!");
            tbregistrasiIGD.requestFocus();
        } else {
            this.setCursor(Cursor.getPredefinedCursor(Cursor.WAIT_CURSOR));
            INACBGPerawatanCorona form = new INACBGPerawatanCorona(null, false);
            form.emptTeks();
            form.setPasien(TNoRw.getText(), TNoRM.getText(), TPasien.getText());
            form.isCek();
            form.setSize(internalFrame1.getWidth() - 20, internalFrame1.getHeight() - 20);
            form.setLocationRelativeTo(internalFrame1);
            form.setVisible(true);
            this.setCursor(Cursor.getDefaultCursor());
        }
    }//GEN-LAST:event_ppPerawatanCoronaBtnPrintActionPerformed

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

    /**
     * @data args the command line arguments
     */
    public static void main(String args[]) {
        java.awt.EventQueue.invokeLater(() -> {
            DlgIGD dialog = new DlgIGD(new javax.swing.JFrame(), true);
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
    private widget.TextBox AsalRujukan;
    private widget.Button BtnAlasanAPS;
    private widget.Button BtnAll;
    private widget.Button BtnBahasa;
    private widget.Button BtnBatal;
    private widget.Button BtnCari;
    private widget.Button BtnCari5;
    private widget.Button BtnCetakMati;
    private widget.Button BtnCetakSurat;
    private widget.Button BtnCloseIn1;
    private widget.Button BtnCloseIn11;
    private widget.Button BtnCloseIn2;
    private widget.Button BtnCloseIn3;
    private widget.Button BtnCloseIn4;
    private widget.Button BtnCtkJkd;
    private widget.Button BtnCtkJmp;
    private widget.Button BtnDokter;
    private widget.Button BtnEdit;
    private widget.Button BtnGantiMati;
    private widget.Button BtnGantiSurat;
    private widget.Button BtnGantijkd;
    private widget.Button BtnGantijmp;
    private widget.Button BtnHapus;
    private widget.Button BtnHapusMati;
    private widget.Button BtnHapusSurat;
    private widget.Button BtnKabupaten;
    private widget.Button BtnKecamatan;
    private widget.Button BtnKeluar;
    private widget.Button BtnKeluar2;
    private widget.Button BtnKeluar3;
    private widget.Button BtnKeluar4;
    private widget.Button BtnKelurahan;
    private widget.Button BtnPasien;
    private widget.Button BtnPrint;
    private widget.Button BtnPrint2;
    private widget.Button BtnPrint3;
    private widget.Button BtnPrint4;
    private widget.Button BtnPrint5;
    private widget.Button BtnSeek5;
    private widget.Button BtnSimpan;
    private widget.Button BtnSimpan1;
    private widget.Button BtnSimpan2;
    private widget.Button BtnSimpan3;
    private widget.Button BtnSuku;
    private widget.CekBox ChkDtgSendiri;
    private widget.CekBox ChkInput;
    private widget.CekBox ChkJln1;
    private widget.CekBox ChkTracker;
    private widget.ComboBox CmbDetik1;
    private widget.ComboBox CmbJam1;
    private widget.ComboBox CmbMenit1;
    private widget.TextBox CrDokter3;
    private javax.swing.JMenuItem CtkSuratTindakanDokter;
    private widget.Tanggal DTPCari1;
    private widget.Tanggal DTPCari2;
    private javax.swing.JDialog DlgAPS;
    private javax.swing.JDialog DlgDemografi;
    private javax.swing.JDialog DlgJamkesda;
    private javax.swing.JDialog DlgJampersal;
    private javax.swing.JDialog DlgMati;
    private javax.swing.JDialog DlgSakit;
    private javax.swing.JDialog DlgSakit2;
    public javax.swing.JDialog DlgSuratTindakanDokter;
    private widget.PanelBiasa FormInput;
    private widget.ComboBox JnsnoID;
    private widget.TextBox Kabupaten2;
    private widget.TextBox KataDatang;
    private widget.TextBox Kd2;
    private widget.TextBox Kecamatan2;
    private widget.TextBox Kelurahan2;
    private widget.Label LCount;
    private javax.swing.JMenuItem MnBarcode;
    private javax.swing.JMenuItem MnBarcodeRM1;
    private javax.swing.JMenuItem MnBarcodeRM2;
    private javax.swing.JMenuItem MnBilling;
    private javax.swing.JMenu MnBridging;
    private javax.swing.JMenuItem MnCatatanPasien;
    private javax.swing.JMenuItem MnCetakRegister;
    private javax.swing.JMenuItem MnCetakSuratSakit;
    private javax.swing.JMenuItem MnCetakSuratSakit1;
    private javax.swing.JMenuItem MnCetakSuratSakit2;
    private javax.swing.JMenuItem MnCheckList;
    private javax.swing.JMenuItem MnCheckList1;
    private javax.swing.JMenuItem MnCheckList2;
    private javax.swing.JMenuItem MnCheckList3;
    private javax.swing.JMenuItem MnCheckList4;
    private javax.swing.JMenuItem MnCheckList5;
    private javax.swing.JMenuItem MnCheckList6;
    private javax.swing.JMenuItem MnCheckList7;
    private javax.swing.JMenuItem MnDataHAIs;
    private javax.swing.JMenuItem MnDataPonek;
    private javax.swing.JMenuItem MnDataTriaseIGD;
    private javax.swing.JMenuItem MnDiagnosa;
    private javax.swing.JMenu MnGelangBayi;
    private javax.swing.JMenu MnGelangDewasaAnak;
    private javax.swing.JMenu MnIdentitasRM;
    private javax.swing.JMenuItem MnJAMPERSAL;
    private javax.swing.JMenuItem MnKamarInap;
    private javax.swing.JMenu MnKartu;
    private javax.swing.JMenuItem MnKartuNonUmum;
    private javax.swing.JMenuItem MnKartuUmum;
    private javax.swing.JMenu MnKelengkapanInap;
    private javax.swing.JMenuItem MnLabelRM1;
    private javax.swing.JMenuItem MnLabelRM2;
    private javax.swing.JMenuItem MnLaporanKunjunganIGD;
    private javax.swing.JMenuItem MnLaporanRekapJenisBayar;
    private javax.swing.JMenuItem MnLaporanRekapKunjunganBulanan;
    private javax.swing.JMenuItem MnLaporanRekapKunjunganBulananPoli;
    private javax.swing.JMenuItem MnLaporanRekapKunjunganDokter;
    private javax.swing.JMenuItem MnLaporanRekapKunjunganPoli;
    private javax.swing.JMenuItem MnLaporanRekapPenyakitRalan;
    private javax.swing.JMenuItem MnLaporanRekapRawatDarurat;
    private javax.swing.JMenuItem MnLaporanStatistikIGD;
    private javax.swing.JMenuItem MnLembarCasemix;
    private javax.swing.JMenuItem MnMati;
    private javax.swing.JMenuItem MnPeniliaianAwalKeperawatanRalan;
    private javax.swing.JMenu MnPermintaan;
    private javax.swing.JMenuItem MnPermintaanLab;
    private javax.swing.JMenuItem MnPermintaanRadiologi;
    private javax.swing.JMenuItem MnPersetujuanMedis;
    private javax.swing.JMenuItem MnPrinterBaru;
    private javax.swing.JMenuItem MnPrinterBaru1;
    private javax.swing.JMenuItem MnPrinterLama;
    private javax.swing.JMenuItem MnPrinterLama1;
    private javax.swing.JMenu MnRekamMedis;
    private javax.swing.JMenuItem MnRujuk;
    private javax.swing.JMenuItem MnRujukMasuk;
    private javax.swing.JMenuItem MnRujukSisrute;
    private javax.swing.JMenu MnRujukan;
    private javax.swing.JMenuItem MnSEP;
    private javax.swing.JMenuItem MnSEPJamkesda;
    private javax.swing.JMenuItem MnSJP;
    private javax.swing.JMenuItem MnSKDPBPJS;
    private javax.swing.JMenuItem MnSuratAPS;
    private javax.swing.JMenuItem MnSuratTindakanDokter;
    private javax.swing.JMenuItem MnTindakanRawatJalan;
    private javax.swing.JMenuItem MnlembarCover;
    private javax.swing.JMenuItem MnlembarLabel;
    private javax.swing.JMenuItem MnlembarRM5;
    private javax.swing.JMenuItem MnlembarRM51;
    private javax.swing.JMenuItem MnlembarRM52;
    private javax.swing.JMenuItem MnlembarRM521;
    private javax.swing.JMenuItem MnlembarRM6;
    private widget.TextBox NoBalasan;
    private widget.TextBox NomorSurat;
    private javax.swing.JPanel PanelInput;
    private widget.ScrollPane Scroll;
    private widget.ScrollPane Scroll5;
    private widget.TextBox TAlmt;
    private widget.TextBox TCari;
    private widget.TextBox TCari1;
    private widget.TextBox TDokter;
    private widget.TextBox THbngn;
    private widget.TextBox TNoID;
    public widget.TextBox TNoRM;
    private widget.TextBox TNoReg;
    private widget.TextBox TNoRw;
    private widget.TextBox TPasien;
    private widget.TextBox TPngJwb;
    private widget.TextBox TStatus;
    private widget.Tanggal TglMati;
    private widget.Tanggal TglSakit1;
    private widget.Tanggal TglSakit2;
    private widget.Tanggal TglSurat;
    private widget.Tanggal TglSurat1;
    public widget.Tanggal TglSuratTindakan;
    private widget.TextBox alamatPJ;
    private widget.TextBox alasanAPS;
    public widget.TextBox alasanTolak;
    private widget.Button btnKab;
    private widget.Button btnKec;
    private widget.Button btnKel;
    private widget.Button btnPenjab;
    private widget.Button btnPenjab1;
    private javax.swing.ButtonGroup buttonGroup1;
    private widget.TextBox cekDataPersalinan;
    private widget.CekBox cekTglMati;
    private widget.ComboBox cmbDtk;
    private widget.ComboBox cmbJam;
    private widget.ComboBox cmbKet;
    private widget.ComboBox cmbMnt;
    private widget.ComboBox cmbNonTrauma;
    private widget.ComboBox cmbTindakan;
    private widget.ComboBox cmbTrauma;
    private widget.ComboBox hubungan;
    private widget.InternalFrame internalFrame1;
    private widget.InternalFrame internalFrame16;
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
    private widget.Label jLabel15;
    private widget.Label jLabel17;
    private widget.Label jLabel18;
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
    private widget.Label jLabel6;
    private widget.Label jLabel7;
    private widget.Label jLabel8;
    private widget.Label jLabel98;
    private javax.swing.JMenu jMenu1;
    private javax.swing.JMenu jMenu2;
    private javax.swing.JMenu jMenu3;
    private javax.swing.JMenu jMenu4;
    private javax.swing.JMenu jMenu5;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JPopupMenu jPopupMenu1;
    private javax.swing.JSeparator jSeparator1;
    private widget.TextBox jam;
    private widget.ComboBox jkPJ;
    public widget.ComboBox jnsSurat;
    private widget.TextBox kdbahasa;
    private widget.TextBox kddokter;
    private widget.TextBox kdpnj;
    private widget.TextBox kdsuku;
    private widget.TextBox kelaminPersalinan;
    private widget.TextBox ket;
    private widget.TextBox ketAPS;
    private widget.TextBox kode_rujukanya;
    private widget.TextBox lmsakit;
    public widget.TextBox nmPJ;
    private widget.TextBox nmbahasa;
    private widget.TextBox nmkab;
    private widget.TextBox nmkec;
    private widget.TextBox nmkel;
    private widget.TextBox nmpnj;
    private widget.TextBox nmsuku;
    private widget.TextBox noRawatDataIGD;
    private widget.TextBox noSrt;
    private widget.TextBox noSrt1;
    public widget.TextBox norwSurat;
    private widget.PanelBiasa panelBiasa2;
    private widget.PanelBiasa panelBiasa3;
    private widget.PanelBiasa panelBiasa4;
    private widget.panelisi panelGlass6;
    private widget.panelisi panelGlass7;
    private javax.swing.JMenuItem ppGrafikDemografi;
    private javax.swing.JMenuItem ppGrafikPerAgama;
    private javax.swing.JMenuItem ppGrafikPerBulan;
    private javax.swing.JMenuItem ppGrafikPerJK;
    private javax.swing.JMenuItem ppGrafikPerPekerjaan;
    private javax.swing.JMenuItem ppGrafikPerTahun;
    private javax.swing.JMenuItem ppGrafikPerTanggal;
    private javax.swing.JMenuItem ppGrafikPerdokter;
    private javax.swing.JMenuItem ppGrafikPerpoli;
    private javax.swing.JMenuItem ppPasienCorona;
    private javax.swing.JMenuItem ppPerawatanCorona;
    private javax.swing.JMenuItem ppRiwayat;
    private widget.TextBox rmMati;
    private widget.TextBox sepJkd;
    private widget.TextBox sepJmp;
    private widget.Table tbAPS;
    private widget.Table tbregistrasiIGD;
    private widget.TextBox tgl;
    private widget.TextBox tindakanDokter;
    private widget.Label tulisan_tanggal;
    private widget.TextBox umurDaftar;
    private widget.TextBox umurPJ;
    private widget.TextBox unitIGD;
    // End of variables declaration//GEN-END:variables

    public void tampil() {
        Valid.tabelKosong(tabMode);
        try {
            ps.setString(1, Valid.SetTgl(DTPCari1.getSelectedItem() + ""));
            ps.setString(2, Valid.SetTgl(DTPCari2.getSelectedItem() + ""));
            ps.setString(3, "%" + TCari.getText().trim() + "%");
            ps.setString(4, Valid.SetTgl(DTPCari1.getSelectedItem() + ""));
            ps.setString(5, Valid.SetTgl(DTPCari2.getSelectedItem() + ""));
            ps.setString(6, "%" + TCari.getText().trim() + "%");
            ps.setString(7, Valid.SetTgl(DTPCari1.getSelectedItem() + ""));
            ps.setString(8, Valid.SetTgl(DTPCari2.getSelectedItem() + ""));
            ps.setString(9, "%" + TCari.getText().trim() + "%");
            ps.setString(10, Valid.SetTgl(DTPCari1.getSelectedItem() + ""));
            ps.setString(11, Valid.SetTgl(DTPCari2.getSelectedItem() + ""));
            ps.setString(12, "%" + TCari.getText().trim() + "%");
            ps.setString(13, Valid.SetTgl(DTPCari1.getSelectedItem() + ""));
            ps.setString(14, Valid.SetTgl(DTPCari2.getSelectedItem() + ""));
            ps.setString(15, "%" + TCari.getText().trim() + "%");
            ps.setString(16, Valid.SetTgl(DTPCari1.getSelectedItem() + ""));
            ps.setString(17, Valid.SetTgl(DTPCari2.getSelectedItem() + ""));
            ps.setString(18, "%" + TCari.getText().trim() + "%");
            ps.setString(19, Valid.SetTgl(DTPCari1.getSelectedItem() + ""));
            ps.setString(20, Valid.SetTgl(DTPCari2.getSelectedItem() + ""));
            ps.setString(21, "%" + TCari.getText().trim() + "%");
            ps.setString(22, Valid.SetTgl(DTPCari1.getSelectedItem() + ""));
            ps.setString(23, Valid.SetTgl(DTPCari2.getSelectedItem() + ""));
            ps.setString(24, "%" + TCari.getText().trim() + "%");
            ps.setString(25, Valid.SetTgl(DTPCari1.getSelectedItem() + ""));
            ps.setString(26, Valid.SetTgl(DTPCari2.getSelectedItem() + ""));
            ps.setString(27, "%" + TCari.getText().trim() + "%");
            ps.setString(28, Valid.SetTgl(DTPCari1.getSelectedItem() + ""));
            ps.setString(29, Valid.SetTgl(DTPCari2.getSelectedItem() + ""));
            ps.setString(30, "%" + TCari.getText().trim() + "%");
            ps.setString(31, Valid.SetTgl(DTPCari1.getSelectedItem() + ""));
            ps.setString(32, Valid.SetTgl(DTPCari2.getSelectedItem() + ""));
            ps.setString(33, "%" + TCari.getText().trim() + "%");
            ps.setString(34, Valid.SetTgl(DTPCari1.getSelectedItem() + ""));
            ps.setString(35, Valid.SetTgl(DTPCari2.getSelectedItem() + ""));
            ps.setString(36, "%" + TCari.getText().trim() + "%");
            ps.setString(37, Valid.SetTgl(DTPCari1.getSelectedItem() + ""));
            ps.setString(38, Valid.SetTgl(DTPCari2.getSelectedItem() + ""));
            ps.setString(39, "%" + TCari.getText().trim() + "%");            
            ps.setString(40, Valid.SetTgl(DTPCari1.getSelectedItem() + ""));
            ps.setString(41, Valid.SetTgl(DTPCari2.getSelectedItem() + ""));
            ps.setString(42, "%" + TCari.getText().trim() + "%");            
            ps.setString(43, Valid.SetTgl(DTPCari1.getSelectedItem() + ""));
            ps.setString(44, Valid.SetTgl(DTPCari2.getSelectedItem() + ""));
            ps.setString(45, "%" + TCari.getText().trim() + "%");            
            ps.setString(46, Valid.SetTgl(DTPCari1.getSelectedItem() + ""));
            ps.setString(47, Valid.SetTgl(DTPCari2.getSelectedItem() + ""));
            ps.setString(48, "%" + TCari.getText().trim() + "%");
            rs = ps.executeQuery();
            while (rs.next()) {
                tabMode.addRow(new Object[]{
                    false, 
                    rs.getString("no_reg"),
                    rs.getString("no_rawat"),
                    rs.getString("tgl_registrasi"),
                    rs.getString("jam_reg"),
                    rs.getString("kd_dokter"),
                    rs.getString("nm_dokter"),
                    rs.getString("no_rkm_medis"),
                    rs.getString("nm_pasien"),
                    rs.getString("jk"),
                    rs.getString("umur"),
                    rs.getString("png_jawab"),
                    rs.getString("nosep"),
                    rs.getString("stts"),
                    rs.getString("stts_daftar"),
                    rs.getString("nm_poli"),
                    rs.getString("p_jawab"),
                    rs.getString("almt_pj"),
                    rs.getString("hubunganpj"),
                    Valid.SetAngka(rs.getDouble("biaya_reg")),
                    rs.getString("datang_sendiri"),
                    rs.getString("trauma"),
                    rs.getString("non_trauma"),
                    rs.getString("tindakan_lanjut"),
                    rs.getString("ket_igd"),
                    rs.getString("umurdaftar")
                });
            }
        } catch (Exception e) {
            System.out.println("Notifikasi : " + e);
        }
        LCount.setText("" + tabMode.getRowCount());
    }

    public void emptTeks() {
        TNoReg.setText("");
        TNoRw.setText("");
        Kd2.setText("");
        tulisan_tanggal.setText(Sequel.cariIsi("SELECT DATE_FORMAT(NOW(),'%d')") + " "
                + Sequel.bulanINDONESIA("SELECT DATE_FORMAT(NOW(),'%m')") + " "
                + Sequel.cariIsi("SELECT DATE_FORMAT(NOW(),'%Y')"));
        tglDaftar = Sequel.cariIsi("SELECT DATE(NOW()) tgl_daftar");
        tglnoRW = Sequel.cariIsi("SELECT DATE_FORMAT(NOW(),'%Y/%m/%d') tgl_daftar");
        AsalRujukan.setText("");
        alamatperujuk = "";
        TNoID.setText("");
        TNoRM.setText("");
        TPasien.setText("");
        TPngJwb.setText("");
        THbngn.setText("");
        TAlmt.setText("");
        TStatus.setText("");
        isNumber();
        TNoID.requestFocus();
        kdpnj.setText("");
        nmpnj.setText("");        
        unitIGD.setText("");
        JnsnoID.setSelectedIndex(0);

        cmbTindakan.setSelectedIndex(0);
        cmbTrauma.setSelectedIndex(0);
        ChkDtgSendiri.setSelected(false);
        ChkDtgSendiri.setText("Pasien TIDAK Datang Sendiri");
        KataDatang.setText("Tidak");
        noSrt.setText("");
        noSrt1.setText("");
        TglSurat.setDate(new Date());
        TglSurat1.setDate(new Date());
        sepJkd.setText("");
        sepJmp.setText("");
        DlgJamkesda.setVisible(false);
        DlgJampersal.setVisible(false);
        cmbNonTrauma.setSelectedIndex(0);
        cmbKet.setSelectedIndex(0);
        noRawatDataIGD.setText("");
        rmMati.setText("");
        kode_rujukanya.setText("");
        cekTglMati.setSelected(false);
        
        cmbTrauma.setEditable(false);
        cmbNonTrauma.setEditable(false);
        cmbTindakan.setEditable(false);
        cmbKet.setEditable(false);
        kdsuku.setText("");
        nmsuku.setText("");
        kdbahasa.setText("");
        nmbahasa.setText("");
        umurDaftar.setText("");
        kelaminPersalinan.setText("");
        cekDataPersalinan.setText("");
        
        alasanAPS.setText("");
        ketAPS.setText("");
    }

    private void getData() {
        norawatAPS = "";
        kdAPS = "";
        tglDaftar = "";
                
        if (tbregistrasiIGD.getSelectedRow() != -1) {
            TNoReg.setText(tbregistrasiIGD.getValueAt(tbregistrasiIGD.getSelectedRow(), 1).toString());
            TNoRw.setText(tbregistrasiIGD.getValueAt(tbregistrasiIGD.getSelectedRow(), 2).toString());
            norwSurat.setText(tbregistrasiIGD.getValueAt(tbregistrasiIGD.getSelectedRow(), 2).toString());
            norawatAPS = TNoRw.getText();
            Kd2.setText(tbregistrasiIGD.getValueAt(tbregistrasiIGD.getSelectedRow(), 2).toString());
            tglDaftar = tbregistrasiIGD.getValueAt(tbregistrasiIGD.getSelectedRow(), 3).toString();
//            Valid.SetTgl(TglReg, tbregistrasiIGD.getValueAt(tbregistrasiIGD.getSelectedRow(), 3).toString());
            kddokter.setText(tbregistrasiIGD.getValueAt(tbregistrasiIGD.getSelectedRow(), 5).toString());
            TDokter.setText(tbregistrasiIGD.getValueAt(tbregistrasiIGD.getSelectedRow(), 6).toString());
            TNoID.setText(tbregistrasiIGD.getValueAt(tbregistrasiIGD.getSelectedRow(), 7).toString());
            TNoRM.setText(tbregistrasiIGD.getValueAt(tbregistrasiIGD.getSelectedRow(), 7).toString());
            TPasien.setText(tbregistrasiIGD.getValueAt(tbregistrasiIGD.getSelectedRow(), 8).toString());
            unitIGD.setText(tbregistrasiIGD.getValueAt(tbregistrasiIGD.getSelectedRow(), 15).toString());
            isPas();
            TPngJwb.setText(tbregistrasiIGD.getValueAt(tbregistrasiIGD.getSelectedRow(), 16).toString());
            TAlmt.setText(tbregistrasiIGD.getValueAt(tbregistrasiIGD.getSelectedRow(), 17).toString());
            THbngn.setText(tbregistrasiIGD.getValueAt(tbregistrasiIGD.getSelectedRow(), 18).toString());
            TStatus.setText(tbregistrasiIGD.getValueAt(tbregistrasiIGD.getSelectedRow(), 14).toString());
            nmpnj.setText(tbregistrasiIGD.getValueAt(tbregistrasiIGD.getSelectedRow(), 11).toString());
            cmbTrauma.setSelectedItem(tbregistrasiIGD.getValueAt(tbregistrasiIGD.getSelectedRow(), 21).toString());
            cmbNonTrauma.setSelectedItem(tbregistrasiIGD.getValueAt(tbregistrasiIGD.getSelectedRow(), 22).toString());
            cmbTindakan.setSelectedItem(tbregistrasiIGD.getValueAt(tbregistrasiIGD.getSelectedRow(), 23).toString());
            cmbKet.setSelectedItem(tbregistrasiIGD.getValueAt(tbregistrasiIGD.getSelectedRow(), 24).toString());
            umurDaftar.setText(tbregistrasiIGD.getValueAt(tbregistrasiIGD.getSelectedRow(), 25).toString());
            AsalRujukan.setText(Sequel.cariIsi("select perujuk from rujuk_masuk where no_rawat='" + TNoRw.getText() + "'"));
            kode_rujukanya.setText(Sequel.cariIsi("select kd_rujukan from rujuk_masuk where no_rawat='" + TNoRw.getText() + "'"));
            JnsnoID.setSelectedIndex(0);
            Sequel.cariIsi("select suku_bangsa from pasien where no_rkm_medis=?", kdsuku, TNoRM.getText());
            Sequel.cariIsi("select bahasa_pasien from pasien where no_rkm_medis=?", kdbahasa, TNoRM.getText());
            Sequel.cariIsi("select nama_suku_bangsa from suku_bangsa where id=?", nmsuku, kdsuku.getText());
            Sequel.cariIsi("select nama_bahasa from bahasa_pasien where id=?", nmbahasa, kdbahasa.getText());            
            Sequel.cariIsi("select nm_alasan from ralan_aps ra inner join master_aps ma on ma.kd_aps=ra.kd_aps where ra.no_rawat=?", alasanAPS, TNoRw.getText());
            Sequel.cariIsi("select keterangan from ralan_aps where no_rawat=?", ketAPS, TNoRw.getText());
            kdAPS = Sequel.cariIsi("select kd_aps from ralan_aps where no_rawat='" + TNoRw.getText() + "'");
            noRawatDataIGD.setText(Sequel.cariIsi("select no_rawat from data_igd where no_rawat='" + TNoRw.getText() + "' "));
            
            Valid.SetTgl(TglSurat, Sequel.cariIsi("SELECT tgl_surat FROM bridging_jamkesda WHERE no_rawat='" + TNoRw.getText() + "' AND jns_rawat='Jalan IGD' "));
            Valid.SetTgl(TglSurat1, Sequel.cariIsi("SELECT tgl_surat FROM bridging_jampersal WHERE no_rawat='" + TNoRw.getText() + "' AND jns_rawat='Jalan IGD' "));
            noSrt.setText(Sequel.cariIsi("SELECT no_surat FROM bridging_jamkesda WHERE no_rawat='" + TNoRw.getText() + "' AND jns_rawat='Jalan IGD'"));
            noSrt1.setText(Sequel.cariIsi("SELECT no_surat FROM bridging_jampersal WHERE no_rawat='" + TNoRw.getText() + "' AND jns_rawat='Jalan IGD'"));            
            sepJkd.setText(Sequel.cariIsi("SELECT no_sep FROM bridging_jamkesda WHERE no_rawat='" + TNoRw.getText() + "' AND jns_rawat='Jalan IGD'"));
            sepJmp.setText(Sequel.cariIsi("SELECT no_sep FROM bridging_jampersal WHERE no_rawat='" + TNoRw.getText() + "' AND jns_rawat='Jalan IGD'"));

            if (noRawatDataIGD.getText().equals("")) {
                ChkDtgSendiri.setSelected(false);
                ChkDtgSendiri.setText("Pasien TIDAK Datang Sendiri");
                cmbTrauma.setSelectedIndex(0);
                cmbNonTrauma.setSelectedIndex(0);
                cmbTindakan.setSelectedIndex(0);
                cmbKet.setSelectedIndex(0);
            } else if (noRawatDataIGD.getText() != "") {                              
                KataDatang.setText(Sequel.cariIsi("select datang_sendiri from data_igd where no_rawat='" + TNoRw.getText() + "' "));

                if (KataDatang.getText().equals("Tidak")) {
                    ChkDtgSendiri.setSelected(false);
                } else {
                    ChkDtgSendiri.setSelected(true);
                }
                
            }

            if (noSrt.getText().equals("")) {
                tbregistrasiIGD.requestFocus();
            } else {
                noSrt.setText(Sequel.cariIsi("SELECT no_surat FROM bridging_jamkesda WHERE no_rawat='" + TNoRw.getText() + "' AND jns_rawat='Jalan IGD'"));
                sepJkd.setText(Sequel.cariIsi("SELECT no_sep FROM bridging_jamkesda WHERE no_rawat='" + TNoRw.getText() + "' AND jns_rawat='Jalan IGD'"));
            }
            
            if (noSrt1.getText().equals("")) {
                tbregistrasiIGD.requestFocus();
            } else {
                noSrt1.setText(Sequel.cariIsi("SELECT no_surat FROM bridging_jampersal WHERE no_rawat='" + TNoRw.getText() + "' AND jns_rawat='Jalan IGD'"));            
                sepJmp.setText(Sequel.cariIsi("SELECT no_sep FROM bridging_jampersal WHERE no_rawat='" + TNoRw.getText() + "' AND jns_rawat='Jalan IGD'"));
            }

            Sequel.cariIsi("select kd_pj from reg_periksa where no_rawat=?", kdpnj, tbregistrasiIGD.getValueAt(tbregistrasiIGD.getSelectedRow(), 2).toString());
        }
    }

    private void isPas() {
        if (validasiregistrasi.equals("Yes")) {
            if (Sequel.cariInteger("select count(no_rkm_medis) from reg_periksa where no_rkm_medis=? and (stts='Sudah' or stts='Belum') ", TNoRM.getText()) > 0) {
                JOptionPane.showMessageDialog(rootPane, "Maaf, pasien pada kunjungan sebelumnya memiliki tagihan yang belum di closing. Silahkan konfirmasi dengan pihak kasir.. !!");
            } else {
                if (Sequel.cariInteger("select count(no_rkm_medis) from catatan_pasien where no_rkm_medis=?", TNoRM.getText()) > 0) {
                    this.setCursor(Cursor.getPredefinedCursor(Cursor.WAIT_CURSOR));
                    DlgCatatan catatan = new DlgCatatan(null, true);
                    catatan.setNoRm(TNoID.getText());
                    catatan.setSize(720, 330);
                    catatan.setLocationRelativeTo(internalFrame1);
                    catatan.setVisible(true);
                    this.setCursor(Cursor.getDefaultCursor());
                }
                isCekPasien();
            }
        } else {
            isCekPasien();
        }
    }

    private void isCekPasien() {
        try {

            if (JnsnoID.getSelectedItem().equals("No. Rekam Medik")) {
                ps3 = koneksi.prepareStatement(" SELECT p.no_rkm_medis, p.nm_pasien, concat(p.alamat,', ',kl.nm_kel,', ',kc.nm_kec,', ',kb.nm_kab) asal, "
                        + " p.namakeluarga, p.keluarga, p.kd_pj, pj.png_jawab, IF (p.tgl_daftar =?, 'Baru', 'Lama') AS daftar, "
                        + " TIMESTAMPDIFF(YEAR, tgl_lahir, CURDATE()) as tahun, (TIMESTAMPDIFF(MONTH, tgl_lahir, CURDATE()) - ((TIMESTAMPDIFF(MONTH, tgl_lahir, CURDATE()) div 12) * 12)) as bulan, "
                        + " TIMESTAMPDIFF(DAY, DATE_ADD(DATE_ADD(tgl_lahir,INTERVAL TIMESTAMPDIFF(YEAR, tgl_lahir, CURDATE()) YEAR), INTERVAL TIMESTAMPDIFF(MONTH, tgl_lahir, CURDATE()) - ((TIMESTAMPDIFF(MONTH, tgl_lahir, CURDATE()) div 12) * 12) MONTH), CURDATE()) as hari, "
                        + " p.suku_bangsa, p.bahasa_pasien FROM pasien p INNER JOIN kelurahan kl ON kl.kd_kel=p.kd_kel "
                        + " INNER JOIN kecamatan kc on kc.kd_kec=p.kd_kec INNER JOIN kabupaten kb on kb.kd_kab=p.kd_kab "
                        + " INNER JOIN penjab pj on pj.kd_pj=p.kd_pj where p.no_rkm_medis=?");
                
            } else if (JnsnoID.getSelectedItem().equals("No. Kartu BPJS")) {
                ps3 = koneksi.prepareStatement(" SELECT p.no_rkm_medis, p.nm_pasien, concat(p.alamat,', ',kl.nm_kel,', ',kc.nm_kec,', ',kb.nm_kab) asal, "
                        + " p.namakeluarga, p.keluarga, p.kd_pj, pj.png_jawab, IF (p.tgl_daftar =?, 'Baru', 'Lama') AS daftar, "
                        + " TIMESTAMPDIFF(YEAR, tgl_lahir, CURDATE()) as tahun, (TIMESTAMPDIFF(MONTH, tgl_lahir, CURDATE()) - ((TIMESTAMPDIFF(MONTH, tgl_lahir, CURDATE()) div 12) * 12)) as bulan, "
                        + " TIMESTAMPDIFF(DAY, DATE_ADD(DATE_ADD(tgl_lahir,INTERVAL TIMESTAMPDIFF(YEAR, tgl_lahir, CURDATE()) YEAR), INTERVAL TIMESTAMPDIFF(MONTH, tgl_lahir, CURDATE()) - ((TIMESTAMPDIFF(MONTH, tgl_lahir, CURDATE()) div 12) * 12) MONTH), CURDATE()) as hari, "
                        + " p.suku_bangsa, p.bahasa_pasien FROM pasien p INNER JOIN kelurahan kl ON kl.kd_kel=p.kd_kel "
                        + " INNER JOIN kecamatan kc on kc.kd_kec=p.kd_kec INNER JOIN kabupaten kb on kb.kd_kab=p.kd_kab "
                        + " INNER JOIN penjab pj on pj.kd_pj=p.kd_pj where p.kd_pj='B01' and p.no_peserta=?");

            } else if (JnsnoID.getSelectedItem().equals("NIK KTP")) {
                ps3 = koneksi.prepareStatement(" SELECT p.no_rkm_medis, p.nm_pasien, concat(p.alamat,', ',kl.nm_kel,', ',kc.nm_kec,', ',kb.nm_kab) asal, "
                        + " p.namakeluarga, p.keluarga, p.kd_pj, pj.png_jawab, IF (p.tgl_daftar =?, 'Baru', 'Lama') AS daftar, "
                        + " TIMESTAMPDIFF(YEAR, tgl_lahir, CURDATE()) as tahun, (TIMESTAMPDIFF(MONTH, tgl_lahir, CURDATE()) - ((TIMESTAMPDIFF(MONTH, tgl_lahir, CURDATE()) div 12) * 12)) as bulan, "
                        + " TIMESTAMPDIFF(DAY, DATE_ADD(DATE_ADD(tgl_lahir,INTERVAL TIMESTAMPDIFF(YEAR, tgl_lahir, CURDATE()) YEAR), INTERVAL TIMESTAMPDIFF(MONTH, tgl_lahir, CURDATE()) - ((TIMESTAMPDIFF(MONTH, tgl_lahir, CURDATE()) div 12) * 12) MONTH), CURDATE()) as hari, "
                        + " p.suku_bangsa, p.bahasa_pasien FROM pasien p INNER JOIN kelurahan kl ON kl.kd_kel=p.kd_kel "
                        + " INNER JOIN kecamatan kc on kc.kd_kec=p.kd_kec INNER JOIN kabupaten kb on kb.kd_kab=p.kd_kab "
                        + " INNER JOIN penjab pj on pj.kd_pj=p.kd_pj where p.no_ktp=?");
            }
            
            try {
                ps3.setString(1, tglDaftar);
                ps3.setString(2, TNoID.getText());

                rs = ps3.executeQuery();
                while (rs.next()) {
                    TNoRM.setText(rs.getString("no_rkm_medis"));
                    TPasien.setText(rs.getString("nm_pasien"));
                    TAlmt.setText(rs.getString("asal"));
                    TPngJwb.setText(rs.getString("namakeluarga"));
                    THbngn.setText(rs.getString("keluarga"));
                    kdpnj.setText(rs.getString("kd_pj"));
                    nmpnj.setText(rs.getString("png_jawab"));
                    TStatus.setText(rs.getString("daftar"));
                    kdsuku.setText(rs.getString("suku_bangsa"));
                    kdbahasa.setText(rs.getString("bahasa_pasien"));
                    Sequel.cariIsi("select nama_suku_bangsa from suku_bangsa where id=?", nmsuku, kdsuku.getText());
                    Sequel.cariIsi("select nama_bahasa from bahasa_pasien where id=?", nmbahasa, kdbahasa.getText());
                    
                    umur = "0";
                    sttsumur = "Th";
                    cekUmur = 0;
                    sttsumur1 = "";
                    if (rs.getInt("tahun") > 0) {
                        cekUmur = rs.getInt("tahun");
                        sttsumur1 = "Tahun.";
                        umur = rs.getString("tahun");
                        sttsumur = "Th";
                    } else if (rs.getInt("tahun") == 0) {
                        if (rs.getInt("bulan") > 0) {
                            cekUmur = rs.getInt("bulan");
                            sttsumur1 = "Bulan.";
                            umur = rs.getString("bulan");
                            sttsumur = "Bl";
                        } else if (rs.getInt("bulan") == 0) {
                            cekUmur = rs.getInt("hari");
                            sttsumur1 = "Hari.";
                            umur = rs.getString("hari");
                            sttsumur = "Hr";
                        }
                    }
                }
            } catch (Exception ex) {
                System.out.println(ex);
            } finally {
                if (rs != null) {
                    rs.close();
                }

                if (ps3 != null) {
                    ps3.close();
                }
            }
        } catch (Exception e) {
            System.out.println(e);
        }
    }

    public JTextField getTextField() {
        return TNoRw;
    }

    public JButton getButton() {
        return BtnKeluar;
    }

    private void isForm() {
        if (ChkInput.isSelected() == true) {
            ChkInput.setVisible(false);
            PanelInput.setPreferredSize(new Dimension(WIDTH, 245));
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
        tulisan_tanggal.setText(Sequel.cariIsi("SELECT DATE_FORMAT(NOW(),'%d')") + " "
                + Sequel.bulanINDONESIA("SELECT DATE_FORMAT(NOW(),'%m')") + " "
                + Sequel.cariIsi("SELECT DATE_FORMAT(NOW(),'%Y')"));
        tglDaftar = Sequel.cariIsi("SELECT DATE(NOW()) tgl_daftar");
        tglnoRW = Sequel.cariIsi("SELECT DATE_FORMAT(NOW(),'%Y/%m/%d') tgl_daftar");
        DTPCari1.setDate(new Date());
        DTPCari2.setDate(new Date());
        BtnSimpan.setEnabled(var.getigd());
        BtnHapus.setEnabled(var.getigd());
        BtnEdit.setEnabled(var.getigd());
        BtnPrint.setEnabled(var.getigd());
        MnKamarInap.setEnabled(var.getkamar_inap());
        MnTindakanRawatJalan.setEnabled(var.gettindakan_ralan());
        MnBilling.setEnabled(var.getbilling_ralan());
        MnRujuk.setEnabled(var.getrujukan_keluar());
        MnRujukMasuk.setEnabled(var.getrujukan_masuk());
        MnDiagnosa.setEnabled(var.getdiagnosa_pasien());
        MnCatatanPasien.setEnabled(var.getcatatan_pasien());
        MnDataHAIs.setEnabled(var.getdata_HAIs());
        MnPeniliaianAwalKeperawatanRalan.setEnabled(var.getpenilaian_awal_keperawatan_ralan());
        MnDataTriaseIGD.setEnabled(var.getdata_triase_igd());
        ppPasienCorona.setEnabled(var.getpasien_corona());
        ppPerawatanCorona.setEnabled(var.getpasien_corona());
    }

    private void isNumber() {
        Valid.autoNomer3("select ifnull(MAX(CONVERT(RIGHT(rujuk_masuk.no_rawat,4),signed)),0) from reg_periksa inner join rujuk_masuk on reg_periksa.no_rawat=rujuk_masuk.no_rawat where reg_periksa.tgl_registrasi='" + tglDaftar + "' ", "BR/" + tglnoRW + "/", 4, NoBalasan);
        switch (URUTNOREG) {
            case "poli":
                Valid.autoNomer3("select ifnull(MAX(CONVERT(no_reg,signed)),0) from reg_periksa where kd_poli='IGDK' and tgl_registrasi='" + tglDaftar + "'", "", 3, TNoReg);
                break;
            case "dokter":
                Valid.autoNomer3("select ifnull(MAX(CONVERT(no_reg,signed)),0) from reg_periksa where kd_dokter='" + kddokter.getText() + "' and tgl_registrasi='" + tglDaftar + "'", "", 3, TNoReg);
                break;
            case "dokter & poli":
                Valid.autoNomer3("select ifnull(MAX(CONVERT(no_reg,signed)),0) from reg_periksa where kd_dokter='" + kddokter.getText() + "' and kd_poli='IGDK' and tgl_registrasi='" + tglDaftar + "'", "", 3, TNoReg);
                break;
            default:
                Valid.autoNomer3("select ifnull(MAX(CONVERT(no_reg,signed)),0) from reg_periksa where kd_dokter='" + kddokter.getText() + "' and tgl_registrasi='" + tglDaftar + "'", "", 3, TNoReg);
                break;
        }
        if (Kd2.getText().equals("")) {
            Valid.autoNomer3("select ifnull(MAX(CONVERT(RIGHT(no_rawat,6),signed)),0) from reg_periksa where tgl_registrasi='" + tglDaftar + "' ", tglnoRW + "/", 6, TNoRw);
        }
    }

    public void ctk() {
        try {
            String os = System.getProperty("os.name").toLowerCase();
            Runtime rt = Runtime.getRuntime();
            FileWriter writer = null;
            if (os.contains("win")) {
                writer = new FileWriter("//" + IPPRINTERTRACER);
            } else if (os.contains("mac")) {
                writer = new FileWriter("smb://" + IPPRINTERTRACER);
            } else if (os.contains("nix") || os.contains("nux")) {
                writer = new FileWriter("smb://" + IPPRINTERTRACER);
            }
            writer.write(".: TRACER :.");
            cetakStruk("Draft Sans Serif Condensed", writer,
                    MODE_DRAFT,
                    FONT_SANS_SERIF,
                    CONDENSED_ON,
                    SIZE_12_CPI,
                    SPACING_12_72);
            sendCommand(RESET, writer);
            writer.close();
        } catch (Exception ex) {
            System.out.println("Notif Writer 3 : " + ex);
        }
    }

    private void cetakStruk(String title, FileWriter writer, char[]... mode) throws IOException {
        sendCommand(RESET, writer);
        for (int i = 0; i < mode.length; i++) {
            char[] cmd = mode[i];
            sendCommand(cmd, writer);
        }

        cetakStruk2(title, writer);
        sendCommand(VERTICAL_PRINT_POSITION, writer);
    }

    public void sendCommand(char[] command, Writer writer) throws IOException {
        writer.write(command);
    }

    private void cetakStruk2(String title, FileWriter writer) {
        String strukFile = "tracerRm.txt";
        BufferedReader reader;
        try {
            reader = new BufferedReader(new FileReader(strukFile));
            String tgll = Sequel.cariIsi("select tgl_registrasi from reg_periksa where no_rawat='" + TNoRw.getText() + "'");
            String[] tglref = tgll.split("-");
            boltText(writer);
            writer.write(".: TRACER :.");
            boltTextOff(writer);
            gantiBaris(writer);
            writer.write("No. RM      : ");
            boltText(writer);
            writer.write(TNoID.getText());
            boltTextOff(writer);
            gantiBaris(writer);
            writer.write("Nama Pasien : ");
            boltText(writer);
            writer.write(TPasien.getText());
            boltTextOff(writer);
            gantiBaris(writer);
            writer.write("Tgl. Daftar : ");
            boltText(writer);
            writer.write(tglref[2] + "-" + tglref[1] + "-" + tglref[0] + "/" +Sequel.cariIsi("SELECT TIME(NOW()) jam"));
            gantiBaris(writer);
            boltTextOff(writer);
            writer.write("Ruangan     : ");
            boltText(writer);
            writer.write(Sequel.cariIsi("select nm_poli from poliklinik where kd_poli='IGDK'"));
            boltTextOff(writer);

            gantiBaris(writer);
            gantiBaris(writer);
            gantiBaris(writer);
            reader.close();
        } catch (Exception ex) {
            System.out.println("Notif : " + ex);
        }
    }

    private void boltText(Writer writer) {
        try {
            writer.write(ESC);
            writer.write((char) 14);
            writer.write(ESC);
            writer.write('E');
        } catch (Exception e) {
            System.out.println("Notif : " + e);
        }
    }

    private void boltTextOff(Writer writer) {
        try {
            writer.write(ESC);
            writer.write('F');
        } catch (Exception e) {
            System.out.println("Notif : " + e);
        }
    }

    private void gantiBaris(Writer writer) {
        try {
            writer.write("\n");
        } catch (Exception e) {
            System.out.println("Notif : " + e);
        }
    }

    private void UpdateUmur() {
        Sequel.mengedit("pasien", "no_rkm_medis=?", "umur=CONCAT(CONCAT(CONCAT(TIMESTAMPDIFF(YEAR, tgl_lahir, CURDATE()), ' Th '),CONCAT(TIMESTAMPDIFF(MONTH, tgl_lahir, CURDATE()) - ((TIMESTAMPDIFF(MONTH, tgl_lahir, CURDATE()) div 12) * 12), ' Bl ')),CONCAT(TIMESTAMPDIFF(DAY, DATE_ADD(DATE_ADD(tgl_lahir,INTERVAL TIMESTAMPDIFF(YEAR, tgl_lahir, CURDATE()) YEAR), INTERVAL TIMESTAMPDIFF(MONTH, tgl_lahir, CURDATE()) - ((TIMESTAMPDIFF(MONTH, tgl_lahir, CURDATE()) div 12) * 12) MONTH), CURDATE()), ' Hr'))", 1, new String[]{TNoID.getText()});
    }

    private void cekDaftar() {
        cek = Sequel.cekIGD("select count(1) from reg_periksa where tgl_registrasi = ? and kd_dokter = ? and no_rkm_medis = ? and kd_poli = ?", tglDaftar, kddokter.getText(), TNoID.getText(), "IGDK");
    }

    private Object executeQuery() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    public void bukaMenuMati() {
        rmMati.setText(Sequel.cariIsi("select no_rkm_medis from pasien_mati where no_rkm_medis='" + TNoRM.getText() + "' "));

        if (tabMode.getRowCount() == 0) {
            JOptionPane.showMessageDialog(null, "Maaf, tabel masih kosong...!!!!");
            TNoReg.requestFocus();
        } else if (TNoRM.getText().equals("") || (TPasien.getText().trim().equals(""))) {
            DlgMati.setVisible(false);
            JOptionPane.showMessageDialog(null, "Maaf, Silahkan anda pilih dulu dengan mengklik data pada tabel...!!!");
            tbregistrasiIGD.requestFocus();
        } else if (!TNoRM.getText().equals("")) {
            DlgMati.setSize(650, 125);
            DlgMati.setLocationRelativeTo(internalFrame1);
            DlgMati.setVisible(true);

            if (!rmMati.getText().equals("")) {
                ket.requestFocus();
                TglMati.setDate(new Date());
                cmbJam.setSelectedIndex(0);
                cmbMnt.setSelectedIndex(0);
                cmbDtk.setSelectedIndex(0);

                ket.setText(Sequel.cariIsi("select keterangan from pasien_mati where no_rkm_medis='" + TNoRM.getText() + "' "));
                jam.setText("Jam : " + Sequel.cariIsi("select jam from pasien_mati where no_rkm_medis='" + TNoRM.getText() + "' "));
                tgl.setText("Tgl. : " + Sequel.cariIsi("select DATE_FORMAT(tanggal,'%d-%m-%Y') tgl from pasien_mati where no_rkm_medis='" + TNoRM.getText() + "' "));
            } else {
                ket.setText("-");
                ket.requestFocus();
                TglMati.setDate(new Date());
                cmbJam.setSelectedIndex(0);
                cmbMnt.setSelectedIndex(0);
                cmbDtk.setSelectedIndex(0);
                jam.setText("");
                tgl.setText("");
            }
        }
    }

    public void editMati() {
        rmMati.setText(Sequel.cariIsi("select no_rkm_medis from pasien_mati where no_rkm_medis='" + TNoRM.getText() + "' "));

        if ((!rmMati.getText().equals("")) && ((!cmbTindakan.getSelectedItem().equals("MENINGGAL DI IGD")) && (!cmbTindakan.getSelectedItem().equals("D.O.A")))) {
            Sequel.meghapus("pasien_mati", "no_rkm_medis", TNoRM.getText());
            
        } else if ((!rmMati.getText().equals("")) && ((cmbTindakan.getSelectedItem().equals("MENINGGAL DI IGD")) || (cmbTindakan.getSelectedItem().equals("D.O.A")))) {
            Sequel.mengedit("pasien_mati", "no_rkm_medis='" + TNoRM.getText() + "'", "keterangan='" + cmbTindakan.getSelectedItem() + "', unit_asal='IGD' ");

        } else if ((rmMati.getText().equals("")) && ((cmbTindakan.getSelectedItem().equals("MENINGGAL DI IGD")) || (cmbTindakan.getSelectedItem().equals("D.O.A")))) {
            Sequel.menyimpan("pasien_mati", "'" + tglDaftar + "','" + Sequel.cariIsi("SELECT TIME(NOW()) jam") + "','"
                    + TNoRM.getText() + "','" + cmbTindakan.getSelectedItem() + "','Rumah Sakit','-','-','-','-','IGD','-'", "pasien");
        }
    }
    
    public void SetPasien(String norm,String nosisrute,String FaskesAsal){
        ChkInput.setSelected(true);
        isForm(); 
        TNoRM.setText(norm);
        this.nosisrute=nosisrute;
        AsalRujukan.setText(FaskesAsal);
        isPas();
    }
    
    public void setPasien(String NamaPasien,String Kontak,String Alamat,String TempatLahir,String TglLahir,
            String JK,String NoKartuJKN,String NIK,String nosisrute,String FaskesAsal){
        var.setform("DlgIGD");
        ChkInput.setSelected(true);
        isForm(); 
        pasien.emptTeks();
        pasien.isCek();
        this.nosisrute=nosisrute;
        AsalRujukan.setText(FaskesAsal);
        pasien.setPasien(NamaPasien, Kontak, Alamat, TempatLahir, TglLahir, JK, NoKartuJKN, NIK);
        pasien.setSize(internalFrame1.getWidth()-20,internalFrame1.getHeight()-20);
        pasien.setLocationRelativeTo(internalFrame1);
        pasien.setVisible(true);
    }
    
    private void tampilAPS() {
        Valid.tabelKosong(tabMode1);
        try {
            psAPS = koneksi.prepareStatement("SELECT kd_aps, nm_alasan FROM master_aps where "
                    + "status='1' and nm_alasan like '%" + TCari1.getText() + "%' ORDER BY kd_aps");

            try {
                rsAPS = psAPS.executeQuery();
                while (rsAPS.next()) {
                    tabMode1.addRow(new Object[]{
                        rsAPS.getString("kd_aps"),
                        rsAPS.getString("nm_alasan")
                    });
                }
            } catch (Exception e) {
                System.out.println("simrskhanza.DlgIGD.tampil() : " + e);
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
            TCari1.setText("");
            DlgAPS.dispose();
        }
    }
    
    private void editAPS() {
        cekAPS = "";
        cekAPS = Sequel.cariIsi("select no_rawat from ralan_aps where no_rawat='" + TNoRw.getText() + "'");

        if (cekAPS.equals("")) {
            Sequel.menyimpan("ralan_aps", "'" + TNoRw.getText() + "','" + kdAPS + "','" + ketAPS.getText() + "'");
        } else if (!cekAPS.equals("")) {
            if (!cmbTindakan.getSelectedItem().equals("APS")) {
                Sequel.meghapus("ralan_aps", "no_rawat", TNoRw.getText());
            } else {
                Sequel.mengedit("ralan_aps", "no_rawat='" + TNoRw.getText() + "'", "kd_aps='" + kdAPS + "',keterangan='" + ketAPS.getText() + "' ");
            }
        }    
    }
    
    private void emptSuratTindakan() {
        nmPJ.setText("");
        umurPJ.setText("");
        jkPJ.setSelectedIndex(0);
        alamatPJ.setText("");
        nmkel.setText("");
        nmkec.setText("");
        nmkab.setText("");
        hubungan.setSelectedIndex(0);
        jnsSurat.setSelectedIndex(0);
        tindakanDokter.setText("");
        ChkJln1.setSelected(true);
        
        if (jnsSurat.getSelectedItem().toString().equals("PERSETUJUAN")) {
            alasanTolak.setText("-");
            alasanTolak.setEnabled(false);
        } else {
            alasanTolak.setText("");
            alasanTolak.setEnabled(true);
        }
    }
    
    public void cekSuratTindakan(String nrSurat) {
        kdKel = "";
        kdKec = "";
        kdKab = "";
        
        try {
            psK = koneksi.prepareStatement("select tgl_surat, nm_penjab, umur_penjab, if(jk_penjab='L','Laki-laki','Perempuan') jk, alamat_penjab, "
                    + "kd_kel, kd_kec, kd_kab, hubungan_dg_pasien, jns_surat, kasus_tindakan, nm_tindakan_kedokteran, "
                    + "alasan_penolakan from surat_tindakan_kedokteran where no_rawat='" + nrSurat + "' and kasus_tindakan='Ralan'");

            try {
                rsK = psK.executeQuery();
                while (rsK.next()) {
                    nmPJ.setText(rsK.getString("nm_penjab"));
                    umurPJ.setText(rsK.getString("umur_penjab"));
                    jkPJ.setSelectedItem(rsK.getString("jk"));
                    alamatPJ.setText(rsK.getString("alamat_penjab"));
                    kdKel = rsK.getString("kd_kel");
                    kdKec = rsK.getString("kd_kec");
                    kdKab = rsK.getString("kd_kab");
                    nmkel.setText(Sequel.cariIsi("select nm_kel from kelurahan where kd_kel='" + kdKel + "'"));
                    nmkec.setText(Sequel.cariIsi("select nm_kec from kecamatan where kd_kec='" + kdKec + "'"));
                    nmkab.setText(Sequel.cariIsi("select nm_kab from kabupaten where kd_kab='" + kdKab + "'"));
                    hubungan.setSelectedItem(rsK.getString("hubungan_dg_pasien"));
                    jnsSurat.setSelectedItem(rsK.getString("jns_surat"));
                    tindakanDokter.setText(rsK.getString("nm_tindakan_kedokteran"));                    
                    alasanTolak.setText(rsK.getString("alasan_penolakan"));
                    Valid.SetTgl(TglSuratTindakan, rsK.getString("tgl_surat"));
                }
            } catch (Exception e) {
                System.out.println("Notifikasi : " + e);
            } finally {
                if (rsK != null) {
                    rsK.close();
                }
                if (psK != null) {
                    psK.close();
                }
            }
        } catch (Exception e) {
            System.out.println("Notifikasi : " + e);
        }
    }
}
