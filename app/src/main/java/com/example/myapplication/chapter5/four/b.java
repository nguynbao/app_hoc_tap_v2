package com.example.myapplication.chapter5.four;

import android.os.Bundle;
import android.widget.TextView;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import com.example.myapplication.R;

public class b extends AppCompatActivity {
    private TextView content;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_b);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });
        content = findViewById(R.id.content);
        content.setText(
                "5.4.2 Các trường hợp cần lưu trạng thái:\n\n" +

                        "1. Khi xoay màn hình (Configuration Change):\n" +
                        "Khi người dùng xoay thiết bị, hệ thống Android sẽ hủy và tạo lại Activity. Tất cả các View như EditText, CheckBox,... sẽ mất trạng thái nếu không lưu.\n" +
                        "Ví dụ: Người dùng đang nhập họ tên vào EditText, khi xoay màn hình nội dung sẽ bị xóa.\n" +
                        "Giải pháp: Sử dụng onSaveInstanceState() để lưu và khôi phục trong onCreate() hoặc onRestoreInstanceState().\n\n" +

                        "2. Khi có cuộc gọi hoặc chuyển sang ứng dụng khác:\n" +
                        "Activity sẽ bị tạm dừng hoặc ẩn. Nếu hệ thống cần tài nguyên, có thể hủy Activity ở nền.\n" +
                        "Ví dụ: Người dùng đang điền biểu mẫu, khi nhận cuộc gọi quay lại thì dữ liệu bị mất.\n" +
                        "Giải pháp: Lưu trạng thái tạm thời bằng Bundle, hoặc lâu dài bằng SharedPreferences.\n\n" +

                        "3. Khi hệ thống thu hồi tài nguyên:\n" +
                        "Nếu thiếu bộ nhớ, Android sẽ thu hồi Activity nền.\n" +
                        "Ví dụ: Người dùng tạm dừng ứng dụng rồi mở camera. Android có thể đóng ứng dụng cũ.\n" +
                        "Giải pháp: Lưu bằng Bundle tạm thời, hoặc file/SQLite nếu cần giữ lâu.\n\n" +

                        "4. Khi chuyển giữa các Activity:\n" +
                        "Cần lưu trạng thái để truyền dữ liệu hoặc khôi phục khi quay lại.\n" +
                        "Ví dụ: Nhập thông tin ở màn hình 1, xác nhận ở màn hình 2. Quay lại sẽ mất dữ liệu nếu không lưu.\n" +
                        "Giải pháp: Dùng Intent, ViewModel hoặc SavedStateHandle (MVVM).\n\n" +

                        "5. Khi cần lưu trạng thái lâu dài (persistent state):\n" +
                        "Một số trạng thái cần giữ lại kể cả khi app đóng hoặc máy khởi động lại.\n" +
                        "Ví dụ: Trạng thái đăng nhập, theme sáng/tối, tên người dùng nhập trước.\n" +
                        "Giải pháp: Dùng SharedPreferences, file nội bộ hoặc SQLite."
        );
    }
}