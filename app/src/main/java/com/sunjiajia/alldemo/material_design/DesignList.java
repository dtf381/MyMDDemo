package com.sunjiajia.alldemo.material_design;

import android.app.ListActivity;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.Menu;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.ListView;

/**
 * Created by mk on 2017/2/23.
 */

public class DesignList extends ListActivity {

    private String[] strArray = new String[]{"TextInputEditText","CoordinatorLayoutAppBarLayoutCollapsingToolbarLayout"};

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        ArrayAdapter adapter = new ArrayAdapter(this,android.R.layout.simple_list_item_1,strArray);
        setListAdapter(adapter);
    }

    @Override
    protected void onListItemClick(ListView l, View v, int position, long id) {
        super.onListItemClick(l, v, position, id);
        switch (position){
            case 0://TextInputEditText
                startActivity(TextInputEditText.class);
                break;
            case 1:
                startActivity(CoordinatorLayout_AppBarLayout_CollapsingToolbarLayout.class);
                break;
            case 2:
                break;
        }
    }

    private void startActivity(Class clazz){
        Intent intent = new Intent(DesignList.this,clazz);
        startActivity(intent);
    }

    @Override
    protected void onPostCreate(@Nullable Bundle savedInstanceState) {
        super.onPostCreate(savedInstanceState);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        return super.onCreateOptionsMenu(menu);
    }
}
