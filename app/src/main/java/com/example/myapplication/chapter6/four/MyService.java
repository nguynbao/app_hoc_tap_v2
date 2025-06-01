package com.example.myapplication.chapter6.four;

import android.app.Service;
import android.content.Intent;
import android.os.Handler;
import android.os.IBinder;
import android.widget.Toast;

public class MyService extends Service {
    private Handler handler;
    private Runnable runnable;
    private int count = 0;

    @Override
    public void onCreate() {
        super.onCreate();
        Toast.makeText(this, "MyService đã được tạo!", Toast.LENGTH_SHORT).show();
        handler = new Handler();
    }

    @Override
    public int onStartCommand(Intent intent, int flags, int startId) {
        Toast.makeText(this, "MyService đang chạy...", Toast.LENGTH_SHORT).show();

        // Ví dụ: tạo một công việc chạy nền (Thread hoặc Handler)
        runnable = new Runnable() {
            @Override
            public void run() {
                count++;
                Toast.makeText(MyService.this, "Service đếm: " + count, Toast.LENGTH_SHORT).show();
                // Lặp lại sau 1 giây
                handler.postDelayed(this, 1000);
            }
        };
        handler.post(runnable);

        // START_STICKY: Service tự restart nếu bị kill
        return START_STICKY;
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        handler.removeCallbacks(runnable);
        Toast.makeText(this, "MyService đã dừng!", Toast.LENGTH_SHORT).show();
    }

    @Override
    public IBinder onBind(Intent intent) {
        return null; // Không cần Bound Service
    }
}
