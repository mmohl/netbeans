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
import java.util.List;
import models.DetailPesanan;

/**
 *
 * @author mmohl
 */
public class DetailPesananController implements CrudInterface<DetailPesanan>{
    
    private String sql;
    
    @Override
    public DetailPesanan Create(DetailPesanan object) throws SQLException {
        sql = "insert into pesanan values (?, ?, ?)";
        PreparedStatement preparedStatement = Database.getConnection().prepareStatement(sql);
        
        preparedStatement.setString(1, object.getId());
        preparedStatement.setInt(2, Integer.parseInt(object.getId_pesanan()));
        preparedStatement.setInt(3, Integer.parseInt(object.getUangMuka()));
        
        preparedStatement.executeUpdate();
        
        return object;
    }

    @Override
    public List Read() throws SQLException {
        sql = "select * from detail_pesanan";
        Statement statement = Database.getConnection().createStatement();
        ResultSet resultSet = statement.executeQuery(sql);
        List list = new ArrayList();
        
        while (resultSet.next()) {
            DetailPesanan detail = new DetailPesanan();
            
            detail.setId(String.valueOf(resultSet.getInt("id")));
            detail.setId_pesanan(String.valueOf(resultSet.getInt("id_pasangan")));
            detail.setUangMuka(String.valueOf(resultSet.getInt("uang_muka")));
            
            list.add(detail);
        }
        
        return list;
    }

    @Override
    public void Update(DetailPesanan object) throws SQLException {
        sql = "update detail_pesanan set id_pesanan = ?, uang_muka = ? where id = ?";
        PreparedStatement preparedStatement = Database.getConnection().prepareStatement(sql);
        
        preparedStatement.setInt(3, Integer.parseInt(object.getId()));
        preparedStatement.setInt(1, Integer.parseInt(object.getId_pesanan()));
        preparedStatement.setInt(2, Integer.parseInt(object.getUangMuka()));
        
        preparedStatement.executeUpdate();
    }

    @Override
    public void Delete(String id) throws SQLException {
         sql = "update detail_pesanan set id_pesanan = ?, uang_muka = ? where id = ?";
        PreparedStatement preparedStatement = Database.getConnection().prepareStatement(sql);
        
        preparedStatement.setInt(3, Integer.parseInt(id));
        
        preparedStatement.executeUpdate();
    }
    
}
