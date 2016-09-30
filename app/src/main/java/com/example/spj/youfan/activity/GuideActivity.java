package com.example.spj.youfan.activity;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.support.v4.view.PagerAdapter;
import android.support.v4.view.ViewPager;
import android.view.View;
import android.view.ViewGroup;
import android.view.ViewTreeObserver;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;

import com.example.spj.youfan.R;
import com.example.spj.youfan.utils.CacheUtils;
import com.example.spj.youfan.utils.UIUtils;

import java.util.ArrayList;
import java.util.List;

/**
 *引导页面
 */
public class GuideActivity extends Activity {


    private ViewPager viewPager;
    private ImageView iv_red_point;
    private LinearLayout ll_point_group;
    private List<ImageView> list;
    private Button btn_start_main;
    private Button btn_guide_skip;
    private int widthDpi;
    private int leftMax;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_guide);

        initView();

        initData();

        initListener();
    }

    private void initListener() {
        btn_start_main.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //1.保存第一次进入,这个到时候改为false
                CacheUtils.putBoolean(GuideActivity.this, SplashActivity.START_First, true);

                startChoiceActivity();
            }
        });

        //点击跳过
        btn_guide_skip.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startChoiceActivity();
            }
        });
    }

    private void startChoiceActivity() {
        //2.跳转到主页面
        Intent intent = new Intent(GuideActivity.this, ChoiceActivity.class);
        startActivity(intent);
        //关闭当前页面
        finish();
    }

    private void initData() {
        int ids[] = new int[]{R.drawable.splash_1, R.drawable.splash_2, R.drawable.splash_3, R.drawable.splash_4, R.drawable.splash_5};
        //把dp转换成像素
        widthDpi = UIUtils.dp2px(10);

        list = new ArrayList<>();
        for (int i = 0; i < ids.length; i++) {
            ImageView imageView = new ImageView(GuideActivity.this);
            //设置背景
            imageView.setBackgroundResource(ids[i]);
            //添加到集合
            list.add(imageView);
            //创建红点
            ImageView point = new ImageView(this);
            point.setBackgroundResource(R.drawable.point_normal);
            LinearLayout.LayoutParams params = new LinearLayout.LayoutParams(widthDpi, widthDpi);
            if (i != 0) {
                //让几个点有距离
                params.leftMargin = widthDpi;
            }
            point.setLayoutParams(params);
            //添加到线性布局中去
            ll_point_group.addView(point);
        }
        //设置ViewPage的适配器
        viewPager.setAdapter(new MyPagerAdapter());

        //根据View的生命周期，当时图执行到onLayout或者onDraw的时候，试图的高和宽，边距都有了
        iv_red_point.getViewTreeObserver().addOnGlobalLayoutListener(new MyOnGlobalLayoutListener());
        //得到屏幕滑动的百分比
        viewPager.setOnPageChangeListener(new MyOnPageChangeListener());
    }

    private void initView() {
        viewPager = (ViewPager) findViewById(R.id.viewPager);
        ll_point_group = (LinearLayout) findViewById(R.id.ll_point_group);
        iv_red_point = (ImageView) findViewById(R.id.iv_red_point);
        btn_start_main = (Button) findViewById(R.id.btn_start_main);
        btn_guide_skip = (Button) findViewById(R.id.btn_guide_skip);
    }


    private class MyPagerAdapter extends PagerAdapter {

        //返回数据的总个数
        @Override
        public int getCount() {
            return list == null ? 0 : list.size();
        }

        /**
         * //作用跟getView（）一样
         *
         * @param container viewPager
         * @param position  创建页面的位置
         * @return
         */
        @Override
        public Object instantiateItem(ViewGroup container, int position) {
            ImageView imageView = list.get(position);
            //添加到容器中去
            container.addView(imageView);
            return imageView;
        }

        /**
         * @param view   当前创建的视图
         * @param object 上面instant方法返回的结果值
         * @return
         */
        @Override
        public boolean isViewFromObject(View view, Object object) {

            return view == object;
        }

        /**
         * @param container ViewPager
         * @param position  要销毁的页面的位置
         * @param object    要销毁的页面
         *                  记住super的方法一定要删除
         */
        @Override
        public void destroyItem(ViewGroup container, int position, Object object) {
//            super.destroyItem(container, position, object);
            container.removeView((View) object);
        }
    }

    private class MyOnGlobalLayoutListener implements ViewTreeObserver.OnGlobalLayoutListener {
        @Override
        public void onGlobalLayout() {
            //这个方法不止执行一次，所以得移除他
            iv_red_point.getViewTreeObserver().removeOnGlobalLayoutListener(MyOnGlobalLayoutListener.this);
            //两个点之间的间距
            leftMax = ll_point_group.getChildAt(1).getLeft() - ll_point_group.getChildAt(0).getLeft();

        }
    }

    private class MyOnPageChangeListener implements ViewPager.OnPageChangeListener {
        /**
         * 当页面回调了会回调这个方法
         *
         * @param position             当前滑动页面的位置
         * @param positionOffset       页面滑动的百分比
         * @param positionOffsetPixels 滑动的像数
         */
        @Override
        public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {
            //两点间移动的距离 = 屏幕滑动百分比 * 间距
            //两点间滑动距离对应的坐标 = 原来的起始位置 +  两点间移动的距离
            int leftMargin = (int) (position * leftMax + (positionOffset * leftMax));
            RelativeLayout.LayoutParams params = (RelativeLayout.LayoutParams) iv_red_point.getLayoutParams();
            params.leftMargin = leftMargin;
            iv_red_point.setLayoutParams(params);
        }

        /**
         * 当页面被选中的时候，回调这个方法
         *
         * @param position 被选中页面的对应的位置
         */
        @Override
        public void onPageSelected(int position) {
            if (position == list.size() - 1) {
                //最后一个页面
                btn_start_main.setVisibility(View.VISIBLE);
            } else {
                //其他页面
                btn_start_main.setVisibility(View.GONE);
            }
        }

        @Override
        public void onPageScrollStateChanged(int state) {

        }
    }
}
