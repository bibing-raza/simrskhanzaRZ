    /*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

/*
 * Dlgdiet.java
 *
 * Created on May 23, 2010, 1:25:13 AM
 */

package simrskhanza;

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
import javax.swing.JButton;
import javax.swing.JOptionPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.event.DocumentEvent;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableColumn;

/**
 *
 * @author dosen
 */
public class DlgDiet extends javax.swing.JDialog {
    private final DefaultTableModel tabMode;
    private Connection koneksi=koneksiDB.condb();
    private sekuel Sequel=new sekuel();
    private validasi Valid=new validasi();
    private PreparedStatement ps;
    private ResultSet rs;
    private String kdLama = "";

    /** Creates new form Dlgdiet
     * @param parent
     * @param modal */
    public DlgDiet(java.awt.Frame parent, boolean modal) {
        super(parent, modal);
        initComponents();

        this.setLocation(10,10);
        setSize(459,539);

        Object[] row={"Kode Diet", "Nama Diet","Jns. Makanan"};
        tabMode=new DefaultTableModel(null,row){
              @Override public boolean isCellEditable(int rowIndex, int colIndex){return false;}
        };

        tbDiet.setModel(tabMode);
        tbDiet.setPreferredScrollableViewportSize(new Dimension(500,500));
        tbDiet.setAutoResizeMode(JTable.AUTO_RESIZE_OFF);

        for (int i = 0; i < 3; i++) {
            TableColumn column = tbDiet.getColumnModel().getColumn(i);
            if (i == 0) {
                column.setPreferredWidth(110);
            } else if (i == 1) {
                column.setPreferredWidth(200);
            } else if (i == 2) {
                column.setPreferredWidth(130);
            }
        }
        tbDiet.setDefaultRenderer(Object.class, new WarnaTable());

        TKd.setDocument(new batasInput((byte)6).getKata(TKd));
        TNm.setDocument(new batasInput((byte)50).getKata(TNm));
        TCari.setDocument(new batasInput((byte)100).getKata(TCari));
        
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

        try {
            ps = koneksi.prepareStatement("select kd_diet, nama_diet, jns_makanan from diet_master where "
                    + "flag='1' and kd_diet like ? or "
                    + "flag='1' and nama_diet like ? or "
                    + "flag='1' and jns_makanan like ? order by kd_diet");
        } catch (SQLException e) {
            System.out.println(e);
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
        tbDiet = new widget.Table();
        jPanel3 = new javax.swing.JPanel();
        panelGlass8 = new widget.panelisi();
        BtnSimpan = new widget.Button();
        BtnBatal = new widget.Button();
        BtnHapus = new widget.Button();
        BtnEdit = new widget.Button();
        BtnKeluar = new widget.Button();
        panelGlass9 = new widget.panelisi();
        jLabel6 = new widget.Label();
        TCari = new widget.TextBox();
        BtnCari = new widget.Button();
        BtnAll = new widget.Button();
        jLabel7 = new widget.Label();
        LCount = new widget.Label();
        panelGlass7 = new widget.panelisi();
        jLabel3 = new widget.Label();
        TKd = new widget.TextBox();
        jLabel4 = new widget.Label();
        TNm = new widget.TextBox();
        jLabel5 = new widget.Label();
        cmbJenis = new widget.ComboBox();

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);
        setUndecorated(true);
        setResizable(false);
        addWindowListener(new java.awt.event.WindowAdapter() {
            public void windowOpened(java.awt.event.WindowEvent evt) {
                formWindowOpened(evt);
            }
        });

        internalFrame1.setBorder(javax.swing.BorderFactory.createTitledBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 153, 255), 3), "::[ Data Diet ]::", javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Tahoma", 1, 12), new java.awt.Color(0, 0, 0))); // NOI18N
        internalFrame1.setName("internalFrame1"); // NOI18N
        internalFrame1.setLayout(new java.awt.BorderLayout(1, 1));

        Scroll.setName("Scroll"); // NOI18N
        Scroll.setOpaque(true);

