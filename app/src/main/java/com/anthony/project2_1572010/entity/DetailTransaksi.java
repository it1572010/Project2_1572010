package com.anthony.project2_1572010.entity;

import android.os.Parcel;
import android.os.Parcelable;

public class DetailTransaksi implements Parcelable {
    private Barang barang;
    private Transaksi transaksi;

    public DetailTransaksi() {
    }

    protected DetailTransaksi(Parcel in) {
        barang = in.readParcelable(Barang.class.getClassLoader());
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

    public Barang getBarang() {
        return barang;
    }

    public void setBarang(Barang barang) {
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
        dest.writeParcelable(barang, flags);
        dest.writeParcelable(transaksi, flags);
    }
}
