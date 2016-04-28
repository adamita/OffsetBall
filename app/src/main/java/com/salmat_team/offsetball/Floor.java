package com.salmat_team.offsetball;

import android.annotation.TargetApi;
import android.app.Activity;
import android.content.Context;
import android.content.SharedPreferences;
import android.graphics.Canvas;
import android.graphics.drawable.Drawable;
import android.graphics.drawable.shapes.Shape;
import android.os.Build;
import android.preference.PreferenceManager;
import android.support.v4.content.ContextCompat;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.view.animation.RotateAnimation;
import android.widget.TextView;

/**
 * Created by adamita on 2016. 04. 27..
 */
public class Floor extends GameElement{

    protected double substance;
    private float rotated;

    public Floor(Context context, int x, int y, int width, int height, double substance)
    {
        super(context,x,y,width,height);
        rotated=0;
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
    }

    public float getRotated(){return rotated;}

    public void Rotated(float m)
    {
        //int x=this.getX();
        //int e=(this.getWidth()<<10);



        //view.setRotation();

        if(m>1)
        {
            if(rotated<30){rotated+=3;}
        }
        else
        {
            if(m<-1)
            {
                if(rotated>-30){rotated-=3;}
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
        //setPosition(x,this.getY());
    }

    public double getSubstance() {
        return substance;
    }
}
