package com.greta.angkasanew.Adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.greta.angkasanew.News;
import com.greta.angkasanew.R;

import java.util.ArrayList;

public class Adapter extends RecyclerView.Adapter<Adapter.MyViewHolder>{

    Context context;
    ArrayList<News> newsArrayList;

    public Adapter(ArrayList<News> newsArrayList) {
        this.context = context;
        this.newsArrayList = newsArrayList;
    }

    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater layoutInflater = LayoutInflater.from(parent.getContext());
        View view = layoutInflater.inflate(R.layout.list, parent, false);
        return new Adapter.MyViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull MyViewHolder holder, int position) {
        News news = newsArrayList.get(position);
        holder.namapakacge.setText(newsArrayList.get(position).getNamapackage());
        holder.namacustomer.setText(newsArrayList.get(position).getNamapemesan());

    }

    @Override
    public int getItemCount() {

        return (newsArrayList != null) ? newsArrayList.size() : 0;
    }

    public static class MyViewHolder extends RecyclerView.ViewHolder {

        TextView namapakacge;
        TextView namacustomer;



        public MyViewHolder(@NonNull View itemView) {
            super(itemView);
            namapakacge = itemView.findViewById(R.id.txt_product);
            namacustomer= itemView.findViewById(R.id.txt_nama_customer);
        }

        }
    }

