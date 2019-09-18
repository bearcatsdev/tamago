package com.bearcats.tamago;

import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;

import com.google.zxing.Result;

import me.dm7.barcodescanner.zxing.ZXingScannerView;

public class Scan_Barcode extends AppCompatActivity implements ZXingScannerView.ResultHandler {

    ZXingScannerView zXingScannerView;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_scan__barcode);
        zXingScannerView = new ZXingScannerView(Scan_Barcode.this);
        setContentView(zXingScannerView);
        zXingScannerView.setResultHandler(Scan_Barcode.this);
        zXingScannerView.startCamera();
    }

    @Override
    protected void onPause() {
        super.onPause();
    }


    @Override
    public void handleResult(Result rawResult) {

    }
}
