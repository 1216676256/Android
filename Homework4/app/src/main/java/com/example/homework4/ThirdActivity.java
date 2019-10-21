package com.example.homework4;


import android.app.Activity;
import android.app.Fragment;
import android.os.Bundle;
import android.view.Display;

public class ThirdActivity extends Activity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Display display = getWindowManager().getDefaultDisplay();
        if (display.getWidth() > display.getHeight()) {
            Fragment1 fragment1 = new Fragment1();
            getFragmentManager().beginTransaction().replace(R.id.zcz, fragment1).commit();
        } else {
            Fragment2 fragment2 = new Fragment2();
            getFragmentManager().beginTransaction().replace(R.id.zcz, fragment2).commit();
        }
    }

}