package com.salmat_team.offsetball;

import android.content.Context;
import android.support.v4.content.ContextCompat;

/**
 * Created by adamita on 2016. 04. 27..
 */
public class Floor extends GameElement{

    protected double substance;

    public Floor(Context context, int x, int y, int width, int height, double substance, int screenWidth, int screenHeight)
    {
        super(x, y, width, height, screenWidth, screenHeight);
        setBitmap(ContextCompat.getDrawable(context, R.drawable.floor));
        this.substance=substance;

        rotate = 0;
        //bitmap=((BitmapDrawable)ContextCompat.getDrawable(context, R.drawable.floor)).getBitmap();

    }


    public void Move(Ball ball, float m)
    {
        int x=this.getX();
        int e=(this.getWidth()<<10);
        if(m>1)
        {
            if (screenWidth > this.getX() + this.getWidth())
            {x=this.getX()+20;}
        } else if (m < -1)
        {

            if (0 < this.getX()) {
                x = this.getX() - 20;
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

        if (m != 0)
            setPosition(x, this.getY());

    }


    public void Rotate(Ball ball, float m)
    {

        Boolean onTop = ball.isOnTop(this);
        setRotate(m);
        if (onTop || ball.isOnTop(this))
            ball.setOnFloorTop(this);


//        if(isInsideRotation(ball))
//        {
//
//
//            if(ball.getCenterX()<getCenterX()) {
//                //baloldal
//                if (m > 0)
//                {
//                    //fel
//                    ballAbove=ball.isOnTop(this);
//
//                } else
//                {
//                    //le
//                    ballUnder=ball.isOnBottom(this);
//                }
//
//            }
//            else
//            {
//                //jobboldal
//                if (m > 0)
//                {
//                    //le
//                    ballUnder=ball.isOnBottom(this);
//
//
//                } else
//                {
//                    //fel
//                    ballAbove=ball.isOnTop(this);
//
//                }
//            }
//        }




        //int x=this.getX();
        //int e=(this.getWidth()<<10);

        //view.setRotation();

//        if(m>1)
//        {
//            if (rotate < 30) {
//                rotate += 3;
//            }
//        }
//        else
//        {
//            if(m<-1)
//            {
//                if (rotate > -30) {
//                    rotate -= 3;
//                }
//            }
//        }
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
