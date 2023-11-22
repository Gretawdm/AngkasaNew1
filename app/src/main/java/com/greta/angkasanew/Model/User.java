package com.greta.angkasanew.Model;

import android.os.Build;

import androidx.annotation.RequiresApi;

import java.io.Serializable;
import java.util.Base64;

public class User implements Serializable {
    private String nama_lengkap;
    private String email;
    private String no_hp;
    private String gender;
    private String password;
    private String jabatan;
    private String image;

    @RequiresApi(api = Build.VERSION_CODES.O)
    public User(String nama_lengkap, String email, String no_hp, String gender, String password, String jabatan, byte[] image) {
        this.nama_lengkap = nama_lengkap;
        this.email = email;
        this.no_hp = no_hp;
        this.gender = gender;
        this.password = password;
        this.jabatan = jabatan;
        this.image = Base64.getEncoder().encodeToString(image);
    }

    public User(String nama, String email, String noHp, String password, String gender) {
    }

    public String getNama_lengkap() {
        return nama_lengkap;
    }

    public void setNama_lengkap(String nama_lengkap) {
        this.nama_lengkap = nama_lengkap;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getNo_hp() {
        return no_hp;
    }

    public void setNo_hp(String no_hp) {
        this.no_hp = no_hp;
    }

    public String getGender() {
        return gender;
    }

    public void setGender(String gender) {
        this.gender = gender;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getJabatan() {
        return jabatan;
    }

    public void setJabatan(String jabatan) {
        this.jabatan = jabatan;
    }

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }

    public User(String nama_lengkap, String email, String no_hp, String gender, String password, String jabatan, String image) {
        this.nama_lengkap = nama_lengkap;
        this.email = email;
        this.no_hp = no_hp;
        this.gender = gender;
        this.password = password;
        this.jabatan = jabatan;
        this.image = image;
    }

    @RequiresApi(api = Build.VERSION_CODES.O)
    public byte[] getImageByte() {
        return Base64.getDecoder().decode(image);
    }

    @RequiresApi(api = Build.VERSION_CODES.O)
    public void setImageByte(byte[] imageData) {
        this.image = Base64.getEncoder().encodeToString(imageData);
    }
}
