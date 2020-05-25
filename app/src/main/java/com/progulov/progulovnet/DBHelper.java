package com.progulov.progulovnet;
import com.progulov.progulovnet.data.AppContract;
import com.progulov.progulovnet.data.AppContract.AllSubjects;
import com.progulov.progulovnet.data.AppContract.AllLecturers;
import android.content.ContentValues;
import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import androidx.annotation.Nullable;

import java.util.SortedMap;

public class DBHelper extends SQLiteOpenHelper {
    public static final int DATABASE_VERSION=10;
    public static final String LOG_TAG = DBHelper.class.getSimpleName();
    public static final String DATABASE_NAME="attendance.db";

    public static final String TABLE_SUBJECTS="subjectsDb";
    public static final String TABLE_LECTURERS="lecturersDb";

    public static final String KEY_ID="_id";
    public static final String KEY_NAME="name";
  //  public static final String KEY_DEPARTMENT="depatrment";

    public DBHelper( Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        String SQL_CREATE_SUBJECTS_TABLE = "CREATE TABLE "+AllSubjects.TABLE_NAME+"("+ AllSubjects._ID+" INTEGER PRIMARY KEY AUTOINCREMENT, " + AllSubjects.COLUMN_NAME+" TEXT NOT NULL, "+AllSubjects.COLUMN_DEPARTMENT+" TEXT NOT NULL );";
        db.execSQL(SQL_CREATE_SUBJECTS_TABLE);
        String SQL_CREATE_LECTURERS_TABLE = "CREATE TABLE "+AllLecturers.TABLE_NAME+"("+ AllLecturers._ID+" INTEGER PRIMARY KEY AUTOINCREMENT, " + AllLecturers.COLUMN_NAME+" TEXT NOT NULL, "+AllLecturers.COLUMN_DEPARTMENT+" TEXT NOT NULL );";
        db.execSQL(SQL_CREATE_LECTURERS_TABLE);
        String SQL_CREATE_STUDENTS_TABLE = "CREATE TABLE "+ AppContract.AllStudents.TABLE_NAME+"("+ AppContract.AllStudents._ID+" INTEGER PRIMARY KEY AUTOINCREMENT, " + AppContract.AllStudents.COLUMN_NAME+" TEXT NOT NULL );";
        db.execSQL(SQL_CREATE_STUDENTS_TABLE);
        ContentValues values1 = new ContentValues();
        ContentValues values = new ContentValues();
        values1.put(AllSubjects.COLUMN_NAME, "Технологии производства ПО");
        values1.put(AllSubjects.COLUMN_DEPARTMENT, "ИМО");
        db.insert(AllSubjects.TABLE_NAME,null,values1);
        values1.put(AllSubjects.COLUMN_NAME, "Физика");
        values1.put(AllSubjects.COLUMN_DEPARTMENT, "ФТИ");
        db.insert(AllSubjects.TABLE_NAME,null,values1);
        values1.put(AllSubjects.COLUMN_NAME, "Web-технологии 2");
        values1.put(AllSubjects.COLUMN_DEPARTMENT, "ИМО");
        db.insert(AllSubjects.TABLE_NAME,null,values1);
        values1.put(AllSubjects.COLUMN_NAME, "Криптографические средства защиты информации");
        values1.put(AllSubjects.COLUMN_DEPARTMENT, "ПМиК");
        db.insert(AllSubjects.TABLE_NAME,null,values1);
        values1.put(AllSubjects.COLUMN_NAME, "Программирование в системе 1С");
        values1.put(AllSubjects.COLUMN_DEPARTMENT, "ПМиК");
        db.insert(AllSubjects.TABLE_NAME,null,values1);
        values1.put(AllSubjects.COLUMN_NAME, "Право");
        values1.put(AllSubjects.COLUMN_DEPARTMENT, "ЭП");
        db.insert(AllSubjects.TABLE_NAME,null,values1);
        values1.put(AllSubjects.COLUMN_NAME, "Моделирование ПО");
        values1.put(AllSubjects.COLUMN_DEPARTMENT, "ИМО");
        db.insert(AllSubjects.TABLE_NAME,null,values1);
        values1.put(AllSubjects.COLUMN_NAME, "Методы проектирования ПО");
        values1.put(AllSubjects.COLUMN_DEPARTMENT, "ИМО");
        db.insert(AllSubjects.TABLE_NAME,null,values1);
        values1.put(AllSubjects.COLUMN_NAME, "Языки программирования и методы трансляции");
        values1.put(AllSubjects.COLUMN_DEPARTMENT, "ИМО");
        db.insert(AllSubjects.TABLE_NAME,null,values1);


        values1.put(AllLecturers.COLUMN_NAME, "Кулаков Кирилл Александрович");
        values1.put(AllLecturers.COLUMN_DEPARTMENT, "ИМО");
        db.insert(AllLecturers.TABLE_NAME,null,values1);
        values1.put(AllLecturers.COLUMN_NAME, "Корзун Дмитрий Жоржевич");
        values1.put(AllLecturers.COLUMN_DEPARTMENT, "ИМО");
        db.insert(AllLecturers.TABLE_NAME,null,values1);
        values1.put(AllLecturers.COLUMN_NAME, "Воронов Роман Владимирович");
        values1.put(AllLecturers.COLUMN_DEPARTMENT, "ПМиК");
        db.insert(AllLecturers.TABLE_NAME,null,values1);
        values1.put(AllLecturers.COLUMN_NAME, "Крупко Наталья Сергеевна");
        values1.put(AllLecturers.COLUMN_DEPARTMENT, "ПМиК");
        db.insert(AllLecturers.TABLE_NAME,null,values1);
        values1.put(AllLecturers.COLUMN_NAME, "Пономарев Вадим Анатольевич");
        values1.put(AllLecturers.COLUMN_DEPARTMENT, "ИМО");
        db.insert(AllLecturers.TABLE_NAME,null,values1);
        values1.put(AllLecturers.COLUMN_NAME, "Чистяков Дмитрий Борисович");
        values1.put(AllLecturers.COLUMN_DEPARTMENT, "ИМО");
        db.insert(AllLecturers.TABLE_NAME,null,values1);
        values1.put(AllLecturers.COLUMN_NAME, "Димитров Вячеслав Михайлович");
        values1.put(AllLecturers.COLUMN_DEPARTMENT, "ИМО");
        db.insert(AllLecturers.TABLE_NAME,null,values1);


        values.put(AppContract.AllStudents.COLUMN_NAME, "Аверков Всеволод");
        db.insert(AppContract.AllStudents.TABLE_NAME,null,values);
        values.put(AppContract.AllStudents.COLUMN_NAME, "Баканов Владимир");
        db.insert(AppContract.AllStudents.TABLE_NAME,null,values);
        values.put(AppContract.AllStudents.COLUMN_NAME, "Горбунова Дарья");
        db.insert(AppContract.AllStudents.TABLE_NAME,null,values);
        values.put(AppContract.AllStudents.COLUMN_NAME, "Жданович Екатерина");
        db.insert(AppContract.AllStudents.TABLE_NAME,null,values);
        values.put(AppContract.AllStudents.COLUMN_NAME, "Зайцев Артем");
        db.insert(AppContract.AllStudents.TABLE_NAME,null,values);
        values.put(AppContract.AllStudents.COLUMN_NAME, "Клименко Владислав");
        db.insert(AppContract.AllStudents.TABLE_NAME,null,values);
        values.put(AppContract.AllStudents.COLUMN_NAME, "Кузнецова Ксения");
        db.insert(AppContract.AllStudents.TABLE_NAME,null,values);
        values.put(AppContract.AllStudents.COLUMN_NAME, "Марков Владислав");
        db.insert(AppContract.AllStudents.TABLE_NAME,null,values);
        values.put(AppContract.AllStudents.COLUMN_NAME, "Мелехов Вячеслав");
        db.insert(AppContract.AllStudents.TABLE_NAME,null,values);
        values.put(AppContract.AllStudents.COLUMN_NAME, "Мотина Вероника");
        db.insert(AppContract.AllStudents.TABLE_NAME,null,values);
        values.put(AppContract.AllStudents.COLUMN_NAME, "Сергеев Руслан");
        db.insert(AppContract.AllStudents.TABLE_NAME,null,values);
        values.put(AppContract.AllStudents.COLUMN_NAME, "Сарбаев Артур");
        db.insert(AppContract.AllStudents.TABLE_NAME,null,values);
        values.put(AppContract.AllStudents.COLUMN_NAME, "Харковчук Артур");
        db.insert(AppContract.AllStudents.TABLE_NAME,null,values);


    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int i, int i1) {
    db.execSQL("drop table if exists "+ AllSubjects.TABLE_NAME);
    db.execSQL("drop table if exists "+ AllLecturers.TABLE_NAME);
        db.execSQL("drop table if exists "+ AppContract.AllStudents.TABLE_NAME);
    onCreate(db);
    }
}
