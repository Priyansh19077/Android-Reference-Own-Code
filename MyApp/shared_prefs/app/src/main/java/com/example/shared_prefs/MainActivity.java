package com.example.shared_prefs;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.widget.ArrayAdapter;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        SharedPreferences sharedPreferences=this.getSharedPreferences("com.example.shared_prefs", Context.MODE_PRIVATE);
        ArrayList<String> friends=new ArrayList<String>();
        friends.add("Priyansh");
        friends.add("Priyam");
        friends.add("Anjali");
    }
}