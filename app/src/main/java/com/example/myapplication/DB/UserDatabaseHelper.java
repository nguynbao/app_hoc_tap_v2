package com.example.myapplication.DB;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

public class UserDatabaseHelper extends SQLiteOpenHelper {
    private static final String DATABASE_NAME = "DB_user";
    private static final int DATABASE_VERSION = 1;
    public UserDatabaseHelper(Context context){
        super(context, DATABASE_NAME, null,DATABASE_VERSION);
    }
    @Override
    public void onCreate(SQLiteDatabase db) {
        Log.d("DB", "onCreate called - creating users table");
        db.execSQL("CREATE TABLE IF NOT EXISTS users (" +
                "id INTEGER PRIMARY KEY AUTOINCREMENT, " +
                "email TEXT UNIQUE, " +
                "password TEXT)");
    }
    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion){
        db.execSQL("DROP TABLE IF EXISTS users");
        onCreate(db);
    }
    public boolean registerUser(String email, String password){
    if (checkUserExists(email)) return false;

    SQLiteDatabase db = this.getWritableDatabase();
    ContentValues values = new ContentValues();
    values.put("email", email);
    values.put("password", password);
    long result = db.insert("users", null, values);
    db.close();
    return result != -1;
    }
    public boolean checkUserExists(String email){
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor cursor = db.rawQuery("SELECT * FROM users WHERE email = ?", new String[]{email});
        boolean exists = cursor.getCount() > 0;
        cursor.close();
        return exists;
    }
    public boolean loginUser(String email, String password){
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor cursor = db.rawQuery(
                "SELECT * FROM users WHERE email = ? AND password = ?",
                new String[]{email, password});
        boolean loginSuccess = cursor.getCount() > 0;
        cursor.close();
        return loginSuccess;
    }

}
