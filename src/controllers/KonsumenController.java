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
import models.Konsumen;

/**
 *
 * @author mmohl
 */
public class KonsumenController implements CrudInterface<Konsumen>{
    
    private String sql;
    private Statement statement;
    private PreparedStatement preparedStatement;

    @Override
    public void Create(Konsumen object) throws SQLException {
        sql = "INSERT INTO konsumen values (?, ?, ?, ?, ?, ?)";
        
        preparedStatement = Database.getConnection().prepareStatement(sql);
        preparedStatement.setString(1, null);
        preparedStatement.setString(2, object.getKtp());
        preparedStatement.setString(3, object.getNama());
        preparedStatement.setString(4, object.getJenis_kelamin());
        preparedStatement.setString(5, object.getNohape());
        preparedStatement.setString(6, object.getAlamat());
        preparedStatement.executeUpdate();
        
    }

    @Override
    public List Read() throws SQLException {
        List<Konsumen> list = new ArrayList<>();
//        String[] attributes = Konsumen.getAttributes();
        Map<String, String> data = new HashMap<>();
        Konsumen konsumen;
        sql = "SELECT * FROM konsumen ORDER BY nama ASC, jenis_kelamin ASC";
        
        statement = Database.getConnection().createStatement();
        ResultSet resultSet = statement.executeQuery(sql);
        
        while (resultSet.next()) {
            
            
//            for (String attribute : attributes) {
//                data.put(resultSet.getString(attribute), resultSet.getString(attribute));
//            }
            data.put("id", resultSet.getString("id"));
            data.put("ktp", resultSet.getString("ktp"));
            data.put("nama", resultSet.getString("nama"));
            data.put("no_handphone", resultSet.getString("no_handphone"));
            data.put("jenis_kelamin", resultSet.getString("jenis_kelamin"));
            data.put("alamat", resultSet.getString("alamat"));
            
            konsumen = new Konsumen(data);
            list.add(konsumen);
            
        }
        
        return list;
    }

    @Override
    public void Update(Konsumen object) throws SQLException {
        sql = "UPDATE konsumen SET ktp = ?, nama = ?, jenis_kelamin = ?, " +
                "no_handphone = ?, alamat = ? WHERE id = ?";
        
        preparedStatement = Database.getConnection().prepareStatement(sql);
        preparedStatement.setString(1, object.getKtp());
        preparedStatement.setString(2, object.getNama());
        preparedStatement.setString(3, object.getJenis_kelamin());
        preparedStatement.setString(4, object.getNohape());
        preparedStatement.setString(5, object.getAlamat());
        preparedStatement.setInt(6, Integer.parseInt(object.getId()));
        preparedStatement.executeUpdate();
    }

    @Override
    public void Delete(String id) throws SQLException {
        sql = "DELETE FROM konsumen WHERE id = ?";
        preparedStatement = Database.getConnection().prepareStatement(sql);
        preparedStatement.setInt(1, Integer.parseInt(id));
        preparedStatement.executeUpdate();
        preparedStatement.close();
    }

    @Override
    public List Search(String name) throws SQLException {
        List<Konsumen> list = new ArrayList<>();
//        String[] attributes = Konsumen.getAttributes();
        Map<String, String> data = new HashMap<>();
        Konsumen konsumen;
        sql = "SELECT * FROM konsumen WHERE nama LIKE '%" + name + "%' ORDER BY nama ASC, jenis_kelamin ASC";
        
        statement = Database.getConnection().createStatement();
        ResultSet resultSet = statement.executeQuery(sql);
        
        while (resultSet.next()) {
            
            
//            for (String attribute : attributes) {
//                data.put(resultSet.getString(attribute), resultSet.getString(attribute));
//            }
            data.put("id", resultSet.getString("id"));
            data.put("ktp", resultSet.getString("ktp"));
            data.put("nama", resultSet.getString("nama"));
            data.put("no_handphone", resultSet.getString("no_handphone"));
            data.put("jenis_kelamin", resultSet.getString("jenis_kelamin"));
            data.put("alamat", resultSet.getString("alamat"));
            
            konsumen = new Konsumen(data);
            list.add(konsumen);
            
        }
        
        return list;
    }
    
    public static String getPenyewaId(String ktp) throws SQLException {
        String id = null;
        String sql = "SELECT id FROM konsumen WHERE ktp = " + ktp;
        Statement statement = Database.getConnection().createStatement();
        ResultSet resultSet = statement.executeQuery(sql);
        
        while (resultSet.next()) {
            id = String.valueOf(resultSet.getInt("id"));
        }
        
        return id;
    }
    
}
