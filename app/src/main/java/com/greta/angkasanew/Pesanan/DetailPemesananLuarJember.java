package com.greta.angkasanew.Pesanan;

import android.os.Bundle;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import com.bumptech.glide.Glide;
import com.greta.angkasanew.API.Api;
import com.greta.angkasanew.R;

public class DetailPemesananLuarJember extends AppCompatActivity {
    TextView nama_customer,No_Telp,tanggal,alamat,nama_packag,nama_layout,nama_qouta,nama_unlimited;
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.detail_pemesanan_luar_jember);

        nama_customer = findViewById(R.id.txt_namacust);
        No_Telp = findViewById(R.id.txt_nohp);
        tanggal = findViewById(R.id.txt_tanggal_luar);
        alamat = findViewById(R.id.txt_alamat);
        nama_packag = findViewById(R.id.txt_package);
        nama_layout = findViewById(R.id.txt_layout);
        nama_qouta = findViewById(R.id.txt_qouta);
        nama_unlimited = findViewById(R.id.txt_unlimited);

        Bundle extras = getIntent().getExtras();
        String nama = extras.getString("nama");
        String packag = extras.getString("package");

        String No_Hp = getIntent().getStringExtra("no_hp");
        String tanggal_acara = getIntent().getStringExtra("tanggal");
        String alamat_acara = getIntent().getStringExtra("alamat");
        String unlimited = getIntent().getStringExtra("unlimited");
        String layout = getIntent().getStringExtra("layout");
        String quota = getIntent().getStringExtra("quota");

        nama_customer.setText(nama);
        No_Telp.setText(No_Hp);
        tanggal.setText(tanggal_acara);
        alamat.setText(alamat_acara);
        nama_packag.setText(packag);
        nama_layout.setText(layout);
        nama_qouta.setText(quota);
        nama_unlimited.setText(unlimited);

    }
}
