package fr.bam.projetfinal;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

import androidx.annotation.Nullable;

import java.util.ArrayList;
import java.util.List;

import fr.bam.projetfinal.model.Doctor;
import fr.bam.projetfinal.model.Medication;
import fr.bam.projetfinal.model.Patient;

public class DoctorDB extends SQLiteOpenHelper {

    private static final String TAG = "SQLite";
    private static final String TABLE_DOCTOR = "Doctor";

    private static final String COLUMN_NAME = "name";
    private static final String TABLE_MEDICATION = "Medication";
    private static final String TABLE_PATIENT = "Patient";
    private static final String COLUMN_ID = "id";

    private static final String COLUMN_FOREIGN_PATIENT_ID = "patient_id";

    private static final String COLUMN_DESCRIPTION = "description";

    private static final String COLUMN_FOREIGN_DOCTOR_ID = "doctor_id";
    private static final String COLUMN_FIRSTNAME = "firstName";
    private static final String COLUMN_LASTNAME = "lastName";
    private static final String COLUMN_ADDRESS = "address";
    private static final String COLUMN_EMAIL = "email";
    private static final String COLUMN_PHOTO = "photo";

    private static final String TABLE_DOSAGE = "dosage";
    private static final String COLUMN_FOREIGN_MEDICATION_ID = "medication_id";

    private static final String COLUMN_DOSAGE = "dosage";

    private static final String TABLE_DATE = "date";
    private static final String COLUMN_FOREIGN_DOSAGE_ID = "dosage_id";
    private static final String COLUMN_DATE = "date";
    private static final String COLUMN_TAKEN = "taken";



