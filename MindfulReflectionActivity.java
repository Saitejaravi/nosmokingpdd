package com.example.nosmoking;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import androidx.appcompat.app.AppCompatActivity;

public class MindfulReflectionActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_mindful_reflection);

        Button btnYes = findViewById(R.id.btnYes);
        Button btnNo = findViewById(R.id.btnNo);

        btnYes.setOnClickListener(v -> {
            // Handle Yes Button Click
            finish();
        });

        btnNo.setOnClickListener(v -> {
            // Handle No Button Click
            finish();
        });
    }
}
