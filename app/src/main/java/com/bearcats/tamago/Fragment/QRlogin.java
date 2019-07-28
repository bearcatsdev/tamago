package com.bearcats.tamago.Fragment;

import android.Manifest;
import android.content.Intent;
import android.content.pm.PackageManager;
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
import android.widget.Toast;

import com.bearcats.tamago.Activity.QRscan;
import com.bearcats.tamago.R;
import com.google.android.material.card.MaterialCardView;

public class QRlogin extends Fragment {


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_qrlogin, container, false);
    }

    int MY_CAMERA_REQUEST_CODE = 100;
    MaterialCardView btn_qrcode;
    Toolbar toolbar;

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        btn_qrcode = view.findViewById(R.id.btn_qrcode);
        toolbar = view.findViewById(R.id.toolbar1);

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
                btn_qrcode.setClickable(true);
                if (ContextCompat.checkSelfPermission(getContext(), Manifest.permission.CAMERA)
                        != PackageManager.PERMISSION_GRANTED) {
                    requestPermissions(new String[]{Manifest.permission.CAMERA},MY_CAMERA_REQUEST_CODE);
                }
                else
                    startActivity(new Intent(getContext(), QRscan.class));
            }
        };

        final Runnable runnable = new Runnable() {
            @Override
            public void run() {
                btn_qrcode.animate().translationY(0).setDuration(100);
                handler.postDelayed(runnable1,100);
            }
        };

        btn_qrcode.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                btn_qrcode.setClickable(false);
                btn_qrcode.animate().translationY(20).setDuration(100);
                handler.postDelayed(runnable,100);

            }
        });
    }

    @Override

    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {

        super.onRequestPermissionsResult(requestCode, permissions, grantResults);

        if (requestCode == MY_CAMERA_REQUEST_CODE) {

            if (grantResults[0] == PackageManager.PERMISSION_GRANTED) {

                Toast.makeText(getContext(), "camera permission granted", Toast.LENGTH_LONG).show();
                startActivity(new Intent(getContext(), QRscan.class));

            } else {
                Toast.makeText(getContext(), "camera permission denied", Toast.LENGTH_LONG).show();
            }

        }}//end onRequestPermissionsResult

}
