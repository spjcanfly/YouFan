package com.example.spj.youfan.uiself;

import android.content.Context;
import android.support.v4.view.ViewPager;
import android.util.AttributeSet;
import android.view.MotionEvent;

/**
 * Created by spj on 2016/8/23.20:02
 */
public class NoScrollViewPager extends ViewPager {
    /**
     * 通常在代码中实例化的时候用该方法
     * @param context
     */
    public NoScrollViewPager(Context context) {
        super(context);
    }

    /**
     * 在布局文件中使用该类的时候，实例化该类用该构造方法，这个方法不能少，少的化会崩溃。
     * @param context
     * @param attrs
     */
    public NoScrollViewPager(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    /**
     * 重写触摸事件，消费掉
     * @param ev
     * @return
     */
    @Override
    public boolean onTouchEvent(MotionEvent ev) {
        return true;
    }

    /**
     * 自己不处理，交给子类
     * @param ev
     * @return
     */
    @Override
    public boolean onInterceptTouchEvent(MotionEvent ev) {
        return false;
    }
}
