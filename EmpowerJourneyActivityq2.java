package com.example.nosmoking;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import androidx.appcompat.app.AppCompatActivity;

public class EmpowerJourneyActivityq2 extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_empower_journeyq2);

        // Back Button
        ImageView backButton = findViewById(R.id.backButton);
        backButton.setOnClickListener(v -> finish());

        // Play Video Button
        ImageView playVideo = findViewById(R.id.playVideo);
        playVideo.setOnClickListener(v -> {
            // TODO: Add video playback logic
        });

        // Next Button
        Button nextButton = findViewById(R.id.nextButton);
        nextButton.setOnClickListener(v -> {
            // TODO: Define next activity navigation
        });

        // Bottom Navigation Buttons
        ImageView navHome = findViewById(R.id.navHome);
        ImageView navTasks = findViewById(R.id.navTasks);
        ImageView navProfile = findViewById(R.id.navProfile);
        ImageView navMore = findViewById(R.id.navMore);

        navHome.setOnClickListener(v -> {
            Intent intent = new Intent(EmpowerJourneyActivityq2.this, PatientHomeActivity.class);
            startActivity(intent);
        });

        navMore.setOnClickListener(v -> {
            Intent intent = new Intent(EmpowerJourneyActivityq2.this, VideosActivity.class);
            startActivity(intent);
        });

        navProfile.setOnClickListener(v -> {
            Intent intent = new Intent(EmpowerJourneyActivityq2.this, PatientProfileActivity.class);
            startActivity(intent);
        });
        navTasks.setOnClickListener(v -> {
            Intent intent = new Intent(EmpowerJourneyActivityq2.this, CravingActivity.class);
            startActivity(intent);
        });
    }
}
