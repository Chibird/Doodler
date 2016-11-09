package com.chibird.doodler;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Path;
import android.util.AttributeSet;
import android.view.MotionEvent;
import android.view.View;

import java.util.ArrayList;
import java.util.Random;

/**
 * Created by Jacqueline on 10/29/2016.
 */

public class DoodleView extends View {

    public class Drawing {
        Paint paint;
        Path path;

        public Drawing(Paint paint, Path path) {
            this.paint = paint;
            this.path = path;
        }

        public Path getPath() {
            return path;
        }

        public Paint getPaint() {
            return paint;
        }
    }

    private Paint currPaint = new Paint();
    private Path currPath = new Path();
    private ArrayList<Drawing> drawings = new ArrayList<Drawing>();
    private ArrayList<Drawing> undidDrawings = new ArrayList<Drawing>();
    private boolean randomBg = false;
    private int bgColor;

    public DoodleView(Context context) {
        super(context);
        init(null, 0);
    }

    public DoodleView(Context context, AttributeSet attrs){
        super(context, attrs);
        init(attrs, 0);
    }

    public DoodleView(Context context, AttributeSet attrs, int defStyle){
        super(context, attrs, defStyle);
        init(attrs, defStyle);
    }

    private void init(AttributeSet attrs, int defStyle){
        currPaint.setColor(Color.BLACK);
        currPaint.setAntiAlias(true);
        currPaint.setStyle(Paint.Style.STROKE);
        currPaint.setStrokeWidth(10);
    }

    private void initBrush() {
        Paint oldPaint = currPaint;
        currPaint = new Paint();
        currPaint.setAntiAlias(true);
        currPaint.setStyle(Paint.Style.STROKE);
        currPaint.setStrokeWidth(oldPaint.getStrokeWidth());
        currPaint.setColor(oldPaint.getColor());
        currPaint.setAlpha(oldPaint.getAlpha());
    }

    public void changeSize(int size) {
        initBrush();
        currPaint.setStrokeWidth(size);
    }

    public void changeOpacity(int size) {
        initBrush();
        currPaint.setAlpha(size);
    }

    public void changeColor(int r, int g, int b) {
        initBrush();
        if (r == -1) {
            r = Color.red(currPaint.getColor());
        }
        if (g == -1){
            g = Color.green(currPaint.getColor());
        }
        if (b == -1) {
            b = Color.blue(currPaint.getColor());
        }
        int oldAlpha = currPaint.getAlpha();
        currPaint.setColor(Color.rgb(r,g,b));
        currPaint.setAlpha(oldAlpha);
    }

    public void clear() {
        drawings.clear();
        bgColor = Color.rgb(255,255,255);
        invalidate();
    }

    public void undo() {
        if (drawings.size() != 0) {
            Drawing last = drawings.remove(drawings.size() - 1);
            undidDrawings.add(last);
            invalidate();
        }
    }

    public void redo(){
        if (undidDrawings.size() != 0) {
            Drawing last = undidDrawings.remove(undidDrawings.size() - 1);
            drawings.add(last);
            invalidate();
        }
    }

    public void randomize(){
        randomBg = true;
        invalidate();
    }

    @Override
    public void onDraw(Canvas canvas){
        super.onDraw(canvas);

        if (randomBg){
            Random r1 = new Random();
            int c1 = r1.nextInt(255);
            Random r2 = new Random();
            int c2 = r2.nextInt(255);
            Random r3 = new Random();
            int c3 = r3.nextInt(255);
            bgColor = Color.rgb(c1,c2,c3);
            randomBg = false;
        }

        canvas.drawColor(bgColor);
        for (int i = 0; i < drawings.size(); i++) {
            canvas.drawPath(drawings.get(i).getPath(), drawings.get(i).getPaint());
        }
    }

    private boolean moved = false;
    @Override
    public boolean onTouchEvent(MotionEvent motionEvent){
        float touchX = motionEvent.getX();
        float touchY = motionEvent.getY();

        switch(motionEvent.getAction()){
            case MotionEvent.ACTION_DOWN:
                currPath = new Path();
                currPath.moveTo(touchX, touchY);
                moved = false;
                break;
            case MotionEvent.ACTION_MOVE:
                currPath.lineTo(touchX, touchY);
                moved = true;
                break;
            case MotionEvent.ACTION_UP:
                if (moved) {
                    drawings.add(new Drawing(currPaint, currPath));
                }
                break;
        }
        invalidate();

        return true;
    }
}
