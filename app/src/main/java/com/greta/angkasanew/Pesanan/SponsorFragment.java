package com.greta.angkasanew.Pesanan;

import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.Volley;
import com.greta.angkasanew.API.Api;
import com.greta.angkasanew.Adapter.PemesananAdapter;
import com.greta.angkasanew.Model.PemesananModel;
import com.greta.angkasanew.R;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link SponsorFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class SponsorFragment extends Fragment {
    private RequestQueue requestQueue;
    private View rootView;
    private ArrayList<PemesananModel> pemesananModelArrayList;
    private String[] nama_package;
    private String[] nama_cust;
    private RecyclerView recyclerview;
    private PemesananAdapter pemesananAdapter;

    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;

    public SponsorFragment() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment SponsorFragment.
     */
    // TODO: Rename and change types and number of parameters
    public static SponsorFragment newInstance(String param1, String param2) {
        SponsorFragment fragment = new SponsorFragment();
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
        rootView = inflater.inflate(R.layout.fragment_sponsor, container, false);
        recyclerview = rootView.findViewById(R.id.ryclesponsor);
        recyclerview.setHasFixedSize(true); //lebar dan tinggi konsisten.
        RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(getContext()); //menyusun item
        recyclerview.setLayoutManager(layoutManager); //mengontrol tata letak

        pemesananModelArrayList = new ArrayList<>();
        if (getContext() != null) {
            requestQueue = Volley.newRequestQueue(getContext());
            perseJSON(); //panggil method JSON
        } else {
            Toast.makeText(getContext(), "Konteks Null", Toast.LENGTH_SHORT).show();
        }
        return rootView;
    }
    private void perseJSON(){

        JsonObjectRequest request = new JsonObjectRequest(Request.Method.GET, Api.urlPemesananProposal, null,
                new Response.Listener<JSONObject>() {
                    @Override
                    public void onResponse(JSONObject response) {
                        try {
                            JSONArray jsonArray = response.getJSONArray("data");

                            for (int i = 0; i < jsonArray.length(); i++) {
                                JSONObject hit = jsonArray.getJSONObject(i);

                                String nama_package = hit.getString("nama_package");
                                String nama_cust = hit.getString("nama_cust");
                                String tanggal = hit.getString("tanggal_acara");
                                String id = hit.getString("id_pemesanan");
                                String no_hp = hit.getString("no_hp");
                                String nama_layout = hit.getString("nama_layout");
                                String quota = hit.getString("nama_quota");
                                String unlimited = hit.getString("nama_unlimited");
                                String bukti_bayar = hit.getString("bukti_bayar");
                                String alamat = hit.getString("alamat_acara");

                                pemesananModelArrayList.add(new PemesananModel( nama_cust,nama_package, tanggal, id,no_hp,nama_layout,alamat,quota,unlimited,bukti_bayar));
                            }

                            pemesananAdapter = new PemesananAdapter(getContext(), pemesananModelArrayList);
                            recyclerview.setAdapter(pemesananAdapter);

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