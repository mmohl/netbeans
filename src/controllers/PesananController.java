/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controllers;

import connection.Database;
import interfaces.CrudInterface;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Random;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.DefaultComboBoxModel;
import javax.swing.DefaultListModel;
import models.Konsumen;
import models.Pasangan;
import models.Pesanan;

/**
 *
 * @author mmohl
 */
public class PesananController implements CrudInterface<Pesanan>{
    
    private String sql;

    @Override
    public void Create(Pesanan object) throws SQLException {
        sql = "insert into pesanan values (?, ?, ?, ?, ?, ?, ?, ?, ?)";
        PreparedStatement preparedStatement = Database.getConnection().prepareStatement(sql);
        
        preparedStatement.setString(1, object.getId());
        preparedStatement.setInt(2, Integer.parseInt(object.getPasangan()));
        preparedStatement.setInt(3, Integer.parseInt(object.getIdPenyewa()));
        preparedStatement.setDate(4, Date.valueOf(object.getTgl()));
        preparedStatement.setInt(5, Integer.parseInt(object.getLama()));
        preparedStatement.setString(6, object.getStatus());
        preparedStatement.setInt(7, Integer.parseInt(object.getTotal()));
        preparedStatement.setString(8, object.getPembayaran());
        preparedStatement.setString(9, object.getNo_pesanan());
        preparedStatement.executeUpdate();
        
    }

    @Override
    public java.util.List Read() throws SQLException {
        List<Pesanan> data = new ArrayList<>();
        sql = "SELECT ko.alamat, p.id, pa.harga, ko.id AS idPenyewa, ko.ktp, p.lama, pe.nama, p.nomor_pesanan, ko.no_handphone, pa.id AS idPasangan,\n" +
                "  ko.jenis_kelamin, p.pembayaran, ko.nama AS penyewa, p.status, p.tanggal, dp.uang_muka, p.total FROM pesanan p\n" +
                "    LEFT JOIN pasangan pa ON p.id_pasangan = pa.id\n" +
                "    LEFT JOIN konsumen ko ON p.id_konsumen = ko.id\n" +
                "    LEFT JOIN pegawai pe ON pa.id_pegawai = pe.id\n" +
                "    LEFT JOIN detail_pesanan dp ON p.id = dp.id_pesanan\n" +
                "    ORDER BY p.tanggal DESC";
        Statement preparedStatement = Database.getConnection().createStatement();
        ResultSet resultSet = preparedStatement.executeQuery(sql);
        
        while (resultSet.next()) {
            Pesanan pesanan = new Pesanan();
            
            pesanan.setAlamat(resultSet.getString("alamat"));
            pesanan.setHarga(String.valueOf(resultSet.getInt("harga")));
            pesanan.setId(String.valueOf(resultSet.getInt("id")));
            pesanan.setIdPenyewa(String.valueOf(resultSet.getInt("idPenyewa")));
            pesanan.setKtp(resultSet.getString("ktp"));
            pesanan.setLama(String.valueOf(resultSet.getInt("lama")));
            pesanan.setNama(resultSet.getString("nama"));
            pesanan.setNo_pesanan(resultSet.getString("nomor_pesanan"));
            pesanan.setNohape(resultSet.getString("no_handphone"));
            pesanan.setPasangan(String.valueOf(resultSet.getInt("idPasangan")));
            pesanan.setPeJeKe(resultSet.getString("jenis_kelamin"));
            pesanan.setPembayaran(resultSet.getString("pembayaran"));
            pesanan.setPenyewa(resultSet.getString("penyewa"));
            pesanan.setStatus(resultSet.getString("status"));
            pesanan.setTgl(String.valueOf(resultSet.getDate("tanggal")));
            pesanan.setTotal(String.valueOf(resultSet.getInt("total")));
            pesanan.setUangMuka(String.valueOf(resultSet.getInt("uang_muka")));
            
            data.add(pesanan);
        }
        
        return data;
    }

