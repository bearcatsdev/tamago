package com.bearcats.tamago.Fragment;

import android.content.Context;
import android.net.Uri;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;

import android.os.Handler;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.bearcats.tamago.Activity.Login;
import com.bearcats.tamago.BottomSheetDialog;
import com.bearcats.tamago.Manager.FragmentChangeListener;
import com.bearcats.tamago.R;
import com.google.android.material.card.MaterialCardView;

public class Welcome extends Fragment {

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_welcome, container, false);
    }

    MaterialCardView next;

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        next = view.findViewById(R.id.btn_letsgo);

        final Handler handler = new Handler();

        final Runnable runnable1 = new Runnable() {
            @Override
            public void run() {
                next.setClickable(true);
            }
        };

        final Runnable runnable = new Runnable() {
            @Override
            public void run() {
                next.animate().translationY(0).setDuration(100);
                handler.postDelayed(runnable1,100);
            }
        };

        next.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                next.setClickable(false);
                next.animate().translationY(20).setDuration(100);
                handler.postDelayed(runnable,100);
                Fragment loginFragment = new Introduction();
                FragmentChangeListener fc = (FragmentChangeListener) getActivity();
                fc.replaceFragment(loginFragment);
            }
        });

    }
}
