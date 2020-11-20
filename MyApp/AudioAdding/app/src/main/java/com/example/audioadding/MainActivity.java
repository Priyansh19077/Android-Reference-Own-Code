package com.example.audioadding;

import androidx.appcompat.app.AppCompatActivity;

import android.media.AudioManager;
import android.media.MediaPlayer;
import android.os.Bundle;
import android.provider.MediaStore;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.SeekBar;

import java.util.Timer;
import java.util.TimerTask;

public class MainActivity extends AppCompatActivity {

    MediaPlayer mediaPlayer;
    AudioManager am;
    SeekBar scrub_bar;
    SeekBar volumeControl;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        am=(AudioManager) getSystemService(AUDIO_SERVICE);
        scrub_bar=(SeekBar)findViewById(R.id.scrub);
        mediaPlayer=MediaPlayer.create(this,R.raw.shayrana);
        scrub_bar.setMax(mediaPlayer.getDuration());
        Button b1= (Button)findViewById(R.id.btn1);
        Button b2= (Button)findViewById(R.id.btn2);
        volumeControl=findViewById(R.id.seekBar);
        int current_volume=am.getStreamVolume(AudioManager.STREAM_MUSIC);
        int max_volume=am.getStreamMaxVolume(AudioManager.STREAM_MUSIC);
        volumeControl.setMax(max_volume);
        volumeControl.setProgress(current_volume);
        b1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                mediaPlayer.start();
            }
        });
        b2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                mediaPlayer.pause();
            }
        });
        scrub_bar.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
            @Override
            public void onProgressChanged(SeekBar seekBar, int i, boolean b) {
                Log.i("Scrub Value Changed",Integer.toString(i));
                mediaPlayer.seekTo(i);
            }

            @Override
            public void onStartTrackingTouch(SeekBar seekBar) {
                mediaPlayer.pause();
            }

            @Override
            public void onStopTrackingTouch(SeekBar seekBar) {
                mediaPlayer.start();
            }
        });
        volumeControl.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
            @Override
            public void onProgressChanged(SeekBar seekBar, int i, boolean b) {
                Log.i("Seekbar changed",Integer.toString(i));
                am.setStreamVolume(AudioManager.STREAM_MUSIC,i,0);
            }

            @Override
            public void onStartTrackingTouch(SeekBar seekBar) {

            }

            @Override
            public void onStopTrackingTouch(SeekBar seekBar) {

            }
        });
        new Timer().scheduleAtFixedRate(new TimerTask() {
            @Override
            public void run() {
                int current_volume=am.getStreamVolume(AudioManager.STREAM_MUSIC);
                scrub_bar.setProgress(mediaPlayer.getCurrentPosition());
                volumeControl.setProgress(current_volume);
            }
        },0,1000);
    }
}