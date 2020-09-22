package com.hencoder.hencoderpracticedraw1.practice;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.support.annotation.Nullable;
import android.util.AttributeSet;
import android.view.View;

public class Practice8DrawArcView extends View {

    public Practice8DrawArcView(Context context) {
        super(context);
    }

    public Practice8DrawArcView(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
    }

    public Practice8DrawArcView(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);

//        练习内容：使用 canvas.drawArc() 方法画弧形和扇形
        Paint paint = new Paint(Paint.ANTI_ALIAS_FLAG);
        paint.setColor(Color.parseColor("#000000"));

        /**绘制扇形
         * left, top, right, bottom 为扇形所在椭圆的四个顶点
         * startAngle 为起始角度. x轴正方向为0度 顺时针为正角度, 逆时针为负角度.
         * sweepAngle 为扫过的角度.
         * useCenter true 代表绘制扇形, false代表绘制弧形*/

        canvas.drawArc(400, 500, 750, 680, 0, 180, true, paint);

        canvas.drawArc(400, 500, 750, 680, -110, 100, true, paint);

        paint.setStyle(Paint.Style.STROKE);
        paint.setStrokeWidth(2);
        canvas.drawArc(400, 500, 750, 680, 180, 60, false, paint);

    }
}
