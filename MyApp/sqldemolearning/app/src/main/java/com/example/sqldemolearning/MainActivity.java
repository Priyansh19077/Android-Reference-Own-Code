package com.example.sqldemolearning;

import androidx.appcompat.app.AppCompatActivity;

import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.util.Log;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        SQLiteDatabase myDatabase=this.openOrCreateDatabase("Users", MODE_PRIVATE,null);
        myDatabase.execSQL("CREATE TABLE IF NOT EXISTS users (name VARCHAR, age INT(3))");
//        myDatabase.execSQL("INSERT INTO users (name, age) VALUES ('Priyansh', 20)");
//        myDatabase.execSQL("INSERT INTO users (name, age) VALUES ('Priyamsh', 18)");
//        myDatabase.execSQL("INSERT INTO users (name, age) VALUES ('Priyansh1', 25)");
        Cursor c=myDatabase.rawQuery("SELECT * FROM users WHERE age > 20",null);
        int name_index=c.getColumnIndex("name");
        int age_index=c.getColumnIndex("age");
        Log.i("name",String.valueOf(name_index));
        Log.i("age",String.valueOf(age_index));
        int count=0;
        if(c!=null && c.moveToFirst()) {
            c.moveToFirst();
            while (c != null) {
                Log.i("name", String.valueOf(name_index));
                Log.i("age", String.valueOf(age_index));
                Log.i("name", c.getString(name_index) + " " + c.getString(age_index));
                count++;
                Log.i("count", String.valueOf(count));
                if (c.moveToNext())
                    continue;
                else
                    break;

            }
        }
        c.close();
    }
}