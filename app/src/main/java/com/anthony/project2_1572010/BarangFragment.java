package com.anthony.project2_1572010;

import android.content.Context;
import android.net.Uri;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.Toast;

import com.anthony.project2_1572010.entity.Barang;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

/**
 * @author Anthony (1572010)
 */

public class BarangFragment extends Fragment {

    @BindView(R.id.txtNamaBarang)
    EditText txtNamaBarang;
    @BindView(R.id.txtStockBarang)
    EditText txtStockBarang;
    @BindView(R.id.txtHargaBeliBarang)
    EditText txtHargaBeliBarang;
    @BindView(R.id.txtHargaJualBarang)
    EditText txtHargaJualBarang;

    private DatabaseReference database;
    boolean addData;
    int id;
    private MainActivity mainActivity;

    public BarangFragment() {
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        database = FirebaseDatabase.getInstance().getReference();
        addData = false;
        View view = inflater.inflate(R.layout.fragment_barang,container,false);
        ButterKnife.bind(this,view);
        return view;

    }

    @Override
    public void onStart() {
        super.onStart();
        if(getArguments() != null && getArguments().containsKey(getResources().getString(R.string.parcel_barang))){
            Barang barang = getArguments().getParcelable(getResources().getString(R.string.parcel_barang));
            txtNamaBarang.setText(barang.getNamaBarang());
            txtStockBarang.setText(barang.getStock());
            txtHargaJualBarang.setText(barang.getHargaJual());
            txtHargaBeliBarang.setText(barang.getHargaBeli());
        }
    }

    @OnClick(R.id.btnAdd)
    public void btnAddBarang(){
        id=0;
        if(!TextUtils.isEmpty(txtNamaBarang.getText().toString().trim()) && !TextUtils.isEmpty(txtStockBarang.getText().toString().trim()) && !TextUtils.isEmpty(txtHargaBeliBarang.getText().toString().trim()) && !TextUtils.isEmpty(txtHargaJualBarang.getText().toString().trim())){
            database.child("Barang").addValueEventListener(new ValueEventListener() {
                @Override
                public void onDataChange(DataSnapshot dataSnapshot) {
                    for (DataSnapshot noteDataSnapshot : dataSnapshot.getChildren()) {
                        int cekId = Integer.valueOf(noteDataSnapshot.getValue(Barang.class).getIdBarang().substring(2,3));
                        id = cekId;
                    }
                }
                @Override
                public void onCancelled(DatabaseError databaseError) {
                    System.out.println(databaseError.getDetails()+" "+databaseError.getMessage());
                }
            });
            Barang barang = new Barang();
            barang.setIdBarang("00"+String.valueOf((id+1)));
            barang.setNamaBarang(txtNamaBarang.getText().toString().trim());
            barang.setStock(Integer.valueOf(txtStockBarang.getText().toString().trim()));
            barang.setHargaBeli(Integer.valueOf(txtHargaBeliBarang.getText().toString().trim()));
            barang.setHargaJual(Integer.valueOf(txtHargaJualBarang.getText().toString().trim()));
            database.child("Barang").push().setValue(barang).addOnSuccessListener(getActivity(), new OnSuccessListener<Void>() {
                @Override
                public void onSuccess(Void aVoid) {
                    txtNamaBarang.setText("");
                    txtStockBarang.setText("");
                    txtHargaBeliBarang.setText("");
                    txtHargaJualBarang.setText("");
                    addData = true;
                    Toast.makeText(getActivity(), "Data Barang berhasil ditambahkan!", Toast.LENGTH_SHORT).show();
                }
            });
        }
    }
}
