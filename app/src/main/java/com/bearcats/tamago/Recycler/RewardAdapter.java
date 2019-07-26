package com.bearcats.tamago;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

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

            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    BottomSheetDialog1 bottomSheetDialog1 = new BottomSheetDialog1();
                    bottomSheetDialog1.show(((AppCompatActivity)context).getSupportFragmentManager(),"example1");
                }
            });
        }
    }
}