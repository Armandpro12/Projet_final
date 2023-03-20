package fr.bam.projetfinal.model;

public class Patient extends User{
    Doctor mDoctor;
    public Patient(String firstName, String lastName, String address, String email, byte[] photo, Doctor doctor) {
        super(firstName, lastName, address, email, photo);
        mDoctor = doctor;
    }

    public Patient() {
        super();
    }

    public Patient(String firstName, String lastName, String address, String email, byte[] photo) {
        super(firstName, lastName, address, email, photo);
    }

    public Doctor getDoctor() {
        return mDoctor;
    }

    public void setDoctor(Doctor doctor) {
        mDoctor = doctor;
    }
}
