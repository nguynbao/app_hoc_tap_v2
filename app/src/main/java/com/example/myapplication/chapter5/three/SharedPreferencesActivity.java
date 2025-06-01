package com.example.myapplication.chapter5.three;

import android.content.SharedPreferences;
import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import com.example.myapplication.R;

public class SharedPreferencesActivity extends AppCompatActivity {
    private EditText edtName;
    private Button btnSave, btnLoad;
    private TextView txtShared;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_shared_preferences);

        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });

        edtName = findViewById(R.id.edt_name);
        btnSave = findViewById(R.id.btn_save);
        btnLoad = findViewById(R.id.btn_load);
        txtShared = findViewById(R.id.txt_prefs);

        btnSave.setOnClickListener(v -> {
            String username = edtName.getText().toString().trim();
            if (!username.isEmpty()) {
                saveUsername(username);
                Toast.makeText(this, "Đã lưu!", Toast.LENGTH_SHORT).show();
            } else {
                Toast.makeText(this, "Vui lòng nhập tên!", Toast.LENGTH_SHORT).show();
            }
        });

        btnLoad.setOnClickListener(v -> {
            String username = getUsername();
            txtShared.setText("Username: " + username);
        });
    }

    private void saveUsername(String username) {
        SharedPreferences prefs = getSharedPreferences("MyPrefs", MODE_PRIVATE);
        prefs.edit().putString("username", username).apply();
    }

    private String getUsername() {
        SharedPreferences prefs = getSharedPreferences("MyPrefs", MODE_PRIVATE);
        return prefs.getString("username", "Chưa có tên");
    }
}
