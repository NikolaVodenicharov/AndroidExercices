package com.example.locationexercise;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;

import android.Manifest;
import android.annotation.SuppressLint;
import android.content.Context;
import android.content.DialogInterface;
import android.content.pm.PackageManager;
import android.location.Location;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.location.FusedLocationProviderClient;
import com.google.android.gms.location.LocationServices;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;

public class MainActivity extends AppCompatActivity {

    public static final int REQUEST_LOCATION_CODE = 5;
    public static final String NEED_LOCATION_PERMISSION = "To get the coordinate we need access to your location.";
    public static final String LOCATION_PERMISSION_GRANTED = "Permission granted, you can get coordinate.";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        registerGetLocationListener();
    }

    private void registerGetLocationListener() {
        Button button = findViewById(R.id.button);

        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                getLocation();
            }
        });
    }

    @SuppressLint("MissingPermission")
    private void getLocation() {
        boolean isLocationPermissionGranted = checkPermissionGranted(this, Manifest.permission.ACCESS_COARSE_LOCATION);
        if (!isLocationPermissionGranted){
            askLocationPermission();
            return;
        }

        FusedLocationProviderClient fusedLocation = LocationServices.getFusedLocationProviderClient(this);

        fusedLocation.getLastLocation().addOnCompleteListener(new OnCompleteListener<Location>() {
            @Override
            public void onComplete(@NonNull Task<Location> task) {
                Location location = task.getResult();

                if (location == null){
                    return;
                }

                TextView locationTextView = findViewById(R.id.locationTextView);
                locationTextView.setText(String.format("Longitude: %s Latitude: %s", location.getLatitude(), location.getLatitude()));
            }
        });
    }

    private void askLocationPermission() {
        if (shouldRationaleLocationPermission()){
            alertDialogRationale();
        }
        else{
            requestLocationPermission();
        }
    }
    private boolean shouldRationaleLocationPermission() {
        return ActivityCompat.shouldShowRequestPermissionRationale(this, Manifest.permission.ACCESS_COARSE_LOCATION);
    }
    private boolean checkPermissionGranted(Context context, String permission){
        int permissionResult = ActivityCompat.checkSelfPermission(context, permission);
        boolean isPermissionGranted = checkPermissionGranted(permissionResult);
        return isPermissionGranted;
    }
    private boolean checkPermissionGranted(int permissionResult){
        return permissionResult == PackageManager.PERMISSION_GRANTED;
    }
    private void alertDialogRationale() {
        AlertDialog.Builder builder = new AlertDialog.Builder(this);

        builder.setMessage(NEED_LOCATION_PERMISSION);

        builder.setPositiveButton("Agree", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                requestLocationPermission();
            }
        });

        builder.setNegativeButton("Disagree", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                dialog.dismiss();
            }
        });

        builder.show();
    }
    private void requestLocationPermission() {
        ActivityCompat.requestPermissions(
                MainActivity.this,
                new String[]{Manifest.permission.ACCESS_COARSE_LOCATION},
                REQUEST_LOCATION_CODE);
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        boolean isAccessLocationTriggered = requestCode == REQUEST_LOCATION_CODE;

        if (isAccessLocationTriggered){
            boolean arePermissionsEmpty = permissions.length == 0;
            if (arePermissionsEmpty){
                return;
            }

            boolean areGrantResultsEmpty = grantResults.length == 0;
            if (areGrantResultsEmpty){
                return;
            }

            boolean isLocationPermissionGranted = checkPermissionGranted(grantResults[0]);
            if (!isLocationPermissionGranted){
                if (!shouldRationaleLocationPermission()){

                    //ToDo should i remove the get location button if permissions is denied with don't ask me again ?
                    Toast.makeText(this, NEED_LOCATION_PERMISSION, Toast.LENGTH_LONG).show();
                }

                return;
            }

            Toast.makeText(this, LOCATION_PERMISSION_GRANTED, Toast.LENGTH_LONG).show();
        }
        else {
            super.onRequestPermissionsResult(requestCode, permissions, grantResults);
        }
    }
}