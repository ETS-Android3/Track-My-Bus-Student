package com.example.trackmybusstudent;

import android.annotation.SuppressLint;

import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.os.Handler;
import android.view.MotionEvent;
import android.view.View;
import android.view.WindowInsets;

import com.example.trackmybusstudent.databinding.ActivityFullscreenBinding;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

/**
 * An example full-screen activity that shows and hides the system UI (i.e.
 * status bar and navigation/system bar) with user interaction.
 */
public class FullscreenActivity extends AppCompatActivity {
    FirebaseAuth auth;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
            setContentView(R.layout.activity_fullscreen);
//        setContentView(R.layout.activity_fullscreen);
        auth = FirebaseAuth.getInstance();
        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                Intent lgnact = new Intent(FullscreenActivity.this, LoginActivity.class);
                startActivity(lgnact);
                finish();
            }
        },3000);


    }

//    @Override
//    protected void onStart() {
//        super.onStart();
//        FirebaseUser firebaseUser = auth.getCurrentUser();
//        if(firebaseUser != null){
//
//        }
//        else{
//            Intent intent = new Intent(FullscreenActivity.this, MainActivityBuses.class);
//            startActivity(intent);
//            finish();
//        }
//    }
}