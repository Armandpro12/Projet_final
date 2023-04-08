package fr.bam.projetfinal;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.Toast;

import java.util.List;

import fr.bam.projetfinal.model.Doctor;
import fr.bam.projetfinal.model.Patient;

import fr.bam.projetfinal.model.Doctor;

public class LoginActivity extends AppCompatActivity {



    private EditText mUsernameInput;
    private EditText mPasswordInput;
    private Button mLoginButton;

    private CheckBox mIsDoctorCheckBox;
    private  Button mCreateDoctor;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        mIsDoctorCheckBox = findViewById(R.id.login_isDoctor_checkbox);
        mUsernameInput = findViewById(R.id.login_username_edittext);
        mPasswordInput = findViewById(R.id.login_password_edittext);
        mLoginButton = findViewById(R.id.login_login_button);
        mCreateDoctor = findViewById(R.id.Create_Doctor_button);
        mLoginButton.setEnabled(false);
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


        mCreateDoctor.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Intent createDoctorActivityIntent = new Intent(LoginActivity.this, CreateDoctorActivity.class);
                startActivity(createDoctorActivityIntent);

            }
        });


        mLoginButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                DoctorDB db = new DoctorDB(LoginActivity.this);
                String username = mUsernameInput.getText().toString();
                String password = mPasswordInput.getText().toString();
                Doctor doctor = db.getDoctor(username, password);
                if (doctor != null) {
                    System.out.printf("Doctor %s logged in", doctor.getFirstName());

                    /*Intent gameActivityIntent = new Intent(LoginActivity.this, DoctorActivity.class);
                    gameActivityIntent.putExtra("doctor", doctor);
                    startActivity(gameActivityIntent);*/
                }

            }
        });


        mLoginButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                DoctorDB doctorDB = new DoctorDB(LoginActivity.this);
                if(mIsDoctorCheckBox.isChecked()){
                    try{
                        Doctor doctor = doctorDB.getDoctor(mUsernameInput.getText().toString(), mPasswordInput.getText().toString());
                        List<Patient> patients = doctorDB.getAllPatientsOfDoctor(doctor.getId());
                        System.out.println("_____PATIENTS___________" + patients);
                        Toast.makeText(LoginActivity.this,"DOCTOR LOGIN SUCCESSFULL", Toast.LENGTH_SHORT).show();
                    }catch(Exception e){
                        System.out.println("_________EXCEPTIONS LOGINPASSWORD FALSE______\n" + e);
                        Toast.makeText(LoginActivity.this,"Doctor does not exist", Toast.LENGTH_SHORT).show();
                    }
                }
                else {
                    try{
                        doctorDB.getPatient(mUsernameInput.getText().toString(), mPasswordInput.getText().toString());
                        Toast.makeText(LoginActivity.this,"PATIENT LOGIN SUCCESSFULL", Toast.LENGTH_SHORT).show();
                    }catch(Exception e){
                        System.out.println("_________EXCEPTIONS LOGINPASSWORD FALSE______\n" + e);
                        Toast.makeText(LoginActivity.this,"Patient does not exist", Toast.LENGTH_SHORT).show();
                    }
                }
                doctorDB.close();
            }
        });

    }

    private void checkFields() {
        String username = mUsernameInput.getText().toString();
        String password = mPasswordInput.getText().toString();
        if (username.isEmpty() || password.isEmpty()) {
            mLoginButton.setEnabled(false);
        } else {
            mLoginButton.setEnabled(true);
        }
    }
}