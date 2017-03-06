package com.sdm.frogger;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.util.Pair;
import android.view.MotionEvent;
import android.view.View;

import java.util.ArrayList;

/**
 * Created by spss on 17-3-6.
 */

public class TouchView extends View implements View.OnTouchListener {
    ArrayList<Pair<Float,Float>> points;

    public TouchView(Context context) {
        super(context);

        setOnTouchListener(this);
        points = new ArrayList<>();
    }

    public boolean onTouch(View view, MotionEvent event){
        if(event.getX() < 0 || event.getY() < 0) return false;

        points.add(new Pair<>(event.getX(),event.getY()));
        invalidate();

        return true;
    }

    @Override
    protected void onDraw(Canvas canvas){
        Paint paint = new Paint();
        paint.setColor(Color.BLACK);
        paint.setStrokeWidth(10);

        for(int i=0;i<points.size()-1;i++){
            Pair<Float,Float> p1,p2;
            p1 = points.get(i);
            p2 = points.get(i+1);
            canvas.drawLine(p1.first, p1.second, p2.first, p2.second, paint);
        }
    }
}
