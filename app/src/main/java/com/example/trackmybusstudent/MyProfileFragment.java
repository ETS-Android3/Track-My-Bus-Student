package com.example.trackmybusstudent;

import android.content.Intent;
import android.os.Bundle;

import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ProgressBar;
import android.widget.TextView;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.firestore.DocumentChange;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.EventListener;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.FirebaseFirestoreException;

public class MyProfileFragment extends Fragment {
    TextView name,dob,id,branch,course,pno,rno,emaiid,address;
    FirebaseFirestore store;
    FirebaseAuth auth;
    String userId;
    Button logoutbtn;
    ProgressBar progressBar;
    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;

    public MyProfileFragment() {
        // Required empty public constructor
    }
    // TODO: Rename and change types and number of parameters
    public static MyProfileFragment newInstance(String param1, String param2) {
        MyProfileFragment fragment = new MyProfileFragment();
        Bundle args = new Bundle();
        args.putString(ARG_PARAM1, param1);
        args.putString(ARG_PARAM2, param2);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            mParam1 = getArguments().getString(ARG_PARAM1);
            mParam2 = getArguments().getString(ARG_PARAM2);
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
       View view = inflater.inflate(R.layout.fragment_my_profile, container, false);
       progressBar = (ProgressBar) view.findViewById(R.id.progressBarprofile);
       name = view.findViewById(R.id.name2);
       dob = view.findViewById(R.id.dob2);
       id = view.findViewById(R.id.id2);
       branch = view.findViewById(R.id.branch);
       course = view.findViewById(R.id.course);
       pno = view.findViewById(R.id.phone2);
       rno = view.findViewById(R.id.route2);
       emaiid = view.findViewById(R.id.email2);
       address = view.findViewById(R.id.address2);
       logoutbtn = view.findViewById(R.id.logoutbtn);
       store = FirebaseFirestore.getInstance();
       auth = FirebaseAuth.getInstance();
       userId = auth.getCurrentUser().getUid();
       progressBar.setVisibility(View.VISIBLE);
        DocumentReference documentReference = store.collection("Student_Database").document(userId);
        documentReference.addSnapshotListener(new EventListener<DocumentSnapshot>() {
            @Override
            public void onEvent(@Nullable DocumentSnapshot value, @Nullable FirebaseFirestoreException error) {
                name.setText(value.getString("Name"));
                dob.setText(value.getString("Semester"));
                id.setText(value.getString("Uid"));
                branch.setText(value.getString("Branch"));
                course.setText(value.getString("Course"));
                pno.setText(value.getString("Contact_Number"));
                rno.setText(value.getString("Route_Number"));
                emaiid.setText(value.getString("Email_Id"));
                address.setText(value.getString("Address"));
                progressBar.setVisibility(View.GONE);
            }
        });


       logoutbtn.setOnClickListener(new View.OnClickListener() {
           @Override
           public void onClick(View v) {
               auth.signOut();
               Intent i = new Intent(view.getContext(), LoginActivity.class);
               i.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK);
               view.getContext().startActivity(i);
           }
       });
        return view;
    }
}