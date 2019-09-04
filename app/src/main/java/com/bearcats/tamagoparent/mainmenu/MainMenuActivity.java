package com.bearcats.tamagoparent.mainmenu;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.content.ContextCompat;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.Manifest;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.os.Bundle;
import android.view.View;
import android.widget.Toast;

import com.bearcats.tamagoparent.Add_Existing_Child;
import com.bearcats.tamagoparent.Create_Child;
import com.bearcats.tamagoparent.R;
import com.bearcats.tamagoparent.manager.InterfaceManager;
import com.bearcats.tamagoparent.manager.NetworkManager;
import com.bearcats.tamagoparent.recyclerview.ChildrenAdapter;
import com.bearcats.tamagoparent.recyclerview.ChildrenModel;
import com.bearcats.tamagoparent.preferences.UserPreferences;
import com.bearcats.tamagoparent.views.FButton;
import com.facebook.shimmer.Shimmer;
import com.facebook.shimmer.ShimmerFrameLayout;
import com.github.clans.fab.FloatingActionButton;
import com.github.clans.fab.FloatingActionMenu;

import java.util.ArrayList;

public class MainMenuActivity extends AppCompatActivity {

    RecyclerView recyclerView_child;
    ArrayList<ChildrenModel> children_models;
    ShimmerFrameLayout loadingShimmer;
    int MY_CAMERA_REQUEST_CODE = 100;
    FloatingActionMenu fabMenu;
    FloatingActionButton fabCreateChildAccount,fabLinkExistingChildAccount;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        setTheme(R.style.AppTheme_TransparentStatusBar);
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main_menu);

        InterfaceManager.setLightStatusBar(this);

        // Untuk ambil data user dari preferences, pakai UserPreferences
        String userName = UserPreferences.getUsername(this);
        int UserId = UserPreferences.getUserId(this);

        recyclerView_child = findViewById(R.id.recyclerView_child);
        loadingShimmer = findViewById(R.id.shimmer_view_container);
        fabMenu = findViewById(R.id.fab_menu);
        fabCreateChildAccount = findViewById(R.id.fab_create_child_account);
        fabLinkExistingChildAccount = findViewById(R.id.fab_link_existing_child);


        loadingShimmer.startShimmer();

        children_models = new ArrayList<>();


        //set recycler view layout
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(MainMenuActivity.this,RecyclerView.VERTICAL,false);
        recyclerView_child.setLayoutManager(linearLayoutManager);

        //input data children
        NetworkManager networkManager = new NetworkManager(this);
        networkManager.getChildrenList(UserId, (success, response) -> {
            if (success) {

                for (int i=0; i<response.length(); ++i) {
                    children_models.add(new ChildrenModel(
                            response.getJSONObject(i).getInt("child_id"),
                            response.getJSONObject(i).getString("child_name"),
                            5000,
                            10000,
                            response.getJSONObject(i).getInt("child_wallet"),
                            response.getJSONObject(i).getInt("child_savings"),
                            response.getJSONObject(i).getInt("child_eggs"),
                            response.getJSONObject(i).getInt("child_avatar"))
                    );
                }

                ChildrenAdapter adapter = new ChildrenAdapter(MainMenuActivity.this, children_models);
                recyclerView_child.setAdapter(adapter);
                recyclerView_child.setVisibility(View.VISIBLE);
                loadingShimmer.stopShimmer();
                loadingShimmer.setVisibility(View.GONE);
            } else {

            }
        });

        fabCreateChildAccount.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(MainMenuActivity.this, Create_Child.class));
            }
        });

        fabLinkExistingChildAccount.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (ContextCompat.checkSelfPermission(MainMenuActivity.this, Manifest.permission.CAMERA)
                        != PackageManager.PERMISSION_GRANTED) {
                    requestPermissions(new String[]{Manifest.permission.CAMERA},MY_CAMERA_REQUEST_CODE);
                }
                else
                    startActivity(new Intent(MainMenuActivity.this, Add_Existing_Child.class));
            }
        });


//        if (!UserPreferences.getUserLoggedIn(this)) {
//            // user not logged in
//            finish();
//        }


        // Untuk logout pakai ini
        // UserPreferences.clearLoggedInUser(this);

        // Untuk ngepost data ke server pakai NetworkManager
    }

    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {

        super.onRequestPermissionsResult(requestCode, permissions, grantResults);

        if (requestCode == MY_CAMERA_REQUEST_CODE) {

            if (grantResults[0] == PackageManager.PERMISSION_GRANTED) {

                Toast.makeText(this, "camera permission granted", Toast.LENGTH_LONG).show();
                startActivity(new Intent(MainMenuActivity.this,Add_Existing_Child.class));

            } else {

                Toast.makeText(this, "camera permission denied", Toast.LENGTH_LONG).show();

            }

        }}//end onRequestPermissionsResult
}
