package com.example.myapplication.chapter5.four;

import android.content.SharedPreferences;
import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.example.myapplication.R;

public class c extends AppCompatActivity {

    private EditText editTextName;
    private Button btnSave, btnRestore;

    private static final String PREFS_NAME = "MyPrefs";
    private static final String KEY_NAME = "saved_name";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_c);

        editTextName = findViewById(R.id.editTextName);
        btnSave = findViewById(R.id.btnSave);
        btnRestore = findViewById(R.id.btnRestore);

        // Nút lưu dữ liệu
        btnSave.setOnClickListener(v -> {
            String name = editTextName.getText().toString();
            SharedPreferences prefs = getSharedPreferences(PREFS_NAME, MODE_PRIVATE);
            SharedPreferences.Editor editor = prefs.edit();
            editor.putString(KEY_NAME, name);
            editor.apply();
            Toast.makeText(this, "Đã lưu: " + name, Toast.LENGTH_SHORT).show();
        });

        // Nút khôi phục dữ liệu
        btnRestore.setOnClickListener(v -> {
            SharedPreferences prefs = getSharedPreferences(PREFS_NAME, MODE_PRIVATE);
            String restoredName = prefs.getString(KEY_NAME, "");
            editTextName.setText(restoredName);
            Toast.makeText(this, "Đã khôi phục: " + restoredName, Toast.LENGTH_SHORT).show();
        });
    }
}
