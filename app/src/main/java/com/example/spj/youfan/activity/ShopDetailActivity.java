package com.example.spj.youfan.activity;

import android.app.Activity;
import android.os.Bundle;
import android.support.design.widget.TabLayout;
import android.view.View;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import com.example.spj.youfan.R;

import org.angmarch.views.NiceSpinner;

public class ShopDetailActivity extends Activity implements View.OnClickListener{


    private ImageView ibTopMenu;
    private ImageView ibTopSet;
    private ImageView ivTopMail;
    private ImageView ivTopSeacher;
    private ImageView ivTopBack;
    private TextView tvTop;
    private NiceSpinner nice_spinner;
    private TabLayout tablayout;
    private WebView webview;
    private ProgressBar pgb;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_shop_detail);
        //找控件
        initView();
        //处理显示和隐藏
        showOrHide();
        //接收传递过来的数据，并且设置
        initData();
    }

    private void initData() {
        //得到顶部的标题
        final String title = getIntent().getStringExtra("title");
        tvTop.setText("页面正在加载中...");
        //得到要去的网页地址
        String url = getIntent().getStringExtra("url");
        WebSettings webSettings = webview.getSettings();
        webSettings.setJavaScriptEnabled(true);
        //设置双击变大变小
        webSettings.setUseWideViewPort(true);
        //增加缩放按钮
        webSettings.setBuiltInZoomControls(true);
        //不让从当前网页跳到系统的浏览器
        webview.setWebViewClient(new WebViewClient() {
            //当加载页面完成的时候回调，将进度条消失
            @Override
            public void onPageFinished(WebView view, String url) {
                super.onPageFinished(view, url);
                tvTop.setText(title);
                pgb.setVisibility(View.GONE);
            }
        });
        webview.loadUrl(url);
    }

    private void showOrHide() {
        ibTopMenu.setVisibility(View.GONE);
        ibTopSet.setVisibility(View.GONE);
        ivTopSeacher.setVisibility(View.GONE);
        ivTopBack.setVisibility(View.VISIBLE);
        ivTopMail.setVisibility(View.VISIBLE);
        tvTop.setVisibility(View.VISIBLE);
        nice_spinner.setVisibility(View.GONE);
        tablayout.setVisibility(View.INVISIBLE);
    }

    private void initView() {
        ibTopMenu = (ImageView) findViewById(R.id.ib_top_menu);
        ibTopSet = (ImageView) findViewById(R.id.ib_top_set);
        ivTopMail = (ImageView) findViewById(R.id.iv_top_mail);
        ivTopSeacher = (ImageView) findViewById(R.id.iv_top_seacher);
        ivTopBack = (ImageView) findViewById(R.id.iv_top_back);
        tvTop = (TextView) findViewById(R.id.tv_top);
        nice_spinner = (NiceSpinner) findViewById(R.id.nice_spinner);
        tablayout = (TabLayout) findViewById(R.id.tablayout);
        webview = (WebView) findViewById(R.id.webview);
        pgb = (ProgressBar) findViewById(R.id.pb_loading);

        ivTopBack.setOnClickListener(this);
        ivTopMail.setOnClickListener(this);
    }


    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.iv_top_back:
                //结束这个页面
                finish();
                break;
            case R.id.iv_top_mail:
                //分享
                Toast.makeText(ShopDetailActivity.this, "分享给谁?", Toast.LENGTH_SHORT).show();
                break;
        }
    }
}
