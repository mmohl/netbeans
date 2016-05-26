/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package models;

/**
 *
 * @author mmohl
 */
public class Pasangan {
    public final static String[] pasanganAttrib = {"id", "id_pegawai", "id_kategori", "harga", "status"};
    private String id, id_pegawai, id_kategori, harga, status;
    
    public Pasangan() {
        
    }

    public void setHarga(String harga) {
        this.harga = harga;
    }

    public void setId(String id) {
        this.id = id;
    }

    public void setId_kategori(String id_kategori) {
        this.id_kategori = id_kategori;
    }

    public void setId_pegawai(String id_pegawai) {
        this.id_pegawai = id_pegawai;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getHarga() {
        return harga;
    }

    public String getId() {
        return id;
    }

    public String getId_kategori() {
        return id_kategori;
    }

    public String getId_pegawai() {
        return id_pegawai;
    }

    public String getStatus() {
        return status;
    }
    
}
