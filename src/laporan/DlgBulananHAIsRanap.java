package laporan;
import keuangan.*;
import fungsi.koneksiDB;
import fungsi.sekuel;
import fungsi.validasi;
import fungsi.var;
import java.awt.Cursor;
import java.awt.Desktop;
import java.awt.Dimension;
import java.awt.Toolkit;
import java.awt.event.KeyEvent;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import javax.swing.text.Document;
import javax.swing.text.html.HTMLEditorKit;
import javax.swing.text.html.StyleSheet;

public class DlgBulananHAIsRanap extends javax.swing.JDialog {
    private sekuel Sequel=new sekuel();
    private validasi Valid=new validasi();
    private Jurnal jur=new Jurnal();
    private Connection koneksi=koneksiDB.condb();
    private PreparedStatement ps;
    private ResultSet rs;
    private int i=0,deku=0,urine=0,sputum=0,darah=0;
    private String ruangan="";
    
    /** Creates new form DlgProgramStudi
     * @param parent
     * @param modal */
    public DlgBulananHAIsRanap(java.awt.Frame parent, boolean modal) {
        super(parent, modal);
        initComponents();
        
        
        HTMLEditorKit kit = new HTMLEditorKit();
        LoadHTML.setEditable(true);
        LoadHTML.setEditorKit(kit);
        LoadHTML.setEditable(true);
        LoadHTML.setEditorKit(kit);
        StyleSheet styleSheet = kit.getStyleSheet();
        styleSheet.addRule(
                ".isi td{border-right: 1px solid #edf2e8;font: 8.5px tahoma;height:12px;border-bottom: 1px solid #edf2e8;background: 0000000;color:0000000;}"+
                ".isi2 td{font: 8.5px tahoma;height:12px;background: 0000000;color:0000000;}"+
                ".isi3 td{border-right: 1px solid #edf2e8;font: 8.5px tahoma;height:12px;border-top: 1px solid #edf2e8;background: 0000000;color:0000000;}"+
                ".isi4 td{font: 11px tahoma;height:12px;border-top: 1px solid 0000000;background: 0000000;color:0000000;}"
        );
        Document doc = kit.createDefaultDocument();
        LoadHTML.setDocument(doc);
        LoadHTML.setDocument(doc);
    }
    private Dimension screen=Toolkit.getDefaultToolkit().getScreenSize();

