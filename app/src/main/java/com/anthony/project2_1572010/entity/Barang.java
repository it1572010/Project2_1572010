package com.anthony.project2_1572010.entity;

import android.os.Parcel;
import android.os.Parcelable;

/**
 * @author Anthony (1572010)
 */

public class Barang implements Parcelable {
    private int hargaBeli;
    private int hargaJual;
    private String idBarang;
    private String namaBarang;
    private String key;
    private int stock;

    public Barang() {
    }

    public Barang(Barang barang) {
        hargaBeli = barang.getHargaBeli();
        hargaJual = barang.getHargaJual();
        idBarang = barang.getIdBarang();
        namaBarang = barang.getNamaBarang();
        stock = barang.getStock();
    }

    protected Barang(Parcel in) {
        hargaBeli = in.readInt();
        hargaJual = in.readInt();
        idBarang = in.readString();
        namaBarang = in.readString();
        stock = in.readInt();
    }

    public static final Creator<Barang> CREATOR = new Creator<Barang>() {
        @Override
        public Barang createFromParcel(Parcel in) {
            return new Barang(in);
        }

        @Override
        public Barang[] newArray(int size) {
            return new Barang[size];
        }
    };

    public int getHargaBeli() {
        return hargaBeli;
    }

    public void setHargaBeli(int hargaBeli) {
        this.hargaBeli = hargaBeli;
    }

    public int getHargaJual() {
        return hargaJual;
    }

    public void setHargaJual(int hargaJual) {
        this.hargaJual = hargaJual;
    }

    public String getIdBarang() {
        return idBarang;
    }

    public void setIdBarang(String idBarang) {
        this.idBarang = idBarang;
    }

    public String getNamaBarang() {
        return namaBarang;
    }

    public void setNamaBarang(String namaBarang) {
        this.namaBarang = namaBarang;
    }

    public int getStock() {
        return stock;
    }

    public void setStock(int stock) {
        this.stock = stock;
    }

    public String getKey() {
        return key;
    }

    public void setKey(String key) {
        this.key = key;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeInt(hargaBeli);
        dest.writeInt(hargaJual);
        dest.writeString(idBarang);
        dest.writeString(namaBarang);
        dest.writeInt(stock);
    }
}
