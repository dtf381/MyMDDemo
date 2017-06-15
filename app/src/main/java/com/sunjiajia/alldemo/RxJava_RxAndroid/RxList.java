package com.sunjiajia.alldemo.RxJava_RxAndroid;

import android.app.ListActivity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.ListView;

/**
 * Created by mk on 2017/3/1.
 */

public class RxList extends ListActivity {

    private String[] strArray = new String[]{"Rxjava基础练习","RxAndroid基础练习"};
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        ArrayAdapter adapter = new ArrayAdapter(this,android.R.layout.simple_list_item_1,strArray);
    }

    @Override
    protected void onListItemClick(ListView l, View v, int position, long id) {
        super.onListItemClick(l, v, position, id);
        switch (position){
            case 0:
                break;
            case 1:
                break;
        }
    }
    public void startActivity(Class clazz){
        Intent intent = new Intent(this,clazz);
        startActivity(intent);

    }
}
