package com.greta.angkasanew.Adapter;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentStatePagerAdapter;

import com.greta.angkasanew.Pesanan.JemberFragment;
import com.greta.angkasanew.Pesanan.LuarJemberFragment;
import com.greta.angkasanew.Pesanan.PromoFragment;
import com.greta.angkasanew.Pesanan.SponsorFragment;

public class Pemesanan_ViewPagerAdapter extends FragmentStatePagerAdapter {
    public Pemesanan_ViewPagerAdapter(@NonNull FragmentManager fm, int behavior){super(fm, behavior);}
    @NonNull
    @Override
    public Fragment getItem(int position) {
        switch (position) {
            case 0:
                return new JemberFragment();
            case 1:
                return new LuarJemberFragment();
            case 2:
                return new SponsorFragment();
            case 3:
                return new PromoFragment();
            default:
                return new JemberFragment();
        }
    }


    @Override
    public int getCount() {
        return 4;
    }
    @Nullable
    @Override
    public CharSequence getPageTitle(int position) {
        switch (position){
            case 0:
                return "Jember";

            case 1:
                return "Luar";

            case 2:
                return "Sponsor";

            case 3:
                return "Promo";

            default:
                return "Jember";
        }
    }

}
