/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package models;

import java.util.List;
import java.util.Map;

/**
 *
 * @author mmohl
 */
public class User extends Validator{
    
    private String[] userAttrib = new String[]{"username", "password"};
    private String username, password, password2, status;
    private int id;

    @Override
    protected final void inisialisasi() {
        attributes = userAttrib;
        beValidate.put("username", new String[]{"3", "25", username});
        beValidate.put("password", new String[]{"6", "50", password});
    }
    
    public User() {}
    
    public User(String username, String password) {
        this.username = username;
        this.password = password;
        inisialisasi();
    }
    
    public User(String username, String password, String password2) {
        this.username = username;
        this.password = password;
        this.password2 = password2;
        inisialisasi();
    }
    
    public User(Map<String, String> model) {
        this.username = model.get("username");
        this.password = model.get("password");
        this.password2 = model.get("password2");
        inisialisasi();
    }

    public List<String> getErrorList() {
        return errorList;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getStatus() {
        return status;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getId() {
        return id;
    }
    

    public void setUsername(String username) {
        this.username = username;
    }

    public String getUsername() {
        return username;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword2(String password2) {
        this.password2 = password2;
    }

    public String getPassword2() {
        return password2;
    }
    
    public boolean banding() {
        return this.password.equals(this.password2);
    }
    
}
