package com.example.trackmybusstudent;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.util.Log;
import android.widget.TextView;
import android.widget.Toast;

import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.EventListener;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.FirebaseFirestoreException;
import com.google.firebase.firestore.ListenerRegistration;

public class samplemap extends AppCompatActivity {
    TextView lati;
    TextView logi,id;
    String docid;
    FirebaseFirestore fstore;
    int i =0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_samplemap);
        lati = (TextView) findViewById(R.id.textView6);
        logi = (TextView) findViewById(R.id.textView7);
id = (TextView) findViewById(R.id.textView8);
//id.setText(getIntent().getStringExtra("iddoc"));
        docid = getIntent().getStringExtra("iddoc");
        fstore = FirebaseFirestore.getInstance();
        DocumentReference documentReference = fstore.collection("Bus_Database").document(docid);
        documentReference.addSnapshotListener(new EventListener<DocumentSnapshot>() {
            @Override
            public void onEvent(@Nullable DocumentSnapshot value, @Nullable FirebaseFirestoreException error) {
                if(value != null && value.exists()){
                    lati.setText(value.getDouble("Latitude").toString());
                  logi.setText(value.getDouble("Longitude").toString());
                  id.setText(String.valueOf(i));
                  i++;
                }
                else{
                    Toast.makeText(samplemap.this, "data not available", Toast.LENGTH_SHORT).show();
                }
            }
        });
//        final DocumentReference docRef = fstore.collection("Bus_Database").document("1RnpDcghlJikr3FcRdcy");
//        ListenerRegistration listenerRegistration = docRef.addSnapshotListener(new EventListener<DocumentSnapshot>() {
//            @Override
//            public void onEvent(@Nullable DocumentSnapshot value, @Nullable FirebaseFirestoreException error) {
//                if (error != null) {
//                    //Log.w(TAG, "Listen failed.", error);
//                    return;
//                }
//
//                if (value != null && value.exists()) {
//                    lati.setText(value.getDate("Latitude").toString());
//                    logi.setText(value.getDate("Longitude").toString());
//                    // Log.d(TAG, "Current data: " + snapshot.getData());
//                } else {
//                    Toast.makeText(samplemap.this, "data not exist", Toast.LENGTH_SHORT).show();
//                }
//            })
//        };
    }

}