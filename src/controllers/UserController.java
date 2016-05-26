/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controllers;

import interfaces.CrudInterface;
import java.sql.SQLException;

import connection.Database;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;
import models.User;
/**
 *
 * @author mmohl
 */
public class UserController implements CrudInterface<User>{
    
    public final static String ACCESS_DENIED = "Akses Ditolak!"; 

    
    public UserController() {
        
    }
    
    public static String Cek(int id) throws SQLException {
        String status = "";
        String sql = "select status from user where id = " + id;
        Statement statement = Database.getConnection().createStatement();
        ResultSet resultSet = statement.executeQuery(sql);
        
        while (resultSet.next()) {
            status = resultSet.getString("status");
        }
        
        return status;
    }
    
    @Override
    public User Create(User object) throws SQLException {
        String sql = "insert into user values(?,?,?,?)";
        PreparedStatement preparedStatement = Database
                .getConnection()
                .prepareStatement(sql);
        
        preparedStatement.setString(1, null);
        preparedStatement.setString(2, object.getUsername());
        preparedStatement.setString(3, object.getPassword());
        preparedStatement.setString(4, "0");
        preparedStatement.executeUpdate();
        return object;
    }

    @Override
    public java.util.List Read() throws SQLException {
        Statement statement = Database.getConnection().createStatement();
        ResultSet resultSet = statement.executeQuery("select * from mahasiswa");
        java.util.List list = new ArrayList();
        while (resultSet.next()) {
            User o = new User();
            o.setUsername(resultSet.getString("username"));
            o.setPassword(resultSet.getString("password"));
            list.add(o);
        }
        return list;
    }

    @Override
    public void Delete(String id) throws SQLException {
        String sql = "delete from mahasiswa where id=?";
        PreparedStatement preparedStatement = Database
                .getConnection()
                .prepareStatement(sql);
        
        preparedStatement.setString(1, id);
        preparedStatement.executeUpdate();
    }

    @Override
    public void Update(User object) throws SQLException {
        
       String sql = "update  mahasiswa set nim=?, nama=?, alamat=?";
        PreparedStatement preparedStatement = Database
                .getConnection()
                .prepareStatement(sql);
        
        preparedStatement.setString(1, object.getUsername());
        preparedStatement.setString(2, object.getPassword());
        preparedStatement.executeUpdate();
        
    }
    
    public boolean login(String username, String password) throws SQLException {
        
        String sql = "select * from user where username = '"+username+"'";
        Statement statement = Database.getConnection().createStatement();
        ResultSet resultSet = statement.executeQuery(sql);
        String _username = null, _password = null;
        int _id = 0;
        
        while(resultSet.next()) {
            _id = resultSet.getInt("id");
            _username = resultSet.getString("username");
            _password = resultSet.getString("password");
        }
        
        if ( _username.equals(username) && _password.equals(password) ) {
            sql = "update user set status = '"+1+"' where id = " + _id ;
            PreparedStatement preparedStatement = Database.getConnection().prepareStatement(sql);
            preparedStatement.executeUpdate();
            return true;
        } else {
            return false;
        }
    }
    
    public static void Logout(int id) throws SQLException {
        String sql = "update user set status = '" + 0 +"' where id = " + id;
        PreparedStatement preparedStatement = Database.getConnection().prepareStatement(sql);
        preparedStatement.executeUpdate();
    }
    
}
