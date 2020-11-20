package com.example.learning_persistent_storage;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import org.w3c.dom.Text;

public class LOAD_DATA extends AppCompatActivity {
    public final String preferences_string="com.example.learning_persistent_storage.FILES";
    private Button load;
    private EditText name;
    private TextView data;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_l_o_a_d__d_a_t);
        name=(EditText) findViewById(R.id.edtName);
        load=(Button) findViewById(R.id.btnLoad);
        data=(TextView) findViewById(R.id.txtData);
        final SharedPreferences sharedPref=getSharedPreferences(preferences_string, Context.MODE_PRIVATE);
        load.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String key=name.getText().toString();
                if(name.length()!=0)
                {
                    String value=sharedPref.getString(key,"NOTHING_FOUND");
                    if(!value.equals("NOTHING_FOUND"))
                        data.setText(value);
                    else
                    {
                        Toast toast = Toast.makeText(getApplicationContext(), "SORRY NOTHING FOUND WITH THE GIVEN KEY", Toast.LENGTH_LONG);
                        toast.show();
                    }
                }
                else
                {
                    Toast toast = Toast.makeText(getApplicationContext(), "ENTER A KEY TO LOAD DATA", Toast.LENGTH_LONG);
                    toast.show();
                }
            }
        });
    }
}