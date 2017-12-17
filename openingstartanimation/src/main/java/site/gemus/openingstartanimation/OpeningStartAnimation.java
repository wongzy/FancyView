package site.gemus.openingstartanimation;
import android.animation.ObjectAnimator;
import android.app.Activity;
import android.content.Context;
import android.content.pm.PackageManager;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.drawable.Drawable;
import android.os.Handler;
import android.os.Message;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.view.ViewGroup;
import android.widget.FrameLayout;

import java.lang.ref.SoftReference;
import java.util.Timer;
import java.util.TimerTask;

/**
 * @author Jackdow
 * @version 1.0
 *          FancyView
 */

 public class OpeningStartAnimation extends View {

     private long animationInterval = 1500;
     private long animationFinishTime = 350;
     private WidthAndHeightOfView mWidthAndHeightOfView;
     private int colorOfBackground = Color.WHITE;
     private float fraction;
     private Drawable mDrawable = null;
     private int colorOfAppIcon = Color.parseColor("#00897b");
     private String appName = "";
     private int colorOfAppName = Color.parseColor("#00897b");
     private String appStatement = "";
     private int colorOfAppStatement = Color.parseColor("#607D8B");
     private DelegateRecycleView mDelegateRecycleView;
     private DrawStrategy mDrawStrategy = new NormalDrawStrategy();
     private static final int FINISHANIMATION = 1;

     private OpeningStartAnimation(Context context) {
        super(context);
        PackageManager packageManager = context.getPackageManager();
         mDrawable = context.getApplicationInfo().loadIcon(packageManager);
         appName = (String) packageManager.getApplicationLabel(context.getApplicationInfo());
         appStatement = "Sample Statement";
    }

    /**
     * 设置完成的百分比
     * @param fraction 百分比
     */
    @SuppressWarnings("unused")
    private void setFraction(float fraction) {
        this.fraction = fraction;
        invalidate();
    }

    {
        FrameLayout.LayoutParams layoutParams;
        layoutParams = new FrameLayout.LayoutParams(FrameLayout.LayoutParams.MATCH_PARENT,
                FrameLayout.LayoutParams.MATCH_PARENT);
        this.setLayoutParams(layoutParams);
        mWidthAndHeightOfView = new WidthAndHeightOfView();
    }



    @Override
    protected void onDraw(Canvas canvas) {
        canvas.drawColor(colorOfBackground); //绘制背景色
        super.onDraw(canvas);
        mWidthAndHeightOfView.setHeight(getHeight());
        mWidthAndHeightOfView.setWidth(getWidth());
        mDrawStrategy.drawAppIcon(canvas, fraction, mDrawable, colorOfAppIcon,
                mWidthAndHeightOfView);
        mDrawStrategy.drawAppName(canvas, fraction, appName, colorOfAppName,
                mWidthAndHeightOfView);
        mDrawStrategy.drawAppStatement(canvas, fraction, appStatement, colorOfAppStatement,
                mWidthAndHeightOfView);
    }

    /**
     * 显示动画
     * @param mactivity 显示动画的界面
     */
    public void show(Activity mactivity) {
        SoftReference<Activity> softReference = new SoftReference<Activity>(mactivity);
        final Activity activity = softReference.get();
        View decorView = activity.getWindow().getDecorView();
        decorView.setSystemUiVisibility(View.SYSTEM_UI_FLAG_FULLSCREEN);
        if (activity instanceof AppCompatActivity) {
            ActionBar actionBar = ((AppCompatActivity) activity).getSupportActionBar();
            if (actionBar != null)
                actionBar.hide();
        } else {
            android.app.ActionBar actionBar = activity.getActionBar();
            if (actionBar != null)
                actionBar.hide();
        }
        ViewGroup.LayoutParams layoutParams = new ViewGroup.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT,
                ViewGroup.LayoutParams.MATCH_PARENT);
        activity.addContentView(this, layoutParams);
        ObjectAnimator objectAnimator = ObjectAnimator.ofFloat(this, "fraction", 0, 1);
        objectAnimator.setDuration(animationInterval - 50);
        objectAnimator.start();
        //处理动画定时
        final Handler handler = new Handler(new Handler.Callback() {
            @Override
            public boolean handleMessage(Message message) {
                if (message.what == FINISHANIMATION) {
                    moveAnimation(activity);
                }
                return false;
            }
        });
        //动画定时器
        new Timer().schedule(new TimerTask() {
            @Override
            public void run() {
                Message message = new Message();
                message.what = FINISHANIMATION;
                handler.sendMessage(message);
            }
        }, animationInterval);
    }

    /**
     * 隐藏动画view
     * @param activity 当前活动
     */
    private void moveAnimation(Activity activity) {
        View decorView = activity.getWindow().getDecorView();
        decorView.setSystemUiVisibility(View.SYSTEM_UI_FLAG_VISIBLE);
        if (activity instanceof AppCompatActivity) {
            ActionBar actionBar = ((AppCompatActivity) activity).getSupportActionBar();
            if (actionBar != null)
                actionBar.show();
        } else {
            android.app.ActionBar actionBar = activity.getActionBar();
            if (actionBar != null)
                actionBar.show();
        }
        this.animate()
                .scaleX(0)
                .scaleY(0)
                .withLayer()
                .alpha(0)
                .setDuration(animationFinishTime);
        mDelegateRecycleView.finishAnimation();
    }

    /**
     * 使用Builder模式创建对象
     */
    public static final class Builder implements DelegateRecycleView {
        OpeningStartAnimation mOpeningStartAnimation;

        public Builder(Context context) {
            mOpeningStartAnimation = new OpeningStartAnimation(context);
            mOpeningStartAnimation.mDelegateRecycleView = this;
        }

        /**
         * 设置动画时间的方法
         * @param animationInterval 动画时间
         * @return Builder对象
         */
        public Builder setAnimationInterval(long animationInterval) {
            mOpeningStartAnimation.animationInterval = animationInterval;
            return  this;
        }

        /**
         * 设置动画消失的时间
         * @param animationFinishTime 动画消失的时间
         * @return Builder对象
         */
        public Builder setAnimationFinishTime(long animationFinishTime) {
            mOpeningStartAnimation.animationFinishTime = animationFinishTime;
            return this;
        }

        /**
         * 设置动画图标
         * @param drawable 动画图标
         * @return Builder对象
         */
        public Builder setAppIcon(Drawable drawable) {
            mOpeningStartAnimation.mDrawable = drawable;
            return this;
        }

        /**
         * 设置图标绘制辅助颜色
         * @param colorOfAppIcon 辅助颜色
         * @return Builder对象
         */
        public Builder setColorOfAppIcon(int colorOfAppIcon) {
            mOpeningStartAnimation.colorOfAppIcon = colorOfAppIcon;
            return this;
        }

        /**
         * 设置要绘制的app名称
         * @param appName app名称
         * @return Builder对象
         */
        public Builder setAppName(String appName) {
            mOpeningStartAnimation.appName = appName;
            return this;
        }

        /**
         * 设置app名称的颜色
         * @param colorOfAppName app名称颜色
         * @return Builder对象
         */
        public Builder setColorOfAppName(int colorOfAppName) {
            mOpeningStartAnimation.colorOfAppName = colorOfAppName;
            return this;
        }

        /**
         * 设置app一句话描述
         * @param appStatement app一句话描述
         * @return Builder对象
         */
        public Builder setAppStatement(String appStatement) {
            mOpeningStartAnimation.appStatement = appStatement;
            return this;
        }

        /**
         * 设置一句话描述的字体颜色
         * @param colorOfAppStatement 字体颜色
         * @return Builder对象
         */
        public Builder setColorOfAppStatement(int colorOfAppStatement) {
            mOpeningStartAnimation.colorOfAppStatement = colorOfAppStatement;
            return this;
        }

        /**
         * 设置背景颜色
         * @param colorOfBackground 背景颜色对应的int值
         * @return Builder对象
         */
        public Builder setColorOfBackground(int colorOfBackground) {
            mOpeningStartAnimation.colorOfBackground = colorOfBackground;
            return this;
        }

        /**
         * 开放绘制策略接口，可由用户自行定义
         * @param drawStrategy 绘制接口
         * @return Builder对象
         */
        public Builder setDrawStategy(DrawStrategy drawStrategy) {
            mOpeningStartAnimation.mDrawStrategy = drawStrategy;
            return this;
        }

        /**
         * 创建开屏动画
         * @return 创建出的开屏动画
         */
        public OpeningStartAnimation create() {
            return  mOpeningStartAnimation;
        }

        @Override
        public void finishAnimation() {
            mOpeningStartAnimation = null;
        }
    }
}
