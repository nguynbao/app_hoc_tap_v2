package com.example.myapplication.chapter.two;

import android.content.Intent;
import android.os.Bundle;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.AppCompatButton;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import com.example.myapplication.R;

public class two_activity extends AppCompatActivity {
    private AppCompatButton Internal, External, Cache, Other,back;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_two);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });
        Internal = findViewById(R.id.Internal);
        External = findViewById(R.id.External);
        Cache = findViewById(R.id.Cache);
        Other = findViewById(R.id.Other);
        back = findViewById(R.id.back);
        back.setOnClickListener(v -> finish());
        Internal.setOnClickListener(v -> {
            startActivity(new Intent(this, Internal.class));
        });
        External.setOnClickListener(v -> {
            startActivity(new Intent(this, External.class));
        });
        Cache.setOnClickListener(v -> {
            startActivity(new Intent(this, Cache.class));
        });
        Other.setOnClickListener(v -> {
            startActivity(new Intent(this, Other.class));
        });

    }
}