package com.example.xichen01.myapplication;

import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.LinearGradient;
import android.graphics.Paint;
import android.graphics.Rect;
import android.graphics.Shader;
import android.util.AttributeSet;
import android.view.View;

/**
 * Created by xi.chen01 on 9/23/2016.
 * Project:MyApplication
 * Company:Lodestone
 * Email:Xi.chen01@lodestonemc.com
 */

public class ColorPickerView extends View {
    private Paint paint;
    private int width;
    private int height;
    private int pickColor;
    private int boderWidth;

    public int getPickColor() {
        return pickColor;
    }

    public void setPickColor(int pickColor) {
        this.pickColor = pickColor;
        this.invalidate();
    }

    public ColorPickerView(Context context) {
        super(context);
        init(context, null);
    }

    public ColorPickerView(Context context, AttributeSet attrs) {
        super(context, attrs);
        init(context, attrs);
    }

    public ColorPickerView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        init(context, attrs);
    }

    private void init(Context context, AttributeSet attrs) {
        TypedArray typedArray = context.obtainStyledAttributes(attrs, R.styleable.ColorPickerView);
        pickColor = typedArray.getColor(R.styleable.ColorPickerView_pickColor, 0XFFFFFFFF);
        typedArray.recycle();

        boderWidth = 4;
        paint = new Paint();
        paint.setAntiAlias(true);
    }

    @Override
    protected void onSizeChanged(int w, int h, int oldw, int oldh) {
        super.onSizeChanged(w, h, oldw, oldh);
        width = getWidth();
        height = getHeight();
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);

        //画渐变
        Rect rect = new Rect(0, 0, width, height);
        Shader shader = new LinearGradient(0, height, 0, 0, pickColor, 0XFFFFFFFF, Shader.TileMode.MIRROR);
        paint.setShader(shader);
        canvas.drawRect(rect, paint);
        paint.reset();

        //画边框
        if (boderWidth > 0) {
            paint.setStrokeWidth(boderWidth);
            paint.setStyle(Paint.Style.STROKE);
            paint.setColor(Color.BLACK);
            canvas.drawRect(boderWidth / 2, boderWidth / 2, width - boderWidth / 2, height - boderWidth / 2, paint);
            paint.reset();
        }
    }
}
