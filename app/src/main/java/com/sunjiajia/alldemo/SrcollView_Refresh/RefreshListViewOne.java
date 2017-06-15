package com.sunjiajia.alldemo.SrcollView_Refresh;

import android.app.Activity;
import android.content.Context;
import android.os.Bundle;
import android.os.PersistableBundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;
import android.widget.Toast;
import com.sunjiajia.androidnewwidgetsdemo.R;

import java.util.ArrayList;

/**
 * Created by mk on 2017/2/15.
 */

public class RefreshListViewOne extends Activity implements ReFreshListView.OnRefreshListener,ReFreshListView.OnLoadListener{
    private TextView textView;
    private String result;
    private ReFreshListView listView;
    private MyAdapter adapter;
    private ArrayList<String> arraylist;

    @Override
    public void onCreate(Bundle savedInstanceState, PersistableBundle persistentState) {
        super.onCreate(savedInstanceState, persistentState);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.refreshlistview_activitymain_one);
        initDatas();
        listView = (ReFreshListView) findViewById(R.id.lv);
        listView.setOnRefreshListener(this);
        listView.setOnLoadListener(this);
        adapter = new MyAdapter(LayoutInflater.from(RefreshListViewOne.this),RefreshListViewOne.this,arraylist);
        listView.setAdapter(adapter);

    }
    private void initDatas(){
        if (arraylist == null){
            arraylist = new ArrayList<String>();
        }
        for (int i =0;i<50;i++){
            arraylist.add("小明" + i);
        }
        if (adapter !=null){
            listView.deferNotifyDataSetChanged();
        }
    }

    @Override
    public void onLoad() {
        Toast.makeText(this,"加载更多",Toast.LENGTH_SHORT).show();
        initDatas();
        listView.postDelayed(new Runnable() {
            @Override
            public void run() {
                listView.hideFooterView();
            }
        },3000);
    }

    @Override
    public void onRefresh() {
        Toast.makeText(this,"下拉刷新",Toast.LENGTH_SHORT).show();
        listView.postDelayed(new Runnable() {
            @Override
            public void run() {
                listView.hideHeaderView();
            }
        },3000);
    }
    private class MyAdapter extends BaseAdapter{
        private LayoutInflater inflater;
        private Context mContext;
        private ArrayList<String> list;

        public MyAdapter(LayoutInflater inflater, Context mContext, ArrayList<String> list) {
            this.inflater = inflater;
            this.mContext = mContext;
            this.list = list;
        }

        private MyAdapter() {
        }

        @Override
        public int getCount() {
            return list.size();
        }

        @Override
        public Object getItem(int position) {
            return list.get(position);
        }

        @Override
        public long getItemId(int position) {
            return position;
        }

        @Override
        public View getView(int position, View convertView, ViewGroup parent) {
            if (convertView == null){
                convertView = new TextView(mContext);
            }
            ((TextView)convertView).setText(list.get(position));
            return convertView;
        }
    }
}
