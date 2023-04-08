package fr.bam.projetfinal;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import fr.bam.projetfinal.model.Doctor;

public class LoginActivity extends AppCompatActivity {

    public static final DoctorDB db = new DoctorDB(null);

    private EditText mUsernameInput;
    private EditText mPasswordInput;
    private Button mLoginButton;
    private Button mCreateDoctor;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        mUsernameInput = findViewById(R.id.login_username_edittext);
        mPasswordInput = findViewById(R.id.login_password_edittext);
        mLoginButton = findViewById(R.id.login_login_button);
        mLoginButton.setEnabled(false);
        mCreateDoctor = findViewById(R.id.Create_Doctor_button);
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


        mCreateDoctor.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Intent gameActivityIntent = new Intent(LoginActivity.this, CreateDoctorActivity.class);
                startActivity(gameActivityIntent);

            }
        });


        mLoginButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
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