package com.greta.angkasanew.Diskon;

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
import com.greta.angkasanew.Adapter.DiskonAdapter;
import com.greta.angkasanew.Model.DiskonModel;
import com.greta.angkasanew.Model.PemesananModel;
import com.greta.angkasanew.R;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link DiskonFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class DiskonFragment extends Fragment {
    private RequestQueue requestQueue;
    private View rootView;
    private ArrayList<DiskonModel> diskonModelArrayList;
    private RecyclerView recyclerview;
    private DiskonAdapter diskonAdapter;

    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;

    public DiskonFragment() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment DiskonFragment.
     */
    // TODO: Rename and change types and number of parameters
    public static DiskonFragment newInstance(String param1, String param2) {
        DiskonFragment fragment = new DiskonFragment();
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

        rootView = inflater.inflate(R.layout.fragment_diskon, container, false);
        recyclerview = rootView.findViewById(R.id.ryclepromo);
        recyclerview.setHasFixedSize(true);
        RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(getContext());
        recyclerview.setLayoutManager(layoutManager);

        diskonModelArrayList = new ArrayList<>();
        if (getContext() != null) {
            requestQueue = Volley.newRequestQueue(getContext());
            perseJSON(); //panggil method JSON
        } else {
            Toast.makeText(getContext(), "Konteks Null", Toast.LENGTH_SHORT).show();
        }
        return rootView;
    }
    private void perseJSON(){
        /*String url = "http://192.168.1.3/project_sem3/ApiPemesanan.php";*/

        JsonObjectRequest request = new JsonObjectRequest(Request.Method.GET, Api.urlPromo, null,
                new Response.Listener<JSONObject>() {
                    @Override
                    public void onResponse(JSONObject response) {
                        try {
                            JSONArray jsonArray = response.getJSONArray("data");

                            for (int i = 0; i < jsonArray.length(); i++) {
                                JSONObject hit = jsonArray.getJSONObject(i);

                                String bulan = hit.getString("bulan");
                                String judul_promo = hit.getString("judul_promo");
                                String nama_promo = hit.getString("nama_promo");
                                String harga = hit.getString("harga_promo");
                                Integer id_promo = hit.getInt("id_promo");

                                diskonModelArrayList.add(new DiskonModel(id_promo,bulan,judul_promo,harga,nama_promo));
                            }

                            diskonAdapter = new DiskonAdapter(getContext(), diskonModelArrayList);
                            recyclerview.setAdapter(diskonAdapter);

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
