package com.greta.angkasanew.Pesanan;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import com.android.volley.AuthFailureError;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.greta.angkasanew.API.Api;
import com.greta.angkasanew.Adapter.PemesananJemberAdapter;
import com.greta.angkasanew.Adapter.PemesananSponsorAdapter;
import com.greta.angkasanew.Model.PemesananJemberModel;
import com.greta.angkasanew.Model.PemesananSponsorModel;
import com.greta.angkasanew.R;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

public class DetailPesananSponsor extends AppCompatActivity {
    private static ArrayList<PemesananSponsorModel> pemesananSponsorArrayList;
    private PemesananSponsorAdapter pemesananSponsorAdapter;
    TextView nama_cust,no_hp,tanggal_acara,alamat_acara,proposal_acara,id_sponsor;
    Button btn_selesai;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.detail_pemesanan_sponsor);

        nama_cust = findViewById(R.id.txt_nama_sponsor);
        no_hp = findViewById(R.id.txt_nohp_sponsor);
        tanggal_acara = findViewById(R.id.txt_tanggal_sponsor);
        alamat_acara = findViewById(R.id.txt_alamat_sponsor);
        proposal_acara = findViewById(R.id.txt_proposal);
        id_sponsor = findViewById(R.id.id_sponsor);

        btn_selesai = findViewById(R.id.btn_selesai);

        Bundle extras = getIntent().getExtras();

        String cust = getIntent().getStringExtra("nama_cust");
        String no_telp = getIntent().getStringExtra("no_hp");
        String tanggal = getIntent().getStringExtra("tanggal_acara");
        String alamat = getIntent().getStringExtra("alamat_acara");
        String proposal = getIntent().getStringExtra("proposal");
        String id_pemesanan = getIntent().getStringExtra("id_pemesanan");

        nama_cust.setText(cust);
        no_hp.setText(no_telp);
        tanggal_acara.setText(tanggal);
        alamat_acara.setText(alamat);
        proposal_acara.setText(proposal);
        id_sponsor.setText(id_pemesanan);


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
                TextView konfirmasi = findViewById(R.id.txt_status_sponsor);

                if (code == 200 && status.equals("Sukses")) {
                    int positionToUpdate = findItemPositionById(id_sponsor.getText().toString().trim());

                    if (positionToUpdate != -1) {
                        PemesananSponsorModel selectedItem = pemesananSponsorArrayList.get(positionToUpdate);
                        selectedItem.setStatus("Selesai");
                        pemesananSponsorAdapter.notifyDataSetChanged();
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
                map.put("id_pemesanan", id_sponsor.getText().toString().trim());
                return map;
            }
        };
        RequestQueue queue = Volley.newRequestQueue(this);
        queue.add(request);
    }
    private int findItemPositionById(String itemId) {
        for (int i = 0; i < pemesananSponsorArrayList.size(); i++) {
            PemesananSponsorModel item = pemesananSponsorArrayList.get(i);
            if (item.getId_pemesanan().equals(itemId)) {
                return i; // Found the item, return its position
            }
        }
        return -1; // Item not found
    }

}
