package com.zju.homework5;

import android.app.Activity;
import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;

import java.util.Random;
import java.util.Timer;
import java.util.TimerTask;

public class SecondActivity extends Activity {
    private final int BALL_SIZE = 100;
    private Random random = new Random();
    private int ballX = 500;
    private int ballY = 600;

    private int nX = 500;
    private int nY = 500;
    private double angle = 0;

    private double len = 200;
    private int red = 255;
    private int green = 128;
    private int blue = 0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
//        setContentView(R.layout.activity_second);
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,
                WindowManager.LayoutParams.FLAG_FULLSCREEN);

        final MyView myView = new MyView(this);
        setContentView(myView);
       // System.out.println("AAAAA" + len);
//        处理消息
        final Handler handler = new Handler() {
            public void handleMessage(Message msg) {
                if (msg.what == 0x123) {
                    myView.invalidate();
                }
            }
        };
        final Timer timer = new Timer();
        timer.schedule(new TimerTask() {
            @Override
            public void run() {
                red = (red + 3) % 256;
                green = (green + 2) % 256;
                blue = (blue + 1) % 256;
                angle = (angle + 6) % 360;
                ballX = (int) (nX + len * Math.cos(angle *1.0 / 180 * Math.PI));
                ballY = (int) (nY + len * Math.sin(angle * 1.0 / 180 * Math.PI));
                //System.out.println(ballX + "   " + ballY + len);
                // 发送消息，通知系统重绘组件
                handler.sendEmptyMessage(0x123);
            }
        },0,100);

    }


    class MyView extends View {
        Paint paint = new Paint();

        public MyView(Context context) {
            super(context);
            setFocusable(true);
        }

        @Override
        protected void onDraw(Canvas canvas) {
            paint.setStyle(Paint.Style.FILL);
            // 设置去锯齿
            paint.setAntiAlias(true);
            paint.setColor(Color.rgb(red, green, blue));
            canvas.drawCircle(ballX, ballY, BALL_SIZE, paint);
            Paint newPaint = new Paint();
            newPaint.setColor(Color.rgb(0,255,0));
            canvas.drawCircle(500, 500,10 , newPaint);
            canvas.drawLine(nX, nY, ballX, ballY, newPaint);
        }
    }
}
