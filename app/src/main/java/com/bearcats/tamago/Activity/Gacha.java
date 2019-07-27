 package com.bearcats.tamago.Activity;

import android.os.Handler;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.CardView;
import android.view.View;

import com.airbnb.lottie.LottieAnimationView;
import com.bearcats.tamago.R;

 public class Gacha extends AppCompatActivity {

    CardView gacha;
    LottieAnimationView gachanimation;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_gacha);

        gacha = findViewById(R.id.btn_gacha);
        gachanimation = findViewById(R.id.gachanimation);

        final Handler handler = new Handler();

        final Runnable runnable1 = new Runnable() {
            @Override
            public void run() {
               gacha.setClickable(true);
            }
        };

        final Runnable runnable = new Runnable() {
            @Override
            public void run() {
                gacha.animate().translationY(0).setDuration(100);
                handler.postDelayed(runnable1,100);
            }
        };

        gacha.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                gacha.animate().translationY(20).setDuration(100);
                handler.postDelayed(runnable,100);
                gachanimation.setProgress(0);
                gachanimation.setAnimation("gacha_roll.json");
                gachanimation.setRepeatCount(0);
                gachanimation.playAnimation();
            }
        });
    }
}
