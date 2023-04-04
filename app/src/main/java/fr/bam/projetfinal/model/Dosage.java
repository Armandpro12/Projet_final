package fr.bam.projetfinal.model;

import java.util.List;

/**
 * the date is a String in this format : "yyyy-MM-dd"
 */
public class Dosage {
    private Patient mPatient;
    private Medication mMedication;
    private List<Date> mDates;
    private String mQuantity;


    public static int id;


    public Dosage(Patient patient, Medication medication, List<Date> dates, String quantity) {
        mPatient = patient;
        mMedication = medication;
        mDates = dates;
        mQuantity = quantity;
    }

    public Dosage(int id, Patient patient, Medication medication, List<Date> dates, String quantity) {
        mPatient = patient;
        mMedication = medication;
        mDates = dates;
        mQuantity = quantity;
        this.id = id;
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

    public List<Date> getDates() {
        return mDates;
    }

    public void setDates(List<Date> dates) {
        mDates = dates;
    }

    public String getQuantity() {
        return mQuantity;
    }

    public void setQuantity(String quantity) {
        mQuantity = quantity;
    }
}
