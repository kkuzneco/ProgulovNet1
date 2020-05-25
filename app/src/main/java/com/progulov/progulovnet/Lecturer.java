package com.progulov.progulovnet;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Context;
import android.content.Intent;
import android.database.sqlite.SQLiteDatabase;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AbsListView;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;
import android.widget.NumberPicker;
import android.widget.TextView;
import android.widget.AdapterView.OnItemClickListener;
import android.view.View.OnScrollChangeListener;
import android.widget.AdapterView.OnItemSelectedListener;

import com.google.firebase.FirebaseApp;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.progulov.progulovnet.adapter.ListViewAdapter;
import com.progulov.progulovnet.adapter.SubjectAdapter;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.List;

import com.progulov.progulovnet.adapter.LecturerAdapter;
import com.progulov.progulovnet.data.AppContract;


public class Lecturer extends AppCompatActivity  {
    Button setLecturer;

 //private RecyclerView listOfLecturers;
   ListView lecturerListView;
int position = -1;
 //   LecturerAdapter lecAdapter;
 //private static final LecturerModel[] lecturerList = new LecturerModel[7];
    public String[] lecturerList = new String[7];
    DBHelper dbHelper = new DBHelper(this);
    private int index = 0;
  //  FirebaseDatabase mFirebaseDatabase;
   // DatabaseReference mDatabaseReference;
    private List<LecturerModel> list_lecturer = new ArrayList<>();
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_lecturer);
        ListView lecturerListView = findViewById(R.id.lecturerListVew);
        setLecturer = (Button) findViewById(R.id.chooseLecturer);
      //  initFirebase();
      //  addEventFirebaseListener();

        lecturerListView.setChoiceMode(ListView.CHOICE_MODE_SINGLE);
        SQLiteDatabase db = dbHelper.getWritableDatabase();
        String[] projection = {
                AppContract.AllLecturers._ID,
                AppContract.AllLecturers.COLUMN_NAME,
                AppContract.AllLecturers.COLUMN_DEPARTMENT
        };
        Cursor cursor = db.query(AppContract.AllLecturers.TABLE_NAME, projection, null, null, null, null, null);
        int idColumnIndex = cursor.getColumnIndex(AppContract.AllLecturers._ID);
        int nameColumnIndex = cursor.getColumnIndex(AppContract.AllLecturers.COLUMN_NAME);
        int departmentColumnIndex = cursor.getColumnIndex(AppContract.AllLecturers.COLUMN_DEPARTMENT);
        while (cursor.moveToNext()) {
            int currentID = cursor.getInt(idColumnIndex);
            String currentName = cursor.getString(nameColumnIndex);
            String currentDepartment = cursor.getString(departmentColumnIndex);
            Log.d("mLog", "ID= " + cursor.getInt(idColumnIndex) + ", name - " + cursor.getString(nameColumnIndex));
            lecturerList[index] = new String();
            lecturerList[index] = currentName;

            // lecturerList[index].department =currentDepartment;
            //Log.d("mLog", "ID= "+ index+", name - "+lecturerList[index].name);
            index++;

        }

        ArrayAdapter<String> adapter = new ArrayAdapter<>(this,
                android.R.layout.simple_list_item_checked, lecturerList);
        lecturerListView.setAdapter(adapter);
        cursor.close();
        dbHelper.close();

      lecturerListView.setOnItemClickListener(new OnItemClickListener() {
          @Override
          public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
              position = i;
              Log.d("mLog", "ID= " + position+ ", name - " );
          }
      });

        lecturerListView.setOnScrollListener(new AbsListView.OnScrollListener() {
            @Override
            public void onScrollStateChanged(AbsListView absListView, int i) {

            }

            @Override
            public void onScroll(AbsListView absListView, int i, int i1, int i2) {

            }
        });


    }
   /* private void addEventFirebaseListener() {
        //показываем View загрузки

        lecturerListView.setVisibility(View.INVISIBLE);

        mDatabaseReference.child("lecturers")
                .addValueEventListener(new ValueEventListener() {
                    //если данные в БД меняются
                    @Override
                    public void onDataChange(DataSnapshot dataSnapshot) {
                        if (list_lecturer.size() > 0) {
                            list_lecturer.clear();
                        }
                        //проходим по всем записям и помещаем их в list_users в виде класса User
                        for (DataSnapshot postSnapshot : dataSnapshot.getChildren()) {
                            LecturerModel lecturer = postSnapshot.getValue(LecturerModel.class);
                            list_lecturer.add(lecturer);
                        }
                        //публикуем данные в ListView
                        ListViewAdapter adapter = new ListViewAdapter(Lecturer.this, list_lecturer);
                        lecturerListView.setAdapter(adapter);
                        //убираем View загрузки
                       // circular_progress.setVisibility(View.INVISIBLE);
                       lecturerListView.setVisibility(View.VISIBLE);
                    }

                    @Override
                    public void onCancelled(DatabaseError databaseError) {

                    }
                });
    }*/
    private void initFirebase() {
        //инициализируем наше приложение для Firebase согласно параметрам в google-services.json
        // (google-services.json - файл, с настройками для firebase, кот. мы получили во время регистрации)
        FirebaseApp.initializeApp(this);
        //получаем точку входа для базы данных
    //    mFirebaseDatabase = FirebaseDatabase.getInstance();
        //получаем ссылку для работы с базой данных
      //  mDatabaseReference = mFirebaseDatabase.getReference();
    }


    public void save(View v){
        Intent intent = new Intent(Lecturer.this, LessonAdd.class);

        String lecturerName = lecturerList[position];
        intent.putExtra("name", lecturerName);
        setResult(RESULT_OK,intent);
        finish();

    }


    }
