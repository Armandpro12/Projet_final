package fr.bam.projetfinal;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.widget.Button;
import android.widget.EditText;

import fr.bam.projetfinal.model.Doctor;
import fr.bam.projetfinal.model.Patient;

public class MainActivity extends AppCompatActivity {


    public static final String STORED_DATA = "storedData";
    public static final String IS_LOGIN = "isLogin";
    public static final String IS_DOCTOR = "isDoctor";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        createDefault();
        startActivity(new Intent(this, LoginActivity.class));
    }

    private void createDefault(){
        Doctor doctor1 = new Doctor("D1", "DLN1", "1 street Doctors", "doctor1@hospital.com", "123", new byte[0]);
        Patient patient1 = new Patient("P1", "PLN1", "1 street Patients", "patient1@home.com", "123", new byte[0], doctor1);
        Patient patient2 = new Patient("P2", "PLN2", "2 street Patients", "patient2@home.com", "123", new byte[0], doctor1);
        testInsertDoctor(doctor1);
        testInsertPatient(patient1);
        testInsertPatient(patient2);



    }

    private void testInsertDoctor(Doctor doctor){
        DoctorDB doctorDB = new DoctorDB(this);
        try{
            System.out.println("__________DOCTOR : "+doctor.getFirstName()+"__________\n" + doctorDB.getDoctor(doctor.getFirstName(), doctor.getPassword()).toString());
        } catch (Exception e){
            System.out.println("_____DOCTOR : "+doctor.getFirstName()+" DOESN'T EXIST_______________\n");
            doctorDB.addDoctor(doctor);
            System.out.println("________DOCTOR : "+doctor.getFirstName()+"__________\n" + doctorDB.getDoctor(doctor.getFirstName(), doctor.getPassword()).toString());
        }
    }

    private void testInsertPatient(Patient patient){
        DoctorDB doctorDB = new DoctorDB(this);
        try{
            System.out.println("__________PATIENT : "+patient.getFirstName()+"__________\n" + doctorDB.getPatient(patient.getFirstName(), patient.getPassword()).toString());
        } catch (Exception e){
            System.out.println("_____PATIENT : "+patient.getFirstName()+" DOESN'T EXIST_______________\n");
            doctorDB.addPatient(patient);
            System.out.println("________PATIENT : "+patient.getFirstName()+"__________\n" + doctorDB.getPatient(patient.getFirstName(), patient.getPassword()).toString());
        }
    }
}