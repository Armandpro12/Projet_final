package fr.bam.projetfinal;

import android.content.Intent;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import fr.bam.projetfinal.model.Doctor;

public class CreateDoctorActivity extends AppCompatActivity {





    private Button mCreateDoctor;
    private Button mTakePicture;
    private EditText mFirstNameInput;
    private EditText mLastNameInput;
    private EditText mAddressInput;
    private EditText mEmailInput;
    private EditText mPasswordInput;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_create_doctor);

        mFirstNameInput = findViewById(R.id.login_firstname_edittext);
        mLastNameInput = findViewById(R.id.login_lastname_edittext);
        mAddressInput = findViewById(R.id.login_adresse_edittext);
        mEmailInput = findViewById(R.id.login_email_edittext);
        mPasswordInput = findViewById(R.id.login_password_edittext);

        mCreateDoctor = findViewById(R.id.create_button);
        mTakePicture = findViewById(R.id.take_photo_button);
        mCreateDoctor.setEnabled(false);
// Champ de saisie pour le prénom
        mFirstNameInput.addTextChangedListener(new TextWatcher() {

            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {
            }

            @Override
            public void afterTextChanged(Editable editable) {
                checkFields();
            }
        });

// Champ de saisie pour le nom de famille
        mLastNameInput.addTextChangedListener(new TextWatcher() {

            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {
            }

            @Override
            public void afterTextChanged(Editable editable) {
                checkFields();
            }
        });

// Champ de saisie pour l'adresse
        mAddressInput.addTextChangedListener(new TextWatcher() {

            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {
            }

            @Override
            public void afterTextChanged(Editable editable) {
                checkFields();
            }
        });

// Champ de saisie pour l'adresse e-mail
        mEmailInput.addTextChangedListener(new TextWatcher() {

            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {
            }

            @Override
            public void afterTextChanged(Editable editable) {
                checkFields();
            }
        });

// Champ de saisie pour le mot de passe
        mPasswordInput.addTextChangedListener(new TextWatcher() {

            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {
            }

            @Override
            public void afterTextChanged(Editable editable) {
                checkFields();
            }
        });





        mCreateDoctor.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                DoctorDB db = new DoctorDB(CreateDoctorActivity.this);
                Doctor doctor = new Doctor();
                doctor.setFirstName(mFirstNameInput.getText().toString());
                doctor.setLastName(mLastNameInput.getText().toString());
                doctor.setAddress(mAddressInput.getText().toString());
                doctor.setEmail(mEmailInput.getText().toString());
                doctor.setPassword(mPasswordInput.getText().toString());

                db.addDoctor(doctor);

                finish();
            }
        });



    }

    private void checkFields() {
        String firstname = mFirstNameInput.getText().toString();
        String lastname = mLastNameInput.getText().toString();
        String address = mAddressInput.getText().toString();
        String email = mEmailInput.getText().toString();
        String password = mPasswordInput.getText().toString();

        mCreateDoctor.setEnabled(!firstname.isEmpty() && !lastname.isEmpty() && !address.isEmpty() && !email.isEmpty() && !password.isEmpty());
    }

}