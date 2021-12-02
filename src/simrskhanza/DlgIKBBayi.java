package simrskhanza;

import fungsi.WarnaTable;
import fungsi.batasInput;
import grafikanalisa.grafikberat;
import grafikanalisa.grafikjkelbayi;
import grafikanalisa.grafiklahirbulan;
import grafikanalisa.grafiklahirtahun;
import grafikanalisa.grafikpanjang;
import grafikanalisa.grafikproses;
import fungsi.koneksiDB;
import fungsi.sekuel;
import fungsi.validasi;
import fungsi.var;
import java.awt.Cursor;
import java.awt.Dimension;
import java.awt.Toolkit;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.event.WindowEvent;
import java.awt.event.WindowListener;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.time.LocalDate;
import java.time.Period;
import java.time.ZoneId;
import java.time.temporal.ChronoUnit;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import javax.swing.JButton;
import javax.swing.JOptionPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.event.DocumentEvent;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableColumn;
import kepegawaian.DlgCariPegawai;

public class DlgIKBBayi extends javax.swing.JDialog {

    private final DefaultTableModel tabMode, tabMode1;
    private sekuel Sequel = new sekuel();
    private validasi Valid = new validasi();
    private DlgPasien member = new DlgPasien(null, false);
    public DlgPasien pasien = new DlgPasien(null, false);
    private Connection koneksi = koneksiDB.condb();
    private Date lahir;
    private LocalDate today = LocalDate.now();
    private LocalDate birthday;
    private Period p;
    private PreparedStatement ps;
    private ResultSet rs;
    private long p2;
    private Date tgl = new Date();
    private DlgCariPegawai pegawai = new DlgCariPegawai(null, false);
    private String pengurutan = "", bulan = "", tahun = "", awalantahun = "", awalanbulan = "", posisitahun = "", norwtIbu = "",periodeNow="";
    private int berat = 0;

