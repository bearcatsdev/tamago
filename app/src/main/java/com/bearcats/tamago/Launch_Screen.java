package com.bearcats.tamago;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import com.bearcats.tamago.Activity.Login;
import com.bearcats.tamago.Activity.MainActivity;
import com.bearcats.tamago.preferences.ChildPreferences;

public class Launch_Screen extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);


        Intent intent;
        if(ChildPreferences.getChildLoggedIn(this)){
            intent = new Intent(this, Login.class);
        }
        else
        {
            intent = new Intent(this,MainActivity.class);
        }

        startActivity(intent);
        finish();
    }
}
