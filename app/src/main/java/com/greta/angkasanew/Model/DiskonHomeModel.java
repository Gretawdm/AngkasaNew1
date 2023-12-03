package com.greta.angkasanew.Model;

public class DiskonHomeModel {
    private String judul_promo;
    private String bulan;

    public DiskonHomeModel(String judul_promo, String bulan) {
        this.judul_promo = judul_promo;
        this.bulan = bulan;
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
}
