package com.zju.homework6_outer;

import androidx.appcompat.app.AppCompatActivity;

import android.content.ContentResolver;
import android.content.ContentValues;
import android.database.Cursor;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {
    private ContentResolver resolver;
    private Uri uri;
    private ContentValues values;
    private Cursor cursor;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        resolver = this.getContentResolver();
        Button addData = findViewById(R.id.add_data);
        addData.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v) {
                uri = Uri.parse("content://com.zju.homework6/Book");
                values = new ContentValues();
                values.put("name", "通过接口外部调用在Book表插入数据");
                values.put("author", "Zhongcz");
                values.put("pages", 54);
                values.put("price", 12306);
                resolver.insert(uri, values);


                uri = Uri.parse("content://com.zju.homework6/Category");
                values = new ContentValues();
                values.put("name", "通过外部接口在Category表插入数据");
                values.put("author", "Zhongcz");
                values.put("pages", 111);
                values.put("price", "1024");
                resolver.insert(uri, values);
            }
        });


        Button queryData = findViewById(R.id.query_data);
        queryData.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v) {
                //本句话执行时会进入到提供者的query方法中，
                //参数2：为查询时可以显示的内容，如果参数设置为自由amount，当执行cursor.getString()方法是，其中的参数不能为1，为1时不能打印内容-
                //参数3：为选择语句
                //参数4：为问号位置的内容--筛选条件
                uri = Uri.parse("content://com.zju.homework6/Book");
                cursor = resolver.query(uri, new String[]{"name","author","pages", "price"}, "author=?", new String[]{"Zhongchangze"}, null);
                System.out.println("执行了query语句");
                if (cursor != null) {
                    while (cursor.moveToNext()) {
                        String name = cursor.getString(0);
                        String author = cursor.getString(1);
                        int pages = cursor.getInt(2);
                        String price = cursor.getString(3);
                        String ans = "名字是 " + name + "\n "
                                + "作者是 " + author + "\n"
                                + "页数是 " + pages + "\n"
                                + "价格是 " + price;
                        Toast.makeText(MainActivity.this, ans, Toast.LENGTH_SHORT).show();
                    }
                }
            }
        });


        Button updataData = findViewById(R.id.update_data);
        updataData.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v) {
                //修改内容
                uri = Uri.parse("content://com.zju.homework6/Book");
                values = new ContentValues();
                values.put("name","我名字已经被改过啦");
                resolver.update(uri,values,"name=?",new String[]{"The Da Vinci Code"});
            }
        });
    }

}
