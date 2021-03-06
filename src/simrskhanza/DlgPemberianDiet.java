package simrskhanza;

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
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import javax.swing.JOptionPane;
import javax.swing.JTable;
import javax.swing.event.DocumentEvent;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableColumn;
import laporan.DlgDataDietRanap;

/**
 *
 * @author dosen
 */
public class DlgPemberianDiet extends javax.swing.JDialog {
    private final DefaultTableModel tabMode, tabMode1, tabMode2, tabMode3 ;
    private Connection koneksi = koneksiDB.condb();
    private sekuel Sequel = new sekuel();
    private validasi Valid = new validasi();
    private PreparedStatement ps, ps2, ps3, ps4, pspasien, psPoli, psRDI, psRDJ;
    private ResultSet rs, rs1, rspasien, rsPoli, rsRDI, rsRDJ;
    public DlgCariPoli poli = new DlgCariPoli(null, false);
    private DlgCariDiet diet = new DlgCariDiet(null, false);
    private DlgCariJumlahPemberianDiet jlhberi = new DlgCariJumlahPemberianDiet(null, false);
    private DlgCariBangsal bangsal = new DlgCariBangsal(null, false);
    private int i = 0, x = 0, cekBonGZ = 0, cekDataKetepatanDiet = 0, cekDataLabel = 0;
    private String jnsRawat = "", kdUnit = "", kdPoli = "", tglDietAwal = "", waktuAwal = "", 
            nama_unit = "", nmHari = "", nmHari1 = "", nmDay = "", nmDay1 = "", dataDiet = "";

    /**
     * Creates new form DlgPemberianInfus
     *
     * @param parent
     * @param modal
     */
    public DlgPemberianDiet(java.awt.Frame parent, boolean modal) {
        super(parent, modal);
        initComponents();
        this.setLocation(10, 2);
        setSize(628, 674);

        Object[] row = {"No.Rawat", "Nama Pasien", "Kamar", "Tanggal", "Waktu", "Diet", "Diagnosa Awal"};
        tabMode = new DefaultTableModel(null, row) {
            @Override
            public boolean isCellEditable(int rowIndex, int colIndex) {
                return false;
            }
        };
        
        tbRanap.setModel(tabMode);
        tbRanap.setPreferredScrollableViewportSize(new Dimension(500, 500));
        tbRanap.setAutoResizeMode(JTable.AUTO_RESIZE_OFF);

        for (i = 0; i < 7; i++) {
            TableColumn column = tbRanap.getColumnModel().getColumn(i);
            if (i == 0) {
                column.setPreferredWidth(120);
            } else if (i == 1) {
                column.setPreferredWidth(200);
            } else if (i == 2) {
                column.setPreferredWidth(400);
            } else if (i == 3) {
                column.setPreferredWidth(90);
            } else if (i == 4) {
                column.setPreferredWidth(70);
            } else if (i == 5) {
                column.setPreferredWidth(120);
            } else if (i == 6) {
                column.setPreferredWidth(300);
            }
        }
        tbRanap.setDefaultRenderer(Object.class, new WarnaTable());

        tabMode1 = new DefaultTableModel(null, new Object[]{"No.Rawat", "Nama Pasien", "Poliklinik/Inst.", "Tanggal", "Waktu", "Diet", "Diagnosa Resume"}) {
            @Override
            public boolean isCellEditable(int rowIndex, int colIndex) {
                return false;
            }
        };
        tbRalan.setModel(tabMode1);

        tbRalan.setPreferredScrollableViewportSize(new Dimension(500, 500));
        tbRalan.setAutoResizeMode(JTable.AUTO_RESIZE_OFF);

        for (i = 0; i < 7; i++) {
            TableColumn column = tbRalan.getColumnModel().getColumn(i);
            if (i == 0) {
                column.setPreferredWidth(120);
            } else if (i == 1) {
                column.setPreferredWidth(200);
            } else if (i == 2) {
                column.setPreferredWidth(200);
            } else if (i == 3) {
                column.setPreferredWidth(90);
            } else if (i == 4) {
                column.setPreferredWidth(70);
            } else if (i == 5) {
                column.setPreferredWidth(160);
            } else if (i == 6) {
                column.setPreferredWidth(300);
            }
        }
        tbRalan.setDefaultRenderer(Object.class, new WarnaTable());
        
        tabMode2 = new DefaultTableModel(null, new Object[]{"Nama Poliklinik/Inst.", "Inisial", "kd_poli"}) {
            @Override
            public boolean isCellEditable(int rowIndex, int colIndex) {
                return false;
            }
        };
        
        tbPoli.setModel(tabMode2);
        tbPoli.setPreferredScrollableViewportSize(new Dimension(500, 500));
        tbPoli.setAutoResizeMode(JTable.AUTO_RESIZE_OFF);

        for (int i = 0; i < 3; i++) {
            TableColumn column = tbPoli.getColumnModel().getColumn(i);
            if (i == 0) {
                column.setPreferredWidth(270);
            } else if (i == 1) {
                column.setPreferredWidth(65);
            } else if (i == 2) {
//                column.setPreferredWidth(75);
                column.setMinWidth(0);
                column.setMaxWidth(0);
            }
        }
        tbPoli.setDefaultRenderer(Object.class, new WarnaTable());
        
        tabMode3 = new DefaultTableModel(null, new Object[]{"kd_diet","#", "Nama Diet", "Jns. Makanan","Jlh. Pemberian"}) {
            @Override
            public boolean isCellEditable(int rowIndex, int colIndex) {
                boolean a = false;
                if (colIndex == 1) {
                    a = true;
                }
                return a;
            }
            Class[] types = new Class[]{
                java.lang.Object.class, java.lang.Boolean.class, java.lang.Object.class,
                java.lang.Object.class, java.lang.Object.class
            };
            @Override
            public Class getColumnClass(int columnIndex) {
                return types [columnIndex];
             }
        };
        
        tbDiet.setModel(tabMode3);
        tbDiet.setPreferredScrollableViewportSize(new Dimension(500, 500));
        tbDiet.setAutoResizeMode(JTable.AUTO_RESIZE_OFF);

        for (int i = 0; i < 5; i++) {
            TableColumn column = tbDiet.getColumnModel().getColumn(i);
            if (i == 0) {
                column.setMinWidth(0);
                column.setMaxWidth(0);
            } else if (i == 1) {
                column.setPreferredWidth(30);
            } else if (i == 2) {
                column.setPreferredWidth(150);
            } else if (i == 3) {
                column.setPreferredWidth(140);
            } else if (i == 4) {
                column.setPreferredWidth(105);
            }
        }
        tbDiet.setDefaultRenderer(Object.class, new WarnaTable());

        TCari.setDocument(new batasInput((byte) 100).getKata(TCari));
        TCariPoli.setDocument(new batasInput((byte) 100).getKata(TCariPoli));
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
        
        diet.addWindowListener(new WindowListener() {
            @Override
            public void windowOpened(WindowEvent e) {
            }

            @Override
            public void windowClosing(WindowEvent e) {
            }

            @Override
            public void windowClosed(WindowEvent e) {
                if (var.getform().equals("DlgPemberianDiet")) {
                    if (diet.getTable().getSelectedRow() != -1) {
                        kddiet.setText(diet.getTable().getValueAt(diet.getTable().getSelectedRow(), 0).toString());
                        nmdiet.setText(diet.getTable().getValueAt(diet.getTable().getSelectedRow(), 1).toString());
                        jnsMakanan.setText(diet.getTable().getValueAt(diet.getTable().getSelectedRow(), 2).toString());
                        
                        if (nmdiet.getText().equals("F75") || nmdiet.getText().equals("F100") || nmdiet.getText().equals("F135")) {
                            kdberi.setText("-");
                            jumlahBeri.setText("");
                            jumlahBeri.setEditable(true);
                            cmbSatuan.setEnabled(true);
                            jumlahBeri.requestFocus();
                        } else {
                            jumlahBeri.setEditable(false);
                            cmbSatuan.setEnabled(false);
                            btnJumlahBeri.requestFocus();
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
        
        diet.getTable().addKeyListener(new KeyListener() {
            @Override
            public void keyTyped(KeyEvent e) {
            }

            @Override
            public void keyPressed(KeyEvent e) {
                if (var.getform().equals("DlgPemberianDiet")) {
                    if (e.getKeyCode() == KeyEvent.VK_SPACE) {
                        diet.dispose();
                    }
                }
            }

            @Override
            public void keyReleased(KeyEvent e) {
            }
        });
        
        jlhberi.addWindowListener(new WindowListener() {
            @Override
            public void windowOpened(WindowEvent e) {
            }

            @Override
            public void windowClosing(WindowEvent e) {
            }

            @Override
            public void windowClosed(WindowEvent e) {
                if (var.getform().equals("DlgPemberianDiet")) {
                    if (jlhberi.getTable().getSelectedRow() != -1) {
                        kdberi.setText(jlhberi.getTable().getValueAt(jlhberi.getTable().getSelectedRow(), 0).toString());
                        jumlahBeri.setText(jlhberi.getTable().getValueAt(jlhberi.getTable().getSelectedRow(), 1).toString());
                        cmbSatuan.setSelectedItem(jlhberi.getTable().getValueAt(jlhberi.getTable().getSelectedRow(), 2).toString());
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
        
        jlhberi.getTable().addKeyListener(new KeyListener() {
            @Override
            public void keyTyped(KeyEvent e) {
            }

            @Override
            public void keyPressed(KeyEvent e) {
                if (var.getform().equals("DlgPemberianDiet")) {
                    if (e.getKeyCode() == KeyEvent.VK_SPACE) {
                        jlhberi.dispose();
                    }
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
                if (var.getform().equals("DlgPemberianDiet")) {
                    if (poli.getTable().getSelectedRow() != -1) {
                        NmUnitCari.setText(poli.getTable().getValueAt(poli.getTable().getSelectedRow(), 1).toString());
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

        bangsal.addWindowListener(new WindowListener() {
            @Override
            public void windowOpened(WindowEvent e) {
            }

            @Override
            public void windowClosing(WindowEvent e) {
            }

            @Override
            public void windowClosed(WindowEvent e) {
                if (var.getform().equals("DlgPemberianDiet")) {
                    if (bangsal.getTable().getSelectedRow() != -1) {
                        NmUnitCari.setText(bangsal.getTable().getValueAt(bangsal.getTable().getSelectedRow(), 1).toString());
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

        try {
            ps = koneksi.prepareStatement("select detail_beri_diet.no_rawat,reg_periksa.no_rkm_medis,pasien.nm_pasien, "
                    + "concat(detail_beri_diet.kd_kamar,', ',bangsal.nm_bangsal),detail_beri_diet.tanggal,detail_beri_diet.waktu,diet.nama_diet "
                    + "from detail_beri_diet inner join reg_periksa inner join pasien inner join diet inner join kamar inner join bangsal "
                    + "on detail_beri_diet.no_rawat=reg_periksa.no_rawat "
                    + "and detail_beri_diet.kd_kamar=kamar.kd_kamar "
                    + "and kamar.kd_bangsal=bangsal.kd_bangsal "
                    + "and reg_periksa.no_rkm_medis=pasien.no_rkm_medis "
                    + "and detail_beri_diet.kd_diet=diet.kd_diet where "
                    + "detail_beri_diet.tanggal between ? and ? and detail_beri_diet.waktu like ? and bangsal.nm_bangsal like ? and detail_beri_diet.no_rawat like ? or "
                    + "detail_beri_diet.tanggal between ? and ? and detail_beri_diet.waktu like ? and bangsal.nm_bangsal like ? and reg_periksa.no_rkm_medis like ? or "
                    + "detail_beri_diet.tanggal between ? and ? and detail_beri_diet.waktu like ? and bangsal.nm_bangsal like ? and concat(detail_beri_diet.kd_kamar,', ',bangsal.nm_bangsal) like ? or "
                    + "detail_beri_diet.tanggal between ? and ? and detail_beri_diet.waktu like ? and bangsal.nm_bangsal like ? and pasien.nm_pasien like ? "
                    + "order by bangsal.nm_bangsal,diet.nama_diet");

            ps2 = koneksi.prepareStatement("select diet.nama_diet, count(diet.nama_diet) as jumlah "
                    + "from detail_beri_diet inner join reg_periksa inner join pasien inner join diet inner join kamar inner join bangsal "
                    + "on detail_beri_diet.no_rawat=reg_periksa.no_rawat "
                    + "and detail_beri_diet.kd_kamar=kamar.kd_kamar "
                    + "and kamar.kd_bangsal=bangsal.kd_bangsal "
                    + "and reg_periksa.no_rkm_medis=pasien.no_rkm_medis "
                    + "and detail_beri_diet.kd_diet=diet.kd_diet where "
                    + "detail_beri_diet.tanggal between ? and ? and detail_beri_diet.waktu like ? and bangsal.nm_bangsal like ? and detail_beri_diet.no_rawat like ? or "
                    + "detail_beri_diet.tanggal between ? and ? and detail_beri_diet.waktu like ? and bangsal.nm_bangsal like ? and reg_periksa.no_rkm_medis like ? or "
                    + "detail_beri_diet.tanggal between ? and ? and detail_beri_diet.waktu like ? and bangsal.nm_bangsal like ? and concat(detail_beri_diet.kd_kamar,', ',bangsal.nm_bangsal) like ? or "
                    + "detail_beri_diet.tanggal between ? and ? and detail_beri_diet.waktu like ? and bangsal.nm_bangsal like ? and pasien.nm_pasien like ? "
                    + "group by diet.nama_diet order by bangsal.nm_bangsal,diet.nama_diet");

            ps3 = koneksi.prepareStatement("SELECT dd.no_rawat, rp.no_rkm_medis, p.nm_pasien, pl.nm_poli, "
                    + "dd.tanggal, dd.waktu, d.nama_diet, lower(IFNULL(pr.diagnosa, '-')) diagnosa FROM detail_beri_diet_ralan dd "
                    + "INNER JOIN reg_periksa rp on rp.no_rawat=dd.no_rawat INNER JOIN pasien p on p.no_rkm_medis=rp.no_rkm_medis "
                    + "INNER JOIN diet d on d.kd_diet=dd.kd_diet INNER JOIN poliklinik pl on pl.kd_poli=dd.kd_poli "
                    + "LEFT JOIN pemeriksaan_ralan pr ON pr.no_rawat = rp.no_rawat where "
                    + "rp.status_lanjut = 'ralan' and dd.tanggal BETWEEN ? AND ? AND dd.waktu LIKE ? AND pl.nm_poli LIKE ? AND dd.no_rawat LIKE ? or "
                    + "rp.status_lanjut = 'ralan' and dd.tanggal BETWEEN ? AND ? AND dd.waktu LIKE ? AND pl.nm_poli LIKE ? AND rp.no_rkm_medis LIKE ? or "
                    + "rp.status_lanjut = 'ralan' and dd.tanggal BETWEEN ? AND ? AND dd.waktu LIKE ? AND pl.nm_poli LIKE ? AND p.nm_pasien LIKE ? "
                    + "ORDER BY pl.nm_poli, d.nama_diet");

            ps4 = koneksi.prepareStatement("SELECT d.nama_diet, count(d.nama_diet) AS jumlah FROM detail_beri_diet_ralan dd "
                    + "INNER JOIN reg_periksa rp on rp.no_rawat=dd.no_rawat INNER JOIN pasien p on p.no_rkm_medis=rp.no_rkm_medis "
                    + "INNER JOIN diet d on d.kd_diet=dd.kd_diet INNER JOIN poliklinik pl on pl.kd_poli=dd.kd_poli "
                    + "WHERE dd.tanggal BETWEEN ? AND ? AND dd.waktu LIKE ? AND pl.nm_poli LIKE ? AND dd.no_rawat LIKE ? OR "
                    + "dd.tanggal BETWEEN ? AND ? AND dd.waktu LIKE ? AND pl.nm_poli LIKE ? AND rp.no_rkm_medis LIKE ? OR "
                    + "dd.tanggal BETWEEN ? AND ? AND dd.waktu LIKE ? AND pl.nm_poli LIKE ? AND p.nm_pasien LIKE ? "
                    + "GROUP BY d.nama_diet ORDER BY pl.nm_poli, d.nama_diet");

        } catch (Exception e) {
            System.out.println("Notifikasi : " + e);
        }
        
        ChkInput.setSelected(false);
        isForm();
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jPopupMenu1 = new javax.swing.JPopupMenu();
        MnDataDietRanap = new javax.swing.JMenuItem();
        MnDataDietRalan = new javax.swing.JMenuItem();
        WindowLabelGiziRALAN = new javax.swing.JDialog();
        internalFrame15 = new widget.InternalFrame();
        panelGlass13 = new widget.panelisi();
        jLabel97 = new widget.Label();
        inisial = new widget.TextBox();
        jLabel100 = new widget.Label();
        tglKun = new widget.Tanggal();
        jLabel99 = new widget.Label();
        tglDiet = new widget.Tanggal();
        internalFrame4 = new widget.InternalFrame();
        Scroll4 = new widget.ScrollPane();
        tbPoli = new widget.Table();
        panelGlass14 = new widget.panelisi();
        jLabel102 = new widget.Label();
        TCariPoli = new widget.TextBox();
        BtnCari4 = new widget.Button();
        BtnAll4 = new widget.Button();
        panelGlass15 = new widget.panelisi();
        jLabel103 = new widget.Label();
        cmbPrin = new widget.ComboBox();
        BtnLabelGZ = new widget.Button();
        BtnBatal1 = new widget.Button();
        BtnCloseIn10 = new widget.Button();
        WindowDataDiet = new javax.swing.JDialog();
        internalFrame9 = new widget.InternalFrame();
        BtnCloseIn3 = new widget.Button();
        nmdiet = new widget.TextBox();
        kddiet = new widget.TextBox();
        jLabel46 = new widget.Label();
        jnsMakanan = new widget.TextBox();
        BtnSimpan2 = new widget.Button();
        jLabel47 = new widget.Label();
        kdberi = new widget.TextBox();
        jumlahBeri = new widget.TextBox();
        cmbSatuan = new widget.ComboBox();
        btnDiet = new widget.Button();
        btnJumlahBeri = new widget.Button();
        jLabel48 = new widget.Label();
        TKd = new widget.TextBox();
        internalFrame1 = new widget.InternalFrame();
        jPanel3 = new javax.swing.JPanel();
        panelGlass8 = new widget.panelisi();
        BtnSimpan = new widget.Button();
        BtnBatal = new widget.Button();
        BtnHapus = new widget.Button();
        BtnEdit = new widget.Button();
        BtnAll = new widget.Button();
        jLabel7 = new widget.Label();
        LCount = new widget.Label();
        BtnKeluar = new widget.Button();
        panelGlass9 = new widget.panelisi();
        jLabel11 = new widget.Label();
        cmbJamCari = new widget.ComboBox();
        labelUnit = new widget.Label();
        NmUnitCari = new widget.TextBox();
        BtnSeek2 = new widget.Button();
        panelGlass10 = new widget.panelisi();
        jLabel19 = new widget.Label();
        DTPCari1 = new widget.Tanggal();
        jLabel21 = new widget.Label();
        DTPCari2 = new widget.Tanggal();
        jLabel6 = new widget.Label();
        TCari = new widget.TextBox();
        BtnCari = new widget.Button();
        PanelInput = new javax.swing.JPanel();
        ChkInput = new widget.CekBox();
        FormInput = new widget.PanelBiasa();
        TNoRw = new widget.TextBox();
        jLabel9 = new widget.Label();
        TPasien = new widget.TextBox();
        DTPTgl = new widget.Tanggal();
        jLabel10 = new widget.Label();
        BtnSeek1 = new widget.Button();
        nmUnit = new widget.TextBox();
        norm = new widget.TextBox();
        jLabel5 = new widget.Label();
        jLabel8 = new widget.Label();
        jLabel13 = new widget.Label();
        jLabel14 = new widget.Label();
        TtglLahir = new widget.TextBox();
        cekWaktu = new widget.CekBox();
        cekDietPagi = new widget.CekBox();
        cekDietSiang = new widget.CekBox();
        cekDietSore = new widget.CekBox();
        Scroll5 = new widget.ScrollPane();
        tbDiet = new widget.Table();
        BtnSeek3 = new widget.Button();
        TabDiet = new javax.swing.JTabbedPane();
        internalFrame2 = new widget.InternalFrame();
        Scroll = new widget.ScrollPane();
        tbRanap = new widget.Table();
        internalFrame3 = new widget.InternalFrame();
        ScrollRalan = new widget.ScrollPane();
        tbRalan = new widget.Table();

        jPopupMenu1.setName("jPopupMenu1"); // NOI18N

        MnDataDietRanap.setBackground(new java.awt.Color(255, 255, 255));
        MnDataDietRanap.setFont(new java.awt.Font("Tahoma", 0, 11)); // NOI18N
        MnDataDietRanap.setIcon(new javax.swing.ImageIcon(getClass().getResource("/picture/category.png"))); // NOI18N
        MnDataDietRanap.setText("Cetak Data Diet Makanan (R.INAP)");
        MnDataDietRanap.setName("MnDataDietRanap"); // NOI18N
        MnDataDietRanap.setPreferredSize(new java.awt.Dimension(240, 26));
        MnDataDietRanap.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                MnDataDietRanapActionPerformed(evt);
            }
        });
        jPopupMenu1.add(MnDataDietRanap);

        MnDataDietRalan.setBackground(new java.awt.Color(255, 255, 255));
        MnDataDietRalan.setFont(new java.awt.Font("Tahoma", 0, 11)); // NOI18N
        MnDataDietRalan.setIcon(new javax.swing.ImageIcon(getClass().getResource("/picture/category.png"))); // NOI18N
        MnDataDietRalan.setText("Cetak Data Diet Makanan (R.JALAN)");
        MnDataDietRalan.setName("MnDataDietRalan"); // NOI18N
        MnDataDietRalan.setPreferredSize(new java.awt.Dimension(240, 26));
        MnDataDietRalan.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                MnDataDietRalanActionPerformed(evt);
            }
        });
        jPopupMenu1.add(MnDataDietRalan);

        WindowLabelGiziRALAN.setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);
        WindowLabelGiziRALAN.setName("WindowLabelGiziRALAN"); // NOI18N
        WindowLabelGiziRALAN.setUndecorated(true);
        WindowLabelGiziRALAN.setResizable(false);

        internalFrame15.setBorder(javax.swing.BorderFactory.createTitledBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 153, 255), 3), "::[ Cetak Data Diet Makanan Pasien ]::", javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Tahoma", 1, 12))); // NOI18N
        internalFrame15.setName("internalFrame15"); // NOI18N
        internalFrame15.setWarnaBawah(new java.awt.Color(245, 250, 240));
        internalFrame15.setLayout(null);

        panelGlass13.setBorder(javax.swing.BorderFactory.createTitledBorder(null, "", javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Tahoma", 0, 12))); // NOI18N
        panelGlass13.setName("panelGlass13"); // NOI18N
        panelGlass13.setPreferredSize(new java.awt.Dimension(44, 44));
        panelGlass13.setLayout(new java.awt.FlowLayout(java.awt.FlowLayout.LEFT));

        jLabel97.setForeground(new java.awt.Color(0, 0, 0));
        jLabel97.setText("Inisial Poli/Inst. :");
        jLabel97.setName("jLabel97"); // NOI18N
        jLabel97.setPreferredSize(new java.awt.Dimension(83, 20));
        panelGlass13.add(jLabel97);

        inisial.setEditable(false);
        inisial.setForeground(new java.awt.Color(0, 0, 0));
        inisial.setHighlighter(null);
        inisial.setName("inisial"); // NOI18N
        inisial.setPreferredSize(new java.awt.Dimension(50, 24));
        panelGlass13.add(inisial);

        jLabel100.setForeground(new java.awt.Color(0, 0, 0));
        jLabel100.setText("Tgl. Kunjungan : ");
        jLabel100.setName("jLabel100"); // NOI18N
        jLabel100.setPreferredSize(new java.awt.Dimension(95, 20));
        panelGlass13.add(jLabel100);

        tglKun.setEditable(false);
        tglKun.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "24-12-2021" }));
        tglKun.setDisplayFormat("dd-MM-yyyy");
        tglKun.setName("tglKun"); // NOI18N
        tglKun.setOpaque(false);
        tglKun.setPreferredSize(new java.awt.Dimension(95, 23));
        panelGlass13.add(tglKun);

        jLabel99.setForeground(new java.awt.Color(0, 0, 0));
        jLabel99.setText("Tgl. Beri Diet :");
        jLabel99.setName("jLabel99"); // NOI18N
        jLabel99.setPreferredSize(new java.awt.Dimension(80, 20));
        panelGlass13.add(jLabel99);

        tglDiet.setEditable(false);
        tglDiet.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "24-12-2021" }));
        tglDiet.setDisplayFormat("dd-MM-yyyy");
        tglDiet.setName("tglDiet"); // NOI18N
        tglDiet.setOpaque(false);
        tglDiet.setPreferredSize(new java.awt.Dimension(95, 23));
        panelGlass13.add(tglDiet);

        internalFrame15.add(panelGlass13);
        panelGlass13.setBounds(7, 250, 580, 36);

        internalFrame4.setBorder(javax.swing.BorderFactory.createTitledBorder(null, ".: Poliklinik / Instalasi :.", javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Tahoma", 0, 12), new java.awt.Color(0, 0, 0))); // NOI18N
        internalFrame4.setName("internalFrame4"); // NOI18N
        internalFrame4.setLayout(new java.awt.BorderLayout());

        Scroll4.setName("Scroll4"); // NOI18N
        Scroll4.setOpaque(true);
        Scroll4.setPreferredSize(new java.awt.Dimension(462, 210));

        tbPoli.setAutoCreateRowSorter(true);
        tbPoli.setToolTipText("Silahkan klik untuk memilih data yang mau diedit ataupun dihapus");
        tbPoli.setName("tbPoli"); // NOI18N
        tbPoli.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                tbPoliMouseClicked(evt);
            }
        });
        tbPoli.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                tbPoliKeyPressed(evt);
            }
        });
        Scroll4.setViewportView(tbPoli);

        internalFrame4.add(Scroll4, java.awt.BorderLayout.CENTER);

        internalFrame15.add(internalFrame4);
        internalFrame4.setBounds(5, 20, 584, 232);
        internalFrame4.getAccessibleContext().setAccessibleName(".: Poliklinik/Instalasi :.");

        panelGlass14.setBorder(javax.swing.BorderFactory.createTitledBorder(null, "", javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Tahoma", 0, 12))); // NOI18N
        panelGlass14.setName("panelGlass14"); // NOI18N
        panelGlass14.setPreferredSize(new java.awt.Dimension(44, 44));
        panelGlass14.setLayout(new java.awt.FlowLayout(java.awt.FlowLayout.LEFT));

        jLabel102.setForeground(new java.awt.Color(0, 0, 0));
        jLabel102.setText("Key Word :");
        jLabel102.setName("jLabel102"); // NOI18N
        jLabel102.setPreferredSize(new java.awt.Dimension(60, 20));
        panelGlass14.add(jLabel102);

        TCariPoli.setForeground(new java.awt.Color(0, 0, 0));
        TCariPoli.setHighlighter(null);
        TCariPoli.setName("TCariPoli"); // NOI18N
        TCariPoli.setPreferredSize(new java.awt.Dimension(250, 24));
        TCariPoli.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                TCariPoliKeyPressed(evt);
            }
        });
        panelGlass14.add(TCariPoli);

        BtnCari4.setBorder(null);
        BtnCari4.setForeground(new java.awt.Color(0, 0, 0));
        BtnCari4.setIcon(new javax.swing.ImageIcon(getClass().getResource("/picture/accept.png"))); // NOI18N
        BtnCari4.setMnemonic('2');
        BtnCari4.setText("Tampilkan Data");
        BtnCari4.setName("BtnCari4"); // NOI18N
        BtnCari4.setPreferredSize(new java.awt.Dimension(130, 23));
        BtnCari4.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                BtnCari4ActionPerformed(evt);
            }
        });
        BtnCari4.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                BtnCari4KeyPressed(evt);
            }
        });
        panelGlass14.add(BtnCari4);