    /** This method is called from within the constructor to
     * initialize the form.
     * WARNING Do NOT modify this code. The content of this method is
     * always regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        Kd2 = new widget.TextBox();
        internalFrame1 = new widget.InternalFrame();
        panelisi1 = new widget.panelisi();
        label11 = new widget.Label();
        Tgl1 = new widget.Tanggal();
        label18 = new widget.Label();
        Tgl2 = new widget.Tanggal();
        btnCari = new widget.Button();
        jLabel8 = new widget.Label();
        cmbRuangan = new widget.ComboBox();
        label9 = new widget.Label();
        BtnPrint = new widget.Button();
        BtnKeluar = new widget.Button();
        Scroll = new widget.ScrollPane();
        LoadHTML = new widget.editorpane();

        Kd2.setName("Kd2"); // NOI18N
        Kd2.setPreferredSize(new java.awt.Dimension(207, 23));

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);
        setUndecorated(true);
        setResizable(false);
        addWindowListener(new java.awt.event.WindowAdapter() {
            public void windowOpened(java.awt.event.WindowEvent evt) {
                formWindowOpened(evt);
            }
        });

        internalFrame1.setBorder(javax.swing.BorderFactory.createTitledBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(240, 245, 235)), "::[ Laporan Bulanan HAIs Rawat Inap ]::", javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Tahoma", 1, 12), new java.awt.Color(0, 0, 0))); // NOI18N
        internalFrame1.setName("internalFrame1"); // NOI18N
        internalFrame1.setLayout(new java.awt.BorderLayout(1, 1));

        panelisi1.setName("panelisi1"); // NOI18N
        panelisi1.setPreferredSize(new java.awt.Dimension(100, 48));
        panelisi1.setLayout(new java.awt.FlowLayout(java.awt.FlowLayout.LEFT, 5, 9));

        label11.setForeground(new java.awt.Color(0, 0, 0));
        label11.setText("Tanggal :");
        label11.setName("label11"); // NOI18N
        label11.setPreferredSize(new java.awt.Dimension(55, 23));
        panelisi1.add(label11);

        Tgl1.setEditable(false);
        Tgl1.setDisplayFormat("dd-MM-yyyy");
        Tgl1.setName("Tgl1"); // NOI18N
        Tgl1.setPreferredSize(new java.awt.Dimension(95, 23));
        panelisi1.add(Tgl1);

        label18.setForeground(new java.awt.Color(0, 0, 0));
        label18.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        label18.setText("s.d.");
        label18.setName("label18"); // NOI18N
        label18.setPreferredSize(new java.awt.Dimension(30, 23));
        panelisi1.add(label18);

        Tgl2.setEditable(false);
        Tgl2.setDisplayFormat("dd-MM-yyyy");
        Tgl2.setName("Tgl2"); // NOI18N
        Tgl2.setPreferredSize(new java.awt.Dimension(95, 23));
        panelisi1.add(Tgl2);

        btnCari.setForeground(new java.awt.Color(0, 0, 0));
        btnCari.setIcon(new javax.swing.ImageIcon(getClass().getResource("/picture/accept.png"))); // NOI18N
        btnCari.setMnemonic('2');
        btnCari.setText("Tampilkan Data");
        btnCari.setToolTipText("Alt+2");
        btnCari.setName("btnCari"); // NOI18N
        btnCari.setPreferredSize(new java.awt.Dimension(130, 23));
        btnCari.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnCariActionPerformed(evt);
            }
        });
        btnCari.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                btnCariKeyPressed(evt);
            }
        });
        panelisi1.add(btnCari);

        jLabel8.setForeground(new java.awt.Color(0, 0, 0));
        jLabel8.setText("Ruangan Inap :");
        jLabel8.setName("jLabel8"); // NOI18N
        jLabel8.setPreferredSize(new java.awt.Dimension(90, 30));
        panelisi1.add(jLabel8);

        cmbRuangan.setForeground(new java.awt.Color(0, 0, 0));
        cmbRuangan.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "SEMUA RUANGAN", "ZAAL", "RKPD", "PARU", "JANTUNG", "AS-SAMI", "ANAK", "BEDAH", "INTERNIST", "ICCU", "NICU", "OBGYN", "VIP", "SVIP", "BAYI-SEHAT", "AR-RAUDAH", "SYARAF", "MATA-THT-KK", "ISOLASI COVID19", "ISOLASI BAYI COVID19", "COVID19" }));
        cmbRuangan.setName("cmbRuangan"); // NOI18N
        cmbRuangan.setPreferredSize(new java.awt.Dimension(150, 23));
        cmbRuangan.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                cmbRuanganMouseClicked(evt);
            }
        });
        cmbRuangan.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                cmbRuanganKeyPressed(evt);
            }
        });
        panelisi1.add(cmbRuangan);

        label9.setForeground(new java.awt.Color(0, 0, 0));
        label9.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        label9.setName("label9"); // NOI18N
        label9.setPreferredSize(new java.awt.Dimension(120, 30));
        panelisi1.add(label9);

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
        panelisi1.add(BtnPrint);

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

        internalFrame1.add(panelisi1, java.awt.BorderLayout.PAGE_END);

        Scroll.setName("Scroll"); // NOI18N
        Scroll.setOpaque(true);

        LoadHTML.setBorder(null);
        LoadHTML.setName("LoadHTML"); // NOI18N
        Scroll.setViewportView(LoadHTML);

        internalFrame1.add(Scroll, java.awt.BorderLayout.CENTER);

        getContentPane().add(internalFrame1, java.awt.BorderLayout.CENTER);

        pack();
    }// </editor-fold>//GEN-END:initComponents
/*
private void KdKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_TKdKeyPressed
    Valid.pindah(evt,BtnCari,Nm);
}//GEN-LAST:event_TKdKeyPressed
*/

