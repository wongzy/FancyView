package site.gemus.openingstartanimation;

import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.drawable.Drawable;


/**
 * @author Jackdow
 * @version 1.0
 *          FancyView
 */

class RYBDrawStrategyStateOne implements RYBDrawStrategyStateInterface {
    @Override
    public void drawIcon(Canvas canvas, float fraction, Drawable drawable, int colorOfIcon,
                         WidthAndHeightOfView widthAndHeightOfView) {
        float newFraction = fraction / 0.65f;
        int centerX = widthAndHeightOfView.getWidth() / 2;
        int centerY = widthAndHeightOfView.getHeight() / 2 - 150;
        Paint paint = new Paint(Paint.ANTI_ALIAS_FLAG);
        canvas.save();
        paint.setColor(Color.parseColor("#e53935"));
        if (newFraction <= 0.33f)  {
            canvas.drawCircle(centerX, centerY - 50, 100 * (newFraction / 0.33f), paint);
        } else {
            canvas.drawCircle(centerX, centerY - 50, 100, paint);
        }
        if (newFraction > 0.33f) {
            paint.setColor(Color.parseColor("#fdd835"));
            if (newFraction <= 0.66f)
                canvas.drawCircle(centerX -35, centerY + 35,100 * ((newFraction - 0.33f) / 0.33f), paint);
            else
                canvas.drawCircle(centerX -35, centerY + 35,100, paint);
        }
        if (newFraction > 0.66f) {
            paint.setColor(Color.parseColor("#1e88e5"));
            if (newFraction <= 1f)
                canvas.drawCircle(centerX + 35, centerY + 35, 100 * ((newFraction - 0.66f) / 0.34f), paint);
            else
                canvas.drawCircle(centerX + 35, centerY + 35, 100, paint);
        }
        canvas.restore();
    }
}
