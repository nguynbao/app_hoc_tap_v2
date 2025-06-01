package com.example.myapplication.chapter6.two;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.widget.TextView;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import com.example.myapplication.R;

public class DeepLinkActivity extends AppCompatActivity {

    private TextView textView;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_deeplink);

        textView = findViewById(R.id.textView);

        handleDeepLink(getIntent());
    }

    // Xử lý deep link khi activity nhận intent mới
    @Override
    protected void onNewIntent(Intent intent) {
        super.onNewIntent(intent);
        handleDeepLink(intent);
    }

    private void handleDeepLink(Intent intent) {
        Uri data = intent.getData();
        if (data != null) {
            String link = data.toString();
            textView.setText("App được mở từ link: " + link);
        } else {
            textView.setText("Không có dữ liệu link");
        }
    }
}
