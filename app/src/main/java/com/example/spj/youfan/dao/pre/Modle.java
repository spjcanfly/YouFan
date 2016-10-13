package com.example.spj.youfan.dao.pre;

import android.content.Context;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/**
 * Created by spj on 2016/8/2.
 */
//模型层
public class Modle {
    private Context mContext;
    private DBManager mDbManager;
    //私有化构造
    private Modle() {

    }

    private static Modle instance = new Modle();

    public static Modle getInstance() {
        return instance;
    }

    public void init(Context context){
        mContext = context;
        mDbManager = new DBManager(mContext,"shopping_cart.db3");
    }

    private ExecutorService executorService = Executors.newCachedThreadPool();

    public ExecutorService getGlobalThreadPool() {
        return executorService;
    }

//    public void loginSuccess(UserInfo userInfo){
//
//        if(userInfo == null) {
//            Log.e("TAG", "进入userInfo");
//            return;
//        }
//        if(mDbManager != null) {
//            Log.e("TAG", "进入mdb");
//            mDbManager.close();
//        }
//        Log.e("TAG", "创建对象");
//        //获取数据库的管理者
//        mDbManager = new DBManager(mContext,userInfo.getName());
//
//    }
    //获取数据库的管理者
    public DBManager getDbManager(){

        return mDbManager;
    }

}
