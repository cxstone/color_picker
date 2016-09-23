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
import android.view.MotionEvent;
import android.view.View;

/**
 * Created by xi.chen01 on 9/23/2016.
 * Project:MyApplication
 * Company:Lodestone
 * Email:Xi.chen01@lodestonemc.com
 */

public class ColorPickerView extends View {
    private ColorPickerViewListener listener;
    private Paint paint;
    private int width;
    private int height;
    private int pickColor;
    private int borderColor;
    private float borderWidth;
    private float barY;

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
        borderColor = typedArray.getColor(R.styleable.ColorPickerView_borderColor, 0XFFFFFFFF);
        borderWidth = typedArray.getDimension(R.styleable.ColorPickerView_borderWidth, 0);
        typedArray.recycle();

        paint = new Paint();
        paint.setAntiAlias(true);
    }

    public void setColorPickerViewListener(ColorPickerViewListener listener) {
        this.listener = listener;
    }

    //设置选择器颜色
    public void setPickColor(int pickColor) {
        this.pickColor = pickColor;
        this.invalidate();
    }

    public int getPickColor() {
        return this.pickColor;
    }

    //设置选择器边框宽度
    public void setBorderWidth(float borderWidth) {
        this.borderWidth = borderWidth <= 0 ? 0 : borderWidth;
        this.invalidate();
    }

    //设置选择器边框颜色
    public void setBorderColor(int borderColor) {
        this.borderColor = borderColor;
        this.invalidate();
    }

    @Override
    protected void onSizeChanged(int w, int h, int oldw, int oldh) {
        super.onSizeChanged(w, h, oldw, oldh);
        width = getWidth();
        height = getHeight();
        barY = height;
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
        if (borderWidth > 0) {
            paint.setStrokeWidth(borderWidth);
            paint.setStyle(Paint.Style.STROKE);
            paint.setColor(borderColor);
            canvas.drawRect(borderWidth / 2, borderWidth / 2, width - borderWidth / 2, height - borderWidth / 2, paint);
            paint.reset();
        }

        //画滑块
        paint.setColor(Color.YELLOW);
        canvas.drawCircle(width / 2, barY, width / 2, paint);
        paint.reset();
    }

    @Override
    public boolean onTouchEvent(MotionEvent event) {
        float y = event.getY();
        if (y < 0) {
            barY = 0;
        } else if (y > height) {
            barY = height;
        } else {
            barY = y;
        }
        invalidate();
        if (listener != null) {
            listener.touchCoordinate(height, barY);
        }
        return true;
    }

    public interface ColorPickerViewListener {
        void touchCoordinate(float total, float position);
    }
}
