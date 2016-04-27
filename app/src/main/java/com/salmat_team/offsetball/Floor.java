package com.salmat_team.offsetball;

import android.annotation.TargetApi;
import android.content.Context;
import android.graphics.Canvas;
import android.graphics.drawable.Drawable;
import android.graphics.drawable.shapes.Shape;
import android.os.Build;
import android.support.v4.content.ContextCompat;

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

    public double getSubstance() {
        return substance;
    }
}
