package com.sunjiajia.alldemo.whew;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.util.AttributeSet;
import android.view.View;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by mk on 2016/11/25.
 */

public class WhewView extends View {
    private Paint mPaint;
    private int maxWidth = 175;
    //是否运行
    private boolean isStarting = false;
    private List<String> alphaList = new ArrayList<String>();
    private List<String> startWidthList = new ArrayList<String>();

    public WhewView(Context context, AttributeSet attrs) {
        super(context, attrs);
        init();
    }

    public WhewView(Context context) {
        super(context);
        init();
    }

    private void init() {
        mPaint = new Paint();
//        mPaint.setColor(0x0059ccf5);
        mPaint.setColor(0xccff00);
        alphaList.add("175");//圆心的不透明度
        startWidthList.add("0");
    }

    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        super.onMeasure(widthMeasureSpec, heightMeasureSpec);
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        setBackgroundColor(Color.TRANSPARENT);//颜色完全透明
        //依次绘制同心圆
        for (int i = 0; i < alphaList.size(); i++) {
            int alpha = Integer.parseInt(alphaList.get(i));
            //圆半径
            int startWidth = Integer.parseInt(startWidthList.get(i));
            mPaint.setAlpha(alpha);
            //这个半径决定你想要多大的扩散面积
            canvas.drawCircle(getWidth() / 2, getHeight() / 2, startWidth + 10, mPaint);
            //同心圆扩散
            if (isStarting && alpha > 0 && startWidth < maxWidth) {
                alphaList.set(i, (alpha - 1) + "");
                startWidthList.set(i, (startWidth + 1) + "");
            }
        }
        if (isStarting && Integer.parseInt(startWidthList.get(startWidthList.size() - 1)) == maxWidth / 5) {
            alphaList.add("175");
            startWidthList.add("0");
        }
        //同心圆数量达到10个，删除最外层圆
        if (isStarting && startWidthList.size() == 8) {
            startWidthList.remove(0);
            alphaList.remove(0);
        }
        //刷新界面
        invalidate();
    }

    public void start() {
        isStarting = true;
    }

    //停止动画
    public void stop() {
        isStarting = false;
    }

    //判断是否在执行
    public boolean isStarting() {
        return isStarting;
    }

    @Override
    protected void onLayout(boolean changed, int left, int top, int right, int bottom) {
        super.onLayout(changed, left, top, right, bottom);
    }
}
