package fr.bam.projetfinal;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import fr.bam.projetfinal.model.Patient;

public class LoginActivity extends AppCompatActivity {

    private EditText mUsernameInput;
    private EditText mPasswordInput;
    private Button mLoginButton;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        mUsernameInput = findViewById(R.id.login_username_edittext);
        mPasswordInput = findViewById(R.id.login_password_edittext);
        mLoginButton = findViewById(R.id.login_login_button);
        mLoginButton.setEnabled(false);
        mUsernameInput.addTextChangedListener(new TextWatcher() {

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
        mLoginButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                DoctorDB doctorDB = new DoctorDB(LoginActivity.this);
                /*
                if(false){
                    try{
                        doctorDB.getDoctor(mUsernameInput.getText().toString(), mPasswordInput.getText().toString());
                    }catch(Exception e){
                        System.out.println("_EXCEPTIONS LOGINPASSWORD FALSE__\n" + e);
                        Toast.makeText(LoginActivity.this,"Doctor does not exist", Toast.LENGTH_SHORT).show();
                    }
                }
                else {*/
                    try{
                        doctorDB.getPatient(mUsernameInput.getText().toString(), mPasswordInput.getText().toString());
                        Patient patient = doctorDB.getPatient(mUsernameInput.getText().toString(), mPasswordInput.getText().toString());
                         Intent calendarintent= new Intent(LoginActivity.this, CalendrierActivity.class);
                        calendarintent.putExtra("Idpatient", patient.getId());
                        startActivity(calendarintent);

                        LoginActivity.this.finish();
                    }catch(Exception e){
                        System.out.println("_EXCEPTIONS LOGINPASSWORD FALSE__\n" + e);
                        Toast.makeText(LoginActivity.this,"Patient does not exist", Toast.LENGTH_SHORT).show();
                    }
               // }
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