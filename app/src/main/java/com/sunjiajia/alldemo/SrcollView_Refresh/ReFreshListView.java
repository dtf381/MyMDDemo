package com.sunjiajia.alldemo.SrcollView_Refresh;

import android.content.Context;
import android.os.Build;
import android.support.annotation.RequiresApi;
import android.util.AttributeSet;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.Animation;
import android.view.animation.RotateAnimation;
import android.widget.AbsListView;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.ProgressBar;
import android.widget.TextView;

import com.sunjiajia.androidnewwidgetsdemo.R;

import java.text.SimpleDateFormat;

/**
 * Created by mk on 2017/2/15.
 */

public class ReFreshListView extends ListView implements AbsListView.OnScrollListener {
    private static final String TAG = "ReFreshListView";

    public static final String FOOTER = "FOOTER";
    public static final String HEADER = "HEADER";
    private static final int PULL = 1;

    private static final int NONE = 0;
    private static final int REFRESHING = 2;
    private static final int SPACE = 20;
    private RotateAnimation downAnimation;
    private RotateAnimation upAnimation;
    private Context context;
    private LayoutInflater inflater;
    private View footer;
    private View header;
    private int headerContentInitialHeight;
    private int headerContentHeight;
    private OnRefreshListener onRefreshListener;
    private boolean loadEnable;
    private OnLoadListener onLoadListener;
    private int firstVisibleItem;
    private boolean isLoadingMore = false;
    private int startY;
    private int state;
    private ImageView iv_pull;
    private int footerViewHeight;
    private ProgressBar mProgressBar;
    private TextView tvState;
    private TextView tvLastUpdateTime;
    private boolean isScrollToBottom;


    public ReFreshListView(Context context) {
        super(context);
        initView(context);
    }

    public ReFreshListView(Context context, AttributeSet attrs) {
        super(context, attrs);
        initView(context);
    }

