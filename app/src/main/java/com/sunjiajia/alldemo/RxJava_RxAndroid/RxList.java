package com.sunjiajia.alldemo.RxJava_RxAndroid;

import android.app.ListActivity;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AlertDialog;
import android.view.KeyEvent;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.Toast;

import com.sunjiajia.alldemo.RxJava_RxAndroid.mvp.LoginActivity;

import static com.sunjiajia.alldemo.RxJava_RxAndroid.RxJavaTest.from;
import static com.sunjiajia.alldemo.RxJava_RxAndroid.RxJavaTest.just;

/**
 * Created by mk on 2017/3/1.
 */

public class RxList extends ListActivity {

    AlertDialog dialog;

    private String[] strArray = new String[]{"Rxjava基础练习", "RxAndroid基础练习", "mvp"};

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        ArrayAdapter adapter = new ArrayAdapter(this, android.R.layout.simple_list_item_1, strArray);
        setListAdapter(adapter);
        dialog = new AlertDialog.Builder(this).create();
        dialog.setCanceledOnTouchOutside(true);
    }

    @Override
    protected void onListItemClick(ListView l, View v, int position, long id) {
        super.onListItemClick(l, v, position, id);
        switch (position) {
            case 0:
//                new RxJavaTest().oneObservable();
//                just();
                from();
                break;
            case 1:
                new RxJavaTest().initDatas();
                break;
            case 2:
                startActivity(LoginActivity.class);
                break;
        }
    }

    public void startActivity(Class clazz) {
        Intent intent = new Intent(this, clazz);
        startActivity(intent);

    }


    private void showDialog(String title, String content) {
        dialog.setTitle(title);
        dialog.setMessage(content);
        dialog.setOnCancelListener(new DialogInterface.OnCancelListener() {
            @Override
            public void onCancel(DialogInterface dialog) {
                Toast.makeText(RxList.this, "cancel", Toast.LENGTH_SHORT).show();
            }
        });
        dialog.setOnDismissListener(new DialogInterface.OnDismissListener() {
            @Override
            public void onDismiss(DialogInterface dialog) {
                Toast.makeText(RxList.this, "onDismiss", Toast.LENGTH_SHORT).show();
            }
        });
        dialog.setOnKeyListener(new DialogInterface.OnKeyListener() {
            @Override
            public boolean onKey(DialogInterface dialog, int keyCode, KeyEvent event) {
                Toast.makeText(RxList.this, "onkeyDown", Toast.LENGTH_SHORT).show();
                return false;
            }
        });
        dialog.setVolumeControlStream(1);
        dialog.show();
    }
}
