package fr.bam.projetfinal.model;

public class Medication {

    public  int id;
    private String mName;
    private String mDescription;

    private byte[] mPhoto;

    public Medication(String name, String description, byte[] photo){
        mName = name;
        mDescription = description;
        mPhoto = photo;
    }

    public Medication(int id, String name, String description, byte[] photo){
        mName = name;
        mDescription = description;
        mPhoto = photo;
        this.id = id;
    }

    public Medication() {
        super();
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

    public int getId() {
        return id;
    }

    public byte[] getPhoto() {
        return mPhoto;
    }

    public void setPhoto(byte[] photo) {
        mPhoto = photo;
    }


    public String toString(){
        return mName;
    }
}
