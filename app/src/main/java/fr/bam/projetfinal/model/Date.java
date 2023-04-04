package fr.bam.projetfinal.model;

public class Date {
    public static int id = 0;
    private String mDate;
    private boolean mIsTaken;

    public Date(String date, boolean isTaken) {
        mDate = date;
        mIsTaken = isTaken;
        id++;
    }

    public Date(int id, String date, boolean isTaken) {
        mDate = date;
        mIsTaken = isTaken;
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

    public static int getId() {
        return id;
    }
}
