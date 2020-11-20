package com.example.downloadingwebcontent;

import androidx.appcompat.app.AppCompatActivity;

import android.os.AsyncTask;
import android.os.Bundle;
import android.util.Log;
import android.widget.TextView;

import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;

public class MainActivity extends AppCompatActivity {

    TextView t1;
    public class DownloadTask extends AsyncTask<String,Void,String>{

        @Override
        protected String doInBackground(String... strings) {
            Log.i("URL RECEIVED",strings[0]);
            String result="";
            URL url;
            HttpURLConnection urlConnection=null;
            try {
                url =new URL(strings[0]);
                urlConnection=(HttpURLConnection) url.openConnection();
                urlConnection.connect();
                InputStream in=urlConnection.getInputStream();
                InputStreamReader reader=new InputStreamReader(in);
                int data=reader.read();
                while(data!=-1)
                {
                    char current=(char)data;
                    result+=current;
                    data=reader.read();
                }
                return result;
            } catch (Exception e) {
                Log.i("URL ERROR","Could not convert the url");
                e.printStackTrace();
            }
            return "NOTHING RECEIVED";
        }
    }
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        t1=(TextView)findViewById(R.id.tttt);
        t1.setText("DATA IS BEING FETCHED");
        DownloadTask task = new DownloadTask();
        String result="NOTHING";
        try {
            result= task.execute("https://www.countries-ofthe-world.com/flags-of-europe.html").get();
        } catch (Exception e) {
            Log.i("PRIYANSH","GOT AN ERRR");
        }
        Log.i("URL RECEIVED",result);
        t1.setText(result);
    }
}