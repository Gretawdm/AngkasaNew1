package com.greta.angkasanew.Diskon;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import android.util.Log;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import com.android.volley.AuthFailureError;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.greta.angkasanew.API.Api;
import com.greta.angkasanew.Login.LoginActivity;
import com.greta.angkasanew.Main.MainActivity;
import com.greta.angkasanew.R;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.HashMap;
import java.util.Map;

public class DetailDiskon extends AppCompatActivity {
    TextView judul_promo,nama_promo,id_pro;
    EditText harga;
    Button btn_send;

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.detail_diskon);

        judul_promo = findViewById(R.id.txt_judul_promo);
        nama_promo = findViewById(R.id.txt_nama_promo);
        harga = findViewById(R.id.txt_harga);
        id_pro = findViewById(R.id.id_promo);
        btn_send = findViewById(R.id.btn_send);

        Bundle extras = getIntent().getExtras();
        String judul = getIntent().getStringExtra("judul_promo");
        String promo = getIntent().getStringExtra("nama_promo");
        String hargapromo = getIntent().getStringExtra("harga");
        String id_promo = getIntent().getStringExtra("id_promo");

        /*String promo = extras.getString("nama_promo");
        Integer hargapromo = extras.getInt("harga");*/

        judul_promo.setText(judul);
        nama_promo.setText(promo);
        harga.setText(hargapromo);
        id_pro.setText(id_promo);

        btn_send.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
            update();
            Log.e("id", id_pro.getText().toString());
            }
        });
    }

    private void update(){
        StringRequest request = new StringRequest(Request.Method.POST, Api.urlUpdatePromo, response -> {
            try {
                //ini ngambil data
                JSONObject jsonObject = new JSONObject(response);

                int code = jsonObject.getInt("code");
                String status = jsonObject.getString("status");

                if (code == 200 && status.equals("Sukses")) {

                    Toast.makeText(getApplicationContext(), "Berhasil di ubah", Toast.LENGTH_SHORT).show();
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
                map.put("harga", harga.getText().toString().trim());
                map.put("id_promo", id_pro.getText().toString().trim());

                /*map.put("status", "Selesai");
                map.put("id_pemesanan", id_pemesanan.getText().toString().trim());*/
                return map;
            }
        };
        RequestQueue queue = Volley.newRequestQueue(this);
        queue.add(request);    }
}