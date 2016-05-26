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
import models.Pemesanan;

/**
 *
 * @author mmohl
 */
public class PemesananController implements CrudInterface<Pemesanan>{
    
    private String sql;

    @Override
    public Pemesanan Create(Pemesanan object) throws SQLException {
        sql = "insert into pesanan values (?, ?, ?, ?, ?, ?, ?, ?)";
        PreparedStatement preparedStatement = Database.getConnection().prepareStatement(sql);
        
        preparedStatement.setString(1, object.getId());
        preparedStatement.setInt(2, Integer.parseInt(object.getPasangan()));
        preparedStatement.setDate(3, Date.valueOf(object.getTgl()));
        preparedStatement.setInt(4, Integer.parseInt(object.getLama()));
        preparedStatement.setString(5, object.getPenyewa());
        preparedStatement.setString(6, object.getKtp());
        preparedStatement.setString(7, object.getNohape());
        preparedStatement.setString(8, object.getMetode());
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
            Pemesanan pesanan = new Pemesanan();
            
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
    public void Update(Pemesanan object) throws SQLException {
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
    
}
