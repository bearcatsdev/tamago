package com.bearcats.tamago.Recycler;

import android.content.Context;
import android.os.Handler;
import androidx.annotation.NonNull;
import androidx.core.content.ContextCompat;
import androidx.appcompat.app.AppCompatActivity;
import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.bearcats.tamago.BottomSheetDialog;
import com.bearcats.tamago.R;

import java.text.DecimalFormat;
import java.text.DecimalFormatSymbols;
import java.util.ArrayList;

public class ExtraAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {

    ArrayList<Extra_Model> extra_models;
    Context context;

    public ExtraAdapter(Context context, ArrayList<Extra_Model> extra_models){
        this.context = context;
        this.extra_models = extra_models;
    }

    @NonNull
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        View view = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.extra_layout,viewGroup,false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull RecyclerView.ViewHolder viewHolder, int i) {
        ((ViewHolder)viewHolder).task.setText(extra_models.get(i).getTaskName());
        ((ViewHolder)viewHolder).time.setText(extra_models.get(i).getTaskTime());

        int parent_type = extra_models.get(i).getParent_type();

        //set icon for parent
        switch(parent_type){
            case 1:
                ((ViewHolder)viewHolder).child_parent.setImageDrawable(ContextCompat.getDrawable(context,R.drawable.avatar_dad));
                break;
            case 2:
                ((ViewHolder)viewHolder).child_parent.setImageDrawable(ContextCompat.getDrawable(context,R.drawable.avatar_mom));
                break;
            case 3:
                ((ViewHolder)viewHolder).child_parent.setImageDrawable(ContextCompat.getDrawable(context,R.drawable.avatar_grandpa));
                break;
            case 4:
                ((ViewHolder)viewHolder).child_parent.setImageDrawable(ContextCompat.getDrawable(context,R.drawable.avatar_grandma));
                break;
            case 5:
                ((ViewHolder)viewHolder).child_parent.setImageDrawable(ContextCompat.getDrawable(context,R.drawable.avatar_chicken));
                break;
        }

        int egg = extra_models.get(i).getEgg();
        int money = extra_models.get(i).getMoney();

        //set icon for money
        if(egg!=0){
            ((ViewHolder)viewHolder).child_reward.setImageDrawable(ContextCompat.getDrawable(context,R.drawable.currency_02_egg));

        }
        else{
            ((ViewHolder)viewHolder).child_reward.setImageDrawable(ContextCompat.getDrawable(context,R.drawable.currency_00_wallet));
        }

        //format indonesia money
        DecimalFormat format = (DecimalFormat)DecimalFormat.getCurrencyInstance();
        DecimalFormatSymbols formatRp = new DecimalFormatSymbols();

        formatRp.setCurrencySymbol("Rp ");
        formatRp.setGroupingSeparator('.');

        format.setDecimalFormatSymbols(formatRp);

        // set reward, egg or money
        if(egg == 0 && money > 0){
            ((ViewHolder) viewHolder).money.setText(format.format(money).substring(0,format.format(money).length()-3));
            ((ViewHolder) viewHolder).money.setTextColor(0xFF0094D6);
        }
        else{
            ((ViewHolder) viewHolder).money.setText(egg+"");
            ((ViewHolder) viewHolder).money.setTextColor(0xFFF3B26C);
        }

        //set task icon
        int task_type = extra_models.get(i).getTask_type();

        switch (task_type){
            case 1:
                ((ViewHolder)viewHolder).child_task.setImageDrawable(ContextCompat.getDrawable(context,R.drawable.chores_01_gloves));
                break;
            case 2:
                ((ViewHolder)viewHolder).child_task.setImageDrawable(ContextCompat.getDrawable(context,R.drawable.chores_02_pump));
                break;
            case 3:
                ((ViewHolder)viewHolder).child_task.setImageDrawable(ContextCompat.getDrawable(context,R.drawable.chores_03_liquid));
                break;
            case 4:
                ((ViewHolder)viewHolder).child_task.setImageDrawable(ContextCompat.getDrawable(context,R.drawable.chores_04_garbage));
                break;
            case 5:
                ((ViewHolder)viewHolder).child_task.setImageDrawable(ContextCompat.getDrawable(context,R.drawable.chores_05_broom));
                break;
            case 6:
                ((ViewHolder)viewHolder).child_task.setImageDrawable(ContextCompat.getDrawable(context,R.drawable.chores_06_recycle));
                break;
            case 7:
                ((ViewHolder)viewHolder).child_task.setImageDrawable(ContextCompat.getDrawable(context,R.drawable.chores_07_spray));
                break;
            case 8:
                ((ViewHolder)viewHolder).child_task.setImageDrawable(ContextCompat.getDrawable(context,R.drawable.chores_08_soap));
                break;
            case 9:
                ((ViewHolder)viewHolder).child_task.setImageDrawable(ContextCompat.getDrawable(context,R.drawable.chores_09_bucket));
                break;
            case 10:
                ((ViewHolder)viewHolder).child_task.setImageDrawable(ContextCompat.getDrawable(context,R.drawable.chores_10_sponge));
                break;
            case 11:
                ((ViewHolder)viewHolder).child_task.setImageDrawable(ContextCompat.getDrawable(context,R.drawable.chores_11_laundry));
                break;
            case 12:
                ((ViewHolder)viewHolder).child_task.setImageDrawable(ContextCompat.getDrawable(context,R.drawable.chores_12_vacuum));
                break;
            case 13:
                ((ViewHolder)viewHolder).child_task.setImageDrawable(ContextCompat.getDrawable(context,R.drawable.chores_13_iron));
                break;
            case 14:
                ((ViewHolder)viewHolder).child_task.setImageDrawable(ContextCompat.getDrawable(context,R.drawable.chores_14_wiper));
                break;
            case 15:
                ((ViewHolder)viewHolder).child_task.setImageDrawable(ContextCompat.getDrawable(context,R.drawable.chores_15_dish));
                break;
            case 16:
                ((ViewHolder)viewHolder).child_task.setImageDrawable(ContextCompat.getDrawable(context,R.drawable.chores_16_brush));
                break;
        }

        final CardView done = ((ViewHolder)viewHolder).done;
        final Handler handler = new Handler();

        final Runnable runnable1 = new Runnable() {
            @Override
            public void run() {
                done.setClickable(true);
                BottomSheetDialog bottomSheetDialog = new BottomSheetDialog();
                bottomSheetDialog.show(((AppCompatActivity)context).getSupportFragmentManager(),"example");
            }
        };

        final Runnable runnable = new Runnable() {
            @Override
            public void run() {
                done.animate().translationY(0).setDuration(100);
                handler.postDelayed(runnable1,100);
            }
        };

        done.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                done.setClickable(false);
                done.animate().translationY(20).setDuration(100);
                handler.postDelayed(runnable,100);

            }
        });

    }

    @Override
    public int getItemCount() {
        return extra_models.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder{
        TextView task,money,time;
        ImageView child_parent, child_task, child_reward;
        CardView done;
        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            task = itemView.findViewById(R.id.tv_task);
            money = itemView.findViewById(R.id.tv_money);
            time = itemView.findViewById(R.id.tv_time);
            done = itemView.findViewById(R.id.btn_done);
            child_parent = itemView.findViewById(R.id.icon_parent);
            child_task = itemView.findViewById(R.id.icon_task);
            child_reward = itemView.findViewById(R.id.icon_reward);
        }
    }
}
