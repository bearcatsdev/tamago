package com.bearcats.tamago.Activity;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.content.ContextCompat;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.Volley;
import com.bearcats.tamago.R;
import com.bearcats.tamago.Recycler.MissionsAdapter;
import com.bearcats.tamago.Recycler.Missions_Model;
import com.bearcats.tamago.RewardData;
import com.bearcats.tamago.preferences.ChildPreferences;
import com.google.android.material.card.MaterialCardView;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.text.DecimalFormat;
import java.text.DecimalFormatSymbols;

public class SetGoal extends AppCompatActivity {

    int product;
    String nama, url;
    int harga;
    TextView price,name;
    ImageView image;
    JSONObject jsonObject;
   MaterialCardView setGoal;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_set_goal);

        product = getIntent().getExtras().getInt("product");
        image = findViewById(R.id.image1);
        price = findViewById(R.id.item_price);
        name = findViewById(R.id.item_name);
        setGoal = findViewById(R.id.btn_setGoal);

        if(product == 0){
          image.setImageDrawable(ContextCompat.getDrawable(SetGoal.this,R.drawable.bounce_off));
        }
        else
        if(product == 1){
            image.setImageDrawable(ContextCompat.getDrawable(SetGoal.this,R.drawable.kerp_lunk));
        }
        else
        if(product  == 2){
            image.setImageDrawable(ContextCompat.getDrawable(SetGoal.this,R.drawable.scrabble));
        }
        else
        if(product == 3){
            image.setImageDrawable(ContextCompat.getDrawable(SetGoal.this,R.drawable.uno_card));
        }

        nama = RewardData.rewardData.get(product).getNama();
        harga = RewardData.rewardData.get(product).getHarga();
        url = RewardData.rewardData.get(product).getUrl();

        name.setText(nama);
        price.setText(FormatRp(harga));

        setGoal.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                jsonObject = new JSONObject();
                try{
                    jsonObject.put("child_id", ChildPreferences.getChildId(SetGoal.this));
                    jsonObject.put("goal_name", nama);
                    jsonObject.put("goal_url", url);
                    jsonObject.put("goal_price",harga);
                    jsonObject.put("goal_done", false);
                }catch(JSONException e){
                    e.printStackTrace();
                }
                //get Json child mission data
                JsonObjectRequest jsonObjectRequest = new JsonObjectRequest
                        (Request.Method.POST, "https://tamago.bancet.cf/api/child/goal/newGoal", jsonObject, new Response.Listener<JSONObject>() {
                            @Override
                            public void onResponse(JSONObject response) {
                                Toast.makeText(SetGoal.this, "Success to set a goal", Toast.LENGTH_SHORT).show();
                                finish();
                            }

                        }, new Response.ErrorListener() {

                            @Override
                            public void onErrorResponse(VolleyError error) {
                                Toast.makeText(SetGoal.this, "Failed to set a goal. Please try again", Toast.LENGTH_SHORT).show();
                            }
                        });
                Volley.newRequestQueue(SetGoal.this).add(jsonObjectRequest);
            }
        });
    }

    public String FormatRp(int amount){
        DecimalFormat format = (DecimalFormat)DecimalFormat.getCurrencyInstance();
        DecimalFormatSymbols formatRp = new DecimalFormatSymbols();

        formatRp.setCurrencySymbol("Rp ");
        formatRp.setGroupingSeparator('.');

        format.setDecimalFormatSymbols(formatRp);

        return format.format(amount).substring(0,format.format(amount).length()-3);
    }
}
