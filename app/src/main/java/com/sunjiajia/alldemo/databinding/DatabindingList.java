package com.sunjiajia.alldemo.databinding;

import android.app.ListActivity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import com.bumptech.glide.Glide;

/**
 * Created by mk on 2017/2/7.
 * 2015年google IO大会介绍的一款数据绑定的方法库，dataBinding
 */

public class DatabindingList extends ListActivity {

    String[] array = new String[]{
            "databindingOne",
            "databindingTwo",
            "databindingListView",
            "GlideDemo"
    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (savedInstanceState == null){
            ArrayAdapter adapter = new ArrayAdapter(this,android.R.layout.simple_list_item_1,array);
            setListAdapter(adapter);
        }
    }

    @Override
    protected void onListItemClick(ListView l, View v, int position, long id) {
        super.onListItemClick(l, v, position, id);
        switch (position){
            case 0://databindingOne
                startActivity(DatabindingActivityOne.class);
                break;
            case 1://databindingTwo
                startActivity(DatabindingActivityTwo.class);
                break;
            case 2://databindingList
                startActivity(DatabindingListView.class);
                break;
            case 3://databindingList
                startActivity(GlideDemo.class);
                break;
        }
    }
    private void startActivity(Class clazz){
        Intent intent = new Intent();
        intent.setClass(this,clazz);
        startActivity(intent);
    }


/*    ※ 写在之后的话，这里我们需要知道Databinding支持与不支持的表达式，语法。如下
    支持的表达式：

    Mathematical + - / * %
    String concatenation +
    Logical && ||
    Binary & | ^
    Unary + - ! ~
    Shift >> >>> <<
    Comparison == > < >= <=
            instanceof
    Grouping ()
    Literals - character, String, numeric, null
    Cast
    Method calls
    Field access
    Array access []
    Ternary operator ?:
    不支持的表达式：

            this
            super
            new
    Explicit generic invocation*/

}
