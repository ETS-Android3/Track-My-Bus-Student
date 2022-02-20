package com.example.trackmybusstudent;

import androidx.fragment.app.Fragment;
import android.os.Build;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ProgressBar;

import androidx.annotation.RequiresApi;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.Query;
import com.google.firebase.firestore.QuerySnapshot;

import java.util.ArrayList;
import java.util.List;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link RecViewFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class RecViewFragment extends Fragment {
RecyclerView recyclerView;
ArrayList datalist;
FirebaseFirestore db;
myadapter myadap;
ProgressBar progressBar;

    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";


    private String mParam1;
    private String mParam2;

    public RecViewFragment() {
    }
    public static RecViewFragment newInstance(String param1, String param2) {
        RecViewFragment fragment = new RecViewFragment();
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

    @RequiresApi(api = Build.VERSION_CODES.M)
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
       View v = inflater.inflate(R.layout.fragment_rec_view, container, false);
       progressBar = (ProgressBar) v.findViewById(R.id.progressBarrec);
       recyclerView = (RecyclerView) v.findViewById(R.id.recycleview);
       recyclerView.setLayoutManager(new LinearLayoutManager(getContext()));
       db = FirebaseFirestore.getInstance();
       datalist = new ArrayList<>();
       myadap = new myadapter(datalist);
       recyclerView.setAdapter(myadap);
       progressBar.setVisibility(View.VISIBLE);
       db.collection("Bus_Database").orderBy("Route_Number", Query.Direction.ASCENDING).get().addOnSuccessListener(new OnSuccessListener<QuerySnapshot>() {
           @Override
           public void onSuccess(QuerySnapshot queryDocumentSnapshots) {
               List<DocumentSnapshot> list = queryDocumentSnapshots.getDocuments();
               for(DocumentSnapshot d:list){
                   model obj = d.toObject(model.class);
                   obj.id = d.getId();
                   datalist.add(obj);
                   myadap.notifyDataSetChanged();
               }
               progressBar.setVisibility(View.GONE);
           }
       });
       return v;
    }
}