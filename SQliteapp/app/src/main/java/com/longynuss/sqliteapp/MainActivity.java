package com.longynuss.sqliteapp;

import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        //create database
//        SQLiteDatabase dataBase = openOrCreateDatabase("app",MODE_PRIVATE,null);

        //create table
//        dataBase.execSQL("CREATE TABLE IF NOT EXISTS people(id INTEGER PRIMARY KEY AUTOINCREMENT, name VARCHAR,age INTEGER(3))");
//        dataBase.execSQL("DROP TABLE people");
//
        //insert data
//        dataBase.execSQL("INSERT INTO people(name,age) VALUES ('IDteste',20)");
//        dataBase.execSQL("INSERT INTO people(name,age) VALUES ('LASTTTT',99)");
//        dataBase.execSQL("UPDATE people SET age = 1 WHERE name ='Joana'");
//        dataBase.execSQL("DELETE FROM people WHERE age = 1");

//        Cursor cursor = dataBase.rawQuery("SELECT name, age FROM people WHERE age > 20 AND name LIKE '%a%'",null);
//        Cursor cursor = dataBase.rawQuery("SELECT * FROM people" ,null);

        //get index of columns in the table of the dataBase
//        int indexName = cursor.getColumnIndex("name");
//        int indexAge = cursor.getColumnIndex("age");
//        int indexID = cursor.getColumnIndex("id");

//        cursor.moveToFirst();
//
//        while(!cursor.isAfterLast()){
//            Toast.makeText(MainActivity.this,"ID: "+ cursor.getString(indexID)+" Name: "
//                            +cursor.getString(indexName) + " Age: "+ cursor.getString(indexAge),
//                            Toast.LENGTH_SHORT).show();
//
//            cursor.moveToNext();
//        }
//
//        cursor.close();
    }
}
