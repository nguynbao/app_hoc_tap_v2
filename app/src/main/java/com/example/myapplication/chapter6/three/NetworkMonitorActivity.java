package com.example.myapplication.chapter6.three;

import android.content.IntentFilter;
import android.net.ConnectivityManager;
import android.net.Network;
import android.net.NetworkCapabilities;
import android.os.Build;
import android.os.Bundle;
import android.widget.TextView;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import com.example.myapplication.R;

public class NetworkMonitorActivity extends AppCompatActivity {

    private NetworkChangeReceiver networkChangeReceiver;
    private ConnectivityManager connectivityManager;
    private ConnectivityManager.NetworkCallback networkCallback;
    private TextView textView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_broadcast_receiver);

        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });
        textView = findViewById(R.id.textView113);
        networkChangeReceiver = new NetworkChangeReceiver();
        connectivityManager = (ConnectivityManager) getSystemService(CONNECTIVITY_SERVICE);

        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.N) {
            // NetworkCallback cho Android 7.0+
            networkCallback = new ConnectivityManager.NetworkCallback() {
                @Override
                public void onAvailable(Network network) {
                    runOnUiThread(() -> {
                        textView.setText("Mạng đã kết nối");
                    });
                }

                @Override
                public void onLost(Network network) {
                    runOnUiThread(() -> {
                        textView.setText("Mạng bị ngắt kết nối");
                    });
                }
            };
        }
    }

    @Override
    protected void onStart() {
        super.onStart();

        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.N) {
            // Đăng ký NetworkCallback
            connectivityManager.registerDefaultNetworkCallback(networkCallback);
        } else {
            // Đăng ký BroadcastReceiver cho các phiên bản thấp hơn
            IntentFilter filter = new IntentFilter(ConnectivityManager.CONNECTIVITY_ACTION);
            registerReceiver(networkChangeReceiver, filter);
        }
    }

    @Override
    protected void onStop() {
        super.onStop();

        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.N) {
            // Hủy đăng ký NetworkCallback
            connectivityManager.unregisterNetworkCallback(networkCallback);
        } else {
            // Hủy đăng ký BroadcastReceiver
            unregisterReceiver(networkChangeReceiver);
        }
    }
}
