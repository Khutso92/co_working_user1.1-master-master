package com.example.khutsomatlala.hackaton_user11.model;

import com.google.firebase.database.Exclude;

import java.util.HashMap;
import java.util.Map;

/**
 * Created by mlab on 10/24/2017.
 */

public class Feature {

    String title, imageUrl;

    public Feature(String title, String imageUrl) {
        this.title = title;
        this.imageUrl = imageUrl;
    }

    public Feature() {
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getImageUrl() {
        return imageUrl;
    }

    public void setImageUrl(String imageUrl) {
        this.imageUrl = imageUrl;
    }



    @Exclude
    public Map<String, Object> toMap() {
        HashMap<String, Object> result = new HashMap<>();
        result.put("tietle", title);
        result.put("imageUrl", imageUrl);
        return result;
    }

}
