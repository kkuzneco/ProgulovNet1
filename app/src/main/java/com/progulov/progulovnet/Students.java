package com.progulov.progulovnet;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.database.Cursor;
import android.os.Bundle;

import android.database.sqlite.SQLiteDatabase;
import android.util.Log;
import android.view.View;

import com.progulov.progulovnet.adapter.StudentAdapter;
import com.progulov.progulovnet.data.AppContract;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;

public class Students extends AppCompatActivity {

    private RecyclerView listOfStudents;
    StudentAdapter studAdapter;
    DBHelper dbHelper = new DBHelper(this);
    private int index = 0;
    public StudentModel[] studentList = new StudentModel[13];
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_students);
        initRecyclerView();
        Intent intent = getIntent();
       // studAdapter.selects = intent.getBooleanArrayExtra("selects");
          SQLiteDatabase db = dbHelper.getWritableDatabase();

        String[] projection = {
                AppContract.AllStudents._ID,
                AppContract.AllStudents.COLUMN_NAME

        };
        Cursor cursor = db.query(AppContract.AllStudents.TABLE_NAME, projection, null,null,null,null,null);
        int idColumnIndex=cursor.getColumnIndex(AppContract.AllStudents._ID);
        int nameColumnIndex=cursor.getColumnIndex(AppContract.AllStudents.COLUMN_NAME);

        while (cursor.moveToNext()){
            int currentID = cursor.getInt(idColumnIndex);
            String currentName = cursor.getString(nameColumnIndex);

            Log.d("mLog", "ID= "+ cursor.getInt(idColumnIndex)+", name - "+cursor.getString(nameColumnIndex));
            studentList[index]=new StudentModel("default");
            studentList[index].name = currentName;
            index++;

        }
        cursor.close();
        dbHelper.close();

        loadStudents();
    }
    private void initRecyclerView(){
        listOfStudents = findViewById(R.id.studentsRecycler);//привязка из лэйаут
        listOfStudents.setLayoutManager(new LinearLayoutManager(this));//менеджер
        studAdapter = new StudentAdapter();
        listOfStudents.setAdapter(studAdapter);
    }
    private void loadStudents() {
        Collection<StudentModel> students = getStudents();

        studAdapter.setItems(students);
    }
    public Collection<StudentModel> getStudents() {
        return Arrays.asList(studentList);
           /*     new StudentModel("Аверков Всеволод Андреевич"),
                new StudentModel("Зайцев Артем Алексеевич"),
                new StudentModel("Горбунова Дарья Владимировна"),
                new StudentModel("Мотина Вероника Сергеевна"),
                new StudentModel("Кузнецова Ксения Константиновна"),
                new StudentModel("Жданович Екатерина Сергеевна"),
                new StudentModel("Марков ВладиславВикторович"),
                new StudentModel("Сергеев Руслан Андреевич"),
                new StudentModel("Сарбаев Артур Мухаматович"),
                new StudentModel("Харковчук Артур "),
                new StudentModel("Баканов Владимир "),
                new StudentModel("Мелехов Вячеслав ")
        );*/
    }
    public void save(View v){
        Intent intent = new Intent(Students.this, LessonAdd.class);
     //   ArrayList<Boolean> array = new ArrayList<>();
        Log.d("selectItemEXIT", String.valueOf(studAdapter.selects[0]));
        Log.d("selectItemEXIT", String.valueOf(studAdapter.selects[1]));
        intent.putExtra("selects", studAdapter.selects);
        setResult(RESULT_OK,intent);
        finish();

    }

}
