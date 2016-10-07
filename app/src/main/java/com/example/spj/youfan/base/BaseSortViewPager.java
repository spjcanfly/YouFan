package com.example.spj.youfan.base;

import android.content.Context;
import android.support.v4.view.ViewPager;
import android.view.View;

/**
 * Created by spj on 2016/10/7.
 */
public abstract class BaseSortViewPager extends ViewPager{
    public final Context mContext;
    public View rootView;

    public BaseSortViewPager(Context context) {
        super(context);
        this.mContext = context;
        rootView = initView();
    }

    //子类必须要重写这个方法
    public abstract View initView();

    public void initData() {

    }
}
