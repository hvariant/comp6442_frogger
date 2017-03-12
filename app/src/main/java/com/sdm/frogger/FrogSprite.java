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

        canvas.drawCircle((float)(getX()+r),(float)(getY()+r),(float)r,paint);
    }

    @Override
    public boolean update(GameController controller){
        FlingInput input = controller.getInput();

        if(input != null){
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

            return true;
        }

        return false;
    }
}
