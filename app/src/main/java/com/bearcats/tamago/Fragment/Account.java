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

import com.bearcats.tamago.Activity.ShowBarcode;
import com.bearcats.tamago.Preferences;
import com.bearcats.tamago.R;
import com.bearcats.tamago.Scan_Barcode;


public class Account extends Fragment {

    TextView account_show_qr, account_logout;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_account, container, false);
    }

    TextView showBarcode;

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        account_show_qr = view.findViewById(R.id.account_show_qr);
        account_logout = view.findViewById(R.id.account_logout);

        account_show_qr.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

            }
        });

        account_show_qr.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {


        showBarcode = view.findViewById(R.id.show_barcode);
        showBarcode.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i = new Intent(getContext(), ShowBarcode.class);
                startActivity(i);
            }
        });
    }

}
