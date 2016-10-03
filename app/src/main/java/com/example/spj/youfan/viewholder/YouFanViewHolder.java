package com.example.spj.youfan.viewholder;

import android.content.Context;
import android.os.Handler;
import android.os.Message;
import android.view.Gravity;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.AnimationUtils;
import android.widget.FrameLayout;
import android.widget.TextSwitcher;
import android.widget.TextView;
import android.widget.ViewSwitcher;

import com.example.spj.youfan.R;
import com.example.spj.youfan.base.BaseRecyviewViewHolder;
import com.example.spj.youfan.bean.ShouYe;
import com.example.spj.youfan.bean.ShouYeModuleData;

import java.util.List;
import java.util.Timer;
import java.util.TimerTask;

/**
 * Created by spj on 2016/9/30.
 * 有范公告
 */
public class YouFanViewHolder extends BaseRecyviewViewHolder implements ViewSwitcher.ViewFactory{

    private final TextSwitcher ts_gong_gao;
    private final Context mContext;
    private int id=0;

    private Handler mHandler = new Handler(){
        @Override
        public void handleMessage(Message msg) {
            switch (msg.what) {
                case 1 :
                    //更新id
                    id= next();
                    //更新文本的内容
                    updateText();
                    break;
            }
        }
    };

    private int next() {
        int flag =id+1;
        if(flag > datas.size()-1) {
            flag = flag - datas.size();
        }
        return flag;
    }

    private List<ShouYeModuleData> datas;

    private void updateText() {
        ts_gong_gao.setText(datas.get(id).getTitle());
    }

    public YouFanViewHolder(Context context, View itemView) {
        super(context, itemView);
        this.mContext = context;

        ts_gong_gao = (TextSwitcher) itemView.findViewById(R.id.ts_gong_gao);
        //设置动画
        init();

        Timer timer = new Timer();
        timer.scheduleAtFixedRate(new MyTask(), 1, 3000);//每3秒更新
    }

    private void init() {
        ts_gong_gao.setFactory(this);
        ts_gong_gao.setInAnimation(AnimationUtils.loadAnimation(mContext,android.R.anim.fade_in));
        ts_gong_gao.setOutAnimation(AnimationUtils.loadAnimation(mContext,android.R.anim.fade_out));
    }

    @Override
    public void setData(ShouYe.DataBean.ModuleBean moduleBean) {
        //获得网络上字段的数据
        datas = moduleBean.getData();
    }

    //首先先把这些显示在页面上
    @Override
    public View makeView() {
        TextView tv = new TextView(mContext);
        FrameLayout.LayoutParams lp = new FrameLayout.LayoutParams(ViewGroup.LayoutParams.WRAP_CONTENT,ViewGroup.LayoutParams.WRAP_CONTENT);
        lp.gravity = Gravity.CENTER;
        tv.setLayoutParams(lp);
        return tv;
    }


    private class MyTask extends TimerTask {
        @Override
        public void run() {
            Message message = new Message();
            message.what = 1;
            mHandler.sendMessage(message);
        }
    }
}
