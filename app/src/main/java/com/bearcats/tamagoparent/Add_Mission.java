package com.bearcats.tamagoparent;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import android.app.DatePickerDialog;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.Volley;
import com.bearcats.tamagoparent.mainmenu.MainMenuActivity;
import com.bearcats.tamagoparent.preferences.UserPreferences;
import com.bearcats.tamagoparent.views.FButton;

import org.json.JSONException;
import org.json.JSONObject;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Locale;

public class Add_Mission extends AppCompatActivity {

    EditText missionName, missionDetail, missionDeadline,amount;
    FButton finishButton;
    RadioGroup reward;
    int reward_type,child_id;
    Calendar myCalendar;
    JSONObject jsonObject;
    Toolbar toolbar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add__mission);

        missionName = findViewById(R.id.edit_misisonName);
        missionDetail = findViewById(R.id.edit_missionDetail);
        missionDeadline = findViewById(R.id.edit_missionDeadline);
        amount = findViewById(R.id.edit_amount);
        reward = findViewById(R.id.rg_reward);
        finishButton = findViewById(R.id.btn_finish);
        toolbar = findViewById(R.id.toolbar_create_mission);

        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setDisplayShowTitleEnabled(false);

        //get child_id
        child_id = getIntent().getExtras().getInt("child_id");

        //set date picker Listener
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

        missionDeadline.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                new DatePickerDialog(Add_Mission.this, date, myCalendar.get(Calendar.YEAR), myCalendar.get(Calendar.MONTH),
                        myCalendar.get(Calendar.DAY_OF_MONTH)).show();
            }
        });

        //set radio group change listener
        reward.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup radioGroup, int i) {
                switch (i){
                    case R.id.rb_egg:
                        reward_type = 1;
                        break;
                    case R.id.rb_wallet:
                        reward_type = 2;
                        break;
                }
            }
        });

        //button finish clicked
        finishButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                    jsonObject = new JSONObject();
                    try{
                        jsonObject.put("task_name",missionName.getText().toString());
                        jsonObject.put("task_detail",missionDeadline.getText().toString());
                        jsonObject.put("child_id",child_id);
                        jsonObject.put("parent_id",UserPreferences.getUserId(Add_Mission.this));
                        if(reward_type == 1){
                            jsonObject.put("task_reward_wallet",0);
                            jsonObject.put("task_reward_eggs",Integer.parseInt(amount.getText().toString()));
                        }
                        else
                        {
                            jsonObject.put("task_reward_wallet",Integer.parseInt(amount.getText().toString()));
                            jsonObject.put("task_reward_eggs",0);
                        }

                        jsonObject.put("task_expiry",missionDeadline.getText().toString());

                    }catch(JSONException e){
                        e.printStackTrace();
                    }

                    JsonObjectRequest jsonObjectRequest = new JsonObjectRequest
                            (Request.Method.POST, "https://tamago.bancet.cf/api/child/task/newTask", jsonObject, new Response.Listener<JSONObject>() {
                                @Override
                                public void onResponse(JSONObject response) {
                                    try{
                                        if(response.getInt("status") == 200){
                                            Intent intent = new Intent(Add_Mission.this, MainMenuActivity.class);
                                            intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP | Intent.FLAG_ACTIVITY_NEW_TASK);
                                            startActivity(intent);
                                            finish();
                                            Toast.makeText(Add_Mission.this, "Success to Add Mission", Toast.LENGTH_SHORT).show();
                                        }

                                    }catch(JSONException e){
                                        e.printStackTrace();
                                    }

                                }
                            }, new Response.ErrorListener() {

                                @Override
                                public void onErrorResponse(VolleyError error) {
                                    Toast.makeText(Add_Mission.this, "Something Went Wrong", Toast.LENGTH_SHORT).show();
                                }
                            });
                    Volley.newRequestQueue(Add_Mission.this).add(jsonObjectRequest);
                }
        });


    }

    private void updateLabel() {
        String myFormat = "YYYY-MM-dd"; //In which you need put here
        SimpleDateFormat sdf = new SimpleDateFormat(myFormat, Locale.US);

        missionDeadline.setText(sdf.format(myCalendar.getTime()));
    }

    @Override
    public boolean onSupportNavigateUp() {
        onBackPressed();
        return true;
    }
}
