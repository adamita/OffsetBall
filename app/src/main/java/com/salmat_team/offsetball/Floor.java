package com.salmat_team.offsetball;

import android.annotation.TargetApi;
import android.app.Activity;
import android.content.Context;
import android.graphics.Canvas;
import android.graphics.drawable.Drawable;
import android.graphics.drawable.shapes.Shape;
import android.os.Build;
import android.support.v4.content.ContextCompat;
import android.util.Log;

/**
 * Created by adamita on 2016. 04. 27..
 */
public class Floor extends GameElement{

    protected double substance;

    public Floor(Context context, int x, int y, int width, int height, double substance)
    {
        super(context,x,y,width,height);

        this.substance=substance;

        drawing=ContextCompat.getDrawable(context, R.drawable.floor);

    }


    public void Move(float m, int w)
    {
        int x=this.getX();
        int e=(this.getWidth()<<10);
        if(m>1)
        {
            if(w>this.getX()+this.getWidth())
            {x=this.getX()+20;}
        }
        else
        {
            if(m<-1)
            {
                if(0<this.getX())
                {x=this.getX()-20;}
            }
        }
        /*if((this.getX()+e)<(w/2))
        {
            x=(int)(((w/2)-e)*((int)m));
        }
        else
        {
            x=(int)(((w/2)+(w-e))*((int)m));
        }*/
        setPosition(x,this.getY());
        Log.i("Game","Floor e="+e);
        Log.i("Game","Floor x="+x);
        Log.i("Game","Floor m="+m);
    }

    public double getSubstance() {
        return substance;
    }
}
