package com.example.spj.youfan.fragment;

import android.support.v4.view.ViewPager;
import android.view.View;
import android.widget.RadioGroup;

import com.example.spj.youfan.R;
import com.example.spj.youfan.activity.MainActivity;
import com.example.spj.youfan.adapter.ContentFragmentAdapter;
import com.example.spj.youfan.base.BaseFragment;
import com.example.spj.youfan.base.BasePager;
import com.example.spj.youfan.pager.HomePager;
import com.example.spj.youfan.pager.InspirationPager;
import com.example.spj.youfan.pager.MePager;
import com.example.spj.youfan.pager.ShopPager;
import com.example.spj.youfan.pager.SortPager;
import com.example.spj.youfan.uiself.NoScrollViewPager;
import com.jeremyfeinstein.slidingmenu.lib.SlidingMenu;

import java.util.ArrayList;
import java.util.List;

import butterknife.Bind;
import butterknife.ButterKnife;

/**
 * Created by spj on 2016/9/27.
 */
public class ContentFragment extends BaseFragment {

    private List<BasePager> basePagers;

    @Bind(R.id.viewPager)
    NoScrollViewPager viewPager;
    @Bind(R.id.rg_main)
    RadioGroup rgMain;

    @Override
    public View initView() {
        View view = View.inflate(mContext, R.layout.content_fragment, null);
        ButterKnife.bind(this, view);
        return view;
    }

    @Override
    public void initData() {
        super.initData();
        //初始化五个页面，并且放入集合中
        basePagers = new ArrayList<>();
        basePagers.add(new HomePager(mContext));
        basePagers.add(new SortPager(mContext));
        basePagers.add(new InspirationPager(mContext));
        basePagers.add(new ShopPager(mContext));
        basePagers.add(new MePager(mContext));
        //设置ViewAdapter的适配器
        viewPager.setAdapter(new ContentFragmentAdapter(basePagers));

        //设置RadioGRoup的选中状态改变的监听
        rgMain.setOnCheckedChangeListener(new MyOnCheckedChangeListener());

        //监听某个页面被选中，初始化对应的页面的数据
        viewPager.addOnPageChangeListener(new MyOnPageChangeListener());
        //默认选中第一个
        rgMain.check(R.id.rb_home);
        basePagers.get(0).initData();
        //设置模式不可以滑动
        isEnableSlidingMenu(SlidingMenu.TOUCHMODE_NONE);
    }

    //设置模式不可以滑动
    private void isEnableSlidingMenu(int touchMode) {
        MainActivity mainActivity = (MainActivity) mContext;
        mainActivity.getSlidingMenu().setTouchModeAbove(touchMode);
    }


    private class MyOnCheckedChangeListener implements RadioGroup.OnCheckedChangeListener {
        @Override
        public void onCheckedChanged(RadioGroup group, int checkedId) {
            switch (checkedId) {
                case R.id.rb_home :
                    //true表示切换有动画
                    viewPager.setCurrentItem(0,true);
                    isEnableSlidingMenu(SlidingMenu.TOUCHMODE_NONE);
                    break;
                case R.id.rb_sort :
                    //true表示切换有动画
                    viewPager.setCurrentItem(1,true);
                    isEnableSlidingMenu(SlidingMenu.TOUCHMODE_NONE);
                    break;
                case R.id.rb_inspiration :
                    //true表示切换有动画
                    viewPager.setCurrentItem(2,true);
                    isEnableSlidingMenu(SlidingMenu.TOUCHMODE_NONE);
                    break;
                case R.id.rb_shop :
                    //true表示切换有动画
                    viewPager.setCurrentItem(3,true);
                    isEnableSlidingMenu(SlidingMenu.TOUCHMODE_NONE);
                    break;
                case R.id.rb_my :
                    //true表示切换有动画
                    viewPager.setCurrentItem(4,true);
                    isEnableSlidingMenu(SlidingMenu.TOUCHMODE_NONE);
                    break;
            }
        }
    }

    private class MyOnPageChangeListener implements ViewPager.OnPageChangeListener {
        @Override
        public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {

        }

        //当页面被选中的时候
        @Override
        public void onPageSelected(int position) {

            basePagers.get(position).initData();
        }

        @Override
        public void onPageScrollStateChanged(int state) {

        }
    }
}
