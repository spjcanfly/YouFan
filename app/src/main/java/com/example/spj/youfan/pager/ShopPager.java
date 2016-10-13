package com.example.spj.youfan.pager;

import android.content.Context;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.CheckBox;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.example.spj.youfan.R;
import com.example.spj.youfan.adapter.MyShopAdapter;
import com.example.spj.youfan.base.BasePager;
import com.example.spj.youfan.bean.shop.Goods;
import com.example.spj.youfan.dao.pre.ShoppingCartBiz;

import java.util.List;

/**
 * Created by spj on 2016/9/28.
 */
public class ShopPager extends BasePager{


    private RecyclerView shop_car_recyclevieew;
    private TextView btnSettle;
    private TextView tvCountMoney;
    private RelativeLayout rlBottomBar;
    private MyShopAdapter adapter;
    private ImageView imageView;
    private TextView textView;
    private List<Goods> lists;
    private CheckBox checkbox_all;
    private static final int ACTION_EDIT = 0;
    private static final int ACTION_COMPLETE = 1;

    public ShopPager(Context context) {
        super(context);
    }

    @Override
    public void initData() {
        //把这个放在这里，防止空指针
        View view = View.inflate(mContext, R.layout.shop_car_pay, null);
        shop_car_recyclevieew = (RecyclerView) view.findViewById(R.id.shop_car_recyclevieew);
        imageView = (ImageView) view.findViewById(R.id.imageView);
        textView = (TextView) view.findViewById(R.id.textView);
        checkbox_all = (CheckBox) view.findViewById(R.id.checkbox_all);
        btnSettle = (TextView) view.findViewById(R.id.btnSettle);
        tvCountMoney = (TextView) view.findViewById(R.id.tvCountMoney);
        rlBottomBar = (RelativeLayout) view.findViewById(R.id.rlBottomBar);

        setAdapter();

        //把子视图添加到BasePager上的Fragment上
        if(flContent != null) {
            flContent.removeAllViews();
        }

        flContent.addView(view);
        super.initData();
    }

    private void setAdapter() {
        int goodsCount = ShoppingCartBiz.getGoodsCount();
        if(goodsCount == 0) {
            showEmpty(true);
        }else {
            showEmpty(false);
            lists = ShoppingCartBiz.getListGood();
            adapter = new MyShopAdapter(mContext,lists,checkbox_all,tvCountMoney,tvEditAll);
            shop_car_recyclevieew.setAdapter(adapter);
            //注意recycleview必须要加上这一句
            shop_car_recyclevieew.setLayoutManager(new LinearLayoutManager(mContext, LinearLayoutManager.VERTICAL, false));

        }
    }

    public void showEmpty(boolean isEmpty) {
        if (isEmpty) {
            //没有数据
            shop_car_recyclevieew.setVisibility(View.GONE);
            textView.setVisibility(View.VISIBLE);
            imageView.setVisibility(View.VISIBLE);
            rlBottomBar.setVisibility(View.GONE);
            tvEditAll.setVisibility(View.GONE);
        } else {
            //有数据
            shop_car_recyclevieew.setVisibility(View.VISIBLE);
            textView.setVisibility(View.GONE);
            imageView.setVisibility(View.GONE);
            rlBottomBar.setVisibility(View.VISIBLE);
            tvEditAll.setVisibility(View.VISIBLE);
        }
    }

    @Override
    public void initTitle() {
          rlTitleBar.setVisibility(View.VISIBLE);
          ll_common_title.setVisibility(View.GONE);
    }

    //为按钮设置监听
    @Override
    public void initListener() {
        //设置为编辑的状态
        tvEditAll.setTag(ACTION_EDIT);
        tvEditAll.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                int action = (int)tvEditAll.getTag();
                if(action == ACTION_EDIT) {
                    tvEditAll.setText("完成");
                    //点击后变为完成状态，显示可以增删的按钮
                    tvEditAll.setTag(ACTION_COMPLETE);
                    //刷新适配器
                    adapter.notifyDataSetChanged();
                }else if(action == ACTION_COMPLETE) {
                    tvEditAll.setText("编辑");
                    //点击后变为编辑状态，显示商品
                    tvEditAll.setTag(ACTION_EDIT);
                    //刷新适配器
                    adapter.notifyDataSetChanged();
                }
            }
        });

        checkbox_all.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //1.得到chekbox是否选中
                boolean isChecked = checkbox_all.isChecked();
                //2.设置全选和非全选
                adapter.checkAll_none(isChecked);
                //3.重新计算总价格
                adapter.showTotalPrice();
            }
        });
    }

}
