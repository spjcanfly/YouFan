package com.example.spj.youfan.base;

import android.content.Context;
import android.support.design.widget.TabLayout;
import android.view.View;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.spj.youfan.R;

/**
 * Created by spj on 2016/8/14.
 */
public abstract class BasePager {

    public Context mContext;//MainActivity
    public View rootView;

    public ImageView ibTopMenu;
    public ImageView ibTopSet;
    public ImageView ivTopSeacher;
    public ImageView ivTopMail;
    public ImageView ivTopBack;
    public FrameLayout flContent;
    public TextView tvTop;
    public TabLayout tablayout;
    public TextView tv_popwindow;
    public View rlTitleBar;
    public View ll_common_title;
    public TextView tvEditAll;

    public BasePager(Context context) {
        this.mContext = context;
        //构造执行，视图初始化
        rootView = initView();
    }

    private View initView() {
        //查找控件
        View view = findView();

        //处理titlebar的控件显示与隐藏
        initTitle();

        return view;
    }

    private View findView() {
        View view = View.inflate(mContext, R.layout.basepager, null);
        ibTopMenu = (ImageView) view.findViewById(R.id.ib_top_menu);
        ibTopSet = (ImageView) view.findViewById(R.id.ib_top_set);
        ivTopMail = (ImageView) view.findViewById(R.id.iv_top_mail);
        ivTopSeacher = (ImageView) view.findViewById(R.id.iv_top_seacher);
        ivTopBack = (ImageView) view.findViewById(R.id.iv_top_back);
        tvTop = (TextView) view.findViewById(R.id.tv_top);
        tablayout = (TabLayout) view.findViewById(R.id.tablayout);
        tv_popwindow = (TextView) view.findViewById(R.id.tv_popwindow);
        rlTitleBar = (View) view.findViewById(R.id.rlTitleBar);
        ll_common_title = (View) view.findViewById(R.id.ll_common_title);
        tvEditAll = (TextView) view.findViewById(R.id.tvEditAll);


        flContent = (FrameLayout) view.findViewById(R.id.fl_content);
        return view;
    }

    /**
     * 初始化数据;当孩子需要初始化数据;或者绑定数据;联网请求数据并且绑定的时候，重写该方法
     */
    public void initData() {
        //初始化监听
        initListener();
    }

    public abstract void initTitle();

    public abstract void initListener();

}
