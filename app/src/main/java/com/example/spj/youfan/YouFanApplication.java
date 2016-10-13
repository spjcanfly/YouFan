package com.example.spj.youfan;

import android.app.Application;
import android.content.Context;
import android.os.Handler;

import com.example.spj.youfan.dao.pre.Modle;

/**
 * Created by spj on 2016/9/27.
 * 应用启动时调用该类
 */
public class YouFanApplication extends Application {
    //提供用到的属性
    public static Context mContext;//在整个应用中用到context的位置，都使用此变量
    public static Handler mHandler;//在整个应用中发送消息
    public static Thread currentThread;//获取当前应用的线程，主线程
    public static int currentThreadId;//获取当前线程的id

    @Override
    public void onCreate() {
        super.onCreate();
        //属性的初始化
        mContext = this.getApplicationContext();
        mHandler = new Handler();
        currentThread = Thread.currentThread();
        currentThreadId = android.os.Process.myTid();

        Modle.getInstance().init(mContext);
    }


}