    private void BtnPrintActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_BtnPrintActionPerformed
        this.setCursor(Cursor.getPredefinedCursor(Cursor.WAIT_CURSOR));
        try {

            File g = new File("file5.css");
            BufferedWriter bg = new BufferedWriter(new FileWriter(g));
            bg.write(
                    ".isi td{border-right: 1px solid #edf2e8;font: 11px tahoma;height:12px;border-bottom: 1px solid #edf2e8;background: 0000000;color:0000000;}"
                    + ".isi2 td{font: 11px tahoma;height:12px;background: 0000000;color:0000000;}"
                    + ".isi3 td{border-right: 1px solid #edf2e8;font: 11px tahoma;height:12px;border-top: 1px solid #edf2e8;background: 0000000;color:0000000;}"
                    + ".isi4 td{font: 11px tahoma;height:12px;border-top: 1px solid 0000000;background: 0000000;color:0000000;}"
            );
            bg.close();

            File f = new File("BulananHAIsRanap.html");
            BufferedWriter bw = new BufferedWriter(new FileWriter(f));
            bw.write(LoadHTML.getText().replaceAll(
                    "<head>", "<head><link href=\"file5.css\" rel=\"stylesheet\" type=\"text/css\" />"
                    + "<table width='100%' border='0' align='center' cellpadding='3px' cellspacing='0' class='tbl_form'>"
                    + "<tr class='isi2'>"
                    + "<td valign='top' align='center'>"
                    + "<font size='4' face='Tahoma'>" + var.getnamars() + "</font><br>"
                    + var.getalamatrs() + ", " + var.getkabupatenrs() + ", " + var.getpropinsirs() + "<br>"
                    + var.getkontakrs() + "e-Mail : " + var.getemailrs() + "<br>"
                    + "<br>"
                    + "LAPORAN BULANAN DATA HAIs RAWAT INAP<br>"
                    + "RUANG PERAWATAN INAP : " + cmbRuangan.getSelectedItem() + "<br>"
                    + "PERIODE TGL. " + Tgl1.getSelectedItem() + " S.D. " + Tgl2.getSelectedItem() + "<br>"
                    + "<br>"
                    + "</td>"
                    + "</tr>"
                    + "</table>")
            );
            bw.close();
            Desktop.getDesktop().browse(f.toURI());
        } catch (Exception e) {
            System.out.println("Notifikasi : " + e);
        }

        this.setCursor(Cursor.getDefaultCursor());
    }//GEN-LAST:event_BtnPrintActionPerformed

    private void BtnPrintKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_BtnPrintKeyPressed
        if(evt.getKeyCode()==KeyEvent.VK_SPACE){
            BtnPrintActionPerformed(null);
        }else{
            Valid.pindah(evt,Tgl2,BtnKeluar);
        }
    }//GEN-LAST:event_BtnPrintKeyPressed

    private void BtnKeluarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_BtnKeluarActionPerformed
        dispose();
    }//GEN-LAST:event_BtnKeluarActionPerformed

    private void BtnKeluarKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_BtnKeluarKeyPressed
        if(evt.getKeyCode()==KeyEvent.VK_SPACE){
            dispose();
        }else{Valid.pindah(evt,BtnPrint,Tgl1);}
    }//GEN-LAST:event_BtnKeluarKeyPressed

private void btnCariActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnCariActionPerformed
    prosesCari();
}//GEN-LAST:event_btnCariActionPerformed

