package fr.bam.projetfinal.model;

/**
 * the date is a String in this format : "yyyy-MM-dd"
 */
public class Dosage {
    private Patient mPatient;
    private Medication mMedication;
    private String mDate;
    private String mQuantity;

    private boolean mIsTaken;

    public static int id = 0;


    public Dosage(Patient patient, Medication medication, String date, String quantity, boolean isTaken) {
        mPatient = patient;
        mMedication = medication;
        mDate = date;
        mQuantity = quantity;
        mIsTaken = isTaken;
        id++;
    }

    public Dosage(int id, Patient patient, Medication medication, String date, String quantity, boolean isTaken) {
        mPatient = patient;
        mMedication = medication;
        mDate = date;
        mQuantity = quantity;
        mIsTaken = isTaken;
        this.id = id;
    }


    public boolean isTaken() {
        return mIsTaken;
    }

    public void setTaken(boolean taken) {
        mIsTaken = taken;
    }

    public static int getId() {
        return id;
    }

    public Patient getPatient() {
        return mPatient;
    }

    public void setPatient(Patient patient) {
        mPatient = patient;
    }

    public Medication getMedication() {
        return mMedication;
    }

    public void setMedication(Medication medication) {
        mMedication = medication;
    }

    public String getDate() {
        return mDate;
    }

    public void setDate(String date) {
        mDate = date;
    }

    public String getQuantity() {
        return mQuantity;
    }

    public void setQuantity(String quantity) {
        mQuantity = quantity;
    }
}
