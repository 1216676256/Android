package com.example.homework3;

import android.app.TabActivity;
import android.content.Intent;
import android.content.res.Resources;
import android.os.Bundle;
import android.widget.TabHost;
import android.widget.Toast;
/**
 * @date:2019/10/20
 * @author:zhongcz
 */
public class SecondActivity extends TabActivity {
    public static SecondActivity secondActivity;
    public static TabHost mTabHost;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_second);

        Resources res=getResources();
        secondActivity=this;
        mTabHost=this.getTabHost();

        Intent intent=new Intent();
        intent.setClass(this, TabOneActivity.class);
        TabHost.TabSpec tabSpec=mTabHost.newTabSpec("First").setIndicator("First",res.getDrawable(R.drawable.image1)).setContent(intent);
        mTabHost.addTab(tabSpec);

        intent=new Intent();
        intent.setClass(this,SecondActivity.class);
        intent.putExtra("name","value");
        tabSpec=mTabHost.newTabSpec("Second").setIndicator("Second",res.getDrawable(R.drawable.image1)).setContent(intent);
        mTabHost.addTab(tabSpec);



        intent = new Intent();
        intent.setClass(this, TabThreeActivity.class);
        tabSpec = SecondActivity.mTabHost.newTabSpec("third").
                setIndicator("third", res.getDrawable(android.R.drawable.ic_media_next)).setContent(intent);
        SecondActivity.mTabHost.addTab(tabSpec);
        mTabHost.setCurrentTabByTag("firstActivity");

    }
}
