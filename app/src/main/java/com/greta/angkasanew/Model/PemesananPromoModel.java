package com.greta.angkasanew.Model;

public class PemesananPromoModel {
    private String harga;
    private String id_pemesanan;
    private String status;
    private String nama_cust;
    private String no_hp;
    private String alamat;
    private String judul_promo;
    private String nama_promo;
    private String bulan;


    public PemesananPromoModel(String judul_promo, String nama_cust, String status, String id_pemesanan,String nama_promo,String no_hp, String alamat, String harga,String bulan) {
        this.harga = harga;
        this.nama_cust = nama_cust;
        this.no_hp = no_hp;
        this.alamat = alamat;
        this.id_pemesanan = id_pemesanan;
        this.judul_promo = judul_promo;
        this.nama_promo = nama_promo;
        this.bulan = bulan;
        this.status = status;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getBulan() {
        return bulan;
    }

    public void setBulan(String bulan) {
        this.bulan = bulan;
    }
    public String getHarga() {
        return harga;
    }

    public void setHarga(String harga) {
        this.harga = harga;
    }

    public String getNama_promo() {
        return nama_promo;
    }

    public void setNama_promo(String nama_promo) {
        this.nama_promo = nama_promo;
    }

    public String getId_pemesanan() {
        return id_pemesanan;
    }

    public void setId_pemesanan(String id_pemesanan) {
        this.id_pemesanan = id_pemesanan;
    }

    public String getNama_cust() {
        return nama_cust;
    }

    public void setNama_cust(String nama_cust) {
        this.nama_cust = nama_cust;
    }

    public String getNo_hp() {
        return no_hp;
    }

    public void setNo_hp(String no_hp) {
        this.no_hp = no_hp;
    }

    public String getAlamat() {
        return alamat;
    }

    public void setAlamat(String alamat) {
        this.alamat = alamat;
    }

    public String getJudul_promo() {
        return judul_promo;
    }

    public void setJudul_promo(String judul_promo) {
        this.judul_promo = judul_promo;
    }
}
