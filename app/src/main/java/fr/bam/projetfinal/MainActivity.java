package fr.bam.projetfinal;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.widget.Button;
import android.widget.EditText;

import fr.bam.projetfinal.model.Doctor;

public class MainActivity extends AppCompatActivity {


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        createDefault();
        startActivity(new Intent(this, LoginActivity.class));
        startActivity(new Intent(this, CalendrierActivity.class));

    }

    private void createDefault(){
        DoctorDB doctorDB = new DoctorDB(this);
        Doctor doctor = new Doctor("Doctor1", "456LTest", "3094423ATest", "930423@test.com", "1234");
        try{
            System.out.println("________DOCTOR__________\n" + doctorDB.getDoctor(doctor.getFirstName(), doctor.getPassword()).toString());
        } catch (Exception e){
            System.out.println("_____EXCEPTION DON't EXIST_______________");
            doctorDB.addDoctor(doctor);
            System.out.println("________DOCTOR__________\n" + doctorDB.getDoctor(doctor.getFirstName(), doctor.getPassword()).toString());
            System.out.println("________EXCEPTION__________\n" + e);
        }


    }
}
