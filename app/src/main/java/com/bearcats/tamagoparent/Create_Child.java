package com.bearcats.tamagoparent;

import androidx.appcompat.app.AppCompatActivity;

import android.app.DatePickerDialog;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.RadioGroup;
import android.widget.Spinner;
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.Volley;
import com.bearcats.tamagoparent.mainmenu.MainMenuActivity;
import com.bearcats.tamagoparent.preferences.UserPreferences;
import com.bearcats.tamagoparent.views.FButton;
import com.google.android.material.snackbar.Snackbar;

import org.json.JSONException;
import org.json.JSONObject;

public class Create_Child extends AppCompatActivity {

    RadioGroup childGender,parentRelation;
    EditText childName,initialSaving,dailyLimit;
    JSONObject jsonObject;
    FButton finish;

    DatePickerDialog.OnDateSetListener mDateSetListener;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_create__child);

        childGender =findViewById(R.id.rg_childGender);
        parentRelation = findViewById(R.id.rg_parentRelation);
        childName = findViewById(R.id.edit_name);
        initialSaving = findViewById(R.id.edit_initialSaving);
        dailyLimit = findViewById(R.id.edit_dailyLimit);
        finish = findViewById(R.id.btn_finish);

        //set Radio Group childGender to boy
        childGender.check(R.id.rb_boy);

        //set Radio Group parentRelation to dad
        parentRelation.check(R.id.rb_dad);

        finish.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(allInputFilled()){
                    jsonObject = new JSONObject();
                    try{
                        jsonObject.put("parent_id", UserPreferences.getUserId(Create_Child.this));
                        jsonObject.put("child_name",childName.getText().toString());
                        jsonObject.put("child_dob","2012-05-29");
                        jsonObject.put("child_initial_saving",Integer.parseInt(initialSaving.getText().toString()));
                        jsonObject.put("child_daily_limit",Integer.parseInt(dailyLimit.getText().toString()));
                        jsonObject.put("child_avatar_type",1);
                        jsonObject.put("child_gender",1);
                        jsonObject.put("parent_relation","Ayah");
                    }catch(JSONException e){
                        e.printStackTrace();
                    }

                    Toast.makeText(Create_Child.this, jsonObject+"", Toast.LENGTH_SHORT).show();
                    JsonObjectRequest jsonObjectRequest = new JsonObjectRequest
                            (Request.Method.POST, "https://tamago.bancet.cf/api/child/newChild", jsonObject, new Response.Listener<JSONObject>() {
                                @Override
                                public void onResponse(JSONObject response) {
                                    try{
                                        if(response.getInt("status") == 200){
                                            Toast.makeText(Create_Child.this, response.toString()+"", Toast.LENGTH_SHORT).show();
                                            //add  data to sqlite
                                            //pindah
                                        }

                                    }catch(JSONException e){
                                        e.printStackTrace();
                                    }

                                }
                            }, new Response.ErrorListener() {

                                @Override
                                public void onErrorResponse(VolleyError error) {
                                    Toast.makeText(Create_Child.this, error+"", Toast.LENGTH_SHORT).show();
                                }
                            });
                    Volley.newRequestQueue(Create_Child.this).add(jsonObjectRequest);
                }
            }
        });

    }

    public boolean allInputFilled(){
        if(childName.getText().toString().equals("") || initialSaving.getText().toString().equals("") || dailyLimit.getText().toString().equals("")){
            Toast.makeText(this, "No Completed", Toast.LENGTH_SHORT).show();
            return false;
        }
        return true;
    }



}
