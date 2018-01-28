# OpeningStartAnimation
![bandge](https://img.shields.io/badge/OpeningStartAnimation-1.0-orange.svg)
#### 效果
[![1.gif](https://i.loli.net/2017/12/18/5a3756ad4bd35.gif)](https://i.loli.net/2017/12/18/5a3756ad4bd35.gif)
[![2.gif](https://i.loli.net/2017/12/18/5a3756b0696de.gif)](https://i.loli.net/2017/12/18/5a3756b0696de.gif)
[![3.gif](https://i.loli.net/2017/12/18/5a3756b30e64e.gif)](https://i.loli.net/2017/12/18/5a3756b30e64e.gif)
[![4.gif](https://i.loli.net/2017/12/18/5a3756b561685.gif)](https://i.loli.net/2017/12/18/5a3756b561685.gif)
#### 使用
```
compile 'site.gemus:openingstartanimation:1.0.0' //在gradle中导入项目
```
```
OpeningStartAnimation openingStartAnimation = new OpeningStartAnimation.Builder(this)
                        .setDrawStategy(new NormalDrawStrategy()) //设置动画效果
                        .create();
openingStartAnimation.show(this);
```
##### 除此之外，还可以设置文字、图标、一句话描述、动画时间等等，也可以自定义开屏动画，开放了策略接口，像这样
```
OpeningStartAnimation openingStartAnimation = new OpeningStartAnimation.Builder(this)
                .setDrawStategy(new DrawStrategy() {
                    @Override
                    public void drawAppName(Canvas canvas, float fraction, String name, int colorOfAppName, WidthAndHeightOfView widthAndHeightOfView) {
                        
                    }

                    @Override
                    public void drawAppIcon(Canvas canvas, float fraction, Drawable icon, int colorOfIcon, WidthAndHeightOfView widthAndHeightOfView) {

                    }

                    @Override
                    public void drawAppStatement(Canvas canvas, float fraction, String statement, int colorOfStatement, WidthAndHeightOfView widthAndHeightOfView) {

                    }
                })
                .create();
```
> 具体使用方法可见： http://blog.csdn.net/qq_33487412/article/details/78832116
> 目前共有四种动画可供选择，欢迎来一起制作出更多的开屏动画效果！
