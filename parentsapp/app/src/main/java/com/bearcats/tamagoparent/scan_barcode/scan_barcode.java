package com.bearcats.tamagoparent.scan_barcode;

import android.content.Context;
import android.net.Uri;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.Volley;
import com.bearcats.tamagoparent.Add_Existing_Child;
import com.bearcats.tamagoparent.R;
import com.bearcats.tamagoparent.preferences.UserPreferences;
import com.bearcats.tamagoparent.tempData.Temp;
import com.google.zxing.Result;

import org.json.JSONException;
import org.json.JSONObject;

import me.dm7.barcodescanner.zxing.ZXingScannerView;

public class scan_barcode extends Fragment implements ZXingScannerView.ResultHandler{


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        return inflater.inflate(R.layout.fragment_scan_barcode, container, false);
    }

    ZXingScannerView zXingScannerView;
    JSONObject jsonObject;

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        zXingScannerView = (ZXingScannerView) view.findViewById(R.id.zxingscanner);
        zXingScannerView.setResultHandler(scan_barcode.this);
        zXingScannerView.startCamera();

    }

    @Override
    public void onPause() {
        super.onPause();
        zXingScannerView.stopCamera();
    }


    @Override
    public void handleResult(Result rawResult) {
        String result = rawResult.getText();
        Temp.child_id = result;
        getActivity().getSupportFragmentManager().beginTransaction().replace(R.id.fragment,new parent_relation()).commit();
    }
}
