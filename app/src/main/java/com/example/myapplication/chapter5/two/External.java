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

import java.io.*;

public class External extends AppCompatActivity {

    private static final String FILE_NAME = "example_external.txt";
    private TextView tvContent;
    private EditText edtInput;
    private Button btnBack;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_external);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });

        tvContent = findViewById(R.id.tvContent);
        Button btnWrite = findViewById(R.id.btnWrite);
        Button btnRead = findViewById(R.id.btnRead);
        btnBack = findViewById(R.id.back);
        btnBack.setOnClickListener(v -> finish());

        btnWrite.setOnClickListener(v -> {
            edtInput = findViewById(R.id.edtInput);
            String data = edtInput.getText().toString();
            if (!data.isEmpty()) {
                writeToExternal(data);
            } else {
                Toast.makeText(this, "Vui lòng nhập nội dung", Toast.LENGTH_SHORT).show();
            }
        });
        btnRead.setOnClickListener(v -> {
            String content = readFromExternalStorage();
            tvContent.setText(content);
        });
    }

    private void writeToExternal(String data) {
        File file = new File(getExternalFilesDir(Environment.DIRECTORY_DOCUMENTS), FILE_NAME);

        try (FileOutputStream fos = new FileOutputStream(file)) {
            fos.write(data.getBytes());
            Toast.makeText(this, "Ghi thành công tại:\n" + file.getAbsolutePath(), Toast.LENGTH_LONG).show();
        } catch (IOException e) {
            e.printStackTrace();
            Toast.makeText(this, "Lỗi ghi file!", Toast.LENGTH_SHORT).show();
        }
    }

    private String readFromExternalStorage() {
        File file = new File(getExternalFilesDir(Environment.DIRECTORY_DOCUMENTS), FILE_NAME);

        if (!file.exists()) {
            return "File không tồn tại!";
        }

        StringBuilder sb = new StringBuilder();
        try (BufferedReader br = new BufferedReader(new FileReader(file))) {
            String line;
            while((line = br.readLine()) != null) {
                sb.append(line).append("\n");
            }
        } catch (IOException e) {
            e.printStackTrace();
            return "Lỗi đọc file!";
        }

        return sb.toString();
    }
}
