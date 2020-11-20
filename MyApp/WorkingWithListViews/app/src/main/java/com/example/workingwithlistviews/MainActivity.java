package com.example.workingwithlistviews;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Adapter;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.SeekBar;
import android.widget.Toast;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        SeekBar number=(SeekBar)findViewById(R.id.seekBar);
        number.setMax(99);
        number.setProgress(0);
        final ListView listView=(ListView)findViewById(R.id.myList);
        final ArrayList<Integer> arr=new ArrayList<Integer>();
        final ArrayAdapter<Integer>[] arrayAdapter = new ArrayAdapter[]{new ArrayAdapter<Integer>(this, android.R.layout.simple_list_item_1, arr)};
        listView.setAdapter(arrayAdapter[0]);
        for(int i=1;i<=10;i++)
            arr.add(i);
        number.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
            @Override
            public void onProgressChanged(SeekBar seekBar, int i, boolean b) {
                int number1=i;
                number1=number1/10;
                number1++;
                for(int i1=1;i1<=10;i1++)
                    arr.set(i1-1,number1*i1);
                arrayAdapter[0] =new ArrayAdapter<Integer>(MainActivity.this,android.R.layout.simple_list_item_1,arr);
                listView.setAdapter(arrayAdapter[0]);
            }

            @Override
            public void onStartTrackingTouch(SeekBar seekBar) {

            }

            @Override
            public void onStopTrackingTouch(SeekBar seekBar) {

            }
        });
    }
}