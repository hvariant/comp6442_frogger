package com.sdm.frogger;

import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.support.annotation.ColorInt;
import android.util.Log;

/**
 * Created by spss on 17-3-27.
 */

public class BGSprite extends Sprite {
    private double w,h;
    private @ColorInt int color;

    BGSprite(int x, int y, int w, int h, @ColorInt int color){
        super(x,y);

        this.w = w;
        this.h = h;
        this.color = color;
    }

    @Override
    public double getWidth() { return w; }

    @Override
    public double getHeight() { return h; }

    @Override
    public void draw(Canvas canvas) {
        Paint paint = new Paint();
        paint.setStyle(Paint.Style.FILL_AND_STROKE);
        paint.setColor(color);

        float x,y,w,h;
        x = (float)getX();
        y = (float)getY();
        w = (float)getWidth();
        h = (float)getHeight();

        canvas.drawRect(x,y,x+w,y+h,paint);
    }

    @Override
    public boolean update(GameController controller) {
        return false;
    }
}
