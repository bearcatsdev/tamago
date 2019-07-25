package com.bearcats.tamagoparent.mainmenu;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;

import com.bearcats.tamagoparent.R;
import com.bearcats.tamagoparent.preferences.UserPreferences;

public class MainMenuActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        setTheme(R.style.AppTheme);
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main_menu);

        if (!UserPreferences.getUserLoggedIn(this)) {
            // user not logged in
            finish();
        }
    }
}
