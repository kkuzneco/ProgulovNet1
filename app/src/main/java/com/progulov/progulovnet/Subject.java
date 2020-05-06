package com.progulov.progulovnet;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.util.Log;
import android.content.ContentValues;
import android.database.Cursor;
import android.os.Bundle;
import android.database.sqlite.SQLiteDatabase;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.view.View;

import java.util.ArrayList;
import java.util.List;

public class Subject extends AppCompatActivity {
   // private long subjectid;
    private String subject_name;
    private List<String> ListOfSubjects;
    private int index = 0;
    ListView subjectList;
    DBHelper dbHelper;
   // private RecyclerView listOfSubject;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        subjectList = (ListView) findViewById(R.id.subjectList);
        setContentView(R.layout.activity_subject);
      //  initRecyclerView();
        ListOfSubjects = new ArrayList<String>();
        ListOfSubjects.add("First");
        ListOfSubjects.add("Second");
        ListOfSubjects.add("Third");

      /*  dbHelper=new DBHelper(this,"",1);
       SQLiteDatabase database = dbHelper.getWritableDatabase();

        Cursor cursor = database.query(DBHelper.TABLE_SUBJECTS,null,null,null,null,null,null);
        if(cursor.moveToFirst()){
            int idIndex = cursor.getColumnIndex(DBHelper.KEY_ID);
            int nameIndex=cursor.getColumnIndex(DBHelper.KEY_NAME);
            do{
                Log.d("mLog", "ID= "+ cursor.getInt(idIndex)+", name - "+cursor.getString(nameIndex));
              //  arrayofSubjects[index]=cursor.getString(nameIndex);
                index++;
            }
            while (cursor.moveToNext());

        }
        else
            Log.d("mLog","0 rows");
        cursor.close();
        dbHelper.close();
        ArrayAdapter<String> adapter = new ArrayAdapter<String>(this, android.R.layout.simple_list_item_1, Subj);
        subjectList.setAdapter(adapter);*/
    }
   /* private void initRecyclerView(){
        listOfSubject = findViewById(R.id.listOfSubjects);
        listOfSubject.setLayoutManager(new LinearLayoutManager(this));
    }*/

}
