package com.greta.angkasanew.Home;

import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
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
 * Use the {@link HomeFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class HomeFragment extends Fragment {


    private RequestQueue requestQueue;
    private View rootView;

    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;
    private TextView txtNamaLengkap;
    private String KEY_NAME = "NAMA";
    private ArrayList<PemesananModel> pemesananModelArrayList;
    private String[] nama_package;
    private String[] nama_cust;
    private RecyclerView recyclerview;
    private PemesananAdapter pemesananAdapter;


    //LinearLayoutManager layoutManager;
    //private TextView txtPesanan;
    //ViewPager2 viewPager2;

    public HomeFragment() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment HomeFragment.
     */
    // TODO: Rename and change types and number of parameters
    public static HomeFragment newInstance(String param1, String param2) {
        HomeFragment fragment = new HomeFragment();
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
        // Inflate the layout for this fragment
        rootView = inflater.inflate(R.layout.fragment_home, container, false);
        setRecyclerview(rootView); //manggil method recycler pesanan

        pemesananModelArrayList = new ArrayList<>();
        // Mendapatkan referensi TextView
        txtNamaLengkap = (TextView) rootView.findViewById(R.id.nama_lengkap);
        // Mendapatkan data dari Intent
        Bundle extras = getActivity().getIntent().getExtras();
        String nama_lengkap = extras.getString(KEY_NAME);
        txtNamaLengkap.setText(nama_lengkap);

        if (getContext() != null) {
            requestQueue = Volley.newRequestQueue(getContext());
            perseJSON(); //panggil method JSON
        } else {
            Toast.makeText(getContext(), "Konteks Null", Toast.LENGTH_SHORT).show();
        }

        return rootView;
    }

    //method recyler pesanan
    private void setRecyclerview(View rootView){
        recyclerview = rootView.findViewById(R.id.rycle);
        recyclerview.setHasFixedSize(true);
        RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(getContext());
        recyclerview.setLayoutManager(layoutManager);

      /*  RecyclerView recyclerTagihan = view.findViewById(R.id.recycletagihan);
        recyclerTagihan.setLayoutManager(new LinearLayoutManager(getActivity(),LinearLayoutManager.HORIZONTAL,false));
        TagihanAdapter adapterTagihan = new TagihanAdapter(getContext(), data,bottomSheet);
        recyclerTagihan.setAdapter(adapterTagihan);*/ //ini buat agar tampilan layout bisa digeser kanan kiri

    }

    private void perseJSON(){
     /*  String url = "http://192.168.21.87/project_sem3/ApiPemesanan.php";*/

        JsonObjectRequest request = new JsonObjectRequest(Request.Method.GET, Api.urlPemesananHome, null,
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





        //slidergalleryhome
        /*ViewPager2 viewPager2 = (ViewPager2) view.findViewById(R.id.smartSliderGallery);
        List<SlideItem> slideItems = new ArrayList<>();
        slideItems.add(new SlideItem(R.drawable.fotoslideawal));
        slideItems.add(new SlideItem(R.drawable.fotoslidekedua));
        slideItems.add(new SlideItem(R.drawable.slidefototiga));

        viewPager2.setAdapter(new SlideAdapter(slideItems, viewPager2));*/


       /* return view;



    }*/

    /*@Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);*/


        //dataInitialize();

        //recyclerview = view.findViewById(R.id.rycle);
        //recyclerview.setLayoutManager(new LinearLayoutManager(getContext()));
       /* recyclerview.setHasFixedSize(true);
        Adapter adapter = new Adapter(newArrayList);
        recyclerview.setAdapter(adapter);*/
        //adapter.notifyDataSetChanged();
    //}
  /*   private void data(){
        newArrayList = new ArrayList<>();
        newArrayList.add(new News("Self PhotoBox", "Greta Wahyu"));
        newArrayList.add(new News("Self Photo", "Insan"));
        newArrayList.add(new News("Manual Photobooth", "Wulan"));
        newArrayList.add(new News("Manual Photobooth", "Rafika"));
        newArrayList.add(new News("Video 350", "Dhika"));
     }
     private void setRecyclerview(View view){
        data();

        recyclerview = (RecyclerView) view.findViewById(R.id.rycle);
        adapter = new Adapter(newArrayList);
        RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(getActivity());
        recyclerview.setLayoutManager(layoutManager);
        recyclerview.setAdapter(adapter);



     }
*/
    /*private void dataInitialize() {

        newArrayList = new ArrayList<>();

        String[] nama_package = { getString(R.string.nama_package1),
                getString(R.string.nama_package2),
                getString(R.string.nama_package3),
                getString(R.string.nama_package4)};*/

        /*nama_package = new String[]{
                getString(R.string.nama_package1),
                getString(R.string.nama_package2),
                getString(R.string.nama_package3),
                getString(R.string.nama_package4),*/


        /*String[] nama_cust = { getString(R.string.nama_customer1),
                getString(R.string.nama_customer2),
                getString(R.string.nama_customer3),
                getString(R.string.nama_customer4)};*/

       /* nama_cust = new String[]{
                //getString(R.string.nama_customer1),
               // getString(R.string.nama_customer2),
                getString(R.string.nama_customer3),
                getString(R.string.nama_customer4),*/

        /*for (int i = 0; i< nama_package.length; i++){
            News news = new News(nama_package[i], nama_cust[i]);
            newArrayList.add(news);
        }*/


    /*public void onBackPressed() {
        new AlertDialog.Builder(requireActivity())
                .setMessage("Are you want to exit?")
                .setCancelable(false)
                .setPositiveButton("Yes", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        requireActivity().finish();
                    }
                })
                .setNegativeButton("No", null)
                .show();
    }*/


