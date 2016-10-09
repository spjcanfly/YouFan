package com.example.spj.youfan.pager.detailpager;

import android.content.Context;
import android.graphics.Paint;
import android.os.Handler;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.load.engine.DiskCacheStrategy;
import com.chanven.lib.cptr.PtrClassicFrameLayout;
import com.chanven.lib.cptr.PtrDefaultHandler;
import com.chanven.lib.cptr.PtrFrameLayout;
import com.example.spj.youfan.R;
import com.example.spj.youfan.bean.PinPaiDetail;
import com.example.spj.youfan.utils.Constants;
import com.example.spj.youfan.utils.LogUtil;
import com.google.gson.Gson;
import com.zhy.http.okhttp.OkHttpUtils;
import com.zhy.http.okhttp.callback.StringCallback;

import java.util.List;

import okhttp3.Call;

/**
 * Created by spj on 2016/10/9.
 */
public class BrandDetailPager {
    private final Context mContext;
    private final String mName;
    public View rootView;
    private RecyclerView recycleview;
    private PtrClassicFrameLayout test_recycler_view_frame;
    private Handler handler = new Handler();
    private MyBrandAdapter adapter;
    private List<PinPaiDetail.ResultsBean> results;

    public BrandDetailPager(Context context, String name) {
        this.mContext = context;
        this.mName = name;
        rootView = initView();
    }

    private View initView() {
        View view = View.inflate(mContext, R.layout.brand_detail_recycle, null);
        recycleview = (RecyclerView) view.findViewById(R.id.recycleview);
        test_recycler_view_frame = (PtrClassicFrameLayout) view.findViewById(R.id.test_recycler_view_frame);
        test_recycler_view_frame.setPtrHandler(new PtrDefaultHandler() {

            @Override
            public void onRefreshBegin(PtrFrameLayout frame) {
                handler.postDelayed(new Runnable() {
                    @Override
                    public void run() {
                        //下拉刷新完成后消失
                        test_recycler_view_frame.refreshComplete();
                        test_recycler_view_frame.setLoadMoreEnable(true);
                    }
                }, 1500);
            }
        });

        return view;
    }

    public void initData() {

        String url = null;
        if ("上新".equals(mName) || "热销".equals(mName)) {
            url = Constants.BRANDDETAIL_PRE + mName + Constants.BRANDDETAIL_TAIL;
        } else if ("价格".equals(mName)) {
            url = Constants.BRANDDETAIL_PRICE_PRE + mName + Constants.BRANDDETAIL_PRICE_TAIL;
        }

        //使用OKhttp第三方封装库请求网络
        OkHttpUtils.get()
                .url(url)
                .id(100)
                .build()
                .execute(new StringCallback() {
                    @Override
                    public void onError(Call call, Exception e, int id) {
                        LogUtil.e("使用okhttp联网请求失败==灵感资讯" + e.getMessage());
                    }

                    @Override
                    public void onResponse(String response, int id) {
                        LogUtil.e("使用okhttp联网请求数据成功==灵感资讯" + response);
                        processData(response);
                    }
                });
    }

    private void processData(String json) {
        PinPaiDetail bean = parsedJson(json);
        results = bean.getResults();
        if(results != null && results.size()>0) {
            //有数据
            adapter = new MyBrandAdapter();
            recycleview.setAdapter(adapter);
            recycleview.setLayoutManager(new GridLayoutManager(mContext, 2));
        }
    }

    //解析json
    private PinPaiDetail parsedJson(String json) {
        return new Gson().fromJson(json, PinPaiDetail.class);
    }

     class MyBrandAdapter extends RecyclerView.Adapter<MyBrandAdapter.ViewHolder>{
         @Override
         public MyBrandAdapter.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
             View itemView = LayoutInflater.from(mContext).inflate(R.layout.brand_detail_item, parent, false);
             return new ViewHolder(itemView);
         }

         @Override
         public void onBindViewHolder(MyBrandAdapter.ViewHolder holder, int position) {
             String name = results.get(position).getClsInfo().getName();
             holder.tv_like_name.setText(name);

             String brand = results.get(position).getClsInfo().getBrand();
             holder.tv_like_brand.setText(brand);

             int sale_price = results.get(position).getClsInfo().getSale_price();
             holder.tv_like_price_now.setText("￥" + sale_price);

             int price = results.get(position).getClsInfo().getPrice();
             holder.tv_like_price_market.getPaint().setFlags(Paint.STRIKE_THRU_TEXT_FLAG);
             holder.tv_like_price_market.setText("￥" + price);

             String img = results.get(position).getClsInfo().getMainImage();
             Glide.with(mContext).load(img).placeholder(R.drawable.fun_loading_0).diskCacheStrategy(DiskCacheStrategy.ALL).into(holder.iv_xin_ren_small);


             String imgUrl = results.get(position).getProdClsTag().get(0).getTagUrl();
             Glide.with(mContext).load(imgUrl).placeholder(R.drawable.fun_loading_0).diskCacheStrategy(DiskCacheStrategy.ALL).into(holder.iv_brand_sale);

             String imgUrl2 = results.get(position).getProdClsTag().get(1).getTagUrl();
             Glide.with(mContext).load(imgUrl2).placeholder(R.drawable.fun_loading_0).diskCacheStrategy(DiskCacheStrategy.ALL).into(holder.iv_brand_new);

         }

         @Override
         public int getItemCount() {
             return results.size();
         }

          class ViewHolder extends RecyclerView.ViewHolder {

              ImageView iv_brand_new;
              ImageView iv_brand_sale;
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
                  iv_brand_sale = (ImageView) itemView.findViewById(R.id.iv_brand_sale);
                  iv_brand_new = (ImageView) itemView.findViewById(R.id.iv_brand_new);
              }
          }
     }
}
