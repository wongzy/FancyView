package site.gemus.openingstartanimation;

import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.Matrix;
import android.graphics.Paint;
import android.graphics.Path;
import android.graphics.RectF;
import android.graphics.drawable.BitmapDrawable;
import android.graphics.drawable.Drawable;

/**
 * @author Jackdow
 * @version 1.0
 *          FancyView
 */

public class NormalDrawStrategy implements DrawStrategy {

    public NormalDrawStrategy(){
    }

    @Override
    public void drawAppName(Canvas canvas, float fraction, String name, int colorOfAppName,
                            WidthAndHeightOfView widthAndHeightOfView) {
        canvas.save();
        int width = widthAndHeightOfView.getWidth();
        int height = widthAndHeightOfView.getHeight();
        Paint paint = new Paint();
        paint.setColor(colorOfAppName);
        paint.setTextAlign(Paint.Align.CENTER);
        paint.setTextSize(50);
        canvas.drawText(name, width / 2, height / 2 + 50, paint);
        canvas.restore();
    }

    @Override
    public void drawAppIcon(Canvas canvas, float fraction, Drawable icon, int colorOfIcon,
                            WidthAndHeightOfView widthAndHeightOfView) {
        canvas.save();
        int width = widthAndHeightOfView.getWidth();
        int height = widthAndHeightOfView.getHeight();
        Paint paint = new Paint(Paint.ANTI_ALIAS_FLAG);
        BitmapDrawable bitmapDrawable = (BitmapDrawable) icon;
        Bitmap bitmap = bitmapDrawable.getBitmap();

        int bitmapWidth = bitmap.getWidth();
        int bitmapHeight = bitmap.getHeight();
        int radius = bitmapWidth * 3 / 2;
        int centerX = width / 2 + bitmapWidth / 2;
        int centerY = height / 2 - 100;
        if (fraction <= 0.60) {
            Path path = new Path();
            Matrix matrix = new Matrix();
            matrix.postScale(1.2f, 1.2f, centerX - bitmapWidth /2, centerY - bitmapHeight / 2);
            path.addCircle(centerX, centerY, radius * (fraction - 0.1f) * 2, Path.Direction.CW);
            canvas.concat(matrix);
            canvas.clipPath(path);
            canvas.drawBitmap(bitmap, centerX - bitmapWidth, centerY - bitmapHeight, paint);
        } else {
            Matrix matrix = new Matrix();
            matrix.postScale(1.2f + (0.5f) * (fraction - 0.6f) * 2.5f,
                    1.2f + (0.5f) * (fraction - 0.6f) * 2.5f,
                    centerX - bitmapWidth /2, centerY - bitmapHeight / 2);
            canvas.concat(matrix);
            canvas.drawBitmap(bitmap, centerX - bitmapWidth, centerY - bitmapHeight, paint);
        }
        canvas.restore();
    }

    @Override
    public void drawAppStatement(Canvas canvas, float fraction, String statement, int colorOfStatement,
                                 WidthAndHeightOfView widthAndHeightOfView) {
        canvas.save();
        int width = widthAndHeightOfView.getWidth();
        int height = widthAndHeightOfView.getHeight();
        Paint paint = new Paint(Paint.ANTI_ALIAS_FLAG);
        paint.setColor(colorOfStatement);
        paint.setStyle(Paint.Style.STROKE);
        paint.setTextSize(45);
        paint.setTextSkewX(-0.2f);
        paint.setTextAlign(Paint.Align.CENTER);
        RectF rectF = new RectF(width / 4 - statement.length(), height * 7 / 8,
                width * 3, height);
        if (fraction <= 0.60f) {
            Path path = new Path();
            path.addArc(rectF,193,40 * fraction * 1.67f);
            canvas.drawPath(path, paint);
        } else {
            Path path = new Path();
            path.addArc(rectF, 193, 40);
            canvas.drawPath(path, paint);
            canvas.drawTextOnPath(statement, path, 0, 0, paint);
        }
        canvas.restore();
    }
}
