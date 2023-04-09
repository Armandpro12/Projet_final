package fr.bam.projetfinal;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import androidx.appcompat.app.AppCompatActivity;

import fr.bam.projetfinal.model.Patient;

public class CreatePatientActivity extends AppCompatActivity {
    private EditText mUsernameInput;
    private EditText mPasswordInput;
    private Button mCreatePatient;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_create_patient);
        mUsernameInput = findViewById(R.id.CreatePatient_username_edittext);
        mPasswordInput = findViewById(R.id.CreatePatient_password_edittext);
        mCreatePatient = findViewById(R.id.CreatePatient_Create_button);

        mUsernameInput.addTextChangedListener(new TextWatcher() {

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

        mPasswordInput.addTextChangedListener(new TextWatcher() {
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


        mCreatePatient.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
            DoctorDB db = new DoctorDB(CreatePatientActivity.this);
            Patient patient = new Patient();
            patient.setFirstName(mUsernameInput.getText().toString());
            patient.setPassword(mPasswordInput.getText().toString());
            SharedPreferences stored_data = getSharedPreferences(MainActivity.STORED_DATA, MODE_PRIVATE);
            int doctorId = stored_data.getInt(MainActivity.STORED_DATA, -1);
            patient.setDoctor(doctorId);
            db.addPatient(patient);

            finish();


            }
        });






    }

    private void checkFields() {
        String username = mUsernameInput.getText().toString();
        String password = mPasswordInput.getText().toString();
        if (username.isEmpty() || password.isEmpty()) {
            mCreatePatient.setEnabled(false);
        } else {
            mCreatePatient.setEnabled(true);
        }
    }
}
