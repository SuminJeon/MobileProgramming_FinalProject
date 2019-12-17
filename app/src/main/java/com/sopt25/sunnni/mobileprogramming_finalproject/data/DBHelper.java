package com.sopt25.sunnni.mobileprogramming_finalproject.data;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteException;
import android.database.sqlite.SQLiteOpenHelper;

public class DBHelper extends SQLiteOpenHelper {

    public DBHelper(Context context, String name, SQLiteDatabase.CursorFactory factory, int version) {
        super(context, name, factory, version);
    }

    @Override
    public void onCreate(SQLiteDatabase sqLiteDatabase) {
        createTable(sqLiteDatabase);
    }

    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int i, int i1) {

    }

    public void createTable(SQLiteDatabase db) {

        String sql = "CREATE TABLE DBTABLE(mandarineCount, date)";

        try {
            db.execSQL(sql);

        } catch (SQLiteException e) {
            e.printStackTrace();
        }
    }

    public void add(SQLiteDatabase db, String inputCount, String inputDate) {
        db.beginTransaction();
        try {
            String sql = "insert into DBTABLE" + "(mandarineCount,date)" + " values('" + inputCount + "','" + inputDate + "')";
            db.execSQL(sql);
            db.setTransactionSuccessful();
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            db.endTransaction();
        }
    }
}
