package fr.bam.projetfinal;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ListView;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;
import androidx.constraintlayout.widget.ConstraintLayout;

import com.google.android.material.floatingactionbutton.FloatingActionButton;

import java.util.ArrayList;

import fr.bam.projetfinal.model.Ordonnance;
import fr.bam.projetfinal.model.OrdonnanceAdapter;

public class PatientInfoActivity  extends AppCompatActivity {

    private ConstraintLayout cl;
    private TextView mFirstName;

    private FloatingActionButton createOrdonnanceButton;

    private ListView mListView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_patient_info);

        cl = findViewById(R.id.patient_info_cl);
        mFirstName = findViewById(R.id.Patient_Info_Name);
        createOrdonnanceButton = findViewById(R.id.patient_info_create_Ordonnance);
        mListView = findViewById(R.id.patient_info_Ordonnace_listView);
        displayOrdonnance();

        createOrdonnanceButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(PatientInfoActivity.this, AddMedicationToPatientActivity.class);
                startActivity(intent);
            }
        });


    }


    public void displayOrdonnance() {
        DoctorDB db = new DoctorDB(PatientInfoActivity.this);


        ArrayList<Ordonnance> ordonnancesList = db.getAllOrdonnances();

        OrdonnanceAdapter adapter = new OrdonnanceAdapter(PatientInfoActivity.this ,  ordonnancesList);
        mListView.setAdapter(adapter);


        mListView.setOnItemClickListener((parent, view, position, id) -> {

        });

    }
}
