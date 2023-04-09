package fr.bam.projetfinal;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.Toast;

import java.util.List;

import fr.bam.projetfinal.decrepeted.DoctorActivity;
import fr.bam.projetfinal.model.Doctor;
import fr.bam.projetfinal.model.Patient;

public class LoginActivity extends AppCompatActivity {



    private EditText mUsernameInput;
    private EditText mPasswordInput;
    private Button mLoginButton;

    private CheckBox mIsDoctorCheckBox;
    private  Button mCreateDoctor;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        isLog();
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
                        login(doctor.getId(), 1);
                    }catch(Exception e){
                        System.out.println("_________EXCEPTIONS LOGINPASSWORD FALSE______\n" + e);
                        Toast.makeText(LoginActivity.this,"Doctor does not exist", Toast.LENGTH_SHORT).show();
                    }
                }
                else {
                    try{
                        Patient p = doctorDB.getPatient(mUsernameInput.getText().toString(), mPasswordInput.getText().toString());
                        Toast.makeText(LoginActivity.this,"PATIENT LOGIN SUCCESSFULL", Toast.LENGTH_SHORT).show();
                        login(p.getId(), 0);
                    }catch(Exception e){
                        System.out.println("_________EXCEPTIONS LOGINPASSWORD FALSE______\n" + e);
                        Toast.makeText(LoginActivity.this,"Patient does not exist", Toast.LENGTH_SHORT).show();
                    }
                }
                doctorDB.close();
            }
        });

    }
    /**
     * Check if both username and password fields are filled.
     * Enable or disable the login button accordingly
     */
    private void checkFields() {
        String username = mUsernameInput.getText().toString();
        String password = mPasswordInput.getText().toString();
        if (username.isEmpty() || password.isEmpty()) {
            mLoginButton.setEnabled(false);
        } else {
            mLoginButton.setEnabled(true);
        }
    }

    /**
     * Login the user
     * @param id the id of the user.
     * @param isDoctor 1 if the user is a doctor, 0 if the user is a patient.
     */
    private void  login(int id  , int  isDoctor){

        Intent intent ;
        if(isDoctor == 1){
            intent = new Intent(LoginActivity.this, doctor_view.class);
        }else{
            intent = new Intent(LoginActivity.this, patient_view.class);
        }

        intent.putExtra("id", id);
        SharedPreferences stored_data = getSharedPreferences(MainActivity.STORED_DATA, MODE_PRIVATE);
        stored_data.edit().putInt(MainActivity.IS_LOGIN , id).apply();
        stored_data.edit().putInt(MainActivity.IS_DOCTOR , isDoctor).apply();
        Toast.makeText(LoginActivity.this,"Login as a doctor "+isDoctor+" with id "+id , Toast.LENGTH_SHORT ).show();
        startActivity(intent);

    }
    /**
     * Check if the user is already logged in
     */
    public void isLog(){
        SharedPreferences stored_data = getSharedPreferences(MainActivity.STORED_DATA, MODE_PRIVATE);
        int isLogin = stored_data.getInt(MainActivity.IS_LOGIN, -1);
        int isDoctor = stored_data.getInt(MainActivity.IS_DOCTOR, -1);
        if(isLogin != -1 && isDoctor != -1){
            login(isLogin, isDoctor);
        }
    }





}