package com.bearcats.tamago.Fragment;

import android.animation.Animator;
import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.appcompat.app.AppCompatActivity;
import androidx.cardview.widget.CardView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.FrameLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.airbnb.lottie.LottieAnimationView;
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
    LottieAnimationView chicken_idle,chicken_click;

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
        gachaButtonLayout = view.findViewById(R.id.gacha_buttonLayout);
        chicken_idle = view.findViewById(R.id.chicken_idle);
        chicken_click = view.findViewById(R.id.chicken_click);

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


        gachaButtonLayout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getContext(), Gacha.class);
                startActivity(intent);

            }
        });

        chicken_idle.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(ChildPreferences.getChildWallet(getContext()) >= 1000) {
                    ChildPreferences.setChildSaving(getContext(), ChildPreferences.getChildSaving(getContext()) + 1000);
                    saving.setText(FormatRp(ChildPreferences.getChildSaving(getContext())));
                    ChildPreferences.setChildWallet(getContext(), ChildPreferences.getChildWallet(getContext()) - 1000);
                    wallet.setText(FormatRp(ChildPreferences.getChildWallet(getContext())));
                    chicken_idle.setVisibility(View.INVISIBLE);
                    chicken_click.setVisibility(View.VISIBLE);
                    chicken_click.setSpeed(2);
                    chicken_click.playAnimation();
                }
                else{
                    Toast.makeText(getContext(), "Insufficient wallet", Toast.LENGTH_SHORT).show();
                }
            }
        });

        chicken_click.addAnimatorListener(new Animator.AnimatorListener() {
            @Override
            public void onAnimationStart(Animator animation) {
            }

            @Override
            public void onAnimationEnd(Animator animation) {
                chicken_idle.setVisibility(View.VISIBLE);
                chicken_click.setVisibility(View.INVISIBLE);
            }

            @Override
            public void onAnimationCancel(Animator animation) {
            }

            @Override
            public void onAnimationRepeat(Animator animation) {
            }
        });
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
