package com.greta.angkasanew.Main;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

import android.os.Bundle;
import android.view.MenuItem;
import android.widget.FrameLayout;

import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.android.material.navigation.NavigationBarView;
import com.greta.angkasanew.AKun.AkunFragment;
import com.greta.angkasanew.Home.HomeFragment;
import com.greta.angkasanew.Pesanan.Pesanan_Masuk_Fragment;
import com.greta.angkasanew.R;
import com.greta.angkasanew.Diskon.DiskonFragment;

public class MainActivity extends AppCompatActivity {

    FloatingActionButton fab;
    private FrameLayout flFragment;
    BottomNavigationView bottomNavigationView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        if (savedInstanceState == null) {
            getSupportFragmentManager().beginTransaction().replace(R.id.flFragment, new HomeFragment()).commit();
        }

        bottomNavigationView = findViewById(R.id.bottomView);

        bottomNavigationView.setOnItemSelectedListener(new NavigationBarView.OnItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {
                if (item.getItemId() == R.id.dashboard) {
                    getfragment(new HomeFragment());
                    return true;
                } else if (item.getItemId() == R.id.pemesanan) {
                    getfragment(new Pesanan_Masuk_Fragment());
                    return true;
                } else if (item.getItemId() == R.id.diskon) {
                    getfragment(new DiskonFragment());
                    return true;
                } else if (item.getItemId() == R.id.akun) {
                    getfragment(new AkunFragment());
                    return true;
                }
                return false;
            }
        });
    }


        private void getfragment(Fragment fragment){
        FragmentManager fragmentManager = getSupportFragmentManager();
        FragmentTransaction fragmentTransaction = getSupportFragmentManager().beginTransaction();
        fragmentTransaction.replace(R.id.flFragment, fragment);
        fragmentTransaction.commit();
    }
}