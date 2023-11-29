package com.greta.angkasanew.Model;

public class PemesananSponsorModel {
    private String namacustomer;
    private String tanggal;
    private String id_pemesanan;
    private String no_hp;
    private String alamat;
    private String proposal;

    public PemesananSponsorModel(String namacustomer, String tanggal, String id_pemesanan, String no_hp, String alamat, String proposal) {
        this.namacustomer = namacustomer;
        this.tanggal = tanggal;
        this.id_pemesanan = id_pemesanan;
        this.no_hp = no_hp;
        this.alamat = alamat;
        this.proposal = proposal;
    }

    public String getNamacustomer() {
        return namacustomer;
    }

    public void setNamacustomer(String namacustomer) {
        this.namacustomer = namacustomer;
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

    public String getAlamat() {
        return alamat;
    }

    public void setAlamat(String alamat) {
        this.alamat = alamat;
    }

    public String getProposal() {
        return proposal;
    }

    public void setProposal(String proposal) {
        this.proposal = proposal;
    }
}
