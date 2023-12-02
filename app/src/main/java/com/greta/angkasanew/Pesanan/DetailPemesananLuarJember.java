package com.greta.angkasanew.Pesanan;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import com.android.volley.AuthFailureError;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.bumptech.glide.Glide;
import com.greta.angkasanew.API.Api;
import com.greta.angkasanew.Adapter.PemesananJemberAdapter;
import com.greta.angkasanew.Adapter.PemesananLuarJemberAdapter;
import com.greta.angkasanew.Model.PemesananJemberModel;
import com.greta.angkasanew.Model.PemesananLuarJemberModel;
import com.greta.angkasanew.R;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

public class DetailPemesananLuarJember extends AppCompatActivity {
    private static ArrayList<PemesananLuarJemberModel> pemesananLuarJemberArrayList;
    private PemesananLuarJemberAdapter pemesananLuarJemberAdapter;
    TextView nama_customer, No_Telp, tanggal, alamat, nama_packag, nama_layout, nama_qouta, nama_unlimited, id_luar_jember;
    Button btn_selesai;

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
        id_luar_jember = findViewById(R.id.id_pemesanan_luarjember);
        btn_selesai = findViewById(R.id.btn_selesai_luar_jember);

        Bundle extras = getIntent().getExtras();

        String nama = getIntent().getStringExtra("nama");
        String packag = getIntent().getStringExtra("package");
        String No_Hp = getIntent().getStringExtra("no_hp");
        String tanggal_acara = getIntent().getStringExtra("tanggal");
        String alamat_acara = getIntent().getStringExtra("alamat");
        String unlimited = getIntent().getStringExtra("unlimited");
        String layout = getIntent().getStringExtra("layout");
        String quota = getIntent().getStringExtra("quota");
        String id_pemesanan = getIntent().getStringExtra("id");

        nama_customer.setText(nama);
        No_Telp.setText(No_Hp);
        tanggal.setText(tanggal_acara);
        alamat.setText(alamat_acara);
        nama_packag.setText(packag);
        nama_layout.setText(layout);
        nama_qouta.setText(quota);
        nama_unlimited.setText(unlimited);
        id_luar_jember.setText(id_pemesanan);

        btn_selesai.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                update();
                /* Log.e("status", id_promo.getText().toString());*/
            }
        });
    }

    private void update() {
        StringRequest request = new StringRequest(Request.Method.POST, Api.urlStatus, response -> {
            try {
                //ini ngambil data
                JSONObject jsonObject = new JSONObject(response);

                int code = jsonObject.getInt("code");
                String status = jsonObject.getString("status");
                TextView konfirmasi = findViewById(R.id.txt_status);

                if (code == 200 && "Sukses".equals(status)) {
                    int positionToUpdate = findItemPositionById(id_luar_jember.getText().toString().trim());

                    if (positionToUpdate != -1) {
                        PemesananLuarJemberModel selectedItem = pemesananLuarJemberArrayList.get(positionToUpdate);
                        selectedItem.setStatus("Selesai");
                        pemesananLuarJemberAdapter.notifyDataSetChanged();
                        Toast.makeText(getApplicationContext(), "Pesanan Telah Selesai", Toast.LENGTH_SHORT).show();
                    } else {
                        Toast.makeText(getApplicationContext(), "Item Not Found", Toast.LENGTH_SHORT).show();
                    }
                } else {
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
                map.put("id_pemesanan", id_luar_jember.getText().toString().trim());
                return map;
            }
        };
        RequestQueue queue = Volley.newRequestQueue(this);
        queue.add(request);
    }

    private int findItemPositionById(String itemId) {
        for (int i = 0; i < pemesananLuarJemberArrayList.size(); i++) {
            PemesananLuarJemberModel item = pemesananLuarJemberArrayList.get(i);
            if (item.getId_pemesanan().equals(itemId)) {
                return i; // Found the item, return its position
            }
        }
        return -1; // Item not found
    }
}