    @Override
    public void Update(Pesanan object) throws SQLException {
        sql = "UPDATE pesanan p LEFT JOIN detail_pesanan dp ON p.id = dp.id_pesanan SET "
                + "p.id_pasangan = ?, p.id_konsumen = ?, p.tanggal = ?, "
                + "p.lama = ?, p.status = ?, p.total = ?, "
                + "p.pembayaran = ?, p.nomor_pesanan = ?, dp.uang_muka = ? WHERE p.id = ?";
        
        PreparedStatement preparedStatement = Database.getConnection().prepareStatement(sql);
        
        preparedStatement.setInt(1, Integer.parseInt(object.getPasangan()));
        preparedStatement.setInt(2, Integer.parseInt(object.getIdPenyewa()));
        preparedStatement.setDate(3, Date.valueOf(object.getTgl()));
        preparedStatement.setInt(4, Integer.parseInt(object.getLama()));
        preparedStatement.setString(5, object.getStatus());
        preparedStatement.setInt(6, Integer.parseInt(object.getTotal()));
        preparedStatement.setString(7, object.getPembayaran());
        preparedStatement.setString(8, object.getNo_pesanan());
        preparedStatement.setInt(9, Integer.parseInt(object.getUangMuka()));
        preparedStatement.setInt(10, Integer.parseInt(object.getId()));
        preparedStatement.executeUpdate();
    }

    @Override
    public void Delete(String id) throws SQLException {
        sql = "delete from pesanan where id = ?";
        PreparedStatement preparedStatement = Database.getConnection().prepareStatement(sql);
        preparedStatement.setInt(1, Integer.parseInt(id));
        preparedStatement.executeUpdate();
    }
    
    public static DefaultComboBoxModel loadPasangan() throws SQLException {
        DefaultComboBoxModel model = new DefaultComboBoxModel();
        
        String sql = "SELECT pe.id as id, pe.nama as nama FROM pasangan p LEFT JOIN pegawai pe ON p.id_pegawai = pe.id";
        
        Statement statement = Database.getConnection().createStatement();
        ResultSet resultSet = statement.executeQuery(sql);
        
        while (resultSet.next()) {
            model.addElement(new Pasangan(String.valueOf(resultSet.getInt("id")), resultSet.getString("nama")));
        }
        
        return model;
    }
    
    public static Map<String, Integer> loadPasanganId() throws SQLException {
        Map<String, Integer> map = new HashMap<String, Integer>();
        String sql = "SELECT p.id as id, pe.nama as nama FROM pasangan p LEFT JOIN pegawai pe ON p.id_pegawai = pe.id";
        
        Statement statement = Database.getConnection().createStatement();
        ResultSet resultSet = statement.executeQuery(sql);
        
        while (resultSet.next()) {
            map.put(resultSet.getString("nama"), resultSet.getInt("id"));
        }
        
        return map;
    }
    
    public static Map<String, String> loadPasanganGender() throws SQLException {
        Map<String, String> map = new HashMap<>();
        String sql = "SELECT pe.nama, pe.jenis_kelamin FROM pasangan p LEFT JOIN pegawai pe ON p.id_pegawai = pe.id";
        
        Statement statement = Database.getConnection().createStatement();
        ResultSet resultSet = statement.executeQuery(sql);
        
        while (resultSet.next()) {
            map.put(resultSet.getString("nama"), resultSet.getString("jenis_kelamin"));
        }
        
        return map;
    }

    @Override
    public List Search(String name) throws SQLException {
        List<Pesanan> data = new ArrayList<>();
        sql = "SELECT ko.alamat, p.id, pa.harga, ko.id AS idPenyewa, ko.ktp, p.lama, pe.nama, p.nomor_pesanan, ko.no_handphone, pa.id AS idPasangan,\n" +
                "  ko.jenis_kelamin, p.pembayaran, ko.nama AS penyewa, p.status, p.tanggal, dp.uang_muka, p.total FROM pesanan p\n" +
                "    LEFT JOIN pasangan pa ON p.id_pasangan = pa.id\n" +
                "    LEFT JOIN konsumen ko ON p.id_konsumen = ko.id\n" +
                "    LEFT JOIN pegawai pe ON pa.id_pegawai = pe.id\n" +
                "    LEFT JOIN detail_pesanan dp ON p.id = dp.id_pesanan\n" +
                "    WHERE p.nomor_pesanan = '" + name + "' AND p.status = '0' AND p.pembayaran = '1'" +   
                "    ORDER BY p.tanggal DESC";
        Statement preparedStatement = Database.getConnection().createStatement();
        ResultSet resultSet = preparedStatement.executeQuery(sql);
        
        while (resultSet.next()) {
            Pesanan pesanan = new Pesanan();
            
            pesanan.setAlamat(resultSet.getString("alamat"));
            pesanan.setHarga(String.valueOf(resultSet.getInt("harga")));
            pesanan.setId(String.valueOf(resultSet.getInt("id")));
            pesanan.setIdPenyewa(String.valueOf(resultSet.getInt("idPenyewa")));
            pesanan.setKtp(resultSet.getString("ktp"));
            pesanan.setLama(String.valueOf(resultSet.getInt("lama")));
            pesanan.setNama(resultSet.getString("nama"));
            pesanan.setNo_pesanan(resultSet.getString("nomor_pesanan"));
            pesanan.setNohape(resultSet.getString("no_handphone"));
            pesanan.setPasangan(String.valueOf(resultSet.getInt("idPasangan")));
            pesanan.setPeJeKe(resultSet.getString("jenis_kelamin"));
            pesanan.setPembayaran(resultSet.getString("pembayaran"));
            pesanan.setPenyewa(resultSet.getString("penyewa"));
            pesanan.setStatus(resultSet.getString("status"));
            pesanan.setTgl(String.valueOf(resultSet.getDate("tanggal")));
            pesanan.setTotal(String.valueOf(resultSet.getInt("total")));
            pesanan.setUangMuka(String.valueOf(resultSet.getInt("uang_muka")));
            
            data.add(pesanan);
        }
        
        return data;
    }
    
