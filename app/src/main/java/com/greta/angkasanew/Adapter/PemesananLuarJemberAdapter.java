package com.greta.angkasanew.Adapter;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.greta.angkasanew.Model.PemesananLuarJemberModel;
import com.greta.angkasanew.Pesanan.DetailPemesananLuarJember;
import com.greta.angkasanew.Pesanan.DetailPesanan;
import com.greta.angkasanew.R;

import java.util.ArrayList;

public class PemesananLuarJemberAdapter extends RecyclerView.Adapter<PemesananLuarJemberAdapter.PemesananLuarJemberViewHolder> {
    private static ArrayList<PemesananLuarJemberModel>pemesananLuarJemberModelArrayList;

    private static Context context;

    public PemesananLuarJemberAdapter(Context context, ArrayList<PemesananLuarJemberModel> pemesananLuarJemberModelArrayList){
        this.pemesananLuarJemberModelArrayList = pemesananLuarJemberModelArrayList;
        this.context = context;
    }

    @Override
    public PemesananLuarJemberAdapter.PemesananLuarJemberViewHolder onCreateViewHolder(ViewGroup parent, int viewType){
        View view = LayoutInflater.from(context).inflate(R.layout.list, parent, false);
        return new PemesananLuarJemberAdapter.PemesananLuarJemberViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull PemesananLuarJemberAdapter.PemesananLuarJemberViewHolder holder, int position) {
        PemesananLuarJemberModel pemesananLuarJemberModel = pemesananLuarJemberModelArrayList.get(position);

        holder.PackageName.setText(pemesananLuarJemberModel.getNamapackage());
        holder.PemesanName.setText(pemesananLuarJemberModel.getNamacustomer());
        holder.tanggalPemesanan.setText(pemesananLuarJemberModel.getTanggal());

    }

    @Override
    public int getItemCount() {
        return pemesananLuarJemberModelArrayList.size();
    }

    public static class PemesananLuarJemberViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener{
        TextView PackageName;
        TextView PemesanName;
        TextView tanggalPemesanan;

        public PemesananLuarJemberViewHolder(@NonNull View itemView){
            super(itemView);

            PackageName = itemView.findViewById(R.id.txt_product);
            PemesanName = itemView.findViewById(R.id.txt_nama_customer);
            tanggalPemesanan = itemView.findViewById(R.id.txt_tanggal);

            //menambahkan listener klik pada itemview
            itemView.setOnClickListener(this);
        }
        @Override
        public void onClick(View v) {
            int position = getAdapterPosition();
            Intent intent = new Intent(context, DetailPemesananLuarJember.class);
            intent.putExtra("package", pemesananLuarJemberModelArrayList.get(position).getNamapackage());
            intent.putExtra("nama", pemesananLuarJemberModelArrayList.get(position).getNamacustomer());
            intent.putExtra("no_hp", pemesananLuarJemberModelArrayList.get(position).getNo_hp());
            intent.putExtra("tanggal", pemesananLuarJemberModelArrayList.get(position).getTanggal());
            intent.putExtra("alamat", pemesananLuarJemberModelArrayList.get(position).getAlamat());
            intent.putExtra("quota", pemesananLuarJemberModelArrayList.get(position).getQuota());
            intent.putExtra("unlimited", pemesananLuarJemberModelArrayList.get(position).getUnlimited());
            intent.putExtra("layout", pemesananLuarJemberModelArrayList.get(position).getNama_layout());
            context.startActivity(intent);
        }

    }
}
