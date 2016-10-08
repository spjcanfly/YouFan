package com.example.spj.youfan.pager;

import android.content.Context;
import android.graphics.Color;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.text.TextUtils;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import com.cjj.MaterialRefreshLayout;
import com.cjj.MaterialRefreshListener;
import com.example.spj.youfan.R;
import com.example.spj.youfan.activity.MainActivity;
import com.example.spj.youfan.adapter.ShouYeAdapter;
import com.example.spj.youfan.base.BasePager;
import com.example.spj.youfan.bean.shouye.CaiNiLike;
import com.example.spj.youfan.bean.shouye.ShouYe;
import com.example.spj.youfan.utils.CacheUtils;
import com.example.spj.youfan.utils.Constants;
import com.example.spj.youfan.utils.LogUtil;
import com.google.gson.Gson;
import com.zhy.http.okhttp.OkHttpUtils;
import com.zhy.http.okhttp.callback.StringCallback;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import okhttp3.Call;

/**
 * Created by spj on 2016/9/28.
 */
public class HomePager extends BasePager {

    private RecyclerView recycleview;
    private MaterialRefreshLayout refresh;
    private List<ShouYe.DataBean.ModuleBean> module;
    private ProgressBar progressbar;
    private TextView tv_nomedia;
    private ShouYeAdapter adapter;

    public HomePager(Context context) {
        super(context);
    }

    @Override
    public void initData() {

        View view = View.inflate(mContext, R.layout.home_pager, null);
        recycleview = (RecyclerView) view.findViewById(R.id.recycleview);
        refresh = (MaterialRefreshLayout) view.findViewById(R.id.refresh);
        progressbar = (ProgressBar) view.findViewById(R.id.progressbar);
        tv_nomedia = (TextView) view.findViewById(R.id.tv_nomedia);
        //把子试图添加到BasePager上的Fragment上,此处是坑
        if(flContent != null) {
            //解决重影
            flContent.removeAllViews();
            flContent.addView(view);
        }
        //初始化spinner控件
        initSpinner();

        //首先判断缓存中有没有
        getDataFromCache();

        //联网请求数据
        refresh.setMaterialRefreshListener(new MaterialRefreshListener() {
            @Override
            public void onRefresh(MaterialRefreshLayout materialRefreshLayout) {
                //最初的请求
                getDataFromNet();
            }

            @Override
            public void onRefreshLoadMore(MaterialRefreshLayout materialRefreshLayout) {
                //更多的请求
//                getMoreDataFromNet();
            }
        });

        super.initData();
    }

    private void getMoreDataFromNet() {
        //使用OKhttp第三方封装库请求网络,请求男生
        OkHttpUtils.get()
                .url(Constants.HOMECASH_MAN_MORE)
                .id(100)
                .build()
                .execute(new StringCallback() {
                    @Override
                    public void onError(Call call, Exception e, int id) {

                    }

                    @Override
                    public void onResponse(String response, int id) {
                        //缓存数据
                        CacheUtils.putString(mContext, Constants.HOMECASH_MAN_MORE, response);

                        processedMoreData(response);
                        refresh.finishRefreshLoadMore();
                    }
                });

    }

    //解析加载更多的数据,猜你喜欢的数据
    private void processedMoreData(String response) {
        //解析json数据
        CaiNiLike bean = parsedMoreJson(response);
        List<CaiNiLike.DataBean.ListBean> lists = bean.getData().getList();
        if(lists != null && lists.size()>0) {
            //有数据
        }
    }

    //解析json数据
    private CaiNiLike parsedMoreJson(String response) {
        return new Gson().fromJson(response,CaiNiLike.class);
    }

    private void getDataFromCache() {
        String saveJson = CacheUtils.getString(mContext, Constants.HOME_MEN);
        if(!TextUtils.isEmpty(saveJson)) {
            //缓存中有数据,直接解析
            processedData(saveJson);
        }

        getDataFromNet();
    }

    private void getDataFromNet() {
        //使用OKhttp第三方封装库请求网络,请求男生
        OkHttpUtils.get()
                .url(Constants.HOME_MEN)
                .id(100)
                .build()
                .execute(new MyStringCallback());
    }

    private void initSpinner() {
        final List<String> dataset = new ArrayList<>(Arrays.asList("男生", "女生", "生活"));
        nice_spinner.setTextColor(Color.BLACK);
        nice_spinner.setPadding(0, 0, 0, 0);
        nice_spinner.setCompoundDrawablePadding(10);
        nice_spinner.setTextSize(18);
        nice_spinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                Toast.makeText(mContext, "你点击的是"+dataset.get(position), Toast.LENGTH_SHORT).show();
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });
        nice_spinner.attachDataSource(dataset);
    }

    @Override
    public void initTitle() {
        ibTopMenu.setVisibility(View.VISIBLE);
        ibTopSet.setVisibility(View.GONE);
        ivTopSeacher.setVisibility(View.VISIBLE);
        ivTopBack.setVisibility(View.GONE);
        ivTopMail.setVisibility(View.GONE);
        tvTop.setVisibility(View.GONE);
        nice_spinner.setVisibility(View.VISIBLE);
        tablayout.setVisibility(View.INVISIBLE);
    }

    @Override
    public void initListener() {
        //切换侧滑
        ibTopMenu.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                MainActivity mainActivity = (MainActivity) mContext;
                //切换左侧的开和关
                mainActivity.getSlidingMenu().toggle();
            }
        });
    }

    private class MyStringCallback extends StringCallback {
        @Override
        public void onError(Call call, Exception e, int id) {
            LogUtil.e("使用okhttp联网请求失败==" + e.getMessage());
        }

        @Override
        public void onResponse(String response, int id) {
            LogUtil.e("使用okhttp联网请求数据成功==" + response);

            //缓存数据
            CacheUtils.putString(mContext, Constants.HOME_MEN, response);

            processedData(response);
            refresh.finishRefresh();
        }
    }

    //获得解析后的bean对象
    private void processedData(String response) {
        //解析json数据
        ShouYe bean = parsedJson(response);
        module = bean.getData().getModule();
        if(module != null && module.size()>0) {
            //有数据
            tv_nomedia.setVisibility(View.GONE);
            adapter = new ShouYeAdapter(mContext,module);
            recycleview.setAdapter(adapter);
            //注意recycleview必须要加上这一句
            recycleview.setLayoutManager(new LinearLayoutManager(mContext, LinearLayoutManager.VERTICAL, false));
        }else {
            //没有数据
            tv_nomedia.setVisibility(View.VISIBLE);
            tv_nomedia.setText("啥也没有啊，看看是否连上网络了");
        }
        progressbar.setVisibility(View.GONE);
    }

    //解析json数据
    private ShouYe parsedJson(String response) {
        return new Gson().fromJson(response,ShouYe.class);
    }
}
