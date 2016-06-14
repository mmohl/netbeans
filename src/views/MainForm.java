/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package views;

import controllers.UserController;
import helpers.DbMaintenance;
import java.io.File;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JFileChooser;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.filechooser.FileNameExtensionFilter;

/**
 *
 * @author mmohl
 */
public class MainForm extends javax.swing.JFrame {
    JPanel panel;
    /*
     * Creates new form MainForm
     */
    public MainForm() {
        super("Main Form");
        setDefaultCloseOperation(JFrame.DO_NOTHING_ON_CLOSE);
        initComponents();
    }
    

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        bBackup = new javax.swing.JButton();
        bRestore = new javax.swing.JButton();
        lRestore = new javax.swing.JLabel();
        jMenuBar1 = new javax.swing.JMenuBar();
        menuMaster = new javax.swing.JMenu();
        masterPegawai = new javax.swing.JMenuItem();
        masterKategori = new javax.swing.JMenuItem();
        masteUser = new javax.swing.JMenuItem();
        masterPasangan = new javax.swing.JMenuItem();
        menuTransaction = new javax.swing.JMenu();
        transaksiPemesanan = new javax.swing.JMenuItem();
        transaksiLaporan = new javax.swing.JMenuItem();
        menuLogout = new javax.swing.JMenu();
        sistemLogout = new javax.swing.JMenuItem();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        bBackup.setText("Backup");
        bBackup.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                bBackupActionPerformed(evt);
            }
        });

        bRestore.setText("Restore");
        bRestore.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                bRestoreActionPerformed(evt);
            }
        });

        menuMaster.setText("Master");

        masterPegawai.setText("Pegawai");
        masterPegawai.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                masterPegawaiActionPerformed(evt);
            }
        });
        menuMaster.add(masterPegawai);

        masterKategori.setText("Kategori");
        masterKategori.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                masterKategoriActionPerformed(evt);
            }
        });
        menuMaster.add(masterKategori);

        masteUser.setText("User");
        masteUser.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                masteUserActionPerformed(evt);
            }
        });
        menuMaster.add(masteUser);

        masterPasangan.setText("Pasangan");
        masterPasangan.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                masterPasanganActionPerformed(evt);
            }
        });
        menuMaster.add(masterPasangan);

        jMenuBar1.add(menuMaster);

        menuTransaction.setText("Transaksi");

        transaksiPemesanan.setText("Pemesanan");
        transaksiPemesanan.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                transaksiPemesananActionPerformed(evt);
            }
        });
        menuTransaction.add(transaksiPemesanan);

        transaksiLaporan.setText("Laporan");
        menuTransaction.add(transaksiLaporan);

        jMenuBar1.add(menuTransaction);

        menuLogout.setText("Sistem");
        menuLogout.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                menuLogoutActionPerformed(evt);
            }
        });

        sistemLogout.setText("Logout");
        sistemLogout.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                sistemLogoutActionPerformed(evt);
            }
        });
        menuLogout.add(sistemLogout);

        jMenuBar1.add(menuLogout);

        setJMenuBar(jMenuBar1);

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(bBackup)
                    .addComponent(bRestore)
                    .addComponent(lRestore))
                .addContainerGap(322, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(bBackup)
                .addGap(18, 18, 18)
                .addComponent(bRestore)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(lRestore)
                .addContainerGap(165, Short.MAX_VALUE))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void menuLogoutActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_menuLogoutActionPerformed

    }//GEN-LAST:event_menuLogoutActionPerformed

    private void sistemLogoutActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_sistemLogoutActionPerformed
        try {
            // TODO add your handling code here:
//            JOptionPane.showConfirmDialog(rootPane, "Apakah anda yakin");
            UserController.Logout(1);
//            JOptionPane.showMessageDialog(rootPane, "Sampai Jumpa");
            FormLogin login = new FormLogin();
            login.setVisible(true);
            this.dispose();
            
        } catch (SQLException ex) {
            Logger.getLogger(MainForm.class.getName()).log(Level.SEVERE, null, ex);
        }
    }//GEN-LAST:event_sistemLogoutActionPerformed

    private void masteUserActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_masteUserActionPerformed
        // TODO add your handling code here:
        FormUser user = new FormUser();
        user.setVisible(true);
    }//GEN-LAST:event_masteUserActionPerformed

    private void masterKategoriActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_masterKategoriActionPerformed
        // TODO add your handling code here:
        FormKategori k = new FormKategori();
        k.setVisible(true);
    }//GEN-LAST:event_masterKategoriActionPerformed

    private void masterPegawaiActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_masterPegawaiActionPerformed
        // TODO add your handling code here:
        FormPegawai form = new FormPegawai();
        form.setVisible(true);
    }//GEN-LAST:event_masterPegawaiActionPerformed

    private void masterPasanganActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_masterPasanganActionPerformed
//        // TODO add your handling code here:
//        panel = 
        FormPasangan form = new FormPasangan();
        form.setVisible(true);
    }//GEN-LAST:event_masterPasanganActionPerformed

    private void transaksiPemesananActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_transaksiPemesananActionPerformed
        // TODO add your handling code here:
        FormPesanan form = new FormPesanan();
        form.setVisible(true);
    }//GEN-LAST:event_transaksiPemesananActionPerformed

    private void bBackupActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_bBackupActionPerformed
        // TODO add your handling code here:
        DbMaintenance.Backupdbtosql();
    }//GEN-LAST:event_bBackupActionPerformed

    private void bRestoreActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_bRestoreActionPerformed
        // TODO add your handling code here:
        JFileChooser fc = new JFileChooser();
        int value;
        FileNameExtensionFilter filter = 
                new FileNameExtensionFilter("SQL", "sql");
        fc.setFileFilter(filter);
        
        value = fc.showOpenDialog(this);
        
        if (value == JFileChooser.APPROVE_OPTION) {
            File file = fc.getSelectedFile();
            String name = file.getName();
            String path = file.getAbsolutePath();
            
            lRestore.setText(name);
            System.out.println(path);
            
            DbMaintenance.Restoredbfromsql(name, path);
        }
        
    }//GEN-LAST:event_bRestoreActionPerformed

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
            java.util.logging.Logger.getLogger(MainForm.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(MainForm.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(MainForm.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(MainForm.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new MainForm().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton bBackup;
    private javax.swing.JButton bRestore;
    private javax.swing.JMenuBar jMenuBar1;
    private javax.swing.JLabel lRestore;
    private javax.swing.JMenuItem masteUser;
    private javax.swing.JMenuItem masterKategori;
    private javax.swing.JMenuItem masterPasangan;
    private javax.swing.JMenuItem masterPegawai;
    private javax.swing.JMenu menuLogout;
    private javax.swing.JMenu menuMaster;
    private javax.swing.JMenu menuTransaction;
    private javax.swing.JMenuItem sistemLogout;
    private javax.swing.JMenuItem transaksiLaporan;
    private javax.swing.JMenuItem transaksiPemesanan;
    // End of variables declaration//GEN-END:variables
}
