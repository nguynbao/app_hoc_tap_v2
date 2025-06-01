package com.example.myapplication.chapter6;

import android.content.Intent;
import android.os.Bundle;
import android.widget.GridView;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import com.example.myapplication.R;
import com.example.myapplication.adapter.chapter;
import com.example.myapplication.chapter5.Chapter5Activity;
import com.example.myapplication.chapter5.four.four_activiity;
import com.example.myapplication.chapter6.five.city_activity;
import com.example.myapplication.chapter6.four.test_service;
import com.example.myapplication.chapter6.one.mutil_thread;
import com.example.myapplication.chapter6.three.NetworkMonitorActivity;
import com.example.myapplication.chapter6.two.DeepLinkActivity;

public class chapter6 extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_chapter6);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });
        GridView gridView = findViewById(R.id.gridview);
        String[] titles = {
                "6.1. Multi-Thread (Xử lý đa luồng trong Android)", "6.2. Intent Filter", "6.3. Broadcast Receiver", "6.4. Android Service","ervice .NET (Sử dụng Android kết nối với Web API)"
        };
        int[] images = {
                R.drawable.ic_launcher_foreground, // dùng hình tùy bạn
                R.drawable.ic_launcher_foreground,
                R.drawable.ic_launcher_foreground,
                R.drawable.ic_launcher_foreground,
                R.drawable.ic_launcher_foreground
        };
        chapter adapter = new chapter(this, titles, images);
        gridView.setAdapter(adapter);
        gridView.setOnItemClickListener((adapterView, view, position, id) -> {
            Toast.makeText(this, "Bạn đã chọn: " + titles[position], Toast.LENGTH_SHORT).show();
            // Hoặc mở activity tương ứng
            Class<?> destination;
            switch (position) {
                case 0:
                    destination = mutil_thread.class;
                    break;
                case 1:
                    destination = DeepLinkActivity.class;
                    break;
                case 2:
                    destination = NetworkMonitorActivity.class;
                    break;
                case 3:
                    destination = test_service.class;
                    break;
                case 4:
                    destination = city_activity.class;
                    break;
                default:
                    destination = mutil_thread.class;
                    break;

            }if (destination !=null) {
                startActivity(new Intent(this, destination));
            }
        });

    }
}