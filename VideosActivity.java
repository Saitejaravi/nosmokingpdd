package com.example.nosmoking;

import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.MediaController;
import android.widget.VideoView;
import com.example.nosmoking.R;
import androidx.appcompat.app.AppCompatActivity;

public class VideosActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_videos);

        // Back Button
        ImageButton btnBack = findViewById(R.id.btnBack);
        btnBack.setOnClickListener(v -> finish()); // Closes the activity

        // Video 1
        VideoView videoView1 = findViewById(R.id.videoView1);
        ImageView playVideo1 = findViewById(R.id.playVideo1);
        setupVideoPlayer(videoView1, playVideo1, "https://example.com/video1.mp4");

        // Video 2
        VideoView videoView2 = findViewById(R.id.videoView2);
        ImageView playVideo2 = findViewById(R.id.playVideo2);
        setupVideoPlayer(videoView2, playVideo2, "https://example.com/video2.mp4");
    }

    private void setupVideoPlayer(VideoView videoView, ImageView playButton, String videoUrl) {
        videoView.setVideoURI(Uri.parse(videoUrl));

        // Set Media Controls
        MediaController mediaController = new MediaController(this);
        videoView.setMediaController(mediaController);
        mediaController.setAnchorView(videoView);

        // Start video on play button click
        playButton.setOnClickListener(v -> {
            playButton.setVisibility(View.GONE);
            videoView.setVisibility(View.VISIBLE);
            videoView.start();
        });

        // Show play button again if video finishes
        videoView.setOnCompletionListener(mp -> {
            playButton.setVisibility(View.VISIBLE);
            videoView.setVisibility(View.GONE);
        });
    }
}
