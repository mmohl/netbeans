/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package views;

import controllers.PasanganController;
import helpers.Limiter;
import helpers.Status;
import interfaces.CrudInterface;
import interfaces.FormUtility;
import java.awt.event.KeyEvent;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Map;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JOptionPane;
import javax.swing.ListSelectionModel;
import javax.swing.event.ListSelectionEvent;
import javax.swing.table.DefaultTableModel;
import models.Pasangan;

/**
 *
 * @author mmohl
 */
public class FormPasangan extends javax.swing.JFrame implements FormUtility{
    java.util.List<Pasangan> record = new ArrayList<>();
    CrudInterface controller;
    int row = 0;
    int id = 0;
    String kategori;
    /**
     * Creates new form FormPasangan
     */
    public FormPasangan() {
        super("Form Pasangan");
        initComponents();
        controller = new PasanganController();
        
        tfHarga.setDocument(new Limiter((byte)7).getOnlyAngka(tfHarga));
                        
        liKategori.setModel(PasanganController.loadModel());
        liKategori.setSelectionMode(ListSelectionModel.MULTIPLE_INTERVAL_SELECTION);
        
        rbTersedia.setActionCommand("1");
        rbTidak.setActionCommand("0");
        
        initialitation();
        jTable1.getSelectionModel().addListSelectionListener((ListSelectionEvent e) -> {
            try {
                row = jTable1.getSelectedRow();
                
                if (row != -1) {
                    setToTextField();
                    int column = 0;
                    String key = jTable1.getModel().getValueAt(row, column).toString();
                    id = PasanganController.getIdUser(key);
                    
                    bSave.setEnabled(false);
                    bDelete.setEnabled(true);
                    bUpdate.setEnabled(true);
                    
                    Pasangan p = record.get(row);
                    kategori = p.getId_kategori();
                    String text = PasanganController.getAllCategories(p.getId_kategori());
                    String jml = PasanganController.sumCategoriesPrice(p.getId_kategori());
                    lTotalHarga.setText("Total Harga Kategori : Rp. " + jml);
                    lDetail.setText(text);
                }
                
            } catch (SQLException ex) {
                Logger.getLogger(FormPasangan.class.getName()).log(Level.SEVERE, null, ex);
            }
        });
        
        try {
            cbPegawai.setModel(PasanganController.loadPegawai());
        } catch (SQLException ex) {
            Logger.getLogger(FormPasangan.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        
        bDelete.setEnabled(false);
        bUpdate.setEnabled(false);
    }
    
        /**
     * Method custom atau buatan
     */
    @Override
    public void loadData() {
        try {
            record = controller.Read();
        } catch (SQLException e) {
            Logger.getLogger(FormUser.class.getName())
                    .log(Level.SEVERE, null, e);
        }
    }
        
    @Override
    public void fillTable() {
        Object object[][] = new Object[record.size()][3];
        int x = 0;
        for(Pasangan obj:record) {
            object[x][0] = obj.getNama();
            object[x][1] = obj.getHarga();
            object[x][2] = obj.getStatus();
            x++;
        }
        
        String judul[] = {"Name", "Price", "Status"};
        jTable1.setModel(new DefaultTableModel(object, judul));
        jScrollPane1.setViewportView(liKategori);
        jScrollPane2.setViewportView(jTable1);
    }
    
    @Override
    public void setToTextField() {
        Pasangan obj = record.get(row);
        
        cbPegawai.setSelectedItem(obj);
        tfHarga.setText(obj.getHarga());
        
        if (obj.getStatus().equals("1")) {
            rbTersedia.setSelected(true);
        } else {
            rbTidak.setSelected(true);
        }
        
        statusGrup.add(rbTersedia);
        statusGrup.add(rbTidak);
    }
    
    @Override
    public final void initialitation() {
        makeNull();
        loadData();
        fillTable();
    }
    
    @Override
    public void makeNull() {
        tfHarga.setText("");
    }
    
    @Override
    public void bersih() {
        initialitation();
        bSave.setEnabled(true);
        bDelete.setEnabled(false);
        bUpdate.setEnabled(false);
        lTotalHarga.setText("Total Harga Kategori :");
        lDetail.setText("-");
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        statusGrup = new javax.swing.ButtonGroup();
        lNama = new javax.swing.JLabel();
        lKategori = new javax.swing.JLabel();
        jScrollPane1 = new javax.swing.JScrollPane();
        liKategori = new javax.swing.JList<>();
        lHarga = new javax.swing.JLabel();
        tfHarga = new javax.swing.JTextField();
        rbTersedia = new javax.swing.JRadioButton();
        lStatus = new javax.swing.JLabel();
        rbTidak = new javax.swing.JRadioButton();
        bSave = new javax.swing.JButton();
        cbPegawai = new javax.swing.JComboBox<>();
        jScrollPane2 = new javax.swing.JScrollPane();
        jTable1 = new javax.swing.JTable();
        jSeparator1 = new javax.swing.JSeparator();
        bUpdate = new javax.swing.JButton();
        bDelete = new javax.swing.JButton();
        bReset = new javax.swing.JButton();
        jSeparator2 = new javax.swing.JSeparator();
        lKeyword = new javax.swing.JLabel();
        tfKeyword = new javax.swing.JTextField();
        bSearch = new javax.swing.JButton();
        jSeparator3 = new javax.swing.JSeparator();
        lKeterangan = new javax.swing.JLabel();
        lDetail = new javax.swing.JLabel();
        lTotalHarga = new javax.swing.JLabel();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        lNama.setText("Name");

        lKategori.setText("Categories");

        liKategori.setModel(new javax.swing.AbstractListModel<String>() {
            String[] strings = { "Item 1", "Item 2", "Item 3", "Item 4", "Item 5" };
            public int getSize() { return strings.length; }
            public String getElementAt(int i) { return strings[i]; }
        });
        jScrollPane1.setViewportView(liKategori);

        lHarga.setText("Price");

        tfHarga.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyTyped(java.awt.event.KeyEvent evt) {
                tfHargaKeyTyped(evt);
            }
        });

        statusGrup.add(rbTersedia);
        rbTersedia.setText("Tersedia");

        lStatus.setText("Status");

        statusGrup.add(rbTidak);
        rbTidak.setText("Tidak");

        bSave.setText("Save");
        bSave.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                bSaveActionPerformed(evt);
            }
        });

        cbPegawai.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Item 1", "Item 2", "Item 3", "Item 4" }));

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
        jScrollPane2.setViewportView(jTable1);

        bUpdate.setText("Update");
        bUpdate.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                bUpdateActionPerformed(evt);
            }
        });

        bDelete.setText("Delete");
        bDelete.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                bDeleteActionPerformed(evt);
            }
        });

        bReset.setText("Reset");
        bReset.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                bResetActionPerformed(evt);
            }
        });

        lKeyword.setText("Keyword");

        tfKeyword.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyTyped(java.awt.event.KeyEvent evt) {
                tfKeywordKeyTyped(evt);
            }
        });

        bSearch.setText("Search");
        bSearch.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                bSearchActionPerformed(evt);
            }
        });

        lKeterangan.setText("Keterangan :");

        lDetail.setText("-");

        lTotalHarga.setText("Total Harga Kategori :");

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jSeparator1)
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                        .addGap(0, 0, Short.MAX_VALUE)
                        .addComponent(jSeparator2, javax.swing.GroupLayout.PREFERRED_SIZE, 284, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(lHarga)
                            .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                .addGroup(layout.createSequentialGroup()
                                    .addComponent(bSave)
                                    .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                    .addComponent(bUpdate)
                                    .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                    .addComponent(bDelete)
                                    .addGap(18, 18, 18)
                                    .addComponent(bReset))
                                .addComponent(bSearch)
                                .addGroup(layout.createSequentialGroup()
                                    .addComponent(lKeyword)
                                    .addGap(18, 18, 18)
                                    .addComponent(tfKeyword))
                                .addGroup(layout.createSequentialGroup()
                                    .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                        .addComponent(lKategori)
                                        .addComponent(lNama)
                                        .addComponent(lStatus))
                                    .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                    .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                        .addGroup(layout.createSequentialGroup()
                                            .addComponent(rbTersedia)
                                            .addGap(18, 18, 18)
                                            .addComponent(rbTidak))
                                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                            .addComponent(cbPegawai, 0, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                            .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 240, javax.swing.GroupLayout.PREFERRED_SIZE)
                                            .addComponent(tfHarga))))))
                        .addGap(0, 61, Short.MAX_VALUE)))
                .addGap(18, 18, 18)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                        .addComponent(jScrollPane2, javax.swing.GroupLayout.DEFAULT_SIZE, 340, Short.MAX_VALUE)
                        .addComponent(jSeparator3))
                    .addComponent(lKeterangan)
                    .addComponent(lDetail)
                    .addComponent(lTotalHarga))
                .addContainerGap())
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(lNama)
                            .addComponent(cbPegawai, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(19, 19, 19)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(lKategori)
                            .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 93, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(18, 18, 18)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(lHarga)
                            .addComponent(tfHarga, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(18, 18, 18)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(lStatus)
                            .addComponent(rbTersedia)
                            .addComponent(rbTidak))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jSeparator1, javax.swing.GroupLayout.PREFERRED_SIZE, 10, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(bSave)
                            .addComponent(bUpdate)
                            .addComponent(bDelete)
                            .addComponent(bReset))
                        .addGap(18, 18, 18)
                        .addComponent(jSeparator2, javax.swing.GroupLayout.PREFERRED_SIZE, 10, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addComponent(jScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, 272, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(10, 10, 10)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(lKeyword)
                            .addComponent(tfKeyword, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(20, 20, 20)
                        .addComponent(bSearch))
                    .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                        .addGroup(layout.createSequentialGroup()
                            .addComponent(jSeparator3, javax.swing.GroupLayout.PREFERRED_SIZE, 10, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                            .addComponent(lKeterangan)
                            .addGap(18, 18, 18)
                            .addComponent(lDetail)
                            .addGap(35, 35, 35))
                        .addComponent(lTotalHarga)))
                .addContainerGap(86, Short.MAX_VALUE))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void bSaveActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_bSaveActionPerformed
        Pasangan p = new Pasangan();

        try {
            
            p.setId(null);
            p.setId_kategori(multiKategori());
            p.setId_pegawai(convertToId());
            p.setHarga(tfHarga.getText());
            p.setStatus(statusGrup.getSelection().getActionCommand());
            
            controller.Create(p);
            
            JOptionPane.showMessageDialog(rootPane, Status.SUCCESS_INSERT);
            bersih();
        
        } catch (SQLException ex) {
            Logger.getLogger(FormPasangan.class.getName()).log(Level.SEVERE, null, ex);
        }
        
    }//GEN-LAST:event_bSaveActionPerformed

    private void bResetActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_bResetActionPerformed
        // TODO add your handling code here:
        bersih();
        bSave.setEnabled(true);
    }//GEN-LAST:event_bResetActionPerformed

    private void bUpdateActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_bUpdateActionPerformed
        // TODO add your handling code here:
        String harga = tfHarga.getText();
        String status = statusGrup.getSelection().getActionCommand();
        String _id = String.valueOf(id);
        String name = jTable1.getModel().getValueAt(row, 0).toString();
        String id_pegawai = "";
        try {
            id_pegawai = convertToId(name);
        } catch (SQLException ex) {
            Logger.getLogger(FormPasangan.class.getName()).log(Level.SEVERE, null, ex);
        }
        int[] temp = liKategori.getSelectedIndices();
        
        if(temp.length != 0) {
            kategori = multiKategori();
        }
        
        Pasangan obj = new Pasangan();
        obj.setId(_id);
        obj.setHarga(harga);
        obj.setId_kategori(kategori);
        obj.setStatus(status);
        obj.setId_pegawai(id_pegawai);
        
        try {
            controller.Update(obj);
            JOptionPane.showMessageDialog(rootPane, Status.SUCCESS_UPDATE);
            bersih();
            bSave.setEnabled(true);
        } catch (SQLException ex) {
            Logger.getLogger(FormPasangan.class.getName()).log(Level.SEVERE, null, ex);
        }
    }//GEN-LAST:event_bUpdateActionPerformed

    private void bDeleteActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_bDeleteActionPerformed
        // TODO add your handling code here:
        String _id = String.valueOf(id);
        try {
            int respon = JOptionPane.showConfirmDialog(rootPane, Status.MESSAGE_DELETE, Status.TITLE_DELETE, JOptionPane.YES_NO_CANCEL_OPTION);
            
            if (respon == JOptionPane.YES_OPTION) {
                controller.Delete(_id);
                JOptionPane.showMessageDialog(rootPane, Status.SUCCESS_DELETE);
                bersih();
            }
            
        } catch (SQLException ex) {
            Logger.getLogger(FormPasangan.class.getName()).log(Level.SEVERE, null, ex);
        }
        
    }//GEN-LAST:event_bDeleteActionPerformed

    private void bSearchActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_bSearchActionPerformed
        // TODO add your handling code here:
        String key = tfKeyword.getText();
        
        try {
            if (!key.isEmpty()) {
                record = controller.Search(key);
                
                if (!record.isEmpty()) {
                    fillTable();
                } else {
                    JOptionPane.showMessageDialog(rootPane, Status.FAILED_SEARCH);
                }
                
            } else {
                JOptionPane.showMessageDialog(rootPane, Status.NO_KEYWWORD);
            }
            
        } catch (SQLException ex) {
            Logger.getLogger(FormKategori.class.getName()).log(Level.SEVERE, null, ex);
        }
    }//GEN-LAST:event_bSearchActionPerformed

    private void tfHargaKeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_tfHargaKeyTyped
        // TODO add your handling code here:
        validasiAngka(evt);
    }//GEN-LAST:event_tfHargaKeyTyped

    private void tfKeywordKeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_tfKeywordKeyTyped
        // TODO add your handling code here:
        validasiHuruf(evt);
    }//GEN-LAST:event_tfKeywordKeyTyped
    
    private String convertToId() throws SQLException {
        int id = 0;
        Map<String, Integer> resource = null;
         
        resource = PasanganController.loadPegawaiId();
        id = resource.get(cbPegawai.getSelectedItem().toString());
 
        return String.valueOf(id);
    }
    
    private String convertToId(String name) throws SQLException {
        int id = 0;
        Map<String, Integer> resource = null;
         
        resource = PasanganController.loadPegawaiId();
        id = resource.get(name);
 
        return String.valueOf(id);
    }
    
    private String multiKategori() {
        int[] k = liKategori.getSelectedIndices();
        String id = "";
        Map<String, Integer> _id = PasanganController.loadModelId();
        
        for (int i = 0; i < k.length; i++) {
            
              if (!id.isEmpty()) {
                  id += "-";
              }
              
              if ( _id.containsKey(liKategori.getModel().getElementAt(k[i])) ) {
                  id += String.valueOf( _id.get(liKategori.getModel().getElementAt(k[i])) );
              }
        }
        return id;
    }
    
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
            java.util.logging.Logger.getLogger(FormPasangan.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(FormPasangan.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(FormPasangan.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(FormPasangan.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(() -> {
            new FormPasangan().setVisible(true);
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton bDelete;
    private javax.swing.JButton bReset;
    private javax.swing.JButton bSave;
    private javax.swing.JButton bSearch;
    private javax.swing.JButton bUpdate;
    private javax.swing.JComboBox<String> cbPegawai;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JSeparator jSeparator1;
    private javax.swing.JSeparator jSeparator2;
    private javax.swing.JSeparator jSeparator3;
    private javax.swing.JTable jTable1;
    private javax.swing.JLabel lDetail;
    private javax.swing.JLabel lHarga;
    private javax.swing.JLabel lKategori;
    private javax.swing.JLabel lKeterangan;
    private javax.swing.JLabel lKeyword;
    private javax.swing.JLabel lNama;
    private javax.swing.JLabel lStatus;
    private javax.swing.JLabel lTotalHarga;
    private javax.swing.JList<String> liKategori;
    private javax.swing.JRadioButton rbTersedia;
    private javax.swing.JRadioButton rbTidak;
    private javax.swing.ButtonGroup statusGrup;
    private javax.swing.JTextField tfHarga;
    private javax.swing.JTextField tfKeyword;
    // End of variables declaration//GEN-END:variables

    @Override
    public Map<String, String> assignToModel() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
}
