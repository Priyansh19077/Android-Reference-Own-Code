package com.example.downloadingimagesfromweb;

import androidx.appcompat.app.AppCompatActivity;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.AsyncTask;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;
import android.widget.Toast;

import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.concurrent.ExecutionException;

public class MainActivity extends AppCompatActivity {

    ImageView downloaded_image;
    public void download_image(View view)
    {
        Log.i("BUTTON TAPPED", "YES");
        ImageDownloader task=new ImageDownloader();
        try {
            Bitmap myImage= task.execute("https://www.countries-ofthe-world.com/flags-normal/flag-of-Grenada.png").get();
            downloaded_image.setImageBitmap(myImage);
        } catch (Exception e) {
            Log.i("FOUND ERROR","!234");
            e.printStackTrace();
        }
    }
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        downloaded_image=(ImageView)findViewById(R.id.downloaded_image1);
    }
    public class ImageDownloader extends AsyncTask<String,Void, Bitmap>{
        URL url;
        HttpURLConnection connection;
        @Override
        protected Bitmap doInBackground(String... urls) {
            try {
                url =new URL(urls[0]);
                connection=(HttpURLConnection) url.openConnection();
                connection.connect();
                InputStream in=connection.getInputStream();
                Bitmap mybitmap= BitmapFactory.decodeStream(in);
                return mybitmap;
            } catch (Exception e) {
                Log.i("NOT CONNECTED","123");
                runOnUiThread(new Runnable(){
                    public void run() {
                        Toast.makeText(getApplicationContext(), "YOU ARE NOT CONNECTED",Toast.LENGTH_LONG).show();
                    }
                });
                e.printStackTrace();
            }
            return null;
        }
    }
}