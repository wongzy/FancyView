package site.gemus.openingstartanimation;

import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.Path;
import android.graphics.RectF;
import android.graphics.drawable.Drawable;

/**
 * @author Jackdow
 * @version 1.0
 *          FancyView
 */

public class RedYellowBlueDrawStrategy implements DrawStrategy {
    private RYBDrawStrategyStateController mRYBDrawStrategyStateController;
    public RedYellowBlueDrawStrategy() {
        mRYBDrawStrategyStateController = new RYBDrawStrategyStateController();
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
        mRYBDrawStrategyStateController.choseStateDrawIcon(canvas, fraction, icon, colorOfIcon, widthAndHeightOfView);
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
