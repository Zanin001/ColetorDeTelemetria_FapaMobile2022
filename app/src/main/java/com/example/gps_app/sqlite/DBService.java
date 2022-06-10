package com.example.gps_app.sqlite;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;

public class DBService {
    private SQLiteDatabase db;
    private DBHelper dbHelper;

    public DBService(Context context){
        dbHelper = new DBHelper(context);
    }

    public boolean create(){
        return true;
    }

}
