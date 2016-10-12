package com.example.spj.youfan.fragment;

import android.annotation.SuppressLint;
import android.content.Context;
import android.graphics.Paint;
import android.os.Bundle;
import android.support.v4.view.PagerAdapter;
import android.support.v4.view.ViewPager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.load.engine.DiskCacheStrategy;
import com.example.spj.youfan.R;
import com.example.spj.youfan.base.BaseShopFragment;
import com.example.spj.youfan.bean.SingleShopDetail;
import com.lzy.widget.vertical.VerticalScrollView;

import java.util.List;

/**
 * Created by spj on 2016/10/10.
 */
@SuppressLint("ValidFragment")
public class FragmentTop extends BaseShopFragment {

    private final Context mContext;
    private final SingleShopDetail.DataBean mData;
    private VerticalScrollView scrollView;
    private ViewPager vp_shop;
    private TextView tv_show_name;
    private TextView tv_like_price_now;
    private TextView tv_like_price_market;
    private ImageView iv_send;
    private ImageView iv_brand_logo;
    private TextView tv_brand_name;
    private TextView tv_viewpager;


    public FragmentTop(Context context, SingleShopDetail.DataBean data) {
        this.mContext = context;
        this.mData = data;
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_scrollview, container, false);
        //找控件
        initView(view);

        //设置数据
        setData();

        return view;
    }

    private void setData() {
        //viewpager的设置
        MyPagerAdapter adapter = new MyPagerAdapter();
        vp_shop.setAdapter(adapter);

        vp_shop.addOnPageChangeListener(new MyPagerListenner());

        String showName = mData.getClsInfo().getShowName();
        tv_show_name.setText(showName);

        String sale_price = mData.getClsInfo().getSale_price();
        String[] split = sale_price.split("\\.");
        tv_like_price_now.setText(split[0]);

        String marketPrice = mData.getClsInfo().getMarketPrice();
        //设置中划线
        String[] split1 = marketPrice.split("\\.");
        tv_like_price_market.getPaint().setFlags(Paint.STRIKE_THRU_TEXT_FLAG);
        tv_like_price_market.setText("￥" + split1[0]);

        String url = mData.getActivity().get(0).getUrl();
        //加载包邮小图片
        Glide.with(mContext).load(url).placeholder(R.drawable.fun_loading_0).diskCacheStrategy(DiskCacheStrategy.ALL).into(iv_send);

        String brandUrl = mData.getClsInfo().getBrandUrl();
        //加载品牌小图片
        Glide.with(mContext).load(brandUrl).placeholder(R.drawable.fun_loading_0).diskCacheStrategy(DiskCacheStrategy.ALL).into(iv_brand_logo);

        String brand = mData.getClsInfo().getBrand();
        tv_brand_name.setText(brand);
    }

    private void initView(View view) {
        scrollView = (VerticalScrollView) view.findViewById(R.id.scrollView);
        vp_shop = (ViewPager) view.findViewById(R.id.vp_shop);
        tv_show_name = (TextView) view.findViewById(R.id.tv_show_name);
        tv_like_price_now = (TextView) view.findViewById(R.id.tv_like_price_now);
        tv_like_price_market = (TextView) view.findViewById(R.id.tv_like_price_market);
        tv_brand_name = (TextView) view.findViewById(R.id.tv_brand_name);
        tv_viewpager = (TextView) view.findViewById(R.id.tv_viewpager);
        iv_send = (ImageView) view.findViewById(R.id.iv_send);
        iv_brand_logo = (ImageView) view.findViewById(R.id.iv_brand_logo);
    }

    @Override
    public void goTop() {
        scrollView.goTop();
    }

    class MyPagerAdapter extends PagerAdapter {

        private List<SingleShopDetail.DataBean.ProPicUrlBean> proPicUrl;

        @Override
        public int getCount() {
            proPicUrl = mData.getProPicUrl();
            return proPicUrl == null ? 0 : proPicUrl.size();
        }

        @Override
        public boolean isViewFromObject(View view, Object object) {
            //判断界面显示的视图数据是否来自于集合对象
            return view == object;
        }

        //销毁指定的图片数据
        @Override
        public void destroyItem(ViewGroup container, int position, Object object) {
            container.removeView((View) object);
        }

        //装配图片数据并返回
        @Override
        public Object instantiateItem(ViewGroup container, int position) {
            String url = proPicUrl.get(position).getFilE_PATH();
            ImageView imageView = new ImageView(mContext);
            imageView.setScaleType(ImageView.ScaleType.CENTER_CROP);
            Glide.with(mContext).load(url).placeholder(R.drawable.fun_loading_0).diskCacheStrategy(DiskCacheStrategy.ALL).into(imageView);
            container.addView(imageView);
            return imageView;
        }
    }

    private class MyPagerListenner implements ViewPager.OnPageChangeListener {
        @Override
        public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {
            int size = mData.getProPicUrl().size();
            int nowPosition = position + 1;
            tv_viewpager.setText(nowPosition+"/"+size);
        }

        @Override
        public void onPageSelected(int position) {

        }

        @Override
        public void onPageScrollStateChanged(int state) {

        }
    }
}
