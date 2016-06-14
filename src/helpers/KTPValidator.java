/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package helpers;

import connection.Database;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author mmohl
 */
public class KTPValidator {
    
    private boolean isValid;
    private String ktp;
    private List<String> source;
    
    public KTPValidator(String ktp) {
        this.ktp = ktp;
         source = new ArrayList<>();
        try {
            getResource();
        } catch (SQLException ex) {
            Logger.getLogger(KTPValidator.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    private void getResource() throws SQLException {
        String sql = "SELECT pegawai.ktp FROM pegawai UNION SELECT konsumen.ktp FROM konsumen";
        Statement statement = Database.getConnection().createStatement();
        ResultSet resultSet = statement.executeQuery(sql);
        
        while (resultSet.next()) {
            source.add(resultSet.getString("ktp"));
        }
    }
    
    public boolean validate() {
        isValid = source.contains(ktp);
        return isValid;
    }
    
    
    
}
