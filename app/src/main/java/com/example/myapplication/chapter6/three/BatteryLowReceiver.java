package com.example.myapplication.chapter6.three;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.widget.Toast;

public class BatteryLowReceiver extends BroadcastReceiver {
    @Override
    public void onReceive(Context context, Intent intent) {
        Toast.makeText(context, "Pin yếu! Vui lòng sạc thiết bị.", Toast.LENGTH_LONG).show();
    }
}