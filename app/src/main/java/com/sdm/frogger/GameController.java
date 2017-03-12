package com.sdm.frogger;

import android.util.Log;
import android.view.View;

import java.util.ArrayList;

/**
 * Created by spss on 17-3-12.
 */

public class GameController {
    GameView gameView;
    GameActivity activity;
    ArrayList<Sprite> sprites;

    GameController(GameView gameView, GameActivity activity){
        this.gameView = gameView;
        this.activity = activity;

        this.sprites = new ArrayList<>();
    }

    public ArrayList<Sprite> getSprites(){ return sprites; }
    public GameView getGameView(){ return gameView; }

    public void onFling(double vx,double vy){
        Log.d("GameController", "Fling:" + vx + "," + vy);
    }

    public void init(){
        //testing only
        LogSprite logSprite = new LogSprite(100,100,20);
        this.getSprites().add(logSprite);
    }

    public void tick(){
        //Log.d("GameController","tick = " + System.currentTimeMillis());
        boolean dirty = false;
        for(Sprite sprite: sprites){
            if(sprite.update(this)){
                dirty = true;
            }
        }

        if(dirty){
            activity.runOnUiThread(new Runnable() {
                @Override
                public void run() {
                    gameView.invalidate();
                }
            });
        }
    }

    //FIXME
}
