package com.example.learning_persistent_storage;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class STORE_DATA extends AppCompatActivity {
    private Button save_data;
    private EditText name;
    private EditText data;
    public final String preferences_string="com.example.learning_persistent_storage.FILES";
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_s_t_o_r_e__d_a_t);
        save_data=(Button) findViewById(R.id.btnSave);
        name=(EditText) findViewById(R.id.edtName);
        data=(EditText) findViewById(R.id.edtData);
        final SharedPreferences sharedPref= getSharedPreferences(preferences_string,Context.MODE_PRIVATE);
        save_data.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String key = name.getText().toString();
                String value = data.getText().toString();
                if (name.length() != 0) {
                    sharedPref.edit().putString(key, value).apply();
                    name.setText("");
                    data.setText("");
                    Toast toast = Toast.makeText(getApplicationContext(), "DATA SAVED", Toast.LENGTH_LONG);
                    toast.show();
                } else {
                    Toast toast = Toast.makeText(getApplicationContext(), "ENTER A KEY TO SAVE DATA", Toast.LENGTH_LONG);
                    toast.show();
                }
            }
        });
    }
}