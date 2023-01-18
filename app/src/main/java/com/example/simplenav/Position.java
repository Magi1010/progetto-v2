package com.example.simplenav;

import android.Manifest;
import android.annotation.SuppressLint;
import android.app.Activity;
import android.content.Context;
import android.content.pm.PackageManager;
import android.location.Location;
import android.os.Bundle;
import android.util.Log;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;

import com.google.android.gms.location.FusedLocationProviderClient;
import com.google.android.gms.location.LocationServices;
import com.google.android.gms.tasks.OnSuccessListener;

public class Position extends AppCompatActivity{


        private final int LOCATION_PERMISSION_REQUEST_CODE = 1;
        private FusedLocationProviderClient fusedLocationClient;
        private double latitude;
        private double longitude;
        Context x;
        Activity a;

        public Position(Context x, Activity a){
            this.x = x;
            this.a = a;



        }

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        fusedLocationClient = LocationServices.getFusedLocationProviderClient(a);

        // Request location permission
        requestLocationPermission();
    }

    private void requestLocationPermission() {
            if (ActivityCompat.checkSelfPermission(x, Manifest.permission.ACCESS_FINE_LOCATION) != PackageManager.PERMISSION_GRANTED) {
                ActivityCompat.requestPermissions(a, new String[]{Manifest.permission.ACCESS_FINE_LOCATION}, LOCATION_PERMISSION_REQUEST_CODE);
                Log.d("funziona", "entra qui?");
            } else {
                getCurrentLocation();
            }
        }


        public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
            super.onRequestPermissionsResult(requestCode, permissions, grantResults);
            if (requestCode == LOCATION_PERMISSION_REQUEST_CODE) {
                if (grantResults.length > 0 && grantResults[0] == PackageManager.PERMISSION_GRANTED) {
                    getCurrentLocation();
                } else {
                    Toast.makeText(x, "Permission denied", Toast.LENGTH_SHORT).show();
                }
            }
        }

        //@SuppressLint("MissingPermission")
        @SuppressLint("MissingPermission")
        private void getCurrentLocation() {
            // fusedLocationClient.getCurrentLocation()


            fusedLocationClient.getLastLocation().addOnSuccessListener(a, new OnSuccessListener<Location>() {
                @Override
                public void onSuccess(Location location) {
                    if (location != null) {
                        latitude = location.getLatitude();
                        longitude = location.getLongitude();
                        // Do something with the location
                        Log.d("funzionaaa", latitude +"/"+ longitude + "");
                    }
                }
            });
            return;



        }

        public double getLatitude() {
            return latitude;
        }

        public double getLongitude() {
            return longitude;
        }
    }


