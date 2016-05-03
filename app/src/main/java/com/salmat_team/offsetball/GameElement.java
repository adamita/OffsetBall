package com.salmat_team.offsetball;

import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.Matrix;
import android.graphics.Paint;
import android.graphics.Rect;
import android.graphics.drawable.Drawable;

import math.geom2d.AffineTransform2D;
import math.geom2d.line.Line2D;

/**
 * Created by adamita on 2016. 04. 27..
 */
public class GameElement {

    protected final Matrix matrix = new Matrix();
    protected float rotate;
    protected Bitmap bitmap;

    protected Rect rect = new Rect();
    AffineTransform2D rotation = AffineTransform2D.createIdentity();
    private Line2D topLine;
    private Line2D bottomLine;

    public GameElement(int left, int top, int width, int height) {
        rect.set(left, top, left + width, top + height);
        topLine = new Line2D(rect.left, rect.top, rect.right, rect.top);
        bottomLine = new Line2D(rect.left, rect.bottom, rect.right, rect.bottom);
        setRotate(0);
    }


    public void setBitmap(Drawable drawable) {
        bitmap = Bitmap.createBitmap(rect.width(), rect.height(), Bitmap.Config.ARGB_8888);
        Canvas canvas = new Canvas(bitmap);
        drawable.setBounds(0, 0, canvas.getWidth(), canvas.getHeight());
        drawable.draw(canvas);
    }

    public void Draw(Canvas canvas) {

        matrix.setTranslate(rect.left, rect.top);
        matrix.postRotate(rotate, rect.centerX(), rect.centerY());

        canvas.drawBitmap(bitmap, matrix, null);
        Paint p = new Paint();
        p.setARGB(200, 200, 200, 200);
        //canvas.drawLine((float)getTop().getX1(),(float)getTop().getY1(),(float)getTop().getX2(),(float)getTop().getY2(),p);
    }

    public void setPosition(int x, int y) {
        topLine = topLine.transform(AffineTransform2D.createTranslation(x - getX(), y - getY()));
        bottomLine = bottomLine.transform(AffineTransform2D.createTranslation(x - getX(), y - getY()));
        rect.offsetTo(x, y);
    }

    public void addRotate(float angle) {
        rotate += angle;
        rotation = AffineTransform2D.createRotation(rect.centerX(), rect.centerY(), rotate * Math.PI / 180);
    }

    public float getRotate() {
        return rotate;
    }

    public void setRotate(float angle) {
        rotate = angle;
        rotation = AffineTransform2D.createRotation(rect.centerX(), rect.centerY(), rotate * Math.PI / 180);
    }

    public Rect getRect() {
        return rect;
    }

    public int getX() {
        return rect.left;
    }

    public int getY() {
        return rect.top;
    }

    public int getWidth() {
        return rect.width();
    }

    public int getHeight() {
        return rect.height();
    }

    public int getCenterX() {
        return rect.centerX();
    }

    public int getCenterY() {
        return rect.centerY();
    }

    public Line2D getTop() {
        return topLine.transform(rotation);
    }

    public Line2D getBottom() {
        return bottomLine.transform(rotation);
    }
}
