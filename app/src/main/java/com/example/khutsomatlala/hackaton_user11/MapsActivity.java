package com.example.khutsomatlala.hackaton_user11;

import android.content.Intent;
import android.support.v4.app.FragmentActivity;
import android.os.Bundle;
import android.widget.Toast;

import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.MarkerOptions;

public class MapsActivity extends FragmentActivity implements OnMapReadyCallback {

    private GoogleMap mMap;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_maps);


        // Obtain the SupportMapFragment and get notified when the map is ready to be used.
        SupportMapFragment mapFragment = (SupportMapFragment) getSupportFragmentManager()
                .findFragmentById(R.id.map);
        mapFragment.getMapAsync(this);
    }


    @Override
    public void onMapReady(GoogleMap googleMap) {
        mMap = googleMap;

        Intent intent = getIntent();

    try {

        String PlaceName = intent.getStringExtra("name");

        Double lat = Double.parseDouble(intent.getStringExtra("lat"));
        Double lon = Double.parseDouble(intent.getStringExtra("lon"));

        // Add a marker in co_space and move the camera
        LatLng co_space = new LatLng(lat, lon);

        mMap.addMarker(new MarkerOptions().position(co_space).title(PlaceName));
        mMap.moveCamera(CameraUpdateFactory.newLatLngZoom(co_space, 30));
    }
        catch (NullPointerException e)
        {
            e.printStackTrace();
            Toast.makeText(this, "NullPointerException  ", Toast.LENGTH_SHORT).show();
        }
    }
}
