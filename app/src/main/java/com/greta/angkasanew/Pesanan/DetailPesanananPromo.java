package com.greta.angkasanew.Pesanan;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.android.volley.AuthFailureError;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.bumptech.glide.Glide;
import com.greta.angkasanew.API.Api;
import com.greta.angkasanew.R;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.HashMap;
import java.util.Map;

public class DetailPesanananPromo extends AppCompatActivity {
    TextView judul_promo,nama_promo,nama_cust,no_hp,alamat_acara,harga, id_promo;
    Button btn_selesai;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_detail_pesananan_promo);

        judul_promo = findViewById(R.id.txt_judulpromo);
        nama_promo = findViewById(R.id.txt_namapromo);
        nama_cust = findViewById(R.id.txt_namacust);
        no_hp = findViewById(R.id.txt_no_hppromo);
        alamat_acara = findViewById(R.id.txt_alamatpromo);
        harga = findViewById(R.id.txt_hargapromo);
        id_promo = findViewById(R.id.id_pemesanan);

        btn_selesai = findViewById(R.id.btn_selesai);

        Bundle extras = getIntent().getExtras();

        String judul = getIntent().getStringExtra("judul_promo");
        String promo = getIntent().getStringExtra("nama_promo");
        String cust = getIntent().getStringExtra("nama_cust");
        String no_telp = getIntent().getStringExtra("no_hp");
        String alamat = getIntent().getStringExtra("alamat_acara");
        String harga_promo = getIntent().getStringExtra("harga_promo");
        String id_pemesanan = getIntent().getStringExtra("id");


        judul_promo.setText(judul);
        nama_promo.setText(promo);
        nama_cust.setText(cust);
        no_hp.setText(no_telp);
        alamat_acara.setText(alamat);
        harga.setText(harga_promo);
        id_promo.setText(id_pemesanan);


        btn_selesai.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                update();
                /* Log.e("status", id_promo.getText().toString());*/
            }
        });
    }

    private void update(){
        StringRequest request = new StringRequest(Request.Method.POST, Api.urlStatus, response -> {
            try {
                //ini ngambil data
                JSONObject jsonObject = new JSONObject(response);

                int code = jsonObject.getInt("code");
                String status = jsonObject.getString("status");

                if (code == 200 && status.equals("Sukses")) {

                    Toast.makeText(getApplicationContext(), "Pesanan Telah Selesai Dilakukan", Toast.LENGTH_SHORT).show();
                  /*  Intent intent = new Intent(DetailDiskon.this, MainActivity.class);
                    startActivity(intent);*/
                }else{
                    Toast.makeText(getApplicationContext(), "Gagal", Toast.LENGTH_SHORT).show();

                }
            } catch (JSONException e) {
                e.printStackTrace();
                Toast.makeText(getApplicationContext(), "JSON ERROR", Toast.LENGTH_SHORT).show();
            }
        }, error -> {
            error.printStackTrace();
            Toast.makeText(getApplicationContext(), "Network Error", Toast.LENGTH_SHORT).show();
        }) {
            @Nullable
            @Override
            protected Map<String, String> getParams() throws AuthFailureError {
                Map<String, String> map = new HashMap<>();

                map.put("status", "Selesai");
                map.put("id_pemesanan", id_promo.getText().toString().trim());
                return map;
            }
        };
        RequestQueue queue = Volley.newRequestQueue(this);
        queue.add(request);
    }

}