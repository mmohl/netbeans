/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package views;

import controllers.PasanganController;
import controllers.PesananController;
import interfaces.CrudInterface;
import java.awt.HeadlessException;
import java.awt.event.KeyEvent;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.ButtonGroup;
import javax.swing.DefaultListModel;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.JOptionPane;
import javax.swing.JRadioButton;
import javax.swing.JScrollPane;
import javax.swing.JSeparator;
import javax.swing.JTextField;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;
import models.Pesanan;
import org.jdesktop.swingx.JXDatePicker;

/**
 *
 * @author mmohl
 */
public class FormPesanan extends javax.swing.JFrame {
    CrudInterface<Pesanan> controller;
    List<Pesanan> record = new ArrayList<Pesanan>();
    int row = 0;

    public FormPesanan(CrudInterface<Pesanan> controller, JButton bReset, JButton bSave, JButton bSearch, JComboBox<String> cbPasangan, JXDatePicker dpTanggal, JLabel jLabel1, JLabel jLabel2, JLabel jLabel3, JScrollPane jScrollPane1, JScrollPane jScrollPane2, JSeparator jSeparator3, JLabel lKtp, JLabel lNohape, JLabel lPasangan, JLabel lPenyewa, JLabel lSewa, JLabel lTanggal, JLabel lUangMuka, JList<String> lbKategori, JList<String> lbPasangan, ButtonGroup pembayaranGrup, JRadioButton rbDebit, JRadioButton rbKredit, JTextField tfKtp, JTextField tfLama, JTextField tfNohape, JTextField tfPenyewa, JTextField tfUangMuka) throws HeadlessException {
        this.controller = controller;
        this.bReset = bReset;
        this.bSave = bSave;
        this.bSearch = bSearch;
        this.dpTanggal = dpTanggal;
        this.jLabel1 = jLabel1;
        this.jLabel2 = jLabel2;
        this.jLabel3 = jLabel3;
        this.jScrollPane1 = jScrollPane1;
        this.jScrollPane2 = jScrollPane2;
        this.lKtp = lKtp;
        this.lNohape = lNohape;
        this.lPasangan = lPasangan;
        this.lPenyewa = lPenyewa;
        this.lSewa = lSewa;
        this.lTanggal = lTanggal;
        this.lUangMuka = lUangMuka;
        this.lbKategori = lbKategori;
        this.lbPasangan = lbPasangan;
        this.pembayaranGrup = pembayaranGrup;
        this.rbDebit = rbDebit;
        this.rbKredit = rbKredit;
        this.tfKtp = tfKtp;
        this.tfLama = tfLama;
        this.tfNohape = tfNohape;
        this.tfPenyewa = tfPenyewa;
        this.tfUangMuka = tfUangMuka;
    }

    /**
     * Creates new form FormPesanan
     */
    public FormPesanan() {
        initComponents();
        controller = new PesananController();

        lbPasangan.setModel(loadListPasangan());
        lbKategori.setModel(loadListKategori());
       
        
        tfUangMuka.setEnabled(false);
        rbDebit.setActionCommand("0");
        rbKredit.setActionCommand("1");
        tfHarga.setEditable(false);
        
        lbPasangan.addListSelectionListener(new ListSelectionListener() {
            @Override
            public void valueChanged(ListSelectionEvent e) {
                String name;
                int id;
                String harga;
                try {
                    name = lbPasangan.getSelectedValue();
                    id = PesananController.getIdPegawai(name);
                    harga = PesananController.getTotalPrice(id);
                    
                    tfHarga.setText(harga);
                    
                    String imageName = PesananController.getPicture(id);
                    String imagePath = "/images/";
                    ImageIcon icon = new ImageIcon(getClass().getResource(imagePath + imageName));
                    lPicture.setIcon(icon);
                    
                } catch (SQLException ex) {
                    Logger.getLogger(FormPesanan.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
        });
        
    }
    
    private DefaultListModel loadListPasangan() {
        DefaultListModel<String> model = PesananController.loadModelCouples();
        return model;
    }
    
    private DefaultListModel loadListKategori() {
        DefaultListModel<String> model = PesananController.loadModelCategories();
        return model;
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        pembayaranGrup = new javax.swing.ButtonGroup();
        lPenyewa = new javax.swing.JLabel();
        tfPenyewa = new javax.swing.JTextField();
        lKtp = new javax.swing.JLabel();
        tfKtp = new javax.swing.JTextField();
        lNohape = new javax.swing.JLabel();
        tfNohape = new javax.swing.JTextField();
        lTanggal = new javax.swing.JLabel();
        dpTanggal = new org.jdesktop.swingx.JXDatePicker();
        lSewa = new javax.swing.JLabel();
        tfLama = new javax.swing.JTextField();
        lPasangan = new javax.swing.JLabel();
        jLabel1 = new javax.swing.JLabel();
        rbDebit = new javax.swing.JRadioButton();
        rbKredit = new javax.swing.JRadioButton();
        bSave = new javax.swing.JButton();
        lUangMuka = new javax.swing.JLabel();
        tfUangMuka = new javax.swing.JTextField();
        bReset = new javax.swing.JButton();
        bSearch = new javax.swing.JButton();
        jScrollPane1 = new javax.swing.JScrollPane();
        lbKategori = new javax.swing.JList<>();
        jLabel2 = new javax.swing.JLabel();
        jLabel3 = new javax.swing.JLabel();
        jScrollPane2 = new javax.swing.JScrollPane();
        lbPasangan = new javax.swing.JList<>();
        lHarga = new javax.swing.JLabel();
        tfHarga = new javax.swing.JTextField();
        lPicture = new javax.swing.JLabel();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        lPenyewa.setText("Penyewa");

        tfPenyewa.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyTyped(java.awt.event.KeyEvent evt) {
                tfPenyewaKeyTyped(evt);
            }
        });

        lKtp.setText("No. KTP");

        tfKtp.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyTyped(java.awt.event.KeyEvent evt) {
                tfKtpKeyTyped(evt);
            }
        });

