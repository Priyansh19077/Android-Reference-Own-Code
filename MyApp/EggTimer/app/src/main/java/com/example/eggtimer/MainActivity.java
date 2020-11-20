package com.example.eggtimer;

import androidx.appcompat.app.AppCompatActivity;

import android.media.MediaPlayer;
import android.os.Bundle;
import android.os.CountDownTimer;
import android.view.View;
import android.widget.Button;
import android.widget.SeekBar;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    private SeekBar s1;
    private TextView timer;
    private boolean seek_bar_active;
    Button set;
    int time;
    int counter2;
    MediaPlayer mediaPlayer;
    CountDownTimer cc;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        mediaPlayer =MediaPlayer.create(getApplicationContext(),R.raw.alarm);
        mediaPlayer.setLooping(true);
        time=30;
        seek_bar_active=true;
        s1=(SeekBar)findViewById(R.id.seekbar);
        set=(Button)findViewById(R.id.set_timer);
        s1.setMax(600);
        s1.setProgress(30);
        timer=(TextView)findViewById(R.id.textView);
        s1.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
            @Override
            public void onProgressChanged(SeekBar seekBar, int i, boolean b) {
                time=i;
                changeTime(i);
            }

            @Override
            public void onStartTrackingTouch(SeekBar seekBar) {

            }

            @Override
            public void onStopTrackingTouch(SeekBar seekBar) {

            }
        });
        set.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(seek_bar_active==true)
                {
                    seek_bar_active=false;
                    set.setText("RESET");
                    s1.setEnabled(false);
                    using_count_down_timer(time);
                }
                else
                {
                    seek_bar_active=true;
                    set.setText("SET");
                    mediaPlayer.stop();
                    mediaPlayer.prepareAsync();
                    s1.setProgress(30);
                    time=30;
                    timer.setText("00:30");
                    s1.setEnabled(true);
                    cc.cancel();
                }
            }
        });
    }
    public void using_count_down_timer(int x)
    {
        cc=new CountDownTimer(1000*x+100,1000)
        {
            public void onTick(long milliseconds_until_done)
            {
                changeTime((int)milliseconds_until_done/1000);
            }
            public void onFinish()
            {
                mediaPlayer.start();
                Toast.makeText(getApplicationContext(),"TIME UP",Toast.LENGTH_SHORT).show();
            }
        }.start();
    }
    public void changeTime(int number)
    {
        int minutes=number/60;
        int seconds=number-60*minutes;
        String minutes1=Integer.toString(minutes);
        String seconds1=Integer.toString(seconds);
        while(minutes1.length()<2)
            minutes1='0'+minutes1;
        while(seconds1.length()<2)
            seconds1='0'+seconds1;
        String final_time=minutes1+":"+seconds1;
        timer.setText(final_time);
    }
}