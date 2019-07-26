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

    }

    @Override
    public int getItemCount() {
        return children_models.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder{
        TextView name,goal;
        ImageView child_icon;
        RoundedHorizontalProgressBar progressBar;
        FButton View, Activity, AddMission;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            
        }
    }
}
