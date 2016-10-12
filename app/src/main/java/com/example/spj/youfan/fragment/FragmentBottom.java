package com.example.spj.youfan.fragment;

import android.annotation.SuppressLint;
import android.content.Context;
import android.os.Bundle;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.spj.youfan.R;
import com.example.spj.youfan.base.BaseShopFragment;
import com.example.spj.youfan.bean.SingleShopDetail;
import com.example.spj.youfan.fragment.shopfragment.FragmentSay;
import com.example.spj.youfan.fragment.shopfragment.FragmentShopAsk;
import com.example.spj.youfan.fragment.shopfragment.FragmentSingleShop;
import com.example.spj.youfan.uiself.NoScrollViewPager;

import java.util.ArrayList;
import java.util.LinkedHashMap;

/**
 * Created by spj on 2016/10/10.
 */
@SuppressLint("ValidFragment")
public class FragmentBottom extends BaseShopFragment{


    private final Context mContext;
    private final SingleShopDetail.DataBean mData;
    private LinkedHashMap<String, Fragment> fragments;


    public FragmentBottom(Context context, SingleShopDetail.DataBean data) {
        this.mContext = context;
        this.mData = data;
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.fragment_viewpager, container, false);
        NoScrollViewPager viewPager = (NoScrollViewPager) rootView.findViewById(R.id.viewPager);
        TabLayout tab = (TabLayout) rootView.findViewById(R.id.tab);
        viewPager.setAdapter(new MyPagerAdapter(getFragmentManager()));
        tab.setupWithViewPager(viewPager);
        return rootView;
    }

    @Override
    public void goTop() {
    }

    private class MyPagerAdapter extends FragmentPagerAdapter {
        private ArrayList<String> titles;

        @Override
        public CharSequence getPageTitle(int position) {
            return titles.get(position);
        }

        public MyPagerAdapter(FragmentManager fm) {
            super(fm);
            fragments = new LinkedHashMap<>();
            fragments.put("单品详情", new FragmentSingleShop());
            fragments.put("全部评价(14)", new FragmentSay());
            fragments.put("购买咨询", new FragmentShopAsk());

            titles = new ArrayList<>();
            titles.addAll(fragments.keySet());
        }

        @Override
        public Fragment getItem(int position) {
            return fragments.get(titles.get(position));
        }

        @Override
        public int getCount() {
            return fragments.size();
        }
    }
}
