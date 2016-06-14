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
    
    private String id, no_pesanan, nama, tgl, 
            penyewa, ktp, pasangan, lama, nohape, 
            status, harga, uangMuka, peJeKe, alamat, 
            pembayaran, idPenyewa, total;
    
    public Pesanan() {
        
    }

    public void setTotal(String total) {
        this.total = total;
    }

    public String getTotal() {
        return total;
    }

    public void setIdPenyewa(String idPenyewa) {
        this.idPenyewa = idPenyewa;
    }

    public String getIdPenyewa() {
        return idPenyewa;
    }

    public void setPembayaran(String pembayaran) {
        this.pembayaran = pembayaran;
    }
    

    public String getPembayaran() {
        return pembayaran;
    }

    public void setNo_pesanan(String no_pesanan) {
        this.no_pesanan = no_pesanan;
    }

    public String getNo_pesanan() {
        return no_pesanan;
    }

    public void setAlamat(String alamat) {
        this.alamat = alamat;
    }

    public String getAlamat() {
        return alamat;
    }

    public void setNama(String nama) {
        this.nama = nama;
    }

    public String getNama() {
        return nama;
    }

    public void setPeJeKe(String peJeKe) {
        this.peJeKe = peJeKe;
    }

    public String getPeJeKe() {
        return peJeKe;
    }

    public void setUangMuka(String uangMuka) {
        this.uangMuka = uangMuka;
    }

    public String getUangMuka() {
        return uangMuka;
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
