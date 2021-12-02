/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

/*
 * DlgLhtBiaya.java
 *
 * Created on 12 Jul 10, 16:21:34
 */

package bridging;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import fungsi.WarnaTable;
import fungsi.koneksiDB;
import fungsi.sekuel;
import fungsi.validasi;
import java.awt.Cursor;
import java.awt.Dimension;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.io.FileInputStream;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.Calendar;
import java.util.Date;
import java.util.Properties;
import javax.swing.JOptionPane;
import javax.swing.JTable;
import javax.swing.Timer;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableColumn;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.MediaType;

/**
 *
 * @author perpustakaan
 */
public final class BPJSCekTanggalRujukan extends javax.swing.JDialog {
    private final DefaultTableModel tabMode,tabMode2;
    private Connection koneksi=koneksiDB.condb();
    private sekuel Sequel=new sekuel();
    private validasi Valid=new validasi();
    private PreparedStatement ps;
    private ResultSet rs;
    private int i=0;
    private String URL="",norm="",statussep="",statuspasien="";
    private final Properties prop = new Properties();
    private BPJSApi api=new BPJSApi();
    /** Creates new form DlgLhtBiaya
     * @param parent
     * @param modal */
    public BPJSCekTanggalRujukan(java.awt.Frame parent, boolean modal) {
        super(parent, modal);
        initComponents();
        this.setLocation(8,1);
        setSize(885,674);
        tabMode=new DefaultTableModel(null,new String[]{
                "No.","Kode Diag","Nama Diagnosa","Keluhan","No.Rujuk/Kunjungan",
                "Kode Pelayanan","Pelayanan","Nama COB Asuransi","No.COB",
                "Tgl.TAT COB","Tgl.TMT COB","Kode Kelas","Hak Kelas","Dinsos","No.SKTM Dinsos",
                "Prolanis PRB","Kode Peserta","Jenis Peserta","No.M.R","No.Telp",
                "Nama Pasien","NIK","No.Kartu","Pisa","Kode Provider","Nama Provider",
                "Sex","Kode Status","Status Peserta","Cetak Kartu","Tgl.Lahir","Tgl.TAT",
                "Tgl.TMT","Umur Saat Pelayanan","Umur Sekarang","Kode Poli","Poli Rujukan",
                "Kode Perujuk","Perujuk","Kunjungan","Status Pasien","SEP Terbit"
            }){
              @Override public boolean isCellEditable(int rowIndex, int colIndex){return false;}
        };
        tbBangsal.setModel(tabMode);
        //tbBangsal.setDefaultRenderer(Object.class, new WarnaTable(jPanel2.getBackground(),tbBangsal.getBackground()));
        tbBangsal.setPreferredScrollableViewportSize(new Dimension(500,500));
        tbBangsal.setAutoResizeMode(JTable.AUTO_RESIZE_OFF);

        for (i = 0; i < 42; i++) {
            TableColumn column = tbBangsal.getColumnModel().getColumn(i);
            if(i==0){
                column.setPreferredWidth(35);
            }else if(i==1){
                column.setPreferredWidth(70);
            }else if(i==2){
                column.setPreferredWidth(150);
            }else if(i==3){
                column.setPreferredWidth(150);
            }else if(i==4){
                column.setPreferredWidth(110);
            }else if(i==5){
                column.setMinWidth(0);
                column.setMaxWidth(0);
            }else if(i==6){
                column.setPreferredWidth(110);
            }else if(i==7){
                column.setPreferredWidth(150);
            }else if(i==8){
                column.setPreferredWidth(80);
            }else if(i==9){
                column.setPreferredWidth(80);
            }else if(i==10){
                column.setPreferredWidth(80);
            }else if(i==11){
                column.setMinWidth(0);
                column.setMaxWidth(0);
            }else if(i==12){
                column.setPreferredWidth(140);
            }else if(i==13){
                column.setPreferredWidth(100);
            }else if(i==14){
                column.setPreferredWidth(100);
            }else if(i==15){
                column.setPreferredWidth(100);
            }else if(i==16){
                column.setMinWidth(0);
                column.setMaxWidth(0);
            }else if(i==17){
                column.setPreferredWidth(150);
            }else if(i==18){
                column.setPreferredWidth(80);
            }else if(i==19){
                column.setPreferredWidth(80);
            }else if(i==20){
                column.setPreferredWidth(150);
            }else if(i==21){
                column.setPreferredWidth(110);
            }else if(i==22){
                column.setPreferredWidth(100);
            }else if(i==23){
                column.setPreferredWidth(80);
            }else if(i==24){
                column.setMinWidth(0);
                column.setMaxWidth(0);
            }else if(i==25){
                column.setPreferredWidth(150);
            }else if(i==26){
                column.setPreferredWidth(30);
            }else if(i==27){
                column.setMinWidth(0);
                column.setMaxWidth(0);
            }else if(i==28){
                column.setPreferredWidth(140);
            }else if(i==29){
                column.setPreferredWidth(70);
            }else if(i==30){
                column.setPreferredWidth(70);
            }else if(i==31){
                column.setPreferredWidth(70);
            }else if(i==32){
                column.setPreferredWidth(70);
            }else if(i==33){
                column.setPreferredWidth(115);
            }else if(i==34){
                column.setPreferredWidth(115);
            }else if(i==35){
                column.setMinWidth(0);
                column.setMaxWidth(0);
            }else if(i==36){
                column.setPreferredWidth(120);
            }else if(i==37){
                column.setMinWidth(0);
                column.setMaxWidth(0);
            }else if(i==38){
                column.setPreferredWidth(150);
            }else if(i==39){
                column.setPreferredWidth(70);
            }else if(i==40){
                column.setPreferredWidth(90);
            }else if(i==41){
                column.setPreferredWidth(120);
            }
        }
        tbBangsal.setDefaultRenderer(Object.class, new WarnaTable());
        
        tabMode2=new DefaultTableModel(null,new String[]{
                "No.","Kode Diag","Nama Diagnosa","Keluhan","No.Rujuk/Kunjungan",
                "Kode Pelayanan","Pelayanan","Nama COB Asuransi","No.COB",
                "Tgl.TAT COB","Tgl.TMT COB","Kode Kelas","Hak Kelas","Dinsos","No.SKTM Dinsos",
                "Prolanis PRB","Kode Peserta","Jenis Peserta","No.M.R","No.Telp",
                "Nama Pasien","NIK","No.Kartu","Pisa","Kode Provider","Nama Provider",
                "Sex","Kode Status","Status Peserta","Cetak Kartu","Tgl.Lahir","Tgl.TAT",
                "Tgl.TMT","Umur Saat Pelayanan","Umur Sekarang","Kode Poli","Poli Rujukan",
                "Kode Perujuk","Perujuk","Kunjungan","Status Pasien","SEP Terbit"
            }){
              @Override public boolean isCellEditable(int rowIndex, int colIndex){return false;}
        };
        tbBangsal2.setModel(tabMode2);
        //tbBangsal2.setDefaultRenderer(Object.class, new WarnaTable(jPanel2.getBackground(),tbBangsal2.getBackground()));
        tbBangsal2.setPreferredScrollableViewportSize(new Dimension(500,500));
        tbBangsal2.setAutoResizeMode(JTable.AUTO_RESIZE_OFF);

        for (i = 0; i < 42; i++) {
            TableColumn column = tbBangsal2.getColumnModel().getColumn(i);
            if(i==0){
                column.setPreferredWidth(35);
            }else if(i==1){
                column.setPreferredWidth(70);
            }else if(i==2){
                column.setPreferredWidth(150);
            }else if(i==3){
                column.setPreferredWidth(150);
            }else if(i==4){
                column.setPreferredWidth(110);
            }else if(i==5){
                column.setMinWidth(0);
                column.setMaxWidth(0);
            }else if(i==6){
                column.setPreferredWidth(110);
            }else if(i==7){
                column.setPreferredWidth(150);
            }else if(i==8){
                column.setPreferredWidth(80);
            }else if(i==9){
                column.setPreferredWidth(80);
            }else if(i==10){
                column.setPreferredWidth(80);
            }else if(i==11){
                column.setMinWidth(0);
                column.setMaxWidth(0);
            }else if(i==12){
                column.setPreferredWidth(140);
            }else if(i==13){
                column.setPreferredWidth(100);
            }else if(i==14){
                column.setPreferredWidth(100);
            }else if(i==15){
                column.setPreferredWidth(100);
            }else if(i==16){
                column.setMinWidth(0);
                column.setMaxWidth(0);
            }else if(i==17){
                column.setPreferredWidth(150);
            }else if(i==18){
                column.setPreferredWidth(80);
            }else if(i==19){
                column.setPreferredWidth(80);
            }else if(i==20){
                column.setPreferredWidth(150);
            }else if(i==21){
                column.setPreferredWidth(110);
            }else if(i==22){
                column.setPreferredWidth(100);
            }else if(i==23){
                column.setPreferredWidth(80);
            }else if(i==24){
                column.setMinWidth(0);
                column.setMaxWidth(0);
            }else if(i==25){
                column.setPreferredWidth(150);
            }else if(i==26){
                column.setPreferredWidth(30);
            }else if(i==27){
                column.setMinWidth(0);
                column.setMaxWidth(0);
            }else if(i==28){
                column.setPreferredWidth(140);
            }else if(i==29){
                column.setPreferredWidth(70);
            }else if(i==30){
                column.setPreferredWidth(70);
            }else if(i==31){
                column.setPreferredWidth(70);
            }else if(i==32){
                column.setPreferredWidth(70);
            }else if(i==33){
                column.setPreferredWidth(115);
            }else if(i==34){
                column.setPreferredWidth(115);
            }else if(i==35){
                column.setMinWidth(0);
                column.setMaxWidth(0);
            }else if(i==36){
                column.setPreferredWidth(120);
            }else if(i==37){
                column.setMinWidth(0);
                column.setMaxWidth(0);
            }else if(i==38){
                column.setPreferredWidth(150);
            }else if(i==39){
                column.setPreferredWidth(70);
            }else if(i==40){
                column.setPreferredWidth(90);
            }else if(i==41){
                column.setPreferredWidth(120);
            }
        }
        tbBangsal2.setDefaultRenderer(Object.class, new WarnaTable());

        try {
            prop.loadFromXML(new FileInputStream("setting/database.xml"));            
        } catch (Exception e) {
            System.out.println("E : "+e);
        }
        
        jam(); 
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
        TabRawat = new javax.swing.JTabbedPane();
        Scroll = new widget.ScrollPane();
        tbBangsal = new widget.Table();
        Scroll2 = new widget.ScrollPane();
        tbBangsal2 = new widget.Table();
        panelGlass5 = new widget.panelisi();
        ChkJln = new widget.CekBox();
        Tanggal = new widget.Tanggal();
        jLabel12 = new widget.Label();
        cmbStatus = new widget.ComboBox();
        BtnCari = new widget.Button();
        jLabel7 = new widget.Label();
        BtnKeluar = new widget.Button();

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);
        setUndecorated(true);
        setResizable(false);

