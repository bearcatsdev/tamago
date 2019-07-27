package com.bearcats.tamagoparent.login;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import com.bearcats.tamagoparent.R;
import com.bearcats.tamagoparent.manager.FontManager;
import com.bearcats.tamagoparent.manager.NetworkManager;
import com.bearcats.tamagoparent.views.FButton;

public class NewUserActivity extends AppCompatActivity {

    TextView welcomeText;
    TextView tellNameText;
    EditText nameEdit;
    FButton nextBtn;
    ProgressBar loadingCircle;
    Toolbar toolbar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_new_user);

        Intent intent = getIntent();
        String phoneNumber = intent.getStringExtra("user_tel");

        if (phoneNumber == null) {
            // no phone number
            finish();
        }

        welcomeText = findViewById(R.id.welcome);
        tellNameText = findViewById(R.id.text_tell_your_name);
        nameEdit = findViewById(R.id.edit_name);
        nextBtn = findViewById(R.id.btn_next);
        loadingCircle = findViewById(R.id.loading_circle);
        toolbar = findViewById(R.id.toolbar);

        loadingCircle.setVisibility(View.GONE);

        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setDisplayShowHomeEnabled(true);
        getSupportActionBar().setDisplayShowTitleEnabled(false);

        toolbar.setNavigationOnClickListener(v -> {
            onBackPressed();
        });

        welcomeText.setTypeface(FontManager.getFontBold(this));
        tellNameText.setTypeface(FontManager.getFontBold(this));
        nameEdit.setTypeface(FontManager.getFontBold(this));
        nextBtn.setTypeface(FontManager.getFontBold(this));

        nameEdit.requestFocus();

        nextBtn.setOnClickListener(v -> {
            enableUI(false);
            if (nameEdit.getText().toString().isEmpty()) {
                nameEdit.setError(getString(R.string.field_must_be_filled));
                nameEdit.requestFocus();
            } else {
                String userName = nameEdit.getText().toString();
                NetworkManager networkManager = new NetworkManager(this);
                networkManager.registerUser(userName, phoneNumber, (success, response) -> {
                    if (success) {
                        // login lagi biar dapet OTP
                        networkManager.userLogin(phoneNumber, (success1, response1) -> {
                            if (success1) {
                                Intent intent2 = new Intent(this, VerifyOtpActivity.class);
                                intent2.putExtra("user_tel", phoneNumber);
                                startActivity(intent2);
                                finish();
                            } else {
                                Toast.makeText(this, getString(R.string.unknown_error), Toast.LENGTH_LONG).show();
                                enableUI(true);
                            }
                        });
                    } else {
                        Toast.makeText(this, getString(R.string.unknown_error), Toast.LENGTH_LONG).show();
                        enableUI(true);
                    }
                });
            }
        });
    }

    private void enableUI(boolean enabled) {
        if (enabled) loadingCircle.setVisibility(View.GONE);
        else loadingCircle.setVisibility(View.VISIBLE);

        nextBtn.setEnabled(enabled);
        nameEdit.setEnabled(enabled);
    }


}
