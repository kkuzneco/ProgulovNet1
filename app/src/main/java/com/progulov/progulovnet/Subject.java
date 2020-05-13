package com.progulov.progulovnet;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import java.util.Arrays;
import java.util.Collection;
import java.util.Collections;
import android.util.Log;
import android.content.ContentValues;
import android.database.Cursor;
import android.os.Bundle;
import android.database.sqlite.SQLiteDatabase;
import android.widget.ArrayAdapter;
import java.util.ArrayList;
import java.util.List;
import android.widget.ListView;
import android.view.View;

import java.util.ArrayList;
import java.util.List;
import com.progulov.progulovnet.adapter.SubjectAdapter;
public class Subject extends AppCompatActivity {


    private int index = 0;
   public SubjectModel[] subjectList = new SubjectModel[20];
    Collection<SubjectModel> collection =new ArrayList<>();
    DBHelper dbHelper;
    private RecyclerView listOfSubject;
    SubjectAdapter subAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
      //  subjectList = (ListView) findViewById(R.id.subjectList);
        setContentView(R.layout.activity_subject);
        initRecyclerView();//сщздаем recycle view
    //    LinearLayoutManager llm = new LinearLayoutManager(this);
     //   listOfSubject.setLayoutManager(llm);




 /*     dbHelper=new DBHelper(this,"",1);

       SQLiteDatabase database = dbHelper.getWritableDatabase();

        Cursor cursor = database.query(DBHelper.TABLE_SUBJECTS,null,null,null,null,null,null);
        if(cursor.moveToFirst()){
            int idIndex = cursor.getColumnIndex(DBHelper.KEY_ID);
            int nameIndex=cursor.getColumnIndex(DBHelper.KEY_NAME);
            do{
                Log.d("mLog", "ID= "+ cursor.getInt(idIndex)+", name - "+cursor.getString(nameIndex));
                subjectList[index]=new SubjectModel("default","ИМО");
                subjectList[index].name=cursor.getString(nameIndex);
                Log.d("mLog", "subLIST= "+   subjectList[index].name );
                index++;
            }
            while (cursor.moveToNext());

        }
        else
            Log.d("mLog","0 rows");
        cursor.close();
        dbHelper.close();
*/
        loadSubjects();

    }
   private void initRecyclerView(){
        listOfSubject = findViewById(R.id.listOfSubjects);//привязка из лэйаут
        listOfSubject.setLayoutManager(new LinearLayoutManager(this));//менеджер
        subAdapter = new SubjectAdapter();
        listOfSubject.setAdapter(subAdapter);
    }
    private void loadSubjects() {
        Collection<SubjectModel> subjects = getSubject();
        subAdapter.setItems(subjects);
    }
   public Collection<SubjectModel> getSubject() {
  //      Collections.addAll(collection, subjectList);
   //     Log.d("mLog", "11111 "+  subjectList[0].name+ subjectList[0].department);

       //     Log.d("mLog", "11111 "+  subjectList[1].name+ subjectList[1].department);
     return Arrays.asList(
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
    }
}


