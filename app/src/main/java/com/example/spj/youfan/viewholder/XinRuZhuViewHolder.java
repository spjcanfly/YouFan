package com.example.spj.youfan.viewholder;

import android.content.Context;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.load.engine.DiskCacheStrategy;
import com.example.spj.youfan.R;
import com.example.spj.youfan.base.BaseRecyviewViewHolder;
import com.example.spj.youfan.bean.shouye.ShouYe;
import com.example.spj.youfan.bean.shouye.ShouYeModuleData;

import java.util.List;

/**
 * Created by spj on 2016/10/5.
 */
public class XinRuZhuViewHolder extends BaseRecyviewViewHolder{

    private final Context mContext;
    private final ImageView iv_xin_ren_big;
    private final RecyclerView recycleview;
    private List<ShouYeModuleData> datas;

    public XinRuZhuViewHolder(Context context, View itemView) {
        super(context, itemView);
        this.mContext = context;
        iv_xin_ren_big = (ImageView) itemView.findViewById(R.id.iv_xin_ren_big);
        recycleview = (RecyclerView) itemView.findViewById(R.id.recycleview);
    }

    @Override
    public void setData(ShouYe.DataBean.ModuleBean moduleBean) {
        datas = moduleBean.getData();
        //最上面的那个图片
        Glide.with(mContext).load(datas.get(0).getImg())
                .placeholder(R.drawable.ic_error_page)
                .diskCacheStrategy(DiskCacheStrategy.ALL).into(iv_xin_ren_big);
        MyXinRenAdapter adapter = new MyXinRenAdapter();
        recycleview.setAdapter(adapter);
        //注意recycleview必须要加上这一句
        recycleview.setLayoutManager(new GridLayoutManager(mContext, 3));
    }

    class MyXinRenAdapter extends RecyclerView.Adapter<MyXinRenAdapter.ViewHolder> {
        @Override
        public MyXinRenAdapter.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
            View convertView = LayoutInflater.from(mContext).inflate(R.layout.remen_pinpai_item, parent, false);
            return new ViewHolder(convertView);
        }

        @Override
        public void onBindViewHolder(MyXinRenAdapter.ViewHolder holder, int position) {
            //position=0的位置已经有图片了
            if(position < datas.size()-1) {
                //新人专享特有的
                String img = datas.get(position+1).getImg();
                Glide.with(mContext).load(img).
                        placeholder(R.drawable.ic_error_page)
                        .diskCacheStrategy(DiskCacheStrategy.ALL)
                        .into(holder.iv_pin_lei);
            }
        }

        //注意条目的数量
        @Override
        public int getItemCount() {
            return datas.size()-1;
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
