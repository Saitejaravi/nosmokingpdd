package com.example.nosmoking;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

public class MindfulBiweeklyReflectionActivity extends AppCompatActivity {

    private CheckBox cbQ1_1, cbQ1_2, cbQ1_3;
    private CheckBox cbQ2_1, cbQ2_2;
    private CheckBox cbQ3_1, cbQ3_2;
    private CheckBox cbQ4_1, cbQ4_2, cbQ4_3, cbQ4_4;
    private Button submitButton;
    private ImageView btnBack;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_mindful_biweekly_reflection);

        // Initialize UI Elements
        btnBack = findViewById(R.id.btnBack);
        submitButton = findViewById(R.id.submitButton);

        cbQ1_1 = findViewById(R.id.cbQ1_1);
        cbQ1_2 = findViewById(R.id.cbQ1_2);
        cbQ1_3 = findViewById(R.id.cbQ1_3);

        cbQ2_1 = findViewById(R.id.cbQ2_1);
        cbQ2_2 = findViewById(R.id.cbQ2_2);

        cbQ3_1 = findViewById(R.id.cbQ3_1);
        cbQ3_2 = findViewById(R.id.cbQ3_2);

        cbQ4_1 = findViewById(R.id.cbQ4_1);
        cbQ4_2 = findViewById(R.id.cbQ4_2);
        cbQ4_3 = findViewById(R.id.cbQ4_3);
        cbQ4_4 = findViewById(R.id.cbQ4_4);

        // Handle Back Button
        btnBack.setOnClickListener(v -> finish());

        // Handle Submit Button
        submitButton.setOnClickListener(v -> calculateScore());
    }

    private void calculateScore() {
        int score = 0;

        if (cbQ1_1.isChecked()) score += 5;
        if (cbQ1_2.isChecked()) score += 3;
        if (cbQ1_3.isChecked()) score += 1;
        if (cbQ2_1.isChecked()) score += 4;
        if (cbQ3_1.isChecked()) score += 5;
        if (cbQ4_4.isChecked()) score += 5;

        String stage = (score >= 15) ? "SEVERE" : (score >= 10) ? "MODERATE" : "MILD";
        showResultDialog(score, stage);
    }

    private void showResultDialog(int score, String stage) {
        new AlertDialog.Builder(this)
                .setTitle("Your Score")
                .setMessage("Your score is " + score + ". You fall in " + stage + " stage.")
                .setPositiveButton("Continue", (dialog, which) -> dialog.dismiss())
                .show();
    }
}
