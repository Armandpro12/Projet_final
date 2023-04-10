package fr.bam.projetfinal.model;

import java.util.Arrays;
import java.util.List;

public abstract class User {
    private String mFirstName;
    private String mLastName;
    private String mAddress;
    private String mEmail;
    private String mPassword;
    private byte[] mPhoto;

    public int id;


    public User(String firstName, String lastName, String address, String email, String password, byte[] photo) {
        mFirstName = firstName;
        mLastName = lastName;
        mAddress = address;
        mEmail = email;
        mPassword = password;
        mPhoto = photo;
    }

    public User(int id, String firstName, String lastName, String address, String email, String password, byte[] photo) {
        mFirstName = firstName;
        mLastName = lastName;
        mAddress = address;
        mEmail = email;
        mPassword = password;
        mPhoto = photo;
        this.id = id;
    }

    public User(String firstName, String lastName, String address, String email, String password) {
        mFirstName = firstName;
        mLastName = lastName;
        mAddress = address;
        mEmail = email;
        mPassword = password;
        mPhoto = new byte[0];
    }

    public User() {
        mFirstName = "";
        mLastName = "";
        mAddress = "";
        mEmail = "";
        mPassword = "";
        mPhoto = new byte[0];
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

    public void setId(int id) {
        this.id = id;
    }

    public String getPassword() {
        return mPassword;
    }

    public void setPassword(String password) {
        mPassword = password;
    }

    @Override
    public String toString() {
        return "User{" +
                "mFirstName='" + mFirstName + '\'' +
                ", mLastName='" + mLastName + '\'' +
                ", mAddress='" + mAddress + '\'' +
                ", mEmail='" + mEmail + '\'' +
                ", mPassword='" + mPassword + '\'' +
                ", mPhoto=" + Arrays.toString(mPhoto) +
                '}';
    }
}
