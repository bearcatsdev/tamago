package com.bearcats.tamago.Activity;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.Volley;
import com.bearcats.tamago.R;
import com.bearcats.tamago.preferences.ChildPreferences;
import com.google.zxing.Result;

import org.json.JSONException;
import org.json.JSONObject;

import javax.security.auth.login.LoginException;

import me.dm7.barcodescanner.zxing.ZXingScannerView;

public class Login_ScanQRCode extends AppCompatActivity implements ZXingScannerView.ResultHandler {

    ZXingScannerView zXingScannerView;
    JSONObject jsonObject;

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
        try {
             jsonObject = new JSONObject(rawResult.getText());
        } catch (JSONException e) {
            e.printStackTrace();
        }

        boolean success;

        JsonObjectRequest jsonObjectRequest = new JsonObjectRequest
                (Request.Method.POST, "https://tamago.bancet.cf/api/child/login", jsonObject, new Response.Listener<JSONObject>() {
                    @Override
                    public void onResponse(JSONObject response) {
                        try {
                            if (response.getInt("status") == 200) {
                                //add  data to sqlite
                                //pindah
                                JSONObject r = response.getJSONObject("response");
                                ChildPreferences.setChildId(Login_ScanQRCode.this,r.getInt("child_id"));
                                ChildPreferences.setChildname(Login_ScanQRCode.this,r.getString("child_name"));
                                ChildPreferences.setChildGender(Login_ScanQRCode.this,r.getInt("child_gender"));
                                ChildPreferences.setChildDob(Login_ScanQRCode.this,r.getString("child_dob"));
                                ChildPreferences.setChildSaving(Login_ScanQRCode.this, r.getInt("child_savings"));

                                ChildPreferences.setChildEgg(Login_ScanQRCode.this, r.getInt("child_eggs"));

                                ChildPreferences.setChildWallet(Login_ScanQRCode.this, r.getInt("child_wallet"));

                                ChildPreferences.setChildDailyLimit(Login_ScanQRCode.this, r.getInt("child_daily_limit"));

                                ChildPreferences.setChildLoggedIn(Login_ScanQRCode.this,true);

                                Toast.makeText(Login_ScanQRCode.this, "Success Logged", Toast.LENGTH_SHORT).show();

                            }

                        } catch (JSONException e) {
                            e.printStackTrace();
                        }
                        Intent intent = new Intent(Login_ScanQRCode.this, MainActivity.class);
                        intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP | Intent.FLAG_ACTIVITY_NEW_TASK);
                        startActivity(intent);
                        finish();
                    }

                }, new Response.ErrorListener() {

                    @Override
                    public void onErrorResponse(VolleyError error) {
                        Toast.makeText(Login_ScanQRCode.this, "Something went error", Toast.LENGTH_SHORT).show();
                        finish();
                    }
                });
        Volley.newRequestQueue(Login_ScanQRCode.this).add(jsonObjectRequest);

    }
}