        internalFrame1.setBorder(javax.swing.BorderFactory.createTitledBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(240, 245, 235)), "::[ Rujukan Berdasar Tanggal Rujukan ]::", javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Tahoma", 1, 11), new java.awt.Color(0, 0, 0))); // NOI18N
        internalFrame1.setName("internalFrame1"); // NOI18N
        internalFrame1.setLayout(new java.awt.BorderLayout(1, 1));

        TabRawat.setBackground(new java.awt.Color(255, 255, 253));
        TabRawat.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(241, 246, 236)));
        TabRawat.setForeground(new java.awt.Color(0, 0, 0));
        TabRawat.setFont(new java.awt.Font("Tahoma", 0, 11)); // NOI18N
        TabRawat.setName("TabRawat"); // NOI18N
        TabRawat.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                TabRawatMouseClicked(evt);
            }
        });

        Scroll.setName("Scroll"); // NOI18N
        Scroll.setOpaque(true);

        tbBangsal.setForeground(new java.awt.Color(0, 0, 0));
        tbBangsal.setToolTipText("Silahkan klik untuk memilih data yang mau diedit ataupun dihapus");
        tbBangsal.setName("tbBangsal"); // NOI18N
        tbBangsal.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                tbBangsalMouseClicked(evt);
            }
        });
        tbBangsal.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                tbBangsalKeyPressed(evt);
            }
        });
        Scroll.setViewportView(tbBangsal);

        TabRawat.addTab("Rujukan PCare", Scroll);

        Scroll2.setName("Scroll2"); // NOI18N
        Scroll2.setOpaque(true);

        tbBangsal2.setForeground(new java.awt.Color(0, 0, 0));
        tbBangsal2.setToolTipText("Silahkan klik untuk memilih data yang mau diedit ataupun dihapus");
        tbBangsal2.setName("tbBangsal2"); // NOI18N
        tbBangsal2.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                tbBangsal2MouseClicked(evt);
            }
        });
        tbBangsal2.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                tbBangsal2KeyPressed(evt);
            }
        });
        Scroll2.setViewportView(tbBangsal2);

        TabRawat.addTab("Rujukan Rumah Sakit", Scroll2);

        internalFrame1.add(TabRawat, java.awt.BorderLayout.CENTER);

        panelGlass5.setForeground(new java.awt.Color(0, 0, 0));
        panelGlass5.setName("panelGlass5"); // NOI18N
        panelGlass5.setPreferredSize(new java.awt.Dimension(55, 55));
        panelGlass5.setLayout(new java.awt.FlowLayout(java.awt.FlowLayout.LEFT, 5, 9));

        ChkJln.setBorder(null);
        ChkJln.setForeground(new java.awt.Color(0, 0, 0));
        ChkJln.setText("Tanggal :");
        ChkJln.setBorderPainted(true);
        ChkJln.setBorderPaintedFlat(true);
        ChkJln.setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);
        ChkJln.setHorizontalTextPosition(javax.swing.SwingConstants.RIGHT);
        ChkJln.setName("ChkJln"); // NOI18N
        ChkJln.setPreferredSize(new java.awt.Dimension(75, 23));
        panelGlass5.add(ChkJln);

        Tanggal.setBackground(new java.awt.Color(245, 250, 240));
        Tanggal.setEditable(false);
        Tanggal.setForeground(new java.awt.Color(0, 0, 0));
        Tanggal.setDisplayFormat("dd-MM-yyyy");
        Tanggal.setName("Tanggal"); // NOI18N
        Tanggal.setPreferredSize(new java.awt.Dimension(90, 23));
        panelGlass5.add(Tanggal);

        jLabel12.setForeground(new java.awt.Color(0, 0, 0));
        jLabel12.setText("Status SEP :");
        jLabel12.setName("jLabel12"); // NOI18N
        jLabel12.setPreferredSize(new java.awt.Dimension(80, 23));
        panelGlass5.add(jLabel12);

        cmbStatus.setForeground(new java.awt.Color(0, 0, 0));
        cmbStatus.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "Semua", "Sudah Terbit", "Belum Terbit" }));
        cmbStatus.setName("cmbStatus"); // NOI18N
        cmbStatus.setOpaque(false);
        cmbStatus.setPreferredSize(new java.awt.Dimension(130, 23));
        cmbStatus.addItemListener(new java.awt.event.ItemListener() {
            public void itemStateChanged(java.awt.event.ItemEvent evt) {
                cmbStatusItemStateChanged(evt);
            }
        });
        cmbStatus.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                cmbStatusMouseClicked(evt);
            }
        });
        panelGlass5.add(cmbStatus);

        BtnCari.setForeground(new java.awt.Color(0, 0, 0));
        BtnCari.setIcon(new javax.swing.ImageIcon(getClass().getResource("/picture/accept.png"))); // NOI18N
        BtnCari.setMnemonic('2');
        BtnCari.setText("Tampilkan Data");
        BtnCari.setToolTipText("Alt+2");
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
        panelGlass5.add(BtnCari);

        jLabel7.setForeground(new java.awt.Color(0, 0, 0));
        jLabel7.setName("jLabel7"); // NOI18N
        jLabel7.setPreferredSize(new java.awt.Dimension(30, 23));
        panelGlass5.add(jLabel7);

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
        panelGlass5.add(BtnKeluar);

        internalFrame1.add(panelGlass5, java.awt.BorderLayout.PAGE_END);

        getContentPane().add(internalFrame1, java.awt.BorderLayout.CENTER);

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void BtnKeluarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_BtnKeluarActionPerformed
        dispose();
}//GEN-LAST:event_BtnKeluarActionPerformed

    private void BtnKeluarKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_BtnKeluarKeyPressed
        if(evt.getKeyCode()==KeyEvent.VK_SPACE){
            dispose();
        }else{Valid.pindah(evt,BtnKeluar,Tanggal);}
}//GEN-LAST:event_BtnKeluarKeyPressed

    private void tbBangsalMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tbBangsalMouseClicked
        if(tabMode.getRowCount()!=0){
            try {
                getData();
            } catch (java.lang.NullPointerException e) {
            }
        }
}//GEN-LAST:event_tbBangsalMouseClicked

    private void tbBangsalKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_tbBangsalKeyPressed
        if(tabMode.getRowCount()!=0){
            if((evt.getKeyCode()==KeyEvent.VK_ENTER)||(evt.getKeyCode()==KeyEvent.VK_UP)||(evt.getKeyCode()==KeyEvent.VK_DOWN)){
                try {
                    getData();
                } catch (java.lang.NullPointerException e) {
                }
            }
        }
}//GEN-LAST:event_tbBangsalKeyPressed

