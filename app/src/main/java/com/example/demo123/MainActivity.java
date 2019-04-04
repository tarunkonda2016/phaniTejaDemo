package com.example.demo123;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;

public class MainActivity extends AppCompatActivity {
    private DBHelper mydb;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);



        mydb = new DBHelper(this);

        //insert
        mydb.insertContact("name","email");

        if (mydb.getAllCotacts().size() > 0) {
            DBHelper.Form form = mydb.getAllCotacts().get(0);
            Log.d("12345678",form.name);
            Log.d("12345678",form.email);
        }
    }
}
