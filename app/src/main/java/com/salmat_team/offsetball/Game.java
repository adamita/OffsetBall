package com.salmat_team.offsetball;

import android.app.Activity;
import android.content.Context;
import android.graphics.Canvas;
import android.view.View;
import android.widget.Toast;

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
    int height;

    Activity activity;

    private Ball ball;
    private Floor floor;
    private Floor floor2;

    public Game(final Context context, final View view, int width, int height)
    {
        this.width=width;
        this.height=height;
        this.view=view;
        this.context=context;


        ball=new Ball(context,width/2,0,30,2);
        floor=new Floor(context,(width-100)/2,400,100,25,3);
        floor2=new Floor(context,(width-300)/2,500,300,25,3);

//        this.view=view;
//        view.post(new Runnable() {
//            @Override
//            public void run() {
//                width=view.getMeasuredWidth();
//                ball=new Ball(context,width/2,0,30,2);
//                floor=new Floor(context,width/2,400,100,25,3);
//                StartGame();
//            }
//        });
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

        boolean falling = !ball.OnFloor(floor, true);

        //if(){

//        floor.Move(-MotionSensor.getX(),width);
//        //}else
//        floor2.Rotated(-MotionSensor.getX());
//        //}

        floor.Move(ball, -MotionSensor.getX(),width);
        /*}else{
        floor.Rotated(MotionSensor.getX());
        }*/

        if(falling)
        {
            ball.Fall(5,-MotionSensor.getX());
        }

        if(ball.Fallen(height))
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
        }
    }
}
