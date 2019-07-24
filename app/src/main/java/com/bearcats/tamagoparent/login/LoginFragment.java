package com.bearcats.tamagoparent.login;


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
import android.widget.TextView;
import android.widget.Toast;

import com.bearcats.tamagoparent.R;
import com.bearcats.tamagoparent.conn.NetworkManager;
import com.bearcats.tamagoparent.manager.FontManager;
import com.bearcats.tamagoparent.views.FButton;

public class LoginFragment extends Fragment {

    Toolbar toolbar;
    TextView verificaion;
    TextView inputPhoneNumber;
    FButton countryCodeBtn;
    FButton sendOtpBtn;
    EditText phoneNumberEditText;

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

        toolbar = getView().findViewById(R.id.toolbar);
        verificaion = getView().findViewById(R.id.verification);
        inputPhoneNumber = getView().findViewById(R.id.text_input_phone_number);
        countryCodeBtn = getView().findViewById(R.id.btn_country_code);
        sendOtpBtn = getView().findViewById(R.id.btn_send_otp);
        phoneNumberEditText = getView().findViewById(R.id.edittext_phone_number);

        verificaion.setTypeface(FontManager.getFontBold(getContext()));
        inputPhoneNumber.setTypeface(FontManager.getFontBold(getContext()));
        countryCodeBtn.setTypeface(FontManager.getFontBold(getContext()));
        sendOtpBtn.setTypeface(FontManager.getFontBold(getContext()));
        phoneNumberEditText.setTypeface(FontManager.getFontBold(getContext()));

        ((AppCompatActivity) getActivity()).setSupportActionBar(toolbar);
        ((AppCompatActivity) getActivity()).getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        ((AppCompatActivity) getActivity()).getSupportActionBar().setDisplayShowHomeEnabled(true);
        ((AppCompatActivity) getActivity()).getSupportActionBar().setDisplayShowTitleEnabled(false);

        toolbar.setNavigationOnClickListener(v -> getActivity().onBackPressed());

        sendOtpBtn.setOnClickListener(v -> {
            String phoneNumber = countryCode + phoneNumberEditText.getText().toString();
            networkManager.userLogin(phoneNumber, new NetworkManager.postCallback() {
                @Override
                public void onSuccess() {
                    Toast.makeText(getContext(), "SUKSES WOI", Toast.LENGTH_LONG).show();
                }

                @Override
                public void onError(String err) {
                    Toast.makeText(getContext(), err, Toast.LENGTH_LONG).show();
                }
            });

        });
    }
}
