package com.example.khutsomatlala.hackaton_user11;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.RatingBar;
import android.widget.TextView;
import android.widget.Toast;

/**
 * Created by Admin on 9/15/2017.
 */

public class Ratingbar extends AppCompatActivity {


    float rate;
    String rateMessage;
    RatingBar ratingRatingBar;
    TextView ratingDisplayTextView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.ratebar);

        ratingRatingBar = (RatingBar) findViewById(R.id.rating_rating_bar);

        ratingDisplayTextView = (TextView) findViewById(R.id.rating_display_text_View);


        ratingRatingBar.setNumStars(5);


        ratingRatingBar.setOnRatingBarChangeListener(new RatingBar.OnRatingBarChangeListener() {
            @Override
            public void onRatingChanged(RatingBar ratingBar, float v, boolean b) {
                Toast.makeText(Ratingbar.this, "Stars: " + (int) v, Toast.LENGTH_SHORT).show();
            }
        });

    }





}


