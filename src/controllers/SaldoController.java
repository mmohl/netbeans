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
    
    public static List<Saldo> getCoupleSaldo() throws SQLException {
        List<Saldo> source = new ArrayList<>();
        
        String sql = "SELECT pe.id, pe.nama, sum(s.nominal) AS total FROM saldo s\n" +
            "LEFT JOIN pegawai pe ON s.id_pegawai = pe.id\n" +
            "GROUP BY pe.nama, pe.id";
        
        Statement statement = Database.getConnection().createStatement();
        try (ResultSet resultSet = statement.executeQuery(sql)) {
            while (resultSet.next()) {
                Saldo saldo = new Saldo();
                saldo.setId(resultSet.getString("id"));
                saldo.setNama(resultSet.getString("nama"));
                saldo.setTotal(String.valueOf(resultSet.getInt("total")));
                
                source.add(saldo);
            }
        }
        
        return source;
    }
    
    public static List<Saldo> getCoupleSaldoDetail(int idPegawai) throws SQLException {
        List<Saldo> source = new ArrayList<>();
        
        String sql = "SELECT s.tanggal, p.nomor_pesanan,s.nominal\n" +
            "FROM saldo s\n" +
            "LEFT JOIN pesanan p ON p.id = s.id_pesanan\n" +
            "WHERE s.id_pegawai = "+ idPegawai +"AND (s.tanggal BETWEEN '2016-1-1' AND '2016-12-31')";
        
        Statement statement = Database.getConnection().createStatement();
        try (ResultSet resultSet = statement.executeQuery(sql)) {
            while (resultSet.next()) {
                Saldo saldo = new Saldo();
                saldo.setId(resultSet.getString("id"));
                saldo.setNama(resultSet.getString("nama"));
                saldo.setTotal(String.valueOf(resultSet.getInt("total")));
                
                source.add(saldo);
            }
        }
        
        return source;
    }
    
    public static Saldo getDataByInvoice(String invoice) throws SQLException {
        Saldo saldo = new Saldo();
        String sql = "SELECT p.id AS idPesanan, pe.id AS idPegawai, p.total AS total FROM pesanan p\n" +
            "LEFT JOIN pasangan pa ON p.id_pasangan = pa.id\n" +
            "LEFT JOIN pegawai pe ON pa.id_pegawai = pe.id\n" +
            "WHERE p.nomor_pesanan = '" + invoice + "'";
        Statement statement = Database.getConnection().createStatement();
        ResultSet resultSet = statement.executeQuery(sql);
        
       while (resultSet.next()){
            saldo.setId_pegawai(String.valueOf(resultSet.getInt("idPegawai")));
            saldo.setId_pesanan(String.valueOf(resultSet.getInt("idPesanan")));
            saldo.setTotal(String.valueOf(resultSet.getInt("total")));
        }
        
        return saldo;
    }
    
    public static List<Saldo> getSaldoDetailById(String id) throws SQLException {
        java.util.Date date = new java.util.Date();
        String yy, mm;
       
        yy = String.valueOf( date.getYear() + 1900);
        mm = String.valueOf( date.getMonth() + 1);
        
        List<Saldo> source = new ArrayList<>();
        
        String sql = "SELECT s.nominal, p.nomor_pesanan, p.tanggal FROM saldo s\n" +
            "LEFT JOIN pegawai pe ON pe.id = s.id_pegawai\n" +
            "LEFT JOIN pesanan p ON s.id_pesanan = p.id\n" +
            "WHERE (s.tanggal BETWEEN '"+yy+"-"+mm+"-1' AND '"+yy+"-"+mm+"-30') AND s.id_pegawai = '"+id+"'";
        Statement statement = Database.getConnection().createStatement();
        ResultSet resultSet = statement.executeQuery(sql);
        
        while (resultSet.next()) {
            Saldo saldo = new Saldo();
            saldo.setNominal(String.valueOf(resultSet.getInt("nominal")));
            saldo.setNo_pesanan(resultSet.getString("nomor_pesanan"));
            saldo.setTanggal(String.valueOf(resultSet.getDate("tanggal")));
            
            source.add(saldo);
        }
        
        return source;
    }
    
    
}
