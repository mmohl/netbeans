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
public class Konsumen {
    
    private String id, ktp, nama, jenis_kelamin, no_handphone, alamat;
    private String[] attributes = {"id", "ktp", "nama", "jenis_kelamin", "no_handphone", "alamat"};
    private static String[] attribute = {"id", "ktp", "nama", "jenis_kelamin", "no_handphone", "alamat"};
    
    public Konsumen() {
        
    }
    
    public Konsumen(Map<String, String> data) {
        
        this.id = data.get("id");
        this.alamat = data.get("alamat");
        this.jenis_kelamin = data.get("jenis_kelamin");
        this.ktp = data.get("ktp");
        this.nama = data.get("nama");
        this.no_handphone = data.get("no_handphone");
        
    }

    public static String[] getAttributes() {
        return attribute;
    }
 

    public void setId(String id) {
        this.id = id;
    }

    public String getId() {
        return id;
    }

    public void setNama(String nama) {
        this.nama = nama;
    }

    public String getNama() {
        return nama;
    }

    public void setJenis_kelamin(String jenis_kelamin) {
        this.jenis_kelamin = jenis_kelamin;
    }

    public String getJenis_kelamin() {
        return jenis_kelamin;
    }

    public void setAlamat(String alamat) {
        this.alamat = alamat;
    }

    public String getAlamat() {
        return alamat;
    }

    public void setKtp(String ktp) {
        this.ktp = ktp;
    }

    public String getKtp() {
        return ktp;
    }

    public void setNohape(String nohape) {
        this.no_handphone = nohape;
    }

    public String getNohape() {
        return no_handphone;
    }
    

    @Override
    public String toString() {
        return this.nama;
    }
    
}
