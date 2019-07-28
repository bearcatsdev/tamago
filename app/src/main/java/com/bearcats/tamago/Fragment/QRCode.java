package com.bearcats.tamago.Fragment;

import android.Manifest;
import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.net.Uri;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.core.content.ContextCompat;
import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.Toast;

import com.bearcats.tamago.Activity.Login_ScanQRCode;
import com.bearcats.tamago.R;
import com.google.android.material.card.MaterialCardView;

public class QRCode extends Fragment {


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_qrcode, container, false);
    }

    int MY_CAMERA_REQUEST_CODE = 100;
    MaterialCardView btn_qrcode;

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        btn_qrcode = view.findViewById(R.id.btn_qrcode);

        btn_qrcode.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (ContextCompat.checkSelfPermission(getContext(), Manifest.permission.CAMERA)
                        != PackageManager.PERMISSION_GRANTED) {
                    requestPermissions(new String[]{Manifest.permission.CAMERA},MY_CAMERA_REQUEST_CODE);
                }
                else
                    startActivity(new Intent(getContext(), Login_ScanQRCode.class));
            }
        });
    }

    @Override

    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {

        super.onRequestPermissionsResult(requestCode, permissions, grantResults);

        if (requestCode == MY_CAMERA_REQUEST_CODE) {

            if (grantResults[0] == PackageManager.PERMISSION_GRANTED) {

                Toast.makeText(getContext(), "camera permission granted", Toast.LENGTH_LONG).show();
                startActivity(new Intent(getContext(),Login_ScanQRCode.class));

            } else {
                Toast.makeText(getContext(), "camera permission denied", Toast.LENGTH_LONG).show();
            }

        }}//end onRequestPermissionsResult

}
