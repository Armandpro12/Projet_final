package fr.bam.projetfinal.model;

import java.util.List;


public class Patient extends User{
    private int doctorId;

    public Patient(String firstName, String lastName, String address, String email, String password, byte[] photo, int doctorId) {
        super(firstName, lastName, address, email, password, photo);
        this.doctorId = doctorId;
    }

    public Patient(int id, String firstName, String lastName, String address, String email, String password, byte[] photo, int doctorId) {
        super(id, firstName, lastName, address, email, password, photo);
        this.doctorId = doctorId;
    }

    public Patient() {
        super();
    }

    public int getDoctorId() {
        return doctorId;
    }

    public void setDoctor(int doctor) {
        doctorId = doctor;
    }
}
