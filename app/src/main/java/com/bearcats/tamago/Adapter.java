package com.bearcats.tamago;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.util.ArrayList;

public class Adapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {

    ArrayList<Task_Model> task_models;
    Context context;

    public Adapter(Context context, ArrayList<Task_Model> task_models){
        this.context = context;
        this.task_models = task_models;
    }

    @NonNull
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        View view = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.task_layout,viewGroup,false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull RecyclerView.ViewHolder viewHolder, int i) {
        ((ViewHolder)viewHolder).task.setText(task_models.get(i).getTaskName());
        ((ViewHolder)viewHolder).taskSource.setText(task_models.get(i).getTaskSource());
        ((ViewHolder)viewHolder).money.setText(task_models.get(i).getTaskReward());
        ((ViewHolder)viewHolder).time.setText(task_models.get(i).getTaskTime());
    }

    @Override
    public int getItemCount() {
        return task_models.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder{
        TextView task,taskSource,money,time;
        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            task = itemView.findViewById(R.id.tv_task);
            taskSource = itemView.findViewById(R.id.tv_taskSource);
            money = itemView.findViewById(R.id.tv_money);
            time = itemView.findViewById(R.id.tv_time);

            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    BottomSheetDialog bottomSheetDialog = new BottomSheetDialog();
                    bottomSheetDialog.show(((AppCompatActivity)context).getSupportFragmentManager(),"example");
                }
            });
        }
    }
}
