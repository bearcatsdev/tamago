package com.bearcats.tamago.Fragment;

import android.content.Intent;
import android.os.Bundle;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.Volley;
import com.bearcats.tamago.Activity.Login_ScanQRCode;
import com.bearcats.tamago.Activity.MainActivity;
import com.bearcats.tamago.R;
import com.bearcats.tamago.Recycler.ExtraAdapter;
import com.bearcats.tamago.Recycler.Extra_Model;
import com.bearcats.tamago.Recycler.TaskAdapter;
import com.bearcats.tamago.Recycler.Task_Model;
import com.bearcats.tamago.preferences.ChildPreferences;
import com.facebook.shimmer.ShimmerFrameLayout;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;


public class Task extends Fragment {


    RecyclerView task_recyclerView;
    ArrayList<Task_Model> task;
    JSONObject jsonObject;
    ShimmerFrameLayout loadingShimmer;

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

        task_recyclerView = view.findViewById(R.id.listTask);
        loadingShimmer = view.findViewById(R.id.shimmer_view_container);

        loadingShimmer.startShimmerAnimation();

        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(getActivity(),RecyclerView.VERTICAL,false);

        task_recyclerView.setLayoutManager(linearLayoutManager);

        task_recyclerView.setHasFixedSize(true);
        task = new ArrayList<>();

        jsonObject = new JSONObject();
        try{
            jsonObject.put("child_id", ChildPreferences.getChildId(getContext()));
        }catch(JSONException e){
            e.printStackTrace();
        }

        //get Json child mission data
        JsonObjectRequest jsonObjectRequest = new JsonObjectRequest
                (Request.Method.POST, "https://tamago.bancet.cf/api/child/task/getTaskList", jsonObject, new Response.Listener<JSONObject>() {
                    @Override
                    public void onResponse(JSONObject response) {
                        try {
                            if (response.getInt("status") == 200) {
                                JSONArray jsonArray = response.getJSONArray("response");
                                for (int i= 0 ;i <jsonArray.length();i++){
                                    task.add( new Task_Model(1,2,jsonArray.getJSONObject(i).getInt("task_reward_eggs"),jsonArray.getJSONObject(i).getInt("task_reward_wallet"),jsonArray.getJSONObject(i).getString("task_name"),jsonArray.getJSONObject(i).getString("task_expiry").substring(0,10)));
                                }
                                TaskAdapter taskAdapter = new TaskAdapter(getContext(),task);
                                task_recyclerView.setAdapter(taskAdapter);
                                task_recyclerView.setVisibility(View.VISIBLE);
                                loadingShimmer.stopShimmerAnimation();
                                loadingShimmer.setVisibility(View.GONE);
                            }

                        } catch (JSONException e) {
                            e.printStackTrace();
                        }
                    }

                }, new Response.ErrorListener() {

                    @Override
                    public void onErrorResponse(VolleyError error) {

                    }
                });
        Volley.newRequestQueue(getContext()).add(jsonObjectRequest);



        //data untuk extra
//        extra.add( new Extra_Model(5,2,1,0,"Watch Video","Advertisement"));

        //set task recycler view


        //set task recycler view
//        ExtraAdapter extraAdapter = new ExtraAdapter(getContext(),extra);
//        extra_recyclerView.setAdapter(extraAdapter);
    }
}
