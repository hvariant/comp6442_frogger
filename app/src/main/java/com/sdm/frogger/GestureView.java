package com.sdm.frogger;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.support.v4.view.GestureDetectorCompat;
import android.util.Log;
import android.util.Pair;
import android.view.GestureDetector;
import android.view.MotionEvent;
import android.view.View;

import java.util.ArrayList;

/**
 * Created by spss on 17-3-6.
 */

public class GestureView extends View {
    private String DEBUG_TAG = "GesturesView";
    private float vx,vy;

    public GestureView(Context context) {
        super(context);
    }

    public void updateDirection(float x, float y) {
        vx = x/5;
        vy = y/5;

        invalidate();
    }

    @Override
    protected void onDraw(Canvas canvas){
        Paint paint = new Paint();
        paint.setColor(Color.BLACK);
        paint.setStrokeWidth(10);

        int width = getWidth();
        int height = getHeight();
        canvas.drawLine(width/2, height/2, width/2 + vx, height/2 + vy, paint);
    }
}
