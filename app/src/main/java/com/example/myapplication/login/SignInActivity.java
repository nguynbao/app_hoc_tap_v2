package com.example.myapplication.login;

import com.example.myapplication.home.homeActivity;

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

public class SignInActivity extends AppCompatActivity {
    private EditText edtEmail, edtPassword;
    private TextView tvForgotPass;
    private TextView tvSignUp;
    private AppCompatButton btnSignIn;
    private UserDatabaseHelper dbHelper;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_sign_in);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });
        edtEmail = findViewById(R.id.edt_email);
        edtPassword = findViewById(R.id.edt_password);
        tvForgotPass = findViewById(R.id.tv_fgPass);
        btnSignIn = findViewById(R.id.btn_SignIn);
        tvSignUp = findViewById(R.id.tv_SignUp);
        dbHelper = new UserDatabaseHelper(this);
        tvSignUp.setOnClickListener(v -> {
            Intent intent = new Intent(SignInActivity.this, SignUpActivity.class);
            startActivity(intent);
        });
        btnSignIn.setOnClickListener(v -> {
            login();
        });

    }
    private void login(){
        String email = edtEmail.getText().toString();
        String password = edtPassword.getText().toString();
        boolean success = dbHelper.loginUser(email, password);
        if (email.isEmpty()|| password.isEmpty()) {
            Toast.makeText(SignInActivity.this, "Vui lòng nhập đầy đủ thông tin", Toast.LENGTH_SHORT).show();
        }
        else {
            if (success){
                Toast.makeText(this, "Đăng nhập thành công", Toast.LENGTH_SHORT).show();
                // chuyển sang màn hình home:
                Intent intent = new Intent(SignInActivity.this, homeActivity.class);
                startActivity(intent);
                finish();

            }else {
                Toast.makeText(this, "Đăng nhập thất bại", Toast.LENGTH_SHORT).show();
            }
        }
    }

}