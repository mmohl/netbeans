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
import java.util.HashMap;
import java.util.Map;
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
    public void Create(User object) throws SQLException {
        String sql = "insert into user values(?,?,?,?)";
        PreparedStatement preparedStatement = Database
                .getConnection()
                .prepareStatement(sql);
        
        preparedStatement.setString(1, null);
        preparedStatement.setString(2, object.getUsername());
        preparedStatement.setString(3, object.getPassword());
        preparedStatement.setString(4, "0");
        preparedStatement.executeUpdate();
    }

    @Override
    public java.util.List Read() throws SQLException {
        Statement statement = Database.getConnection().createStatement();
        ResultSet resultSet = statement.executeQuery("select username, password, status from user");
        java.util.List list = new ArrayList();
        while (resultSet.next()) {
            User o = new User();
            o.setUsername(resultSet.getString("username"));
            o.setPassword(resultSet.getString("password"));
            o.setStatus(resultSet.getString("status"));
            list.add(o);
        }
        return list;
    }

    @Override
    public void Delete(String name) throws SQLException {
        String sql = "delete from user where username=?";
        PreparedStatement preparedStatement = Database
                .getConnection()
                .prepareStatement(sql);
        
        preparedStatement.setString(1, name);
        preparedStatement.executeUpdate();
    }

    @Override
    public void Update(User object) throws SQLException {
        
       String sql = "update  user set username=?, password=? where id=?";
        PreparedStatement preparedStatement = Database
                .getConnection()
                .prepareStatement(sql);
        
        preparedStatement.setString(1, object.getUsername());
        preparedStatement.setString(2, object.getPassword());
        preparedStatement.setInt(3, object.getId());
        preparedStatement.executeUpdate();
        
    }
    
    public boolean login(String username, String password) throws SQLException {
        
        String sql = "SELECT * FROM user WHERE username = '"+username+"'";
        Statement statement = Database.getConnection().createStatement();
        ResultSet resultSet = statement.executeQuery(sql);
        String _username = null, _password = null;
        int _id = 0;
        Boolean stat;
        
        if (!resultSet.next()) {
            stat = false;
        } else {
            
            do {
                _id = resultSet.getInt("id");
                _username = resultSet.getString("username");
                _password = resultSet.getString("password");
            }   while(resultSet.next());
            
            if ( _password.equals(password) ) {
                sql = "update user set status = '"+1+"' where id = " + _id ;
                PreparedStatement preparedStatement = Database.getConnection().prepareStatement(sql);
                preparedStatement.executeUpdate();
                stat =  true;
            } else {
                stat = false;
            }
        }

      return stat;
    }
    
    public static void Logout(int id) throws SQLException {
        String sql = "update user set status = '" + 0 +"' where id = " + id;
        PreparedStatement preparedStatement = Database.getConnection().prepareStatement(sql);
        preparedStatement.executeUpdate();
    }
    
    public static Integer getIdUser(String key) throws SQLException {
        int id = 0;
        Map<String, Integer> data = new HashMap<String, Integer>();
        String sql = "select id, username from user";
        
        Statement statement = Database.getConnection().createStatement();
        ResultSet resultSet = statement.executeQuery(sql);
        
        while (resultSet.next()) {
            data.put(resultSet.getString("username"), resultSet.getInt("id"));
        }
        
        id = data.get(key);
        return id;
    }
    
    @Override
    public java.util.List Search(String name) throws SQLException {
        Statement statement = Database.getConnection().createStatement();
        ResultSet resultSet = statement.executeQuery("select username, password from user where username like '%" + name +"%'");
        java.util.List list = new ArrayList();
        while (resultSet.next()) {
            User o = new User();
            o.setUsername(resultSet.getString("username"));
            o.setPassword(resultSet.getString("password"));
            list.add(o);
        }
        return list;
    }
    
}
