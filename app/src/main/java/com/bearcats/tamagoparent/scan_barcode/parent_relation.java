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
import android.widget.RadioGroup;
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
import com.bearcats.tamagoparent.views.FButton;

import org.json.JSONException;
import org.json.JSONObject;

public class parent_relation extends Fragment {

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_parent_relation, container, false);
    }

    JSONObject jsonObject;
    RadioGroup parent_relation;
    FButton finishButton;
    String parent_type;

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        parent_relation = view.findViewById(R.id.rg_parentRelation1);
        finishButton = view.findViewById(R.id.btn_finish1);


        //set redio button check change listenet
        parent_relation.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup radioGroup, int i) {
                switch (i){
                    case R.id.rb_dad:
                        parent_type = "Dad";
                        break;
                    case R.id.rb_mom:
                        parent_type = "Mom";
                        break;
                    case R.id.rb_grandpa:
                        parent_type = "Grandpa";
                        break;
                    case R.id.rb_grandma:
                        parent_type = "Grandma";
                        break;
                }
            }
        });

        Toast.makeText(getContext(), Temp.child_id+" "+UserPreferences.getUserId(getContext()), Toast.LENGTH_SHORT).show();
        finishButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                jsonObject = new JSONObject();

                try{
                    jsonObject.put("parent_id", UserPreferences.getUserId(getContext()));
                    jsonObject.put("child_id", Temp.child_id);
                    jsonObject.put("parent_relation",parent_type);
                }catch(
                        JSONException e){
                    e.printStackTrace();
                }

                JsonObjectRequest jsonObjectRequest = new JsonObjectRequest
                        (Request.Method.POST, "https://tamago.bancet.cf/api/child/newRelation", jsonObject, new Response.Listener<JSONObject>() {
                            @Override
                            public void onResponse(JSONObject response) {
                                try{
                                    if(response.getInt("status") == 200){
                                        //pindah
                                        Toast.makeText(getContext(), "Berhasil", Toast.LENGTH_SHORT).show();
                                    }

                                }catch(JSONException e){
                                    e.printStackTrace();
                                }

                            }
                        }, new Response.ErrorListener() {

                            @Override
                            public void onErrorResponse(VolleyError error) {
                                Toast.makeText(getContext(), error+"", Toast.LENGTH_SHORT).show();
                            }
                        });
                Volley.newRequestQueue(getContext()).add(jsonObjectRequest);
                getActivity().finish();
            }
        });
    }


}
