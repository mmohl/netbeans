/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package views;

import controllers.PegawaiController;
import helpers.KTPValidator;
import helpers.Limiter;
import helpers.Status;
import interfaces.CrudInterface;
import interfaces.FormUtility;
import java.awt.event.KeyEvent;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.sql.SQLException;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.Locale;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.imageio.ImageIO;
import javax.swing.JFileChooser;
import javax.swing.JOptionPane;
import javax.swing.event.ListSelectionEvent;
import javax.swing.filechooser.FileNameExtensionFilter;
import javax.swing.table.DefaultTableModel;
import models.Pegawai;

/**
 *
 * @author mmohl
 */
public class FormPegawai extends javax.swing.JFrame implements FormUtility{
    SimpleDateFormat format = new SimpleDateFormat("yyyy-M-dd", Locale.US);
    Calendar cal = Calendar.getInstance();
    Calendar cPicker;
    Date lowerBound, upperBound;
    CrudInterface<Pegawai> controller;
    List<Pegawai> record = new ArrayList<>();
    JFileChooser fc;
    String gambar;
    String path = System.getProperty("user.dir") + "/src/images/";
    File file;
    int row = 0;
    int val = 0;
    private int id = 0;
    /**
     * Creates new form FormPegawai
     */
    public FormPegawai() {
        super("Form Pegawai");
        initComponents();
        
        tfKtp.setDocument(new Limiter((byte)16).getOnlyAngka(tfKtp));
        tfNama.setDocument(new Limiter((byte) 50).getKata(tfNama));
        tfNohape.setDocument(new Limiter((byte) 12).getOnlyAngka(tfNohape));
        
        rbPria.setActionCommand("p");
        rbWanita.setActionCommand("w");
        setTanggal();
        controller = new PegawaiController();
        fc = new JFileChooser();
        fc.setFileSelectionMode(JFileChooser.FILES_ONLY);
        
        jTable1.getSelectionModel().addListSelectionListener((ListSelectionEvent e) -> {
            row = jTable1.getSelectedRow();
            
            if (row != -1) {
                try {
                    setToTextField();
                    id = PegawaiController.getIdUser(tfNama.getText());
                    bSave.setEnabled(false);
                    bDelete.setEnabled(true);
                    bUpdate.setEnabled(true);
                    
                } catch (SQLException ex) {
                    Logger.getLogger(FormPegawai.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
        });
        
        initialitation();
        bDelete.setEnabled(false);
        bUpdate.setEnabled(false);
    }
    
    private void setTanggal() {
        dpTanggal.setFormats(format);
        
        
        lowerBound = new Date(Calendar.YEAR - 17, 1, 1);
        upperBound = new Date(Calendar.YEAR- 30, 12, 1);
//        dpTanggal.setDate(new Date(Calendar.YEAR, 1, 1));
//        dpTanggal.getMonthView().setLowerBound(lowerBound);
//        dpTanggal.getMonthView().setUpperBound(upperBound);
    }
    
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
        Object object[][] = new Object[record.size()][5];
        int x = 0;
        
        for(Pegawai obj:record) {
            object[x][0] = obj.getNama();
            object[x][1] = obj.getGender();
            object[x][2] = obj.getTanggal_lahir();
            object[x][3] = obj.getKtp();
            object[x][4] = obj.getNo_handphone();
            x++;
        }
        
        String judul[] = {"Name", "Gender", "Birthdate", "Ktp", "Phone"};
        jTable1.setModel(new DefaultTableModel(object, judul));
        jScrollPane1.setViewportView(jTable1);
    }
    
    @Override
    public void setToTextField() {
        Pegawai obj = record.get(row);
        DateFormat format;
        format = new SimpleDateFormat("yyyy-mm-dd");
        Date tgl = null;
        try {
            tgl = format.parse(obj.getTanggal_lahir());
        } catch (ParseException ex) {
            Logger.getLogger(FormPegawai.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        gambar = obj.getFoto();
        lPictureName.setText(obj.getFoto());
        tfNama.setText(obj.getNama());
        dpTanggal.setDate(tgl);
        tfKtp.setText(obj.getKtp());
        tfNohape.setText(obj.getNo_handphone());
        
        if (obj.getGender().equals("p")) {
            rbPria.setSelected(true);
        } else {
            rbWanita.setSelected(true);
        }
        
        jenisKelaminGrup.add(rbPria);
        jenisKelaminGrup.add(rbWanita);
        
    }
    
    @Override
    public final void initialitation() {
        makeNull();
        loadData();
        fillTable();
    }
    
    @Override
    public void makeNull() {
        tfNama.setText("");
        tfNohape.setText("");
        tfKtp.setText("");
        jenisKelaminGrup.clearSelection();
    }
    
    @Override
    public void bersih() {
        initialitation();
        bSave.setEnabled(true);
        bDelete.setEnabled(false);
        bUpdate.setEnabled(false);
        lPictureName.setText("");
        fc.cancelSelection();
    }
    

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jenisKelaminGrup = new javax.swing.ButtonGroup();
        lNama = new javax.swing.JLabel();
        tfNama = new javax.swing.JTextField();
        lJenisKelamin = new javax.swing.JLabel();
        rbPria = new javax.swing.JRadioButton();
        rbWanita = new javax.swing.JRadioButton();
        lTanggalLahir = new javax.swing.JLabel();
        dpTanggal = new org.jdesktop.swingx.JXDatePicker();
        jLabel1 = new javax.swing.JLabel();
        tfKtp = new javax.swing.JTextField();
        jLabel2 = new javax.swing.JLabel();
        tfNohape = new javax.swing.JTextField();
        bSave = new javax.swing.JButton();
        jScrollPane1 = new javax.swing.JScrollPane();
        jTable1 = new javax.swing.JTable();
        jSeparator1 = new javax.swing.JSeparator();
        bUpdate = new javax.swing.JButton();
        bDelete = new javax.swing.JButton();
        bReset = new javax.swing.JButton();
        jSeparator2 = new javax.swing.JSeparator();
        lKeyword = new javax.swing.JLabel();
        tfKeyword = new javax.swing.JTextField();
        bSearch = new javax.swing.JButton();
        bBrowse = new javax.swing.JButton();
        lGambar = new javax.swing.JLabel();
        lPictureName = new javax.swing.JLabel();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        lNama.setText("Nama");

        tfNama.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyTyped(java.awt.event.KeyEvent evt) {
                tfNamaKeyTyped(evt);
            }
        });

        lJenisKelamin.setText("Jenis Kelamin");

        jenisKelaminGrup.add(rbPria);
        rbPria.setText("Pria");

        jenisKelaminGrup.add(rbWanita);
        rbWanita.setText("Wanita");

        lTanggalLahir.setText("Tanggal Lahir");

        jLabel1.setText("No. Ktp");

        tfKtp.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyTyped(java.awt.event.KeyEvent evt) {
                tfKtpKeyTyped(evt);
            }
        });

