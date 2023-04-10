package fr.bam.projetfinal;

import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import androidx.appcompat.app.AppCompatActivity;

import fr.bam.projetfinal.model.Medication;

public class CreateMedicationActivity extends AppCompatActivity {

    private EditText mMedicationNameInput;
    private EditText mMedicationDescriptionInput;
    private Button mCreateMedicationButton;


    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_create_medication);
        mMedicationNameInput = findViewById(R.id.Create_Medication_Name);
        mMedicationDescriptionInput = findViewById(R.id.Create_Medication_Description);
        mCreateMedicationButton = findViewById(R.id.Create_Medication_Confirmation);
        mCreateMedicationButton.setEnabled(true);

        mMedicationNameInput.addTextChangedListener(new TextWatcher() {

            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {
                checkFields();
            }

            @Override
            public void afterTextChanged(Editable editable) {
                checkFields();
            }
        });

        mMedicationDescriptionInput.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {
                checkFields();
            }

            @Override
            public void afterTextChanged(Editable editable) {
                checkFields();
            }
        });


        mCreateMedicationButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                DoctorDB db = new DoctorDB(CreateMedicationActivity.this);
                String name = mMedicationNameInput.getText().toString();
                String description = mMedicationDescriptionInput.getText().toString();
                Medication medication = new Medication();
                medication.setName(name);
                medication.setDescription(description);
                db.addMedication(medication);
                finish();
            }
        });


    }

    private void checkFields() {
        String username = mMedicationNameInput.getText().toString();
        String password = mMedicationDescriptionInput.getText().toString();
        if (username.isEmpty() || password.isEmpty()) {
            mCreateMedicationButton.setEnabled(false);
        } else {
            mCreateMedicationButton.setEnabled(true);
        }
    }

}
