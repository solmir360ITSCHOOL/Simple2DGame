package net.rsnode.simple2dgame;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.util.AttributeSet;
import android.view.MotionEvent;
import android.view.View;
import androidx.annotation.Nullable;

public class Graphics extends View {
    private float pos_x = 0, pos_y = 0, ax, ay, vx = 1, vy = 1;

    public Graphics(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);

        setOnTouchListener(new OnTouchListener() {
            @Override
            public boolean onTouch(View view, MotionEvent motionEvent) {
                float x = motionEvent.getX();
                float y = motionEvent.getY();
                float d = (float) Math.sqrt((pos_x - x) * (pos_x - x) + (pos_y - y) * (pos_y - y));
                float a = 2.0f;

                ax = a * (x - pos_x) / d;
                ay = a * (y - pos_y) / d;

                return false;
            }
        });
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);

        this.vx += this.ax;
        this.vy += this.ay;
        this.pos_x += this.vx;
        this.pos_y += this.vy;

        Paint p = new Paint();
        p.setColor(Color.GREEN);
        canvas.drawCircle(this.pos_x, this.pos_y, 30.0f, p);

        this.invalidate();
    }
}