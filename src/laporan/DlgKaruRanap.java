package laporan;
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
import java.util.HashMap;
import java.util.Map;
import javax.swing.JOptionPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.event.DocumentEvent;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableColumn;
import kepegawaian.DlgCariPegawai;

/**
 *
 * @author dosen
 */
public final class DlgKaruRanap extends javax.swing.JDialog {    
    private Connection koneksi=koneksiDB.condb();
    private sekuel Sequel=new sekuel();
    private validasi Valid=new validasi();
    public DlgCariPegawai pegawai = new DlgCariPegawai(null, false);
    private String noRM = "", kdKAMAR = "";
        
    /** Creates new form DlgPenyakit
     * @param parent
     * @param modal */
    
    public DlgKaruRanap(java.awt.Frame parent, boolean modal) {
        super(parent, modal);
        initComponents();
        this.setLocation(10,2);
        setSize(628,674);
        
        pegawai.addWindowListener(new WindowListener() {
            @Override
            public void windowOpened(WindowEvent e) {
            }

            @Override
            public void windowClosing(WindowEvent e) {
            }

            @Override
            public void windowClosed(WindowEvent e) {
                if (var.getform().equals("DlgKaruRanap")) {
                    if (pegawai.getTable().getSelectedRow() != -1) {
                        nipKaru.setText(pegawai.getTable().getValueAt(pegawai.getTable().getSelectedRow(), 0).toString());
                        nmKaru.setText(pegawai.getTable().getValueAt(pegawai.getTable().getSelectedRow(), 1).toString());                        
                    }
                    BtnNama.requestFocus();
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

        pegawai.getTable().addKeyListener(new KeyListener() {
            @Override
            public void keyTyped(KeyEvent e) {
            }

            @Override
            public void keyPressed(KeyEvent e) {
                if (var.getform().equals("DlgKaruRanap")) {
                    if (e.getKeyCode() == KeyEvent.VK_SPACE) {
                        pegawai.dispose();
                    }
                }
            }

            @Override
            public void keyReleased(KeyEvent e) {
            }
        });
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
        jPanel3 = new javax.swing.JPanel();
        panelGlass8 = new widget.panelisi();
        BtnPrint = new widget.Button();
        BtnKeluar = new widget.Button();
        PanelInput = new javax.swing.JPanel();
        FormInput = new widget.PanelBiasa();
        jLabel3 = new widget.Label();
        nipKaru = new widget.TextBox();
        nmKaru = new widget.TextBox();
        jLabel9 = new widget.Label();
        BtnNama = new widget.Button();

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);
        setUndecorated(true);
        setResizable(false);
        addWindowListener(new java.awt.event.WindowAdapter() {
            public void windowOpened(java.awt.event.WindowEvent evt) {
                formWindowOpened(evt);
            }
        });

        internalFrame1.setBorder(javax.swing.BorderFactory.createTitledBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 153, 255), 3), "::[ Kepala Ruang Perawatan Inap ]::", javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Tahoma", 1, 12), new java.awt.Color(0, 0, 0))); // NOI18N
        internalFrame1.setName("internalFrame1"); // NOI18N
        internalFrame1.setLayout(new java.awt.BorderLayout(1, 1));

        jPanel3.setName("jPanel3"); // NOI18N
        jPanel3.setOpaque(false);
        jPanel3.setPreferredSize(new java.awt.Dimension(44, 55));
        jPanel3.setLayout(new java.awt.BorderLayout(1, 1));

        panelGlass8.setName("panelGlass8"); // NOI18N
        panelGlass8.setPreferredSize(new java.awt.Dimension(44, 44));
        panelGlass8.setLayout(new java.awt.FlowLayout(java.awt.FlowLayout.RIGHT, 5, 8));

        BtnPrint.setForeground(new java.awt.Color(0, 0, 0));
        BtnPrint.setIcon(new javax.swing.ImageIcon(getClass().getResource("/picture/b_print.png"))); // NOI18N
        BtnPrint.setMnemonic('T');
        BtnPrint.setText("Cetak Surat");
        BtnPrint.setToolTipText("Alt+T");
        BtnPrint.setName("BtnPrint"); // NOI18N
        BtnPrint.setPreferredSize(new java.awt.Dimension(120, 30));
        BtnPrint.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                BtnPrintActionPerformed(evt);
            }
        });
        panelGlass8.add(BtnPrint);

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
        panelGlass8.add(BtnKeluar);

        jPanel3.add(panelGlass8, java.awt.BorderLayout.CENTER);

        internalFrame1.add(jPanel3, java.awt.BorderLayout.PAGE_END);

        PanelInput.setName("PanelInput"); // NOI18N
        PanelInput.setOpaque(false);
        PanelInput.setPreferredSize(new java.awt.Dimension(611, 80));
        PanelInput.setLayout(new java.awt.BorderLayout(1, 1));

        FormInput.setName("FormInput"); // NOI18N
        FormInput.setPreferredSize(new java.awt.Dimension(611, 100));
        FormInput.setLayout(null);

        jLabel3.setForeground(new java.awt.Color(0, 0, 0));
        jLabel3.setText("NIP : ");
        jLabel3.setName("jLabel3"); // NOI18N
        FormInput.add(jLabel3);
        jLabel3.setBounds(0, 12, 100, 23);

        nipKaru.setEditable(false);
        nipKaru.setForeground(new java.awt.Color(0, 0, 0));
        nipKaru.setName("nipKaru"); // NOI18N
        FormInput.add(nipKaru);
        nipKaru.setBounds(103, 12, 190, 23);

