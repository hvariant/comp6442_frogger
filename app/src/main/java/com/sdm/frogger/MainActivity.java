package com.sdm.frogger;

import android.content.Intent;
import android.provider.Settings;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.Window;

public class MainActivity extends AppCompatActivity {
    final static String TAG = "WelcomeActivity";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }

    /** Called when the user clicks the Send button */
    public void startGame(View view) {
        // Do something in response to button
        Log.v(TAG, "Hello world!");

        Intent intent = new Intent(this, GameActivity.class);
        startActivity(intent);
    }

    public void startTouchDemo(View view){
        Log.v(TAG,"Touch!");

        Intent intent = new Intent(this, TouchActivity.class);
        startActivity(intent);
    }

    public void startGestureDemo(View view){
        Log.v(TAG,"Gesture!");

        Intent intent = new Intent(this, GestureActivity.class);
        startActivity(intent);
    }
}
