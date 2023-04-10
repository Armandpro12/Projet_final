package fr.bam.projetfinal;

import android.Manifest;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.pm.PackageManager;
import android.graphics.Bitmap;
import android.graphics.drawable.BitmapDrawable;
import android.os.Bundle;
import android.provider.MediaStore;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;

import java.io.ByteArrayOutputStream;

import fr.bam.projetfinal.model.Doctor;
import fr.bam.projetfinal.model.Patient;

public class CreatePatientActivity extends AppCompatActivity {

    private static final int REQUEST_CAMERA_PERMISSION = 100;



    private Button mCreatePatient;
    private Button mTakePicture;
    private EditText mFirstNameInput;
    private EditText mLastNameInput;
    private EditText mAddressInput;
    private EditText mEmailInput;
    private EditText mPasswordInput;

    private ImageView mImageView;
    private boolean isPictureSet;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_create_doctor);

        mFirstNameInput = findViewById(R.id.login_firstname_edittext);
        mLastNameInput = findViewById(R.id.login_lastname_edittext);
        mAddressInput = findViewById(R.id.login_adresse_edittext);
        mEmailInput = findViewById(R.id.login_email_edittext);
        mPasswordInput = findViewById(R.id.login_password_edittext);
        mImageView = findViewById(R.id.photo_imageview);
        mCreatePatient = findViewById(R.id.create_button);
        mTakePicture = findViewById(R.id.take_photo_button);
        mCreatePatient.setEnabled(false);

        //mTakePictureListener
        mTakePicture.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (ContextCompat.checkSelfPermission(CreatePatientActivity.this, android.Manifest.permission.CAMERA) != PackageManager.PERMISSION_GRANTED) {
                    ActivityCompat.requestPermissions(CreatePatientActivity.this, new String[]{Manifest.permission.CAMERA}, REQUEST_CAMERA_PERMISSION);
                } else {
                    Intent intent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
                    startActivityForResult(intent, 1);
                }
            }
        });

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





        mCreatePatient.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                DoctorDB db = new DoctorDB(CreatePatientActivity.this);
                Patient patient = new Patient();
                patient.setFirstName(mFirstNameInput.getText().toString());
                patient.setLastName(mLastNameInput.getText().toString());
                patient.setAddress(mAddressInput.getText().toString());
                patient.setEmail(mEmailInput.getText().toString());
                Bitmap img = ((BitmapDrawable) mImageView.getDrawable()).getBitmap();
                byte[] data = getBitmapAsByteArray(img); // this is a function
                patient.setPhoto(data);
                patient.setPassword(mPasswordInput.getText().toString());
                SharedPreferences stored_data = getSharedPreferences(MainActivity.STORED_DATA, MODE_PRIVATE);
                int doctorId = stored_data.getInt(MainActivity.STORED_DATA, -1);
                patient.setDoctor(doctorId);
                db.addPatient(patient);

                finish();


            }
        });



    }


    private void takePicture(){

    }
    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == 1 && resultCode == RESULT_OK) {
            Bitmap photo = (Bitmap) data.getExtras().get("data");
            mImageView.setImageBitmap(photo);
            isPictureSet = true;

        }
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
        if (requestCode == REQUEST_CAMERA_PERMISSION) {
            if (grantResults.length > 0 && grantResults[0] == PackageManager.PERMISSION_GRANTED) {
                Intent intent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
                startActivityForResult(intent, 1);
            } else {
                Toast.makeText(this, "Camera permission denied", Toast.LENGTH_SHORT).show();
            }
        }
    }
    public static byte[] getBitmapAsByteArray(Bitmap bitmap) {
        ByteArrayOutputStream outputStream = new ByteArrayOutputStream();
        bitmap.compress(Bitmap.CompressFormat.PNG, 0, outputStream);
        return outputStream.toByteArray();
    }
    private void checkFields() {
        String firstname = mFirstNameInput.getText().toString();
        String lastname = mLastNameInput.getText().toString();
        String address = mAddressInput.getText().toString();
        String email = mEmailInput.getText().toString();
        String password = mPasswordInput.getText().toString();

        mCreatePatient.setEnabled(!firstname.isEmpty() && !lastname.isEmpty() && !address.isEmpty() && !email.isEmpty() && !password.isEmpty() && isPictureSet);
    }

}











