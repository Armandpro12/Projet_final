package fr.bam.projetfinal.model;

public class Medication {

    public static int id = 0;
    private String mName;
    private String mDescription;
    private String mDosage;
    private String mFrequency;
    private String mStartDate;
    private String mEndDate;

    private boolean mIsTaken;

    private byte[] mPhoto;

    public Medication(String name, String description, String dosage, String frequency, String startDate, String endDate, boolean isTaken, byte[] photo){
        mName = name;
        mDescription = description;
        mDosage = dosage;
        mFrequency = frequency;
        mStartDate = startDate;
        mEndDate = endDate;
        mIsTaken = isTaken;
        mPhoto = photo;
        id += 1;
    }

    public Medication(int id, String name, String description, String dosage, String frequency, String startDate, String endDate, boolean isTaken, byte[] photo){
        mName = name;
        mDescription = description;
        mDosage = dosage;
        mFrequency = frequency;
        mStartDate = startDate;
        mEndDate = endDate;
        mIsTaken = isTaken;
        mPhoto = photo;
        this.id += id;
    }

    public String getName() {
        return mName;
    }

    public void setName(String name) {
        mName = name;
    }

    public String getDescription() {
        return mDescription;
    }

    public void setDescription(String description) {
        mDescription = description;
    }

    public String getDosage() {
        return mDosage;
    }

    public void setDosage(String dosage) {
        mDosage = dosage;
    }

    public String getFrequency() {
        return mFrequency;
    }

    public void setFrequency(String frequency) {
        mFrequency = frequency;
    }

    public String getStartDate() {
        return mStartDate;
    }

    public void setStartDate(String startDate) {
        mStartDate = startDate;
    }

    public String getEndDate() {
        return mEndDate;
    }

    public void setEndDate(String endDate) {
        mEndDate = endDate;
    }

    public boolean isTaken() {
        return mIsTaken;
    }

    public void setTaken(boolean taken) {
        mIsTaken = taken;
    }

    public int getId() {
        return id;
    }

    public byte[] getPhoto() {
        return mPhoto;
    }

    public void setPhoto(byte[] photo) {
        mPhoto = photo;
    }
}
