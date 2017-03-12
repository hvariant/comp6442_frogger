package com.sdm.frogger;

import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Rect;
import android.view.View;

/**
 * Created by spss on 17-3-12.
 */

public class LogSprite extends Sprite {
    //testing only
    double w,h;
    double vx;

    LogSprite(){
        this(0,0,5);
    }

    LogSprite(double x, double y, double vx){
        super(x,y);

        w = 100;
        h = 20;
        this.vx = vx;
    }

    @Override
    public double getWidth(){
        return w;
    }

    @Override
    public double getHeight(){
        return h;
    }

    @Override
    public void draw(Canvas canvas){
        Paint paint = new Paint();
        paint.setStyle(Paint.Style.FILL);
        paint.setColor(Color.WHITE);

        canvas.drawRect(new Rect((int)getX(),(int)getY(),(int)(getX()+w),(int)(getY()+h)),paint);
    }

    @Override
    public boolean update(GameController controller){
        setX(getX() + vx);

        if(outOfBound(controller.getGameView())){ //turn back!
            this.vx = -vx;
            setX(getX() + 2*vx);
        }

        return true;
    }
}
