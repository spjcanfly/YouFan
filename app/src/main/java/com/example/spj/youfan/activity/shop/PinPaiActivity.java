package com.example.spj.youfan.activity.shop;

import android.graphics.Color;
import android.os.Bundle;
import android.support.design.widget.TabLayout;
import android.support.v4.app.FragmentActivity;
import android.support.v4.view.PagerAdapter;
import android.support.v4.view.ViewPager;
import android.view.View;
import android.view.ViewGroup;
import android.view.ViewTreeObserver;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.bumptech.glide.Glide;
import com.bumptech.glide.load.engine.DiskCacheStrategy;
import com.example.spj.youfan.R;
import com.example.spj.youfan.bean.PinPaiStory;
import com.example.spj.youfan.pager.detailpager.BrandDetailPager;
import com.example.spj.youfan.uiself.ObservableScrollView;
import com.example.spj.youfan.utils.Constants;
import com.example.spj.youfan.utils.LogUtil;
import com.google.gson.Gson;
import com.zhy.http.okhttp.OkHttpUtils;
import com.zhy.http.okhttp.callback.StringCallback;

import org.angmarch.views.NiceSpinner;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import butterknife.Bind;
import butterknife.ButterKnife;
import butterknife.OnClick;
import okhttp3.Call;

public class PinPaiActivity extends FragmentActivity implements ObservableScrollView.Callbacks {


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
    @Bind(R.id.iv_top_seacher)
    ImageView ivTopSeacher;
    @Bind(R.id.iv_top_mail)
    ImageView ivTopMail;
    @Bind(R.id.ll_common_title)
    LinearLayout llCommonTitle;
    @Bind(R.id.iv_brand)
    ImageView ivBrand;
    @Bind(R.id.iv_brand_logo)
    ImageView ivBrandLogo;
    @Bind(R.id.tv_brand)
    TextView tvBrand;
    @Bind(R.id.btn_brand)
    Button btnBrand;
    @Bind(R.id.tablayout_brand)
    TabLayout tablayout_brand;
    @Bind(R.id.stopView)
    View stopView;
    @Bind(R.id.brand_viewpager)
    ViewPager brandViewpager;
    @Bind(R.id.scrollView)
    ObservableScrollView scrollView;
    @Bind(R.id.tablayout)
    TabLayout tablayout;
    private String url;
    private String[] attrs;
    private List<BrandDetailPager> brandDetails;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_pin_pai);
        ButterKnife.bind(this);
        //得到传递过来的数据
        String name = getIntent().getStringExtra("name");

        //请求数据的网络地址
//        url = Constants.BRANDDETAIL_PRE + name + Constants.BRANDDETAIL_TAIL;
        url = Constants.BRAND_STORY_PRE + name + Constants.BRANDDETAIL_TAIL;
        //顶部的显示还是隐藏
        initView();

        getDataFromNet();

        //关联Tablayout和viewpager
        bindTabAndVp(name);

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

    private void bindTabAndVp(String name) {
        //准备灵感详情页面的数据
        brandDetails = new ArrayList<>();
        //往Tablayout上准备装配的数据
        attrs = new String[]{"上新", "热销", "价格"};

        for (int i = 0; i < attrs.length; i++) {
            brandDetails.add(new BrandDetailPager(PinPaiActivity.this, name));
        }

        //设置viewpager的高度，使其能够滑动
        ViewGroup.LayoutParams params = brandViewpager.getLayoutParams();
        params.height = 10000;
        brandViewpager.setLayoutParams(params);

        //设置ViewPager的适配器
        brandViewpager.setAdapter(new MyBrandPagerAdapter());

        //ViewPager 和TabPageIndicator关联
        tablayout_brand.setupWithViewPager(brandViewpager);

        //监听页面的变化
        brandViewpager.addOnPageChangeListener(new MyOnpagerChangeListener());

        //设置滑动(如果没有这个，上面的指针会跟着页面移动(MODE_SCROLLABLE,这个是会跟着移动))
        tablayout.setTabMode(TabLayout.MODE_FIXED);
    }

    private void getDataFromNet() {
        OkHttpUtils.get()
                .url(url)
                .id(100)
                .build()
                .execute(new StringCallback() {
                    @Override
                    public void onError(Call call, Exception e, int id) {
                        LogUtil.e("使用okhttp联网请求失败==品牌详细" + e.getMessage());
                    }

                    @Override
                    public void onResponse(String response, int id) {
                        LogUtil.e("使用okhttp联网请求数据成功==品牌详细" + response);
                        processedData(response);
                    }
                });
    }

    private void processedData(String json) {
        PinPaiStory bean = parsedJson(json);
        PinPaiStory.DataBean data = bean.getData();
        if (data != null) {
            //有数据
            String logo_img = data.getLittle_img();
            String youfan_img = data.getYoufan_img();
            String ename = data.getEname();
            tvBrand.setText(ename);
            Glide.with(PinPaiActivity.this).load(logo_img).placeholder(R.drawable.ic_error_page).diskCacheStrategy(DiskCacheStrategy.ALL).into(ivBrandLogo);
            Glide.with(PinPaiActivity.this).load(youfan_img).placeholder(R.drawable.ic_error_page).diskCacheStrategy(DiskCacheStrategy.ALL).into(ivBrand);
        }

    }

    private PinPaiStory parsedJson(String json) {
        return new Gson().fromJson(json, PinPaiStory.class);
    }

    @OnClick({R.id.iv_top_back, R.id.iv_top_seacher, R.id.iv_brand, R.id.iv_brand_logo, R.id.btn_brand})
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.iv_top_back:
                finish();
                break;
            case R.id.iv_top_seacher:
                break;
            case R.id.iv_brand:
                break;
            case R.id.iv_brand_logo:
                break;
            case R.id.btn_brand:
                break;
        }
    }


    private void initView() {
        ibTopMenu.setVisibility(View.GONE);
        ibTopSet.setVisibility(View.GONE);
        ivTopSeacher.setVisibility(View.VISIBLE);
        ivTopBack.setVisibility(View.VISIBLE);
        ivTopMail.setVisibility(View.GONE);
        tvTop.setVisibility(View.GONE);
        niceSpinner.setVisibility(View.VISIBLE);
        tablayout.setVisibility(View.INVISIBLE);

        final List<String> dataset = new ArrayList<>(Arrays.asList("全部", "男生"));
        niceSpinner.setTextColor(Color.BLACK);
        niceSpinner.setPadding(0, 0, 0, 0);
        niceSpinner.setCompoundDrawablePadding(10);
        niceSpinner.setTextSize(18);
        niceSpinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                Toast.makeText(PinPaiActivity.this, "你点击的是" + dataset.get(position), Toast.LENGTH_SHORT).show();
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });
        niceSpinner.attachDataSource(dataset);
    }

    //实现ObservableScrollView.Callbacks的方法
    @Override
    public void onScrollChanged(int scrollY) {
        ((LinearLayout) this.findViewById(R.id.ll_brand))
                .setTranslationY(Math.max(stopView.getTop(), scrollY));
    }

    //实现ObservableScrollView.Callbacks的方法
    @Override
    public void onDownMotionEvent() {

    }

    //实现ObservableScrollView.Callbacks的方法
    @Override
    public void onUpOrCancelMotionEvent() {

    }


    private class MyBrandPagerAdapter extends PagerAdapter {
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
            BrandDetailPager brandDetailPager = brandDetails.get(position);
            View rootView = brandDetailPager.rootView;
            brandDetailPager.initData();//初始化数据
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