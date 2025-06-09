package com.example.myapplication.chapter7.one;

import android.content.Intent;
import android.os.Bundle;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.AppCompatButton;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import com.example.myapplication.R;

public class chap7_one extends AppCompatActivity {
    AppCompatButton khai_niem,strict_mode,truy_xuat;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_chap7_one);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });
        khai_niem = findViewById(R.id.khai_niem);
        khai_niem.setOnClickListener(view -> {
            Intent intent = new Intent(this, khai_niem.class);
            startActivity(intent);
        });
        strict_mode = findViewById(R.id.strict_mode);
        strict_mode.setOnClickListener(view -> {
            Intent intent = new Intent(this, strict_mode.class);
            startActivity(intent);
        });
        truy_xuat = findViewById(R.id.truy_xuat);
        truy_xuat.setOnClickListener(view -> {
            Intent intent = new Intent(this, truy_xuat.class);
            startActivity(intent);
        });
    }
}
