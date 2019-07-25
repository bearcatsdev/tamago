package com.bearcats.tamago;

import android.content.Context;
import android.net.Uri;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import java.util.ArrayList;


public class Task extends Fragment {


    RecyclerView recyclerView;
    ArrayList<Task_Model> task;

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

        recyclerView = (RecyclerView) view.findViewById(R.id.listTask);
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(getActivity(),LinearLayoutManager.VERTICAL,false);

        recyclerView.setLayoutManager(linearLayoutManager);
        recyclerView.setHasFixedSize(true);

        task = new ArrayList<>();

        task.add( new Task_Model(1,2,0,100000,"Vacuum the House","2 minute(s) ago"));
        task.add(new Task_Model(2,3,10,0,"Wash Car","10 minute(s) ago"));
        task.add(new Task_Model(3,5,0,5000,"Do Laundry","7 minute(s) ago"));
        task.add( new Task_Model(1,2,0,100000,"Vacuum the House","2 minute(s) ago"));
        task.add(new Task_Model(2,3,10,0,"Wash Car","10 minute(s) ago"));
        task.add(new Task_Model(3,5,0,5000,"Do Laundry","7 minute(s) ago"));

        Adapter adapter = new Adapter(getContext(),task);
        recyclerView.setAdapter(adapter);
    }
}
