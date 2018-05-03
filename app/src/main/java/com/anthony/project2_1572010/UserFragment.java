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
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.Toast;

import com.anthony.project2_1572010.entity.User;
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

public class UserFragment extends Fragment {

    final static String ARG_User = "parcel_user";

    @BindView(R.id.txtNama)
    EditText txtNama;
    @BindView(R.id.txtUsername)
    EditText txtUsername;
    @BindView(R.id.txtPassword)
    EditText txtPassword;
    @BindView(R.id.txtRePassword)
    EditText txtRePassword;
    @BindView(R.id.txtNoTelepon)
    EditText txtNoTelepeon;
    @BindView(R.id.txtAlamat)
    EditText txtAlamat;
    @BindView(R.id.radRole)
    RadioGroup radRole;
    @BindView(R.id.radAdmin)
    RadioButton radAdmin;
    @BindView(R.id.radKasir)
    RadioButton radKasir;

    private DatabaseReference database;
    boolean addData;
    boolean updateData;
    boolean deleteData;
    int id;
    private MainActivity mainActivity;
    public User selectedUser;

    public UserFragment() {
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        database = FirebaseDatabase.getInstance().getReference();
        addData=false;
        updateData=false;
        deleteData=false;
        id=0;
        View view=inflater.inflate(R.layout.fragment_user,container,false);
        ButterKnife.bind(this,view);
        return view;
    }

    @Override
    public void onStart() {
        super.onStart();
        if(getArguments()!=null && getArguments().containsKey(getResources().getString(R.string.parcel_user))){
            User user = getArguments().getParcelable(getResources().getString(R.string.parcel_user));
            txtAlamat.setText(user.getAlamatUser());
            txtPassword.setText(user.getPassword());
            txtNama.setText(user.getNamaUser());
            txtNoTelepeon.setText(user.getNoTelpUser());
            txtUsername.setText(user.getUsername());
            selectedUser = user;
        }
    }

    @OnClick(R.id.btnAdd)
    public void btnAddUser(){
        id=0;
        if(!TextUtils.isEmpty(txtNama.getText().toString().trim()) && !TextUtils.isEmpty(txtUsername.getText().toString().trim()) && !TextUtils.isEmpty(txtNoTelepeon.getText().toString().trim()) && !TextUtils.isEmpty(txtPassword.getText().toString().trim()) && !TextUtils.isEmpty(txtAlamat.getText().toString().trim())){
            database.child("User").addValueEventListener(new ValueEventListener() {
                @Override
                public void onDataChange(DataSnapshot dataSnapshot) {
                    for (DataSnapshot noteDataSnapshot : dataSnapshot.getChildren()) {
                        int cekId = Integer.valueOf(noteDataSnapshot.getValue(User.class).getIdUser().substring(2,3));
                        id = cekId;
                    }
                }
                @Override
                public void onCancelled(DatabaseError databaseError) {
                    System.out.println(databaseError.getDetails()+" "+databaseError.getMessage());
                }
            });
            User user = new User();
            user.setIdUser("00"+String.valueOf((id+1)));
            user.setAdmin(1);
            user.setAlamatUser(txtAlamat.getText().toString());
            user.setNamaUser(txtNama.getText().toString());
            user.setNoTelpUser(txtNoTelepeon.getText().toString());
            user.setUsername(txtUsername.getText().toString());
            user.setPassword(txtPassword.getText().toString());
            database.child("User").push().setValue(user).addOnSuccessListener(getActivity(), new OnSuccessListener<Void>() {
                @Override
                public void onSuccess(Void aVoid) {
                    txtAlamat.setText("");
                    txtNama.setText("");
                    txtNoTelepeon.setText("");
                    txtPassword.setText("");
                    txtRePassword.setText("");
                    txtUsername.setText("");
                    addData = true;
                    Toast.makeText(getActivity(), "Data User berhasil ditambahkan!", Toast.LENGTH_SHORT).show();
                }
            });
        }
    }

    @OnClick(R.id.btnUpdate)
    public void btnUpdateUser(){
        if(!TextUtils.isEmpty(txtNama.getText().toString().trim()) && !TextUtils.isEmpty(txtUsername.getText().toString().trim()) && !TextUtils.isEmpty(txtNoTelepeon.getText().toString().trim()) && !TextUtils.isEmpty(txtPassword.getText().toString().trim()) && !TextUtils.isEmpty(txtAlamat.getText().toString().trim())){
            selectedUser.setIdUser("00"+String.valueOf((id+1)));
            selectedUser.setAdmin(1);
            selectedUser.setAlamatUser(txtAlamat.getText().toString());
            selectedUser.setNamaUser(txtNama.getText().toString());
            selectedUser.setNoTelpUser(txtNoTelepeon.getText().toString());
            selectedUser.setUsername(txtUsername.getText().toString());
            selectedUser.setPassword(txtPassword.getText().toString());
            database.child("User").child(selectedUser.getKey()).setValue(selectedUser).addOnSuccessListener(getActivity(), new OnSuccessListener<Void>() {
                @Override
                public void onSuccess(Void aVoid) {
                    txtAlamat.setText("");
                    txtNama.setText("");
                    txtNoTelepeon.setText("");
                    txtPassword.setText("");
                    txtRePassword.setText("");
                    txtUsername.setText("");
                    updateData = true;
                    Toast.makeText(getActivity(), "Data User berhasil diubah!", Toast.LENGTH_SHORT).show();
                }
            });
        }
    }

    @OnClick(R.id.btnDelete)
    public void btnDeleteUser(){
        if(selectedUser!=null){
            database.child("User").child(selectedUser.getKey()).removeValue().addOnSuccessListener(new OnSuccessListener<Void>() {
                @Override
                public void onSuccess(Void aVoid) {
                    txtAlamat.setText("");
                    txtNama.setText("");
                    txtNoTelepeon.setText("");
                    txtPassword.setText("");
                    txtRePassword.setText("");
                    txtUsername.setText("");
                    deleteData = true;
                    Toast.makeText(getActivity(), "Data User berhasil didelete!", Toast.LENGTH_SHORT).show();
                }
            });
        }
    }
}