/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controllers;

import connection.Database;
import interfaces.CrudInterface;
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
import models.Pegawai;

/**
 *
 * @author mmohl
 */
public class PasanganController implements CrudInterface<Pasangan>{
    private String sql;
    
    @Override
    public void Create(Pasangan object) throws SQLException {
        sql = "insert into pasangan values(?,?,?,?,?)";
        PreparedStatement preparedStatement = Database.getConnection().prepareStatement(sql);
        
        preparedStatement.setString(1, object.getId());
        preparedStatement.setInt(2, Integer.parseInt(object.getId_pegawai()));
        preparedStatement.setString(3, object.getId_kategori());
        preparedStatement.setInt(4, Integer.parseInt(object.getHarga()));
        preparedStatement.setString(5, object.getStatus());
        
        preparedStatement.executeUpdate();
        
    }

    @Override
    public java.util.List Read() throws SQLException {
        sql = "SELECT pe.nama AS nama, p.harga AS harga, p.id_kategori AS id_kategori, p.`status` AS status FROM pasangan p \n" +
                "LEFT JOIN pegawai pe ON p.id_pegawai = pe.id";
        Statement statement = Database.getConnection().createStatement();
        ResultSet resultSet = statement.executeQuery(sql);
        java.util.List list = new ArrayList();
        
        while (resultSet.next()) {
            Pasangan k = new Pasangan();
            
            k.setNama(resultSet.getString("nama"));
            k.setHarga(resultSet.getString("harga"));
            k.setId_kategori(resultSet.getString("id_kategori"));
            k.setStatus(resultSet.getString("status"));
            list.add(k);
        }
        
        return list;
        
    }

    @Override
    public void Update(Pasangan object) throws SQLException {
        sql = "update pasangan set id_pegawai = ?, id_kategori = ?, harga = ?, status = ? where id = ?";
        PreparedStatement preparedStatement = Database.getConnection().prepareStatement(sql);
        
        preparedStatement.setInt(1, Integer.parseInt(object.getId_pegawai()));
        preparedStatement.setString(2, object. getId_kategori());
        preparedStatement.setInt(3, Integer.parseInt(object.getHarga()));
        preparedStatement.setString(4, object. getStatus());
        preparedStatement.setInt(5, Integer.parseInt(object.getId()));
        preparedStatement.executeUpdate();
    }

    @Override
    public void Delete(String id) throws SQLException {
        sql = "delete from pasangan where id = ?";
        
        PreparedStatement preparedStatement = Database.getConnection().prepareStatement(sql);
        preparedStatement.setInt(1, Integer.parseInt(id));
        preparedStatement.executeUpdate();
    }
    
