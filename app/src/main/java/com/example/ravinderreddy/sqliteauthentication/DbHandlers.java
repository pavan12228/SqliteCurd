package com.example.ravinderreddy.sqliteauthentication;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.DatabaseErrorHandler;
import android.database.SQLException;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;
import android.widget.Toast;


public class DbHandlers extends SQLiteOpenHelper {
    private static final String DATABASE_NAME = "Employee.db";
    private static final int DATABASE_VERSION = 1;
    public static final String DATABASE_TABLE_NAME = "EmployeeTable3";
    public static final String KEY_ROWID = "User_id";
    public static final String KEY_FNAME = "firstname";
    public static final String KEY_Email = "email";
    public static final String KEY_DOB = "dob";
    public static final String KEY_MOBILE = "mobile";
    public static final String KEY_PASSWORD = "password";
    SQLiteDatabase db;

//    private static final String DATABASE_TABLE_CREATE =
//            "CREATE TABLE" + DATABASE_TABLE_NAME + "(" + "User_id INTEGER PRIMARY KEY AUTOINCREMENT NOT NULL ," + "firstname TEXT NOT NULL, email TEXT NOT NULL," +
//                    " dob TEXT NOT NULL, mobile TEXT NOT NULL, " +
//                    "password TEXT NOT NULL);";


    private static final String DATABASE_TABLE_CREATE =
        /*    "CREATE TABLE " + DATABASE_TABLE_NAME + "(" + "User_id INTEGER PRIMARY KEY AUTOINCREMENT ,"+ "firstname TEXT unique, email TEXT ," +
                    " dob TEXT , mobile TEXT , " +
                    "password TEXT );";*/
        "CREATE TABLE " + DATABASE_TABLE_NAME + "(" + "User_id INTEGER  PRIMARY KEY AUTOINCREMENT,"+ "firstname TEXT, email TEXT ," +
                    " dob TEXT , mobile TEXT , " +
                    "password TEXT );";


    public DbHandlers(final Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION, new DatabaseErrorHandler() {
            @Override
            public void onCorruption(SQLiteDatabase dbObj) {
                Toast.makeText(context, ""+dbObj.toString(), Toast.LENGTH_SHORT).show();
                l("DBHelper",dbObj.toString());
            }
        });
    }

    public static void l(String key, String value) {
        Log.d(key,value);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL(DATABASE_TABLE_CREATE);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        String DROP_TABLE = "DROP TABLE IF EXISTS" +DATABASE_TABLE_NAME;
        db.execSQL(DROP_TABLE);
        onCreate(db);
    }

    public void insert(ModelClass modelClass)/* throws SQLException*/ {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put(KEY_FNAME, modelClass.getName());
        contentValues.put(KEY_Email, modelClass.getEmail());
        contentValues.put(KEY_MOBILE, modelClass.getMobilenum());
        contentValues.put(KEY_DOB, modelClass.getDob());
        contentValues.put(KEY_PASSWORD, modelClass.getPwd());
        db.insert(DATABASE_TABLE_NAME, null, contentValues);
        db.close();
        Log.d("cv", "" + contentValues);
    }

    public Cursor retrieveData() throws SQLException {
        String selectQuery = "SELECT  * FROM " +DATABASE_TABLE_NAME;
        SQLiteDatabase db = this.getReadableDatabase();
        return  db.rawQuery(selectQuery, null);
    }


    public void updateRec(int id,String newpwd) throws SQLException {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put(KEY_ROWID, id);
        contentValues.put(KEY_PASSWORD, newpwd);
        db.update(DATABASE_TABLE_NAME, contentValues, KEY_ROWID + "="+id, null);
    }

    public void deleteData(int db_id) {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put(KEY_ROWID, db_id);
        db.delete(DATABASE_TABLE_NAME, KEY_ROWID + "=" + db_id, null);
        db.compileStatement("");
    }
}
