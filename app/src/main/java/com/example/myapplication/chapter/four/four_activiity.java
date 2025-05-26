package com.example.myapplication.chapter.four;

import android.content.Intent;
import android.os.Bundle;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.AppCompatButton;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import com.example.myapplication.R;

public class four_activiity extends AppCompatActivity {
    private AppCompatButton a, b, c, back;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_four_activiity);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });
        a = findViewById(R.id.a);
        b = findViewById(R.id.b);
        c = findViewById(R.id.c);
        back = findViewById(R.id.btnBack);
        back.setOnClickListener(v -> finish());
        a.setOnClickListener(v -> {
            startActivity(new Intent(this, a.class));
        });
        b.setOnClickListener(v -> {
            startActivity(new Intent(this, b.class));
        });
        c.setOnClickListener(v -> {
            startActivity(new Intent(this, c.class));
        });
}}