    public static DefaultListModel loadModel() {
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
    
    public static Map<String, Integer> loadModelId() {
        Map<String, Integer> _id = new HashMap<String, Integer>();
        
        String sql = "select kategori, id from kategori";
        Statement statement;
        
        try {
            statement = Database.getConnection().createStatement();
            ResultSet resultSet = statement.executeQuery(sql);
            while(resultSet.next()) {
                _id.put(resultSet.getString("kategori"), resultSet.getInt("id"));
            }
            resultSet.close();
            statement.close();
        } catch (SQLException ex) {
            Logger.getLogger(PasanganController.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        return _id;
    }
    
    public static DefaultComboBoxModel loadPegawai() throws SQLException {
        DefaultComboBoxModel combo = new DefaultComboBoxModel();
        
        String sql = "SELECT p.nama FROM pegawai p LEFT JOIN pasangan pp ON p.id = pp.id_pegawai WHERE pp.id IS NULL";
        ResultSet resultSet;
        try (Statement statement = Database.getConnection().createStatement()) {
            resultSet = statement.executeQuery(sql);
            while(resultSet.next()) {
                combo.addElement( new Pegawai(resultSet.getString("nama")) );
            }
        }
        resultSet.close();
        
        return combo;        
    }
    
    public static Map<String, Integer> loadPegawaiId() throws SQLException {
        Map<String, Integer> id = new HashMap<String, Integer>();
        
        String sql = "select id, nama from pegawai";
        Statement statement = Database.getConnection().createStatement();
        ResultSet resultSet = statement.executeQuery(sql);
        
        while (resultSet.next()) {
            id.put(resultSet.getString("nama"), resultSet.getInt("id"));
        }
        
        resultSet.close();
        
        return id;
    }

    @Override
    public List Search(String name) throws SQLException {
        sql = "SELECT pe.nama AS nama, p.harga AS harga, p.id_kategori AS id_kategori, p.`status` AS status FROM pasangan p \n" +
                "LEFT JOIN pegawai pe ON p.id_pegawai = pe.id where pe.nama like '%" + name +"%'";
        Statement statement = Database.getConnection().createStatement();
        ResultSet resultSet = statement.executeQuery(sql);
        java.util.List list = new ArrayList();
        
        while (resultSet.next()) {
            Pasangan k = new Pasangan();
            
            k.setNama(resultSet.getString("nama"));
            k.setHarga(resultSet.getString("harga"));
            k.setId_kategori(resultSet.getString("id_kategori"));
            k.setStatus(resultSet.getString("status"));
            list.add(k);
        }
        
        return list;
    }
    
    public static Integer getIdUser(String key) throws SQLException {
        int id = 0;
        Map<String, Integer> data = new HashMap<String, Integer>();
        String sql = "SELECT p.id AS id, pe.nama AS nama FROM pasangan p \n" +
                "LEFT JOIN pegawai pe ON p.id_pegawai = pe.id";
        
        Statement statement = Database.getConnection().createStatement();
        ResultSet resultSet = statement.executeQuery(sql);
        
        while (resultSet.next()) {
            data.put(resultSet.getString("nama"), resultSet.getInt("id"));
        }
        
        id = data.get(key);
        return id;
    }
    
    public static String getAllCategories(String kategori) throws SQLException {
        String result = "";
        String sql = "SELECT kategori, id FROM kategori";
        Map<Integer, String> resources = new HashMap<Integer, String>();
        int i = 1;
        
        Statement preparedStatement = Database.getConnection().createStatement();
        ResultSet resultSet = preparedStatement.executeQuery(sql);
        
        while (resultSet.next()) {
            resources.put(resultSet.getInt("id"), resultSet.getString("kategori"));
        }
        
        for (String id : kategori.split("-")) {
            
            if (!result.isEmpty()) {
                result += ", ";
            }
            
            String data = resources.get(Integer.parseInt(id));
            result += String.valueOf(i) + ". " + data;
            i += 1;
        }
        
        return result;
    }
    
    public static String getAllCategoriesById(String idPasangan) throws SQLException {
        String result = "";
        String kategori = "";
        String sql = "SELECT kategori, id FROM kategori";
        Map<Integer, String> resources = new HashMap<Integer, String>();
        int i = 1;
        
        Statement preparedStatement = Database.getConnection().createStatement();
        ResultSet resultSet = preparedStatement.executeQuery(sql);
        
        while (resultSet.next()) {
            resources.put(resultSet.getInt("id"), resultSet.getString("kategori"));
        }
        
        sql = "SELECT id_kategori FROM pasangan WHERE id = " + idPasangan;
        preparedStatement = Database.getConnection().createStatement();
        resultSet = preparedStatement.executeQuery(sql);
        
        while (resultSet.next()) {
            kategori = resultSet.getString("id_kategori");
        }
        
        for (String id : kategori.split("-")) {
            
            if (!result.isEmpty()) {
                result += ", ";
            }
            
            String data = resources.get(Integer.parseInt(id));
            result += String.valueOf(i) + ". " + data;
            i += 1;
        }
        
        return result;
    }
    
    public static String sumCategoriesPrice(String kategori) throws SQLException {
        String result = "";
        String sql = "SELECT harga, id FROM kategori";
        Map<Integer, Integer> resources = new HashMap<Integer, Integer>();
        int temporary = 0;
        
        Statement preparedStatement = Database.getConnection().createStatement();
        ResultSet resultSet = preparedStatement.executeQuery(sql);
        
        while (resultSet.next()) {
            resources.put(resultSet.getInt("id"), resultSet.getInt("harga"));
        }
        
        for (String id : kategori.split("-")) {
            
            temporary += resources.get(Integer.parseInt(id));

        }
        
        result = String.valueOf(temporary);
        
        return result;
    }
    
     
    
}