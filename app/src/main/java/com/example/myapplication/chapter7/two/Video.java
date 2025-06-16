package com.example.myapplication.chapter7.two;

import android.content.pm.ActivityInfo;
import android.os.Bundle;
import android.view.ViewGroup;
import android.view.WindowManager;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.appcompat.widget.AppCompatButton;

import com.example.myapplication.R;
import com.pierfrancescosoffritti.androidyoutubeplayer.core.player.YouTubePlayer;
import com.pierfrancescosoffritti.androidyoutubeplayer.core.player.listeners.AbstractYouTubePlayerListener;
import com.pierfrancescosoffritti.androidyoutubeplayer.core.player.views.YouTubePlayerView;

public class Video extends AppCompatActivity {

    private YouTubePlayerView youTubePlayerView;
    private YouTubePlayer youTubePlayer;
    private boolean isPaused = false;
    private boolean isFullScreen = false;
    private final String videoId = "LPx7ERqFYM0"; // YouTube video ID
    private ConstraintLayout.LayoutParams originalParams;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_video);

        youTubePlayerView = findViewById(R.id.youtube_player_view);
        getLifecycle().addObserver(youTubePlayerView);

        AppCompatButton start = findViewById(R.id.start);
        AppCompatButton pause = findViewById(R.id.pause);
        AppCompatButton full = findViewById(R.id.full);

        originalParams = (ConstraintLayout.LayoutParams) youTubePlayerView.getLayoutParams();

        // Load player
        youTubePlayerView.addYouTubePlayerListener(new AbstractYouTubePlayerListener() {
            @Override
            public void onReady(@NonNull YouTubePlayer player) {
                youTubePlayer = player;
                youTubePlayer.cueVideo(videoId, 0);

                start.setOnClickListener(v -> {
                    if (isPaused) {
                        youTubePlayer.play();
                    } else {
                        youTubePlayer.loadVideo(videoId, 0);
                    }
                    isPaused = false;
                });

                pause.setOnClickListener(v -> {
                    youTubePlayer.pause();
                    isPaused = true;
                });

                full.setOnClickListener(v -> toggleFullscreen());
            }
        });
    }

    private void toggleFullscreen() {
        if (!isFullScreen) {
            setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_LANDSCAPE);
        } else {
            setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_PORTRAIT);
        }
        isFullScreen = !isFullScreen;
    }

}
