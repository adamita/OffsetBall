package com.salmat_team.offsetball;

import android.annotation.TargetApi;
import android.content.Context;
import android.content.res.Resources;
import android.graphics.Canvas;
import android.graphics.drawable.Drawable;
import android.graphics.drawable.DrawableWrapper;
import android.os.Build;
import android.support.v4.content.ContextCompat;
import android.util.Log;

/**
 * Created by adamita on 2016. 04. 27..
 */
public class Ball extends GameElement{
    double gPower;
    int maxGPower;

    public Ball(Context context, int x, int y, int size, int maxGPower)
    {
        super(context,x,y,size,size);

        gPower=0;
        this.maxGPower=maxGPower;

        drawing=ContextCompat.getDrawable(context, R.drawable.ball);
    }

    public void Move(int x, int y)
    {
        setPosition(x,y);
    }

    public void Fall(double g, double side)
    {
        if(gPower<maxGPower)
            gPower+=g;

        if(gPower!=0)
            setPosition((int)(getX()+side), (int)(getY()+gPower));


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
                    gPower=0;
                    setPosition(getX(),floor.getY()-getHeight()+1);
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
        return Intersect(floor);
    }
}
