package com.progulov.progulovnet;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import com.progulov.progulovnet.data.AppContract.AllSubjects;
import java.util.Arrays;
import java.util.Collection;
import java.util.Collections;

import android.content.Intent;
import android.util.Log;
import android.content.ContentValues;
import android.database.Cursor;
import android.os.Bundle;
import android.database.sqlite.SQLiteDatabase;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import java.util.ArrayList;
import java.util.List;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.Button;
import android.widget.ListView;
import android.view.View;

import java.util.ArrayList;
import java.util.List;
import com.progulov.progulovnet.adapter.SubjectAdapter;
import com.progulov.progulovnet.data.AppContract;
import com.progulov.progulovnet.MainActivity;
public class Subject extends AppCompatActivity {
    ListView subjectListView;
    String subjectList[] = new String[9];
     int position = 0;
     int index = 0;
  // SubjectModel[] subjectList = new SubjectModel[9];
    //Collection<SubjectModel> collection =new ArrayList<>();
    DBHelper dbHelper = new DBHelper(this);
    // RecyclerView listOfSubject;
    //SubjectAdapter subAdapter;
    Button saveAttendance ;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_subject);
        saveAttendance = (Button)findViewById(R.id.button);
      //  initRecyclerView();//сщздаем recycle view
        subjectListView = (ListView)findViewById(R.id.subjectListView);
        subjectListView.setChoiceMode(ListView.CHOICE_MODE_SINGLE);
        SQLiteDatabase db = dbHelper.getWritableDatabase();
          String[] projection = {
                AppContract.AllSubjects._ID,
                AppContract.AllSubjects.COLUMN_NAME,
                AppContract.AllSubjects.COLUMN_DEPARTMENT
        };
     Cursor cursor = db.query(AppContract.AllSubjects.TABLE_NAME, projection, null,null,null,null,null);
     int idColumnIndex=cursor.getColumnIndex(AllSubjects._ID);
    int nameColumnIndex=cursor.getColumnIndex(AllSubjects.COLUMN_NAME);
    int departmentColumnIndex=cursor.getColumnIndex(AllSubjects.COLUMN_DEPARTMENT);
    while (cursor.moveToNext()){
        int currentID = cursor.getInt(idColumnIndex);
        String currentName = cursor.getString(nameColumnIndex);
        String currentDepartment = cursor.getString(departmentColumnIndex);
        Log.d("mLog", "ID= "+ cursor.getInt(idColumnIndex)+", name - "+cursor.getString(nameColumnIndex));
        subjectList[index]=new String();
        subjectList[index]=currentName;
        //subjectList[index].department =currentDepartment;
        //Log.d("mLog", "ID= "+ index+", name - "+subjectList[index].name);
        index++;

    }
        cursor.close();
        dbHelper.close();
        db.close();
        ArrayAdapter<String> adapter = new ArrayAdapter<>(this,
                android.R.layout.simple_list_item_checked, subjectList);
        subjectListView.setAdapter(adapter);

        subjectListView.setOnItemClickListener(new OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                position = i;
                Log.d("mLog", "ID= " + position+ ", name - " );
            }
        });
    //    loadSubjects();

    }
    public void Save(View v){
        Intent intent = new Intent(Subject.this, LessonAdd.class);

        String subjectName = subjectList[position];
        intent.putExtra("name", subjectName);
        setResult(RESULT_OK,intent);
        finish();

    }
  /* private void initRecyclerView(){
        listOfSubject = findViewById(R.id.listOfSubjects);//привязка из лэйаут
        listOfSubject.setLayoutManager(new LinearLayoutManager(this));//менеджер
        subAdapter = new SubjectAdapter();
        listOfSubject.setAdapter(subAdapter);
    }
    private void loadSubjects() {

        Collection<SubjectModel> subjects =  getSubject();
        Log.d("mLog", "22222 "+  subjects.toString()+ subjectList[1].department);
        subAdapter.setItems(subjects);
    }
   public Collection<SubjectModel> getSubject() {
    //    Collections.addAll(collection, subjectList);
      // return collection;
   //     Log.d("mLog", "11111 "+  subjectList.toString()+ subjectList[0].department);
       //Log.d("mLog", "ID= "+ 1+", name - "+subjectList[0].name);
           Log.d("mLog", "11111 "+  subjectList[1].name+ subjectList[1].department);
     return Arrays.asList(subjectList);
       new SubjectModel("История", "ОИ"),
        new SubjectModel("Технологии производства ПО", "ИМО"),
        new SubjectModel("Web-технологии 2", "ИМО"),
        new SubjectModel("Право", "право"),
        new SubjectModel("Моделирование ПО", "ИМО"),
        new SubjectModel("Криптографические средства защиты информации", "ПМиК"),
        new SubjectModel("Методы проектирования ПО", "ИМО"),
        new SubjectModel("Программирование в системе 1С", "ПМиК"),
        new SubjectModel("Офисные технологии", "ПМиК"),
        new SubjectModel("Языки программирования и методы трансляции", "ИМО")
        );
    }*/
}


