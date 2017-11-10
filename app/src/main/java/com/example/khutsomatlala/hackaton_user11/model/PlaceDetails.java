package com.example.khutsomatlala.hackaton_user11.model;

/**
 * Created by mlab on 10/24/2017.
 */

public class PlaceDetails {


    public String placeName;
    public String placeInfo;
    public String placeAddress;
    public String placeCell;
    public String placeHours;
    public String placeWebsite;
    public String placeLongitude;
    public String placeLatitude;
    public long price;

    public PlaceDetails(String placeName, String placeInfo, String placeAddress, String placeCell, String placeHours, String placeWebsite, String placeLongitude, String placeLatitude, long price) {
        this.placeName = placeName;
        this.placeInfo = placeInfo;
        this.placeAddress = placeAddress;
        this.placeCell = placeCell;
        this.placeHours = placeHours;
        this.placeWebsite = placeWebsite;
        this.placeLongitude = placeLongitude;
        this.placeLatitude = placeLatitude;
        this.price = price;
    }

    public PlaceDetails() {
    }


    //getters
    public String getPlaceName() {
        return placeName;
    }

    public String getPlaceInfo() {
        return placeInfo;
    }

    public String getPlaceAddress() {
        return placeAddress;
    }

    public String getPlaceCell() {
        return placeCell;
    }

    public String getPlaceHours() {
        return placeHours;
    }


    public String getPlaceWebsite() {
        return placeWebsite;
    }

    public String getPlaceLongitude() {
        return placeLongitude;
    }

    public String getPlaceLatitude() {
        return placeLatitude;
    }

    public void setPlaceName(String placeName) {
        this.placeName = placeName;
    }

    public void setPlaceInfo(String placeInfo) {
        this.placeInfo = placeInfo;
    }

    public void setPlaceAddress(String placeAddress) {
        this.placeAddress = placeAddress;
    }

    public void setPlaceCell(String placeCell) {
        this.placeCell = placeCell;
    }

    public void setPlaceHours(String placeHours) {
        this.placeHours = placeHours;
    }

    public void setPlaceWebsite(String placeWebsite) {
        this.placeWebsite = placeWebsite;
    }

    public void setPlaceLongitude(String placeLongitude) {
        this.placeLongitude = placeLongitude;
    }

    public void setPlaceLatitude(String placeLatitude) {
        this.placeLatitude = placeLatitude;
    }

    public long getPrice() {
        return price;
    }

    public void setPrice(long price) {
        this.price = price;
    }



}
