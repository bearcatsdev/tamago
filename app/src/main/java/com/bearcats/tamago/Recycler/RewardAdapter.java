package com.bearcats.tamago.Recycler;

import android.content.Context;
import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.bearcats.tamago.Activity.SetGoal;
import com.bearcats.tamago.BottomSheetDialog1;
import com.bearcats.tamago.R;
import com.bearcats.tamago.Reward_Model;

import java.util.ArrayList;

public class RewardAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {

    ArrayList<Reward_Model> reward_models;
    Context context;

    public RewardAdapter(Context context, ArrayList<Reward_Model> reward_models){
        this.reward_models = reward_models;
        this.context = context;
    }

    @NonNull
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        View view = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.reward_layout,viewGroup,false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull RecyclerView.ViewHolder viewHolder, int i) {
        ((ViewHolder)viewHolder).imageName.setText(reward_models.get(i).getImageName());
        ((ViewHolder)viewHolder).image.setImageResource(reward_models.get(i).getImage());

        final int product = i;
        ((ViewHolder) viewHolder).image.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(context,SetGoal.class);
                intent.putExtra("product",product);
                context.startActivity(intent);
            }
        });
    }

    @Override
    public int getItemCount() {
        return reward_models.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder{
        TextView imageName;
        ImageView image;
        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            imageName = itemView.findViewById(R.id.RewardImageName);
            image = itemView.findViewById(R.id.RewardImage);


        }
    }
}