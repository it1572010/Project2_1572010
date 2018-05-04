package com.anthony.project2_1572010.entity;

import android.os.Parcel;
import android.os.Parcelable;

public class Transaksi implements Parcelable{
    private User user;
    private String idTransaksi;
    private int nominalTransaksi;
    private String tanggalTransaksi;
    private String key;

    public Transaksi() {
    }

    protected Transaksi(Parcel in) {
        user = in.readParcelable(User.class.getClassLoader());
        idTransaksi = in.readString();
        nominalTransaksi = in.readInt();
        tanggalTransaksi = in.readString();
    }

    public static final Creator<Transaksi> CREATOR = new Creator<Transaksi>() {
        @Override
        public Transaksi createFromParcel(Parcel in) {
            return new Transaksi(in);
        }

        @Override
        public Transaksi[] newArray(int size) {
            return new Transaksi[size];
        }
    };

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public String getIdTransaksi() {
        return idTransaksi;
    }

    public void setIdTransaksi(String idTransaksi) {
        this.idTransaksi = idTransaksi;
    }

    public int getNominalTransaksi() {
        return nominalTransaksi;
    }

    public void setNominalTransaksi(int nominalTransaksi) {
        this.nominalTransaksi = nominalTransaksi;
    }

    public String getTanggalTransaksi() {
        return tanggalTransaksi;
    }

    public void setTanggalTransaksi(String tanggalTransaksi) {
        this.tanggalTransaksi = tanggalTransaksi;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeParcelable(user, flags);
        dest.writeString(idTransaksi);
        dest.writeInt(nominalTransaksi);
        dest.writeString(tanggalTransaksi);
    }
}
