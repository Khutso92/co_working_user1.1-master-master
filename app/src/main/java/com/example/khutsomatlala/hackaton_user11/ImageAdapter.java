package com.example.khutsomatlala.hackaton_user11;

/**
 * Created by Admin on 11/10/2017.
 */

import android.app.Activity;
import android.support.v4.view.PagerAdapter;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import com.bumptech.glide.Glide;

import java.util.List;


/**
 * Created by Loux 11/10/2017(mlabSA).
 */

public class ImageAdapter extends PagerAdapter {

    Activity activity;
    List<String> images;

    ImageAdapter(Activity activity, List<String> images) {
        this.images = images;
        this.activity = activity;

    }

    @Override

    public int getCount() {

        return images.size();

    }


    @Override

    public boolean isViewFromObject(View view, Object object) {

        return view == object;

    }


    @Override

    public Object instantiateItem(ViewGroup container, int position) {
        ImageView imageView = new ImageView(activity);

        //Sets the image url to the image
        Glide.with(activity).load(images.get(position)).into(imageView);

        container.addView(imageView, 0);

        return imageView;

    }


    @Override

    public void destroyItem(ViewGroup container, int position, Object object) {

        container.removeView((ImageView) object);

    }

}