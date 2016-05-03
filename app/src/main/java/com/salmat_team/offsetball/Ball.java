package com.salmat_team.offsetball;

import android.content.Context;
import android.support.v4.content.ContextCompat;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import math.geom2d.AffineTransform2D;
import math.geom2d.Point2D;
import math.geom2d.conic.EllipseShape2D;
import math.geom2d.line.Line2D;

/**
 * Created by adamita on 2016. 04. 27..
 */
public class Ball extends GameElement {
    double gPower;
    double sPower;
    int maxGPower;
    Boolean bumping;

    Collection<Point2D> intersections = new ArrayList<Point2D>();

    public Ball(Context context, int x, int y, int size, int maxGPower, Boolean bumping, int screenWidth, int screenHeight) {
        super(x, y, size, size, screenWidth, screenHeight);
        this.bumping = bumping;
        this.maxGPower = maxGPower;
        gPower = 0;
        sPower = 0;
        setBitmap(ContextCompat.getDrawable(context, R.drawable.ball));

    }

//    public void Move(int x, int y) {
////        circle = circle.transform(AffineTransform2D.createTranslation(x - getX(), y - getY()));
//        setPosition(x, y);
//
//    }

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


    public void Fall(double g, double side, List<Floor> floors) {

        EllipseShape2D hcircle;
        Floor floor = null;


        //Földön állunk?
        for (Floor f : floors) {
            intersections = circle.intersections(f.getTopLine());
            if (!intersections.isEmpty()) {
                floor = f;
                break;
            }
        }

        if (floor == null) {
            //Ha nem földön állunk akkor esni kell
            gPower += sPower;
            sPower = 0;

            if (gPower < maxGPower)
                gPower += g;
            else
                gPower = maxGPower;


            hcircle = circle.transform(AffineTransform2D.createTranslation((int) side, (int) gPower));


            for (Floor f : floors) {
                intersections = hcircle.intersections(f.getTopLine());
                if (!intersections.isEmpty()) {
                    floor = f;
                    break;
                }
            }


            if (floor == null) {

                MoveWith((int) side, (int) gPower);

            } else {

                setOnFloorTop(floor);

                if (!bumping) {
                    gPower = 0;
                } else {
                    gPower = -gPower + floor.getSubstance();
                }


            }
        } else {
            //Ha földön állunk gurulunk

            float gradient = floor.getRotate() / 90;
            if (sPower < maxGPower * gradient)
                sPower += g * gradient;
            else
                sPower = maxGPower * gradient;

            MoveWith((int) sPower, (int) ((int) floor.getTopLine().distance(getCenterX() + sPower, getBottom()) + gPower));

        }

    }

    public boolean Fallen()
    {
        return getY() >= screenHeight;
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
