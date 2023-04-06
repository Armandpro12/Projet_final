package fr.bam.projetfinal.model;

import java.util.ArrayList;
import java.util.List;


public class Patient extends User{
    private Doctor mDoctor;


    public Patient(String firstName, String lastName, String address, String email, String password, byte[] photo, Doctor doctor) {
        super(firstName, lastName, address, email, password, photo);
        mDoctor = doctor;

    }

    public Patient(int id, String firstName, String lastName, String address, String email, String password, byte[] photo, Doctor doctor) {
        super(id, firstName, lastName, address, email, password, photo);
        mDoctor = doctor;

    }

    public Patient() {
        super();
    }


    public Patient(int id, String firstName, String lastName, String address, String email, String password, byte[] photo) {
        super(id, firstName, lastName, address, email, password, photo);
        mDoctor = new Doctor();
    }
    public Doctor getDoctor() {
        return mDoctor;
    }

    public void setDoctor(Doctor doctor) {
        mDoctor = doctor;
    }


}
