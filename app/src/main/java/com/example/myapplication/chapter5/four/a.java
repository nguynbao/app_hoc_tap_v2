package com.example.myapplication.chapter5.four;

import android.os.Bundle;
import android.widget.TextView;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import com.example.myapplication.R;

public class a extends AppCompatActivity {
    private TextView content;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_a);

        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });

        content = findViewById(R.id.content);
        content.setText("Trạng thái trong ứng dụng Android:\n" +
                "- Là dữ liệu tạm thời hoặc giao diện hiện tại mà người dùng đang tương tác.\n" +
                "- Ví dụ: văn bản đang nhập trong EditText, mục đang chọn trong ListView,\n" +
                "  vị trí cuộn trong ScrollView, dữ liệu hiển thị trong một form.\n\n" +
                "Trạng thái không phải là dữ liệu vĩnh viễn như trong cơ sở dữ liệu,\n" +
                "mà là những thông tin tạm thời giúp duy trì trải nghiệm người dùng.\n" +
                "Nếu ứng dụng bị đóng hoặc bị hệ thống hủy mà không lưu trạng thái,\n" +
                "người dùng có thể phải thao tác lại từ đầu – điều này gây khó chịu và mất thời gian.\n\n" +
                "Mối liên hệ với vòng đời Activity:\n" +
                "- Trong Android, mỗi Activity có vòng đời.\n" +
                "- Khi người dùng rời khỏi màn hình, chuyển ứng dụng, nhận cuộc gọi hoặc xoay thiết bị,\n" +
                "  hệ thống có thể gọi pause, stop hoặc destroy.\n" +
                "- Nếu không lưu trạng thái, mọi dữ liệu nhập và UI sẽ bị mất.");
    }
}
