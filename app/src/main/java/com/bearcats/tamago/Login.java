package com.bearcats.tamago;

import android.content.Intent;
import android.os.strictmode.WebViewMethodCalledOnWrongThreadViolation;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.google.zxing.Result;

import me.dm7.barcodescanner.zxing.ZXingScannerView;

public class Login extends AppCompatActivity implements ZXingScannerView.ResultHandler {

    Button btn_qrcode;
    TextView tv_qrcode;
    ZXingScannerView zXingScannerView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        btn_qrcode = findViewById(R.id.btn_qrcode);
        tv_qrcode = findViewById(R.id.tv_qrcode);

        btn_qrcode.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                zXingScannerView = new ZXingScannerView(Login.this);
                setContentView(zXingScannerView);
                zXingScannerView.setResultHandler(Login.this);
                zXingScannerView.startCamera();
            }
        });

    }

    @Override
    protected void onPause() {
        super.onPause();
        zXingScannerView.stopCamera();
    }

    @Override
    public void handleResult(Result rawResult) {
        String text, format;
        text = rawResult.getText().toString();
        format = rawResult.getBarcodeFormat().toString();

        if(text.equals("12345678")){
            Intent i = new Intent(Login.this,MainActivity.class);
            startActivity(i);
            finish();
        }
        else{
            Toast.makeText(this,text+" Have no any data similar", Toast.LENGTH_SHORT).show();
            Intent i = new Intent(Login.this,Login.class);
            startActivity(i);
            finish();
        }


    }
}
