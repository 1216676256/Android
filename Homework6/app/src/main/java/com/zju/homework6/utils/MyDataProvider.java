package com.zju.homework6.utils;

import android.content.ContentProvider;
import android.content.ContentValues;
import android.content.UriMatcher;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.net.Uri;
import android.os.Bundle;
import android.os.CancellationSignal;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

/**
 * @date:2019/11/11
 * @author:zhongcz
 */
public class MyDataProvider extends ContentProvider {
    private SQLiteDatabase db;
    UriMatcher um = new UriMatcher(UriMatcher.NO_MATCH);

    {
        //9.将库中的表通过addURL方法加入筛选器中
        //参数1：主机名字-提供者的配置文件中，参数2：表名，参数3：表名的数字
        um.addURI("com.zju.hoomework6", "Book", 1);
        um.addURI("com.zju.homework6", "Category", 2);
    }

    @Override
    public boolean onCreate() {
        //这里的版本号可能要改一下
        MyDatabaseHelper myDatabaseHelper = new MyDatabaseHelper(getContext(), "BookStore.db.db", null, 1);
        //7.调用方法，确定以什么方式打开此数据库
        db = myDatabaseHelper.getWritableDatabase();
        return false;
    }

    @Nullable
    @Override
    public Cursor query(Uri uri, String[] projection, String selection, String[] selectionArgs, String sortOrder) {
        Cursor cursor = null;
        System.out.println("进入了提供者的查询方法");
        if (um.match(uri) == 1) {
            cursor = db.query("luobo", projection, selection, selectionArgs, sortOrder, null, null);
        } else if (um.match(uri) == 2) {
            System.out.println("进入了提供者的查询方法中的第二个判断");
            cursor = db.query("lizi", projection, selection, selectionArgs, sortOrder, null, null);
            System.out.println("执行了提供者的查询方法中的第二个判断");
        } else if (um.match(uri) == 3) {
            cursor = db.query("taozi", projection, selection, selectionArgs, sortOrder, null, null);
        }
        return cursor;
    }

    @Nullable
    @Override
    public String getType(Uri uri) {
        return null;
    }

    @Nullable
    @Override
    public Uri insert(Uri uri, ContentValues values) {
        //10.对传过来经过代码块判断完的链接生成的表名数字进行判断
        //地址过滤器的使用---筛选作用
        //um.match(uri)方法通过在代码块进行比较，返回一个int型数据，此数据就是链接匹配上后的数字
        if (um.match(uri) == 1) {
            //11.如果匹配成功后，进行的操作----此处就是在对应的表中插入数据，调用insert方法即可
            //此步骤完毕后，提供者功能完成，该是解析者的代码了
            db.insert("Book", null, values);
        } else if (um.match(uri) == 2) {
            db.insert("Category", null, values);
        }
        return null;
    }

    @Override
    public int delete(Uri uri, String selection, String[] selectionArgs) {
        System.out.println("进入了提供者的删除方法");
        switch (um.match(uri)) {
            case 1:
                db.delete("Book", selection, selectionArgs);
                break;
            case 2:
                db.delete("Category", selection, selectionArgs);
                break;
            /*case 3:
                System.out.println("进入了提供者的删除方法的case3");
                db.delete("taozi",selection,selectionArgs);
                System.out.println("执行了case3中的删除");
                break;*/
        }
        return 0;
    }

    @Override
    public int update(Uri uri, ContentValues values, String selection, String[] selectionArgs) {
        switch (um.match(uri)) {
            case 1:
                db.update("Book", values, selection, selectionArgs);
                break;
            case 2:
                db.update("Category", values, selection, selectionArgs);
                break;
            /*case 3:
                db.update("taozi",values,selection,selectionArgs);
                break;*/
        }
        return 0;
    }
}
