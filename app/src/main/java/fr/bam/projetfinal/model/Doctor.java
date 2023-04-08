package fr.bam.projetfinal.model;

import java.util.ArrayList;
import java.util.List;

public class Doctor extends User{
    public Doctor(String firstName, String lastName, String address, String email, String password, byte[] photo) {
        super(firstName, lastName, address, email, password, photo);
    }

    public Doctor(int id, String firstName, String lastName, String address, String email, String password, byte[] photo) {
        super(id, firstName, lastName, address, email, password, photo);
    }

    public Doctor() {
        super();
    }

}
