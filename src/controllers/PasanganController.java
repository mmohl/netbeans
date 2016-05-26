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
import java.util.Map;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.DefaultComboBoxModel;
import javax.swing.DefaultListModel;
import models.Kategori;
import models.Pasangan;
import models.Pegawai;

/**
 *
 * @author mmohl
 */
public class PasanganController implements CrudInterface<Pasangan>{
    private String sql;
    
    @Override
    public Pasangan Create(Pasangan object) throws SQLException {
        sql = "insert into pasangan values(?,?,?,?,?)";
        PreparedStatement preparedStatement = Database.getConnection().prepareStatement(sql);
        
        preparedStatement.setString(1, object.getId());
        preparedStatement.setInt(2, Integer.parseInt(object.getId_pegawai()));
        preparedStatement.setString(3, object.getId_kategori());
        preparedStatement.setInt(4, Integer.parseInt(object.getHarga()));
        preparedStatement.setString(5, object.getStatus());
        
        preparedStatement.executeUpdate();
        
        return object;
    }

    @Override
    public java.util.List Read() throws SQLException {
        sql = "SELECT p.id, kategori AS id_kategori, pg.nama AS id_pegawai, p.harga, p.`status`\n" +
                "FROM pasangan p\n" +
                "JOIN kategori k ON p.id_kategori = k.id\n" +
                "JOIN pegawai pg ON p.id_pegawai = pg.id\n" +
                "WHERE p.`status` = 1";
        Statement statement = Database.getConnection().createStatement();
        ResultSet resultSet = statement.executeQuery(sql);
        java.util.List list = new ArrayList();
        
        while (resultSet.next()) {
            Pasangan k = new Pasangan();
            
            k.setId(resultSet.getString("id"));
            k.setHarga(resultSet.getString("harga"));
            k.setId_kategori(resultSet.getString("id_kategori"));
            k.setId_pegawai(resultSet.getString("id_pegawai"));
            k.setStatus(resultSet.getString("status"));
            list.add(k);
        }
        
        return list;
        
    }

    @Override
    public void Update(Pasangan object) throws SQLException {
        sql = "update pasangan set id_pegawai = ?, id_kategori = ?, harga = ?, status = ?";
        
        PreparedStatement preparedStatement = Database.getConnection().prepareStatement(sql);
        preparedStatement.setString(1, object.getId_pegawai());
        preparedStatement.setString(2, object. getId_kategori());
        preparedStatement.setString(3, object. getHarga());
        preparedStatement.setString(4, object. getStatus());
//        preparedStatement.setString(5, object. getId());
        preparedStatement.executeUpdate();
    }

    @Override
    public void Delete(String id) throws SQLException {
        sql = "delete from kategori where id = ?";
        
        PreparedStatement preparedStatement = Database.getConnection().prepareStatement(sql);
        preparedStatement.setString(1, id);
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
        
        String sql = "select nama from pegawai";
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
    
}