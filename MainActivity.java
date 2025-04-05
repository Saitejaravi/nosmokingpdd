package com.example.nosmoking;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import androidx.appcompat.app.AppCompatActivity;

public class MainActivity extends AppCompatActivity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        // Get shared preferences
        SharedPreferences sharedPreferences = getSharedPreferences("NoSmokingApp", MODE_PRIVATE);
        boolean isLoggedIn = sharedPreferences.getBoolean("isLoggedIn", false);

        // 🚀 If already logged in, go to PatientHomeActivity
        if (isLoggedIn) {
            startActivity(new Intent(MainActivity.this, PatientHomeActivity.class));
            finish(); // Prevent going back to MainActivity
            return;
        }

        // Otherwise, show the login/profile selection screen
        setContentView(R.layout.activity_main);
        Button getStartedButton = findViewById(R.id.getStartedButton);

        getStartedButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity.this, ProfileSelectionActivity.class);
                startActivity(intent);
            }
        });
    }
}
