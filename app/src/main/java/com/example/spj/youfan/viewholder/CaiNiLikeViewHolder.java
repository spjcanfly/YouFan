package com.example.spj.youfan.viewholder;

import android.content.Context;
import android.graphics.Paint;
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
import com.example.spj.youfan.base.BaseRecyviewViewHolder;
import com.example.spj.youfan.bean.CaiNiLike;
import com.example.spj.youfan.bean.ShouYe;
import com.example.spj.youfan.bean.ShouYeModuleData;
import com.example.spj.youfan.utils.CacheUtils;
import com.example.spj.youfan.utils.Constants;
import com.example.spj.youfan.utils.LogUtil;
import com.google.gson.Gson;
import com.zhy.http.okhttp.OkHttpUtils;
import com.zhy.http.okhttp.callback.StringCallback;

import java.util.List;

import okhttp3.Call;

/**
 * Created by spj on 2016/10/5.
 */
public class CaiNiLikeViewHolder extends BaseRecyviewViewHolder{

    private final Context mContext;
    private final TextView tv_shou_ye_chinese;
    private final TextView tv_shou_ye_english;
    private final ImageView iv_shou_ye_common;
    private final RecyclerView recycleview;
    private List<ShouYeModuleData> datas;
    private MyCaiNiLikeAdapter adapter;
    private List<CaiNiLike.DataBean.ListBean> lists;

    public CaiNiLikeViewHolder(Context context, View itemView) {
        super(context, itemView);
        this.mContext = context;
        tv_shou_ye_chinese = (TextView) itemView.findViewById(R.id.tv_shou_ye_chinese);
        tv_shou_ye_english = (TextView) itemView.findViewById(R.id.tv_shou_ye_english);
        iv_shou_ye_common = (ImageView) itemView.findViewById(R.id.iv_shou_ye_common);
        //更多的那个图片消失
        iv_shou_ye_common.setVisibility(View.GONE);
        recycleview = (RecyclerView) itemView.findViewById(R.id.recycleview);
    }

    @Override
    public void setData(ShouYe.DataBean.ModuleBean moduleBean) {
        datas = moduleBean.getData();
        tv_shou_ye_chinese.setText(moduleBean.getC_title());
        tv_shou_ye_english.setText(moduleBean.getE_title());

        //猜你喜欢的数据准备
        getDataFromNet();
    }

    private void getDataFromNet() {
        //使用OKhttp第三方封装库请求网络,请求男生
        OkHttpUtils.get()
                .url(Constants.HOMECASH_MAN_MORE)
                .id(100)
                .build()
                .execute(new MyStringCallback());
    }

    class MyCaiNiLikeAdapter extends RecyclerView.Adapter<MyCaiNiLikeAdapter.ViewHolder> {
        @Override
        public MyCaiNiLikeAdapter.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
            View convertView = LayoutInflater.from(mContext).inflate(R.layout.caini_xihuan_item, parent, false);
            return new ViewHolder(convertView);
        }

        @Override
        public void onBindViewHolder(MyCaiNiLikeAdapter.ViewHolder holder, int position) {
            String product_url = lists.get(position).getProduct_url();
            Glide.with(mContext).load(product_url).
                    placeholder(R.drawable.ic_error_page)
                    .diskCacheStrategy(DiskCacheStrategy.ALL)
                    .into(holder.iv_xin_ren_small);
            String brand_name = lists.get(position).getBrandName();
            holder.tv_like_brand.setText(brand_name);
            String product_name = lists.get(position).getProduct_name();
            holder.tv_like_name.setText(product_name);
            String market_price = lists.get(position).getMarket_price();
            String[] split = market_price.split("\\.");
            //设置中划线
            holder.tv_like_price_market.getPaint().setFlags(Paint.STRIKE_THRU_TEXT_FLAG);
            holder.tv_like_price_market.setText("￥"+split[0]);
            String price = lists.get(position).getPrice();
            String[] split1 = price.split("\\.");
            holder.tv_like_price_now.setText("￥"+split1[0]);
        }

        //注意条目的数量
        @Override
        public int getItemCount() {
            return lists.size();
        }

        class ViewHolder extends RecyclerView.ViewHolder {

            ImageView iv_xin_ren_small;
            TextView tv_like_brand;
            TextView tv_like_name;
            TextView tv_like_price_now;
            TextView tv_like_price_market;

            public ViewHolder(View itemView) {
                super(itemView);
                iv_xin_ren_small = (ImageView) itemView.findViewById(R.id.iv_xin_ren_small);
                tv_like_brand = (TextView) itemView.findViewById(R.id.tv_like_brand);
                tv_like_name = (TextView) itemView.findViewById(R.id.tv_like_name);
                tv_like_price_now = (TextView) itemView.findViewById(R.id.tv_like_price_now);
                tv_like_price_market = (TextView) itemView.findViewById(R.id.tv_like_price_market);
            }

        }
    }

    private class MyStringCallback extends StringCallback {
        @Override
        public void onError(Call call, Exception e, int id) {
            LogUtil.e("使用okhttp联网请求失败==首页猜你喜欢" + e.getMessage());
        }

        @Override
        public void onResponse(String response, int id) {
            LogUtil.e("使用okhttp联网请求数据成功==首页猜你喜欢" + response);
            //缓存数据
            CacheUtils.putString(mContext, Constants.HOMECASH_MAN_MORE, response);
            processedData(response);
        }
    }

    //获得解析后的bean对象
    private void processedData(String response) {
        //解析json数据
        CaiNiLike bean = parsedJson(response);
        lists = bean.getData().getList();
        if(lists != null && lists.size()>0) {
            //有数据
            adapter = new MyCaiNiLikeAdapter();
            recycleview.setAdapter(adapter);
            //注意recycleview必须要加上这一句
            recycleview.setLayoutManager(new GridLayoutManager(mContext, 2));
        }

    }

    //解析json数据
    private CaiNiLike parsedJson(String response) {
        return new Gson().fromJson(response,CaiNiLike.class);
    }
}
