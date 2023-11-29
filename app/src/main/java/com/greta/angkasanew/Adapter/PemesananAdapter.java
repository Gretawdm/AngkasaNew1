package com.greta.angkasanew.Adapter;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.greta.angkasanew.Model.PemesananModel;
import com.greta.angkasanew.Pesanan.DetailPesanan;
import com.greta.angkasanew.Product;
import com.greta.angkasanew.R;

import java.util.ArrayList;

public class PemesananAdapter extends RecyclerView.Adapter<PemesananAdapter.PemesananViewHolder> {
    private static ArrayList<PemesananModel>pemesananModelArrayList;

    private static Context context;

    public PemesananAdapter(Context context, ArrayList<PemesananModel> pemesananModelArrayList){
        this.pemesananModelArrayList = pemesananModelArrayList;
        this.context = context;
    }

    @Override
    public PemesananViewHolder onCreateViewHolder( ViewGroup parent, int viewType){
        View view = LayoutInflater.from(context).inflate(R.layout.list, parent, false);
        return new PemesananViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull PemesananAdapter.PemesananViewHolder holder, int position) {
        PemesananModel pemesananModel = pemesananModelArrayList.get(position);

        holder.PackageName.setText(pemesananModel.getNamapackage());
        holder.PemesanName.setText(pemesananModel.getNamacustomer());
        holder.tanggalPemesanan.setText(pemesananModel.getTanggal());

    }

    @Override
    public int getItemCount() {
        return pemesananModelArrayList.size();
    }

    public static class PemesananViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener{
        TextView PackageName;
        TextView PemesanName;
        TextView tanggalPemesanan;

        public PemesananViewHolder(@NonNull View itemView){
            super(itemView);

            PackageName = itemView.findViewById(R.id.txt_product);
            PemesanName = itemView.findViewById(R.id.txt_nama_customer);
            tanggalPemesanan = itemView.findViewById(R.id.txt_tanggal);

            //menambahkan listener klik pada itemview
           /* itemView.setOnClickListener(this);*/
        }
        @Override
        public void onClick(View v) {
            int position = getAdapterPosition();
            Intent intent = new Intent(context, DetailPesanan.class);
            intent.putExtra("package", pemesananModelArrayList.get(position).getNamapackage());
            intent.putExtra("nama", pemesananModelArrayList.get(position).getNamacustomer());
            intent.putExtra("no_hp", pemesananModelArrayList.get(position).getNo_hp());
            intent.putExtra("tanggal", pemesananModelArrayList.get(position).getTanggal());
            intent.putExtra("alamat", pemesananModelArrayList.get(position).getAlamat());
            intent.putExtra("quota", pemesananModelArrayList.get(position).getQuota());
            intent.putExtra("unlimited", pemesananModelArrayList.get(position).getUnlimited());
            intent.putExtra("layout", pemesananModelArrayList.get(position).getNama_layout());
           /* intent.putExtra("harga", pemesananModelArrayList.get(position).getHarga());*/
            intent.putExtra("bukti_bayar", pemesananModelArrayList.get(position).getBukti_pembayaran());
            context.startActivity(intent);
        }

        }
}
