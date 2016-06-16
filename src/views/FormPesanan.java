/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package views;

import controllers.DetailPesananController;
import controllers.KonsumenController;
import controllers.PasanganController;
import controllers.PesananController;
import helpers.Limiter;
import helpers.Status;
import interfaces.CrudInterface;
import java.awt.HeadlessException;
import java.awt.event.ItemEvent;
import java.awt.event.KeyEvent;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.ButtonGroup;
import javax.swing.DefaultComboBoxModel;
import javax.swing.DefaultListModel;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.JOptionPane;
import javax.swing.JProgressBar;
import javax.swing.JRadioButton;
import javax.swing.JScrollPane;
import javax.swing.JSeparator;
import javax.swing.JTextField;
import javax.swing.ListSelectionModel;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ListSelectionEvent;
import models.DetailPesanan;
import models.Konsumen;
import models.Pesanan;
import org.jdesktop.swingx.JXDatePicker;

/**
 *
 * @author mmohl
 */
public class FormPesanan extends javax.swing.JFrame {
    CrudInterface<Pesanan> controller;
    List<Konsumen> record = new ArrayList<>();
    int row = 0;
    int id = 0;

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
        super("Form Pemesanan");
        initComponents();
        controller = new PesananController();
        lbKategori.setSelectionMode(ListSelectionModel.MULTIPLE_INTERVAL_SELECTION);
        lbPasangan.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
        
        tfKtp.setDocument(new Limiter((byte) 16).getOnlyAngka(tfKtp));
        tfLama.setDocument(new Limiter((byte) 2).getKata(tfLama));
        tfNohape.setDocument(new Limiter((byte) 12).getOnlyAngka(tfNohape));
        tfPenyewa.setDocument(new Limiter((byte) 50).getKata(tfPenyewa));
        tfUangMuka.setDocument(new Limiter((byte) 8).getOnlyAngka(tfUangMuka));
        
        rbPria.setActionCommand("p");
        rbWanita.setActionCommand("w");
        rbPePria.setActionCommand("p");
        rbPeWanita.setActionCommand("w");
        cbKonsumen.setEnabled(false);
        cbYa.setSelected(true);

        lbPasangan.setModel(loadListPasangan());
        lbKategori.setModel(loadListKategori());
       
        tfUangMuka.setEnabled(false);
        rbDebit.setActionCommand("0");
        rbKredit.setActionCommand("1");
        tfHarga.setEditable(false);
        
