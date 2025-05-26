package com.example.myapplication.chapter.two;

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

import java.io.BufferedReader;
import java.io.File;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.IOException;

public class Cache extends AppCompatActivity {
    private static final String FILE_NAME = "cache_example.txt";
    private EditText etInput;
    private TextView tvContent;
    private Button btnWrite, btnRead, back;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_cache);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });
        etInput = findViewById(R.id.etInput);
        tvContent = findViewById(R.id.tvContent);
        btnWrite = findViewById(R.id.btnWrite);
        btnRead = findViewById(R.id.btnRead);
        back = findViewById(R.id.back);
        back.setOnClickListener(v -> finish());
        btnWrite.setOnClickListener(v -> {
            String data = etInput.getText().toString();
            if (!data.isEmpty()) {
                writeToCache(data);
            } else {
                Toast.makeText(this, "Nhập nội dung trước đã!", Toast.LENGTH_SHORT).show();
            }
        });

        btnRead.setOnClickListener(v -> {
            String content = readFromCache();
            tvContent.setText(content);
        });
    }

    private void writeToCache(String data) {
        File file = new File(getCacheDir(), FILE_NAME);
        try (FileOutputStream fos = new FileOutputStream(file)) {
            fos.write(data.getBytes());
            Toast.makeText(this, "Ghi cache thành công", Toast.LENGTH_SHORT).show();
        } catch (IOException e) {
            e.printStackTrace();
            Toast.makeText(this, "Lỗi ghi cache", Toast.LENGTH_SHORT).show();
        }
    }

    private String readFromCache() {
        File file = new File(getCacheDir(), FILE_NAME);
        if (!file.exists()) return "File cache không tồn tại.";

        StringBuilder sb = new StringBuilder();
        try (BufferedReader br = new BufferedReader(new FileReader(file))) {
            String line;
            while((line = br.readLine()) != null) {
                sb.append(line).append("\n");
            }
        } catch (IOException e) {
            e.printStackTrace();
            return "Lỗi đọc cache!";
        }

        return sb.toString();
    }
}
