package com.example.spj.youfan.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CheckBox;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.load.engine.DiskCacheStrategy;
import com.example.spj.youfan.R;
import com.example.spj.youfan.bean.shop.Goods;
import com.example.spj.youfan.dao.pre.ShoppingCartBiz;
import com.example.spj.youfan.uiself.NumberAddSubView;

import java.util.List;

/**
 * Created by spj on 2016/10/13.
 */
public class MyShopAdapter extends RecyclerView.Adapter<MyShopAdapter.ViewHolder> {

    private final Context mContext;
    private final List<Goods> lists;
    private final CheckBox mCheckBox_all;
    private final TextView tv_total_money;
    private final TextView tv_edit_all;
    private static final int ACTION_EDIT = 0;
    private static final int ACTION_COMPLETE = 1;
    private Goods goods;

    public MyShopAdapter(Context context, final List<Goods> list, CheckBox checkbox_all, TextView tvCountMoney, TextView tvEditAll) {
        this.mContext = context;
        this.lists = list;
        this.mCheckBox_all = checkbox_all;
        this.tv_total_money = tvCountMoney;
        this.tv_edit_all = tvEditAll;
        //校验是否全选
        checkAll();

        //每一个item的chekbox的点击事件
        setmOnItemClickListener(new OnItemClickListener() {
            @Override
            public void onItemClick(View view, int position) {
                //单个chectkbox的点击事件,点击后保存状态
                Goods good = list.get(position);
                good.setIsSelected(!good.isSelected());
                notifyItemChanged(position);
                //4.校验全选和非全选
                checkAll();
                //显示总价格
                showTotalPrice();
            }
        });

    }

    public void checkAll() {
        if(lists != null && lists.size()>0) {
            int number = 0;
            for (int i = 0; i < lists.size(); i++) {
                Goods goods = lists.get(i);
                if(goods.isSelected()) {
                    number ++;
                }else {
                    //有一个没有被选中
                    mCheckBox_all.setChecked(false);
                }
            }
            if(number == lists.size()) {
                //全部被选中
                mCheckBox_all.setChecked(true);
            }
        }else {
            mCheckBox_all.setChecked(false);
        }
    }

    public void showTotalPrice() {
        int shoppingPrice = ShoppingCartBiz.getShoppingPrice(lists);
        tv_total_money.setText("合计：￥" + shoppingPrice);
    }

    //设置全选和非全选
    public void checkAll_none(boolean isChecked) {
        if (lists != null && lists.size() > 0) {
            for (int i = 0; i < lists.size(); i++) {
                Goods goods = lists.get(i);
                goods.setIsSelected(isChecked);
                //通知刷新
                notifyItemChanged(i);
            }
        }
    }

    @Override
    public MyShopAdapter.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View convertView = LayoutInflater.from(mContext).inflate(R.layout.shop_pager_item, parent, false);
        return new ViewHolder(convertView);
    }

    @Override
    public void onBindViewHolder(final MyShopAdapter.ViewHolder holder, final int position) {
        goods = lists.get(position);
        holder.checkbox_single.setChecked(goods.isSelected());
        holder.tvItemChild.setText(goods.getName());
        holder.tvPriceNew.setText("￥" + goods.getSale_price());
        holder.tvNum.setText("×" + goods.getNumber());
        Glide.with(mContext).load(goods.getMainImage()).placeholder(R.drawable.fun_loading_0).diskCacheStrategy(DiskCacheStrategy.ALL).into(holder.ivGoods);

        //给该商品设置从数据库得到的数量
        holder.numberAddSubView.setValue(goods.getNumber());
        holder.numberAddSubView.setMaxValue(10);
        int action = (int) tv_edit_all.getTag();
        if(action == ACTION_EDIT) {
            //点击后变成完成状态
            holder.rlEditStatus.setVisibility(View.GONE);
            holder.llGoodInfo.setVisibility(View.VISIBLE);
        }else if(action == ACTION_COMPLETE) {
            //点击后变为编辑状态，显示商品的详细
            holder.rlEditStatus.setVisibility(View.VISIBLE);
            holder.llGoodInfo.setVisibility(View.GONE);
        }

        holder.numberAddSubView.setOnNumberClickListener(new NumberAddSubView.OnNumberClickListener() {
            @Override
            public void onButtonSub(View view, int value) {
                goods.setNumber(value);
                ShoppingCartBiz.updateGoodsNumber(goods,value);
                //2.重新显示价格
                showTotalPrice();
            }

            @Override
            public void onButtonAdd(View view, int value) {
                //增加.1.更新数据
                goods.setNumber(value);
                //重新更新到本地
                ShoppingCartBiz.updateGoodsNumber(goods, value);
                //2.重新显示价格
                showTotalPrice();
            }
        });

        holder.tvDel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //本地数据库删除
                ShoppingCartBiz.delGood(goods);
                notifyItemRemoved(position);
            }
        });
    }

    //注意条目的数量
    @Override
    public int getItemCount() {
        return lists == null ? 0 : lists.size();
    }

    class ViewHolder extends RecyclerView.ViewHolder {

        NumberAddSubView numberAddSubView;
        ImageView ivGoods;
        TextView tvItemChild;
        TextView tvGoodsParam;
        TextView tvPriceNew;
        TextView tvNum;
        RelativeLayout rlEditStatus;
        TextView tvDel;
        LinearLayout llGoodInfo;
        CheckBox checkbox_single;

        public ViewHolder(final View convertView) {
            super(convertView);
            checkbox_single = (CheckBox) convertView.findViewById(R.id.checkbox_single);
            ivGoods = (ImageView) convertView.findViewById(R.id.ivGoods);
            llGoodInfo = (LinearLayout) convertView.findViewById(R.id.llGoodInfo);
            tvItemChild = (TextView) convertView.findViewById(R.id.tvItemChild);
            tvGoodsParam = (TextView) convertView.findViewById(R.id.tvGoodsParam);
            tvPriceNew = (TextView) convertView.findViewById(R.id.tvPriceNew);
            tvNum = (TextView) convertView.findViewById(R.id.tvNum);
            rlEditStatus = (RelativeLayout) convertView.findViewById(R.id.rlEditStatus);
            tvDel = (TextView) convertView.findViewById(R.id.tvDel);
            tvDel = (TextView) convertView.findViewById(R.id.tvDel);
            numberAddSubView = (NumberAddSubView) convertView.findViewById(R.id.numberAddSubView);

            checkbox_single.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    if (mOnItemClickListener != null) {
                        mOnItemClickListener.onItemClick(convertView, getLayoutPosition());
                    }
                }
            });

        }

    }
    //点击某个的监听
    public interface OnItemClickListener{
        //点击某个item的时候回调
        void onItemClick(View view,int position);
    }

    private OnItemClickListener mOnItemClickListener;

    public void setmOnItemClickListener(OnItemClickListener mOnItemClickListener) {
        this.mOnItemClickListener = mOnItemClickListener;
    }

}