        lNohape.setText("No. Handphone");

        tfNohape.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyTyped(java.awt.event.KeyEvent evt) {
                tfNohapeKeyTyped(evt);
            }
        });

        lTanggal.setText("Tanggal Acara");

        lSewa.setText("Lama Sewa");

        tfLama.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyTyped(java.awt.event.KeyEvent evt) {
                tfLamaKeyTyped(evt);
            }
        });

        lPasangan.setText("Pasangan");

        jLabel1.setText("Pembayaran");

        pembayaranGrup.add(rbDebit);
        rbDebit.setText("Debit/Lunas");
        rbDebit.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                rbDebitActionPerformed(evt);
            }
        });

        pembayaranGrup.add(rbKredit);
        rbKredit.setText("Kredit/Uang Muka");
        rbKredit.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                rbKreditActionPerformed(evt);
            }
        });

        bSave.setText("Save");
        bSave.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                bSaveActionPerformed(evt);
            }
        });

        lUangMuka.setText("Uang Muka");

        tfUangMuka.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyTyped(java.awt.event.KeyEvent evt) {
                tfUangMukaKeyTyped(evt);
            }
        });

        bReset.setText("Reset");
        bReset.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                bResetActionPerformed(evt);
            }
        });

        bSearch.setText("Search");
        bSearch.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                bSearchActionPerformed(evt);
            }
        });

        lbKategori.setModel(new javax.swing.AbstractListModel<String>() {
            String[] strings = { "Item 1", "Item 2", "Item 3", "Item 4", "Item 5" };
            public int getSize() { return strings.length; }
            public String getElementAt(int i) { return strings[i]; }
        });
        jScrollPane1.setViewportView(lbKategori);

        jLabel2.setText("Filter");

        jLabel3.setText("Categories");

        lbPasangan.setModel(new javax.swing.AbstractListModel<String>() {
            String[] strings = { "Item 1", "Item 2", "Item 3", "Item 4", "Item 5" };
            public int getSize() { return strings.length; }
            public String getElementAt(int i) { return strings[i]; }
        });
        jScrollPane2.setViewportView(lbPasangan);

        lHarga.setText("Harga");

        lPicture.setText("image");

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(lPasangan)
                            .addComponent(jLabel1)
                            .addComponent(lUangMuka)
                            .addComponent(lHarga))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addComponent(jLabel2)
                            .addComponent(jScrollPane1)
                            .addGroup(layout.createSequentialGroup()
                                .addComponent(bSearch)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 22, Short.MAX_VALUE)
                                .addComponent(bReset))
                            .addComponent(jLabel3)))
                    .addGroup(layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(layout.createSequentialGroup()
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addGroup(layout.createSequentialGroup()
                                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                            .addComponent(lPenyewa)
                                            .addComponent(lKtp))
                                        .addGap(49, 49, 49)
                                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                            .addComponent(tfKtp, javax.swing.GroupLayout.DEFAULT_SIZE, 324, Short.MAX_VALUE)
                                            .addComponent(tfPenyewa)))
                                    .addGroup(layout.createSequentialGroup()
                                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                            .addComponent(lNohape)
                                            .addComponent(lTanggal)
                                            .addComponent(lSewa))
                                        .addGap(18, 18, 18)
                                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                            .addComponent(jScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, 131, javax.swing.GroupLayout.PREFERRED_SIZE)
                                            .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                                .addComponent(tfLama)
                                                .addComponent(tfNohape)
                                                .addComponent(dpTanggal, javax.swing.GroupLayout.DEFAULT_SIZE, 326, Short.MAX_VALUE))
                                            .addComponent(tfHarga, javax.swing.GroupLayout.PREFERRED_SIZE, 212, javax.swing.GroupLayout.PREFERRED_SIZE)
                                            .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                                                .addComponent(tfUangMuka, javax.swing.GroupLayout.PREFERRED_SIZE, 212, javax.swing.GroupLayout.PREFERRED_SIZE)
                                                .addGroup(layout.createSequentialGroup()
                                                    .addComponent(rbDebit)
                                                    .addGap(18, 18, 18)
                                                    .addComponent(rbKredit))))))
                                .addGap(18, 18, 18)
                                .addComponent(lPicture, javax.swing.GroupLayout.PREFERRED_SIZE, 148, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addComponent(bSave))
                        .addGap(0, 0, Short.MAX_VALUE)))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addGroup(layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(lPenyewa)
                            .addComponent(tfPenyewa, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(18, 18, 18)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(lKtp)
                            .addComponent(tfKtp, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(18, 18, 18)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(lNohape)
                            .addComponent(tfNohape, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(18, 18, 18)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(lTanggal)
                            .addComponent(dpTanggal, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(18, 18, 18)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(lSewa)
                            .addComponent(tfLama, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                    .addComponent(lPicture, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addGap(18, 18, 18)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(lPasangan)
                        .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                    .addGroup(layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(layout.createSequentialGroup()
                                .addGap(0, 0, Short.MAX_VALUE)
                                .addComponent(jScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, 121, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                    .addComponent(lHarga)
                                    .addComponent(tfHarga, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                                .addGap(18, 18, 18)
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                        .addComponent(rbDebit)
                                        .addComponent(rbKredit))
                                    .addComponent(jLabel1))
                                .addGap(22, 22, 22)
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                    .addComponent(lUangMuka)
                                    .addComponent(tfUangMuka, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                                .addGap(21, 21, 21))
                            .addGroup(layout.createSequentialGroup()
                                .addComponent(jLabel2)
                                .addGap(17, 17, 17)
                                .addComponent(jLabel3)
                                .addGap(15, 15, 15)
                                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                    .addComponent(bSearch)
                                    .addComponent(bReset))
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)))
                        .addComponent(bSave)
                        .addGap(22, 22, 22))))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void rbDebitActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_rbDebitActionPerformed
        // TODO add your handling code here:
        tfUangMuka.setText("");
        tfUangMuka.setEnabled(false);
    }//GEN-LAST:event_rbDebitActionPerformed

    private void rbKreditActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_rbKreditActionPerformed
        // TODO add your handling code here:
        tfUangMuka.setEnabled(true);
    }//GEN-LAST:event_rbKreditActionPerformed

    private void bSaveActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_bSaveActionPerformed
        try {
            Pesanan pesanan = new Pesanan();
            
            pesanan.setId(null);
            pesanan.setKtp(tfKtp.getText());
            pesanan.setLama(tfLama.getText());
            pesanan.setStatus(pembayaranGrup.getSelection().getActionCommand());
            pesanan.setNohape(tfNohape.getText());
            pesanan.setPasangan(prosesPasangan());
            pesanan.setPenyewa(tfPenyewa.getText());
            pesanan.setTgl(prosesTanggal());
            
            controller.Create(pesanan);
            
        } catch (SQLException ex) {
            Logger.getLogger(FormPesanan.class.getName()).log(Level.SEVERE, null, ex);
        }
//        JOptionPane.showMessageDialog(rootPane, lbPasangan.getSelectedValue().toString());
    }//GEN-LAST:event_bSaveActionPerformed

    private void bSearchActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_bSearchActionPerformed
        // TODO add your handling code here:
        String categories = multiKategori();
        try {
            DefaultListModel model = PesananController.getFilteredModel(categories);
            lbPasangan.removeAll();
            lbPasangan.setModel(model);
            lbPasangan.clearSelection();
            jScrollPane2.setViewportView(lbPasangan);
        } catch (SQLException ex) {
            Logger.getLogger(FormPesanan.class.getName()).log(Level.SEVERE, null, ex);
        }
    }//GEN-LAST:event_bSearchActionPerformed

    private void bResetActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_bResetActionPerformed
        // TODO add your handling code here:
