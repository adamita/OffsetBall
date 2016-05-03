package com.salmat_team.offsetball;

import android.app.Activity;
import android.content.Context;
import android.graphics.Canvas;
import android.view.View;
import android.widget.Toast;

import java.util.ArrayList;
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
    //    Invalidate invalidateGame=new Invalidate();
    int width;
    int height;

    Activity activity;

    private Ball ball;
    private ArrayList<Floor> floors = new ArrayList<>();

    public Game(final Context context, final View view, int width, int height)
    {
        this.width=width;
        this.height=height;
        this.view=view;
        this.context=context;


        ball = new Ball(context, width / 2, 0, 30, 10, true, width, height);
        floors.add(new Floor(context, (width - 100) / 2, 400, 100, 25, 2, width, height));
        floors.add(new Floor(context, (width - 300) / 2, 500, 300, 25, 2, width, height));
//        floors.get(1).setRotate(30);

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


        for (Floor floor : floors) {
            floor.Draw(canvas);
        }

    }

    private void Gravitation()
    {


        ball.Fall(2, -MotionSensor.getX() * 2, floors);

        floors.get(0).Move(ball, -MotionSensor.getX());
        floors.get(1).Rotate(ball, -MotionSensor.getX() * 10);


        if (ball.Fallen())
        {
            timer.cancel();
            view.post(new Runnable() {
                @Override
                public void run() {
                    Toast.makeText(context, "Game Over!",
                            Toast.LENGTH_LONG).show();
                }
            });
            view.postInvalidate();

        }

    }

    public class TimerElapsed extends TimerTask
    {
        @Override
        public void run() {
            Gravitation();
            view.postInvalidate();


//            view.post(invalidateGame);


        }
    }

//    public class Invalidate implements Runnable {
//
//        @Override
//        public void run() {
//            if(ball!=null)
//                view.invalidate(ball.getRect());
//
//            if(floors!=null)
//                for (Floor floor : floors)
//                {
//                    view.invalidate(floor.getShape());
//                }
//        }
//    }
}
