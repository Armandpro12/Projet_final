package fr.bam.projetfinal;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;

import androidx.appcompat.app.AppCompatActivity;

import java.util.ArrayList;

import fr.bam.projetfinal.model.Date;
import fr.bam.projetfinal.model.Medication;
import fr.bam.projetfinal.model.Ordonnance;

public class AddMedicationToPatientActivity extends AppCompatActivity {

    private EditText mDescription;
    private EditText mDosage;
    private EditText mDate;

    private Spinner mMedicationSpinner;

    private Button mAddMedicationButton;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_medication_to_patient);

        mDescription = findViewById(R.id.add_medication_add_description);
        mDosage = findViewById(R.id.add_medication_add_dosage);
        mDate = findViewById(R.id.add_medication_add_date);
        mMedicationSpinner = findViewById(R.id.add_medication_medication_spinner);
        mAddMedicationButton = findViewById(R.id.add_medication_Create_ordonnace_and_date);

        //add all medication name to the spinner
        DoctorDB db = new DoctorDB(this);
        ArrayList<Medication> medications = db.getAllMedications();
        ArrayList<String> medicationNames = new ArrayList<>();
        for (int i = 0; i < medications.size(); i++) {
          medicationNames.add(medications.get(i).getName());
        }
        ArrayAdapter aa = new ArrayAdapter(this,android.R.layout.simple_spinner_item, medicationNames);
        aa.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        mMedicationSpinner.setAdapter(aa);

        mAddMedicationButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String description = mDescription.getText().toString();
                String dosage = mDosage.getText().toString();
                String date = mDate.getText().toString();
                String medicationChose = mMedicationSpinner.getSelectedItem().toString();


                DoctorDB db = new DoctorDB(AddMedicationToPatientActivity.this);
                //get medication id


                //get patient id from preferances
                SharedPreferences preferences = getSharedPreferences(MainActivity.STORED_DATA, Context.MODE_PRIVATE);
                int patientID = preferences.getInt(MainActivity.IS_LOGIN, -1);

                Ordonnance ordonnance = new Ordonnance(patientID, getMedicationID(medicationChose , medications ), description, dosage);
                db.addOrdonnance(ordonnance);
                //get the id of this ordonnance
                int ordonnanceID = db.getOrdonnanceId(ordonnance);
                //add the date
                Date d = new Date( date , false , ordonnanceID);
                db.addDate(d);


                finish();
            }
        });

    }




    public int getMedicationID(String medicationName , ArrayList<Medication> allMedication){
        for (int i = 0; i < allMedication.size(); i++) {
            if (allMedication.get(i).getName().equals(medicationName)){
                return allMedication.get(i).getId();
            }
        }
        return -1;
    }
}
