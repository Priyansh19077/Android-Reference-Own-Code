package com.example.whatstheweather;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.os.AsyncTask;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.inputmethod.InputMethodManager;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import org.json.JSONArray;
import org.json.JSONObject;

import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;

public class MainActivity extends AppCompatActivity {
    TextView result;
    EditText city;
    String result_weather;
    public void getWeather(View view) {
        DownloadTask task=new DownloadTask();
        String city_weather=city.getText().toString();
        if(have_connection()) {
            InputMethodManager inputMethodManager = (InputMethodManager) getSystemService(Context.INPUT_METHOD_SERVICE);
            inputMethodManager.hideSoftInputFromWindow(city.getWindowToken(), 0);
            result_weather = "";
            task.execute("https://openweathermap.org/data/2.5/weather?q=" + city_weather + "&appid=439d4b804bc8187953eb36d2a8c26a02");
        }
        else
            Toast.makeText(getApplicationContext(),"NO CONNECTION DETECTED, OPEN YOUR WIFI OR MOBILE DATA",Toast.LENGTH_SHORT).show();
        return;
    }
    public class DownloadTask extends AsyncTask<String, Void, String> {
        @Override
        protected String doInBackground(String... urls) {
            String result="";
            URL url;
            HttpURLConnection urlConnection;
            try{
                url=new URL(urls[0]);
                urlConnection=(HttpURLConnection) url.openConnection();
                InputStream in=urlConnection.getInputStream();
                InputStreamReader reader=new InputStreamReader(in);
                int data=reader.read();
                while(data!=-1)
                {
                    char ch=(char)data;
                    result+=ch;
                    data=reader.read();
                }
                return result;
            }catch(Exception e)
            {
                runOnUiThread(new Runnable(){
                    public void run() {
                        Toast.makeText(getApplicationContext(), "INVALID CITY",Toast.LENGTH_LONG).show();
                    }
                });
                e.printStackTrace();
                return null;
            }
        }

        @Override
        protected void onPostExecute(String s) {
            super.onPostExecute(s);
            if(s==null)
                s="INVALID";
            Log.i("RECEIVED STIRNG",s);
            JSONObject jsonObject= null;
            try {
                jsonObject = new JSONObject(s);
                String weigherInfo=jsonObject.getString("weather");
                Log.i("Weather Content",weigherInfo);
                JSONArray arr=new JSONArray(weigherInfo);
                for(int i=0;i<arr.length();i++)
                {
                    JSONObject jsonObject1=arr.getJSONObject(i);
                    Log.i("MAIN",jsonObject1.getString("main"));
                    Log.i("DESCRIPTION",jsonObject1.getString("description"));
                    if(jsonObject1.getString("main").length()!=0 && jsonObject1.getString("description").length()!=0)
                    result_weather+=jsonObject1.getString("main")+" : "+jsonObject1.getString("description")+"\r\n";
                }
                if(result_weather.length()!=0)
                    result.setText(result_weather);
                else
                    Toast.makeText(getApplicationContext(),"INVALID CITY",Toast.LENGTH_SHORT).show();
                return;
            } catch (Exception e) {
                e.printStackTrace();
                return;
            }

        }
    }
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        result=(TextView)findViewById(R.id.txt_result);
        city=(EditText)findViewById(R.id.edt_city);

    }
    public boolean have_connection()
    {
        boolean have_WIFI= false;
        boolean have_MobileData = false;
        ConnectivityManager connectivityManager = (ConnectivityManager)getSystemService(CONNECTIVITY_SERVICE);
        NetworkInfo[] networkInfos = connectivityManager.getAllNetworkInfo();
        for(NetworkInfo info:networkInfos){
            if (info.getTypeName().equalsIgnoreCase("WIFI"))if (info.isConnected())have_WIFI=true;
            if (info.getTypeName().equalsIgnoreCase("MOBILE DATA"))if (info.isConnected())have_MobileData=true;
        }
        return have_WIFI||have_MobileData;
    }
}