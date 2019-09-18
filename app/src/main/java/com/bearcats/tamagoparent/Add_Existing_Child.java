package com.bearcats.tamagoparent;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;

import android.os.Bundle;
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.Volley;
import com.bearcats.tamagoparent.preferences.UserPreferences;
import com.bearcats.tamagoparent.scan_barcode.scan_barcode;
import com.bearcats.tamagoparent.tempData.Temp;
import com.google.zxing.Result;

import org.json.JSONException;
import org.json.JSONObject;

import me.dm7.barcodescanner.zxing.ZXingScannerView;

public class Add_Existing_Child extends AppCompatActivity {

    Fragment fragment;
    String child_id;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add__existing__child);

        Temp.child_id = "";

        getSupportFragmentManager().beginTransaction().replace(R.id.fragment,new scan_barcode()).commit();
    }


}
