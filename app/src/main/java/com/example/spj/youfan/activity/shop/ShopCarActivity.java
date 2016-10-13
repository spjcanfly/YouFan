package com.example.spj.youfan.activity.shop;

import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.v4.app.FragmentActivity;
import android.support.v4.app.FragmentTransaction;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.Toast;

import com.example.spj.youfan.R;
import com.example.spj.youfan.base.BaseShopFragment;
import com.example.spj.youfan.bean.SingleShopDetail;
import com.example.spj.youfan.bean.shop.Goods;
import com.example.spj.youfan.dao.pre.ShoppingCartBiz;
import com.example.spj.youfan.fragment.FragmentBottom;
import com.example.spj.youfan.fragment.FragmentTop;
import com.example.spj.youfan.utils.CacheUtils;
import com.example.spj.youfan.utils.Constants;
import com.example.spj.youfan.utils.LogUtil;
import com.google.gson.Gson;
import com.lzy.widget.VerticalSlide;
import com.zhy.http.okhttp.OkHttpUtils;
import com.zhy.http.okhttp.callback.StringCallback;

import okhttp3.Call;

public class ShopCarActivity extends FragmentActivity implements View.OnClickListener {

    private VerticalSlide verticalSlide;
    private FloatingActionButton fab;
    private BaseShopFragment topFragment;
    private BaseShopFragment bottomFragment;
    private SingleShopDetail.DataBean data;
    private ImageView iv_top_back;
    private Button btn_add_car;

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
        getDataFromNet(url);

    }

    private void getDataFromNet(final String url) {
        //从缓存中取得数据
        String response = CacheUtils.getString(ShopCarActivity.this, url);
        if(!TextUtils.isEmpty(response)) {
            processedData(response);
        }
        //使用OKhttp第三方封装库请求网络
        OkHttpUtils.get()
                .url(url)
                .id(100)
                .build()
                .execute(new StringCallback() {
                    @Override
                    public void onError(Call call, Exception e, int id) {
                        LogUtil.e("使用okhttp联网请求失败==单品详情" + e.getMessage());
                    }

                    @Override
                    public void onResponse(String response, int id) {
                        LogUtil.e("使用okhttp联网请求数据成功==单品详情" + response);
                        //缓存数据
                        CacheUtils.putString(ShopCarActivity.this, url, response);
                        processedData(response);
                    }
                });
    }

    //获得解析后的bean对象
    private void processedData(String json) {
        SingleShopDetail bean = parsedJson(json);
        data = bean.getData();
        if(data != null) {
            initFragment();
        }
    }

    //解析json数据
    private SingleShopDetail parsedJson(String response) {
        return new Gson().fromJson(response, SingleShopDetail.class);
    }

    private void initFragment() {

        FragmentTransaction transaction = getSupportFragmentManager().beginTransaction();
        topFragment = new FragmentTop(ShopCarActivity.this,data);
        transaction.replace(R.id.first, topFragment);

        bottomFragment = new FragmentBottom(ShopCarActivity.this,data);
        transaction.replace(R.id.second,bottomFragment);
        transaction.commit();

    }

    private void initView() {
        verticalSlide = (VerticalSlide) findViewById(R.id.dragLayout);
        fab = (FloatingActionButton) findViewById(R.id.fab);
        iv_top_back = (ImageView) findViewById(R.id.iv_top_back);
        btn_add_car = (Button) findViewById(R.id.btn_add_car);
        fab.setOnClickListener(this);
        iv_top_back.setOnClickListener(this);
        btn_add_car.setOnClickListener(this);
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
        switch (v.getId()) {
            case R.id.fab :
                bottomFragment.goTop();
                verticalSlide.goTop(new VerticalSlide.OnGoTopListener() {
                    @Override
                    public void goTop() {
                        topFragment.goTop();
                    }
                });
                break;
            case R.id.iv_top_back:
                finish();
                break;
            case R.id.btn_add_car:
//                Goods good = new Goods(5,"1026919","风衣",150,"http://img7.ibanggo.com/sources/images/goods/TP/832565/832565_13_00.jpg");
//                Goods good = new Goods(2,"804633","牛仔裤",99,"http://img6.ibanggo.com/sources/images/goods/TP/819559/819559_43_00.jpg");
                Goods good = new Goods(1,"834262","PUMA背包",169,"http://img6.ibanggo.com/sources/images/goods/TP/834262/834262_40_00.jpg");
                ShoppingCartBiz.addGoodToCart(good);
                Toast.makeText(ShopCarActivity.this, "成功添加到购物车", Toast.LENGTH_SHORT).show();
                break;
        }
    }
}
