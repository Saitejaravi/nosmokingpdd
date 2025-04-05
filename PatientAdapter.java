package com.example.nosmoking;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;
import java.util.ArrayList;
import java.util.List;

public class PatientAdapter extends RecyclerView.Adapter<PatientAdapter.PatientViewHolder> {
    private Context context;
    private List<Patient> patientList;
    private List<Patient> patientListFull; // Stores full list for filtering

    public PatientAdapter(Context context, List<Patient> patientList) {
        this.context = context;
        this.patientList = new ArrayList<>(patientList);
        this.patientListFull = new ArrayList<>(patientList);
    }

    @NonNull
    @Override
    public PatientViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.item_patient, parent, false);
        return new PatientViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull PatientViewHolder holder, int position) {
        Patient patient = patientList.get(position);
        holder.name.setText(patient.getName());
        holder.age.setText("Age: " + patient.getAge());
        holder.condition.setText("Condition: " + patient.getCondition());
        holder.image.setImageResource(patient.getImageResId());

        // 🔹 Navigate to PatientDetailsActivity when "View" button is clicked
        holder.viewButton.setOnClickListener(v -> {
            Intent intent = new Intent(context, PatientDetailsActivity.class);
            intent.putExtra("patientName", patient.getName());
            intent.putExtra("patientAge", patient.getAge());
            intent.putExtra("patientCondition", patient.getCondition());
            intent.putExtra("patientImage", patient.getImageResId());
            context.startActivity(intent);
        });
    }

    @Override
    public int getItemCount() {
        return patientList.size();
    }

    // 🔍 **Search Filter**
    public void filter(String text) {
        patientList.clear();
        if (text.isEmpty()) {
            patientList.addAll(patientListFull);
        } else {
            text = text.toLowerCase();
            for (Patient patient : patientListFull) {
                if (patient.getName().toLowerCase().contains(text) ||
                        patient.getCondition().toLowerCase().contains(text)) {
                    patientList.add(patient);
                }
            }
        }
        notifyDataSetChanged();
    }

    public static class PatientViewHolder extends RecyclerView.ViewHolder {
        TextView name, age, condition;
        ImageView image;
        Button viewButton;

        public PatientViewHolder(@NonNull View itemView) {
            super(itemView);
            name = itemView.findViewById(R.id.patientName);
            age = itemView.findViewById(R.id.patientAge);
            condition = itemView.findViewById(R.id.patientCondition);
            image = itemView.findViewById(R.id.patientImage);
            viewButton = itemView.findViewById(R.id.viewButton);
        }
    }
}