        tbDiet.setAutoCreateRowSorter(true);
        tbDiet.setToolTipText("Silahkan klik untuk memilih data yang mau diedit ataupun dihapus");
        tbDiet.setName("tbDiet"); // NOI18N
        tbDiet.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                tbDietMouseClicked(evt);
            }
        });
        tbDiet.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                tbDietKeyPressed(evt);
            }
        });
        Scroll.setViewportView(tbDiet);

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

        jLabel6.setForeground(new java.awt.Color(0, 0, 0));
        jLabel6.setText("Key Word :");
        jLabel6.setName("jLabel6"); // NOI18N
        jLabel6.setPreferredSize(new java.awt.Dimension(70, 23));
        panelGlass9.add(jLabel6);

        TCari.setForeground(new java.awt.Color(0, 0, 0));
        TCari.setName("TCari"); // NOI18N
        TCari.setPreferredSize(new java.awt.Dimension(250, 23));
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
        BtnAll.setPreferredSize(new java.awt.Dimension(110, 23));
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

        jPanel3.add(panelGlass9, java.awt.BorderLayout.PAGE_START);

        internalFrame1.add(jPanel3, java.awt.BorderLayout.PAGE_END);

        panelGlass7.setName("panelGlass7"); // NOI18N
        panelGlass7.setPreferredSize(new java.awt.Dimension(44, 44));
        panelGlass7.setLayout(new java.awt.FlowLayout(java.awt.FlowLayout.LEFT));

        jLabel3.setForeground(new java.awt.Color(0, 0, 0));
        jLabel3.setText("Kode Diet : ");
        jLabel3.setName("jLabel3"); // NOI18N
        jLabel3.setPreferredSize(new java.awt.Dimension(80, 23));
        panelGlass7.add(jLabel3);

        TKd.setEditable(false);
        TKd.setForeground(new java.awt.Color(0, 0, 0));
        TKd.setName("TKd"); // NOI18N
        TKd.setPreferredSize(new java.awt.Dimension(90, 23));
        TKd.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                TKdKeyPressed(evt);
            }
        });
        panelGlass7.add(TKd);

        jLabel4.setForeground(new java.awt.Color(0, 0, 0));
        jLabel4.setText("Nama Diet : ");
        jLabel4.setName("jLabel4"); // NOI18N
        jLabel4.setPreferredSize(new java.awt.Dimension(70, 23));
        panelGlass7.add(jLabel4);

        TNm.setForeground(new java.awt.Color(0, 0, 0));
        TNm.setName("TNm"); // NOI18N
        TNm.setPreferredSize(new java.awt.Dimension(250, 23));
        TNm.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                TNmKeyPressed(evt);
            }
        });
        panelGlass7.add(TNm);

        jLabel5.setForeground(new java.awt.Color(0, 0, 0));
        jLabel5.setText("Jns. Makanan :");
        jLabel5.setName("jLabel5"); // NOI18N
        jLabel5.setPreferredSize(new java.awt.Dimension(90, 23));
        panelGlass7.add(jLabel5);

        cmbJenis.setForeground(new java.awt.Color(0, 0, 0));
        cmbJenis.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "-" }));
        cmbJenis.setName("cmbJenis"); // NOI18N
        cmbJenis.setPreferredSize(new java.awt.Dimension(170, 23));
        cmbJenis.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                cmbJenisKeyPressed(evt);
            }
        });
        panelGlass7.add(cmbJenis);

        internalFrame1.add(panelGlass7, java.awt.BorderLayout.PAGE_START);

        getContentPane().add(internalFrame1, java.awt.BorderLayout.CENTER);

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void TKdKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_TKdKeyPressed
        Valid.pindah(evt,TCari,TNm);
}//GEN-LAST:event_TKdKeyPressed

    private void TNmKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_TNmKeyPressed
        Valid.pindah(evt,TKd,BtnSimpan);
}//GEN-LAST:event_TNmKeyPressed

    private void BtnSimpanActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_BtnSimpanActionPerformed
        if (TKd.getText().trim().equals("")) {
            Valid.textKosong(TKd, "kode diet");
        } else if (TNm.getText().trim().equals("")) {
            Valid.textKosong(TNm, "nama diet");
        } else if (cmbJenis.getSelectedItem().equals("-")) {
            JOptionPane.showMessageDialog(null, "Pilih salah satu jenis makanannya dengan benar,...!!");
            cmbJenis.requestFocus();
        } else {            
            Sequel.simpanReplaceInto("diet_master", "'" + TKd.getText() + "','" + TNm.getText() + "','" + cmbJenis.getSelectedItem() + "','1'", "Diet Master");            
            tampil();
            emptTeks();
            BtnKeluarActionPerformed(null);
        }
}//GEN-LAST:event_BtnSimpanActionPerformed

    private void BtnSimpanKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_BtnSimpanKeyPressed
        if (evt.getKeyCode() == KeyEvent.VK_SPACE) {
            BtnSimpanActionPerformed(null);
        } else {
            Valid.pindah(evt, TNm, BtnBatal);
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
//        Valid.hapusTable(tabMode, TKd, "diet", "kd_diet");

        Sequel.mengedit("diet_master", "kd_diet='" + kdLama + "'", "flag='0'");        
        tampil();
        emptTeks();
}//GEN-LAST:event_BtnHapusActionPerformed

    private void BtnHapusKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_BtnHapusKeyPressed
        if(evt.getKeyCode()==KeyEvent.VK_SPACE){
            BtnHapusActionPerformed(null);
        }else{
            Valid.pindah(evt, BtnBatal, BtnEdit);
        }
}//GEN-LAST:event_BtnHapusKeyPressed

    private void BtnEditActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_BtnEditActionPerformed
        if (TKd.getText().trim().equals("")) {
            Valid.textKosong(TKd, "kode diet");
        } else if (TNm.getText().trim().equals("")) {
            Valid.textKosong(TNm, "nama diet");
        } else if (cmbJenis.getSelectedItem().equals(" ")) {
            JOptionPane.showMessageDialog(rootPane, "Pilih salah satu pilihan jenis makanannya dengan benar,...!!");
            cmbJenis.requestFocus();
        } else {
            Sequel.mengedit("diet_master", "kd_diet='" + kdLama + "'",
                    "kd_diet='" + TKd.getText() + "', "
                    + "nama_diet='" + TNm.getText() + "', "
                    + "jns_makanan='" + cmbJenis.getSelectedItem() + "'");
          
            if (tabMode.getRowCount() != 0) {
                tampil();
            }
            emptTeks();
        }
}//GEN-LAST:event_BtnEditActionPerformed

    private void BtnEditKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_BtnEditKeyPressed
        if(evt.getKeyCode()==KeyEvent.VK_SPACE){
            BtnEditActionPerformed(null);
        }else{
            Valid.pindah(evt, BtnHapus, BtnKeluar);
        }
}//GEN-LAST:event_BtnEditKeyPressed

    private void BtnKeluarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_BtnKeluarActionPerformed
        dispose();
}//GEN-LAST:event_BtnKeluarActionPerformed

    private void BtnKeluarKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_BtnKeluarKeyPressed
        if(evt.getKeyCode()==KeyEvent.VK_SPACE){
            dispose();
        }else{Valid.pindah(evt,BtnEdit,TCari);}
}//GEN-LAST:event_BtnKeluarKeyPressed

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
        tampil();
}//GEN-LAST:event_BtnCariActionPerformed

    private void BtnCariKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_BtnCariKeyPressed
        if(evt.getKeyCode()==KeyEvent.VK_SPACE){
            BtnCariActionPerformed(null);
        }else{
            Valid.pindah(evt, TCari, BtnAll);
        }
}//GEN-LAST:event_BtnCariKeyPressed

    private void BtnCariKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_BtnCariKeyReleased
        // TODO add your handling code here:
}//GEN-LAST:event_BtnCariKeyReleased

    private void BtnAllActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_BtnAllActionPerformed
        emptTeks();
        tampil();
}//GEN-LAST:event_BtnAllActionPerformed

    private void BtnAllKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_BtnAllKeyPressed
        if(evt.getKeyCode()==KeyEvent.VK_SPACE){
            BtnAllActionPerformed(null);
        }else{
            Valid.pindah(evt, BtnCari, TKd);
        }
}//GEN-LAST:event_BtnAllKeyPressed

    private void tbDietMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tbDietMouseClicked
        if(tabMode.getRowCount()!=0){
            try {
                getData();
            } catch (java.lang.NullPointerException e) {
            }
        }
}//GEN-LAST:event_tbDietMouseClicked

    private void tbDietKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_tbDietKeyPressed
        if(tabMode.getRowCount()!=0){
            if((evt.getKeyCode()==KeyEvent.VK_ENTER)||(evt.getKeyCode()==KeyEvent.VK_UP)||(evt.getKeyCode()==KeyEvent.VK_DOWN)){
                try {
                    getData();
                } catch (java.lang.NullPointerException e) {
                }
            }
        }
}//GEN-LAST:event_tbDietKeyPressed

    private void formWindowOpened(java.awt.event.WindowEvent evt) {//GEN-FIRST:event_formWindowOpened
        Sequel.cariIsiComboDB("SELECT jns_makanan FROM diet_master GROUP BY jns_makanan", cmbJenis);
        emptTeks();
        tampil();
    }//GEN-LAST:event_formWindowOpened

    private void cmbJenisKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_cmbJenisKeyPressed
        // TODO add your handling code here:
    }//GEN-LAST:event_cmbJenisKeyPressed

    /**
    * @param args the command line arguments
    */
    public static void main(String args[]) {
        java.awt.EventQueue.invokeLater(() -> {
            DlgDiet dialog = new DlgDiet(new javax.swing.JFrame(), true);
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
    private widget.Button BtnBatal;
    private widget.Button BtnCari;
    private widget.Button BtnEdit;
    private widget.Button BtnHapus;
    private widget.Button BtnKeluar;
    private widget.Button BtnSimpan;
    private widget.Label LCount;
    private widget.ScrollPane Scroll;
    public widget.TextBox TCari;
    private widget.TextBox TKd;
    private widget.TextBox TNm;
    private widget.ComboBox cmbJenis;
    private widget.InternalFrame internalFrame1;
    private widget.Label jLabel3;
    private widget.Label jLabel4;
    private widget.Label jLabel5;
    private widget.Label jLabel6;
    private widget.Label jLabel7;
    private javax.swing.JPanel jPanel3;
    private widget.panelisi panelGlass7;
    private widget.panelisi panelGlass8;
    private widget.panelisi panelGlass9;
    private widget.Table tbDiet;
    // End of variables declaration//GEN-END:variables

    private void tampil() {
        Valid.tabelKosong(tabMode);
        try {
            ps.setString(1, "%" + TCari.getText().trim() + "%");
            ps.setString(2, "%" + TCari.getText().trim() + "%");
            ps.setString(3, "%" + TCari.getText().trim() + "%");
            rs = ps.executeQuery();
            while (rs.next()) {
                tabMode.addRow(new Object[]{
                    rs.getString(1), 
                    rs.getString(2),
                    rs.getString(3)
                });
            }
        } catch (SQLException e) {
            System.out.println("Notifikasi : " + e);
        }
        LCount.setText("" + tabMode.getRowCount());
    }

    public void emptTeks() {
        kdLama = "";
        TKd.setText("");
        TNm.setText("");
        cmbJenis.setSelectedIndex(0);
        TCari.setText("");
        TNm.requestFocus();
        Valid.autoNomer("diet_master","DM",4,TKd);        
        TKd.requestFocus();
    }

    private void getData() {
        kdLama = "";
        if(tbDiet.getSelectedRow()!= -1){
            kdLama = tbDiet.getValueAt(tbDiet.getSelectedRow(),0).toString();
            TKd.setText(tbDiet.getValueAt(tbDiet.getSelectedRow(),0).toString());
            TNm.setText(tbDiet.getValueAt(tbDiet.getSelectedRow(),1).toString());
            cmbJenis.setSelectedItem(tbDiet.getValueAt(tbDiet.getSelectedRow(),2).toString());
        }
    }
    
    public JTextField getTextField(){
        return TKd;
    }

    public JButton getButton(){
        return BtnKeluar;
    }
    
    public void isCek(){
        BtnSimpan.setEnabled(var.getdiet_pasien());
        BtnHapus.setEnabled(var.getdiet_pasien());
        BtnEdit.setEnabled(var.getdiet_pasien());
    }
    
    public JTable getTable(){
        return tbDiet;
    }
}
