package fr.bam.projetfinal;

import android.content.Intent;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import androidx.appcompat.app.AppCompatActivity;

public class CreateDoctorActivity extends AppCompatActivity {

    private EditText mUsernameInput;
    private EditText mPasswordInput;
    private Button mCreateDoctor;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_Create_Doctor);
        mUsernameInput = findViewById(R.id.login_username_edittext);
        mPasswordInput = findViewById(R.id.login_password_edittext);
        mCreateDoctor = findViewById(R.id.Create_button);
        mCreateDoctor.setEnabled(false);
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
                finish();
            }
        });



    }

    private void checkFields() {
        String username = mUsernameInput.getText().toString();
        String password = mPasswordInput.getText().toString();
        if (username.isEmpty() || password.isEmpty()) {
            mCreateDoctor.setEnabled(false);
        } else {
            mCreateDoctor.setEnabled(true);
        }
    }
}