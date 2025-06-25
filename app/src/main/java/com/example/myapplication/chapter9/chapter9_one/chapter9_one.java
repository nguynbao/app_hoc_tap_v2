package com.example.myapplication.chapter9.chapter9_one;

import android.content.Intent;
import android.hardware.Sensor;
import android.hardware.SensorManager;
import android.os.Bundle;
import android.util.Log;
import android.widget.TextView;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.AppCompatButton;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import com.example.myapplication.R;

import java.util.List;

public class chapter9_one extends AppCompatActivity {
TextView textView;
    AppCompatButton appCompatButton, light,Proximity;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_chapter9_one);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });
        textView = findViewById(R.id.text);
        appCompatButton = findViewById(R.id.appCompatButton);
        light = findViewById(R.id.Light);
        Proximity = findViewById(R.id.Proximity);
        SensorManager sensorManager = (SensorManager) getSystemService(SENSOR_SERVICE);
        List<Sensor> sensorList = sensorManager.getSensorList(Sensor.TYPE_ALL);
        StringBuilder sensorInfo = new StringBuilder();
        for (Sensor sensor : sensorList) {
            String info = "Tên: " + sensor.getName() + ", Loại: " + sensor.getType() + "\n";
            sensorInfo.append(info);
        }
        textView.setText(sensorInfo.toString());
        appCompatButton.setOnClickListener(v -> {
            Intent intent = new Intent(this, AccelerometerActivity.class);
            startActivity(intent);
        });
        light.setOnClickListener(v -> {
            Intent intent = new Intent(this, LightActivity.class);
            startActivity(intent);
        });
        Proximity.setOnClickListener(v -> {
            Intent intent = new Intent(this, ProximityActivity.class);
            startActivity(intent);
        });
    }
}




