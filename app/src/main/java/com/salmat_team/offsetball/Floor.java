package com.salmat_team.offsetball;

import android.annotation.TargetApi;
import android.content.Context;
import android.graphics.Canvas;
import android.graphics.drawable.Drawable;
import android.graphics.drawable.shapes.Shape;
import android.os.Build;

/**
 * Created by adamita on 2016. 04. 27..
 */
public class Floor extends GameElement{

    protected double substance;

    @TargetApi(Build.VERSION_CODES.LOLLIPOP)
    public Floor(Context context, int x, int y, int width, int height, double substance)
    {
        super(context,x,y,width,height);

        this.substance=substance;

        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
            drawing =context.getDrawable(R.drawable.floor);
        }
        else
        {
            drawing =context.getResources().getDrawable(R.drawable.floor,null);
        }
    }

    public double getSubstance() {
        return substance;
    }
}