        jLabel2.setText("No. Handphone");

        tfNohape.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyTyped(java.awt.event.KeyEvent evt) {
                tfNohapeKeyTyped(evt);
            }
        });

        bSave.setText("Save");
        bSave.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                bSaveActionPerformed(evt);
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

        bBrowse.setText("Browse");
        bBrowse.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                bBrowseActionPerformed(evt);
            }
        });

        lGambar.setText("Gambar");

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jSeparator2, javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(jSeparator1)
                    .addGroup(layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(lTanggalLahir)
                            .addComponent(jLabel1)
                            .addComponent(lJenisKelamin)
                            .addComponent(lNama))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addGroup(layout.createSequentialGroup()
                                .addComponent(rbPria)
                                .addGap(18, 18, 18)
                                .addComponent(rbWanita))
                            .addComponent(tfNama)
                            .addComponent(dpTanggal, javax.swing.GroupLayout.DEFAULT_SIZE, 266, Short.MAX_VALUE)
                            .addComponent(tfKtp)))
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(lKeyword)
                        .addGap(42, 42, 42)
                        .addComponent(tfKeyword))
                    .addGroup(layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(layout.createSequentialGroup()
                                .addComponent(bSave)
                                .addGap(18, 18, 18)
                                .addComponent(bUpdate)
                                .addGap(18, 18, 18)
                                .addComponent(bDelete)
                                .addGap(18, 18, 18)
                                .addComponent(bReset))
                            .addComponent(bSearch))
                        .addGap(0, 100, Short.MAX_VALUE))
                    .addGroup(layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel2)
                            .addComponent(lGambar))
                        .addGap(18, 18, 18)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(tfNohape)
                            .addGroup(layout.createSequentialGroup()
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(lPictureName)
                                    .addComponent(bBrowse))
                                .addGap(0, 0, Short.MAX_VALUE)))))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 332, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap())
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(26, 26, 26)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 473, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addContainerGap(19, Short.MAX_VALUE))
                    .addGroup(layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(lNama)
                            .addComponent(tfNama, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(lJenisKelamin)
                            .addComponent(rbPria)
                            .addComponent(rbWanita))
                        .addGap(15, 15, 15)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(dpTanggal, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(lTanggalLahir))
                        .addGap(18, 18, 18)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel1)
                            .addComponent(tfKtp, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(18, 18, 18)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel2)
                            .addComponent(tfNohape, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(18, 18, 18)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(bBrowse)
                            .addComponent(lGambar))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(lPictureName)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jSeparator1, javax.swing.GroupLayout.PREFERRED_SIZE, 10, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(bSave)
                            .addComponent(bUpdate)
                            .addComponent(bDelete)
                            .addComponent(bReset))
                        .addGap(21, 21, 21)
                        .addComponent(jSeparator2, javax.swing.GroupLayout.PREFERRED_SIZE, 10, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(lKeyword)
                            .addComponent(tfKeyword, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(bSearch)
                        .addGap(71, 71, 71))))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void bSaveActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_bSaveActionPerformed
