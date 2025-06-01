package com.example.myapplication.chapter5.one;

import android.text.Html;

import android.os.Bundle;
import android.widget.TextView;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.AppCompatButton;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import com.example.myapplication.R;

public class one_activity extends AppCompatActivity {
    private TextView DN, DN2, DN3, DN4;
    private AppCompatButton next, back;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_one);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });
        DN2 = findViewById(R.id.DN2);
        String text = "• <b>Bộ nhớ trong (Internal Storage):</b> Dữ liệu được lưu trong vùng bộ nhớ riêng của ứng dụng. " +
                "Chỉ ứng dụng đó mới có quyền truy cập và không thể chia sẻ với ứng dụng khác." +
                "<br><br>• <b>Bộ nhớ ngoài (External Storage):</b> Dữ liệu có thể được chia sẻ giữa các ứng dụng. " +
                "Thường là thẻ SD hoặc vùng lưu trữ chung, tuy nhiên cần cấp quyền truy cập từ người dùng.";
        DN2.setText(Html.fromHtml(text,Html.FROM_HTML_MODE_LEGACY));
        DN = findViewById(R.id.DN);
        DN.setText("Trong phát triển ứng dụng di động, tập tin (file) là một thành phần quan trọng giúp lưu trữ dữ liệu lâu dài.\n\n" +
                "Việc sử dụng tập tin cho phép ứng dụng giữ lại thông tin người dùng ngay cả khi ứng dụng bị tắt hoặc khởi động lại.");
        next = findViewById(R.id.next);
        next.setOnClickListener(v -> {
            setContentView(R.layout.a);
            DN3 = findViewById(R.id.DN3);
            String text1 = "•Hệ thống tập tin Android dựa trên hệ điều hành Linux.<br>" +
                    "•Mỗi ứng dụng Android có một thư mục riêng trong /data/data/<package_name>/.<br>" +
                    "•Dữ liệu lưu trữ ở Internal Storage sẽ bị xóa khi gỡ cài đặt ứng dụng (trừ khi sao lưu).<br>" +
                    "•File có thể ở định dạng văn bản (text), nhị phân (binary), hình ảnh, âm thanh,...<br>";
            DN3.setText(Html.fromHtml(text1,Html.FROM_HTML_MODE_LEGACY));
            DN4 = findViewById(R.id.DN4);
            String text2 = "<b>Internal Storage:</b><br>" +
                    "• Đường dẫn: /data/data/&lt;tên_gói&gt;/files/<br>" +
                    "• Truy cập thông qua các phương thức:<br>" +
                    "&emsp;o openFileInput(String name)<br>" +
                    "&emsp;o openFileOutput(String name, int mode)<br><br>" +

                    "<b>External Storage:</b><br>" +
                    "• Truy cập thông qua:<br>" +
                    "&emsp;o getExternalFilesDir(String type) – thư mục riêng của app trên bộ nhớ ngoài<br>" +
                    "&emsp;o Environment.getExternalStorageDirectory() – thư mục gốc (cần cấp quyền READ/WRITE_EXTERNAL_STORAGE)";

            DN4.setText(Html.fromHtml(text2, Html.FROM_HTML_MODE_LEGACY));
            back = findViewById(R.id.back);
            back.setOnClickListener(v1 -> {
               finish();
            });

        });
        }
    }
