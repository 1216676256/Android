package com.example.homework2;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Handler;
import android.os.Message;
import android.os.Bundle;
import android.view.View;
import android.widget.ProgressBar;
import android.widget.TextView;

import java.util.Timer;
import java.util.TimerTask;


import android.os.Bundle;
import android.widget.Toast;


/**
 * https://blog.csdn.net/hfut_why/article/details/90381882
 */

public class SecondActivity extends AppCompatActivity implements View.OnClickListener {

    int progress = 0;
    int cacheProgress = 0;
    ProgressBar defaultBar1;
    ProgressBar horizonBar1;
    ProgressBar horizonBar2;
    TextView progressText;
    @SuppressLint("HandlerLeak")
    Handler handler = new Handler() {
        @Override
        public void handleMessage(Message msg) {
            switch (msg.what) {
                case 0:
                    horizonBar1.setProgress(progress);
                    if (progress == 100) {
                        progressText.setText("下载完成");
                    } else {
                        progressText.setText("下载进度 " + progress + "%");
                    }
                    break;
                case 1:
                    horizonBar2.setProgress(progress);

                    if (progress == 100) {
                        progressText.setText("播放完毕");
                    } else {
                        if (cacheProgress <= 100) {
                            progressText.setText("播放完成：" + progress + "%" + "，缓存完成" + cacheProgress + "%");
                            horizonBar2.setSecondaryProgress(cacheProgress);
                        } else {
                            progressText.setText("播放完成：" + progress + "%" + "，缓存结束");
                        }
                    }
                    break;
            }
        }
    };


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_second);
        findView();
    }

    private void findView() {
        defaultBar1 = findViewById(R.id.normal_progress_bar_default);
        horizonBar1 = findViewById(R.id.normal_progress_bar_horizon);
        horizonBar2 = findViewById(R.id.normal_progress_bar_horizon2);

        progressText = findViewById(R.id.progress_text);
        findViewById(R.id.normal_default_bar).setOnClickListener(this);
        findViewById(R.id.normal_horizon_bar1).setOnClickListener(this);
        findViewById(R.id.normal_horizon_bar2).setOnClickListener(this);
        findViewById(R.id.btn_turn2).setOnClickListener(this);
    }


    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.btn_turn2:

                AlertDialog.Builder builder = new AlertDialog.Builder(SecondActivity.this);
                builder.setTitle("提示！");
                builder.setMessage("将要进入第三个页面");
                builder.setPositiveButton("确定", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        Intent intent = new Intent(SecondActivity.this, ThirdActivity.class);
                        startActivity(intent);
                    }
                });
                builder.setNegativeButton("取消", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        // Toast.makeText(SecondActivity.this,"嘤嘤嘤", Toast.LENGTH_SHORT).show();
                    }
                });
                builder.show();
                /*Intent intent = new Intent(SecondActivity.this, ThirdActivity.class);
                startActivity(intent);*/
                break;
            case R.id.normal_default_bar:
                progressText.setVisibility(View.INVISIBLE);
                horizonBar1.setVisibility(View.INVISIBLE);
                horizonBar2.setVisibility(View.INVISIBLE);
                defaultBar1.setVisibility(View.VISIBLE);
                break;
            case R.id.normal_horizon_bar1:
                progress = 0;
                progressText.setVisibility(View.VISIBLE);
                defaultBar1.setVisibility(View.INVISIBLE);
                horizonBar2.setVisibility(View.INVISIBLE);
                horizonBar1.setVisibility(View.VISIBLE);
                final Timer timer = new Timer();
                timer.schedule(new TimerTask() {
                    @Override
                    public void run() {
                        progress += 2;
                        if (progress <= 100) {
                            Message message = Message.obtain();
                            message.what = 0;
                            handler.sendMessage(message);
                        } else {
                            timer.cancel();
                        }
                    }
                }, 0, 200);
                break;
            case R.id.normal_horizon_bar2:
                progressText.setVisibility(View.VISIBLE);
                defaultBar1.setVisibility(View.INVISIBLE);
                horizonBar1.setVisibility(View.INVISIBLE);
                horizonBar2.setVisibility(View.VISIBLE);
                cacheProgress = 0;
                progress = 0;
                final Timer timer1 = new Timer();
                timer1.schedule(new TimerTask() {
                    @Override
                    public void run() {
                        progress += 1;
                        cacheProgress += 2;
                        if (progress <= 100) {
                            Message message = Message.obtain();
                            message.what = 1;
                            handler.sendMessage(message);
                        } else {
                            timer1.cancel();
                        }
                    }
                }, 0, 400);
                break;


        }
    }
}