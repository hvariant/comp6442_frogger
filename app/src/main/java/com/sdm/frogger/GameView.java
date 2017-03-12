package com.sdm.frogger;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Movie;
import android.graphics.Paint;
import android.os.Build;
import android.util.Log;
import android.view.View;

import java.io.InputStream;
import java.util.ArrayList;

/**
 * Created by spss on 17-3-6.
 */

public class GameView extends View {
    GameController gameController;

    public GameView(Context context) {
        super(context);

        //http://stackoverflow.com/questions/20416383/how-to-play-gif-in-android
        //need to turn off hardware rendering
        setLayerType(View.LAYER_TYPE_SOFTWARE, null);
    }

    public void setController(GameController gameController){
        this.gameController = gameController;
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);

        Paint paint = new Paint();

        //canvas background
        paint.setStyle(Paint.Style.FILL);
        paint.setColor(Color.BLACK);
        canvas.drawPaint(paint);

        //get sprites from controller & draw
        ArrayList<Sprite> sprites = gameController.getSprites();
        for(Sprite sprite: sprites){
            sprite.draw(canvas);
        }
    }
}
