package com.example.spj.youfan.pager.detailpager;

import android.content.Context;
import android.os.Handler;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.load.engine.DiskCacheStrategy;
import com.chanven.lib.cptr.PtrClassicFrameLayout;
import com.chanven.lib.cptr.PtrDefaultHandler;
import com.chanven.lib.cptr.PtrFrameLayout;
import com.example.spj.youfan.R;
import com.example.spj.youfan.base.BaseSortViewPager;
import com.example.spj.youfan.bean.fenlei.FenLeiPinPai;
import com.example.spj.youfan.utils.CacheUtils;
import com.example.spj.youfan.utils.Constants;
import com.example.spj.youfan.utils.LogUtil;
import com.google.gson.Gson;
import com.zhy.http.okhttp.OkHttpUtils;
import com.zhy.http.okhttp.callback.StringCallback;

import java.util.List;

import okhttp3.Call;

/**
 * Created by spj on 2016/10/7.
 */
public class SortPinPaiDetailPager extends BaseSortViewPager{

    private String url;
    private RecyclerView recycleview;
    private List<FenLeiPinPai.DataBean> datas;
    private PtrClassicFrameLayout test_recycler_view_frame;
    private Handler handler = new Handler();

    public SortPinPaiDetailPager(Context context) {
        super(context);
    }

    @Override
    public View initView() {
        url = Constants.KIND_PINPAI;
        View view = View.inflate(mContext, R.layout.sort_pinpai_pager_recycle, null);
        recycleview = (RecyclerView) view.findViewById(R.id.recycleview);
        test_recycler_view_frame = (PtrClassicFrameLayout) view.findViewById(R.id.test_recycler_view_frame);
        //下拉刷新消失
        test_recycler_view_frame.setPtrHandler(new PtrDefaultHandler() {

            @Override
            public void onRefreshBegin(PtrFrameLayout frame) {
                handler.postDelayed(new Runnable() {
                    @Override
                    public void run() {
                        test_recycler_view_frame.refreshComplete();
                    }
                }, 1500);
            }
        });
        return view;
    }

    @Override
    public void initData() {
        super.initData();
        //把之前的缓存取出
        String saveJason = CacheUtils.getString(mContext, url);
        if (!TextUtils.isEmpty(saveJason)) {
            //解析数据和处理数据
            processData(saveJason);
        }
        //联网请求数据
        getDataFromNet();
    }

    private void processData(String json) {
        FenLeiPinPai bean = parsedJson(json);
        datas = bean.getData();
        if(datas != null && datas.size()>0) {
            //有数据
            MySortPinPaiAdapter adapter = new MySortPinPaiAdapter();
            recycleview.setAdapter(adapter);
            recycleview.setLayoutManager(new GridLayoutManager(mContext, 3));
            //自动刷新
            test_recycler_view_frame.postDelayed(new Runnable() {

                @Override
                public void run() {
                    test_recycler_view_frame.autoRefresh(true);
                }
            }, 150);
        }
    }

    //解析品牌的json
    private FenLeiPinPai parsedJson(String json) {
        return new Gson().fromJson(json, FenLeiPinPai.class);
    }

    private void getDataFromNet() {
        //使用OKhttp第三方封装库请求网络
        OkHttpUtils.get()
                .url(url)
                .id(100)
                .build()
                .execute(new StringCallback() {
                    @Override
                    public void onError(Call call, Exception e, int id) {
                        LogUtil.e("使用okhttp联网请求失败==分类品牌" + e.getMessage());
                    }

                    @Override
                    public void onResponse(String response, int id) {
                        LogUtil.e("使用okhttp联网请求数据成功==分类品牌" + response);
                        //缓存的数据放到sp存储中
                        CacheUtils.putString(mContext, url, response);
                        processData(response);

                    }
                });
    }

     class MySortPinPaiAdapter extends RecyclerView.Adapter<MySortPinPaiAdapter.ViewHolder>{
         @Override
         public MySortPinPaiAdapter.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
             View view = LayoutInflater.from(mContext).inflate(R.layout.sort_pinpai_pager_item, parent, false);
             return new ViewHolder(view);
         }

         @Override
         public void onBindViewHolder(MySortPinPaiAdapter.ViewHolder holder, int position) {

             Glide.with(mContext).load(datas.get(position).getLogo_img())
                     .placeholder(R.drawable.ic_error_page)
                     .diskCacheStrategy(DiskCacheStrategy.ALL)
                     .into(holder.iv_sort_pinpai);
         }

         @Override
         public int getItemCount() {
             return datas == null ? 0 : datas.size();
         }

          class ViewHolder extends RecyclerView.ViewHolder{

              ImageView iv_sort_pinpai;

              public ViewHolder(View itemView) {
                  super(itemView);
                  iv_sort_pinpai = (ImageView) itemView.findViewById(R.id.iv_sort_pinpai);
              }

          }
     }

}
