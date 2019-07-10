package com.bearcats.tamago;

import android.os.Handler;
import android.support.design.bottomappbar.BottomAppBar;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageButton;

import com.airbnb.lottie.LottieAnimationView;

public class MainActivity extends AppCompatActivity {

    ImageButton ayam;
    boolean zoomin = true;
    BottomAppBar bottomAppBar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        bottomAppBar = findViewById(R.id.bar);

        final Handler handler = new Handler();

        final Runnable runnable = new Runnable() {
            @Override
            public void run() {
                ayam.animate().scaleX(1f).scaleY(1f).setDuration(70);
                ayam.setClickable(true);
            }
        };

        ayam = findViewById(R.id.ayam);
        ayam.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                ayam.setClickable(false);
                ayam.animate().scaleX(1f).scaleY(0.8f).setDuration(100);
                handler.postDelayed(runnable,100);
            }
        });
    }
}