        nmKaru.setEditable(false);
        nmKaru.setForeground(new java.awt.Color(0, 0, 0));
        nmKaru.setName("nmKaru"); // NOI18N
        FormInput.add(nmKaru);
        nmKaru.setBounds(103, 42, 270, 23);

        jLabel9.setForeground(new java.awt.Color(0, 0, 0));
        jLabel9.setText("Nama Pegawai : ");
        jLabel9.setName("jLabel9"); // NOI18N
        FormInput.add(jLabel9);
        jLabel9.setBounds(0, 42, 100, 23);

        BtnNama.setForeground(new java.awt.Color(0, 0, 0));
        BtnNama.setIcon(new javax.swing.ImageIcon(getClass().getResource("/picture/190.png"))); // NOI18N
        BtnNama.setMnemonic('2');
        BtnNama.setToolTipText("Alt+2");
        BtnNama.setName("BtnNama"); // NOI18N
        BtnNama.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                BtnNamaActionPerformed(evt);
            }
        });
        FormInput.add(BtnNama);
        BtnNama.setBounds(375, 42, 28, 23);

        PanelInput.add(FormInput, java.awt.BorderLayout.CENTER);

        internalFrame1.add(PanelInput, java.awt.BorderLayout.PAGE_START);

        getContentPane().add(internalFrame1, java.awt.BorderLayout.CENTER);

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void BtnKeluarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_BtnKeluarActionPerformed
        dispose();
}//GEN-LAST:event_BtnKeluarActionPerformed

    private void BtnPrintActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_BtnPrintActionPerformed
        this.setCursor(Cursor.getPredefinedCursor(Cursor.WAIT_CURSOR));
        if (nipKaru.getText().trim().equals("")) {
            JOptionPane.showMessageDialog(null, "Pilih salah satu nama pegawai utk. KARU nya...!!!!");
        } else {
            Map<String, Object> param = new HashMap<>();
            param.put("nip_karu", "NIP. " + nipKaru.getText());
            param.put("nama_karu", nmKaru.getText());
            Valid.MyReport("rptSuratKeteranganRawatCovid.jrxml", "report", "::[ Cetak Surat Keterangan Perawatan Inap (Pasien COVID-19) ]::",
                    " SELECT p.no_rkm_medis, p.nm_pasien, CONCAT(DATE_FORMAT(p.tgl_lahir,'%d-%m-%Y'),' / ',rp.umurdaftar,' ',rp.sttsumur,'.') tgl_lhr_umur, "
                    + "ifnull(bs.no_kartu,'-') no_kartu, IF(ki.diagnosa_awal,'-',ki.diagnosa_awal) diag_awal, DATE_FORMAT(rp.tgl_registrasi,'%d-%m-%Y') tgl_msk, b.nm_bangsal, "
                    + "CONCAT('Martapura, ',DATE_FORMAT(rp.tgl_registrasi,'%d-%m-%Y')) tgl_surt, b.nm_gedung FROM reg_periksa rp "
                    + "INNER JOIN pasien p ON p.no_rkm_medis=rp.no_rkm_medis INNER JOIN kamar_inap ki on ki.no_rawat=rp.no_rawat "
                    + "INNER JOIN kamar k on k.kd_kamar=ki.kd_kamar INNER JOIN bangsal b on b.kd_bangsal=k.kd_bangsal "
                    + "LEFT JOIN bridging_sep bs on bs.no_rawat=ki.no_rawat WHERE rp.no_rkm_medis='" + noRM + "' AND ki.kd_kamar='" + kdKAMAR + "'", param);
            dispose();
        }
        this.setCursor(Cursor.getDefaultCursor());
}//GEN-LAST:event_BtnPrintActionPerformed

    private void formWindowOpened(java.awt.event.WindowEvent evt) {//GEN-FIRST:event_formWindowOpened
        nipKaru.setText("");
        nmKaru.setText("");
        BtnNama.requestFocus();
    }//GEN-LAST:event_formWindowOpened

    private void BtnNamaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_BtnNamaActionPerformed
        var.setform("DlgKaruRanap");
        pegawai.isCek();
        pegawai.emptTeks();
        pegawai.TCari.setText("");
        pegawai.TCari.requestFocus();
        pegawai.setSize(1039, 268);
        pegawai.setLocationRelativeTo(internalFrame1);
        pegawai.setVisible(true);
    }//GEN-LAST:event_BtnNamaActionPerformed

    /**
    * @param args the command line arguments
    */
    public static void main(String args[]) {
        java.awt.EventQueue.invokeLater(() -> {
            DlgKaruRanap dialog = new DlgKaruRanap(new javax.swing.JFrame(), true);
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
    private widget.Button BtnNama;
    private widget.Button BtnPrint;
    private widget.PanelBiasa FormInput;
    private javax.swing.JPanel PanelInput;
    private widget.InternalFrame internalFrame1;
    private widget.Label jLabel3;
    private widget.Label jLabel9;
    private javax.swing.JPanel jPanel3;
    private widget.TextBox nipKaru;
    private widget.TextBox nmKaru;
    private widget.panelisi panelGlass8;
    // End of variables declaration//GEN-END:variables

    public JTextField getTextField(){
        return nipKaru;
    }
    
    public void setData(String nomorrm, String kodekamar) {
        noRM = nomorrm;
        kdKAMAR = kodekamar;
    }
}