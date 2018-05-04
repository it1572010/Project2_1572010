package com.anthony.project2_1572010;

import android.content.Context;
import android.net.Uri;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;

import com.google.firebase.database.DatabaseReference;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

/**
 * @author Anthony (1572010)
 */

public class KasirFragment extends Fragment {
    final static String ARG_Kasir = "parcel_kasir";

    @BindView(R.id.txtNamaBarang)
    EditText txtNamaBarang;
    @BindView(R.id.txtJumlahBarang)
    EditText txtJumlahBarang;
    @BindView(R.id.txtHargaJualBarang)
    EditText txtHargaJualBarang;
    @BindView(R.id.btnAdd)
    Button btnAdd;
    @BindView(R.id.btnUpdate)
    Button btnUpdate;
    @BindView(R.id.btnDelete)
    Button btnDelete;

    private DatabaseReference database;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_kasir,container,false);
        ButterKnife.bind(this,view);
        return view;

    }

    @Override
    public void onStart() {
        super.onStart();
    }

    @OnClick(R.id.btnAdd)
    public void btnAddTransaksi(){

    }

    @OnClick(R.id.btnUpdate)
    public void btnUpdateTransaksi(){

    }

    @OnClick(R.id.btnDelete)
    public void btnDeleteTransaksi(){

    }
}