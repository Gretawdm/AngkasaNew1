package com.greta.angkasanew.Pesanan;

import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;
import android.util.Log;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.Volley;
import com.greta.angkasanew.API.Api;
import com.greta.angkasanew.Adapter.PemesananPromoAdapter;
import com.greta.angkasanew.Model.PemesananPromoModel;
import com.greta.angkasanew.R;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link PromoFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class PromoFragment extends Fragment {
    private RequestQueue requestQueue;
    private View rootView;
    private ArrayList<PemesananPromoModel> pemesananPromoModelArrayList;
    private RecyclerView recyclerview;
    private PemesananPromoAdapter pemesananPromoAdapter;

    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;

    public PromoFragment() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment PromoFragment.
     */
    // TODO: Rename and change types and number of parameters
    public static PromoFragment newInstance(String param1, String param2) {
        PromoFragment fragment = new PromoFragment();
        Bundle args = new Bundle();
        args.putString(ARG_PARAM1, param1);
        args.putString(ARG_PARAM2, param2);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            mParam1 = getArguments().getString(ARG_PARAM1);
            mParam2 = getArguments().getString(ARG_PARAM2);
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        rootView = inflater.inflate(R.layout.fragment_promo, container, false);
        recyclerview = rootView.findViewById(R.id.ryclepemesananpromo);
        recyclerview.setHasFixedSize(true); //lebar dan tinggi konsisten.
        RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(getContext()); //menyusun item
        recyclerview.setLayoutManager(layoutManager); //mengontrol tata letak
        pemesananPromoModelArrayList = new ArrayList<>();
        if (getContext() != null) {
            requestQueue = Volley.newRequestQueue(getContext());
            perseJSON(); //panggil method JSON
        } else {
            Toast.makeText(getContext(), "Konteks Null", Toast.LENGTH_SHORT).show();
        }
        return rootView;
    }
    private void perseJSON(){

        JsonObjectRequest request = new JsonObjectRequest(Request.Method.GET, Api.urlPemesananPromo, null,
                new Response.Listener<JSONObject>() {
                    @Override
                    public void onResponse(JSONObject response) {
                        try {
                            JSONArray jsonArray = response.getJSONArray("data");

                            for (int i = 0; i < jsonArray.length(); i++) {
                                JSONObject hit = jsonArray.getJSONObject(i);

                                String Bulan = hit.getString("bulan");
                                String Status = hit.getString("status");
                                String judulpromo = hit.getString("judul_promo");
                                String promo = hit.getString("nama_promo");
                                String namacustomer = hit.getString("nama_cust");
                                String No_telp = hit.getString("no_hp");
                                String alamat = hit.getString("alamat_acara");
                                String harga = hit.getString("harga_promo");
                                String id = hit.getString("id_pemesanan");

                                pemesananPromoModelArrayList.add(new PemesananPromoModel(judulpromo,namacustomer,Status,id,promo,No_telp,alamat,harga,Bulan));
                            }

                            pemesananPromoAdapter = new PemesananPromoAdapter(getContext(), pemesananPromoModelArrayList);
                            recyclerview.setAdapter(pemesananPromoAdapter);

                        } catch (JSONException e) {
                            e.printStackTrace();
                        }
                    }
                }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                error.printStackTrace();
            }
        });
        requestQueue.add(request);
    }
}