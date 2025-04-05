package com.example.nosmoking;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

public class CravingActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_craving);

        // Back Button
        ImageView backButton = findViewById(R.id.backButton);
        backButton.setOnClickListener(view -> finish());

        // Bottom Navigation Buttons
        ImageView navHome = findViewById(R.id.navHome);
        ImageView navTasks = findViewById(R.id.navTasks);
        ImageView navProfile = findViewById(R.id.navProfile);
        ImageView navMore = findViewById(R.id.navMore);

        navHome.setOnClickListener(view -> startActivity(new Intent(this, PatientHomeActivity.class)));
        navTasks.setOnClickListener(view -> startActivity(new Intent(this, TasksActivity.class)));
        navProfile.setOnClickListener(view -> startActivity(new Intent(this, PatientProfileActivity.class)));
        navMore.setOnClickListener(view -> startActivity(new Intent(this, VideosActivity.class)));


        // Craving Reason Buttons
        Button btnFrustration = findViewById(R.id.btnFrustration);
        Button btnStress = findViewById(R.id.btnStress);
        Button btnDepression = findViewById(R.id.btnDepression);
        Button btnAnxiety = findViewById(R.id.btnAnxiety);

        View.OnClickListener cravingClickListener = view -> {
            Button clickedButton = (Button) view;
            Toast.makeText(this, "Selected: " + clickedButton.getText(), Toast.LENGTH_SHORT).show();
        };

        btnFrustration.setOnClickListener(cravingClickListener);
        btnStress.setOnClickListener(cravingClickListener);
        btnDepression.setOnClickListener(cravingClickListener);
        btnAnxiety.setOnClickListener(cravingClickListener);

        // Craving Scale (1-10)
        RadioGroup cravingScaleGroup = findViewById(R.id.radioGroupCravingScale);
        RadioButton radioTwo = findViewById(R.id.radioTwo);

        // Navigate to EmpowerJourneyActivity when "2" is selected
        cravingScaleGroup.setOnCheckedChangeListener((group, checkedId) -> {
            if (checkedId == R.id.radioTwo) {
                Intent intent = new Intent(CravingActivity.this, EmpowerJourneyActivityq2.class);
                startActivity(intent);
            }

        });
    }
}
