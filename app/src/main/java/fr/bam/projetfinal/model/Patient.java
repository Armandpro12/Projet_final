package fr.bam.projetfinal.model;

import java.util.ArrayList;
import java.util.List;


public class Patient extends User{
    private Doctor mDoctor;
    private List<Medication> mMedications;

    public Patient(String firstName, String lastName, String address, String email, byte[] photo, Doctor doctor, List<Medication> medications) {
        super(firstName, lastName, address, email, photo);
        mDoctor = doctor;
        mMedications = medications;
    }

    public Patient(int id, String firstName, String lastName, String address, String email, byte[] photo, Doctor doctor, List<Medication> medications) {
        super(id, firstName, lastName, address, email, photo);
        mDoctor = doctor;
        mMedications = medications;
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

    public List<Medication> getMedications() {
        return mMedications;
    }

    public void setMedications(List<Medication> medications) {
        mMedications = medications;
    }
}
