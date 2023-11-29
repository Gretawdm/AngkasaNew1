package com.greta.angkasanew.SplashScreen;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityOptionsCompat;
import androidx.core.view.ViewCompat;
import androidx.transition.ChangeBounds;
import androidx.transition.ChangeImageTransform;
import androidx.transition.TransitionManager;
import androidx.transition.TransitionSet;

import com.greta.angkasanew.Login.LoginActivity;
import com.greta.angkasanew.R;

public class splashscreen extends AppCompatActivity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.splash_screnn);

        ImageView imageView = findViewById(R.id.image);
        // Set transition name pada ImageView
        imageView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startSecondActivity(v);
            }
        });
    }
    // Other initialization code...
    public void startSecondActivity(View view) {
        Intent intent = new Intent(this, LoginActivity.class);

        // Pass any additional data to ActivityKedua if needed

        ActivityOptionsCompat options = ActivityOptionsCompat.makeSceneTransitionAnimation(
                this,
                findViewById(R.id.image), // The shared element
                ViewCompat.getTransitionName(findViewById(R.id.image)) // The transition name
        );

        startActivity(intent, options.toBundle());
    }


}
