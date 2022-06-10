package com.example.gps_app.receiver;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.util.Log;

public class GPSBroadcastReceiver extends BroadcastReceiver {

    public static final String TAG = "GPSBroadcastReceiver";
    private GPSService service;

    @Override
    public void onReceive(Context context, Intent intent) {

        Intent intentBoot = new Intent();
        intentBoot.setAction("com.example.gps_app.START");

        Intent intent1 = new Intent(context, GPSService.class);
        if(intent.getAction().equals("android.net.wifi.WIFI_STATE_CHANGED")){

        }


    }
}
