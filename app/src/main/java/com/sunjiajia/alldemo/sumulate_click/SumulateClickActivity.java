package com.sunjiajia.alldemo.sumulate_click;

import android.app.ListActivity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.ListView;

/**
 * Created by mk on 2017/1/3.
 */

public class SumulateClickActivity extends ListActivity {

    String[] array = new String[]{"应用本上点击", "另外应用点击"};

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        ArrayAdapter adapter = new ArrayAdapter(this, android.R.layout.simple_list_item_1, array);
        setListAdapter(adapter);
    }

    @Override
    protected void onListItemClick(ListView l, View v, int position, long id) {
        super.onListItemClick(l, v, position, id);
        switch (position) {
            case 0:
                startActivity(Base_SumulateClick.class);
                break;
            case 1:
                break;
        }
    }

    private void startActivity(Class clzz) {
        startActivity(new Intent(this, clzz));
    }
}
