package com.example.nosmoking;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.text.InputType;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;
import androidx.appcompat.app.AppCompatActivity;

public class PatientLoginActivity extends AppCompatActivity {

    private EditText emailEditText, passwordEditText;
    private ImageView passwordToggle, backArrow;
    private boolean isPasswordVisible = false;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        // Check if user is already logged in
        SharedPreferences sharedPreferences = getSharedPreferences("NoSmokingApp", MODE_PRIVATE);
        if (sharedPreferences.getBoolean("isLoggedIn", false)) {
            startActivity(new Intent(PatientLoginActivity.this, PatientHomeActivity.class));
            finish();
            return;
        }

        setContentView(R.layout.activity_patient_login);

        emailEditText = findViewById(R.id.emailEditText);
        passwordEditText = findViewById(R.id.passwordEditText);
        passwordToggle = findViewById(R.id.passwordToggle);
        backArrow = findViewById(R.id.backArrow);
        Button loginButton = findViewById(R.id.loginButton);
        TextView signUp = findViewById(R.id.signUpButton); // 🔹 Added SignUp TextView

        // 🔙 Handle Back Arrow Click
        backArrow.setOnClickListener(v -> finish());

        // 👁️ Toggle Password Visibility
        passwordToggle.setOnClickListener(v -> {
            if (isPasswordVisible) {
                passwordEditText.setInputType(InputType.TYPE_CLASS_TEXT | InputType.TYPE_TEXT_VARIATION_PASSWORD);
                passwordToggle.setImageResource(R.drawable.ic_visibility_off);
            } else {
                passwordEditText.setInputType(InputType.TYPE_TEXT_VARIATION_VISIBLE_PASSWORD);
                passwordToggle.setImageResource(R.drawable.ic_visibility);
            }
            isPasswordVisible = !isPasswordVisible;
        });

        // 🔑 Login Button Click
        loginButton.setOnClickListener(v -> {
            String email = emailEditText.getText().toString().trim();
            String password = passwordEditText.getText().toString().trim();

            if (email.isEmpty() || password.isEmpty()) {
                Toast.makeText(PatientLoginActivity.this, "Please enter email and password", Toast.LENGTH_SHORT).show();
            } else if (validateLogin(email, password)) {
                // ✅ Save login state
                SharedPreferences.Editor editor = sharedPreferences.edit();
                editor.putBoolean("isLoggedIn", true);
                editor.apply();

                Toast.makeText(PatientLoginActivity.this, "Login Successful!", Toast.LENGTH_SHORT).show();

                // ✅ Navigate to PatientHomeActivity
                Intent intent = new Intent(PatientLoginActivity.this, PatientHomeActivity.class);
                startActivity(intent);
                finish();
            } else {
                Toast.makeText(PatientLoginActivity.this, "Invalid Credentials", Toast.LENGTH_SHORT).show();
            }
        });

        // 🔹 Handle SignUp Click
        signUp.setOnClickListener(v -> {
            Intent intent = new Intent(PatientLoginActivity.this, SignUpActivity.class);
            startActivity(intent);
        });
    }

    // ✅ Ensure this method is OUTSIDE the onCreate() method
    private boolean validateLogin(String email, String password) {
        return email.equals("1") && password.equals("1"); // Example credentials
    }
}