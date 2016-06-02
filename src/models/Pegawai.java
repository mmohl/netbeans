/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package models;

import java.util.HashMap;
import java.util.Map;

/**
 *
 * @author mmohl
 */
public class Pegawai {
    Map<String, String> pegawaiMap = new HashMap<String, String>();
    private String[] pegawaiAttrib = {"id", "nama", "no_handphone", "jenis_kelamin", "gambar", "tanggal_lahir", "ktp"};
    private String id, nama, no_handphone, gender, foto, tanggal_lahir, ktp;
    
    public Pegawai() {
       
    }
    
    public Pegawai(String nama) {
       this.nama = nama;
    }
    
    public Pegawai(Map map) {
       id = (String) map.get(pegawaiAttrib[0]);
       nama = (String) map.get(pegawaiAttrib[1]);
       no_handphone = (String) map.get(pegawaiAttrib[2]);
       gender = (String) map.get(pegawaiAttrib[3]);
       foto = (String) map.get(pegawaiAttrib[4]);
       tanggal_lahir = (String) map.get(pegawaiAttrib[5]);
       ktp = (String) map.get(pegawaiAttrib[6]);
    }

    public void setId(String id) {
        this.id = id;
    }

    public void setFoto(String foto) {
        this.foto = foto;
    }

    public void setGender(String gender) {
        this.gender = gender;
    }

    public void setKtp(String ktp) {
        this.ktp = ktp;
    }

    public void setNama(String nama) {
        this.nama = nama;
    }

    public void setNo_handphone(String no_handphone) {
        this.no_handphone = no_handphone;
    }

    public void setTanggal_lahir(String tanggal_lahir) {
        this.tanggal_lahir = tanggal_lahir;
    }

    public String getFoto() {
        return foto;
    }

    public String getGender() {
        return gender;
    }

    public String getId() {
        return id;
    }

    public String getKtp() {
        return ktp;
    }

    public String getNama() {
        return nama;
    }

    public String getNo_handphone() {
        return no_handphone;
    }

    public String getTanggal_lahir() {
        return tanggal_lahir;
    }
    
    @Override
    public String toString() {
        return nama;
    }

}
