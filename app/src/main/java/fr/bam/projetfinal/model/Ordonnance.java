package fr.bam.projetfinal.model;

/**
 * the date is a String in this format : "yyyy-MM-dd"
 */
public class Ordonnance {
    private Patient mPatient;
    private Medication mMedication;
    private String mDosage;

    private String mDescription;


    public static int id;


    public Ordonnance(Patient patient, Medication medication, String description, String dosage) {
        mPatient = patient;
        mMedication = medication;
        mDosage = dosage;
        mDescription = description;
    }

    public Ordonnance(int id, Patient patient, Medication medication, String description, String dosage) {
        mPatient = patient;
        mMedication = medication;
        mDosage = dosage;
        this.id = id;
        mDescription = description;
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
