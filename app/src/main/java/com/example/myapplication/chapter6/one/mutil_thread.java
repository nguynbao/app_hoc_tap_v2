package com.example.myapplication.chapter6.one;

import android.os.AsyncTask;
import android.os.Bundle;
import android.os.Handler;
import android.os.Looper;
import android.os.Message;
import android.util.Log;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import com.example.myapplication.R;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class mutil_thread extends AppCompatActivity {

    private Button btnThreadHandlerStart, btnThreadHandlerOtherWork;
    private Button btnAsyncTaskStart, btnAsyncTaskOtherWork;
    private Button btnExecutorStart, btnExecutorOtherWork;
    private Button btnRunnableStart, btnRunnableOtherWork;
    private Button btnHandlerLooperStart, btnHandlerLooperOtherWork;

    private TextView statusText, statusText2, statusText3, statusText4, statusText5;

    private Handler handler;
    private ExecutorService executorService;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_mutil_thread);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });

        // Ánh xạ view
        statusText = findViewById(R.id.statusText);
        statusText2 = findViewById(R.id.statusText2);
        statusText3 = findViewById(R.id.statusText3);
        statusText4 = findViewById(R.id.statusText4);
        statusText5 = findViewById(R.id.statusText5);

        btnThreadHandlerStart = findViewById(R.id.btnThreadHandlerStart);
        btnThreadHandlerOtherWork = findViewById(R.id.btnThreadHandlerOtherWork);

        btnAsyncTaskStart = findViewById(R.id.btnAsyncTaskStart);
        btnAsyncTaskOtherWork = findViewById(R.id.btnAsyncTaskOtherWork);

        btnExecutorStart = findViewById(R.id.btnExecutorStart);
        btnExecutorOtherWork = findViewById(R.id.btnExecutorOtherWork);

        btnRunnableStart = findViewById(R.id.btnRunnableStart);
        btnRunnableOtherWork = findViewById(R.id.btnRunnableOtherWork);

        btnHandlerLooperStart = findViewById(R.id.btnHandlerLooperStart);
        btnHandlerLooperOtherWork = findViewById(R.id.btnHandlerLooperOtherWork);

        // Handler nhận message trên MainThread
        handler = new Handler(Looper.getMainLooper()) {
            @Override
            public void handleMessage(Message msg) {
                if (msg.what == 1) {
                    statusText.setText("Hoàn thành Thread + Handler!");
                }
            }
        };

        // ExecutorService
        executorService = Executors.newFixedThreadPool(2);

        // Thread + Handler
        btnThreadHandlerStart.setOnClickListener(v -> {
            statusText.setText("Bắt đầu Thread + Handler...");
            new Thread(() -> {
                try {
                    Thread.sleep(3000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                handler.sendEmptyMessage(1);
            }).start();
        });
        btnThreadHandlerOtherWork.setOnClickListener(v -> Toast.makeText(this, "Đang làm việc khác khi Thread + Handler chạy", Toast.LENGTH_SHORT).show());

        // AsyncTask (deprecated)
        btnAsyncTaskStart.setOnClickListener(v -> {
            statusText2.setText("Bắt đầu AsyncTask...");
            new MyAsyncTask().execute();
        });
        btnAsyncTaskOtherWork.setOnClickListener(v -> Toast.makeText(this, "Làm việc khác khi AsyncTask chạy", Toast.LENGTH_SHORT).show());

        // ExecutorService
        btnExecutorStart.setOnClickListener(v -> {
            statusText3.setText("Bắt đầu ExecutorService...");
            executorService.execute(() -> {
                try {
                    Thread.sleep(4000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                runOnUiThread(() -> statusText3.setText("Hoàn thành ExecutorService!"));
            });
        });
        btnExecutorOtherWork.setOnClickListener(v -> Toast.makeText(this, "Làm việc khác khi ExecutorService chạy", Toast.LENGTH_SHORT).show());

        // Runnable interface
        btnRunnableStart.setOnClickListener(v -> {
            statusText4.setText("Bắt đầu Runnable...");
            Runnable runnable = () -> {
                try {
                    Thread.sleep(3500);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                runOnUiThread(() -> statusText4.setText("Hoàn thành Runnable!"));
            };
            new Thread(runnable).start();
        });
        btnRunnableOtherWork.setOnClickListener(v -> Toast.makeText(this, "Làm việc khác khi Runnable chạy", Toast.LENGTH_SHORT).show());

        // Handler + Looper (background thread gửi cập nhật UI)
        btnHandlerLooperStart.setOnClickListener(v -> {
            statusText5.setText("Bắt đầu Handler + Looper...");
            Thread bgThread = new Thread(() -> {
                try {
                    Thread.sleep(3000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                handler.post(() -> statusText5.setText("Hoàn thành Handler + Looper!"));
            });
            bgThread.start();
        });
        btnHandlerLooperOtherWork.setOnClickListener(v -> Toast.makeText(this, "Làm việc khác khi Handler + Looper chạy", Toast.LENGTH_SHORT).show());
    }

    // AsyncTask (deprecated)
    private class MyAsyncTask extends AsyncTask<Void, Void, String> {
        @Override
        protected String doInBackground(Void... voids) {
            try {
                Thread.sleep(3500);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            return "Hoàn thành AsyncTask!";
        }

        @Override
        protected void onPostExecute(String result) {
            statusText2.setText(result);
        }
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        if (executorService != null && !executorService.isShutdown()) {
            executorService.shutdown();
        }
    }
}
