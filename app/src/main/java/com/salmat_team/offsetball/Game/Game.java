package com.salmat_team.offsetball.Game;

import android.content.Context;
import android.graphics.Canvas;
import android.view.View;
import android.widget.Toast;

import com.salmat_team.offsetball.R;
import com.salmat_team.offsetball.View.GameView.GameActivity;
import com.salmat_team.offsetball.Common.MotionSensor;

import java.util.Random;
import java.util.Timer;
import java.util.TimerTask;

/**
 * Created by adamita on 2016. 04. 27..
 */
public class Game {

    Boolean end=false;
    TimerTask elapsed=new TimerElapsed();
    private View view;
    private Context context;
    private Timer timer;
    private int width;
    private int height;
    private float density;
    private long timerTime;
    private Ball ball;
    //private ArrayList<Floor> floors = new ArrayList<>();
    private Floor floor;
    private int numFrag;
    Random r=new Random();

    public Game(final Context context, final View view, int width, int height)
    {
        this.width=width;
        this.height=height;
        this.view=view;
        this.context=context;
        density = context.getResources().getDisplayMetrics().density;

        timerTime = 50;


        //floors.add(new Floor(context, 0.5, 0.5, (int) (65 * density), (int) (15 * density), 1, width, height));
        //floors.add(new Floor(context, 0.5, 0.8, (int) (200 * density), (int) (15 * density), 1, width, height));
        if(1==Integer.valueOf(view.getTag().toString())) {
            floor = (new Floor(context, 0.5, 0.9, (int) (100 * density), (int) (15 * density), 0, width, height));
            r.setSeed((long) (MotionSensor.getX() * MotionSensor.getY() * MotionSensor.getZ() * 1000));
            double s=0;
            double g=0;

            s = (r.nextInt(8) + 2) * density;
            g = (r.nextInt(8) +2) * density;

            if(r.nextInt(1)==0)
                s=-s;

            if(r.nextInt(1)==0)
                g=-g;

            ball = new Ball(context, 0.5, 0.05, (int) (20 * density), 15, false, width, height).setgPower(g).setsPower(s);
        }
        else
        {
            floor=(new Floor(context, 0.5, 0.8, (int) (200 * density), (int) (15 * density), 1, width, height));
            ball = new Ball(context, 0.5, 0.05, (int) (20 * density), 15, false, width, height);
        }
//        floors.get(1).setRotate(30);

    }

    public void StartGame()
    {
        timer=new Timer();
        timer.schedule(elapsed, 0, timerTime);
    }

    public void PauseGame()
    {
        timer.cancel();
    }

    public void Draw(Canvas canvas)
    {
        ball.Draw(canvas);

        //for (Floor floor : floors) {
            floor.Draw(canvas);
        //}

    }

    private void Gravitation()
    {


        if(1==Integer.valueOf(view.getTag().toString()))
        {
            ball.BuncingMove(floor);
            floor.Move(ball, -MotionSensor.getX() * density);
        }
        else
        {
            ball.Fall(2 * density, -MotionSensor.getX() * density, floor);
            floor.Rotate(ball, -MotionSensor.getX() * 8 * density);
        }

        //floors.get(0).Move(ball, -MotionSensor.getX() * density);
        //floors.get(1).Rotate(ball, -MotionSensor.getX() * 10);


        if (ball.Fallen())
        {
            timer.cancel();
            view.post(new Runnable() {
                @Override
                public void run() {
                    Toast.makeText(context, R.string.GameOver,
                            Toast.LENGTH_LONG).show();
                }
            });
            view.postInvalidate();
            //BackTime();
            //view.findViewById(R.layout.game_layout).getContext();
            GameActivity GA=(GameActivity) context;
            GA.BackTime();
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
