package com.bearcats.tamago.Activity;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.Toast;

import com.bearcats.tamago.R;
import com.bearcats.tamago.Scan_Barcode;
import com.google.zxing.Result;

import me.dm7.barcodescanner.zxing.ZXingScannerView;

public class Login_ScanQRCode extends AppCompatActivity implements ZXingScannerView.ResultHandler {

    ZXingScannerView zXingScannerView;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login__scan_qrcode);

        zXingScannerView = (ZXingScannerView) findViewById(R.id.zxingscanner);
        zXingScannerView.setResultHandler(this);
        zXingScannerView.startCamera();
    }

    @Override
    protected void onPause() {
        super.onPause();
        zXingScannerView.stopCamera();
    }


    @Override
    public void handleResult(Result rawResult) {
        String result = rawResult.getText().toString();
        Toast.makeText(this, result, Toast.LENGTH_SHORT).show();
        finish();
    }
}
