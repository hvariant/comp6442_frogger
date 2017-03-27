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

    private static double padding = 20;

    LogSprite(double x, double y, double h, double vx){
        super(x,y);

        w = 400;
        this.h = h;
        this.vx = vx;
    }

    public double getVelocity(){ return vx; }

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
        paint.setColor(Color.YELLOW);

        canvas.drawRect(new Rect((int)getX(),(int)(getY()+padding),(int)(getX()+w),(int)(getY()+h-padding)),paint);
    }

    @Override
    public boolean update(GameController controller){
        setX(getX() + vx);

        if(outOfBound(controller.getGameView())){ //turn back!
            this.vx = -vx;
//            setX(getX() + vx);
        }

        return true;
    }
}
