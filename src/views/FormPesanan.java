/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package views;

import controllers.PesananController;
import interfaces.CrudInterface;
import java.sql.SQLException;
import java.util.Map;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.DefaultComboBoxModel;
import javax.swing.JOptionPane;
import models.Pesanan;

/**
 *
 * @author mmohl
 */
public class FormPesanan extends javax.swing.JFrame {
    CrudInterface<Pesanan> controller;

    /**
     * Creates new form FormPesanan
     */
    public FormPesanan() {
        initComponents();
        controller = new PesananController();
        
        try {
            cbPasangan.setModel(loadPasanganModel());
        } catch (SQLException ex) {
            Logger.getLogger(FormPesanan.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        tfUangMuka.setEnabled(false);
        rbDebit.setActionCommand("0");
        rbKredit.setActionCommand("1");
        
    }
    
    private DefaultComboBoxModel loadPasanganModel() throws SQLException {
        DefaultComboBoxModel cm = PesananController.loadPasangan();
        return cm;
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
        cbPasangan = new javax.swing.JComboBox<>();
        jLabel1 = new javax.swing.JLabel();
        rbDebit = new javax.swing.JRadioButton();
        rbKredit = new javax.swing.JRadioButton();
        bSimpan = new javax.swing.JButton();
        lUangMuka = new javax.swing.JLabel();
        tfUangMuka = new javax.swing.JTextField();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        lPenyewa.setText("Penyewa");

        lKtp.setText("No. KTP");

        lNohape.setText("No. Handphone");

        lTanggal.setText("Tanggal Acara");

        lSewa.setText("Lama Sewa");

        lPasangan.setText("Pasangan");

        cbPasangan.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Item 1", "Item 2", "Item 3", "Item 4" }));

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

        bSimpan.setText("Simpan");
        bSimpan.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                bSimpanActionPerformed(evt);
            }
        });

        lUangMuka.setText("Uang Muka");

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(lPenyewa)
                            .addComponent(lKtp))
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(layout.createSequentialGroup()
                                .addGap(49, 49, 49)
                                .addComponent(tfKtp))
                            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 46, Short.MAX_VALUE)
                                .addComponent(tfPenyewa, javax.swing.GroupLayout.PREFERRED_SIZE, 309, javax.swing.GroupLayout.PREFERRED_SIZE))))
                    .addGroup(layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(lNohape)
                            .addComponent(lTanggal)
                            .addComponent(lSewa)
                            .addComponent(lPasangan)
                            .addComponent(jLabel1)
                            .addComponent(bSimpan)
                            .addComponent(lUangMuka))
                        .addGap(18, 18, 18)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(layout.createSequentialGroup()
                                .addComponent(rbDebit)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addComponent(rbKredit))
                            .addComponent(tfNohape)
                            .addComponent(dpTanggal, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(tfLama)
                            .addComponent(cbPasangan, 0, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(tfUangMuka))))
                .addContainerGap())
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
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
                    .addComponent(tfLama, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(lPasangan)
                    .addComponent(cbPasangan, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel1)
                    .addComponent(rbKredit)
                    .addComponent(rbDebit))
                .addGap(18, 18, 18)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(lUangMuka)
                    .addComponent(tfUangMuka, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 20, Short.MAX_VALUE)
                .addComponent(bSimpan)
                .addContainerGap())
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

    private void bSimpanActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_bSimpanActionPerformed
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
            JOptionPane.showMessageDialog(rootPane, prosesPasangan());
            
        } catch (SQLException ex) {
            Logger.getLogger(FormPesanan.class.getName()).log(Level.SEVERE, null, ex);
        }
    }//GEN-LAST:event_bSimpanActionPerformed

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
        
        id = String.valueOf( map.get( cbPasangan.getSelectedItem().toString() ) ); 
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
    private javax.swing.JButton bSimpan;
    private javax.swing.JComboBox<String> cbPasangan;
    private org.jdesktop.swingx.JXDatePicker dpTanggal;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel lKtp;
    private javax.swing.JLabel lNohape;
    private javax.swing.JLabel lPasangan;
    private javax.swing.JLabel lPenyewa;
    private javax.swing.JLabel lSewa;
    private javax.swing.JLabel lTanggal;
    private javax.swing.JLabel lUangMuka;
    private javax.swing.ButtonGroup pembayaranGrup;
    private javax.swing.JRadioButton rbDebit;
    private javax.swing.JRadioButton rbKredit;
    private javax.swing.JTextField tfKtp;
    private javax.swing.JTextField tfLama;
    private javax.swing.JTextField tfNohape;
    private javax.swing.JTextField tfPenyewa;
    private javax.swing.JTextField tfUangMuka;
    // End of variables declaration//GEN-END:variables
}
