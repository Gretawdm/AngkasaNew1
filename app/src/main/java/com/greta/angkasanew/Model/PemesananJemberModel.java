package com.greta.angkasanew.Model;

import androidx.annotation.NonNull;

public class PemesananJemberModel {
    private String namacustomer;
    private String namapackage;
    private String tanggal;
    private String id_pemesanan;
    private String status;
    private String no_hp;
    private String nama_layout;
    private String alamat;
    private String quota;
    private String unlimited;
    private String harga_qouta;
    private String harga_unlimited;
    String bukti_pembayaran;

    public PemesananJemberModel(String namacustomer, String namapackage, String tanggal, String status, String id_pemesanan, String no_hp, String nama_layout, String alamat, String quota, String unlimited, String harga_qouta, String harga_unlimited, String bukti_pembayaran) {
        this.namacustomer = namacustomer;
        this.namapackage = namapackage;
        this.tanggal = tanggal;
        this.status = status;
        this.id_pemesanan = id_pemesanan;
        this.no_hp = no_hp;
        this.nama_layout = nama_layout;
        this.alamat = alamat;
        this.quota = quota;
        this.unlimited = unlimited;
        this.harga_qouta = harga_qouta;
        this.harga_unlimited = harga_unlimited;
        this.bukti_pembayaran = bukti_pembayaran;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getNamacustomer() {
        return namacustomer;
    }

    public void setNamacustomer(String namacustomer) {
        this.namacustomer = namacustomer;
    }

    public String getNamapackage() {
        return namapackage;
    }

    public void setNamapackage(String namapackage) {
        this.namapackage = namapackage;
    }

    public String getTanggal() {
        return tanggal;
    }

    public void setTanggal(String tanggal) {
        this.tanggal = tanggal;
    }

    public String getId_pemesanan() {
        return id_pemesanan;
    }

    public void setId_pemesanan(String id_pemesanan) {
        this.id_pemesanan = id_pemesanan;
    }

    public String getNo_hp() {
        return no_hp;
    }

    public void setNo_hp(String no_hp) {
        this.no_hp = no_hp;
    }

    public String getNama_layout() {
        return nama_layout;
    }

    public void setNama_layout(String nama_layout) {
        this.nama_layout = nama_layout;
    }

    public String getAlamat() {
        return alamat;
    }

    public void setAlamat(String alamat) {
        this.alamat = alamat;
    }

    public String getQuota() {
        return quota;
    }

    public void setQuota(String quota) {
        this.quota = quota;
    }

    public String getUnlimited() {
        return unlimited;
    }

    public void setUnlimited(String unlimited) {
        this.unlimited = unlimited;
    }

    public String getHarga_qouta() {
        return harga_qouta;
    }

    public void setHarga_qouta(String harga_qouta) {
        this.harga_qouta = harga_qouta;
    }

    public String getHarga_unlimited() {
        return harga_unlimited;
    }

    public void setHarga_unlimited(String harga_unlimited) {
        this.harga_unlimited = harga_unlimited;
    }

    public String getBukti_pembayaran() {
        return bukti_pembayaran;
    }

    public void setBukti_pembayaran(String bukti_pembayaran) {
        this.bukti_pembayaran = bukti_pembayaran;
    }
}
