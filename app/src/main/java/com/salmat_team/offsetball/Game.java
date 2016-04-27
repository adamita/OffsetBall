package com.salmat_team.offsetball;

import android.content.Context;
import android.graphics.Canvas;
import android.hardware.SensorManager;
import android.view.View;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Timer;
import java.util.TimerTask;

/**
 * Created by adamita on 2016. 04. 27..
 */
public class Game {
    View view;
    Timer timer;
    TimerTask elapsed=new TimerElapsed();
    int width;

    private Ball ball;
    private Floor floor;

    public Game(Context context, View view)
    {
        int w=480;//jó lenne leolvasni a szélességet
        width=w;
        this.view=view;
        ball=new Ball(context,w/2,0,30,2);
        floor=new Floor(context,w/2,400,100,25,3);
    }

    public void StartGame()
    {
        timer=new Timer();
        timer.schedule(elapsed,0,100);
    }

    public void PauseGame()
    {
        timer.cancel();
    }

    public void Draw(Canvas canvas)
    {
        ball.Draw(canvas);
        floor.Draw(canvas);

    }

    private void Gravitation()
    {
        boolean falling=true;
        if(ball.OnFloor(floor,true)) {
            falling = false;
        }

        //if(){
        floor.Move(-MotionSensor.getX(),width);
        /*}else{
        floor.Rotated(MotionSensor.getX());
        }*/

        if(falling)
        {
            ball.Fall(5,-MotionSensor.getX());
        }

    }

    public class TimerElapsed extends TimerTask
    {
        @Override
        public void run() {
            Gravitation();

            view.postInvalidate();
        }
    }
}
