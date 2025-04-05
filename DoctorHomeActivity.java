package com.example.nosmoking;

import android.content.Intent;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.MenuItem;
import android.widget.EditText;
import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import com.google.android.material.bottomnavigation.BottomNavigationView;
import java.util.ArrayList;
import java.util.List;

public class DoctorHomeActivity extends AppCompatActivity {

    private RecyclerView recyclerView;
    private PatientAdapter adapter;
    private EditText searchPatient;
    private List<Patient> patientList = new ArrayList<>();
    private BottomNavigationView bottomNavigation;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_doctor_home);

        searchPatient = findViewById(R.id.searchPatient);
        recyclerView = findViewById(R.id.patientRecyclerView);
        bottomNavigation = findViewById(R.id.bottomNavigation);

        recyclerView.setLayoutManager(new LinearLayoutManager(this));

        // Load patient data
        loadPatients();

        // Initialize adapter
        adapter = new PatientAdapter(this, patientList);
        recyclerView.setAdapter(adapter);

        // 🔍 Search functionality
        searchPatient.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {}

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                adapter.filter(s.toString()); // Apply search filter
            }

            @Override
            public void afterTextChanged(Editable s) {}
        });

        // ✅ Corrected Bottom Navigation Click Handling
        bottomNavigation.setOnItemSelectedListener(new BottomNavigationView.OnItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {
                if (item.getItemId() == R.id.nav_home) {
                    // Stay on Home
                    return true;
                } else if (item.getItemId() == R.id.nav_profile) {
                    // ✅ Navigate to Doctor Profile
                    Intent profileIntent = new Intent(DoctorHomeActivity.this, DoctorProfileActivity.class);
                    startActivity(profileIntent);
                    return true;
                }
                return false;
            }
        });
    }

    private void loadPatients() {
        patientList.add(new Patient("John Doe", "25", "Lung Infection", R.drawable.patient1));
        patientList.add(new Patient("Sarah Smith", "30", "Asthma", R.drawable.patient2));
        patientList.add(new Patient("Mike Johnson", "40", "Heart Disease", R.drawable.patient3));
        patientList.add(new Patient("Emma Brown", "35", "COPD", R.drawable.patient4));
        patientList.add(new Patient("William Wilson", "50", "Pneumonia", R.drawable.patient5));
    }
}
