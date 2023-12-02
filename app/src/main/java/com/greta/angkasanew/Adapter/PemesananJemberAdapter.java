package com.greta.angkasanew.Adapter;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.util.Log;

import androidx.annotation.NonNull;
import androidx.core.content.ContextCompat;
import androidx.recyclerview.widget.RecyclerView;

import com.greta.angkasanew.Model.PemesananJemberModel;
import com.greta.angkasanew.Pesanan.DetailPemesananJember;
import com.greta.angkasanew.R;

import java.util.ArrayList;

public class PemesananJemberAdapter extends RecyclerView.Adapter<PemesananJemberAdapter.PemesananJemberViewHolder> {
    private static ArrayList<PemesananJemberModel>pemesananJemberModelArrayList;

    private static Context context;

    public PemesananJemberAdapter(Context context, ArrayList<PemesananJemberModel> pemesananJemberModelArrayList){
        this.pemesananJemberModelArrayList = pemesananJemberModelArrayList;
        this.context = context;
    }
    @Override
   public PemesananJemberViewHolder onCreateViewHolder(ViewGroup parent, int viewType){
        View view = LayoutInflater.from(context).inflate(R.layout.list_jember, parent, false);
        return new PemesananJemberAdapter.PemesananJemberViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull PemesananJemberAdapter.PemesananJemberViewHolder holder, int position) {
        PemesananJemberModel pemesananJemberModel = pemesananJemberModelArrayList.get(position);

        holder.PackageName.setText(pemesananJemberModel.getNamapackage());
        holder.PemesanName.setText(pemesananJemberModel.getNamacustomer());
        holder.tanggalPemesanan.setText(pemesananJemberModel.getTanggal());
        holder.status.setText(pemesananJemberModel.getStatus());

        if("Selesai".equals(pemesananJemberModel.getStatus())) {
            holder.status.setTextColor(ContextCompat.getColor(holder.itemView.getContext(), R.color.hijau));
        }else {
            holder.status.setTextColor(ContextCompat.getColor(holder.itemView.getContext(), R.color.merah));
        }
    }

    @Override
    public int getItemCount() {
        return pemesananJemberModelArrayList.size();
    }

    public static class PemesananJemberViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener{
        TextView PackageName;
        TextView PemesanName;
        TextView tanggalPemesanan;
        TextView status;

        public PemesananJemberViewHolder(@NonNull View itemView){
            super(itemView);


            PackageName = itemView.findViewById(R.id.txt_product_jember);
            PemesanName = itemView.findViewById(R.id.txt_nama_customer_jember);
            tanggalPemesanan = itemView.findViewById(R.id.txt_tanggal_jember);
            status = itemView.findViewById(R.id.txt_status_jember);

            //menambahkan listener klik pada itemview
            itemView.setOnClickListener(this);
        }
        @Override
        public void onClick(View v) {
            int position = getAdapterPosition();
            Intent intent = new Intent(context, DetailPemesananJember.class);
            intent.putExtra("package", pemesananJemberModelArrayList.get(position).getNamapackage());
            intent.putExtra("nama", pemesananJemberModelArrayList.get(position).getNamacustomer());
            intent.putExtra("no_hp", pemesananJemberModelArrayList.get(position).getNo_hp());
            intent.putExtra("tanggal", pemesananJemberModelArrayList.get(position).getTanggal());
            intent.putExtra("alamat", pemesananJemberModelArrayList.get(position).getAlamat());
            intent.putExtra("quota", pemesananJemberModelArrayList.get(position).getQuota());
            intent.putExtra("unlimited", pemesananJemberModelArrayList.get(position).getUnlimited());
            intent.putExtra("layout", pemesananJemberModelArrayList.get(position).getNama_layout());
            intent.putExtra("hargaqouta", pemesananJemberModelArrayList.get(position).getHarga_qouta());
            intent.putExtra("hargaunlimited", pemesananJemberModelArrayList.get(position).getHarga_unlimited());
            intent.putExtra("bukti_bayar", pemesananJemberModelArrayList.get(position).getBukti_pembayaran());
            intent.putExtra("id", pemesananJemberModelArrayList.get(position).getId_pemesanan());
            context.startActivity(intent);

        }

    }
}
