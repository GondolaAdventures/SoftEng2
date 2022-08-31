package com.comeng.lbt;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

public class DBHelper extends SQLiteOpenHelper {
    public DBHelper(Context context) {
        super(context, "books.db", null, 1);
    }

    @Override
    public void onCreate(SQLiteDatabase DB) {
        DB.execSQL("create Table Bookdetails(bookid INT primary key, booktitle TEXT, bookauthor TEXT)");
    }

    @Override
    public void onUpgrade(SQLiteDatabase DB, int oldVersion, int newVersion) {
        DB.execSQL("drop Table if exists Bookdetails");
    }

    public Boolean insertbookdata(String booktitle, String bookauthor) {
        SQLiteDatabase DB = this.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put("booktitle", booktitle);
        contentValues.put("bookauthor", bookauthor);
        long result = DB.insert("Bookdetails", null, contentValues);
        if(result == -1) {
            return false;
        } else {
            return true;
        }
    }

    // updatebookdata

    // deletebookdata

    public Cursor getdata () {
        SQLiteDatabase DB = this.getWritableDatabase();
        Cursor cursor = DB.rawQuery("Select * from Bookdetails", null);
        return cursor;
    }
}
