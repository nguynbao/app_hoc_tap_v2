package com.example.myapplication.login;

import android.content.Intent;
import android.os.Bundle;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.AppCompatButton;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import com.example.myapplication.DB.UserDatabaseHelper;
import com.example.myapplication.R;

public class SignUpActivity extends AppCompatActivity {
    private EditText edtEmail, edtPassword;
    private TextView tvForgotPass;
    private TextView tvSignIn;
    private AppCompatButton btnSignUp;
    private UserDatabaseHelper dbHelper;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_sign_up);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });
        edtEmail = findViewById(R.id.edt_email);
        edtPassword = findViewById(R.id.edt_password);
        btnSignUp = findViewById(R.id.btn_SignUp);
        tvSignIn = findViewById(R.id.tv_SignIn);
        dbHelper = new UserDatabaseHelper(this);

        tvSignIn.setOnClickListener(v -> {
            Intent intent = new Intent(SignUpActivity.this, SignInActivity.class);
            startActivity(intent);
        });
        btnSignUp.setOnClickListener(v -> {
            register();
        });
    }
    private void register(){
        String email = edtEmail.getText().toString();
        String password = edtPassword.getText().toString();
        if (email.isEmpty()|| password.isEmpty()) {
            Toast.makeText(SignUpActivity.this, "Vui lòng nhập đầy đủ thông tin", Toast.LENGTH_SHORT).show();
        }
        else {
            boolean success = dbHelper.registerUser(email, password);
            if (success) {
                Toast.makeText(SignUpActivity.this, "Đăng ký thành công", Toast.LENGTH_SHORT).show();
                // có thể chuyển sang màn hình đăng nhập:
                Intent intent = new Intent(SignUpActivity.this, SignInActivity.class);
                startActivity(intent);
                finish();
            } else {
                Toast.makeText(SignUpActivity.this, "Người dùng đã tồn tại", Toast.LENGTH_SHORT).show();
            }

        }
    }
}