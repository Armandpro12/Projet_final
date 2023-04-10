package fr.bam.projetfinal.model;

public class Date {
    public static int id;


    private String mDate;
    private boolean mIsTaken;

    private int mOrdonnanceID;

    /**
     * type of date : 'yyyy-MM-dd HH:mm:ss'
     */
    public Date(String date, boolean isTaken, int ordonnanceID) {
        mDate = date;
        mIsTaken = isTaken;
        mOrdonnanceID = ordonnanceID;
    }
    /**
     * type of date : 'yyyy-MM-dd HH:mm:ss'
     */
    public Date(int id, String date, boolean isTaken, int ordonnanceID) {
        mDate = date;
        mIsTaken = isTaken;
        mOrdonnanceID = ordonnanceID;
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

    public int getOrdonnanceID() {
        return mOrdonnanceID;
    }

    public void setOrdonnance(int ordonnance) {
        this.mOrdonnanceID = ordonnance;
    }
}
