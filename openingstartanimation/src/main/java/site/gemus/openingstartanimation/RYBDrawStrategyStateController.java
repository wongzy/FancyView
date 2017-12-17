package site.gemus.openingstartanimation;

import android.graphics.Canvas;
import android.graphics.drawable.Drawable;

/**
 * @author Jackdow
 * @version 1.0
 *          FancyView
 */
class RYBDrawStrategyStateController {

  private RYBDrawStrategyStateInterface mRYBDrawStrategyStateInterface;
  private RYBDrawStrategyStateOne mRYBDrawStrategyStateOne = new RYBDrawStrategyStateOne();
  private RYBDrawStrategyStateTwo mRYBDrawStrategyStateTwo = new RYBDrawStrategyStateTwo();

  RYBDrawStrategyStateController(){
  }

 /**
  * 设置状态
  * @param rybDrawStrategyStateInterface 状态接口
  */
  private void setState(RYBDrawStrategyStateInterface rybDrawStrategyStateInterface) {
   mRYBDrawStrategyStateInterface = rybDrawStrategyStateInterface;
  }

 /**
  * 选择合适的状态来执行操作
  * @param canvas 画布
  * @param fraction 完成时间百分比
  * @param icon 图标
  * @param colorOfIcon 绘制颜色
  * @param widthAndHeightOfView view的宽高
  */
  void choseStateDrawIcon(Canvas canvas, float fraction, Drawable icon, int colorOfIcon,
                          WidthAndHeightOfView widthAndHeightOfView) {
      if (fraction <= 0.65f) {
          setState(mRYBDrawStrategyStateOne);
      } else if (fraction > 0.65f) {
          setState(mRYBDrawStrategyStateTwo);
      }
      mRYBDrawStrategyStateInterface.drawIcon(canvas, fraction, icon, colorOfIcon, widthAndHeightOfView);
  }

}