private void BtnCariActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_BtnCariActionPerformed
    this.setCursor(Cursor.getPredefinedCursor(Cursor.WAIT_CURSOR));            
    if(TabRawat.getSelectedIndex()==0){
        tampil();
    }else if(TabRawat.getSelectedIndex()==1){
        tampil2();
    }
    this.setCursor(Cursor.getDefaultCursor());
}//GEN-LAST:event_BtnCariActionPerformed

private void BtnCariKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_BtnCariKeyPressed
        if(evt.getKeyCode()==KeyEvent.VK_SPACE){
            this.setCursor(Cursor.getPredefinedCursor(Cursor.WAIT_CURSOR)); 
            tampil();
            this.setCursor(Cursor.getDefaultCursor());
        }else{
            //Valid.pindah(evt, TKd, BtnPrint);
        }
}//GEN-LAST:event_BtnCariKeyPressed

    private void TabRawatMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_TabRawatMouseClicked
        if(TabRawat.getSelectedIndex()==0){
            tampil();
        }else if(TabRawat.getSelectedIndex()==1){
            tampil2();
        }
    }//GEN-LAST:event_TabRawatMouseClicked

    private void tbBangsal2MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tbBangsal2MouseClicked
        // TODO add your handling code here:
    }//GEN-LAST:event_tbBangsal2MouseClicked

    private void tbBangsal2KeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_tbBangsal2KeyPressed
        // TODO add your handling code here:
    }//GEN-LAST:event_tbBangsal2KeyPressed

    private void cmbStatusItemStateChanged(java.awt.event.ItemEvent evt) {//GEN-FIRST:event_cmbStatusItemStateChanged
        TabRawatMouseClicked(null);
    }//GEN-LAST:event_cmbStatusItemStateChanged

    private void cmbStatusMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_cmbStatusMouseClicked
        cmbStatus.setEditable(false);
    }//GEN-LAST:event_cmbStatusMouseClicked

    /**
    * @param args the command line arguments
    */
    public static void main(String args[]) {
        java.awt.EventQueue.invokeLater(() -> {
            BPJSCekTanggalRujukan dialog = new BPJSCekTanggalRujukan(new javax.swing.JFrame(), true);
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
    private widget.CekBox ChkJln;
    private widget.ScrollPane Scroll;
    private widget.ScrollPane Scroll2;
    private javax.swing.JTabbedPane TabRawat;
    private widget.Tanggal Tanggal;
    private widget.ComboBox cmbStatus;
    private widget.InternalFrame internalFrame1;
    private widget.Label jLabel12;
    private widget.Label jLabel7;
    private widget.panelisi panelGlass5;
    private widget.Table tbBangsal;
    private widget.Table tbBangsal2;
    // End of variables declaration//GEN-END:variables

    public void tampil(){        
        try {
            URL = prop.getProperty("URLAPIBPJS")+"/Rujukan/List/TglRujukan/"+Valid.SetTgl(Tanggal.getSelectedItem()+"");	

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
                Valid.tabelKosong(tabMode);
                JsonNode response = root.path("response").path("rujukan");
                if(response.isArray()){
                    i=1;
                    for(JsonNode list:response){
                        statussep=Sequel.cariIsi("select no_sep from bridging_sep where no_kartu=?",list.path("peserta").path("noKartu").asText());
                        if(cmbStatus.getSelectedItem().toString().equals("Semua")){
                            norm=Sequel.cariIsi("select no_rkm_medis from pasien where no_peserta =?",list.path("peserta").path("noKartu").asText());
                            statuspasien="Baru";
                            if(!norm.equals("")){
                                statuspasien="Lama";
                            }
                            tabMode.addRow(new Object[]{
                                i+".",list.path("diagnosa").path("kode").asText(),list.path("diagnosa").path("nama").asText(),
                                list.path("keluhan").asText(),list.path("noKunjungan").asText(),
                                list.path("pelayanan").path("kode").asText(),list.path("pelayanan").path("nama").asText(),
                                list.path("peserta").path("cob").path("nmAsuransi").asText(),list.path("peserta").path("cob").path("noAsuransi").asText(),
                                list.path("peserta").path("cob").path("tglTAT").asText(),list.path("peserta").path("cob").path("tglTMT").asText(),
                                list.path("peserta").path("hakKelas").path("kode").asText(),list.path("peserta").path("hakKelas").path("keterangan").asText(),
                                list.path("peserta").path("informasi").path("dinsos").asText(),list.path("peserta").path("informasi").path("noSKTM").asText(),
                                list.path("peserta").path("informasi").path("prolanisPRB").asText(),list.path("peserta").path("jenisPeserta").path("kode").asText(),
                                list.path("peserta").path("jenisPeserta").path("keterangan").asText(),norm,
                                list.path("peserta").path("mr").path("noTelepon").asText(),list.path("peserta").path("nama").asText(),
                                list.path("peserta").path("nik").asText(),list.path("peserta").path("noKartu").asText(),
                                list.path("peserta").path("pisa").asText(),list.path("peserta").path("provUmum").path("kdProvider").asText(),
                                list.path("peserta").path("provUmum").path("nmProvider").asText(),
                                list.path("peserta").path("sex").asText().replaceAll("L","Laki-Laki").replaceAll("P","Perempuan"),
                                list.path("peserta").path("statusPeserta").path("kode").asText(),list.path("peserta").path("statusPeserta").path("keterangan").asText(),
                                list.path("peserta").path("tglCetakKartu").asText(),list.path("peserta").path("tglLahir").asText(),
                                list.path("peserta").path("tglTAT").asText(),list.path("peserta").path("tglTMT").asText(),
                                list.path("peserta").path("umur").path("umurSaatPelayanan").asText(),list.path("peserta").path("umur").path("umurSekarang").asText(),
                                list.path("poliRujukan").path("kode").asText(),list.path("poliRujukan").path("nama").asText(),
                                list.path("provPerujuk").path("kode").asText(),list.path("provPerujuk").path("nama").asText(),
                                list.path("tglKunjungan").asText(),statuspasien,statussep
                            });
                            i++;
                        }else if(cmbStatus.getSelectedItem().toString().equals("Sudah Terbit")){
                            if(!statussep.equals("")){
                                norm=Sequel.cariIsi("select no_rkm_medis from pasien where no_peserta =?",list.path("peserta").path("noKartu").asText());
                                statuspasien="Baru";
                                if(!norm.equals("")){
                                    statuspasien="Lama";
                                }
                                tabMode.addRow(new Object[]{
                                    i+".",list.path("diagnosa").path("kode").asText(),list.path("diagnosa").path("nama").asText(),
                                    list.path("keluhan").asText(),list.path("noKunjungan").asText(),
                                    list.path("pelayanan").path("kode").asText(),list.path("pelayanan").path("nama").asText(),
                                    list.path("peserta").path("cob").path("nmAsuransi").asText(),list.path("peserta").path("cob").path("noAsuransi").asText(),
                                    list.path("peserta").path("cob").path("tglTAT").asText(),list.path("peserta").path("cob").path("tglTMT").asText(),
                                    list.path("peserta").path("hakKelas").path("kode").asText(),list.path("peserta").path("hakKelas").path("keterangan").asText(),
                                    list.path("peserta").path("informasi").path("dinsos").asText(),list.path("peserta").path("informasi").path("noSKTM").asText(),
                                    list.path("peserta").path("informasi").path("prolanisPRB").asText(),list.path("peserta").path("jenisPeserta").path("kode").asText(),
                                    list.path("peserta").path("jenisPeserta").path("keterangan").asText(),norm,
                                    list.path("peserta").path("mr").path("noTelepon").asText(),list.path("peserta").path("nama").asText(),
                                    list.path("peserta").path("nik").asText(),list.path("peserta").path("noKartu").asText(),
                                    list.path("peserta").path("pisa").asText(),list.path("peserta").path("provUmum").path("kdProvider").asText(),
                                    list.path("peserta").path("provUmum").path("nmProvider").asText(),
                                    list.path("peserta").path("sex").asText().replaceAll("L","Laki-Laki").replaceAll("P","Perempuan"),
                                    list.path("peserta").path("statusPeserta").path("kode").asText(),list.path("peserta").path("statusPeserta").path("keterangan").asText(),
                                    list.path("peserta").path("tglCetakKartu").asText(),list.path("peserta").path("tglLahir").asText(),
                                    list.path("peserta").path("tglTAT").asText(),list.path("peserta").path("tglTMT").asText(),
                                    list.path("peserta").path("umur").path("umurSaatPelayanan").asText(),list.path("peserta").path("umur").path("umurSekarang").asText(),
                                    list.path("poliRujukan").path("kode").asText(),list.path("poliRujukan").path("nama").asText(),
                                    list.path("provPerujuk").path("kode").asText(),list.path("provPerujuk").path("nama").asText(),
                                    list.path("tglKunjungan").asText(),statuspasien,statussep
                                });
                                i++;
                            }
                        }else if(cmbStatus.getSelectedItem().toString().equals("Belum Terbit")){
                            if(statussep.equals("")){
                                norm=Sequel.cariIsi("select no_rkm_medis from pasien where no_peserta =?",list.path("peserta").path("noKartu").asText());
                                statuspasien="Baru";
                                if(!norm.equals("")){
                                    statuspasien="Lama";
                                }
                                tabMode.addRow(new Object[]{
                                    i+".",list.path("diagnosa").path("kode").asText(),list.path("diagnosa").path("nama").asText(),
                                    list.path("keluhan").asText(),list.path("noKunjungan").asText(),
                                    list.path("pelayanan").path("kode").asText(),list.path("pelayanan").path("nama").asText(),
                                    list.path("peserta").path("cob").path("nmAsuransi").asText(),list.path("peserta").path("cob").path("noAsuransi").asText(),
                                    list.path("peserta").path("cob").path("tglTAT").asText(),list.path("peserta").path("cob").path("tglTMT").asText(),
                                    list.path("peserta").path("hakKelas").path("kode").asText(),list.path("peserta").path("hakKelas").path("keterangan").asText(),
                                    list.path("peserta").path("informasi").path("dinsos").asText(),list.path("peserta").path("informasi").path("noSKTM").asText(),
                                    list.path("peserta").path("informasi").path("prolanisPRB").asText(),list.path("peserta").path("jenisPeserta").path("kode").asText(),
                                    list.path("peserta").path("jenisPeserta").path("keterangan").asText(),norm,
                                    list.path("peserta").path("mr").path("noTelepon").asText(),list.path("peserta").path("nama").asText(),
                                    list.path("peserta").path("nik").asText(),list.path("peserta").path("noKartu").asText(),
                                    list.path("peserta").path("pisa").asText(),list.path("peserta").path("provUmum").path("kdProvider").asText(),
                                    list.path("peserta").path("provUmum").path("nmProvider").asText(),
                                    list.path("peserta").path("sex").asText().replaceAll("L","Laki-Laki").replaceAll("P","Perempuan"),
                                    list.path("peserta").path("statusPeserta").path("kode").asText(),list.path("peserta").path("statusPeserta").path("keterangan").asText(),
                                    list.path("peserta").path("tglCetakKartu").asText(),list.path("peserta").path("tglLahir").asText(),
                                    list.path("peserta").path("tglTAT").asText(),list.path("peserta").path("tglTMT").asText(),
                                    list.path("peserta").path("umur").path("umurSaatPelayanan").asText(),list.path("peserta").path("umur").path("umurSekarang").asText(),
                                    list.path("poliRujukan").path("kode").asText(),list.path("poliRujukan").path("nama").asText(),
                                    list.path("provPerujuk").path("kode").asText(),list.path("provPerujuk").path("nama").asText(),
                                    list.path("tglKunjungan").asText(),statuspasien,statussep
                                });
                                i++;
                            }
                        }
                    }
                }
                                       
            }else {
                JOptionPane.showMessageDialog(null,nameNode.path("message").asText());                
            }   
        } catch (Exception ex) {
            System.out.println("Notifikasi Peserta : "+ex);
            if(ex.toString().contains("UnknownHostException")){
                JOptionPane.showMessageDialog(rootPane,"Koneksi ke server BPJS terputus...!");
            }
        }
    }

    public void tampil2(){        
        try {
            URL = prop.getProperty("URLAPIBPJS")+"/Rujukan/RS/List/TglRujukan/"+Valid.SetTgl(Tanggal.getSelectedItem()+"");	

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
                Valid.tabelKosong(tabMode);
                JsonNode response = root.path("response").path("rujukan");
                if(response.isArray()){
                    i=1;
                    for(JsonNode list:response){
                        statussep=Sequel.cariIsi("select no_sep from bridging_sep where no_kartu=?",list.path("peserta").path("noKartu").asText());
                        if(cmbStatus.getSelectedItem().toString().equals("Semua")){
                            norm=Sequel.cariIsi("select no_rkm_medis from pasien where no_peserta =?",list.path("peserta").path("noKartu").asText());
                            statuspasien="Baru";
                            if(!norm.equals("")){
                                statuspasien="Lama";
                            }
                            tabMode.addRow(new Object[]{
                                i+".",list.path("diagnosa").path("kode").asText(),list.path("diagnosa").path("nama").asText(),
                                list.path("keluhan").asText(),list.path("noKunjungan").asText(),
                                list.path("pelayanan").path("kode").asText(),list.path("pelayanan").path("nama").asText(),
                                list.path("peserta").path("cob").path("nmAsuransi").asText(),list.path("peserta").path("cob").path("noAsuransi").asText(),
                                list.path("peserta").path("cob").path("tglTAT").asText(),list.path("peserta").path("cob").path("tglTMT").asText(),
                                list.path("peserta").path("hakKelas").path("kode").asText(),list.path("peserta").path("hakKelas").path("keterangan").asText(),
                                list.path("peserta").path("informasi").path("dinsos").asText(),list.path("peserta").path("informasi").path("noSKTM").asText(),
                                list.path("peserta").path("informasi").path("prolanisPRB").asText(),list.path("peserta").path("jenisPeserta").path("kode").asText(),
                                list.path("peserta").path("jenisPeserta").path("keterangan").asText(),norm,
                                list.path("peserta").path("mr").path("noTelepon").asText(),list.path("peserta").path("nama").asText(),
                                list.path("peserta").path("nik").asText(),list.path("peserta").path("noKartu").asText(),
                                list.path("peserta").path("pisa").asText(),list.path("peserta").path("provUmum").path("kdProvider").asText(),
                                list.path("peserta").path("provUmum").path("nmProvider").asText(),
                                list.path("peserta").path("sex").asText().replaceAll("L","Laki-Laki").replaceAll("P","Perempuan"),
                                list.path("peserta").path("statusPeserta").path("kode").asText(),list.path("peserta").path("statusPeserta").path("keterangan").asText(),
                                list.path("peserta").path("tglCetakKartu").asText(),list.path("peserta").path("tglLahir").asText(),
                                list.path("peserta").path("tglTAT").asText(),list.path("peserta").path("tglTMT").asText(),
                                list.path("peserta").path("umur").path("umurSaatPelayanan").asText(),list.path("peserta").path("umur").path("umurSekarang").asText(),
                                list.path("poliRujukan").path("kode").asText(),list.path("poliRujukan").path("nama").asText(),
                                list.path("provPerujuk").path("kode").asText(),list.path("provPerujuk").path("nama").asText(),
                                list.path("tglKunjungan").asText(),statuspasien,statussep
                            });
                            i++;
                        }else if(cmbStatus.getSelectedItem().toString().equals("Sudah Terbit")){
                            if(!statussep.equals("")){
                                norm=Sequel.cariIsi("select no_rkm_medis from pasien where no_peserta =?",list.path("peserta").path("noKartu").asText());
                                statuspasien="Baru";
                                if(!norm.equals("")){
                                    statuspasien="Lama";
                                }
                                tabMode.addRow(new Object[]{
                                    i+".",list.path("diagnosa").path("kode").asText(),list.path("diagnosa").path("nama").asText(),
                                    list.path("keluhan").asText(),list.path("noKunjungan").asText(),
                                    list.path("pelayanan").path("kode").asText(),list.path("pelayanan").path("nama").asText(),
                                    list.path("peserta").path("cob").path("nmAsuransi").asText(),list.path("peserta").path("cob").path("noAsuransi").asText(),
                                    list.path("peserta").path("cob").path("tglTAT").asText(),list.path("peserta").path("cob").path("tglTMT").asText(),
                                    list.path("peserta").path("hakKelas").path("kode").asText(),list.path("peserta").path("hakKelas").path("keterangan").asText(),
                                    list.path("peserta").path("informasi").path("dinsos").asText(),list.path("peserta").path("informasi").path("noSKTM").asText(),
                                    list.path("peserta").path("informasi").path("prolanisPRB").asText(),list.path("peserta").path("jenisPeserta").path("kode").asText(),
                                    list.path("peserta").path("jenisPeserta").path("keterangan").asText(),norm,
                                    list.path("peserta").path("mr").path("noTelepon").asText(),list.path("peserta").path("nama").asText(),
                                    list.path("peserta").path("nik").asText(),list.path("peserta").path("noKartu").asText(),
                                    list.path("peserta").path("pisa").asText(),list.path("peserta").path("provUmum").path("kdProvider").asText(),
                                    list.path("peserta").path("provUmum").path("nmProvider").asText(),
                                    list.path("peserta").path("sex").asText().replaceAll("L","Laki-Laki").replaceAll("P","Perempuan"),
                                    list.path("peserta").path("statusPeserta").path("kode").asText(),list.path("peserta").path("statusPeserta").path("keterangan").asText(),
                                    list.path("peserta").path("tglCetakKartu").asText(),list.path("peserta").path("tglLahir").asText(),
                                    list.path("peserta").path("tglTAT").asText(),list.path("peserta").path("tglTMT").asText(),
                                    list.path("peserta").path("umur").path("umurSaatPelayanan").asText(),list.path("peserta").path("umur").path("umurSekarang").asText(),
                                    list.path("poliRujukan").path("kode").asText(),list.path("poliRujukan").path("nama").asText(),
                                    list.path("provPerujuk").path("kode").asText(),list.path("provPerujuk").path("nama").asText(),
                                    list.path("tglKunjungan").asText(),statuspasien,statussep
                                });
                                i++;
                            }
                        }else if(cmbStatus.getSelectedItem().toString().equals("Belum Terbit")){
                            if(statussep.equals("")){
                                norm=Sequel.cariIsi("select no_rkm_medis from pasien where no_peserta =?",list.path("peserta").path("noKartu").asText());
                                statuspasien="Baru";
                                if(!norm.equals("")){
                                    statuspasien="Lama";
                                }
                                tabMode.addRow(new Object[]{
                                    i+".",list.path("diagnosa").path("kode").asText(),list.path("diagnosa").path("nama").asText(),
                                    list.path("keluhan").asText(),list.path("noKunjungan").asText(),
                                    list.path("pelayanan").path("kode").asText(),list.path("pelayanan").path("nama").asText(),
                                    list.path("peserta").path("cob").path("nmAsuransi").asText(),list.path("peserta").path("cob").path("noAsuransi").asText(),
                                    list.path("peserta").path("cob").path("tglTAT").asText(),list.path("peserta").path("cob").path("tglTMT").asText(),
                                    list.path("peserta").path("hakKelas").path("kode").asText(),list.path("peserta").path("hakKelas").path("keterangan").asText(),
                                    list.path("peserta").path("informasi").path("dinsos").asText(),list.path("peserta").path("informasi").path("noSKTM").asText(),
                                    list.path("peserta").path("informasi").path("prolanisPRB").asText(),list.path("peserta").path("jenisPeserta").path("kode").asText(),
                                    list.path("peserta").path("jenisPeserta").path("keterangan").asText(),norm,
                                    list.path("peserta").path("mr").path("noTelepon").asText(),list.path("peserta").path("nama").asText(),
                                    list.path("peserta").path("nik").asText(),list.path("peserta").path("noKartu").asText(),
                                    list.path("peserta").path("pisa").asText(),list.path("peserta").path("provUmum").path("kdProvider").asText(),
                                    list.path("peserta").path("provUmum").path("nmProvider").asText(),
                                    list.path("peserta").path("sex").asText().replaceAll("L","Laki-Laki").replaceAll("P","Perempuan"),
                                    list.path("peserta").path("statusPeserta").path("kode").asText(),list.path("peserta").path("statusPeserta").path("keterangan").asText(),
                                    list.path("peserta").path("tglCetakKartu").asText(),list.path("peserta").path("tglLahir").asText(),
                                    list.path("peserta").path("tglTAT").asText(),list.path("peserta").path("tglTMT").asText(),
                                    list.path("peserta").path("umur").path("umurSaatPelayanan").asText(),list.path("peserta").path("umur").path("umurSekarang").asText(),
                                    list.path("poliRujukan").path("kode").asText(),list.path("poliRujukan").path("nama").asText(),
                                    list.path("provPerujuk").path("kode").asText(),list.path("provPerujuk").path("nama").asText(),
                                    list.path("tglKunjungan").asText(),statuspasien,statussep
                                });
                                i++;
                            }
                        }
                    }
                }
                                       
            }else {
                JOptionPane.showMessageDialog(null,nameNode.path("message").asText());                
            }   
        } catch (Exception ex) {
            System.out.println("Notifikasi Peserta : "+ex);
            if(ex.toString().contains("UnknownHostException")){
                JOptionPane.showMessageDialog(rootPane,"Koneksi ke server BPJS terputus...!");
            }
        }
    }
    
    private void getData() {
        int row=tbBangsal.getSelectedRow();
        if(row!= -1){
            
        }
    }
    
    private void jam(){
        ActionListener taskPerformer = new ActionListener(){
            private int nilai_jam;
            private int nilai_menit;
            private int nilai_detik;
            public void actionPerformed(ActionEvent e) {
                String nol_jam = "";
                String nol_menit = "";
                String nol_detik = "";
                
                Date now = Calendar.getInstance().getTime();
                
                nilai_jam = now.getHours();
                nilai_menit = now.getMinutes();
                nilai_detik = now.getSeconds();
                

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
                if(ChkJln.isSelected()==true){
                    if(detik.equals("20")){
                        TabRawatMouseClicked(null);
                    }
                }
            }
        };
        // Timer
        new Timer(1000, taskPerformer).start();
    }

    public void fokus() {
        cmbStatus.requestFocus();
    }
}