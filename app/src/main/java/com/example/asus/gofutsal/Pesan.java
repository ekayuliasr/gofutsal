package com.example.asus.gofutsal;

public class Pesan {
    private int id;
    private String nama;
    private String hp;
    private String pemesanan;
    private String tanggal;

    public Pesan(){

    }

    public Pesan(int id, String nama, String hp, String pemesanan, String tanggal){
        this.id = id;
        this.nama = nama;
        this.hp = hp;
        this.pemesanan = pemesanan;
        this.tanggal = tanggal;
    }

    public int get_id(){
        return this.id;
    }

    public void set_id(int id){
        this.id = id;
    }

    public String getNama(){
        return nama;
    }

    public void setNama(String nama){
        this.nama = nama;
    }

    public String getHp(){
        return hp;
    }

    public void setHp(String hp){
        this.hp = hp;
    }

    public String getPemesanan(){
        return pemesanan;
    }

    public void setPemesanan(String pemesanan){
        this.pemesanan = pemesanan;
    }
    public String getTanggal(){
        return tanggal;
    }

    public void setTanggal(String tanggal){
        this.tanggal = tanggal;
    }

}


