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
public class DetailPesanan {
    private String id, id_pesanan, uangMuka;

    public void setId(String id) {
        this.id = id;
    }

    public void setId_pesanan(String id_pesanan) {
        this.id_pesanan = id_pesanan;
    }

    public void setUangMuka(String uangMuka) {
        this.uangMuka = uangMuka;
    }

    public String getId() {
        return id;
    }

    public String getId_pesanan() {
        return id_pesanan;
    }

    public String getUangMuka() {
        return uangMuka;
    }
    
}
