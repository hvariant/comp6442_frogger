package com.sdm.frogger;

import android.support.annotation.Nullable;
import android.support.v4.view.GestureDetectorCompat;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.GestureDetector;
import android.view.MotionEvent;

import java.util.Timer;
import java.util.TimerTask;

public class GameActivity extends AppCompatActivity implements GestureDetector.OnGestureListener{
    double fps = 30;

    private GestureDetectorCompat mDetector;
    private String DEBUG_TAG = "GameActivity";

    private GameController gameController;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        GameView gameView = new GameView(this);
        setContentView(gameView);

        gameController = new GameController(gameView,this);
        gameController.init();
        gameView.setController(gameController);

        Timer timer = new Timer();
        long period = (long)(1000.0/fps); //1/f in milliseconds where f is refresh rate
        timer.scheduleAtFixedRate(new TimerTask() {
            @Override
            public void run() {
                gameController.tick();
            }
        },1000,period);

        mDetector = new GestureDetectorCompat(this,this);
    }

    @Override
    public boolean onTouchEvent(MotionEvent event){
        this.mDetector.onTouchEvent(event);
        // Be sure to call the superclass implementation
        return super.onTouchEvent(event);
    }

    @Override
    public boolean onDown(MotionEvent event) {
        //Log.d(DEBUG_TAG,"onDown: " + event.toString());
        return true;
    }

    @Override
    public boolean onFling(MotionEvent event1, MotionEvent event2,
                           float velocityX, float velocityY) {
//        Log.d(DEBUG_TAG, "onFling: " + event1.toString()+event2.toString());
        gameController.onFling(velocityX,velocityY);

        return true;
    }

    @Override
    public void onLongPress(MotionEvent event) {
        //Log.d(DEBUG_TAG, "onLongPress: " + event.toString());
    }

    @Override
    public boolean onScroll(MotionEvent e1, MotionEvent e2, float distanceX,
                            float distanceY) {
        //Log.d(DEBUG_TAG, "onScroll: " + e1.toString()+e2.toString());
        return true;
    }

    @Override
    public void onShowPress(MotionEvent event) {
        //Log.d(DEBUG_TAG, "onShowPress: " + event.toString());
    }

    @Override
    public boolean onSingleTapUp(MotionEvent event) {
        //Log.d(DEBUG_TAG, "onSingleTapUp: " + event.toString());
        return true;
    }
}
