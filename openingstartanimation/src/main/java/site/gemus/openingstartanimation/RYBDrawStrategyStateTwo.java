package site.gemus.openingstartanimation;

import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Matrix;
import android.graphics.Paint;
import android.graphics.Path;
import android.graphics.drawable.BitmapDrawable;
import android.graphics.drawable.Drawable;

/**
 * @author Jackdow
 * @version 1.0
 *          FancyView
 */

class RYBDrawStrategyStateTwo implements RYBDrawStrategyStateInterface{
    @Override
    public void drawIcon(Canvas canvas, float fraction, Drawable drawable, int colorOfIcon,
                         WidthAndHeightOfView widthAndHeightOfView) {
        int centerX = widthAndHeightOfView.getWidth() / 2;
        int centerY = widthAndHeightOfView.getHeight() / 2 - 150;
        canvas.save();
        Paint paint = new Paint(Paint.ANTI_ALIAS_FLAG);
        float newFraction = (fraction - 0.65f) / 0.35f;
        paint.setColor(Color.parseColor("#e53935"));
        canvas.drawCircle(centerX, centerY - 50, 100 * (1 - newFraction), paint);
        paint.setColor(Color.parseColor("#fdd835"));
        canvas.drawCircle(centerX -35, centerY + 35,100 * (1 - newFraction), paint);
        paint.setColor(Color.parseColor("#1e88e5"));
        canvas.drawCircle(centerX + 35, centerY + 35, 100 * (1 - newFraction), paint);
        canvas.restore();
        canvas.save();
        Path path = new Path();
        Bitmap bitmap = ((BitmapDrawable) drawable).getBitmap();
        Matrix matrix = new Matrix();
        matrix.postScale(1.7f, 1.7f, centerX, centerY);
        canvas.concat(matrix);
        path.addCircle(centerX, centerY, bitmap.getHeight() * 1.5f * newFraction, Path.Direction.CW);
        canvas.clipPath(path);
        canvas.drawBitmap(bitmap, centerX - bitmap.getWidth() / 2, centerY - bitmap.getHeight() / 2, paint);
        canvas.restore();
    }
}
