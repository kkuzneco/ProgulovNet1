package com.progulov.progulovnet;

import androidx.appcompat.app.AppCompatActivity;
import android.content.Intent;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.TimePicker;
import android.app.DatePickerDialog;
import android.app.TimePickerDialog;
import android.text.format.DateUtils;
import android.view.View;
import android.os.Bundle;
import java.util.Calendar;

public class LessonAdd extends AppCompatActivity {
    Calendar dateAndTime=Calendar.getInstance();
    Button setdateTime, setSubject, setLecturer, setStudents;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_lesson_add);
        setdateTime=(Button)findViewById(R.id.setDate);
        setSubject=(Button)findViewById(R.id.setSubject);
        setLecturer=(Button)findViewById(R.id.setLecturer);
        setStudents=(Button)findViewById(R.id.setAttendance);
        setInitialDateTime();
    }
    public void ShowSubjectChoice(View v){
        Intent intent = new Intent(LessonAdd.this,Subject.class);
        startActivity(intent);
    }
    public void ShowLecturerChoice(View v){
        Intent intent = new Intent(LessonAdd.this,Lecturer.class);
        startActivity(intent);
    }
    public void ShowStudents(View v){
        Intent intent = new Intent(LessonAdd.this,Students.class);
        startActivity(intent);
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

}
