package com.example.gps_app.views;

import androidx.activity.result.ActivityResultLauncher;
import androidx.activity.result.contract.ActivityResultContracts;
import androidx.appcompat.app.AppCompatActivity;

import android.Manifest;
import android.content.BroadcastReceiver;
import android.content.Intent;
import android.content.IntentFilter;
import android.os.Bundle;

import android.app.Activity;
import android.app.AlertDialog;
import android.util.Log;
import android.view.View;
import android.widget.EditText;

import com.example.gps_app.R;

import com.example.gps_app.models.Login;
import com.example.gps_app.receiver.GPSBroadcastReceiver;

public class MainActivity extends AppCompatActivity {

    private EditText user;
    private EditText password;
    BroadcastReceiver br;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        InitializeObjects();

        br = new GPSBroadcastReceiver();
        IntentFilter intf = new IntentFilter("android.net.wifi.WIFI_STATE_CHANGED");
        //IntentFilter intf2 = new IntentFilter()

        registerReceiver(br, intf);

        ActivityResultLauncher<String[]> locationPermissionRequest = registerForActivityResult(
                new ActivityResultContracts.RequestMultiplePermissions(), result -> {
                    Boolean fineLocationGranted = result.getOrDefault(
                            Manifest.permission.ACCESS_FINE_LOCATION, false);
                    Boolean coarseLocationGranted = result.getOrDefault(
                            Manifest.permission.ACCESS_COARSE_LOCATION, false);
                    Boolean backgroundLocationGranted = result.getOrDefault(
                            Manifest.permission.ACCESS_BACKGROUND_LOCATION, false);
                    if(fineLocationGranted != null && fineLocationGranted && backgroundLocationGranted)
                        Log.d("MainActivity", "onCreate: autorizado GPS");
                    else if (coarseLocationGranted != null && coarseLocationGranted)
                        Log.d("MainActivity", "onCreate: Somente localização aproximada autorizada");
                    else
                        Log.d("MainActivity", "onCreate: Localização não autorizada");
                });

        locationPermissionRequest.launch(new String[]{
                Manifest.permission.ACCESS_FINE_LOCATION,
                Manifest.permission.ACCESS_COARSE_LOCATION
        });
    }


    public void InitializeObjects(){
        user = (EditText) findViewById(R.id.LoginText);
        password = (EditText) findViewById(R.id.PasswordText);
    }

    public void WarningMessage(String message){
        new AlertDialog.Builder(this)
                .setTitle("Atenção")
                .setMessage(message)
                .setPositiveButton("OK", null)
                .setCancelable(false)
                .show();
    }
    public void Login(View view){
        Login login = new Login();

        login.setUser(user.getText().toString());
        login.setPassword(password.getText().toString());

        if (login.Validate() == false){
            WarningMessage("Usuário ou senha incorretos!");
        }
        else{
            setResult(Activity.RESULT_OK);
            startActivity(new Intent(MainActivity.this, GpsActivity.class));
        }
    }
}