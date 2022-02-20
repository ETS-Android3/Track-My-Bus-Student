package com.example.trackmybusstudent;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.auth.FirebaseAuth;

public class Forgot_Password extends AppCompatActivity {
    TextView linkbtn, backbtn;
    EditText inputid;
    FirebaseAuth auth;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_forgot_password);
        linkbtn = (TextView) findViewById(R.id.fpsendlink);
        backbtn = (TextView) findViewById(R.id.fpback);
        inputid = (EditText) findViewById(R.id.inputid);
        auth = FirebaseAuth.getInstance();
        linkbtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
              String text = inputid.getText().toString();
              if(text.isEmpty()){
                  Toast.makeText(Forgot_Password.this, "Please enter your email-id first", Toast.LENGTH_SHORT).show();
              }
              else{
                 auth.sendPasswordResetEmail(text).addOnSuccessListener(new OnSuccessListener<Void>() {
                     @Override
                     public void onSuccess(Void unused) {
                         Toast.makeText(Forgot_Password.this, "Reset Link sent to your EmailId", Toast.LENGTH_SHORT).show();
                         finish();
                     }
                 }).addOnFailureListener(new OnFailureListener() {
                     @Override
                     public void onFailure(@NonNull Exception e) {
                         Toast.makeText(Forgot_Password.this, "Error:"+e.getMessage(), Toast.LENGTH_SHORT).show();
                     }
                 });
              }
            }
        });
        backbtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onBackPressed();
            }
        });
    }
}