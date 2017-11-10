package com.example.khutsomatlala.hackaton_user11.model;

import java.util.List;

/**
 * Created by mlab on 10/24/2017.
 */

public class WorkingSpace {


    PlaceDetails placeDetails;
    List<Feature> features;
    List<PlacePicture> pictures;

    public WorkingSpace() {
    }

    public WorkingSpace(PlaceDetails placeDetails, List<Feature> features, List<PlacePicture> pictures) {
        this.placeDetails = placeDetails;
        this.features = features;
        this.pictures = pictures;
    }

    public PlaceDetails getPlaceDetails() {
        return placeDetails;
    }

    public void setPlaceDetails(PlaceDetails placeDetails) {
        this.placeDetails = placeDetails;
    }

    public List<Feature> getFeatures() {
        return features;
    }

    public void setFeatures(List<Feature> features) {
        this.features = features;
    }

    public List<PlacePicture> getPictures() {
        return pictures;
    }

    public void setPictures(List<PlacePicture> pictures) {
        this.pictures = pictures;
    }



}
