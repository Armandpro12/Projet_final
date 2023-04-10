package fr.bam.projetfinal.model;

public class Date {
    public  int id;


    private String mDate;
    private boolean mIsTaken;

    private String mTime;
    private int mOrdonnanceID;

    /**
     * type of date : 'yyyy-MM-dd '
     */
    public Date(String date,String time, boolean isTaken, int ordonnanceID) {
        mDate = date;
        mTime = time;
        mIsTaken = isTaken;
        mOrdonnanceID = ordonnanceID;
    }

    public String getTime() {
        return mTime;
    }

    public void setTime(String mTime) {
        this.mTime = mTime;
    }

    /**
     * type of date : 'yyyy-MM-dd'
     */
    public Date(int id, String date,String time, boolean isTaken, int ordonnanceID) {
        mDate = date;
        mTime = time;
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

    public  int getId() {
        return id;
    }

    public int getOrdonnanceID() {
        return mOrdonnanceID;
    }

    public void setOrdonnance(int ordonnance) {
        this.mOrdonnanceID = ordonnance;
    }

    public int getYear() {
        return Integer.parseInt(mDate.substring(0, 4));
    }

    public int getMonth() {
        return Integer.parseInt(mDate.substring(5, 7));
    }

    public int getDay() {
        return Integer.parseInt(mDate.substring(8, 10));
    }

    public int getSecond() {
        return Integer.parseInt(mTime.substring(6, 8));
    }

    public int getMinute() {
        return Integer.parseInt(mTime.substring(3, 5));
    }

    public int getHour() {
        return Integer.parseInt(mTime.substring(0, 2));
    }
}
