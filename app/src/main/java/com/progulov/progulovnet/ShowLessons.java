package com.progulov.progulovnet;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.RecyclerView;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.text.format.DateUtils;
import android.app.DatePickerDialog;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.firebase.FirebaseApp;;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.Query;
import com.google.firebase.database.ValueEventListener;
import com.progulov.progulovnet.adapter.LessonsAdapter;
import com.progulov.progulovnet.adapter.ListViewAdapterForLessons;
import com.progulov.progulovnet.data.AppContract;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.Collection;
import java.util.List;

public class ShowLessons extends AppCompatActivity {
    Button date;
    AlertDialog.Builder builder;
    TextView noItems;
    Collection<LessonModel> lessons;
    private RecyclerView listOfLessons;
    LessonsAdapter lesAdapter;
    Calendar dateAndTime=Calendar.getInstance();
    DBHelper dbHelper = new DBHelper(this);
    private List<LessonModel> list_lessons = new ArrayList<>();
    ListView lessonListView;
FloatingActionButton add;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_show_lessons);
        date = (Button) findViewById(R.id.dateEdit);
        lessonListView = findViewById(R.id.lessonListView);
        add = (FloatingActionButton) findViewById(R.id.floatingActionButton);
        noItems =(TextView)findViewById(R.id.noItems) ;
        setInitialDate();


    }

    DatePickerDialog.OnDateSetListener d=new DatePickerDialog.OnDateSetListener() {
        public void onDateSet(DatePicker view, int year, int monthOfYear, int dayOfMonth) {
            dateAndTime.set(Calendar.YEAR, year);
            dateAndTime.set(Calendar.MONTH, monthOfYear);
            dateAndTime.set(Calendar.DAY_OF_MONTH, dayOfMonth);
            setInitialDate();

        }
    };

    public void onClickDate(){

    }
public void update(){
    SQLiteDatabase db = dbHelper.getWritableDatabase();


    String[] projection = {
            AppContract.AllLessons._ID,
            AppContract.AllLessons.COLUMN_DATE,
            AppContract.AllLessons.COLUMN_TIME,
            AppContract.AllLessons.COLUMN_LECTURER,
            AppContract.AllLessons.COLUMN_SUBJECT

    };

    String selection = "date = \"" + date.getText().toString()+"\"";
    Log.d("mLog", selection);
    Cursor cursor = db.query(AppContract.AllLessons.TABLE_NAME, projection, selection,null,null,null,null);

    int idColumnIndex=cursor.getColumnIndex(AppContract.AllLessons._ID);
    int dateColumnIndex=cursor.getColumnIndex(AppContract.AllLessons.COLUMN_DATE);
    int timeColumnIndex=cursor.getColumnIndex(AppContract.AllLessons.COLUMN_TIME);
    int lecturerColumnIndex=cursor.getColumnIndex(AppContract.AllLessons.COLUMN_LECTURER);
    int subjectColumnIndex=cursor.getColumnIndex(AppContract.AllLessons.COLUMN_SUBJECT);
    while (cursor.moveToNext()){

        LessonModel lesson = new LessonModel(cursor.getString(idColumnIndex), cursor.getString(subjectColumnIndex), cursor.getString(lecturerColumnIndex), cursor.getString(dateColumnIndex), cursor.getString(timeColumnIndex));
        list_lessons.add(lesson);
    }
    if (list_lessons.size() != 0) {
        ListViewAdapterForLessons adapter = new ListViewAdapterForLessons(ShowLessons.this, list_lessons);
        lessonListView.setAdapter(adapter);
        //убираем View загрузки
        // circular_progress.setVisibility(View.INVISIBLE);
        lessonListView.setVisibility(View.VISIBLE);
        noItems.setVisibility(View.INVISIBLE);
    }
    else {
        noItems.setVisibility(View.VISIBLE);
    }
    cursor.close();
    dbHelper.close();

}
    private void setInitialDate() {
        date.setText(DateUtils.formatDateTime(this,
                dateAndTime.getTimeInMillis(),
                DateUtils.FORMAT_SHOW_DATE | DateUtils.FORMAT_SHOW_YEAR));
        list_lessons.clear();
        ListViewAdapterForLessons adapter = new ListViewAdapterForLessons(ShowLessons.this, list_lessons);
        lessonListView.setAdapter(adapter);
        update();
    }

    public void setDate(View v) {
        new DatePickerDialog(ShowLessons.this, d,
                dateAndTime.get(Calendar.YEAR),
                dateAndTime.get(Calendar.MONTH),
                dateAndTime.get(Calendar.DAY_OF_MONTH))
                .show();
    }

public void addLesson(View v){
    Intent intent = new Intent(ShowLessons.this, LessonAdd.class);
     startActivityForResult(intent,1);;

}
    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        list_lessons.clear();
        update();
    }

    public void exit(View v){
        builder = new AlertDialog.Builder(this);
        builder.setMessage("Выйти из учетной записи?");

        builder.setCancelable(false);
        builder.setPositiveButton("ДА", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {
                Intent intent = new Intent(ShowLessons.this, MainActivity.class);
                setResult(RESULT_OK,intent);
                finish();
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

    }
}
