package com.example.myapplication.chapter7.one;

import android.os.Bundle;
import android.os.StrictMode;
import android.widget.TextView;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import com.example.myapplication.R;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;

public class strict_mode extends AppCompatActivity {
    TextView textView;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_strict_mode);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });
        textView = findViewById(R.id.text);
        // Bật StrictMode (chỉ để phát hiện lỗi khác, không còn lỗi mạng trên main thread nữa)
        StrictMode.setThreadPolicy(new StrictMode.ThreadPolicy.Builder()
                .detectNetwork()
                .penaltyLog()
                .build());

        // Gọi network trên background thread
        new Thread(() -> {
            try {
                String response = getDataFromServer();
                runOnUiThread(() -> {
                    Toast.makeText(this, "Data: " + response, Toast.LENGTH_SHORT).show();
                    textView.setText("Data"+ response);
                });
            } catch (Exception e) {
                runOnUiThread(() -> {
                    Toast.makeText(this, "Lỗi: " + e.getMessage(), Toast.LENGTH_LONG).show();
                    textView.setText("Lỗi: " + e.getMessage());
                });
                e.printStackTrace();
            }
        }).start();
    }

    // Hàm gọi mạng trên Main Thread (sai cách)
    private String getDataFromServer() throws Exception {
        URL url = new URL("https://provinces.open-api.vn/api/p/79?depth=2");
        HttpURLConnection connection = (HttpURLConnection) url.openConnection();
        connection.setRequestMethod("GET");

        // Đọc dữ liệu
        BufferedReader reader = new BufferedReader(new InputStreamReader(connection.getInputStream()));
        StringBuilder response = new StringBuilder();
        String line;

        while ((line = reader.readLine()) != null) {
            response.append(line);
        }

        reader.close();
        connection.disconnect();

        return response.toString();
    }
}