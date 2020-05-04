package com.progulov.progulovnet;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import androidx.annotation.Nullable;

public class DBHelper extends SQLiteOpenHelper {
    public static final int DATABASE_VERSION=1;
    public static final String DATABASE_NAME="ProgulovNet";
    public static final String TABLE_SUBJECTS="subjectsDb";


    public static final String KEY_ID="_id";
    public static final String KEY_NAME="name";

    public DBHelper( Context context,  String name, int version) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
    db.execSQL("create table " + TABLE_SUBJECTS + "(" +KEY_ID+" integer primary key,"+KEY_NAME+" text"+")");
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int i, int i1) {
    db.execSQL("drop table if exists "+ TABLE_SUBJECTS);
    onCreate(db);
    }
}
