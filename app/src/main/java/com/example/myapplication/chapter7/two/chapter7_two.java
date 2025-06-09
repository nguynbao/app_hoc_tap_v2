package com.example.myapplication.chapter7.two;

import android.content.Intent;
import android.os.Bundle;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.AppCompatButton;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import com.example.myapplication.R;

public class chapter7_two extends AppCompatActivity {
    AppCompatButton Camera, Video, Audio;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_chapter7_two);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });
        Camera = findViewById(R.id.Camera);
        Video = findViewById(R.id.Video);
        Audio = findViewById(R.id.Audio);
        Audio.setOnClickListener(v -> {
            Intent intent = new Intent(chapter7_two.this, Audio.class);
            startActivity(intent);
        });
        Video.setOnClickListener(v -> {
            Intent intent = new Intent(chapter7_two.this, Video.class);
            startActivity(intent);
        });
        Camera.setOnClickListener(v -> {
            Intent intent = new Intent(chapter7_two.this, Camera.class);
            startActivity(intent);
        });
        }
}