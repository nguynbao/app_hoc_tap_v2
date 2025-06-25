package com.example.myapplication.chapter9.chapter9_two;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.os.BatteryManager;
import android.os.Bundle;
import android.widget.TextView;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import com.example.myapplication.R;

public class chapter9_two extends AppCompatActivity {
    private TextView textView;
    private final BroadcastReceiver batteryReceiver = new BroadcastReceiver() {
        @Override
        public void onReceive(Context context, Intent intent) {
            int level = intent.getIntExtra(BatteryManager.EXTRA_LEVEL, -1);
            int status = intent.getIntExtra(BatteryManager.EXTRA_STATUS, -1);
            int plugged = intent.getIntExtra(BatteryManager.EXTRA_PLUGGED, -1);
            int health = intent.getIntExtra(BatteryManager.EXTRA_HEALTH, -1);
            int voltage = intent.getIntExtra(BatteryManager.EXTRA_VOLTAGE, -1);
            int temperature = intent.getIntExtra(BatteryManager.EXTRA_TEMPERATURE, -1);

            String statusString = (status == BatteryManager.BATTERY_STATUS_CHARGING) ? "Đang sạc" :
                    (status == BatteryManager.BATTERY_STATUS_FULL) ? "Đầy pin" : "Không sạc";

            String pluggedString = (plugged == BatteryManager.BATTERY_PLUGGED_USB) ? "USB" :
                    (plugged == BatteryManager.BATTERY_PLUGGED_AC) ? "AC Adapter" :
                            (plugged == BatteryManager.BATTERY_PLUGGED_WIRELESS) ? "Sạc không dây" : "Không sạc";

            String healthString = (health == BatteryManager.BATTERY_HEALTH_GOOD) ? "Tốt" :
                    (health == BatteryManager.BATTERY_HEALTH_OVERHEAT) ? "Quá nhiệt" :
                            (health == BatteryManager.BATTERY_HEALTH_DEAD) ? "Hỏng" :
                                    "Không xác định";

            textView.setText(
                    "Mức pin: " + level + "%\n" +
                            "Trạng thái: " + statusString + "\n" +
                            "Kiểu sạc: " + pluggedString + "\n" +
                            "Tình trạng pin: " + healthString + "\n" +
                            "Điện áp: " + voltage + " mV\n" +
                            "Nhiệt độ: " + (temperature / 10.0) + " °C"
            );
        }
    };
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_chapter9_two);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });
        textView = findViewById(R.id.textView);
        registerReceiver(batteryReceiver, new IntentFilter(Intent.ACTION_BATTERY_CHANGED));
    }
        @Override
        protected void onDestroy() {
            super.onDestroy();
            unregisterReceiver(batteryReceiver);
        }
    }