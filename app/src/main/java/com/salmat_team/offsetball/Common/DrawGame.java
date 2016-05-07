package com.salmat_team.offsetball.Common;

import android.annotation.TargetApi;
import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.os.Build;
import android.util.AttributeSet;
import android.view.View;

/**
 * Created by Peti on 2016.04.27..
 */

class BoxData
{
    private float Ux, Uy, Dx, Dy;
    private int Rotate;

    public float getDx() {
        return Dx;
    }

    public float getUx() {
        return Ux;
    }

    public float getDy() {
        return Dy;
    }

    public float getUy() {
        return Uy;
    }

    public int getRotate() {
        return Rotate;
    }

    BoxData(int length, int fragment_height, int fragment_width)
    {
        Ux=(fragment_width<<1)-(length<<1);
        Uy=fragment_height*0.8f;
        Dx=(fragment_width<<1)+(length<<1);
        Dy=(fragment_height*0.8f)+20;//A 20-as érték a magassága
        Rotate=0;
    }

    void MoveBox(float x1, float x2)
    {
        Ux=x1;
        Dx=x2;
    }

    void RotateBox(int r)
    {
        Rotate=r;
    }

}


public class DrawGame extends View {

    private Paint paint;
    private BoxData BD;


    public DrawGame(Context context) {
        super(context);
        init();
    }

    public DrawGame(Context context, AttributeSet attrs) {
        super(context, attrs);
        init();
    }

    public DrawGame(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        init();
    }

    @TargetApi(Build.VERSION_CODES.LOLLIPOP)
    public DrawGame(Context context, AttributeSet attrs, int defStyleAttr, int defStyleRes) {
        super(context, attrs, defStyleAttr, defStyleRes);
        init();
    }

    private void init()
    {
        BD=new BoxData(50,getHeight(),getWidth());

        //this.line=new Path();
        /*this.paint=new Paint();
        this.paint.setColor(Color.BLUE);
        this.paint.setStyle(Paint.Style.STROKE);
        this.paint.setStrokeWidth(7f);
        this.paint.setStrokeJoin(Paint.Join.ROUND);
        this.paint.setAntiAlias(true);  */



    }

    public void BoxMove(float value)
    {
        BD.MoveBox(BD.getUx()+value,BD.getDx()+value);
    }

    public void addValue(float value){
        //this.values.remove(0); //Régi törlése
        //this.values.add(value); //Új hozzáadása
        invalidate();

    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);


        //int middle=x<<1;
        canvas.save();
        canvas.rotate(BD.getRotate());//ez alap esetben 0 azaz nem forog el
        paint.setColor(Color.BLACK);//keret színe
        paint.setStrokeWidth(3);//keret vastagság
        canvas.drawRect(BD.getUx(),//keret rajzolás
                BD.getUy(), BD.getDx(),
                BD.getDy(), paint);
        paint.setStrokeWidth(0);
        paint.setColor(Color.GRAY);//kitöltő szín
        canvas.drawRect(BD.getUx()+3,//kitöltés
                BD.getUy()+3, BD.getDx()-3,
                BD.getDy()-3, paint);
        canvas.restore();

    }


    }
