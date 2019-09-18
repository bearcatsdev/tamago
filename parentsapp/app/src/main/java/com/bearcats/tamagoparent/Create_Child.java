package com.bearcats.tamagoparent;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import android.app.DatePickerDialog;
import android.content.Intent;
import android.os.Bundle;
import android.view.MotionEvent;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.DatePicker;
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

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Locale;

public class Create_Child extends AppCompatActivity {

    RadioGroup childGender,parentRelation;
    EditText childName,initialSaving,dailyLimit,dob;
    JSONObject jsonObject;
    FButton finish;
    Calendar myCalendar;
    int child_gender;
    String parent;
    Toolbar toolbar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_create__child);

        childGender =findViewById(R.id.rg_childGender);
        parentRelation = findViewById(R.id.rg_parentRelation);
        childName = findViewById(R.id.edit_name);
        initialSaving = findViewById(R.id.edit_initialSaving);
        dailyLimit = findViewById(R.id.edit_dailyLimit);
        dob = findViewById(R.id.edit_dob);
        finish = findViewById(R.id.btn_finish);
        toolbar = findViewById(R.id.toolbar_create_new_account);

        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setDisplayShowTitleEnabled(false);

        //set Radio Group childGender to boy
        childGender.check(R.id.rb_boy);

        //set Radio Group parentRelation to dad
        parentRelation.check(R.id.rb_dad);

        //set date picker listener and respont when dob clicked

        myCalendar = Calendar.getInstance();

        DatePickerDialog.OnDateSetListener date = new DatePickerDialog.OnDateSetListener() {

            @Override
            public void onDateSet(DatePicker view, int year, int monthOfYear,
                                  int dayOfMonth) {
                // TODO Auto-generated method stub
                myCalendar.set(Calendar.YEAR, year);
                myCalendar.set(Calendar.MONTH, monthOfYear);
                myCalendar.set(Calendar.DAY_OF_MONTH, dayOfMonth);
                updateLabel();
            }

        };
        dob.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                new DatePickerDialog(Create_Child.this, date, myCalendar.get(Calendar.YEAR), myCalendar.get(Calendar.MONTH),
                        myCalendar.get(Calendar.DAY_OF_MONTH)).show();
            }
        });

        //set radio group change listener
        childGender.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup radioGroup, int i) {
                switch (i){
                    case R.id.rb_boy:
                        child_gender = 1;
                        break;
                    case R.id.rb_girl:
                        child_gender = 2;
                        break;
                }
            }
        });

        parentRelation.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup radioGroup, int i) {
                switch (i){
                    case R.id.rb_dad:
                        parent = "Dad";
                        break;
                    case R.id.rb_mom:
                        parent = "Mom";
                        break;
                    case R.id.rb_grandpa:
                        parent = "Grandpa";
                        break;
                    case R.id.rb_grandma:
                        parent = "Grandma";
                        break;
                    }
                }
            });


        //Post
        finish.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(allInputFilled()){
                    jsonObject = new JSONObject();
                    try{
                        jsonObject.put("parent_id", UserPreferences.getUserId(Create_Child.this));
                        jsonObject.put("child_name",childName.getText().toString());
                        jsonObject.put("child_dob",dob.getText().toString());
                        jsonObject.put("child_initial_saving",Integer.parseInt(initialSaving.getText().toString()));
                        jsonObject.put("child_daily_limit",Integer.parseInt(dailyLimit.getText().toString()));
                        jsonObject.put("child_avatar_type",child_gender);
                        jsonObject.put("child_gender",child_gender);
                        jsonObject.put("parent_relation",parent);
                    }catch(JSONException e){
                        e.printStackTrace();
                    }

                    JsonObjectRequest jsonObjectRequest = new JsonObjectRequest
                            (Request.Method.POST, "https://tamago.bancet.cf/api/child/newChild", jsonObject, new Response.Listener<JSONObject>() {
                                @Override
                                public void onResponse(JSONObject response) {
                                    try{
                                        if(response.getInt("status") == 200){
                                            Intent intent = new Intent(Create_Child.this, MainMenuActivity.class);
                                            intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP | Intent.FLAG_ACTIVITY_NEW_TASK);
                                            Toast.makeText(Create_Child.this, "Success to Create Child", Toast.LENGTH_SHORT).show();
                                            startActivity(intent);
                                            finish();
                                        }

                                    }catch(JSONException e){
                                        e.printStackTrace();
                                    }
                                }
                            }, new Response.ErrorListener() {

                                @Override
                                public void onErrorResponse(VolleyError error) {
                                    Toast.makeText(Create_Child.this, "Something Went Wrong", Toast.LENGTH_SHORT).show();
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


    private void updateLabel() {
        String myFormat = "YYYY-MM-dd"; //In which you need put here
        SimpleDateFormat sdf = new SimpleDateFormat(myFormat, Locale.US);

        dob.setText(sdf.format(myCalendar.getTime()));
    }

    @Override
    public boolean onSupportNavigateUp() {
        onBackPressed();
        return true;
    }
}
