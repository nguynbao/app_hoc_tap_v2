package com.example.myapplication.chapter.three;

import android.content.Intent;
import android.os.Bundle;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.AppCompatButton;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import com.example.myapplication.R;

public class three_activity extends AppCompatActivity {
    private AppCompatButton btnInternalFile, btnXmlParser, btnSharedPreferences, btnSQLite, btnContentProvider, back;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_three);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });
        btnInternalFile = findViewById(R.id.btnInternalFile);
        btnXmlParser = findViewById(R.id.btnXmlParser);
        btnSharedPreferences = findViewById(R.id.btnSharedPreferences);
        btnSQLite = findViewById(R.id.btnSQLite);
        btnContentProvider = findViewById(R.id.btnContentProvider);
        back = findViewById(R.id.btnBack);
        back.setOnClickListener(v -> finish());
        btnInternalFile.setOnClickListener(v -> {
            startActivity(new Intent(this, InternalStorageActivity.class));
        });
        btnXmlParser.setOnClickListener(v -> {
            startActivity(new Intent(this, XmlParserActivity.class));
        });
        btnSharedPreferences.setOnClickListener(v -> {
            startActivity(new Intent(this, SharedPreferencesActivity.class));
        });
        btnSQLite.setOnClickListener(v -> {
            startActivity(new Intent(this, SQLiteActivity.class));
            });
        btnContentProvider.setOnClickListener(v -> {
            startActivity(new Intent(this, ContentProviderActivity.class));
        });
        }
}