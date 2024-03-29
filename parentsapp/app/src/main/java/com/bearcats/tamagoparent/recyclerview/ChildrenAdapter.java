package com.bearcats.tamagoparent.recyclerview;


import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.core.content.ContextCompat;
import androidx.recyclerview.widget.RecyclerView;

import com.bearcats.tamagoparent.Add_Mission;
import com.bearcats.tamagoparent.R;
import com.bearcats.tamagoparent.views.FButton;
import com.sasank.roundedhorizontalprogress.RoundedHorizontalProgressBar;

import java.text.DecimalFormat;
import java.text.DecimalFormatSymbols;
import java.util.ArrayList;

public class ChildrenAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {

    ArrayList<ChildrenModel> children_models;
    Context context;

    public ChildrenAdapter(Context context, ArrayList<ChildrenModel> children_models){
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

        //set Goal progress bar
        if (b != 0) {
            ((ViewHolder) viewHolder).progressBar.setProgress(a * 100 / b);
            ((ViewHolder) viewHolder).goal.setText("Goal Progress ("+FormatRp(a)+"/"+FormatRp(b)+")");
        } else {
            ((ViewHolder) viewHolder).progressBar.setVisibility(View.GONE);
            ((ViewHolder) viewHolder).goal.setText("No current goals");
        }

        //set wallet
        ((ViewHolder) viewHolder).wallet.setText(FormatRp(children_models.get(i).getWallet()));

        //set saving
        ((ViewHolder) viewHolder).saving.setText(FormatRp(children_models.get(i).getSaving()));

        //set egg
        ((ViewHolder) viewHolder).egg.setText(children_models.get(i).getEgg()+"");

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
        ((ViewHolder) viewHolder).addMission.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(context, Add_Mission.class);
                intent.putExtra("child_id",children_models.get(i).getId());
                context.startActivity(intent);
            }
        });

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
            wallet = itemView.findViewById(R.id.tv_childWallet);
            saving = itemView.findViewById(R.id.tv_ChildSaving);
            egg = itemView.findViewById(R.id.tv_childEgg);
            child_icon = itemView.findViewById(R.id.icon_child);
            progressBar = itemView.findViewById(R.id.progress_bar);
            view = itemView.findViewById(R.id.btn_view);
            activity = itemView.findViewById(R.id.btn_activity);
            addMission = itemView.findViewById(R.id.btn_addMission);

            //set warna pada add mision button
            addMission.setButtonColor(0xFF7FB800);
        }
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
