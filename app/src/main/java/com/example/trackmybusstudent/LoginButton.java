package com.example.trackmybusstudent;

import androidx.appcompat.app.AppCompatActivity;
import androidx.cardview.widget.CardView;
import androidx.constraintlayout.widget.ConstraintLayout;

import android.media.tv.TvContract;
import android.os.Bundle;
import android.view.View;
import android.widget.ProgressBar;
import android.widget.TextView;

public class LoginButton {
    private CardView cardView;
    private ConstraintLayout layout;
    private ProgressBar progressBar;
    private TextView textView;

    LoginButton(View view){
        cardView = view.findViewById(R.id.logincard);
        layout = view.findViewById(R.id.loginconstraint);
        progressBar = view.findViewById(R.id.loginprogressBar);
        textView = view.findViewById(R.id.logintext);
    }
    void butttonActivated(){
        progressBar.setVisibility(View.VISIBLE);
        textView.setText("Loading...");
    }
    void buttonTerminatedsucces(){
progressBar.setVisibility(View.GONE);
textView.setText("SUCCESS");
layout.setBackgroundColor(cardView.getResources().getColor(R.color.success_btn));
    }
    void buttonTerminatedfail(){
        progressBar.setVisibility(View.GONE);
        textView.setText("FAILED");
        layout.setBackgroundColor(cardView.getResources().getColor(R.color.failed_btn));
    }
}