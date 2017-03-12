package com.sdm.frogger;

import android.util.Log;
import android.view.View;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.Objects;

import static com.sdm.frogger.FlingInput.DOWN;
import static com.sdm.frogger.FlingInput.LEFT;
import static com.sdm.frogger.FlingInput.RIGHT;
import static com.sdm.frogger.FlingInput.UP;

/**
 * Created by spss on 17-3-12.
 */

public class GameController {
    GameView gameView;
    GameActivity activity;
    ArrayList<Sprite> sprites;

    Object flingInputsLock = new Object();
    LinkedList<FlingInput> flingInputs;

    GameController(GameView gameView, GameActivity activity){
        this.gameView = gameView;
        this.activity = activity;

        this.sprites = new ArrayList<>();
        this.flingInputs = new LinkedList<>();
    }

    public ArrayList<Sprite> getSprites(){ return sprites; }
    public GameView getGameView(){ return gameView; }

    private FlingInput getDirection(double vx,double vy){
        double cosT = Math.cos(Math.toRadians(45));
        double sinT = Math.sin(Math.toRadians(45));

        double vx2 = vx*cosT - vy*sinT;
        double vy2 = vx*sinT + vy*cosT;

        if(vx2 >= 0 && vy2 >= 0){
            return FlingInput.RIGHT;
        } else if(vx2 < 0 && vy2 >= 0){
            return FlingInput.DOWN;
        } else if(vx2 < 0 && vy2 < 0){
            return FlingInput.LEFT;
        } else {
            return FlingInput.UP;
        }
    }

    public void onFling(double vx,double vy){
        Log.d("GameController", "Fling:" + vx + "," + vy);

        //FIXME: recognize direction
        FlingInput input = getDirection(vx,vy);

        //not sure if lock is needed but just in case
        synchronized (flingInputsLock){
            flingInputs.add(input);
        }
    }

    public FlingInput getInput(){
        FlingInput ret = null;

        synchronized (flingInputsLock){
            if(flingInputs.size() > 0){
                ret = flingInputs.getLast();
                flingInputs.clear();
            }
        }

        return ret;
    }

    public void init(){
        //testing only
        LogSprite logSprite = new LogSprite(100,100,20);
        this.getSprites().add(logSprite);

        //center of screen
        FrogSprite frogSprite = new FrogSprite(500, 1000);
        this.getSprites().add(frogSprite);
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
