package com.example.myapplication.login;

import android.content.Intent;
import android.graphics.Paint;
import android.os.Bundle;
import android.widget.TextView;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.AppCompatButton;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import com.example.myapplication.R;

public class LoginAcitivity extends AppCompatActivity {
    private AppCompatButton btn_SignIn;
    private TextView tv_SignUp;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_login_acitivity);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });
        btn_SignIn = findViewById(R.id.btn_SignIn);
        tv_SignUp = findViewById(R.id.tv_SignUp);
        btn_SignIn.setOnClickListener(view -> {
            Intent intent = new Intent(LoginAcitivity.this, SignInActivity.class);
            startActivity(intent);
        });
        tv_SignUp.setPaintFlags(tv_SignUp.getPaintFlags() | Paint.UNDERLINE_TEXT_FLAG);
        tv_SignUp.setOnClickListener(view -> {
            Intent intent = new Intent(LoginAcitivity.this,SignUpActivity.class);
            startActivity(intent);
        });


    }
}