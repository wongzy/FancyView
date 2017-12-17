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

public class LineDrawStrategy implements DrawStrategy {

    public LineDrawStrategy(){
    }

    @Override
    public void drawAppName(Canvas canvas, float fraction, String name, int colorOfAppName,
                            WidthAndHeightOfView widthAndHeightOfView) {
        canvas.save();
        Paint paint = new Paint(Paint.ANTI_ALIAS_FLAG);
        paint.setColor(colorOfAppName);
        paint.setStyle(Paint.Style.STROKE);
        paint.setStrokeWidth(5);
        paint.setTextSize(50);
        paint.setStrokeJoin(Paint.Join.ROUND);
        paint.setTextAlign(Paint.Align.LEFT);
        float x = widthAndHeightOfView.getWidth() / 2;
        int centerY = widthAndHeightOfView.getHeight() / 2;
        float y = centerY - 275;
        Path path = new Path();
        path.moveTo(x, y);
        if (fraction <= 0.50) {
            path.lineTo(x, y + (25 + name.length() + 250) * (fraction / 0.50f));
            canvas.drawPath(path, paint);
        } else {
            path.lineTo(x, y + (25 + name.length() + 250) * ((1 - fraction)/ 0.50f));
            canvas.drawPath(path, paint);
            paint.setStyle(Paint.Style.FILL);
            canvas.drawText(name, x + 20, y + 150, paint);
        }
        canvas.restore();
    }

    @Override
    public void drawAppIcon(Canvas canvas, float fraction, Drawable icon, int colorOfIcon,
                            WidthAndHeightOfView widthAndHeightOfView) {
        int centerX = widthAndHeightOfView.getWidth() / 2;
        int centerY = widthAndHeightOfView.getHeight() / 2;
        Bitmap bitmap = ((BitmapDrawable) icon).getBitmap();
        Paint paint = new Paint(Paint.ANTI_ALIAS_FLAG);
        paint.setColor(colorOfIcon);
        paint.setStrokeWidth(3);
        paint.setStrokeJoin(Paint.Join.ROUND);
        paint.setStyle(Paint.Style.STROKE);
        float bitmapLeft = centerX - 250;
        float bitmapRight = bitmapLeft + bitmap.getWidth() * 1.7f;
        float bitmapTop = centerY - 250;
        float bitmapBottom = bitmapTop + bitmap.getHeight() * 1.7f;
        canvas.save();
        if (fraction <= 0.75) {
            float newfraction = fraction / 0.75f;
            if (newfraction <= 0.25) {
                canvas.drawLine(bitmapLeft, bitmapBottom, bitmapLeft,
                        bitmapBottom - (bitmapBottom - bitmapTop) * (newfraction / 0.25f), paint);
              //  path.lineTo(bitmapLeft, bitmapBottom + (bitmapBottom - bitmapTop) * (newfraction / 0.25f));
            } else {
                canvas.drawLine(bitmapLeft, bitmapBottom, bitmapLeft, bitmapTop, paint);
              //  path.lineTo(bitmapLeft, bitmapTop);
            }
            if (newfraction > 0.25) {
                if (newfraction <= 0.50) {
                    canvas.drawLine(bitmapLeft, bitmapTop,
                            bitmapLeft + (bitmapRight - bitmapLeft) * ((newfraction - 0.25f)/0.25f),
                            bitmapTop, paint);
                  //  path.lineTo(bitmapLeft + (bitmapRight - bitmapLeft) * ((newfraction - 0.25f)/0.25f),
                   //         bitmapTop);
                } else {
                    canvas.drawLine(bitmapLeft, bitmapTop, bitmapRight, bitmapTop, paint);
                   // path.lineTo(bitmapRight, bitmapTop);
                }
            }
            if (newfraction > 0.50) {
                if (newfraction <= 0.75) {
                    canvas.drawLine(bitmapRight, bitmapTop, bitmapRight,
                            bitmapTop + (bitmapBottom - bitmapTop) * ((newfraction - 0.50f) / 0.25f),
                            paint);
                    //path.lineTo(bitmapRight, bitmapTop + (bitmapBottom - bitmapTop) * ((fraction - 0.50f) / 0.25f));
                } else {
                    canvas.drawLine(bitmapRight, bitmapTop, bitmapRight, bitmapBottom, paint);
                    //path.lineTo(bitmapRight, bitmapBottom);
                }
            }
            if (newfraction > 0.75) {
                if (newfraction <= 1) {
                    canvas.drawLine(bitmapRight, bitmapBottom, bitmapRight - (bitmapRight - bitmapLeft) * ((newfraction - 0.75f)/ 0.25f),
                                     bitmapBottom, paint);
                   // path.lineTo(bitmapLeft + (bitmapRight - bitmapLeft) * ((fraction - 0.75f)/ 0.25f),
                   //         bitmapBottom);
                } else {
                    canvas.drawLine(bitmapRight, bitmapBottom, bitmapLeft, bitmapBottom, paint);
                   // path.lineTo(bitmapLeft, bitmapBottom);
                }
            }
        }
        canvas.restore();
        canvas.save();
        if (fraction > 0.75) {
            canvas.clipRect(bitmapLeft + (bitmap.getWidth()/2f) * ((1 - fraction) /0.25f),
                    bitmapTop + (bitmap.getHeight()/2f)* ((1 - fraction) / 0.25f),
                    bitmapRight - (bitmap.getWidth()/2f) * ((1 - fraction) /0.25f),
                    bitmapBottom - (bitmap.getHeight()/2f)* ((1 - fraction) / 0.25f));
            Matrix matrix = new Matrix();
            matrix.postScale(1.7f, 1.7f, (bitmapLeft + bitmapRight) * 0.5f,
                    (bitmapTop + bitmapBottom) * 0.5f);
            canvas.concat(matrix);
            canvas.drawBitmap(bitmap, (bitmapLeft + bitmapRight) / 2 - bitmap.getWidth() / 2,
                    (bitmapTop + bitmapBottom) / 2 - bitmap.getHeight() / 2, paint);
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
