package com.greta.angkasanew.Model;

public class DiskonModel {
    private String judul_promo;
    private String bulan;
    private String harga_promo;
    private String nama_promo;
    private Integer id_promo;

    public Integer getId_promo() {
        return id_promo;
    }

    public void setId_promo(Integer id_promo) {
        this.id_promo = id_promo;
    }

    public DiskonModel(Integer id_promo, String bulan, String judul_promo, String harga_promo, String nama_promo) {
        this.bulan = bulan;
        this.judul_promo = judul_promo;
        this.harga_promo = harga_promo;
        this.nama_promo = nama_promo;
        this.id_promo = id_promo;
    }

    public String getJudul_promo() {
        return judul_promo;
    }

    public void setJudul_promo(String judul_promo) {
        this.judul_promo = judul_promo;
    }

    public String getBulan() {
        return bulan;
    }

    public void setBulan(String bulan) {
        this.bulan = bulan;
    }

    public String getHarga_promo() {
        return harga_promo;
    }

    public void setHarga_promo(String harga_promo) {
        this.harga_promo = harga_promo;
    }

    public String getNama_promo() {
        return nama_promo;
    }

    public void setNama_promo(String nama_promo) {
        this.nama_promo = nama_promo;
    }
}
