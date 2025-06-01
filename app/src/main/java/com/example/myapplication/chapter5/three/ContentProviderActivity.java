package com.example.myapplication.chapter5.three;

import android.Manifest;
import android.content.pm.PackageManager;
import android.database.Cursor;
import android.net.Uri;
import android.os.Bundle;
import android.provider.CalendarContract;
import android.provider.CallLog;
import android.provider.ContactsContract;
import android.provider.MediaStore;
import android.widget.TextView;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import com.example.myapplication.R;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;

public class ContentProviderActivity extends AppCompatActivity {
    private TextView txtAll;

    private static final int REQUEST_CONTACT_PERMISSION = 1;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_content_provider);

        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });
        txtAll = findViewById(R.id.txtAll);

        // Kiểm tra quyền cần thiết
        String[] permissions = {
                Manifest.permission.READ_CONTACTS,
                Manifest.permission.READ_CALL_LOG,
                Manifest.permission.READ_SMS,
                Manifest.permission.READ_CALENDAR,
                Manifest.permission.READ_EXTERNAL_STORAGE // Hoặc READ_MEDIA_IMAGES trên Android 13+
        };

        boolean allGranted = true;
        for (String perm : permissions) {
            if (ContextCompat.checkSelfPermission(this, perm) != PackageManager.PERMISSION_GRANTED) {
                allGranted = false;
                break;
            }
        }

        if (!allGranted) {
            ActivityCompat.requestPermissions(this, permissions, 101);
        } else {
            loadAllContent();
        }
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, String[] permissions, int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
        if (requestCode == 101) {
            boolean granted = true;
            for (int res : grantResults) {
                if (res != PackageManager.PERMISSION_GRANTED) {
                    granted = false;
                    break;
                }
            }
            if (granted) {
                loadAllContent();
            } else {
                txtAll.setText("Bạn cần cấp đầy đủ quyền để xem nội dung.");
            }
        }
    }

    private void loadAllContent() {
        StringBuilder sb = new StringBuilder();

        sb.append("📞 DANH BẠ:\n");
        Cursor contacts = getContentResolver().query(
                ContactsContract.CommonDataKinds.Phone.CONTENT_URI,
                null, null, null, null);
        if (contacts != null) {
            while (contacts.moveToNext()) {
                String name = contacts.getString(contacts.getColumnIndex(ContactsContract.CommonDataKinds.Phone.DISPLAY_NAME));
                String phone = contacts.getString(contacts.getColumnIndex(ContactsContract.CommonDataKinds.Phone.NUMBER));
                sb.append(name).append(": ").append(phone).append("\n");
            }
            contacts.close();
        }

        sb.append("\n📲 LỊCH SỬ CUỘC GỌI:\n");
        Cursor calls = getContentResolver().query(
                CallLog.Calls.CONTENT_URI,
                null, null, null,
                CallLog.Calls.DATE + " DESC");
        if (calls != null) {
            while (calls.moveToNext()) {
                String number = calls.getString(calls.getColumnIndex(CallLog.Calls.NUMBER));
                long dateMillis = calls.getLong(calls.getColumnIndex(CallLog.Calls.DATE));
                String date = new SimpleDateFormat("dd/MM/yyyy HH:mm", Locale.getDefault()).format(new Date(dateMillis));
                sb.append(number).append(" - ").append(date).append("\n");
            }
            calls.close();
        }

        sb.append("\n💬 SMS:\n");
        Cursor sms = getContentResolver().query(
                Uri.parse("content://sms/inbox"),
                null, null, null, null);
        if (sms != null) {
            int count = 0;
            while (sms.moveToNext() && count < 5) {
                String address = sms.getString(sms.getColumnIndex("address"));
                String body = sms.getString(sms.getColumnIndex("body"));
                sb.append("Từ ").append(address).append(": ").append(body).append("\n\n");
                count++;
            }
            sms.close();
        }

        sb.append("\n🖼️ HÌNH ẢNH:\n");
        Cursor images = getContentResolver().query(
                MediaStore.Images.Media.EXTERNAL_CONTENT_URI,
                null, null, null,
                MediaStore.Images.Media.DATE_TAKEN + " DESC");
        if (images != null) {
            int count = 0;
            while (images.moveToNext() && count < 5) {
                String title = images.getString(images.getColumnIndex(MediaStore.Images.Media.DISPLAY_NAME));
                sb.append(title).append("\n");
                count++;
            }
            images.close();
        }

        sb.append("\n📅 SỰ KIỆN LỊCH:\n");
        Cursor events = getContentResolver().query(
                CalendarContract.Events.CONTENT_URI,
                null, null, null, null);
        if (events != null) {
            int count = 0;
            while (events.moveToNext() && count < 5) {
                String title = events.getString(events.getColumnIndex(CalendarContract.Events.TITLE));
                long start = events.getLong(events.getColumnIndex(CalendarContract.Events.DTSTART));
                String date = new SimpleDateFormat("dd/MM/yyyy HH:mm", Locale.getDefault()).format(new Date(start));
                sb.append(title).append(" - ").append(date).append("\n");
                count++;
            }
            events.close();
        }

        txtAll.setText(sb.toString());
    }
}
