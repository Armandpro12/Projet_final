package fr.bam.projetfinal.model;

public class Date {
    public static int id ;
    private String mDate;
    private boolean mIsTaken;

    Dosage mDosage;

    public Date(String date, boolean isTaken, Dosage dosage) {
        mDate = date;
        mIsTaken = isTaken;
        mDosage = dosage;
    }

    public Date(int id, String date, boolean isTaken, Dosage dosage) {
        mDate = date;
        mIsTaken = isTaken;
        mDosage = dosage;
        this.id = id;
    }

    public String getDate() {
        return mDate;
    }

    public void setDate(String date) {
        mDate = date;
    }

    public boolean isTaken() {
        return mIsTaken;
    }

    public void setTaken(boolean taken) {
        mIsTaken = taken;
    }

    public Dosage getmDosage() {
        return mDosage;
    }

    public void setmDosage(Dosage mDosage) {
        this.mDosage = mDosage;
    }

    public static int getId() {
        return id;
    }
}
