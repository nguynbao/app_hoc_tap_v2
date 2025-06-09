package com.example.myapplication.chapter7;

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
import com.example.myapplication.chapter6.five.city_activity;
import com.example.myapplication.chapter6.four.test_service;
import com.example.myapplication.chapter6.one.mutil_thread;
import com.example.myapplication.chapter6.three.NetworkMonitorActivity;
import com.example.myapplication.chapter6.two.DeepLinkActivity;
import com.example.myapplication.chapter7.one.chap7_one;
import com.example.myapplication.chapter7.two.chapter7_two;

public class chapter7 extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_chapter7);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });
        GridView gridView = findViewById(R.id.gridview);
        String[] titles = {
                "7.1 Networking APIs", "7.2 MULTIMEDIA APIs"
        };
        int[] images = {
                R.drawable.ic_launcher_foreground, // dùng hình tùy bạn
                R.drawable.ic_launcher_foreground,
        };
        chapter adapter = new chapter(this, titles, images);
        gridView.setAdapter(adapter);
        gridView.setOnItemClickListener((adapterView, view, position, id) -> {
            Toast.makeText(this, "Bạn đã chọn: " + titles[position], Toast.LENGTH_SHORT).show();
            // Hoặc mở activity tương ứng
            Class<?> destination;
            switch (position) {
                case 0:
                    destination = chap7_one.class;
                    break;
                case 1:
                    destination = chapter7_two.class;
                    break;
                default:
                    destination = chap7_one.class;
                    break;

            }
            startActivity(new Intent(this, destination));
        });

    }
}