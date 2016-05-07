package com.salmat_team.offsetball.Game;

import android.content.Context;
import android.support.v4.content.ContextCompat;

import com.salmat_team.offsetball.R;

/**
 * Created by adamita on 2016. 04. 27..
 */
public class Floor extends GameElement{

    protected double substance;

    public Floor(Context context, double centerX, double centerY, int width, int height, double substance, int screenWidth, int screenHeight)
    {
        super(centerX, centerY, width, height, screenWidth, screenHeight);
        setBitmap(ContextCompat.getDrawable(context, R.drawable.floor));
        this.substance=substance;

        rotate = 0;
        //bitmap=((BitmapDrawable)ContextCompat.getDrawable(context, R.drawable.floor)).getBitmap();

    }

    int constx=this.getX();
    float lastm=0;
    public void Move(Ball ball, float m)
    {
        int x=this.getX();
        float a=1f;
        if(m>lastm+a)
        {
            if (screenWidth > this.getX() + this.getWidth()) {
                x += 25;
                lastm+=a;
            }
        }
        if(m<lastm-a)
        {
            if (0 < this.getX()) {
                x -= 25;
                lastm-=a;
            }
        }

        /*int x=this.getX();
        int e=(this.getWidth()<<10);
        if(m>1)
        {
            if (screenWidth > this.getX() + this.getWidth())
            {x=this.getX()+25;}
        } else if (m < -1)
        {

            if (0 < this.getX()) {
                x = this.getX() - 25;
            }
        }*/
        /*if((this.getX()+e)<(w/2))
        {
            x=(int)(((w/2)-e)*((int)m));
        }
        else
        {
            x=(int)(((w/2)+(w-e))*((int)m));
        }*/


//        if(shape.intersect(ball.getShape()))
//            ball.MoveWith(getX()-x,0);

        //if (m != 0)
            setPosition(x, this.getY());


    }


    public void Rotate(Ball ball, float m)
    {
        float add = m - getRotate();
        if (add >= 10)
            add = 10;
        else if (add <= -10)
            add = -10;

        Boolean onTop = ball.isOnTop(this);
        Boolean onBottom = ball.isOnBottom(this);

        addRotate(add);

        if (onTop || ball.isOnTop(this))
            ball.setOnFloorTop(this);
        else if (onBottom || ball.isOnBottom(this))
            ball.setOnFloorBottom(this);



    }

    public double getSubstance() {
        return substance;
    }


}
