package com.salmat_team.offsetball;

import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.Matrix;
import android.graphics.Paint;
import android.graphics.Rect;
import android.graphics.drawable.Drawable;

import math.geom2d.AffineTransform2D;
import math.geom2d.conic.Circle2D;
import math.geom2d.conic.EllipseShape2D;
import math.geom2d.line.Line2D;

/**
 * Created by adamita on 2016. 04. 27..
 */
public class GameElement {

    protected final Matrix matrix = new Matrix();
    protected int screenWidth, screenHeight;
    protected float rotate;
    protected Bitmap bitmap;
    protected EllipseShape2D circle;

    protected Rect rect = new Rect();
    private AffineTransform2D rotation = AffineTransform2D.createIdentity();
    private AffineTransform2D transform = AffineTransform2D.createIdentity();
    private Line2D topLine;
    private Line2D bottomLine;


    public GameElement(int left, int top, int width, int height, int screenWidth, int screenHeight) {
        this.screenWidth = screenWidth;
        this.screenHeight = screenHeight;
        rect.set(left, top, left + width, top + height);
        topLine = new Line2D(rect.left, rect.top, rect.right, rect.top);
        bottomLine = new Line2D(rect.left, rect.bottom, rect.right, rect.bottom);

        if (width > height)
            circle = new Circle2D(getCenterX(), getCenterY(), width >> 1);
        else
            circle = new Circle2D(getCenterX(), getCenterY(), height >> 1);

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
        p.setStrokeWidth(2);
        p.setARGB(200, 255, 0, 0);

        //Draw top -- help
        //canvas.drawLine((float) getTopLine().getX1(), (float) getTopLine().getY1(), (float) getTopLine().getX2(), (float) getTopLine().getY2(), p);
    }

    protected void setPosition(int x, int y) {
        transform = AffineTransform2D.createTranslation(x - getX(), y - getY());
        topLine = topLine.transform(transform);
        bottomLine = bottomLine.transform(transform);
        circle = circle.transform(transform);
        rect.offsetTo(x, y);
    }

    public float getRotate() {
        return rotate;
    }

    protected void setRotate(float angle) {
        if (angle <= 90 && angle >= -90) {
            rotate = angle;
            rotation = AffineTransform2D.createRotation(rect.centerX(), rect.centerY(), rotate * Math.PI / 180);
        }
    }

    protected void addRotate(float angle) {
        if (rotate + angle <= 90 && rotate + angle >= -90) {
            rotate += angle;
            rotation = AffineTransform2D.createRotation(rect.centerX(), rect.centerY(), rotate * Math.PI / 180);
        }
    }

//    protected void setRotateAround(float angle, int aroundX, int aroundY) {
//            Point2D newPoint=AffineTransform2D.createRotation(rect.centerX(), rect.centerY(), angle * Math.PI / 180).transform(new Point2D(rect.left,rect.right));
//            setPosition((int)newPoint.getX(),(int)newPoint.getY());
//    }

    protected Boolean isInsideRotation(GameElement other) {
        return circle.boundingBox().contains(other.getCenterX(), getCenterY());
        //return circle.contains(other.getX(),other.getCenterY()) || circle.contains(other.getX(),other.getBottom()) || circle.contains(other.getRight(),other.getY()) || circle.contains(other.getRight(),other.getBottom());
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

    public int getBottom() {
        return rect.bottom;
    }

    public int getRight() {
        return rect.right;
    }

    public EllipseShape2D getCircle() {
        return circle;
    }

    public Line2D getTopLine() {
        return topLine.transform(rotation);
    }

    public Line2D getBottomLine() {
        return bottomLine.transform(rotation);
    }
}