    public static List SearchFilter(String name) throws SQLException {
        List<Pesanan> data = new ArrayList<>();
        String sql = "SELECT ko.alamat, p.id, pa.harga, ko.id AS idPenyewa, ko.ktp, p.lama, pe.nama, p.nomor_pesanan, ko.no_handphone, pa.id AS idPasangan,\n" +
                "  ko.jenis_kelamin, p.pembayaran, ko.nama AS penyewa, p.status, p.tanggal, dp.uang_muka, p.total FROM pesanan p\n" +
                "    LEFT JOIN pasangan pa ON p.id_pasangan = pa.id\n" +
                "    LEFT JOIN konsumen ko ON p.id_konsumen = ko.id\n" +
                "    LEFT JOIN pegawai pe ON pa.id_pegawai = pe.id\n" +
                "    LEFT JOIN detail_pesanan dp ON p.id = dp.id_pesanan\n" +
                "    WHERE p.nomor_pesanan = '" + name + "'" +   
                "    ORDER BY p.tanggal DESC";
        Statement preparedStatement = Database.getConnection().createStatement();
        ResultSet resultSet = preparedStatement.executeQuery(sql);
        
        while (resultSet.next()) {
            Pesanan pesanan = new Pesanan();
            
            pesanan.setAlamat(resultSet.getString("alamat"));
            pesanan.setHarga(String.valueOf(resultSet.getInt("harga")));
            pesanan.setId(String.valueOf(resultSet.getInt("id")));
            pesanan.setIdPenyewa(String.valueOf(resultSet.getInt("idPenyewa")));
            pesanan.setKtp(resultSet.getString("ktp"));
            pesanan.setLama(String.valueOf(resultSet.getInt("lama")));
            pesanan.setNama(resultSet.getString("nama"));
            pesanan.setNo_pesanan(resultSet.getString("nomor_pesanan"));
            pesanan.setNohape(resultSet.getString("no_handphone"));
            pesanan.setPasangan(String.valueOf(resultSet.getInt("idPasangan")));
            pesanan.setPeJeKe(resultSet.getString("jenis_kelamin"));
            pesanan.setPembayaran(resultSet.getString("pembayaran"));
            pesanan.setPenyewa(resultSet.getString("penyewa"));
            pesanan.setStatus(resultSet.getString("status"));
            pesanan.setTgl(String.valueOf(resultSet.getDate("tanggal")));
            pesanan.setTotal(String.valueOf(resultSet.getInt("total")));
            pesanan.setUangMuka(String.valueOf(resultSet.getInt("uang_muka")));
            
            data.add(pesanan);
        }
        
        return data;
    }
    
    public static DefaultListModel loadModelCategories() {
        DefaultListModel<String> combo = new DefaultListModel<>();
        String sql = "select kategori from kategori";
        Statement statement;
        
        try {
            statement = Database.getConnection().createStatement();
            ResultSet resultSet = statement.executeQuery(sql);
            
            while(resultSet.next()) {
                combo.addElement(resultSet.getString("kategori"));
            }
            
            resultSet.close();
            statement.close();
        } catch (SQLException ex) {
            Logger.getLogger(PasanganController.class.getName()).log(Level.SEVERE, null, ex);
        } 
        
        return combo;
    }
    
    public static DefaultListModel loadModelCouples() {
        DefaultListModel<String> combo = new DefaultListModel<String>();
        String sql = "SELECT pe.nama as nama FROM pasangan p LEFT JOIN pegawai pe ON p.id_pegawai = pe.id";
        Statement statement;
        
        try {
            statement = Database.getConnection().createStatement();
            ResultSet resultSet = statement.executeQuery(sql);
            
            while(resultSet.next()) {
                combo.addElement(resultSet.getString("nama"));
            }
            
            resultSet.close();
            statement.close();
        } catch (SQLException ex) {
            Logger.getLogger(PasanganController.class.getName()).log(Level.SEVERE, null, ex);
        } 
        
        return combo;
    }
    
