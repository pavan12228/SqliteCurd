package com.example.ravinderreddy.sqliteauthentication;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

/**
 * Created by rajasekharsanikommu on 12/06/17.
 */

public class DbHandlers extends SQLiteOpenHelper
{
    private static final String DATABASE_NAME = "user.db";
    private static final int DATABASE_VERSION = 1;
    public static final String DATABASE_TABLE_NAME = "user";
    public static final String KEY_ROWID = "_id";
    public static final String KEY_FNAME = "firstname";
    public static final String KEY_Email = "email";
    public static final String KEY_DOB = "dob";
    public static final String KEY_MOBILE = "mobile";
    public static final String KEY_PASSWORD = "password";
    public static final String KEY_CONFIRM_PASSWORD = "confirm_password";


    private static final String DATABASE_TABLE_CREATE =
            "CREATE TABLE " + DATABASE_TABLE_NAME + "(" +
                    "_id INTEGER PRIMARY KEY ,"+
                    "firstname TEXT NOT NULL, email TEXT NOT NULL," +
                    " dob TEXT NOT NULL, mobile TEXT NOT NULL, " +
                    "password TEXT NOT NULL, confirm_password TEXT NOT NULL);";



    public DbHandlers(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL(DATABASE_TABLE_CREATE);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        String DROP_TABLE = "DROP TABLE IF EXISTS"+DATABASE_TABLE_NAME;
        db.execSQL(DROP_TABLE);
        onCreate(db);
    }



    public Cursor retrieveData(){
        String selectQuery = "SELECT  * FROM " +DATABASE_TABLE_NAME;
        SQLiteDatabase db=this.getReadableDatabase();
        Cursor cursor=db.rawQuery(selectQuery,null);
        return cursor;
    }



    public void insert(ModelClass modelClass) {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put(KEY_FNAME,modelClass.getName());
        contentValues.put(KEY_Email,modelClass.getEmail());
        contentValues.put(KEY_MOBILE,modelClass.getMobilenum());
        contentValues.put(KEY_DOB,modelClass.getDob());
        contentValues.put(KEY_PASSWORD,modelClass.getPwd());
        contentValues.put(KEY_CONFIRM_PASSWORD,modelClass.getConfirmpwd());
        db.insert(DATABASE_TABLE_NAME,null,contentValues);
        db.close();

        Log.d("cv",""+contentValues);
    }
}
