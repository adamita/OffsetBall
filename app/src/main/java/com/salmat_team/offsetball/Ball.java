package com.salmat_team.offsetball;

import android.content.Context;
import android.support.v4.content.ContextCompat;

import java.util.Collection;
import java.util.List;

import math.geom2d.AffineTransform2D;
import math.geom2d.Point2D;
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
//        circle = circle.transform(AffineTransform2D.createTranslation(x - getX(), y - getY()));
        setPosition(x, y);

    }

    public void MoveWith(int x, int y) {
        circle = circle.transform(AffineTransform2D.createTranslation(x, y));
        setPosition(getX() + x, getY() + y);

    }

    public void Fall(double g, double side, List<Floor> floors) {

        EllipseShape2D hcircle;
        if (gPower < maxGPower)
            gPower += g;

        if (gPower != 0) {
            hcircle = circle.transform(AffineTransform2D.createTranslation((int) side, (int) gPower));


            boolean falling = true;
            int i;
            Collection<Point2D> intersections;
            for (i = floors.size() - 1; i >= 0 && falling; i--) {
                intersections = hcircle.intersections(floors.get(i).getTop());
                if (!intersections.isEmpty())
                    falling = false;
            }

            if (falling) {
                MoveWith((int) side, (int) gPower);
            }
//            else
//            {
//                int y=0;
//
//                Move(getX() + (int) side,y);
//            }
        }


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


}
