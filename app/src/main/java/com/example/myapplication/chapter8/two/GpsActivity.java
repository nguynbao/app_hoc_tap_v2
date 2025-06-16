package com.example.myapplication.chapter8.two;

import android.Manifest;
import android.content.IntentSender;
import android.content.pm.PackageManager;
import android.os.Build;
import android.os.Bundle;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;

import com.example.myapplication.R;
import com.google.android.gms.common.api.ResolvableApiException;
import com.google.android.gms.location.*;
import com.google.android.gms.tasks.Task;

import java.util.ArrayList;
import java.util.List;

public class GpsActivity extends AppCompatActivity {

    private final int REQUEST_LOCATION_PERMISSION = 1001;
    private static final int REQUEST_CHECK_GPS = 2001;


    private FusedLocationProviderClient fusedLocationClient;
    private TextView tvLocation;
    private Button btnGetLocation;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_gps);

        fusedLocationClient = LocationServices.getFusedLocationProviderClient(this);
        tvLocation = findViewById(R.id.tvLocation);
        btnGetLocation = findViewById(R.id.btnGetLocation);

        btnGetLocation.setOnClickListener(v -> checkAndRequestLocationPermission());
    }

    private void checkAndRequestLocationPermission() {
        List<String> permissionsNeeded = new ArrayList<>();

        if (ContextCompat.checkSelfPermission(this, Manifest.permission.ACCESS_FINE_LOCATION)
                != PackageManager.PERMISSION_GRANTED) {
            permissionsNeeded.add(Manifest.permission.ACCESS_FINE_LOCATION);
        }

        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.Q &&
                ContextCompat.checkSelfPermission(this, Manifest.permission.ACCESS_BACKGROUND_LOCATION)
                        != PackageManager.PERMISSION_GRANTED) {
            permissionsNeeded.add(Manifest.permission.ACCESS_BACKGROUND_LOCATION);
        }

        if (!permissionsNeeded.isEmpty()) {
            ActivityCompat.requestPermissions(this,
                    permissionsNeeded.toArray(new String[0]),
                    REQUEST_LOCATION_PERMISSION);
        } else {
            turnOnGPS();
        }
    }
    private void turnOnGPS() {
        LocationRequest locationRequest = LocationRequest.create()
                .setPriority(Priority.PRIORITY_HIGH_ACCURACY)
                .setInterval(10000)
                .setFastestInterval(5000);

        LocationSettingsRequest.Builder builder = new LocationSettingsRequest.Builder()
                .addLocationRequest(locationRequest);

        SettingsClient client = LocationServices.getSettingsClient(this);
        Task<LocationSettingsResponse> task = client.checkLocationSettings(builder.build());

        task.addOnSuccessListener(locationSettingsResponse -> {
            getCurrentLocation(); // GPS đã bật
        });

        task.addOnFailureListener(e -> {
            if (e instanceof ResolvableApiException) {
                try {
                    ((ResolvableApiException) e).startResolutionForResult(this, REQUEST_CHECK_GPS);
                } catch (IntentSender.SendIntentException ex) {
                    Toast.makeText(this, "Không thể bật GPS", Toast.LENGTH_SHORT).show();
                }
            } else {
                Toast.makeText(this, "Thiết bị không hỗ trợ GPS", Toast.LENGTH_SHORT).show();
            }
        });
    }
    private void checkGPSEnabledAndFetchLocation() {
        LocationRequest locationRequest = LocationRequest.create()
                .setPriority(Priority.PRIORITY_HIGH_ACCURACY)
                .setInterval(10000)
                .setFastestInterval(5000);

        LocationSettingsRequest settingsRequest = new LocationSettingsRequest.Builder()
                .addLocationRequest(locationRequest)
                .build();

        SettingsClient client = LocationServices.getSettingsClient(this);
        client.checkLocationSettings(settingsRequest)
                .addOnSuccessListener(locationSettingsResponse -> getCurrentLocation())
                .addOnFailureListener(e -> {
                    Toast.makeText(this, "Vui lòng bật GPS để lấy vị trí", Toast.LENGTH_LONG).show();
                });
    }

    private void getCurrentLocation() {
        if (ActivityCompat.checkSelfPermission(this, Manifest.permission.ACCESS_FINE_LOCATION)
                != PackageManager.PERMISSION_GRANTED) {
            Toast.makeText(this, "Chưa có quyền truy cập vị trí", Toast.LENGTH_SHORT).show();
            return;
        }

        fusedLocationClient.getLastLocation()
                .addOnSuccessListener(location -> {
                    if (location != null) {
                        String latLng = "Latitude: " + location.getLatitude() +
                                "\nLongitude: " + location.getLongitude();
                        tvLocation.setText(latLng);
                    } else {
                        tvLocation.setText("Không thể lấy được vị trí. Thử lại sau.");
                    }
                })
                .addOnFailureListener(e -> {
                    tvLocation.setText("Lỗi khi lấy vị trí: " + e.getMessage());
                });
    }

    @Override
    public void onRequestPermissionsResult(int requestCode,
                                           @NonNull String[] permissions,
                                           @NonNull int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
        if (requestCode == REQUEST_LOCATION_PERMISSION) {
            boolean allGranted = true;
            for (int result : grantResults) {
                if (result != PackageManager.PERMISSION_GRANTED) {
                    allGranted = false;
                    break;
                }
            }
            if (allGranted) {
                checkGPSEnabledAndFetchLocation();
            } else {
                Toast.makeText(this, "Bạn cần cấp quyền vị trí", Toast.LENGTH_SHORT).show();
            }
        }
    }
}
