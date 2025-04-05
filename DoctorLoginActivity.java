package com.example.nosmoking;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Toast;
import androidx.appcompat.app.AppCompatActivity;

public class DoctorLoginActivity extends AppCompatActivity {

    private EditText emailEditText, passwordEditText;
    private ImageView passwordToggle, backArrow;
    private boolean isPasswordVisible = false;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_doctor_login);

        emailEditText = findViewById(R.id.emailEditText);
        passwordEditText = findViewById(R.id.passwordEditText);
        passwordToggle = findViewById(R.id.passwordToggle);
        backArrow = findViewById(R.id.backArrow);
        Button loginButton = findViewById(R.id.loginButton);

        // 🔙 Handle Back Arrow Click
        backArrow.setOnClickListener(v -> finish());

        // 👁️ Toggle Password Visibility
        passwordToggle.setOnClickListener(v -> {
            if (isPasswordVisible) {
                passwordEditText.setInputType(129); // Hide password
                passwordToggle.setImageResource(R.drawable.ic_visibility_off);
            } else {
                passwordEditText.setInputType(145); // Show password
                passwordToggle.setImageResource(R.drawable.ic_visibility);
            }
            isPasswordVisible = !isPasswordVisible;
        });

        // 🔑 Login Button Click
        loginButton.setOnClickListener(v -> {
            String email = emailEditText.getText().toString().trim();
            String password = passwordEditText.getText().toString().trim();

            if (email.isEmpty() || password.isEmpty()) {
                Toast.makeText(DoctorLoginActivity.this, "Please enter email and password", Toast.LENGTH_SHORT).show();
            } else if (validateLogin(email, password)) {
                Toast.makeText(DoctorLoginActivity.this, "Login Successful!", Toast.LENGTH_SHORT).show();

                // ✅ Navigate to DoctorHomeActivity
                Intent intent = new Intent(DoctorLoginActivity.this, DoctorHomeActivity.class);
                startActivity(intent);
                finish(); // Close login screen
            } else {
                Toast.makeText(DoctorLoginActivity.this, "Invalid Credentials", Toast.LENGTH_SHORT).show();
            }
        });
    }

    // Dummy validation method (Replace this with actual authentication logic)
    private boolean validateLogin(String email, String password) {
        return email.equals("1") && password.equals("1"); // Example credentials
    }
}