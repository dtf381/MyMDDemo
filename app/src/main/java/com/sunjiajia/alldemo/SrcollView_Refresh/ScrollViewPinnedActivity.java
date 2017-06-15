package com.sunjiajia.alldemo.SrcollView_Refresh;

import android.app.Activity;
import android.os.Bundle;
import android.util.Log;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.sunjiajia.alldemo.view.MyScrollViewPinned;
import com.sunjiajia.androidnewwidgetsdemo.R;

/**
 * Created by mk on 2016/12/27.
 */

//getLocationOnScreen ，计算该视图在全局坐标系中的x，y值，（注意这个值是要从屏幕顶端算起，也就是索包括了通知栏的高度）//获取在当前屏幕内的绝对坐标
//
//        getLocationInWindow ，计算该视图在它所在的widnow的坐标x，y值，//获取在整个窗口内的绝对坐标 (不是很理解= =、)


public class ScrollViewPinnedActivity extends Activity implements MyScrollViewPinned.OnScrollListener {
    public static final String TAG = "ScrollViewPinnedActivity";

    private EditText search_edit;
    private MyScrollViewPinned myScrollView;
    private int searchLayoutTop;

    LinearLayout search01, search02;
    RelativeLayout rlayout;
    TextView iv;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.scrollview_pinned);
        //初始化控件
        init();
    }

    private void init() {
        search_edit = (EditText) findViewById(R.id.search_edit);
        myScrollView = (MyScrollViewPinned) findViewById(R.id.myScrollView);
        search01 = (LinearLayout) findViewById(R.id.search01);
        search02 = (LinearLayout) findViewById(R.id.search02);
        rlayout = (RelativeLayout) findViewById(R.id.rlayout);
        iv = (TextView) findViewById(R.id.iv);

        myScrollView.setOnScrollListener(this);

    }

    @Override
    public void onWindowFocusChanged(boolean hasFocus) {
        super.onWindowFocusChanged(hasFocus);
        if (hasFocus) {
            searchLayoutTop = rlayout.getBottom();//获取searchLayout的顶部位置

//            int[] outLocation = new int[2];
//            search02.getLocationInWindow(outLocation);//获取searchLayout的顶部位置
//            searchLayoutTop = outLocation[1] - search02.getMeasuredHeight();

        }
    }

    //监听滚动Y值变化，通过addView和removeView来实现悬停效果
    @Override
    public void onScroll(int scrollY) {
        Log.e(TAG, "scrollY = " + scrollY);
        if (scrollY >= searchLayoutTop) {
            if (search_edit.getParent() != search01) {
                search02.removeView(search_edit);
                search01.addView(search_edit);
            }
        } else {
            if (search_edit.getParent() != search02) {
                search01.removeView(search_edit);
                search02.addView(search_edit);
            }
        }
    }
}
