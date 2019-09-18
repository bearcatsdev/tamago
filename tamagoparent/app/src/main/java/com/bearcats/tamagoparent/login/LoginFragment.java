package com.bearcats.tamagoparent.login;


import android.content.Intent;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import com.bearcats.tamagoparent.R;
import com.bearcats.tamagoparent.manager.NetworkManager;
import com.bearcats.tamagoparent.manager.FontManager;
import com.bearcats.tamagoparent.views.FButton;

public class LoginFragment extends Fragment {

    private static final String TAG = "LoginFragment";

    public LoginFragment() {
        // Required empty public constructor
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_login, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        String countryCode = "62";

        NetworkManager networkManager = new NetworkManager(getContext());

        Toolbar toolbar = getView().findViewById(R.id.toolbar);
        TextView verification = getView().findViewById(R.id.verification);
        TextView inputPhoneNumber = getView().findViewById(R.id.text_input_phone_number);
        FButton countryCodeBtn = getView().findViewById(R.id.btn_country_code);
        FButton sendOtpBtn = getView().findViewById(R.id.btn_send_otp);
        EditText phoneNumberEditText = getView().findViewById(R.id.edittext_phone_number);
        TextView otpNote = getView().findViewById(R.id.text_otp_note);
        ProgressBar loadingCircle = getView().findViewById(R.id.loading_circle);

        verification.setTypeface(FontManager.getFontBold(getContext()));
        inputPhoneNumber.setTypeface(FontManager.getFontBold(getContext()));
        countryCodeBtn.setTypeface(FontManager.getFontBold(getContext()));
        sendOtpBtn.setTypeface(FontManager.getFontBold(getContext()));
        phoneNumberEditText.setTypeface(FontManager.getFontBold(getContext()));
        otpNote.setTypeface(FontManager.getFontRegular(getContext()));
        otpNote.setTextColor(getResources().getColor(R.color.textColorDisabled));
        loadingCircle.setVisibility(View.GONE);

        ((AppCompatActivity) getActivity()).setSupportActionBar(toolbar);
        ((AppCompatActivity) getActivity()).getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        ((AppCompatActivity) getActivity()).getSupportActionBar().setDisplayShowHomeEnabled(true);
        ((AppCompatActivity) getActivity()).getSupportActionBar().setDisplayShowTitleEnabled(false);

        toolbar.setNavigationOnClickListener(v -> getActivity().onBackPressed());

        sendOtpBtn.setOnClickListener(v -> {
            loadingCircle.setVisibility(View.VISIBLE);
            sendOtpBtn.setEnabled(false);
            phoneNumberEditText.setEnabled(false);
            // check if field empty
            if (phoneNumberEditText.getText().toString().isEmpty()) {
                loadingCircle.setVisibility(View.GONE);
                sendOtpBtn.setEnabled(true);
                phoneNumberEditText.setEnabled(true);
                phoneNumberEditText.requestFocus();
                phoneNumberEditText.setError(getString(R.string.field_must_be_filled));
            } else {
                if (phoneNumberEditText.getText().toString().equals("6969")) {
                    Intent intent = new Intent(getContext(), VerifyOtpActivity.class);
                    intent.putExtra("user_tel", "626969");
                    startActivity(intent);
                    // enable UI
                    loadingCircle.setVisibility(View.GONE);
                    sendOtpBtn.setEnabled(true);
                    phoneNumberEditText.setEnabled(true);
                    return;
                }
                // continue
                // remove 0 from number
                String phoneNumber = phoneNumberEditText.getText().toString();
                if (String.valueOf(phoneNumber.charAt(0)).equals("0")) {
                    phoneNumber = phoneNumber.substring(1);
                }
                phoneNumber = countryCode + phoneNumber;
                String finalPhoneNumber = phoneNumber;
                networkManager.userLogin(phoneNumber, (success, response) -> {
                    if (success) {
                        if (response.getString("response").equals("User not found")) {
                            // register new user
                            Intent intent = new Intent(getContext(), NewUserActivity.class);
                            intent.putExtra("user_tel", finalPhoneNumber);
                            startActivity(intent);// enable UI
                            loadingCircle.setVisibility(View.GONE);
                            sendOtpBtn.setEnabled(true);
                            phoneNumberEditText.setEnabled(true);

                        } else if (response.getString("response").equals("OTP sent successfully")) {
                            // verify otp
                            Intent intent = new Intent(getContext(), VerifyOtpActivity.class);
                            intent.putExtra("user_tel", finalPhoneNumber);
                            startActivity(intent);// enable UI
                            loadingCircle.setVisibility(View.GONE);
                            sendOtpBtn.setEnabled(true);
                            phoneNumberEditText.setEnabled(true);

                        } else {
                            // unknown error
                            Toast.makeText(getContext(), getString(R.string.unknown_error), Toast.LENGTH_LONG).show();
                            loadingCircle.setVisibility(View.GONE);
                            sendOtpBtn.setEnabled(true);
                            phoneNumberEditText.setEnabled(true);
                        }

                    } else {
                        // unknown error
                        Toast.makeText(getContext(), getString(R.string.unknown_error), Toast.LENGTH_LONG).show();
                        loadingCircle.setVisibility(View.GONE);
                        sendOtpBtn.setEnabled(true);
                        phoneNumberEditText.setEnabled(true);
                    }
                });
            }
        });
    }
}
