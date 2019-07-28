package com.bearcats.tamago;

public class RewardData_Model {
    private String nama,url;
    private int harga;

    public RewardData_Model(String nama, String url, int harga) {
        this.nama = nama;
        this.url = url;
        this.harga = harga;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public int getHarga() {
        return harga;
    }

    public void setHarga(int harga) {
        this.harga = harga;
    }

    public String getNama() {
        return nama;
    }

    public void setNama(String nama) {
        this.nama = nama;
    }
}
