/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

/*
 * DlgKamar.java
 *
 * Created on May 23, 2010, 12:07:21 AM
 */

package bridging;

import fungsi.WarnaTable;
import java.awt.Dimension;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableColumn;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import fungsi.batasInput;
import fungsi.koneksiDB;
import fungsi.sekuel;
import fungsi.validasi;
import fungsi.var;
import java.awt.Color;
import java.awt.Cursor;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.event.WindowEvent;
import java.awt.event.WindowListener;
import java.io.FileInputStream;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import java.util.Properties;
import javax.swing.JOptionPane;
import keuangan.DlgKamar;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.MediaType;
import org.springframework.web.client.RestTemplate;
import simrskhanza.DlgPasien;

/**
 *
 * @author dosen
 */
public final class BPJSCekKartu extends javax.swing.JDialog {
    private final DefaultTableModel tabMode;
    private final Properties prop = new Properties();
    private sekuel Sequel = new sekuel();
    private validasi Valid = new validasi();
    private Connection koneksi = koneksiDB.condb();
    private DlgPasien pasien = new DlgPasien(null, false);
    private BPJSCekNoKartu cekViaBPJSKartu = new BPJSCekNoKartu();
    private BPJSApi api = new BPJSApi();
    private String utc = "", URL = "", user = "";
    private PreparedStatement ps;
    private ResultSet rs;
    
