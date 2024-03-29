package com.bearcats.tamago.Recycler;

import android.content.Context;
import android.content.DialogInterface;
import android.graphics.Typeface;
import android.os.Handler;
import androidx.annotation.NonNull;
import androidx.appcompat.app.AlertDialog;
import androidx.core.content.ContextCompat;
import androidx.core.content.res.ResourcesCompat;
import androidx.appcompat.app.AppCompatActivity;
import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.Volley;
import com.bearcats.tamago.BottomSheetDialog;
import com.bearcats.tamago.R;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.text.DecimalFormat;
import java.text.DecimalFormatSymbols;
import java.util.ArrayList;

public class MissionsAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {

    ArrayList<Missions_Model> missions_models;
    Context context;
    JSONObject jsonObject;

    public MissionsAdapter(Context context, ArrayList<Missions_Model> missions_models){
        this.context = context;
        this.missions_models = missions_models;
    }

    @NonNull
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        View view = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.task_layout,viewGroup,false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull RecyclerView.ViewHolder viewHolder, int i) {
        ((ViewHolder)viewHolder).task.setText(missions_models.get(i).getTaskName());
        ((ViewHolder)viewHolder).time.setText(missions_models.get(i).getTaskTime());

        int parent_type = missions_models.get(i).getParent_type();

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
        }

        int egg = missions_models.get(i).getEgg();
        int money = missions_models.get(i).getMoney();

        //set button to wait
        if(missions_models.get(i).getTask_done() == 1){
            ((ViewHolder) viewHolder).done.setVisibility(View.GONE);
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
            ((ViewHolder)viewHolder).child_reward.setImageDrawable(ContextCompat.getDrawable(context,R.drawable.currency_00_wallet));
        }
        else{
            ((ViewHolder) viewHolder).money.setText(egg+"");
            ((ViewHolder) viewHolder).money.setTextColor(0xFFF3B26C);
            ((ViewHolder)viewHolder).child_reward.setImageDrawable(ContextCompat.getDrawable(context,R.drawable.currency_02_egg));
        }

        //set task icon
        int task_type = missions_models.get(i).getTask_type();

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
        final int pos = i;
        final Runnable runnable1 = new Runnable() {
            @Override
            public void run() {
                done.setClickable(true);
                AlertDialog.Builder builder = new AlertDialog.Builder(context);
                builder.setMessage("Do you realy want to confirm this mission?")
                        .setPositiveButton("Yes", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialogInterface, int a) {
                                //get Json child mission data
                                jsonObject = new JSONObject();
                                try {
                                    jsonObject.put("task_id",missions_models.get(pos).getTask_id());
                                }catch (JSONException e){
                                    e.printStackTrace();
                                }

                                JsonObjectRequest jsonObjectRequest = new JsonObjectRequest
                                        (Request.Method.POST, "https://tamago.bancet.cf/api/child/task/setTaskDone", jsonObject, new Response.Listener<JSONObject>() {
                                            @Override
                                            public void onResponse(JSONObject response) {
                                                try {
                                                    if (response.getInt("status") == 200) {
                                                        //refress
                                                    }

                                                } catch (JSONException e) {
                                                    e.printStackTrace();
                                                }
                                            }

                                        }, new Response.ErrorListener() {

                                            @Override
                                            public void onErrorResponse(VolleyError error) {

                                            }
                                        });
                                Volley.newRequestQueue(context).add(jsonObjectRequest);
                            }
                        })
                        .setNegativeButton("No", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialogInterface, int i) {

                            }
                        }).show();
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
        return missions_models.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder{
        TextView task,money,time,task_done;
        ImageView child_parent, child_task, child_reward;
        CardView done;
        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            task = itemView.findViewById(R.id.tv_task);
            money = itemView.findViewById(R.id.tv_money);
            time = itemView.findViewById(R.id.tv_time);
            task_done = itemView.findViewById(R.id.tv_task_done);
            done = itemView.findViewById(R.id.btn_done);
            child_parent = itemView.findViewById(R.id.icon_parent);
            child_task = itemView.findViewById(R.id.icon_task);
            child_reward = itemView.findViewById(R.id.icon_reward);

            Typeface face = ResourcesCompat.getFont(context, R.font.quicksand_r);
            time.setTypeface(face);
            time.setTextColor(0xFFC0C0C0);
            task_done.setTypeface(face);
            task_done.setTextColor(0xFFC0C0C0);
        }
    }
}
