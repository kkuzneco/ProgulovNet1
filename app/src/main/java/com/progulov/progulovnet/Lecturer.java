package com.progulov.progulovnet;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.util.Log;
import android.widget.Button;

import com.progulov.progulovnet.adapter.SubjectAdapter;

import java.util.Arrays;
import java.util.Collection;
import com.progulov.progulovnet.adapter.LecturerAdapter;

public class Lecturer extends AppCompatActivity {
 //   setLecturer=(Button)findViewById(R.id.chooseLecturer);
 private RecyclerView listOfLecturers;
    LecturerAdapter lecAdapter;
    DBHelper dbHelper;
    private int index = 0;
    public LecturerModel[] lecturerList = new LecturerModel[20];
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_lecturer);
        initRecyclerView();


        dbHelper=new DBHelper(this,"",1);

      //  SQLiteDatabase database = dbHelper.getWritableDatabase();
     //   database.delete(DBHelper.TABLE_LECTURERS,null,null);
       // database.delete(DBHelper.TABLE_SUBJECTS,null,null);
     /*  Cursor cursor = database.query(DBHelper.TABLE_LECTURERS,null,null,null,null,null,null);

         if(cursor.moveToFirst()){
            int idIndex = cursor.getColumnIndex(DBHelper.KEY_ID);
            int nameIndex=cursor.getColumnIndex(DBHelper.KEY_NAME);
            do{
                Log.d("mLog", "ID= "+ cursor.getInt(idIndex)+", name - "+cursor.getString(nameIndex));
                lecturerList[index] = new  LecturerModel("default","ИМО");
                lecturerList[index].name=cursor.getString(nameIndex);
                Log.d("mLog", "subLIST= "+    lecturerList[index].name );
                index++;
            }
            while (cursor.moveToNext());

        }
        else
            Log.d("mLog","0 rows");

        cursor.close();
        dbHelper.close();*/

        loadLecturers();
    }
    private void initRecyclerView(){
        listOfLecturers = findViewById(R.id.lecturerRecycler);//привязка из лэйаут
        listOfLecturers.setLayoutManager(new LinearLayoutManager(this));//менеджер
        lecAdapter = new LecturerAdapter();
        listOfLecturers.setAdapter(lecAdapter);
    }
    private void loadLecturers() {
        Collection<LecturerModel> lecturers = getLecturers();
        lecAdapter.setItems(lecturers);
    }
    public Collection<LecturerModel> getLecturers() {
        return Arrays.asList(
                new LecturerModel("Яковлева Дарья Сергеевна", "ФТИ"),
        new LecturerModel("Кулаков Кирилл Александрович", "ИМО"),
        new LecturerModel("Димитров Вячеслав Михайлович", "ИМО"),
        new LecturerModel("Блаткова Вера Валентиновна", "право"),
        new LecturerModel("Корзун Дмитрий Жоржевич", "ИМО"),
        new LecturerModel("Воронов Роман Владимирович", "ПМиК"),
        new LecturerModel("Бородин Александр Владимирович", "ИМО"),
        new LecturerModel("Марковцева Наталья Сергевна", "ПМиК"),
        new LecturerModel("Чистяков Дмитрий Борисович", "ИМО"),
        new LecturerModel("Рогов Александр Александрович", "ИМО")
        );
    }
}