//        // TODO add your handling code here:
        Pegawai pegawai = new Pegawai();
          
          file = fc.getSelectedFile();
          pegawai.setGender(jenisKelaminGrup.getSelection().getActionCommand());
          pegawai.setKtp(tfKtp.getText());
          pegawai.setNama(tfNama.getText());
          pegawai.setNo_handphone(tfNohape.getText());
          String tanggal = dpTanggal.getDate().getYear()+1900 + "-" + (dpTanggal.getDate().getMonth() + 1) + "-" +dpTanggal.getDate().getDate();
          pegawai.setTanggal_lahir(tanggal);
          pegawai.setFoto( saveAndGetNameImage(file) );
          boolean isValid = new KTPValidator(pegawai.getKtp()).validate();
          
          JOptionPane.showMessageDialog(rootPane, isValid);
            try {
                if (isValid != true) {
                    controller.Create(pegawai);
                    JOptionPane.showMessageDialog(rootPane, Status.SUCCESS_INSERT);
                    bersih();
                } else {
                    JOptionPane.showMessageDialog(rootPane, "KTP sudah terdaftar");
                }
                
            } catch (SQLException ex) {
                Logger.getLogger(FormPegawai.class.getName()).log(Level.SEVERE, null, ex);
            }

    }//GEN-LAST:event_bSaveActionPerformed

    private void bResetActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_bResetActionPerformed
        // TODO add your handling code here:
        bersih();
        bSave.setEnabled(true);
        jenisKelaminGrup.clearSelection();
    }//GEN-LAST:event_bResetActionPerformed

    private void bUpdateActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_bUpdateActionPerformed
        // TODO add your handling code here:
        file = fc.getSelectedFile();
        Pegawai obj = new Pegawai();
        String nama = tfNama.getText();
        String ktp = tfKtp.getText();
        String nohape = tfNohape.getText();
        String gender = jenisKelaminGrup.getSelection().getActionCommand();
        String tanggal = dpTanggal.getDate().getYear()+1900 + "-" + (dpTanggal.getDate().getMonth() + 1) + "-" +dpTanggal.getDate().getDate();
        String _id = String.valueOf(id);
        
        obj.setId(_id);
        obj.setNama(nama);
        obj.setKtp(ktp);
        obj.setNo_handphone(nohape);
        obj.setGender(gender);
        obj.setTanggal_lahir(tanggal);
        
        if (file != null) {
            file = new File(path + gambar);
            file.delete();
            file = fc.getSelectedFile();
   
            obj.setFoto( saveAndGetNameImage(file) );
        } else {
            obj.setFoto(gambar);
        }
        
        
        try {
            controller.Update(obj);
            JOptionPane.showMessageDialog(rootPane, Status.SUCCESS_UPDATE);
            bersih();
//        JOptionPane.showMessageDialog(rootPane, id);
        } catch (SQLException ex) {
            Logger.getLogger(FormPegawai.class.getName()).log(Level.SEVERE, null, ex);
        }
    }//GEN-LAST:event_bUpdateActionPerformed

    private void bDeleteActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_bDeleteActionPerformed
        // TODO add your handling code here:
        String _id = String.valueOf(id);
        file = new File(path + gambar);
        
        try {
            int respon = JOptionPane.showConfirmDialog(rootPane, Status.MESSAGE_DELETE, 
                    Status.TITLE_DELETE, JOptionPane.YES_NO_CANCEL_OPTION);
            if (respon == JOptionPane.YES_OPTION) {
                controller.Delete(_id);
                JOptionPane.showMessageDialog(rootPane, Status.SUCCESS_DELETE);
                file.delete();
                bersih();
            }
            
        } catch (SQLException ex) {
            Logger.getLogger(FormPegawai.class.getName()).log(Level.SEVERE, null, ex);
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
                    JOptionPane.showMessageDialog(rootPane, "Data was not found");
                }
                
            } else {
                JOptionPane.showMessageDialog(rootPane, "Keyword was not defined");
            }
            
        } catch (SQLException ex) {
            Logger.getLogger(FormKategori.class.getName()).log(Level.SEVERE, null, ex);
        }
    }//GEN-LAST:event_bSearchActionPerformed
   
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
    
    private String saveAndGetNameImage(File file) {
        String fullname = null;
        
        if (val == JFileChooser.APPROVE_OPTION) {
            String name = tfNama.getText();
            String eks = file.getName().substring(file.getName().length() - 3, file.getName().length());
            fullname = name + "." + eks;
            BufferedImage bufferedImage;
            
                        
            try {
                bufferedImage = ImageIO.read(file);
                ImageIO.write(bufferedImage, "jpg", new File(path + fullname));
                
            } catch (IOException ex) {
                Logger.getLogger(FormPegawai.class.getName()).log(Level.SEVERE, null, ex);
            }
            
        }
        
        return fullname;
    }
    
    private void tfNamaKeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_tfNamaKeyTyped
        // TODO add your handling code here:
        validasiHuruf(evt);
    }//GEN-LAST:event_tfNamaKeyTyped

    private void tfKtpKeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_tfKtpKeyTyped
        // TODO add your handling code here:
        validasiAngka(evt);
    }//GEN-LAST:event_tfKtpKeyTyped

    private void tfNohapeKeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_tfNohapeKeyTyped
        // TODO add your handling code here:
        validasiAngka(evt);
    }//GEN-LAST:event_tfNohapeKeyTyped

    private void tfKeywordKeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_tfKeywordKeyTyped
        // TODO add your handling code here:
        validasiHuruf(evt);
    }//GEN-LAST:event_tfKeywordKeyTyped

    private void bBrowseActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_bBrowseActionPerformed
        // TODO add your handling code here:
        FileNameExtensionFilter filter = 
                new FileNameExtensionFilter("Only JPG, PNG and BMP", "jpg", "png", "bmp");
        fc.setFileFilter(filter);
        val = fc.showOpenDialog(this);
        file = fc.getSelectedFile();
        lPictureName.setText(file.getName());
    }//GEN-LAST:event_bBrowseActionPerformed

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
            java.util.logging.Logger.getLogger(FormPegawai.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(FormPegawai.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(FormPegawai.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(FormPegawai.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new FormPegawai().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton bBrowse;
    private javax.swing.JButton bDelete;
    private javax.swing.JButton bReset;
    private javax.swing.JButton bSave;
    private javax.swing.JButton bSearch;
    private javax.swing.JButton bUpdate;
    private org.jdesktop.swingx.JXDatePicker dpTanggal;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JSeparator jSeparator1;
    private javax.swing.JSeparator jSeparator2;
    private javax.swing.JTable jTable1;
    private javax.swing.ButtonGroup jenisKelaminGrup;
    private javax.swing.JLabel lGambar;
    private javax.swing.JLabel lJenisKelamin;
    private javax.swing.JLabel lKeyword;
    private javax.swing.JLabel lNama;
    private javax.swing.JLabel lPictureName;
    private javax.swing.JLabel lTanggalLahir;
    private javax.swing.JRadioButton rbPria;
    private javax.swing.JRadioButton rbWanita;
    private javax.swing.JTextField tfKeyword;
    private javax.swing.JTextField tfKtp;
    private javax.swing.JTextField tfNama;
    private javax.swing.JTextField tfNohape;
    // End of variables declaration//GEN-END:variables
}
