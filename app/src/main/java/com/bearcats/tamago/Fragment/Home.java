package com.bearcats.tamago.Fragment;

import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.os.Handler;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.CardView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.FrameLayout;
import android.widget.TextView;

import com.bearcats.tamago.Activity.Gacha;
import com.bearcats.tamago.R;
import com.bearcats.tamago.preferences.ChildPreferences;
import com.sasank.roundedhorizontalprogress.RoundedHorizontalProgressBar;

import java.text.DecimalFormat;
import java.text.DecimalFormatSymbols;

public class Home extends Fragment {
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_home, container, false);
    }

    RoundedHorizontalProgressBar roundedHorizontalProgressBar;
    TextView wallet,saving,egg;
    FrameLayout gachaButtonLayout, skinButtonLayout;
    CardView menuButton;
    boolean menuOpened = false;

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        menuOpened = false;
        roundedHorizontalProgressBar = view.findViewById(R.id.progress_bar);
        menuButton = view.findViewById(R.id.btn_menu);
        wallet = view.findViewById(R.id.tv_childWallet);
        saving = view.findViewById(R.id.tv_ChildSaving);
        egg = view.findViewById(R.id.tv_childEgg);
        skinButtonLayout = view.findViewById(R.id.skin_buttonLayout);
//        gachaButtonLayout = view.findViewById(R.id.gacha_buttonLayout);

        //set wallet, saving, and egg
        wallet.setText(FormatRp(ChildPreferences.getChildWallet(getContext())));
        saving.setText(FormatRp(ChildPreferences.getChildSaving(getContext())));
        egg.setText(ChildPreferences.getChildEgg(getContext())+"");

        roundedHorizontalProgressBar.animateProgress(2000,0,50);

        final Handler handler = new Handler();

        final Runnable runnable1 = new Runnable() {
            @Override
            public void run() {
                menuButton.setClickable(true);
                if(!menuOpened){
                    showMenu();
                }
                else{
                    closeMenu();
                }
//                Intent intent = new Intent(getContext(),Gacha.class);
//                startActivity(intent);
            }
        };

        final Runnable runnable = new Runnable() {
            @Override
            public void run() {
                menuButton.animate().translationY(0).setDuration(100);
                handler.postDelayed(runnable1,100);
            }
        };

        menuButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                menuButton.animate().translationY(20).setDuration(100);
                handler.postDelayed(runnable,100);

            }
        });


//        gachaButtonLayout.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View view) {
//                Intent intent = new Intent(getContext(), Gacha.class);
//                startActivity(intent);
//            }
//        });
    }

    public void showMenu(){
        menuOpened = true;
        gachaButtonLayout.animate().translationX(-225);
        skinButtonLayout.animate().translationX(-425);
    }

    public void closeMenu(){
        menuOpened = false;
        gachaButtonLayout.animate().translationX(0);
        skinButtonLayout.animate().translationX(0);
    }

    public String FormatRp(int amount){
        DecimalFormat format = (DecimalFormat)DecimalFormat.getCurrencyInstance();
        DecimalFormatSymbols formatRp = new DecimalFormatSymbols();

        formatRp.setCurrencySymbol("Rp ");
        formatRp.setGroupingSeparator('.');

        format.setDecimalFormatSymbols(formatRp);

        return format.format(amount).substring(0,format.format(amount).length()-3);
    }
}
