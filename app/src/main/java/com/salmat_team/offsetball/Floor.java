package com.salmat_team.offsetball;

import android.content.Context;
import android.support.v4.content.ContextCompat;

/**
 * Created by adamita on 2016. 04. 27..
 */
public class Floor extends GameElement{

    protected double substance;

    public Floor(Context context, int x, int y, int width, int height, double substance)
    {
        super(x,y,width,height);
        setBitmap(ContextCompat.getDrawable(context, R.drawable.floor));
        this.substance=substance;

        rotate = 0;
        //bitmap=((BitmapDrawable)ContextCompat.getDrawable(context, R.drawable.floor)).getBitmap();

    }


    public void Move(Ball ball,float m, int w)
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


//        if(shape.intersect(ball.getShape()))
//            ball.MoveWith(getX()-x,0);

        setPosition(x,this.getY());

    }


    public void Rotated(float m)
    {
        //int x=this.getX();
        //int e=(this.getWidth()<<10);



        //view.setRotation();

        if(m>1)
        {
            if (rotate < 30) {
                rotate += 3;
            }
        }
        else
        {
            if(m<-1)
            {
                if (rotate > -30) {
                    rotate -= 3;
                }
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
