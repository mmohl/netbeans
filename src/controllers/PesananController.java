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
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.DefaultComboBoxModel;
import javax.swing.DefaultListModel;
import models.Pasangan;
import models.Pesanan;

/**
 *
 * @author mmohl
 */
public class PesananController implements CrudInterface<Pesanan>{
    
    private String sql;

    @Override
    public Pesanan Create(Pesanan object) throws SQLException {
        sql = "insert into pesanan values (?, ?, ?, ?, ?, ?, ?, ?)";
        PreparedStatement preparedStatement = Database.getConnection().prepareStatement(sql);
        
        preparedStatement.setString(1, object.getId());
        preparedStatement.setInt(2, Integer.parseInt(object.getPasangan()));
        preparedStatement.setDate(3, Date.valueOf(object.getTgl()));
        preparedStatement.setInt(4, Integer.parseInt(object.getLama()));
        preparedStatement.setString(5, object.getPenyewa());
        preparedStatement.setString(6, object.getKtp());
        preparedStatement.setString(7, object.getNohape());
        preparedStatement.setString(8, object.getStatus());
        preparedStatement.executeUpdate();
        
        return object;
    }

    @Override
    public java.util.List Read() throws SQLException {
        sql = "SELECT * FROM pesanan p LEFT JOIN detail_pesanan dp ON p.id = dp.id_pesanan";
        Statement statement = Database.getConnection().createStatement();
        ResultSet resultSet = statement.executeQuery(sql);
        java.util.List list = new ArrayList();
        
        while (resultSet.next()) {
            Pesanan pesanan = new Pesanan();
            
            pesanan.setId(String.valueOf(resultSet.getInt("id")));
            pesanan.setPasangan(String.valueOf(resultSet.getInt("id_pasangan")));
            pesanan.setTgl(String.valueOf(resultSet.getDate("tanggal")));
            pesanan.setLama(String.valueOf(resultSet.getInt("lama")));
            pesanan.setPenyewa(resultSet.getString("penyewa"));
            pesanan.setKtp(resultSet.getString("ktp"));
            pesanan.setNohape(resultSet.getString("no_handphone"));
            if ( resultSet.getString("status").equals("1") ) {
                pesanan.setStatus("Kredit");
            } else {
                pesanan.setStatus("Debit");
            }
            
            list.add(pesanan);
        }
        
        return list;
    }

    @Override
    public void Update(Pesanan object) throws SQLException {
        sql = "update pesanan set "
                + "id_pasangan = ?, tanggal = ?, "
                + "lama = ?, penyewa = ?, ktp = ?, "
                + "no_handphone = ?, status = ? where id = ?";
        
        PreparedStatement preparedStatement = Database.getConnection().prepareStatement(sql);
        
        preparedStatement.setInt(1, Integer.parseInt(object.getPasangan()));
        preparedStatement.setDate(2, Date.valueOf(object.getTgl()));
        preparedStatement.setInt(3, Integer.parseInt(object.getLama()));
        preparedStatement.setString(4, object.getPenyewa());
        preparedStatement.setString(5, object.getKtp());
        preparedStatement.setString(6, object.getNohape());
        preparedStatement.setInt(7, Integer.parseInt(object.getId()));
        preparedStatement.executeUpdate();
    }

    @Override
    public void Delete(String id) throws SQLException {
        sql = "delete from pesanan where id = ?";
        PreparedStatement preparedStatement = Database.getConnection().prepareStatement(sql);
        preparedStatement.setInt(1, Integer.parseInt(id));
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

    @Override
    public List Search(String name) throws SQLException {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
    
    public static DefaultListModel loadModelCategories() {
        DefaultListModel<String> combo = new DefaultListModel<String>();
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
    
}
