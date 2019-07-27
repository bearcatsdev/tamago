package com.bearcats.tamago.Fragment;

import android.os.Bundle;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import com.bearcats.tamago.R;
import com.bearcats.tamago.Recycler.RewardAdapter;
import com.synnapps.carouselview.CarouselView;
import com.synnapps.carouselview.ImageListener;

import java.util.ArrayList;

public class Reward extends Fragment {

    CarouselView carouselView;
    int[] image = {R.drawable.gundam,R.drawable.tamiya,R.drawable.hotwell,R.drawable.rubik};
    ArrayList<com.bearcats.tamago.Reward_Model> reward_models;

    RecyclerView recyclerView;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_reward, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        carouselView = view.findViewById(R.id.carouselView);
        recyclerView = view.findViewById(R.id.listReward);
        reward_models = new ArrayList<>();

        reward_models.add(new com.bearcats.tamago.Reward_Model(R.drawable.gundam,"Gundam"));
        reward_models.add(new com.bearcats.tamago.Reward_Model(R.drawable.tamiya,"Tamiya"));
        reward_models.add(new com.bearcats.tamago.Reward_Model(R.drawable.hotwell,"Lego"));
        reward_models.add(new com.bearcats.tamago.Reward_Model(R.drawable.rubik,"rubik"));

        recyclerView.setLayoutManager(new GridLayoutManager(getContext(),2));

        RewardAdapter rewardAdapter = new RewardAdapter(getContext(),reward_models);

        recyclerView.setAdapter(rewardAdapter);

        ImageListener imageListener = new ImageListener() {
            @Override
            public void setImageForPosition(int position, ImageView imageView) {
                imageView.setImageResource(image[position]);
            }
        };

        carouselView.setImageListener(imageListener);
        carouselView.setPageCount(image.length);


    }


}
