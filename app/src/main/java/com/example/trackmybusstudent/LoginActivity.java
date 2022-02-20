package com.example.trackmybusstudent;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.text.TextUtils;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.gms.tasks.Task;
import com.google.android.material.textfield.TextInputEditText;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;

public class LoginActivity extends AppCompatActivity {
    View view;
    private boolean checktask;
    public TextInputEditText username;
    public TextInputEditText password;
    public String inputedusername;
    public String inputedpassword;
    private FirebaseAuth auth;
    TextView forgotpassword;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        forgotpassword = (TextView) findViewById(R.id.forgotpassword);
        view = findViewById(R.id.loginButton);
        username = findViewById(R.id.inputadminusername);
        password = findViewById(R.id.inputadminpassword);
        auth = FirebaseAuth.getInstance();
        forgotpassword.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(LoginActivity.this, Forgot_Password.class);
                startActivity(intent);
//                EditText editText = new EditText(v.getContext());
//                AlertDialog.Builder alertDialog = new AlertDialog.Builder(v.getContext());
//                alertDialog.setTitle("Resetting Password");
//                alertDialog.setMessage("Enter your Username to recieve Reset Link");
//                alertDialog.setView(editText);
//
//                alertDialog.setPositiveButton("Send Link", new DialogInterface.OnClickListener() {
//                    @Override
//                    public void onClick(DialogInterface dialog, int which) {
//                        String linkid = editText.getText().toString();
//                        auth.sendPasswordResetEmail(linkid).addOnSuccessListener(new OnSuccessListener<Void>() {
//                            @Override
//                            public void onSuccess(Void unused) {
//                                Toast.makeText(LoginActivity.this, "Reset Link sent to your EmailId", Toast.LENGTH_SHORT).show();
//                            }
//                        }).addOnFailureListener(new OnFailureListener() {
//                            @Override
//                            public void onFailure(@NonNull Exception e) {
//                                Toast.makeText(LoginActivity.this, "Error:"+e.getMessage(), Toast.LENGTH_SHORT).show();
//                            }
//                        });
//                    }
//                });
//
//                alertDialog.setNegativeButton("Back", new DialogInterface.OnClickListener() {
//                    @Override
//                    public void onClick(DialogInterface dialog, int which) {
//                    }
//                });

            }
        });
        view.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                LoginButton lgnbtn = new LoginButton(view);
                inputedpassword = password.getText().toString();
                inputedusername = username.getText().toString();
                if (TextUtils.isEmpty(inputedpassword) || TextUtils.isEmpty(inputedusername)) {
                    Toast.makeText(LoginActivity.this, "USERNAME or PASSWORD can't be empty", Toast.LENGTH_SHORT).show();
                } else {
                    lgnbtn.butttonActivated();
                    signin(inputedusername, inputedpassword);
                    Handler handle = new Handler();
                    handle.postDelayed(new Runnable() {
                        @Override
                        public void run() {
                            if(checktask == true)
                            {
                                lgnbtn.buttonTerminatedsucces();
                                Intent intent = new Intent(LoginActivity.this, MainActivityBuses.class);
                                startActivity(intent);
                                finish();
                            }
                            else{
                                lgnbtn.buttonTerminatedfail();
                                handle.postDelayed(new Runnable() {
                                    @Override
                                    public void run() {
                                        finish();
                                        overridePendingTransition(0, 0);
                                        startActivity(getIntent());
                                        overridePendingTransition(0, 0);
                                    }
                                },1000);
                            }
                        }
                    },2000);

                }

            }
        });



    }
    public void signin(String usname, String pass){
        //usname = usname +"st@gmail.com";
        auth.signInWithEmailAndPassword(usname, pass).addOnCompleteListener(LoginActivity.this, new OnCompleteListener<AuthResult>() {
            @Override
            public void onComplete(@NonNull Task<AuthResult> task) {
                if(task.isSuccessful())
                {checktask = true;}
                else
                {checktask = false;}

            }
        }).addOnFailureListener(LoginActivity.this, new OnFailureListener() {
            @Override
            public void onFailure(@NonNull Exception e) {
                Toast.makeText(LoginActivity.this, "error:"+e.getMessage(), Toast.LENGTH_SHORT).show();
            }
        });

    }


}