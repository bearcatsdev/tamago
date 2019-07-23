package com.bearcats.tamagoparent.login;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.cardview.widget.CardView;
import androidx.fragment.app.Fragment;

import com.bearcats.tamagoparent.R;
import com.bearcats.tamagoparent.manager.FontManager;
import com.bearcats.tamagoparent.views.FButton;
import com.google.android.material.button.MaterialButton;

public class WelcomeFragment extends Fragment {

    TextView welcomeText;
    TextView termsOfService;
    TextView privacyPolicy;
    FButton letsGoButton;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup parent, Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_welcome, parent, false);
    }

    @Override
    public void onViewCreated(View view, Bundle savedInstanceState) {
        welcomeText = getView().findViewById(R.id.welcome_text);
        termsOfService = getView().findViewById(R.id.terms_of_service);
        privacyPolicy = getView().findViewById(R.id.privacy_policy);
        letsGoButton = getView().findViewById(R.id.lets_go_button);

        welcomeText.setTypeface(FontManager.getFontBold(getContext()));
        termsOfService.setTypeface(FontManager.getFontBold(getContext()));
        privacyPolicy.setTypeface(FontManager.getFontBold(getContext()));
        letsGoButton.setTypeface(FontManager.getFontBold(getContext()));
    }
}