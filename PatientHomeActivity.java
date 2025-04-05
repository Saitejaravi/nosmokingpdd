package com.example.nosmoking;

import android.app.Dialog;
import android.content.Intent;
import android.os.Bundle;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.Window;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.PopupWindow;
import android.widget.TextView;
import androidx.appcompat.app.AppCompatActivity;

public class PatientHomeActivity extends AppCompatActivity {

    private TextView coinCount, timeWithoutCigarette, savedMoney;
    private ImageView notificationButton, navHome, navTasks, navProfile, navMore;
    private Button btnTakeTask;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_patient_home);

        // Initialize UI elements
        coinCount = findViewById(R.id.coinCount);
        notificationButton = findViewById(R.id.notificationButton);
        btnTakeTask = findViewById(R.id.btnTakeTask);
        navHome = findViewById(R.id.navHome);
        navTasks = findViewById(R.id.navTasks);
        navProfile = findViewById(R.id.navProfile);
        navMore = findViewById(R.id.navMore);
        timeWithoutCigarette = findViewById(R.id.timeWithoutCigarette);
        savedMoney = findViewById(R.id.savedMoney);

        // Set Click Listeners
        notificationButton.setOnClickListener(v -> showNotificationPopup());

        btnTakeTask.setOnClickListener(v -> {
            Intent intent = new Intent(PatientHomeActivity.this, TasksActivity.class);
            startActivity(intent);
        });

        navHome.setOnClickListener(v -> {
            // Stay on the same screen
        });

        navTasks.setOnClickListener(v -> {
            Intent intent = new Intent(PatientHomeActivity.this, CravingActivity.class);
            startActivity(intent);
        });

        navProfile.setOnClickListener(v -> {
            Intent intent = new Intent(PatientHomeActivity.this, PatientProfileActivity.class);
            startActivity(intent);
        });

        navMore.setOnClickListener(v -> {
            Intent intent = new Intent(PatientHomeActivity.this, com.example.nosmoking.VideosActivity.class);
            startActivity(intent);
        });

        // Example of setting values dynamically
        coinCount.setText("253");
        savedMoney.setText("₹ 275.00");

        // Show coin popup when clicked
        coinCount.setOnClickListener(v -> showCoinPopup());
    }

    private void showCoinPopup() {
        Dialog dialog = new Dialog(this);
        dialog.requestWindowFeature(Window.FEATURE_NO_TITLE);
        dialog.setContentView(R.layout.dialog_coin_report);
        dialog.setCancelable(true);

        // Find views in the dialog
        TextView closeBtn = dialog.findViewById(R.id.btnClosePopup);
        closeBtn.setOnClickListener(v -> dialog.dismiss());

        // Show the dialog
        dialog.show();
    }

    private void showNotificationPopup() {
        // Inflate the popup layout
        View popupView = LayoutInflater.from(this).inflate(R.layout.popup_notification, null);

        // Create the popup window
        PopupWindow popupWindow = new PopupWindow(
                popupView,
                ViewGroup.LayoutParams.WRAP_CONTENT,
                ViewGroup.LayoutParams.WRAP_CONTENT,
                true
        );

        // Find button inside the popup and set click listener
        Button btnTake = popupView.findViewById(R.id.btnTakeTask);
        btnTake.setOnClickListener(v -> {
            popupWindow.dismiss();
            Intent intent = new Intent(PatientHomeActivity.this, MindfulBiweeklyReflectionActivity.class);
            startActivity(intent);
        });

        // Show the popup at the center of the screen
        popupWindow.showAtLocation(findViewById(android.R.id.content), Gravity.CENTER, 0, 0);

        btnTakeTask.setOnClickListener(v -> {
            Intent intent = new Intent(PatientHomeActivity.this, MindfulReflectionActivity.class);
            startActivity(intent);
        });

    }
}
