package com.anthony.project2_1572010.adapter;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.anthony.project2_1572010.R;
import com.anthony.project2_1572010.entity.User;

import java.util.ArrayList;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * @author Anthony (1572010)
 */

public class UserAdapter extends RecyclerView.Adapter<UserAdapter.UserViewHolder>{
    private ArrayList<User> users;
    private UserDataListener userDataListener;

    public void setUserDataListener(UserDataListener userDataListener) {
        this.userDataListener = userDataListener;
    }

    public ArrayList<User> getUsers() {
        if(users==null){
            users = new ArrayList<>();
        }
        return users;
    }

    public void setUsers(ArrayList<User> users) {
        getUsers().clear();
        getUsers().addAll(users);
        notifyDataSetChanged();
    }

    @NonNull
    @Override
    public UserViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        Context context = parent.getContext();
        View view = LayoutInflater.from(context).inflate(R.layout.row_user,parent,false);
        return new UserViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull UserViewHolder holder, int position) {
        final User user = getUsers().get(position);
        String id = user.getIdUser();
        String nama = user.getNamaUser();
        String username = user.getUsername();
        String alamat = user.getAlamatUser();
        String noHp = user.getNoTelpUser();
        String password = user.getPassword();
        int admin = user.getAdmin();
        String role = "";
        if(admin==0){
            role = "Kasir";
        }
        else{
            role = "Admin";
        }
        holder.tvNamaUser.setText(nama);
        holder.tvRoleUser.setText(role);
        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(userDataListener != null){
                    userDataListener.onUserClicked(user);
                }
            }
        });
    }

    @Override
    public int getItemCount() {
        return getUsers().size();
    }

    class UserViewHolder extends RecyclerView.ViewHolder{

        @BindView(R.id.tvNamaUser)
        TextView tvNamaUser;
        @BindView(R.id.tvRoleUser)
        TextView tvRoleUser;

        public UserViewHolder(View itemView) {
            super(itemView);
            ButterKnife.bind(this,itemView);
        }
    }

    public interface UserDataListener{
        void onUserClicked(User user);
    }
}
