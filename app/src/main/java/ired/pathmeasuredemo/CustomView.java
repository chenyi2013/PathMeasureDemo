package ired.pathmeasuredemo;

import android.animation.ValueAnimator;
import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.Path;
import android.graphics.PathMeasure;
import android.os.Build;
import android.support.annotation.RequiresApi;
import android.util.AttributeSet;
import android.view.View;

/**
 * Created by kevin on 16/8/2.
 */

public class CustomView extends View {

    private Path path;
    private Paint paint;
    private Path des;
    private PathMeasure pathMeasure;
    private float animatorValue;
    private float length;
    public CustomView(Context context) {
        super(context);
        init();
    }

    public CustomView(Context context, AttributeSet attrs) {
        super(context, attrs);
        init();
    }

    public CustomView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        init();
    }

    @RequiresApi(api = Build.VERSION_CODES.LOLLIPOP)
    public CustomView(Context context, AttributeSet attrs, int defStyleAttr, int defStyleRes) {
        super(context, attrs, defStyleAttr, defStyleRes);
        init();
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        des.reset();
        // 硬件加速的BUG
        des.lineTo(0,0);
        float stop = length * animatorValue;
        float start = (float) (stop - ((0.5 - Math.abs(animatorValue - 0.5)) * length));
        pathMeasure.getSegment(start, stop, des, true);
        canvas.drawPath(des, paint);

    }

    private void init(){

        paint = new Paint();
        paint.setAntiAlias(true);
        paint.setStrokeWidth(5);
        paint.setStyle(Paint.Style.STROKE);

        des = new Path();
        path = new Path();
        path.moveTo(10,10);
        path.lineTo(30,50);
        path.lineTo(60,100);
        path.lineTo(90,200);
        path.lineTo(200,43);
        path.lineTo(400,500);
        path.lineTo(300,250);
        path.close();

        pathMeasure = new PathMeasure(path,true);
        length = pathMeasure.getLength();

        final ValueAnimator valueAnimator = ValueAnimator.ofFloat(0,1);
        valueAnimator.addUpdateListener(new ValueAnimator.AnimatorUpdateListener() {
            @Override
            public void onAnimationUpdate(ValueAnimator valueAnimator) {
                animatorValue = (float)valueAnimator.getAnimatedValue();
                invalidate();
            }
        });
        valueAnimator.setDuration(2000);
        valueAnimator.setRepeatCount(ValueAnimator.INFINITE);
        valueAnimator.start();

    }
}
