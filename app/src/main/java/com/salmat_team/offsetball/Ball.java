package com.salmat_team.offsetball;

import android.content.Context;
import android.support.v4.content.ContextCompat;

/**
 * Created by adamita on 2016. 04. 27..
 */
public class Ball extends GameElement {
    double gPower;
    int maxGPower;


    public Ball(Context context, int x, int y, int size, int maxGPower) {
        super(x, y, size, size);
        this.maxGPower = maxGPower;

        setBitmap(ContextCompat.getDrawable(context, R.drawable.ball));
    }

    public void Move(int x, int y) {
        setPosition(x, y);
    }

    public void MoveWith(int x, int y) {
        setPosition(getX() + x, getY() + y);
    }

    public void Fall(double g, double side) {
        if (gPower < maxGPower)
            gPower += g;

        if (gPower != 0)
            setPosition((int) (getX() + side), (int) (getY() + gPower));
    }

    public boolean Fallen(int height)
    {
        return getY()>=height;
    }

    public boolean OnFloor(Floor floor, boolean bump)
    {
        if(OnFloor(floor))
        {
            if(!bump)
            {
                setPosition(getX(),floor.getY()-getHeight()+1);
                gPower=0;
            }
            else
            {
                setPosition(getX(),floor.getY()-getHeight());
                gPower=-gPower+floor.getSubstance();
                if(gPower>0)
                {
                    gPower = 0;
                    setPosition(getX(), floor.getY() - getHeight() + 1);
                }
            }
            return true;
        }
        else
        {
            return false;
        }

    }

    private boolean OnFloor(Floor floor)
    {
        return onTop(floor);
    }
}
