package com.salmat_team.offsetball;

import android.content.Context;
import android.support.v4.content.ContextCompat;

import math.geom2d.AffineTransform2D;
import math.geom2d.conic.Circle2D;
import math.geom2d.conic.EllipseShape2D;

/**
 * Created by adamita on 2016. 04. 27..
 */
public class Ball extends GameElement {
    double gPower;
    int maxGPower;
    EllipseShape2D circle;

    public Ball(Context context, int x, int y, int size, int maxGPower) {
        super(x, y, size, size);
        this.maxGPower = maxGPower;
        setBitmap(ContextCompat.getDrawable(context, R.drawable.ball));
        circle = new Circle2D(getCenterX(), getCenterY(), size >> 1);
    }

    public void Move(int x, int y) {
        setPosition(x, y);
        circle = circle.transform(AffineTransform2D.createTranslation(getX() - x, getY() - y));
    }

    public void MoveWith(int x, int y) {
        setPosition(getX() + x, getY() + y);
        circle = circle.transform(AffineTransform2D.createTranslation(x, y));
    }

    public void Fall(double g, double side) {
        if (gPower < maxGPower)
            gPower += g;

        if (gPower != 0)
            MoveWith((int) side, (int) gPower);
    }

    public boolean Fallen(int height)
    {
        return getY()>=height;
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

    public boolean onFloor(Floor floor)
    {
        return !circle.intersections(floor.getTop()).isEmpty();
    }
}
