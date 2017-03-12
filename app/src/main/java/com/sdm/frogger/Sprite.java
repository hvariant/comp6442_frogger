package com.sdm.frogger;

import android.graphics.Canvas;
import android.graphics.Paint;
import android.view.View;

/**
 * Created by spss on 17-3-12.
 */

public abstract class Sprite {
    private double x,y;

    Sprite(){
        this(0,0);
    }

    Sprite(double x,double y) {
        this.x = x;
        this.y = y;
    }

    public double getX(){ return x; }
    public double getY(){ return y; }
    public void setX(double x){
        this.x = x;
    }
    public void setY(double y){
        this.y = y;
    }

    public abstract double getWidth();
    public abstract double getHeight();

    public abstract void draw(Canvas canvas);
    public abstract boolean update(GameController controller);

    public boolean outOfBound(View view){
        return !(0 <= x && (x+getWidth()) <= view.getWidth() &&
                0 <= y && (y+getHeight()) <= view.getHeight());
    }
}
