package com.sunjiajia.alldemo.card_stack;

import android.app.ListActivity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import com.sunjiajia.alldemo.card_stack.FlippableStackView.uiActivity.flippablestackviewActivity;
import com.sunjiajia.alldemo.card_stack.flip.IssueFilpActivity;

/**
 * Created by mk on 2016/12/8.
 */

public class Card_stackList extends ListActivity {


    String[] array = new String[]{"卡片墙滑动效果","filpOne","flippablestackView"};

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        ArrayAdapter adapter = new ArrayAdapter(this,android.R.layout.simple_list_item_1,array);
        setListAdapter(adapter);
    }

    @Override
    protected void onListItemClick(ListView l, View v, int position, long id) {
        super.onListItemClick(l, v, position, id);
        switch (position){
            case 0://卡片墙滑动效果
                startActivity(TinderCardActivity.class);
                break;
            case 1://filp翻页特效一，引用jar包filplibrary
                startActivity(IssueFilpActivity.class);
                break;
            case 2://filp翻页特效一，引用jar包filplibrary
                startActivity(flippablestackviewActivity.class);
                break;
        }
    }

    private void startActivity(Class<?> clas){
        Intent intent = new Intent();
        intent.setClass(this,clas);
        startActivity(intent);

    }
}
