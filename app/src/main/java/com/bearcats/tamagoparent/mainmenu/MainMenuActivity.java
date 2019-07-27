package com.bearcats.tamagoparent.mainmenu;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;

import com.bearcats.tamagoparent.R;
import com.bearcats.tamagoparent.recyclerview.ChildrenAdapter;
import com.bearcats.tamagoparent.recyclerview.ChildrenModel;
import com.bearcats.tamagoparent.preferences.UserPreferences;

import java.util.ArrayList;

public class MainMenuActivity extends AppCompatActivity {

    RecyclerView recyclerView_child;
    ArrayList<ChildrenModel> children_models;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main_menu);

        recyclerView_child = findViewById(R.id.recyclerView_child);
        children_models = new ArrayList<>();

        //set recycler view layout
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(MainMenuActivity.this,RecyclerView.VERTICAL,false);
        recyclerView_child.setLayoutManager(linearLayoutManager);

        //input data children
        children_models.add(new ChildrenModel("Chandra",5000,10000,10000,10000,2,1));
        children_models.add(new ChildrenModel("Chandra",5000,10000,10000,10000,2,1));
        children_models.add(new ChildrenModel("Chandra",5000,10000,10000,10000,2,1));
        children_models.add(new ChildrenModel("Chandra",5000,10000,10000,10000,2,1));
        children_models.add(new ChildrenModel("Chandra",5000,10000,10000,10000,2,1));

        ChildrenAdapter adapter = new ChildrenAdapter(MainMenuActivity.this, children_models);

        recyclerView_child.setAdapter(adapter);


//        if (!UserPreferences.getUserLoggedIn(this)) {
//            // user not logged in
//            finish();
//        }

        // Untuk ambil data user dari preferences, pakai UserPreferences
        String userName = UserPreferences.getUsername(this);
        int UserId = UserPreferences.getUserId(this);

        // Untuk logout pakai ini
        // UserPreferences.clearLoggedInUser(this);

        // Untuk ngepost data ke server pakai NetworkManager
    }
}
