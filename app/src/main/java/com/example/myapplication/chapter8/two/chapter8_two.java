package com.example.myapplication.chapter8.two;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.AppCompatButton;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import com.example.myapplication.R;

public class chapter8_two extends AppCompatActivity {
    AppCompatButton appCompatButton, appCompatButton2, appCompatButton3;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_chapter8_two);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });
        appCompatButton = findViewById(R.id.appCompatButton);
        appCompatButton2 = findViewById(R.id.appCompatButton2);
        appCompatButton3 = findViewById(R.id.appCompatButton3);
        appCompatButton.setOnClickListener(v -> {
            Toast.makeText(this, "Sử dụng Global Positioning Services", Toast.LENGTH_SHORT).show();
            Intent intent = new Intent(this, GpsActivity.class);
            startActivity(intent);
        });
        appCompatButton2.setOnClickListener(v -> {
            Toast.makeText(this, "Sử dụng vị trí địa lý", Toast.LENGTH_SHORT).show();
            Intent intent = new Intent(this, GeolocationActivity.class);
            startActivity(intent);
        });
        appCompatButton3.setOnClickListener(v -> {
            Toast.makeText(this, "Sử dụng vị trên bản đồ", Toast.LENGTH_SHORT).show();
            Intent intent = new Intent(this, MapsActivity.class);
            startActivity(intent);
        });
        }
    }
