package bridging;
import fungsi.WarnaTable;
import fungsi.batasInput;
import fungsi.koneksiDB;
import fungsi.sekuel;
import fungsi.validasi;
import fungsi.var;
import java.awt.Dimension;
import java.awt.event.KeyEvent;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Date;
import javax.swing.JOptionPane;
import javax.swing.JTable;
import javax.swing.event.DocumentEvent;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableColumn;

/**
 *
 * @author dosen
 */
public class INACBGjknBelumDiklaim extends javax.swing.JDialog {
    private final DefaultTableModel tabMode;
    private Connection koneksi = koneksiDB.condb();
    private sekuel Sequel = new sekuel();
    private validasi Valid = new validasi();
    private PreparedStatement ps;
    private ResultSet rs;
    private String jnsRWT = "", tglRanap = "", norawat = "", noSEPnya = "", tglRalan = "";
    private Date tgl = new Date();

    /**
     * Creates new form DlgSpesialis
     *
     * @param parent
     * @param modal
     */
    public INACBGjknBelumDiklaim(java.awt.Frame parent, boolean modal) {
        super(parent, modal);
        initComponents();

        this.setLocation(10, 10);
        setSize(459, 539);

        Object[] row = {"No. SEP", "No. Rawat", "No. RM","Nama Pasien","Nama Unit","Tgl. Reg./Msk.","Tgl. Klr./Plg.","status_rwt"};
        tabMode = new DefaultTableModel(null, row) {
            @Override
            public boolean isCellEditable(int rowIndex, int colIndex) {
                return false;
            }
        };

        tbData.setModel(tabMode);
        tbData.setPreferredScrollableViewportSize(new Dimension(500, 500));
        tbData.setAutoResizeMode(JTable.AUTO_RESIZE_OFF);

        for (int i = 0; i < 8; i++) {
            TableColumn column = tbData.getColumnModel().getColumn(i);
            if (i == 0) {
                column.setPreferredWidth(130);
            } else if (i == 1) {
                column.setPreferredWidth(105);
            } else if (i == 2) {
                column.setPreferredWidth(55);
            } else if (i == 3) {
                column.setPreferredWidth(270);
            } else if (i == 4) {
                column.setPreferredWidth(270);
            } else if (i == 5) {
                column.setPreferredWidth(85);
            } else if (i == 6) {
                column.setPreferredWidth(85);
            } else if (i == 7) {
//                column.setPreferredWidth(200);
                column.setMinWidth(0);
                column.setMaxWidth(0);
            }
        }

        tbData.setDefaultRenderer(Object.class, new WarnaTable());

        TCari.setDocument(new batasInput((byte) 100).getKata(TCari));
        if (koneksiDB.cariCepat().equals("aktif")) {
            TCari.getDocument().addDocumentListener(new javax.swing.event.DocumentListener() {
                @Override
                public void insertUpdate(DocumentEvent e) {
                    if (TCari.getText().length() > 2) {
                        tampil();
                    }
                }

                @Override
                public void removeUpdate(DocumentEvent e) {
                    if (TCari.getText().length() > 2) {
                        tampil();
                    }
                }

                @Override
                public void changedUpdate(DocumentEvent e) {
                    if (TCari.getText().length() > 2) {
                        tampil();
                    }
                }
            });
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

        Popup1 = new javax.swing.JPopupMenu();
        MnPengajuanKlaim = new javax.swing.JMenuItem();
        internalFrame1 = new widget.InternalFrame();
        Scroll = new widget.ScrollPane();
        tbData = new widget.Table();
        panelGlass9 = new widget.panelisi();
        jLabel6 = new widget.Label();
        TCari = new widget.TextBox();
        BtnCari = new widget.Button();
        BtnAll = new widget.Button();
        jLabel7 = new widget.Label();
        LCount = new widget.Label();
        BtnKeluar = new widget.Button();
        panelGlass10 = new widget.panelisi();
        jLabel8 = new widget.Label();
        tgl1 = new widget.Tanggal();
        jLabel21 = new widget.Label();
        tgl2 = new widget.Tanggal();
        jLabel23 = new widget.Label();
        cmbJnsRawat = new widget.ComboBox();

        Popup1.setName("Popup1"); // NOI18N

        MnPengajuanKlaim.setBackground(new java.awt.Color(242, 242, 242));
        MnPengajuanKlaim.setFont(new java.awt.Font("Tahoma", 0, 11)); // NOI18N
        MnPengajuanKlaim.setIcon(new javax.swing.ImageIcon(getClass().getResource("/picture/category.png"))); // NOI18N
        MnPengajuanKlaim.setText("Proses Pengajuan Klaim JKN");
        MnPengajuanKlaim.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        MnPengajuanKlaim.setHorizontalTextPosition(javax.swing.SwingConstants.RIGHT);
        MnPengajuanKlaim.setIconTextGap(8);
        MnPengajuanKlaim.setName("MnPengajuanKlaim"); // NOI18N
        MnPengajuanKlaim.setPreferredSize(new java.awt.Dimension(180, 25));
        MnPengajuanKlaim.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                MnPengajuanKlaimBtnPrintActionPerformed(evt);
            }
        });
        Popup1.add(MnPengajuanKlaim);

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);
        setUndecorated(true);
        setResizable(false);
        addWindowListener(new java.awt.event.WindowAdapter() {
            public void windowOpened(java.awt.event.WindowEvent evt) {
                formWindowOpened(evt);
            }
        });

        internalFrame1.setBorder(javax.swing.BorderFactory.createTitledBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(240, 245, 235)), "::[ Daftar Pasien JKN Yang Belum Selesai Proses Klaim INACBG ]::", javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Tahoma", 1, 12), new java.awt.Color(0, 0, 0))); // NOI18N
        internalFrame1.setName("internalFrame1"); // NOI18N
        internalFrame1.setLayout(new java.awt.BorderLayout(1, 1));

        Scroll.setName("Scroll"); // NOI18N
        Scroll.setOpaque(true);

        tbData.setAutoCreateRowSorter(true);
        tbData.setToolTipText("Silahkan klik untuk memilih data yang akan diproses klaim");
        tbData.setComponentPopupMenu(Popup1);
        tbData.setName("tbData"); // NOI18N
        tbData.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                tbDataMouseClicked(evt);
            }
        });
        tbData.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                tbDataKeyPressed(evt);
            }
        });
        Scroll.setViewportView(tbData);

        internalFrame1.add(Scroll, java.awt.BorderLayout.CENTER);

        panelGlass9.setName("panelGlass9"); // NOI18N
        panelGlass9.setPreferredSize(new java.awt.Dimension(44, 44));
        panelGlass9.setLayout(new java.awt.FlowLayout(java.awt.FlowLayout.LEFT, 5, 9));

        jLabel6.setForeground(new java.awt.Color(0, 0, 0));
        jLabel6.setText("Key Word :");
        jLabel6.setName("jLabel6"); // NOI18N
        jLabel6.setPreferredSize(new java.awt.Dimension(70, 23));
        panelGlass9.add(jLabel6);

        TCari.setForeground(new java.awt.Color(0, 0, 0));
        TCari.setName("TCari"); // NOI18N
        TCari.setPreferredSize(new java.awt.Dimension(300, 23));
        TCari.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                TCariKeyPressed(evt);
            }
        });
        panelGlass9.add(TCari);

        BtnCari.setForeground(new java.awt.Color(0, 0, 0));
        BtnCari.setIcon(new javax.swing.ImageIcon(getClass().getResource("/picture/accept.png"))); // NOI18N
        BtnCari.setMnemonic('1');
        BtnCari.setText("Tampilkan Data");
        BtnCari.setToolTipText("Alt+1");
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
            public void keyReleased(java.awt.event.KeyEvent evt) {
                BtnCariKeyReleased(evt);
            }
        });
        panelGlass9.add(BtnCari);

        BtnAll.setForeground(new java.awt.Color(0, 0, 0));
        BtnAll.setIcon(new javax.swing.ImageIcon(getClass().getResource("/picture/Search-16x16.png"))); // NOI18N
        BtnAll.setMnemonic('2');
        BtnAll.setText("Semua Data");
        BtnAll.setToolTipText("Alt+2");
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
        panelGlass9.add(BtnAll);

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

        BtnKeluar.setForeground(new java.awt.Color(0, 0, 0));
        BtnKeluar.setIcon(new javax.swing.ImageIcon(getClass().getResource("/picture/exit.png"))); // NOI18N
        BtnKeluar.setMnemonic('4');
        BtnKeluar.setText("Keluar");
        BtnKeluar.setToolTipText("Alt+4");
        BtnKeluar.setName("BtnKeluar"); // NOI18N
        BtnKeluar.setPreferredSize(new java.awt.Dimension(90, 23));
        BtnKeluar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                BtnKeluarActionPerformed(evt);
            }
        });
        panelGlass9.add(BtnKeluar);

        internalFrame1.add(panelGlass9, java.awt.BorderLayout.PAGE_END);

        panelGlass10.setName("panelGlass10"); // NOI18N
        panelGlass10.setPreferredSize(new java.awt.Dimension(44, 44));
        panelGlass10.setLayout(new java.awt.FlowLayout(java.awt.FlowLayout.LEFT, 5, 9));

        jLabel8.setForeground(new java.awt.Color(0, 0, 0));
        jLabel8.setText("Tgl. Reg./Masuk : ");
        jLabel8.setName("jLabel8"); // NOI18N
        jLabel8.setPreferredSize(new java.awt.Dimension(110, 23));
        panelGlass10.add(jLabel8);

        tgl1.setEditable(false);
        tgl1.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "02-06-2021" }));
        tgl1.setDisplayFormat("dd-MM-yyyy");
        tgl1.setName("tgl1"); // NOI18N
        tgl1.setOpaque(false);
        tgl1.setPreferredSize(new java.awt.Dimension(90, 23));
        panelGlass10.add(tgl1);

        jLabel21.setForeground(new java.awt.Color(0, 0, 0));
        jLabel21.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel21.setText("s.d.");
        jLabel21.setName("jLabel21"); // NOI18N
        jLabel21.setPreferredSize(new java.awt.Dimension(23, 23));
        panelGlass10.add(jLabel21);

        tgl2.setEditable(false);
        tgl2.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "02-06-2021" }));
        tgl2.setDisplayFormat("dd-MM-yyyy");
        tgl2.setName("tgl2"); // NOI18N
        tgl2.setOpaque(false);
        tgl2.setPreferredSize(new java.awt.Dimension(90, 23));
        panelGlass10.add(tgl2);

        jLabel23.setForeground(new java.awt.Color(0, 0, 0));
        jLabel23.setText("Jns. Rawat : ");
        jLabel23.setName("jLabel23"); // NOI18N
        jLabel23.setPreferredSize(new java.awt.Dimension(85, 23));
        panelGlass10.add(jLabel23);

        cmbJnsRawat.setForeground(new java.awt.Color(0, 0, 0));
        cmbJnsRawat.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "Semua", "JALAN", "INAP" }));
        cmbJnsRawat.setName("cmbJnsRawat"); // NOI18N
        cmbJnsRawat.setPreferredSize(new java.awt.Dimension(70, 23));
        cmbJnsRawat.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                cmbJnsRawatKeyPressed(evt);
            }
        });
        panelGlass10.add(cmbJnsRawat);

        internalFrame1.add(panelGlass10, java.awt.BorderLayout.PAGE_START);

        getContentPane().add(internalFrame1, java.awt.BorderLayout.CENTER);

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void TCariKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_TCariKeyPressed
        if (evt.getKeyCode() == KeyEvent.VK_ENTER) {
            BtnCariActionPerformed(null);
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

    private void BtnCariKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_BtnCariKeyReleased
        // TODO add your handling code here:
}//GEN-LAST:event_BtnCariKeyReleased

    private void BtnAllActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_BtnAllActionPerformed
        TCari.setText("");
        cmbJnsRawat.setSelectedIndex(0);
        tampil();
}//GEN-LAST:event_BtnAllActionPerformed

    private void BtnAllKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_BtnAllKeyPressed
        if (evt.getKeyCode() == KeyEvent.VK_SPACE) {
            BtnAllActionPerformed(null);
        } else {
            Valid.pindah(evt, BtnCari, TCari);
        }
}//GEN-LAST:event_BtnAllKeyPressed

    private void formWindowOpened(java.awt.event.WindowEvent evt) {//GEN-FIRST:event_formWindowOpened
        
    }//GEN-LAST:event_formWindowOpened

    private void BtnKeluarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_BtnKeluarActionPerformed
        dispose();
    }//GEN-LAST:event_BtnKeluarActionPerformed

    private void cmbJnsRawatKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_cmbJnsRawatKeyPressed
        // TODO add your handling code here:
    }//GEN-LAST:event_cmbJnsRawatKeyPressed

    private void MnPengajuanKlaimBtnPrintActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_MnPengajuanKlaimBtnPrintActionPerformed
        if (tabMode.getRowCount() == 0) {
            JOptionPane.showMessageDialog(null, "Maaf, tabel masih kosong...!!!!");
            TCari.requestFocus();
        } else if (norawat.equals("")) {
            JOptionPane.showMessageDialog(null, "Klik dulu salah satu nama pasiennya pada tabel...!!!!");
            tbData.requestFocus();
        } else {
            INACBGDaftarKlaim diklaim = new INACBGDaftarKlaim(null, false);
            diklaim.isCek();
            diklaim.setSize(internalFrame1.getWidth() - 40, internalFrame1.getHeight() - 40);
            diklaim.setLocationRelativeTo(internalFrame1);
            diklaim.verifData();
            diklaim.KlaimRAZA(norawat, noSEPnya, "JKN", "3");
            diklaim.setVisible(true);
        }
    }//GEN-LAST:event_MnPengajuanKlaimBtnPrintActionPerformed

    private void tbDataMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tbDataMouseClicked
        if(tabMode.getRowCount()!=0){
            try {
                getData();
            } catch (java.lang.NullPointerException e) {
            }
        }
    }//GEN-LAST:event_tbDataMouseClicked

    private void tbDataKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_tbDataKeyPressed
        if(tabMode.getRowCount()!=0){
            if((evt.getKeyCode()==KeyEvent.VK_ENTER)||(evt.getKeyCode()==KeyEvent.VK_UP)||(evt.getKeyCode()==KeyEvent.VK_DOWN)){
                try {
                    getData();
                } catch (java.lang.NullPointerException e) {
                }
            }
        }
    }//GEN-LAST:event_tbDataKeyPressed

    /**
     * @param args the command line arguments
     */
    public static void main(String args[]) {
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                INACBGjknBelumDiklaim dialog = new INACBGjknBelumDiklaim(new javax.swing.JFrame(), true);
                dialog.addWindowListener(new java.awt.event.WindowAdapter() {
                    public void windowClosing(java.awt.event.WindowEvent e) {
                        System.exit(0);
                    }
                });
                dialog.setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private widget.Button BtnAll;
    private widget.Button BtnCari;
    private widget.Button BtnKeluar;
    private widget.Label LCount;
    private javax.swing.JMenuItem MnPengajuanKlaim;
    private javax.swing.JPopupMenu Popup1;
    private widget.ScrollPane Scroll;
    private widget.TextBox TCari;
    public widget.ComboBox cmbJnsRawat;
    private widget.InternalFrame internalFrame1;
    private widget.Label jLabel21;
    private widget.Label jLabel23;
    private widget.Label jLabel6;
    private widget.Label jLabel7;
    private widget.Label jLabel8;
    private widget.panelisi panelGlass10;
    private widget.panelisi panelGlass9;
    private widget.Table tbData;
    private widget.Tanggal tgl1;
    private widget.Tanggal tgl2;
    // End of variables declaration//GEN-END:variables

    public void tampil() {
        jnsRWT = "";
        tglRalan = "";
        tglRanap = "";
//        tglKosong = "IF (rp.status_lanjut = 'Ralan', DATE_FORMAT(rp.tgl_registrasi,'%d-%m-%Y'),(SELECT DATE_FORMAT(ki1.tgl_keluar, '%d-%m-%Y') FROM kamar_inap ki1 WHERE ki1.no_rawat = rp.no_rawat AND ki1.stts_pulang <> 'Pindah Kamar'))<>'00-00-0000'";
        
        tglRalan = "rp.tgl_registrasi between '" + Valid.SetTgl(tgl1.getSelectedItem() + "") + "' and '" + Valid.SetTgl(tgl2.getSelectedItem() + "") + "' ";
        tglRanap = "(SELECT ki1.tgl_keluar FROM kamar_inap ki1 inner join reg_periksa rp1 on rp1.no_rawat=ki1.no_rawat WHERE ki1.stts_pulang <> 'Pindah Kamar')";
        Valid.tabelKosong(tabMode);
        
        if (cmbJnsRawat.getSelectedItem().equals("INAP")) {
            jnsRWT = "%ranap%";
        } else if (cmbJnsRawat.getSelectedItem().equals("JALAN")) {
            jnsRWT = "%ralan%";
        } else {
            jnsRWT = "%%";
        }
        
        // query nya masih salah hrs dicek lagi
        try {
            if (cmbJnsRawat.getSelectedIndex() == 0) {
                ps = koneksi.prepareStatement("SELECT bs.no_sep, bs.no_rawat, ps.no_rkm_medis, ps.nm_pasien, IF (rp.status_lanjut = 'Ralan',CONCAT('Inst./Poli ', p.nm_poli), CONCAT('Rg. ',(SELECT b.nm_bangsal FROM kamar_inap ki "
                        + "INNER JOIN kamar k ON k.kd_kamar = ki.kd_kamar INNER JOIN bangsal b ON b.kd_bangsal = k.kd_bangsal WHERE ki.no_rawat = rp.no_rawat AND ki.stts_pulang <> 'Pindah Kamar'))) unit, "
                        + "DATE_FORMAT(rp.tgl_registrasi, '%d-%m-%Y') tglRegMsk, IF (rp.status_lanjut = 'Ralan', DATE_FORMAT(rp.tgl_registrasi,'%d-%m-%Y'),(SELECT DATE_FORMAT(ki1.tgl_keluar, '%d-%m-%Y') FROM kamar_inap ki1 "
                        + "WHERE ki1.no_rawat = rp.no_rawat AND ki1.stts_pulang <> 'Pindah Kamar')) tglPlg, rp.status_lanjut FROM bridging_sep bs INNER JOIN reg_periksa rp ON rp.no_rawat = bs.no_rawat "
                        + "INNER JOIN poliklinik p ON p.kd_poli = rp.kd_poli INNER JOIN pasien ps ON ps.no_rkm_medis = rp.no_rkm_medis LEFT JOIN eklaim_new_claim enc ON enc.no_sep = bs.no_sep WHERE "
                        + "rp.status_lanjut like '" + jnsRWT + "' and bs.tglsep BETWEEN ? AND ? AND enc.no_sep IS NULL and bs.no_sep like ? or "
                        + "rp.status_lanjut like '" + jnsRWT + "' and bs.tglsep BETWEEN ? AND ? AND enc.no_sep IS NULL and bs.no_rawat like ? or "
                        + "rp.status_lanjut like '" + jnsRWT + "' and bs.tglsep BETWEEN ? AND ? AND enc.no_sep IS NULL and ps.no_rkm_medis like ? or "
                        + "rp.status_lanjut like '" + jnsRWT + "' and bs.tglsep BETWEEN ? AND ? AND enc.no_sep IS NULL and ps.nm_pasien like ? or "
                        + "rp.status_lanjut like '" + jnsRWT + "' and bs.tglsep BETWEEN ? AND ? AND enc.no_sep IS NULL and IF (rp.status_lanjut = 'Ralan',CONCAT('Inst./Poli ', p.nm_poli), CONCAT('Rg. ',(SELECT b.nm_bangsal FROM kamar_inap ki "
                        + "INNER JOIN kamar k ON k.kd_kamar = ki.kd_kamar INNER JOIN bangsal b ON b.kd_bangsal = k.kd_bangsal WHERE ki.no_rawat = rp.no_rawat AND ki.stts_pulang <> 'Pindah Kamar'))) like ? "
                        + "ORDER BY IF (rp.status_lanjut = 'Ralan', CONCAT('Inst./Poli ', p.nm_poli), CONCAT('Rg. ', (SELECT b.nm_bangsal FROM kamar_inap ki INNER JOIN kamar k ON k.kd_kamar = ki.kd_kamar "
                        + "INNER JOIN bangsal b ON b.kd_bangsal = k.kd_bangsal WHERE ki.no_rawat = rp.no_rawat AND ki.stts_pulang <> 'Pindah Kamar')))");
            } else if (cmbJnsRawat.getSelectedIndex() == 1) {
                ps = koneksi.prepareStatement("SELECT bs.no_sep, bs.no_rawat, ps.no_rkm_medis, ps.nm_pasien, IF (rp.status_lanjut = 'Ralan',CONCAT('Inst./Poli ', p.nm_poli), CONCAT('Rg. ',(SELECT b.nm_bangsal FROM kamar_inap ki "
                        + "INNER JOIN kamar k ON k.kd_kamar = ki.kd_kamar INNER JOIN bangsal b ON b.kd_bangsal = k.kd_bangsal WHERE ki.no_rawat = rp.no_rawat AND ki.stts_pulang <> 'Pindah Kamar'))) unit, "
                        + "DATE_FORMAT(rp.tgl_registrasi, '%d-%m-%Y') tglRegMsk, IF (rp.status_lanjut = 'Ralan', DATE_FORMAT(rp.tgl_registrasi,'%d-%m-%Y'),(SELECT DATE_FORMAT(ki1.tgl_keluar, '%d-%m-%Y') FROM kamar_inap ki1 "
                        + "WHERE ki1.no_rawat = rp.no_rawat AND ki1.stts_pulang <> 'Pindah Kamar')) tglPlg, rp.status_lanjut FROM bridging_sep bs INNER JOIN reg_periksa rp ON rp.no_rawat = bs.no_rawat "
                        + "INNER JOIN poliklinik p ON p.kd_poli = rp.kd_poli INNER JOIN pasien ps ON ps.no_rkm_medis = rp.no_rkm_medis LEFT JOIN eklaim_new_claim enc ON enc.no_sep = bs.no_sep WHERE "
                        + "rp.status_lanjut like '" + jnsRWT + "' and " + tglRalan + " and bs.tglsep BETWEEN ? AND ? AND enc.no_sep IS NULL and bs.no_sep like ? or "
                        + "rp.status_lanjut like '" + jnsRWT + "' and " + tglRalan + " and bs.tglsep BETWEEN ? AND ? AND enc.no_sep IS NULL and bs.no_rawat like ? or "
                        + "rp.status_lanjut like '" + jnsRWT + "' and " + tglRalan + " and bs.tglsep BETWEEN ? AND ? AND enc.no_sep IS NULL and ps.no_rkm_medis like ? or "
                        + "rp.status_lanjut like '" + jnsRWT + "' and " + tglRalan + " and bs.tglsep BETWEEN ? AND ? AND enc.no_sep IS NULL and ps.nm_pasien like ? or "
                        + "rp.status_lanjut like '" + jnsRWT + "' and " + tglRalan + " and bs.tglsep BETWEEN ? AND ? AND enc.no_sep IS NULL and IF (rp.status_lanjut = 'Ralan',CONCAT('Inst./Poli ', p.nm_poli), CONCAT('Rg. ',(SELECT b.nm_bangsal FROM kamar_inap ki "
                        + "INNER JOIN kamar k ON k.kd_kamar = ki.kd_kamar INNER JOIN bangsal b ON b.kd_bangsal = k.kd_bangsal WHERE ki.no_rawat = rp.no_rawat AND ki.stts_pulang <> 'Pindah Kamar'))) like ? "
                        + "ORDER BY IF (rp.status_lanjut = 'Ralan', CONCAT('Inst./Poli ', p.nm_poli), CONCAT('Rg. ', (SELECT b.nm_bangsal FROM kamar_inap ki INNER JOIN kamar k ON k.kd_kamar = ki.kd_kamar "
                        + "INNER JOIN bangsal b ON b.kd_bangsal = k.kd_bangsal WHERE ki.no_rawat = rp.no_rawat AND ki.stts_pulang <> 'Pindah Kamar')))");
            } else if (cmbJnsRawat.getSelectedIndex() == 2) {
                ps = koneksi.prepareStatement("SELECT bs.no_sep, bs.no_rawat, ps.no_rkm_medis, ps.nm_pasien, IF (rp.status_lanjut = 'Ralan',CONCAT('Inst./Poli ', p.nm_poli), CONCAT('Rg. ',(SELECT b.nm_bangsal FROM kamar_inap ki "
                        + "INNER JOIN kamar k ON k.kd_kamar = ki.kd_kamar INNER JOIN bangsal b ON b.kd_bangsal = k.kd_bangsal WHERE ki.no_rawat = rp.no_rawat AND ki.stts_pulang <> 'Pindah Kamar'))) unit, "
                        + "DATE_FORMAT(rp.tgl_registrasi, '%d-%m-%Y') tglRegMsk, IF (rp.status_lanjut = 'Ralan', DATE_FORMAT(rp.tgl_registrasi,'%d-%m-%Y'),(SELECT DATE_FORMAT(ki1.tgl_keluar, '%d-%m-%Y') FROM kamar_inap ki1 "
                        + "WHERE ki1.no_rawat = rp.no_rawat AND ki1.stts_pulang <> 'Pindah Kamar')) tglPlg, rp.status_lanjut FROM bridging_sep bs INNER JOIN reg_periksa rp ON rp.no_rawat = bs.no_rawat "
                        + "INNER JOIN poliklinik p ON p.kd_poli = rp.kd_poli INNER JOIN pasien ps ON ps.no_rkm_medis = rp.no_rkm_medis LEFT JOIN eklaim_new_claim enc ON enc.no_sep = bs.no_sep WHERE "
                        + "rp.status_lanjut like '" + jnsRWT + "' and " + tglRanap + " and bs.tglsep BETWEEN ? AND ? AND enc.no_sep IS NULL and bs.no_sep like ? or "
                        + "rp.status_lanjut like '" + jnsRWT + "' and " + tglRanap + " and bs.tglsep BETWEEN ? AND ? AND enc.no_sep IS NULL and bs.no_rawat like ? or "
                        + "rp.status_lanjut like '" + jnsRWT + "' and " + tglRanap + " and bs.tglsep BETWEEN ? AND ? AND enc.no_sep IS NULL and ps.no_rkm_medis like ? or "
                        + "rp.status_lanjut like '" + jnsRWT + "' and " + tglRanap + " and bs.tglsep BETWEEN ? AND ? AND enc.no_sep IS NULL and ps.nm_pasien like ? or "
                        + "rp.status_lanjut like '" + jnsRWT + "' and " + tglRanap + " and bs.tglsep BETWEEN ? AND ? AND enc.no_sep IS NULL and IF (rp.status_lanjut = 'Ralan',CONCAT('Inst./Poli ', p.nm_poli), CONCAT('Rg. ',(SELECT b.nm_bangsal FROM kamar_inap ki "
                        + "INNER JOIN kamar k ON k.kd_kamar = ki.kd_kamar INNER JOIN bangsal b ON b.kd_bangsal = k.kd_bangsal WHERE ki.no_rawat = rp.no_rawat AND ki.stts_pulang <> 'Pindah Kamar'))) like ? "
                        + "ORDER BY IF (rp.status_lanjut = 'Ralan', CONCAT('Inst./Poli ', p.nm_poli), CONCAT('Rg. ', (SELECT b.nm_bangsal FROM kamar_inap ki INNER JOIN kamar k ON k.kd_kamar = ki.kd_kamar "
                        + "INNER JOIN bangsal b ON b.kd_bangsal = k.kd_bangsal WHERE ki.no_rawat = rp.no_rawat AND ki.stts_pulang <> 'Pindah Kamar')))");
            }
            
            try {
                ps.setString(1, Valid.SetTgl(tgl1.getSelectedItem() + ""));
                ps.setString(2, Valid.SetTgl(tgl2.getSelectedItem() + ""));
                ps.setString(3, "%" + TCari.getText().trim() + "%");                
                ps.setString(4, Valid.SetTgl(tgl1.getSelectedItem() + ""));
                ps.setString(5, Valid.SetTgl(tgl2.getSelectedItem() + ""));
                ps.setString(6, "%" + TCari.getText().trim() + "%");                
                ps.setString(7, Valid.SetTgl(tgl1.getSelectedItem() + ""));
                ps.setString(8, Valid.SetTgl(tgl2.getSelectedItem() + ""));
                ps.setString(9, "%" + TCari.getText().trim() + "%");                
                ps.setString(10, Valid.SetTgl(tgl1.getSelectedItem() + ""));
                ps.setString(11, Valid.SetTgl(tgl2.getSelectedItem() + ""));
                ps.setString(12, "%" + TCari.getText().trim() + "%");                
                ps.setString(13, Valid.SetTgl(tgl1.getSelectedItem() + ""));
                ps.setString(14, Valid.SetTgl(tgl2.getSelectedItem() + ""));
                ps.setString(15, "%" + TCari.getText().trim() + "%");
                rs = ps.executeQuery();
                while (rs.next()) {
                    tabMode.addRow(new Object[]{
                        rs.getString("no_sep"), 
                        rs.getString("no_rawat"), 
                        rs.getString("no_rkm_medis"),
                        rs.getString("nm_pasien"),
                        rs.getString("unit"),
                        rs.getString("tglRegMsk"),
                        rs.getString("tglPlg"),
                        rs.getString("status_lanjut")
                    });
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
        } catch (SQLException e) {
            System.out.println("Notifikasi : " + e);
        }
        LCount.setText("" + tabMode.getRowCount());
    }

    public JTable getTable() {
        return tbData;
    }
    
    public void emptText() {
        TCari.setText("");
        tgl.setDate(1);
        tgl1.setDate(tgl);
        tgl2.setDate(new Date());
        cmbJnsRawat.setSelectedIndex(0);
        norawat = "";
        noSEPnya = "";
    }
    
    public void isCek() {
        MnPengajuanKlaim.setEnabled(var.getjkn_belum_diproses_klaim());
    }
    
    private void getData() {
        norawat = "";
        noSEPnya = "";
        if (tbData.getSelectedRow() != -1) {
            norawat = tbData.getValueAt(tbData.getSelectedRow(), 1).toString();
            noSEPnya = tbData.getValueAt(tbData.getSelectedRow(), 0).toString();
        }
    }
}
