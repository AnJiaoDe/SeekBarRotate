package com.cy.module_seekbarrotate;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Point;
import android.util.AttributeSet;
import android.util.DisplayMetrics;
import android.view.View;
import android.widget.FrameLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

/**
 * @Description:
 * @Author: cy
 * @CreateDate: 2020/10/28 17:35
 * @UpdateUser:
 * @UpdateDate: 2020/10/28 17:35
 * @UpdateRemark:
 * @Version:
 */
public class SeekBarRotate extends FrameLayout {
    private Paint paint_circle;
    private Paint paint_line_small;
    private Paint paint_line_big;
    private int width;
    private int height;
    private int stroke_width_line_small;
    private int lenghth_line_small;
    private int stroke_width_line_big;
    private int lenghth_line_big;
    private int color_circle = 0xff000000;
    private int color_line_small = 0xffeeeeee;
    private int color_line_big = 0xffffffff;

    public SeekBarRotate(@NonNull Context context) {
        this(context, null);
    }

    public SeekBarRotate(@NonNull Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
        paint_circle = new Paint();
        setColor_circle(color_circle);
        paint_circle.setAntiAlias(true);
        paint_circle.setStyle(Paint.Style.FILL);

        paint_line_small = new Paint();
        setColor_line_small(color_line_small);
        paint_line_small.setAntiAlias(true);
        paint_line_small.setStyle(Paint.Style.FILL);

        paint_line_big = new Paint();
        setColor_line_big(color_line_big);
        paint_line_big.setAntiAlias(true);
        paint_line_big.setStyle(Paint.Style.FILL);

        setStroke_width_line_small(dpAdapt(1f));
        setStroke_width_line_big(dpAdapt(2f));
        setLenghth_line_small(dpAdapt(10));
        setLenghth_line_big(dpAdapt(20));


    }

    public void setStroke_width_line_small(int stroke_width_line_small) {
        this.stroke_width_line_small = stroke_width_line_small;
    }

    public void setLenghth_line_small(int lenghth_line_small) {
        this.lenghth_line_small = lenghth_line_small;
    }

    public void setStroke_width_line_big(int stroke_width_line_big) {
        this.stroke_width_line_big = stroke_width_line_big;
    }

    public void setLenghth_line_big(int lenghth_line_big) {
        this.lenghth_line_big = lenghth_line_big;
    }

    public SeekBarRotate setColor_line_small(int color_line_small) {
        this.color_line_small = color_line_small;
        paint_line_small.setColor(color_line_small);
        return this;
    }

    public SeekBarRotate setColor_line_big(int color_line_big) {
        this.color_line_big = color_line_big;
        paint_line_big.setColor(color_line_big);
        return this;
    }

    public void setColor_circle(int color_circle) {
        this.color_circle = color_circle;
        paint_circle.setColor(color_circle);
    }

    @Override
    protected void onLayout(boolean changed, int left, int top, int right, int bottom) {
        super.onLayout(changed, left, top, right, bottom);
        width = getWidth();
        height = getHeight();
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        dispatchDraw(canvas);
    }

    @Override
    protected void dispatchDraw(Canvas canvas) {
        super.dispatchDraw(canvas);
        float cx = width * 1f / 2;
        float cy = height * 1f / 2;

        canvas.drawCircle(cx, cy, Math.min(width, height) * 1f / 2, paint_circle);
        Point point_center=new Point((int)cx,(int)cy);
        Point point_left = new Point((int) (cx - stroke_width_line_small*1f / 2), 0);
        Point point_top = new Point(0, dpAdapt(2));
        Point point_right= new Point((int) (cx + stroke_width_line_small*1f / 2),0);
        Point point_bottom= new Point(0, (int) lenghth_line_small);
//        Point point_rotate;
        for (int i = 0; i < 360; i++) {
//            canvas.drawRoundRect(cx - stroke_width_line_small / 2, dpAdapt(2), cx + stroke_width_line_small / 2, lenghth_line_small,
//                    dpAdapt(2), dpAdapt(2), paint_line_small);
//            point_rotate=rotatePoint(point_left,point_center,i);
            canvas.drawRoundRect(rotatePoint(point_left,point_center,i).x,
                    rotatePoint(point_top,point_center,i).y,
                    rotatePoint(point_right,point_center,i).x,
                    rotatePoint(point_bottom,point_center,i).y,
                    dpAdapt(2),
                    dpAdapt(2),
                    paint_line_small);
        }
    }

    /**
     * --------------------------------------------------------------------------------
     */
    public int dpAdapt(float dp) {
        return dpAdapt(dp, 360);
    }

    public int dpAdapt(float dp, float widthDpBase) {
        DisplayMetrics dm = getResources().getDisplayMetrics();
        int heightPixels = dm.heightPixels;//高的像素
        int widthPixels = dm.widthPixels;//宽的像素
        float density = dm.density;//density=dpi/160,密度比
        float heightDP = heightPixels / density;//高度的dp
        float widthDP = widthPixels / density;//宽度的dp
        float w = widthDP > heightDP ? heightDP : widthDP;
        return (int) (dp * w / widthDpBase * density + 0.5f);
    }

    public Point rotatePoint(Point p, Point pCenter, float angle) {
        // calc arc
        float l = (float) ((angle * Math.PI) / 180);

        //sin/cos value
        float cosv = (float) Math.cos(l);
        float sinv = (float) Math.sin(l);

        // calc new point
        float newX = (float) ((p.x - pCenter.x) * cosv - (p.y - pCenter.y) * sinv + pCenter.x);
        float newY = (float) ((p.x - pCenter.x) * sinv + (p.y - pCenter.y) * cosv + pCenter.y);
        return new Point((int) newX, (int) newY);
    }
}
