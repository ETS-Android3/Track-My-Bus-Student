package com.example.trackmybusstudent;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

public class myadapter extends RecyclerView.Adapter<myadapter.viewholder> {
    ArrayList<model> data;

    public myadapter(ArrayList<model> data) {
        this.data = data;
    }

    @NonNull
    @Override
    public viewholder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.bus_card,parent,false);
        return new viewholder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull final viewholder holder, @SuppressLint("RecyclerView") final int position) {
        holder.tvname.setText(data.get(position).getDrivers_Name());
        holder.tvroute.setText(String.valueOf(data.get(position).getRoute_Number()));
        holder.mainlayout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(holder.tvroute.getContext(),DialogActivity.class);
                intent.putExtra("dname",data.get(position).getDrivers_Name());
                intent.putExtra("dcity",data.get(position).getBus_City());
                intent.putExtra("droute",String.valueOf(data.get(position).getRoute_Number()));
                intent.putExtra("id",data.get(position).getId());
                //intent.putExtra("lon",data.get(position).getL_Longitude());
                intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                holder.tvroute.getContext().startActivity(intent);
            }
        });

    }


    @Override
    public int getItemCount() {
        return data.size();
    }

    class viewholder extends RecyclerView.ViewHolder
    {
        TextView tvname,tvroute;
        ConstraintLayout mainlayout;
        public viewholder(@NonNull View itemView) {
            super(itemView);
            tvname = (TextView) itemView.findViewById(R.id.textViewName);
            tvroute = (TextView) itemView.findViewById(R.id.textViewRoute);
            mainlayout = (ConstraintLayout) itemView.findViewById(R.id.mainlayout);
        }
    }

}
