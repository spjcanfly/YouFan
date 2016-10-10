package com.example.spj.youfan.fragment;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.spj.youfan.R;
import com.example.spj.youfan.base.BaseShopFragment;

import java.util.ArrayList;
import java.util.LinkedHashMap;

/**
 * Created by spj on 2016/10/10.
 */
public class FragmentBottom extends BaseShopFragment{

    private LinkedHashMap<String, Fragment> fragments;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.fragment_viewpager, container, false);
//        ViewPager viewPager = (ViewPager) rootView.findViewById(R.id.viewPager);
//        TabLayout tab = (TabLayout) rootView.findViewById(R.id.tab);
//        viewPager.setAdapter(new MyPagerAdapter(getFragmentManager()));
//        tab.setupWithViewPager(viewPager);
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
//            fragments = new LinkedHashMap<>();
//            fragments.put("ListView", new Fragment_ListView());
//            fragments.put("GridView", new Fragment_GridView());
//            fragments.put("WebView", new Fragment_WebView());
//            fragments.put("View", new Fragment_View());
//
//            titles = new ArrayList<>();
//            titles.addAll(fragments.keySet());
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
