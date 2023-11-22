package com.greta.angkasanew;

public class News {

    String namapackage, namapemesan;

    public News(String namapackage, String namapemesan) {
        this.namapackage = namapackage;
        this.namapemesan = namapemesan;
    }

    public String getNamapackage() {
        return namapackage;
    }

    public void setNamapackage(String namapackage) {
        this.namapackage = namapackage;
    }

    public String getNamapemesan() {
        return namapemesan;
    }

    public void setNamapemesan(String namapemesan) {
        this.namapemesan = namapemesan;
    }
}
