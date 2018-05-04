package com.anthony.project2_1572010;

import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.support.design.widget.NavigationView;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.Toast;

import com.anthony.project2_1572010.adapter.BarangAdapter;
import com.anthony.project2_1572010.adapter.UserAdapter;
import com.anthony.project2_1572010.entity.Barang;
import com.anthony.project2_1572010.entity.User;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;

import butterknife.BindView;
import butterknife.ButterKnife;

public class MainActivity extends AppCompatActivity
        implements NavigationView.OnNavigationItemSelectedListener, UserAdapter.UserDataListener,
        BarangAdapter.BarangDataListener {

    private UserAdapter userAdapter;
    private UserListFragment userListFragment;
    private BarangAdapter barangAdapter;
    private BarangListFragment barangListFragment;
    int userRole;

    @BindView(R.id.rvListUser)
    RecyclerView recyclerUsers;

    public BarangListFragment getBarangListFragment() {
        if (barangListFragment == null) {
            barangListFragment = new BarangListFragment();
            barangListFragment.getBarangAdapter().setBarangDataListener(this);
        }
        return barangListFragment;
    }

    public UserListFragment getUserListFragment() {
        if (userListFragment == null) {
            userListFragment = new UserListFragment();
            userListFragment.getUserAdapter().setUserDataListener(this);
        }
        return userListFragment;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ButterKnife.bind(this);
        Intent intent = getIntent();
        userRole = intent.getIntExtra("userRole", 0);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(MainActivity.this, LoginActivity.class);
                MainActivity.this.finish();
                MainActivity.this.startActivity(intent);
//                Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
//                        .setAction("Action", null).show();

            }
        });

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
                this, drawer, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawer.addDrawerListener(toggle);
        toggle.syncState();

        NavigationView navigationView = (NavigationView) findViewById(R.id.nav_view);
        navigationView.setNavigationItemSelectedListener(this);
    }

    @Override
    public void onBackPressed() {
        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        if (drawer.isDrawerOpen(GravityCompat.START)) {
            drawer.closeDrawer(GravityCompat.START);
        } else {
            super.onBackPressed();
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_logout) {
            Intent intent = new Intent(MainActivity.this, LoginActivity.class);
            MainActivity.this.finish();
            MainActivity.this.startActivity(intent);
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    @SuppressWarnings("StatementWithEmptyBody")
    @Override
    public boolean onNavigationItemSelected(MenuItem item) {
        // Handle navigation view item clicks here.
        int id = item.getItemId();

        if (id == R.id.nav_kasir) {
            KasirFragment kasirFragment = new KasirFragment();
            FragmentTransaction kasirTransaction = getSupportFragmentManager().beginTransaction();
            kasirTransaction.replace(R.id.frameKiri,kasirFragment);
            kasirTransaction.commit();
            FragmentTransaction barangLisTransaction = getSupportFragmentManager().beginTransaction();
            barangLisTransaction.replace(R.id.frameKanan, getBarangListFragment());
            barangLisTransaction.commit();
        } else if (id == R.id.nav_laporan) {

        } else if (id == R.id.nav_user) {
            if (userRole == 1) {
                UserFragment userFragment = new UserFragment();
                FragmentTransaction userTransaction = getSupportFragmentManager().beginTransaction();
                userTransaction.replace(R.id.frameKiri, userFragment);
                userTransaction.commit();
                FragmentTransaction userListTransaction = getSupportFragmentManager().beginTransaction();
                userListTransaction.replace(R.id.frameKanan, getUserListFragment());
                userListTransaction.commit();
            } else {
                Toast.makeText(this, "not allowed", Toast.LENGTH_SHORT).show();
            }
        } else if (id == R.id.nav_barang) {
            if (userRole == 1) {
                BarangFragment barangFragment = new BarangFragment();
                FragmentTransaction barangTransaction = getSupportFragmentManager().beginTransaction();
                barangTransaction.replace(R.id.frameKiri, barangFragment);
                barangTransaction.commit();
                FragmentTransaction barangLisTransaction = getSupportFragmentManager().beginTransaction();
                barangLisTransaction.replace(R.id.frameKanan, getBarangListFragment());
                barangLisTransaction.commit();
            } else {
                Toast.makeText(this, "not allowed", Toast.LENGTH_SHORT).show();
            }
        }
        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        drawer.closeDrawer(GravityCompat.START);
        return true;
    }

    public UserAdapter getUserAdapter() {
        if (userAdapter == null) {
            userAdapter = new UserAdapter();
            userAdapter.setUserDataListener(this);
        }
        return userAdapter;
    }

    public BarangAdapter getBarangAdapter() {
        if (barangAdapter == null) {
            barangAdapter = new BarangAdapter();
            barangAdapter.setBarangDataListener(this);
        }
        return barangAdapter;
    }


    @Override
    public void onBarangClicked(Barang barang) {
        if (findViewById(R.id.frameKiri) != null) {
            Bundle bundle = new Bundle();
            bundle.putParcelable(BarangFragment.ARG_Barang, barang);
            BarangFragment barangFragment = new BarangFragment();
            barangFragment.setArguments(bundle);
            FragmentTransaction transaction = getSupportFragmentManager().beginTransaction();
            transaction.replace(R.id.frameKiri, barangFragment);
            transaction.commit();
        } else {
            Bundle bundle = new Bundle();
            bundle.putParcelable(BarangFragment.ARG_Barang, barang);
            BarangFragment barangFragment = new BarangFragment();
            barangFragment.setArguments(bundle);
            FragmentTransaction transaction = getSupportFragmentManager().beginTransaction();
            transaction.replace(R.id.frameKanan, barangFragment);
            transaction.addToBackStack(null);
            transaction.commit();
        }
    }

    @Override
    public void onUserClicked(User user) {
        if (findViewById(R.id.frameKiri) != null) {
            Bundle bundle = new Bundle();
            bundle.putParcelable(UserFragment.ARG_User, user);
            UserFragment userFragment = new UserFragment();
            userFragment.setArguments(bundle);
            FragmentTransaction transaction = getSupportFragmentManager().beginTransaction();
            transaction.replace(R.id.frameKiri, userFragment);
            transaction.commit();
        } else {
            Bundle bundle = new Bundle();
            bundle.putParcelable(UserFragment.ARG_User, user);
            UserFragment userFragment = new UserFragment();
            userFragment.setArguments(bundle);
            FragmentTransaction transaction = getSupportFragmentManager().beginTransaction();
            transaction.replace(R.id.frameKanan, userFragment);
            transaction.addToBackStack(null);
            transaction.commit();
        }
    }
}
