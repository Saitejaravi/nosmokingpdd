package com.example.nosmoking;

import android.content.Intent;
import android.os.Bundle;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.ImageView;
import android.widget.PopupMenu;
import android.widget.TextView;
import android.widget.Toast;
import androidx.appcompat.app.AppCompatActivity;

public class PatientProfileActivity extends AppCompatActivity {

    private TextView txtName, txtAge, txtGender, txtPhone, txtEmail;
    private ImageView btnBack, btnMenu, navHome, navTasks, navmore, navProfile;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_patient_profile);

        // Initialize Views
        txtName = findViewById(R.id.txtName);
        txtAge = findViewById(R.id.txtAge);
        txtGender = findViewById(R.id.txtGender);
        txtPhone = findViewById(R.id.txtPhone);
        txtEmail = findViewById(R.id.txtEmail);
        btnBack = findViewById(R.id.btnBack);
        btnMenu = findViewById(R.id.menuButton);
        navHome = findViewById(R.id.navHome);
        navTasks = findViewById(R.id.navTasks);
        navmore = findViewById(R.id.navMore);
        navProfile = findViewById(R.id.navProfile);

        // Set Data (Can be dynamically set from Intent or Database)
        txtName.setText("patient_1");
        txtAge.setText("26");
        txtGender.setText("Male");
        txtPhone.setText("702345690");
        txtEmail.setText("patient@sav.com");

        // Navigation Click Listeners
        btnBack.setOnClickListener(v -> finish());

        navHome.setOnClickListener(v -> startActivity(new Intent(PatientProfileActivity.this, PatientHomeActivity.class)));
        navTasks.setOnClickListener(v -> startActivity(new Intent(PatientProfileActivity.this, TasksActivity.class)));
        navmore.setOnClickListener(v -> startActivity(new Intent(PatientProfileActivity.this, VideosActivity.class)));
        navProfile.setOnClickListener(v -> startActivity(new Intent(PatientProfileActivity.this, PatientProfileActivity.class)));

        // Handle Menu Button Click
        btnMenu.setOnClickListener(this::showPopupMenu);
    }

    private void showPopupMenu(View view) {
        PopupMenu popup = new PopupMenu(this, view);
        MenuInflater inflater = popup.getMenuInflater();
        inflater.inflate(R.menu.menu_profile, popup.getMenu());

        popup.setOnMenuItemClickListener(item -> {
            if (item.getItemId() == R.id.action_edit_profile) {
                Toast.makeText(this, "Edit Profile Clicked", Toast.LENGTH_SHORT).show();
                // Navigate to Edit Profile Activity (Create if needed)
                startActivity(new Intent(PatientProfileActivity.this, PatientProfileActivity.class));
                return true;
            } else if (item.getItemId() == R.id.action_sign_out) {
                Toast.makeText(this, "Signing Out...", Toast.LENGTH_SHORT).show();
                // Implement sign-out logic (e.g., clear session, navigate to login)
                startActivity(new Intent(PatientProfileActivity.this, ProfileSelectionActivity.class));
                finish(); // Close current activity
                return true;
            }
            return false;
        });

        popup.show();
    }
}
