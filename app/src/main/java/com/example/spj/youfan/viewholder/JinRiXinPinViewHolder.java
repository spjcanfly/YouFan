package com.example.spj.youfan.viewholder;

import android.content.Context;
import android.content.Intent;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.load.engine.DiskCacheStrategy;
import com.example.spj.youfan.R;
import com.example.spj.youfan.activity.shop.ShopDetailActivity;
import com.example.spj.youfan.base.BaseRecyviewViewHolder;
import com.example.spj.youfan.bean.shouye.ShouYe;
import com.example.spj.youfan.bean.shouye.ShouYeModuleData;

import java.util.List;

/**
 * Created by spj on 2016/9/30.
 * 今日新品的显示，嵌套一个Recycleview
 */
public class JinRiXinPinViewHolder extends BaseRecyviewViewHolder {

    private final RecyclerView recyclerView;
    private static Context mContext;
    private static List<ShouYeModuleData> datas;


    public JinRiXinPinViewHolder(Context context, View itemView) {
        super(context, itemView);
        this.mContext = context;
        recyclerView = (RecyclerView) itemView.findViewById(R.id.recycleview);
    }

    @Override
    public void setData(ShouYe.DataBean.ModuleBean moduleBean) {
        datas = moduleBean.getData();
        MyJinRiAdapter adapter = new MyJinRiAdapter();
        recyclerView.setAdapter(adapter);
        //注意recycleview必须要加上这一句
        recyclerView.setLayoutManager(new GridLayoutManager(mContext, 4));
        //自定义Recyclevieew的点击事件
        adapter.setmOnItemClickListener(new MyJinRiAdapter.OnRecyclerViewItemClickListener() {
            @Override
            public void onItemClick(View view, ShouYeModuleData data) {
                String title = data.getTitle();
                String url = data.getJump().getUrl();
                Intent intent = new Intent(mContext,ShopDetailActivity.class);
                intent.putExtra("title",title);
                intent.putExtra("url",url);
                mContext.startActivity(intent);
            }
        });
    }

    static class MyJinRiAdapter extends RecyclerView.Adapter<MyJinRiAdapter.ViewHolder> implements View.OnClickListener{

        private OnRecyclerViewItemClickListener mOnItemClickListener = null;

        //为创建的view注册点击事件
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
        public MyJinRiAdapter.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
            View convertView = LayoutInflater.from(mContext).inflate(R.layout.jinri_xinpin_item, parent, false);
            //为创建的view注册点击事件
            convertView.setOnClickListener(this);
            return new ViewHolder(convertView);
        }

        @Override
        public void onBindViewHolder(MyJinRiAdapter.ViewHolder holder, int position) {

            String title = datas.get(position).getTitle();
            String img = datas.get(position).getImg();
            holder.tv_jinri.setText(title);
            Glide.with(mContext).load(img).placeholder(R.drawable.ic_error_page).diskCacheStrategy(DiskCacheStrategy.ALL).into(holder.iv_jinri);
            //将数据保存在itemView的Tag中，以便点击时进行获取
            holder.itemView.setTag(datas.get(position));
        }

        @Override
        public int getItemCount() {
            return datas.size();
        }

        class ViewHolder extends RecyclerView.ViewHolder {

            ImageView iv_jinri;
            TextView tv_jinri;

            public ViewHolder(View itemView) {
                super(itemView);
                iv_jinri = (ImageView) itemView.findViewById(R.id.iv_jinri);
                tv_jinri = (TextView) itemView.findViewById(R.id.tv_jinri);

            }

        }
    }
}
