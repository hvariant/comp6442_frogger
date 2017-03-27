package com.sdm.frogger;

import android.graphics.Color;
import android.util.Log;
import android.view.View;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.Objects;
import java.util.Random;

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

    ArrayList<LogSprite> logSprites;
    FrogSprite frogSprite;

    Object flingInputsLock = new Object();
    LinkedList<FlingInput> flingInputs;

    GameController(GameView gameView, GameActivity activity){
        this.gameView = gameView;
        this.activity = activity;

        this.sprites = new ArrayList<>();
        this.logSprites = new ArrayList<>();
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

    private int dH = 200;

    public void init(){
        BGSprite water = new BGSprite(0,2*dH,1080,3*dH, Color.BLUE);
        this.getSprites().add(water);

        BGSprite land = new BGSprite(0,0,1080,2*dH, Color.GREEN);
        this.getSprites().add(land);

        BGSprite land2 = new BGSprite(0,5*dH,1080,1920-5*dH, Color.GREEN);
        this.getSprites().add(land2);

        Random random = new Random();
        //testing only
        for(int i=0;i<3;i++) {
            LogSprite logSprite = new LogSprite(0, 2*dH+dH*i, dH, 10+10*random.nextDouble());
            this.getSprites().add(logSprite);
            this.logSprites.add(logSprite);
        }

        //center of screen
        frogSprite = new FrogSprite(540 - dH/2, 7*dH, dH/2);
        this.getSprites().add(frogSprite);
    }

    public void tick(){
        //Log.d("GameController","tick = " + System.currentTimeMillis());

        // collision detection
        if(!frogSprite.getDead()) {
            LogSprite attachedLog = null;
            for (LogSprite logSprite : logSprites) {
                if (frogSprite.onLog(logSprite)) {
                    attachedLog = logSprite;
                    break;
                }
            }

            if (attachedLog != null) {
                frogSprite.setVelocity(attachedLog.getVelocity());
            } else {
                frogSprite.setVelocity(0);
            }

            if (frogSprite.getY() >= 2*dH && frogSprite.getY() < 5*dH && attachedLog == null) {
                frogSprite.setDead(true);
            }
        }

        for (Sprite sprite : logSprites) {
            sprite.update(this);
        }
        frogSprite.update(this);

//        Log.d("LogSprite","vx=" + logSprites.get(2).getVelocity());

        activity.runOnUiThread(new Runnable() {
            @Override
            public void run() {
                gameView.invalidate();
            }
        });
    }

    //FIXME
}
