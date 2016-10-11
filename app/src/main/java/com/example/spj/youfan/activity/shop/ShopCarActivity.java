package com.example.spj.youfan.activity.shop;

import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.v4.app.FragmentActivity;
import android.support.v4.app.FragmentTransaction;
import android.view.View;

import com.example.spj.youfan.R;
import com.example.spj.youfan.base.BaseShopFragment;
import com.example.spj.youfan.fragment.FragmentBottom;
import com.example.spj.youfan.fragment.FragmentTop;
import com.example.spj.youfan.utils.Constants;
import com.example.spj.youfan.utils.LogUtil;
import com.lzy.widget.VerticalSlide;

public class ShopCarActivity extends FragmentActivity implements View.OnClickListener {

    private VerticalSlide verticalSlide;
    private FloatingActionButton fab;
    private BaseShopFragment topFragment;
    private BaseShopFragment bottomFragment;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_shop_car);
        //接收传递过来的数据
        String tid = getIntent().getStringExtra("tid");
        String url = Constants.GOODS_HEADER + tid + Constants.GOODS_TAIL;
        LogUtil.e(url+"22222");
        //找控件
        initView();

        //请求网络
        getDataFromNet();

        //初始化Fragment
        initFragment();

    }

    private void getDataFromNet() {

    }

    private void initFragment() {

        FragmentTransaction transaction = getSupportFragmentManager().beginTransaction();
        topFragment = new FragmentTop();
        transaction.replace(R.id.first, topFragment);

        bottomFragment = new FragmentBottom();
        transaction.replace(R.id.second,bottomFragment);
        transaction.commit();

    }

    private void initView() {
        verticalSlide = (VerticalSlide) findViewById(R.id.dragLayout);
        fab = (FloatingActionButton) findViewById(R.id.fab);
        fab.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        /**
         * 返回顶部分三步
         * 1.第二页滚动到第二页的顶部
         * 2.VerticalSlide从第二页返回第一页
         * 3.第一页滚动到第一页的顶部
         * OnGoTopListener 表示第一页滚动到顶部 的方法,这个由于采用什么布局,库内部并不知道,所以一般是自己实现
         * 也可以不实现,直接传null
         */
        bottomFragment.goTop();
        verticalSlide.goTop(new VerticalSlide.OnGoTopListener() {
            @Override
            public void goTop() {
                topFragment.goTop();
            }
        });
    }
}
