package com.example.spj.youfan.activity;

import android.os.Bundle;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.util.DisplayMetrics;
import android.widget.FrameLayout;

import com.example.spj.youfan.R;
import com.example.spj.youfan.fragment.ContentFragment;
import com.example.spj.youfan.fragment.LeftmenuFragment;
import com.example.spj.youfan.utils.LogUtil;
import com.jeremyfeinstein.slidingmenu.lib.SlidingMenu;
import com.jeremyfeinstein.slidingmenu.lib.app.SlidingFragmentActivity;

import butterknife.Bind;
import butterknife.ButterKnife;

public class MainActivity extends SlidingFragmentActivity {

    public static final String MAIN_CONTENT_TAG = "main_content_tag";
    public static final String LEFTMENU_TAG = "leftmenu_tag";
    private FragmentManager fm;
    private int screenWidth;
    private int screenHeight;

    @Bind(R.id.fl_main_content)
    FrameLayout flMainContent;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ButterKnife.bind(this);
        //初始化页面
        initView();
        //初始化Fragment
        initFragment();
    }

    private void initFragment() {
        //得到Fragment管理者

        fm = getSupportFragmentManager();
        //开启事务
        FragmentTransaction ft = fm.beginTransaction();
        //替换
        ft.replace(R.id.fl_main_content, new ContentFragment(), MAIN_CONTENT_TAG);//主页
        ft.replace(R.id.fl_leftmenu, new LeftmenuFragment(), LEFTMENU_TAG);//左侧菜单
        //提交
        ft.commit();
    }

    private void initView() {
        //设置左侧的滑动菜单
        setBehindContentView(R.layout.activity_leftmenu);
        //获得菜单对象
        SlidingMenu slidingMenu = getSlidingMenu();
        //设置显示的模式
        slidingMenu.setMode(SlidingMenu.LEFT);
        //设置滑动的模式,全屏滑动：点击各个地方都可以滑动
        slidingMenu.setTouchModeAbove(SlidingMenu.TOUCHMODE_FULLSCREEN);
        //设置主页占据的宽度，用百分比设置的方法设置长度
        DisplayMetrics outmetrics = new DisplayMetrics();
        getWindowManager().getDefaultDisplay().getMetrics(outmetrics);
        screenWidth = outmetrics.widthPixels;
        screenHeight = outmetrics.heightPixels;
        LogUtil.e("screenWidth=======" + screenWidth);
//        slidingMenu.setBehindOffset(Util.dip2px(MainActivity.this, 250));
        //  250/screenWidth 解决屏幕适配
        slidingMenu.setBehindOffset((int) (screenWidth * 0.35));
    }

    //得到左侧菜单Fragment
    public LeftmenuFragment getLeftmenuFragment() {
        LeftmenuFragment leftmenuFragment = (LeftmenuFragment) fm.findFragmentByTag(LEFTMENU_TAG);
        return leftmenuFragment;
    }

    //得到正文Fragment
    public ContentFragment getContentFragment(){

        return (ContentFragment) getSupportFragmentManager().findFragmentByTag(MAIN_CONTENT_TAG);
    }

}
