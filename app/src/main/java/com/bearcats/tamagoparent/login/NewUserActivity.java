package com.bearcats.tamagoparent.login;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;

import com.bearcats.tamagoparent.R;

public class NewUserActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        setTheme(R.style.AppTheme);
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_new_user);
    }
}
