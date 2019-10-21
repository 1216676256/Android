package com.example.homework3;

import androidx.annotation.NonNull;
import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.view.GravityCompat;
import androidx.drawerlayout.widget.DrawerLayout;

import android.content.Intent;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import com.google.android.material.navigation.NavigationView;

public class ThirdActiviry extends AppCompatActivity {
    private DrawerLayout mDrawLayout;
    private NavigationView navigationView;
    private Button btn_turn;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_third);
        mDrawLayout = findViewById(R.id.drawer_layout);
        btn_turn = findViewById(R.id.btn_turn3);
        navigationView = findViewById(R.id.nav_view);
        ActionBar actionBar = getSupportActionBar();
        if (actionBar != null) {
            actionBar.setDisplayHomeAsUpEnabled(true);
            actionBar.setHomeAsUpIndicator(R.drawable.nav_menu);
        }
        btn_turn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(ThirdActiviry.this,SecondActivity.class);
                startActivity(intent);
            }
        });

        navigationView.setCheckedItem(R.id.nav_call);
        navigationView.setNavigationItemSelectedListener(new NavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem menuItem) {
                mDrawLayout.closeDrawers();
                return true;
            }
        });
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case android.R.id.home:
                mDrawLayout.openDrawer(GravityCompat.START);
                Toast.makeText(this,"滑动分页", Toast.LENGTH_SHORT).show();
                break;
            /*case R.id.backup:
                Toast.makeText(this, "You  clicked  Backup", Toast.LENGTH_SHORT).show();
                Intent intent2=new Intent(MainActivity.this,Deliver_lecture.class);
                startActivity(intent2);
                break;
            case R.id.delete:
                Toast.makeText(this, "You  clicked  Delete", Toast.LENGTH_SHORT).show();
                Intent intent1=new Intent(MainActivity.this,Add_relation.class);
                startActivity(intent1);
                break;
            case R.id.settings:
                Toast.makeText(this, "You  clicked  Settings", Toast.LENGTH_SHORT).show();
                backgroundAlpha(0.5f);
                showPopwindows_1();+
                break;*/
            default:
        }
        return true;
    }
}
