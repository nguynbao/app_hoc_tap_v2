package com.example.myapplication.chapter7.one;

import android.os.Bundle;
import android.widget.TextView;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import com.example.myapplication.R;

public class khai_niem extends AppCompatActivity {
    TextView kn;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_khai_niem);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });
        kn = findViewById(R.id.kn);
        String text = "Trong thời đại công nghệ hiện nay, hầu hết các ứng dụng Android đều cần truy cập Internet để tải dữ liệu, gửi thông tin lên server hoặc đồng bộ hóa với các dịch vụ đám mây. "
                + "Networking trong Android chính là việc thiết bị thực hiện truyền dữ liệu qua mạng, ví dụ như gửi/nhận JSON từ RESTful API.\n\n"
                + "Những lưu ý khi làm việc với mạng trên Android:\n"
                + "- Kết nối mạng có thể không ổn định → cần kiểm tra kết nối trước khi gửi request.\n"
                + "- Tuyệt đối không được thực hiện request mạng trên Main Thread → gây lỗi hoặc đơ app.\n"
                + "- Phải xử lý timeout, lỗi kết nối, retry, v.v.\n"
                + "- Ưu tiên sử dụng background thread hoặc các thư viện hỗ trợ bất đồng bộ (Retrofit, OkHttp).";
        kn.setText(text);

    }
}