    public ReFreshListView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        initView(context);
    }

    @RequiresApi(api = Build.VERSION_CODES.LOLLIPOP)
    public ReFreshListView(Context context, AttributeSet attrs, int defStyleAttr, int defStyleRes) {
        super(context, attrs, defStyleAttr, defStyleRes);
        initView(context);
    }

    private void initView(Context context) {
        initHeaderView();
        initFooterView();
        this.setOnScrollListener(this);
    }

    private void initHeaderView() {
        header = View.inflate(getContext(), R.layout.refreshlistview_pull_to_refresh_header, null);
        iv_pull = (ImageView) header.findViewById(R.id.iv_pull);
        mProgressBar = (ProgressBar) header.findViewById(R.id.pb_listview_header);
        tvState = (TextView) header.findViewById(R.id.tv_listview_header_state);
        tvLastUpdateTime = (TextView) header.findViewById(R.id.tv_listView_header_last_update_time);
        //设置最后刷新时间
        tvLastUpdateTime.setText("最后刷新时间：" + getLastUpdateTime());
        header.measure(0, 0);//系统会帮我们测量出headerView的高度
        headerContentHeight = header.getMeasuredHeight();
        header.setPadding(0, -headerContentHeight, 0, 0);
        this.addHeaderView(header, HEADER, true);//向ListView的顶部添加一个view对象
//        initAnimation();
    }

    private void initAnimation() {
        upAnimation = new RotateAnimation(0f, -180f, Animation.RELATIVE_TO_SELF, 0.5f, Animation.RELATIVE_TO_SELF, 0.5f);
        upAnimation.setDuration(500);
        upAnimation.setFillAfter(true);//动画结束后，停留在结束的位置上
        upAnimation = new RotateAnimation(-180f, -360f, Animation.RELATIVE_TO_SELF, 0.5f, Animation.RELATIVE_TO_SELF, 0.5f);
        downAnimation.setDuration(500);
        downAnimation.setFillAfter(true);
    }

    private String getLastUpdateTime() {
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        return sdf.format(System.currentTimeMillis());
    }

    private void initFooterView() {
        footer = View.inflate(getContext(), R.layout.refreshlistview_foot_view, null);
        footer.measure(0, 0);
        footerViewHeight = footer.getMeasuredHeight();
        footer.setPadding(0, -footerViewHeight, 0, 0);
        this.addFooterView(footer, FOOTER, true);
    }

    private void measureView(View child) {
        ViewGroup.LayoutParams p = child.getLayoutParams();
        if (p == null) {
            p = new ViewGroup.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.WRAP_CONTENT);
        }
        int childWidthSpec = ViewGroup.getChildMeasureSpec(0, 0 + 0, p.width);
        int lpHeight = p.height;
        int childHeightSpec;
        if (lpHeight > 0) {
            childHeightSpec = MeasureSpec.makeMeasureSpec(lpHeight, MeasureSpec.EXACTLY);//得到的是size
        } else {
            childHeightSpec = MeasureSpec.makeMeasureSpec(lpHeight, MeasureSpec.UNSPECIFIED);//得到的是子布局的实际大小
        }
        child.measure(childWidthSpec, childHeightSpec);
    }

    private void topPadding(int topPadding) {
        header.setPadding(header.getPaddingLeft(), topPadding, header.getPaddingRight(), header.getPaddingBottom());
        header.invalidate();
    }

    @Override
    public void onScrollStateChanged(AbsListView view, int scrollState) {
        if (scrollState == SCROLL_STATE_IDLE || scrollState == SCROLL_STATE_FLING) {
            //判断当前是否已经到了底部
            if (isScrollToBottom && !isLoadingMore) {
                isLoadingMore = true;
                //当前到底部
                Log.i(TAG, "加载更多数据");
                footer.setPadding(0, 0, 0, 0);
                this.setSelection(this.getCount());
                if (onLoadListener != null) {
                    onLoadListener.onLoad();
                }
            }
        }
    }

    @Override
    public void onScroll(AbsListView view, int firstVisibleItem, int visibleItemCount, int totalItemCount) {
        this.firstVisibleItem = firstVisibleItem;
        if (getLastVisiblePosition() == (totalItemCount - 1)) {
            isScrollToBottom = true;
        } else {
            isScrollToBottom = false;
        }
    }

    @Override
    public boolean onTouchEvent(MotionEvent ev) {
        switch (ev.getAction()) {
            case MotionEvent.ACTION_DOWN:
                startY = (int) ev.getY();
                break;
            case MotionEvent.ACTION_MOVE:
                int moveY = (int) ev.getY();
                //移动中的y - 按下的y = 间距。
                int diff = (moveY - startY) / 2;
                //头布局的高度 + 间距 = paddingTop
                int paddingTop = -headerContentHeight + diff;
                //如果：头布局的高度 > paddingTop的值 执行super.onTouchEvent(ev);
                if (firstVisibleItem == 0 && -headerContentHeight < paddingTop) {
                    if (paddingTop > 0 && state == NONE) {//完全显示了
                        Log.i(TAG, "松开刷新");
                        state = PULL;
                        refreshHeaderViewByState();
                    } else if (paddingTop < 0 && state == PULL) {//没有显示完全
                        Log.i(TAG, "下拉刷新");
                        state = NONE;
                        refreshHeaderViewByState();
                    }
                    header.setPadding(0, paddingTop, 0, 0);
                }
                break;
            case MotionEvent.ACTION_UP:
                //判断当前的状态是松开刷新还是下拉刷新
                if (state == PULL) {
                    Log.i(TAG, "刷新数据.");
                    //把头布局设置为完全显示状态
                    header.setPadding(0, 0, 0, 0);
                    //进入到正在刷新中状态
                    state = REFRESHING;
                    refreshHeaderViewByState();
                    if (onRefreshListener != null) {
                        onRefreshListener.onRefresh();//调用使用者的监听方法
                    }
                } else if (state == NONE) {
                    //隐藏头布局
                    header.setPadding(0, -headerContentHeight, 0, 0);
                }
        }
        return super.onTouchEvent(ev);
    }


    private void refreshHeaderViewByState() {
        switch (state) {
            case NONE://下拉刷新状态
                tvState.setText("下拉刷新");
//                iv_pull.startAnimation(downAnimation);//执行向下旋转
                break;
            case PULL://松开刷新状态
                tvState.setText("松开刷新");
//                iv_pull.startAnimation(upAnimation);//执行向上旋转
                break;
            case REFRESHING://正在刷新中状态
                iv_pull.clearAnimation();
                iv_pull.setVisibility(View.GONE);
                mProgressBar.setVisibility(View.VISIBLE);
                tvState.setText("正在刷新中。。。");
                break;
        }
    }

    public void hideHeaderView() {
        header.setPadding(0, -headerContentHeight, 0, 0);
        iv_pull.setVisibility(View.VISIBLE);
        mProgressBar.setVisibility(View.GONE);
        tvState.setText("下拉刷新");
        tvLastUpdateTime.setText("最后刷新时间：" + getLastUpdateTime());
        state = NONE;
    }

    public void hideFooterView() {
        footer.setPadding(0, -footerViewHeight, 0, 0);
        isLoadingMore = false;
    }

    public void setOnRefreshListener(OnRefreshListener onRefreshListener) {
        this.onRefreshListener = onRefreshListener;

    }

    public void setOnLoadListener(OnLoadListener onLoadListener) {
        this.onLoadListener = onLoadListener;
    }

    public void onRefresh() {

        if (onRefreshListener != null) {
            onLoadListener.onLoad();
        }
    }

    public void onLoad() {
        if (onLoadListener != null) {
            onLoadListener.onLoad();
        }
    }

    public interface OnRefreshListener {
        //定义下拉刷新接口
        public void onRefresh();
    }

    public interface OnLoadListener {
        //定义上拉加载更多
        public void onLoad();
    }


}
