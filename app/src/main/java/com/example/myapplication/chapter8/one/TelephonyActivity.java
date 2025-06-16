package com.example.myapplication.chapter8.one;

import android.Manifest;
import android.content.pm.PackageManager;
import android.os.Bundle;
import android.telephony.TelephonyManager;
import android.widget.TextView;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import com.example.myapplication.R;

public class TelephonyActivity extends AppCompatActivity {
    private static final int REQUEST_CODE_PHONE_STATE = 1;
    private TextView tvInfo;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_telephony);

        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });

        tvInfo = findViewById(R.id.tvInfo);

        // Kiểm tra và yêu cầu các quyền cần thiết
        if (hasAllPhonePermissions()) {
            showTelephonyInfo();
        } else {
            ActivityCompat.requestPermissions(this,
                    new String[]{
                            Manifest.permission.READ_PHONE_STATE,
                            Manifest.permission.READ_PHONE_NUMBERS,
                            Manifest.permission.READ_SMS
                    },
                    REQUEST_CODE_PHONE_STATE);
        }
    }

    private boolean hasAllPhonePermissions() {
        return ContextCompat.checkSelfPermission(this, Manifest.permission.READ_PHONE_STATE) == PackageManager.PERMISSION_GRANTED &&
                ContextCompat.checkSelfPermission(this, Manifest.permission.READ_PHONE_NUMBERS) == PackageManager.PERMISSION_GRANTED &&
                ContextCompat.checkSelfPermission(this, Manifest.permission.READ_SMS) == PackageManager.PERMISSION_GRANTED;
    }

    private void showTelephonyInfo() {
        TelephonyManager telephonyManager = (TelephonyManager) getSystemService(TELEPHONY_SERVICE);

        if (!hasAllPhonePermissions()) {
            Toast.makeText(this, "Thiếu quyền truy cập thông tin điện thoại", Toast.LENGTH_SHORT).show();
            return;
        }

        int simState = telephonyManager.getSimState();
        String simStateString;

        switch (simState) {
            case TelephonyManager.SIM_STATE_READY:
                simStateString = "Hoạt động";
                break;
            case TelephonyManager.SIM_STATE_ABSENT:
                simStateString = "Không có SIM";
                break;
            case TelephonyManager.SIM_STATE_PIN_REQUIRED:
                simStateString = "Yêu cầu mã PIN";
                break;
            case TelephonyManager.SIM_STATE_PUK_REQUIRED:
                simStateString = "Yêu cầu mã PUK";
                break;
            case TelephonyManager.SIM_STATE_NETWORK_LOCKED:
                simStateString = "Bị khóa mạng";
                break;
            default:
                simStateString = "Không xác định";
        }

        // Chỉ lấy simSerialNumber nếu API < 29 (Android 10)
        String simSerialNumber = "Không khả dụng (Android 10+)";
        if (android.os.Build.VERSION.SDK_INT < android.os.Build.VERSION_CODES.Q) {
            simSerialNumber = telephonyManager.getSimSerialNumber();
        }

        String simOperator = telephonyManager.getSimOperatorName();
        String lineNumber = telephonyManager.getLine1Number(); // Có thể null
        int networkType = telephonyManager.getNetworkType();

        String networkTypeString;
        switch (networkType) {
            case TelephonyManager.NETWORK_TYPE_LTE:
                networkTypeString = "4G (LTE)";
                break;
            case TelephonyManager.NETWORK_TYPE_HSPAP:
                networkTypeString = "3G (HSPAP)";
                break;
            case TelephonyManager.NETWORK_TYPE_EDGE:
                networkTypeString = "2G (EDGE)";
                break;
            default:
                networkTypeString = "Khác / Không xác định";
        }

        String info = "Tên nhà mạng: " + (simOperator != null ? simOperator : "Không xác định") + "\n"
                + "Số điện thoại: " + (lineNumber != null ? lineNumber : "Không xác định") + "\n"
                + "Loại mạng: " + networkTypeString + "\n"
                + "Trạng thái SIM: " + simStateString + "\n"
                + "Số serial SIM: " + (simSerialNumber != null ? simSerialNumber : "Không xác định");

        tvInfo.setText(info);
    }


    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
        if (requestCode == REQUEST_CODE_PHONE_STATE) {
            boolean allGranted = true;
            for (int result : grantResults) {
                if (result != PackageManager.PERMISSION_GRANTED) {
                    allGranted = false;
                    break;
                }
            }

            if (allGranted) {
                showTelephonyInfo();
            } else {
                Toast.makeText(this, "Bạn cần cấp đủ quyền để hiển thị thông tin", Toast.LENGTH_LONG).show();
            }
        }
    }
}
