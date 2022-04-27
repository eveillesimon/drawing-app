package com.example.drawapp;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.util.AttributeSet;
import android.view.View;

import java.util.ArrayList;

/**
 * TODO: document your custom view class.
 */
public class DrawingView extends View {
    private Paint paint = new Paint();
    private ArrayList<DrawnElement> model = new ArrayList<DrawnElement>();

    public void setPaint(int color, float thickness){
        Paint paint = new Paint();
        paint.setColor(color);
        paint.setStrokeWidth(thickness);
        paint.setStyle(Paint.Style.STROKE);
        this.paint = paint;
    }

    public void setModel(ArrayList<DrawnLine> lines) {
        model.clear();
        model.addAll(lines);
    }

    public void undo() {
        if (model.size() > 0) {
            model.remove(model.size() - 1);
        }
    }

    public void addModel(DrawnElement shape) {
        model.add(shape);
    }

    public DrawingView(Context context) {
        super(context);
    }

    public DrawingView(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    public DrawingView(Context context, AttributeSet attrs, int defStyle) {
        super(context, attrs, defStyle);
    }


    private void init(AttributeSet attrs, int defStyle) {
    }


    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        for (DrawnElement shape : model) {
            this.setPaint(shape.getColor(), shape.getThickness());
            canvas.drawPath(shape.getPath(), paint);
        }
    }

    @Override
    public boolean performClick() {
        return super.performClick();
    }

}