package com.example.khutsomatlala.hackaton_user11;

import android.os.Bundle;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.widget.ImageView;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.List;


public class ImageActivity extends AppCompatActivity {
    private ImageView imageView;

    //Instantiate adapter object globally
    private ImageAdapter adapter;

    //Instantiate database object globally
    private DatabaseReference database;
    //Initialize array of images of type string
    private List<String> images = new ArrayList<>();


    @Override

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_images);


        imageView = (ImageView) findViewById(R.id.image);
        ViewPager viewPager = (ViewPager) findViewById(R.id.view_pager);

        /*
        * Initialize adapter and send parameters in constructor
        * Parameters
        * this = Send current activity object to adapter
        * images = List of image url strings Send array to adapter. Will call notifyDatasetChanged on this adapter later.
        **/
        adapter = new ImageAdapter(this, images);
        viewPager.setAdapter(adapter);

        //Queries Firebase for places endpoint
        FirebaseDatabase.getInstance().getReference().child("places").addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                if (dataSnapshot != null) {
                    images.clear();
                    if (dataSnapshot.hasChildren()) {
                        System.out.println(dataSnapshot.getChildrenCount());
                        for (DataSnapshot snapshot : dataSnapshot.getChildren()) {
                            System.out.println(snapshot.child("details").child("cover_photo").getValue());

                            //images.add(snapshot.child("url2").getValue().toString());
                            //images.add(snapshot.child("url3").getValue().toString());
                        }
                        if (images.size() > 0) {
                            adapter.notifyDataSetChanged();
                        } else {
                            System.out.println("No new items added");
                        }

                    } else {
                        System.out.println("No children found");
                    }
                } else {
                    System.out.println("No Such reference found or value is empty");
                }
            }

            @Override
            public void onCancelled(DatabaseError error) {
                // Failed to read value
                Log.w("Main ACtivity", "Failed to read value.", error.toException());
            }
        });

    }
}


