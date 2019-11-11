package com.zju.homework6;

import androidx.appcompat.app.AppCompatActivity;

import android.content.ContentValues;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import com.zju.homework6.utils.MyDatabaseHelper;

public class MainActivity extends AppCompatActivity {
    private MyDatabaseHelper dbHelper;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        dbHelper = new MyDatabaseHelper(this, "BookStore.db", null, 2);
        Button createDatabase = findViewById(R.id.create_database);
        createDatabase.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                dbHelper.getWritableDatabase();
            }
        });


        Button addData = findViewById(R.id.add_data);
        addData.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                SQLiteDatabase db = dbHelper.getWritableDatabase();
                ContentValues values = new ContentValues();
                values.put("name", "The Da Vinci Code");
                values.put("author", "Zhongchangze");
                values.put("pages", 454);
                values.put("price", "16.96");
                db.insert("Book", null, values);
                values.clear();

                //第二条数据
                values.put("name", "The Lost Symbol");
                values.put("author","Zhongchangze");
                values.put("pages",510);
                values.put("price", 19.95);
                db.insert("Book", null, values);

                Toast.makeText(MainActivity.this,"添加数据成功",Toast.LENGTH_SHORT).show();
            }
        });

        Button updateDate = findViewById(R.id.update_data);
        updateDate.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v) {
                SQLiteDatabase db = dbHelper.getWritableDatabase();
                ContentValues values = new ContentValues();
                values.put("price","10.99");
                db.update("Book",values,"name = ? ",new String[]{"The Da Vinci Code"});
            }
        });

        Button queryData = findViewById(R.id.query_data);
        queryData.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v) {
                SQLiteDatabase db = dbHelper.getWritableDatabase();
                //查询Book中的所有数据
                Cursor cursor = db.query("Book",null,null,null,null,
                        null,null);
                if(cursor.moveToFirst()){
                    do{
                        //遍历，并打印
                        String name = cursor.getString(cursor.getColumnIndex("name"));
                        String author = cursor.getString(cursor.getColumnIndex("author"));
                        int pages = cursor.getInt(cursor.getColumnIndex("pages"));
                        double price = cursor.getDouble(cursor.getColumnIndex("price"));
                        String res = "book name is" + name + "\n" +
                            "book author is " + author + "\n" +
                            "book pages is " + pages + "\n" +
                            "book price is" + price;
                        Toast.makeText(MainActivity.this,res,Toast.LENGTH_SHORT).show();
                    }while(cursor.moveToNext());
                }
                cursor.close();
            }
        });
    }
}
