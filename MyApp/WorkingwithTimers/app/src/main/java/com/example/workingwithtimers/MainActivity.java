package com.example.workingwithtimers;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.os.CountDownTimer;
import android.os.Handler;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    int time;
    int counter2;
    EditText timer;
    Button b1;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        time=0;
        timer=(EditText)findViewById(R.id.timer);
        b1=(Button)findViewById(R.id.timer_button);
        b1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String s=timer.getText().toString();
                if(s.length()==0)
                    Toast.makeText(getApplicationContext(),"PLEASE ENTER SOMETHING",Toast.LENGTH_SHORT).show();
                else {
                    time = Integer.parseInt(s);
                    if(time>100) {
                        Toast.makeText(getApplicationContext(), "NUMBER MUST BE LESS THAN 100", Toast.LENGTH_SHORT).show();
                        timer.setText("");
                    }
                    else
                    {
                        b1.setVisibility(View.INVISIBLE);
                        timer.clearFocus();
                        using_count_down_timer(time);
                    }
                }
            }
        });
    }
    public void using_handler_and_runnable(int x)
    {
        //runs infinitely until a condition is met
        counter2=x;
        final Handler handler=new Handler();
        Runnable runnable=new Runnable() {
            @Override
            public void run() {
                TextView edt2=(TextView) findViewById(R.id.textView2);
                if(counter2<0) {
                    timer.setText("");
                    b1.setVisibility(View.VISIBLE);
                    edt2.setVisibility(View.INVISIBLE);
                    return;
                }
                edt2.setText(Integer.toString(counter2));
                counter2-=1;
                edt2.setVisibility(View.VISIBLE);
                handler.postDelayed(this,1000);
            }
        };
        handler.post(runnable);
    }
    public void using_count_down_timer(int x)
    {

        counter2=x;
        new CountDownTimer(1000*x,1000)
        {
            public void onTick(long milliseconds_until_done)
            {
                TextView edt2=(TextView)findViewById(R.id.textView2);
                edt2.setText(Integer.toString(counter2));
                edt2.setVisibility(View.VISIBLE);
                counter2-=1;
            }
            public void onFinish()
            {
                TextView edt2=(TextView)findViewById(R.id.textView2);
                edt2.setVisibility(View.VISIBLE);
                timer.setText("");
                b1.setVisibility(View.VISIBLE);
                edt2.setVisibility(View.INVISIBLE);
                return;
            }
        }.start();
    }

}