package com.example.spj.youfan.viewholder;

import android.content.Context;
import android.content.Intent;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.bumptech.glide.Glide;
import com.bumptech.glide.load.engine.DiskCacheStrategy;
import com.example.spj.youfan.R;
import com.example.spj.youfan.activity.shop.PinPaiActivity;
import com.example.spj.youfan.base.BaseRecyviewViewHolder;
import com.example.spj.youfan.bean.shouye.ShouYe;
import com.example.spj.youfan.bean.shouye.ShouYeModuleData;

import java.util.List;

/**
 * Created by spj on 2016/10/4.
 */
public class PinLeiViewHolder extends BaseRecyviewViewHolder{

    private static  Context mContext;
    private final TextView tv_shou_ye_chinese;
    private final TextView tv_shou_ye_english;
    private final ImageView iv_shou_ye_common;
    private final RecyclerView recycleview;
    private static List<ShouYeModuleData> datas;
    private static String c_title;

    public PinLeiViewHolder(Context context, View itemView) {
        super(context, itemView);
        this.mContext = context;
        tv_shou_ye_chinese = (TextView) itemView.findViewById(R.id.tv_shou_ye_chinese);
        tv_shou_ye_english = (TextView) itemView.findViewById(R.id.tv_shou_ye_english);
        iv_shou_ye_common = (ImageView) itemView.findViewById(R.id.iv_shou_ye_common);
        recycleview = (RecyclerView) itemView.findViewById(R.id.recycleview);
        //更多的那个图片出现
        iv_shou_ye_common.setVisibility(View.VISIBLE);
    }

    @Override
    public void setData(ShouYe.DataBean.ModuleBean moduleBean) {
        c_title = moduleBean.getC_title();
        datas = moduleBean.getData();
        //热门品类
        tv_shou_ye_chinese.setText(moduleBean.getC_title());
        tv_shou_ye_english.setText(moduleBean.getE_title());

        //准备适配器,要想复用得写在这里
        if("搭配趋势".equals(c_title)) {
            MyPinLeiAdapter adapter = new MyPinLeiAdapter();
            recycleview.setAdapter(adapter);
            //注意recycleview必须要加上这一句
            recycleview.setLayoutManager(new LinearLayoutManager(mContext, LinearLayoutManager.HORIZONTAL,false));
        }else{
            //热门品牌，热门品类
            MyPinLeiAdapter adapter = new MyPinLeiAdapter();
            recycleview.setAdapter(adapter);
            //注意recycleview必须要加上这一句
            recycleview.setLayoutManager(new GridLayoutManager(mContext, 3));
            //点击事件
            adapter.setmOnItemClickListener(new MyPinLeiAdapter.OnRecyclerViewItemClickListener() {
                @Override
                public void onItemClick(View view, ShouYeModuleData data) {
                    String name = data.getJump().getName();
                    Toast.makeText(mContext, "name"+name, Toast.LENGTH_SHORT).show();
                    Intent intent = new Intent(mContext, PinPaiActivity.class);
                    intent.putExtra("name",name);
                    mContext.startActivity(intent);
                }
            });
        }
    }

    static class MyPinLeiAdapter extends RecyclerView.Adapter<MyPinLeiAdapter.ViewHolder> implements View.OnClickListener{

        private OnRecyclerViewItemClickListener mOnItemClickListener = null;

        @Override
        public void onClick(View v) {
            if(mOnItemClickListener != null) {
                mOnItemClickListener.onItemClick(v, (ShouYeModuleData) v.getTag());
            }
        }

        //自定义Recycleview点击事件的监听
        public interface OnRecyclerViewItemClickListener {
            void onItemClick(View view, ShouYeModuleData data);
        }

        public void setmOnItemClickListener(OnRecyclerViewItemClickListener mOnItemClickListener) {
            this.mOnItemClickListener = mOnItemClickListener;
        }

        @Override
        public MyPinLeiAdapter.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
            if("热门品牌".equals(c_title)) {
                View convertView = LayoutInflater.from(mContext).inflate(R.layout.remen_pinpai_item, parent, false);
                //为创建的view注册点击事件
                convertView.setOnClickListener(this);
                return new ViewHolder(convertView);
            }else if("搭配趋势".equals(c_title)) {
                View convertView = LayoutInflater.from(mContext).inflate(R.layout.dapei_qushi_item, parent, false);
                return new ViewHolder(convertView);
            }else {
                //复用热门分类
                View convertView = LayoutInflater.from(mContext).inflate(R.layout.pin_lei_item, parent, false);
                return new ViewHolder(convertView);
            }
        }

        @Override
        public void onBindViewHolder(MyPinLeiAdapter.ViewHolder holder, int position) {
                String img = datas.get(position).getImg();
                Glide.with(mContext).load(img).
                        placeholder(R.drawable.fun_loading_0)
                        .diskCacheStrategy(DiskCacheStrategy.ALL)
                        .into(holder.iv_pin_lei);
            //将数据保存在itemView的Tag中，以便点击时进行获取
            holder.itemView.setTag(datas.get(position));
        }

        @Override
        public int getItemCount() {
            return datas.size();
        }



        class ViewHolder extends RecyclerView.ViewHolder {

            ImageView iv_pin_lei;

            public ViewHolder(View itemView) {
                super(itemView);
                iv_pin_lei = (ImageView) itemView.findViewById(R.id.iv_pin_lei);
            }

        }
    }
}
