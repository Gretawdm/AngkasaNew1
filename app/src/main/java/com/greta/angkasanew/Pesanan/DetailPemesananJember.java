package com.greta.angkasanew.Pesanan;

import android.os.Bundle;
import android.widget.ImageView;
import android.widget.TextView;
import android.util.Log;
import androidx.appcompat.app.AppCompatActivity;

import com.bumptech.glide.Glide;
import com.greta.angkasanew.API.Api;
import com.greta.angkasanew.R;

import java.util.ArrayList;

public class DetailPemesananJember extends AppCompatActivity {
    TextView nama_customer,No_Telp,tanggal,alamat,nama_packag,nama_layout,nama_qouta,harga_qouta,nama_unlimited,harga_unlimited;
    ImageView bayar;
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.detail_pemesanan_jember);

        nama_customer = findViewById(R.id.txt_namacust_jembe);
        No_Telp = findViewById(R.id.txt_notelp);
        tanggal = findViewById(R.id.txt_tanggal_jember);
        alamat = findViewById(R.id.txt_alamat_jember);
        nama_packag = findViewById(R.id.txt_product_jember);
        nama_layout = findViewById(R.id.txt_nama_layout_jember);
        nama_qouta = findViewById(R.id.txt_qouta_jember);
        harga_qouta = findViewById(R.id.txt_harga_qouta_jember);
        nama_unlimited = findViewById(R.id.txt_unlimited_jember);
        harga_unlimited = findViewById(R.id.txt_harga_unlimited);
        bayar = findViewById(R.id.txt_bukti_pembayaran);

        Bundle extras = getIntent().getExtras();

        String nama = getIntent().getStringExtra("nama");
        String packag = getIntent().getStringExtra("package");
        String No_Hp = getIntent().getStringExtra("no_hp");
        String tanggal_acara = getIntent().getStringExtra("tanggal");
        String alamat_acara = getIntent().getStringExtra("alamat");
        String unlimited = getIntent().getStringExtra("unlimited");
        String layout = getIntent().getStringExtra("layout");
        String quota = getIntent().getStringExtra("quota");
        String hargaunlimited = getIntent().getStringExtra("hargaunlimited");
        String hargaqouta = getIntent().getStringExtra("hargaqouta");
        String bukti_bayar = getIntent().getStringExtra("bukti_bayar");
        if(bukti_bayar != null){
            String urlbuktibayar = "http://"+ Api.ip+"/Angkasa_Website/img/" + bukti_bayar;
            Glide.with(this).load(urlbuktibayar).into(bayar);
        }

        nama_customer.setText(nama);
        No_Telp.setText(No_Hp);
        tanggal.setText(tanggal_acara);
        alamat.setText(alamat_acara);
        nama_packag.setText(packag);
        nama_layout.setText(layout);
        nama_qouta.setText(quota);
        harga_qouta.setText(hargaqouta);
        nama_unlimited.setText(unlimited);
        harga_unlimited.setText(hargaunlimited);
        //bayar.setText(bukti_bayar);
    }
}
