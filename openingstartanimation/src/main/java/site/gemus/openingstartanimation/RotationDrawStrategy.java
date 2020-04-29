package site.gemus.openingstartanimation;

import android.graphics.Bitmap;
import android.graphics.Camera;
import android.graphics.Canvas;
import android.graphics.Matrix;
import android.graphics.Paint;
import android.graphics.Path;
import android.graphics.RectF;
import android.graphics.drawable.BitmapDrawable;
import android.graphics.drawable.Drawable;

import site.gemus.openingstartanimation.utils.BitmapUtils;

/**
 * @author Jackdow
 * @version 1.0
 *          FancyView
 */

public class RotationDrawStrategy implements DrawStrategy {
    public RotationDrawStrategy() {
    }
    @Override
    public void drawAppName(Canvas canvas, float fraction, String name, int colorOfAppName,
                            WidthAndHeightOfView widthAndHeightOfView) {
        canvas.save();
        int width = widthAndHeightOfView.getWidth();
        int height = widthAndHeightOfView.getHeight();
        canvas.clipRect(width / 2 - 150, height / 2 - 80,
                width / 2 + 150 * fraction, height / 2 + 65 * fraction);
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
        drawIcon(canvas, (int) (fraction * 540), BitmapUtils.drawableToBitmap(icon),
                widthAndHeightOfView.getWidth(), widthAndHeightOfView.getHeight());
    }

    @Override
    public void drawAppStatement(Canvas canvas, float fraction, String statement,
                                 int colorOfStatement, WidthAndHeightOfView widthAndHeightOfView) {
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

    /**
     * 根据角度绘制图标的类
     * @param canvas 画布
     * @param degree 角度
     * @param icon 图标
     * @param width view宽度
     * @param height view高度
     */
    private void drawIcon(Canvas canvas, int degree, Bitmap icon, int width, int height) {
        if (degree <= 180) {
            drawIconOne(canvas, degree / 180f, icon, width, height);
        }
        if (degree > 180 && degree <= 360) {
            drawIconTwo(canvas, degree - 180, icon, width, height);
        }
        if (degree > 360 && degree <= 540) {
            drawIconThree(canvas, degree - 360, icon, width, height);
        }
    }

    /**
     * 根据角度绘制图标的类
     * @param canvas 画布
     * @param fraction 完成时间百分比
     * @param icon 图标
     * @param width view宽度
     * @param height view高度
     */
    private void drawIconOne(Canvas canvas, float fraction, Bitmap icon, int width, int height) {
        Camera camera = new Camera();
        Paint paint = new Paint(Paint.ANTI_ALIAS_FLAG);
        canvas.save();
        int centerX = width / 2;
        int centerY = height / 2 - 200;
        int x = centerX - icon.getWidth()/2;
        int y = centerY - icon.getHeight()/2;
        Matrix matrix = new Matrix();
        matrix.postScale(1.7f, 1.7f, centerX, centerY);
        canvas.concat(matrix);
        canvas.clipRect(x, y, x + icon.getWidth() * fraction * 0.5f, y + icon.getHeight() * fraction * 0.5f);
        canvas.translate(centerX, centerY);
        camera.save();
        camera.rotateX(180);
        camera.rotateY(-180);
        camera.applyToCanvas(canvas);
        camera.restore();
        canvas.translate(-centerX, -centerY);
        canvas.drawBitmap(icon, x, y, paint);
        canvas.restore();
    }

    /**
     * 根据角度绘制图标的类
     * @param canvas 画布
     * @param degree 角度
     * @param icon 图标
     * @param width view宽度
     * @param height view高度
     */
    private void drawIconTwo(Canvas canvas, int degree, Bitmap icon, int width, int height) {
        Camera camera = new Camera();
        Paint paint = new Paint(Paint.ANTI_ALIAS_FLAG);
        int centerX = width / 2;
        int centerY = height / 2 - 200;
        int x = centerX - icon.getWidth()/2;
        int y = centerY - icon.getHeight()/2;
        Matrix matrix = new Matrix();
        matrix.postScale(1.7f, 1.7f, centerX, centerY);

        //绘制左半部分
        canvas.save();
        canvas.concat(matrix);
        canvas.clipRect(x, y, x + icon.getWidth() / 2, y + icon.getHeight() / 2);
        canvas.translate(centerX, centerY);
        camera.save();
        camera.rotateX(180);
        camera.applyToCanvas(canvas);
        camera.restore();
        canvas.translate(-centerX, -centerY);
        canvas.drawBitmap(icon, x, y, paint);
        canvas.restore();

        //绘制右半部分
        canvas.save();
        canvas.concat(matrix);
        if (degree <= 90)
            canvas.clipRect(x , y , x + icon.getWidth() / 2, y + icon.getHeight() / 2);
        else
            canvas.clipRect(x + icon.getWidth() / 2, y, x + icon.getWidth(), y + icon.getHeight() / 2);
        canvas.translate(centerX, centerY);
        camera.save();
        camera.rotateX(180);
        camera.rotateY(180 - degree);
        camera.applyToCanvas(canvas);
        camera.restore();
        canvas.translate(-centerX, -centerY);
        canvas.drawBitmap(icon, x, y, paint);
        canvas.restore();
    }

    /**
     * 根据角度绘制图标的类
     * @param canvas 画布
     * @param degree 角度
     * @param icon 图标
     * @param width view宽度
     * @param height view高度
     */
    private void drawIconThree(Canvas canvas, int degree, Bitmap icon, int width, int height) {
        Camera camera = new Camera();
        Paint paint = new Paint(Paint.ANTI_ALIAS_FLAG);
        int centerX = width / 2;
        int centerY = height / 2 - 200;
        int x = centerX - icon.getWidth()/2;
        int y = centerY - icon.getHeight()/2;
        Matrix matrix = new Matrix();
        matrix.postScale(1.7f, 1.7f, centerX, centerY);
        //绘制上半部分
        canvas.save();
        canvas.concat(matrix);
        canvas.clipRect(x, y, x + icon.getWidth(), y + icon.getHeight() / 2);
        canvas.drawBitmap(icon, x, y, paint);
        canvas.restore();

        //绘制下半部分
        canvas.save();
        canvas.concat(matrix);
        if (degree <= 90)
            canvas.clipRect(x, y, x + icon.getWidth(), y + icon.getHeight() / 2);
        else
            canvas.clipRect(x, y + icon.getHeight() / 2, x + icon.getWidth(), y + icon.getHeight());
        canvas.translate(centerX, centerY);
        camera.save();
        camera.rotateX(180 - degree);
        camera.applyToCanvas(canvas);
        camera.restore();
        canvas.translate(-centerX, -centerY);
        canvas.drawBitmap(icon, x, y, paint);
        canvas.restore();
    }
}
