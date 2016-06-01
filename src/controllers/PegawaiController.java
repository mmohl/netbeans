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
import models.Pegawai;

/**
 *
 * @author mmohl
 */
public class PegawaiController implements CrudInterface<Pegawai>{
    
    private String sql;
    private String[] attributes = {"id", "nama", "no_handphone", "pekerjaan", "jenis_kelamin", "foto", "tanggal_lahir", "ktp"};
    private Map<String, String> map = new HashMap<String, String>();

    @Override
    public Pegawai Create(Pegawai object) throws SQLException {
        sql = "insert into pegawai values (?, ?, ?, ?, ?, ?, ?, ?)";
        PreparedStatement preparedStatement = Database.getConnection().prepareStatement(sql);
        
        preparedStatement.setString(1, null);
        preparedStatement.setString(2, object.getNama());
        preparedStatement.setString(3, object.getGender());
        preparedStatement.setDate(4, Date.valueOf(object.getTanggal_lahir()));
        preparedStatement.setString(5, object.getKtp());
        preparedStatement.setString(6, object.getNo_handphone());
        preparedStatement.setString(7, object.getFoto());
        preparedStatement.setString(8, object.getPekerjaan());
        preparedStatement.executeUpdate();
        
        return object;
    }

    @Override
    public java.util.List Read() throws SQLException {
        sql = "select * from pegawai ";
        Statement statement = Database.getConnection().createStatement();
        ResultSet resultSet = statement.executeQuery(sql);
        java.util.List list = new ArrayList<Pegawai>();
        int i = 0;
        
        while(resultSet.next()) {
          
            for (String attribute : attributes) {
                map.put(attribute, resultSet.getString(attribute));
            }
            Pegawai pegawai = new Pegawai(map);
            list.add(pegawai);
        }
        return list;
    }

    @Override
    public void Update(Pegawai object) throws SQLException {
        sql = "insert into pegawai values (?, ?, ?, ?, ?, ?, ?, ?)";
        PreparedStatement preparedStatement = Database.getConnection().prepareStatement(sql);
        preparedStatement.setString(1, null);
        preparedStatement.setString(2, object.getNama());
        preparedStatement.setString(3, object.getNo_handphone());
        preparedStatement.setString(4, object.getPekerjaan());
        preparedStatement.setString(5, object.getTanggal_lahir());
        preparedStatement.setString(6, object.getKtp());
        preparedStatement.setString(7, object.getGender());
        preparedStatement.setString(8, object.getFoto());
        preparedStatement.executeUpdate();
    }

    @Override
    public void Delete(String id) throws SQLException {
        sql = "delete from pegawai where id = ? ";
        PreparedStatement preparedStatement = Database.getConnection().prepareStatement(sql);
        preparedStatement.setInt(1, Integer.parseInt(id));
        preparedStatement.executeUpdate();
    }

    @Override
    public List Search(String name) throws SQLException {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
    
}
