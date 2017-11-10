package com.example.khutsomatlala.hackaton_user11;

import android.app.Activity;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.ImageView;

import com.bumptech.glide.Glide;

public class MaxPicActivity extends Activity {

    ImageView max_pic_dialog;


    String max_pic;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_max_pic);


        Intent i = getIntent();
        max_pic = i.getStringExtra("max_pic");

        max_pic_dialog = (ImageView) findViewById(R.id.max_pic);

        Glide.with(this)
                .load(max_pic)
               // .override(300, 200)
                .centerCrop()
                .into(max_pic_dialog);
    }
}
