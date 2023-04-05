package fr.bam.projetfinal;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.util.AttributeSet;
import android.view.View;

public class CircleView extends View {
    private Paint paint;
    private int color;
    private boolean show;

    public CircleView(Context context) {
        super(context);
        init();
    }

    public CircleView(Context context, AttributeSet attrs) {
        super(context, attrs);
        init();
    }

    private void init() {
        paint = new Paint(Paint.ANTI_ALIAS_FLAG);
        color = Color.parseColor("#00FFFF");
        show = false;
    }

    public void setColor(int color) {
        this.color = color;
        invalidate();
    }

    public void setShow(boolean show) {
        this.show = show;
        invalidate();
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);

        if (show) {
            paint.setColor(color);
            canvas.drawCircle(getWidth() / 2f, getHeight() / 2f, getWidth() / 3f, paint);
        }
    }
}