    public static Integer getIdPegawai(String name) throws SQLException {
        int id = 0;
        String sql = "SELECT id FROM pegawai WHERE nama = ?";
        
        PreparedStatement preparedStatement = Database.getConnection().prepareStatement(sql);
        preparedStatement.setString(1, name);
        ResultSet resultSet = preparedStatement.executeQuery();
        
        while (resultSet.next()) {
            id = resultSet.getInt("id");
        }
        
        return id;
    }
    
    public static String getTotalPrice(Integer id) throws SQLException {
        String total;
        int temp;
        Map<String, String> value = new HashMap<String, String>();
        String allCategories = "";
        String sql = "SELECT pa.harga AS harga, pa.id_kategori AS id_kategori FROM pegawai pe\n" +
                     "JOIN pasangan pa ON pe.id = pa.id_pegawai WHERE pe.id = ?";
        
        PreparedStatement preparedStatement = Database.getConnection().prepareStatement(sql);
        preparedStatement.setInt(1, id);
        ResultSet resultSet = preparedStatement.executeQuery();
        
        while (resultSet.next()) {
            value.put("id_kategori", resultSet.getString("id_kategori"));
            value.put("harga", String.valueOf(resultSet.getInt("harga")));
        }
        
        resultSet.close();
        preparedStatement.close();
        
        allCategories = PasanganController.sumCategoriesPrice(value.get("id_kategori"));
        
        temp = Integer.parseInt(allCategories) + Integer.parseInt(value.get("harga"));
        total = String.valueOf(temp);
        
        return total;
    }
    
    public static DefaultListModel getFilteredModel(String kategori) throws SQLException {
        DefaultListModel<String> model = new DefaultListModel<String>();
        String sql = "SELECT pe.nama AS nama FROM pegawai pe \n" +
                     "LEFT JOIN pasangan pa ON pe.id = pa.id_pegawai\n" +
                     "WHERE pa.id_kategori LIKE '%" + kategori + "%'";
        Statement preparedStatement = Database.getConnection().createStatement();
        ResultSet resultSet = preparedStatement.executeQuery(sql);
        
        while (resultSet.next()) {
            model.addElement(resultSet.getString("nama"));
        }
        
        return model;
    }
    
    public static DefaultListModel getFilteredModel(String kategori, String jenis, String tanggal) throws SQLException {
        DefaultListModel<String> model = new DefaultListModel<String>();
//        String sql = "SELECT pe.nama AS nama FROM pegawai pe \n" +
//                     "LEFT JOIN pasangan pa ON pe.id = pa.id_pegawai\n" +
//                     "WHERE pa.id_kategori LIKE '%" + kategori + "%'";
        
        String sql = "SELECT pg.nama\n" +
                        "FROM pegawai pg\n" +
                        "WHERE pg.jenis_kelamin = '"+ jenis +"' AND pg.id NOT IN (\n" +
                        "  SELECT pe.id FROM pasangan pa\n" +
                        "  LEFT JOIN pesanan p ON p.id_pasangan = pa.id\n" +
                        "  LEFT JOIN pegawai pe ON pe.id = pa.id_pegawai\n" +
                        "  WHERE p.tanggal = '" + tanggal +"' AND pa.id_kategori LIKE '%" + kategori +"%' " +
                        " AND p.status != '1'\n" +
                        ")";
        
        Statement preparedStatement = Database.getConnection().createStatement();
        ResultSet resultSet = preparedStatement.executeQuery(sql);
        
        while (resultSet.next()) {
            model.addElement(resultSet.getString("nama"));
        }
        
        return model;
    }
    
    public static String getPicture(Integer id) throws SQLException {
        String image = null;
        String sql = "SELECT gambar FROM pegawai WHERE id = ?";
        PreparedStatement preparedStatement = Database.getConnection().prepareStatement(sql);
        preparedStatement.setInt(1, id);
        ResultSet resultSet = preparedStatement.executeQuery();
        
        while (resultSet.next()) {
            image = resultSet.getString("gambar");
        }
        return image;
    }
    
