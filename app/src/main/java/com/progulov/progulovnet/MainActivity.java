package com.progulov.progulovnet;

import androidx.appcompat.app.AppCompatActivity;

import android.content.ContentValues;
import android.content.Intent;
import android.database.sqlite.SQLiteDatabase;
import android.graphics.Color;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;


public class MainActivity extends AppCompatActivity {
    private EditText username;
    private EditText password;
    private Button login;
    private TextView loginLocked;
    private TextView attempts;
    private TextView numberOfAttempts;
    DBHelper dbHelper;
    int numberOfRemainingLoginAttempts = 3;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        username = (EditText) findViewById(R.id.edit_user);
        password = (EditText) findViewById(R.id.edit_password);
        login = (Button) findViewById(R.id.button_login);
        dbHelper=new DBHelper(this,"",1);
        SQLiteDatabase database = dbHelper.getWritableDatabase();
        ContentValues contentValues= new ContentValues();
        contentValues.put(DBHelper.KEY_NAME, "Аверков Всеволод");
        database.insert(DBHelper.TABLE_SUBJECTS, null, contentValues);
        contentValues.put(DBHelper.KEY_NAME, "Баканов Владимир");
        database.insert(DBHelper.TABLE_SUBJECTS, null, contentValues);
        contentValues.put(DBHelper.KEY_NAME, "Горбунова Дарья");
        database.insert(DBHelper.TABLE_SUBJECTS, null, contentValues);
        contentValues.put(DBHelper.KEY_NAME, "Жданович Екатерина");
        database.insert(DBHelper.TABLE_SUBJECTS, null, contentValues);
        contentValues.put(DBHelper.KEY_NAME, "Зайцев Артем");
        database.insert(DBHelper.TABLE_SUBJECTS, null, contentValues);
        contentValues.put(DBHelper.KEY_NAME, "Клименко Владислав");
        database.insert(DBHelper.TABLE_SUBJECTS, null, contentValues);
        contentValues.put(DBHelper.KEY_NAME, "Кузнецова Ксения");
        database.insert(DBHelper.TABLE_SUBJECTS, null, contentValues);
        contentValues.put(DBHelper.KEY_NAME, "Марков Владислав");
        database.insert(DBHelper.TABLE_SUBJECTS, null, contentValues);
        contentValues.put(DBHelper.KEY_NAME, "Сергеев Руслан");
        database.insert(DBHelper.TABLE_SUBJECTS, null, contentValues);
        contentValues.put(DBHelper.KEY_NAME, "Мотина Вероника");
        database.insert(DBHelper.TABLE_SUBJECTS, null, contentValues);
        contentValues.put(DBHelper.KEY_NAME, "Сарбаев Артур");
        database.insert(DBHelper.TABLE_SUBJECTS, null, contentValues);
        contentValues.put(DBHelper.KEY_NAME, "Харковчук Артур");
        database.insert(DBHelper.TABLE_SUBJECTS, null, contentValues);


    }

    public void OnClick(View view) {

        if( (username.getText().toString().equals("admin") &&
                password.getText().toString().equals("admin"))||(username.getText().toString().equals("a") &&
                password.getText().toString().equals("a")) ){
            Toast.makeText(getApplicationContext(), "Вход выполнен!",Toast.LENGTH_SHORT).show();

            // Выполняем переход на другой экран:
            Intent intent = new Intent(MainActivity.this,LessonAdd.class);
            startActivity(intent);

        }

        // В другом случае выдаем сообщение с ошибкой:
        else {
            Toast.makeText(getApplicationContext(), "Неправильные данные!",Toast.LENGTH_SHORT).show();
        }
    }
}