//        DefaultListModel model = loadListPasangan();
//        lbPasangan.removeAll();
//        lbPasangan.setModel(model);
//        lbPasangan.clearSelection();
        jScrollPane1.setViewportView(lbKategori);
        jScrollPane2.setViewportView(lbPasangan);
        lPicture.remove(0);
    }//GEN-LAST:event_bResetActionPerformed

    private void tfPenyewaKeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_tfPenyewaKeyTyped
        // TODO add your handling code here:
        validasiHuruf(evt);
    }//GEN-LAST:event_tfPenyewaKeyTyped

    private void tfKtpKeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_tfKtpKeyTyped
        // TODO add your handling code here:
        validasiAngka(evt);
    }//GEN-LAST:event_tfKtpKeyTyped

    private void tfNohapeKeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_tfNohapeKeyTyped
        // TODO add your handling code here:
        validasiAngka(evt);
    }//GEN-LAST:event_tfNohapeKeyTyped

    private void tfLamaKeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_tfLamaKeyTyped
        // TODO add your handling code here:
        validasiAngka(evt);
    }//GEN-LAST:event_tfLamaKeyTyped

    private void tfUangMukaKeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_tfUangMukaKeyTyped
        // TODO add your handling code here:
        validasiAngka(evt);
    }//GEN-LAST:event_tfUangMukaKeyTyped
    
    protected void validasiAngka(KeyEvent k) {
        if (Character.isAlphabetic(k.getKeyChar())) {
            k.consume();
        }
    }
    
    protected void validasiHuruf(KeyEvent k) {
        if (Character.isDigit(k.getKeyChar())) {
            k.consume();
        }
    }
    
    private String multiKategori() {
        int[] k = lbKategori.getSelectedIndices();
        String id = "";
        Map<String, Integer> _id = PasanganController.loadModelId();
        
        for (int i = 0; i < k.length; i++) {
            
              if (!id.isEmpty()) {
                  id += "-";
              }
              
              if ( _id.containsKey(lbKategori.getModel().getElementAt(k[i])) ) {
                  id += String.valueOf( _id.get(lbKategori.getModel().getElementAt(k[i])) );
              }
        }
        return id;
    }
    
    private String prosesTanggal() {
        String tanggal = "";
        String y, m, d;
        
        y = String.valueOf(dpTanggal.getDate().getYear() + 1900);
        m = String.valueOf(dpTanggal.getDate().getMonth() + 1);
        d = String.valueOf(dpTanggal.getDate().getDate());
        tanggal = y + "-" + m +"-" + d;
        
        return tanggal;
    }
    
    private String prosesPasangan() throws SQLException {
        String id = "";
        Map<String, Integer> map = PesananController.loadPasanganId();
        
        id = String.valueOf( map.get( lbPasangan.getSelectedValue().toString() ) ); 
        return id;
    }
    
    /**
     * @param args the command line arguments
     */
    public static void main(String args[]) {
        /* Set the Nimbus look and feel */
        //<editor-fold defaultstate="collapsed" desc=" Look and feel setting code (optional) ">
        /* If Nimbus (introduced in Java SE 6) is not available, stay with the default look and feel.
         * For details see http://download.oracle.com/javase/tutorial/uiswing/lookandfeel/plaf.html 
         */
        try {
            for (javax.swing.UIManager.LookAndFeelInfo info : javax.swing.UIManager.getInstalledLookAndFeels()) {
                if ("Nimbus".equals(info.getName())) {
                    javax.swing.UIManager.setLookAndFeel(info.getClassName());
                    break;
                }
            }
        } catch (ClassNotFoundException ex) {
            java.util.logging.Logger.getLogger(FormPesanan.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(FormPesanan.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(FormPesanan.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(FormPesanan.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new FormPesanan().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton bReset;
    private javax.swing.JButton bSave;
    private javax.swing.JButton bSearch;
    private org.jdesktop.swingx.JXDatePicker dpTanggal;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JLabel lHarga;
    private javax.swing.JLabel lKtp;
    private javax.swing.JLabel lNohape;
    private javax.swing.JLabel lPasangan;
    private javax.swing.JLabel lPenyewa;
    private javax.swing.JLabel lPicture;
    private javax.swing.JLabel lSewa;
    private javax.swing.JLabel lTanggal;
    private javax.swing.JLabel lUangMuka;
    private javax.swing.JList<String> lbKategori;
    private javax.swing.JList<String> lbPasangan;
    private javax.swing.ButtonGroup pembayaranGrup;
    private javax.swing.JRadioButton rbDebit;
    private javax.swing.JRadioButton rbKredit;
    private javax.swing.JTextField tfHarga;
    private javax.swing.JTextField tfKtp;
    private javax.swing.JTextField tfLama;
    private javax.swing.JTextField tfNohape;
    private javax.swing.JTextField tfPenyewa;
    private javax.swing.JTextField tfUangMuka;
    // End of variables declaration//GEN-END:variables
}
