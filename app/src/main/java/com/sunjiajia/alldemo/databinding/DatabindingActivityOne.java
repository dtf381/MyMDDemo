package com.sunjiajia.alldemo.databinding;

import android.app.Activity;
import android.databinding.DataBindingUtil;
import android.os.Bundle;
import android.view.View;
import android.widget.Toast;

import com.sunjiajia.androidnewwidgetsdemo.R;
import com.sunjiajia.androidnewwidgetsdemo.databinding.DatabindingBinding;

/**
 * Created by mk on 2017/2/6.
 */

public class DatabindingActivityOne extends Activity{

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
//        setContentView(R.layout.databinding);
        DatabindingBinding binding  = DataBindingUtil.setContentView(this,R.layout.databinding);
        User user=new User("donkor",10,"http://blog.csdn.net/donkor_");
        binding.setUser(user);

        //事件绑定--单机
        binding.setMyClick(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(DatabindingActivityOne.this,"发生了点击",Toast.LENGTH_SHORT).show();
            }
        });

        binding.myBtnLongClick.setOnLongClickListener(new View.OnLongClickListener() {
            @Override
            public boolean onLongClick(View v) {
                Toast.makeText(DatabindingActivityOne.this,"发生了长按事件",Toast.LENGTH_SHORT).show();
                return false;
            }
        });
    }

}
