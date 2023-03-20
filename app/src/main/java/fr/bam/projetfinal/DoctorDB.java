package fr.bam.projetfinal;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

import androidx.annotation.Nullable;

import java.util.ArrayList;
import java.util.List;

public class DoctorDB extends SQLiteOpenHelper {

    private static final String TAG = "SQLite";
    private static final String TABLE_PROFILE = "Profile";

    private static final String COLUMN_PROFILE_ID = "Profile_Id";
    private static final String COLUMN_PROFILE_FIRSTNAME = "Profile_FirstName";
    private static final String COLUMN_PROFILE_LAST_NAME = "Profile_LastName";

    private static final String COLUMN_PROFILE_ADDRESS = "Profile_Address";

    private static final String COLUMN_PROFILE_PHOTO = "Profile_Photo";


    public DoctorDB (@Nullable Context context) {
        super(context, "Note", null, 1);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        Log.i(TAG, "MyDatabaseHelper.onCreate ... ");
        // Script.
        String script = "CREATE TABLE " + TABLE_PROFILE + "("
                + COLUMN_PROFILE_ID + " INTEGER PRIMARY KEY," + COLUMN_PROFILE_FIRSTNAME + " TEXT,"
                + COLUMN_PROFILE_LAST_NAME + " TEXT," + COLUMN_PROFILE_ADDRESS + " TEXT," + COLUMN_PROFILE_PHOTO + " BLOB" + ")";
        // Execute Script.
        db.execSQL(script);
    }


    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int i, int i1) {

    }

    public void addProfile(Profile profile) {
        Log.i(TAG, "MyDatabaseHelper.addProfile ... " + profile.getFirstName());

        SQLiteDatabase db = this.getWritableDatabase();

        ContentValues values = new ContentValues();
        values.put(COLUMN_PROFILE_FIRSTNAME, profile.getFirstName());
        values.put(COLUMN_PROFILE_LAST_NAME, profile.getLastName());
        values.put(COLUMN_PROFILE_ADDRESS, profile.getAddress());
        values.put(COLUMN_PROFILE_PHOTO, profile.getPhoto());

        // Inserting Row
        db.insert(TABLE_PROFILE, null, values);

        // Closing database connection
        db.close();
    }

    public Profile getProfile(int id) {
        Log.i(TAG, "MyDatabaseHelper.getProfile ... " + id);

        SQLiteDatabase db = this.getReadableDatabase();

        Cursor cursor = db.query(TABLE_PROFILE, new String[]{COLUMN_PROFILE_ID, COLUMN_PROFILE_FIRSTNAME,
                        COLUMN_PROFILE_LAST_NAME, COLUMN_PROFILE_ADDRESS, COLUMN_PROFILE_PHOTO}, COLUMN_PROFILE_ID + "=?",
                new String[]{String.valueOf(id)}, null, null, null, null);
        if (cursor != null)
            cursor.moveToFirst();

        Profile profile = new Profile(cursor.getInt(0), cursor.getString(1),
                cursor.getString(2), cursor.getString(3), cursor.getBlob(4));
        // return profile
        return profile;
    }

    public List<Profile> getAllProfiles() {
        Log.i(TAG, "MyDatabaseHelper.getAllProfiles ... ");

        List<Profile> profileList = new ArrayList<Profile>();
        // Select All Query
        String selectQuery = "SELECT  * FROM " + TABLE_PROFILE;

        SQLiteDatabase db = this.getWritableDatabase();
        Cursor cursor = db.rawQuery(selectQuery, null);

        // looping through all rows and adding to list
        if (cursor.moveToFirst()) {
            do {
                Profile profile = new Profile(cursor.getInt(0), cursor.getString(1), cursor.getString(2), cursor.getString(3), cursor.getBlob(4));
                // Adding profile to list
                profileList.add(profile);


            } while (cursor.moveToNext());
        }
        return profileList;
    }

    public int getProfilesCount() {
        Log.i(TAG, "MyDatabaseHelper.getProfilesCount ... " );

        String countQuery = "SELECT  * FROM " + TABLE_PROFILE;
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor cursor = db.rawQuery(countQuery, null);

        int count = cursor.getCount();

        cursor.close();

        // return count
        return count;
    }

}
