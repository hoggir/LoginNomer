package com.example.loginnomer.Models;

public class data_user {

    private String nama;
    private String alamatktp;
    private String alamattgl;
    private String nohp;
    private String key;

    public String getNama() {
        return nama;
    }

    public void setNama(String nama) {
        this.nama = nama;
    }

    public String getAlamatktp() {
        return alamatktp;
    }

    public void setAlamatktp(String alamatktp) {
        this.alamatktp = alamatktp;
    }

    public String getAlamattgl() {
        return alamattgl;
    }

    public void setAlamattgl(String alamattgl) {
        this.alamattgl = alamattgl;
    }

    public String getNohp() {
        return nohp;
    }

    public void setNohp(String nohp) {
        this.nohp = nohp;
    }

    public String getKey() {
        return key;
    }

    public void setKey(String key) {
        this.key = key;
    }

    public data_user (){

    }

    public data_user(String nama, String alamatktp, String alamattgl, String nohp){

        this.alamatktp = alamatktp;
        this.nama = nama;
        this.alamattgl =alamattgl;
        this.nohp = nohp;
    }
}
