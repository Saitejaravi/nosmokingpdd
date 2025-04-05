package com.example.nosmoking;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import androidx.appcompat.app.AppCompatActivity;

public class ProfileSelectionActivity extends AppCompatActivity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_profile_selection);

        Button doctorButton = findViewById(R.id.doctorButton);
        Button patientButton = findViewById(R.id.patientButton);

        doctorButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(ProfileSelectionActivity.this, DoctorLoginActivity.class);
                startActivity(intent);
            }
        });

        patientButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(ProfileSelectionActivity.this, PatientLoginActivity.class);
                startActivity(intent);
            }
        });
    }
}
