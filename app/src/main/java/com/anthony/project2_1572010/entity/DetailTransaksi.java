package com.anthony.project2_1572010.entity;

import android.os.Parcel;
import android.os.Parcelable;

import java.util.List;

public class DetailTransaksi implements Parcelable {
    private List<Barang> barang;
    private Transaksi transaksi;
    private String key;

    public DetailTransaksi() {
    }

    protected DetailTransaksi(Parcel in) {
        barang = in.createTypedArrayList(Barang.CREATOR);
        transaksi = in.readParcelable(Transaksi.class.getClassLoader());
    }

    public static final Creator<DetailTransaksi> CREATOR = new Creator<DetailTransaksi>() {
        @Override
        public DetailTransaksi createFromParcel(Parcel in) {
            return new DetailTransaksi(in);
        }

        @Override
        public DetailTransaksi[] newArray(int size) {
            return new DetailTransaksi[size];
        }
    };

    public List<Barang> getBarang() {
        return barang;
    }

    public void setBarang(List<Barang> barang) {
        this.barang = barang;
    }

    public Transaksi getTransaksi() {
        return transaksi;
    }

    public void setTransaksi(Transaksi transaksi) {
        this.transaksi = transaksi;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeTypedList(barang);
        dest.writeParcelable(transaksi, flags);
    }
}
