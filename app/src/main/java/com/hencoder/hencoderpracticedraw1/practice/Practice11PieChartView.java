package com.hencoder.hencoderpracticedraw1.practice;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.Path;
import android.os.Build;
import android.support.annotation.Nullable;
import android.support.annotation.RequiresApi;
import android.util.AttributeSet;
import android.util.Log;
import android.view.View;

import com.hencoder.hencoderpracticedraw1.R;

public class Practice11PieChartView extends View {
    private static final String TAG = "Practice11PieChartView";

    public Practice11PieChartView(Context context) {
        super(context);
    }

    public Practice11PieChartView(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
    }

    public Practice11PieChartView(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }

    @RequiresApi(api = Build.VERSION_CODES.LOLLIPOP)
    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);

//        综合练习
//        练习内容：使用各种 Canvas.drawXXX() 方法画饼图

        /**思路
         * 1.绘制base圆形
         * 2.绘制各个扇形(基于base圆形)
         * 3.红色扇形的base圆形radius与其他扇形的base圆形一致, 但圆心偏移
         * 4.为各个扇形添加间隔 3度
         * 5.绘制线: 圆心 --> 扇形角度/2 再延长 最后弯折
         * 6.绘制文字*/
        Paint paint = new Paint(Paint.ANTI_ALIAS_FLAG);

        //红色扇形的base圆
        paint.setColor(getResources().getColor(android.R.color.transparent));
        canvas.drawCircle(430, 380, 240, paint);

        //其他扇形的base圆
        paint.setColor(getResources().getColor(android.R.color.transparent));

        canvas.drawCircle(450, 400, 240, paint);

        //大圆(绘制line时用)
        paint.setStyle(Paint.Style.STROKE);
        paint.setColor(getResources().getColor(R.color.white));
        canvas.drawCircle(450, 400, 280, paint);

        /**绘制line: 找到所在扇形角度一半在大圆上的点, 连接圆心与该点. 再绘制横线*/

        paint.setStyle(Paint.Style.FILL);

        /**红色扇形绘制规则(其他扇形绘制规则相同):
         * left为圆心x - 半径 430 - 240 = 190
         * right为圆心x + 半径 430 + 240 = 670
         * top为圆心y - 半径 380 - 240 = 140
         * bottom为圆心y + 半径 380 + 240 = 620*/
        //绘制红色扇形
        paint.setColor(getResources().getColor(R.color.pie_red));
        canvas.drawArc(190, 140, 670, 620, -180, 120, true, paint);


        //绘制黄色扇形
        paint.setColor(getResources().getColor(R.color.pie_yellow));
        canvas.drawArc(210, 160, 690, 640, -60, 60, true, paint);

        paint.setColor(getResources().getColor(R.color.black));
        paint.setStrokeWidth(20);
        paint.setStyle(Paint.Style.FILL);
        Point yellowPoint = calcPoint(450, 400, 240, 0);
        canvas.drawPoint(yellowPoint.x, yellowPoint.y, paint);
        //绘制透明色扇形
        paint.setColor(getResources().getColor(android.R.color.transparent));
        canvas.drawArc(210, 160, 690, 640, 0, 3, true, paint);

        //绘制紫色扇形
        paint.setColor(getResources().getColor(R.color.pie_purple));
        canvas.drawArc(210, 160, 690, 640, 3, 7, true, paint);

        //绘制灰色扇形
        paint.setColor(getResources().getColor(R.color.pie_grey));
        canvas.drawArc(210, 160, 690, 640, 13, 7, true, paint);

        //绘制绿色扇形
        paint.setColor(getResources().getColor(R.color.pie_green));
        canvas.drawArc(210, 160, 690, 640, 23, 57, true, paint);

        //绘制蓝色扇形
        paint.setColor(getResources().getColor(R.color.pie_blue));
        canvas.drawArc(210, 160, 690, 640, 83, 97, true, paint);


    }

    /**计算圆上某一点的坐标
     * @param cx 圆心x
     * @param cy 圆心y
     * @param radius 半径
     * @param angle 角度 只接受正角度*/
    private Point calcPoint(int cx,
                            int cy,
                            int radius,
                            int angle){
        Point point = new Point();

        point.x = (int) (cx + radius * Math.cos(angle));
        point.y = (int) (cy + radius * Math.sin(angle));
        Log.d(TAG, "calcPoint: " + point);
        return point;
    }

    private static class Point{
        public int x;
        public int y;

        @Override
        public String toString() {
            final StringBuilder sb = new StringBuilder("Point{");
            sb.append("x=").append(x);
            sb.append(", y=").append(y);
            sb.append('}');
            return sb.toString();
        }
    }
}
