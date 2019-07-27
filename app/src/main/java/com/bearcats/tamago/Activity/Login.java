package com.bearcats.tamago.Activity;

import android.Manifest;
import android.content.Intent;
import android.content.pm.PackageManager;

import androidx.annotation.NonNull;
import androidx.core.content.ContextCompat;
import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.bearcats.tamago.R;

import me.dm7.barcodescanner.zxing.ZXingScannerView;

public class Login extends AppCompatActivity {

    int MY_CAMERA_REQUEST_CODE = 100;
    Button btn_qrcode;
    TextView tv_qrcode;
    ZXingScannerView zXingScannerView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        btn_qrcode = findViewById(R.id.btn_qrcode);

        btn_qrcode.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (ContextCompat.checkSelfPermission(Login.this, Manifest.permission.CAMERA)
                        != PackageManager.PERMISSION_GRANTED) {
                        requestPermissions(new String[]{Manifest.permission.CAMERA},MY_CAMERA_REQUEST_CODE);
                }
                else
                startActivity(new Intent(Login.this,Login_ScanQRCode.class));
            }
        });



    }

    @Override

    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {

        super.onRequestPermissionsResult(requestCode, permissions, grantResults);

        if (requestCode == MY_CAMERA_REQUEST_CODE) {

            if (grantResults[0] == PackageManager.PERMISSION_GRANTED) {

                Toast.makeText(this, "camera permission granted", Toast.LENGTH_LONG).show();
                startActivity(new Intent(Login.this,Login_ScanQRCode.class));

            } else {

                Toast.makeText(this, "camera permission denied", Toast.LENGTH_LONG).show();

            }

        }}//end onRequestPermissionsResult


}
