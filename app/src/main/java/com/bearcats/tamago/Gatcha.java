 package com.bearcats.tamago;

import android.os.Handler;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.CardView;
import android.view.View;

 public class Gatcha extends AppCompatActivity {

    CardView gatcha;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_gatcha);

        gatcha = findViewById(R.id.btn_gatcha);

        final Handler handler = new Handler();

        final Runnable runnable1 = new Runnable() {
            @Override
            public void run() {
               gatcha.setClickable(true);
            }
        };

        final Runnable runnable = new Runnable() {
            @Override
            public void run() {
                gatcha.animate().translationY(0).setDuration(100);
                handler.postDelayed(runnable1,100);
            }
        };

        gatcha.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                gatcha.animate().translationY(20).setDuration(100);
                handler.postDelayed(runnable,100);

            }
        });
    }
}
