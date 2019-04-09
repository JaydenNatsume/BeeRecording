package com.utarlingtonserc.beerecording.Database;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import java.util.ArrayList;

public class DatabaseSQL extends SQLiteOpenHelper {

    private static final String DATABASE = "UTA_STUD_DB";

    private static final String USERINFO = "userinfotable";
    private static final String NOTES = "notestable";


    // Attributes of the userinfotable
    private static final String UID = "uid";
    private static final String FIRSTNAME = "firstname";
    private static final String LASTNAME = "lastname";
    private static final String IDNUMBER = "studentid";
    private static final String EMAIL = "email";
    private static final String COURSE1 = "course1";
    private static final String COURSE2 = "course2";
    private static final String COURSE3 = "course3";
    private static final String PROFESSOR1 = "professor1";
    private static final String PROFESSOR2 = "professor2";
    private static final String PROFESSOR3 = "professor3";
    private static final String STATUS = "status";

    // Attributes of the notestable
    private static final String TITLE = "title";
    private static final String NOTE = "note";


    public DatabaseSQL(Context context) {
        super(context, DATABASE, null, 1);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {

        db.execSQL("CREATE TABLE IF NOT EXISTS userinfotable" + " (" + UID + " TEXT, " + FIRSTNAME + " TEXT, " + LASTNAME + " TEXT, " + IDNUMBER + " TEXT, " + EMAIL + " TEXT, " + COURSE1 + " TEXT, " + COURSE2 + " TEXT, " + COURSE3 + " TEXT, " + PROFESSOR1 + " TEXT, " + PROFESSOR2 + " TEXT, " + PROFESSOR3 + " TEXT, " + STATUS + " TEXT)");
        db.execSQL("CREATE TABLE IF NOT EXISTS notestable" + " (" + TITLE + " TEXT, " + NOTE + " TEXT)");
        //db.execSQL("CREATE TABLE IF NOT EXISTS eventstable" + " (" + PROFESSOR_NAME + " TEXT, " + ASSISTANT_NAME + " TEXT, " + PROFESSOR_OFFICE + " TEXT, " + ASSISTANT_OFFICE + " TEXT, " + PROFESSOR_EMAIL + " TEXT, " + ASSISTANT_EMAIL + " TEXT, " + PROFESSOR_PHONE + " TEXT, " + ASSISTANT_PHONE + " TEXT, " + PROFESSOR_HOURS + " TEXT, " + ASSISTANT_HOURS + " TEXT)");
    }


    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("DROP TABLE IF EXISTS " + USERINFO);
        db.execSQL("DROP TABLE IF EXISTS " + NOTES);
        onCreate(db);
    }


    public void createNote(String title, String note){
        deleteNote(title);
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put(TITLE,title);
        values.put(NOTE,note);
        db.insert(NOTES, null, values);
    }



    public ArrayList<String> fetchNotesTitle() {
        ArrayList<String> myArray= new ArrayList<String>();
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor cur = db.rawQuery("SELECT * FROM "+ NOTES, null);
        cur.moveToFirst();
        while(cur.isAfterLast()==false){
            myArray.add(cur.getString(cur.getColumnIndex(TITLE)));
            cur.moveToNext();
        }
        return myArray;
    }

    public void deleteNote(String title){
        SQLiteDatabase db = this.getWritableDatabase();
        db.delete(NOTES, TITLE + " = ?", new String[]{title});
    }

    public String getNote(String noteTitle){
        String whereClause = TITLE + " = ?";
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor cur = db.query(NOTES, new String[]{NOTE}, whereClause, new String[]{noteTitle}, null, null, null);
        cur.moveToFirst();
        return cur.getString(cur.getColumnIndex(NOTE));
    }
}
