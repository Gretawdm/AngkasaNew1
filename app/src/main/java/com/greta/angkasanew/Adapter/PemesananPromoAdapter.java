package com.greta.angkasanew.Adapter;

import android.content.Context;
import android.content.Intent;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;
import com.greta.angkasanew.Adapter.PemesananPromoAdapter;

import com.greta.angkasanew.Model.PemesananModel;
import com.greta.angkasanew.Model.PemesananPromoModel;
import com.greta.angkasanew.Pesanan.DetailPesanananPromo;
import com.greta.angkasanew.Product;
import com.greta.angkasanew.R;

import java.util.ArrayList;

public class PemesananPromoAdapter extends RecyclerView.Adapter<PemesananPromoAdapter.PemesananPromoViewHolder> {
    private static ArrayList<PemesananPromoModel>pemesananPromoModelArrayList;

    private static Context context;

    public PemesananPromoAdapter(Context context, ArrayList<PemesananPromoModel> pemesananPromoModelArrayList){
        this.pemesananPromoModelArrayList = pemesananPromoModelArrayList;
        this.context = context;
    }

    @Override
    public PemesananPromoViewHolder onCreateViewHolder( ViewGroup parent, int viewType){
        View view = LayoutInflater.from(context).inflate(R.layout.listpromo, parent, false);
        return new PemesananPromoViewHolder(view);
    }

    @Override
    public void onBindViewHolder(PemesananPromoViewHolder holder, int position) {
        PemesananPromoModel pemesananPromoModel = pemesananPromoModelArrayList.get(position);

        holder.Nama_Cust.setText(pemesananPromoModel.getNama_cust());
        holder.Judul_Promo.setText(pemesananPromoModel.getJudul_promo());
        holder.status.setText(pemesananPromoModel.getStatus());
      /*  holder.status.setText(pemesananPromoModel.getStatus());*/
        Log.w("test",pemesananPromoModel.getStatus());
    }
    @Override
    public int getItemCount() {
        return pemesananPromoModelArrayList.size();
    }

    public static class PemesananPromoViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener{
        TextView Nama_Cust;
        TextView Judul_Promo;
        TextView status;
        public PemesananPromoViewHolder(@NonNull View itemView){
            super(itemView);

            Nama_Cust = itemView.findViewById(R.id.txt_nama_c);
            Judul_Promo = itemView.findViewById(R.id.txt_jpromo);
            status = itemView.findViewById(R.id.txt_konfirmasi);

            //menambahkan listener klik pada itemview
            itemView.setOnClickListener(this);
        }
        @Override
        public void onClick(View v) {
            int position = getAdapterPosition();
            Intent intent = new Intent(context, DetailPesanananPromo.class);
           /* Log.w("myApp", pemesananPromoModelArrayList.get(position).getId_pemesanan());*/
            intent.putExtra("judul_promo", pemesananPromoModelArrayList.get(position).getJudul_promo());
            intent.putExtra("nama_promo", pemesananPromoModelArrayList.get(position).getNama_promo());
            intent.putExtra("nama_cust", pemesananPromoModelArrayList.get(position).getNama_cust());
            intent.putExtra("no_hp", pemesananPromoModelArrayList.get(position).getNo_hp());
            intent.putExtra("alamat_acara", pemesananPromoModelArrayList.get(position).getAlamat());
            intent.putExtra("harga_promo", pemesananPromoModelArrayList.get(position).getHarga());
            intent.putExtra("id", pemesananPromoModelArrayList.get(position).getId_pemesanan());
            context.startActivity(intent);
        }

    }
}
