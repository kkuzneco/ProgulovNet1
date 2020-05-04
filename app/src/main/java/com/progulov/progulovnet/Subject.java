package com.progulov.progulovnet;

import androidx.appcompat.app.AppCompatActivity;
import java.util.List;

import android.os.Bundle;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
public class Subject extends AppCompatActivity {
    private long subjectid;
    private String subject_name;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_subject);

    }

}
