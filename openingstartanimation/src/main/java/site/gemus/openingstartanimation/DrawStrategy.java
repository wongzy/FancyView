package site.gemus.openingstartanimation;

import android.graphics.Canvas;
import android.graphics.drawable.Drawable;

/**
 * @author Jackdow
 * @version 1.0
 *          FancyView
 */

public interface DrawStrategy {

    /**
     * 绘制app名称文字
     * @param canvas 画布
     * @param fraction 完成时间百分比
     * @param colorOfAppName 字体颜色
     * @param name 文字
     * @param widthAndHeightOfView view的宽和高
     */
    void drawAppName(Canvas canvas, float fraction, String name, int colorOfAppName,
                     WidthAndHeightOfView widthAndHeightOfView);

    /**
     * 绘制app图标
     * @param canvas 画布
     * @param fraction 完成时间百分比
     * @param colorOfIcon 绘制图标颜色
     * @param icon 图标
     * @param widthAndHeightOfView view的宽和高
     */
    void drawAppIcon(Canvas canvas, float fraction, Drawable icon, int colorOfIcon,
                     WidthAndHeightOfView widthAndHeightOfView);

    /**
     * 绘制app一句话描述
     * @param canvas 画布
     * @param fraction 完成时间百分比
     * @param statement 一句话描述
     * @param colorOfStatement 字体颜色
     * @param widthAndHeightOfView view的宽和高
     */
    void drawAppStatement(Canvas canvas, float fraction, String statement, int colorOfStatement,
                          WidthAndHeightOfView widthAndHeightOfView);
}
