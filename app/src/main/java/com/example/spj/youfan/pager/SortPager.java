package com.example.spj.youfan.pager;

import android.content.Context;
import android.support.design.widget.TabLayout;
import android.support.v4.view.PagerAdapter;
import android.support.v4.view.ViewPager;
import android.view.View;
import android.view.ViewGroup;

import com.example.spj.youfan.R;
import com.example.spj.youfan.activity.MainActivity;
import com.example.spj.youfan.base.BasePager;
import com.example.spj.youfan.base.BaseSortViewPager;
import com.example.spj.youfan.pager.detailpager.SortDongTaiDetailPager;
import com.example.spj.youfan.pager.detailpager.SortPinLeiDetailPager;
import com.example.spj.youfan.pager.detailpager.SortPinPaiDetailPager;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by spj on 2016/9/28.
 */
public class SortPager extends BasePager {

    private ViewPager sort_viewpager;
    private List<BaseSortViewPager> sortDetailPagers;
    private String[] attrs;

    public SortPager(Context context) {
        super(context);
    }

    @Override
    public void initData() {
        //移除之前的内容才行，一个坑啊
        flContent.removeAllViews();
        View view = View.inflate(mContext, R.layout.sort_pager, null);
        sort_viewpager = (ViewPager) view.findViewById(R.id.sort_viewpager);
        //把子视图添加到BasePager上的Fragment上
        flContent.addView(view);

        //往Tablayout上准备装配的数据
        attrs = new String[]{"品类", "品牌", "动态"};

        //准备分类详情页面的数据
        sortDetailPagers = new ArrayList<>();
        sortDetailPagers.add(new SortPinLeiDetailPager(mContext));
        sortDetailPagers.add(new SortPinPaiDetailPager(mContext));
        sortDetailPagers.add(new SortDongTaiDetailPager(mContext));

        show();
        super.initData();
    }


    private void show() {

        //设置ViewPager的适配器
        sort_viewpager.setAdapter(new MySortPagerAdapter());

        //ViewPager 和TabPageIndicator关联
        tablayout.setupWithViewPager(sort_viewpager);

        //监听页面的变化
        sort_viewpager.addOnPageChangeListener(new MyOnpagerChangeListener());

        //设置滑动(如果没有这个，上面的指针会跟着页面移动(MODE_SCROLLABLE,这个是会跟着移动))
        tablayout.setTabMode(TabLayout.MODE_FIXED);
    }

    @Override
    public void initTitle() {
        ibTopMenu.setVisibility(View.VISIBLE);
        ibTopSet.setVisibility(View.GONE);
        ivTopSeacher.setVisibility(View.VISIBLE);
        ivTopMail.setVisibility(View.GONE);
        ivTopBack.setVisibility(View.GONE);
        tvTop.setVisibility(View.GONE);
        tablayout.setVisibility(View.VISIBLE);
        tv_popwindow.setVisibility(View.GONE);
        rlTitleBar.setVisibility(View.GONE);
        ll_common_title.setVisibility(View.VISIBLE);
    }

    private class MySortPagerAdapter extends PagerAdapter {

        @Override
        public CharSequence getPageTitle(int position) {
            return attrs[position];
        }

        @Override
        public int getCount() {
            return attrs == null ? 0 : attrs.length;
        }

        @Override
        public boolean isViewFromObject(View view, Object object) {
            return view == object;
        }

        @Override
        public Object instantiateItem(ViewGroup container, int position) {
            BaseSortViewPager baseSortViewPager = sortDetailPagers.get(position);
            View rootView = baseSortViewPager.rootView;
            baseSortViewPager.initData();//初始化数据
            container.addView(rootView);
            return rootView;
        }

        @Override
        public void destroyItem(ViewGroup container, int position, Object object) {
            container.removeView((View) object);
        }
    }

    @Override
    public void initListener() {
        //切换侧滑
        ibTopMenu.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                MainActivity mainActivity = (MainActivity) mContext;
                //切换左侧的开和关
                mainActivity.getSlidingMenu().toggle();
            }
        });
    }

    private class MyOnpagerChangeListener implements ViewPager.OnPageChangeListener {
        @Override
        public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {

        }

        @Override
        public void onPageSelected(int position) {

        }

        @Override
        public void onPageScrollStateChanged(int state) {

        }
    }
}
