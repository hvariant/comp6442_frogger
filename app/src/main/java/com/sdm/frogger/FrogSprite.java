package com.sdm.frogger;

import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Rect;
import android.util.Log;

/**
 * Created by spss on 17-3-12.
 */

public class FrogSprite extends Sprite {
    //testing only
    double r;
    private static double padding = 25;

    double vx;

    FrogSprite(double x, double y, double r){
        super(x,y);

        this.r = r;
        this.vx = 0;
    }

    public void setVelocity(double vx){
        this.vx = vx;
    }

    public boolean onLog(LogSprite logSprite){
        double lx,ly,lw,lh;
        lx = logSprite.getX();
        ly = logSprite.getY();
        lw = logSprite.getWidth();
        lh = logSprite.getHeight();

        double cx,cy,cr;
        cx = getX() + r;
        cy = getY() + r;
        double margin = r/2;

        return lx <= (cx - r + margin) && (cx + r - margin) <= (lx + lw) &&
            ly <= (cy - r + margin) && (cy + r - margin) <= (ly + lh);
    }

    private boolean dead = false;
    public void setDead(boolean dead) {
        this.dead = dead;
    }
    public boolean getDead(){ return dead; }

    @Override
    public double getWidth(){
        return 2*r;
    }

    @Override
    public double getHeight(){
        return 2*r;
    }

    @Override
    public void draw(Canvas canvas){
        Paint paint = new Paint();
        paint.setStyle(Paint.Style.FILL);

        if(getDead())
            paint.setColor(Color.CYAN);
        else
            paint.setColor(Color.RED);

        canvas.drawCircle((float)(getX()+r),(float)(getY()+r),(float)(r - padding),paint);
    }

    @Override
    public boolean update(GameController controller){
        FlingInput input = controller.getInput();
        boolean flag = false;

        if(input != null && !getDead()){
            double x_old,y_old;

            x_old = getX();
            y_old = getY();
            switch (input){
                case LEFT:
                {
                    setX(getX() - getWidth());
                    break;
                }
                case RIGHT:
                {
                    setX(getX() + getWidth());
                    break;
                }
                case UP:
                {
                    setY(getY() - getHeight());
                    break;
                }
                case DOWN:
                {
                    setY(getY() + getHeight());
                    break;
                }
            }
            if(outOfBound(controller.getGameView())){ //can't let people move out of bounds
                setX(x_old);
                setY(y_old);
            }

            flag = true;
        }

//        Log.d("FrogSprite", "vx = " + vx);

        if(!getDead())
            setX(getX() + vx);

        return flag;
    }
}
