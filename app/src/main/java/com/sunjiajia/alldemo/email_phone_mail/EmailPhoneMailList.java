package com.sunjiajia.alldemo.email_phone_mail;

import android.app.ListActivity;
import android.content.Intent;
import android.os.Bundle;
import android.os.PersistableBundle;
import android.support.annotation.Nullable;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.ListView;

/**
 * Created by mk on 2017/2/17.
 * Email 邮件的收发，会引用到activation.jar additionnal.jar commons-email-1.4.jar mail.jar 这些jar包作为功能
 *
 */

public class EmailPhoneMailList extends ListActivity {

    private String[] strArray = new String[]{"Email邮件的收发","短信的拦截与发送","手机的监听，监听来电号码，以及来电的转接"};

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
            case 0:
                startActivity(EmailSendAndReceive.class);//Email邮件的发送和接受
                break;
            case 1:
                break;
            case 2:
                break;
            default:
                break;
        }
    }

    private void startActivity(Class clazz){
        Intent intent = new Intent(EmailPhoneMailList.this,clazz);
        startActivity(intent);
    }


    @Override
    protected void onResume() {
        super.onResume();
    }

    @Override
    protected void onPause() {
        super.onPause();
    }

    @Override
    protected void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState(outState);
    }

    @Override
    public void onSaveInstanceState(Bundle outState, PersistableBundle outPersistentState) {
        super.onSaveInstanceState(outState, outPersistentState);
    }

    @Override
    protected void onRestoreInstanceState(Bundle savedInstanceState) {
        super.onRestoreInstanceState(savedInstanceState);
    }

    @Override
    public void onRestoreInstanceState(Bundle savedInstanceState, PersistableBundle persistentState) {
        super.onRestoreInstanceState(savedInstanceState, persistentState);
    }
}
