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
    Context context;
    Timer timer;
    TimerTask elapsed=new TimerElapsed();
    int width;

    private Ball ball;
    private Floor floor;
    private Floor floor2;

    public Game(Context context, View view)
    {
        width=480;
        this.view=view;
        ball=new Ball(context,width/2,0,30,2);
        floor=new Floor(context,(width-100)/2,400,100,25,3);
        floor2=new Floor(context,(width-300)/2,500,300,25,3);
        this.context=context;
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
        float w=floor2.getRotated();
        canvas.save();
        canvas.rotate(w,240,500);
        floor2.Draw(canvas);
        canvas.restore();

    }

    private void Gravitation()
    {
        boolean falling=true;
        if(ball.OnFloor(floor,true)) {
            falling = false;
        }

        //if(){
        floor.Move(-MotionSensor.getX(),width);
        //}else{
        floor2.Rotated(-MotionSensor.getX());
        //}

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
