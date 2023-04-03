package fr.bam.projetfinal.model;

import java.util.ArrayList;
import java.util.List;


public class Patient extends User{
    private Doctor mDoctor;
    private List<Dosage> mDosages;

    public Patient(String firstName, String lastName, String address, String email, byte[] photo, Doctor doctor, List<Dosage> dosages) {
        super(firstName, lastName, address, email, photo);
        mDoctor = doctor;
        mDosages = dosages;
    }

    public Patient(int id, String firstName, String lastName, String address, String email, byte[] photo, Doctor doctor, List<Dosage> dosages) {
        super(id, firstName, lastName, address, email, photo);
        mDoctor = doctor;
        mDosages = dosages;
    }

    public Patient() {
        super();
    }

    public Patient(int id, String firstName, String lastName, String address, String email, byte[] photo, Doctor doctor) {
        super(id, firstName, lastName, address, email, photo);
        mDoctor = doctor;
    }

    public Patient(int id, String firstName, String lastName, String address, String email, byte[] photo) {
        super(id, firstName, lastName, address, email, photo);
        mDoctor = new Doctor();
    }
    public Doctor getDoctor() {
        return mDoctor;
    }

    public void setDoctor(Doctor doctor) {
        mDoctor = doctor;
    }

    public List<Dosage> getDosages() {
        return mDosages;
    }

    public void setMedications(List<Dosage> dosages) {
        mDosages = dosages;
    }
}
