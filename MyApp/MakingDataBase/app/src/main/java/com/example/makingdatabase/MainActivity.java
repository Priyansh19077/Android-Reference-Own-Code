package com.example.makingdatabase;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {
    DatabaseHelper myDb;
    EditText e1,e2,e3;
    Button b1;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        myDb=new DatabaseHelper(this);
        e1=findViewById(R.id.edt1);
        e2=findViewById(R.id.edt2);
        e3=findViewById(R.id.edt3);
        b1=findViewById(R.id.btn1);
        b1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String s1=e1.getText().toString();
                String s2=e1.getText().toString();
                String s3=e1.getText().toString();
//                int marks=Integer.parseInt(s3);
                int marks=0;
                boolean x=myDb.insertData(s1,s2,marks);
                if(x==false) {
                    Toast t1 = Toast.makeText(getApplicationContext(), "DATA NOT INSERTED", Toast.LENGTH_SHORT);
                    t1.show();
                }
                else {
                    Toast t1 = Toast.makeText(getApplicationContext(), "DATA INSERTED", Toast.LENGTH_SHORT);
                    t1.show();
                }
            }
        });
    }
}