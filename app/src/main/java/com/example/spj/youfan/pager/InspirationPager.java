package com.example.spj.youfan.pager;

import android.content.Context;
import android.support.design.widget.TabLayout;
import android.support.v4.view.PagerAdapter;
import android.support.v4.view.ViewPager;
import android.text.TextUtils;
import android.view.View;
import android.view.ViewGroup;

import com.example.spj.youfan.R;
import com.example.spj.youfan.base.BasePager;
import com.example.spj.youfan.bean.LingGanZiXun;
import com.example.spj.youfan.utils.CacheUtils;
import com.example.spj.youfan.utils.Constants;
import com.example.spj.youfan.utils.LogUtil;
import com.google.gson.Gson;
import com.zhy.http.okhttp.OkHttpUtils;
import com.zhy.http.okhttp.callback.StringCallback;

import java.util.ArrayList;
import java.util.List;

import okhttp3.Call;

/**
 * Created by spj on 2016/9/28.
 * 灵感的页面
 */
public class InspirationPager extends BasePager {

    private TabLayout tablayout;
    private ViewPager inspiration_viewpager;
    private List<LingGanZiXun.DataBean.AttrBean> attrs;
    private ArrayList<TabDetailPager> tabDetailPagers;

    public InspirationPager(Context context) {
        super(context);
    }

    @Override
    public void initData() {
        //得到缓存数据
        String saveJason = CacheUtils.getString(mContext, Constants.LINGGAN_ZIXUN);
        if (!TextUtils.isEmpty(saveJason)) {
            processData(saveJason);
        }
        //从网络获取数据
        getDataFromNet();

        super.initData();
    }

    private void getDataFromNet() {
        //使用OKhttp第三方封装库请求网络
        OkHttpUtils.get()
                .url(Constants.LINGGAN_ZIXUN)
                .id(100)
                .build()
                .execute(new StringCallback() {
                    @Override
                    public void onError(Call call, Exception e, int id) {
                        LogUtil.e("使用okhttp联网请求失败==灵感资讯" + e.getMessage());
                    }

                    @Override
                    public void onResponse(String response, int id) {
                        LogUtil.e("使用okhttp联网请求数据成功==灵感资讯" + response);
                        //缓存的数据放到sp存储中
                        CacheUtils.putString(mContext, Constants.LINGGAN_ZIXUN, response);
                        processData(response);
                    }
                });
    }

    private void processData(String json) {
        //移除之前的内容才行，一个坑啊
        flContent.removeAllViews();

        LingGanZiXun bean = parsedJson(json);
        //最上面的各个标题
        attrs = bean.getData().getAttr();

        View view = View.inflate(mContext, R.layout.inspiration_pager, null);
        tablayout = (TabLayout) view.findViewById(R.id.tablayout);
        inspiration_viewpager = (ViewPager) view.findViewById(R.id.inspiration_viewpager);
        //把子视图添加到BasePager上的Fragment上
        flContent.addView(view);

        //准备灵感详情页面的数据
        tabDetailPagers = new ArrayList<>();
        for (int i = 0; i < attrs.size(); i++) {
            tabDetailPagers.add(new TabDetailPager(mContext, attrs.get(i).getName()));
        }

        //设置ViewPager的适配器
        inspiration_viewpager.setAdapter(new MyInspirationPagerAdapter());

        //ViewPager 和TabPageIndicator关联
        tablayout.setupWithViewPager(inspiration_viewpager);

        //监听页面的变化
        inspiration_viewpager.addOnPageChangeListener(new MyOnpagerChangeListener());

        //设置滑动(如果没有这个，上面的指针不会跟着页面移动)
        tablayout.setTabMode(TabLayout.MODE_SCROLLABLE);
    }

    private LingGanZiXun parsedJson(String json) {
        return new Gson().fromJson(json, LingGanZiXun.class);
    }

    @Override
    public void initTitle() {
        ibTopMenu.setVisibility(View.GONE);
        ibTopSet.setVisibility(View.GONE);
        ivTopSeacher.setVisibility(View.VISIBLE);
        ivTopMail.setVisibility(View.GONE);
        ivTopBack.setVisibility(View.GONE);
        tvTop.setText("资讯");
    }

    @Override
    public void initListener() {

    }

    private class MyInspirationPagerAdapter extends PagerAdapter {

        @Override
        public CharSequence getPageTitle(int position) {
            return attrs.get(position).getName();
        }

        @Override
        public int getCount() {
            return attrs == null ? 0 : attrs.size();
        }

        @Override
        public boolean isViewFromObject(View view, Object object) {
            return view == object;
        }

        @Override
        public Object instantiateItem(ViewGroup container, int position) {
            TabDetailPager tabDetailPager = tabDetailPagers.get(position);
            View rootView = tabDetailPager.rootView;
            tabDetailPager.initData();//初始化数据
            container.addView(rootView);
            return rootView;
        }

        @Override
        public void destroyItem(ViewGroup container, int position, Object object) {
            container.removeView((View) object);
        }
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
