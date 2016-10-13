package com.example.spj.youfan.pager;

import android.content.Context;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.load.engine.DiskCacheStrategy;
import com.example.spj.youfan.R;
import com.example.spj.youfan.base.BasePager;
import com.example.spj.youfan.bean.shop.Goods;
import com.example.spj.youfan.dao.pre.ShoppingCartBiz;
import com.example.spj.youfan.utils.LogUtil;

import java.util.List;

/**
 * Created by spj on 2016/9/28.
 */
public class ShopPager extends BasePager{


    private RecyclerView shop_car_recyclevieew;
    private ImageView ivSelectAll;
    private TextView btnSettle;
    private TextView tvCountMoney;
    private RelativeLayout rlBottomBar;
    private MyShopAdapter adapter;
    private ImageView imageView;
    private TextView textView;
    private List<Goods> lists;
    private boolean isSelectAll = false;

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
        ivSelectAll = (ImageView) view.findViewById(R.id.ivSelectAll);
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
            adapter = new MyShopAdapter();
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
        //点击全部编辑的那个按钮
//        tvEditAll.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                for (int i = 0; i < lists.size(); i++) {
//                }
//            }
//        });
        ivSelectAll.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                isSelectAll = ShoppingCartBiz.selectAll(lists, isSelectAll, (ImageView) v);
//                        setSettleInfo();
                LogUtil.e("222222222222");
                adapter.notifyDataSetChanged();
            }
        });
    }

    class MyShopAdapter extends RecyclerView.Adapter<MyShopAdapter.ViewHolder> {

        private Goods goods;

        @Override
        public MyShopAdapter.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
            View convertView = LayoutInflater.from(mContext).inflate(R.layout.shop_pager_item, parent, false);
            return new ViewHolder(convertView);
        }

        @Override
        public void onBindViewHolder(MyShopAdapter.ViewHolder holder, int position) {
            holder.ivCheckGood.setOnClickListener(listener);
            holder.tvDel.setOnClickListener(listener);
            holder.ivAdd.setOnClickListener(listener);
            holder.ivReduce.setOnClickListener(listener);
            holder.llGoodInfo.setOnClickListener(listener);
            goods = lists.get(position);
            holder.tvItemChild.setText(goods.getName());
            holder.tvPriceNew.setText("￥" + goods.getSale_price());
            holder.tvNum.setText("×" + goods.getNumber());
            Glide.with(mContext).load(goods.getMainImage()).placeholder(R.drawable.fun_loading_0).diskCacheStrategy(DiskCacheStrategy.ALL).into(holder.ivGoods);

        }

        View.OnClickListener listener = new View.OnClickListener() {

            public void onClick(View v) {
                switch (v.getId()) {
                    case R.id.btnSettle:
//                        if (ShoppingCartBiz.hasSelectedGoods(mListGoods)) {
//                            ToastHelper.getInstance()._toast("结算跳转");
//                        } else {
//                            ToastHelper.getInstance()._toast("亲，先选择商品！");
//                        }
                        break;
                    //child
                    case R.id.ivCheckGood:
                        //刚开始给他设置为true
                        boolean isSelected = true;
                        if(isSelected) {
                            goods.setIsSelected(isSelected);
                            ShoppingCartBiz.checkItem(isSelected, (ImageView) v);
                        }else {
//                            ShoppingCartBiz.selectOne()
                        }
                        break;
                    case R.id.tvDel:
//                        String tagPos = String.valueOf(v.getTag());
//                        if (tagPos.contains(",")) {
//                            String s[] = tagPos.split(",");
//                            int groupPosition = Integer.parseInt(s[0]);
//                            int childPosition = Integer.parseInt(s[1]);
//                            showDelDialog(groupPosition, childPosition);
//                        }
                        break;
                    case R.id.ivAdd:
//                        ShoppingCartBiz.addOrReduceGoodsNum(true, (ShoppingCartBean.Goods) v.getTag(), ((TextView) (((View) (v.getParent())).findViewById(R.id.tvNum2))));
//                        setSettleInfo();
                        break;
                    case R.id.ivReduce:
//                        ShoppingCartBiz.addOrReduceGoodsNum(false, (ShoppingCartBean.Goods) v.getTag(), ((TextView) (((View) (v.getParent())).findViewById(R.id.tvNum2))));
//                        setSettleInfo();
                        break;
                }
            }
        };

        //注意条目的数量
        @Override
        public int getItemCount() {
            return lists == null ? 0 : lists.size();
        }

        class ViewHolder extends RecyclerView.ViewHolder {

            ImageView ivCheckGood;
            ImageView ivGoods;
            TextView tvItemChild;
            TextView tvGoodsParam;
            TextView tvPriceNew;
            TextView tvNum;
            RelativeLayout rlEditStatus;
            ImageView ivAdd;
            TextView tvNum2;
            ImageView ivReduce;
            TextView tvDel;
            LinearLayout llGoodInfo;

            public ViewHolder(View convertView) {
                super(convertView);
                ivCheckGood = (ImageView) convertView.findViewById(R.id.ivCheckGood);
                ivGoods = (ImageView) convertView.findViewById(R.id.ivGoods);
                llGoodInfo = (LinearLayout) convertView.findViewById(R.id.llGoodInfo);
                tvItemChild = (TextView) convertView.findViewById(R.id.tvItemChild);
                tvGoodsParam = (TextView) convertView.findViewById(R.id.tvGoodsParam);
                tvPriceNew = (TextView) convertView.findViewById(R.id.tvPriceNew);
                tvNum = (TextView) convertView.findViewById(R.id.tvNum);
                rlEditStatus = (RelativeLayout) convertView.findViewById(R.id.rlEditStatus);
                ivAdd = (ImageView) convertView.findViewById(R.id.ivAdd);
                tvNum2 = (TextView) convertView.findViewById(R.id.tvNum2);
                ivReduce = (ImageView) convertView.findViewById(R.id.ivReduce);
                tvDel = (TextView) convertView.findViewById(R.id.tvDel);
            }

        }
    }
}
