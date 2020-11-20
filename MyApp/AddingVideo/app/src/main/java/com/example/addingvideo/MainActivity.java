package com.example.addingvideo;

import androidx.appcompat.app.AppCompatActivity;

import android.net.Uri;
import android.os.Bundle;
import android.widget.MediaController;
import android.widget.VideoView;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        VideoView view1=(VideoView)findViewById(R.id.videoView);
        String path="android.resource://"+getPackageName()+"/"+R.raw.mountain;
        Uri uri= Uri.parse(path);
        view1.setVideoURI(uri);
        MediaController mediaController=new MediaController(this);
        view1.setMediaController(mediaController);
        mediaController.setAnchorView(view1);
    }

}