package ired.pathmeasuredemo;

import android.animation.ValueAnimator;
import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.DashPathEffect;
import android.graphics.Paint;
import android.graphics.Path;
import android.graphics.PathEffect;
import android.graphics.PathMeasure;
import android.os.Build;
import android.support.annotation.RequiresApi;
import android.util.AttributeSet;
import android.view.View;
import android.view.animation.AccelerateDecelerateInterpolator;

/**
 * Created by kevin on 16/8/2.
 */

public class PathPainterEffectView extends View {

    private Paint paint;
    private Path path;
    private PathMeasure pathMeasure;
    private PathEffect pathEffect;
    private float fraction = 0;
    public PathPainterEffectView(Context context) {
        super(context);
        init();
    }

    public PathPainterEffectView(Context context, AttributeSet attrs) {
        super(context, attrs);
        init();
    }

    public PathPainterEffectView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        init();
    }

    @RequiresApi(api = Build.VERSION_CODES.LOLLIPOP)
    public PathPainterEffectView(Context context, AttributeSet attrs, int defStyleAttr, int defStyleRes) {
        super(context, attrs, defStyleAttr, defStyleRes);
        init();
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        canvas.drawPath(path, paint);
    }

    private void init(){
        path = new Path();
        path.moveTo(100,100);
        path.lineTo(100,500);
        path.lineTo(400,300);
        path.close();

        paint = new Paint();
        paint.setColor(Color.BLACK);
        paint.setStyle(Paint.Style.STROKE);
        paint.setStrokeWidth(5);

        pathMeasure = new PathMeasure(path,false);
        final float length = pathMeasure.getLength();

        ValueAnimator animator = ValueAnimator.ofFloat(1,0);
        animator.setInterpolator(new AccelerateDecelerateInterpolator());
        animator.setDuration(2000);
        animator.setRepeatCount(ValueAnimator.INFINITE);
        animator.addUpdateListener(new ValueAnimator.AnimatorUpdateListener() {
            @Override
            public void onAnimationUpdate(ValueAnimator valueAnimator) {
                fraction = (float) valueAnimator.getAnimatedValue();
                pathEffect = new DashPathEffect(new float[]{length, length}, fraction * length);
                paint.setPathEffect(pathEffect);
                invalidate();
            }
        });
        animator.start();

    }
}
