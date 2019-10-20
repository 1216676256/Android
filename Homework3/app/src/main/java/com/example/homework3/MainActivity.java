package com.example.homework3;

import android.app.AlertDialog;
import android.app.Dialog;
import android.app.ExpandableListActivity;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.SimpleExpandableListAdapter;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;


public class MainActivity extends ExpandableListActivity {
    private Button button = null;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        button = (Button)findViewById(R.id.btn_turn);

        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                AlertDialog.Builder build = new AlertDialog.Builder(MainActivity.this);
                build.setTitle("提示！");
                build.setMessage("即将进入第二个页面!");
                build.setPositiveButton("确定", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        Intent intent = new Intent(MainActivity.this,SecondActivity.class);
                        startActivity(intent);
                    }
                });

                build.setNegativeButton("取消", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {

                    }
                });
                build.show();



            }
        });

        //  定义一个List，该List对象为一级条目提供数据
        List<Map<String, String>> groups = new ArrayList<>();
        Map<String, String> group1 = new HashMap<>();
        group1.put("group", "河南");
        Map<String, String> group2 = new HashMap<>();
        group2.put("group", "山东");
        groups.add(group1);
        groups.add(group2);

        //  定义一个List，该List对象为第一个一级条目提供二级条目数据
        List<Map<String, String>> child1 = new ArrayList<>();
        Map<String, String> child1Data1 = new HashMap<>();
        child1Data1.put("child", "郑州");
        Map<String, String> child1Data2 = new HashMap<>();
        child1Data2.put("child", "洛阳");
        Map<String, String> child1Data3 = new HashMap<>();
        child1Data3.put("child", "南阳");
        child1.add(child1Data1);
        child1.add(child1Data2);
        child1.add(child1Data3);

        //  定义一个List，该List对象为第二个一级条目提供二级条目数据
        List<Map<String, String>> child2 = new ArrayList<>();
        Map<String, String> child2Data = new HashMap<>();
        child2Data.put("child", "济南");
        child2.add(child2Data);

        //  定义一个List，该List对象用来存储所有的二级条目数据
        List<List<Map<String, String>>> childs = new ArrayList<>();
        childs.add(child1);
        childs.add(child2);

        //  生成一个SimpleExpandableListAdapter对象
        //  参数列表：
        //  1.上下文对象
        //  2.一级条目的数据
        //  3.用来设置一级条目样式的布局文件
        //  4.指定一级条目数据的key
        //  5.指定一级条目数据显示控件的id
        //  6.二级条目的数据
        //  7.用来设置二级条目样式的布局文件
        //  8.指定二级条目数据的key
        //  9.指定二级条目数据显示控件的id
        SimpleExpandableListAdapter adapter = new SimpleExpandableListAdapter(
                this, groups, R.layout.group,
                new String[]{"group"}, new int[]{R.id.groupTo},
                childs, R.layout.child,
                new String[]{"child"}, new int[]{R.id.childTo});

        //  将adapter设置给当前的ExpandableListActivity
        setListAdapter(adapter);
    }
}