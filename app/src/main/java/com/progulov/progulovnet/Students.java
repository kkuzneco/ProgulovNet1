package com.progulov.progulovnet;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;

import com.progulov.progulovnet.adapter.StudentAdapter;

import java.util.Arrays;
import java.util.Collection;

public class Students extends AppCompatActivity {

    private RecyclerView listOfStudents;
    StudentAdapter studAdapter;
    DBHelper dbHelper;
    private int index = 0;
    public StudentModel[] lecturerList = new StudentModel[20];
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_students);
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
        return Arrays.asList(
                new StudentModel("Аверков Всеволод Андреевич"),
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
        );
    }
}
