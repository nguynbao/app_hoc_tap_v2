package com.example.myapplication.chapter6.three;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.widget.Toast;

public class NetworkChangeReceiver extends BroadcastReceiver {
    @Override
    public void onReceive(Context context, Intent intent) {
        ConnectivityManager cm =
                (ConnectivityManager) context.getSystemService(Context.CONNECTIVITY_SERVICE);

        NetworkInfo activeNetwork = cm.getActiveNetworkInfo();
        boolean isConnected = activeNetwork != null && activeNetwork.isConnected();

        if (isConnected) {
            Toast.makeText(context, "Mạng đã kết nối", Toast.LENGTH_SHORT).show();
        } else {
            Toast.makeText(context, "Mạng bị ngắt kết nối", Toast.LENGTH_SHORT).show();
        }
    }
}