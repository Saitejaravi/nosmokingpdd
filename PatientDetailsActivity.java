package com.example.nosmoking;

import android.content.Intent;
import android.os.Bundle;
import android.widget.ImageView;
import android.widget.TextView;
import androidx.appcompat.app.AppCompatActivity;

public class PatientDetailsActivity extends AppCompatActivity {

    private ImageView patientImage;
    private TextView patientName, patientAge, patientCondition;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_patient_details);

        patientImage = findViewById(R.id.patientImage);
        patientName = findViewById(R.id.patientName);
        patientAge = findViewById(R.id.patientAge);
        patientCondition = findViewById(R.id.patientCondition);


        // 🔹 Get Data from Intent
        Intent intent = getIntent();
        if (intent != null) {
            String name = intent.getStringExtra("name");
            String age = intent.getStringExtra("age");
            String condition = intent.getStringExtra("condition");
            int imageResId = intent.getIntExtra("image", R.drawable.ic_patient);

            patientName.setText(name);
            patientAge.setText("Age: " + age);
            patientCondition.setText("Condition: " + condition);
            patientImage.setImageResource(imageResId);
        }
    }
}
