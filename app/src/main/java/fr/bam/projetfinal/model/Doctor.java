package fr.bam.projetfinal.model;

import java.util.ArrayList;
import java.util.List;

public class Doctor extends User{
    List<Patient> mPatients;
    public Doctor(String firstName, String lastName, String address, String email, byte[] photo) {
        super(firstName, lastName, address, email, photo);
        this.mPatients = new ArrayList<>();
    }

    public Doctor() {
        super();
        this.mPatients = new ArrayList<>();
    }

    public Doctor(String firstName, String lastName, String address, String email, byte[] photo, List<Patient> patients) {
        super(firstName, lastName, address, email, photo);
        mPatients = patients;
    }

    public void addPatient(Patient patient){
        mPatients.add(patient);
    }

    public List<Patient> getPatients() {
        return mPatients;
    }

    public void setPatients(List<Patient> patients) {
        mPatients = patients;
    }
}
