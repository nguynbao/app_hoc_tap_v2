package com.example.myapplication.chapter9.chapter9_one;

import android.hardware.Sensor;
import android.hardware.SensorEvent;
import android.hardware.SensorEventListener;
import android.hardware.SensorManager;
import android.os.Bundle;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import com.example.myapplication.R;

public class AccelerometerActivity extends AppCompatActivity {

    private SensorManager sensorManager;
    private Sensor accelerometer;
    private TextView textView;

    private final SensorEventListener sensorListener = new SensorEventListener() {
        @Override
        public void onSensorChanged(SensorEvent event) {
            float x = event.values[0];
            float y = event.values[1];
            float z = event.values[2];
            textView.setText("Gia tốc X: " + x + "\nGia tốc Y: " + y + "\nGia tốc Z: " + z);
        }

        @Override
        public void onAccuracyChanged(Sensor sensor, int accuracy) {}
    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sensor);

        textView = findViewById(R.id.textView);

        sensorManager = (SensorManager) getSystemService(SENSOR_SERVICE);
        accelerometer = sensorManager.getDefaultSensor(Sensor.TYPE_ACCELEROMETER);

        if (accelerometer != null) {
            sensorManager.registerListener(sensorListener, accelerometer, SensorManager.SENSOR_DELAY_NORMAL);
        } else {
            textView.setText("Không có cảm biến gia tốc!");
        }
    }

    @Override
    protected void onPause() {
        super.onPause();
        sensorManager.unregisterListener(sensorListener);
    }
}