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

package bridging;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import fungsi.WarnaTable;
import fungsi.batasInput;
import fungsi.koneksiDB;
import fungsi.sekuel;
import fungsi.validasi;
import fungsi.var;
import java.awt.Cursor;
import java.awt.Dimension;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.event.WindowEvent;
import java.awt.event.WindowListener;
import java.io.FileInputStream;
import java.net.URI;
import java.security.SecureRandom;
import java.security.cert.CertificateException;
import java.security.cert.X509Certificate;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import java.util.Properties;
import javax.net.ssl.SSLContext;
import javax.net.ssl.X509TrustManager;
import javax.swing.JOptionPane;
import javax.swing.JTable;
import javax.swing.event.DocumentEvent;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableColumn;
import org.apache.http.client.methods.HttpEntityEnclosingRequestBase;
import org.apache.http.client.methods.HttpUriRequest;
import org.apache.http.conn.scheme.Scheme;
import org.apache.http.conn.ssl.SSLSocketFactory;
import org.bouncycastle.util.Strings;
import org.junit.Test;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.MediaType;
import org.springframework.http.client.HttpComponentsClientHttpRequestFactory;
import org.springframework.web.client.RestTemplate;
import simrskhanza.DlgRujukMasuk;


/**
 *
 * @author perpustakaan
 */
public final class BPJSDataSEP extends javax.swing.JDialog {
    private DefaultTableModel tabMode, tabMode1, tabMode2, tabMode3, tabMode4;
    private Connection koneksi = koneksiDB.condb();
    private sekuel Sequel = new sekuel();
    private validasi Valid = new validasi();
    private PreparedStatement ps;
    private ResultSet rs;
    private int i = 0, pilihan = 1, x, tglpulangnya = 0;
    private final Properties prop = new Properties();
    private SimpleDateFormat dateformat = new SimpleDateFormat("yyyy/MM/dd");
    public DlgRujukMasuk rujukmasuk = new DlgRujukMasuk(null, false);
    private BPJSApi api = new BPJSApi();
    private BPJSCekReferensiFaskes faskes = new BPJSCekReferensiFaskes(null, false);
    private BPJSCekReferensiPenyakit penyakit = new BPJSCekReferensiPenyakit(null, false);
    private BPJSCekReferensiPoli poli = new BPJSCekReferensiPoli(null, false);
    private BPJSCekNoKartu cekViaBPJSKartu = new BPJSCekNoKartu();
    private BPJSCekReferensiDokterDPJP dpjp = new BPJSCekReferensiDokterDPJP(null, false);
    private BPJSCekReferensiPropinsi provinsi = new BPJSCekReferensiPropinsi(null, false);
    private BPJSCekReferensiKabupaten kabupaten = new BPJSCekReferensiKabupaten(null, false);
    private BPJSCekReferensiKecamatan kecamatan = new BPJSCekReferensiKecamatan(null, false);
    private DlgSKDPBPJS skdp = new DlgSKDPBPJS(null, false);
    private String no_peserta = "", requestJson, URL = "", jkel = "", duplikat = "", user = "", penjamin = "",
            jasaraharja = "", BPJS = "", Taspen = "", Asabri = "", tglkkl = "0000-00-00", jnsrawat = "", dialog_simpan = "",            
            cttnVclaim = "", diagVclaim = "", jpelVclaim = "", KRVclaim = "", noRujukVclaim = "", JPVclaim = "", kelaminVclaim = "",
            namaVclaim = "", nokaVclaim = "", nomrVclaim = "", tglLhrVclaim = "", poliVclaim = "", poliEksVclaim = "", tglSEPVclaim = "", 
            nilaiJP = "", nilaiKR = "", nilaiEKS = "";

