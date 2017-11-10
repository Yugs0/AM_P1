package com.example.hugo.coursintent;

import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import java.util.ArrayList;
import com.example.hugo.coursintent.DbContract.*;

/**
 * Created by Hugo on 07/11/2017.
 */

public class DbHelper extends SQLiteOpenHelper {
    private static final String DATABASE_NAME = "messagerie.db";
    private static final Integer DATABASE_VERSION = 1;

    //Constructor
    public DbHelper(Context context){
        super(context,DATABASE_NAME,null,DATABASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        StringBuilder sb = new StringBuilder();

        sb.append("CREATE TABLE ");
        sb.append(MessageEntries.TABLE_NAME);   //voir imports
        sb.append(" (");
        sb.append(MessageEntries._ID);
        sb.append(" INTEGER PRIMARY KEY AUTOINCREMENT,");
        sb.append(MessageEntries.USERNAME);
        sb.append(" TEXT NOT NULL,");
        sb.append(MessageEntries.MESSAGE);
        sb.append(" TEXT NOT NULL);");

        db.execSQL(sb.toString());
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("DROP TABLE IF EXISTS "+ MessageEntries.TABLE_NAME);
        onCreate(db);
    }

    public ArrayList<String> getAllMessages(SQLiteDatabase db){
        Cursor cursor;

        String query = "SELECT * FROM "+MessageEntries.TABLE_NAME;
        cursor = db.rawQuery(query,null);

        ArrayList<String> result = new ArrayList<String>();
        while(cursor.moveToNext()){
            result.add(
                    cursor.getString(cursor.getColumnIndex(MessageEntries.USERNAME))
                    + " a dit : "
                    + cursor.getString(cursor.getColumnIndex(MessageEntries.MESSAGE))
                    + "\n");    //Le add revient déjà à la ligne à chaque fois, mais je voulais qu'il passe une ligne de plus
        }

        return result;
    }


}
