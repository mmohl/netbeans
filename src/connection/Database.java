/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package connection;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author mmohl
 */
public class Database {
    
    private static final String dbname = "pvl_tb_kel";
    private static final String dbhost = "localhost";
    private static final String dbuser = "root";
    private static final String dbpass = "";

    private static Connection conn;

    public static Connection getConnection(){
        if(conn == null){
            try {
                DriverManager.registerDriver(new com.mysql.jdbc.Driver());
                conn = DriverManager.getConnection("jdbc:mysql://"+ dbhost+ ":3306/"+ dbname, dbuser, dbpass);
            } catch (SQLException ex) {
                Logger.getLogger(Database.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
        return conn;
    }
}
