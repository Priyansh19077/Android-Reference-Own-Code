package com.example.sql;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.Switch;
import android.widget.Toast;

import java.util.List;

public class MainActivity extends AppCompatActivity {

    private EditText et1;
    private EditText et2;
    private Switch sw1;
    private Button b1;
    private Button b2;
    private ListView lvcustomerList;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        et1=findViewById(R.id.edt_name);
        et2=findViewById(R.id.edt_age);
        sw1=findViewById(R.id.switch_active_user);
        b1=findViewById(R.id.btn_new);
        b2=findViewById(R.id.btn_view);
        lvcustomerList=findViewById(R.id.lst);
        b1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String name =et1.getText().toString();
                String age=et2.getText().toString();
                CustomerModel cm;
                if(age.length()==0)
                {
                    Toast t1= Toast.makeText(getApplicationContext(),"AGE CANNOT BE EMPTY", Toast.LENGTH_SHORT);
                    t1.show();
                }
                else {
                    int age1 = Integer.parseInt(age);
                    cm = new CustomerModel(-1, name, age1, sw1.isChecked());
                    DataBaseHelper dataBaseHelper=new DataBaseHelper(MainActivity.this);
                    boolean p= dataBaseHelper.addOne(cm);
                    if(p) {
                        Toast t1 = Toast.makeText(getApplicationContext(), cm.toString(), Toast.LENGTH_SHORT);
                        t1.show();
                    }
                }
                DataBaseHelper dataBaseHelper=new DataBaseHelper(MainActivity.this);
                ArrayAdapter arrayAdapter=new ArrayAdapter<CustomerModel>(MainActivity.this,android.R.layout.simple_list_item_1,dataBaseHelper.getEveryone());
                lvcustomerList.setAdapter(arrayAdapter);
            }
        });
        b2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                DataBaseHelper dataBaseHelper=new DataBaseHelper(MainActivity.this);
                ArrayAdapter arrayAdapter=new ArrayAdapter<CustomerModel>(MainActivity.this,android.R.layout.simple_list_item_1,dataBaseHelper.getEveryone());
                lvcustomerList.setAdapter(arrayAdapter);
//                Toast t1= Toast.makeText(getApplicationContext(),list.toString(), Toast.LENGTH_SHORT);
//                t1.show();
            }
        });
        lvcustomerList.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int i, long l) {
                CustomerModel clicked=(CustomerModel)parent.getItemAtPosition(i);
                DataBaseHelper dataBaseHelper=new DataBaseHelper(MainActivity.this);
                boolean p=dataBaseHelper.deleteOne(clicked);
                    Toast t1= Toast.makeText(getApplicationContext(),"DELETED", Toast.LENGTH_SHORT);
                    t1.show();
                    ArrayAdapter arrayAdapter=new ArrayAdapter<CustomerModel>(MainActivity.this,android.R.layout.simple_list_item_1,dataBaseHelper.getEveryone());
                    lvcustomerList.setAdapter(arrayAdapter);
            }
        });
    }
}