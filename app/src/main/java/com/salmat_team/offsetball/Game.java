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

    private Ball ball;
    private List<Floor> floorList = new ArrayList<>();

    public Game(Context context, View view)
    {
        this.view=view;
        ball=new Ball(context,0,0,100,20);
        floorList.add(new Floor(context,0,400,300,50,3));
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
        for (Floor floor:floorList)
        {
            floor.Draw(canvas);
        }
    }

    private void Gravitation()
    {
        boolean falling=true;
        Floor floor=null;
        for (int i=0; i<floorList.size() && falling; i++)
        {
            floor=floorList.get(i);
            if(ball.OnFloor(floor,true)) {
                falling = false;
            }
        }

        if(falling)
        {
            ball.Fall(1.5,-MotionSensor.getX());
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