    /** Creates new form DlgKamar
     * @param parent
     * @param modal */
    public BPJSCekKartu(java.awt.Frame parent, boolean modal) {
        super(parent, modal);
        initComponents();

        this.setLocation(10,2);
        setSize(628,674);

        Object[] row={"",""};
        tabMode=new DefaultTableModel(null,row){
              @Override public boolean isCellEditable(int rowIndex, int colIndex){return false;}
        };
        
        tbKamar.setModel(tabMode);
        tbKamar.setPreferredScrollableViewportSize(new Dimension(500,500));
        tbKamar.setAutoResizeMode(JTable.AUTO_RESIZE_OFF);

        for (int i = 0; i < 2; i++) {
            TableColumn column = tbKamar.getColumnModel().getColumn(i);
            if (i == 0) {
                column.setPreferredWidth(170);
            } else if (i == 1) {
                column.setPreferredWidth(470);
            }
        }        
        tbKamar.setDefaultRenderer(Object.class, new WarnaTable());
        
        
        pasien.addWindowListener(new WindowListener() {
            @Override
            public void windowOpened(WindowEvent e) {}
            @Override
            public void windowClosing(WindowEvent e) {}
            @Override
            public void windowClosed(WindowEvent e) {
                if (pasien.getTable().getSelectedRow() != -1) {
                    if (pasien.getTable().getValueAt(pasien.getTable().getSelectedRow(), 21).toString().equals("")) {
                        JOptionPane.showMessageDialog(rootPane, "Maaf pasien tidak punya Nomor Kartu...!");
                    } else {
                        NoKartu.setText(pasien.getTable().getValueAt(pasien.getTable().getSelectedRow(), 21).toString());
                        tampil(NoKartu.getText());
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
        
        pasien.getTable().addKeyListener(new KeyListener() {
            @Override
            public void keyTyped(KeyEvent e) {}
            @Override
            public void keyPressed(KeyEvent e) {
                if(e.getKeyCode()==KeyEvent.VK_SPACE){
                    pasien.dispose();
                }
            }
            @Override
            public void keyReleased(KeyEvent e) {}
        });
        
        NoKartu.setDocument(new batasInput((int)80).getKata(NoKartu));
        
        try {
            user=var.getkode().replace(" ","").substring(0,9);
        } catch (Exception e) {
            user=var.getkode();
        }
        
        try {
            prop.loadFromXML(new FileInputStream("setting/database.xml"));
        } catch (Exception e) {
            System.out.println("E : "+e);
        }
    }
    
    

    /** This method is called from within the constructor to
     * initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is
     * always regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        internalFrame1 = new widget.InternalFrame();
        Scroll = new widget.ScrollPane();
        tbKamar = new widget.Table();
        PanelInput = new javax.swing.JPanel();
        panelGlass6 = new widget.panelisi();
        jLabel16 = new widget.Label();
        NoKartu = new widget.TextBox();
        btnPasien = new widget.Button();
        BtnCari = new widget.Button();
        jLabel17 = new widget.Label();
        BtnKeluar = new widget.Button();

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);
        setIconImage(null);
        setIconImages(null);
        setUndecorated(true);
        setResizable(false);

        internalFrame1.setBorder(javax.swing.BorderFactory.createTitledBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(240, 245, 235)), "::[ Pencarian Peserta BPJS Berdasarkan Nomor Kepesertaan ]::", javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Tahoma", 1, 12), new java.awt.Color(0, 0, 0))); // NOI18N
        internalFrame1.setName("internalFrame1"); // NOI18N
        internalFrame1.setLayout(new java.awt.BorderLayout(1, 1));

        Scroll.setName("Scroll"); // NOI18N
        Scroll.setOpaque(true);

        tbKamar.setAutoCreateRowSorter(true);
        tbKamar.setToolTipText("");
        tbKamar.setName("tbKamar"); // NOI18N
        Scroll.setViewportView(tbKamar);

        internalFrame1.add(Scroll, java.awt.BorderLayout.CENTER);

        PanelInput.setName("PanelInput"); // NOI18N
        PanelInput.setOpaque(false);
        PanelInput.setPreferredSize(new java.awt.Dimension(200, 55));
        PanelInput.setLayout(new java.awt.BorderLayout(1, 1));

        panelGlass6.setName("panelGlass6"); // NOI18N
        panelGlass6.setPreferredSize(new java.awt.Dimension(44, 54));
        panelGlass6.setLayout(new java.awt.FlowLayout(java.awt.FlowLayout.LEFT, 5, 9));

        jLabel16.setForeground(new java.awt.Color(0, 0, 0));
        jLabel16.setText("No.Kartu :");
        jLabel16.setName("jLabel16"); // NOI18N
        jLabel16.setPreferredSize(new java.awt.Dimension(55, 23));
        panelGlass6.add(jLabel16);

        NoKartu.setForeground(new java.awt.Color(0, 0, 0));
        NoKartu.setName("NoKartu"); // NOI18N
        NoKartu.setPreferredSize(new java.awt.Dimension(250, 23));
        NoKartu.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                NoKartuKeyPressed(evt);
            }
        });
        panelGlass6.add(NoKartu);

        btnPasien.setForeground(new java.awt.Color(0, 0, 0));
        btnPasien.setIcon(new javax.swing.ImageIcon(getClass().getResource("/picture/190.png"))); // NOI18N
        btnPasien.setMnemonic('5');
        btnPasien.setToolTipText("Alt+5");
        btnPasien.setName("btnPasien"); // NOI18N
        btnPasien.setPreferredSize(new java.awt.Dimension(28, 23));
        btnPasien.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnPasienActionPerformed(evt);
            }
        });
        panelGlass6.add(btnPasien);

        BtnCari.setForeground(new java.awt.Color(0, 0, 0));
        BtnCari.setIcon(new javax.swing.ImageIcon(getClass().getResource("/picture/accept.png"))); // NOI18N
        BtnCari.setMnemonic('6');
        BtnCari.setText("Tampilkan Data");
        BtnCari.setToolTipText("Alt+6");
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
        panelGlass6.add(BtnCari);

        jLabel17.setForeground(new java.awt.Color(0, 0, 0));
        jLabel17.setName("jLabel17"); // NOI18N
        jLabel17.setPreferredSize(new java.awt.Dimension(30, 23));
        panelGlass6.add(jLabel17);

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

        PanelInput.add(panelGlass6, java.awt.BorderLayout.PAGE_END);

        internalFrame1.add(PanelInput, java.awt.BorderLayout.PAGE_END);

        getContentPane().add(internalFrame1, java.awt.BorderLayout.CENTER);

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void BtnKeluarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_BtnKeluarActionPerformed
        dispose();
    }//GEN-LAST:event_BtnKeluarActionPerformed

    private void BtnKeluarKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_BtnKeluarKeyPressed
        if(evt.getKeyCode()==KeyEvent.VK_SPACE){
            dispose();
        }
    }//GEN-LAST:event_BtnKeluarKeyPressed

    private void NoKartuKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_NoKartuKeyPressed
        if(evt.getKeyCode()==KeyEvent.VK_ENTER){
            BtnCariActionPerformed(null);
        }else if(evt.getKeyCode()==KeyEvent.VK_PAGE_UP){
            BtnKeluar.requestFocus();
        }
    }//GEN-LAST:event_NoKartuKeyPressed

    private void btnPasienActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnPasienActionPerformed
        pasien.emptTeks();
        pasien.isCek();
        pasien.TutupJendela();
        pasien.setSize(internalFrame1.getWidth()-40,internalFrame1.getHeight()-40);
        pasien.setLocationRelativeTo(internalFrame1);
        pasien.setVisible(true);
        pasien.fokus();
    }//GEN-LAST:event_btnPasienActionPerformed

    private void BtnCariActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_BtnCariActionPerformed
        this.setCursor(Cursor.getPredefinedCursor(Cursor.WAIT_CURSOR));    
        if (NoKartu.getText().trim().equals("")) {
            JOptionPane.showMessageDialog(null, "Silahkan isi dulu no. kartu BPJS nya dengan benar...!!!!");
        } else {
            tampil(NoKartu.getText());
        }
        this.setCursor(Cursor.getDefaultCursor());
    }//GEN-LAST:event_BtnCariActionPerformed

    private void BtnCariKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_BtnCariKeyPressed
        if(evt.getKeyCode()==KeyEvent.VK_SPACE){
            BtnCariActionPerformed(null);
        }
    }//GEN-LAST:event_BtnCariKeyPressed

    /**
    * @param args the command line arguments
    */
    public static void main(String args[]) {
        java.awt.EventQueue.invokeLater(() -> {
            BPJSCekKartu dialog = new BPJSCekKartu(new javax.swing.JFrame(), true);
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
    private widget.Button BtnCari;
    private widget.Button BtnKeluar;
    private widget.TextBox NoKartu;
    private javax.swing.JPanel PanelInput;
    private widget.ScrollPane Scroll;
    private widget.Button btnPasien;
    private widget.InternalFrame internalFrame1;
    private widget.Label jLabel16;
    private widget.Label jLabel17;
    private widget.panelisi panelGlass6;
    private widget.Table tbKamar;
    // End of variables declaration//GEN-END:variables

    public void tampil(String nomorpeserta) {
        try {
            cekViaBPJSKartu.tampil(nomorpeserta);
            if(cekViaBPJSKartu.informasi.equals("OK")){
                Valid.tabelKosong(tabMode);             
                tabMode.addRow(new Object[]{
                    "Nama",": "+cekViaBPJSKartu.nama
                });
                tabMode.addRow(new Object[]{
                    "NIK",": "+cekViaBPJSKartu.nik
                });
                tabMode.addRow(new Object[]{
                    "Nomor Kartu",": "+cekViaBPJSKartu.noKartu
                });
                tabMode.addRow(new Object[]{
                    "Nomor MR",": "+cekViaBPJSKartu.mrnoMR
                });
                tabMode.addRow(new Object[]{
                    "Nomor Telp",": "+cekViaBPJSKartu.mrnoTelepon
                });
                tabMode.addRow(new Object[]{
                    "Pisa",": "+cekViaBPJSKartu.pisa
                });
                tabMode.addRow(new Object[]{
                    "Jenis Kelamin",": "+cekViaBPJSKartu.sex.replaceAll("L","Laki-Laki").replaceAll("P","Perempuan")
                });
                tabMode.addRow(new Object[]{
                    "Status Peserta",":"
                });
                tabMode.addRow(new Object[]{
                    "       Keterangan",": "+cekViaBPJSKartu.statusPesertaketerangan
                });
                tabMode.addRow(new Object[]{
                    "       Kode",": "+cekViaBPJSKartu.statusPesertakode
                });
                tabMode.addRow(new Object[]{
                    "Jenis Peserta",":"
                });
                tabMode.addRow(new Object[]{
                    "       Kode Jenis Peserta",": "+cekViaBPJSKartu.jenisPesertakode
                });
                tabMode.addRow(new Object[]{
                    "       Nama Jenis Peserta",": "+cekViaBPJSKartu.jenisPesertaketerangan
                });            
                tabMode.addRow(new Object[]{
                    "Kelas Tanggungan",":"
                });
                tabMode.addRow(new Object[]{
                    "       Kode Kelas",": "+cekViaBPJSKartu.hakKelaskode
                });
                tabMode.addRow(new Object[]{
                    "       Nama Kelas",": "+cekViaBPJSKartu.hakKelasketerangan
                });
                tabMode.addRow(new Object[]{
                    "Provider Umum",":"
                });
                tabMode.addRow(new Object[]{
                    "       Kode Provider",": "+cekViaBPJSKartu.provUmumkdProvider
                });                
                tabMode.addRow(new Object[]{
                    "       Nama Provider",": "+cekViaBPJSKartu.provUmumnmProvider
                });
                tabMode.addRow(new Object[]{
                    "Tanggal Cetak Kartu",": "+cekViaBPJSKartu.tglCetakKartu
                });
                tabMode.addRow(new Object[]{
                    "Tanggal Lahir",": "+cekViaBPJSKartu.tglLahir
                });
                tabMode.addRow(new Object[]{
                    "Tanggal TAT",": "+cekViaBPJSKartu.tglTAT
                });
                tabMode.addRow(new Object[]{
                    "Tanggal TMT",": "+cekViaBPJSKartu.tglTMT
                });
                tabMode.addRow(new Object[]{
                    "Umur",":"
                });
                tabMode.addRow(new Object[]{
                    "       Umur Saat Pelayanan",": "+cekViaBPJSKartu.umurumurSaatPelayanan
                });
                tabMode.addRow(new Object[]{
                    "       Umur Sekarang",": "+cekViaBPJSKartu.umurumurSekarang.replaceAll("tahun ,","Th ").replaceAll("bulan ,","Bl ").replaceAll("hari","Hr")
                });
                tabMode.addRow(new Object[]{
                    "Informasi",":"
                });
                tabMode.addRow(new Object[]{
                    "       Dinsos",": "+cekViaBPJSKartu.informasidinsos
                });
                tabMode.addRow(new Object[]{
                    "       No.SKTM",": "+cekViaBPJSKartu.informasinoSKTM
                });
                tabMode.addRow(new Object[]{
                    "       Prolanis PRB",": "+cekViaBPJSKartu.informasiprolanisPRB
                });
                tabMode.addRow(new Object[]{
                    "COB",":"
                });
                tabMode.addRow(new Object[]{
                    "       Nama Asuransi",": "+cekViaBPJSKartu.cobnmAsuransi
                });
                tabMode.addRow(new Object[]{
                    "       No Asuransi",": "+cekViaBPJSKartu.cobnoAsuransi
                });
                tabMode.addRow(new Object[]{
                    "       Tanggal TAT",": "+cekViaBPJSKartu.cobtglTAT
                });
                tabMode.addRow(new Object[]{
                    "       Tanggal TMT",": "+cekViaBPJSKartu.cobtglTMT
                });
                
            }else {
                JOptionPane.showMessageDialog(null,cekViaBPJSKartu.informasi);                
            }   
        } catch (Exception ex) {
            System.out.println("Notifikasi Peserta : "+ex);
        }
    }  
    
    public void SetNoKartu(String NoPeserta){
        NoKartu.setText(NoPeserta);
        tampil(NoPeserta);
    }
}
