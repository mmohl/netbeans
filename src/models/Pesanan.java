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
public class Pesanan {
    
    private String id, tgl, penyewa, ktp, pasangan, lama, nohape, status, harga;
    
    public Pesanan() {
        
    }

    public void setHarga(String harga) {
        this.harga = harga;
    }

    public String getHarga() {
        return harga;
    }
    

    public void setNohape(String nohape) {
        this.nohape = nohape;
    }

    public String getNohape() {
        return nohape;
    }
    
    public void setLama(String lama) {
        this.lama = lama;
    }

    public String getLama() {
        return lama;
    }
    
    

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getKtp() {
        return ktp;
    }

    public void setKtp(String ktp) {
        this.ktp = ktp;
    }


    public String getPasangan() {
        return pasangan;
    }

    public void setPasangan(String pasangan) {
        this.pasangan = pasangan;
    }

    public String getPenyewa() {
        return penyewa;
    }

    public void setPenyewa(String penyewa) {
        this.penyewa = penyewa;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getTgl() {
        return tgl;
    }

    public void setTgl(String tgl) {
        this.tgl = tgl;
    }
    
}
