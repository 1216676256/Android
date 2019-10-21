package com.example.homework3;

import android.app.Activity;
import android.os.Bundle;
import android.widget.TextView;

/**
 * @date:2019/10/20
 * @author:zhongcz
 */
public class TabOneActivity extends Activity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        TextView textView=new TextView(this);
        textView.setPadding(150,150,150,150);
        textView.setText("This is the FirstActivity");
        setContentView(textView);
    }
}
