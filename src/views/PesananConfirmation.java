/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package views;

import controllers.KasController;
import controllers.PesananController;
import controllers.SaldoController;
import helpers.Limiter;
import helpers.Status;
import interfaces.CrudInterface;
import interfaces.FormUtility;
import java.sql.SQLException;
import java.util.List;
import java.util.Map;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JOptionPane;
import javax.swing.event.ListSelectionEvent;
import javax.swing.table.DefaultTableModel;
import models.Kas;
import models.Pesanan;
import models.Saldo;

/**
 *
 * @author mmohl
 */
public class PesananConfirmation extends javax.swing.JFrame implements FormUtility{
    List<Pesanan> record;
    CrudInterface<Pesanan> controller;
    int id;
    int row;

    /**
     * Creates new form PesananConfirmation
     */
    public PesananConfirmation() {
        super("Confirmation");
        initComponents();
        controller = new PesananController();
        
        tfInvoice.setDocument(new Limiter((byte) 17).getFilter(tfInvoice));
        
        tfDate.setEditable(false);
        tfInvoice.setEditable(false);
        tfKtp.setEditable(false);
        tfName.setEditable(false);
        
        jTable1.getSelectionModel().addListSelectionListener((ListSelectionEvent e) -> {
            row = jTable1.getSelectedRow();
            if (row != -1) {
                setToTextField();
            
                bConfirm.setEnabled(true);
                bReset.setEnabled(true);

            }

        });
        
        initialitation();
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jTextField1 = new javax.swing.JTextField();
        bSearch = new javax.swing.JButton();
        jScrollPane1 = new javax.swing.JScrollPane();
        jTable1 = new javax.swing.JTable();
        tfInvoice = new javax.swing.JTextField();
        tfName = new javax.swing.JTextField();
        tfKtp = new javax.swing.JTextField();
        tfDate = new javax.swing.JTextField();
        bConfirm = new javax.swing.JButton();
        bReset = new javax.swing.JButton();
        lInvoice = new javax.swing.JLabel();
        lName = new javax.swing.JLabel();
        lKtp = new javax.swing.JLabel();
        lDate = new javax.swing.JLabel();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        bSearch.setText("Search");
        bSearch.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                bSearchActionPerformed(evt);
            }
        });

        jTable1.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null}
            },
            new String [] {
                "Title 1", "Title 2", "Title 3", "Title 4"
            }
        ));
        jScrollPane1.setViewportView(jTable1);

        bConfirm.setText("Confirm");
        bConfirm.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                bConfirmActionPerformed(evt);
            }
        });

        bReset.setText("Reset");
        bReset.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                bResetActionPerformed(evt);
            }
        });

        lInvoice.setText("Invoice");

        lName.setText("Name");

        lKtp.setText("KTP");

        lDate.setText("Date");

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jTextField1)
                            .addGroup(layout.createSequentialGroup()
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(bSearch)
                                    .addComponent(bConfirm, javax.swing.GroupLayout.PREFERRED_SIZE, 80, javax.swing.GroupLayout.PREFERRED_SIZE))
                                .addGap(50, 50, 50)
                                .addComponent(bReset, javax.swing.GroupLayout.PREFERRED_SIZE, 76, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(0, 0, Short.MAX_VALUE)))
                        .addGap(18, 18, 18))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(lKtp)
                            .addComponent(lDate)
                            .addComponent(lName)
                            .addComponent(lInvoice))
                        .addGap(82, 82, 82)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                            .addComponent(tfInvoice, javax.swing.GroupLayout.DEFAULT_SIZE, 86, Short.MAX_VALUE)
                            .addComponent(tfName, javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(tfKtp, javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(tfDate, javax.swing.GroupLayout.Alignment.LEADING))
                        .addGap(88, 88, 88)))
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap())
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(jTextField1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(bSearch)
                        .addGap(17, 17, 17)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(tfInvoice, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(lInvoice))
                        .addGap(18, 18, 18)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(tfName, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(lName))
                        .addGap(18, 18, 18)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(tfKtp, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(lKtp))
                        .addGap(18, 18, 18)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(tfDate, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(lDate))
                        .addGap(19, 19, 19)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(bConfirm)
                            .addComponent(bReset))))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void bResetActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_bResetActionPerformed
        // TODO add your handling code here:
        bersih();
    }//GEN-LAST:event_bResetActionPerformed

    private void bSearchActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_bSearchActionPerformed
        // TODO add your handling code here:
        String invoice = tfInvoice.getText();
        
        if (!invoice.isEmpty()) {
            try {
                record = controller.Search(invoice);
                
                if (!record.isEmpty()) {
                    fillTable();
                    
                } else {
                    JOptionPane.showMessageDialog(rootPane, Status.FAILED_SEARCH);
                }
                
            } catch (SQLException ex) {
                Logger.getLogger(FormUser.class.getName()).log(Level.SEVERE, null, ex);
            }
        } else {
            JOptionPane.showMessageDialog(rootPane, Status.NO_KEYWWORD);
        }
    }//GEN-LAST:event_bSearchActionPerformed

    private void bConfirmActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_bConfirmActionPerformed
        // TODO add your handling code here:
        String invoice = tfInvoice.getText();
        Pesanan pesanan = record.get(row);
        pesanan.setStatus("1");
        
        try {
            controller.Update(pesanan);
            addSaldo();
            addKas();
            JOptionPane.showMessageDialog(rootPane, Status.SUCCESS_CONFIRMED);
            bersih();
        } catch (SQLException ex) {
            Logger.getLogger(PesananConfirmation.class.getName()).log(Level.SEVERE, null, ex);
        }
    }//GEN-LAST:event_bConfirmActionPerformed

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
            java.util.logging.Logger.getLogger(PesananConfirmation.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(PesananConfirmation.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(PesananConfirmation.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(PesananConfirmation.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(() -> {
            new PesananConfirmation().setVisible(true);
        });
    }

    @Override
    public void loadData() {
        try {
            record = PesananController.getUnconfirmed();
        } catch (SQLException ex) {
            Logger.getLogger(PesananConfirmation.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    @Override
    public void fillTable() {
       Object object[][] = new Object[record.size()][4];
        int x = 0;
        for(Pesanan user:record) {
            object[x][0] = user.getNo_pesanan();
            object[x][1] = user.getPenyewa();
            object[x][2] = user.getKtp();
            object[x][3] = user.getTgl();
            x++;
        }
        String judul[] = {"Invoice", "Name", "KTP", "Date"};
        jTable1.setModel(new DefaultTableModel(object, judul));
        jScrollPane1.setViewportView(jTable1);
    }

    @Override
    public void setToTextField() {
        Pesanan pesanan = record.get(row);
        
        tfInvoice.setText(pesanan.getNo_pesanan());
        tfName.setText(pesanan.getPenyewa());
        tfKtp.setText(pesanan.getKtp());
        tfDate.setText(pesanan.getTgl());
    }

    @Override
    public final void initialitation() {
        makeNull();
        loadData();
        fillTable();
        bConfirm.setEnabled(false);
        bReset.setEnabled(false);
    }

    @Override
    public void makeNull() {
        tfName.setText("");
        tfDate.setText("");
        tfInvoice.setText("");
        tfKtp.setText("");
    }

    @Override
    public void bersih() {
        initialitation();
        bConfirm.setEnabled(false);
        bReset.setEnabled(false);
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton bConfirm;
    private javax.swing.JButton bReset;
    private javax.swing.JButton bSearch;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JTable jTable1;
    private javax.swing.JTextField jTextField1;
    private javax.swing.JLabel lDate;
    private javax.swing.JLabel lInvoice;
    private javax.swing.JLabel lKtp;
    private javax.swing.JLabel lName;
    private javax.swing.JTextField tfDate;
    private javax.swing.JTextField tfInvoice;
    private javax.swing.JTextField tfKtp;
    private javax.swing.JTextField tfName;
    // End of variables declaration//GEN-END:variables

    @Override
    public Map<String, String> assignToModel() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
    
    private void addSaldo() throws SQLException {
        String invoice = tfInvoice.getText();
        Saldo saldo = SaldoController.getDataByInvoice(invoice);
        saldo.setNominal(String.valueOf(saldo.getPercentage()));
        saldo.setTanggal(saldo.getDate());
        System.out.println(saldo.getTanggal());
        
        CrudInterface<Saldo> saldoController = new SaldoController();
        saldoController.Create(saldo);
    }
    
    private void addKas() throws SQLException {
        String invoice = tfInvoice.getText();
        Kas kas = KasController.getDataByInvoice(invoice);
        kas.setNominal(String.valueOf(kas.getPercentage()));
        kas.setTanggal(kas.getDate());
        
        CrudInterface kasController = new KasController();
        kasController.Create(kas);
    }
}
