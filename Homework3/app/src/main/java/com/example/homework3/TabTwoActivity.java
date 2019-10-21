package com.example.homework3;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.widget.TextView;

/**
 * @date:2019/10/20
 * @author:zhongcz
 */
public class TabTwoActivity extends Activity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        TextView textView=new TextView(this);
        textView.setText("This is the SecondActivity");
        textView.setPadding(150,150,150,150);
        setContentView(textView);
        Intent intent=getIntent();
        String str=intent.getStringExtra("name");
        textView.setText(textView.getText()+"\n\n"+str);
    }
}
