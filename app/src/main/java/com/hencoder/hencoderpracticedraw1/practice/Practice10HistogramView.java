package com.hencoder.hencoderpracticedraw1.practice;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.support.annotation.Nullable;
import android.util.AttributeSet;
import android.view.View;

import com.hencoder.hencoderpracticedraw1.R;

public class Practice10HistogramView extends View {

    public Practice10HistogramView(Context context) {
        super(context);
    }

    public Practice10HistogramView(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
    }

    public Practice10HistogramView(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);

//        综合练习
//        练习内容：使用各种 Canvas.drawXXX() 方法画直方图
        Paint paint = new Paint(Paint.ANTI_ALIAS_FLAG);
        paint.setStrokeWidth(2);
        paint.setColor(Color.parseColor("#ffffff"));

        //绘制坐标轴
        /**y轴length: 500
         * x轴length: 750
         *
         * 7个柱子(柱状图), 7个divider 共700length 右侧留50length
         * 每个柱子80, 每个间隔20*/
        canvas.drawLine(150, 100, 150, 600, paint);
        canvas.drawLine(150, 600, 900, 600, paint);

        paint.setColor(getResources().getColor(R.color.light_green_500));
        canvas.drawRect(170, 595, 250, 600, paint);

        canvas.drawRect(270, 580, 350, 600, paint);

        canvas.drawRect(370, 580, 450, 600, paint);

        canvas.drawRect(470, 450, 550, 600, paint);

        canvas.drawRect(570, 315, 650, 600, paint);

        canvas.drawRect(670, 255, 750, 600, paint);

        canvas.drawRect(770, 465, 850, 600, paint);

        paint.setColor(getResources().getColor(R.color.white));
        paint.setTextAlign(Paint.Align.CENTER);
        paint.setTextSize(24);

        canvas.drawText("Froyo", 210, 620, paint);

        canvas.drawText("GB", 310, 620, paint);

        canvas.drawText("ICS", 410, 620, paint);

        canvas.drawText("JB", 510, 620, paint);

        canvas.drawText("KitKat", 610, 620, paint);

        canvas.drawText("L", 710, 620, paint);

        canvas.drawText("M", 810, 620, paint);

    }
}
