package com.anthony.project2_1572010.adapter;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.anthony.project2_1572010.R;
import com.anthony.project2_1572010.entity.Barang;

import org.w3c.dom.Text;

import java.util.ArrayList;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * @author Anthony (1572010)
 */

public class BarangAdapter extends RecyclerView.Adapter<BarangAdapter.BarangViewHolder> {
    private ArrayList<Barang> barangs;
    private BarangDataListener barangDataListener;

    @NonNull
    @Override
    public BarangViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        Context context = parent.getContext();
        View view = LayoutInflater.from(context).inflate(R.layout.row_barang, parent, false);
        return new BarangViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull BarangViewHolder holder, int position) {
        final Barang barang = getBarangs().get(position);
        String namaBarang = barang.getNamaBarang();
        int stockBarang = barang.getStock();
        int hargaJualBarang = barang.getHargaJual();
        int hargaBeliBarang = barang.getHargaBeli();

        holder.txtNamaBarang.setText(namaBarang);
        holder.txtStockBarang.setText(String.valueOf(stockBarang));
        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(barangDataListener != null){
                    barangDataListener.onBarangClicked(barang);
                }
            }
        });
    }

    @Override
    public int getItemCount() {
        return getBarangs().size();
    }

    class BarangViewHolder extends RecyclerView.ViewHolder {

        @BindView(R.id.tvNamaBarang)
        TextView txtNamaBarang;
        @BindView(R.id.tvStockBarang)
        TextView txtStockBarang;

        public BarangViewHolder(View itemView) {
            super(itemView);
            ButterKnife.bind(this, itemView);
        }
    }

    public void setBarangDataListener(BarangDataListener barangDataListener) {
        this.barangDataListener = barangDataListener;
    }

    public ArrayList<Barang> getBarangs() {
        if(barangs == null){
            barangs = new ArrayList<>();
        }
        return barangs;
    }

    public void setBarangs(ArrayList<Barang> barangs) {
        getBarangs().clear();
        getBarangs().addAll(barangs);
        notifyDataSetChanged();
    }

    public interface BarangDataListener {
        void onBarangClicked(Barang barang);
    }
}
