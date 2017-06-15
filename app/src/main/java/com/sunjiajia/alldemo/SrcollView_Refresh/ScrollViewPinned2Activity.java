package com.sunjiajia.alldemo.SrcollView_Refresh;

import android.app.Activity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.TextView;

import com.sunjiajia.alldemo.view.MyScrollView;
import com.sunjiajia.androidnewwidgetsdemo.R;

public class ScrollViewPinned2Activity extends Activity implements MyScrollView.MyScrollListener {
    public static final String TAG = "ScrollViewPinnedActivity";
    private int topDistance;
    private int height;
    private TextView move, head, stop;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.scrollview_pinned2);
        //初始化控件
        init();
    }

    private void init() {
        move = (TextView) findViewById(R.id.move);
        head = (TextView) findViewById(R.id.head);
        stop = (TextView) findViewById(R.id.stop);
        ((MyScrollView)findViewById(R.id.scv)).setMyScrollListener(this);
    }

    @Override
    public void onWindowFocusChanged(boolean hasFocus) {
        super.onWindowFocusChanged(hasFocus);
        if (hasFocus) {
            topDistance = move.getTop();  //获取位置3，即内部绿色栏的顶部到布局顶部的距离
            height = head.getMeasuredHeight();  //获取位置2，不就是搜索栏的高度么，啊哈哈哈，是不是很机智，当然你也可以用getButtom，一样的，看你自己
        }
    }

    @Override
    public void sendDistanceY(int scrollY) {
        Log.d("scroll", "----------------------height:" + height);
        if (scrollY >= topDistance - height) {  //如果滑动的距离大于或等于位置3到位置2的距离，那么说明内部绿色的顶部在位置2上面了，我们需要显示外部绿色栏了
            stop.setVisibility(View.VISIBLE);
        } else {  //反之隐藏
            stop.setVisibility(View.GONE);
        }
    }
}
