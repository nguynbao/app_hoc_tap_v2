package com.example.myapplication.chapter5.three;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import com.example.myapplication.R;

public class SQLiteActivity extends AppCompatActivity {

    private TextView txt_sqlite;
    private EditText name;
    private Button btn_save, btn_load;
    private SQLiteDatabase db;

    // SQLite Helper class
    class DBHelper extends SQLiteOpenHelper {
        DBHelper(Context ctx) {
            super(ctx, "MyDB", null, 1);
        }

        @Override
        public void onCreate(SQLiteDatabase db) {
            db.execSQL("CREATE TABLE IF NOT EXISTS user(id INTEGER PRIMARY KEY AUTOINCREMENT, name TEXT)");
        }

        @Override
        public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
            db.execSQL("DROP TABLE IF EXISTS user");
            onCreate(db);
        }
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_sqlite);

        // Áp dụng padding cho layout tránh tràn mép màn hình
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });

        // Ánh xạ các thành phần giao diện
        txt_sqlite = findViewById(R.id.txt_sqlite);
        name = findViewById(R.id.name);
        btn_save = findViewById(R.id.btn_save);
        btn_load = findViewById(R.id.btn_load);

        // Tạo DB
        DBHelper dbHelper = new DBHelper(this);
        db = dbHelper.getWritableDatabase();

        // Xử lý nút LƯU
        btn_save.setOnClickListener(v -> {
            String n = name.getText().toString().trim();
            if (!n.isEmpty()) {
                ContentValues values = new ContentValues();
                values.put("name", n);
                long result = db.insert("user", null, values);
                if (result != -1) {
                    Toast.makeText(this, "Lưu thành công", Toast.LENGTH_SHORT).show();
                    name.setText(""); // Clear EditText
                } else {
                    Toast.makeText(this, "Lưu thất bại", Toast.LENGTH_SHORT).show();
                }
            } else {
                Toast.makeText(this, "Vui lòng nhập tên", Toast.LENGTH_SHORT).show();
            }
        });

        // Xử lý nút TẢI DỮ LIỆU
        btn_load.setOnClickListener(v -> {
            Cursor c = db.query("user", null, null, null, null, null, null);
            StringBuilder s = new StringBuilder();
            if (c.moveToFirst()) {
                do {
                    int id = c.getInt(0);
                    String username = c.getString(1);
                    s.append("ID: ").append(id).append(" - Tên: ").append(username).append("\n");
                } while (c.moveToNext());
                txt_sqlite.setText(s.toString());
            } else {
                txt_sqlite.setText("Không có dữ liệu nào.");
            }
            c.close(); // Đóng cursor sau khi dùng
        });
    }
}
