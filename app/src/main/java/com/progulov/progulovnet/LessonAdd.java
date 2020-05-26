package com.progulov.progulovnet;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import android.app.AlertDialog;
import android.content.ContentValues;
import android.content.DialogInterface;
import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.util.Log;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.TimePicker;
import android.app.DatePickerDialog;
import android.app.TimePickerDialog;
import android.text.format.DateUtils;
import android.view.View;
import android.os.Bundle;
import android.widget.Toast;
import java.util.UUID;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;
import com.google.android.gms.tasks.Task;
import com.google.firebase.FirebaseApp;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.progulov.progulovnet.data.AppContract;

public class LessonAdd extends AppCompatActivity implements
        View.OnClickListener{
    Calendar dateAndTime=Calendar.getInstance();
    SQLiteDatabase db;
    AlertDialog.Builder builder;
    Button setdateTime, setSubject, setLecturer, setStudents, exitAppBut, save;
    int index = 0, index1 = 0;
    boolean[] arrayStud = new boolean[13];
    TextView tv;
    String date;
    String time;
   // boolean[] selects = new boolean[13];
    ProgressBar pb;
    private List<LessonModel> list_lessons = new ArrayList<>();
    FirebaseDatabase mFirebaseDatabase;
    DatabaseReference mDatabaseReference;
    DatabaseReference resultRef;
    public StudentModel[] studentList = new StudentModel[13];
    DBHelper dbHelper = new DBHelper(this);
 //   MainActivity mainActivity;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_lesson_add);
        setdateTime=(Button)findViewById(R.id.dateEdit);
        setSubject=(Button)findViewById(R.id.setSubject);
        setLecturer=(Button)findViewById(R.id.setLecturer);
        setStudents=(Button)findViewById(R.id.setAttendance);
        exitAppBut=(Button)findViewById(R.id.exit);
        tv=(TextView) findViewById(R.id.tv);
        pb = (ProgressBar)findViewById(R.id.progressBar2);
        save = (Button)findViewById(R.id.save) ;
       db = dbHelper.getWritableDatabase();
        arrayStudReset();

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

        setInitialDateTime();
        initFirebase();
        addEventFirebaseListener();
    }

    public void ShowSubjectChoice(View v){
        Intent intent = new Intent(LessonAdd.this,Subject.class);
        startActivityForResult(intent,1);
    }

    public void exitApp(View v){
        setResult(RESULT_OK);
        finish();
    }

    public void ShowLecturerChoice(View v){
        Intent intent = new Intent(LessonAdd.this,Lecturer.class);
        startActivityForResult(intent,2);
    }

    public void ShowStudents(View v){
        Intent intent = new Intent(LessonAdd.this,Students.class);
        intent.putExtra("selects",arrayStud);
        startActivityForResult(intent, 3);
    }

    // отображаем диалоговое окно для выбора даты
    public void setDate(View v) {
        new DatePickerDialog(LessonAdd.this, d,
                dateAndTime.get(Calendar.YEAR),
                dateAndTime.get(Calendar.MONTH),
                dateAndTime.get(Calendar.DAY_OF_MONTH))
                .show();
    }

    // отображаем диалоговое окно для выбора времени
    public void setTime(View v) {
        new TimePickerDialog(LessonAdd.this, t,
                dateAndTime.get(Calendar.HOUR_OF_DAY),
                dateAndTime.get(Calendar.MINUTE), true)
                .show();
    }

    // установка начальных даты и времени
    private void setInitialDateTime() {
        date = DateUtils.formatDateTime(this,  dateAndTime.getTimeInMillis(),DateUtils.FORMAT_SHOW_DATE | DateUtils.FORMAT_SHOW_YEAR);
        time = DateUtils.formatDateTime(this,  dateAndTime.getTimeInMillis(),DateUtils.FORMAT_SHOW_TIME);
        setdateTime.setText(DateUtils.formatDateTime(this,
                dateAndTime.getTimeInMillis(),
                DateUtils.FORMAT_SHOW_DATE | DateUtils.FORMAT_SHOW_YEAR
                        | DateUtils.FORMAT_SHOW_TIME));
    }

    // установка обработчика выбора времени
    TimePickerDialog.OnTimeSetListener t=new TimePickerDialog.OnTimeSetListener() {
        public void onTimeSet(TimePicker view, int hourOfDay, int minute) {
            dateAndTime.set(Calendar.HOUR_OF_DAY, hourOfDay);
            dateAndTime.set(Calendar.MINUTE, minute);
            setInitialDateTime();
        }
    };

    // установка обработчика выбора даты
    DatePickerDialog.OnDateSetListener d=new DatePickerDialog.OnDateSetListener() {
        public void onDateSet(DatePicker view, int year, int monthOfYear, int dayOfMonth) {
            dateAndTime.set(Calendar.YEAR, year);
            dateAndTime.set(Calendar.MONTH, monthOfYear);
            dateAndTime.set(Calendar.DAY_OF_MONTH, dayOfMonth);
            setInitialDateTime();
            setTime(setdateTime);
        }
    };

    @Override
    public void onClick(View view) {

    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if(requestCode==2) {
            String a = data.getStringExtra("name");
            Log.d("mLog", a);
            setLecturer.setText(a);
        }
        if(requestCode==1){
            String a = data.getStringExtra("name");
            Log.d("mLog", a);
            setSubject.setText(a);
        }
        if(requestCode==3){
            arrayStud = data.getBooleanArrayExtra("selects");//здесь данные о посещаемости
        }
    }

    private void addEventFirebaseListener() {
        pb.setVisibility(View.VISIBLE);
        save.setVisibility(View.INVISIBLE);
        setdateTime.setVisibility(View.INVISIBLE);
                setSubject.setVisibility(View.INVISIBLE);
                setLecturer.setVisibility(View.INVISIBLE);
        setStudents.setVisibility(View.INVISIBLE);
                exitAppBut.setVisibility(View.INVISIBLE);
        mDatabaseReference.child("lecturers")
                .addValueEventListener(new ValueEventListener() {
                                        //если данные в БД меняются
                    @Override
                    public void onDataChange(DataSnapshot dataSnapshot) {
                        if (list_lessons.size() > 0) {
                            list_lessons.clear();
                        }
                        index = list_lessons.size();
                        //проходим по всем записям и помещаем их в list_users в виде класса User
                        for (DataSnapshot postSnapshot : dataSnapshot.getChildren()) {
                            LessonModel lesson = postSnapshot.getValue(LessonModel.class);
                            list_lessons.add(lesson);
                        }
                        pb.setVisibility(View.INVISIBLE);
                        setdateTime.setVisibility(View.VISIBLE);
                        setSubject.setVisibility(View.VISIBLE);
                        setLecturer.setVisibility(View.VISIBLE);
                        setStudents.setVisibility(View.VISIBLE);
                        exitAppBut.setVisibility(View.VISIBLE);
                        save.setVisibility(View.VISIBLE);
                        //публикуем данные в ListView
                      //  ListViewAdapter adapter = new ListViewAdapter(Lecturer.this, list_lecturer);
                        //lecturerListView.setAdapter(adapter);
                        //убираем View загрузки
                        // circular_progress.setVisibility(View.INVISIBLE);
                        //lecturerListView.setVisibility(View.VISIBLE);
                    }

                    @Override
                    public void onCancelled(DatabaseError databaseError) {

                    }
                });
    }

    private void initFirebase() {
        //инициализируем наше приложение для Firebase согласно параметрам в google-services.json
        // (google-services.json - файл, с настройками для firebase, кот. мы получили во время регистрации)
        FirebaseApp.initializeApp(this);
        //получаем точку входа для базы данных
         mFirebaseDatabase = FirebaseDatabase.getInstance();
        //получаем ссылку для работы с базой данных
         mDatabaseReference = mFirebaseDatabase.getReference();
         resultRef = mDatabaseReference.child("lessons");
    }

    public void save(View v){
         builder = new AlertDialog.Builder(this);
         builder.setMessage("Создать занятие?");
     // builder.setTitle("Внимание");
        builder.setCancelable(false);
        builder.setPositiveButton("ДА", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {
                createLesson();
                arrayStudReset();
            }
        });
        builder.setNegativeButton("ОТМЕНА", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {
                dialogInterface.cancel();
            }
        });
        AlertDialog alertDialog = builder.create();
        alertDialog.show();
        //dialog.setTitle("Добавить занятие?");
    }

    private void createLesson() {
        //создаем элемент класса User


        LessonModel lesson = new LessonModel(UUID.randomUUID().toString(), setSubject.getText().toString(), setLecturer.getText().toString(), date,time);
        //сохраняем данные в базе данных Firebase по пути users -> UUID_User
        for(index = 0;index<13;index++) {
            AttendanceModel attendance = new AttendanceModel(UUID.randomUUID().toString(), lesson.getid(), studentList[index].name, arrayStud[index]);
            mDatabaseReference.child("attendance").child(attendance.getid()).setValue(attendance);

        }
        Task t =   mDatabaseReference.child("lessons").child(lesson.getid()).setValue(lesson);
        ContentValues values = new ContentValues();
        values.put(AppContract.AllLessons._ID, lesson.getid());
        values.put(AppContract.AllLessons.COLUMN_DATE, lesson.date);
        values.put(AppContract.AllLessons.COLUMN_TIME, lesson.time);
        values.put(AppContract.AllLessons.COLUMN_LECTURER, lesson.lecturer_name);
        values.put(AppContract.AllLessons.COLUMN_SUBJECT, lesson.subject_name);
        db.insert(AppContract.AllLessons.TABLE_NAME,null,values);
        mDatabaseReference = FirebaseDatabase.getInstance().getReference("lessons/"+lesson.getid());

              if(mDatabaseReference!=null){
                  clearEditText();
                  mDatabaseReference = mFirebaseDatabase.getReference();
                  Toast.makeText(LessonAdd.this, "Занятие успешно добавлено",
                          Toast.LENGTH_SHORT).show();
              }
              else {
                  Toast.makeText(LessonAdd.this, "Произошла ошибка",
                          Toast.LENGTH_SHORT).show();
              }

        onBackPressed();
    }

    void arrayStudReset(){
        for(index1=0;index1<13; index1++)
            arrayStud[index1]= false;
    }

    void clearEditText() {
        setInitialDateTime();
        setSubject.setText("Выберите предмет");
        setLecturer.setText("Выберите преподавателя");
    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();
        Intent intent = new Intent(LessonAdd.this, ShowLessons.class);
        setResult(RESULT_OK);
        finish();
    }
}
