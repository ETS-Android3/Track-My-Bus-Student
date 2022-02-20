package com.example.trackmybusstudent;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageButton;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

public class DialogActivity extends AppCompatActivity {
    TextView dname,droute,dcity;
    ImageButton dlocation;
    String id,lon;
    ProgressBar progressBar;

    @Override
   protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_dialog);
        progressBar = (ProgressBar) findViewById(R.id.progressBardialog);
        dname = (TextView) findViewById(R.id.named2);
        droute = (TextView) findViewById(R.id.routed2);
        dcity = (TextView) findViewById(R.id.city2);
        dlocation = (ImageButton) findViewById(R.id.located);
        progressBar.setVisibility(View.VISIBLE);
        dname.setText(getIntent().getStringExtra("dname"));
        droute.setText(getIntent().getStringExtra("droute"));
        dcity.setText(getIntent().getStringExtra("dcity"));
        progressBar.setVisibility(View.GONE);
       id = getIntent().getStringExtra("id");
 //       lon = getIntent().getStringExtra("lon").toString();

        dlocation.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(DialogActivity.this,MapActivity.class);
                intent.putExtra("iddoc",id);
               // intent.putExtra("lon",lon);
                intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                v.getContext().startActivity(intent);
            }
        });
    }}
