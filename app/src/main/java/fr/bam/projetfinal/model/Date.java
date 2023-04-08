package fr.bam.projetfinal.model;

public class Date {
    public static int id;

    /**
     * type of date : 'yyyy-MM-dd HH:mm:ss'
     */
    private String mDate;
    private boolean mIsTaken;

    private Ordonnance mOrdonnance;

    public Date(String date, boolean isTaken, Ordonnance ordonnance) {
        mDate = date;
        mIsTaken = isTaken;
        mOrdonnance = ordonnance;
    }

    public Date(int id, String date, boolean isTaken, Ordonnance ordonnance) {
        mDate = date;
        mIsTaken = isTaken;
        mOrdonnance = ordonnance;
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

    public Ordonnance getOrdonnance() {
        return mOrdonnance;
    }

    public void setOrdonnance(Ordonnance ordonnance) {
        this.mOrdonnance = ordonnance;
    }
}
