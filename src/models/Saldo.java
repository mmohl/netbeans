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
public class Saldo {
    
    private String id, id_pesanan, id_pegawai, tanggal, nominal;
    
    public Saldo() {
        
    }
    
    public Saldo(Map<String, String> data) {
        this.id = data.get("id");
        this.id_pegawai = data.get("id_pegawai");
        this.id_pesanan = data.get("id_pesanan");
        this.tanggal = data.get("tanggal");
        this.nominal = data.get("nominal");
    }
    
    public Integer getPercentage() {
        return (Integer.valueOf(nominal) * 70) / 100;
    }

    public String getId() {
        return id;
    }

    public String getId_pegawai() {
        return id_pegawai;
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

    public void setId_pegawai(String id_pegawai) {
        this.id_pegawai = id_pegawai;
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
    
}
