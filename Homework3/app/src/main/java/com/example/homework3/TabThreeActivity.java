package com.example.homework3;

import android.app.Activity;
import android.os.Bundle;
import android.view.KeyEvent;
import android.view.View;
import android.widget.TextView;

/**
 * @date:2019/10/20
 * @author:zhongcz
 */
public class TabThreeActivity extends Activity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        TextView textView = new TextView(this);
        textView.setText("This is the ThirdActivity!");
        setContentView(textView);
        textView.setTextSize(25);
        textView.setPadding(150, 150, 150, 150);
        textView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                SecondActivity.mTabHost.setCurrentTabByTag("firstActivity");
            }
        });
    }

    @Override
    public boolean onKeyDown(int keyCode, KeyEvent event) {
        if (keyCode == KeyEvent.KEYCODE_BACK && event.getRepeatCount() == 0) {
            SecondActivity.mTabHost.setCurrentTabByTag("secondActivity");
            return true;
        }
        return super.onKeyDown(keyCode, event);
    }
}