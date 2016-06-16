/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package models;

import java.util.Map;

/**
 *
 * @author mmohl
 */
public class Kas {
    
    private String id, id_pesanan, tanggal, nominal, no_pesanan, total;
    
    public Kas() {
        
    }
    
    public Kas(Map<String, String> data) {
        this.id = data.get("id");
        this.id_pesanan = data.get("id_pesanan");
        this.tanggal = data.get("tanggal");
        this.nominal = data.get("nominal");
    }
    
    public Integer getPercentage() {
        return (Integer.parseInt(nominal) * 30) / 100;
    }

    public String getId() {
        return id;
    }

    public String getTotal() {
        return total;
    }
    

    public String getNo_pesanan() {
        return no_pesanan;
    }

    public String getId_pesanan() {
        return id_pesanan;
    }

    public String getNominal() {
        return nominal;
    }

    public String getTanggal() {
        return tanggal;
    }

    public void setId(String id) {
        this.id = id;
    }

    public void setId_pesanan(String id_pesanan) {
        this.id_pesanan = id_pesanan;
    }

    public void setNominal(String nominal) {
        this.nominal = nominal;
    }

    public void setTanggal(String tanggal) {
        this.tanggal = tanggal;
    }

    public void setNo_pesanan(String no_pesanan) {
        this.no_pesanan = no_pesanan;
    }

    public void setTotal(String total) {
        this.total = total;
    }
   
}
