package com.example.beerdelivery;

import androidx.annotation.NonNull;
import androidx.core.app.ActivityCompat;
import androidx.fragment.app.FragmentActivity;

import android.Manifest;
import android.annotation.SuppressLint;
import android.content.pm.PackageManager;
import android.os.Bundle;
import android.os.Looper;
import android.widget.Toast;

import com.google.android.gms.location.LocationCallback;
import com.google.android.gms.location.LocationRequest;
import com.google.android.gms.location.LocationResult;
import com.google.android.gms.location.LocationServices;
import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.MarkerOptions;

public class MinhaLocalizacao extends FragmentActivity implements OnMapReadyCallback {

    private GoogleMap mMap;
    double longi, lati;
    private static final int REQUEST_CODE = 1;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_minha_localizacao);
        // Obtain the SupportMapFragment and get notified when the map is ready to be used.
        SupportMapFragment mapFragment = (SupportMapFragment) getSupportFragmentManager()
                .findFragmentById(R.id.map);
        mapFragment.getMapAsync(this);

        if (ActivityCompat.checkSelfPermission(this, Manifest.permission.ACCESS_FINE_LOCATION)
                != PackageManager.PERMISSION_GRANTED
 && ActivityCompat.checkSelfPermission(this, Manifest.permission.ACCESS_COARSE_LOCATION)
                    != PackageManager.PERMISSION_GRANTED){
            ActivityCompat.requestPermissions(
                    MinhaLocalizacao.this,
                    new String[]{Manifest.permission.ACCESS_FINE_LOCATION, Manifest.permission.ACCESS_COARSE_LOCATION},
                    REQUEST_CODE
            );
            return;

        }

                SeuLocal();

    }


    @Override
    public void onMapReady(GoogleMap googleMap) {
        mMap = googleMap;



    }


    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {

        if (requestCode == REQUEST_CODE && grantResults.length > 0) {

            if (grantResults[0] == PackageManager.PERMISSION_GRANTED) {
                SeuLocal();
            }

        }
    }


    @SuppressLint("MissingPermission")
    private void SeuLocal() {

        final LocationRequest locationRequest = new LocationRequest();
        locationRequest.setFastestInterval(5000);
        locationRequest.setInterval(2000);
        locationRequest.setPriority(LocationRequest.PRIORITY_HIGH_ACCURACY);


        LocationServices.getFusedLocationProviderClient(MinhaLocalizacao.this)
                .requestLocationUpdates(locationRequest, new LocationCallback() {
                    @Override
                    public void onLocationResult(LocationResult locationResult) {
                        super.onLocationResult(locationResult);
                        LocationServices.getFusedLocationProviderClient(MinhaLocalizacao.this)
                                .removeLocationUpdates(this);

                        if (locationResult != null && locationResult.getLocations().size() > 0) {
                            int LasteLocation = locationResult.getLocations().size() - 1;
                            longi =
                                    locationResult.getLocations().get(LasteLocation).getLongitude();
                            lati =
                                    locationResult.getLocations().get(LasteLocation).getLatitude();

                            LatLng latLng = new LatLng(lati, longi);
                            mMap.addMarker(new MarkerOptions().position(latLng).title("Seu Local"));
                            mMap.moveCamera(CameraUpdateFactory.newLatLngZoom(latLng, 17));


                        }else {
                            Toast.makeText(MinhaLocalizacao.this, "Erro",
                                    Toast.LENGTH_LONG).show();

                        }

                    }
                }, Looper.getMainLooper());






    }
}