package com.greta.angkasanew.Adapter;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.core.content.ContextCompat;
import androidx.recyclerview.widget.RecyclerView;

import com.greta.angkasanew.Model.PemesananSponsorModel;
import com.greta.angkasanew.Pesanan.DetailPesananSponsor;
import com.greta.angkasanew.R;

import java.util.ArrayList;

public class PemesananSponsorAdapter extends RecyclerView.Adapter<PemesananSponsorAdapter.PemesananSponsorViewHolder> {
    private static ArrayList<PemesananSponsorModel> pemesananSponsorModelArrayList;

    private static Context context;

    public PemesananSponsorAdapter(Context context, ArrayList<PemesananSponsorModel> pemesananSponsorModelArrayList) {
        this.pemesananSponsorModelArrayList = pemesananSponsorModelArrayList;
        this.context = context;
    }

    @Override
    public PemesananSponsorAdapter.PemesananSponsorViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.listsponsor, parent, false);
        return new PemesananSponsorAdapter.PemesananSponsorViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull PemesananSponsorAdapter.PemesananSponsorViewHolder holder, int position) {
        PemesananSponsorModel pemesananSponsorModel = pemesananSponsorModelArrayList.get(position);

        holder.PemesanName.setText(pemesananSponsorModel.getNamacustomer());
        holder.tanggalPemesanan.setText(pemesananSponsorModel.getTanggal());
        holder.status.setText(pemesananSponsorModel.getStatus());

        if("Selesai".equals(pemesananSponsorModel.getStatus())) {
            holder.status.setTextColor(ContextCompat.getColor(holder.itemView.getContext(), R.color.hijau));
        }else {
            holder.status.setTextColor(ContextCompat.getColor(holder.itemView.getContext(), R.color.merah));
        }

    }

    @Override
    public int getItemCount() {
        return pemesananSponsorModelArrayList.size();
    }

    public static class PemesananSponsorViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {
        TextView PemesanName;
        TextView tanggalPemesanan;
        TextView status;

        public PemesananSponsorViewHolder(@NonNull View itemView) {
            super(itemView);

            PemesanName = itemView.findViewById(R.id.txt_nama);
            tanggalPemesanan = itemView.findViewById(R.id.txt_tanggal_sponsor);
            status = itemView.findViewById(R.id.txt_konfirmasi_sponsor);

            //menambahkan listener klik pada itemview
            itemView.setOnClickListener(this);
        }

        @Override
        public void onClick(View v) {
            int position = getAdapterPosition();
            Intent intent = new Intent(context, DetailPesananSponsor.class);
            intent.putExtra("nama_cust", pemesananSponsorModelArrayList.get(position).getNamacustomer());
            intent.putExtra("no_hp", pemesananSponsorModelArrayList.get(position).getNo_hp());
            intent.putExtra("tanggal_acara", pemesananSponsorModelArrayList.get(position).getTanggal());
            intent.putExtra("alamat_acara", pemesananSponsorModelArrayList.get(position).getAlamat());
            intent.putExtra("proposal", pemesananSponsorModelArrayList.get(position).getProposal());
            intent.putExtra("id_pemesanan", pemesananSponsorModelArrayList.get(position).getId_pemesanan());

            context.startActivity(intent);
        }

    }
}
