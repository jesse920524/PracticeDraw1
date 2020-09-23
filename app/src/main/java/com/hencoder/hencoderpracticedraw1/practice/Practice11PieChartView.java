package com.hencoder.hencoderpracticedraw1.practice;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
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
         * 1. 绘制基准圆, 所有arc依附于基准圆
         * 2. 绘制各个arc 间隔角度为2度
         * 3. 绘制特殊arc: 红色arc. 其圆心与基准圆圆心偏移. 偏移量由算法确定.
         * 4. 确定每个arc圆弧中点, 并以此中点为原点绘制line
         * 5. 绘制文字*/
        drawArcs(canvas);



    }

    private static final int BASE_RADIUS = 300;//基准圆半径
    private static final int GAP_ANGEL = 2;//2个arc之间的间隔角度
    private void drawArcs(Canvas canvas) {
        //draw base circle
        Paint paint = new Paint(Paint.ANTI_ALIAS_FLAG);
        paint.setStyle(Paint.Style.FILL);
        paint.setColor(Color.TRANSPARENT);
        int cx = (getRight()-getLeft())/2;
        int cy = (getBottom() - getTop())/2;
        canvas.drawCircle(cx, cy, BASE_RADIUS, paint);

        int cLeft = cx - BASE_RADIUS;
        int cRight = cx + BASE_RADIUS;
        int cTop = cy - BASE_RADIUS;
        int cBottom = cy + BASE_RADIUS;

        //draw arcs

        int startAngel = 0;
        startAngel += 0+GAP_ANGEL;
        paint.setColor(getResources().getColor(R.color.pie_purple));
        canvas.drawArc(cLeft,
                cTop,
                cRight,
                cBottom,
                startAngel,
                5,
                true,
                paint);

        startAngel += 5+GAP_ANGEL;
        paint.setColor(getResources().getColor(R.color.pie_grey));
        canvas.drawArc(cLeft,
                cTop,
                cRight,
                cBottom,
                startAngel,
                5,
                true,
                paint);

        startAngel += 5+GAP_ANGEL;
        paint.setColor(getResources().getColor(R.color.pie_green));
        canvas.drawArc(cLeft,
                cTop, cRight,
                cBottom,
                startAngel,
                60,
                true,
                 paint);

        startAngel += 60 + GAP_ANGEL;
        paint.setColor(getResources().getColor(R.color.pie_blue));
        canvas.drawArc(cLeft, cTop, cRight, cBottom,
                startAngel,
                100,
                true,
                paint);

        startAngel += 100 + GAP_ANGEL;
        paint.setColor(getResources().getColor(R.color.pie_red));
        canvas.drawArc(cLeft, cTop, cRight, cBottom,
                startAngel,
                120 - 4,
                true,
                paint);

        startAngel += 120 - GAP_ANGEL + GAP_ANGEL;
        paint.setColor(getResources().getColor(R.color.pie_yellow));
        canvas.drawArc(cLeft, cTop, cRight, cBottom,
                startAngel,
                60,
                true,
                paint);
    }

    @Deprecated
    private void oldDraw(Canvas canvas) {
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
        Point yellowPoint = calcPoint(450, 400, 240, -60, 60);
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
     * @param startAngle 角度
     * @param sweepAngle */
    private Point calcPoint(int cx,
                             int cy,
                            int radius,
                            double startAngle,
                            double sweepAngle){

        double radians = Math.toRadians(startAngle + sweepAngle/2);
        Point point = new Point();

        point.x = (int) (cx + radius * Math.cos(radians));
        point.y = (int) (cy + radius * Math.sin(radians));
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
