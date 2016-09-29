package com.example.spj.youfan.uiself;

import android.content.Context;
import android.graphics.Rect;
import android.util.AttributeSet;
import android.view.MotionEvent;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.TranslateAnimation;
import android.widget.ScrollView;

import com.example.spj.youfan.utils.LogUtil;

/**
 * Created by spj on 2016/9/29.
 */
public class MyScrollView1 extends ScrollView {
    //    private int mMaxOverScrollY = 800;
    private View mChildView;


    public MyScrollView1(Context context) {
        super(context);
    }

    public MyScrollView1(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    public MyScrollView1(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }

    @Override
    protected void onFinishInflate() {
        super.onFinishInflate();
        if (getChildCount() > 0) {
            mChildView = getChildAt(0);
        }
    }

    private int iDownX, iDownY, lastX, lastY;

    @Override
    public boolean onInterceptTouchEvent(MotionEvent ev) {
        boolean isIntercept = false;
        int eventY = (int) ev.getRawY();
        int eventX = (int) ev.getRawX();
        switch (ev.getAction()) {
            case MotionEvent.ACTION_DOWN:
                flag = true;
                iDownX = eventX;
                iDownY = eventY;
                LogUtil.e("Intercept   idowny" + iDownY);
                break;
            case MotionEvent.ACTION_MOVE:

                int totalX = Math.abs(eventX - iDownX);
                int totalY = Math.abs(eventY - iDownY);
                LogUtil.e("Intercept  move totalY" + totalY);
                if (totalY > totalX && totalY > 10) {
                    isIntercept = true;
                }
                LogUtil.e("isIntercept + :" + isIntercept);

//
//                lastX = eventX;
                lastY = eventY;
                break;
            case MotionEvent.ACTION_UP:
                LogUtil.e("Intercept  up ");
                break;
        }

        return isIntercept;
    }

    private int downY;
    Rect normal = new Rect();
    boolean isAnimationState = false;

    boolean flag;

    @Override
    public boolean onTouchEvent(MotionEvent ev) {

        if (isAnimationState) {
            return super.onTouchEvent(ev);
        }

        switch (ev.getAction()) {
            case MotionEvent.ACTION_DOWN:

                //  downY = (int) ev.getRawY();
                LogUtil.e("down -----------:downy :" + downY + "idown :" + iDownY);
                // LogUtil.e("idowny == downy " + (downY == iDownY));
                break;
            case MotionEvent.ACTION_MOVE:

                if (flag) {
                    downY = (int) ev.getRawY();
                    LogUtil.e("downY : " + downY);
                    flag = false;
                }
                int moveY = (int) ev.getRawY();
                int offSetY = moveY - downY;
                LogUtil.e("move-----" + moveY);
                if (isNeedMove()) {
                    LogUtil.e("-----------------满足条件--------");
                    if (normal.isEmpty()) {
                        normal.set(mChildView.getLeft(), mChildView.getTop(), mChildView.getRight(), mChildView.getBottom());
                    }
                    // LogUtil.e("getTop =" + mChildView.getTop() + "");
                    //getLeft : 0getTop :0 getRight :1080 getBottom: 1455
                    mChildView.layout(mChildView.getLeft(), mChildView.getTop() + offSetY / 2,
                            mChildView.getRight(), mChildView.getBottom() + offSetY / 2);
                }

                downY = moveY;
                break;
            case MotionEvent.ACTION_UP:
                LogUtil.e("up---------- ");
                if (isNeedAnimation()) {
                    StartAnimation();
                }

                break;
        }
        return super.onTouchEvent(ev);
    }

    private void StartAnimation() {
        LogUtil.e("normal -- :" + normal.top + "top :" + mChildView.getTop());
        TranslateAnimation animation = new TranslateAnimation(0, 0, 0, normal.top - mChildView.getTop());
        animation.setDuration(200);
        mChildView.startAnimation(animation);
        // animation.setFillAfter(true);//错误的：不应该使用此种方式保留视图的最终位置,其实视图的最终位置还是没有变
        animation.setAnimationListener(new Animation.AnimationListener() {
            @Override
            public void onAnimationStart(Animation animation) {
                ///  LogUtil.e("开始动画--------");
                isAnimationState = true;
            }

            @Override
            public void onAnimationEnd(Animation animation) {
                // LogUtil.e("停止动画--------");
                //清除动画
                mChildView.clearAnimation();
                //将布局指定在最初的位置
                mChildView.layout((int) normal.left, (int) normal.top, (int) normal.right, (int) normal.bottom);
                //清除normal
                normal.setEmpty();
                isAnimationState = false;

            }

            @Override
            public void onAnimationRepeat(Animation animation) {

            }
        });

    }

    private boolean isNeedAnimation() {
        return !normal.isEmpty();
    }

    private boolean isNeedMove() {
        //获取子视图图的高度
        int measuredHeight = mChildView.getMeasuredHeight(); //1445
        //获取子视图的显示高度
        int height = this.getHeight();   //1365
        // LogUtil.e("measureHeight = " + measuredHeight + ",height = " + height);

        //得到子视图和scrollView的高度差值
        int offSet = measuredHeight - height;

        //获取垂直方向上移动的数值:上移为+ , 下移为-
        int scrollY = getScrollY();
        //Return the scrolled top position of this view. This is the top edge of the displayed part of your view.
        //当scrollY == 0 ;初始化的状态
        //scrollY == offSet : 滑动到底部  90
        LogUtil.e("scrollY ---------- " + scrollY);

        if (scrollY == 0 || scrollY == offSet) {
            return true;
        }

        return false;
    }

    /*@Override
    protected boolean overScrollBy(int deltaX, int deltaY, int scrollX, int scrollY,
                                   int scrollRangeX, int scrollRangeY, int maxOverScrollX,
                                   int maxOverScrollY, boolean isTouchEvent) {
        return super.overScrollBy(deltaX, deltaY, scrollX, scrollY, scrollRangeX,
                scrollRangeY, maxOverScrollX, mMaxOverScrollY, isTouchEvent);
    }*/
}

