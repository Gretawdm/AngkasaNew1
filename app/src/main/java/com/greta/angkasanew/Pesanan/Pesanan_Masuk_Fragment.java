package com.greta.angkasanew.Pesanan;

import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentStatePagerAdapter;
import androidx.viewpager.widget.ViewPager;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.google.android.material.tabs.TabLayout;
import com.greta.angkasanew.Adapter.Pemesanan_ViewPagerAdapter;
import com.greta.angkasanew.R;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link Pesanan_Masuk_Fragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class Pesanan_Masuk_Fragment extends Fragment {

    private TabLayout tabLayout;
    private ViewPager viewPager;
    private View mView;
    private TextView txtNamaLengkap;
    private String KEY_NAME = "NAMA";


    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;

    public Pesanan_Masuk_Fragment() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment Pesanan_Masuk_Fragment.
     */
    // TODO: Rename and change types and number of parameters
    public static Pesanan_Masuk_Fragment newInstance(String param1, String param2) {
        Pesanan_Masuk_Fragment fragment = new Pesanan_Masuk_Fragment();
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


        mView = inflater.inflate(R.layout.fragment_pesanan__masuk_, container, false);

        txtNamaLengkap = (TextView) mView.findViewById(R.id.nama_lengkap);
        // Mendapatkan data dari Intent
        Bundle extras = getActivity().getIntent().getExtras();
        String nama_lengkap = extras.getString(KEY_NAME);
        txtNamaLengkap.setText(nama_lengkap);

        tabLayout = mView.findViewById(R.id.tab_layout);
        viewPager = mView.findViewById(R.id.pesanan_viewpager);

        Pemesanan_ViewPagerAdapter adapter = new Pemesanan_ViewPagerAdapter(getChildFragmentManager(), FragmentStatePagerAdapter.BEHAVIOR_RESUME_ONLY_CURRENT_FRAGMENT);
        viewPager.setAdapter(adapter);

        tabLayout.setupWithViewPager(viewPager);

        return mView;


    }
}