    /**
     * Creates new form DlgProgramStudi
     *
     * @param parent
     * @param modal
     */
    public DlgIKBBayi(java.awt.Frame parent, boolean modal) {
        super(parent, modal);
        initComponents();

        tabMode = new DefaultTableModel(null, new Object[]{
            "No.RM", "Nama Anak/Bayi", "J.K", "Tgl.Lahir", "Jam Lahir",
            "Umur", "Tgl.Daftar", "Nama Ibu", "Umur Ibu", "Nama Ayah",
            "Umur Ayah", "Alamat Ibu", "Rentang BBB", "Panjang Badan",
            "Lk.Kepala", "Proses Lahir", "Kelahiran Ke", "Diagnsa. Resume",
            "Diagnosa Awal", "Penyulit Kehamilan", "Ketuban", "Lk.Perut",
            "Lk.Dada", "Penolong", "No.SKL", "Kematian Perinatal", "Sebab Kematian",
            "Asal Rujukan", "Dirujuk",
            "Jns. Alamat", "Cara Lahir", "Jns. Penolong", "Apgus Score menit 1",
            "Apgus Score menit 5", "Apgus Score menit 10", "Asal Bayi", "Umur Kehamilan",
            "Rwt. Gabung dg. Ibu", "IMD", "KMC", "BBB Sebenarnya","norwIbunya"
        }) {
            @Override
            public boolean isCellEditable(int rowIndex, int colIndex) {
                return false;
            }
        };
        tbBayi.setModel(tabMode);

        tbBayi.setPreferredScrollableViewportSize(new Dimension(800, 800));
        tbBayi.setAutoResizeMode(JTable.AUTO_RESIZE_OFF);

        for (int i = 0; i < 42; i++) {
            TableColumn column = tbBayi.getColumnModel().getColumn(i);
            if (i == 0) {
                column.setPreferredWidth(80);
            } else if (i == 1) {
                column.setPreferredWidth(150);
            } else if (i == 2) {
                column.setPreferredWidth(30);
            } else if (i == 3) {
                column.setPreferredWidth(75);
            } else if (i == 4) {
                column.setPreferredWidth(65);
            } else if (i == 5) {
                column.setPreferredWidth(80);
            } else if (i == 6) {
                column.setPreferredWidth(70);
            } else if (i == 7) {
                column.setPreferredWidth(150);
            } else if (i == 8) {
                column.setPreferredWidth(60);
            } else if (i == 9) {
                column.setPreferredWidth(150);
            } else if (i == 10) {
                column.setPreferredWidth(60);
            } else if (i == 11) {
                column.setPreferredWidth(150);
            } else if (i == 12) {
                column.setPreferredWidth(130);
            } else if (i == 13) {
                column.setPreferredWidth(80);
            } else if (i == 14) {
                column.setPreferredWidth(70);
            } else if (i == 15) {
                column.setPreferredWidth(90);
            } else if (i == 16) {
                column.setPreferredWidth(70);
            } else if (i == 17) {
                column.setPreferredWidth(160);
            } else if (i == 18) {
                column.setPreferredWidth(160);
            } else if (i == 19) {
                column.setPreferredWidth(100);
            } else if (i == 20) {
                column.setPreferredWidth(100);
            } else if (i == 21) {
                column.setPreferredWidth(60);
            } else if (i == 22) {
                column.setPreferredWidth(60);
            } else if (i == 23) {
                column.setPreferredWidth(150);
            } else if (i == 24) {
                column.setPreferredWidth(150);
            } else if (i == 25) {
                column.setPreferredWidth(150);
            } else if (i == 26) {
                column.setPreferredWidth(150);
            } else if (i == 27) {
                column.setPreferredWidth(120);
            } else if (i == 28) {
                column.setPreferredWidth(70);
            } else if (i == 29) {
                column.setPreferredWidth(140);
            } else if (i == 30) {
                column.setPreferredWidth(100);
            } else if (i == 31) {
                column.setPreferredWidth(100);
            } else if (i == 32) {
                column.setPreferredWidth(120);
            } else if (i == 33) {
                column.setPreferredWidth(120);
            } else if (i == 34) {
                column.setPreferredWidth(120);
            } else if (i == 35) {
                column.setPreferredWidth(60);
            } else if (i == 36) {
                column.setPreferredWidth(90);
            } else if (i == 37) {
                column.setPreferredWidth(120);
            } else if (i == 38) {
                column.setPreferredWidth(40);
            } else if (i == 39) {
                column.setPreferredWidth(40);
            } else if (i == 40) {
                column.setPreferredWidth(100);
            } else if (i == 41) {
//                column.setPreferredWidth(100);
                column.setMinWidth(0);
                column.setMaxWidth(0);
            }
        }
        tbBayi.setDefaultRenderer(Object.class, new WarnaTable());
        
        tabMode1 = new DefaultTableModel(null, new Object[]{"No. Rawat", "No. RM", "Nama Ibu Bayinya","Tgl. Masuk"}) {
            @Override
            public boolean isCellEditable(int rowIndex, int colIndex) {
                return false;
            }
        };
        tbPasienPersalinan.setModel(tabMode1);
        tbPasienPersalinan.setPreferredScrollableViewportSize(new Dimension(500, 500));
        tbPasienPersalinan.setAutoResizeMode(JTable.AUTO_RESIZE_OFF);

        for (int i = 0; i < 4; i++) {
            TableColumn column = tbPasienPersalinan.getColumnModel().getColumn(i);
            if (i == 0) {
                column.setPreferredWidth(130);
            } else if (i == 1) {
                column.setPreferredWidth(50);
            } else if (i == 2) {
                column.setPreferredWidth(300);
            } else if (i == 3) {
                column.setPreferredWidth(75);
            } 
        }
        tbPasienPersalinan.setDefaultRenderer(Object.class, new WarnaTable());

        NoRm.setDocument(new batasInput((byte) 15).getKata(NoRm));
        NmBayi.setDocument(new batasInput((byte) 40).getKata(NmBayi));
        AlamatIbu.setDocument(new batasInput((int) 200).getKata(AlamatIbu));
        Nmibu.setDocument(new batasInput((byte) 50).getKata(Nmibu));
        UmurIbu.setDocument(new batasInput((byte) 8).getKata(UmurIbu));
        NmAyah.setDocument(new batasInput((byte) 50).getKata(NmAyah));
        UmurAyah.setDocument(new batasInput((byte) 8).getKata(UmurAyah));
        Proses.setDocument(new batasInput((byte) 60).getKata(Proses));
        Diagnosa.setDocument(new batasInput((byte) 60).getKata(Diagnosa));
        Ketuban.setDocument(new batasInput((byte) 60).getKata(Ketuban));
        PenyulitKehamilan.setDocument(new batasInput((byte) 60).getKata(PenyulitKehamilan));
        Panjang.setDocument(new batasInput((byte) 10).getKata(Panjang));
        LingkarKepala.setDocument(new batasInput((byte) 10).getKata(LingkarKepala));
        LingkarPerut.setDocument(new batasInput((byte) 10).getKata(LingkarPerut));
        LingkarDada.setDocument(new batasInput((byte) 10).getKata(LingkarDada));
        keterangan.setDocument(new batasInput((byte) 50).getKata(keterangan));
        NoSKL.setDocument(new batasInput((byte) 30).getKata(NoSKL));
        Anakke.setDocument(new batasInput((byte) 2).getOnlyAngka(Anakke));
        benarberat.setDocument(new batasInput((byte) 4).getOnlyAngka(benarberat));

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

        member.addWindowListener(new WindowListener() {
            @Override
            public void windowOpened(WindowEvent e) {
            }

            @Override
            public void windowClosing(WindowEvent e) {
            }

            @Override
            public void windowClosed(WindowEvent e) {
                if (var.getform().equals("DlgIKBBayi")) {
                    if (member.getTable().getSelectedRow() != -1) {
                        NoRm.setText(member.getTable().getValueAt(member.getTable().getSelectedRow(), 1).toString());
                        NmBayi.setText(member.getTable().getValueAt(member.getTable().getSelectedRow(), 2).toString());
                        NmAyah.setText("-");
                        UmurAyah.setText("0");
                        Valid.SetTgl(Lahir, member.getTable().getValueAt(member.getTable().getSelectedRow(), 6).toString());
                        Valid.SetTgl(Daftar, member.getTable().getValueAt(member.getTable().getSelectedRow(), 13).toString());

                        if (member.getTable().getValueAt(member.getTable().getSelectedRow(), 4).toString().equals("L")) {
                            JKel.setSelectedItem("LAKI-LAKI");
                        } else {
                            JKel.setSelectedItem("PEREMPUAN");
                        }
                        //NoRm.requestFocus();
                        autoSKL();
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

        member.getTable().addKeyListener(new KeyListener() {
            @Override
            public void keyTyped(KeyEvent e) {
            }

            @Override
            public void keyPressed(KeyEvent e) {
                if (var.getform().equals("DlgIKBBayi")) {
                    if (e.getKeyCode() == KeyEvent.VK_SPACE) {
                        member.dispose();
                    }
                }
            }

            @Override
            public void keyReleased(KeyEvent e) {
            }
        });

        pegawai.addWindowListener(new WindowListener() {
            @Override
            public void windowOpened(WindowEvent e) {
            }

            @Override
            public void windowClosing(WindowEvent e) {
            }

            @Override
            public void windowClosed(WindowEvent e) {
                if (pegawai.getTable().getSelectedRow() != -1) {
                    KdPenolong.setText(pegawai.tbPegawai.getValueAt(pegawai.tbPegawai.getSelectedRow(), 0).toString());
                    NmPenolong.setText(pegawai.tbPegawai.getValueAt(pegawai.tbPegawai.getSelectedRow(), 1).toString());
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
        ChkInput.setSelected(false);
        isForm();

        pengurutan = Sequel.cariIsi("select urutan from set_urut_no_rkm_medis");
        tahun = Sequel.cariIsi("select tahun from set_urut_no_rkm_medis");
        bulan = Sequel.cariIsi("select bulan from set_urut_no_rkm_medis");
        posisitahun = Sequel.cariIsi("select posisi_tahun_bulan from set_urut_no_rkm_medis");

    }
    private Dimension screen = Toolkit.getDefaultToolkit().getScreenSize();
    private String[] hlm;
    private String jkelcari = "", tglcari = "";

    /*
    private DlgIbu ibu=new DlgIbu(null,false);
    private DlgInapBayi ranap=new DlgInapBayi(null,false);
    private DlgJalanBayi ralan=new DlgJalanBayi(null,false);*/
    /**
     * This method is called from within the constructor to initialize the
     * forem. WARNING: Do NOT modify this code. The content of this method is
     * always regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        Popup = new javax.swing.JPopupMenu();
        ppGrafikjkbayi = new javax.swing.JMenuItem();
        jMenu1 = new javax.swing.JMenu();
        ppGrafikberat = new javax.swing.JMenuItem();
        ppGrafikberatlaki = new javax.swing.JMenuItem();
        ppGrafikberatwn = new javax.swing.JMenuItem();
        jMenu2 = new javax.swing.JMenu();
        ppGrafikpanjang = new javax.swing.JMenuItem();
        ppGrafikpanjanglaki = new javax.swing.JMenuItem();
        ppGrafikpanjangwn = new javax.swing.JMenuItem();
        jMenu3 = new javax.swing.JMenu();
        ppGrafiklahirtahun = new javax.swing.JMenuItem();
        ppGrafiklahirtahunlaki = new javax.swing.JMenuItem();
        ppGrafiklahirtahunwn = new javax.swing.JMenuItem();
        jMenu4 = new javax.swing.JMenu();
        ppGrafiklahirbulan = new javax.swing.JMenuItem();
        ppGrafiklahirbulanlaki = new javax.swing.JMenuItem();
        ppGrafiklahirbulanwn = new javax.swing.JMenuItem();
        jMenu5 = new javax.swing.JMenu();
        ppGrafikproseslahir = new javax.swing.JMenuItem();
        ppGrafikproseslahirlaki = new javax.swing.JMenuItem();
        ppGrafikproseslahirwn = new javax.swing.JMenuItem();
        MnKartu = new javax.swing.JMenuItem();
        MnInformasiBayi = new javax.swing.JMenuItem();
        MnSKL = new javax.swing.JMenuItem();
        MnSKL1 = new javax.swing.JMenuItem();
        Kd2 = new widget.TextBox();
        WindowPasienIbuBersalin = new javax.swing.JDialog();
        internalFrame5 = new widget.InternalFrame();
        jPanel3 = new javax.swing.JPanel();
        panelGlass8 = new widget.panelisi();
        jLabel15 = new widget.Label();
        tgl1 = new widget.Tanggal();
        jLabel17 = new widget.Label();
        tgl2 = new widget.Tanggal();
        jLabel8 = new widget.Label();
        LCount1 = new widget.Label();
        chkIbu = new widget.CekBox();
        panelGlass9 = new widget.panelisi();
        jLabel16 = new widget.Label();
        TCari1 = new widget.TextBox();
        BtnCari1 = new widget.Button();
        BtnAll1 = new widget.Button();
        BtnKeluar1 = new widget.Button();
        Scroll1 = new widget.ScrollPane();
        tbPasienPersalinan = new widget.Table();
        internalFrame1 = new widget.InternalFrame();
        jPanel2 = new javax.swing.JPanel();
        panelisi2 = new widget.panelisi();
        label29 = new widget.Label();
        cmbCrJk = new widget.ComboBox();
        ckTglCari = new widget.CekBox();
        DTPCari1 = new widget.Tanggal();
        label33 = new widget.Label();
        DTPCari2 = new widget.Tanggal();
        label9 = new widget.Label();
        TCari = new widget.TextBox();
        BtnCari = new widget.Button();
        jLabel7 = new widget.Label();
        cmbHlm = new widget.ComboBox();
        panelisi1 = new widget.panelisi();
        BtnSimpan = new widget.Button();
        BtnBatal = new widget.Button();
        BtnHapus = new widget.Button();
        BtnEdit = new widget.Button();
        BtnPrint = new widget.Button();
        BtnAll = new widget.Button();
        label10 = new widget.Label();
        LCount = new widget.Label();
        BtnKeluar = new widget.Button();
        scrollPane1 = new widget.ScrollPane();
        tbBayi = new widget.Table();
        PanelInput = new javax.swing.JPanel();
        ChkInput = new widget.CekBox();
        FormInput = new widget.PanelBiasa();
        label12 = new widget.Label();
        NoRm = new widget.TextBox();
        label18 = new widget.Label();
        label22 = new widget.Label();
        label24 = new widget.Label();
        Proses = new widget.TextBox();
        Anakke = new widget.TextBox();
        label25 = new widget.Label();
        LingkarKepala = new widget.TextBox();
        label27 = new widget.Label();
        label28 = new widget.Label();
        JKel = new widget.ComboBox();
        label23 = new widget.Label();
        label30 = new widget.Label();
        Lahir = new widget.Tanggal();
        label31 = new widget.Label();
        Diagnosa = new widget.TextBox();
        Nmibu = new widget.TextBox();
        label26 = new widget.Label();
        label19 = new widget.Label();
        NmAyah = new widget.TextBox();
        label20 = new widget.Label();
        UmurIbu = new widget.TextBox();
        label21 = new widget.Label();
        AlamatIbu = new widget.TextBox();
        label32 = new widget.Label();
        jam = new widget.ComboBox();
        menit = new widget.ComboBox();
        detik = new widget.ComboBox();
        label34 = new widget.Label();
        Panjang = new widget.TextBox();
        Daftar = new widget.Tanggal();
        scrollPane2 = new widget.ScrollPane();
        keterangan = new widget.TextArea();
        NmBayi = new widget.TextBox();
        label35 = new widget.Label();
        UmurAyah = new widget.TextBox();
        label36 = new widget.Label();
        UmurBayi = new widget.TextBox();
        label37 = new widget.Label();
        PenyulitKehamilan = new widget.TextBox();
        label38 = new widget.Label();
        Ketuban = new widget.TextBox();
        label39 = new widget.Label();
        LingkarDada = new widget.TextBox();
        LingkarPerut = new widget.TextBox();
        label40 = new widget.Label();
        label41 = new widget.Label();
        NoSKL = new widget.TextBox();
        jLabel24 = new widget.Label();
        KdPenolong = new widget.TextBox();
        NmPenolong = new widget.TextBox();
        BtnPenjab = new widget.Button();
        btnPasien = new widget.Button();
        cmbBerat = new widget.ComboBox();
        cmbMatiPerin = new widget.ComboBox();
        cmbSebabMati = new widget.ComboBox();
        cmbRujukan = new widget.ComboBox();
        cmbDirujuk = new widget.ComboBox();
        label42 = new widget.Label();
        label43 = new widget.Label();
        label44 = new widget.Label();
        label45 = new widget.Label();
        label46 = new widget.Label();
        jns_alamat = new widget.ComboBox();
        label47 = new widget.Label();
        cara_lahir = new widget.ComboBox();
        label48 = new widget.Label();
        jns_penolong = new widget.ComboBox();
        label49 = new widget.Label();
        label50 = new widget.Label();
        label51 = new widget.Label();
        label52 = new widget.Label();
        menit1 = new widget.ComboBox();
        menit5 = new widget.ComboBox();
        menit10 = new widget.ComboBox();
        label53 = new widget.Label();
        umur_hamil = new widget.ComboBox();
        label54 = new widget.Label();
        cmbAsalBayi = new widget.ComboBox();
        label55 = new widget.Label();
        cmbRawat = new widget.ComboBox();
        label56 = new widget.Label();
        cmbIMD = new widget.ComboBox();
        label57 = new widget.Label();
        cmbKMC = new widget.ComboBox();
        label58 = new widget.Label();
        benarberat = new widget.TextBox();
        label59 = new widget.Label();
        NoRmIbu = new widget.TextBox();
        btnPasienIbu = new widget.Button();

        Popup.setName("Popup"); // NOI18N

        ppGrafikjkbayi.setBackground(new java.awt.Color(255, 255, 255));
        ppGrafikjkbayi.setFont(new java.awt.Font("Tahoma", 0, 11)); // NOI18N
        ppGrafikjkbayi.setIcon(new javax.swing.ImageIcon(getClass().getResource("/picture/category.png"))); // NOI18N
        ppGrafikjkbayi.setText("Grafik Jns.Kelamin Bayi");
        ppGrafikjkbayi.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        ppGrafikjkbayi.setHorizontalTextPosition(javax.swing.SwingConstants.RIGHT);
        ppGrafikjkbayi.setIconTextGap(5);
        ppGrafikjkbayi.setName("ppGrafikjkbayi"); // NOI18N
        ppGrafikjkbayi.setPreferredSize(new java.awt.Dimension(190, 25));
        ppGrafikjkbayi.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                ppGrafikjkbayiActionPerformed(evt);
            }
        });
        Popup.add(ppGrafikjkbayi);

        jMenu1.setBackground(new java.awt.Color(255, 255, 255));
        jMenu1.setIcon(new javax.swing.ImageIcon(getClass().getResource("/picture/category.png"))); // NOI18N
        jMenu1.setText("Grafik Berat Bayi");
        jMenu1.setFont(new java.awt.Font("Tahoma", 0, 11)); // NOI18N
        jMenu1.setIconTextGap(5);
        jMenu1.setName("jMenu1"); // NOI18N
        jMenu1.setPreferredSize(new java.awt.Dimension(190, 25));

        ppGrafikberat.setBackground(new java.awt.Color(255, 255, 255));
        ppGrafikberat.setFont(new java.awt.Font("Tahoma", 0, 11)); // NOI18N
        ppGrafikberat.setIcon(new javax.swing.ImageIcon(getClass().getResource("/picture/category.png"))); // NOI18N
        ppGrafikberat.setText("Keseluruhan");
        ppGrafikberat.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        ppGrafikberat.setHorizontalTextPosition(javax.swing.SwingConstants.RIGHT);
        ppGrafikberat.setIconTextGap(5);
        ppGrafikberat.setName("ppGrafikberat"); // NOI18N
        ppGrafikberat.setPreferredSize(new java.awt.Dimension(110, 25));
        ppGrafikberat.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                ppGrafikberatActionPerformed(evt);
            }
        });
        jMenu1.add(ppGrafikberat);

        ppGrafikberatlaki.setBackground(new java.awt.Color(255, 255, 255));
        ppGrafikberatlaki.setFont(new java.awt.Font("Tahoma", 0, 11)); // NOI18N
        ppGrafikberatlaki.setIcon(new javax.swing.ImageIcon(getClass().getResource("/picture/category.png"))); // NOI18N
        ppGrafikberatlaki.setText("Laki-Laki");
        ppGrafikberatlaki.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        ppGrafikberatlaki.setHorizontalTextPosition(javax.swing.SwingConstants.RIGHT);
        ppGrafikberatlaki.setIconTextGap(5);
        ppGrafikberatlaki.setName("ppGrafikberatlaki"); // NOI18N
        ppGrafikberatlaki.setPreferredSize(new java.awt.Dimension(110, 25));
        ppGrafikberatlaki.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                ppGrafikberatlakiActionPerformed(evt);
            }
        });
        jMenu1.add(ppGrafikberatlaki);

        ppGrafikberatwn.setBackground(new java.awt.Color(255, 255, 255));
        ppGrafikberatwn.setFont(new java.awt.Font("Tahoma", 0, 11)); // NOI18N
        ppGrafikberatwn.setIcon(new javax.swing.ImageIcon(getClass().getResource("/picture/category.png"))); // NOI18N
        ppGrafikberatwn.setText("Perempuan");
        ppGrafikberatwn.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        ppGrafikberatwn.setHorizontalTextPosition(javax.swing.SwingConstants.RIGHT);
        ppGrafikberatwn.setIconTextGap(5);
        ppGrafikberatwn.setName("ppGrafikberatwn"); // NOI18N
        ppGrafikberatwn.setPreferredSize(new java.awt.Dimension(110, 25));
        ppGrafikberatwn.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                ppGrafikberatwnActionPerformed(evt);
            }
        });
        jMenu1.add(ppGrafikberatwn);

        Popup.add(jMenu1);

        jMenu2.setBackground(new java.awt.Color(255, 255, 255));
        jMenu2.setIcon(new javax.swing.ImageIcon(getClass().getResource("/picture/category.png"))); // NOI18N
        jMenu2.setText("Grafik Panjang Bayi");
        jMenu2.setFont(new java.awt.Font("Tahoma", 0, 11)); // NOI18N
        jMenu2.setIconTextGap(5);
        jMenu2.setName("jMenu2"); // NOI18N
        jMenu2.setPreferredSize(new java.awt.Dimension(190, 25));

        ppGrafikpanjang.setBackground(new java.awt.Color(255, 255, 255));
        ppGrafikpanjang.setFont(new java.awt.Font("Tahoma", 0, 11)); // NOI18N
        ppGrafikpanjang.setIcon(new javax.swing.ImageIcon(getClass().getResource("/picture/category.png"))); // NOI18N
        ppGrafikpanjang.setText("Keseluruhan");
        ppGrafikpanjang.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        ppGrafikpanjang.setHorizontalTextPosition(javax.swing.SwingConstants.RIGHT);
        ppGrafikpanjang.setIconTextGap(5);
        ppGrafikpanjang.setName("ppGrafikpanjang"); // NOI18N
        ppGrafikpanjang.setPreferredSize(new java.awt.Dimension(110, 25));
        ppGrafikpanjang.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                ppGrafikpanjangActionPerformed(evt);
            }
        });
        jMenu2.add(ppGrafikpanjang);

        ppGrafikpanjanglaki.setBackground(new java.awt.Color(255, 255, 255));
        ppGrafikpanjanglaki.setFont(new java.awt.Font("Tahoma", 0, 11)); // NOI18N
        ppGrafikpanjanglaki.setIcon(new javax.swing.ImageIcon(getClass().getResource("/picture/category.png"))); // NOI18N
        ppGrafikpanjanglaki.setText("Laki-Laki");
        ppGrafikpanjanglaki.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        ppGrafikpanjanglaki.setHorizontalTextPosition(javax.swing.SwingConstants.RIGHT);
        ppGrafikpanjanglaki.setIconTextGap(5);
        ppGrafikpanjanglaki.setName("ppGrafikpanjanglaki"); // NOI18N
        ppGrafikpanjanglaki.setPreferredSize(new java.awt.Dimension(110, 25));
        ppGrafikpanjanglaki.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                ppGrafikpanjanglakiActionPerformed(evt);
            }
        });
        jMenu2.add(ppGrafikpanjanglaki);

        ppGrafikpanjangwn.setBackground(new java.awt.Color(255, 255, 255));
        ppGrafikpanjangwn.setFont(new java.awt.Font("Tahoma", 0, 11)); // NOI18N
        ppGrafikpanjangwn.setIcon(new javax.swing.ImageIcon(getClass().getResource("/picture/category.png"))); // NOI18N
        ppGrafikpanjangwn.setText("Perempuan");
        ppGrafikpanjangwn.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        ppGrafikpanjangwn.setHorizontalTextPosition(javax.swing.SwingConstants.RIGHT);
        ppGrafikpanjangwn.setIconTextGap(5);
        ppGrafikpanjangwn.setName("ppGrafikpanjangwn"); // NOI18N
        ppGrafikpanjangwn.setPreferredSize(new java.awt.Dimension(110, 25));
        ppGrafikpanjangwn.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                ppGrafikpanjangwnActionPerformed(evt);
            }
        });
        jMenu2.add(ppGrafikpanjangwn);

        Popup.add(jMenu2);

        jMenu3.setBackground(new java.awt.Color(255, 255, 255));
        jMenu3.setIcon(new javax.swing.ImageIcon(getClass().getResource("/picture/category.png"))); // NOI18N
        jMenu3.setText("Grafik Lahir/Tahun");
        jMenu3.setActionCommand("Grafik Kelahiran Tahunan");
        jMenu3.setFont(new java.awt.Font("Tahoma", 0, 11)); // NOI18N
        jMenu3.setIconTextGap(5);
        jMenu3.setName("jMenu3"); // NOI18N
        jMenu3.setPreferredSize(new java.awt.Dimension(190, 25));

        ppGrafiklahirtahun.setBackground(new java.awt.Color(255, 255, 255));
        ppGrafiklahirtahun.setFont(new java.awt.Font("Tahoma", 0, 11)); // NOI18N
        ppGrafiklahirtahun.setIcon(new javax.swing.ImageIcon(getClass().getResource("/picture/category.png"))); // NOI18N
        ppGrafiklahirtahun.setText("Keseluruhan");
        ppGrafiklahirtahun.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        ppGrafiklahirtahun.setHorizontalTextPosition(javax.swing.SwingConstants.RIGHT);
        ppGrafiklahirtahun.setIconTextGap(5);
        ppGrafiklahirtahun.setName("ppGrafiklahirtahun"); // NOI18N
        ppGrafiklahirtahun.setPreferredSize(new java.awt.Dimension(110, 25));
        ppGrafiklahirtahun.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                ppGrafiklahirtahunActionPerformed(evt);
            }
        });
        jMenu3.add(ppGrafiklahirtahun);

        ppGrafiklahirtahunlaki.setBackground(new java.awt.Color(255, 255, 255));
        ppGrafiklahirtahunlaki.setFont(new java.awt.Font("Tahoma", 0, 11)); // NOI18N
        ppGrafiklahirtahunlaki.setIcon(new javax.swing.ImageIcon(getClass().getResource("/picture/category.png"))); // NOI18N
        ppGrafiklahirtahunlaki.setText("Laki-Laki");
        ppGrafiklahirtahunlaki.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        ppGrafiklahirtahunlaki.setHorizontalTextPosition(javax.swing.SwingConstants.RIGHT);
        ppGrafiklahirtahunlaki.setIconTextGap(5);
        ppGrafiklahirtahunlaki.setName("ppGrafiklahirtahunlaki"); // NOI18N
        ppGrafiklahirtahunlaki.setPreferredSize(new java.awt.Dimension(110, 25));
        ppGrafiklahirtahunlaki.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                ppGrafiklahirtahunlakiActionPerformed(evt);
            }
        });
        jMenu3.add(ppGrafiklahirtahunlaki);

        ppGrafiklahirtahunwn.setBackground(new java.awt.Color(255, 255, 255));
        ppGrafiklahirtahunwn.setFont(new java.awt.Font("Tahoma", 0, 11)); // NOI18N
        ppGrafiklahirtahunwn.setIcon(new javax.swing.ImageIcon(getClass().getResource("/picture/category.png"))); // NOI18N
        ppGrafiklahirtahunwn.setText("Perempuan");
        ppGrafiklahirtahunwn.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        ppGrafiklahirtahunwn.setHorizontalTextPosition(javax.swing.SwingConstants.RIGHT);
        ppGrafiklahirtahunwn.setIconTextGap(5);
        ppGrafiklahirtahunwn.setName("ppGrafiklahirtahunwn"); // NOI18N
        ppGrafiklahirtahunwn.setPreferredSize(new java.awt.Dimension(110, 25));
        ppGrafiklahirtahunwn.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                ppGrafiklahirtahunwnActionPerformed(evt);
            }
        });
        jMenu3.add(ppGrafiklahirtahunwn);

        Popup.add(jMenu3);

        jMenu4.setBackground(new java.awt.Color(255, 255, 255));
        jMenu4.setIcon(new javax.swing.ImageIcon(getClass().getResource("/picture/category.png"))); // NOI18N
        jMenu4.setText("Grafik Lahir/Bulan");
        jMenu4.setActionCommand("Grafik Kelahiran Tahunan");
        jMenu4.setFont(new java.awt.Font("Tahoma", 0, 11)); // NOI18N
        jMenu4.setIconTextGap(5);
        jMenu4.setName("jMenu4"); // NOI18N
        jMenu4.setPreferredSize(new java.awt.Dimension(190, 25));

        ppGrafiklahirbulan.setBackground(new java.awt.Color(255, 255, 255));
        ppGrafiklahirbulan.setFont(new java.awt.Font("Tahoma", 0, 11)); // NOI18N
        ppGrafiklahirbulan.setIcon(new javax.swing.ImageIcon(getClass().getResource("/picture/category.png"))); // NOI18N
        ppGrafiklahirbulan.setText("Keseluruhan");
        ppGrafiklahirbulan.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        ppGrafiklahirbulan.setHorizontalTextPosition(javax.swing.SwingConstants.RIGHT);
        ppGrafiklahirbulan.setIconTextGap(5);
        ppGrafiklahirbulan.setName("ppGrafiklahirbulan"); // NOI18N
        ppGrafiklahirbulan.setPreferredSize(new java.awt.Dimension(110, 25));
        ppGrafiklahirbulan.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                ppGrafiklahirbulanActionPerformed(evt);
            }
        });
        jMenu4.add(ppGrafiklahirbulan);

        ppGrafiklahirbulanlaki.setBackground(new java.awt.Color(255, 255, 255));
        ppGrafiklahirbulanlaki.setFont(new java.awt.Font("Tahoma", 0, 11)); // NOI18N
        ppGrafiklahirbulanlaki.setIcon(new javax.swing.ImageIcon(getClass().getResource("/picture/category.png"))); // NOI18N
        ppGrafiklahirbulanlaki.setText("Laki-Laki");
        ppGrafiklahirbulanlaki.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        ppGrafiklahirbulanlaki.setHorizontalTextPosition(javax.swing.SwingConstants.RIGHT);
        ppGrafiklahirbulanlaki.setIconTextGap(5);
        ppGrafiklahirbulanlaki.setName("ppGrafiklahirbulanlaki"); // NOI18N
        ppGrafiklahirbulanlaki.setPreferredSize(new java.awt.Dimension(110, 25));
        ppGrafiklahirbulanlaki.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                ppGrafiklahirbulanlakiActionPerformed(evt);
            }
        });
        jMenu4.add(ppGrafiklahirbulanlaki);

        ppGrafiklahirbulanwn.setBackground(new java.awt.Color(255, 255, 255));
        ppGrafiklahirbulanwn.setFont(new java.awt.Font("Tahoma", 0, 11)); // NOI18N
        ppGrafiklahirbulanwn.setIcon(new javax.swing.ImageIcon(getClass().getResource("/picture/category.png"))); // NOI18N
        ppGrafiklahirbulanwn.setText("Perempuan");
        ppGrafiklahirbulanwn.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        ppGrafiklahirbulanwn.setHorizontalTextPosition(javax.swing.SwingConstants.RIGHT);
        ppGrafiklahirbulanwn.setIconTextGap(5);
        ppGrafiklahirbulanwn.setName("ppGrafiklahirbulanwn"); // NOI18N
        ppGrafiklahirbulanwn.setPreferredSize(new java.awt.Dimension(110, 25));
        ppGrafiklahirbulanwn.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                ppGrafiklahirbulanwnActionPerformed(evt);
            }
        });
        jMenu4.add(ppGrafiklahirbulanwn);

        Popup.add(jMenu4);

        jMenu5.setBackground(new java.awt.Color(255, 255, 255));
        jMenu5.setIcon(new javax.swing.ImageIcon(getClass().getResource("/picture/category.png"))); // NOI18N
        jMenu5.setText("Grafik Proses Lahir");
        jMenu5.setActionCommand("Grafik Kelahiran Tahunan");
        jMenu5.setFont(new java.awt.Font("Tahoma", 0, 11)); // NOI18N
        jMenu5.setIconTextGap(5);
        jMenu5.setName("jMenu5"); // NOI18N
        jMenu5.setPreferredSize(new java.awt.Dimension(190, 25));

        ppGrafikproseslahir.setBackground(new java.awt.Color(255, 255, 255));
        ppGrafikproseslahir.setFont(new java.awt.Font("Tahoma", 0, 11)); // NOI18N
        ppGrafikproseslahir.setIcon(new javax.swing.ImageIcon(getClass().getResource("/picture/category.png"))); // NOI18N
        ppGrafikproseslahir.setText("Keseluruhan");
        ppGrafikproseslahir.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        ppGrafikproseslahir.setHorizontalTextPosition(javax.swing.SwingConstants.RIGHT);
        ppGrafikproseslahir.setIconTextGap(5);
        ppGrafikproseslahir.setName("ppGrafikproseslahir"); // NOI18N
        ppGrafikproseslahir.setPreferredSize(new java.awt.Dimension(110, 25));
        ppGrafikproseslahir.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                ppGrafikproseslahirActionPerformed(evt);
            }
        });
        jMenu5.add(ppGrafikproseslahir);

        ppGrafikproseslahirlaki.setBackground(new java.awt.Color(255, 255, 255));
        ppGrafikproseslahirlaki.setFont(new java.awt.Font("Tahoma", 0, 11)); // NOI18N
        ppGrafikproseslahirlaki.setIcon(new javax.swing.ImageIcon(getClass().getResource("/picture/category.png"))); // NOI18N
        ppGrafikproseslahirlaki.setText("Laki-Laki");
        ppGrafikproseslahirlaki.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        ppGrafikproseslahirlaki.setHorizontalTextPosition(javax.swing.SwingConstants.RIGHT);
        ppGrafikproseslahirlaki.setIconTextGap(5);
        ppGrafikproseslahirlaki.setName("ppGrafikproseslahirlaki"); // NOI18N
        ppGrafikproseslahirlaki.setPreferredSize(new java.awt.Dimension(110, 25));
        ppGrafikproseslahirlaki.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                ppGrafikproseslahirlakiActionPerformed(evt);
            }
        });
        jMenu5.add(ppGrafikproseslahirlaki);

        ppGrafikproseslahirwn.setBackground(new java.awt.Color(255, 255, 255));
        ppGrafikproseslahirwn.setFont(new java.awt.Font("Tahoma", 0, 11)); // NOI18N
        ppGrafikproseslahirwn.setIcon(new javax.swing.ImageIcon(getClass().getResource("/picture/category.png"))); // NOI18N
        ppGrafikproseslahirwn.setText("Perempuan");
        ppGrafikproseslahirwn.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        ppGrafikproseslahirwn.setHorizontalTextPosition(javax.swing.SwingConstants.RIGHT);
        ppGrafikproseslahirwn.setIconTextGap(5);
        ppGrafikproseslahirwn.setName("ppGrafikproseslahirwn"); // NOI18N
        ppGrafikproseslahirwn.setPreferredSize(new java.awt.Dimension(110, 25));
        ppGrafikproseslahirwn.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                ppGrafikproseslahirwnActionPerformed(evt);
            }
        });
        jMenu5.add(ppGrafikproseslahirwn);

        Popup.add(jMenu5);

        MnKartu.setBackground(new java.awt.Color(255, 255, 255));
        MnKartu.setFont(new java.awt.Font("Tahoma", 0, 11)); // NOI18N
        MnKartu.setIcon(new javax.swing.ImageIcon(getClass().getResource("/picture/category.png"))); // NOI18N
        MnKartu.setText("Kartu Berobat");
        MnKartu.setIconTextGap(5);
        MnKartu.setName("MnKartu"); // NOI18N
        MnKartu.setPreferredSize(new java.awt.Dimension(190, 25));
        MnKartu.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                MnKartuActionPerformed(evt);
            }
        });
        Popup.add(MnKartu);

        MnInformasiBayi.setBackground(new java.awt.Color(255, 255, 255));
        MnInformasiBayi.setFont(new java.awt.Font("Tahoma", 0, 11)); // NOI18N
        MnInformasiBayi.setIcon(new javax.swing.ImageIcon(getClass().getResource("/picture/category.png"))); // NOI18N
        MnInformasiBayi.setText("Label Informasi Bayi");
        MnInformasiBayi.setIconTextGap(5);
        MnInformasiBayi.setName("MnInformasiBayi"); // NOI18N
        MnInformasiBayi.setPreferredSize(new java.awt.Dimension(190, 25));
        MnInformasiBayi.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                MnInformasiBayiActionPerformed(evt);
            }
        });
        Popup.add(MnInformasiBayi);

        MnSKL.setBackground(new java.awt.Color(255, 255, 255));
        MnSKL.setFont(new java.awt.Font("Tahoma", 0, 11)); // NOI18N
        MnSKL.setIcon(new javax.swing.ImageIcon(getClass().getResource("/picture/category.png"))); // NOI18N
        MnSKL.setText("Surat Keterangan Lahir 1");
        MnSKL.setIconTextGap(5);
        MnSKL.setName("MnSKL"); // NOI18N
        MnSKL.setPreferredSize(new java.awt.Dimension(190, 25));
        MnSKL.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                MnSKLActionPerformed(evt);
            }
        });
        Popup.add(MnSKL);

        MnSKL1.setBackground(new java.awt.Color(255, 255, 255));
        MnSKL1.setFont(new java.awt.Font("Tahoma", 0, 11)); // NOI18N
        MnSKL1.setIcon(new javax.swing.ImageIcon(getClass().getResource("/picture/category.png"))); // NOI18N
        MnSKL1.setText("Surat Keterangan Lahir 2");
        MnSKL1.setIconTextGap(5);
        MnSKL1.setName("MnSKL1"); // NOI18N
        MnSKL1.setPreferredSize(new java.awt.Dimension(190, 25));
        MnSKL1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                MnSKL1ActionPerformed(evt);
            }
        });
        Popup.add(MnSKL1);

        Kd2.setName("Kd2"); // NOI18N
        Kd2.setPreferredSize(new java.awt.Dimension(207, 23));

        WindowPasienIbuBersalin.setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);
        WindowPasienIbuBersalin.setName("WindowPasienIbuBersalin"); // NOI18N
        WindowPasienIbuBersalin.setUndecorated(true);
        WindowPasienIbuBersalin.setResizable(false);

        internalFrame5.setBorder(javax.swing.BorderFactory.createTitledBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 153, 255), 3), "::[ Pasien Ibu Bersalin ]::", javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Tahoma", 1, 12))); // NOI18N
        internalFrame5.setName("internalFrame5"); // NOI18N
        internalFrame5.setWarnaBawah(new java.awt.Color(240, 245, 235));
        internalFrame5.setLayout(new java.awt.BorderLayout());

        jPanel3.setName("jPanel3"); // NOI18N
        jPanel3.setOpaque(false);
        jPanel3.setPreferredSize(new java.awt.Dimension(44, 95));
        jPanel3.setLayout(new java.awt.BorderLayout(1, 1));

        panelGlass8.setName("panelGlass8"); // NOI18N
        panelGlass8.setPreferredSize(new java.awt.Dimension(44, 48));
        panelGlass8.setLayout(new java.awt.FlowLayout(java.awt.FlowLayout.LEFT, 5, 9));

        jLabel15.setForeground(new java.awt.Color(0, 0, 0));
        jLabel15.setText("Tgl. Masuk : ");
        jLabel15.setName("jLabel15"); // NOI18N
        jLabel15.setPreferredSize(new java.awt.Dimension(70, 23));
        panelGlass8.add(jLabel15);

        tgl1.setEditable(false);
        tgl1.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "04-08-2021" }));
        tgl1.setDisplayFormat("dd-MM-yyyy");
        tgl1.setName("tgl1"); // NOI18N
        tgl1.setOpaque(false);
        tgl1.setPreferredSize(new java.awt.Dimension(95, 23));
        tgl1.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                tgl1KeyPressed(evt);
            }
        });
        panelGlass8.add(tgl1);

        jLabel17.setForeground(new java.awt.Color(0, 0, 0));
        jLabel17.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel17.setText("s.d");
        jLabel17.setName("jLabel17"); // NOI18N
        jLabel17.setPreferredSize(new java.awt.Dimension(23, 23));
        panelGlass8.add(jLabel17);

        tgl2.setEditable(false);
        tgl2.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "04-08-2021" }));
        tgl2.setDisplayFormat("dd-MM-yyyy");
        tgl2.setName("tgl2"); // NOI18N
        tgl2.setOpaque(false);
        tgl2.setPreferredSize(new java.awt.Dimension(95, 23));
        tgl2.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                tgl2KeyPressed(evt);
            }
        });
        panelGlass8.add(tgl2);

        jLabel8.setForeground(new java.awt.Color(0, 0, 0));
        jLabel8.setText("Record :");
        jLabel8.setName("jLabel8"); // NOI18N
        jLabel8.setPreferredSize(new java.awt.Dimension(55, 30));
        panelGlass8.add(jLabel8);

        LCount1.setForeground(new java.awt.Color(0, 0, 0));
        LCount1.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        LCount1.setText("0");
        LCount1.setName("LCount1"); // NOI18N
        LCount1.setPreferredSize(new java.awt.Dimension(75, 30));
        panelGlass8.add(LCount1);

        chkIbu.setForeground(new java.awt.Color(0, 0, 0));
        chkIbu.setText("Ibunya TIDAK dirawat di RUMAH SAKIT");
        chkIbu.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        chkIbu.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        chkIbu.setName("chkIbu"); // NOI18N
        chkIbu.setOpaque(false);
        chkIbu.setPreferredSize(new java.awt.Dimension(250, 20));
        chkIbu.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                chkIbuActionPerformed(evt);
            }
        });
        panelGlass8.add(chkIbu);

        jPanel3.add(panelGlass8, java.awt.BorderLayout.PAGE_START);

        panelGlass9.setName("panelGlass9"); // NOI18N
        panelGlass9.setPreferredSize(new java.awt.Dimension(44, 48));
        panelGlass9.setLayout(new java.awt.FlowLayout(java.awt.FlowLayout.LEFT, 5, 9));

        jLabel16.setForeground(new java.awt.Color(0, 0, 0));
        jLabel16.setText("Key Word : ");
        jLabel16.setName("jLabel16"); // NOI18N
        jLabel16.setPreferredSize(new java.awt.Dimension(70, 23));
        panelGlass9.add(jLabel16);

        TCari1.setForeground(new java.awt.Color(0, 0, 0));
        TCari1.setName("TCari1"); // NOI18N
        TCari1.setPreferredSize(new java.awt.Dimension(250, 23));
        TCari1.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                TCari1KeyPressed(evt);
            }
        });
        panelGlass9.add(TCari1);

        BtnCari1.setForeground(new java.awt.Color(0, 0, 0));
        BtnCari1.setIcon(new javax.swing.ImageIcon(getClass().getResource("/picture/accept.png"))); // NOI18N
        BtnCari1.setMnemonic('6');
        BtnCari1.setText("Tampilkan Data");
        BtnCari1.setToolTipText("Alt+6");
        BtnCari1.setName("BtnCari1"); // NOI18N
        BtnCari1.setPreferredSize(new java.awt.Dimension(130, 30));
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
        panelGlass9.add(BtnCari1);

        BtnAll1.setForeground(new java.awt.Color(0, 0, 0));
        BtnAll1.setIcon(new javax.swing.ImageIcon(getClass().getResource("/picture/Search-16x16.png"))); // NOI18N
        BtnAll1.setMnemonic('M');
        BtnAll1.setText("Semua Data");
        BtnAll1.setToolTipText("Alt+M");
        BtnAll1.setName("BtnAll1"); // NOI18N
        BtnAll1.setPreferredSize(new java.awt.Dimension(120, 23));
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
        panelGlass9.add(BtnAll1);

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
        panelGlass9.add(BtnKeluar1);

        jPanel3.add(panelGlass9, java.awt.BorderLayout.PAGE_END);

        internalFrame5.add(jPanel3, java.awt.BorderLayout.PAGE_END);

        Scroll1.setToolTipText("Silahkan pilih salah satu data pasien ibu bersalin");
        Scroll1.setName("Scroll1"); // NOI18N
        Scroll1.setOpaque(true);

        tbPasienPersalinan.setToolTipText("");
        tbPasienPersalinan.setName("tbPasienPersalinan"); // NOI18N
        tbPasienPersalinan.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                tbPasienPersalinanMouseClicked(evt);
            }
        });
        tbPasienPersalinan.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                tbPasienPersalinanKeyPressed(evt);
            }
        });
        Scroll1.setViewportView(tbPasienPersalinan);

        internalFrame5.add(Scroll1, java.awt.BorderLayout.CENTER);

        WindowPasienIbuBersalin.getContentPane().add(internalFrame5, java.awt.BorderLayout.CENTER);

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);
        setUndecorated(true);
        setResizable(false);
        addWindowListener(new java.awt.event.WindowAdapter() {
            public void windowOpened(java.awt.event.WindowEvent evt) {
                formWindowOpened(evt);
            }
        });

        internalFrame1.setBorder(javax.swing.BorderFactory.createTitledBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 153, 255), 3), "::[ Data Bayi ]::", javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Tahoma", 1, 12))); // NOI18N
        internalFrame1.setName("internalFrame1"); // NOI18N
        internalFrame1.setLayout(new java.awt.BorderLayout(1, 1));

        jPanel2.setName("jPanel2"); // NOI18N
        jPanel2.setOpaque(false);
        jPanel2.setPreferredSize(new java.awt.Dimension(816, 100));
        jPanel2.setLayout(new java.awt.BorderLayout(1, 1));

        panelisi2.setBackground(new java.awt.Color(255, 150, 255));
        panelisi2.setName("panelisi2"); // NOI18N
        panelisi2.setPreferredSize(new java.awt.Dimension(100, 44));
        panelisi2.setLayout(new java.awt.FlowLayout(java.awt.FlowLayout.LEFT, 4, 9));

        label29.setForeground(new java.awt.Color(0, 0, 0));
        label29.setText("J.K. :");
        label29.setName("label29"); // NOI18N
        label29.setPreferredSize(new java.awt.Dimension(35, 23));
        panelisi2.add(label29);

        cmbCrJk.setForeground(new java.awt.Color(0, 0, 0));
        cmbCrJk.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "SEMUA", "LAKI-LAKI", "PEREMPUAN" }));
        cmbCrJk.setName("cmbCrJk"); // NOI18N
        cmbCrJk.setPreferredSize(new java.awt.Dimension(90, 23));
        cmbCrJk.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                cmbCrJkKeyPressed(evt);
            }
        });
        panelisi2.add(cmbCrJk);

        ckTglCari.setForeground(new java.awt.Color(0, 0, 0));
        ckTglCari.setText("Tgl.Lahir :");
        ckTglCari.setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);
        ckTglCari.setHorizontalTextPosition(javax.swing.SwingConstants.RIGHT);
        ckTglCari.setName("ckTglCari"); // NOI18N
        ckTglCari.setPreferredSize(new java.awt.Dimension(90, 23));
        ckTglCari.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                ckTglCariActionPerformed(evt);
            }
        });
        panelisi2.add(ckTglCari);

        DTPCari1.setEditable(false);
        DTPCari1.setDisplayFormat("dd-MM-yyyy");
        DTPCari1.setName("DTPCari1"); // NOI18N
        DTPCari1.setPreferredSize(new java.awt.Dimension(90, 23));
        DTPCari1.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                DTPCari1KeyPressed(evt);
            }
        });
        panelisi2.add(DTPCari1);

        label33.setForeground(new java.awt.Color(0, 0, 0));
        label33.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        label33.setText("S.D.");
        label33.setName("label33"); // NOI18N
        label33.setPreferredSize(new java.awt.Dimension(27, 23));
        panelisi2.add(label33);

        DTPCari2.setEditable(false);
        DTPCari2.setDisplayFormat("dd-MM-yyyy");
        DTPCari2.setName("DTPCari2"); // NOI18N
        DTPCari2.setPreferredSize(new java.awt.Dimension(90, 23));
        DTPCari2.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                DTPCari2KeyPressed(evt);
            }
        });
        panelisi2.add(DTPCari2);

        label9.setForeground(new java.awt.Color(0, 0, 0));
        label9.setText("Key Word :");
        label9.setName("label9"); // NOI18N
        label9.setPreferredSize(new java.awt.Dimension(70, 23));
        panelisi2.add(label9);

        TCari.setForeground(new java.awt.Color(0, 0, 0));
        TCari.setName("TCari"); // NOI18N
        TCari.setPreferredSize(new java.awt.Dimension(175, 23));
        TCari.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                TCariKeyPressed(evt);
            }
        });
        panelisi2.add(TCari);

        BtnCari.setForeground(new java.awt.Color(0, 0, 0));
        BtnCari.setIcon(new javax.swing.ImageIcon(getClass().getResource("/picture/accept.png"))); // NOI18N
        BtnCari.setMnemonic('1');
        BtnCari.setText("Tampilkan Data");
        BtnCari.setToolTipText("Alt+1");
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
        panelisi2.add(BtnCari);

        jLabel7.setForeground(new java.awt.Color(0, 0, 0));
        jLabel7.setText("Limit Data :");
        jLabel7.setName("jLabel7"); // NOI18N
        jLabel7.setPreferredSize(new java.awt.Dimension(70, 23));
        panelisi2.add(jLabel7);

        cmbHlm.setForeground(new java.awt.Color(0, 0, 0));
        cmbHlm.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "50", "100", "200", "300", "400", "500", "1000" }));
        cmbHlm.setName("cmbHlm"); // NOI18N
        cmbHlm.setOpaque(false);
        cmbHlm.setPreferredSize(new java.awt.Dimension(60, 23));
        panelisi2.add(cmbHlm);

        jPanel2.add(panelisi2, java.awt.BorderLayout.PAGE_START);

        panelisi1.setName("panelisi1"); // NOI18N
        panelisi1.setPreferredSize(new java.awt.Dimension(100, 44));
        panelisi1.setLayout(new java.awt.FlowLayout(java.awt.FlowLayout.LEFT, 5, 9));

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
        panelisi1.add(BtnSimpan);

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
        panelisi1.add(BtnBatal);

        BtnHapus.setForeground(new java.awt.Color(0, 0, 0));
        BtnHapus.setIcon(new javax.swing.ImageIcon(getClass().getResource("/picture/stop_f2.png"))); // NOI18N
        BtnHapus.setMnemonic('H');
        BtnHapus.setText("Hapus");
        BtnHapus.setToolTipText("Alt+H");
        BtnHapus.setName("BtnHapus"); // NOI18N
        BtnHapus.setPreferredSize(new java.awt.Dimension(100, 30));
        BtnHapus.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                BtnHapusActionPerformed1(evt);
            }
        });
        BtnHapus.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                BtnHapusKeyPressed(evt);
            }
        });
        panelisi1.add(BtnHapus);

        BtnEdit.setForeground(new java.awt.Color(0, 0, 0));
        BtnEdit.setIcon(new javax.swing.ImageIcon(getClass().getResource("/picture/inventaris.png"))); // NOI18N
        BtnEdit.setMnemonic('G');
        BtnEdit.setText("Ganti");
        BtnEdit.setToolTipText("Alt+G");
        BtnEdit.setName("BtnEdit"); // NOI18N
        BtnEdit.setPreferredSize(new java.awt.Dimension(100, 30));
        BtnEdit.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                BtnEditActionPerformed1(evt);
            }
        });
        BtnEdit.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                BtnEditKeyPressed(evt);
            }
        });
        panelisi1.add(BtnEdit);

        BtnPrint.setForeground(new java.awt.Color(0, 0, 0));
        BtnPrint.setIcon(new javax.swing.ImageIcon(getClass().getResource("/picture/b_print.png"))); // NOI18N
        BtnPrint.setMnemonic('T');
        BtnPrint.setText("Cetak");
        BtnPrint.setToolTipText("Alt+PT");
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
        panelisi1.add(BtnPrint);

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
        panelisi1.add(BtnAll);

        label10.setForeground(new java.awt.Color(0, 0, 0));
        label10.setText("Record :");
        label10.setName("label10"); // NOI18N
        label10.setPreferredSize(new java.awt.Dimension(70, 30));
        panelisi1.add(label10);

        LCount.setForeground(new java.awt.Color(0, 0, 0));
        LCount.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        LCount.setText("0");
        LCount.setName("LCount"); // NOI18N
        LCount.setPreferredSize(new java.awt.Dimension(72, 30));
        panelisi1.add(LCount);

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
        panelisi1.add(BtnKeluar);

        jPanel2.add(panelisi1, java.awt.BorderLayout.CENTER);

        internalFrame1.add(jPanel2, java.awt.BorderLayout.PAGE_END);

        scrollPane1.setComponentPopupMenu(Popup);
        scrollPane1.setName("scrollPane1"); // NOI18N
        scrollPane1.setOpaque(true);

        tbBayi.setAutoCreateRowSorter(true);
        tbBayi.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {},
                {},
                {},
                {}
            },
            new String [] {

            }
        ));
        tbBayi.setToolTipText("Silahkan klik untuk memilih data yang mau diedit ataupun dihapus");
        tbBayi.setComponentPopupMenu(Popup);
        tbBayi.setName("tbBayi"); // NOI18N
        tbBayi.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                tbBayiMouseClicked(evt);
            }
        });
        tbBayi.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                tbBayiKeyPressed(evt);
            }
        });
        scrollPane1.setViewportView(tbBayi);

        internalFrame1.add(scrollPane1, java.awt.BorderLayout.CENTER);

        PanelInput.setName("PanelInput"); // NOI18N
        PanelInput.setOpaque(false);
        PanelInput.setPreferredSize(new java.awt.Dimension(560, 370));
        PanelInput.setLayout(new java.awt.BorderLayout());

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

        FormInput.setName("FormInput"); // NOI18N
        FormInput.setPreferredSize(new java.awt.Dimension(560, 208));
        FormInput.setLayout(null);

        label12.setForeground(new java.awt.Color(0, 0, 0));
        label12.setText("No. RM Bayi :");
        label12.setName("label12"); // NOI18N
        label12.setPreferredSize(new java.awt.Dimension(65, 23));
        FormInput.add(label12);
        label12.setBounds(0, 12, 85, 23);

        NoRm.setEditable(false);
        NoRm.setForeground(new java.awt.Color(0, 0, 0));
        NoRm.setName("NoRm"); // NOI18N
        NoRm.setPreferredSize(new java.awt.Dimension(207, 23));
        NoRm.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                NoRmKeyPressed(evt);
            }
        });
        FormInput.add(NoRm);
        NoRm.setBounds(89, 12, 80, 23);

        label18.setForeground(new java.awt.Color(0, 0, 0));
        label18.setText("No. RM. Ibu : ");
        label18.setName("label18"); // NOI18N
        label18.setPreferredSize(new java.awt.Dimension(65, 23));
        FormInput.add(label18);
        label18.setBounds(0, 42, 85, 23);

        label22.setForeground(new java.awt.Color(0, 0, 0));
        label22.setText("J.K.Bayi :");
        label22.setName("label22"); // NOI18N
        label22.setPreferredSize(new java.awt.Dimension(65, 23));
        FormInput.add(label22);
        label22.setBounds(0, 162, 85, 23);

        label24.setForeground(new java.awt.Color(0, 0, 0));
        label24.setText("Panjang Badan :");
        label24.setName("label24"); // NOI18N
        label24.setPreferredSize(new java.awt.Dimension(65, 23));
        FormInput.add(label24);
        label24.setBounds(475, 12, 98, 23);

        Proses.setForeground(new java.awt.Color(0, 0, 0));
        Proses.setName("Proses"); // NOI18N
        Proses.setPreferredSize(new java.awt.Dimension(207, 23));
        Proses.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                ProsesKeyPressed(evt);
            }
        });
        FormInput.add(Proses);
        Proses.setBounds(577, 222, 300, 23);

        Anakke.setForeground(new java.awt.Color(0, 0, 0));
        Anakke.setName("Anakke"); // NOI18N
        Anakke.setPreferredSize(new java.awt.Dimension(207, 23));
        Anakke.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                AnakkeKeyPressed(evt);
            }
        });
        FormInput.add(Anakke);
        Anakke.setBounds(767, 72, 95, 23);

        label25.setForeground(new java.awt.Color(0, 0, 0));
        label25.setText("Kelahiran Ke :");
        label25.setName("label25"); // NOI18N
        label25.setPreferredSize(new java.awt.Dimension(65, 23));
        FormInput.add(label25);
        label25.setBounds(679, 72, 83, 23);

        LingkarKepala.setForeground(new java.awt.Color(0, 0, 0));
        LingkarKepala.setName("LingkarKepala"); // NOI18N
        LingkarKepala.setPreferredSize(new java.awt.Dimension(207, 23));
        LingkarKepala.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                LingkarKepalaKeyPressed(evt);
            }
        });
        FormInput.add(LingkarKepala);
        LingkarKepala.setBounds(767, 12, 95, 23);

        label27.setForeground(new java.awt.Color(0, 0, 0));
        label27.setText("Diagnosa Resume :");
        label27.setName("label27"); // NOI18N
        label27.setPreferredSize(new java.awt.Dimension(65, 23));
        FormInput.add(label27);
        label27.setBounds(475, 102, 98, 23);

        label28.setForeground(new java.awt.Color(0, 0, 0));
        label28.setText("Tgl. Daftar :");
        label28.setName("label28"); // NOI18N
        label28.setPreferredSize(new java.awt.Dimension(65, 23));
        FormInput.add(label28);
        label28.setBounds(475, 72, 98, 23);

        JKel.setBackground(new java.awt.Color(248, 253, 243));
        JKel.setForeground(new java.awt.Color(0, 0, 0));
        JKel.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "LAKI-LAKI", "PEREMPUAN" }));
        JKel.setName("JKel"); // NOI18N
        JKel.setPreferredSize(new java.awt.Dimension(100, 23));
        JKel.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                JKelKeyPressed(evt);
            }
        });
        FormInput.add(JKel);
        JKel.setBounds(89, 162, 100, 23);

        label23.setForeground(new java.awt.Color(0, 0, 0));
        label23.setText("Berat Bayi (gram) :");
        label23.setName("label23"); // NOI18N
        label23.setPreferredSize(new java.awt.Dimension(65, 23));
        FormInput.add(label23);
        label23.setBounds(220, 132, 100, 23);

        label30.setForeground(new java.awt.Color(0, 0, 0));
        label30.setText("Tgl. Lahir :");
        label30.setName("label30"); // NOI18N
        label30.setPreferredSize(new java.awt.Dimension(65, 23));
        FormInput.add(label30);
        label30.setBounds(0, 192, 85, 23);

        Lahir.setEditable(false);
        Lahir.setDisplayFormat("dd-MM-yyyy");
        Lahir.setName("Lahir"); // NOI18N
        Lahir.setVerifyInputWhenFocusTarget(false);
        Lahir.addItemListener(new java.awt.event.ItemListener() {
            public void itemStateChanged(java.awt.event.ItemEvent evt) {
                LahirItemStateChanged(evt);
            }
        });
        Lahir.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                LahirKeyPressed(evt);
            }
        });
        FormInput.add(Lahir);
        Lahir.setBounds(89, 192, 100, 23);

        label31.setForeground(new java.awt.Color(0, 0, 0));
        label31.setText("Diagnosa Awal :");
        label31.setName("label31"); // NOI18N
        label31.setPreferredSize(new java.awt.Dimension(65, 23));
        FormInput.add(label31);
        label31.setBounds(475, 162, 98, 23);

        Diagnosa.setForeground(new java.awt.Color(0, 0, 0));
        Diagnosa.setName("Diagnosa"); // NOI18N
        Diagnosa.setPreferredSize(new java.awt.Dimension(207, 23));
        Diagnosa.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                DiagnosaKeyPressed(evt);
            }
        });
        FormInput.add(Diagnosa);
        Diagnosa.setBounds(577, 162, 120, 23);

        Nmibu.setEditable(false);
        Nmibu.setForeground(new java.awt.Color(0, 0, 0));
        Nmibu.setName("Nmibu"); // NOI18N
        Nmibu.setPreferredSize(new java.awt.Dimension(207, 23));
        Nmibu.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                NmibuKeyPressed(evt);
            }
        });
        FormInput.add(Nmibu);
        Nmibu.setBounds(172, 42, 258, 23);

        label26.setForeground(new java.awt.Color(0, 0, 0));
        label26.setText("Lingkar Kepala :");
        label26.setName("label26"); // NOI18N
        label26.setPreferredSize(new java.awt.Dimension(65, 23));
        FormInput.add(label26);
        label26.setBounds(679, 12, 83, 23);

        label19.setForeground(new java.awt.Color(0, 0, 0));
        label19.setText("Nama Ayah :");
        label19.setName("label19"); // NOI18N
        label19.setPreferredSize(new java.awt.Dimension(65, 23));
        FormInput.add(label19);
        label19.setBounds(0, 102, 85, 23);

        NmAyah.setForeground(new java.awt.Color(0, 0, 0));
        NmAyah.setName("NmAyah"); // NOI18N
        NmAyah.setPreferredSize(new java.awt.Dimension(207, 23));
        NmAyah.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                NmAyahKeyPressed(evt);
            }
        });
        FormInput.add(NmAyah);
        NmAyah.setBounds(89, 102, 230, 23);

        label20.setForeground(new java.awt.Color(0, 0, 0));
        label20.setText("Umur Ibu :");
        label20.setName("label20"); // NOI18N
        label20.setPreferredSize(new java.awt.Dimension(65, 23));
        FormInput.add(label20);
        label20.setBounds(322, 72, 70, 23);

        UmurIbu.setEditable(false);
        UmurIbu.setForeground(new java.awt.Color(0, 0, 0));
        UmurIbu.setName("UmurIbu"); // NOI18N
        UmurIbu.setPreferredSize(new java.awt.Dimension(207, 23));
        UmurIbu.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                UmurIbuKeyPressed(evt);
            }
        });
        FormInput.add(UmurIbu);
        UmurIbu.setBounds(395, 72, 70, 23);

        label21.setForeground(new java.awt.Color(0, 0, 0));
        label21.setText("Alamat Ibu :");
        label21.setName("label21"); // NOI18N
        label21.setPreferredSize(new java.awt.Dimension(65, 23));
        FormInput.add(label21);
        label21.setBounds(0, 72, 85, 23);

        AlamatIbu.setEditable(false);
        AlamatIbu.setForeground(new java.awt.Color(0, 0, 0));
        AlamatIbu.setName("AlamatIbu"); // NOI18N
        AlamatIbu.setPreferredSize(new java.awt.Dimension(207, 23));
        AlamatIbu.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                AlamatIbuKeyPressed(evt);
            }
        });
        FormInput.add(AlamatIbu);
        AlamatIbu.setBounds(89, 72, 230, 23);

        label32.setForeground(new java.awt.Color(0, 0, 0));
        label32.setText("Jam Lahir Bayi :");
        label32.setName("label32"); // NOI18N
        label32.setPreferredSize(new java.awt.Dimension(65, 23));
        FormInput.add(label32);
        label32.setBounds(212, 162, 90, 23);

        jam.setForeground(new java.awt.Color(0, 0, 0));
        jam.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "00", "01", "02", "03", "04", "05", "06", "07", "08", "09", "10", "11", "12", "13", "14", "15", "16", "17", "18", "19", "20", "21", "22", "23" }));
        jam.setName("jam"); // NOI18N
        jam.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                jamKeyPressed(evt);
            }
        });
        FormInput.add(jam);
        jam.setBounds(305, 162, 50, 23);

        menit.setForeground(new java.awt.Color(0, 0, 0));
        menit.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "00", "01", "02", "03", "04", "05", "06", "07", "08", "09", "10", "11", "12", "13", "14", "15", "16", "17", "18", "19", "20", "21", "22", "23", "24", "25", "26", "27", "28", "29", "30", "31", "32", "33", "34", "35", "36", "37", "38", "39", "40", "41", "42", "43", "44", "45", "46", "47", "48", "49", "50", "51", "52", "53", "54", "55", "56", "57", "58", "59" }));
        menit.setName("menit"); // NOI18N
        menit.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                menitKeyPressed(evt);
            }
        });
        FormInput.add(menit);
        menit.setBounds(360, 162, 50, 23);

        detik.setForeground(new java.awt.Color(0, 0, 0));
        detik.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "00", "01", "02", "03", "04", "05", "06", "07", "08", "09", "10", "11", "12", "13", "14", "15", "16", "17", "18", "19", "20", "21", "22", "23", "24", "25", "26", "27", "28", "29", "30", "31", "32", "33", "34", "35", "36", "37", "38", "39", "40", "41", "42", "43", "44", "45", "46", "47", "48", "49", "50", "51", "52", "53", "54", "55", "56", "57", "58", "59" }));
        detik.setName("detik"); // NOI18N
        detik.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                detikKeyPressed(evt);
            }
        });
        FormInput.add(detik);
        detik.setBounds(415, 162, 50, 23);

        label34.setForeground(new java.awt.Color(0, 0, 0));
        label34.setText("Asal Bayi :");
        label34.setName("label34"); // NOI18N
        label34.setPreferredSize(new java.awt.Dimension(65, 23));
        FormInput.add(label34);
        label34.setBounds(640, 252, 60, 23);

        Panjang.setForeground(new java.awt.Color(0, 0, 0));
        Panjang.setName("Panjang"); // NOI18N
        Panjang.setPreferredSize(new java.awt.Dimension(207, 23));
        Panjang.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                PanjangKeyPressed(evt);
            }
        });
        FormInput.add(Panjang);
        Panjang.setBounds(577, 12, 95, 23);

        Daftar.setEditable(false);
        Daftar.setDisplayFormat("dd-MM-yyyy");
        Daftar.setName("Daftar"); // NOI18N
        Daftar.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                DaftarKeyPressed(evt);
            }
        });
        FormInput.add(Daftar);
        Daftar.setBounds(577, 72, 95, 23);

        scrollPane2.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));
        scrollPane2.setName("scrollPane2"); // NOI18N

        keterangan.setColumns(20);
        keterangan.setRows(5);
        keterangan.setName("keterangan"); // NOI18N
        keterangan.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                keteranganKeyPressed(evt);
            }
        });
        scrollPane2.setViewportView(keterangan);

        FormInput.add(scrollPane2);
        scrollPane2.setBounds(577, 102, 300, 52);

        NmBayi.setEditable(false);
        NmBayi.setForeground(new java.awt.Color(0, 0, 0));
        NmBayi.setName("NmBayi"); // NOI18N
        NmBayi.setPreferredSize(new java.awt.Dimension(207, 23));
        NmBayi.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                NmBayiKeyPressed(evt);
            }
        });
        FormInput.add(NmBayi);
        NmBayi.setBounds(172, 12, 258, 23);

        label35.setForeground(new java.awt.Color(0, 0, 0));
        label35.setText("Umur Ayah :");
        label35.setName("label35"); // NOI18N
        label35.setPreferredSize(new java.awt.Dimension(65, 23));
        FormInput.add(label35);
        label35.setBounds(322, 102, 70, 23);

        UmurAyah.setForeground(new java.awt.Color(0, 0, 0));
        UmurAyah.setName("UmurAyah"); // NOI18N
        UmurAyah.setPreferredSize(new java.awt.Dimension(207, 23));
        UmurAyah.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                UmurAyahKeyPressed(evt);
            }
        });
        FormInput.add(UmurAyah);
        UmurAyah.setBounds(395, 102, 70, 23);

        label36.setForeground(new java.awt.Color(0, 0, 0));
        label36.setText("Umur Bayi :");
        label36.setName("label36"); // NOI18N
        label36.setPreferredSize(new java.awt.Dimension(65, 23));
        FormInput.add(label36);
        label36.setBounds(475, 42, 98, 23);

        UmurBayi.setEditable(false);
        UmurBayi.setForeground(new java.awt.Color(0, 0, 0));
        UmurBayi.setName("UmurBayi"); // NOI18N
        UmurBayi.setPreferredSize(new java.awt.Dimension(207, 23));
        UmurBayi.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                UmurBayiKeyPressed(evt);
            }
        });
        FormInput.add(UmurBayi);
        UmurBayi.setBounds(577, 42, 95, 23);

        label37.setForeground(new java.awt.Color(0, 0, 0));
        label37.setText("Penyulit Kehamilan :");
        label37.setName("label37"); // NOI18N
        label37.setPreferredSize(new java.awt.Dimension(65, 23));
        FormInput.add(label37);
        label37.setBounds(473, 192, 100, 23);

        PenyulitKehamilan.setForeground(new java.awt.Color(0, 0, 0));
        PenyulitKehamilan.setName("PenyulitKehamilan"); // NOI18N
        PenyulitKehamilan.setPreferredSize(new java.awt.Dimension(207, 23));
        PenyulitKehamilan.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                PenyulitKehamilanKeyPressed(evt);
            }
        });
        FormInput.add(PenyulitKehamilan);
        PenyulitKehamilan.setBounds(577, 192, 300, 23);

        label38.setForeground(new java.awt.Color(0, 0, 0));
        label38.setText("Ketuban :");
        label38.setName("label38"); // NOI18N
        label38.setPreferredSize(new java.awt.Dimension(65, 23));
        FormInput.add(label38);
        label38.setBounds(697, 162, 57, 23);

        Ketuban.setForeground(new java.awt.Color(0, 0, 0));
        Ketuban.setName("Ketuban"); // NOI18N
        Ketuban.setPreferredSize(new java.awt.Dimension(207, 23));
        Ketuban.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                KetubanKeyPressed(evt);
            }
        });
        FormInput.add(Ketuban);
        Ketuban.setBounds(757, 162, 120, 23);

        label39.setForeground(new java.awt.Color(0, 0, 0));
        label39.setText("Lingkar Dada :");
        label39.setName("label39"); // NOI18N
        label39.setPreferredSize(new java.awt.Dimension(65, 23));
        FormInput.add(label39);
        label39.setBounds(679, 42, 83, 23);

        LingkarDada.setForeground(new java.awt.Color(0, 0, 0));
        LingkarDada.setName("LingkarDada"); // NOI18N
        LingkarDada.setPreferredSize(new java.awt.Dimension(207, 23));
        LingkarDada.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                LingkarDadaKeyPressed(evt);
            }
        });
        FormInput.add(LingkarDada);
        LingkarDada.setBounds(767, 42, 95, 23);

        LingkarPerut.setForeground(new java.awt.Color(0, 0, 0));
        LingkarPerut.setName("LingkarPerut"); // NOI18N
        LingkarPerut.setPreferredSize(new java.awt.Dimension(207, 23));
        LingkarPerut.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                LingkarPerutKeyPressed(evt);
            }
        });
        FormInput.add(LingkarPerut);
        LingkarPerut.setBounds(370, 192, 95, 23);

        label40.setForeground(new java.awt.Color(0, 0, 0));
        label40.setText("Lingkar Perut :");
        label40.setName("label40"); // NOI18N
        label40.setPreferredSize(new java.awt.Dimension(65, 23));
        FormInput.add(label40);
        label40.setBounds(280, 192, 85, 23);

        label41.setForeground(new java.awt.Color(0, 0, 0));
        label41.setText("Jenis Alamat :");
        label41.setName("label41"); // NOI18N
        label41.setPreferredSize(new java.awt.Dimension(65, 23));
        FormInput.add(label41);
        label41.setBounds(0, 252, 85, 23);

        NoSKL.setForeground(new java.awt.Color(0, 0, 0));
        NoSKL.setName("NoSKL"); // NOI18N
        NoSKL.setPreferredSize(new java.awt.Dimension(207, 23));
        NoSKL.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                NoSKLKeyPressed(evt);
            }
        });
        FormInput.add(NoSKL);
        NoSKL.setBounds(90, 222, 160, 23);

        jLabel24.setForeground(new java.awt.Color(0, 0, 0));
        jLabel24.setText("Penolong :");
        jLabel24.setName("jLabel24"); // NOI18N
        FormInput.add(jLabel24);
        jLabel24.setBounds(475, 282, 98, 23);

        KdPenolong.setEditable(false);
        KdPenolong.setForeground(new java.awt.Color(0, 0, 0));
        KdPenolong.setHighlighter(null);
        KdPenolong.setName("KdPenolong"); // NOI18N
        KdPenolong.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                KdPenolongKeyPressed(evt);
            }
        });
        FormInput.add(KdPenolong);
        KdPenolong.setBounds(577, 282, 100, 23);

        NmPenolong.setEditable(false);
        NmPenolong.setForeground(new java.awt.Color(0, 0, 0));
        NmPenolong.setName("NmPenolong"); // NOI18N
        FormInput.add(NmPenolong);
        NmPenolong.setBounds(680, 282, 241, 23);

        BtnPenjab.setForeground(new java.awt.Color(0, 0, 0));
        BtnPenjab.setIcon(new javax.swing.ImageIcon(getClass().getResource("/picture/190.png"))); // NOI18N
        BtnPenjab.setMnemonic('1');
        BtnPenjab.setToolTipText("ALt+1");
        BtnPenjab.setName("BtnPenjab"); // NOI18N
        BtnPenjab.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                BtnPenjabActionPerformed(evt);
            }
        });
        FormInput.add(BtnPenjab);
        BtnPenjab.setBounds(925, 282, 28, 23);

        btnPasien.setForeground(new java.awt.Color(0, 0, 0));
        btnPasien.setIcon(new javax.swing.ImageIcon(getClass().getResource("/picture/190.png"))); // NOI18N
        btnPasien.setMnemonic('1');
        btnPasien.setToolTipText("Alt+1");
        btnPasien.setName("btnPasien"); // NOI18N
        btnPasien.setPreferredSize(new java.awt.Dimension(28, 23));
        btnPasien.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnPasienActionPerformed(evt);
            }
        });
        FormInput.add(btnPasien);
        btnPasien.setBounds(437, 10, 28, 23);

        cmbBerat.setBackground(new java.awt.Color(248, 253, 243));
        cmbBerat.setForeground(new java.awt.Color(0, 0, 0));
        cmbBerat.setModel(new javax.swing.DefaultComboBoxModel(new String[] { " ", ">3.000", ">2.500 s.d >=3.000", "<2.500", "0 - 999", "1.000 - 1.499", "1.500 - 2.499", "2.500 - 4.000", ">=4.001" }));
        cmbBerat.setEnabled(false);
        cmbBerat.setName("cmbBerat"); // NOI18N
        cmbBerat.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                cmbBeratKeyPressed(evt);
            }
        });
        FormInput.add(cmbBerat);
        cmbBerat.setBounds(325, 132, 130, 23);

        cmbMatiPerin.setForeground(new java.awt.Color(0, 0, 0));
        cmbMatiPerin.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "-", "Kelahiran mati", "Mati neonatal < 7 hari" }));
        cmbMatiPerin.setName("cmbMatiPerin"); // NOI18N
        cmbMatiPerin.setPreferredSize(new java.awt.Dimension(100, 23));
        cmbMatiPerin.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                cmbMatiPerinKeyPressed(evt);
            }
        });
        FormInput.add(cmbMatiPerin);
        cmbMatiPerin.setBounds(995, 12, 140, 23);

        cmbSebabMati.setForeground(new java.awt.Color(0, 0, 0));
        cmbSebabMati.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "-", "Asphyxia", "Trauma Lahir", "BBLR", "Tetanus Neonatum", "Kelainan Congenital", "ISPA", "Diare", "Lain-lain" }));
        cmbSebabMati.setName("cmbSebabMati"); // NOI18N
        cmbSebabMati.setPreferredSize(new java.awt.Dimension(100, 23));
        cmbSebabMati.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                cmbSebabMatiKeyPressed(evt);
            }
        });
        FormInput.add(cmbSebabMati);
        cmbSebabMati.setBounds(995, 42, 140, 23);

        cmbRujukan.setForeground(new java.awt.Color(0, 0, 0));
        cmbRujukan.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "Rumah Sakit", "Bidan", "Non Medis", "Puskesmas", "Faskes lainnya", "Non Rujukan" }));
        cmbRujukan.setSelectedIndex(5);
        cmbRujukan.setName("cmbRujukan"); // NOI18N
        cmbRujukan.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                cmbRujukanKeyPressed(evt);
            }
        });
        FormInput.add(cmbRujukan);
        cmbRujukan.setBounds(995, 72, 130, 23);

        cmbDirujuk.setForeground(new java.awt.Color(0, 0, 0));
        cmbDirujuk.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "Tidak", "Ya" }));
        cmbDirujuk.setName("cmbDirujuk"); // NOI18N
        cmbDirujuk.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                cmbDirujukKeyPressed(evt);
            }
        });
        FormInput.add(cmbDirujuk);
        cmbDirujuk.setBounds(995, 102, 70, 23);

        label42.setForeground(new java.awt.Color(0, 0, 0));
        label42.setText("Dirujuk :");
        label42.setName("label42"); // NOI18N
        label42.setPreferredSize(new java.awt.Dimension(65, 23));
        FormInput.add(label42);
        label42.setBounds(885, 102, 105, 23);

        label43.setForeground(new java.awt.Color(0, 0, 0));
        label43.setText("Kematian Perinatal :");
        label43.setName("label43"); // NOI18N
        label43.setPreferredSize(new java.awt.Dimension(65, 23));
        FormInput.add(label43);
        label43.setBounds(885, 12, 105, 23);

        label44.setForeground(new java.awt.Color(0, 0, 0));
        label44.setText("Sebab Kematian :");
        label44.setName("label44"); // NOI18N
        label44.setPreferredSize(new java.awt.Dimension(65, 23));
        FormInput.add(label44);
        label44.setBounds(885, 42, 105, 23);

        label45.setForeground(new java.awt.Color(0, 0, 0));
        label45.setText("Asal Rujukan :");
        label45.setName("label45"); // NOI18N
        label45.setPreferredSize(new java.awt.Dimension(65, 23));
        FormInput.add(label45);
        label45.setBounds(885, 72, 105, 23);

        label46.setForeground(new java.awt.Color(0, 0, 0));
        label46.setText("No. SKL :");
        label46.setName("label46"); // NOI18N
        label46.setPreferredSize(new java.awt.Dimension(65, 23));
        FormInput.add(label46);
        label46.setBounds(0, 222, 85, 23);

        jns_alamat.setBackground(new java.awt.Color(248, 253, 243));
        jns_alamat.setForeground(new java.awt.Color(0, 0, 0));
        jns_alamat.setModel(new javax.swing.DefaultComboBoxModel(new String[] { " ", "Dalam Wilayah Kab. Banjar", "Luar Wilayah" }));
        jns_alamat.setName("jns_alamat"); // NOI18N
        jns_alamat.setPreferredSize(new java.awt.Dimension(100, 23));
        jns_alamat.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jns_alamatMouseClicked(evt);
            }
        });
        jns_alamat.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                jns_alamatKeyPressed(evt);
            }
        });
        FormInput.add(jns_alamat);
        jns_alamat.setBounds(89, 252, 160, 23);

        label47.setForeground(new java.awt.Color(0, 0, 0));
        label47.setText("Cara Lahir :");
        label47.setName("label47"); // NOI18N
        label47.setPreferredSize(new java.awt.Dimension(65, 23));
        FormInput.add(label47);
        label47.setBounds(0, 282, 85, 23);

        cara_lahir.setBackground(new java.awt.Color(248, 253, 243));
        cara_lahir.setForeground(new java.awt.Color(0, 0, 0));
        cara_lahir.setModel(new javax.swing.DefaultComboBoxModel(new String[] { " ", "Spontan", "Letak Sungsang", "VE/Forcep", "SC" }));
        cara_lahir.setName("cara_lahir"); // NOI18N
        cara_lahir.setPreferredSize(new java.awt.Dimension(100, 23));
        cara_lahir.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                cara_lahirMouseClicked(evt);
            }
        });
        cara_lahir.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                cara_lahirKeyPressed(evt);
            }
        });
        FormInput.add(cara_lahir);
        cara_lahir.setBounds(89, 282, 130, 23);

        label48.setForeground(new java.awt.Color(0, 0, 0));
        label48.setText("Jenis Penolong :");
        label48.setName("label48"); // NOI18N
        label48.setPreferredSize(new java.awt.Dimension(65, 23));
        FormInput.add(label48);
        label48.setBounds(0, 312, 85, 23);

        jns_penolong.setBackground(new java.awt.Color(248, 253, 243));
        jns_penolong.setForeground(new java.awt.Color(0, 0, 0));
        jns_penolong.setModel(new javax.swing.DefaultComboBoxModel(new String[] { " ", "Bidan", "Dokter Spesialis" }));
        jns_penolong.setName("jns_penolong"); // NOI18N
        jns_penolong.setPreferredSize(new java.awt.Dimension(100, 23));
        jns_penolong.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jns_penolongMouseClicked(evt);
            }
        });
        jns_penolong.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                jns_penolongKeyPressed(evt);
            }
        });
        FormInput.add(jns_penolong);
        jns_penolong.setBounds(89, 312, 110, 23);

        label49.setForeground(new java.awt.Color(0, 0, 0));
        label49.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        label49.setText("- Menit 10");
        label49.setName("label49"); // NOI18N
        label49.setPreferredSize(new java.awt.Dimension(65, 23));
        FormInput.add(label49);
        label49.setBounds(342, 282, 50, 23);

        label50.setForeground(new java.awt.Color(0, 0, 0));
        label50.setText("Apgus Score :");
        label50.setName("label50"); // NOI18N
        label50.setPreferredSize(new java.awt.Dimension(65, 23));
        FormInput.add(label50);
        label50.setBounds(268, 222, 70, 23);

        label51.setForeground(new java.awt.Color(0, 0, 0));
        label51.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        label51.setText("- Menit 1");
        label51.setName("label51"); // NOI18N
        label51.setPreferredSize(new java.awt.Dimension(65, 23));
        FormInput.add(label51);
        label51.setBounds(342, 222, 50, 23);

        label52.setForeground(new java.awt.Color(0, 0, 0));
        label52.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        label52.setText("- Menit 5");
        label52.setName("label52"); // NOI18N
        label52.setPreferredSize(new java.awt.Dimension(65, 23));
        FormInput.add(label52);
        label52.setBounds(342, 252, 50, 23);

        menit1.setBackground(new java.awt.Color(248, 253, 243));
        menit1.setForeground(new java.awt.Color(0, 0, 0));
        menit1.setModel(new javax.swing.DefaultComboBoxModel(new String[] { " ", "0-3", "4-7", "8-10" }));
        menit1.setName("menit1"); // NOI18N
        menit1.setPreferredSize(new java.awt.Dimension(100, 23));
        menit1.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                menit1MouseClicked(evt);
            }
        });
        menit1.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                menit1KeyPressed(evt);
            }
        });
        FormInput.add(menit1);
        menit1.setBounds(395, 222, 60, 23);

        menit5.setBackground(new java.awt.Color(248, 253, 243));
        menit5.setForeground(new java.awt.Color(0, 0, 0));
        menit5.setModel(new javax.swing.DefaultComboBoxModel(new String[] { " ", "0-3", "4-7", "8-10" }));
        menit5.setName("menit5"); // NOI18N
        menit5.setPreferredSize(new java.awt.Dimension(100, 23));
        menit5.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                menit5MouseClicked(evt);
            }
        });
        menit5.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                menit5KeyPressed(evt);
            }
        });
        FormInput.add(menit5);
        menit5.setBounds(395, 252, 60, 23);

        menit10.setBackground(new java.awt.Color(248, 253, 243));
        menit10.setForeground(new java.awt.Color(0, 0, 0));
        menit10.setModel(new javax.swing.DefaultComboBoxModel(new String[] { " ", "0-3", "4-7", "8-10" }));
        menit10.setName("menit10"); // NOI18N
        menit10.setPreferredSize(new java.awt.Dimension(100, 23));
        menit10.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                menit10MouseClicked(evt);
            }
        });
        menit10.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                menit10KeyPressed(evt);
            }
        });
        FormInput.add(menit10);
        menit10.setBounds(395, 282, 60, 23);

        label53.setForeground(new java.awt.Color(0, 0, 0));
        label53.setText("Proses Kelahiran :");
        label53.setName("label53"); // NOI18N
        label53.setPreferredSize(new java.awt.Dimension(65, 23));
        FormInput.add(label53);
        label53.setBounds(475, 222, 98, 23);

        umur_hamil.setBackground(new java.awt.Color(248, 253, 243));
        umur_hamil.setForeground(new java.awt.Color(0, 0, 0));
        umur_hamil.setModel(new javax.swing.DefaultComboBoxModel(new String[] { " ", "<=35", "36-40", ">=42" }));
        umur_hamil.setName("umur_hamil"); // NOI18N
        umur_hamil.setPreferredSize(new java.awt.Dimension(100, 23));
        umur_hamil.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                umur_hamilMouseClicked(evt);
            }
        });
        umur_hamil.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                umur_hamilKeyPressed(evt);
            }
        });
        FormInput.add(umur_hamil);
        umur_hamil.setBounds(577, 252, 60, 23);

        label54.setForeground(new java.awt.Color(0, 0, 0));
        label54.setText("Umur Kehamilan :");
        label54.setName("label54"); // NOI18N
        label54.setPreferredSize(new java.awt.Dimension(65, 23));
        FormInput.add(label54);
        label54.setBounds(475, 252, 98, 23);

        cmbAsalBayi.setBackground(new java.awt.Color(248, 253, 243));
        cmbAsalBayi.setForeground(new java.awt.Color(0, 0, 0));
        cmbAsalBayi.setModel(new javax.swing.DefaultComboBoxModel(new String[] { " ", "Dalam RS", "Luar RS" }));
        cmbAsalBayi.setName("cmbAsalBayi"); // NOI18N
        cmbAsalBayi.setPreferredSize(new java.awt.Dimension(100, 23));
        cmbAsalBayi.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                cmbAsalBayiMouseClicked(evt);
            }
        });
        cmbAsalBayi.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                cmbAsalBayiKeyPressed(evt);
            }
        });
        FormInput.add(cmbAsalBayi);
        cmbAsalBayi.setBounds(705, 252, 80, 23);

        label55.setForeground(new java.awt.Color(0, 0, 0));
        label55.setText("Rwt. Gabung Ibu :");
        label55.setName("label55"); // NOI18N
        label55.setPreferredSize(new java.awt.Dimension(65, 23));
        FormInput.add(label55);
        label55.setBounds(885, 132, 105, 23);

        cmbRawat.setBackground(new java.awt.Color(248, 253, 243));
        cmbRawat.setForeground(new java.awt.Color(0, 0, 0));
        cmbRawat.setModel(new javax.swing.DefaultComboBoxModel(new String[] { " ", "Ya", "Tidak" }));
        cmbRawat.setName("cmbRawat"); // NOI18N
        cmbRawat.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                cmbRawatMouseClicked(evt);
            }
        });
        cmbRawat.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                cmbRawatKeyPressed(evt);
            }
        });
        FormInput.add(cmbRawat);
        cmbRawat.setBounds(995, 132, 70, 23);

        label56.setForeground(new java.awt.Color(0, 0, 0));
        label56.setText("IMD :");
        label56.setName("label56"); // NOI18N
        label56.setPreferredSize(new java.awt.Dimension(65, 23));
        FormInput.add(label56);
        label56.setBounds(885, 162, 105, 23);

        cmbIMD.setBackground(new java.awt.Color(248, 253, 243));
        cmbIMD.setForeground(new java.awt.Color(0, 0, 0));
        cmbIMD.setModel(new javax.swing.DefaultComboBoxModel(new String[] { " ", "Ya", "Tidak" }));
        cmbIMD.setName("cmbIMD"); // NOI18N
        cmbIMD.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                cmbIMDMouseClicked(evt);
            }
        });
        cmbIMD.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                cmbIMDKeyPressed(evt);
            }
        });
        FormInput.add(cmbIMD);
        cmbIMD.setBounds(995, 162, 70, 23);

        label57.setForeground(new java.awt.Color(0, 0, 0));
        label57.setText("KMC :");
        label57.setName("label57"); // NOI18N
        label57.setPreferredSize(new java.awt.Dimension(65, 23));
        FormInput.add(label57);
        label57.setBounds(885, 192, 105, 23);

        cmbKMC.setBackground(new java.awt.Color(248, 253, 243));
        cmbKMC.setForeground(new java.awt.Color(0, 0, 0));
        cmbKMC.setModel(new javax.swing.DefaultComboBoxModel(new String[] { " ", "Ya", "Tidak" }));
        cmbKMC.setName("cmbKMC"); // NOI18N
        cmbKMC.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                cmbKMCMouseClicked(evt);
            }
        });
        cmbKMC.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                cmbKMCKeyPressed(evt);
            }
        });
        FormInput.add(cmbKMC);
        cmbKMC.setBounds(995, 192, 70, 23);

        label58.setForeground(new java.awt.Color(0, 0, 0));
        label58.setText("Berat Bayi Sebenarnya :");
        label58.setName("label58"); // NOI18N
        label58.setPreferredSize(new java.awt.Dimension(65, 23));
        FormInput.add(label58);
        label58.setBounds(0, 132, 130, 23);

        benarberat.setForeground(new java.awt.Color(0, 0, 0));
        benarberat.setName("benarberat"); // NOI18N
        benarberat.setPreferredSize(new java.awt.Dimension(207, 23));
        benarberat.addFocusListener(new java.awt.event.FocusAdapter() {
            public void focusLost(java.awt.event.FocusEvent evt) {
                benarberatFocusLost(evt);
            }
        });
        benarberat.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                benarberatKeyPressed(evt);
            }
        });
        FormInput.add(benarberat);
        benarberat.setBounds(133, 132, 60, 23);

        label59.setForeground(new java.awt.Color(0, 0, 0));
        label59.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        label59.setText("gr.");
        label59.setName("label59"); // NOI18N
        label59.setPreferredSize(new java.awt.Dimension(65, 23));
        FormInput.add(label59);
        label59.setBounds(198, 132, 20, 23);

        NoRmIbu.setEditable(false);
        NoRmIbu.setForeground(new java.awt.Color(0, 0, 0));
        NoRmIbu.setName("NoRmIbu"); // NOI18N
        NoRmIbu.setPreferredSize(new java.awt.Dimension(207, 23));
        NoRmIbu.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                NoRmIbuKeyPressed(evt);
            }
        });
        FormInput.add(NoRmIbu);
        NoRmIbu.setBounds(89, 42, 80, 23);

        btnPasienIbu.setForeground(new java.awt.Color(0, 0, 0));
        btnPasienIbu.setIcon(new javax.swing.ImageIcon(getClass().getResource("/picture/190.png"))); // NOI18N
        btnPasienIbu.setMnemonic('1');
        btnPasienIbu.setToolTipText("Alt+1");
        btnPasienIbu.setName("btnPasienIbu"); // NOI18N
        btnPasienIbu.setPreferredSize(new java.awt.Dimension(28, 23));
        btnPasienIbu.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnPasienIbuActionPerformed(evt);
            }
        });
        FormInput.add(btnPasienIbu);
        btnPasienIbu.setBounds(437, 42, 28, 23);

        PanelInput.add(FormInput, java.awt.BorderLayout.CENTER);

        internalFrame1.add(PanelInput, java.awt.BorderLayout.PAGE_START);

        getContentPane().add(internalFrame1, java.awt.BorderLayout.CENTER);

        pack();
    }// </editor-fold>//GEN-END:initComponents

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

    private void tbBayiMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tbBayiMouseClicked
        if (tabMode.getRowCount() != 0) {
            try {
                getData();
            } catch (java.lang.NullPointerException e) {
            }
        }
}//GEN-LAST:event_tbBayiMouseClicked

    private void tbBayiKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_tbBayiKeyPressed
        if (tabMode.getRowCount() != 0) {
            if ((evt.getKeyCode() == KeyEvent.VK_ENTER) || (evt.getKeyCode() == KeyEvent.VK_UP) || (evt.getKeyCode() == KeyEvent.VK_DOWN)) {
                try {
                    getData();
                } catch (java.lang.NullPointerException e) {
                }
            }
        }
}//GEN-LAST:event_tbBayiKeyPressed

    private void KdKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_KdKeyPressed
        //Valid.pindah(evt,BtnClose,no_rm_ibu);
}//GEN-LAST:event_KdKeyPressed

    private void ProsesKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_ProsesKeyPressed
        Valid.pindah(evt, PenyulitKehamilan, BtnSimpan);
    }//GEN-LAST:event_ProsesKeyPressed

    private void AnakkeKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_AnakkeKeyPressed
        Valid.pindah(evt, Daftar, keterangan);
    }//GEN-LAST:event_AnakkeKeyPressed


    private void BtnPrintActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_BtnPrintActionPerformed
        this.setCursor(Cursor.getPredefinedCursor(Cursor.WAIT_CURSOR));
        tampil();
        if (tabMode.getRowCount() == 0) {
            JOptionPane.showMessageDialog(null, "Maaf, data sudah habis. Tidak ada data yang bisa anda print...!!!!");
            BtnBatal.requestFocus();
        } else if (tabMode.getRowCount() != 0) {
            jkelcari = "";
            tglcari = "";
            if (!cmbCrJk.getSelectedItem().toString().equals("SEMUA")) {
                jkelcari = " pasien.jk='" + cmbCrJk.getSelectedItem().toString().substring(0, 1) + "' and ";
            }

            if (ckTglCari.isSelected() == true) {
                tglcari = " pasien.tgl_lahir between '" + Valid.SetTgl(DTPCari1.getSelectedItem() + "") + "' and '" + Valid.SetTgl(DTPCari2.getSelectedItem() + "") + "' and ";
            }

            Map<String, Object> param = new HashMap<>();
            param.put("namars", var.getnamars());
            param.put("alamatrs", var.getalamatrs());
            param.put("kotars", var.getkabupatenrs());
            param.put("propinsirs", var.getpropinsirs());
            param.put("kontakrs", var.getkontakrs());
            param.put("emailrs", var.getemailrs());
            param.put("logo", Sequel.cariGambar("select logo from setting"));

            String sql = "select pasien.no_rkm_medis, pasien.nm_pasien, pasien.jk, "
                    + "pasien.tgl_lahir,pasien_bayi.jam_lahir, pasien.umur, "
                    + "pasien.tgl_daftar,pasien.nm_ibu,pasien_bayi.umur_ibu, "
                    + "pasien_bayi.nama_ayah,pasien_bayi.umur_ayah,pasien.alamat, "
                    + "pasien_bayi.berat_badan,pasien_bayi.panjang_badan, pasien_bayi.lingkar_kepala, "
                    + "pasien_bayi.proses_lahir,pasien_bayi.anakke, pasien_bayi.keterangan, "
                    + "pasien_bayi.diagnosa,pasien_bayi.penyulit_kehamilan,pasien_bayi.ketuban,"
                    + "pasien_bayi.lingkar_perut,pasien_bayi.lingkar_dada,pegawai.nama,"
                    + "pasien_bayi.no_skl,pasien_bayi.kematian_perinatal,pasien_bayi.sebab_kematian,"
                    + "pasien_bayi.asal_rujukan,pasien_bayi.dirujuk from pasien inner join pegawai inner join pasien_bayi "
                    + "on pasien.no_rkm_medis=pasien_bayi.no_rkm_medis and pasien_bayi.penolong=pegawai.nik "
                    + "where " + jkelcari + tglcari + " pasien.no_rkm_medis like '%" + TCari.getText().trim() + "%' "
                    + "or " + jkelcari + tglcari + " pasien.nm_pasien like '%" + TCari.getText().trim() + "%' "
                    + "or " + jkelcari + tglcari + " pasien.tgl_lahir like '%" + TCari.getText().trim() + "%' "
                    + "or " + jkelcari + tglcari + " pasien.namakeluarga like '%" + TCari.getText().trim() + "%' "
                    + "or " + jkelcari + tglcari + " pasien.alamat like '%" + TCari.getText().trim() + "%' "
                    + "or " + jkelcari + tglcari + " pegawai.nama like '%" + TCari.getText().trim() + "%' "
                    + "or " + jkelcari + tglcari + " pasien_bayi.diagnosa like '%" + TCari.getText().trim() + "%' "
                    + "or " + jkelcari + tglcari + " pasien.nm_ibu like '%" + TCari.getText().trim() + "%' "
                    + "or " + jkelcari + tglcari + " pasien_bayi.proses_lahir like '%" + TCari.getText().trim() + "%' "
                    + "or " + jkelcari + tglcari + " pasien_bayi.penyulit_kehamilan like '%" + TCari.getText().trim() + "%' "
                    + "or " + jkelcari + tglcari + " pasien_bayi.ketuban like '%" + TCari.getText().trim() + "%'  order by pasien.no_rkm_medis desc";
            Valid.MyReport("rptPasienbayi.jrxml", "report", "::[ Data Bayi ]::", sql, param);
        }
        this.setCursor(Cursor.getDefaultCursor());
}//GEN-LAST:event_BtnPrintActionPerformed

    private void BtnPrintKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_BtnPrintKeyPressed
        if (evt.getKeyCode() == KeyEvent.VK_SPACE) {
            BtnPrintActionPerformed(null);
        } else {
            Valid.pindah(evt, BtnEdit, BtnAll);
        }
}//GEN-LAST:event_BtnPrintKeyPressed

    private void BtnAllActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_BtnAllActionPerformed
        TCari.setText("");
        emptTeks();
        tampil();
}//GEN-LAST:event_BtnAllActionPerformed

    private void BtnAllKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_BtnAllKeyPressed
        if (evt.getKeyCode() == KeyEvent.VK_SPACE) {
            BtnAllActionPerformed(null);
        } else {
            Valid.pindah(evt, BtnPrint, BtnKeluar);
        }
}//GEN-LAST:event_BtnAllKeyPressed

    private void BtnKeluarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_BtnKeluarActionPerformed
        dispose();
}//GEN-LAST:event_BtnKeluarActionPerformed

    private void BtnKeluarKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_BtnKeluarKeyPressed
        if (evt.getKeyCode() == KeyEvent.VK_SPACE) {
            dispose();
        } else {
            Valid.pindah(evt, BtnAll, TCari);
        }
}//GEN-LAST:event_BtnKeluarKeyPressed

    private void LingkarKepalaKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_LingkarKepalaKeyPressed
        Valid.pindah(evt, Panjang, UmurBayi);
    }//GEN-LAST:event_LingkarKepalaKeyPressed

private void JKelKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_JKelKeyPressed
    Valid.pindah(evt, AlamatIbu, cmbBerat);
}//GEN-LAST:event_JKelKeyPressed

private void DiagnosaKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_DiagnosaKeyPressed
    Valid.pindah(evt, keterangan, Ketuban);
}//GEN-LAST:event_DiagnosaKeyPressed

private void NoRmKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_NoRmKeyPressed
    Valid.pindah(evt, TCari, NmBayi);
}//GEN-LAST:event_NoRmKeyPressed

private void LahirKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_LahirKeyPressed
    Valid.pindah(evt, cmbBerat, jam);
}//GEN-LAST:event_LahirKeyPressed

private void cmbCrJkKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_cmbCrJkKeyPressed
    Valid.pindah(evt, BtnAll, DTPCari1);
}//GEN-LAST:event_cmbCrJkKeyPressed

private void DTPCari1KeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_DTPCari1KeyPressed
    Valid.pindah(evt, Proses, Diagnosa);
}//GEN-LAST:event_DTPCari1KeyPressed

private void DTPCari2KeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_DTPCari2KeyPressed
// TODO add your handling code here:
}//GEN-LAST:event_DTPCari2KeyPressed

private void jamKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_jamKeyPressed
    Valid.pindah(evt, Lahir, menit);
}//GEN-LAST:event_jamKeyPressed

private void menitKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_menitKeyPressed
    Valid.pindah(evt, jam, detik);
}//GEN-LAST:event_menitKeyPressed

private void detikKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_detikKeyPressed
    Valid.pindah(evt, menit, KdPenolong);
}//GEN-LAST:event_detikKeyPressed

private void PanjangKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_PanjangKeyPressed
    Valid.pindah(evt, LingkarPerut, LingkarKepala);
}//GEN-LAST:event_PanjangKeyPressed

private void DaftarKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_DaftarKeyPressed
    Valid.pindah(evt, LingkarDada, Anakke);
}//GEN-LAST:event_DaftarKeyPressed

private void keteranganKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_keteranganKeyPressed

}//GEN-LAST:event_keteranganKeyPressed

private void LahirItemStateChanged(java.awt.event.ItemEvent evt) {//GEN-FIRST:event_LahirItemStateChanged
    lahir = Lahir.getDate();
    birthday = lahir.toInstant().atZone(ZoneId.systemDefault()).toLocalDate();
    p = Period.between(birthday, today);
    p2 = ChronoUnit.DAYS.between(birthday, today);
    UmurBayi.setText(String.valueOf(p.getYears()) + " Th " + String.valueOf(p.getMonths()) + " Bl " + String.valueOf(p.getDays()) + " Hr");
}//GEN-LAST:event_LahirItemStateChanged

private void ppGrafikberatActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_ppGrafikberatActionPerformed
    String say = "";
    String tgl = "";
    if (ckTglCari.isSelected() == true) {
        say = "inner join pasien on pasien.no_rkm_medis=pasien_bayi.no_rkm_medis where tgl_lahir between '" + Valid.SetTgl(DTPCari1.getSelectedItem() + "") + "' and '" + Valid.SetTgl(DTPCari2.getSelectedItem() + "") + "' ";
        tgl = " Antara Tanggal " + Valid.SetTgl(DTPCari1.getSelectedItem() + "") + " s.d. " + Valid.SetTgl(DTPCari2.getSelectedItem() + "") + " ";
    }
    grafikberat kas = new grafikberat("Grafik Berat Badan Bayi " + tgl, say + " ");
    kas.setSize(this.getWidth(), this.getHeight());
    kas.setLocationRelativeTo(this);
    kas.setVisible(true);
}//GEN-LAST:event_ppGrafikberatActionPerformed

private void ppGrafikberatlakiActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_ppGrafikberatlakiActionPerformed
    String say = "";
    String tgl = "";
    if (ckTglCari.isSelected() == true) {
        say = " tgl_lahir between '" + Valid.SetTgl(DTPCari1.getSelectedItem() + "") + "' and '" + Valid.SetTgl(DTPCari2.getSelectedItem() + "") + "' and ";
        tgl = " Antara Tanggal " + Valid.SetTgl(DTPCari1.getSelectedItem() + "") + " s.d. " + Valid.SetTgl(DTPCari2.getSelectedItem() + "") + " ";
    }
    grafikberat kas = new grafikberat("Grafik Berat Badan Bayi Laki-Laki " + tgl, "inner join pasien "
            + "on pasien.no_rkm_medis=pasien_bayi.no_rkm_medis where " + say + " pasien.jk='L' ");
    kas.setSize(this.getWidth(), this.getHeight());
    kas.setLocationRelativeTo(this);
    kas.setVisible(true);
}//GEN-LAST:event_ppGrafikberatlakiActionPerformed

private void ppGrafikberatwnActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_ppGrafikberatwnActionPerformed
    String say = "";
    String tgl = "";
    if (ckTglCari.isSelected() == true) {
        say = " tgl_lahir between '" + Valid.SetTgl(DTPCari1.getSelectedItem() + "") + "' and '" + Valid.SetTgl(DTPCari2.getSelectedItem() + "") + "' and ";
        tgl = " Antara Tanggal " + Valid.SetTgl(DTPCari1.getSelectedItem() + "") + " s.d. " + Valid.SetTgl(DTPCari2.getSelectedItem() + "") + " ";
    }
    grafikberat kas = new grafikberat("Grafik Berat Badan Bayi Perempuan " + tgl, "inner join pasien "
            + "on pasien.no_rkm_medis=pasien_bayi.no_rkm_medis where " + say + " pasien.jk='P' ");
    kas.setSize(this.getWidth(), this.getHeight());
    kas.setLocationRelativeTo(this);
    kas.setVisible(true);
}//GEN-LAST:event_ppGrafikberatwnActionPerformed

private void ppGrafikjkbayiActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_ppGrafikjkbayiActionPerformed
    String say = "";
    String tgl = "";
    if (ckTglCari.isSelected() == true) {
        say = " where tgl_lahir between '" + Valid.SetTgl(DTPCari1.getSelectedItem() + "") + "' and '" + Valid.SetTgl(DTPCari2.getSelectedItem() + "") + "' ";
        tgl = " Antara Tanggal " + Valid.SetTgl(DTPCari1.getSelectedItem() + "") + " s.d. " + Valid.SetTgl(DTPCari2.getSelectedItem() + "") + " ";
    }
    grafikjkelbayi kas = new grafikjkelbayi("Grafik Jenis Kelamin Bayi " + tgl, say + " ");
    kas.setSize(this.getWidth(), this.getHeight());
    kas.setLocationRelativeTo(this);
    kas.setVisible(true);
}//GEN-LAST:event_ppGrafikjkbayiActionPerformed

private void ppGrafikpanjangActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_ppGrafikpanjangActionPerformed
    String say = "";
    String tgl = "";
    if (ckTglCari.isSelected() == true) {
        say = "inner join pasien on pasien.no_rkm_medis=pasien_bayi.no_rkm_medis where tgl_lahir between '" + Valid.SetTgl(DTPCari1.getSelectedItem() + "") + "' and '" + Valid.SetTgl(DTPCari2.getSelectedItem() + "") + "' ";
        tgl = " Antara Tanggal " + Valid.SetTgl(DTPCari1.getSelectedItem() + "") + " s.d. " + Valid.SetTgl(DTPCari2.getSelectedItem() + "") + " ";
    }
    grafikpanjang kas = new grafikpanjang("Grafik Panjang Badan Bayi " + tgl, say + " ");
    kas.setSize(this.getWidth(), this.getHeight());
    kas.setLocationRelativeTo(this);
    kas.setVisible(true);
}//GEN-LAST:event_ppGrafikpanjangActionPerformed

private void ppGrafikpanjanglakiActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_ppGrafikpanjanglakiActionPerformed
    String say = "";
    String tgl = "";
    if (ckTglCari.isSelected() == true) {
        say = " tgl_lahir between '" + Valid.SetTgl(DTPCari1.getSelectedItem() + "") + "' and '" + Valid.SetTgl(DTPCari2.getSelectedItem() + "") + "' and ";
        tgl = " Antara Tanggal " + Valid.SetTgl(DTPCari1.getSelectedItem() + "") + " s.d. " + Valid.SetTgl(DTPCari2.getSelectedItem() + "") + " ";
    }
    grafikpanjang kas = new grafikpanjang("Grafik Panjang Badan Bayi Laki-Laki " + tgl, "inner join pasien "
            + "on pasien.no_rkm_medis=pasien_bayi.no_rkm_medis where " + say + " jk='L' ");
    kas.setSize(this.getWidth(), this.getHeight());
    kas.setLocationRelativeTo(this);
    kas.setVisible(true);
}//GEN-LAST:event_ppGrafikpanjanglakiActionPerformed

private void ppGrafikpanjangwnActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_ppGrafikpanjangwnActionPerformed
    String say = "";
    String tgl = "";
    if (ckTglCari.isSelected() == true) {
        say = " tgl_lahir between '" + Valid.SetTgl(DTPCari1.getSelectedItem() + "") + "' and '" + Valid.SetTgl(DTPCari2.getSelectedItem() + "") + "' and ";
        tgl = " Antara Tanggal " + Valid.SetTgl(DTPCari1.getSelectedItem() + "") + " s.d. " + Valid.SetTgl(DTPCari2.getSelectedItem() + "") + " ";
    }
    grafikpanjang kas = new grafikpanjang("Grafik Panjang Badan Bayi Perempuan " + tgl, "inner join pasien "
            + "on pasien.no_rkm_medis=pasien_bayi.no_rkm_medis where " + say + " jk='P' ");
    kas.setSize(this.getWidth(), this.getHeight());
    kas.setLocationRelativeTo(this);
    kas.setVisible(true);
}//GEN-LAST:event_ppGrafikpanjangwnActionPerformed

private void ppGrafiklahirtahunActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_ppGrafiklahirtahunActionPerformed
    String say = "";
    String tgl = "";
    if (ckTglCari.isSelected() == true) {
        say = " where tgl_lahir between '" + Valid.SetTgl(DTPCari1.getSelectedItem() + "") + "' and '" + Valid.SetTgl(DTPCari2.getSelectedItem() + "") + "' ";
        tgl = " Antara Tanggal " + Valid.SetTgl(DTPCari1.getSelectedItem() + "") + " s.d. " + Valid.SetTgl(DTPCari2.getSelectedItem() + "") + " ";
    }
    grafiklahirtahun kas = new grafiklahirtahun("Grafik Kelahiran Pertahun Bayi " + tgl, say + " ");
    kas.setSize(this.getWidth(), this.getHeight());
    kas.setLocationRelativeTo(this);
    kas.setVisible(true);
}//GEN-LAST:event_ppGrafiklahirtahunActionPerformed

private void ppGrafiklahirtahunlakiActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_ppGrafiklahirtahunlakiActionPerformed
    String say = "";
    String tgl = "";
    if (ckTglCari.isSelected() == true) {
        say = " tgl_lahir between '" + Valid.SetTgl(DTPCari1.getSelectedItem() + "") + "' and '" + Valid.SetTgl(DTPCari2.getSelectedItem() + "") + "' and ";
        tgl = " Antara Tanggal " + Valid.SetTgl(DTPCari1.getSelectedItem() + "") + " s.d. " + Valid.SetTgl(DTPCari2.getSelectedItem() + "") + " ";
    }
    grafiklahirtahun kas = new grafiklahirtahun("Grafik Kelahiran Pertahun Bayi Laki-Laki " + tgl, " where " + say + " jk='L' ");
    kas.setSize(this.getWidth(), this.getHeight());
    kas.setLocationRelativeTo(this);
    kas.setVisible(true);
}//GEN-LAST:event_ppGrafiklahirtahunlakiActionPerformed

private void ppGrafiklahirtahunwnActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_ppGrafiklahirtahunwnActionPerformed
    String say = "";
    String tgl = "";
    if (ckTglCari.isSelected() == true) {
        say = " tgl_lahir between '" + Valid.SetTgl(DTPCari1.getSelectedItem() + "") + "' and '" + Valid.SetTgl(DTPCari2.getSelectedItem() + "") + "' and ";
        tgl = " Antara Tanggal " + Valid.SetTgl(DTPCari1.getSelectedItem() + "") + " s.d. " + Valid.SetTgl(DTPCari2.getSelectedItem() + "") + " ";
    }
    grafiklahirtahun kas = new grafiklahirtahun("Grafik Kelahiran Pertahun Bayi Perempuan " + tgl, "  where " + say + " jk='P' ");
    kas.setSize(this.getWidth(), this.getHeight());
    kas.setLocationRelativeTo(this);
    kas.setVisible(true);
}//GEN-LAST:event_ppGrafiklahirtahunwnActionPerformed

private void ppGrafiklahirbulanActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_ppGrafiklahirbulanActionPerformed
    String say = "";
    String tgl = "";
    if (ckTglCari.isSelected() == true) {
        say = " where tgl_lahir between '" + Valid.SetTgl(DTPCari1.getSelectedItem() + "") + "' and '" + Valid.SetTgl(DTPCari2.getSelectedItem() + "") + "' ";
        tgl = " Antara Tanggal " + Valid.SetTgl(DTPCari1.getSelectedItem() + "") + " s.d. " + Valid.SetTgl(DTPCari2.getSelectedItem() + "") + " ";
    }
    grafiklahirbulan kas = new grafiklahirbulan("Grafik Kelahiran Perbulan Bayi " + tgl, say + " ");
    kas.setSize(this.getWidth(), this.getHeight());
    kas.setLocationRelativeTo(this);
    kas.setVisible(true);
}//GEN-LAST:event_ppGrafiklahirbulanActionPerformed

private void ppGrafiklahirbulanlakiActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_ppGrafiklahirbulanlakiActionPerformed
    String say = "";
    String tgl = "";
    if (ckTglCari.isSelected() == true) {
        say = " tgl_lahir between '" + Valid.SetTgl(DTPCari1.getSelectedItem() + "") + "' and '" + Valid.SetTgl(DTPCari2.getSelectedItem() + "") + "' and ";
        tgl = " Antara Tanggal " + Valid.SetTgl(DTPCari1.getSelectedItem() + "") + " s.d. " + Valid.SetTgl(DTPCari2.getSelectedItem() + "") + " ";
    }
    grafiklahirbulan kas = new grafiklahirbulan("Grafik Kelahiran Perbulan Bayi Laki-Laki " + tgl, " where " + say + " jk='L' ");
    kas.setSize(this.getWidth(), this.getHeight());
    kas.setLocationRelativeTo(this);
    kas.setVisible(true);
}//GEN-LAST:event_ppGrafiklahirbulanlakiActionPerformed

private void ppGrafiklahirbulanwnActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_ppGrafiklahirbulanwnActionPerformed
    String say = "";
    String tgl = "";
    if (ckTglCari.isSelected() == true) {
        say = " tgl_lahir between '" + Valid.SetTgl(DTPCari1.getSelectedItem() + "") + "' and '" + Valid.SetTgl(DTPCari2.getSelectedItem() + "") + "' and ";
        tgl = " Antara Tanggal " + Valid.SetTgl(DTPCari1.getSelectedItem() + "") + " s.d. " + Valid.SetTgl(DTPCari2.getSelectedItem() + "") + " ";
    }
    grafiklahirbulan kas = new grafiklahirbulan("Grafik Kelahiran Perbulan Bayi Perempuan " + tgl, " where " + say + " jk='P' ");
    kas.setSize(this.getWidth(), this.getHeight());
    kas.setLocationRelativeTo(this);
    kas.setVisible(true);
}//GEN-LAST:event_ppGrafiklahirbulanwnActionPerformed

private void ppGrafikproseslahirActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_ppGrafikproseslahirActionPerformed
    String say = "";
    String tgl = "";
    if (ckTglCari.isSelected() == true) {
        say = " where tgl_lahir between '" + Valid.SetTgl(DTPCari1.getSelectedItem() + "") + "' and '" + Valid.SetTgl(DTPCari2.getSelectedItem() + "") + "' ";
        tgl = " Antara Tanggal " + Valid.SetTgl(DTPCari1.getSelectedItem() + "") + " s.d. " + Valid.SetTgl(DTPCari2.getSelectedItem() + "") + " ";
    }
    grafikproses kas = new grafikproses("Grafik Proses Lahir Bayi " + tgl, say + " ");
    kas.setSize(this.getWidth(), this.getHeight());
    kas.setLocationRelativeTo(this);
    kas.setVisible(true);
}//GEN-LAST:event_ppGrafikproseslahirActionPerformed

private void ppGrafikproseslahirlakiActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_ppGrafikproseslahirlakiActionPerformed
    String say = "";
    String tgl = "";
    if (ckTglCari.isSelected() == true) {
        say = " tgl_lahir between '" + Valid.SetTgl(DTPCari1.getSelectedItem() + "") + "' and '" + Valid.SetTgl(DTPCari2.getSelectedItem() + "") + "' and ";
        tgl = " Antara Tanggal " + Valid.SetTgl(DTPCari1.getSelectedItem() + "") + " s.d. " + Valid.SetTgl(DTPCari2.getSelectedItem() + "") + " ";
    }
    grafikproses kas = new grafikproses("Grafik Proses Lahir Bayi Laki-Laki " + tgl, " where " + say + " jk='L' ");
    kas.setSize(this.getWidth(), this.getHeight());
    kas.setLocationRelativeTo(this);
    kas.setVisible(true);
}//GEN-LAST:event_ppGrafikproseslahirlakiActionPerformed

private void ppGrafikproseslahirwnActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_ppGrafikproseslahirwnActionPerformed
    String say = "";
    String tgl = "";
    if (ckTglCari.isSelected() == true) {
        say = " tgl_lahir between '" + Valid.SetTgl(DTPCari1.getSelectedItem() + "") + "' and '" + Valid.SetTgl(DTPCari2.getSelectedItem() + "") + "' and ";
        tgl = " Antara Tanggal " + Valid.SetTgl(DTPCari1.getSelectedItem() + "") + " s.d. " + Valid.SetTgl(DTPCari2.getSelectedItem() + "") + " ";
    }
    grafikproses kas = new grafikproses("Grafik Proses Lahir Bayi Perempuan " + tgl, " where " + say + " jk='P' ");
    kas.setSize(this.getWidth(), this.getHeight());
    kas.setLocationRelativeTo(this);
    kas.setVisible(true);
}//GEN-LAST:event_ppGrafikproseslahirwnActionPerformed

private void ChkInputActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_ChkInputActionPerformed
    isForm();
}//GEN-LAST:event_ChkInputActionPerformed

private void BtnSimpanActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_BtnSimpanActionPerformed
    if (NoRm.getText().trim().equals("")) {
        Valid.textKosong(NoRm, "No.Rekam Medis Bayi");
    } else if (NmBayi.getText().trim().equals("")) {
        Valid.textKosong(NmBayi, "nama bayi");
    } else if (NoRmIbu.getText().trim().equals("")) {
        Valid.textKosong(NoRmIbu, "No.Rekam Medis Ibu");
    } else if (KdPenolong.getText().trim().equals("") || NmPenolong.getText().trim().equals("")) {
        Valid.textKosong(KdPenolong, "penolong");
    } else if (jns_alamat.getSelectedItem().equals(" ")) {
        Valid.textKosong(jns_alamat, "jenis alamat");
    } else if (cara_lahir.getSelectedItem().equals(" ")) {
        Valid.textKosong(cara_lahir, "cara lahir");
    } else if (jns_penolong.getSelectedItem().equals(" ")) {
        Valid.textKosong(jns_penolong, "jenis penolong");
    } else if (menit1.getSelectedItem().equals(" ")) {
        Valid.textKosong(menit1, "apgus score menit 1");
    } else if (menit5.getSelectedItem().equals(" ")) {
        Valid.textKosong(menit5, "apgus score menit 5");
    } else if (menit10.getSelectedItem().equals(" ")) {
        Valid.textKosong(menit10, "apgus score menit 10");
    } else if (umur_hamil.getSelectedItem().equals(" ")) {
        Valid.textKosong(umur_hamil, "umur kehamilan");
    } else if (cmbAsalBayi.getSelectedItem().equals(" ")) {
        Valid.textKosong(cmbAsalBayi, "asal pasien bayi");
    } else if (benarberat.getText().trim().equals("")) {
        Valid.textKosong(benarberat, "berat lahir bayi sebenarnya");
    } else if (cmbBerat.getSelectedItem().equals(" ")) {
        Valid.textKosong(cmbBerat, "berat lahir bayi");
    } else if (cmbRawat.getSelectedItem().equals(" ")) {
        Valid.textKosong(cmbRawat, "rawat gabung dengan ibunya");
    } else if (cmbIMD.getSelectedItem().equals(" ")) {
        Valid.textKosong(cmbIMD, "IMD");
    } else if (cmbKMC.getSelectedItem().equals(" ")) {
        Valid.textKosong(cmbKMC, "KMC");
    } else if (LingkarPerut.getText().trim().equals("") || (Panjang.getText().trim().equals("")
            || (LingkarKepala.getText().trim().equals("") || (LingkarDada.getText().trim().equals("")
            || (Anakke.getText().trim().equals("") || (Ketuban.getText().trim().equals(""))))))) {
        JOptionPane.showMessageDialog(null, "Data bayi tidak boleh kosong...!!");

    } else {
        simpanData();
    }
}//GEN-LAST:event_BtnSimpanActionPerformed

private void BtnSimpanKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_BtnSimpanKeyPressed
    if (evt.getKeyCode() == KeyEvent.VK_SPACE) {
        BtnSimpanActionPerformed(null);
    } else {
        Valid.pindah(evt, Proses, BtnBatal);
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

private void BtnEditActionPerformed1(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_BtnEditActionPerformed1
    if (NoRm.getText().trim().equals("")) {
        Valid.textKosong(NoRm, "No.Rekam Medis");
    } else if (NmBayi.getText().trim().equals("")) {
        Valid.textKosong(NmBayi, "nama bayi");
    } else if (NoRmIbu.getText().trim().equals("")) {
        Valid.textKosong(NoRmIbu, "No.Rekam Medis Ibu");
    } else if (KdPenolong.getText().trim().equals("") || NmPenolong.getText().trim().equals("")) {
        Valid.textKosong(KdPenolong, "penolong");
    } else if (jns_alamat.getSelectedItem().equals(" ")) {
        Valid.textKosong(jns_alamat, "jenis alamat");
    } else if (cara_lahir.getSelectedItem().equals(" ")) {
        Valid.textKosong(cara_lahir, "cara lahir");
    } else if (jns_penolong.getSelectedItem().equals(" ")) {
        Valid.textKosong(jns_penolong, "jenis penolong");
    } else if (menit1.getSelectedItem().equals(" ")) {
        Valid.textKosong(menit1, "apgus score menit 1");
    } else if (menit5.getSelectedItem().equals(" ")) {
        Valid.textKosong(menit5, "apgus score menit 5");
    } else if (menit10.getSelectedItem().equals(" ")) {
        Valid.textKosong(menit10, "apgus score menit 10");
    } else if (umur_hamil.getSelectedItem().equals(" ")) {
        Valid.textKosong(umur_hamil, "umur kehamilan");
    } else if (cmbAsalBayi.getSelectedItem().equals(" ")) {
        Valid.textKosong(cmbAsalBayi, "asal pasien bayi");
    } else if (benarberat.getText().trim().equals("")) {
        Valid.textKosong(benarberat, "berat lahir bayi sebenarnya");
    } else if (cmbBerat.getSelectedItem().equals(" ")) {
        Valid.textKosong(cmbBerat, "berat lahir bayi");
    } else if (cmbRawat.getSelectedItem().equals(" ")) {
        Valid.textKosong(cmbRawat, "rawat gabung dengan ibunya");
    } else if (cmbIMD.getSelectedItem().equals(" ")) {
        Valid.textKosong(cmbIMD, "IMD");
    } else if (cmbKMC.getSelectedItem().equals(" ")) {
        Valid.textKosong(cmbKMC, "KMC");
    } else {
        Valid.editTable(tabMode, "pasien", "no_rkm_medis", Kd2, "no_rkm_medis='" + NoRm.getText()
                + "',nm_pasien='" + NmBayi.getText()
                + "',jk='" + JKel.getSelectedItem().toString().substring(0, 1)
                + "',tgl_lahir='" + Valid.SetTgl(Lahir.getSelectedItem() + "")
                + "',alamat='" + AlamatIbu.getText()
                + "',tgl_daftar='" + Valid.SetTgl(Daftar.getSelectedItem() + "")
                + "',umur='" + UmurBayi.getText()
                + "',nm_ibu='" + Nmibu.getText()
                + "',namakeluarga='" + NmAyah.getText() + "'");
        Valid.editTable(tabMode, "pasien_bayi", "no_rkm_medis", Kd2, "umur_ibu='" + UmurIbu.getText()
                + "',nama_ayah='" + NmAyah.getText()
                + "',umur_ayah='" + UmurAyah.getText()
                + "',berat_badan='" + cmbBerat.getSelectedItem()
                + "',panjang_badan='" + Panjang.getText()
                + "',lingkar_kepala='" + LingkarKepala.getText()
                + "',proses_lahir='" + Proses.getText()
                + "',anakke='" + Anakke.getText()
                + "',diagnosa='" + Diagnosa.getText()
                + "',penyulit_kehamilan='" + PenyulitKehamilan.getText()
                + "',ketuban='" + Ketuban.getText()
                + "',lingkar_perut='" + LingkarPerut.getText()
                + "',lingkar_dada='" + LingkarDada.getText()
                + "',penolong='" + KdPenolong.getText()
                + "',no_skl='" + NoSKL.getText()
                + "',kematian_perinatal='" + cmbMatiPerin.getSelectedItem()
                + "',sebab_kematian='" + cmbSebabMati.getSelectedItem()
                + "',asal_rujukan='" + cmbRujukan.getSelectedItem()
                + "',dirujuk='" + cmbDirujuk.getSelectedItem()
                + "',jenis_alamat='" + jns_alamat.getSelectedItem()
                + "',cara_lahir='" + cara_lahir.getSelectedItem()
                + "',jenis_penolong='" + jns_penolong.getSelectedItem()
                + "',apgus_skor_menit1='" + menit1.getSelectedItem()
                + "',apgus_skor_menit5='" + menit5.getSelectedItem()
                + "',apgus_skor_menit10='" + menit10.getSelectedItem()
                + "',asal_bayi='" + cmbAsalBayi.getSelectedItem()
                + "',umur_kehamilan='" + umur_hamil.getSelectedItem()
                + "',jam_lahir='" + jam.getSelectedItem() + ":" + menit.getSelectedItem() + ":" + detik.getSelectedItem()
                + "',keterangan='" + keterangan.getText()
                + "',rawat_gabung_dg_ibu='" + cmbRawat.getSelectedItem()
                + "',IMD='" + cmbIMD.getSelectedItem()
                + "',KMC='" + cmbKMC.getSelectedItem()
                + "',berat_badan_benar='" + benarberat.getText()
                + "',no_rawat_ibu='" + norwtIbu + "'");
        if (tabMode.getRowCount() != 0) {
            tampil();
        }
        emptTeks();
    }
}//GEN-LAST:event_BtnEditActionPerformed1

private void BtnEditKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_BtnEditKeyPressed
    if (evt.getKeyCode() == KeyEvent.VK_SPACE) {
        BtnEditActionPerformed1(null);
    } else {
        Valid.pindah(evt, BtnHapus, BtnPrint);
    }
}//GEN-LAST:event_BtnEditKeyPressed

private void BtnHapusActionPerformed1(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_BtnHapusActionPerformed1
    Valid.hapusTable(tabMode, NoRm, "pasien_bayi", "no_rkm_medis");
    tampil();
    emptTeks();
}//GEN-LAST:event_BtnHapusActionPerformed1

private void BtnHapusKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_BtnHapusKeyPressed
    if (evt.getKeyCode() == KeyEvent.VK_SPACE) {
        BtnHapusActionPerformed1(null);
    } else {
        Valid.pindah(evt, BtnBatal, BtnEdit);
    }
}//GEN-LAST:event_BtnHapusKeyPressed

private void NmBayiKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_NmBayiKeyPressed
    Valid.pindah(evt, NoRm, Nmibu);
}//GEN-LAST:event_NmBayiKeyPressed

private void NmibuKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_NmibuKeyPressed
    Valid.pindah(evt, NmBayi, UmurIbu);
}//GEN-LAST:event_NmibuKeyPressed

private void UmurIbuKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_UmurIbuKeyPressed
    Valid.pindah(evt, Nmibu, NmAyah);
}//GEN-LAST:event_UmurIbuKeyPressed

private void NmAyahKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_NmAyahKeyPressed
    Valid.pindah(evt, Nmibu, UmurAyah);
}//GEN-LAST:event_NmAyahKeyPressed

private void UmurAyahKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_UmurAyahKeyPressed
    Valid.pindah(evt, NmAyah, AlamatIbu);
}//GEN-LAST:event_UmurAyahKeyPressed

private void AlamatIbuKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_AlamatIbuKeyPressed
    Valid.pindah(evt, UmurAyah, JKel);
}//GEN-LAST:event_AlamatIbuKeyPressed

private void MnKartuActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_MnKartuActionPerformed
    if (tabMode.getRowCount() == 0) {
        JOptionPane.showMessageDialog(null, "Maaf, data pasien sudah habis...!!!!");
        NoRm.requestFocus();
    } else if (NmBayi.getText().trim().equals("")) {
        JOptionPane.showMessageDialog(null, "Maaf, Silahkan anda pilih dulu data pasien yang mau pulang dengan menklik data pada table...!!!");
        tbBayi.requestFocus();
    } else {
        this.setCursor(Cursor.getPredefinedCursor(Cursor.WAIT_CURSOR));
        Valid.MyReport("rptKartuBerobat.jrxml", "report", "::[ Kartu Berobat Pasien (KIB) ]::", "select * from pasien where pasien.no_rkm_medis='" + NoRm.getText() + "' ");
        this.setCursor(Cursor.getDefaultCursor());
    }
}//GEN-LAST:event_MnKartuActionPerformed

    private void formWindowOpened(java.awt.event.WindowEvent evt) {//GEN-FIRST:event_formWindowOpened
        tampil();
    }//GEN-LAST:event_formWindowOpened

    private void MnInformasiBayiActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_MnInformasiBayiActionPerformed
        this.setCursor(Cursor.getPredefinedCursor(Cursor.WAIT_CURSOR));
        tampil();
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
            Valid.MyReport("rptInformasiBayi.jrxml", "report", "::[ Data Informasi Bayi ]::",
                    "select pasien.no_rkm_medis, pasien.nm_pasien, pasien.jk, "
                    + "pasien.tgl_lahir,pasien_bayi.jam_lahir, pasien.umur, "
                    + "pasien.tgl_daftar,pasien.nm_ibu as namakeluarga,pasien_bayi.umur_ibu, "
                    + "pasien_bayi.nama_ayah,pasien_bayi.umur_ayah,pasien.alamat, "
                    + "pasien_bayi.berat_badan,pasien_bayi.panjang_badan, pasien_bayi.lingkar_kepala, "
                    + "pasien_bayi.proses_lahir,pasien_bayi.anakke, pasien_bayi.keterangan from pasien "
                    + "inner join pasien_bayi on pasien.no_rkm_medis=pasien_bayi.no_rkm_medis "
                    + "where pasien_bayi.no_rkm_medis='" + NoRm.getText() + "'", param);
        }
        this.setCursor(Cursor.getDefaultCursor());
    }//GEN-LAST:event_MnInformasiBayiActionPerformed

    private void MnSKLActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_MnSKLActionPerformed
        this.setCursor(Cursor.getPredefinedCursor(Cursor.WAIT_CURSOR));
        tampil();
        if (tabMode.getRowCount() == 0) {
            JOptionPane.showMessageDialog(null, "Maaf, data sudah habis. Tidak ada data yang bisa anda print...!!!!");
            BtnBatal.requestFocus();
        } else if (tabMode.getRowCount() != 0) {
            Map<String, Object> param = new HashMap<>();
            param.put("namars", var.getnamars());
            param.put("alamatrs", var.getalamatrs());
            param.put("kotars", var.getkabupatenrs());
            param.put("nomor", NoSKL.getText());
            param.put("propinsirs", var.getpropinsirs());
            param.put("kontakrs", var.getkontakrs());
            param.put("emailrs", var.getemailrs());
            param.put("logo", Sequel.cariGambar("select logo from setting"));
            param.put("logo2", Sequel.cariGambar("select logo from setting"));
            Valid.MyReport("rptSKL.jrxml", "report", "::[ Surat Kelahiran Bayi ]::",
                    "select pasien.no_rkm_medis, pasien.nm_pasien, pasien.jk, "
                    + "pasien.tgl_lahir,pasien_bayi.jam_lahir, pasien.umur, "
                    + "pasien.tgl_daftar,pasien.nm_ibu,pasien_bayi.umur_ibu, "
                    + "pasien_bayi.nama_ayah,pasien_bayi.umur_ayah,pasien.alamat, "
                    + "pasien_bayi.berat_badan,pasien_bayi.panjang_badan, pasien_bayi.lingkar_kepala, "
                    + "pasien_bayi.proses_lahir,pasien_bayi.anakke, pasien_bayi.keterangan, "
                    + "pasien_bayi.diagnosa,pasien_bayi.penyulit_kehamilan,pasien_bayi.ketuban,"
                    + "pasien_bayi.lingkar_perut,pasien_bayi.lingkar_dada,pegawai.nama,"
                    + "pasien_bayi.no_skl from pasien inner join pegawai inner join pasien_bayi "
                    + "on pasien.no_rkm_medis=pasien_bayi.no_rkm_medis and pasien_bayi.penolong=pegawai.nik "
                    + "where pasien_bayi.no_rkm_medis='" + NoRm.getText() + "'", param);
        }
        this.setCursor(Cursor.getDefaultCursor());
    }//GEN-LAST:event_MnSKLActionPerformed

    private void UmurBayiKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_UmurBayiKeyPressed
        Valid.pindah(evt, LingkarKepala, LingkarDada);
    }//GEN-LAST:event_UmurBayiKeyPressed

    private void PenyulitKehamilanKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_PenyulitKehamilanKeyPressed
        Valid.pindah(evt, Ketuban, Proses);
    }//GEN-LAST:event_PenyulitKehamilanKeyPressed

    private void KetubanKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_KetubanKeyPressed
        Valid.pindah(evt, Diagnosa, PenyulitKehamilan);
    }//GEN-LAST:event_KetubanKeyPressed

    private void LingkarDadaKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_LingkarDadaKeyPressed
        Valid.pindah(evt, UmurBayi, Daftar);
    }//GEN-LAST:event_LingkarDadaKeyPressed

    private void LingkarPerutKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_LingkarPerutKeyPressed
        Valid.pindah(evt, NoSKL, Panjang);
    }//GEN-LAST:event_LingkarPerutKeyPressed

    private void NoSKLKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_NoSKLKeyPressed
        Valid.pindah(evt, KdPenolong, LingkarPerut);
    }//GEN-LAST:event_NoSKLKeyPressed

    private void KdPenolongKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_KdPenolongKeyPressed
        Valid.pindah(evt, detik, NoSKL);
    }//GEN-LAST:event_KdPenolongKeyPressed

    private void BtnPenjabActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_BtnPenjabActionPerformed
        pegawai.isCek();        
        pegawai.emptTeks();
        pegawai.setSize(internalFrame1.getWidth() - 40, internalFrame1.getHeight() - 40);
        pegawai.setLocationRelativeTo(internalFrame1);
        pegawai.setAlwaysOnTop(false);
        pegawai.setVisible(true);
    }//GEN-LAST:event_BtnPenjabActionPerformed

    private void MnSKL1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_MnSKL1ActionPerformed
        this.setCursor(Cursor.getPredefinedCursor(Cursor.WAIT_CURSOR));
        tampil();
        if (tabMode.getRowCount() == 0) {
            JOptionPane.showMessageDialog(null, "Maaf, data sudah habis. Tidak ada data yang bisa anda print...!!!!");
            BtnBatal.requestFocus();
        } else if (tabMode.getRowCount() != 0) {
            Map<String, Object> param = new HashMap<>();
            param.put("namars", var.getnamars());
            param.put("alamatrs", var.getalamatrs());
            param.put("kotars", var.getkabupatenrs());
            param.put("nomor", NoSKL.getText());
            param.put("propinsirs", var.getpropinsirs());
            param.put("kontakrs", var.getkontakrs());
            param.put("emailrs", var.getemailrs());
            param.put("logo", Sequel.cariGambar("select logo from setting"));
            param.put("logo2", Sequel.cariGambar("select logo from setting"));
            Valid.MyReport("rptSKL2.jrxml", "report", "::[ Surat Kelahiran Bayi ]::",
                    "select pasien.no_rkm_medis,pasien.nm_pasien, pasien.jk, "
                    + "pasien.no_ktp,pasien.pekerjaanpj, pasien.no_tlp, "
                    + "pasien.tgl_lahir,pasien_bayi.jam_lahir, pasien.umur, "
                    + "pasien.tgl_daftar,pasien.nm_ibu,pasien_bayi.umur_ibu, "
                    + "pasien_bayi.nama_ayah,pasien_bayi.umur_ayah,pasien.alamat, "
                    + "pasien_bayi.berat_badan,pasien_bayi.panjang_badan, pasien_bayi.lingkar_kepala, "
                    + "pasien_bayi.proses_lahir,pasien_bayi.anakke, pasien_bayi.keterangan, "
                    + "pasien_bayi.diagnosa,pasien_bayi.penyulit_kehamilan,pasien_bayi.ketuban,"
                    + "pasien_bayi.lingkar_perut,pasien_bayi.lingkar_dada,pegawai.nama,"
                    + "pasien_bayi.no_skl from pasien inner join pegawai inner join pasien_bayi "
                    + "on pasien.no_rkm_medis=pasien_bayi.no_rkm_medis and pasien_bayi.penolong=pegawai.nik "
                    + "where pasien_bayi.no_rkm_medis='" + NoRm.getText() + "'", param);
        }
        this.setCursor(Cursor.getDefaultCursor());
    }//GEN-LAST:event_MnSKL1ActionPerformed

    private void btnPasienActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnPasienActionPerformed
        var.setform("DlgIKBBayi");
        member.emptTeks();
        member.isCek();
        member.setSize(internalFrame1.getWidth() - 40, internalFrame1.getHeight() - 40);
        member.setLocationRelativeTo(internalFrame1);
        member.setAlwaysOnTop(false);
        member.setVisible(true);
    }//GEN-LAST:event_btnPasienActionPerformed

    private void ckTglCariActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_ckTglCariActionPerformed
        if (ckTglCari.isSelected() == true) {
            tampil();
        } else if (ckTglCari.isSelected() == false) {
            DTPCari1.requestFocus();
        }
    }//GEN-LAST:event_ckTglCariActionPerformed

    private void cmbBeratKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_cmbBeratKeyPressed
        // TODO add your handling code here:
    }//GEN-LAST:event_cmbBeratKeyPressed

    private void cmbMatiPerinKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_cmbMatiPerinKeyPressed
        // TODO add your handling code here:
    }//GEN-LAST:event_cmbMatiPerinKeyPressed

    private void cmbSebabMatiKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_cmbSebabMatiKeyPressed
        // TODO add your handling code here:
    }//GEN-LAST:event_cmbSebabMatiKeyPressed

    private void cmbRujukanKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_cmbRujukanKeyPressed
        // TODO add your handling code here:
    }//GEN-LAST:event_cmbRujukanKeyPressed

    private void cmbDirujukKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_cmbDirujukKeyPressed
        // TODO add your handling code here:
    }//GEN-LAST:event_cmbDirujukKeyPressed

    private void jns_alamatKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_jns_alamatKeyPressed
        // TODO add your handling code here:
    }//GEN-LAST:event_jns_alamatKeyPressed

    private void cara_lahirKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_cara_lahirKeyPressed
        // TODO add your handling code here:
    }//GEN-LAST:event_cara_lahirKeyPressed

    private void jns_penolongKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_jns_penolongKeyPressed
        // TODO add your handling code here:
    }//GEN-LAST:event_jns_penolongKeyPressed

    private void menit1KeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_menit1KeyPressed
        // TODO add your handling code here:
    }//GEN-LAST:event_menit1KeyPressed

    private void menit5KeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_menit5KeyPressed
        // TODO add your handling code here:
    }//GEN-LAST:event_menit5KeyPressed

    private void menit10KeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_menit10KeyPressed
        // TODO add your handling code here:
    }//GEN-LAST:event_menit10KeyPressed

    private void umur_hamilKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_umur_hamilKeyPressed
        // TODO add your handling code here:
    }//GEN-LAST:event_umur_hamilKeyPressed

    private void jns_alamatMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jns_alamatMouseClicked
        jns_alamat.setEditable(false);
    }//GEN-LAST:event_jns_alamatMouseClicked

    private void cara_lahirMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_cara_lahirMouseClicked
        cara_lahir.setEditable(false);
    }//GEN-LAST:event_cara_lahirMouseClicked

    private void jns_penolongMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jns_penolongMouseClicked
        jns_penolong.setEditable(false);
    }//GEN-LAST:event_jns_penolongMouseClicked

    private void menit1MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_menit1MouseClicked
        menit1.setEditable(false);
    }//GEN-LAST:event_menit1MouseClicked

    private void menit5MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_menit5MouseClicked
        menit5.setEditable(false);
    }//GEN-LAST:event_menit5MouseClicked

    private void menit10MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_menit10MouseClicked
        menit10.setEditable(false);
    }//GEN-LAST:event_menit10MouseClicked

    private void umur_hamilMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_umur_hamilMouseClicked
        umur_hamil.setEditable(false);
    }//GEN-LAST:event_umur_hamilMouseClicked

    private void cmbAsalBayiMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_cmbAsalBayiMouseClicked
        cmbAsalBayi.setEditable(false);
    }//GEN-LAST:event_cmbAsalBayiMouseClicked

    private void cmbAsalBayiKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_cmbAsalBayiKeyPressed
        // TODO add your handling code here:
    }//GEN-LAST:event_cmbAsalBayiKeyPressed

    private void cmbRawatKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_cmbRawatKeyPressed
        // TODO add your handling code here:
    }//GEN-LAST:event_cmbRawatKeyPressed

    private void cmbIMDKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_cmbIMDKeyPressed
        // TODO add your handling code here:
    }//GEN-LAST:event_cmbIMDKeyPressed

    private void cmbKMCKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_cmbKMCKeyPressed
        // TODO add your handling code here:
    }//GEN-LAST:event_cmbKMCKeyPressed

    private void cmbRawatMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_cmbRawatMouseClicked
        cmbRawat.setEditable(false);
    }//GEN-LAST:event_cmbRawatMouseClicked

    private void cmbIMDMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_cmbIMDMouseClicked
        cmbIMD.setEditable(false);
    }//GEN-LAST:event_cmbIMDMouseClicked

    private void cmbKMCMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_cmbKMCMouseClicked
        cmbKMC.setEditable(false);
    }//GEN-LAST:event_cmbKMCMouseClicked

    private void benarberatKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_benarberatKeyPressed
        // TODO add your handling code here:
    }//GEN-LAST:event_benarberatKeyPressed

    private void benarberatFocusLost(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_benarberatFocusLost
        if (benarberat.getText().equals("")) {
            cmbBerat.setSelectedIndex(0);
        } else {
            berat = Integer.parseInt(benarberat.getText());
            if (berat < 1000) {
                cmbBerat.setSelectedIndex(4);
            } else if (berat >= 1000 && berat < 1500) {
                cmbBerat.setSelectedIndex(5);
            } else if (berat >= 1500 && berat < 2500) {
                cmbBerat.setSelectedIndex(6);
            } else if (berat >= 2500 && berat <= 4000) {
                cmbBerat.setSelectedIndex(7);
            } else {
                cmbBerat.setSelectedIndex(8);
            }
        }
    }//GEN-LAST:event_benarberatFocusLost

    private void NoRmIbuKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_NoRmIbuKeyPressed
        // TODO add your handling code here:
    }//GEN-LAST:event_NoRmIbuKeyPressed

    private void btnPasienIbuActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnPasienIbuActionPerformed
        if (tabMode.getRowCount() == 0) {
            JOptionPane.showMessageDialog(null, "Maaf, tabel masih kosong...!!!!");
            TCari.requestFocus();
        } else if (NoRm.getText().equals("")) {
            JOptionPane.showMessageDialog(null, "No. RM Bayi harus terisi dulu...!!!!");
            BtnPrint.requestFocus();
        } else {
            WindowPasienIbuBersalin.setSize(724, 512);
            WindowPasienIbuBersalin.setLocationRelativeTo(internalFrame1);
            WindowPasienIbuBersalin.setAlwaysOnTop(false);
            WindowPasienIbuBersalin.setVisible(true);  
            chkIbu.setSelected(false);
            tgl.setDate(1);
            tgl1.setDate(tgl);
            tgl2.setDate(new Date());
            tampilPersalinan();
            TCari1.requestFocus();            
        }
    }//GEN-LAST:event_btnPasienIbuActionPerformed

    private void tbPasienPersalinanMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tbPasienPersalinanMouseClicked
        if (tabMode1.getRowCount() != 0) {
            try {
//                getDatakasir();
            } catch (java.lang.NullPointerException e) {
            }

            if (evt.getClickCount() == 2) {
                norwtIbu = tbPasienPersalinan.getValueAt(tbPasienPersalinan.getSelectedRow(), 0).toString();
                NoRmIbu.setText(tbPasienPersalinan.getValueAt(tbPasienPersalinan.getSelectedRow(), 1).toString());
                Nmibu.setText(tbPasienPersalinan.getValueAt(tbPasienPersalinan.getSelectedRow(), 2).toString());
                AlamatIbu.setText(Sequel.cariIsi("select alamat from pasien where no_rkm_medis='" + NoRmIbu.getText() + "'"));
                UmurIbu.setText(Sequel.cariIsi("select concat(umurdaftar,' ',sttsumur) from reg_periksa where no_rkm_medis='" + NoRmIbu.getText() + "'"));
                WindowPasienIbuBersalin.dispose();
                NmAyah.requestFocus();
            }
        }
    }//GEN-LAST:event_tbPasienPersalinanMouseClicked

    private void tbPasienPersalinanKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_tbPasienPersalinanKeyPressed
        if (tabMode1.getRowCount() != 0) {
            if ((evt.getKeyCode() == KeyEvent.VK_ENTER) || (evt.getKeyCode() == KeyEvent.VK_UP) || (evt.getKeyCode() == KeyEvent.VK_DOWN)) {
                try {
//                    getDatakasir();
                } catch (java.lang.NullPointerException e) {
                }
            }

            if (evt.getKeyCode() == KeyEvent.VK_SPACE) {
                norwtIbu = tbPasienPersalinan.getValueAt(tbPasienPersalinan.getSelectedRow(), 0).toString();
                NoRmIbu.setText(tbPasienPersalinan.getValueAt(tbPasienPersalinan.getSelectedRow(), 1).toString());
                Nmibu.setText(tbPasienPersalinan.getValueAt(tbPasienPersalinan.getSelectedRow(), 2).toString());
                AlamatIbu.setText(Sequel.cariIsi("select alamat from pasien where no_rkm_medis='" + NoRmIbu.getText() + "'"));
                UmurIbu.setText(Sequel.cariIsi("select concat(umurdaftar,' ',sttsumur) from reg_periksa where no_rkm_medis='" + NoRmIbu.getText() + "'"));
                WindowPasienIbuBersalin.dispose();
                NmAyah.requestFocus();
            }
        }
    }//GEN-LAST:event_tbPasienPersalinanKeyPressed

    private void tgl1KeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_tgl1KeyPressed
        // TODO add your handling code here:
    }//GEN-LAST:event_tgl1KeyPressed

    private void tgl2KeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_tgl2KeyPressed
        // TODO add your handling code here:
    }//GEN-LAST:event_tgl2KeyPressed

    private void TCari1KeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_TCari1KeyPressed
        if (evt.getKeyCode() == KeyEvent.VK_ENTER) {
            BtnCari1ActionPerformed(null);
        }
    }//GEN-LAST:event_TCari1KeyPressed

    private void BtnCari1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_BtnCari1ActionPerformed
        tampilPersalinan();
    }//GEN-LAST:event_BtnCari1ActionPerformed

    private void BtnCari1KeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_BtnCari1KeyPressed
        if (evt.getKeyCode() == KeyEvent.VK_SPACE) {
            BtnCari1ActionPerformed(null);
        } else {
            Valid.pindah(evt, TCari1, BtnCari1);
        }
    }//GEN-LAST:event_BtnCari1KeyPressed

    private void BtnAll1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_BtnAll1ActionPerformed
        TCari1.setText("");
        tampilPersalinan();
    }//GEN-LAST:event_BtnAll1ActionPerformed

    private void BtnAll1KeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_BtnAll1KeyPressed
        if (evt.getKeyCode() == KeyEvent.VK_SPACE) {
            tampilPersalinan();
            TCari1.setText("");
        } else {
            Valid.pindah(evt, TCari1, BtnKeluar1);
        }
    }//GEN-LAST:event_BtnAll1KeyPressed

    private void BtnKeluar1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_BtnKeluar1ActionPerformed
        WindowPasienIbuBersalin.dispose();
        NmAyah.requestFocus();
    }//GEN-LAST:event_BtnKeluar1ActionPerformed

    private void BtnKeluar1KeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_BtnKeluar1KeyPressed
        if (evt.getKeyCode() == KeyEvent.VK_ENTER) {
            dispose();
        } else {
            Valid.pindah(evt, BtnPrint, TCari);
        }
    }//GEN-LAST:event_BtnKeluar1KeyPressed

    private void chkIbuActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_chkIbuActionPerformed
        if (chkIbu.isSelected() == true) {
            norwtIbu = "-";
            NoRmIbu.setText("-");
            Nmibu.setText("-");
            AlamatIbu.setText("-");
            UmurIbu.setText("-");
        } else {
            norwtIbu = "";
            NoRmIbu.setText("");
            Nmibu.setText("");
            AlamatIbu.setText("");
            UmurIbu.setText("");
        }
    }//GEN-LAST:event_chkIbuActionPerformed

    /**
     * @param args the command line arguments
     */
    public static void main(String args[]) {
        java.awt.EventQueue.invokeLater(() -> {
            DlgIKBBayi dialog = new DlgIKBBayi(new javax.swing.JFrame(), true);
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
    private widget.TextBox AlamatIbu;
    private widget.TextBox Anakke;
    private widget.Button BtnAll;
    private widget.Button BtnAll1;
    private widget.Button BtnBatal;
    private widget.Button BtnCari;
    private widget.Button BtnCari1;
    private widget.Button BtnEdit;
    private widget.Button BtnHapus;
    private widget.Button BtnKeluar;
    private widget.Button BtnKeluar1;
    private widget.Button BtnPenjab;
    private widget.Button BtnPrint;
    private widget.Button BtnSimpan;
    private widget.CekBox ChkInput;
    private widget.Tanggal DTPCari1;
    private widget.Tanggal DTPCari2;
    private widget.Tanggal Daftar;
    private widget.TextBox Diagnosa;
    private widget.PanelBiasa FormInput;
    private widget.ComboBox JKel;
    private widget.TextBox Kd2;
    private widget.TextBox KdPenolong;
    private widget.TextBox Ketuban;
    private widget.Label LCount;
    private widget.Label LCount1;
    private widget.Tanggal Lahir;
    private widget.TextBox LingkarDada;
    private widget.TextBox LingkarKepala;
    private widget.TextBox LingkarPerut;
    private javax.swing.JMenuItem MnInformasiBayi;
    private javax.swing.JMenuItem MnKartu;
    private javax.swing.JMenuItem MnSKL;
    private javax.swing.JMenuItem MnSKL1;
    private widget.TextBox NmAyah;
    private widget.TextBox NmBayi;
    private widget.TextBox NmPenolong;
    private widget.TextBox Nmibu;
    private widget.TextBox NoRm;
    private widget.TextBox NoRmIbu;
    private widget.TextBox NoSKL;
    private javax.swing.JPanel PanelInput;
    private widget.TextBox Panjang;
    private widget.TextBox PenyulitKehamilan;
    private javax.swing.JPopupMenu Popup;
    private widget.TextBox Proses;
    private widget.ScrollPane Scroll1;
    private widget.TextBox TCari;
    private widget.TextBox TCari1;
    private widget.TextBox UmurAyah;
    private widget.TextBox UmurBayi;
    private widget.TextBox UmurIbu;
    private javax.swing.JDialog WindowPasienIbuBersalin;
    private widget.TextBox benarberat;
    private widget.Button btnPasien;
    private widget.Button btnPasienIbu;
    private widget.ComboBox cara_lahir;
    private widget.CekBox chkIbu;
    private widget.CekBox ckTglCari;
    private widget.ComboBox cmbAsalBayi;
    private widget.ComboBox cmbBerat;
    private widget.ComboBox cmbCrJk;
    private widget.ComboBox cmbDirujuk;
    private widget.ComboBox cmbHlm;
    private widget.ComboBox cmbIMD;
    private widget.ComboBox cmbKMC;
    private widget.ComboBox cmbMatiPerin;
    private widget.ComboBox cmbRawat;
    private widget.ComboBox cmbRujukan;
    private widget.ComboBox cmbSebabMati;
    private widget.ComboBox detik;
    private widget.InternalFrame internalFrame1;
    private widget.InternalFrame internalFrame5;
    private widget.Label jLabel15;
    private widget.Label jLabel16;
    private widget.Label jLabel17;
    private widget.Label jLabel24;
    private widget.Label jLabel7;
    private widget.Label jLabel8;
    private javax.swing.JMenu jMenu1;
    private javax.swing.JMenu jMenu2;
    private javax.swing.JMenu jMenu3;
    private javax.swing.JMenu jMenu4;
    private javax.swing.JMenu jMenu5;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JPanel jPanel3;
    private widget.ComboBox jam;
    private widget.ComboBox jns_alamat;
    private widget.ComboBox jns_penolong;
    private widget.TextArea keterangan;
    private widget.Label label10;
    private widget.Label label12;
    private widget.Label label18;
    private widget.Label label19;
    private widget.Label label20;
    private widget.Label label21;
    private widget.Label label22;
    private widget.Label label23;
    private widget.Label label24;
    private widget.Label label25;
    private widget.Label label26;
    private widget.Label label27;
    private widget.Label label28;
    private widget.Label label29;
    private widget.Label label30;
    private widget.Label label31;
    private widget.Label label32;
    private widget.Label label33;
    private widget.Label label34;
    private widget.Label label35;
    private widget.Label label36;
    private widget.Label label37;
    private widget.Label label38;
    private widget.Label label39;
    private widget.Label label40;
    private widget.Label label41;
    private widget.Label label42;
    private widget.Label label43;
    private widget.Label label44;
    private widget.Label label45;
    private widget.Label label46;
    private widget.Label label47;
    private widget.Label label48;
    private widget.Label label49;
    private widget.Label label50;
    private widget.Label label51;
    private widget.Label label52;
    private widget.Label label53;
    private widget.Label label54;
    private widget.Label label55;
    private widget.Label label56;
    private widget.Label label57;
    private widget.Label label58;
    private widget.Label label59;
    private widget.Label label9;
    private widget.ComboBox menit;
    private widget.ComboBox menit1;
    private widget.ComboBox menit10;
    private widget.ComboBox menit5;
    private widget.panelisi panelGlass8;
    private widget.panelisi panelGlass9;
    private widget.panelisi panelisi1;
    private widget.panelisi panelisi2;
    private javax.swing.JMenuItem ppGrafikberat;
    private javax.swing.JMenuItem ppGrafikberatlaki;
    private javax.swing.JMenuItem ppGrafikberatwn;
    private javax.swing.JMenuItem ppGrafikjkbayi;
    private javax.swing.JMenuItem ppGrafiklahirbulan;
    private javax.swing.JMenuItem ppGrafiklahirbulanlaki;
    private javax.swing.JMenuItem ppGrafiklahirbulanwn;
    private javax.swing.JMenuItem ppGrafiklahirtahun;
    private javax.swing.JMenuItem ppGrafiklahirtahunlaki;
    private javax.swing.JMenuItem ppGrafiklahirtahunwn;
    private javax.swing.JMenuItem ppGrafikpanjang;
    private javax.swing.JMenuItem ppGrafikpanjanglaki;
    private javax.swing.JMenuItem ppGrafikpanjangwn;
    private javax.swing.JMenuItem ppGrafikproseslahir;
    private javax.swing.JMenuItem ppGrafikproseslahirlaki;
    private javax.swing.JMenuItem ppGrafikproseslahirwn;
    private widget.ScrollPane scrollPane1;
    private widget.ScrollPane scrollPane2;
    private widget.Table tbBayi;
    private widget.Table tbPasienPersalinan;
    private widget.Tanggal tgl1;
    private widget.Tanggal tgl2;
    private widget.ComboBox umur_hamil;
    // End of variables declaration//GEN-END:variables

    public void tampil() {
        jkelcari = "";
        tglcari = "";
        if (!cmbCrJk.getSelectedItem().toString().equals("SEMUA")) {
            jkelcari = " p.jk='" + cmbCrJk.getSelectedItem().toString().substring(0, 1) + "' and ";
        }

        if (ckTglCari.isSelected() == true) {
            tglcari = " p.tgl_lahir between '" + Valid.SetTgl(DTPCari1.getSelectedItem() + "") + "' and '" + Valid.SetTgl(DTPCari2.getSelectedItem() + "") + "' and ";
        }

        String sql = "SELECT p.no_rkm_medis, p.nm_pasien, p.jk, p.tgl_lahir, pb.jam_lahir, p.umur, p.tgl_daftar, p.nm_ibu, "
                + "pb.umur_ibu, pb.nama_ayah, pb.umur_ayah, p.alamat, pb.berat_badan, pb.panjang_badan, pb.lingkar_kepala, "
                + "pb.proses_lahir, pb.anakke, pb.keterangan, pb.diagnosa, pb.penyulit_kehamilan, pb.ketuban, pb.lingkar_perut, "
                + "pb.lingkar_dada, pg.nama, pb.no_skl, pb.kematian_perinatal, pb.sebab_kematian, pb.asal_rujukan, pb.dirujuk, "
                + "ifnull(pb.jenis_alamat,'-') jenis_alamat, ifnull(pb.cara_lahir,'-') cara_lahir, ifnull(pb.jenis_penolong,'-') jenis_penolong, "
                + "ifnull(pb.apgus_skor_menit1,'-') apgus_skor_menit1, ifnull(pb.apgus_skor_menit5,'-') apgus_skor_menit1, ifnull(pb.apgus_skor_menit10,'-') apgus_skor_menit10, "
                + "ifnull(pb.asal_bayi,'-') asal_bayi, ifnull(pb.umur_kehamilan,'-') umur_kehamilan, ifnull(pb.rawat_gabung_dg_ibu,'-') rwt_gabung, "
                + "ifnull(pb.IMD,'-') imd, ifnull(pb.KMC,'-') kmc, pb.berat_badan_benar, ifnull(pb.no_rawat_ibu,'-') no_rawat_ibu "
                + "FROM pasien p INNER JOIN pasien_bayi pb on pb.no_rkm_medis=p.no_rkm_medis INNER JOIN pegawai pg on pg.nik=pb.penolong "
                + "where " + jkelcari + tglcari + " p.no_rkm_medis like '%" + TCari.getText().trim() + "%' "
                + "or " + jkelcari + tglcari + " p.nm_pasien like '%" + TCari.getText().trim() + "%' "
                + "or " + jkelcari + tglcari + " p.tgl_lahir like '%" + TCari.getText().trim() + "%' "
                + "or " + jkelcari + tglcari + " p.namakeluarga like '%" + TCari.getText().trim() + "%' "
                + "or " + jkelcari + tglcari + " p.alamat like '%" + TCari.getText().trim() + "%' "
                + "or " + jkelcari + tglcari + " pg.nama like '%" + TCari.getText().trim() + "%' "
                + "or " + jkelcari + tglcari + " pb.diagnosa like '%" + TCari.getText().trim() + "%' "
                + "or " + jkelcari + tglcari + " p.nm_ibu like '%" + TCari.getText().trim() + "%' "
                + "or " + jkelcari + tglcari + " pb.proses_lahir like '%" + TCari.getText().trim() + "%' "
                + "or " + jkelcari + tglcari + " pb.penyulit_kehamilan like '%" + TCari.getText().trim() + "%' "
                + "or " + jkelcari + tglcari + " pb.ketuban like '%" + TCari.getText().trim() + "%' "
                + "or " + jkelcari + tglcari + " pb.no_skl like '%" + TCari.getText().trim() + "%' "
                + "or " + jkelcari + tglcari + " pb.kematian_perinatal like '%" + TCari.getText().trim() + "%' "
                + "or " + jkelcari + tglcari + " pb.sebab_kematian like '%" + TCari.getText().trim() + "%' "
                + "or " + jkelcari + tglcari + " pb.asal_rujukan like '%" + TCari.getText().trim() + "%' "
                + "or " + jkelcari + tglcari + " pb.dirujuk like '%" + TCari.getText().trim() + "%' "
                + "or " + jkelcari + tglcari + " pb.jenis_alamat like '%" + TCari.getText().trim() + "%' "
                + "or " + jkelcari + tglcari + " pb.cara_lahir like '%" + TCari.getText().trim() + "%' "
                + "or " + jkelcari + tglcari + " pb.jenis_penolong like '%" + TCari.getText().trim() + "%' "
                + "or " + jkelcari + tglcari + " pb.apgus_skor_menit1 like '%" + TCari.getText().trim() + "%' "
                + "or " + jkelcari + tglcari + " pb.apgus_skor_menit5 like '%" + TCari.getText().trim() + "%' "
                + "or " + jkelcari + tglcari + " pb.apgus_skor_menit10 like '%" + TCari.getText().trim() + "%' "
                + "or " + jkelcari + tglcari + " pb.asal_bayi like '%" + TCari.getText().trim() + "%' "
                + "or " + jkelcari + tglcari + " pb.rawat_gabung_dg_ibu like '%" + TCari.getText().trim() + "%' "
                + "or " + jkelcari + tglcari + " pb.IMD like '%" + TCari.getText().trim() + "%' "
                + "or " + jkelcari + tglcari + " pb.KMC like '%" + TCari.getText().trim() + "%' "
                + "or " + jkelcari + tglcari + " pb.berat_badan_benar like '%" + TCari.getText().trim() + "%' "
                + "or " + jkelcari + tglcari + " pb.umur_kehamilan like '%" + TCari.getText().trim() + "%' "
                + "or " + jkelcari + tglcari + " pb.no_rawat_ibu like '%" + TCari.getText().trim() + "%' "
                + "order by p.tgl_daftar desc limit " + cmbHlm.getSelectedItem() + " ";
        prosesCari(sql);
    }

    private void prosesCari(String sql) {
        Valid.tabelKosong(tabMode);
        try {
            java.sql.Statement stat = koneksi.createStatement();
            ResultSet rs = stat.executeQuery(sql);
            while (rs.next()) {
                tabMode.addRow(new Object[]{
                    rs.getString(1),
                    rs.getString(2),
                    rs.getString(3),
                    rs.getString(4),
                    rs.getString(5),
                    rs.getString(6),
                    rs.getString(7),
                    rs.getString(8),
                    rs.getString(9),
                    rs.getString(10),
                    rs.getString(11),
                    rs.getString(12),
                    rs.getString(13),
                    rs.getString(14),
                    rs.getString(15),
                    rs.getString(16),
                    rs.getString(17),
                    rs.getString(18),
                    rs.getString(19),
                    rs.getString(20),
                    rs.getString(21),
                    rs.getString(22),
                    rs.getString(23),
                    rs.getString(24),
                    rs.getString(25),
                    rs.getString(26),
                    rs.getString(27),
                    rs.getString(28),
                    rs.getString(29),
                    rs.getString(30),
                    rs.getString(31),
                    rs.getString(32),
                    rs.getString(33),
                    rs.getString(34),
                    rs.getString(35),
                    rs.getString(36),
                    rs.getString(37),
                    rs.getString(38),
                    rs.getString(39),
                    rs.getString(40),
                    rs.getString(41),
                    rs.getString(42)
                });
            }
        } catch (SQLException e) {
            System.out.println("Notifikasi : " + e);
        }
        int b = tabMode.getRowCount();
        LCount.setText("" + b);
    }

    public void emptTeks() {
        NoRm.setText("");
        NmBayi.setText("");
        norwtIbu = "";
        NoRmIbu.setText("");
        Nmibu.setText("");
        UmurIbu.setText("");
        NmAyah.setText("");
        UmurAyah.setText("");
        AlamatIbu.setText("");
        JKel.setSelectedIndex(0);
        cmbBerat.setSelectedIndex(0);
        benarberat.setText("");
        Lahir.setDate(new Date());
        jam.setSelectedIndex(0);
        menit.setSelectedIndex(0);
        detik.setSelectedIndex(0);
        Panjang.setText("");
        LingkarKepala.setText("");
        UmurBayi.setText("");
        Proses.setText("");
        LingkarPerut.setText("");
        LingkarDada.setText("");
        Daftar.setDate(new Date());
        Anakke.setText("");
        Diagnosa.setText("");
        PenyulitKehamilan.setText("");
        Ketuban.setText("");
        keterangan.setText("");
        //autoNomor();        
        autoSKL();
        btnPasien.requestFocus();
        KdPenolong.setText("PR10");
        NmPenolong.setText("PETUGAS RUANG PERINATOLOGI");
        cmbMatiPerin.setSelectedIndex(0);
        cmbSebabMati.setSelectedIndex(0);
        cmbRujukan.setSelectedIndex(5);
        cmbDirujuk.setSelectedIndex(0);

        JKel.setEditable(false);
        Lahir.setEditable(false);
        cmbBerat.setEditable(false);
        cmbMatiPerin.setEditable(false);
        cmbSebabMati.setEditable(false);
        cmbRujukan.setEditable(false);
        cmbDirujuk.setEditable(false);
        jam.setEditable(false);
        menit.setEditable(false);
        detik.setEditable(false);

        jns_alamat.setSelectedIndex(0);
        cara_lahir.setSelectedIndex(0);
        jns_penolong.setSelectedIndex(0);
        menit1.setSelectedIndex(0);
        menit5.setSelectedIndex(0);
        menit10.setSelectedIndex(0);
        umur_hamil.setSelectedIndex(0);
        cmbAsalBayi.setSelectedIndex(0);
        cmbRawat.setSelectedIndex(0);
        cmbIMD.setSelectedIndex(0);
        cmbKMC.setSelectedIndex(0);
    }

    private void getData() {
        norwtIbu = "";
        
        if (tbBayi.getSelectedRow() != -1) {
            NoRm.setText(tbBayi.getValueAt(tbBayi.getSelectedRow(), 0).toString());
            Kd2.setText(tbBayi.getValueAt(tbBayi.getSelectedRow(), 0).toString());
            NmBayi.setText(tbBayi.getValueAt(tbBayi.getSelectedRow(), 1).toString());
            if (tbBayi.getValueAt(tbBayi.getSelectedRow(), 2).toString().equals("L")) {
                JKel.setSelectedItem("LAKI-LAKI");
            } else if (tbBayi.getValueAt(tbBayi.getSelectedRow(), 2).toString().equals("P")) {
                JKel.setSelectedItem("PEREMPUAN");
            }
            Valid.SetTgl(Lahir, tbBayi.getValueAt(tbBayi.getSelectedRow(), 3).toString());
            jam.setSelectedItem(tbBayi.getValueAt(tbBayi.getSelectedRow(), 4).toString().substring(0, 2));
            menit.setSelectedItem(tbBayi.getValueAt(tbBayi.getSelectedRow(), 4).toString().substring(3, 5));
            detik.setSelectedItem(tbBayi.getValueAt(tbBayi.getSelectedRow(), 4).toString().substring(6, 8));
            UmurBayi.setText(tbBayi.getValueAt(tbBayi.getSelectedRow(), 5).toString());
            Valid.SetTgl(Lahir, tbBayi.getValueAt(tbBayi.getSelectedRow(), 6).toString());            
            UmurIbu.setText(tbBayi.getValueAt(tbBayi.getSelectedRow(), 8).toString());
            NmAyah.setText(tbBayi.getValueAt(tbBayi.getSelectedRow(), 9).toString());
            UmurAyah.setText(tbBayi.getValueAt(tbBayi.getSelectedRow(), 10).toString());            
            cmbBerat.setSelectedItem(tbBayi.getValueAt(tbBayi.getSelectedRow(), 12).toString());
            Panjang.setText(tbBayi.getValueAt(tbBayi.getSelectedRow(), 13).toString());
            LingkarKepala.setText(tbBayi.getValueAt(tbBayi.getSelectedRow(), 14).toString());
            Proses.setText(tbBayi.getValueAt(tbBayi.getSelectedRow(), 15).toString());
            Anakke.setText(tbBayi.getValueAt(tbBayi.getSelectedRow(), 16).toString());
            keterangan.setText(tbBayi.getValueAt(tbBayi.getSelectedRow(), 17).toString());
            Diagnosa.setText(tbBayi.getValueAt(tbBayi.getSelectedRow(), 18).toString());
            PenyulitKehamilan.setText(tbBayi.getValueAt(tbBayi.getSelectedRow(), 19).toString());
            Ketuban.setText(tbBayi.getValueAt(tbBayi.getSelectedRow(), 20).toString());
            LingkarPerut.setText(tbBayi.getValueAt(tbBayi.getSelectedRow(), 21).toString());
            LingkarDada.setText(tbBayi.getValueAt(tbBayi.getSelectedRow(), 22).toString());
            NmPenolong.setText(tbBayi.getValueAt(tbBayi.getSelectedRow(), 23).toString());
            NoSKL.setText(tbBayi.getValueAt(tbBayi.getSelectedRow(), 24).toString());
            cmbMatiPerin.setSelectedItem(tbBayi.getValueAt(tbBayi.getSelectedRow(), 25).toString());
            cmbSebabMati.setSelectedItem(tbBayi.getValueAt(tbBayi.getSelectedRow(), 26).toString());
            cmbRujukan.setSelectedItem(tbBayi.getValueAt(tbBayi.getSelectedRow(), 27).toString());
            cmbDirujuk.setSelectedItem(tbBayi.getValueAt(tbBayi.getSelectedRow(), 28).toString());
            benarberat.setText(tbBayi.getValueAt(tbBayi.getSelectedRow(), 40).toString());
            norwtIbu = tbBayi.getValueAt(tbBayi.getSelectedRow(), 41).toString();
            Sequel.cariIsi("select penolong from pasien_bayi where no_rkm_medis=?", KdPenolong, tbBayi.getValueAt(tbBayi.getSelectedRow(), 0).toString());
            
            if (norwtIbu.equals("-")) {
                NoRmIbu.setText("-");
                Nmibu.setText(tbBayi.getValueAt(tbBayi.getSelectedRow(), 7).toString());
                UmurIbu.setText(tbBayi.getValueAt(tbBayi.getSelectedRow(), 8).toString());
                AlamatIbu.setText(tbBayi.getValueAt(tbBayi.getSelectedRow(), 11).toString());                
            } else {
                NoRmIbu.setText(Sequel.cariIsi("select no_rkm_medis from reg_periksa where no_rawat='" + norwtIbu + "'"));
                Nmibu.setText(Sequel.cariIsi("select nm_pasien from pasien where no_rkm_medis='" + NoRmIbu.getText() + "'"));
                AlamatIbu.setText(Sequel.cariIsi("select alamat from pasien where no_rkm_medis='" + NoRmIbu.getText() + "'"));
                UmurIbu.setText(Sequel.cariIsi("select concat(umurdaftar,' ',sttsumur) from reg_periksa where no_rkm_medis='" + NoRmIbu.getText() + "'"));
            }
                                
            if (tbBayi.getValueAt(tbBayi.getSelectedRow(), 29).toString().equals("-")
                    || tbBayi.getValueAt(tbBayi.getSelectedRow(), 29).toString().equals("")) {
                jns_alamat.setSelectedIndex(0);
            } else {
                jns_alamat.setSelectedItem(tbBayi.getValueAt(tbBayi.getSelectedRow(), 29).toString());
            }

            if (tbBayi.getValueAt(tbBayi.getSelectedRow(), 30).toString().equals("-")
                    || tbBayi.getValueAt(tbBayi.getSelectedRow(), 30).toString().equals("")) {
                cara_lahir.setSelectedIndex(0);
            } else {
                cara_lahir.setSelectedItem(tbBayi.getValueAt(tbBayi.getSelectedRow(), 30).toString());
            }

            if (tbBayi.getValueAt(tbBayi.getSelectedRow(), 31).toString().equals("-")
                    || tbBayi.getValueAt(tbBayi.getSelectedRow(), 31).toString().equals("")) {
                jns_penolong.setSelectedIndex(0);
            } else {
                jns_penolong.setSelectedItem(tbBayi.getValueAt(tbBayi.getSelectedRow(), 31).toString());
            }

            if (tbBayi.getValueAt(tbBayi.getSelectedRow(), 32).toString().equals("-")
                    || tbBayi.getValueAt(tbBayi.getSelectedRow(), 32).toString().equals("")) {
                menit1.setSelectedIndex(0);
            } else {
                menit1.setSelectedItem(tbBayi.getValueAt(tbBayi.getSelectedRow(), 32).toString());
            }

            if (tbBayi.getValueAt(tbBayi.getSelectedRow(), 33).toString().equals("-")
                    || tbBayi.getValueAt(tbBayi.getSelectedRow(), 33).toString().equals("")) {
                menit5.setSelectedIndex(0);
            } else {
                menit5.setSelectedItem(tbBayi.getValueAt(tbBayi.getSelectedRow(), 33).toString());
            }

            if (tbBayi.getValueAt(tbBayi.getSelectedRow(), 34).toString().equals("-")
                    || tbBayi.getValueAt(tbBayi.getSelectedRow(), 34).toString().equals("")) {
                menit10.setSelectedIndex(0);
            } else {
                menit10.setSelectedItem(tbBayi.getValueAt(tbBayi.getSelectedRow(), 34).toString());
            }

            if (tbBayi.getValueAt(tbBayi.getSelectedRow(), 36).toString().equals("-")
                    || tbBayi.getValueAt(tbBayi.getSelectedRow(), 36).toString().equals("")) {
                umur_hamil.setSelectedIndex(0);
            } else {
                umur_hamil.setSelectedItem(tbBayi.getValueAt(tbBayi.getSelectedRow(), 36).toString());
            }

            if (tbBayi.getValueAt(tbBayi.getSelectedRow(), 37).toString().equals("-")
                    || tbBayi.getValueAt(tbBayi.getSelectedRow(), 37).toString().equals("")) {
                cmbRawat.setSelectedIndex(0);
            } else {
                cmbRawat.setSelectedItem(tbBayi.getValueAt(tbBayi.getSelectedRow(), 37).toString());
            }

            if (tbBayi.getValueAt(tbBayi.getSelectedRow(), 38).toString().equals("-")
                    || tbBayi.getValueAt(tbBayi.getSelectedRow(), 38).toString().equals("")) {
                cmbIMD.setSelectedIndex(0);
            } else {
                cmbIMD.setSelectedItem(tbBayi.getValueAt(tbBayi.getSelectedRow(), 38).toString());
            }

            if (tbBayi.getValueAt(tbBayi.getSelectedRow(), 39).toString().equals("-")
                    || tbBayi.getValueAt(tbBayi.getSelectedRow(), 39).toString().equals("")) {
                cmbKMC.setSelectedIndex(0);
            } else {
                cmbKMC.setSelectedItem(tbBayi.getValueAt(tbBayi.getSelectedRow(), 39).toString());
            }

            if (tbBayi.getValueAt(tbBayi.getSelectedRow(), 35).toString().equals("-")
                    || tbBayi.getValueAt(tbBayi.getSelectedRow(), 35).toString().equals("")) {
                cmbAsalBayi.setSelectedIndex(0);
            } else {
                cmbAsalBayi.setSelectedItem(tbBayi.getValueAt(tbBayi.getSelectedRow(), 35).toString());
            }
        }
    }

    public JTextField getTextField() {
        return NoRm;
    }

    public JButton getButton() {
        return BtnKeluar;
    }

    private void isIbu() {
        try {
            Statement stat = koneksi.createStatement();
            ResultSet rs = stat.executeQuery("select pasien_ibu.nm_pasien,"
                    + " pasien_ibu.suami, "
                    + " pasien_ibu.umur, "
                    + " pasien_ibu.alamat "
                    + " from pasien_ibu where pasien_ibu.no_rm_ib ");
            while (rs.next()) {
                Nmibu.setText(rs.getString(1));
                NmAyah.setText(rs.getString(2));
                UmurIbu.setText(rs.getString(3));
                AlamatIbu.setText(rs.getString(4));
            }
        } catch (SQLException ex) {
            System.out.println("Catatan ibu : " + ex);
        }
    }

    private void isForm() {
        if (ChkInput.isSelected() == true) {
            ChkInput.setVisible(false);
            PanelInput.setPreferredSize(new Dimension(WIDTH, 370));
            FormInput.setVisible(true);
            ChkInput.setVisible(true);
        } else if (ChkInput.isSelected() == false) {
            ChkInput.setVisible(false);
            PanelInput.setPreferredSize(new Dimension(WIDTH, 20));
            FormInput.setVisible(false);
            ChkInput.setVisible(true);
        }
    }

    public void setNoRM(String norm, String nama, String ibubayi, String alamatibu,
            String jkel, String umur, Date tgllhir, Date daftar) {
        NoRm.setText(norm);
        NmBayi.setText(nama);
        Nmibu.setText(ibubayi);
        AlamatIbu.setText(alamatibu);
        JKel.setSelectedItem(jkel);
        UmurBayi.setText(umur);
        Lahir.setDate(tgllhir);
        Daftar.setDate(daftar);

        if (Sequel.cariIsi("select keluarga from pasien where no_rkm_medis=?", norm).equals("AYAH")) {
            Sequel.cariIsi("select namakeluarga from pasien where no_rkm_medis=?", NmAyah, norm);
        }

        ChkInput.setSelected(true);
        isForm();
        autoSKL();
    }

    public void isCek() {
        BtnSimpan.setEnabled(var.getkelahiran_bayi());
        BtnHapus.setEnabled(var.getkelahiran_bayi());
        BtnEdit.setEnabled(var.getkelahiran_bayi());
        BtnPrint.setEnabled(var.getkelahiran_bayi());

        if (var.getkode().equals("Admin Utama")) {
            NoSKL.setEditable(true);
        } else {
            NoSKL.setEditable(false);
        }
    }

    private void autoNomor() {
        if (Kd2.getText().equals("")) {
            if (tahun.equals("Yes")) {
                awalantahun = Daftar.getSelectedItem().toString().substring(8, 10);
            } else {
                awalantahun = "";
            }

            if (bulan.equals("Yes")) {
                awalanbulan = Daftar.getSelectedItem().toString().substring(3, 5);
            } else {
                awalanbulan = "";
            }

            if (posisitahun.equals("Depan")) {
                switch (pengurutan) {
                    case "Straight":
                        Valid.autoNomer3("select ifnull(MAX(CONVERT(RIGHT(no_rkm_medis,6),signed)),0) from set_no_rkm_medis", "", 6, NoRm);
                        break;
                    case "Terminal":
                        Valid.autoNomer4("select ifnull(MAX(CONVERT(CONCAT(SUBSTRING(RIGHT(no_rkm_medis,6),5,2),SUBSTRING(RIGHT(no_rkm_medis,6),3,2),SUBSTRING(RIGHT(no_rkm_medis,6),1,2)),signed)),0) from set_no_rkm_medis", "", 6, NoRm);
                        break;
                    case "Middle":
                        Valid.autoNomer5("select ifnull(MAX(CONVERT(CONCAT(SUBSTRING(RIGHT(no_rkm_medis,6),3,2),SUBSTRING(RIGHT(no_rkm_medis,6),1,2),SUBSTRING(RIGHT(no_rkm_medis,6),5,2)),signed)),0) from set_no_rkm_medis", "", 6, NoRm);
                        break;
                }
            } else if (posisitahun.equals("Belakang")) {
                switch (pengurutan) {
                    case "Straight":
                        Valid.autoNomer3("select ifnull(MAX(CONVERT(LEFT(no_rkm_medis,6),signed)),0) from set_no_rkm_medis", "", 6, NoRm);
                        break;
                    case "Terminal":
                        Valid.autoNomer4("select ifnull(MAX(CONVERT(CONCAT(SUBSTRING(LEFT(no_rkm_medis,6),5,2),SUBSTRING(LEFT(no_rkm_medis,6),3,2),SUBSTRING(LEFT(no_rkm_medis,6),1,2)),signed)),0) from set_no_rkm_medis", "", 6, NoRm);
                        break;
                    case "Middle":
                        Valid.autoNomer5("select ifnull(MAX(CONVERT(CONCAT(SUBSTRING(LEFT(no_rkm_medis,6),3,2),SUBSTRING(LEFT(no_rkm_medis,6),1,2),SUBSTRING(LEFT(no_rkm_medis,6),5,2)),signed)),0) from set_no_rkm_medis", "", 6, NoRm);
                        break;
                }
            }

            if (posisitahun.equals("Depan")) {
                NoRm.setText(awalantahun + awalanbulan + NoRm.getText());
            } else if (posisitahun.equals("Belakang")) {
                if (!(awalanbulan + awalantahun).equals("")) {
                    NoRm.setText(NoRm.getText() + "-" + awalanbulan + awalantahun);
                } else {
                    NoRm.setText(NoRm.getText());
                }
            }
        }
    }

    public JTable getTable() {
        return tbBayi;
    }

    public void autoSKL() {
        periodeNow = Sequel.cariIsi("select DATE_FORMAt(NOW(),\"%m/%Y\")");
        Valid.autoNomer6("select ifnull(MAX(CONVERT(LEFT(no_skl,4),signed)),0) from pasien inner join pasien_bayi on pasien.no_rkm_medis=pasien_bayi.no_rkm_medis where "
                + "pasien_bayi.no_skl like '%" + periodeNow + "%' ", "/RM-SKL/" + periodeNow, 4, NoSKL);
    }

    public void bukaDataBayi(String no_rm) {
        ChkInput.setSelected(true);
        isForm();

        NoRm.setText(no_rm);
        NmBayi.setText(Sequel.cariIsi("select nm_pasien from pasien where no_rkm_medis=?", no_rm));
        AlamatIbu.setText(Sequel.cariIsi("SELECT CONCAT(p.alamat,', ',kl.nm_kel,', ',kc.nm_kec,', ',kb.nm_kab) alamat "
                + "FROM pasien  p INNER JOIN kelurahan kl on kl.kd_kel = p.kd_kel "
                + "INNER JOIN kecamatan kc on kc.kd_kec =  p.kd_kec INNER JOIN kabupaten kb on kb.kd_kab = p. kd_kab "
                + "WHERE no_rkm_medis=?", no_rm));
        Nmibu.setText(Sequel.cariIsi("select nm_ibu from pasien where no_rkm_medis=?", no_rm));
        UmurIbu.setText("0");
        NmAyah.setText("-");
        UmurAyah.setText("0");
        Valid.SetTgl(Lahir, Sequel.cariIsi("select tgl_lahir from pasien where no_rkm_medis=?", no_rm));
        Valid.SetTgl(Daftar, Sequel.cariIsi("select tgl_daftar from pasien where no_rkm_medis=?", no_rm));
        KdPenolong.setText("PR10");
        NmPenolong.setText("PETUGAS RUANG PERINATOLOGI");

        if (Sequel.cariIsi("select jk from pasien where no_rkm_medis=?", no_rm).toString().equals("L")) {
            JKel.setSelectedItem("LAKI-LAKI");
        } else {
            JKel.setSelectedItem("PEREMPUAN");
        }
    }

    public void simpanData() {
        if (Sequel.cariIsi("select pasien.no_rkm_medis from pasien where pasien.no_rkm_medis=?", NoRm.getText()).isEmpty()) {
            Sequel.AutoComitFalse();
            Sequel.queryu2("insert into penjab values(?,?)", 2, new String[]{"-", "-"});
            Sequel.queryu2("insert into kelurahan values(?,?)", 2, new String[]{"0", "-"});
            Sequel.queryu2("insert into kecamatan values(?,?)", 2, new String[]{"0", "-"});
            Sequel.queryu2("insert into kabupaten values(?,?)", 2, new String[]{"0", "-"});
            if (Sequel.menyimpantf("pasien", "?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?", "No.Rekam Medis Pasien", 28, new String[]{
                NoRm.getText(), NmBayi.getText(), "-",
                JKel.getSelectedItem().toString().substring(0, 1), "-",
                Valid.SetTgl(Lahir.getSelectedItem() + ""),
                Nmibu.getText(), AlamatIbu.getText(), "-", "-", "BELUM MENIKAH", "-",
                Valid.SetTgl(Daftar.getSelectedItem() + ""), "0", UmurBayi.getText(),
                "-", "AYAH", NmAyah.getText(), "-", "-",
                Sequel.cariIsi("select kelurahan.kd_kel from kelurahan where kelurahan.nm_kel=?", "-"),
                Sequel.cariIsi("select kecamatan.kd_kec from kecamatan where kecamatan.nm_kec=?", "-"),
                Sequel.cariIsi("select kabupaten.kd_kab from kabupaten where kabupaten.nm_kab=?", "-"),
                "-", AlamatIbu.getText(), "-", "-", "-"}) == true) {
                if (Sequel.menyimpantf("pasien_bayi", "'" + NoRm.getText() + "','"
                        + UmurIbu.getText() + "','"
                        + NmAyah.getText() + "','"
                        + UmurAyah.getText() + "','"
                        + cmbBerat.getSelectedItem() + "','"
                        + Panjang.getText() + "','"
                        + LingkarKepala.getText() + "','"
                        + Proses.getText() + "','"
                        + Anakke.getText() + "','"
                        + jam.getSelectedItem() + ":" + menit.getSelectedItem() + ":" + detik.getSelectedItem() + "','"
                        + keterangan.getText() + "','" + Diagnosa.getText() + "','"
                        + PenyulitKehamilan.getText() + "','" + Ketuban.getText() + "','"
                        + LingkarPerut.getText() + "','" + LingkarDada.getText() + "','"
                        + KdPenolong.getText() + "','"
                        + NoSKL.getText() + "','"
                        + cmbMatiPerin.getSelectedItem() + "','"
                        + cmbSebabMati.getSelectedItem() + "','"
                        + cmbRujukan.getSelectedItem() + "','"
                        + cmbDirujuk.getSelectedItem() + "','"
                        + jns_alamat.getSelectedItem() + "','"
                        + cara_lahir.getSelectedItem() + "','"
                        + jns_penolong.getSelectedItem() + "','"
                        + menit1.getSelectedItem() + "','"
                        + menit5.getSelectedItem() + "','"
                        + menit10.getSelectedItem() + "','"
                        + cmbAsalBayi.getSelectedItem() + "','"
                        + umur_hamil.getSelectedItem() + "','"
                        + cmbRawat.getSelectedItem() + "','"
                        + cmbIMD.getSelectedItem() + "','"
                        + cmbKMC.getSelectedItem() + "','"
                        + benarberat.getText() + "','"
                        + norwtIbu + "'", "No.RM/No.SKL") == true) {
                    Sequel.queryu2("delete from set_no_rkm_medis");
                    Sequel.queryu2("insert into set_no_rkm_medis values(?)", 1, new String[]{NoRm.getText()});
                }
                Sequel.queryu2("delete from set_no_rkm_medis");
                Sequel.queryu2("insert into set_no_rkm_medis values(?)", 1, new String[]{NoRm.getText()});
                tampil();
                emptTeks();
            } else {
                //autoNomor();
                autoSKL();
                if (Sequel.menyimpantf("pasien", "?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?", "No.Rekam Medis Pasien", 28, new String[]{
                    NoRm.getText(), NmBayi.getText(), "-",
                    JKel.getSelectedItem().toString().substring(0, 1), "-",
                    Valid.SetTgl(Lahir.getSelectedItem() + ""),
                    Nmibu.getText(), AlamatIbu.getText(), "-", "-", "BELUM MENIKAH", "-",
                    Valid.SetTgl(Daftar.getSelectedItem() + ""), "0", UmurBayi.getText(),
                    "-", "AYAH", NmAyah.getText(), "-", "-",
                    Sequel.cariIsi("select kelurahan.kd_kel from kelurahan where kelurahan.nm_kel=?", "-"),
                    Sequel.cariIsi("select kecamatan.kd_kec from kecamatan where kecamatan.nm_kec=?", "-"),
                    Sequel.cariIsi("select kabupaten.kd_kab from kabupaten where kabupaten.nm_kab=?", "-"),
                    "-", AlamatIbu.getText(), "-", "-", "-"}) == true) {
                    if (Sequel.menyimpantf("pasien_bayi", "'" + NoRm.getText() + "','"
                            + UmurIbu.getText() + "','"
                            + NmAyah.getText() + "','"
                            + UmurAyah.getText() + "','"
                            + cmbBerat.getSelectedItem() + "','"
                            + Panjang.getText() + "','"
                            + LingkarKepala.getText() + "','"
                            + Proses.getText() + "','"
                            + Anakke.getText() + "','"
                            + jam.getSelectedItem() + ":" + menit.getSelectedItem() + ":" + detik.getSelectedItem() + "','"
                            + keterangan.getText() + "','" + Diagnosa.getText() + "','"
                            + PenyulitKehamilan.getText() + "','" + Ketuban.getText() + "','"
                            + LingkarPerut.getText() + "','" + LingkarDada.getText() + "','"
                            + KdPenolong.getText() + "','"
                            + NoSKL.getText() + "','"
                            + cmbMatiPerin.getSelectedItem() + "','"
                            + cmbSebabMati.getSelectedItem() + "','"
                            + cmbRujukan.getSelectedItem() + "','"
                            + cmbDirujuk.getSelectedItem() + "','"
                            + jns_alamat.getSelectedItem() + "','"
                            + cara_lahir.getSelectedItem() + "','"
                            + jns_penolong.getSelectedItem() + "','"
                            + menit1.getSelectedItem() + "','"
                            + menit5.getSelectedItem() + "','"
                            + menit10.getSelectedItem() + "','"
                            + cmbAsalBayi.getSelectedItem() + "','"
                            + umur_hamil.getSelectedItem() + "','"
                            + cmbRawat.getSelectedItem() + "','"
                            + cmbIMD.getSelectedItem() + "','"
                            + cmbKMC.getSelectedItem() + "','"
                            + benarberat.getText() + "','"
                            + norwtIbu + "'", "No.RM/No.SKL") == true) {
                        Sequel.queryu2("delete from set_no_rkm_medis");
                        Sequel.queryu2("insert into set_no_rkm_medis values(?)", 1, new String[]{NoRm.getText()});
                    }
                    Sequel.queryu2("delete from set_no_rkm_medis");
                    Sequel.queryu2("insert into set_no_rkm_medis values(?)", 1, new String[]{NoRm.getText()});

                    tampil();
                    emptTeks();
                } else {
                    //autoNomor();
                    autoSKL();
                    if (Sequel.menyimpantf("pasien", "?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?", "No.Rekam Medis Pasien", 28, new String[]{
                        NoRm.getText(), NmBayi.getText(), "-",
                        JKel.getSelectedItem().toString().substring(0, 1), "-",
                        Valid.SetTgl(Lahir.getSelectedItem() + ""),
                        Nmibu.getText(), AlamatIbu.getText(), "-", "-", "BELUM MENIKAH", "-",
                        Valid.SetTgl(Daftar.getSelectedItem() + ""), "0", UmurBayi.getText(),
                        "-", "AYAH", NmAyah.getText(), "-", "-",
                        Sequel.cariIsi("select kelurahan.kd_kel from kelurahan where kelurahan.nm_kel=?", "-"),
                        Sequel.cariIsi("select kecamatan.kd_kec from kecamatan where kecamatan.nm_kec=?", "-"),
                        Sequel.cariIsi("select kabupaten.kd_kab from kabupaten where kabupaten.nm_kab=?", "-"),
                        "-", AlamatIbu.getText(), "-", "-", "-"}) == true) {
                        if (Sequel.menyimpantf("pasien_bayi", "'" + NoRm.getText() + "','"
                                + UmurIbu.getText() + "','"
                                + NmAyah.getText() + "','"
                                + UmurAyah.getText() + "','"
                                + cmbBerat.getSelectedItem() + "','"
                                + Panjang.getText() + "','"
                                + LingkarKepala.getText() + "','"
                                + Proses.getText() + "','"
                                + Anakke.getText() + "','"
                                + jam.getSelectedItem() + ":" + menit.getSelectedItem() + ":" + detik.getSelectedItem() + "','"
                                + keterangan.getText() + "','" + Diagnosa.getText() + "','"
                                + PenyulitKehamilan.getText() + "','" + Ketuban.getText() + "','"
                                + LingkarPerut.getText() + "','" + LingkarDada.getText() + "','"
                                + KdPenolong.getText() + "','"
                                + NoSKL.getText() + "','"
                                + cmbMatiPerin.getSelectedItem() + "','"
                                + cmbSebabMati.getSelectedItem() + "','"
                                + cmbRujukan.getSelectedItem() + "','"
                                + cmbDirujuk.getSelectedItem() + "','"
                                + jns_alamat.getSelectedItem() + "','"
                                + cara_lahir.getSelectedItem() + "','"
                                + jns_penolong.getSelectedItem() + "','"
                                + menit1.getSelectedItem() + "','"
                                + menit5.getSelectedItem() + "','"
                                + menit10.getSelectedItem() + "','"
                                + cmbAsalBayi.getSelectedItem() + "','"
                                + umur_hamil.getSelectedItem() + "','"
                                + cmbRawat.getSelectedItem() + "','"
                                + cmbIMD.getSelectedItem() + "','"
                                + cmbKMC.getSelectedItem() + "','"
                                + benarberat.getText() + "','"
                                + norwtIbu + "'", "No.RM/No.SKL") == true) {
                            Sequel.queryu2("delete from set_no_rkm_medis");
                            Sequel.queryu2("insert into set_no_rkm_medis values(?)", 1, new String[]{NoRm.getText()});
                        }
                        Sequel.queryu2("delete from set_no_rkm_medis");
                        Sequel.queryu2("insert into set_no_rkm_medis values(?)", 1, new String[]{NoRm.getText()});

                        tampil();
                        emptTeks();
                    }
                }
            }
            Sequel.AutoComitTrue();
        } else {
            if (Sequel.menyimpantf("pasien_bayi", "'" + NoRm.getText() + "','"
                    + UmurIbu.getText() + "','"
                    + NmAyah.getText() + "','"
                    + UmurAyah.getText() + "','"
                    + cmbBerat.getSelectedItem() + "','"
                    + Panjang.getText() + "','"
                    + LingkarKepala.getText() + "','"
                    + Proses.getText() + "','"
                    + Anakke.getText() + "','"
                    + jam.getSelectedItem() + ":" + menit.getSelectedItem() + ":" + detik.getSelectedItem() + "','"
                    + keterangan.getText() + "','" + Diagnosa.getText() + "','"
                    + PenyulitKehamilan.getText() + "','" + Ketuban.getText() + "','"
                    + LingkarPerut.getText() + "','" + LingkarDada.getText() + "','"
                    + KdPenolong.getText() + "','"
                    + NoSKL.getText() + "','"
                    + cmbMatiPerin.getSelectedItem() + "','"
                    + cmbSebabMati.getSelectedItem() + "','"
                    + cmbRujukan.getSelectedItem() + "','"
                    + cmbDirujuk.getSelectedItem() + "','"
                    + jns_alamat.getSelectedItem() + "','"
                    + cara_lahir.getSelectedItem() + "','"
                    + jns_penolong.getSelectedItem() + "','"
                    + menit1.getSelectedItem() + "','"
                    + menit5.getSelectedItem() + "','"
                    + menit10.getSelectedItem() + "','"
                    + cmbAsalBayi.getSelectedItem() + "','"
                    + umur_hamil.getSelectedItem() + "','"
                    + cmbRawat.getSelectedItem() + "','"
                    + cmbIMD.getSelectedItem() + "','"
                    + cmbKMC.getSelectedItem() + "','"
                    + benarberat.getText() + "','"
                    + norwtIbu + "'", "No.RM/No.SKL") == true) {
                tampil();
                emptTeks();
            }
        }
    }
    
    private void tampilPersalinan() {
        Valid.tabelKosong(tabMode1);
        try {
            ps = koneksi.prepareStatement("SELECT DISTINCT rp.no_rawat, p.no_rkm_medis, p.nm_pasien, date_format(rp.tgl_registrasi,'%d-%m-%Y') tgl_msk FROM kamar_inap ki "
                    + "INNER JOIN kamar k on k.kd_kamar=ki.kd_kamar INNER JOIN bangsal b on b.kd_bangsal=k.kd_bangsal "
                    + "INNER JOIN reg_periksa rp on rp.no_rawat=ki.no_rawat INNER JOIN pasien p on p.no_rkm_medis=rp.no_rkm_medis where "
                    + "rp.tgl_registrasi BETWEEN ? and ? and (b.nm_bangsal LIKE '%obg%' or b.nm_bangsal LIKE '%intan%' or "
                    + "b.nm_bangsal LIKE '%iccu%' or b.nm_bangsal LIKE '%covid%') and p.jk='P' and rp.sttsumur='Th' and rp.no_rawat like ? or "
                    + "rp.tgl_registrasi BETWEEN ? and ? and (b.nm_bangsal LIKE '%obg%' or b.nm_bangsal LIKE '%intan%' or "
                    + "b.nm_bangsal LIKE '%iccu%' or b.nm_bangsal LIKE '%covid%') and p.jk='P' and rp.sttsumur='Th' and p.no_rkm_medis like ? or "
                    + "rp.tgl_registrasi BETWEEN ? and ? and (b.nm_bangsal LIKE '%obg%' or b.nm_bangsal LIKE '%intan%' or "
                    + "b.nm_bangsal LIKE '%iccu%' or b.nm_bangsal LIKE '%covid%') and p.jk='P' and rp.sttsumur='Th' and p.nm_pasien like ? order by p.nm_pasien");
            try {
                ps.setString(1, Valid.SetTgl(tgl1.getSelectedItem() + ""));
                ps.setString(2, Valid.SetTgl(tgl2.getSelectedItem() + ""));
                ps.setString(3, "%" + TCari1.getText().trim() + "%");
                ps.setString(4, Valid.SetTgl(tgl1.getSelectedItem() + ""));
                ps.setString(5, Valid.SetTgl(tgl2.getSelectedItem() + ""));
                ps.setString(6, "%" + TCari1.getText().trim() + "%");
                ps.setString(7, Valid.SetTgl(tgl1.getSelectedItem() + ""));
                ps.setString(8, Valid.SetTgl(tgl2.getSelectedItem() + ""));
                ps.setString(9, "%" + TCari1.getText().trim() + "%");
                rs = ps.executeQuery();
                while (rs.next()) {
                    tabMode1.addRow(new Object[]{
                        rs.getString("no_rawat"),
                        rs.getString("no_rkm_medis"),
                        rs.getString("nm_pasien"),
                        rs.getString("tgl_msk")
                    });
                }
                this.setCursor(Cursor.getDefaultCursor());
            } catch (Exception e) {
                System.out.println("simrskhanza.DlgIKBBayi.tampilPersalinan() : " + e);
            } finally {
                if (rs != null) {
                    rs.close();
                }
                if (ps != null) {
                    ps.close();
                }
            }
        } catch (SQLException e) {
            System.out.println("Notifikasi : " + e);
        }
        LCount1.setText("" + tabMode1.getRowCount());
    }
}
