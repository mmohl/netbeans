/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package helpers;

import com.google.common.cache.CacheBuilder;
import com.google.common.cache.CacheLoader;
import com.google.common.cache.LoadingCache;
import connection.Database;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.concurrent.TimeUnit;
import models.User;

/**
 *
 * @author mmohl
 */
public class UserCache {
    
    private static LoadingCache<String, User> myCache;
    private static User object;
    
    static {
        
        myCache = CacheBuilder.newBuilder()
                .maximumSize(100)
                .expireAfterAccess(1, TimeUnit.DAYS)
                .build(
                      new CacheLoader<String, User>() {
                        @Override
                        public User load(String k) throws Exception {
                            object = getUser(k);
                            return object;
                        }
                      }
                );
    }
    
    public static LoadingCache<String, User> getLoadingCache() {
        return myCache;
    }
    
    public static User getUser(String key) throws SQLException {
        String sql = "select * from user where username = '" + key +"'";
        Statement statement = Database.getConnection().createStatement();
        ResultSet resultSet = statement.executeQuery(sql);
        User user = new User();
        
        while (resultSet.next()) {
            user.setId(resultSet.getInt("id"));
            user.setUsername(resultSet.getString("username"));
            user.setPassword(resultSet.getString("password"));
            user.setStatus(resultSet.getString("status"));
        }
        
        return user;
    }
    
    public static User getUser() {
        return object;
    }
    
}