    public static String getPesananNumber(java.util.Date tanggal, String jk1, String jk2, String lamasewa) {
        /**
         *  jk1 = penyewa
         *  jk2 = pasangan
         *  p = pria
         *  w = wanita
         *  n = lama sewa
         *  xxxx = random number 5 
         *  
         */
        // jk1jk2/n/yymmdd/xxxxx
        // pp/1/161020/11114
        // wp/5/161030/89896
        java.util.Date date = tanggal;
        String no;
        String yy, mm, dd, acak;
        Random rand = new Random();
        
        
        yy = String.valueOf( date.getYear() - 2000 );
        mm = String.valueOf( date.getMonth() );
        dd = String.valueOf( date.getDate() );
        acak = String.valueOf( rand.nextInt(99999) );
        
        no = jk1.toUpperCase() + jk2.toUpperCase() + "/" + String.valueOf(lamasewa) + "/" + yy+mm+dd + "/" + acak;
        
        return no;
    }
    
    public static java.util.List getUnconfirmed() throws SQLException {
        List<Pesanan> data = new ArrayList<>();
        String sql = "SELECT ko.alamat, p.id, pa.harga, ko.id AS idPenyewa, ko.ktp, p.lama, pe.nama, p.nomor_pesanan, ko.no_handphone, pa.id AS idPasangan,\n" +
                "  ko.jenis_kelamin, p.pembayaran, ko.nama AS penyewa, p.status, p.tanggal, dp.uang_muka, p.total FROM pesanan p\n" +
                "    LEFT JOIN pasangan pa ON p.id_pasangan = pa.id\n" +
                "    LEFT JOIN konsumen ko ON p.id_konsumen = ko.id\n" +
                "    LEFT JOIN pegawai pe ON pa.id_pegawai = pe.id\n" +
                "    LEFT JOIN detail_pesanan dp ON p.id = dp.id_pesanan\n" +
                "    WHERE p.status = '0' AND p.pembayaran = '1'" +   
                "    ORDER BY p.tanggal DESC";
        Statement preparedStatement = Database.getConnection().createStatement();
        ResultSet resultSet = preparedStatement.executeQuery(sql);
        
        while (resultSet.next()) {
            Pesanan pesanan = new Pesanan();
            
            pesanan.setAlamat(resultSet.getString("alamat"));
            pesanan.setHarga(String.valueOf(resultSet.getInt("harga")));
            pesanan.setId(String.valueOf(resultSet.getInt("id")));
            pesanan.setIdPenyewa(String.valueOf(resultSet.getInt("idPenyewa")));
            pesanan.setKtp(resultSet.getString("ktp"));
            pesanan.setLama(String.valueOf(resultSet.getInt("lama")));
            pesanan.setNama(resultSet.getString("nama"));
            pesanan.setNo_pesanan(resultSet.getString("nomor_pesanan"));
            pesanan.setNohape(resultSet.getString("no_handphone"));
            pesanan.setPasangan(String.valueOf(resultSet.getInt("idPasangan")));
            pesanan.setPeJeKe(resultSet.getString("jenis_kelamin"));
            pesanan.setPembayaran(resultSet.getString("pembayaran"));
            pesanan.setPenyewa(resultSet.getString("penyewa"));
            pesanan.setStatus(resultSet.getString("status"));
            pesanan.setTgl(String.valueOf(resultSet.getDate("tanggal")));
            pesanan.setTotal(String.valueOf(resultSet.getInt("total")));
            pesanan.setUangMuka(String.valueOf(resultSet.getInt("uang_muka")));
            
            data.add(pesanan);
        }
        
        return data;
        
    }
    
    public static DefaultComboBoxModel loadKonsumen() throws SQLException {
        DefaultComboBoxModel combo = new DefaultComboBoxModel();
        String[] attributes = Konsumen.getAttributes();
        
        int i = 0;
        Map<String, String> obj = new HashMap<>();
        String sql = "SELECT * FROM konsumen\n" +
                        "ORDER BY nama ASC, jenis_kelamin ASC";
        ResultSet resultSet;
        
        try (Statement statement = Database.getConnection().createStatement()) {
            resultSet = statement.executeQuery(sql);
            
            while(resultSet.next()) {
                
                for (String attribute : attributes) {
                    obj.put(attribute, resultSet.getString(attribute));
                }
                
                combo.addElement( new Konsumen(obj) );
                i += 1;
            }
        }
        resultSet.close();
        
        return combo;        
    }
    
    public static String getIdPesanan(String no_pesanan) throws SQLException {
        String no = "";
        String sql = "SELECT id FROM pesanan WHERE nomor_pesanan = '" + no_pesanan + "'";
        Statement statement = Database.getConnection().createStatement();
        ResultSet resultSet = statement.executeQuery(sql);
        
        while (resultSet.next()) {
            no = String.valueOf(resultSet.getInt("id"));
        }
        
        return no;
    }
    
}