private void btnCariKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_btnCariKeyPressed
        if(evt.getKeyCode()==KeyEvent.VK_SPACE){
            btnCariActionPerformed(null);
        }else{
            Valid.pindah(evt, Tgl2, BtnPrint);
        }
}//GEN-LAST:event_btnCariKeyPressed

    private void formWindowOpened(java.awt.event.WindowEvent evt) {//GEN-FIRST:event_formWindowOpened
        prosesCari();
    }//GEN-LAST:event_formWindowOpened

    private void cmbRuanganMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_cmbRuanganMouseClicked
        cmbRuangan.setEditable(false);
    }//GEN-LAST:event_cmbRuanganMouseClicked

    private void cmbRuanganKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_cmbRuanganKeyPressed
        // TODO add your handling code here:
    }//GEN-LAST:event_cmbRuanganKeyPressed

    /**
    * @param args the command line arguments
    */
    public static void main(String args[]) {
        java.awt.EventQueue.invokeLater(() -> {
            DlgBulananHAIsRanap dialog = new DlgBulananHAIsRanap(new javax.swing.JFrame(), true);
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
    private widget.Button BtnKeluar;
    private widget.Button BtnPrint;
    private widget.TextBox Kd2;
    private widget.editorpane LoadHTML;
    private widget.ScrollPane Scroll;
    private widget.Tanggal Tgl1;
    private widget.Tanggal Tgl2;
    private widget.Button btnCari;
    private widget.ComboBox cmbRuangan;
    private widget.InternalFrame internalFrame1;
    private widget.Label jLabel8;
    private widget.Label label11;
    private widget.Label label18;
    private widget.Label label9;
    private widget.panelisi panelisi1;
    // End of variables declaration//GEN-END:variables

    private void prosesCari() {
        ruangan = "";
        if (!cmbRuangan.getSelectedItem().toString().equals("SEMUA RUANGAN")) {
            ruangan = " b.nm_bangsal like '%" + cmbRuangan.getSelectedItem() + "%' ";
        } else {
            ruangan = " b.nm_bangsal like '%%' ";
        }
        
        this.setCursor(Cursor.getPredefinedCursor(Cursor.WAIT_CURSOR));
        try {
            StringBuilder htmlContent = new StringBuilder();
            htmlContent.append(                             
                "<tr class='isi'>"+
                    "<td valign='middle' bgcolor='#f8fdf3' align='center' rowspan='2'>No.</td>"+
                    "<td valign='middle' bgcolor='#f8fdf3' align='center' rowspan='2'>Tanggal</td>"+
                    "<td valign='middle' bgcolor='#f8fdf3' align='center' rowspan='2'>Jml.Pasien</td>"+
                    "<td valign='middle' bgcolor='#f8fdf3' align='center' colspan='4'>Hari Pemasangan</td>"+
                    "<td valign='middle' bgcolor='#f8fdf3' align='center' colspan='5'>Infeksi</td>"+
                    "<td valign='middle' bgcolor='#f8fdf3' align='center' rowspan='2'>Deku</td>"+
                    "<td valign='middle' bgcolor='#f8fdf3' align='center' colspan='3'>Hasil Kultur</td>"+
                    "<td valign='middle' bgcolor='#f8fdf3' align='center' rowspan='2'>Antibiotik</td>"+
                "</tr>"+
                "<tr class='isi'>"+
                    "<td valign='middle' bgcolor='#f8fdf3' align='center'>ETT</td>"+
                    "<td valign='middle' bgcolor='#f8fdf3' align='center'>CVL</td>"+
                    "<td valign='middle' bgcolor='#f8fdf3' align='center'>IVL</td>"+
                    "<td valign='middle' bgcolor='#f8fdf3' align='center'>UC</td>"+
                    "<td valign='middle' bgcolor='#f8fdf3' align='center'>VAP</td>"+
                    "<td valign='middle' bgcolor='#f8fdf3' align='center'>IAD</td>"+
                    "<td valign='middle' bgcolor='#f8fdf3' align='center'>PLEB</td>"+
                    "<td valign='middle' bgcolor='#f8fdf3' align='center'>ISK</td>"+
                    "<td valign='middle' bgcolor='#f8fdf3' align='center'>IDO/ILO</td>"+
                    "<td valign='middle' bgcolor='#f8fdf3' align='center'>Sputum</td>"+
                    "<td valign='middle' bgcolor='#f8fdf3' align='center'>Darah</td>"+
                    "<td valign='middle' bgcolor='#f8fdf3' align='center'>Urine</td>"+
                "</tr>"
            );     
            ps=koneksi.prepareStatement(
                    "SELECT dh.tanggal FROM data_hais dh INNER JOIN reg_periksa rp on rp.no_rawat=dh.no_rawat INNER JOIN kamar_inap ki on ki.no_rawat=rp.no_rawat "
                    + "INNER JOIN kamar k on k.kd_kamar=ki.kd_kamar INNER JOIN bangsal b on b.kd_bangsal=k.kd_bangsal "
                    + "WHERE dh.tanggal BETWEEN ? and ? and " + ruangan + " and rp.status_lanjut='ranap' and ki.stts_pulang not in ('-','Pindah Kamar') "
                    + "and rp.stts_daftar<>'batal' GROUP BY dh.tanggal ORDER BY dh.tanggal");
            try {
                i=1;
                ps.setString(1,Valid.SetTgl(Tgl1.getSelectedItem()+""));
                ps.setString(2,Valid.SetTgl(Tgl2.getSelectedItem()+""));
                rs=ps.executeQuery();
                while(rs.next()){
                    htmlContent.append(
                            "<tr class='isi'>"
                            + "<td valign='middle' align='center'>" + i + "</td>"
                            + "<td valign='middle' align='center'>" + rs.getString("tanggal") + "</td>"
                            + "<td valign='middle' align='center'>" + Sequel.cariIsi("select count(dh.no_rawat) FROM data_hais dh INNER JOIN reg_periksa rp on rp.no_rawat=dh.no_rawat "
                                    + "INNER JOIN kamar_inap ki on ki.no_rawat=rp.no_rawat INNER JOIN kamar k on k.kd_kamar=ki.kd_kamar INNER JOIN bangsal b on b.kd_bangsal=k.kd_bangsal "
                                    + "where dh.tanggal=? and " + ruangan + " and rp.status_lanjut='ranap' and ki.stts_pulang not in ('-','Pindah Kamar') "
                                    + "and rp.stts_daftar<>'batal'", rs.getString("tanggal")) + "</td>"
                                            
                            + "<td valign='middle' align='center'>" + Sequel.cariIsi("select sum(dh.ETT) FROM data_hais dh INNER JOIN reg_periksa rp on rp.no_rawat=dh.no_rawat "
                                    + "INNER JOIN kamar_inap ki on ki.no_rawat=rp.no_rawat INNER JOIN kamar k on k.kd_kamar=ki.kd_kamar INNER JOIN bangsal b on b.kd_bangsal=k.kd_bangsal "
                                    + "where dh.tanggal=? and " + ruangan + " and rp.status_lanjut='ranap' and ki.stts_pulang not in ('-','Pindah Kamar') "
                                    + "and rp.stts_daftar<>'batal'", rs.getString("tanggal")) + "</td>"
                                            
                            + "<td valign='middle' align='center'>" + Sequel.cariIsi("select sum(dh.CVL) FROM data_hais dh INNER JOIN reg_periksa rp on rp.no_rawat=dh.no_rawat "
                                    + "INNER JOIN kamar_inap ki on ki.no_rawat=rp.no_rawat INNER JOIN kamar k on k.kd_kamar=ki.kd_kamar INNER JOIN bangsal b on b.kd_bangsal=k.kd_bangsal "
                                    + "where dh.tanggal=? and " + ruangan + " and rp.status_lanjut='ranap' and ki.stts_pulang not in ('-','Pindah Kamar') "
                                    + "and rp.stts_daftar<>'batal'", rs.getString("tanggal")) + "</td>"   
                                            
                            + "<td valign='middle' align='center'>" + Sequel.cariIsi("select sum(dh.IVL) FROM data_hais dh INNER JOIN reg_periksa rp on rp.no_rawat=dh.no_rawat "
                                    + "INNER JOIN kamar_inap ki on ki.no_rawat=rp.no_rawat INNER JOIN kamar k on k.kd_kamar=ki.kd_kamar INNER JOIN bangsal b on b.kd_bangsal=k.kd_bangsal "
                                    + "where dh.tanggal=? and " + ruangan + " and rp.status_lanjut='ranap' and ki.stts_pulang not in ('-','Pindah Kamar') "
                                    + "and rp.stts_daftar<>'batal'", rs.getString("tanggal")) + "</td>"
                            
                            + "<td valign='middle' align='center'>" + Sequel.cariIsi("select sum(dh.UC) FROM data_hais dh INNER JOIN reg_periksa rp on rp.no_rawat=dh.no_rawat "
                                    + "INNER JOIN kamar_inap ki on ki.no_rawat=rp.no_rawat INNER JOIN kamar k on k.kd_kamar=ki.kd_kamar INNER JOIN bangsal b on b.kd_bangsal=k.kd_bangsal "
                                    + "where dh.tanggal=? and " + ruangan + " and rp.status_lanjut='ranap' and ki.stts_pulang not in ('-','Pindah Kamar') "
                                    + "and rp.stts_daftar<>'batal'", rs.getString("tanggal")) + "</td>"
                                            
                            + "<td valign='middle' align='center'>" + Sequel.cariIsi("select sum(dh.VAP) FROM data_hais dh INNER JOIN reg_periksa rp on rp.no_rawat=dh.no_rawat "
                                    + "INNER JOIN kamar_inap ki on ki.no_rawat=rp.no_rawat INNER JOIN kamar k on k.kd_kamar=ki.kd_kamar INNER JOIN bangsal b on b.kd_bangsal=k.kd_bangsal "
                                    + "where dh.tanggal=? and " + ruangan + " and rp.status_lanjut='ranap' and ki.stts_pulang not in ('-','Pindah Kamar') "
                                    + "and rp.stts_daftar<>'batal'", rs.getString("tanggal")) + "</td>"
                                            
                            + "<td valign='middle' align='center'>" + Sequel.cariIsi("select sum(dh.IAD) FROM data_hais dh INNER JOIN reg_periksa rp on rp.no_rawat=dh.no_rawat "
                                    + "INNER JOIN kamar_inap ki on ki.no_rawat=rp.no_rawat INNER JOIN kamar k on k.kd_kamar=ki.kd_kamar INNER JOIN bangsal b on b.kd_bangsal=k.kd_bangsal "
                                    + "where dh.tanggal=? and " + ruangan + " and rp.status_lanjut='ranap' and ki.stts_pulang not in ('-','Pindah Kamar') "
                                    + "and rp.stts_daftar<>'batal'", rs.getString("tanggal")) + "</td>"
                                            
                            + "<td valign='middle' align='center'>" + Sequel.cariIsi("select sum(dh.PLEB) FROM data_hais dh INNER JOIN reg_periksa rp on rp.no_rawat=dh.no_rawat "
                                    + "INNER JOIN kamar_inap ki on ki.no_rawat=rp.no_rawat INNER JOIN kamar k on k.kd_kamar=ki.kd_kamar INNER JOIN bangsal b on b.kd_bangsal=k.kd_bangsal "
                                    + "where dh.tanggal=? and " + ruangan + " and rp.status_lanjut='ranap' and ki.stts_pulang not in ('-','Pindah Kamar') "
                                    + "and rp.stts_daftar<>'batal'", rs.getString("tanggal")) + "</td>"
                                            
                            + "<td valign='middle' align='center'>" + Sequel.cariIsi("select sum(dh.ISK) FROM data_hais dh INNER JOIN reg_periksa rp on rp.no_rawat=dh.no_rawat "
                                    + "INNER JOIN kamar_inap ki on ki.no_rawat=rp.no_rawat INNER JOIN kamar k on k.kd_kamar=ki.kd_kamar INNER JOIN bangsal b on b.kd_bangsal=k.kd_bangsal "
                                    + "where dh.tanggal=? and " + ruangan + " and rp.status_lanjut='ranap' and ki.stts_pulang not in ('-','Pindah Kamar') "
                                    + "and rp.stts_daftar<>'batal'", rs.getString("tanggal")) + "</td>"
                                            
                            + "<td valign='middle' align='center'>" + Sequel.cariIsi("select sum(dh.IDO_ILO) FROM data_hais dh INNER JOIN reg_periksa rp on rp.no_rawat=dh.no_rawat "
                                    + "INNER JOIN kamar_inap ki on ki.no_rawat=rp.no_rawat INNER JOIN kamar k on k.kd_kamar=ki.kd_kamar INNER JOIN bangsal b on b.kd_bangsal=k.kd_bangsal "
                                    + "where dh.tanggal=? and " + ruangan + " and rp.status_lanjut='ranap' and ki.stts_pulang not in ('-','Pindah Kamar') "
                                    + "and rp.stts_daftar<>'batal'", rs.getString("tanggal")) + "</td>"
                                            
                            + "<td valign='middle' align='center'>" + Sequel.cariIsi("select count(dh.no_rawat) FROM data_hais dh INNER JOIN reg_periksa rp on rp.no_rawat=dh.no_rawat "
                                    + "INNER JOIN kamar_inap ki on ki.no_rawat=rp.no_rawat INNER JOIN kamar k on k.kd_kamar=ki.kd_kamar INNER JOIN bangsal b on b.kd_bangsal=k.kd_bangsal "
                                    + "where dh.tanggal=? and " + ruangan + " and DEKU='IYA' and rp.status_lanjut='ranap' and ki.stts_pulang not in ('-','Pindah Kamar') "
                                    + "and rp.stts_daftar<>'batal'", rs.getString("tanggal")) + "</td>"
                                            
                            + "<td valign='middle' align='center'>" + Sequel.cariIsi("select count(dh.no_rawat) FROM data_hais dh INNER JOIN reg_periksa rp on rp.no_rawat=dh.no_rawat "
                                    + "INNER JOIN kamar_inap ki on ki.no_rawat=rp.no_rawat INNER JOIN kamar k on k.kd_kamar=ki.kd_kamar INNER JOIN bangsal b on b.kd_bangsal=k.kd_bangsal "
                                    + "where dh.tanggal=? and " + ruangan + " and SPUTUM<>'' and rp.status_lanjut='ranap' and ki.stts_pulang not in ('-','Pindah Kamar') "
                                    + "and rp.stts_daftar<>'batal'", rs.getString("tanggal")) + "</td>"
                                            
                            + "<td valign='middle' align='center'>" + Sequel.cariIsi("select count(dh.no_rawat) FROM data_hais dh INNER JOIN reg_periksa rp on rp.no_rawat=dh.no_rawat "
                                    + "INNER JOIN kamar_inap ki on ki.no_rawat=rp.no_rawat INNER JOIN kamar k on k.kd_kamar=ki.kd_kamar INNER JOIN bangsal b on b.kd_bangsal=k.kd_bangsal "
                                    + "where dh.tanggal=? and " + ruangan + " and DARAH<>'' and rp.status_lanjut='ranap' and ki.stts_pulang not in ('-','Pindah Kamar') "
                                    + "and rp.stts_daftar<>'batal'", rs.getString("tanggal")) + "</td>"
                                            
                            + "<td valign='middle' align='center'>" + Sequel.cariIsi("select count(dh.no_rawat) FROM data_hais dh INNER JOIN reg_periksa rp on rp.no_rawat=dh.no_rawat "
                                    + "INNER JOIN kamar_inap ki on ki.no_rawat=rp.no_rawat INNER JOIN kamar k on k.kd_kamar=ki.kd_kamar INNER JOIN bangsal b on b.kd_bangsal=k.kd_bangsal "
                                    + "where dh.tanggal=? and " + ruangan + " and URINE<>'' and rp.status_lanjut='ranap' and ki.stts_pulang not in ('-','Pindah Kamar') "
                                    + "and rp.stts_daftar<>'batal'", rs.getString("tanggal")) + "</td>"
                                            
                            + "<td valign='middle' align='center'>" + Sequel.cariIsi("select count(dh.no_rawat) FROM data_hais dh INNER JOIN reg_periksa rp on rp.no_rawat=dh.no_rawat "
                                    + "INNER JOIN kamar_inap ki on ki.no_rawat=rp.no_rawat INNER JOIN kamar k on k.kd_kamar=ki.kd_kamar INNER JOIN bangsal b on b.kd_bangsal=k.kd_bangsal "
                                    + "where dh.tanggal=? and " + ruangan + " and ANTIBIOTIK<>'' and rp.status_lanjut='ranap' and ki.stts_pulang not in ('-','Pindah Kamar') "
                                    + "and rp.stts_daftar<>'batal'", rs.getString("tanggal")) + "</td>"
                            + "</tr>"
                    ); 
                    i++;
                }
            } catch (Exception e) {
                System.out.println("laporan.DlgHarianHAIsRanap.prosesCari() : "+e);
            } finally{
                if(rs!=null){
                    rs.close();
                }
                if(ps!=null){
                    ps.close();
                }
            }
            
            LoadHTML.setText(
                    "<html>"+
                      "<table width='100%' border='0' align='center' cellpadding='3px' cellspacing='0' class='tbl_form'>"+
                       htmlContent.toString()+
                      "</table>"+
                    "</html>");
        } catch (Exception e) {
            System.out.println("laporan.DlgHarianHAIsRanap.prosesCari() 5 : "+e);
        } 
        this.setCursor(Cursor.getDefaultCursor());
        
    }
    
    public void isCek(){
        BtnPrint.setEnabled(var.getbulanan_HAIs());
    }
    
}
