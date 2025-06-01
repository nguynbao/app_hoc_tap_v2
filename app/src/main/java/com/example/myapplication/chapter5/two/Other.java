package com.example.myapplication.chapter5.two;

import android.os.Bundle;
import android.os.Environment;
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

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;

public class Other extends AppCompatActivity {

    private static final String FILE_NAME = "myfile.txt";
    private EditText etInput;
    private TextView tvPath;
    private Button btnInternalWrite, btnExternalWrite, back;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_other);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });

        etInput = findViewById(R.id.etInput);
        tvPath = findViewById(R.id.tvPath);
        btnInternalWrite = findViewById(R.id.btnInternalWrite);
        btnExternalWrite = findViewById(R.id.btnExternalWrite);
        back = findViewById(R.id.back);
        back.setOnClickListener(v -> finish());

        btnInternalWrite.setOnClickListener(v -> writeInternalFile());
        btnExternalWrite.setOnClickListener(v -> writeExternalFile());
    }

    private void writeInternalFile() {
        File file = new File(getFilesDir(), FILE_NAME);
        writeFile(file, "Nội dung ghi vào Internal Storage");
    }

    private void writeExternalFile() {
        File externalDir = getExternalFilesDir(Environment.DIRECTORY_DOCUMENTS);
        if (externalDir == null) {
            Toast.makeText(this, "External storage không khả dụng", Toast.LENGTH_SHORT).show();
            return;
        }

        File file = new File(externalDir, FILE_NAME);
        writeFile(file, "Nội dung ghi vào External Storage riêng của app");
    }

    private void writeFile(File file, String data) {
        try (FileOutputStream fos = new FileOutputStream(file)) {
            fos.write(data.getBytes());
            Toast.makeText(this, "Ghi file thành công", Toast.LENGTH_SHORT).show();
            tvPath.setText("File path: " + file.getAbsolutePath());
        } catch (IOException e) {
            e.printStackTrace();
            Toast.makeText(this, "Lỗi ghi file", Toast.LENGTH_SHORT).show();
        }
    }
}
