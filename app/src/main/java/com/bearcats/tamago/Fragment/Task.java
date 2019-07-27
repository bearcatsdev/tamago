package com.bearcats.tamago.Fragment;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.bearcats.tamago.R;
import com.bearcats.tamago.Recycler.ExtraAdapter;
import com.bearcats.tamago.Recycler.Extra_Model;
import com.bearcats.tamago.Recycler.TaskAdapter;
import com.bearcats.tamago.Recycler.Task_Model;

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

        task_recyclerView = view.findViewById(R.id.listTask);
        extra_recyclerView = view.findViewById(R.id.listExtra);
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(getActivity(),LinearLayoutManager.VERTICAL,false);
        LinearLayoutManager linearLayoutManager1 = new LinearLayoutManager(getActivity(),LinearLayoutManager.VERTICAL,false);

        task_recyclerView.setLayoutManager(linearLayoutManager);
        extra_recyclerView.setLayoutManager(linearLayoutManager1);

        task_recyclerView.setHasFixedSize(true);
        extra_recyclerView.setHasFixedSize(true);

        task = new ArrayList<>();
        extra = new ArrayList<>();



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
