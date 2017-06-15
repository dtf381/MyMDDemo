package com.sunjiajia.alldemo.SrcollView_Refresh;

import android.app.ListActivity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.ListView;

/**
 * Created by mk on 2016/12/16.
 */

public class ScrollVieRefreshList extends ListActivity {

    String[] array = new String[]{"ScrollView上下拉弹性拉伸","scrollView空间滑动固定效果","设置scrollView滑动固定2"
            ,"ListView上下拉刷新01","ListView上下拉刷新02"};
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        ArrayAdapter adapter = new ArrayAdapter(this,android.R.layout.simple_list_item_1,array);
        setListAdapter(adapter);
    }

    @Override
    protected void onListItemClick(ListView l, View v, int position, long id) {
        super.onListItemClick(l, v, position, id);
        switch (position){
            case 0:
                startActivity(ScrollViewStreching.class);
                break;
            case 1:
                startActivity(ScrollViewPinnedActivity.class);
                break;
            case 2:
                startActivity(ScrollViewPinned2Activity.class);
                break;
            case 3:
                startActivity(RefreshListViewOne.class);
                break;
            case 4:
                startActivity(RefreshListViewTwo.class);
                break;
        }
    }

    private void startActivity(Class<?> cls){
        Intent intent = new Intent(this,cls);
        startActivity(intent);
    }
}