        BtnAll4.setForeground(new java.awt.Color(0, 0, 0));
        BtnAll4.setIcon(new javax.swing.ImageIcon(getClass().getResource("/picture/Search-16x16.png"))); // NOI18N
        BtnAll4.setMnemonic('M');
        BtnAll4.setText("Semua Data");
        BtnAll4.setToolTipText("Alt+M");
        BtnAll4.setName("BtnAll4"); // NOI18N
        BtnAll4.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                BtnAll4ActionPerformed(evt);
            }
        });
        panelGlass14.add(BtnAll4);

        internalFrame15.add(panelGlass14);
        panelGlass14.setBounds(7, 286, 580, 36);

        panelGlass15.setBorder(javax.swing.BorderFactory.createTitledBorder(null, "", javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Tahoma", 0, 12))); // NOI18N
        panelGlass15.setName("panelGlass15"); // NOI18N
        panelGlass15.setPreferredSize(new java.awt.Dimension(44, 44));
        panelGlass15.setLayout(new java.awt.FlowLayout(java.awt.FlowLayout.LEFT));

        jLabel103.setForeground(new java.awt.Color(0, 0, 0));
        jLabel103.setText("Yang Akan Diprint :");
        jLabel103.setName("jLabel103"); // NOI18N
        jLabel103.setPreferredSize(new java.awt.Dimension(105, 20));
        panelGlass15.add(jLabel103);

        cmbPrin.setForeground(new java.awt.Color(0, 0, 0));
        cmbPrin.setModel(new javax.swing.DefaultComboBoxModel(new String[] { " ", "Bon Diet", "Form Ketepatan", "Label Diet" }));
        cmbPrin.setName("cmbPrin"); // NOI18N
        cmbPrin.setOpaque(false);
        cmbPrin.setPreferredSize(new java.awt.Dimension(120, 23));
        panelGlass15.add(cmbPrin);

        BtnLabelGZ.setForeground(new java.awt.Color(0, 0, 0));
        BtnLabelGZ.setIcon(new javax.swing.ImageIcon(getClass().getResource("/picture/b_print.png"))); // NOI18N
        BtnLabelGZ.setMnemonic('C');
        BtnLabelGZ.setText("Cetak");
        BtnLabelGZ.setToolTipText("Alt+C");
        BtnLabelGZ.setName("BtnLabelGZ"); // NOI18N
        BtnLabelGZ.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                BtnLabelGZActionPerformed(evt);
            }
        });
        panelGlass15.add(BtnLabelGZ);

        BtnBatal1.setBorder(null);
        BtnBatal1.setForeground(new java.awt.Color(0, 0, 0));
        BtnBatal1.setIcon(new javax.swing.ImageIcon(getClass().getResource("/picture/Cancel-2-16x16.png"))); // NOI18N
        BtnBatal1.setMnemonic('B');
        BtnBatal1.setText("Baru");
        BtnBatal1.setToolTipText("Alt+B");
        BtnBatal1.setName("BtnBatal1"); // NOI18N
        BtnBatal1.setPreferredSize(new java.awt.Dimension(65, 26));
        BtnBatal1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                BtnBatal1ActionPerformed(evt);
            }
        });
        panelGlass15.add(BtnBatal1);

        BtnCloseIn10.setForeground(new java.awt.Color(0, 0, 0));
        BtnCloseIn10.setIcon(new javax.swing.ImageIcon(getClass().getResource("/picture/cross.png"))); // NOI18N
        BtnCloseIn10.setMnemonic('U');
        BtnCloseIn10.setText("Keluar");
        BtnCloseIn10.setToolTipText("Alt+U");
        BtnCloseIn10.setName("BtnCloseIn10"); // NOI18N
        BtnCloseIn10.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                BtnCloseIn10ActionPerformed(evt);
            }
        });
        panelGlass15.add(BtnCloseIn10);

        internalFrame15.add(panelGlass15);
        panelGlass15.setBounds(7, 322, 580, 40);

        WindowLabelGiziRALAN.getContentPane().add(internalFrame15, java.awt.BorderLayout.CENTER);

        WindowDataDiet.setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);
        WindowDataDiet.setName("WindowDataDiet"); // NOI18N
        WindowDataDiet.setUndecorated(true);
        WindowDataDiet.setResizable(false);

        internalFrame9.setBorder(javax.swing.BorderFactory.createTitledBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 153, 255), 3), "::[ Pilihan Diet ]::", javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Tahoma", 1, 12))); // NOI18N
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
        internalFrame9.add(BtnCloseIn3);
        BtnCloseIn3.setBounds(350, 110, 80, 30);

        nmdiet.setEditable(false);
        nmdiet.setForeground(new java.awt.Color(0, 0, 0));
        nmdiet.setName("nmdiet"); // NOI18N
        internalFrame9.add(nmdiet);
        nmdiet.setBounds(207, 20, 200, 23);

        kddiet.setEditable(false);
        kddiet.setForeground(new java.awt.Color(0, 0, 0));
        kddiet.setName("kddiet"); // NOI18N
        internalFrame9.add(kddiet);
        kddiet.setBounds(124, 20, 80, 23);

        jLabel46.setForeground(new java.awt.Color(0, 0, 0));
        jLabel46.setText("Jenis Makanan :");
        jLabel46.setName("jLabel46"); // NOI18N
        internalFrame9.add(jLabel46);
        jLabel46.setBounds(10, 47, 110, 23);

        jnsMakanan.setEditable(false);
        jnsMakanan.setForeground(new java.awt.Color(0, 0, 0));
        jnsMakanan.setName("jnsMakanan"); // NOI18N
        internalFrame9.add(jnsMakanan);
        jnsMakanan.setBounds(124, 47, 283, 23);

        BtnSimpan2.setForeground(new java.awt.Color(0, 0, 0));
        BtnSimpan2.setIcon(new javax.swing.ImageIcon(getClass().getResource("/picture/save-16x16.png"))); // NOI18N
        BtnSimpan2.setMnemonic('S');
        BtnSimpan2.setText("Simpan");
        BtnSimpan2.setToolTipText("Alt+S");
        BtnSimpan2.setName("BtnSimpan2"); // NOI18N
        BtnSimpan2.setPreferredSize(new java.awt.Dimension(100, 30));
        BtnSimpan2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                BtnSimpan2ActionPerformed(evt);
            }
        });
        internalFrame9.add(BtnSimpan2);
        BtnSimpan2.setBounds(250, 110, 90, 30);

        jLabel47.setForeground(new java.awt.Color(0, 0, 0));
        jLabel47.setText("Jumlah Pemberian :");
        jLabel47.setName("jLabel47"); // NOI18N
        internalFrame9.add(jLabel47);
        jLabel47.setBounds(10, 74, 110, 23);

        kdberi.setEditable(false);
        kdberi.setForeground(new java.awt.Color(0, 0, 0));
        kdberi.setName("kdberi"); // NOI18N
        internalFrame9.add(kdberi);
        kdberi.setBounds(124, 74, 80, 23);

        jumlahBeri.setEditable(false);
        jumlahBeri.setForeground(new java.awt.Color(0, 0, 0));
        jumlahBeri.setName("jumlahBeri"); // NOI18N
        internalFrame9.add(jumlahBeri);
        jumlahBeri.setBounds(207, 74, 130, 23);

        cmbSatuan.setForeground(new java.awt.Color(0, 0, 0));
        cmbSatuan.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "-" }));
        cmbSatuan.setName("cmbSatuan"); // NOI18N
        cmbSatuan.setPreferredSize(new java.awt.Dimension(105, 23));
        internalFrame9.add(cmbSatuan);
        cmbSatuan.setBounds(342, 74, 65, 23);

        btnDiet.setForeground(new java.awt.Color(0, 0, 0));
        btnDiet.setIcon(new javax.swing.ImageIcon(getClass().getResource("/picture/190.png"))); // NOI18N
        btnDiet.setMnemonic('X');
        btnDiet.setToolTipText("Alt+X");
        btnDiet.setName("btnDiet"); // NOI18N
        btnDiet.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnDietActionPerformed(evt);
            }
        });
        internalFrame9.add(btnDiet);
        btnDiet.setBounds(410, 20, 28, 23);

        btnJumlahBeri.setForeground(new java.awt.Color(0, 0, 0));
        btnJumlahBeri.setIcon(new javax.swing.ImageIcon(getClass().getResource("/picture/190.png"))); // NOI18N
        btnJumlahBeri.setMnemonic('X');
        btnJumlahBeri.setToolTipText("Alt+X");
        btnJumlahBeri.setName("btnJumlahBeri"); // NOI18N
        btnJumlahBeri.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnJumlahBeriActionPerformed(evt);
            }
        });
        internalFrame9.add(btnJumlahBeri);
        btnJumlahBeri.setBounds(410, 74, 28, 23);

        jLabel48.setForeground(new java.awt.Color(0, 0, 0));
        jLabel48.setText("Nama Diet : ");
        jLabel48.setName("jLabel48"); // NOI18N
        internalFrame9.add(jLabel48);
        jLabel48.setBounds(10, 20, 110, 23);

        WindowDataDiet.getContentPane().add(internalFrame9, java.awt.BorderLayout.CENTER);

        TKd.setEditable(false);
        TKd.setForeground(new java.awt.Color(0, 0, 0));
        TKd.setName("TKd"); // NOI18N

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);
        setUndecorated(true);
        setResizable(false);
        addWindowListener(new java.awt.event.WindowAdapter() {
            public void windowOpened(java.awt.event.WindowEvent evt) {
                formWindowOpened(evt);
            }
        });

        internalFrame1.setBorder(javax.swing.BorderFactory.createTitledBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 153, 255), 3), "::[ Diet Harian Pasien ]::", javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Tahoma", 1, 12))); // NOI18N
        internalFrame1.setName("internalFrame1"); // NOI18N
        internalFrame1.setLayout(new java.awt.BorderLayout(1, 1));

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
        BtnAll.setText("Semua");
        BtnAll.setToolTipText("Alt+M");
        BtnAll.setName("BtnAll"); // NOI18N
        BtnAll.setPreferredSize(new java.awt.Dimension(100, 30));
        BtnAll.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                BtnAllActionPerformed(evt);
            }
        });
        panelGlass8.add(BtnAll);

        jLabel7.setForeground(new java.awt.Color(0, 0, 0));
        jLabel7.setText("Record :");
        jLabel7.setName("jLabel7"); // NOI18N
        jLabel7.setPreferredSize(new java.awt.Dimension(65, 23));
        panelGlass8.add(jLabel7);

        LCount.setForeground(new java.awt.Color(0, 0, 0));
        LCount.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        LCount.setText("0");
        LCount.setName("LCount"); // NOI18N
        LCount.setPreferredSize(new java.awt.Dimension(50, 23));
        panelGlass8.add(LCount);

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

        jPanel3.add(panelGlass8, java.awt.BorderLayout.PAGE_END);

        panelGlass9.setName("panelGlass9"); // NOI18N
        panelGlass9.setPreferredSize(new java.awt.Dimension(44, 44));
        panelGlass9.setLayout(new java.awt.FlowLayout(java.awt.FlowLayout.LEFT, 5, 9));

        jLabel11.setForeground(new java.awt.Color(0, 0, 0));
        jLabel11.setText("Waktu Diet :");
        jLabel11.setName("jLabel11"); // NOI18N
        jLabel11.setPreferredSize(new java.awt.Dimension(67, 23));
        panelGlass9.add(jLabel11);

        cmbJamCari.setForeground(new java.awt.Color(0, 0, 0));
        cmbJamCari.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "Semua", "Pagi", "Siang", "Sore", "Malam" }));
        cmbJamCari.setName("cmbJamCari"); // NOI18N
        cmbJamCari.setOpaque(false);
        cmbJamCari.setPreferredSize(new java.awt.Dimension(100, 23));
        cmbJamCari.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                cmbJamCariMouseClicked(evt);
            }
        });
        cmbJamCari.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                cmbJamCariKeyPressed(evt);
            }
        });
        panelGlass9.add(cmbJamCari);

        labelUnit.setForeground(new java.awt.Color(0, 0, 0));
        labelUnit.setText("labelUnit");
        labelUnit.setName("labelUnit"); // NOI18N
        labelUnit.setPreferredSize(new java.awt.Dimension(135, 23));
        panelGlass9.add(labelUnit);

        NmUnitCari.setEditable(false);
        NmUnitCari.setForeground(new java.awt.Color(0, 0, 0));
        NmUnitCari.setName("NmUnitCari"); // NOI18N
        NmUnitCari.setPreferredSize(new java.awt.Dimension(310, 23));
        panelGlass9.add(NmUnitCari);

        BtnSeek2.setForeground(new java.awt.Color(0, 0, 0));
        BtnSeek2.setIcon(new javax.swing.ImageIcon(getClass().getResource("/picture/190.png"))); // NOI18N
        BtnSeek2.setMnemonic('X');
        BtnSeek2.setToolTipText("Alt+X");
        BtnSeek2.setName("BtnSeek2"); // NOI18N
        BtnSeek2.setPreferredSize(new java.awt.Dimension(28, 23));
        BtnSeek2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                BtnSeek2ActionPerformed(evt);
            }
        });
        BtnSeek2.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                BtnSeek2KeyPressed(evt);
            }
        });
        panelGlass9.add(BtnSeek2);

        jPanel3.add(panelGlass9, java.awt.BorderLayout.PAGE_START);

        panelGlass10.setName("panelGlass10"); // NOI18N
        panelGlass10.setPreferredSize(new java.awt.Dimension(44, 44));
        panelGlass10.setLayout(new java.awt.FlowLayout(java.awt.FlowLayout.LEFT, 5, 9));

        jLabel19.setForeground(new java.awt.Color(0, 0, 0));
        jLabel19.setText("Tgl.Rawat :");
        jLabel19.setName("jLabel19"); // NOI18N
        jLabel19.setPreferredSize(new java.awt.Dimension(67, 23));
        panelGlass10.add(jLabel19);

        DTPCari1.setEditable(false);
        DTPCari1.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "24-12-2021" }));
        DTPCari1.setDisplayFormat("dd-MM-yyyy");
        DTPCari1.setName("DTPCari1"); // NOI18N
        DTPCari1.setOpaque(false);
        DTPCari1.setPreferredSize(new java.awt.Dimension(100, 23));
        DTPCari1.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                DTPCari1MouseClicked(evt);
            }
        });
        panelGlass10.add(DTPCari1);

        jLabel21.setForeground(new java.awt.Color(0, 0, 0));
        jLabel21.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel21.setText("s.d.");
        jLabel21.setName("jLabel21"); // NOI18N
        jLabel21.setPreferredSize(new java.awt.Dimension(23, 23));
        panelGlass10.add(jLabel21);

        DTPCari2.setEditable(false);
        DTPCari2.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "24-12-2021" }));
        DTPCari2.setDisplayFormat("dd-MM-yyyy");
        DTPCari2.setName("DTPCari2"); // NOI18N
        DTPCari2.setOpaque(false);
        DTPCari2.setPreferredSize(new java.awt.Dimension(100, 23));
        DTPCari2.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                DTPCari2MouseClicked(evt);
            }
        });
        panelGlass10.add(DTPCari2);

        jLabel6.setForeground(new java.awt.Color(0, 0, 0));
        jLabel6.setText("Key Word :");
        jLabel6.setName("jLabel6"); // NOI18N
        jLabel6.setPreferredSize(new java.awt.Dimension(60, 23));
        panelGlass10.add(jLabel6);

        TCari.setForeground(new java.awt.Color(0, 0, 0));
        TCari.setName("TCari"); // NOI18N
        TCari.setPreferredSize(new java.awt.Dimension(350, 24));
        TCari.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                TCariKeyPressed(evt);
            }
        });
        panelGlass10.add(TCari);

        BtnCari.setBorder(null);
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

        jPanel3.add(panelGlass10, java.awt.BorderLayout.CENTER);

        internalFrame1.add(jPanel3, java.awt.BorderLayout.PAGE_END);

        PanelInput.setName("PanelInput"); // NOI18N
        PanelInput.setOpaque(false);
        PanelInput.setPreferredSize(new java.awt.Dimension(192, 245));
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

        FormInput.setName("FormInput"); // NOI18N
        FormInput.setPreferredSize(new java.awt.Dimension(160, 77));
        FormInput.setLayout(null);

        TNoRw.setEditable(false);
        TNoRw.setForeground(new java.awt.Color(0, 0, 0));
        TNoRw.setName("TNoRw"); // NOI18N
        FormInput.add(TNoRw);
        TNoRw.setBounds(78, 12, 125, 23);

        jLabel9.setForeground(new java.awt.Color(0, 0, 0));
        jLabel9.setText("Diet : ");
        jLabel9.setName("jLabel9"); // NOI18N
        FormInput.add(jLabel9);
        jLabel9.setBounds(167, 72, 40, 23);

        TPasien.setEditable(false);
        TPasien.setForeground(new java.awt.Color(0, 0, 0));
        TPasien.setName("TPasien"); // NOI18N
        FormInput.add(TPasien);
        TPasien.setBounds(155, 42, 310, 23);

        DTPTgl.setEditable(false);
        DTPTgl.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "24-12-2021" }));
        DTPTgl.setDisplayFormat("dd-MM-yyyy");
        DTPTgl.setName("DTPTgl"); // NOI18N
        DTPTgl.setOpaque(false);
        DTPTgl.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                DTPTglKeyPressed(evt);
            }
        });
        FormInput.add(DTPTgl);
        DTPTgl.setBounds(78, 72, 90, 23);

        jLabel10.setForeground(new java.awt.Color(0, 0, 0));
        jLabel10.setText("Tanggal : ");
        jLabel10.setName("jLabel10"); // NOI18N
        FormInput.add(jLabel10);
        jLabel10.setBounds(0, 72, 75, 23);

        BtnSeek1.setForeground(new java.awt.Color(0, 0, 0));
        BtnSeek1.setIcon(new javax.swing.ImageIcon(getClass().getResource("/picture/190.png"))); // NOI18N
        BtnSeek1.setMnemonic('X');
        BtnSeek1.setToolTipText("Alt+X");
        BtnSeek1.setName("BtnSeek1"); // NOI18N
        BtnSeek1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                BtnSeek1ActionPerformed(evt);
            }
        });
        FormInput.add(BtnSeek1);
        BtnSeek1.setBounds(655, 72, 28, 23);

        nmUnit.setEditable(false);
        nmUnit.setForeground(new java.awt.Color(0, 0, 0));
        nmUnit.setName("nmUnit"); // NOI18N
        FormInput.add(nmUnit);
        nmUnit.setBounds(345, 12, 300, 23);

        norm.setEditable(false);
        norm.setForeground(new java.awt.Color(0, 0, 0));
        norm.setName("norm"); // NOI18N
        FormInput.add(norm);
        norm.setBounds(78, 42, 70, 23);

        jLabel5.setForeground(new java.awt.Color(0, 0, 0));
        jLabel5.setText("No. Rawat : ");
        jLabel5.setName("jLabel5"); // NOI18N
        FormInput.add(jLabel5);
        jLabel5.setBounds(0, 12, 75, 23);

        jLabel8.setForeground(new java.awt.Color(0, 0, 0));
        jLabel8.setText("Pasien : ");
        jLabel8.setName("jLabel8"); // NOI18N
        FormInput.add(jLabel8);
        jLabel8.setBounds(0, 42, 75, 23);

        jLabel13.setForeground(new java.awt.Color(0, 0, 0));
        jLabel13.setText("Rg. Rawat/Poliklinik/Inst. : ");
        jLabel13.setName("jLabel13"); // NOI18N
        FormInput.add(jLabel13);
        jLabel13.setBounds(206, 12, 140, 23);

        jLabel14.setForeground(new java.awt.Color(0, 0, 0));
        jLabel14.setText("Tgl. Lahir : ");
        jLabel14.setName("jLabel14"); // NOI18N
        FormInput.add(jLabel14);
        jLabel14.setBounds(470, 42, 70, 23);

        TtglLahir.setEditable(false);
        TtglLahir.setForeground(new java.awt.Color(0, 0, 0));
        TtglLahir.setName("TtglLahir"); // NOI18N
        FormInput.add(TtglLahir);
        TtglLahir.setBounds(544, 42, 100, 23);

        cekWaktu.setBorder(null);
        cekWaktu.setForeground(new java.awt.Color(0, 0, 0));
        cekWaktu.setText("Semua Waktu Diet");
        cekWaktu.setBorderPainted(true);
        cekWaktu.setBorderPaintedFlat(true);
        cekWaktu.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        cekWaktu.setHorizontalTextPosition(javax.swing.SwingConstants.RIGHT);
        cekWaktu.setName("cekWaktu"); // NOI18N
        cekWaktu.setOpaque(false);
        cekWaktu.setPreferredSize(new java.awt.Dimension(100, 23));
        cekWaktu.addItemListener(new java.awt.event.ItemListener() {
            public void itemStateChanged(java.awt.event.ItemEvent evt) {
                cekWaktuItemStateChanged(evt);
            }
        });
        cekWaktu.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                cekWaktuMouseClicked(evt);
            }
        });
        cekWaktu.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                cekWaktuActionPerformed(evt);
            }
        });
        FormInput.add(cekWaktu);
        cekWaktu.setBounds(78, 102, 125, 23);

        cekDietPagi.setBorder(null);
        cekDietPagi.setForeground(new java.awt.Color(0, 0, 0));
        cekDietPagi.setText("Diet Pagi");
        cekDietPagi.setBorderPainted(true);
        cekDietPagi.setBorderPaintedFlat(true);
        cekDietPagi.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        cekDietPagi.setHorizontalTextPosition(javax.swing.SwingConstants.RIGHT);
        cekDietPagi.setName("cekDietPagi"); // NOI18N
        cekDietPagi.setOpaque(false);
        cekDietPagi.setPreferredSize(new java.awt.Dimension(100, 23));
        FormInput.add(cekDietPagi);
        cekDietPagi.setBounds(78, 132, 70, 23);

        cekDietSiang.setBorder(null);
        cekDietSiang.setForeground(new java.awt.Color(0, 0, 0));
        cekDietSiang.setText("Diet Siang");
        cekDietSiang.setBorderPainted(true);
        cekDietSiang.setBorderPaintedFlat(true);
        cekDietSiang.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        cekDietSiang.setHorizontalTextPosition(javax.swing.SwingConstants.RIGHT);
        cekDietSiang.setName("cekDietSiang"); // NOI18N
        cekDietSiang.setOpaque(false);
        cekDietSiang.setPreferredSize(new java.awt.Dimension(100, 23));
        FormInput.add(cekDietSiang);
        cekDietSiang.setBounds(78, 162, 70, 23);

        cekDietSore.setBorder(null);
        cekDietSore.setForeground(new java.awt.Color(0, 0, 0));
        cekDietSore.setText("Diet Sore");
        cekDietSore.setBorderPainted(true);
        cekDietSore.setBorderPaintedFlat(true);
        cekDietSore.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        cekDietSore.setHorizontalTextPosition(javax.swing.SwingConstants.RIGHT);
        cekDietSore.setName("cekDietSore"); // NOI18N
        cekDietSore.setOpaque(false);
        cekDietSore.setPreferredSize(new java.awt.Dimension(100, 23));
        FormInput.add(cekDietSore);
        cekDietSore.setBounds(78, 192, 70, 23);

        Scroll5.setName("Scroll5"); // NOI18N
        Scroll5.setOpaque(true);

        tbDiet.setAutoCreateRowSorter(true);
        tbDiet.setToolTipText("Silahkan klik untuk memilih data yang mau diedit ataupun dihapus");
        tbDiet.setName("tbDiet"); // NOI18N
        Scroll5.setViewportView(tbDiet);

        FormInput.add(Scroll5);
        Scroll5.setBounds(210, 72, 440, 125);

        BtnSeek3.setForeground(new java.awt.Color(0, 0, 0));
        BtnSeek3.setIcon(new javax.swing.ImageIcon(getClass().getResource("/picture/stop_f2.png"))); // NOI18N
        BtnSeek3.setMnemonic('X');
        BtnSeek3.setToolTipText("Alt+X");
        BtnSeek3.setName("BtnSeek3"); // NOI18N
        BtnSeek3.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                BtnSeek3ActionPerformed(evt);
            }
        });
        BtnSeek3.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                BtnSeek3KeyPressed(evt);
            }
        });
        FormInput.add(BtnSeek3);
        BtnSeek3.setBounds(655, 100, 28, 23);

        PanelInput.add(FormInput, java.awt.BorderLayout.CENTER);

        internalFrame1.add(PanelInput, java.awt.BorderLayout.PAGE_START);

        TabDiet.setToolTipText("");
        TabDiet.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        TabDiet.setName("TabDiet"); // NOI18N
        TabDiet.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                TabDietMouseClicked(evt);
            }
        });

        internalFrame2.setName("internalFrame2"); // NOI18N
        internalFrame2.setLayout(new java.awt.BorderLayout(1, 1));

        Scroll.setComponentPopupMenu(jPopupMenu1);
        Scroll.setName("Scroll"); // NOI18N
        Scroll.setOpaque(true);

        tbRanap.setAutoCreateRowSorter(true);
        tbRanap.setToolTipText("Silahkan klik untuk memilih data yang mau diedit ataupun dihapus");
        tbRanap.setComponentPopupMenu(jPopupMenu1);
        tbRanap.setName("tbRanap"); // NOI18N
        tbRanap.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                tbRanapMouseClicked(evt);
            }
        });
        tbRanap.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                tbRanapKeyPressed(evt);
            }
        });
        Scroll.setViewportView(tbRanap);

        internalFrame2.add(Scroll, java.awt.BorderLayout.CENTER);

        TabDiet.addTab(".: Pasien Rawat Inap", internalFrame2);

        internalFrame3.setName("internalFrame3"); // NOI18N
        internalFrame3.setLayout(new java.awt.BorderLayout(1, 1));

        ScrollRalan.setComponentPopupMenu(jPopupMenu1);
        ScrollRalan.setName("ScrollRalan"); // NOI18N
        ScrollRalan.setOpaque(true);
        ScrollRalan.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                ScrollRalanMouseClicked(evt);
            }
        });

        tbRalan.setToolTipText("Klik data di tabel");
        tbRalan.setComponentPopupMenu(jPopupMenu1);
        tbRalan.setName("tbRalan"); // NOI18N
        tbRalan.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                tbRalanMouseClicked(evt);
            }
        });
        tbRalan.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                tbRalanKeyPressed(evt);
            }
        });
        ScrollRalan.setViewportView(tbRalan);

        internalFrame3.add(ScrollRalan, java.awt.BorderLayout.CENTER);

        TabDiet.addTab(".: Pasien Rawat Jalan", internalFrame3);

        internalFrame1.add(TabDiet, java.awt.BorderLayout.CENTER);

        getContentPane().add(internalFrame1, java.awt.BorderLayout.CENTER);

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void DTPTglKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_DTPTglKeyPressed
        Valid.pindah(evt, TCari, DTPTgl);
}//GEN-LAST:event_DTPTglKeyPressed

    private void BtnSimpanActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_BtnSimpanActionPerformed
        if (TNoRw.getText().trim().equals("") || TPasien.getText().trim().equals("")) {
            Valid.textKosong(TNoRw, "pasien");
        } else if (tabMode3.getRowCount() == 0) {
            JOptionPane.showMessageDialog(null, "Data diet belum terisi...!!");
        } else {
            if (!jnsRawat.equals("Ralan")) {
                simpanDietINAP();
            } else if (jnsRawat.equals("Ralan")) {
                simpanDietJALAN();
            }            
        }
}//GEN-LAST:event_BtnSimpanActionPerformed

    private void BtnSimpanKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_BtnSimpanKeyPressed
        if (evt.getKeyCode() == KeyEvent.VK_SPACE) {
            BtnSimpanActionPerformed(null);
        } else {
            Valid.pindah(evt, TPasien, BtnBatal);
        }
}//GEN-LAST:event_BtnSimpanKeyPressed

    private void BtnBatalActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_BtnBatalActionPerformed
        emptTeks();
        TCari.setText("");
        cekWaktu.setSelected(false);
        cekDietPagi.setSelected(false);
        cekDietSiang.setSelected(false);
        cekDietSore.setSelected(false);
        ChkInput.setSelected(true);
        isForm();
        
        if (TabDiet.getSelectedIndex() == 0) {
            tampil();
        } else if (TabDiet.getSelectedIndex() == 1) {
            tampilRalan();
        }
}//GEN-LAST:event_BtnBatalActionPerformed

    private void BtnBatalKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_BtnBatalKeyPressed
        if (evt.getKeyCode() == KeyEvent.VK_SPACE) {
            emptTeks();
        } else {
            Valid.pindah(evt, BtnSimpan, BtnHapus);
        }
}//GEN-LAST:event_BtnBatalKeyPressed

    private void BtnHapusActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_BtnHapusActionPerformed
        if (TabDiet.getSelectedIndex() == 0) {
            if (tabMode.getRowCount() == 0) {
                JOptionPane.showMessageDialog(null, "Maaf, data sudah habis...!!!!");
                DTPTgl.requestFocus();
            } else if (TPasien.getText().trim().equals("")) {
                JOptionPane.showMessageDialog(null, "Maaf, Gagal menghapus. Pilih dulu data yang mau dihapus. Klik data pada tabel untuk memilih...!!!!");
            } else if (!TPasien.getText().trim().equals("")) {
                if (waktuAwal.equals("Pagi")) {
                    Sequel.queryu("delete from detail_beri_diet "
                            + "where no_rawat='" + TNoRw.getText() + "' "
                            + "and tanggal='" + tglDietAwal + "' "
                            + "and waktu='Pagi' and kd_diet='" + TKd.getText() + "'");
                    
                    Sequel.queryu("delete from diet_ranap_daftar_rincian "
                            + "where no_rawat='" + TNoRw.getText() + "' "
                            + "and kd_kamar='" + kdUnit + "' "
                            + "and tanggal='" + tglDietAwal + "' "
                            + "and waktu='Pagi'");
                } else if (waktuAwal.equals("Siang")) {
                    Sequel.queryu("delete from detail_beri_diet "
                            + "where no_rawat='" + TNoRw.getText() + "' "
                            + "and tanggal='" + tglDietAwal + "' "
                            + "and waktu='Siang' and kd_diet='" + TKd.getText() + "'");
                    
                    Sequel.queryu("delete from diet_ranap_daftar_rincian "
                            + "where no_rawat='" + TNoRw.getText() + "' "
                            + "and kd_kamar='" + kdUnit + "' "
                            + "and tanggal='" + tglDietAwal + "' "
                            + "and waktu='Siang'");
                } else if (waktuAwal.equals("Sore")) {
                    Sequel.queryu("delete from detail_beri_diet "
                            + "where no_rawat='" + TNoRw.getText() + "' "
                            + "and tanggal='" + tglDietAwal + "' "
                            + "and waktu='Sore' and kd_diet='" + TKd.getText() + "'");
                    
                    Sequel.queryu("delete from diet_ranap_daftar_rincian "
                            + "where no_rawat='" + TNoRw.getText() + "' "
                            + "and kd_kamar='" + kdUnit + "' "
                            + "and tanggal='" + tglDietAwal + "' "
                            + "and waktu='Sore'");
                }

                TabDietMouseClicked(null);
                emptTeks();
                cekWaktu.setSelected(false);
                cekDietPagi.setSelected(false);
                cekDietSiang.setSelected(false);
                cekDietSore.setSelected(false);
            }

        } else if (TabDiet.getSelectedIndex() == 1) {
            if (tabMode1.getRowCount() == 0) {
                JOptionPane.showMessageDialog(null, "Maaf, data sudah habis...!!!!");
                DTPTgl.requestFocus();
            } else if (TPasien.getText().trim().equals("")) {
                JOptionPane.showMessageDialog(null, "Maaf, Gagal menghapus. Pilih dulu data yang mau dihapus. Klik data pada tabel untuk memilih...!!!!");
            } else if (!TPasien.getText().trim().equals("")) {
                if (waktuAwal.equals("Pagi")) {
                    Sequel.queryu("delete from detail_beri_diet_ralan "
                            + "where no_rawat='" + TNoRw.getText() + "' "
                            + "and tanggal='" + tglDietAwal + "' "
                            + "and waktu='Pagi' and kd_diet='" + TKd.getText() + "'");
                    
                    Sequel.queryu("delete from diet_ralan_daftar_rincian "
                            + "where no_rawat='" + TNoRw.getText() + "' "
                            + "and kd_poli='" + kdUnit + "' "
                            + "and tanggal='" + tglDietAwal + "' "
                            + "and waktu='Pagi'");
                } else if (waktuAwal.equals("Siang")) {
                    Sequel.queryu("delete from detail_beri_diet_ralan "
                            + "where no_rawat='" + TNoRw.getText() + "' "
                            + "and tanggal='" + tglDietAwal + "' "
                            + "and waktu='Siang' and kd_diet='" + TKd.getText() + "'");
                    
                    Sequel.queryu("delete from diet_ralan_daftar_rincian "
                            + "where no_rawat='" + TNoRw.getText() + "' "
                            + "and kd_poli='" + kdUnit + "' "
                            + "and tanggal='" + tglDietAwal + "' "
                            + "and waktu='Siang'");
                } else if (waktuAwal.equals("Sore")) {
                    Sequel.queryu("delete from detail_beri_diet_ralan "
                            + "where no_rawat='" + TNoRw.getText() + "' "
                            + "and tanggal='" + tglDietAwal + "' "
                            + "and waktu='Sore' and kd_diet='" + TKd.getText() + "'");
                    
                    Sequel.queryu("delete from diet_ralan_daftar_rincian "
                            + "where no_rawat='" + TNoRw.getText() + "' "
                            + "and kd_poli='" + kdUnit + "' "
                            + "and tanggal='" + tglDietAwal + "' "
                            + "and waktu='Sore'");
                }

                TabDietMouseClicked(null);
                emptTeks();
                cekWaktu.setSelected(false);
                cekDietPagi.setSelected(false);
                cekDietSiang.setSelected(false);
                cekDietSore.setSelected(false);
            }
        }
}//GEN-LAST:event_BtnHapusActionPerformed

    private void BtnHapusKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_BtnHapusKeyPressed
        if (evt.getKeyCode() == KeyEvent.VK_SPACE) {
            BtnHapusActionPerformed(null);
        }
}//GEN-LAST:event_BtnHapusKeyPressed

    private void BtnKeluarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_BtnKeluarActionPerformed
        dispose();
}//GEN-LAST:event_BtnKeluarActionPerformed

    private void BtnKeluarKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_BtnKeluarKeyPressed
        if (evt.getKeyCode() == KeyEvent.VK_SPACE) {
            dispose();
        }
}//GEN-LAST:event_BtnKeluarKeyPressed

    private void BtnCariActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_BtnCariActionPerformed
        tabDataKlik();
}//GEN-LAST:event_BtnCariActionPerformed

    private void BtnCariKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_BtnCariKeyPressed
        if (evt.getKeyCode() == KeyEvent.VK_SPACE) {
            BtnCariActionPerformed(null);
        } else {
            Valid.pindah(evt, TCari, BtnAll);
        }
}//GEN-LAST:event_BtnCariKeyPressed

    private void BtnAllActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_BtnAllActionPerformed
        if (TabDiet.getSelectedIndex() == 0) {
            TCari.setText("");
            tampil();
        } else if (TabDiet.getSelectedIndex() == 1) {
            TCari.setText("");
            tampilRalan();
        }
}//GEN-LAST:event_BtnAllActionPerformed

    private void tbRanapMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tbRanapMouseClicked
        if (tabMode.getRowCount() != 0) {
            try {
                getData();
            } catch (java.lang.NullPointerException e) {
            }
        }
}//GEN-LAST:event_tbRanapMouseClicked

    private void tbRanapKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_tbRanapKeyPressed
        if (tabMode.getRowCount() != 0) {
            if ((evt.getKeyCode() == KeyEvent.VK_ENTER) || (evt.getKeyCode() == KeyEvent.VK_UP) || (evt.getKeyCode() == KeyEvent.VK_DOWN)) {
                try {
                    getData();
                } catch (java.lang.NullPointerException e) {
                }
            }
        }
}//GEN-LAST:event_tbRanapKeyPressed

