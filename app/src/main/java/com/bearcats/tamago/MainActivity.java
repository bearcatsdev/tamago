package com.bearcats.tamago;

import android.content.Intent;
import android.os.Handler;
import android.support.annotation.NonNull;
import android.support.design.bottomappbar.BottomAppBar;
import android.support.design.widget.BottomNavigationView;
import android.support.design.widget.FloatingActionButton;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTabHost;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.ImageButton;
import android.widget.Toast;

import com.airbnb.lottie.LottieAnimationView;

public class MainActivity extends AppCompatActivity {

//    ImageButton ayam;
    boolean zoomin = true;
    BottomAppBar bottomAppBar;
    BottomNavigationView navigationView;
    FloatingActionButton fab;
    Fragment home,task,reward,account;
    Fragment fragment;
    Preferences preferences;
    MenuItem prev_menuItem;
    Menu menu;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        bottomAppBar = findViewById(R.id.bar);
        navigationView = findViewById(R.id.navigation_menu);
        fab = findViewById(R.id.fab);
        home = new Home();
        task = new Task();
        reward = new Reward();
        account = new Account();
        preferences = new Preferences();
        menu = navigationView.getMenu();

        getSupportFragmentManager().beginTransaction().replace(R.id.fragment,home).commit();

        navigationView.setOnNavigationItemSelectedListener(new BottomNavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem menuItem) {
                refreshItemMenu();
                if(menuItem.getItemId() == R.id.home){
                    fragment = home;
                    getFragmentManager().beginTransaction().remove(preferences).commit();
                    menuItem.setIcon(R.drawable.ic_01_home_b);
                }
                else
                if(menuItem.getItemId() == R.id.task){
                    fragment = task;
                    getFragmentManager().beginTransaction().remove(preferences).commit();
                    menuItem.setIcon(R.drawable.ic_02_tasks_b);
                }
                else
                if(menuItem.getItemId() == R.id.reward){
                    fragment = reward;
                    getFragmentManager().beginTransaction().remove(preferences).commit();
                    menuItem.setIcon(R.drawable.ic_03_rewards_b);
                }
                else
                if(menuItem.getItemId() == R.id.account){
                    fragment = account;
                    getFragmentManager().beginTransaction().replace(R.id.fragment,preferences).commit();
                    menuItem.setIcon(R.drawable.ic_04_account_b);
                }
                getSupportFragmentManager().beginTransaction().replace(R.id.fragment,fragment).commit();
                prev_menuItem = menuItem;
                return true;
            }
        });

        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i = new Intent(MainActivity.this,Scan_Barcode.class);
                startActivity(i);
            }
        });

//        final Handler handler = new Handler();
//
//        final Runnable runnable = new Runnable() {
//            @Override
//            public void run() {
//                ayam.animate().scaleX(1f).scaleY(1f).setDuration(70);
//                ayam.setClickable(true);
//            }
//        };
//
//        ayam = findViewById(R.id.ayam);
//        ayam.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View view) {
//                ayam.setClickable(false);
//                ayam.animate().scaleX(1f).scaleY(0.8f).setDuration(100);
//                handler.postDelayed(runnable,100);
//            }
//        });
    }

    public void refreshItemMenu(){
        menu.findItem(R.id.home).setIcon(R.drawable.ic_01_home_w);
        menu.findItem(R.id.task).setIcon(R.drawable.ic_02_task_w);
        menu.findItem(R.id.reward).setIcon(R.drawable.ic_03_reward_w);
        menu.findItem(R.id.account).setIcon(R.drawable.ic_04_account_w);
    }
}
