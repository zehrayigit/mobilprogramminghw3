package com.example.easytutomusicapp;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.hardware.Sensor;
import android.hardware.SensorEvent;
import android.hardware.SensorEventListener;
import android.media.AudioManager;
import android.widget.Toast;

public class MyBroadcastReceiver extends BroadcastReceiver implements SensorEventListener {
    AudioManager audioManager;
    int maxVolume;
    int minVolume;

    @Override
    public void onReceive(Context context, Intent intent) {
        if("com.example.myBroadcastMessage".equals(intent.getAction())){
           // Toast.makeText(context, "Triggered!", Toast.LENGTH_SHORT).show();
            audioManager = (AudioManager) context.getSystemService(Context.AUDIO_SERVICE);////?
            maxVolume = audioManager.getStreamMaxVolume(AudioManager.STREAM_MUSIC);
            audioManager.setStreamVolume(AudioManager.STREAM_MUSIC,maxVolume,0);
            Toast.makeText(context, "Cepte", Toast.LENGTH_SHORT).show();
        }
        if("com.example.myBroadcastMessage2".equals(intent.getAction())){
            //Toast.makeText(context, "Triggered!", Toast.LENGTH_SHORT).show();
            audioManager = (AudioManager) context.getSystemService(Context.AUDIO_SERVICE);////?
            maxVolume = audioManager.getStreamMaxVolume(AudioManager.STREAM_MUSIC);
            audioManager.setStreamVolume(AudioManager.STREAM_MUSIC,0,0);
            Toast.makeText(context, "Masada", Toast.LENGTH_SHORT).show();
        }
    }

    @Override
    public void onSensorChanged(SensorEvent sensorEvent) {

    }

    @Override
    public void onAccuracyChanged(Sensor sensor, int i) {

    }
}
