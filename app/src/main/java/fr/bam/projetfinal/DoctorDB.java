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

import fr.bam.projetfinal.model.Date;
import fr.bam.projetfinal.model.Doctor;
import fr.bam.projetfinal.model.Ordonnance;
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

    private static final String TABLE_ORDONNANCE = "ordonnance";
    private static final String COLUMN_FOREIGN_MEDICATION_ID = "medication_id";

    private static final String COLUMN_DOSAGE = "dosage";

    private static final String TABLE_DATE = "date";
    private static final String COLUMN_FOREIGN_ORDONNANCE_ID = "ordonnance_id";
    private static final String COLUMN_DATE = "date";
    private static final String COLUMN_TAKEN = "taken";
    private static final String COLUMN_PASSWORD = "password";



    public DoctorDB (@Nullable Context context) {
        super(context, "Note", null, 1);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        Log.i(TAG, "MyDatabaseHelper.onCreate ... ");
        // Script.
        String scriptDoctor = "CREATE TABLE " + TABLE_DOCTOR + "("
                + COLUMN_ID + " INTEGER PRIMARY KEY," + COLUMN_FIRSTNAME + " TEXT,"
                + COLUMN_LASTNAME + " TEXT," + COLUMN_ADDRESS + " TEXT," + COLUMN_EMAIL + " TEXT," + COLUMN_PASSWORD + " TEXT," + COLUMN_PHOTO + " BLOB"  + ")";
        // Execute Script.
        db.execSQL(scriptDoctor);

        String scriptPatient = "CREATE TABLE " + TABLE_PATIENT + "("
                + COLUMN_ID + " INTEGER PRIMARY KEY," + COLUMN_FIRSTNAME + " TEXT,"
                + COLUMN_LASTNAME + " TEXT," + COLUMN_ADDRESS + " TEXT," + COLUMN_EMAIL + " TEXT," + COLUMN_PASSWORD + " TEXT," + COLUMN_PHOTO + " BLOB,"  + COLUMN_FOREIGN_DOCTOR_ID + " INTEGER," +  "FOREIGN KEY("+COLUMN_FOREIGN_DOCTOR_ID+") REFERENCES "+TABLE_DOCTOR+"("+COLUMN_ID+")" + ")";
        // Execute Script.
        db.execSQL(scriptPatient);

        String scriptMedication = "CREATE TABLE " + TABLE_MEDICATION + "("
                + COLUMN_ID + " INTEGER PRIMARY KEY," + COLUMN_NAME + " TEXT," + COLUMN_DESCRIPTION + " TEXT," + COLUMN_PHOTO + " BLOB" + ")";
        db.execSQL(scriptMedication);

        String scriptDosage = "CREATE TABLE " + TABLE_ORDONNANCE + "("
                + COLUMN_ID + " INTEGER PRIMARY KEY," + COLUMN_FOREIGN_PATIENT_ID + " INTEGER," + COLUMN_FOREIGN_MEDICATION_ID + " INTEGER," + COLUMN_DESCRIPTION + " TEXT," + COLUMN_DOSAGE + " TEXT," + "FOREIGN KEY("+COLUMN_FOREIGN_PATIENT_ID+") REFERENCES "+TABLE_PATIENT+"("+COLUMN_ID+"),"+"FOREIGN KEY("+COLUMN_FOREIGN_MEDICATION_ID+") REFERENCES "+TABLE_MEDICATION+"("+COLUMN_ID+")"+")";
        db.execSQL(scriptDosage);

        String scriptDate = "CREATE TABLE " + TABLE_DATE + "("
                + COLUMN_ID + " INTEGER PRIMARY KEY," + COLUMN_DATE + " DATETIME," + COLUMN_TAKEN + " BIT," + COLUMN_FOREIGN_ORDONNANCE_ID + " INTEGER," + "FOREIGN KEY("+ COLUMN_FOREIGN_ORDONNANCE_ID +") REFERENCES " + TABLE_ORDONNANCE + "("+COLUMN_ID+")"+")";
        db.execSQL(scriptDate);

    }


    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int i, int i1) {

    }

    public void addDoctor(Doctor doctor) {
        Log.i(TAG, "MyDatabaseHelper.addDoctor ... " + doctor.getFirstName());

        SQLiteDatabase db = this.getWritableDatabase();

        ContentValues values = new ContentValues();
        values.put(COLUMN_FIRSTNAME, doctor.getFirstName());
        values.put(COLUMN_LASTNAME, doctor.getLastName());
        values.put(COLUMN_ADDRESS, doctor.getAddress());
        values.put(COLUMN_EMAIL, doctor.getEmail());
        values.put(COLUMN_PASSWORD, doctor.getPassword());
        values.put(COLUMN_PHOTO, doctor.getPhoto());

        // Inserting Row
        db.insert(TABLE_DOCTOR, null, values);

        // Closing database connection
        db.close();
    }

    public void addMedication(Medication medication){

        Log.i(TAG, "MyDatabaseHelper.addMedication ... " + medication.getName());
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put(COLUMN_NAME, medication.getName());
        values.put(COLUMN_DESCRIPTION, medication.getDescription());
        values.put(COLUMN_PHOTO, medication.getPhoto());

        db.insert(TABLE_MEDICATION, null, values);
        db.close();

    }

    /**
     * add the wanted dosage and the date in the bd
     * @param ordonnance
     */
    public void addOrdonnance(Ordonnance ordonnance){
        Log.i(TAG, "MyDatabaseHelper.addOrdonnance ... " + ordonnance.toString());
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put(COLUMN_FOREIGN_PATIENT_ID, ordonnance.getPatient().getId());
        values.put(COLUMN_FOREIGN_MEDICATION_ID, ordonnance.getMedication().getId());
        values.put(COLUMN_DESCRIPTION, ordonnance.getDescription());
        values.put(COLUMN_DOSAGE, ordonnance.getDosage());

        db.insert(TABLE_ORDONNANCE, null, values);
        db.close();
    }

    public void addDate(Date date){
        Log.i(TAG, "MyDatabaseHelper.addDate ... " + date.toString());
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put(COLUMN_DATE, date.getDate());
        values.put(COLUMN_TAKEN, date.isTaken());
        values.put(COLUMN_FOREIGN_ORDONNANCE_ID, date.getOrdonnance().getId());

        db.insert(TABLE_DATE, null, values);
        db.close();
    }

    public void addPatient(Patient patient) {
        Log.i(TAG, "MyDatabaseHelper.addPatient ... " + patient.getFirstName());

        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put(COLUMN_FIRSTNAME, patient.getFirstName());
        values.put(COLUMN_LASTNAME, patient.getLastName());
        values.put(COLUMN_ADDRESS, patient.getAddress());
        values.put(COLUMN_EMAIL, patient.getEmail());
        values.put(COLUMN_PASSWORD, patient.getPassword());
        values.put(COLUMN_PHOTO, patient.getPhoto());
        values.put(COLUMN_FOREIGN_DOCTOR_ID, patient.getDoctorId());

        // Inserting Row
        db.insert(TABLE_PATIENT, null, values);

        // Closing database connection
        db.close();
    }

    public Patient getPatient(int id) {
        Log.i(TAG, "MyDatabaseHelper.getPatient ... " + id);

        SQLiteDatabase db = this.getReadableDatabase();

        Cursor cursor = db.query(TABLE_PATIENT, new String[]{COLUMN_ID, COLUMN_FIRSTNAME,
                        COLUMN_LASTNAME, COLUMN_ADDRESS, COLUMN_EMAIL, COLUMN_PASSWORD, COLUMN_PHOTO, COLUMN_FOREIGN_DOCTOR_ID}, COLUMN_ID + "=?",
                new String[]{String.valueOf(id)}, null, null, null, null);
        if (cursor != null)
            cursor.moveToFirst();

        Patient patient = new Patient(
                cursor.getInt(0),
                cursor.getString(1),
                cursor.getString(2),
                cursor.getString(3),
                cursor.getString(4),
                cursor.getString(5),
                cursor.getBlob(6),
                (cursor.getInt(7))
        );
        // return profile
        cursor.close();
        return patient;
    }

    public Medication getMedication(int id){
        Log.i(TAG, "MyDatabaseHelper.getMedication ... " + id);

        SQLiteDatabase db = this.getReadableDatabase();

        Cursor cursor = db.query(TABLE_MEDICATION, new String[]{COLUMN_ID, COLUMN_NAME, COLUMN_DESCRIPTION, COLUMN_PHOTO}, COLUMN_ID + "=?",
                new String[]{String.valueOf(id)}, null, null, null, null);
        if (cursor != null)
            cursor.moveToFirst();

        Medication medication = new Medication(
                cursor.getInt(0),
                cursor.getString(1),
                cursor.getString(2),
                cursor.getBlob(3));
        // return profile
        cursor.close();
        return medication;
    }

    public Ordonnance getOrdonnance(int id){
        Log.i(TAG, "MyDatabaseHelper.getOrdonnance... " + id);

        SQLiteDatabase db = this.getReadableDatabase();

        Cursor cursor = db.query(TABLE_ORDONNANCE, new String[]{COLUMN_ID, COLUMN_FOREIGN_PATIENT_ID, COLUMN_FOREIGN_MEDICATION_ID, COLUMN_DESCRIPTION, COLUMN_DOSAGE}, COLUMN_ID + "=?",
                new String[]{String.valueOf(id)}, null, null, null, null);
        if (cursor != null)
            cursor.moveToFirst();

        Ordonnance ordonnance = new Ordonnance(
                cursor.getInt(0),
                getPatient(cursor.getInt(1)),
                getMedication(cursor.getInt(2)),
                cursor.getString(3),
                cursor.getString(4)
        );
        cursor.close();
        // return profile
        return ordonnance;
    }

    public Date getDate(int id){
        Log.i(TAG, "MyDatabaseHelper.getDate ... " + id);

        SQLiteDatabase db = this.getReadableDatabase();

    Cursor cursor = db.query(TABLE_DATE, new String[]{COLUMN_ID, COLUMN_DATE, COLUMN_TAKEN, COLUMN_FOREIGN_ORDONNANCE_ID}, COLUMN_ID + "=?",
                new String[]{String.valueOf(id)}, null, null, null, null);
        if (cursor != null)
            cursor.moveToFirst();

        Date date = new Date(
                cursor.getInt(0),
                cursor.getString(1),
                cursor.getInt(2) > 0,
                getOrdonnance(cursor.getInt(3))
        );
        cursor.close();
        // return profile
        return date;
    }

    public Doctor getDoctor(int id) {
        Log.i(TAG, "MyDatabaseHelper.getDoctor ... " + id);

        SQLiteDatabase db = this.getReadableDatabase();

        Cursor cursor = db.query(TABLE_DOCTOR, new String[]{COLUMN_ID, COLUMN_FIRSTNAME,
                        COLUMN_LASTNAME, COLUMN_ADDRESS, COLUMN_EMAIL, COLUMN_PASSWORD, COLUMN_PHOTO}, COLUMN_ID + "=?",
                new String[]{String.valueOf(id)}, null, null, null, null);
        if (cursor != null)
            cursor.moveToFirst();

        Doctor doctor = new Doctor(
                cursor.getInt(0),
                cursor.getString(1),
                cursor.getString(2),
                cursor.getString(3),
                cursor.getString(4),
                cursor.getString(5),
                cursor.getBlob(6)
        );
        // return profile
        cursor.close();
        return doctor;
    }

    public List<Patient> getAllPatients() {
        Log.i(TAG, "MyDatabaseHelper.getAllPatients ... ");

        List<Patient>  patientList = new ArrayList<Patient>();
        // Select All Query
        String selectQuery = "SELECT  * FROM " + TABLE_PATIENT;

        SQLiteDatabase db = this.getWritableDatabase();
        Cursor cursor = db.rawQuery(selectQuery, null);

        // looping through all rows and adding to list
        if (cursor.moveToFirst()) {
            do {
                Patient patient = new Patient(
                        cursor.getInt(0),
                        cursor.getString(1),
                        cursor.getString(2),
                        cursor.getString(3),
                        cursor.getString(4),
                        cursor.getString(5),
                        cursor.getBlob(6),
                        (cursor.getInt(7))
                );
                // Adding profile to list
                patientList.add(patient);


            } while (cursor.moveToNext());
        }
        cursor.close();
        return patientList;
    }

    public List<Doctor> getAllDoctors() {
        Log.i(TAG, "MyDatabaseHelper.getAllDoctors ... ");

        List<Doctor> doctorList = new ArrayList<Doctor>();
        // Select All Query
        String selectQuery = "SELECT  * FROM " + TABLE_DOCTOR;

        SQLiteDatabase db = this.getWritableDatabase();
        Cursor cursor = db.rawQuery(selectQuery, null);

        // looping through all rows and adding to list
        if (cursor.moveToFirst()) {
            do {
                Doctor doctor = new Doctor(
                        cursor.getInt(0),
                        cursor.getString(1),
                        cursor.getString(2),
                        cursor.getString(3),
                        cursor.getString(4),
                        cursor.getString(5),
                        cursor.getBlob(6)
                );
                // Adding profile to list
                doctorList.add(doctor);


            } while (cursor.moveToNext());
        }
        cursor.close();
        return doctorList;
    }


    public List<Medication> getAllMedications(){
        Log.i(TAG, "MyDatabaseHelper.getAllMedications ... ");

        List<Medication>  medicationList = new ArrayList<Medication>();
        // Select All Query
        String selectQuery = "SELECT  * FROM " + TABLE_MEDICATION;

        SQLiteDatabase db = this.getWritableDatabase();
        Cursor cursor = db.rawQuery(selectQuery, null);

        // looping through all rows and adding to list
        if (cursor.moveToFirst()) {
            do {
                Medication medication = new Medication(
                        cursor.getInt(0),
                        cursor.getString(1),
                        cursor.getString(2),
                        cursor.getBlob(3)
                );
                medicationList.add(medication);


            } while (cursor.moveToNext());
        }
        cursor.close();
        return medicationList;
    }

    public List<Ordonnance> getAllOrdonnances(){
        Log.i(TAG, "MyDatabaseHelper.getAllOrdonnances ... ");

        List<Ordonnance>  ordonnanceList = new ArrayList<Ordonnance>();
        // Select All Query
        String selectQuery = "SELECT  * FROM " + TABLE_ORDONNANCE;

        SQLiteDatabase db = this.getWritableDatabase();
        Cursor cursor = db.rawQuery(selectQuery, null);

        // looping through all rows and adding to list
        if (cursor.moveToFirst()) {
            do {
                Ordonnance ordonnance = new Ordonnance(
                        cursor.getInt(0),
                        getPatient(cursor.getInt(1)),
                        getMedication(cursor.getInt(2)),
                        cursor.getString(3),
                        cursor.getString(4)
                );
                ordonnanceList.add(ordonnance);


            } while (cursor.moveToNext());
        }
        cursor.close();
        return ordonnanceList;
    }

    public List<Date> getAllDates(){
        Log.i(TAG, "MyDatabaseHelper.getAllDates ... ");

        List<Date>  dates = new ArrayList<Date>();
        // Select All Query
        String selectQuery = "SELECT  * FROM " + TABLE_DATE;

        SQLiteDatabase db = this.getWritableDatabase();
        Cursor cursor = db.rawQuery(selectQuery, null);

        // looping through all rows and adding to list
        if (cursor.moveToFirst()) {
            do {
                Date date = new Date(
                        cursor.getInt(0),
                        cursor.getString(1),
                        cursor.getInt(2) > 0,
                        getOrdonnance(cursor.getInt(3))
                );
                dates.add(date);


            } while (cursor.moveToNext());
        }
        cursor.close();
        return dates;
    }

    public Patient getPatient(String userName, String password) {
        Log.i(TAG, "MyDatabaseHelper.getPatient ... " + userName);

        SQLiteDatabase db = this.getReadableDatabase();

        Cursor cursor = db.query(TABLE_PATIENT, new String[]{COLUMN_ID, COLUMN_FIRSTNAME,
                        COLUMN_LASTNAME, COLUMN_ADDRESS, COLUMN_EMAIL, COLUMN_PASSWORD, COLUMN_PHOTO, COLUMN_FOREIGN_DOCTOR_ID}, COLUMN_FIRSTNAME + "=?" + " AND " + COLUMN_PASSWORD + "=?" ,
                new String[]{userName, password}, null, null, null, null);
        if (cursor != null)
            cursor.moveToFirst();

        Patient patient = new Patient(
                cursor.getInt(0),
                cursor.getString(1),
                cursor.getString(2),
                cursor.getString(3),
                cursor.getString(4),
                cursor.getString(5),
                cursor.getBlob(6),
                (cursor.getInt(7))
        );
        // return profile
        cursor.close();
        return patient;
    }

    public List<Patient> getAllPatientsOfDoctor(int idDoctor){
        Log.i(TAG, "MyDatabaseHelper.getAllPatientsOfDoctor ... " + idDoctor);

        List<Patient> patientList = new ArrayList<>();
        SQLiteDatabase db = this.getReadableDatabase();

        Cursor cursor = db.query(TABLE_PATIENT, new String[]{COLUMN_ID, COLUMN_FIRSTNAME,
                        COLUMN_LASTNAME, COLUMN_ADDRESS, COLUMN_EMAIL, COLUMN_PASSWORD, COLUMN_PHOTO, COLUMN_FOREIGN_DOCTOR_ID}, COLUMN_FOREIGN_DOCTOR_ID + "=?",
                new String[]{String.valueOf(idDoctor)}, null, null, null, null);
        if (cursor.moveToFirst()){
            do{
                Patient patient = new Patient(
                        cursor.getInt(0),
                        cursor.getString(1),
                        cursor.getString(2),
                        cursor.getString(3),
                        cursor.getString(4),
                        cursor.getString(5),
                        cursor.getBlob(6),
                        (cursor.getInt(7))
                );
                patientList.add(patient);
            } while (cursor.moveToNext());
        }
        cursor.close();
        return patientList;
    }

    public Doctor getDoctor(String userName, String password) {
        Log.i(TAG, "MyDatabaseHelper.getDoctor ... " +userName);

        SQLiteDatabase db = this.getReadableDatabase();

        Cursor cursor = db.query(TABLE_DOCTOR, new String[]{COLUMN_ID, COLUMN_FIRSTNAME,
                        COLUMN_LASTNAME, COLUMN_ADDRESS, COLUMN_EMAIL, COLUMN_PASSWORD, COLUMN_PHOTO}, COLUMN_FIRSTNAME + "=?" + " AND " + COLUMN_PASSWORD + "=?" ,
                new String[]{userName, password}, null, null, null, null);
        if (cursor != null)
            cursor.moveToFirst();

        Doctor doctor = new Doctor(
                cursor.getInt(0),
                cursor.getString(1),
                cursor.getString(2),
                cursor.getString(3),
                cursor.getString(4),
                cursor.getString(5),
                cursor.getBlob(6)
        );
        cursor.close();
        return doctor;
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

    public int getPatientsCount() {
        Log.i(TAG, "MyDatabaseHelper.getProfilesCount ... " );

        String countQuery = "SELECT  * FROM " + TABLE_PATIENT;
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor cursor = db.rawQuery(countQuery, null);

        int count = cursor.getCount();

        cursor.close();

        // return count
        return count;
    }

    public ArrayList<Patient> getAlldoctorsPatients(int idDoctor){
        Log.i(TAG, "MyDatabaseHelper.getAlldoctorsPatients ... " + idDoctor);

        ArrayList<Patient> patientList = new ArrayList<>();
        SQLiteDatabase db = this.getReadableDatabase();

        Cursor cursor = db.query(TABLE_PATIENT, new String[]{COLUMN_ID, COLUMN_FIRSTNAME,
                        COLUMN_LASTNAME, COLUMN_ADDRESS, COLUMN_EMAIL, COLUMN_PASSWORD, COLUMN_PHOTO, COLUMN_FOREIGN_DOCTOR_ID}, COLUMN_FOREIGN_DOCTOR_ID + "=?",
                new String[]{String.valueOf(idDoctor)}, null, null, null, null);
        if (cursor.moveToFirst()){
            do{
                Patient patient = new Patient(
                        cursor.getInt(0),
                        cursor.getString(1),
                        cursor.getString(2),
                        cursor.getString(3),
                        cursor.getString(4),
                        cursor.getString(5),
                        cursor.getBlob(6),
                        (cursor.getInt(7))
                );
                patientList.add(patient);
            } while (cursor.moveToNext());
        }
        cursor.close();
        return patientList;
    }


}
