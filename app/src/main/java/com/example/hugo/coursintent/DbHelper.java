package com.example.hugo.coursintent;

import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import java.util.ArrayList;

/**
 * Created by Hugo on 07/11/2017.
 */

public class DbHelper extends SQLiteOpenHelper {
    private static final String DATABASE_NAME = "ludus.db";
    private static final Integer DATABASE_VERSION = 1;

    //Constructor
    public DBHelper(Context context){
        super(context,DATABASE_NAME,null,DATABASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        StringBuilder sb = new StringBuilder();

        sb.append("CREATE TABLE ");
        sb.append(StudentEntries.TABLE_NAME);   //voir imports
        sb.append(" (");
        sb.append(StudentEntries._ID);
        sb.append(" INTEGER PRIMARY KEY AUTOINCREMENT,");
        sb.append(StudentEntries.FIRST_NAME);
        sb.append(" TEXT NOT NULL);");

        /*final String SQL_CREATE_WAITLIST_TABLE = "CREATE TABLE " + WaitlistEntry.TABLE_NAME + " (" +
                WaitlistEntry._ID + " INTEGER PRIMARY KEY AUTOINCREMENT," +
                WaitlistEntry.COLUMN_GUEST_NAME + " TEXT NOT NULL, " +
                WaitlistEntry.COLUMN_PARTY_SIZE + " INTEGER NOT NULL, " +
                WaitlistEntry.COLUMN_TIMESTAMP + " TIMESTAMP DEFAULT CURRENT_TIMESTAMP" +
                "); ";*/

        db.execSQL(sb.toString());
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("DROP TABLE IF EXISTS "+ StudentEntries.TABLE_NAME);
        onCreate(db);
    }

    public ArrayList<String> getAllStudents(SQLiteDatabase db){
        Cursor cursor;
        String[] args = {"Albert"};
        String[] columns = {StudentEntries.FIRST_NAME};
        /*cursor = db.query(
                StudentEntries.TABLE_NAME,
                columns,
                StudentEntries.FIRST_NAME + " = ?",
                args,
                null,
                null,
                StudentEntries.FIRST_NAME+" ASC "
        );*/

        //Deuxième possibilité :
        String query = "SELECT * FROM "+StudentEntries.TABLE_NAME+" ORDER BY "+StudentEntries.FIRST_NAME+" ASC";
        cursor = db.rawQuery(query,null);

        ArrayList<String> result = new ArrayList<String>();
        while(cursor.moveToNext()){
            result.add(cursor.getString(cursor.getColumnIndex(StudentEntries.FIRST_NAME)));
        }

        return result;
    }
}
