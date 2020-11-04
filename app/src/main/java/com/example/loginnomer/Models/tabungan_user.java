package com.example.loginnomer.Models;

public class tabungan_user {

    private String jenis;
    private String status;
    private String key;
    private String iduser;
    private String jenispengajuan;

    public String getJenispengajuan() {
        return jenispengajuan;
    }

    public void setJenispengajuan(String jenispengajuan) {
        this.jenispengajuan = jenispengajuan;
    }

    public String getJenis() {
        return jenis;
    }

    public void setJenis(String jenis) {
        this.jenis = jenis;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getKey() {
        return key;
    }

    public void setKey(String key) {
        this.key = key;
    }

    public String getIduser() {
        return iduser;
    }

    public void setIduser(String iduser) {
        this.iduser = iduser;
    }

    public tabungan_user (){

    }

    public tabungan_user (String jenis, String status, String iduser, String jenispengajuan){
        this.jenis = jenis;
        this.status = status;
        this.iduser = iduser;
        this.jenispengajuan = jenispengajuan;
    }
}
