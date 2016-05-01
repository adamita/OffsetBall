package com.salmat_team.offsetball;

import android.annotation.TargetApi;
import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Rect;
import android.graphics.drawable.Drawable;
import android.os.Build;

/**
 * Created by adamita on 2016. 04. 27..
 */
public class GameElement {
    private int width;
    private int height;
    protected Drawable drawing;
    protected Rect shape=new Rect();

    public GameElement(int x, int y, int width, int height)
    {
        this.width = width;
        this.height = height;
        setPosition(x,y);
    }

    public boolean Intersect(GameElement other)
    {
        return shape.intersect(other.getShape());
    }

    public void Draw(Canvas canvas) {
        drawing.setBounds(getX(), getY(), getX() + getWidth(), getY() + getHeight());
        drawing.draw(canvas);
    }

    public Rect getShape() {
        return shape;
    }

    public int getX() {
        return shape.left;
    }

    public void setPosition(int x, int y) {
        shape.set(x,y,x+ getWidth(),y+ getHeight());
    }

    public int getY() {
        return shape.top;
    }

    public int getWidth() {
        return width;
    }

    public int getHeight() {
        return height;
    }
}
