package com.example.bluetoothapp;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;

import android.Manifest;
import android.bluetooth.BluetoothAdapter;
import android.bluetooth.BluetoothDevice;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.content.pm.PackageManager;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;
import android.widget.TextView;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {
    ListView listView;
    TextView status;
    Button search;
    BluetoothAdapter bluetoothAdapter;
    ArrayList<String> devices=new ArrayList<String>();
    ArrayAdapter adapter;
    private final BroadcastReceiver broadcastReceiver=new BroadcastReceiver() {
        @Override
        public void onReceive(Context context, Intent intent) {
            String action = intent.getAction();
            Log.i("Action",action);
            if(BluetoothAdapter.ACTION_DISCOVERY_STARTED.equals(action)) {
                status.setText("Discovering...");
            }
            else if(BluetoothAdapter.ACTION_DISCOVERY_FINISHED.equals(action)){
                status.setText("Finished");
                search.setEnabled(true);
            }
            else if(BluetoothDevice.ACTION_FOUND.equals(action)) {
                BluetoothDevice device=intent.getParcelableExtra(BluetoothDevice.EXTRA_DEVICE);
                String name=device.getName();
                String rssi=Integer.toString(intent.getShortExtra(BluetoothDevice.EXTRA_RSSI,Short.MIN_VALUE));
                if(name!=null) {
                    Log.i("DEVICE FOUND", "name:" + name + " rssi: " + rssi);
                    devices.add("name: " + name + ", strength: " + rssi);
                    Log.i("SIZE", Integer.toString(devices.size()));
                    adapter.notifyDataSetChanged();
                }
            }
        }
    };
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        listView=findViewById(R.id.listofDevices);
        status=findViewById(R.id.textViewStatus);
        search=findViewById(R.id.buttonSearch);
        status.setVisibility(View.INVISIBLE);
        adapter=new ArrayAdapter(this,android.R.layout.simple_list_item_1,devices);
        listView.setAdapter(adapter);
        bluetoothAdapter=BluetoothAdapter.getDefaultAdapter();
        if (ContextCompat.checkSelfPermission(this,
                Manifest.permission.ACCESS_FINE_LOCATION)
                != PackageManager.PERMISSION_GRANTED) {

            Log.i("info", "No fine location permissions");

            ActivityCompat.requestPermissions(this, new String[]{Manifest.permission.ACCESS_FINE_LOCATION},
                    1);
        }
        IntentFilter intentFilter=new IntentFilter();
        intentFilter.addAction(BluetoothAdapter.ACTION_STATE_CHANGED);
        intentFilter.addAction(BluetoothDevice.ACTION_FOUND);
        intentFilter.addAction(BluetoothAdapter.ACTION_DISCOVERY_STARTED);
        intentFilter.addAction(BluetoothAdapter.ACTION_DISCOVERY_FINISHED);
        registerReceiver(broadcastReceiver,intentFilter);
    }
    public void searching(View view){
        devices.clear();
        adapter.notifyDataSetChanged();
        status.setVisibility(View.VISIBLE);
        status.setText("Searching...");
        search.setEnabled(false);
        bluetoothAdapter.startDiscovery();
    }
}