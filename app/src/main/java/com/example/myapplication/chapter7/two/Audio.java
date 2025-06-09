package com.example.myapplication.chapter7.two;

import android.media.MediaPlayer;
import android.os.Bundle;
import android.widget.Button;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import com.example.myapplication.R;

public class Audio extends AppCompatActivity {
    private MediaPlayer mediaPlayer;
    private Button btnPlay, btnPause, btnStop;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_audio);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });
            btnPlay = findViewById(R.id.btnPlay);
            btnPause = findViewById(R.id.btnPause);
            btnStop = findViewById(R.id.btnStop);

            // Ví dụ 1: Phát từ URL
            String audioUrl = "https://github.com/rafaelreis-hotmart/Audio-Sample-files/raw/master/sample.mp3";
            mediaPlayer = new MediaPlayer();
            try {
                mediaPlayer.setDataSource(audioUrl);
                mediaPlayer.prepareAsync(); // prepareAsync để tránh blocking main thread
                mediaPlayer.setOnPreparedListener(mp -> {
                    Toast.makeText(this, "Đã sẵn sàng để phát nhạc!", Toast.LENGTH_SHORT).show();
                });
            } catch (Exception e) {
                e.printStackTrace();
            }

            btnPlay.setOnClickListener(v -> {
                if (!mediaPlayer.isPlaying()) {
                    mediaPlayer.start();
                    Toast.makeText(this, "Đã phát nhạc!", Toast.LENGTH_SHORT).show();
                }
            });

            btnPause.setOnClickListener(v -> {
                if (mediaPlayer.isPlaying()) {
                    mediaPlayer.pause();
                    Toast.makeText(this, "Đã tạm dừng phát nhạc!", Toast.LENGTH_SHORT).show();
                }
            });

            btnStop.setOnClickListener(v -> {
                if (mediaPlayer.isPlaying()) {
                    mediaPlayer.stop();
                    Toast.makeText(this, "Đã dừng phát nhạc!", Toast.LENGTH_SHORT).show();
                    try {
                        mediaPlayer.prepare(); // prepare lại sau khi stop
                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                }
            });
        }

        @Override
        protected void onDestroy() {
            super.onDestroy();
            if (mediaPlayer != null) {
                mediaPlayer.release();
                mediaPlayer = null;
            }
        }
    }


