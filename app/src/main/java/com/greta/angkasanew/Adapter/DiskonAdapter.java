package com.greta.angkasanew.Adapter;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.greta.angkasanew.Diskon.DetailDiskon;
import com.greta.angkasanew.Model.DiskonModel;
import com.greta.angkasanew.Model.PemesananModel;
import com.greta.angkasanew.R;

import java.util.ArrayList;

public class DiskonAdapter extends RecyclerView.Adapter<DiskonAdapter.DiskonViewHolder> {
    private static ArrayList<DiskonModel> diskonModelArrayList;
    private static Context context;

    public DiskonAdapter(Context context, ArrayList<DiskonModel> diskonModelArrayList){
        this.diskonModelArrayList = diskonModelArrayList;
        this.context = context;
    }
    @Override
    public DiskonViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.card_diskon, parent, false);
        return new DiskonViewHolder(view);
    }

    @Override
    public void onBindViewHolder(DiskonViewHolder holder, int position) {
        DiskonModel diskon = diskonModelArrayList.get(position);

        holder.bulan.setText(diskon.getBulan());
        holder.judul_promo.setText(diskon.getJudul_promo());
    }

    @Override
    public int getItemCount() {
        return diskonModelArrayList.size();
    }

    public static class DiskonViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {
        TextView bulan, judul_promo;

        public DiskonViewHolder(@NonNull View itemView){
            super(itemView);

            bulan = itemView.findViewById(R.id.txt_bulan);
            judul_promo = itemView.findViewById(R.id.txt_judul_promo);

            //menambahkan listener klik pada itemview
            itemView.setOnClickListener(this);
        }

        @Override
        public void onClick(View v){
            int position = getAdapterPosition();
            Intent intent = new Intent(context, DetailDiskon.class);
            intent.putExtra("judul_promo", diskonModelArrayList.get(position).getJudul_promo());
            intent.putExtra("nama_promo", diskonModelArrayList.get(position).getNama_promo());
            intent.putExtra("harga", diskonModelArrayList.get(position).getHarga_promo());
            intent.putExtra("id_promo", diskonModelArrayList.get(position).getId_promo().toString());
            context.startActivity(intent);
        }
    }
}
