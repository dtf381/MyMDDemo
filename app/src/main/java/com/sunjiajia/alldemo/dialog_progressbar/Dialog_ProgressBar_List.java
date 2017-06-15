package com.sunjiajia.alldemo.dialog_progressbar;

import android.app.Dialog;
import android.app.ListActivity;
import android.content.Intent;
import android.os.Bundle;
import android.util.DisplayMetrics;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import com.sunjiajia.androidnewwidgetsdemo.R;

/**
 * Created by mk on 2016/12/6.
 */

public class Dialog_ProgressBar_List extends ListActivity {

    String[] str = new String[]{"圆角dialog","全屏dialog"};

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (savedInstanceState == null) {
            setListAdapter(new ArrayAdapter(this, android.R.layout.simple_list_item_1, str));
        }
    }

    @Override
    protected void onListItemClick(ListView l, View v, int position, long id) {
        super.onListItemClick(l, v, position, id);
        switch (position) {
            case 0://圆角AlertDialog
                startActivity(RoundDialog.class);
                break;
            case 1:
                fullDialog();
                break;
            case 2:
                break;
        }
    }

    private void startActivity(Class<?> clas) {
        Intent intent = new Intent();
        intent.setClass(this, clas);
        startActivity(intent);
    }

    //全屏dialog
    private void fullDialog(){
        WindowManager windowManager = getWindowManager();
        DisplayMetrics displaysMetrics = new DisplayMetrics();
        windowManager.getDefaultDisplay().getMetrics(displaysMetrics);

        Dialog dialog = new Dialog(this,R.style.Dialog_Fullscreen);
        dialog.setContentView(R.layout.fulldialog);
        Window window =dialog.getWindow();
        View view = window.getDecorView();
        WindowManager.LayoutParams lp = window.getAttributes();
        lp.verticalMargin = 0;
        lp.horizontalMargin =  0;
        lp.width = displaysMetrics.widthPixels;
        lp.height = displaysMetrics.heightPixels;
        window.setAttributes(lp);
        dialog.show();

    }
}
