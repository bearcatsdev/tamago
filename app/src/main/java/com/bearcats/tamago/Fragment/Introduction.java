package com.bearcats.tamago.Fragment;

import android.Manifest;
import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.net.Uri;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.core.content.ContextCompat;
import androidx.fragment.app.Fragment;

import android.os.Handler;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.Toast;

import com.bearcats.tamago.Activity.Login;
import com.bearcats.tamago.Activity.Login_ScanQRCode;
import com.bearcats.tamago.Manager.FragmentChangeListener;
import com.bearcats.tamago.R;
import com.google.android.material.card.MaterialCardView;

public class Introduction extends Fragment {

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_introduction, container, false);
    }


    MaterialCardView next;
    Toolbar toolbar;

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        next = view.findViewById(R.id.btn_next);

        toolbar = view.findViewById(R.id.toolbar);
        ((AppCompatActivity) getActivity()).setSupportActionBar(toolbar);
        ((AppCompatActivity) getActivity()).getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        ((AppCompatActivity) getActivity()).getSupportActionBar().setDisplayShowHomeEnabled(true);
        ((AppCompatActivity) getActivity()).getSupportActionBar().setDisplayShowTitleEnabled(false);

        toolbar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
              getActivity().onBackPressed();
            }
        });

        final Handler handler = new Handler();
        final Runnable runnable1 = new Runnable() {
            @Override
            public void run() {
                next.setClickable(true);
                Fragment loginFragment = new QRCode();
                FragmentChangeListener fc = (FragmentChangeListener) getActivity();
                fc.replaceFragment(loginFragment);
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

            }
        });

    }



}
