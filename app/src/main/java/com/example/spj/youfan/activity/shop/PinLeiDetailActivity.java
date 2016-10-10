package com.example.spj.youfan.activity.shop;

import android.app.Activity;
import android.os.Bundle;
import android.support.design.widget.TabLayout;
import android.support.v4.view.PagerAdapter;
import android.support.v4.view.ViewPager;
import android.view.View;
import android.view.ViewGroup;
import android.view.ViewTreeObserver;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.load.engine.DiskCacheStrategy;
import com.example.spj.youfan.R;
import com.example.spj.youfan.pager.detailpager.PinLeiDetailPager;
import com.example.spj.youfan.uiself.NoScrollViewPager;
import com.example.spj.youfan.uiself.ObservableScrollView;

import org.angmarch.views.NiceSpinner;

import java.util.ArrayList;
import java.util.List;

import butterknife.Bind;
import butterknife.ButterKnife;

public class PinLeiDetailActivity extends Activity implements ObservableScrollView.Callbacks{

    @Bind(R.id.ib_top_menu)
    ImageView ibTopMenu;
    @Bind(R.id.ib_top_set)
    ImageView ibTopSet;
    @Bind(R.id.iv_top_back)
    ImageView ivTopBack;
    @Bind(R.id.tv_top)
    TextView tvTop;
    @Bind(R.id.nice_spinner)
    NiceSpinner niceSpinner;
    @Bind(R.id.tablayout)
    TabLayout tablayout;
    @Bind(R.id.iv_top_seacher)
    ImageView ivTopSeacher;
    @Bind(R.id.iv_top_mail)
    ImageView ivTopMail;
    @Bind(R.id.iv_brand)
    ImageView ivBrand;
    @Bind(R.id.stopView)
    View stopView;
    @Bind(R.id.brand_viewpager)
    NoScrollViewPager brandViewpager;
    @Bind(R.id.tablayout_brand)
    TabLayout tablayoutBrand;
    @Bind(R.id.scrollView)
    ObservableScrollView scrollView;
    private String[] attrs;
    private List<PinLeiDetailPager> pinLeiDetails;
    private String id;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_pin_lei_detail);
        ButterKnife.bind(this);
        //得到传递过来的数据,并且设置
        getData();

        //顶部的显示还是隐藏
        initView();

        //关联Tablayout和viewpager
        bindTabAndVp(id);

        //设置scrollview
        setScrollview();
    }

    private void setScrollview() {
        scrollView.setCallbacks(this);

        scrollView.getViewTreeObserver().addOnGlobalLayoutListener(
                new ViewTreeObserver.OnGlobalLayoutListener() {

                    @Override
                    public void onGlobalLayout() {
                        onScrollChanged(scrollView.getScrollY());
                    }
                });

        scrollView.scrollTo(0, 0);
        scrollView.smoothScrollTo(0, 0);
    }

    private void bindTabAndVp(String id) {
        //准备品牌详情页面的数据
        pinLeiDetails = new ArrayList<>();
        //往Tablayout上准备装配的数据
        attrs = new String[]{"上新", "热销", "价格"};

        for (int i = 0; i < attrs.length; i++) {
            pinLeiDetails.add(new PinLeiDetailPager(PinLeiDetailActivity.this, id, attrs[i]));
        }

        //设置viewpager的高度，使其能够滑动
        ViewGroup.LayoutParams params = brandViewpager.getLayoutParams();
        params.height = 6000;
        brandViewpager.setLayoutParams(params);

        //设置ViewPager的适配器
        brandViewpager.setAdapter(new MyPinLeiPagerAdapter());

        //ViewPager 和TabPageIndicator关联
        tablayoutBrand.setupWithViewPager(brandViewpager);

        //监听页面的变化
        brandViewpager.addOnPageChangeListener(new MyOnpagerChangeListener());

        //设置滑动(如果没有这个，上面的指针会跟着页面移动(MODE_SCROLLABLE,这个是会跟着移动))
        tablayout.setTabMode(TabLayout.MODE_FIXED);

    }

    private void getData() {
        String img = getIntent().getStringExtra("img");
        Glide.with(this).load(img).placeholder(R.drawable.fun_loading_0).diskCacheStrategy(DiskCacheStrategy.ALL).into(ivBrand);
        String name = getIntent().getStringExtra("name");
        tvTop.setText(name);
        id = getIntent().getStringExtra("id");
    }

    private void initView() {
        ibTopMenu.setVisibility(View.GONE);
        ibTopSet.setVisibility(View.GONE);
        ivTopSeacher.setVisibility(View.VISIBLE);
        ivTopBack.setVisibility(View.VISIBLE);
        ivTopMail.setVisibility(View.GONE);
        tvTop.setVisibility(View.VISIBLE);
        niceSpinner.setVisibility(View.GONE);
        tablayout.setVisibility(View.INVISIBLE);

        ivTopBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
    }

    @Override
    public void onScrollChanged(int scrollY) {
        ((LinearLayout) this.findViewById(R.id.ll_brand))
                .setTranslationY(Math.max(stopView.getTop(), scrollY));
    }

    @Override
    public void onDownMotionEvent() {

    }

    @Override
    public void onUpOrCancelMotionEvent() {

    }

    private class MyPinLeiPagerAdapter extends PagerAdapter {
        @Override
        public CharSequence getPageTitle(int position) {
            return attrs[position];
        }

        @Override
        public int getCount() {
            return attrs.length;
        }

        @Override
        public boolean isViewFromObject(View view, Object object) {
            return view == object;
        }

        @Override
        public Object instantiateItem(ViewGroup container, int position) {
            PinLeiDetailPager pinLeiDetailPager = pinLeiDetails.get(position);
            View rootView = pinLeiDetailPager.rootView;
            pinLeiDetailPager.initData();//初始化数据
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
