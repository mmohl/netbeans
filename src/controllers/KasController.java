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
import models.Kas;

/**
 *
 * @author mmohl
 */
public class KasController implements CrudInterface<Kas>{

    private String sql;
    private Statement statement;
    private PreparedStatement preparedStatement;
    
    @Override
    public void Create(Kas object) throws SQLException {
        sql = "INSERT INTO kas VAlUE (?, ?, ?, ?)";
        preparedStatement = Database.getConnection().prepareStatement(sql);
        preparedStatement.setString(1, null);
        preparedStatement.setInt(2, Integer.parseInt(object.getId_pesanan()));
        preparedStatement.setDate(3, Date.valueOf(object.getTanggal()));
        preparedStatement.setInt(4, object.getPercentage());
        preparedStatement.executeUpdate();
        
    }

    @Override
    public List Read() throws SQLException {
        sql = "SELECT k.*, p.nomor_pesanan FROM kas k\n" +
                "LEFT JOIN pesanan p ON k.id_pesanan = p.id ";
        statement = Database.getConnection().createStatement();
        List<Kas> source;
        
        try (ResultSet resultSet = statement.executeQuery(sql)) {
            source = new ArrayList<>();
            while (resultSet.next()) {
                Kas kas = new Kas();
                kas.setId(resultSet.getString("id"));
                kas.setNo_pesanan(resultSet.getString("nomor_pesanan"));
                kas.setId_pesanan(String.valueOf(resultSet.getInt("id_pesanan")));
                kas.setNominal(String.valueOf(resultSet.getInt("nominal")));
                kas.setTanggal(String.valueOf(resultSet.getDate("tanggal")));
//                kas.setTotal(String.valueOf(resultSet.getInt("total")));
                
                source.add(kas);
            }
        }
        
        return source;
    }

    @Override
    public void Update(Kas object) throws SQLException {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void Delete(String id) throws SQLException {
        sql = "DELETE FROM kas where id = ?";
        preparedStatement = Database.getConnection().prepareStatement(sql);
        preparedStatement.setInt(1, Integer.valueOf(id));
        preparedStatement.executeUpdate();
    }

    @Override
    public List Search(String name) throws SQLException {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
    
    public static Kas getDataByInvoice(String invoice) throws SQLException {
        Kas kas = new Kas();
        String sql = "SELECT p.id AS idPesanan,  p.total AS total FROM pesanan p\n" +
            "LEFT JOIN pasangan pa ON p.id_pasangan = pa.id\n" +
            "LEFT JOIN pegawai pe ON pa.id_pegawai = pe.id\n" +
            "WHERE p.nomor_pesanan = '" + invoice + "'";
        Statement statement = Database.getConnection().createStatement();
        ResultSet resultSet = statement.executeQuery(sql);
        
        while (resultSet.next()){
            kas.setId_pesanan(String.valueOf(resultSet.getInt("idPesanan")));
            kas.setTotal(String.valueOf(resultSet.getInt("total")));
        }
        
        return kas;
    }
    
}