    /** Creates new form DlgRujuk
     * @param parent
     * @param modal */
    public BPJSDataSEP(java.awt.Frame parent, boolean modal) {
        super(parent, modal);
        initComponents();
        this.setLocation(8,1);
        //this.setLocation(10,2);
        //setSize(706,674);

        WindowUpdatePulang.setSize(494,78);
        WindowRujukan.setSize(616,247);
        WindowCariSEP.setSize(410,115);
        WindowCariNoRujuk.setSize(874,250);

        tabMode=new DefaultTableModel(null,new Object[]{
                "No.SEP","No.Rawat","No.RM","Nama Pasien","Tanggal SEP","Tanggal Rujukan",
                "No.Rujukan","Kode PPK Rujukan","Nama PPK Rujukan","Kode PPK Pelayanan",
                "Nama PPK Pelayanan","Jenis Pelayanan","Catatan", "Kode Diagnosa",
                "Nama Diagnosa", "Kode Poli", "Nama Poli", "Kelas Rawat", "Laka Lantas",
                "Lokasi Laka Lantas", "User Input","Tgl.Lahir","Peserta",
                "J.Kel","No.Kartu","Tanggal Pulang","Asal Rujukan","Eksekutif","COB","Penjamin","No.Telp",
                "Kasus Katarak","Tgl. KLL","Keterangan KLL","Suplesi","No.SEP Suplesi","KD Prov.",
                "Nama Provinsi","KD Kab.","Nama Kabupaten","KD Kec.","Nama Kecamatan","No.SKDP",
                "KD DPJP","Nama DPJP"
            }){
                @Override
            public boolean isCellEditable(int rowIndex, int colIndex) {
                boolean a = false;
                if (colIndex == 0) {
                    a = true;
                }
                return a;
            }
            Class[] types = new Class[]{
                java.lang.Object.class, java.lang.Object.class, java.lang.Object.class, java.lang.Object.class,
                java.lang.Object.class, java.lang.Object.class, java.lang.Object.class, java.lang.Object.class,
                java.lang.Object.class, java.lang.Object.class, java.lang.Object.class, java.lang.Object.class,
                java.lang.Object.class, java.lang.Object.class, java.lang.Object.class, java.lang.Object.class,
                java.lang.Object.class, java.lang.Object.class, java.lang.Object.class, java.lang.Object.class,                
                java.lang.Object.class, java.lang.Object.class, java.lang.Object.class, java.lang.Object.class,
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
             // @Override public boolean isCellEditable(int rowIndex, int colIndex){return false;}
        };
        
        tbObat.setModel(tabMode);
        //tbObat.setDefaultRenderer(Object.class, new WarnaTable(panelJudul.getBackground(),tbObat.getBackground()));
        tbObat.setPreferredScrollableViewportSize(new Dimension(500,500));
        tbObat.setAutoResizeMode(JTable.AUTO_RESIZE_OFF);

        for (i = 0; i < 45; i++) {
            TableColumn column = tbObat.getColumnModel().getColumn(i);
            if(i==0){
                column.setPreferredWidth(165);
            }else if(i==1){
                column.setPreferredWidth(105);
            }else if(i==2){
                column.setPreferredWidth(80);
            }else if(i==3){
                column.setPreferredWidth(200);
            }else if(i==4){
                column.setPreferredWidth(90);
            }else if(i==5){
                column.setPreferredWidth(90);
            }else if(i==6){
                column.setPreferredWidth(110);
            }else if(i==7){
                column.setMinWidth(0);
                column.setMaxWidth(0);
            }else if(i==8){
                column.setPreferredWidth(180);
            }else if(i==9){
                column.setMinWidth(0);
                column.setMaxWidth(0);
            }else if(i==10){
                column.setMinWidth(0);
                column.setMaxWidth(0);
            }else if(i==11){
                column.setPreferredWidth(100);
            }else if(i==12){
                column.setPreferredWidth(180);
            }else if(i==13){
                column.setMinWidth(0);
                column.setMaxWidth(0);
            }else if(i==14){
                column.setPreferredWidth(200);
            }else if(i==15){
                column.setMinWidth(0);
                column.setMaxWidth(0);
            }else if(i==16){
                column.setPreferredWidth(180);
            }else if(i==17){
                column.setPreferredWidth(80);
            }else if(i==19){
                column.setPreferredWidth(100);
            }else if(i==20){
                column.setMinWidth(0);
                column.setMaxWidth(0);
            }else if(i==21){
                column.setMinWidth(0);
                column.setMaxWidth(0);
            }else if(i==22){
                column.setMinWidth(0);
                column.setMaxWidth(0);
            }else if(i==23){
                column.setMinWidth(0);
                column.setMaxWidth(0);
            }else if(i==24){
                column.setMinWidth(0);
                column.setMaxWidth(0);
            }else if(i==25){
                column.setPreferredWidth(120);
            }else if(i==26){
                column.setPreferredWidth(100);
            }else if(i==27){
                column.setPreferredWidth(60);
            }else if(i==28){
                column.setPreferredWidth(60);
            }else if(i==29){
                column.setPreferredWidth(120);
            }else if(i==30){
                column.setPreferredWidth(120);
            }else if(i==31){
                column.setPreferredWidth(80);
            }else if(i==32){
                column.setPreferredWidth(90);
            }else if(i==33){
                column.setPreferredWidth(180);
            }else if(i==34){
                column.setPreferredWidth(60);
            }else if(i==35){
                column.setPreferredWidth(190);
            }else if(i==36){
                column.setPreferredWidth(70);
            }else if(i==37){
                column.setPreferredWidth(180);
            }else if(i==38){
                column.setPreferredWidth(90);
            }else if(i==39){
                column.setPreferredWidth(180);
            }else if(i==40){
                column.setPreferredWidth(90);
            }else if(i==41){
                column.setPreferredWidth(180);
            }else if(i==42){
                column.setPreferredWidth(180);
            }else if(i==43){
                column.setPreferredWidth(80);
            }else if(i==44){
                column.setPreferredWidth(200);
            }
        }
        tbObat.setDefaultRenderer(Object.class, new WarnaTable());  
        
        //tabel riwayat rujukan faskes 1
        tabMode1=new DefaultTableModel(null,new String[]{"No.","No. Rujukan","Tgl. Rujukan",
                "kode_ppk","Nama PPK Rujukan","Poli Rujukan","kode_icd","Diagnosa","Keluhan","kode_poli",
                "kode_ply","Jns. Pelayn."}) {
            @Override
            public boolean isCellEditable(int rowIndex, int colIndex) {
                return false;
            }
        };
        
        tbFaskes1.setModel(tabMode1);
        tbFaskes1.setPreferredScrollableViewportSize(new Dimension(500,500));
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
        tabMode2=new DefaultTableModel(null,new String[]{"No.","No. Rujukan","Tgl. Rujukan",
                "kode_ppk","Nama PPK Rujukan","Poli Rujukan","kode_icd","Diagnosa","Keluhan","kode_poli",
                "kode_ply","Jns. Pelayn."}) {
            @Override
            public boolean isCellEditable(int rowIndex, int colIndex) {
                return false;
            }
        };
        
        tbFaskes2.setModel(tabMode2);
        tbFaskes2.setPreferredScrollableViewportSize(new Dimension(500,500));
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
        tabMode3=new DefaultTableModel(null,new String[]{"No.","No. Rujukan","Tgl. Rujukan",
                "kode_ppk","Nama PPK Rujukan","Poli Rujukan","kode_icd","Diagnosa","Keluhan","kode_poli",
                "kode_ply","Jns. Pelayn."}) {
            @Override
            public boolean isCellEditable(int rowIndex, int colIndex) {
                return false;
            }
        };
        
        tbFaskes3.setModel(tabMode3);
        tbFaskes3.setPreferredScrollableViewportSize(new Dimension(500,500));
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
        tabMode4=new DefaultTableModel(null,new String[]{"No.","No. Rujukan","Tgl. Rujukan",
                "kode_ppk","Nama PPK Rujukan","Poli Rujukan","kode_icd","Diagnosa","Keluhan","kode_poli",
                "kode_ply","Jns. Pelayn."}) {
            @Override
            public boolean isCellEditable(int rowIndex, int colIndex) {
                return false;
            }
        };
        
        tbFaskes4.setModel(tabMode4);
        tbFaskes4.setPreferredScrollableViewportSize(new Dimension(500,500));
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
        
        TCari.setDocument(new batasInput((byte)100).getKata(TCari));
        NoRujukan.setDocument(new batasInput((byte)40).getKata(NoRujukan));
        noSKDP.setDocument(new batasInput((byte)10).getKata(noSKDP));
        NoSEPSuplesi.setDocument(new batasInput((byte)40).getKata(NoSEPSuplesi));
        //Catatan.setDocument(new batasInput((byte)255).getKata(Catatan));
        Ket.setDocument(new batasInput((byte)50).getKata(Ket));
        //Catatan1.setDocument(new batasInput((byte)255).getKata(Catatan1));
        
        if(koneksiDB.cariCepat().equals("aktif")){
            TCari.getDocument().addDocumentListener(new javax.swing.event.DocumentListener(){
                @Override
                public void insertUpdate(DocumentEvent e) {tampil();}
                @Override
                public void removeUpdate(DocumentEvent e) {tampil();}
                @Override
                public void changedUpdate(DocumentEvent e) {tampil();}
            });
        }

        faskes.addWindowListener(new WindowListener() {
            @Override
            public void windowOpened(WindowEvent e) {}
            @Override
            public void windowClosing(WindowEvent e) {}
            @Override
            public void windowClosed(WindowEvent e) {
                if(faskes.getTable().getSelectedRow()!= -1){
                    if(pilihan==1){
                        KdPpkRujukan.setText(faskes.getTable().getValueAt(faskes.getTable().getSelectedRow(),1).toString());
                        NmPpkRujukan.setText(faskes.getTable().getValueAt(faskes.getTable().getSelectedRow(),2).toString());
                        KdPpkRujukan.requestFocus();
                    }else if(pilihan==2){
                        KdPpkRujukan1.setText(faskes.getTable().getValueAt(faskes.getTable().getSelectedRow(),1).toString());
                        NmPpkRujukan1.setText(faskes.getTable().getValueAt(faskes.getTable().getSelectedRow(),2).toString());
                        KdPpkRujukan1.requestFocus();
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
        
        rujukmasuk.WindowPerujuk.addWindowListener(new WindowListener() {
            @Override
            public void windowOpened(WindowEvent e) {
            }

            @Override
            public void windowClosing(WindowEvent e) {
            }

            @Override
            public void windowClosed(WindowEvent e) {
                if (var.getform().equals("BPJSDataSEP")) {
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

        faskes.getTable().addKeyListener(new KeyListener() {
            @Override
            public void keyTyped(KeyEvent e) {}
            @Override
            public void keyPressed(KeyEvent e) {
                if(e.getKeyCode()==KeyEvent.VK_SPACE){
                    faskes.dispose();
                }
            }
            @Override
            public void keyReleased(KeyEvent e) {}
        });

        penyakit.addWindowListener(new WindowListener() {
            @Override
            public void windowOpened(WindowEvent e) {}
            @Override
            public void windowClosing(WindowEvent e) {}
            @Override
            public void windowClosed(WindowEvent e) {
                if(penyakit.getTable().getSelectedRow()!= -1){
                    if(pilihan==1){
                        KdPenyakit.setText(penyakit.getTable().getValueAt(penyakit.getTable().getSelectedRow(),1).toString());
                        NmPenyakit.setText(penyakit.getTable().getValueAt(penyakit.getTable().getSelectedRow(),2).toString());
                        KdPenyakit.requestFocus();
                    }else if(pilihan==2){
                        KdPenyakit1.setText(penyakit.getTable().getValueAt(penyakit.getTable().getSelectedRow(),1).toString());
                        NmPenyakit1.setText(penyakit.getTable().getValueAt(penyakit.getTable().getSelectedRow(),2).toString());
                        KdPenyakit1.requestFocus();
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

        penyakit.getTable().addKeyListener(new KeyListener() {
            @Override
            public void keyTyped(KeyEvent e) {}
            @Override
            public void keyPressed(KeyEvent e) {
                if(e.getKeyCode()==KeyEvent.VK_SPACE){
                    penyakit.dispose();
                }
            }
            @Override
            public void keyReleased(KeyEvent e) {}
        });

        poli.addWindowListener(new WindowListener() {
            @Override
            public void windowOpened(WindowEvent e) {}
            @Override
            public void windowClosing(WindowEvent e) {}
            @Override
            public void windowClosed(WindowEvent e) {
                if(poli.getTable().getSelectedRow()!= -1){
                    if(pilihan==1){
                        KdPoli.setText(poli.getTable().getValueAt(poli.getTable().getSelectedRow(),1).toString());
                        NmPoli.setText(poli.getTable().getValueAt(poli.getTable().getSelectedRow(),2).toString());
                        KdPoli.requestFocus();
                    }else if(pilihan==2){
                        KdPoli1.setText(poli.getTable().getValueAt(poli.getTable().getSelectedRow(),1).toString());
                        NmPoli1.setText(poli.getTable().getValueAt(poli.getTable().getSelectedRow(),2).toString());
                        KdPoli1.requestFocus();
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

        poli.getTable().addKeyListener(new KeyListener() {
            @Override
            public void keyTyped(KeyEvent e) {}
            @Override
            public void keyPressed(KeyEvent e) {
                if(e.getKeyCode()==KeyEvent.VK_SPACE){
                    poli.dispose();
                }
            }
            @Override
            public void keyReleased(KeyEvent e) {}
        });
        
        dpjp.addWindowListener(new WindowListener() {
            @Override
            public void windowOpened(WindowEvent e) {}
            @Override
            public void windowClosing(WindowEvent e) {}
            @Override
            public void windowClosed(WindowEvent e) {
                if(dpjp.getTable().getSelectedRow()!= -1){  
                    Kddpjp.setText(dpjp.getTable().getValueAt(dpjp.getTable().getSelectedRow(),1).toString());
                    NmDPJP.setText(dpjp.getTable().getValueAt(dpjp.getTable().getSelectedRow(),2).toString());
                    btnDPJP.requestFocus();             
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

        dpjp.getTable().addKeyListener(new KeyListener() {
            @Override
            public void keyTyped(KeyEvent e) {}
            @Override
            public void keyPressed(KeyEvent e) {
                if(e.getKeyCode()==KeyEvent.VK_SPACE){
                    dpjp.dispose();
                }
            }
            @Override
            public void keyReleased(KeyEvent e) {}
        });
        
        provinsi.addWindowListener(new WindowListener() {
            @Override
            public void windowOpened(WindowEvent e) {}
            @Override
            public void windowClosing(WindowEvent e) {}
            @Override
            public void windowClosed(WindowEvent e) {
                if(provinsi.getTable().getSelectedRow()!= -1){                   
                    KdProv.setText(provinsi.getTable().getValueAt(provinsi.getTable().getSelectedRow(),1).toString());
                    NmProv.setText(provinsi.getTable().getValueAt(provinsi.getTable().getSelectedRow(),2).toString());
                    btnProv.requestFocus();
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

        provinsi.getTable().addKeyListener(new KeyListener() {
            @Override
            public void keyTyped(KeyEvent e) {}
            @Override
            public void keyPressed(KeyEvent e) {
                if(e.getKeyCode()==KeyEvent.VK_SPACE){
                    provinsi.dispose();
                }
            }
            @Override
            public void keyReleased(KeyEvent e) {}
        });
        
        kabupaten.addWindowListener(new WindowListener() {
            @Override
            public void windowOpened(WindowEvent e) {}
            @Override
            public void windowClosing(WindowEvent e) {}
            @Override
            public void windowClosed(WindowEvent e) {
                if(kabupaten.getTable().getSelectedRow()!= -1){                   
                    KdKab.setText(kabupaten.getTable().getValueAt(kabupaten.getTable().getSelectedRow(),1).toString());
                    NmKab.setText(kabupaten.getTable().getValueAt(kabupaten.getTable().getSelectedRow(),2).toString());
                    btnKab.requestFocus();
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

        kabupaten.getTable().addKeyListener(new KeyListener() {
            @Override
            public void keyTyped(KeyEvent e) {}
            @Override
            public void keyPressed(KeyEvent e) {
                if(e.getKeyCode()==KeyEvent.VK_SPACE){
                    kabupaten.dispose();
                }
            }
            @Override
            public void keyReleased(KeyEvent e) {}
        });
        
        kecamatan.addWindowListener(new WindowListener() {
            @Override
            public void windowOpened(WindowEvent e) {}
            @Override
            public void windowClosing(WindowEvent e) {}
            @Override
            public void windowClosed(WindowEvent e) {
                if(kecamatan.getTable().getSelectedRow()!= -1){                   
                    KdKec.setText(kecamatan.getTable().getValueAt(kecamatan.getTable().getSelectedRow(),1).toString());
                    NmKec.setText(kecamatan.getTable().getValueAt(kecamatan.getTable().getSelectedRow(),2).toString());
                    btnKec.requestFocus();
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

        kecamatan.getTable().addKeyListener(new KeyListener() {
            @Override
            public void keyTyped(KeyEvent e) {}
            @Override
            public void keyPressed(KeyEvent e) {
                if(e.getKeyCode()==KeyEvent.VK_SPACE){
                    kecamatan.dispose();
                }
            }
            @Override
            public void keyReleased(KeyEvent e) {}
        });
        
        skdp.addWindowListener(new WindowListener() {
            @Override
            public void windowOpened(WindowEvent e) {}
            @Override
            public void windowClosing(WindowEvent e) {}
            @Override
            public void windowClosed(WindowEvent e) {
                if(skdp.getTable().getSelectedRow()!= -1){                   
                    noSKDP.setText(skdp.getTable().getValueAt(skdp.getTable().getSelectedRow(),11).toString());
                    btnDPJP.requestFocus();
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
        
        skdp.getTable().addKeyListener(new KeyListener() {
            @Override
            public void keyTyped(KeyEvent e) {}
            @Override
            public void keyPressed(KeyEvent e) {
                if(e.getKeyCode()==KeyEvent.VK_SPACE){
                    skdp.dispose();
                }
            }
            @Override
            public void keyReleased(KeyEvent e) {}
        });

        try{
            KdPPK.setText(Sequel.cariIsi("select kode_ppk from setting"));
            NmPPK.setText(Sequel.cariIsi("select nama_instansi from setting"));
        }catch(Exception e){
            System.out.println(e);
        }

        try {
            user=var.getkode().replace(" ","").substring(0,9);
        } catch (Exception e) {
            user=var.getkode();
        }

        try {
            prop.loadFromXML(new FileInputStream("setting/database.xml"));
        } catch (Exception e) {
            System.out.println("SEP XML : "+e);
        }
        
        cekLAKA();
        cekLAYAN();
    }


    /** This method is called from within the constructor to
     * initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is
     * always regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        Popup = new javax.swing.JPopupMenu();
        ppSEP = new javax.swing.JMenuItem();
        ppUpdateSEPdariVclaim = new javax.swing.JMenuItem();
        ppPulang = new javax.swing.JMenuItem();
        ppPulangAuto = new javax.swing.JMenuItem();
        ppDetailSEPPeserta = new javax.swing.JMenuItem();
        ppRujukan = new javax.swing.JMenuItem();
        ppSuratKontrol = new javax.swing.JMenuItem();
        jSeparator1 = new javax.swing.JPopupMenu.Separator();
        MnRekapSEPRanap = new javax.swing.JMenuItem();
        MnRekapSEPRalan = new javax.swing.JMenuItem();
        Popup1 = new javax.swing.JPopupMenu();
        ppPengajuan = new javax.swing.JMenu();
        MnBackdate = new javax.swing.JMenuItem();
        MnFingerPrin = new javax.swing.JMenuItem();
        ppAprovalSEP = new javax.swing.JMenu();
        MnBackdate1 = new javax.swing.JMenuItem();
        MnFingerPrin1 = new javax.swing.JMenuItem();
        ppAmbilSep = new javax.swing.JMenuItem();
        WindowUpdatePulang = new javax.swing.JDialog();
        internalFrame5 = new widget.InternalFrame();
        BtnCloseIn4 = new widget.Button();
        BtnSimpan4 = new widget.Button();
        jLabel26 = new widget.Label();
        TanggalPulang = new widget.Tanggal();
        WindowRujukan = new javax.swing.JDialog();
        internalFrame6 = new widget.InternalFrame();
        BtnCloseIn5 = new widget.Button();
        BtnSimpan5 = new widget.Button();
        jLabel30 = new widget.Label();
        TanggalRujukKeluar = new widget.Tanggal();
        jLabel12 = new widget.Label();
        KdPpkRujukan1 = new widget.TextBox();
        NmPpkRujukan1 = new widget.TextBox();
        btnPPKRujukan1 = new widget.Button();
        jLabel31 = new widget.Label();
        JenisPelayanan1 = new widget.ComboBox();
        jLabel32 = new widget.Label();
        KdPenyakit1 = new widget.TextBox();
        NmPenyakit1 = new widget.TextBox();
        btnDiagnosa1 = new widget.Button();
        nosep = new widget.Label();
        KdPoli1 = new widget.TextBox();
        NmPoli1 = new widget.TextBox();
        btnPoli1 = new widget.Button();
        jLabel33 = new widget.Label();
        TipeRujukan = new widget.ComboBox();
        jLabel34 = new widget.Label();
        Catatan1 = new widget.TextBox();
        LabelPoli2 = new widget.Label();
        sepRujuk = new widget.TextBox();
        BtnResetRujuk = new widget.Button();
        WindowCariSEP = new javax.swing.JDialog();
        internalFrame7 = new widget.InternalFrame();
        BtnCloseIn6 = new widget.Button();
        BtnSimpan6 = new widget.Button();
        jLabel35 = new widget.Label();
        NoSEP = new widget.TextBox();
        BtnCari1 = new widget.Button();
        WindowCariNoRujuk = new javax.swing.JDialog();
        internalFrame8 = new widget.InternalFrame();
        TabRujukan = new javax.swing.JTabbedPane();
        internalFrame2 = new widget.InternalFrame();
        scrollPane1 = new widget.ScrollPane();
        tbFaskes1 = new widget.Table();
        internalFrame3 = new widget.InternalFrame();
        scrollPane2 = new widget.ScrollPane();
        tbFaskes2 = new widget.Table();
        internalFrame4 = new widget.InternalFrame();
        scrollPane3 = new widget.ScrollPane();
        tbFaskes3 = new widget.Table();
        internalFrame9 = new widget.InternalFrame();
        scrollPane4 = new widget.ScrollPane();
        tbFaskes4 = new widget.Table();
        panelisi1 = new widget.panelisi();
        BtnKeluar1 = new widget.Button();
        NoBalasan = new widget.TextBox();
        LokasiLaka = new widget.TextBox();
        kode_rujukanya = new widget.TextBox();
        nmfaskes_keluar = new widget.TextBox();
        internalFrame1 = new widget.InternalFrame();
        Scroll = new widget.ScrollPane();
        tbObat = new widget.Table();
        jPanel3 = new javax.swing.JPanel();
        panelGlass8 = new widget.panelisi();
        BtnSimpan = new widget.Button();
        BtnBatal = new widget.Button();
        BtnHapus = new widget.Button();
        BtnPrint = new widget.Button();
        BtnEdit = new widget.Button();
        BtnAll = new widget.Button();
        BtnKeluar = new widget.Button();
        panelGlass9 = new widget.panelisi();
        jLabel19 = new widget.Label();
        DTPCari1 = new widget.Tanggal();
        jLabel21 = new widget.Label();
        DTPCari2 = new widget.Tanggal();
        jLabel6 = new widget.Label();
        TCari = new widget.TextBox();
        BtnCari = new widget.Button();
        jLabel7 = new widget.Label();
        LCount = new widget.Label();
        PanelInput = new javax.swing.JPanel();
        ChkInput = new widget.CekBox();
        FormInput = new widget.PanelBiasa();
        jLabel4 = new widget.Label();
        TNoRw = new widget.TextBox();
        TPasien = new widget.TextBox();
        TNoRM = new widget.TextBox();
        jLabel5 = new widget.Label();
        NoKartu = new widget.TextBox();
        jLabel20 = new widget.Label();
        TanggalSEP = new widget.Tanggal();
        jLabel22 = new widget.Label();
        TanggalRujuk = new widget.Tanggal();
        jLabel23 = new widget.Label();
        NoRujukan = new widget.TextBox();
        jLabel9 = new widget.Label();
        KdPPK = new widget.TextBox();
        NmPPK = new widget.TextBox();
        btnPPKRujukan = new widget.Button();
        jLabel10 = new widget.Label();
        KdPpkRujukan = new widget.TextBox();
        NmPpkRujukan = new widget.TextBox();
        jLabel11 = new widget.Label();
        KdPenyakit = new widget.TextBox();
        NmPenyakit = new widget.TextBox();
        btnDiagnosa = new widget.Button();
        btnPoli = new widget.Button();
        NmPoli = new widget.TextBox();
        KdPoli = new widget.TextBox();
        LabelKatarak = new widget.Label();
        jLabel13 = new widget.Label();
        jLabel14 = new widget.Label();
        JenisPelayanan = new widget.ComboBox();
        LabelKelas = new widget.Label();
        Kelas = new widget.ComboBox();
        LabTglkll = new widget.Label();
        LakaLantas = new widget.ComboBox();
        LabNoSup = new widget.Label();
        jLabel8 = new widget.Label();
        TglLahir = new widget.TextBox();
        jLabel18 = new widget.Label();
        JK = new widget.TextBox();
        jLabel24 = new widget.Label();
        JenisPeserta = new widget.TextBox();
        jLabel25 = new widget.Label();
        Status = new widget.TextBox();
        rujukanSEP = new widget.Label();
        AsalRujukan = new widget.ComboBox();
        jLabel15 = new widget.Label();
        Eksekutif = new widget.ComboBox();
        LabelKelas1 = new widget.Label();
        COB = new widget.ComboBox();
        LabKetkll = new widget.Label();
        ChkAsa = new widget.CekBox();
        ChkJasaRaharja = new widget.CekBox();
        ChkBPJSTenaga = new widget.CekBox();
        ChkTaspen = new widget.CekBox();
        jLabel29 = new widget.Label();
        NoTelp = new widget.TextBox();
        BtnRujukanVclaim = new widget.Button();
        jLabel36 = new widget.Label();
        TanggalKejadian = new widget.Tanggal();
        LabjaminKll = new widget.Label();
        Ket = new widget.TextBox();
        LabProv = new widget.Label();
        LabKab = new widget.Label();
        KdProv = new widget.TextBox();
        KdKab = new widget.TextBox();
        KdKec = new widget.TextBox();
        NmProv = new widget.TextBox();
        NmKab = new widget.TextBox();
        NmKec = new widget.TextBox();
        btnProv = new widget.Button();
        btnKab = new widget.Button();
        btnKec = new widget.Button();
        LabKec = new widget.Label();
        suplesi = new widget.ComboBox();
        LabSup = new widget.Label();
        NoSEPSuplesi = new widget.TextBox();
        LabelPoli = new widget.Label();
        KasusKatarak = new widget.ComboBox();
        jLabel42 = new widget.Label();
        noSKDP = new widget.TextBox();
        jLabel43 = new widget.Label();
        Kddpjp = new widget.TextBox();
        NmDPJP = new widget.TextBox();
        btnDPJP = new widget.Button();
        btnNoSKDP = new widget.Button();
        btnNoRujukan = new widget.Button();
        jLabel28 = new widget.Label();
        Scroll3 = new widget.ScrollPane();
        Catatan = new widget.TextArea();

        Popup.setName("Popup"); // NOI18N

        ppSEP.setBackground(new java.awt.Color(242, 242, 242));
        ppSEP.setFont(new java.awt.Font("Tahoma", 0, 11)); // NOI18N
        ppSEP.setIcon(new javax.swing.ImageIcon(getClass().getResource("/picture/category.png"))); // NOI18N
        ppSEP.setText("Print SEP");
        ppSEP.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        ppSEP.setHorizontalTextPosition(javax.swing.SwingConstants.RIGHT);
        ppSEP.setIconTextGap(8);
        ppSEP.setName("ppSEP"); // NOI18N
        ppSEP.setPreferredSize(new java.awt.Dimension(200, 25));
        ppSEP.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                ppSEPBtnPrintActionPerformed(evt);
            }
        });
        Popup.add(ppSEP);

        ppUpdateSEPdariVclaim.setBackground(new java.awt.Color(242, 242, 242));
        ppUpdateSEPdariVclaim.setFont(new java.awt.Font("Tahoma", 0, 11)); // NOI18N
        ppUpdateSEPdariVclaim.setIcon(new javax.swing.ImageIcon(getClass().getResource("/picture/category.png"))); // NOI18N
        ppUpdateSEPdariVclaim.setText("Update SEP dari VCLAIM");
        ppUpdateSEPdariVclaim.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        ppUpdateSEPdariVclaim.setHorizontalTextPosition(javax.swing.SwingConstants.RIGHT);
        ppUpdateSEPdariVclaim.setIconTextGap(8);
        ppUpdateSEPdariVclaim.setName("ppUpdateSEPdariVclaim"); // NOI18N
        ppUpdateSEPdariVclaim.setPreferredSize(new java.awt.Dimension(200, 25));
        ppUpdateSEPdariVclaim.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                ppUpdateSEPdariVclaimBtnPrintActionPerformed(evt);
            }
        });
        Popup.add(ppUpdateSEPdariVclaim);

        ppPulang.setBackground(new java.awt.Color(242, 242, 242));
        ppPulang.setFont(new java.awt.Font("Tahoma", 0, 11)); // NOI18N
        ppPulang.setIcon(new javax.swing.ImageIcon(getClass().getResource("/picture/category.png"))); // NOI18N
        ppPulang.setText("Update Tgl. Pulang Manual");
        ppPulang.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        ppPulang.setHorizontalTextPosition(javax.swing.SwingConstants.RIGHT);
        ppPulang.setIconTextGap(8);
        ppPulang.setName("ppPulang"); // NOI18N
        ppPulang.setPreferredSize(new java.awt.Dimension(200, 25));
        ppPulang.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                ppPulangBtnPrintActionPerformed(evt);
            }
        });
        Popup.add(ppPulang);

        ppPulangAuto.setBackground(new java.awt.Color(242, 242, 242));
        ppPulangAuto.setFont(new java.awt.Font("Tahoma", 0, 11)); // NOI18N
        ppPulangAuto.setIcon(new javax.swing.ImageIcon(getClass().getResource("/picture/category.png"))); // NOI18N
        ppPulangAuto.setText("Update Tgl. Pulang Otomatis");
        ppPulangAuto.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        ppPulangAuto.setHorizontalTextPosition(javax.swing.SwingConstants.RIGHT);
        ppPulangAuto.setIconTextGap(8);
        ppPulangAuto.setName("ppPulangAuto"); // NOI18N
        ppPulangAuto.setPreferredSize(new java.awt.Dimension(200, 25));
        ppPulangAuto.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                ppPulangAutoBtnPrintActionPerformed(evt);
            }
        });
        Popup.add(ppPulangAuto);

        ppDetailSEPPeserta.setBackground(new java.awt.Color(242, 242, 242));
        ppDetailSEPPeserta.setFont(new java.awt.Font("Tahoma", 0, 11)); // NOI18N
        ppDetailSEPPeserta.setIcon(new javax.swing.ImageIcon(getClass().getResource("/picture/category.png"))); // NOI18N
        ppDetailSEPPeserta.setText("Detail SEP Peserta");
        ppDetailSEPPeserta.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        ppDetailSEPPeserta.setHorizontalTextPosition(javax.swing.SwingConstants.RIGHT);
        ppDetailSEPPeserta.setIconTextGap(8);
        ppDetailSEPPeserta.setName("ppDetailSEPPeserta"); // NOI18N
        ppDetailSEPPeserta.setPreferredSize(new java.awt.Dimension(200, 25));
        ppDetailSEPPeserta.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                ppDetailSEPPesertaBtnPrintActionPerformed(evt);
            }
        });
        Popup.add(ppDetailSEPPeserta);

        ppRujukan.setBackground(new java.awt.Color(242, 242, 242));
        ppRujukan.setFont(new java.awt.Font("Tahoma", 0, 11)); // NOI18N
        ppRujukan.setIcon(new javax.swing.ImageIcon(getClass().getResource("/picture/category.png"))); // NOI18N
        ppRujukan.setText("Membuat Rujukan Keluar");
        ppRujukan.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        ppRujukan.setHorizontalTextPosition(javax.swing.SwingConstants.RIGHT);
        ppRujukan.setIconTextGap(8);
        ppRujukan.setName("ppRujukan"); // NOI18N
        ppRujukan.setPreferredSize(new java.awt.Dimension(200, 25));
        ppRujukan.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                ppRujukanBtnPrintActionPerformed(evt);
            }
        });
        Popup.add(ppRujukan);

        ppSuratKontrol.setBackground(new java.awt.Color(242, 242, 242));
        ppSuratKontrol.setFont(new java.awt.Font("Tahoma", 0, 11)); // NOI18N
        ppSuratKontrol.setIcon(new javax.swing.ImageIcon(getClass().getResource("/picture/category.png"))); // NOI18N
        ppSuratKontrol.setText("Rencana Kontrol/SPRI BPJS");
        ppSuratKontrol.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        ppSuratKontrol.setHorizontalTextPosition(javax.swing.SwingConstants.RIGHT);
        ppSuratKontrol.setIconTextGap(8);
        ppSuratKontrol.setName("ppSuratKontrol"); // NOI18N
        ppSuratKontrol.setPreferredSize(new java.awt.Dimension(200, 25));
        ppSuratKontrol.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                ppSuratKontrolBtnPrintActionPerformed(evt);
            }
        });
        Popup.add(ppSuratKontrol);

        jSeparator1.setForeground(new java.awt.Color(170, 190, 145));
        jSeparator1.setName("jSeparator1"); // NOI18N
        Popup.add(jSeparator1);

        MnRekapSEPRanap.setBackground(new java.awt.Color(242, 242, 242));
        MnRekapSEPRanap.setFont(new java.awt.Font("Tahoma", 0, 11)); // NOI18N
        MnRekapSEPRanap.setIcon(new javax.swing.ImageIcon(getClass().getResource("/picture/download1-24.png"))); // NOI18N
        MnRekapSEPRanap.setText("Rekap No. SEP R. INAP");
        MnRekapSEPRanap.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        MnRekapSEPRanap.setHorizontalTextPosition(javax.swing.SwingConstants.RIGHT);
        MnRekapSEPRanap.setIconTextGap(8);
        MnRekapSEPRanap.setName("MnRekapSEPRanap"); // NOI18N
        MnRekapSEPRanap.setPreferredSize(new java.awt.Dimension(200, 25));
        MnRekapSEPRanap.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                MnRekapSEPRanapBtnPrintActionPerformed(evt);
            }
        });
        Popup.add(MnRekapSEPRanap);

        MnRekapSEPRalan.setBackground(new java.awt.Color(242, 242, 242));
        MnRekapSEPRalan.setFont(new java.awt.Font("Tahoma", 0, 11)); // NOI18N
        MnRekapSEPRalan.setIcon(new javax.swing.ImageIcon(getClass().getResource("/picture/download1-24.png"))); // NOI18N
        MnRekapSEPRalan.setText("Rekap No. SEP R. JALAN");
        MnRekapSEPRalan.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        MnRekapSEPRalan.setHorizontalTextPosition(javax.swing.SwingConstants.RIGHT);
        MnRekapSEPRalan.setIconTextGap(8);
        MnRekapSEPRalan.setName("MnRekapSEPRalan"); // NOI18N
        MnRekapSEPRalan.setPreferredSize(new java.awt.Dimension(200, 25));
        MnRekapSEPRalan.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                MnRekapSEPRalanBtnPrintActionPerformed(evt);
            }
        });
        Popup.add(MnRekapSEPRalan);

        Popup1.setName("Popup1"); // NOI18N

        ppPengajuan.setBackground(new java.awt.Color(255, 255, 255));
        ppPengajuan.setIcon(new javax.swing.ImageIcon(getClass().getResource("/picture/category.png"))); // NOI18N
        ppPengajuan.setText("Pengajuan SEP");
        ppPengajuan.setFont(new java.awt.Font("Tahoma", 0, 11)); // NOI18N
        ppPengajuan.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        ppPengajuan.setHorizontalTextPosition(javax.swing.SwingConstants.RIGHT);
        ppPengajuan.setIconTextGap(5);
        ppPengajuan.setName("ppPengajuan"); // NOI18N
        ppPengajuan.setOpaque(true);
        ppPengajuan.setPreferredSize(new java.awt.Dimension(180, 25));

        MnBackdate.setBackground(new java.awt.Color(255, 255, 255));
        MnBackdate.setFont(new java.awt.Font("Tahoma", 0, 11)); // NOI18N
        MnBackdate.setIcon(new javax.swing.ImageIcon(getClass().getResource("/picture/category.png"))); // NOI18N
        MnBackdate.setText("Backdate");
        MnBackdate.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        MnBackdate.setHorizontalTextPosition(javax.swing.SwingConstants.RIGHT);
        MnBackdate.setIconTextGap(5);
        MnBackdate.setName("MnBackdate"); // NOI18N
        MnBackdate.setPreferredSize(new java.awt.Dimension(110, 25));
        MnBackdate.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                MnBackdateActionPerformed(evt);
            }
        });
        ppPengajuan.add(MnBackdate);

        MnFingerPrin.setBackground(new java.awt.Color(255, 255, 255));
        MnFingerPrin.setFont(new java.awt.Font("Tahoma", 0, 11)); // NOI18N
        MnFingerPrin.setIcon(new javax.swing.ImageIcon(getClass().getResource("/picture/category.png"))); // NOI18N
        MnFingerPrin.setText("Finger Print");
        MnFingerPrin.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        MnFingerPrin.setHorizontalTextPosition(javax.swing.SwingConstants.RIGHT);
        MnFingerPrin.setIconTextGap(5);
        MnFingerPrin.setName("MnFingerPrin"); // NOI18N
        MnFingerPrin.setPreferredSize(new java.awt.Dimension(110, 25));
        MnFingerPrin.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                MnFingerPrinActionPerformed(evt);
            }
        });
        ppPengajuan.add(MnFingerPrin);

        Popup1.add(ppPengajuan);

        ppAprovalSEP.setBackground(new java.awt.Color(255, 255, 255));
        ppAprovalSEP.setIcon(new javax.swing.ImageIcon(getClass().getResource("/picture/category.png"))); // NOI18N
        ppAprovalSEP.setText("Aproval SEP");
        ppAprovalSEP.setFont(new java.awt.Font("Tahoma", 0, 11)); // NOI18N
        ppAprovalSEP.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        ppAprovalSEP.setHorizontalTextPosition(javax.swing.SwingConstants.RIGHT);
        ppAprovalSEP.setIconTextGap(5);
        ppAprovalSEP.setName("ppAprovalSEP"); // NOI18N
        ppAprovalSEP.setOpaque(true);
        ppAprovalSEP.setPreferredSize(new java.awt.Dimension(180, 25));

        MnBackdate1.setBackground(new java.awt.Color(255, 255, 255));
        MnBackdate1.setFont(new java.awt.Font("Tahoma", 0, 11)); // NOI18N
        MnBackdate1.setIcon(new javax.swing.ImageIcon(getClass().getResource("/picture/category.png"))); // NOI18N
        MnBackdate1.setText("Backdate");
        MnBackdate1.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        MnBackdate1.setHorizontalTextPosition(javax.swing.SwingConstants.RIGHT);
        MnBackdate1.setIconTextGap(5);
        MnBackdate1.setName("MnBackdate1"); // NOI18N
        MnBackdate1.setPreferredSize(new java.awt.Dimension(110, 25));
        MnBackdate1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                MnBackdate1ActionPerformed(evt);
            }
        });
        ppAprovalSEP.add(MnBackdate1);

        MnFingerPrin1.setBackground(new java.awt.Color(255, 255, 255));
        MnFingerPrin1.setFont(new java.awt.Font("Tahoma", 0, 11)); // NOI18N
        MnFingerPrin1.setIcon(new javax.swing.ImageIcon(getClass().getResource("/picture/category.png"))); // NOI18N
        MnFingerPrin1.setText("Finger Print");
        MnFingerPrin1.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        MnFingerPrin1.setHorizontalTextPosition(javax.swing.SwingConstants.RIGHT);
        MnFingerPrin1.setIconTextGap(5);
        MnFingerPrin1.setName("MnFingerPrin1"); // NOI18N
        MnFingerPrin1.setPreferredSize(new java.awt.Dimension(110, 25));
        MnFingerPrin1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                MnFingerPrin1ActionPerformed(evt);
            }
        });
        ppAprovalSEP.add(MnFingerPrin1);

        Popup1.add(ppAprovalSEP);

        ppAmbilSep.setBackground(new java.awt.Color(255, 255, 255));
        ppAmbilSep.setFont(new java.awt.Font("Tahoma", 0, 11)); // NOI18N
        ppAmbilSep.setIcon(new javax.swing.ImageIcon(getClass().getResource("/picture/category.png"))); // NOI18N
        ppAmbilSep.setText("Ambil SEP di VClaim");
        ppAmbilSep.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        ppAmbilSep.setHorizontalTextPosition(javax.swing.SwingConstants.RIGHT);
        ppAmbilSep.setIconTextGap(5);
        ppAmbilSep.setName("ppAmbilSep"); // NOI18N
        ppAmbilSep.setPreferredSize(new java.awt.Dimension(180, 25));
        ppAmbilSep.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                ppAmbilSepBtnPrintActionPerformed(evt);
            }
        });
        Popup1.add(ppAmbilSep);

        WindowUpdatePulang.setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);
        WindowUpdatePulang.setName("WindowUpdatePulang"); // NOI18N
        WindowUpdatePulang.setUndecorated(true);
        WindowUpdatePulang.setResizable(false);

        internalFrame5.setBorder(javax.swing.BorderFactory.createTitledBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 153, 255), 3), "::[ Update Tanggal Pulang ]::", javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Tahoma", 1, 12), new java.awt.Color(0, 0, 0))); // NOI18N
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
        BtnCloseIn4.setBounds(370, 30, 100, 30);

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
        BtnSimpan4.setBounds(260, 30, 100, 30);

        jLabel26.setForeground(new java.awt.Color(0, 0, 0));
        jLabel26.setText("Tanggal Pulang :");
        jLabel26.setName("jLabel26"); // NOI18N
        internalFrame5.add(jLabel26);
        jLabel26.setBounds(6, 32, 100, 23);

        TanggalPulang.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "18-11-2021 16:00:29" }));
        TanggalPulang.setDisplayFormat("dd-MM-yyyy HH:mm:ss");
        TanggalPulang.setName("TanggalPulang"); // NOI18N
        TanggalPulang.setOpaque(false);
        TanggalPulang.setPreferredSize(new java.awt.Dimension(95, 23));
        internalFrame5.add(TanggalPulang);
        TanggalPulang.setBounds(110, 32, 140, 23);

        WindowUpdatePulang.getContentPane().add(internalFrame5, java.awt.BorderLayout.CENTER);

        WindowRujukan.setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);
        WindowRujukan.setName("WindowRujukan"); // NOI18N
        WindowRujukan.setUndecorated(true);
        WindowRujukan.setResizable(false);

        internalFrame6.setBorder(javax.swing.BorderFactory.createTitledBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 153, 255), 3), "::[ Buat Rujukan Keluar VClaim ]::", javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Tahoma", 1, 12), new java.awt.Color(0, 0, 0))); // NOI18N
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
        BtnCloseIn5.setBounds(490, 205, 100, 30);

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
        BtnSimpan5.setBounds(380, 205, 100, 30);

        jLabel30.setForeground(new java.awt.Color(0, 0, 0));
        jLabel30.setText("Tanggal Rujukan :");
        jLabel30.setName("jLabel30"); // NOI18N
        internalFrame6.add(jLabel30);
        jLabel30.setBounds(0, 25, 102, 23);

        TanggalRujukKeluar.setEditable(false);
        TanggalRujukKeluar.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "18-11-2021" }));
        TanggalRujukKeluar.setDisplayFormat("dd-MM-yyyy");
        TanggalRujukKeluar.setName("TanggalRujukKeluar"); // NOI18N
        TanggalRujukKeluar.setOpaque(false);
        TanggalRujukKeluar.setPreferredSize(new java.awt.Dimension(95, 23));
        internalFrame6.add(TanggalRujukKeluar);
        TanggalRujukKeluar.setBounds(105, 25, 90, 23);

        jLabel12.setForeground(new java.awt.Color(0, 0, 0));
        jLabel12.setText("PPK Rujukan :");
        jLabel12.setName("jLabel12"); // NOI18N
        internalFrame6.add(jLabel12);
        jLabel12.setBounds(0, 55, 102, 23);

        KdPpkRujukan1.setEditable(false);
        KdPpkRujukan1.setBackground(new java.awt.Color(245, 250, 240));
        KdPpkRujukan1.setForeground(new java.awt.Color(0, 0, 0));
        KdPpkRujukan1.setName("KdPpkRujukan1"); // NOI18N
        internalFrame6.add(KdPpkRujukan1);
        KdPpkRujukan1.setBounds(105, 55, 75, 23);

        NmPpkRujukan1.setEditable(false);
        NmPpkRujukan1.setBackground(new java.awt.Color(245, 250, 240));
        NmPpkRujukan1.setForeground(new java.awt.Color(0, 0, 0));
        NmPpkRujukan1.setHighlighter(null);
        NmPpkRujukan1.setName("NmPpkRujukan1"); // NOI18N
        internalFrame6.add(NmPpkRujukan1);
        NmPpkRujukan1.setBounds(182, 55, 384, 23);

        btnPPKRujukan1.setForeground(new java.awt.Color(0, 0, 0));
        btnPPKRujukan1.setIcon(new javax.swing.ImageIcon(getClass().getResource("/picture/190.png"))); // NOI18N
        btnPPKRujukan1.setMnemonic('X');
        btnPPKRujukan1.setToolTipText("Alt+X");
        btnPPKRujukan1.setName("btnPPKRujukan1"); // NOI18N
        btnPPKRujukan1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnPPKRujukan1ActionPerformed(evt);
            }
        });
        btnPPKRujukan1.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                btnPPKRujukan1KeyPressed(evt);
            }
        });
        internalFrame6.add(btnPPKRujukan1);
        btnPPKRujukan1.setBounds(570, 55, 28, 23);

        jLabel31.setForeground(new java.awt.Color(0, 0, 0));
        jLabel31.setText("Jns.Pelayanan :");
        jLabel31.setName("jLabel31"); // NOI18N
        internalFrame6.add(jLabel31);
        jLabel31.setBounds(390, 25, 83, 23);

        JenisPelayanan1.setForeground(new java.awt.Color(0, 0, 0));
        JenisPelayanan1.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "1. Rawat Inap", "2. Rawat Jalan" }));
        JenisPelayanan1.setSelectedIndex(1);
        JenisPelayanan1.setName("JenisPelayanan1"); // NOI18N
        JenisPelayanan1.setOpaque(false);
        JenisPelayanan1.addItemListener(new java.awt.event.ItemListener() {
            public void itemStateChanged(java.awt.event.ItemEvent evt) {
                JenisPelayanan1ItemStateChanged(evt);
            }
        });
        JenisPelayanan1.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                JenisPelayanan1KeyPressed(evt);
            }
        });
        internalFrame6.add(JenisPelayanan1);
        JenisPelayanan1.setBounds(478, 25, 100, 23);

        jLabel32.setForeground(new java.awt.Color(0, 0, 0));
        jLabel32.setText("Diagnosa Rujuk :");
        jLabel32.setName("jLabel32"); // NOI18N
        internalFrame6.add(jLabel32);
        jLabel32.setBounds(0, 85, 102, 23);

        KdPenyakit1.setEditable(false);
        KdPenyakit1.setBackground(new java.awt.Color(245, 250, 240));
        KdPenyakit1.setForeground(new java.awt.Color(0, 0, 0));
        KdPenyakit1.setHighlighter(null);
        KdPenyakit1.setName("KdPenyakit1"); // NOI18N
        internalFrame6.add(KdPenyakit1);
        KdPenyakit1.setBounds(105, 85, 75, 23);

        NmPenyakit1.setEditable(false);
        NmPenyakit1.setBackground(new java.awt.Color(245, 250, 240));
        NmPenyakit1.setForeground(new java.awt.Color(0, 0, 0));
        NmPenyakit1.setHighlighter(null);
        NmPenyakit1.setName("NmPenyakit1"); // NOI18N
        internalFrame6.add(NmPenyakit1);
        NmPenyakit1.setBounds(182, 85, 384, 23);

        btnDiagnosa1.setForeground(new java.awt.Color(0, 0, 0));
        btnDiagnosa1.setIcon(new javax.swing.ImageIcon(getClass().getResource("/picture/190.png"))); // NOI18N
        btnDiagnosa1.setMnemonic('X');
        btnDiagnosa1.setToolTipText("Alt+X");
        btnDiagnosa1.setName("btnDiagnosa1"); // NOI18N
        btnDiagnosa1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnDiagnosa1ActionPerformed(evt);
            }
        });
        btnDiagnosa1.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                btnDiagnosa1KeyPressed(evt);
            }
        });
        internalFrame6.add(btnDiagnosa1);
        btnDiagnosa1.setBounds(570, 85, 28, 23);

        nosep.setForeground(new java.awt.Color(0, 0, 0));
        nosep.setText("No. SEP :");
        nosep.setName("nosep"); // NOI18N
        internalFrame6.add(nosep);
        nosep.setBounds(0, 143, 102, 23);

        KdPoli1.setEditable(false);
        KdPoli1.setBackground(new java.awt.Color(245, 250, 240));
        KdPoli1.setForeground(new java.awt.Color(0, 0, 0));
        KdPoli1.setHighlighter(null);
        KdPoli1.setName("KdPoli1"); // NOI18N
        internalFrame6.add(KdPoli1);
        KdPoli1.setBounds(105, 114, 75, 23);

        NmPoli1.setEditable(false);
        NmPoli1.setBackground(new java.awt.Color(245, 250, 240));
        NmPoli1.setForeground(new java.awt.Color(0, 0, 0));
        NmPoli1.setHighlighter(null);
        NmPoli1.setName("NmPoli1"); // NOI18N
        internalFrame6.add(NmPoli1);
        NmPoli1.setBounds(185, 114, 384, 23);

        btnPoli1.setForeground(new java.awt.Color(0, 0, 0));
        btnPoli1.setIcon(new javax.swing.ImageIcon(getClass().getResource("/picture/190.png"))); // NOI18N
        btnPoli1.setMnemonic('X');
        btnPoli1.setToolTipText("Alt+X");
        btnPoli1.setName("btnPoli1"); // NOI18N
        btnPoli1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnPoli1ActionPerformed(evt);
            }
        });
        btnPoli1.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                btnPoli1KeyPressed(evt);
            }
        });
        internalFrame6.add(btnPoli1);
        btnPoli1.setBounds(570, 114, 28, 23);

        jLabel33.setForeground(new java.awt.Color(0, 0, 0));
        jLabel33.setText("Tipe Rujukan :");
        jLabel33.setName("jLabel33"); // NOI18N
        internalFrame6.add(jLabel33);
        jLabel33.setBounds(200, 25, 73, 23);

        TipeRujukan.setForeground(new java.awt.Color(0, 0, 0));
        TipeRujukan.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "0. Penuh", "1. Partial", "2. Rujuk Balik" }));
        TipeRujukan.setName("TipeRujukan"); // NOI18N
        TipeRujukan.setOpaque(false);
        TipeRujukan.addItemListener(new java.awt.event.ItemListener() {
            public void itemStateChanged(java.awt.event.ItemEvent evt) {
                TipeRujukanItemStateChanged(evt);
            }
        });
        TipeRujukan.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                TipeRujukanMouseClicked(evt);
            }
        });
        TipeRujukan.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                TipeRujukanActionPerformed(evt);
            }
        });
        TipeRujukan.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                TipeRujukanKeyPressed(evt);
            }
        });
        internalFrame6.add(TipeRujukan);
        TipeRujukan.setBounds(277, 25, 110, 23);

        jLabel34.setForeground(new java.awt.Color(0, 0, 0));
        jLabel34.setText("Catatan :");
        jLabel34.setName("jLabel34"); // NOI18N
        internalFrame6.add(jLabel34);
        jLabel34.setBounds(0, 172, 102, 23);

        Catatan1.setForeground(new java.awt.Color(0, 0, 0));
        Catatan1.setName("Catatan1"); // NOI18N
        Catatan1.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                Catatan1KeyPressed(evt);
            }
        });
        internalFrame6.add(Catatan1);
        Catatan1.setBounds(105, 172, 490, 23);

        LabelPoli2.setForeground(new java.awt.Color(0, 0, 0));
        LabelPoli2.setText("Poli Tujuan :");
        LabelPoli2.setName("LabelPoli2"); // NOI18N
        internalFrame6.add(LabelPoli2);
        LabelPoli2.setBounds(0, 114, 102, 23);

        sepRujuk.setEditable(false);
        sepRujuk.setForeground(new java.awt.Color(0, 0, 0));
        sepRujuk.setName("sepRujuk"); // NOI18N
        sepRujuk.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                sepRujukKeyPressed(evt);
            }
        });
        internalFrame6.add(sepRujuk);
        sepRujuk.setBounds(105, 143, 160, 23);

        BtnResetRujuk.setForeground(new java.awt.Color(0, 0, 0));
        BtnResetRujuk.setIcon(new javax.swing.ImageIcon(getClass().getResource("/picture/New.png"))); // NOI18N
        BtnResetRujuk.setMnemonic('R');
        BtnResetRujuk.setText("Reset");
        BtnResetRujuk.setToolTipText("Alt+R");
        BtnResetRujuk.setName("BtnResetRujuk"); // NOI18N
        BtnResetRujuk.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                BtnResetRujukActionPerformed(evt);
            }
        });
        internalFrame6.add(BtnResetRujuk);
        BtnResetRujuk.setBounds(270, 205, 100, 30);

        WindowRujukan.getContentPane().add(internalFrame6, java.awt.BorderLayout.CENTER);

        WindowCariSEP.setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);
        WindowCariSEP.setName("WindowCariSEP"); // NOI18N
        WindowCariSEP.setUndecorated(true);
        WindowCariSEP.setResizable(false);

        internalFrame7.setBorder(javax.swing.BorderFactory.createTitledBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 153, 255), 3), "::[ Ambil SEP di VClaim ]::", javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Tahoma", 1, 12), new java.awt.Color(0, 0, 0))); // NOI18N
        internalFrame7.setName("internalFrame7"); // NOI18N
        internalFrame7.setWarnaBawah(new java.awt.Color(245, 250, 240));
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
        BtnCloseIn6.setBounds(290, 70, 100, 30);

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
        internalFrame7.add(BtnSimpan6);
        BtnSimpan6.setBounds(20, 70, 100, 30);

        jLabel35.setForeground(new java.awt.Color(0, 0, 0));
        jLabel35.setText("No.SEP VClaim :");
        jLabel35.setName("jLabel35"); // NOI18N
        internalFrame7.add(jLabel35);
        jLabel35.setBounds(0, 25, 102, 23);

        NoSEP.setForeground(new java.awt.Color(0, 0, 0));
        NoSEP.setName("NoSEP"); // NOI18N
        NoSEP.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyTyped(java.awt.event.KeyEvent evt) {
                NoSEPKeyTyped(evt);
            }
            public void keyPressed(java.awt.event.KeyEvent evt) {
                NoSEPKeyPressed(evt);
            }
        });
        internalFrame7.add(NoSEP);
        NoSEP.setBounds(106, 25, 240, 23);

        BtnCari1.setForeground(new java.awt.Color(0, 0, 0));
        BtnCari1.setIcon(new javax.swing.ImageIcon(getClass().getResource("/picture/accept.png"))); // NOI18N
        BtnCari1.setMnemonic('3');
        BtnCari1.setToolTipText("Alt+3");
        BtnCari1.setName("BtnCari1"); // NOI18N
        BtnCari1.setPreferredSize(new java.awt.Dimension(28, 23));
        BtnCari1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                BtnCari1ActionPerformed(evt);
            }
        });
        internalFrame7.add(BtnCari1);
        BtnCari1.setBounds(350, 25, 28, 23);

        WindowCariSEP.getContentPane().add(internalFrame7, java.awt.BorderLayout.CENTER);

        WindowCariNoRujuk.setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);
        WindowCariNoRujuk.setName("WindowCariNoRujuk"); // NOI18N
        WindowCariNoRujuk.setUndecorated(true);
        WindowCariNoRujuk.setResizable(false);
        WindowCariNoRujuk.getContentPane().setLayout(new java.awt.BorderLayout(1, 1));

        internalFrame8.setBorder(javax.swing.BorderFactory.createTitledBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 153, 255), 3), "::[ Cek Riwayat Rujukan Faskes ]::", javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Tahoma", 1, 12), new java.awt.Color(0, 0, 0))); // NOI18N
        internalFrame8.setName("internalFrame8"); // NOI18N
        internalFrame8.setWarnaBawah(new java.awt.Color(245, 250, 240));
        internalFrame8.setLayout(new java.awt.BorderLayout(1, 1));

        TabRujukan.setForeground(new java.awt.Color(0, 0, 0));
        TabRujukan.setToolTipText("");
        TabRujukan.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        TabRujukan.setName("TabRujukan"); // NOI18N
        TabRujukan.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                TabRujukanMouseClicked(evt);
            }
        });

        internalFrame2.setName("internalFrame2"); // NOI18N
        internalFrame2.setLayout(new java.awt.BorderLayout(1, 1));

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

        internalFrame2.add(scrollPane1, java.awt.BorderLayout.CENTER);

        TabRujukan.addTab(".: Faskes 1", internalFrame2);

        internalFrame3.setName("internalFrame3"); // NOI18N
        internalFrame3.setLayout(new java.awt.BorderLayout(1, 1));

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

        internalFrame3.add(scrollPane2, java.awt.BorderLayout.CENTER);

        TabRujukan.addTab(".: Faskes 2 (RS)", internalFrame3);

        internalFrame4.setName("internalFrame4"); // NOI18N
        internalFrame4.setLayout(new java.awt.BorderLayout(1, 1));

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

        internalFrame4.add(scrollPane3, java.awt.BorderLayout.CENTER);

        TabRujukan.addTab(".: Faskes 1 (banyak)", internalFrame4);

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
        TabRujukan.getAccessibleContext().setAccessibleName(".: Faskes 1");

        panelisi1.setName("panelisi1"); // NOI18N
        panelisi1.setPreferredSize(new java.awt.Dimension(44, 54));
        panelisi1.setLayout(new java.awt.FlowLayout(java.awt.FlowLayout.LEFT, 5, 9));

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

        internalFrame8.add(panelisi1, java.awt.BorderLayout.PAGE_END);

        WindowCariNoRujuk.getContentPane().add(internalFrame8, java.awt.BorderLayout.CENTER);

        NoBalasan.setHighlighter(null);
        NoBalasan.setName("NoBalasan"); // NOI18N

        LokasiLaka.setForeground(new java.awt.Color(0, 0, 0));
        LokasiLaka.setHighlighter(null);
        LokasiLaka.setName("LokasiLaka"); // NOI18N
        LokasiLaka.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                LokasiLakaKeyPressed(evt);
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

        nmfaskes_keluar.setForeground(new java.awt.Color(0, 0, 0));
        nmfaskes_keluar.setHighlighter(null);
        nmfaskes_keluar.setName("nmfaskes_keluar"); // NOI18N
        nmfaskes_keluar.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                nmfaskes_keluarKeyPressed(evt);
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

        internalFrame1.setBorder(javax.swing.BorderFactory.createTitledBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 153, 255), 3), "::[ Data Bridging SEP VClaim BPJS ]::", javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Tahoma", 1, 12), new java.awt.Color(0, 0, 0))); // NOI18N
        internalFrame1.setFont(new java.awt.Font("Tahoma", 2, 12)); // NOI18N
        internalFrame1.setName("internalFrame1"); // NOI18N
        internalFrame1.setLayout(new java.awt.BorderLayout(1, 1));

        Scroll.setName("Scroll"); // NOI18N
        Scroll.setOpaque(true);

        tbObat.setAutoCreateRowSorter(true);
        tbObat.setToolTipText("Silahkan klik untuk memilih data yang mau diedit ataupun dihapus");
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
        jPanel3.setPreferredSize(new java.awt.Dimension(44, 100));
        jPanel3.setLayout(new java.awt.BorderLayout(1, 1));

        panelGlass8.setName("panelGlass8"); // NOI18N
        panelGlass8.setPreferredSize(new java.awt.Dimension(44, 44));
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
        panelGlass8.add(BtnEdit);

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

        jPanel3.add(panelGlass8, java.awt.BorderLayout.CENTER);

        panelGlass9.setName("panelGlass9"); // NOI18N
        panelGlass9.setPreferredSize(new java.awt.Dimension(44, 44));
        panelGlass9.setLayout(new java.awt.FlowLayout(java.awt.FlowLayout.LEFT, 5, 9));

        jLabel19.setForeground(new java.awt.Color(0, 0, 0));
        jLabel19.setText("Tgl. SEP :");
        jLabel19.setName("jLabel19"); // NOI18N
        jLabel19.setPreferredSize(new java.awt.Dimension(55, 23));
        panelGlass9.add(jLabel19);

        DTPCari1.setEditable(false);
        DTPCari1.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "18-11-2021" }));
        DTPCari1.setDisplayFormat("dd-MM-yyyy");
        DTPCari1.setName("DTPCari1"); // NOI18N
        DTPCari1.setOpaque(false);
        DTPCari1.setPreferredSize(new java.awt.Dimension(95, 23));
        DTPCari1.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                DTPCari1MouseClicked(evt);
            }
        });
        panelGlass9.add(DTPCari1);

        jLabel21.setForeground(new java.awt.Color(0, 0, 0));
        jLabel21.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel21.setText("s.d.");
        jLabel21.setName("jLabel21"); // NOI18N
        jLabel21.setPreferredSize(new java.awt.Dimension(23, 23));
        panelGlass9.add(jLabel21);

        DTPCari2.setEditable(false);
        DTPCari2.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "18-11-2021" }));
        DTPCari2.setDisplayFormat("dd-MM-yyyy");
        DTPCari2.setName("DTPCari2"); // NOI18N
        DTPCari2.setOpaque(false);
        DTPCari2.setPreferredSize(new java.awt.Dimension(95, 23));
        DTPCari2.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                DTPCari2MouseClicked(evt);
            }
        });
        panelGlass9.add(DTPCari2);

        jLabel6.setForeground(new java.awt.Color(0, 0, 0));
        jLabel6.setText("Key Word :");
        jLabel6.setName("jLabel6"); // NOI18N
        jLabel6.setPreferredSize(new java.awt.Dimension(70, 23));
        panelGlass9.add(jLabel6);

        TCari.setForeground(new java.awt.Color(0, 0, 0));
        TCari.setName("TCari"); // NOI18N
        TCari.setPreferredSize(new java.awt.Dimension(205, 23));
        TCari.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                TCariKeyPressed(evt);
            }
        });
        panelGlass9.add(TCari);

        BtnCari.setForeground(new java.awt.Color(0, 0, 0));
        BtnCari.setIcon(new javax.swing.ImageIcon(getClass().getResource("/picture/accept.png"))); // NOI18N
        BtnCari.setMnemonic('3');
        BtnCari.setText("Tampilkan Data");
        BtnCari.setToolTipText("Alt+3");
        BtnCari.setName("BtnCari"); // NOI18N
        BtnCari.setPreferredSize(new java.awt.Dimension(140, 23));
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
        panelGlass9.add(BtnCari);

        jLabel7.setForeground(new java.awt.Color(0, 0, 0));
        jLabel7.setText("Record :");
        jLabel7.setName("jLabel7"); // NOI18N
        jLabel7.setPreferredSize(new java.awt.Dimension(65, 23));
        panelGlass9.add(jLabel7);

        LCount.setForeground(new java.awt.Color(0, 0, 0));
        LCount.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        LCount.setText("0");
        LCount.setName("LCount"); // NOI18N
        LCount.setPreferredSize(new java.awt.Dimension(50, 23));
        panelGlass9.add(LCount);

        jPanel3.add(panelGlass9, java.awt.BorderLayout.PAGE_START);

        internalFrame1.add(jPanel3, java.awt.BorderLayout.PAGE_END);

        PanelInput.setName("PanelInput"); // NOI18N
        PanelInput.setOpaque(false);
        PanelInput.setPreferredSize(new java.awt.Dimension(560, 360));
        PanelInput.setLayout(new java.awt.BorderLayout(1, 1));

        ChkInput.setForeground(new java.awt.Color(0, 0, 0));
        ChkInput.setIcon(new javax.swing.ImageIcon(getClass().getResource("/picture/143.png"))); // NOI18N
        ChkInput.setMnemonic('I');
        ChkInput.setSelected(true);
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
        ChkInput.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                ChkInputMouseClicked(evt);
            }
        });
        ChkInput.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                ChkInputActionPerformed(evt);
            }
        });
        PanelInput.add(ChkInput, java.awt.BorderLayout.PAGE_END);

        FormInput.setName("FormInput"); // NOI18N
        FormInput.setPreferredSize(new java.awt.Dimension(100, 257));
        FormInput.setLayout(null);

        jLabel4.setForeground(new java.awt.Color(0, 0, 0));
        jLabel4.setText("No.Rawat :");
        jLabel4.setName("jLabel4"); // NOI18N
        FormInput.add(jLabel4);
        jLabel4.setBounds(0, 12, 90, 23);

        TNoRw.setEditable(false);
        TNoRw.setBackground(new java.awt.Color(245, 250, 240));
        TNoRw.setForeground(new java.awt.Color(0, 0, 0));
        TNoRw.setName("TNoRw"); // NOI18N
        FormInput.add(TNoRw);
        TNoRw.setBounds(93, 12, 152, 23);

        TPasien.setEditable(false);
        TPasien.setBackground(new java.awt.Color(245, 250, 240));
        TPasien.setForeground(new java.awt.Color(0, 0, 0));
        TPasien.setHighlighter(null);
        TPasien.setName("TPasien"); // NOI18N
        FormInput.add(TPasien);
        TPasien.setBounds(359, 12, 368, 23);

        TNoRM.setEditable(false);
        TNoRM.setForeground(new java.awt.Color(0, 0, 0));
        TNoRM.setName("TNoRM"); // NOI18N
        TNoRM.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                TNoRMActionPerformed(evt);
            }
        });
        FormInput.add(TNoRM);
        TNoRM.setBounds(247, 12, 110, 23);

        jLabel5.setForeground(new java.awt.Color(0, 0, 0));
        jLabel5.setText("No.Kartu :");
        jLabel5.setName("jLabel5"); // NOI18N
        FormInput.add(jLabel5);
        jLabel5.setBounds(0, 72, 90, 23);

        NoKartu.setEditable(false);
        NoKartu.setBackground(new java.awt.Color(245, 250, 240));
        NoKartu.setForeground(new java.awt.Color(0, 0, 0));
        NoKartu.setComponentPopupMenu(Popup1);
        NoKartu.setName("NoKartu"); // NOI18N
        FormInput.add(NoKartu);
        NoKartu.setBounds(93, 72, 152, 23);

        jLabel20.setForeground(new java.awt.Color(0, 0, 0));
        jLabel20.setText("Tgl.SEP :");
        jLabel20.setName("jLabel20"); // NOI18N
        jLabel20.setPreferredSize(new java.awt.Dimension(55, 23));
        FormInput.add(jLabel20);
        jLabel20.setBounds(190, 102, 50, 23);

        TanggalSEP.setEditable(false);
        TanggalSEP.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "18-11-2021" }));
        TanggalSEP.setDisplayFormat("dd-MM-yyyy");
        TanggalSEP.setName("TanggalSEP"); // NOI18N
        TanggalSEP.setOpaque(false);
        TanggalSEP.setPreferredSize(new java.awt.Dimension(95, 23));
        TanggalSEP.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                TanggalSEPKeyPressed(evt);
            }
        });
        FormInput.add(TanggalSEP);
        TanggalSEP.setBounds(245, 102, 90, 23);

        jLabel22.setForeground(new java.awt.Color(0, 0, 0));
        jLabel22.setText("Tgl.Rujuk :");
        jLabel22.setName("jLabel22"); // NOI18N
        jLabel22.setPreferredSize(new java.awt.Dimension(55, 23));
        FormInput.add(jLabel22);
        jLabel22.setBounds(0, 102, 90, 23);

        TanggalRujuk.setEditable(false);
        TanggalRujuk.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "18-11-2021" }));
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
        FormInput.add(TanggalRujuk);
        TanggalRujuk.setBounds(93, 102, 90, 23);

        jLabel23.setForeground(new java.awt.Color(0, 0, 0));
        jLabel23.setText("No.Rujukan :");
        jLabel23.setName("jLabel23"); // NOI18N
        jLabel23.setPreferredSize(new java.awt.Dimension(55, 23));
        FormInput.add(jLabel23);
        jLabel23.setBounds(430, 72, 70, 23);

        NoRujukan.setForeground(new java.awt.Color(0, 0, 0));
        NoRujukan.setName("NoRujukan"); // NOI18N
        NoRujukan.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                NoRujukanKeyPressed(evt);
            }
        });
        FormInput.add(NoRujukan);
        NoRujukan.setBounds(503, 72, 195, 23);

        jLabel9.setForeground(new java.awt.Color(0, 0, 0));
        jLabel9.setText("No. Surat SKDP :");
        jLabel9.setName("jLabel9"); // NOI18N
        FormInput.add(jLabel9);
        jLabel9.setBounds(380, 102, 85, 23);

        KdPPK.setEditable(false);
        KdPPK.setBackground(new java.awt.Color(245, 250, 240));
        KdPPK.setForeground(new java.awt.Color(0, 0, 0));
        KdPPK.setName("KdPPK"); // NOI18N
        FormInput.add(KdPPK);
        KdPPK.setBounds(470, 162, 75, 23);

        NmPPK.setEditable(false);
        NmPPK.setBackground(new java.awt.Color(245, 250, 240));
        NmPPK.setForeground(new java.awt.Color(0, 0, 0));
        NmPPK.setHighlighter(null);
        NmPPK.setName("NmPPK"); // NOI18N
        FormInput.add(NmPPK);
        NmPPK.setBounds(550, 162, 180, 23);

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
        FormInput.add(btnPPKRujukan);
        btnPPKRujukan.setBounds(352, 162, 28, 23);

        jLabel10.setForeground(new java.awt.Color(0, 0, 0));
        jLabel10.setText("PPK Rujukan :");
        jLabel10.setName("jLabel10"); // NOI18N
        FormInput.add(jLabel10);
        jLabel10.setBounds(0, 162, 90, 23);

        KdPpkRujukan.setEditable(false);
        KdPpkRujukan.setBackground(new java.awt.Color(245, 250, 240));
        KdPpkRujukan.setForeground(new java.awt.Color(0, 0, 0));
        KdPpkRujukan.setHighlighter(null);
        KdPpkRujukan.setName("KdPpkRujukan"); // NOI18N
        FormInput.add(KdPpkRujukan);
        KdPpkRujukan.setBounds(93, 162, 75, 23);

        NmPpkRujukan.setEditable(false);
        NmPpkRujukan.setBackground(new java.awt.Color(245, 250, 240));
        NmPpkRujukan.setForeground(new java.awt.Color(0, 0, 0));
        NmPpkRujukan.setHighlighter(null);
        NmPpkRujukan.setName("NmPpkRujukan"); // NOI18N
        FormInput.add(NmPpkRujukan);
        NmPpkRujukan.setBounds(170, 162, 180, 23);

        jLabel11.setForeground(new java.awt.Color(0, 0, 0));
        jLabel11.setText("Diagnosa Awal :");
        jLabel11.setName("jLabel11"); // NOI18N
        FormInput.add(jLabel11);
        jLabel11.setBounds(0, 192, 90, 23);

        KdPenyakit.setEditable(false);
        KdPenyakit.setBackground(new java.awt.Color(245, 250, 240));
        KdPenyakit.setForeground(new java.awt.Color(0, 0, 0));
        KdPenyakit.setHighlighter(null);
        KdPenyakit.setName("KdPenyakit"); // NOI18N
        FormInput.add(KdPenyakit);
        KdPenyakit.setBounds(93, 192, 75, 23);

        NmPenyakit.setEditable(false);
        NmPenyakit.setBackground(new java.awt.Color(245, 250, 240));
        NmPenyakit.setForeground(new java.awt.Color(0, 0, 0));
        NmPenyakit.setHighlighter(null);
        NmPenyakit.setName("NmPenyakit"); // NOI18N
        FormInput.add(NmPenyakit);
        NmPenyakit.setBounds(170, 192, 180, 23);

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
        FormInput.add(btnDiagnosa);
        btnDiagnosa.setBounds(352, 192, 28, 23);

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
        FormInput.add(btnPoli);
        btnPoli.setBounds(352, 222, 28, 23);

        NmPoli.setEditable(false);
        NmPoli.setBackground(new java.awt.Color(245, 250, 240));
        NmPoli.setForeground(new java.awt.Color(0, 0, 0));
        NmPoli.setHighlighter(null);
        NmPoli.setName("NmPoli"); // NOI18N
        FormInput.add(NmPoli);
        NmPoli.setBounds(170, 222, 180, 23);

        KdPoli.setEditable(false);
        KdPoli.setBackground(new java.awt.Color(245, 250, 240));
        KdPoli.setForeground(new java.awt.Color(0, 0, 0));
        KdPoli.setHighlighter(null);
        KdPoli.setName("KdPoli"); // NOI18N
        FormInput.add(KdPoli);
        KdPoli.setBounds(93, 222, 75, 23);

        LabelKatarak.setForeground(new java.awt.Color(0, 0, 0));
        LabelKatarak.setText("Kasus Katarak :");
        LabelKatarak.setName("LabelKatarak"); // NOI18N
        FormInput.add(LabelKatarak);
        LabelKatarak.setBounds(0, 252, 90, 23);

        jLabel13.setForeground(new java.awt.Color(0, 0, 0));
        jLabel13.setText("Jns.Pelayanan :");
        jLabel13.setName("jLabel13"); // NOI18N
        FormInput.add(jLabel13);
        jLabel13.setBounds(380, 192, 85, 23);

        jLabel14.setForeground(new java.awt.Color(0, 0, 0));
        jLabel14.setText("Catatan :");
        jLabel14.setName("jLabel14"); // NOI18N
        FormInput.add(jLabel14);
        jLabel14.setBounds(0, 282, 90, 23);

        JenisPelayanan.setForeground(new java.awt.Color(0, 0, 0));
        JenisPelayanan.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "1. Ranap", "2. Ralan" }));
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
        FormInput.add(JenisPelayanan);
        JenisPelayanan.setBounds(470, 192, 110, 23);

        LabelKelas.setForeground(new java.awt.Color(0, 0, 0));
        LabelKelas.setText("Kelas :");
        LabelKelas.setName("LabelKelas"); // NOI18N
        FormInput.add(LabelKelas);
        LabelKelas.setBounds(585, 192, 40, 23);

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
        FormInput.add(Kelas);
        Kelas.setBounds(630, 192, 100, 23);

        LabTglkll.setForeground(new java.awt.Color(0, 0, 0));
        LabTglkll.setText("Tgl. Kejadian :");
        LabTglkll.setName("LabTglkll"); // NOI18N
        FormInput.add(LabTglkll);
        LabTglkll.setBounds(920, 12, 80, 23);

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
        FormInput.add(LakaLantas);
        LakaLantas.setBounds(825, 12, 70, 23);

        LabNoSup.setForeground(new java.awt.Color(0, 0, 0));
        LabNoSup.setText("No.SEP Suplesi :");
        LabNoSup.setName("LabNoSup"); // NOI18N
        FormInput.add(LabNoSup);
        LabNoSup.setBounds(740, 252, 80, 23);

        jLabel8.setForeground(new java.awt.Color(0, 0, 0));
        jLabel8.setText("Tgl.Lahir :");
        jLabel8.setName("jLabel8"); // NOI18N
        FormInput.add(jLabel8);
        jLabel8.setBounds(0, 42, 90, 23);

        TglLahir.setEditable(false);
        TglLahir.setBackground(new java.awt.Color(245, 250, 240));
        TglLahir.setForeground(new java.awt.Color(0, 0, 0));
        TglLahir.setHighlighter(null);
        TglLahir.setName("TglLahir"); // NOI18N
        FormInput.add(TglLahir);
        TglLahir.setBounds(93, 42, 152, 23);

        jLabel18.setForeground(new java.awt.Color(0, 0, 0));
        jLabel18.setText("J.K.:");
        jLabel18.setName("jLabel18"); // NOI18N
        FormInput.add(jLabel18);
        jLabel18.setBounds(504, 42, 70, 23);

        JK.setEditable(false);
        JK.setBackground(new java.awt.Color(245, 250, 240));
        JK.setForeground(new java.awt.Color(0, 0, 0));
        JK.setHighlighter(null);
        JK.setName("JK"); // NOI18N
        FormInput.add(JK);
        JK.setBounds(577, 42, 150, 23);

        jLabel24.setForeground(new java.awt.Color(0, 0, 0));
        jLabel24.setText("Peserta :");
        jLabel24.setName("jLabel24"); // NOI18N
        jLabel24.setPreferredSize(new java.awt.Dimension(55, 23));
        FormInput.add(jLabel24);
        jLabel24.setBounds(278, 42, 60, 23);

        JenisPeserta.setEditable(false);
        JenisPeserta.setBackground(new java.awt.Color(245, 250, 240));
        JenisPeserta.setForeground(new java.awt.Color(0, 0, 0));
        JenisPeserta.setHighlighter(null);
        JenisPeserta.setName("JenisPeserta"); // NOI18N
        FormInput.add(JenisPeserta);
        JenisPeserta.setBounds(341, 42, 150, 23);

        jLabel25.setForeground(new java.awt.Color(0, 0, 0));
        jLabel25.setText("Status :");
        jLabel25.setName("jLabel25"); // NOI18N
        jLabel25.setPreferredSize(new java.awt.Dimension(55, 23));
        FormInput.add(jLabel25);
        jLabel25.setBounds(248, 72, 45, 23);

        Status.setEditable(false);
        Status.setBackground(new java.awt.Color(245, 250, 240));
        Status.setForeground(new java.awt.Color(0, 0, 0));
        Status.setHighlighter(null);
        Status.setName("Status"); // NOI18N
        FormInput.add(Status);
        Status.setBounds(295, 72, 130, 23);

        rujukanSEP.setForeground(new java.awt.Color(0, 0, 0));
        rujukanSEP.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        rujukanSEP.setText("- belum terisi -");
        rujukanSEP.setName("rujukanSEP"); // NOI18N
        FormInput.add(rujukanSEP);
        rujukanSEP.setBounds(218, 132, 170, 23);

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
        FormInput.add(AsalRujukan);
        AsalRujukan.setBounds(93, 132, 120, 23);

        jLabel15.setForeground(new java.awt.Color(0, 0, 0));
        jLabel15.setText("Eksekutif :");
        jLabel15.setName("jLabel15"); // NOI18N
        FormInput.add(jLabel15);
        jLabel15.setBounds(395, 222, 70, 23);

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
        FormInput.add(Eksekutif);
        Eksekutif.setBounds(470, 222, 110, 23);

        LabelKelas1.setForeground(new java.awt.Color(0, 0, 0));
        LabelKelas1.setText("COB :");
        LabelKelas1.setName("LabelKelas1"); // NOI18N
        FormInput.add(LabelKelas1);
        LabelKelas1.setBounds(585, 222, 40, 23);

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
        FormInput.add(COB);
        COB.setBounds(630, 222, 100, 23);

        LabKetkll.setForeground(new java.awt.Color(0, 0, 0));
        LabKetkll.setText("Keterangan :");
        LabKetkll.setName("LabKetkll"); // NOI18N
        FormInput.add(LabKetkll);
        LabKetkll.setBounds(730, 102, 90, 23);

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
        FormInput.add(ChkAsa);
        ChkAsa.setBounds(930, 72, 80, 23);

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
        FormInput.add(ChkJasaRaharja);
        ChkJasaRaharja.setBounds(825, 42, 100, 23);

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
        FormInput.add(ChkBPJSTenaga);
        ChkBPJSTenaga.setBounds(930, 42, 140, 23);

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
        FormInput.add(ChkTaspen);
        ChkTaspen.setBounds(825, 72, 80, 23);

        jLabel29.setForeground(new java.awt.Color(0, 0, 0));
        jLabel29.setText("No.Telp :");
        jLabel29.setName("jLabel29"); // NOI18N
        jLabel29.setPreferredSize(new java.awt.Dimension(55, 23));
        FormInput.add(jLabel29);
        jLabel29.setBounds(170, 252, 65, 23);

        NoTelp.setForeground(new java.awt.Color(0, 0, 0));
        NoTelp.setMaxLenth(13);
        NoTelp.setName("NoTelp"); // NOI18N
        NoTelp.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                NoTelpKeyPressed(evt);
            }
        });
        FormInput.add(NoTelp);
        NoTelp.setBounds(240, 252, 150, 23);

        BtnRujukanVclaim.setForeground(new java.awt.Color(0, 0, 0));
        BtnRujukanVclaim.setIcon(new javax.swing.ImageIcon(getClass().getResource("/picture/inventaris.png"))); // NOI18N
        BtnRujukanVclaim.setMnemonic('R');
        BtnRujukanVclaim.setText("Data Rujukan Keluar VCLAIM");
        BtnRujukanVclaim.setToolTipText("Alt+R");
        BtnRujukanVclaim.setName("BtnRujukanVclaim"); // NOI18N
        BtnRujukanVclaim.setPreferredSize(new java.awt.Dimension(100, 30));
        BtnRujukanVclaim.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                BtnRujukanVclaimActionPerformed(evt);
            }
        });
        BtnRujukanVclaim.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                BtnRujukanVclaimKeyPressed(evt);
            }
        });
        FormInput.add(BtnRujukanVclaim);
        BtnRujukanVclaim.setBounds(520, 249, 210, 30);

        jLabel36.setForeground(new java.awt.Color(0, 0, 0));
        jLabel36.setText("Laka Lantas :");
        jLabel36.setName("jLabel36"); // NOI18N
        FormInput.add(jLabel36);
        jLabel36.setBounds(730, 12, 90, 23);

        TanggalKejadian.setEditable(false);
        TanggalKejadian.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "18-11-2021" }));
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
        FormInput.add(TanggalKejadian);
        TanggalKejadian.setBounds(1004, 12, 90, 23);

        LabjaminKll.setForeground(new java.awt.Color(0, 0, 0));
        LabjaminKll.setText("Penjamin Laka :");
        LabjaminKll.setName("LabjaminKll"); // NOI18N
        FormInput.add(LabjaminKll);
        LabjaminKll.setBounds(730, 42, 90, 23);

        Ket.setForeground(new java.awt.Color(0, 0, 0));
        Ket.setHighlighter(null);
        Ket.setName("Ket"); // NOI18N
        Ket.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                KetKeyPressed(evt);
            }
        });
        FormInput.add(Ket);
        Ket.setBounds(825, 102, 270, 23);

        LabProv.setForeground(new java.awt.Color(0, 0, 0));
        LabProv.setText("Provinsi :");
        LabProv.setName("LabProv"); // NOI18N
        FormInput.add(LabProv);
        LabProv.setBounds(730, 132, 90, 23);

        LabKab.setForeground(new java.awt.Color(0, 0, 0));
        LabKab.setText("Kabupaten :");
        LabKab.setName("LabKab"); // NOI18N
        FormInput.add(LabKab);
        LabKab.setBounds(730, 162, 90, 23);

        KdProv.setEditable(false);
        KdProv.setBackground(new java.awt.Color(245, 250, 240));
        KdProv.setForeground(new java.awt.Color(0, 0, 0));
        KdProv.setHighlighter(null);
        KdProv.setName("KdProv"); // NOI18N
        FormInput.add(KdProv);
        KdProv.setBounds(825, 132, 60, 23);

        KdKab.setEditable(false);
        KdKab.setBackground(new java.awt.Color(245, 250, 240));
        KdKab.setForeground(new java.awt.Color(0, 0, 0));
        KdKab.setHighlighter(null);
        KdKab.setName("KdKab"); // NOI18N
        FormInput.add(KdKab);
        KdKab.setBounds(825, 162, 60, 23);

        KdKec.setEditable(false);
        KdKec.setBackground(new java.awt.Color(245, 250, 240));
        KdKec.setForeground(new java.awt.Color(0, 0, 0));
        KdKec.setHighlighter(null);
        KdKec.setName("KdKec"); // NOI18N
        FormInput.add(KdKec);
        KdKec.setBounds(825, 192, 60, 23);

        NmProv.setEditable(false);
        NmProv.setBackground(new java.awt.Color(245, 250, 240));
        NmProv.setForeground(new java.awt.Color(0, 0, 0));
        NmProv.setHighlighter(null);
        NmProv.setName("NmProv"); // NOI18N
        FormInput.add(NmProv);
        NmProv.setBounds(887, 132, 170, 23);

        NmKab.setEditable(false);
        NmKab.setBackground(new java.awt.Color(245, 250, 240));
        NmKab.setForeground(new java.awt.Color(0, 0, 0));
        NmKab.setHighlighter(null);
        NmKab.setName("NmKab"); // NOI18N
        FormInput.add(NmKab);
        NmKab.setBounds(887, 162, 170, 23);

        NmKec.setEditable(false);
        NmKec.setBackground(new java.awt.Color(245, 250, 240));
        NmKec.setForeground(new java.awt.Color(0, 0, 0));
        NmKec.setHighlighter(null);
        NmKec.setName("NmKec"); // NOI18N
        FormInput.add(NmKec);
        NmKec.setBounds(887, 192, 170, 23);

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
        FormInput.add(btnProv);
        btnProv.setBounds(1060, 132, 28, 23);

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
        FormInput.add(btnKab);
        btnKab.setBounds(1060, 162, 28, 23);

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
        FormInput.add(btnKec);
        btnKec.setBounds(1060, 192, 28, 23);

        LabKec.setForeground(new java.awt.Color(0, 0, 0));
        LabKec.setText("Kecamatan :");
        LabKec.setName("LabKec"); // NOI18N
        FormInput.add(LabKec);
        LabKec.setBounds(740, 192, 80, 23);

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
        FormInput.add(suplesi);
        suplesi.setBounds(825, 222, 70, 23);

        LabSup.setForeground(new java.awt.Color(0, 0, 0));
        LabSup.setText("Suplesi :");
        LabSup.setName("LabSup"); // NOI18N
        FormInput.add(LabSup);
        LabSup.setBounds(740, 222, 80, 23);

        NoSEPSuplesi.setForeground(new java.awt.Color(0, 0, 0));
        NoSEPSuplesi.setHighlighter(null);
        NoSEPSuplesi.setName("NoSEPSuplesi"); // NOI18N
        NoSEPSuplesi.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                NoSEPSuplesiKeyPressed(evt);
            }
        });
        FormInput.add(NoSEPSuplesi);
        NoSEPSuplesi.setBounds(825, 252, 270, 23);

        LabelPoli.setForeground(new java.awt.Color(0, 0, 0));
        LabelPoli.setText("Poli Tujuan :");
        LabelPoli.setName("LabelPoli"); // NOI18N
        FormInput.add(LabelPoli);
        LabelPoli.setBounds(0, 222, 90, 23);

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
        FormInput.add(KasusKatarak);
        KasusKatarak.setBounds(93, 252, 70, 23);

        jLabel42.setForeground(new java.awt.Color(0, 0, 0));
        jLabel42.setText("DPJP :");
        jLabel42.setName("jLabel42"); // NOI18N
        FormInput.add(jLabel42);
        jLabel42.setBounds(390, 132, 40, 23);

        noSKDP.setForeground(new java.awt.Color(0, 0, 0));
        noSKDP.setName("noSKDP"); // NOI18N
        noSKDP.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                noSKDPKeyPressed(evt);
            }
        });
        FormInput.add(noSKDP);
        noSKDP.setBounds(470, 102, 228, 23);

        jLabel43.setForeground(new java.awt.Color(0, 0, 0));
        jLabel43.setText("PPK Pelayanan :");
        jLabel43.setName("jLabel43"); // NOI18N
        FormInput.add(jLabel43);
        jLabel43.setBounds(380, 162, 85, 23);

        Kddpjp.setEditable(false);
        Kddpjp.setBackground(new java.awt.Color(245, 250, 240));
        Kddpjp.setForeground(new java.awt.Color(0, 0, 0));
        Kddpjp.setName("Kddpjp"); // NOI18N
        FormInput.add(Kddpjp);
        Kddpjp.setBounds(435, 132, 70, 23);

        NmDPJP.setEditable(false);
        NmDPJP.setBackground(new java.awt.Color(245, 250, 240));
        NmDPJP.setForeground(new java.awt.Color(0, 0, 0));
        NmDPJP.setHighlighter(null);
        NmDPJP.setName("NmDPJP"); // NOI18N
        FormInput.add(NmDPJP);
        NmDPJP.setBounds(508, 132, 190, 23);

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
        FormInput.add(btnDPJP);
        btnDPJP.setBounds(700, 132, 28, 23);

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
        FormInput.add(btnNoSKDP);
        btnNoSKDP.setBounds(700, 102, 28, 23);

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
        FormInput.add(btnNoRujukan);
        btnNoRujukan.setBounds(700, 72, 28, 23);

        jLabel28.setForeground(new java.awt.Color(0, 0, 0));
        jLabel28.setText("Asal Rujukan :");
        jLabel28.setName("jLabel28"); // NOI18N
        FormInput.add(jLabel28);
        jLabel28.setBounds(0, 132, 90, 23);

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

        FormInput.add(Scroll3);
        Scroll3.setBounds(93, 282, 1010, 50);

        PanelInput.add(FormInput, java.awt.BorderLayout.CENTER);

        internalFrame1.add(PanelInput, java.awt.BorderLayout.PAGE_START);

        getContentPane().add(internalFrame1, java.awt.BorderLayout.CENTER);

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void BtnSimpanActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_BtnSimpanActionPerformed
        if (TNoRw.getText().trim().equals("") || TPasien.getText().trim().equals("")) {
            Valid.textKosong(TNoRw, "Pasien");
        } else if (NoKartu.getText().trim().equals("")) {
            Valid.textKosong(NoKartu, "Nomor Kartu");
        } else if (NoRujukan.getText().trim().equals("")) {
            Valid.textKosong(NoRujukan, "Nomor Rujukan");
        } else if (KdPpkRujukan.getText().trim().equals("") || NmPpkRujukan.getText().trim().equals("") || rujukanSEP.getText().equals("- belum terisi -")) {
            Valid.textKosong(KdPpkRujukan, "PPK Rujukan");
        } else if (KdPPK.getText().trim().equals("") || NmPPK.getText().trim().equals("")) {
            Valid.textKosong(KdPPK, "PPK Pelayanan");
        } else if (KdPenyakit.getText().trim().equals("") || NmPenyakit.getText().trim().equals("")) {
            Valid.textKosong(KdPenyakit, "Diagnosa");
        } else if (Catatan.getText().trim().equals("")) {
            Valid.textKosong(Catatan, "Catatan");
        } else if ((JenisPelayanan.getSelectedIndex() == 1) && (KdPoli.getText().trim().equals("") || NmPoli.getText().trim().equals(""))) {
            Valid.textKosong(KdPoli, "Poli Tujuan");
        } else if ((LakaLantas.getSelectedIndex() == 1) && Ket.getText().equals("")) {
            Valid.textKosong(Ket, "Keterangan");
        } else if ((LakaLantas.getSelectedIndex() == 1) && NmProv.getText().equals("")) {
            Valid.textKosong(KdProv, "Provinsi");
        } else if ((LakaLantas.getSelectedIndex() == 1) && NmKab.getText().equals("")) {
            Valid.textKosong(KdKab, "Kabupaten");
        } else if ((LakaLantas.getSelectedIndex() == 1) && NmKec.getText().equals("")) {
            Valid.textKosong(KdKec, "Kecamatan");
        } else if (KdPpkRujukan.getText().equals(KdPPK.getText()) ) {
            if (Kddpjp.getText().trim().equals("") || noSKDP.getText().length() < 6) {
                JOptionPane.showMessageDialog(null, "Dokter DPJP dan No. SKDP harus diisi, karena pasien ini rujukan internal..!");
                btnDPJP.requestFocus();
            }else {
                if (JenisPelayanan.getSelectedIndex() == 0) {
                    insertSEP();
                } else if (JenisPelayanan.getSelectedIndex() == 1) {
                    if (NmPoli.getText().toLowerCase().contains("darurat")) {
                        if (Sequel.cariInteger("select count(no_kartu) from bridging_sep where "
                                + "no_kartu='" + no_peserta + "' and jnspelayanan='" + JenisPelayanan.getSelectedItem().toString().substring(0, 1) + "' "
                                + "and tglsep like '%" + Valid.SetTgl(TanggalSEP.getSelectedItem() + "").substring(0, 10) + "%' and "
                                + "nmpolitujuan like '%darurat%'") >= 3) {
                            JOptionPane.showMessageDialog(null, "Maaf, sebelumnya sudah dilakukan 3x pembuatan SEP di jenis pelayanan yang sama..!!");
                            TCari.requestFocus();
                        } else if (Sequel.cariInteger("select count(no_kartu) from bridging_sep where "
                                + "no_rawat='" + TNoRw.getText() + "' and jnspelayanan='2'") > 0) {
                            JOptionPane.showMessageDialog(null, "Kunjungan dengan No Rawat ini sudah pernah dibuatkan SEP sebelumnya, KLIK PADA KUNJUNGAN YANG BARU!!!");
                            TCari.requestFocus();
                        } else {
                            insertSEP();
                        }
                    } else if (!NmPoli.getText().toLowerCase().contains("darurat")) {
                        if (Sequel.cariInteger("select count(no_kartu) from bridging_sep where "
                                + "no_kartu='" + no_peserta + "' and jnspelayanan='" + JenisPelayanan.getSelectedItem().toString().substring(0, 1) + "' "
                                + "and tglsep like '%" + Valid.SetTgl(TanggalSEP.getSelectedItem() + "").substring(0, 10) + "%' and "
                                + "nmpolitujuan not like '%darurat%'") >= 1) {
                            JOptionPane.showMessageDialog(null, "Maaf, sebelumnya sudah dilakukan pembuatan SEP di jenis pelayanan yang sama..!!");
                            TCari.requestFocus();
                        } else if (Sequel.cariInteger("select count(no_kartu) from bridging_sep where "
                                + "no_rawat='" + TNoRw.getText() + "' and jnspelayanan='1'") > 0) {
                            JOptionPane.showMessageDialog(null, "Kunjungan dengan No Rawat ini sudah pernah dibuatkan SEP sebelumnya, KLIK PADA KUNJUNGAN YANG BARU!!!");
                            TCari.requestFocus();
                        } else {
                            insertSEP();
                        }
                    }
                }                
            }
        } else {
            if(JenisPelayanan.getSelectedIndex()==0){
                insertSEP();
            }else if(JenisPelayanan.getSelectedIndex()==1){
                if(NmPoli.getText().toLowerCase().contains("darurat")){
                    if(Sequel.cariInteger("select count(no_kartu) from bridging_sep where "+
                            "no_kartu='"+no_peserta+"' and jnspelayanan='"+JenisPelayanan.getSelectedItem().toString().substring(0,1)+"' "+
                            "and tglsep like '%"+Valid.SetTgl(TanggalSEP.getSelectedItem()+"").substring(0,10)+"%' and "+
                            "nmpolitujuan like '%darurat%'")>=3){
                        JOptionPane.showMessageDialog(null,"Maaf, sebelumnya sudah dilakukan 3x pembuatan SEP di jenis pelayanan yang sama..!!");
                        TCari.requestFocus();
                    }else if(Sequel.cariInteger("select count(no_kartu) from bridging_sep where "+
                            "no_rawat='"+TNoRw.getText()+"' and jnspelayanan='2'")>0){
                        JOptionPane.showMessageDialog(null,"Kunjungan dengan No Rawat ini sudah pernah dibuatkan SEP sebelumnya, KLIK PADA KUNJUNGAN YANG BARU!!!");
                        TCari.requestFocus();
                    }else{
                        insertSEP();
                    }
                }else if(!NmPoli.getText().toLowerCase().contains("darurat")){
                    if(Sequel.cariInteger("select count(no_kartu) from bridging_sep where "+
                            "no_kartu='"+no_peserta+"' and jnspelayanan='"+JenisPelayanan.getSelectedItem().toString().substring(0,1)+"' "+
                            "and tglsep like '%"+Valid.SetTgl(TanggalSEP.getSelectedItem()+"").substring(0,10)+"%' and "+
                            "nmpolitujuan not like '%darurat%'")>=1){
                        JOptionPane.showMessageDialog(null,"Maaf, sebelumnya sudah dilakukan pembuatan SEP di jenis pelayanan yang sama..!!");
                        TCari.requestFocus();
                    }else if(Sequel.cariInteger("select count(no_kartu) from bridging_sep where "+
                            "no_rawat='"+TNoRw.getText()+"' and jnspelayanan='1'")>0){
                        JOptionPane.showMessageDialog(null,"Kunjungan dengan No Rawat ini sudah pernah dibuatkan SEP sebelumnya, KLIK PADA KUNJUNGAN YANG BARU!!!");
                        TCari.requestFocus();
                    }else{
                        insertSEP();
                    }
                }
            }

        }
}//GEN-LAST:event_BtnSimpanActionPerformed

    private void BtnSimpanKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_BtnSimpanKeyPressed
        if(evt.getKeyCode()==KeyEvent.VK_SPACE){
            BtnSimpanActionPerformed(null);
        }else{
            Valid.pindah(evt,LokasiLaka,BtnBatal);
        }
}//GEN-LAST:event_BtnSimpanKeyPressed

    private void BtnBatalActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_BtnBatalActionPerformed
        emptTeks();
}//GEN-LAST:event_BtnBatalActionPerformed

    private void BtnBatalKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_BtnBatalKeyPressed
        if(evt.getKeyCode()==KeyEvent.VK_SPACE){
            emptTeks();
        }else{Valid.pindah(evt, BtnSimpan, BtnHapus);}
}//GEN-LAST:event_BtnBatalKeyPressed

    private void BtnHapusActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_BtnHapusActionPerformed
        if(tbObat.getSelectedRow()!= -1){
            try {
                x = JOptionPane.showConfirmDialog(rootPane, "Yakin data mau dihapus..??", "Konfirmasi", JOptionPane.YES_NO_OPTION);
                if (x == JOptionPane.YES_OPTION) {
                    bodyWithDeleteRequest();
                }
            }catch (Exception ex) {
                System.out.println("Notifikasi Bridging : "+ex);
                if(ex.toString().contains("UnknownHostException")){
                    JOptionPane.showMessageDialog(null,"Koneksi ke server BPJS terputus...!");
                }
            }
        }else{
            JOptionPane.showMessageDialog(null,"Silahkan pilih dulu data yang mau dihapus..!!");
        }
}//GEN-LAST:event_BtnHapusActionPerformed

    private void BtnHapusKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_BtnHapusKeyPressed
        if(evt.getKeyCode()==KeyEvent.VK_SPACE){
            BtnHapusActionPerformed(null);
        }else{
            Valid.pindah(evt, BtnBatal, BtnEdit);
        }
}//GEN-LAST:event_BtnHapusKeyPressed

    private void BtnEditActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_BtnEditActionPerformed
        if(tbObat.getSelectedRow()!= -1){
            if (TNoRw.getText().trim().equals("") || TPasien.getText().trim().equals("")) {
                Valid.textKosong(TNoRw, "Pasien");
            } else if (NoKartu.getText().trim().equals("")) {
                Valid.textKosong(NoKartu, "Nomor Kartu");
            } else if (NoRujukan.getText().trim().equals("")) {
                Valid.textKosong(NoRujukan, "Nomor Rujukan");
            } else if (KdPpkRujukan.getText().trim().equals("") || NmPpkRujukan.getText().trim().equals("")) {
                Valid.textKosong(KdPpkRujukan, "PPK Rujukan");
            } else if (KdPPK.getText().trim().equals("") || NmPPK.getText().trim().equals("")) {
                Valid.textKosong(KdPPK, "PPK Pelayanan");
            } else if (KdPenyakit.getText().trim().equals("") || NmPenyakit.getText().trim().equals("")) {
                Valid.textKosong(KdPenyakit, "Diagnosa");
            } else if (Catatan.getText().trim().equals("")) {
                Valid.textKosong(Catatan, "Catatan");
            } else if ((LakaLantas.getSelectedIndex() == 1) && Ket.getText().equals("")) {
                Valid.textKosong(Ket, "Keterangan");
            } else if ((LakaLantas.getSelectedIndex() == 1) && NmProv.getText().equals("")) {
                Valid.textKosong(KdProv, "Propinsi");
            } else if ((LakaLantas.getSelectedIndex() == 1) && NmKab.getText().equals("")) {
                Valid.textKosong(KdKab, "Kabupaten");
            } else if ((LakaLantas.getSelectedIndex() == 1) && NmKec.getText().equals("")) {
                Valid.textKosong(KdKec, "Kecamatan");
            } else {
                try {
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
                    
                    URL = prop.getProperty("URLAPIBPJS")+"/SEP/1.1/Update";
                    HttpHeaders headers = new HttpHeaders();
                    headers.setContentType(MediaType.APPLICATION_FORM_URLENCODED);
                    headers.add("X-Cons-ID",prop.getProperty("CONSIDAPIBPJS"));
                    headers.add("X-Timestamp",String.valueOf(api.GetUTCdatetimeAsString()));
                    headers.add("X-Signature",api.getHmac());                    
                    requestJson = "{"
                            + "\"request\":"
                            + "{"
                            + "\"t_sep\":"
                            + "{"
                            + "\"noSep\":\"" + tbObat.getValueAt(tbObat.getSelectedRow(), 0).toString() + "\","
                            + "\"klsRawat\":\"" + Kelas.getSelectedItem().toString().substring(0, 1) + "\","
                            + "\"noMR\":\"" + TNoRM.getText() + "\","
                            + "\"rujukan\": {"
                            + "\"asalRujukan\":\"" + AsalRujukan.getSelectedItem().toString().substring(0, 1) + "\","
                            + "\"tglRujukan\":\"" + Valid.SetTgl(TanggalRujuk.getSelectedItem() + "") + "\","
                            + "\"noRujukan\":\"" + NoRujukan.getText() + "\","
                            + "\"ppkRujukan\":\"" + KdPpkRujukan.getText() + "\""
                            + "},"
                            + "\"catatan\":\"" + Catatan.getText() + "\","
                            + "\"diagAwal\":\"" + KdPenyakit.getText() + "\","
                            + "\"poli\": {"
                            + "\"eksekutif\": \"" + Eksekutif.getSelectedItem().toString().substring(0, 1) + "\""
                            + "},"
                            + "\"cob\": {"
                            + "\"cob\": \"" + COB.getSelectedItem().toString().substring(0, 1) + "\""
                            + "},"
                            + "\"katarak\": {"
                            + "\"katarak\": \"" + KasusKatarak.getSelectedItem().toString().substring(0, 1) + "\""
                            + "},"
                            + "\"skdp\": {"
                            + "\"noSurat\": \"" + noSKDP.getText() + "\","
                            + "\"kodeDPJP\": \"" + Kddpjp.getText() + "\""
                            + "},"
                            + "\"jaminan\": {"
                            + "\"lakaLantas\":\"" + LakaLantas.getSelectedItem().toString().substring(0, 1) + "\","
                            + "\"penjamin\": {"
                            + "\"penjamin\": \"" + penjamin + "\","
                            + "\"tglKejadian\": \"" + tglkkl + "\","
                            + "\"keterangan\": \"" + Ket.getText() + "\","
                            + "\"suplesi\": {"
                            + "\"suplesi\": \"" + suplesi.getSelectedItem().toString().substring(0, 1) + "\","
                            + "\"noSepSuplesi\": \"" + NoSEPSuplesi.getText() + "\","
                            + "\"lokasiLaka\": {"
                            + "\"kdPropinsi\": \"" + KdProv.getText() + "\","
                            + "\"kdKabupaten\": \"" + KdKab.getText() + "\","
                            + "\"kdKecamatan\": \"" + KdKec.getText() + "\""
                            + "}"
                            + "}"
                            + "}"
                            + "},"
                            + "\"noTelp\":\"" + NoTelp.getText() + "\","
                            + "\"user\":\"" + user + "\""
                            + "}"
                            + "}"
                            + "}";
                    
                    HttpEntity requestEntity = new HttpEntity(requestJson,headers);
                    ObjectMapper mapper = new ObjectMapper();
                    JsonNode root = mapper.readTree(api.getRest().exchange(URL, HttpMethod.PUT, requestEntity, String.class).getBody());
                    JsonNode nameNode = root.path("metaData");
                    System.out.println("code : "+nameNode.path("code").asText());
                    System.out.println("message : "+nameNode.path("message").asText());
                    JsonNode response = root.path("response");
                    if(nameNode.path("code").asText().equals("200")){
                        Sequel.mengedit("bridging_sep",
                             "no_sep=?","no_sep=?,no_rawat=?,tglsep=?,tglrujukan=?,no_rujukan=?,kdppkrujukan=?,"+
                             "nmppkrujukan=?,kdppkpelayanan=?,nmppkpelayanan=?,jnspelayanan=?,catatan=?,diagawal=?,"+
                             "nmdiagnosaawal=?,kdpolitujuan=?,nmpolitujuan=?,klsrawat=?,lakalantas=?,lokasilaka=?,"+
                             "user=?,nomr=?,nama_pasien=?,tanggal_lahir=?,peserta=?,jkel=?,no_kartu=?,asal_rujukan=?,eksekutif=?,cob=?,penjamin=?,notelep=?,"+
                             "katarak=?,tglkkl=?,keterangankkl=?,suplesi=?,no_sep_suplesi=?,kdprop=?,nmprop=?,kdkab=?,nmkab=?,kdkec=?,nmkec=?,noskdp=?,"+
                             "kddpjp=?,nmdpdjp=?",45,new String[]{
                             response.asText(),TNoRw.getText(),Valid.SetTgl(TanggalSEP.getSelectedItem()+""),Valid.SetTgl(TanggalRujuk.getSelectedItem()+""),
                             NoRujukan.getText(),KdPpkRujukan.getText(), rujukanSEP.getText(),KdPPK.getText(), NmPPK.getText(),
                             JenisPelayanan.getSelectedItem().toString().substring(0,1), Catatan.getText(),KdPenyakit.getText(),
                             NmPenyakit.getText(),KdPoli.getText(),NmPoli.getText(), Kelas.getSelectedItem().toString().substring(0,1),
                             LakaLantas.getSelectedItem().toString().substring(0,1),LokasiLaka.getText(),user,
                             TNoRM.getText(),TPasien.getText(),TglLahir.getText(),JenisPeserta.getText(),JK.getText(),NoKartu.getText(),
                             AsalRujukan.getSelectedItem().toString(),Eksekutif.getSelectedItem().toString(),
                             COB.getSelectedItem().toString(),penjamin,NoTelp.getText(),KasusKatarak.getSelectedItem().toString(),
                             tglkkl,Ket.getText(),suplesi.getSelectedItem().toString(),
                             NoSEPSuplesi.getText(),KdProv.getText(),NmProv.getText(),KdKab.getText(),NmKab.getText(),
                             KdKec.getText(),NmKec.getText(),noSKDP.getText(),Kddpjp.getText(),NmDPJP.getText(),
                             tbObat.getValueAt(tbObat.getSelectedRow(),0).toString()
                         });
                         Sequel.mengedit("rujuk_masuk","no_rawat=?","no_rawat=?,perujuk=?,no_rujuk=?,kd_rujukan=?",5,new String[]{
                             TNoRw.getText(),NmPpkRujukan.getText(),NoRujukan.getText(),
                             tbObat.getValueAt(tbObat.getSelectedRow(),1).toString(),kode_rujukanya.getText()
                         });
                         emptTeks();
                         tampil();

                    }else{
                        JOptionPane.showMessageDialog(null,nameNode.path("message").asText());
                    }
                }catch (Exception ex) {
                    System.out.println("Notifikasi Bridging : "+ex);
                    if(ex.toString().contains("UnknownHostException")){
                        JOptionPane.showMessageDialog(null,"Koneksi ke server BPJS terputus...!");
                    }
                }
            }
        }else{
            JOptionPane.showMessageDialog(null,"Silahkan pilih dulu data yang mau diganti..!!");
        }
}//GEN-LAST:event_BtnEditActionPerformed

    private void BtnEditKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_BtnEditKeyPressed
        if(evt.getKeyCode()==KeyEvent.VK_SPACE){
            BtnEditActionPerformed(null);
        }else{
            Valid.pindah(evt, BtnHapus, BtnPrint);
        }
}//GEN-LAST:event_BtnEditKeyPressed

    private void BtnKeluarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_BtnKeluarActionPerformed
        WindowRujukan.dispose();
        WindowUpdatePulang.dispose();
        faskes.dispose();
        penyakit.dispose();
        poli.dispose();
        dispose();
}//GEN-LAST:event_BtnKeluarActionPerformed

    private void BtnKeluarKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_BtnKeluarKeyPressed
        if(evt.getKeyCode()==KeyEvent.VK_SPACE){
            BtnKeluarActionPerformed(null);
        }else{Valid.pindah(evt,BtnEdit,TCari);}
}//GEN-LAST:event_BtnKeluarKeyPressed

    private void BtnPrintActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_BtnPrintActionPerformed
        this.setCursor(Cursor.getPredefinedCursor(Cursor.WAIT_CURSOR));
        BtnCariActionPerformed(evt);
        if(tabMode.getRowCount()==0){
            JOptionPane.showMessageDialog(null,"Maaf, data sudah habis. Tidak ada data yang bisa anda print...!!!!");
            TCari.requestFocus();
        }else if(tabMode.getRowCount()!=0){
            Map<String, Object> param = new HashMap<>();
                param.put("namars",var.getnamars());
                param.put("alamatrs",var.getalamatrs());
                param.put("kotars",var.getkabupatenrs());
                param.put("propinsirs",var.getpropinsirs());
                param.put("kontakrs",var.getkontakrs());
                param.put("emailrs",var.getemailrs());
                param.put("logo",Sequel.cariGambar("select logo from setting"));
            Valid.MyReport("rptBridgingDaftar.jrxml","report","::[ Data Bridging SEP ]::",
                    " SELECT no_sep, no_rawat, nomr, nama_pasien, tglsep, tglrujukan, no_rujukan, kdppkrujukan, "
                    + "nmppkrujukan, kdppkpelayanan, nmppkpelayanan, IF (jnspelayanan = '1','1. Ranap','2. Ralan') jns_pely, "
                    + "catatan, diagawal, nmdiagnosaawal, kdpolitujuan, nmpolitujuan, "
                    + "IF (klsrawat = '1','1. Kelas 1',IF (klsrawat = '2','2. Kelas 2','3. Kelas 3')) kls_rwt, "
                    + "IF (lakalantas = '0','0. Tidak','1. Ya') kasus_laka, lokasilaka, USER, tanggal_lahir, "
                    + "peserta, jkel, no_kartu, tglpulang, asal_rujukan, eksekutif, cob, penjamin, notelep, "
                    + "katarak, tglkkl, keterangankkl, suplesi, no_sep_suplesi, kdprop, nmprop, kdkab, nmkab, kdkec, "
                    + "nmkec, noskdp, kddpjp, nmdpdjp from bridging_sep where "                    
                    + "tglsep between '" + Valid.SetTgl(DTPCari1.getSelectedItem() + "") + "' and '" + Valid.SetTgl(DTPCari2.getSelectedItem() + "") + "' and nomr like '%" + TCari.getText().trim() + "%' or "
                    + "tglsep between '" + Valid.SetTgl(DTPCari1.getSelectedItem() + "") + "' and '" + Valid.SetTgl(DTPCari2.getSelectedItem() + "") + "' and nama_pasien like '%" + TCari.getText().trim() + "%' or "
                    + "tglsep between '" + Valid.SetTgl(DTPCari1.getSelectedItem() + "") + "' and '" + Valid.SetTgl(DTPCari2.getSelectedItem() + "") + "' and nmppkrujukan like '%" + TCari.getText().trim() + "%' or "
                    + "tglsep between '" + Valid.SetTgl(DTPCari1.getSelectedItem() + "") + "' and '" + Valid.SetTgl(DTPCari2.getSelectedItem() + "") + "' and diagawal like '%" + TCari.getText().trim() + "%' or "
                    + "tglsep between '" + Valid.SetTgl(DTPCari1.getSelectedItem() + "") + "' and '" + Valid.SetTgl(DTPCari2.getSelectedItem() + "") + "' and nmdiagnosaawal like '%" + TCari.getText().trim() + "%' or "
                    + "tglsep between '" + Valid.SetTgl(DTPCari1.getSelectedItem() + "") + "' and '" + Valid.SetTgl(DTPCari2.getSelectedItem() + "") + "' and no_rawat like '%" + TCari.getText().trim() + "%' or "
                    + "tglsep between '" + Valid.SetTgl(DTPCari1.getSelectedItem() + "") + "' and '" + Valid.SetTgl(DTPCari2.getSelectedItem() + "") + "' and nmpolitujuan like '%" + TCari.getText().trim() + "%' order by tglsep",param);                     
        }
        this.setCursor(Cursor.getDefaultCursor());
}//GEN-LAST:event_BtnPrintActionPerformed

    private void BtnPrintKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_BtnPrintKeyPressed
        if(evt.getKeyCode()==KeyEvent.VK_SPACE){
            BtnPrintActionPerformed(null);
        }else{
            Valid.pindah(evt, BtnEdit, BtnKeluar);
        }
}//GEN-LAST:event_BtnPrintKeyPressed

    private void TCariKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_TCariKeyPressed
        if(evt.getKeyCode()==KeyEvent.VK_ENTER){
            BtnCariActionPerformed(null);
        }else if(evt.getKeyCode()==KeyEvent.VK_PAGE_DOWN){
            BtnCari.requestFocus();
        }else if(evt.getKeyCode()==KeyEvent.VK_PAGE_UP){
            BtnKeluar.requestFocus();
        }
}//GEN-LAST:event_TCariKeyPressed

    private void BtnCariActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_BtnCariActionPerformed
        emptTeks();
        tampil();
}//GEN-LAST:event_BtnCariActionPerformed

    private void BtnCariKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_BtnCariKeyPressed
        if(evt.getKeyCode()==KeyEvent.VK_SPACE){
            BtnCariActionPerformed(null);
        }else{
            Valid.pindah(evt, TCari, BtnAll);
        }
}//GEN-LAST:event_BtnCariKeyPressed

    private void BtnAllActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_BtnAllActionPerformed
        TCari.setText("");
        tampil();
        emptTeks();
}//GEN-LAST:event_BtnAllActionPerformed

    private void BtnAllKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_BtnAllKeyPressed
        if(evt.getKeyCode()==KeyEvent.VK_SPACE){
            tampil();
            TCari.setText("");
        }else{
            Valid.pindah(evt, BtnCari, TPasien);
        }
}//GEN-LAST:event_BtnAllKeyPressed

    private void tbObatMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tbObatMouseClicked
        if (tabMode.getRowCount() != 0) {
            try {
                getData();
            } catch (java.lang.NullPointerException e) {
            }
//            if (evt.getClickCount() == 2) {
//                i = tbObat.getSelectedColumn();
//                if (i == 0) {
//                    ppSEPBtnPrintActionPerformed(null);
//                } else if (i == 1) {
//                    ppPulangBtnPrintActionPerformed(null);
//                } else if (i == 2) {
//                    ppDetailSEPPesertaBtnPrintActionPerformed(null);
//                } else if (i == 3) {
//                    ppRujukanBtnPrintActionPerformed(null);
//                }
//            }
        }
}//GEN-LAST:event_tbObatMouseClicked

    private void tbObatKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_tbObatKeyPressed
        if (tabMode.getRowCount() != 0) {
            if ((evt.getKeyCode() == KeyEvent.VK_ENTER) || (evt.getKeyCode() == KeyEvent.VK_UP) || (evt.getKeyCode() == KeyEvent.VK_DOWN)) {
                try {
                    getData();
                } catch (java.lang.NullPointerException e) {
                }
            } else if (evt.getKeyCode() == KeyEvent.VK_SPACE) {
                try {
                    getData();
                } catch (java.lang.NullPointerException e) {
                }
//                i = tbObat.getSelectedColumn();
//                if (i == 0) {
//                    ppSEPBtnPrintActionPerformed(null);
//                } else if (i == 1) {
//                    ppPulangBtnPrintActionPerformed(null);
//                } else if (i == 2) {
//                    ppDetailSEPPesertaBtnPrintActionPerformed(null);
//                } else if (i == 3) {
//                    ppRujukanBtnPrintActionPerformed(null);
//                }
            }
        }
}//GEN-LAST:event_tbObatKeyPressed

    private void NoRujukanKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_NoRujukanKeyPressed
        Valid.pindah(evt, TCari, TanggalRujuk);
    }//GEN-LAST:event_NoRujukanKeyPressed

    private void btnPPKRujukanActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnPPKRujukanActionPerformed
        var.setform("BPJSDataSEP");
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
        Valid.pindah(evt,AsalRujukan,btnDiagnosa);
    }//GEN-LAST:event_btnPPKRujukanKeyPressed

    private void btnDiagnosaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnDiagnosaActionPerformed
        pilihan=1;
        penyakit.setSize(internalFrame1.getWidth()-40,internalFrame1.getHeight()-40);
        penyakit.setLocationRelativeTo(internalFrame1);
        penyakit.setVisible(true);
        penyakit.fokus();
    }//GEN-LAST:event_btnDiagnosaActionPerformed

    private void btnDiagnosaKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_btnDiagnosaKeyPressed
        Valid.pindah(evt,btnPPKRujukan,btnPoli);
    }//GEN-LAST:event_btnDiagnosaKeyPressed

    private void btnPoliActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnPoliActionPerformed
        pilihan=1;
        poli.setSize(internalFrame1.getWidth()-40,internalFrame1.getHeight()-40);
        poli.setLocationRelativeTo(internalFrame1);
        poli.setVisible(true);
        poli.fokus();
    }//GEN-LAST:event_btnPoliActionPerformed

    private void btnPoliKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_btnPoliKeyPressed
        Valid.pindah(evt,btnDiagnosa,LakaLantas);
    }//GEN-LAST:event_btnPoliKeyPressed

    private void LokasiLakaKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_LokasiLakaKeyPressed
        Valid.pindah(evt,Catatan,BtnSimpan);
    }//GEN-LAST:event_LokasiLakaKeyPressed

    private void formWindowOpened(java.awt.event.WindowEvent evt) {//GEN-FIRST:event_formWindowOpened
        tampil();
        if (var.getform().equals("DlgReg")
                || var.getform().equals("DlgIGD")
                || var.getform().equals("DlgKamarInap")
                || var.getform().equals("DlgBookingRegistrasi")
                || var.getform().equals("DlgKasirRalan")) {
            no_peserta = Sequel.cariIsi("select no_peserta from pasien where no_rkm_medis=?", TNoRM.getText());
            if (no_peserta.trim().equals("")) {
                JOptionPane.showMessageDialog(null, "Pasien tidak mempunyai kepesertaan BPJS");
                dispose();
            }else{
                cekViaBPJSKartu.tampil(no_peserta);
                if(cekViaBPJSKartu.informasi.equals("OK")){
                    if(cekViaBPJSKartu.statusPesertaketerangan.equals("AKTIF")){
//                        TPasien.setText(cekViaBPJSKartu.nama);
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
                            NmPpkRujukan.setText(NmPPK.getText());
                            Sequel.cariIsi("select kd_rujukan from master_nama_rujukan where status='1' and kode_faskes_bpjs=? ", kode_rujukanya, KdPpkRujukan.getText());
                        }
                        
                        if(cekViaBPJSKartu.hakKelaskode.equals("1")){
                            Kelas.setSelectedIndex(0);
                        }else if(cekViaBPJSKartu.hakKelaskode.equals("2")){
                            Kelas.setSelectedIndex(1);
                        }else if(cekViaBPJSKartu.hakKelaskode.equals("3")){
                            Kelas.setSelectedIndex(2);
                        }
                        NoTelp.setText(cekViaBPJSKartu.mrnoTelepon);
                        NoTelp.requestFocus();
                    }else{
                        JOptionPane.showMessageDialog(null,"Status kepesertaan tidak aktif..!!");
                        dispose();
                    }
                }else{
                    dispose();
                }
            }
        }
    }//GEN-LAST:event_formWindowOpened

    private void ChkInputActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_ChkInputActionPerformed
        isForm();
    }//GEN-LAST:event_ChkInputActionPerformed

    private void ppSEPBtnPrintActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_ppSEPBtnPrintActionPerformed
        if (tbObat.getSelectedRow() != -1) {
            this.setCursor(Cursor.getPredefinedCursor(Cursor.WAIT_CURSOR));
            Map<String, Object> param = new HashMap<>();
            param.put("namars", var.getnamars());
            param.put("alamatrs", var.getalamatrs());
            param.put("kotars", var.getkabupatenrs());
            param.put("propinsirs", var.getpropinsirs());
            param.put("kontakrs", var.getkontakrs());
            param.put("logo", Sequel.cariGambar("select bpjs from gambar"));
            if (JenisPelayanan.getSelectedIndex() == 0) {
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
                        + " IF(bridging_sep.notelep='','-',bridging_sep.notelep) notelep FROM bridging_sep where no_sep='" + tbObat.getValueAt(tbObat.getSelectedRow(), 0).toString() + "'", param);

            } else {
                Valid.MyReport("rptBridgingSEP2.jrxml", "report", "::[ Cetak SEP Rawat Jalan ]::",
                        " SELECT bridging_sep.no_sep, bridging_sep.no_rawat, bridging_sep.nomr, bridging_sep.nama_pasien, bridging_sep.tglsep, "
                        + " bridging_sep.tglrujukan, bridging_sep.no_rujukan, bridging_sep.kdppkrujukan, bridging_sep.nmppkrujukan, bridging_sep.kdppkpelayanan, "
                        + " bridging_sep.nmppkpelayanan, IF (bridging_sep.jnspelayanan = '1','R.Inap','R.Jalan') jns_rawat, bridging_sep.catatan, bridging_sep.diagawal, "
                        + " bridging_sep.nmdiagnosaawal, bridging_sep.kdpolitujuan, bridging_sep.nmpolitujuan, "
                        + " IF (bridging_sep.klsrawat = '1','Kelas 1',IF (bridging_sep.klsrawat = '2','Kelas 2','Kelas 3')) kelas, "
                        + " IF (bridging_sep.lakalantas = '0','Kasus Kecelakaan','Bukan Kasus Kecelakaan'), bridging_sep.lokasilaka, bridging_sep. USER, "
                        + " CONCAT(DATE_FORMAT(bridging_sep.tanggal_lahir,'%Y-%m-%d'),' Kelamin : ',IF(bridging_sep.jkel='L','Laki-laki','Perempuan')) tanggal_lahir, "
                        + " bridging_sep.peserta, bridging_sep.jkel, bridging_sep.no_kartu, bridging_sep.asal_rujukan, bridging_sep.eksekutif, "
                        + " IF(bridging_sep.cob='0. Tidak','-',bridging_sep.cob) cob, IF(bridging_sep.penjamin='','-',bridging_sep.penjamin) penjamin, "
                        + " IF(bridging_sep.notelep='','-',bridging_sep.notelep) notelep FROM bridging_sep where no_sep='" + tbObat.getValueAt(tbObat.getSelectedRow(), 0).toString() + "'", param);
                
                Sequel.mengedit("kelengkapan_booking_sep_bpjs", "no_rawat='" + TNoRw.getText() + "'", "status_cetak_sep='SUDAH' ");
            }
            this.setCursor(Cursor.getDefaultCursor());
        } else {
            JOptionPane.showMessageDialog(null, "Maaf, silahkan pilih data SEP yang mau dicetak...!!!!");
            BtnBatal.requestFocus();
        }
    }//GEN-LAST:event_ppSEPBtnPrintActionPerformed

    private void ppPulangBtnPrintActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_ppPulangBtnPrintActionPerformed
        this.setCursor(Cursor.getPredefinedCursor(Cursor.WAIT_CURSOR));
        if (tbObat.getSelectedRow() != -1) {
            WindowUpdatePulang.setLocationRelativeTo(internalFrame1);
            WindowUpdatePulang.setVisible(true);
        } else {
            JOptionPane.showMessageDialog(null, "Maaf, silahkan pilih data SEP yang mau diupdate tanggal pulangnya...!!!!");
            BtnBatal.requestFocus();
        }
        this.setCursor(Cursor.getDefaultCursor());
    }//GEN-LAST:event_ppPulangBtnPrintActionPerformed

    private void BtnCloseIn4ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_BtnCloseIn4ActionPerformed
        WindowUpdatePulang.dispose();
    }//GEN-LAST:event_BtnCloseIn4ActionPerformed

    private void BtnSimpan4ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_BtnSimpan4ActionPerformed
        if (TNoRw.getText().trim().equals("") || TPasien.getText().trim().equals("")) {
            Valid.textKosong(TNoRw, "Pasien");
        } else if (NoKartu.getText().trim().equals("")) {
            Valid.textKosong(NoKartu, "Nomor Kartu");
        } else if (NoRujukan.getText().trim().equals("")) {
            Valid.textKosong(NoRujukan, "Nomor Rujukan");
        } else if (KdPpkRujukan.getText().trim().equals("") || NmPpkRujukan.getText().trim().equals("")) {
            Valid.textKosong(KdPpkRujukan, "PPK Rujukan");
        } else if (KdPPK.getText().trim().equals("") || NmPPK.getText().trim().equals("")) {
            Valid.textKosong(KdPPK, "PPK Pelayanan");
        } else if (KdPenyakit.getText().trim().equals("") || NmPenyakit.getText().trim().equals("")) {
            Valid.textKosong(KdPenyakit, "Diagnosa");
        } else if (Catatan.getText().trim().equals("")) {
            Valid.textKosong(Catatan, "Catatan");
        } else {
            try {
                URL = prop.getProperty("URLAPIBPJS") + "/Sep/updtglplg";

                HttpHeaders headers = new HttpHeaders();
                headers.setContentType(MediaType.APPLICATION_FORM_URLENCODED);
                headers.add("X-Cons-ID", prop.getProperty("CONSIDAPIBPJS"));
                headers.add("X-Timestamp", String.valueOf(api.GetUTCdatetimeAsString()));
                headers.add("X-Signature", api.getHmac());
                requestJson = "{"
                        + "\"request\":"
                        + "{"
                        + "\"t_sep\":"
                        + "{"
                        + "\"noSep\":\"" + tbObat.getValueAt(tbObat.getSelectedRow(), 0).toString() + "\","
                        + "\"tglPulang\":\"" + Valid.SetTgl(TanggalPulang.getSelectedItem() + "") + "\","
                        + "\"user\":\"" + user + "\""
                        + "}"
                        + "}"
                        + "}";
                HttpEntity requestEntity = new HttpEntity(requestJson, headers);
                ObjectMapper mapper = new ObjectMapper();
                JsonNode root = mapper.readTree(api.getRest().exchange(URL, HttpMethod.PUT, requestEntity, String.class).getBody());
                JsonNode nameNode = root.path("metaData");
                System.out.println("code : " + nameNode.path("code").asText());
                System.out.println("message : " + nameNode.path("message").asText());
                JsonNode response = root.path("response");
                if (nameNode.path("code").asText().equals("200")) {
                    Sequel.mengedit("bridging_sep", "no_sep=?", "tglpulang=?", 2, new String[]{
                        Valid.SetTgl(TanggalPulang.getSelectedItem() + "") + " " + TanggalPulang.getSelectedItem().toString().substring(11, 19),
                        tbObat.getValueAt(tbObat.getSelectedRow(), 0).toString()
                    });
                    emptTeks();
                    tampil();
                    JOptionPane.showMessageDialog(null, "Proses update pulang selesai..!!");
                    WindowUpdatePulang.dispose();
                } else {
                    JOptionPane.showMessageDialog(null, nameNode.path("message").asText());
                }
            } catch (Exception ex) {
                System.out.println("Notifikasi Bridging : " + ex);
                if (ex.toString().contains("UnknownHostException")) {
                    JOptionPane.showMessageDialog(null, "Koneksi ke server BPJS terputus...!");
                }
            }
        }
    }//GEN-LAST:event_BtnSimpan4ActionPerformed

    private void TanggalRujukKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_TanggalRujukKeyPressed
        Valid.pindah(evt, NoRujukan, TanggalSEP);
    }//GEN-LAST:event_TanggalRujukKeyPressed

    private void TanggalSEPKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_TanggalSEPKeyPressed
        Valid.pindah(evt, TanggalRujuk,AsalRujukan);
    }//GEN-LAST:event_TanggalSEPKeyPressed

    private void LakaLantasKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_LakaLantasKeyPressed
        Valid.pindah(evt,btnPoli,JenisPelayanan);
    }//GEN-LAST:event_LakaLantasKeyPressed

    private void JenisPelayananKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_JenisPelayananKeyPressed
        Valid.pindah(evt,LakaLantas,Kelas);
    }//GEN-LAST:event_JenisPelayananKeyPressed

    private void KelasKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_KelasKeyPressed
        Valid.pindah(evt,JenisPelayanan,Eksekutif);
    }//GEN-LAST:event_KelasKeyPressed

    private void ppDetailSEPPesertaBtnPrintActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_ppDetailSEPPesertaBtnPrintActionPerformed
        this.setCursor(Cursor.getPredefinedCursor(Cursor.WAIT_CURSOR));        
        if(tbObat.getSelectedRow()!= -1){
            BPJSCekDetailSEP detail=new BPJSCekDetailSEP(null,true);
            detail.tampil(tbObat.getValueAt(tbObat.getSelectedRow(),0).toString());
            detail.setSize(internalFrame1.getWidth()-40,internalFrame1.getHeight()-40);
            detail.setLocationRelativeTo(internalFrame1);
            detail.setVisible(true);
        }else{
            JOptionPane.showMessageDialog(null,"Maaf, silahkan pilih data SEP ...!!!!");
            BtnBatal.requestFocus();
        }
        this.setCursor(Cursor.getDefaultCursor());  
    }//GEN-LAST:event_ppDetailSEPPesertaBtnPrintActionPerformed

    private void JenisPelayananItemStateChanged(java.awt.event.ItemEvent evt) {//GEN-FIRST:event_JenisPelayananItemStateChanged
        cekLAYAN();
        cekPPKRUJUKAN();
    }//GEN-LAST:event_JenisPelayananItemStateChanged

    private void AsalRujukanKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_AsalRujukanKeyPressed
        Valid.pindah(evt, TanggalSEP,btnPPKRujukan);
    }//GEN-LAST:event_AsalRujukanKeyPressed

    private void EksekutifKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_EksekutifKeyPressed
        Valid.pindah(evt,Kelas,COB);
    }//GEN-LAST:event_EksekutifKeyPressed

    private void COBKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_COBKeyPressed
        Valid.pindah(evt,Eksekutif,Catatan);
    }//GEN-LAST:event_COBKeyPressed

    private void ChkAsaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_ChkAsaActionPerformed

    }//GEN-LAST:event_ChkAsaActionPerformed

    private void ChkJasaRaharjaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_ChkJasaRaharjaActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_ChkJasaRaharjaActionPerformed

    private void ChkBPJSTenagaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_ChkBPJSTenagaActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_ChkBPJSTenagaActionPerformed

    private void ChkTaspenActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_ChkTaspenActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_ChkTaspenActionPerformed

    private void NoTelpKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_NoTelpKeyPressed
        // TODO add your handling code here:
    }//GEN-LAST:event_NoTelpKeyPressed

    private void TNoRMActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_TNoRMActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_TNoRMActionPerformed

    private void ppRujukanBtnPrintActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_ppRujukanBtnPrintActionPerformed
        this.setCursor(Cursor.getPredefinedCursor(Cursor.WAIT_CURSOR));
        if (tbObat.getSelectedRow() != -1) {
            WindowRujukan.setLocationRelativeTo(internalFrame1);
            WindowRujukan.setVisible(true);
        } else {
            JOptionPane.showMessageDialog(null, "Maaf, silahkan pilih data SEP yang mau dibuatkan rujukan...!!!!");
            BtnBatal.requestFocus();
        }
        this.setCursor(Cursor.getDefaultCursor());
    }//GEN-LAST:event_ppRujukanBtnPrintActionPerformed

    private void BtnCloseIn5ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_BtnCloseIn5ActionPerformed
        WindowRujukan.dispose();
    }//GEN-LAST:event_BtnCloseIn5ActionPerformed

    private void BtnSimpan5ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_BtnSimpan5ActionPerformed
        if (TNoRw.getText().trim().equals("")||TPasien.getText().trim().equals("")) {
            Valid.textKosong(TNoRw, "Pasien");
        }else if (NoKartu.getText().trim().equals("")) {
            Valid.textKosong(NoKartu, "Nomor Kartu");
        }else if (KdPpkRujukan1.getText().trim().equals("")||NmPpkRujukan1.getText().trim().equals("")) {
            Valid.textKosong(KdPpkRujukan1, "PPK Rujukan");
        }else if (KdPPK.getText().trim().equals("")||NmPPK.getText().trim().equals("")) {
            Valid.textKosong(KdPPK, "PPK Pelayanan");
        }else if (KdPenyakit1.getText().trim().equals("")||NmPenyakit1.getText().trim().equals("")) {
            Valid.textKosong(KdPenyakit1, "Diagnosa");
        }else if (Catatan1.getText().trim().equals("")) {
            Valid.textKosong(Catatan1, "Catatan");
        }else{
            try {
                URL = prop.getProperty("URLAPIBPJS")+"/Rujukan/insert";
                HttpHeaders headers = new HttpHeaders();
                headers.setContentType(MediaType.APPLICATION_FORM_URLENCODED);
                headers.add("X-Cons-ID",prop.getProperty("CONSIDAPIBPJS"));
                headers.add("X-Timestamp",String.valueOf(api.GetUTCdatetimeAsString()));
                headers.add("X-Signature",api.getHmac());
                requestJson ="{" +
                                "\"request\": {" +
                                    "\"t_rujukan\": {" +
                                        "\"noSep\": \""+tbObat.getValueAt(tbObat.getSelectedRow(),0).toString()+"\"," +
                                        "\"tglRujukan\": \""+Valid.SetTgl(TanggalRujukKeluar.getSelectedItem()+"")+"\"," +
                                        "\"ppkDirujuk\": \""+KdPpkRujukan1.getText()+"\"," +
                                        "\"jnsPelayanan\": \""+JenisPelayanan1.getSelectedItem().toString().substring(0,1)+"\"," +
                                        "\"catatan\": \""+Catatan1.getText()+"\"," +
                                        "\"diagRujukan\": \""+KdPenyakit1.getText()+"\"," +
                                        "\"tipeRujukan\": \""+TipeRujukan.getSelectedItem().toString().substring(0,1)+"\"," +
                                        "\"poliRujukan\": \""+KdPoli1.getText()+"\"," +
                                        "\"user\": \""+user+"\"" +
                                    "}" +
                                "}" +
                            "}";
                HttpEntity requestEntity = new HttpEntity(requestJson,headers);
                ObjectMapper mapper = new ObjectMapper();
                JsonNode root = mapper.readTree(api.getRest().exchange(URL, HttpMethod.POST, requestEntity, String.class).getBody());
                JsonNode nameNode = root.path("metaData");
                System.out.println("code : "+nameNode.path("code").asText());
                System.out.println("message : "+nameNode.path("message").asText());
                JsonNode response = root.path("response");
                if(nameNode.path("code").asText().equals("200")){
                    if(Sequel.menyimpantf2("bridging_rujukan_bpjs","?,?,?,?,?,?,?,?,?,?,?,?,?","No.Rujukan",13,new String[]{
                            tbObat.getValueAt(tbObat.getSelectedRow(),0).toString(),Valid.SetTgl(TanggalRujukKeluar.getSelectedItem()+""),
                            KdPpkRujukan1.getText(),NmPpkRujukan1.getText(),JenisPelayanan1.getSelectedItem().toString().substring(0,1),
                            Catatan1.getText(),KdPenyakit1.getText(),NmPenyakit1.getText(),
                            TipeRujukan.getSelectedItem().toString(),KdPoli1.getText(),
                            NmPoli1.getText(),response.path("rujukan").path("noRujukan").asText(),
                            user
                        })==true){
                        
                        Sequel.cariIsi("select nama_rujukan from master_nama_rujukan where kode_faskes_bpjs=? ", nmfaskes_keluar, KdPpkRujukan1.getText());
                        Sequel.cariIsi("select kd_rujukan from master_nama_rujukan where kode_faskes_bpjs=? ", kode_rujukanya, KdPpkRujukan1.getText());
                        
                        Sequel.menyimpan("rujuk", "'" + response.path("rujukan").path("noRujukan").asText() + "','"
                                + TNoRw.getText() + "','" + nmfaskes_keluar.getText() + "','"
                                + Valid.SetTgl(TanggalRujukKeluar.getSelectedItem() + "") + "','"
                                + NmPenyakit1.getText() + "','" + Sequel.cariIsi("select kd_dokter from reg_periksa where no_rawat=?", TNoRw.getText())
                                + "','-','-','" + Catatan1.getText() + "','12:00:01','" + kode_rujukanya.getText() + "'", "No.Rujuk");
                        
                        this.setCursor(Cursor.getPredefinedCursor(Cursor.WAIT_CURSOR)); 
                        Map<String, Object> param = new HashMap<>();
                        param.put("namars",var.getnamars());
                        param.put("alamatrs",var.getalamatrs());
                        param.put("kotars",var.getkabupatenrs());
                        param.put("propinsirs",var.getpropinsirs());
                        param.put("kontakrs",var.getkontakrs());
                        param.put("norujuk",response.path("rujukan").path("noRujukan").asText());
                        param.put("logo",Sequel.cariGambar("select bpjs from gambar")); 
                        Valid.MyReport("rptBridgingRujukanBPJS.jrxml",param,"::[ Surat Rujukan Keluar VClaim BPJS ]::");
                        WindowRujukan.dispose();
                        this.setCursor(Cursor.getDefaultCursor());
                    }
                Sequel.menyimpan("history_user", "Now(),'" + TNoRw.getText() + "','" + var.getkode() + "','Rujukan Keluar VClaim','Simpan'");
                }else{
                    JOptionPane.showMessageDialog(null,nameNode.path("message").asText());
                }
            }catch (Exception ex) {
                System.out.println("Notifikasi Bridging : "+ex);
                if(ex.toString().contains("UnknownHostException")){
                    JOptionPane.showMessageDialog(null,"Koneksi ke server BPJS terputus...!");
                }
            }
        }
    }//GEN-LAST:event_BtnSimpan5ActionPerformed

    private void btnPPKRujukan1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnPPKRujukan1ActionPerformed
        pilihan=2;
        faskes.setSize(internalFrame1.getWidth()-40,internalFrame1.getHeight()-40);
        faskes.setLocationRelativeTo(internalFrame1);
        faskes.setVisible(true);
        faskes.fokus();
    }//GEN-LAST:event_btnPPKRujukan1ActionPerformed

    private void btnPPKRujukan1KeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_btnPPKRujukan1KeyPressed
        // TODO add your handling code here:
    }//GEN-LAST:event_btnPPKRujukan1KeyPressed

    private void JenisPelayanan1ItemStateChanged(java.awt.event.ItemEvent evt) {//GEN-FIRST:event_JenisPelayanan1ItemStateChanged
        // TODO add your handling code here:
    }//GEN-LAST:event_JenisPelayanan1ItemStateChanged

    private void JenisPelayanan1KeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_JenisPelayanan1KeyPressed
        // TODO add your handling code here:
    }//GEN-LAST:event_JenisPelayanan1KeyPressed

    private void btnDiagnosa1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnDiagnosa1ActionPerformed
        pilihan=2;
        penyakit.setSize(internalFrame1.getWidth()-40,internalFrame1.getHeight()-40);
        penyakit.setLocationRelativeTo(internalFrame1);
        penyakit.setVisible(true);
        penyakit.fokus();
    }//GEN-LAST:event_btnDiagnosa1ActionPerformed

    private void btnDiagnosa1KeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_btnDiagnosa1KeyPressed
        // TODO add your handling code here:
    }//GEN-LAST:event_btnDiagnosa1KeyPressed

    private void btnPoli1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnPoli1ActionPerformed
        pilihan=2;
        poli.setSize(internalFrame1.getWidth()-40,internalFrame1.getHeight()-40);
        poli.setLocationRelativeTo(internalFrame1);
        poli.setVisible(true);
        poli.fokus();
    }//GEN-LAST:event_btnPoli1ActionPerformed

    private void btnPoli1KeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_btnPoli1KeyPressed
        // TODO add your handling code here:
    }//GEN-LAST:event_btnPoli1KeyPressed

    private void TipeRujukanItemStateChanged(java.awt.event.ItemEvent evt) {//GEN-FIRST:event_TipeRujukanItemStateChanged
        // TODO add your handling code here:
    }//GEN-LAST:event_TipeRujukanItemStateChanged

    private void TipeRujukanKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_TipeRujukanKeyPressed
        // TODO add your handling code here:
    }//GEN-LAST:event_TipeRujukanKeyPressed

    private void Catatan1KeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_Catatan1KeyPressed
        // TODO add your handling code here:
    }//GEN-LAST:event_Catatan1KeyPressed

    private void TipeRujukanActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_TipeRujukanActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_TipeRujukanActionPerformed

    private void BtnRujukanVclaimActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_BtnRujukanVclaimActionPerformed
            this.setCursor(Cursor.getPredefinedCursor(Cursor.WAIT_CURSOR));
            BPJSRujukanKeluar rujukanvclaim = new BPJSRujukanKeluar(null, false);
            rujukanvclaim.setSize(internalFrame1.getWidth(), internalFrame1.getHeight());
            rujukanvclaim.setLocationRelativeTo(internalFrame1);
            rujukanvclaim.setVisible(true);
            rujukanvclaim.isCek();
            rujukanvclaim.tampil();
            rujukanvclaim.fokus();
            this.setCursor(Cursor.getDefaultCursor());
    }//GEN-LAST:event_BtnRujukanVclaimActionPerformed

    private void BtnRujukanVclaimKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_BtnRujukanVclaimKeyPressed
        // TODO add your handling code here:
    }//GEN-LAST:event_BtnRujukanVclaimKeyPressed

    private void TanggalRujukMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_TanggalRujukMouseClicked

    }//GEN-LAST:event_TanggalRujukMouseClicked

    private void AsalRujukanMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_AsalRujukanMouseClicked
        AsalRujukan.setEditable(false);
    }//GEN-LAST:event_AsalRujukanMouseClicked

    private void LakaLantasMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_LakaLantasMouseClicked
        LakaLantas.setEditable(false);
    }//GEN-LAST:event_LakaLantasMouseClicked

    private void JenisPelayananMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_JenisPelayananMouseClicked
        JenisPelayanan.setEditable(false);
    }//GEN-LAST:event_JenisPelayananMouseClicked

    private void EksekutifMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_EksekutifMouseClicked
        Eksekutif.setEditable(false);
    }//GEN-LAST:event_EksekutifMouseClicked

    private void KelasMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_KelasMouseClicked
        Kelas.setEditable(false);
    }//GEN-LAST:event_KelasMouseClicked

    private void COBMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_COBMouseClicked
        COB.setEditable(false);
    }//GEN-LAST:event_COBMouseClicked

    private void DTPCari1MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_DTPCari1MouseClicked
        DTPCari1.setEditable(false);
    }//GEN-LAST:event_DTPCari1MouseClicked

    private void DTPCari2MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_DTPCari2MouseClicked
        DTPCari2.setEditable(false);
    }//GEN-LAST:event_DTPCari2MouseClicked

    private void ppAmbilSepBtnPrintActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_ppAmbilSepBtnPrintActionPerformed
        if (TNoRw.getText().trim().equals("") || TPasien.getText().trim().equals("")) {
            Valid.textKosong(TNoRw, "Pasien");
        } else if (NoKartu.getText().trim().equals("")) {
            Valid.textKosong(NoKartu, "Nomor Kartu");
        } else {
            WindowCariSEP.setLocationRelativeTo(internalFrame1);
            WindowCariSEP.setVisible(true);
            BtnSimpan6.setEnabled(false);
        }
    }//GEN-LAST:event_ppAmbilSepBtnPrintActionPerformed

    private void BtnCloseIn6ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_BtnCloseIn6ActionPerformed
        WindowCariSEP.dispose();
    }//GEN-LAST:event_BtnCloseIn6ActionPerformed

    private void BtnSimpan6ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_BtnSimpan6ActionPerformed
        if(!NoSEP.getText().equals("")){
//            if (TNoRw.getText().trim().equals("") || TPasien.getText().trim().equals("")) {
//                Valid.textKosong(TNoRw, "Pasien");
//            } else if (NoKartu.getText().trim().equals("")) {
//                Valid.textKosong(NoKartu, "Nomor Kartu");
//            } else if (NoRujukan.getText().trim().equals("")) {
//                Valid.textKosong(NoRujukan, "Nomor Rujukan");
//            } else if (KdPpkRujukan.getText().trim().equals("") || NmPpkRujukan.getText().trim().equals("")) {
//                Valid.textKosong(KdPpkRujukan, "PPK Rujukan");
//            } else if (KdPPK.getText().trim().equals("") || NmPPK.getText().trim().equals("")) {
//                Valid.textKosong(KdPPK, "PPK Pelayanan");
//            } else if (KdPenyakit.getText().trim().equals("") || NmPenyakit.getText().trim().equals("")) {
//                Valid.textKosong(KdPenyakit, "Diagnosa");
//            } else if (Catatan.getText().trim().equals("")) {
//                Valid.textKosong(Catatan, "Catatan");
//            } else if ((JenisPelayanan.getSelectedIndex() == 1) && (KdPoli.getText().trim().equals("") || NmPoli.getText().trim().equals(""))) {
//                Valid.textKosong(KdPoli, "Poli Tujuan");
//            } else if ((LakaLantas.getSelectedIndex() == 1) && Ket.getText().equals("")) {
//                Valid.textKosong(Ket, "Keterangan");
//            } else if ((LakaLantas.getSelectedIndex() == 1) && NmProv.getText().equals("")) {
//                Valid.textKosong(KdProv, "Provinsi");
//            } else if ((LakaLantas.getSelectedIndex() == 1) && NmKab.getText().equals("")) {
//                Valid.textKosong(KdKab, "Kabupaten");
//            } else if ((LakaLantas.getSelectedIndex() == 1) && NmKec.getText().equals("")) {
//                Valid.textKosong(KdKec, "Kecamatan");
//            } else {
                jasaraharja="";BPJS="";Taspen="";Asabri="";
                penjamin="";
                if(ChkJasaRaharja.isSelected()==true){
                    jasaraharja="1,";
                }
                if(ChkBPJSTenaga.isSelected()==true){
                    BPJS="2,";
                }
                if(ChkTaspen.isSelected()==true){
                    Taspen="3,";
                }
                if(ChkAsa.isSelected()==true){
                    Asabri="4,";
                }

                if((ChkJasaRaharja.isSelected()==true)||(ChkBPJSTenaga.isSelected()==true)||(ChkTaspen.isSelected()==true)||(ChkAsa.isSelected()==true)){
                    penjamin=jasaraharja+BPJS+Taspen+Asabri+penjamin;
                }else{
                    penjamin="";
                }

                if(penjamin.endsWith(",")){
                    penjamin = penjamin.substring(0,penjamin.length() - 1);
                }
                URL = prop.getProperty("URLAPIBPJS")+"/SEP/1.1/insert";	

                tglkkl="0000-00-00";
                if(LakaLantas.getSelectedIndex()==1){
                    tglkkl=Valid.SetTgl(TanggalKejadian.getSelectedItem()+"");
                }
                
                Sequel.menyimpantf("bridging_sep","?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?","SEP",46,new String[]{
                     NoSEP.getText(),TNoRw.getText(),Valid.SetTgl(TanggalSEP.getSelectedItem()+""),Valid.SetTgl(TanggalRujuk.getSelectedItem()+""),
                     NoRujukan.getText(),KdPpkRujukan.getText(), NmPpkRujukan.getText(),KdPPK.getText(), NmPPK.getText(),
                     JenisPelayanan.getSelectedItem().toString().substring(0,1), Catatan.getText(),KdPenyakit.getText(),
                     NmPenyakit.getText(),KdPoli.getText(),NmPoli.getText(), Kelas.getSelectedItem().toString().substring(0,1),
                     LakaLantas.getSelectedItem().toString().substring(0,1),LokasiLaka.getText(),user,
                     TNoRM.getText(),TPasien.getText(),TglLahir.getText(),JenisPeserta.getText(),JK.getText(),NoKartu.getText(),
                     "0000-00-00 00:00:00",AsalRujukan.getSelectedItem().toString(),Eksekutif.getSelectedItem().toString(),
                     COB.getSelectedItem().toString(),penjamin,NoTelp.getText(),KasusKatarak.getSelectedItem().toString(),
                     tglkkl,Ket.getText(),suplesi.getSelectedItem().toString(),
                     NoSEPSuplesi.getText(),KdProv.getText(),NmProv.getText(),KdKab.getText(),NmKab.getText(),
                     KdKec.getText(),NmKec.getText(),noSKDP.getText(),Kddpjp.getText(),NmDPJP.getText(),""
                });                
                
                WindowCariSEP.dispose();
                JOptionPane.showMessageDialog(null, "Data VClaim SEP BPJS pasien tersebut berhasil tersimpan...!!!!");
                emptTeks();
                tampil();
//            }
        }
    }//GEN-LAST:event_BtnSimpan6ActionPerformed

    private void NoSEPKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_NoSEPKeyPressed
        if (evt.getKeyCode() == KeyEvent.VK_ENTER) {
            BtnCari1ActionPerformed(null);
        } else if (evt.getKeyCode() == KeyEvent.VK_PAGE_DOWN) {
            BtnCari1.requestFocus();
        } else if (evt.getKeyCode() == KeyEvent.VK_PAGE_UP) {
            BtnCari1.requestFocus();
        }
    }//GEN-LAST:event_NoSEPKeyPressed

    private void BtnCari1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_BtnCari1ActionPerformed
        cekSEPVclaim();
    }//GEN-LAST:event_BtnCari1ActionPerformed

    private void TanggalKejadianKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_TanggalKejadianKeyPressed
        // TODO add your handling code here:
    }//GEN-LAST:event_TanggalKejadianKeyPressed

    private void TanggalKejadianMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_TanggalKejadianMouseClicked
        TanggalKejadian.setEditable(false);
    }//GEN-LAST:event_TanggalKejadianMouseClicked

    private void KetKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_KetKeyPressed
        // TODO add your handling code here:
    }//GEN-LAST:event_KetKeyPressed

    private void btnProvActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnProvActionPerformed
        pilihan = 1;
        provinsi.setSize(internalFrame1.getWidth() - 40, internalFrame1.getHeight() - 40);
        provinsi.setLocationRelativeTo(internalFrame1);
        provinsi.setVisible(true);
        provinsi.fokus();
    }//GEN-LAST:event_btnProvActionPerformed

    private void btnProvKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_btnProvKeyPressed
        // TODO add your handling code here:
    }//GEN-LAST:event_btnProvKeyPressed

    private void btnKabActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnKabActionPerformed
        pilihan = 1;
        kabupaten.setSize(internalFrame1.getWidth() - 40, internalFrame1.getHeight() - 40);
        kabupaten.setLocationRelativeTo(internalFrame1);
        kabupaten.setVisible(true);
        kabupaten.fokus(KdProv.getText(), NmProv.getText());
    }//GEN-LAST:event_btnKabActionPerformed

    private void btnKabKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_btnKabKeyPressed
        // TODO add your handling code here:
    }//GEN-LAST:event_btnKabKeyPressed

    private void btnKecActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnKecActionPerformed
        pilihan = 1;
        kecamatan.setSize(internalFrame1.getWidth() - 40, internalFrame1.getHeight() - 40);
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

    private void KasusKatarakMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_KasusKatarakMouseClicked
        KasusKatarak.setEditable(false);
    }//GEN-LAST:event_KasusKatarakMouseClicked

    private void KasusKatarakKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_KasusKatarakKeyPressed
        // TODO add your handling code here:
    }//GEN-LAST:event_KasusKatarakKeyPressed

    private void noSKDPKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_noSKDPKeyPressed
        // TODO add your handling code here:
    }//GEN-LAST:event_noSKDPKeyPressed

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

    private void btnNoSKDPActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnNoSKDPActionPerformed
        skdp.setNoRm(TNoRM.getText(),TPasien.getText());
        skdp.setSize(internalFrame1.getWidth()-40,internalFrame1.getHeight()-40);
        skdp.setLocationRelativeTo(internalFrame1);        
        skdp.setVisible(true);        
        skdp.fokusdata();
    }//GEN-LAST:event_btnNoSKDPActionPerformed

    private void btnNoSKDPKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_btnNoSKDPKeyPressed
        // TODO add your handling code here:
    }//GEN-LAST:event_btnNoSKDPKeyPressed

    private void LakaLantasItemStateChanged(java.awt.event.ItemEvent evt) {//GEN-FIRST:event_LakaLantasItemStateChanged
        cekLAKA();
    }//GEN-LAST:event_LakaLantasItemStateChanged

    private void TipeRujukanMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_TipeRujukanMouseClicked
        TipeRujukan.setEditable(false);
    }//GEN-LAST:event_TipeRujukanMouseClicked

    private void sepRujukKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_sepRujukKeyPressed
        // TODO add your handling code here:
    }//GEN-LAST:event_sepRujukKeyPressed

    private void BtnResetRujukActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_BtnResetRujukActionPerformed
        KdPpkRujukan1.setText("");
        NmPpkRujukan1.setText("");
        KdPenyakit1.setText("");
        NmPenyakit1.setText("");
        KdPoli1.setText("");
        NmPoli1.setText("");
        Catatan1.setText("");
        nmfaskes_keluar.setText("");
        kode_rujukanya.setText("");
        TanggalRujukKeluar.requestFocus();
    }//GEN-LAST:event_BtnResetRujukActionPerformed

    private void btnNoRujukanActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnNoRujukanActionPerformed
        if (TNoRw.getText().trim().equals("")||TPasien.getText().trim().equals("")) {
            Valid.textKosong(TNoRw, "Pasien");
        }else if (NoKartu.getText().trim().equals("")) {
            Valid.textKosong(NoKartu, "Nomor Kartu");
        }else{  
            WindowCariNoRujuk.setLocationRelativeTo(internalFrame1);
            WindowCariNoRujuk.setVisible(true);
            tampilFaskes1();
        }
    }//GEN-LAST:event_btnNoRujukanActionPerformed

    private void btnNoRujukanKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_btnNoRujukanKeyPressed
        // TODO add your handling code here:
    }//GEN-LAST:event_btnNoRujukanKeyPressed

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

    private void BtnKeluar1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_BtnKeluar1ActionPerformed
        WindowCariNoRujuk.dispose();
    }//GEN-LAST:event_BtnKeluar1ActionPerformed

    private void BtnKeluar1KeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_BtnKeluar1KeyPressed
        if(evt.getKeyCode()==KeyEvent.VK_SPACE){
            dispose();
        }else{Valid.pindah(evt,BtnPrint,BtnKeluar);}
    }//GEN-LAST:event_BtnKeluar1KeyPressed

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

    private void CatatanKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_CatatanKeyPressed
        Valid.pindah(evt,COB,LokasiLaka);
    }//GEN-LAST:event_CatatanKeyPressed

    private void NoSEPKeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_NoSEPKeyTyped
        evt.setKeyChar(Character.toUpperCase(evt.getKeyChar()));
    }//GEN-LAST:event_NoSEPKeyTyped

    private void ChkInputMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_ChkInputMouseClicked
        isForm();
    }//GEN-LAST:event_ChkInputMouseClicked

    private void kode_rujukanyaKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_kode_rujukanyaKeyPressed
        // TODO add your handling code here:
    }//GEN-LAST:event_kode_rujukanyaKeyPressed

    private void nmfaskes_keluarKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_nmfaskes_keluarKeyPressed
        // TODO add your handling code here:
    }//GEN-LAST:event_nmfaskes_keluarKeyPressed

    private void ppPulangAutoBtnPrintActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_ppPulangAutoBtnPrintActionPerformed
        tglpulangnya = 0;     
        jnsrawat = "";
        jnsrawat = Sequel.cariIsi("select jnspelayanan from bridging_sep where no_rawat='" + TNoRw.getText() + "'");
        
        if (tabMode.getRowCount() == 0) {
            JOptionPane.showMessageDialog(null, "Maaf, data pasien sudah habis...!!!!");
            DTPCari1.requestFocus();
        } else if (TNoRw.getText().trim().equals("") || TPasien.getText().trim().equals("")) {
            JOptionPane.showMessageDialog(null, "Silahkan pilih dulu salah satu data pasiennya pada tabel...!!!!");
            tbObat.requestFocus();
        } else if (!jnsrawat.equals("1")) {
            JOptionPane.showMessageDialog(null, "Fitur ini hanya untuk pasien rawat inap saja...!!!!");
            emptTeks();
            tampil();
        } else {            
            tglpulangnya = Sequel.cariInteger("select COUNT(1) cek from bridging_sep where jnspelayanan='1' and tglpulang='0000-00-00 00:00:00' and no_rawat='" + TNoRw.getText() + "'");
            
            if (tglpulangnya >=1) {
                JOptionPane.showMessageDialog(null, "Tgl. Pulang masih belum terisi dari kamar inap, silahkan update tgl. pulang secara manual...!!!!");
            } else {
                try {
                    URL = prop.getProperty("URLAPIBPJS") + "/Sep/updtglplg";

                    HttpHeaders headers = new HttpHeaders();
                    headers.setContentType(MediaType.APPLICATION_FORM_URLENCODED);
                    headers.add("X-Cons-ID", prop.getProperty("CONSIDAPIBPJS"));
                    headers.add("X-Timestamp", String.valueOf(api.GetUTCdatetimeAsString()));
                    headers.add("X-Signature", api.getHmac());
                    requestJson = "{"
                            + "\"request\":"
                            + "{"
                            + "\"t_sep\":"
                            + "{"
                            + "\"noSep\":\"" + tbObat.getValueAt(tbObat.getSelectedRow(), 0).toString() + "\","
                            + "\"tglPulang\":\"" + Sequel.cariIsi("select tglpulang from bridging_sep where no_sep='" + tbObat.getValueAt(tbObat.getSelectedRow(), 0).toString() + "'") + "\","
                            + "\"user\":\"" + user + "\""
                            + "}"
                            + "}"
                            + "}";
                    HttpEntity requestEntity = new HttpEntity(requestJson, headers);
                    ObjectMapper mapper = new ObjectMapper();
                    JsonNode root = mapper.readTree(api.getRest().exchange(URL, HttpMethod.PUT, requestEntity, String.class).getBody());
                    JsonNode nameNode = root.path("metaData");
                    System.out.println("code : " + nameNode.path("code").asText());
                    System.out.println("message : " + nameNode.path("message").asText());
                    JsonNode response = root.path("response");
                    if (nameNode.path("code").asText().equals("200")) {
                        emptTeks();
                        tampil();
                        JOptionPane.showMessageDialog(null, "Proses update tgl. pulang secara otomatis selesai..!!");
                    } else {
                        JOptionPane.showMessageDialog(null, nameNode.path("message").asText());
                    }
                } catch (Exception ex) {
                    System.out.println("Notifikasi Bridging : " + ex);
                    if (ex.toString().contains("UnknownHostException")) {
                        JOptionPane.showMessageDialog(null, "Koneksi ke server BPJS terputus...!");
                    }
                }
            }
        }
    }//GEN-LAST:event_ppPulangAutoBtnPrintActionPerformed

    private void MnRekapSEPRanapBtnPrintActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_MnRekapSEPRanapBtnPrintActionPerformed
        if (tbObat.getRowCount() == 0) {
            JOptionPane.showMessageDialog(null, "Maaf, data sudah habis. Tidak ada data yang bisa diexport menjadi file excel...!!!!");
            BtnBatal.requestFocus();
        } else if (tbObat.getRowCount() != 0) {
            ExportSEPRanap();
        }
    }//GEN-LAST:event_MnRekapSEPRanapBtnPrintActionPerformed

    private void MnRekapSEPRalanBtnPrintActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_MnRekapSEPRalanBtnPrintActionPerformed
        if (tbObat.getRowCount() == 0) {
            JOptionPane.showMessageDialog(null, "Maaf, data sudah habis. Tidak ada data yang bisa diexport menjadi file excel...!!!!");
            BtnBatal.requestFocus();
        } else if (tbObat.getRowCount() != 0) {
            ExportSEPRalan();
        }
    }//GEN-LAST:event_MnRekapSEPRalanBtnPrintActionPerformed

    private void ppSuratKontrolBtnPrintActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_ppSuratKontrolBtnPrintActionPerformed
        if (TNoRw.getText().trim().equals("")) {
            JOptionPane.showMessageDialog(null, "Maaf, Silahkan anda pilih dulu pasien...!!!");
            TCari.requestFocus();
        } else {
            if (tbObat.getSelectedRow() != -1) {
                this.setCursor(Cursor.getPredefinedCursor(Cursor.WAIT_CURSOR));
                BPJSSuratKontrol form = new BPJSSuratKontrol(null, false);
                form.setNoRm(tbObat.getValueAt(tbObat.getSelectedRow(), 1).toString(), 
                        tbObat.getValueAt(tbObat.getSelectedRow(), 0).toString(), 
                        tbObat.getValueAt(tbObat.getSelectedRow(), 24).toString(), 
                        tbObat.getValueAt(tbObat.getSelectedRow(), 2).toString(), 
                        tbObat.getValueAt(tbObat.getSelectedRow(), 3).toString(), 
                        tbObat.getValueAt(tbObat.getSelectedRow(), 21).toString(), 
                        tbObat.getValueAt(tbObat.getSelectedRow(), 23).toString(), 
                        tbObat.getValueAt(tbObat.getSelectedRow(), 14).toString());
                form.setSize(internalFrame1.getWidth() - 20, internalFrame1.getHeight() - 20);
                form.setLocationRelativeTo(internalFrame1);
                form.setVisible(true);
                this.setCursor(Cursor.getDefaultCursor());
            }
        }
    }//GEN-LAST:event_ppSuratKontrolBtnPrintActionPerformed

    private void ppUpdateSEPdariVclaimBtnPrintActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_ppUpdateSEPdariVclaimBtnPrintActionPerformed
        cttnVclaim = "";
        diagVclaim = "";
        jpelVclaim = "";
        KRVclaim = "";
        noRujukVclaim = "";
        JPVclaim = "";
        kelaminVclaim = "";
        namaVclaim = "";
        nokaVclaim = "";
        nomrVclaim = "";
        tglLhrVclaim = "";
        poliVclaim = "";
        poliEksVclaim = "";
        tglSEPVclaim = "";
        nilaiJP = "";
        nilaiKR = "";
        nilaiEKS = "";
 
        try {
            URL = prop.getProperty("URLAPIBPJS") + "/SEP/" + tbObat.getValueAt(tbObat.getSelectedRow(), 0).toString();
            HttpHeaders headers = new HttpHeaders();
            headers.setContentType(MediaType.APPLICATION_FORM_URLENCODED);
            headers.add("X-Cons-ID", prop.getProperty("CONSIDAPIBPJS"));
            headers.add("X-Timestamp", String.valueOf(api.GetUTCdatetimeAsString()));
            headers.add("X-Signature", api.getHmac());
            HttpEntity requestEntity = new HttpEntity(headers);

            //System.out.println(rest.exchange(URL, HttpMethod.GET, requestEntity, String.class).getBody());
            ObjectMapper mapper = new ObjectMapper();
            JsonNode root = mapper.readTree(api.getRest().exchange(URL, HttpMethod.GET, requestEntity, String.class).getBody());
            JsonNode nameNode = root.path("metaData");
            System.out.println("code : " + nameNode.path("code").asText());
            System.out.println("message : " + nameNode.path("message").asText());
            if (nameNode.path("message").asText().equals("Sukses")) {                
                JsonNode response = root.path("response");
                cttnVclaim = response.path("catatan").asText();
                diagVclaim = response.path("diagnosa").asText();
                jpelVclaim = response.path("jnsPelayanan").asText();
                KRVclaim = response.path("kelasRawat").asText();
                noRujukVclaim = response.path("noRujukan").asText();
                JPVclaim = response.path("peserta").path("jnsPeserta").asText();
                kelaminVclaim = response.path("peserta").path("kelamin").asText();
                namaVclaim = response.path("peserta").path("nama").asText();
                nokaVclaim = response.path("peserta").path("noKartu").asText();
                nomrVclaim = response.path("peserta").path("noMr").asText();
                tglLhrVclaim = response.path("peserta").path("tglLahir").asText();
                poliVclaim = response.path("poli").asText();
                poliEksVclaim = response.path("poliEksekutif").asText();
                tglSEPVclaim = response.path("tglSep").asText();
                
                if (jpelVclaim.equals("Rawat Jalan")) {
                    nilaiJP = "2";
                } else {
                    nilaiJP = "1";
                }
                
                if (KRVclaim.equals("-")) {
                    nilaiKR = "3";
                } else {
                    nilaiKR = KRVclaim;
                }
                
                if (poliEksVclaim.equals("0")) {
                    nilaiEKS = "0. Tidak";
                } else {
                    nilaiEKS = "1. Ya";
                }

                Sequel.mengedit("bridging_sep", "no_sep='" + tbObat.getValueAt(tbObat.getSelectedRow(), 0).toString() + "'",
                        "catatan='" + cttnVclaim + "', nmdiagnosaawal='" + diagVclaim + "', jnspelayanan='" + nilaiJP + "', klsrawat='" + nilaiKR + "', "
                        + "no_rujukan='" + noRujukVclaim + "', peserta='" + JPVclaim + "', jkel='" + kelaminVclaim + "', nama_pasien='" + namaVclaim + "', "
                        + "no_kartu='" + nokaVclaim + "', nomr='" + nomrVclaim + "', tanggal_lahir='" + tglLhrVclaim + "', nmpolitujuan='" + poliVclaim + "', "
                        + "eksekutif='" + nilaiEKS + "', tglsep='" + tglSEPVclaim + "', user='" + var.getkode() + "'");
                
                Sequel.mengedit("rujuk_masuk", "no_rawat=?", "no_rawat=?,perujuk=?,no_rujuk=?,kd_rujukan=?", 5, new String[]{
                    TNoRw.getText(), NmPpkRujukan.getText(), noRujukVclaim,
                    tbObat.getValueAt(tbObat.getSelectedRow(), 1).toString(), kode_rujukanya.getText()
                });
                emptTeks();
                tampil();
            } else {
                JOptionPane.showMessageDialog(null, nameNode.path("message").asText());
            }
        } catch (Exception ex) {
            System.out.println("Notifikasi Peserta : " + ex);
            if (ex.toString().contains("UnknownHostException")) {
                JOptionPane.showMessageDialog(rootPane, "Koneksi ke server BPJS terputus...!");
                dispose();
            }
        }        
    }//GEN-LAST:event_ppUpdateSEPdariVclaimBtnPrintActionPerformed

    private void MnBackdateActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_MnBackdateActionPerformed
        PengajuanBackdate();
    }//GEN-LAST:event_MnBackdateActionPerformed

    private void MnFingerPrinActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_MnFingerPrinActionPerformed
        PengajuanFingerPrint();
    }//GEN-LAST:event_MnFingerPrinActionPerformed

    private void MnBackdate1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_MnBackdate1ActionPerformed
        this.setCursor(Cursor.getPredefinedCursor(Cursor.WAIT_CURSOR));
        if (!NoKartu.getText().equals("")) {
            try {
                URL = prop.getProperty("URLAPIBPJS") + "/Sep/aprovalSEP";

                HttpHeaders headers = new HttpHeaders();
                headers.setContentType(MediaType.APPLICATION_FORM_URLENCODED);
                headers.add("X-Cons-ID", prop.getProperty("CONSIDAPIBPJS"));
                headers.add("X-Timestamp", String.valueOf(api.GetUTCdatetimeAsString()));
                headers.add("X-Signature", api.getHmac());
                
                requestJson = " {"
                        + "\"request\": {"
                        + "\"t_sep\": {"
                        + "\"noKartu\": \"" + NoKartu.getText() + "\","
                        + "\"tglSep\": \"" + Valid.SetTgl(TanggalSEP.getSelectedItem() + "") + "\","
                        + "\"jnsPelayanan\": \"" + JenisPelayanan.getSelectedItem().toString().substring(0, 1) + "\","
                        + "\"jnsPengajuan\": \"1\","
                        + "\"keterangan\": \"" + Catatan.getText() + "\","
                        + "\"user\": \"" + user + "\""
                        + "}"
                        + "}"
                        + "}";
                
                HttpEntity requestEntity = new HttpEntity(requestJson, headers);
                ObjectMapper mapper = new ObjectMapper();
                JsonNode root = mapper.readTree(api.getRest().exchange(URL, HttpMethod.POST, requestEntity, String.class).getBody());
                JsonNode nameNode = root.path("metaData");
                System.out.println("code : " + nameNode.path("code").asText());
                System.out.println("message : " + nameNode.path("message").asText());
                JsonNode response = root.path("response");
                if (nameNode.path("code").asText().equals("200")) {
                    JOptionPane.showMessageDialog(null, "Proses mapping aproval backdate selesai, data nomor rawat berhasil dikirim ke SEP..!!");
                } else {
                    JOptionPane.showMessageDialog(null, nameNode.path("message").asText());
                }
            } catch (Exception ex) {
                System.out.println("Notifikasi Bridging : " + ex);
                if (ex.toString().contains("UnknownHostException")) {
                    JOptionPane.showMessageDialog(null, "Koneksi ke server BPJS terputus...!");
                }
            }
        } else {
            JOptionPane.showMessageDialog(null, "Maaf, silahkan pilih data peserta yang mau dimapping transaksinya...!!!!");
            BtnBatal.requestFocus();
        }
        this.setCursor(Cursor.getDefaultCursor());
    }//GEN-LAST:event_MnBackdate1ActionPerformed

    private void MnFingerPrin1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_MnFingerPrin1ActionPerformed
        this.setCursor(Cursor.getPredefinedCursor(Cursor.WAIT_CURSOR));
        if (!NoKartu.getText().equals("")) {
            try {
                URL = prop.getProperty("URLAPIBPJS") + "/Sep/aprovalSEP";

                HttpHeaders headers = new HttpHeaders();
                headers.setContentType(MediaType.APPLICATION_FORM_URLENCODED);
                headers.add("X-Cons-ID", prop.getProperty("CONSIDAPIBPJS"));
                headers.add("X-Timestamp", String.valueOf(api.GetUTCdatetimeAsString()));
                headers.add("X-Signature", api.getHmac());

                requestJson = " {"
                        + "\"request\": {"
                        + "\"t_sep\": {"
                        + "\"noKartu\": \"" + NoKartu.getText() + "\","
                        + "\"tglSep\": \"" + Valid.SetTgl(TanggalSEP.getSelectedItem() + "") + "\","
                        + "\"jnsPelayanan\": \"" + JenisPelayanan.getSelectedItem().toString().substring(0, 1) + "\","
                        + "\"jnsPengajuan\": \"2\","
                        + "\"keterangan\": \"" + Catatan.getText() + "\","
                        + "\"user\": \"" + user + "\""
                        + "}"
                        + "}"
                        + "}";

                HttpEntity requestEntity = new HttpEntity(requestJson, headers);
                ObjectMapper mapper = new ObjectMapper();
                JsonNode root = mapper.readTree(api.getRest().exchange(URL, HttpMethod.POST, requestEntity, String.class).getBody());
                JsonNode nameNode = root.path("metaData");
                System.out.println("code : " + nameNode.path("code").asText());
                System.out.println("message : " + nameNode.path("message").asText());
                JsonNode response = root.path("response");
                if (nameNode.path("code").asText().equals("200")) {
                    JOptionPane.showMessageDialog(null, "Proses mapping aproval finger print selesai, data nomor rawat berhasil dikirim ke SEP..!!");
                } else {
                    JOptionPane.showMessageDialog(null, nameNode.path("message").asText());
                }
            } catch (Exception ex) {
                System.out.println("Notifikasi Bridging : " + ex);
                if (ex.toString().contains("UnknownHostException")) {
                    JOptionPane.showMessageDialog(null, "Koneksi ke server BPJS terputus...!");
                }
            }
        } else {
            JOptionPane.showMessageDialog(null, "Maaf, silahkan pilih data peserta yang mau dimapping transaksinya...!!!!");
            BtnBatal.requestFocus();
        }
        this.setCursor(Cursor.getDefaultCursor());
    }//GEN-LAST:event_MnFingerPrin1ActionPerformed

    /**
    * @param args the command line arguments
    */
    public static void main(String args[]) {
        java.awt.EventQueue.invokeLater(() -> {
            BPJSDataSEP dialog = new BPJSDataSEP(new javax.swing.JFrame(), true);
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
    private widget.Button BtnBatal;
    private widget.Button BtnCari;
    private widget.Button BtnCari1;
    private widget.Button BtnCloseIn4;
    private widget.Button BtnCloseIn5;
    private widget.Button BtnCloseIn6;
    private widget.Button BtnEdit;
    private widget.Button BtnHapus;
    private widget.Button BtnKeluar;
    private widget.Button BtnKeluar1;
    private widget.Button BtnPrint;
    private widget.Button BtnResetRujuk;
    private widget.Button BtnRujukanVclaim;
    private widget.Button BtnSimpan;
    private widget.Button BtnSimpan4;
    private widget.Button BtnSimpan5;
    private widget.Button BtnSimpan6;
    private widget.ComboBox COB;
    public widget.TextArea Catatan;
    private widget.TextBox Catatan1;
    private widget.CekBox ChkAsa;
    private widget.CekBox ChkBPJSTenaga;
    public widget.CekBox ChkInput;
    private widget.CekBox ChkJasaRaharja;
    private widget.CekBox ChkTaspen;
    private widget.Tanggal DTPCari1;
    private widget.Tanggal DTPCari2;
    private widget.ComboBox Eksekutif;
    private widget.PanelBiasa FormInput;
    private widget.TextBox JK;
    private widget.ComboBox JenisPelayanan;
    private widget.ComboBox JenisPelayanan1;
    private widget.TextBox JenisPeserta;
    private widget.ComboBox KasusKatarak;
    private widget.TextBox KdKab;
    private widget.TextBox KdKec;
    private widget.TextBox KdPPK;
    private widget.TextBox KdPenyakit;
    private widget.TextBox KdPenyakit1;
    private widget.TextBox KdPoli;
    private widget.TextBox KdPoli1;
    private widget.TextBox KdPpkRujukan;
    private widget.TextBox KdPpkRujukan1;
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
    private widget.Label LabelPoli2;
    private widget.Label LabjaminKll;
    private widget.ComboBox LakaLantas;
    private widget.TextBox LokasiLaka;
    private javax.swing.JMenuItem MnBackdate;
    private javax.swing.JMenuItem MnBackdate1;
    private javax.swing.JMenuItem MnFingerPrin;
    private javax.swing.JMenuItem MnFingerPrin1;
    private javax.swing.JMenuItem MnRekapSEPRalan;
    private javax.swing.JMenuItem MnRekapSEPRanap;
    private widget.TextBox NmDPJP;
    private widget.TextBox NmKab;
    private widget.TextBox NmKec;
    private widget.TextBox NmPPK;
    private widget.TextBox NmPenyakit;
    private widget.TextBox NmPenyakit1;
    private widget.TextBox NmPoli;
    private widget.TextBox NmPoli1;
    private widget.TextBox NmPpkRujukan;
    private widget.TextBox NmPpkRujukan1;
    private widget.TextBox NmProv;
    private widget.TextBox NoBalasan;
    private widget.TextBox NoKartu;
    private widget.TextBox NoRujukan;
    private widget.TextBox NoSEP;
    private widget.TextBox NoSEPSuplesi;
    private widget.TextBox NoTelp;
    private javax.swing.JPanel PanelInput;
    private javax.swing.JPopupMenu Popup;
    private javax.swing.JPopupMenu Popup1;
    private widget.ScrollPane Scroll;
    private widget.ScrollPane Scroll3;
    private widget.TextBox Status;
    private widget.TextBox TCari;
    private widget.TextBox TNoRM;
    private widget.TextBox TNoRw;
    private widget.TextBox TPasien;
    private javax.swing.JTabbedPane TabRujukan;
    private widget.Tanggal TanggalKejadian;
    private widget.Tanggal TanggalPulang;
    private widget.Tanggal TanggalRujuk;
    private widget.Tanggal TanggalRujukKeluar;
    private widget.Tanggal TanggalSEP;
    private widget.TextBox TglLahir;
    private widget.ComboBox TipeRujukan;
    private javax.swing.JDialog WindowCariNoRujuk;
    private javax.swing.JDialog WindowCariSEP;
    private javax.swing.JDialog WindowRujukan;
    private javax.swing.JDialog WindowUpdatePulang;
    private widget.Button btnDPJP;
    private widget.Button btnDiagnosa;
    private widget.Button btnDiagnosa1;
    private widget.Button btnKab;
    private widget.Button btnKec;
    private widget.Button btnNoRujukan;
    private widget.Button btnNoSKDP;
    private widget.Button btnPPKRujukan;
    private widget.Button btnPPKRujukan1;
    private widget.Button btnPoli;
    private widget.Button btnPoli1;
    private widget.Button btnProv;
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
    private widget.Label jLabel18;
    private widget.Label jLabel19;
    private widget.Label jLabel20;
    private widget.Label jLabel21;
    private widget.Label jLabel22;
    private widget.Label jLabel23;
    private widget.Label jLabel24;
    private widget.Label jLabel25;
    private widget.Label jLabel26;
    private widget.Label jLabel28;
    private widget.Label jLabel29;
    private widget.Label jLabel30;
    private widget.Label jLabel31;
    private widget.Label jLabel32;
    private widget.Label jLabel33;
    private widget.Label jLabel34;
    private widget.Label jLabel35;
    private widget.Label jLabel36;
    private widget.Label jLabel4;
    private widget.Label jLabel42;
    private widget.Label jLabel43;
    private widget.Label jLabel5;
    private widget.Label jLabel6;
    private widget.Label jLabel7;
    private widget.Label jLabel8;
    private widget.Label jLabel9;
    private javax.swing.JPanel jPanel3;
    private javax.swing.JPopupMenu.Separator jSeparator1;
    private widget.TextBox kode_rujukanya;
    private widget.TextBox nmfaskes_keluar;
    private widget.TextBox noSKDP;
    private widget.Label nosep;
    private widget.panelisi panelGlass8;
    private widget.panelisi panelGlass9;
    private widget.panelisi panelisi1;
    private javax.swing.JMenuItem ppAmbilSep;
    private javax.swing.JMenu ppAprovalSEP;
    private javax.swing.JMenuItem ppDetailSEPPeserta;
    private javax.swing.JMenu ppPengajuan;
    private javax.swing.JMenuItem ppPulang;
    private javax.swing.JMenuItem ppPulangAuto;
    private javax.swing.JMenuItem ppRujukan;
    private javax.swing.JMenuItem ppSEP;
    private javax.swing.JMenuItem ppSuratKontrol;
    private javax.swing.JMenuItem ppUpdateSEPdariVclaim;
    private widget.Label rujukanSEP;
    private widget.ScrollPane scrollPane1;
    private widget.ScrollPane scrollPane2;
    private widget.ScrollPane scrollPane3;
    private widget.ScrollPane scrollPane4;
    private widget.TextBox sepRujuk;
    private widget.ComboBox suplesi;
    private widget.Table tbFaskes1;
    private widget.Table tbFaskes2;
    private widget.Table tbFaskes3;
    private widget.Table tbFaskes4;
    public widget.Table tbObat;
    // End of variables declaration//GEN-END:variables

    public void tampil() {
        Valid.tabelKosong(tabMode);
        try{
            ps=koneksi.prepareStatement(
                    " SELECT no_sep, no_rawat, nomr, nama_pasien, tglsep, tglrujukan, no_rujukan, kdppkrujukan, "
                    + " nmppkrujukan, kdppkpelayanan, nmppkpelayanan, IF (jnspelayanan = '1','1. Ranap','2. Ralan') jns_pely, "
                    + " catatan, diagawal, nmdiagnosaawal, kdpolitujuan, nmpolitujuan, "
                    + " IF (klsrawat = '1','1. Kelas 1',IF (klsrawat = '2','2. Kelas 2','3. Kelas 3')) kls_rwt, "
                    + " IF (lakalantas = '0','0. Tidak','1. Ya') kasus_laka, lokasilaka, USER, tanggal_lahir, peserta, jkel, no_kartu, "
                    + " tglpulang, asal_rujukan, eksekutif, cob, penjamin, notelep, katarak, tglkkl, keterangankkl, "
                    + " suplesi, no_sep_suplesi, kdprop, nmprop, kdkab, nmkab, kdkec, nmkec, noskdp, kddpjp, nmdpdjp from bridging_sep where "
                    + " tglsep between ? and ? and no_sep like ? or "
                    + " tglsep between ? and ? and nomr like ? or "
                    + " tglsep between ? and ? and nama_pasien like ? or "
                    + " tglsep between ? and ? and nmppkrujukan like ? or "
                    + " tglsep between ? and ? and diagawal like ? or "
                    + " tglsep between ? and ? and nmdiagnosaawal like ? or "
                    + " tglsep between ? and ? and no_rawat like ? or "
                    + " tglsep between ? and ? and no_kartu like ? or "
                    + " tglsep between ? and ? and nmpolitujuan like ? order by tglsep");
            try {
                ps.setString(1,Valid.SetTgl(DTPCari1.getSelectedItem()+"")+" 00:00:00");
                ps.setString(2,Valid.SetTgl(DTPCari2.getSelectedItem()+"")+" 23:59:59");
                ps.setString(3,"%"+TCari.getText().trim()+"%");
                ps.setString(4,Valid.SetTgl(DTPCari1.getSelectedItem()+"")+" 00:00:00");
                ps.setString(5,Valid.SetTgl(DTPCari2.getSelectedItem()+"")+" 23:59:59");
                ps.setString(6,"%"+TCari.getText().trim()+"%");
                ps.setString(7,Valid.SetTgl(DTPCari1.getSelectedItem()+"")+" 00:00:00");
                ps.setString(8,Valid.SetTgl(DTPCari2.getSelectedItem()+"")+" 23:59:59");
                ps.setString(9,"%"+TCari.getText().trim()+"%");
                ps.setString(10,Valid.SetTgl(DTPCari1.getSelectedItem()+"")+" 00:00:00");
                ps.setString(11,Valid.SetTgl(DTPCari2.getSelectedItem()+"")+" 23:59:59");
                ps.setString(12,"%"+TCari.getText().trim()+"%");
                ps.setString(13,Valid.SetTgl(DTPCari1.getSelectedItem()+"")+" 00:00:00");
                ps.setString(14,Valid.SetTgl(DTPCari2.getSelectedItem()+"")+" 23:59:59");
                ps.setString(15,"%"+TCari.getText().trim()+"%");
                ps.setString(16,Valid.SetTgl(DTPCari1.getSelectedItem()+"")+" 00:00:00");
                ps.setString(17,Valid.SetTgl(DTPCari2.getSelectedItem()+"")+" 23:59:59");
                ps.setString(18,"%"+TCari.getText().trim()+"%");
                ps.setString(19,Valid.SetTgl(DTPCari1.getSelectedItem()+"")+" 00:00:00");
                ps.setString(20,Valid.SetTgl(DTPCari2.getSelectedItem()+"")+" 23:59:59");
                ps.setString(21,"%"+TCari.getText().trim()+"%");
                ps.setString(22,Valid.SetTgl(DTPCari1.getSelectedItem()+"")+" 00:00:00");
                ps.setString(23,Valid.SetTgl(DTPCari2.getSelectedItem()+"")+" 23:59:59");
                ps.setString(24,"%"+TCari.getText().trim()+"%");                
                ps.setString(25,Valid.SetTgl(DTPCari1.getSelectedItem()+"")+" 00:00:00");
                ps.setString(26,Valid.SetTgl(DTPCari2.getSelectedItem()+"")+" 23:59:59");
                ps.setString(27,"%"+TCari.getText().trim()+"%");
                rs=ps.executeQuery();
                while(rs.next()){
                    tabMode.addRow(new Object[]{
                        rs.getString(1),rs.getString(2),rs.getString(3),rs.getString(4),
                        rs.getString(5),rs.getString(6),rs.getString(7),rs.getString(8),
                        rs.getString(9),rs.getString(10),rs.getString(11),rs.getString(12),
                        rs.getString(13),rs.getString(14),rs.getString(14)+" "+rs.getString(15),rs.getString(16),
                        rs.getString(17),rs.getString(18),rs.getString(19),rs.getString(20),
                        rs.getString(21),rs.getString(22),rs.getString(23),rs.getString(24),
                        rs.getString(25),rs.getString(26),rs.getString(27),rs.getString(28),
                        rs.getString(29).replaceAll("1","1.Jasa Raharja").replaceAll("2","2.BPJS").replaceAll("3","3.Taspen").replaceAll("4","4.Asabri"),
                        rs.getString(30),rs.getString(31),rs.getString(32),
                        rs.getString(33),rs.getString(34),rs.getString(35),rs.getString(36),
                        rs.getString(37),rs.getString(38),rs.getString(39),rs.getString(40),
                        rs.getString(41),rs.getString(42),rs.getString(43),rs.getString(44),rs.getString(45)
                    });
                }
            } catch (Exception e) {
                System.out.println("Notifikasi : "+e);
            } finally{
                if(rs!=null){
                    rs.close();
                }
                if(ps!=null){
                    ps.close();
                }
            }
        }catch(Exception e){
            System.out.println("Notifikasi : "+e);
        }
        LCount.setText(""+tabMode.getRowCount());
    }

    private void isRawat() {
        Sequel.cariIsi("select no_rkm_medis from reg_periksa where no_rawat=? ", TNoRM, TNoRw.getText());
        Catatan.setText("-");

        if (NoTelp.getText().equals("null")) {
            NoTelp.setText("0");
        } else {
            NoTelp.setText(Sequel.cariIsi("select no_tlp from pasien where no_rkm_medis='" + TNoRM.getText() + "'"));
        }
    }

    public void emptTeks(){
        TNoRw.setText("");
        TPasien.setText("");
        TanggalSEP.setDate(new Date());
        TanggalRujuk.setDate(new Date());
        TglLahir.setText("");
        NoKartu.setText("");
        JenisPeserta.setText("");
        Status.setText("");
        JK.setText("");
        NoRujukan.setText("");
        KdPpkRujukan.setText("");
        NmPpkRujukan.setText("");
        JenisPelayanan.setSelectedIndex(1);
        Catatan.setText("");
        KdPenyakit.setText("");
        NmPenyakit.setText("");
        KdPoli.setText("");
        NmPoli.setText("");
        Kelas.setSelectedIndex(0);
        LakaLantas.setSelectedIndex(0);
        LokasiLaka.setText("-");
        TNoRM.setText("");
        NoTelp.requestFocus();
        ChkAsa.setSelected(false);
        ChkBPJSTenaga.setSelected(false);
        ChkInput.setSelected(false);
        ChkJasaRaharja.setSelected(false);
        ChkTaspen.setSelected(false);
        NoTelp.setText("0");
        rujukanSEP.setText("- belum terisi -");
        
        noSKDP.setText("");
        Kddpjp.setText("");
        NmDPJP.setText("");
        KasusKatarak.setSelectedIndex(0);
        Ket.setText("");
        TanggalKejadian.setDate(new Date());
        KdProv.setText("");
        NmProv.setText("");
        KdKab.setText("");
        NmKab.setText("");
        KdKec.setText("");
        NmKec.setText("");
        suplesi.setSelectedIndex(0);
        NoSEPSuplesi.setText("");
        kode_rujukanya.setText("");
        nmfaskes_keluar.setText("");
        Valid.autoNomer3("select ifnull(MAX(CONVERT(RIGHT(rujuk_masuk.no_rawat,4),signed)),0) from reg_periksa inner join rujuk_masuk on reg_periksa.no_rawat=rujuk_masuk.no_rawat where reg_periksa.tgl_registrasi='"+Valid.SetTgl(TanggalSEP.getSelectedItem()+"")+"' ","BR/"+dateformat.format(TanggalSEP.getDate())+"/",4,NoBalasan);
    }

    public void setNoRm(String norwt, String tgl1, String status, String kdpoli, String namapoli) {
        TNoRw.setText(norwt);        
        KdPoli.setText(kdpoli);
        NmPoli.setText(namapoli);
        LakaLantas.setSelectedIndex(0);
        Valid.SetTgl(TanggalSEP, Sequel.cariIsi("select tgl_registrasi from reg_periksa where no_rawat='" + norwt + "'"));
        JenisPelayanan.setSelectedItem(status);
        JenisPelayananItemStateChanged(null);
        isRawat();
        TCari.setText(TNoRM.getText());
    }

    public void isCek(){
        BtnSimpan.setEnabled(var.getbpjs_sep());
//        BtnHapus.setEnabled(var.getbpjs_sep());
        BtnPrint.setEnabled(var.getbpjs_sep());
        BtnEdit.setEnabled(var.getbpjs_sep());
        ppDetailSEPPeserta.setEnabled(var.getbpjs_sep());
        ppPengajuan.setEnabled(var.getbpjs_sep());
        ppAprovalSEP.setEnabled(var.getbpjs_sep());
        ppPulang.setEnabled(var.getbpjs_sep());
        ppSEP.setEnabled(var.getbpjs_sep());
        ppUpdateSEPdariVclaim.setEnabled(var.getbpjs_sep());
        ppRujukan.setEnabled(var.getbpjs_sep());
        ppSuratKontrol.setEnabled(var.getbpjs_surat_kontrol());
      
        if (var.getkode().equals("Admin Utama")) {
            BtnHapus.setEnabled(true);
        } else {
            BtnHapus.setEnabled(false);
        }
    }

    private void getData() {
        if(tbObat.getSelectedRow()!= -1){
            sepRujuk.setText(tbObat.getValueAt(tbObat.getSelectedRow(),0).toString());
            TNoRw.setText(tbObat.getValueAt(tbObat.getSelectedRow(),1).toString());
            TNoRM.setText(tbObat.getValueAt(tbObat.getSelectedRow(),2).toString());
            TPasien.setText(tbObat.getValueAt(tbObat.getSelectedRow(),3).toString());
            AsalRujukan.setSelectedItem(tbObat.getValueAt(tbObat.getSelectedRow(),26).toString());
            NoRujukan.setText(tbObat.getValueAt(tbObat.getSelectedRow(),6).toString());
            KdPpkRujukan.setText(tbObat.getValueAt(tbObat.getSelectedRow(),7).toString());
            rujukanSEP.setText(tbObat.getValueAt(tbObat.getSelectedRow(),8).toString());
            Sequel.cariIsi("select nama_rujukan from master_nama_rujukan where kode_faskes_bpjs=? ", NmPpkRujukan, KdPpkRujukan.getText());
            Sequel.cariIsi("select kd_rujukan from rujuk_masuk where no_rawat=? ", kode_rujukanya, TNoRw.getText());
//            Sequel.cariIsi("select perujuk from rujuk_masuk where no_rawat=? ", NmPpkRujukan, TNoRw.getText());
//            NmPpkRujukan.setText(tbObat.getValueAt(tbObat.getSelectedRow(),8).toString());
            KdPPK.setText(tbObat.getValueAt(tbObat.getSelectedRow(),9).toString());
            NmPPK.setText(tbObat.getValueAt(tbObat.getSelectedRow(),10).toString());
            JenisPelayanan.setSelectedItem(tbObat.getValueAt(tbObat.getSelectedRow(),11).toString());
            Catatan.setText(tbObat.getValueAt(tbObat.getSelectedRow(),12).toString());
            KdPenyakit.setText(tbObat.getValueAt(tbObat.getSelectedRow(),13).toString());
            NmPenyakit.setText(tbObat.getValueAt(tbObat.getSelectedRow(),14).toString().replaceAll(KdPenyakit.getText(),""));
            KdPenyakit1.setText(tbObat.getValueAt(tbObat.getSelectedRow(),13).toString());
            NmPenyakit1.setText(tbObat.getValueAt(tbObat.getSelectedRow(),14).toString().replaceAll(KdPenyakit.getText(),""));
            KdPoli.setText(tbObat.getValueAt(tbObat.getSelectedRow(),15).toString());
            NmPoli.setText(tbObat.getValueAt(tbObat.getSelectedRow(),16).toString());            
            Kelas.setSelectedItem(tbObat.getValueAt(tbObat.getSelectedRow(),17).toString());
            LakaLantas.setSelectedItem(tbObat.getValueAt(tbObat.getSelectedRow(),18).toString());
            LokasiLaka.setText(tbObat.getValueAt(tbObat.getSelectedRow(),19).toString());
            TglLahir.setText(tbObat.getValueAt(tbObat.getSelectedRow(),21).toString());
            JenisPeserta.setText(tbObat.getValueAt(tbObat.getSelectedRow(),22).toString());
            JK.setText(tbObat.getValueAt(tbObat.getSelectedRow(),23).toString());
            NoKartu.setText(tbObat.getValueAt(tbObat.getSelectedRow(),24).toString());            
            Eksekutif.setSelectedItem(tbObat.getValueAt(tbObat.getSelectedRow(),27).toString());
            COB.setSelectedItem(tbObat.getValueAt(tbObat.getSelectedRow(),28).toString());
            
            if (tbObat.getValueAt(tbObat.getSelectedRow(), 29).toString().contains("1")) {
                ChkJasaRaharja.setSelected(true);
            } else {
                ChkJasaRaharja.setSelected(false);
            }
            
            if (tbObat.getValueAt(tbObat.getSelectedRow(), 29).toString().contains("2")) {
                ChkBPJSTenaga.setSelected(true);
            } else {
                ChkBPJSTenaga.setSelected(false);
            }
            
            if (tbObat.getValueAt(tbObat.getSelectedRow(), 29).toString().contains("3")) {
                ChkTaspen.setSelected(true);
            } else {
                ChkTaspen.setSelected(false);
            }
            
            if (tbObat.getValueAt(tbObat.getSelectedRow(), 29).toString().contains("3")) {
                ChkAsa.setSelected(true);
            } else {
                ChkAsa.setSelected(false);
            }
            
            NoTelp.setText(tbObat.getValueAt(tbObat.getSelectedRow(),30).toString());
            KasusKatarak.setSelectedItem(tbObat.getValueAt(tbObat.getSelectedRow(),31).toString());
            Ket.setText(tbObat.getValueAt(tbObat.getSelectedRow(),33).toString());
            suplesi.setSelectedItem(tbObat.getValueAt(tbObat.getSelectedRow(),34).toString());
            NoSEPSuplesi.setText(tbObat.getValueAt(tbObat.getSelectedRow(),35).toString());
            KdProv.setText(tbObat.getValueAt(tbObat.getSelectedRow(),36).toString());
            NmProv.setText(tbObat.getValueAt(tbObat.getSelectedRow(),37).toString());
            KdKab.setText(tbObat.getValueAt(tbObat.getSelectedRow(),38).toString());
            NmKab.setText(tbObat.getValueAt(tbObat.getSelectedRow(),39).toString());
            KdKec.setText(tbObat.getValueAt(tbObat.getSelectedRow(),40).toString());
            NmKec.setText(tbObat.getValueAt(tbObat.getSelectedRow(),41).toString());
            noSKDP.setText(tbObat.getValueAt(tbObat.getSelectedRow(),42).toString());
            Kddpjp.setText(tbObat.getValueAt(tbObat.getSelectedRow(),43).toString());
            NmDPJP.setText(tbObat.getValueAt(tbObat.getSelectedRow(),44).toString());
            
            Valid.SetTgl(TanggalSEP,tbObat.getValueAt(tbObat.getSelectedRow(),4).toString());
            Valid.SetTgl(TanggalRujukKeluar,tbObat.getValueAt(tbObat.getSelectedRow(),4).toString());
            Valid.SetTgl(TanggalRujuk,tbObat.getValueAt(tbObat.getSelectedRow(),5).toString());            
            Valid.SetTgl(TanggalKejadian,tbObat.getValueAt(tbObat.getSelectedRow(),32).toString());
            Status.setText("AKTIF");
        }
    }

    public static class HttpEntityEnclosingDeleteRequest extends HttpEntityEnclosingRequestBase {
        public HttpEntityEnclosingDeleteRequest(final URI uri) {
            super();
            setURI(uri);
        }

        @Override
        public String getMethod() {
            return "DELETE";
        }
    }

    @Test
    public void bodyWithDeleteRequest() throws Exception {
        RestTemplate restTemplate = new RestTemplate();
        SSLContext sslContext = SSLContext.getInstance("SSL");
        javax.net.ssl.TrustManager[] trustManagers= {
            new X509TrustManager() {
                public X509Certificate[] getAcceptedIssuers() {return null;}
                public void checkServerTrusted(X509Certificate[] arg0, String arg1)throws CertificateException {}
                public void checkClientTrusted(X509Certificate[] arg0, String arg1)throws CertificateException {}
            }
        };
        sslContext.init(null,trustManagers , new SecureRandom());
        SSLSocketFactory sslFactory=new SSLSocketFactory(sslContext,SSLSocketFactory.ALLOW_ALL_HOSTNAME_VERIFIER);
        Scheme scheme=new Scheme("https",443,sslFactory);
    
        HttpComponentsClientHttpRequestFactory factory=new HttpComponentsClientHttpRequestFactory(){
            @Override
            protected HttpUriRequest createHttpUriRequest(HttpMethod httpMethod, URI uri) {
                if (HttpMethod.DELETE == httpMethod) {
                    return new HttpEntityEnclosingDeleteRequest(uri);
                }
                return super.createHttpUriRequest(httpMethod, uri);
            }
        };
        factory.getHttpClient().getConnectionManager().getSchemeRegistry().register(scheme);
        restTemplate.setRequestFactory(factory);
        
        try {
            URL = prop.getProperty("URLAPIBPJS")+"/SEP/Delete";	
            
            HttpHeaders headers = new HttpHeaders();
            headers.setContentType(MediaType.APPLICATION_FORM_URLENCODED);
            headers.add("X-Cons-ID",prop.getProperty("CONSIDAPIBPJS"));
            headers.add("X-Timestamp",String.valueOf(api.GetUTCdatetimeAsString()));            
            headers.add("X-Signature",api.getHmac());
            requestJson ="{\"request\":{\"t_sep\":{\"noSep\":\""+tbObat.getValueAt(tbObat.getSelectedRow(),0).toString()+"\",\"user\":\""+user+"\"}}}";            
            ObjectMapper mapper = new ObjectMapper();
            JsonNode root = mapper.readTree(restTemplate.exchange(URL, HttpMethod.DELETE,new HttpEntity<String>(requestJson,headers), String.class).getBody());
            JsonNode nameNode = root.path("metaData");
            System.out.println("code : "+nameNode.path("code").asText());
            System.out.println("message : "+nameNode.path("message").asText());
            if(nameNode.path("code").asText().equals("200")){
                Sequel.meghapus("bridging_sep","no_sep",tbObat.getValueAt(tbObat.getSelectedRow(),0).toString());
                tampil();
                emptTeks();
            }else{
                JOptionPane.showMessageDialog(null,nameNode.path("message").asText());
            }
        } catch (Exception e) {   
            System.out.println("Notif : "+e);
        }
    }

    public void isForm(){
        if (ChkInput.isSelected() == true) {
            ChkInput.setVisible(false);
            PanelInput.setPreferredSize(new Dimension(WIDTH, 360));
            FormInput.setVisible(true);
            ChkInput.setVisible(true);
        } else if (ChkInput.isSelected() == false) {
            ChkInput.setVisible(false);
            PanelInput.setPreferredSize(new Dimension(WIDTH, 20));
            FormInput.setVisible(false);
            ChkInput.setVisible(true);
        }
    }

    private void EksekutifItemStateChanged(java.awt.event.ItemEvent evt){

    }

    private void insertSEP(){
        try {
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
            
            Sequel.menyimpantf("bridging_sep_backup","?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?","SEP",48,new String[]{
                     "-",TNoRw.getText(),Valid.SetTgl(TanggalSEP.getSelectedItem()+""),Valid.SetTgl(TanggalRujuk.getSelectedItem()+""),
                     NoRujukan.getText(),KdPpkRujukan.getText(), rujukanSEP.getText(),KdPPK.getText(), NmPPK.getText(),
                     JenisPelayanan.getSelectedItem().toString().substring(0,1), Catatan.getText(),KdPenyakit.getText(),
                     NmPenyakit.getText(),KdPoli.getText(),NmPoli.getText(), Kelas.getSelectedItem().toString().substring(0,1),
                     LakaLantas.getSelectedItem().toString().substring(0,1),LokasiLaka.getText(),user,
                     TNoRM.getText(),TPasien.getText(),TglLahir.getText(),JenisPeserta.getText(),JK.getText(),NoKartu.getText(),
                     "0000-00-00 00:00:00",AsalRujukan.getSelectedItem().toString(),Eksekutif.getSelectedItem().toString(),
                     COB.getSelectedItem().toString(),penjamin,NoTelp.getText(),"XXX","PETUGAS MENGIRIM",
                     KasusKatarak.getSelectedItem().toString().substring(0,1),tglkkl,Ket.getText(),suplesi.getSelectedItem().toString(),
                     NoSEPSuplesi.getText(),KdProv.getText(),NmProv.getText(),KdKab.getText(),NmKab.getText(),
                     KdKec.getText(),NmKec.getText(),noSKDP.getText(),Kddpjp.getText(),NmDPJP.getText(),""
                 });
            
            URL = prop.getProperty("URLAPIBPJS")+"/SEP/1.1/insert";
            HttpHeaders headers = new HttpHeaders();
            headers.setContentType(MediaType.APPLICATION_FORM_URLENCODED);
            headers.add("X-Cons-ID",prop.getProperty("CONSIDAPIBPJS"));
            headers.add("X-Timestamp",String.valueOf(api.GetUTCdatetimeAsString()));
            headers.add("X-Signature",api.getHmac());
            
            requestJson = "{"
                    + "\"request\":"
                    + "{"
                    + "\"t_sep\":"
                    + "{"
                    + "\"noKartu\":\"" + NoKartu.getText() + "\","
                    + "\"tglSep\":\"" + Valid.SetTgl(TanggalSEP.getSelectedItem() + "") + "\","
                    + "\"ppkPelayanan\":\"" + KdPPK.getText() + "\","
                    + "\"jnsPelayanan\":\"" + JenisPelayanan.getSelectedItem().toString().substring(0, 1) + "\","
                    + "\"klsRawat\":\"" + Kelas.getSelectedItem().toString().substring(0, 1) + "\","
                    + "\"noMR\":\"" + TNoRM.getText() + "\","
                    + "\"rujukan\": {"
                    + "\"asalRujukan\":\"" + AsalRujukan.getSelectedItem().toString().substring(0, 1) + "\","
                    + "\"tglRujukan\":\"" + Valid.SetTgl(TanggalRujuk.getSelectedItem() + "") + "\","
                    + "\"noRujukan\":\"" + NoRujukan.getText() + "\","
                    + "\"ppkRujukan\":\"" + KdPpkRujukan.getText() + "\""
                    + "},"
                    + "\"catatan\":\"" + Catatan.getText() + "\","
                    + "\"diagAwal\":\"" + KdPenyakit.getText() + "\","
                    + "\"poli\": {"
                    + "\"tujuan\": \"" + KdPoli.getText() + "\","
                    + "\"eksekutif\": \"" + Eksekutif.getSelectedItem().toString().substring(0, 1) + "\""
                    + "},"
                    + "\"cob\": {"
                    + "\"cob\": \"" + COB.getSelectedItem().toString().substring(0, 1) + "\""
                    + "},"
                    
//ini yang baru -----------
                    + "\"katarak\": {"
                    + "\"katarak\":\"" + KasusKatarak.getSelectedItem().toString().substring(0, 1) + "\","
                    + "},"

//sampai sini -----------	                    
                    + "\"jaminan\": {"
                    + "\"lakaLantas\":\"" + LakaLantas.getSelectedItem().toString().substring(0, 1) + "\","

//ini yang baru -----------                    
                    + "\"penjamin\": {"
                    + "\"penjamin\": \"" + penjamin + "\","
                    + "\"tglKejadian\":\"" + tglkkl + "\","
                    + "\"keterangan\":\"" + Ket.getText() + "\","
                    + "\"suplesi\": {"
                    + "\"suplesi\":\"" + suplesi.getSelectedItem().toString().substring(0, 1) + "\","
                    + "\"noSepSuplesi\":\"" + NoSEPSuplesi.getText() + "\","
                    + "\"lokasiLaka\": {"
                    + "\"kdPropinsi\":\"" + KdProv.getText() + "\","
                    + "\"kdKabupaten\":\"" + KdKab.getText() + "\","
                    + "\"kdKecamatan\":\"" + KdKec.getText() + "\","
                    + "}"
                    + "}"
                    + "}"
                    
//sampai sini -----------
                    + "},"
//ini yang baru -----------                    
                    + "\"skdp\": {"
                    + "\"noSurat\":\"" + noSKDP.getText() + "\","
                    + "\"kodeDPJP\":\"" + Kddpjp.getText() + "\""
                    + "},"
                    
//sampai sini -----------
                    + "\"noTelp\": \"" + NoTelp.getText() + "\","
                    + "\"user\":\"" + user + "\""
                    + "}"
                    + "}"
                    + "}";

            HttpEntity requestEntity = new HttpEntity(requestJson,headers);
            ObjectMapper mapper = new ObjectMapper();
            JsonNode root = mapper.readTree(api.getRest().exchange(URL, HttpMethod.POST, requestEntity, String.class).getBody());            
            JsonNode nameNode = root.path("metaData");
            System.out.println("code : "+nameNode.path("code").asText());
            System.out.println("message : "+nameNode.path("message").asText());
            JsonNode response = root.path("response").path("sep").path("noSep");

            //Simpan Ke tabel bridging_sep_backup
            if(Sequel.menyimpantf("bridging_sep_backup","?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?","SEP",48,new String[]{
                     response.asText(),TNoRw.getText(),Valid.SetTgl(TanggalSEP.getSelectedItem()+""),Valid.SetTgl(TanggalRujuk.getSelectedItem()+""),
                     NoRujukan.getText(),KdPpkRujukan.getText(), rujukanSEP.getText(),KdPPK.getText(), NmPPK.getText(),
                     JenisPelayanan.getSelectedItem().toString().substring(0,1), Catatan.getText(),KdPenyakit.getText(),
                     NmPenyakit.getText(),KdPoli.getText(),NmPoli.getText(), Kelas.getSelectedItem().toString().substring(0,1),
                     LakaLantas.getSelectedItem().toString().substring(0,1),LokasiLaka.getText(),user,
                     TNoRM.getText(),TPasien.getText(),TglLahir.getText(),JenisPeserta.getText(),JK.getText(),NoKartu.getText(),
                     "0000-00-00 00:00:00",AsalRujukan.getSelectedItem().toString(),Eksekutif.getSelectedItem().toString(),
                     COB.getSelectedItem().toString(),penjamin,NoTelp.getText(),nameNode.path("code").asText(),nameNode.path("message").asText(),
                     KasusKatarak.getSelectedItem().toString().substring(0,1),tglkkl,Ket.getText(),suplesi.getSelectedItem().toString(),
                     NoSEPSuplesi.getText(),KdProv.getText(),NmProv.getText(),KdKab.getText(),NmKab.getText(),
                     KdKec.getText(),NmKec.getText(),noSKDP.getText(),Kddpjp.getText(),NmDPJP.getText(),""
                 })== false){
                Sequel.menyimpantf("bridging_sep_backup","?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?","SEP",48,new String[]{
                     "-",TNoRw.getText(),Valid.SetTgl(TanggalSEP.getSelectedItem()+""),Valid.SetTgl(TanggalRujuk.getSelectedItem()+""),
                     NoRujukan.getText(),KdPpkRujukan.getText(), rujukanSEP.getText(),KdPPK.getText(), NmPPK.getText(),
                     JenisPelayanan.getSelectedItem().toString().substring(0,1), Catatan.getText(),KdPenyakit.getText(),
                     NmPenyakit.getText(),KdPoli.getText(),NmPoli.getText(), Kelas.getSelectedItem().toString().substring(0,1),
                     LakaLantas.getSelectedItem().toString().substring(0,1),LokasiLaka.getText(),user,
                     TNoRM.getText(),TPasien.getText(),TglLahir.getText(),JenisPeserta.getText(),JK.getText(),NoKartu.getText(),
                     "0000-00-00 00:00:00",AsalRujukan.getSelectedItem().toString(),Eksekutif.getSelectedItem().toString(),
                     COB.getSelectedItem().toString(),penjamin,NoTelp.getText(),nameNode.path("code").asText(),nameNode.path("message").asText(),
                     KasusKatarak.getSelectedItem().toString().substring(0,1),tglkkl,Ket.getText(),suplesi.getSelectedItem().toString(),
                     NoSEPSuplesi.getText(),KdProv.getText(),NmProv.getText(),KdKab.getText(),NmKab.getText(),
                     KdKec.getText(),NmKec.getText(),noSKDP.getText(),Kddpjp.getText(),NmDPJP.getText(),""
                 });
            }


            if(nameNode.path("code").asText().equals("200")){
                System.out.println("SEP : "+response.asText());
                 if(Sequel.menyimpantf("bridging_sep","?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?","SEP",46,new String[]{
                     response.asText(),TNoRw.getText(),Valid.SetTgl(TanggalSEP.getSelectedItem()+""),Valid.SetTgl(TanggalRujuk.getSelectedItem()+""),
                     NoRujukan.getText(),KdPpkRujukan.getText(), rujukanSEP.getText(),KdPPK.getText(), NmPPK.getText(),
                     JenisPelayanan.getSelectedItem().toString().substring(0,1), Catatan.getText(),KdPenyakit.getText(),
                     NmPenyakit.getText(),KdPoli.getText(),NmPoli.getText(), Kelas.getSelectedItem().toString().substring(0,1),
                     LakaLantas.getSelectedItem().toString().substring(0,1),LokasiLaka.getText(),user,
                     TNoRM.getText(),TPasien.getText(),TglLahir.getText(),JenisPeserta.getText(),JK.getText(),NoKartu.getText(),
                     "0000-00-00 00:00:00",AsalRujukan.getSelectedItem().toString(),Eksekutif.getSelectedItem().toString(),
                     COB.getSelectedItem().toString(),penjamin,NoTelp.getText(),KasusKatarak.getSelectedItem().toString(),
                     tglkkl,Ket.getText(),suplesi.getSelectedItem().toString(),
                     NoSEPSuplesi.getText(),KdProv.getText(),NmProv.getText(),KdKab.getText(),NmKab.getText(),
                     KdKec.getText(),NmKec.getText(),noSKDP.getText(),Kddpjp.getText(),NmDPJP.getText(),""
                     
                 })==true){
                     Sequel.menyimpan("rujuk_masuk", "?,?,?,?,?,?,?,?,?,?,?", 11, new String[]{
                         TNoRw.getText(), NmPpkRujukan.getText(), "-", NoRujukan.getText(), "0", NmPpkRujukan.getText(), KdPenyakit.getText(), "-",
                         "-", NoBalasan.getText(), kode_rujukanya.getText()
                     });
                     
                     Sequel.mengedit("kelengkapan_booking_sep_bpjs", "no_rawat='" + TNoRw.getText() + "'", "status_cetak_sep='SUDAH' ");
                     
                     if(JenisPelayanan.getSelectedIndex()==1){
                        try {
                            URL = prop.getProperty("URLAPIBPJS")+"/Sep/updtglplg";

                            HttpHeaders headers2 = new HttpHeaders();
                            headers2.setContentType(MediaType.APPLICATION_FORM_URLENCODED);
                            headers2.add("X-Cons-ID",prop.getProperty("CONSIDAPIBPJS"));
                            headers2.add("X-Timestamp",String.valueOf(api.GetUTCdatetimeAsString()));
                            headers2.add("X-Signature",api.getHmac());
                            requestJson ="{" +
                                          "\"request\":" +
                                             "{" +
                                                "\"t_sep\":" +
                                                   "{" +
                                                    "\"noSep\":\""+response.asText()+"\"," +
                                                    "\"tglPulang\":\""+Valid.SetTgl(TanggalSEP.getSelectedItem()+"")+"\"," +
                                                    "\"user\":\""+user+"\"" +
                                                   "}" +
                                             "}" +
                                         "}";
                            HttpEntity requestEntity2 = new HttpEntity(requestJson,headers2);
                            ObjectMapper mapper2 = new ObjectMapper();
                            JsonNode root2 = mapper2.readTree(api.getRest().exchange(URL, HttpMethod.PUT, requestEntity2, String.class).getBody());
                            JsonNode nameNode2 = root2.path("metaData");
                            System.out.println("code : "+nameNode2.path("code").asText());
                            System.out.println("message : "+nameNode2.path("message").asText());
                            JsonNode response2 = root2.path("response");
                            if(nameNode2.path("code").asText().equals("200")){
                                Sequel.mengedit("bridging_sep","no_sep=?","tglpulang=?",2,new String[]{                             
                                     Valid.SetTgl(TanggalSEP.getSelectedItem()+""),
                                     response.asText()
                                });
                                emptTeks();
                                tampil();
                            }else{
                                JOptionPane.showMessageDialog(null,nameNode2.path("message").asText());
                            }
                        }catch (Exception ex) {
                            System.out.println("Notifikasi Bridging : "+ex);
                            if(ex.toString().contains("UnknownHostException")){
                                JOptionPane.showMessageDialog(null,"Koneksi ke server BPJS terputus...!");
                            }
                        }
                     }
                     emptTeks();
                     tampil();
                 }
            }else{
                JOptionPane.showMessageDialog(null,nameNode.path("message").asText());
            }
        }catch (Exception ex) {
            System.out.println("Notifikasi Bridging : "+ex);
            if(ex.toString().contains("UnknownHostException")){
                JOptionPane.showMessageDialog(null,"Koneksi ke server BPJS terputus...!");
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
    
    public void cekSEPVclaim() {
        if (!NoSEP.getText().equals("")) {
            try {
                URL = prop.getProperty("URLAPIBPJS")+"/SEP/"+NoSEP.getText();	

                HttpHeaders headers = new HttpHeaders();
                headers.setContentType(MediaType.APPLICATION_FORM_URLENCODED);
                headers.add("X-Cons-ID",prop.getProperty("CONSIDAPIBPJS"));
                headers.add("X-Timestamp",String.valueOf(api.GetUTCdatetimeAsString()));            
                headers.add("X-Signature",api.getHmac());
                HttpEntity requestEntity = new HttpEntity(headers);
                
                //System.out.println(rest.exchange(URL, HttpMethod.GET, requestEntity, String.class).getBody());
                ObjectMapper mapper = new ObjectMapper();
                JsonNode root = mapper.readTree(api.getRest().exchange(URL, HttpMethod.GET, requestEntity, String.class).getBody());
                JsonNode nameNode = root.path("metaData");
                System.out.println("code : "+nameNode.path("code").asText());
                System.out.println("message : "+nameNode.path("message").asText());
                if(nameNode.path("message").asText().equals("Sukses")){
                    BtnSimpan6.setEnabled(true);
                    BtnSimpan6.requestFocus();
                    
                    JsonNode response = root.path("response");
                    Catatan.setText(response.path("catatan").asText());
                    KdPoli.setText("");
                    NmPoli.setText(response.path("poli").asText());
                    NmPenyakit.setText(response.path("diagnosa").asText());
                    Valid.SetTgl(TanggalSEP,response.path("tglSep").asText());
                    
                    if(response.path("jnsPelayanan").asText().toLowerCase().contains("inap")){
                        JenisPelayanan.setSelectedIndex(0);
                    }else{
                        JenisPelayanan.setSelectedIndex(1);
                    }
                    
                    if(response.path("kelasRawat").asText().toLowerCase().equals("1")){
                        Kelas.setSelectedIndex(0);
                    }else if(response.path("kelasRawat").asText().toLowerCase().equals("2")){
                        Kelas.setSelectedIndex(1);
                    }else if(response.path("kelasRawat").asText().toLowerCase().equals("3")){
                        Kelas.setSelectedIndex(2);
                    }
                    
                    if(response.path("poliEksekutif").asText().equals("0")){
                        Eksekutif.setSelectedIndex(0);
                    }else{
                        Eksekutif.setSelectedIndex(1);
                    }
                }else {
                    JOptionPane.showMessageDialog(null,nameNode.path("message").asText());                
                }   
            } catch (Exception ex) {
                System.out.println("Notifikasi Peserta : "+ex);
                if(ex.toString().contains("UnknownHostException")){
                    JOptionPane.showMessageDialog(rootPane,"Koneksi ke server BPJS terputus...!");
                    dispose();
                }
            }
        }
    }
    
    public void cekLAKA() {
        noSKDP.setText("-");
        
        if (LakaLantas.getSelectedItem().equals("0. Tidak")) {
            LabTglkll.setVisible(false);
            TanggalKejadian.setVisible(false);
            LabjaminKll.setVisible(false);
            ChkJasaRaharja.setVisible(false);
            ChkBPJSTenaga.setVisible(false);
            ChkTaspen.setVisible(false);
            ChkAsa.setVisible(false);
            LabKetkll.setVisible(false);
            Ket.setVisible(false);
            LabProv.setVisible(false);
            KdProv.setVisible(false);
            NmProv.setVisible(false);
            btnProv.setVisible(false);
            LabKab.setVisible(false);
            KdKab.setVisible(false);
            NmKab.setVisible(false);
            btnKab.setVisible(false);
            LabKec.setVisible(false);
            KdKec.setVisible(false);
            NmKec.setVisible(false);
            btnKec.setVisible(false);
            LabSup.setVisible(false);
            suplesi.setVisible(false);
            LabNoSup.setVisible(false);
            NoSEPSuplesi.setVisible(false);
        } else {
            LabTglkll.setVisible(true);
            TanggalKejadian.setVisible(true);
            LabjaminKll.setVisible(true);
            ChkJasaRaharja.setVisible(true);
            ChkBPJSTenaga.setVisible(true);
            ChkTaspen.setVisible(true);
            ChkAsa.setVisible(true);
            LabKetkll.setVisible(true);
            Ket.setVisible(true);
            LabProv.setVisible(true);
            KdProv.setVisible(true);
            NmProv.setVisible(true);
            btnProv.setVisible(true);
            LabKab.setVisible(true);
            KdKab.setVisible(true);
            NmKab.setVisible(true);
            btnKab.setVisible(true);
            LabKec.setVisible(true);
            KdKec.setVisible(true);
            NmKec.setVisible(true);
            btnKec.setVisible(true);
            LabSup.setVisible(true);
            suplesi.setVisible(true);
            LabNoSup.setVisible(true);
            NoSEPSuplesi.setVisible(true);
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
            
            jLabel9.setText("No. SPRI :");
            NoRujukan.setText(TNoRw.getText());
            noSKDP.setText(TNoRw.getText());
            AsalRujukan.setSelectedIndex(1);            
        } else {
            LabelPoli.setVisible(true);
            KdPoli.setVisible(true);
            NmPoli.setVisible(true);
            btnPoli.setVisible(true);
            jLabel9.setText("No. Surat SKDP :");
            AsalRujukan.setSelectedIndex(0);
        }
    }
    
    public void tampilFaskes1() {
    try {            
            prop.loadFromXML(new FileInputStream("setting/database.xml"));
            String URL = prop.getProperty("URLAPIBPJS")+"/Rujukan/Peserta/"+NoKartu.getText();
            
            HttpHeaders headers = new HttpHeaders();
            headers.setContentType(MediaType.APPLICATION_JSON);
	    headers.add("X-Cons-ID",prop.getProperty("CONSIDAPIBPJS"));
	    headers.add("X-Timestamp",String.valueOf(api.GetUTCdatetimeAsString()));            
	    headers.add("X-Signature",api.getHmac());
            
	    HttpEntity requestEntity = new HttpEntity(headers);
	    ObjectMapper mapper = new ObjectMapper();
            JsonNode root = mapper.readTree(api.getRest().exchange(URL, HttpMethod.GET, requestEntity, String.class).getBody());
            JsonNode nameNode = root.path("metaData");
            if(nameNode.path("code").asText().equals("200")){
                Valid.tabelKosong(tabMode1);
                JsonNode response = root.path("response").path("rujukan");
                tabMode1.addRow(new Object[]{
                    "1",response.path("noKunjungan").asText(),
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
            }else {
                JOptionPane.showMessageDialog(null,nameNode.path("message").asText()+" di Faskes 1.");                
            }  
        } catch (Exception ex) {
            System.out.println("Notifikasi : "+ex);
            if(ex.toString().contains("UnknownHostException")){
                JOptionPane.showMessageDialog(rootPane,"Koneksi ke server BPJS terputus...!");
            }
        }
    }
    
    public void tampilFaskes2() {
    try {            
            prop.loadFromXML(new FileInputStream("setting/database.xml"));
            String URL = prop.getProperty("URLAPIBPJS")+"/Rujukan/RS/Peserta/"+NoKartu.getText();
            
            HttpHeaders headers = new HttpHeaders();
            headers.setContentType(MediaType.APPLICATION_JSON);
	    headers.add("X-Cons-ID",prop.getProperty("CONSIDAPIBPJS"));
	    headers.add("X-Timestamp",String.valueOf(api.GetUTCdatetimeAsString()));            
	    headers.add("X-Signature",api.getHmac());
            
	    HttpEntity requestEntity = new HttpEntity(headers);
	    ObjectMapper mapper = new ObjectMapper();
            JsonNode root = mapper.readTree(api.getRest().exchange(URL, HttpMethod.GET, requestEntity, String.class).getBody());
            JsonNode nameNode = root.path("metaData");
            if(nameNode.path("code").asText().equals("200")){
                Valid.tabelKosong(tabMode2);
                JsonNode response = root.path("response").path("rujukan");
                tabMode2.addRow(new Object[]{
                    "1",response.path("noKunjungan").asText(),
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
            }else {
                JOptionPane.showMessageDialog(null,nameNode.path("message").asText()+" di Faskes 2.");                
            }  
        } catch (Exception ex) {
            System.out.println("Notifikasi : "+ex);
            if(ex.toString().contains("UnknownHostException")){
                JOptionPane.showMessageDialog(rootPane,"Koneksi ke server BPJS terputus...!");
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
                            list.path("poliRujukan").path("nama").asText(),
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
            String URL = prop.getProperty("URLAPIBPJS")+"/Rujukan/RS/List/Peserta/"+NoKartu.getText();
            
            HttpHeaders headers = new HttpHeaders();
            headers.setContentType(MediaType.APPLICATION_JSON);
	    headers.add("X-Cons-ID",prop.getProperty("CONSIDAPIBPJS"));
	    headers.add("X-Timestamp",String.valueOf(api.GetUTCdatetimeAsString()));            
	    headers.add("X-Signature",api.getHmac());
            
	    HttpEntity requestEntity = new HttpEntity(headers);
	    ObjectMapper mapper = new ObjectMapper();
            JsonNode root = mapper.readTree(api.getRest().exchange(URL, HttpMethod.GET, requestEntity, String.class).getBody());
            JsonNode nameNode = root.path("metaData");
            if(nameNode.path("code").asText().equals("200")){
                Valid.tabelKosong(tabMode4);                
                JsonNode response = root.path("response");
                if(response.path("rujukan").isArray()){
                    i=1;
                    for(JsonNode list:response.path("rujukan")){
                        tabMode4.addRow(new Object[]{
                            i+".",
                            list.path("noKunjungan").asText(),
                            list.path("tglKunjungan").asText(),
                            list.path("provPerujuk").path("kode").asText(),
                            list.path("provPerujuk").path("nama").asText(),
                            list.path("poliRujukan").path("nama").asText(),
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
            }else {
                JOptionPane.showMessageDialog(null,nameNode.path("message").asText()+" di Faskes 2 data rujukan banyak.");                
            }  
        } catch (Exception ex) {
            System.out.println("Notifikasi : "+ex);
            if(ex.toString().contains("UnknownHostException")){
                JOptionPane.showMessageDialog(rootPane,"Koneksi ke server BPJS terputus...!");
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
            KdPoli.setText(tbFaskes1.getValueAt(tbFaskes1.getSelectedRow(), 9).toString());
            NmPoli.setText(tbFaskes1.getValueAt(tbFaskes1.getSelectedRow(), 5).toString());

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
            KdPoli.setText(tbFaskes2.getValueAt(tbFaskes2.getSelectedRow(), 9).toString());
            NmPoli.setText(tbFaskes2.getValueAt(tbFaskes2.getSelectedRow(), 5).toString());            

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
            KdPoli.setText(tbFaskes3.getValueAt(tbFaskes3.getSelectedRow(), 9).toString());
            NmPoli.setText(tbFaskes3.getValueAt(tbFaskes3.getSelectedRow(), 5).toString());

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
            KdPoli.setText(tbFaskes4.getValueAt(tbFaskes4.getSelectedRow(), 9).toString());
            NmPoli.setText(tbFaskes4.getValueAt(tbFaskes4.getSelectedRow(), 5).toString());

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
 
    private void ExportSEPRanap() {
        this.setCursor(Cursor.getPredefinedCursor(Cursor.WAIT_CURSOR));
        dialog_simpan = Valid.openDialog();
        Valid.MyReportToExcel("SELECT p.no_rkm_medis 'No. RM', p.nm_pasien 'Nama Pasien', bs.no_sep 'No. SEP', d.nm_dokter 'DPJP' FROM bridging_sep bs "
                + "INNER JOIN reg_periksa rp on rp.no_rawat=bs.no_rawat INNER JOIN pasien p on p.no_rkm_medis=rp.no_rkm_medis "
                + "INNER JOIN dpjp_ranap dr on dr.no_rawat=bs.no_rawat INNER JOIN dokter d on d.kd_dokter=dr.kd_dokter "
                + "WHERE bs.tglsep BETWEEN '" + Valid.SetTgl(DTPCari1.getSelectedItem() + "") + "' and '" + Valid.SetTgl(DTPCari2.getSelectedItem() + "") + "' and bs.jnspelayanan='1' "
                + "and rp.tgl_registrasi>='" + Valid.SetTgl(DTPCari1.getSelectedItem() + "") + "' and "
                + "rp.tgl_registrasi<='" + Valid.SetTgl(DTPCari2.getSelectedItem() + "") + "'", dialog_simpan);

        JOptionPane.showMessageDialog(null, "Data SEP Rawat Inap berhasil diexport menjadi file excel,..!!!");
        this.setCursor(Cursor.getDefaultCursor());
    }
    
    private void ExportSEPRalan() {
        this.setCursor(Cursor.getPredefinedCursor(Cursor.WAIT_CURSOR));
        dialog_simpan = Valid.openDialog();
        Valid.MyReportToExcel("SELECT p.no_rkm_medis 'No. RM', p.nm_pasien 'Nama Pasien', bs.no_sep 'No. SEP', d.nm_dokter 'Dokter Poliklinik' FROM bridging_sep bs "
                + "INNER JOIN reg_periksa rp on rp.no_rawat=bs.no_rawat INNER JOIN pasien p on p.no_rkm_medis=rp.no_rkm_medis "
                + "INNER JOIN dokter d on d.kd_dokter=rp.kd_dokter INNER JOIN poliklinik pl on pl.kd_poli=rp.kd_poli "
                + "WHERE bs.tglsep BETWEEN '" + Valid.SetTgl(DTPCari1.getSelectedItem() + "") + "' and '" + Valid.SetTgl(DTPCari2.getSelectedItem() + "") + "' "
                + "and bs.jnspelayanan='2' and rp.tgl_registrasi>='" + Valid.SetTgl(DTPCari1.getSelectedItem() + "") + "' "
                + "and rp.tgl_registrasi<='" + Valid.SetTgl(DTPCari2.getSelectedItem() + "") + "'", dialog_simpan);
        
        JOptionPane.showMessageDialog(null, "Data SEP Rawat Jalan berhasil diexport menjadi file excel,..!!!");
        this.setCursor(Cursor.getDefaultCursor());
    }
    
    public JTable getTable(){
        return tbObat;
    }
    
    private void PengajuanBackdate() {
        this.setCursor(Cursor.getPredefinedCursor(Cursor.WAIT_CURSOR));
        if (!NoKartu.getText().equals("")) {
            try {
                URL = prop.getProperty("URLAPIBPJS") + "/Sep/pengajuanSEP";

                HttpHeaders headers = new HttpHeaders();
                headers.setContentType(MediaType.APPLICATION_FORM_URLENCODED);
                headers.add("X-Cons-ID", prop.getProperty("CONSIDAPIBPJS"));
                headers.add("X-Timestamp", String.valueOf(api.GetUTCdatetimeAsString()));
                headers.add("X-Signature", api.getHmac());
                requestJson = " {"
                        + "\"request\": {"
                        + "\"t_sep\": {"
                        + "\"noKartu\": \"" + NoKartu.getText() + "\","
                        + "\"tglSep\": \"" + Valid.SetTgl(TanggalSEP.getSelectedItem() + "") + "\","
                        + "\"jnsPelayanan\": \"" + JenisPelayanan.getSelectedItem().toString().substring(0, 1) + "\","
                        + "\"jnsPengajuan\": \"1\","
                        + "\"keterangan\": \"" + Catatan.getText() + "\","
                        + "\"user\": \"" + user + "\""
                        + "}"
                        + "}"
                        + "}";
                HttpEntity requestEntity = new HttpEntity(requestJson, headers);
                ObjectMapper mapper = new ObjectMapper();
                JsonNode root = mapper.readTree(api.getRest().exchange(URL, HttpMethod.POST, requestEntity, String.class).getBody());
                JsonNode nameNode = root.path("metaData");
                System.out.println("code : " + nameNode.path("code").asText());
                System.out.println("message : " + nameNode.path("message").asText());
                JsonNode response = root.path("response");
                if (nameNode.path("code").asText().equals("200")) {
                    JOptionPane.showMessageDialog(null, "Proses mapping selesai, data nomor rawat berhasil dikirim ke SEP (Pengajuan Backdate)..!!");
                } else {
                    JOptionPane.showMessageDialog(null, nameNode.path("message").asText());
                }
            } catch (Exception ex) {
                System.out.println("Notifikasi Bridging : " + ex);
                if (ex.toString().contains("UnknownHostException")) {
                    JOptionPane.showMessageDialog(null, "Koneksi ke server BPJS terputus...!");
                }
            }
        } else {
            JOptionPane.showMessageDialog(null, "Maaf, silahkan pilih data peserta yang mau dimapping transaksinya...!!!!");
            BtnBatal.requestFocus();
        }
        this.setCursor(Cursor.getDefaultCursor());
    }
    
    private void PengajuanFingerPrint() {
        this.setCursor(Cursor.getPredefinedCursor(Cursor.WAIT_CURSOR));
        if (!NoKartu.getText().equals("")) {
            try {
                URL = prop.getProperty("URLAPIBPJS") + "/Sep/pengajuanSEP";

                HttpHeaders headers = new HttpHeaders();
                headers.setContentType(MediaType.APPLICATION_FORM_URLENCODED);
                headers.add("X-Cons-ID", prop.getProperty("CONSIDAPIBPJS"));
                headers.add("X-Timestamp", String.valueOf(api.GetUTCdatetimeAsString()));
                headers.add("X-Signature", api.getHmac());
                requestJson = " {"
                        + "\"request\": {"
                        + "\"t_sep\": {"
                        + "\"noKartu\": \"" + NoKartu.getText() + "\","
                        + "\"tglSep\": \"" + Valid.SetTgl(TanggalSEP.getSelectedItem() + "") + "\","
                        + "\"jnsPelayanan\": \"" + JenisPelayanan.getSelectedItem().toString().substring(0, 1) + "\","
                        + "\"jnsPengajuan\": \"2\","
                        + "\"keterangan\": \"" + Catatan.getText() + "\","
                        + "\"user\": \"" + user + "\""
                        + "}"
                        + "}"
                        + "}";
                HttpEntity requestEntity = new HttpEntity(requestJson, headers);
                ObjectMapper mapper = new ObjectMapper();
                JsonNode root = mapper.readTree(api.getRest().exchange(URL, HttpMethod.POST, requestEntity, String.class).getBody());
                JsonNode nameNode = root.path("metaData");
                System.out.println("code : " + nameNode.path("code").asText());
                System.out.println("message : " + nameNode.path("message").asText());
                JsonNode response = root.path("response");
                if (nameNode.path("code").asText().equals("200")) {
                    JOptionPane.showMessageDialog(null, "Proses mapping selesai, data nomor rawat berhasil dikirim ke SEP (Pengajuan Finger Print)..!!");
                } else {
                    JOptionPane.showMessageDialog(null, nameNode.path("message").asText());
                }
            } catch (Exception ex) {
                System.out.println("Notifikasi Bridging : " + ex);
                if (ex.toString().contains("UnknownHostException")) {
                    JOptionPane.showMessageDialog(null, "Koneksi ke server BPJS terputus...!");
                }
            }
        } else {
            JOptionPane.showMessageDialog(null, "Maaf, silahkan pilih data peserta yang mau dimapping transaksinya...!!!!");
            BtnBatal.requestFocus();
        }
        this.setCursor(Cursor.getDefaultCursor());
    }
}