private void BtnSeek1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_BtnSeek1ActionPerformed
    kddiet.setText("");
    nmdiet.setText("");
    jnsMakanan.setText("");
    kdberi.setText("");
    jumlahBeri.setText("");
    cmbSatuan.setSelectedIndex(0);
    jumlahBeri.setEditable(false);
    cmbSatuan.setEnabled(false);
    
    WindowDataDiet.setSize(458, 157);
    WindowDataDiet.setLocationRelativeTo(internalFrame1);
    WindowDataDiet.setVisible(true);
    WindowDataDiet.requestFocus();
}//GEN-LAST:event_BtnSeek1ActionPerformed

private void ChkInputActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_ChkInputActionPerformed
    isForm();
}//GEN-LAST:event_ChkInputActionPerformed

    private void cmbJamCariKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_cmbJamCariKeyPressed
        // TODO add your handling code here:
    }//GEN-LAST:event_cmbJamCariKeyPressed

    private void BtnSeek2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_BtnSeek2ActionPerformed
        if (labelUnit.getText().equals("Ruangan Inap/Bangsal : ")) {
            var.setform("DlgPemberianDiet");
            bangsal.emptTeks();
            bangsal.isCek();
            bangsal.setSize(internalFrame1.getWidth() - 40, internalFrame1.getHeight() - 40);
            bangsal.setLocationRelativeTo(internalFrame1);
            bangsal.setVisible(true);
        } else {
            var.setform("DlgPemberianDiet");
            poli.isCek();
            poli.setSize(internalFrame1.getWidth() - 40, internalFrame1.getHeight() - 40);
            poli.setLocationRelativeTo(internalFrame1);
            poli.setAlwaysOnTop(false);
            poli.setVisible(true);
        }      
    }//GEN-LAST:event_BtnSeek2ActionPerformed

    private void BtnSeek2KeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_BtnSeek2KeyPressed
        // TODO add your handling code here:
    }//GEN-LAST:event_BtnSeek2KeyPressed

    private void MnDataDietRanapActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_MnDataDietRanapActionPerformed
        DlgDataDietRanap dietRanap = new DlgDataDietRanap(null, false);
        dietRanap.setSize(657, 370);
        dietRanap.setLocationRelativeTo(internalFrame1);
        dietRanap.emptTeks();
        dietRanap.tampil();
        dietRanap.setVisible(true);
    }//GEN-LAST:event_MnDataDietRanapActionPerformed

    private void cmbJamCariMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_cmbJamCariMouseClicked
        cmbJamCari.setEditable(false);
    }//GEN-LAST:event_cmbJamCariMouseClicked

    private void DTPCari1MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_DTPCari1MouseClicked
        DTPCari1.setEditable(false);
    }//GEN-LAST:event_DTPCari1MouseClicked

    private void DTPCari2MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_DTPCari2MouseClicked
        DTPCari2.setEditable(false);
    }//GEN-LAST:event_DTPCari2MouseClicked

    private void TCariKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_TCariKeyPressed
        if (evt.getKeyCode() == KeyEvent.VK_ENTER) {
            BtnCariActionPerformed(null);
        } else if (evt.getKeyCode() == KeyEvent.VK_PAGE_DOWN) {
            BtnCari.requestFocus();
        } else if (evt.getKeyCode() == KeyEvent.VK_PAGE_UP) {
            BtnKeluar.requestFocus();
        }
    }//GEN-LAST:event_TCariKeyPressed

    private void tbRalanMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tbRalanMouseClicked
        if (tabMode1.getRowCount() != 0) {
            try {
                getDietRalan();
            } catch (java.lang.NullPointerException e) {
            }
        }
    }//GEN-LAST:event_tbRalanMouseClicked

    private void tbRalanKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_tbRalanKeyPressed
        if (tabMode1.getRowCount() != 0) {
            if ((evt.getKeyCode() == KeyEvent.VK_ENTER) || (evt.getKeyCode() == KeyEvent.VK_UP) || (evt.getKeyCode() == KeyEvent.VK_DOWN)) {
                try {
                    getDietRalan();
                } catch (java.lang.NullPointerException e) {
                }
            }
        }
    }//GEN-LAST:event_tbRalanKeyPressed

    private void TabDietMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_TabDietMouseClicked
        if (TabDiet.getSelectedIndex() == 0) {
            tampil();
            labelUnit.setText("Ruangan Inap/Bangsal : ");
        } else if (TabDiet.getSelectedIndex() == 1) {
            tampilRalan();
            labelUnit.setText("Poliklinik/Instalasi : ");
        }
    }//GEN-LAST:event_TabDietMouseClicked

    private void cekWaktuItemStateChanged(java.awt.event.ItemEvent evt) {//GEN-FIRST:event_cekWaktuItemStateChanged
        if (cekWaktu.isSelected() == true) {
            cekWaktu.setText("Semua Waktu Diet");
            cekDietPagi.setSelected(true);
            cekDietSiang.setSelected(true);
            cekDietSore.setSelected(true);
        } else if (cekWaktu.isSelected() == false) {
            cekWaktu.setText("Waktu Diet BERBEDA");
            cekDietPagi.setSelected(false);
            cekDietSiang.setSelected(false);
            cekDietSore.setSelected(false);
        }
    }//GEN-LAST:event_cekWaktuItemStateChanged

    private void cekWaktuMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_cekWaktuMouseClicked
        if (cekWaktu.isSelected() == true) {
            cekWaktu.setText("Semua Waktu Diet");
            cekDietPagi.setSelected(true);
            cekDietSiang.setSelected(true);
            cekDietSore.setSelected(true);
        } else if (cekWaktu.isSelected() == false) {
            cekWaktu.setText("Waktu Diet BERBEDA");
            cekDietPagi.setSelected(false);
            cekDietSiang.setSelected(false);
            cekDietSore.setSelected(false);
        }
    }//GEN-LAST:event_cekWaktuMouseClicked

    private void cekWaktuActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_cekWaktuActionPerformed
        if (cekWaktu.isSelected() == true) {
            cekWaktu.setText("Semua Waktu Diet");
            cekDietPagi.setSelected(true);
            cekDietSiang.setSelected(true);
            cekDietSore.setSelected(true);
        } else if (cekWaktu.isSelected() == false) {
            cekWaktu.setText("Waktu Diet BERBEDA");
            cekDietPagi.setSelected(false);
            cekDietSiang.setSelected(false);
            cekDietSore.setSelected(false);
        }
    }//GEN-LAST:event_cekWaktuActionPerformed

    private void BtnEditActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_BtnEditActionPerformed
        if (TabDiet.getSelectedIndex() == 0) {
            if (tabMode.getRowCount() == 0) {
                JOptionPane.showMessageDialog(null, "Maaf, data sudah habis...!!!!");
                DTPTgl.requestFocus();
            } else if (tabMode3.getRowCount() == 0) {
                JOptionPane.showMessageDialog(null, "Data diet belum terisi...!!");
            } else if (TNoRw.getText().trim().equals("") || TPasien.getText().trim().equals("")) {
                JOptionPane.showMessageDialog(null, "Silakan pilih dulu data yang mau diperbaiki. Klik data pada tabel untuk memilih...!!!!");
            } else if (cekDietPagi.isSelected() == true && (cekDietSiang.isSelected() == true) && (cekDietSore.isSelected() == true)) {
                JOptionPane.showMessageDialog(null, "Silakan pilih salah satu waktu dietnya...!!!!");
            } else if (cekDietPagi.isSelected() == true && (cekDietSiang.isSelected() == true)) {
                JOptionPane.showMessageDialog(null, "Silakan pilih salah satu waktu dietnya...!!!!");
            } else if (cekDietPagi.isSelected() == true && (cekDietSore.isSelected() == true)) {
                JOptionPane.showMessageDialog(null, "Silakan pilih salah satu waktu dietnya...!!!!");
            } else if (cekDietSiang.isSelected() == true && (cekDietSore.isSelected() == true)) {
                JOptionPane.showMessageDialog(null, "Silakan pilih salah satu waktu dietnya...!!!!");
            } else if (waktuAwal.equals("Pagi") && !cekDietPagi.isSelected() == true) {
                JOptionPane.showMessageDialog(null, "Untuk merubah wkt. diet pagi mnjdi. wkt. diet yg. lain, hapus dulu datanya..!!");
            } else if (waktuAwal.equals("Siang") && !cekDietSiang.isSelected() == true) {
                JOptionPane.showMessageDialog(null, "Untuk merubah wkt. diet siang mnjdi. wkt. diet yg. lain, hapus dulu datanya..!!");
            } else if (waktuAwal.equals("Sore") && !cekDietSore.isSelected() == true) {
                JOptionPane.showMessageDialog(null, "Untuk merubah wkt. diet sore mnjdi. wkt. diet yg. lain, hapus dulu datanya..!!");
            } else if (!TPasien.getText().trim().equals("")) {
                dietOK();
                if (cekDietPagi.isSelected() == true) {
                    hapusRincianDietRanap();
                    Sequel.mengedit("detail_beri_diet", "no_rawat='" + TNoRw.getText() + "' and tanggal='" + tglDietAwal + "' and waktu='" + waktuAwal + "'",
                            "tanggal='" + Valid.SetTgl(DTPTgl.getSelectedItem() + "") + "',"
                            + "waktu='Pagi',kd_diet='" + TKd.getText() + "'");

                    for (i = 0; i < tbDiet.getRowCount(); i++) {
                        Sequel.menyimpan("diet_ranap_daftar_rincian", "'" + TNoRw.getText() + "','" + kdUnit + "','"
                                + Valid.SetTgl(DTPTgl.getSelectedItem() + "") + "','Pagi','"
                                + tbDiet.getValueAt(i, 0).toString() + "','" + tbDiet.getValueAt(i, 4).toString() + "'", "diet pagi");
                    }
                }
                if (cekDietSiang.isSelected() == true) {
                    hapusRincianDietRanap();
                    Sequel.mengedit("detail_beri_diet", "no_rawat='" + TNoRw.getText() + "' and tanggal='" + tglDietAwal + "' and waktu='" + waktuAwal + "'",
                            "tanggal='" + Valid.SetTgl(DTPTgl.getSelectedItem() + "") + "',"
                            + "waktu='Siang',kd_diet='" + TKd.getText() + "'");

                    for (i = 0; i < tbDiet.getRowCount(); i++) {
                        Sequel.menyimpan("diet_ranap_daftar_rincian", "'" + TNoRw.getText() + "','" + kdUnit + "','"
                                + Valid.SetTgl(DTPTgl.getSelectedItem() + "") + "','Siang','"
                                + tbDiet.getValueAt(i, 0).toString() + "','" + tbDiet.getValueAt(i, 4).toString() + "'", "diet siang");
                    }
                }
                if (cekDietSore.isSelected() == true) {
                    hapusRincianDietRanap();
                    Sequel.mengedit("detail_beri_diet", "no_rawat='" + TNoRw.getText() + "' and tanggal='" + tglDietAwal + "' and waktu='" + waktuAwal + "'",
                            "tanggal='" + Valid.SetTgl(DTPTgl.getSelectedItem() + "") + "',"
                            + "waktu='Sore',kd_diet='" + TKd.getText() + "'");
                    
                    for (i = 0; i < tbDiet.getRowCount(); i++) {
                        Sequel.menyimpan("diet_ranap_daftar_rincian", "'" + TNoRw.getText() + "','" + kdUnit + "','"
                                + Valid.SetTgl(DTPTgl.getSelectedItem() + "") + "','Sore','"
                                + tbDiet.getValueAt(i, 0).toString() + "','" + tbDiet.getValueAt(i, 4).toString() + "'", "diet sore");
                    }
                }

                TabDietMouseClicked(null);
                emptTeks();
                cekWaktu.setSelected(false);
                cekDietPagi.setSelected(false);
                cekDietSiang.setSelected(false);
                cekDietSore.setSelected(false);
            }

        } else if (TabDiet.getSelectedIndex() == 1) {
            if (tabMode1.getRowCount() == 0) {
                JOptionPane.showMessageDialog(null, "Maaf, data sudah habis...!!!!");
                DTPTgl.requestFocus();
            } else if (tabMode3.getRowCount() == 0) {
                JOptionPane.showMessageDialog(null, "Data diet belum terisi...!!");
            } else if (TNoRw.getText().trim().equals("") || TPasien.getText().trim().equals("")) {
                JOptionPane.showMessageDialog(null, "Silakan pilih dulu data yang mau diperbaiki. Klik data pada tabel untuk memilih...!!!!");
            } else if (cekDietPagi.isSelected() == true && (cekDietSiang.isSelected() == true) && (cekDietSore.isSelected() == true)) {
                JOptionPane.showMessageDialog(null, "Silakan pilih salah satu waktu dietnya...!!!!");
            } else if (cekDietPagi.isSelected() == true && (cekDietSiang.isSelected() == true)) {
                JOptionPane.showMessageDialog(null, "Silakan pilih salah satu waktu dietnya...!!!!");
            } else if (cekDietPagi.isSelected() == true && (cekDietSore.isSelected() == true)) {
                JOptionPane.showMessageDialog(null, "Silakan pilih salah satu waktu dietnya...!!!!");
            } else if (cekDietSiang.isSelected() == true && (cekDietSore.isSelected() == true)) {
                JOptionPane.showMessageDialog(null, "Silakan pilih salah satu waktu dietnya...!!!!");
            } else if (waktuAwal.equals("Pagi") && !cekDietPagi.isSelected() == true) {
                JOptionPane.showMessageDialog(null, "Untuk merubah wkt. diet pagi mnjdi. wkt. diet yg. lain, hapus dulu datanya..!!");
            } else if (waktuAwal.equals("Siang") && !cekDietSiang.isSelected() == true) {
                JOptionPane.showMessageDialog(null, "Untuk merubah wkt. diet siang mnjdi. wkt. diet yg. lain, hapus dulu datanya..!!");
            } else if (waktuAwal.equals("Sore") && !cekDietSore.isSelected() == true) {
                JOptionPane.showMessageDialog(null, "Untuk merubah wkt. diet sore mnjdi. wkt. diet yg. lain, hapus dulu datanya..!!");
            } else if (!TPasien.getText().trim().equals("")) {
                dietOK();
                if (cekDietPagi.isSelected() == true) {
                    hapusRincianDietRalan();
                    Sequel.mengedit("detail_beri_diet_ralan", "no_rawat='" + TNoRw.getText() + "' and tanggal='" + tglDietAwal + "' and waktu='" + waktuAwal + "'",
                            "tanggal='" + Valid.SetTgl(DTPTgl.getSelectedItem() + "") + "',"
                            + "waktu='Pagi',kd_diet='" + TKd.getText() + "'");

                    for (i = 0; i < tbDiet.getRowCount(); i++) {
                        Sequel.menyimpan("diet_ralan_daftar_rincian", "'" + TNoRw.getText() + "','" + kdUnit + "','"
                                + Valid.SetTgl(DTPTgl.getSelectedItem() + "") + "','Pagi','"
                                + tbDiet.getValueAt(i, 0).toString() + "','" + tbDiet.getValueAt(i, 4).toString() + "'", "diet pagi");
                    }
                } 
                if (cekDietSiang.isSelected() == true) {
                    hapusRincianDietRalan();
                    Sequel.mengedit("detail_beri_diet_ralan", "no_rawat='" + TNoRw.getText() + "' and tanggal='" + tglDietAwal + "' and waktu='" + waktuAwal + "'",
                            "tanggal='" + Valid.SetTgl(DTPTgl.getSelectedItem() + "") + "',"
                            + "waktu='Siang',kd_diet='" + TKd.getText() + "'");

                    for (i = 0; i < tbDiet.getRowCount(); i++) {
                        Sequel.menyimpan("diet_ralan_daftar_rincian", "'" + TNoRw.getText() + "','" + kdUnit + "','"
                                + Valid.SetTgl(DTPTgl.getSelectedItem() + "") + "','Siang','"
                                + tbDiet.getValueAt(i, 0).toString() + "','" + tbDiet.getValueAt(i, 4).toString() + "'", "diet Siang");
                    }
                } 
                if (cekDietSore.isSelected() == true) {
                    hapusRincianDietRalan();
                    Sequel.mengedit("detail_beri_diet_ralan", "no_rawat='" + TNoRw.getText() + "' and tanggal='" + tglDietAwal + "' and waktu='" + waktuAwal + "'",
                            "tanggal='" + Valid.SetTgl(DTPTgl.getSelectedItem() + "") + "',"
                            + "waktu='Sore',kd_diet='" + TKd.getText() + "'");

                    for (i = 0; i < tbDiet.getRowCount(); i++) {
                        Sequel.menyimpan("diet_ralan_daftar_rincian", "'" + TNoRw.getText() + "','" + kdUnit + "','"
                                + Valid.SetTgl(DTPTgl.getSelectedItem() + "") + "','Sore','"
                                + tbDiet.getValueAt(i, 0).toString() + "','" + tbDiet.getValueAt(i, 4).toString() + "'", "diet Sore");
                    }
                }

                TabDietMouseClicked(null);
                emptTeks();
                cekWaktu.setSelected(false);
                cekDietPagi.setSelected(false);
                cekDietSiang.setSelected(false);
                cekDietSore.setSelected(false);
            }
        }
    }//GEN-LAST:event_BtnEditActionPerformed

    private void BtnEditKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_BtnEditKeyPressed
        if (evt.getKeyCode() == KeyEvent.VK_PAGE_DOWN) {
            BtnEditActionPerformed(null);
        } else {
            Valid.pindah(evt, BtnHapus, BtnKeluar);
        }
    }//GEN-LAST:event_BtnEditKeyPressed

    private void tbPoliMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tbPoliMouseClicked
        if (tabMode2.getRowCount() != 0) {
            try {
                getDataLabelGZ();
            } catch (java.lang.NullPointerException e) {
            }
        }
    }//GEN-LAST:event_tbPoliMouseClicked

    private void tbPoliKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_tbPoliKeyPressed
        if (tabMode2.getRowCount() != 0) {
            if ((evt.getKeyCode() == KeyEvent.VK_ENTER) || (evt.getKeyCode() == KeyEvent.VK_UP) || (evt.getKeyCode() == KeyEvent.VK_DOWN)) {
                try {
                    getDataLabelGZ();
                } catch (java.lang.NullPointerException e) {
                }
            }
        }
    }//GEN-LAST:event_tbPoliKeyPressed

    private void TCariPoliKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_TCariPoliKeyPressed
        if (evt.getKeyCode() == KeyEvent.VK_ENTER) {
            BtnCari4ActionPerformed(null);
            inisial.setText("");
        } else if (evt.getKeyCode() == KeyEvent.VK_PAGE_DOWN) {
            BtnCari4.requestFocus();
        } else if (evt.getKeyCode() == KeyEvent.VK_PAGE_UP) {
            BtnCloseIn10.requestFocus();
        }
    }//GEN-LAST:event_TCariPoliKeyPressed

    private void BtnCari4ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_BtnCari4ActionPerformed
        tampilPoliGZ();
    }//GEN-LAST:event_BtnCari4ActionPerformed

    private void BtnCari4KeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_BtnCari4KeyPressed
        if (evt.getKeyCode() == KeyEvent.VK_SPACE) {
            BtnCariActionPerformed(null);
        } else {
            Valid.pindah(evt, TCari, BtnAll);
        }
    }//GEN-LAST:event_BtnCari4KeyPressed

    private void BtnAll4ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_BtnAll4ActionPerformed
        TCariPoli.setText("");
        tampilPoliGZ();
    }//GEN-LAST:event_BtnAll4ActionPerformed

    private void BtnLabelGZActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_BtnLabelGZActionPerformed
        if (inisial.getText().equals("")) {
            JOptionPane.showMessageDialog(rootPane, "Inisial Poliklinik/Inst. harus terisi..!!!");
        } else {
            if (cmbPrin.getSelectedItem().toString().equals(" ")) {
                JOptionPane.showMessageDialog(rootPane, "Pilih dulu salah satu yang akan diprint..!!!");
                cmbPrin.requestFocus();
            } else if (cmbPrin.getSelectedItem().toString().equals("Label Diet")) {
                cekLabel();
                if (cekDataLabel == 0) {
                    JOptionPane.showMessageDialog(rootPane, "Data tidak ditemukan, periksa lagi tgl. kunjungannya..!!!");
                } else {
                    PrinLabelDiet();
                }
            } else if (cmbPrin.getSelectedItem().toString().equals("Bon Diet")) {
                cekBonGZ = 0;
                cekBonGZ = Sequel.cariInteger("SELECT COUNT(1) cek FROM reg_periksa rp INNER JOIN poliklinik pl ON pl.kd_poli = rp.kd_poli "
                        + "INNER JOIN pasien p ON p.no_rkm_medis = rp.no_rkm_medis LEFT JOIN detail_beri_diet_ralan dd ON dd.no_rawat = rp.no_rawat "
                        + "and dd.tanggal = '" + Valid.SetTgl(tglDiet.getSelectedItem() + "") + "' AND (dd.waktu = 'SIANG' OR ifnull(dd.waktu, '-') = '-') "
                        + "LEFT JOIN diet d ON d.kd_diet = dd.kd_diet WHERE rp.status_lanjut = 'ralan' AND rp.kd_poli = '" + kdPoli + "' "
                        + "and rp.tgl_registrasi='" + Valid.SetTgl(tglKun.getSelectedItem() + "") + "'");

                if (cekBonGZ == 0) {
                    JOptionPane.showMessageDialog(rootPane, "Data tidak ditemukan..!!!");
                } else {
                    PrinBonDiet();
                }

            } else if (cmbPrin.getSelectedItem().toString().equals("Form Ketepatan")) {
                cekDataTepatGZ();
                if (cekDataKetepatanDiet == 0) {
                    JOptionPane.showMessageDialog(rootPane, "Data tidak ditemukan..!!!");
                } else {
                    PrinKetepatanDiet();
                }
            }
        }
    }//GEN-LAST:event_BtnLabelGZActionPerformed

    private void BtnBatal1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_BtnBatal1ActionPerformed
        emptLabelGZ();
    }//GEN-LAST:event_BtnBatal1ActionPerformed

    private void BtnCloseIn10ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_BtnCloseIn10ActionPerformed
        WindowLabelGiziRALAN.dispose();
    }//GEN-LAST:event_BtnCloseIn10ActionPerformed

    private void MnDataDietRalanActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_MnDataDietRalanActionPerformed
        WindowLabelGiziRALAN.setSize(596, 370);
        WindowLabelGiziRALAN.setLocationRelativeTo(internalFrame1);
        WindowLabelGiziRALAN.setVisible(true);
        emptLabelGZ();
        tampilPoliGZ();
    }//GEN-LAST:event_MnDataDietRalanActionPerformed

    private void BtnCloseIn3ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_BtnCloseIn3ActionPerformed
        WindowDataDiet.dispose();
    }//GEN-LAST:event_BtnCloseIn3ActionPerformed

    private void BtnSimpan2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_BtnSimpan2ActionPerformed
        if (kddiet.getText().equals("")) {
            JOptionPane.showMessageDialog(null, "Silahkan pilih dulu salah satu jenis dietnya...!!!!");
        } else if (kdberi.getText().equals("")) {
            JOptionPane.showMessageDialog(null, "Silahkan pilih dulu salah satu jenis jlh. pemberian dietnya...!!!!");
        } else {
            if (nmdiet.getText().equals("F75") || nmdiet.getText().equals("F100") || nmdiet.getText().equals("F135")) {
                if (jumlahBeri.getText().trim().equals("-") || jumlahBeri.getText().trim().equals("")) {
                    JOptionPane.showMessageDialog(null, "Jlh. pemberian diet harus diisi dg. benar...!!!!");
                } else if (cmbSatuan.getSelectedIndex() == 0) {
                    JOptionPane.showMessageDialog(null, "Pilih salah satu satuan jlh. pemberian dietnya dg. benar...!!!!");
                } else {
                    if (Sequel.cariInteger("select count(-1) from diet_master_pemberian where nm_pemberian='" + jumlahBeri.getText() + "' and satuan='" + cmbSatuan.getSelectedItem().toString() + "'") == 0) {
                        kdberi.setText("");
                        Valid.autoNomer("diet_master_pemberian", "DP", 4, kdberi);
                        Sequel.menyimpan("diet_master_pemberian", "'" + kdberi.getText() + "','" + jumlahBeri.getText() + "','" + cmbSatuan.getSelectedItem() + "','1'", "Jumlah Pemberian Diet");
                        tabMode3.addRow(new Object[]{kddiet.getText(), false, nmdiet.getText(), jnsMakanan.getText(), jumlahBeri.getText() + " " + cmbSatuan.getSelectedItem()});
                        WindowDataDiet.dispose();
                    } else {
                        tabMode3.addRow(new Object[]{kddiet.getText(), false, nmdiet.getText(), jnsMakanan.getText(), jumlahBeri.getText() + " " + cmbSatuan.getSelectedItem()});
                        WindowDataDiet.dispose();
                    }                    
                }
            } else {
                tabMode3.addRow(new Object[]{kddiet.getText(), false, nmdiet.getText(), jnsMakanan.getText(), jumlahBeri.getText() + " " + cmbSatuan.getSelectedItem()});
                WindowDataDiet.dispose();
            }
        }
    }//GEN-LAST:event_BtnSimpan2ActionPerformed

    private void btnDietActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnDietActionPerformed
        var.setform("DlgPemberianDiet");
        diet.setSize(internalFrame1.getWidth() - 40, internalFrame1.getHeight() - 40);
        diet.setLocationRelativeTo(internalFrame1);
        diet.setVisible(true);
        diet.TCari.requestFocus();
    }//GEN-LAST:event_btnDietActionPerformed

    private void btnJumlahBeriActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnJumlahBeriActionPerformed
        var.setform("DlgPemberianDiet");
        jlhberi.setSize(internalFrame1.getWidth() - 40, internalFrame1.getHeight() - 40);
        jlhberi.setLocationRelativeTo(internalFrame1);
        jlhberi.tampil();
        jlhberi.setVisible(true);
        jlhberi.TCari.requestFocus();
    }//GEN-LAST:event_btnJumlahBeriActionPerformed

    private void BtnSeek3ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_BtnSeek3ActionPerformed
        for (i = 0; i < tbDiet.getRowCount(); i++) {
            if (tbDiet.getValueAt(i, 1).toString().equals("true")) {
                tabMode3.removeRow(i);
            }
        }
        BtnSeek3.requestFocus();
    }//GEN-LAST:event_BtnSeek3ActionPerformed

    private void BtnSeek3KeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_BtnSeek3KeyPressed
        // TODO add your handling code here:
    }//GEN-LAST:event_BtnSeek3KeyPressed

    private void formWindowOpened(java.awt.event.WindowEvent evt) {//GEN-FIRST:event_formWindowOpened
        Sequel.cariIsiComboDB("select satuan from diet_master_pemberian group by satuan", cmbSatuan);
    }//GEN-LAST:event_formWindowOpened

    private void ScrollRalanMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_ScrollRalanMouseClicked
        // TODO add your handling code here:
    }//GEN-LAST:event_ScrollRalanMouseClicked

    /**
     * @param args the command line arguments
     */
    public static void main(String args[]) {
        java.awt.EventQueue.invokeLater(() -> {
            DlgPemberianDiet dialog = new DlgPemberianDiet(new javax.swing.JFrame(), true);
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
    private widget.Button BtnAll4;
    private widget.Button BtnBatal;
    private widget.Button BtnBatal1;
    public widget.Button BtnCari;
    private widget.Button BtnCari4;
    private widget.Button BtnCloseIn10;
    private widget.Button BtnCloseIn3;
    private widget.Button BtnEdit;
    private widget.Button BtnHapus;
    private widget.Button BtnKeluar;
    private widget.Button BtnLabelGZ;
    private widget.Button BtnSeek1;
    private widget.Button BtnSeek2;
    private widget.Button BtnSeek3;
    private widget.Button BtnSimpan;
    private widget.Button BtnSimpan2;
    public widget.CekBox ChkInput;
    private widget.Tanggal DTPCari1;
    private widget.Tanggal DTPCari2;
    private widget.Tanggal DTPTgl;
    private widget.PanelBiasa FormInput;
    private widget.Label LCount;
    private javax.swing.JMenuItem MnDataDietRalan;
    private javax.swing.JMenuItem MnDataDietRanap;
    private widget.TextBox NmUnitCari;
    private javax.swing.JPanel PanelInput;
    private widget.ScrollPane Scroll;
    private widget.ScrollPane Scroll4;
    private widget.ScrollPane Scroll5;
    private widget.ScrollPane ScrollRalan;
    public widget.TextBox TCari;
    private widget.TextBox TCariPoli;
    private widget.TextBox TKd;
    private widget.TextBox TNoRw;
    private widget.TextBox TPasien;
    public javax.swing.JTabbedPane TabDiet;
    private widget.TextBox TtglLahir;
    private javax.swing.JDialog WindowDataDiet;
    public javax.swing.JDialog WindowLabelGiziRALAN;
    private widget.Button btnDiet;
    private widget.Button btnJumlahBeri;
    private widget.CekBox cekDietPagi;
    private widget.CekBox cekDietSiang;
    private widget.CekBox cekDietSore;
    public widget.CekBox cekWaktu;
    private widget.ComboBox cmbJamCari;
    private widget.ComboBox cmbPrin;
    private widget.ComboBox cmbSatuan;
    private widget.TextBox inisial;
    private widget.InternalFrame internalFrame1;
    private widget.InternalFrame internalFrame15;
    private widget.InternalFrame internalFrame2;
    private widget.InternalFrame internalFrame3;
    private widget.InternalFrame internalFrame4;
    private widget.InternalFrame internalFrame9;
    private widget.Label jLabel10;
    private widget.Label jLabel100;
    private widget.Label jLabel102;
    private widget.Label jLabel103;
    private widget.Label jLabel11;
    private widget.Label jLabel13;
    private widget.Label jLabel14;
    private widget.Label jLabel19;
    private widget.Label jLabel21;
    private widget.Label jLabel46;
    private widget.Label jLabel47;
    private widget.Label jLabel48;
    private widget.Label jLabel5;
    private widget.Label jLabel6;
    private widget.Label jLabel7;
    private widget.Label jLabel8;
    private widget.Label jLabel9;
    private widget.Label jLabel97;
    private widget.Label jLabel99;
    private javax.swing.JPanel jPanel3;
    private javax.swing.JPopupMenu jPopupMenu1;
    private widget.TextBox jnsMakanan;
    private widget.TextBox jumlahBeri;
    private widget.TextBox kdberi;
    private widget.TextBox kddiet;
    private widget.Label labelUnit;
    private widget.TextBox nmUnit;
    private widget.TextBox nmdiet;
    private widget.TextBox norm;
    private widget.panelisi panelGlass10;
    private widget.panelisi panelGlass13;
    private widget.panelisi panelGlass14;
    private widget.panelisi panelGlass15;
    private widget.panelisi panelGlass8;
    private widget.panelisi panelGlass9;
    private widget.Table tbDiet;
    private widget.Table tbPoli;
    private widget.Table tbRalan;
    private widget.Table tbRanap;
    private widget.Tanggal tglDiet;
    private widget.Tanggal tglKun;
    // End of variables declaration//GEN-END:variables

    public void tampil() {
        try {
            Valid.tabelKosong(tabMode);
            ps.setString(1, Valid.SetTgl(DTPCari1.getSelectedItem() + ""));
            ps.setString(2, Valid.SetTgl(DTPCari2.getSelectedItem() + ""));
            ps.setString(3, "%" + cmbJamCari.getSelectedItem().toString().replaceAll("Semua", "").trim() + "%");
            ps.setString(4, "%" + NmUnitCari.getText().trim() + "%");
            ps.setString(5, "%" + TCari.getText().trim() + "%");
            ps.setString(6, Valid.SetTgl(DTPCari1.getSelectedItem() + ""));
            ps.setString(7, Valid.SetTgl(DTPCari2.getSelectedItem() + ""));
            ps.setString(8, "%" + cmbJamCari.getSelectedItem().toString().replaceAll("Semua", "").trim() + "%");
            ps.setString(9, "%" + NmUnitCari.getText().trim() + "%");
            ps.setString(10, "%" + TCari.getText().trim() + "%");
            ps.setString(11, Valid.SetTgl(DTPCari1.getSelectedItem() + ""));
            ps.setString(12, Valid.SetTgl(DTPCari2.getSelectedItem() + ""));
            ps.setString(13, "%" + cmbJamCari.getSelectedItem().toString().replaceAll("Semua", "").trim() + "%");
            ps.setString(14, "%" + NmUnitCari.getText().trim() + "%");
            ps.setString(15, "%" + TCari.getText().trim() + "%");
            ps.setString(16, Valid.SetTgl(DTPCari1.getSelectedItem() + ""));
            ps.setString(17, Valid.SetTgl(DTPCari2.getSelectedItem() + ""));
            ps.setString(18, "%" + cmbJamCari.getSelectedItem().toString().replaceAll("Semua", "").trim() + "%");
            ps.setString(19, "%" + NmUnitCari.getText().trim() + "%");
            ps.setString(20, "%" + TCari.getText().trim() + "%");
            rs = ps.executeQuery();
            while (rs.next()) {
                tabMode.addRow(new String[]{
                    rs.getString(1), rs.getString(2) + ", " + rs.getString(3),
                    rs.getString(4), rs.getString(5), rs.getString(6), rs.getString(7),
                    Sequel.cariIsi("select diagnosa_awal from kamar_inap where no_rawat=? order by tgl_masuk desc limit 1", rs.getString(1))
                });
            }
        } catch (Exception e) {
            System.out.println("Notifikasi : " + e);
        }
        LCount.setText("" + tabMode.getRowCount());

        try {
            if (tabMode.getRowCount() > 0) {
                tabMode.addRow(new String[]{"", "", "", "", "", "", ""});
            }
            ps2.setString(1, Valid.SetTgl(DTPCari1.getSelectedItem() + ""));
            ps2.setString(2, Valid.SetTgl(DTPCari2.getSelectedItem() + ""));
            ps2.setString(3, "%" + cmbJamCari.getSelectedItem().toString().replaceAll("Semua", "").trim() + "%");
            ps2.setString(4, "%" + NmUnitCari.getText().trim() + "%");
            ps2.setString(5, "%" + TCari.getText().trim() + "%");
            ps2.setString(6, Valid.SetTgl(DTPCari1.getSelectedItem() + ""));
            ps2.setString(7, Valid.SetTgl(DTPCari2.getSelectedItem() + ""));
            ps2.setString(8, "%" + cmbJamCari.getSelectedItem().toString().replaceAll("Semua", "").trim() + "%");
            ps2.setString(9, "%" + NmUnitCari.getText().trim() + "%");
            ps2.setString(10, "%" + TCari.getText().trim() + "%");
            ps2.setString(11, Valid.SetTgl(DTPCari1.getSelectedItem() + ""));
            ps2.setString(12, Valid.SetTgl(DTPCari2.getSelectedItem() + ""));
            ps2.setString(13, "%" + cmbJamCari.getSelectedItem().toString().replaceAll("Semua", "").trim() + "%");
            ps2.setString(14, "%" + NmUnitCari.getText().trim() + "%");
            ps2.setString(15, "%" + TCari.getText().trim() + "%");
            ps2.setString(16, Valid.SetTgl(DTPCari1.getSelectedItem() + ""));
            ps2.setString(17, Valid.SetTgl(DTPCari2.getSelectedItem() + ""));
            ps2.setString(18, "%" + cmbJamCari.getSelectedItem().toString().replaceAll("Semua", "").trim() + "%");
            ps2.setString(19, "%" + NmUnitCari.getText().trim() + "%");
            ps2.setString(20, "%" + TCari.getText().trim() + "%");
            rs = ps2.executeQuery();
            i = 1;
            while (rs.next()) {
                tabMode.addRow(new String[]{"", i + ". " + rs.getString(1), ": " + rs.getString(2), "", "", "", ""});
                i++;
            }
        } catch (Exception e) {
            System.out.println("Notifikasi : " + e);
        }
    }

    public void emptTeks() {
        Valid.tabelKosong(tabMode3);
        jnsRawat = "";
        TNoRw.setText("");
        nmUnit.setText("");
        norm.setText("");
        TPasien.setText("");
        TtglLahir.setText("");
        NmUnitCari.setText("");
        DTPTgl.setDate(new Date());
        DTPTgl.requestFocus();
    }

    private void getData() {
        tglDietAwal = "";
        waktuAwal = "";
        TKd.setText("");
        cekWaktu.setSelected(false);
        cekDietPagi.setSelected(false);
        cekDietSiang.setSelected(false);
        cekDietSore.setSelected(false);

        if (tbRanap.getSelectedRow() != -1) {
            TNoRw.setText(tbRanap.getValueAt(tbRanap.getSelectedRow(), 0).toString());
            isRawat();
            TKd.setText(Sequel.cariString("select kd_diet from diet where nama_diet='" + tbRanap.getValueAt(tbRanap.getSelectedRow(), 5).toString() + "'"));
            Valid.SetTgl(DTPTgl, tbRanap.getValueAt(tbRanap.getSelectedRow(), 3).toString());
            tglDietAwal = tbRanap.getValueAt(tbRanap.getSelectedRow(), 3).toString();
            waktuAwal = tbRanap.getValueAt(tbRanap.getSelectedRow(), 4).toString();
            tampilDietRanap();
            
            if (tbRanap.getValueAt(tbRanap.getSelectedRow(), 4).toString().equals("Pagi")) {
                cekDietPagi.setSelected(true);
            } else if (tbRanap.getValueAt(tbRanap.getSelectedRow(), 4).toString().equals("Siang")) {
                cekDietSiang.setSelected(true);
            } else if (tbRanap.getValueAt(tbRanap.getSelectedRow(), 4).toString().equals("Sore")) {
                cekDietSore.setSelected(true);
            }
        }
    }

    private void isRawat() {
        try {
            pspasien = koneksi.prepareStatement("select p.nm_pasien, p.no_rkm_medis, date_format(p.tgl_lahir,'%d-%m-%Y') tgl_lhr, rp.status_lanjut, rp.kd_poli from reg_periksa rp "
                    + "inner join pasien p on p.no_rkm_medis=rp.no_rkm_medis where rp.no_rawat='" + TNoRw.getText() + "'");

            try {
                rspasien = pspasien.executeQuery();
                while (rspasien.next()) {
                    norm.setText(rspasien.getString("no_rkm_medis"));
                    TPasien.setText(rspasien.getString("nm_pasien"));
                    TtglLahir.setText(rspasien.getString("tgl_lhr"));
                    jnsRawat = rspasien.getString("status_lanjut");
                    kdUnit = rspasien.getString("kd_poli");
                }
            } catch (Exception e) {
                System.out.println("Notifikasi : " + e);
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
        
        if (jnsRawat.equals("Ralan")) {
            nmUnit.setText(Sequel.cariIsi("select nm_poli from poliklinik where kd_poli='" + kdUnit + "'"));
        } else if (!jnsRawat.equals("Ralan")) {   
            kdUnit = "";
            kdUnit = Sequel.cariIsi("select kd_kamar from kamar_inap where no_rawat='" + TNoRw.getText() + "' order by tgl_masuk desc limit 1");
            nmUnit.setText(Sequel.cariIsi("SELECT b.nm_bangsal FROM kamar k INNER JOIN bangsal b ON b.kd_bangsal = k.kd_bangsal WHERE k.kd_kamar='" + kdUnit + "'"));
        }
    }

    public void setNoRm(String norwt, Date tgl1, Date tgl2) {
        TNoRw.setText(norwt);
        TCari.setText(norwt);
        isRawat();
        DTPCari1.setDate(tgl1);
        DTPCari2.setDate(tgl2);
        ChkInput.setSelected(true);
        isForm();
      
        if (jnsRawat.equals("Ralan")) {
            TabDiet.setSelectedIndex(1);
            labelUnit.setText("Poliklinik/Instalasi : ");
        } else {
            TabDiet.setSelectedIndex(0);
            labelUnit.setText("Ruangan Inap/Bangsal : ");
        }
    }

    public void isForm() {
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
        BtnSimpan.setEnabled(var.getdiet_pasien());
        BtnHapus.setEnabled(var.getdiet_pasien());
    }

    public void tampilRalan() {
        try {
            Valid.tabelKosong(tabMode1);
            ps3.setString(1, Valid.SetTgl(DTPCari1.getSelectedItem() + ""));
            ps3.setString(2, Valid.SetTgl(DTPCari2.getSelectedItem() + ""));
            ps3.setString(3, "%" + cmbJamCari.getSelectedItem().toString().replaceAll("Semua", "").trim() + "%");
            ps3.setString(4, "%" + NmUnitCari.getText().trim() + "%");
            ps3.setString(5, "%" + TCari.getText().trim() + "%");
            ps3.setString(6, Valid.SetTgl(DTPCari1.getSelectedItem() + ""));
            ps3.setString(7, Valid.SetTgl(DTPCari2.getSelectedItem() + ""));
            ps3.setString(8, "%" + cmbJamCari.getSelectedItem().toString().replaceAll("Semua", "").trim() + "%");
            ps3.setString(9, "%" + NmUnitCari.getText().trim() + "%");
            ps3.setString(10, "%" + TCari.getText().trim() + "%");
            ps3.setString(11, Valid.SetTgl(DTPCari1.getSelectedItem() + ""));
            ps3.setString(12, Valid.SetTgl(DTPCari2.getSelectedItem() + ""));
            ps3.setString(13, "%" + cmbJamCari.getSelectedItem().toString().replaceAll("Semua", "").trim() + "%");
            ps3.setString(14, "%" + NmUnitCari.getText().trim() + "%");
            ps3.setString(15, "%" + TCari.getText().trim() + "%");
            rs1 = ps3.executeQuery();
            while (rs1.next()) {
                tabMode1.addRow(new String[]{
                    rs1.getString(1),
                    rs1.getString(2) + ", " + rs1.getString(3),
                    rs1.getString(4),
                    rs1.getString(5),
                    rs1.getString(6),
                    rs1.getString(7),
                    rs1.getString(8)
                });
            }
        } catch (Exception e) {
            System.out.println("Notifikasi : " + e);
        }
        LCount.setText("" + tabMode1.getRowCount());

        try {
            if (tabMode1.getRowCount() > 0) {
                tabMode1.addRow(new String[]{"", "", "", "", "", "", ""});
            }
            ps4.setString(1, Valid.SetTgl(DTPCari1.getSelectedItem() + ""));
            ps4.setString(2, Valid.SetTgl(DTPCari2.getSelectedItem() + ""));
            ps4.setString(3, "%" + cmbJamCari.getSelectedItem().toString().replaceAll("Semua", "").trim() + "%");
            ps4.setString(4, "%" + NmUnitCari.getText().trim() + "%");
            ps4.setString(5, "%" + TCari.getText().trim() + "%");
            ps4.setString(6, Valid.SetTgl(DTPCari1.getSelectedItem() + ""));
            ps4.setString(7, Valid.SetTgl(DTPCari2.getSelectedItem() + ""));
            ps4.setString(8, "%" + cmbJamCari.getSelectedItem().toString().replaceAll("Semua", "").trim() + "%");
            ps4.setString(9, "%" + NmUnitCari.getText().trim() + "%");
            ps4.setString(10, "%" + TCari.getText().trim() + "%");
            ps4.setString(11, Valid.SetTgl(DTPCari1.getSelectedItem() + ""));
            ps4.setString(12, Valid.SetTgl(DTPCari2.getSelectedItem() + ""));
            ps4.setString(13, "%" + cmbJamCari.getSelectedItem().toString().replaceAll("Semua", "").trim() + "%");
            ps4.setString(14, "%" + NmUnitCari.getText().trim() + "%");
            ps4.setString(15, "%" + TCari.getText().trim() + "%");
            rs1 = ps4.executeQuery();
            x = 1;
            while (rs1.next()) {
                tabMode1.addRow(new String[]{"", x + ". " + rs1.getString(1), ": " + rs1.getString(2), "", "", "", ""});
                x++;
            }
        } catch (Exception e) {
            System.out.println("Notifikasi : " + e);
        }
    }

    private void getDietRalan() {
        tglDietAwal = "";
        waktuAwal = "";
        cekWaktu.setSelected(false);
        cekDietPagi.setSelected(false);
        cekDietSiang.setSelected(false);
        cekDietSore.setSelected(false);

        if (tbRalan.getSelectedRow() != -1) {
            TNoRw.setText(tbRalan.getValueAt(tbRalan.getSelectedRow(), 0).toString());
            isRawat();
            TKd.setText(Sequel.cariString("select kd_diet from diet where nama_diet='" + tbRalan.getValueAt(tbRalan.getSelectedRow(), 5).toString() + "'"));
            Valid.SetTgl(DTPTgl, tbRalan.getValueAt(tbRalan.getSelectedRow(), 3).toString());
            tglDietAwal = tbRalan.getValueAt(tbRalan.getSelectedRow(), 3).toString();
            waktuAwal = tbRalan.getValueAt(tbRalan.getSelectedRow(), 4).toString();
            tampilDietRalan();
            
            if (tbRalan.getValueAt(tbRalan.getSelectedRow(), 4).toString().equals("Pagi")) {
                cekDietPagi.setSelected(true);
            } else if (tbRalan.getValueAt(tbRalan.getSelectedRow(), 4).toString().equals("Siang")) {
                cekDietSiang.setSelected(true);
            } else if (tbRalan.getValueAt(tbRalan.getSelectedRow(), 4).toString().equals("Sore")) {
                cekDietSore.setSelected(true);
            }
        }
    }

    private void simpanDietINAP() {
        if ((cekDietPagi.isSelected() == false) && (cekDietSiang.isSelected() == false) && (cekDietSore.isSelected() == false)) {
            JOptionPane.showMessageDialog(null, "Waktu diet harus dipilih...!!!!");
        } else {
            dietOK();
            if (cekDietPagi.isSelected() == true) {
                Sequel.menyimpan("detail_beri_diet", "'" + TNoRw.getText() + "','" + kdUnit + "','"
                        + Valid.SetTgl(DTPTgl.getSelectedItem() + "") + "','Pagi','"
                        + TKd.getText() + "'", "data");

                for (i = 0; i < tbDiet.getRowCount(); i++) {
                    Sequel.menyimpan("diet_ranap_daftar_rincian", "'" + TNoRw.getText() + "','" + kdUnit + "','"
                            + Valid.SetTgl(DTPTgl.getSelectedItem() + "") + "','Pagi','"
                            + tbDiet.getValueAt(i, 0).toString() + "','" + tbDiet.getValueAt(i, 4).toString() + "'", "diet pagi");
                }
            }
            if (cekDietSiang.isSelected() == true) {
                Sequel.menyimpan("detail_beri_diet", "'" + TNoRw.getText() + "','" + kdUnit + "','"
                        + Valid.SetTgl(DTPTgl.getSelectedItem() + "") + "','Siang','"
                        + TKd.getText() + "'", "data");

                for (i = 0; i < tbDiet.getRowCount(); i++) {
                    Sequel.menyimpan("diet_ranap_daftar_rincian", "'" + TNoRw.getText() + "','" + kdUnit + "','"
                            + Valid.SetTgl(DTPTgl.getSelectedItem() + "") + "','Siang','"
                            + tbDiet.getValueAt(i, 0).toString() + "','" + tbDiet.getValueAt(i, 4).toString() + "'", "diet siang");
                }
            }
            if (cekDietSore.isSelected() == true) {
                Sequel.menyimpan("detail_beri_diet", "'" + TNoRw.getText() + "','" + kdUnit + "','"
                        + Valid.SetTgl(DTPTgl.getSelectedItem() + "") + "','Sore','"
                        + TKd.getText() + "'", "data");

                for (i = 0; i < tbDiet.getRowCount(); i++) {
                    Sequel.menyimpan("diet_ranap_daftar_rincian", "'" + TNoRw.getText() + "','" + kdUnit + "','"
                            + Valid.SetTgl(DTPTgl.getSelectedItem() + "") + "','Sore','"
                            + tbDiet.getValueAt(i, 0).toString() + "','" + tbDiet.getValueAt(i, 4).toString() + "'", "diet sore");
                }
            }
            
            TabDietMouseClicked(null);
            emptTeks();
            cekWaktu.setSelected(false);
            cekDietPagi.setSelected(false);
            cekDietSiang.setSelected(false);
            cekDietSore.setSelected(false);
        }     
    }

    private void simpanDietJALAN() {
        if ((cekDietPagi.isSelected() == false) && (cekDietSiang.isSelected() == false) && (cekDietSore.isSelected() == false)) {
            JOptionPane.showMessageDialog(null, "Waktu diet harus dipilih...!!!!");
        } else {
            dietOK();
            if (cekDietPagi.isSelected() == true) {
                Sequel.menyimpan("detail_beri_diet_ralan", "'" + TNoRw.getText() + "','" + kdUnit + "','"
                        + Valid.SetTgl(DTPTgl.getSelectedItem() + "") + "','Pagi','"
                        + TKd.getText() + "'", "data");
                
                for (i = 0; i < tbDiet.getRowCount(); i++) {
                    Sequel.menyimpan("diet_ralan_daftar_rincian", "'" + TNoRw.getText() + "','" + kdUnit + "','"
                            + Valid.SetTgl(DTPTgl.getSelectedItem() + "") + "','Pagi','"
                            + tbDiet.getValueAt(i, 0).toString() + "','" + tbDiet.getValueAt(i, 4).toString() + "'", "diet pagi");
                }
            }
            if (cekDietSiang.isSelected() == true) {
                Sequel.menyimpan("detail_beri_diet_ralan", "'" + TNoRw.getText() + "','" + kdUnit + "','"
                        + Valid.SetTgl(DTPTgl.getSelectedItem() + "") + "','Siang','"
                        + TKd.getText() + "'", "data");
                
                for (i = 0; i < tbDiet.getRowCount(); i++) {
                    Sequel.menyimpan("diet_ralan_daftar_rincian", "'" + TNoRw.getText() + "','" + kdUnit + "','"
                            + Valid.SetTgl(DTPTgl.getSelectedItem() + "") + "','Siang','"
                            + tbDiet.getValueAt(i, 0).toString() + "','" + tbDiet.getValueAt(i, 4).toString() + "'", "diet siang");
                }
            }
            if (cekDietSore.isSelected() == true) {
                Sequel.menyimpan("detail_beri_diet_ralan", "'" + TNoRw.getText() + "','" + kdUnit + "','"
                        + Valid.SetTgl(DTPTgl.getSelectedItem() + "") + "','Sore','"
                        + TKd.getText() + "'", "data");
                
                for (i = 0; i < tbDiet.getRowCount(); i++) {
                    Sequel.menyimpan("diet_ralan_daftar_rincian", "'" + TNoRw.getText() + "','" + kdUnit + "','"
                            + Valid.SetTgl(DTPTgl.getSelectedItem() + "") + "','Sore','"
                            + tbDiet.getValueAt(i, 0).toString() + "','" + tbDiet.getValueAt(i, 4).toString() + "'", "diet sore");
                }
            }
            
            TabDietMouseClicked(null);
            emptTeks();
            cekWaktu.setSelected(false);
            cekDietPagi.setSelected(false);
            cekDietSiang.setSelected(false);
            cekDietSore.setSelected(false);
        }
    }
    
    public void tampilPoliGZ() {
        Valid.tabelKosong(tabMode2);
        try {
            psPoli = koneksi.prepareStatement("SELECT nm_poli, left(kd_poli,3) inisial, kd_poli FROM poliklinik where "
                    + "kd_poli not in ('-','laa','lab','rad','kjh') and nm_poli like ? or "
                    + "kd_poli not in ('-','laa','lab','rad','kjh') and left(kd_poli,3) like ? ORDER BY nm_poli");

            try {
                psPoli.setString(1, "%" + TCariPoli.getText() + "%");
                psPoli.setString(2, "%" + TCariPoli.getText() + "%");
                rsPoli = psPoli.executeQuery();
                while (rsPoli.next()) {
                    tabMode2.addRow(new Object[]{
                        rsPoli.getString(1),
                        rsPoli.getString(2),
                        rsPoli.getString(3)
                    });
                }
            } catch (Exception e) {
                System.out.println("simrskhanza.DlgPemberianDiet.tampilPoliGZ() : " + e);
            } finally {
                if (rsPoli != null) {
                    rsPoli.close();
                }
                if (psPoli != null) {
                    psPoli.close();
                }
            }
        } catch (SQLException e) {
            System.out.println("Notifikasi : " + e);
        }
    }
    
    private void getDataLabelGZ() {
        nama_unit = "";
        kdPoli = "";
        
        if (tbPoli.getSelectedRow() != -1) {
            nama_unit = tbPoli.getValueAt(tbPoli.getSelectedRow(), 0).toString();
            inisial.setText(tbPoli.getValueAt(tbPoli.getSelectedRow(), 1).toString());
            kdPoli = tbPoli.getValueAt(tbPoli.getSelectedRow(), 2).toString();
        }
    }
    
    public void emptLabelGZ() {
        inisial.setText("");
        TCariPoli.setText("");
        cmbPrin.setSelectedIndex(0);
        TCariPoli.requestFocus();
    }
    
    private void PrinLabelDiet() {
        this.setCursor(Cursor.getPredefinedCursor(Cursor.WAIT_CURSOR));
        Map<String, Object> param = new HashMap<>();
        param.put("namars", var.getnamars());
        param.put("alamatrs", var.getalamatrs());
        param.put("kotars", var.getkabupatenrs());
        param.put("propinsirs", var.getpropinsirs());
        param.put("kontakrs", var.getkontakrs());
        param.put("emailrs", var.getemailrs());
        param.put("logo", Sequel.cariGambar("select logo from setting"));
        param.put("inisial_poli", inisial.getText());
        param.put("nm_unit", "Poliklinik / Instalasi : " + nama_unit + ", Tgl. Pemberian Diet : " + tglDiet.getSelectedItem());
        Valid.MyReport("rptlabeldietRalan.jrxml", "report", "::[ Label Diet Gizi Pasien Rawat Jalan ]::",
                " select a.nm_pasien,a.no_rkm_medis, a.ttl, 'RJ' inisial_kls, a.inisial_label_gz, "
                + "ifnull(b.nama_diet,'.........................................') nama_diet_pagi, "
                + "ifnull(b.waktu,'Pagi') waktu_pagi, ifnull(b.inisial_wkt,'PA') inisial_wkt_pagi, "
                + "ifnull(c.nama_diet,'.........................................') nama_diet_siang, "
                + "ifnull(c.waktu,'Siang') waktu_siang, ifnull(c.inisial_wkt,'SI') inisial_wkt_siang, "
                + "ifnull(d.nama_diet,'.........................................') nama_diet_sore, "
                + "ifnull(d.waktu,'Sore') waktu_sore, ifnull(d.inisial_wkt,'SO') inisial_wkt_sore "
                + "from ((select rp.no_rkm_medis, p.nm_pasien, CONCAT(p.tmp_lahir,', ',DATE_FORMAT(p.tgl_lahir, '%d-%m-%Y')) ttl, "
                + "left(rp.kd_poli,3) inisial_label_gz from reg_periksa rp "
                + "INNER JOIN pasien p ON p.no_rkm_medis = rp.no_rkm_medis "
                + "where rp.status_lanjut='Ralan' and rp.kd_poli = '" + kdPoli + "' and rp.tgl_registrasi='" + Valid.SetTgl(tglKun.getSelectedItem() + "") + "') as a "
                + "left join "
                + "(SELECT rp.no_rkm_medis, d.nama_diet, dd.waktu, LEFT (UPPER(dd.waktu), 2) inisial_wkt FROM reg_periksa rp "
                + "LEFT JOIN detail_beri_diet_ralan dd ON dd.no_rawat = rp.no_rawat AND dd.tanggal='" + Valid.SetTgl(tglDiet.getSelectedItem() + "") + "' "
                + "and (dd.waktu = 'pagi' OR ifnull(dd.waktu, '-') = '-') LEFT JOIN diet d ON d.kd_diet = dd.kd_diet "
                + "WHERE rp.status_lanjut='Ralan' and rp.kd_poli = '" + kdPoli + "' and rp.tgl_registrasi='" + Valid.SetTgl(tglKun.getSelectedItem() + "") + "') as b on a.no_rkm_medis = b.no_rkm_medis "
                + "left JOIN "
                + "(SELECT rp.no_rkm_medis, d.nama_diet, dd.waktu, LEFT (UPPER(dd.waktu), 2) inisial_wkt FROM reg_periksa rp "
                + "LEFT JOIN detail_beri_diet_ralan dd ON dd.no_rawat = rp.no_rawat AND dd.tanggal='" + Valid.SetTgl(tglDiet.getSelectedItem() + "") + "' "
                + "and (dd.waktu = 'siang' OR ifnull(dd.waktu, '-') = '-') LEFT JOIN diet d ON d.kd_diet = dd.kd_diet "
                + "WHERE rp.status_lanjut='Ralan' and rp.kd_poli = '" + kdPoli + "' and rp.tgl_registrasi='" + Valid.SetTgl(tglKun.getSelectedItem() + "") + "') as c on a.no_rkm_medis = c.no_rkm_medis "
                + "left JOIN "
                + "(SELECT rp.no_rkm_medis, d.nama_diet, dd.waktu, LEFT (UPPER(dd.waktu), 2) inisial_wkt FROM reg_periksa rp "
                + "LEFT JOIN detail_beri_diet_ralan dd ON dd.no_rawat = rp.no_rawat AND dd.tanggal='" + Valid.SetTgl(tglDiet.getSelectedItem() + "") + "' "
                + "and (dd.waktu = 'sore' OR ifnull(dd.waktu, '-') = '-') LEFT JOIN diet d ON d.kd_diet = dd.kd_diet "
                + "WHERE rp.status_lanjut='Ralan' and rp.kd_poli = '" + kdPoli + "' and rp.tgl_registrasi='" + Valid.SetTgl(tglKun.getSelectedItem() + "") + "') as d on a.no_rkm_medis = d.no_rkm_medis) "
                + "ORDER BY nama_diet_pagi DESC, nama_diet_siang DESC, nama_diet_sore DESC, nm_pasien ", param);

        this.setCursor(Cursor.getDefaultCursor());
        tampilPoliGZ();
    }
    
    private void PrinBonDiet() {
        nmHari = "";
        nmHari1 = "";
        nmDay = "";
        nmDay1 = "";

        nmDay = Sequel.cariIsi("SELECT DATE_FORMAT('" + Valid.SetTgl(tglDiet.getSelectedItem() + "") + "','%W') hari");
        nmDay1 = Sequel.cariIsi("SELECT DATE_FORMAT(now(),'%W') hari");

        if (nmDay.equals("Monday")) {
            nmHari = "Senin";
        } else if (nmDay.equals("Tuesday")) {
            nmHari = "Selasa";
        } else if (nmDay.equals("Wednesday")) {
            nmHari = "Rabu";
        } else if (nmDay.equals("Thursday")) {
            nmHari = "Kamis";
        } else if (nmDay.equals("Friday")) {
            nmHari = "Jum'at";
        } else if (nmDay.equals("Saturday")) {
            nmHari = "Sabtu";
        } else if (nmDay.equals("Sunday")) {
            nmHari = "Minggu";
        }

        if (nmDay1.equals("Monday")) {
            nmHari1 = "Senin";
        } else if (nmDay1.equals("Tuesday")) {
            nmHari1 = "Selasa";
        } else if (nmDay1.equals("Wednesday")) {
            nmHari1 = "Rabu";
        } else if (nmDay1.equals("Thursday")) {
            nmHari1 = "Kamis";
        } else if (nmDay1.equals("Friday")) {
            nmHari1 = "Jum'at";
        } else if (nmDay1.equals("Saturday")) {
            nmHari1 = "Sabtu";
        } else if (nmDay1.equals("Sunday")) {
            nmHari1 = "Minggu";
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
        param.put("tgl_beri_diet", nmHari + ", " + tglDiet.getSelectedItem());
        param.put("tgl_sekarang", nmHari1 + ", " + Sequel.cariIsi("SELECT DATE_FORMAT(NOW(),'%d-%m-%Y')"));
        param.put("nm_unit", "BON DIET MAKANAN PASIEN POLIKLINIK/INSTALASI " + nama_unit);
        Valid.MyReport("rptbondietRalan.jrxml", "report", "::[ Bon Diet Makanan Pasien ]::",
                "SELECT CONCAT(p.no_rkm_medis,' - ',p.nm_pasien) pasien, DATE_FORMAT(p.tgl_lahir, '%d-%m-%Y') tgl_lhr, "
                + "pl.nm_poli, lower(IFNULL(pr.diagnosa, '-')) diag_resum, IFNULL(d.nama_diet, '') nm_diet FROM reg_periksa rp "
                + "INNER JOIN poliklinik pl ON pl.kd_poli = rp.kd_poli "
                + "INNER JOIN pasien p ON p.no_rkm_medis = rp.no_rkm_medis "
                + "LEFT JOIN pemeriksaan_ralan pr ON pr.no_rawat = rp.no_rawat "
                + "LEFT JOIN detail_beri_diet_ralan dd ON dd.no_rawat = rp.no_rawat AND dd.tanggal = '" + Valid.SetTgl(tglDiet.getSelectedItem() + "") + "' "
                + "AND (dd.waktu = 'SIANG' OR ifnull(dd.waktu, '-') = '-') LEFT JOIN diet d ON d.kd_diet = dd.kd_diet WHERE "
                + "rp.status_lanjut = 'ralan' AND rp.kd_poli = '" + kdPoli + "' and rp.tgl_registrasi='" + Valid.SetTgl(tglKun.getSelectedItem() + "") + "' "
                + "ORDER BY pl.nm_poli, pasien", param);
        this.setCursor(Cursor.getDefaultCursor());
        tampilPoliGZ();
    }
    
    private void cekDataTepatGZ() {
        cekDataKetepatanDiet = 0;
        cekDataKetepatanDiet = Sequel.cariInteger("select count(-1) cek "
                + "from ((select rp.no_rkm_medis, p.nm_pasien, DATE_FORMAT(p.tgl_lahir, '%d-%m-%Y') tgl_lahir, pl.nm_poli from reg_periksa rp "
                + "INNER JOIN poliklinik pl ON pl.kd_poli = rp.kd_poli "
                + "INNER JOIN pasien p ON p.no_rkm_medis = rp.no_rkm_medis "
                + "where rp.status_lanjut = 'Ralan' AND rp.kd_poli = '" + kdPoli + "' AND rp.tgl_registrasi='" + Valid.SetTgl(tglKun.getSelectedItem() + "") + "') as a "
                + "left join "
                + "(SELECT rp.no_rkm_medis, d.nama_diet, dd.waktu FROM reg_periksa rp "
                + "INNER JOIN poliklinik pl ON pl.kd_poli = rp.kd_poli "
                + "INNER JOIN pasien p ON p.no_rkm_medis = rp.no_rkm_medis "
                + "LEFT JOIN detail_beri_diet_ralan dd ON dd.no_rawat = rp.no_rawat AND dd.tanggal='" + Valid.SetTgl(tglDiet.getSelectedItem() + "") + "' "
                + "and dd.waktu = 'pagi' LEFT JOIN diet d ON d.kd_diet = dd.kd_diet "
                + "WHERE rp.status_lanjut = 'Ralan' AND rp.kd_poli = '" + kdPoli + "' and rp.tgl_registrasi='" + Valid.SetTgl(tglKun.getSelectedItem() + "") + "') as b on b.no_rkm_medis = a.no_rkm_medis "
                + "left JOIN "
                + "(SELECT rp.no_rkm_medis, d.nama_diet, dd.waktu FROM reg_periksa rp "
                + "INNER JOIN poliklinik pl ON pl.kd_poli = rp.kd_poli "
                + "INNER JOIN pasien p ON p.no_rkm_medis = rp.no_rkm_medis "
                + "LEFT JOIN detail_beri_diet_ralan dd ON dd.no_rawat = rp.no_rawat AND dd.tanggal='" + Valid.SetTgl(tglDiet.getSelectedItem() + "") + "' "
                + "and dd.waktu = 'siang' LEFT JOIN diet d ON d.kd_diet = dd.kd_diet "
                + "WHERE rp.status_lanjut = 'Ralan' AND rp.kd_poli = '" + kdPoli + "' and rp.tgl_registrasi='" + Valid.SetTgl(tglKun.getSelectedItem() + "") + "') as c on a.no_rkm_medis = c.no_rkm_medis "
                + "left JOIN "
                + "(SELECT rp.no_rkm_medis, d.nama_diet, dd.waktu FROM reg_periksa rp "
                + "INNER JOIN poliklinik pl ON pl.kd_poli = rp.kd_poli "
                + "INNER JOIN pasien p ON p.no_rkm_medis = rp.no_rkm_medis "
                + "LEFT JOIN detail_beri_diet_ralan dd ON dd.no_rawat = rp.no_rawat AND dd.tanggal='" + Valid.SetTgl(tglDiet.getSelectedItem() + "") + "' "
                + "and dd.waktu = 'sore' LEFT JOIN diet d ON d.kd_diet = dd.kd_diet "
                + "WHERE rp.status_lanjut = 'Ralan' AND rp.kd_poli = '" + kdPoli + "' and rp.tgl_registrasi='" + Valid.SetTgl(tglKun.getSelectedItem() + "") + "') as d on a.no_rkm_medis = d.no_rkm_medis)");
    }
    
    private void PrinKetepatanDiet() {
        nmHari = "";
        nmHari1 = "";
        nmDay = "";
        nmDay1 = "";

        nmDay = Sequel.cariIsi("SELECT DATE_FORMAT('" + Valid.SetTgl(tglDiet.getSelectedItem() + "") + "','%W') hari");
        nmDay1 = Sequel.cariIsi("SELECT DATE_FORMAT(now(),'%W') hari");

        if (nmDay.equals("Monday")) {
            nmHari = "Senin";
        } else if (nmDay.equals("Tuesday")) {
            nmHari = "Selasa";
        } else if (nmDay.equals("Wednesday")) {
            nmHari = "Rabu";
        } else if (nmDay.equals("Thursday")) {
            nmHari = "Kamis";
        } else if (nmDay.equals("Friday")) {
            nmHari = "Jum'at";
        } else if (nmDay.equals("Saturday")) {
            nmHari = "Sabtu";
        } else if (nmDay.equals("Sunday")) {
            nmHari = "Minggu";
        }

        if (nmDay1.equals("Monday")) {
            nmHari1 = "Senin";
        } else if (nmDay1.equals("Tuesday")) {
            nmHari1 = "Selasa";
        } else if (nmDay1.equals("Wednesday")) {
            nmHari1 = "Rabu";
        } else if (nmDay1.equals("Thursday")) {
            nmHari1 = "Kamis";
        } else if (nmDay1.equals("Friday")) {
            nmHari1 = "Jum'at";
        } else if (nmDay1.equals("Saturday")) {
            nmHari1 = "Sabtu";
        } else if (nmDay1.equals("Sunday")) {
            nmHari1 = "Minggu";
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
        param.put("tgl_beri_diet", nmHari + ", " + tglDiet.getSelectedItem());
        param.put("tgl_sekarang", nmHari1 + ", " + Sequel.cariIsi("SELECT DATE_FORMAT(NOW(),'%d-%m-%Y')"));
        param.put("nm_unit", "FORM KETEPATAN DIET POLIKLINIK/INSTALASI " + nama_unit);
        Valid.MyReport("rptKetepatandietRalan.jrxml", "report", "::[ Form Ketepatan Diet Pasien Rawat Jalan ]::",
                "select concat(a.no_rkm_medis,' - ',a.nm_pasien) pasien, a.tgl_lahir, a.nm_poli, ifnull(b.nama_diet,'') diet_pagi, ifnull(b.waktu,'Pagi') waktu_pagi, "
                + "ifnull(c.nama_diet,'') diet_siang, ifnull(c.waktu,'Siang') waktu_siang, ifnull(d.nama_diet,'') diet_sore, ifnull(d.waktu,'Sore') waktu_sore, 'RJ' kelas "
                + "from ((select rp.no_rkm_medis, p.nm_pasien, DATE_FORMAT(p.tgl_lahir, '%d-%m-%Y') tgl_lahir, pl.nm_poli from reg_periksa rp "
                + "INNER JOIN poliklinik pl ON pl.kd_poli = rp.kd_poli "
                + "INNER JOIN pasien p ON p.no_rkm_medis = rp.no_rkm_medis "
                + "where rp.status_lanjut = 'Ralan' AND rp.kd_poli = '" + kdPoli + "' AND rp.tgl_registrasi='" + Valid.SetTgl(tglKun.getSelectedItem() + "") + "') as a "
                + "left join "
                + "(SELECT rp.no_rkm_medis, d.nama_diet, dd.waktu FROM reg_periksa rp "
                + "INNER JOIN poliklinik pl ON pl.kd_poli = rp.kd_poli "
                + "INNER JOIN pasien p ON p.no_rkm_medis = rp.no_rkm_medis "
                + "LEFT JOIN detail_beri_diet_ralan dd ON dd.no_rawat = rp.no_rawat AND dd.tanggal='" + Valid.SetTgl(tglDiet.getSelectedItem() + "") + "' "
                + "and dd.waktu = 'pagi' LEFT JOIN diet d ON d.kd_diet = dd.kd_diet "
                + "WHERE rp.status_lanjut = 'Ralan' AND rp.kd_poli = '" + kdPoli + "' and rp.tgl_registrasi='" + Valid.SetTgl(tglKun.getSelectedItem() + "") + "') as b on b.no_rkm_medis = a.no_rkm_medis "
                + "left JOIN "
                + "(SELECT rp.no_rkm_medis, d.nama_diet, dd.waktu FROM reg_periksa rp "
                + "INNER JOIN poliklinik pl ON pl.kd_poli = rp.kd_poli "
                + "INNER JOIN pasien p ON p.no_rkm_medis = rp.no_rkm_medis "
                + "LEFT JOIN detail_beri_diet_ralan dd ON dd.no_rawat = rp.no_rawat AND dd.tanggal='" + Valid.SetTgl(tglDiet.getSelectedItem() + "") + "' "
                + "and dd.waktu = 'siang' LEFT JOIN diet d ON d.kd_diet = dd.kd_diet "
                + "WHERE rp.status_lanjut = 'Ralan' AND rp.kd_poli = '" + kdPoli + "' and rp.tgl_registrasi='" + Valid.SetTgl(tglKun.getSelectedItem() + "") + "') as c on a.no_rkm_medis = c.no_rkm_medis "
                + "left JOIN "
                + "(SELECT rp.no_rkm_medis, d.nama_diet, dd.waktu FROM reg_periksa rp "
                + "INNER JOIN poliklinik pl ON pl.kd_poli = rp.kd_poli "
                + "INNER JOIN pasien p ON p.no_rkm_medis = rp.no_rkm_medis "
                + "LEFT JOIN detail_beri_diet_ralan dd ON dd.no_rawat = rp.no_rawat AND dd.tanggal='" + Valid.SetTgl(tglDiet.getSelectedItem() + "") + "' "
                + "and dd.waktu = 'sore' LEFT JOIN diet d ON d.kd_diet = dd.kd_diet "
                + "WHERE rp.status_lanjut = 'Ralan' AND rp.kd_poli = '" + kdPoli + "' and rp.tgl_registrasi='" + Valid.SetTgl(tglKun.getSelectedItem() + "") + "') as d on a.no_rkm_medis = d.no_rkm_medis) "
                + "ORDER BY nm_poli, pasien", param);
        this.setCursor(Cursor.getDefaultCursor());
        tampilPoliGZ();
    }
    
    private void cekLabel() {
    cekDataLabel = 0;
    cekDataLabel = Sequel.cariInteger("select count(-1) "
                + "from ((select rp.no_rkm_medis, p.nm_pasien, CONCAT(p.tmp_lahir,', ',DATE_FORMAT(p.tgl_lahir, '%d-%m-%Y')) ttl, "
                + "left(rp.kd_poli,3) inisial_label_gz from reg_periksa rp "
                + "INNER JOIN pasien p ON p.no_rkm_medis = rp.no_rkm_medis "
                + "where rp.status_lanjut='Ralan' and rp.kd_poli = '" + kdPoli + "' and rp.tgl_registrasi='" + Valid.SetTgl(tglKun.getSelectedItem() + "") + "') as a "
                + "left join "
                + "(SELECT rp.no_rkm_medis, d.nama_diet, dd.waktu, LEFT (UPPER(dd.waktu), 2) inisial_wkt FROM reg_periksa rp "
                + "LEFT JOIN detail_beri_diet_ralan dd ON dd.no_rawat = rp.no_rawat AND dd.tanggal='" + Valid.SetTgl(tglDiet.getSelectedItem() + "") + "' "
                + "and (dd.waktu = 'pagi' OR ifnull(dd.waktu, '-') = '-') LEFT JOIN diet d ON d.kd_diet = dd.kd_diet "
                + "WHERE rp.status_lanjut='Ralan' and rp.kd_poli = '" + kdPoli + "' and rp.tgl_registrasi='" + Valid.SetTgl(tglKun.getSelectedItem() + "") + "') as b on a.no_rkm_medis = b.no_rkm_medis "
                + "left JOIN "
                + "(SELECT rp.no_rkm_medis, d.nama_diet, dd.waktu, LEFT (UPPER(dd.waktu), 2) inisial_wkt FROM reg_periksa rp "
                + "LEFT JOIN detail_beri_diet_ralan dd ON dd.no_rawat = rp.no_rawat AND dd.tanggal='" + Valid.SetTgl(tglDiet.getSelectedItem() + "") + "' "
                + "and (dd.waktu = 'siang' OR ifnull(dd.waktu, '-') = '-') LEFT JOIN diet d ON d.kd_diet = dd.kd_diet "
                + "WHERE rp.status_lanjut='Ralan' and rp.kd_poli = '" + kdPoli + "' and rp.tgl_registrasi='" + Valid.SetTgl(tglKun.getSelectedItem() + "") + "') as c on a.no_rkm_medis = c.no_rkm_medis "
                + "left JOIN "
                + "(SELECT rp.no_rkm_medis, d.nama_diet, dd.waktu, LEFT (UPPER(dd.waktu), 2) inisial_wkt FROM reg_periksa rp "
                + "LEFT JOIN detail_beri_diet_ralan dd ON dd.no_rawat = rp.no_rawat AND dd.tanggal='" + Valid.SetTgl(tglDiet.getSelectedItem() + "") + "' "
                + "and (dd.waktu = 'sore' OR ifnull(dd.waktu, '-') = '-') LEFT JOIN diet d ON d.kd_diet = dd.kd_diet "
                + "WHERE rp.status_lanjut='Ralan' and rp.kd_poli = '" + kdPoli + "' and rp.tgl_registrasi='" + Valid.SetTgl(tglKun.getSelectedItem() + "") + "') as d on a.no_rkm_medis = d.no_rkm_medis)");
    }
    
    private void dietOK() {
        dataDiet = "";
        TKd.setText("");
        
        for (i = 0; i < tbDiet.getRowCount(); i++) {
            if (dataDiet.equals("")) {
                dataDiet = tbDiet.getValueAt(i, 2).toString() + " " + tbDiet.getValueAt(i, 4).toString();
            } else {
                dataDiet = dataDiet + " + " + tbDiet.getValueAt(i, 2).toString() + " " + tbDiet.getValueAt(i, 4).toString();
            }
        }
        
        if (Sequel.cariInteger("select count(-1) from diet where nama_diet='" + dataDiet + "'") == 0) {
            Valid.autoNomer("diet", "DB", 4, TKd);
            Sequel.menyimpan("diet", "'" + TKd.getText() + "','" + dataDiet + "','UMUM','1'", "Data diet pasien");
        } else {
            TKd.setText(Sequel.cariIsi("select kd_diet from diet where nama_diet='" + dataDiet + "'"));
        }
    }
    
    private void tampilDietRanap() {
        Valid.tabelKosong(tabMode3);
        try {
            if (waktuAwal.equals("Pagi")) {
                psRDI = koneksi.prepareStatement("select d.kd_diet, dm.nama_diet, dm.jns_makanan, d.jlh_pemberian from diet_ranap_daftar_rincian d "
                        + "inner join diet_master dm on dm.kd_diet=d.kd_diet where d.no_rawat='" + TNoRw.getText() + "' "
                        + "and kd_kamar='" + kdUnit + "' and tanggal='" + tglDietAwal + "' and waktu='Pagi'");
            } else if (waktuAwal.equals("Siang")) {
                psRDI = koneksi.prepareStatement("select d.kd_diet, dm.nama_diet, dm.jns_makanan, d.jlh_pemberian from diet_ranap_daftar_rincian d "
                        + "inner join diet_master dm on dm.kd_diet=d.kd_diet where d.no_rawat='" + TNoRw.getText() + "' "
                        + "and kd_kamar='" + kdUnit + "' and tanggal='" + tglDietAwal + "' and waktu='Siang'");
            } else if (waktuAwal.equals("Sore")) {
                psRDI = koneksi.prepareStatement("select d.kd_diet, dm.nama_diet, dm.jns_makanan, d.jlh_pemberian from diet_ranap_daftar_rincian d "
                        + "inner join diet_master dm on dm.kd_diet=d.kd_diet where d.no_rawat='" + TNoRw.getText() + "' "
                        + "and kd_kamar='" + kdUnit + "' and tanggal='" + tglDietAwal + "' and waktu='Sore'");
            } 

            try {
                rsRDI = psRDI.executeQuery();
                while (rsRDI.next()) {
                    tabMode3.addRow(new Object[]{
                        rsRDI.getString("kd_diet"), 
                        false, 
                        rsRDI.getString("nama_diet"), 
                        rsRDI.getString("jns_makanan"), 
                        rsRDI.getString("jlh_pemberian")
                    });
                }
            } catch (Exception e) {
                System.out.println("Notifikasi : " + e);
            } finally {
                if (rsRDI != null) {
                    rsRDI.close();
                }
                if (psRDI != null) {
                    psRDI.close();
                }
            }
        } catch (Exception e) {
            System.out.println("Notifikasi : " + e);
        }
    }
    
    private void tampilDietRalan() {
        Valid.tabelKosong(tabMode3);
        try {
            if (waktuAwal.equals("Pagi")) {
                psRDJ = koneksi.prepareStatement("select d.kd_diet, dm.nama_diet, dm.jns_makanan, d.jlh_pemberian from diet_ralan_daftar_rincian d "
                        + "inner join diet_master dm on dm.kd_diet=d.kd_diet where d.no_rawat='" + TNoRw.getText() + "' "
                        + "and kd_poli='" + kdUnit + "' and tanggal='" + tglDietAwal + "' and waktu='Pagi'");
            } else if (waktuAwal.equals("Siang")) {
                psRDJ = koneksi.prepareStatement("select d.kd_diet, dm.nama_diet, dm.jns_makanan, d.jlh_pemberian from diet_ralan_daftar_rincian d "
                        + "inner join diet_master dm on dm.kd_diet=d.kd_diet where d.no_rawat='" + TNoRw.getText() + "' "
                        + "and kd_poli='" + kdUnit + "' and tanggal='" + tglDietAwal + "' and waktu='Siang'");
            } else if (waktuAwal.equals("Sore")) {
                psRDJ = koneksi.prepareStatement("select d.kd_diet, dm.nama_diet, dm.jns_makanan, d.jlh_pemberian from diet_ralan_daftar_rincian d "
                        + "inner join diet_master dm on dm.kd_diet=d.kd_diet where d.no_rawat='" + TNoRw.getText() + "' "
                        + "and kd_poli='" + kdUnit + "' and tanggal='" + tglDietAwal + "' and waktu='Sore'");
            } 

            try {
                rsRDJ = psRDJ.executeQuery();
                while (rsRDJ.next()) {
                    tabMode3.addRow(new Object[]{
                        rsRDJ.getString("kd_diet"), 
                        false, 
                        rsRDJ.getString("nama_diet"), 
                        rsRDJ.getString("jns_makanan"), 
                        rsRDJ.getString("jlh_pemberian")
                    });
                }
            } catch (Exception e) {
                System.out.println("Notifikasi : " + e);
            } finally {
                if (rsRDJ != null) {
                    rsRDJ.close();
                }
                if (psRDJ != null) {
                    psRDJ.close();
                }
            }
        } catch (Exception e) {
            System.out.println("Notifikasi : " + e);
        }
    }
    
    private void hapusRincianDietRanap() {
        if (waktuAwal.equals("Pagi")) {
            Sequel.queryu("delete from diet_ranap_daftar_rincian "
                    + "where no_rawat='" + TNoRw.getText() + "' "
                    + "and tanggal='" + tglDietAwal + "' "
                    + "and waktu='Pagi'");
        } else if (waktuAwal.equals("Siang")) {
            Sequel.queryu("delete from diet_ranap_daftar_rincian "
                    + "where no_rawat='" + TNoRw.getText() + "' "
                    + "and tanggal='" + tglDietAwal + "' "
                    + "and waktu='Siang'");
        } else if (waktuAwal.equals("Sore")) {
            Sequel.queryu("delete from diet_ranap_daftar_rincian "
                    + "where no_rawat='" + TNoRw.getText() + "' "
                    + "and tanggal='" + tglDietAwal + "' "
                    + "and waktu='Sore'");
        }
    }
    
    private void hapusRincianDietRalan() {
        if (waktuAwal.equals("Pagi")) {
            Sequel.queryu("delete from diet_ralan_daftar_rincian "
                    + "where no_rawat='" + TNoRw.getText() + "' "
                    + "and tanggal='" + tglDietAwal + "' "
                    + "and waktu='Pagi'");
        } else if (waktuAwal.equals("Siang")) {
            Sequel.queryu("delete from diet_ralan_daftar_rincian "
                    + "where no_rawat='" + TNoRw.getText() + "' "
                    + "and tanggal='" + tglDietAwal + "' "
                    + "and waktu='Siang'");
        } else if (waktuAwal.equals("Sore")) {
            Sequel.queryu("delete from diet_ralan_daftar_rincian "
                    + "where no_rawat='" + TNoRw.getText() + "' "
                    + "and tanggal='" + tglDietAwal + "' "
                    + "and waktu='Sore'");
        }
    }
    
    public void tabDataKlik() {
        if (TabDiet.getSelectedIndex() == 0) {
            tampil();
        } else if (TabDiet.getSelectedIndex() == 1) {
            tampilRalan();
        }
    }
}
