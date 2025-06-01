package com.example.myapplication.chapter5.three;

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

import java.io.*;

public class InternalStorageActivity extends AppCompatActivity {
    private EditText edtInput;
    private Button btnSave, btnRead;
    private TextView txtInternal;
    private final String filename = "note.txt";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_internal_storage);

        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });

        edtInput = findViewById(R.id.edt_input);
        btnSave = findViewById(R.id.btn_save);
        btnRead = findViewById(R.id.btn_read);
        txtInternal = findViewById(R.id.txt_internal);

        btnSave.setOnClickListener(v -> {
            String content = edtInput.getText().toString().trim();
            if (!content.isEmpty()) {
                writeToFile(filename, content);
            } else {
                Toast.makeText(this, "Vui lòng nhập nội dung", Toast.LENGTH_SHORT).show();
            }
        });

        btnRead.setOnClickListener(v -> {
            String content = readFromFile(filename);
            if (content != null) {
                txtInternal.setText(content);
            } else {
                txtInternal.setText("Không thể đọc dữ liệu từ tập tin.");
            }
        });
    }

    private void writeToFile(String filename, String content) {
        try (FileOutputStream fos = openFileOutput(filename, MODE_PRIVATE)) {
            fos.write(content.getBytes());
            Toast.makeText(this, "Đã ghi file thành công", Toast.LENGTH_SHORT).show();
        } catch (IOException e) {
            Toast.makeText(this, "Lỗi khi ghi file: " + e.getMessage(), Toast.LENGTH_SHORT).show();
        }
    }

    private String readFromFile(String filename) {
        StringBuilder sb = new StringBuilder();
        try (FileInputStream fis = openFileInput(filename);
             BufferedReader reader = new BufferedReader(new InputStreamReader(fis))) {

            String line;
            while ((line = reader.readLine()) != null) {
                sb.append(line).append("\n");
            }
            return sb.toString();

        } catch (IOException e) {
            Toast.makeText(this, "Lỗi khi đọc file: " + e.getMessage(), Toast.LENGTH_SHORT).show();
            return null;
        }
    }
}
