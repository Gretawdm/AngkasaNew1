package com.greta.angkasanew.Adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.core.content.ContextCompat;
import androidx.recyclerview.widget.RecyclerView;

import com.greta.angkasanew.Model.DiskonHomeModel;
import com.greta.angkasanew.Model.PemesananJemberModel;
import com.greta.angkasanew.R;

import java.util.ArrayList;

public class DiskonHomeAdapter extends RecyclerView.Adapter<DiskonHomeAdapter.DiskonHomeViewHolder> {
    private static ArrayList<DiskonHomeModel> diskonHomeModelArrayList;
    private static Context context;

    public DiskonHomeAdapter(Context context, ArrayList<DiskonHomeModel> diskonHomeModelArrayList){
        this.diskonHomeModelArrayList = diskonHomeModelArrayList;
        this.context = context;
    }

    @Override
    public DiskonHomeViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.card_diskon_home, parent, false);
        return new DiskonHomeAdapter.DiskonHomeViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull DiskonHomeAdapter.DiskonHomeViewHolder holder, int position) {
        DiskonHomeModel diskonHomeModel = diskonHomeModelArrayList.get(position);

        holder.Bulan.setText(diskonHomeModel.getBulan());
        holder.Judulpromo.setText(diskonHomeModel.getJudul_promo());
    }

    @Override
    public int getItemCount() {
        return diskonHomeModelArrayList.size();
    }

    public static class DiskonHomeViewHolder extends RecyclerView.ViewHolder{
        TextView Bulan, Judulpromo;

        public DiskonHomeViewHolder(@NonNull View itemView){
            super(itemView);

            Bulan = itemView.findViewById(R.id.txt_nama_bulan_home);
            Judulpromo = itemView.findViewById(R.id.txt_judulpromo);
        }
    }
}
