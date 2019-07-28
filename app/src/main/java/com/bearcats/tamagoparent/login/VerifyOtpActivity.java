package com.bearcats.tamagoparent.login;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.os.Vibrator;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import com.bearcats.tamagoparent.R;
import com.bearcats.tamagoparent.mainmenu.MainMenuActivity;
import com.bearcats.tamagoparent.manager.FontManager;
import com.bearcats.tamagoparent.manager.InterfaceManager;
import com.bearcats.tamagoparent.manager.NetworkManager;
import com.bearcats.tamagoparent.preferences.UserPreferences;
import com.bearcats.tamagoparent.views.FButton;
import com.stfalcon.smsverifycatcher.SmsVerifyCatcher;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class VerifyOtpActivity extends AppCompatActivity {

    EditText otp1;
    EditText otp2;
    EditText otp3;
    EditText otp4;
    EditText otp5;
    EditText otp6;
    SmsVerifyCatcher smsVerifyCatcher;
    LinearLayout innerLayout;
    TextView otpSentNumber;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_verify_otp);

        Intent intent = getIntent();
        String phoneNumber = intent.getStringExtra("user_tel");

        if (phoneNumber == null) {
            // no phone number
            finish();
        }

        InterfaceManager.setLightStatusBar(this);

        otp1 = findViewById(R.id.otp_1);
        otp2 = findViewById(R.id.otp_2);
        otp3 = findViewById(R.id.otp_3);
        otp4 = findViewById(R.id.otp_4);
        otp5 = findViewById(R.id.otp_5);
        otp6 = findViewById(R.id.otp_6);
        innerLayout = findViewById(R.id.linear_layout_inner);
        otpSentNumber = findViewById(R.id.text_otp_sent_number);
        Toolbar toolbar = findViewById(R.id.toolbar);
        TextView verification = findViewById(R.id.verification);
        TextView otpSentText = findViewById(R.id.text_otp_sent);
        FButton verifyBtn = findViewById(R.id.btn_verify);
        ProgressBar loadingCircle = findViewById(R.id.loading_circle);

        verification.setTypeface(FontManager.getFontBold(this));
        otpSentText.setTypeface(FontManager.getFontBold(this));
        otp1.setTypeface(FontManager.getFontBold(this));
        otp2.setTypeface(FontManager.getFontBold(this));
        otp3.setTypeface(FontManager.getFontBold(this));
        otp4.setTypeface(FontManager.getFontBold(this));
        otp5.setTypeface(FontManager.getFontBold(this));
        otp6.setTypeface(FontManager.getFontBold(this));
        verifyBtn.setTypeface(FontManager.getFontBold(this));
        otpSentNumber.setTypeface(FontManager.getFontRegular(this));
        otpSentNumber.setTextColor(getResources().getColor(R.color.textColorDisabled));
        loadingCircle.setVisibility(View.GONE);

        otpSentNumber.setText(getString(R.string.otp_sent_number, "+" + phoneNumber));

        // toolbar
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayShowTitleEnabled(false);
        getSupportActionBar().setDisplayShowHomeEnabled(true);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        toolbar.setNavigationOnClickListener(v -> {
            onBackPressed();
        });

        otp1.addTextChangedListener(new OtpTextWatcher(otp1));
        otp2.addTextChangedListener(new OtpTextWatcher(otp2));
        otp3.addTextChangedListener(new OtpTextWatcher(otp3));
        otp4.addTextChangedListener(new OtpTextWatcher(otp4));
        otp5.addTextChangedListener(new OtpTextWatcher(otp5));
        otp6.addTextChangedListener(new OtpTextWatcher(otp6));

        // OTP catcher
        smsVerifyCatcher = new SmsVerifyCatcher(this, message -> {
            String code = parseCode(message);
            otp1.setText(String.valueOf(code.charAt(0)));
            otp2.setText(String.valueOf(code.charAt(1)));
            otp3.setText(String.valueOf(code.charAt(2)));
            otp4.setText(String.valueOf(code.charAt(3)));
            otp5.setText(String.valueOf(code.charAt(4)));
            otp6.setText(String.valueOf(code.charAt(5)));
            verifyBtn.callOnClick();
        });

        verifyBtn.setOnClickListener(v -> {
            String otp = otp1.getText().toString() +
                    otp2.getText().toString() +
                    otp3.getText().toString() +
                    otp4.getText().toString() +
                    otp5.getText().toString() +
                    otp6.getText().toString();

            // disable UI objects
            loadingCircle.setVisibility(View.VISIBLE);
            otp1.setEnabled(false);
            otp2.setEnabled(false);
            otp3.setEnabled(false);
            otp4.setEnabled(false);
            otp5.setEnabled(false);
            otp6.setEnabled(false);
            verifyBtn.setEnabled(false);

            NetworkManager networkManager = new NetworkManager(this);
            networkManager.verifyOtp(phoneNumber, otp, (success, response) -> {
                if (success) {
                    int userId = response.getInt("user_id");
                    String userName = response.getString("user_name");
                    String userTel = response.getString("user_tel");
                    //String userEmail = response.getString("user_email");
                    //int userType = response.getInt("user_type");

                    UserPreferences.setUserId(this, userId);
                    UserPreferences.setUsername(this, userName);
                    UserPreferences.setUserTel(this, userTel);
                    UserPreferences.setUserLoggedIn(this, true);

                    Intent intent2 = new Intent(this, MainMenuActivity.class);
                    intent2.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK);
                    startActivity(intent2);
                    finish();

                } else {
                    String reason = response.getString("reason");
                    if (reason.equals("Invalid OTP")) {
                        shakeOtpFields();
                        // enable UI objects
                        loadingCircle.setVisibility(View.GONE);
                        otp1.setEnabled(true);
                        otp2.setEnabled(true);
                        otp3.setEnabled(true);
                        otp4.setEnabled(true);
                        otp5.setEnabled(true);
                        otp6.setEnabled(true);
                        verifyBtn.setEnabled(true);
                        otp6.setError(getString(R.string.otp_invalid));
                        otp6.requestFocus();

                    } else {
                        Toast.makeText(this, getString(R.string.unknown_error), Toast.LENGTH_LONG).show();
                        // enable UI objects
                        loadingCircle.setVisibility(View.GONE);
                        otp1.setEnabled(true);
                        otp2.setEnabled(true);
                        otp3.setEnabled(true);
                        otp4.setEnabled(true);
                        otp5.setEnabled(true);
                        otp6.setEnabled(true);
                        verifyBtn.setEnabled(true);
                    }
                }
            });
        });
    }

    private void shakeOtpFields() {
        Animation shake = AnimationUtils.loadAnimation(this, R.anim.shake);
        innerLayout.startAnimation(shake);

        Vibrator vibrator = (Vibrator) getSystemService(Context.VIBRATOR_SERVICE);
        vibrator.vibrate(1000);
    }

    private String parseCode(String message) {
        Pattern p = Pattern.compile("\\b\\d{6}\\b");
        Matcher m = p.matcher(message);
        String code = "";
        while (m.find()) {
            code = m.group(0);
        }
        return code;
    }

    @Override
    protected void onStart() {
        super.onStart();
        smsVerifyCatcher.onStart();
    }

    @Override
    protected void onStop() {
        super.onStop();
        smsVerifyCatcher.onStop();
    }

    /**
     * need for Android 6 real time permissions
     */
    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
        smsVerifyCatcher.onRequestPermissionsResult(requestCode, permissions, grantResults);
    }

    public class OtpTextWatcher implements TextWatcher {
        private View view;
        protected OtpTextWatcher(View view) {
            this.view = view;
        }

        @Override
        public void afterTextChanged(Editable editable) {
            String text = editable.toString();
            switch(view.getId()) {

                case R.id.otp_1:
                    if(text.length()==1)
                        otp2.requestFocus();
                    break;
                case R.id.otp_2:
                    if(text.length()==1)
                        otp3.requestFocus();
                    else if(text.length()==0)
                        otp1.requestFocus();
                    break;
                case R.id.otp_3:
                    if(text.length()==1)
                        otp4.requestFocus();
                    else if(text.length()==0)
                        otp2.requestFocus();
                    break;
                case R.id.otp_4:
                    if(text.length()==1)
                        otp5.requestFocus();
                    else if(text.length()==0)
                        otp3.requestFocus();
                    break;
                case R.id.otp_5:
                    if(text.length()==1)
                        otp6.requestFocus();
                    else if(text.length()==0)
                        otp4.requestFocus();
                    break;
                case R.id.otp_6:
                    if(text.length()==0)
                        otp5.requestFocus();
                    break;
            }
        }

        @Override
        public void beforeTextChanged(CharSequence arg0, int arg1, int arg2, int arg3) {
            // TODO Auto-generated method stub
        }

        @Override
        public void onTextChanged(CharSequence arg0, int arg1, int arg2, int arg3) {
            // TODO Auto-generated method stub
        }
    }
}
