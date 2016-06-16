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
import java.util.List;
import models.Saldo;

/**
 *
 * @author mmohl
 */
public class SaldoController implements CrudInterface<Saldo>{
    private String sql;
    private Statement statement;
    private PreparedStatement preparedStatement;
    
    @Override
    public void Create(Saldo object) throws SQLException {
        sql = "INSERT INTO saldo VALUE (?, ?, ?, ?, ?)";
        preparedStatement = Database.getConnection().prepareStatement(sql);
        preparedStatement.setString(1, null);
        preparedStatement.setInt(2, Integer.parseInt(object.getId_pesanan()));
        preparedStatement.setInt(3, Integer.parseInt(object.getId_pegawai()));
        preparedStatement.setDate(4, Date.valueOf(object.getTanggal()));
        preparedStatement.setInt(5, Integer.parseInt(object.getNominal()));
        preparedStatement.executeUpdate();
        
        preparedStatement.close();
        
    }

    @Override
    public List Read() throws SQLException {
        sql = "SELECT * FROM saldo";
        statement = Database.getConnection().createStatement();
        List<Saldo> source;
        
        try (ResultSet resultSet = statement.executeQuery(sql)) {
            source = new ArrayList<>();
            while (resultSet.next()) {
                Saldo saldo = new Saldo();
                
                saldo.setId(resultSet.getString("id"));
                saldo.setId_pegawai(String.valueOf(resultSet.getInt("id_pegawai")));
                saldo.setId_pesanan(String.valueOf(resultSet.getInt("id_pesanan")));
                saldo.setNominal(String.valueOf(resultSet.getInt("nominal")));
                saldo.setTanggal(String.valueOf(resultSet.getDate("tanggal")));
                
                source.add(saldo);
            }
        }
        
        return source;
    }

    @Override
    public void Update(Saldo object) throws SQLException {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void Delete(String id) throws SQLException {
        sql = "DELETE FROM saldo WHERE id = ?";
        preparedStatement = Database.getConnection().prepareStatement(sql);
        preparedStatement.setInt(1, Integer.parseInt(id));
        preparedStatement.executeUpdate();
        
        preparedStatement.close();
    }

    @Override
    public List Search(String name) throws SQLException {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
    
}
