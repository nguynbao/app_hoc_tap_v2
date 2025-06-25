package com.example.myapplication.chapter9;

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
import com.example.myapplication.chapter8.one.chapter8_one;
import com.example.myapplication.chapter8.two.chapter8_two;
import com.example.myapplication.chapter9.chapter9_one.chapter9_one;
import com.example.myapplication.chapter9.chapter9_two.chapter9_two;

public class chapter9 extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_chapter9);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });
        GridView gridView = findViewById(R.id.gridview);
        String[] titles = {
                "9.1 Sensors",
                "9.2 Monitoring the Battery"

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
                case 1:
                    destination = chapter9_two.class;
                    break;
                default:
                    destination = chapter9_one.class;
                    break;
            }
            startActivity(new Intent(this, destination));
        });

    }
}