package fr.bam.projetfinal.model;

import java.util.List;

public abstract class User {
    private String mFirstName;
    private String mLastName;
    private String mAddress;
    private String mEmail;
    private byte[] mPhoto;

    public static int id = 0;


    public User(String firstName, String lastName, String address, String email, byte[] photo) {
        mFirstName = firstName;
        mLastName = lastName;
        mAddress = address;
        mEmail = email;
        mPhoto = photo;
        id += 1;
    }

    public User(int id, String firstName, String lastName, String address, String email, byte[] photo) {
        mFirstName = firstName;
        mLastName = lastName;
        mAddress = address;
        mEmail = email;
        mPhoto = photo;
        this.id = id;
    }

    public User() {
        String firstName = "";
        String lastName = "";
        String address = "";
        String email = "";
        byte[] photo = new byte[0];
    }

    public String getFirstName() {
        return mFirstName;
    }

    public void setFirstName(String firstName) {
        mFirstName = firstName;
    }

    public String getLastName() {
        return mLastName;
    }

    public void setLastName(String lastName) {
        mLastName = lastName;
    }

    public String getAddress() {
        return mAddress;
    }

    public void setAddress(String address) {
        mAddress = address;
    }

    public String getEmail() {
        return mEmail;
    }

    public void setEmail(String email) {
        mEmail = email;
    }

    public byte[] getPhoto() {
        return mPhoto;
    }

    public void setPhoto(byte[] photo) {
        mPhoto = photo;
    }

    public int getId() {
        return id;
    }
}
