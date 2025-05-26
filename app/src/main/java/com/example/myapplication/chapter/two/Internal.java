package com.example.myapplication.chapter.two;

import android.os.Bundle;
import android.util.Log;
import android.view.View;
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

public class Internal extends AppCompatActivity {

    private static final String FILE_NAME = "example.txt";
    private TextView tvContent;
    private EditText edtInput;
    private Button back, btnWrite,btnRead;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_internal);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });
        back = findViewById(R.id.back);
        edtInput = findViewById(R.id.edtInput);
        tvContent = findViewById(R.id.tvContent);
        btnWrite = findViewById(R.id.btnWrite);
        btnRead = findViewById(R.id.btnRead);

        back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }});
        // Khi nhấn nút ghi file
        btnWrite.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String data = edtInput.getText().toString();
                if (!data.isEmpty()) {
                    writeFile(data);
                }else {
                    Toast.makeText(Internal.this,"Vui lòng nhập nội dung", Toast.LENGTH_SHORT).show();
                }
            }
        });

        // Khi nhấn nút đọc file
        btnRead.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String content = readFile();
                tvContent.setText(content);
            }
        });
    }

    // Hàm ghi file
    private void writeFile(String data) {
        Toast.makeText(this,"Ghi file thành công", Toast.LENGTH_SHORT).show();
        try {
            FileOutputStream fos = openFileOutput(FILE_NAME, MODE_PRIVATE);
            fos.write(data.getBytes());
            fos.close();
            Log.d("File", "Ghi file thành công");
        } catch (IOException e) {
            e.printStackTrace();
            Log.e("File", "Lỗi ghi file");
        }
    }

    // Hàm đọc file
    private String readFile() {
        StringBuilder sb = new StringBuilder();
        try {
            FileInputStream fis = openFileInput(FILE_NAME);
            InputStreamReader isr = new InputStreamReader(fis);
            BufferedReader br = new BufferedReader(isr);
            String line;
            while ((line = br.readLine()) != null) {
                sb.append(line).append("\n");
            }
            br.close();
            isr.close();
            fis.close();
            Log.d("File", "Đọc file thành công");
        } catch (IOException e) {
            e.printStackTrace();
            Log.e("File", "Lỗi đọc file");
        }
        return sb.toString();
    }
}
