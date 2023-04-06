package fr.bam.projetfinal;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.widget.Button;
import android.widget.EditText;

import java.util.ArrayList;
import java.util.List;

import fr.bam.projetfinal.model.Date;
import fr.bam.projetfinal.model.Doctor;
import fr.bam.projetfinal.model.Dosage;
import fr.bam.projetfinal.model.Medication;
import fr.bam.projetfinal.model.Patient;

public class MainActivity extends AppCompatActivity {


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        //createDefault();
        //startActivity(new Intent(this, LoginActivity.class));
        startActivity(new Intent(this, CalendrierActivity.class));

    }

    private void createDefault(){
        DoctorDB doctorDB = new DoctorDB(this);

        Doctor doctor = new Doctor("Doctor1", "456LTest", "3094423ATest", "930423@test.com", "1234");
        doctorDB.addDoctor(doctor);
        List<Dosage> dosages = new ArrayList<>();
        Medication medication = new Medication("doliprane","paracetamol",new byte[0]);


        Patient patient = new Patient("p", "LastName1", "address", "email@gmail.com", "123",new byte[0], doctor);
        Dosage dosage = new Dosage(patient, medication,"3 capsule");
        Date date = new Date("2023-04-06",false,dosage);
        doctorDB.addPatient(patient);
        doctorDB.addMedication(medication);
        doctorDB.addDosage(dosage);
        doctorDB.addDate(date);
        try{
            System.out.println("________Patient__________\n" + doctorDB.getPatient(patient.getFirstName(), patient.getPassword()).toString());
        } catch (Exception e){
            System.out.println("_____EXCEPTION DON't EXIST_______________");
            //doctorDB.addDoctor(doctor);
            //System.out.println("________Patient__________\n" + doctorDB.getPatient(patient.getFirstName(), patient.getPassword()).toString());
            System.out.println("________EXCEPTION__________\n" + e);
        }


    }
}
