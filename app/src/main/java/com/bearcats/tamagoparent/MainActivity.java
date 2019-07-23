package com.bearcats.tamagoparent;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;

import com.bearcats.tamagoparent.login.LoginActivity;
import com.bearcats.tamagoparent.preferences.UserPreferences;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        setTheme(R.style.AppTheme);
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        if (!UserPreferences.getUserLoggedIn(this)) {
            startActivity(new Intent(this, LoginActivity.class));
            finish();
        } else {

        }
    }
}
