package com.zju.homework5;

import androidx.appcompat.app.AppCompatActivity;
import androidx.vectordrawable.graphics.drawable.ArgbEvaluator;

import android.animation.AnimatorInflater;
import android.animation.ObjectAnimator;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.LinearLayout;

public class MainActivity extends AppCompatActivity {
    private Button button;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        button = findViewById(R.id.btn_turn1);
        LinearLayout container = findViewById(R.id.container);
        container.addView(new MyAnimationView(this));
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity.this, SecondActivity.class);
                startActivity(intent);
            }
        });
    }
    public class MyAnimationView extends View {
        public MyAnimationView(Context context){
            super(context);
            ObjectAnimator objectAnimator = (ObjectAnimator) AnimatorInflater
                    .loadAnimator(MainActivity.this,R.animator.color_anim);
            objectAnimator.setEvaluator(new ArgbEvaluator());
            objectAnimator.setTarget(this);
            objectAnimator.start();
        }
    }
}
