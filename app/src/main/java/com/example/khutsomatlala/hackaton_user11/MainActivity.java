package com.example.khutsomatlala.hackaton_user11;

import android.app.Activity;
import android.app.ProgressDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.RecyclerView;
import android.view.Window;
import android.widget.ImageView;

import com.example.khutsomatlala.hackaton_user11.adapter.MyItemRecyclerViewAdapter;
import com.example.khutsomatlala.hackaton_user11.model.Feature;
import com.example.khutsomatlala.hackaton_user11.model.PlaceDetails;
import com.example.khutsomatlala.hackaton_user11.model.PlacePicture;
import com.example.khutsomatlala.hackaton_user11.model.WorkingSpace;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends Activity {

    public static final String FB_DATABASE_PATH = "places";
    private DatabaseReference mDatabaseRef;
    private List<WorkingSpace> workingSpaces = new ArrayList<>();
    private RecyclerView mRecyclerView;
    private MyItemRecyclerViewAdapter adapter;
    private ProgressDialog progressDialog;
    public static Boolean stauts = false;
    ImageView ivBook;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        mRecyclerView = (RecyclerView) findViewById(R.id.recyclerView);

        // Showprogress dialog during list image loading
        progressDialog = new ProgressDialog(this);

        progressDialog.setMessage("Co working places  loading...");
        progressDialog.show();
        mDatabaseRef = FirebaseDatabase.getInstance().getReference(FB_DATABASE_PATH);

        //Init adapter
        adapter = new MyItemRecyclerViewAdapter(workingSpaces, MainActivity.this);
        mRecyclerView.setAdapter(adapter);


        mDatabaseRef.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                progressDialog.dismiss();
                workingSpaces.clear();
                //Fectching information from database
                for (DataSnapshot snapshot : dataSnapshot.getChildren()) {
                    WorkingSpace workingSpace = new WorkingSpace();

                    PlaceDetails details = new PlaceDetails();
                    details.setPlaceAddress(snapshot.child("details").child("PlaceAddress").getValue().toString());
                    details.setPlaceName(snapshot.child("details").child("PlaceName").getValue().toString());
                    details.setPlaceCell(snapshot.child("details").child("PlaceCell").getValue().toString());
                    details.setPlaceInfo(snapshot.child("details").child("PlaceInfo").getValue().toString());
                    details.setPlaceHours(snapshot.child("details").child("PlaceHours").getValue().toString());
                    details.setPrice((long) snapshot.child("details").child("PlacePrice").getValue());
                    details.setPlaceLatitude(snapshot.child("details").child("Latitude").getValue().toString());
                    details.setPlaceLongitude(snapshot.child("details").child("Longitude").getValue().toString());
                    details.setPlaceWebsite(snapshot.child("details").child("PlaceWebsite").getValue().toString());


                    workingSpace.setPlaceDetails(details);
                    List<PlacePicture> pictures = new ArrayList<>();

                    //Get and set pictures of working space
                    for (DataSnapshot snap : snapshot.child("pictures").getChildren()) {
                        PlacePicture picture = new PlacePicture();
                        picture.setImageUrl(snap.child("url").getValue().toString());
                        pictures.add(picture);
                    }
                    workingSpace.setPictures(pictures);

                    //Get and set fetaures of working space
                    List<Feature> features = new ArrayList<>();
                    for (DataSnapshot _snap : snapshot.child("features").getChildren()) {
                        Feature feature = new Feature();
                        feature.setTitle(_snap.child("title").getValue().toString());
                        feature.setImageUrl(_snap.child("image").getValue().toString());
                        features.add(feature);
                    }
                    workingSpace.setFeatures(features);

                    workingSpaces.add(workingSpace);
                }
                adapter.notifyDataSetChanged();
            }

            @Override
            public void onCancelled(DatabaseError databaseError) {

            }
        });


    }


}


