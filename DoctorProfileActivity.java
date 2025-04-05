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

public class DoctorProfileActivity extends AppCompatActivity {

    private TextView doctorName, doctorAge, doctorGender, doctorPhone, doctorEmail;
    private ImageView btnBack, btnMenu;
    private com.google.android.material.bottomnavigation.BottomNavigationView bottomNavigation;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_doctorprofile);

        // Initialize Views
        doctorName = findViewById(R.id.doctorName);
        doctorAge = findViewById(R.id.doctorAge);
        doctorGender = findViewById(R.id.doctorGender);
        doctorPhone = findViewById(R.id.doctorPhone);
        doctorEmail = findViewById(R.id.doctorEmail);
        btnBack = findViewById(R.id.btnBack);
        btnMenu = findViewById(R.id.menuButton);
        bottomNavigation = findViewById(R.id.bottomNavigation);

        // Back Button Click Listener
        btnBack.setOnClickListener(v -> finish());

        // Handle Menu Button Click
        btnMenu.setOnClickListener(this::showPopupMenu);

        // Handle Bottom Navigation Clicks
        bottomNavigation.setOnItemSelectedListener(item -> {
            if (item.getItemId() == R.id.nav_home) {
                startActivity(new Intent(DoctorProfileActivity.this, DoctorHomeActivity.class));
                return true;
            } else if (item.getItemId() == R.id.nav_profile) {
                startActivity(new Intent(DoctorProfileActivity.this, DoctorProfileActivity.class));
                return true;
            }
            return false;
        });
    }

    private void showPopupMenu(View view) {
        PopupMenu popup = new PopupMenu(this, view);
        MenuInflater inflater = popup.getMenuInflater();
        inflater.inflate(R.menu.menu_profile, popup.getMenu());

        popup.setOnMenuItemClickListener(item -> {
            if (item.getItemId() == R.id.action_edit_profile) {
                startActivity(new Intent(DoctorProfileActivity.this, EditDoctorProfileActivity.class));
                return true;
            } else if (item.getItemId() == R.id.action_sign_out) {
                Toast.makeText(this, "Signing Out...", Toast.LENGTH_SHORT).show();
                startActivity(new Intent(DoctorProfileActivity.this, ProfileSelectionActivity.class));
                finish();
                return true;
            }
            return false;
        });

        popup.show();
    }
}
