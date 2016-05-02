package com.salmat_team.offsetball;

import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.Matrix;
import android.graphics.Rect;
import android.graphics.drawable.Drawable;

/**
 * Created by adamita on 2016. 04. 27..
 */
public class GameElement {

    protected final Matrix matrix = new Matrix();
    protected float rotate;
    protected Bitmap bitmap;

    protected Rect rect = new Rect();
    private Rect hRect = new Rect();

    public GameElement(int left, int top, int width, int height)
    {
        rect.set(left, top, left + width, top + height);
    }

    public boolean onTop(GameElement other)
    {
        hRect.set(rect.left, rect.top - 1, rect.right, rect.top);
        Rect oRect = other.getRect();
        return hRect.intersect(oRect);
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
    }

    public void setPosition(int x, int y) {
        rect.offsetTo(x, y);
    }

    public void addRotate(float angle) {
        rotate += angle;
    }

    public float getRotate() {
        return rotate;
    }

    public void setRotate(float angle) {
        rotate = angle;
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
}
