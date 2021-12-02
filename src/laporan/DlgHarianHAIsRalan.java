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
import java.awt.event.WindowEvent;
import java.awt.event.WindowListener;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import javax.swing.text.Document;
import javax.swing.text.html.HTMLEditorKit;
import javax.swing.text.html.StyleSheet;
import simrskhanza.DlgCariPoli;

public class DlgHarianHAIsRalan extends javax.swing.JDialog {
    private sekuel Sequel=new sekuel();
    private validasi Valid=new validasi();
    private Jurnal jur=new Jurnal();
    private Connection koneksi=koneksiDB.condb();
    private PreparedStatement ps;
    private ResultSet rs;
    private int i=0,deku=0,urine=0,sputum=0,darah=0;
    private String lihat_deku="",poli="";
    private DlgCariPoli poliklinik = new DlgCariPoli(null, false);
    
    /** Creates new form DlgProgramStudi
     * @param parent
     * @param modal */
    public DlgHarianHAIsRalan(java.awt.Frame parent, boolean modal) {
        super(parent, modal);
        initComponents();
        
        HTMLEditorKit kit = new HTMLEditorKit();
        LoadHTML.setEditable(true);
        LoadHTML.setEditorKit(kit);
        LoadHTML.setEditable(true);
        LoadHTML.setEditorKit(kit);
        StyleSheet styleSheet = kit.getStyleSheet();
        styleSheet.addRule(
                ".isi td{border-right: 1px solid #edf2e8;font: 8.5px tahoma;height:12px;border-bottom: 1px solid #edf2e8;background: 0000000;color:0000000;}"
                + ".isi2 td{font: 8.5px tahoma;height:12px;background: 0000000;color:0000000;}"
                + ".isi3 td{border-right: 1px solid #edf2e8;font: 8.5px tahoma;height:12px;border-top: 1px solid #edf2e8;background: 0000000;color:0000000;}"
                + ".isi4 td{font: 11px tahoma;height:12px;border-top: 1px solid 0000000;background: 0000000;color:0000000;}"
        );
        Document doc = kit.createDefaultDocument();
        LoadHTML.setDocument(doc);
        LoadHTML.setDocument(doc);
        
        poliklinik.addWindowListener(new WindowListener() {
            @Override
            public void windowOpened(WindowEvent e) {
            }

            @Override
            public void windowClosing(WindowEvent e) {
            }

            @Override
            public void windowClosed(WindowEvent e) {
                if (var.getform().equals("DlgHarianHAIsRalan")) {
                    if (poliklinik.getTable().getSelectedRow() != -1) {
                        kdpoli.setText(poliklinik.getTable().getValueAt(poliklinik.getTable().getSelectedRow(), 0).toString());
                        TPoli.setText(poliklinik.getTable().getValueAt(poliklinik.getTable().getSelectedRow(), 1).toString());
                        cekSemuaPoli.setSelected(false);
                        prosesCari();
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
        jLabel6 = new widget.Label();
        TCari = new widget.TextBox();
        btnCari = new widget.Button();
        label9 = new widget.Label();
        BtnPrint = new widget.Button();
        BtnKeluar = new widget.Button();
        panelisi2 = new widget.panelisi();
        jLabel25 = new widget.Label();
        cekDeku = new widget.ComboBox();
        label19 = new widget.Label();
        kdpoli = new widget.TextBox();
        TPoli = new widget.TextBox();
        BtnUnit = new widget.Button();
        cekSemuaPoli = new widget.CekBox();
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

        internalFrame1.setBorder(javax.swing.BorderFactory.createTitledBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(240, 245, 235)), "::[ Laporan Harian HAIs Rawat Jalan ]::", javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Tahoma", 1, 12), new java.awt.Color(0, 0, 0))); // NOI18N
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
        Tgl1.setForeground(new java.awt.Color(0, 0, 0));
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
        Tgl2.setForeground(new java.awt.Color(0, 0, 0));
        Tgl2.setDisplayFormat("dd-MM-yyyy");
        Tgl2.setName("Tgl2"); // NOI18N
        Tgl2.setPreferredSize(new java.awt.Dimension(95, 23));
        panelisi1.add(Tgl2);

        jLabel6.setForeground(new java.awt.Color(0, 0, 0));
        jLabel6.setText("Key Word :");
        jLabel6.setName("jLabel6"); // NOI18N
        jLabel6.setPreferredSize(new java.awt.Dimension(70, 23));
        panelisi1.add(jLabel6);

        TCari.setForeground(new java.awt.Color(0, 0, 0));
        TCari.setName("TCari"); // NOI18N
        TCari.setPreferredSize(new java.awt.Dimension(150, 23));
        TCari.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                TCariKeyPressed(evt);
            }
        });
        panelisi1.add(TCari);

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

        label9.setForeground(new java.awt.Color(0, 0, 0));
        label9.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        label9.setName("label9"); // NOI18N
        label9.setPreferredSize(new java.awt.Dimension(20, 30));
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

        panelisi2.setName("panelisi2"); // NOI18N
        panelisi2.setPreferredSize(new java.awt.Dimension(100, 45));
        panelisi2.setLayout(new java.awt.FlowLayout(java.awt.FlowLayout.LEFT, 5, 9));

        jLabel25.setForeground(new java.awt.Color(0, 0, 0));
        jLabel25.setText("Cek Dekubitus : ");
        jLabel25.setName("jLabel25"); // NOI18N
        jLabel25.setPreferredSize(new java.awt.Dimension(105, 14));
        panelisi2.add(jLabel25);

        cekDeku.setForeground(new java.awt.Color(0, 0, 0));
        cekDeku.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "SEMUA", "IYA", "TIDAK" }));
        cekDeku.setName("cekDeku"); // NOI18N
        cekDeku.setOpaque(false);
        cekDeku.setPreferredSize(new java.awt.Dimension(70, 23));
        cekDeku.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                cekDekuMouseClicked(evt);
            }
        });
        cekDeku.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                cekDekuKeyPressed(evt);
            }
        });
        panelisi2.add(cekDeku);

        label19.setForeground(new java.awt.Color(0, 0, 0));
        label19.setText("Nama Unit/Poliklinik :");
        label19.setName("label19"); // NOI18N
        label19.setPreferredSize(new java.awt.Dimension(110, 23));
        panelisi2.add(label19);

        kdpoli.setEditable(false);
        kdpoli.setForeground(new java.awt.Color(0, 0, 0));
        kdpoli.setDisabledTextColor(new java.awt.Color(0, 0, 0));
        kdpoli.setHighlighter(null);
        kdpoli.setName("kdpoli"); // NOI18N
        kdpoli.setPreferredSize(new java.awt.Dimension(55, 23));
        kdpoli.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                kdpoliKeyPressed(evt);
            }
        });
        panelisi2.add(kdpoli);

        TPoli.setEditable(false);
        TPoli.setForeground(new java.awt.Color(0, 0, 0));
        TPoli.setDisabledTextColor(new java.awt.Color(0, 0, 0));
        TPoli.setName("TPoli"); // NOI18N
        TPoli.setPreferredSize(new java.awt.Dimension(203, 23));
        panelisi2.add(TPoli);

        BtnUnit.setForeground(new java.awt.Color(0, 0, 0));
        BtnUnit.setIcon(new javax.swing.ImageIcon(getClass().getResource("/picture/190.png"))); // NOI18N
        BtnUnit.setMnemonic('4');
        BtnUnit.setToolTipText("ALt+4");
        BtnUnit.setName("BtnUnit"); // NOI18N
        BtnUnit.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                BtnUnitActionPerformed(evt);
            }
        });
        panelisi2.add(BtnUnit);

        cekSemuaPoli.setBackground(new java.awt.Color(255, 255, 250));
        cekSemuaPoli.setBorder(null);
        cekSemuaPoli.setForeground(new java.awt.Color(0, 0, 0));
        cekSemuaPoli.setText("Semua Poli/Unit");
        cekSemuaPoli.setBorderPainted(true);
        cekSemuaPoli.setBorderPaintedFlat(true);
        cekSemuaPoli.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        cekSemuaPoli.setHorizontalTextPosition(javax.swing.SwingConstants.RIGHT);
        cekSemuaPoli.setName("cekSemuaPoli"); // NOI18N
        cekSemuaPoli.setOpaque(false);
        cekSemuaPoli.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                cekSemuaPoliActionPerformed(evt);
            }
        });
        panelisi2.add(cekSemuaPoli);

        internalFrame1.add(panelisi2, java.awt.BorderLayout.PAGE_START);

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

            File g = new File("file6.css");
            BufferedWriter bg = new BufferedWriter(new FileWriter(g));
            bg.write(
                    ".isi td{border-right: 1px solid #edf2e8;font: 11px tahoma;height:12px;border-bottom: 1px solid #edf2e8;background: 0000000;color:0000000;}"
                    + ".isi2 td{font: 11px tahoma;height:12px;background: 0000000;color:0000000;}"
                    + ".isi3 td{border-right: 1px solid #edf2e8;font: 11px tahoma;height:12px;border-top: 1px solid #edf2e8;background: 0000000;color:0000000;}"
                    + ".isi4 td{font: 11px tahoma;height:12px;border-top: 1px solid 0000000;background: 0000000;color:0000000;}"
            );
            bg.close();

            File f = new File("HarianHAIsRalan.html");
            BufferedWriter bw = new BufferedWriter(new FileWriter(f));
            if (cekSemuaPoli.isSelected() == true) {
                bw.write(LoadHTML.getText().replaceAll(
                        "<head>", "<head><link href=\"file6.css\" rel=\"stylesheet\" type=\"text/css\" />"
                        + "<table width='100%' border='0' align='center' cellpadding='3px' cellspacing='0' class='tbl_form'>"
                        + "<tr class='isi2'>"
                        + "<td valign='top' align='center'>"
                        + "<font size='4' face='Tahoma'>" + var.getnamars() + "</font><br>"
                        + var.getalamatrs() + ", " + var.getkabupatenrs() + ", " + var.getpropinsirs() + "<br>"
                        + var.getkontakrs() + "e-Mail : " + var.getemailrs() + "<br>"
                        + "<br>"
                        + "LAPORAN HARIAN DATA HAIs RAWAT JALAN<br>"
                        + "POLIKLINIK/UNIT : SEMUA POLIKLINIK/UNIT<br>"
                        + "PERIODE TGL. " + Tgl1.getSelectedItem() + " S.D. " + Tgl2.getSelectedItem() + "<br>"
                        + "<br>"
                        + "</td>"
                        + "</tr>"
                        + "</table>")
                );
            } else {
                bw.write(LoadHTML.getText().replaceAll(
                        "<head>", "<head><link href=\"file6.css\" rel=\"stylesheet\" type=\"text/css\" />"
                        + "<table width='100%' border='0' align='center' cellpadding='3px' cellspacing='0' class='tbl_form'>"
                        + "<tr class='isi2'>"
                        + "<td valign='top' align='center'>"
                        + "<font size='4' face='Tahoma'>" + var.getnamars() + "</font><br>"
                        + var.getalamatrs() + ", " + var.getkabupatenrs() + ", " + var.getpropinsirs() + "<br>"
                        + var.getkontakrs() + "e-Mail : " + var.getemailrs() + "<br>"
                        + "<br>"
                        + "LAPORAN HARIAN DATA HAIs RAWAT JALAN<br>"
                        + "POLIKLINIK/UNIT : " + TPoli.getText() + "<br>"
                        + "PERIODE TGL. " + Tgl1.getSelectedItem() + " S.D. " + Tgl2.getSelectedItem() + "<br>"
                        + "<br>"
                        + "</td>"
                        + "</tr>"
                        + "</table>")
                );
            }
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
        cekSemuaPoli.setSelected(true);
        kdpoli.setText("");
        TPoli.setText("");
        cekDeku.setSelectedIndex(0);
        prosesCari();
    }//GEN-LAST:event_formWindowOpened

    private void TCariKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_TCariKeyPressed
        if(evt.getKeyCode()==KeyEvent.VK_ENTER){
            btnCariActionPerformed(null);
        }else if(evt.getKeyCode()==KeyEvent.VK_PAGE_DOWN){
            btnCari.requestFocus();
        }else if(evt.getKeyCode()==KeyEvent.VK_PAGE_UP){
            BtnKeluar.requestFocus();
        }
    }//GEN-LAST:event_TCariKeyPressed

    private void cekDekuMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_cekDekuMouseClicked

    }//GEN-LAST:event_cekDekuMouseClicked

    private void cekDekuKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_cekDekuKeyPressed

    }//GEN-LAST:event_cekDekuKeyPressed

    private void kdpoliKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_kdpoliKeyPressed
        if (evt.getKeyCode() == KeyEvent.VK_PAGE_DOWN) {
            //            Sequel.cariIsi("select nm_poli from poliklinik where kd_poli=?", TPoli, kdpoli.getText());
        } else if (evt.getKeyCode() == KeyEvent.VK_UP) {
            //            BtnUnitActionPerformed(null);
        } else {
            //            Valid.pindah(evt, kddokter, TNoRM);
        }
    }//GEN-LAST:event_kdpoliKeyPressed

    private void BtnUnitActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_BtnUnitActionPerformed
        TPoli.setText("");
        kdpoli.setText("");

        var.setform("DlgHarianHAIsRalan");
        poliklinik.isCek();
        poliklinik.setSize(internalFrame1.getWidth() - 40, internalFrame1.getHeight() - 40);
        poliklinik.setLocationRelativeTo(internalFrame1);
        poliklinik.setVisible(true);
    }//GEN-LAST:event_BtnUnitActionPerformed

    private void cekSemuaPoliActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_cekSemuaPoliActionPerformed
        if (cekSemuaPoli.isSelected() == true) {
            kdpoli.setText("");
            TPoli.setText("");
        }
    }//GEN-LAST:event_cekSemuaPoliActionPerformed

    /**
    * @param args the command line arguments
    */
    public static void main(String args[]) {
        java.awt.EventQueue.invokeLater(() -> {
            DlgHarianHAIsRalan dialog = new DlgHarianHAIsRalan(new javax.swing.JFrame(), true);
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
    private widget.Button BtnUnit;
    private widget.TextBox Kd2;
    private widget.editorpane LoadHTML;
    private widget.ScrollPane Scroll;
    private widget.TextBox TCari;
    private widget.TextBox TPoli;
    private widget.Tanggal Tgl1;
    private widget.Tanggal Tgl2;
    private widget.Button btnCari;
    private widget.ComboBox cekDeku;
    private widget.CekBox cekSemuaPoli;
    private widget.InternalFrame internalFrame1;
    private widget.Label jLabel25;
    private widget.Label jLabel6;
    private widget.TextBox kdpoli;
    private widget.Label label11;
    private widget.Label label18;
    private widget.Label label19;
    private widget.Label label9;
    private widget.panelisi panelisi1;
    private widget.panelisi panelisi2;
    // End of variables declaration//GEN-END:variables

    private void prosesCari() {
        lihat_deku = "";
        poli = "";
        
        if (!cekDeku.getSelectedItem().toString().equals("SEMUA")) {
            lihat_deku = " dh.DEKU='" + cekDeku.getSelectedItem() + "' and rp.status_lanjut='ralan' and rp.stts_daftar<>'batal' ";
        } else {
            lihat_deku = " dh.DEKU like '%%' and rp.status_lanjut='ralan' and rp.stts_daftar<>'batal' ";
        }
        
        if (!kdpoli.getText().equals("")) {
            poli = " and pl.kd_poli='" + kdpoli.getText() + "' ";
        } else {
            poli = " and pl.kd_poli like '%%' ";
        }
        
        this.setCursor(Cursor.getPredefinedCursor(Cursor.WAIT_CURSOR));
        try {
            StringBuilder htmlContent = new StringBuilder();
            htmlContent.append(                             
                "<tr class='isi'>"+
                    "<td valign='middle' bgcolor='#f8fdf3' align='center' rowspan='2'>No.</td>"+
                    "<td valign='middle' bgcolor='#f8fdf3' align='center' rowspan='2'>No.R.M</td>"+
                    "<td valign='middle' bgcolor='#f8fdf3' align='center' rowspan='2'>Nama Pasien</td>"+
                    "<td valign='middle' bgcolor='#f8fdf3' align='center' rowspan='2'>Tanggal</td>"+
                    "<td valign='middle' bgcolor='#f8fdf3' align='center' colspan='4'>Hari Pemasangan</td>"+
                    "<td valign='middle' bgcolor='#f8fdf3' align='center' colspan='5'>Infeksi</td>"+
                    "<td valign='middle' bgcolor='#f8fdf3' align='center' rowspan='2'>Deku</td>"+
                    "<td valign='middle' bgcolor='#f8fdf3' align='center' colspan='3'>Hasil Kultur</td>"+
                    "<td valign='middle' bgcolor='#f8fdf3' align='center' rowspan='2'>Antibiotik</td>"+
                    "<td valign='middle' bgcolor='#f8fdf3' align='center' rowspan='2'>Poliklinik/Unit</td>"+
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
                    "SELECT dh.tanggal, dh.no_rawat, rp.no_rkm_medis, p.nm_pasien, dh.ETT, dh.CVL, "
                    + "dh.IVL, dh.UC, dh.VAP, dh.IAD, dh.PLEB, dh.ISK, dh.DEKU, dh.SPUTUM, dh.DARAH, "
                    + "dh.URINE, dh.ANTIBIOTIK, dh.IDO_ILO, pl.nm_poli FROM data_hais dh "
                    + "INNER JOIN reg_periksa rp on rp.no_rawat=dh.no_rawat INNER JOIN pasien p on p.no_rkm_medis=rp.no_rkm_medis "
                    + "INNER JOIN poliklinik pl on pl.kd_poli=rp.kd_poli where "
                    + "dh.tanggal between ? and ? and " + lihat_deku + poli + " and dh.no_rawat like ? or "
                    + "dh.tanggal between ? and ? and " + lihat_deku + poli + " and rp.no_rkm_medis like ? or "
                    + "dh.tanggal between ? and ? and " + lihat_deku + poli + " and dh.SPUTUM like ? or "
                    + "dh.tanggal between ? and ? and " + lihat_deku + poli + " and dh.DARAH like ? or "
                    + "dh.tanggal between ? and ? and " + lihat_deku + poli + " and dh.URINE like ? or "
                    + "dh.tanggal between ? and ? and " + lihat_deku + poli + " and dh.ANTIBIOTIK like ? or "
                    + "dh.tanggal between ? and ? and " + lihat_deku + poli + " and p.nm_pasien like ? order by dh.tanggal");
            try {
                i=1;
                ps.setString(1, Valid.SetTgl(Tgl1.getSelectedItem() + ""));
                ps.setString(2, Valid.SetTgl(Tgl2.getSelectedItem() + ""));
                ps.setString(3, "%" + TCari.getText() + "%");
                ps.setString(4, Valid.SetTgl(Tgl1.getSelectedItem() + ""));
                ps.setString(5, Valid.SetTgl(Tgl2.getSelectedItem() + ""));
                ps.setString(6, "%" + TCari.getText() + "%");
                ps.setString(7, Valid.SetTgl(Tgl1.getSelectedItem() + ""));
                ps.setString(8, Valid.SetTgl(Tgl2.getSelectedItem() + ""));
                ps.setString(9, "%" + TCari.getText() + "%");
                ps.setString(10, Valid.SetTgl(Tgl1.getSelectedItem() + ""));
                ps.setString(11, Valid.SetTgl(Tgl2.getSelectedItem() + ""));
                ps.setString(12, "%" + TCari.getText() + "%");
                ps.setString(13, Valid.SetTgl(Tgl1.getSelectedItem() + ""));
                ps.setString(14, Valid.SetTgl(Tgl2.getSelectedItem() + ""));
                ps.setString(15, "%" + TCari.getText() + "%");
                ps.setString(16, Valid.SetTgl(Tgl1.getSelectedItem() + ""));
                ps.setString(17, Valid.SetTgl(Tgl2.getSelectedItem() + ""));
                ps.setString(18, "%" + TCari.getText() + "%");
                ps.setString(19, Valid.SetTgl(Tgl1.getSelectedItem() + ""));
                ps.setString(20, Valid.SetTgl(Tgl2.getSelectedItem() + ""));
                ps.setString(21, "%" + TCari.getText() + "%");                                
                rs=ps.executeQuery();
                while(rs.next()){
                    deku=0;urine=0;sputum=0;darah=0;
                    if(rs.getString("DEKU").equals("IYA")){
                        deku=1;
                    }
                    if(!rs.getString("URINE").equals("")){
                        urine=1;
                    }
                    if(!rs.getString("SPUTUM").equals("")){
                        sputum=1;
                    }
                    if(!rs.getString("DARAH").equals("")){
                        darah=1;
                    }
                    htmlContent.append(
                        "<tr class='isi'>"+
                            "<td valign='middle' align='center'>"+i+"</td>"+
                            "<td valign='middle' align='left'>"+rs.getString("no_rkm_medis")+"</td>"+
                            "<td valign='middle' align='left'>"+rs.getString("nm_pasien")+"</td>"+
                            "<td valign='middle' align='center'>"+rs.getString("tanggal")+"</td>"+
                            "<td valign='middle' align='center'>"+rs.getString("ETT")+"</td>"+
                            "<td valign='middle' align='center'>"+rs.getString("CVL")+"</td>"+
                            "<td valign='middle' align='center'>"+rs.getString("IVL")+"</td>"+
                            "<td valign='middle' align='center'>"+rs.getString("UC")+"</td>"+
                            "<td valign='middle' align='center'>"+rs.getString("VAP")+"</td>"+
                            "<td valign='middle' align='center'>"+rs.getString("IAD")+"</td>"+
                            "<td valign='middle' align='center'>"+rs.getString("PLEB")+"</td>"+
                            "<td valign='middle' align='center'>"+rs.getString("ISK")+"</td>"+
                            "<td valign='middle' align='center'>"+rs.getString("IDO_ILO")+"</td>"+
                            "<td valign='middle' align='center'>"+deku+"</td>"+
                            "<td valign='middle' align='center'>"+sputum+"</td>"+
                            "<td valign='middle' align='center'>"+darah+"</td>"+
                            "<td valign='middle' align='center'>"+urine+"</td>"+
                            "<td valign='middle' align='left'>"+rs.getString("ANTIBIOTIK")+"</td>"+
                            "<td valign='middle' align='left'>"+rs.getString("nm_poli")+"</td>"+
                        "</tr>"
                    );
                    i++;
                }
            } catch (Exception e) {
                System.out.println("laporan.DlgHarianHAIsRalan.prosesCari() : "+e);
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
            System.out.println("laporan.DlgHarianHAIsRalan.prosesCari() 5 : "+e);
        } 
        this.setCursor(Cursor.getDefaultCursor());        
    }
    
    public void isCek(){
        BtnPrint.setEnabled(var.getharian_HAIs());
    }
    
}