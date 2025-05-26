package com.example.myapplication.chapter;


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
import com.example.myapplication.adapter.GridAdapter;
import com.example.myapplication.chapter.four.four_activiity;
import com.example.myapplication.chapter.one.one_activity;
import com.example.myapplication.chapter.three.three_activity;
import com.example.myapplication.chapter.two.two_activity;

public class Chapter5Activity extends AppCompatActivity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_chapter5);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });

        GridView gridView = findViewById(R.id.gridview);

        String[] titles = {
                "5.1. Khái niệm tập tin trên di động", "5.2. Các loại tập tin trong Android", "5.3 Xử lý tập tin trong Android", "5.4 Lưu trạng thái ứng dụng"
        };

        int[] images = {
                R.drawable.ic_launcher_foreground, // dùng hình tùy bạn
                R.drawable.ic_launcher_foreground,
                R.drawable.ic_launcher_foreground,
                R.drawable.ic_launcher_foreground
        };

        GridAdapter adapter = new GridAdapter(this, titles, images);
        gridView.setAdapter(adapter);
        gridView.setOnItemClickListener((adapterView, view, position, id) -> {
            Toast.makeText(this, "Bạn đã chọn: " + titles[position], Toast.LENGTH_SHORT).show();
            // Hoặc mở activity tương ứng
            Class<?> destination;
            switch (position) {
                case 0:
                    destination = one_activity.class;
                    break;
                case 1:
                    destination = two_activity.class;
                    break;
                case 2:
                    destination = three_activity.class;
                    break;
                case 3:
                    destination = four_activiity.class;
                    break;
                default:
                    destination = Chapter5Activity.class;
                    break;

        }if (destination !=null) {
                startActivity(new Intent(this, destination));
            }
        });
    }
}
