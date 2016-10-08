package com.example.spj.youfan.viewholder;

import android.content.Context;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.load.engine.DiskCacheStrategy;
import com.example.spj.youfan.R;
import com.example.spj.youfan.base.BaseRecyviewViewHolder;
import com.example.spj.youfan.bean.ShouYe;
import com.example.spj.youfan.bean.ShouYeModuleData;

import java.util.List;

/**
 * Created by spj on 2016/10/4.
 * 上装，鞋子
 */
public class ShangZhuangViewHolder extends BaseRecyviewViewHolder{

    private final Context mContext;
    private final TextView tv_shou_ye_chinese;
    private final TextView tv_shou_ye_english;
    private final ImageView iv_shou_ye_common;
    private final ImageView iv_xin_ren_big;
    private List<ShouYeModuleData> datas;
    private final RecyclerView recycleview;

    public ShangZhuangViewHolder(Context context, View itemView) {
        super(context, itemView);
        this.mContext = context;
        tv_shou_ye_chinese = (TextView) itemView.findViewById(R.id.tv_shou_ye_chinese);
        tv_shou_ye_english = (TextView) itemView.findViewById(R.id.tv_shou_ye_english);
        iv_shou_ye_common = (ImageView) itemView.findViewById(R.id.iv_shou_ye_common);
        iv_xin_ren_big = (ImageView) itemView.findViewById(R.id.iv_xin_ren_big);
        //更多的那个图片消失
        iv_shou_ye_common.setVisibility(View.GONE);
        recycleview = (RecyclerView) itemView.findViewById(R.id.recycleview);
    }

    @Override
    public void setData(ShouYe.DataBean.ModuleBean moduleBean) {
        datas = moduleBean.getData();
        tv_shou_ye_chinese.setText(moduleBean.getC_title());
        tv_shou_ye_english.setText(moduleBean.getE_title());
        if(datas != null && datas.get(0) != null ) {
            Glide.with(mContext).load(datas.get(0).getImg())
                    .placeholder(R.drawable.ic_error_page)
                    .diskCacheStrategy(DiskCacheStrategy.ALL).into(iv_xin_ren_big);
        }
        MyShangZhuangAdapter adapter = new MyShangZhuangAdapter();
        recycleview.setAdapter(adapter);
        //注意recycleview必须要加上这一句
        recycleview.setLayoutManager(new LinearLayoutManager(mContext, LinearLayoutManager.HORIZONTAL, false));
    }

    class MyShangZhuangAdapter extends RecyclerView.Adapter<MyShangZhuangAdapter.ViewHolder> {
        @Override
        public MyShangZhuangAdapter.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
            View convertView = LayoutInflater.from(mContext).inflate(R.layout.shang_zhuang_item, parent, false);
            return new ViewHolder(convertView);
        }

        @Override
        public void onBindViewHolder(MyShangZhuangAdapter.ViewHolder holder, int position) {
            //position=0的位置已经有图片了
            if(position < datas.size()-1) {
                String img = datas.get(position+1).getImg();
                    Glide.with(mContext).load(img).
                            placeholder(R.drawable.ic_error_page)
                            .diskCacheStrategy(DiskCacheStrategy.ALL)
                            .into(holder.iv_xin_ren_small);
            }
        }

        //注意条目的数量
        @Override
        public int getItemCount() {
            return datas.size()-1;
        }

        class ViewHolder extends RecyclerView.ViewHolder {

            ImageView iv_xin_ren_small;

            public ViewHolder(View itemView) {
                super(itemView);
                iv_xin_ren_small = (ImageView) itemView.findViewById(R.id.iv_xin_ren_small);
            }

        }
    }
}
