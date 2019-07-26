package com.bearcats.tamagoparent.RecyclerView;


import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.cardview.widget.CardView;
import androidx.core.content.ContextCompat;
import androidx.recyclerview.widget.RecyclerView;

import com.bearcats.tamagoparent.R;
import com.bearcats.tamagoparent.views.FButton;
import com.sasank.roundedhorizontalprogress.RoundedHorizontalProgressBar;

import java.util.ArrayList;

public class Children_Adapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {

    ArrayList<Children_Model> children_models;
    Context context;

    public Children_Adapter(Context context, ArrayList<Children_Model> children_models){
        this.context = context;
        this.children_models = children_models;
    }

    @NonNull
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        View view = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.children_layout,viewGroup,false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull RecyclerView.ViewHolder viewHolder, int i) {
        //set name
        ((ViewHolder) viewHolder).name.setText(children_models.get(i).getName());

        //set Goal Sentence
        int a = children_models.get(i).getGoal_start();
        int b = children_models.get(i).getGoal_end();
        ((ViewHolder) viewHolder).goal.setText("Goal Progress ("+a+"/"+b+")");

        //set Goal progress bar
        ((ViewHolder) viewHolder).progressBar.setProgress(a);

        //set wallet
        ((ViewHolder) viewHolder).wallet.setText(children_models.get(i).getWallet());

        //set saving
        ((ViewHolder) viewHolder).saving.setText(children_models.get(i).getSaving());

        //set egg
        ((ViewHolder) viewHolder).egg.setText(children_models.get(i).getEgg());

        //set Children avatar
        int avatar_type = children_models.get(i).getAvatar_type();
        if(avatar_type == 1){
            ((ViewHolder) viewHolder).child_icon.setImageDrawable(ContextCompat.getDrawable(context,R.drawable.avatar_1_boy));
        }
        else
        {
            ((ViewHolder) viewHolder).child_icon.setImageDrawable(ContextCompat.getDrawable(context,R.drawable.avatar_2_girl));
        }

        //add click listener here

    }

    @Override
    public int getItemCount() {
        return children_models.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder{
        TextView name,goal,wallet,saving,egg;
        ImageView child_icon;
        RoundedHorizontalProgressBar progressBar;
        FButton view, activity, addMission;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            name = itemView.findViewById(R.id.tv_name);
            goal = itemView.findViewById(R.id.tv_goal);
            wallet = itemView.findViewById(R.id.tv_ChildWallet);
            saving = itemView.findViewById(R.id.tv_childSsving);
            egg = itemView.findViewById(R.id.tv_childEgg);
            child_icon = itemView.findViewById(R.id.icon_child);
            progressBar = itemView.findViewById(R.id.progress_bar);
            view = itemView.findViewById(R.id.btn_view);
            activity = itemView.findViewById(R.id.btn_activity);
            addMission = itemView.findViewById(R.id.btn_addMission);
        }
    }
}
