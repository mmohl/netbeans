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
import models.Kategori;

/**
 *
 * @author mmohl
 */
public class KategoriController implements CrudInterface<Kategori>{
    private String sql;
    
    @Override
    public Kategori Create(Kategori object) throws SQLException {
        sql = "insert into kategori values(?,?,?)";
        PreparedStatement preparedStatement = Database.getConnection().prepareStatement(sql);
        
        preparedStatement.setString(1, null);
        preparedStatement.setString(2, object.getKategori());
        preparedStatement.setInt(3, object.getHarga());
        preparedStatement.executeUpdate();
        
        return object;
    }

    @Override
    public java.util.List Read() throws SQLException {
        sql = "select * from kategori";
        Statement statement = Database.getConnection().createStatement();
        ResultSet resultSet = statement.executeQuery(sql);
        java.util.List list = new ArrayList();
        
        while (resultSet.next()) {
            Kategori k = new Kategori();
            
            k.setId(resultSet.getInt("id"));
            k.setHarga(resultSet.getInt("harga"));
            k.setKategori(resultSet.getString("kategori"));
            list.add(k);
        }
        
        return list;
        
    }

    @Override
    public void Update(Kategori object) throws SQLException {
        sql = "update kategori set kategori = ?, harga = ? where id = ?";
        
        PreparedStatement preparedStatement = Database.getConnection().prepareStatement(sql);
        preparedStatement.setString(1, object.getKategori());
        preparedStatement.setInt(2, object.getHarga());
        preparedStatement.setInt(3, object.getId());
        preparedStatement.executeUpdate();
    }

    @Override
    public void Delete(String id) throws SQLException {
        sql = "delete from kategori where id = ?";
        
        PreparedStatement preparedStatement = Database.getConnection().prepareStatement(sql);
        preparedStatement.setString(1, id);
        preparedStatement.executeUpdate();
    }

    @Override
    public java.util.List Search(String name) throws SQLException {
        Statement statement = Database.getConnection().createStatement();
        ResultSet resultSet = statement.executeQuery("select kategori, harga from kategori where kategori like '%" + name +"%'");
        java.util.List list = new ArrayList();
        while (resultSet.next()) {
            Kategori o = new Kategori();
            o.setHarga(resultSet.getInt("harga"));
            o.setKategori(resultSet.getString("kategori"));
            list.add(o);
        }
        return list;
    }
    
    public static Integer getIdUser(String key) throws SQLException {
        int id = 0;
        Map<String, Integer> data = new HashMap<String, Integer>();
        String sql = "select id, kategori from kategori";
        
        Statement statement = Database.getConnection().createStatement();
        ResultSet resultSet = statement.executeQuery(sql);
        
        while (resultSet.next()) {
            data.put(resultSet.getString("kategori"), resultSet.getInt("id"));
        }
        
        id = data.get(key);
        return id;
    }
    
}
