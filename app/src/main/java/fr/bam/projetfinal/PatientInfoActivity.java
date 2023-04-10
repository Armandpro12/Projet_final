package fr.bam.projetfinal;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;
import androidx.constraintlayout.widget.ConstraintLayout;

import com.google.android.material.floatingactionbutton.FloatingActionButton;

import java.util.ArrayList;

import fr.bam.projetfinal.model.Doctor;
import fr.bam.projetfinal.model.Ordonnance;
import fr.bam.projetfinal.model.Patient;
import fr.bam.projetfinal.ui.ListPatientFragment;

public class PatientInfoActivity  extends AppCompatActivity {

    private ConstraintLayout cl;
    private TextView mFirstName;

    private FloatingActionButton createOrdonnanceButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_patient_info);

        cl = findViewById(R.id.patient_info_cl);
        mFirstName = findViewById(R.id.Patient_Info_Name);
        createOrdonnanceButton = findViewById(R.id.patient_info_create_Ordonnance);
        displayOrdonnance(cl);

        createOrdonnanceButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(PatientInfoActivity.this, AddMedicationToPatientActivity.class);
                startActivity(intent);
            }
        });


    }


    public void displayOrdonnance(ConstraintLayout cl) {
        int patientID = getIntent().getIntExtra("patientId", -1);
        System.out.printf("\n\n patientID = %d \n\n\n", patientID);
        //get the patient
        DoctorDB db = new DoctorDB(this);
        Patient patient = db.getPatient(patientID);
        //set the patient name
        mFirstName.setText(patient.getFirstName());
        ArrayList<Ordonnance> all = db.getAllOrdonnances();
        ArrayList<Ordonnance> ordonnances = db.getAllPatientOrdonnances(patientID);
        System.out.printf("\n\n ordonnances = %s \n\n\n", ordonnances.toString());
        System.out.println("\n\n all = " + all.toString() + "\n\n\n");

        for (int i = 0; i < ordonnances.size(); i++) {

            TextView textView = new TextView(this);
            textView.setText(db.getMedication(ordonnances.get(i).getMedicationID()).toString());
            textView.setId(ordonnances.get(i).getId());
            textView.setPadding(10, 30 + 60 * i, 0, 0);
            textView.setTextSize(20);
            cl.addView(textView);


            textView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {

                    /*
                    // start PatientInfoActivity and pass the patient id
                    Intent intent = new Intent(getActivity(), PatientInfoActivity.class);
                    intent.putExtra("patientId", textView.getId());
                    startActivity(intent);

                     */


                }
            });

        }
    }
}