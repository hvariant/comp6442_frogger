package com.sdm.frogger;

import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Rect;

/**
 * Created by spss on 17-3-12.
 */

public class FrogSprite extends Sprite {
    //testing only
    double r;

    FrogSprite(){
        this(0,0);
    }

    FrogSprite(double x, double y){
        super(x,y);

        r = 50;
    }

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
        paint.setColor(Color.WHITE);

        canvas.drawCircle((float)(x+r),(float)(y+r),(float)r,paint);
    }

    @Override
    public boolean update(GameController controller){
        //FIXME: respond to input
        return false;
    }
}
