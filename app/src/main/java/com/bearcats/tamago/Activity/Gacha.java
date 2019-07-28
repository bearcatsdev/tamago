 package com.bearcats.tamago.Activity;

import android.animation.Animator;
import android.app.Activity;
import android.content.Context;
import android.os.Handler;
import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;
import androidx.cardview.widget.CardView;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import com.airbnb.lottie.LottieAnimationView;
import com.airbnb.lottie.LottieDrawable;
import com.bearcats.tamago.R;
import com.bearcats.tamago.preferences.ChildPreferences;

import static java.security.AccessController.getContext;

 public class Gacha extends AppCompatActivity {
    TextView egg_count;
    CardView gacha;
    LottieAnimationView gacha_idle,gacha_roll;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_gacha);

        egg_count = findViewById(R.id.egg_count);
        gacha = findViewById(R.id.btn_gacha);
        gacha_idle = findViewById(R.id.gacha_idle);
        gacha_roll = findViewById(R.id.gacha_roll);

        egg_count.setText(ChildPreferences.getChildEgg()) ;

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

        gacha_roll.addAnimatorListener(new Animator.AnimatorListener() {
            @Override
            public void onAnimationStart(Animator animation) {

            }

            @Override
            public void onAnimationEnd(Animator animation) {
                gacha_idle.setVisibility(View.VISIBLE);
                gacha_roll.setVisibility(View.INVISIBLE);
            }

            @Override
            public void onAnimationCancel(Animator animation) {

            }

            @Override
            public void onAnimationRepeat(Animator animation) {

            }
        });

        gacha.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                gacha.animate().translationY(20).setDuration(100);
                handler.postDelayed(runnable,100);
//                if(ChildPreferences.getChildEgg(Gacha.this)>=15){
                    gacha_idle.setVisibility(View.INVISIBLE);
                    gacha_roll.setVisibility(View.VISIBLE);
                    gacha_roll.playAnimation();
//                    ChildPreferences.setChildEgg(Gacha.this, ChildPreferences.getChildEgg(Gacha.this) + 15);
//                    egg_count.setText(ChildPreferences.getChildEgg(Gacha.this)) ;
//                }
//                else{
//                    Toast.makeText(Gacha.this, "Insufficient egg", Toast.LENGTH_SHORT).show();
//                }

            }
        });


    }
}
