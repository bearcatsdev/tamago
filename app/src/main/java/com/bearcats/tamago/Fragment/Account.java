package com.bearcats.tamago.Fragment;

import android.content.Intent;
import android.os.Bundle;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;

import com.bearcats.tamago.Activity.ShowBarcode;
import com.bearcats.tamago.Preferences;
import com.bearcats.tamago.R;
import com.bearcats.tamago.Scan_Barcode;
import com.bearcats.tamago.preferences.ChildPreferences;


public class Account extends Fragment {

    TextView account_show_qr, account_logout, debug_add_wallet, debug_add_egg, debug_reduce_saving;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_account, container, false);
    }


    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        account_show_qr = view.findViewById(R.id.account_show_qr);
        account_logout = view.findViewById(R.id.account_logout);
        debug_add_wallet = view.findViewById(R.id.add_wallet);
        debug_add_egg = view.findViewById(R.id.add_egg);
        debug_reduce_saving = view.findViewById(R.id.reduce_saving);

        account_show_qr.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i = new Intent(getContext(), ShowBarcode.class);
                startActivity(i);
            }
        });

        account_logout.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View view) {
                ChildPreferences.clearLoggedInUser(getContext());
                Intent intent = new Intent(getContext(),Welcome.class);
                intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP | Intent.FLAG_ACTIVITY_NEW_TASK);
                getActivity().startActivity(intent);
                getActivity().finish();
            }
        });

        debug_add_wallet.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ChildPreferences.setChildWallet(getContext(), ChildPreferences.getChildWallet(getContext()) + 1000);
                Toast.makeText(getContext(), "Wallet added by 1000", Toast.LENGTH_SHORT).show();
            }
        });

        debug_add_egg.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                    ChildPreferences.setChildEgg(getContext(), ChildPreferences.getChildEgg(getContext()) + 15);
                    Toast.makeText(getContext(), "Egg added by 15", Toast.LENGTH_SHORT).show();
            }
        });

        debug_reduce_saving.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(ChildPreferences.getChildSaving(getContext())>1000){
                    ChildPreferences.setChildEgg(getContext(), ChildPreferences.getChildEgg(getContext()) - 1000);
                    Toast.makeText(getContext(), "Saving reduced by 1000", Toast.LENGTH_SHORT).show();
                }
            }
        });
    }

}