        lbPasangan.addListSelectionListener((ListSelectionEvent e) -> {
            String name1;
            int id1;
            String harga;
            if (e.getValueIsAdjusting()) {
                try {
                    name1 = lbPasangan.getSelectedValue();
                    id1 = PesananController.getIdPegawai(name1);
                    harga = PesananController.getTotalPrice(id1);
                    tfHarga.setText(harga);
                    
                    String imageName = PesananController.getPicture(id1);
                    String imagePath = "/images/";
                    ImageIcon icon = new ImageIcon(getClass().getResource(imagePath + imageName));
                    lPicture.setIcon(icon);
                    lPicture.setText("");
                }catch (SQLException ex) {
                    Logger.getLogger(FormPesanan.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
        });
        
        cbKonsumen.addItemListener((ItemEvent e) -> {
            row = cbKonsumen.getSelectedIndex();
            Konsumen konsumen = record.get(row);
            id = Integer.parseInt(konsumen.getId());
            SetToTextField(konsumen);
        });
        
        loadResource();
        setProgress();
//        bSave.setEnabled(false);
        
    }
    
    private void setProgress() {
        pbForm = new JProgressBar();
        pbForm.addChangeListener((ChangeEvent e) -> {
            int progress = pbForm.getValue();
            
            if (progress == 100) {
                bSave.setEnabled(true);
            } else {
                bSave.setEnabled(false);
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
        genderGroup = new javax.swing.ButtonGroup();
        PeJeKeGrup = new javax.swing.ButtonGroup();
        checkingGroup = new javax.swing.ButtonGroup();
        bSave = new javax.swing.JButton();
        jTabbedPane1 = new javax.swing.JTabbedPane();
        jPanel1 = new javax.swing.JPanel();
        lPenyewa = new javax.swing.JLabel();
        lKtp = new javax.swing.JLabel();
        tfPenyewa = new javax.swing.JTextField();
        tfKtp = new javax.swing.JTextField();
        lNohape = new javax.swing.JLabel();
        tfNohape = new javax.swing.JTextField();
        cbYa = new javax.swing.JRadioButton();
        cbTidak = new javax.swing.JRadioButton();
        cbKonsumen = new javax.swing.JComboBox<>();
        rbPePria = new javax.swing.JRadioButton();
        rbPeWanita = new javax.swing.JRadioButton();
        jScrollPane3 = new javax.swing.JScrollPane();
        taAlamat = new javax.swing.JTextArea();
        jLabel4 = new javax.swing.JLabel();
        lGender = new javax.swing.JLabel();
        jPanel2 = new javax.swing.JPanel();
        lPasangan = new javax.swing.JLabel();
        jScrollPane2 = new javax.swing.JScrollPane();
        lbPasangan = new javax.swing.JList<>();
        lHarga = new javax.swing.JLabel();
        tfHarga = new javax.swing.JTextField();
        lPicture = new javax.swing.JLabel();
        jLabel3 = new javax.swing.JLabel();
        jLabel2 = new javax.swing.JLabel();
        jScrollPane1 = new javax.swing.JScrollPane();
        lbKategori = new javax.swing.JList<>();
        rbPria = new javax.swing.JRadioButton();
        rbWanita = new javax.swing.JRadioButton();
        bSearch = new javax.swing.JButton();
        bReset = new javax.swing.JButton();
        lTanggal = new javax.swing.JLabel();
        dpTanggal = new org.jdesktop.swingx.JXDatePicker();
        jPanel3 = new javax.swing.JPanel();
        tfLama = new javax.swing.JTextField();
        lSewa = new javax.swing.JLabel();
        jLabel1 = new javax.swing.JLabel();
        rbDebit = new javax.swing.JRadioButton();
        rbKredit = new javax.swing.JRadioButton();
        lUangMuka = new javax.swing.JLabel();
        tfUangMuka = new javax.swing.JTextField();
        pbForm = new javax.swing.JProgressBar();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        bSave.setText("Save");
        bSave.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                bSaveActionPerformed(evt);
            }
        });

        lPenyewa.setText("Penyewa");

        lKtp.setText("KTP Number");

        tfPenyewa.addFocusListener(new java.awt.event.FocusAdapter() {
            public void focusLost(java.awt.event.FocusEvent evt) {
                tfPenyewaFocusLost(evt);
            }
        });
        tfPenyewa.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyTyped(java.awt.event.KeyEvent evt) {
                tfPenyewaKeyTyped(evt);
            }
        });

        tfKtp.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyTyped(java.awt.event.KeyEvent evt) {
                tfKtpKeyTyped(evt);
            }
        });

        lNohape.setText("Phone Number");

        tfNohape.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyTyped(java.awt.event.KeyEvent evt) {
                tfNohapeKeyTyped(evt);
            }
        });

        checkingGroup.add(cbYa);
        cbYa.setText("Ya");
        cbYa.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                cbYaActionPerformed(evt);
            }
        });

        checkingGroup.add(cbTidak);
        cbTidak.setText("Tidak");
        cbTidak.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                cbTidakActionPerformed(evt);
            }
        });

        cbKonsumen.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Item 1", "Item 2", "Item 3", "Item 4" }));

        PeJeKeGrup.add(rbPePria);
        rbPePria.setText("Male");

        PeJeKeGrup.add(rbPeWanita);
        rbPeWanita.setText("Female");

        taAlamat.setColumns(20);
        taAlamat.setRows(5);
        jScrollPane3.setViewportView(taAlamat);

        jLabel4.setText("Address");

        lGender.setText("Gender");

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(lNohape)
                    .addComponent(lKtp)
                    .addComponent(lPenyewa)
                    .addComponent(jLabel4)
                    .addComponent(lGender))
                .addGap(18, 18, 18)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addComponent(cbYa)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(cbTidak))
                    .addComponent(cbKonsumen, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                        .addGroup(jPanel1Layout.createSequentialGroup()
                            .addComponent(rbPePria)
                            .addGap(112, 112, 112)
                            .addComponent(rbPeWanita))
                        .addComponent(tfPenyewa)
                        .addComponent(tfKtp)
                        .addComponent(tfNohape)
                        .addComponent(jScrollPane3, javax.swing.GroupLayout.DEFAULT_SIZE, 298, Short.MAX_VALUE)))
                .addContainerGap(90, Short.MAX_VALUE))
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                .addGap(14, 14, 14)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(cbYa)
                    .addComponent(cbTidak, javax.swing.GroupLayout.Alignment.TRAILING))
                .addGap(18, 18, 18)
                .addComponent(cbKonsumen, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(lPenyewa)
                    .addComponent(tfPenyewa, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(lKtp)
                    .addComponent(tfKtp, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(lNohape)
                    .addComponent(tfNohape, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(rbPePria)
                    .addComponent(rbPeWanita)
                    .addComponent(lGender))
                .addGap(18, 18, 18)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jScrollPane3, javax.swing.GroupLayout.PREFERRED_SIZE, 140, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel4))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        jTabbedPane1.addTab("Consument Identity", jPanel1);

        lPasangan.setText("Pasangan");

        lbPasangan.setModel(new javax.swing.AbstractListModel<String>() {
            String[] strings = { "Item 1", "Item 2", "Item 3", "Item 4", "Item 5" };
            public int getSize() { return strings.length; }
            public String getElementAt(int i) { return strings[i]; }
        });
        jScrollPane2.setViewportView(lbPasangan);

        lHarga.setText("Harga");

        lPicture.setText("image");

        jLabel3.setText("Categories");

        jLabel2.setText("Filter");

        lbKategori.setModel(new javax.swing.AbstractListModel<String>() {
            String[] strings = { "Item 1", "Item 2", "Item 3", "Item 4", "Item 5" };
            public int getSize() { return strings.length; }
            public String getElementAt(int i) { return strings[i]; }
        });
        jScrollPane1.setViewportView(lbKategori);

        genderGroup.add(rbPria);
        rbPria.setText("Pria");

        genderGroup.add(rbWanita);
        rbWanita.setText("Wanita");

        bSearch.setText("Search");
        bSearch.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                bSearchActionPerformed(evt);
            }
        });

        bReset.setText("Reset");
        bReset.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                bResetActionPerformed(evt);
            }
        });

        lTanggal.setText("Tanggal Acara");

        javax.swing.GroupLayout jPanel2Layout = new javax.swing.GroupLayout(jPanel2);
        jPanel2.setLayout(jPanel2Layout);
        jPanel2Layout.setHorizontalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addGap(127, 127, 127)
                        .addComponent(tfHarga))
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(lPasangan)
                            .addGroup(jPanel2Layout.createSequentialGroup()
                                .addGap(127, 127, 127)
                                .addComponent(jScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, 131, javax.swing.GroupLayout.PREFERRED_SIZE)))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(lPicture, javax.swing.GroupLayout.PREFERRED_SIZE, 150, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(jPanel2Layout.createSequentialGroup()
                                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                    .addGroup(jPanel2Layout.createSequentialGroup()
                                        .addComponent(bSearch)
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                        .addComponent(bReset))
                                    .addComponent(jLabel2)
                                    .addComponent(jLabel3)
                                    .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 148, javax.swing.GroupLayout.PREFERRED_SIZE))
                                .addGap(44, 44, 44)
                                .addComponent(rbPria)
                                .addGap(18, 18, 18)
                                .addComponent(rbWanita))
                            .addComponent(lHarga)
                            .addGroup(jPanel2Layout.createSequentialGroup()
                                .addComponent(lTanggal)
                                .addGap(29, 29, 29)
                                .addComponent(dpTanggal, javax.swing.GroupLayout.PREFERRED_SIZE, 326, javax.swing.GroupLayout.PREFERRED_SIZE)))
                        .addGap(0, 46, Short.MAX_VALUE)))
                .addContainerGap())
        );
        jPanel2Layout.setVerticalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addGap(30, 30, 30)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(jPanel2Layout.createSequentialGroup()
                                .addComponent(lPasangan)
                                .addGap(0, 0, Short.MAX_VALUE))
                            .addComponent(jScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, 0, Short.MAX_VALUE))
                        .addGap(42, 42, 42))
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addComponent(lPicture, javax.swing.GroupLayout.PREFERRED_SIZE, 210, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)))
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(lHarga)
                    .addComponent(tfHarga, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jLabel2)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jLabel3)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(lTanggal)
                    .addComponent(dpTanggal, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(20, 20, 20)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(rbPria)
                        .addComponent(rbWanita))
                    .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 140, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(bSearch)
                    .addComponent(bReset))
                .addContainerGap())
        );

        jTabbedPane1.addTab("Couple", jPanel2);

        tfLama.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyTyped(java.awt.event.KeyEvent evt) {
                tfLamaKeyTyped(evt);
            }
        });

        lSewa.setText("Lama Sewa");

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

        lUangMuka.setText("Uang Muka");

        tfUangMuka.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyTyped(java.awt.event.KeyEvent evt) {
                tfUangMukaKeyTyped(evt);
            }
        });

        javax.swing.GroupLayout jPanel3Layout = new javax.swing.GroupLayout(jPanel3);
        jPanel3.setLayout(jPanel3Layout);
        jPanel3Layout.setHorizontalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel3Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel3Layout.createSequentialGroup()
                        .addComponent(lSewa)
                        .addGap(49, 49, 49)
                        .addComponent(tfLama, javax.swing.GroupLayout.PREFERRED_SIZE, 326, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(jPanel3Layout.createSequentialGroup()
                        .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(lUangMuka)
                            .addComponent(jLabel1))
                        .addGap(41, 41, 41)
                        .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(tfUangMuka, javax.swing.GroupLayout.PREFERRED_SIZE, 212, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addGroup(jPanel3Layout.createSequentialGroup()
                                .addComponent(rbDebit)
                                .addGap(18, 18, 18)
                                .addComponent(rbKredit)))))
                .addContainerGap(58, Short.MAX_VALUE))
        );
        jPanel3Layout.setVerticalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel3Layout.createSequentialGroup()
                .addGap(65, 65, 65)
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(lSewa)
                    .addComponent(tfLama, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel3Layout.createSequentialGroup()
                        .addGap(10, 10, 10)
                        .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel1)
                            .addComponent(rbDebit)))
                    .addGroup(jPanel3Layout.createSequentialGroup()
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(rbKredit)))
                .addGap(18, 18, 18)
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(tfUangMuka, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(lUangMuka))
                .addContainerGap(382, Short.MAX_VALUE))
        );

        jTabbedPane1.addTab("Order Detail", jPanel3);

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(jTabbedPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 531, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(0, 0, Short.MAX_VALUE))
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(bSave)
                        .addGap(18, 18, 18)
                        .addComponent(pbForm, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)))
                .addContainerGap())
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jTabbedPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 592, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(bSave, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(pbForm, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
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
            Date tanggal = getTanggal();
            String jk1 = PeJeKeGrup.getSelection().getActionCommand();
            String jk2 = prosesPasanganGender(lbPasangan.getSelectedValue());
            String total = String.valueOf(Integer.parseInt(tfHarga.getText()) * Integer.parseInt(tfLama.getText()));
            String no_pesanan = PesananController.getPesananNumber(tanggal, jk1, jk2, tfLama.getText());
            
            pesanan.setId(null);
            pesanan.setPasangan(prosesPasangan());
            pesanan.setIdPenyewa(getIdPenyewa());
            pesanan.setTgl(prosesTanggal());
            pesanan.setLama(tfLama.getText());
            pesanan.setStatus("0");
            pesanan.setTotal(total);
            pesanan.setPembayaran(pembayaranGrup.getSelection().getActionCommand());
            pesanan.setNo_pesanan(no_pesanan);
            
            
            controller.Create(pesanan);
            
            if (pembayaranGrup.getSelection().getActionCommand().equals("1")) {
                DetailPesanan dp = new DetailPesanan();
                CrudInterface<DetailPesanan> DeCont = new DetailPesananController();
                String idp = PesananController.getIdPesanan(no_pesanan);
                
                dp.setId(null);
                dp.setId_pesanan(idp);
                dp.setUangMuka(tfUangMuka.getText());
                
                DeCont.Create(dp);
                
            }
            
            JOptionPane.showMessageDialog(rootPane, Status.SUCCESS_INSERT);
                        
        } catch (SQLException ex) {
            Logger.getLogger(FormPesanan.class.getName()).log(Level.SEVERE, null, ex);
        }
    }//GEN-LAST:event_bSaveActionPerformed

    private void bSearchActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_bSearchActionPerformed
        // TODO add your handling code here:
        String categories = multiKategori();
        String jenis = genderGroup.getSelection().getActionCommand();
        tfHarga.setText("");
        lPicture.setIcon(null);
        lbPasangan.clearSelection();
        
        try {
            DefaultListModel model = PesananController.getFilteredModel(categories, jenis, prosesTanggal());
            lbPasangan.updateUI();
            lbPasangan.setModel(model);
            lbPasangan.clearSelection();
            
        } catch (SQLException ex) {
            Logger.getLogger(FormPesanan.class.getName()).log(Level.SEVERE, null, ex);
        }
    }//GEN-LAST:event_bSearchActionPerformed

    private void bResetActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_bResetActionPerformed
        // TODO add your handling code here:
        DefaultListModel model = loadListPasangan();
        lbPasangan.removeAll();
        dpTanggal.setDate(null);
        tfHarga.setText("");
        lbPasangan.setModel(model);
        lbPasangan.clearSelection();
        jScrollPane1.setViewportView(lbKategori);
        jScrollPane2.setViewportView(lbPasangan);
        lbPasangan.updateUI();
        
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

    private void cbTidakActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_cbTidakActionPerformed
        // TODO add your handling code here:
        cbKonsumen.setEnabled(true);
        makeNullKonsumen();
        DefaultComboBoxModel model;
        
        tfPenyewa.setEditable(false);
        tfKtp.setEditable(false);
        tfNohape.setEditable(false);
        taAlamat.setEditable(false);
        PeJeKeGrup.clearSelection();
        
        try {
            model = PesananController.loadKonsumen();
            cbKonsumen.setModel(model);
            cbKonsumen.updateUI();
            
        } catch (SQLException ex) {
            Logger.getLogger(FormPesanan.class.getName()).log(Level.SEVERE, null, ex);
        }
    }//GEN-LAST:event_cbTidakActionPerformed

    private void cbYaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_cbYaActionPerformed
        // TODO add your handling code here:
        cbKonsumen.setEnabled(false);
        makeNullKonsumen();
        
        tfPenyewa.setEditable(true);
        tfKtp.setEditable(true);
        tfNohape.setEditable(true);
        taAlamat.setEditable(true);
        PeJeKeGrup.clearSelection();
    }//GEN-LAST:event_cbYaActionPerformed

    private void tfPenyewaFocusLost(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_tfPenyewaFocusLost
        // TODO add your handling code here:
        String penyewa = tfPenyewa.getText();
        
        if (!penyewa.isEmpty()) {
            pbForm.setValue(20);
        }
    }//GEN-LAST:event_tfPenyewaFocusLost
    
    private void makeNullKonsumen() {
        tfKtp.setText("");
        tfNohape.setText("");
        tfPenyewa.setText("");
        taAlamat.setText("");
        genderGroup.clearSelection();
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
    
    private Date getTanggal() {
        int y, m, d;
        
        y = dpTanggal.getDate().getYear() + 1900;
        m = dpTanggal.getDate().getMonth() + 1;
        d = dpTanggal.getDate().getDate();
        
        return new Date(y, m, d);
    }
    
    private String getIdPenyewa() {
        String idP = null;
        CrudInterface<Konsumen> konCon;
        Konsumen konsumen;
        
        if (this.id == 0) {
            Map<String, String> data = new HashMap();
            konCon = new KonsumenController();
            
            data.put("id", null);
            data.put("ktp", tfKtp.getText());
            data.put("nama", tfPenyewa.getText());
            data.put("jenis_kelamin", PeJeKeGrup.getSelection().getActionCommand());
            data.put("no_handphone", tfNohape.getText());
            data.put("alamat", taAlamat.getText());
            
            konsumen = new Konsumen(data);
            
            try {
                konCon.Create(konsumen);
                idP = KonsumenController.getPenyewaId(tfKtp.getText());
            } catch (SQLException ex) {
                Logger.getLogger(FormPesanan.class.getName()).log(Level.SEVERE, null, ex);
            }
            
        } else {
            idP = String.valueOf(this.id);
        }
        
        return idP;
    }
    
    private String prosesPasangan() throws SQLException {
        String id = "";
        Map<String, Integer> map = PesananController.loadPasanganId();
        
        id = String.valueOf( map.get(lbPasangan.getSelectedValue() ) ); 
        return id;
    }
    
    private String prosesPasanganGender(String nama) throws SQLException {
        String gender = "";
        
            Map<String, String> data = PesananController.loadPasanganGender();
            gender = data.get(nama);

        return gender;
    }
    
    private void loadResource() {
        CrudInterface<Konsumen> konCo = new KonsumenController();
        
        try {
            record = konCo.Read();
        } catch (SQLException ex) {
            Logger.getLogger(FormPesanan.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    private void SetToTextField(Konsumen data) {
        tfPenyewa.setText( data.getNama() );
        tfNohape.setText( data.getNohape() );
        tfKtp.setText( data.getKtp() );
        taAlamat.setText( data.getAlamat() );
        PeJeKeGrup.clearSelection();
        
        switch (data.getJenis_kelamin()) {
            case "p" :
                rbPePria.setSelected(true);
                break;
            case "w" :
                rbPeWanita.setSelected(true);
                break;
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
    private javax.swing.ButtonGroup PeJeKeGrup;
    private javax.swing.JButton bReset;
    private javax.swing.JButton bSave;
    private javax.swing.JButton bSearch;
    private javax.swing.JComboBox<String> cbKonsumen;
    private javax.swing.JRadioButton cbTidak;
    private javax.swing.JRadioButton cbYa;
    private javax.swing.ButtonGroup checkingGroup;
    private org.jdesktop.swingx.JXDatePicker dpTanggal;
    private javax.swing.ButtonGroup genderGroup;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JPanel jPanel3;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JScrollPane jScrollPane3;
    private javax.swing.JTabbedPane jTabbedPane1;
    private javax.swing.JLabel lGender;
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
    private javax.swing.JProgressBar pbForm;
    private javax.swing.ButtonGroup pembayaranGrup;
    private javax.swing.JRadioButton rbDebit;
    private javax.swing.JRadioButton rbKredit;
    private javax.swing.JRadioButton rbPePria;
    private javax.swing.JRadioButton rbPeWanita;
    private javax.swing.JRadioButton rbPria;
    private javax.swing.JRadioButton rbWanita;
    private javax.swing.JTextArea taAlamat;
    private javax.swing.JTextField tfHarga;
    private javax.swing.JTextField tfKtp;
    private javax.swing.JTextField tfLama;
    private javax.swing.JTextField tfNohape;
    private javax.swing.JTextField tfPenyewa;
    private javax.swing.JTextField tfUangMuka;
    // End of variables declaration//GEN-END:variables
}
