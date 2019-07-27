package com.bearcats.tamago.Fragment;

import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.os.Handler;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.CardView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.FrameLayout;

import com.bearcats.tamago.Activity.Gacha;
import com.bearcats.tamago.R;
import com.sasank.roundedhorizontalprogress.RoundedHorizontalProgressBar;

public class Home extends Fragment {
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_home, container, false);
    }

    RoundedHorizontalProgressBar roundedHorizontalProgressBar;
    FrameLayout gachaButtonLayout, skinButtonLayout;
    CardView menuButton;
    boolean menuOpened = false;

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        menuOpened = false;
        roundedHorizontalProgressBar = view.findViewById(R.id.progress_bar);
        menuButton = view.findViewById(R.id.btn_menu);
        gachaButtonLayout = view.findViewById(R.id.gacha_buttonLayout);
        skinButtonLayout = view.findViewById(R.id.skin_buttonLayout);

        roundedHorizontalProgressBar.animateProgress(2000,0,50);

        final Handler handler = new Handler();

        final Runnable runnable1 = new Runnable() {
            @Override
            public void run() {
                menuButton.setClickable(true);
                if(!menuOpened){
                    showMenu();
                }
                else{
                    closeMenu();
                }
//                Intent intent = new Intent(getContext(),Gacha.class);
//                startActivity(intent);
            }
        };

        final Runnable runnable = new Runnable() {
            @Override
            public void run() {
                menuButton.animate().translationY(0).setDuration(100);
                handler.postDelayed(runnable1,100);
            }
        };

        menuButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                menuButton.animate().translationY(20).setDuration(100);
                handler.postDelayed(runnable,100);

            }
        });


        gachaButtonLayout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getContext(), Gacha.class);
                startActivity(intent);
            }
        });
    }

    public void showMenu(){
        menuOpened = true;
        gachaButtonLayout.animate().translationX(-225);
        skinButtonLayout.animate().translationX(-425);
    }

    public void closeMenu(){
        menuOpened = false;
        gachaButtonLayout.animate().translationX(0);
        skinButtonLayout.animate().translationX(0);
    }
}