    public DoctorDB (@Nullable Context context) {
        super(context, "Note", null, 1);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        Log.i(TAG, "MyDatabaseHelper.onCreate ... ");
        // Script.
        String scriptDoctor = "CREATE TABLE " + TABLE_DOCTOR + "("
                + COLUMN_ID + " INTEGER PRIMARY KEY," + COLUMN_FIRSTNAME + " TEXT,"
                + COLUMN_LASTNAME + " TEXT," + COLUMN_ADDRESS + " TEXT," + COLUMN_EMAIL + " TEXT," + COLUMN_PHOTO + " BLOB"  + ")";
        // Execute Script.
        db.execSQL(scriptDoctor);

        String scriptPatient = "CREATE TABLE " + TABLE_PATIENT + "("
                + COLUMN_ID + " INTEGER PRIMARY KEY," + COLUMN_FIRSTNAME + " TEXT,"
                + COLUMN_LASTNAME + " TEXT," + COLUMN_ADDRESS + " TEXT," + COLUMN_EMAIL + " TEXT," + COLUMN_PHOTO + " BLOB,"  + COLUMN_FOREIGN_DOCTOR_ID + " INTEGER," +  "FOREIGN KEY("+COLUMN_FOREIGN_DOCTOR_ID+") REFERENCES "+TABLE_DOCTOR+"("+COLUMN_ID+")" + ")";
        // Execute Script.
        db.execSQL(scriptPatient);

        String scriptMedication = "CREATE TABLE " + TABLE_MEDICATION + "("
                + COLUMN_ID + " INTEGER PRIMARY KEY," + COLUMN_NAME + " TEXT," + COLUMN_DESCRIPTION + " TEXT," + COLUMN_PHOTO + " BLOB" + ")";
        db.execSQL(scriptMedication);

        String scriptDosage = "CREATE TABLE " + TABLE_DOSAGE + "("
                + COLUMN_ID + " INTEGER PRIMARY KEY," + COLUMN_FOREIGN_PATIENT_ID + " INTEGER," + COLUMN_FOREIGN_MEDICATION_ID + " INTEGER," + COLUMN_DOSAGE + " TEXT," + "FOREIGN KEY("+COLUMN_FOREIGN_PATIENT_ID+") REFERENCES "+TABLE_PATIENT+"("+COLUMN_ID+"),"+"FOREIGN KEY("+COLUMN_FOREIGN_MEDICATION_ID+") REFERENCES "+TABLE_MEDICATION+"("+COLUMN_ID+")"+")";
        db.execSQL(scriptDosage);

        String scriptDate = "CREATE TABLE " + TABLE_DATE + "("
                + COLUMN_ID + " INTEGER PRIMARY KEY," + COLUMN_DATE + " DATE," + COLUMN_TAKEN + " BIT," + COLUMN_FOREIGN_DOSAGE_ID + " INTEGER," + "FOREIGN KEY("+ COLUMN_FOREIGN_DOSAGE_ID +") REFERENCES " + TABLE_DOSAGE + "("+COLUMN_ID+")"+")";
        db.execSQL(scriptDate);

    }


    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int i, int i1) {

    }

    public void addDoctor(Doctor doctor) {
        Log.i(TAG, "MyDatabaseHelper.addProfile ... " + doctor.getFirstName());

        SQLiteDatabase db = this.getWritableDatabase();

        ContentValues values = new ContentValues();
        values.put(COLUMN_ID, doctor.getId());
        values.put(COLUMN_FIRSTNAME, doctor.getFirstName());
        values.put(COLUMN_LASTNAME, doctor.getLastName());
        values.put(COLUMN_ADDRESS, doctor.getAddress());
        values.put(COLUMN_EMAIL, doctor.getEmail());
        values.put(COLUMN_PHOTO, doctor.getPhoto());

        // Inserting Row
        db.insert(TABLE_DOCTOR, null, values);

        // Closing database connection
        db.close();
    }

    public void addMedication(Medication medication){

        Log.i(TAG, "MyDatabaseHelper.addProfile ... " + medication.getName());
        SQLiteDatabase db = this.getWritableDatabase();

        ContentValues values = new ContentValues();
        values.put(COLUMN_ID, medication.getId());
        values.put(COLUMN_NAME, medication.getName());
        values.put(COLUMN_DESCRIPTION, medication.getDescription());
        values.put(COLUMN_PHOTO, medication.getPhoto());

        db.insert(TABLE_MEDICATION, null, values);

    }

    public void addPatient(Patient patient) {
        Log.i(TAG, "MyDatabaseHelper.addProfile ... " + patient.getFirstName());

        SQLiteDatabase db = this.getWritableDatabase();

        ContentValues values = new ContentValues();
        values.put(COLUMN_ID, patient.getId());
        values.put(COLUMN_FIRSTNAME, patient.getFirstName());
        values.put(COLUMN_LASTNAME, patient.getLastName());
        values.put(COLUMN_ADDRESS, patient.getAddress());
        values.put(COLUMN_EMAIL, patient.getEmail());
        values.put(COLUMN_PHOTO, patient.getPhoto());
        values.put(COLUMN_FOREIGN_DOCTOR_ID, patient.getDoctor().getId());

        // Inserting Row
        db.insert(TABLE_PATIENT, null, values);

        // Closing database connection
        db.close();
    }

    public Patient getPatient(int id) {
        Log.i(TAG, "MyDatabaseHelper.getProfile ... " + id);

        SQLiteDatabase db = this.getReadableDatabase();

        Cursor cursor = db.query(TABLE_PATIENT, new String[]{COLUMN_ID, COLUMN_FIRSTNAME,
                        COLUMN_LASTNAME, COLUMN_ADDRESS, COLUMN_EMAIL, COLUMN_PHOTO, COLUMN_FOREIGN_DOCTOR_ID}, COLUMN_ID + "=?",
                new String[]{String.valueOf(id)}, null, null, null, null);
        if (cursor != null)
            cursor.moveToFirst();

        Patient patient = new Patient(cursor.getInt(0), cursor.getString(1),
                cursor.getString(2), cursor.getString(3), cursor.getString(4), cursor.getBlob(5), getDoctor(cursor.getInt(6)));
        // return profile
        return patient;
    }


    public Doctor getDoctor(int id) {
        Log.i(TAG, "MyDatabaseHelper.getProfile ... " + id);

        SQLiteDatabase db = this.getReadableDatabase();

        Cursor cursor = db.query(TABLE_DOCTOR, new String[]{COLUMN_ID, COLUMN_FIRSTNAME,
                        COLUMN_LASTNAME, COLUMN_ADDRESS, COLUMN_EMAIL, COLUMN_PHOTO}, COLUMN_ID + "=?",
                new String[]{String.valueOf(id)}, null, null, null, null);
        if (cursor != null)
            cursor.moveToFirst();

        Doctor doctor = new Doctor(cursor.getInt(0), cursor.getString(1),
                cursor.getString(2), cursor.getString(3), cursor.getString(4), cursor.getBlob(5));
        // return profile
        return doctor;
    }

    public List<Patient> getAllPatients() {
        Log.i(TAG, "MyDatabaseHelper.getAllProfiles ... ");

        List<Patient>  patientList = new ArrayList<Patient>();
        // Select All Query
        String selectQuery = "SELECT  * FROM " + TABLE_PATIENT;

        SQLiteDatabase db = this.getWritableDatabase();
        Cursor cursor = db.rawQuery(selectQuery, null);

        // looping through all rows and adding to list
        if (cursor.moveToFirst()) {
            do {
                Patient patient = new Patient(cursor.getInt(0), cursor.getString(1), cursor.getString(2), cursor.getString(3), cursor.getString(4), cursor.getBlob(5), getDoctor(cursor.getInt(6)));
                // Adding profile to list
                patientList.add(patient);


            } while (cursor.moveToNext());
        }
        return patientList;
    }

    public List<Doctor> getAllDoctors() {
        Log.i(TAG, "MyDatabaseHelper.getAllProfiles ... ");

        List<Doctor> doctorList = new ArrayList<Doctor>();
        // Select All Query
        String selectQuery = "SELECT  * FROM " + TABLE_DOCTOR;

        SQLiteDatabase db = this.getWritableDatabase();
        Cursor cursor = db.rawQuery(selectQuery, null);

        // looping through all rows and adding to list
        if (cursor.moveToFirst()) {
            do {
                Doctor doctor = new Doctor(cursor.getInt(0), cursor.getString(1), cursor.getString(2), cursor.getString(3), cursor.getString(4), cursor.getBlob(5));
                // Adding profile to list
                doctorList.add(doctor);


            } while (cursor.moveToNext());
        }
        return doctorList;
    }

    public int getDoctorsCount() {
        Log.i(TAG, "MyDatabaseHelper.getProfilesCount ... " );

        String countQuery = "SELECT  * FROM " + TABLE_DOCTOR;
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor cursor = db.rawQuery(countQuery, null);

        int count = cursor.getCount();

        cursor.close();

        // return count
        return count;
    }

    public int getPatientCount() {
        Log.i(TAG, "MyDatabaseHelper.getProfilesCount ... " );

        String countQuery = "SELECT  * FROM " + TABLE_PATIENT;
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor cursor = db.rawQuery(countQuery, null);

        int count = cursor.getCount();

        cursor.close();

        // return count
        return count;
    }

}
