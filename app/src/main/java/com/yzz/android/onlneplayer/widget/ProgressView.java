package com.yzz.android.onlneplayer.widget;

import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.Point;
import android.graphics.RectF;
import android.util.AttributeSet;
import android.view.View;

import com.yzz.android.onlneplayer.R;

/**
 * @name OnLnePlayer
 * @class name：com.yzz.android.onlneplayer.widget
 * @anthor yzz
 * @Email:yzzandroid@163.com
 * @time 2017/3/27 0027 下午 7:59
 * 自定义精度条
 */
public class ProgressView extends View {
    //横向
    public static final int HORIZONTAL = 0;
    //纵向
    public static final int VERTICALITY = 1;
    private Paint mPaint;
    //半径
    private int mRadius;
    //进度条的颜色
    private int mColor = 0xffff0000;
    //进度条的方向
    private int mOrientation = HORIZONTAL;
    private float mProgressSize = 10;
    private int mBackGroundColor = 0xfff2f2f2;
    private RectF mRectF;
    private float mArc = 5;
    //圆心
    private Point mPoint;

    private int mWidth = 0;
    private int mHeight = 0;
    private float mLength;
    private float mAngle;


    public ProgressView(Context context) {
        this(context, null);
    }

    public ProgressView(Context context, AttributeSet attrs) {
        this(context, attrs, 0);
    }

    public ProgressView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        init();
        TypedArray array = context.obtainStyledAttributes(attrs, R.styleable.ProgressView);
        mBackGroundColor = array.getColor(R.styleable.ProgressView_mBackGroundColor, mBackGroundColor);
        mProgressSize = array.getDimension(R.styleable.ProgressView_mProgressSize, mProgressSize);
        mArc = array.getDimension(R.styleable.ProgressView_mArc, mArc);
        array.recycle();
    }

    private void init() {
        mPaint = new Paint();
        mPaint.setStyle(Paint.Style.FILL);
        mPaint.setAntiAlias(true);
        mPaint.setStrokeWidth(mProgressSize);
    }

    @Override
    protected void onSizeChanged(int w, int h, int oldw, int oldh) {
        super.onSizeChanged(w, h, oldw, oldh);
        //初始化半径
        if (mRadius == 0) {
            mWidth = getMeasuredWidth();
            mHeight = getMeasuredHeight();
            mRadius = Math.min(mWidth, mHeight);
            mPoint = new Point(mRadius / 2, mRadius / 2);
            mRectF = new RectF(0, 0, mWidth, mHeight);
        }
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        mPaint.setColor(mBackGroundColor);
        switch (mOrientation) {
            case HORIZONTAL:
                mRectF.set(0, 0, mWidth, mHeight);
                canvas.drawRoundRect(mRectF, mArc, mArc, mPaint);
                drawHorizontal(canvas);
                break;
            case VERTICALITY:
                canvas.drawCircle(mPoint.x, mPoint.y, mRadius, mPaint);
                drawVerticality(canvas);
                break;
        }
    }

    public void drawHorizontal(Canvas canvas) {
        mPaint.setColor(mColor);
        mRectF.set(0, 0, mLength, mHeight);
        canvas.drawRoundRect(mRectF, mArc, mArc, mPaint);
    }

    public void drawVerticality(Canvas canvas) {
        mPaint.setColor(mColor);
        mRectF.set(0, 0, mWidth, mHeight);
        canvas.drawArc(mRectF, 0, mAngle, false, mPaint);
    }

    public void setProgress(int progress) {
        if (progress < 0 || progress > 100)
            throw new RuntimeException("the progress must in [0 , 100]");
        switch (mOrientation) {
            case HORIZONTAL:
                mLength = (float) ((progress / 100.0) * mWidth);
                break;
            case VERTICALITY:
                mAngle = (float) ((progress / 100.0) * 360);
                break;
        }
        invalidate();
    }
}
