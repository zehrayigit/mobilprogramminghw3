package com.example.mp_odev3;

import androidx.appcompat.app.AppCompatActivity;

import android.app.Service;
import android.content.Context;
import android.content.Intent;
import android.hardware.Sensor;
import android.hardware.SensorEvent;
import android.hardware.SensorEventListener;
import android.hardware.SensorManager;
import android.media.AudioManager;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity implements SensorEventListener {
    TextView textView;
    SensorManager sensorManager;
    SensorManager sensorManager2;
    Sensor sensor;
    Sensor sensor2;
    AudioManager audioManager;
    int maxVolume;
    int minVolume;
    float xValueBefore = 0;
    float yValueBefore = 0;
    float zValueBefore = 0;
    float lightValue = 0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        textView = (TextView) findViewById(R.id.textView);
        sensorManager = (SensorManager) getSystemService(Service.SENSOR_SERVICE);
        sensorManager2 = (SensorManager) getSystemService(Service.SENSOR_SERVICE);
        sensor = sensorManager.getDefaultSensor(Sensor.TYPE_LIGHT);
        sensor2 = sensorManager2.getDefaultSensor(Sensor.TYPE_ACCELEROMETER);
        audioManager = (AudioManager) getSystemService(Context.AUDIO_SERVICE);
        maxVolume = audioManager.getStreamMaxVolume(AudioManager.STREAM_MUSIC);

        int curVolume = audioManager.getStreamVolume(AudioManager.STREAM_MUSIC);
    }

    @Override
    protected void onPause(){
        super.onPause();
        sensorManager.unregisterListener(this);
        sensorManager2.unregisterListener(this);
    }

    @Override
    protected void onResume(){
        super.onResume();
        sensorManager.registerListener(this,sensor,SensorManager.SENSOR_DELAY_NORMAL);
        sensorManager2.registerListener(this,sensor2,SensorManager.SENSOR_DELAY_NORMAL);
    }

    @Override
    public void onSensorChanged(SensorEvent event) {
        if(event.sensor.getType()==Sensor.TYPE_LIGHT){
            if(event.values[0]>20000){
                lightValue = event.values[0];
               // Toast.makeText(this, "masada", Toast.LENGTH_SHORT).show();
                Intent intent = new Intent();
                intent.setAction("com.example.myBroadcastMessage2");
                intent.setFlags(Intent.FLAG_INCLUDE_STOPPED_PACKAGES);
                sendBroadcast(intent);
            }else{
                lightValue = event.values[0];
            }
        }

        if(event.sensor.getType()==Sensor.TYPE_ACCELEROMETER){
            if((xValueBefore != event.values[0]) || (yValueBefore != event.values[1] || zValueBefore != event.values[2])){
                xValueBefore = event.values[0];
                yValueBefore = event.values[1];
                zValueBefore = event.values[2];
                if(lightValue<20000){
                    //Toast.makeText(this, "cepte", Toast.LENGTH_SHORT).show();
                    Intent intent = new Intent();
                    intent.setAction("com.example.myBroadcastMessage");
                    intent.setFlags(Intent.FLAG_INCLUDE_STOPPED_PACKAGES);
                    sendBroadcast(intent);
                }
            }
        }
    }

    @Override
    public void onAccuracyChanged(Sensor sensor, int i) {

    }

    public void onButtonClicked(View v){
        Intent intent = new Intent();
        intent.setAction("com.example.myBroadcastMessage");
        intent.setFlags(Intent.FLAG_INCLUDE_STOPPED_PACKAGES);
        sendBroadcast(intent);
    }
}