package fr.bam.projetfinal.model;

/**
 * the date is a String in this format : "yyyy-MM-dd"
 */
public class Ordonnance {
    private int mPatientID;
    private int mMedicationID;
    private String mDosage;

    private String mDescription;


    public static int id;


    public Ordonnance(int patientID, int medicationID, String description, String dosage) {
        mPatientID = patientID;
        mMedicationID = medicationID;
        mDosage = dosage;
        mDescription = description;
    }

    public Ordonnance(int id, int patientID, int medicationID, String description, String dosage) {
        patientID = patientID;
        mMedicationID = medicationID;
        mDosage = dosage;
        this.id = id;
        mDescription = description;
    }



    public static int getId() {
        return id;
    }

    public int getPatientID() {
        return mPatientID;
    }

    public void setPatientID(int patient) {
        mPatientID = patient;
    }

    public int getMedicationID() {
        return mMedicationID;
    }

    public void setMedication(int medication) {
        mMedicationID = medication;
    }


    public String getDosage() {
        return mDosage;
    }

    public void setDosage(String dosage) {
        mDosage = dosage;
    }

    public String getDescription() {
        return mDescription;
    }

    public void setDescription(String description) {
        mDescription = description;
    }
}
