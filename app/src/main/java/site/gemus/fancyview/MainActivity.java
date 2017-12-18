package site.gemus.fancyview;

import android.graphics.Canvas;
import android.graphics.drawable.Drawable;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import site.gemus.openingstartanimation.DrawStrategy;
import site.gemus.openingstartanimation.LineDrawStrategy;
import site.gemus.openingstartanimation.NormalDrawStrategy;
import site.gemus.openingstartanimation.OpeningStartAnimation;
import site.gemus.openingstartanimation.RedYellowBlueDrawStrategy;
import site.gemus.openingstartanimation.RotationDrawStrategy;
import site.gemus.openingstartanimation.WidthAndHeightOfView;

/**
 *
 */
public class MainActivity extends AppCompatActivity implements View.OnClickListener{

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Button button1, button2, button3, button4;
        button1 = findViewById(R.id.button_normal);
        button2 = findViewById(R.id.button_ryb);
        button3 = findViewById(R.id.button_line);
        button4 = findViewById(R.id.button_rotation);
        button1.setOnClickListener(this);
        button2.setOnClickListener(this);
        button3.setOnClickListener(this);
        button4.setOnClickListener(this);

    }

    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.button_normal:
                OpeningStartAnimation openingStartAnimation = new OpeningStartAnimation.Builder(this)
                        .setDrawStategy(new NormalDrawStrategy())
                        .create();
                openingStartAnimation.show(this);
                break;
            case R.id.button_ryb:
                OpeningStartAnimation openingStartAnimation1 = new OpeningStartAnimation.Builder(this)
                        .setDrawStategy(new RedYellowBlueDrawStrategy())
                        .create();
                openingStartAnimation1.show(this);
                break;
            case R.id.button_line:
                OpeningStartAnimation openingStartAnimation2 = new OpeningStartAnimation.Builder(this)
                        .setDrawStategy(new LineDrawStrategy())
                        .create();
                openingStartAnimation2.show(this);
                break;
            case R.id.button_rotation:
                OpeningStartAnimation openingStartAnimation3 = new OpeningStartAnimation.Builder(this)
                        .setDrawStategy(new RotationDrawStrategy())
                        .create();
                openingStartAnimation3.show(this);
                break;
            default:
                break;
        }
    }
}
