package fr.bam.projetfinal;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;

public class MainActivity extends AppCompatActivity {

    private EditText mUsernameInput;
    private EditText mPasswordInput;
    private Button mLoginButton;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        mUsernameInput = findViewById(R.id.main_username_edittext);
        mPasswordInput = findViewById(R.id.main_password_edittext);
        mLoginButton = findViewById(R.id.main_login_button);
        mLoginButton.setEnabled(false);
    }
}