package com.bearcats.tamago;

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
    CardView menuButton;

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        roundedHorizontalProgressBar = view.findViewById(R.id.progress_bar);
        menuButton = view.findViewById(R.id.btn_menu);

        roundedHorizontalProgressBar.animateProgress(2000,0,50);

        final Handler handler = new Handler();

        final Runnable runnable1 = new Runnable() {
            @Override
            public void run() {
                menuButton.setClickable(true);
                Intent intent = new Intent(getContext(),Gatcha.class);
                startActivity(intent);
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

    }
}
