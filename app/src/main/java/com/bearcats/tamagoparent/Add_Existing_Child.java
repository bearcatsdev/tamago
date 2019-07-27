package com.bearcats.tamagoparent;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.Volley;
import com.bearcats.tamagoparent.preferences.UserPreferences;
import com.google.zxing.Result;

import org.json.JSONException;
import org.json.JSONObject;

import me.dm7.barcodescanner.zxing.ZXingScannerView;

public class Add_Existing_Child extends AppCompatActivity implements ZXingScannerView.ResultHandler{

    ZXingScannerView zXingScannerView;
    JSONObject jsonObject;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add__existing__child);

        zXingScannerView = (ZXingScannerView) findViewById(R.id.zxingscanner);
        zXingScannerView.setResultHandler(Add_Existing_Child.this);
        zXingScannerView.startCamera();
    }

    @Override
    protected void onPause() {
        super.onPause();
        zXingScannerView.stopCamera();
    }


    @Override
    public void handleResult(Result rawResult) {
        String result = rawResult.getText();
        jsonObject = new JSONObject();

        try{
            jsonObject.put("parent_id", UserPreferences.getUserId(Add_Existing_Child.this));
            jsonObject.put("child_id",result);
            jsonObject.put("parent_relation","");
        }catch(JSONException e){
            e.printStackTrace();
        }

        JsonObjectRequest jsonObjectRequest = new JsonObjectRequest
                (Request.Method.POST, "https://tamago.bancet.cf/api/child/newRelation", jsonObject, new Response.Listener<JSONObject>() {
                    @Override
                    public void onResponse(JSONObject response) {
                        try{
                            if(response.getInt("status") == 200){
                                //pindah
                            }

                        }catch(JSONException e){
                            e.printStackTrace();
                        }

                    }
                }, new Response.ErrorListener() {

                    @Override
                    public void onErrorResponse(VolleyError error) {
                        Toast.makeText(Add_Existing_Child.this, error+"", Toast.LENGTH_SHORT).show();
                    }
                });
        Volley.newRequestQueue(Add_Existing_Child.this).add(jsonObjectRequest);
        finish();
    }
}
