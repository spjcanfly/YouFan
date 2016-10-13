package com.example.spj.youfan.dao.pre;

import android.content.Context;

import com.example.spj.youfan.dao.now.ShopCartDao;

/**
 * Created by spj on 2016/8/5.
 */
public class DBManager {

    private final DBHelper mDbhelper;
    private final ShopCartDao shopCartDao;

    public DBManager(Context context,String name) {
         mDbhelper = new DBHelper(context,name);
        shopCartDao = new ShopCartDao(mDbhelper);
    }

    // 获取联系人操作类
    public ShopCartDao getContactDao(){
       return  shopCartDao;
    }

    public void close(){
        mDbhelper.close();
    }

}
