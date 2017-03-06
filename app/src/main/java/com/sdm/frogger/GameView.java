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

/**
 * Created by spss on 17-3-6.
 */

public class GameView extends View {
    private InputStream gifInputStream;
    private Movie gifMovie;
    private long movieStart;

    public GameView(Context context) {
        super(context);

        //http://android-er.blogspot.com.au/2014/03/play-animated-gif-with.html
        //http://stackoverflow.com/questions/25572647/android-openrawresource-not-working-for-a-drawable
        gifInputStream = context.getResources().openRawResource(+ R.drawable.wolf);
        gifMovie = Movie.decodeStream(gifInputStream);
        movieStart = 0;

        //http://stackoverflow.com/questions/20416383/how-to-play-gif-in-android
        //need to turn off hardware rendering
        setLayerType(View.LAYER_TYPE_SOFTWARE, null);

        Log.d("GameView", "duration = " + gifMovie.duration());
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);

        Paint paint = new Paint();
        int x = getWidth();
        int y = getHeight();
        int radius = 100;

        //canvas background
        paint.setStyle(Paint.Style.FILL);
        paint.setColor(Color.BLACK);
        canvas.drawPaint(paint);

        //circle
        paint.setColor(Color.WHITE);
        canvas.drawCircle(x/2, y*3/4, radius, paint);

        //img
        int width = 400;
        int height = 280;
        Bitmap bm = BitmapFactory.decodeResource(getResources(), R.drawable.awoo);
        canvas.drawBitmap(Bitmap.createScaledBitmap(bm,400,280,false),x/2 - width/2,y/4 - height/2,paint);

        //animation
        long now = android.os.SystemClock.currentThreadTimeMillis();
        if(movieStart == 0){
            movieStart = now;
        }
        int dur = gifMovie.duration();
        int relTime = (int)((now - movieStart) % dur);

        System.out.println("time="+relTime+"\tdur="+gifMovie.duration());

        gifMovie.setTime(relTime);
        gifMovie.draw(canvas, x/2 - gifMovie.width()/2, y/2 - gifMovie.height()/2);

        invalidate();
    }
}
