package com.anthony.project2_1572010;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.TextUtils;
import android.widget.EditText;
import android.widget.Toast;

import com.anthony.project2_1572010.entity.User;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class LoginActivity extends AppCompatActivity {

    @BindView(R.id.txtUsername)
    EditText txtUsername;
    @BindView(R.id.txtPassword)
    EditText txtPassword;

    private DatabaseReference userRf;
    private ArrayList<User> users;

    public ArrayList<User> getUsers() {
        if (users == null) {
            users = new ArrayList<>();
        }
        return users;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        ButterKnife.bind(this);

        FirebaseDatabase firebaseDatabase = FirebaseDatabase.getInstance();
        userRf = firebaseDatabase.getReference();
    }

    @OnClick(R.id.btnLogin)
    private void btnLoginAct() {
        if (!TextUtils.isEmpty(txtUsername.getText().toString().trim()) || !TextUtils.isEmpty(txtPassword.getText().toString().trim())) {
            userRf.child("User").addValueEventListener(new ValueEventListener() {
                @Override
                public void onDataChange(DataSnapshot dataSnapshot) {
                    for(DataSnapshot dataSnapshot1 : dataSnapshot.getChildren()) {
                        if(dataSnapshot1.getValue(User.class).getUsername().equals(txtUsername.getText().toString().trim()) && dataSnapshot1.getValue(User.class).getPassword().equals(txtPassword.getText().toString().trim())) {
                            Intent intent=new Intent(LoginActivity.this,MainActivity.class);
                            LoginActivity.this.finish();
                            LoginActivity.this.startActivity(intent);
                        }
                    }
                }

                @Override
                public void onCancelled(DatabaseError databaseError) {

                }
            });
        } else {
            Toast.makeText(this, "Please insert username and password", Toast.LENGTH_SHORT).show();
        }
    }
}
