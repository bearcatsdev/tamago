package com.bearcats.tamago;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.Volley;

import org.json.JSONObject;

import java.util.ArrayList;


public class Task extends Fragment {


    RecyclerView task_recyclerView,extra_recyclerView;
    ArrayList<Task_Model> task;
    ArrayList<Extra_Model> extra;
    JSONObject jsonObject;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_task, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        task_recyclerView = (RecyclerView) view.findViewById(R.id.listTask);
        extra_recyclerView = view.findViewById(R.id.listExtra);
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(getActivity(),LinearLayoutManager.VERTICAL,false);
        LinearLayoutManager linearLayoutManager1 = new LinearLayoutManager(getActivity(),LinearLayoutManager.VERTICAL,false);

        task_recyclerView.setLayoutManager(linearLayoutManager);
        extra_recyclerView.setLayoutManager(linearLayoutManager1);

        task_recyclerView.setHasFixedSize(true);
        extra_recyclerView.setHasFixedSize(true);

        task = new ArrayList<>();
        extra = new ArrayList<>();

//        try{
//            jsonObject.put("parent_id",1);
//            jsonObject.put("child_name",childName.getText().toString());
//            jsonObject.put("child_dob",childDob.getText().toString());
//            jsonObject.put("child_initial_saving",childInitialSaving.getText().toString());
//            jsonObject.put("child_daily_limit",childDailyLimit.getText().toString());
//            jsonObject.put("child_avatar_type",1);
//            jsonObject.put("child_gender",1);
//            jsonObject.put("parent_relation",parentRelation.getText().toString());
//        }catch(JSONException e){
//            e.printStackTrace();
//        }
//

//        JsonObjectRequest jsonObjectRequest = new JsonObjectRequest
//                (Request.Method.GET, "https://raw.githubusercontent.com/BinusBearcats/TamagoFiles/master/test.json", null, new Response.Listener<JSONObject>() {
//                    @Override
//                    public void onResponse(JSONObject response) {
//                        Toast.makeText(getContext(), response.toString()+"", Toast.LENGTH_SHORT).show();
//                    }
//                }, new Response.ErrorListener() {
//
//                    @Override
//                    public void onErrorResponse(VolleyError error) {
//
//                        Toast.makeText(getContext(), error.toString()+"", Toast.LENGTH_SHORT).show();
//
//                    }
//                });
//        Volley.newRequestQueue(getContext()).add(jsonObjectRequest);


        //data untuk task
        task.add( new Task_Model(1,2,0,100000,"Vacuum the House","2 minute(s) ago"));
        task.add(new Task_Model(2,3,10,0,"Wash Car","10 minute(s) ago"));
        task.add(new Task_Model(3,5,0,5000,"Do Laundry","7 minute(s) ago"));
        task.add( new Task_Model(1,2,0,100000,"Vacuum the House","2 minute(s) ago"));
        task.add(new Task_Model(2,3,10,0,"Wash Car","10 minute(s) ago"));
        task.add(new Task_Model(3,5,0,5000,"Do Laundry","7 minute(s) ago"));

        //data untuk extra
        extra.add( new Extra_Model(5,2,1,0,"Watch Video","Advertisement"));

        //set task recycler view
        TaskAdapter taskAdapter = new TaskAdapter(getContext(),task);
        task_recyclerView.setAdapter(taskAdapter);

        //set task recycler view
        ExtraAdapter extraAdapter = new ExtraAdapter(getContext(),extra);
        extra_recyclerView.setAdapter(extraAdapter);
    }
}
