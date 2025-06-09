package com.example.myapplication.chapter7.two;

import android.net.Uri;
import android.os.Bundle;
import android.widget.MediaController;
import android.widget.Toast;
import android.widget.VideoView;

import androidx.appcompat.app.AppCompatActivity;

import com.example.myapplication.R;

public class Video extends AppCompatActivity {

    private VideoView videoView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_video);

        videoView = findViewById(R.id.videoView);

        // URL video sample (free to use)
        String videoUrl = "https://www.learningcontainer.com/wp-content/uploads/2020/05/sample-mp4-file.mp4";

        Uri videoUri = Uri.parse(videoUrl);

        // Set MediaController để có các nút Play/Pause
        MediaController mediaController = new MediaController(this);
        mediaController.setAnchorView(videoView);

        videoView.setMediaController(mediaController);
        videoView.setVideoURI(videoUri);

        // Khi video đã chuẩn bị xong
        videoView.setOnPreparedListener(mp -> {
            Toast.makeText(this, "Video đã sẵn sàng để phát", Toast.LENGTH_SHORT).show();
            videoView.start();
        });

        videoView.setOnErrorListener((mp, what, extra) -> {
            Toast.makeText(this, "Không thể phát video", Toast.LENGTH_SHORT).show();
            return true;
        });
    }
}
