package com.salmat_team.offsetball.Game;

import android.content.Context;
import android.support.v4.content.ContextCompat;
import android.util.Log;

import com.salmat_team.offsetball.R;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Random;

import math.geom2d.AffineTransform2D;
import math.geom2d.Point2D;
import math.geom2d.conic.Circle2D;
import math.geom2d.conic.EllipseShape2D;
import math.geom2d.line.Line2D;

/**
 * Created by adamita on 2016. 04. 27..
 */
public class Ball extends GameElement {

    float fallTime;

    private double gPower;
    private double sPower;
    int maxGPower;
    Boolean bumping;

    Collection<Point2D> intersections = new ArrayList<Point2D>();

    public Ball(Context context, double centerX, double centerY, int size, int maxGPower, Boolean bumping, int screenWidth, int screenHeight) {
        super(centerX, centerY, size, size, screenWidth, screenHeight);
        this.bumping = bumping;
        this.maxGPower = maxGPower;
        setgPower(0);
        setsPower(0);
        fallTime = 0;
        setBitmap(ContextCompat.getDrawable(context, R.drawable.ball));

    }


    public void MoveWith(int x, int y) {
        if (screenWidth < getRight() + x)
            setPosition(screenWidth - getWidth(), getY() + y);
        else if (0 > getX() + x)
            setPosition(0, getY() + y);
        else
            setPosition(getX() + x, getY() + y);
    }

    public void setOnFloorTop(Floor floor) {
        Line2D topLine = floor.getTopLine();
        double dist = topLine.distance(getCenterX(), getBottom());
        double disth = topLine.distance(getCenterX(), getBottom() + 1);

        if (dist > disth) {
            //felette
            MoveWith(0, (int) dist);

        } else {
            //alatta
            MoveWith(0, (int) -dist);
        }
    }

    public void setOnFloorBottom(Floor floor) {
        Line2D topLine = floor.getBottomLine();
        double dist = topLine.distance(getCenterX(), getY());
        double disth = topLine.distance(getCenterX(), getY() + 1);

        if (dist > disth) {
            //felette
            MoveWith(0, (int) -dist);

        } else {
            //alatta
            MoveWith(0, (int) dist);
        }
    }

    public Boolean isOnTop(Floor floor) {
        return !circle.intersections(floor.getTopLine()).isEmpty();
    }

    public Boolean isOnBottom(Floor floor) {
        return !circle.intersections(floor.getTopLine()).isEmpty();
    }

    public void BuncingMove(Floor f)
    {
        /*
        if(r.nextInt(101)<5)
        {
            if(r.nextInt(1)==0)
                gPower++;
            else
                gPower--;
        }

        if(r.nextInt(101)<5)
        {
            if(r.nextInt(1)==0)
                sPower++;
            else
                sPower--;
        }*/

        int x= (int) (getX()+ getsPower());
        int y= (int) (getY()+ getgPower());

        //Log.i("Ball","gPower: "+gPower);
        //Log.i("Ball","sPower: "+sPower);
        //Log.i("Ball","x,y: "+x+","+y);

        if(x<0) {
            setsPower(-sPower);
            x=0;

        }else if(x+getWidth()>screenWidth){
            setsPower(-sPower);
            x=screenWidth-getWidth();
        }

        if(y<0) {
            setgPower(-gPower);
            y=1;

        }

        setPosition(x,y);

        if (isOnTop(f)) {
            setOnFloorTop(f);
            setgPower(-gPower);
        }

    }

    EllipseShape2D hcircle=null;
    Floor floor = null;

    public void Fall(double g, double side, Floor f) {



        //Log.i("Ball","gBefore: "+gPower);

        //Földön állunk?
        //for (Floor f : floors) {
            intersections = circle.intersections(f.getTopLine());
            if (!intersections.isEmpty()) {
                floor = f;
                //break;
            }
        //}

        if (floor == null) {
            //Ha nem földön állunk akkor esni kell

            setgPower(getgPower() + getsPower());
            setsPower(0);


            double newgPower;

            if (g + getgPower() < maxGPower)
                newgPower = getgPower() + g;
            else
                newgPower = maxGPower;

            hcircle = circle.transform(AffineTransform2D.createTranslation((int) side, (int) newgPower));


            //for (Floor f : floors) {
                intersections = hcircle.intersections(f.getTopLine());
                if (!intersections.isEmpty()) {
                    floor = f;
                   // break;
                }
            //}


            if (floor == null) {

                setgPower(newgPower);
                MoveWith((int) side, (int) getgPower());

            } else {
                //Log.i("Ball","Ráesett");


                setOnFloorTop(floor);

                if (!bumping) {
                    setgPower(0);
                } else {
                    setgPower((-getgPower()) + floor.getSubstance());
                }


            }
        } else {
            //Ha földön állunk gurulunk

            //Log.i("Ball","Gurul");

            float gradient = floor.getRotate() / 90;

            double pluspower = g * (gradient/2);
            double realMaxG = maxGPower * (gradient/2);
            if (gradient<0 && sPower + pluspower > realMaxG)
                setsPower(realMaxG);
            else if (gradient>0 && sPower + pluspower < -realMaxG)
                setsPower(-realMaxG);
            else
                setsPower(sPower + pluspower);


            if (gPower > 0)
                setgPower(0);

            MoveWith((int) sPower, (int) ((int) floor.getTopLine().distance(getCenterX() + sPower, getBottom()) + gPower));
        }

        Log.i("Ball","sPower: "+sPower);
        //Log.i("Ball","gAfter: "+gPower);

    }

    public boolean Fallen()
    {
        return getY() >= screenHeight;
    }

    public double getgPower() {
        return gPower;
    }

    public Ball setgPower(double gPower) {
        this.gPower = gPower;
        return this;
    }

    public double getsPower() {
        return sPower;
    }

    public Ball setsPower(double sPower) {
        this.sPower = sPower;
        return this;
    }

//    public boolean onFloor(Floor floor, boolean bump)
//    {
//        if(onFloor(floor))
//        {
//            if(!bump)
//            {
//                setPosition(getX(),floor.getY()-getHeight()+1);
//                gPower=0;
//            }
//            else
//            {
//                setPosition(getX(),floor.getY()-getHeight());
//                gPower=-gPower+floor.getSubstance();
//                if(gPower>0)
//                {
//                    gPower = 0;
//                    setPosition(getX(), floor.getY() - getHeight() + 1);
//                }
//            }
//            return true;
//        }
//        else
//        {
//            return false;
//        }
//
//    }


}
